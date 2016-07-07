package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.RequestBusinessCardMisReport;
import org.paradyne.model.admin.srd.RequestBusinessCardMisReportModel;
import org.struts.lib.ParaActionSupport;
/**
 * 
 * @author Priyanka.Kumbhar
 *
 */
public class RequestBusinessCardMisReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RequestBusinessCardMisReportAction.class);

	RequestBusinessCardMisReport businessReport;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		businessReport = new RequestBusinessCardMisReport();
		businessReport.setMenuCode(5054);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return businessReport;
	}

	public RequestBusinessCardMisReport getFamilyreport() {
		return businessReport;
	}

	public void setFamilyreport(RequestBusinessCardMisReport familyreport) {
		this.businessReport = familyreport;
	}

	public String report() {
		RequestBusinessCardMisReportModel model = new RequestBusinessCardMisReportModel();
		model.initiate(context, session);
		String result = model.getReport(businessReport, response);
		model.terminate();
		clear();
		return null;
	}

	// Generate Report using iVreport library
	public String businessReport() throws Exception {
		RequestBusinessCardMisReportModel model = new RequestBusinessCardMisReportModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getFamilyMISReport(businessReport, request, response, reportPath);
		model.terminate();
		return null;
	}

	// To generate Mail Report

	public final String mailReport() {
		try {
			final RequestBusinessCardMisReportModel model = new RequestBusinessCardMisReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			String reportPath = getText("data_path") + "\\Report\\Master"
					+ poolName + "\\";
			model.getFamilyMISReport(businessReport, request, response,
					reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

	public String clear() {

		businessReport.setEmpid("");
		businessReport.setEmpName("");

		businessReport.setDesgCode("");
		businessReport.setDesgName("");
		businessReport.setCenterId("");
		businessReport.setCenterName("");

		businessReport.setToken("");
		businessReport.setStatus("");
		businessReport.setDeptName("");
		businessReport.setDeptCode("");

		businessReport.setDivCode("");
		businessReport.setDivsion("");
		businessReport.setReportType("");
		return "success";
	}

	public String f9MultiDiv() {
		try {
			String query = " SELECT " + " 	DISTINCT DIV_ID, " + "		DIV_NAME "
					+ " FROM " + " 	HRMS_DIVISION WHERE IS_ACTIVE='Y'";

			if (this.businessReport.getUserProfileDivision() != null
					&& this.businessReport.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN ("
						+ this.businessReport.getUserProfileDivision() + ")";
			query += " ORDER BY  DIV_ID ";

			String header = getMessage("division");
			String textAreaId = "paraFrm_divsion";

			String hiddenFieldId = "paraFrm_divCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in FamilyReportAction.f9MultiDiv() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	/**
	 * Method to select the Branch
	 */

	public String f9Brch() {
		try {
			String query = " SELECT " + " 	DISTINCT CENTER_ID ,"
					+ "		CENTER_NAME " + " FROM " + " 	HRMS_CENTER  WHERE IS_ACTIVE='Y' "
					+ " ORDER BY " + "		UPPER (CENTER_NAME)";

			String header = getMessage("branch");
			String textAreaId = "paraFrm_centerName";

			String hiddenFieldId = "paraFrm_centerId";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";
		} catch (Exception e) {
			logger
					.error("Error in FamilyReportAction.f9Brch() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9MultiDept() {
		try {
			String query = " SELECT " + "		DISTINCT DEPT_ID," + "		DEPT_NAME "
					+ "	FROM " + " 	HRMS_DEPT WHERE IS_ACTIVE='Y' " + " ORDER BY "
					+ "		UPPER (DEPT_NAME) ";

			String header = getMessage("department");
			String textAreaId = "paraFrm_deptName";

			String hiddenFieldId = "paraFrm_deptCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger.error("Error in FamilyReport.f9MultiDept() method Action : "
					+ e.getMessage());
			return "";
		}
	}

	public String f9MultiRank() {
		try {
			String query = " SELECT " + "		DISTINCT RANK_ID," + "		RANK_NAME "
					+ "	FROM " + " 	HRMS_RANK WHERE IS_ACTIVE='Y'" + " ORDER BY "
					+ "		UPPER (RANK_NAME)" ;

			String header = getMessage("designation");
			String textAreaId = "paraFrm_desgName";

			String hiddenFieldId = "paraFrm_desgCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger.error("Error in FamilyReport.f9MultiRank() method Action : "
					+ e.getMessage());
			return "";
		}
	}

	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */

		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION WHERE IS_ACTIVE='Y' ";

		if (businessReport.getUserProfileDivision() != null
				&& businessReport.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN ("
					+ businessReport.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Division Code", getMessage("division") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divCode", "divsion" };

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
	}// end of f9div

	/**
	 * THIS METHOD IS USED FOR SELECTING DEPARTMENT
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9dept() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DEPT_ID,DEPT_NAME FROM HRMS_DEPT WHERE IS_ACTIVE='Y'";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Department Id", getMessage("department") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

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
	}// end of f9dept

	/**
	 * THIS METHOD IS USED FOR SELECTING DESIGNATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9desg() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  RANK_ID,RANK_NAME" + "	FROM HRMS_RANK WHERE IS_ACTIVE='Y' ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "designation Id", getMessage("designation") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

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
	}// end of f9desg

	/**
	 * THIS METHOD IS USED OFR SELECTING BRANCH
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9center() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//

		String query = " SELECT   CENTER_ID , CENTER_NAME FROM HRMS_CENTER WHERE IS_ACTIVE='Y' ORDER BY CENTER_ID ";

		// SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *

		String[] headers = { "Branch Id", getMessage("branch") };

		// DEFINE THE PERCENT WIDTH OF EACH COLUMN

		String[] headerWidth = { "40", "60" };

		// -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		// ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		// -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		// INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		// FIELDNAMES
		//

		String[] fieldNames = { "centerId", "centerName" };

		// SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		// CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		// IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}

		// NOTE: COLUMN NUMBERS STARTS WITH 0

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
	}// end of f9center

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				+ "  EMP_ID FROM HRMS_EMP_OFFC";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Employee Id", getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "token", "empName", "empid" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
