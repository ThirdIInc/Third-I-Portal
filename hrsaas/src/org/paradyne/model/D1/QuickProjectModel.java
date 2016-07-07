package org.paradyne.model.D1;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.QuickProjectBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.leave.RegularizationModel;

public class QuickProjectModel extends ModelBase {
	/**
	 * METHOD TO SET COMPLETED BY AND ON
	 */
	public void setOnloadData(QuickProjectBean bean) {

		/**
		 * SET MANAGER DETAILS
		 */
		String query = "SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,ADD_PH1,ADD_EMAIL FROM HRMS_EMP_OFFC "
				+ "	LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P') WHERE HRMS_EMP_OFFC.EMP_ID="
				+ bean.getUserEmpId();
		Object[][] object = getSqlModel().getSingleResult(query);
		if (object != null && object.length > 0) {
			bean
					.setManagerCode(Utility.checkNull(String
							.valueOf(object[0][0])));
			bean.setManagerToken(Utility
					.checkNull(String.valueOf(object[0][1])));
			bean
					.setManagerName(Utility.checkNull(String
							.valueOf(object[0][2])));
			bean.setPhone(Utility.checkNull(String.valueOf(object[0][3])));
			bean.setManagerEmail(Utility
					.checkNull(String.valueOf(object[0][4])));
		}

		/**
		 * SET COMPLETED BY AND ON
		 */
		String completeOnQuery = "SELECT EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI') FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ bean.getUserEmpId();
		Object[][] obj = getSqlModel().getSingleResult(completeOnQuery);
		if (obj != null && obj.length > 0) {
			bean.setCompletedBy(String.valueOf(obj[0][0]));
			bean.setCompletedOn(String.valueOf(obj[0][1]));
		}

	}

	public boolean draft(QuickProjectBean bean, String status) {
		boolean flag = false;
		String selQuery = "SELECT NVL(MAX(PROJECT_CODE),0)+1 ,(NVL(MAX(PROJECT_CODE),0)+1)||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_D1_QUICK_PROJECT ";
		Object[][] data = getSqlModel().getSingleResult(selQuery);
		if (bean.getQuickProjectCode().equals("")) {
			bean.setQuickProjectCode(String.valueOf(data[0][0]));
			if (data != null && data.length > 0) {
				try {
					ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					String applnCode = model.generateApplicationCode(bean
							.getQuickProjectCode(), "D1-QUICK PROJECT", bean
							.getManagerCode(), String.valueOf(data[0][2]));
					bean.setFileheaderName(checkNull(applnCode));
					model.terminate();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		Object[][] obj = new Object[1][34];
		obj[0][0] = bean.getQuickProjectCode();
		obj[0][1] = bean.getManagerCode();
		obj[0][2] = bean.getPhone();
		obj[0][3] = bean.getManagerEmail();
		obj[0][4] = bean.getNoOfTemps();
		obj[0][5] = bean.getPositionTitle();
		obj[0][6] = bean.getBrandCode();
		obj[0][7] = bean.getBranchCode();
		obj[0][8] = bean.getExecutive();
		obj[0][9] = bean.getDepartmentCode();
		obj[0][10] = bean.getLocation();
		obj[0][11] = bean.getCustomer();
		obj[0][12] = bean.getOpsDirectorName();
		obj[0][13] = bean.getMaxTempAgency();
		obj[0][14] = bean.getStandardHour();// hh24:mm
		obj[0][15] = bean.getPerWeek();
		obj[0][16] = bean.getOtRequired();
		obj[0][17] = bean.getNoOfOTHrs();// hh
		obj[0][18] = bean.getProject();
		obj[0][19] = bean.getStartDate();// date
		obj[0][20] = bean.getEndDate();// date'
		obj[0][21] = bean.getExtension();
		obj[0][22] = bean.getSbu();
		obj[0][23] = bean.getSupportType();
		obj[0][24] = bean.getHardwareSkill();
		obj[0][25] = bean.getSoftwareSkill();
		obj[0][26] = bean.getNetworkSkill();
		obj[0][27] = bean.getOtherSkill();
		obj[0][28] = bean.getNextAppCode();
		obj[0][29] = bean.getForwardTo();
		obj[0][30] = bean.getUserEmpId();
		// obj[0][31]="SYSDATE";
		obj[0][31] = status;
		obj[0][32] = bean.getFileheaderName();
		obj[0][33] = bean.getApplicantComments().trim();
		
		String delQuery = "DELETE FROM HRMS_D1_QUICK_PROJECT WHERE PROJECT_CODE=" + bean.getQuickProjectCode();
		getSqlModel().singleExecute(delQuery);
		String insQuery = "INSERT INTO HRMS_D1_QUICK_PROJECT(PROJECT_CODE, MANAGER_CODE, MANAGER_PHONE, MANAGER_EMAIL, NO_OF_TEMPS, POSITION_TITLE, BRAND_CODE, BRANCH_CODE, EXECUTIVE, DEPT_CODE, LOCATION, CUSTOMER, OPS_NAME, MAX_TEMP_AGENCY, STANDARD_HRS, PER_WEEK, OT_REQUIRED, NO_OT_HRS, PROJECT, START_DATE, END_DATE, EXTENSION, SBU, SUPPORT_TYPE, HARDWARE_SKILL, SOFTWARE_SKILL, NETWORK_SKILL, OTHER_SKILL, NEXT_APPR_CODE, FORWARD_TO, COMPLETED_BY, COMPLETED_ON, STATUS,TRACKING_NO, APPLICANT_COMMENTS)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'HH24:MI'),?,?,TO_DATE(?,'HH24:MI'),?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?)	";

		flag = getSqlModel().singleExecute(insQuery, obj);

		if (flag) {
			String revenueIns = "INSERT INTO HRMS_D1_QUICK_PROJ_REVENUE( PER_PRJ_CURR, PER_PRJ_NEXT, PER_PRJ_SUCC, REV_PRJ_CURR, REV_PRJ_NEXT, REV_PRJ_SUCC, LABORCOST_PRJ_CURR, LABORCOST_PRJ_NEXT, LABORCOST_PRJ_SUCC, OTHERCOST_PRJ_CURR, OTHERCOST_PRJ_NEXT, OTHERCOST_PRJ_SUCC, TOTALCOST_PRJ_CURR, TOTALCOST_PRJ_NEXT, TOTALCOST_PRJ_SUCC, PROFITCOST_PRJ_CURR, PROFITCOST_PRJ_NEXT, PROFITCOST_PRJ_SUCC, GROSSCOST_PRJ_CURR, GROSSCOST_PRJ_NEXT, GROSSCOST_PRJ_SUCC, MEETS_PRJ_CURR, MEETS_PRJ_NEXT, MEETS_PRJ_SUCC, REV_COST_CURR, REV_COST_NEXT, REV_COST_SUCC, LABORCOST_CURR, LABORCOST_NEXT, LABORCOST_SUCC, OTHERCOST_CURR, OTHERCOST_NEXT, OTHERCOST_SUCC, OPSCOST_CURR, OPSCOST_NEXT, OPSCOST_SUCC,QUICK_PROJECT_CODE,CURR_EXP, NEXT_EXP, SUCC_EXP) "
					+ "   VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)   ";
			Object[][] revObj = new Object[1][40];
			revObj[0][0] = bean.getPerCurrentMonth();
			revObj[0][1] = bean.getPerNextMonth();
			revObj[0][2] = bean.getPerSuccessiveMonth();
			revObj[0][3] = bean.getRevCurrentMonth();
			revObj[0][4] = bean.getRevNextMonth();
			revObj[0][5] = bean.getRevSuccessiveMonth();
			revObj[0][6] = bean.getLaborCurrentMonth();
			revObj[0][7] = bean.getLaborNextMonth();
			revObj[0][8] = bean.getLaborSuccessiveMonth();
			revObj[0][9] = bean.getOthorCurrentMonth();
			revObj[0][10] = bean.getOthorNextMonth();
			revObj[0][11] = bean.getOthorSuccessiveMonth();
			revObj[0][12] = bean.getTotalCurrentMonth();
			revObj[0][13] = bean.getTotalNextMonth();
			revObj[0][14] = bean.getTotalSuccessiveMonth();
			revObj[0][15] = bean.getProfitCurrentMonth();
			revObj[0][16] = bean.getProfitNextMonth();
			revObj[0][17] = bean.getProfitSuccessiveMonth();
			revObj[0][18] = bean.getGrossCurrentMonth();
			revObj[0][19] = bean.getGrossNextMonth();
			revObj[0][20] = bean.getGrossSuccessiveMonth();
			revObj[0][21] = bean.getMeetsCurrentMonth();
			revObj[0][22] = bean.getMeetsNextMonth();
			revObj[0][23] = bean.getMeetsSuccessiveMonth();

			revObj[0][24] = bean.getRevCurrent();
			revObj[0][25] = bean.getRevNext();
			revObj[0][26] = bean.getRevSuccessive();
			revObj[0][27] = bean.getLaborCurrent();
			revObj[0][28] = bean.getLaborNext();
			revObj[0][29] = bean.getLaborSuccessive();
			revObj[0][30] = bean.getOthorCurrent();
			revObj[0][31] = bean.getOthorNext();
			revObj[0][32] = bean.getOthorSuccessive();
			revObj[0][33] = bean.getOpsCurrent();
			revObj[0][34] = bean.getOpsNext();
			revObj[0][35] = bean.getOpsSuccessive();
			revObj[0][36] = bean.getQuickProjectCode();

			revObj[0][37] = bean.getCurrExp();
			revObj[0][38] = bean.getNextExp();
			revObj[0][39] = bean.getSuccExp();

			String delRevenueQuery = "DELETE FROM HRMS_D1_QUICK_PROJ_REVENUE WHERE QUICK_PROJECT_CODE="
					+ bean.getQuickProjectCode();
			getSqlModel().singleExecute(delRevenueQuery);

			flag = getSqlModel().singleExecute(revenueIns, revObj);
		}
		return flag;
	}

	public void input(QuickProjectBean bean, HttpServletRequest request) {

		if (bean.getFlag().equals("")) {
			bean.setHeaderName("Pending Application");
		} else if (bean.getFlag().equals("AA")) {
			bean.setHeaderName("Approved Application");
		} else if (bean.getFlag().equals("RR")) {
			bean.setHeaderName("Rejected Application");
		}

		String query = "SELECT NVL(TRACKING_NO,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(COMPLETED_ON,'DD-MM-YYYY') "
				+ "	,HRMS_D1_QUICK_PROJECT.MANAGER_CODE,PROJECT_CODE FROM HRMS_D1_QUICK_PROJECT "
				+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_QUICK_PROJECT.MANAGER_CODE) ";
		// if (bean.isGeneralFlag()) {
		query += " WHERE HRMS_D1_QUICK_PROJECT.MANAGER_CODE="
				+ bean.getUserEmpId();
		// }
		/*
		 * else{ query += " WHERE 1=1 "; }
		 */

		String draft = query + " AND STATUS='D' ";
		draft = draft + " ORDER BY HRMS_D1_QUICK_PROJECT.TRACKING_NO DESC  ";

		Object[][] data = getSqlModel().getSingleResult(draft);
		String[] pageIndex = new String[5];
		pageIndex[0] = "0";
		pageIndex[1] = "20";
		pageIndex[2] = "1";
		pageIndex[3] = "1";
		pageIndex[4] = "";
		if (data != null) {
			pageIndex = new org.paradyne.lib.Utility().doPaging(bean
					.getMyPage(), data.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			try {
				System.out.println("pageIndex  of 2============    "
						+ Integer.parseInt(String.valueOf(pageIndex[2])));
			} catch (Exception e) {
				// TODO: handle exception
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			/**
			 * DRAFT APPLICATION
			 */
		}
		if (data != null && data.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
					.parseInt(String.valueOf(pageIndex[1])); i++) {
				QuickProjectBean bean1 = new QuickProjectBean();
				bean1.setIttEmployeeToken(String.valueOf(data[i][0]));
				bean1.setIttEmployee(String.valueOf(data[i][1]));
				bean1.setIttApplicationDate(String.valueOf(data[i][2]));
				bean1.setIttquickProjectCode(String.valueOf(data[i][4]));
				list.add(bean1);
				bean1.setButtonName("Edit");

			}
			bean.setListDraft(list);
		}

		/**
		 * IN PROGRESS
		 */
		String progress = query + " AND STATUS IN('P','F','S')  ";
		progress = progress
				+ " ORDER BY HRMS_D1_QUICK_PROJECT.TRACKING_NO DESC  ";
		Object[][] objProgress = getSqlModel().getSingleResult(progress);
		String[] pageIndexRe = setData(bean, request, objProgress,
				"totalPageP", "pageNoP", bean.getMyPage1(), "1");
		if (objProgress != null && objProgress.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexRe[0])); i < Integer
					.parseInt(String.valueOf(pageIndexRe[1])); i++) {
				QuickProjectBean bean1 = new QuickProjectBean();
				bean1.setIttEmployeeToken(String.valueOf(objProgress[i][0]));
				bean1.setIttEmployee(String.valueOf(objProgress[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objProgress[i][2]));
				// bean1.setIttEmployeeId(String.valueOf(objProgress[i][3]));
				bean1.setIttquickProjectCode(String.valueOf(objProgress[i][4]));
				list.add(bean1);
				bean1.setButtonName("View");
			}
			bean.setListInProgress(list);
		}

		/**
		 * APPROVED PPLICATION
		 */
		String approve = query + " AND STATUS='A'  ";
		if (bean.getFlag().equals("AA")) {
			approve = approve
					+ " ORDER BY HRMS_D1_QUICK_PROJECT.TRACKING_NO DESC  ";
			Object[][] objApprove = getSqlModel().getSingleResult(approve);
			String[] pageIndexAppr = setData(bean, request, objApprove,
					"totalPage", "pageNo", bean.getMyPage(), "");
			if (objApprove != null && objApprove.length > 0) {
				ArrayList list = new ArrayList();
				for (int i = Integer.parseInt(String.valueOf(pageIndexAppr[0])); i < Integer
						.parseInt(String.valueOf(pageIndexAppr[1])); i++) {
					QuickProjectBean bean1 = new QuickProjectBean();
					bean1.setIttEmployeeToken(String.valueOf(objApprove[i][0]));
					bean1.setIttEmployee(String.valueOf(objApprove[i][1]));
					bean1.setIttApplicationDate(String
							.valueOf(objApprove[i][2]));
					// bean1.setIttEmployeeId(String.valueOf(objApprove[i][3]));
					bean1.setIttquickProjectCode(String
							.valueOf(objApprove[i][4]));
					list.add(bean1);
					bean1.setButtonName("View");
				}
				bean.setListApprove(list);
			}
		}

		/**
		 * REJECT APPLICATION
		 */
		String reject = query + " AND STATUS='R'  ";

		if (bean.getFlag().equals("RR")) {
			reject = reject
					+ " ORDER BY HRMS_D1_QUICK_PROJECT.TRACKING_NO DESC  ";
			Object[][] objReject = getSqlModel().getSingleResult(reject);
			String[] pageIndexR = setData(bean, request, objReject,
					"totalPage", "pageNo", bean.getMyPage(), "");
			if (objReject != null && objReject.length > 0) {
				ArrayList list = new ArrayList();
				for (int i = Integer.parseInt(String.valueOf(pageIndexR[0])); i < Integer
						.parseInt(String.valueOf(pageIndexR[1])); i++) {
					QuickProjectBean bean1 = new QuickProjectBean();
					bean1.setIttEmployeeToken(String.valueOf(objReject[i][0]));
					bean1.setIttEmployee(String.valueOf(objReject[i][1]));
					bean1
							.setIttApplicationDate(String
									.valueOf(objReject[i][2]));
					// bean1.setIttEmployeeId(String.valueOf(objReject[i][3]));
					bean1.setIttquickProjectCode(String
							.valueOf(objReject[i][4]));
					list.add(bean1);
					bean1.setButtonName("View");
				}
				bean.setListReject(list);
			}
		}

		/**
		 * SEND BACK APPLICATION
		 */
		String sendBack = query + " AND STATUS='B'  ";
		sendBack = sendBack
				+ " ORDER BY HRMS_D1_QUICK_PROJECT.TRACKING_NO DESC  ";
		Object[][] objSendBack = getSqlModel().getSingleResult(sendBack);
		if (objSendBack != null && objSendBack.length > 0) {
			String[] pageIndexB = setData(bean, request, objSendBack,
					"totalPageB", "pageNoB", bean.getMyPage2(), "2");
			ArrayList list = new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexB[0])); i < Integer
					.parseInt(String.valueOf(pageIndexB[1])); i++) {
				QuickProjectBean bean1 = new QuickProjectBean();
				bean1.setIttEmployeeToken(String.valueOf(objSendBack[i][0]));
				bean1.setIttEmployee(String.valueOf(objSendBack[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objSendBack[i][2]));
				// bean1.setIttEmployeeId(String.valueOf(objSendBack[i][3]));
				bean1.setIttquickProjectCode(String.valueOf(objSendBack[i][4]));
				list.add(bean1);
				bean1.setButtonName("Edit");
			}
			bean.setListSendBack(list);
		}
	}

	public String[] setData(QuickProjectBean bean, HttpServletRequest request,
			Object[][] data, String totalPage, String pageNo, String page,
			String type) {
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(page,
				data.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		try {
			System.out.println("pageIndex  of 2============    "
					+ Integer.parseInt(String.valueOf(pageIndex[2])));
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute(totalPage, Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute(pageNo, Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1")) {
			bean.setMyPage("1");
			if (type.equals("1")) {
				bean.setMyPage1("1");
			}
			if (type.equals("2")) {
				bean.setMyPage2("1");
			}
		}
		return pageIndex;
	}

	public void setEmployeeData(QuickProjectBean bean, String check) {

		String query = "SELECT MANAGER_CODE, OFFC.EMP_TOKEN, OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME, MANAGER_PHONE, " 
					+" MANAGER_EMAIL, NO_OF_TEMPS, POSITION_TITLE, BRAND_CODE,CADRE_NAME, BRANCH_CODE,CENTER_NAME, EXECUTIVE, DEPT_CODE, " 
					+" DEPT_NAME, LOCATION, CUSTOMER, OPS_NAME, MAX_TEMP_AGENCY, TO_CHAR(STANDARD_HRS,'HH24:MI'), PER_WEEK, OT_REQUIRED, " 
					+" TO_CHAR(NO_OT_HRS,'HH24:MI'), PROJECT, TO_CHAR(START_DATE,'DD-MM-YYYY'), TO_CHAR(END_DATE,'DD-MM-YYYY'), EXTENSION, " 
					+" SBU, SUPPORT_TYPE, HARDWARE_SKILL, SOFTWARE_SKILL, NETWORK_SKILL, OTHER_SKILL, NEXT_APPR_CODE,OFFC1.EMP_TOKEN, " 
					+" OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME, FORWARD_TO, OFFC2.EMP_TOKEN||'-'||OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME, " 
					+" TO_CHAR(COMPLETED_ON,'DD-MM-YYYY  HH24:MI'), STATUS,TRACKING_NO, APPLICANT_COMMENTS "
					+ "	FROM HRMS_D1_QUICK_PROJECT "
					+ "	INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID=HRMS_D1_QUICK_PROJECT.MANAGER_CODE)"
					+ "	LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_D1_QUICK_PROJECT.BRAND_CODE) "
					+ "	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_D1_QUICK_PROJECT.BRANCH_CODE) "
					+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_D1_QUICK_PROJECT.DEPT_CODE)"
					+ "	LEFT JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID=HRMS_D1_QUICK_PROJECT.NEXT_APPR_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID=HRMS_D1_QUICK_PROJECT.COMPLETED_BY)    "
					+ "	WHERE PROJECT_CODE=" + bean.getQuickProjectCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			bean.setManagerCode(Utility.checkNull(String.valueOf(data[0][0])));
			bean.setManagerToken(Utility.checkNull(String.valueOf(data[0][1])));
			bean.setManagerName(Utility.checkNull(String.valueOf(data[0][2])));
			bean.setPhone(Utility.checkNull(String.valueOf(data[0][3])));
			bean.setManagerEmail(Utility.checkNull(String.valueOf(data[0][4])));
			bean.setNoOfTemps(Utility.checkNull(String.valueOf(data[0][5])));
			bean.setPositionTitle(Utility.checkNull(String.valueOf(data[0][6])));
			bean.setBrandCode(Utility.checkNull(String.valueOf(data[0][7])));
			bean.setBrand(Utility.checkNull(String.valueOf(data[0][8])));
			bean.setBranchCode(Utility.checkNull(String.valueOf(data[0][9])));
			bean.setBranch(Utility.checkNull(String.valueOf(data[0][10])));
			bean.setExecutive(Utility.checkNull(String.valueOf(data[0][11])));
			bean.setDepartmentCode(Utility.checkNull(String.valueOf(data[0][12])));
			bean.setDepartment(Utility.checkNull(String.valueOf(data[0][13])));
			bean.setLocation(Utility.checkNull(String.valueOf(data[0][14])));
			bean.setCustomer(Utility.checkNull(String.valueOf(data[0][15])));
			bean.setOpsDirectorName(Utility.checkNull(String.valueOf(data[0][16])));
			bean.setMaxTempAgency(Utility.checkNull(String.valueOf(data[0][17])));
			bean.setStandardHour(Utility.checkNull(String.valueOf(data[0][18])));// hh24:mm
			bean.setPerWeek(Utility.checkNull(String.valueOf(data[0][19])));
			bean.setOtRequired(Utility.checkNull(String.valueOf(data[0][20])));
			bean.setNoOfOTHrs(Utility.checkNull(String.valueOf(data[0][21])));// hh
			bean.setProject(Utility.checkNull(String.valueOf(data[0][22])));
			bean.setStartDate(Utility.checkNull(String.valueOf(data[0][23])));// date
			bean.setEndDate(Utility.checkNull(String.valueOf(data[0][24])));// date'
			bean.setExtension(Utility.checkNull(String.valueOf(data[0][25])));
			bean.setSbu(Utility.checkNull(String.valueOf(data[0][26])));
			bean.setSupportType(Utility.checkNull(String.valueOf(data[0][27])));
			bean.setHardwareSkill(Utility.checkNull(String.valueOf(data[0][28])));
			bean.setSoftwareSkill(Utility.checkNull(String.valueOf(data[0][29])));
			bean.setNetworkSkill(Utility.checkNull(String.valueOf(data[0][30])));
			bean.setOtherSkill(Utility.checkNull(String.valueOf(data[0][31])));
			if (check.equals("")) {
				bean.setNextAppCode(Utility.checkNull(String.valueOf(data[0][32])));
				bean.setNextAppToken(Utility.checkNull(String.valueOf(data[0][33])));
				bean.setNextAppName(Utility.checkNull(String.valueOf(data[0][34])));
			} else {
				bean.setNextAppCode("");
				bean.setNextAppToken("");
				bean.setNextAppName("");
			}
			bean.setForwardTo(Utility.checkNull(String.valueOf(data[0][35])));
			bean.setForwardToHidden(Utility.checkNull(String.valueOf(data[0][35])));
			bean.setCompletedBy(Utility.checkNull(String.valueOf(data[0][36])));
			bean.setCompletedOn(Utility.checkNull(String.valueOf(data[0][37])));
			bean.setStatus(Utility.checkNull(String.valueOf(data[0][38])));

			bean.setFileheaderName(Utility.checkNull(String.valueOf(data[0][39])));
			bean.setApplicantComments(Utility.checkNull(String.valueOf(data[0][40])));
		}
		/**
		 * SET REVENUES
		 */

		String revenueQuery = "SELECT  PER_PRJ_CURR, PER_PRJ_NEXT, PER_PRJ_SUCC, REV_PRJ_CURR, REV_PRJ_NEXT, REV_PRJ_SUCC, LABORCOST_PRJ_CURR, LABORCOST_PRJ_NEXT, LABORCOST_PRJ_SUCC, OTHERCOST_PRJ_CURR, OTHERCOST_PRJ_NEXT, OTHERCOST_PRJ_SUCC, TOTALCOST_PRJ_CURR, TOTALCOST_PRJ_NEXT, TOTALCOST_PRJ_SUCC, PROFITCOST_PRJ_CURR, PROFITCOST_PRJ_NEXT, PROFITCOST_PRJ_SUCC, GROSSCOST_PRJ_CURR, GROSSCOST_PRJ_NEXT, GROSSCOST_PRJ_SUCC, MEETS_PRJ_CURR, MEETS_PRJ_NEXT, MEETS_PRJ_SUCC, REV_COST_CURR, REV_COST_NEXT, REV_COST_SUCC, LABORCOST_CURR, LABORCOST_NEXT, LABORCOST_SUCC, OTHERCOST_CURR, OTHERCOST_NEXT, OTHERCOST_SUCC, OPSCOST_CURR, OPSCOST_NEXT, OPSCOST_SUCC,CURR_EXP, NEXT_EXP, SUCC_EXP FROM HRMS_D1_QUICK_PROJ_REVENUE WHERE QUICK_PROJECT_CODE="
				+ bean.getQuickProjectCode();
		Object[][] revObj = getSqlModel().getSingleResult(revenueQuery);
		if (revObj != null && revObj.length > 0) {
			bean.setPerCurrentMonth(Utility.checkNull(String
					.valueOf(revObj[0][0])));
			bean.setPerNextMonth(Utility
					.checkNull(String.valueOf(revObj[0][1])));
			bean.setPerSuccessiveMonth(Utility.checkNull(String
					.valueOf(revObj[0][2])));
			bean.setRevCurrentMonth(Utility.checkNull(String
					.valueOf(revObj[0][3])));
			bean.setRevNextMonth(Utility
					.checkNull(String.valueOf(revObj[0][4])));
			bean.setRevSuccessiveMonth(Utility.checkNull(String
					.valueOf(revObj[0][5])));
			bean.setLaborCurrentMonth(Utility.checkNull(String
					.valueOf(revObj[0][6])));
			bean.setLaborNextMonth(Utility.checkNull(String
					.valueOf(revObj[0][7])));
			bean.setLaborSuccessiveMonth(Utility.checkNull(String
					.valueOf(revObj[0][8])));
			bean.setOthorCurrentMonth(Utility.checkNull(String
					.valueOf(revObj[0][9])));
			bean.setOthorNextMonth(Utility.checkNull(String
					.valueOf(revObj[0][10])));
			bean.setOthorSuccessiveMonth(Utility.checkNull(String
					.valueOf(revObj[0][10])));
			bean.setTotalCurrentMonth(Utility.checkNull(String
					.valueOf(revObj[0][12])));
			bean.setTotalNextMonth(Utility.checkNull(String
					.valueOf(revObj[0][13])));
			bean.setTotalSuccessiveMonth(Utility.checkNull(String
					.valueOf(revObj[0][14])));
			bean.setProfitCurrentMonth(Utility.checkNull(String
					.valueOf(revObj[0][15])));
			bean.setProfitNextMonth(Utility.checkNull(String
					.valueOf(revObj[0][16])));
			bean.setProfitSuccessiveMonth(Utility.checkNull(String
					.valueOf(revObj[0][17])));
			bean.setGrossCurrentMonth(Utility.checkNull(String
					.valueOf(revObj[0][18])));
			bean.setGrossNextMonth(Utility.checkNull(String
					.valueOf(revObj[0][19])));
			bean.setGrossSuccessiveMonth(Utility.checkNull(String
					.valueOf(revObj[0][20])));
			bean.setMeetsCurrentMonth(Utility.checkNull(String
					.valueOf(revObj[0][21])));
			bean.setMeetsNextMonth(Utility.checkNull(String
					.valueOf(revObj[0][22])));
			bean.setMeetsSuccessiveMonth(Utility.checkNull(String
					.valueOf(revObj[0][23])));
			bean
					.setRevCurrent(Utility.checkNull(String
							.valueOf(revObj[0][24])));
			bean.setRevNext(Utility.checkNull(String.valueOf(revObj[0][25])));
			bean.setRevSuccessive(Utility.checkNull(String
					.valueOf(revObj[0][26])));
			bean.setLaborCurrent(Utility.checkNull(String
					.valueOf(revObj[0][27])));
			bean.setLaborNext(Utility.checkNull(String.valueOf(revObj[0][28])));
			bean.setLaborSuccessive(Utility.checkNull(String
					.valueOf(revObj[0][29])));
			bean.setOthorCurrent(Utility.checkNull(String
					.valueOf(revObj[0][30])));
			bean.setOthorNext(Utility.checkNull(String.valueOf(revObj[0][31])));
			bean.setOthorSuccessive(Utility.checkNull(String
					.valueOf(revObj[0][32])));
			bean
					.setOpsCurrent(Utility.checkNull(String
							.valueOf(revObj[0][33])));
			bean.setOpsNext(Utility.checkNull(String.valueOf(revObj[0][34])));
			bean.setOpsSuccessive(Utility.checkNull(String
					.valueOf(revObj[0][35])));
			bean.setCurrExp(Utility.checkNull(String.valueOf(revObj[0][36])));
			bean.setNextExp(Utility.checkNull(String.valueOf(revObj[0][37])));
			bean.setSuccExp(Utility.checkNull(String.valueOf(revObj[0][38])));

		}

	}

	public String getApproverComments(QuickProjectBean bean) {

		String qq = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,PROJECT_COMMENTS,TO_CHAR(PROJECT_DATE,'DD-MM-YYYY'),DECODE(PROJECT_STATUS,'A','Approved','R','Reject','B','Send Back','P','Pending','Z','Approved','L','1st Approval ','F','Forwarded ','S','Forwarded ') FROM HRMS_D1_QUICK_PROJ_PATH "
				+ "	INNER JOIN HRMS_EMP_OFFC ON(HRMS_D1_QUICK_PROJ_PATH.PROJECT_APPROVER=HRMS_EMP_OFFC.EMP_ID) "
				+ "	WHERE HRMS_D1_QUICK_PROJ_PATH .PROJECT_CODE="
				+ bean.getQuickProjectCode() + " ORDER BY PROJECT_PATH_ID";
		Object[][] data = getSqlModel().getSingleResult(qq);
		ArrayList list = new ArrayList();
		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				QuickProjectBean bean1 = new QuickProjectBean();
				bean1.setIttApproverName(checkNull(String.valueOf(data[i][0])));
				bean1.setIttComments(checkNull(String.valueOf(data[i][1])));
				bean1.setIttApplicationDate(checkNull(String
						.valueOf(data[i][2])));
				bean1.setIttStatus(checkNull(String.valueOf(data[i][3])));
				list.add(bean1);
			}
			bean.setListComment(list);
		}
		return "";
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public boolean delete(QuickProjectBean bean) {
		String delQuery = "DELETE FROM HRMS_D1_QUICK_PROJECT WHERE PROJECT_CODE="
				+ bean.getQuickProjectCode();
		return getSqlModel().singleExecute(delQuery);
	}

	public void inputAppr(QuickProjectBean bean, HttpServletRequest request) {
		String query = "SELECT NVL(TRACKING_NO,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(COMPLETED_ON,'DD-MM-YYYY') "
				+ "	,HRMS_D1_QUICK_PROJECT.MANAGER_CODE,PROJECT_CODE FROM HRMS_D1_QUICK_PROJECT "
				+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_QUICK_PROJECT.MANAGER_CODE) ";

		String queryHR = "SELECT NVL(TRACKING_NO,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(COMPLETED_ON,'DD-MM-YYYY') "
				+ "	,HRMS_D1_QUICK_PROJECT.MANAGER_CODE,PROJECT_CODE FROM HRMS_D1_QUICK_PROJECT "
				+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_QUICK_PROJECT.MANAGER_CODE) "
				+ "  WHERE 1=1  ";

		query += " WHERE HRMS_D1_QUICK_PROJECT.NEXT_APPR_CODE="
				+ bean.getUserEmpId();

		if (bean.getFlag().equals("")) {
			bean.setHeaderName("Pending Application");
			query += " AND STATUS IN('P','F') ";
			queryHR += " AND STATUS='S' ";

		} else if (bean.getFlag().equals("AA")) {
			bean.setHeaderName("Approved Application");
			query += " AND STATUS='A' ";
			queryHR += " AND STATUS='A' ";
		} else if (bean.getFlag().equals("RR")) {
			bean.setHeaderName("Rejected Application");
			query += " AND STATUS='R' ";
			queryHR += " AND STATUS='R' ";
		}

		Object[][] data = getSqlModel().getSingleResult(query);

		/**
		 * QUERY FOR APPROVE RELECT
		 */
		String apprRejectQuery = "SELECT DISTINCT NVL(TRACKING_NO,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(COMPLETED_ON,'DD-MM-YYYY') "
				+ "	,HRMS_D1_QUICK_PROJECT.MANAGER_CODE,HRMS_D1_QUICK_PROJECT.PROJECT_CODE FROM HRMS_D1_QUICK_PROJECT "
				+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_QUICK_PROJECT.MANAGER_CODE) "
				+ "    INNER JOIN HRMS_D1_QUICK_PROJ_PATH ON(HRMS_D1_QUICK_PROJ_PATH.PROJECT_CODE=HRMS_D1_QUICK_PROJECT.PROJECT_CODE  AND PROJECT_APPROVER="
				+ bean.getUserEmpId() + ") " + "  WHERE 1=1  ";

		if (checkReporting(bean)) {
			query = query + " UNION " + queryHR;
			query += " ORDER BY 1 DESC  ";
			data = getSqlModel().getSingleResult(query);
		} else {
			if (bean.getFlag().equals("AA")) {
				apprRejectQuery += "  AND STATUS IN('A','F','S') ORDER BY HRMS_D1_QUICK_PROJECT.TRACKING_NO DESC ";
				data = getSqlModel().getSingleResult(apprRejectQuery);
			} else if (bean.getFlag().equals("RR")) {
				apprRejectQuery += "  AND STATUS IN('R') ORDER BY HRMS_D1_QUICK_PROJECT.TRACKING_NO DESC ";
				data = getSqlModel().getSingleResult(apprRejectQuery);
			}
		}

		// data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {
			String[] pageIndex = setData(bean, request, data, "totalPage",
					"pageNo", bean.getMyPage(), "");
			ArrayList list = new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
					.parseInt(String.valueOf(pageIndex[1])); i++) {
				QuickProjectBean bean1 = new QuickProjectBean();
				bean1.setIttEmployeeToken(String.valueOf(data[i][0]));
				bean1.setIttEmployee(String.valueOf(data[i][1]));
				bean1.setIttApplicationDate(String.valueOf(data[i][2]));
				bean1.setIttquickProjectCode(String.valueOf(data[i][4]));
				list.add(bean1);
				if (bean.getFlag().equals("")) {
					bean1.setButtonName("View/Approve");
				} else {
					bean1.setButtonName("View");
				}
			}
			bean.setListDraft(list);
		}

	}

	public boolean approve(QuickProjectBean bean, String status,
			HttpServletRequest request) {
		String updateQuery = "";
		/**
		 * CHECK FOR HR IT WILL GO DIRECTLY TO APPROVAL SETTING USER
		 */
		if (bean.getForwardTo().equals("HH")) {
			status = "S";
		}
		if (status.equals("F")) {
			updateQuery = "UPDATE HRMS_D1_QUICK_PROJECT SET NEXT_APPR_CODE="
					+ bean.getNextAppCode() + ",STATUS='" + status
					+ "',FORWARD_TO='" + bean.getForwardTo()
					+ "' WHERE PROJECT_CODE=" + bean.getQuickProjectCode();
		} else if (status.equals("A") || status.equals("R")) {
			updateQuery = "UPDATE HRMS_D1_QUICK_PROJECT SET NEXT_APPR_CODE="
					+ bean.getUserEmpId() + ",STATUS='" + status
					+ "',FORWARD_TO='" + bean.getForwardTo()
					+ "' WHERE PROJECT_CODE=" + bean.getQuickProjectCode();
		} else if (status.equals("B")) {
			updateQuery = "UPDATE HRMS_D1_QUICK_PROJECT SET NEXT_APPR_CODE='',STATUS='"
					+ status
					+ "',FORWARD_TO='"
					+ bean.getForwardTo()
					+ "' WHERE PROJECT_CODE=" + bean.getQuickProjectCode();
		} else {
			updateQuery = "UPDATE HRMS_D1_QUICK_PROJECT SET STATUS='" + status
					+ "' WHERE PROJECT_CODE=" + bean.getQuickProjectCode();
		}

		boolean result = getSqlModel().singleExecute(updateQuery);
		// INSERT INTO PATH
		String insQUery = "INSERT INTO HRMS_D1_QUICK_PROJ_PATH(PROJECT_CODE,PROJECT_APPROVER,PROJECT_COMMENTS,PROJECT_DATE,PROJECT_PATH_ID,PROJECT_STATUS) "
				+ "	VALUES(?,?,?,SYSDATE,(SELECT NVL(MAX(PROJECT_PATH_ID),0)+1 FROM HRMS_D1_QUICK_PROJ_PATH),?)";
		Object[][] obj = new Object[1][4];
		obj[0][0] = bean.getQuickProjectCode();
		obj[0][1] = bean.getUserEmpId();
		obj[0][2] = bean.getComments();
		obj[0][3] = status;
		if (result) {
			getSqlModel().singleExecute(insQUery, obj);
		}

		String pathQuery = "SELECT DISTINCT PROJECT_APPROVER FROM HRMS_D1_QUICK_PROJ_PATH 	 WHERE HRMS_D1_QUICK_PROJ_PATH .PROJECT_CODE ="
				+ bean.getQuickProjectCode()
				+ " AND 	  PROJECT_APPROVER NOT IN("
				+ bean.getUserEmpId()
				+ ")";
		Object[][] pathObj = getSqlModel().getSingleResult(pathQuery);

		String HRquery = "SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H')  AND APP_SETTINGS_EMP_ID IS NOT NULL AND  APP_SETTINGS_EMP_ID NOT IN("
				+ bean.getUserEmpId() + ")   ";
		Object[][] HRdata = getSqlModel().getSingleResult(HRquery);

		String HRGroupQuery = "SELECT APP_EMAIL_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H')  AND APP_EMAIL_ID IS NOT NULL  ";
		Object[][] HRGroupEmail = getSqlModel().getSingleResult(HRGroupQuery);

		try {
			if (status.equals("F")) {
				sendMailMethod(
						"D1-QUICK PROJECT FROM APPROVER TO NEXT  APPROVER",
						bean.getManagerCode(), bean.getUserEmpId(), bean
								.getQuickProjectCode(), null, null, null,
						request, bean.getNextAppCode(), null, false, true);

				sendMailMethod("D1-QUICK PROJECT FROM APPROVER TO APPLICANT",
						bean.getManagerCode(), bean.getUserEmpId(), bean
								.getQuickProjectCode(), null, null, null,
						request, bean.getManagerCode(), pathObj, false, true);

			}
			if (status.equals("S")) {
				if (HRdata != null && HRdata.length > 0) {
					sendMailMethod(
							"D1-QUICK PROJECT FROM APPROVER TO NEXT  APPROVER",
							bean.getManagerCode(), bean.getUserEmpId(), bean
									.getQuickProjectCode(), null, null, null,
							request, String.valueOf(HRdata[0][0]),
							HRGroupEmail, true, false);

				}
				sendMailMethod("D1-QUICK PROJECT FROM APPROVER TO APPLICANT",
						bean.getManagerCode(), bean.getUserEmpId(), bean
								.getQuickProjectCode(), null, null, null,
						request, bean.getManagerCode(), pathObj, false, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (status.equals("A") || status.equals("R") || status.equals("B")) {
				if (checkReporting(bean)) {
					sendMailMethod(
							"D1-QUICK PROJECT FROM APPROVER TO APPLICANT", bean
									.getManagerCode(), bean.getUserEmpId(),
							bean.getQuickProjectCode(), null, null, null,
							request, bean.getManagerCode(), HRGroupEmail, true,
							true);

				}
				sendMailMethod("D1-QUICK PROJECT FROM APPROVER TO APPLICANT",
						bean.getManagerCode(), bean.getUserEmpId(), bean
								.getQuickProjectCode(), null, null, null,
						request, bean.getManagerCode(), pathObj, false, true);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	// APPROVER TO NEXT APPROVER
	public String sendMailMethod(String tempName, String fromEmp,
			String approveCode, String applicationCode

			, String[] link_param, String[] link_label, Object[][] data,
			HttpServletRequest request, String nextAppr, Object[][] pathObj,
			boolean isGroupEmail, boolean isSendMail) throws Exception {
		try {
			Object[][] eventData = null;
			Object[][] templateData = null;
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);

			String templateQuery = "SELECT TEMPLATE_NAME "
					+ " FROM HRMS_EMAILTEMPLATE_HDR  "
					+ " WHERE HRMS_EMAILTEMPLATE_HDR.TEMPLATE_NAME = '"
					+ tempName + "'";
			templateData = model.getSqlModel().getSingleResult(templateQuery);

			String templateName = tempName.trim();
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate(templateName);

			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
																				// EMAIL
			templateQuery1.setParameter(1, approveCode);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
																				// EMAIL
			templateQuery2.setParameter(1, nextAppr);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, approveCode);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, nextAppr);

			EmailTemplateQuery templateQuery8 = template.getTemplateQuery(8);
			templateQuery8.setParameter(1, applicationCode);
			
			EmailTemplateQuery templateQuery9 = template.getTemplateQuery(9);
			templateQuery9.setParameter(1, applicationCode);

			template.configMailAlert();

			// SEND MAIL TO GROUP EMAIL
			if (isGroupEmail && pathObj != null && pathObj.length > 0) {
				template.sendApplicationMailToGroups(pathObj);
			} else {
				if (pathObj != null && pathObj.length > 0) {
					String[] toCC = new String[pathObj.length];
					for (int i = 0; i < pathObj.length; i++) {
						toCC[i] = String.valueOf(pathObj[i][0]);
					}
					try {
						template.sendApplicationMailToKeepInfo(toCC);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

			if (link_param != null && link_param.length > 0) {
				template.addOnlineLink(request, link_param, link_label);
			}

			if (isSendMail) {
				template.sendApplicationMail();
			}

			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";
	}

	/**
	 * GET LIST OF HR APPROVAL
	 */
	public boolean checkReporting(QuickProjectBean bean) {
		boolean rr = false;
		String query = "SELECT APP_SETTINGS_EMP_ID,APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H')  AND APP_SETTINGS_EMP_ID IS NOT NULL AND APP_SETTINGS_EMP_ID= "
				+ bean.getUserEmpId();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			rr = true;
		} else {
			rr = false;
		}
		return rr;
	}
}
