package org.struts.action.Loan;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Loan.LoanClosure;
import org.paradyne.model.loan.LoanClosureModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Tarun Chaturvedi
 * @date 21-07-2008
 * @description LoanClosureAction class to save, update, delete and view 
 * loan closure details for the selected application
 */
public class LoanClosureAction extends ParaActionSupport {
		
	LoanClosure loanClosure;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoanClosureAction.class);
	
	public LoanClosure getLoanClosure() {
		return loanClosure;
	}

	public void setLoanClosure(LoanClosure loanClosure) {
		this.loanClosure = loanClosure;
	}

	@Override
	public void prepare_local() throws Exception {
		loanClosure = new LoanClosure();
		loanClosure.setMenuCode(359);
		// TODO Auto-generated method stub
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return loanClosure;
	}
	
	/** method name : save 
	 * purpose     : to add new record or to modify the existing record
	 * return type : String
	 * parameter   : none
	 */
	public String save(){
		logger.info("in save method");
		String result = "";
		
		/**
		 * getting all the form fields values which are necessary to save the 
		 * closure details of the selected application
		 */
		String [] installmentDate     = request.getParameterValues("monthYear");	//installment date
		String [] principalAmount     = request.getParameterValues("principalAmt");	//principal amount
		String [] interestAmount      = request.getParameterValues("interestAmt");	//interest amount
		String [] installmentAmount   = request.getParameterValues("installmentAmt");	//total installment amount
		String [] balancePrincipalAmt = request.getParameterValues("balancePrincipalAmt");	//balance principal amount
		String [] isPaid              = request.getParameterValues("isPaid");				//is installment paid or not
		
		if(installmentAmount != null && installmentAmount.length != 0){
			for (int i = 0; i < installmentAmount.length; i++) {
				logger.info("installmentDate  "+installmentDate [i]);
				logger.info("principalAmount  "+principalAmount [i]);
				logger.info("interestAmount  "+interestAmount [i]);
				logger.info("installmentAmount  "+installmentAmount [i]);
				logger.info("balancePrincipalAmt  "+balancePrincipalAmt [i]);
				logger.info("isPaid  "+isPaid [i]);
			}	//end of for loop	
		}	//end of if statement
		
		LoanClosureModel model = new LoanClosureModel();	//creating an instance of LoanClosureModel class
		model.initiate(context, session);					//initialize the LoanClosureModel class
		
		if(loanClosure.getLoanClosureCode().equals("")){	//loan closure code is null or blank
			
			/**
			 * call saveLoanClosure(loanClosure, installmentDate, principalAmount, interestAmount, 
			 *		installmentAmount, balancePrincipalAmt, isPaid); method of LoanClosureModel class
			 * to save the closure details of selected application in HRMS_LOAN_CLOSURE table
			 */
			result = model.saveLoanClosure(loanClosure, installmentDate, principalAmount, interestAmount, 
						installmentAmount, balancePrincipalAmt, isPaid);
			
			if(result.equals("saved")){	//if application saved with out any error 
				addActionMessage(getText("addMessage", ""));	//display save message
				reset();	//call reset() method to clear all form fields values
			}	//end of if statement
			else{	
				addActionMessage("Record can not be saved");	//else display error message
				
				/**
				 * call rescheduleInstallments() method to display the new installment 
				 * schedule as per the given criteria
				 */
				rescheduleInstallments();
			}	//end of else statement
		}	//end of if statement
		else{	//if loan closure code present 
			
			/**
			 * call updateLoanClosure(loanClosure, installmentDate, principalAmount, interestAmount, 
			 *		installmentAmount, balancePrincipalAmt, isPaid); method of LoanClosureModel class
			 * to update the closure details of selected application in HRMS_LOAN_CLOSURE table
			 */
			result = model.updateLoanClosure(loanClosure, installmentDate, principalAmount, interestAmount, 
						installmentAmount, balancePrincipalAmt, isPaid);
			
			if(result.equals("updated")){	//if application updated with out any error
				addActionMessage(getMessage("update"));	//display update message
				reset();	//call reset() method to clear all form fields values
			}	//end of if	statement
			
			else if(result.equals("error")){	//if some error occurred 
				addActionMessage("Record can not be updated"); //else display error message
				
				/**
				 * call rescheduleInstallments() method to display the new installment 
				 * schedule as per the given criteria
				 */
				rescheduleInstallments();
			}	//end of else if statement
			else{	//if the installment is not the latest one 
				addActionMessage("This is not your latest installment, so can not be updated.");	//display this message
				
				/**
				 * call showInstallmentSchedule(loanClosure, request) method of LoanClosureModel class 
				 * to display the installment schedule
				 */
				model.showInstallmentSchedule(loanClosure, request);
			}	//end of else statement
		}	//end of else statement
		
		model.terminate();	//terminate the LoanClosureModel class 
		return "success";
	}
	
	/** method name : delete 
	 * purpose     : to delete the selected record
	 * return type : String
	 * parameter   : none
	 */
	public String delete(){
		logger.info("in delete method");
		
		LoanClosureModel model = new LoanClosureModel();	//creating an instance of LoanClosureModel class
		model.initiate(context, session);					//initialize the LoanClosureModel class
		
		/**
		 * call deleteLoanClosure(loanClosure) method of LoanClosureModel class
		 * to delete the selected application from HRMS_LOAN_APPLICATION
		 */
		
		String result = model.deleteLoanClosure(loanClosure);
		
		if(result.equals("deleted")){	//if application deleted with out any error
			addActionMessage(getText("delMessage", ""));	//display the delete message
			reset();	//call reset() method to clear all form fields values
		}	//end of if statement
		else if(result.equals("error")){	//if some error occurred
			addActionMessage("Record can not be deleted");	//display the error message
		}	//end of else if statement
		else{	//if the installment is not the latest one 
			addActionMessage("This is not your latest installment, so can not be deleted.");	//display this message
		}	//end of else statement
		
		model.terminate();	//terminate the LoanClosureModel class
		return "success";
	}
	
	/** method name : report 
	 * purpose     : to generate the report for the selected application
	 * return type : String
	 * parameter   : none
	 */
	public String report(){
		logger.info("in report method");
		
		LoanClosureModel model = new LoanClosureModel();	//creating an instance of LoanClosureModel class
		model.initiate(context, session);					//initialize the LoanClosureModel class
		
		/**
		 * call generateReport(loanClosure, response) method of LoanClosureModel class
		 * to generate the report for the selected application 
		 */
		model.generateReport(loanClosure, response);
		
		model.terminate();	//terminate the LoanClosureModel class
		return null;
	}
	
	/** method name : rescheduleInstallments 
	 * purpose     : to reschedule the installment details
	 * 					 for the selected application
	 * return type : String
	 * parameter   : none
	 */
	public String rescheduleInstallments(){
		logger.info("in rescheduleInstallments");
		
		/**
		 * getting all the form fields values which are necessary to
		 * reschedule the loan installments
		 */
		String principalAmount = request.getParameter("remainingPrincipalAmount");				//principal amount
		String interestRate    = request.getParameter("interestRate");				//interest rate
		String interestType    = request.getParameter("interestType");		//interest type
		String noOfInstallment = "";			
		String startDate       = request.getParameter("closureDate");				//installment start date
		if(!interestType.equals("I")){
			noOfInstallment = request.getParameter("noOfInstallmentOther");			//no of installments
		}else {
			noOfInstallment = request.getParameter("noOfInstallmentsReduceInt");			//no of installments
		}
		logger.info("principalAmount  "+principalAmount);
		logger.info("interestRate  "+interestRate);
		logger.info("interestType  "+interestType);
		logger.info("noOfInstallment  "+noOfInstallment);
		logger.info("startDate  "+startDate);
		
		LoanClosureModel model = new LoanClosureModel();	//creating an instance of LoanClosureModel class
		model.initiate(context, session);					//initialize the LoanClosureModel class
		
		try {
			/**
			 * call rescheduleInstallments(loanClosure, principalAmount, interestRate, interestType, 
			 * 		noOfInstallment, startDate, request) method of LoanClosureModel class
			 * to reschedule the loan installments
			 */
			if (loanClosure.getHiddenCalType().equals("E")) {
				model.rescheduleInstallmentsForEmi(loanClosure,
						principalAmount, interestRate, Double
								.parseDouble(loanClosure.getEmiAmount()),
						startDate, request);
			} else if (loanClosure.getHiddenCalType().equals("P")) {
				//UPDATED BY REEBA
				if (!String.valueOf(loanClosure.getMonthlyPrincAmount())
						.equals("0"))
					model.rescheduleInstallmentsForPrinc(loanClosure,
							principalAmount, interestRate, interestType,
							noOfInstallment, startDate, request);
			} else {
				model.rescheduleInstallments(loanClosure, principalAmount,
						interestRate, interestType, noOfInstallment, startDate,
						request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String label = "";
		
		if(loanClosure.getInstallmentFlag().equals("true")){
			if(!(interestType.equals("R")))	//if interest type is not R
				label = "Principal Amount";	//set label as Principal Amount
			else 
				label="Principal Reduction";	//else set label as Principle Reduction
			
			request.setAttribute("lable", label);	//set label as a request attribute
			
		}else if(loanClosure.getInstallmentFlag().equals("false")){
			if(!loanClosure.getEmiAmount().equals("")){
				addActionMessage("Please check the EMI amount");
			}
		}
		
		model.terminate();	//terminate the LoanClosureModel class
		return "success";
	}
	
	/** method name : setBalanceAmount 
	 * purpose     : to calculate the balance loan amount 
	 * 					 for the selected application
	 * return type : String
	 * parameter   : none
	 */
	public String setBalanceAmount(){
		logger.info("in setBalanceAmount method");
		
		LoanClosureModel model = new LoanClosureModel();	//creating an instance of LoanClosureModel class
		model.initiate(context, session);					//initialize the LoanClosureModel class
		
		/**
		 * call setBalanceAmount(loanClosure) method of LoanClosureModel class
		 * to calculate the balance amount
		 */
		model.setBalanceAmount(loanClosure);
		String interestType    = loanClosure.getInterestType();
		if(!interestType.equals("I")){
			loanClosure.setFlatRateFlag("true");
		}else{
			loanClosure.setFlatRateFlag("false");
		}
		loanClosure.setClosureDate("");
		loanClosure.setAmtPaidByEmp("");
		
		loanClosure.setNoOfInstallmentOther("");
		loanClosure.setNoOfInstallmentsReduceInt("");
		loanClosure.setLoanClosureCode("");
		
		model.terminate();	//terminate the LoanClosureModel class
		return "success";
	}
	
	/** method name : showInstallmentSchedule 
	 * purpose     : to display the installment schedule in tabular format
	 * return type : String
	 * parameter   : none
	 */
	public String showInstallmentSchedule(){
		logger.info("in setInstallmentData method");
		
		LoanClosureModel model = new LoanClosureModel();	//creating an instance of LoanClosureModel class
		model.initiate(context, session);					//initialize the LoanClosureModel class
		
		/**
		 * call showInstallmentSchedule(loanClosure, request) method of LoanClosureModel class
		 * to display the installment details on form
		 */
		model.showInstallmentSchedule(loanClosure, request);
		
		String interestType    = loanClosure.getInterestType();
		if(!interestType.equals("I")){
			loanClosure.setFlatRateFlag("true");
		}else{
			loanClosure.setFlatRateFlag("false");
		}
		if(loanClosure.getLoanClosureCode().equals("")) {	//if loan closure code is null or blank
			loanClosure.setClosureDate("");		//clear Loan Payment/Closure Date 
			loanClosure.setAmtPaidByEmp("");	//clear Pre Payment Amount
		}	//end of if statement
		
		String label = "";
		
		if(!(loanClosure.getInterestType().equals("R"))) //if interest type is not equal to R
			label = "Principal Amount";		//set label as Principal Amount
		else 
			label = "Principal Reduction";	//else set label as Principal Reduction
		
		request.setAttribute("lable", label);	//set label as request attribute
		
		model.terminate();	//terminate the LoanClosureModel class
		return "success";
	}
	
	/** method name : reset 
	 * purpose     : to reset all the form fields and set all values to empty strings
	 * return type : String
	 * parameter   : none
	 */
	
	public String reset(){
		logger.info("in reset method");
		
		loanClosure.setLoanAppCode("");
		loanClosure.setLoanClosureCode("");
		loanClosure.setEmpCode("");
		loanClosure.setEmpName("");
		loanClosure.setEmpToken("");
		loanClosure.setBranchName("");
		loanClosure.setDeptName("");
		loanClosure.setSanctionAmount("");
		loanClosure.setInterestRate("");
		loanClosure.setInterestType("");
		loanClosure.setAmountPaid("");
		loanClosure.setBalanceAmount("");
		loanClosure.setClosureDate("");
		loanClosure.setAmtPaidByEmp("");
		loanClosure.setDate("");
		loanClosure.setNoOfInstallmentOther("");
		loanClosure.setNoOfInstallmentsReduceInt("");
		loanClosure.setHiddenInterestType("");
		loanClosure.setEmiAmount("");
		loanClosure.setMonthlyPrincAmount("");
		return "success";
	}
	
	/** method name : f9loanAppAction 
	 * purpose     : to select the loan types
	 * return type : String
	 * parameter   : none
	 */
	public String f9loanAppAction(){

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT HRMS_LOAN_PROCESS.LOAN_APPL_CODE, HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||"
					   +" HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, NVL(TO_CHAR(LOAN_SANCTION_DATE, 'DD-MM-YYYY'), ' '), "
					   +" LOAN_SANCTION_AMOUNT, HRMS_EMP_OFFC.EMP_ID, CENTER_NAME, DEPT_NAME, NVL(LOAN_INTEREST_RATE, 0), "
					   +" LOAN_INTEREST_TYPE "
					   +" FROM HRMS_LOAN_PROCESS "
					   +" INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_PROCESS.LOAN_APPL_CODE AND LOAN_PF_TYPE='Y') "
					   +" INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = LOAN_EMP_ID) "
					   +" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					   +" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  ";
					   
		/*if(loanApp.isGeneralFlag()){
			query += "WHERE LOAN_EMP_ID = "+loanApp.getUserEmpId();
		}*/
		
		query += " ORDER BY HRMS_LOAN_PROCESS.LOAN_APPL_CODE";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("appCode"),getMessage("employee.id"), getMessage("employee"), getMessage("sancDate"),getMessage("sancAmnt")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"20", "15", "25", "20", "20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"loanAppCode", "empToken", "empName", "date", "sanctionAmount",  
									"empCode", "branchName", "deptName", "interestRate", "interestType"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LoanClosure_setBalanceAmount.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/** method name : f9action 
	 * purpose     : to show all the details for the selected application
	 * return type : String
	 * parameter   : none
	 */
	public String f9Action(){

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
					   +"NVL(TO_CHAR(LOAN_SANCTION_DATE, 'DD-MM-YYYY'), ' ') SAN_DATE, LOAN_SANCTION_AMOUNT, "
					   +"TO_CHAR(LOAN_PREPAY_DATE, 'DD-MM-YYYY') PAY_DATE, LOAN_PREPAY_AMOUNT, HRMS_EMP_OFFC.EMP_ID, CENTER_NAME, "
					   +"DEPT_NAME, NVL(LOAN_INTEREST_RATE, 0),LOAN_INTEREST_TYPE "
					   +"INT_TYPE, HRMS_LOAN_CLOSURE.LOAN_APPL_CODE, HRMS_LOAN_CLOSURE.LOAN_CLOSURE_CODE, "
					   +"LOAN_PAID_AMOUNT, LOAN_BALANCE_AMOUNT FROM HRMS_LOAN_CLOSURE " 
					   +"INNER JOIN HRMS_LOAN_PROCESS ON (HRMS_LOAN_CLOSURE.LOAN_APPL_CODE = HRMS_LOAN_PROCESS.LOAN_APPL_CODE) " 
					   +"INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_PROCESS.LOAN_APPL_CODE) "
					   +"INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = LOAN_EMP_ID) " 
					   +"INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					   +"INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "; 
					   
		if(loanClosure.isGeneralFlag()){
			query += " WHERE HRMS_LOAN_APPLICATION.LOAN_EMP_ID = "+loanClosure.getUserEmpId();
		}
		
		query += " ORDER BY HRMS_LOAN_CLOSURE.LOAN_APPL_CODE ASC, HRMS_LOAN_CLOSURE.LOAN_CLOSURE_CODE DESC";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("employee.id"), getMessage("employee"),getMessage("sancDate"), getMessage("sancAmnt"), 
								getMessage("loanDatePC"),getMessage("perpAmnt")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"15", "25", "15", "15", "15", "15"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"empToken", "empName", "date", "sanctionAmount", "closureDate", "amtPaidByEmp", "empCode", 
									"branchName", "deptName", "interestRate", "interestType", "loanAppCode", "loanClosureCode", 
									 "amountPaid", "balanceAmount"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LoanClosure_showInstallmentSchedule.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}

}
