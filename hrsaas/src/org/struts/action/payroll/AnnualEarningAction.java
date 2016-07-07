package org.struts.action.payroll;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.payroll.AnnualEarningRep;
import org.paradyne.model.payroll.AnnualEarningModel;
/**
 * @author Pradeep Kumar Sahoo
 * Date:01-11-2008
 * Modified by Prashant on 30 Apr 2012
 */
public class AnnualEarningAction extends ParaActionSupport  {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AnnualEarningAction.class);
	AnnualEarningRep annualEarning;

	public final void prepare_local() throws Exception {
		annualEarning = new AnnualEarningRep();
		annualEarning.setMenuCode(693);
	}

	public final Object getModel() {
		return annualEarning;
	}

	/**This function is used to select the record from the search screen.
	 * @return string - result type
	 * @throws Exception - e
	 */
	public final String f9desg() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DISTINCT RANK_ID,TO_CHAR(RANK_NAME) FROM HRMS_RANK ORDER BY  RANK_ID ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		*/
		String[] headers = {getMessage("designation.code"), getMessage("designation")};

		String[] headerWidth = {"20", "80"};
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 */	
		String[] fieldNames = {"desgId", "desgName"};
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 *
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";
		/**
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		*/
		String submitToMethod = "";
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 */ 
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	/**This function is used to select the record from the search screen.
	 * @return string - result type
	 * @throws Exception - e
	 */
	public final String f9div() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DISTINCT DIV_ID, TO_CHAR(DIV_NAME) FROM  HRMS_DIVISION ";
			if (annualEarning.getUserProfileDivision() != null && annualEarning.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN (" + annualEarning.getUserProfileDivision() + ")";
			}
				query += " ORDER BY  DIV_ID ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		*/
		String[] headers = {getMessage("division.code"), getMessage("division")};
		String[] headerWidth = {"20", "80"};
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 */	
		String[] fieldNames = {"divId", "divName"};
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1, 3}
		 *
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 */
		int[] columnIndex = {0, 1};
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";
		/**
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		*/
		String submitToMethod = "";
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 */ 
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	/**This function is used to select the record from the search screen.
	 * @return string - result type
	 * @throws Exception - e
	 */
	public final String f9type() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("employee.type")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"typeName", "typeId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1, 3}
		 *
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 *
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 *
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true', THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		logger.info("4");
		return "f9page";
	}

	/**This function is used to select the record from the search screen.
	 * @return string - result type
	 * @throws Exception - e
	 */
	public final String f9dept() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY  DEPT_ID ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.
		 */
		String[] headers = {getMessage("department.code"), getMessage("department")};
		String[] headerWidth = {"30", "30"};
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"deptId", "deptName"};
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1, 3}
		 *
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 *
		 */
		int[] columnIndex = {0, 1};
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 *
		 */
		String submitFlag = "false";
		/**
		 * 		IF THE 'submitFlag' IS 'true', THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	/**This function is used to select the record from the search screen.
	 * @return string - result type
	 * @throws Exception - e
	 */
	public final String f9branch() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY  CENTER_ID ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = {getMessage("branch.code"), getMessage("branch")};
		String[] headerWidth = {"30", "30"};
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 */
		String[] fieldNames = {"brnId", "brnName"};
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1, 3}
		 *
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0
		 *
		 */
		int[] columnIndex = {0, 1};
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 *
		 */
		String submitFlag = "false";
		/**
		 * 		IF THE 'submitFlag' IS 'true', THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	/** This function generates the report.
	 * @return void
	 */
	public final void report() {
		try {
			AnnualEarningModel model = new AnnualEarningModel();
			model.initiate(context, session);
			String reportPath = "";
			model.generateReport(annualEarning, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**This function generates the report as an email attachment.
	 * @return void
	 */
	public final String mailReport() {
		try {
			AnnualEarningModel model = new AnnualEarningModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.generateReport(annualEarning, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
}
