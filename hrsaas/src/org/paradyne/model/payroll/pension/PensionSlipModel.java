package org.paradyne.model.payroll.pension;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.pension.PensionSlip;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

public class PensionSlipModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(PensionSlipModel.class);
	
	String sCompanyName = null;
	
	public void generateReport(PensionSlip bean, HttpServletResponse response) {

		String sReportType = "";
		if (bean.getSSelectedReportType().equalsIgnoreCase("pdf")) {
			sReportType = "Pdf";
		} else if (bean.getSSelectedReportType().equalsIgnoreCase("word")) {
			sReportType = "Txt";
		}

		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				sReportType, "Pension Slip");

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
				rg.addFormatedText("PENSION SLIP FOR - " + getMonthName(bean.getSMonth()) + " " + bean.getSYear()  , 1, 0, 1, 0);
				
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("There is no pension data to display.", 0, 1, 0, 2);
			}
			
			
			Object[][] releasedPensionData = getSqlModel().getSingleResult(getReleasedPensionQuery(bean));
			if (releasedPensionData != null && releasedPensionData.length > 0) {
				
				for (int i = 0; i < releasedPensionData.length; i++) {
					rg.pageBreak();
					generatePageWiseReleasedPensionReportPDF(bean, releasedPensionData[i], rg);
				}
				
				
			} else {
				
				//rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
				rg.addFormatedText(sCompanyName, 6, 0, 1, 0);
				rg.addFormatedText("PENSION SLIP FOR - " + getMonthName(bean.getSMonth()) + " " + bean.getSYear()  , 1, 0, 1, 0);
				
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("There are no released pensions to display.", 0, 1, 0, 2);
			}
			
			rg.createReport(response);

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
				rg.addFormatedText("PENSION SLIP FOR - " + getMonthName(bean.getSMonth()) + " " + bean.getSYear()  , 1, 0, 1, 0);
				
				rg.addFormatedText("There is no data to display.", 6, 0, 1, 0);
			}
			
			Object[][] releasedPensionData = getSqlModel().getSingleResult(getReleasedPensionQuery(bean));
			if (releasedPensionData != null && releasedPensionData.length > 0) {
				
				for (int i = 0; i < releasedPensionData.length; i++) {
					rg.pageBreak();
					generatePageWiseReleasedPensionReportWord(bean, releasedPensionData[i], rg);
				}
				
				
			} else {
				
				//rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
				rg.addFormatedText(sCompanyName, 6, 0, 1, 0);
				rg.addFormatedText("PENSION SLIP FOR - " + getMonthName(bean.getSMonth()) + " " + bean.getSYear()  , 1, 0, 1, 0);
				
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("\n", 0, 0, 0); /* For Space */
				rg.addText("There are no released pensions to display.", 0, 1, 0, 2);
			}
			
			rg.createReport(response);
		}

	}
	
	private void generatePageWiseReleasedPensionReportWord(PensionSlip bean,
			Object[] pensionObject, ReportGenerator rg) {

		try {
			Vector v = new Vector();
			v.add(pensionObject);
			
			Object[][] tmpData = new Object [1][1];
			tmpData [0] = (Object[])v.get(0);
			
			String sDivName = "";
			
			if (tmpData[0][3] != null) {
				sDivName = String.valueOf(tmpData[0][3]);
				rg.addFormatedText(sDivName.toUpperCase(), 6, 0, 1, 0);
			} else {
				//rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
				rg.addFormatedText(sCompanyName, 6, 0, 1, 0);
			}	
			
			rg.addFormatedText("PENSION SLIP FOR - " + getMonthName(bean.getSMonth()) + " " + bean.getSYear()  , 1, 0, 1, 0);
			
			Object[][] empData = new Object [5][7];
			empData[0][0] = "  Name"; 
			empData[0][1] = ":"; 
			empData[0][2] = "" + checkNull(String.valueOf(tmpData[0][13])) + " "+ tmpData[0][0]; 
			empData[0][3] = ""; 
			empData[0][4] = "Pay Mode"; 
			empData[0][5] = ":"; 
			empData[0][6] = "" + tmpData[0][10];
			
			
			empData[1][0] = "  Department"; 
			empData[1][1] = ":"; 
			empData[1][2] = "" + tmpData[0][5]; 
			empData[1][3] = ""; 
			empData[1][4] = "Account No."; 
			empData[1][5] = ":"; 
			empData[1][6] = "" + tmpData[0][8];
			
			empData[2][0] = "  Designation"; 
			empData[2][1] = ":"; 
			empData[2][2] = "" + tmpData[0][7]; 
			empData[2][3] = ""; 
			empData[2][4] = "Branch"; 
			empData[2][5] = ":"; 
			empData[2][6] = "" + tmpData[0][4];
			
			empData[3][0] = "  Bank Name"; 
			empData[3][1] = ":"; 
			empData[3][2] = "" + tmpData[0][6]; 
			empData[3][3] = ""; 
			empData[3][4] = ""; 
			empData[3][5] = ""; 
			empData[3][6] = "";
			
			empData[4][0] = "  Pan No"; 
			empData[4][1] = ":"; 
			empData[4][2] = "" + tmpData[0][9]; 
			empData[4][3] = ""; 
			empData[4][4] = ""; 
			empData[4][5] = ""; 
			empData[4][6] = "";
			
			int[] empCellWidth = { 18,3,30,0,16,3,30 };
			int[] empAlignment = { 0,0,0,0,0,0,0 };
			
			rg.tableBodyNoBorder(empData, empCellWidth, empAlignment);
			
			/* Pension table Heading - Start */
			Object[][] signatureData = new Object [1][1];
			int[] signatureCellWidth = { 100 };
			int[] signatureAlignment = { 0 };
			
			signatureData[0][0] = "Pension Information";
			
			rg.tableBodyNoBorder(signatureData, signatureCellWidth, signatureAlignment);
			/* Pension table Heading - End */
			
			
			String[] colNames = new String[4];
			colNames[0] = "Pension Credits";
			colNames[1] = "Amount in Rs.";
			colNames[2] = "Pension Debits";
			colNames[3] = "Amount in Rs.";
			
			Object[][] pensionData = getPensionInformation(bean, tmpData);
			
			int[] cellWidth = { 30, 20, 30, 20 };
			int[] alignment = { 0, 2, 0, 2 };
			
			rg.tableBody(colNames, pensionData, cellWidth, alignment);

			rg.pageBreak();
		} catch (Exception e) {
			logger.error("Error in PensionSlipModel - generatePageWiseReleasedPensionReportWord() : " + e.getMessage());
		}
	
		
	}

	private void generatePageWiseReleasedPensionReportPDF(PensionSlip bean,
			Object[] pensionObject, ReportGenerator rg) {

		try {
			Vector v = new Vector();
			v.add(pensionObject);
			
			Object[][] tmpData = new Object [1][1];
			
			tmpData [0] = (Object[])v.get(0);
			
			String sDivName = "";
			
			if (tmpData[0][3] != null) {
				sDivName = String.valueOf(tmpData[0][3]);
				rg.addFormatedText(sDivName.toUpperCase(), 6, 0, 1, 0);
			} else {
				rg.addFormatedText(sCompanyName, 6, 0, 1, 0);
			}	
			
			rg.addFormatedText("PENSION SLIP FOR - " + getMonthName(bean.getSMonth()) + " " + bean.getSYear()  , 1, 0, 1, 0);
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			
			Object[][] empData = new Object [5][7];
			empData[0][0] = "  Name"; 
			empData[0][1] = ":"; 
			empData[0][2] = "" + checkNull(String.valueOf(tmpData[0][13])) + " "+ tmpData[0][0]; 
			empData[0][3] = ""; 
			empData[0][4] = "Pay Mode"; 
			empData[0][5] = ":"; 
			empData[0][6] = "" + tmpData[0][10];
			
			
			empData[1][0] = "  Department"; 
			empData[1][1] = ":"; 
			empData[1][2] = "" + tmpData[0][5]; 
			empData[1][3] = ""; 
			empData[1][4] = "Account No."; 
			empData[1][5] = ":"; 
			empData[1][6] = "" + tmpData[0][8];
			
			empData[2][0] = "  Designation"; 
			empData[2][1] = ":"; 
			empData[2][2] = "" + tmpData[0][7]; 
			empData[2][3] = ""; 
			empData[2][4] = "Branch"; 
			empData[2][5] = ":"; 
			empData[2][6] = "" + tmpData[0][4];
			
			empData[3][0] = "  Bank Name"; 
			empData[3][1] = ":"; 
			empData[3][2] = "" + tmpData[0][6]; 
			empData[3][3] = ""; 
			empData[3][4] = ""; 
			empData[3][5] = ""; 
			empData[3][6] = "";
			
			empData[4][0] = "  Pan No"; 
			empData[4][1] = ":"; 
			empData[4][2] = "" + tmpData[0][9]; 
			empData[4][3] = ""; 
			empData[4][4] = ""; 
			empData[4][5] = ""; 
			empData[4][6] = "";
			
			int[] empCellWidth = { 12,5,30,8,10,5,30 };
			int[] empAlignment = { 0,0,0,0,0,0,0 };
			
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			rg.tableBodyNoCellBorder(empData, empCellWidth, empAlignment, 0);
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			
			rg.addText("Pension Information", 0, 0, 0, 2); /* For Space */
			
			String[] colNames = new String[4];
			colNames[0] = "Pension Credits";
			colNames[1] = "Amount in Rs.";
			colNames[2] = "Pension Debits";
			colNames[3] = "Amount in Rs.";
			
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			
			Object[][] pensionData = getPensionInformation(bean, tmpData);
			
			int[] cellWidth = { 30, 20, 30, 20 };
			int[] alignment = { 0, 2, 0, 2 };
			
			rg.tableBody(colNames, pensionData, cellWidth, alignment);

			rg.pageBreak();
		} catch (Exception e) {
			logger.error("Error in PensionSlipModel - generatePageWiseReleasedPensionReportPDF() : " + e.getMessage());
		}
	
		
	}

	private String getQuery(PensionSlip bean)
	{
		String sQuery = null;
		try {
			
			if (bean.isGeneralFlag())
			{
				sQuery = " SELECT "
						+ " 	(C.EMP_FNAME || ' ' || C.EMP_MNAME || ' ' || C.EMP_LNAME) AS EMP_NAME, "
						+ "	 	A.LEDGER_MONTH, "
						+ "   	A.LEDGER_YEAR, "
						+ "		(SELECT DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID = C.EMP_DIV) AS DIV_NAME, "
						+ " 	(SELECT CENTER_NAME FROM HRMS_CENTER WHERE  CENTER_ID = C.EMP_CENTER) AS BRCH_NAME, "
						+ "  	(SELECT DEPT_NAME FROM HRMS_DEPT WHERE DEPT_ID = C.EMP_DEPT) AS DEPT_NAME, "
						+ "		(SELECT BANK_NAME FROM HRMS_BANK WHERE BANK_MICR_CODE=D.SAL_PENSION_BANK) AS BANK_NAME, "
						+ "		(SELECT RANK_NAME FROM HRMS_RANK WHERE RANK_ID=C.EMP_RANK) AS DESIGNATION, "
						+ " 	D.SAL_PENSION_ACCNO, "
						+ "		D.SAL_PANNO, "
						+ " 	(SELECT MOD_NAME FROM HRMS_DATA_MODIFICATION WHERE MOD_VALUE = D.SAL_PAY_MODE AND MOD_TYPE = 'payMode') AS PAY_MODE," 
						+ " 	A.LEDGER_CODE, " 
						+ "		B.PENS_EMP_ID, " 
						+ " 	(SELECT NVL(TITLE_NAME,' ') FROM HRMS_TITLE WHERE TITLE_CODE = C.EMP_TITLE_CODE) AS TITLE, PENS_ONHOLD, "
						+ " 	PENS_RELEASE_ONHOLD, TO_CHAR(TO_DATE(PENS_PAID_IN_MONTH,'MM'),'MONTH'), PENS_PAID_IN_YEAR"
						+ " FROM "
						+ " 	HRMS_PENSION_LEDGER A "
						+ " 	INNER JOIN HRMS_PENSION_" + bean.getSYear()
						+ " 	B ON (A.LEDGER_CODE = B.PENS_LEDGER_CODE) "
						+ " 	AND A.LEDGER_MONTH = " + bean.getSMonth()
						+ " 	AND A.LEDGER_YEAR = " + bean.getSYear()
						+ " 	INNER JOIN HRMS_EMP_OFFC C ON (B.PENS_EMP_ID = C.EMP_ID) AND C.EMP_ID = " + bean.getSEmpId()
						+ "		LEFT JOIN HRMS_SALARY_MISC D ON (C.EMP_ID = D.EMP_ID)  "; 
				
				//if( !(bean.getSMonth().equals("")) && !(bean.getSYear().equals("")) )
				//{
				//	sQuery +=" AND A.LEDGER_MONTH = " + bean.getSMonth();
				//	sQuery +=" AND A.LEDGER_YEAR = " + bean.getSYear();
				//}
				
			}
			else
			{
					sQuery = " SELECT "
						+ " 	(C.EMP_FNAME || ' ' || C.EMP_MNAME || ' ' || C.EMP_LNAME) AS EMP_NAME, "
						+ "	 	A.LEDGER_MONTH, "
						+ "   	A.LEDGER_YEAR, "
						+ "		(SELECT DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID = C.EMP_DIV) AS DIV_NAME, "
						+ " 	(SELECT CENTER_NAME FROM HRMS_CENTER WHERE  CENTER_ID = C.EMP_CENTER) AS BRCH_NAME, "
						+ "  	(SELECT DEPT_NAME FROM HRMS_DEPT WHERE DEPT_ID = C.EMP_DEPT) AS DEPT_NAME, "
						+ "		(SELECT BANK_NAME FROM HRMS_BANK WHERE BANK_MICR_CODE=D.SAL_PENSION_BANK) AS BANK_NAME, "
						+ "		(SELECT RANK_NAME FROM HRMS_RANK WHERE RANK_ID=C.EMP_RANK) AS DESIGNATION, "
						+ " 	D.SAL_PENSION_ACCNO, "
						+ "		D.SAL_PANNO, "
						+ " 	(SELECT MOD_NAME FROM HRMS_DATA_MODIFICATION WHERE MOD_VALUE = D.SAL_PAY_MODE AND MOD_TYPE = 'payMode') AS PAY_MODE," 
						+ " 	A.LEDGER_CODE, " 
						+ "		B.PENS_EMP_ID, "
						+ " 	(SELECT NVL(TITLE_NAME,' ') FROM HRMS_TITLE WHERE TITLE_CODE = C.EMP_TITLE_CODE) AS TITLE, PENS_ONHOLD, "
						+ " 	PENS_RELEASE_ONHOLD, TO_CHAR(TO_DATE(PENS_PAID_IN_MONTH,'MM'),'MONTH'), PENS_PAID_IN_YEAR"
						+ " FROM "
						+ " 	HRMS_PENSION_LEDGER A "
						+ " 	INNER JOIN HRMS_PENSION_" + bean.getSYear()
						+ " 	B ON (A.LEDGER_CODE = B.PENS_LEDGER_CODE) "
						+ " 	AND A.LEDGER_MONTH = " + bean.getSMonth()
						+ " 	AND A.LEDGER_YEAR = " + bean.getSYear()
						+ " 	INNER JOIN HRMS_EMP_OFFC C ON (B.PENS_EMP_ID = C.EMP_ID) ";
						
				
				//if( !(bean.getSMonth().equals("")) && !(bean.getSYear().equals("")) )
				//{
				//	sQuery +=" AND A.LEDGER_MONTH = " + bean.getSMonth();
				//	sQuery +=" AND A.LEDGER_YEAR = " + bean.getSYear();
				//}
				
				
				/* Division */
				if(!bean.getSDivId().equals("")) {
					sQuery +=" AND C.EMP_DIV = " + bean.getSDivId();
				}
				
				/* Branch */
				if(!bean.getSBrchId().equals("")) {
					sQuery +=" AND C.EMP_CENTER = " + bean.getSBrchId();
				}
				
				/* Department */
				if(!bean.getSDeptId().equals("")) {
					sQuery +=" AND C.EMP_DEPT = " + bean.getSDeptId();
				}
				
				/* Designation */
				if(!bean.getSDesignationCode().equals("")) {
					sQuery +=" AND C.EMP_RANK = " + bean.getSDesignationCode();
				}
				
				/* Individual Employee */
				if (!bean.getSEmpId().equals("")) {
					sQuery +=" AND C.EMP_ID = " + bean.getSEmpId();
				}
				
				sQuery += "	LEFT JOIN HRMS_SALARY_MISC D ON (C.EMP_ID = D.EMP_ID) ";
			}
			
		} catch (Exception e) {
			logger.error("Error in PFSlipReportModel - getQuery() ");
		}
		
		return sQuery;
	}
	
	private String getReleasedPensionQuery(PensionSlip bean)
	{
		String sQuery = null;
		try {
			
			if (bean.isGeneralFlag())
			{
				sQuery = " SELECT "
						+ " 	(C.EMP_FNAME || ' ' || C.EMP_MNAME || ' ' || C.EMP_LNAME) AS EMP_NAME, "
						+ "	 	A.LEDGER_MONTH, "
						+ "   	A.LEDGER_YEAR, "
						+ "		(SELECT DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID = C.EMP_DIV) AS DIV_NAME, "
						+ " 	(SELECT CENTER_NAME FROM HRMS_CENTER WHERE  CENTER_ID = C.EMP_CENTER) AS BRCH_NAME, "
						+ "  	(SELECT DEPT_NAME FROM HRMS_DEPT WHERE DEPT_ID = C.EMP_DEPT) AS DEPT_NAME, "
						+ "		(SELECT BANK_NAME FROM HRMS_BANK WHERE BANK_MICR_CODE=D.SAL_PENSION_BANK) AS BANK_NAME, "
						+ "		(SELECT RANK_NAME FROM HRMS_RANK WHERE RANK_ID=C.EMP_RANK) AS DESIGNATION, "
						+ " 	D.SAL_PENSION_ACCNO, "
						+ "		D.SAL_PANNO, "
						+ " 	(SELECT MOD_NAME FROM HRMS_DATA_MODIFICATION WHERE MOD_VALUE = D.SAL_PAY_MODE AND MOD_TYPE = 'payMode') AS PAY_MODE," 
						+ " 	A.LEDGER_CODE, " 
						+ "		B.PENS_EMP_ID, " 
						+ " 	(SELECT NVL(TITLE_NAME,' ') FROM HRMS_TITLE WHERE TITLE_CODE = C.EMP_TITLE_CODE) AS TITLE, PENS_ONHOLD, "
						+ " 	PENS_RELEASE_ONHOLD, TO_CHAR(TO_DATE(PENS_PAID_IN_MONTH,'MM'),'MONTH'), PENS_PAID_IN_YEAR"
						+ " FROM "
						+ " 	HRMS_PENSION_LEDGER A "
						+ " 	INNER JOIN HRMS_PENSION_" + bean.getSYear()
						+ " 	B ON (A.LEDGER_CODE = B.PENS_LEDGER_CODE) "
						+ " 	AND B.PENS_PAID_IN_MONTH = " + bean.getSMonth()
						+ " 	AND B.PENS_PAID_IN_YEAR = " + bean.getSYear()
						+ " 	INNER JOIN HRMS_EMP_OFFC C ON (B.PENS_EMP_ID = C.EMP_ID) AND C.EMP_ID = " + bean.getSEmpId()
						+ "		LEFT JOIN HRMS_SALARY_MISC D ON (C.EMP_ID = D.EMP_ID)  "; 
				
				//if( !(bean.getSMonth().equals("")) && !(bean.getSYear().equals("")) )
				//{
				//	sQuery +=" AND A.LEDGER_MONTH = " + bean.getSMonth();
				//	sQuery +=" AND A.LEDGER_YEAR = " + bean.getSYear();
				//}
				
			}
			else
			{
					sQuery = " SELECT "
						+ " 	(C.EMP_FNAME || ' ' || C.EMP_MNAME || ' ' || C.EMP_LNAME) AS EMP_NAME, "
						+ "	 	A.LEDGER_MONTH, "
						+ "   	A.LEDGER_YEAR, "
						+ "		(SELECT DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID = C.EMP_DIV) AS DIV_NAME, "
						+ " 	(SELECT CENTER_NAME FROM HRMS_CENTER WHERE  CENTER_ID = C.EMP_CENTER) AS BRCH_NAME, "
						+ "  	(SELECT DEPT_NAME FROM HRMS_DEPT WHERE DEPT_ID = C.EMP_DEPT) AS DEPT_NAME, "
						+ "		(SELECT BANK_NAME FROM HRMS_BANK WHERE BANK_MICR_CODE=D.SAL_PENSION_BANK) AS BANK_NAME, "
						+ "		(SELECT RANK_NAME FROM HRMS_RANK WHERE RANK_ID=C.EMP_RANK) AS DESIGNATION, "
						+ " 	D.SAL_PENSION_ACCNO, "
						+ "		D.SAL_PANNO, "
						+ " 	(SELECT MOD_NAME FROM HRMS_DATA_MODIFICATION WHERE MOD_VALUE = D.SAL_PAY_MODE AND MOD_TYPE = 'payMode') AS PAY_MODE," 
						+ " 	A.LEDGER_CODE, " 
						+ "		B.PENS_EMP_ID, "
						+ " 	(SELECT NVL(TITLE_NAME,' ') FROM HRMS_TITLE WHERE TITLE_CODE = C.EMP_TITLE_CODE) AS TITLE, PENS_ONHOLD, "
						+ " 	PENS_RELEASE_ONHOLD, TO_CHAR(TO_DATE(PENS_PAID_IN_MONTH,'MM'),'MONTH'), PENS_PAID_IN_YEAR"
						+ " FROM "
						+ " 	HRMS_PENSION_LEDGER A "
						+ " 	INNER JOIN HRMS_PENSION_" + bean.getSYear()
						+ " 	B ON (A.LEDGER_CODE = B.PENS_LEDGER_CODE) "
						+ " 	AND B.PENS_PAID_IN_MONTH = " + bean.getSMonth()
						+ " 	AND B.PENS_PAID_IN_YEAR = " + bean.getSYear()
						+ " 	INNER JOIN HRMS_EMP_OFFC C ON (B.PENS_EMP_ID = C.EMP_ID) ";
						
				
				/* Division */
				if(!bean.getSDivId().equals("")) {
					sQuery +=" AND C.EMP_DIV = " + bean.getSDivId();
				}
				
				/* Branch */
				if(!bean.getSBrchId().equals("")) {
					sQuery +=" AND C.EMP_CENTER = " + bean.getSBrchId();
				}
				
				/* Department */
				if(!bean.getSDeptId().equals("")) {
					sQuery +=" AND C.EMP_DEPT = " + bean.getSDeptId();
				}
				
				/* Designation */
				if(!bean.getSDesignationCode().equals("")) {
					sQuery +=" AND C.EMP_RANK = " + bean.getSDesignationCode();
				}
				
				/* Individual Employee */
				if (!bean.getSEmpId().equals("")) {
					sQuery +=" AND C.EMP_ID = " + bean.getSEmpId();
				}
				
				sQuery += "	LEFT JOIN HRMS_SALARY_MISC D ON (C.EMP_ID = D.EMP_ID) ";
			}
			
		} catch (Exception e) {
			logger.error("Error in PFSlipReportModel - getQuery() ");
		}
		
		return sQuery;
	}
	
	private void generatePageWiseReportPDF(PensionSlip bean,  Object[] objData, org.paradyne.lib.report.ReportGenerator rg)
	{
		try {
			Vector v = new Vector();
			v.add(objData);
			
			Object[][] tmpData = new Object [1][1];
			
			tmpData [0] = (Object[])v.get(0);
			
			//String sQuery = " SELECT " + 
			//				" 	DIV_NAME "  +
			//				" FROM " +
			//				"	HRMS_EMP_OFFC A " + 
			//				"	INNER JOIN HRMS_DIVISION B ON (B.DIV_ID = A.EMP_DIV) WHERE EMP_ID = " + tmpData[0][12];
			
			//Object[][] titleData = getSqlModel().getSingleResult(sQuery);
			String sDivName = "";
			
			if (tmpData[0][3] != null) {
				sDivName = String.valueOf(tmpData[0][3]);
				rg.addFormatedText(sDivName.toUpperCase(), 6, 0, 1, 0);
			} else {
				//rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
				rg.addFormatedText(sCompanyName, 6, 0, 1, 0);
			}	
			
			rg.addFormatedText("PENSION SLIP FOR - " + getMonthName(bean.getSMonth()) + " " + bean.getSYear()  , 1, 0, 1, 0);
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			
			Object[][] empData = new Object [5][7];
			empData[0][0] = "  Name"; 
			empData[0][1] = ":"; 
			empData[0][2] = "" + checkNull(String.valueOf(tmpData[0][13])) + " "+ tmpData[0][0]; 
			empData[0][3] = ""; 
			empData[0][4] = "Pay Mode"; 
			empData[0][5] = ":"; 
			empData[0][6] = "" + tmpData[0][10];
			
			
			empData[1][0] = "  Department"; 
			empData[1][1] = ":"; 
			empData[1][2] = "" + tmpData[0][5]; 
			empData[1][3] = ""; 
			empData[1][4] = "Account No."; 
			empData[1][5] = ":"; 
			empData[1][6] = "" + tmpData[0][8];
			
			empData[2][0] = "  Designation"; 
			empData[2][1] = ":"; 
			empData[2][2] = "" + tmpData[0][7]; 
			empData[2][3] = ""; 
			empData[2][4] = "Branch"; 
			empData[2][5] = ":"; 
			empData[2][6] = "" + tmpData[0][4];
			
			empData[3][0] = "  Bank Name"; 
			empData[3][1] = ":"; 
			empData[3][2] = "" + tmpData[0][6]; 
			empData[3][3] = ""; 
			empData[3][4] = ""; 
			empData[3][5] = ""; 
			empData[3][6] = "";
			
			empData[4][0] = "  Pan No"; 
			empData[4][1] = ":"; 
			empData[4][2] = "" + tmpData[0][9]; 
			empData[4][3] = ""; 
			empData[4][4] = ""; 
			empData[4][5] = ""; 
			empData[4][6] = "";
			
			int[] empCellWidth = { 12,5,30,8,10,5,30 };
			int[] empAlignment = { 0,0,0,0,0,0,0 };
			
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			rg.tableBodyNoCellBorder(empData, empCellWidth, empAlignment, 0);
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			
			rg.addText("Pension Information", 0, 0, 0, 2); /* For Space */
			
			String[] colNames = new String[4];
			colNames[0] = "Pension Credits";
			colNames[1] = "Amount in Rs.";
			colNames[2] = "Pension Debits";
			colNames[3] = "Amount in Rs.";
			
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			
			Object[][] pensionData = getPensionInformation(bean, tmpData);
			
			int[] cellWidth = { 30, 20, 30, 20 };
			int[] alignment = { 0, 2, 0, 2 };
			if(String.valueOf(tmpData[0][14]).equals("N") && String.valueOf(tmpData[0][15]).equals("N")){
				//IF NOT ONHOLD AND NOT RELEASED
				rg.tableBody(colNames, pensionData, cellWidth, alignment);
			}else if(String.valueOf(tmpData[0][14]).equals("Y") && String.valueOf(tmpData[0][15]).equals("N")){
				//IF ONHOLD AND NOT RELEASED
				rg.addText("Pension is kept onhold.", 0, 1, 0);
			}else if(String.valueOf(tmpData[0][14]).equals("Y") && String.valueOf(tmpData[0][15]).equals("Y")){
				//IF ONHOLD AND RELEASED
				rg.addText("Pension is released in the month of "+String.valueOf(tmpData[0][16])+"- "
						+String.valueOf(tmpData[0][17])+".", 0, 1, 0);
			}

			rg.pageBreak();
		} catch (Exception e) {
			logger.error("Error in PensionSlipModel - generatePageWiseReportPDF() : " + e.getMessage());
		}
	}
	
	
	private void generatePageWiseReportWord(PensionSlip bean,  Object[] objData, org.paradyne.lib.report.ReportGenerator rg) {
		try {
			Vector v = new Vector();
			v.add(objData);
			
			Object[][] tmpData = new Object [1][1];
			tmpData [0] = (Object[])v.get(0);
			
			//String sQuery = " SELECT " + 
			//				" 	DIV_NAME "  +
			//				" FROM " +
			//				"	HRMS_EMP_OFFC A " + 
			//				"	INNER JOIN HRMS_DIVISION B ON (B.DIV_ID = A.EMP_DIV) WHERE EMP_ID = " + tmpData[0][8];
			
			//rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
			
			String sDivName = "";
			
			if (tmpData[0][3] != null) {
				sDivName = String.valueOf(tmpData[0][3]);
				rg.addFormatedText(sDivName.toUpperCase(), 6, 0, 1, 0);
			} else {
				//rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
				rg.addFormatedText(sCompanyName, 6, 0, 1, 0);
			}	
			
			rg.addFormatedText("PENSION SLIP FOR - " + getMonthName(bean.getSMonth()) + " " + bean.getSYear()  , 1, 0, 1, 0);
			
			Object[][] empData = new Object [5][7];
			empData[0][0] = "  Name"; 
			empData[0][1] = ":"; 
			empData[0][2] = "" + checkNull(String.valueOf(tmpData[0][13])) + " "+ tmpData[0][0]; 
			empData[0][3] = ""; 
			empData[0][4] = "Pay Mode"; 
			empData[0][5] = ":"; 
			empData[0][6] = "" + tmpData[0][10];
			
			
			empData[1][0] = "  Department"; 
			empData[1][1] = ":"; 
			empData[1][2] = "" + tmpData[0][5]; 
			empData[1][3] = ""; 
			empData[1][4] = "Account No."; 
			empData[1][5] = ":"; 
			empData[1][6] = "" + tmpData[0][8];
			
			empData[2][0] = "  Designation"; 
			empData[2][1] = ":"; 
			empData[2][2] = "" + tmpData[0][7]; 
			empData[2][3] = ""; 
			empData[2][4] = "Branch"; 
			empData[2][5] = ":"; 
			empData[2][6] = "" + tmpData[0][4];
			
			empData[3][0] = "  Bank Name"; 
			empData[3][1] = ":"; 
			empData[3][2] = "" + tmpData[0][6]; 
			empData[3][3] = ""; 
			empData[3][4] = ""; 
			empData[3][5] = ""; 
			empData[3][6] = "";
			
			empData[4][0] = "  Pan No"; 
			empData[4][1] = ":"; 
			empData[4][2] = "" + tmpData[0][9]; 
			empData[4][3] = ""; 
			empData[4][4] = ""; 
			empData[4][5] = ""; 
			empData[4][6] = "";
			
			int[] empCellWidth = { 18,3,30,0,16,3,30 };
			int[] empAlignment = { 0,0,0,0,0,0,0 };
			
			rg.tableBodyNoBorder(empData, empCellWidth, empAlignment);
			
			/* Pension table Heading - Start */
			//rg.addFormatedText("Pension Information", 1, 0, 0, 0);
			Object[][] signatureData = new Object [1][1];
			int[] signatureCellWidth = { 100 };
			int[] signatureAlignment = { 0 };
			
			signatureData[0][0] = "Pension Information";
			
			rg.tableBodyNoBorder(signatureData, signatureCellWidth, signatureAlignment);
			/* Pension table Heading - End */
			
			
			String[] colNames = new String[4];
			colNames[0] = "Pension Credits";
			colNames[1] = "Amount in Rs.";
			colNames[2] = "Pension Debits";
			colNames[3] = "Amount in Rs.";
			
			Object[][] pensionData = getPensionInformation(bean, tmpData);
			
			int[] cellWidth = { 30, 20, 30, 20 };
			int[] alignment = { 0, 2, 0, 2 };
			
			if(String.valueOf(tmpData[0][14]).equals("N") && String.valueOf(tmpData[0][15]).equals("N")){
				//IF NOT ONHOLD AND NOT RELEASED
				rg.tableBody(colNames, pensionData, cellWidth, alignment);
			}else if(String.valueOf(tmpData[0][14]).equals("Y") && String.valueOf(tmpData[0][15]).equals("N")){
				//IF ONHOLD AND NOT RELEASED
				rg.addText("Pension is kept onhold.", 0, 1, 0);
			}else if(String.valueOf(tmpData[0][14]).equals("Y") && String.valueOf(tmpData[0][15]).equals("Y")){
				//IF ONHOLD AND RELEASED
				rg.addText("Pension is released in the month of "+String.valueOf(tmpData[0][16])+"- "
						+String.valueOf(tmpData[0][17])+".", 0, 1, 0);
			}

			rg.pageBreak();
		} catch (Exception e) {
			logger.error("Error in PensionSlipModel - generatePageWiseReportPDF() : " + e.getMessage());
		}
	}
	
	private String getMonthName (String sMonth) {
		String sResultMonth = null;
		try {
			if (sMonth.equals("01")) {
				sResultMonth = "January";
			} else if (sMonth.equals("02")) {
				sResultMonth = "February";
			} else if (sMonth.equals("03")) {
				sResultMonth = "March";
			} else if (sMonth.equals("04")) {
				sResultMonth = "April";
			} else if (sMonth.equals("05")) {
				sResultMonth = "May";
			} else if (sMonth.equals("06")) {
				sResultMonth = "June";
			} else if (sMonth.equals("07")) {
				sResultMonth = "July";
			} else if (sMonth.equals("08")) {
				sResultMonth = "August";
			} else if (sMonth.equals("09")) {
				sResultMonth = "September";
			} else if (sMonth.equals("10")) {
				sResultMonth = "October";
			} else if (sMonth.equals("11")) {
				sResultMonth = "November";
			} else if (sMonth.equals("12")) {
				sResultMonth = "December";
			}
				
			
		} catch (Exception e) {
			logger.error("Error in PensionSlipModel.getMonthName() : " + e.getMessage());
		}
		return sResultMonth;
	}
	
	private Object[][] getPensionInformation(PensionSlip bean, Object[][] tmpData) {

		Object[][] finalObject = null;
		int row =0;	
		
		try {
			String creditQuery =  " SELECT "
								+ "		CREDIT_NAME, "
								+ "		PENS_AMOUNT "
								+ " FROM "
								+ "		HRMS_PENSION_CREDIT_" + bean.getSYear() + " A "
								+ "	INNER JOIN HRMS_CREDIT_HEAD B ON (A.PENS_CREDIT_CODE = B.CREDIT_CODE) "
								+ "		AND PENS_LEDGER_CODE = " + tmpData[0][11] 
								+ "		AND PENS_EMP_ID = " + tmpData[0][12] 
							    + " UNION "
							    + " SELECT " 
							    + "		CAST('Arrear' AS NVARCHAR2(20)) AS CREDIT_NAME, " 
							    + "		PENS_ARREARS_AMOUNT " 
							    + "	FROM " 
							    + "		HRMS_PENSION_" + bean.getSYear() + " A, " 
							    + "		HRMS_PENSION_LEDGER B " 
							    + "	WHERE "
							    + " 	A.PENS_LEDGER_CODE = B.LEDGER_CODE AND "
							    + " 	B.LEDGER_MONTH = " + bean.getSMonth() + " AND "
		    					+ " 	B.LEDGER_YEAR = " + bean.getSYear() + " AND "
							    + "		A.PENS_EMP_ID = " + tmpData[0][12];	   		                                 
			
			String debitQuery =  "  SELECT " 
								+ " 	PENS_MISC_RECOVERY, "
								+ " 	PENS_COMM_AMOUNT, " 
								+ " 	PENS_REC_AMOUNT " 
								+ " FROM "
								+ " 	HRMS_PENSION_" + bean.getSYear() 
								+ " WHERE "
								+ " 	PENS_LEDGER_CODE = " + tmpData[0][11]
								+ "		AND PENS_EMP_ID = " + tmpData[0][12];
			
			String netSalQuery = "";

			Object creditData[][] = getSqlModel().getSingleResult(creditQuery);
			Object debitData[][] = getSqlModel().getSingleResult(debitQuery);
			//Object netSalData[][] = getSqlModel().getSingleResult(netSalQuery);
			
			
			if (creditData.length > 3) {
				finalObject = new Object[creditData.length + 2][4];
				row = creditData.length;
			} else {
				finalObject = new Object[5][4];
				//row = debitData.length;
				row = 3;
			}
			
			
			/*
			 * Following loop is used to insert the credit name and amount in the first and second position of the final object.
			 */
			Double dCreditTotal = 0.0;
			for (int i = 0; i < creditData.length; i++) {
				finalObject[i][0] = creditData[i][0];
				finalObject[i][1] = Math.round(Double.parseDouble(String.valueOf(creditData[i][1])));
				
				dCreditTotal = dCreditTotal + (Double.parseDouble(String.valueOf(creditData[i][1])));
			}
			
			
			/*
			 * Following loop is used to insert the debit name and amount in the third and fourth position of the final object.
			 */
			Double dDebitTotal = 0.0;
			for (int i = 0; i < debitData.length; i++) {
				
					// finalObject[i][2] = debitData[i][1];
					finalObject[0][2] = "Misc recovery";
					finalObject[0][3] = debitData[0][0];
				
					// finalObject[i][2] = debitData[i][1];
					finalObject[1][2] = "Commutation";
					finalObject[1][3] = debitData[0][1];
				
					// finalObject[i][2] = debitData[i][1];
					finalObject[2][2] = "Recoveries";
					finalObject[2][3] = debitData[0][2];
					
					dDebitTotal = (Double.parseDouble(String.valueOf(debitData[0][0])) +
								   Double.parseDouble(String.valueOf(debitData[0][1])) +
								   Double.parseDouble(String.valueOf(debitData[0][2])));
			}
			
			Double dNetTotal = 0.0;
			dNetTotal = dCreditTotal - dDebitTotal;
			
			finalObject[row][0] = "Total";
			finalObject[row][1] = Math.round(dCreditTotal);
			finalObject[row][2] = "Total";
			finalObject[row][3] = Math.round(dDebitTotal);
			finalObject[row+1][0] = "Net Pension";
			finalObject[row+1][1] = Math.round(dNetTotal);
			finalObject[row+1][2] = "";
			finalObject[row+1][3] = "";
			
			

		} catch (Exception e) {
			logger.error("Error in PensionSlipModel.getPensionInformation() : "
					+ e.getMessage());
		}
		return finalObject;
	}
	
	public void getLoginUserInfo(PensionSlip bean, HttpServletRequest request) {
		try {
			
			String sQuery = " SELECT " +
							"	EMP_TOKEN, " +
							"	EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMPLOYEE_NAME " +
							" FROM " +
							"	HRMS_EMP_OFFC " +
							" WHERE " +
							"	EMP_ID = " + bean.getUserEmpId();
			
			Object[][] outputData = getSqlModel().getSingleResult(sQuery);
			
			if (outputData.length > 0)
			{
				bean.setSEmpCode(String.valueOf(outputData[0][0]));		/* Employee Code */
				bean.setSEmpName(String.valueOf(outputData[0][1]));		/* Employee Name */
				
			}
			
		} catch (Exception e) {
			logger.error("Error in PensionSlipModel.getLoginUserInfo() methos : " + e.getMessage());
		}
	}
	
	public static String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
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
