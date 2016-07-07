package org.struts.action.payroll;
import org.paradyne.bean.payroll.BankStatement;
import org.paradyne.model.payroll.BankStatementModel;

/*
 * 
 * @author Hemant 
 * 
 */
public class BankStatementAction extends org.struts.lib.ParaActionSupport{
	BankStatement bean;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		logger.info(">>>>>Inside prepare_local()");
		bean=new BankStatement();
	}
	public Object getModel(){
		return bean;
	}
	public String report(){
		BankStatementModel model=new BankStatementModel();
		model.initiate(context,session);
		model.generateReport(bean,response);
		model.terminate();
		return null;
	}
	public String f9bank() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//
	logger.info("Inside-->report()");
		String query = " SELECT BANK_MICR_CODE,BANK_NAME FROM HRMS_BANK ORDER BY BANK_NAME";

		
		 // SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		
		String[] headers = {"Code","Bank"};

		
		  //DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 
		String[] headerWidth = {"30", "30"};

		
		 // -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 // ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 // -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 // INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 // FIELDNAMES
		 //

		String[] fieldNames = { "bankCode","bank" };

		
		 // SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 // CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 // IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		  
		 //NOTE: COLUMN NUMBERS STARTS WITH 0
		  
		 
		int[] columnIndex = { 0,1};

		
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
	
	public String f9payBill() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL ORDER BY PAYBILL_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Pay Bill Code","Pay Bill Group" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "25","25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "payBill"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0,1 };

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
}
