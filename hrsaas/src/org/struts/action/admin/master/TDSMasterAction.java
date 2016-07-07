package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.TDSMaster;
import org.paradyne.model.admin.master.TDSMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author Pradeep Kumar Sahoo Date:20-05-2008
 * 
 */
public class TDSMasterAction extends ParaActionSupport {

	TDSMaster bean;

	public TDSMaster getBean() {
		return bean;
	}

	public void setBean(TDSMaster bean) {
		this.bean = bean;
	}

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new TDSMaster();
		bean.setMenuCode(618);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		TDSMasterModel model = new TDSMasterModel();
		model.initiate(context, session);
		// model.getTdsListDtls(bean, request);
		model.terminate();

	}

	/**
	 * following function is called in the jsp page when a record is double
	 * clicked from the list.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		TDSMasterModel model = new TDSMasterModel();
		model.initiate(context, session);
		bean.setInsertButtonFlag(false);
		bean.setUpdateButtonFlag(true);
		model.getTdsOnDblClk(bean);
		model.getTdsListDtls(bean, request);
		model.terminate();
		getNavigationPanel(3);
		bean.setEnableAll("N");
		return "success";
	}

	/**
	 * following function is called when the previous,next or page number is
	 * selected from the jsp page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		TDSMasterModel model = new TDSMasterModel();
		model.initiate(context, session);
		model.getTdsListDtls(bean, request);
		model.terminate();
		return "success";
	}

	/**
	 * following function is called when the Update button is clicked.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		TDSMasterModel model = new TDSMasterModel();
		model.initiate(context, session);
		// boolean result = model.modTds(bean);
		// result = model.addTds(bean);
		int myint = 0;
		myint = model.modTds(bean);
		// myint 1 rep saved sucessfully...! 2 reord already exist for the
		// financial year...! otherwise duplicate entry.
		// if (result) {
		if (myint == 2) {
			addActionMessage("Records already exists for this financial year.!");
		} else if (myint == 1) {
			// prepare_withLoginProfileDetails();
			addActionMessage("Record updated successfully.");
			bean.setInsertButtonFlag(true);
			bean.setUpdateButtonFlag(false);
			reset();

		} else {
			addActionMessage("Record can't be updated.");
		}
		//prepare_withLoginProfileDetails();
		
		
		model.terminate();
		return "success";
	}

	/**
	 * following function is called when the delete button is clicked from the
	 * list
	 * 
	 * @return
	 * @throws Exception
	 */

	public String delete1() throws Exception {
		boolean result = false;
		TDSMasterModel model = new TDSMasterModel();
		model.initiate(context, session);
		String[] code = request.getParameterValues("hdeleteCode");
		result = model.deleteCheckedRecords(bean, code);
		if (result) {
			prepare_withLoginProfileDetails();
			reset();
			addActionMessage(getText("delMessage", ""));

		} else
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		return "success";
	}

	/**
	 * following function is called in the jsp page when a new record is added
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		TDSMasterModel model = new TDSMasterModel();
		model.initiate(context, session);
		boolean result;
		if (bean.getTdsCode().equals("")) {

			// result = model.addTds(bean);
			int myint = 0;
			myint = model.addTds(bean);
			// myint 1 rep saved sucessfully...! 2 reord already exist for the
			// financial year...! otherwise duplicate entry.
			// if (result) {
			if (myint == 2) {
				addActionMessage("Record already exists for this financial year.!");
			} else if (myint == 1) {
				prepare_withLoginProfileDetails();
				addActionMessage("Record saved successfully!");
				//reset();
				bean.setTdsCode("");
			} else {
				addActionMessage("Duplicate entry found.");
			}

		}else {
			int myint = 0;
			myint = model.modTds(bean);
			// myint 1 rep saved sucessfully...! 2 reord already exist for the
			// financial year...! otherwise duplicate entry.
			// if (result) {
			if (myint == 2) {
				addActionMessage("Records already exists for this financial year.!");
			} else if (myint == 1) {
				// prepare_withLoginProfileDetails();
				addActionMessage("Record updated successfully.");
				bean.setInsertButtonFlag(true);
				bean.setUpdateButtonFlag(false);
				//reset();

			} else {
				addActionMessage("Record can't be updated.");
			}
		}

		model.terminate();
		model.getTdsListDtls(bean, request);
		getNavigationPanel(3);
		return "success";
	}

	/**
	 * following function is called when the delete button is clicked in the jsp
	 * page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		TDSMasterModel model = new TDSMasterModel();

		model.initiate(context, session);
		boolean result = model.delTds(bean);
		if (result) {
			bean.setTdsCode("");
			reset();
			addActionMessage("Record deleted Successfully.");
			model.Data(bean, request);

		} else {
			addActionMessage("This record is referenced in other resources.\nSo can't delete.");

		}
		getNavigationPanel(1);
		return INPUT;
	}

	/**
	 * following function is called to reset the fields of the jsp page
	 * 
	 * @return
	 */
	public String reset() {
		//bean.setTdsCode("");
		bean.setTdsDate("");
		bean.setTdsDebit("");
		bean.setTdsDebitCode("");
		bean.setEduCess("");
		bean.setSurcharge("");
		bean.setSalAmt("");
		bean.setSalAmt2("");
		bean.setSalAmt3("");
		bean.setSalAmt4("");
		bean.setSalAmt5("");
		bean.setSalAmt6("");
		bean.setCitizenAge("");
		bean.setCitizenSurLimit("");
		bean.setHraCode("");
		bean.setHraName("");
		bean.setBasicCode("");
		bean.setBasicName("");
		bean.setDaCode("");
		bean.setDaName("");
		bean.setHraExCode("");
		bean.setHraExName("");
		bean.setRebateLimit("");
		bean.setProvFundCode("");
		bean.setProvFundName("");
		bean.setSignAuthId("");
		bean.setSignAuthName("");
		bean.setToken("");
		bean.setHraPaidMetro("");
		bean.setInvVerificationDate("");
		bean.setReimbursebillDate("");
		bean.setToYear("");
		bean.setFromYear("");

		bean.setLeaveEncashamt("");
		bean.setLeaveEncashFormula("");
		bean.setLeaveEncashMonths("");

		// bean.setAvgleaveEncashNo("");
		bean.setLeaveEncInvcode("");
		bean.setLeaveEncInvName("");

		bean.setCreditCode("");
		bean.setCreditType("");
		bean.setGratuityAmount("");
		bean.setTaxCode("");
		bean.setExemptSectionNo("");

		bean.setLtaExemptSectionNo("");
		bean.setLtaCreditType("");
		bean.setLtaCreditCode("");
		bean.setLtaTaxCode("");
		bean.setLtaAmount("");
		//Added by Ganesh 5/10/2011
		bean.setTraAllowanceLimit("");
		bean.setTraAllowExemptSectionNo("");
		bean.setTraAllowTaxCode("");
		bean.setVehicleCapGreaterthan1600("");
		bean.setVehicleCapLessthan1600("");
		bean.setDriverUsedAddExemption("");
		bean.setVehicalAllowExemptSectionNo("");
		bean.setVehicalAllowTaxCode("");
		bean.setDonationsExemptSectionNo("");
		bean.setDonationsTaxCode("");
		bean.setInvDeclarationCuttOffDate("");
		bean.setMonthInvestmentDecPeriodFromDate("");
		bean.setMonthInvestmentDecPeriodToDate("");
		if (bean.getTdsCode().equals("")) 
		{
			bean.setInsertButtonFlag(true);
			bean.setUpdateButtonFlag(false);
		} else {
			bean.setInsertButtonFlag(false);
			bean.setUpdateButtonFlag(true);
		}
		
		bean.setHraExUnpaidCode("");
		bean.setHraExUnpaidName("");
		bean.setMapPerquisiteHeadCode("");
		bean.setMapPerquisiteHeadName("");
		bean.setAccomPerqCentOwnedHigher("");
		bean.setAccomPerqCentOwnedLess("");
		bean.setAccomPerqCentRentedHigher("");
		bean.setAccomPerqCentRentedLess("");
		bean.setTransAllowanceLimitPermDisabi("");
		bean.setTraAllowCreditHeadName("");
		bean.setTraAllowCreditHeadCode("");
		bean.setPermanentDisabilityDedCode("");
		bean.setPermanentDisabilityDedName("");
		bean.setGovLoanRate("");
		bean.setPerqHeadCompanyLoan("");
		bean.setPerqHeadCompanyLoanCode("");
		bean.setMinLoanAmt("");
		bean.setLockFlag("");
		bean.setDonationApplInv("");
		bean.setDonationApplInvCode("");
		bean.setDonationFormPercent("");
		getNavigationPanel(2);
		return "success";
	}

	/**
	 * following function is called when a record is selected from the pop up
	 * window.
	 * 
	 * @return
	 */
	public String getTds() {
		TDSMasterModel model = new TDSMasterModel();
		model.initiate(context, session);
		bean.setInsertButtonFlag(false);
		bean.setUpdateButtonFlag(true);
		model.getTdsOnDblClk(bean);
		// model.getTdsListDtls(bean, request);
		model.terminate();
		getNavigationPanel(3);
		return SUCCESS;

	}

	public String f9signAuth() throws Exception {
		String query = " SELECT EMP_TOKEN,NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '),EMP_ID  FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' ORDER BY EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

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

		String[] fieldNames = { "token", "signAuthName", "signAuthId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
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
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9debit() throws Exception {
		String query = " SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("debit.code"), getMessage("debit.name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "tdsDebitCode", "tdsDebit" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9da() throws Exception {
		String query = " SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("credit.code"),
				getMessage("credit.name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "daCode", "daName" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9basic() throws Exception {
		String query = " SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("credit.code"),
				getMessage("credit.name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "basicCode", "basicName" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9hra() throws Exception {
		String query = " SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("credit.code"),
				getMessage("credit.name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "hraCode", "hraName" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9tds() throws Exception {
		String query = " SELECT nvl(TDS_FINANCIALYEAR_FROM,''),nvl(TDS_FINANCIALYEAR_TO,''),TO_CHAR(TDS_EFF_DATE,'DD-MM-YYYY'),NVL(TDS_SURCHARGE,''),NVL(TDS_EDU_CESS,''),"
				+ " NVL(DEBIT_NAME,' '),TDS_CODE,TDS_DEBIT_CODE FROM  HRMS_TAX_PARAMETER INNER JOIN HRMS_DEBIT_HEAD ON "
				+ " (HRMS_TAX_PARAMETER.TDS_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE) ORDER BY TDS_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("fromyear"), getMessage("toyear"),
				getMessage("taxation.TdsEffDate"),
				getMessage("taxation.TDSSurcharge"),
				getMessage("taxation.EduCess"),
				getMessage("taxation.TDSDebtType") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "10", "10", "15", "15", "15", "25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "fromYear", "toYear", "tdsDate", "surcharge",
				"eduCess", "tdsDebit", "tdsCode", "tdsDebitCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7 };

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
		String submitToMethod = "TDSMaster_getTds.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9permanentDisabilityAction() throws Exception {

		String query = "SELECT INV_CODE , INV_NAME FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { "Investment Code", "Investment Section" };

		String[] headerWidth = { "20", "60" };

		String[] fieldNames = { "permanentDisabilityDedCode", "permanentDisabilityDedName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9hraPaidEx() {

		String query = "SELECT INV_CODE , NVL(INV_NAME,' ') FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { getMessage("taxation.f9InvstCode"),
				getMessage("taxation.f9InvstName") };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "hraExPaidCode", "hraExPaidName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}
	
	public String f9hraUnpaidEx() {

		String query = "SELECT INV_CODE , NVL(INV_NAME,' ') FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { getMessage("taxation.f9InvstCode"),
				getMessage("taxation.f9InvstName") };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "hraExUnpaidCode", "hraExUnpaidName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}
	
	public String f9hraEx() {

		String query = "SELECT INV_CODE , NVL(INV_NAME,' ') FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { getMessage("taxation.f9InvstCode"),
				getMessage("taxation.f9InvstName") };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "hraExCode", "hraExName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9LeaveEx() {

		String query = "SELECT INV_CODE , NVL(INV_NAME,' ') FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { getMessage("taxation.f9InvstCode"),
				getMessage("taxation.f9InvstName") };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "leaveEncInvcode", "leaveEncInvName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9provident() {

		String query = "SELECT INV_CODE , NVL(INV_NAME,' ') FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { getMessage("taxation.f9provFunndCode"),
				getMessage("taxation.f9ProvFundName") };

		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "provFundCode", "provFundName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String callFormulaBuilder() {
		return "callFor";
	}

	public String f9Tdscredits() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD "
				+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "
				+ " ORDER BY CREDIT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Code", getMessage("credited.to") };

		String[] headerWidth = { "40", "60" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "ltaCreditCode", "ltaCreditType" };

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

	}// 

	public String f9credits() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD "
				+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "
				+ " ORDER BY CREDIT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Code", getMessage("credited.to") };

		String[] headerWidth = { "40", "60" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "creditCode", "creditType" };

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

	}// 

	public String f9Tdstaxaction() throws Exception {

		String query = "SELECT INV_CODE , INV_NAME FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { "Investment Code", "Investment Section" };

		String[] headerWidth = { "20", "60" };

		String[] fieldNames = { "ltaTaxCode", "ltaExemptSectionNo" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9taxaction() throws Exception {

		String query = "SELECT INV_CODE , INV_NAME FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { "Investment Code", "Investment Section" };

		String[] headerWidth = { "20", "60" };

		String[] fieldNames = { "taxCode", "exemptSectionNo" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String input() {
		TDSMasterModel model = new TDSMasterModel();
		model.initiate(context, session);
		model.Data(bean, request);
		model.getTdsListDtls(bean, request);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}

	// to display the save mode
	public String addNew() {
		try {
			TDSMasterModel model = new TDSMasterModel();
			model.initiate(context, session);
			bean.setTdsCode("");
			bean.setInsertButtonFlag(true);
			bean.setUpdateButtonFlag(false);
			model.terminate();
			getNavigationPanel(4);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String back() throws Exception {
		input();
		reset();
		getNavigationPanel(1);
		bean.setEnableAll("Y");
		return INPUT;
	}

	public String f9tdsSearch() throws Exception {
		String query = " SELECT nvl(TDS_FINANCIALYEAR_FROM,'') ,nvl(TDS_FINANCIALYEAR_TO,''),TO_CHAR(TDS_EFF_DATE,'DD-MM-YYYY'),NVL(TDS_SURCHARGE,''),NVL(TDS_EDU_CESS,''),"
				+ " NVL(DEBIT_NAME,' '),TDS_DEBIT_CODE,TDS_CODE FROM  HRMS_TAX_PARAMETER INNER JOIN HRMS_DEBIT_HEAD ON "
				+ " (HRMS_TAX_PARAMETER.TDS_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE) ORDER BY TDS_FINANCIALYEAR_FROM DESC";

		String[] headers = { getMessage("fromyear"), getMessage("toyear"),
				getMessage("taxation.TdsEffDate"),
				getMessage("taxation.TDSSurcharge"),
				getMessage("taxation.EduCess"),
				getMessage("taxation.TDSDebtType") };

		String[] headerWidth = { "10", "10", "15", "15", "15", "25" };

		String[] fieldNames = { "fromYear", "toYear", "tdsDate", "surcharge",
				"eduCess", "tdsDebit", "tdsDebitCode", "tdsCode" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7 };

		String submitFlag = "true";

		String submitToMethod = "TDSMaster_getTds.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9traAllowTaxAction() throws Exception {
		 
		String query = "SELECT INV_CODE , INV_NAME FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { "Investment Code", "Investment Section" };

		String[] headerWidth = { "20", "60" };

		String[] fieldNames = { "traAllowTaxCode", "traAllowExemptSectionNo" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9vehicalAllowAction() throws Exception {
		 
		String query = "SELECT INV_CODE , INV_NAME FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { "Investment Code", "Investment Section" };

		String[] headerWidth = { "20", "60" };

		String[] fieldNames = { "vehicalAllowTaxCode", "vehicalAllowExemptSectionNo" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9donationsAction() throws Exception {
		 
		String query = "SELECT INV_CODE , INV_NAME FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { "Investment Code", "Investment Section" };

		String[] headerWidth = { "20", "60" };

		String[] fieldNames = { "donationsTaxCode", "donationsExemptSectionNo" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	public String f9mapPerquisiteHeadAction() throws Exception {
		 
		String query = "SELECT HRMS_PERQUISITE_HEAD.PERQ_CODE, HRMS_PERQUISITE_HEAD.PERQ_NAME"
				+ " FROM HRMS_PERQUISITE_HEAD"
				+ " WHERE HRMS_PERQUISITE_HEAD.PERQ_PERIOD='A'" ;

		String[] headers = { "Perquisite Code", "Perquisite Name" };

		String[] headerWidth = { "20", "60" };

		String[] fieldNames = { "mapPerquisiteHeadCode", "mapPerquisiteHeadName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9perqHeadCompanyLoanAction() throws Exception {
		 
		String query = "SELECT HRMS_PERQUISITE_HEAD.PERQ_CODE, HRMS_PERQUISITE_HEAD.PERQ_NAME "
			+ " FROM HRMS_PERQUISITE_HEAD"
			+ " WHERE HRMS_PERQUISITE_HEAD.PERQ_PERIOD='A'" ;

		String[] headers = { "Perquisite Code", "Perquisite Name" };

		String[] headerWidth = { "20", "60" };

		String[] fieldNames = { "perqHeadCompanyLoanCode", "perqHeadCompanyLoan" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}	
	
	public String f9traAllowCreditHeadAction() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD "
				+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "
				+ " ORDER BY CREDIT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Code", getMessage("credited.to") };

		String[] headerWidth = { "40", "60" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "traAllowCreditHeadCode", "traAllowCreditHeadName" };

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

	}// 

	
	/**
	 * following function is called to get the Pdf report for list of Designation records
	 * 
	 */
	public String report(){
		//getNavigationPanel(3);
		TDSMasterModel model = new TDSMasterModel();
		model.initiate(context,session);
		String[]label={"Sr.No",getMessage("donation.name"),getMessage("donation.percentage"),getMessage("is.active")};
		model.generateReport(bean,response,request,label,"");
		model.terminate();
		return null;
	}
	
	public final String mailReport(){
		try {
			TDSMasterModel model = new TDSMasterModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			String[]label={"Sr.No",getMessage("donation.name"),getMessage("donation.percentage"),getMessage("is.active")};
			model.generateReport(bean,response,request,label,reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String f9DonationApplInvestment() {
		try {
			String query =  " SELECT INV_CODE, INV_NAME, INV_SECTION"+
			" FROM  HRMS_TAX_INVESTMENT  WHERE INV_SECTION NOT IN('80C') ";	

			String header = getMessage("taxation.f9InvstName");
			String textAreaId = "paraFrm_donationApplInv";

			String hiddenFieldId = "paraFrm_donationApplInvCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
}
