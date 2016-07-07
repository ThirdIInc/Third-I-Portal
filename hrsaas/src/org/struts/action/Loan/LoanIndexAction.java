/**
 * 
 */
package org.struts.action.Loan;

import org.paradyne.bean.Loan.LoanIndex;
import org.paradyne.model.loan.LoanIndexModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj
 * @date 14-07-2008
 * LoanIndexAction class to generate the loan index report
 * as per the selected filters
 */
public class LoanIndexAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoanIndexAction.class);
	
	LoanIndex loanIndex;
	
	public LoanIndex getLoanIndex() {
		return loanIndex;
	}

	public void setLoanIndex(LoanIndex loanIndex) {
		this.loanIndex = loanIndex;
		
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		loanIndex=new LoanIndex();
		loanIndex.setMenuCode(632);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return loanIndex;
	}
	
	/* method name : f9actionLoanAppl 
	 * purpose     : to show all the details for the selected application
	 * return type : String
	 * parameter   : none
	 */
	public String f9actionLoanAppl() {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT LOAN_TRACKING_NUMBER,EMP_TOKEN, (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), " 
					   +"TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY'), LOAN_NAME, LOAN_EMP_ID, HRMS_LOAN_PROCESS.LOAN_APPL_CODE,NVL(LOAN_SANCTION_AMOUNT,0) "
					   +"FROM HRMS_LOAN_PROCESS  "
					   +"INNER JOIN HRMS_LOAN_APPLICATION ON(HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_PROCESS.LOAN_APPL_CODE) " 
					   +"INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "
					   +"LEFT JOIN HRMS_LOAN_MASTER ON(HRMS_LOAN_MASTER.LOAN_CODE=HRMS_LOAN_APPLICATION.LOAN_CODE) "
					   +"WHERE 1=1";
					   
		if(loanIndex.isGeneralFlag()){
			query += " AND LOAN_EMP_ID = "+loanIndex.getUserEmpId();
		}	//if statement ends
		query +=" AND NVL(LOAN_PF_TYPE,'Y') ='Y'";
					   
		query += " ORDER BY LOAN_APPL_CODE";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers = {"Application Code",getMessage("employee.id"),getMessage("employee"),
				 getMessage("ApplDate"),getMessage("typeLoan") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String[] headerWidth = { "20","10", "20", "20", "20", "15" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 
		String[] fieldNames = { "trackingNumber", "empToken", "empname","appDate","loanType","empCode","loanAppCode","sanctionAmount"};

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7};

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
	
	/* method name : reset 
	 * purpose     : to generate the report as per the selected filters
	 * return type : String
	 * parameter   : none
	 */
	/*public String report(){
		LoanIndexModel model = new LoanIndexModel();
		model.initiate(context, session);
		model.getReport(loanIndex, request, response);
		model.terminate();
		return null;
	}*/
	
	/**
	 * Resets the fields
	 */
	/**
	 * @return String SUCCESS
	 */
	public String reset() {
		try {
			loanIndex.setEmpCode("");
			loanIndex.setEmpToken("");
			loanIndex.setEmpname("");
			loanIndex.setLoanType("");
			loanIndex.setLoanAppCode("");
			loanIndex.setAppDate("");
			loanIndex.setSanctionAmount("");
			
			return SUCCESS;
		} catch(Exception e) {
			logger.info("Exception in reset in action:" + e);
			return "";
		} // end of try-catch block
	}
	
	public final String mailReport(){
		try {
			LoanIndexModel model = new LoanIndexModel(); //creating an instance of LoanApprovalModel class
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			
		//	model.generateReport(bean, request, response, reportPath);
			model.getReport(loanIndex, response,  request, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String input(){
		//deleteFile();
		return INPUT;
	}
	/* method name : reset 
	 * purpose     : to generate the report as per the selected filters
	 * return type : String
	 * parameter   : none
	 */
	public String getReport() {
		LoanIndexModel model = new LoanIndexModel(); //creating an instance of LoanApprovalModel class
		model.initiate(context, session);
		
		String reportPath = "";
		
		model.getReport(loanIndex, response,  request, reportPath);
		
		model.terminate();
		return null;
	}
}
