package org.paradyne.model.D1;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.InformationSystemChangeRequestForm;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;


public class InformationSystemChangeRequestModel extends ModelBase {

	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InformationSystemChangeRequestModel.class);

	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}


	public void getEmployeeDetails(String empId, InformationSystemChangeRequestForm bean) {
		Object[] beanObj = null;
		try {
			beanObj = new Object[1];
			beanObj[0] = empId;

			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	HRMS_D1_DEPARTMENT.DEPT_NUMBER, NVL(HRMS_DEPT.DEPT_NAME, ' '),HRMS_EMP_OFFC.EMP_RANK,RANK_NAME,HRMS_EMP_ADDRESS.ADD_PH1,HRMS_EMP_OFFC.EMP_ID , "
				+ "	ADD_CITY, CITY.LOCATION_NAME AS CITY,ADD_STATE,  ADD_FAX, ADD_EMAIL, ADD_PINCODE,EMP_REGION_ID,REGION_NAME FROM HRMS_EMP_OFFC " 
				+ " LEFT JOIN HRMS_DEPT ON (EMP_DEPT = HRMS_DEPT.DEPT_ID) "
				+ " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID) " 
				+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_EMP_OFFC.EMP_ID)"
				+ " LEFT JOIN hrms_d1_region ON(hrms_d1_region.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID)"
				+ "LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID=?";

			Object[][] data = getSqlModel().getSingleResult(query, beanObj);

		//	bean.setEmployeeToken(String.valueOf(data[0][0]));// employee
			// token
//
		//	bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));// employee
			// name
		//	bean.setDeptNumber(checkNull(String.valueOf(data[0][2])));// Dept Number
		//	bean.setDeptName(checkNull(String.valueOf(data[0][3])));// department
		//	bean.setDesignation(checkNull(String.valueOf(data[0][5])));// designation
		//	bean.setPhoneNumber(checkNull(String.valueOf(data[0][4])));
			//bean.setCityName(checkNull(String.valueOf(data[0][7])));
			//bean.setStateName(checkNull(String.valueOf(data[0][8])));
		//	bean.setFaxNumber(checkNull(String.valueOf(data[0][9])));
		//	bean.setEmailAddress(checkNull(String.valueOf(data[0][10])));
		//	bean.setStateZip(checkNull(String.valueOf(data[0][11])));
		//	bean.setLocation(checkNull(String.valueOf(data[0][13])));
			bean.setEmployeeCode(checkNull(String.valueOf(data[0][7])));// employee
			// id
			String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			if(dateObj!=null && dateObj.length >0){
				bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
			}
			
			String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
			+ "	INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
			+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + empId;
			
			Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
			if(initiatorObj!=null && initiatorObj.length >0){
				bean.setInitiatorCode(bean.getUserEmpId());
				bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
				bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
			}
			// end of for loop
		} catch(Exception e) {
			logger.info("Exception in getEmployeeDetails------------" + e);
		}

	}

	/**
	 * @Method getOrdinalFor
	 * @Purpose to append the ordinal with serial number
	 * @param value value to which ordinal will be appended
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;

		if(hundredRemainder >= 10 && hundredRemainder <= 20) { return "th"; } // end of if

		int tenRemainder = value % 10;

		switch(tenRemainder) {
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

	
	
	

	public boolean isCurrentUser(String userId) {
		String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID=" + userId;
		Object[][] currentUserObj = getSqlModel().getSingleResult(currentUserSql);
		if(currentUserObj != null && currentUserObj.length > 0) { return true; }
		return false;
	}

	

	public boolean sendForApprovalFunction(InformationSystemChangeRequestForm bean,HttpServletRequest request) {
		boolean result = false;
		if(bean.getHiddenCode().equals(""))
		{
			result = draftFunction(bean, request);
		}else
		{
			result = updateRecords(bean, request);
			try {
				String changeStatusQuery = "UPDATE HRMS_D1_INF_CNG_REQ SET APPL_STATUS = 'P' WHERE INF_CNG_CODE  = "+bean.getHiddenCode();
				result =  getSqlModel().singleExecute(changeStatusQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	

	public boolean updateRecords(InformationSystemChangeRequestForm bean,HttpServletRequest request) {
		boolean result = false;
		try {

			Object updateObj[][] = new Object[1][19];
			updateObj[0][0] = bean.getChangeTitle();
			updateObj[0][1] = bean.getChangeSchedularOccur();
			updateObj[0][2] = bean.getChangeCategory();
			updateObj[0][3] = bean.getReason();
			updateObj[0][4] = bean.getWhatChange();
			updateObj[0][5] = bean.getImpactChange();
			updateObj[0][6] = bean.getRiskAssociatedChange();
			updateObj[0][7] = bean.getExpectResult();
			updateObj[0][8] = bean.getCurrentStatusChange();
			updateObj[0][9] = bean.getUploadFileName();
			updateObj[0][10] = bean.getUploadOptionalFileName();
			updateObj[0][11] = bean.getBackoutPlanEstimate();
			updateObj[0][12] = bean.getWhoPerformChangeTesting();
			updateObj[0][13] = bean.getHowChangeTested();
			updateObj[0][14] = bean.getUpdateOptional();
			
			if (bean.getApproverCode().trim().equals("") || bean.getApproverCode().equals("null") || bean.getApproverCode().equals(null)) {
				updateObj[0][15] = bean.getFirstApproverCode();
			} else {
				updateObj[0][15] = bean.getApproverCode();
			}
			
			
			updateObj[0][16] = bean.getStatus();
			updateObj[0][17] = bean.getOptionalProjectPlan();
			updateObj[0][18] = bean.getApproverCode();
			
			String insertQuery = "UPDATE HRMS_D1_INF_CNG_REQ SET "
				+ " CNG_TITLE = ? , CNG_DATE = TO_DATE(?,'DD-MM-YYYY'), CNG_CATEGORY = ? , REASON_CNG = ? , WHT_BEING_CNG = ? , IMPACT_CNG = ? , " +
						"RISK_ASS_CNG = ? , EXPECTED_RESULT = ? , CURRENT_STATUS = ? , DTLS_PLAN = ? , OPTINAL_PRJ_PLAN = ? , BACKOUT_PLAN = ? , WHO_WILL_PERF = ? , HOW_WILL_CNG = ? , WHO_WILL_UPDATE = ? " +
					"   , APPROVER_CODE = ? , APPL_STATUS = ? ,OPTINAL_PRJ_PLAN_DTL = ?, CHG_BY_MANG_CODE = ? WHERE INF_CNG_CODE = " + bean.getHiddenCode() ;
			

			result = getSqlModel().singleExecute(insertQuery, updateObj);

			for(int i = 0; i < updateObj.length; i++) {
				for(int j = 0; j < updateObj[i].length; j++) {
					logger.info("updateObj[" + i + "][" + j + "]  " + updateObj[i][j]);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public void setApproverData(InformationSystemChangeRequestForm bean, Object[][] empFlow) {
		try {
			if(empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][1];
				for(int i = 0; i < empFlow.length; i++) {

					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
						+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' " + "  FROM HRMS_EMP_OFFC "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)" + " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					Object temObj[][] = getSqlModel().getSingleResult(selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if(approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for(int i = 0; i < approverDataObj.length; i++) {
						InformationSystemChangeRequestForm bean1 = new InformationSystemChangeRequestForm();
						bean1.setApproverName(String.valueOf(approverDataObj[i][0]));
						// String srNo = i + 1 + getOrdinalFor(i + 1)
						// + "-Approver";
						// bean1.setSrNoIterator(srNo);
						arrList.add(bean1);
					}
					bean.setApproverList(arrList);
				}

			}
		} catch(Exception e) {
			logger.error("Exception in setApproverData------" + e);
		}

	}

	

	public void getSysDate(InformationSystemChangeRequestForm bean) {
		String sysDateSql = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object data[][] = getSqlModel().getSingleResult(sysDateSql);

		if (data != null && data.length > 0) {
			bean.setApplDate(String.valueOf(data[0][0]));// Set sys_date as
			// application date
		}
		
	}

	public boolean draftFunction(InformationSystemChangeRequestForm bean,HttpServletRequest request) {
		boolean result = false;
		System.out.println("Status : "+bean.getStatus());
		try {
			Object addObj[][] = new Object[1][21];
			addObj[0][0] = bean.getChangeTitle();
			addObj[0][1] = bean.getChangeSchedularOccur();
			addObj[0][2] = bean.getChangeCategory();
			addObj[0][3] = bean.getReason();
			addObj[0][4] = bean.getWhatChange();
			addObj[0][5] = bean.getImpactChange();
			addObj[0][6] = bean.getRiskAssociatedChange();
			addObj[0][7] = bean.getExpectResult();
			addObj[0][8] = bean.getCurrentStatusChange();
			addObj[0][9] = bean.getUploadFileName();
			addObj[0][10] = bean.getUploadOptionalFileName();
			addObj[0][11] = bean.getBackoutPlanEstimate();
			addObj[0][12] = bean.getWhoPerformChangeTesting();
			addObj[0][13] = bean.getHowChangeTested();
			addObj[0][14] = bean.getUpdateOptional();
			
			/*String approverCode = "0";
			if (!bean.getApproverCode().equals("")) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}*/
			if (bean.getApproverCode().trim().equals("") || bean.getApproverCode().equals("null") || bean.getApproverCode().equals(null)) {
				addObj[0][15] = bean.getFirstApproverCode();
			} else {
				addObj[0][15] = bean.getApproverCode();
			}
			
			addObj[0][16] = bean.getStatus();
			
			
			String autoCode = "";
			String selQuery = "SELECT NVL(MAX(INF_CNG_CODE),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(INF_CNG_CODE),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_INF_CNG_REQ	";
			Object[][] selData = getSqlModel().getSingleResult(selQuery);
			if (bean.getHiddenCode().equals("")) {			
				autoCode = String.valueOf(selData[0][0]);
				System.out.println("autoCode==="+ autoCode);
				bean.setHiddenCode(autoCode);			
				/**
					 * SET TRACKING NO
					 */String qq="SELECT NVL(MAX(INF_CNG_CODE),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(INF_CNG_CODE),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_INF_CNG_REQ	";
						Object[][]obj=getSqlModel().getSingleResult(qq);
						if(obj !=null && obj.length>0){			
						try {
							ApplCodeTemplateModel model = new ApplCodeTemplateModel();
							model.initiate(context, session);
							String applnCode = model.generateApplicationCode(String.valueOf(obj[0][1]), "D1-ISCR", bean.getUserEmpId(),String.valueOf(obj[0][2]));
							bean.setTrackingNo(checkNull(applnCode));
							System.out.println("applnCode%%%%%" + applnCode);
							model.terminate();
						} catch (Exception e) {
							// TODO: handle exception
						}
						}			
			}
			
			addObj[0][17] = bean.getTrackingNo();
		//	addObj[0][17] = bean.getApplDate();
			addObj[0][18] = bean.getUserEmpId();
			addObj[0][19] = bean.getOptionalProjectPlan();
			addObj[0][20] = bean.getApproverCode();
			String insertQuery = "INSERT INTO HRMS_D1_INF_CNG_REQ"
				+ "(INF_CNG_CODE, CNG_TITLE, CNG_DATE, CNG_CATEGORY, REASON_CNG, WHT_BEING_CNG, IMPACT_CNG, " +
						" RISK_ASS_CNG, EXPECTED_RESULT, CURRENT_STATUS, DTLS_PLAN," +
						" OPTINAL_PRJ_PLAN, BACKOUT_PLAN, WHO_WILL_PERF, HOW_WILL_CNG, WHO_WILL_UPDATE" +
						", APPROVER_CODE,APPL_STATUS,TRACKING_NUMBER, CREATED_BY,CREATED_ON,OPTINAL_PRJ_PLAN_DTL,CHG_BY_MANG_CODE) "
				+ " VALUES((SELECT NVL(MAX(INF_CNG_CODE),0)+1 FROM HRMS_D1_INF_CNG_REQ),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";

			result = getSqlModel().singleExecute(insertQuery, addObj);
			if (result) {
				String autoCodeQuery = " SELECT NVL(MAX(INF_CNG_CODE),0) FROM HRMS_D1_INF_CNG_REQ ";
				Object[][] data = getSqlModel().getSingleResult(
						autoCodeQuery);
				if (data != null && data.length > 0) {
					bean.setHiddenCode(String.valueOf(data[0][0]));
				}
			}
			
			for(int i = 0; i < addObj.length; i++) {
				for(int j = 0; j < addObj[i].length; j++) {
					logger.info("addObj[" + i + "][" + j + "]  " + addObj[i][j]);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void getPendingList(InformationSystemChangeRequestForm bean,
			HttpServletRequest request, String userId) {
		try {
			System.out.println("fgdfgfgdg");
			Object[][] draftListData = null;
			ArrayList draftvoucherList = new ArrayList();
			
			Object[][] inProcessListData = null;
			ArrayList inProcessVoucherList = new ArrayList();
			
			Object[][] sentBackListData = null;
			ArrayList sentBackVoucherList = new ArrayList();
			
			// For drafted application Begins
			
			String selQuery = " SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
						     +" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
						     +"	TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY')  ,"
						     +"	INF_CNG_CODE , APPL_STATUS, NVL(CNG_TITLE,'')"
						     +"	FROM HRMS_D1_INF_CNG_REQ"
						     +"	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
						     +"	WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  = 'D' AND HRMS_D1_INF_CNG_REQ.CREATED_BY = " +userId 
						     +"	ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
			draftListData = getSqlModel().getSingleResult(selQuery);
			
			String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(),
					draftListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}
			
			request.setAttribute("totalDraftPage", Integer.parseInt(String
					.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String
					.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals("1"))
				bean.setMyPage("1");
			
			if (draftListData != null && draftListData.length > 0) {
				bean.setDraftVoucherListLength(true);
				System.out.println("Flag : "+bean.isDraftVoucherListLength());
				System.out.println("draftListData.length : "+draftListData.length);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
				.parseInt(pageIndexDrafted[1]); i++) {
					System.out.println("Fo loop : "+i);
					InformationSystemChangeRequestForm beanItt = new InformationSystemChangeRequestForm();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(draftListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(draftListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(draftListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(draftListData[i][4])));
					beanItt.setInfoSysReqId(checkNull(String
							.valueOf(draftListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(draftListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(draftListData[i][7])));
					draftvoucherList.add(beanItt);
				}
				bean.setDraftVoucherIteratorList(draftvoucherList);
			}
			// For drafted application Ends
			
			// For in-Process application Begins
			String inProcessQuery = "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
								 +" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
								 +" TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY')  ,"
								 +" INF_CNG_CODE , APPL_STATUS, NVL(CNG_TITLE,'') , " 
								 +" CASE WHEN APPL_STATUS IN('P','Z') THEN TO_CHAR(APPROVER.EMP_FNAME||' '||APPROVER.EMP_LNAME) "   
								 +" ELSE TO_CHAR('IT Group') END AS PENDING_WITH " 
								 +" FROM HRMS_D1_INF_CNG_REQ "
								 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY) " 
								 +" INNER JOIN HRMS_EMP_OFFC APPROVER ON(APPROVER.EMP_ID = HRMS_D1_INF_CNG_REQ.APPROVER_CODE) "
								 +" WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  IN ('P','F','A','C','Q','Z','Y') AND HRMS_D1_INF_CNG_REQ.CREATED_BY = " +userId 
								 +"	ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
			
			inProcessListData = getSqlModel().getSingleResult(inProcessQuery);
			
			String[] pageIndexInProcess = Utility.doPaging(bean.getMyPageInProcess(),
					inProcessListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexInProcess[0] = "0";
				pageIndexInProcess[1] = "20";
				pageIndexInProcess[2] = "1";
				pageIndexInProcess[3] = "1";
				pageIndexInProcess[4] = "";
			}
			
			request.setAttribute("totalInProcessPage", Integer.parseInt(String
					.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String
					.valueOf(pageIndexInProcess[3])));
			if (pageIndexInProcess[4].equals("1"))
				bean.setMyPageInProcess("1");
			
			if (inProcessListData != null && inProcessListData.length > 0) {
				bean.setInProcessVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer
				.parseInt(pageIndexInProcess[1]); i++) {
					InformationSystemChangeRequestForm beanItt = new InformationSystemChangeRequestForm();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(inProcessListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(inProcessListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(inProcessListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(inProcessListData[i][4])));
					beanItt.setInfoSysReqId(checkNull(String
							.valueOf(inProcessListData[i][5])));
					/*beanItt.setStatus(checkNull(String
							.valueOf(inProcessListData[i][6])));*/
					beanItt.setStatus("false");
					if(String.valueOf(inProcessListData[i][6]).equals("A") || String.valueOf(inProcessListData[i][6]).equals("C"))
					{
						beanItt.setStatus("true");
					}
					beanItt.setChangeTitleItr(checkNull(String.valueOf(inProcessListData[i][7])));
					beanItt.setPendingWithNameItr(checkNull(String.valueOf(inProcessListData[i][8])));
					inProcessVoucherList.add(beanItt);
				}
				bean.setInProcessVoucherIteratorList(inProcessVoucherList);
			}
			// For in-Process application Ends
			
			
			// Sent-Back application Begins
			String sentBackQuery = "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
								  +" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
								  +" TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY')  ,"
								  +" INF_CNG_CODE , APPL_STATUS, NVL(CNG_TITLE,'')"
								  +" FROM HRMS_D1_INF_CNG_REQ"
								  +" LEFT JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
								  +" WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  = 'B' AND HRMS_D1_INF_CNG_REQ.CREATED_BY = " +userId 
								  +" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
			
			sentBackListData = getSqlModel().getSingleResult(sentBackQuery);
			
			String[] pageIndexSentBack = Utility.doPaging(bean.getMyPageSentBack(),
					sentBackListData.length, 20);
			if (pageIndexSentBack == null) {
				pageIndexSentBack[0] = "0";
				pageIndexSentBack[1] = "20";
				pageIndexSentBack[2] = "1";
				pageIndexSentBack[3] = "1";
				pageIndexSentBack[4] = "";
			}
			
			request.setAttribute("totalSentBackPage", Integer.parseInt(String
					.valueOf(pageIndexSentBack[2])));
			request.setAttribute("sentBackPageNo", Integer.parseInt(String
					.valueOf(pageIndexSentBack[3])));
			if (pageIndexSentBack[4].equals("1"))
				bean.setMyPageSentBack("1");
			
			if (sentBackListData != null && sentBackListData.length > 0) {
				bean.setSentBackVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer
				.parseInt(pageIndexSentBack[1]); i++) {
					InformationSystemChangeRequestForm beanItt = new InformationSystemChangeRequestForm();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(sentBackListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(sentBackListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(sentBackListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(sentBackListData[i][4])));
					beanItt.setInfoSysReqId(checkNull(String
							.valueOf(sentBackListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(sentBackListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(sentBackListData[i][7])));
					
					sentBackVoucherList.add(beanItt);
				}
				bean.setSentBackVoucherIteratorList(sentBackVoucherList);
			}
			// Sent-Back application Ends
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public void editApplicationFunction(InformationSystemChangeRequestForm reqbean,
			String requestID) {
		try {
			
			
			String editQuery = "SELECT INF_CNG_CODE, CNG_TITLE, TO_CHAR(CNG_DATE,'DD-MM-YYYY'), CNG_CATEGORY, "
							+ " REASON_CNG, WHT_BEING_CNG, IMPACT_CNG, RISK_ASS_CNG, EXPECTED_RESULT, "
							+ " CURRENT_STATUS, DTLS_PLAN, OPTINAL_PRJ_PLAN, BACKOUT_PLAN, WHO_WILL_PERF, "
							+ " HOW_WILL_CNG, WHO_WILL_UPDATE,  "
							+ " APPL_STATUS, CREATED_BY"
							+ " ,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME,"
							+ "  TO_CHAR(CREATED_ON,'DD-MM-YYYY HH24:MI'), TRACKING_NUMBER, CHG_BY_MANG_CODE ,"
							+ "  OFFC2.EMP_TOKEN AS APPROVER_TOKEN,  OFFC2.EMP_FNAME || ' ' || OFFC2.EMP_MNAME || ' ' || OFFC2.EMP_LNAME AS MANAGER_NAME"
							+ " ,TO_CHAR(CREATED_ON,'DD-MM-YYYY'),OPTINAL_PRJ_PLAN_DTL  FROM HRMS_D1_INF_CNG_REQ"
							+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
							+ " LEFT JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID = HRMS_D1_INF_CNG_REQ.CHG_BY_MANG_CODE)"
							+ " WHERE INF_CNG_CODE  ="+requestID;
								
							  // +" WHERE VOUCHER_REQUEST_ID = "+requestID;
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if(editObj!=null && editObj.length>0)
			{
				reqbean.setHiddenCode(checkNull(String.valueOf(editObj[0][0])));
				reqbean.setChangeTitle(checkNull(String.valueOf(editObj[0][1])));
				reqbean.setChangeSchedularOccur(checkNull(String.valueOf(editObj[0][2])));
				reqbean.setChangeCategory(checkNull(String.valueOf(editObj[0][3])));
				reqbean.setReason(checkNull(String.valueOf(editObj[0][4])));
				reqbean.setWhatChange(checkNull(String.valueOf(editObj[0][5])));
				reqbean.setImpactChange(checkNull(String.valueOf(editObj[0][6])));
				reqbean.setRiskAssociatedChange(checkNull(String.valueOf(editObj[0][7])));
				reqbean.setExpectResult(checkNull(String.valueOf(editObj[0][8])));
				reqbean.setCurrentStatusChange(checkNull(String.valueOf(editObj[0][9])));
				reqbean.setUploadFileName(checkNull(String.valueOf(editObj[0][10])));
				reqbean.setUploadOptionalFileName(checkNull(String.valueOf(editObj[0][11])));
				
			
				reqbean.setBackoutPlanEstimate(checkNull(String.valueOf(editObj[0][12])));
				reqbean.setWhoPerformChangeTesting(checkNull(String.valueOf(editObj[0][13])));
				reqbean.setHowChangeTested(checkNull(String.valueOf(editObj[0][14])));
				reqbean.setUpdateOptional(checkNull(String.valueOf(editObj[0][15])));
				
				reqbean.setStatus(checkNull(String.valueOf(editObj[0][16])));
				
				reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][17])));
				reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][18])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][19])));
				reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][20])));
				
				
				reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][21])));
				reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][22])));
				reqbean.setSelectapproverName(checkNull(String.valueOf(editObj[0][23])));
				
				/*reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][21])));
				reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][22])));
				reqbean.setApproverName(checkNull(String.valueOf(editObj[0][23])));*/
				
			/*	Object[][] empFlow = null;
				try {
					ReportingModel reporting = new ReportingModel();
					reporting.initiate(context, session);
					empFlow = reporting.generateEmpFlow(reqbean.getEmployeeCode(), "D1", 1);
					reporting.terminate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(empFlow!=null && empFlow.length>0)
				{
					
					String setApprover = String.valueOf(empFlow[0][0]);
						// Approver Section Begins
						if(!checkNull(String.valueOf(editObj[0][21])).equals(setApprover))
						{
							reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][21])));
							reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][22])));
							reqbean.setSelectapproverName(checkNull(String.valueOf(editObj[0][23])));
						}
					
					// Approver Section Ends
				}else{
					
					reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][21])));
					reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][22])));
					reqbean.setSelectapproverName(checkNull(String.valueOf(editObj[0][23])));
				}
				*/
				
				reqbean.setApplDate(checkNull(String.valueOf(editObj[0][24])));
				reqbean.setOptionalProjectPlan(checkNull(String.valueOf(editObj[0][25])));
				
				for(int i = 0; i < editObj.length; i++) {
					for(int j = 0; j < editObj[i].length; j++) {
						logger.info("addObj[" + i + "][" + j + "]  " + editObj[i][j]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void viewApplicationFunction(InformationSystemChangeRequestForm reqbean,
			String requestID) {
try {
	
	String editQuery =  "SELECT INF_CNG_CODE, CNG_TITLE, TO_CHAR(CNG_DATE,'DD-MM-YYYY'), CNG_CATEGORY, "
				+ " REASON_CNG, WHT_BEING_CNG, IMPACT_CNG, RISK_ASS_CNG, EXPECTED_RESULT, "
				+ " CURRENT_STATUS, DTLS_PLAN, OPTINAL_PRJ_PLAN, BACKOUT_PLAN, WHO_WILL_PERF, "
				+ " HOW_WILL_CNG, WHO_WILL_UPDATE,  "
				+ " APPL_STATUS, CREATED_BY"
				+ " ,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME,"
				+ "  TO_CHAR(CREATED_ON,'DD-MM-YYYY HH24:MI'), TRACKING_NUMBER, CHG_BY_MANG_CODE ,"
				+ "  OFFC2.EMP_TOKEN AS APPROVER_TOKEN,  OFFC2.EMP_FNAME || ' ' || OFFC2.EMP_MNAME || ' ' || OFFC2.EMP_LNAME AS MANAGER_NAME"
				+ " ,TO_CHAR(CREATED_ON,'DD-MM-YYYY'),DESC_CNG, IDENTIFY_IMPROVEMENT, COMMENTS , OPTINAL_PRJ_PLAN_DTL FROM HRMS_D1_INF_CNG_REQ"
				// + " OFFC2.EMP_TOKEN AS APPROVER_TOKEN,  OFFC2.EMP_FNAME || ' ' || OFFC2.EMP_MNAME || ' ' || OFFC2.EMP_LNAME AS MANAGER_NAME	 FROM HRMS_D1_INF_CNG_REQ"
				+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
				//+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_INF_CNG_REQ.APPROVER_CODE)"
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID = HRMS_D1_INF_CNG_REQ.CHG_BY_MANG_CODE) "
				+ " WHERE INF_CNG_CODE  ="+requestID;
								
							  // +" WHERE VOUCHER_REQUEST_ID = "+requestID;
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if(editObj!=null && editObj.length>0)
			{
				reqbean.setHiddenCode(checkNull(String.valueOf(editObj[0][0])));
				reqbean.setChangeTitle(checkNull(String.valueOf(editObj[0][1])));
				reqbean.setChangeSchedularOccur(checkNull(String.valueOf(editObj[0][2])));
				reqbean.setChangeCategory(checkNull(String.valueOf(editObj[0][3])));
				reqbean.setReason(checkNull(String.valueOf(editObj[0][4])));
				reqbean.setWhatChange(checkNull(String.valueOf(editObj[0][5])));
				reqbean.setImpactChange(checkNull(String.valueOf(editObj[0][6])));
				reqbean.setRiskAssociatedChange(checkNull(String.valueOf(editObj[0][7])));
				reqbean.setExpectResult(checkNull(String.valueOf(editObj[0][8])));
				reqbean.setCurrentStatusChange(checkNull(String.valueOf(editObj[0][9])));
				reqbean.setUploadFileName(checkNull(String.valueOf(editObj[0][10])));
				reqbean.setUploadOptionalFileName(checkNull(String.valueOf(editObj[0][11])));
				
				reqbean.setBackoutPlanEstimate(checkNull(String.valueOf(editObj[0][12])));
				reqbean.setWhoPerformChangeTesting(checkNull(String.valueOf(editObj[0][13])));
				reqbean.setHowChangeTested(checkNull(String.valueOf(editObj[0][14])));
				reqbean.setUpdateOptional(checkNull(String.valueOf(editObj[0][15])));
				
				reqbean.setStatus(checkNull(String.valueOf(editObj[0][16])));
				
				reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][17])));
				reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][18])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][19])));
				reqbean.setApplDate(checkNull(String.valueOf(editObj[0][19])));
				reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][20])));
				
				reqbean.setDescribeChange(checkNull(String.valueOf(editObj[0][25])));
				reqbean.setIdentifyImprovement(checkNull(String.valueOf(editObj[0][26])));
				reqbean.setComments(checkNull(String.valueOf(editObj[0][27])));
				reqbean.setOptionalProjectPlan(checkNull(String.valueOf(editObj[0][28])));
				
				reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][21])));
				reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][22])));
				reqbean.setSelectapproverName(checkNull(String.valueOf(editObj[0][23])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getApprovedList(InformationSystemChangeRequestForm bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();

			Object[][] approvedCancellationListData = null;
			ArrayList approvedCancellationList = new ArrayList();

			// Approved application Begins
					
			String selQuery =  "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
							  +" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
							  +" TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY')  ,"
							  +" INF_CNG_CODE , APPL_STATUS, NVL(CNG_TITLE,'')"
							  +" FROM HRMS_D1_INF_CNG_REQ"
							  +" LEFT JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
							  +" WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  IN ('A') AND HRMS_D1_INF_CNG_REQ.CREATED_BY =" +userId 
							  +" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
			
			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(bean
					.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				bean.setMyPageApproved("1");

			if (approvedListData != null && approvedListData.length > 0) {
				bean.setApprovedApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					InformationSystemChangeRequestForm beanItt = new InformationSystemChangeRequestForm();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(approvedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(approvedListData[i][3])));
					
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(approvedListData[i][4])));
					beanItt.setInfoSysReqId(checkNull(String
							.valueOf(approvedListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(approvedListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(approvedListData[i][7])));
					approvedList.add(beanItt);
				}
				bean.setApprovedApplicationIteratorList(approvedList);
			}
			// Approved application Ends

			// Approved cancellation application Begins
			
			
			String approvedcancellationQuery =  "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
											   +" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
											   +" TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY')  ,"
											   +" INF_CNG_CODE , APPL_STATUS, NVL(CNG_TITLE,'')"
											   +" FROM HRMS_D1_INF_CNG_REQ"
											   +" LEFT JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
											   +" WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  = 'X' AND HRMS_D1_INF_CNG_REQ.CREATED_BY =" +userId 
											   +" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
				
			approvedCancellationListData = getSqlModel().getSingleResult(
					approvedcancellationQuery);

			String[] pageIndexApprovedCancel = Utility.doPaging(bean
					.getMyPageApprovedCancel(),
					approvedCancellationListData.length, 20);
			if (pageIndexApprovedCancel == null) {
				pageIndexApprovedCancel[0] = "0";
				pageIndexApprovedCancel[1] = "20";
				pageIndexApprovedCancel[2] = "1";
				pageIndexApprovedCancel[3] = "1";
				pageIndexApprovedCancel[4] = "";
			}

			request.setAttribute("totalApprovedCancellationPage", Integer
					.parseInt(String.valueOf(pageIndexApprovedCancel[2])));
			request.setAttribute("approvedCancellationPageNo", Integer
					.parseInt(String.valueOf(pageIndexApprovedCancel[3])));
			if (pageIndexApprovedCancel[4].equals("1"))
				bean.setMyPageApprovedCancel("1");

			if (approvedCancellationListData != null
					&& approvedCancellationListData.length > 0) {
				bean.setApprovedCancellationApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer
						.parseInt(pageIndexApprovedCancel[1]); i++) {
					InformationSystemChangeRequestForm beanItt = new InformationSystemChangeRequestForm();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedCancellationListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(approvedCancellationListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(approvedCancellationListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(approvedCancellationListData[i][4])));
					beanItt.setInfoSysReqId(checkNull(String
							.valueOf(approvedCancellationListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(approvedCancellationListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(approvedCancellationListData[i][7])));
					approvedCancellationList.add(beanItt);
				}
				bean
						.setApprovedCancellationApplicationIteratorList(approvedCancellationList);
			}
			// Approved cancellation application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRejectedList(InformationSystemChangeRequestForm bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			Object[][] rejectedCancellationListData = null;
			ArrayList rejectedCancellationList = new ArrayList();
			// Rejected application Begins
					
			String selQuery =  "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
							  +" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
							  +" TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY')  ,"
							  +" INF_CNG_CODE , APPL_STATUS, NVL(CNG_TITLE,'')"
							  +" FROM HRMS_D1_INF_CNG_REQ"
							  +" LEFT JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
							  +" WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  = 'R' AND HRMS_D1_INF_CNG_REQ.CREATED_BY =" +userId 
							  +" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
				
			rejectedListData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexRejected = Utility.doPaging(bean
					.getMyPageRejected(), rejectedListData.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = "0";
				pageIndexRejected[1] = "20";
				pageIndexRejected[2] = "1";
				pageIndexRejected[3] = "1";
				pageIndexRejected[4] = "";
			}
			request.setAttribute("totalRejectedPage", Integer.parseInt(String
					.valueOf(pageIndexRejected[2])));
			request.setAttribute("rejectedPageNo", Integer.parseInt(String
					.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals("1"))
				bean.setMyPageRejected("1");
			if (rejectedListData != null && rejectedListData.length > 0) {
				bean.setRejectedApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					InformationSystemChangeRequestForm beanItt = new InformationSystemChangeRequestForm();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(rejectedListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(rejectedListData[i][4])));
					beanItt.setInfoSysReqId(checkNull(String
							.valueOf(rejectedListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(rejectedListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(rejectedListData[i][7])));
					rejectedList.add(beanItt);
				}
				bean.setRejectedApplicationIteratorList(rejectedList);
			}
			// Rejected application Ends
			// Rejected cancellation application Begins
				
			String approvedcancellationQuery =   "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
												+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
												+" TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY')  ,"
												+" INF_CNG_CODE , APPL_STATUS, NVL(CNG_TITLE,'')"
												+" FROM HRMS_D1_INF_CNG_REQ"
												+" LEFT JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
												+" WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  = 'Z' AND HRMS_D1_INF_CNG_REQ.CREATED_BY =" +userId 
												+" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
			
			rejectedCancellationListData = getSqlModel().getSingleResult(
					approvedcancellationQuery);
			String[] pageIndexRejectedCancellation = Utility.doPaging(
					bean.getMyPageCancelRejected(),
					rejectedCancellationListData.length, 20);
			if (pageIndexRejectedCancellation == null) {
				pageIndexRejectedCancellation[0] = "0";
				pageIndexRejectedCancellation[1] = "20";
				pageIndexRejectedCancellation[2] = "1";
				pageIndexRejectedCancellation[3] = "1";
				pageIndexRejectedCancellation[4] = "";
			}
			request
					.setAttribute("totalRejectedCancellationPage", Integer
							.parseInt(String
									.valueOf(pageIndexRejectedCancellation[2])));
			request
					.setAttribute("rejectedCancellationPageNo", Integer
							.parseInt(String
									.valueOf(pageIndexRejectedCancellation[3])));
			if (pageIndexRejectedCancellation[4].equals("1"))
				bean.setMyPageCancelRejected("1");
			if (rejectedCancellationListData != null
					&& rejectedCancellationListData.length > 0) {
				bean.setRejectedCancelApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer
						.parseInt(pageIndexRejectedCancellation[1]); i++) {
					InformationSystemChangeRequestForm beanItt = new InformationSystemChangeRequestForm();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedCancellationListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedCancellationListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(rejectedCancellationListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(rejectedCancellationListData[i][4])));
					beanItt.setInfoSysReqId(checkNull(String
							.valueOf(rejectedCancellationListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(rejectedCancellationListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(rejectedCancellationListData[i][7])));
					rejectedCancellationList.add(beanItt);
				}
				bean
						.setRejectedCancelApplicationIteratorList(rejectedCancellationList);
			}
			// Rejected cancellation application Ends
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void getApproverCommentList(InformationSystemChangeRequestForm bean, String applicationId) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, INF_CNG_COMMENTS,"
									+ " TO_CHAR(INF_CNG_DATE, 'DD-MM-YYYY   HH:MI:SS') AS INF_CNG_DATE,"
									+ "  DECODE(INF_CNG_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back','C','Closed Ticket', " 
									+ " 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen') AS STATUS, HRMS_D1_ROLE.ROLE_NAME, NVL(INF_CNG_UPLOADED_FILE,'')"
									+ "  FROM HRMS_D1_INF_CNG_PATH "
									+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_CODE)"
									+ " LEFT JOIN HRMS_D1_ROLE ON (HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_ROLE)"
									+ "  WHERE INF_CNG_CODE = " + applicationId + " ORDER BY INF_CNG_PATH_ID DESC";
		
		Object[][] apprCommentListObj = getSqlModel().getSingleResult(apprCommentListSql);
		ArrayList approverList = new ArrayList<Object>(apprCommentListObj.length);
		if(apprCommentListObj !=null && apprCommentListObj.length>0)
		{
			bean.setApproverCommentFlag(true);
			for(int i = 0; i < apprCommentListObj.length; i++) {
				InformationSystemChangeRequestForm innerBean = new InformationSystemChangeRequestForm();
				innerBean.setApprName(checkNull(String.valueOf(apprCommentListObj[i][0])));
				innerBean.setApprComments(checkNull(String.valueOf(apprCommentListObj[i][1])));
				innerBean.setApprDate(checkNull(String.valueOf(apprCommentListObj[i][2])));
				innerBean.setApprStatus(checkNull(String.valueOf(apprCommentListObj[i][3])));
				innerBean.setApproverRole(checkNull(String.valueOf(apprCommentListObj[i][4])));
				innerBean.setUploadFileNameApproverItr(checkNull(String.valueOf(apprCommentListObj[i][5])));
				approverList.add(innerBean);
			}
			bean.setApproverCommentList(approverList);
		}
	}

	

	public boolean delRecord(InformationSystemChangeRequestForm bean) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = bean.getHiddenCode();
		} catch(Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_D1_INF_CNG_REQ WHERE INF_CNG_CODE=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}


	public boolean feedbackFunction(InformationSystemChangeRequestForm bean, HttpServletRequest request,String applicationId) {
		boolean result = false;
		try {
			Object addObj[][] = new Object[1][3];
			addObj[0][0] = bean.getDescribeChange();
			addObj[0][1] = bean.getIdentifyImprovement();
			addObj[0][2] = bean.getComments();
			
			String updateFeedBackDataQuery = " UPDATE HRMS_D1_INF_CNG_REQ SET APPL_STATUS = 'X',"
										   + " DESC_CNG = ? , IDENTIFY_IMPROVEMENT=? , COMMENTS = ?  "
										   + " WHERE INF_CNG_CODE = " + applicationId;

			result = getSqlModel().singleExecute(updateFeedBackDataQuery, addObj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	/**Method : insertIntoActivityLogs.
	 * Purpose : This method is used to insert re-opened comments into activity logs
	 * @param applicationId : Application ID
	 * @param userId : Current login employee ID
	 * @param reOpenComments : Re-open comments
	 * @param statusToUpdate : Status To Update
	 */
	public void insertIntoActivityLogs(String applicationId, String userId, String reOpenComments, String statusToUpdate) {
		try {
			String insertSql = " INSERT INTO HRMS_D1_INF_CNG_PATH (INF_CNG_PATH_ID, INF_CNG_CODE, INF_CNG_APPR_CODE, INF_CNG_COMMENTS, INF_CNG_STATUS) "
							 + " VALUES ((SELECT NVL(MAX(INF_CNG_PATH_ID), 0) + 1 FROM HRMS_D1_INF_CNG_PATH), ?, ?, ?, ?) ";
			Object[][] insertObj = new Object[1][4];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = userId;
			insertObj[0][2] = reOpenComments;
			insertObj[0][3] = statusToUpdate;
			getSqlModel().singleExecute(insertSql, insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}