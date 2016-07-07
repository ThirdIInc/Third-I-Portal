/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.LoanInstallment;

import org.paradyne.model.admin.acr.AssessmentModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.paradyne.model.payroll.LoanInstallmentModel;
 import org.struts.lib.ParaActionSupport;

/**
 * @author riteshr
 *
 */
public class LoanInstallmentAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	LoanInstallment  loanInstallment = null;
	 public Object getModel() {
		logger.info("installment  getmodel");
		
		return loanInstallment;
	}
	
	/**
	 * @return the loanInstallment
	 */
	public LoanInstallment getLoanInstallment() {
		logger.info("inst getLoanInstallment***");
		return loanInstallment;
	}
	/**
	 * @param loanInstallment the loanInstallment to set
	 */
	public void setLoanInstallment(LoanInstallment loanInstallment) {
		logger.info("inst setLoanInstallment");
		this.loanInstallment = loanInstallment;
	}
	
	public void prepare_local () throws Exception{
		logger.info("inst prepare");
		loanInstallment = new LoanInstallment();
		loanInstallment.setMenuCode(122);
	}
	/*
	 * following method is for general user login who can view only his/her records. 
	 * 
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		if (loanInstallment.isGeneralFlag()) {
			LoanInstallmentModel model = new LoanInstallmentModel();
			model.initiate(context,session);
			model.getEmployeeDetails(loanInstallment.getUserEmpId(),loanInstallment);
			model.getLoanDetails(loanInstallment);
			model.terminate();
		}
	}
	/*
	 * following method is for saving and modifying the records.
	 * when Id is not present then add the new record
	 * otherwise  modify the existing record.
	 */
	
	public String save() throws Exception{
		LoanInstallmentModel model = new LoanInstallmentModel();
		model.initiate(context,session);
		String result= "";
		if(loanInstallment.getInstallCode().equals("")){
			logger.info("ritesh in save() for add ");
			result = model.addLoanInstallment(loanInstallment);
			
		}else{
			logger.info("ritesh in save() for mod ");
			result = model.modLoanInstallment(loanInstallment);
			
		}
		model.getLoanDetails(loanInstallment);
		model.terminate();
		
		
		loanInstallment.setBalanceInstallNo("");
		loanInstallment.setTotalInstallNo("");
		loanInstallment.setLoanRecoveryType("");
		loanInstallment.setLoanCode("");
		loanInstallment.setLoanStartDate("");
		loanInstallment.setInterestBalanceAmount("");
		loanInstallment.setInterestRate("");
		loanInstallment.setMonthInstallAmount("");
		loanInstallment.setInstallCode("");
		loanInstallment.setPenaltyInterestAmount("");
		loanInstallment.setTotalInterestAmount("");
		loanInstallment.setTotalPrincipalAmount("");
		loanInstallment.setPrincipleBalanceAmount("");
		loanInstallment.setLoanType("");
		
	
		addActionMessage(result);
		
		
		return "success";
	}
	
	/*
	 * following method returns the employee name ,id and token no by f9 window . 
	 * 
	 */
	public String f9actionEmployee (){
		logger.info("ritesh in f9action1 Loan");
		String query = " SELECT EMP_TOKEN,(TITLE_NAME||' '||EMP_FNAME||'  '|| EMP_MNAME|| '  ' ||EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC "
			+" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE  =HRMS_TITLE.TITLE_CODE)  " 
			+" INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER ";
					query += getprofileQuery(loanInstallment);
					query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		String []headers ={"Token No.","Employee Name"};
		String []headerwidth={"8","20"};
		String []fieldNames={"loanInstallment.tokenNo","loanInstallment.empName","loanInstallment.loanEmpId"};
		int []columnIndex={0,1,2};
		String submitFlage ="true";
		String submitToMethod = "LoanInstallment_loanRecord.action";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		
		
		return "f9page";
		
	}
	
	/*
	 * following method returns the employee name ,id and token no by f9 window For Loan Details Report Form. 
	 * 
	 */
	public String f9actionEmployeeForReport (){
		logger.info("ritesh in f9action1 Loan");
		String query = " SELECT EMP_TOKEN,(TITLE_NAME||' '||EMP_FNAME||'  '|| EMP_MNAME|| '  ' ||EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC "
			+" INNER JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE  =HRMS_TITLE.TITLE_CODE)  " 
			+" INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER ";
					query += getprofileQuery(loanInstallment);
					query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		String []headers ={"Token No.","Employee Name"};
		String []headerwidth={"8","20"};
		String []fieldNames={"loanInstallment.tokenNo","loanInstallment.empName","loanInstallment.loanEmpId"};
		int []columnIndex={0,1,2};
		String submitFlage ="false";
		String submitToMethod = "";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		
		
		return "f9page";
		
	}
	/*
	 * following method returns the center id and center name by f9 window For Loan Details Report Form. 
	 * 
	 */
	public String f9actionCenterForReport (){
		logger.info("ritesh in f9actionCenter Loan");
		String query = "SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID ";
		String []headers ={"Center Code","Center Name"};
		String []headerwidth={"5","20"};
		String []fieldNames={"loanInstallment.centerId","loanInstallment.center"};
		int []columnIndex={0,1};
		String submitFlage ="false";
		String submitToMethod = "";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		
		return "f9page";
		
	}
	/*
	 * following method returns the paybill group by f9 window For Loan Details Report Form. 
	 * 
	 */
	public String f9payBill() throws Exception {
		logger.info("f9payBill");
		
		String query = " SELECT PAYBILL_ID FROM HRMS_PAYBILL ";
		String[] headers = { "PAY BILL NO" };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "loanInstallment.payBilGrp"};
		int[] columnIndex = { 0 };

		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/*
	 * following method returns the loan id and loan type by f9 window . 
	 * 
	 */
	public String f9actionLoan (){
		logger.info("ritesh in f9action2 Loan");
		String query = "SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE ";
		String []headers ={"Debit Code","Debit Name"};
		String []headerwidth={"5","20"};
		String []fieldNames={"loanInstallment.loanCode","loanInstallment.loanType"};
		int []columnIndex={0,1};
		String submitFlage ="false";
		String submitToMethod = "";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		
		return "f9page";
		
	}
	/*
	 * following method returns the center,Rank and all the records of a selected employee. . 
	 * 
	 */
	public String loanRecord(){
		logger.info("ritesh in loanRecord Loan");
		LoanInstallmentModel model = new LoanInstallmentModel();
		model.initiate(context,session);
		model.getLoanDetails(loanInstallment);
		model.terminate();
		return "success";
	}
	/*
	 * following method returns the particular records of an employee after pressing the 
	 * respective edit button for the modification purpose . 
	 * 
	 */
	public String  edit(){
		logger.info("ritesh in edit Loan");
		LoanInstallmentModel model = new LoanInstallmentModel();
		model.initiate(context,session);
		model.getOneRecord(loanInstallment);
		model.getLoanDetails(loanInstallment);
		model.terminate();
		
		return "success";
		
	}
	/*
	 * following method is for  delete the record of an employee after pressing the 
	 * respective delete button  
	 * 
	 */ 
	public String delete()throws Exception{
		logger.info("ritesh in delete loanInstallment");
		LoanInstallmentModel model = new LoanInstallmentModel();
		model.initiate(context,session);
		String result = model.deleteLoan(loanInstallment); 
		model.getLoanDetails(loanInstallment);
		model.terminate();
		addActionMessage(result);
		return "success";
		
		
	}
	 /*
	  * following method is for  generating the report from the 
	  * separate form (jsp file name -> loanInstallmentReport.jsp)
	  * called  loan details report . 
	  */ 
	public String report()throws Exception{
		logger.info("ritesh in report() loanInstallment");
		LoanInstallmentModel model = new LoanInstallmentModel();
		model.initiate(context,session);
		model.generateReport(loanInstallment);
		model.terminate();
		return "success";
	}
	
	 /*
	  * following method is for  reseting the record of an employee after pressing the 
	  * respective reset button  
	  * 
	  */ 
	public String reset(){
		try{
			loanInstallment.setLoanEmpId("");
			loanInstallment.setEmpName("");
			loanInstallment.setTokenNo("");
			loanInstallment.setBalanceInstallNo("");
			loanInstallment.setLoanRecoveryType("");
			loanInstallment.setLoanCode("");
			loanInstallment.setLoanStartDate("");
			loanInstallment.setInterestBalanceAmount("");
			loanInstallment.setInterestRate("");
			loanInstallment.setInstallList(null);
			loanInstallment.setMonthInstallAmount("");
			loanInstallment.setInstallCode("");
			loanInstallment.setPenaltyInterestAmount("");
			loanInstallment.setTotalInterestAmount("");
			loanInstallment.setTotalInstallNo("");
			loanInstallment.setTotalPrincipalAmount("");
			loanInstallment.setPrincipleBalanceAmount("");
			loanInstallment.setLoanType("");
			loanInstallment.setRank("");
			loanInstallment.setCenter("");
			loanInstallment.setParacode("");
			loanInstallment.setPayBilGrp("");
			loanInstallment.setStatus("Select");
			
						
		}catch (Exception e) {
			e.printStackTrace();
		
		}
		return "success";
		
	}

}
