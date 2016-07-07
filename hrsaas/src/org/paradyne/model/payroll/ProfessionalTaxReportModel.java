package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.ProfessionalTaxRep;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

public class ProfessionalTaxReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ProfessionalTaxReportModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	double sumGloble = 0.0;
	double ptaxArrAmt = 0.0;
	double settlementAmt = 0;
	/** This method sets the ReportDataSet
	 * @param ptax
	 * @param request
	 * @param response
	 * @param reportPath - attachment path
	 * @param logoPath - path of the logo to be used
	 */
	public void generateReport(ProfessionalTaxRep ptax, HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			
			ReportDataSet rds = new ReportDataSet();
			String type = ptax.getReportType();
			rds.setReportType(type);
			String fileName = "ProfessionalTaxReport"+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			
			String reportName = "PROFESSIONAL TAX OF "
				+ Utility.month(Integer.parseInt(ptax.getMonth())).toUpperCase() + " - "
				+ ptax.getYear();
			if (ptax.getToMonth().equals("") || ptax.getMonth().equals(ptax.getToMonth())) {
	
				reportName = "STATEMENT OF PROFESSIONAL TAX FOR THE MONTH OF "
						+ Utility.month(Integer.parseInt(ptax.getMonth())).toUpperCase()
						+ " - " + ptax.getYear();
			} else {
				reportName = "STATEMENT OF PROFESSIONAL TAX FROM "
						+ Utility.month(Integer.parseInt(ptax.getMonth())).toUpperCase()
						+ " - " + ptax.getYear() + " TO "
						+ Utility.month(Integer.parseInt(ptax.getToMonth())).toUpperCase()
						+ " - " + ptax.getToYear();
			}
			
			rds.setReportName(reportName);
			rds.setPageSize("A4");
			rds.setShowPageNo(true);
			rds.setUserEmpId(ptax.getUserEmpId());
			
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			/* Added by Prashant*/
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "PTax_input.action");
			}
			rg = getReport(rg, ptax);
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
	
	/**
	 * Following function is used to display the records branch wise and
	 * department wise
	 * 
	 * @param ptax
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, ProfessionalTaxRep ptax) {
		try {
			
			String filters = "Division : " + ptax.getDivName();
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBorderDetail(0);
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			
			/*
			 * Following query is used to find the debit code for professional tax
			 */
			String profTaxCodeQuery = " SELECT PTAX_DEBIT_CODE FROM HRMS_PTAX_HDR WHERE TO_CHAR(PTAX_DATE,'dd-MON-yyyy') = (select max(PTAX_DATE) FROM HRMS_PTAX_HDR WHERE to_char(PTAX_DATE,'yyyy-mm')<='"
					+ ptax.getYear() + "-" + ptax.getMonth() + "')";
			Object[][] ptax_code = getSqlModel().getSingleResult(profTaxCodeQuery);
			
			if (ptax_code != null && ptax_code.length > 0) {
				
				/**
				 * Following query is used to find the ledger code if salary has been
				 * processed for the selected month and year
				 */
				String ptaxDebitCode = String.valueOf(ptax_code[0][0]);
				String ledgerQuery = "";
				if (ptax.getToMonth().equals("") || ptax.getMonth().equals(ptax.getToMonth())) {
					logger.info("######### SAME MONTH ##########");
					ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
							+ ptax.getMonth()
							+ " AND LEDGER_YEAR="
							+ ptax.getYear()
							+ " AND LEDGER_DIVISION="
							+ ptax.getDivId();
	
				} else {
					ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE ((LEDGER_MONTH >="
							+ ptax.getMonth()
							+ " AND LEDGER_YEAR="
							+ ptax.getYear()
							+ ") OR (LEDGER_MONTH <="
							+ ptax.getToMonth()
							+ " AND LEDGER_YEAR="
							+ ptax.getToYear()
							+ ")) AND LEDGER_DIVISION="
							+ ptax.getDivId();
				}
				Object[][] ledger_code = getSqlModel().getSingleResult(ledgerQuery);
				
				/*Condition check to generate report as per selected report option */
				
				if(ptax.getReportOption().equals("O")) {
					rg = fetchReportForAll(rg, ptax, ledger_code, ptaxDebitCode);
				} else if(ptax.getReportOption().equals("S")) {
					rg = fetchReportForOnlySalary(rg, ptax, ledger_code, ptaxDebitCode);
				} else if(ptax.getReportOption().equals("A")){
					rg = fetchReportForOnlyArrears(rg, ptax, ledger_code, ptaxDebitCode);
				} else if(ptax.getReportOption().equals("se")){
					rg = fetchReportForOnlySettlement(rg, ptax, ledger_code, ptaxDebitCode);
				}
			} else {
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] { { "No data available for selected period." } });
				noDataTable.setCellAlignment(new int[] { 1 });
				noDataTable.setCellWidth(new int[] { 100 });
				rg.addTableToDoc(noDataTable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	
	public org.paradyne.lib.ireportV2.ReportGenerator fetchReportForAll(org.paradyne.lib.ireportV2.ReportGenerator rg, ProfessionalTaxRep ptax, Object [][]ledgerCodeObj, String ptaxDebitCode){
		Object[][] salObj = null;
		Object[][] arrearObj = null;
		Object[][] finalDataObject = null;
		
		try {
			//DISPLAYING SALARY TABLE
			if (ledgerCodeObj != null && ledgerCodeObj.length > 0) {
				if (ptax.getToMonth().equals("")
						|| ptax.getMonth().equals(ptax.getToMonth())) {
					salObj = getFilterwiseRep(ptax, ptaxDebitCode, rg);
				} else {
					salObj = getMultiMonthRep(ptax, ptaxDebitCode, rg);
				}
			} // End of ledger code if condition
			
			// DISPLAYING ARREARS TABLE
			arrearObj = getArrears(ptax, ptaxDebitCode, rg);
			
			
			// DISPLAYING CONSOLIDATED ARREARS TABLE IF SELECTED
			if (ptax.getCheckFlag().equals("Y")) {
				finalDataObject = Utility.consolidateArrearsObject(salObj, arrearObj, 0, new int[] { 3 }, 5);
				
				String[] consCol = { "Employee Id", "Employee Name", "Designation",	"Tax Amount", "Gross Amount" };
				int[] consWidth = { 10, 20, 10, 10, 10 };
				int[] consAlign = { 0, 0, 0, 1, 1 };
				TableDataSet salLabelTable = new TableDataSet();
				salLabelTable.setData(new Object[][] { { "Salary with consolidated arrears" } });
				salLabelTable.setCellAlignment(new int[] { 0 });
				salLabelTable.setCellWidth(new int[] { 100 });
				salLabelTable.setBodyFontStyle(1);
				rg.addTableToDoc(salLabelTable);
				
				TableDataSet finalTable = new TableDataSet();
				finalTable.setHeader(consCol);
				finalTable.setHeaderBorderDetail(3);
				finalTable.setData(finalDataObject);
				finalTable.setHeaderTable(true);
				finalTable.setCellAlignment(consAlign);
				finalTable.setCellWidth(consWidth);
				finalTable.setBlankRowsBelow(1);
				finalTable.setBorderDetail(3);
				rg.addTableToDoc(finalTable);
				
			} 
			
			//DISPLAYING SETTLEMENT TABLE
			fetchReportForOnlySettlement(rg, ptax, arrearObj, ptaxDebitCode);
			
			sumGloble = Double.parseDouble(formatter.format(sumGloble));
			logger.info("sumGloble==" + sumGloble);
			logger.info("ptaxArrAmt==" + ptaxArrAmt);
			logger.info("settlementAmt==" + settlementAmt);
			
			String grandTotalString = "Grand Total Amount ( ";
			double grandTotal = 0.00;
			if(sumGloble != 0.0){
				grandTotalString+= "Salary";
				grandTotal += sumGloble;
			}
			if(ptaxArrAmt != 0.0){
				if(sumGloble != 0.0){
					grandTotalString+= " + ";
				}
				grandTotalString+= " Arrears";
				grandTotal += ptaxArrAmt;
			}
			if(settlementAmt != 0.0){
				if(ptaxArrAmt != 0.0 || sumGloble != 0.0){
					grandTotalString+= " + ";
				}
				grandTotalString+= " Settlement";
				grandTotal += settlementAmt;
			}
			grandTotalString+= " ) : "+grandTotal;
			
			TableDataSet grandTotalTable = new TableDataSet();
			grandTotalTable.setData(new Object[][] { { grandTotalString } });
			grandTotalTable.setCellAlignment(new int[] { 2 });
			grandTotalTable.setCellWidth(new int[] { 100 });
			grandTotalTable.setBodyFontStyle(1);
			grandTotalTable.setBodyBGColor(new BaseColor(200, 200, 200));
			grandTotalTable.setBlankRowsBelow(1);
			rg.addTableToDoc(grandTotalTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	} 
	
	public org.paradyne.lib.ireportV2.ReportGenerator fetchReportForOnlySalary(org.paradyne.lib.ireportV2.ReportGenerator rg, ProfessionalTaxRep ptax, Object [][]ledgerCodeObj, String ptaxDebitCode){
		Object[][] salObj = null;
		try {
			if (ledgerCodeObj != null && ledgerCodeObj.length > 0) {
				if (ptax.getToMonth().equals("") || ptax.getMonth().equals(ptax.getToMonth())) {
					salObj = getFilterwiseRep(ptax, ptaxDebitCode, rg);
				} else {
					salObj = getMultiMonthRep(ptax, ptaxDebitCode, rg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	} 
	
	public org.paradyne.lib.ireportV2.ReportGenerator fetchReportForOnlyArrears(org.paradyne.lib.ireportV2.ReportGenerator rg, ProfessionalTaxRep ptax, Object [][]ledgerCodeObj, String ptaxDebitCode){
		Object[][] arrearObj = null;
		try {
			arrearObj = getArrears(ptax, ptaxDebitCode, rg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	} 
	public org.paradyne.lib.ireportV2.ReportGenerator fetchReportForOnlySettlement(org.paradyne.lib.ireportV2.ReportGenerator rg, ProfessionalTaxRep ptax, Object [][]ledgerCodeObj, String ptaxDebitCode){
		Object[][] settObj = null;
		try {
			settObj = getSettlement(ptaxDebitCode, rg, ptax);
			
			if(settObj != null && settObj.length > 0){
				for (int i = 0; i < settObj.length; i++) {
					settlementAmt += Double.parseDouble(String.valueOf(settObj[i][3]));
				}
				
				String[] settleCol = { "Employee Id", "Employee Name", "Designation",  "Amount",
						"Date Of Leaving", "Settlement Date" };
				int[] settleWidth = { 10, 20, 12, 12, 12, 12 };
				int[] settleAlign = { 0, 0, 0, 2, 1, 1 };
				
				settlementAmt = Double.parseDouble(formatter.format(settlementAmt));

				TableDataSet settlementTableTitle = new TableDataSet();
				settlementTableTitle.setData(new Object[][] { { "Settlement Details" } });
				settlementTableTitle.setCellAlignment(new int[] { 0 });
				settlementTableTitle.setCellWidth(new int[] { 100 });
				settlementTableTitle.setBodyFontStyle(1);
				settlementTableTitle.setBlankRowsBelow(1);
				rg.addTableToDoc(settlementTableTitle);

				TableDataSet settlementTable = new TableDataSet();
				settlementTable.setData(settObj);
				settlementTable.setHeader(settleCol);
				settlementTable.setCellAlignment(settleAlign);
				settlementTable.setCellWidth(settleWidth);
				settlementTable.setBlankRowsBelow(1);
				settlementTable.setBorderDetail(3);
				settlementTable.setHeaderTable(true);
				settlementTable.setHeaderBorderDetail(3);
				rg.addTableToDoc(settlementTable);

				TableDataSet settlementTotalTable = new TableDataSet();
				settlementTotalTable.setData(new Object[][] { { "Total Settlement Amount : "+ Utility.twoDecimals(settlementAmt) } });
				settlementTotalTable.setCellAlignment(new int[] { 2 });
				settlementTotalTable.setCellWidth(new int[] { 100 });
				settlementTotalTable.setBodyFontStyle(1);
				settlementTotalTable.setBodyBGColor(new BaseColor(200, 200, 200));
				settlementTotalTable.setBlankRowsBelow(1);
				rg.addTableToDoc(settlementTotalTable);
			}else{
				TableDataSet noSettlementTable = new TableDataSet();
				noSettlementTable.setData(new Object[][] { {"Settlement data not available for selected period."}});
				noSettlementTable.setCellAlignment(new int[] { 1 });
				noSettlementTable.setCellWidth(new int[] { 100 });
				noSettlementTable.setBodyFontStyle(1);
				noSettlementTable.setBlankRowsBelow(1);
				rg.addTableToDoc(noSettlementTable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	} 
	
	public Object[][] getArrears(ProfessionalTaxRep ptax, String ptaxDebitCode, org.paradyne.lib.ireportV2.ReportGenerator rg) {
		
		String dynamicColmn = "";
		int dynColCount = 0;
		
		if (ptax.getIncomeCheck().equals("true")) {
			dynamicColmn += " ,NVL(ROUND(ARREARS_CREDITS_AMT),0)";
			dynColCount++;
		}
		
		Object[][] arrearAmt = null;
		Object[][] consObj = null;
		String arrCol[] = new String[7 + dynColCount];
		int[] arrCell = new int[7 + dynColCount];
		int[] arrAlign = new int[7 + dynColCount];
		arrCol[0] = "Employee Id";
		arrCol[1] = "Employee Name";
		arrCol[2] = "Designation";
		arrCol[3] = "Amount";
		arrCell[0] = 10;
		arrCell[1] = 20;
		arrCell[2] = 15;
		arrCell[3] = 10;
		arrAlign[0] = 0;
		arrAlign[1] = 0;
		arrAlign[2] = 0;
		arrAlign[3] = 2;
		int colCount = 4;
		if (ptax.getIncomeCheck().equals("true")) {
			arrCol[colCount] = "Gross Income";
			arrCell[colCount] = 15;
			arrAlign[colCount] = 2;
			colCount++;
		}
		arrCol[colCount] = "Month-Year";
		arrCell[colCount] = 10;
		arrAlign[colCount] = 1;
		colCount++;
		
		arrCol[colCount] = "Days";
		arrCell[colCount] = 10;
		arrAlign[colCount] = 1;
		colCount++;
		
		arrCol[colCount] = "Arrear Type";
		arrCell[colCount] = 10;
		arrAlign[colCount] = 1;
		
		try {
			String ledgerData = "";
			String arrearLedgerQuery = "";
			
			if (ptax.getToMonth().equals("") || ptax.getMonth().equals(ptax.getToMonth())) {
				arrearLedgerQuery = "SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH="
						+ ptax.getMonth()
						+ " AND ARREARS_PAID_YEAR="
						+ ptax.getYear() + " " +
						"AND ARREARS_DIVISION=" + ptax.getDivId();
			} else if (ptax.getToYear().equals(ptax.getYear())) {
				arrearLedgerQuery = "SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE (ARREARS_PAID_MONTH  BETWEEN "
						+ ptax.getMonth()
						+ " AND "
						+ ptax.getToMonth()
						+ " AND ARREARS_PAID_YEAR=" + ptax.getYear() + ") "
						+ "AND ARREARS_DIVISION=" + ptax.getDivId();
			} else {
				arrearLedgerQuery = "SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ((ARREARS_PAID_MONTH BETWEEN "
						+ ptax.getMonth()
						+ " AND 12 AND ARREARS_PAID_YEAR="
						+ ptax.getYear()
						+ ") OR (ARREARS_PAID_MONTH  BETWEEN 01 AND "
						+ ptax.getToMonth()
						+ " AND ARREARS_PAID_YEAR="
						+ ptax.getToYear() + ")) "
						+ "AND ARREARS_DIVISION=" + ptax.getDivId();
			}
			Object ledgerCode[][] = getSqlModel().getSingleResult(arrearLedgerQuery);

			if (ledgerCode != null && ledgerCode.length > 0) {
				for (int i = 0; i < ledgerCode.length; i++) {
					if (i == ledgerCode.length - 1) {
						ledgerData += ledgerCode[i][0];
					} else {
						ledgerData += ledgerCode[i][0] + ",";
					}
				}
				String arrearQuery = "";
				
				if (ptax.getToMonth().equals("") || ptax.getMonth().equals(ptax.getToMonth())) {

					arrearQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME, NVL(RANK_NAME, ' '), ARREARS_AMT "
							+ dynamicColmn
							+ " ,TO_CHAR(TO_DATE(ARREARS_MONTH,'mm'),'Mon')||'-'|| ARREARS_YEAR AS MONTHRE,ARREARS_DAYS"
							+ " ,DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional') AS ARRTYPE,NVL(CENTER_NAME,'') FROM HRMS_ARREARS_"
							+ ptax.getYear()
							+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".ARREARS_CODE)"
							+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".EMP_ID)"
							+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " INNER JOIN HRMS_CENTER ON (CENTER_ID=EMP_CENTER)"
							+ " INNER JOIN HRMS_ARREARS_DEBIT_"
							+ ptax.getYear()
							+ " ON(HRMS_ARREARS_DEBIT_"
							+ ptax.getYear()
							+ ".ARREARS_CODE=HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".ARREARS_CODE )"
							+ " WHERE HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".ARREARS_CODE IN("
							+ ledgerData
							+ ") AND ARREARS_DEBIT_CODE="
							+ ptaxDebitCode
							+ " AND "
							+ " HRMS_ARREARS_DEBIT_"
							+ ptax.getYear()
							+ ".ARREARS_MONTH=HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".ARREARS_MONTH "
							+ " AND ARREARS_EMP_ID=HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".EMP_ID";

					if (!(ptax.getBrnId().equals("") || ptax.getBrnId() == null)) {
						arrearQuery += " AND EMP_CENTER IN("
								+ ptax.getBrnId() + ")";
					} else if (!(ptax.getStateId().equals("") || ptax
							.getStateId() == null)) {
						arrearQuery += " AND EMP_CENTER IN( SELECT CENTER_ID FROM HRMS_CENTER WHERE CENTER_PTAX_STATE IN("
								+ ptax.getStateId() + "))";
					}
					if (!ptax.getOnHold().equals("A")) {
						arrearQuery += " AND ARREARS_ONHOLD='"
								+ ptax.getOnHold() + "' ";
					}

					arrearQuery += " ORDER BY TO_DATE(ARREARS_MONTH,'MM'),ARREARS_YEAR,NAME";

					arrearAmt = getSqlModel().getSingleResult(arrearQuery);

				} else if (ptax.getYear().equals(ptax.getToYear())) {

					arrearQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME, NVL(RANK_NAME, ' '), SUM(ARREARS_AMT) "
							+ dynamicColmn
							+ " ,TO_CHAR(TO_DATE(ARREARS_MONTH,'mm'),'Mon')||'-'|| ARREARS_YEAR AS MONTHRE,SUM(ARREARS_DAYS)"
							+ " ,DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional') AS ARRTYPE FROM HRMS_ARREARS_"
							+ ptax.getYear()
							+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".ARREARS_CODE)"
							+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".EMP_ID)"
							+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " INNER JOIN HRMS_ARREARS_DEBIT_"
							+ ptax.getYear()
							+ " ON(HRMS_ARREARS_DEBIT_"
							+ ptax.getYear()
							+ ".ARREARS_CODE=HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".ARREARS_CODE )"
							+ " WHERE HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".ARREARS_CODE IN("
							+ ledgerData
							+ ") AND ARREARS_DEBIT_CODE="
							+ ptaxDebitCode
							+ " AND "
							+ " HRMS_ARREARS_DEBIT_"
							+ ptax.getYear()
							+ ".ARREARS_MONTH=HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".ARREARS_MONTH "
							+ " AND ARREARS_EMP_ID=HRMS_ARREARS_"
							+ ptax.getYear()
							+ ".EMP_ID";

					if (!(ptax.getBrnId().equals("") || ptax.getBrnId() == null)) {
						arrearQuery += " AND EMP_CENTER IN("
								+ ptax.getBrnId() + ")";
					} else if (!(ptax.getStateId().equals("") || ptax
							.getStateId() == null)) {
						arrearQuery += " AND EMP_CENTER IN( SELECT CENTER_ID FROM HRMS_CENTER WHERE CENTER_PTAX_STATE IN("
								+ ptax.getStateId() + "))";
					}
					if (!ptax.getOnHold().equals("A")) {
						arrearQuery += " AND ARREARS_ONHOLD='"
								+ ptax.getOnHold() + "' ";
					}
					arrearQuery += " GROUP BY EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , ARREARS_MONTH, ARREARS_YEAR, ARREARS_TYPE, RANK_NAME "
							+ dynamicColmn
							+ " ORDER BY TO_DATE(ARREARS_MONTH,'MM'),ARREARS_YEAR,NAME";

					arrearAmt = getSqlModel().getSingleResult(arrearQuery);

				} else {

					arrearAmt = getMultiMonthArrear(ptax, ptaxDebitCode, ledgerData);
				}
				if(arrearAmt != null && arrearAmt.length > 0){
					double branchTotal=0.00;					
					if (ptax.getCheckFlag().equals("N")) {
						
						for (int i = 0; i < arrearAmt.length; i++) {
							ptaxArrAmt += Double.parseDouble(String.valueOf(arrearAmt[i][3]));
							branchTotal += Double.parseDouble(String.valueOf(arrearAmt[i][3]));
						}
						
						TableDataSet arrearTableTitle = new TableDataSet();
						arrearTableTitle.setData(new Object[][] { { "Arrears" } });
						arrearTableTitle.setCellAlignment(new int[] { 0 });
						arrearTableTitle.setCellWidth(new int[] { 100 });
						arrearTableTitle.setBodyFontStyle(1);
						rg.addTableToDoc(arrearTableTitle);
						
						TableDataSet arrearTable = new TableDataSet();
						arrearTable.setData(arrearAmt);
						arrearTable.setHeader(arrCol);
						arrearTable.setCellAlignment(arrAlign);
						arrearTable.setCellWidth(arrCell);
						arrearTable.setBorderDetail(3);
						arrearTable.setHeaderTable(true);
						arrearTable.setHeaderBorderDetail(3);
						rg.addTableToDoc(arrearTable);
	
						TableDataSet arrearTotalTitle = new TableDataSet();
						arrearTotalTitle.setData(new Object[][] { { "Total Arrear Amount : "+ Utility.twoDecimals(branchTotal) } });
						arrearTotalTitle.setCellAlignment(new int[] { 2 });
						arrearTotalTitle.setCellWidth(new int[] { 100 });
						arrearTotalTitle.setBodyFontStyle(1);
						arrearTotalTitle.setBodyBGColor(new BaseColor(200, 200, 200));
						arrearTotalTitle.setBlankRowsBelow(1);
						rg.addTableToDoc(arrearTotalTitle);
					}
				}
				
		/*		LinkedHashMap<String, Object[][]> branchMap= (LinkedHashMap<String, Object[][]> )Utility.getGroupObj(arrearAmt, new int[]{6+dynColCount});
				int braCount = 0;
				String[] uniqueKeys = new String[branchMap.size()];
				int noOfBrnDept = 0;
				for (Iterator iterator = branchMap.keySet().iterator(); iterator.hasNext();) {
					double branchTotal=0;
					uniqueKeys[noOfBrnDept] = (String) iterator.next();
					Object[][] brnDataObj = branchMap.get(uniqueKeys[noOfBrnDept]);
					brnDataObj = Utility.removeColumns(brnDataObj, new int[]{dynColCount+6});
					if (brnDataObj != null && brnDataObj.length > 0) {
						for (int i = 0; i < brnDataObj.length; i++) {
							ptaxArrAmt += Double.parseDouble(String.valueOf(brnDataObj[i][3]));
							branchTotal += Double.parseDouble(String.valueOf(brnDataObj[i][3]));
						}
						ptaxArrAmt = Math.round(ptaxArrAmt);
						if (ptax.getCheckFlag().equals("N")) {
							
							TableDataSet arrearTableTitle = new TableDataSet();
							arrearTableTitle.setData(new Object[][] { { "Arrears" } });
							arrearTableTitle.setCellAlignment(new int[] { 0 });
							arrearTableTitle.setCellWidth(new int[] { 100 });
							arrearTableTitle.setBodyFontStyle(1);
							rg.addTableToDoc(arrearTableTitle);
							
							TableDataSet arrearTable = new TableDataSet();
							arrearTable.setData(brnDataObj);
							arrearTable.setHeader(arrCol);
							arrearTable.setCellAlignment(arrAlign);
							arrearTable.setCellWidth(arrCell);
							arrearTable.setBorderDetail(3);
							arrearTable.setHeaderTable(true);
							arrearTable.setHeaderBorderDetail(3);
							rg.addTableToDoc(arrearTable);
		
							TableDataSet arrearTotalTitle = new TableDataSet();
							arrearTotalTitle.setData(new Object[][] { { "Total Arrear Amount : "+ Utility.twoDecimals(branchTotal) } });
							arrearTotalTitle.setCellAlignment(new int[] { 2 });
							arrearTotalTitle.setCellWidth(new int[] { 100 });
							arrearTotalTitle.setBodyFontStyle(1);
							arrearTotalTitle.setBodyBGColor(new BaseColor(200, 200, 200));
							arrearTotalTitle.setBlankRowsBelow(1);
							rg.addTableToDoc(arrearTotalTitle);
						}
				}
				}*/
				}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return arrearAmt;
	}

	/** This method returns the object with settlement details
	 * @param ptax_code
	 * @param rg
	 * @param ptax
	 * @return
	 */
	public Object[][] getSettlement(String ptax_code, org.paradyne.lib.ireportV2.ReportGenerator rg, ProfessionalTaxRep ptax) {
		Object settData[][] = null;
		try {
			logger.info(" ############## IN SETTLEMENT BLOCK ################");
			
			String monYear = ptax.getMonth() + "-" + ptax.getYear();
			String toMonYear = ptax.getToMonth() + "-" + ptax.getToYear();
			
			String settleQuery = "SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, NVL(RANK_NAME, ' '),  NVL(SETTL_AMT,0), "
					+ " NVL(TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),' '),NVL(TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'),' ')"
					+ "  FROM  HRMS_SETTL_HDR "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_SETTL_HDR.SETTL_ECODE)"
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
					+ " INNER JOIN HRMS_SETTL_DEBITS ON(HRMS_SETTL_DEBITS.SETTL_CODE=HRMS_SETTL_HDR.SETTL_CODE)"
					+ " WHERE HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE="
					+ ptax_code
					+ " AND EMP_DIV="
					+ ptax.getDivId()
					+ " AND SETTL_AMT <>0 AND SETTL_MTH_TYPE='CO' ";
			if (ptax.getToMonth().equals("")) {
				/*settleQuery += " AND TO_CHAR(SETTL_SETTLDT,'MM-YYYY')=" + "'"
						+ monYear + "'";*/
				settleQuery += " AND SETTL_SETTLDT = TO_DATE('" +
						monYear  +	"','MM-YYYY')";
				
			} else {
				/*settleQuery += " AND TO_CHAR(SETTL_SETTLDT,'MM-YYYY')>='"
						+ monYear + "' AND TO_CHAR(SETTL_SETTLDT,'MM-YYYY')<='"
						+ toMonYear + "'";
				*/
				settleQuery += " AND SETTL_SETTLDT BETWEEN TO_DATE('" +
						monYear + "','MM-YYYY') AND TO_DATE('" +
						toMonYear +	"','MM-YYYY')";
			}

			if (!(ptax.getBrnId().equals(""))) {
				settleQuery += " AND EMP_CENTER IN(" + ptax.getBrnId() + ")";
			} else if (!(ptax.getStateId().equals(""))) {
				settleQuery += " AND EMP_CENTER IN( SELECT CENTER_ID FROM HRMS_CENTER WHERE CENTER_PTAX_STATE IN("
						+ ptax.getStateId() + "))";
			}
			
			settData = getSqlModel().getSingleResult(settleQuery);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return settData;
	}

	public Object[][] getFilterwiseRep(ProfessionalTaxRep ptax, String ptaxDebit, org.paradyne.lib.ireportV2.ReportGenerator rg) {
		String dynamicColmn = "";
		Object[][] filterList = null;
		String filterQuery = "";
		int count = 0, dynaColCount = 0;
		double sum = 0;
		sumGloble = 0;
		boolean showAllrecords = false;
		Object[][] param = null;
		Object[][] para = null;

		if (ptax.getStateCheck().equals("true")) {
			
			filterQuery = "SELECT DISTINCT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION "
				+ " INNER JOIN HRMS_CENTER ON(LOCATION_CODE=CENTER_PTAX_STATE) ";
			if (!ptax.getStateId().equals("")) {
				filterQuery += " WHERE LOCATION_CODE IN(" + ptax.getStateId()+ ") ORDER BY UPPER(LOCATION_NAME)";
			}
			
		} else if (ptax.getBranchCheck().equals("true")) {
			
			filterQuery = "SELECT DISTINCT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ";
			
			if (!ptax.getBrnId().equals("")) {
				filterQuery += " WHERE CENTER_ID IN(" + ptax.getBrnId()	+ ") ORDER BY UPPER(CENTER_NAME) ";
			} 
		} else {
			showAllrecords = true;
		}
		if (!showAllrecords) {
			filterList = getSqlModel().getSingleResult(filterQuery);
		}

		Object[][] salAmt = null;
		if (ptax.getIncomeCheck().equals("true")) {
			dynamicColmn += " ,NVL(SAL_TOTAL_CREDIT,0)";
			dynaColCount++;
		}
		if (ptax.getDojCheck().equals("true")) {

			dynamicColmn += " ,NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),'')";
			dynaColCount++;
		}
		int totalCol = 4 + dynaColCount;
		String[] colNames = new String[totalCol];
		int[] cellWidth = new int[totalCol];
		int[] alignment = new int[totalCol];
		colNames[0] = "Employee Id";
		colNames[1] = "Employee Name";
		colNames[2] = "Designation";
		colNames[3] = "Tax Amount";
		cellWidth[0] = 15;
		cellWidth[1] = 30;
		cellWidth[2] = 15;
		cellWidth[3] = 20;
		alignment[0] = 0;
		alignment[1] = 0;
		alignment[2] = 0;
		alignment[3] = 2;
		int colCount = 4;
		if (ptax.getIncomeCheck().equals("true")) {
			colNames[colCount] = "Gross Income";
			cellWidth[colCount] = 25;
			alignment[colCount] = 2;
			colCount++;
		}
		if (ptax.getDojCheck().equals("true")) {
			colNames[colCount] = "Joining Date";
			cellWidth[colCount] = 25;
			alignment[colCount] = 1;
		}

		if (filterList != null && filterList.length > 0) {
			/*
			 * Following loop is used to set the professional tax amount for the
			 * various state
			 */

			for (int i = 0; i < filterList.length; i++) {
				//rg.createHeader("");
				String filterInnerQuery = "";
				String filterCodeInner = String.valueOf(filterList[i][0]);
				double totalPTax = 0;
				double totalperFilter = 0.0;
				if (ptax.getBranchCheck().equals("true")) {
					filterInnerQuery = filterCodeInner;
				} else if (ptax.getStateCheck().equals("true")) {
					filterInnerQuery = "SELECT CENTER_ID FROM HRMS_CENTER WHERE CENTER_PTAX_STATE=("
							+ filterCodeInner + ")";
				}
				try {
					/*
					 * Following query is used to to select the professional tax
					 * slabs
					 */

					if (ptax.getSlabCheck().equals("true")) { // to display slab-wise report

						String salAmtQuery = "SELECT DISTINCT SAL_AMOUNT FROM HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ " INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".EMP_ID "
								+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".SAL_LEDGER_CODE "
								+ " AND LEDGER_MONTH="
								+ ptax.getMonth()
								+ " AND LEDGER_YEAR="
								+ ptax.getYear()
								+ ") "
								+ " INNER JOIN HRMS_SALARY_"
								+ ptax.getYear()
								+ " ON (HRMS_SALARY_"
								+ ptax.getYear()
								+ ".EMP_ID = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".EMP_ID AND "
								+ " HRMS_SALARY_"
								+ ptax.getYear()
								+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
								+ ptax.getYear() + ".SAL_LEDGER_CODE ";

						if (!ptax.getOnHold().equals("A")) {
							salAmtQuery += " AND SAL_ONHOLD='"
									+ ptax.getOnHold() + "' ";
						}
						if (ptax.getBranchCheck().equals("true")) {
							if (!(ptax.getBrnId().equals(""))) {
								salAmtQuery += " AND SAL_EMP_CENTER IN("
										+ filterInnerQuery + ") ";
							}
						} else if (ptax.getStateCheck().equals("true")) {
							if (!(ptax.getStateId().equals(""))) {
								salAmtQuery += " AND SAL_EMP_CENTER IN("
										+ filterInnerQuery + ") ";
							}
						}

						salAmtQuery += " ) WHERE SAL_DEBIT_CODE=" + ptaxDebit
								+ " AND SAL_AMOUNT > 0  ";

						salAmt = getSqlModel().getSingleResult(salAmtQuery);
						double individualTotal = 0.0;
						for (int s = 0; s < salAmt.length; s++) {
							String ptaxQuery = "";

							ptaxQuery = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
									+ ", NVL(RANK_NAME, ' '), SAL_AMOUNT "
									+ dynamicColmn
									+ " FROM HRMS_SAL_DEBITS_"
									+ ptax.getYear()
									+ " INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
									+ ptax.getYear()
									+ ".EMP_ID "
									+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
									+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
									+ ptax.getYear()
									+ ".SAL_LEDGER_CODE "
									+ " AND LEDGER_MONTH="
									+ ptax.getMonth()
									+ " AND LEDGER_YEAR="
									+ ptax.getYear()
									+ ") "
									+ " INNER JOIN HRMS_SALARY_"
									+ ptax.getYear()
									+ " ON (HRMS_SALARY_"
									+ ptax.getYear()
									+ ".EMP_ID = HRMS_SAL_DEBITS_"
									+ ptax.getYear()
									+ ".EMP_ID AND "
									+ " HRMS_SALARY_"
									+ ptax.getYear()
									+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
									+ ptax.getYear() + ".SAL_LEDGER_CODE ";

							if (!ptax.getOnHold().equals("A")) {
								ptaxQuery += " AND SAL_ONHOLD='"
										+ ptax.getOnHold() + "' ";
							}
							ptaxQuery += " ) WHERE SAL_DEBIT_CODE="
									+ ptaxDebit
									+ " AND SAL_DIVISION = "
									+ ptax.getDivId()
									+ " AND SAL_EMP_CENTER in("
									+ filterInnerQuery
									+ ") AND SAL_AMOUNT = "
									+ salAmt[s][0]
									+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
							param = getSqlModel().getSingleResult(ptaxQuery);// start

							para = new Object[param.length][totalCol];

							for (int l = 0; l < param.length; l++) {
								for (int j = 0; j < totalCol; j++) {
									para[l][j] = param[l][j];
									if (j == para[l].length - 1) {
										/*
										 * try { totalperFilter = totalperFilter +
										 * Double.parseDouble(String.valueOf(param[l][j])); }
										 * catch (Exception e) { // TODO: handle
										 * exception }
										 */
									}
								}
							}
							if (param != null && param.length > 0) {
								count += 1;
								Object[][] brnStateObj = new Object[1][1];
								if (ptax.getBranchCheck().equals("true")) {
									brnStateObj[0][0] = "Branch :"
											+ String.valueOf(filterList[i][1]);
								} else if (ptax.getStateCheck().equals("true")) {
									brnStateObj[0][0] = "State :"
											+ String.valueOf(filterList[i][1]);
								}
								
								if (ptax.getCheckFlag().equals("N")) {
									TableDataSet brnStateName = new TableDataSet();
									brnStateName.setData(brnStateObj);
									brnStateName.setCellAlignment(new int[] { 0 });
									brnStateName.setCellWidth(new int[] { 100 });
									rg.addTableToDoc(brnStateName);
	
									TableDataSet ptaxSlabTitle = new TableDataSet();
									ptaxSlabTitle.setData(new Object[][] { { "Professional Tax Slab in Rs "+ salAmt[s][0] } });
									ptaxSlabTitle.setCellAlignment(new int[] { 2 });
									ptaxSlabTitle.setCellWidth(new int[] { 100 });
									ptaxSlabTitle.setBodyFontStyle(1);
									ptaxSlabTitle.setBodyBGColor(new BaseColor(200, 200, 200));
									rg.addTableToDoc(ptaxSlabTitle);
								}

								/*para[para.length - 1][totalCol - 3] = "Total Tax Amount";
								para[para.length - 1][totalCol - 2] = "No. Of Employees  :  "
										+ (para.length - 1)
										+ " X "
										+ String.valueOf(salAmt[s][0]);*/
								try {
									totalPTax = Double.parseDouble(String.valueOf(para.length))
											* Double.parseDouble(String.valueOf(salAmt[s][0]));
									
									//para[para.length - 1][totalCol - 1] = totalPTax;
									
									sum += totalPTax;
									individualTotal += totalPTax;
									
									if (ptax.getCheckFlag().equals("N")) {
										TableDataSet paraFinal = new TableDataSet();
										paraFinal.setData(para);
										paraFinal.setHeader(colNames);
										paraFinal.setCellAlignment(alignment);
										paraFinal.setCellWidth(cellWidth);
										paraFinal.setBorderDetail(3);
										rg.addTableToDoc(paraFinal);
										
										TableDataSet slabDataTable = new TableDataSet(); 
										slabDataTable.setData(new Object[][]{{" ",
											"No. Of Employees  :  "+ (para.length)+ " X " + String.valueOf(salAmt[s][0]), " ", "Total Tax Amount : "+totalPTax}});
										slabDataTable.setCellAlignment(alignment);
										slabDataTable.setCellWidth(cellWidth);
										slabDataTable.setBodyFontStyle(1);
										slabDataTable.setBodyBGColor(new BaseColor(200, 200 , 200));
										slabDataTable.setBlankRowsBelow(1);
										rg.addTableToDoc(slabDataTable);
										
									}

								} catch (Exception e) {
									e.printStackTrace();
								}
								sumGloble += sum;
							}
						}
						//logger.info("sumGloble branch========="+individualTotal);
						if(individualTotal !=0.0){
							TableDataSet ptaxSlabTotal = new TableDataSet();
							ptaxSlabTotal.setData(new Object[][] { { "Total Tax Amount: "+ Utility.twoDecimals(individualTotal) } });
							ptaxSlabTotal.setCellAlignment(new int[] { 2 });
							ptaxSlabTotal.setCellWidth(new int[] { 100 });
							ptaxSlabTotal.setBodyFontStyle(1);
							ptaxSlabTotal.setBodyBGColor(new BaseColor(200, 200, 200));
							ptaxSlabTotal.setBlankRowsBelow(1);
							rg.addTableToDoc(ptaxSlabTotal);
						}
					} else {

						String ptaxQuery = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
								
								+ ", NVL(RANK_NAME, ' '),SAL_AMOUNT "
								+ dynamicColmn
								+ " FROM HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ " INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".EMP_ID "
								+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
								+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".SAL_LEDGER_CODE "
								+ " AND LEDGER_MONTH="
								+ ptax.getMonth()
								+ " AND LEDGER_YEAR="
								+ ptax.getYear()
								+ ") "
								+ " INNER JOIN HRMS_SALARY_"
								+ ptax.getYear()
								+ " ON (HRMS_SALARY_"
								+ ptax.getYear()
								+ ".EMP_ID = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".EMP_ID AND "
								+ " HRMS_SALARY_"
								+ ptax.getYear()
								+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
								+ ptax.getYear() + ".SAL_LEDGER_CODE ";
						if (!ptax.getOnHold().equals("A")) {
							ptaxQuery += " AND SAL_ONHOLD='" + ptax.getOnHold()
									+ "' ";
						}
						ptaxQuery += " ) WHERE SAL_DEBIT_CODE="
								+ ptaxDebit
								+ " AND SAL_DIVISION = "
								+ ptax.getDivId()
								+ " AND SAL_EMP_CENTER in("
								+ filterInnerQuery
								+ ") AND SAL_AMOUNT > 0 "
								+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";

						param = getSqlModel().getSingleResult(ptaxQuery);// start

						para = new Object[param.length][4 + dynaColCount];

						for (int l = 0; l < param.length; l++) {
							for (int j = 0; j < totalCol; j++) {
								para[l][j] = param[l][j];
								if (j == 3) {
									try {
										totalperFilter = totalperFilter
												+ Double.parseDouble(String
														.valueOf(param[l][3]));
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}

						if (param != null && param.length > 0) {
							count += 1;
							Object[][] brnStateObj = new Object[1][1];
							if (ptax.getBranchCheck().equals("true")) {
								brnStateObj[0][0] = "Branch :"
										+ String.valueOf(filterList[i][1]);
							} else if (ptax.getStateCheck().equals("true")) {
								brnStateObj[0][0] = "State :"
										+ String.valueOf(filterList[i][1]);
							}
							
							if (ptax.getCheckFlag().equals("N")) {
								TableDataSet brnStateName = new TableDataSet();
								brnStateName.setData(brnStateObj);
								brnStateName.setCellAlignment(new int[] { 0 });
								brnStateName.setCellWidth(new int[] { 100 });
								brnStateName.setBlankRowsBelow(1);
								rg.addTableToDoc(brnStateName);
							}
							
							sum += totalperFilter;
							if (ptax.getCheckFlag().equals("N")) {
								TableDataSet paraFinal = new TableDataSet();
								paraFinal.setData(para);
								paraFinal.setHeader(colNames);
								paraFinal.setCellAlignment(alignment);
								paraFinal.setCellWidth(cellWidth);
								paraFinal.setHeaderTable(true);
								paraFinal.setHeaderBorderDetail(3);
								paraFinal.setBorderDetail(3);
								paraFinal.setBlankRowsBelow(1);
								rg.addTableToDoc(paraFinal);
								
							}
							if (totalperFilter != 0.0) {
								if (ptax.getCheckFlag().equals("N")) {
									
									TableDataSet filterTotalTable = new TableDataSet();
									filterTotalTable.setData(new Object[][]{{"Total Professional Tax of "+ filterList[i][1]+" "+ Utility.twoDecimals(totalperFilter)}});
									filterTotalTable.setCellAlignment(new int[] { 2 });
									filterTotalTable.setCellWidth(new int[] { 100 });
									filterTotalTable.setBodyFontStyle(1);
									filterTotalTable.setBodyBGColor(new BaseColor(200, 200, 200));
									filterTotalTable.setBlankRowsBelow(1);
									rg.addTableToDoc(filterTotalTable);
								}
							}
							sumGloble += sum;
						}
					}
				} catch (Exception e1) {
					logger.error(e1.getMessage());
					e1.printStackTrace();
				}
			} // End of first for loop

		} // End of if condition of filterList
		else if (showAllrecords) {

			/*
			 * Following loop is used to set the professional tax amount for the
			 * various state
			 */

			double totalPTax = 0;
			// rg.addText("Division :"+ptax.getDivName(),0,0,0);
			try {
				/*
				 * Following query is used to to select the professional tax
				 * slabs
				 */
				if (ptax.getSlabCheck().equals("true")) { // to display
															// slab-wise report

					String salAmtQuery = "SELECT DISTINCT SAL_AMOUNT FROM HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ " INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ ".EMP_ID "
							+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ ".SAL_LEDGER_CODE "
							+ " AND LEDGER_MONTH="
							+ ptax.getMonth()
							+ " AND LEDGER_YEAR="
							+ ptax.getYear()
							+ ") "
							+ " INNER JOIN HRMS_SALARY_"
							+ ptax.getYear()
							+ " ON (HRMS_SALARY_"
							+ ptax.getYear()
							+ ".EMP_ID = HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ ".EMP_ID AND "
							+ " HRMS_SALARY_"
							+ ptax.getYear()
							+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
							+ ptax.getYear() + ".SAL_LEDGER_CODE ";

					if (!ptax.getOnHold().equals("A")) {
						salAmtQuery += " AND SAL_ONHOLD='" + ptax.getOnHold()
								+ "' ";
					}

					salAmtQuery += " ) WHERE SAL_DEBIT_CODE=" + ptaxDebit
							+ " AND SAL_AMOUNT > 0  "; 

					salAmt = getSqlModel().getSingleResult(salAmtQuery);
					
					/*Loop for total slabs*/
					
					for (int s = 0; s < salAmt.length; s++) {
						String ptaxQuery = "";

						ptaxQuery = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
								+ ", NVL(RANK_NAME, ' '),SAL_AMOUNT "
								+ dynamicColmn
								+ " FROM HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ " INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".EMP_ID "
								+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
								+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".SAL_LEDGER_CODE "
								+ " AND LEDGER_MONTH="
								+ ptax.getMonth()
								+ " AND LEDGER_YEAR="
								+ ptax.getYear()
								+ ") "
								+ " INNER JOIN HRMS_SALARY_"
								+ ptax.getYear()
								+ " ON (HRMS_SALARY_"
								+ ptax.getYear()
								+ ".EMP_ID = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".EMP_ID AND "
								+ " HRMS_SALARY_"
								+ ptax.getYear()
								+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
								+ ptax.getYear() + ".SAL_LEDGER_CODE ";

						if (!ptax.getOnHold().equals("A")) {
							ptaxQuery += " AND SAL_ONHOLD='" + ptax.getOnHold()+ "' ";
						}
						ptaxQuery += " ) WHERE SAL_DEBIT_CODE="
								+ ptaxDebit
								+ " AND SAL_DIVISION = "
								+ ptax.getDivId()
								+ " AND SAL_AMOUNT = "
								+ salAmt[s][0]
								+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
						param = getSqlModel().getSingleResult(ptaxQuery);

						para = new Object[param.length][totalCol];

						for (int l = 0; l < param.length; l++) {
							for (int j = 0; j < totalCol; j++) {
								para[l][j] = param[l][j];
								if (j == para[l].length - 1) {
									try {
										totalPTax = totalPTax
												+ Double.parseDouble(String
														.valueOf(param[l][j]));
									} catch (Exception e) {
										// TODO: handle exception
										e.printStackTrace();
									}
								}
							}
						}

						if (param != null && param.length > 0) {
							count += 1;
							if (ptax.getCheckFlag().equals("N")) {
								TableDataSet ptaxSlabTitle = new TableDataSet();
								ptaxSlabTitle.setData(new Object[][] { { "Professional Tax Slab in Rs "+ salAmt[s][0] } });
								ptaxSlabTitle.setCellAlignment(new int[] { 2 });
								ptaxSlabTitle.setCellWidth(new int[] { 100 });
								ptaxSlabTitle.setBodyFontStyle(1);
								rg.addTableToDoc(ptaxSlabTitle);
							}

							/*para[para.length - 1][totalCol - 3] = "Total Tax Amount";
							para[para.length - 1][totalCol - 2] = "No. Of Employees  :  "
									+ (para.length - 1)
									+ " X "
									+ String.valueOf(salAmt[s][0]);*/
							try {
								totalPTax = Double.parseDouble(String.valueOf(para.length))
										* Double.parseDouble(String.valueOf(salAmt[s][0]));
								
								//para[para.length - 1][totalCol - 1] = totalPTax;
								
								sum += totalPTax;
								if (ptax.getCheckFlag().equals("N")) {
									TableDataSet paraFinal = new TableDataSet();
									paraFinal.setData(para);
									paraFinal.setHeader(colNames);
									paraFinal.setCellAlignment(alignment);
									paraFinal.setCellWidth(cellWidth);
									paraFinal.setHeaderTable(true);
									paraFinal.setHeaderBorderDetail(3);
									paraFinal.setBorderDetail(3);
									rg.addTableToDoc(paraFinal);
									
									TableDataSet slabDataTable = new TableDataSet(); 
									slabDataTable.setData(new Object[][]{{" ",
										"No. Of Employees  :  "+ (para.length)+ " X " + String.valueOf(salAmt[s][0]), " ", "Total Tax Amount : "+totalPTax}});
									slabDataTable.setCellAlignment(alignment);
									slabDataTable.setCellWidth(cellWidth);
									slabDataTable.setBodyFontStyle(1);
									slabDataTable.setBodyBGColor(new BaseColor(200, 200 , 200));
									slabDataTable.setBlankRowsBelow(1);
									rg.addTableToDoc(slabDataTable);
								}

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				} else {

					String ptaxQuery = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
							
							+ ", NVL(RANK_NAME, ' '),SAL_AMOUNT "
							+ dynamicColmn
							+ " FROM HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ " INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ ".EMP_ID "
							+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
							+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ ".SAL_LEDGER_CODE "
							+ " AND LEDGER_MONTH="
							+ ptax.getMonth()
							+ " AND LEDGER_YEAR="
							+ ptax.getYear()
							+ ") "
							+ " INNER JOIN HRMS_SALARY_"
							+ ptax.getYear()
							+ " ON (HRMS_SALARY_"
							+ ptax.getYear()
							+ ".EMP_ID = HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ ".EMP_ID AND "
							+ " HRMS_SALARY_"
							+ ptax.getYear()
							+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
							+ ptax.getYear() + ".SAL_LEDGER_CODE ";
					if (!ptax.getOnHold().equals("A")) {
						ptaxQuery += " AND SAL_ONHOLD='" + ptax.getOnHold()
								+ "' ";
					}
					ptaxQuery += " ) WHERE SAL_DEBIT_CODE="
							+ ptaxDebit
							+ " AND SAL_DIVISION = "
							+ ptax.getDivId()
							+ " AND SAL_AMOUNT > 0 "
							+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";

					param = getSqlModel().getSingleResult(ptaxQuery);// start

					para = new Object[param.length][4 + dynaColCount];

					for (int l = 0; l < param.length; l++) {
						for (int j = 0; j < totalCol; j++) {
							para[l][j] = param[l][j];
							if (j == 3) {
								try {
									totalPTax = totalPTax+ Double.parseDouble(String.valueOf(param[l][j]));
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}

					if (param != null && param.length > 0) {
						count += 1;
						try {
							sum += totalPTax;
							if (ptax.getCheckFlag().equals("N")) {
								TableDataSet paraFinal = new TableDataSet();
								paraFinal.setData(para);
								paraFinal.setHeader(colNames);
								paraFinal.setCellAlignment(alignment);
								paraFinal.setCellWidth(cellWidth);
								paraFinal.setBlankRowsBelow(1);
								paraFinal.setHeaderTable(true);
								paraFinal.setHeaderBorderDetail(3);
								paraFinal.setBorderDetail(3);
								rg.addTableToDoc(paraFinal);
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e1) {
				logger.error(e1.getMessage());
				e1.printStackTrace();
			}
		}
		if (count == 0) {
			TableDataSet noRecordsTable = new TableDataSet();
			noRecordsTable.setData(new Object[][] { { "No Records found for the selected filters. " } });
			noRecordsTable.setCellAlignment(new int[] { 1 });
			noRecordsTable.setCellWidth(new int[] { 100 });
			noRecordsTable.setBlankRowsAbove(1);
			noRecordsTable.setBlankRowsBelow(1);
			rg.addTableToDoc(noRecordsTable);
		}
		if (sum != 0) {
			sum = Double.parseDouble(formatter.format(sum));
			if (ptax.getCheckFlag().equals("N")) {
	
				TableDataSet grandTotal = new TableDataSet();
				grandTotal.setData(new Object[][] {{"Grand Total Professional Tax in Rs : "+Utility.twoDecimals(sum)}});
				grandTotal.setCellAlignment(new int[] { 2 });
				grandTotal.setCellWidth(new int[] { 100 });
				grandTotal.setBlankRowsBelow(1);
				grandTotal.setBodyBGColor(new BaseColor(200, 200, 200));
				grandTotal.setBodyFontStyle(1);
				rg.addTableToDoc(grandTotal);
			}
			sumGloble = sum;
		}
		return para;
	}

	public Object[][] getMultiMonthRep(ProfessionalTaxRep ptax, String ptaxDebit,	org.paradyne.lib.ireportV2.ReportGenerator rg) {
		
		String dynamicColmn = "";
		String dynamicGroupBy = "";
		Object[][] filterList = null;
		String filterQuery = "";
		int count = 0, dynaColCount = 0;
		double sum = 0;
		sumGloble = 0;
		boolean showAllrecords = false;
		Object[][] param = null;
		Object[][] para = null;

		if (ptax.getStateCheck().equals("true")) {
			
			filterQuery = "SELECT DISTINCT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION "
				+ " INNER JOIN HRMS_CENTER ON(LOCATION_CODE=CENTER_PTAX_STATE) ";
			if (!ptax.getStateId().equals("")) {
				filterQuery += " WHERE LOCATION_CODE IN(" + ptax.getStateId()+ ") ORDER BY UPPER(LOCATION_NAME)";
			}
			
		} else if (ptax.getBranchCheck().equals("true")) {
			
			filterQuery = "SELECT DISTINCT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ";
			
			if (!ptax.getBrnId().equals("")) {
				filterQuery += " WHERE CENTER_ID IN(" + ptax.getBrnId()	+ ") ORDER BY UPPER(CENTER_NAME) ";
			} 
		} else {
			showAllrecords = true;
		}
		
		if (!showAllrecords) {
			filterList = getSqlModel().getSingleResult(filterQuery);
		}

		if (ptax.getIncomeCheck().equals("true")) {
			dynamicColmn += " ,SUM(NVL(SAL_TOTAL_CREDIT,0))";
			dynaColCount++;
		}
		if (ptax.getDojCheck().equals("true")) {

			dynamicColmn += " ,NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),'')";
			dynamicGroupBy += ", EMP_REGULAR_DATE";
			dynaColCount++;
		}
		int totalCol = 4 + dynaColCount;
		String[] colNames = new String[totalCol];
		int[] cellWidth = new int[totalCol];
		int[] alignment = new int[totalCol];

		colNames[0] = "Employee Id";
		colNames[1] = "Employee Name";
		colNames[2] = "Designation";
		colNames[3] = "Tax Amount";
		cellWidth[0] = 15;
		cellWidth[1] = 30;
		cellWidth[2] = 15;
		cellWidth[3] = 20;
		alignment[0] = 0;
		alignment[1] = 0;
		alignment[2] = 0;
		alignment[3] = 2;
		int colCount = 4;
		if (ptax.getIncomeCheck().equals("true")) {
			colNames[colCount] = "Gross Income";
			cellWidth[colCount] = 25;
			alignment[colCount] = 2;
			colCount++;
		}
		if (ptax.getDojCheck().equals("true")) {
			colNames[colCount] = "Joining Date";
			cellWidth[colCount] = 25;
			alignment[colCount] = 1;
		}

		if (filterList!= null && filterList.length > 0) {
			/*
			 * Following loop is used to set the professional tax amount for the
			 * various state
			 */

			for (int i = 0; i < filterList.length; i++) {
				String filterInnerQuery = "";
				String filterCodeInner = String.valueOf(filterList[i][0]);
				double totalperFilter = 0.0;
				if (ptax.getBranchCheck().equals("true")) {
					filterInnerQuery = filterCodeInner;
				} else if (ptax.getStateCheck().equals("true")) {
					filterInnerQuery = "SELECT CENTER_ID FROM HRMS_CENTER WHERE CENTER_PTAX_STATE=("
							+ filterCodeInner + ")";
				}
				try {
					/*
					 * Following query is used to select the professional tax
					 * slabs
					 */

					if (ptax.getToYear().equals(ptax.getYear())) {
						String ptaxQuery = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME "
								+ ", NVL(RANK_NAME, ' '), SUM(SAL_AMOUNT) "
								+ dynamicColmn
								+ " FROM HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ " INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".EMP_ID "
								+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
								+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".SAL_LEDGER_CODE "
								+ " AND LEDGER_MONTH BETWEEN "
								+ ptax.getMonth()
								+ " AND "
								+ ptax.getToMonth()
								+ " AND LEDGER_YEAR="
								+ ptax.getYear()
								+ ") "
								+ " INNER JOIN HRMS_SALARY_"
								+ ptax.getYear()
								+ " ON (HRMS_SALARY_"
								+ ptax.getYear()
								+ ".EMP_ID = HRMS_SAL_DEBITS_"
								+ ptax.getYear()
								+ ".EMP_ID AND "
								+ " HRMS_SALARY_"
								+ ptax.getYear()
								+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
								+ ptax.getYear() + ".SAL_LEDGER_CODE ";
						if (!ptax.getOnHold().equals("A")) {
							ptaxQuery += " AND SAL_ONHOLD='" + ptax.getOnHold()
									+ "' ";
						}
						ptaxQuery += " ) WHERE SAL_DEBIT_CODE="
								+ ptaxDebit
								+ " AND SAL_DIVISION = "
								+ ptax.getDivId()
								+ " AND SAL_EMP_CENTER IN("
								+ filterInnerQuery
								+ ") AND SAL_AMOUNT > 0 "
								+ " GROUP BY EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, RANK_NAME "
								+ dynamicGroupBy;

						ptaxQuery += " ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)";

						param = getSqlModel().getSingleResult(ptaxQuery);// start
					} else {
						param = getMultiMonthData(ptax, ptaxDebit, filterInnerQuery, false);// start
					}

					if (param != null && param.length > 0) {
						para = new Object[param.length][4 + dynaColCount];
	
						for (int l = 0; l < param.length; l++) {
							for (int j = 0; j < totalCol; j++) {
								para[l][j] = param[l][j];
								if (j == 3) {
									try {
										totalperFilter = totalperFilter	+ Double.parseDouble(String.valueOf(param[l][3]));
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
					logger.info("totalperFilter===="+totalperFilter);

					if (param != null && param.length > 0) {
						count += 1;
						Object[][] brnStateObj = new Object[1][1];
						if (ptax.getBranchCheck().equals("true")) {
							brnStateObj[0][0] = "Branch :"
									+ String.valueOf(filterList[i][1]);
						} else if (ptax.getStateCheck().equals("true")) {
							brnStateObj[0][0] = "State :"
									+ String.valueOf(filterList[i][1]);
						}
						
						if (ptax.getCheckFlag().equals("N")) {
							TableDataSet brnStateName = new TableDataSet();
							brnStateName.setData(brnStateObj);
							brnStateName.setCellAlignment(new int[] { 0 });
							brnStateName.setCellWidth(new int[] { 100 });
							brnStateName.setBlankRowsBelow(1);
							rg.addTableToDoc(brnStateName);
							
						}
						try {
							sum += totalperFilter;
							logger.info("sum===="+sum);
							if (ptax.getCheckFlag().equals("N")) {
								
								TableDataSet salLabelTable = new TableDataSet();
								salLabelTable.setData(new Object[][] { { "Salary details" } });
								salLabelTable.setCellAlignment(new int[] { 0 });
								salLabelTable.setCellWidth(new int[] { 100 });
								salLabelTable.setBodyFontStyle(1);
								rg.addTableToDoc(salLabelTable);
								
								TableDataSet paraFinal = new TableDataSet();
								paraFinal.setData(para);
								paraFinal.setHeader(colNames);
								paraFinal.setCellAlignment(alignment);
								paraFinal.setCellWidth(cellWidth);
								paraFinal.setHeaderTable(true);
								paraFinal.setHeaderBorderDetail(3);
								paraFinal.setBorderDetail(3);
								rg.addTableToDoc(paraFinal);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						sumGloble += sum;
						logger.info("sumGloble===="+sumGloble);
					}

				} catch (Exception e1) {
					logger.error(e1.getMessage());
					e1.printStackTrace();

				}

				if (totalperFilter != 0.0) {
					if (ptax.getCheckFlag().equals("N")) {
						
						TableDataSet salLabelTable = new TableDataSet();
						salLabelTable.setData(new Object[][] { { "Salary details" } });
						salLabelTable.setCellAlignment(new int[] { 0 });
						salLabelTable.setCellWidth(new int[] { 100 });
						salLabelTable.setBodyFontStyle(1);
						rg.addTableToDoc(salLabelTable);
						
						TableDataSet filterTotalTable = new TableDataSet();
						filterTotalTable.setData(new Object[][]{{"Total Professional Tax of "+ filterList[i][1]+" "+ Utility.twoDecimals(totalperFilter)}});
						filterTotalTable.setCellAlignment(new int[] { 2 });
						filterTotalTable.setCellWidth(new int[] { 100 });
						filterTotalTable.setBodyFontStyle(1);
						filterTotalTable.setBodyBGColor(new BaseColor(200, 200, 200));
						rg.addTableToDoc(filterTotalTable);
					}
				}
			} // End of first for loop
		} // End of if condition of filterList
		else if (showAllrecords) {

			/*
			 * Following loop is used to set the professional tax amount for the
			 * various state
			 */

			double totalPTax = 0;
			// rg.addText("Division :"+ptax.getDivName(),0,0,0);
			try {
				/*
				 * Following query is used to to select the professional tax
				 * slabs
				 */
				if (Integer.parseInt(ptax.getToYear()) == Integer.parseInt(ptax.getYear())) {
					String ptaxQuery = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
							+ ", NVL(RANK_NAME, ' '), SUM(SAL_AMOUNT) "
							+ dynamicColmn
							+ " FROM HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ " INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ ".EMP_ID "
							+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ ".SAL_LEDGER_CODE "
							+ " AND LEDGER_MONTH BETWEEN "
							+ ptax.getMonth()
							+ " AND "
							+ ptax.getToMonth()
							+ " AND LEDGER_YEAR="
							+ ptax.getYear()
							+ ") "
							+ " INNER JOIN HRMS_SALARY_"
							+ ptax.getYear()
							+ " ON (HRMS_SALARY_"
							+ ptax.getYear()
							+ ".EMP_ID = HRMS_SAL_DEBITS_"
							+ ptax.getYear()
							+ ".EMP_ID AND "
							+ " HRMS_SALARY_"
							+ ptax.getYear()
							+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
							+ ptax.getYear() + ".SAL_LEDGER_CODE ";
					if (!ptax.getOnHold().equals("A")) {
						ptaxQuery += " AND SAL_ONHOLD='" + ptax.getOnHold()
								+ "' ";
					}
					ptaxQuery += " ) WHERE SAL_DEBIT_CODE="
							+ ptaxDebit
							+ " AND SAL_DIVISION = "
							+ ptax.getDivId()
							+ " AND SAL_AMOUNT > 0 "
							+ " GROUP BY HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, RANK_NAME "
							+ dynamicGroupBy
							+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";

					param = getSqlModel().getSingleResult(ptaxQuery);// start
				} else {
					param = getMultiMonthData(ptax, ptaxDebit, "", true);// start
				}

				if (param != null && param.length > 0) {
					para = new Object[param.length][4 + dynaColCount];
					for (int l = 0; l < param.length; l++) {
						for (int j = 0; j < totalCol; j++) {
							para[l][j] = param[l][j];
							if (j == 3) {
								try {
									totalPTax = totalPTax
											+ Double.parseDouble(String
													.valueOf(param[l][j]));
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
				
				if (param != null && param.length > 0) {
					count += 1;
					try {
						sum += totalPTax;
						if (ptax.getCheckFlag().equals("N")) {
							
							TableDataSet salLabelTable = new TableDataSet();
							salLabelTable.setData(new Object[][] { { "Salary details" } });
							salLabelTable.setCellAlignment(new int[] { 0 });
							salLabelTable.setCellWidth(new int[] { 100 });
							salLabelTable.setBodyFontStyle(1);
							rg.addTableToDoc(salLabelTable);
							
							TableDataSet paraFinal = new TableDataSet();
							paraFinal.setData(para);
							paraFinal.setHeader(colNames);
							paraFinal.setCellAlignment(alignment);
							paraFinal.setCellWidth(cellWidth);
							paraFinal.setHeaderTable(true);
							paraFinal.setHeaderBorderDetail(3);
							paraFinal.setBorderDetail(3);
							rg.addTableToDoc(paraFinal);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					sumGloble += sum;
				}

			} catch (Exception e1) {
				logger.error(e1.getMessage());
				e1.printStackTrace();

			}
		}
		if (count == 0) {
			TableDataSet noRecordsTable = new TableDataSet();
			noRecordsTable.setData(new Object[][] { { "No Records found for the selected filters. " } });
			noRecordsTable.setCellAlignment(new int[] { 1 });
			noRecordsTable.setCellWidth(new int[] { 100 });
			rg.addTableToDoc(noRecordsTable);
		}
		if (sum != 0) {
			sum = Double.parseDouble(formatter.format(sum));
			logger.info("sum 2===="+sum);
			if (ptax.getCheckFlag().equals("N")) {
				
				TableDataSet grandTotal = new TableDataSet();
				grandTotal.setData(new Object[][] {{"Grand Total Professional Tax in Rs : "+Utility.twoDecimals(sum)}});
				grandTotal.setCellAlignment(new int[] { 2 });
				grandTotal.setCellWidth(new int[] { 100 });
				grandTotal.setBodyBGColor(new BaseColor(200, 200, 200));
				grandTotal.setBodyFontStyle(1);
				rg.addTableToDoc(grandTotal);
			}
		}
		return para;
	}

	public Object[][] getMultiMonthData(ProfessionalTaxRep ptax,
		String ptaxDebit, String filterInnerQuery, boolean showAllRecords) {
		Object[][] multiMonthObj = null;
		String dynamicColmn = "";
		int dynaColCount = 0;
		String dynamicGroupBy = "";
		boolean netSalFalg = false;
		Object toYearObj[][] = null;
		Object fromYearObj[][] = null;
		String fromYearQuery = "";
		String toYearQuery = "";

		if (ptax.getIncomeCheck().equals("true")) {
			dynamicColmn += " ,SUM(NVL(SAL_TOTAL_CREDIT,0))";
			netSalFalg = true;
			dynaColCount++;

		}
		if (ptax.getDojCheck().equals("true")) {

			dynamicColmn += " ,NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),'')";
			dynamicGroupBy += ",EMP_REGULAR_DATE";
			dynaColCount++;
		}
		fromYearQuery = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME "
				+ ",NVL(RANK_NAME, ' '),SUM(SAL_AMOUNT) "
				+ dynamicColmn+" ,HRMS_EMP_OFFC.EMP_ID "
				+ " FROM HRMS_SAL_DEBITS_"
				+ ptax.getYear()
				+ " INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
				+ ptax.getYear()
				+ ".EMP_ID "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
				+ ptax.getYear()
				+ ".SAL_LEDGER_CODE "
				+ " AND LEDGER_MONTH BETWEEN "
				+ ptax.getMonth()
				+ " AND 12 AND LEDGER_YEAR="
				+ ptax.getYear()
				+ ") "
				+ " INNER JOIN HRMS_SALARY_"
				+ ptax.getYear()
				+ " ON (HRMS_SALARY_"
				+ ptax.getYear()
				+ ".EMP_ID = HRMS_SAL_DEBITS_"
				+ ptax.getYear()
				+ ".EMP_ID AND "
				+ " HRMS_SALARY_"
				+ ptax.getYear()
				+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
				+ ptax.getYear()
				+ ".SAL_LEDGER_CODE ";

		if (!ptax.getOnHold().equals("A")) {

			fromYearQuery += " AND SAL_ONHOLD='" + ptax.getOnHold() + "' ";

		}
		fromYearQuery += " ) WHERE SAL_DEBIT_CODE=" + ptaxDebit
				+ " AND SAL_DIVISION = " + ptax.getDivId()
				+ " AND SAL_AMOUNT > 0 ";

		if (!showAllRecords) {
			fromYearQuery += "AND SAL_EMP_CENTER in(" + filterInnerQuery + ")";
		}
		fromYearQuery += " GROUP BY HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, RANK_NAME "
				+ dynamicGroupBy
				+ " ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)";

		fromYearObj = getSqlModel().getSingleResult(fromYearQuery);

		toYearQuery = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME "
				+ ",NVL(RANK_NAME, ' '),SUM(SAL_AMOUNT) "
				+ dynamicColmn+" ,HRMS_EMP_OFFC.EMP_ID "
				+ " FROM HRMS_SAL_DEBITS_"
				+ ptax.getToYear()
				+ " INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
				+ ptax.getToYear()
				+ ".EMP_ID "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
				+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
				+ ptax.getToYear()
				+ ".SAL_LEDGER_CODE "
				+ " AND LEDGER_MONTH BETWEEN 01 AND "
				+ ptax.getToMonth()
				+ " AND LEDGER_YEAR="
				+ ptax.getToYear()
				+ ") "
				+ " INNER JOIN HRMS_SALARY_"
				+ ptax.getToYear()
				+ " ON (HRMS_SALARY_"
				+ ptax.getToYear()
				+ ".EMP_ID = HRMS_SAL_DEBITS_"
				+ ptax.getToYear()
				+ ".EMP_ID AND "
				+ " HRMS_SALARY_"
				+ ptax.getToYear()
				+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
				+ ptax.getToYear()
				+ ".SAL_LEDGER_CODE ";

		if (!ptax.getOnHold().equals("A")) {

			toYearQuery += " AND SAL_ONHOLD='" + ptax.getOnHold() + "' ";

		}
		toYearQuery += " ) WHERE SAL_DEBIT_CODE=" + ptaxDebit
				+ " AND SAL_DIVISION = " + ptax.getDivId()
				+ " AND SAL_AMOUNT > 0 ";
		if (!showAllRecords) {
			toYearQuery += "AND SAL_EMP_CENTER in(" + filterInnerQuery + ")";
		}
		toYearQuery += " GROUP BY HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, RANK_NAME "
				+ dynamicGroupBy
				+ " ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)";

		toYearObj = getSqlModel().getSingleResult(toYearQuery);

		multiMonthObj = addObjectByEmpId(fromYearObj, toYearObj, dynaColCount, netSalFalg);

		return multiMonthObj;

	}

	public Object[][] addObjectByEmpId(Object obj1[][], Object obj2[][], int dynamicCol, boolean netSalFalg) {
		Object finalObj[][] = null;
		HashMap<String, Object[]> map1 = new HashMap<String, Object[]>();
		HashMap<String, Object[]> map2 = new HashMap<String, Object[]>();

		for (int i = 0; i < obj1.length; i++) {
			map1.put(String.valueOf(obj1[i][4+dynamicCol]), obj1[i]);
		}
		for (int i = 0; i < obj2.length; i++) {
			map2.put(String.valueOf(obj2[i][4+dynamicCol]), obj2[i]);
		}

		for (int i = 0; i < obj1.length; i++) {
			String empId = String.valueOf(obj1[i][4+dynamicCol]);
			if (map2.containsKey(empId)) {
				Object tempObj1[] = map1.get(empId);
				Object tempObj2[] = map2.get(empId);
				if (netSalFalg) {
					tempObj1[4] = Double.parseDouble(String
							.valueOf(tempObj1[4]))
							+ Double.parseDouble(String.valueOf(tempObj2[4]));
				}
				tempObj1[3 + dynamicCol] = Double.parseDouble(String
						.valueOf(tempObj1[3 + dynamicCol]))
						+ Double.parseDouble(String
								.valueOf(tempObj2[3 + dynamicCol]));
				map1.put(empId, tempObj1);
				map2.remove(empId);
			}
		}
		map1.putAll(map2);

		if(obj1.length > 0 ){
			finalObj = new Object[map1.size()][obj1[0].length - 1];
		}
		Vector<Object[]> vect = new Vector<Object[]>();

		for (Iterator<Object[]> iterator = map1.values().iterator(); iterator
				.hasNext();) {
			Object[] tempObjFinal = (Object[]) iterator.next();
			vect.add(tempObjFinal);
		}
		if(finalObj != null && finalObj.length > 0){
			for (int i = 0; i < finalObj.length; i++) {
				Object[] tempObj = (Object[]) vect.get(i);
				for (int j = 0; j < finalObj[i].length; j++) {
					finalObj[i][j] = tempObj[j];
				}
			}
		}
		return finalObj;
	}

	public Object[][] getMultiMonthArrear(ProfessionalTaxRep ptax, String ptaxDebitCode, String ledgerCode) {
		Object arrearObj[][] = null;
		Object fromArrearObj[][] = null;
		Object toArrearObj[][] = null;
		String dynamicColmn = "";
		int dynaColCount = 0;
		if (ptax.getIncomeCheck().equals("true")) {
			dynamicColmn += " ,SUM(NVL(ARREARS_CREDITS_AMT,0))";
			dynaColCount++;
		}
		String fromQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME, NVL(RANK_NAME, ' '), SUM(ARREARS_AMT) "
				+ dynamicColmn
				+ " ,TO_CHAR(TO_DATE(ARREARS_MONTH,'mm'),'Mon')||'-'|| ARREARS_YEAR ,SUM(ARREARS_DAYS)"
				+ " ,DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional') FROM HRMS_ARREARS_"
				+ ptax.getYear()
				+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
				+ ptax.getYear()
				+ ".ARREARS_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"
				+ ptax.getYear()
				+ ".EMP_ID)"
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				+ " INNER JOIN HRMS_ARREARS_DEBIT_"
				+ ptax.getYear()
				+ " ON(HRMS_ARREARS_DEBIT_"
				+ ptax.getYear()
				+ ".ARREARS_CODE=HRMS_ARREARS_"
				+ ptax.getYear()
				+ ".ARREARS_CODE )"
				+ " WHERE HRMS_ARREARS_"
				+ ptax.getYear()
				+ ".ARREARS_CODE IN("
				+ ledgerCode
				+ ") AND ARREARS_DEBIT_CODE="
				+ ptaxDebitCode
				+ " AND "
				+ " HRMS_ARREARS_DEBIT_"
				+ ptax.getYear()
				+ ".ARREARS_MONTH=HRMS_ARREARS_"
				+ ptax.getYear()
				+ ".ARREARS_MONTH"
				+ " AND ARREARS_EMP_ID=HRMS_ARREARS_"
				+ ptax.getYear() + ".EMP_ID" + " AND ARREARS_AMT>0";

		if (!(ptax.getBrnId().equals("") || ptax.getBrnId() == null)) {
			fromQuery += " AND EMP_CENTER IN(" + ptax.getBrnId() + ")";
		} else if (!(ptax.getStateId().equals("") || ptax.getStateId() == null)) {
			fromQuery += " AND EMP_CENTER IN( SELECT CENTER_ID FROM HRMS_CENTER WHERE CENTER_PTAX_STATE IN("
					+ ptax.getStateId() + "))";
		}
		if (!ptax.getOnHold().equals("A")) {
			fromQuery += " AND ARREARS_ONHOLD='"
					+ ptax.getOnHold() + "' ";
		}

		fromQuery += " GROUP BY EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , ARREARS_MONTH, ARREARS_YEAR, ARREARS_TYPE, RANK_NAME "
				+ " ORDER BY TO_DATE(ARREARS_MONTH,'MM'),ARREARS_YEAR,NAME";
		fromArrearObj = getSqlModel().getSingleResult(fromQuery);

		String toQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME, NVL(RANK_NAME, ' '),SUM(ARREARS_AMT) "
				+ dynamicColmn	
				+ " ,TO_CHAR(TO_DATE(ARREARS_MONTH,'mm'),'Mon')||'-'|| ARREARS_YEAR,SUM(ARREARS_DAYS)"
				+ " ,DECODE(ARREARS_TYPE,'M','Monthly','P','Promotional') FROM HRMS_ARREARS_"
				+ ptax.getToYear()
				+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
				+ ptax.getToYear()
				+ ".ARREARS_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"
				+ ptax.getToYear()
				+ ".EMP_ID)"
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				+ " INNER JOIN HRMS_ARREARS_DEBIT_"
				+ ptax.getToYear()
				+ " ON(HRMS_ARREARS_DEBIT_"
				+ ptax.getToYear()
				+ ".ARREARS_CODE=HRMS_ARREARS_"
				+ ptax.getToYear()
				+ ".ARREARS_CODE )"
				+ " WHERE HRMS_ARREARS_"
				+ ptax.getToYear()
				+ ".ARREARS_CODE IN("
				+ ledgerCode
				+ ") AND ARREARS_DEBIT_CODE="
				+ ptaxDebitCode
				+ " AND "
				+ " HRMS_ARREARS_DEBIT_"
				+ ptax.getToYear()
				+ ".ARREARS_MONTH=HRMS_ARREARS_"
				+ ptax.getToYear()
				+ ".ARREARS_MONTH "
				+ " AND ARREARS_EMP_ID=HRMS_ARREARS_"
				+ ptax.getToYear() + ".EMP_ID" + " AND ARREARS_AMT>0";

		if (!(ptax.getBrnId().equals("") || ptax.getBrnId() == null)) {
			toQuery += " AND EMP_CENTER IN(" + ptax.getBrnId() + ")";
		} else if (!(ptax.getStateId().equals("") || ptax.getStateId() == null)) {
			toQuery += " AND EMP_CENTER IN( SELECT CENTER_ID FROM HRMS_CENTER WHERE CENTER_PTAX_STATE IN("
					+ ptax.getStateId() + "))";
		}
		if (!ptax.getOnHold().equals("A")) {
			toQuery += " AND ARREARS_ONHOLD='"
					+ ptax.getOnHold() + "' ";
		}

		toQuery += " GROUP BY EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , ARREARS_MONTH, ARREARS_YEAR, ARREARS_TYPE, RANK_NAME "
				+ " ORDER BY TO_DATE(ARREARS_MONTH,'MM'),ARREARS_YEAR,NAME";
		toArrearObj = getSqlModel().getSingleResult(toQuery);

		arrearObj = Utility.joinArrays(fromArrearObj, toArrearObj, 1, 0);

		return arrearObj;
	}
}
