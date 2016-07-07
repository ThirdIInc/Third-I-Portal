package org.paradyne.model.payroll.pf;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.pf.PfForm98;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * 
 * @author REEBA JOSEPH
 * 01 NOVEMBER 2010
 *
 */

public class PfForm98Model extends ModelBase {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(PfForm98Model.class);
	
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	/**
	 * Method to generate report in PDF format
	 * @param form98
	 * @param response
	 */
	public void getReport(PfForm98 form98, HttpServletResponse response) {
		ReportDataSet rds = new ReportDataSet();
		rds.setFileName("PF Form 98");
		rds.setReportName("PF Form 98");
		rds.setReportType("PDF");
		
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
				rds);
		
		//DIVISION NAME
        TableDataSet divisionTable = new TableDataSet();
        divisionTable.setData(new Object[][]{{form98.getDivName()}});
        divisionTable.setCellAlignment(new int[] { 1 });
        divisionTable.setCellWidth(new int[] { 100 });
        divisionTable.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(
				0, 0, 0));
		rg.addTableToDoc(divisionTable);
		
		//REPORT NAME
        TableDataSet titleName = new TableDataSet();
		titleName.setData(new Object[][]{{"Form 98-PF TRUST LEDGER"}});
		titleName.setCellAlignment(new int[] { 1 });
		titleName.setCellWidth(new int[] { 100 });
		titleName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(
				0, 0, 0));
		rg.addTableToDoc(titleName);
		
		//EMPLOYEE DETAILS
		try {
			Object[][] empDetails = new Object[3][4];
			empDetails[0][0] = "Employee: ";
			empDetails[0][1] = form98.getEmpName();
			empDetails[0][2] = "";
			empDetails[0][3] = "";
			empDetails[1][0] = "Division: ";
			empDetails[1][1] = form98.getDivName();
			empDetails[1][2] = "Branch: ";
			empDetails[1][3] = form98.getBranchName();
			empDetails[2][0] = "Designation: ";
			empDetails[2][1] = form98.getDesgName();
			empDetails[2][2] = "PF NO: ";
			empDetails[2][3] = form98.getEmpPfNo();
			TableDataSet empTable = new TableDataSet();
			empTable.setData(empDetails);
			empTable.setCellAlignment(new int[] {0, 0, 0, 0});
			empTable.setCellWidth(new int[] {  25, 25, 25, 25  });
			empTable.setBlankRowsAbove(1);
			empTable.setBlankRowsBelow(1);
			empTable.setBorder(false);
			rg.addTableToDoc(empTable);
		} catch (Exception e) {
			e.printStackTrace();
		}//end of try-catch block
		
		//PF SUBSCRIPTION DETAILS
		TableDataSet subTitleName = new TableDataSet();
		subTitleName.setData(new Object[][]{{"PF Subscription Details"}});
		subTitleName.setCellAlignment(new int[] { 0 });
		subTitleName.setCellWidth(new int[] { 100 });
		subTitleName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(
				0, 0, 0));
		subTitleName.setBorder(false);
		rg.addTableToDoc(subTitleName);
		
		//CALCULATE OPENING BALANCE
		String openingBalance = getOpeningBalance(form98, String.valueOf(form98.getEmpCode()));
		
		//SET OPENING BALNCE
		TableDataSet opBal = new TableDataSet();
		opBal.setData(new Object[][]{{"Opening Balance: "+openingBalance}});
		opBal.setCellAlignment(new int[] { 2 });
		opBal.setCellWidth(new int[] { 100 });
		opBal.setBorder(false);
		rg.addTableToDoc(opBal);
		
		//HEADER FOR SUBSCRIPTION DETAILS
		String[] header = new String[8];
		header[0] = "SR.NO";
		header[1] = "MONTH";
		header[2] = "SUBS.";
		header[3] = "REFUND";
		header[4] = "MPL.CONT.";
		header[5] = "LOAN";
		header[6] = "REPAY";
		header[7] = "PROG.";
		
		double pfProgAmt = 0.0;
		double totProgAmt = 0.0;
		double totPfSub = 0.0;
		double totPfRefund = 0.0;
		double totPfLoan = 0.0;
		double totMpCont = 0.0;
		double totPfRepay = 0.0;
		
		//RETRIEVE INTEREST RATE
		String interestRate = "";
		String intQuery="SELECT PFT_INTEREST FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)" 
			+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)";
		Object [][]pfInterestRate = getSqlModel().getSingleResult(intQuery);
		try {
			if (pfInterestRate != null && pfInterestRate.length > 0) {
				interestRate = formatter.format(Double.parseDouble(String.valueOf(pfInterestRate[0][0])));
			}
		} catch (Exception e) {
			interestRate = formatter.format(0);
		}
		
		int fromMonth = 4;
		int toMonth =Integer.parseInt(form98.getToMonth());
		int length= 0;
		if(toMonth <4){
			length = 9 + toMonth;
		}else{
			length= toMonth - 3;
		}//end of if-else block
		
		try{
			Object[][] pfObj = new Object[length][8];
		
			//SET SUBSCRIPTION TABLE DTLS FOR EACH MONTH
			for (int i = 0; i < length; i++) {
				pfObj[i][0] = (i + 1);//SR NO
				int monthInt = (fromMonth+i) % 12;
				String toYearString =form98.getToYear();
				String frmYearString =form98.getFromYear();
				String year="";
				if(monthInt==0){
					monthInt =12;
					
				}
				if(monthInt<4){
					year= toYearString;
				
				}else{
					year= frmYearString;
				}
				
				//CALCULATE PF SUBSCRIPTION AMOUNTS
				Object monthData[][]= getSalData(String.valueOf(monthInt),year,form98.getEmpCode());
				
				if(monthInt==1)
					pfObj[i][1] = Utility.month(monthInt).toUpperCase()+"'"+toYearString.substring(toYearString.length()-2);//MONTH
				else if(monthInt==4){
					pfObj[i][1] = Utility.month(monthInt).toUpperCase()+"'"+frmYearString.substring(frmYearString.length()-2);//MONTH
				}else 
					pfObj[i][1] = Utility.month(monthInt).toUpperCase();//MONTH
				
				pfObj[i][2] = formatter.format(Double.parseDouble(checkNullToZero(String.valueOf(monthData[0][0]))));//SUBS
				totPfSub += Double.parseDouble(String.valueOf(pfObj[i][2]));
				pfObj[i][3] = formatter.format(Double.parseDouble(checkNullToZero(String.valueOf(monthData[0][1]))));//REFUND
				totPfRefund += Double.parseDouble(String.valueOf(pfObj[i][3]));
				pfObj[i][4] = "0";//MPL.CONT.
				totMpCont+= Double.parseDouble(String.valueOf(pfObj[i][4]));
				pfObj[i][5] = formatter.format(Double.parseDouble(checkNullToZero(String.valueOf(monthData[0][2]))));//LOAN
				totPfLoan += Double.parseDouble(String.valueOf(pfObj[i][5]));
				pfObj[i][6] = "0";//REPAY
				totPfRepay += Double.parseDouble(String.valueOf(pfObj[i][6]));
				pfProgAmt = Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(pfObj[i][2]))+Double.parseDouble(String.valueOf(pfObj[i][3]))
						+Double.parseDouble(String.valueOf(pfObj[i][4]))+
									pfProgAmt - Double.parseDouble(String.valueOf(pfObj[i][5]))));
				totProgAmt += pfProgAmt;
				pfObj[i][7] = String.valueOf(pfProgAmt);//PROG
				
			}//end of loop
			//SET PF SUBSCRIPTION DTL TABLE
			TableDataSet pfSubs = new TableDataSet();
			pfSubs.setHeader(header);
			pfSubs.setData(pfObj);
			pfSubs.setCellAlignment(new int[] {1,0,2,2,2,2,2,2});
			pfSubs.setCellWidth(new int[] {5,15,10,10,10,10,10,10});
			pfSubs.setHeaderFontDetails(Font.HELVETICA, 8,
					Font.BOLD, new Color(0, 0, 0));
			pfSubs.setHeaderBGColor(new Color(200, 200, 200));
			pfSubs.setBorder(true);
			rg.addTableToDoc(pfSubs);
			
			//SET PF SUBSCRIPTION TOTALS
			Object[][] totalObj = new Object[1][8];
			totalObj[0][0] = "TOTAL";
			totalObj[0][1] = "";
			totalObj[0][2] = formatter.format(totPfSub);//setTotPfSub
			totalObj[0][3] = formatter.format(totPfRefund);//setTotPfRefund
			totalObj[0][4] = formatter.format(totMpCont);//setTotPfMPCont
			totalObj[0][5] = formatter.format(totPfLoan);//setTotPfLoan
			totalObj[0][6] = formatter.format(totPfRepay);//setTotPfRepay
			//totalObj[0][7] = formatter.format(Math.round((totProgAmt/length)));//setTotPfProgActual
			totalObj[0][7] = formatter.format(totProgAmt)+" / "+length+" = "+Math.round((totProgAmt/length));//setTotPfProg
			
			TableDataSet pfSubsTotal = new TableDataSet();
			pfSubsTotal.setData(totalObj);
			pfSubsTotal.setCellAlignment(new int[] {0,0,2,2,2,2,2,2});
			pfSubsTotal.setCellWidth(new int[] {5,15,10,10,10,10,10,10});
			pfSubsTotal.setBorder(true);
			pfSubsTotal.setBlankRowsBelow(1);
			rg.addTableToDoc(pfSubsTotal);
			
			//SET INTERSET CALCULATION TABLE
			TableDataSet finalTitleName = new TableDataSet();
			finalTitleName.setData(new Object[][]{{"Interest Calculation"}});
			finalTitleName.setCellAlignment(new int[] { 0 });
			finalTitleName.setCellWidth(new int[] { 100 });
			finalTitleName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(
					0, 0, 0));
			finalTitleName.setBorder(false);
			rg.addTableToDoc(finalTitleName);
			
			Object[][] finalObj = new Object[4][3];
			finalObj[0][0] = "Total Interest Amount: ";
			finalObj[0][1] = "[("+ openingBalance+"+"+Math.round(totProgAmt/length)+")*("+ interestRate+"/100) ]";
			finalObj[0][2] = formatter.format((Double.parseDouble(openingBalance)+ Double.parseDouble(formatter.format(Math.round((totProgAmt/length)))))*
					(Double.parseDouble(interestRate)/100));
			finalObj[1][0] = "Total Deposit: ";
			finalObj[1][1] = "("+ formatter.format(totPfSub)  +"+"+ formatter.format(totPfRefund)  +"+"+ formatter.format(totMpCont)+")"; 
			finalObj[1][2] = formatter.format(Double.parseDouble(formatter.format(totPfSub))+Double.parseDouble(formatter.format(totPfRefund))
					+Double.parseDouble(formatter.format(totMpCont)));
			finalObj[2][0] = "Total Withdrawal: ";
			finalObj[2][1] = "("+ formatter.format(totPfLoan) +")"; 
			finalObj[2][2] = formatter.format(totPfLoan);
			finalObj[3][0] = "Closing Balance: ";
			finalObj[3][1] = "("+ openingBalance  +"+"+ finalObj[1][2]  +"+"+ finalObj[0][2] +"-"+ finalObj[2][2] +")"; 
			finalObj[3][2] = formatter.format(Double.parseDouble(openingBalance)+Double.parseDouble(String.valueOf(finalObj[1][2]))+ Double.parseDouble(String.valueOf(finalObj[0][2]))
					- Double.parseDouble(String.valueOf(finalObj[2][2])));
	
			TableDataSet finalTable = new TableDataSet();
			finalTable.setData(finalObj);
			finalTable.setCellAlignment(new int[] { 0, 10, 2});
			finalTable.setCellWidth(new int[] { 30, 30, 30});
			finalTable.setBorder(false);
			rg.addTableToDoc(finalTable);
		
		}catch(Exception e){
			e.printStackTrace();
		}//end of try-catch block
		rg.process();
		rg.createReport(response);
	}//end of method getReport

	/**
	 * Method to retrieve and calculate PF amounts
	 * @param month
	 * @param year
	 * @param empId
	 * @return
	 */
	private Object[][] getSalData(String month, String year,String empId) {
		Object [][] returnData = new Object[1][3];
		
		String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER " 
					+"LEFT JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
					+"WHERE LEDGER_MONTH="+String.valueOf(month)+" AND LEDGER_YEAR="+String.valueOf(year)+" AND LEDGER_STATUS = 'SAL_FINAL' AND EMP_ID ="+empId;
		
		
		Object ledgerCode[][]= getSqlModel().getSingleResult(ledgerQuery);
		if(Integer.parseInt(month)<10){
			month ="0"+month;
		}//end of if
		try{
			if(ledgerCode.length > 0 && ledgerCode != null){
				
				 //CALCULATE PF SUBS
				String pfSubQuery ="SELECT NVL(SAL_AMOUNT,0),SAL_DEBIT_CODE FROM HRMS_DEBIT_HEAD " 
					+" INNER JOIN HRMS_SAL_DEBITS_"+year+" ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="+empId+" AND SAL_LEDGER_CODE IN("+ledgerQuery+")) "
					+" WHERE HRMS_DEBIT_HEAD.DEBIT_CODE=(SELECT PFT_DEBIT_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
					+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE )) ";
				
				Object [][]pfSubAmt =getSqlModel().getSingleResult(pfSubQuery);
				try{
					returnData [0][0] =String.valueOf(pfSubAmt[0][0]);
				}catch (Exception pfSubEx) {
					returnData [0][0] = "0";
				}//end of try-catch block
				
				// CALCULATE PF LOAN REFUND AMT
				String pfLoanRefQuery ="SELECT NVL(SAL_AMOUNT,0),SAL_DEBIT_CODE FROM HRMS_DEBIT_HEAD " 
					+" INNER JOIN HRMS_SAL_DEBITS_"+year+" ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID="+empId+" AND SAL_LEDGER_CODE IN("+ledgerQuery+")) "
					+" WHERE HRMS_DEBIT_HEAD.DEBIT_CODE=(SELECT LOAN_DEBIT_CODE FROM HRMS_LOAN_MASTER WHERE LOAN_CODE=(SELECT PFT_LOAN_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
					+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE ))) ";
				
				Object [][]pfLoanRefAmt =getSqlModel().getSingleResult(pfLoanRefQuery);
				double loanRefAmt=0;
				try {
					loanRefAmt = Double.parseDouble(String.valueOf(pfLoanRefAmt[0][0]));
				} catch (Exception e) {
					loanRefAmt =0;
				}//end of try-catch block
				// CALCULATE PF PRELOAN AMT
				String pfPreLoanAmtQuery ="SELECT NVL(SUM(LOAN_PREPAY_AMOUNT),0) FROM HRMS_LOAN_CLOSURE  "
					+" INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE=HRMS_LOAN_CLOSURE.LOAN_APPL_CODE)"
					+" INNER JOIN HRMS_PFTRUST_CONF ON(HRMS_PFTRUST_CONF.PFT_LOAN_CODE=HRMS_LOAN_APPLICATION.LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
					+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE ) )"
					+" WHERE LOAN_EMP_ID="+empId+" AND TO_CHAR(LOAN_PREPAY_DATE,'MM-YYYY')='"+month+"-"+year+"'";
	
				Object [][]pfLoanPreAmt =getSqlModel().getSingleResult(pfPreLoanAmtQuery);
				double preLoanAmt=0;
				try {
					preLoanAmt = Double.parseDouble(String.valueOf(pfLoanPreAmt[0][0]));
				} catch (Exception e) {
					preLoanAmt =0;
				}//end of try-catch block
				try{
					returnData [0][1] =String.valueOf(loanRefAmt+preLoanAmt);
				}catch (Exception pfloanEx) {
					returnData [0][1] = "0";
				}
				// CALCULATE PF LOAN REFUND AMT
				
			}else{
				
				String pfPreLoanAmtQuery ="SELECT NVL(SUM(LOAN_PREPAY_AMOUNT),0) FROM HRMS_LOAN_CLOSURE  "
					+" INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE=HRMS_LOAN_CLOSURE.LOAN_APPL_CODE)"
					+" INNER JOIN HRMS_PFTRUST_CONF ON(HRMS_PFTRUST_CONF.PFT_LOAN_CODE=HRMS_LOAN_APPLICATION.LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
					+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE ) )"
					+" WHERE LOAN_EMP_ID="+empId+" AND TO_CHAR(LOAN_PREPAY_DATE,'MM-YYYY')='"+month+"-"+year+"'";
	
				Object [][]pfLoanPreAmt =getSqlModel().getSingleResult(pfPreLoanAmtQuery);
				double preLoanAmt=0;
				try {
					preLoanAmt = Double.parseDouble(String.valueOf(pfLoanPreAmt[0][0]));
				} catch (Exception e) {
					preLoanAmt =0;
				}//end of try-catch block
				returnData [0][0] ="0";					// PF SUB AMT
				returnData [0][1] =preLoanAmt;			// PF LOAN REFUND 
									
			}//end of if-else block
		}catch (Exception e) {
			returnData [0][0] ="0";					// PF SUB AMT
			returnData [0][1] ="0";					// PF LOAN REFUND 
			
		}//end of outer try-catch block
		
		String pfLoanAmtQuery ="SELECT SUM(LOAN_AMOUNT),EMP_ID FROM HRMS_LOAN_SUPPL_APPL "
			+" WHERE EMP_ID="+empId+" AND TO_CHAR(LOAN_DATE,'MM-YYYY')='"+month+"-"+year+"' GROUP BY EMP_ID";

		Object [][]pfLoanAmt =getSqlModel().getSingleResult(pfLoanAmtQuery);
		
		
		try {
			returnData[0][2] = String.valueOf(pfLoanAmt[0][0]);
		} catch (Exception loanEx) {
			returnData[0][2] = "0";
		}
		return returnData;
	}//end of method getSalData

	/**
	 * Calculate opening balance
	 * @param form98
	 * @param empId
	 * @return
	 */
	private String getOpeningBalance(PfForm98 form98, String empId) {
		String openingBalance ="0.0";
		Object [][]opnBalanceObj =null;
		String opnBalanceQuery="";
		
		opnBalanceQuery="SELECT NVL(PF_OPENING_BAL,'0') FROM HRMS_PF_LEDGER WHERE PF_EMPID="+empId+" AND "
		+" PF_FROM_YEAR ="+form98.getFromYear();

		opnBalanceObj =getSqlModel().getSingleResult(opnBalanceQuery);

		try {
			if (opnBalanceObj != null && opnBalanceObj.length > 0) {
				
				openingBalance =(formatter.format(Double.parseDouble(String.valueOf(opnBalanceObj[0][0]))));
				
			}else{
				opnBalanceQuery="SELECT NVL(PF_CLOSING_BAL,'0') FROM HRMS_PF_LEDGER WHERE PF_EMPID="+empId+" AND "
				+" PF_FROM_YEAR ="+(Integer.parseInt(form98.getFromYear())-1);
				
				opnBalanceObj =getSqlModel().getSingleResult(opnBalanceQuery);
				
				openingBalance =(formatter.format(Double.parseDouble(String.valueOf(opnBalanceObj[0][0]))));
			}//end of if-else block
		} catch (Exception e) {
			
			opnBalanceQuery="SELECT NVL(PF_CLOSING_BAL,'0') FROM HRMS_PF_LEDGER WHERE PF_EMPID="+empId+" AND "
			+" PF_FROM_YEAR ="+(Integer.parseInt(form98.getFromYear())-1);
			
			opnBalanceObj =getSqlModel().getSingleResult(opnBalanceQuery);
			try{
				
			openingBalance =(formatter.format(Double.parseDouble(String.valueOf(opnBalanceObj[0][0]))));
			
			}catch (Exception ex) {
				// TODO: handle exception
			}//end of try-catch block
		}//end of outer try-catch block
		
		return openingBalance;
	}//end of method getOpeningBalance
	
	/**
	 * Set null values to zero
	 * @param result
	 * @return
	 */
	public String checkNullToZero(String result){
		if(result ==null ||result.equals("null")){
			return "0";
		}//end of if
		else{
			return result;
		}//end of else
	}//end of method checkNullToZero

}
