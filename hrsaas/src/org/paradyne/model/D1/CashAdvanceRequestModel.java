package org.paradyne.model.D1;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.CashAdvanceRequestForm;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.common.ReportingModel;


public class CashAdvanceRequestModel extends ModelBase {

	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CashAdvanceRequestModel.class);

	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean delRecord(CashAdvanceRequestForm bean) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = bean.getHiddenCode();
		} catch(Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_D1_CASH_ADVANCE WHERE CASH_ADV_ID=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}

	
	

	public void getEmployeeDetails(String empId, CashAdvanceRequestForm bean) {
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

			bean.setEmployeeToken(String.valueOf(data[0][0]));// employee
			// token

			bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));// employee
			// name
			bean.setDeptNumber(checkNull(String.valueOf(data[0][2])));// Dept Number
			bean.setDeptName(checkNull(String.valueOf(data[0][3])));// department
			bean.setDesignation(checkNull(String.valueOf(data[0][5])));// designation
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

	
	/**
	 * Method to select the State.
	 * 
	 * @param addDet
	 */
	public void getStateCountry(CashAdvanceRequestForm bean) {
		String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
			+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE =" + bean.getCityId() + ") ";
		Object[][] stateCode = getSqlModel().getSingleResult(query);

		if(stateCode.length > 0) {
			bean.setStateName(checkNull(String.valueOf(stateCode[0][1])));
			String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
				+ stateCode[0][2] + " ";
			Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
			if(countryName.length > 0) {
				bean.setCountry(checkNull(String.valueOf(countryName[0][1])));
			}// end of nested if
			else bean.setCountry("");
		}// end of if
	}

	

	public boolean isCurrentUser(String userId) {
		String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID=" + userId;
		Object[][] currentUserObj = getSqlModel().getSingleResult(currentUserSql);
		if(currentUserObj != null && currentUserObj.length > 0) { return true; }
		return false;
	}

	

	public boolean sendForApprovalFunction(CashAdvanceRequestForm bean,HttpServletRequest request) {
		boolean result = false;
		if(bean.getHiddenCode().equals(""))
		{
			result = draftFunction(bean, request);
		}else
		{
			result = updateRecords(bean, request);
			try {
				String changeStatusQuery = "UPDATE HRMS_D1_CASH_ADVANCE SET STATUS = 'P' WHERE CASH_ADV_ID  = "+bean.getHiddenCode();
				result =  getSqlModel().singleExecute(changeStatusQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	

	public boolean updateRecords(CashAdvanceRequestForm bean,HttpServletRequest request) {
		boolean result = false;
		try {

			Object updateObj[][] = new Object[1][17];
			updateObj[0][0] = bean.getEmployeeCode();
			updateObj[0][1] = bean.getNoOfEmployeeTravel();
			updateObj[0][2] = bean.getEmployeeAddress();
			updateObj[0][3] = bean.getCityName();
			updateObj[0][4] = bean.getStateName();
			updateObj[0][5] = bean.getStateZip();
			updateObj[0][6] = bean.getPhoneNumber();
			updateObj[0][7] = bean.getBusinessPurpose();
			updateObj[0][8] = bean.getDepartmentCode();
			updateObj[0][9] = bean.getStartDate();
			updateObj[0][10] = bean.getEndDate();
			updateObj[0][11] = bean.getTripDuration();
			updateObj[0][12] = bean.getAdvanceAmount();
			updateObj[0][13] = bean.getAdvanceNeededDate();
			updateObj[0][14] = bean.getComments();
			updateObj[0][15] = bean.getStatus();
			updateObj[0][16] = bean.getApproverCode();
			
			 
			
			String insertQuery = "UPDATE HRMS_D1_CASH_ADVANCE SET "
				+ " EMP_ID = ? , NO_EMP_TRVL = ? , EMP_ADDRESS = ? , CITY = ? , STATE = ? , ZIP = ? , PHONE_NUMBER = ? , BUSINESS_PURPOSE = ? ," +
					" DEPT_CODE  = ? , START_DATE  = TO_DATE(?,'DD-MM-YYYY')  , END_DATE  = TO_DATE(?,'DD-MM-YYYY') , TRIP_DURATION = ? , ADV_AMOUNT = ? , ADV_NEEDED_DATE  = TO_DATE(?,'DD-MM-YYYY')  , EMP_COMMENT = ? , STATUS = ? , APPROVER_CODE = ?  WHERE CASH_ADV_ID = " + bean.getHiddenCode() ;
			

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
	
	public void setApproverData(CashAdvanceRequestForm bean, Object[][] empFlow) {
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
						CashAdvanceRequestForm bean1 = new CashAdvanceRequestForm();
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

	

	public void getSysDate(CashAdvanceRequestForm bean) {
		String sysDateSql = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object data[][] = getSqlModel().getSingleResult(sysDateSql);

		if (data != null && data.length > 0) {
			bean.setApplDate(String.valueOf(data[0][0]));// Set sys_date as
			// application date
		}
		
	}

	public boolean draftFunction(CashAdvanceRequestForm bean,HttpServletRequest request) {
		boolean result = false;
		System.out.println("Status : "+bean.getStatus());
		try {
			Object addObj[][] = new Object[1][19];
			addObj[0][0] = bean.getEmployeeCode();
			addObj[0][1] = bean.getNoOfEmployeeTravel();
			addObj[0][2] = bean.getEmployeeAddress();
			addObj[0][3] = bean.getCityName();
			addObj[0][4] = bean.getStateName();
			addObj[0][5] = bean.getStateZip();
			addObj[0][6] = bean.getPhoneNumber();
			addObj[0][7] = bean.getBusinessPurpose();
			addObj[0][8] = bean.getDepartmentCode();
			addObj[0][9] = bean.getStartDate();
			addObj[0][10] = bean.getEndDate();
			addObj[0][11] = bean.getTripDuration();
			addObj[0][12] = bean.getAdvanceAmount();
			addObj[0][13] = bean.getAdvanceNeededDate();
			addObj[0][14] = bean.getComments();
			addObj[0][15] = bean.getStatus();
			
			
			String autoCode = "";
			String selQuery = "SELECT NVL(MAX(CASH_ADV_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(CASH_ADV_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_CASH_ADVANCE	";
			Object[][] selData = getSqlModel().getSingleResult(selQuery);
			if (bean.getHiddenCode().equals("")) {			
				autoCode = String.valueOf(selData[0][0]);
				System.out.println("autoCode==="+ autoCode);
				bean.setHiddenCode(autoCode);			
				/**
					 * SET TRACKING NO
					 */String qq="SELECT NVL(MAX(CASH_ADV_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(CASH_ADV_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_CASH_ADVANCE	";
						Object[][]obj=getSqlModel().getSingleResult(qq);
						if(obj !=null && obj.length>0){			
						try {
							ApplCodeTemplateModel model = new ApplCodeTemplateModel();
							model.initiate(context, session);
							String applnCode = model.generateApplicationCode(String.valueOf(obj[0][1]), "D1-CASHADV", bean.getUserEmpId(),String.valueOf(obj[0][2]));
							bean.setTrackingNo(checkNull(applnCode));
							System.out.println("applnCode%%%%%" + applnCode);
							model.terminate();
						} catch (Exception e) {
							// TODO: handle exception
						}
						}			
			}
			
			addObj[0][16] = bean.getTrackingNo();
		//	addObj[0][17] = bean.getApplDate();
			addObj[0][17] = bean.getApproverCode();
			addObj[0][18] = bean.getUserEmpId();
			String insertQuery = "INSERT INTO HRMS_D1_CASH_ADVANCE"
				+ "(CASH_ADV_ID, EMP_ID, NO_EMP_TRVL, EMP_ADDRESS, CITY, STATE, ZIP, PHONE_NUMBER, BUSINESS_PURPOSE," +
						" DEPT_CODE, START_DATE, END_DATE, TRIP_DURATION, ADV_AMOUNT, ADV_NEEDED_DATE, EMP_COMMENT, STATUS, " +
						"TRACKING_NUMBER, APPLICAION_DATE, APPROVER_CODE,CASH_ADV_INITIATOR,CASH_ADV_INITIATOR_DATE) "
				+ " VALUES((SELECT NVL(MAX(CASH_ADV_ID),0)+1 FROM HRMS_D1_CASH_ADVANCE),?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,SYSDATE,?,?,SYSDATE)";

			result = getSqlModel().singleExecute(insertQuery, addObj);
			if (result) {
				String autoCodeQuery = " SELECT NVL(MAX(CASH_ADV_ID),0) FROM HRMS_D1_CASH_ADVANCE ";
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
	public void getPendingList(CashAdvanceRequestForm bean,
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
			String selQuery = "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
							+"		CASH_ADV_ID, STATUS"
							+"		FROM HRMS_D1_CASH_ADVANCE"
							+"		left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
							+"		where HRMS_D1_CASH_ADVANCE.STATUS  = 'D' AND HRMS_D1_CASH_ADVANCE.CASH_ADV_INITIATOR =" +userId 
							+		"ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID  DESC ";
			
			
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
					CashAdvanceRequestForm beanItt = new CashAdvanceRequestForm();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(draftListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(draftListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(draftListData[i][3])));
					beanItt.setCashAdvancId(checkNull(String
							.valueOf(draftListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(draftListData[i][5])));
					draftvoucherList.add(beanItt);
				}
				bean.setDraftVoucherIteratorList(draftvoucherList);
			}
			// For drafted application Ends
			
			// For in-Process application Begins
			String inProcessQuery = "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
				+"		left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				+"		where HRMS_D1_CASH_ADVANCE.STATUS  IN ('P','F') AND HRMS_D1_CASH_ADVANCE.CASH_ADV_INITIATOR =" +userId 
				+		"ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID  DESC ";
			
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
					CashAdvanceRequestForm beanItt = new CashAdvanceRequestForm();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(inProcessListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(inProcessListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(inProcessListData[i][3])));
					beanItt.setCashAdvancId(checkNull(String
							.valueOf(inProcessListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(inProcessListData[i][5])));
					
					inProcessVoucherList.add(beanItt);
				}
				bean.setInProcessVoucherIteratorList(inProcessVoucherList);
			}
			// For in-Process application Ends
			
			
			// Sent-Back application Begins
			String sentBackQuery = "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
				+"		left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				+"		where HRMS_D1_CASH_ADVANCE.STATUS  = 'B' AND HRMS_D1_CASH_ADVANCE.CASH_ADV_INITIATOR =" +userId 
				+		"ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID  DESC ";
			
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
					CashAdvanceRequestForm beanItt = new CashAdvanceRequestForm();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(sentBackListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(sentBackListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(sentBackListData[i][3])));
					beanItt.setCashAdvancId(checkNull(String
							.valueOf(sentBackListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(sentBackListData[i][5])));
					
					sentBackVoucherList.add(beanItt);
				}
				bean.setSentBackVoucherIteratorList(sentBackVoucherList);
			}
			// Sent-Back application Ends
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public void editApplicationFunction(CashAdvanceRequestForm reqbean,
			String requestID) {
		try {
			
			
			String editQuery = "SELECT CASH_ADV_ID, HRMS_D1_CASH_ADVANCE.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN, "
			 +" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_RANK,RANK_NAME,"
			 +" HRMS_D1_DEPARTMENT.DEPT_NUMBER,NVL(HRMS_DEPT.DEPT_NAME, ' '),  NO_EMP_TRVL, EMP_ADDRESS, "
			 +" CITY, STATE, ZIP, PHONE_NUMBER, BUSINESS_PURPOSE, DEPT_CODE, NVL(HRMS_DEPT.DEPT_NAME, ' '),EMP_DEPT,"
			 +" TO_CHAR(START_DATE,'DD-MM-YYYY'), TO_CHAR(END_DATE,'DD-MM-YYYY'), TRIP_DURATION, ADV_AMOUNT, TO_CHAR(ADV_NEEDED_DATE,'DD-MM-YYYY'), "
			 +" EMP_COMMENT, STATUS, APPLICAION_DATE, APPROVER_CODE,"
			 +" OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME ,TRACKING_NUMBER "
			 +" , CASH_ADV_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(CASH_ADV_INITIATOR_DATE,'DD-MM-YYYY HH24:MI')FROM HRMS_D1_CASH_ADVANCE"
			 +" left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
			 +" LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)"
			 +" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID =HRMS_D1_CASH_ADVANCE.DEPT_CODE )"
			 +" LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_CASH_ADVANCE.APPROVER_CODE)"
			 +" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
			 + " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_CASH_ADVANCE.CASH_ADV_INITIATOR)  "
			+ " WHERE  CASH_ADV_ID ="+requestID;
								
							  // +" WHERE VOUCHER_REQUEST_ID = "+requestID;
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if(editObj!=null && editObj.length>0)
			{
				reqbean.setHiddenCode(checkNull(String.valueOf(editObj[0][0])));
				reqbean.setEmployeeCode(checkNull(String.valueOf(editObj[0][1])));
				reqbean.setEmployeeToken(checkNull(String.valueOf(editObj[0][2])));
				reqbean.setEmployeeName(checkNull(String.valueOf(editObj[0][3])));
				reqbean.setDesignation(checkNull(String.valueOf(editObj[0][5])));
				reqbean.setNoOfEmployeeTravel(checkNull(String.valueOf(editObj[0][8])));
				reqbean.setEmployeeAddress(checkNull(String.valueOf(editObj[0][9])));
				reqbean.setCityName(checkNull(String.valueOf(editObj[0][10])));
				reqbean.setStateName(checkNull(String.valueOf(editObj[0][11])));
				reqbean.setStateZip(checkNull(String.valueOf(editObj[0][12])));
				reqbean.setPhoneNumber(checkNull(String.valueOf(editObj[0][13])));
				reqbean.setBusinessPurpose(checkNull(String.valueOf(editObj[0][14])));
				
			
				reqbean.setDepartmentCode(checkNull(String.valueOf(editObj[0][15])));
				reqbean.setDepartmentName(checkNull(String.valueOf(editObj[0][16])));
				reqbean.setStartDate(checkNull(String.valueOf(editObj[0][18])));
				reqbean.setEndDate(checkNull(String.valueOf(editObj[0][19])));
				reqbean.setTripDuration(checkNull(String.valueOf(editObj[0][20])));
				reqbean.setAdvanceAmount(checkNull(String.valueOf(editObj[0][21])));
				reqbean.setAdvanceNeededDate(checkNull(String.valueOf(editObj[0][22])));
				reqbean.setComments(checkNull(String.valueOf(editObj[0][23])));
				reqbean.setStatus(checkNull(String.valueOf(editObj[0][24])));
				reqbean.setApplDate(checkNull(String.valueOf(editObj[0][25])));
				reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][26])));
				reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][27])));
				reqbean.setApproverName(checkNull(String.valueOf(editObj[0][28])));
				
				
				reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][29])));
				reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][30])));
				reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][31])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][32])));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void viewApplicationFunction(CashAdvanceRequestForm reqbean,
			String requestID) {
try {
			String editQuery =  "SELECT CASH_ADV_ID, HRMS_D1_CASH_ADVANCE.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN, "
				 +" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_RANK,RANK_NAME,"
				 +" HRMS_D1_DEPARTMENT.DEPT_NUMBER,NVL(HRMS_DEPT.DEPT_NAME, ' '),  NO_EMP_TRVL, EMP_ADDRESS, "
				 +" CITY, STATE, ZIP, PHONE_NUMBER, BUSINESS_PURPOSE, DEPT_CODE, NVL(HRMS_DEPT.DEPT_NAME, ' '),EMP_DEPT,"
				 +" TO_CHAR(START_DATE,'DD-MM-YYYY'), TO_CHAR(END_DATE,'DD-MM-YYYY'), TRIP_DURATION, ADV_AMOUNT, TO_CHAR(ADV_NEEDED_DATE,'DD-MM-YYYY'), "
				 +" EMP_COMMENT, STATUS, APPLICAION_DATE, APPROVER_CODE,"
				 +" OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME ,TRACKING_NUMBER "
				 +" , CASH_ADV_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(CASH_ADV_INITIATOR_DATE,'DD-MM-YYYY HH24:MI')FROM HRMS_D1_CASH_ADVANCE"
				 +" left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				 +" LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)"
				 +" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID =HRMS_D1_CASH_ADVANCE.DEPT_CODE )"
				 +" LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_CASH_ADVANCE.APPROVER_CODE)"
				 +" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
				 + " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_CASH_ADVANCE.CASH_ADV_INITIATOR)  "
				+ " WHERE  CASH_ADV_ID ="+requestID;
								
							  // +" WHERE VOUCHER_REQUEST_ID = "+requestID;
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if(editObj!=null && editObj.length>0)
			{
				reqbean.setHiddenCode(checkNull(String.valueOf(editObj[0][0])));
				reqbean.setEmployeeCode(checkNull(String.valueOf(editObj[0][1])));
				reqbean.setEmployeeToken(checkNull(String.valueOf(editObj[0][2])));
				reqbean.setEmployeeName(checkNull(String.valueOf(editObj[0][3])));
				reqbean.setDesignation(checkNull(String.valueOf(editObj[0][5])));
				reqbean.setNoOfEmployeeTravel(checkNull(String.valueOf(editObj[0][8])));
				reqbean.setEmployeeAddress(checkNull(String.valueOf(editObj[0][9])));
				reqbean.setCityName(checkNull(String.valueOf(editObj[0][10])));
				reqbean.setStateName(checkNull(String.valueOf(editObj[0][11])));
				reqbean.setStateZip(checkNull(String.valueOf(editObj[0][12])));
				reqbean.setPhoneNumber(checkNull(String.valueOf(editObj[0][13])));
				reqbean.setBusinessPurpose(checkNull(String.valueOf(editObj[0][14])));
				
			
				reqbean.setDepartmentCode(checkNull(String.valueOf(editObj[0][15])));
				reqbean.setDepartmentName(checkNull(String.valueOf(editObj[0][16])));
				reqbean.setStartDate(checkNull(String.valueOf(editObj[0][18])));
				reqbean.setEndDate(checkNull(String.valueOf(editObj[0][19])));
				reqbean.setTripDuration(checkNull(String.valueOf(editObj[0][20])));
				reqbean.setAdvanceAmount(checkNull(String.valueOf(editObj[0][21])));
				reqbean.setAdvanceNeededDate(checkNull(String.valueOf(editObj[0][22])));
				reqbean.setComments(checkNull(String.valueOf(editObj[0][23])));
				reqbean.setStatus(checkNull(String.valueOf(editObj[0][24])));
				reqbean.setApplDate(checkNull(String.valueOf(editObj[0][25])));
				reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][26])));
				reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][27])));
				reqbean.setApproverName(checkNull(String.valueOf(editObj[0][28])));
				
				
				reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][29])));
				reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][30])));
				reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][31])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][32])));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getApprovedList(CashAdvanceRequestForm bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();

			Object[][] approvedCancellationListData = null;
			ArrayList approvedCancellationList = new ArrayList();

			// Approved application Begins
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'A' AND HWSW_REQUESTER_ID= "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";*/
			
			String selQuery =  "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
				+"		left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				+"		where HRMS_D1_CASH_ADVANCE.STATUS  = 'A' AND HRMS_D1_CASH_ADVANCE.EMP_ID =" +userId 
				+		"ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID  DESC ";
			
			
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
					CashAdvanceRequestForm beanItt = new CashAdvanceRequestForm();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(approvedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(approvedListData[i][3])));
					beanItt.setCashAdvancId(checkNull(String
							.valueOf(approvedListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(approvedListData[i][5])));
					approvedList.add(beanItt);
				}
				bean.setApprovedApplicationIteratorList(approvedList);
			}
			// Approved application Ends

			// Approved cancellation application Begins
			/*String approvedcancellationQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'X' AND HWSW_REQUESTER_ID= "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";*/
			
			String approvedcancellationQuery =  "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
				+"		left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				+"		where HRMS_D1_CASH_ADVANCE.STATUS  = 'X' AND HRMS_D1_CASH_ADVANCE.EMP_ID =" +userId 
				+		"ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID  DESC ";
			
			
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
					CashAdvanceRequestForm beanItt = new CashAdvanceRequestForm();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedCancellationListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(approvedCancellationListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(approvedCancellationListData[i][3])));
					beanItt.setCashAdvancId(checkNull(String
							.valueOf(approvedCancellationListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(approvedCancellationListData[i][5])));
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

	public void getRejectedList(CashAdvanceRequestForm bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			Object[][] rejectedCancellationListData = null;
			ArrayList rejectedCancellationList = new ArrayList();
			// Rejected application Begins
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'R' AND HWSW_REQUESTER_ID= "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";*/
				
			String selQuery =  "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
				+"		left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				+"		where HRMS_D1_CASH_ADVANCE.STATUS  = 'R' AND HRMS_D1_CASH_ADVANCE.EMP_ID =" +userId 
				+		"ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID  DESC ";
			
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
					CashAdvanceRequestForm beanItt = new CashAdvanceRequestForm();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(rejectedListData[i][3])));
					beanItt.setCashAdvancId(checkNull(String
							.valueOf(rejectedListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(rejectedListData[i][5])));
					rejectedList.add(beanItt);
				}
				bean.setRejectedApplicationIteratorList(rejectedList);
			}
			// Rejected application Ends
			// Rejected cancellation application Begins
			/*String approvedcancellationQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'Z' AND HWSW_REQUESTER_ID= "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";*/
			
			String approvedcancellationQuery =  "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
				+"		left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				+"		where HRMS_D1_CASH_ADVANCE.STATUS  = 'Z' AND HRMS_D1_CASH_ADVANCE.EMP_ID =" +userId 
				+		"ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID  DESC ";
			
			
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
					CashAdvanceRequestForm beanItt = new CashAdvanceRequestForm();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedCancellationListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedCancellationListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(rejectedCancellationListData[i][3])));
					beanItt.setCashAdvancId(checkNull(String
							.valueOf(rejectedCancellationListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(rejectedCancellationListData[i][5])));
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
		
	public void getApproverCommentList(CashAdvanceRequestForm bean, String applicationId) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CASH_ADV_COMMENTS,"
									+ " TO_CHAR(CASH_ADV_APPR_DATE, 'DD-MM-YYYY') AS CASH_ADV_APPR_DATE,"
									+ "  DECODE(CASH_ADV_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS "
									+ "  FROM HRMS_D1_CASH_ADV_PATH "
									+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_CASH_ADV_PATH.CASH_ADV_APPROVER_CODE)"
									+ "  WHERE CASH_ADV_CODE = " + applicationId + " ORDER BY CASH_ADV_PATH_ID DESC";
		
		Object[][] apprCommentListObj = getSqlModel().getSingleResult(apprCommentListSql);
		ArrayList approverList = new ArrayList<Object>(apprCommentListObj.length);
		if(apprCommentListObj !=null && apprCommentListObj.length>0)
		{
			bean.setApproverCommentFlag(true);
			for(int i = 0; i < apprCommentListObj.length; i++) {
				CashAdvanceRequestForm innerBean = new CashAdvanceRequestForm();
				innerBean.setApprName(String.valueOf(apprCommentListObj[i][0]));
				innerBean.setApprComments(String.valueOf(apprCommentListObj[i][1]));
				innerBean.setApprDate(String.valueOf(apprCommentListObj[i][2]));
				innerBean.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
				approverList.add(innerBean);
			}
			bean.setApproverCommentList(approverList);
		}
	}

}