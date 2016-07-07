/**
 * 
 */
package org.paradyne.model.loan;

import java.awt.Color;
import java.io.DataOutput;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Loan.LoanIndex;
import org.paradyne.bean.Loan.LoanMISApplicationReport;
import org.paradyne.bean.Loan.LoanProcessing;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;

import org.paradyne.lib.ireportV2.TableDataSet; 

import com.lowagie.text.Font;

/**
 * @author mangeshj
 * @date 14-07-2008
 * LoanIndexModel class to write business logic to generate the loan index report
 */
public class LoanIndexModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoanIndexModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	/* method name : checkNull 
	 * purpose     : to check whether the value of the string parameter is null or not.
	 * 					if yes then it will set the value of that string to blank
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}	//end of if 
		else {
			return result;
		}	//end of else
	}
	
	public String checkNullValue(final String result) {
		if (result == null || "null".equals(result)) {
			return "0";
		} else {
			return result;
		}
	}
	public void getReport(LoanIndex bean,
			HttpServletResponse response, HttpServletRequest request,
			 String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReportType();
			rds.setReportType(type);
			String fileName = "Loan Index Report  "+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("Loan Index Report ");
			rds.setPageSize("A4");
			rds.setPageOrientation("portrait");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setShowPageNo(true);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", request.getContextPath()+"/loan/LoanIndexReport_input.action");
			}
			rg = getReport(rg, bean);
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
	
private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, LoanIndex bean) {
		try {
			Object[][] loanTypeDetails = new Object[2][4];// new-------------->
			loanTypeDetails[0][0] = "Employee Name :";
			loanTypeDetails[0][1] = checkNull(bean.getEmpname());
			loanTypeDetails[0][2] = "Loan Type :";
			loanTypeDetails[0][3] = checkNull(bean.getLoanType());
			
			loanTypeDetails[1][0] = "Application Date :";
			loanTypeDetails[1][1] = bean.getAppDate();
			loanTypeDetails[1][2] = "";
			loanTypeDetails[1][3] = "";
			
			TableDataSet loanTypeInfoTable = new TableDataSet();
			loanTypeInfoTable.setData(loanTypeDetails);
			loanTypeInfoTable.setCellWidth(new int[] { 8, 30, 8, 30 });
			loanTypeInfoTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
			loanTypeInfoTable.setBorder(true);
			loanTypeInfoTable.setBorderDetail(0);
			rg.addTableToDoc(loanTypeInfoTable);
			
			Object[][] installHeadData = new Object[1][1];
			installHeadData[0][0] = "Installments Details " ;
			int[] cellWidthDateHeader = { 100 };
			int[] cellAlignDateHeader = { 0 };
			TableDataSet installHeadDtlData = new TableDataSet();
			installHeadDtlData.setData(installHeadData);
			installHeadDtlData.setCellWidth(cellWidthDateHeader);
			installHeadDtlData.setCellAlignment(cellAlignDateHeader);  
			installHeadDtlData.setBodyFontStyle(1);  
			installHeadDtlData.setBorderDetail(0);
			installHeadDtlData.setBlankRowsAbove(1);
			//rg.addTableToDoc(installHeadDtlData);
			
			String[] header = null;
			header = new String[6];
				header[0] = "Sn.";
				header[1] = "Month-Year";
				header[2] = "Principle Amount";
				header[3] = "Interest Amount";
				header[4] = "EMI Amount";
				header[5] = "Is Paid";
			
			int[] bcellAlign = new int[header.length];
			int[] bcellWidth = new int[header.length];
			boolean[] bcellwrap = new boolean[header.length];
			for (int i = 0; i < header.length; i++) {
				// bcellAlign[i] = 1;
				// bcellWidth[i] = 40;
				if (i == 0) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 8;
					bcellwrap[i]=true;
				} else if (i == 1) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 20;
					bcellwrap[i]=true;
				} else if (i == 2) {
					bcellAlign[i] = 2;
					bcellWidth[i] = 20;
					bcellwrap[i]=true;
				} else if (i == 3) {
					bcellAlign[i] = 2;
					bcellWidth[i] = 20;
					bcellwrap[i]=true;
				}else if (i == 4) {
					bcellAlign[i] = 2;
					bcellWidth[i] = 20;
					bcellwrap[i]=true;
				}
				else  {
					bcellAlign[i] = 0;
					bcellWidth[i] = 20;
					bcellwrap[i]=true;
				}
			}
			int installCount=0;
			
			String installQuery =" SELECT  TO_CHAR(TO_DATE(LOAN_INSTAL_MONTH,'MM'),'MON') ||'-'||LOAN_INSTAL_YEAR, "
				+" TO_CHAR(LOAN_PRINCIPLE_AMT,999999999.99), TO_CHAR(LOAN_INSTAL_INTEREST,999999999.99), TO_CHAR(LOAN_INSTAL_AMT,999999999.99), DECODE(LOAN_INSTALLMENT_IS_PAID, 'Y', 'Yes', 'N', 'No') "
				+" ,LOAN_INSTAL_DAY FROM HRMS_LOAN_INSTALMENT WHERE LOAN_APPL_CODE="+bean.getLoanAppCode()+ " ORDER BY LOAN_INSTAL_YEAR, LOAN_INSTAL_MONTH";
			Object[][] installDetail = getSqlModel().getSingleResult(installQuery);
			
			if(installDetail!=null && installDetail.length>0){
				installCount=installDetail.length;
			}
			Object[][] objDebitTotalTabularData = new Object[installCount][header.length];
			
			double totalAmt=Double.parseDouble(bean.getSanctionAmount());
			double totalPaidAmt=0.0;
			double totalBalAmt=0.0;
			double totalPrePayAmt=0.0;
			
			int count = 1;
			if (installDetail != null && installDetail.length > 0) {
				int MAINCOUNT = 0;
				for (int i = 0; i < installDetail.length; i++) {
					objDebitTotalTabularData[MAINCOUNT][0] = count++;
					objDebitTotalTabularData[MAINCOUNT][1] = String.valueOf(installDetail[i][0]);
					objDebitTotalTabularData[MAINCOUNT][2] = formatter.format(Double.parseDouble(checkNullValue(String.valueOf(installDetail[i][1]))));

					objDebitTotalTabularData[MAINCOUNT][3] = formatter.format(Double.parseDouble(checkNullValue(String.valueOf(installDetail[i][2]))));
					objDebitTotalTabularData[MAINCOUNT][4] = formatter.format(Double.parseDouble(checkNullValue(String.valueOf(installDetail[i][3]))));
					objDebitTotalTabularData[MAINCOUNT][5] = String.valueOf(installDetail[i][4]);
					
					//totalAmt+=Double.parseDouble(String.valueOf(installDetail[i][3]));
					if(String.valueOf(installDetail[i][4]).equalsIgnoreCase("Yes")){
						totalPaidAmt +=Double.parseDouble(String.valueOf(installDetail[i][3]));
					}
					
				MAINCOUNT++;
				}
				TableDataSet loanInstallInfoTable = new TableDataSet();
				loanInstallInfoTable.setHeader(header);
				loanInstallInfoTable.setHeaderBorderDetail(3);
				loanInstallInfoTable.setData(objDebitTotalTabularData);
				loanInstallInfoTable.setBorderDetail(3);
				loanInstallInfoTable.setHeaderTable(true);
				loanInstallInfoTable.setCellWidth(bcellWidth);
				loanInstallInfoTable.setCellAlignment(bcellAlign);
				loanInstallInfoTable.setBorder(true);
				rg.addTableToDoc(loanInstallInfoTable);
				
			rg.seperatorLine();	
			}
			String prePaymentQuery = "SELECT TO_CHAR(LOAN_PREPAY_DATE, 'DD-MM-YYYY') PAY_DATE, TO_CHAR(LOAN_PREPAY_AMOUNT,999999999.99) "
				 +" FROM HRMS_LOAN_CLOSURE "
				 +" WHERE LOAN_APPL_CODE = "+bean.getLoanAppCode()
				 +" ORDER BY LOAN_PREPAY_DATE ASC";

			Object [][] getPrePaymentDetails = getSqlModel().getSingleResult(prePaymentQuery);
			
			if(getPrePaymentDetails != null && getPrePaymentDetails.length != 0){
					Object [][] prePaymentData = new Object [getPrePaymentDetails.length][3];
					
					String [] prePaymentcolumn = { "Sr.No", "Pre Payment Date", "Pre Payment Amount"};
					int [] cellwidth = { 10, 20, 20};
					int [] alignment = { 1, 1, 2};
					
					for (int j = 0; j < getPrePaymentDetails.length; j++) {
					prePaymentData [j][0] = j+1;
					prePaymentData [j][1] = String.valueOf(getPrePaymentDetails[j][0]);
					prePaymentData [j][2] = String.valueOf(getPrePaymentDetails[j][1]);
					totalPrePayAmt+= Double.parseDouble(String.valueOf(getPrePaymentDetails[j][1]));
					}	//end of for loop
					
					installHeadData[0][0] = "Pre Payment Details :- " ;
					int[] cellWidthHeader = { 100 };
					int[] cellAlignHeader = { 0 };
					TableDataSet installDtlData = new TableDataSet();
					installDtlData.setData(prePaymentData);
					installDtlData.setCellWidth(cellwidth);
					installDtlData.setCellAlignment(alignment);  
					installDtlData.setBodyFontStyle(1);  
					installHeadDtlData.setBorderDetail(0);
					installHeadDtlData.setBlankRowsAbove(1);
					rg.addTableToDoc(installHeadDtlData);
				}
			
			totalBalAmt=totalAmt-(totalPaidAmt+totalPrePayAmt);
			//totalAmt +=totalPrePayAmt;
			
			
			Object[][] totalLoanDetails = new Object[3][4];// new-------------->
			totalLoanDetails[0][0] = "Total No of Installments :";
			totalLoanDetails[0][1] = installCount;
			totalLoanDetails[0][2] = "Total Amount to Be Paid :";
			totalLoanDetails[0][3] = formatter.format(totalAmt);
			
			totalLoanDetails[1][0] = "Total Paid Amount (EMI) :";
			totalLoanDetails[1][1] = formatter.format(totalPaidAmt);
			totalLoanDetails[1][2] = "Prepayment Amount :";
			totalLoanDetails[1][3] = formatter.format(totalPrePayAmt);
			
			totalLoanDetails[2][0] = "Total Balance Amount :";
			totalLoanDetails[2][1] = formatter.format(totalBalAmt);
			totalLoanDetails[2][2] = "";
			totalLoanDetails[2][3] = "";
			
			TableDataSet totalLoanInfoTable = new TableDataSet();
			totalLoanInfoTable.setData(totalLoanDetails);
			totalLoanInfoTable.setCellWidth(new int[] { 15, 30, 15, 30 });
			totalLoanInfoTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
			totalLoanInfoTable.setBorderDetail(0);
			totalLoanInfoTable.setBodyFontStyle(1);
			totalLoanInfoTable.setBlankRowsAbove(1);
			rg.addTableToDoc(totalLoanInfoTable);
			
			/*}else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

}
