package org.paradyne.model.payroll;

import java.awt.Color;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.PFSubsReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.lowagie.text.Font;

/**
 * @author aa1385 Date:06-12-2011
 */
public class PFSubsReportModel extends ModelBase {
	NumberFormat formatter = new DecimalFormat("#0.00");
	/**
	 * report type Pdf.
	 */
	public static final String REPORT_TYPE_PDF = "Pdf";
	/**
	 * Log4j logger.
	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PFSubsReportModel.class);

	/**
	 * @param str :
	 *            string
	 * @param space :
	 *            space
	 * @return str
	 */
	public String addSpaces(String str, final int space) {
		for (int i = 0; i < space; i++) {
			str += " ";
		}
		return str;

	}

	/**
	 * Method to check string is null or not.
	 * 
	 * @param result -
	 *            Check null or not
	 * @return - String after checking null
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}

	public String checkNullValue(final String result) {
		if (result == null || "null".equals(result)) {
			return "0";
		} else {
			return result;
		}
	}

	public void getReport(PFSubsReportBean bean, HttpServletResponse response,
			HttpServletRequest request) {

		try {
			Date date = new Date();
			final String month = bean.getMonth(); // month
			String prevMonth = "" + (Integer.parseInt(month) - 1);
			System.out.println("prevMonth==" + prevMonth);
			final String year = bean.getYear(); // year
			System.out.println("year==" + year);
			final String reportType = bean.getReport(); // report type
			
			final String divName = bean.getDivName(); // division
			final String brnName = bean.getBranchName(); // branch
			
			
			logger.info("reportType--------------->" + reportType + "<-------");

	
			final String reportName = "PF Subs Report";
			final ReportGenerator rg = new ReportGenerator(reportType, reportName + "_" + Utility.month(Integer.parseInt(month)) + "_" + year, "A4");
			if (PFSubsReportModel.REPORT_TYPE_PDF.equals(reportType)) {
				rg.addFormatedText(reportName, 6, 0, 1, 0);
			} else {
				final Object[][] title = new Object[1][3];
				title[0][0] = "";
				title[0][1] = "";
				title[0][2] = reportName;

				final int[] cols = {20, 20, 30};
				final int[] align = {0, 0, 1};
				rg.tableBodyNoCellBorder(title, cols, align, 1);
			}
			
			// set current date
			final Date today = new Date();
			final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			final String toDay = sdf.format(today);
			
			
			rg.addText("\n", 0, 0, 0);
			
			rg.addTextBold("PF Subs Report For "+	Utility.month(Integer.parseInt(month))+" "+year , 0, 1, 1, 12);
			rg.addText("Date: " + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);

			int cntFilter = 0;
			
			if (!"".equals(divName)) {
				if (PFSubsReportModel.REPORT_TYPE_PDF.equals(reportType)) {
					rg.addText("Division : " + divName, 1, 1, 0);
				} else {
					cntFilter++;
				}
			}
			
			if (!"".equals(brnName)) {
				if (PFSubsReportModel.REPORT_TYPE_PDF.equals(reportType)) {
					rg.addText("Branch : " + brnName, 1, 1, 0);
				} else {
					cntFilter++;
				}
			}

		//	Object filterObj[][] = new Object[8][4];
/*
			filterObj[0][0] = "Period:";
			filterObj[0][1] = "" + Utility.month(Integer.parseInt(month)) + ""
					+ year + "";

			if (!bean.getDivName().equals("")) {
				filterObj[1][0] = "Division Name:";
				filterObj[1][1] = "" + String.valueOf(bean.getDivName()) + "";
			}
			if (!bean.getDeptName().equals("")) {
				filterObj[2][0] = "Department Name:";
				filterObj[2][1] = "" + String.valueOf(bean.getDeptName()) + "";
			}

			if (!bean.getBranchName().equals("")) {
				filterObj[3][0] = "Branch Name:";
				filterObj[3][1] = "" + String.valueOf(bean.getBranchName())
						+ "";
			}

			if (!bean.getEmpName().equals("")) {
				filterObj[4][0] = "Employee Name:";
				filterObj[4][1] = "" + String.valueOf(bean.getEmpName()) + "";
			}
			if (!bean.getCadreName().equals("")) {
				filterObj[5][0] = "Grade:";
				filterObj[5][1] = "" + String.valueOf(bean.getCadreName()) + "";
			}
			if (!bean.getPaybillName().equals("")) {
				filterObj[6][0] = "PayBill:";
				filterObj[6][1] = "" + String.valueOf(bean.getPaybillName())
						+ "";
			}
			if (!bean.getTypeName().equals("")) {
				filterObj[7][0] = "Employee type:";
				filterObj[7][1] = "" + String.valueOf(bean.getTypeName()) + "";
			}*/

			
	Object[][] filterObj = null;

			if (cntFilter > 0) {
				filterObj = new Object[cntFilter][2]; // Contains the filters data which will appear on the top table
				int fil = 0;

				if (!"".equals(divName)) {
					filterObj[fil][0] = " Division : ";
					filterObj[fil][1] = divName;
					fil++;
				}
				if (!"".equals(brnName)) {
					filterObj[fil][0] = "Branch : ";
					filterObj[fil][1] = brnName;
					fil++;
				}
				rg.tableBodyNoCellBorder(filterObj, new int[]{8, 8}, new int[]{2, 0}, 0);
			} // end of if statement
			rg.addText("\n", 0, 0, 0);
			
			
			/*TableDataSet signDataSet = new TableDataSet();
			signDataSet.setData(filterObj);
			signDataSet.setCellWidth(new int[] { 25, 25, 25, 25 });
			signDataSet.setCellAlignment(new int[] { 0, 0, 0, 0 });
			// signDataSet.setBlankRowsAbove(3);
			signDataSet.setBlankRowsBelow(1);
			signDataSet.setBorder(false);
			signDataSet.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
					new Color(0, 0, 0));

			rg.tableBody(filterObj,signDataSet, cellWidth, alignment);  

			java.util.Date d = new java.util.Date();
			logger.info("Date : - " + d);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			String strDate = sdf.format(d);

			Object[][] dateData = new Object[1][1];
			dateData[0][0] = "Date: - " + strDate;
			int[] cellWidthDateHeader = { 100 };
			int[] cellAlignDateHeader = { 2 };
			TableDataSet tableheadingDateData = new TableDataSet();
			tableheadingDateData.setData(dateData);
			tableheadingDateData.setCellWidth(cellWidthDateHeader);
			tableheadingDateData.setCellAlignment(cellAlignDateHeader);
			tableheadingDateData.setBodyFontDetails(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0));
			tableheadingDateData.setBorder(false);
			tableheadingDateData.setHeaderTable(true);
			tableheadingDateData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingDateData);
*/
			String whereClause = "";

			if (!bean.getBranchName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_CENTER ="
						+ bean.getBranchCode();
				System.out.println("+bean.getBranchCode() ="
						+ bean.getBranchCode());
			}
			/*
			 * if(!bean.getDivName().equals("")) { whereClause +=" AND
			 * HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivCode();
			 * System.out.println("+bean.getDivId() =" +bean.getDivCode()); }
			 */
			if (!bean.getDeptName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_DEPT ="
						+ bean.getDeptCode();
				System.out.println("+bean.getDeptId() =" + bean.getDeptCode());
			}
			if (!bean.getEmpName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_ID =" + bean.getEmpId();
				System.out.println("+bean.getEmpCode() =" + bean.getEmpId());
			}
			if (!bean.getCadreName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_CADRE ="
						+ bean.getCadreCode();
				System.out.println("+bean.getCadreCode() ="
						+ bean.getCadreCode());
			}
			if (!bean.getPaybillName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_PAYBILL ="
						+ bean.getPaybillId();
				System.out.println("+bean.getPaybillId() ="
						+ bean.getPaybillId());
			}
			if (!bean.getTypeName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_TYPE ="
						+ bean.getTypeCode();
				System.out
						.println("+bean.getTypeCode() =" + bean.getTypeCode());
			}

			whereClause += "  ORDER BY SAL_LEDGER_CODE DESC";

			// Current Month
			String currentmonthQuery = " SELECT LEDGER_CODE, LEDGER_MONTH, LEDGER_YEAR , "
					+ "LEDGER_DIVISION FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = "
					+ bean.getMonth()
					+ " AND LEDGER_YEAR = "
					+ bean.getYear()
					+ " AND LEDGER_DIVISION = " + bean.getDivCode();

			Object[][] expDetail = getSqlModel().getSingleResult(
					currentmonthQuery);

			String debitTypeQuery = "SELECT DEBIT_CODE, DEBIT_NAME, DEBIT_ABBR FROM HRMS_DEBIT_HEAD WHERE DEBIT_PAYFLAG ='Y' AND DEBIT_CODE IN (8,9) ";
			debitTypeQuery += " ORDER BY DEBIT_CODE ";
			// for credit type
			Object[][] debitDetail = getSqlModel().getSingleResult(
					debitTypeQuery);

			String[] header = null;
			int creditCount = 0;
			int debitCount = 0;

			if (debitDetail != null && debitDetail.length > 0) {
				debitCount = debitDetail.length;
			}
			header = new String[5 + debitCount];

			header[0] = "Sr. No.";
			header[1] = "Employee ID";
			header[2] = "Employee Name";
			header[3] = "Branch";
			header[4] = "Paybill";

			int count = 5;

			for (int j = 0; j < debitDetail.length; j++) {
				header[count] = String.valueOf(debitDetail[j][2]);
				count++;

			}

			int[] bcellAlign = new int[header.length];
			int[] bcellWidth = new int[header.length];
			for (int i = 0; i < header.length; i++) {
				if (i == 2) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 40;
				} else if (i == 3) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 40;
				} else if (i == 4) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 40;
				} else {
					bcellAlign[i] = 1;
					bcellWidth[i] = 40;
				}
			}

			if (expDetail != null && expDetail.length > 0) {
				// For Current Month ::
				String empTypeQuery = "SELECT HRMS_SALARY_"
						+ year
						+ ".EMP_ID ,SAL_LEDGER_CODE ,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,SAL_TOTAL_CREDIT ,SAL_TOTAL_DEBIT,SAL_NET_SALARY,EMP_CENTER, CENTER_NAME,EMP_DEPT,DEPT_NAME,EMP_PAYBILL,PAYBILL_GROUP  "
						+ "FROM HRMS_SALARY_"
						+ year
						+ " "
						+ "left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"
						+ year
						+ ".EMP_ID) "
						+ "left join HRMS_CENTER on(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+ "left join HRMS_DEPT on(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						+"left join HRMS_PAYBILL on(HRMS_PAYBILL.PAYBILL_ID = HRMS_EMP_OFFC.EMP_PAYBILL) "
						+ " WHERE SAL_LEDGER_CODE = " + expDetail[0][0];

				empTypeQuery += whereClause;

				// for emp type
				Object[][] empTypeDetail = getSqlModel().getSingleResult(
						empTypeQuery);

				Object[][] objTotalTabularData = new Object[empTypeDetail.length][header.length];

				Object[][] totalByColumn = new Object[1][header.length];

				int countSr = 1;

				if (empTypeDetail != null && empTypeDetail.length > 0) {

				
					for (int i = 0; i < objTotalTabularData.length; i++) {
						
						objTotalTabularData[i][0] = countSr++;
						objTotalTabularData[i][1] = String
								.valueOf(empTypeDetail[i][2]);
						objTotalTabularData[i][2] = String
								.valueOf(empTypeDetail[i][3]);
						objTotalTabularData[i][3] = checkNull(String
								.valueOf(empTypeDetail[i][8]));
						objTotalTabularData[i][4] = checkNull(String
								.valueOf(empTypeDetail[i][12]));

						int empCount = 5;

						// For Current Month :::
						String debitQuery = "SELECT EMP_ID, SAL_DEBIT_CODE, SAL_AMOUNT,SAL_LEDGER_CODE FROM HRMS_SAL_DEBITS_"
								+ year
								+ " "
								+ "WHERE SAL_LEDGER_CODE="
								+ empTypeDetail[0][1]
								+ " AND EMP_ID="
								+ empTypeDetail[i][0]
								+ " AND SAL_DEBIT_CODE IN(8,9) ORDER BY EMP_ID, SAL_DEBIT_CODE ";

						// for credit emp type
						Object[][] debitEmpDetail = getSqlModel()
								.getSingleResult(debitQuery);

						double valueTotal = 0.0;
						for (int j = 0; j < debitDetail.length; j++) {

							if (j < debitEmpDetail.length) {
							
								double currenMonthValue = Double
										.parseDouble(String
												.valueOf(debitEmpDetail[j][2]));

								objTotalTabularData[i][empCount] = currenMonthValue;

								valueTotal = Double
										.parseDouble(checkNullValue(String
												.valueOf(totalByColumn[0][empCount])));
								totalByColumn[0][empCount] = currenMonthValue
										+ valueTotal;
						
							} else {
								objTotalTabularData[i][empCount] = "0";
							}
							empCount++;

						}

						empCount++;
				
					}

					/*TableDataSet tdstable = new TableDataSet();
					tdstable.setHeader(header);
					tdstable.setData(objTotalTabularData);
					tdstable.setCellAlignment(bcellAlign);
					tdstable.setCellWidth(bcellWidth);
					tdstable.setBorder(true);
					tdstable.setHeaderTable(true);
					tdstable.setHeaderBGColor(new Color(225, 225, 225));
					// tdstable.setBlankRowsBelow(1);
					rg.addTableToDoc(tdstable);*/
				
						
						if ("Xls".equals(reportType)) {
							rg.xlsTableBody(header, objTotalTabularData,bcellWidth,bcellAlign);
						} else if (PFSubsReportModel.REPORT_TYPE_PDF.equals(reportType)) {
							rg.tableBody(header, objTotalTabularData,bcellWidth,bcellAlign);
						}
				
					//	 rg.tableBodyNoBorder(objTotalTabularData,bcellWidth,bcellAlign);
					 
					
					

				}

				if (empTypeDetail != null && empTypeDetail.length > 0) {
					
					totalByColumn[0][0] = "";
					totalByColumn[0][1] = "";
					totalByColumn[0][2] = "Total ";
					
				}

				if (empTypeDetail != null && empTypeDetail.length > 0) {

					/*TableDataSet tdstableTotal = new TableDataSet();
					// tdstableTotal.setHeader(columns);
					tdstableTotal.setData(totalByColumn);
					// /tdstableTotal.setColumnSum(new int[] { 4, 5, 6, 7, 8, 9,
					// 10, 11,
					// / 12, 13, 14, 15, 16, 17 });
					tdstableTotal.setCellAlignment(bcellAlign);
					tdstableTotal.setCellWidth(bcellWidth);
					tdstableTotal.setBorder(true);
					tdstableTotal.setHeaderFontStyle(-1);
					tdstableTotal.setBodyFontDetails(Font.HELVETICA, 8,
							Font.NORMAL, new Color(0, 0, 0));
					tdstableTotal.setHeaderBGColor(new Color(225, 225, 225));
					rg.addTableToDoc(tdstableTotal);*/
					
					
					//	 rg.tableBodyNoBorder(totalByColumn,bcellWidth,bcellAlign);
					if ("Xls".equals(reportType)) {
						rg.tableBody(totalByColumn,bcellWidth,bcellAlign);
					} else if (PFSubsReportModel.REPORT_TYPE_PDF.equals(reportType)) {
						rg.tableBody( totalByColumn,bcellWidth,bcellAlign);
					} 
				}

				String footerQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_RANK,RANK_NAME "
						+ " FROM HRMS_EMP_OFFC "
						+ "INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
						+ "WHERE EMP_ID=" + bean.getUserEmpId();

				// for credit emp type
				Object[][] footerDetail = getSqlModel().getSingleResult(
						footerQuery);

				Object footerObj[][] = new Object[2][2];

				footerObj[0][0] = "Total No of Employees:";
				footerObj[0][1] = objTotalTabularData.length;

				footerObj[1][0] = "Generated By:";
				footerObj[1][1] = "" + String.valueOf(footerDetail[0][3])
						+ " on "
						+ DateFormat.getDateTimeInstance().format(date);

			/*	TableDataSet footerDataSet = new TableDataSet();
				footerDataSet.setData(footerObj);
				footerDataSet.setCellWidth(new int[] { 25, 75 });
				footerDataSet.setCellAlignment(new int[] { 0, 0 });
				signDataSet.setBlankRowsAbove(1);
				footerDataSet.setBlankRowsBelow(1);
				footerDataSet.setBorder(false);
				footerDataSet.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
						new Color(0, 0, 0));

				rg.addTableToDoc(footerDataSet);*/
				
			
				//	 rg.tableBodyNoBorder(footerObj,bcellWidth,bcellAlign);
				  
				if ("Xls".equals(reportType)) {
					rg.tableBody( footerObj,new int[] {  15, 75  },new int[] { 0, 0 });
					//rg.tableBodyNoBorder(footerObj,new int[] {  15, 75  },new int[] { 0, 0 });
				} else if (PFSubsReportModel.REPORT_TYPE_PDF.equals(reportType)) {
					rg.tableBodyNoBorder(footerObj,new int[] {  15, 75  },new int[] { 0, 0 });
					//rg.tableBody( footerObj,new int[] {  15, 75  },new int[] { 0, 0 });
				} 

			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";

			/*	noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);*/
				
				
			//		 rg.tableBodyNoBorder(noDataObj,bcellWidth,bcellAlign);
				if ("Xls".equals(reportType)) {
					rg.tableBody(noDataObj,new int[] { 100 },new int[] { 1 });
				} else if (PFSubsReportModel.REPORT_TYPE_PDF.equals(reportType)) {
					rg.tableBody( noDataObj,new int[] { 100 },new int[] { 1 });
				}   
			}

			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
