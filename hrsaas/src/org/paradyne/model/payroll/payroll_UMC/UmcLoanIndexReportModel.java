package org.paradyne.model.payroll.payroll_UMC;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.payroll_UMC.UmcLoanIndexReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.struts.action.payroll.payroll_UMC.UmcLoanIndexReportAction;

public class UmcLoanIndexReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(UmcLoanIndexReportModel.class);
	public void getReport(UmcLoanIndexReport bean,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			String reportName = "Loan Index Report";
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					"Pdf", reportName);
			rg.addTextBold("Loan Index Report", 0, 1, 0);
			
			Object pfloanEMIDataObj[] = new Object[1];
			pfloanEMIDataObj[0] = bean.getEmpCode();

			String query = "  SELECT TO_CHAR(TO_DATE(EMI_MONTH,'MM'),'Month') ||'-'||EMI_YEAR, 'YES',TO_CHAR(EMI_AMOUNT,999999999.00),HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME " 
				+ " FROM HRMS_LOAN_PAID_EMI " 
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_PAID_EMI.EMP_ID) " 
				+ " WHERE HRMS_LOAN_PAID_EMI.EMP_ID=? AND EMI_AMOUNT>0"
				+ " ORDER BY EMI_MONTH, EMI_YEAR" ;

			Object pfEMILoanReportData[][] = getSqlModel().getSingleResult(
					query, pfloanEMIDataObj);

			Object[][] data = new Object[pfEMILoanReportData.length][4];
			String empName = "";
			double totalAmt = 0.0;
			double totalPaidAmt = 0.0;
			double totalBalAmt = 0.0;
			double totalPrePayAmt = 0.0;
			
			int emiPaidCounter = 0;
			if (pfEMILoanReportData != null && pfEMILoanReportData.length > 0) {
				emiPaidCounter=pfEMILoanReportData.length;
				for (int i = 0; i < data.length; i++) {
					data[i][0] = String.valueOf(i + 1);
					data[i][1] = String.valueOf(pfEMILoanReportData[i][0]);
					data[i][2] = String.valueOf(pfEMILoanReportData[i][1]);
					data[i][3] = String.valueOf(pfEMILoanReportData[i][2]);
						totalPaidAmt += Double.parseDouble(String
								.valueOf(pfEMILoanReportData[i][2]));
										
					totalAmt += Double.parseDouble(String
							.valueOf(pfEMILoanReportData[i][2]));
					empName = String.valueOf(pfEMILoanReportData[i][3]);
				}
				
			}
			/*
			 * CALCULATE UNPAID EMI SCHEDULE
			 */
			Double unpaidEmiCounter=0.0;
			unpaidEmiCounter = Math.ceil(Double.parseDouble(bean.getPendingAmt())/Double.parseDouble(bean.getEmiAmt()));
			//Integer.parseInt(String.valueOf(unpaidEmiCounter.intValue())) ;
			Object unpaidEmiObj[][]=new Object[Integer.parseInt(String.valueOf(unpaidEmiCounter.intValue()))][4];
			//if(unpaidEmiCounter==(Double.parseDouble(bean.getPendingAmt())/Double.parseDouble(bean.getEmiAmt()))){
			int unPaidMonth=Integer.parseInt(bean.getLastPaidMonth());
			int unPaidYear=Integer.parseInt(bean.getLastPaidYear());
			if(unPaidMonth==13){
				unPaidMonth =1;
				unPaidYear+=unPaidYear;
			}
			
				for (int i = 0; i < unpaidEmiObj.length; i++) {
					unPaidMonth=unPaidMonth+1;
					if(unPaidMonth==13){
						unPaidMonth =1;
						unPaidYear+=1;
					}
					unpaidEmiObj[i][0]= emiPaidCounter+i+1;
					unpaidEmiObj[i][1]= Utility.month(unPaidMonth)+"  -"+unPaidYear;		// month year
					unpaidEmiObj[i][2]= "No";
					if(i!=unpaidEmiObj.length-1){
				
					unpaidEmiObj[i][3]= bean.getEmiAmt();
					
					}else {
						if(unpaidEmiCounter==(Double.parseDouble(bean.getPendingAmt())/Double.parseDouble(bean.getEmiAmt()))){
							unpaidEmiObj[i][3]= bean.getEmiAmt();
						}else{
							unpaidEmiObj[i][3]= Double.parseDouble(bean.getPendingAmt())-((Double.parseDouble(bean.getEmiAmt())*(unpaidEmiCounter-1)));
						}
					}
					totalAmt +=Double.parseDouble(String.valueOf(unpaidEmiObj[i][3]));
				}
			//}
			
			/*
			 * CALCULATE UNPAID EMI SCHEDULE END
			 */
			data =Utility.joinArrays(data, unpaidEmiObj, 1, 0);
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String todayDate = dateFormat.format(date);

			rg.addText("Date :" + todayDate, 0, 2, 0);
			
			Object pfLoanDetailDataObj[] = new Object[1];
			pfLoanDetailDataObj[0] = bean.getEmpCode();

			String loanDtlQuery = " SELECT SUM(NVL(HRMS_LOAN_SUPPL_APPL.LOAN_AMOUNT,0)), NVL(TO_CHAR(HRMS_LOAN_NEW_APPL.LOAN_DATE,'DD-MM-YYYY'),' ')"
							+" FROM HRMS_LOAN_NEW_APPL "
							+" INNER JOIN HRMS_LOAN_SUPPL_APPL ON HRMS_LOAN_SUPPL_APPL.EMP_ID = HRMS_LOAN_NEW_APPL.EMP_ID"  
							+" WHERE HRMS_LOAN_SUPPL_APPL.EMP_ID=? AND LOAN_IS_REFUNDABLE='Y' GROUP BY NVL(TO_CHAR(HRMS_LOAN_NEW_APPL.LOAN_DATE,'DD-MM-YYYY'),' ')";

			Object loanDtlObj[][] = getSqlModel().getSingleResult(loanDtlQuery, pfLoanDetailDataObj);

			Object[][] employee = new Object[3][4];
			

			if (loanDtlObj != null && loanDtlObj.length > 0) {
				employee[0][0] = "Employee Name ";
				employee[0][1] = ": " + String.valueOf(empName);
				employee[0][2] = "Loan Amount  ";
				employee[0][3] = ": " + String.valueOf(loanDtlObj[0][0]);

				employee[1][0] = "Loan Application Date ";
				employee[1][1] = ": " + String.valueOf(loanDtlObj[0][1]);
				employee[1][2] = "Loan Type  ";
				employee[1][3] = ": Refundable" ;
				employee[2][0] = "Total EMI's Paid  ";
				employee[2][1] = ": " + emiPaidCounter + " / " +data.length;

				int[] cellwidth2 = { 25, 25, 25, 25 };
				int[] alignmnet2 = { 0, 0, 0, 0, };

				rg.tableBodyNoBorder(employee, cellwidth2, alignmnet2);
			}

			String[] colNames = { "Sr.No", "Month-Year", "EMI Amount",
					"Is Paid" };
			int[] cellWidth = { 5, 20, 25, 25 };
			int[] alignment = { 1, 0, 0, 2 };

			rg.tableBody(colNames, data, cellWidth, alignment);
			
		/*	Object prePaymentDataObj[] = new Object[1];
			prePaymentDataObj[0] = bean.getUserEmpId();

			String prePaymentQuery = " SELECT TO_CHAR(LOAN_PREPAY_DATE, 'DD-MM-YYYY') PAY_DATE, TO_CHAR(LOAN_PREPAY_AMOUNT,999999999.99) "
					+ " FROM HRMS_LOAN_CLOSURE "
					+ " WHERE LOAN_APPL_CODE = (SELECT DISTINCT LOAN_APPL_CODE FROM HRMS_LOAN_INSTALMENT  WHERE LOAN_EMP_ID=?) "
					+ " ORDER BY LOAN_PREPAY_DATE ASC ";

			Object[][] getPrePaymentDetails = getSqlModel().getSingleResult(prePaymentQuery,prePaymentDataObj);

			if (getPrePaymentDetails != null
					&& getPrePaymentDetails.length != 0) {
				Object[][] prePaymentData = new Object[getPrePaymentDetails.length][3];
				rg.addFormatedText("\n", 0, 0, 0, 0);

				Object[][] heading = new Object[1][1];
				int[] cells = { 25 };
				int[] align = { 0 };

				heading[0][0] = "Pre Payment Details :-";
				rg.tableBodyBold(heading, cells, align);

				String[] prePaymentcolumn = { "Sr.No", "Pre Payment Date",
						"Pre Payment Amount" };
				int[] cellwidth = { 10, 20, 20 };
				int[] prePaymentalignment = { 1, 1, 2 };

				for (int j = 0; j < getPrePaymentDetails.length; j++) {
					prePaymentData[j][0] = j + 1;
					prePaymentData[j][1] = String
							.valueOf(getPrePaymentDetails[j][0]);
					prePaymentData[j][2] = String
							.valueOf(getPrePaymentDetails[j][1]);
					totalPrePayAmt += Double.parseDouble(String
							.valueOf(getPrePaymentDetails[j][1]));
				} // end of for loop
				rg.tableBody(prePaymentcolumn, prePaymentData, cellwidth,
						prePaymentalignment);
			} // end of if
*/
			NumberFormat formatter = new DecimalFormat("#0.00");
			Object[][] totalObj = new Object[3][2];

			totalObj[0][0] = "Total amount to be paid";
			totalObj[1][0] = "Total paid amount (EMI)";
			//totalObj[2][0] = "Total paid amount (Prepayment)";
			totalObj[2][0] = "Total balance amount";

			totalBalAmt = totalAmt - totalPaidAmt;
			//totalAmt += totalPrePayAmt;

			totalObj[0][1] = formatter.format(totalAmt);
			totalObj[1][1] = formatter.format(totalPaidAmt);
			//totalObj[2][1] = formatter.format(totalPrePayAmt);
			totalObj[2][1] = formatter.format(totalBalAmt);
			rg.addText("", 1, 1, 0);
			int[] cellwidthTotal = { 70, 30 };
			int[] alignmentTotal = { 2, 2 };
			rg.tableBody(totalObj, cellwidthTotal, alignmentTotal);

			rg.createReport(response);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}

}
