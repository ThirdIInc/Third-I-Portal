package org.paradyne.model.kiosk;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.kiosk.KioskMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.leave.LeavePolicyModel;

public class KioskModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(KioskModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	/*public Object[][] checkUserCredential(String loginName) {
		Object result[][] = null;
		try {
			String query = " SELECT LOGIN_NAME,nvl(EMP_PHOTO,' ') "
					+ " FROM HRMS_LOGIN"
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LOGIN.EMP_ID) "
					+ " WHERE LOGIN_NAME=? ";
			result = getSqlModel().getSingleResult(query,
					new Object[] { loginName });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	public Object[][] selectLoginData(String login) {
		String selectSql = " SELECT LOGIN_NAME,HRMS_LOGIN.EMP_ID, PROFILE_CODE,LOGIN_CODE,EMP_ACTIVE,HRMS_EMP_OFFC.EMP_CENTER,ACCESS_PROFILE_CODE "
				+ " ,nvl(EMP_PHOTO,' '),EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_LOGIN  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_LOGIN.EMP_ID) "
				+ " WHERE LOGIN_NAME = ? ";
		return getSqlModel().getSingleResult(selectSql, new Object[] { login });
	}

	public Object[][] getComponyLogo() {

		Object[][] logoquery = null;
		try {
			String query = "SELECT COMPANY_LOGO,COMPANY_PPURL FROM  HRMS_COMPANY";
			logoquery = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return logoquery;
	}

	public String getKioskLeaveMonthReport(HttpServletResponse response,
			KioskMaster bean) {

		try {
			String reportName = "Leave Monthly Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					"Pdf", reportName);
			rg.addTextBold("Leave Monthly Report", 0, 1, 0);

			String currentDate = "";
			String policyCode = "";
			String year = "";
			String month = "";
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			currentDate = dateFormat.format(date);

			System.out.println("currentDate    " + currentDate);

			year = currentDate.substring(6, 10);

			System.out.println("year    " + year);

			month = currentDate.substring(3, 5);

			System.out.println("month    " + month);

			policyCode = getLeavePolicyCode(bean.getUserEmpId());
			
			Object leaveDataObj[] = new Object[6];
			leaveDataObj[0] = policyCode;
			leaveDataObj[1] = policyCode;
			leaveDataObj[2] = bean.getUserEmpId();
			leaveDataObj[3] = month;
			leaveDataObj[4] = year;
			leaveDataObj[5] = bean.getUserEmpId();
			
			 

			String query = " SELECT "
					+ " HRMS_EMP_OFFC.EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,HRMS_LEAVE.LEAVE_NAME  "
					+ ", NVL(LEAVE_OPENING_BALANCE,0)||' '||DECODE(TO_CHAR(LEAVE_HRS_OPENING_BALANCE,'HH24:MI'),'00:00',' ') "
					+ ", NVL(LEAVE_CLOSING_BALANCE,0)||' '||DECODE(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),'00:00',' ') , "
					+ "  NVL(SUM(NVL(LEAVE_DAYS,0)),0) "
					+ ", NVL(LEAVE_ONHOLD,0)||' '||DECODE(TO_CHAR(LEAVE_HRS_ONHOLD,'HH24:MI'),'00:00',' ') "
					+ " FROM HRMS_LEAVE_DTLHISTORY "
					+ " RIGHT JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE = HRMS_LEAVE_DTLHISTORY.LEAVE_CODE "
					+ "AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE=?)"
					+ "INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE "
					+ "AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE=? AND HRMS_LEAVE_BALANCE.EMP_ID =?)"
					+ "INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  =  HRMS_LEAVE_BALANCE.EMP_ID) "
					+ "INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID= HRMS_LEAVE_BALANCE.LEAVE_CODE) "
					+ " WHERE LEAVE_MONTH=? AND LEAVE_YEAR=? AND HRMS_LEAVE_DTLHISTORY.EMP_ID=? group by HRMS_LEAVE.LEAVE_NAME,HRMS_EMP_OFFC.EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,HRMS_LEAVE.LEAVE_NAME "
					+ ", NVL(LEAVE_OPENING_BALANCE,0)||' '||DECODE(TO_CHAR(LEAVE_HRS_OPENING_BALANCE,'HH24:MI'),'00:00',' ') "
					+ ", NVL(LEAVE_CLOSING_BALANCE,0)||' '||DECODE(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),'00:00',' ')  "
					+ ", NVL(LEAVE_ONHOLD,0)||' '||DECODE(TO_CHAR(LEAVE_HRS_ONHOLD,'HH24:MI'),'00:00',' ') ";
 	 
			 Object leaveData[][] = getSqlModel().getSingleResult(query, leaveDataObj);
			
			Object[][] finalLeaveData = null;
			if (leaveData != null && leaveData.length > 0)
				finalLeaveData = new Object[leaveData.length][8];
			else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
				rg.createReportForKiosk(response);
			}

			String empTok = "";
			String name = "";
			int s = 1;

			if (leaveData != null && leaveData.length > 0) {
				for (int i = 0; i < leaveData.length; i++) {
					if (name.equals(String.valueOf(leaveData[i][1]))
							&& empTok.equals(String.valueOf(leaveData[i][0]))) {
						finalLeaveData[i][0] = "";
						finalLeaveData[i][1] = "";
						finalLeaveData[i][2] = "";
					}// end of if
					else {
						finalLeaveData[i][0] = s++;
						finalLeaveData[i][1] = leaveData[i][0];
						finalLeaveData[i][2] = leaveData[i][1];
					}// end of else
					finalLeaveData[i][3] = leaveData[i][2];
					finalLeaveData[i][4] = leaveData[i][3];
					finalLeaveData[i][5] = leaveData[i][4];
					finalLeaveData[i][6] = leaveData[i][5];
					finalLeaveData[i][7] = leaveData[i][6];
					name = String.valueOf(leaveData[i][1]);
					empTok = String.valueOf(leaveData[i][0]);
				}// end of for

				String[] colNames = { "Sr. No.", "Employee Id", "Name",
						"Leave Type", "Leaves Entitled", "Available Balance",
						"Leaves Used","Leaves On-hold" };
				int[] cellWidth = { 8, 30, 30, 30, 25, 25, 25 ,25};
				int[] alignment = { 0, 0, 0, 0, 2,2,2,2 };

				if (finalLeaveData != null && finalLeaveData.length > 0) {
					rg.addText("\n", 0, 0, 0);
					rg
							.tableBody(colNames, finalLeaveData, cellWidth,
									alignment);
				} else {
					rg.addTextBold("There is no data to display.", 0, 1, 0);
				}// end of else
			} else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}// 

			rg.createReportForKiosk(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getLeavePolicyCode(String empId) {
		String leavePolicyCode = "";
		try {
			LeavePolicyModel model = new LeavePolicyModel();
			model.initiate(context, session);
			System.out.println("Employee code is-------------------------"
					+ empId);
			leavePolicyCode = model.getLeavePolicy(empId);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in getLeavePolicyCode------------" + e);
		}
		return leavePolicyCode;
	}// end of getLeavePolicyCode

	public String getKioskLICPolicyReport(HttpServletResponse response,
			KioskMaster bean) {

		try {
			String reportName = "LIC Policy Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					"Pdf", reportName);
			rg.addTextBold("LIC Policy Report", 0, 1, 0);
			
			Object licPolicyDataObj[] = new Object[1];
			licPolicyDataObj[0] = bean.getUserEmpId();

			String query = "SELECT LIC_NAME,LIC_POLICY_NUMBER, LIC_INSURANCE_AMOUNT, LIC_MONTHLY_PREMIUM FROM "
					+ " HRMS_LIC WHERE EMP_ID=? ORDER BY LIC_NAME ";

			Object licReportData[][] = getSqlModel().getSingleResult(query, licPolicyDataObj);

			Object licFinalData[][] = new Object[licReportData.length][5];
			double total=0.0 ;
			
			for (int i = 0; i < licFinalData.length; i++) {
				licFinalData[i][0] = String.valueOf(i + 1);
				licFinalData[i][1] = licReportData[i][0];
				licFinalData[i][2] = licReportData[i][1];
				licFinalData[i][3] = licReportData[i][2];
				licFinalData[i][4] = licReportData[i][3];
				total+=Double.parseDouble(String.valueOf(licFinalData[i][4]));
			}

			String[] colNames = { "Sr.No", "L.I.C Name", "Policy No",
					"Insurance Amount", "Premium Amount" };
			int[] cellWidth = { 10, 25, 15, 25, 25 };
			int[] alignment = { 1, 0, 0, 2, 2 };

			if (licReportData != null && licReportData.length > 0) {
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(colNames, licFinalData, cellWidth, alignment);
				rg.addText("\n", 0, 0, 0);
				rg.addTextBold("Total Premium Amount: "+total+" ", 0, 2, 0   );
			} else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}// end of else

			rg.createReportForKiosk(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getKioskPFLoanEMIReportOld(HttpServletResponse response,
			KioskMaster bean) {
		try {
			System.out.println("--In Kiosk Model----bean.getUserEmpId()------"
					+ bean.getUserEmpId());

			String reportName = "Loan Index Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					"Pdf", reportName);
			rg.addTextBold("Loan Index Report", 0, 1, 0);
			
			Object pfloanEMIDataObj[] = new Object[1];
			pfloanEMIDataObj[0] = bean.getUserEmpId();

			String query = "  SELECT TO_CHAR(TO_DATE(LOAN_INSTAL_MONTH,'MM'),'MON') ||'-'||LOAN_INSTAL_YEAR, "
					+ " TO_CHAR(LOAN_PRINCIPLE_AMT,999999999.99), DECODE(LOAN_INSTALLMENT_IS_PAID, 'Y', 'YES', 'N', 'NO') "
					+ " ,TO_CHAR(LOAN_INSTAL_AMT,999999999.99),HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME "
					+ " FROM HRMS_LOAN_INSTALMENT "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_INSTALMENT.LOAN_EMP_ID) "
					+ "	 WHERE LOAN_APPL_CODE=(SELECT DISTINCT LOAN_APPL_CODE FROM HRMS_LOAN_INSTALMENT  WHERE LOAN_EMP_ID=?) "
					+ "  ORDER BY LOAN_INSTAL_YEAR, LOAN_INSTAL_MONTH ";

			Object pfEMILoanReportData[][] = getSqlModel().getSingleResult(
					query, pfloanEMIDataObj);

			Object[][] data = new Object[pfEMILoanReportData.length][5];
			String empName = "";
			double totalAmt = 0.0;
			double totalPaidAmt = 0.0;
			double totalBalAmt = 0.0;
			double totalPrePayAmt = 0.0;
			
			int emiPaidCounter = 0;
			if (pfEMILoanReportData != null && pfEMILoanReportData.length > 0) {
				for (int i = 0; i < data.length; i++) {
					data[i][0] = String.valueOf(i + 1);
					data[i][1] = String.valueOf(pfEMILoanReportData[i][0]);
					data[i][2] = String.valueOf(pfEMILoanReportData[i][1]);
					data[i][3] = String.valueOf(pfEMILoanReportData[i][2]);
					
					if(String.valueOf(data[i][3]).equalsIgnoreCase("Yes")){
						emiPaidCounter++;
						totalPaidAmt += Double.parseDouble(String
								.valueOf(pfEMILoanReportData[i][3]));
					}
					
					totalAmt += Double.parseDouble(String
							.valueOf(pfEMILoanReportData[i][3]));
					empName = String.valueOf(pfEMILoanReportData[i][4]);
				}
				
			}
			
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String todayDate = dateFormat.format(date);

			rg.addText("Date :" + todayDate, 0, 2, 0);
			
			Object pfLoanDetailDataObj[] = new Object[1];
			pfLoanDetailDataObj[0] = bean.getUserEmpId();

			String loanDtlQuery = " SELECT NVL(LOAN_SANCTION_AMOUNT,0), NVL(TO_CHAR(LOAN_SANCTION_DATE,'DD-MM-YYYY'),' '), DECODE(LOAN_PF_TYPE,'Y','Refundable','N','Non-Refundable'),NVL(LOAN_INTALMENT_NUMBERS,0)" 
								+ " FROM HRMS_LOAN_PROCESS INNER JOIN HRMS_LOAN_APPLICATION ON HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_PROCESS.LOAN_APPL_CODE " 
								+ " WHERE HRMS_LOAN_PROCESS.LOAN_APPL_CODE=(SELECT HRMS_LOAN_APPLICATION.LOAN_APPL_CODE FROM HRMS_LOAN_APPLICATION WHERE LOAN_EMP_ID=?)";

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
				employee[1][3] = ": " + String.valueOf(loanDtlObj[0][2]);
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
			
			Object prePaymentDataObj[] = new Object[1];
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

			NumberFormat formatter = new DecimalFormat("#0.00");
			Object[][] totalObj = new Object[4][2];

			totalObj[0][0] = "Total amount to be paid";
			totalObj[1][0] = "Total paid amount (EMI)";
			totalObj[2][0] = "Total paid amount (Prepayment)";
			totalObj[3][0] = "Total balance amount";

			totalBalAmt = totalAmt - totalPaidAmt;
			totalAmt += totalPrePayAmt;

			totalObj[0][1] = formatter.format(totalAmt);
			totalObj[1][1] = formatter.format(totalPaidAmt);
			totalObj[2][1] = formatter.format(totalPrePayAmt);
			totalObj[3][1] = formatter.format(totalBalAmt);
			rg.addText("", 1, 1, 0);
			int[] cellwidthTotal = { 70, 30 };
			int[] alignmentTotal = { 2, 2 };
			rg.tableBody(totalObj, cellwidthTotal, alignmentTotal);

			rg.createReportForKiosk(response);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

public void getKioskPFLoanEMIReport(HttpServletResponse response,
		KioskMaster bean){


	String reportName = "Loan Index Report";
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
			"Pdf", reportName);
	rg.addTextBold("Loan Index Report", 0, 1, 0);
	
	Object pfloanEMIDataObj[] = new Object[1];
	pfloanEMIDataObj[0] = bean.getUserEmpId();
	
	String emiDTLQuery="SELECT PENDING_LOAN_AMT, EMI_AMOUNT, EMI_PAID_MONTH, EMI_PAID_YEAR FROM HRMS_LOAN_NEW_APPL WHERE EMP_ID ="+bean.getUserEmpId();
	Object[][]emiDTLObj = getSqlModel().getSingleResult(emiDTLQuery);
	String query = "  SELECT TO_CHAR(TO_DATE(EMI_MONTH,'MM'),'Month') ||'-'||EMI_YEAR, 'YES',TO_CHAR(EMI_AMOUNT,999999999.00),HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME " 
		+ " FROM HRMS_LOAN_PAID_EMI " 
		+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_PAID_EMI.EMP_ID) " 
		+ " WHERE HRMS_LOAN_PAID_EMI.EMP_ID=? AND EMI_AMOUNT>0"
		+ " ORDER BY CASE WHEN EMI_MONTH <10 THEN TO_NUMBER(NVL(EMI_YEAR,0)||'0'||NVL(EMI_MONTH,0)) ELSE TO_NUMBER(NVL(EMI_YEAR,0)||''||NVL(EMI_MONTH,0))END" ;

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
	String pendingAmt = String.valueOf(emiDTLObj[0][0]);
	String emiAmt = String.valueOf(emiDTLObj[0][1]);
	String lastPaidMonth = String.valueOf(emiDTLObj[0][2]);
	String lastPaidYear = String.valueOf(emiDTLObj[0][3]);
	unpaidEmiCounter = Math.ceil(Double.parseDouble(pendingAmt)/Double.parseDouble(emiAmt));
	//Integer.parseInt(String.valueOf(unpaidEmiCounter.intValue())) ;
	Object unpaidEmiObj[][]=new Object[Integer.parseInt(String.valueOf(unpaidEmiCounter.intValue()))][4];
	//if(unpaidEmiCounter==(Double.parseDouble(bean.getPendingAmt())/Double.parseDouble(bean.getEmiAmt()))){
	int unPaidMonth=Integer.parseInt(lastPaidMonth);
	int unPaidYear=Integer.parseInt(lastPaidYear);
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
		
			unpaidEmiObj[i][3]= emiAmt;
			
			}else {
				if(unpaidEmiCounter==(Double.parseDouble(pendingAmt)/Double.parseDouble(emiAmt))){
					unpaidEmiObj[i][3]=emiAmt;
				}else{
					unpaidEmiObj[i][3]= Double.parseDouble(pendingAmt)-((Double.parseDouble(emiAmt)*(unpaidEmiCounter-1)));
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
	pfLoanDetailDataObj[0] = bean.getUserEmpId();

	String loanDtlQuery = " SELECT SUM(NVL(HRMS_LOAN_SUPPL_APPL.LOAN_AMOUNT,0)), NVL(TO_CHAR(HRMS_LOAN_NEW_APPL.LOAN_DATE,'DD-MM-YYYY'),' ')"
					+" FROM HRMS_LOAN_NEW_APPL "
					+" INNER JOIN HRMS_LOAN_SUPPL_APPL ON HRMS_LOAN_SUPPL_APPL.EMP_ID = HRMS_LOAN_NEW_APPL.EMP_ID"  
					+" WHERE HRMS_LOAN_SUPPL_APPL.EMP_ID=? AND LOAN_IS_REFUNDABLE='Y' GROUP BY NVL(TO_CHAR(HRMS_LOAN_NEW_APPL.LOAN_DATE,'DD-MM-YYYY'),' ')";

	Object loanDtlObj[][] = getSqlModel().getSingleResult(loanDtlQuery, pfLoanDetailDataObj);

	Object[][] employee = new Object[1][4];
	

	if (loanDtlObj != null && loanDtlObj.length > 0) {
		employee[0][0] = "Employee Name ";
		employee[0][1] = ": " + String.valueOf(empName);
		/*employee[0][2] = "Loan Amount  ";
		employee[0][3] = ": " + String.valueOf(loanDtlObj[0][0]);*/

		/*employee[1][0] = "Loan Application Date ";
		employee[1][1] = ": " + String.valueOf(loanDtlObj[0][1]);*/
		/*employee[1][2] = "Loan Type  ";
		employee[1][3] = ": Refundable" ;*/
		employee[0][2] = "Total EMI's Paid  ";
		employee[0][3] = ": " + emiPaidCounter + " / " +data.length;

		int[] cellwidth2 = { 25, 25, 25, 25 };
		int[] alignmnet2 = { 0, 0, 0, 0 };

		rg.tableBodyNoBorder(employee, cellwidth2, alignmnet2);
	}

	String[] colNames = { "Sr.No", "Month-Year", "EMI Amount",
			"Is Paid" };
	int[] cellWidth = { 5, 20, 25, 25 };
	int[] alignment = { 1, 0, 0, 2 };

	rg.tableBody(colNames, data, cellWidth, alignment);
	

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

}
	public String getPFBalanceReport(KioskMaster bean,
			HttpServletRequest request) {
		String pfBalance="";
		String frmYear="";
		double openingBalance=0.0;
		double pfSubAmt =0.0;
		double pfRefundAmt =0.0;
		double pfLoanAmt =0.0;
		double pfProgAmt =0.0;
		int noOfMonth =0;
		int year =0;
		String currentDate = "";
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		currentDate = dateFormat.format(date);

		System.out.println("currentDate    " + currentDate);

		frmYear = currentDate.substring(6, 10);
		String dateSplit[] = currentDate.split("-");
		System.out.println("year     " + frmYear);
		
		int toMonth =Integer.parseInt(dateSplit[1]);
		int length= 0;
		if(toMonth <4){
			length = 9 + toMonth;
		}else{
			length= toMonth - 3;
		}//end of if-else block
		
		
		
		if(Integer.parseInt(dateSplit[1]) >3){
			frmYear = String.valueOf(Integer.parseInt(dateSplit[2]) );
			noOfMonth = Integer.parseInt(dateSplit[1]) - 3;
			year = Integer.parseInt(dateSplit[2]);
		}else{
			frmYear = String.valueOf(Integer.parseInt(dateSplit[2]) -1);
			noOfMonth = Integer.parseInt(dateSplit[1]) + 9;
			year = Integer.parseInt(dateSplit[2])-1;
		}
		String opnBalanceQuery="SELECT NVL(PF_OPENING_BAL,'0') FROM HRMS_PF_LEDGER WHERE PF_EMPID="+bean.getUserEmpId()+" AND "
								+" PF_FROM_YEAR ="+frmYear;
		
		Object [][]opnBalanceObj =getSqlModel().getSingleResult(opnBalanceQuery);
		
		try {
			openingBalance = Double.parseDouble(String.valueOf(opnBalanceObj[0][0]));
		} catch (Exception e) {
			// TODO: handle exception
		} 
		String pfSubQuery ="";
		String pfRefundQuery ="";
		String pfLoanQuery ="";
		int month =0;
		for (int i = 0; i < noOfMonth; i++) {
			month =  (4+i) % 12;
			if (month ==0)
			month =12;
			if(month==1){
				year +=1;
			}
			pfSubQuery ="SELECT NVL(SUM(SAL_AMOUNT),0) FROM HRMS_SAL_DEBITS_"+year
				+" INNER JOIN HRMS_PFTRUST_CONF ON(PFT_DEBIT_CODE=SAL_DEBIT_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
				+" INNER JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="+month+" AND LEDGER_YEAR="+year+" AND LEDGER_STATUS='SAL_FINAL')"
				+" WHERE EMP_ID ="+bean.getUserEmpId();
			
			Object [][]pfSubObj =getSqlModel().getSingleResult(pfSubQuery);
			
			try {
				pfSubAmt += Double.parseDouble(String.valueOf(pfSubObj[0][0]));
			} catch (Exception e) {
				// TODO: handle exception
			}
			pfRefundQuery ="SELECT NVL(SUM(SAL_AMOUNT),0) FROM HRMS_SAL_DEBITS_"+year
				+" INNER JOIN HRMS_LOAN_MASTER ON (LOAN_DEBIT_CODE=SAL_DEBIT_CODE)"
				+" INNER JOIN HRMS_PFTRUST_CONF ON(PFT_LOAN_CODE=LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
				+" INNER JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="+month+" AND LEDGER_YEAR="+year+"  AND LEDGER_STATUS='SAL_FINAL')"
				+" WHERE EMP_ID ="+bean.getUserEmpId();
			
			Object [][]pfRefundObj =getSqlModel().getSingleResult(pfRefundQuery);
			
			try {
				pfRefundAmt += Double.parseDouble(String.valueOf(pfRefundObj[0][0]));
			} catch (Exception e) {
				// TODO: handle exception
			}
			String monthString =""+month;
			if(month<10){
				monthString ="0"+month;
			}
			pfLoanQuery ="SELECT SUM(LOAN_AMOUNT),EMP_ID FROM HRMS_LOAN_SUPPL_APPL "
						+" WHERE EMP_ID="+bean.getUserEmpId()+" AND TO_CHAR(LOAN_DATE,'MM-YYYY')='"+monthString+"-"+year+"' GROUP BY EMP_ID";
						/*+" INNER JOIN HRMS_LOAN_APPLICATION ON(HRMS_LOAN_APPLICATION.LOAN_APPL_CODE=HRMS_LOAN_PROCESS.LOAN_APPL_CODE)"
						+" INNER JOIN HRMS_PFTRUST_CONF ON(PFT_LOAN_CODE=LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
						+" WHERE TO_CHAR(LOAN_PAYMENT_DATE,'MM-YYYY') ='"+monthString+"-"+year+"' AND LOAN_EMP_ID ="+bean.getUserEmpId();
			
			String pfLoanAmtQuery ="SELECT SUM(LOAN_AMOUNT),EMP_ID FROM HRMS_LOAN_SUPPL_APPL "
				+" WHERE EMP_ID="+empId+" AND TO_CHAR(LOAN_DATE,'MM-YYYY')='"+month+"-"+year+"' GROUP BY EMP_ID";*/

		//	Object [][]pfLoanAmt =getSqlModel().getSingleResult(pfLoanAmtQuery);
			Object [][]pfLoanObj =getSqlModel().getSingleResult(pfLoanQuery);
			
			try {
				pfLoanAmt += Double.parseDouble(String.valueOf(pfLoanObj[0][0]));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		
		
		logger.info("openingBalance=== :"+openingBalance);
		logger.info("pfSubAmt=== :"+pfSubAmt);
		logger.info("pfRefundAmt=== :"+pfRefundAmt);
		logger.info("pfLoanAmt=== :"+pfLoanAmt);
		
		/*pfProgAmt=(openingBalance-pfLoanAmt+ pfSubAmt + pfRefundAmt)*1.08;*/
		//logger.info("pfProgAmt=== :"+pfProgAmt);
		pfBalance = formatter.format(openingBalance + pfSubAmt + pfRefundAmt -pfLoanAmt);
		//pfBalance=formatter.format(pfProgAmt);
		logger.info("pfBalance==="+pfBalance);
		Object [][]pfBalanceData= getSqlModel().getSingleResult("SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"+pfBalance+" FROM HRMS_EMP_OFFC WHERE EMP_ID="+bean.getUserEmpId());
		request.setAttribute("pfBalanceData", pfBalanceData);
		return  null;
	}

	public Object[][] saveLoginSessionDetails(String ipAddressOne, String ipAddressTwo,
			String ipAddressThree, String ipAddressFour, int portNumber, String loginCode) {
		// TODO Auto-generated method stub
		Object[][] objectData =null;
		try {
			String insertSql = " INSERT INTO HRMS_LOGIN_SESSION(LOGIN_ID ,LOGIN_DATETIME,LOGIN_IP_ADDR1,LOGIN_IP_ADDR2,LOGIN_IP_ADDR3,LOGIN_IP_ADDR4,LOGIN_PORT,LOGIN_SUCCESS_FLAG)"
					+ " VALUES(?,SYSDATE,?,?,?,?,?,'Y') ";
			Object loginCd[][] = new Object[1][6];
			loginCd[0][0] = loginCode;
			loginCd[0][1] = ipAddressOne;
			loginCd[0][2] = ipAddressTwo;
			loginCd[0][3] = ipAddressThree;
			loginCd[0][4] = ipAddressFour;
			loginCd[0][5] = portNumber;
			
			getSqlModel().singleExecute(insertSql, loginCd);
			
			String visitString = "SELECT COUNT(*),TO_CHAR(SYSDATE,'Day DD Mon YYYY HH:MI:SS') FROM hrms_login_SESSION ";
			objectData=getSqlModel().getSingleResult(visitString);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return objectData ;
	}
}
