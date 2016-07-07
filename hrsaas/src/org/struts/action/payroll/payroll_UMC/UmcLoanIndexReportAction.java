package org.struts.action.payroll.payroll_UMC;

import org.paradyne.bean.payroll.payroll_UMC.UmcLoanIndexReport;
import org.paradyne.model.loan.LoanIndexModel;
import org.paradyne.model.payroll.payroll_UMC.UmcLoanIndexReportModel;
import org.struts.lib.ParaActionSupport;

public class UmcLoanIndexReportAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(UmcLoanIndexReportAction.class);
	
	UmcLoanIndexReport loanIndexBean = null;

	@Override
	public void prepare_local() throws Exception {
		loanIndexBean = new UmcLoanIndexReport();
		loanIndexBean.setMenuCode(632);
	}

	public Object getModel() {
		return loanIndexBean;
	}
	
	public String input() {
		logger.info("######## IN INPUT METHOD ##############");
		return INPUT ;
	 }

	public UmcLoanIndexReport getLoanIndexBean() {
		return loanIndexBean;
	}

	public void setLoanIndexBean(UmcLoanIndexReport loanIndexBean) {
		this.loanIndexBean = loanIndexBean;
	}
	
	public String f9actionLoanAppl() {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT EMP_TOKEN, (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), EMP_ID, " 
		+ " NVL(PENDING_LOAN_AMT,0), NVL(EMI_AMOUNT,0),EMI_PAID_MONTH, EMI_PAID_YEAR FROM HRMS_LOAN_NEW_APPL "
		+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_NEW_APPL.EMP_ID) "
		+ " INNER JOIN HRMS_LOAN_SUPPL_APPL ON(HRMS_LOAN_SUPPL_APPL.EMP_ID=HRMS_LOAN_NEW_APPL.EMP_ID) "
		+ " WHERE LOAN_IS_REFUNDABLE = 'Y' ";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers = {getMessage("employee.id"),getMessage("employee") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String[] headerWidth = { "5", "20" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 
		String[] fieldNames = {  "empToken","empname","empCode","pendingAmt","emiAmt","lastPaidMonth","lastPaidYear"};

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6};

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String report(){
		UmcLoanIndexReportModel model = new UmcLoanIndexReportModel();
		model.initiate(context, session);
		model.getReport(loanIndexBean, request, response);
		model.terminate();
		return null;
	}

}
