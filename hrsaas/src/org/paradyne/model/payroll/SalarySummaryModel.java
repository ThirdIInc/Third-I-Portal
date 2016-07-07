package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;


import org.paradyne.bean.payroll.SalarySummary;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author  Venkatesh
 * Date:04-07-2009
 */
public class SalarySummaryModel extends ModelBase {
	NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SalarySummaryModel.class); 
	
	public void getReport(SalarySummary bean,HttpServletResponse response){
		
		String reportTitle = "\n Salary Summary Of "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear();
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportTitle,"A3");
		rg.showPage=false;
		/*String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
		Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
		//rg.setFName("Appraisal Configuration Report");
		
		rg.addText("Date: " + dateData[0][0], 0, 2, 0);*/
		rg.addText("", 0, 2, 0);
		
		rg.addText("\n", 0, 0, 0);
		rg.addText("\n", 0, 0, 0);
		rg.addText("\n\n\n\n\n", 0, 0, 0);
		rg.addFormatedText(bean.getDivName(), 5, 0, 1, 0, 25);//(bean.getDivName(), 0, 1, 0, 12);
		rg.addFormatedText(" ", 5, 0, 1, 0, 25);//(bean.getDivName(), 0, 1, 0, 12);
		//rg.addText("\n", 0, 0, 0);
		String summaryTitle="";
		if(!bean.getBrnName().equals("")){
			summaryTitle =bean.getBrnName();
		}else if(!bean.getDeptName().equals("")){
			summaryTitle =bean.getDeptName();
		}else if(!bean.getDesgName().equals("")){
			summaryTitle =bean.getDesgName();
		} 
		if(!summaryTitle.equals("")){
		//summaryTitle=summaryTitle.substring(summaryTitle.indexOf(".")+1,summaryTitle.length()-1);
		//rg.addText("\n", 0, 0, 0);
		//rg.addText("\n", 0, 0, 0);
		///rg.addText("\n", 0, 0, 0);
		//rg.addText("\n", 0, 0, 0);
		//rg.addText("\n", 0, 0, 0);
		rg.addText("\n", 0, 0, 0);
		rg.addFormatedTextPaybill(summaryTitle, 1, 5, 1, 0, 25);
		}
		rg.addFormatedText("\n", 5, 0, 1, 0, 25);
		rg.addText("\n\n\n\n\n\n\n\n\n\n", 0, 0, 0);
		//rg.addTextBold("PAYMENT BILL", 1, 1, 0, 25);
		String billTitle="REGULAR PAYMENT BILL";
		rg.addFormatedText(billTitle, 6, 0, 1, 0, 25);
		rg.addText("\n", 0, 0, 0);
		rg.addText("\n", 0, 0, 0);
		rg.addText("\n", 0, 0, 0);
		
		//rg.addText("FOR THE MONTH OF :"+Utility.month(Integer.parseInt(bean.getMonth()))+"'"+bean.getYear(), 0, 1, 0, 10);
		
		rg.addFormatedText("FOR THE MONTH OF :                "+Utility.month(Integer.parseInt(bean.getMonth()))+"' "+bean.getYear(), 1, 0, 1, 0, 20);
		
		rg.addText("\n\n\n\n\n", 0, 0, 0);
		rg.addText("\n", 0, 0, 0);
		rg.addFormatedText("Dearness Allowance 65%", 0, 0, 1, 0, 15);
		rg.pageBreak();
		if(bean.getBranchFlag().equals("true")){
			if(bean.getDeptFlag().equals("true")){
				rg = getBranchDeptwiseReport(rg,bean);
			}else{
				rg = getBranchwiseReport(rg,bean);
			}
		}else if (bean.getDeptFlag().equals("true")){
			rg = getDeptwiseReport(rg,bean);
		}else{
			rg = getGeneralReport(rg, bean);
		}
		
		
		rg.createReport(response);
	}
	
	
	public ReportGenerator getGeneralReport(ReportGenerator rg, SalarySummary bean){
		
		logger.info("inside getGeneralRport");
		String reportTitle = "\n Salary Summary Of "+bean.getDivName()+" For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear();
		
		/*String creditHeadQuery="SELECT CREDIT_ABBR,0,CREDIT_CODE FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";*/
		String creditHeadQuery="SELECT distinct PARENT_TABLE.CREDIT_ABBR,0,PARENT_TABLE.CREDIT_CODE  FROM HRMS_CREDIT_HEAD "
						+" INNER JOIN HRMS_CREDIT_HEAD PARENT_TABLE ON (HRMS_CREDIT_HEAD.PARENT_CREDIT=PARENT_TABLE.CREDIT_CODE)"
						+" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' ORDER BY PARENT_TABLE.CREDIT_CODE";
		
		Object creditCode[][]=getSqlModel().getSingleResult(creditHeadQuery);
		
		//String debitQuery="SELECT DEBIT_ABBR, 0, DEBIT_CODE FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_CODE";
		String debitQuery="SELECT DISTINCT PARENT_TABLE.DEBIT_ABBR,0,PARENT_TABLE.DEBIT_CODE  FROM HRMS_DEBIT_HEAD "
						+" INNER JOIN HRMS_DEBIT_HEAD PARENT_TABLE ON (HRMS_DEBIT_HEAD.PARENT_DEBIT=PARENT_TABLE.DEBIT_CODE)"
						+" WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y' ORDER BY PARENT_TABLE.DEBIT_CODE";
		
		Object debitCode[][]=getSqlModel().getSingleResult(debitQuery);
		
		String ledgerCode = getLedgerCode(bean);
		String arrearCode = getArrearCode(bean,"ADD");
		
		if(!(ledgerCode.equals("") || ledgerCode.equals("null") || ledgerCode.equals("0"))){
		Object creditData [][] = getCreditSalAmt(ledgerCode, arrearCode,creditCode, bean,"","") ;
		double grossSalary =0.0;
		double totDeduction =0.0;
		double totSalary =0.0;
		
		Object debitData [][] = getDebitSalAmt(ledgerCode,arrearCode, debitCode, bean,"","") ;
		
		String [] heading={"SALARY","DEDUCTION"};
		if(creditData !=null && creditData.length>0){
			for (int i = 0; i < creditData.length; i++) {
				creditData[i][1]=formatter.format(Double.parseDouble(String.valueOf(creditData[i][1])));
			}
		}
		if(debitData !=null && debitData.length>0){
			for (int i = 0; i < debitData.length; i++) {
				debitData[i][1]=formatter.format(Double.parseDouble(String.valueOf(debitData[i][1])));
			}
		}
		//----
		if(creditData!=null && creditData.length>0){
			Vector<Object[]> creditVector=new Vector<Object[]>();
			for (int i = 0; i < creditData.length; i++) {
				if(Double.parseDouble(String.valueOf(creditData[i][1]))>0){
					creditVector.add(creditData[i]);
				}
			}
			creditData =new Object[creditVector.size()][2];
			for (int i = 0; i < creditData.length; i++) {
				creditData[i]=(Object[])creditVector.get(i);
			}
		}
		//-----
		
		if(debitData!=null && debitData.length>0){
			Vector<Object[]> debitVector=new Vector<Object[]>();
			for (int i = 0; i < debitData.length; i++) {
				if(Double.parseDouble(String.valueOf(debitData[i][1]))>0){
					debitVector.add(debitData[i]);
				}
			}
			debitData =new Object[debitVector.size()][2];
			for (int i = 0; i < debitData.length; i++) {
				debitData[i]=(Object[])debitVector.get(i);
			}
		}
		
		
		if(creditData != null){
			
			int debitCOunt=0;
		int objLength = creditData.length;
		if(debitData!=null && debitData.length>0)
		debitCOunt=debitData.length;
		if(creditData.length < debitCOunt){
			objLength = debitCOunt;
		}
		Object [][]finalObject = new Object[objLength][4];
		
			for (int i = 0; i < finalObject.length; i++) {
				try {
					finalObject[i][0] = creditData[i][0];
					finalObject[i][1] = Utility.twoDecimals(""+creditData[i][1]);
					grossSalary += Double.parseDouble(String.valueOf(creditData[i][1]));
				} catch (Exception e) {
					finalObject[i][0] = "";
					finalObject[i][1] = "";
				}
			}
			for (int i = 0; i < finalObject.length; i++) {
				try {
					finalObject[i][2] = "                  "+debitData[i][0];
					finalObject[i][3] = Utility.twoDecimals(""+debitData[i][1]);
					totDeduction += Double.parseDouble(String.valueOf(debitData[i][1]));
				} catch (Exception e) {
					finalObject[i][2] = "";
					finalObject[i][3] = "";
				}
			}
			/*Vector<Object[]> finalVector=new Vector<Object[]>();
			for (int i = 0; i < finalObject.length; i++) {
				try {
					if(Double.parseDouble(String.valueOf(finalObject[i][1]))>0 && Double.parseDouble(String.valueOf(finalObject[i][1]))>0){
						finalVector.add(debitData[i]);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			finalObject =new Object[finalVector.size()][4];
			for (int i = 0; i < finalObject.length; i++) {
				finalObject[i]=(Object[])finalVector.get(i);
			}*/
			totSalary = (grossSalary-totDeduction);
			String []grossSalaryArray = {"Gross Salary",formatter.format(grossSalary),"  Total Deduction",formatter.format(totDeduction)};
			String []totalDeductionArray = {"Total Deduction",formatter.format(totDeduction),"",""};
			String []totSalaryArray = {"Net Salary",formatter.format(totSalary),"",""};
			
			
		int []allignment ={1,1};
		int []cellWidth ={50,50};
		int []allignment1 ={0,2,0,2};
		int []allignment3 ={0,2,0,2};
		int []allignment2 ={1,1,1,1};
		int []cellWidth1 ={25,25,25,25};
		rg.addFormatedText(reportTitle, 6, 0, 1, 0);
		rg.addText("\n", 0, 0, 0);
		rg.tableRowWithBG(heading,cellWidth,allignment,100 );
		rg.tableBody(finalObject,cellWidth1,allignment3 );
		rg.tableRowWithBG(grossSalaryArray,cellWidth1,allignment1,100 );
		rg.tableRowWithBG(totalDeductionArray,cellWidth1,allignment1,100 );
		rg.tableRowWithBG(totSalaryArray,cellWidth1,allignment1,100 );
		
		Object [][]signatureTable =new Object [1][4];
		
		
		signatureTable [0][0] = "Passed for payment of \nRs. "+formatter.format(totSalary);
		signatureTable [0][1] = "";
		
		
		if(!bean.getReportType().equals("Txt")){
			rg.addText("\n", 0, 0, 0);
			rg.addText("", 0, 0, 0);
		rg.tableBodyNoBorderPromoSmall(signatureTable, cellWidth1, allignment2);
		rg.addText("\n\n\n\n\n\n\n\n", 0, 0, 0);
		rg.addText("", 0, 0, 0);
		}else{
			rg.tableBodyNoBorder(signatureTable, cellWidth1, allignment2);
		}
		signatureTable [0][0] = "ACCOUNTS OFFICER\n"+bean.getDivName();
		signatureTable [0][1] = "CHIEF ACCOUNTS OFFICER\n"+bean.getDivName();
		
		//rg.tableBodyNoBorder(signatureTable, cellWidth, allignment);
		signatureTable [0][2] = "MPL. CHIEF AUDIT\n"+bean.getDivName();
		signatureTable [0][3] = "DY. COMMISSIONER\n"+bean.getDivName();
		
		if(!bean.getReportType().equals("Txt"))
		rg.addText("\n\n\n\n\n\n", 0, 0, 0);
		rg.addText("", 0, 0, 0);
		rg.tableBodyNoBorder(signatureTable, cellWidth1, allignment2);
		}else{
			if(!bean.getReportType().equals("Txt")){
				rg.tableBodyNoBorderPromoSmall(new Object[][]{{"Salary of "+bean.getDivName()+" is not available For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear()}}, new int[]{50},new int[]{1});
				}else{
					rg.tableBodyNoBorder(new Object[][]{{"Salary of "+bean.getDivName()+" is not available For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear()}}, new int[]{50},new int[]{1});
				}
			}
		}else{
			if(!bean.getReportType().equals("Txt")){
			rg.tableBodyNoBorderPromoSmall(new Object[][]{{"Salary of "+bean.getDivName()+" is not available For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear()}}, new int[]{50},new int[]{1});
			}else{
				rg.tableBodyNoBorder(new Object[][]{{"Salary of "+bean.getDivName()+" is not available For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear()}}, new int[]{50},new int[]{1});
			}
		}
		return rg;
	}
	public ReportGenerator getBranchDeptwiseReport(ReportGenerator rg, SalarySummary bean){
		logger.info("inside getBranchwiseRport");
		
		int objLength = 0;
		double totSalary =0.0;
		int []allignment ={1,1};
		int []cellWidth ={50,50};
		int []allignment1 ={0,2,0,2};
		int []allignment2 ={1,1,1,1};
		int []cellWidth1 ={25,25,25,25};
		
		Object finalObject [][] = null;
		Object creditData [][] = null;
		Object debitData [][] = null;
		Object [][]signatureTable = null;
		String creditHeadQuery="SELECT CREDIT_ABBR,0,CREDIT_CODE FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
		Object creditCode[][]=getSqlModel().getSingleResult(creditHeadQuery);
		
		String debitQuery="SELECT DEBIT_ABBR, 0, DEBIT_CODE FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_CODE";
		Object debitCode[][]=getSqlModel().getSingleResult(debitQuery);
		
		String branchQuery = "SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME)";
		Object branchObj [][] =getSqlModel().getSingleResult(branchQuery) ;
		
		String deptQuery = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME)";
		Object deptObj [][] =getSqlModel().getSingleResult(deptQuery) ;
		
		String ledgerCode = getLedgerCode(bean);
		String arrearCode = getArrearCode(bean,"ADD");
		String [] heading={"SALARY","DEDUCTION"};
		
		
		if(!(ledgerCode.equals("") || ledgerCode.equals("null") || ledgerCode.equals("0"))){
		for (int branchIndex = 0; branchIndex < branchObj.length; branchIndex++) {
			for (int deptIndex = 0; deptIndex < deptObj.length; deptIndex++) {
			double grossSalary =0.0, totDeduction =0.0;
		creditData  = getCreditSalAmt(ledgerCode,arrearCode, creditCode, bean,""+branchObj[branchIndex][0],""+deptObj[deptIndex][0]) ;
		debitData = getDebitSalAmt(ledgerCode,arrearCode, debitCode, bean,""+branchObj[branchIndex][0],""+deptObj[deptIndex][0]) ;
		if(creditData != null){
		objLength = creditCode.length;
		if(creditCode.length < debitCode.length){
			objLength = debitCode.length;
		}
		finalObject = new Object[objLength][4];
		
		for (int i = 0; i < finalObject.length; i++) {
			try {
				finalObject[i][0] = creditData[i][0];
				finalObject[i][1] = Utility.twoDecimals(""+creditData[i][1]);
				grossSalary += Double.parseDouble(String.valueOf(creditData[i][1]));
			} catch (Exception e) {
				finalObject[i][0] = "";
				finalObject[i][1] = "";
			}
		}
		for (int i = 0; i < finalObject.length; i++) {
			try {
				finalObject[i][2] = debitData[i][0];
				finalObject[i][3] = Utility.twoDecimals(""+debitData[i][1]);
				totDeduction += Double.parseDouble(String.valueOf(debitData[i][1]));
			} catch (Exception e) {
				finalObject[i][2] = "";
				finalObject[i][3] = "";
			}
		}
		
		totSalary = (grossSalary-totDeduction);
		String []grossSalaryArray = {"Gross Salary",""+formatter.format(grossSalary),"Total Deduction",""+formatter.format(totDeduction)};
		String []totalDeductionArray = {"Total Deduction",""+formatter.format(totDeduction),"",""};
		String []totSalaryArray = {"Net Salary",""+formatter.format(totSalary),"",""};
			
			
			String reportTitle = "\n Salary Summary Of "+bean.getDivName()+" For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear();
		rg.addFormatedText(reportTitle, 6, 0, 1, 0);
		rg.addText("\n", 0, 0, 0);
		if(!bean.getReportType().equals("Txt")){
		rg.tableBodyNoBorderPromoSmall(new Object[][]{{"Branch :"+branchObj[branchIndex][1],"Department :"+deptObj[deptIndex][1]}}, new int[]{50,50},new int[]{0,0});
		}else{
			rg.tableBodyNoBorder(new Object[][]{{"Branch :"+branchObj[branchIndex][1],"Department :"+deptObj[deptIndex][1]}}, new int[]{50,50},new int[]{0,0});
		}
		//rg.addFormatedText("Branch :"+branchObj[branchIndex][1], 1, 0, 0, 100);
		//rg.addFormatedText("Department :"+deptObj[deptIndex][1], 1, 0, 0, 100);
		rg.addText("\n", 0, 0, 0);
		rg.tableRowWithBG(heading,cellWidth,allignment,100 );
		rg.tableBody(finalObject,cellWidth1,allignment1 );
		rg.tableRowWithBG(grossSalaryArray,cellWidth1,allignment1,100 );
		rg.tableRowWithBG(totalDeductionArray,cellWidth1,allignment1,100 );
		rg.tableRowWithBG(totSalaryArray,cellWidth1,allignment1,100 );
		
		signatureTable =new Object [1][4];
		
		
		signatureTable [0][0] = "Passed for payment of \nRs. "+formatter.format(totSalary);
		signatureTable [0][1] = "";
		
		if(!bean.getReportType().equals("Txt")){
			rg.addText("\n", 0, 0, 0);
			rg.addText("", 0, 0, 0);
		rg.tableBodyNoBorderPromoSmall(signatureTable, cellWidth1, allignment2);
		rg.addText("\n\n\n\n\n\n\n\n", 0, 0, 0);
		rg.addText("", 0, 0, 0);
		}else{
			rg.tableBodyNoBorder(signatureTable, cellWidth1, allignment2);
		}
		signatureTable [0][0] = "ACCOUNTS OFFICER\n"+bean.getDivName();
		signatureTable [0][1] = "CHIEF ACCOUNTS OFFICER\n"+bean.getDivName();
		
		//rg.tableBodyNoBorder(signatureTable, cellWidth, allignment);
		signatureTable [0][2] = "MPL. CHIEF AUDIT\n"+bean.getDivName();
		signatureTable [0][3] = "DY. COMMISSIONER\n"+bean.getDivName();
		if(!bean.getReportType().equals("Txt"))
		rg.addText("\n\n\n\n\n\n", 0, 0, 0);
		rg.addText("", 0, 0, 0);
		rg.tableBodyNoBorder(signatureTable, cellWidth1, allignment2);
		rg.pageBreak();
		}
		}
		}
	}else{
		if(!bean.getReportType().equals("Txt")){
		rg.tableBodyNoBorderPromoSmall(new Object[][]{{"Salary of "+bean.getDivName()+" is not available For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear()}}, new int[]{50},new int[]{1});
		}else{
			rg.tableBodyNoBorder(new Object[][]{{"Salary of "+bean.getDivName()+" is not available For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear()}}, new int[]{50},new int[]{1});
		}
	}
		return rg;
	}

	
public ReportGenerator getBranchwiseReport(ReportGenerator rg, SalarySummary bean){
			logger.info("inside getBranchwiseReport");
			
			int objLength = 0;
			int []allignment ={1,1};
			int []cellWidth ={50,50};
			int []allignment1 ={0,2,0,2};
			int []allignment2 ={1,1,1,1};
			int []cellWidth1 ={25,25,25,25};
			double totSalary =0.0;
			
			Object finalObject [][] = null;
			Object creditData [][] = null;
			Object debitData [][] = null;
			Object [][]signatureTable = null;
			String creditHeadQuery="SELECT CREDIT_ABBR,0,CREDIT_CODE FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
			Object creditCode[][]=getSqlModel().getSingleResult(creditHeadQuery);
			
			String debitQuery="SELECT DEBIT_ABBR, 0, DEBIT_CODE FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_CODE";
			Object debitCode[][]=getSqlModel().getSingleResult(debitQuery);
			
			String branchQuery = "SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME)";
			Object branchObj [][] =getSqlModel().getSingleResult(branchQuery) ;
			
			String ledgerCode = getLedgerCode(bean);
			String arrearCode = getArrearCode(bean,"ADD");
			
			String [] heading={"SALARY","DEDUCTION"};
			if(!(ledgerCode.equals("") || ledgerCode.equals("null")|| ledgerCode.equals("0"))){
			for (int branchIndex = 0; branchIndex < branchObj.length; branchIndex++) {
				
				double grossSalary =0.0, totDeduction =0.0;
			creditData  = getCreditSalAmt(ledgerCode,arrearCode, creditCode, bean,""+branchObj[branchIndex][0],"") ;
			debitData = getDebitSalAmt(ledgerCode,arrearCode, debitCode, bean,""+branchObj[branchIndex][0],"") ;
			if(creditData != null){
			objLength = creditCode.length;
			if(creditCode.length < debitCode.length){
				objLength = debitCode.length;
			}
			finalObject = new Object[objLength][4];
			
			for (int i = 0; i < finalObject.length; i++) {
				try {
					finalObject[i][0] = creditData[i][0];
					finalObject[i][1] = Utility.twoDecimals(""+creditData[i][1]);
					grossSalary += Double.parseDouble(String.valueOf(creditData[i][1]));
				} catch (Exception e) {
					finalObject[i][0] = "";
					finalObject[i][1] = "";
				}
			}
			for (int i = 0; i < finalObject.length; i++) {
				try {
					finalObject[i][2] = debitData[i][0];
					finalObject[i][3] = Utility.twoDecimals(""+debitData[i][1]);
					totDeduction += Double.parseDouble(String.valueOf(debitData[i][1]));
				} catch (Exception e) {
					finalObject[i][2] = "";
					finalObject[i][3] = "";
				}
			}
			
			totSalary = (grossSalary-totDeduction);
			String []grossSalaryArray = {"Gross Salary",""+formatter.format(grossSalary),"Total Deduction",""+formatter.format(totDeduction)};
			String []totalDeductionArray = {"Total Deduction",""+formatter.format(totDeduction),"",""};
			String []totSalaryArray = {"Net Salary",""+formatter.format(totSalary),"",""};
				
				String reportTitle = "\n Salary Summary Of "+bean.getDivName()+" For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear();
			rg.addFormatedText(reportTitle, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			if(!bean.getReportType().equals("Txt")){
			rg.tableBodyNoBorderPromoSmall(new Object[][]{{String.valueOf("Branch :"+branchObj[branchIndex][1])}}, new int[]{20},new int[]{0});
			}else{
				rg.tableBodyNoBorder(new Object[][]{{String.valueOf("Branch :"+branchObj[branchIndex][1])}}, new int[]{20},new int[]{0});
			}
			//rg.addFormatedText("Branch :"+branchObj[branchIndex][1], 1, 0, 0, 100);
			rg.addText("\n", 0, 0, 0);
			rg.tableRowWithBG(heading,cellWidth,allignment,100 );
			rg.tableBody(finalObject,cellWidth1,allignment1 );
			rg.tableRowWithBG(grossSalaryArray,cellWidth1,allignment1,100 );
			rg.tableRowWithBG(totalDeductionArray,cellWidth1,allignment1,100 );
			rg.tableRowWithBG(totSalaryArray,cellWidth1,allignment1,100 );
			signatureTable =new Object [1][4];
			
			
			signatureTable [0][0] = "Passed for payment of \nRs. "+formatter.format(totSalary);
			signatureTable [0][1] = "";
			
			if(!bean.getReportType().equals("Txt")){
				rg.addText("\n", 0, 0, 0);
				rg.addText("", 0, 0, 0);
			rg.tableBodyNoBorderPromoSmall(signatureTable, cellWidth1, allignment2);
			rg.addText("\n\n\n\n\n\n\n\n", 0, 0, 0);
			rg.addText("", 0, 0, 0);
			}else{
				rg.tableBodyNoBorder(signatureTable, cellWidth1, allignment2);
			}
			signatureTable [0][0] = "ACCOUNTS OFFICER\n"+bean.getDivName();
			signatureTable [0][1] = "CHIEF ACCOUNTS OFFICER\n"+bean.getDivName();
			
			//rg.tableBodyNoBorder(signatureTable, cellWidth, allignment);
			signatureTable [0][2] = "MPL. CHIEF AUDIT\n"+bean.getDivName();
			signatureTable [0][3] = "DY. COMMISSIONER\n"+bean.getDivName();
			if(!bean.getReportType().equals("Txt"))
			rg.addText("\n\n\n\n\n\n", 0, 0, 0);
			rg.addText("", 0, 0, 0);
			rg.tableBodyNoBorder(signatureTable, cellWidth1, allignment2);
			rg.pageBreak();
			}
			}
			}else{
				if(!bean.getReportType().equals("Txt")){
				rg.tableBodyNoBorderPromoSmall(new Object[][]{{"Salary of "+bean.getDivName()+" is not available For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear()}}, new int[]{50},new int[]{1});
				}else{
					rg.tableBodyNoBorder(new Object[][]{{"Salary of "+bean.getDivName()+" is not available For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear()}}, new int[]{50},new int[]{1});
				}
			}
			
			return rg;
		}
		public ReportGenerator getDeptwiseReport(ReportGenerator rg, SalarySummary bean){
			logger.info("inside getDeptwiseRport");
			
			int objLength = 0;
			int []allignment ={1,1};
			int []cellWidth ={50,50};
			int []allignment1 ={0,2,0,2};
			int []allignment2 ={1,1,1,1};
			int []cellWidth1 ={25,25,25,25};
			double totSalary =0.0;
			
			Object finalObject [][] = null;
			Object creditData [][] = null;
			Object debitData [][] = null;
			Object [][]signatureTable = null;
			String creditHeadQuery="SELECT CREDIT_ABBR,0,CREDIT_CODE FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
			Object creditCode[][]=getSqlModel().getSingleResult(creditHeadQuery);
			
			String debitQuery="SELECT DEBIT_ABBR, 0, DEBIT_CODE FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_CODE";
			Object debitCode[][]=getSqlModel().getSingleResult(debitQuery);
			
			String deptQuery = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME)";
			Object deptObj [][] =getSqlModel().getSingleResult(deptQuery) ;
			
			String ledgerCode = getLedgerCode(bean);
			String arrearCode = getArrearCode(bean,"ADD");
			
			String [] heading={"SALARY","DEDUCTION"};
			
			logger.info("ledgercode==="+ledgerCode);
			if(!(ledgerCode.equals("") || ledgerCode.equals("null") || ledgerCode.equals("0"))){
				
			for (int deptIndex = 0; deptIndex < deptObj.length; deptIndex++) {
				
				double grossSalary =0.0, totDeduction =0.0;
			creditData  = getCreditSalAmt(ledgerCode,arrearCode, creditCode, bean,"",""+deptObj[deptIndex][0]) ;
			debitData = getDebitSalAmt(ledgerCode,arrearCode, debitCode, bean,"",""+deptObj[deptIndex][0]) ;
			if(creditData != null ){
			objLength = creditCode.length;
			if(creditCode.length < debitCode.length){
				objLength = debitCode.length;
			}
			finalObject = new Object[objLength][4];
			
			for (int i = 0; i < finalObject.length; i++) {
				try {
					finalObject[i][0] = creditData[i][0];
					finalObject[i][1] = Utility.twoDecimals(""+creditData[i][1]);
					grossSalary += Double.parseDouble(String.valueOf(creditData[i][1]));
				} catch (Exception e) {
					finalObject[i][0] = "";
					finalObject[i][1] = "";
				}
			}
			for (int i = 0; i < finalObject.length; i++) {
				try {
					finalObject[i][2] = debitData[i][0];
					finalObject[i][3] = Utility.twoDecimals(""+debitData[i][1]);
					totDeduction += Double.parseDouble(String.valueOf(debitData[i][1]));
				} catch (Exception e) {
					finalObject[i][2] = "";
					finalObject[i][3] = "";
				}
			}
			
			totSalary = (grossSalary-totDeduction);
			String []grossSalaryArray = {"Gross Salary",""+formatter.format(grossSalary),"Total Deduction",""+formatter.format(totDeduction)};
			String []totalDeductionArray = {"Total Deduction",""+formatter.format(totDeduction),"",""};
			String []totSalaryArray = {"Net Salary",""+formatter.format(totSalary),"",""};
				
				
				String reportTitle = "\n Salary Summary Of "+bean.getDivName()+" For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear();
			rg.addFormatedText(reportTitle, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			if(!bean.getReportType().equals("Txt")){
			rg.tableBodyNoBorderPromoSmall(new Object[][]{{String.valueOf("Department :"+deptObj[deptIndex][1])}}, new int[]{20},new int[]{0});
			}else{
				rg.tableBodyNoBorder(new Object[][]{{String.valueOf("Department :"+deptObj[deptIndex][1])}}, new int[]{20},new int[]{0});
			}
			//rg.addFormatedText("Department :"+deptObj[deptIndex][1], 1, 0, 0, 100);
			rg.addText("\n", 0, 0, 0);
			rg.tableRowWithBG(heading,cellWidth,allignment,100 );
			rg.tableBody(finalObject,cellWidth1,allignment1 );
			rg.tableRowWithBG(grossSalaryArray,cellWidth1,allignment1,100 );
			rg.tableRowWithBG(totalDeductionArray,cellWidth1,allignment1,100 );
			rg.tableRowWithBG(totSalaryArray,cellWidth1,allignment1,100 );
			
			signatureTable =new Object [1][4];
			
			signatureTable [0][0] = "Passed for payment of \nRs. "+formatter.format(totSalary);
			signatureTable [0][1] = "";
			
			if(!bean.getReportType().equals("Txt")){
				rg.addText("\n", 0, 0, 0);
				rg.addText("", 0, 0, 0);
			rg.tableBodyNoBorderPromoSmall(signatureTable, cellWidth1, allignment2);
			rg.addText("\n\n\n\n\n\n\n\n", 0, 0, 0);
			rg.addText("", 0, 0, 0);
			}else{
				rg.tableBodyNoBorder(signatureTable, cellWidth1, allignment2);
			}
			signatureTable [0][0] = "ACCOUNTS OFFICER\n"+bean.getDivName();
			signatureTable [0][1] = "CHIEF ACCOUNTS OFFICER\n"+bean.getDivName();
			
			//rg.tableBodyNoBorder(signatureTable, cellWidth, allignment);
			signatureTable [0][2] = "MPL. CHIEF AUDIT\n"+bean.getDivName();
			signatureTable [0][3] = "DY. COMMISSIONER\n"+bean.getDivName();
			if(!bean.getReportType().equals("Txt"))
			rg.addText("\n\n\n\n\n\n", 0, 0, 0);
			rg.addText("", 0, 0, 0);
			rg.tableBodyNoBorder(signatureTable, cellWidth1, allignment2);
			rg.pageBreak();
			}
			}
			}else{
				if(!bean.getReportType().equals("Txt")){
				rg.tableBodyNoBorderPromoSmall(new Object[][]{{"Salary of "+bean.getDivName()+" is not available For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear()}}, new int[]{50},new int[]{1});
				}else{
					rg.tableBodyNoBorder(new Object[][]{{"Salary of "+bean.getDivName()+" is not available For "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear()}}, new int[]{50},new int[]{1});
				}
			}
			
			return rg;
		}
	/**
	 * following function is called to calculate the total sal amount for a credit component
	 * @param empId
	 * @param year
	 * @param ledgerCode
	 * @return
	 */
	
	public Object[][] getCreditSalAmt(String ledgerCode,String arrearCode,Object [][]creditCode,SalarySummary bean, String brnId, String deptId) {
		Object[][] credit_amount_sal = null;
		
		String year = bean.getYear();
		/*String salAmt="SELECT CREDIT_ABBR,SUM(NVL(SAL_AMOUNT,0)),CREDIT_CODE FROM HRMS_CREDIT_HEAD "
			+" LEFT JOIN HRMS_SAL_CREDITS_"+year+" ON (SAL_CREDIT_CODE = CREDIT_CODE AND SAL_LEDGER_CODE IN("+ledgerCode+")) "
			//+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_"+year+".EMP_ID)"
			+" INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_CREDITS_"+year+".EMP_ID  AND HRMS_SALARY_"+year+".SAL_LEDGER_CODE IN("+ledgerCode+"))"
			+" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y'";*/
		
		String  salAmt="SELECT PARENT_TABLE.CREDIT_ABBR,SUM(NVL(SAL_AMOUNT,0)),PARENT_TABLE.CREDIT_CODE FROM HRMS_SAL_CREDITS_"+year 
			+" LEFT JOIN HRMS_CREDIT_HEAD  ON (SAL_CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE AND SAL_LEDGER_CODE IN("+ledgerCode+"))" 
			+" INNER JOIN HRMS_CREDIT_HEAD PARENT_TABLE ON (HRMS_CREDIT_HEAD.PARENT_CREDIT=PARENT_TABLE.CREDIT_CODE)"
			+" INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_CREDITS_"+year+".EMP_ID  AND HRMS_SALARY_"+year+".SAL_LEDGER_CODE IN("+ledgerCode+"))"
			+" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y'";
		if(!bean.getOnHold().equals("A")){
			salAmt += " AND SAL_ONHOLD='"+bean.getOnHold()+"'";
		}
		if(bean.getBranchFlag().equals("true")){
			salAmt += " AND SAL_EMP_CENTER="+brnId;
			
		}else if(!bean.getBrnCode().equals("")){
			salAmt += " AND SAL_EMP_CENTER IN ("+(bean.getBrnCode())+")";
		}
		if(bean.getDeptFlag().equals("true")){
			salAmt += " AND SAL_DEPT="+deptId;
			
		}else if(!bean.getDeptCode().equals("")){
			salAmt += " AND SAL_DEPT IN ("+(bean.getDeptCode())+")";
		}
		
		if(!bean.getDesgCode().equals("")){
			salAmt += " AND SAL_EMP_RANK IN ("+(bean.getDesgCode())+")";
		}
		if(!bean.getPaybillId().equals("")){
			salAmt += " AND SAL_EMP_PAYBILL IN ("+(bean.getPaybillId())+")";
		}
		/*SELECT CREDIT_ABBR,sum(NVL(SAL_AMOUNT,0)),SAL_CREDIT_CODE FROM HRMS_CREDIT_HEAD  
		LEFT JOIN HRMS_SAL_CREDITS_2009 ON (SAL_CREDIT_CODE = CREDIT_CODE AND SAL_LEDGER_CODE IN(2))
		LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_2009.EMP_ID) 
		WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y'
		and SAL_EMP_CENTER =1 and emp_DEPT =3 and emp_rank =1*/
		
		 
		
//		 salAmt += " GROUP BY CREDIT_ABBR,SAL_CREDIT_CODE,CREDIT_CODE ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
		 salAmt += " GROUP BY PARENT_TABLE.CREDIT_ABBR,PARENT_TABLE.CREDIT_CODE ORDER BY PARENT_TABLE.CREDIT_CODE ";
		
		 logger.info("salAmt query for credits=="+salAmt);
		 
		// String recoveryCode2=getArrearCode(bean, "DED");
		 
		 Object [][]returnObj = null;
		if(bean.getSummaryFor().equals("AR")){
			Object arrearObj [][]= getCreditArrearAmt(arrearCode, creditCode, bean, brnId, deptId);
			 returnObj = compareObject(creditCode, arrearObj);
		}else if(bean.getSummaryFor().equals("SA")){
			 credit_amount_sal  = getSqlModel().getSingleResult(salAmt);
			 returnObj = compareObject(creditCode, credit_amount_sal);
			 /*Object arrearObj [][]= getCreditArrearAmt(recoveryCode, creditCode, bean, brnId, deptId);
			 if((credit_amount_sal==null || credit_amount_sal.length==0)){
				  if(arrearObj== null || arrearObj.length==0){
					  return null;
				  }else 
				 return null;
			  }else{
				  credit_amount_sal = compareObject(creditCode, credit_amount_sal);
				  if(arrearObj== null || arrearObj.length==0){
					  return credit_amount_sal;
				  }else{
					  returnObj = addObjectValues(credit_amount_sal,arrearObj,"DED");
				  }
			  }*/
		}
		else if(bean.getSummaryFor().equals("AL")){
			credit_amount_sal  = getSqlModel().getSingleResult(salAmt);
			// Object recoveryObj [][]= getCreditArrearAmt(recoveryCode, creditCode, bean, brnId, deptId);
			/* if((credit_amount_sal==null || credit_amount_sal.length==0)){
				  if(recoveryObj== null || recoveryObj.length==0){
					  return null;
				  }else 
				 return null;
			  }else{
				  credit_amount_sal = compareObject(creditCode, credit_amount_sal);
				  if(recoveryObj== null || recoveryObj.length==0){
					  return credit_amount_sal;
				  }else{
					  credit_amount_sal = addObjectValues(credit_amount_sal,recoveryObj,"DED");
				  }
			  }*/
			Object arrearObj [][]= getCreditArrearAmt(arrearCode, creditCode, bean, brnId, deptId);
		  if((credit_amount_sal==null || credit_amount_sal.length==0)){
			  if(arrearObj== null || arrearObj.length==0){
				  return null;
			  }else 
			 return null;
		  }else{
			  credit_amount_sal = compareObject(creditCode, credit_amount_sal);
			  if(arrearObj== null || arrearObj.length==0){
				  return credit_amount_sal;
			  }else{
				  returnObj = addObjectValues(credit_amount_sal,arrearObj,"ADD");
			  }
		  }
		  /*
		   * call compareObjectSal method to compare the credit codes from HRMS_CREDIT_HEAD and local object
		   * 
		   */
		  
		  returnObj = compareObject(creditCode, returnObj);
		}
		  return returnObj ;
     }
	/**
	 * following function is called to calculate the total arrear amount for a credit component
	 * @param empId
	 * @param year
	 * @param ledgerCode
	 * @param arrearCode
	 * @return
	 */
	
	public Object[][] getCreditArrearAmt(String arrearCode,Object [][]creditCode,SalarySummary bean, String brnId, String deptId) {
		Object[][] credit_amount_arrear = null;
		
		String year = bean.getYear();
		String  arrearAmtQuery="SELECT CREDIT_ABBR,NVL(SUM(ARREARS_AMT),0),CREDIT_CODE FROM HRMS_ARREARS_CREDIT_"+year
		+" INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE AND HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE IN("+arrearCode+")) "
		+" INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ARREARS_CREDIT_"+year+".ARREARS_CREDIT_CODE) "
		+" INNER JOIN HRMS_ARREARS_"+year+" ON (HRMS_ARREARS_"+year+".ARREARS_CODE=HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE and HRMS_ARREARS_"+year+".EMP_ID=HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID) "
		+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID= HRMS_ARREARS_"+year+".EMP_ID)"
		+" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND HRMS_ARREARS_LEDGER.ARREARS_PAY_IN_SAL='Y' ";
		if(!bean.getOnHold().equals("A")){
			arrearAmtQuery += " AND ARREARS_ONHOLD='"+bean.getOnHold()+"'";
		}
		if(bean.getBranchFlag().equals("true")){
			arrearAmtQuery += " AND EMP_CENTER="+brnId;
			
		}else if(!bean.getBrnCode().equals("")){
			arrearAmtQuery += " AND EMP_CENTER IN ("+(bean.getBrnCode())+")";
		}
		if(bean.getDeptFlag().equals("true")){
			arrearAmtQuery += " AND EMP_DEPT="+deptId;
			
		}else if(!bean.getDeptCode().equals("")){
			arrearAmtQuery += " AND EMP_DEPT IN ("+(bean.getDeptCode())+")";
		}
		
		if(!bean.getDesgCode().equals("")){
			arrearAmtQuery += " AND EMP_RANK IN ("+(bean.getDesgCode())+")";
		}
		if(!bean.getPaybillId().equals("")){
			arrearAmtQuery += " AND EMP_PAYBILL IN ("+(bean.getPaybillId())+")";
		}
		/*SELECT CREDIT_ABBR,sum(NVL(SAL_AMOUNT,0)),SAL_CREDIT_CODE FROM HRMS_CREDIT_HEAD  
		LEFT JOIN HRMS_SAL_CREDITS_2009 ON (SAL_CREDIT_CODE = CREDIT_CODE AND SAL_LEDGER_CODE IN(2))
		LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_2009.EMP_ID) 
		WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y'
		and emp_center =1 and emp_DEPT =3 and emp_rank =1*/
		
		 
		
		arrearAmtQuery += " GROUP BY CREDIT_CODE,CREDIT_ABBR ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
		
		 logger.info("arrearAmtQuery query for credits=="+arrearAmtQuery);
		 credit_amount_arrear  = getSqlModel().getSingleResult(arrearAmtQuery);
		
			
		
		 Object [][]returnObj = null;
		  if(credit_amount_arrear==null || credit_amount_arrear.length==0){
			 return null;
		  }else{
			  returnObj= credit_amount_arrear;
		  }
		  /*
		   * call compareObjectSal method to compare the credit codes from HRMS_CREDIT_HEAD and local object
		   * 
		   */
		  
		  returnObj = compareObject(creditCode, returnObj);
		  return returnObj ;
     }
	/**
	 * following function is called to calculate the total arrear amount for a debit component
	 * @param empId
	 * @param year
	 * @param ledgerCode
	 * @param arrearCode
	 * @return
	 */
	
	public Object[][] getDebitArrearAmt(String arrearCode,Object [][]debitCode,SalarySummary bean, String brnId, String deptId) {
		Object[][] debit_amount_arrear = null;
		
		String year = bean.getYear();
		String  arrearAmtQuery="SELECT DEBIT_ABBR,NVL(SUM(ARREARS_AMT),0),DEBIT_CODE FROM HRMS_ARREARS_DEBIT_"+year
					+" INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE AND HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE IN("+arrearCode+"))"
					+" INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE  )"
					+" INNER JOIN HRMS_ARREARS_"+year+" ON (HRMS_ARREARS_"+year+".ARREARS_CODE=HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE AND HRMS_ARREARS_"+year+".EMP_ID=HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID) "
					+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID= HRMS_ARREARS_"+year+".EMP_ID)"
					+" WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' AND HRMS_ARREARS_LEDGER.ARREARS_PAY_IN_SAL='Y'";
					
		if(!bean.getOnHold().equals("A")){
			arrearAmtQuery += " AND ARREARS_ONHOLD='"+bean.getOnHold()+"'";
		}
		if(bean.getBranchFlag().equals("true")){
			arrearAmtQuery += " AND EMP_CENTER="+brnId;
			
		}else if(!bean.getBrnCode().equals("")){
			arrearAmtQuery += " AND EMP_CENTER IN ("+(bean.getBrnCode())+")";
		}
		if(bean.getDeptFlag().equals("true")){
			arrearAmtQuery += " AND EMP_DEPT="+deptId;
			
		}else if(!bean.getDeptCode().equals("")){
			arrearAmtQuery += " AND EMP_DEPT IN ("+(bean.getDeptCode())+")";
		}
		
		if(!bean.getDesgCode().equals("")){
			arrearAmtQuery += " AND EMP_RANK IN ("+(bean.getDesgCode())+")";
		}
		if(!bean.getPaybillId().equals("")){
			arrearAmtQuery += " AND EMP_PAYBILL IN ("+(bean.getPaybillId())+")";
		}
		/*SELECT CREDIT_ABBR,sum(NVL(SAL_AMOUNT,0)),SAL_CREDIT_CODE FROM HRMS_CREDIT_HEAD  
		LEFT JOIN HRMS_SAL_CREDITS_2009 ON (SAL_CREDIT_CODE = CREDIT_CODE AND SAL_LEDGER_CODE IN(2))
		LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_2009.EMP_ID) 
		WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y'
		and emp_center =1 and emp_DEPT =3 and emp_rank =1*/
		
		 
		
		arrearAmtQuery +=" GROUP BY DEBIT_CODE,DEBIT_ABBR ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
		
		 logger.info("arrearAmtQuery query for credits=="+arrearAmtQuery);
		 debit_amount_arrear  = getSqlModel().getSingleResult(arrearAmtQuery);
		
			
		
		 Object [][]returnObj = null;
		  if(debit_amount_arrear==null || debit_amount_arrear.length==0){
			 return null;
		  }else{
			  returnObj= debit_amount_arrear;
		  }
		  /*
		   * call compareObjectSal method to compare the credit codes from HRMS_CREDIT_HEAD and local object
		   * 
		   */
		  
		  returnObj = compareObject(debitCode, returnObj);
		  return returnObj ;
     }
	/**
	 * following function is called to calculate the total sal amount for a debit component
	 * @param empId
	 * @param year
	 * @param ledgerCode
	 * @return
	 */
	
	public Object[][] getDebitSalAmt(String ledgerCode,String arrearCode,Object [][]debitCode,SalarySummary bean,String brnId, String deptId) {
		Object[][] debit_amount_sal = null;
		
		String year = bean.getYear();
		/*String salAmt="SELECT DEBIT_ABBR,SUM(NVL(SAL_AMOUNT,0)),SAL_DEBIT_CODE FROM HRMS_DEBIT_HEAD "
			+" INNER JOIN HRMS_SAL_DEBITS_"+year+" ON (SAL_DEBIT_CODE = DEBIT_CODE AND SAL_LEDGER_CODE IN("+ledgerCode+")) "
			//+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID)"
			+" INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID  AND HRMS_SALARY_"+year+".SAL_LEDGER_CODE IN("+ledgerCode+"))"
			+" WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y'";*/
		
		String salAmt="SELECT PARENT_TABLE.DEBIT_ABBR,SUM(NVL(SAL_AMOUNT,0)),PARENT_TABLE.DEBIT_CODE FROM  HRMS_SAL_DEBITS_"+year
				+" INNER JOIN HRMS_DEBIT_HEAD  ON (SAL_DEBIT_CODE = DEBIT_CODE AND SAL_LEDGER_CODE IN("+ledgerCode+")) "
				+" INNER JOIN HRMS_DEBIT_HEAD PARENT_TABLE ON (HRMS_DEBIT_HEAD.PARENT_DEBIT=PARENT_TABLE.DEBIT_CODE)"
				+" INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID  AND HRMS_SALARY_"+year+".SAL_LEDGER_CODE IN("+ledgerCode+"))"
				+" WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y' ";
				
		
		if(!bean.getOnHold().equals("A")){
			salAmt += " AND SAL_ONHOLD='"+bean.getOnHold()+"'";
		}
		if(bean.getBranchFlag().equals("true")){
			salAmt += " AND SAL_EMP_CENTER="+brnId;
			
		}else if(!bean.getBrnCode().equals("")){
			salAmt += " AND SAL_EMP_CENTER IN ("+(bean.getBrnCode())+")";
		}
		if(bean.getDeptFlag().equals("true")){
			salAmt += " AND SAL_DEPT="+deptId;
			
		}else if(!bean.getDeptCode().equals("")){
			salAmt += " AND SAL_DEPT IN ("+(bean.getDeptCode())+")";
		}
		
		if(!bean.getDesgCode().equals("")){
			salAmt += " AND SAL_EMP_RANK IN ("+(bean.getDesgCode())+")";
		}
		if(!bean.getPaybillId().equals("")){
			salAmt += " AND SAL_EMP_PAYBILL IN ("+(bean.getPaybillId())+")";
		}
		
		salAmt += " GROUP BY PARENT_TABLE.DEBIT_ABBR,PARENT_TABLE.DEBIT_CODE ORDER BY PARENT_TABLE.DEBIT_CODE";
		
		 logger.info("salAmt query for credits=="+salAmt);
		 Object arrearObj [][]=null;
		 
		 
		 Object [][]returnObj = null;
		 if(bean.getSummaryFor().equals("AR")){
			 arrearObj= getDebitArrearAmt(arrearCode, debitCode, bean, brnId, deptId); 
			 returnObj = compareObject(debitCode, arrearObj);
		 }else  if(bean.getSummaryFor().equals("SA")){
			 debit_amount_sal = getSqlModel().getSingleResult(salAmt);
			 returnObj = compareObject(debitCode, debit_amount_sal);
		 }else  if(bean.getSummaryFor().equals("AL")){
			 arrearObj = getDebitArrearAmt(arrearCode, debitCode, bean, brnId, deptId);
			 debit_amount_sal = getSqlModel().getSingleResult(salAmt);
		 
		  if((debit_amount_sal==null || debit_amount_sal.length==0)){
			  if(arrearObj== null || arrearObj.length==0){
				  return null;
			  }else 
			 return null;
		  }else{
			  debit_amount_sal = compareObject(debitCode, debit_amount_sal);
			  if(arrearObj== null || arrearObj.length==0){
				  return debit_amount_sal;
			  }else{
				  returnObj = addObjectValues(debit_amount_sal,arrearObj,"ADD");
			  }
		  }
		  /*
		   * call compareObjectSal method to compare the credit codes from HRMS_CREDIT_HEAD and local object
		   * 
		   */
		  
		  returnObj = compareObject(debitCode, returnObj);
		 }
		  return returnObj ;
     }
	
	public Object [][] compareObject(Object [][]sourceObj,Object[][]destObj){
		Object returnObj [][]= new Object[sourceObj.length][3];
		
		int j = 0;
		for (int i = 0; i < returnObj.length; i++) {
			if(j<destObj.length && String.valueOf(sourceObj[i][2]).equals(String.valueOf(destObj[j][2]))){
				
					returnObj[i][0] = destObj[j][0];
					returnObj[i][1] = destObj[j][1];
					returnObj[i][2] = destObj[j++][2];
				
			}else{
				returnObj[i][0] = sourceObj[i][0];
				returnObj[i][1] = sourceObj[i][1];
				returnObj[i][2] = sourceObj[i][2];
			}
		}
		return returnObj;
	}
	
	public String getLedgerCode(SalarySummary bean){
		String ledgerCode = "";
		String year =bean.getYear();
		try{
		String ledgerQuery = "SELECT DISTINCT LEDGER_CODE FROM HRMS_SALARY_LEDGER " 
			//+"LEFT JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
			+"WHERE LEDGER_MONTH="+bean.getMonth()+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION ="+bean.getDivCode()+" AND LEDGER_STATUS IN('SAL_START','SAL_FINAL')";
		
		Object ledgerObj [][]=getSqlModel().getSingleResult(ledgerQuery); 
		for (int i = 0; i < ledgerObj.length; i++) {
			if(i== ledgerObj.length -1){
				ledgerCode += String.valueOf(ledgerObj[i][0]);
			}else
			ledgerCode += String.valueOf(ledgerObj[i][0])+",";
		}
		//ledgerCode = String.valueOf(ledgerObj[0][0]);
		}catch (Exception e) {
			ledgerCode ="0";
		}
		return ledgerCode;
	}
	public String getArrearCode(SalarySummary bean,String type){
		String arrearCode = "0";
		String year =bean.getYear();
		try{
		String ledgerQuery = "SELECT DISTINCT ARREARS_CODE FROM HRMS_ARREARS_LEDGER " 
				+" WHERE ARREARS_REF_MONTH="+bean.getMonth()+" AND ARREARS_REF_YEAR="+year+" AND ARREARS_DIVISION ="+bean.getDivCode()+" AND ARREARS_PAY_TYPE='"+type+"'";			//AND ARREARS_TYPE ='M' and ARREARS_PAY_IN_SAL='Y'";
		
		Object ledgerObj [][]=getSqlModel().getSingleResult(ledgerQuery); 
		for (int i = 0; i < ledgerObj.length; i++) {
			if(i== ledgerObj.length -1){
				arrearCode += String.valueOf(ledgerObj[i][0]);
			}else
				arrearCode += String.valueOf(ledgerObj[i][0])+",";
		}
		}catch (Exception e) {
			arrearCode ="0";
		}
		return arrearCode;
	}
	public Object [][] addObjectValues(Object [][] object1,Object [][] object2,String type){
		for (int i = 0; i < object2.length; i++) {
			if(type.equals("ADD")){
				object1 [i][1]= Double.parseDouble(""+object1[i][1])+ Double.parseDouble(""+object2[i][1]);
			}else{
				object1 [i][1]= Double.parseDouble(""+object1[i][1])- Double.parseDouble(""+object2[i][1]);
			}
			
		}
		
		return object1;
	}
	
}
