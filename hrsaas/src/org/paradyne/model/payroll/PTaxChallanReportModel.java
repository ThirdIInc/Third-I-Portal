package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.PTaxChallanReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import com.lowagie.text.Font;

public class PTaxChallanReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	public void generateReport(HttpServletResponse response,
			PTaxChallanReport ptaxChallanBean, HttpServletRequest request,
			String reportPath) {
		try {

			String divisionQuery = " SELECT NVL(HRMS_DIVISION.DIV_NAME,' '), NVL(HRMS_DIVISION.DIV_ADDRESS1,' '), NVL(HRMS_DIVISION.DIV_ADDRESS2,' '), NVL(HRMS_DIVISION.DIV_ADDRESS3, ' '), NVL(HRMS_DIVISION.DIV_PTAX_REG,'N/A'), NVL(HRMS_DIVISION.DIV_ABBR,' ') FROM HRMS_DIVISION WHERE HRMS_DIVISION.DIV_ID="
					+ ptaxChallanBean.getDivCode();
			Object divisionObj[][] = getSqlModel().getSingleResult(
					divisionQuery);

			String stateSelected = "N/A";
			String stateQuery = " SELECT HRMS_LOCATION.LOCATION_NAME FROM HRMS_LOCATION WHERE HRMS_LOCATION.LOCATION_CODE="
					+ ptaxChallanBean.getStateCode();
			Object stateObj[][] = getSqlModel().getSingleResult(stateQuery);
			if (stateObj != null && stateObj.length > 0) {
				stateSelected = String.valueOf(stateObj[0][0]).toUpperCase();
			}
			String year = ptaxChallanBean.getYear();
			int month = Integer.parseInt(ptaxChallanBean.getMonth());

			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String todaysDate = formater.format(date);

			ReportDataSet rds = new ReportDataSet();
			String reportType = ptaxChallanBean.getReportType();
			String fileName = "PTAX_Challan_"+divisionObj[0][5]+"_"+ stateObj[0][0]+"_"+ Utility.month(Integer.parseInt(ptaxChallanBean.getMonth())).substring(0, 3) 
				+ ptaxChallanBean.getYear().substring(ptaxChallanBean.getYear().length()-2, ptaxChallanBean.getYear().length())
				+"_"+ Utility.getRandomNumber(1000); 
			
			String reportPathName = reportPath + fileName + "." + reportType;
			rds.setFileName(fileName);
			rds.setReportName("Professional Tax Report");
			rds.setReportType(reportType);
			rds.setPageSize("A4");
			//rds.setUserEmpId(ptaxChallanBean.getUserEmpId());
			rds.setReportHeaderRequired(false);
			rds.setCompanyAddressRequired(false);
			

			org.paradyne.lib.ireportV2.ReportGenerator rg = null;

			if (reportPath.equals("")) {
				rg = new ReportGenerator(rds, session, context, request);
			} else {
				logger.info("################# ATTACHMENT PATH #############"
						+ reportPath + fileName + "." + reportType);
				rg = new ReportGenerator(rds, reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ reportType);
				request.setAttribute("fileName", fileName + "."
						+ reportType);
				request
						.setAttribute("action",
								"PTaxChallanReport_input.action?path="
										+ reportPathName);
			}
			TableDataSet titleData = new TableDataSet();
			titleData.setData(new Object[][] { { " ", " " },
					{ "Part I-A", "For the Profession Tax Office" } });
			titleData.setCellAlignment(new int[] { 0, 2 });
			titleData.setCellWidth(new int[] { 50, 50 });
			//titleData.setBodyFontStyle(1);
			titleData.setBorder(false);
			// titleData.setHeaderTable(false);
			titleData.setBlankRowsAbove(2);
			rg.addTableToDoc(titleData);

			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[1][1];
			obj[0][0] = "FORM III RETURN-CUM CHALLAN";
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontStyle(1);
			subtitleName.setCellColSpan(new int[] {4});
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(false);
			rg.addTableToDoc(subtitleName);

			TableDataSet titleData1 = new TableDataSet();
			if (divisionObj != null && divisionObj.length > 0) {
				titleData1
						.setData(new Object[][] {
								{ "Professional Tax Registration Certificate No. :"
										+ String.valueOf(divisionObj[0][4]) },
								{ "THE "
										+ stateSelected
										+ " STATE TAX ON PROFESSIONS, TRADES, CALLINGS AND EMPLOYMENTS ACT, 1975 AND RULE 11, 11-A, 11-B, 11-C" },
								{ "" },
								{ "0028, Other Taxes on Income and Expenditure-Taxes on Professionals, Trades, Callings and Employments-Taxes on Employments " } });
				titleData1.setCellAlignment(new int[] { 1 });
				titleData1.setCellWidth(new int[] { 100 });
				titleData1.setBorder(false);
				titleData1.setCellColSpan(new int[] {4});
				titleData1.setHeaderTable(false);
				titleData1.setBlankRowsBelow(1);
			} else {
				titleData1
						.setData(new Object[][] {
								{ "Professional Tax Registration Certificate No. : N/A" },
								{ "THE "
										+ stateSelected
										+ " STATE TAX ON PROFESSIONS, TRADES, CALLINGS AND EMPLOYMENTS ACT, 1975 AND RULE 11 ,11-A, 11-B, 11-C" },
								{ "" },
								{ "0028, Other Taxes on Income and Expenditure-Taxes on Professionals, Trades, Callings and Employments-Taxes on Employments " } });
				titleData1.setCellAlignment(new int[] { 1 });
				titleData1.setCellWidth(new int[] { 100 });
				titleData1.setBorder(false);
				titleData1.setCellColSpan(new int[] {4});
				titleData1.setHeaderTable(false);
				titleData1.setBlankRowsBelow(1);
			}
			
			rg.addTableToDoc(titleData1);

			String profTaxCodeQuery = " SELECT HRMS_PTAX_HDR.PTAX_DEBIT_CODE,HRMS_PTAX_HDR.PTAX_CODE FROM HRMS_PTAX_HDR WHERE TO_CHAR(HRMS_PTAX_HDR.PTAX_DATE,'dd-MON-yyyy') = (select max(HRMS_PTAX_HDR.PTAX_DATE) FROM HRMS_PTAX_HDR WHERE to_char(HRMS_PTAX_HDR.PTAX_DATE,'yyyy-mm')<='"
					+ ptaxChallanBean.getYear()
					+ "-"
					+ ptaxChallanBean.getMonth()
					+ "'  AND HRMS_PTAX_HDR.PTAX_LOCATION="
					+ ptaxChallanBean.getStateCode() + " )";
			Object[][] ptaxcodeObj = getSqlModel().getSingleResult(
					profTaxCodeQuery);

			Object dataObject[][] = null;
			Object[][] slabObject = null;
			if (ptaxcodeObj != null && ptaxcodeObj.length > 0) {
				String query = " SELECT HRMS_PTAX_DTL.PTAX_UPTOAMT, NVL(HRMS_PTAX_DTL.PTAX_FIXEDAMT,0), HRMS_PTAX_DTL.PTAX_VARMTH, NVL(TRIM(HRMS_PTAX_DTL.PTAX_VARAMT),'0')"
						+ " FROM HRMS_PTAX_DTL WHERE HRMS_PTAX_DTL.PTAX_CODE="
						+ String.valueOf(ptaxcodeObj[0][1])
						+ " ORDER BY HRMS_PTAX_DTL.PTAX_FROMAMT ";
				dataObject = getSqlModel().getSingleResult(query);
			}

			if (dataObject != null && dataObject.length > 0) {
				slabObject = new Object[dataObject.length][1];
				for (int i = 0; i < dataObject.length; i++) {
					if (i == 0) {
						slabObject[i][0] = "From 0 To "
								+ String.valueOf(dataObject[i][0]);
					} else if (i > 0 && i < dataObject.length) {
						slabObject[i][0] = "From "
								+ (Integer.parseInt(String
										.valueOf(dataObject[i - 1][0])) + 1)
								+ " To " + String.valueOf(dataObject[i][0]);
					}
				}
			}
			String ledgerQuery = "SELECT HRMS_SALARY_LEDGER.LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE HRMS_SALARY_LEDGER.LEDGER_MONTH="
					+ ptaxChallanBean.getMonth()
					+ " AND HRMS_SALARY_LEDGER.LEDGER_YEAR="
					+ ptaxChallanBean.getYear()
					+ " AND HRMS_SALARY_LEDGER.LEDGER_DIVISION="
					+ ptaxChallanBean.getDivCode();
			Object[][] ledgerCodeObj = getSqlModel().getSingleResult(
					ledgerQuery);

			if (dataObject != null && dataObject.length > 0
					&& ledgerCodeObj != null && ledgerCodeObj.length > 0) {

				String header[] = {
						"Employees whose monthly Salaries, Wages are ",
						"Rate of tax per month", "No of Employees",
						"Amount of tax deducted" };
				int[] cellwidth = { 45, 30, 25, 25 };
				int[] alignment = { 0, 2, 2, 2 };

				Object[][] empSalObj = new Object[slabObject.length][4];

				int taxAmountTotal = 0;
				if (slabObject != null && slabObject.length > 0) {
					String noOfEmployees = "";
					for (int i = 0; i < slabObject.length; i++) {
						if (String.valueOf(dataObject[i][2]).equals(
								ptaxChallanBean.getMonth())) {
							noOfEmployees = "SELECT COUNT(*) FROM HRMS_SAL_DEBITS_"
									+ year
									+ " WHERE HRMS_SAL_DEBITS_"
									+ year + ".SAL_DEBIT_CODE="
									+ String.valueOf(ptaxcodeObj[0][0])
									+ " AND HRMS_SAL_DEBITS_"
									+ year + ".SAL_LEDGER_CODE="
									+ String.valueOf(ledgerCodeObj[0][0])
									+ " AND HRMS_SAL_DEBITS_"
									+ year + ".SAL_AMOUNT="
									+ String.valueOf(dataObject[i][3]);
						} else {
							noOfEmployees = "SELECT COUNT(*) FROM HRMS_SAL_DEBITS_"
									+ year
									+ " WHERE HRMS_SAL_DEBITS_"
									+ year + ".SAL_DEBIT_CODE="
									+ String.valueOf(ptaxcodeObj[0][0])
									+ " AND  HRMS_SAL_DEBITS_"
									+ year + ".SAL_LEDGER_CODE="
									+ String.valueOf(ledgerCodeObj[0][0])
									+ " AND HRMS_SAL_DEBITS_"
									+ year + ".SAL_AMOUNT="
									+ String.valueOf(dataObject[i][1]);
						}
						Object[][] noOfEmpObj = getSqlModel().getSingleResult(
								noOfEmployees);

						empSalObj[i][0] = String.valueOf(slabObject[i][0]);
						empSalObj[i][1] = String.valueOf(dataObject[i][1]);
						if (noOfEmpObj != null && noOfEmpObj.length > 0) {
							empSalObj[i][2] = String.valueOf(noOfEmpObj[0][0]);
						} else {
							empSalObj[i][2] = "0";
						}
						if (String.valueOf(dataObject[i][2]).equals(
								ptaxChallanBean.getMonth())) {
							empSalObj[i][3] = formatter.format(Integer.parseInt(String
									.valueOf(empSalObj[i][2])) * Integer
									.parseInt(String.valueOf(dataObject[i][3])));
						} else {
							empSalObj[i][3] = formatter.format(Integer.parseInt(String
									.valueOf(empSalObj[i][2])) * Integer
									.parseInt(String.valueOf(dataObject[i][1])));
						}

						taxAmountTotal += (Double.parseDouble(String
								.valueOf(empSalObj[i][3])));
					}
				}

				TableDataSet tableData2 = new TableDataSet();
				tableData2.setData(empSalObj);
				tableData2.setHeader(header);
				tableData2.setHeaderBorderDetail(3);
				tableData2.setCellAlignment(alignment);
				tableData2.setCellWidth(cellwidth);
				tableData2.setBorder(true);
				tableData2.setBorderDetail(3);
				tableData2.setHeaderTable(true);
				tableData2.setHeaderFontStyle(-1);
				tableData2.setHeaderLines(false);
				rg.addTableToDoc(tableData2);

				TableDataSet tableData3 = new TableDataSet();
				tableData3.setData(new Object[][] { {"Tax Amount \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Rs.",formatter.format(taxAmountTotal)}, 
													{"Interest Amount \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Rs.", " " }, 
													{"Less : Excess tax paid, if any, in the previous Year/Qtr./Month \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Rs."," "}, 
													{"Net amount paid \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Rs.",formatter.format(taxAmountTotal)}});
				tableData3.setCellAlignment(new int[] { 0, 2 });
				tableData3.setCellWidth(new int[] { 80, 20 });
				tableData3.setBorder(true);
				tableData3.setBorderDetail(3);
				tableData3.setCellColSpan(new int[] {3,1});
				tableData3.setBodyFontStyle(1);
				tableData3.setHeaderTable(false);
				rg.addTableToDoc(tableData3);

				TableDataSet tableData4 = new TableDataSet();
				tableData4.setData(new Object[][] {
						{
								"Professional Tax Registration \nCertificate No. : "
										+ String.valueOf(divisionObj[0][4]),
								"Period from", "Period to" },
						{ " ", Utility.month(month) + ", " + year,
								Utility.month(month) + ", " + year } });
				tableData4.setCellAlignment(new int[] { 0, 1, 1 });
				tableData4.setCellWidth(new int[] { 60, 20, 20 });
				tableData4.setBorder(true);
				tableData4.setBorderDetail(3);
				tableData4.setCellColSpan(new int[] {2,1,1});
				tableData4.setHeaderTable(false);
				rg.addTableToDoc(tableData4);

				if (divisionObj != null && divisionObj.length > 0) {
					TableDataSet tableData5 = new TableDataSet();
					tableData5.setData(new Object[][] { { String
							.valueOf(divisionObj[0][0])
							+ "\n\n"
							+ String.valueOf(divisionObj[0][1]) + " "
							+ String.valueOf(divisionObj[0][2]) + " "
							+ String.valueOf(divisionObj[0][3]) } });
					tableData5.setCellAlignment(new int[] { 0 });
					tableData5.setCellWidth(new int[] { 100 });
					tableData5.setBorder(true);
					tableData5.setBorderDetail(3);
					tableData5.setCellColSpan(new int[] {4});
					rg.addTableToDoc(tableData5);
				} else {
					TableDataSet tableData5 = new TableDataSet();
					tableData5
							.setData(new Object[][] { { "Company name : \n\nAddress : " } });
					tableData5.setCellAlignment(new int[] { 0 });
					tableData5.setCellWidth(new int[] { 100 });
					tableData5.setBorder(true);
					tableData5.setBorderDetail(3);
					tableData5.setCellColSpan(new int[] {4});
					rg.addTableToDoc(tableData5);
				}

				TableDataSet tableData6 = new TableDataSet();
				tableData6
						.setData(new Object[][] { { "\nThe above statements are true to the best of my knowledge and belief.\n\n" } });
				tableData6.setCellAlignment(new int[] { 1 });
				tableData6.setCellWidth(new int[] { 100 });
				tableData6.setBodyFontStyle(1);
				tableData6.setBorder(true);
				tableData6.setBorderDetail(3);
				tableData6.setCellColSpan(new int[] {4});
				rg.addTableToDoc(tableData6);

				TableDataSet tableData7 = new TableDataSet();
				tableData7
						.setData(new Object[][] {
								{ "\nDate : " + todaysDate + " \n" },
								{ "\nPlace :" } });
				tableData7.setCellAlignment(new int[] { 0 });
				tableData7.setCellWidth(new int[] { 100 });
				tableData7.setBodyFontStyle(1);
				tableData7.setBorder(true);
				tableData7.setBorderDetail(3);

				TableDataSet tableData8 = new TableDataSet();
				tableData8
						.setData(new Object[][] { { "\n\nSignature and Designation\n" } });
				tableData8.setCellAlignment(new int[] { 2 });
				tableData8.setCellWidth(new int[] { 100 });
				tableData8.setBodyFontStyle(1);
				tableData8.setBorder(false);

				HashMap<String, Object> map = rg.joinTableDataSet(tableData7,
						tableData8, true, 20);
				rg.addTableToDoc(map);

				TableDataSet tableData9 = new TableDataSet();
				tableData9
						.setData(new Object[][] { { "For the Treasury Use Only" } });
				tableData9.setCellAlignment(new int[] { 1 });
				tableData9.setCellWidth(new int[] { 100 });
				tableData9.setBodyFontStyle(1);
				tableData9.setBorder(true);
				tableData9.setBorderDetail(3);
				tableData9.setCellColSpan(new int[] {4});
				//tableData9.setHeaderTable(true);
				rg.addTableToDoc(tableData9);

				TableDataSet tableData10 = new TableDataSet();
				tableData10.setHeader(new String[] { "Rupees (in Words)",
						"Rupees(in Figures)" });
				tableData10.setData(new Object[][] { {
						Utility.convert(taxAmountTotal).toUpperCase(),
						formatter.format(taxAmountTotal) } });
				tableData10.setCellAlignment(new int[] { 1, 2 });
				tableData10.setCellWidth(new int[] { 70, 30 });
				tableData10.setBorder(true);
				tableData10.setBorderDetail(3);
				tableData10.setHeaderTable(false);
				tableData10.setHeaderBorderDetail(3);
				tableData10.setCellColSpan(new int[] {2,2});
				rg.addTableToDoc(tableData10);

				TableDataSet tableData11 = new TableDataSet();
				tableData11.setData(new Object[][] { { "Date of Entry : ",
						"Challan No : " } });
				tableData11.setCellAlignment(new int[] { 0, 0 });
				tableData11.setCellWidth(new int[] { 70, 30 });
				tableData11.setBodyFontStyle(1);
				tableData11.setBorder(true);
				tableData11.setBorderDetail(3);
				tableData11.setCellColSpan(new int[] {3,1});
				//tableData11.setHeaderTable(true);
				rg.addTableToDoc(tableData11);

				TableDataSet tableData12 = new TableDataSet();
				tableData12
						.setData(new Object[][] { { "\n\n\n\n\nTreasurer\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
								 "Accountant\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTreasury Officer/Agent or Manager\n\n"}});
				tableData12.setCellAlignment(new int[] { 0 });
				tableData12.setCellWidth(new int[] { 100 });
				tableData12.setBodyFontStyle(1);
				tableData12.setBorder(true);
				tableData12.setBorderDetail(3);
				//tableData12.setHeaderTable(true);
				tableData12.setCellColSpan(new int[] {4});
				rg.addTableToDoc(tableData12);

				rg.addProperty(rg.PAGE_BREAK);

				/* Page 2 for receipts/challan begins */
				TableDataSet subtitleName2 = new TableDataSet();
				Object obj2[][] = new Object[1][1];
				obj2[0][0] = "For the Treasury\n\nFORM III RETURN-CUM CHALLAN \nProfessional Tax Registration Certificate No. :\n\nPart II\n\n\n\nTHE " + stateSelected + " STATE TAX ON PROFESSIONS, TRADES, CALLINGS AND EMPLOYMENTS ACT, 1975 AND RULE 11, 11-A, 11-B, 11-C \n\n0028, Other Taxes on Income and Expenditure-Taxes on Professionals, Trades, Callings and Employments-Taxes on Employments";

				subtitleName2.setData(obj2);
				subtitleName2.setCellAlignment(new int[] { 1 });
				subtitleName2.setCellWidth(new int[] { 100 });
				subtitleName2.setBorder(true);
				subtitleName2.setBorderDetail(3);
				subtitleName2.setCellColSpan(new int[] {3});
				//subtitleName2.setHeaderTable(true);

				TableDataSet subtitleName3 = new TableDataSet();
				Object obj3[][] = new Object[1][1];
				obj3[0][0] = "For the Payee\n\nFORM III RETURN-CUM CHALLAN \nProfessional Tax Registration Certificate No. :\n\nPart III\n\n\n\nTHE " + stateSelected + " STATE TAX ON PROFESSIONS, TRADES, CALLINGS AND EMPLOYMENTS ACT, 1975 AND RULE 11, 11-A, 11-B, 11-C \n\n0028, Other Taxes on Income and Expenditure-Taxes on Professionals, Trades, Callings and Employments-Taxes on Employments";
				subtitleName3.setData(obj3);
				subtitleName3.setCellAlignment(new int[] { 1 });
				subtitleName3.setCellWidth(new int[] { 100 });
				subtitleName3.setBorder(true);
				subtitleName3.setBorderDetail(3);
				subtitleName3.setCellColSpan(new int[] {3});
				//subtitleName3.setHeaderTable(true);

				HashMap<String, Object> titleMap1 = rg.joinTableDataSet(
						subtitleName2, subtitleName3, true, 50);
				rg.addTableToDoc(titleMap1);

				TableDataSet tableChallanData = new TableDataSet();
				tableChallanData
						.setData(new Object[][] {
								{
										"Tax Amount\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRs.",formatter.format(taxAmountTotal)}, 
								{
										"Interest Amount\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRs.", " " }, 
								{
										"Less : Excess tax paid, if any, in the previous Year/Qtr./Month\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRs."," "}, 
								{
										"Net amount paid\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRs.",formatter.format(taxAmountTotal)}});
				tableChallanData.setCellAlignment(new int[] { 0, 2 });
				tableChallanData.setCellWidth(new int[] { 80, 20 });
				tableChallanData.setBodyFontStyle(1);
				tableChallanData.setBorder(true);
				tableChallanData.setBorderDetail(3);
				tableChallanData.setCellColSpan(new int[] {2,1});
				tableChallanData.setHeaderTable(false);

				HashMap<String, Object> challanMap1 = rg.joinTableDataSet(
						tableChallanData, tableChallanData, true, 50);
				rg.addTableToDoc(challanMap1);

				TableDataSet tableChallanData2 = new TableDataSet();
				tableChallanData2.setData(new Object[][] {
						{
								"Professional Tax Registration \nCertificate No. : "
										+ String.valueOf(divisionObj[0][4]),
								"Period from", "Period to" },
						{ " ", Utility.month(month) + ", " + year,
								Utility.month(month) + ", " + year } });
				tableChallanData2.setCellAlignment(new int[] { 0, 1, 1 });
				tableChallanData2.setCellWidth(new int[] { 60, 20, 20 });
				tableChallanData2.setBorder(true);
				tableChallanData2.setBorderDetail(3);
				tableChallanData2.setHeaderTable(false);

				HashMap<String, Object> challanMap2 = rg.joinTableDataSet(
						tableChallanData2, tableChallanData2, true, 50);
				rg.addTableToDoc(challanMap2);

				TableDataSet tableChallanData4 = new TableDataSet();
				tableChallanData4.setData(new Object[][] { { String
						.valueOf(divisionObj[0][0])
						+ "\n\n"
						+ String.valueOf(divisionObj[0][1]) + " "
						+ String.valueOf(divisionObj[0][2]) + " "
						+ String.valueOf(divisionObj[0][3]) } });
				tableChallanData4.setCellAlignment(new int[] { 0 });
				tableChallanData4.setCellWidth(new int[] { 100 });
				tableChallanData4.setBorder(true);
				tableChallanData4.setBorderDetail(3);
				tableChallanData4.setCellColSpan(new int[] {3});

				HashMap<String, Object> challanMap3 = rg.joinTableDataSet(
						tableChallanData4, tableChallanData4, true, 50);
				rg.addTableToDoc(challanMap3);

				TableDataSet tableChallanData6 = new TableDataSet();
				tableChallanData6
						.setData(new Object[][] { { "\nThe above statements are true to the best of my knowledge and belief.\n\n" } });
				tableChallanData6.setCellAlignment(new int[] { 1 });
				tableChallanData6.setCellWidth(new int[] { 100 });
				tableChallanData6.setBodyFontStyle(1);
				tableChallanData6.setBorder(true);
				tableChallanData6.setBorderDetail(3);
				tableChallanData6.setCellColSpan(new int[] {3});
				//tableChallanData6.setHeaderTable(true);

				HashMap<String, Object> challanMap4 = rg.joinTableDataSet(
						tableChallanData6, tableChallanData6, true, 50);
				rg.addTableToDoc(challanMap4);

				TableDataSet tableChallanData7 = new TableDataSet();
				tableChallanData7
						.setData(new Object[][] {
								{ "\nDate : " + todaysDate + " \n" },
								{ "\nPlace :" } });
				tableChallanData7.setCellAlignment(new int[] { 0 });
				tableChallanData7.setCellWidth(new int[] { 100 });
				tableChallanData7.setBodyFontStyle(1);
				tableChallanData7.setBorder(true);
				tableChallanData7.setBorderDetail(3);
				//tableChallanData7.setHeaderTable(true);

				TableDataSet tableChallanData8 = new TableDataSet();
				tableChallanData8
						.setData(new Object[][] { { "\n\nSignature and Designation\n" } });
				tableChallanData8.setCellAlignment(new int[] { 2 });
				tableChallanData8.setCellWidth(new int[] { 100 });
				tableChallanData8.setBodyFontStyle(1);
				tableChallanData8.setBorder(false);
				//tableChallanData8.setBorderDetail(3);
				//tableChallanData8.setHeaderTable(true);

				HashMap<String, Object> map3 = rg.joinTableDataSet(
						tableChallanData7, tableChallanData8, true, 40);
				HashMap<String, Object> challanMap5 = rg.joinTableDataSet(map3,
						map3, true, 50);
				rg.addTableToDoc(challanMap5);

				TableDataSet tableChallanData9 = new TableDataSet();
				tableChallanData9
						.setData(new Object[][] { { "For the Treasury Use Only" } });
				tableChallanData9.setCellAlignment(new int[] { 1 });
				tableChallanData9.setCellWidth(new int[] { 100 });
				tableChallanData9.setBodyFontStyle(1);
				tableChallanData9.setBorder(true);
				tableChallanData9.setBorderDetail(3);
				tableChallanData9.setCellColSpan(new int[] {3});
				//tableChallanData9.setHeaderTable(true);
				tableChallanData9.setHeaderBorderDetail(3);

				HashMap<String, Object> challanMap6 = rg.joinTableDataSet(
						tableChallanData9, tableChallanData9, true, 50);
				rg.addTableToDoc(challanMap6);

				TableDataSet tableChallanData10 = new TableDataSet();
				tableChallanData10.setHeader(new String[] {
						"Rupees (in Words)", "Rupees(in Figures)" });
				tableChallanData10.setData(new Object[][] { {
						Utility.convert(taxAmountTotal).toUpperCase(),
						formatter.format(taxAmountTotal) } });
				tableChallanData10.setCellAlignment(new int[] { 1, 2 });
				tableChallanData10.setCellWidth(new int[] { 60, 40 });
				tableChallanData10.setBorder(true);
				tableChallanData10.setBorderDetail(3);
				tableChallanData10.setCellColSpan(new int[] {2,1});
				tableChallanData10.setHeaderTable(false);
				tableChallanData10.setHeaderBorderDetail(3);
				

				HashMap<String, Object> challanMap7 = rg.joinTableDataSet(
						tableChallanData10, tableChallanData10, true, 50);
				rg.addTableToDoc(challanMap7);

				TableDataSet tableChallanData11 = new TableDataSet();
				tableChallanData11.setData(new Object[][] { {
						"Date of Entry : ", "Challan No : " } });
				tableChallanData11.setCellAlignment(new int[] { 0, 0 });
				tableChallanData11.setCellWidth(new int[] { 70, 30 });
				tableChallanData11.setBodyFontStyle(1);
				tableChallanData11.setBorder(true);
				tableChallanData11.setBorderDetail(3);
				tableChallanData11.setCellColSpan(new int[] {2,1});
				//tableChallanData11.setHeaderTable(true);

				HashMap<String, Object> challanMap8 = rg.joinTableDataSet(
						tableChallanData11, tableChallanData11, true, 50);
				rg.addTableToDoc(challanMap8);

				TableDataSet tableChallanData12 = new TableDataSet();
				tableChallanData12.setData(new Object[][] { {"\n\n\n\n\nTreasurer\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
						"Accountant\t\t\t\t\t\t\t\t\t\t\t\tTreasury Officer/Agent or Manager\n\n"}});
				tableChallanData12.setCellAlignment(new int[] { 0 });
				tableChallanData12.setCellWidth(new int[] { 100 });
				tableChallanData12.setBodyFontStyle(1);
				tableChallanData12.setBorder(true);
				tableChallanData12.setBorderDetail(3);
				tableChallanData12.setCellColSpan(new int[] {3});
				//tableChallanData12.setHeaderTable(true);

				HashMap<String, Object> challanMap9 = rg.joinTableDataSet(
						tableChallanData12, tableChallanData12, true, 50);
				rg.addTableToDoc(challanMap9);
			} else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "P-TAX SLAB NOT DEFINED" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontStyle(1);
				noData.setBorder(false);
				//noData.setHeaderTable(true);
				rg.addTableToDoc(noData);
			}
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

}
