package org.struts.action.payroll.salary;

import org.paradyne.bean.payroll.salary.PayslipMsg;
import org.paradyne.model.payroll.salary.PayslipMsgModel;
import org.struts.lib.ParaActionSupport;

public class PayslipMsgAction extends ParaActionSupport {

	/**
	 * 
	 */
	PayslipMsg payslipMsg;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PayslipMsgAction.class);

	public void prepare_local() throws Exception {

		payslipMsg = new PayslipMsg();

	}

	public Object getModel() {
		return payslipMsg;
	}

	public PayslipMsg getPayslipMsg() {
		return payslipMsg;
	}

	public void setPayslipMsg(PayslipMsg payslipMsg) {
		this.payslipMsg = payslipMsg;
	}

	public String save() throws Exception {
		PayslipMsgModel model = new PayslipMsgModel();
		model.initiate(context, session);
		if (payslipMsg.getMsgID().equals("")) {
			boolean result = model.add(payslipMsg);
			if (result == true) {
				addActionMessage("Record Saved Successfully");
			} else {
				addActionMessage("Message can not be added ");
			}
			reset();
		} else {
			boolean result = model.update(payslipMsg);
			if (result == true) {
				addActionMessage("Record Updated Successfully");
			} else {
				addActionMessage("Record can not be Updated");
			}
			reset();
			
		}

		/*
		 * casteMaster.setCasteCatgCode(""); casteMaster.setCasteCatgName("");
		 * casteMaster.setCasteCode(""); casteMaster.setCasteName("");
		 */
		model.terminate();
		return "success";
	}

	public String delete() throws Exception {
		PayslipMsgModel model = new PayslipMsgModel();
		model.initiate(context, session);
		boolean result = model.delete(payslipMsg);
		if (result == true) {
			addActionMessage("Record Deleted Successfully");
		}
		reset();

		model.terminate();
		return "success";
	}
	//Added divisionId & divisionName for resetting by Abhijit
	public String reset() {
		payslipMsg.setEmpName("");
		payslipMsg.setMonth("");
		payslipMsg.setYear("");
		payslipMsg.setEmpTokenNo("");
		payslipMsg.setPayBillNo("");
		payslipMsg.setMsgName("");
		payslipMsg.setMsgID("");
		payslipMsg.setDivisionId("");
		payslipMsg.setDivisionName("");
		payslipMsg.setPayBillName("");

		return "success";
	}

	public String f9empaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */

		logger.info("In f9 action===========1");
		
		/*String query = " SELECT EMP_TOKEN,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ "	 EMP_ID FROM HRMS_EMP_OFFC  	"
				+ "LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)	"
				+ "LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ORDER BY EMP_ID ";
*/
		// Added Employee details by Division wise by Abhijit 
		String query = " SELECT EMP_TOKEN,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
			+ "	EMP_ID FROM HRMS_EMP_OFFC "
			+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
			+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
			+ " LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE)"
			+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)";
		
		if(payslipMsg.getUserProfileDivision() !=null && payslipMsg.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+ payslipMsg.getUserProfileDivision() +")"; 
		    query+= " ORDER BY DIV_ID ";
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Id", "Employee Name" };

		String[] headerWidth = { "20", "80" };
		logger.info("In f9 action===========2");

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empTokenNo", "empName", "empID" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

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
		logger.info("In f9 action===========3");

		String submitToMethod = "";

		logger.info("In f9 action===========4");

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9payBill() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		
		/*String query1 = " SELECT DISTINCT PAYBILL_GROUP,PAYBILL_ID FROM HRMS_PAYBILL "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL=PAYBILL_ID) ";

		  query1 += getprofilePaybillQuery(payslipMsg);*/
		  
		  //Added Divisio id & name by Division wise
		  String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
			 
			if(payslipMsg.getUserProfileDivision() !=null && payslipMsg.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+payslipMsg.getUserProfileDivision() +")"; 
				query+= " ORDER BY  DIV_ID ";
		
				
				/*String query = " SELECT DISTINCT PAYBILL_GROUP,PAYBILL_ID FROM HRMS_PAYBILL "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_PAYBILL = HRMS_PAYBILL.PAYBILL_ID) ";
				
				if(payslipMsg.getUserProfileDivision() !=null && payslipMsg.getUserProfileDivision().length()>0)
					query+= " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("+ payslipMsg.getUserProfileDivision() +")"; 
				    query+= " ORDER BY HRMS_EMP_OFFC.EMP_DIV ";*/
		
	 
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Division Id", "Division Name" };

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

		String[] fieldNames = { "divisionId", "divisionName" };

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
	}

	public String f9msgAction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		/*String query = " SELECT HRMS_PAYSLIP_MSG.MSG_ID,HRMS_PAYSLIP_MSG.MSG_MONTH,HRMS_PAYSLIP_MSG.MSG_YEAR, "
				+ "NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_TITLE.TITLE_NAME||' '||NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '),' '), "
				+ "NVL(HRMS_PAYSLIP_MSG.EMP_ID,''),NVL(HRMS_PAYBILL.PAYBILL_GROUP,' '),NVL(HRMS_PAYSLIP_MSG.MSG_PAYBILL,'') "
				+ "FROM HRMS_PAYSLIP_MSG  "
				+ "LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_PAYSLIP_MSG.EMP_ID) "
				+ "LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ "LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_PAYSLIP_MSG.MSG_PAYBILL) "
				+ " ORDER BY HRMS_PAYSLIP_MSG.MSG_ID ";*/
		// Added Message Division in place of PayBill by Abhijit
		String query = " SELECT HRMS_PAYSLIP_MSG.MSG_ID, HRMS_PAYSLIP_MSG.MSG_MONTH, HRMS_PAYSLIP_MSG.MSG_YEAR, "
				+ " NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), NVL(HRMS_TITLE.TITLE_NAME||' '||NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '),' '), " 
				+ " NVL(HRMS_PAYSLIP_MSG.EMP_ID,''), NVL(HRMS_PAYSLIP_MSG.MSG_DIVISION,'') "
				+ " FROM HRMS_PAYSLIP_MSG "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_PAYSLIP_MSG.EMP_ID) " 
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) " 
				+ " ORDER BY HRMS_PAYSLIP_MSG.MSG_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {"PAYSLIP RECORDS" ,"MONTH", "YEAR" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20","20","40" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		/*String[] fieldNames = { "msgID","month", "year", "empTokenNo",
				"empName", "empID", "payBillName", "payBillNo" };*/
		String[] fieldNames = { "msgID","month", "year", "empTokenNo",
				"empName", "empID", "divisionName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "PayslipMsg_message.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String message() {
		PayslipMsgModel model = new PayslipMsgModel();
		model.initiate(context, session);
	    model.data(payslipMsg);
		
		model.terminate();
		
		return "success";
	}

}
