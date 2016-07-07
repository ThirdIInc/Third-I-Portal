package org.struts.action.probation;

import org.paradyne.bean.probation.ProbationDueListReport;
import org.paradyne.model.probation.ProbationDueListReportModel;
import org.struts.lib.ParaActionSupport;

public class ProbationDueListReportAction extends ParaActionSupport {

	ProbationDueListReport probationDueListBean;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		probationDueListBean = new ProbationDueListReport();
		probationDueListBean.setMenuCode(1054);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return probationDueListBean;
	}

	public ProbationDueListReport getProbationDueListBean() {
		return probationDueListBean;
	}

	public void setProbationDueListBean(
			ProbationDueListReport probationDueListBean) {
		this.probationDueListBean = probationDueListBean;
	}
	
	
	public String report()
	{
		try {
			ProbationDueListReportModel model = new ProbationDueListReportModel();
			model.initiate(context, session);
			model.getReport(probationDueListBean,response);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null ;
	}
	
	public String clear()
	{
		try {
			probationDueListBean.setDivCode("");
			probationDueListBean.setDivision("");
			probationDueListBean.setDeptCode("");
			probationDueListBean.setDeptName("");
			probationDueListBean.setCenterNo("");
			probationDueListBean.setCenterName("");
			probationDueListBean.setTypeCode("");
			probationDueListBean.setEmpType("");
			probationDueListBean.setDesgCode("");
			probationDueListBean.setDesgName("");
			probationDueListBean.setFromDate("");
			probationDueListBean.setToDate("");
			probationDueListBean.setReportType("");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return SUCCESS ;
	}

	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DIV_ID,DIV_NAME" + "	FROM HRMS_DIVISION  ";

		if (probationDueListBean.getUserProfileDivision() != null
				&& probationDueListBean.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN ("
					+ probationDueListBean.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("division.code"),
				getMessage("division") };

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

		String[] fieldNames = { "divCode", "division" };

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
	public String f9dept() throws Exception {
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
		String[] headerWidth = {"30", "70" };

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
		String submitFlag = "";

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
	public String f9desg() throws Exception {
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
		String[] headerWidth = { "30", "70" };

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
		String submitFlag = "";

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
	public String f9center() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//

		String query = " SELECT   CENTER_ID , center_name FROM HRMS_CENTER ORDER BY CENTER_ID ";

		// SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *

		String[] headers = { getMessage("branch.code"), getMessage("branch") };

		//DEFINE THE PERCENT WIDTH OF EACH COLUMN

		String[] headerWidth = { "30", "70" };

		// -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		// ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		// -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		// INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		// FIELDNAMES
		//

		String[] fieldNames = { "centerNo", "centerName" };

		// SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		// CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		// IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}

		//NOTE: COLUMN NUMBERS STARTS WITH 0

		int[] columnIndex = { 0, 1 };

		// WHEN SET TO 'true' WILL SUBMIT THE FORM

		String submitFlag = "";

		// IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		// FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		// ACTION>_<METHOD TO CALL>.action

		String submitToMethod = "";

		// CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9center	

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE TYPE
	 * @return String
	 * @throws Exception
	 */
	public String f9type() throws Exception {

		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//
		String query = " SELECT    TYPE_ID,TYPE_NAME  FROM HRMS_EMP_TYPE ";

		// SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *

		String[] headers = { getMessage("typeid"), getMessage("employee.type") };

		// DEFINE THE PERCENT WIDTH OF EACH COLUMN

		String[] headerWidth = {"30", "70" };

		// -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		// ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		// -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		// INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		// FIELDNAMES

		String[] fieldNames = { "typeCode", "empType", };

		// SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		// CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		// IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}

		// NOTE: COLUMN NUMBERS STARTS WITH 0

		int[] columnIndex = { 0, 1 };

		// WHEN SET TO 'true' WILL SUBMIT THE FORM

		String submitFlag = "";

		// IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		// FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		// ACTION>_<METHOD TO CALL>.action

		String submitToMethod = "";

		// CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9type

}
