package org.struts.action.common;

import org.paradyne.bean.common.*;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.UserModel;


@SuppressWarnings("serial")
public class UserAction extends org.struts.lib.ParaActionSupport {
	UserBean userBean = null;

	public Object getModel() {
		logger.info("i am in get Model");
		return userBean;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		userBean = new UserBean();
		userBean.setMenuCode(5);
	}

	public org.paradyne.bean.common.UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(org.paradyne.bean.common.UserBean userBean) {
		logger.info("i am in  setMaster");
		this.userBean = userBean;
	}

	/** Setting Lock Flag 
	 * @return String
	 * @throws Exception*/
	public String getData() throws Exception {
		userBean.setLockFlag(userBean.getHiddenLockStatus());
		isAccessProfile();
		profile();
		return "successUser";
	}

	public String profileData() {
		profile();
		return "successUser";
	}

	public String profile() {
		userBean.setUserFlag(true);
		String loginCode = userBean.getLoginCode();
		String getName="";
		UserModel model = new UserModel();
		model.initiate(context, session);
		String query="SELECT HRMS_LOGIN.LOGIN_PROFILE FROM HRMS_LOGIN WHERE HRMS_LOGIN.LOGIN_CODE=" +loginCode;
		Object[][] codeObj = model.getSqlModel().getSingleResult(query);
		String code="";
		if(codeObj != null && codeObj.length > 0){
			code=String.valueOf(codeObj[0][0]);
		}		
		String selQuery="SELECT PROFILE_CODE,PROFILE_NAME FROM  HRMS_PROFILE_HDR";
		Object[][]obj= model.getSqlModel().getSingleResult(selQuery);
		if(!code.equals("null")){
		getName=Utility.getNameByKey(obj, code);
		}
		userBean.setUserProfileID(code);
		userBean.setUserProfile(getName);
		userBean.setLogin_profile_ID(code);
		userBean.setLogin_profile(getName);
		model.terminate();
		return "successUser";
	}

	/**
	 * Reset JSP fields 
	 * @return String
	 */
	public String reset() {
		logger.info("im in  reset");
		userBean.setUserActive("Y");
		userBean.setUserEmpID("");
		userBean.setUserName("");
		userBean.setUserPassword("");
		userBean.setUserProfile("");
		userBean.setUserProfileID("");
		userBean.setUserToken("");
		userBean.setLoginCode("");
		userBean.setUserEmpName("");
		userBean.setLockFlag("false");
		userBean.setHiddenLockStatus("");
		userBean.setAccessProfile("");
		userBean.setAccessProfileId("");
		userBean.setLogin_profile_ID("");
		userBean.setLogin_profile("");
		userBean.setLogin_access_profile("");
		userBean.setLogin_access_profile_ID("");
		userBean.setValidUpto("");
		userBean.setUserFlag(true);
		return "successUser";
	}

	/**
	 * Deleting any record
	 * @return String
	 */

	public String delete() {
		UserModel userModel = new UserModel();
		userModel.initiate(context, session);
		boolean result = false;
		if (userBean.getUserEmpID().equals("")) {
			addActionMessage("Please select record");
		}// END if
		else if (!(userBean.getUserEmpID().equals(""))) {
			result = userModel.deleteUser(userBean);
			if (result) {
				reset();
				logger.info(" Employee deleted ");
				addActionMessage(getMessage("delete"));
			}// END if
		} // END else if
		else {
			logger.info(" Employee can not be deleted ");
			addActionMessage(getMessage("del.error"));
		}// END else
		userModel.terminate();
		return "successUser";
	}

	/**
	 * Save a record
	 * @return String
	 */
	public String submit() {
		logger.info("im in  submit");
		UserModel userModel = new UserModel();
		userModel.initiate(context, session);
		boolean result = false;
		try {
			String password = userBean.getUserPassword();
			// Checking password with filters under password settings
			String resultMsg = userModel.checkPasswordSettings(password);
			logger
					.info("Result Msg Is --------------------------------------- "
							+ resultMsg);
			if (!resultMsg.equals("")) {
				addActionMessage(resultMsg);
				return "successUser";
			}// END if
			if (userBean.getLoginCode().equals("")) {
				logger.info("Inside if not Exist");
				// call to model for save
				result = userModel.addUser(userBean);
				if (result) {
					logger.info(" User can be added/modified ");
					reset();
					addActionMessage(getMessage("save"));
				}// END nested if
				else {
					logger.info(getMessage("save.error"));
					addActionMessage("Please choose different user name! This user name already exist.");
					return "successUser";
				}// END else
			} // END if
			else {
				int returnValue = userModel.modUser(userBean, request);
				if (returnValue == 1) {
					reset();
					addActionMessage(getMessage("update"));
				}// END if
				/* checking user-name uniqueness */
				else if 
					(returnValue == 2){
					addActionMessage("Please choose different user name! This user name already exist.");
				return "successUser";
				}
				/* checking password re-usability */
				else if (returnValue == 3) {
					if ((Integer) request.getAttribute("reusePeriodicity") == 1)
						addActionMessage("Previous password cannot be reused. Please select different password!");
					else
						addActionMessage("Previous "
								+ request.getAttribute("reusePeriodicity")
										.toString()
								+ " passwords cannot be reused. Please Select different Password!");
				}// END else if
			} // END else
			userModel.terminate();
		} catch (Exception e) {
			logger.error("Exception in user add/modify " + e);
		}
		return input();
	}

	/**
	 * Search a record
	 * 
	 * @return f9page
	 * @throws Exception
	 */
	public String f9Token() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		logger.info("--inside f9Token()--");
		String query = " SELECT EMP_TOKEN,TRIM(EMP_FNAME)||' '||TRIM(EMP_MNAME)||' '||TRIM(EMP_LNAME) AS NAME,"
				+ " LOGIN_NAME,HRMS_PROFILE_HDR.PROFILE_NAME,DECODE(LOCK_FLAG,'Y','true','false')," 
				+ " LOGIN_CODE,LOGIN_PASSWORD, DECODE(EMP_ACTIVE,'Y','YES','N','NO'),HRMS_LOGIN.LOGIN_PROFILE, "
				+ " HRMS_LOGIN.EMP_ID ,HRMS_PROFILE_ACC_HDR.PROFILE_NAME,"
				+ " HRMS_LOGIN.LOGIN_ACCESS_PROFILE  FROM HRMS_LOGIN "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_LOGIN.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
				+ " LEFT JOIN HRMS_PROFILE_HDR ON (HRMS_PROFILE_HDR.PROFILE_CODE = HRMS_LOGIN.LOGIN_PROFILE)"
				+ " LEFT JOIN HRMS_PROFILE_ACC_HDR ON (HRMS_PROFILE_ACC_HDR.PROFILE_CODE = HRMS_LOGIN.LOGIN_ACCESS_PROFILE) ";

		query += getprofileQuery(userBean);
		query += " AND EMP_STATUS='S'";
		query += "	ORDER BY UPPER(NAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("user.name"), getMessage("profile") };
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "40", "15", "30" };
		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "userBean.userToken", "userBean.userEmpName",
				"userBean.userName", "userBean.login_profile",
				"hiddenLockStatus", "loginCode", "userBean.userActive",
				"userBean.login_profile_ID", "userEmpID",
				"userBean.login_access_profile", "userBean.login_access_profile_ID" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11 };
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "User_getData.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		logger.info("4");
		return "f9page";
	}

	/**On Load Retrieving Password length from Password Settings*/
	public void prepare_withLoginProfileDetails() throws Exception {
		UserModel model = new UserModel();
		model.initiate(context, session);
		model.initialData(userBean, request);
		model.getPasswordLength(request);
		model.terminate();
	}

	/**Added by Nilesh D on 4th May 11**/
	public String input() {
		UserModel model = new UserModel();
		model.initiate(context, session);
		model.initialData(userBean, request);
		model.terminate();
		return "list";
	}

	/**Add New Button for List in Jsp*/
	public String addNew() {
		reset();
		userBean.setUserFlag(true);
		return "input";
	}

	public String callPage() throws Exception {
		UserModel model = new UserModel();
		model.initiate(context, session);
		input();
		model.terminate();
		return "list";
	}

	public String cancel() {
		input();
		reset();
		return "list";
	}

	public String editBusinessData() {
		try {
			UserModel model = new UserModel();
			model.initiate(context, session);
			userBean.setUserFlag(false);
			model.editBusinessData(userBean);
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "input";
	}

	/**following function is called when delete button is clicked in the jsp page*/

	public String deleteChkRecords() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		UserModel model = new UserModel();
		model.initiate(context, session);
		boolean result = model.deleteCheckedRecords(userBean, code);
		if (result) {
			addActionMessage(getText("delMessage", ""));
			reset();
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}
		model.initialData(userBean, request);
		model.terminate();
		return "list";
	}

	/**
	 * Search employee
	 * 
	 * @return f9page
	 * @throws Exception
	 */
	public String f9UserToken() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		logger.info("--inside f9Token()--");
		String query = " SELECT EMP_TOKEN,TRIM(EMP_FNAME)||' '||TRIM(EMP_MNAME)||' '||TRIM(EMP_LNAME) AS NAME,EMP_ID FROM HRMS_EMP_OFFC ";
		query += getprofileQuery(userBean);
		//query += " AND EMP_ID not in (SELECT LOGIN_CODE FROM HRMS_LOGIN )";
		query += " AND EMP_STATUS='S'";
		query += "	ORDER BY UPPER(NAME)";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "30", "70" };
		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "userBean.userToken", "userBean.userEmpName",
				"userBean.userEmpID" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1, 2 };
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		logger.info("4");
		return "f9page";
	}

	/**
	 * Search Profile 
	 * @return f9page
	 * @throws Exception
	 */
	public String f9profile() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		logger.info("--inside f9profile()--");
		String query = " SELECT PROFILE_NAME,PROFILE_CODE FROM HRMS_PROFILE_HDR ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("profile") };
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };
		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "userBean.userProfile",
				"userBean.userProfileID" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1 };
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM 
		 */
		String submitFlag = "false";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		//String submitToMethod = "User_profileData.action";
		String submitToMethod = "";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		logger.info("4");
		return "f9page";
	}

	/**
	 * Method for Access Profile
	 * 
	 * @return f9page
	 * @throws Exception
	 */
	public String f9accessProfile() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT PROFILE_NAME,PROFILE_CODE FROM HRMS_PROFILE_ACC_HDR "
				+ "	ORDER BY PROFILE_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("accessProfile") };
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };
		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "userBean.accessProfile",
				"userBean.accessProfileId" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * NOTE: COLUMN NUMBERS STARTS WITH 0 
		 */
		int[] columnIndex = { 0, 1 };
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String isAccessProfile() {
		userBean.setUserFlag(true);
		String loginCode = userBean.getLoginCode();
		String getName="";
		UserModel model = new UserModel();
		model.initiate(context, session);
		String query="SELECT HRMS_LOGIN.LOGIN_ACCESS_PROFILE FROM HRMS_LOGIN" 
                      +" WHERE HRMS_LOGIN.LOGIN_CODE=" + loginCode;		
		Object[][] accessCodeObj = model.getSqlModel().getSingleResult(query);	
		String accessCode="";
		if(accessCodeObj != null && accessCodeObj.length > 0){
			accessCode= String.valueOf(accessCodeObj[0][0]);
		}
		String selQuery= " SELECT HRMS_PROFILE_ACC_HDR.PROFILE_CODE , HRMS_PROFILE_ACC_HDR.PROFILE_NAME FROM HRMS_PROFILE_ACC_HDR";
		Object[][]obj= model.getSqlModel().getSingleResult(selQuery);
		System.out.println("accessCode----------"+accessCode);
		if(!accessCode.equals("null")){
			getName=Utility.getNameByKey(obj, accessCode);
		}
		userBean.setAccessProfileId(accessCode);
		userBean.setAccessProfile(getName);
		userBean.setLogin_access_profile_ID(accessCode);
		userBean.setLogin_access_profile(getName);
		model.terminate();
		return "successUser";
	}

	//Added by Nilesh D 
	public String f9User() throws Exception {
		String EmpID= request.getParameter("userBean.userEmpID");
		userBean.setUserEmpId(EmpID);
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		logger.info("--inside f9User()--");
		String query = " SELECT EMP_TOKEN,TRIM(EMP_FNAME)||' '||TRIM(EMP_MNAME)||' '||TRIM(EMP_LNAME) AS NAME,"
					   + " LOGIN_NAME, DECODE(LOCK_FLAG,'Y','true','false'),LOGIN_CODE,"
					   + " DECODE(EMP_ACTIVE,'Y','YES','N','NO'), TO_CHAR(HRMS_LOGIN.LOGIN_VALID_UPTO,'DD-MM-YYYY' ), "
					   + " HRMS_LOGIN.EMP_ID  FROM HRMS_LOGIN "
					   + " INNER JOIN HRMS_EMP_OFFC ON (HRMS_LOGIN.EMP_ID = HRMS_EMP_OFFC.EMP_ID) ";
		query += getprofileQuery(userBean);
		query += " AND EMP_STATUS='S'";
		query += "	ORDER BY UPPER(NAME)";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("user.name") };
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "40", "15", "30" };
		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "userBean.userToken", "userBean.userEmpName",
				"userBean.userName", "hiddenLockStatus","loginCode",
				  "userBean.userActive","userBean.validUpto","userBean.userEmpID"};
				 //"userBean.login_profile", "userBean.login_profile_ID"};
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7};
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM 
		 */
		String submitFlag = "true";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "User_getData.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		logger.info("4");
		return "f9page";
	}

	/** Function to Search Profile and Multi select Functionality*/
	public String f9Multiprofile() throws Exception {
		logger.info("--inside f9Multiprofile()--");
		String query = " SELECT PROFILE_CODE, PROFILE_NAME FROM HRMS_PROFILE_HDR ";
		String headers =getMessage("profile");
		String textAreaId ="paraFrm_userBean_login_profile";
		String hiddenFieldId ="paraFrm_userBean_login_profile_ID";
		String submitFlag = "";
		//String submitToMethod = "User_profileData.action";
		String submitToMethod = "";
		logger.info("1");
		setMultiSelectF9(query, headers, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		logger.info("4");
		return "f9multiSelect";
	}

	public String f9MultiaccessProfile() throws Exception {
		String query = " SELECT PROFILE_CODE,PROFILE_NAME FROM HRMS_PROFILE_ACC_HDR "
				+ "	ORDER BY PROFILE_CODE ";
		String headers = getMessage("accessProfile");
		String textAreaId ="paraFrm_userBean_login_access_profile";
		String hiddenFieldId ="paraFrm_userBean_login_access_profile_ID";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, headers, textAreaId, hiddenFieldId , submitFlag, submitToMethod);
		return "f9multiSelect";
	}
	
	public String callReport(){
		UserModel model = new UserModel();
		model.initiate(context, session);
		
		model.getReport(userBean,request,response);
		model.terminate();
		return null;
	}

	
}