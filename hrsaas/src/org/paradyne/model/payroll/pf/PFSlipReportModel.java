package org.paradyne.model.payroll.pf;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.pf.PFSlipReport;
import org.paradyne.lib.ModelBase;

public class PFSlipReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(PFSlipReportModel.class);
	
	String sCompanyName = null;
	
	public void generateReport(PFSlipReport bean, HttpServletResponse response, int check) {

		String sReportType = "";
		if (bean.getSSelectedReportType().equalsIgnoreCase("pdf")) {
			sReportType = "Pdf";
		} else if (bean.getSSelectedReportType().equalsIgnoreCase("word")) {
			sReportType = "Txt";
		}

		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				sReportType, "PF Slip Report");

		/* Get Company Name */
		getCompanyName ();
		
		if (bean.getSSelectedReportType().equalsIgnoreCase("pdf")) {
			
			Object[][] outputData = getSqlModel().getSingleResult(getQuery(bean));
			
			if (outputData != null && outputData.length > 0) {
				
				for (int i = 0; i < outputData.length; i++) {
					generatePageWiseReportPDF(bean, outputData[i], rg);
				}
				
				
			} else {
				
				//rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
				rg.addFormatedText(sCompanyName, 6, 0, 1, 0);
				rg.addFormatedText("PROVIDENT FUND ACCOUNT FOR THE FINANCIAL YEAR - " + bean.getSFinancialYearFrm() + "-" + bean.getSFinancialYearTo()  , 1, 0, 1, 0);
				
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("There is no data to display.", 0, 1, 0, 2);
			}
			if(check==1)
			{
				rg.createReportForKiosk(response);
			}
			else
			{
				rg.createReport(response);
			}

		} else if (bean.getSSelectedReportType().equalsIgnoreCase("Word")) {
			
			rg.setFName("PF Slip Report.doc");
			rg.setPageSize(5);
			Object[][] outputData = getSqlModel().getSingleResult(getQuery(bean));
			
			if (outputData != null && outputData.length > 0) {
				
				for (int i = 0; i < outputData.length; i++) {
					generatePageWiseReportWord(bean, outputData[i], rg);
				}
				
				
			} else {
				
				//rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
				rg.addFormatedText(sCompanyName, 6, 0, 1, 0);
				rg.addFormatedText("PROVIDENT FUND ACCOUNT FOR THE FINANCIAL YEAR - " + bean.getSFinancialYearFrm() + "-" + bean.getSFinancialYearTo()  , 1, 0, 1, 0);
				
				//rg.addText("\n", 0, 0, 0); /* For Space */
				//rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addFormatedText("There is no data to display.", 6, 0, 1, 0);
			}
			rg.createReport(response);
		}

	}
	
	private void generatePageWiseReportPDF(PFSlipReport bean,  Object[] objData, org.paradyne.lib.report.ReportGenerator rg)
	{
		try {
			Vector v = new Vector();
			v.add(objData);
			
			Object[][] tmpData = new Object [1][1];
			
			tmpData [0] = (Object[])v.get(0);
			
			String sQuery = " SELECT " + 
							" 	DIV_NAME "  +
							" FROM " +
							"	HRMS_EMP_OFFC A " + 
							"	INNER JOIN HRMS_DIVISION B ON (B.DIV_ID = A.EMP_DIV) WHERE EMP_ID = " + tmpData[0][8];
			
			Object[][] titleData = getSqlModel().getSingleResult(sQuery);
			String sDivName = "";
			
			if (titleData.length > 0)
			{
				sDivName = String.valueOf(titleData[0][0]);
				rg.addFormatedText(sDivName.toUpperCase(), 6, 0, 1, 0);
			}
			else
			{
				//rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
				rg.addFormatedText(sCompanyName, 6, 0, 1, 0);
			}	
			
			rg.addFormatedText("PROVIDENT FUND ACCOUNT FOR THE FINANCIAL YEAR - " + bean.getSFinancialYearFrm() + "-" + bean.getSFinancialYearTo()  , 1, 0, 1, 0);
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			
			String[] colNames = new String[8];
			colNames[0] = "PF A/C No.";
			colNames[1] = "Name of Subscriber";
			colNames[2] = "Opening Balance";
			colNames[3] = "Deposits";
			colNames[4] = "Interest";
			colNames[5] = "Total Accumulation";
			colNames[6] = "Withdrawal";
			colNames[7] = "Closing Balance";
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			
			
			
			int[] cellWidth = { 15, 25, 15, 15, 15, 15, 17, 13 };
			int[] alignment = { 1, 0, 2, 2, 2, 2, 2, 2 };
			rg.tableBody(colNames, tmpData, cellWidth, alignment);
			
			Object[][] noteData = new Object [1][1];
			int[] noteCellWidth = { 100 };
			int[] noteAlignment = { 0 };
			
			noteData[0][0]="The Closing Balance is inclusive of the interest on credits related to earlier period also.";
			
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			rg.tableBodyNoBorder(noteData, noteCellWidth, noteAlignment);
			
			Object[][] instructionData = new Object [3][2];
			int[] instructionCellWidth = { 10, 90 };
			int[] instaructionAlignment = { 0, 0 };
			
			instructionData[0][0]="Note 1 : ";
			instructionData[0][1]="If the subscriber desires to make any alteration in the nomination already made, a revised nomination may be sent herewith in accordance with the rules of Provident Fund. Those who have not made the nomination should make the nomination immediately.";
			instructionData[1][0]="Note 2 : ";
			instructionData[1][1]="In case the subscriber, owning to his/her having no family has nominated a person/persons other than member/members of his/her family and subsequently accquired a family, he/she should submit in favour of member/members.";
			instructionData[2][0]="Note 3 :  ";
			instructionData[2][1]="The subscriber is requested to satisfy himself/herself as to the corrections of the statement & to bring errors, if any to the notice of the Chief Accounts Officer, " + sDivName + "., immediatley.";
			
			rg.tableBodyNoBorder(instructionData, instructionCellWidth, instaructionAlignment);
			
			
			/* Signature */
			Object[][] signatureData = new Object [2][1];
			int[] signatureCellWidth = { 100 };
			int[] signatureAlignment = { 2 };
			
			signatureData[0][0] = "CHIEF ACCOUNTS OFFICER";
			signatureData[1][0] = sDivName;
			
			rg.addText("\n", 0, 0, 0, 2); 
			rg.addText("\n", 0, 0, 0, 2); 
			rg.addText("\n", 0, 0, 0, 2); 
			rg.tableBodyNoBorder(signatureData, signatureCellWidth, signatureAlignment);
			rg.pageBreak();
		} catch (Exception e) {
			logger.error("Error in PFSlipReportModel - generatePageWiseReportPDF() : " + e.getMessage());
		}
	}
	
	private void generatePageWiseReportWord(PFSlipReport bean,  Object[] objData, org.paradyne.lib.report.ReportGenerator rg)
	{
		try {
			//rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
			Vector v = new Vector();
			v.add(objData);
			
			Object[][] tmpData = new Object [1][1];
			tmpData [0] = (Object[])v.get(0);
			
			String sQuery = " SELECT " + 
			" 	DIV_NAME "  +
			" FROM " +
			"	HRMS_EMP_OFFC A " + 
			"	INNER JOIN HRMS_DIVISION B ON (B.DIV_ID = A.EMP_DIV) WHERE EMP_ID = " + tmpData[0][8];

			Object[][] titleData = getSqlModel().getSingleResult(sQuery);
			String sDivName = "";
			
			if (titleData.length > 0)
			{
				sDivName = String.valueOf(titleData[0][0]);
				rg.addFormatedText(sDivName.toUpperCase(), 6, 0, 1, 0);
			}
			else
			{
				//rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
				rg.addFormatedText(sCompanyName, 6, 0, 1, 0);
			}	
			
			rg.addFormatedText("PROVIDENT FUND ACCOUNT FOR THE FINANCIAL YEAR - " + bean.getSFinancialYearFrm() + "-" + bean.getSFinancialYearTo()  , 1, 0, 1, 0);
			
			/* Actual Data */
			String[] colNames = new String[8];
			colNames[0] = "PF A/C No.";
			colNames[1] = "Name of Subscriber";
			colNames[2] = "Opening Balance";
			colNames[3] = "Deposits";
			colNames[4] = "Interest Amt.";
			colNames[5] = "Total Accumulation";
			colNames[6] = "Withdrawal";
			colNames[7] = "Closing Balance";
			
			int[] cellWidth = { 15, 25, 18, 18, 18, 18, 17, 18 };
			int[] alignment = { 1, 0, 1, 1, 1, 1, 1, 1 };
			rg.tableBody(colNames, tmpData, cellWidth, alignment);
			
			/* Instruction */
			Object[][] noteData = new Object [1][1];
			int[] noteCellWidth = { 100 };
			int[] noteAlignment = { 0 };
			
			noteData[0][0]="The Closing Balance is inclusive of the interest on credits related to earlier period also.";
			rg.tableBodyNoBorder(noteData, noteCellWidth, noteAlignment);
			
			Object[][] instructionData = new Object [3][2];
			int[] instructionCellWidth = { 10, 90 };
			int[] instaructionAlignment = { 0, 0 };
			instructionData[0][0]="Note 1 : ";
			instructionData[0][1]="If the subscriber desires to make any alteration in the nomination already made, a revised nomination may be sent herewith in accordance with the rules of Provid Fund. Those who have not made the nomination should make the nomination immediatly.";
			instructionData[1][0]="Note 2 : ";
			instructionData[1][1]="In case the subscriber, owning to his/her having no family has nominated a person/persons other than member/members of his/her fmaily and subsequently accquired a family, he/she should submit in favour of member/mebers.";
			instructionData[2][0]="Note 3 :  ";
			instructionData[2][1]="The subscriber is requested to satisfy himself/herself as to the corrections of the statement & to bring errors, if any to the notice of the Chief Accounts Officer, " + sDivName + ", immediatly.";
			rg.tableBodyNoBorder(instructionData, instructionCellWidth, instaructionAlignment);
			
			/* Signature */
			Object[][] signatureData = new Object [2][1];
			int[] signatureCellWidth = { 100 };
			int[] signatureAlignment = { 2 };
			
			signatureData[0][0] = "CHIEF ACCOUNTS OFFICER";
			signatureData[1][0] = sDivName;
			
			rg.tableBodyNoBorder(signatureData, signatureCellWidth, signatureAlignment);
			
			rg.pageBreak();
		} catch (Exception e) {
			logger.error("Error in PFSlipReportModel - generatePageWiseReportWord() : " + e.getMessage());
		}
	}
	
	
	private String getQuery(PFSlipReport bean)
	{
		String sQuery = null;
		try {
			
			if (bean.isGeneralFlag())
			{
				sQuery = " SELECT "
						+ "		SAL_GPFNO , "
						+ "		(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) AS EMP_NAME, "
						+ " 	CASE WHEN PF_OPENING_BAL = 0 THEN '' ELSE LTRIM(TO_CHAR(PF_OPENING_BAL,'99,999,990.99')) END AS PF_OPENING_BAL, "
						+ " 	TO_CHAR(PF_DEPOSITS, '99,999,990.99') AS PF_DEPOSITS, "
						+ " 	TO_CHAR(PF_INT_AMOUNT, '99,999,990.99') AS PF_INT_AMOUNT, "
						+ " 	TO_CHAR((PF_OPENING_BAL + PF_DEPOSITS + PF_INT_AMOUNT), '99,999,990.99') AS TOTAL_AMOUNT, "
						+ " 	TO_CHAR(PF_WITHDRAWAL, '99,999,990.99') AS PF_WITHDRAWAL, "
						+ "  	TO_CHAR(PF_CLOSING_BAL, '99,999,990.99') AS PF_CLOSING_BAL, "
						+ "		C.EMP_ID "
						+ " FROM "
						+ "		HRMS_PF_LEDGER A "
						+ "		INNER JOIN HRMS_SALARY_MISC B ON (A.PF_EMPID = B.EMP_ID) AND B.SAL_PFTRUST_FLAG = 'Y'"
						+ " 	INNER JOIN HRMS_EMP_OFFC C ON (B.EMP_ID = C.EMP_ID) AND A.PF_EMPID = " + bean.getSEmpId() ;
				
				/* Financial Year */
				if(!bean.getSFinancialYearFrm().equals(""))
				{
					sQuery +=" AND A.PF_FROM_YEAR = " + bean.getSFinancialYearFrm();
					sQuery +=" AND A.PF_TO_YEAR = " + bean.getSFinancialYearTo();
				}
				
			}
			else
			{
				sQuery = " SELECT " 
						+ "		SAL_GPFNO , "
						+ "		(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) AS EMP_NAME, "
						+ " 	CASE WHEN PF_OPENING_BAL = 0 THEN '' ELSE LTRIM(TO_CHAR(PF_OPENING_BAL,'99,999,990.99')) END AS PF_OPENING_BAL, "
						+ " 	TO_CHAR(PF_DEPOSITS, '99,999,990.99') AS PF_DEPOSITS, "
						+ " 	TO_CHAR(PF_INT_AMOUNT, '99,999,990.99') AS PF_INT_AMOUNT, "
						+ " 	TO_CHAR((PF_OPENING_BAL + PF_DEPOSITS + PF_INT_AMOUNT), '99,999,990.99') AS TOTAL_AMOUNT, "
						+ " 	TO_CHAR(PF_WITHDRAWAL, '99,999,990.99') AS PF_WITHDRAWAL, "
						+ "  	TO_CHAR(PF_CLOSING_BAL, '99,999,990.99') AS PF_CLOSING_BAL, "
						+ "		C.EMP_ID "
						+ " FROM "
						+ "		HRMS_PF_LEDGER A "
						+ "		INNER JOIN HRMS_SALARY_MISC B ON (A.PF_EMPID = B.EMP_ID) AND B.SAL_PFTRUST_FLAG = 'Y'"
						+ " 	INNER JOIN HRMS_EMP_OFFC C ON (B.EMP_ID = C.EMP_ID) ";
				
				
				/* Financial Year */
				if(!bean.getSFinancialYearFrm().equals(""))
				{
					sQuery +=" AND A.PF_FROM_YEAR = " + bean.getSFinancialYearFrm();
					sQuery +=" AND A.PF_TO_YEAR = " + bean.getSFinancialYearTo();
				}
				
				
				/* Division */
				if(!bean.getSDivId().equals(""))
				{
					sQuery +=" AND C.EMP_DIV = " + bean.getSDivId();
				}
				
				/* Branch */
				if(!bean.getSBrchId().equals(""))
				{
					sQuery +=" AND C.EMP_CENTER = " + bean.getSBrchId();
				}
				
				/* Department */
				if(!bean.getSDeptId().equals(""))
				{
					sQuery +=" AND C.EMP_DEPT = " + bean.getSDeptId();
				}
				
				/* Designation */
				if(!bean.getSDesignationCode().equals(""))
				{
					sQuery +=" AND C.EMP_RANK = " + bean.getSDesignationCode();
				}
				
				/* Individual Employee */
				if (!bean.getSEmpId().equals(""))
				{
					sQuery +=" AND A.PF_EMPID = " + bean.getSEmpId();
				}
			}
			
		} catch (Exception e) {
			logger.error("Error in PFSlipReportModel - getQuery() ");
		}
		
		return sQuery;
	}
	
	public void getLoginUserInfo(PFSlipReport bean, HttpServletRequest request) {
		try {
			
			String sQuery = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMPLOYEE_NAME FROM HRMS_EMP_OFFC WHERE EMP_ID = " + bean.getUserEmpId();
			
			Object[][] outputData = getSqlModel().getSingleResult(sQuery);
			
			if (outputData.length > 0)
			{
				bean.setSEmpCode(String.valueOf(outputData[0][0]));		/* Employee Code */
				bean.setSEmpName(String.valueOf(outputData[0][1]));		/* Employee Name */
				
			}
			
		} catch (Exception e) {
			logger.error("Error in NewLoansIssuedReportModel.getLoginUserInfo() methos : " + e.getMessage());
		}
	}
	
	private void getCompanyName() {
		try {
			
			sCompanyName = "";
			
			String sQuery = " SELECT " +
			"	COMPANY_NAME " +
			" FROM " +
			"	HRMS_COMPANY ";

			Object[][] outputData = getSqlModel().getSingleResult(sQuery);
			
			if (outputData.length > 0)
			{
				sCompanyName = String.valueOf(outputData[0][0]);		/* Company Name */
			}
			
		} catch (Exception e) {
			logger.error("Error in PensionSlipModel.getCompanyName() methos : " + e.getMessage());
		}
	}
	
}
