package org.paradyne.model.gov;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.gov.PensionSummaryReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class PensionSummaryReportModel extends ModelBase {

public void getReport(PensionSummaryReport bean,HttpServletResponse response){
		
		String reportTitle = "\n Pension Summary Of "+Utility.month(Integer.parseInt(bean.getMonth()))+"-"+bean.getYear();
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportTitle);
		/*String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
		Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
		//rg.setFName("Appraisal Configuration Report");
		
		rg.addText("Date: " + dateData[0][0], 0, 2, 0);*/
		
		rg.addFormatedText(bean.getDivName(), 6, 0, 1, 0, 13);//(bean.getDivName(), 0, 1, 0, 12);
		rg.addText("\n", 0, 0, 0);
		String billTitle="PENSION FOR THE MONTH OF "+Utility.month(Integer.parseInt(bean.getMonth())).toUpperCase()+"-"+bean.getYear();
		rg.addFormatedText(billTitle, 5, 0, 1, 0, 13);
		rg.addText("\n", 0, 0, 0);
		//rg.addText("FOR THE MONTH OF :"+Utility.month(Integer.parseInt(bean.getMonth()))+"'"+bean.getYear(), 0, 1, 0, 10);
		
		//rg.addFormatedText("FOR THE MONTH OF :"+Utility.month(Integer.parseInt(bean.getMonth()))+"' "+bean.getYear(), 1, 0, 1, 0, 20);
		//rg = getGeneralReport(rg, bean);
		
		String [] heading={"PARTICULARS","AMOUNT","","PARTICULARS","AMOUNT"};
		int []allignment ={0,2,0,0,2};
		int []cellWidth ={28,20,5,28,20};
		
		String moonth=bean.getMonth();
		String year=bean.getYear();
		String divCode=bean.getDivCode();
		String query="SELECT LEDGER_CODE FROM HRMS_PENSION_LEDGER WHERE LEDGER_MONTH="+moonth+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION="+divCode;
		Object[][]ledgerObj=getSqlModel().getSingleResult(query);
		if(ledgerObj!=null && ledgerObj.length>0){
			NumberFormat formatter = new DecimalFormat("#0.00");
			String creditQuery="SELECT PENS_CREDIT_CODE,CREDIT_NAME,SUM(NVL(PENS_AMOUNT,0)) FROM HRMS_PENSION_CREDIT_"+year+" " 
								+"	INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_PENSION_CREDIT_"+year+".PENS_CREDIT_CODE) " 
								+"	WHERE PENS_LEDGER_CODE="+ledgerObj[0][0]+" GROUP BY PENS_CREDIT_CODE,CREDIT_NAME ";
			Object[][]creditObj=getSqlModel().getSingleResult(creditQuery);
			
			
			String queryOther="SELECT  SUM(PENS_COMM_AMOUNT),SUM(PENS_REC_AMOUNT),SUM(PENS_MISC_RECOVERY) "
						+"	,SUM(PENS_ARREARS_AMOUNT)"
						+"	  FROM hrms_pension_"+year+" WHERE PENS_LEDGER_CODE="+ledgerObj[0][0]+"";
			Object[][] otherObj=getSqlModel().getSingleResult(queryOther);
			double otherArrear=0.0;
			double commutationAmt=0.0;
			double recoveryAmt=0.0;
			double miscAmt=0.0;
			if(otherObj!=null && otherObj.length>0){
				otherArrear=Double.parseDouble(String.valueOf(otherObj[0][3]));
				commutationAmt=Double.parseDouble(String.valueOf(otherObj[0][0]));
				recoveryAmt=Double.parseDouble(String.valueOf(otherObj[0][1]));
				miscAmt=Double.parseDouble(String.valueOf(otherObj[0][2]));
			}
			
			
			
			if(creditObj!=null && creditObj.length>0){
				int COUNT=1;
				Object[][]obj=new Object[creditObj.length+COUNT][5];	
				if(creditObj.length<3){
					obj=new Object[3][5];	
				}
				double totalCredit=0.0;
				double totalDebit=0.0;
				for (int i = 0; i < creditObj.length; i++) {
					obj[i][0]=creditObj[i][1];
					obj[i][1]=formatter.format(Double.parseDouble(String.valueOf(creditObj[i][2])));
					obj[i][2]="";
					obj[i][3]="";
					obj[i][4]="";	
					totalCredit+=Double.parseDouble(String.valueOf(obj[i][1]));
				}				
				
				obj[creditObj.length][0]="ARREARS";
				obj[creditObj.length][1]=formatter.format(otherArrear);
				obj[creditObj.length][2]="";
				obj[creditObj.length][3]="";
				obj[creditObj.length][4]="";	
				totalCredit+=Double.parseDouble(String.valueOf(otherArrear));
				
				obj[0][3]="COMMUTATION";
				obj[0][4]=formatter.format(recoveryAmt);	
				totalDebit+=Double.parseDouble(String.valueOf(recoveryAmt));
				
				obj[1][3]="RECOVERY";
				obj[1][4]=formatter.format(commutationAmt);	
				totalDebit+=Double.parseDouble(String.valueOf(commutationAmt));
				
				obj[2][3]="MISCELLANIUS";
				obj[2][4]=formatter.format(miscAmt);	
				totalDebit+=Double.parseDouble(String.valueOf(miscAmt));
				
				/*obj[creditObj.length][0]="GROSS PENSION";
				obj[creditObj.length][1]=Utility.twoDecimals(totalCredit);
				obj[creditObj.length][2]="";
				obj[creditObj.length][3]="PENSION DEDUCTION";
				obj[creditObj.length][4]="";
				
				obj[creditObj.length+1][0]="";
				
				
				obj[creditObj.length+2][0]="PENSION AMOUNT";
				obj[creditObj.length+2][1]=Utility.twoDecimals(totalCredit);
				obj[creditObj.length+3][0]="DEDUCTION";
				obj[creditObj.length+4][0]="PENSION PAYABLE";*/
				
				
				rg.tableBody(heading,obj,cellWidth,allignment );
				rg.addText("\n", 0, 0, 0);
				
				
				String []grossPension = {"GROSS PENSION",formatter.format(totalCredit),"","PENSION DEDUCTION",formatter.format(totalDebit)};
				String []pensionAmt = {"PENSION AMOUNT",formatter.format(totalCredit),"","",""};
				String []deductionAmt = {"DEDUCTION",formatter.format(totalDebit),"","",""};
				String []pensionPayable = {"PENSION PAYABLE",formatter.format(totalCredit-totalDebit),"","",""};
				
				rg.tableRowWithBG(grossPension,cellWidth,allignment,100 );
				rg.tableRowWithBG(pensionAmt,cellWidth,allignment,100 );
				rg.tableRowWithBG(deductionAmt,cellWidth,allignment,100 );
				rg.tableRowWithBG(pensionPayable,cellWidth,allignment,100 );
			}
			
		}
		
		
		
		
		rg.createReport(response);
	}
	
}
