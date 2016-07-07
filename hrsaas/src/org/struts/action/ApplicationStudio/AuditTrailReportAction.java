package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.AuditTrailReport;
import org.paradyne.model.ApplicationStudio.AuditTrailReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author Vishwambhar Deshpande
 *
 */

public class AuditTrailReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AuditTrailReportAction.class);

	AuditTrailReport auditTrail;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		auditTrail = new AuditTrailReport();
		auditTrail.setMenuCode(894);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return auditTrail;
	}

	public AuditTrailReport getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(AuditTrailReport auditTrail) {
		this.auditTrail = auditTrail;
	}

	public String report() {
		try {
			AuditTrailReportModel model = new AuditTrailReportModel();
			model.initiate(context, session);
			model.getReport(auditTrail, response);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in report------------------" + e);
		}
		return null;
	}

	public String clear() {
		auditTrail.setFromDate("");
		auditTrail.setToDate("");
		auditTrail.setReportType("");
		auditTrail.setModuleName("");
		auditTrail.setDivCode("");
		auditTrail.setDivName("");
		auditTrail.setDeptCode("");
		auditTrail.setDeptName("");
		auditTrail.setBranchCode("");
		auditTrail.setBranchName("");
		auditTrail.setDesgCode("");
		auditTrail.setDesgName("");
		auditTrail.setEmptokenNo("");
		auditTrail.setEmployeeName("");
		auditTrail.setEmployeeCode("");
		auditTrail.setEmpName("");
		auditTrail.setEmpCode("");
		auditTrail.setTokenNo("");
		auditTrail.setStatus("");
		return SUCCESS;
	}

	/**
	 * THIS METHOD IS USED FOR RESETTING RECORD
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String chk() throws Exception {

		auditTrail.setDivCode("");
		auditTrail.setDivName("");
		auditTrail.setDeptCode("");
		auditTrail.setDeptName("");
		auditTrail.setBranchCode("");
		auditTrail.setBranchName("");
		auditTrail.setDesgCode("");
		auditTrail.setDesgName("");

		return SUCCESS;
	}// end of chk

	/**
	 * Method to select the Division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9divaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME,'','','','','','' FROM HRMS_DIVISION ";
		
		if(auditTrail.getUserProfileDivision() !=null && auditTrail.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+auditTrail.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
			 

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Division Code", "Division" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		 
		String[] fieldNames = { "divCode", "divName","tokenNo","employeeName","employeeCode", "emptokenNo", "empName", "empCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1,2,3,4,5,6,7 };

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

	/**
	 * Method to select the department. *
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9deptaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DEPT_ID,DEPT_NAME,'','','','','','' FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Department Code", "Department"};


		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptCode", "deptName" ,"tokenNo","employeeName","employeeCode", "emptokenNo", "empName", "empCode" };


		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1,2,3,4,5,6,7 };

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

	/**
	 * Method to select the employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9moduleName() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT TRAIL_MODULENAME  FROM HRMS_AUDIT_TRAIL_REF ORDER BY TRAIL_MODULENAME ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Form Name"};


		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "moduleName"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0 };

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

	/**
	 * Method to select the Center
	 * 
	 * @return Center
	 * @throws Exception
	 */
	public String f9branchaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CENTER_ID,CENTER_NAME,'','','','','','' FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Branch Code", "Branch" };


		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "branchCode", "branchName","tokenNo","employeeName","employeeCode", "emptokenNo", "empName", "empCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 ,2,3,4,5,6,7};

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

	/**
	 * Method to select the designation of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9desigantionaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT RANK_ID,TO_CHAR(RANK_NAME),'','','','','','' FROM  HRMS_RANK  "
				+ " ORDER BY  RANK_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Designation Code", " Designation" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "desgCode", "desgName" ,"tokenNo","employeeName","employeeCode", "emptokenNo", "empName", "empCode" };


		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */

		int[] columnIndex = { 0, 1,2,3,4,5,6,7 };

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
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * To select any employee
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9employee() throws Exception {

		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
				+ " FROM HRMS_EMP_OFFC  ";
				
				query += getprofileQuery(auditTrail);
				 query +=" AND EMP_STATUS='S'";
				query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
				
				 

		String[] headers = { "Employee Id", "Employee" };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "tokenNo", "employeeName", "employeeCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "AuditTrailReport_chk.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9employee method

	/**
	 * To select any employee
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9employeeaction() throws Exception {

		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
				+ " FROM HRMS_EMP_OFFC  ";
				query += getprofileQuery(auditTrail);
				 query +=" AND EMP_STATUS='S'";
				query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
				

		String[] headers = { "Employee Id", "Employee" };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "emptokenNo", "empName", "empCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "AuditTrailReport_chk.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9employee method

}
