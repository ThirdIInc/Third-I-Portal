package org.struts.action.payroll;

/**
 * Pradeep
 */
import org.paradyne.bean.payroll.RecoveryReport;
import org.paradyne.model.payroll.RecoveryReportModel;
 import org.struts.lib.ParaActionSupport;


public class RecoveryReportAction extends ParaActionSupport {
	
	RecoveryReport rr ;
	public RecoveryReport getRr() {
		return rr;
	}

	public void setRr(RecoveryReport rr) {
		this.rr = rr;
	}
	

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		rr = new RecoveryReport();
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	 public Object getModel() {
		// TODO Auto-generated method stub
		return rr;
	}
	
	public String report() throws Exception {
		RecoveryReportModel model = new RecoveryReportModel();
		model.initiate(context,session);
		model.generateReport(rr,response);
		model.terminate();
		return null;
	}
	
	public String f9paybill() throws Exception {
		
		String query="SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL ORDER BY PAYBILL_ID ";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */

	String[] headers = { "PayBill Id", "PayBill Group" };

	/**
	 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
	 */
	String[] headerWidth = { "50", "50" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */

	String[] fieldNames = {"payId","payName" };

	/**
	 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
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
	 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
	 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
	 * ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod ="";

	/**
	 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
	 */

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
		
		return "f9page";
	}


public String f9deb() throws Exception {
		
		String query=" SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */

	String[] headers = { "Debit Id", "Debit Head" };

	/**
	 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
	 */
	String[] headerWidth = { "50", "50" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */

	String[] fieldNames = {"debId","debName" };

	/**
	 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
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
	 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
	 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
	 * ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod ="";

	/**
	 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
	 */

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
		
		return "f9page";
	}

	

}
