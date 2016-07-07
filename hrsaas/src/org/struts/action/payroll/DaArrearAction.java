package org.struts.action.payroll;

import org.paradyne.bean.payroll.DaArrear;
import org.paradyne.model.payroll.DaArrearModel;
 import org.struts.lib.ParaActionSupport;

public class DaArrearAction extends ParaActionSupport {

	private DaArrear daArrear = null;

	public DaArrear getDaArrear() {
		return daArrear;
	}

	public void setDaArrear(DaArrear daArrear) {
		this.daArrear = daArrear;
	}

	 public Object getModel() {
		// TODO Auto-generated method stub
		return daArrear;
	}

	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		daArrear = new DaArrear();
	}

	public String view() throws Exception {
		DaArrearModel model = new DaArrearModel();
		model.initiate(context,session);
		String result = model.view(daArrear);
		if (daArrear.getDaList().size() == 0) {
			addActionMessage(getText("Arrear for this effective date not yet processed. \n Please Calculate and Save First."));
		}

		return "success";
	}

	public String calculate() throws Exception {
		DaArrearModel model = new DaArrearModel();
		model.initiate(context,session);
		String result = model.calculate(daArrear);
		if (result.equals("false")) {
			addActionMessage(getText("No Records to Calculate."));
		} else {
			daArrear.setSaveFlag("true");
		}
		return "success";
	}

	public String save() throws Exception {
		DaArrearModel model = new DaArrearModel();
		model.initiate(context,session);
		String[] emp_id = request.getParameterValues("empId");
		String[] paidDa = request.getParameterValues("paidDa");
		String[] dueDa = request.getParameterValues("dueDa");
		String[] daMonth = request.getParameterValues("daMonth");
		String[] daYear = request.getParameterValues("daYear");
		String[] diff = request.getParameterValues("diff");

		/*
		 * for (int i = 0; i < emp_id.length; i++) { logger.info("Values" +
		 * emp_id[i]+""+i); //
		 * +"Values"+paidDa[i]+"Values"+daMonth[i]+"Values"+daYear[i] }
		 */
		String result = model.save(emp_id, daMonth, daYear, paidDa, dueDa,
				diff, daArrear);
		model.terminate();
		if (result.equals("yes")) {
			addActionMessage(getText("Arrear for this effective date already locked"));
		} else {
			daArrear.setSaveFlag("true");
			daArrear.setLockFlag("true");
			addActionMessage(getText("addMessage", ""));
		}
		return "success";
	}

	public String lock() throws Exception {
		DaArrearModel model = new DaArrearModel();
		model.initiate(context,session);
		boolean result = model.lockStatus(daArrear);

		model.terminate();
		if (result) {
			addActionMessage(getText("Locked"));
		}

		return "success";
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

		String[] fieldNames = { "daArrear.typeName", "daArrear.typeCode" };

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
		
			String query = "	SELECT DISTINCT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL INNER join  HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_PAYBILL=HRMS_PAYBILL.PAYBILL_ID) ";
					
			query+=getprofilePaybillQuery(daArrear);
					
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

		String[] fieldNames = { "daArrear.payBillNo", "daArrear.payBillName" };

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

		String query = "SELECT DA_CODE,DA_RATE,TO_CHAR(DA_EFFECTIVE_DATE,'DD-MM-YYYY'),DA_ISLOCKED FROM HRMS_DA_PARAMETER order by da_code ";

		logger.info("in f9 action1");
		String[] headers = { "DA Code", "DA Rate", "DA Effect Date" };

		String[] headerWidth = { "20", "20", "60" };
		logger.info("in f9 action2");

		String[] fieldNames = { "daArrear.daCode", "daArrear.daRate",
				"daArrear.daDate", "daArrear.daFlag" };

		int[] columnIndex = { 0, 1, 2, 3 };

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
