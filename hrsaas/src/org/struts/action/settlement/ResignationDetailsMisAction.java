package org.struts.action.settlement;


import org.paradyne.bean.settlement.ResignationDetailsMis;
import org.paradyne.model.settlement.ResignationDetailsMisModel;
import org.struts.lib.ParaActionSupport;

public class ResignationDetailsMisAction extends ParaActionSupport{

	ResignationDetailsMis resigMis;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		resigMis=new ResignationDetailsMis();
		resigMis.setMenuCode(902);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return resigMis;
	}

	public ResignationDetailsMis getResigMis() {
		return resigMis;
	}

	public void setResigMis(ResignationDetailsMis resigMis) {
		this.resigMis = resigMis;
	}
	
	/**
	 * Generates report
	 * @return String
	 */
	public String report()
	{
		
		ResignationDetailsMisModel  model=new ResignationDetailsMisModel();
		model.initiate(context, session);
		model.getReport(response,resigMis);
		model.terminate();
		return null;
	}//end of report
	
	/**
	 * Clears all jsp fields
	 * @return String
	 */
	public String reset()
	{
		resigMis.setEmpName("");
		resigMis.setEmpToken("");
		resigMis.setResigbranch("");
		resigMis.setResigbranchCode("");
		resigMis.setResigdept("");
		resigMis.setResigdeptCode("");
		resigMis.setResigdesg("");
		resigMis.setResigdesgCode("");
		resigMis.setResigdivision("");
		resigMis.setResigdivisionCode("");
		resigMis.setFrmDate("");
		resigMis.setToDate("");
		resigMis.setEmpCode("");
		return SUCCESS;
	}//end of reset
	
	/**
	 * For Selecting Employee
	 * @return String
	 */
	public String f9Employee() {

		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
				+ "	EMP_ID  FROM HRMS_EMP_OFFC ";
		
		query += getprofileQuery(resigMis);
		 query +=" AND EMP_STATUS != 'S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
			   
		String[] headers = { getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "empToken", "empName", "empCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	 /**
	  * For Selecting Branch
	  * @return String
	  */ 
	public String f9Brnach() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String settBranchQue = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER "
				+ " ORDER BY CENTER_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("branch.code"), getMessage("branch")};

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "resigbranchCode", "resigbranch" };

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
		 * WHEN SET TO 'settue' WILL SUBMIT THE FORM
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

		setF9Window(settBranchQue, headers, headerWidth, fieldNames,
				columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	 /**
	  * For Selecting Department
	  * @return String
	  */ 
	public String f9Dept() {

		String settDeptQue = " SELECT DEPT_ID, NVL(DEPT_NAME,' ')  FROM HRMS_DEPT ORDER BY DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("department.code"), getMessage("department")};

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "resigdeptCode", "resigdept" };

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

		setF9Window(settDeptQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	 /**
	  * For Selecting Designation
	  * @return String
	  */ 
	public String f9Desg() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String settDesgQue = " SELECT  RANK_ID, NVL(RANK_NAME,' ')  FROM HRMS_RANK ORDER BY RANK_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("designation.code"), getMessage("designation")};

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "resigdesgCode", "resigdesg" };

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

		setF9Window(settDesgQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	 /**
	  * For Selecting Division
	  * @return String
	  */ 
	public String f9Div() {

		String settDivQue = " SELECT  DIV_ID , NVL(DIV_NAME,' ')  FROM HRMS_DIVISION";
		
		if(resigMis.getUserProfileDivision() !=null && resigMis.getUserProfileDivision().length()>0)
		settDivQue+= " WHERE DIV_ID IN ("+resigMis.getUserProfileDivision() +")"; 
		settDivQue+= " ORDER BY  DIV_ID ";
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("division.code"), getMessage("division") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "resigdivisionCode", "resigdivision" };

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

		setF9Window(settDivQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

}//end of class
