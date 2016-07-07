package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.ExpenseApprovalRegBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.leave.RegularizationModel;

public class ExpenseApprovalRegModel extends ModelBase {

	public boolean draft(ExpenseApprovalRegBean bean, String status,
			String[] approverCode) {
		boolean flag = false;
		String shortTermCode = "";
		
		
		
		String selQuery = "SELECT NVL(MAX(EXPENSE_CODE),0)+1 ,(NVL(MAX(EXPENSE_CODE),0)+1)||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY') FROM HRMS_D1_EXPENSE_APPR_REQ ";
		Object[][] selData = getSqlModel().getSingleResult(selQuery);
		if (bean.getExpenseApproverCode().equals("")) {			
			shortTermCode = String.valueOf(selData[0][0]);
			bean.setExpenseApproverCode(shortTermCode);			
				/**
				 * SET TRACKING NO
				 */String qq="SELECT NVL(MAX(EXPENSE_CODE),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(EXPENSE_CODE),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_EXPENSE_APPR_REQ	";
					Object[][]obj=getSqlModel().getSingleResult(qq);
					if(obj !=null && obj.length>0){			
					try {
						ApplCodeTemplateModel model = new ApplCodeTemplateModel();
						model.initiate(context, session);
						String applnCode = model.generateApplicationCode(bean.getExpenseApproverCode(), "D1-EXP", bean.getEmployeeCode(),String.valueOf(obj[0][2]));
						bean.setFileheaderName(checkNull(applnCode));
						model.terminate();
					} catch (Exception e) {
						// TODO: handle exception
					}
					}			
		}
		
		
		
		String delQuery = "DELETE FROM HRMS_D1_EXPENSE_APPR_REQ WHERE EXPENSE_CODE= "
				+ bean.getExpenseApproverCode();
		flag = getSqlModel().singleExecute(delQuery);

		Object[][] data = new Object[1][15];
		data[0][0] = bean.getExpenseApproverCode();
		data[0][1] = bean.getEmployeeCode();
		data[0][2] = bean.getBusinessPurpose();
		data[0][3] = bean.getUploadFileName();
		data[0][4] = bean.getTotalExpenseDollarAmt();
		data[0][5] = bean.getChangeApproverCode();
		data[0][6] = status;
		String approver = "";
		if (approverCode != null && approverCode.length > 0
				&& bean.getChangeApproverCode().equals("")) {
			approver = approverCode[0];
		}
		data[0][7] = approver;
		data[0][8] = bean.getPreApprovedPolicy().equals("true") ? "Y" : "N";
		data[0][9] = bean.getPreApprovedPolicyComments();
		data[0][10] = bean.getAddress();
		data[0][11] = bean.getCity();
		data[0][12] = bean.getState();
		data[0][13] = bean.getZipCode();
		data[0][14] = bean.getTelephoneNo();
		
		
		String insQuery = "INSERT INTO HRMS_D1_EXPENSE_APPR_REQ(EXPENSE_CODE, EXPENSE_EMP_ID, EXPENSE_BUSINESS_PUR, EXPENSE_FILE, TOTAL_EXPENSE_AMOUNT, EXPENSE_CHANGE_MNG, STATUS, APPLICATION_DATE,EXPENSE_APPR_CODE,PREAPPROVED_POLICY,PREAPPROVED_POLICY_COMMENTS,FILE_HEADER_NAME,COMPLETED_BY,COMPLETED_ON,EXPENSE_ADDR, EXPENSE_CITY, EXPENSE_STATE, EXPENSE_ZIP, EXPENSE_TEL_NO)"
				+ "  VALUES(?,?,?,?,?,?,?,SYSDATE,?,?,?,'"+bean.getFileheaderName()+"','"+bean.getUserEmpId()+"',SYSDATE,?,?,?,?,?) ";
		if (flag) {
			flag = getSqlModel().singleExecute(insQuery, data);
		}
		
		
		return flag;
	}

	public boolean editApplication(ExpenseApprovalRegBean bean) {
		// TODO Auto-generated method stub
		return false;
	}

	public void input(ExpenseApprovalRegBean bean, HttpServletRequest request) {

		if (bean.getFlag().equals("")) {
			bean.setHeaderName("Pending Application");
		} else if (bean.getFlag().equals("AA")) {
			bean.setHeaderName("Approved Application");
		} else if (bean.getFlag().equals("RR")) {
			bean.setHeaderName("Rejected Application");
		}

		String query = "SELECT NVL(FILE_HEADER_NAME,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') "
				+ "	,HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID,EXPENSE_CODE, NVL(EXPENSE_BUSINESS_PUR,'') FROM HRMS_D1_EXPENSE_APPR_REQ "
				+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID) ";
		// if (bean.isGeneralFlag()) {
		query += " WHERE HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID="
				+ bean.getUserEmpId();
		// }
		/*
		 * else{ query += " WHERE 1=1 "; }
		 */

		String draft = query + " AND STATUS='D' ";
		draft = draft
				+ " ORDER BY HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_CODE DESC  ";

		Object[][] data = getSqlModel().getSingleResult(draft);
		String[] pageIndex=new String[5];
		pageIndex[0] = "0";
		pageIndex[1] = "20";
		pageIndex[2] = "1";
		pageIndex[3] = "1";
		pageIndex[4] = "";
		if(data!=null){
		 pageIndex = new org.paradyne.lib.Utility().doPaging(bean
				.getMyPage(), data.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
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
				ExpenseApprovalRegBean bean1 = new ExpenseApprovalRegBean();
				bean1.setIttEmployeeToken(checkNull(String.valueOf(data[i][0])));
				bean1.setIttEmployee(checkNull(String.valueOf(data[i][1])));
				bean1.setIttApplicationDate(checkNull(String.valueOf(data[i][2])));
				bean1.setIttExpenseApproverCode(checkNull(String.valueOf(data[i][4])));
				bean1.setBusinessPurposeItr(checkNull(String.valueOf(data[i][5])));
				list.add(bean1);
				bean1.setButtonName("Edit Application");

			}
			bean.setListDraft(list);
		}

		/**
		 * IN PROGRESS
		 */
		String progress = query + " AND STATUS IN('P','F','Z')  ";
		progress = progress
				+ " ORDER BY HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_CODE DESC  ";
		Object[][] objProgress = getSqlModel().getSingleResult(progress);
		String[] pageIndexRe = setData(bean, request, objProgress,
				"totalPageP", "pageNoP", bean.getMyPage1(), "1");
		if (objProgress != null && objProgress.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexRe[0])); i < Integer
					.parseInt(String.valueOf(pageIndexRe[1])); i++) {
				ExpenseApprovalRegBean bean1 = new ExpenseApprovalRegBean();
				bean1.setIttEmployeeToken(String.valueOf(objProgress[i][0]));
				bean1.setIttEmployee(String.valueOf(objProgress[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objProgress[i][2]));
				// bean1.setIttEmployeeId(String.valueOf(objProgress[i][3]));
				bean1.setIttExpenseApproverCode(String.valueOf(objProgress[i][4]));
				bean1.setBusinessPurposeItr(checkNull(String.valueOf(objProgress[i][5])));
				list.add(bean1);
				bean1.setButtonName("View Application");
			}
			bean.setListInProgress(list);
		}

		/**
		 * APPROVED PPLICATION
		 */
		String approve = query + " AND STATUS='A'  ";
		if (bean.getFlag().equals("AA")) {
			approve = approve
					+ " ORDER BY HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_CODE DESC  ";
			Object[][] objApprove = getSqlModel().getSingleResult(approve);
			String[] pageIndexAppr = setData(bean, request, objApprove,
					"totalPage", "pageNo", bean.getMyPage(), "");
			if (objApprove != null && objApprove.length > 0) {
				ArrayList list = new ArrayList();
				for (int i = Integer.parseInt(String.valueOf(pageIndexAppr[0])); i < Integer
						.parseInt(String.valueOf(pageIndexAppr[1])); i++) {
					ExpenseApprovalRegBean bean1 = new ExpenseApprovalRegBean();
					bean1.setIttEmployeeToken(String.valueOf(objApprove[i][0]));
					bean1.setIttEmployee(String.valueOf(objApprove[i][1]));
					bean1.setIttApplicationDate(String
							.valueOf(objApprove[i][2]));
					// bean1.setIttEmployeeId(String.valueOf(objApprove[i][3]));
					bean1.setIttExpenseApproverCode(String.valueOf(objApprove[i][4]));
					bean1.setBusinessPurposeItr(checkNull(String.valueOf(objApprove[i][5])));
					list.add(bean1);
					bean1.setButtonName("View Application");
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
					+ " ORDER BY HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_CODE DESC  ";
			Object[][] objReject = getSqlModel().getSingleResult(reject);
			String[] pageIndexR = setData(bean, request, objReject,
					"totalPage", "pageNo", bean.getMyPage(), "");
			if (objReject != null && objReject.length > 0) {
				ArrayList list = new ArrayList();
				for (int i = Integer.parseInt(String.valueOf(pageIndexR[0])); i < Integer
						.parseInt(String.valueOf(pageIndexR[1])); i++) {
					ExpenseApprovalRegBean bean1 = new ExpenseApprovalRegBean();
					bean1.setIttEmployeeToken(String.valueOf(objReject[i][0]));
					bean1.setIttEmployee(String.valueOf(objReject[i][1]));
					bean1.setIttApplicationDate(String.valueOf(objReject[i][2]));
					// bean1.setIttEmployeeId(String.valueOf(objReject[i][3]));
					bean1.setIttExpenseApproverCode(String.valueOf(objReject[i][4]));
					bean1.setBusinessPurposeItr(checkNull(String.valueOf(objReject[i][5])));
					list.add(bean1);
					bean1.setButtonName("View Application");
				}
				bean.setListReject(list);
			}
		}

		/**
		 * SEND BACK APPLICATION
		 */
		String sendBack = query + " AND STATUS='B'  ";
		sendBack = sendBack
				+ " ORDER BY HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_CODE DESC  ";
		Object[][] objSendBack = getSqlModel().getSingleResult(sendBack);
		if (objSendBack != null && objSendBack.length > 0) {
			String[] pageIndexB = setData(bean, request, objSendBack,
					"totalPageB", "pageNoB", bean.getMyPage2(), "2");
			ArrayList list = new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexB[0])); i < Integer
					.parseInt(String.valueOf(pageIndexB[1])); i++) {
				ExpenseApprovalRegBean bean1 = new ExpenseApprovalRegBean();
				bean1.setIttEmployeeToken(String.valueOf(objSendBack[i][0]));
				bean1.setIttEmployee(String.valueOf(objSendBack[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objSendBack[i][2]));
				// bean1.setIttEmployeeId(String.valueOf(objSendBack[i][3]));
				bean1.setIttExpenseApproverCode(String.valueOf(objSendBack[i][4]));
				bean1.setBusinessPurposeItr(checkNull(String.valueOf(objSendBack[i][5])));
				list.add(bean1);
				bean1.setButtonName("Edit Application");
			}
			bean.setListSendBack(list);
		}
	}

	public String[] setData(ExpenseApprovalRegBean bean,
			HttpServletRequest request, Object[][] data, String totalPage,
			String pageNo, String page, String type) {
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(page,
				data.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
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

	public void setApproverData(ExpenseApprovalRegBean bean, Object[][] empFlow) {

		Object[][] approverDataObj = new Object[empFlow.length][2];
		for (int i = 0; i < empFlow.length; i++) {
			approverDataObj[i][0] = "";
			String selectQuery = "  SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') ,' '||'('||HRMS_RANK.RANK_NAME||')',HRMS_EMP_OFFC.EMP_ID "
					+ "  FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
					+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

			Object temObj[][] = getSqlModel().getSingleResult(selectQuery);
			if (temObj != null && temObj.length > 0) {
				approverDataObj[i][0] = checkNull(String.valueOf(temObj[0][0]));
				approverDataObj[i][1] = checkNull(String.valueOf(temObj[0][2]));
			}
		}
		bean.setApproverList(null);
		if (approverDataObj != null && approverDataObj.length > 0) {
			ArrayList arrList = new ArrayList();
			for (int i = 0; i < approverDataObj.length; i++) {
				ExpenseApprovalRegBean leaveBean = new ExpenseApprovalRegBean();
				leaveBean
						.setApproverName(String.valueOf(approverDataObj[i][0]));
				String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
				leaveBean.setApprSrNo(srNo);
				leaveBean
						.setApproverCode(String.valueOf(approverDataObj[i][1]));
				arrList.add(leaveBean);
			}
			bean.setCheckData(String.valueOf(arrList.size()));
			bean.setApproverList(arrList);
		}
		else{
			bean.setCheckData(String.valueOf(0));
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;

		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		} // end of if

		int tenRemainder = value % 10;

		switch (tenRemainder) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		} // end of switch
	}

	public void setEmployeeData(String empCode, ExpenseApprovalRegBean bean,
			String type) {
		String query = "      SELECT OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,EXPENSE_ADDR, EXPENSE_CITY, EXPENSE_STATE, EXPENSE_ZIP, EXPENSE_TEL_NO,OFFC.EMP_ID "
				+ "	,EXPENSE_BUSINESS_PUR,EXPENSE_FILE, TOTAL_EXPENSE_AMOUNT,OFFC1.EMP_TOKEN,OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME,OFFC1.EMP_ID,STATUS ,DECODE(PREAPPROVED_POLICY,'Y','true','false'),PREAPPROVED_POLICY_COMMENTS,FILE_HEADER_NAME " +
						" ,COMPLETED_BY.EMP_TOKEN||'-'||COMPLETED_BY.EMP_FNAME||' '||COMPLETED_BY.EMP_MNAME||' '||COMPLETED_BY.EMP_LNAME,TO_CHAR(COMPLETED_ON,'DD-MM-YYYY HH24:MI')  FROM  HRMS_D1_EXPENSE_APPR_REQ   "
				+ "  INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID)  "
				+ "   LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID=HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_CHANGE_MNG) "
				+ "	 LEFT JOIN HRMS_EMP_ADDRESS ADD1 ON(ADD1.EMP_ID=OFFC.EMP_ID AND  ADD1.ADD_TYPE='P') "
				+ "	LEFT JOIN HRMS_EMP_OFFC COMPLETED_BY ON(COMPLETED_BY.EMP_ID = HRMS_D1_EXPENSE_APPR_REQ.COMPLETED_BY)  LEFT JOIN HRMS_LOCATION ON(LOCATION_CODE=ADD1.ADD_CITY)	";
		if (type.equals("A")) {
			query += "  WHERE OFFC.EMP_ID= " + empCode + " ";
		} else {
			query += "  WHERE EXPENSE_CODE= " + empCode + " ";
		}

		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {
			bean.setEmployeeToken(checkNull(String.valueOf(data[0][0])));
			bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));
			bean.setAddress(checkNull(String.valueOf(data[0][2])));
			bean.setCity(checkNull(String.valueOf(data[0][3])));
			bean.setState(checkNull(String.valueOf(data[0][4])));
			bean.setZipCode(checkNull(String.valueOf(data[0][5])));
			bean.setTelephoneNo(checkNull(String.valueOf(data[0][6])));
			bean.setEmployeeCode(checkNull(String.valueOf(data[0][7])));
			bean.setBusinessPurpose(checkNull(String.valueOf(data[0][8])));
			bean.setUploadFileName(checkNull(String.valueOf(data[0][9])));
			bean
					.setTotalExpenseDollarAmt(checkNull(String
							.valueOf(data[0][10])));
			bean.setChangeApproverToken(checkNull(String.valueOf(data[0][11])));
			bean.setChangeApproverName(checkNull(String.valueOf(data[0][12])));
			bean.setChangeApproverCode(checkNull(String.valueOf(data[0][13])));
			bean.setStatus(checkNull(String.valueOf(data[0][14])));
			bean.setPreApprovedPolicy(checkNull(String.valueOf(data[0][15])));
			bean.setPreApprovedPolicyComments(checkNull(String
					.valueOf(data[0][16])));
			bean.setFileheaderName(checkNull(String
					.valueOf(data[0][17])));
			
			bean.setCompletedBy(checkNull(String
					.valueOf(data[0][18])));
			
			bean.setCompletedOn(checkNull(String
					.valueOf(data[0][19])));
			
		}

	}

	public void setEmployeeDataOnload(String empCode,
			ExpenseApprovalRegBean bean) {
		String query = "      SELECT OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,ADD1.ADD_1||' '||ADD1.ADD_2||' '||ADD1.ADD_3,LOCATION_NAME	,ADD_STATE,ADD_PINCODE,ADD_PH1,OFFC.EMP_ID "
				+ "  FROM  HRMS_EMP_OFFC OFFC   "
				+ "	 LEFT JOIN HRMS_EMP_ADDRESS ADD1 ON(ADD1.EMP_ID=OFFC.EMP_ID AND  ADD1.ADD_TYPE='P') "
				+ "	 LEFT JOIN HRMS_LOCATION ON(LOCATION_CODE=ADD1.ADD_CITY)	";

		query += "  WHERE OFFC.EMP_ID= " + empCode + " ";

		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {
			bean.setEmployeeToken(checkNull(String.valueOf(data[0][0])));
			bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));
			bean.setAddress(checkNull(String.valueOf(data[0][2])));
			bean.setCity(checkNull(String.valueOf(data[0][3])));
			bean.setState(checkNull(String.valueOf(data[0][4])));
			bean.setZipCode(checkNull(String.valueOf(data[0][5])));
			bean.setTelephoneNo(checkNull(String.valueOf(data[0][6])));
			bean.setEmployeeCode(checkNull(String.valueOf(data[0][7])));
			
		}

		String completeOnQuery="SELECT EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI') FROM HRMS_EMP_OFFC WHERE EMP_ID="+bean.getUserEmpId();
		Object[][]obj=getSqlModel().getSingleResult(completeOnQuery);
		if(obj !=null && obj.length>0){
			bean.setCompletedBy(String.valueOf(obj[0][0]));
			bean.setCompletedOn(String.valueOf(obj[0][1]));
		}	
		
		
	}

	public boolean delete(ExpenseApprovalRegBean bean) {
		boolean flag = false;
		String delQuery = "DELETE FROM HRMS_D1_EXPENSE_APPR_REQ WHERE EXPENSE_CODE= "
				+ bean.getExpenseApproverCode();
		String delDtl = "DELETE FROM HRMS_D1_EXPENSE_APPR_PATH WHERE EXPENSE_CODE= "
				+ bean.getExpenseApproverCode();
		flag = getSqlModel().singleExecute(delDtl);
		flag = getSqlModel().singleExecute(delQuery);
		return flag;
	}

	public void inputApprover(ExpenseApprovalRegBean bean,
			HttpServletRequest request) {
		String pass="";

		String query = "SELECT NVL(FILE_HEADER_NAME,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') "
				+ "	,HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID,EXPENSE_CODE, NVL(EXPENSE_BUSINESS_PUR,'') FROM HRMS_D1_EXPENSE_APPR_REQ "
				+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID) ";
		query += " WHERE 1=1 ";
		
		
		String queryAP = "SELECT NVL(FILE_HEADER_NAME,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') "
			+ "	,HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID,EXPENSE_CODE, NVL(EXPENSE_BUSINESS_PUR,'') FROM HRMS_D1_EXPENSE_APPR_REQ "
			+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID) ";
		queryAP += " WHERE 1=1 ";

		String draft = "";
		String draftAP = "";
		/**
		 * PENDING APPLICATION
		 */

		if (bean.getFlag().equals("")) {
			bean.setHeaderName("Pending Application");
			draft = query + " AND (STATUS='P' OR STATUS='F' ) AND (EXPENSE_APPR_CODE="
					+ bean.getUserEmpId() + " OR EXPENSE_CHANGE_MNG="
					+ bean.getUserEmpId()
					+ ") ";
		}

		/**
		 * APPROVED APPLICATION
		 */
		else if (bean.getFlag().equals("AA")) {
			bean.setHeaderName("Approved Application");
			draft = query + " AND STATUS='A' AND (EXPENSE_APPR_CODE="
					+ bean.getUserEmpId() + " OR EXPENSE_CHANGE_MNG="
					+ bean.getUserEmpId()
					+ ")  ";
		}

		/**
		 * REJECT APPLICATION
		 */
		else if (bean.getFlag().equals("RR")) {
			bean.setHeaderName("Rejected Application");
			draft = query + " AND STATUS='R' AND (EXPENSE_APPR_CODE="
					+ bean.getUserEmpId() + " OR EXPENSE_CHANGE_MNG="
					+ bean.getUserEmpId()
					+ ")  ";
		}

		boolean check=false;	
		Object[][] data = getSqlModel().getSingleResult(draft+ " ORDER BY EXPENSE_CODE DESC ");
		
		if(data !=null && data.length>0){
			check=true;
		}
		//else{
			
			
		//}
		
			/**
			 * QUERY FOR APPROVE RELECT
			 */
			String apprRejectQuery="SELECT DISTINCT NVL(FILE_HEADER_NAME,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') "
				+ "	,HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID,HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_CODE, NVL(HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_BUSINESS_PUR,'') FROM HRMS_D1_EXPENSE_APPR_REQ "
				+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_EMP_ID) "
				+"    INNER JOIN HRMS_D1_EXPENSE_APPR_PATH ON(HRMS_D1_EXPENSE_APPR_PATH.EXPENSE_CODE=HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_CODE  AND EXPENSE_APPROVER="+bean.getUserEmpId()+") "
				+" WHERE 1=1 ";

					if (bean.getFlag().equals("AA")) {
					apprRejectQuery +="  AND STATUS IN('A','F','S','Z') ORDER BY HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_CODE DESC ";
					data = getSqlModel().getSingleResult(apprRejectQuery);
					check=true;
					}
					else if (bean.getFlag().equals("RR")) {
					apprRejectQuery +="  AND STATUS IN('R') ORDER BY HRMS_D1_EXPENSE_APPR_REQ.EXPENSE_CODE DESC ";
					data = getSqlModel().getSingleResult(apprRejectQuery);
					check=true;
					}
					String empEmailGrp= checkReporting(bean.getUserEmpId());
					if(empEmailGrp!= null && empEmailGrp.length() >0 && !empEmailGrp.equals("")){
							draftAP=draft+ " UNION ";
						
						
						if (bean.getFlag().equals("")) {
							draftAP += queryAP + " AND (STATUS='Z') AND EMP_DIV IN("+empEmailGrp+") ORDER BY EXPENSE_CODE DESC ";
						}
						else if (bean.getFlag().equals("AA")) {
							draftAP += queryAP + " AND (STATUS='A') AND EMP_DIV IN("+empEmailGrp+") ORDER BY EXPENSE_CODE DESC ";
						}
						else if (bean.getFlag().equals("RR")) {
							draftAP += queryAP + " AND (STATUS='R') AND EMP_DIV IN("+empEmailGrp+") ORDER BY EXPENSE_CODE DESC ";
						}				
						data = getSqlModel().getSingleResult(draftAP);
						check=true;
						
					}
					
			
		
		if (data != null && data.length > 0 && check) {
			String[] pageIndex = setData(bean, request, data, "totalPage",
					"pageNo", bean.getMyPage(), "");
			ArrayList list = new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
					.parseInt(String.valueOf(pageIndex[1])); i++) {
				ExpenseApprovalRegBean bean1 = new ExpenseApprovalRegBean();
				bean1.setIttEmployeeToken(checkNull(String.valueOf(data[i][0])));
				bean1.setIttEmployee(checkNull(String.valueOf(data[i][1])));
				bean1.setIttApplicationDate(checkNull(String.valueOf(data[i][2])));
				bean1.setIttExpenseApproverCode(checkNull(String.valueOf(data[i][4])));
				bean1.setBusinessPurposeItr(checkNull(String.valueOf(data[i][5])));
				list.add(bean1);
				if (bean.getFlag().equals("")) {
					bean1.setButtonName("View/Approve Application");
				} else {
					bean1.setButtonName("View Application");
				}
			}
			bean.setListDraft(list);
		}

	}

	public String approve(HttpServletRequest request,String expCode, String approverCode,
			String Comments, String status, 
			String nextAppr,String empCode,String path) {
		boolean flag = false;
		String result="";
		/**
		 * CHECK DEFOULT MANAGER
		 */
		result="Application approved successfully";
		System.out.println("GetUserEmpId..........................Approver Code" +approverCode+"\n");
		String query="SELECT STATUS FROM HRMS_D1_EXPENSE_APPR_REQ WHERE EXPENSE_CODE="+expCode+" ";
		System.out.print("expCode in approve method"+expCode);
		String checkExpCode=expCode;
		Object[][]aaa=getSqlModel().getSingleResult(query);		
		if(aaa !=null && aaa.length>0){
			if(String.valueOf(aaa[0][0]).equals("A")||String.valueOf(aaa[0][0]).equals("R")||String.valueOf(aaa[0][0]).equals("B")){
				return "Application has already been processed.";
			}
		}
		if (status.equals("A")) {
			result="Application approved successfully";
		}
		else if (status.equals("R")) {
			result="Application rejected successfully";
		}
		else if (status.equals("B")) {
			result="Application send back successfully";
		}
		
		String finalApprove = status;
		if (status.equals("F")) {
			status = "F";
		}
		if (nextAppr.equals("")) {
			nextAppr = approverCode;
		}
		if(finalApprove.equals("F")){
			String updLogistic = " UPDATE HRMS_D1_EXPENSE_APPR_REQ  SET STATUS='"
				+ status + "',EXPENSE_APPR_CODE=" + nextAppr
				+ " , EXPENSE_CHANGE_MNG="+nextAppr+" WHERE 	EXPENSE_CODE  =" + expCode;
		flag = getSqlModel().singleExecute(updLogistic);
		}
		else{
			String updLogistic = " UPDATE HRMS_D1_EXPENSE_APPR_REQ  SET STATUS='"
				+ status + "' "
				+ " WHERE 	EXPENSE_CODE  =" + expCode;
		flag = getSqlModel().singleExecute(updLogistic);
		}
	

		if (flag) {
			// INSERT INTO PATH
			String qqq = "INSERT INTO HRMS_D1_EXPENSE_APPR_PATH(EXPENSE_CODE,EXPENSE_APPROVER,EXPENSE_COMMENTS,EXPENSE_DATE,EXPENSE_PATH_ID,EXPENSE_STATUS) "
					+ "	VALUES(?,?,?,SYSDATE,(SELECT NVL(MAX(EXPENSE_PATH_ID),0)+1 FROM HRMS_D1_EXPENSE_APPR_PATH),?)";
			Object[][] obj = new Object[1][4];
			obj[0][0] = expCode;	
			obj[0][1] = approverCode;
			obj[0][2] = Comments;
			obj[0][3] = status;
			flag = getSqlModel().singleExecute(qqq, obj);
			
			/**
			 * GET LIST OF PATH EMPLOYEE
			 */
			String pathQuery="SELECT DISTINCT HRMS_EMP_ADDRESS.EMP_ID,ADD_EMAIL FROM HRMS_D1_EXPENSE_APPR_PATH INNER JOIN HRMS_EMP_ADDRESS	ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_D1_EXPENSE_APPR_PATH.EXPENSE_APPROVER) " +
							"  WHERE ADD_EMAIL IS NOT NULL AND HRMS_D1_EXPENSE_APPR_PATH.EXPENSE_APPROVER NOT IN("+approverCode+") AND   EXPENSE_CODE="+expCode;
			Object[][]objPath=getSqlModel().getSingleResult(pathQuery);
			
			Object[][]FinanceEmailID=null;
			//String querySetting="SELECT APP_EMAIL_ID ,APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('F') AND APP_EMAIL_ID  IS NOT NULL  ";
			
			/*Division Added by Priyanka*/
			
			String empQuery=" SELECT  EXPENSE_EMP_ID FROM  HRMS_D1_EXPENSE_APPR_REQ  WHERE EXPENSE_CODE="+expCode;
			Object [][] empObj= getSqlModel().getSingleResult(empQuery);
			if(empObj != null && empObj.length >0){
				String empDivQuery="SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID ="+empObj[0][0];
			
				Object [][] empDivObj= getSqlModel().getSingleResult(empDivQuery);
				System.out.print("Employee ID in approve function of Model......." +empDivObj[0][0]+"\n\n");
				
				String querySetting=" SELECT APP_EMAIL_ID, APP_SETTINGS_TYPE "
									+ " FROM HRMS_D1_APPROVAL_SETTINGS "
									+ " WHERE APP_SETTINGS_TYPE='F' "
									+ " AND (','||APP_DIVISION_CODE||',' LIKE '%,"+empDivObj[0][0]+",%' OR APP_DIVISION_CODE IS NULL)  "
									+ " AND APP_EMAIL_ID IS NOT NULL";
				FinanceEmailID=getSqlModel().getSingleResult(querySetting);	
			}
			/*Division ended by Priyanka*/
			
			//Object[][]joinArray=Utility.joinArrays(objPath, data, 1, 0);
			/*
			 * check logistic approver present or not
			 */
			if(status.equals("A")||status.equals("R")||status.equals("B")){
				try {
					sendMailMethod(
							"D1-EXPENSE REQUEST FROM APPROVER TO APPLICANT",
							approverCode, empCode,
							expCode, null, null, objPath,"",request,path,null,true);
				} catch (Exception e) {
					// TODO: handle exception
				}
				Object[][] financeEmailGroup=checkEmailIds(empCode);
				if(financeEmailGroup != null && financeEmailGroup.length >0){
					try {
						sendMailMethod(
								"D1-EXPENSE REQUEST FROM APPROVER TO GROUPS EMAIL",
								approverCode, empCode,
								expCode, null, null, null,"",request,path,financeEmailGroup,false);
					} catch (Exception e) {
						        // TODO: handle exception
					}
	
				}
			}
			
			if(status.equals("Z")){
				try {
					sendMailMethod(
							"D1-EXPENSE REQUEST FROM APPROVER TO APPLICANT",
							approverCode, empCode,
							expCode, null, null, objPath,"",request,path,null,true);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					if(FinanceEmailID !=null && FinanceEmailID.length>0){
					sendMailMethod(
							"D1-EXPENSE REQUEST FROM APPROVER TO NEXT  APPROVER",
							approverCode, empCode, expCode, null, null,
							null, "0", request, path,FinanceEmailID,false);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
			if(finalApprove.equals("F")){
				try {
					
					
					try {
						
						sendMailMethod(
								"D1-EXPENSE REQUEST FROM APPROVER TO APPLICANT",
								approverCode, empCode,
								expCode, null, null, objPath,"",request,path,null,true);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					
					
					String applicationType = "ExpenseRequest";			
					String[]link_param=new String[3];
					String[]link_label=new String[3];				
					
					 link_param[0] = applicationType + "#"
						+ expCode + "#" + nextAppr + "#" + "..." + "#"
						+ "A" + "#" + " " +"#"+ empCode+"#"+path;
					
					 link_param[1] = applicationType + "#"
						+ expCode + "#" + nextAppr + "#" + "..." + "#"
						+ "R" + "#" + " " +"#"+ empCode+"#"+path;
					 link_param[2] = applicationType + "#"
						+ expCode + "#" + nextAppr + "#" + "..." + "#"
						+ "B" + "#" + " " +"#"+ empCode+"#"+path;
					
					 link_label[0]="Approve";
					 link_label[1]="Reject";
					 link_label[2]="Send Back";
					 
					sendMailMethod(
							"D1-EXPENSE REQUEST FROM APPROVER TO NEXT  APPROVER",
							approverCode, empCode,
							expCode, null, null, objPath,nextAppr,request,path,null,true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		}

		return result;
	}

	

	public String getApproverComments(ExpenseApprovalRegBean bean) {

		String qq = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EXPENSE_COMMENTS,TO_CHAR(EXPENSE_DATE,'DD-MM-YYYY'),DECODE(EXPENSE_STATUS,'A','Approved','R','Reject','B','Send Back','P','Pending','Z','Approved','L','1st Approval ','F','Forwarded ') FROM HRMS_D1_EXPENSE_APPR_PATH "
				+ "	INNER JOIN HRMS_EMP_OFFC ON(HRMS_D1_EXPENSE_APPR_PATH.EXPENSE_APPROVER=HRMS_EMP_OFFC.EMP_ID) "
				+ "	WHERE HRMS_D1_EXPENSE_APPR_PATH .EXPENSE_CODE="
				+ bean.getExpenseApproverCode() + " ORDER BY EXPENSE_PATH_ID";
		Object[][] data = getSqlModel().getSingleResult(qq);
		ArrayList list = new ArrayList();
		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				ExpenseApprovalRegBean bean1 = new ExpenseApprovalRegBean();
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
	public void setCompletedBy(ExpenseApprovalRegBean bean) {
		String query="SELECT EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI') FROM HRMS_EMP_OFFC WHERE EMP_ID="+bean.getUserEmpId();
		Object[][]obj=getSqlModel().getSingleResult(query);
		if(obj !=null && obj.length>0){
			bean.setCompletedBy(String.valueOf(obj[0][0]));
			bean.setCompletedOn(String.valueOf(obj[0][1]));
		}		
	}
	public String sendMailMethod(String tempName, String fromEmp,
			String approveCode, String applicationCode, String[] link_param, String[] link_label, Object[][] data,
			String nextAppr,HttpServletRequest request,String file,Object[][] GroupEmail,boolean isFinance) throws Exception {
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
			// if(templateData !=null && templateData.length>0){
			String templateName = tempName.trim();
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate(templateName);

			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM// EMAIL
			templateQuery1.setParameter(1, fromEmp);

			
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO// EMAIL
			
			if(nextAppr.equals("")){
				templateQuery2.setParameter(1, approveCode);	
			}
			else{
				templateQuery2.setParameter(1, nextAppr);
			}
			

			
			
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationCode);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, fromEmp);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationCode);

			
			if(!nextAppr.equals("")){
				EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
				templateQuery6.setParameter(1, nextAppr);
			}
			else{
				EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
				templateQuery6.setParameter(1, fromEmp);
			}
			
			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, applicationCode);
			
			/*
			 * EmailTemplateQuery templateQuery7 = template
			 * .getTemplateQuery(7); templateQuery7.setParameter(1,
			 * applicationCode);
			 */
			//template.configMailAlert();

			
			template.configMailAlert();
			
			/**
			 * SENT MAIL TO PATH EMPLOYEE
			 */
			String[] attachedFile = new String[1];
			attachedFile[0] = file;
			if(data !=null && data.length>0){				
				String[] toCC = new String[data.length];
				for (int i = 0; i < data.length; i++) {
					toCC[i]=String.valueOf(data[i][0]);
				}
				template.sendApplMailWithAttachmentToKeepInf(toCC, attachedFile);		
				//template.sendApplicationMailToKeepInfo(toCC);
			}			
			if (link_param != null && link_param.length > 0) {
				template.addOnlineLink(request, link_param, link_label);
			}
			
			if(GroupEmail !=null && GroupEmail.length>0){
				template.sendApplicationMailToGroups(GroupEmail,attachedFile);
			}
			
			
			if(!file.equals("")){
				if(isFinance)
				template.sendApplMailWithAttachment(attachedFile);
			}
			else{
				if(isFinance)
				template.sendApplicationMail();				
			}
			
			
			template.clearParameters();
			template.terminate();
			// }
			
			
			
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";
	}
	public Object[][] getPathEmp(String column,String tableName,String id){
		String pathQuery="SELECT NVL("+column+",0) FROM "+tableName+" WHERE EXPENSE_CODE="+id;
		Object[][]data=getSqlModel().getSingleResult(pathQuery);
		return data;
	}
	
	private String getTrackingNo(String requestId,String appl) {
		String trackingNo = "";

		if(requestId.length() < 4) {
			int remChars = 4 - requestId.length();

			for(int i = 0; i < remChars; i++) {
				requestId = "0" + requestId;
			}
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		
		String strDay = day < 10 ? "0" + day : String.valueOf(day);
		String strMonth = month < 10 ? "0" + month : String.valueOf(month);
		String strYear = String.valueOf(year);

		trackingNo = appl + strYear + strMonth + strDay + "-" + requestId;
		
		return trackingNo;
	}
	
	public String checkReporting(String loginCode){
		String result="";
		String query="SELECT APP_EMP_GROUP FROM  HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_EMP_ID="+loginCode+" AND APP_SETTINGS_TYPE='F'"; 
		
		Object[][] resObj=getSqlModel().getSingleResult(query);
		if(resObj!=null && resObj.length>0)
			result=String.valueOf(resObj[0][0]);
		return result;
	}
	private Object[][] checkEmailIds(String applicantCode) {
		// TODO Auto-generated method stub
		Object[][] resObj=null;
		String query=" SELECT APP_EMAIL_ID  "
		+" FROM HRMS_D1_APPROVAL_SETTINGS " 
		+" WHERE  APP_SETTINGS_TYPE IN('F')" 
		+" AND (','||APP_DIVISION_CODE||','LIKE '%,'||(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID ="+applicantCode+")||',%' OR APP_DIVISION_CODE IS NULL)"; 
		
		 resObj=getSqlModel().getSingleResult(query);
		
		return resObj;

	}

}