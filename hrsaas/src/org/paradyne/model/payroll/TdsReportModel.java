package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.TDSReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

/**
 * 
 * @author Pradeep Kumar Sahoo Date:04-03-2009
 * 
 */

public class TdsReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	NumberFormat formatter = new DecimalFormat("#0.00");

	/**
	 * following function is called when Tds report button is clicked and Report
	 * Option type is All or Only Arrears
	 * 
	 * @param rg
	 * @param tds
	 * @return
	 */
	public ReportGenerator getTdsReportArrears(ReportGenerator rg, TDSReport tds) {

		if (tds.getCheckFlag().equals("Y")) {
			return getTdsReportConsolidatedArrears(rg, tds);
		} else {
			String reportName = "TDS";
			String reportType = tds.getReportType();
			// rg.setFName(reportName + "." + reportType);

			// rg.addText("\n", 0, 0, 0);
			String colName[] = { "Employee Id", "Employee Name", "Pan No.",
					"TDS", "Surcharge", "Education Cess", "Total Tax" };
			int[] cellWidth = { 15, 25, 15, 10, 15, 15, 15 };
			int[] alignMent = { 0, 0, 1, 2, 2, 2, 2 };
			try {
				String tdsDebitCodeQuery = " SELECT TDS_DEBIT_CODE FROM  HRMS_TAX_PARAMETER WHERE TO_CHAR(TDS_EFF_DATE,'dd-MON-yyyy') = (select max(TDS_EFF_DATE) FROM  HRMS_TAX_PARAMETER WHERE to_char(TDS_EFF_DATE,'yyyy-mm')<='"
						+ tds.getYear() + "-" + tds.getMonth() + "')";
				Object[][] tds_code = getSqlModel().getSingleResult(
						tdsDebitCodeQuery);

				if (tds_code == null || tds_code.length <= 0) {
					tds.setTdsArrFlag("true");

					// rg.addText("Records are not available.",0,1,0);
				}
				String arrQuery = " SELECT EMP_TOKEN,EMP_FNAME ||' '||EMP_LNAME,NVL(SAL_PANNO,' '),"
						+ "  HRMS_ARREARS_"
						+ tds.getYear()
						+ ".ARREARS_MONTH,HRMS_ARREARS_"
						+ tds.getYear()
						+ ".ARREARS_YEAR,HRMS_ARREARS_"
						+ tds.getYear()
						+ ".ARREARS_DAYS,HRMS_ARREARS_DEBIT_"
						+ tds.getYear()
						+ ".ARREARS_AMT"
						+ "  ,CASE WHEN ARREARS_TYPE='M' THEN 'Monthly'  WHEN ARREARS_TYPE='P' THEN 'Promotional' END FROM HRMS_ARREARS_"
						+ tds.getYear()
						+ "  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"
						+ tds.getYear()
						+ ".EMP_ID) "
						+ "  INNER JOIN HRMS_ARREARS_DEBIT_"
						+ tds.getYear()
						+ "  ON(HRMS_ARREARS_"
						+ tds.getYear()
						+ ".ARREARS_CODE=HRMS_ARREARS_DEBIT_"
						+ tds.getYear()
						+ ".ARREARS_CODE AND HRMS_ARREARS_"
						+ tds.getYear()
						+ ".ARREARS_MONTH=HRMS_ARREARS_DEBIT_"
						+ tds.getYear()
						+ ".ARREARS_MONTH AND HRMS_ARREARS_"
						+ tds.getYear()
						+ ".EMP_ID=HRMS_ARREARS_DEBIT_"
						+ tds.getYear()
						+ ".ARREARS_EMP_ID)"
						+ "  inner join hrms_arrears_ledger on(hrms_arrears_ledger.arrears_code=hrms_arrears_"
						+ tds.getYear()
						+ ".arrears_code)"
						+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_ARREARS_"
						+ tds.getYear()
						+ ".EMP_ID)"
						+ "  WHERE ARREARS_PAID_MONTH="
						+ tds.getMonth()
						+ " AND ARREARS_PAID_YEAR="
						+ tds.getYear()
						+ " AND ARREARS_DIVISION in("
						+ tds.getDivCode()
						// + emp_id[k][1]
						+ ") AND HRMS_ARREARS_DEBIT_"
						+ tds.getYear()
						+ ".ARREARS_DEBIT_CODE="
						+ tds_code[0][0]
						+ " AND ARREARS_AMT > 0";// ;AND
				// HRMS_ARREARS_DEBIT_"+tds.getYear()+".ARREARS_MONTH="+emp_id[k][2]+
				// " AND
				// HRMS_ARREARS_DEBIT_"+tds.getYear()+".ARREARS_DEBIT_CODE="+tds_code[0][0];
				// +" AND
				// HRMS_ARREARS_DEBIT_"+tds.getYear()+".PROM_CODE="+emp_id[k][4]+"
				// AND
				// HRMS_ARREARS_"+tds.getYear()+".ARREARS_PROMCODE="+emp_id[k][4];
				if (!tds.getOnHold().equals("A")) {
					arrQuery += " AND ARREARS_ONHOLD='" + tds.getOnHold() + "'";
				}
				if (!(tds.getCadreCode().equals("") || tds.getCadreCode() == null)) {
					arrQuery += " and HRMS_EMP_OFFC.EMP_CADRE in("
							+ tds.getCadreCode() + ")";
				}

				arrQuery += " ORDER BY ARREARS_TYPE,EMP_TOKEN, HRMS_ARREARS_"
						+ tds.getYear() + ".ARREARS_MONTH,HRMS_ARREARS_"
						+ tds.getYear() + ".ARREARS_YEAR";
				Object[][] arrearAmt = getSqlModel().getSingleResult(arrQuery);
				double totaltds = 0;
				double surcharge = 0;
				double educationCess = 0;
				double totalTax = 0;

				/**
				 * Following query is used to select the arrear details
				 */

				double tdsArrAmt = 0;
				Object[][] param1 = new Object[arrearAmt.length][11];

				Object[][] tdsParameter = getTdsDebitCode(tds.getYear(), tds
						.getMonth());

				// logger.info("cess=====>"+cess);
				if (!(arrearAmt == null || arrearAmt.length == 0)) {
					for (int i = 0; i < arrearAmt.length; i++) {

						Object[][] taxDetails = null;
						try {
							taxDetails = getTaxDtls(Double.parseDouble(String
									.valueOf(arrearAmt[i][6])), 0.0, 0.0,
									tdsParameter);

						} catch (Exception e) {
							logger.error("exception in challanTax", e);
						} // end of catch
						double tottds = Double.parseDouble(String
								.valueOf(taxDetails[0][0]));
						// logger.info("tottds=====>"+tottds);
						double sur = 0;
						// logger.info("sur=====>"+sur);
						double cess = Double.parseDouble(String
								.valueOf(taxDetails[0][1]));
						param1[i][0] = String.valueOf(arrearAmt[i][0]);// Emp
						// Id
						param1[i][1] = String.valueOf(arrearAmt[i][1]);// Emp
						// Name
						param1[i][2] = String.valueOf(arrearAmt[i][2]);// PAN

						param1[i][3] = tottds;// PAN

						param1[i][4] = formatter.format(sur);

						param1[i][5] = formatter.format(cess);

						param1[i][6] = formatter.format(Double
								.parseDouble(String.valueOf(arrearAmt[i][6])));
						param1[i][7] = Utility.month(Integer.parseInt(String
								.valueOf(arrearAmt[i][3])));// Month
						param1[i][8] = String.valueOf(arrearAmt[i][4]);// Year
						param1[i][9] = String.valueOf(arrearAmt[i][5]);// Arrears
						// Days
						param1[i][10] = String.valueOf(arrearAmt[i][7]);

						tdsArrAmt += Double.parseDouble(String
								.valueOf(arrearAmt[i][6]));
					}

					String col[] = { "Employee Id", "Employee Name", "Pan No.",
							"TDS", "Surcharge", "Education Cess", "Total Tax",
							"Month", "Year", "Arrears Days", "Arrear Type" };
					int cellWidth1[] = { 12, 20, 15, 15, 10, 10, 10, 8, 8, 10,
							10 };
					int[] align = { 0, 0, 0, 2, 2, 2, 2, 0, 1, 2, 0 };

					if (tds.getCheckFlag().equals("N")) {

						// rg.addText("\n", 0, 0, 0);
						if (param1 != null && param1.length > 0) {
							// rg.addText("\n\nArrears", 0, 0, 0);

							Object[][] title = new Object[1][1];
							title[0][0] = "Arrears";
							TableDataSet reportObjTitle = new TableDataSet();
							reportObjTitle.setCellAlignment(new int[] { 0 });
							reportObjTitle.setCellWidth(new int[] { 100 });
							reportObjTitle.setData(title);
							reportObjTitle.setBorderDetail(0);
							reportObjTitle.setBodyFontStyle(1);
							// reportObjTitle.setBlankRowsBelow(1);
							rg.addTableToDoc(reportObjTitle);

							// rg.tableBody(col, param1, cellWidth1, align);
							boolean[] bcellwrap = new boolean[] { true, true,
									true, true, true, true, true, true, true,
									true, true };
							TableDataSet reportObjData = new TableDataSet();
							reportObjData.setHeader(col);
							reportObjData.setCellAlignment(align);
							reportObjData.setCellWidth(cellWidth1);
							reportObjData.setData(param1);
							// reportObjData.setHeaderLines(true);
							reportObjData.setHeaderBorderColor(new BaseColor(
									255, 0, 0));
							reportObjData.setHeaderBorderDetail(3);
							reportObjData.setBorderDetail(3);
							// reportObjData.setHeaderBGColor(new BaseColor(225,
							// 225, 225));
							reportObjData.setHeaderTable(true);
							reportObjData.setBlankRowsBelow(1);
							reportObjData.setBlankRowsAbove(1);
							reportObjData.setCellNoWrap(bcellwrap);
							rg.addTableToDoc(reportObjData);

							for (int j = 0; j < param1.length; j++) {
								totaltds += Double.parseDouble(String
										.valueOf(param1[j][3]));
								educationCess += Double.parseDouble(String
										.valueOf(param1[j][5]));
								totalTax += Double.parseDouble(String
										.valueOf(param1[j][6]));
							}
							Object[][] paramTotal = new Object[1][7];
							paramTotal[0][0] = "";
							paramTotal[0][1] = "";
							paramTotal[0][2] = "TOTAL";
							paramTotal[0][3] = Utility.twoDecimals(totaltds);
							paramTotal[0][4] = Utility.twoDecimals(surcharge);
							paramTotal[0][5] = Utility
									.twoDecimals(educationCess);
							paramTotal[0][6] = Utility.twoDecimals(totalTax);
							// rg.tableBody(paramTotal, cellWidth, alignMent);

							reportObjData = new TableDataSet();
							reportObjData.setCellAlignment(align);
							reportObjData.setCellWidth(cellWidth1);
							reportObjData.setData(paramTotal);
							// reportObjData.setColumnSum(new int[]{4, 5, 6,
							// 7});
							reportObjData.setBorderDetail(0);
							reportObjData.setBlankRowsBelow(1);
							reportObjData.setBlankRowsAbove(1);
							reportObjData.setBodyFontStyle(1);
							reportObjData.setBorderLines(true);
							reportObjData.setBodyFontStyle(1);
							rg.addTableToDoc(reportObjData);

						}
					} else {

						param1 = consolidateArrearsObject(param1, null, 0,
								new int[] { 3, 4, 5, 6 }, 11);
						// rg.addText("\n", 0, 0, 0);
						if (param1 != null && param1.length > 0) {
							// rg.addText("\n\nArrears", 0, 0, 0);
							Object[][] title = new Object[1][1];
							title[0][0] = "Arrears";
							TableDataSet reportObjTitle = new TableDataSet();
							reportObjTitle.setCellAlignment(new int[] { 0 });
							reportObjTitle.setCellWidth(new int[] { 100 });
							reportObjTitle.setData(title);
							reportObjTitle.setBorderDetail(0);
							reportObjTitle.setBodyFontStyle(1);
							// reportObjTitle.setBlankRowsBelow(1);
							rg.addTableToDoc(reportObjTitle);

							boolean[] bcellwrap = new boolean[] { true, true,
									true, true, true, true, true };
							// rg.tableBody(colName, param1, cellWidth,
							// alignMent);
							TableDataSet reportObjData = new TableDataSet();
							reportObjData.setHeader(colName);
							reportObjData.setCellAlignment(alignMent);
							reportObjData.setCellWidth(cellWidth);
							reportObjData.setData(param1);
							// reportObjData.setHeaderLines(true);
							reportObjData.setHeaderBorderColor(new BaseColor(
									255, 0, 0));
							reportObjData.setBorderDetail(3);
							reportObjData.setHeaderBorderDetail(3);
							// reportObjData.setHeaderBGColor(new BaseColor(225,
							// 225, 225));
							reportObjData.setHeaderTable(true);
							reportObjData.setCellNoWrap(bcellwrap);
							reportObjData.setBlankRowsBelow(1);
							reportObjData.setBlankRowsAbove(1);
							rg.addTableToDoc(reportObjData);

							for (int j = 0; j < param1.length; j++) {
								totaltds += Double.parseDouble(String
										.valueOf(param1[j][3]));
								educationCess += Double.parseDouble(String
										.valueOf(param1[j][5]));
								totalTax += Double.parseDouble(String
										.valueOf(param1[j][6]));
							}
							Object[][] paramTotal = new Object[1][7];
							paramTotal[0][0] = "";
							paramTotal[0][1] = "";
							paramTotal[0][2] = "TOTAL";
							paramTotal[0][3] = Utility.twoDecimals(totaltds);
							paramTotal[0][4] = Utility.twoDecimals(surcharge);
							paramTotal[0][5] = Utility
									.twoDecimals(educationCess);
							paramTotal[0][6] = Utility.twoDecimals(totalTax);
							// rg.tableBody(paramTotal, cellWidth, alignMent);

							reportObjData = new TableDataSet();
							reportObjData.setCellAlignment(alignMent);
							reportObjData.setCellWidth(cellWidth);
							reportObjData.setData(paramTotal);
							// reportObjData.setColumnSum(new int[]{4, 5, 6,
							// 7});
							// reportObjData.setBorder(true);
							reportObjData.setBorderDetail(0);
							reportObjData.setBodyFontStyle(1);
							reportObjData.setBlankRowsBelow(1);
							reportObjData.setBlankRowsAbove(1);
							reportObjData.setBorderLines(true);
							reportObjData.setBodyFontStyle(1);
							rg.addTableToDoc(reportObjData);
						}

					}
				} else {
					Object[][] title = new Object[1][1];
					title[0][0] = "Arrears : No Arrears records avaliable for selected criteria";
					TableDataSet reportObjTitle = new TableDataSet();
					reportObjTitle.setCellAlignment(new int[] { 0 });
					reportObjTitle.setCellWidth(new int[] { 100 });
					reportObjTitle.setData(title);
					reportObjTitle.setBodyFontStyle(1);
					reportObjTitle.setBorderDetail(0);
					reportObjTitle.setCellColSpan(new int[] {3});
					// reportObjTitle.setBlankRowsBelow(1);
					rg.addTableToDoc(reportObjTitle);

					/*
					 * final TableDataSet headerName1 = new TableDataSet();
					 * headerName1.setData(new Object[][] { { " " }, { "No
					 * Arrears records avaliable for selected criteria" } });
					 * headerName1.setCellAlignment(new int[] { 1 });
					 * headerName1.setCellWidth(new int[] { 100 });
					 * headerName1.setBodyFontStyle(1);
					 * headerName1.setBorder(false);
					 * headerName1.setHeaderTable(false); //
					 * headerName6.setBlankRowsBelow(1);
					 * rg.addTableToDoc(headerName1);
					 */
				}
			} catch (Exception e) {
				e.printStackTrace();

				Object[][] title = new Object[1][1];
				title[0][0] = "Arrears : No Arrears records avaliable for selected criteria";
				TableDataSet reportObjTitle = new TableDataSet();
				reportObjTitle.setCellAlignment(new int[] { 0 });
				reportObjTitle.setCellWidth(new int[] { 100 });
				reportObjTitle.setData(title);
				reportObjTitle.setBodyFontStyle(1);
				reportObjTitle.setCellColSpan(new int[] {3});
				reportObjTitle.setBorderDetail(0);
				// reportObjTitle.setBlankRowsBelow(1);
				rg.addTableToDoc(reportObjTitle);

				/*
				 * final TableDataSet headerName1 = new TableDataSet();
				 * headerName1.setData(new Object[][] { { " " }, { "No records
				 * avaliable for selected criteria" } });
				 * headerName1.setCellAlignment(new int[] { 1 });
				 * headerName1.setCellWidth(new int[] { 100 });
				 * headerName1.setBodyFontStyle(1);
				 * headerName1.setBorder(false);
				 * headerName1.setHeaderTable(false); //
				 * headerName6.setBlankRowsBelow(1);
				 * rg.addTableToDoc(headerName1);
				 */
			}
			return rg;
		}

	}

	public ReportGenerator getTdsReportConsolidatedArrears(ReportGenerator rg,
			TDSReport tds) {

		String reportName = "TDS";
		String reportType = tds.getReportType();
		// rg.setFName(reportName + "." + reportType);
		double tdsArrAmt = 0;

		// rg.addText("\n", 0, 0, 0);

		try {
			String tdsDebitCodeQuery = " SELECT TDS_DEBIT_CODE FROM  HRMS_TAX_PARAMETER WHERE TO_CHAR(TDS_EFF_DATE,'dd-MON-yyyy') = (select max(TDS_EFF_DATE) FROM  HRMS_TAX_PARAMETER WHERE to_char(TDS_EFF_DATE,'yyyy-mm')<='"
					+ tds.getYear() + "-" + tds.getMonth() + "')";
			Object[][] tds_code = getSqlModel().getSingleResult(
					tdsDebitCodeQuery);

			if (tds_code == null || tds_code.length <= 0) {
				tds.setTdsArrFlag("true");

				// rg.addText("Records are not available.",0,1,0);
			}
			String arrQuery = " SELECT EMP_TOKEN,EMP_FNAME ||' '||EMP_LNAME,NVL(SAL_PANNO,' '),"
					+ "  HRMS_ARREARS_"
					+ tds.getYear()
					+ ".ARREARS_MONTH,HRMS_ARREARS_"
					+ tds.getYear()
					+ ".ARREARS_YEAR,HRMS_ARREARS_"
					+ tds.getYear()
					+ ".ARREARS_DAYS,HRMS_ARREARS_DEBIT_"
					+ tds.getYear()
					+ ".ARREARS_AMT"
					+ "  ,CASE WHEN ARREARS_TYPE='M' THEN 'Monthly'  WHEN ARREARS_TYPE='P' THEN 'Promotional' END FROM HRMS_ARREARS_"
					+ tds.getYear()
					+ "  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"
					+ tds.getYear()
					+ ".EMP_ID) "
					+ "  INNER JOIN HRMS_ARREARS_DEBIT_"
					+ tds.getYear()
					+ "  ON(HRMS_ARREARS_"
					+ tds.getYear()
					+ ".ARREARS_CODE=HRMS_ARREARS_DEBIT_"
					+ tds.getYear()
					+ ".ARREARS_CODE AND HRMS_ARREARS_"
					+ tds.getYear()
					+ ".ARREARS_MONTH=HRMS_ARREARS_DEBIT_"
					+ tds.getYear()
					+ ".ARREARS_MONTH AND HRMS_ARREARS_"
					+ tds.getYear()
					+ ".EMP_ID=HRMS_ARREARS_DEBIT_"
					+ tds.getYear()
					+ ".ARREARS_EMP_ID)"
					+ "  inner join hrms_arrears_ledger on(hrms_arrears_ledger.arrears_code=hrms_arrears_"
					+ tds.getYear()
					+ ".arrears_code)"
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_ARREARS_"
					+ tds.getYear()
					+ ".EMP_ID)"
					+ "  WHERE ARREARS_PAID_MONTH="
					+ tds.getMonth()
					+ " AND ARREARS_PAID_YEAR="
					+ tds.getYear()
					+ " AND ARREARS_DIVISION in("
					+ tds.getDivCode()
					// + emp_id[k][1]
					+ ") AND HRMS_ARREARS_DEBIT_"
					+ tds.getYear()
					+ ".ARREARS_DEBIT_CODE="
					+ tds_code[0][0]
					+ " AND ARREARS_AMT > 0";// ;AND
			// HRMS_ARREARS_DEBIT_"+tds.getYear()+".ARREARS_MONTH="+emp_id[k][2]+
			// " AND
			// HRMS_ARREARS_DEBIT_"+tds.getYear()+".ARREARS_DEBIT_CODE="+tds_code[0][0];
			// +" AND
			// HRMS_ARREARS_DEBIT_"+tds.getYear()+".PROM_CODE="+emp_id[k][4]+"
			// AND
			// HRMS_ARREARS_"+tds.getYear()+".ARREARS_PROMCODE="+emp_id[k][4];

			if (!(tds.getOnHold().equals("A"))) {
				arrQuery += " and ARREARS_ONHOLD='" + tds.getOnHold() + "'";
			}
			if (!(tds.getCadreCode().equals("") || tds.getCadreCode() == null)) {
				arrQuery += " and HRMS_EMP_OFFC.EMP_CADRE in("
						+ tds.getCadreCode() + ")";
			}

			arrQuery += " ORDER BY  HRMS_ARREARS_" + tds.getYear()
					+ ".ARREARS_MONTH,HRMS_ARREARS_" + tds.getYear()
					+ ".ARREARS_YEAR";
			Object[][] arrearAmt = getSqlModel().getSingleResult(arrQuery);
			double totaltds = 0;
			double surcharge = 0;
			double educationCess = 0;
			Object[][] param1 = new Object[arrearAmt.length][11];
			Object[][] tdsParameter = null;
			try {
				tdsParameter = getTdsDebitCode(tds.getYear(), tds.getMonth());
			} catch (Exception e) {
				logger.error("exception in tdsParameter query", e);
			} // end of catch
			// logger.info("cess=====>"+cess);

			if (!(arrearAmt == null || arrearAmt.length == 0)) {
				for (int i = 0; i < arrearAmt.length; i++) {

					Object[][] taxDetails = null;
					try {
						taxDetails = getTaxDtls(Double.parseDouble(String
								.valueOf(arrearAmt[i][6])), 0.0, 0.0,
								tdsParameter);

					} catch (Exception e) {
						logger.error("exception in challanTax", e);
					} // end of catch
					double tottds = Double.parseDouble(String
							.valueOf(taxDetails[0][0]));
					// logger.info("tottds=====>"+tottds);
					double sur = 0;
					// logger.info("sur=====>"+sur);
					double cess = Double.parseDouble(String
							.valueOf(taxDetails[0][1]));
					param1[i][0] = String.valueOf(arrearAmt[i][0]);// Emp
					// Id
					param1[i][1] = String.valueOf(arrearAmt[i][1]);// Emp
					// Name
					param1[i][2] = String.valueOf(arrearAmt[i][2]);// PAN

					param1[i][3] = tottds;// PAN

					param1[i][4] = formatter.format(sur);

					param1[i][5] = formatter.format(cess);

					param1[i][6] = formatter.format(Double.parseDouble(String
							.valueOf(arrearAmt[i][6])));
					param1[i][7] = Utility.month(Integer.parseInt(String
							.valueOf(arrearAmt[i][3])));// Month
					param1[i][8] = String.valueOf(arrearAmt[i][4]);// Year
					param1[i][9] = String.valueOf(arrearAmt[i][5]);// Arrears
					// Days
					param1[i][10] = String.valueOf(arrearAmt[i][7]);

					tdsArrAmt += Double.parseDouble(String
							.valueOf(arrearAmt[i][6]));
					totaltds += Double.parseDouble(Utility.twoDecimals(Math
							.round(tottds)));
					surcharge += sur;
					educationCess += cess;
				}

				param1 = consolidateArrearsObject(param1, null, 0, new int[] {
						3, 4, 5, 6 }, 7);
				Object[][] param = new Object[1][7];
				param[0][0] = "";
				param[0][1] = "";
				param[0][2] = "TOTAL";
				param[0][3] = Utility.twoDecimals(totaltds);
				param[0][4] = Utility.twoDecimals(surcharge);
				param[0][5] = Utility.twoDecimals(educationCess);
				param[0][6] = Utility.twoDecimals(tdsArrAmt);
				String col[] = { "Employee Id", "Employee Name", "Pan No.",
						"TDS", "Surcharge", "Education Cess", "Total Tax" };

				int cellWidth1[] = { 12, 20, 15, 15, 10, 10, 10 };
				int[] align = { 0, 0, 0, 2, 2, 2, 2 };
				if (param1 != null && param1.length != 0) {
					/*
					 * rg.addText("Arrear Details :", 0, 0, 0); rg.addText("\n",
					 * 0, 0, 0);
					 * 
					 * rg.tableBody(col, param1, cellWidth1, align);
					 * rg.tableBody(param, cellWidth1, align);
					 */

					Object[][] title = new Object[1][1];
					title[0][0] = "Arrear Details";
					TableDataSet reportObjTitle = new TableDataSet();
					reportObjTitle.setCellAlignment(new int[] { 0 });
					reportObjTitle.setCellWidth(new int[] { 100 });
					reportObjTitle.setData(title);
					reportObjTitle.setBodyFontStyle(1);
					reportObjTitle.setBorderDetail(0);
					// reportObjTitle.setBlankRowsBelow(1);
					rg.addTableToDoc(reportObjTitle);

					boolean[] bcellwrap = new boolean[] { true, true, true,
							true, true, true, true };
					TableDataSet reportObjData = new TableDataSet();
					reportObjData.setHeader(col);
					reportObjData.setCellAlignment(align);
					reportObjData.setCellWidth(cellWidth1);
					reportObjData.setData(param1);
					// reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
					// reportObjData.setBorder(true);
					// reportObjData.setHeaderBorderDetail(2);
					// reportObjData.setHeaderLines(true);
					reportObjData
							.setHeaderBorderColor(new BaseColor(255, 0, 0));
					reportObjData.setBorderDetail(3);
					reportObjData.setHeaderBorderDetail(3);
					// reportObjData.setHeaderBGColor(new BaseColor(225, 225,
					// 225));
					reportObjData.setHeaderTable(true);
					reportObjData.setCellNoWrap(bcellwrap);
					reportObjData.setBlankRowsBelow(1);
					reportObjData.setBlankRowsAbove(1);
					rg.addTableToDoc(reportObjData);
				}
			} // End of if condition for amt
			else {
				Object[][] title = new Object[1][1];
				title[0][0] = "Arrear Details : No arrear records available for selected criteria";
				TableDataSet reportObjTitle = new TableDataSet();
				reportObjTitle.setCellAlignment(new int[] { 0 });
				reportObjTitle.setCellWidth(new int[] { 100 });
				reportObjTitle.setData(title);
				reportObjTitle.setBodyFontStyle(1);
				reportObjTitle.setBorderDetail(0);
				reportObjTitle.setCellColSpan(new int[] {3});
				// reportObjTitle.setBlankRowsBelow(1);
				rg.addTableToDoc(reportObjTitle);

				/*
				 * final TableDataSet headerName1 = new TableDataSet();
				 * headerName1.setData(new Object[][] { { " " }, { "No arrear
				 * records available for selected criteria" } });
				 * headerName1.setCellAlignment(new int[] { 1 });
				 * headerName1.setCellWidth(new int[] { 100 });
				 * headerName1.setBodyFontStyle(1);
				 * headerName1.setBorder(false);
				 * headerName1.setHeaderTable(false); //
				 * headerName6.setBlankRowsBelow(1);
				 * rg.addTableToDoc(headerName1);
				 */
			}

		} catch (Exception e) {
			e.printStackTrace();

			Object[][] title = new Object[1][1];
			title[0][0] = "Arrear Details : No arrear records available for selected criteria";
			TableDataSet reportObjTitle = new TableDataSet();
			reportObjTitle.setCellAlignment(new int[] { 0 });
			reportObjTitle.setCellWidth(new int[] { 100 });
			reportObjTitle.setData(title);
			reportObjTitle.setBodyFontStyle(1);
			reportObjTitle.setBorderDetail(0);
			reportObjTitle.setCellColSpan(new int[] {3});
			// reportObjTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(reportObjTitle);

			/*
			 * final TableDataSet headerName1 = new TableDataSet();
			 * headerName1.setData(new Object[][] { { " " }, { "No records
			 * avaliable for selected criteria" } });
			 * headerName1.setCellAlignment(new int[] { 1 });
			 * headerName1.setCellWidth(new int[] { 100 });
			 * headerName1.setBodyFontStyle(1); headerName1.setBorder(false);
			 * headerName1.setHeaderTable(false); //
			 * headerName6.setBlankRowsBelow(1); rg.addTableToDoc(headerName1);
			 */

		}
		return rg;

	}

	/**
	 * * following function is called when when report option type is Only
	 * Salary or All and Tds button is clicked.
	 * 
	 * @param rg
	 * @param tds
	 * @return
	 */

	public ReportGenerator getTdsReportSalary(ReportGenerator rg, TDSReport tds) {

		double tdsTotal = 0.00;
		String ledgerCode = "";
		String reportName = "TDS";
		String reportType = tds.getReportType();

		/*
		 * rg.setFName(reportName + "." + reportType);
		 * 
		 * rg.addText("\n", 0, 0, 0);
		 */
		String tdsDebitCodeQuery = " SELECT TDS_DEBIT_CODE FROM  HRMS_TAX_PARAMETER WHERE TO_CHAR(TDS_EFF_DATE,'dd-MON-yyyy') = (select max(TDS_EFF_DATE) FROM  HRMS_TAX_PARAMETER WHERE to_char(TDS_EFF_DATE,'yyyy-mm')<='"
				+ tds.getYear() + "-" + tds.getMonth() + "')";

		Object[][] tds_code = getSqlModel().getSingleResult(tdsDebitCodeQuery);

		if (tds_code == null || tds_code.length <= 0) {

			// rg.addText("Records are not available.",0,1,0);

		}
		/**
		 * following query is called to check whether ledger code is available
		 * or not for that selected month and year
		 */
		String ledgerQuery = "SELECT HRMS_SALARY_LEDGER.LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE HRMS_SALARY_LEDGER.LEDGER_MONTH="
				+ tds.getMonth() + " AND HRMS_SALARY_LEDGER.LEDGER_YEAR=" + tds.getYear();
		System.out.println("query in tdsrep" + ledgerQuery);
		Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
		if (ledgerData != null && ledgerData.length > 0) {

			for (int i = 0; i < ledgerData.length; i++) {

				if (i == ledgerData.length - 1) {
					ledgerCode += ledgerData[i][0];
				} else {
					ledgerCode += ledgerData[i][0] + ",";
				}
			}
		} else {
			tds.setTdsSalFlag("true");
		}
		/**
		 * following query is called to select the employee name,pan no., tds
		 * amount and employee id
		 */
		logger.info("Length of ledger code in tdsreportmodel"
				+ ledgerData.length);
		if (ledgerData != null && ledgerData.length > 0) {
			String tdsQuery = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,NVL(SAL_PANNO,' '),nvl(SAL_AMOUNT,''),HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ ".EMP_ID "
					+ " FROM HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ " "
					+ " INNER JOIN HRMS_EMP_OFFC ON  (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ ".EMP_ID )"
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ ".EMP_ID)"
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ ".SAL_LEDGER_CODE  AND LEDGER_MONTH="
					+ tds.getMonth()
					+ " AND LEDGER_YEAR="
					+ tds.getYear()
					+ ")"
					+ " INNER JOIN HRMS_SALARY_"
					+ tds.getYear()
					+ "  ON (HRMS_SALARY_"
					+ tds.getYear()
					+ ".EMP_ID = HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ ".EMP_ID) "
					+ " WHERE SAL_DIVISION in("
					+ tds.getDivCode()
					+ ") AND SAL_AMOUNT >0 AND SAL_DEBIT_CODE="
					+ String.valueOf(tds_code[0][0])
					+ " AND SAL_LEDGER_CODE IN(" + ledgerCode + ") ";

			if (!(tds.getOnHold().equals("A"))) {
				tdsQuery += " and SAL_ONHOLD='" + tds.getOnHold() + "'";
			}
			if (!(tds.getBrnCode().equals("") || tds.getBrnCode() == null)) {

				tdsQuery += " and sal_emp_center in(" + tds.getBrnCode() + ")";
			}
			if (!(tds.getDeptCode().equals("") || tds.getDeptCode() == null)) {
				tdsQuery += " and sal_dept in(" + tds.getDeptCode() + ")";

			}
			if (!(tds.getTypeCode().equals("") || tds.getTypeCode() == null)) {
				tdsQuery += " and sal_emp_type in(" + tds.getTypeCode() + ")";

			}
			if (!(tds.getPayBillNo().equals("") || tds.getPayBillNo() == null)) {
				tdsQuery += " and SAL_EMP_PAYBILL in(" + tds.getPayBillNo()
						+ ")";
			}
			if (!(tds.getCadreCode().equals("") || tds.getCadreCode() == null)) {
				tdsQuery += " and HRMS_EMP_OFFC.EMP_CADRE in("
						+ tds.getCadreCode() + ")";
			}

			Object[][] tds_amt = getSqlModel().getSingleResult(tdsQuery);
			Object[][] tdsParameter = null;
			try {
				tdsParameter = getTdsDebitCode(tds.getYear(), tds.getMonth());
			} catch (Exception e) {
				logger.error("exception in tdsParameter query", e);
			} // end of catch

			Object[][] param = new Object[tds_amt.length][7];

			int toYear = Integer.parseInt(tds.getYear()) + 1;
			Object empTaxIncome[][] = null;
			double surcharge = 0.00, totaltds = 0.00;
			double educationCess = 0.00;
			double totalTax = 0.00;
			try {
				String empIncomeQuery = "SELECT NVL(HRMS_TDS.TDS_TAXABLE_INCOME,0.00),HRMS_TDS.TDS_EMP_ID FROM HRMS_TDS "
						+ " WHERE  HRMS_TDS.TDS_FROM_YEAR="
						+ tds.getYear()
						+ " AND HRMS_TDS.TDS_TO_YEAR=" + toYear + " ORDER BY HRMS_TDS.TDS_EMP_ID";
				empTaxIncome = getSqlModel().getSingleResult(empIncomeQuery);
			} catch (Exception e) {
				logger.error("exception in empTaxIncome query", e);
			} // end of catch
			if (tds_amt != null && tds_amt.length != 0) {

				for (int i = 0; i < tds_amt.length; i++) {
					double amount = 0.00;
					Object[][] challanTax = null;
					double chkAmount = 0.00;
					Object amt[][] = null;

					if (empTaxIncome != null && empTaxIncome.length != 0) {
						for (int j = 0; j < empTaxIncome.length; j++) {
							if (String.valueOf(empTaxIncome[j][1]).equals(
									String.valueOf(tds_amt[i][4]))) {
								amount = Double.parseDouble(String
										.valueOf(empTaxIncome[j][0]));
							} // end of if
						} // end of loop
					} // end of if
					else {
						amount = 0.00;
					} // end of else

					try {
						challanTax = getTaxDtls(Double.parseDouble(String
								.valueOf(tds_amt[i][3])),
								Double.parseDouble(String
										.valueOf(tdsParameter[0][2])), amount,
								tdsParameter);
					} catch (Exception e) {
						logger.error("exception in challanTax", e);
					} // end of catch

					double tottds = Double.parseDouble(String
							.valueOf(challanTax[0][0]));

					double sur = Double.parseDouble(String
							.valueOf(challanTax[0][2]));

					double cess = Double.parseDouble(String
							.valueOf(challanTax[0][1]));

					chkAmount = Math.round(tottds) + Math.round(sur)
							+ Math.round(cess);

					chkAmount = Double.parseDouble(String
							.valueOf(tds_amt[i][3]))
							- chkAmount;

					totaltds += Double.parseDouble(Utility.twoDecimals(Math
							.round(tottds)
							+ chkAmount));
					surcharge += sur;
					educationCess += cess;
					totalTax += Double.parseDouble(Utility.twoDecimals(Math
							.round(Double.parseDouble(String
									.valueOf(tds_amt[i][3])))));

					param[i][0] = tds_amt[i][0];// Employee Id
					param[i][1] = tds_amt[i][1];// Employee Name
					param[i][2] = tds_amt[i][2];// Pan No.
					param[i][3] = formatter.format(Math.round(tottds)
							+ chkAmount);// TDS
					param[i][4] = formatter.format(Double.parseDouble(String
							.valueOf(challanTax[0][2])));// Surcharge
					param[i][5] = formatter.format(Double.parseDouble(String
							.valueOf(challanTax[0][1])));// Edu-Cess
					param[i][6] = formatter.format(Double.parseDouble((String
							.valueOf(tds_amt[i][3]))));// TDS Amount
				}// End of for loop

				String colName[] = { "Employee Id", "Employee Name", "Pan No.",
						"TDS", "Surcharge", "Educational Cess", "TDS Amount" };
				int[] cellWidth = { 15, 25, 15, 10, 15, 15, 15 };
				int[] alignMent = { 0, 0, 0, 2, 2, 2, 2 };
				/*
				 * rg.addText("Salary Details :", 0, 0, 0); rg.addText("\n", 0,
				 * 0, 0); rg.tableBody(colName, param, cellWidth, alignMent);
				 * rg.addText("\n", 0, 0, 0);
				 */

				Object[][] title = new Object[1][1];
				title[0][0] = "Salary Details";
				TableDataSet reportObjTitle = new TableDataSet();
				reportObjTitle.setCellAlignment(new int[] { 0 });
				reportObjTitle.setCellWidth(new int[] { 100 });
				reportObjTitle.setData(title);
				reportObjTitle.setBodyFontStyle(1);
				reportObjTitle.setBorderDetail(0);
				// reportObjTitle.setBlankRowsBelow(1);
				rg.addTableToDoc(reportObjTitle);

				boolean[] bcellwrap = new boolean[] { true, true, true, true,
						true, true, false, true };
				TableDataSet reportObjData = new TableDataSet();
				reportObjData.setHeader(colName);
				reportObjData.setCellAlignment(alignMent);
				reportObjData.setCellWidth(cellWidth);
				reportObjData.setData(param);
				// reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				// reportObjData.setBorder(true);
				// reportObjData.setHeaderBorderDetail(2);
				// reportObjData.setHeaderLines(true);
				reportObjData.setHeaderBorderColor(new BaseColor(255, 0, 0));
				reportObjData.setBorderDetail(3);
				reportObjData.setHeaderBorderDetail(3);
				// reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				// reportObjData.setCellNoWrap(bcellwrap);
				rg.addTableToDoc(reportObjData);

				Object paramTotal[][] = new Object[1][7];
				paramTotal[0][0] = "";
				paramTotal[0][1] = "";
				paramTotal[0][2] = "TOTAL";
				paramTotal[0][3] = formatter.format(totaltds);
				paramTotal[0][4] = formatter.format(surcharge);
				paramTotal[0][5] = formatter.format(educationCess);
				paramTotal[0][6] = formatter.format(totalTax);

				TableDataSet reportTotalData = new TableDataSet();
				reportTotalData.setCellAlignment(alignMent);
				reportTotalData.setCellWidth(cellWidth);
				reportTotalData.setData(paramTotal);
				// reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				// reportObjData.setBorder(true);
				reportTotalData.setBorderDetail(0);
				reportTotalData.setBodyFontStyle(1);
				reportTotalData.setBorderLines(true);
				reportTotalData.setBlankRowsBelow(1);
				rg.addTableToDoc(reportTotalData);

				reportObjData = new TableDataSet();
				Object[][] paramTotalRec = new Object[2][2];
				paramTotalRec[0][0] = "Total Records: " + param.length;
				paramTotalRec[1][1] = "Grand Total : "
						+ formatter.format(totalTax);
				reportObjData.setCellAlignment(new int[] { 0, 2 });
				reportObjData.setCellWidth(new int[] { 50, 50 });
				reportObjData.setData(paramTotalRec);
				reportObjData
						.setCellColSpan(new int[] { 1, param[0].length - 1 });
				// reportObjData.setBorder(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBodyBGColor(new BaseColor(200, 200, 200));
				rg.addTableToDoc(reportObjData);

			} // End of tds_amt if condition
		} // End of ledger code
		else {
			tds.setTdsSalFlag("true");
		}

		return rg;

	}

	/**
	 * Following function is called to calculate the tds debit code,tds edu
	 * cess,tds suchatge percentage and the maximum surcharge amount.
	 * 
	 * @return
	 */
	public Object[][] getTdsDebitCode(String year, String month) {
		Object tdsParameter[][] = null;
		int frmYear = Integer.parseInt(year);

		if (Integer.parseInt(month) < 4)
			frmYear = frmYear - 1;

		int toYear = frmYear + 1;
		try {
			String tdsParaQuery = "SELECT HRMS_TAX_PARAMETER.TDS_DEBIT_CODE,HRMS_TAX_PARAMETER.TDS_EDU_CESS,HRMS_TAX_PARAMETER.TDS_REBATEMAX_AMOUNT,HRMS_TAX_PARAMETER.TDS_SURCHARGE "
					+ " FROM HRMS_TAX_PARAMETER "
					+ " WHERE HRMS_TAX_PARAMETER.TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND HRMS_TAX_PARAMETER.TDS_FINANCIALYEAR_TO = " + toYear;
			tdsParameter = getSqlModel().getSingleResult(tdsParaQuery);
		} catch (Exception e) {
			logger.error("exception in tdsParameter query", e);
		} // end of catch
		return tdsParameter;
	} // end of method

	/**
	 * following function is used to calculate surcharge amt,tds amt and
	 * education cess.
	 * 
	 * @param tax
	 * @param surchargeTax
	 * @param tdsParameter
	 * @return
	 */
	public Object[][] getTaxDtls(Double totalTax, Double surchargeTax,
			Double taxableAmt, Object[][] tdsParameter) {
		double surCharge = 0;
		double edu_cess = 0;
		double tds = 0;
		Object[][] param = new Object[1][3];
		try {
			if (tdsParameter == null) {
				param[0][0] = 0;
				param[0][1] = 0;
				param[0][2] = 0;
			} // end of if
			else if (tdsParameter.length == 0) {
				param[0][0] = 0;
				param[0][1] = 0;
				param[0][2] = 0;
			} // end of else if
			else {
				tds = totalTax
						/ (1 + (Double.parseDouble(String
								.valueOf(tdsParameter[0][1])) / 100));
				surCharge = 0;
				edu_cess = totalTax - tds;

			} // end of else

			param[0][0] = Math.round(tds);
			param[0][1] = Math.round(edu_cess);
			param[0][2] = Math.round(surCharge);
		} catch (Exception e) {
			logger.error("exception in getTaxDtls method", e);
		} // end of catch
		return param;
	} // end of method

	/**
	 * following function is called when Tds button is clicked and report option
	 * type is All If consolidated arrears check box is chcekd system will add
	 * the tds amount with the arrears tds amount for the corresponding
	 * employee. If consolidated arrears is not checked then system will
	 * generate the arrear tds amount and salary tds amount separately.
	 * 
	 * @param rg
	 * @param tds
	 * @return
	 */
	public ReportGenerator getTdsSalaryWithArrears(ReportGenerator rg,
			TDSReport tds) {

		String ledgerCode = "";
		String reportName = "TDS";
		double tdsTotal = 0.0;
		Object[][] param = null;
		String colName[] = { "Employee Id", "Employee Name", "Pan No.", "TDS",
				"Surcharge", "Education Cess", "Total Tax" };
		int[] cellWidth = { 15, 25, 15, 10, 15, 15, 15 };
		int[] alignMent = { 0, 0, 0, 2, 2, 2, 2 };
		String reportType = tds.getReportType();
		// rg.setFName(reportName + "." + reportType);
		// rg.addText("\n", 0, 0, 0);
		Object[][] tdsParameter = null;
		String tdsDebitCodeQuery = " SELECT HRMS_TAX_PARAMETER.TDS_DEBIT_CODE FROM  HRMS_TAX_PARAMETER ";
		/*
		 * String tdsDebitCodeQuery=" SELECT TDS_DEBIT_CODE FROM
		 * HRMS_TAX_PARAMETER WHERE TO_CHAR(TDS_EFF_DATE,'dd-MON-yyyy') =
		 * (select max(TDS_EFF_DATE) FROM HRMS_TAX_PARAMETER WHERE
		 * to_char(TDS_EFF_DATE,'yyyy')<="+tds.getYear()+")" ;
		 */
		Object[][] tds_code = getSqlModel().getSingleResult(tdsDebitCodeQuery);
		if (tds_code == null || tds_code.length <= 0) {
			// rg.addText("Records are not available.",0,1,0);

		}

		String ledgerQuery = "SELECT HRMS_SALARY_LEDGER.LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
				+ tds.getMonth() + " AND HRMS_SALARY_LEDGER.LEDGER_YEAR=" + tds.getYear();

		Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
		if (ledgerData != null && ledgerData.length > 0) {
			for (int i = 0; i < ledgerData.length; i++) {
				if (i == ledgerData.length - 1) {
					ledgerCode += ledgerData[i][0];
				} else {
					ledgerCode += ledgerData[i][0] + ",";
				}
			}
		}
		String arrQuery = " SELECT EMP_TOKEN,EMP_FNAME ||' '||EMP_LNAME,NVL(SAL_PANNO,' '),"
				+ "  HRMS_ARREARS_"
				+ tds.getYear()
				+ ".ARREARS_MONTH,HRMS_ARREARS_"
				+ tds.getYear()
				+ ".ARREARS_YEAR,HRMS_ARREARS_"
				+ tds.getYear()
				+ ".ARREARS_DAYS,HRMS_ARREARS_DEBIT_"
				+ tds.getYear()
				+ ".ARREARS_AMT"
				+ "  ,CASE WHEN ARREARS_TYPE='M' THEN 'Monthly'  WHEN ARREARS_TYPE='P' THEN 'Promotional' END FROM HRMS_ARREARS_"
				+ tds.getYear()
				+ "  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"
				+ tds.getYear()
				+ ".EMP_ID) "
				+ "  INNER JOIN HRMS_ARREARS_DEBIT_"
				+ tds.getYear()
				+ "  ON(HRMS_ARREARS_"
				+ tds.getYear()
				+ ".ARREARS_CODE=HRMS_ARREARS_DEBIT_"
				+ tds.getYear()
				+ ".ARREARS_CODE AND HRMS_ARREARS_"
				+ tds.getYear()
				+ ".ARREARS_MONTH=HRMS_ARREARS_DEBIT_"
				+ tds.getYear()
				+ ".ARREARS_MONTH AND HRMS_ARREARS_"
				+ tds.getYear()
				+ ".EMP_ID=HRMS_ARREARS_DEBIT_"
				+ tds.getYear()
				+ ".ARREARS_EMP_ID)"
				+ "  inner join hrms_arrears_ledger on(hrms_arrears_ledger.arrears_code=hrms_arrears_"
				+ tds.getYear()
				+ ".arrears_code)"
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_ARREARS_"
				+ tds.getYear()
				+ ".EMP_ID)"
				+ "  WHERE ARREARS_PAID_MONTH="
				+ tds.getMonth()
				+ " AND ARREARS_PAID_YEAR="
				+ tds.getYear()
				+ " AND ARREARS_DIVISION in("
				+ tds.getDivCode()
				// + emp_id[k][1]
				+ ") AND HRMS_ARREARS_DEBIT_"
				+ tds.getYear()
				+ ".ARREARS_DEBIT_CODE="
				+ tds_code[0][0]
				+ " AND ARREARS_AMT > 0";// ;AND
		// HRMS_ARREARS_DEBIT_"+tds.getYear()+".ARREARS_MONTH="+emp_id[k][2]+
		// " AND
		// HRMS_ARREARS_DEBIT_"+tds.getYear()+".ARREARS_DEBIT_CODE="+tds_code[0][0];
		// +" AND
		// HRMS_ARREARS_DEBIT_"+tds.getYear()+".PROM_CODE="+emp_id[k][4]+"
		// AND
		// HRMS_ARREARS_"+tds.getYear()+".ARREARS_PROMCODE="+emp_id[k][4];
		if (!(tds.getOnHold().equals("A"))) {
			arrQuery += " and ARREARS_ONHOLD='" + tds.getOnHold() + "'";
		}
		if (!(tds.getCadreCode().equals("") || tds.getCadreCode() == null)) {
			arrQuery += " and HRMS_EMP_OFFC.EMP_CADRE in(" + tds.getCadreCode()
					+ ")";
		}
		arrQuery += " ORDER BY ARREARS_TYPE,EMP_TOKEN, HRMS_ARREARS_"
				+ tds.getYear() + ".ARREARS_MONTH,HRMS_ARREARS_"
				+ tds.getYear() + ".ARREARS_YEAR";
		Object[][] arrearAmt = getSqlModel().getSingleResult(arrQuery);

		if (!(ledgerCode.equals("") || ledgerCode.equals("null"))) {

			// Following query is used to select the employee name,id and the
			// tds amount.

			String tdsQuery = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,NVL(SAL_PANNO,' '),nvl(SAL_AMOUNT,''),HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ ".EMP_ID "
					+ " FROM HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ " "
					+ " INNER JOIN HRMS_EMP_OFFC ON  (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ ".EMP_ID )"
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ ".EMP_ID)"
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
					+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ ".SAL_LEDGER_CODE  AND LEDGER_MONTH="
					+ tds.getMonth()
					+ " AND LEDGER_YEAR="
					+ tds.getYear()
					+ ")"
					+ " INNER JOIN HRMS_SALARY_"
					+ tds.getYear()
					+ "  ON (HRMS_SALARY_"
					+ tds.getYear()
					+ ".EMP_ID = HRMS_SAL_DEBITS_"
					+ tds.getYear()
					+ ".EMP_ID) "
					+ " WHERE SAL_DIVISION in("
					+ tds.getDivCode()
					+ ") AND SAL_AMOUNT >0 AND SAL_DEBIT_CODE="
					+ String.valueOf(tds_code[0][0])
					+ " AND SAL_LEDGER_CODE IN(" + ledgerCode + ") ";

			if (!(tds.getOnHold().equals("A"))) {
				tdsQuery += " and SAL_ONHOLD='" + tds.getOnHold() + "'";
			}
			if (!(tds.getBrnCode().equals("") || tds.getBrnCode() == null)) {

				tdsQuery += " and sal_emp_center in(" + tds.getBrnCode() + ")";
			}
			if (!(tds.getDeptCode().equals("") || tds.getDeptCode() == null)) {
				tdsQuery += " and sal_dept in(" + tds.getDeptCode() + ")";

			}
			if (!(tds.getTypeCode().equals("") || tds.getTypeCode() == null)) {
				tdsQuery += " and sal_emp_type in(" + tds.getTypeCode() + ")";

			}
			if (!(tds.getPayBillNo().equals("") || tds.getPayBillNo() == null)) {
				tdsQuery += " and SAL_EMP_PAYBILL in(" + tds.getPayBillNo()
						+ ")";
			}
			if (!(tds.getCadreCode().equals("") || tds.getCadreCode() == null)) {
				tdsQuery += " and HRMS_EMP_OFFC.EMP_CADRE in("
						+ tds.getCadreCode() + ")";
			}

			Object[][] tds_amt = getSqlModel().getSingleResult(tdsQuery);

			try {
				tdsParameter = getTdsDebitCode(tds.getYear(), tds.getMonth());
			} catch (Exception e) {
				logger.error("exception in tdsParameter query", e);
			} // end of catch

			if (tds_amt != null && tds_amt.length != 0 && tdsParameter != null
					&& tdsParameter.length != 0) {

				param = new Object[tds_amt.length][7];
				int toYear = Integer.parseInt(tds.getYear()) + 1;
				Object empTaxIncome[][] = null;
				double surcharge = 0.00, totaltds = 0.00;
				double educationCess = 0.00;
				double totalTax = 0.00, chkAmount = 0.00;

				/*
				 * try { String empIncomeQuery = "SELECT
				 * NVL(TDS_TAXABLE_INCOME,0.00),TDS_EMP_ID FROM HRMS_TDS " + "
				 * WHERE TDS_FROM_YEAR=" + tds.getYear() + " AND TDS_TO_YEAR=" +
				 * toYear + " ORDER BY TDS_EMP_ID"; empTaxIncome =
				 * getSqlModel().getSingleResult(empIncomeQuery); } catch
				 * (Exception e) { logger.error("exception in empTaxIncome
				 * query",e); } //end of catch
				 */

				for (int i = 0; i < tds_amt.length; i++) {

					double amount = 0.00, totalTax1 = 0.00;
					Object[][] challanTax = null;
					/*
					 * Object amt[][] = null; try { String sqlQuery = "SELECT
					 * NVL(TDS_TAXABLE_INCOME,0.00) FROM HRMS_TDS WHERE
					 * TDS_EMP_ID=" + tds_amt[i][4] + " AND TDS_FROM_YEAR=" +
					 * tds.getYear() + " AND TDS_TO_YEAR=" + toYear; amt =
					 * getSqlModel().getSingleResult(sqlQuery); } catch
					 * (Exception e) { logger.error("exception in amt query",e); }
					 */
					/*
					 * if(empTaxIncome!=null && empTaxIncome.length!=0){ for
					 * (int j = 0; j < empTaxIncome.length; j++) {
					 * if(String.valueOf(empTaxIncome[j][1]).equals(String.valueOf(tds_amt[i][4]))){
					 * amount=Double.parseDouble(String.valueOf(empTaxIncome[j][0])); }
					 * //end of if } //end of loop } //end of if else{
					 * amount=0.00; } //end of else
					 */logger.info("tds.getCheckFlag()====="
							+ tds.getCheckFlag());
					/*
					 * if (tds.getCheckFlag().equals("Y")) {
					 * 
					 * String arrearsQuery = " SELECT NVL(SUM(ARREARS_AMT),'')
					 * from HRMS_ARREARS_DEBIT_" + tds.getYear() + " INNER JOIN
					 * HRMS_ARREARS_LEDGER ON " + " (HRMS_ARREARS_DEBIT_" +
					 * tds.getYear() +
					 * ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE
					 * ARREARS_EMP_ID=" + String.valueOf(tds_amt[i][4]) + " AND
					 * ARREARS_REF_MONTH=" + tds.getMonth() + " AND
					 * ARREARS_REF_YEAR=" + tds.getYear() + " AND ARREARS_TYPE
					 * IN('M','P') AND ARREARS_DEBIT_CODE=" +
					 * String.valueOf(tds_code[0][0]); Object[][] arrears_amount =
					 * getSqlModel() .getSingleResult(arrearsQuery); param[i][0] =
					 * tds_amt[i][0];// Employee Id param[i][1] =
					 * tds_amt[i][1];// Employee Name param[i][2] =
					 * tds_amt[i][2];// Pan No.
					 * 
					 * if (arrears_amount != null || arrears_amount.length != 0)
					 * 
					 * if (!(String.valueOf(arrears_amount[0][0]).equals("null") ||
					 * String.valueOf( arrears_amount[0][0]).equals(""))) {
					 * //logger.info("tds_amt[i][3]===="+tds_amt[i][3]); //
					 * logger.info("arrears_amount[0][0]===="+arrears_amount[0][0]);
					 * totalTax1 =
					 * Math.round(Double.parseDouble(String.valueOf(tds_amt[i][3])) +
					 * Double.parseDouble(String
					 * .valueOf(arrears_amount[0][0])));// TDS // Amount
					 * //logger.info("totalTax1===="+totalTax1); try {
					 * challanTax =
					 * getTaxDtls(Double.parseDouble(String.valueOf(totalTax1)),
					 * Double
					 * .parseDouble(String.valueOf(tdsParameter[0][2])),amount,
					 * tdsParameter); } catch (Exception e) {
					 * logger.error("exception in challanTax",e); } //end of
					 * catch
					 * 
					 * double tottds =
					 * Double.parseDouble(String.valueOf(challanTax[0][0]));
					 * //logger.info("tottds=====>"+tottds); double
					 * sur=Double.parseDouble(String.valueOf(challanTax[0][2]));
					 * //logger.info("sur=====>"+sur); double
					 * cess=Double.parseDouble(String.valueOf(challanTax[0][1]));
					 * //logger.info("cess=====>"+cess);
					 * 
					 * chkAmount = Math.round(tottds) + Math.round(sur) +
					 * Math.round(cess);
					 * //logger.info("chkAmount=====>"+chkAmount);
					 * //logger.info("totalTax1=====>"+totalTax1); chkAmount =
					 * totalTax1 - chkAmount;
					 * //logger.info("chkAmount=====>"+chkAmount); totaltds +=
					 * Double.parseDouble(Utility.twoDecimals(Math.round(tottds) +
					 * chkAmount));
					 * 
					 * surcharge+= sur; educationCess+= cess; totalTax+=
					 * Double.parseDouble(Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(totalTax1)))));
					 * 
					 * param[i][3] = challanTax[0][0];//TDS param[i][4] =
					 * challanTax[0][2];//Surcharge param[i][5] =
					 * challanTax[0][1];//Edu-cess param[i][6] = totalTax1;//
					 * TDS Amount
					 * 
					 * param[i][3] = Utility.twoDecimals(Math.round(tottds) +
					 * chkAmount);//TDS param[i][4] =
					 * challanTax[0][2];//Surcharge param[i][5] =
					 * challanTax[0][1];//Edu-Cess param[i][6] = totalTax1;//
					 * TDS Amount
					 * //logger.info("param[i][6]=11111===>"+param[i][6]); }
					 * //end of if null or blank else{ try { challanTax =
					 * getTaxDtls(Double.parseDouble(String.valueOf(tds_amt[i][3])),
					 * Double
					 * .parseDouble(String.valueOf(tdsParameter[0][2])),amount,
					 * tdsParameter); } catch (Exception e) {
					 * logger.error("exception in challanTax",e); } //end of
					 * catch
					 * 
					 * double tottds =
					 * Double.parseDouble(String.valueOf(challanTax[0][0]));
					 * 
					 * double
					 * sur=Double.parseDouble(String.valueOf(challanTax[0][2]));
					 * 
					 * double
					 * cess=Double.parseDouble(String.valueOf(challanTax[0][1]));
					 * 
					 * 
					 * chkAmount = Math.round(tottds) + Math.round(sur) +
					 * Math.round(cess);
					 * 
					 * chkAmount =
					 * Double.parseDouble(String.valueOf(tds_amt[i][3])) -
					 * chkAmount;
					 * 
					 * totaltds +=
					 * Double.parseDouble(Utility.twoDecimals(Math.round(tottds) +
					 * chkAmount)); surcharge+= sur; educationCess+= cess;
					 * totalTax+=
					 * Double.parseDouble(Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(tds_amt[i][3])))));
					 * 
					 * param[i][3] = Utility.twoDecimals(Math.round(tottds) +
					 * chkAmount);//TDS param[i][4] =
					 * challanTax[0][2];//Surcharge param[i][5] =
					 * challanTax[0][1];//Edu-Cess param[i][6] =
					 * Utility.twoDecimals(String .valueOf(tds_amt[i][3]));//
					 * TDS Amount
					 * //logger.info("param[i][6]=2222===>"+param[i][6]); }
					 * //end of else } else{
					 */

					try {
						challanTax = getTaxDtls(Double.parseDouble(String
								.valueOf(tds_amt[i][3])),
								Double.parseDouble(String
										.valueOf(tdsParameter[0][2])), amount,
								tdsParameter);
					} catch (Exception e) {
						logger.error("exception in challanTax", e);
					} // end of catch

					double tottds = Double.parseDouble(String
							.valueOf(challanTax[0][0]));

					double sur = Double.parseDouble(String
							.valueOf(challanTax[0][2]));

					double cess = Double.parseDouble(String
							.valueOf(challanTax[0][1]));

					chkAmount = Math.round(tottds) + Math.round(sur)
							+ Math.round(cess);

					chkAmount = Double.parseDouble(String
							.valueOf(tds_amt[i][3]))
							- chkAmount;

					totaltds += Double.parseDouble(Utility.twoDecimals(Math
							.round(tottds)
							+ chkAmount));
					surcharge += sur;
					educationCess += cess;
					totalTax += Double.parseDouble(Utility.twoDecimals(Math
							.round(Double.parseDouble(String
									.valueOf(tds_amt[i][3])))));

					param[i][0] = tds_amt[i][0];// Employee Id
					param[i][1] = tds_amt[i][1];// Employee Name
					param[i][2] = tds_amt[i][2];// Pan No.
					param[i][3] = formatter
							.format((Math.round(tottds) + chkAmount));// TDS
					param[i][4] = formatter.format(Double.parseDouble(String
							.valueOf(challanTax[0][2])));// Surcharge
					param[i][5] = formatter.format(Double.parseDouble(String
							.valueOf(challanTax[0][1])));// Edu-Cess
					param[i][6] = formatter.format(Double.parseDouble(String
							.valueOf(tds_amt[i][3])));// TDS Amount
					// logger.info("param[i][6]=3333===>"+param[i][6]);

				} // End of tds_amt for loop
				Object[][] paramTotal = new Object[1][7];
				paramTotal[0][0] = "";
				paramTotal[0][1] = "";
				paramTotal[0][2] = "TOTAL";
				paramTotal[0][3] = formatter.format(totaltds);
				paramTotal[0][4] = formatter.format(surcharge);
				paramTotal[0][5] = formatter.format(educationCess);
				paramTotal[0][6] = formatter.format(totalTax);

				/*
				 * if(tds.getCheckFlag().equals("N")){ rg.addText("\nSalary
				 * Details:", 0, 0, 0); rg.tableBody(colName, param, cellWidth,
				 * alignMent); rg.tableBody(paramTotal, cellWidth, alignMent);
				 * rg.addText("\n", 0, 0, 0); }
				 */

			}// End of tds amt if condition
			// tds.setTdsSalWithArrear("true");
		}// End of ledger code if condition
		else {
			tds.setTdsSalWithArrear("true");
		}

		/**
		 * Following condition is used to select the arrear details.
		 */

		try {

			// * Following query is used to select the arrears of the
			// employees.

			/*
			 * String arrEmpIdQuery = " SELECT distinct HRMS_ARREARS_" +
			 * tds.getYear() + ".EMP_ID,HRMS_ARREARS_" + tds.getYear() +
			 * ".ARREARS_CODE,HRMS_ARREARS_" + tds.getYear() +
			 * ".ARREARS_MONTH,HRMS_ARREARS_" + tds.getYear() + ".arrears_year " + "
			 * ,ARREARS_PROMCODE from hrms_arrears_" + tds.getYear() + " INNER
			 * JOIN HRMS_ARREARS_LEDGER
			 * ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_" +
			 * tds.getYear() + ".ARREARS_CODE) " + " inner join hrms_emp_offc
			 * on(HRMS_ARREARS_" + tds.getYear() +
			 * ".EMP_ID=HRMS_EMP_OFFC.EMP_ID)" + " INNER JOIN HRMS_salary_" +
			 * tds.getYear() + " ON HRMS_SALARY_" + tds.getYear() + ".EMP_ID =
			 * HRMS_EMP_OFFC.EMP_ID " + " INNER JOIN HRMS_SALARY_LEDGER ON
			 * (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_" + tds.getYear() +
			 * ".SAL_LEDGER_CODE AND LEDGER_MONTH=" + tds.getMonth() + " AND
			 * LEDGER_YEAR=" + tds.getYear() + ") " + " WHERE
			 * ARREARS_REF_MONTH=" + tds.getMonth() + " AND ARREARS_REF_YEAR=" +
			 * tds.getYear() + " and arrears_type IN ('M','P') AND
			 * SAL_DIVISION=" + tds.getDivCode();
			 * 
			 * if (!(tds.getOnHold().equals("A"))) { arrEmpIdQuery += " and
			 * SAL_ONHOLD='" + tds.getOnHold() + "'"; }
			 * 
			 * if (!(tds.getBrnCode().equals("") || tds.getBrnCode() == null)) {
			 * 
			 * arrEmpIdQuery += " and sal_emp_center=" + tds.getBrnCode(); }
			 * 
			 * if (!(tds.getDeptCode().equals("") || tds.getDeptCode() == null)) {
			 * arrEmpIdQuery += " and sal_dept=" + tds.getDeptCode();
			 *  } if (!(tds.getTypeCode().equals("") || tds.getTypeCode() ==
			 * null)) { arrEmpIdQuery += " and sal_emp_type=" +
			 * tds.getTypeCode();
			 *  } if (!(tds.getPayBillNo().equals("") || tds.getPayBillNo() ==
			 * null)) { arrEmpIdQuery += " and SAL_EMP_PAYBILL=" +
			 * tds.getPayBillNo(); } arrEmpIdQuery += " ORDER BY HRMS_ARREARS_" +
			 * tds.getYear() + ".EMP_ID";
			 * 
			 * Object[][] emp_id = getSqlModel()
			 * .getSingleResult(arrEmpIdQuery);
			 * 
			 * logger.info("arrears pf debit code" + tds_code[0][0]); int
			 * counter = 0;
			 * 
			 * if (emp_id != null) {
			 *  // * Following query is used to find the length of the array //
			 * i.e. the number of employees.
			 * 
			 * for (int k = 0; k < emp_id.length; k++) {
			 * 
			 * String arrQuery = " SELECT EMP_TOKEN,EMP_FNAME ||' '||EMP_MNAME
			 * ||' '||EMP_LNAME," + " HRMS_ARREARS_" + tds.getYear() +
			 * ".ARREARS_MONTH,HRMS_ARREARS_" + tds.getYear() +
			 * ".ARREARS_YEAR,HRMS_ARREARS_" + tds.getYear() +
			 * ".ARREARS_DAYS,HRMS_ARREARS_DEBIT_" + tds.getYear() +
			 * ".ARREARS_AMT" + " FROM HRMS_ARREARS_" + tds.getYear() + " INNER
			 * JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_" +
			 * tds.getYear() + ".EMP_ID) " + " INNER JOIN HRMS_ARREARS_DEBIT_" +
			 * tds.getYear() + " ON(HRMS_ARREARS_" + tds.getYear() +
			 * ".ARREARS_CODE=HRMS_ARREARS_DEBIT_" + tds.getYear() +
			 * ".ARREARS_CODE AND HRMS_ARREARS_" + tds.getYear() +
			 * ".ARREARS_MONTH=HRMS_ARREARS_DEBIT_" + tds.getYear() +
			 * ".ARREARS_MONTH)" + " WHERE ARREARS_REF_MONTH=" + tds.getMonth() + "
			 * AND ARREARS_REF_YEAR=" + tds.getYear() + " and arrears_type IN
			 * ('M','P') AND SAL_DIVISION=" + tds.getDivCode() + " AND
			 * HRMS_ARREARS_DEBIT_" + tds.getYear() + " AND HRMS_ARREARS_DEBIT_" +
			 * tds.getYear() + ".ARREARS_DEBIT_CODE=" + tds_code[0][0] + " AND
			 * HRMS_ARREARS_DEBIT_" + tds.getYear() + " AND ARREARS_AMT > 0";//
			 * ;AND //
			 * HRMS_ARREARS_DEBIT_"+tds.getYear()+".ARREARS_MONTH="+emp_id[k][2]+ // "
			 * AND //
			 * HRMS_ARREARS_DEBIT_"+tds.getYear()+".ARREARS_DEBIT_CODE="+tds_code[0][0]; // +"
			 * AND //
			 * HRMS_ARREARS_DEBIT_"+tds.getYear()+".PROM_CODE="+emp_id[k][4]+" //
			 * AND //
			 * HRMS_ARREARS_"+tds.getYear()+".ARREARS_PROMCODE="+emp_id[k][4];
			 * if (!(String.valueOf(emp_id[k][4]).equals("") || String
			 * .valueOf(emp_id[k][4]).equals("null"))) { arrQuery += " AND
			 * HRMS_ARREARS_DEBIT_" + tds.getYear() + ".PROM_CODE=" +
			 * emp_id[k][4] + " AND HRMS_ARREARS_" + tds.getYear() +
			 * ".ARREARS_PROMCODE=" + emp_id[k][4]; }
			 * 
			 * arrQuery += " ORDER BY HRMS_ARREARS_" + tds.getYear() +
			 * ".ARREARS_MONTH,HRMS_ARREARS_" + tds.getYear() + ".ARREARS_YEAR";
			 * 
			 * Object[][] arrearAmt = getSqlModel().getSingleResult( arrQuery);
			 * if (arrearAmt == null || arrearAmt.length == 0) {
			 *  } else { counter++; } } }
			 */

			if (param != null) {

				double tdsArrAmt = 0.0;
				int c = 0;
				int counter = 0;
				double totaltds = 0;
				double surcharge = 0;
				double educationCess = 0;
				double totalTax = 0;

				/**
				 * Following query is used to select the arrear details
				 */

				Object[][] param1 = null;

				// logger.info("cess=====>"+cess);
				if (!(arrearAmt == null || arrearAmt.length == 0)) {

					param1 = new Object[arrearAmt.length][11];
					for (int i = 0; i < arrearAmt.length; i++) {

						Object[][] taxDetails = null;
						try {
							taxDetails = getTaxDtls(Double.parseDouble(String
									.valueOf(arrearAmt[i][6])), 0.0, 0.0,
									tdsParameter);

						} catch (Exception e) {
							logger.error("exception in challanTax", e);
						} // end of catch
						double tottds = Double.parseDouble(String
								.valueOf(taxDetails[0][0]));
						// logger.info("tottds=====>"+tottds);
						double sur = 0;
						// logger.info("sur=====>"+sur);
						double cess = Double.parseDouble(String
								.valueOf(taxDetails[0][1]));
						param1[i][0] = String.valueOf(arrearAmt[i][0]);// Emp
						// Id
						param1[i][1] = String.valueOf(arrearAmt[i][1]);// Emp
						// Name
						param1[i][2] = String.valueOf(arrearAmt[i][2]);// PAN

						param1[i][3] = formatter.format(tottds);// PAN

						param1[i][4] = formatter.format(sur);

						param1[i][5] = formatter.format(cess);

						param1[i][6] = formatter.format(Double
								.parseDouble(String.valueOf(arrearAmt[i][6])));
						param1[i][7] = Utility.month(Integer.parseInt(String
								.valueOf(arrearAmt[i][3])));// Month
						param1[i][8] = String.valueOf(arrearAmt[i][4]);// Year
						param1[i][9] = String.valueOf(arrearAmt[i][5]);// Arrears
						// Days
						param1[i][10] = String.valueOf(arrearAmt[i][7]);

						tdsArrAmt += Double.parseDouble(String
								.valueOf(arrearAmt[i][6]));
					}

					String col[] = { "Employee Id", "Employee Name", "Pan No.",
							"TDS", "Surcharge", "Education Cess", "Total Tax",
							"Month", "Year", "Arrears Days", "Arrear Type" };
					int cellWidth1[] = { 12, 20, 15, 15, 10, 10, 10, 8, 8, 10,
							10 };
					int[] align = { 0, 0, 0, 2, 2, 2, 2, 0, 1, 2, 0 };

					if (tds.getCheckFlag().equals("N")) {

						/*
						 * rg.addText("\nSalary Details:", 0, 0, 0);
						 * rg.tableBody(colName, param, cellWidth, alignMent);
						 */

						Object[][] title = new Object[1][1];
						title[0][0] = "Salary Details";
						TableDataSet reportObjTitle = new TableDataSet();
						reportObjTitle.setCellAlignment(new int[] { 0 });
						reportObjTitle.setCellWidth(new int[] { 100 });
						reportObjTitle.setData(title);
						reportObjTitle.setBorderDetail(0);
						reportObjTitle.setBodyFontStyle(1);
						// reportObjTitle.setBlankRowsBelow(1);
						rg.addTableToDoc(reportObjTitle);

						boolean[] bcellwrap = new boolean[] { true, true, true,
								true, true, true, true };
						TableDataSet reportObjData = new TableDataSet();
						reportObjData.setHeader(colName);
						reportObjData.setCellAlignment(alignMent);
						reportObjData.setCellWidth(cellWidth);
						reportObjData.setData(param);
						// reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
						// reportObjData.setBorder(true);
						// reportObjData.setHeaderBorderDetail(2);
						// reportObjData.setHeaderLines(true);
						reportObjData.setHeaderBorderColor(new BaseColor(255,
								0, 0));
						reportObjData.setBorderDetail(3);
						reportObjData.setHeaderBorderDetail(3);
						// reportObjData.setHeaderBGColor(new BaseColor(225,
						// 225, 225));
						reportObjData.setHeaderTable(true);
						reportObjData.setCellNoWrap(bcellwrap);
						reportObjData.setBlankRowsBelow(1);
						rg.addTableToDoc(reportObjData);

						for (int j = 0; j < param.length; j++) {
							totaltds += Double.parseDouble(String
									.valueOf(param[j][3]));
							educationCess += Double.parseDouble(String
									.valueOf(param[j][5]));
							totalTax += Double.parseDouble(String
									.valueOf(param[j][6]));
						}
						Object[][] paramTotal = new Object[1][7];
						paramTotal[0][0] = "";
						paramTotal[0][1] = "";
						paramTotal[0][2] = "TOTAL";
						paramTotal[0][3] = formatter.format(totaltds);
						paramTotal[0][4] = formatter.format(surcharge);
						paramTotal[0][5] = formatter.format(educationCess);
						paramTotal[0][6] = formatter.format(totalTax);

						/*
						 * rg.tableBody(paramTotal, cellWidth, alignMent);
						 * rg.addText("\n", 0, 0, 0);
						 */

						TableDataSet reportTotalData = new TableDataSet();
						reportTotalData.setCellAlignment(alignMent);
						reportTotalData.setCellWidth(cellWidth);
						reportTotalData.setData(paramTotal);
						// reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
						// reportObjData.setBorder(true);
						reportTotalData.setBodyFontStyle(1);
						reportTotalData.setBorderDetail(0);
						reportTotalData.setBorderLines(true);
						reportTotalData.setBlankRowsBelow(1);
						rg.addTableToDoc(reportTotalData);

						reportObjData = new TableDataSet();
						Object[][] paramTotalRec = new Object[2][2];
						paramTotalRec[0][0] = "Total Records: " + param.length;
						paramTotalRec[1][1] = "Grand Total : "
								+ formatter.format(totalTax);
						reportObjData.setCellAlignment(new int[] { 0, 2 });
						reportObjData.setCellWidth(new int[] { 50, 50 });
						reportObjData.setData(paramTotalRec);
						reportObjData.setCellColSpan(new int[] { 1,
								param[0].length - 1 });
						// reportObjData.setBorder(true);
						reportObjData.setBlankRowsBelow(1);
						reportObjData.setBodyBGColor(new BaseColor(200, 200,
								200));
						rg.addTableToDoc(reportObjData);

						if (param1 != null && param1.length > 0) {
							/*
							 * rg.addText("\n\nArrears", 0, 0, 0);
							 * rg.tableBody(col, param1, cellWidth1, align);
							 */
							title = new Object[1][1];
							title[0][0] = "Arrears";
							reportObjTitle = new TableDataSet();
							reportObjTitle.setCellAlignment(new int[] { 0 });
							reportObjTitle.setCellWidth(new int[] { 100 });
							reportObjTitle.setData(title);
							reportObjTitle.setBodyFontStyle(1);
							reportObjTitle.setBorderDetail(0);
							// reportObjTitle.setBlankRowsBelow(1);
							rg.addTableToDoc(reportObjTitle);

							bcellwrap = new boolean[] { true, true, true, true,
									true, true, true, true, true, true, true };
							reportObjData = new TableDataSet();
							reportObjData.setHeader(col);
							reportObjData.setCellAlignment(align);
							reportObjData.setCellWidth(cellWidth1);
							reportObjData.setData(param1);
							// reportObjData.setColumnSum(new int[]{4, 5, 6,
							// 7});
							// reportObjData.setBorder(true);
							// reportObjData.setHeaderBorderDetail(2);
							// reportObjData.setHeaderLines(true);
							reportObjData.setHeaderBorderColor(new BaseColor(
									255, 0, 0));
							reportObjData.setBorderDetail(3);
							reportObjData.setHeaderBorderDetail(3);
							// reportObjData.setHeaderBGColor(new BaseColor(225,
							// 225, 225));
							reportObjData.setHeaderTable(true);
							reportObjData.setCellNoWrap(bcellwrap);
							reportObjData.setBlankRowsBelow(1);
							rg.addTableToDoc(reportObjData);

							double arrTotaltds = 0;
							double arrSurcharge = 0;
							double arrEducationCess = 0;
							double arrTotalTax = 0;
							// rg.addFormatedText("\t\t\t\t\t\t\t\tArrear
							// Amount:\t\t\t\t\t\t "+Math.round(tdsArrAmt),
							// 0,0,2,10);
							for (int j = 0; j < param1.length; j++) {
								arrTotaltds += Double.parseDouble(String
										.valueOf(param1[j][3]));
								arrEducationCess += Double.parseDouble(String
										.valueOf(param1[j][5]));
								arrTotalTax += Double.parseDouble(String
										.valueOf(param1[j][6]));
							}
							Object[][] arrParamTotal = new Object[1][7];
							arrParamTotal[0][0] = "";
							arrParamTotal[0][1] = "";
							arrParamTotal[0][2] = "TOTAL";
							arrParamTotal[0][3] = formatter.format(arrTotaltds);
							arrParamTotal[0][4] = formatter
									.format(arrSurcharge);
							arrParamTotal[0][5] = formatter
									.format(arrEducationCess);
							arrParamTotal[0][6] = formatter.format(arrTotalTax);

							/*
							 * rg.tableBody(arrParamTotal, cellWidth,
							 * alignMent); rg.addText("\n", 0, 0, 0);
							 */
							reportObjData = new TableDataSet();
							reportObjData.setCellAlignment(alignMent);
							reportObjData.setCellWidth(cellWidth);
							reportObjData.setData(arrParamTotal);
							reportObjData.setBorderDetail(0);
							reportObjData.setBorderLines(true);
							reportObjData.setBodyFontStyle(1);
							reportObjData.setBlankRowsBelow(1);
							rg.addTableToDoc(reportObjData);

							reportObjData = new TableDataSet();
							Object[][] paramTotalRec1 = new Object[2][2];
							paramTotalRec1[0][0] = "Total Records: "
									+ param1.length;
							paramTotalRec1[1][1] = "Grand Total : "
									+ formatter.format(totalTax);
							reportObjData.setCellAlignment(new int[] { 0, 2 });
							reportObjData.setCellWidth(new int[] { 50, 50 });
							reportObjData.setData(paramTotalRec1);
							reportObjData.setBorder(true);
							reportObjData.setCellColSpan(new int[] { 1,
									param1[0].length - 1 });
							// reportObjData.setBorderDetail(2);
							reportObjData.setBodyBGColor(new BaseColor(200,
									200, 200));
							reportObjData.setBlankRowsBelow(1);
							rg.addTableToDoc(reportObjData);

						} else {
							tds.setTdsWithoutSal("true");
						}
					} else {
						param = consolidateArrearsObject(param, param1, 0,
								new int[] { 3, 4, 5, 6 }, 7);
						/*
						 * rg.addText("\nSalary Details:", 0, 0, 0);
						 * rg.tableBody(colName, param, cellWidth, alignMent);
						 */

						Object[][] title = new Object[1][1];
						title[0][0] = "Salary Details";
						TableDataSet reportObjTitle = new TableDataSet();
						reportObjTitle.setCellAlignment(new int[] { 0 });
						reportObjTitle.setCellWidth(new int[] { 100 });
						reportObjTitle.setData(title);
						reportObjTitle.setBorderDetail(0);
						reportObjTitle.setBodyFontStyle(1);
						// reportObjTitle.setBlankRowsBelow(1);
						rg.addTableToDoc(reportObjTitle);

						boolean[] bcellwrap = new boolean[] { true, true, true,
								true, true, true, true };
						TableDataSet reportObjData = new TableDataSet();
						reportObjData.setHeader(colName);
						reportObjData.setCellAlignment(alignMent);
						reportObjData.setCellWidth(cellWidth);
						reportObjData.setData(param);
						// reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
						// reportObjData.setBorder(true);
						// reportObjData.setHeaderBorderDetail(2);
						// reportObjData.setHeaderLines(true);
						reportObjData.setHeaderBorderColor(new BaseColor(255,
								0, 0));
						reportObjData.setBorderDetail(3);
						reportObjData.setHeaderBorderDetail(3);
						reportObjData.setCellNoWrap(bcellwrap);
						// reportObjData.setHeaderBGColor(new BaseColor(225,
						// 225, 225));
						reportObjData.setHeaderTable(true);
						reportObjData.setBlankRowsBelow(1);
						rg.addTableToDoc(reportObjData);

						for (int j = 0; j < param.length; j++) {
							totaltds += Double.parseDouble(String
									.valueOf(param[j][3]));
							educationCess += Double.parseDouble(String
									.valueOf(param[j][5]));
							totalTax += Double.parseDouble(String
									.valueOf(param[j][6]));
						}
						Object[][] paramTotal = new Object[1][7];
						paramTotal[0][0] = "";
						paramTotal[0][1] = "";
						paramTotal[0][2] = "TOTAL";
						paramTotal[0][3] = formatter.format(totaltds);
						paramTotal[0][4] = formatter.format(surcharge);
						paramTotal[0][5] = formatter.format(educationCess);
						paramTotal[0][6] = formatter.format(totalTax);
						/*
						 * rg.tableBody(paramTotal, cellWidth, alignMent);
						 * rg.addText("\n", 0, 0, 0);
						 */
						reportObjData = new TableDataSet();
						reportObjData.setCellAlignment(alignMent);
						reportObjData.setCellWidth(cellWidth);
						reportObjData.setData(paramTotal);
						// reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
						// reportObjData.setBorder(true);
						reportObjData.setBorderDetail(0);
						reportObjData.setBorderLines(true);
						reportObjData.setBodyFontStyle(1);
						reportObjData.setBlankRowsBelow(1);
						rg.addTableToDoc(reportObjData);

						reportObjData = new TableDataSet();
						Object[][] paramTotalRec = new Object[2][2];
						paramTotalRec[0][0] = "Total Records: " + param.length;
						paramTotalRec[1][1] = "Grand Total : "
								+ formatter.format(totalTax);
						reportObjData.setCellAlignment(new int[] { 0, 2 });
						reportObjData.setCellWidth(new int[] { 50, 50 });
						reportObjData.setData(paramTotalRec);
						reportObjData.setCellColSpan(new int[] { 1,
								param[0].length - 1 });
						// reportObjData.setBorder(true);
						// reportObjData.setBorderDetail(2);
						reportObjData.setBodyBGColor(new BaseColor(200, 200,
								200));
						reportObjData.setBlankRowsBelow(1);
						rg.addTableToDoc(reportObjData);
					}

				} else {
					/*
					 * rg.addText("\nSalary Details:", 0, 0, 0);
					 * rg.tableBody(colName, param, cellWidth, alignMent);
					 */
					Object[][] title = new Object[1][1];
					title[0][0] = "Salary Details";
					TableDataSet reportObjTitle = new TableDataSet();
					reportObjTitle.setCellAlignment(new int[] { 0 });
					reportObjTitle.setCellWidth(new int[] { 100 });
					reportObjTitle.setData(title);
					reportObjTitle.setBodyFontStyle(1);
					reportObjTitle.setBorderDetail(0);
					// reportObjTitle.setBlankRowsBelow(1);
					rg.addTableToDoc(reportObjTitle);

					boolean[] bcellwrap = new boolean[] { true, true, true,
							true, true, true, true };
					TableDataSet reportObjData = new TableDataSet();
					reportObjData.setHeader(colName);
					reportObjData.setCellAlignment(alignMent);
					reportObjData.setCellWidth(cellWidth);
					reportObjData.setData(param);
					// reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
					// reportObjData.setBorder(true);
					// reportObjData.setHeaderBorderDetail(2);
					// reportObjData.setHeaderLines(true);
					reportObjData
							.setHeaderBorderColor(new BaseColor(255, 0, 0));
					reportObjData.setBorderDetail(3);
					reportObjData.setHeaderBorderDetail(3);
					reportObjData.setCellNoWrap(bcellwrap);
					// reportObjData.setHeaderBGColor(new BaseColor(225, 225,
					// 225));
					reportObjData.setHeaderTable(true);
					reportObjData.setBlankRowsBelow(1);
					rg.addTableToDoc(reportObjData);

					for (int j = 0; j < param.length; j++) {
						totaltds += Double.parseDouble(String
								.valueOf(param[j][3]));
						educationCess += Double.parseDouble(String
								.valueOf(param[j][5]));
						totalTax += Double.parseDouble(String
								.valueOf(param[j][6]));
					}
					Object[][] paramTotal = new Object[1][7];
					paramTotal[0][0] = "";
					paramTotal[0][1] = "";
					paramTotal[0][2] = "TOTAL";
					paramTotal[0][3] = formatter.format(totaltds);
					paramTotal[0][4] = formatter.format(surcharge);
					paramTotal[0][5] = formatter.format(educationCess);
					paramTotal[0][6] = formatter.format(totalTax);
					/*
					 * rg.tableBody(paramTotal, cellWidth, alignMent);
					 * rg.addText("\n", 0, 0, 0);
					 */
					reportObjData = new TableDataSet();
					reportObjData.setCellAlignment(alignMent);
					reportObjData.setCellWidth(cellWidth);
					reportObjData.setData(paramTotal);
					// reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
					reportObjData.setBorder(true);
					reportObjData.setBorderDetail(0);
					reportObjData.setBodyFontStyle(1);
					reportObjData.setBorderLines(true);
					reportObjData.setBlankRowsBelow(1);
					rg.addTableToDoc(reportObjData);

					reportObjData = new TableDataSet();
					Object[][] paramTotalRec = new Object[2][2];
					paramTotalRec[0][0] = "Total Records: " + param.length;
					paramTotalRec[1][1] = "Grand Total : "
							+ formatter.format(totalTax);
					reportObjData.setCellAlignment(new int[] { 0, 2 });
					reportObjData.setCellWidth(new int[] { 50, 50 });
					reportObjData.setData(paramTotalRec);
					reportObjData.setBorder(true);
					reportObjData.setCellColSpan(new int[] { 1,
							param[0].length - 1 });
					// reportObjData.setBorderDetail(2);
					reportObjData.setBodyBGColor(new BaseColor(200, 200, 200));
					reportObjData.setBlankRowsBelow(1);
					rg.addTableToDoc(reportObjData);

				}
			} else {
				Object[][] title = new Object[1][2];
				title[0][0] = "Salary Details : No records avaliable for selected criteria";
				TableDataSet reportObjTitle = new TableDataSet();
				reportObjTitle.setCellAlignment(new int[] { 0 });
				reportObjTitle.setCellWidth(new int[] { 100 });
				reportObjTitle.setData(title);
				reportObjTitle.setBodyFontStyle(1);
				reportObjTitle.setBorderDetail(0);
				reportObjTitle.setCellColSpan(new int[] {3});
				// reportObjTitle.setBlankRowsBelow(1);
				rg.addTableToDoc(reportObjTitle);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();

			Object[][] title = new Object[1][2];
			title[0][0] = "Salary Details : No records avaliable for selected criteria";
			TableDataSet reportObjTitle = new TableDataSet();
			reportObjTitle.setCellAlignment(new int[] { 0 });
			reportObjTitle.setCellWidth(new int[] { 100 });
			reportObjTitle.setData(title);
			reportObjTitle.setBodyFontStyle(1);
			reportObjTitle.setBorderDetail(0);
			reportObjTitle.setCellColSpan(new int[] {3});
			// reportObjTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(reportObjTitle);

			/*
			 * final TableDataSet headerName1 = new TableDataSet();
			 * headerName1.setData(new Object[][] { { " " }, { "No records
			 * avaliable for selected criteria" } });
			 * headerName1.setCellAlignment(new int[] { 1 });
			 * headerName1.setCellWidth(new int[] { 100 });
			 * headerName1.setBodyFontStyle(1); headerName1.setBorder(false);
			 * headerName1.setHeaderTable(false); rg.addTableToDoc(headerName1);
			 */
		}

		return rg;

	}

	/**
	 * following function is called when Tds button is clicked and report option
	 * is Settlement or All
	 * 
	 * @param rg
	 * @param tds
	 * @return
	 */
	public ReportGenerator getSettlementDtl(ReportGenerator rg, TDSReport tds) {
		double settlementAmt = 0.00;

		try {
			String tdsDebitCodeQuery = " SELECT HRMS_TAX_PARAMETER.TDS_DEBIT_CODE FROM  HRMS_TAX_PARAMETER WHERE TO_CHAR(HRMS_TAX_PARAMETER.TDS_EFF_DATE,'dd-MON-yyyy') = (select max(HRMS_TAX_PARAMETER.TDS_EFF_DATE) FROM  HRMS_TAX_PARAMETER WHERE to_char(HRMS_TAX_PARAMETER.TDS_EFF_DATE,'yyyy-mm')<='"
					+ tds.getYear() + "-" + tds.getMonth() + "')";
			/*
			 * String tdsDebitCodeQuery=" SELECT TDS_DEBIT_CODE FROM
			 * HRMS_TAX_PARAMETER WHERE TO_CHAR(TDS_EFF_DATE,'dd-MON-yyyy') =
			 * (select max(TDS_EFF_DATE) FROM HRMS_TAX_PARAMETER WHERE
			 * to_char(TDS_EFF_DATE,'yyyy')<="+tds.getYear()+")" ;
			 */
			Object[][] tds_code = getSqlModel().getSingleResult(
					tdsDebitCodeQuery);

			if (tds_code == null || tds_code.length <= 0) {

				// rg.addText("Records are not available.", 0, 1, 0);

			} else {
				String monYear = tds.getMonth() + "-" + tds.getYear();
				String settleQuery = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,"
						+ " NVL(TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),' '),NVL(TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'),' '),"
						+ " SUM(NVL(SETTL_AMT,0)) FROM  "

						+ " HRMS_SETTL_HDR INNER JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_RESIGN.RESIGN_EMP)"
						+ " INNER JOIN HRMS_SETTL_DEBITS ON(HRMS_SETTL_DEBITS.SETTL_CODE=HRMS_SETTL_HDR.SETTL_CODE)"
						+ " WHERE HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE="
						+ tds_code[0][0]
						+ " AND emp_div in("
						+ tds.getDivCode()
						+ ")  "
						+ " and SETTL_AMT <>0  AND TO_CHAR(SETTL_SETTLDT,'MM-YYYY')="
						+ "'" + monYear + "'";

				if (!(tds.getBrnCode().equals("") || tds.getBrnCode() == null)) {

					settleQuery += " AND EMP_CENTER in(" + tds.getBrnCode()
							+ ")";
				}

				if (!(tds.getDeptCode().equals("") || tds.getDeptCode() == null)) {
					settleQuery += " AND EMP_DEPT in(" + tds.getDeptCode()
							+ ")";

				}
				if (!(tds.getTypeCode().equals("") || tds.getTypeCode() == null)) {
					settleQuery += " AND EMP_TYPE in(" + tds.getTypeCode()
							+ ")";

				}
				if (!(tds.getPayBillNo().equals("") || tds.getPayBillNo() == null)) {
					settleQuery += " AND EMP_PAYBILL in(" + tds.getPayBillNo()
							+ ")";
				}

				if (!(tds.getCadreCode().equals("") || tds.getCadreCode() == null)) {
					settleQuery += " AND HRMS_EMP_OFFC.EMP_CADRE in("
							+ tds.getCadreCode() + ")";
				}

				settleQuery += " GROUP BY EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,"
						+ " NVL(TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),' '),NVL(TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'),' ')";

				Object settData[][] = getSqlModel()
						.getSingleResult(settleQuery);
				if (settData != null || settData.length != 0) {
					for (int i = 0; i < settData.length; i++) {
						settlementAmt += Double.parseDouble(String
								.valueOf(settData[i][4]));
					}
					String[] settleCol = { "Employee Id", "Employee Name",
							"Date Of Leaving", "Settlement Date", "Amount" };
					int[] settleWidth = { 10, 20, 12, 12, 10 };
					int[] settleAlign = { 0, 0, 1, 1, 2 };
					if (settlementAmt != 0) {
						/*
						 * rg.addText("Settlement Details :", 0, 0, 0);
						 * 
						 * rg.addText("\n", 0, 0, 0); rg.tableBody(settleCol,
						 * settData, settleWidth, settleAlign);
						 */
						Object[][] title = new Object[1][1];
						title[0][0] = "Settlement Details";
						TableDataSet reportObjTitle = new TableDataSet();
						reportObjTitle.setCellAlignment(new int[] { 0 });
						reportObjTitle.setCellWidth(new int[] { 100 });
						reportObjTitle.setData(title);
						reportObjTitle.setBodyFontStyle(1);
						reportObjTitle.setBorderDetail(0);
						// reportObjTitle.setBlankRowsBelow(1);
						rg.addTableToDoc(reportObjTitle);

						boolean[] bcellwrap = new boolean[] { true, true, true,
								true, true };
						TableDataSet reportObjData = new TableDataSet();
						reportObjData.setHeader(settleCol);
						reportObjData.setCellAlignment(settleAlign);
						reportObjData.setCellWidth(settleWidth);
						reportObjData.setData(settData);
						// reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
						// reportObjData.setBorder(true);
						// reportObjData.setHeaderBorderDetail(2);
						// reportObjData.setHeaderLines(true);
						reportObjData.setHeaderBorderColor(new BaseColor(255,
								0, 0));
						reportObjData.setBorderDetail(3);
						reportObjData.setHeaderBorderDetail(3);
						reportObjData.setCellNoWrap(bcellwrap);
						// reportObjData.setHeaderBGColor(new BaseColor(225,
						// 225, 225));
						reportObjData.setHeaderTable(true);
						reportObjData.setBlankRowsBelow(1);
						rg.addTableToDoc(reportObjData);

						/*
						 * rg.addText("\n", 0, 0, 0);
						 * 
						 * rg.addText("\n", 0, 0, 0);
						 */
					} else {
						tds.setTdsSetFlag("true");

						Object[][] title = new Object[1][1];
						title[0][0] = "Settlement Details : No Settlement records avaliable for selected criteria";
						TableDataSet reportObjTitle = new TableDataSet();
						reportObjTitle.setCellAlignment(new int[] { 0 });
						reportObjTitle.setCellWidth(new int[] { 100 });
						reportObjTitle.setData(title);
						reportObjTitle.setBodyFontStyle(1);
						reportObjTitle.setBorderDetail(0);
						reportObjTitle.setCellColSpan(new int[] {3});
						// reportObjTitle.setBlankRowsBelow(1);
						rg.addTableToDoc(reportObjTitle);

						/*
						 * final TableDataSet headerName1 = new TableDataSet();
						 * headerName1.setData(new Object[][] { { " " }, { "No
						 * Settlement records avaliable for selected criteria" }
						 * }); headerName1.setCellAlignment(new int[] { 1 });
						 * headerName1.setCellWidth(new int[] { 100 });
						 * headerName1.setBodyFontStyle(1);
						 * headerName1.setBorder(false);
						 * headerName1.setHeaderTable(false); //
						 * headerName6.setBlankRowsBelow(1);
						 * rg.addTableToDoc(headerName1);
						 */
					}
				}
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			e.printStackTrace();

			Object[][] title = new Object[1][1];
			title[0][0] = "Settlement Details : No Settlement records avaliable for selected criteria";
			TableDataSet reportObjTitle = new TableDataSet();
			reportObjTitle.setCellAlignment(new int[] { 0 });
			reportObjTitle.setCellWidth(new int[] { 100 });
			reportObjTitle.setData(title);
			reportObjTitle.setBodyFontStyle(1);
			reportObjTitle.setCellColSpan(new int[] {3});
			reportObjTitle.setBorderDetail(0);
			// reportObjTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(reportObjTitle);

			/*
			 * final TableDataSet headerName1 = new TableDataSet();
			 * headerName1.setData(new Object[][] { { " " }, { "No records
			 * avaliable for selected criteria" } });
			 * headerName1.setCellAlignment(new int[] { 1 });
			 * headerName1.setCellWidth(new int[] { 100 });
			 * headerName1.setBodyFontStyle(1); headerName1.setBorder(false);
			 * headerName1.setHeaderTable(false); //
			 * headerName6.setBlankRowsBelow(1); rg.addTableToDoc(headerName1);
			 */

		}
		return rg;

	}

	public Object[][] consolidateArrearsObject(Object salaObject[][],
			Object arrearsObject[][], int x, int[] colNo, int totalCol) {
		Object[][] consolidatedObject = null;
		Object[][] arrearsNewObject = null;
		Object[][] salaNewObject = null;
		HashMap<String, Object[]> empMap = new HashMap<String, Object[]>();
		try {
			if (salaObject != null || salaObject.length > 0) {
				salaNewObject = new Object[salaObject.length][totalCol];
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (arrearsObject != null || arrearsObject.length > 0) {
				arrearsNewObject = new Object[arrearsObject.length][totalCol];
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			try {
				if (salaObject != null || salaObject.length > 0) {

					for (int j = 0; j < salaNewObject.length; j++) {
						for (int k = 0; k < salaNewObject[0].length; k++) {
							salaNewObject[j][k] = salaObject[j][k];
						}
					}
					for (int i = 0; i < salaNewObject.length; i++) {
						if (empMap.containsKey(String
								.valueOf(salaNewObject[i][x]))) {
							Object[] temObj = empMap.get(String
									.valueOf(salaNewObject[i][x]));
							for (int j = 0; j < colNo.length; j++) {

								temObj[colNo[j]] = formatter
										.format(Double.parseDouble(String
												.valueOf(temObj[colNo[j]]))
												+ Double
														.parseDouble(String
																.valueOf(salaNewObject[i][colNo[j]])));
							}
							empMap.put(String.valueOf(salaNewObject[i][x]),
									temObj);
						} else {
							empMap.put(String.valueOf(salaNewObject[i][x]),
									salaNewObject[i]);
						}
					}
				}
			} catch (Exception e) {
				logger.error("Exception in salaNewObject" + e);
			}
			try {
				if (arrearsObject != null || arrearsObject.length > 0) {
					for (int j = 0; j < arrearsNewObject.length; j++) {
						for (int k = 0; k < arrearsNewObject[0].length; k++) {
							arrearsNewObject[j][k] = arrearsObject[j][k];
						}
					}
					for (int i = 0; i < arrearsNewObject.length; i++) {
						if (empMap.containsKey(String
								.valueOf(arrearsNewObject[i][x]))) {
							Object[] temObj = empMap.get(String
									.valueOf(arrearsNewObject[i][x]));
							for (int j = 0; j < colNo.length; j++) {

								temObj[colNo[j]] = formatter
										.format(Double.parseDouble(String
												.valueOf(temObj[colNo[j]]))
												+ Double
														.parseDouble(String
																.valueOf(arrearsNewObject[i][colNo[j]])));
							}
							empMap.put(String.valueOf(arrearsNewObject[i][x]),
									temObj);
						} else {
							empMap.put(String.valueOf(arrearsNewObject[i][x]),
									arrearsNewObject[i]);
						}
					}
				}
			} catch (Exception e) {
				logger.error("Exception in arrearsNewObject" + e);
			}

			if (empMap.size() > 0) {
				consolidatedObject = new Object[empMap.size()][totalCol];
				int i = 0;
				for (Iterator<String> iter = empMap.keySet().iterator(); iter
						.hasNext();) {
					String empId = iter.next();

					consolidatedObject[i++] = empMap.get(empId);

				}
			}
			/*
			 * for (int j = 0; j < consolidatedObject.length; j++) { for (int k =
			 * 0; k < consolidatedObject[0].length; k++) {
			 * logger.info("consolidatedObject[==" + consolidatedObject[j][k]); }
			 *  }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consolidatedObject;
	}

	public void getTdsReport(TDSReport tds, HttpServletRequest request,
			HttpServletResponse response, String path) throws Exception// Generates
																		// the
																		// TDS
																		// Report
	{

		try {
			ReportDataSet rds = new ReportDataSet();
			String type = tds.getReportType();
			rds.setReportType(type);
			String fileName ="TDS_"+ Utility.month(Integer.parseInt(tds.getMonth())).substring(0, 3) 
					+ tds.getYear().substring(tds.getYear().length()-2, tds.getYear().length())
					+"_"+ Utility.getRandomNumber(1000);
			
			String reportPathName = tds + fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("TDS Report");
			rds.setPageSize("A4");
			rds.setShowPageNo(true);
			rds.setMarginTop(25f);
			rds.setUserEmpId(tds.getUserEmpId());
			rds.setTotalColumns(7);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;

			if (path.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				logger.info("################# ATTACHMENT PATH #############"+ path + fileName + "." + type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, path, session, context, request);
				request.setAttribute("reportPath", path + fileName + "."+ type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "TDSReport_input.action");
			}

			int cnt = 0;
			String filterObj = "";
			filterObj = "Period: "
					+ Utility.month(Integer.parseInt(tds.getMonth())) + " "
					+ tds.getYear();

			int rowCnt = 1;
			int colCnt = 1;
			filterObj += "\n\nDivision : " + tds.getDivName();

			if (tds.getBrnName() != null && !tds.getBrnName().equals("")) {
				filterObj += "\n\nBranch : " + tds.getBrnName();
			}
			if (tds.getDeptName() != null && !tds.getDeptName().equals("")) {
				filterObj += "\n\nDepartment : " + tds.getDeptName();
			}
			if (tds.getPayBillName() != null
					&& !tds.getPayBillName().equals("")) {
				filterObj += "\n\nPay Bill : " + tds.getPayBillName();
			}
			if (tds.getTypeName() != null && !tds.getTypeName().equals("")) {
				filterObj += "\n\nEmployee Type : " + tds.getTypeName();
			}
			if (tds.getCadreName() != null && !tds.getCadreName().equals("")) {
				filterObj += "\n\nGrade : " + tds.getCadreName();
			}

			TableDataSet filterName = new TableDataSet();

			filterName.setData(new Object[][] { { filterObj } });
			filterName.setCellAlignment(new int[] { 0 });
			//filterName.setBodyFontStyle(1);
			filterName.setCellWidth(new int[] { 100 });
			filterName.setCellColSpan(new int[] { 16 });
			filterName.setCellNoWrap(new boolean[] { false });

			// filterName.setBodyFontDetails(FontFamily.HELVETICA, 10,
			// Font.NORMAL, new BaseColor(0, 0, 0));
			filterName.setBorder(false);
			filterName.setBlankRowsBelow(1);
			rg.addTableToDoc(filterName);

			if (tds.getReportOption().equals("A")) {

				rg = getTdsReportArrears(rg, tds);

				if (tds.getTdsArrFlag().equals("true")) {
					rg = getMessage(rg);
				}

			} else if (tds.getReportOption().equals("S")) {

				rg = getTdsReportSalary(rg, tds);
				if (tds.getTdsSalFlag().equals("true")) {
					rg = getMessage(rg);
				}

			} else if (tds.getReportOption().equals("O")) {
				rg = getTdsSalaryWithArrears(rg, tds);
				rg = getSettlementDtl(rg, tds);
				if (tds.getTdsSalWithArrear().equals("true")
						&& tds.getTdsWithoutSal().equals("true")
						&& tds.getTdsSetFlag().equals("true")) {
					rg = getMessage(rg);
				}

			} else if (tds.getReportOption().equals("se")) {
				rg = getSettlementDtl(rg, tds);
				if (tds.getTdsSetFlag().equals("true")) {
					rg = getMessage(rg);
				}
			}

			/*
			String sql = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE  EMP_ID=" + tds.getUserEmpId();
			Object[][] loginEmp = getSqlModel().getSingleResult(sql);
			Object[][] loginObj = new Object[1][1];
			SimpleDateFormat formatter = new SimpleDateFormat("dd, MMM yyyy HH:mm");
			loginObj[0][0] = "Generated By " + loginEmp[0][0] + " on " + formatter.format(new java.util.Date());// "05, Dec 2011 18:30 " ;
			
			TableDataSet genBy = new TableDataSet();			
			genBy.setData(loginObj);
			genBy.setCellAlignment(new int[] { 0 });
			genBy.setCellWidth(new int[] { 100 });
			//genBy.setBodyFontDetails(FontFamily.HELVETICA, 10,	Font.NORMAL, new BaseColor(0, 0, 0));
			genBy.setBorder(false);
			genBy.setBlankRowsBelow(1);
			rg.addTableToDoc(genBy);
			 */

			rg.process();
			if (path.equals("")) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment*/
				rg.saveReport(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called to display the message when no records are
	 * available.
	 * 
	 * @param rg
	 * @param ptax
	 * @return
	 */
	public ReportGenerator getMessage(ReportGenerator rg) {
		//rg.addText("Records are not available", 0, 1, 0);
		return rg;
	}

}
