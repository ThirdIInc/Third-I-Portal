package org.paradyne.model.payroll;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;
import org.paradyne.bean.payroll.EmpSalaryReg;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
//import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * author:Pradeep
 * Date:14-05-2008
 */

public class EmpSalRegModel extends ModelBase {
	NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.paradyne.model.payroll.EmpSalRegModel.class);
	
	public String checkNullValue(final String result) {
		if (result == null || "null".equals(result)) {
			return "0";
		} else {
			return result;
		}
	}
	
	/**
	 * following function is used to calculate the total arrear credit amount for a particular month and year
	 * This function is called in the getSalary Function.
	 * @param year
	 * @param empId
	 * @param month
	 * @return
	 */
	
	public Object[][] getArrears(String year,String empId,String month,String salaryStatus,Object[][] creditCode){
		Object[][]	 arrears_amount = null;
		String  arrearsQuery="SELECT CREDIT_NAME,NVL(SUM(ARREARS_AMT),0.00),CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END,credit_code FROM HRMS_ARREARS_CREDIT_"+year
		+" INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) "
		+" INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ARREARS_CREDIT_"+year+".ARREARS_CREDIT_CODE) "
		//+" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND ARREARS_PAID_MONTH ="+month+" AND ARREARS_PAID_YEAR = "+year+" AND ARREARS_EMP_ID="+empId+" GROUP BY credit_code,CREDIT_NAME,CREDIT_TAXABLE_FLAG";
		+" WHERE CREDIT_PAYFLAG='Y' AND ARREARS_PAID_MONTH ="+month+" AND ARREARS_PAID_YEAR = "+year+" AND ARREARS_EMP_ID="+empId+" GROUP BY credit_code,CREDIT_NAME,CREDIT_TAXABLE_FLAG";
		if(salaryStatus.equals("Final")){
			arrears_amount  = getSqlModel().getSingleResult(arrearsQuery);
		}
		 Object [][]returnObj = null;
	  if(arrears_amount==null || arrears_amount.length==0){
		  String creditAmtHead="SELECT HRMS_CREDIT_HEAD.CREDIT_NAME,0.00,CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END,HRMS_CREDIT_HEAD.CREDIT_CODE  FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_EMP_CREDIT.CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE and EMP_ID="+empId+" ) "
		 //  +" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
		  +" WHERE CREDIT_PAYFLAG='Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
		
		  Object[][] credit_amount  = getSqlModel().getSingleResult(creditAmtHead);
		  returnObj = credit_amount;
		  
	  }else {
		  returnObj = arrears_amount ;
	  }
	  		returnObj = compareObjectSal(creditCode, returnObj);
	  		return returnObj;
	}
	/**
	 * following function is used to calculate the total arrear credit amount for a particular month and year
	 * This function is called in the getSalary Function.
	 * @param year
	 * @param empId
	 * @param month
	 * @return
	 */
	
	public Object[][] getArrearsDebit(String year,String empId,String month,String salaryStatus,Object[][] debitCode){
		Object[][]	 arrears_amount = null;
		String  arrearsQuery="SELECT DEBIT_NAME,NVL(SUM(ARREARS_AMT),0.00),'Yes',DEBIT_CODE FROM HRMS_ARREARS_DEBIT_"+year
						+" INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) "
						+" INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE) "
						+" WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' AND ARREARS_PAID_MONTH ="+month+" AND ARREARS_PAID_YEAR = "+year+" AND ARREARS_EMP_ID="+empId+" GROUP BY DEBIT_CODE,DEBIT_NAME";
		if(salaryStatus.equals("Final")){
			arrears_amount  = getSqlModel().getSingleResult(arrearsQuery);
		}
		 Object [][]returnObj = null;
	  if(arrears_amount==null || arrears_amount.length==0){
		  String debitAmtHead="SELECT HRMS_DEBIT_HEAD.DEBIT_NAME,0.00,'Yes',HRMS_DEBIT_HEAD.DEBIT_CODE  FROM HRMS_DEBIT_HEAD  LEFT JOIN HRMS_EMP_DEBIT ON (HRMS_EMP_DEBIT.DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE and EMP_ID="+empId+" ) "
		   +" WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE ";
		
		  Object[][] debit_amount  = getSqlModel().getSingleResult(debitAmtHead);
		  returnObj = debit_amount;
		  
	  }else {
		  returnObj = arrears_amount ;
	  }
	  		returnObj = compareObjectSal(debitCode, returnObj);
	  		return returnObj;
	}
	                
	
	/**
	 * Following function is called to get the credit head and credit amount from hrms_credit_head 
	 * @param empId
	 * @return
	 */
	public Object[][] getCredit(String empId) {
		Object[][] credit_amount=null;
		
		try{
			   String creditAmtHead="SELECT HRMS_CREDIT_HEAD.CREDIT_NAME,NVL(CREDIT_AMT,0),CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END  FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_EMP_CREDIT.CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE and EMP_ID="+empId+" ) "
			  // +" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
			   +" WHERE CREDIT_PAYFLAG='Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
			   credit_amount  = getSqlModel().getSingleResult(creditAmtHead);
		
			
						
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	
	
		return credit_amount;
		
	}
	
	
	/**
	 * following function is called to calculate the total salary amount for a credit component
	 * @param empId
	 * @param year
	 * @param ledgerCode
	 * @param salaryStatus
	 * @param creditCode
	 * @return
	 */
	
	public Object[][] getCreditSalAmt(String empId,int year,int month, String ledgerCode,String salaryStatus,Object [][]creditCode) {
		String creditAmtQuery ="";
		
		/*String checkSettlementQuery = "SELECT DISTINCT HRMS_SETTL_CREDITS.SETTL_CODE FROM HRMS_SETTL_CREDITS "
					+" LEFT JOIN HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_CREDITS.SETTL_CODE)"
					+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
					+" WHERE HRMS_SETTL_CREDITS.SETTL_MTH="+month
					+" AND HRMS_SETTL_CREDITS.SETTL_YEAR ="+year+" AND HRMS_RESIGN.RESIGN_EMP="+empId;
		
		Object checkSettlementObj [][]=getSqlModel().getSingleResult(checkSettlementQuery) ;*/
		
		/*
		 * if data is present in the settlement for that month then credit amount will be zero
		 
		if(!(checkSettlementObj == null || checkSettlementObj.length <= 0)){						
			creditAmtQuery ="SELECT HRMS_CREDIT_HEAD.CREDIT_ABBR,0.00,CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END, HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD "
			   +" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
			
		}
		
		 * if data is not present in the settlement & salary is done for that month
		 * then get credit amount from salary table(HRMS_SAL_CREDITS_YEAR)
		 * 
		 
		 
		else */
			
			if(!salaryStatus.equals("")){
			creditAmtQuery="SELECT CREDIT_NAME,CASE WHEN SAL_ONHOLD='Y' THEN 0 ELSE NVL(SAL_AMOUNT,0) END,CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END,HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD  " 
				+" LEFT JOIN HRMS_SAL_CREDITS_"+year+" ON (SAL_CREDIT_CODE = CREDIT_CODE AND EMP_ID="+empId+" AND SAL_LEDGER_CODE IN("+ledgerCode+")) "
				+" LEFT JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".SAL_LEDGER_CODE=HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE AND EMP_ID="+empId+" )" 
				//+" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE  ";
				+" WHERE CREDIT_PAYFLAG='Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE  ";
		}
		/*
		 * if data is not present in the settlement & salary is not done for that month
		 * then get credit amount from employee credit table(HRMS_EMP_CREDIT)
		 * 
		 */
		else{
			creditAmtQuery ="SELECT HRMS_CREDIT_HEAD.CREDIT_NAME,NVL(CREDIT_AMT,0),CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END, HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_EMP_CREDIT.CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE and EMP_ID="+empId+" ) "
			   +" WHERE CREDIT_PAYFLAG='Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
			//+" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
		}
		
		
		 Object [][]returnObj = getSqlModel().getSingleResult(creditAmtQuery);
		 
		  /*
		   * call compareObjectSal method to compare the credit codes from HRMS_CREDIT_HEAD and local object
		   * 
		   */
		  returnObj = compareObjectSal(creditCode, returnObj);
		  return returnObj ;
     }
	
	/**
	 * following function is called to calculate the total salary amount for a debit component
	 * @param empId
	 * @param year
	 * @param ledgerCode
	 * @param salaryStatus
	 * @param debitCode
	 * @return
	 */
	
	public Object[][] getDebitSalAmt(String empId,int year,int month,String ledgerCode,String salaryStatus,Object [][]debitCode) {
		String debitAmtQuery ="";
		
		/*String checkSettlementQuery = "SELECT DISTINCT HRMS_SETTL_CREDITS.SETTL_CODE FROM HRMS_SETTL_CREDITS "
			+" LEFT JOIN HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_CREDITS.SETTL_CODE)"
			+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
			+" WHERE HRMS_SETTL_CREDITS.SETTL_MTH="+month
			+" AND HRMS_SETTL_CREDITS.SETTL_YEAR ="+year+" AND HRMS_RESIGN.RESIGN_EMP="+empId;

		Object checkSettlementObj [][]=getSqlModel().getSingleResult(checkSettlementQuery) ;*/
		
		/*
		 * if data is present in the settlement for that month then debit amount will be zero
		 */
		
		/*if(!(checkSettlementObj == null || checkSettlementObj.length <= 0)){		
			debitAmtQuery = "SELECT HRMS_DEBIT_HEAD.DEBIT_ABBR,0.00,'Yes',HRMS_DEBIT_HEAD.DEBIT_CODE  FROM HRMS_DEBIT_HEAD  "
							+" WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE  ";
		}
		
		 * if data is not present in the settlement & salary is done for that month
		 * then get DEBIT amount from salary table(HRMS_SAL_DEBITS_YEAR)
		 * 
		 
		else */
			
			if(!salaryStatus.equals("")){									// if salary is done for particular month then data from Salary Table
		 debitAmtQuery="SELECT DEBIT_NAME,NVL(SAL_AMOUNT,0),'Yes',SAL_DEBIT_CODE FROM HRMS_DEBIT_HEAD  INNER JOIN HRMS_SAL_DEBITS_"+year+" ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="+empId+" AND SAL_LEDGER_CODE IN("+ledgerCode+")) "
					+" WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE  ";
		
		}
		/*
		 * if data is not present in the settlement & salary is not done for that month
		 * then get DEBIT amount from employee DEBIT table(HRMS_EMP_DEBIT)
		 * 
		 */
		else{
			debitAmtQuery="SELECT HRMS_DEBIT_HEAD.DEBIT_NAME,NVL(DEBIT_AMT,0),'Yes',HRMS_DEBIT_HEAD.DEBIT_CODE  FROM HRMS_DEBIT_HEAD   LEFT JOIN HRMS_EMP_DEBIT ON (HRMS_EMP_DEBIT.DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE and EMP_ID="+empId+" ) "
			   +" WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE  ";
		}
		
		/*
		 * if salary is not done for that month then get data from HRMS_EMP_DEBIT
		 * 
		 */
		
		
		 Object [][]returnObj = getSqlModel().getSingleResult(debitAmtQuery);
		  /*
		   * call compareObjectSal method to compare the DEBIT codes from HRMS_DEBIT_HEAD and local object
		   * 
		   */
		  returnObj = compareObjectSal(debitCode, returnObj);
		  return returnObj;
     }
	
	/**
	 * following function is called to calculate the total sal amount for a credit component of settlement
	 * @param empCode
	 * @param creditCode
	 * @return settleCreditData
	 */
	/*
	 * will be Used with new database structure used for settlement
	 * 
	 */
	
	public Object [][]getSettlementCredit(String empCode,Object [][]creditCode){
		Object [][]settlCreditData =null;
		String settlPaidByCmpQuery = "SELECT (SETTL_CREDIT_CODE), SUM(SETTL_AMT),CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_SETTL_CREDITS "
							+" LEFT JOIN HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_CREDITS.SETTL_CODE)"
							+" LEFT JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=SETTL_CREDIT_CODE) "
							+" WHERE SETTL_ECODE ="+empCode+" AND SETTL_MTH_TYPE !='EM'" 
							+" GROUP BY SETTL_CREDIT_CODE,CREDIT_TAXABLE_FLAG ORDER BY HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE";
		
		settlCreditData = compareObject(creditCode, getSqlModel().getSingleResult(settlPaidByCmpQuery));
		
		/*	String settlPaidByEmpQuery = "SELECT (SETTL_CREDIT_CODE), SUM(SETTL_AMT),CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_SETTL_CREDITS "
							+" LEFT JOIN HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_CREDITS.SETTL_CODE)"
							+" LEFT JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=SETTL_CREDIT_CODE) "
							+" WHERE SETTL_ECODE ="+empCode+" AND SETTL_MTH_TYPE ='EM'" 
							+" GROUP BY SETTL_CREDIT_CODE,CREDIT_TAXABLE_FLAG ORDER BY HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE";
		 
		Object settlPaidByEmpObj [][] = compareObject(creditCode, getSqlModel().getSingleResult(settlPaidByEmpQuery));
		
		
		
		for (int i = 0; i < settlCreditData.length; i++) {
			settlCreditData [i][1] = Double.parseDouble(String.valueOf(settlCreditData[i][1]))- Double.parseDouble(String.valueOf(settlPaidByEmpObj[i][1]));
		}
		*/
		
		return settlCreditData;
	}
	
	
	/**
	 * following function is called to calculate the total sal amount for a debit component of settlement
	 * @param empCode
	 * @param debitCode
	 * @return settleDebitData
	 */
	public Object [][]getSettlementDebit(String empCode,Object [][]debitCode){
		Object [][]settleDebitData =null;
		String settlementCmpQuery = "SELECT (SETTL_DEBIT_CODE), SUM(SETTL_AMT),'Yes' FROM HRMS_SETTL_DEBITS "
								+" LEFT JOIN HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_DEBITS.SETTL_CODE) "
								+" WHERE SETTL_ECODE ="+empCode+ " AND SETTL_MTH_TYPE !='EM' "
								+" GROUP BY SETTL_DEBIT_CODE ORDER BY HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE";
		
		settleDebitData = compareObject(debitCode,getSqlModel().getSingleResult(settlementCmpQuery));
		
		/*String settlementEmpQuery = "SELECT (SETTL_DEBIT_CODE), SUM(SETTL_AMT),'Yes' FROM HRMS_SETTL_DEBITS "
			+" LEFT JOIN HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_DEBITS.SETTL_CODE) "
			+" WHERE SETTL_ECODE ="+empCode+ " AND SETTL_MTH_TYPE ='EM' "
			+" GROUP BY SETTL_DEBIT_CODE ORDER BY HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE";
		
		Object settlPaidByEmpObj [][]= compareObject(debitCode, getSqlModel().getSingleResult(settlementEmpQuery));
		
		for (int i = 0; i < settleDebitData.length; i++) {
			settleDebitData [i][1] = Double.parseDouble(String.valueOf(settleDebitData[i][1]))- Double.parseDouble(String.valueOf(settlPaidByEmpObj[i][1]));
		}*/
		
		return settleDebitData;
		
	}
	
	/*
	 * end will be Used with new database structure used for settlement
	 * 
	 */
	
	/*
	 * start code to be used  Used with old database structure used for settlement
	 * 
	 */
	/*
	/**
	 * following function is called to calculate the total sal amount for a credit component of settlement
	 * @param empCode
	 * @param creditCode
	 * @return settleCreditData
	 */
	
	/*public Object [][]getSettlementCredit(String empCode,Object [][]creditCode){
		Object [][]settleCreditData =null;
		try{
		String settlementQuery = "SELECT (SETTL_CREDIT_CODE), SUM(SETTL_AMT),CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_SETTL_CREDITS "
						+" LEFT JOIN HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_CREDITS.SETTL_CODE)"
						+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
						+" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=SETTL_CREDIT_CODE)"
						+" WHERE HRMS_RESIGN.RESIGN_EMP ="+empCode+" AND SETTL_MTH_TYPE !='S'"
						+" GROUP BY SETTL_CREDIT_CODE,CREDIT_TAXABLE_FLAG ORDER BY HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE";
		
		settleCreditData = getSqlModel().getSingleResult(settlementQuery);
		
		 //* get the short notice pay data for credit, if it is pay by employee then
		 //* subtract from settleCreditData if its pay by Company then add it to the settleCreditData
		// * 
		 
		String shortNoticeQuery = " SELECT (SETTL_CREDIT_CODE), CASE WHEN SETTL_SHORTPAYFLAG='E' THEN -SUM(SETTL_AMT) ELSE SUM(SETTL_AMT) END,CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END  FROM HRMS_SETTL_CREDITS"
					+" LEFT JOIN HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_CREDITS.SETTL_CODE)"
					+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
					+" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=SETTL_CREDIT_CODE)"
					+" WHERE HRMS_RESIGN.RESIGN_EMP ="+empCode+" AND SETTL_MTH_TYPE ='S'"
					+" GROUP BY SETTL_CREDIT_CODE, SETTL_SHORTPAYFLAG,CREDIT_TAXABLE_FLAG ORDER BY HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE ";
		Object shortNoticeData [][]=getSqlModel().getSingleResult(shortNoticeQuery);
		try{
		for (int i = 0; i < settleCreditData.length; i++) {
			settleCreditData [i][1] = Double.parseDouble(String.valueOf(settleCreditData[i][1]))+ Double.parseDouble(String.valueOf(shortNoticeData[i][1]));
		}
		}catch (Exception e) {
			logger.error("exception in calculating the shortNotice credit"+e);
		}
		settleCreditData = compareObject(creditCode, settleCreditData);
		
		}catch (Exception e) {
			logger.error("exception in calculting the settlement credit"+e);
		}
		return settleCreditData;
	}*/
	/**
	 * following function is called to calculate the total sal amount for a debit component of settlement
	 * @param empCode
	 * @param debitCode
	 * @return settleDebitData
	 */
	/*
	public Object [][]getSettlementDebit(String empCode,Object [][]debitCode){
		Object [][]settleDebitData =null;
		try{
		String settlementQuery = "SELECT (SETTL_DEBIT_CODE), SUM(SETTL_AMT),'Yes' FROM HRMS_SETTL_DEBITS "
				+" LEFT JOIN HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_DEBITS.SETTL_CODE)"
				+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
				+" WHERE HRMS_RESIGN.RESIGN_EMP ="+empCode+" AND SETTL_MTH_TYPE !='S'"
				+" GROUP BY SETTL_DEBIT_CODE ORDER BY HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE";
		
		settleDebitData = getSqlModel().getSingleResult(settlementQuery);
		
		String shortNoticeQuery = " SELECT (SETTL_DEBIT_CODE), CASE WHEN SETTL_SHORTPAYFLAG='E' THEN -SUM(SETTL_AMT) ELSE SUM(SETTL_AMT) END,'Yes'  FROM HRMS_SETTL_DEBITS"
				+" LEFT JOIN HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_DEBITS.SETTL_CODE)"
				+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE=HRMS_SETTL_HDR.SETTL_RESGNO)"
				+" WHERE HRMS_RESIGN.RESIGN_EMP ="+empCode+ "AND SETTL_MTH_TYPE ='S'"
				+" GROUP BY SETTL_DEBIT_CODE, SETTL_SHORTPAYFLAG ORDER BY HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE";
		Object shortNoticeData [][]=getSqlModel().getSingleResult(shortNoticeQuery);
		try{
		for (int i = 0; i < settleDebitData.length; i++) {
			settleDebitData [i][1] = Double.parseDouble(String.valueOf(settleDebitData[i][1]))+ Double.parseDouble(String.valueOf(shortNoticeData[i][1]));
		}
		}catch (Exception e) {
			logger.error("exception in calculating the shortNotice debit"+e);
		}
		settleDebitData = compareObject(debitCode, settleDebitData);
		
		}catch (Exception e) {
			logger.error("exception in calculating the settlment debit"+e);
		}
		return settleDebitData;
		
	}*/
	/*
	 *end of code to be used  Used with old database structure used for settlement
	 * 
	 */
	/**
	 * following function is called to calculate the other settlement details like gratuity, leave encashment
	 * other reimbursement & other deductions
	 * @param empCode
	 * @param debitCode
	 * @return settleDebitData
	 */
	public Object [][]getOtherSettlementDetails(String empCode){
		Object [][]otherSettlementDetails=null;
		
		String otherDetailsQuery="SELECT NVL(SETTL_LEAVE_ENCASH,0),NVL(SETTL_GRATUITY,0),NVL(SETTL_REIMBURSE,0),NVL(SETTL_DEDUCTION,0),"
						+" NVL(SETTL_TAX_AMT,0),NVL(SUM(SETTL_LOAN_AMT),0) FROM HRMS_SETTL_HDR"
						+" LEFT JOIN HRMS_SETTL_LOAN ON(HRMS_SETTL_LOAN.SETTL_CODE=HRMS_SETTL_HDR.SETTL_CODE)"
						+" WHERE SETTL_ECODE ="+empCode
						+" GROUP BY SETTL_LEAVE_ENCASH,SETTL_GRATUITY,SETTL_REIMBURSE,SETTL_DEDUCTION,SETTL_TAX_AMT ";
		otherSettlementDetails = getSqlModel().getSingleResult(otherDetailsQuery);
		
		return otherSettlementDetails;
	}
	
	
	
	/*
	 * Following function is called when the general user makes login.
	 */
	public void getEmployeeDetails(String empId, EmpSalaryReg empSalReg) {
		Object[] beanObj = new Object[1];
		beanObj[0] = empId;
		String query = " SELECT HRMS_EMP_OFFC.EMP_ID, "
				+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	EMP_TOKEN, EMP_STATUS,CENTER_NAME, DIV_NAME, CADRE_NAME FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
				+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
				+ "  WHERE HRMS_EMP_OFFC.EMP_ID =" + beanObj[0] + " ";
		Object[][] values = getSqlModel().getSingleResult(query);
		empSalReg.setEmpId(String.valueOf(values[0][0]));
		empSalReg.setEmpName(String.valueOf(values[0][1]));
		empSalReg.setEmpToken(String.valueOf(values[0][2]));
		empSalReg.setEmpStatus(String.valueOf(values[0][3]));
		empSalReg.setBrnName(String.valueOf(values[0][4]));
		empSalReg.setEmpDiv(String.valueOf(values[0][5]));
		empSalReg.setEmpGrade(String.valueOf(values[0][6]));
		
	}
	
	/** This method is used to set up the variables for generating the report.
	 * @param annualBean
	 * @param response
	 */
	public void generateReport(EmpSalaryReg esr, HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = esr.getReportType();
			String frmYear=esr.getFromYear();
			String toYear=esr.getToYear();
			rds.setReportType(type);
			String fileName = esr.getEmpName()+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("EMPLOYEE EARNING REPORT FOR THE FINANCIAL YEAR "+frmYear+" - "+toYear);
			rds.setPageSize("A4");
			rds.setShowPageNo(true);
			rds.setUserEmpId(esr.getUserEmpId());
			rds.setTotalColumns(15);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "EmpSalReg_input.action");
			}
			rg = getReport(rg, esr);
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * Following function is called to generate the report.
	 */

	
	public org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, EmpSalaryReg esr){
		try{
			
			String resignDateStatus = "";
			
			/*
			 * If Employee is resigned then check whether resigned date falls between the financial period selected to generate report
			 * 
			 * 
			 */
			logger.info("employee status =="+esr.getEmpStatus());
		if(esr.getEmpStatus().equals("N")){
			logger.info("inside if employee status =="+esr.getEmpStatus());
				String resignDateQuery  ="SELECT TO_CHAR(SETTL_SEPRDT,'MM'),TO_CHAR(SETTL_SEPRDT,'YYYY') FROM HRMS_SETTL_HDR "
					+" LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO)"
					+" WHERE HRMS_SETTL_HDR.SETTL_LOCKFLAG = 'Y' AND HRMS_RESIGN.RESIGN_EMP = "+esr.getEmpId();
		
		Object resignDateObject [][]= getSqlModel().getSingleResult(resignDateQuery);
		
		String frmYear=esr.getFromYear();
		String toYear=esr.getToYear();
		
		int resignMonth=0 , resignYear=0;
		int resignMonthYearString =0;
		int fromMonthYearString =0;
		int toMonthYearString =0;
		
		fromMonthYearString = Integer.parseInt(frmYear+esr.getFromMonth());
		toMonthYearString = Integer.parseInt(toYear+""+esr.getToMonth());
		
		try {
			if (resignDateObject != null && resignDateObject.length > 0) {
				resignMonth = Integer.parseInt("" + resignDateObject[0][0]);
				resignYear = Integer.parseInt("" + resignDateObject[0][1]);
			}
			if(resignMonth<10){
				resignMonthYearString = Integer.parseInt(resignYear+"0"+resignMonth);
			}else{
				resignMonthYearString = Integer.parseInt(""+resignYear+""+resignMonth);
			}
			if (resignMonthYearString >= fromMonthYearString
					&& resignMonthYearString <= toMonthYearString) {
				resignDateStatus = "BETWEEN";
			} 	
			else if (resignMonthYearString < fromMonthYearString) {
						resignDateStatus = "BEFORE";
					} else if (resignMonthYearString > toMonthYearString) {
						resignDateStatus = "AFTER";
					}  			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("resigndateStatus===="+resignDateStatus);
		logger.info("resignMonthYearString===="+resignMonthYearString);
		logger.info("fromMonthYearString===="+fromMonthYearString);
		logger.info("toMonthYearString===="+toMonthYearString); 
		if(resignDateStatus.equals("BETWEEN")){									// if resigned date falls between financial period then get salary details for months before resigned month
			
		rg=salReportForResigned(rg,esr);
			
		}else if(resignDateStatus.equals("AFTER")){								// if resigned date falls after the financial period selected then get salary details for all months in the period
			rg=salReport(rg,esr);
			
		}else if(resignDateStatus.equals("")){								// if resigned date falls after the financial period selected then get salary details for all months in the period
			rg=salReportForResigned(rg,esr);
			
		}else if(resignDateStatus.equals("BEFORE")){								// if resigned date falls after the financial period selected then get salary details for all months in the period
			rg=salReportForResigned(rg,esr);
			
		}
		else{																	// if resigned date falls before the financial period selected then show the message that employee has resigned.
			TableDataSet resignedEmp = new TableDataSet();
			resignedEmp.setData(new Object[][]{{"Employee Earnings for the Financial Year "+frmYear+"-"+toYear}, {esr.getEmpName()+" has resigned on "+Utility.month(Integer.parseInt(""+resignDateObject[0][0]))+"-"+resignDateObject[0][1]}});
			resignedEmp.setCellAlignment(new int[]{0});
			resignedEmp.setCellWidth(new int[]{100});
			resignedEmp.setBorderDetail(0);
			resignedEmp.setBlankRowsAbove(1);
			rg.addTableToDoc(resignedEmp);
			
			/*rg.addTextBold("Employee Earnings for the Financial Year "+frmYear+"-"+toYear,6,1,0);
			rg.addText(""+esr.getEmpName()+" has resigned on "+Utility.month(Integer.parseInt(""+resignDateObject[0][0]))+"-"+resignDateObject[0][1],0,1,0);*/
		}
			/*
			 * if employee is not resigned then show salary record for all months
			 * 
			 */
			}else{
				logger.info("inside else employee status =="+esr.getEmpStatus());
				rg=salReport(rg,esr);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			
		}
		return rg;
	}	
	
	/**
	 * following function is called to generate the report 
	 * @param rg
	 * @param esr
	 * @param request
	 * @return
	 */
	
		
public org.paradyne.lib.ireportV2.ReportGenerator salReportForResigned(org.paradyne.lib.ireportV2.ReportGenerator rg,EmpSalaryReg esr){
	
	String frmYear="",token="";
	String toYear="";
	String empId="",empCode="",empName="",empToken="";
	int year=0,yearTo=0;
	
	/*empId =  request.getParameter("empId");
	empName =  request.getParameter("empName");
	empToken =  request.getParameter("empToken");
	frmYear = request.getParameter("frYr");
	toYear = request.getParameter("toYr");*/
	
	empId =  esr.getEmpId();
	empName =  esr.getEmpName();
	empToken =  esr.getEmpToken();
	frmYear = esr.getFromYear();
	toYear = esr.getToYear();

	
	if(!(esr.getEmpId().trim().equals("") || esr.getEmpId().trim().equals("null") || esr.getEmpId().equals(null))){
		empCode=esr.getEmpId();
	}
	else{
		empCode=empId;
	}
	if(!(esr.getFromYear().trim().equals("") || esr.getFromYear().trim().equals("null") || esr.getFromYear().trim().equals(null))){
		year=Integer.parseInt(esr.getFromYear());
	}
	else{
		year=Integer.parseInt(frmYear);
	}
	if(!(esr.getToYear().trim().equals("") || esr.getToYear().trim().equals("null") || esr.getToYear().trim().equals(null))){
		yearTo=Integer.parseInt(esr.getToYear());
	}
	else{
		yearTo=Integer.parseInt(toYear);
	}
	if(!(esr.getEmpToken().trim().equals("") || esr.getEmpToken().trim().equals("null") || esr.getEmpToken().trim().equals(null))){
		token=esr.getEmpToken();
	}
	else{
		token=empToken;
	}
	if(!(esr.getEmpName().trim().equals("") || esr.getEmpName().trim().equals("null") || esr.getEmpName().trim().equals(null))){
		empName=esr.getEmpName();
	}
	else{
		empName=empToken;
	}
	String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
	Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
	if(esr.getReportType().equals("Pdf")){
		Object titleObj [][]= new Object[1][3];
		titleObj[0][0] = "";
		titleObj[0][1] = "Employee Earnings for the Financial Year "+year+"-"+yearTo;
		titleObj[0][2] = "Date : "+String.valueOf(dateData[0][0]);

		//rg.tableBodyNoBorder(titleObj, new int[]{25,  50, 25},new int[]{0,  1, 2},new int[]{0, 1, 0},new int[]{0,  12, 10});
		
		TableDataSet empObj = new TableDataSet();
		empObj.setData(titleObj);
		empObj.setCellAlignment(new int[]{0,  1, 2});
		empObj.setCellWidth(new int[]{25,  50, 25});
		empObj.setBorderDetail(0);
		empObj.setBlankRowsAbove(1);
		rg.addTableToDoc(empObj);
		
	Object [][] empDetails = new Object[3][2];
	
	empDetails [0][0] = "Employee Id  : "+token;
	empDetails [0][1] = "Employee Name : "+empName;
	
	empDetails [1][0] = "Employee Branch  : "+esr.getBrnName();
	empDetails [1][1] = "Employee Division : "+esr.getEmpDiv();
	
	empDetails [2][0] = "Employee Grade : "+checkNull(esr.getEmpGrade());
	empDetails [2][1] = "";
	
	//int [] colsEmp = {45,45};
	//int [] alignEmp = {0,0};
	 
	//rg.tableBodyNoBorder(empDetails,colsEmp,alignEmp,new int []{0,0},new int []{11,11});
	
	TableDataSet empDetailObj = new TableDataSet();
	empDetailObj.setData(empDetails);
	empDetailObj.setCellAlignment(new int[] {0,0 });
	empDetailObj.setCellWidth(new int[] {45,45});
	empDetailObj.setBorderDetail(0);
	rg.addTableToDoc(empDetailObj);
	
	}else {
		 Object [][] title = new Object[1][3];
		 title [0][0] = "";
		 title [0][1] = "";
		 title [0][2] = "Employee Earnings for the Financial Year "+year+"-"+yearTo;  
		 
		//int [] cols = {20,20,30};
		//int [] align = {0,0,1};
		//rg.tableBodyNoCellBorder(title,cols,align,1); 
		
		TableDataSet titleObj = new TableDataSet();
		titleObj.setData(title);
		titleObj.setCellAlignment(new int[]{0,0,1});
		titleObj.setCellWidth(new int[]{20,20,30});
		titleObj.setBorderDetail(0);
		titleObj.setBlankRowsAbove(1);
		rg.addTableToDoc(titleObj);
		
		/*rg.addText("Date: " + dateData[0][0], 0, 2, 0);
		rg.addText("Employee Id  :"+token,0,0,0);
		rg.addText("Employee Name:"+empName,0,0,0);
		rg.addText("Employee Branch  :"+esr.getBrnName(),0,0,0);
		rg.addText("Employee Division:"+esr.getEmpDiv(),0,0,0);
		rg.addText("Employee Grade:"+checkNull(esr.getEmpGrade()),0,0,0);*/
		
		TableDataSet totalEmp = new TableDataSet();
		totalEmp.setData(new Object[][]{{"Date: " + dateData[0][0]},
					{"Employee Id  :"+token},
					{"Employee Name:"+empName},
					{"Employee Branch  :"+esr.getBrnName()},
					{"Employee Division:"+esr.getEmpDiv()},
					{"Employee Grade:"+checkNull(esr.getEmpGrade())}} );
		totalEmp.setCellAlignment(new int[]{0});
		totalEmp.setCellWidth(new int[]{100});
		totalEmp.setBorderDetail(0);
		totalEmp.setBlankRowsAbove(1);
		rg.addTableToDoc(totalEmp);
		
	}
	
	
	
	//String creditQuery="SELECT CREDIT_CODE,CREDIT_NAME,CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
	String creditQuery="SELECT CREDIT_CODE,CREDIT_NAME,CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_CREDIT_HEAD WHERE CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
	Object creditCode[][]=getSqlModel().getSingleResult(creditQuery);

	String debitQuery="SELECT DEBIT_CODE,DEBIT_NAME, 'Yes' FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_CODE";
	Object debitCode[][]=getSqlModel().getSingleResult(debitQuery);
	
	int noOfMonths=0;
	/*String ledgerCodeString="0";
	String ledgerQueryAprToDec = "SELECT LEDGER_CODE,SAL_ONHOLD,LEDGER_MONTH,NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m' FROM HRMS_SALARY_LEDGER " 
		+" LEFT JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
		+" WHERE LEDGER_MONTH BETWEEN 4 AND 12 AND LEDGER_YEAR="+String.valueOf(year)+" AND LEDGER_STATUS = 'SAL_FINAL'  AND EMP_ID ="+empCode+" AND SAL_ONHOLD='N'"
		+" ORDER BY LEDGER_MONTH";
	
	Object ledgerAprToDec[][]=getSqlModel().getSingleResult(ledgerQueryAprToDec);
	
	try {
		noOfMonths =ledgerAprToDec.length;
		for (int i = 0; i < noOfMonths; i++) {
			
				ledgerCodeString +=","+String.valueOf(ledgerAprToDec[i][0]);
			
			
		}
	} catch (Exception e) {
		noOfMonths =0;
		ledgerCodeString="0";
	}
	
	String ledgerQueryJanToMar = "SELECT LEDGER_CODE,SAL_ONHOLD,LEDGER_MONTH,NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m' FROM HRMS_SALARY_LEDGER " 
		+"LEFT JOIN HRMS_SALARY_"+yearTo+" ON(HRMS_SALARY_"+yearTo+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
		+"WHERE LEDGER_MONTH BETWEEN 1 AND 3 AND LEDGER_YEAR="+String.valueOf(yearTo)+" AND LEDGER_STATUS = 'SAL_FINAL'  AND EMP_ID ="+empCode+" AND SAL_ONHOLD='N'"
		+" ORDER BY LEDGER_MONTH";
	Object ledgerJanToMar[][]=null;
	try {
		ledgerJanToMar=getSqlModel().getSingleResult(ledgerQueryJanToMar);
		noOfMonths +=ledgerJanToMar.length;
		for (int i = 0; i < ledgerJanToMar.length; i++) {
			ledgerCodeString +=","+String.valueOf(ledgerJanToMar[i][0]);
			
		}
		
	} catch (Exception e) {
		for (int i = 0; i < ledgerJanToMar.length; i++) {
			ledgerCodeString +=","+String.valueOf(ledgerJanToMar[i][0]);
			
		}
	}*/
	
	//UPDATES BY REEBA BEGINS
	
	//IF FROM YEAR = TO YEAR, ledgerQueryAprToDec WITH MONTHS BETWEEN FRM_MONTH & TO_MONTH
	//ELSE IF TO YEAR > FROM YEAR, ledgerQueryAprToDec - YEAR = FROM_YEAR, 4 = FROM_MONTH, 12=12
	//& ledgerQueryJanToMar WITH YEAR = TO_YEAR AND MONTHS BETWEEN 1 = 1 & 3 = TO_MONTH
	
	logger.info("FROM MONTH : "+esr.getFromMonth());
	logger.info("FROM YEAR  : "+esr.getFromYear());
	logger.info("TO MONTH   : "+esr.getToMonth());
	logger.info("TO YEAR    : "+esr.getToYear());
	Object ledgerAprToDec[][]= null;
	Object ledgerJanToMar[][]=null;
	String ledgerCodeString="0";
	
	if(Integer.parseInt(String.valueOf(esr.getFromYear())) == Integer.parseInt(String.valueOf(esr.getToYear()))){
		if(Integer.parseInt(esr.getFromMonth())>3){
		String ledgerQueryAprToDec = "SELECT LEDGER_CODE,SAL_ONHOLD,LEDGER_MONTH,NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m' FROM HRMS_SALARY_LEDGER " 
			+" LEFT JOIN HRMS_SALARY_"+esr.getFromYear()+" ON(HRMS_SALARY_"+esr.getFromYear()+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
			+" WHERE LEDGER_MONTH BETWEEN "+ esr.getFromMonth()+" AND "+ esr.getToMonth()+" AND LEDGER_YEAR="+String.valueOf(esr.getFromYear())+" AND LEDGER_STATUS = 'SAL_FINAL'  AND EMP_ID ="+empCode+" AND SAL_ONHOLD='N'"
			+" ORDER BY LEDGER_MONTH";
		
		ledgerAprToDec=getSqlModel().getSingleResult(ledgerQueryAprToDec);
		
		try {
			noOfMonths =ledgerAprToDec.length;
			for (int i = 0; i < noOfMonths; i++) {
				
					ledgerCodeString +=","+String.valueOf(ledgerAprToDec[i][0]);
				
				
			}
		} catch (Exception e) {
			noOfMonths =0;
			
		}
		} else {
		String ledgerQueryJanToMar = "SELECT LEDGER_CODE,SAL_ONHOLD,LEDGER_MONTH,NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m' FROM HRMS_SALARY_LEDGER " 
			+"LEFT JOIN HRMS_SALARY_"+esr.getToYear()+" ON(HRMS_SALARY_"+esr.getToYear()+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
			+"WHERE LEDGER_MONTH BETWEEN 1 AND "+esr.getToMonth()+" AND LEDGER_YEAR="+String.valueOf(esr.getToYear())+" AND LEDGER_STATUS = 'SAL_FINAL'  AND EMP_ID ="+empCode+ " AND SAL_ONHOLD='N'"
			+" ORDER BY LEDGER_MONTH";
		
		try {
			ledgerJanToMar=getSqlModel().getSingleResult(ledgerQueryJanToMar);
			noOfMonths +=ledgerJanToMar.length;
			
			for (int i = 0; i < ledgerJanToMar.length; i++) {
				ledgerCodeString +=","+String.valueOf(ledgerJanToMar[i][0]);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
	else if(Integer.parseInt(String.valueOf(esr.getToYear())) > Integer.parseInt(String.valueOf(esr.getFromYear()))){
		String ledgerQueryAprToDec = "SELECT LEDGER_CODE,SAL_ONHOLD,LEDGER_MONTH,NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m' FROM HRMS_SALARY_LEDGER " 
			+" LEFT JOIN HRMS_SALARY_"+esr.getFromYear()+" ON(HRMS_SALARY_"+esr.getFromYear()+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
			+" WHERE LEDGER_MONTH BETWEEN "+ esr.getFromMonth()+" AND 12 AND LEDGER_YEAR="+String.valueOf(esr.getFromYear())+" AND LEDGER_STATUS = 'SAL_FINAL'  AND EMP_ID ="+empCode+ " AND SAL_ONHOLD='N'"
			+" ORDER BY LEDGER_MONTH";
		
		ledgerAprToDec=getSqlModel().getSingleResult(ledgerQueryAprToDec);
		
		try {
			noOfMonths =ledgerAprToDec.length;
			for (int i = 0; i < noOfMonths; i++) {
				ledgerCodeString +=","+String.valueOf(ledgerAprToDec[i][0]);
				
			}
		} catch (Exception e) {
			noOfMonths =0;
			
		}
		String ledgerQueryJanToMar = "SELECT LEDGER_CODE,SAL_ONHOLD,LEDGER_MONTH,NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m' FROM HRMS_SALARY_LEDGER " 
			+"LEFT JOIN HRMS_SALARY_"+esr.getToYear()+" ON(HRMS_SALARY_"+esr.getToYear()+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
			+"WHERE LEDGER_MONTH BETWEEN 1 AND "+esr.getToMonth()+" AND LEDGER_YEAR="+String.valueOf(esr.getToYear())+" AND LEDGER_STATUS = 'SAL_FINAL'  AND EMP_ID ="+empCode+ " AND SAL_ONHOLD='N'"
			+" ORDER BY LEDGER_MONTH";
		
		try {
			ledgerJanToMar=getSqlModel().getSingleResult(ledgerQueryJanToMar);
			noOfMonths +=ledgerJanToMar.length;
			
			for (int i = 0; i < ledgerJanToMar.length; i++) {
				ledgerCodeString +=","+String.valueOf(ledgerJanToMar[i][0]);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//UPDATES BY REEBA ENDS
	
	logger.info("noOfMonths=="+noOfMonths);
	Object[][]	para=new Object[creditCode.length][noOfMonths+3];
	Object[][] arrear=new Object[creditCode.length][noOfMonths+3];
	Object[][] arrearDebit=new Object[debitCode.length][noOfMonths+3];
	Object[][]	paraDebt=new Object[debitCode.length][noOfMonths+3];
//	Object[][] allowance =new Object[creditCode.length][14];
	
	Object[][] totCreditAmt=new Object[1][noOfMonths+2];
	Object[][] totDebitAmt=new Object[1][noOfMonths+2];
	
	Object[][] netSalAmt=new Object[1][noOfMonths+3];
	Object[][] salDays=new Object[1][noOfMonths+3];
	
	Object[][] taxData=new Object[1][noOfMonths+2];
	
	Object[][] totArrCreditAmt=new Object[1][noOfMonths+1];
	Object[][] totArrNetAmt=new Object[1][noOfMonths+1];
	Object[][] totArrDebitAmt=new Object[1][noOfMonths+1];
	Object[][] taxArr=new Object[1][noOfMonths+1];
	
	Object [][] otherSettlCredit = new Object [3][noOfMonths+3];
	Object [][] otherDeductions = new Object [1][noOfMonths+3];
	Object [][] pendingLoan = new Object [1][noOfMonths+3];
	Object [][] pendingTax = new Object [1][noOfMonths+3];
	
	Object [][]settleCreditData=null;
	Object [][]settleDebitData=null;
	
	int arrCount=0;
	double leaveEncashAmt =0.00,gratuityAmt =0.00,otherReimbAmt =0.00,otherDedAmt =0.00,pendingLoanAmt =0.00;
	double pendingTaxAmt =0.00;
	
	try{	
	if(noOfMonths>0){
		int colCount=1;
		int creditCol=1;
		int debitCol=1;
		int arrearCol=1;
		int arrTaxCol=1;
		int arrCreditCol=1;
		int arrDebitCol=1;
		int taxCreditCount=1;
		
		if(esr.getEmpStatus().equals("N")){
		
		settleCreditData = getSettlementCredit(empCode,creditCode);
		settleDebitData = getSettlementDebit(empCode,debitCode);
		netSalAmt[0][noOfMonths+1]="0";
		try {
			for (int i = 0; i < para.length; i++) {
				para[i][noOfMonths + 2] = Utility.twoDecimals(""
						+ settleCreditData[i][1]);
				para[i][noOfMonths + 1] = String
						.valueOf(settleCreditData[i][2]);

			}
		} catch (Exception e) {
			logger.error("exception in setting the settlemnt credit"+e);
		}
		try{
			for (int i = 0; i < paraDebt.length; i++) {
				paraDebt[i][noOfMonths + 2] = Utility.twoDecimals(""
						+ settleDebitData[i][1]);
			}
		} catch (Exception e) {
			logger.error("exception in setting the settlemnt debit"+e);
		}
		/* get other settlement details  
		 * like leave encash,Other Reimbursements, 
		Gratuity, Other Deductions*/
		Object otherSettlementDetails [][]=getOtherSettlementDetails(empCode);				
		otherDeductions[0][0] = "Other Deductions";
		pendingLoan[0][0] = "Loan Amount";
		pendingTax[0][0] = "Tax Deducted in Settlement";
		
		otherSettlCredit[0][0] = "Leave Encashment";
		otherSettlCredit[1][0] = "Gratuity";
		otherSettlCredit[2][0] = "Other Reimbursements";
		
		
		try {
			
			leaveEncashAmt = Double.parseDouble(String.valueOf(otherSettlementDetails[0][0]));
						
			gratuityAmt = Double.parseDouble(String.valueOf(otherSettlementDetails[0][1]));
			
			otherReimbAmt = Double.parseDouble(String.valueOf(otherSettlementDetails[0][2]));
			
			otherDedAmt = Double.parseDouble(String.valueOf(otherSettlementDetails[0][3]));
			
			pendingTaxAmt = Double.parseDouble(String.valueOf(otherSettlementDetails[0][4]));
			
			pendingLoanAmt = Double.parseDouble(String.valueOf(otherSettlementDetails[0][5]));
			
		} catch (Exception e) {
			
		}
		for (int i = 1; i <= 2; i++) {
			otherSettlCredit [0][noOfMonths+i] = leaveEncashAmt;
			otherSettlCredit [1][noOfMonths+i] = gratuityAmt ;
			otherSettlCredit [2][noOfMonths+i] = otherReimbAmt;
			
			otherDeductions [0][noOfMonths+i] = otherDedAmt;
			pendingLoan [0][noOfMonths+i] = pendingLoanAmt;
			pendingTax [0][noOfMonths+i] = pendingTaxAmt;
		}
		
		if(String.valueOf(totCreditAmt[0][noOfMonths+1]).equals("null") || String.valueOf(totCreditAmt[0][noOfMonths+1]).equals("")){
			totCreditAmt[0][noOfMonths+1]=0;
		}
		if(String.valueOf(totDebitAmt[0][noOfMonths+1]).equals("null") || String.valueOf(totDebitAmt[0][noOfMonths+1]).equals("")){
			totDebitAmt[0][noOfMonths+1]=0;
		}
		if(String.valueOf(taxData[0][noOfMonths+1]).equals("null") || String.valueOf(taxData[0][noOfMonths+1]).equals("")){
			taxData[0][noOfMonths+1]=0;
		}
		
		/*
		 * adding settleCtedit value in the totCreditAmt Object
		 */
		try{
		for (int i = 0; i < settleCreditData.length; i++) {
			totCreditAmt [0][noOfMonths+1] = Double.parseDouble(""+settleCreditData[i][1])+ Double.parseDouble(""+totCreditAmt [0][noOfMonths+1]);
			netSalAmt [0][noOfMonths+1] = Double.parseDouble(""+settleCreditData[i][1])+ Double.parseDouble(""+netSalAmt[0][noOfMonths+1]);
			//logger.info("paraCredit[i][noOfMonths+1]"+paraCredit[i][noOfMonths+1]);
			if(String.valueOf(para[i][noOfMonths+1]).equals("Yes"))
			taxData[0][noOfMonths+1]=Double.parseDouble(""+settleCreditData[i][1])+Double.parseDouble(""+taxData[0][noOfMonths+1]);
		}
		}catch (Exception e) {
			logger.error("exception in setting the other settlemnt credits"+e);
		}
		/*
		 * adding the leave encashment, other reimbursement, and gratuity values to total credit Object
		 */
		totCreditAmt [0][noOfMonths+1] = leaveEncashAmt + gratuityAmt + otherReimbAmt +	Double.parseDouble(""+totCreditAmt [0][noOfMonths+1]);
		
		/*
		 * adding settleCtedit value in the totDebitAmt Object
		 */
		try{
		for (int i = 0; i < settleDebitData.length; i++) {
			totDebitAmt [0][noOfMonths+1] = Double.parseDouble(""+settleDebitData[i][1])+ Double.parseDouble(""+totDebitAmt [0][noOfMonths+1]);
			netSalAmt [0][noOfMonths+1] = Double.parseDouble(""+netSalAmt [0][noOfMonths+1])-Double.parseDouble(""+settleDebitData[i][1]);
		}
		}catch (Exception e) {
			logger.error("exception in setting the other settlemnt debits"+e);
		}
		netSalAmt [0][noOfMonths+1] = leaveEncashAmt + gratuityAmt + otherReimbAmt +Double.parseDouble(""+netSalAmt [0][noOfMonths+1]) - (otherDedAmt +pendingLoanAmt+pendingTaxAmt) ;
		/*
		 * adding the other deduction values to totDebitAmt
		 */
		totDebitAmt [0][noOfMonths+1] = otherDedAmt +pendingLoanAmt+pendingTaxAmt+ Double.parseDouble(""+totDebitAmt [0][noOfMonths+1]);
		
		
		}
		
		try {
			for (int j = 0; j < ledgerAprToDec.length; j++) {
				int loopMonth = Integer.parseInt(String
						.valueOf(ledgerAprToDec[j][2]));
				Object[][] credit_amount = getCreditSalAmt(empCode, year,
						loopMonth, String.valueOf(ledgerAprToDec[j][0]),
						"Final", creditCode);
				Object[][] debit_amount = getDebitSalAmt(empCode, year,
						loopMonth, String.valueOf(ledgerAprToDec[j][0]),
						"Final", debitCode);
				Object[][] totCredit = new Object[1][1];
				Object[][] totDebit = new Object[1][1];
				double cr_amt = 0.00;
				double db_amt = 0.00;
				double taxCredit = 0.00;

				/*
				 * Following loop calculates the total credit amount per month and the total tax amount for whom TAXABLE_FLAG='Y'
				 
				 */

				if (credit_amount != null && credit_amount.length > 0) {
					for (int i = 0; i < credit_amount.length; i++) {

						if (String.valueOf(credit_amount[i][2]).equals("Yes")) {

							taxCredit += Double.parseDouble(String
									.valueOf(credit_amount[i][1]));

						}
						cr_amt += Double.parseDouble(String
								.valueOf(credit_amount[i][1]));

					}
				}
				totCredit[0][0] = cr_amt;

				if (debit_amount != null && debit_amount.length > 0) {

					for (int i = 0; i < debit_amount.length; i++) {
						db_amt += Double.parseDouble(String
								.valueOf(debit_amount[i][1]));
					}
				}
				totDebit[0][0] = db_amt;

				totCreditAmt[0][0] = "TOTAL CREDIT";
				netSalAmt[0][0] = "NET SALARY";
				salDays[0][0] = "DAYS PRESENT";
				totCreditAmt[0][0] = "TOTAL CREDIT";
				totDebitAmt[0][0] = "TOTAL DEBIT";
				taxData[0][0] = "TOTAL TAXABLE CREDIT";
				totCreditAmt[0][creditCol] = totCredit[0][0];
				netSalAmt[0][creditCol] = cr_amt - db_amt;
				salDays[0][creditCol] = Utility.getViewDays(String.valueOf(ledgerAprToDec[j][3]));
				
				
				taxData[0][taxCreditCount] = taxCredit;//tax[0][0];

				if (credit_amount != null && credit_amount.length > 0) {

					/*
					 * Following loop sets the credit head and their respective values
					 */
					for (int i = 0; i < credit_amount.length; i++)//4 records
					{
						para[i][0] = credit_amount[i][0];

						if (credit_amount[i][1].equals("null")) {
							para[i][colCount] = "";
						}//End of if
						else {

							para[i][colCount] = (String
									.valueOf(credit_amount[i][1]));

						}

						para[i][noOfMonths + 1] = credit_amount[i][2];

					}//End of for loop of credit_amount

				}

				if (debit_amount != null && debit_amount.length > 0) {

					/*
					 * Following loop sets the debit head and their respective values
					 */
					for (int i = 0; i < debit_amount.length; i++)//4 records
					{
						paraDebt[i][0] = debit_amount[i][0];

						if (debit_amount[i][1].equals("null")) {
							paraDebt[i][colCount] = "";
						}//End of if
						else {

							paraDebt[i][colCount] = (String
									.valueOf(debit_amount[i][1]));

						}

						paraDebt[i][noOfMonths + 1] = debit_amount[i][2];
						totDebitAmt[0][debitCol] = totDebit[0][0];

					}//End of for loop of credit_amount

				}

				double taxArrear = 0.00;
				double arr_amt = 0.00;
				double arr_amt_debit = 0.00;
				/**
				 * following loop is used to calculate the total arrear credit amount and the total arrear 
				 * taxable amount.
				 */
				Object[][] arrearAmt = getArrears(String.valueOf(year), esr
						.getEmpId(), "" + loopMonth, "Final", creditCode);
				if (arrearAmt != null && arrearAmt.length > 0) {
					for (int i = 0; i < arrearAmt.length; i++) {

						if (String.valueOf(arrearAmt[i][2]).equals("Yes")) {

							taxArrear += Double.parseDouble(String
									.valueOf(arrearAmt[i][1]));

						}

						arr_amt += Double.parseDouble(String
								.valueOf(arrearAmt[i][1]));

					}
				}

				Object[][] arrearAmtDebit = getArrearsDebit("" + year, esr
						.getEmpId(), "" + loopMonth, "Final", debitCode);

				if (arrearAmtDebit != null && arrearAmtDebit.length > 0) {
					for (int i = 0; i < arrearAmtDebit.length; i++) {
						arr_amt_debit += Double.parseDouble(String
								.valueOf(arrearAmtDebit[i][1]));

					}
				}

				totArrCreditAmt[0][0] = "TOT CREDIT";
				totArrDebitAmt[0][0] = "TOT DEBIT";
				netSalAmt[0][0] = "NET SALARY";
				salDays[0][0] = "DAYS PRESENT";

				totArrCreditAmt[0][arrCreditCol] = arr_amt;//[0][0];
				totArrDebitAmt[0][arrDebitCol] = arr_amt_debit;//[0][0];	
				taxArr[0][0] = "TOT TAXABLE CREDIT";
				taxArr[0][arrTaxCol] = taxArrear;//tax[0][0];

				totArrNetAmt[0][0] = "NET ARREARS";
				totArrNetAmt[0][arrTaxCol] = arr_amt - arr_amt_debit;//[0][0];	(NET ARREARS=ARREAR CREDIT-ARREAR DEBIT)
				/**
				 * following loop is used to select the credit head name and the corresponding amount.
				 */
				if (arrearAmt != null && arrearAmt.length > 0) {

					for (int i = 0; i < arrearAmt.length; i++) {
						arrear[i][0] = arrearAmt[i][0];//Credit Head

						if (arrearAmt[i][1].equals("null")) {
							arrear[i][arrearCol] = "";
						}//End of if
						else {
							arrear[i][arrearCol] = (String
									.valueOf(arrearAmt[i][1]));//Credit Amount
						}

						arrear[i][noOfMonths + 1] = arrearAmt[i][2];//Is Taxable or not

					}//End of for loop of credit_amount

				}
				if (arrearAmtDebit != null && arrearAmtDebit.length > 0) {
					for (int i = 0; i < arrearAmtDebit.length; i++) {
						arrearDebit[i][0] = arrearAmtDebit[i][0];//Debit Head
						if (arrearAmtDebit[i][1].equals("null")) {
							arrearDebit[i][arrearCol] = "";
						}//End of if
						else {
							arrearDebit[i][arrearCol] = (String
									.valueOf(arrearAmtDebit[i][1]));//Debit Amount

						}

						arrearDebit[i][noOfMonths + 1] = arrearAmtDebit[i][2];//Is Taxable or not

					}//End of for loop of credit_amount
				}

				arrearCol++;
				arrCreditCol++;
				arrDebitCol++;
				arrTaxCol++;
				arrCount++;

				colCount++;
				creditCol++;
				debitCol++;
				taxCreditCount++;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			for (int j = 0; j < ledgerJanToMar.length; j++) {
				int loopMonth = Integer.parseInt(String
						.valueOf(ledgerJanToMar[j][2]));
				Object[][] credit_amount = getCreditSalAmt(empCode, yearTo,
						loopMonth, String.valueOf(ledgerJanToMar[j][0]),
						"Final", creditCode);
				Object[][] debit_amount = getDebitSalAmt(empCode, yearTo,
						loopMonth, String.valueOf(ledgerJanToMar[j][0]),
						"Final", debitCode);
				Object[][] totCredit = new Object[1][1];
				Object[][] totDebit = new Object[1][1];
				double cr_amt = 0.00;
				double db_amt = 0.00;
				double taxCredit = 0.00;

				/*
				 * Following loop calculates the total credit amount per month and the total tax amount for whom TAXABLE_FLAG='Y'
				 
				 */

				if (credit_amount != null && credit_amount.length > 0) {
					for (int i = 0; i < credit_amount.length; i++) {

						if (String.valueOf(credit_amount[i][2]).equals("Yes")) {

							taxCredit += Double.parseDouble(String
									.valueOf(credit_amount[i][1]));

						}
						cr_amt += Double.parseDouble(String
								.valueOf(credit_amount[i][1]));

					}
				}
				totCredit[0][0] = cr_amt;

				if (debit_amount != null && debit_amount.length > 0) {

					for (int i = 0; i < debit_amount.length; i++) {
						db_amt += Double.parseDouble(String
								.valueOf(debit_amount[i][1]));
					}
				}
				totDebit[0][0] = db_amt;

				totCreditAmt[0][creditCol] = totCredit[0][0];
				netSalAmt[0][creditCol] = cr_amt - db_amt; // net Salary
				salDays[0][creditCol] = Utility.getViewDays(String.valueOf(ledgerJanToMar[j][3]));

				taxData[0][0] = "TOTAL TAXABLE CREDIT";
				taxData[0][taxCreditCount] = taxCredit;//tax[0][0];

				if (credit_amount != null && credit_amount.length > 0) {

					/*
					 * Following loop sets the credit head and their respective values
					 */
					for (int i = 0; i < credit_amount.length; i++)//4 records
					{
						para[i][0] = credit_amount[i][0];

						if (credit_amount[i][1].equals("null")) {
							para[i][colCount] = "";
						}//End of if
						else {

							para[i][colCount] = (String
									.valueOf(credit_amount[i][1]));

						}

						para[i][noOfMonths + 1] = credit_amount[i][2];

					}//End of for loop of credit_amount

				}

				if (debit_amount != null && debit_amount.length > 0) {

					/*
					 * Following loop sets the debit head and their respective values
					 */
					for (int i = 0; i < debit_amount.length; i++)//4 records
					{
						paraDebt[i][0] = debit_amount[i][0];

						if (debit_amount[i][1].equals("null")) {
							paraDebt[i][colCount] = "";
						}//End of if
						else {

							paraDebt[i][colCount] = (String
									.valueOf(debit_amount[i][1]));

						}

						paraDebt[i][noOfMonths + 1] = debit_amount[i][2];
						totDebitAmt[0][debitCol] = totDebit[0][0];

					}//End of for loop of credit_amount

				}

				double taxArrear = 0.00;
				double arr_amt = 0.00;
				double arr_amt_debit = 0.00;
				/**
				 * following loop is used to calculate the total arrear credit amount and the total arrear 
				 * taxable amount.
				 */
				Object[][] arrearAmt = getArrears(String.valueOf(yearTo), esr
						.getEmpId(), "" + loopMonth, "Final", creditCode);
				if (arrearAmt != null && arrearAmt.length > 0) {
					for (int i = 0; i < arrearAmt.length; i++) {

						if (String.valueOf(arrearAmt[i][2]).equals("Yes")) {

							taxArrear += Double.parseDouble(String
									.valueOf(arrearAmt[i][1]));

						}

						arr_amt += Double.parseDouble(String
								.valueOf(arrearAmt[i][1]));

					}
				}

				Object[][] arrearAmtDebit = getArrearsDebit("" + yearTo, esr
						.getEmpId(), "" + loopMonth, "Final", debitCode);

				if (arrearAmtDebit != null && arrearAmtDebit.length > 0) {
					for (int i = 0; i < arrearAmtDebit.length; i++) {
						arr_amt_debit += Double.parseDouble(String
								.valueOf(arrearAmtDebit[i][1]));

					}
				}

				totArrCreditAmt[0][arrCreditCol] = arr_amt;//[0][0];
				totArrDebitAmt[0][arrDebitCol] = arr_amt_debit;//[0][0];	
				taxArr[0][arrTaxCol] = taxArrear;//tax[0][0];
				totArrNetAmt[0][arrTaxCol] = arr_amt - arr_amt_debit;//[0][0];	(NET ARREARS=ARREAR CREDIT-ARREAR DEBIT)

				/**
				 * following loop is used to select the credit head name and the corresponding amount.
				 */
				if (arrearAmt != null && arrearAmt.length > 0) {

					for (int i = 0; i < arrearAmt.length; i++) {
						arrear[i][0] = arrearAmt[i][0];//Credit Head

						if (arrearAmt[i][1].equals("null")) {
							arrear[i][arrearCol] = "";
						}//End of if
						else {
							arrear[i][arrearCol] = (String
									.valueOf(arrearAmt[i][1]));//Credit Amount
						}

						arrear[i][noOfMonths + 1] = arrearAmt[i][2];//Is Taxable or not

					}//End of for loop of credit_amount

				}
				if (arrearAmtDebit != null && arrearAmtDebit.length > 0) {
					for (int i = 0; i < arrearAmtDebit.length; i++) {
						arrearDebit[i][0] = arrearAmtDebit[i][0];//Debit Head
						if (arrearAmtDebit[i][1].equals("null")) {
							arrearDebit[i][arrearCol] = "";
						}//End of if
						else {
							arrearDebit[i][arrearCol] = (String
									.valueOf(arrearAmtDebit[i][1]));//Debit Amount

						}

						arrearDebit[i][noOfMonths + 1] = arrearAmtDebit[i][2];//Is Taxable or not

					}//End of for loop of credit_amount
				}

				arrearCol++;
				arrCreditCol++;
				arrDebitCol++;
				arrTaxCol++;
				arrCount++;

				colCount++;
				creditCol++;
				debitCol++;
				taxCreditCount++;

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		totCreditAmt[0][0] = "TOTAL CREDIT";
		netSalAmt[0][0] = "NET SALARY";
		salDays[0][0] = "DAYS PRESENT";
		totCreditAmt[0][0] = "TOTAL CREDIT";
		totDebitAmt[0][0] = "TOTAL DEBIT";
		taxData[0][0] = "TOTAL TAXABLE CREDIT";
		
		Object[][] finalObjCredit=new Object[para.length][noOfMonths+4];
		Object[][] finalObjDebit=new Object[paraDebt.length][noOfMonths+4];
		
		/*
	 * The finalObjCredit table is used for displaying the credit head names and their respective values.
	 */
		for(int k=0;k<para.length;k++){
			double creditAmtYearly=0;
				for(int l=1;l<para[k].length;l++){
					if(l != noOfMonths +1 ){
					if(String.valueOf(para[k][l]).equals("null") || String.valueOf(para[k][l]).equals("")){
						para[k][l]="0";
						
					}
					creditAmtYearly+=Double.parseDouble(String.valueOf(para[k][l]));
					}
				}
				for (int i = 0; i < noOfMonths+4; i++) {
					if(i==0){
						finalObjCredit[k][i]=String.valueOf(para[k][i]);
					}else if(i== noOfMonths+1){
						finalObjCredit[k][i]=String.valueOf(para[k][noOfMonths+2]);
					}
					else if(i== noOfMonths+2){
						finalObjCredit[k][i]=Utility.twoDecimals(creditAmtYearly);
					}else if(i== noOfMonths+3){
						finalObjCredit[k][i]=String.valueOf(para[k][noOfMonths+1]);
					}else{
						finalObjCredit[k][i]=Utility.twoDecimals(String.valueOf(para[k][i]));
					}
				}
			
		} 
		for(int k=0;k<paraDebt.length;k++){
			double debitAmtYearly=0;
				for(int l=1;l<paraDebt[k].length;l++){
					if(l != noOfMonths+1){
					if(String.valueOf(paraDebt[k][l]).equals("null") || String.valueOf(paraDebt[k][l]).equals("")){
						paraDebt[k][l]="0";
					}
							
					debitAmtYearly+=Double.parseDouble(String.valueOf(paraDebt[k][l]));
					}
				}
				
				for (int i = 0; i < noOfMonths+4; i++) {
					if(i==0){
						finalObjDebit[k][i]=String.valueOf(paraDebt[k][i]);
					}else if(i== noOfMonths+1){
						finalObjDebit[k][i]=String.valueOf(paraDebt[k][noOfMonths+2]);
					}
					else if(i== noOfMonths+2){
						finalObjDebit[k][i]=Utility.twoDecimals(debitAmtYearly);
					}else if(i== noOfMonths+3){
						finalObjDebit[k][i]=String.valueOf(paraDebt[k][noOfMonths+1]);
					}else{
						finalObjDebit[k][i]=Utility.twoDecimals(String.valueOf(paraDebt[k][i]));
					}
					
				}
				
					
		}
		
		
	Object[][] finalObjTotCredit=new Object[totCreditAmt.length][noOfMonths + 3];
	Object[][] finalObjTotDebit=new Object[totDebitAmt.length][noOfMonths + 3];
	Object[][] finalObjNetSal=new Object[netSalAmt.length][noOfMonths+3];
	Object[][] finalObjSalDays=new Object[salDays.length][noOfMonths+3];
	/*
	 * The finalObjTotCredit is used for displaying total credit amounts for a particular month.
	 */
	
		for(int k=0;k<totCreditAmt.length;k++){
			double totCrediAmtYearly=0;
			for(int l=1;l<totCreditAmt[k].length;l++){
				if(String.valueOf(totCreditAmt[k][l]).equals("null") || String.valueOf(totCreditAmt[k][l]).equals("")){
					totCreditAmt[k][l]=0.00;
				}
				
				totCrediAmtYearly+=Double.parseDouble(String.valueOf(totCreditAmt[k][l]));
				}
			for (int l = 0; l < noOfMonths+3; l++) {
				if(l==0){
					finalObjTotCredit[k][l]=totCreditAmt[k][l];
				}else if( l == noOfMonths +2){
					finalObjTotCredit[k][l]=Utility.twoDecimals(totCrediAmtYearly);
				}else{
					finalObjTotCredit[k][l]=Utility.twoDecimals(String.valueOf(totCreditAmt[k][l]));
				}
			}
						
			
		}
		
		for(int k=0;k<finalObjTotDebit.length;k++){
			double totDebitAmtYearly=0;
			for(int l=1;l<totDebitAmt[k].length;l++){
				if(String.valueOf(totDebitAmt[k][l]).equals("null") || String.valueOf(totDebitAmt[k][l]).equals("")){
					totDebitAmt[k][l]=0.00;
				}
				
				totDebitAmtYearly+=Double.parseDouble(String.valueOf(totDebitAmt[k][l]));
				}
			
			for (int l = 0; l < noOfMonths+3; l++) {
				if(l==0){
					finalObjTotDebit[k][l]=totDebitAmt[k][l];
				}else if( l == noOfMonths +2){
					finalObjTotDebit[k][l]=Utility.twoDecimals(totDebitAmtYearly);
				}else{
					finalObjTotDebit[k][l]=Utility.twoDecimals(String.valueOf(totDebitAmt[k][l]));
				}
			}
			
			
		}
		
		for(int k=0;k<netSalAmt.length;k++){
			double totNetSalAmtYearly=0;
			for(int l=1;l<netSalAmt[k].length;l++){
				if(String.valueOf(netSalAmt[k][l]).equals("null") || String.valueOf(netSalAmt[k][l]).equals("")){
					netSalAmt[k][l]=0.00;
				}
				
				totNetSalAmtYearly+=Double.parseDouble(String.valueOf(netSalAmt[k][l]));
				}
			
			finalObjNetSal[k][0]=netSalAmt[k][0];
			finalObjNetSal[k][noOfMonths+2]=Utility.twoDecimals(totNetSalAmtYearly);
			
			for (int i = 1; i < noOfMonths+2; i++) {
				finalObjNetSal[k][i]=Utility.twoDecimals(String.valueOf(netSalAmt[k][i]));
			}
			
		}
		try{
			
		for(int k=0;k<salDays.length;k++){
			String totSalDaysYearly=getSalDaysSum(ledgerCodeString,empCode,esr);
			for(int l=1;l<salDays[k].length;l++){
				if(String.valueOf(salDays[k][l]).equals("null") || String.valueOf(salDays[k][l]).equals("")){
					salDays[k][l]=0.00;
				}
				
				}
			
			finalObjSalDays[k][0]=salDays[k][0];
			finalObjSalDays[k][noOfMonths+2]=totSalDaysYearly;
			
			for (int i = 1; i < noOfMonths+2; i++) {
				finalObjSalDays[k][i]=(String.valueOf(salDays[k][i]));
			}
			
		}
		}catch (Exception e) {
			logger.info("error while calculating totalSalDays");
		}
		
		
	Object[][] finalArrear=new Object[arrear.length][noOfMonths+3]; 	
	for(int k=0;k<arrear.length;k++){
		double creditAmtArrYearly=0;
			for(int l=1;l<arrear[k].length-1;l++){
				if(l != noOfMonths +1){
				if(String.valueOf(arrear[k][l]).equals("null") || String.valueOf(arrear[k][l]).equals("")){
					arrear[k][l]="0";
				}
				creditAmtArrYearly+=Double.parseDouble(String.valueOf(arrear[k][l]));
				}
			}
			for (int i = 0; i < noOfMonths+3; i++) {
				if(i==0){
					finalArrear[k][i]=String.valueOf(arrear[k][i]);
				}else if(i== noOfMonths +1 ){
					finalArrear[k][i]=Utility.twoDecimals(creditAmtArrYearly);
				}else if(i== noOfMonths +2 ){
					finalArrear[k][i]=String.valueOf(arrear[k][noOfMonths +1]);
				}
				else{
					finalArrear[k][i]=Utility.twoDecimals(String.valueOf(arrear[k][i]));
				}
			}
			
					
				
	}
	Object[][] finalArrearDebit=new Object[arrearDebit.length][noOfMonths+3]; 	
	for(int k=0;k<arrearDebit.length;k++){
		double debitAmtArrYearly=0;
			for(int l=1;l<arrearDebit[k].length-1;l++){
				if(l != noOfMonths +1){
				if(String.valueOf(arrearDebit[k][l]).equals("null") || String.valueOf(arrearDebit[k][l]).equals("")){
					arrearDebit[k][l]="0";
				}
				debitAmtArrYearly+=Double.parseDouble(String.valueOf(arrearDebit[k][l]));
				}
			}
			for (int i = 0; i < noOfMonths+3; i++) {
				if(i==0){
					finalArrearDebit[k][i]=String.valueOf(arrearDebit[k][i]);
				}else if(i== noOfMonths +1 ){
					finalArrearDebit[k][i]=Utility.twoDecimals(debitAmtArrYearly);
				}else if(i== noOfMonths +2 ){
					finalArrearDebit[k][i]=String.valueOf(arrearDebit[k][noOfMonths +1]);
				}
				else{
					finalArrearDebit[k][i]=Utility.twoDecimals(String.valueOf(arrearDebit[k][i]));
				}
			}
								
	}
	
	
	Object[][] finalArrTotCrdt=new Object[totArrCreditAmt.length][noOfMonths+2]; 
	Object[][] finalArrNet=new Object[totArrNetAmt.length][noOfMonths+3]; 	
	//logger.info("Length of arr tot"+finalArrTotCrdt.length);
	for(int k=0;k<totArrCreditAmt.length;k++){
		double totYearly=0;
		for(int l=1;l<totArrCreditAmt[k].length;l++){
			if(String.valueOf(totArrCreditAmt[k][l]).equals("null") || String.valueOf(totArrCreditAmt[k][l]).equals("")){
				totArrCreditAmt[k][l]=0;
			}
			//logger.info(">>>>>>>"+totArrCreditAmt[k][l]);
			totYearly+=Double.parseDouble(String.valueOf(totArrCreditAmt[k][l]));
			}
		for (int i = 0; i < noOfMonths +2; i++) {
			if(i==0){
				finalArrTotCrdt[k][i]=totArrCreditAmt[k][i];
			}else if(i== noOfMonths+1){
				finalArrTotCrdt[k][i]=Utility.twoDecimals(totYearly);
			}else{
				finalArrTotCrdt[k][i]=Utility.twoDecimals(String.valueOf(totArrCreditAmt[k][i]));
			}
		}
		
	}
	for(int k=0;k<totArrNetAmt.length;k++){
		double totNetYearly=0;
		for(int l=1;l<totArrNetAmt[k].length;l++){
			if(String.valueOf(totArrNetAmt[k][l]).equals("null") || String.valueOf(totArrNetAmt[k][l]).equals("")){
				totArrNetAmt[k][l]=0;
			}
			//logger.info(">>>>>>>"+totArrNetAmt[k][l]);
			totNetYearly+=Double.parseDouble(String.valueOf(totArrNetAmt[k][l]));
			}
		
		finalArrNet[k][0]=totArrNetAmt[k][0];
		finalArrNet[k][noOfMonths+1]=Utility.twoDecimals(totNetYearly);
		
		for (int i = 1; i < noOfMonths+1; i++) {
			finalArrNet[k][i]=Utility.twoDecimals(String.valueOf(totArrNetAmt[k][i]));
		}
		
	}
	
	Object[][] finalArrTotDebit=new Object[totArrDebitAmt.length][noOfMonths+2]; 	
	logger.info("Length of arr tot"+finalArrTotDebit.length);
	for(int k=0;k<totArrDebitAmt.length;k++){
		double totYearly=0;
		for(int l=1;l<totArrDebitAmt[k].length;l++){
			if(String.valueOf(totArrDebitAmt[k][l]).equals("null") || String.valueOf(totArrDebitAmt[k][l]).equals("")){
				totArrDebitAmt[k][l]=0;
			}
			logger.info(">>>>>>>"+totArrDebitAmt[k][l]);
			totYearly+=Double.parseDouble(String.valueOf(totArrDebitAmt[k][l]));
			}
		for (int i = 0; i < noOfMonths +2; i++) {
			if(i==0){
				finalArrTotDebit[k][i]=totArrDebitAmt[k][i];
			}else if(i== noOfMonths+1){
				finalArrTotDebit[k][i]=Utility.twoDecimals(totYearly);
			}else{
				finalArrTotDebit[k][i]=Utility.twoDecimals(String.valueOf(totArrDebitAmt[k][i]));
			}
		}
	}
	
	
	Object[][] finalArrTax=new Object[taxArr.length][noOfMonths +2];
	for(int k=0;k<taxArr.length;k++){
		double totYearly=0;
		for(int l=1;l<taxArr[k].length;l++){
			if(String.valueOf(taxArr[k][l]).equals("null") || String.valueOf(taxArr[k][l]).equals("")){
				taxArr[k][l]=0;
			}
			totYearly+=Double.parseDouble(String.valueOf(taxArr[k][l]));
			}
		for (int i = 0; i < noOfMonths+2; i++) {
			if(i==0){
				finalArrTax[k][i]=taxArr[k][i];
			}else if(i== noOfMonths +1){
				finalArrTax[k][i]=Utility.twoDecimals(totYearly);
			}else{
				finalArrTax[k][i]=Utility.twoDecimals(String.valueOf(taxArr[k][i]));
			}
		}
		
		
	}
	
		/*
		 * The taxCreditData is used for displaying total taxable credit amounts for a particular month.
		 */
		Object[][] taxCreditData=new Object[taxData.length][noOfMonths+3];
		for(int k=0;k<taxData.length;k++){
			double totYearly=0;
			for(int l=1;l<taxData[k].length;l++){
				if(String.valueOf(taxData[k][l]).equals("null") || String.valueOf(taxData[k][l]).equals("")){
					taxData[k][l]=0;
				}
				totYearly+=Double.parseDouble(String.valueOf(taxData[k][l]));
				}
			for (int i = 0; i < noOfMonths+3; i++) {
				if(i==0){
					taxCreditData[k][i]=taxData[k][i];
				}else if(i==noOfMonths +2){
					taxCreditData[k][i]=Utility.twoDecimals(totYearly);
				}else{
					taxCreditData[k][i]=Utility.twoDecimals(String.valueOf(taxData[k][i]));
				}
			}
			
		}
		String fy="";
		String ty="";
		if(!(esr.getFromYear().trim().equals("") || esr.getFromYear().trim().equals("null") || esr.getFromYear().trim().equals(null))){
			fy=esr.getFromYear();
		}
		else{
			fy=frmYear;
		}
		if(!(esr.getToYear().trim().equals("") || esr.getToYear().trim().equals("null") || esr.getToYear().trim().equals(null))){
			ty=esr.getToYear();
		}
		else{
			ty=toYear;
		}
		
		String []colNames=new String[noOfMonths+4];	
		String []colNamesArr=new String[noOfMonths+3];	
		
		int[] cellWidth=new int [noOfMonths+4];
		int[] alignment=new int [noOfMonths+4];
		
		int[] cellWidthArr=new int [noOfMonths+3];
		int[] alignmentArr=new int [noOfMonths+3];
	
		colNames[0] ="Salary Head Name";
		colNames[noOfMonths+1] ="Settlement";
		
		colNames[noOfMonths+2] ="Total";
		colNames[noOfMonths+3] ="Taxable";
		
		colNamesArr[0] ="Salary Head Name";
		
		colNamesArr[noOfMonths+1] ="Total";
		colNamesArr[noOfMonths+2] ="Taxable";
		cellWidth[noOfMonths+3]=25;
		cellWidthArr[noOfMonths+2]=25;
		alignment[0]=0;
		alignmentArr[0]=0;
		int ledgerAprToDecLength =0;
		try {
			for (int i = 0; i < ledgerAprToDec.length; i++) {
				colNames[i + 1] = getColumnName(Integer.parseInt(String
						.valueOf(ledgerAprToDec[i][2])), Integer.parseInt(fy));
				colNamesArr[i + 1] = getColumnName(Integer.parseInt(String
						.valueOf(ledgerAprToDec[i][2])), Integer.parseInt(fy));
				ledgerAprToDecLength ++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			for (int i = 0; i < ledgerJanToMar.length; i++) {
				colNames[i + ledgerAprToDecLength + 1] = getColumnName(Integer
						.parseInt(String.valueOf(ledgerJanToMar[i][2])),
						Integer.parseInt(ty));
				colNamesArr[i + ledgerAprToDecLength + 1] = getColumnName(
						Integer.parseInt(String.valueOf(ledgerJanToMar[i][2])),
						Integer.parseInt(ty));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		for (int i = 0; i < cellWidth.length-1; i++) {
			cellWidth[i]=30;
			
		}
		for (int i = 1; i < alignment.length; i++) {
			alignment[i]=2;
		}
		for (int i = 0; i < cellWidthArr.length-1; i++) {
			cellWidthArr[i]=30;
			
		}
		for (int i = 1; i < alignmentArr.length; i++) {
			alignmentArr[i]=2;
		}
	
	finalObjCredit =Utility.joinArrays(finalObjCredit, otherSettlCredit, 1, 0);	
	finalObjCredit =Utility.joinArrays(finalObjCredit, finalObjTotCredit, 1, 0);
	finalObjCredit =Utility.joinArrays(finalObjCredit, taxCreditData, 1, 0);
	finalObjCredit =Utility.joinArrays(finalObjCredit, finalObjDebit, 1, 0);
	finalObjCredit =Utility.joinArrays(finalObjCredit, otherDeductions, 1, 0);
	if(pendingLoanAmt>0){
		finalObjCredit =Utility.joinArrays(finalObjCredit, pendingLoan, 1, 0);
	}
	if(pendingTaxAmt>0){
		finalObjCredit =Utility.joinArrays(finalObjCredit, pendingTax, 1, 0);
	}
	finalObjCredit =Utility.joinArrays(finalObjCredit, finalObjTotDebit, 1, 0);
	finalObjCredit =Utility.joinArrays(finalObjCredit, finalObjNetSal, 1, 0);
	finalObjCredit =Utility.joinArrays(finalObjCredit, finalObjSalDays, 1, 0);
	
	//rg.tableBodyBold(colNames,finalObjCredit,cellWidth,alignment,12);
	
	TableDataSet creditData = new TableDataSet();
	creditData.setHeader(colNames);
	creditData.setHeaderTable(true);
	creditData.setData(finalObjCredit);
	creditData.setCellAlignment(alignment);
	creditData.setCellWidth(cellWidth);
	//creditData.setBodyFontStyle(1);
	creditData.setBorderDetail(0);
	creditData.setHeaderLines(true);
	rg.addTableToDoc(creditData);
	
	
	Vector allwVector=null;
	try{
	allwVector=getAllowanceData(esr, rg);
	
	}catch(Exception e){
		e.printStackTrace();
	}
	//rg.addText("\n\nArrears:", 0,0,0);
	
	rg.addProperty(rg.PAGE_BREAK);
	
	TableDataSet totalEmp = new TableDataSet();
	totalEmp.setData(new Object[][]{{"Arrears: "}});
	totalEmp.setCellAlignment(new int[]{0});
	totalEmp.setCellWidth(new int[]{100});
	totalEmp.setBorderDetail(0);
	totalEmp.setBlankRowsAbove(1);
	rg.addTableToDoc(totalEmp);
	
	finalArrear =Utility.joinArrays(finalArrear, finalArrTotCrdt, 1, 0);
	finalArrear =Utility.joinArrays(finalArrear, finalArrTax, 1, 0);
	finalArrear =Utility.joinArrays(finalArrear, finalArrearDebit, 1, 0);
	finalArrear =Utility.joinArrays(finalArrear, finalArrTotDebit, 1, 0);
	
	//rg.tableBodyBold(colNamesArr,finalArrear,cellWidthArr,alignmentArr,12);
	
	TableDataSet finalArrearData = new TableDataSet();
	finalArrearData.setHeader(colNamesArr);
	finalArrearData.setHeaderTable(true);
	finalArrearData.setData(finalArrear);
	finalArrearData.setCellAlignment(alignmentArr);
	finalArrearData.setCellWidth(cellWidthArr);
	finalArrearData.setHeaderLines(true);
	rg.addTableToDoc(finalArrearData);
	
	//rg.tableBody(finalArrNet, cellWidthArr, alignmentArr,12);
	
	TableDataSet finalArrNetData = new TableDataSet();
	finalArrNetData.setData(finalArrNet);
	finalArrNetData.setCellAlignment(alignmentArr);
	finalArrNetData.setCellWidth(cellWidthArr);
	finalArrNetData.setBorderDetail(0);
	finalArrNetData.setBlankRowsAbove(1);
	finalArrNetData.setBorderLines(true);
	finalArrNetData.setBodyFontStyle(1);
	rg.addTableToDoc(finalArrNetData);
	
	Object [][]netIncomeObj=new Object[1][6];
	double netCredits =0.00, netDebits =0.00, allwAmt =0.00;
	netIncomeObj[0][0] = "NET CREDITS :";
	netIncomeObj[0][2] = "NET DEBITS :";
	netIncomeObj[0][4] = "NET INCOME :";
	double allwCreditAmt =0;
	double allwTaxAmt =0;
	try{
		if (allwVector.size() > 0) {
			allwCreditAmt = Double.parseDouble((String) allwVector.get(1));
			allwTaxAmt = Double.parseDouble((String) allwVector.get(2));
		}
		
	}catch (Exception e) {
		logger.error("exception while calculating allowance amount"+e);
	}	
	netCredits = Double.parseDouble(String.valueOf(finalObjTotCredit[0][finalObjTotCredit[0].length-1])) 
					  +	 allwCreditAmt  
					  +	 Double.parseDouble(String.valueOf(finalArrTotCrdt[0][finalArrTotCrdt[0].length-1])) ;
	
	netDebits = Double.parseDouble(String.valueOf(finalObjTotDebit[0][finalObjTotDebit[0].length-1])) + allwTaxAmt
					  +	 Double.parseDouble(String.valueOf(finalArrTotDebit[0][finalArrTotDebit[0].length-1])) ;
	
	
	netIncomeObj[0][1] = netCredits;
	netIncomeObj[0][3] = netDebits;
	netIncomeObj[0][5] = netCredits - netDebits;
	
	//rg.tableBody(netIncomeObj, new int[]{20, 30, 20, 30, 20, 30}, new int[]{0,0,0,0,0,0},12);
	
	TableDataSet netIncomeObjData = new TableDataSet();
	netIncomeObjData.setData(netIncomeObj);
	netIncomeObjData.setCellAlignment(new int[]{0,0,0,0,0,0});
	netIncomeObjData.setCellWidth( new int[]{20, 30, 20, 30, 20, 30});
	netIncomeObjData.setBorderDetail(0);
	netIncomeObjData.setBodyFontStyle(1);
	//creditData.setBodyBGColor(new BaseColor(200,200,200));
	netIncomeObjData.setBlankRowsAbove(1);
	rg.addTableToDoc(netIncomeObjData);
	
	} else {
		Object [][] msg = new Object[1][1];
		msg[0][0] = "Salary not available for "+empName;
		
		//rg.tableBodyNoBorder(msg,new int []{100},new int []{1},new int []{1},new int []{12});
		
		TableDataSet netIncomeObjData = new TableDataSet();
		netIncomeObjData.setData(msg);
		netIncomeObjData.setCellAlignment(new int[]{0});
		netIncomeObjData.setCellWidth( new int[]{100});
		netIncomeObjData.setBorderDetail(0);
		netIncomeObjData.setBlankRowsAbove(1);
		rg.addTableToDoc(netIncomeObjData);
	}
	
	}catch(Exception e){
		Object [][] msg = new Object[1][1];
		msg[0][0] = "Salary not available for "+empName;
		//rg.tableBodyNoBorder(msg,new int []{100},new int []{1},new int []{1},new int []{12});
		
		TableDataSet netIncomeObjData = new TableDataSet();
		netIncomeObjData.setData(msg);
		netIncomeObjData.setCellAlignment(new int[]{0});
		netIncomeObjData.setCellWidth( new int[]{100});
		netIncomeObjData.setBorderDetail(0);
		netIncomeObjData.setBlankRowsAbove(1);
		rg.addTableToDoc(netIncomeObjData);
		
		e.printStackTrace();
	}
	//rg.pageBreak();
	return rg;
}
/*
		 * Following function is called to generate the arrears details.
		 * It may be used later.
		 */
	
		
		public String getColumnName(int month, int frmYear){
			String columnName="";
			month= (month)%12;
			if(month==0){
				month = 12;
			}
			if(frmYear <10){
				columnName = ""+Utility.month(month).substring(0,3)+"-0"+frmYear;
			}else
				columnName = ""+Utility.month(month).substring(0,3)+"-"+frmYear;
			return columnName;
		}
		
		public Object [][] compareObject(Object [][]sourceObj,Object[][]destObj){
			Object returnObj [][]= new Object[sourceObj.length][3];
			int j = 0;
			try{
			for (int i = 0; i < returnObj.length; i++) {
				if(j<destObj.length && String.valueOf(sourceObj[i][0]).equals(String.valueOf(destObj[j][0]))){
					
						returnObj[i][0] = destObj[j][0];
						returnObj[i][1] = destObj[j][1];
						returnObj[i][2] = destObj[j++][2];
					
				}else{
					returnObj[i][0] = sourceObj[i][0];
					returnObj[i][1] = 0;
					returnObj[i][2] = sourceObj[i][2];
					
				}
			}
			return returnObj;
			}catch (Exception e) {
				return destObj;
			}
		}
		public Object [][] compareObjectSal(Object [][]sourceObj,Object[][]destObj){
			Object returnObj [][]= new Object[sourceObj.length][4];
			int j = 0;
			
			try{
			for (int i = 0; i < returnObj.length; i++) {
				if(j<destObj.length && String.valueOf(sourceObj[i][0]).equals(String.valueOf(destObj[j][3]))){
					
						returnObj[i][0] = destObj[j][0];
						returnObj[i][1] = destObj[j][1];
						returnObj[i][2] = destObj[j][2];
						returnObj[i][3] = destObj[j++][3];
					
				}else{
					returnObj[i][0] = sourceObj[i][1];
					returnObj[i][1] = 0;
					returnObj[i][2] = sourceObj[i][2];
					returnObj[i][3] = sourceObj[i][0];
				}
			}
			return returnObj;
			}catch (Exception e) {
				return destObj;
			}
		}
		public Object [][] compareObjectAllw(Object [][]sourceObj,Object[][]destObj){
			Object returnObj [][]= new Object[sourceObj.length][3];
			int j = 0;
			try{
			for (int i = 0; i < returnObj.length; i++) {
				if(j<destObj.length && String.valueOf(sourceObj[i][0]).equals(String.valueOf(destObj[j][0]))){
					
						returnObj[i][0] = destObj[j][0];
						returnObj[i][1] = destObj[j][1];
						returnObj[i][2] = destObj[j++][2];
					
				}else{
					returnObj[i][0] = sourceObj[i][0];
					returnObj[i][1] = 0;
					returnObj[i][2] = 0;
				}
			}
			
			}catch (Exception e) {
				for (int i = 0; i < returnObj.length; i++) {
					returnObj[i][0] = sourceObj[i][0];
					returnObj[i][1] = 0;
					returnObj[i][2] = 0;
					logger.info("exception in compare"+e);
					e.printStackTrace();
				}
				
			}
			return returnObj;
			
		}
		/* 
		 * method name : checkNull
		 * purpose     : check the string is null or not
		 * return type : String
		 * parameter   : String
		 */
		public String checkNull(String result) {
			if (result == null || result.equals("null")) {
				return "";
			}  // end of if
			else {
				return result;
			} // end of else
		}
		
		public org.paradyne.lib.ireportV2.ReportGenerator salReport(org.paradyne.lib.ireportV2.ReportGenerator rg,EmpSalaryReg esr){
			
			String frmYear="",token="";
			String toYear="";
			String empId="",empCode="",empName="",empToken="";
			int year=0,yearTo=0;
			
			empId =  esr.getEmpId();
			empName =  esr.getEmpName();
			empToken =  esr.getEmpToken();
			frmYear = esr.getFromYear();
			toYear = esr.getToYear();
		
			
			
			if(!(esr.getEmpId().trim().equals("") || esr.getEmpId().trim().equals("null") || esr.getEmpId().equals(null))){
				empCode=esr.getEmpId();
			}
			else{
				empCode=empId;
			}
			if(!(esr.getFromYear().trim().equals("") || esr.getFromYear().trim().equals("null") || esr.getFromYear().trim().equals(null))){
				year=Integer.parseInt(esr.getFromYear());
			}
			else{
				year=Integer.parseInt(frmYear);
			}
			if(!(esr.getToYear().trim().equals("") || esr.getToYear().trim().equals("null") || esr.getToYear().trim().equals(null))){
				yearTo=Integer.parseInt(esr.getToYear());
			}
			else{
				yearTo=Integer.parseInt(toYear);
			}
			if(!(esr.getEmpToken().trim().equals("") || esr.getEmpToken().trim().equals("null") || esr.getEmpToken().trim().equals(null))){
				token=esr.getEmpToken();
			}
			else{
				token=empToken;
			}
			if(!(esr.getEmpName().trim().equals("") || esr.getEmpName().trim().equals("null") || esr.getEmpName().trim().equals(null))){
				empName=esr.getEmpName();
			}
			else{
				empName=empToken;
			}
			/*String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
			Object[][] dateData = getSqlModel().getSingleResult(dateQuery);*/
			//if(esr.getReportType().equals("Pdf")){/
				/*Object titleObj [][]= new Object[1][3];
				titleObj[0][0] = "";
				titleObj[0][1] = "Employee Earnings for the Financial Year "+year+"-"+yearTo;
				titleObj[0][2] = "Date : "+String.valueOf(dateData[0][0]);

				//rg.tableBodyNoBorder(titleObj, new int[]{25,  50, 25},new int[]{0,  1, 2},new int[]{0, 1, 0},new int[]{0,  12, 10});
				
				TableDataSet titleObjData = new TableDataSet();
				titleObjData.setData(titleObj);
				titleObjData.setCellAlignment(new int[]{0,  1, 2});
				titleObjData.setCellWidth(new int[]{25,  50, 25});
				titleObjData.setBorderDetail(0);
				titleObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(titleObjData);*/
				
				Object [][] empDetails = new Object[3][2];
			
				empDetails [0][0] = "Employee Id  : "+token;
				empDetails [0][1] = "Employee Name : "+empName;
				
				empDetails [1][0] = "Employee Branch  : "+esr.getBrnName();
				empDetails [1][1] = "Employee Division : "+esr.getEmpDiv();
				
				empDetails [2][0] = "Employee Grade : "+checkNull(esr.getEmpGrade());
				empDetails [2][1] = "";
				int [] width = {45,45};
				int [] align= {0,0};
			 
			//rg.tableBodyNoBorder(empDetails,colsEmp,alignEmp,new int []{0,0},new int []{11,11});
			TableDataSet empDetailsData = new TableDataSet();
			empDetailsData.setData(empDetails);
			empDetailsData.setCellAlignment(align);
			empDetailsData.setCellColSpan(new int[]{5,5});
			empDetailsData.setCellWidth(width);
			empDetailsData.setBorderDetail(0);
			empDetailsData.setBlankRowsAbove(1);
			rg.addTableToDoc(empDetailsData);
				
			//}
			
			/*else {
				 Object [][] title = new Object[1][3];
				 title [0][0] = "";
				 title [0][1] = "";
				 title [0][2] = "Employee Earnings for the Financial Year "+year+"-"+yearTo;  
				 
				 int [] cols = {20,20,30};
				 int [] align = {0,0,1};
				 
				//rg.tableBodyNoCellBorder(title,cols,align,1); 
				
				TableDataSet titleData = new TableDataSet();
				titleData.setData(title);
				titleData.setCellAlignment(align);
				titleData.setCellWidth(cols);
				titleData.setBorderDetail(0);
				titleData.setBlankRowsAbove(1);
				rg.addTableToDoc(titleData);
				
				//rg.addText("Date: " + dateData[0][0], 0, 2, 0);
				//rg.addText("Employee Id  :"+token,0,0,0);
				//rg.addText("Employee Name:"+empName,0,0,0);
				//rg.addText("Employee Branch  :"+esr.getBrnName(),0,0,0);
				//rg.addText("Employee Division:"+esr.getEmpDiv(),0,0,0);
				//rg.addText("Employee Grade:"+checkNull(esr.getEmpGrade()),0,0,0);
				
				TableDataSet dataTable = new TableDataSet();
				dataTable.setData(new Object[][]{{"Date: " + dateData[0][0]},
							{"Employee Id  :"+token},
							{"Employee Name:"+empName},
							{"Employee Branch  :"+esr.getBrnName()},
							{"Employee Division:"+esr.getEmpDiv()},
							{"Employee Grade:"+checkNull(esr.getEmpGrade())}} );
				dataTable.setCellAlignment(new int[]{0});
				dataTable.setCellWidth(new int[]{100});
				dataTable.setBorderDetail(0);
				dataTable.setBlankRowsAbove(1);
				rg.addTableToDoc(dataTable);
			}*/
			
			//rg = getAllowanceTable(rg,esr);	
			
			//String creditQuery="SELECT CREDIT_CODE,CREDIT_NAME,CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
			String creditQuery="SELECT CREDIT_CODE,CREDIT_NAME,CASE WHEN CREDIT_TAXABLE_FLAG='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_CREDIT_HEAD WHERE CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
			Object creditCode[][]=getSqlModel().getSingleResult(creditQuery);
		
			String debitQuery="SELECT DEBIT_CODE,DEBIT_NAME, 'Yes' FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_CODE";
			Object debitCode[][]=getSqlModel().getSingleResult(debitQuery);
			
			int noOfMonths=0;
			
			//UPDATES BY REEBA BEGINS
			
			//IF FROM YEAR = TO YEAR, ledgerQueryAprToDec WITH MONTHS BETWEEN FRM_MONTH & TO_MONTH
			//ELSE IF TO YEAR > FROM YEAR, ledgerQueryAprToDec - YEAR = FROM_YEAR, 4 = FROM_MONTH, 12=12
			//& ledgerQueryJanToMar WITH YEAR = TO_YEAR AND MONTHS BETWEEN 1 = 1 & 3 = TO_MONTH
			
			logger.info("FROM MONTH : "+esr.getFromMonth());
			logger.info("FROM YEAR  : "+esr.getFromYear());
			logger.info("TO MONTH   : "+esr.getToMonth());
			logger.info("TO YEAR    : "+esr.getToYear());
			Object ledgerAprToDec[][]= null;
			Object ledgerJanToMar[][]=null;
			String ledgerCodeString="0";
			
			if(Integer.parseInt(String.valueOf(esr.getFromYear())) == Integer.parseInt(String.valueOf(esr.getToYear()))){
				if(Integer.parseInt(esr.getFromMonth())>3){
					String ledgerQueryAprToDec = "SELECT LEDGER_CODE,SAL_ONHOLD,LEDGER_MONTH,NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m' FROM HRMS_SALARY_LEDGER " 
						+" LEFT JOIN HRMS_SALARY_"+esr.getFromYear()+" ON(HRMS_SALARY_"+esr.getFromYear()+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
						+" WHERE LEDGER_MONTH BETWEEN "+ esr.getFromMonth()+" AND "+ esr.getToMonth()+" AND LEDGER_YEAR="+String.valueOf(esr.getFromYear())+" AND LEDGER_STATUS = 'SAL_FINAL'  AND EMP_ID ="+empCode+" AND SAL_ONHOLD='N'"
						+" ORDER BY LEDGER_MONTH";
					
					ledgerAprToDec=getSqlModel().getSingleResult(ledgerQueryAprToDec);
					
					try {
						noOfMonths =ledgerAprToDec.length;
						for (int i = 0; i < noOfMonths; i++) {
							
								ledgerCodeString +=","+String.valueOf(ledgerAprToDec[i][0]);
							
							
						}
					} catch (Exception e) {
						noOfMonths =0;
						
					}
					} else {
					String ledgerQueryJanToMar = "SELECT LEDGER_CODE,SAL_ONHOLD,LEDGER_MONTH,NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m' FROM HRMS_SALARY_LEDGER " 
						+"LEFT JOIN HRMS_SALARY_"+esr.getToYear()+" ON(HRMS_SALARY_"+esr.getToYear()+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
						+"WHERE LEDGER_MONTH BETWEEN 1 AND "+esr.getToMonth()+" AND LEDGER_YEAR="+String.valueOf(esr.getToYear())+" AND LEDGER_STATUS = 'SAL_FINAL'  AND EMP_ID ="+empCode+ " AND SAL_ONHOLD='N'"
						+" ORDER BY LEDGER_MONTH";
					
					try {
						ledgerJanToMar=getSqlModel().getSingleResult(ledgerQueryJanToMar);
						noOfMonths +=ledgerJanToMar.length;
						
						for (int i = 0; i < ledgerJanToMar.length; i++) {
							ledgerCodeString +=","+String.valueOf(ledgerJanToMar[i][0]);
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					}
				}
			else if(Integer.parseInt(String.valueOf(esr.getToYear())) > Integer.parseInt(String.valueOf(esr.getFromYear()))){
				String ledgerQueryAprToDec = "SELECT LEDGER_CODE,SAL_ONHOLD,LEDGER_MONTH,NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m' FROM HRMS_SALARY_LEDGER " 
					+" LEFT JOIN HRMS_SALARY_"+esr.getFromYear()+" ON(HRMS_SALARY_"+esr.getFromYear()+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
					+" WHERE LEDGER_MONTH BETWEEN "+ esr.getFromMonth()+" AND 12 AND LEDGER_YEAR="+String.valueOf(esr.getFromYear())+" AND LEDGER_STATUS = 'SAL_FINAL'  AND EMP_ID ="+empCode+ " AND SAL_ONHOLD='N'"
					+" ORDER BY LEDGER_MONTH";
				
				ledgerAprToDec=getSqlModel().getSingleResult(ledgerQueryAprToDec);
				
				try {
					noOfMonths =ledgerAprToDec.length;
					for (int i = 0; i < noOfMonths; i++) {
						ledgerCodeString +=","+String.valueOf(ledgerAprToDec[i][0]);
						
					}
				} catch (Exception e) {
					noOfMonths =0;
					
				}
				String ledgerQueryJanToMar = "SELECT LEDGER_CODE,SAL_ONHOLD,LEDGER_MONTH,NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m' FROM HRMS_SALARY_LEDGER " 
					+"LEFT JOIN HRMS_SALARY_"+esr.getToYear()+" ON(HRMS_SALARY_"+esr.getToYear()+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
					+"WHERE LEDGER_MONTH BETWEEN 1 AND "+esr.getToMonth()+" AND LEDGER_YEAR="+String.valueOf(esr.getToYear())+" AND LEDGER_STATUS = 'SAL_FINAL'  AND EMP_ID ="+empCode+ " AND SAL_ONHOLD='N'"
					+" ORDER BY LEDGER_MONTH";
				
				try {
					ledgerJanToMar=getSqlModel().getSingleResult(ledgerQueryJanToMar);
					noOfMonths +=ledgerJanToMar.length;
					
					for (int i = 0; i < ledgerJanToMar.length; i++) {
						ledgerCodeString +=","+String.valueOf(ledgerJanToMar[i][0]);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//UPDATES BY REEBA ENDS
			
			Object[][] para=new Object[creditCode.length][noOfMonths+2];
			Object[][] arrear=new Object[creditCode.length][noOfMonths+2];
			Object[][] arrearDebit=new Object[debitCode.length][noOfMonths+2];
			Object[][] paraDebt=new Object[debitCode.length][noOfMonths+2];
		//	Object[][] allowance =new Object[creditCode.length][14];
			
			Object[][] totCreditAmt=new Object[1][noOfMonths+1];
			Object[][] totDebitAmt=new Object[1][noOfMonths+1];
			
			Object[][] netSalAmt=new Object[1][noOfMonths+1];
			Object[][] salDays=new Object[1][noOfMonths+1];
			
			Object[][] taxData=new Object[1][noOfMonths+1];
			Object[][] totArrCreditAmt=new Object[1][noOfMonths+1];
			Object[][] totArrNetAmt=new Object[1][noOfMonths+1];
			Object[][] totArrDebitAmt=new Object[1][noOfMonths+1];
			Object[][] taxArr=new Object[1][noOfMonths+1];
			int arrCount=0;
			
			try{	
			if(noOfMonths>0){
				int colCount=1;
				int creditCol=1;
				int debitCol=1;
				int arrearCol=1;
				int arrTaxCol=1;
				int arrCreditCol=1;
				int arrDebitCol=1;
				int taxCreditCount=1;
			
				try {
					if(ledgerAprToDec !=null && ledgerAprToDec.length > 0){
					for (int j = 0; j < ledgerAprToDec.length; j++) {
						int loopMonth = Integer.parseInt(String
								.valueOf(ledgerAprToDec[j][2]));
						Object[][] credit_amount = getCreditSalAmt(empCode,
								year, loopMonth, String
										.valueOf(ledgerAprToDec[j][0]),
								"Final", creditCode);
						Object[][] debit_amount = getDebitSalAmt(empCode, year,
								loopMonth,
								String.valueOf(ledgerAprToDec[j][0]), "Final",
								debitCode);
						Object[][] totCredit = new Object[1][1];
						Object[][] totDebit = new Object[1][1];
						double cr_amt = 0.00;
						double db_amt = 0.00;
						double taxCredit = 0.00;

						/*
						 * Following loop calculates the total credit amount per month and the total tax amount for whom TAXABLE_FLAG='Y'
						 
						 */

						if (credit_amount != null && credit_amount.length > 0) {
							for (int i = 0; i < credit_amount.length; i++) {

								if (String.valueOf(credit_amount[i][2]).equals(
										"Yes")) {

									taxCredit += Double.parseDouble(String
											.valueOf(credit_amount[i][1]));

								}
								cr_amt += Double.parseDouble(String
										.valueOf(credit_amount[i][1]));

							}
						}
						totCredit[0][0] = cr_amt;

						if (debit_amount != null && debit_amount.length > 0) {

							for (int i = 0; i < debit_amount.length; i++) {
								db_amt += Double.parseDouble(String
										.valueOf(debit_amount[i][1]));
							}
						}
						totDebit[0][0] = db_amt;

						totCreditAmt[0][0] = "TOTAL CREDIT";
						netSalAmt[0][0] = "NET SALARY";
						salDays[0][0] = "DAYS PRESENT";
						totCreditAmt[0][0] = "TOTAL CREDIT";
						totCreditAmt[0][creditCol] = totCredit[0][0];
						netSalAmt[0][creditCol] = cr_amt - db_amt;
						salDays[0][creditCol] = Utility.getViewDays(String
								.valueOf(ledgerAprToDec[j][3]));
						totDebitAmt[0][0] = "TOTAL DEBIT";

						taxData[0][0] = "TOTAL TAXABLE CREDIT";
						taxData[0][taxCreditCount] = taxCredit;//tax[0][0];

						if (credit_amount != null && credit_amount.length > 0) {

							/*
							 * Following loop sets the credit head and their respective values
							 */
							for (int i = 0; i < credit_amount.length; i++)//4 records
							{
								para[i][0] = credit_amount[i][0];

								if (credit_amount[i][1].equals("null")) {
									para[i][colCount] = "";
								}//End of if
								else {

									para[i][colCount] = (String
											.valueOf(credit_amount[i][1]));

								}

								para[i][noOfMonths + 1] = credit_amount[i][2];

							}//End of for loop of credit_amount

						}

						if (debit_amount != null && debit_amount.length > 0) {

							/*
							 * Following loop sets the debit head and their respective values
							 */
							for (int i = 0; i < debit_amount.length; i++)//4 records
							{
								paraDebt[i][0] = debit_amount[i][0];

								if (debit_amount[i][1].equals("null")) {
									paraDebt[i][colCount] = "";
								}//End of if
								else {

									paraDebt[i][colCount] = (String
											.valueOf(debit_amount[i][1]));

								}

								paraDebt[i][noOfMonths + 1] = debit_amount[i][2];
								totDebitAmt[0][debitCol] = totDebit[0][0];

							}//End of for loop of credit_amount

						}

						double taxArrear = 0.00;
						double arr_amt = 0.00;
						double arr_amt_debit = 0.00;
						/**
						 * following loop is used to calculate the total arrear credit amount and the total arrear 
						 * taxable amount.
						 */
						Object[][] arrearAmt = getArrears(String.valueOf(year),
								esr.getEmpId(), "" + loopMonth, "Final",
								creditCode);
						if (arrearAmt != null && arrearAmt.length > 0) {
							for (int i = 0; i < arrearAmt.length; i++) {

								if (String.valueOf(arrearAmt[i][2]).equals(
										"Yes")) {

									taxArrear += Double.parseDouble(String
											.valueOf(arrearAmt[i][1]));

								}

								arr_amt += Double.parseDouble(String
										.valueOf(arrearAmt[i][1]));

							}
						}

						Object[][] arrearAmtDebit = getArrearsDebit("" + year,
								esr.getEmpId(), "" + loopMonth, "Final",
								debitCode);

						if (arrearAmtDebit != null && arrearAmtDebit.length > 0) {
							for (int i = 0; i < arrearAmtDebit.length; i++) {
								arr_amt_debit += Double.parseDouble(String
										.valueOf(arrearAmtDebit[i][1]));

							}
						}

						totArrCreditAmt[0][0] = "TOT CREDIT";
						totArrDebitAmt[0][0] = "TOT DEBIT";

						totArrCreditAmt[0][arrCreditCol] = arr_amt;//[0][0];
						totArrDebitAmt[0][arrDebitCol] = arr_amt_debit;//[0][0];	
						taxArr[0][0] = "TOT TAXABLE CREDIT";
						taxArr[0][arrTaxCol] = taxArrear;//tax[0][0];
						totArrNetAmt[0][0] = "NET ARREARS";
						totArrNetAmt[0][arrTaxCol] = arr_amt - arr_amt_debit;//[0][0];	(NET ARREARS=ARREAR CREDIT-ARREAR DEBIT)
						/**
						 * following loop is used to select the credit head name and the corresponding amount.
						 */
						if (arrearAmt != null && arrearAmt.length > 0) {

							for (int i = 0; i < arrearAmt.length; i++) {
								arrear[i][0] = arrearAmt[i][0];//Credit Head

								if (arrearAmt[i][1].equals("null")) {
									arrear[i][arrearCol] = "";
								}//End of if
								else {
									arrear[i][arrearCol] = (String
											.valueOf(arrearAmt[i][1]));//Credit Amount
								}

								arrear[i][noOfMonths + 1] = arrearAmt[i][2];//Is Taxable or not

							}//End of for loop of credit_amount

						}
						if (arrearAmtDebit != null && arrearAmtDebit.length > 0) {
							for (int i = 0; i < arrearAmtDebit.length; i++) {
								arrearDebit[i][0] = arrearAmtDebit[i][0];//Debit Head
								if (arrearAmtDebit[i][1].equals("null")) {
									arrearDebit[i][arrearCol] = "";
								}//End of if
								else {
									arrearDebit[i][arrearCol] = (String
											.valueOf(arrearAmtDebit[i][1]));//Debit Amount

								}

								arrearDebit[i][noOfMonths + 1] = arrearAmtDebit[i][2];//Is Taxable or not

							}//End of for loop of credit_amount
						}

						arrearCol++;
						arrCreditCol++;
						arrDebitCol++;
						arrTaxCol++;
						arrCount++;

						colCount++;
						creditCol++;
						debitCol++;
						taxCreditCount++;

					}
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if(ledgerJanToMar != null && ledgerJanToMar.length > 0) {
					for (int j = 0; j < ledgerJanToMar.length; j++) {
						int loopMonth = Integer.parseInt(String
								.valueOf(ledgerJanToMar[j][2]));
						Object[][] credit_amount = getCreditSalAmt(empCode,
								yearTo, loopMonth, String
										.valueOf(ledgerJanToMar[j][0]),
								"Final", creditCode);
						Object[][] debit_amount = getDebitSalAmt(empCode, yearTo,
								loopMonth,
								String.valueOf(ledgerJanToMar[j][0]), "Final",
								debitCode);
						Object[][] totCredit = new Object[1][1];
						Object[][] totDebit = new Object[1][1];
						double cr_amt = 0.00;
						double db_amt = 0.00;
						double taxCredit = 0.00;

						/*
						 * Following loop calculates the total credit amount per month and the total tax amount for whom TAXABLE_FLAG='Y'
						 
						 */

						if (credit_amount != null && credit_amount.length > 0) {
							for (int i = 0; i < credit_amount.length; i++) {

								if (String.valueOf(credit_amount[i][2]).equals(
										"Yes")) {

									taxCredit += Double.parseDouble(String
											.valueOf(credit_amount[i][1]));

								}
								cr_amt += Double.parseDouble(String
										.valueOf(credit_amount[i][1]));

							}
						}
						totCredit[0][0] = cr_amt;

						if (debit_amount != null && debit_amount.length > 0) {

							for (int i = 0; i < debit_amount.length; i++) {
								db_amt += Double.parseDouble(String
										.valueOf(debit_amount[i][1]));
							}
						}
						totDebit[0][0] = db_amt;

						totCreditAmt[0][creditCol] = totCredit[0][0];
						netSalAmt[0][creditCol] = cr_amt - db_amt; // net Salary
						salDays[0][creditCol] = Utility.getViewDays(String.valueOf(ledgerJanToMar[j][3]));

						taxData[0][0] = "TOTAL TAXABLE CREDIT";
						taxData[0][taxCreditCount] = taxCredit;//tax[0][0];

						if (credit_amount != null && credit_amount.length > 0) {

							/*
							 * Following loop sets the credit head and their respective values
							 */
							for (int i = 0; i < credit_amount.length; i++)//4 records
							{
								para[i ][0] = credit_amount[i][0];

								if (credit_amount[i][1].equals("null")) {
									para[i][colCount] = "";
								}//End of if
								else {

									para[i][colCount] = (String
											.valueOf(credit_amount[i][1]));

								}

								para[i][noOfMonths + 1] = credit_amount[i][2];

							}//End of for loop of credit_amount

						}

						if (debit_amount != null && debit_amount.length > 0) {

							/*
							 * Following loop sets the debit head and their respective values
							 */
							for (int i = 0; i < debit_amount.length; i++)//4 records
							{
								paraDebt[i][0] = debit_amount[i][0];

								if (debit_amount[i][1].equals("null")) {
									paraDebt[i][colCount] = "";
								}//End of if
								else {

									paraDebt[i][colCount] = (String
											.valueOf(debit_amount[i][1]));

								}

								paraDebt[i][noOfMonths + 1] = debit_amount[i][2];
								totDebitAmt[0][debitCol] = totDebit[0][0];

							}//End of for loop of credit_amount

						}

						double taxArrear = 0.00;
						double arr_amt = 0.00;
						double arr_amt_debit = 0.00;
						/**
						 * following loop is used to calculate the total arrear credit amount and the total arrear 
						 * taxable amount.
						 */
						Object[][] arrearAmt = getArrears(String.valueOf(yearTo),
								esr.getEmpId(), "" + loopMonth, "Final",
								creditCode);
						if (arrearAmt != null && arrearAmt.length > 0) {
							for (int i = 0; i < arrearAmt.length; i++) {

								if (String.valueOf(arrearAmt[i][2]).equals(
										"Yes")) {

									taxArrear += Double.parseDouble(String
											.valueOf(arrearAmt[i][1]));

								}

								arr_amt += Double.parseDouble(String
										.valueOf(arrearAmt[i][1]));

							}
						}

						Object[][] arrearAmtDebit = getArrearsDebit("" + yearTo,
								esr.getEmpId(), "" + loopMonth, "Final",
								debitCode);

						if (arrearAmtDebit != null && arrearAmtDebit.length > 0) {
							for (int i = 0; i < arrearAmtDebit.length; i++) {
								arr_amt_debit += Double.parseDouble(String
										.valueOf(arrearAmtDebit[i][1]));

							}
						}

						totArrCreditAmt[0][arrCreditCol] = arr_amt;//[0][0];
						totArrDebitAmt[0][arrDebitCol] = arr_amt_debit;//[0][0];	
						taxArr[0][arrTaxCol] = taxArrear;//tax[0][0];
						totArrNetAmt[0][arrTaxCol] = arr_amt - arr_amt_debit;//[0][0];	(NET ARREARS=ARREAR CREDIT-ARREAR DEBIT)

						/**
						 * following loop is used to select the credit head name and the corresponding amount.
						 */
						if (arrearAmt != null && arrearAmt.length > 0) {

							for (int i = 0; i < arrearAmt.length; i++) {
								arrear[i][0] = arrearAmt[i][0];//Credit Head

								if (arrearAmt[i][1].equals("null")) {
									arrear[i][arrearCol] = "";
								}//End of if
								else {
									arrear[i][arrearCol] = (String
											.valueOf(arrearAmt[i][1]));//Credit Amount
								}

								arrear[i][noOfMonths + 1] = arrearAmt[i][2];//Is Taxable or not

							}//End of for loop of credit_amount

						}
						if (arrearAmtDebit != null && arrearAmtDebit.length > 0) {
							for (int i = 0; i < arrearAmtDebit.length; i++) {
								arrearDebit[i][0] = arrearAmtDebit[i][0];//Debit Head
								if (arrearAmtDebit[i][1].equals("null")) {
									arrearDebit[i][arrearCol] = "";
								}//End of if
								else {
									arrearDebit[i][arrearCol] = (String
											.valueOf(arrearAmtDebit[i][1]));//Debit Amount

								}

								arrearDebit[i][noOfMonths + 1] = arrearAmtDebit[i][2];//Is Taxable or not

							}//End of for loop of credit_amount
						}

						arrearCol++;
						arrCreditCol++;
						arrDebitCol++;
						arrTaxCol++;
						arrCount++;

						colCount++;
						creditCol++;
						debitCol++;
						taxCreditCount++;

					}
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
				totCreditAmt[0][0] = "TOTAL CREDIT";
				netSalAmt[0][0] = "NET SALARY";
				salDays[0][0] = "DAYS PRESENT";
				totCreditAmt[0][0] = "TOTAL CREDIT";
				totDebitAmt[0][0] = "TOTAL DEBIT";
				taxData[0][0] = "TOTAL TAXABLE CREDIT";
				Object[][] finalObjCredit=new Object[para.length][noOfMonths+3];
				Object[][] finalObjDebit=new Object[paraDebt.length][noOfMonths+3];
				/*
			 * The finalObjCredit table is used for displaying the credit head names and their respective values.
			 */
				for(int k=0;k<para.length;k++){
					double creditAmtYearly=0;
						for(int l=1;l<para[k].length-1;l++){
							
							if(String.valueOf(para[k][l]).equals("null") || String.valueOf(para[k][l]).equals("")){
								para[k][l]="0";
							}
									
							creditAmtYearly+=Double.parseDouble(String.valueOf(para[k][l]));
							
						}
						finalObjCredit[k][0]=String.valueOf(para[k][0]);
						finalObjCredit[k][noOfMonths+1]=Utility.twoDecimals(creditAmtYearly);
						finalObjCredit[k][noOfMonths+2]=String.valueOf(para[k][noOfMonths+1]);
					for (int i = 1; i < noOfMonths+1; i++) {
							finalObjCredit[k][i]=Utility.twoDecimals(String.valueOf(para[k][i]));
						}
					
				} 
				if(paraDebt != null && paraDebt.length > 0){
					for(int k=0;k<paraDebt.length;k++){
						double debitAmtYearly=0;
							for(int l=1;l<paraDebt[k].length-1;l++){
								
								if(String.valueOf(paraDebt[k][l]).equals("null") || String.valueOf(paraDebt[k][l]).equals("")){
									paraDebt[k][l]="0";
								}
										
								debitAmtYearly+=Double.parseDouble(String.valueOf(paraDebt[k][l]));
								
							}
					
							finalObjDebit[k][0]=String.valueOf(paraDebt[k][0]);
							finalObjDebit[k][noOfMonths+1]=Utility.twoDecimals(debitAmtYearly);
							finalObjDebit[k][noOfMonths+2]=String.valueOf(paraDebt[k][noOfMonths+1]);
						for (int i = 1; i < noOfMonths+1; i++) {
								finalObjDebit[k][i]=Utility.twoDecimals(String.valueOf(paraDebt[k][i]));
							}
						
					} 
				}
				
				
			Object[][] finalObjTotCredit=new Object[totCreditAmt.length][noOfMonths+2];
			Object[][] finalObjTotDebit=new Object[totDebitAmt.length][noOfMonths+2];
			Object[][] finalObjNetSal=new Object[netSalAmt.length][noOfMonths+3];
			Object[][] finalObjSalDays=new Object[salDays.length][noOfMonths+2];
			/*
			 * The finalObjTotCredit is used for displaying total credit amounts for a particular month.
			 */
				
			if(totCreditAmt != null && totCreditAmt.length > 0){
				for(int k=0;k<totCreditAmt.length;k++){
					double totCrediAmtYearly=0;
					for(int l=1;l<totCreditAmt[k].length;l++){
						if(String.valueOf(totCreditAmt[k][l]).equals("null") || String.valueOf(totCreditAmt[k][l]).equals("")){
							totCreditAmt[k][l]=0.00;
						}
						
						totCrediAmtYearly+=Double.parseDouble(String.valueOf(totCreditAmt[k][l]));
						}
					
					finalObjTotCredit[k][0]=totCreditAmt[k][0];
					finalObjTotCredit[k][noOfMonths+1]=Utility.twoDecimals(totCrediAmtYearly);
					
					for (int i = 1; i < noOfMonths+1; i++) {
						finalObjTotCredit[k][i]=Utility.twoDecimals(String.valueOf(totCreditAmt[k][i]));
					}
					
				}
			}
				
				
			for(int k=0;k<finalObjTotDebit.length;k++){
				double totDebitAmtYearly=0;
				for(int l=1;l<totDebitAmt[k].length;l++){
					if(String.valueOf(totDebitAmt[k][l]).equals("null") || String.valueOf(totDebitAmt[k][l]).equals("")){
						totDebitAmt[k][l]=0.00;
					}
					
					totDebitAmtYearly+=Double.parseDouble(String.valueOf(totDebitAmt[k][l]));
					}
				finalObjTotDebit[k][0]=totDebitAmt[k][0];
				finalObjTotDebit[k][noOfMonths+1]=Utility.twoDecimals(totDebitAmtYearly);
				
				for (int i = 1; i < noOfMonths+1; i++) {
					finalObjTotDebit[k][i]=Utility.twoDecimals(String.valueOf(totDebitAmt[k][i]));
				}
				
			}
				
			for(int k=0;k<netSalAmt.length;k++){
				double totNetSalAmtYearly=0;
				for(int l=1;l<netSalAmt[k].length;l++){
					if(String.valueOf(netSalAmt[k][l]).equals("null") || String.valueOf(netSalAmt[k][l]).equals("")){
						netSalAmt[k][l]=0.00;
					}
					
					totNetSalAmtYearly+=Double.parseDouble(String.valueOf(netSalAmt[k][l]));
					}
				
				finalObjNetSal[k][0]=netSalAmt[k][0];
				finalObjNetSal[k][noOfMonths+1]=Utility.twoDecimals(totNetSalAmtYearly);
				
				for (int i = 1; i < noOfMonths+1; i++) {
					finalObjNetSal[k][i]=Utility.twoDecimals(String.valueOf(netSalAmt[k][i]));
				}
				
			}
			try{
				for(int k=0;k<salDays.length;k++){
					String  totSalDaysYearly=getSalDaysSum(ledgerCodeString, empCode, esr);
					for(int l=1;l<salDays[k].length;l++){
						if(String.valueOf(salDays[k][l]).equals("null") || String.valueOf(salDays[k][l]).equals("")){
							salDays[k][l]=0.00;
						}
						}
					
					finalObjSalDays[k][0]=salDays[k][0];
					finalObjSalDays[k][noOfMonths+1]=totSalDaysYearly;
					
					for (int i = 1; i < noOfMonths+1; i++) {
						finalObjSalDays[k][i]=(String.valueOf(salDays[k][i]));
					}
					
				}
				}catch (Exception e) {
					logger.info("error while calculating total salDays");
				}
				
			Object[][] finalArrear=new Object[arrear.length][noOfMonths+3]; 	
			for(int k=0;k<arrear.length;k++){
				double creditAmtArrYearly=0;
					for(int l=1;l<arrear[k].length-1;l++){
						
						if(String.valueOf(arrear[k][l]).equals("null") || String.valueOf(arrear[k][l]).equals("")){
							arrear[k][l]="0";
						}
								
						creditAmtArrYearly+=Double.parseDouble(String.valueOf(arrear[k][l]));
						
					}
					finalArrear[k][0]=String.valueOf(arrear[k][0]);
					finalArrear[k][noOfMonths+1]=Utility.twoDecimals(creditAmtArrYearly);
					finalArrear[k][noOfMonths+2]=String.valueOf(arrear[k][noOfMonths+1]);
					for (int i = 1; i < noOfMonths+1; i++) {
						finalArrear[k][i]=Utility.twoDecimals(String.valueOf(arrear[k][i]));
					}
					
						
			}
			
			
			Object[][] finalArrTotCrdt=new Object[totArrCreditAmt.length][noOfMonths+2]; 
			Object[][] finalArrNet=new Object[totArrNetAmt.length][noOfMonths+3]; 	
			//logger.info("Length of arr tot"+finalArrTotCrdt.length);
			for(int k=0;k<totArrCreditAmt.length;k++){
				double totYearly=0;
				for(int l=1;l<totArrCreditAmt[k].length;l++){
					if(String.valueOf(totArrCreditAmt[k][l]).equals("null") || String.valueOf(totArrCreditAmt[k][l]).equals("")){
						totArrCreditAmt[k][l]=0;
					}
					//logger.info(">>>>>>>"+totArrCreditAmt[k][l]);
					totYearly+=Double.parseDouble(String.valueOf(totArrCreditAmt[k][l]));
					}
				
				finalArrTotCrdt[k][0]=totArrCreditAmt[k][0];
				finalArrTotCrdt[k][noOfMonths+1]=Utility.twoDecimals(totYearly);
				
				for (int i = 1; i < noOfMonths+1; i++) {
					finalArrTotCrdt[k][i]=Utility.twoDecimals(String.valueOf(totArrCreditAmt[k][i]));
				}
				
			}
			for(int k=0;k<totArrNetAmt.length;k++){
				double totNetYearly=0;
				for(int l=1;l<totArrNetAmt[k].length;l++){
					if(String.valueOf(totArrNetAmt[k][l]).equals("null") || String.valueOf(totArrNetAmt[k][l]).equals("")){
						totArrNetAmt[k][l]=0;
					}
					//logger.info(">>>>>>>"+totArrNetAmt[k][l]);
					totNetYearly+=Double.parseDouble(String.valueOf(totArrNetAmt[k][l]));
					}
				
				finalArrNet[k][0]=totArrNetAmt[k][0];
				finalArrNet[k][noOfMonths+1]=Utility.twoDecimals(totNetYearly);
				
				for (int i = 1; i < noOfMonths+1; i++) {
					finalArrNet[k][i]=Utility.twoDecimals(String.valueOf(totArrNetAmt[k][i]));
				}
				
			}
			
			
			Object[][] finalArrTax=new Object[taxArr.length][noOfMonths+2];
			for(int k=0;k<taxArr.length;k++){
				double totYearly=0;
				for(int l=1;l<taxArr[k].length;l++){
					if(String.valueOf(taxArr[k][l]).equals("null") || String.valueOf(taxArr[k][l]).equals("")){
						taxArr[k][l]=0;
					}
					totYearly+=Double.parseDouble(String.valueOf(taxArr[k][l]));
					}
				
				finalArrTax[k][0]=taxArr[k][0];
				finalArrTax[k][noOfMonths+1]=Utility.twoDecimals(totYearly);
				for (int i = 1; i < noOfMonths+1; i++) {
					finalArrTax[k][i]=Utility.twoDecimals(String.valueOf(taxArr[k][i]));
				}
				
				
			}
			Object[][] finalArrearDebit=new Object[arrearDebit.length][noOfMonths+3]; 	
			for(int k=0;k<arrearDebit.length;k++){
				double debitAmtArrYearly=0;
					for(int l=1;l<arrearDebit[k].length-1;l++){
						
						if(String.valueOf(arrearDebit[k][l]).equals("null") || String.valueOf(arrearDebit[k][l]).equals("")){
							arrearDebit[k][l]="0";
						}
								
						debitAmtArrYearly+=Double.parseDouble(String.valueOf(arrearDebit[k][l]));
						
					}
					
					finalArrearDebit[k][0]=String.valueOf(arrearDebit[k][0]);
					finalArrearDebit[k][noOfMonths+1]=Utility.twoDecimals(debitAmtArrYearly);
					finalArrearDebit[k][noOfMonths+2]=String.valueOf(arrearDebit[k][noOfMonths+1]);
					
					for (int i = 1; i < noOfMonths+1; i++) {
						finalArrearDebit[k][i]=Utility.twoDecimals(String.valueOf(arrearDebit[k][i]));
					}
					
					
						
			}
			Object[][] finalArrTotDebit=new Object[totArrDebitAmt.length][noOfMonths+2]; 	
			//logger.info("Length of arr tot"+totArrDebitAmt.length);
			for(int k=0;k<finalArrTotDebit.length;k++){
				double totYearly=0;
				for(int l=1;l<totArrDebitAmt[k].length;l++){
					if(String.valueOf(totArrDebitAmt[k][l]).equals("null") || String.valueOf(totArrDebitAmt[k][l]).equals("")){
						totArrDebitAmt[k][l]=0;
					}
					//logger.info(">>>>>>>"+finalArrTotDebit[k][l]);
					totYearly+=Double.parseDouble(String.valueOf(totArrDebitAmt[k][l]));
					}
				finalArrTotDebit[k][0]=totArrDebitAmt[k][0];
				finalArrTotDebit[k][noOfMonths+1]=Utility.twoDecimals(totYearly);
				for (int i = 1; i < noOfMonths+1; i++) {
					finalArrTotDebit[k][i]=Utility.twoDecimals(String.valueOf(totArrDebitAmt[k][i]));
				}
				
			}
			
				/*
				 * The taxCreditData is used for displaying total taxable credit amounts for a particular month.
				 */
				Object[][] taxCreditData=new Object[taxData.length][noOfMonths+2];
				for(int k=0;k<taxData.length;k++){
					double totYearly=0;
					for(int l=1;l<taxData[k].length;l++){
						if(String.valueOf(taxData[k][l]).equals("null") || String.valueOf(taxData[k][l]).equals("")){
							taxData[k][l]=0;
						}
						totYearly+=Double.parseDouble(String.valueOf(taxData[k][l]));
						}
					taxCreditData[k][0]=taxData[k][0];
					taxCreditData[k][noOfMonths+1]=Utility.twoDecimals(totYearly);
					for (int i = 1; i < noOfMonths+1; i++) {
						taxCreditData[k][i]=Utility.twoDecimals(String.valueOf(taxData[k][i]));
					}
				}
				
				String fy="";
				String ty="";
				if(!(esr.getFromYear().trim().equals("") || esr.getFromYear().trim().equals("null") || esr.getFromYear().trim().equals(null))){
					fy=esr.getFromYear();
				}
				else{
					fy=frmYear;
				}
				if(!(esr.getToYear().trim().equals("") || esr.getToYear().trim().equals("null") || esr.getToYear().trim().equals(null))){
					ty=esr.getToYear();
				}
				else{
					ty=toYear;
				}
				
				String []colNames=new String[noOfMonths+3];	
				int[] cellWidth=new int [noOfMonths+3];
				int[] alignment=new int [noOfMonths+3];
			
				colNames[0] ="Salary Head Name";
				colNames[noOfMonths+1] ="Total";
				colNames[noOfMonths+2] ="Taxable";
				cellWidth[noOfMonths+2]=25;
				alignment[0]=0;
				int aprToDecLenth=0;
				try {
					if(ledgerAprToDec != null && ledgerAprToDec.length > 0){
						aprToDecLenth=ledgerAprToDec.length;
						for (int i = 0; i < ledgerAprToDec.length; i++) {
							colNames[i + 1] = getColumnName(Integer.parseInt(String.valueOf(ledgerAprToDec[i][2])), Integer.parseInt(fy));

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if(ledgerJanToMar != null && ledgerJanToMar.length > 0){
						for (int i = 0; i < ledgerJanToMar.length; i++) {
							colNames[i + aprToDecLenth + 1] = getColumnName(Integer.parseInt(String	.valueOf(ledgerJanToMar[i][2])),
									Integer.parseInt(ty));

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				for (int i = 1; i < cellWidth.length-1; i++) {
					cellWidth[i]=20;
				}
				cellWidth[0]=30;
				for (int i = 1; i < alignment.length; i++) {
					alignment[i]=2;
				}
				
				
				Object [][]netIncomeObj=new Object[1][6];
				double netCredits =0.00;
				double netDebits =0.00;
				netIncomeObj[0][0] = "NET CREDITS :";
				netIncomeObj[0][2] = "NET DEBITS :";
				netIncomeObj[0][4] = "NET INCOME :";
				
			finalObjCredit =Utility.joinArrays(finalObjCredit, finalObjTotCredit, 1, 0)	;
			finalObjCredit =Utility.joinArrays(finalObjCredit, taxCreditData, 1, 0)	;
			finalObjCredit =Utility.joinArrays(finalObjCredit, finalObjDebit, 1, 0)	;
			finalObjCredit =Utility.joinArrays(finalObjCredit, finalObjTotDebit, 1, 0)	;
			
			//rg.tableBodyBold(colNames,finalObjCredit,cellWidth,alignment,12); 
			
			TableDataSet finalObjData = new TableDataSet();
			finalObjData.setHeader(colNames);
			finalObjData.setHeaderTable(true);
			finalObjData.setData(finalObjCredit);
			finalObjData.setCellAlignment(alignment);
			finalObjData.setCellWidth(cellWidth);
			finalObjData.setBorderDetail(0);
			finalObjData.setHeaderLines(true);
			rg.addTableToDoc(finalObjData);
			
			//rg.tableBody(finalObjTotCredit,cellWidth,alignment,12);
			//rg.tableBody(taxCreditData,cellWidth,alignment,12);
			//rg.tableBody(finalObjDebit,cellWidth,alignment,12);
			//rg.tableBody(finalObjTotDebit,cellWidth,alignment,12);
			finalObjNetSal =Utility.joinArrays(finalObjNetSal, finalObjSalDays, 1, 0);
			
			//rg.tableBody(finalObjNetSal,cellWidth,alignment,12);
			
			TableDataSet finalObjNetSalData = new TableDataSet();
			finalObjNetSalData.setData(finalObjNetSal);
			finalObjNetSalData.setCellAlignment(alignment);
			finalObjNetSalData.setCellWidth(cellWidth);
			finalObjNetSalData.setBorderDetail(0);
			finalObjNetSalData.setBlankRowsAbove(1);
			finalObjNetSalData.setBodyFontStyle(1);
			finalObjNetSalData.setBodyBGColor(new BaseColor(200,200,200));
			rg.addTableToDoc(finalObjNetSalData);
			
			//rg.tableBody(finalObjSalDays,cellWidth,alignment,12);
			Vector allwVector=null;
			try{
			allwVector = getAllowanceData(esr, rg);
			}catch(Exception e){
				e.printStackTrace();
			}
			//rg.addText("\n\nArrears:", 0,0,0);
			
			TableDataSet totalEmp = new TableDataSet();
			totalEmp.setData(new Object[][]{{"Arrears: "}});
			totalEmp.setCellAlignment(new int[]{0});
			totalEmp.setCellWidth(new int[]{100});
			totalEmp.setBorderDetail(0);
			totalEmp.setBlankRowsAbove(1);
			totalEmp.setBodyFontStyle(1);  
			totalEmp.setBlankRowsBelow(1);
			rg.addTableToDoc(totalEmp);
			
			finalArrear =Utility.joinArrays(finalArrear, finalArrTotCrdt, 1, 0)	;
			finalArrear =Utility.joinArrays(finalArrear, finalArrTax, 1, 0)	;
			finalArrear =Utility.joinArrays(finalArrear, finalArrearDebit, 1, 0)	;
			finalArrear =Utility.joinArrays(finalArrear, finalArrTotDebit, 1, 0)	;
			
			//rg.tableBodyBold(colNames,finalArrear,cellWidth,alignment,12);
			
			TableDataSet finalArrearData = new TableDataSet();
			finalArrearData.setHeader(colNames);
			finalArrearData.setHeaderTable(true);
			finalArrearData.setData(finalArrear);
			finalArrearData.setCellAlignment(alignment);
			finalArrearData.setCellWidth(cellWidth);
			finalArrearData.setBorderDetail(0);
			finalArrearData.setHeaderLines(true);
			rg.addTableToDoc(finalArrearData);
			
			//rg.tableBody(finalArrTotCrdt, cellWidth, alignment,12);
			//rg.tableBody(finalArrTax,cellWidth,alignment,12);
			//rg.tableBody(finalArrearDebit,cellWidth,alignment,12);
			//rg.tableBody(finalArrTotDebit,cellWidth,alignment,12);
			
			//rg.tableBody(finalArrNet, cellWidth, alignment,12);
			
			TableDataSet finalArrNetData = new TableDataSet();
			finalArrNetData.setData(finalArrNet);
			finalArrNetData.setCellAlignment(alignment);
			finalArrNetData.setCellWidth(cellWidth);
			finalArrNetData.setBorderDetail(0);
			finalArrNetData.setBodyBGColor(new BaseColor(200,200,200));
			finalArrNetData.setBodyFontStyle(1);
			finalArrNetData.setBlankRowsAbove(1);
			rg.addTableToDoc(finalArrNetData);
			
			
			String[] header = null;
			header = new String[4];
			header[0] = "Credit Name";
			header[1] = "Pay In Month-Year";
			//header[2] = "Pay In Year";
			header[2] = "Bonus Amount";
			header[3] = "Tax Amount";
			
			int[] bcellAlign = new int[header.length];
			int[] bcellWidth = new int[header.length];
			boolean[] bcellwrap = new boolean[header.length];
			for (int i = 0; i < header.length; i++) {
				// bcellAlign[i] = 1;
				// bcellWidth[i] = 40;
				if (i == 0) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 20;
					bcellwrap[i] = true;
				} else if (i == 1) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 20;
					bcellwrap[i] = true;
				}  else {
					bcellAlign[i] = 2;
					bcellWidth[i] = 20;
					bcellwrap[i] = true;
				}
			}
			
			
			
			String queryBonus = " SELECT HRMS_CREDIT_HEAD.CREDIT_NAME , PAY_MONTH, PAY_YEAR, SUM(NVL(EMP_BONUS_AMT,0)), SUM(NVL(BONUS_TAX_AMT,0))	 "
							+"	FROM HRMS_BONUS_HDR "
							+"	INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_BONUS_HDR.PAY_CREDIT_CODE) " +
									" INNER JOIN HRMS_BONUS_EMP ON HRMS_BONUS_EMP.BONUS_CODE= HRMS_BONUS_HDR.BONUS_CODE "
							+"	WHERE TO_NUMBER(PAY_YEAR|| CASE WHEN PAY_MONTH<10 THEN '0'||PAY_MONTH ELSE TO_CHAR(PAY_MONTH) END )>="+esr.getFromYear()+String.valueOf(esr.getFromMonth())+" AND TO_NUMBER(PAY_YEAR|| CASE WHEN PAY_MONTH<10 THEN '0'||PAY_MONTH ELSE TO_CHAR(PAY_MONTH) END ) <="+esr.getToYear()+String.valueOf(esr.getToMonth())+"  "
							+"	AND  HRMS_BONUS_EMP.EMP_ID = "+esr.getEmpId()+" " +
							"  GROUP BY HRMS_CREDIT_HEAD.CREDIT_NAME , PAY_MONTH, PAY_YEAR,HRMS_CREDIT_HEAD.CREDIT_CODE "
							+"	ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
		

			Object[][] data = getSqlModel().getSingleResult(queryBonus);
			
			Object[][] totalByColumn = new Object[1][header.length];
			
			Object[][] bonusData = new Object[data.length][header.length];
			if (data != null && data.length > 0) {
				int MAINCOUNT = 0;
				for (int i = 0; i < data.length; i++) {
					bonusData[MAINCOUNT][0] = String.valueOf(data[i][0]);
					///bonusData[i][1] = String.valueOf(data[i][1]);
					bonusData[MAINCOUNT][1] = getColumnName(
							Integer.parseInt(String.valueOf(data[i][1])),
							Integer.parseInt(String.valueOf(data[i][2])));
					
					//bonusData[i][2] = String.valueOf(data[i][2]);
					bonusData[MAINCOUNT][2] = Utility.twoDecimals(String.valueOf(data[i][3]));
					bonusData[MAINCOUNT][3] = Utility.twoDecimals(String.valueOf(data[i][4]));
					
					
					// Total Credit
					totalByColumn[0][2] = formatter.format(Double
									.parseDouble(checkNullValue(String
											.valueOf(totalByColumn[0][2])))
									+ Double
											.parseDouble(checkNullValue(String
													.valueOf(bonusData[MAINCOUNT][2]))));
					
					
					totalByColumn[0][3] = formatter.format(Double
							.parseDouble(checkNullValue(String
									.valueOf(totalByColumn[0][3])))
							+ Double
									.parseDouble(checkNullValue(String
											.valueOf(bonusData[MAINCOUNT][3]))));
					
					
					MAINCOUNT++;
				}
				
				TableDataSet bonusTable = new TableDataSet();
				bonusTable.setData(new Object[][]{{"Bonus Details: "}});
				bonusTable.setBodyFontStyle(1); 
				bonusTable.setCellAlignment(new int[]{0});
				bonusTable.setCellWidth(new int[]{100});
				bonusTable.setBorderDetail(0);
				bonusTable.setBlankRowsAbove(1);
				rg.addTableToDoc(bonusTable);
				
				TableDataSet tdstableDebit = new TableDataSet();
				tdstableDebit.setHeader(header);
				tdstableDebit.setHeaderTable(true);
				tdstableDebit.setData(bonusData);
				tdstableDebit.setCellAlignment(bcellAlign);
				tdstableDebit.setCellWidth(bcellWidth);
				///tdstableDebit.setBorderDetail(3);
				tdstableDebit.setHeaderLines(true);
			
				rg.addTableToDoc(tdstableDebit);
				
				totalByColumn[0][0] = "Total Bonus :";
				totalByColumn[0][1] = " ";
				
				TableDataSet netBonusDataSet = new TableDataSet();
				netBonusDataSet.setData(totalByColumn);
				netBonusDataSet.setCellWidth(bcellWidth);
				netBonusDataSet.setCellAlignment(bcellAlign);
				netBonusDataSet.setBodyFontStyle(1);
				netBonusDataSet.setBorderDetail(0);
				netBonusDataSet.setBorderLines(true);
				rg.addTableToDoc(netBonusDataSet);
				
				
				
			} else {
				TableDataSet noData = new TableDataSet();
		          Object[][] noDataObj = new Object[1][1];
		          noDataObj[0][0] = "Bonus Details : No records available";

		          noData.setData(noDataObj);
		          noData.setCellAlignment(new int[1]);
		          noData.setCellWidth(new int[] { 100 });
		          noData.setBlankRowsAbove(1);
		          noData.setBorder(Boolean.valueOf(false));
		          rg.addTableToDoc(noData);
			}
	
	
			
			double allwCreditAmt =0;
			double allwBonusAmt =0;
			double allwTaxAmt =0;
			try{
				if(allwVector.size() > 0){
					allwCreditAmt =Double.parseDouble((String)allwVector.get(1));
					allwTaxAmt =Double.parseDouble((String)allwVector.get(2));
				}				
			}catch (Exception e) {
				logger.error("exception while calculating allowance amount");
				e.printStackTrace();
			}	
			
			
			netCredits = Double.parseDouble(String.valueOf(finalObjTotCredit[0][finalObjTotCredit[0].length-1])) 
							  +	 allwCreditAmt  
							  +	 Double.parseDouble(String.valueOf(finalArrTotCrdt[0][finalArrTotCrdt[0].length-1])) 
							  + allwBonusAmt 
							  + Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[0][2]))) ;
			
			netDebits = Double.parseDouble(String.valueOf(finalObjTotDebit[0][finalObjTotDebit[0].length-1])) + allwTaxAmt
							  +	 Double.parseDouble(String.valueOf(finalArrTotDebit[0][finalArrTotDebit[0].length-1])) 
							  + allwBonusAmt 
							  + Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[0][3])));
			
			
			netIncomeObj[0][1] = netCredits;
			netIncomeObj[0][3] = netDebits;
			netIncomeObj[0][5] = netCredits - netDebits;
			
			//rg.tableBody(netIncomeObj, new int[]{20, 30, 20, 30, 20, 30}, new int[]{0,0,0,0,0,0},12);
			
			TableDataSet netIncomeObjData = new TableDataSet();
			netIncomeObjData.setData(netIncomeObj);
			netIncomeObjData.setCellAlignment(new int[]{0,0,0,0,0,0});
			netIncomeObjData.setCellWidth(new int[]{20, 30, 20, 30, 20, 30});
			netIncomeObjData.setBorderDetail(0);
			netIncomeObjData.setBodyBGColor(new BaseColor(200,200,200));
			netIncomeObjData.setBodyFontStyle(1);
			netIncomeObjData.setBlankRowsAbove(1);
			rg.addTableToDoc(netIncomeObjData);
			
			}
			else{
				Object [][] msg = new Object[1][1];
				msg[0][0] = "Salary not available for "+empName;
				
				//rg.tableBodyNoBorder(msg,new int []{100},new int []{1},new int []{1},new int []{12});
				 
				 TableDataSet msgData = new TableDataSet();
				 msgData.setData(msg);
				 msgData.setCellAlignment(new int[]{0});
				 msgData.setCellWidth( new int[]{100});
				 msgData.setBorderDetail(0);
				 msgData.setBlankRowsAbove(1);
				 rg.addTableToDoc(msgData);
			}
			}catch(Exception e){
				Object [][] msg = new Object[1][1];
				msg[0][0] = "Salary not available for "+empName;
				
				//rg.tableBodyNoBorder(msg,new int []{100},new int []{1},new int []{1},new int []{12});
				 
				 TableDataSet msgData = new TableDataSet();
				 msgData.setData(msg);
				 msgData.setCellAlignment(new int[]{0});
				 msgData.setCellWidth( new int[]{100});
				 msgData.setBorderDetail(0);
				 msgData.setBlankRowsAbove(1);
				 rg.addTableToDoc(msgData);
				e.printStackTrace();
			}
			return rg;
	}	
		
	public Vector getAllowanceData(EmpSalaryReg esr, org.paradyne.lib.ireportV2.ReportGenerator rg){
			String allwHeads ="SELECT DISTINCT ALLW_CREDIT_HEAD, CREDIT_NAME FROM HRMS_ALLOWANCE_HDR "
						+" INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID=HRMS_ALLOWANCE_HDR.ALLW_ID AND HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID="+esr.getEmpId()+")"
						+" INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=ALLW_CREDIT_HEAD)"
						+" WHERE ((TO_CHAR(ALLW_PROCESS_DATE,'MM') BETWEEN 4 AND 12) AND TO_CHAR(ALLW_PROCESS_DATE,'YYYY')="+esr.getFromYear()+")"
						+" OR ((TO_CHAR(ALLW_PROCESS_DATE,'MM') BETWEEN 1 AND 3) AND TO_CHAR(ALLW_PROCESS_DATE,'YYYY')="+esr.getToYear()+")";
						
			Object [][] allwHeadsObj =getSqlModel().getSingleResult(allwHeads);
			Vector allwVector=new Vector();
			try{
			if(allwHeadsObj!=null && allwHeadsObj.length>0){
				
			String monthQuery="SELECT DISTINCT TO_CHAR(ALLW_PROCESS_DATE,'MM') AS MONTH,TO_CHAR(ALLW_PROCESS_DATE,'YYYY') AS YEAR "
							+" FROM  HRMS_ALLOWANCE_HDR"
							+" INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID=HRMS_ALLOWANCE_HDR.ALLW_ID AND HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID="+esr.getEmpId()+")"
							+" WHERE ((TO_CHAR(ALLW_PROCESS_DATE,'MM') BETWEEN 4 AND 12) AND TO_CHAR(ALLW_PROCESS_DATE,'YYYY')="+esr.getFromYear()+")"
							+" OR ((TO_CHAR(ALLW_PROCESS_DATE,'MM') BETWEEN 1 AND 3) AND TO_CHAR(ALLW_PROCESS_DATE,'YYYY')="+esr.getToYear()+")"
							+" ORDER BY MONTH,YEAR";
				
			logger.info("empId=="+esr.getEmpId());
			Object [][]monthObj =getSqlModel().getSingleResult(monthQuery);
			
			Object [][]finalAllwObj =new Object[allwHeadsObj.length][monthObj.length+2];
			Object [][]taxData =new Object[1][monthObj.length+2];
			Object [][]totMonthlyAllw =new Object[1][monthObj.length+2];
			Object [][]netAllw =new Object[1][monthObj.length+2];
			taxData [0][0] ="TAX AMOUNT";
			totMonthlyAllw [0][0] ="TOTAL ALLOWANCE";
			netAllw [0][0] ="NET ALLOWANCE";
			
			for (int i = 1; i < taxData[0].length; i++) {
				taxData[0][i] ="0";
				totMonthlyAllw[0][i]="0";
				netAllw[0][i]="0";
			}
			
			for (int i = 0; i < finalAllwObj.length; i++) {
				double totAll=0.00;
				double totYearAll=0.00;
				double totTax=0.00;
				double totNetAll=0.00;
				
				for (int j = 0; j < finalAllwObj[i].length; j++) {
					if(j==0){		
						finalAllwObj[i][j]= allwHeadsObj[i][1];				// set allowance head name
					}else{
						if(j==finalAllwObj[i].length-1){
							finalAllwObj[i][j] =totAll;					// adding the allowance amt to total allowance
							taxData[0][j] =totTax;						// adding the tax amt to total tax
							netAllw[0][j] = totNetAll;					// adding the net amt to total net
							totMonthlyAllw[0][j]=totYearAll;
						}else{
							String monthStr=String.valueOf(monthObj[j-1][0]);
							String yearStr=String.valueOf(monthObj[j-1][1]);
							
							String allwQuery="SELECT DISTINCT ALLW_CREDIT_HEAD,SUM(ALLW_AMOUNT_FINAL),SUM(ALLW_TAX_AMT) "
									+" FROM  HRMS_ALLOWANCE_HDR"
									+" INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=ALLW_CREDIT_HEAD) "
									+" INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID=HRMS_ALLOWANCE_HDR.ALLW_ID AND HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID="+esr.getEmpId()+") "
									+" WHERE (TO_CHAR(ALLW_PROCESS_DATE,'MM-YYYY')='"+monthStr+"-"+yearStr+"') AND ALLW_CREDIT_HEAD="+allwHeadsObj[i][0]+"   GROUP BY ALLW_CREDIT_HEAD";
							
							Object [][]allwObj =getSqlModel().getSingleResult(allwQuery);
							
							try {
								
								finalAllwObj[i][j] = String.valueOf(allwObj[0][1]);				// allowance amount
								taxData[0][j] = Double.parseDouble(String.valueOf(allwObj[0][2]))+ Double.parseDouble(String.valueOf(taxData[0][j])); 		// tax amount
								totMonthlyAllw[0][j]= Double.parseDouble(String.valueOf(finalAllwObj[i][j]))+ Double.parseDouble(String.valueOf(totMonthlyAllw[0][j])); 	// total allowance
								netAllw[0][j] =Double.parseDouble(String.valueOf(totMonthlyAllw[0][j]))- Double.parseDouble(String.valueOf(taxData[0][j])); 		// net allowance (total allowance-tax amount)
								
								
							} catch (Exception e) {
								finalAllwObj[i][j] ="0";
								netAllw[0][j] =Double.parseDouble(String.valueOf(totMonthlyAllw[0][j]))- Double.parseDouble(String.valueOf(taxData[0][j])); 		// net allowance (total allowance-tax amount)
							}
						}
						
						totAll +=Double.parseDouble(String.valueOf(finalAllwObj[i][j]));
						totTax +=Double.parseDouble(String.valueOf(taxData[0][j]));
						totNetAll +=Double.parseDouble(String.valueOf(netAllw[0][j]));
						totYearAll +=Double.parseDouble(String.valueOf(totMonthlyAllw[0][j]));
					}
				}
			}
			
			
			String colName[]=new String[finalAllwObj[0].length];
			colName[0]="Allowance Name";
			colName[colName.length-1]="Total";
			
			for (int i = 1; i < colName.length-1; i++) {
				colName[i]=getColumnName(Integer.parseInt(String.valueOf(monthObj[i-1][0])), Integer.parseInt(String.valueOf(monthObj[i-1][1])));
			}
			int colwidth[]=new int[finalAllwObj[0].length];
			int align[]=new int[finalAllwObj[0].length];
			
			colwidth[0]=40;
			align[0]=0;
			for (int i = 1; i < align.length; i++) {
				colwidth[i]=30;
				align[i]=2;
			}
			
			//rg.addText("\n\nAllowance Details:", 0,0,0);
			
			TableDataSet allowanceTable = new TableDataSet();
			allowanceTable.setData(new Object[][]{{"Allowance Details: "}});
			allowanceTable.setBodyFontStyle(1); 
			allowanceTable.setCellAlignment(new int[]{0});
			allowanceTable.setCellWidth(new int[]{100});
			allowanceTable.setBorderDetail(0);
			allowanceTable.setBlankRowsAbove(1);
			rg.addTableToDoc(allowanceTable);
			
			finalAllwObj = Utility.joinArrays(finalAllwObj, totMonthlyAllw, 1, 0);
			finalAllwObj = Utility.joinArrays(finalAllwObj, taxData, 1, 0);
			
			//rg.tableBodyBold(colName,finalAllwObj, colwidth, align,12);
			
			TableDataSet finalAllwObjData = new TableDataSet();
			finalAllwObjData.setHeader(colName);
			finalAllwObjData.setHeaderTable(true);
			finalAllwObjData.setData(finalAllwObj);
			finalAllwObjData.setCellAlignment(align);
			finalAllwObjData.setCellWidth(colwidth);
			finalAllwObjData.setBorderDetail(0);
			finalAllwObjData.setHeaderLines(true);
			rg.addTableToDoc(finalAllwObjData);
			
			//rg.tableBody(totMonthlyAllw, colwidth, align,12);
			//rg.tableBody(taxData, colwidth, align,12);
			
			//rg.tableBody(netAllw, colwidth, align,12);
			
			
			TableDataSet netAllwData = new TableDataSet();
			netAllwData.setData(netAllw);
			netAllwData.setCellAlignment(align);
			netAllwData.setCellWidth(colwidth);
			netAllwData.setBorderDetail(0);
			netAllwData.setBlankRowsAbove(1);
			netAllwData.setBodyBGColor(new BaseColor(200,200,200));
			netAllwData.setBodyFontStyle(1);
			rg.addTableToDoc(netAllwData);
			
			allwVector.add(rg);
			//allwVector.add(netAllw);
			allwVector.add(String.valueOf(totMonthlyAllw[0][totMonthlyAllw[0].length-1]));
			allwVector.add(String.valueOf(taxData[0][taxData[0].length-1]));
		}
			}catch(Exception e){
				logger.info("Exception while calculating allowance"+e);
				e.printStackTrace();
			}
			
			return allwVector;
			
		}
		public String getSalDaysSum(String ledgerCode,String empCode,EmpSalaryReg bean){
			
			
			String totSalDaysFrmQuery = "SELECT NVL(SUM(SAL_DAYS),0),NVL(SUM(TO_CHAR(SAL_HRS,'HH24')),0),NVL(SUM(TO_CHAR(SAL_HRS,'MI')),0) FROM HRMS_SALARY_"+bean.getFromYear() 
					+" WHERE SAL_LEDGER_CODE IN("+ledgerCode+") AND EMP_ID ="+empCode;
			
			Object totSalDaysFrm[][]=getSqlModel().getSingleResult(totSalDaysFrmQuery) ;
			
			
			
			double days=0, hours=0, min =0;
			
			try{
				days =Double.parseDouble(String.valueOf(totSalDaysFrm[0][0]));
				hours =Double.parseDouble(String.valueOf(totSalDaysFrm[0][1]));  //+Double.parseDouble(String.valueOf(totSalDaysTo[0][1]));
				min =Double.parseDouble(String.valueOf(totSalDaysFrm[0][2]));   //+Double.parseDouble(String.valueOf(totSalDaysTo[0][2]));
			}catch(Exception e){
				
			}
			try{
			if(!bean.getFromYear().equals(bean.getToYear())){
				String totSalDaysToQuery = "SELECT NVL(SUM(SAL_DAYS),0),NVL(SUM(TO_CHAR(SAL_HRS,'HH24')),0),NVL(SUM(TO_CHAR(SAL_HRS,'MI')),0) FROM HRMS_SALARY_"+bean.getToYear() 
				+" WHERE SAL_LEDGER_CODE IN("+ledgerCode+") AND EMP_ID ="+empCode;

				Object totSalDaysTo[][]=getSqlModel().getSingleResult(totSalDaysToQuery) ;
				days +=Double.parseDouble(String.valueOf(totSalDaysTo[0][0]));
				hours +=Double.parseDouble(String.valueOf(totSalDaysTo[0][1]));
				min +=Double.parseDouble(String.valueOf(totSalDaysTo[0][2]));
			}
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			hours =hours+(min/60);
			min = min%60;
			days = days+(hours/24);
			hours = hours %24;
			
			String salDaysSum = Utility.getViewDays(days+"d:"+hours+"h:"+min+"m");
			
			return salDaysSum;
		
		}
}

