package org.paradyne.model.D1;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.ClassEnrollmentForm;
import org.paradyne.bean.D1.HardwareSoftwareRequest;
import org.paradyne.bean.D1.PersonalDataChange;
import org.paradyne.bean.D1.ReqToBackOutVoucher;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.common.ReportingModel;

/**
 * Bhushan Dasare Feb 15, 2011
 */

public class ClassEnrollmentFormModel extends ModelBase {

	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ClassEnrollmentFormModel.class);

	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean delRecord(ClassEnrollmentForm bean) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = bean.getHiddenCode();
		} catch(Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_D1_CLASS_ENROLL WHERE ENROLL_ID=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}

	
	public void getEmployeeDetails(ClassEnrollmentForm bean) {

		String query = " SELECT EMP_DEPT,HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME, ' '),HRMS_EMP_ADDRESS.ADD_PH1 , "
			+ " ADD_CITY, CITY.LOCATION_NAME AS CITY,ADD_STATE,  ADD_FAX, ADD_EMAIL, ADD_PINCODE FROM HRMS_EMP_OFFC " 
			+ " LEFt JOIN HRMS_DEPT ON (EMP_DEPT = HRMS_DEPT.DEPT_ID) "

			+ " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "

			+ " LEFT JOIN HRMS_D1_DEPARTMENT d1 ON(d1.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO)  "

			+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID) " 
			+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_EMP_OFFC.EMP_ID)"
			+ " WHERE HRMS_EMP_OFFC.EMP_ID ="
			+ bean.getEmployeeCode();

		Object[][] empData = getSqlModel().getSingleResult(query);

		bean.setDeptNumber(checkNull(String.valueOf(empData[0][1])));
		bean.setDeptCode(checkNull(String.valueOf(empData[0][2])));
		bean.setDeptName(checkNull(String.valueOf(empData[0][3])));
		//bean.setPhoneNumber(checkNull(String.valueOf(empData[0][3])));
		//bean.setCityName(checkNull(String.valueOf(empData[0][4])));
		//bean.setStateName(checkNull(String.valueOf(empData[0][5])));
	//	bean.setEmailAddress(checkNull(String.valueOf(empData[0][6])));
	//	bean.setStateZip(checkNull(String.valueOf(empData[0][7])));
		// bean.setDeptName(String.valueOf(empData[0][3]));

		for(int i = 0; i < 7; i++) {
			System.out.println(" getEmployeeDetails Details::::::" + empData[0][i]);
		}
		String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
		Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
		if(dateObj!=null && dateObj.length >0){
			bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
		}
		
		String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
		+ "	INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
		+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + bean.getUserEmpId();
		
		Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
		if(initiatorObj!=null && initiatorObj.length >0){
			bean.setInitiatorCode(bean.getUserEmpId());
			bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
			bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
		}

		// }// end of if
	}

	public void getEmployeeDetails(String empId, ClassEnrollmentForm bean) {
		Object[] beanObj = null;
		try {
			beanObj = new Object[1];
			beanObj[0] = empId;

			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME, ' '),HRMS_EMP_ADDRESS.ADD_PH1,HRMS_EMP_OFFC.EMP_ID , "
				+ "	ADD_CITY, CITY.LOCATION_NAME AS CITY,ADD_STATE,  ADD_FAX, ADD_EMAIL, ADD_PINCODE,EMP_REGION_ID,REGION_NAME FROM HRMS_EMP_OFFC " 
				+ " LEFT JOIN HRMS_DEPT ON (EMP_DEPT = HRMS_DEPT.DEPT_ID) "
				+ " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID) " 
				+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_EMP_OFFC.EMP_ID)"
				+ " LEFT JOIN hrms_d1_region ON(hrms_d1_region.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID)"
				+ " WHERE HRMS_EMP_OFFC.EMP_ID=?";

			Object[][] data = getSqlModel().getSingleResult(query, beanObj);

			bean.setEmployeeToken(String.valueOf(data[0][0]));// employee
			// token

			bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));// employee
			// name
			bean.setDeptNumber(checkNull(String.valueOf(data[0][2])));// Dept Number
			bean.setDeptCode(checkNull(String.valueOf(data[0][3])));// Dept Number
			bean.setDeptName(checkNull(String.valueOf(data[0][4])));// department
		//	bean.setPhoneNumber(checkNull(String.valueOf(data[0][4])));
			//bean.setCityName(checkNull(String.valueOf(data[0][7])));
			//bean.setStateName(checkNull(String.valueOf(data[0][8])));
		//	bean.setFaxNumber(checkNull(String.valueOf(data[0][9])));
		//	bean.setEmailAddress(checkNull(String.valueOf(data[0][10])));
		//	bean.setStateZip(checkNull(String.valueOf(data[0][11])));
		//	bean.setLocation(checkNull(String.valueOf(data[0][13])));
			bean.setEmployeeCode(checkNull(String.valueOf(data[0][6])));// employee
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
	public void getStateCountry(ClassEnrollmentForm bean) {
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

	

	public boolean sendForApprovalFunction(ClassEnrollmentForm bean,HttpServletRequest request) {
		boolean result = false;
		if(bean.getHiddenCode().equals(""))
		{
			result = draftFunction(bean, request);
		}else
		{
			result = updateRecords(bean, request);
			try {
				String changeStatusQuery = "UPDATE HRMS_D1_CLASS_ENROLL SET STATUS = 'P' WHERE ENROLL_ID = "+bean.getHiddenCode();
				result =  getSqlModel().singleExecute(changeStatusQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	

	public boolean updateRecords(ClassEnrollmentForm bean,HttpServletRequest request) {
		boolean result = false;
		try {

			Object updateObj[][] = new Object[1][19];
			//updateObj[0][0] = bean.getCourseId();
			
			updateObj[0][0] = bean.getCourseName();
			updateObj[0][1] = bean.getCourseLocationName();
			updateObj[0][2] = bean.getStartDate();
			updateObj[0][3] = bean.getEndDate();
			
			updateObj[0][4] = bean.getEmployeeCode();
			
			String approverCode = "0";
			if(!bean.getApproverCode().equals("")) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			updateObj[0][5] = approverCode;
			
			//addObj[0][2] = bean.getAttendeeCode();
			updateObj[0][6] = bean.getApplDate();
			updateObj[0][7] = bean.getStatus();
			
			System.out.println("getPositionType()33333333" + bean.getLocation());
			if (bean.getLocation().equals("Us")) {
				updateObj[0][8] = "U";
			} else if (bean.getLocation().equals("Ca")) {
				updateObj[0][8] = "C";
			} 
			else {
				updateObj[0][8] = "";
			}
			updateObj[0][9] = bean.getStreetAddress();
			updateObj[0][10] = bean.getCityName();
			updateObj[0][11] = bean.getStateName();
			updateObj[0][12] = bean.getStateZip();
			updateObj[0][13] = bean.getProvidence();
			updateObj[0][14] = bean.getCanadaZipcode();
			updateObj[0][15] = bean.getPhoneNumber();
			updateObj[0][16] = bean.getFaxNumber();
			updateObj[0][17] = bean.getEmailAddress();
			updateObj[0][18] = bean.getDeptCode();
			
			String insertQuery = "UPDATE HRMS_D1_CLASS_ENROLL SET "
				+ " COURSE_NAME = ? , COURSE_LOCATION = ? , COURSE_START_DATE = TO_DATE(?,'DD-MM-YYYY')  , COURSE_END_DATE  = TO_DATE(?,'DD-MM-YYYY')   , EMP_ID = ? , ATTENDEE_MANAGER_ID = ?  ,APPLICAION_DATE = TO_DATE(?,'DD-MM-YYYY')  ,STATUS = ? " +
						" ,LOCATION = ?  , STREET_ADDRESS = ? , CITY = ? , STATE = ? , US_ZIP = ? , PROVIDENCE = ? , CANADA_ZIP = ?, PHONE_NUMBER = ? , FAX_NUMBER = ? , EMAIL_ID = ? , DEPT_CODE=?  WHERE ENROLL_ID = " + bean.getHiddenCode() ;
			

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
	
	public void setApproverData(ClassEnrollmentForm bean, Object[][] empFlow) {
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
						ClassEnrollmentForm bean1 = new ClassEnrollmentForm();
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

	

	

	public void getCourseDetails(ClassEnrollmentForm bean) {
		String query = " select CLASS_NAME, LOCATION, TO_CHAR(REQUEST_START_DATE, 'DD-MM-YYYY'), TO_CHAR(REQUEST_END_DATE, 'DD-MM-YYYY') from HRMS_D1_CLASS_REQUEST "
						+ " where CLASS_REQUEST_ID =	"
			+ bean.getCourseId();

		Object[][] empData = getSqlModel().getSingleResult(query);

		bean.setCourseName(checkNull(String.valueOf(empData[0][0])));
		bean.setCourseLocationName(checkNull(String.valueOf(empData[0][1])));
		bean.setStartDate(checkNull(String.valueOf(empData[0][2])));
		bean.setEndDate(checkNull(String.valueOf(empData[0][3])));
		//bean.setWorkPhone(checkNull(String.valueOf(empData[0][3])));
		// bean.setDeptName(String.valueOf(empData[0][3]));

		for(int i = 0; i < 4; i++) {
			System.out.println(" getEmployeeDetails Details::::::" + empData[0][i]);
		}
		String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
		Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
		if(dateObj!=null && dateObj.length >0){
			bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
		}
		
		String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
		+ "	INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
		+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + bean.getUserEmpId();
		
		Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
		if(initiatorObj!=null && initiatorObj.length >0){
			bean.setInitiatorCode(bean.getUserEmpId());
			bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
			bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
		}
		
	}

	public void getSysDate(ClassEnrollmentForm bean) {
		String sysDateSql = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object data[][] = getSqlModel().getSingleResult(sysDateSql);

		if (data != null && data.length > 0) {
			bean.setApplDate(String.valueOf(data[0][0]));// Set sys_date as
			// application date
		}
		
	}

	public boolean draftFunction(ClassEnrollmentForm bean,HttpServletRequest request) {
		boolean result = false;
		System.out.println("Status : "+bean.getStatus());
		try {
			Object addObj[][] = new Object[1][20];
			///addObj[0][0] = bean.getCourseId();
			
			addObj[0][0] = bean.getCourseName();
			addObj[0][1] = bean.getCourseLocationName();
			addObj[0][2] = bean.getStartDate();
			addObj[0][3] = bean.getEndDate();
			
			addObj[0][4] = bean.getEmployeeCode();
			
			String approverCode = "0";
			if(!bean.getApproverCode().equals("")) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			addObj[0][5] = approverCode;
			
			//addObj[0][2] = bean.getAttendeeCode();
		//	addObj[0][6] = bean.getApplDate();
			addObj[0][6] = bean.getStatus();
			
			String autoCode = "";
			String selQuery = "SELECT NVL(MAX(ENROLL_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(ENROLL_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_CLASS_ENROLL	";
			Object[][] selData = getSqlModel().getSingleResult(selQuery);
			if (bean.getHiddenCode().equals("")) {			
				autoCode = String.valueOf(selData[0][0]);
				System.out.println("autoCode==="+ autoCode);
				bean.setHiddenCode(autoCode);			
				/**
					 * SET TRACKING NO
					 */String qq="SELECT NVL(MAX(ENROLL_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(ENROLL_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_CLASS_ENROLL	";
						Object[][]obj=getSqlModel().getSingleResult(qq);
						if(obj !=null && obj.length>0){			
						try {
							ApplCodeTemplateModel model = new ApplCodeTemplateModel();
							model.initiate(context, session);
							String applnCode = model.generateApplicationCode(String.valueOf(obj[0][1]), "D1-ENROLL", bean.getUserEmpId(),String.valueOf(obj[0][2]));
							bean.setTrackingNo(checkNull(applnCode));
							System.out.println("applnCode%%%%%" + applnCode);
							model.terminate();
						} catch (Exception e) {
							// TODO: handle exception
						}
						}			
			}
			
			addObj[0][7] = bean.getTrackingNo();
			addObj[0][8] = bean.getInitiatorCode();
			
			System.out.println("getPositionType()33333333" + bean.getLocation());
			if (bean.getLocation().equals("Us")) {
				addObj[0][9] = "U";
			} else if (bean.getLocation().equals("Ca")) {
				addObj[0][9] = "C";
			} 
			else {
				addObj[0][9] = "";
			}
			addObj[0][10] = bean.getStreetAddress();
			addObj[0][11] = bean.getCityName();
			addObj[0][12] = bean.getStateName();
			addObj[0][13] = bean.getStateZip();
			addObj[0][14] = bean.getProvidence();
			addObj[0][15] = bean.getCanadaZipcode();
			addObj[0][16] = bean.getPhoneNumber();
			addObj[0][17] = bean.getFaxNumber();
			addObj[0][18] = bean.getEmailAddress();
			addObj[0][19] = bean.getDeptCode();
			
			
			String insertQuery = "INSERT INTO HRMS_D1_CLASS_ENROLL"
				+ "(ENROLL_ID, COURSE_NAME, COURSE_LOCATION, COURSE_START_DATE, COURSE_END_DATE, EMP_ID, " +
						"ATTENDEE_MANAGER_ID ,APPLICAION_DATE ,STATUS,TRACKING_NUMBER ,CLASS_INITIATOR, CLASS_INITIATOR_DATE," +
						"LOCATION, STREET_ADDRESS, CITY, STATE, US_ZIP, PROVIDENCE, CANADA_ZIP, PHONE_NUMBER, FAX_NUMBER, EMAIL_ID,DEPT_CODE) "
				+ " VALUES((SELECT NVL(MAX(ENROLL_ID),0)+1 FROM HRMS_D1_CLASS_ENROLL),?,?,TO_DATE(?,'DD-MM-YYYY')," +
						"TO_DATE(?,'DD-MM-YYYY'), ?,?,SYSDATE,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?,?)";

			result = getSqlModel().singleExecute(insertQuery, addObj);
			if (result) {
				String autoCodeQuery = " SELECT NVL(MAX(ENROLL_ID),0) FROM HRMS_D1_CLASS_ENROLL ";
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
	public void getPendingList(ClassEnrollmentForm bean,
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
			String selQuery = "SELECT HRMS_D1_CLASS_ENROLL.TRACKING_NUMBER,   HRMS_D1_CLASS_ENROLL.EMP_ID, "
							+"	HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY'),COURSE_ID , STATUS , ENROLL_ID "
							+"		FROM HRMS_D1_CLASS_ENROLL	"
							+"		left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CLASS_ENROLL.EMP_ID)"
							+" left join HRMS_D1_CLASS_REQUEST on(HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID = HRMS_D1_CLASS_ENROLL.COURSE_ID)"
							+"		where HRMS_D1_CLASS_ENROLL.STATUS  = 'D' AND HRMS_D1_CLASS_ENROLL.CLASS_INITIATOR =" +userId 
							+		"ORDER BY HRMS_D1_CLASS_ENROLL.ENROLL_ID  DESC ";
			
			
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
					ClassEnrollmentForm beanItt = new ClassEnrollmentForm();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(draftListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(draftListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(draftListData[i][3])));
					beanItt.setCourseId(checkNull(String
							.valueOf(draftListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(draftListData[i][5])));
					beanItt.setEnrollmentHiddenID(checkNull(String
							.valueOf(draftListData[i][6])));
					draftvoucherList.add(beanItt);
				}
				bean.setDraftVoucherIteratorList(draftvoucherList);
			}
			// For drafted application Ends
			
			// For in-Process application Begins
			String inProcessQuery =  "SELECT HRMS_D1_CLASS_ENROLL.TRACKING_NUMBER,   HRMS_D1_CLASS_ENROLL.EMP_ID, "
				+"	HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY'),COURSE_ID , STATUS , ENROLL_ID "
				+"		FROM HRMS_D1_CLASS_ENROLL	"
				+"		left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CLASS_ENROLL.EMP_ID)"
				+" left join HRMS_D1_CLASS_REQUEST on(HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID = HRMS_D1_CLASS_ENROLL.COURSE_ID)"
				+"		where HRMS_D1_CLASS_ENROLL.STATUS  = 'P' AND HRMS_D1_CLASS_ENROLL.CLASS_INITIATOR =" +userId 
				+		"ORDER BY HRMS_D1_CLASS_ENROLL.ENROLL_ID  DESC ";
			
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
					ClassEnrollmentForm beanItt = new ClassEnrollmentForm();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(inProcessListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(inProcessListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(inProcessListData[i][3])));
					beanItt.setCourseId(checkNull(String
							.valueOf(inProcessListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(inProcessListData[i][5])));
					beanItt.setEnrollmentHiddenID(checkNull(String
							.valueOf(inProcessListData[i][6])));
					inProcessVoucherList.add(beanItt);
				}
				bean.setInProcessVoucherIteratorList(inProcessVoucherList);
			}
			// For in-Process application Ends
			
			
			// Sent-Back application Begins
			String sentBackQuery = "SELECT HRMS_D1_CLASS_ENROLL.TRACKING_NUMBER,   HRMS_D1_CLASS_ENROLL.EMP_ID, "
				+"	HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY'),COURSE_ID , STATUS , ENROLL_ID "
				+"		FROM HRMS_D1_CLASS_ENROLL	"
				+"		left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CLASS_ENROLL.EMP_ID)"
				+" left join HRMS_D1_CLASS_REQUEST on(HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID = HRMS_D1_CLASS_ENROLL.COURSE_ID)"
				+"		where HRMS_D1_CLASS_ENROLL.STATUS  = 'B' AND HRMS_D1_CLASS_ENROLL.CLASS_INITIATOR =" +userId 
				+		"ORDER BY HRMS_D1_CLASS_ENROLL.ENROLL_ID  DESC ";
			
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
					ClassEnrollmentForm beanItt = new ClassEnrollmentForm();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(sentBackListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(sentBackListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(sentBackListData[i][3])));
					beanItt.setCourseId(checkNull(String
							.valueOf(sentBackListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(sentBackListData[i][5])));
					beanItt.setEnrollmentHiddenID(checkNull(String
							.valueOf(sentBackListData[i][6])));
					sentBackVoucherList.add(beanItt);
				}
				bean.setSentBackVoucherIteratorList(sentBackVoucherList);
			}
			// Sent-Back application Ends
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public void editApplicationFunction(ClassEnrollmentForm reqbean,
			String requestID) {
		try {
			
			
			/*String editQuery = "SELECT ENROLL_ID,HRMS_D1_CLASS_ENROLL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN, "
								+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
								+ " TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY'), COURSE_ID ,HRMS_D1_CLASS_REQUEST.CLASS_NAME ,"
								+ " TO_CHAR(REQUEST_START_DATE,'DD-MM-YYYY'), TO_CHAR(REQUEST_END_DATE,'DD-MM-YYYY'), LOCATION,STATUS ,"
								+ " HRMS_D1_DEPARTMENT.DEPT_NUMBER, NVL(HRMS_DEPT.DEPT_NAME, ' '), "
								+ " LOCATION, STREET_ADDRESS, CITY, STATE, US_ZIP, PROVIDENCE,CANADA_ZIP, PHONE_NUMBER, FAX_NUMBER, EMAIL_ID,"
								+ " ATTENDEE_MANAGER_ID, "
								+ " OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,HRMS_D1_CLASS_ENROLL.TRACKING_NUMBER "
								+ " ,CLASS_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(CLASS_INITIATOR_DATE,'DD-MM-YYYY HH24:MI') FROM HRMS_D1_CLASS_ENROLL"
								+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CLASS_ENROLL.EMP_ID)"
								+ " INNER JOIN HRMS_D1_CLASS_REQUEST ON(HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID = HRMS_D1_CLASS_ENROLL.COURSE_ID)"
								+ " LEFT JOIN HRMS_DEPT ON (EMP_DEPT = HRMS_DEPT.DEPT_ID) "
								+ " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
								+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID)  "
								+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_EMP_OFFC.EMP_ID)"
								+ " LEFT JOIN HRMS_D1_REGION ON(HRMS_D1_REGION.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID)"
								+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_CLASS_ENROLL.ATTENDEE_MANAGER_ID)"
								+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_CLASS_ENROLL.CLASS_INITIATOR)  "
								+ " WHERE  ENROLL_ID ="+requestID;*/
			
	String editQuery = "SELECT ENROLL_ID,HRMS_D1_CLASS_ENROLL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN, "
			+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
			+ " TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY'), COURSE_NAME ,COURSE_LOCATION,"
			+ " TO_CHAR(COURSE_START_DATE,'DD-MM-YYYY'), TO_CHAR(COURSE_END_DATE,'DD-MM-YYYY'), STATUS ,"
			+ " DEPT_CODE, NVL(HRMS_DEPT.DEPT_NAME, ' '), "
			+ " LOCATION, STREET_ADDRESS, CITY, STATE, US_ZIP, PROVIDENCE,CANADA_ZIP, PHONE_NUMBER, FAX_NUMBER, EMAIL_ID,"
			+ " ATTENDEE_MANAGER_ID, "
			+ " OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,HRMS_D1_CLASS_ENROLL.TRACKING_NUMBER "
			+ " ,CLASS_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(CLASS_INITIATOR_DATE,'DD-MM-YYYY HH24:MI') FROM HRMS_D1_CLASS_ENROLL"
			+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CLASS_ENROLL.EMP_ID)"
			//+ " INNER JOIN HRMS_D1_CLASS_REQUEST ON(HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID = HRMS_D1_CLASS_ENROLL.COURSE_ID)"
			+ "LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_D1_CLASS_ENROLL.DEPT_CODE) "
			//+ " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
			+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID)  "
			+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_EMP_OFFC.EMP_ID)"
			+ " LEFT JOIN HRMS_D1_REGION ON(HRMS_D1_REGION.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID)"
			+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_CLASS_ENROLL.ATTENDEE_MANAGER_ID)"
			+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_CLASS_ENROLL.CLASS_INITIATOR)  "
			+ " WHERE  ENROLL_ID ="+requestID;
								
							  // +" WHERE VOUCHER_REQUEST_ID = "+requestID;
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if(editObj!=null && editObj.length>0)
			{
				reqbean.setHiddenCode(checkNull(String.valueOf(editObj[0][0])));
				reqbean.setEmployeeCode(checkNull(String.valueOf(editObj[0][1])));
				reqbean.setEmployeeToken(checkNull(String.valueOf(editObj[0][2])));
				reqbean.setEmployeeName(checkNull(String.valueOf(editObj[0][3])));
				reqbean.setApplDate(checkNull(String.valueOf(editObj[0][4])));
				//reqbean.setCourseId(checkNull(String.valueOf(editObj[0][5])));
				reqbean.setCourseName(checkNull(String.valueOf(editObj[0][5])));
				reqbean.setCourseLocationName(checkNull(String.valueOf(editObj[0][6])));
				reqbean.setStartDate(checkNull(String.valueOf(editObj[0][7])));
				reqbean.setEndDate(checkNull(String.valueOf(editObj[0][8])));
				//reqbean.setCourseLocationName(checkNull(String.valueOf(editObj[0][9])));
				reqbean.setStatus(checkNull(String.valueOf(editObj[0][9])));
				reqbean.setDeptCode(checkNull(String.valueOf(editObj[0][10])));
				reqbean.setDeptName(checkNull(String.valueOf(editObj[0][11])));
				
				//reqbean.setLocation(checkNull(String.valueOf(editObj[0][22])));
				
				if (String.valueOf(editObj[0][12]).equals("U")) {
					reqbean.setLocation("Us");
					reqbean.setLocationOption("Us");
				} else if (String.valueOf(editObj[0][12]).equals("C")) {
					reqbean.setLocation("Ca");
					reqbean.setLocationOption("Ca");
				}  else {
					reqbean.setLocation("");
					reqbean.setLocationOption("");
				}
				reqbean.setStreetAddress(checkNull(String.valueOf(editObj[0][13])));
				reqbean.setCityName(checkNull(String.valueOf(editObj[0][14])));
				reqbean.setStateName(checkNull(String.valueOf(editObj[0][15])));
				reqbean.setStateZip(checkNull(String.valueOf(editObj[0][16])));
				reqbean.setProvidence(checkNull(String.valueOf(editObj[0][17])));
				reqbean.setCanadaZipcode(checkNull(String.valueOf(editObj[0][18])));
				reqbean.setPhoneNumber(checkNull(String.valueOf(editObj[0][19])));
				reqbean.setFaxNumber(checkNull(String.valueOf(editObj[0][20])));
				reqbean.setEmailAddress(checkNull(String.valueOf(editObj[0][21])));
				
				//reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][23])));
				//reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][24])));
				//reqbean.setApproverName(checkNull(String.valueOf(editObj[0][25])));
				//reqbean.setSelectapproverName(checkNull(String.valueOf(editObj[0][26])));
				
				Object[][] empFlow = null;
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
						if(!checkNull(String.valueOf(editObj[0][22])).equals(setApprover))
						{
							reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][22])));
							reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][23])));
							reqbean.setSelectapproverName(checkNull(String.valueOf(editObj[0][24])));
						}
					
					// Approver Section Ends
				}else{
					
					reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][22])));
					reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][23])));
					reqbean.setSelectapproverName(checkNull(String.valueOf(editObj[0][24])));
				}
				
				reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][25])));
				reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][26])));
				reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][27])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][28])));
				getApproverCommentList(reqbean, requestID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void viewApplicationFunction(ClassEnrollmentForm reqbean,
			String requestID) {
try {
			String editQuery =  "SELECT ENROLL_ID,HRMS_D1_CLASS_ENROLL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY'), COURSE_NAME ,COURSE_LOCATION,"
				+ " TO_CHAR(COURSE_START_DATE,'DD-MM-YYYY'), TO_CHAR(COURSE_END_DATE,'DD-MM-YYYY'), STATUS ,"
				+ " DEPT_CODE, NVL(HRMS_DEPT.DEPT_NAME, ' '), "
				+ " LOCATION, STREET_ADDRESS, CITY, STATE, US_ZIP, PROVIDENCE,CANADA_ZIP, PHONE_NUMBER, FAX_NUMBER, EMAIL_ID,"
				+ " ATTENDEE_MANAGER_ID, "
				+ " OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,HRMS_D1_CLASS_ENROLL.TRACKING_NUMBER "
				+ " ,CLASS_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(CLASS_INITIATOR_DATE,'DD-MM-YYYY HH24:MI') FROM HRMS_D1_CLASS_ENROLL"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CLASS_ENROLL.EMP_ID)"
				//+ " INNER JOIN HRMS_D1_CLASS_REQUEST ON(HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID = HRMS_D1_CLASS_ENROLL.COURSE_ID)"
				+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_D1_CLASS_ENROLL.DEPT_CODE) "
				//+ " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID)  "
				+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_EMP_OFFC.EMP_ID)"
				+ " LEFT JOIN HRMS_D1_REGION ON(HRMS_D1_REGION.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_CLASS_ENROLL.ATTENDEE_MANAGER_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_CLASS_ENROLL.CLASS_INITIATOR)  "
				+ " WHERE  ENROLL_ID ="+requestID;
								
							  // +" WHERE VOUCHER_REQUEST_ID = "+requestID;
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if(editObj!=null && editObj.length>0)
			{
				reqbean.setClassEnrollmentId(checkNull(String.valueOf(editObj[0][0])));
				reqbean.setEmployeeCode(checkNull(String.valueOf(editObj[0][1])));
				reqbean.setEmployeeToken(checkNull(String.valueOf(editObj[0][2])));
				reqbean.setEmployeeName(checkNull(String.valueOf(editObj[0][3])));
				reqbean.setApplDate(checkNull(String.valueOf(editObj[0][4])));
				//reqbean.setCourseId(checkNull(String.valueOf(editObj[0][5])));
				reqbean.setCourseName(checkNull(String.valueOf(editObj[0][5])));
				reqbean.setCourseLocationName(checkNull(String.valueOf(editObj[0][6])));
				reqbean.setStartDate(checkNull(String.valueOf(editObj[0][7])));
				reqbean.setEndDate(checkNull(String.valueOf(editObj[0][8])));
				
				reqbean.setStatus(checkNull(String.valueOf(editObj[0][9])));
				reqbean.setDeptCode(checkNull(String.valueOf(editObj[0][10])));
				reqbean.setDeptName(checkNull(String.valueOf(editObj[0][11])));
				
				//reqbean.setLocation(checkNull(String.valueOf(editObj[0][22])));
				
				if (String.valueOf(editObj[0][12]).equals("U")) {
					reqbean.setLocation("Us");
					reqbean.setLocationOption("Us");
				} else if (String.valueOf(editObj[0][12]).equals("C")) {
					reqbean.setLocation("Ca");
					reqbean.setLocationOption("Ca");
				}  else {
					reqbean.setLocation("");
					reqbean.setLocationOption("");
				}
				reqbean.setStreetAddress(checkNull(String.valueOf(editObj[0][13])));
				reqbean.setCityName(checkNull(String.valueOf(editObj[0][14])));
				reqbean.setStateName(checkNull(String.valueOf(editObj[0][15])));
				reqbean.setStateZip(checkNull(String.valueOf(editObj[0][16])));
				reqbean.setProvidence(checkNull(String.valueOf(editObj[0][17])));
				reqbean.setCanadaZipcode(checkNull(String.valueOf(editObj[0][18])));
				reqbean.setPhoneNumber(checkNull(String.valueOf(editObj[0][19])));
				reqbean.setFaxNumber(checkNull(String.valueOf(editObj[0][20])));
				reqbean.setEmailAddress(checkNull(String.valueOf(editObj[0][21])));
				
				//reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][23])));
				//reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][24])));
				//reqbean.setApproverName(checkNull(String.valueOf(editObj[0][25])));
				//reqbean.setSelectapproverName(checkNull(String.valueOf(editObj[0][26])));
				
				Object[][] empFlow = null;
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
						if(!checkNull(String.valueOf(editObj[0][22])).equals(setApprover))
						{
							reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][22])));
							reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][23])));
							reqbean.setSelectapproverName(checkNull(String.valueOf(editObj[0][24])));
						}
					
					// Approver Section Ends
				}else{
					
					reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][22])));
					reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][23])));
					reqbean.setSelectapproverName(checkNull(String.valueOf(editObj[0][24])));
				}
				
				reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][25])));
				reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][26])));
				reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][27])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][28])));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getApprovedList(ClassEnrollmentForm bean,
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
			
			String selQuery = "select ENROLL_ID,HRMS_D1_CLASS_ENROLL.TRACKING_NUMBER,HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "to_CHAR(APPLICAION_DATE,'DD-MM-YYYY'), COURSE_ID ,HRMS_D1_CLASS_REQUEST.CLASS_NAME ,STATUS "
				+ "from HRMS_D1_CLASS_ENROLL"
				+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CLASS_ENROLL.EMP_ID)"
				+ "	left join HRMS_D1_CLASS_REQUEST on(HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID = HRMS_D1_CLASS_ENROLL.COURSE_ID)"
				+ "	where HRMS_D1_CLASS_ENROLL.STATUS  = 'A' AND HRMS_D1_CLASS_ENROLL.CLASS_INITIATOR =" +userId 
				+ "	ORDER BY HRMS_D1_CLASS_ENROLL.APPLICAION_DATE DESC ";
			
			
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
					ClassEnrollmentForm beanItt = new ClassEnrollmentForm();
					beanItt.setEnrollmentHiddenID(checkNull(String
							.valueOf(approvedListData[i][0])));
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedListData[i][1])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(approvedListData[i][3])));
					beanItt.setApplDate(checkNull(String
							.valueOf(approvedListData[i][4])));
					beanItt.setCourseId(checkNull(String
							.valueOf(approvedListData[i][6])));
					beanItt.setStatus(checkNull(String
							.valueOf(approvedListData[i][7])));
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
			
			String approvedcancellationQuery = "select ENROLL_ID,HRMS_D1_CLASS_ENROLL.TRACKING_NUMBER,HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "to_CHAR(APPLICAION_DATE,'DD-MM-YYYY'), COURSE_ID ,HRMS_D1_CLASS_REQUEST.CLASS_NAME ,STATUS "
				+ "from HRMS_D1_CLASS_ENROLL"
				+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CLASS_ENROLL.EMP_ID)"
				+ "	left join HRMS_D1_CLASS_REQUEST on(HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID = HRMS_D1_CLASS_ENROLL.COURSE_ID)"
				+ "	where HRMS_D1_CLASS_ENROLL.STATUS  = 'X' AND HRMS_D1_CLASS_ENROLL.CLASS_INITIATOR =" +userId 
				+ "	ORDER BY HRMS_D1_CLASS_ENROLL.APPLICAION_DATE DESC ";
			
			
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
					ClassEnrollmentForm beanItt = new ClassEnrollmentForm();
					beanItt.setEnrollmentHiddenID(checkNull(String
							.valueOf(approvedCancellationListData[i][0])));
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedCancellationListData[i][1])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(approvedCancellationListData[i][3])));
					beanItt.setApplDate(checkNull(String
							.valueOf(approvedCancellationListData[i][4])));
					beanItt.setCourseId(checkNull(String
							.valueOf(approvedCancellationListData[i][6])));
					beanItt.setStatus(checkNull(String
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

	public void getRejectedList(ClassEnrollmentForm bean,
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
				
			String selQuery = "select ENROLL_ID,HRMS_D1_CLASS_ENROLL.TRACKING_NUMBER,HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "to_CHAR(APPLICAION_DATE,'DD-MM-YYYY'), COURSE_ID ,HRMS_D1_CLASS_REQUEST.CLASS_NAME ,STATUS "
				+ "from HRMS_D1_CLASS_ENROLL"
				+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CLASS_ENROLL.EMP_ID)"
				+ "	left join HRMS_D1_CLASS_REQUEST on(HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID = HRMS_D1_CLASS_ENROLL.COURSE_ID)"
				+ "	where HRMS_D1_CLASS_ENROLL.STATUS  = 'R' AND HRMS_D1_CLASS_ENROLL.CLASS_INITIATOR =" +userId 
				+ "	ORDER BY HRMS_D1_CLASS_ENROLL.APPLICAION_DATE DESC ";
			
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
					ClassEnrollmentForm beanItt = new ClassEnrollmentForm();
					beanItt.setEnrollmentHiddenID(checkNull(String
							.valueOf(rejectedListData[i][0])));
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedListData[i][1])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedListData[i][3])));
					beanItt.setApplDate(checkNull(String
							.valueOf(rejectedListData[i][4])));
					beanItt.setCourseId(checkNull(String
							.valueOf(rejectedListData[i][6])));
					beanItt.setStatus(checkNull(String
							.valueOf(rejectedListData[i][7])));
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
			
			String approvedcancellationQuery = "select ENROLL_ID,HRMS_D1_CLASS_ENROLL.TRACKING_NUMBER,HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "to_CHAR(APPLICAION_DATE,'DD-MM-YYYY'), COURSE_ID ,HRMS_D1_CLASS_REQUEST.CLASS_NAME ,STATUS "
				+ "from HRMS_D1_CLASS_ENROLL"
				+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CLASS_ENROLL.EMP_ID)"
				+ "	left join HRMS_D1_CLASS_REQUEST on(HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID = HRMS_D1_CLASS_ENROLL.COURSE_ID)"
				+ "	where HRMS_D1_CLASS_ENROLL.STATUS  = 'Z' AND HRMS_D1_CLASS_ENROLL.CLASS_INITIATOR =" +userId 
				+ "	ORDER BY HRMS_D1_CLASS_ENROLL.APPLICAION_DATE DESC ";
			
			
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
					ClassEnrollmentForm beanItt = new ClassEnrollmentForm();
					beanItt.setEnrollmentHiddenID(checkNull(String
							.valueOf(rejectedListData[i][0])));
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedListData[i][1])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedListData[i][3])));
					beanItt.setApplDate(checkNull(String
							.valueOf(rejectedListData[i][4])));
					beanItt.setCourseId(checkNull(String
							.valueOf(rejectedListData[i][6])));
					beanItt.setStatus(checkNull(String
							.valueOf(rejectedListData[i][7])));
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
		
	public void getApproverCommentList(ClassEnrollmentForm bean, String applicationId) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, ENRL_COMNTS,"
									+ " TO_CHAR(ENRL_APPRD_DATE, 'DD-MM-YYYY') AS ENRL_APPRD_DATE,"
									+ "  DECODE(ENRL_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS "
									+ "  FROM HRMS_D1_CLAS_ENRL_DATA_PATH "
									+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_CLAS_ENRL_DATA_PATH.ENRL_APPR)"
									+ "  WHERE ENRL_APPL_ID = " + applicationId + " ORDER BY ENRL_PATH_ID DESC";
		
		Object[][] apprCommentListObj = getSqlModel().getSingleResult(apprCommentListSql);
		ArrayList approverList = new ArrayList<Object>(apprCommentListObj.length);
		if(apprCommentListObj !=null && apprCommentListObj.length>0)
		{
			bean.setApproverCommentFlag(true);
			for(int i = 0; i < apprCommentListObj.length; i++) {
				ClassEnrollmentForm innerBean = new ClassEnrollmentForm();
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