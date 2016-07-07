package org.paradyne.model.payroll;

import java.util.ArrayList;



import org.paradyne.bean.payroll.LoanInstallment;
 import org.paradyne.lib.ModelBase;

public class LoanInstallmentModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	String done="";
	
	
	/*
	 * following method is being called from the Action class 
	 * for saving the new records.
	 */
	public String addLoanInstallment(LoanInstallment loanInstallment){
		logger.info("addLoanInstallment");
		if(loanInstallment.isInsertFlag()){
		Object addObj[][] = new Object[1][13];
		
		addObj[0][0] = loanInstallment.getLoanCode();
		addObj[0][1] = loanInstallment.getLoanStartDate();
		addObj[0][2] = loanInstallment.getLoanRecoveryType();
		addObj[0][3] = loanInstallment.getInterestRate();
		addObj[0][4] = loanInstallment.getTotalPrincipalAmount();
		addObj[0][5] = loanInstallment.getTotalInterestAmount();
		addObj[0][6] = loanInstallment.getMonthInstallAmount();
		addObj[0][7] = loanInstallment.getInterestBalanceAmount();
		addObj[0][8] = loanInstallment.getPrincipleBalanceAmount();
		addObj[0][9] = loanInstallment.getTotalInstallNo();
		addObj[0][10] = loanInstallment.getBalanceInstallNo();
		addObj[0][11] = loanInstallment.getPenaltyInterestAmount();
		addObj[0][12] = loanInstallment.getLoanEmpId();

		boolean result= getSqlModel().singleExecute(getQuery(1), addObj);
			if(result){
				return "Data saved Successfully";
			}else{
				return "Record can not be added";
			}
		}else {
			return "Permission denied to insert record";
		}
		
	}
	
	/*
	 * following method is being called from the Action class 
	 * for modifing the perticular record of an employee.
	 */
	
	public String modLoanInstallment(LoanInstallment loanInstallment){
		if(loanInstallment.isUpdateFlag()){
		Object modObj[][] = new Object[1][14];
		
		modObj[0][0] = loanInstallment.getLoanCode();
		modObj[0][1] = loanInstallment.getLoanStartDate();
		modObj[0][2] = loanInstallment.getLoanRecoveryType();
		modObj[0][3] = loanInstallment.getInterestRate();
		modObj[0][4] = loanInstallment.getTotalPrincipalAmount();
		modObj[0][5] = loanInstallment.getTotalInterestAmount();
		modObj[0][6] = loanInstallment.getMonthInstallAmount();
		modObj[0][7] = loanInstallment.getInterestBalanceAmount();
		modObj[0][8] = loanInstallment.getPrincipleBalanceAmount();
		modObj[0][9] = loanInstallment.getTotalInstallNo();
		modObj[0][10] = loanInstallment.getBalanceInstallNo();
		modObj[0][11] = loanInstallment.getPenaltyInterestAmount();
		modObj[0][12] = loanInstallment.getLoanEmpId();
		modObj[0][13] = loanInstallment.getInstallCode();
		
		logger.info("loanInstallment.getInstallCode(); ********"+loanInstallment.getInstallCode());
		
		boolean result = getSqlModel().singleExecute(getQuery(4), modObj);
		if(result){
			return "Record updated successfully";
		}else{
			return "Record can not be updated ";
		}
	}else {
		return "Permission denied to update record";
	}
	}
	/*
	 * following method is being called from the Action class 
	 * for display the records for selected employee.
	 */	
	
	public void getLoanDetails(LoanInstallment loanInstallment){
		logger.info("getLoanDetails");
		Object [] empcode = new Object[1];
		empcode[0] = loanInstallment.getLoanEmpId();
		logger.info("EMPCODE : "+loanInstallment.getLoanEmpId());
		Object[][] centerNrank = getSqlModel().getSingleResult(getQuery(7),empcode);
		loanInstallment.setCenter(String.valueOf(centerNrank[0][0]));
		loanInstallment.setRank(String.valueOf(centerNrank[0][1]));
		Object[][] data = getSqlModel().getSingleResult(getQuery(2),empcode);
		ArrayList<Object> list = new ArrayList<Object>();
		if(null != data && data.length>0){
			for(int i =0;i<data.length;i++){
				LoanInstallment bean = new LoanInstallment();
				
				bean.setLoanType(String.valueOf(data[i][0]));
				bean.setLoanStartDate(String.valueOf(data[i][1]));
				bean.setLoanRecoveryType(String.valueOf(data[i][2]));
				if(String.valueOf(data[i][3]).equals("null")) {
					bean.setInterestRate("");	
					
				} else {
				bean.setInterestRate(String.valueOf(data[i][3]));
				}
				bean.setTotalPrincipalAmount(String.valueOf(data[i][4]));
				bean.setTotalInterestAmount(String.valueOf(data[i][5]));
				if(String.valueOf(data[i][6]).equals("null")||(data[i][6]==null) || (data[i][6].equals(""))){
					bean.setMonthInstallAmount("");
				}else{
					bean.setMonthInstallAmount(String.valueOf(data[i][6]));
				}
				if(String.valueOf(data[i][7]).equals("null")||(data[i][7]==null) || (data[i][7].equals(""))){
					bean.setInterestBalanceAmount("");
				}else{
					bean.setInterestBalanceAmount(String.valueOf(data[i][7]));
				}
				if(String.valueOf(data[i][8]).equals("null")||(data[i][8]==null) || (data[i][8].equals(""))){
					bean.setPrincipleBalanceAmount("");
				}else{
					bean.setPrincipleBalanceAmount(String.valueOf(data[i][8]));
				}
				bean.setTotalInstallNo(String.valueOf(data[i][9]));
				if(String.valueOf(data[i][10]).equals("null")||(data[i][10]==null) || (data[i][10].equals(""))){
					bean.setBalanceInstallNo("");
				}else{
					bean.setBalanceInstallNo(String.valueOf(data[i][10]));
				}
				if(String.valueOf(data[i][11]).equals("null")||(data[i][11]==null) || (data[i][11].equals(""))){
					bean.setPenaltyInterestAmount("");
				}else{
					bean.setPenaltyInterestAmount(String.valueOf(data[i][11]));
				}
								
				bean.setInstallCode(String.valueOf(data[i][12]));
					
				list.add(bean);
				
			}
			loanInstallment.setInstallList(list);
		}
	}
	/*
     * following method is being called from the Action class 
     * for selecting the perticular record which is going to be  modify.
     */ 
	public void getOneRecord(LoanInstallment loanInstallment){
		
		Object [] installId = new Object[1];
		installId[0] = loanInstallment.getParacode();
		logger.info("in getOneRecord**** id = "+ loanInstallment.getParacode());
		Object[][] data = getSqlModel().getSingleResult(getQuery(3),installId);
		
		loanInstallment.setLoanType(String.valueOf(data[0][0]));
		loanInstallment.setLoanStartDate(String.valueOf(data[0][1]));
		loanInstallment.setLoanRecoveryType(String.valueOf(data[0][2]));
		if(String.valueOf(data[0][3]).equals("null")) {
			
			loanInstallment.setInterestRate("");
		}else {
		loanInstallment.setInterestRate(String.valueOf(data[0][3]));
		}
		loanInstallment.setTotalPrincipalAmount(String.valueOf(data[0][4]));
		loanInstallment.setTotalInterestAmount(String.valueOf(data[0][5]));
		if(String.valueOf(data[0][6]).equals("null")||(data[0][6]==null) || (data[0][6].equals(""))){
			loanInstallment.setMonthInstallAmount("");
		}else{
			loanInstallment.setMonthInstallAmount(String.valueOf(data[0][6]));
		}
		if(String.valueOf(data[0][7]).equals("null")||(data[0][7]==null) || (data[0][7].equals(""))){
			loanInstallment.setInterestBalanceAmount("");
		}else{
			loanInstallment.setInterestBalanceAmount(String.valueOf(data[0][7]));
		}
		if(String.valueOf(data[0][8]).equals("null")||(data[0][8]==null) || (data[0][8].equals(""))){
			loanInstallment.setPrincipleBalanceAmount("");
		}else{
			loanInstallment.setPrincipleBalanceAmount(String.valueOf(data[0][8]));
		}
		loanInstallment.setTotalInstallNo(String.valueOf(data[0][9]));
		if(String.valueOf(data[0][10]).equals("null")||(data[0][10]==null) || (data[0][10].equals(""))){
			loanInstallment.setBalanceInstallNo("");
		}else{
			loanInstallment.setBalanceInstallNo(String.valueOf(data[0][10]));
		}
		if(String.valueOf(data[0][11]).equals("null")||(data[0][11]==null) || (data[0][11].equals(""))){
			loanInstallment.setPenaltyInterestAmount("");
		}else{
			loanInstallment.setPenaltyInterestAmount(String.valueOf(data[0][11]));
		}
		
		loanInstallment.setLoanEmpId(String.valueOf(data[0][12]));
		loanInstallment.setLoanCode(String.valueOf(data[0][13]));
		loanInstallment.setInstallCode(loanInstallment.getParacode());
		
	}
	/*
	 * following method is being called from the Action class 
	 * for deleting the perticular record of an employee.
	 */
public String deleteLoan(LoanInstallment bean){
	if(bean.isDeleteFlag()){
		Object [][]delObj = new Object[1][1];
		delObj[0][0]= bean.getParacode();
	    boolean result=  getSqlModel().singleExecute(getQuery(5), delObj);
	    if(result){
			return "Data Deleted successfully";
		}else{
			return "Record can not be deleted";
		}
	}else {
		return "Permission denied to delete record";
	}		
}

/*
 * following method is being called from the Action class 
 * for generating the reports in XLS of Loan Installment with 
 * various filers like employee wise, center wise, 
 * loan type wise, paybill group wise and completed/pending . .
 */
public String generateReport(LoanInstallment loanInstallment) {
	logger.info("generateReport**** LoanInstallment ");
	
	String[] colNames={"S.NO","EMPLOYEE NAME","CENTER","RANK","LOAN TYPE","LOAN START DATE","LOAN RECOVERY TYPE",
			"RATE OF INTEREST","TOTAL PRINCIPAL AMOUNT","TOTAL INTEREST AMOUNT","MONTHLY INSTALLMENT AMOUNT","INTEREST BALANCE AMOUNT",
			"PRINCIPAL BALANCE AMOUNT","TOTAL INSTALLMENT NO.","BALANCE INSTALLMENT NO.","PENALTY INTEREST AMOUNT"};
	
	String loanQuery = " SELECT ROWNUM,(TITLE_NAME||' '||EMP_FNAME||'  '|| EMP_MNAME|| '  ' ||EMP_LNAME),(CENTER_ID||'-'||CENTER_NAME),RANK_NAME, "
					+" DEBIT_NAME,TO_CHAR(LOAN_DATE,'DD-MM-YYYY'),NVL(DECODE(LOAN_RECOVERY_TYPE,'0',' ','1','Principal First','2','Interest  First','3','Both'),' '),LOAN_INTEREST_RATE, " 
					+" LOAN_AMOUNT,LOAN_INTEREST,NVL(INSTALL_MONTHLY_PRINCIPAL,''),NVL(INSTALL_MONTHLY_INTEREST,''),NVL(LOAN_BALANCE_AMOUNT,''),INSTALL_TOTAL_NUMBER,NVL(INSTALL_BALANCE_NUMBER,'') , "
					+" NVL(LOAN_PENALTY_INTEREST_AMOUNT,'') FROM HRMS_EMP_INSTALLMENTS "
					+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_INSTALLMENTS.EMP_ID =  HRMS_EMP_OFFC.EMP_ID) "
					+" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE  =HRMS_TITLE.TITLE_CODE) "
					+" INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_EMP_INSTALLMENTS.LOAN_TYPE =  HRMS_DEBIT_HEAD.DEBIT_CODE) "
					+" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
					+" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+" INNER JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID) ";
					
	
	String empId = loanInstallment.getLoanEmpId();
	String center = loanInstallment.getCenterId();
	String loanType = loanInstallment.getLoanCode();
	String status = loanInstallment.getStatus();
	String payBillGroup = loanInstallment.getPayBilGrp(); 
	
	logger.info("empId :*: "+empId); 
	logger.info("center :*: "+center); 
	logger.info("loanType :*: "+loanType); 
	logger.info("status :*: "+status); 
	logger.info("payBillGroup :*: "+payBillGroup); 
	
	
	String where = "WHERE";   

	if (empId != null && empId.length() != 0) {
		where = where + " HRMS_EMP_INSTALLMENTS.EMP_ID  = "+ empId;
		if (loanType != null && loanType.length() != 0) {
			where = where + " AND HRMS_DEBIT_HEAD.DEBIT_CODE  = "+ loanType;
		}if (payBillGroup != null && payBillGroup.length() != 0) {
			where = where + " AND HRMS_SALARY_MISC.SAL_PAYBILL_NO  = "+ payBillGroup;
		}if (status != null && status.length() != 0) {
			if(status.equals("Pending")){
			where = where + " AND HRMS_EMP_INSTALLMENTS.LOAN_BALANCE_AMOUNT  > 0.0 ";
			}else if(status.equals("Completed")){
			where = where + " AND HRMS_EMP_INSTALLMENTS.LOAN_BALANCE_AMOUNT  <= 0.0 ";	
			}
		}
		
	}else{
		if (center != null && center.length() != 0) {
			where = where + " HRMS_CENTER.CENTER_ID  = "+ center;
			if (loanType != null && loanType.length() != 0) {
				where = where + " AND HRMS_DEBIT_HEAD.DEBIT_CODE  = "+ loanType;
			}if (payBillGroup != null && payBillGroup.length() != 0) {
				where = where + " AND HRMS_SALARY_MISC.SAL_PAYBILL_NO  = "+ payBillGroup;
			}if (status != null && status.length() != 0) {
				if(status.equals("Pending")){
				where = where + " AND HRMS_EMP_INSTALLMENTS.LOAN_BALANCE_AMOUNT  > 0.0 ";
				}else if(status.equals("Completed")){
				where = where + " AND HRMS_EMP_INSTALLMENTS.LOAN_BALANCE_AMOUNT  <= 0.0 ";	
				}
			}	
		}else{
			if (loanType != null && loanType.length() != 0) {
				where = where + " HRMS_DEBIT_HEAD.DEBIT_CODE  = "+ loanType;
				if (payBillGroup != null && payBillGroup.length() != 0) {
					where = where + " AND HRMS_SALARY_MISC.SAL_PAYBILL_NO  = "+ payBillGroup;
				}if (status != null && status.length() != 0) {
					if(status.equals("Pending")){
					where = where + " AND HRMS_EMP_INSTALLMENTS.LOAN_BALANCE_AMOUNT  > 0.0 ";
					}else if(status.equals("Completed")){
					where = where + " AND HRMS_EMP_INSTALLMENTS.LOAN_BALANCE_AMOUNT  <= 0.0 ";	
					}
				}	
			}else{
				if (status != null && status.length() != 0) {
					if(status.equals("Pending")){
					where = where + " HRMS_EMP_INSTALLMENTS.LOAN_BALANCE_AMOUNT  > 0.0 ";
					if (payBillGroup != null && payBillGroup.length() != 0) {
						where = where + " AND HRMS_SALARY_MISC.SAL_PAYBILL_NO  = "+ payBillGroup;
					}
					}else if(status.equals("Completed")){
					where = where + " HRMS_EMP_INSTALLMENTS.LOAN_BALANCE_AMOUNT  <= 0.0 ";
					if (payBillGroup != null && payBillGroup.length() != 0) {
						where = where + " AND HRMS_SALARY_MISC.SAL_PAYBILL_NO  = "+ payBillGroup;
					}
					}
				}else{
					if (payBillGroup != null && payBillGroup.length() != 0) {
						where = where + " HRMS_SALARY_MISC.SAL_PAYBILL_NO  = "+ payBillGroup;
					}
					
				}
				
			}
			
		}
		
	}
		
	if(!(where.equals("WHERE"))){
		loanQuery=loanQuery+where;   
	}
	
	Object loanData[][] = getSqlModel().getSingleResult(loanQuery);
	for(int i = 0;i<loanData.length;i++){
		for(int j = 0;j<16;j++){
			if(String.valueOf(loanData[i][j]).equals("null")||(loanData[i][j]==null) || (loanData[i][j].equals(""))){
				loanData[i][j] = "";
			}
		}
	}
	
	logger.info("query------------------->>>> "+loanQuery);
	
	
	String reportType="Xls";
	String reportName="Loan Installment Report";
	int [] cellWidth={25,45,25,25,25,25,25,22,22,22,25,25,25,25,25,25};
	int [] alignment={1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	
	org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
	rg.addText("Loan Installment Report", 0, 1, 0);
	/*String message[]={"Loan Installment Report"};
	int style[]={2};
	rg.addFormatedText(message, style, 1,1,1);	*/
	
	rg.genHeader("");
	rg.tableBody(colNames,loanData,cellWidth,alignment);
	
	done=rg.createReport();
	String file="LoanInstallmentReport";
	new org.paradyne.lib.report.Report().showReport("C:/"+file+".Xls");
	
	return done;
	
	}
/*
 * following method is being called from the Action class 
 * for display the general user records after general user login.
 */
public void  getEmployeeDetails(String empId, LoanInstallment loanInstallment){
	Object[] empcode = new Object[1];
	empcode[0] =empId ;
	
	Object[][] genValues = getSqlModel().getSingleResult(getQuery(7),empcode);
	loanInstallment.setCenter(String.valueOf(genValues[0][0]));
	loanInstallment.setRank(String.valueOf(genValues[0][1]));
	loanInstallment.setLoanEmpId(String.valueOf(genValues[0][2]));
	loanInstallment.setEmpName(String.valueOf(genValues[0][3]));
	
	}




}
