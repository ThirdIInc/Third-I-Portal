package org.struts.action.Loan;

import org.paradyne.bean.Loan.LoanMaster;
import org.paradyne.model.loan.LoanMasterModel;
import org.paradyne.model.payroll.CreditModel;
import org.struts.action.payroll.YearlyEDSummaryReportAction;

/**
 * @author Tarun Chaturvedi 
 * @date 10-07-08
 * @description LoanMasterAction class to save, update, delete and view 
 * loan types
 */
public class LoanMasterAction extends org.struts.lib.ParaActionSupport {
	LoanMaster loanMaster;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoanMasterAction.class);
	/**
     * Used for f9 windows submit Flag false.
     */
	private static final String SUBMIT_FLAG_FALSE = "false";
	@Override
	public void prepare_local() throws Exception {
		loanMaster = new LoanMaster();
		loanMaster.setMenuCode(386);
	}

	public LoanMaster getLoanMaster() {
		return loanMaster;
	}

	public void setLoanMaster(LoanMaster loanMaster) {
		this.loanMaster = loanMaster;
	}

	public Object getModel() {
		return loanMaster;
	}

	/** method name : reset 
	 * purpose     : to reset all the form fields to blank values
	 * return type : String
	 * parameter   : none
	 */
	public String reset() {
		loanMaster.setLoanTypeName("");
		loanMaster.setLoanTypeCode("");
		loanMaster.setDebitcode("");
		loanMaster.setDebitname("");
		loanMaster.setHiddencode("");
		
		loanMaster.setLoanLimitAmount("");
		//loanMaster.setAdmApprovalReq("N");
		loanMaster.setAdminCode("");
		loanMaster.setAdminToken("");
		loanMaster.setAdminName("");
	//	loanMaster.setAccApprovalReq("N");
		loanMaster.setAccountCode("");
		loanMaster.setAccountToken("");
		loanMaster.setAccountName("");
		
		loanMaster.setDivCode("");
		loanMaster.setDivName("");
		loanMaster.setInterestRate("");
		loanMaster.setInterestType("");
		loanMaster.setTaxable("");
		loanMaster.setOtherLoanTerms("");
		loanMaster.setStdIntRateSBI("");
		
		getNavigationPanel(2);

		return "showData";
	}

	public String cancel() throws Exception {
		reset();
		getNavigationPanel(1);
		return SUCCESS;
	}

	/** method name : save 
	 * purpose     : to add new record or to modify the existing record
	 * return type : String
	 * parameter   : none
	 */
	public String save() {
		logger.info("in save method");
		boolean result = false;

		LoanMasterModel model = new LoanMasterModel(); //creating an instance of LoanMasterModel class
		model.initiate(context, session); //initialize LoanMasterModel class

		if (loanMaster.getLoanTypeCode().equals("")) { //if loan type code is null or blank

			/**
			 * call saveLoanType(loanMaster) method of LoanMasterModel class 
			 * to save the loan type record in HRMS_LOAN_MASTER table
			 */
			result = model.saveLoanType(loanMaster);

			if (result) { //if record saved with out any error
				addActionMessage(getText("addMessage", "")); //display save message
			} //end of if statement
			else {
				addActionMessage("Loan type can not be saved"); //else display the error message
			} //end of else statement
		} //end of if	statement
		else { //if loan type code present

			/**
			 * call updateLoanType(loanMaster) method of LoanMasterModel class 
			 * to update the loan type record in HRMS_LOAN_MASTER table
			 */
			result = model.updateLoanType(loanMaster);

			if (result) { //if record updated with out any error
				addActionMessage("Record Updated Succcessfully."); //display update message
			} //end of if statement
			else {
				addActionMessage("Loan type can not be updated"); //display the error message
			} //end of else statement
		} //end of else statement

		/**
		 * call data(loanMaster,request) method of LoanMasterModel class 
		 * to display all the records form HRMS_LOAN_MASTER table on page loading
		 */

		model.data(loanMaster, request);
		model.terminate();
		if (result) {
			getNavigationPanel(3);
			return "showData";
		} else {
			getNavigationPanel(1);
			return SUCCESS;
		}
	}

	/** method name : delete 
	 * purpose     : to delete the selected record
	 * return type : String
	 * parameter   : none
	 */
	public String delete() {
		logger.info("in delete method");
		boolean result = false;

		LoanMasterModel model = new LoanMasterModel(); //creating an instance of LoanMasterModel class
		model.initiate(context, session); //initialize LoanMasterModel class

		/**
		 * call deleteLoanType(loanMaster) method of LoanMasterModel class
		 * to delete the selected loan type from HRMS_LOAN_MASTER table
		 */
		result = model.deleteLoanType(loanMaster);

		if (result) { //record deleted with out any error
			addActionMessage(getText("delMessage", "")); //display delete message

		} //end of if	statement
		else {
			addActionMessage("This record is referenced in other resources.So cannot delete."); //else display the error message
		} //end of else
		reset(); //call reset() method to clear all form fields values
		/**
		 * call data(loanMaster,request) method of LoanMasterModel class 
		 * to display all the records form HRMS_LOAN_MASTER table on page loading
		 */
		getNavigationPanel(1);
		model.data(loanMaster, request); //terminate the LoanMasterModel class

		return "success";
	}

	/** method name : report 
	 * purpose     : to generate the report 
	 * return type : String
	 * parameter   : none
	 */
	public String report() {
		logger.info("in report method");

		LoanMasterModel model = new LoanMasterModel(); //creating an instance of LoanMasterModel class
		model.initiate(context, session); //initialize LoanMasterModel class

		/**
		 * call getReport(loanMaster,request,response,context,session) method of LoanMasterModel class
		 * to generate the report
		 */
		model.getReport(loanMaster, request, response, context, session);

		model.terminate(); //terminate the LoanMasterModel class	
		return null;
	}

	/** method name : f9action 
	 * purpose     : to show all the details for the selected record
	 * return type : String
	 * parameter   : none
	 */
	public String f9action() {
		logger.info("in f9action method");
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		//String query = "SELECT LOAN_CODE, LOAN_NAME FROM HRMS_LOAN_MASTER ORDER BY LOAN_CODE";		
		String query = " select LOAN_CODE, LOAN_NAME,Nvl(DEBIT_NAME,' '),LOAN_DEBIT_CODE FROM HRMS_LOAN_MASTER "
				+ " inner JOIN  HRMS_DEBIT_HEAD ON(HRMS_LOAN_MASTER.LOAN_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE) "
				+ " order by LOAN_CODE";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { "Loan  Code", getMessage("loan.type"),
				getMessage("loan.underdebithead") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "20", "40", "40" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = { "loanTypeCode", "loanTypeName", "Debitname",
				"Debitcode" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LoanMaster_LoanRecord.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String LoanRecord() throws Exception {

		LoanMasterModel model = new LoanMasterModel();
		model.initiate(context, session);
		model.getCreditRecord(loanMaster);
		getNavigationPanel(3);
		loanMaster.setEnableAll("N");
		model.terminate();
		return "showData";
	}

	/** method name : prepare_withLoginProfileDetails 
	 * purpose     : to display all the saved loan types at the time of page loading
	 * return type : void
	 * parameter   : none
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		LoanMasterModel model = new LoanMasterModel(); //creating an instance of LoanMasterModel class
		model.initiate(context, session); //initialize LoanMasterModel class

		/**
		 * call data(loanMaster,request) method of LoanMasterModel class 
		 * to display all the records form HRMS_LOAN_MASTER table on page loading
		 */
		model.data(loanMaster, request);

		model.terminate(); //terminate the LoanMasterModel class	
	}

	public String input() throws Exception {
		LoanMasterModel model = new LoanMasterModel(); //creating an instance of LoanMasterModel class
		model.initiate(context, session); //initialize LoanMasterModel class
		model.data(loanMaster, request);
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String addNew() throws Exception {
		reset();
		getNavigationPanel(2);
		return "showData";
	}

	/** method name : calforedit 
	 * purpose     : to edit the selected record
	 * return type : void
	 * parameter   : none
	 */
	public String calforedit() throws Exception {
		LoanMasterModel model = new LoanMasterModel(); //creating an instance of LoanMasterModel class
		model.initiate(context, session); //initialize LoanMasterModel class

		/**
		 * call calforedit(loanMaster) method of LoanMasterModel class
		 * to edit the selected record
		 */
		model.calforedit(loanMaster);

		/**
		 * call data(loanMaster,request) method of LoanMasterModel class 
		 * to display all the records form HRMS_LOAN_MASTER table on page loading
		 */
		model.data(loanMaster, request);
		
		getNavigationPanel(3);
		loanMaster.setEnableAll("N");
		model.terminate(); //terminate the LoanMasterModel class	
		return "showData";
	}

	/** method name : delete1 
	 * purpose     : to delete the selected record
	 * return type : void
	 * parameter   : none
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		LoanMasterModel model = new LoanMasterModel(); //creating an instance of LoanMasterModel class
		model.initiate(context, session); //initialize LoanMasterModel class

		/**
		 * call deletecheckedRecords(loanMaster, code) method of LoanMasterModel class
		 * to delete the checked records
		 */
		boolean result = model.deletecheckedRecords(loanMaster, code);

		if (result) { //if record deleted with out any error
			addActionMessage(getText("delMessage", "")); //display delete message
		} //end of if statement
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");//else display error message
		} //end of else statement
		reset();
		/**
		 * call data(loanMaster,request) method of LoanMasterModel class 
		 * to display all the records form HRMS_LOAN_MASTER table on page loading
		 */
		model.data(loanMaster, request);

		getNavigationPanel(1);
		model.terminate(); //terminate the LoanMasterModel class	

		return SUCCESS;

	}

	/** method name : f9debitaction 
	 * purpose     : to select the debit head
	 * return type : String
	 * parameter   : none
	 */
	public String f9debitaction() {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { "Loan Debit Code", "Loan Debit Head" };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerwidth = { "25", "75" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "Debitcode", "Debitname" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlage = "false";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}
	
	/**
	 * For Selecting Admin Approval
	 * 
	 * @return String
	 */
	public String f9admin() {
		
		
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(loanMaster);
		query += " AND EMP_STATUS='S' ";//AND EMP_ID NOT IN(" + str + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"adminToken", "adminName", "adminCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
	
	/**
	 * For Selecting Accountant Approval
	 * 
	 * @return String
	 */
	public String f9account() {
		
		
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(loanMaster);
		query += " AND EMP_STATUS='S' ";//AND EMP_ID NOT IN(" + str + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"accountToken", "accountName", "accountCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
	
	/**
	 * Method to select the Division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9divaction() throws Exception {
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
		if (this.loanMaster.getUserProfileDivision() != null && this.loanMaster.getUserProfileDivision().length() > 0) {
			query += " WHERE DIV_ID IN (" + this.loanMaster.getUserProfileDivision() + ")"; 
		}
		query += " ORDER BY  DIV_ID ";
		final String header = this.getMessage("division");
		String textAreaId = "paraFrm_divName";

		String hiddenFieldId = "paraFrm_divCode";
		final String submitFlag = LoanMasterAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		return "f9multiSelect";
	}
	
	// Added by Tinshuk S.B. Begin
	public String getReport() throws Exception {
		LoanMasterModel model = new LoanMasterModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getLoanReport(loanMaster, request, response, reportPath);
		model.terminate();
		return null;
	}

	public final String mailReport() {
		try {
			final LoanMasterModel model = new LoanMasterModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath = getText("data_path") + "/Report/Master"+ poolName + "/";
			model.getLoanReport(loanMaster, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	// Added by Tinshuk S.B. End
}
