package org.struts.action.payroll;

import org.paradyne.bean.payroll.DaAcquintanceRoll;

import org.paradyne.model.payroll.DaAcquintanceRollModel;

 import org.struts.lib.ParaActionSupport;

public class DaAcquintanceRollAction extends ParaActionSupport {

	private DaAcquintanceRoll daAcquintanceRoll = null;

	public DaAcquintanceRoll getDaAcquintanceRoll() {
		return daAcquintanceRoll;
	}

	public void setDaAcquintanceRoll(DaAcquintanceRoll daAcquintanceRoll) {
		this.daAcquintanceRoll = daAcquintanceRoll;
	}

	 public Object getModel() {
		// TODO Auto-generated method stub
		return daAcquintanceRoll;
	}

	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		daAcquintanceRoll = new DaAcquintanceRoll();
	}

	public String report() throws Exception {
		DaAcquintanceRollModel model = new DaAcquintanceRollModel();
		logger.info("report action ");
		model.initiate(context,session);
		model.report(daAcquintanceRoll, response);
		/*
		 * if(result.equals("false")) {
		 * addActionMessage(getText("effective","")); }
		 * if(result.equals("true")) { addActionMessage("Report Generated
		 * Successfully"); }
		 */
		model.terminate();
		return null;
	}

	public String bankState() throws Exception {
		DaAcquintanceRollModel model = new DaAcquintanceRollModel();
		logger.info("report action ");
		model.initiate(context,session);
		model.bankStatement(daAcquintanceRoll, response);
		/*
		 * if(result.equals("false")) {
		 * addActionMessage(getText("effective","")); }
		 * if(result.equals("true")) { addActionMessage("Report Generated
		 * Successfully"); }
		 */
		model.terminate();
		return null;
	}

	public String f9type() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "TYPE NAME" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "daAcquintanceRoll.typeName",
				"daAcquintanceRoll.typeCode" };

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
		//String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL ";
		
		

		String query = "	SELECT DISTINCT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL INNER join  HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_PAYBILL=HRMS_PAYBILL.PAYBILL_ID)  ";
				
		query+=getprofilePaybillQuery(daAcquintanceRoll);
				
		query+=" ORDER BY PAYBILL_ID ";
	

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "PAY BILL NO", "PAY BILL GROUP" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "daAcquintanceRoll.payBillNo",
				"daAcquintanceRoll.payBillName" };

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
		logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		logger.info("4");
		return "f9page";
	}

	public String f9action() throws Exception {

		logger.info("in f9 action");

		String query = "SELECT DA_RATE,TO_CHAR(DA_EFFECTIVE_DATE,'DD-MM-YYYY') FROM HRMS_DA_PARAMETER order by da_code ";

		logger.info("in f9 action1");
		String[] headers = { "DA Rate", "DA Effect Date" };

		String[] headerWidth = { "20", "80" };
		logger.info("in f9 action2");

		String[] fieldNames = { "daAcquintanceRoll.daRate",
				"daAcquintanceRoll.daDate" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";
		logger.info("in f9 action4");

		String submitToMethod = "";
		logger.info("in f9 action5");

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		logger.info("in f9 action6");
		return "f9page";
	}

}
