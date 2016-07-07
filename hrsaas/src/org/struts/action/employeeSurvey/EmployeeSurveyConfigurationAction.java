/**
 * 
 */
package org.struts.action.employeeSurvey;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.employeeSurvey.EmployeeSurveyConfiguration;
import org.paradyne.model.employeeSurvey.EmployeeSurveyConfigurationModel;
import org.paradyne.model.leave.LeaveEncashmentProcessModel;
import org.struts.action.leave.LeaveEncashmentProcessAction;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0491
 *
 */
public class EmployeeSurveyConfigurationAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeeSurveyConfigurationAction.class);

	EmployeeSurveyConfiguration empSurveyConfig;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		empSurveyConfig = new EmployeeSurveyConfiguration();
		empSurveyConfig.setMenuCode(1064);
	}

	public String input() throws Exception {
		try {
			reset();
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);
			model.callList(empSurveyConfig, request);
			model.terminate();
			getNavigationPanel(1);
			empSurveyConfig.setEnableAll("Y");

		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public String callforedit() throws Exception {
		try {
			System.out.println("hidden code============="
					+ empSurveyConfig.getHiddencode());
			getNavigationPanel(4);
			String autoCode = request.getParameter("autoCode");

			System.out.println("autoCode      " + autoCode);
			empSurveyConfig.setSurveyCode(autoCode);
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);
			getDtlRecord();
			empSurveyConfig.setListFlag("false");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";

	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			empSurveyConfig.setListFlag("false");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return empSurveyConfig;
	}

	public EmployeeSurveyConfiguration getEmpSurveyConfig() {
		return empSurveyConfig;
	}

	public void setEmpSurveyConfig(EmployeeSurveyConfiguration empSurveyConfig) {
		this.empSurveyConfig = empSurveyConfig;
	}

	public String publish() {
		try {
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);
			boolean result = model.publish(empSurveyConfig);
			if (result) {
				addActionMessage("Record publish successfully.");
				model.setSavedDtlRecord(empSurveyConfig, request);
				getNavigationPanel(4);
			} else {
				addActionMessage("Record publish successfully.");
				model.setSavedDtlRecord(empSurveyConfig, request);
				getNavigationPanel(4);
			}
			empSurveyConfig.setShowFlag("true");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String getEmployeeList() {
		try {
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);
			model.setSavedDtlRecord(empSurveyConfig, request);
			empSurveyConfig.setShowFlag("true");
			getNavigationPanel(4);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String publishWithMailAlert() {
		try {
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);
			String empCode[] = request.getParameterValues("employeeId");
			model.setSavedDtlRecord(empSurveyConfig, request);
			empSurveyConfig.setShowFlag("true");
			getNavigationPanel(4);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "mailMessageJsp";
	}

	public String sendMailForSurvey() {
		try {
			 
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);
  
			boolean result = model.publishWithMailAlert(empSurveyConfig);
			if (result) {
				addActionMessage("Record publish successfully and mail sent to employee.");
				model.setSavedDtlRecord(empSurveyConfig, request);
				getNavigationPanel(4);
			} else {
				addActionMessage("Record can't be publish ");
				model.setSavedDtlRecord(empSurveyConfig, request);
				getNavigationPanel(4);
			}
			empSurveyConfig.setShowFlag("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailMessageJsp";
	}

	public String save() {
		try {
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);

			String empCode[] = request.getParameterValues("employeeId");

			boolean result = model.saveRecord(empSurveyConfig, empCode);

			if (result) {
				model.setSavedDtlRecord(empSurveyConfig, request);
				addActionMessage("Record saved successfully");
				getNavigationPanel(4);

			} else {
				model.setSavedDtlRecord(empSurveyConfig, request);
				addActionMessage("Record can't be saved");
				getNavigationPanel(4);
			}

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String addEmployee() {
		try {
			logger.info("in addEmployee-------------------------");
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);

			String[] srNo = request.getParameterValues("srNo");
			String[] empToken = request.getParameterValues("empToken");
			String[] employeeId = request.getParameterValues("employeeId");
			String[] empName = request.getParameterValues("empName");

			boolean result = false;
			/* 
			 result = model.setNewAddedEmployee(srNo, empToken, employeeId,
					empName, empSurveyConfig);*/

			result = model.saveAddEmployee(empSurveyConfig);

			if (result) {
				addActionMessage("Record added successfully");
				model.setSavedDtlRecord(empSurveyConfig, request);
				getNavigationPanel(4);

			} else {
				addActionMessage("No data to process");
				getNavigationPanel(4);
			}
			empSurveyConfig.setShowFlag("true");
			empSurveyConfig.setEmployeeToken("");
			empSurveyConfig.setEmployeeCode("");
			empSurveyConfig.setEmployeeName("");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public void displayIttValues() {
		try {
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);
			String[] srNo = request.getParameterValues("srNo");
			String[] empToken = request.getParameterValues("empToken");
			String[] employeeId = request.getParameterValues("employeeId");
			String[] empName = request.getParameterValues("empName");
			model.displayIttValues(srNo, empToken, employeeId, empName,
					empSurveyConfig);

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String process() {
		try {
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);
			String[] employeeId = request.getParameterValues("employeeId");
			boolean result = model.setFilterList(employeeId, empSurveyConfig);
			if (result) {
				addActionMessage("Data processed successfully");
				model.setSavedDtlRecord(empSurveyConfig, request);
				empSurveyConfig.setShowFlag("true");
				getNavigationPanel(4);
			} else {
				addActionMessage("There is no data to process");
				getNavigationPanel(4);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String delete() {
		try {
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);

			boolean result = model.delete(empSurveyConfig);
			if (result) {
				addActionMessage("Record deleted successfully");
				reset();
				getNavigationPanel(7);
			} else {
				addActionMessage("Record can't be deleted");
				getNavigationPanel(7);
			}
			input();
			empSurveyConfig.setListFlag("true");
			empSurveyConfig.setEnableAll("Y");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;
	}

	public String deleteData() {

		try {
			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);
			boolean result = model.deleteData(empSurveyConfig);
			if (result) {
				addActionMessage("Record deleted successfully");
			} else {
				addActionMessage("Record can not be deleted");
			}
			model.setSavedDtlRecord(empSurveyConfig, request);
			empSurveyConfig.setHiddenEdit("");
			empSurveyConfig.setShowFlag("true");
			getNavigationPanel(4);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String reset() {
		try {

			empSurveyConfig.setSurveyCode("");
			empSurveyConfig.setSurveyName("");
			empSurveyConfig.setDivCode("");
			empSurveyConfig.setDivName("");
			empSurveyConfig.setDeptCode("");
			empSurveyConfig.setDeptName("");
			empSurveyConfig.setBranchCode("");
			empSurveyConfig.setBranchName("");
			empSurveyConfig.setDesgCode("");
			empSurveyConfig.setDesgName("");

			empSurveyConfig.setEmployeeId("");
			empSurveyConfig.setEmployeeName("");
			empSurveyConfig.setEmployeeToken("");

			getNavigationPanel(2);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;
	}

	public String getDtlRecord() {
		try {

			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);
			model.setSavedDtlRecord(empSurveyConfig, request);
			getNavigationPanel(4);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String getSurveyRecord() {
		try {

			EmployeeSurveyConfigurationModel model = new EmployeeSurveyConfigurationModel();
			model.initiate(context, session);
			model.setSavedDtlRecord(empSurveyConfig, request);
			getNavigationPanel(2);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING BRANCH
	 * @return String
	 * @throws Exception
	 */
	public String f9survey() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//

		String query = " SELECT SURVEY_CODE,NVL(SURVEY_NAME,'') FROM HRMS_EMPSURVEY_MASTER ORDER BY SURVEY_CODE ";

		// SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *

		String[] headers = { getMessage("emp.surveyCode"),
				getMessage("emp.survey") };

		//DEFINE THE PERCENT WIDTH OF EACH COLUMN

		String[] headerWidth = { "20", "80" };

		// -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		// ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		// -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		// INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		// FIELDNAMES
		//

		String[] fieldNames = { "surveyCode", "surveyName" };

		// SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		// CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		// IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}

		//NOTE: COLUMN NUMBERS STARTS WITH 0

		int[] columnIndex = { 0, 1 };

		// WHEN SET TO 'true' WILL SUBMIT THE FORM

		String submitFlag = "true";

		// IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		// FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		// ACTION>_<METHOD TO CALL>.action

		String submitToMethod = "EmployeeSurveyConfiguration_getSurveyRecord.action";

		// CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9center	

	/**
	 * THIS METHOD IS USED FOR SELECTING DEVISION
	 * @return String
	 * @throws Exception
	 */

	public String f9division() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DIV_ID,DIV_NAME" + "	FROM HRMS_DIVISION  ";

		if (empSurveyConfig.getUserProfileDivision() != null
				&& empSurveyConfig.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN ("
					+ empSurveyConfig.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("division.code"),
				getMessage("division") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divCode", "divName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9div

	/**
	 * THIS METHOD IS USED FOR SELECTING DEPARTMENT
	 * @return String
	 * @throws Exception
	 */
	public String f9department() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DEPT_ID,DEPT_NAME FROM HRMS_DEPT ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("department.code"),
				getMessage("department") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptCode", "deptName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9dept

	/**
	 * THIS METHOD IS USED FOR SELECTING DESIGANTION
	 * @return String
	 * @throws Exception
	 */
	public String f9designation() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  RANK_ID,RANK_NAME" + "	FROM HRMS_RANK  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("designation.code"),
				getMessage("designation") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "desgCode", "desgName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9desg

	/**
	 * THIS METHOD IS USED FOR SELECTING BRANCH
	 * @return String
	 * @throws Exception
	 */
	public String f9branch() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//

		String query = " SELECT   CENTER_ID , center_name FROM HRMS_CENTER ORDER BY CENTER_ID ";

		// SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *

		String[] headers = { getMessage("branch.code"), getMessage("branch") };

		//DEFINE THE PERCENT WIDTH OF EACH COLUMN

		String[] headerWidth = { "20", "80" };

		// -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		// ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		// -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		// INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		// FIELDNAMES
		//

		String[] fieldNames = { "branchCode", "branchName" };

		// SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		// CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		// IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}

		//NOTE: COLUMN NUMBERS STARTS WITH 0

		int[] columnIndex = { 0, 1 };

		// WHEN SET TO 'true' WILL SUBMIT THE FORM

		String submitFlag = "false";

		// IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		// FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		// ACTION>_<METHOD TO CALL>.action

		String submitToMethod = "";

		// CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9center	

	public String f9employee() throws Exception {
		//  to remove an already selected employee from the search list
		String[] empCode = request.getParameterValues("employeeId");
		String str = "0";
		if (empCode != null) {
			for (int i = 0; i < empCode.length; i++) {// loop x
				str += "," + empCode[i];
			}// end loop x
		}// end if
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
				+ " FROM HRMS_EMP_OFFC ";
		query += " WHERE EMP_STATUS='S' AND EMP_ID NOT IN(" + str + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "employeeToken", "employeeName", "employeeCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9employee method

	public String f9searchAction() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT HRMS_EMPSURVEY_CONFIG.SURVEY_CODE,NVL(SURVEY_NAME,'') "
				+ " FROM HRMS_EMPSURVEY_CONFIG "
				+ " INNER JOIN HRMS_EMPSURVEY_MASTER ON(HRMS_EMPSURVEY_MASTER.SURVEY_CODE=HRMS_EMPSURVEY_CONFIG.SURVEY_CODE )"
				+ "  order by HRMS_EMPSURVEY_CONFIG.SURVEY_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("emp.surveyCode"),
				getMessage("emp.survey") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "surveyCode", "surveyName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */

		int[] columnIndex = { 0, 1 };
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
		String submitToMethod = "EmployeeSurveyConfiguration_getDtlRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
