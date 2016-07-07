package org.struts.action.admin.transfer;

import org.paradyne.model.admin.transfer.*;
import org.paradyne.bean.admin.transfer.*;

import org.struts.lib.ParaActionSupport;
/**
 * @author Pradeep K
 * Date:28-06-2008
 *
 */
public class TransferMisReportAction   extends ParaActionSupport {
	      
	private TransferMisReport tmr;
	
	public TransferMisReport getTmr() {
		return tmr;
	}

	public void setTmr(TransferMisReport tmr) {
		this.tmr = tmr;
	}
	public Object getModel() {
		return this.tmr;
	}

	    
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	
	public void prepare_local() throws Exception {
		tmr=new TransferMisReport();
		tmr.setMenuCode(524);
		
	}
	
	/**
	 * Following function is called to generate the report
	 * @return
	 * @throws Exception
	 */
  public String report() throws Exception
	{		
	try	{			 
			try {
				TransferMisReportModel model = new TransferMisReportModel();
				model.initiate(context,session);
				model.generateReport(tmr, response);		
				model.terminate();
			   } catch (Exception e) {
				logger.error(e.getMessage());
			   }		
		 } catch(Exception e)
		  {
			 logger.error(e.getMessage());
		  }		
		return null;
}
  
  
  public String reset(){
	  TransferMisReportModel model = new TransferMisReportModel();
		model.initiate(context,session);
		tmr.setFromDate("");
		tmr.setToDate("");
		tmr.setDeptId("");
		tmr.setDivId("");
		tmr.setBrnId("");
		tmr.setDeptName("");
		tmr.setDivName("");
		tmr.setBrnName("");
		tmr.setStatus("");
		tmr.setEmpId("");
		tmr.setEmpName("");
		model.terminate();
		return "success";
  }
  /*
   *Following function is called when the branch is selected from the pop up window. 
   */
  public String f9brn() throws Exception {
		String query = " SELECT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY CENTER_ID ";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */

	String[] headers = { getMessage("branch.code"),getMessage("branch")};

	/**
	 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
	 */
	String[] headerWidth = { "10", "35" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */

	String[] fieldNames = { "brnId","brnName"};

	/**
	 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
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
	tmr.setMasterMenuCode(31);
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
		
		return "f9page";
	}
  
  
  public String f9emp() throws Exception {
		String query = " SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  FROM HRMS_EMP_OFFC   ";
		
		query += getprofileQuery(tmr);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */

	String[] headers = { getMessage("employee.id"), getMessage("employee")};

	/**
	 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
	 */
	String[] headerWidth = { "10", "30" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */

	String[] fieldNames = { "empToken","empName","empId"};

	/**
	 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
	 * 
	 * NOTE: COLUMN NUMBERS STARTS WITH 0
	 * 
	 */
	int[] columnIndex = { 0, 1,2 };

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
	
  /*
   *Following function is called when the department is selected from the pop up window. 
   */
  public String f9dept() throws Exception {
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID ";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */

	String[] headers = { getMessage("department.code"), getMessage("department")};
	/**
	 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
	 */
	String[] headerWidth = { "10", "35" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */

	String[] fieldNames = { "deptId","deptName"};

	/**
	 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
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
	tmr.setMasterMenuCode(23);
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
		
		return "f9page";
	}
  
  /*
   *Following function is called when the division is selected from the pop up window. 
   */
  public String f9div() throws Exception {
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION";
		
		if(tmr.getUserProfileDivision() !=null && tmr.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+tmr.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */

	String[] headers = { getMessage("division.code"), getMessage("division")};
	/**
	 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
	 */
	String[] headerWidth = { "10", "35" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */

	String[] fieldNames = { "divId","divName"};

	/**
	 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
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
	tmr.setMasterMenuCode(42);
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
		
		return "f9page";
	}




}

