package org.struts.action.payroll;


import org.paradyne.bean.payroll.EmpInvstmnt;
import org.paradyne.model.payroll.EmpInvstmntModel;
import javax.servlet.http.HttpServletResponse;

 import org.struts.lib.ParaActionSupport;
/**
 * @author Hemant Nagam
 *
 */
public class EmpInvstmntAction   extends ParaActionSupport {
	
      
	private EmpInvstmnt empInv;
    
	public EmpInvstmnt getEmpInv() {
		
		try{
			EmpInvstmntModel model=new EmpInvstmntModel();
			model.initiate(context,session);
			
			model.setPayBill(empInv);
			
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		logger.info("Inside-->getNdaHours()");		
		
		return empInv;
	}

	public void setEmpInv(EmpInvstmnt empInv) {
		logger.info("Inside-->setNdaHours()");
		this.empInv = empInv;
	}

	 public Object getModel() {
		logger.info("Inside-->getModel1()");
		return this.empInv;
	}

	public String execute() throws Exception {
		logger.info("Inside-->execute()");
		return "success";
	}
    
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		logger.info("Inside-->prepare()");
		empInv=new EmpInvstmnt();	 
	}
	
public String report() throws Exception
	{		
	logger.info("Inside-->report()");
	
		try
		{
			
				EmpInvstmntModel model = new EmpInvstmntModel();
				model.initiate(context,session);
				
				logger.info("===>"+empInv.getFrmYear());
				logger.info("===>"+empInv.getToYear());
				logger.info("===>"+empInv.getPayBill());
				logger.info("===>"+empInv.getEmpType());
				logger.info("===>"+empInv.getCenterNo());
				
				
				model.generateReport(empInv,response);
					
				model.terminate();
				
		}
		catch(Exception e)
		{
			 logger.info("eXCEPTION IN PROCESS OF OT "+e);
		}	
		if(empInv.getRptTyp().equals("Pdf") ||empInv.getRptTyp().equals("Xls") ){
			logger.info("------------->PDF");
			return null;
		}	
		return "";
}
	
public String f9center() throws Exception {
	//
	// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
	// OUTPUT ALONG WITH PROFILES
	//

	String query = " SELECT   CENTER_ID , center_name FROM HRMS_CENTER ORDER BY CENTER_ID ";

	
	 // SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	
	String[] headers = { "CENTER NO", "CENTER NAME" };

	
	  //DEFINE THE PERCENT WIDTH OF EACH COLUMN
	 
	String[] headerWidth = { "40", "60" };

	
	 // -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 // ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 // -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 // INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 // FIELDNAMES
	 //

	String[] fieldNames = { "empInv.centerNo" };

	
	 // SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 // CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
	 // IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
	  
	 //NOTE: COLUMN NUMBERS STARTS WITH 0
	  
	 
	int[] columnIndex = { 0,1 };

	
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

public String f9type() throws Exception {
	
	 // BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
	 // OUTPUT ALONG WITH PROFILES
	 //
	String query = " SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE ";

	
	 // SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 
	String[] headers = { "TYPE NAME" };

	
	 // DEFINE THE PERCENT WIDTH OF EACH COLUMN
	 
	String[] headerWidth = { "100" };

	
	 // -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 // ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 // -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 // INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 // FIELDNAMES
	 

	String[] fieldNames = { "empInv.empType",
			"empInv.typeCode" };

	
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
	 
	logger.info("1");
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	logger.info("4");
	return "f9page";
}

public String f9payBill() throws Exception {
	/**
	 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
	 * OUTPUT ALONG WITH PROFILES
	 */
	String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL ORDER BY PAYBILL_ID";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */
	String[] headers = { "PAY BILL NO","PAYBILL GROUP" };

	/**
	 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
	 */
	String[] headerWidth = { "11","11" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */

	String[] fieldNames = { "empInv.payBill"};

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
