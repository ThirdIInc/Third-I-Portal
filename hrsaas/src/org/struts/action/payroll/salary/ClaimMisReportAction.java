package org.struts.action.payroll.salary;
import org.paradyne.bean.payroll.salary.ClaimMisReport;
import org.paradyne.model.admin.srd.AddressDetailsModel;
import org.paradyne.model.payroll.salary.ClaimMisReportModel;;


/*
 * author:Pradeep 
 * Date:05-04-2008
 */
public class ClaimMisReportAction extends org.struts.lib.ParaActionSupport {
	ClaimMisReport cmr;
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void prepare_local() throws Exception {
		logger.info(">>>>>Inside prepare_local()");
		logger.info("PPPPP");
		cmr=new ClaimMisReport();
		cmr.setMenuCode(562);
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		logger.info("*****LOGIN PROFILE");
		ClaimMisReportModel model = new ClaimMisReportModel();
		model.initiate(context,session);
		if (cmr.isGeneralFlag()) {
			model.getEmployeeDetails(cmr.getUserEmpId(),cmr);
		}
		model.terminate();
	}
	public ClaimMisReport getCmr() {
		return cmr;
	}
	public void setCmr(ClaimMisReport cmr) {
		this.cmr = cmr;
	}
	
	
	
	public Object getModel(){
		return cmr;
	}
	
	
	public String submit() throws Exception{
		ClaimMisReportModel model=new ClaimMisReportModel();
		model.initiate(context, session);
		/*if (cmr.isGeneralFlag()) {
			model.generateReportGeneral(cmr,response,cmr.getUserEmpId());
		}else {*/
		model.generateReport(cmr,response);
	//	}
		model.terminate();
		return null;
	}
	
	public String reset() throws Exception{
		ClaimMisReportModel model=new ClaimMisReportModel();
		model.initiate(context, session);
		cmr.setEmpId("");
		cmr.setEmpToken("");
		cmr.setEmpName("");
		cmr.setFromDate("");
		cmr.setToDate("");
		cmr.setDeptCode("");
		cmr.setDepartment("");
		cmr.setDivCode("");
		cmr.setDivName("");
		cmr.setDesgCode("");
		cmr.setCreditCode("");
		cmr.setCreditName("");
		cmr.setStatus("");
		cmr.setDesgname("");
		cmr.setBranchcode("");
		cmr.setBranchname("");
		
		model.terminate();
		return "success";
	}
	
public String f9emp() throws Exception {
		
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//
	logger.info("Inside-->report()");
		String query = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC";// ORDER BY EMP_ID";
		query+=getprofileQuery(cmr);
		query+=" ORDER BY EMP_ID "	;
		
		 // SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		
		String[] headers = {getMessage("employee.id"),getMessage("employee")};

		
		  //DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 
		String[] headerWidth = {"20", "40"};

		
		 // -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 // ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 // -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 // INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 // FIELDNAMES
		 //

		String[] fieldNames = { "empToken","empName","empId" };

		
		 // SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 // CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 // IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		  
		 //NOTE: COLUMN NUMBERS STARTS WITH 0
		  
		 
		int[] columnIndex = {0,1,2};

		
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
	} 

public String f9Brnach() {

	/**
	 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
	 * OUTPUT
	 */
	String trBranchQue = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER "
			+ " ORDER BY CENTER_ID";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */
	String[] headers = {getMessage("branch.code"),getMessage("branch") };
	  
	String[] headerWidth = { "20", "80" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */

	String[] fieldNames = { "branchcode", "branchname" };

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

	setF9Window(trBranchQue, headers, headerWidth, fieldNames,
			columnIndex, submitFlag, submitToMethod);

	return "f9page";
}  // end of f9trBrnach
/**
 * @This method is used for show the Department Name
 */
public String f9Dept() {

	String trDeptQue = " SELECT DEPT_ID, NVL(DEPT_NAME,' ')  FROM HRMS_DEPT ORDER BY DEPT_ID ";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */
	String[] headers = { getMessage("department.code"), getMessage("department") };

	String[] headerWidth = { "20", "80" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */ 

	String[] fieldNames = { "deptCode", "department" };

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

	setF9Window(trDeptQue, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	return "f9page";
}  // end of f9trDept
/**
 * @This method is used for show the Designation Name
 */
public String f9Desg() {

	/**
	 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
	 * OUTPUT
	 */
	String trDesgQue = " SELECT  RANK_ID, NVL(RANK_NAME,' ')  FROM HRMS_RANK ORDER BY RANK_ID ";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */
	String[] headers = { getMessage("designation.id"), getMessage("designation") };

	String[] headerWidth = { "20", "80" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */
	
	String[] fieldNames = { "desgCode","desgname" };

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

	setF9Window(trDesgQue, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	return "f9page";
}  // end of f9trDesg
/**
 * @This method is used for show the Division Name
 */
public String f9Div() {

	String trDivQue = " SELECT  DIV_ID , NVL(DIV_NAME,' ')  FROM HRMS_DIVISION  ";
	
	
	 
	if(cmr.getUserProfileDivision() !=null && cmr.getUserProfileDivision().length()>0)
		trDivQue+= " WHERE DIV_ID IN ("+cmr.getUserProfileDivision() +")"; 
		trDivQue+= " ORDER BY  DIV_ID ";
		

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */
	String[] headers = { getMessage("division.code"),getMessage("division") };

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

	setF9Window(trDivQue, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	return "f9page";
}  // end of f9trDiv


public String f9Credit() {
	String trDivQue = " SELECT  CREDIT_CODE,CREDIT_NAME,CREDIT_ABBR FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_NAME ";
	String[] headers = { getMessage("credit.code"), getMessage("credit.name"),getMessage("credit.abbreviation") };
	String[] headerWidth = { "20", "50","30" };
    String[] fieldNames = { "creditCode", "creditName","creditAbbr" };
	int[] columnIndex = { 0, 1,2 };
    String submitFlag = "false";
    String submitToMethod = "";
    setF9Window(trDivQue, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	return "f9page";
}  // end of f9creditheads

public String f9Voucher() {
	String trDivQue = " SELECT VCH_NAME, VCH_CODE FROM HRMS_VCH_HD ORDER BY VCH_NAME ";
	String[] headers = { getMessage("vchHead")};
	String[] headerWidth = { "100" };
    String[] fieldNames = { "vchName", "vchCode" };
	int[] columnIndex = { 0, 1};
    String submitFlag = "false";
    String submitToMethod = "";
    setF9Window(trDivQue, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	return "f9page";
}  // end of f9creditheads



}
