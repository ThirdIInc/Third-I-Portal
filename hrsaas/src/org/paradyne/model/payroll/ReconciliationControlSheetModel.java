package org.paradyne.model.payroll;

import java.awt.Color;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.ReconciliationControlSheetBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;

import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;

/**
 * @author aa1385 Date:01-12-2011
 */
public class ReconciliationControlSheetModel extends ModelBase {
	NumberFormat formatter = new DecimalFormat("#0.00");
	/**
	 * report type Pdf.
	 */
	public static final String REPORT_TYPE_PDF = "Pdf";
	/**
	 * Log4j logger.
	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ReconciliationControlSheetModel.class);

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
			return "0.00";
		} else {
			return result;
		}
	}
	public String checkNullValueJoin(final String result) {
		if (result == null || "null".equals(result)) {
			return "0";
		} else {
			return result;
		}
	}
	public void generateReport(ReconciliationControlSheetBean bean,
			HttpServletResponse response, HttpServletRequest request,
			String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();

			String type = bean.getReport();
			System.out.println("type===" + type);
			rds.setReportType(type);
			String fileName = "Salary Reconciliation Control Sheet " + Utility.getRandomNumber(1000);
			String reportPathName = reportPath + fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("Salary Reconciliation Control Sheet");
			rds.setPageOrientation("landscape");
			rds.setPageSize("A4");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setShowPageNo(true);
			rds.setTotalColumns(13);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;

			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				logger.info("################# ATTACHMENT PATH #############"
						+ reportPath + fileName + "." + type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ type);
				request.setAttribute("fileName",  fileName + "."
						+ type);
				request.setAttribute("action",
						"ReconciliationControlSheet_input.action");
			}

			rg = getReport(rg, bean);
			rg.process();
			if (reportPath.equals("")) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment */
				rg.saveReport(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*private org.paradyne.lib.ireportV2.ReportGenerator getReport_old(
			org.paradyne.lib.ireportV2.ReportGenerator rg,
			ReconciliationControlSheetBean bean) {
		try {
			Date date = new Date();
			final String month = bean.getMonth(); // month
			String prevMonth = "" + (Integer.parseInt(month) - 1);
			System.out.println("prevMonth==" + prevMonth);
			final String year = bean.getYear(); // year
			System.out.println("year==" + year);

			String title = " Salary Reconciliation Control Sheet  ";


			 Setting filter details 
			String filters = "Period : "
					+ Utility.month(Integer.parseInt(month)) + "" + year + "";

			if (!bean.getDivName().equals("")) {
				filters += "\nDivision : " + bean.getDivName();
			}

			if (!bean.getBranchName().equals("")) {
				filters += "\nBranch : " + bean.getBranchName();
			}

			if (!bean.getDeptName().equals("")) {
				filters += "\nDepartment : " + bean.getDeptName();
			}

			if (!bean.getPaybillName().equals("")) {
				filters += "\nPaybill : " + bean.getPaybillName(); // Paybill
			}

			if (!bean.getTypeName().equals("")) {
				filters += "\nEmployee Type : " + bean.getTypeName(); // Employee
				// Type
			}
			if (!bean.getEmpName().equals("")) {
				filters += "\nEmployee Name : " + bean.getEmpName(); // Employee
				// Name
			}
			if (!bean.getCadreName().equals("")) {
				filters += "\nGrade : " + bean.getCadreName(); // Grade
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6,
					Font.BOLD, new BaseColor(0, 0, 0));
			filterData.setBorder(false);
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);

			// /////////////////

			java.util.Date d = new java.util.Date();
			logger.info("Date :- " + d);
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
			tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA,
					6, Font.NORMAL, new BaseColor(0, 0, 0));
			tableheadingDateData.setBorder(false);
			tableheadingDateData.setHeaderTable(true);
			// tableheadingDateData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingDateData);

			// //////////////

			String whereClause = "";

			if (!bean.getBranchName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year + ".SAL_EMP_CENTER IN ("
						+ bean.getBranchCode()+") ";
				System.out.println("+bean.getBranchCode() ="
						+ bean.getBranchCode());
			}
			if (!bean.getDivName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year + ".SAL_DIVISION IN ( "
						+ bean.getDivCode()+") ";
				System.out.println("+bean.getDivId() =" + bean.getDivCode());
			}
			if (!bean.getDeptName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year + ".SAL_DEPT IN ( "
						+ bean.getDeptCode()+") ";
				System.out.println("+bean.getDeptId() =" + bean.getDeptCode());
			}

			 
			if (!bean.getCadreName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year
						+ ".SAL_EMP_GRADE IN (" + bean.getCadreCode()+") ";
				System.out.println("+bean.getCadreCode() ="
						+ bean.getCadreCode());
			}
			if (!bean.getPaybillName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year
						+ ".SAL_EMP_PAYBILL IN (" + bean.getPaybillId()+") ";
				System.out.println("+bean.getPaybillId() ="
						+ bean.getPaybillId());
			}
			
			if (!bean.getCostCenterName().equals("")) {
				whereClause += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
						+ bean.getCostCenterId()+")";
				System.out
						.println("+bean.getCostCenterId() =" + bean.getCostCenterId());
			}
			

			// whereClause += " ORDER BY SAL_LEDGER_CODE DESC";

			String bandQueery = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) "
					+ "FROM  HRMS_CADRE   WHERE CADRE_IS_ACTIVE = 'Y' ";
			if (!"".equals(bean.getCadreCode())) {
				bandQueery += " AND CADRE_ID=" + bean.getCadreCode();
			}
			bandQueery += " ORDER BY CADRE_ID ";

			Object[][] bandDetail = getSqlModel().getSingleResult(bandQueery);

			TableDataSet mainTitle = new TableDataSet();

			mainTitle.setData(new Object[][] { { "Company Details",
					"Employee Details", "CTC", "Net" } });
			// tableData20.setHeaderFontDetails(Font.FontFamily.HELVETICA, 5,
			// Font.BOLD, new BaseColor(0, 0, 0));

			mainTitle.setCellAlignment(new int[] { 1, 1, 1, 1 });
			mainTitle.setCellWidth(new int[] { 8, 32, 40, 24 });
			mainTitle.setBorderDetail(3);
			mainTitle.setHeaderTable(true);
		 rg.addTableToDoc(mainTitle);
		 

			String[] header = null;
			Object[][] objTotalTabularData = null;
			int countSr = 1;
			objTotalTabularData = new Object[bandDetail.length][13];
			
			Object[][] prevObjTotalTabularData = new Object[bandDetail.length][14];

			Object[][] totalByColumn = new Object[2][13];
			
			Object[][] increaseDecreaseColumn = new Object[2][13];

			if (bandDetail != null && bandDetail.length > 0) {

				int MAINCOUNT = 0;
				
				for (int i = 0; i < bandDetail.length; i++) {

					String empDetails = "SELECT EMP_CADRE, EMP_REGULAR_DATE, EMP_LEAVE_DATE  FROM HRMS_EMP_OFFC "
							+ " WHERE TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY') = '"
							+ month
							+ "-"
							+ year
							+ "' AND EMP_CADRE = "
							+ bandDetail[i][0];
					empDetails += whereClause;
					Object[][] empDetailObj = getSqlModel().getSingleResult(
							empDetails);

					String empResignDetails = "SELECT EMP_CADRE, EMP_REGULAR_DATE, EMP_LEAVE_DATE  FROM HRMS_EMP_OFFC "
							+ " WHERE TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY') = '"
							+ month
							+ "-"
							+ year
							+ "' AND EMP_CADRE = "
							+ bandDetail[i][0];
					empResignDetails += whereClause;
					Object[][] empResignDetailObj = getSqlModel()
							.getSingleResult(empResignDetails);
					
					
					//For previous month
					
					String prevEmpDetails = "SELECT EMP_CADRE, EMP_REGULAR_DATE, EMP_LEAVE_DATE  FROM HRMS_EMP_OFFC "
						+ " WHERE TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY') = '"
						+ prevMonth
						+ "-"
						+ year
						+ "' AND EMP_CADRE = "
						+ bandDetail[i][0];
					prevEmpDetails += whereClause;
				Object[][] prevEmpDetailObj = getSqlModel().getSingleResult(
						prevEmpDetails);
				
					
				String prevEmpResignDetails = "SELECT EMP_CADRE, EMP_REGULAR_DATE, EMP_LEAVE_DATE  FROM HRMS_EMP_OFFC "
					+ " WHERE TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY') = '"
					+ month
					+ "-"
					+ year
					+ "' AND EMP_CADRE = "
					+ bandDetail[i][0];
				prevEmpResignDetails += whereClause;
			Object[][] prevEmpResignDetailObj = getSqlModel()
					.getSingleResult(prevEmpResignDetails);	
					
					

					// objTotalTabularData[MAINCOUNT][0] = countSr++;
					objTotalTabularData[MAINCOUNT][0] = String
							.valueOf(bandDetail[i][1]);// CADRE NAME

					objTotalTabularData[MAINCOUNT][1] = empDetailObj.length;// TOTAL
					// No
					// of
					// Staff
					// at
					// Begining
					objTotalTabularData[MAINCOUNT][2] = "0";
					objTotalTabularData[MAINCOUNT][3] = "0";

					if (empDetailObj != null && empDetailObj.length > 0) {
						objTotalTabularData[MAINCOUNT][2] = empDetailObj.length; // Total
																					// No
																					// of
																					// Join

						// count++;

						
					}
					if (prevEmpDetailObj != null && prevEmpDetailObj.length > 0) {
						
						prevObjTotalTabularData[MAINCOUNT][2] = prevEmpDetailObj.length; 
						
					}
					if (empResignDetailObj != null
							&& empResignDetailObj.length > 0) {
						objTotalTabularData[MAINCOUNT][3] = empResignDetailObj.length; // Total
																						// No
																						// of
																						// Resign

						// count++;

					}
					
					if (prevEmpResignDetailObj != null
							&& prevEmpResignDetailObj.length > 0) {
						prevObjTotalTabularData[MAINCOUNT][3] = prevEmpResignDetailObj.length; // Total
																						// No
																						// of
																						// Resign

						// count++;

					}
					
					
					
					
					double noOfStaffAtBeginValue = Double
							.parseDouble(checkNullValue(String
									.valueOf(objTotalTabularData[MAINCOUNT][1])));
					double joinResignValue = Double
							.parseDouble(checkNullValue(String
									.valueOf(objTotalTabularData[MAINCOUNT][2])))
							- Double
									.parseDouble(checkNullValue(String
											.valueOf(objTotalTabularData[MAINCOUNT][3])));
					objTotalTabularData[MAINCOUNT][4] = noOfStaffAtBeginValue
							+ joinResignValue; // Total Staff

					
					double prevNoOfStaffAtBeginValue = Double
					.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[MAINCOUNT][1])));
					
					
					double prevJoinResignValue = Double
					.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[MAINCOUNT][2])))
					- Double
							.parseDouble(checkNullValue(String
									.valueOf(prevObjTotalTabularData[MAINCOUNT][3])));
					
					prevObjTotalTabularData[MAINCOUNT][4] = prevNoOfStaffAtBeginValue
					+ prevJoinResignValue; // Total Previous Staff
					
					//current Month
					String empCTCDetails = "SELECT NVL(SUM(SAL_TOTAL_CREDIT),0),NVL(SUM(SAL_TOTAL_DEBIT),0), NVL(SUM(SAL_NET_SALARY),0) "

							+ " FROM hrms_salary_"
							+ year
							+ "  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = hrms_salary_"
							+ year
							+ ".EMP_ID) "
							+ " WHERE SAL_MONTH = "
							+ month
							+ " AND  SAL_YEAR = "
							+ year
							+ " AND HRMS_EMP_OFFC.EMP_CADRE = "
							+ bandDetail[i][0];
					empCTCDetails += whereClause;
					Object[][] empCTCDetailObj = getSqlModel().getSingleResult(
							empCTCDetails);

					//Previous month
					
					String prevEmpCTCDetails = "SELECT NVL(SUM(SAL_TOTAL_CREDIT),0),NVL(SUM(SAL_TOTAL_DEBIT),0), NVL(SUM(SAL_NET_SALARY),0) "

						+ " FROM hrms_salary_"
						+ year
						+ "  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = hrms_salary_"
						+ year
						+ ".EMP_ID) "
						+ " WHERE SAL_MONTH = "
						+ prevMonth
						+ " AND  SAL_YEAR = "
						+ year
						+ " AND HRMS_EMP_OFFC.EMP_CADRE = "
						+ bandDetail[i][0];
					prevEmpCTCDetails += whereClause;
				Object[][] prevEmpCTCDetailObj = getSqlModel().getSingleResult(
						prevEmpCTCDetails);
					
					
					// objTotalTabularData[MAINCOUNT][5] = "0";
					if (empCTCDetailObj != null && empCTCDetailObj.length > 0) {
						objTotalTabularData[MAINCOUNT][5] = checkNullValue(String
								.valueOf(empCTCDetailObj[0][0])); // Salary

						// ctcCount++;

					}
					//previous month salary 
					
					if (prevEmpCTCDetailObj != null && prevEmpCTCDetailObj.length > 0) {
						prevObjTotalTabularData[MAINCOUNT][5] = checkNullValue(String
								.valueOf(prevEmpCTCDetailObj[0][0])); // prev Salary

						// ctcCount++;

					}

					if (empCTCDetailObj != null && empCTCDetailObj.length > 0) {

						objTotalTabularData[MAINCOUNT][10] = empCTCDetailObj[0][1]; // Deduction

						// deductionCount++;

					}
					//prev month deduction
					if (prevEmpCTCDetailObj != null && prevEmpCTCDetailObj.length > 0) {
						prevObjTotalTabularData[MAINCOUNT][10] = checkNullValue(String
								.valueOf(prevEmpCTCDetailObj[0][1])); // prev Deduction

						// ctcCount++;

					}

					if (empCTCDetailObj != null && empCTCDetailObj.length > 0) {

						objTotalTabularData[MAINCOUNT][11] = checkNullValue(String
								.valueOf(empCTCDetailObj[0][2])); // Net
																	// Salary

						//netCount++;
					}

					
					//prev month net salary
					if (prevEmpCTCDetailObj != null && prevEmpCTCDetailObj.length > 0) {
						prevObjTotalTabularData[MAINCOUNT][11] = checkNullValue(String
								.valueOf(prevEmpCTCDetailObj[0][2])); // prev Deduction

						// ctcCount++;

					}
					
					//current month arreras
					String empArrDetails = "SELECT ARREARS_CREDITS_AMT, HRMS_ARREARS_"
							+ year
							+ ".EMP_ID "
							+ "FROM HRMS_ARREARS_"
							+ year
							+ "  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_"
							+ year
							+ ".EMP_ID) "
							+ " WHERE ARREARS_MONTH = "
							+ month
							+ " AND  ARREARS_YEAR = "
							+ year
							+ " AND HRMS_EMP_OFFC.EMP_CADRE = "
							+ bandDetail[i][0];
					empArrDetails += whereClause;
					Object[][] empArrDetailObj = getSqlModel().getSingleResult(
							empArrDetails);

					
					//previous month arreras
					String prevEmpArrDetails = "SELECT ARREARS_CREDITS_AMT, HRMS_ARREARS_"
							+ year
							+ ".EMP_ID "
							+ "FROM HRMS_ARREARS_"
							+ year
							+ "  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_"
							+ year
							+ ".EMP_ID) "
							+ " WHERE ARREARS_MONTH = "
							+ prevMonth
							+ " AND  ARREARS_YEAR = "
							+ year
							+ " AND HRMS_EMP_OFFC.EMP_CADRE = "
							+ bandDetail[i][0];
					prevEmpArrDetails += whereClause;
					Object[][] prevEmpArrDetailObj = getSqlModel().getSingleResult(
							prevEmpArrDetails);
					
					objTotalTabularData[MAINCOUNT][6] = "0";
					if (empArrDetailObj != null && empArrDetailObj.length > 0) {
						objTotalTabularData[MAINCOUNT][6] = empArrDetailObj[0][0]; // Arrears

						// arrCount++;

					}
					//previous month arrerars
					if (prevEmpArrDetailObj != null && prevEmpArrDetailObj.length > 0) {
						prevObjTotalTabularData[MAINCOUNT][6] = prevEmpArrDetailObj[0][0]; // Arrears

						// arrCount++;

					}
					

					MAINCOUNT++;
				}
				for (int i = 0; i < objTotalTabularData.length; i++) {

					// totalByColumn[0][K] = "0";

					totalByColumn[0][1] = Double
							.parseDouble(checkNullValue(String
									.valueOf(totalByColumn[0][1])))
							+ Double.parseDouble(checkNullValue(String
									.valueOf(objTotalTabularData[i][1])));

					totalByColumn[0][2] = Double
							.parseDouble(checkNullValue(String
									.valueOf(totalByColumn[0][2])))
							+ Double.parseDouble(checkNullValue(String
									.valueOf(objTotalTabularData[i][2])));

					totalByColumn[0][3] = Double
							.parseDouble(checkNullValue(String
									.valueOf(totalByColumn[0][3])))
							+ Double.parseDouble(checkNullValue(String
									.valueOf(objTotalTabularData[i][3])));

					totalByColumn[0][4] = Double
							.parseDouble(checkNullValue(String
									.valueOf(totalByColumn[0][4])))
							+ Double.parseDouble(checkNullValue(String
									.valueOf(objTotalTabularData[i][4])));
					
					
					totalByColumn[0][5] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][5])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(objTotalTabularData[i][5])));
					
					
					totalByColumn[0][6] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][6])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(objTotalTabularData[i][6])));
					
					
					totalByColumn[0][7] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][7])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(objTotalTabularData[i][7])));
					
					totalByColumn[0][8] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][8])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(objTotalTabularData[i][8])));
					
					totalByColumn[0][9] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][9])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(objTotalTabularData[i][9])));
					
					totalByColumn[0][10] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][10])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(objTotalTabularData[i][10])));
					
					totalByColumn[0][11] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][11])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(objTotalTabularData[i][11])));
					
					totalByColumn[0][12] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][12])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(objTotalTabularData[i][12])));
					
					

				}
				
				for (int i = 0; i < prevObjTotalTabularData.length; i++) {

					// totalByColumn[0][K] = "0";

					totalByColumn[1][1] = Double
							.parseDouble(checkNullValue(String
									.valueOf(totalByColumn[1][1])))
							+ Double.parseDouble(checkNullValue(String
									.valueOf(prevObjTotalTabularData[i][2])));
					
					totalByColumn[1][2] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][2])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[i][3])));
					
					
					totalByColumn[1][3] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][3])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[i][4])));
					
					totalByColumn[1][4] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][4])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[i][5])));
					
					totalByColumn[1][5] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][5])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[i][6])));
					
					totalByColumn[1][6] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][6])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[i][7])));
					
					totalByColumn[1][7] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][7])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[i][8])));
					
					totalByColumn[1][8] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][8])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[i][9])));
					
					totalByColumn[1][9] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][9])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[i][10])));
					
					totalByColumn[1][10] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][10])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[i][11])));
					
					totalByColumn[1][11] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][11])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[i][12])));
					
					
					totalByColumn[1][12] = Double
					.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][12])))
					+ Double.parseDouble(checkNullValue(String
							.valueOf(prevObjTotalTabularData[i][13])));
					
					
					
					
					//increase/Decrease
					
					increaseDecreaseColumn[0][1] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][1])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][1]))));
					
					
					increaseDecreaseColumn[0][2] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][2])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][2]))));
					
					
					increaseDecreaseColumn[0][3] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][3])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][3]))));
					
					increaseDecreaseColumn[0][4] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][4])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][4]))));
					
					
					increaseDecreaseColumn[0][5] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][5])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][5]))));
					
					increaseDecreaseColumn[0][6] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][6])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][6]))));
					
					increaseDecreaseColumn[0][7] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][7])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][7]))));
					
					
					increaseDecreaseColumn[0][8] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][8])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][8]))));
					
					
					increaseDecreaseColumn[0][9] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][9])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][9]))));
					
					increaseDecreaseColumn[0][10] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][10])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][10]))));
					
					increaseDecreaseColumn[0][11] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][11])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][11]))));
					
					increaseDecreaseColumn[0][12] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[0][12])))-Double.parseDouble(checkNullValue(String
							.valueOf(totalByColumn[1][12]))));
					
					
					//increase/Decrease %
					increaseDecreaseColumn[1][1] = "";
					
					increaseDecreaseColumn[1][2] ="";
					
					
					increaseDecreaseColumn[1][3] ="";
					
					increaseDecreaseColumn[1][4] = Utility.twoDecimals(Double.parseDouble(checkNullValue(String.valueOf(increaseDecreaseColumn[0][4])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][4]))));
					System.out.println("increaseDecreaseColumn[1][4]==="+increaseDecreaseColumn[1][4]);
					
					increaseDecreaseColumn[1][5] = Utility.twoDecimals(Double.parseDouble(checkNullValue(String.valueOf(increaseDecreaseColumn[0][5])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][5]))));
					
					increaseDecreaseColumn[1][6] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(increaseDecreaseColumn[0][6]))) /100);
					
					increaseDecreaseColumn[1][7] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(increaseDecreaseColumn[0][7]))) /100);
					
					increaseDecreaseColumn[1][8] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(increaseDecreaseColumn[0][8]))) /100);
					
					increaseDecreaseColumn[1][9] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(increaseDecreaseColumn[0][9]))) /100);
					
					increaseDecreaseColumn[1][10] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(increaseDecreaseColumn[0][10]))) /100);
					
					increaseDecreaseColumn[1][11] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(increaseDecreaseColumn[0][11]))) /100);
					
					increaseDecreaseColumn[1][12] = Utility
					.twoDecimals(Double.parseDouble(checkNullValue(String
							.valueOf(increaseDecreaseColumn[0][12]))) /100);
					
				}
				
				
				
				
				
			}
			TableDataSet subMainTitle = new TableDataSet();
			subMainTitle.setHeader(new String[] { "Name Of Company",
					"No of Staff at the Beginning of month", "Joining",
					"Resignation", "Total Staff", "Salary", "Arrears", "Bonus",
					"Incentive", "Total Earnings", "Deduction", "Net Payment",
					"Avg CTC as per Employee" });
			subMainTitle.setHeaderFontDetails(Font.FontFamily.HELVETICA, 6,
					Font.NORMAL, new BaseColor(0, 0, 0));
			subMainTitle.setHeaderBorderDetail(3);
			subMainTitle.setHeaderTable(true);
			subMainTitle.setData(objTotalTabularData);
			subMainTitle.setCellAlignment(new int[] { 0, 1, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 1 });
			subMainTitle.setCellWidth(new int[] { 30, 30, 30, 30, 30, 30, 30,
					30, 30, 30, 30, 30, 30 });
			
			subMainTitle.setBorderDetail(3);
			subMainTitle.setBorder(true);

			HashMap<String, Object> map = rg.joinTableDataSet(mainTitle,
					subMainTitle, false, 20);

			rg.addTableToDoc(subMainTitle);
			Vector lineVectorSubTitle = new Vector();
			lineVectorSubTitle.add(new BaseColor(0, 0, 0));
			lineVectorSubTitle.add(Rectangle.TOP);
			rg.addLine(lineVectorSubTitle);

			totalByColumn[0][0] = "Current Month Total  ";
			totalByColumn[1][0] = "Previous Month Total  ";

			TableDataSet netEarningDataSet = new TableDataSet();
			netEarningDataSet.setData(totalByColumn);
			// netEarningDataSet.setRowSum(new int[] { 2, 3, 4, 5, 6, 7, 8, 9,
			// 10, 11, 12, 13, 14 });
			netEarningDataSet.setCellAlignment(new int[] { 0, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 1, 1 });
			netEarningDataSet.setCellWidth(new int[] { 30, 30, 30, 30, 30, 30,
					30, 30, 30, 30, 30, 30, 30 });
			netEarningDataSet.setBlankRowsAbove(1);
			// netEarningDataSet.setBlankRowsBelow(1);
			netEarningDataSet.setBorderDetail(3);
			
			rg.addTableToDoc(netEarningDataSet);

			
			
			increaseDecreaseColumn[0][0] = "Increase / Decrease  ";
			increaseDecreaseColumn[1][0] = "Increase / Decrease   in %  ";

			TableDataSet increaseDecreaseDataSet = new TableDataSet();
			increaseDecreaseDataSet.setData(increaseDecreaseColumn);
			// netEarningDataSet.setRowSum(new int[] { 2, 3, 4, 5, 6, 7, 8, 9,
			// 10, 11, 12, 13, 14 });
			increaseDecreaseDataSet.setCellAlignment(new int[] { 0, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 1, 1 });
			increaseDecreaseDataSet.setCellWidth(new int[] { 30, 30, 30, 30, 30, 30,
					30, 30, 30, 30, 30, 30, 30 });
			increaseDecreaseDataSet.setBlankRowsAbove(1);
			// netEarningDataSet.setBlankRowsBelow(1);
			increaseDecreaseDataSet.setBorderDetail(3);
			
			rg.addTableToDoc(increaseDecreaseDataSet);
			
			
			String generatedByQuery = " SELECT TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) FROM HRMS_EMP_OFFC "
					// + " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =
					// HRMS_EMP_OFFC.EMP_RANK)"
					+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + bean.getUserEmpId();

			Object[][] empDataObj = getSqlModel().getSingleResult(
					generatedByQuery);

			if (empDataObj != null && empDataObj.length > 0) {
				TableDataSet generatedByData = new TableDataSet();
				generatedByData.setData(new Object[][] { { "Generated By : "
						+ String.valueOf(empDataObj[0][0]) + " on "
						+ date.toString() } });
				generatedByData.setCellAlignment(new int[] { 0 });
				generatedByData.setCellWidth(new int[] { 100 });
				
				generatedByData.setBorder(false);
				// generatedByData.setBorderDetail(1);
				generatedByData.setHeaderTable(true);
				generatedByData.setBlankRowsAbove(1);
				rg.addTableToDoc(generatedByData);
				Vector lineVector = new Vector();
				lineVector.add(new BaseColor(255, 0, 0));
				lineVector.add(Rectangle.TOP);
				rg.addLine(lineVector);
			}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rg;
	}*/
	
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(
			org.paradyne.lib.ireportV2.ReportGenerator rg,
			ReconciliationControlSheetBean bean) {
		try {
			Date date = new Date();
			final String month = bean.getMonth(); // month
			
			final String year = bean.getYear(); // year
			final String prevMonth = bean.getPrevMonth(); // previous month
			
			final String prevYear = bean.getPrevYear(); // previous year
			String title = " Salary Reconciliation Control Sheet  ";


			/* Setting filter details */
			String filters = "Period : "
					+ Utility.month(Integer.parseInt(month)) + " " + year + "";

			if (!bean.getDivName().equals("")) {
				filters += "\n\nDivision : " + bean.getDivName();
			}

			if (!bean.getBranchName().equals("")) {
				filters += "\n\nBranch : " + bean.getBranchName();
			}

			if (!bean.getDeptName().equals("")) {
				filters += "\n\nDepartment : " + bean.getDeptName();
			}

			if (!bean.getPaybillName().equals("")) {
				filters += "\n\nPaybill : " + bean.getPaybillName(); // Paybill
			}

			if (!bean.getTypeName().equals("")) {
				filters += "\n\nEmployee Type : " + bean.getTypeName(); // Employee
				// Type
			}
			if (!bean.getEmpName().equals("")) {
				filters += "\n\nEmployee Name : " + bean.getEmpName(); // Employee
				// Name
			}
			if (!bean.getCadreName().equals("")) {
				filters += "\n\nGrade : " + bean.getCadreName(); // Grade
			}
			
			if (!bean.getCostCenterName().equals("")) {
				filters += "\n\nCost Center : " + bean.getCostCenterName(); // Grade
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			//filterData.setBodyFontStyle(1);
			filterData.setCellColSpan(new int[]{13});
			//filterData.setBorderDetail(0);
			//filterData.setBlankRowsBelow(1);
			filterData.setBorder(false);
			rg.addTableToDoc(filterData);
		

			String whereClause = "";

			if (!bean.getBranchName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year + ".SAL_EMP_CENTER IN ("
						+ bean.getBranchCode()+") ";
				System.out.println("+bean.getBranchCode() ="
						+ bean.getBranchCode());
			}
			if (!bean.getDivName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year + ".SAL_DIVISION IN ( "
						+ bean.getDivCode()+") ";
				System.out.println("+bean.getDivId() =" + bean.getDivCode());
			}
			if (!bean.getDeptName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year + ".SAL_DEPT IN ( "
						+ bean.getDeptCode()+") ";
				System.out.println("+bean.getDeptId() =" + bean.getDeptCode());
			}

			 
			if (!bean.getCadreName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year
						+ ".SAL_EMP_GRADE IN (" + bean.getCadreCode()+") ";
				System.out.println("+bean.getCadreCode() ="
						+ bean.getCadreCode());
			}
			if (!bean.getPaybillName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year
						+ ".SAL_EMP_PAYBILL IN (" + bean.getPaybillId()+") ";
				System.out.println("+bean.getPaybillId() ="
						+ bean.getPaybillId());
			}
			
			if (!bean.getCostCenterName().equals("")) {
				whereClause += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
						+ bean.getCostCenterId()+")";
				System.out
						.println("+bean.getCostCenterId() =" + bean.getCostCenterId());
			}
			

			// whereClause += " ORDER BY SAL_LEDGER_CODE DESC";

		 //filters for arrears start
			
			String whereArrearsClause = "";

			if (!bean.getBranchName().equals("")) {
				whereArrearsClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
						+ bean.getBranchCode()+") ";
				System.out.println("+bean.getBranchCode() ="
						+ bean.getBranchCode());
			}
			
			  if(!bean.getDivName().equals("")) { 
				  whereArrearsClause +=" AND HRMS_EMP_OFFC.EMP_DIV In ("+bean.getDivCode()+") ";
			 System.out.println("+bean.getDivId() =" +bean.getDivCode()); }
			
			if (!bean.getDeptName().equals("")) {
				whereArrearsClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
						+ bean.getDeptCode()+") ";
				System.out.println("+bean.getDeptId() =" + bean.getDeptCode());
			}
			
			if (!bean.getCadreName().equals("")) {
				whereArrearsClause += " AND HRMS_EMP_OFFC.EMP_CADRE IN ("
						+ bean.getCadreCode()+") ";
				System.out.println("+bean.getCadreCode() ="
						+ bean.getCadreCode());
			}
			if (!bean.getPaybillName().equals("")) {
				whereArrearsClause += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
						+ bean.getPaybillId()+") ";
				System.out.println("+bean.getPaybillId() ="
						+ bean.getPaybillId());
			}
			

			if (!bean.getCostCenterName().equals("")) {
				whereArrearsClause += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
						+ bean.getCostCenterId()+")";
				System.out
						.println("+bean.getCostCenterId() =" + bean.getCostCenterId());
			}
			
			// filters for arrears end
			
			
			String prevWhereClause = "";

			if (!bean.getBranchName().equals("")) {
				prevWhereClause += " AND HRMS_SALARY_" + prevYear + ".SAL_EMP_CENTER IN ("
						+ bean.getBranchCode()+") ";
				System.out.println("+bean.getBranchCode() ="
						+ bean.getBranchCode());
			}
			if (!bean.getDivName().equals("")) {
				prevWhereClause += " AND HRMS_SALARY_" + prevYear + ".SAL_DIVISION IN ( "
						+ bean.getDivCode()+") ";
				System.out.println("+bean.getDivId() =" + bean.getDivCode());
			}
			if (!bean.getDeptName().equals("")) {
				prevWhereClause += " AND HRMS_SALARY_" + prevYear + ".SAL_DEPT IN ( "
						+ bean.getDeptCode()+") ";
				System.out.println("+bean.getDeptId() =" + bean.getDeptCode());
			}

			 
			if (!bean.getCadreName().equals("")) {
				prevWhereClause += " AND HRMS_SALARY_" + prevYear
						+ ".SAL_EMP_GRADE IN (" + bean.getCadreCode()+") ";
				System.out.println("+bean.getCadreCode() ="
						+ bean.getCadreCode());
			}
			if (!bean.getPaybillName().equals("")) {
				prevWhereClause += " AND HRMS_SALARY_" + prevYear
						+ ".SAL_EMP_PAYBILL IN (" + bean.getPaybillId()+") ";
				System.out.println("+bean.getPaybillId() ="
						+ bean.getPaybillId());
			}
			
			if (!bean.getCostCenterName().equals("")) {
				prevWhereClause += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
						+ bean.getCostCenterId()+")";
				System.out
						.println("+bean.getCostCenterId() =" + bean.getCostCenterId());
			}
			
			
			
			String prevWhereArrearsClause = "";

			if (!bean.getBranchName().equals("")) {
				prevWhereArrearsClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
						+ bean.getBranchCode()+") ";
				System.out.println("+bean.getBranchCode() ="
						+ bean.getBranchCode());
			}
			
			  if(!bean.getDivName().equals("")) { 
				  prevWhereArrearsClause +=" AND HRMS_EMP_OFFC.EMP_DIV In ("+bean.getDivCode()+") ";
			 System.out.println("+bean.getDivId() =" +bean.getDivCode()); }
			
			if (!bean.getDeptName().equals("")) {
				prevWhereArrearsClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
						+ bean.getDeptCode()+") ";
				System.out.println("+bean.getDeptId() =" + bean.getDeptCode());
			}
			
			if (!bean.getCadreName().equals("")) {
				prevWhereArrearsClause += " AND HRMS_EMP_OFFC.EMP_CADRE IN ("
						+ bean.getCadreCode()+") ";
				System.out.println("+bean.getCadreCode() ="
						+ bean.getCadreCode());
			}
			if (!bean.getPaybillName().equals("")) {
				prevWhereArrearsClause += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
						+ bean.getPaybillId()+") ";
				System.out.println("+bean.getPaybillId() ="
						+ bean.getPaybillId());
			}
			

			if (!bean.getCostCenterName().equals("")) {
				prevWhereArrearsClause += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
						+ bean.getCostCenterId()+")";
				System.out
						.println("+bean.getCostCenterId() =" + bean.getCostCenterId());
			}
			
			// filters for arrears end
			
		// Separation Details start
			
		 

		 String gradeQuery = " SELECT DISTINCT HRMS_CADRE.CADRE_ID,TO_CHAR(HRMS_CADRE.CADRE_NAME) FROM  HRMS_CADRE "
					+"INNER JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".SAL_EMP_GRADE=HRMS_CADRE.CADRE_ID)"
				+ " WHERE HRMS_SALARY_"+year+".SAL_MONTH = " + month;
				

		 gradeQuery += whereClause;

		// for emp type
		Object[][] gradeDetail = getSqlModel().getSingleResult(
				gradeQuery);
		
		// Query for Dept
		String deptQuery = " SELECT DISTINCT HRMS_DEPT.DEPT_ID,TO_CHAR(HRMS_DEPT.DEPT_NAME)  FROM  HRMS_DEPT "
			+"INNER JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".SAL_DEPT=HRMS_DEPT.DEPT_ID)"
		+ " WHERE HRMS_SALARY_"+year+".SAL_MONTH = " + month;
		
		deptQuery += whereClause;
		
		// for emp type
		if(bean.getGroupByRadio().equals("Dp")){
		 gradeDetail = getSqlModel().getSingleResult(
				deptQuery);
		}
		
		// For Cost Center current month details ::
		String costCenterQuery = "	SELECT DISTINCT HRMS_COST_CENTER.COST_CENTER_ID, HRMS_COST_CENTER.COST_CENTER_NAME FROM HRMS_SALARY_"+year+" "
								 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
								 +"  INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC .EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
								 +" INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
								 +"  WHERE HRMS_SALARY_"+year+".SAL_MONTH ="+month;
		
		costCenterQuery += whereClause;
		
		if(bean.getGroupByRadio().equals("Cc")){
			 gradeDetail = getSqlModel().getSingleResult(
					 costCenterQuery);
			}
		
		if (gradeDetail != null && gradeDetail.length > 0) {
			
		
		
		
		// For current month details ::
		String empDetailsQuery = "	SELECT HRMS_SALARY_"+year+".SAL_EMP_GRADE,count(*) "
								+" FROM HRMS_SALARY_"+year+" "
								+" WHERE HRMS_SALARY_"+year+".SAL_MONTH = "+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+" "
								+" ";
		
		empDetailsQuery += whereClause;
		
		empDetailsQuery += "group by HRMS_SALARY_"+year+".SAL_EMP_GRADE ";
		
		
		// For Dept current month details ::
		String empDeptDetailsQuery = "	SELECT HRMS_SALARY_"+year+".SAL_DEPT,count(*) "
								+" FROM HRMS_SALARY_"+year+" "
								+" WHERE HRMS_SALARY_"+year+".SAL_MONTH = "+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+" "
								+" ";
		
		empDeptDetailsQuery += whereClause;
		empDeptDetailsQuery += "group by HRMS_SALARY_"+year+".SAL_DEPT ";
		
		// For Cost Center current month details ::
		String costCenterDetailsQuery = "	 SELECT DISTINCT HRMS_COST_CENTER.COST_CENTER_ID, COUNT(*) FROM HRMS_SALARY_"+year+" "
		 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
		 +"  INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC .EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
		 +" 	INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
		 +"  WHERE HRMS_SALARY_"+year+".SAL_MONTH ="+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+"  ";
		
		costCenterDetailsQuery += whereClause;
		
		costCenterDetailsQuery += " GROUP BY HRMS_COST_CENTER.COST_CENTER_ID ";
		

			Object[][] empDetailsObj = getSqlModel().getSingleResult(
				empDetailsQuery);
			
			if(bean.getGroupByRadio().equals("Dp")){
				empDetailsObj = getSqlModel().getSingleResult(
						empDeptDetailsQuery);
				
			}
			if(bean.getGroupByRadio().equals("Cc")){
				empDetailsObj = getSqlModel().getSingleResult(
						costCenterDetailsQuery);
				
			}
			HashMap<String, String>map=new HashMap<String, String>();
			
			// For previous month details ::
			String prevEmpDetailsQuery = "	SELECT HRMS_SALARY_"+prevYear+".SAL_EMP_GRADE,count(*) "
									+" FROM HRMS_SALARY_"+prevYear+" "
									+" WHERE HRMS_SALARY_"+prevYear+".SAL_MONTH = "+prevMonth+" AND HRMS_SALARY_"+prevYear+".SAL_YEAR="+prevYear+" "
									+" ";

			prevEmpDetailsQuery += prevWhereClause;
			prevEmpDetailsQuery += " group by HRMS_SALARY_"+prevYear+".SAL_EMP_GRADE ";
			
			
			// For Dept previous month details ::
			String prevDeptEmpDetailsQuery = "	SELECT HRMS_SALARY_"+prevYear+".SAL_DEPT,count(*) "
									+" FROM HRMS_SALARY_"+prevYear+" "
									+" WHERE HRMS_SALARY_"+prevYear+".SAL_MONTH = "+prevMonth+" AND HRMS_SALARY_"+prevYear+".SAL_YEAR="+prevYear+" "
									+" ";
			
			prevDeptEmpDetailsQuery += prevWhereClause;
			prevDeptEmpDetailsQuery += " group by HRMS_SALARY_"+prevYear+".SAL_DEPT ";
			
			// For Cost Center previous month details ::
			String prevCostCenterDetailsQuery = "	 SELECT DISTINCT HRMS_COST_CENTER.COST_CENTER_ID, COUNT(*) FROM HRMS_SALARY_"+year+" "
								 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
								 +"  INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC .EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
								 +" 	INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
								 +" 	 WHERE HRMS_SALARY_"+year+".SAL_MONTH ="+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+" ";
			
			prevCostCenterDetailsQuery += whereClause;
			prevCostCenterDetailsQuery += " GROUP BY HRMS_COST_CENTER.COST_CENTER_ID ";
			
			
				Object[][] prevEmpDetailsObj = getSqlModel().getSingleResult(
						prevEmpDetailsQuery);
			
				if(bean.getGroupByRadio().equals("Dp")){
					prevEmpDetailsObj = getSqlModel().getSingleResult(
							prevDeptEmpDetailsQuery);
					
				}
				if(bean.getGroupByRadio().equals("Cc")){
					prevEmpDetailsObj = getSqlModel().getSingleResult(
							prevCostCenterDetailsQuery);
					
				}
				
			HashMap<String, String>previousmap=new HashMap<String, String>();
			
			// For current month net pay details ::
			String currEmpDetailsQuery = "	SELECT HRMS_SALARY_"+year+".SAL_EMP_GRADE,SUM(HRMS_SALARY_"+year+".SAL_NET_SALARY),SUM(HRMS_SALARY_"+year+".SAL_TOTAL_CREDIT),SUM(HRMS_SALARY_"+year+".SAL_TOTAL_DEBIT) "
									+" FROM HRMS_SALARY_"+year+" "
									+" WHERE HRMS_SALARY_"+year+".SAL_MONTH = "+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+" "
									+"  ";
			
			currEmpDetailsQuery += whereClause;
			currEmpDetailsQuery += " group by HRMS_SALARY_"+year+".SAL_EMP_GRADE ";
			
			// For Dept current month net pay details ::
			String currEmpDeptDetailsQuery = "	SELECT HRMS_SALARY_"+year+".SAL_DEPT,SUM(HRMS_SALARY_"+year+".SAL_NET_SALARY),SUM(HRMS_SALARY_"+year+".SAL_TOTAL_CREDIT),SUM(HRMS_SALARY_"+year+".SAL_TOTAL_DEBIT) "
									+" FROM HRMS_SALARY_"+year+" "
									+" WHERE HRMS_SALARY_"+year+".SAL_MONTH = "+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+" "
									+" ";
			
			currEmpDeptDetailsQuery += whereClause;
			currEmpDeptDetailsQuery += " group by HRMS_SALARY_"+year+".SAL_DEPT  ";
			
			// For Cost Center current month net pay details ::
			String currCostCenterDetailsQuery = "	  SELECT DISTINCT HRMS_COST_CENTER.COST_CENTER_ID, SUM(HRMS_SALARY_"+year+".SAL_NET_SALARY),SUM(HRMS_SALARY_"+year+".SAL_TOTAL_CREDIT),SUM(HRMS_SALARY_"+year+".SAL_TOTAL_DEBIT)  FROM HRMS_SALARY_"+year+"" 
							  +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
							  +" INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC .EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
							  +" INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
							  +" WHERE HRMS_SALARY_"+year+".SAL_MONTH ="+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+"  ";
			
			currCostCenterDetailsQuery += whereClause;
			currCostCenterDetailsQuery += "  GROUP BY HRMS_COST_CENTER.COST_CENTER_ID  ";

				Object[][] currEmpDetailsObj = getSqlModel().getSingleResult(
						currEmpDetailsQuery);
				
				if(bean.getGroupByRadio().equals("Dp")){
					currEmpDetailsObj = getSqlModel().getSingleResult(
							currEmpDeptDetailsQuery);
					
				}
				if(bean.getGroupByRadio().equals("Cc")){
					currEmpDetailsObj = getSqlModel().getSingleResult(
							currCostCenterDetailsQuery);
					
				}
				
				HashMap<String, String>currEmpSalMap=new HashMap<String, String>();
				HashMap<String, String>totEarningsEmpSalMap=new HashMap<String, String>();
				HashMap<String, String>deductionEmpSalMap=new HashMap<String, String>();
				
				
				
				// For previous month net pay details ::
				String previousEmpDetailsQuery = "	SELECT SUM(HRMS_SALARY_"+prevYear+".SAL_NET_SALARY),SUM(HRMS_SALARY_"+prevYear+".SAL_TOTAL_CREDIT),SUM(HRMS_SALARY_"+prevYear+".SAL_TOTAL_DEBIT) "
										+" FROM HRMS_SALARY_"+prevYear+" "
										+" WHERE HRMS_SALARY_"+prevYear+".SAL_MONTH = "+prevMonth+" AND HRMS_SALARY_"+prevYear+".SAL_YEAR="+prevYear+" ";
										//+" group by SAL_EMP_GRADE ";

				previousEmpDetailsQuery += prevWhereClause;
				
					Object[][] previousEmpDetailsObj = getSqlModel().getSingleResult(
							previousEmpDetailsQuery);
					
				// For current arrears details ::
				String arrearsEmpDetailsQuery = "	SELECT HRMS_CADRE.CADRE_ID,SUM(HRMS_ARREARS_"+year+".ARREARS_CREDITS_AMT),SUM(HRMS_ARREARS_"+year+".ARREARS_DEBITS_AMT) "
									+" FROM HRMS_ARREARS_"+year+" "
									+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_"+year+".EMP_ID) "
									+" INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
									+"INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+year+".ARREARS_CODE AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+month+" AND HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR="+year+")"
									+" WHERE HRMS_ARREARS_"+year+".ARREARS_PAY_TYPE='A' AND  HRMS_ARREARS_"+year+".ARREARS_YEAR = "+year+"   "
									+"  ";
				
				arrearsEmpDetailsQuery += whereArrearsClause;
				arrearsEmpDetailsQuery += "  GROUP BY HRMS_CADRE.CADRE_ID  ";
				
				// For Previous arrears details ::
				String prevArrearsEmpDetailsQuery = "	SELECT SUM(HRMS_ARREARS_"+prevYear+".ARREARS_CREDITS_AMT),SUM(HRMS_ARREARS_"+prevYear+".ARREARS_DEBITS_AMT) "
									+" FROM HRMS_ARREARS_"+prevYear+" "
									+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_"+prevYear+".EMP_ID) "
									+" INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
									+"INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+prevYear+".ARREARS_CODE AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+prevMonth+" AND HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR="+prevYear+")"
									+" WHERE HRMS_ARREARS_"+prevYear+".ARREARS_PAY_TYPE='A' AND   HRMS_ARREARS_"+prevYear+".ARREARS_YEAR = "+prevYear+"   "
									+" ";
				
				prevArrearsEmpDetailsQuery += prevWhereArrearsClause;
				
				// For Dept current arrears details ::
				String arrearsEmpDeptDetailsQuery = "	SELECT HRMS_DEPT.DEPT_ID,SUM(HRMS_ARREARS_"+year+".ARREARS_CREDITS_AMT),SUM(HRMS_ARREARS_"+year+".ARREARS_DEBITS_AMT) "
									+" FROM HRMS_ARREARS_"+year+" "
									+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_"+year+".EMP_ID) "
									+" INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
									+"INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+year+".ARREARS_CODE AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+month+" AND HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR="+year+")"
									+" WHERE HRMS_ARREARS_"+year+".ARREARS_PAY_TYPE='A' AND  HRMS_ARREARS_"+year+".ARREARS_YEAR = "+year+"   "
									+"  ";
				
				arrearsEmpDeptDetailsQuery += whereArrearsClause;
				arrearsEmpDeptDetailsQuery += "  GROUP BY HRMS_DEPT.DEPT_ID  ";
				
				// For Dept Previous arrears details ::
				String prevArrearsEmpDeptDetailsQuery = "	SELECT SUM(HRMS_ARREARS_"+prevYear+".ARREARS_CREDITS_AMT),SUM(HRMS_ARREARS_"+prevYear+".ARREARS_DEBITS_AMT) "
									+" FROM HRMS_ARREARS_"+prevYear+" "
									+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_"+prevYear+".EMP_ID) "
									+" INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
									+"INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+prevYear+".ARREARS_CODE AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+prevMonth+" AND HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR="+prevYear+")"
									+" WHERE HRMS_ARREARS_"+prevYear+".ARREARS_PAY_TYPE='A' AND  HRMS_ARREARS_"+prevYear+".ARREARS_YEAR = "+prevYear+"   "
									+"  ";
				prevArrearsEmpDeptDetailsQuery += prevWhereArrearsClause;
				
				
				// For Cost Center current arrears details ::
				String arrearsCostCenterDetailsQuery = "		 SELECT DISTINCT HRMS_COST_CENTER.COST_CENTER_ID, SUM(HRMS_ARREARS_"+year+".ARREARS_CREDITS_AMT),SUM(HRMS_ARREARS_"+year+".ARREARS_DEBITS_AMT) FROM HRMS_ARREARS_"+year+" " 
								 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"+year+".EMP_ID)"
								 +"  INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC .EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
								 +" 	INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
								 +"INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+year+".ARREARS_CODE AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+month+" AND HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR="+year+")"
								 +"  WHERE HRMS_ARREARS_"+year+".ARREARS_PAY_TYPE='A' AND  HRMS_ARREARS_"+year+".ARREARS_YEAR="+year+"    ";
				
				arrearsCostCenterDetailsQuery += whereArrearsClause;
				arrearsCostCenterDetailsQuery += "  GROUP BY HRMS_COST_CENTER.COST_CENTER_ID  ";
				
				// For Cost Center Previous arrears details ::
				String prevArrearsCostCenterDetailsQuery = "		 SELECT   SUM(HRMS_ARREARS_"+prevYear+".ARREARS_CREDITS_AMT),SUM(HRMS_ARREARS_"+prevYear+".ARREARS_DEBITS_AMT) FROM HRMS_ARREARS_"+prevYear+" " 
								 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"+prevYear+".EMP_ID)"
								 +"  INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC .EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
								 +" 	INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
								 +"INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"+prevYear+".ARREARS_CODE AND HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+prevMonth+" AND HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR="+prevYear+")"
								 +"  WHERE HRMS_ARREARS_"+prevYear+".ARREARS_PAY_TYPE='A' AND  HRMS_ARREARS_"+prevYear+".ARREARS_MONTH ="+prevMonth+" AND HRMS_ARREARS_"+prevYear+".ARREARS_YEAR="+prevYear+"  ";
				
				prevArrearsCostCenterDetailsQuery += prevWhereArrearsClause;
				
				Object[][] prevArrearsEmpDetailsObj = getSqlModel().getSingleResult(
						prevArrearsEmpDetailsQuery);
			
				
				if(bean.getGroupByRadio().equals("Dp")){
					prevArrearsEmpDetailsObj = getSqlModel().getSingleResult(
							prevArrearsEmpDeptDetailsQuery);
					
				}
				
				if(bean.getGroupByRadio().equals("Cc")){
					prevArrearsEmpDetailsObj = getSqlModel().getSingleResult(
							prevArrearsCostCenterDetailsQuery);
					
				}
				HashMap<String, String>prevArrearsEmpSalMap=new HashMap<String, String>();
				HashMap<String, String>prevArrearsEmpDebitSalMap=new HashMap<String, String>();

					Object[][] arrearsEmpDetailsObj = getSqlModel().getSingleResult(
							arrearsEmpDetailsQuery);
					
					if(bean.getGroupByRadio().equals("Dp")){
						arrearsEmpDetailsObj = getSqlModel().getSingleResult(
								arrearsEmpDeptDetailsQuery);
						
					}
					
					if(bean.getGroupByRadio().equals("Cc")){
						arrearsEmpDetailsObj = getSqlModel().getSingleResult(
								arrearsCostCenterDetailsQuery);
						
					}
					
					HashMap<String, String>arrearsEmpSalMap=new HashMap<String, String>();
					HashMap<String, String>arrearsEmpDebitSalMap=new HashMap<String, String>();
				
				
		if(empDetailsObj!=null && empDetailsObj.length > 0){
			for (int i = 0; i < empDetailsObj.length; i++) {
				map.put(String.valueOf(empDetailsObj[i][0]), String.valueOf(empDetailsObj[i][1]));
			}
		}
		
		if(prevEmpDetailsObj!=null && prevEmpDetailsObj.length > 0){
			for (int i = 0; i < prevEmpDetailsObj.length; i++) {
				previousmap.put(String.valueOf(prevEmpDetailsObj[i][0]), String.valueOf(prevEmpDetailsObj[i][1]));
			}
		}
		
		if(currEmpDetailsObj!=null && currEmpDetailsObj.length > 0){
			for (int i = 0; i < currEmpDetailsObj.length; i++) {
				currEmpSalMap.put(String.valueOf(currEmpDetailsObj[i][0]), String.valueOf(currEmpDetailsObj[i][2]));
			}
		}
		
		if(currEmpDetailsObj!=null && currEmpDetailsObj.length > 0){
			for (int i = 0; i < currEmpDetailsObj.length; i++) {
				totEarningsEmpSalMap.put(String.valueOf(currEmpDetailsObj[i][0]), String.valueOf(currEmpDetailsObj[i][2]));
			}
		}
		if(currEmpDetailsObj!=null && currEmpDetailsObj.length > 0){
			for (int i = 0; i < currEmpDetailsObj.length; i++) {
				deductionEmpSalMap.put(String.valueOf(currEmpDetailsObj[i][0]), String.valueOf(currEmpDetailsObj[i][3]));
			}
		}
		
		
		
		// for arrears
		if(arrearsEmpDetailsObj!=null && arrearsEmpDetailsObj.length > 0){
			for (int i = 0; i < arrearsEmpDetailsObj.length; i++) {
				arrearsEmpSalMap.put(String.valueOf(arrearsEmpDetailsObj[i][0]), String.valueOf(arrearsEmpDetailsObj[i][1]));
			}
		}
		
		// for arrears Debit
		if(arrearsEmpDetailsObj!=null && arrearsEmpDetailsObj.length > 0){
			for (int i = 0; i < arrearsEmpDetailsObj.length; i++) {
				arrearsEmpDebitSalMap.put(String.valueOf(arrearsEmpDetailsObj[i][0]), String.valueOf(arrearsEmpDetailsObj[i][2]));
			}
		}
		
		
		/**
		 * FOR NEW JOINEE
		 */
		// For Grade
			String newJoineeGrade="SELECT HRMS_SALARY_"+year+".SAL_EMP_GRADE,count(*)  FROM HRMS_SALARY_"+year+"  " +
					"WHERE HRMS_SALARY_"+year+".SAL_MONTH = "+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+"    "+whereClause+"" +
					"AND HRMS_SALARY_"+year+".EMP_ID NOT IN(SELECT HRMS_SALARY_"+prevYear+".EMP_ID FROM HRMS_SALARY_"+prevYear+"  " +
					"WHERE HRMS_SALARY_"+prevYear+".SAL_MONTH = "+prevMonth+" AND HRMS_SALARY_"+prevYear+".SAL_YEAR="+prevYear+"   "+prevWhereClause+" ) " +
					"group by HRMS_SALARY_"+year+".SAL_EMP_GRADE "	;
			
				Object[][]newJoineeObj = getSqlModel().getSingleResult(newJoineeGrade);
				
		// For Dept
		String newJoineeDept="SELECT HRMS_SALARY_"+year+".SAL_DEPT,count(*)  FROM HRMS_SALARY_"+year+"  WHERE HRMS_SALARY_"+year+".SAL_MONTH = "+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+"  "+whereClause+" "
						+"	AND HRMS_SALARY_"+year+".EMP_ID NOT IN(SELECT HRMS_SALARY_"+prevYear+".EMP_ID FROM HRMS_SALARY_"+prevYear+"  WHERE HRMS_SALARY_"+prevYear+".SAL_MONTH ="+prevMonth+" AND HRMS_SALARY_"+prevYear+".SAL_YEAR="+prevYear+"  "+prevWhereClause+" )"
						+"	 group by HRMS_SALARY_"+year+".SAL_DEPT "	;
		
		
		// For Cost Center
		String newJoineeCostCenter=" SELECT DISTINCT HRMS_COST_CENTER.COST_CENTER_ID, COUNT(*) FROM HRMS_SALARY_"+year+" "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
					+" INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+"	INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+"	 WHERE HRMS_SALARY_"+year+".SAL_MONTH ="+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+""
					+"	 AND HRMS_SALARY_"+year+".EMP_ID NOT IN("
					+"	  SELECT HRMS_SALARY_"+prevYear+".EMP_ID FROM HRMS_SALARY_"+prevYear+" "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+prevYear+".EMP_ID)"
					+" INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+"	INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+"	 WHERE HRMS_SALARY_"+prevYear+".SAL_MONTH ="+prevMonth+" AND HRMS_SALARY_"+prevYear+".SAL_YEAR="+prevYear+" "
					+"	  )"
					+"	  GROUP BY HRMS_COST_CENTER.COST_CENTER_ID "	;
		
		
		if(bean.getGroupByRadio().equals("Dp")){
			newJoineeObj = getSqlModel().getSingleResult(
					newJoineeDept);
			
		}
		
		if(bean.getGroupByRadio().equals("Cc")){
			newJoineeObj = getSqlModel().getSingleResult(
					newJoineeCostCenter);
			
		}
		
		HashMap<String, String>newJoineeMap=new HashMap<String, String>();
		HashMap<String, String>newJoineeGradeMap=new HashMap<String, String>();
		HashMap<String, String>newJoineeCostCenterMap=new HashMap<String, String>();
		
		if(newJoineeObj!=null && newJoineeObj.length > 0){
			for (int i = 0; i < newJoineeObj.length; i++) {
				newJoineeMap.put(String.valueOf(newJoineeObj[i][0]), String.valueOf(newJoineeObj[i][1]));
			}
		}
		
		
		if(newJoineeObj!=null && newJoineeObj.length > 0){
		for (int i = 0; i < newJoineeObj.length; i++) {
			newJoineeGradeMap.put(String.valueOf(newJoineeObj[i][0]), String.valueOf(newJoineeObj[i][1]));
		}
		}
		if(newJoineeObj!=null && newJoineeObj.length > 0){
			for (int i = 0; i < newJoineeObj.length; i++) {
				newJoineeCostCenterMap.put(String.valueOf(newJoineeObj[i][0]), String.valueOf(newJoineeObj[i][1]));
			}
			}
		
		
		/**
		 * FOR Resign
		 */
		// For Grade
			String resignGrade="SELECT HRMS_SALARY_"+prevYear+".SAL_EMP_GRADE,count(*)  FROM HRMS_SALARY_"+prevYear+"  " +
					"WHERE HRMS_SALARY_"+prevYear+".SAL_MONTH = "+prevMonth+" AND HRMS_SALARY_"+prevYear+".SAL_YEAR="+prevYear+"   "+prevWhereClause+"  " +
					"AND HRMS_SALARY_"+prevYear+".EMP_ID NOT IN(SELECT HRMS_SALARY_"+year+".EMP_ID FROM HRMS_SALARY_"+year+"  " +
					"WHERE HRMS_SALARY_"+year+".SAL_MONTH = "+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+"   "+whereClause+"  ) " +
					"group by HRMS_SALARY_"+prevYear+".SAL_EMP_GRADE "	;
			
		Object[][]resignGradeObj = getSqlModel().getSingleResult(resignGrade);
		
		// For Dept
		String resignDept="SELECT HRMS_SALARY_"+prevYear+".SAL_DEPT,count(*)  FROM HRMS_SALARY_"+prevYear+"  WHERE HRMS_SALARY_"+prevYear+".SAL_MONTH = "+prevMonth+" " +
				"AND HRMS_SALARY_"+prevYear+".SAL_YEAR="+prevYear+"    "+prevWhereClause+" "
				+"AND HRMS_SALARY_"+prevYear+".EMP_ID NOT IN(SELECT HRMS_SALARY_"+year+".EMP_ID FROM HRMS_SALARY_"+year+"  "
						+"WHERE HRMS_SALARY_"+year+".SAL_MONTH = "+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+"  "+whereClause+"  )"
						+" group by HRMS_SALARY_"+prevYear+".SAL_DEPT  "	;
		
	
		
		// For Cost Center
		String resignCostCenter=" SELECT DISTINCT HRMS_COST_CENTER.COST_CENTER_ID, COUNT(*) FROM HRMS_SALARY_"+prevYear+" "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+prevYear+".EMP_ID)"
					+" INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+"	INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+"	 WHERE HRMS_SALARY_"+prevYear+".SAL_MONTH ="+prevMonth+" AND HRMS_SALARY_"+prevYear+".SAL_YEAR="+prevYear+" "
					+"	 AND HRMS_SALARY_"+prevYear+".EMP_ID NOT IN("
					+"	  SELECT HRMS_SALARY_"+year+".EMP_ID FROM HRMS_SALARY_"+year+"  "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
					+" INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+"	INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
					+"	 WHERE HRMS_SALARY_"+year+".SAL_MONTH ="+month+" AND HRMS_SALARY_"+year+".SAL_YEAR="+year+" "
					+"	  )"
					+"	  GROUP BY HRMS_COST_CENTER.COST_CENTER_ID "	;
		
		if(bean.getGroupByRadio().equals("Dp")){
			resignGradeObj = getSqlModel().getSingleResult(
					resignDept);
			
		}
		if(bean.getGroupByRadio().equals("Cc")){
			resignGradeObj = getSqlModel().getSingleResult(
					resignCostCenter);
			
		}
		HashMap<String, String>resignGradeMap=new HashMap<String, String>();
		HashMap<String, String>resignDeptMap=new HashMap<String, String>();
		HashMap<String, String>resignCostCenterMap=new HashMap<String, String>();
		
		
		if(resignGradeObj!=null && resignGradeObj.length > 0){
			for (int i = 0; i < resignGradeObj.length; i++) {
				resignGradeMap.put(String.valueOf(resignGradeObj[i][0]), String.valueOf(resignGradeObj[i][1]));
			}
			}
		
		if(resignGradeObj!=null && resignGradeObj.length > 0){
			for (int i = 0; i < resignGradeObj.length; i++) {
				resignDeptMap.put(String.valueOf(resignGradeObj[i][0]), String.valueOf(resignGradeObj[i][1]));
			}
			}
		
		if(resignGradeObj!=null && resignGradeObj.length > 0){
			for (int i = 0; i < resignGradeObj.length; i++) {
				resignCostCenterMap.put(String.valueOf(resignGradeObj[i][0]), String.valueOf(resignGradeObj[i][1]));
			}
			}
		
		// for arrears previous 
		/*if(prevArrearsEmpDetailsObj!=null && prevArrearsEmpDetailsObj.length > 0){
			for (int i = 0; i < prevArrearsEmpDetailsObj.length; i++) {
				prevArrearsEmpSalMap.put(String.valueOf(arrearsEmpDetailsObj[i][0]), String.valueOf(arrearsEmpDetailsObj[i][1]));
			}
		}*/
		
		
		// for arrears previous 
		/*if(prevArrearsEmpDetailsObj!=null && prevArrearsEmpDetailsObj.length > 0){
			for (int i = 0; i < prevArrearsEmpDetailsObj.length; i++) {
				prevArrearsEmpDebitSalMap.put(String.valueOf(arrearsEmpDetailsObj[i][0]), String.valueOf(arrearsEmpDetailsObj[i][1]));
			}
		}*/
		
		
		
		/**
		 * FOR Current Bonus start 20 Feb 2012
		 */
		
		
		String whereBonusClause="";
		
		if(!bean.getBranchName().equals(""))
		{
			whereBonusClause +=" AND HRMS_EMP_OFFC.EMP_CENTER ="+bean.getBranchCode();
			System.out.println("+bean.getBranchCode() ="  +bean.getBranchCode());
		}
		/*if(!bean.getDivName().equals(""))
		{
			whereBonusClause +=" AND HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivId();
			System.out.println("+bean.getDivId() ="  +bean.getDivId());
		}*/
		if(!bean.getDeptName().equals(""))
		{
			whereBonusClause +=" AND HRMS_EMP_OFFC.EMP_DEPT ="+bean.getDeptCode();
			System.out.println("+bean.getDeptId() ="  +bean.getDeptCode());
		}
		
		if (!bean.getCadreName().equals("")) {
			whereBonusClause += " AND HRMS_EMP_OFFC.EMP_CADRE IN (" + bean.getCadreCode()+") ";
			System.out.println("+bean.getCadreCode() ="
					+ bean.getCadreCode());
		}
		if (!bean.getPaybillName().equals("")) {
			whereBonusClause += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN (" + bean.getPaybillId()+") ";
			System.out.println("+bean.getPaybillId() ="
					+ bean.getPaybillId());
		}
		
		if (!bean.getCostCenterName().equals("")) {
			whereBonusClause += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
					+ bean.getCostCenterId()+")";
			System.out
					.println("+bean.getCostCenterId() =" + bean.getCostCenterId());
		}
		
		
		
		
		// For Current Grade
			String bonusGrade="SELECT EMP_CADRE,SUM(EMP_BONUS_TOTAL) FROM HRMS_BONUS_EMP "
						+"INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_BONUS_EMP.EMP_ID)"
						+"WHERE BONUS_CODE=(SELECT BONUS_CODE FROM HRMS_BONUS_HDR WHERE PAY_MONTH="+month+" AND  PAY_YEAR="+year+"  AND HRMS_BONUS_HDR.DIV_CODE IN ("+bean.getDivCode()+") )"
						+" "	;
			bonusGrade += whereBonusClause;
			bonusGrade += " GROUP BY EMP_CADRE ";
			
		Object[][]bonusGradeObj = getSqlModel().getSingleResult(bonusGrade);
		
		
		// For Current Dept
		String bonusDept="SELECT HRMS_EMP_OFFC.EMP_DEPT,SUM(HRMS_BONUS_EMP.EMP_BONUS_TOTAL) FROM HRMS_BONUS_EMP "
					+"INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_BONUS_EMP.EMP_ID)"
					+"WHERE HRMS_BONUS_EMP.BONUS_CODE=(SELECT HRMS_BONUS_HDR.BONUS_CODE FROM HRMS_BONUS_HDR WHERE HRMS_BONUS_HDR.PAY_MONTH="+month+" AND  HRMS_BONUS_HDR.PAY_YEAR="+year+"  AND HRMS_BONUS_HDR.DIV_CODE IN ("+bean.getDivCode()+") )"
					+" "	;
		bonusDept += whereBonusClause;
		bonusDept += " GROUP BY HRMS_EMP_OFFC.EMP_DEPT ";
		
		// For Current Cost Center
		String bonusCostCenter="SELECT HRMS_COST_CENTER.COST_CENTER_ID,SUM(HRMS_BONUS_EMP.EMP_BONUS_TOTAL) "
				+"	FROM HRMS_BONUS_EMP "
				+"		INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_BONUS_EMP.EMP_ID)"
				+"		INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+"		INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
				+"		WHERE HRMS_BONUS_EMP.BONUS_CODE=(SELECT HRMS_BONUS_HDR.BONUS_CODE FROM HRMS_BONUS_HDR " +
						"WHERE HRMS_BONUS_HDR.PAY_MONTH="+month+" AND  HRMS_BONUS_HDR.PAY_YEAR="+year+"  AND HRMS_BONUS_HDR.DIV_CODE IN ("+bean.getDivCode()+") )"
				+"		 "	;
		//bonusGrade += whereClause;
		bonusCostCenter += whereBonusClause;
		bonusCostCenter += " GROUP BY HRMS_COST_CENTER.COST_CENTER_ID ";
		
		if(bean.getGroupByRadio().equals("Dp")){
			bonusGradeObj = getSqlModel().getSingleResult(
					bonusDept);
			
		}
		if(bean.getGroupByRadio().equals("Cc")){
			bonusGradeObj = getSqlModel().getSingleResult(
					bonusCostCenter);
			
		}
		
		HashMap<String, String>bonusGradeMap=new HashMap<String, String>();
		HashMap<String, String>bonusDeptMap=new HashMap<String, String>();
		HashMap<String, String>bonusCostCenterMap=new HashMap<String, String>();
		
		if(bonusGradeObj!=null && bonusGradeObj.length > 0){
			for (int i = 0; i < bonusGradeObj.length; i++) {
				bonusGradeMap.put(String.valueOf(bonusGradeObj[i][0]), String.valueOf(bonusGradeObj[i][1]));
			}
		}
		
		if(bonusGradeObj!=null && bonusGradeObj.length > 0){
			for (int i = 0; i < bonusGradeObj.length; i++) {
				bonusDeptMap.put(String.valueOf(bonusGradeObj[i][0]), String.valueOf(bonusGradeObj[i][1]));
			}
			}
		
		if(bonusGradeObj!=null && bonusGradeObj.length > 0){
			for (int i = 0; i < bonusGradeObj.length; i++) {
				bonusCostCenterMap.put(String.valueOf(bonusGradeObj[i][0]), String.valueOf(bonusGradeObj[i][1]));
			}
			}
		
		
		/**
		 * FOR Bonus end
		 */
		
		
		/**
		 * FOR Previous Bonus start 20 Feb 2012
		 */
		// For Current Grade
			String bonusPrevGrade="SELECT SUM(EMP_BONUS_TOTAL) FROM HRMS_BONUS_EMP "
						+"INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_BONUS_EMP.EMP_ID)"
						+"WHERE BONUS_CODE=(SELECT BONUS_CODE FROM HRMS_BONUS_HDR WHERE PAY_MONTH="+prevMonth+" AND  PAY_YEAR="+prevYear+"  AND HRMS_BONUS_HDR.DIV_CODE IN ("+bean.getDivCode()+") )"
						+" "	;
			bonusPrevGrade += whereBonusClause;
			bonusPrevGrade += " GROUP BY EMP_CADRE ";
			
		Object[][]bonusPrevGradeObj = getSqlModel().getSingleResult(bonusPrevGrade);
		
		
		// For Current Dept
		String bonusPrevDept="SELECT SUM(HRMS_BONUS_EMP.EMP_BONUS_TOTAL) FROM HRMS_BONUS_EMP "
					+"INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_BONUS_EMP.EMP_ID)"
					+"WHERE HRMS_BONUS_EMP.BONUS_CODE=(SELECT HRMS_BONUS_HDR.BONUS_CODE FROM HRMS_BONUS_HDR WHERE HRMS_BONUS_HDR.PAY_MONTH="+prevMonth+" AND  HRMS_BONUS_HDR.PAY_YEAR="+prevYear+"  AND HRMS_BONUS_HDR.DIV_CODE IN ("+bean.getDivCode()+") )"
					+" "	;
		bonusPrevDept += whereBonusClause;
		bonusPrevDept += " GROUP BY HRMS_EMP_OFFC.EMP_DEPT ";
		
		// For Current Cost Center
		String bonusPrevCostCenter="SELECT SUM(HRMS_BONUS_EMP.EMP_BONUS_TOTAL) "
				+"	FROM HRMS_BONUS_EMP "
				+"		INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_BONUS_EMP.EMP_ID)"
				+"		INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+"		INNER JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
				+"		WHERE HRMS_BONUS_EMP.BONUS_CODE=(SELECT HRMS_BONUS_HDR.BONUS_CODE FROM HRMS_BONUS_HDR " +
						"WHERE HRMS_BONUS_HDR.PAY_MONTH="+prevMonth+" AND  HRMS_BONUS_HDR.PAY_YEAR="+prevYear+"  AND HRMS_BONUS_HDR.DIV_CODE IN ("+bean.getDivCode()+") )"
				+"		 "	;
		bonusPrevCostCenter += whereBonusClause;
		bonusPrevCostCenter += " GROUP BY HRMS_COST_CENTER.COST_CENTER_ID ";
		
		if(bean.getGroupByRadio().equals("Dp")){
			bonusPrevGradeObj = getSqlModel().getSingleResult(
					bonusPrevDept);
			
		}
		if(bean.getGroupByRadio().equals("Cc")){
			bonusPrevGradeObj = getSqlModel().getSingleResult(
					bonusPrevCostCenter);
			
		}
		
		
		
		/**
		 * FOR previous Bonus end
		 */
		
		Object[][] objTotalTabularData = new Object[gradeDetail.length][13];
		Object[][] prevObjTotalTabularData = new Object[gradeDetail.length][13];
		Object[][] increaseDecreaseColumn = new Object[2][13];
		
		Object[][] totalByColumn = new Object[2][13];
		
		
		if (gradeDetail != null && gradeDetail.length > 0) {

			for (int j = 0; j < gradeDetail.length; j++) {
				
				String noOfStaff = map.get(String.valueOf(gradeDetail[j][0]));
				String prvnoOfStaff = previousmap.get(String.valueOf(gradeDetail[j][0]));
				
				String currEmpSal = currEmpSalMap.get(String.valueOf(gradeDetail[j][0]));
				String totEarningsEmpSal = totEarningsEmpSalMap.get(String.valueOf(gradeDetail[j][0]));
				String deductionEmpSal = deductionEmpSalMap.get(String.valueOf(gradeDetail[j][0]));
				String arrearsEmpSal = arrearsEmpSalMap.get(String.valueOf(gradeDetail[j][0]));
				String arrEmpDebitSal = arrearsEmpDebitSalMap.get(String.valueOf(gradeDetail[j][0]));
				String bonusGradeVal = bonusGradeMap.get(String.valueOf(gradeDetail[j][0]));
								
				
				
				int join=0;
				int resign=0;
				int totalNoOfStaff=0;
				int totalNoOfStaffAtBegining=0;
				objTotalTabularData[j][0] = gradeDetail[j][1]; // Grade Name
				objTotalTabularData[j][1] = 0; 
				objTotalTabularData[j][2] = 0; 
				objTotalTabularData[j][3] = 0; 
				objTotalTabularData[j][4] = 0; 
				
				if(noOfStaff!=null){
					totalNoOfStaff=Integer.parseInt(checkNullValue(String.valueOf(noOfStaff)));
					objTotalTabularData[j][4] = totalNoOfStaff; // Total No. of Staff
				}
				
				String newJoineeString=newJoineeMap.get(String.valueOf(gradeDetail[j][0]));
				if(newJoineeString!=null){
					join=Integer.parseInt(checkNullValueJoin(newJoineeString));
				}
				
				String resignString = resignGradeMap.get(String.valueOf(gradeDetail[j][0]));
				if(resignString!=null){
					resign=Integer.parseInt(checkNullValueJoin(resignString));
				}
				totalNoOfStaffAtBegining=(totalNoOfStaff+resign-join);
				objTotalTabularData[j][2] = join; 
				objTotalTabularData[j][3] = resign; 
				
				//int totalStaff = Integer.parseInt(checkNullValue(String.valueOf(join)))-Integer.parseInt(checkNullValue(String.valueOf(resign)));
				
				objTotalTabularData[j][1] = totalNoOfStaffAtBegining; // No of Staff at	the	Beginning of month
				
				objTotalTabularData[j][5] = "0.00";
				if(currEmpSal!=null){
					objTotalTabularData[j][5] = formatter.format(Double.parseDouble(String.valueOf(currEmpSal)));// Salary
					System.out.println("objTotalTabularData[j][5]===="+objTotalTabularData[j][5]);
				}
				
				
				objTotalTabularData[j][6] = "0.00";
				if(arrearsEmpSal!=null){
					objTotalTabularData[j][6] = formatter.format(Double.parseDouble(String.valueOf(arrearsEmpSal)));// Arrears
				}
				
				objTotalTabularData[j][7] = "0.00 "; 
			//	int totalBonus=0;
				if(bonusGradeVal!=null){
					//totalBonus=Integer.parseInt(checkNullValue(String.valueOf(bonusGradeVal)));
					objTotalTabularData[j][7] = formatter.format(Double.parseDouble(String.valueOf(bonusGradeVal))); // Bonus
					System.out.println("totalBonus==="+bonusGradeVal);
				}
				
				
				
				objTotalTabularData[j][8] = "0.00"; // Incentive
				
				double salary =Double.parseDouble(checkNullValue(String.valueOf(objTotalTabularData[j][5])));
				double Arrears =Double.parseDouble(checkNullValue(String.valueOf(objTotalTabularData[j][6])));
				double bonus =Double.parseDouble(checkNullValue(String.valueOf(objTotalTabularData[j][7])));	
					
				
				objTotalTabularData[j][9] = formatter.format(salary+Arrears+bonus);
				/*if(totEarningsEmpSal!=null){
					objTotalTabularData[j][9] = totEarningsEmpSal;// total Earnings
				}*/
				
				double debitSalary =Double.parseDouble(checkNullValue(String.valueOf(deductionEmpSal)));
				double debitArrears =Double.parseDouble(checkNullValue(String.valueOf(arrEmpDebitSal)));
				double debitBonus =0.00;
				
				objTotalTabularData[j][10] = formatter.format(debitSalary+debitArrears+debitBonus);// Dedcution
				/*if(deductionEmpSal!=null){
					objTotalTabularData[j][10] = deductionEmpSal;// Dedcution
				}
				*/
				
				double netTotalEarnings =Double.parseDouble(checkNullValue(String.valueOf(objTotalTabularData[j][9])));
				double deduction =Double.parseDouble(checkNullValue(String.valueOf(objTotalTabularData[j][10])));
				
				objTotalTabularData[j][11] = formatter.format(netTotalEarnings-deduction); // Net Payment
			//	double avgCtc= (Double.parseDouble(String.valueOf(objTotalTabularData[j][11]))/totalNoOfStaff) * 100;
				double avgCtc= (Double.parseDouble(String.valueOf(objTotalTabularData[j][11]))/totalNoOfStaff);
				objTotalTabularData[j][12] = formatter.format(avgCtc); // Avg CTC per Employee
				
				
			}
			
			
		
			
			for (int i = 0; i < objTotalTabularData.length; i++) {

							
				// totalByColumn[0][K] = "0";

				totalByColumn[0][1] = Integer.parseInt(checkNullValueJoin(String
								.valueOf(totalByColumn[0][1])))
						+ Integer.parseInt(checkNullValueJoin(String
								.valueOf(objTotalTabularData[i][1])));

				totalByColumn[0][2] = Integer.parseInt(checkNullValueJoin(String
								.valueOf(totalByColumn[0][2])))
						+ Integer.parseInt(checkNullValueJoin(String
								.valueOf(objTotalTabularData[i][2])));

				totalByColumn[0][3] = Integer.parseInt(checkNullValueJoin(String
								.valueOf(totalByColumn[0][3])))
						+ Integer.parseInt(checkNullValueJoin(String
								.valueOf(objTotalTabularData[i][3])));

				totalByColumn[0][4] = Integer.parseInt(checkNullValueJoin(String
								.valueOf(totalByColumn[0][4])))
						+ Integer.parseInt(checkNullValueJoin(String
								.valueOf(objTotalTabularData[i][4])));
				
				totalByColumn[1][4] = Integer.parseInt(checkNullValueJoin(String
								.valueOf(totalByColumn[1][4])))
						+ Integer.parseInt(checkNullValueJoin(String
								.valueOf(objTotalTabularData[i][1])));
				
				
				totalByColumn[0][5] = formatter
				.format(Double
				.parseDouble(checkNullValue(String
						.valueOf(totalByColumn[0][5])))
				+ Double.parseDouble(checkNullValue(String
						.valueOf(objTotalTabularData[i][5]))));
				
				
				if(previousEmpDetailsObj!=null&& previousEmpDetailsObj.length>0){
					totalByColumn[1][5]=checkNullValue(String.valueOf(previousEmpDetailsObj[0][1]));
				}
				
								
				totalByColumn[0][6] = formatter
				.format(Double
				.parseDouble(checkNullValue(String
						.valueOf(totalByColumn[0][6])))
				+ Double.parseDouble(checkNullValue(String
						.valueOf(objTotalTabularData[i][6]))));
				
				
				totalByColumn[1][6]="0.00";
				if(prevArrearsEmpDetailsObj!=null&& prevArrearsEmpDetailsObj.length>0){
					totalByColumn[1][6]=checkNullValue(String.valueOf(prevArrearsEmpDetailsObj[0][0]));
				}
				
				
				
				
				totalByColumn[0][7] = formatter
				.format(Double
				.parseDouble(checkNullValue(String
						.valueOf(totalByColumn[0][7])))
				+ Double.parseDouble(checkNullValue(String
						.valueOf(objTotalTabularData[i][7]))));
				
				totalByColumn[1][7]="0.00";
				
				if(bonusPrevGradeObj!=null&& bonusPrevGradeObj.length>0){
					totalByColumn[1][7]=checkNullValue(String.valueOf(bonusPrevGradeObj[0][0]));
				}
				
				
				totalByColumn[0][8] = formatter
				.format(Double
				.parseDouble(checkNullValue(String
						.valueOf(totalByColumn[0][8])))
				+ Double.parseDouble(checkNullValue(String
						.valueOf(objTotalTabularData[i][8]))));
				
				totalByColumn[1][8]="0.00";
				
				totalByColumn[0][9] = formatter
				.format(Double
				.parseDouble(checkNullValue(String
						.valueOf(totalByColumn[0][9])))
				+ Double.parseDouble(checkNullValue(String
						.valueOf(objTotalTabularData[i][9]))));
				
				
				
				double salary =Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][5])));
				double Arrears =Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][6])));
				double bonus =0.00;	
				
				
				totalByColumn[1][9] = formatter
				.format(salary+Arrears+bonus);
				
				
				totalByColumn[0][10] = formatter
				.format(Double
				.parseDouble(checkNullValueJoin(String
						.valueOf(totalByColumn[0][10])))
				+ Double.parseDouble(checkNullValueJoin(String
						.valueOf(objTotalTabularData[i][10]))));
				
				totalByColumn[1][10] = " 0.00";
				
				double debitSalary =Double.parseDouble(checkNullValue(String.valueOf(previousEmpDetailsObj[0][2])));
				double debitArrears =Double.parseDouble(checkNullValue(String.valueOf(prevArrearsEmpDetailsObj[0][1])));
				double debitBonus =0.00;	
				
				totalByColumn[1][10]=formatter
				.format(debitSalary+debitArrears+debitBonus);
			
				
				totalByColumn[0][11] = formatter
				.format(Double
				.parseDouble(checkNullValue(String
						.valueOf(totalByColumn[0][11])))
				+ Double.parseDouble(checkNullValue(String
						.valueOf(objTotalTabularData[i][11]))));
				
			
				double netTotalEarnings =Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][9])));
				double deduction =Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][10])));
				
				totalByColumn[1][11] = formatter
				.format(netTotalEarnings-deduction);
				
				totalByColumn[0][12] = formatter
				.format(Double
				.parseDouble(checkNullValue(String
						.valueOf(totalByColumn[0][12])))
				+ Double.parseDouble(checkNullValue(String
						.valueOf(objTotalTabularData[i][12]))));
				
				double avgCtc=0.00;
				if(Double.parseDouble(String.valueOf(totalByColumn[1][4]))>0.0)
				 avgCtc= (Double.parseDouble(String.valueOf(totalByColumn[1][11]))/Double.parseDouble(String.valueOf(totalByColumn[1][4]))) * 100;
				
				
				totalByColumn[1][12] = formatter
				.format(avgCtc);
			}
			
			
			/**
			 * SET PREVIOUS MONTH TOTAL
			 */
			
			//increase/Decrease
			
			increaseDecreaseColumn[0][1] = Integer.parseInt(checkNullValueJoin(String
					.valueOf(totalByColumn[0][1])))-Integer.parseInt(checkNullValueJoin(String
					.valueOf(totalByColumn[1][1])));
			
			
			increaseDecreaseColumn[0][2] = Integer.parseInt(checkNullValueJoin(String
					.valueOf(totalByColumn[0][2])))-Integer.parseInt(checkNullValueJoin(String
					.valueOf(totalByColumn[1][2])));
			
			
			increaseDecreaseColumn[0][3] = 
				Integer.parseInt(checkNullValueJoin(String.valueOf(totalByColumn[0][3])))-Integer.parseInt(checkNullValueJoin(String.valueOf(totalByColumn[1][3])));
				
							
			increaseDecreaseColumn[0][4] = 
				Integer.parseInt(checkNullValueJoin(String.valueOf(totalByColumn[0][4])))-Integer.parseInt(checkNullValueJoin(String.valueOf(totalByColumn[1][4])));
			
			
			increaseDecreaseColumn[0][5] = 
				formatter.format((Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[0][5])))-Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][5])))));
			
			
			
			increaseDecreaseColumn[0][6] = 
				formatter.format((Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[0][6])))-Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][6])))));
				
						
			increaseDecreaseColumn[0][7] = 
				formatter.format((Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[0][7])))-Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][7])))));
			
			
			increaseDecreaseColumn[0][8] = 
				formatter.format((Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[0][8])))-Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][8])))));
				
			
			
			increaseDecreaseColumn[0][9] = 
				formatter.format((Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[0][9])))-Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][9])))));
				
			
			increaseDecreaseColumn[0][10] = Utility
			.twoDecimals(Double.parseDouble(checkNullValue(String
					.valueOf(totalByColumn[0][10])))-Double.parseDouble(checkNullValue(String
					.valueOf(totalByColumn[1][10]))));
			
			
			increaseDecreaseColumn[0][11] =formatter.format((Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[0][11])))-Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][11])))));
			
			
			increaseDecreaseColumn[0][12] = 
				formatter.format((Double.parseDouble(String.valueOf(totalByColumn[0][12]))-Double.parseDouble(String.valueOf(totalByColumn[1][12]))));
				
			
			
			//increase/Decrease %
			increaseDecreaseColumn[1][1] = "";
			
			increaseDecreaseColumn[1][2] = "";
			
			
			increaseDecreaseColumn[1][3] = "";
			
			double totalStaff=100;
			if(Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][4])))>0.0)
			
				totalStaff = (Double.parseDouble(checkNullValueJoin(String.valueOf(increaseDecreaseColumn[0][4])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][4]))));
						
			increaseDecreaseColumn[1][4] = formatter.format(totalStaff);
			
			double totalsalary=100;
			if(Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][5])))>0.0)
				totalsalary=(Double.parseDouble(checkNullValueJoin(String.valueOf(increaseDecreaseColumn[0][5])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][5]))));
				
			increaseDecreaseColumn[1][5] = formatter.format(totalsalary);
			
			double incArrears=100;
			if(Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][6])))>0.0)
				incArrears= (Double.parseDouble(checkNullValue(String.valueOf(increaseDecreaseColumn[0][6])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][6]))));
			
			
			increaseDecreaseColumn[1][6] = formatter.format(incArrears);
			
			//increaseDecreaseColumn[1][7] = Utility.twoDecimals(Double.parseDouble(checkNullValue(String.valueOf(increaseDecreaseColumn[0][7])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][7]))));
			
			//increaseDecreaseColumn[1][7] = "0.00";
			double incBonus=100;
			if(Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][7])))>0.0)
				incBonus= (Double.parseDouble(checkNullValue(String.valueOf(increaseDecreaseColumn[0][7])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][7]))));
				
			increaseDecreaseColumn[1][7] = formatter.format(incBonus);
			
			//increaseDecreaseColumn[1][8] = Utility.twoDecimals(Double.parseDouble(checkNullValue(String.valueOf(increaseDecreaseColumn[0][8])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][8]))));
			
			increaseDecreaseColumn[1][8] = "0.00";
			
			double totEarnings=100;
			if(Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][9])))>0.0)
				totEarnings= (Double.parseDouble(checkNullValue(String.valueOf(increaseDecreaseColumn[0][9])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][9]))));
			
			
			increaseDecreaseColumn[1][9] = formatter.format(totEarnings);
			
			
			double deduction=100;
			if(Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][10])))>0.0)
				deduction= (Double.parseDouble(checkNullValue(String.valueOf(increaseDecreaseColumn[0][10])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][10]))));
			
			
			increaseDecreaseColumn[1][10] = formatter.format(deduction);
			
			double netPayment=100;
			if(Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][11])))>0.0)
				netPayment= (Double.parseDouble(checkNullValue(String.valueOf(increaseDecreaseColumn[0][11])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][11]))));
			
			
			increaseDecreaseColumn[1][11] = formatter.format(netPayment);
			
			double avgCtcEmp=100;
			if(Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][12])))>0.0)
				avgCtcEmp= (Double.parseDouble(checkNullValue(String.valueOf(increaseDecreaseColumn[0][12])))*100/Double.parseDouble(checkNullValue(String.valueOf(totalByColumn[1][12]))));
		
			
			increaseDecreaseColumn[1][12] = formatter.format(avgCtcEmp);
		}
		
		
		Object[][] separationData = new Object[1][3];
		//separationData[0][0] = " ";
		separationData[0][0] = "Employee Details";
		separationData[0][1] = "Earning and Deduction Details";
		separationData[0][2] = "Net Payment";
		
		int[] cellWidthSeparationHeader = { 36,43,14 };
		int[] cellAlignSeparationHeader = { 1,1,1 };
		TableDataSet separationTableheadingDateData = new TableDataSet();
		separationTableheadingDateData.setBodyBGColor(new BaseColor(200,200,200));
		separationTableheadingDateData.setData(separationData);
		separationTableheadingDateData
				.setCellWidth(cellWidthSeparationHeader);
		separationTableheadingDateData
				.setCellAlignment(cellAlignSeparationHeader);
		separationTableheadingDateData.setBodyFontStyle(1);  
		separationTableheadingDateData.setCellColSpan(new int[]{5,5,3});
		// tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA,
		// 6, Font.NORMAL, new BaseColor(0, 0, 0));
		separationTableheadingDateData.setBorderDetail(3);
		// tableheadingDateData.setHeaderTable(true);
		separationTableheadingDateData.setBlankRowsAbove(1);
		rg.addTableToDoc(separationTableheadingDateData);
		
		String[] header = null;
		header = new String[13];
		header[0] = "Name";
		header[1] = "No of Staff at the Beginning of month ";
		header[2] = "Joining";
		header[3] = "Resignation ";
		header[4] = "Total Staff ";
		header[5] ="Salary";
		header[6] ="Arrears";
		header[7] ="Bonus";
	    header[8] ="Incentive";
		header[9] ="Total Earnings";
		header[10] ="Deduction";
		header[11] ="Net Payment";
		header[12] ="Avg Salary" ;
	
		int[] bcellAlign = new int[header.length];
		int[] bcellWidth = new int[header.length];
		boolean[] bcellwrap = new boolean[header.length];
		
		for (int i = 0; i < header.length; i++) {
			// bcellAlign[i] = 1;
			// bcellWidth[i] = 40;
			if (i == 0) {
				bcellAlign[i] = 0;
				bcellWidth[i] = 40;
				bcellwrap[i]=true;
			} else if (i == 1) {
				bcellAlign[i] = 2;
				bcellWidth[i] = 40;
				bcellwrap[i]=true;
			} else if (i == 2) {
				bcellAlign[i] = 2;
				bcellWidth[i] = 40;
				bcellwrap[i]=true;
			} else if (i == 3) {
				bcellAlign[i] = 2;
				bcellWidth[i] = 40;
				bcellwrap[i]=true;
			}
			else  {
				bcellAlign[i] = 2;
				bcellWidth[i] = 40;
				bcellwrap[i]=true;
			}
		}
											
			TableDataSet subMainTitle = new TableDataSet();
			subMainTitle.setHeader(header);
			//subMainTitle.setHeaderLines(true);
			//subMainTitle.setHeaderBorderColor(new BaseColor(255, 0, 0));
			subMainTitle.setHeaderBorderDetail(3);
			subMainTitle.setHeaderTable(true);
			//subMainTitle.setCellColSpan(new int[]{1});
		//	subMainTitle.setCellNoWrap(bcellwrap);
			subMainTitle.setData(objTotalTabularData);
			subMainTitle.setCellAlignment(bcellAlign);
			subMainTitle.setCellWidth(bcellWidth);
		//	subMainTitle.setHeaderTable(true);
			subMainTitle.setBorderDetail(3);
		

			/*HashMap<String, Object> map = rg.joinTableDataSet(mainTitle,
					subMainTitle, false, 20);*/

			rg.addTableToDoc(subMainTitle);
			rg.seperatorLine();

			
			totalByColumn[0][0] = "Current Month Total";
			totalByColumn[1][0] = "Previous Month Total";

			TableDataSet netEarningDataSet = new TableDataSet();
			netEarningDataSet.setData(totalByColumn);
			 /*netEarningDataSet.setRowSum(new int[] { 2, 3, 4, 5, 6, 7, 8, 9,
			 10, 11, 12, 13, 14 });*/
			netEarningDataSet.setCellAlignment(new int[] { 0, 2, 2, 2, 2, 2, 2,
					2, 2, 2, 2, 2, 2 });
			netEarningDataSet.setCellWidth(new int[] { 50, 30, 40, 40, 40, 40,
					40, 40, 40, 40, 40, 40, 40 });
			netEarningDataSet.setBlankRowsAbove(1);
			netEarningDataSet.setBodyFontStyle(1);
			// netEarningDataSet.setBlankRowsBelow(1);
			netEarningDataSet.setBorderDetail(1);
			//netEarningDataSet.setBorderLines(true);
			rg.addTableToDoc(netEarningDataSet);
			
			
			increaseDecreaseColumn[0][0] = "Increase / Decrease";
			increaseDecreaseColumn[1][0] = "Increase / Decrease in %";

			TableDataSet increaseDecreaseDataSet = new TableDataSet();
			increaseDecreaseDataSet.setData(increaseDecreaseColumn);
			// netEarningDataSet.setRowSum(new int[] { 2, 3, 4, 5, 6, 7, 8, 9,
			// 10, 11, 12, 13, 14 });
			increaseDecreaseDataSet.setCellAlignment(new int[] { 0, 2, 2, 2, 2, 2, 2,
					2, 2, 2, 2, 2, 2 });
			increaseDecreaseDataSet.setCellWidth(new int[] { 50, 30, 40, 40, 40, 40,
					40, 40, 40, 40, 40, 40, 40 });
			increaseDecreaseDataSet.setBlankRowsAbove(1);
			// netEarningDataSet.setBlankRowsBelow(1);
			increaseDecreaseDataSet.setBorderDetail(1);
			increaseDecreaseDataSet.setBodyFontStyle(1);
			//increaseDecreaseDataSet.setBorderLines(true);
			rg.addTableToDoc(increaseDecreaseDataSet);
			
			/*String generatedByQuery = " SELECT TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) FROM HRMS_EMP_OFFC "
					// + " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =
					// HRMS_EMP_OFFC.EMP_RANK)"
					+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + bean.getUserEmpId();

			Object[][] empDataObj = getSqlModel().getSingleResult(
					generatedByQuery);

			if (empDataObj != null && empDataObj.length > 0) {
				TableDataSet generatedByData = new TableDataSet();
				generatedByData.setData(new Object[][] { { "Generated By : "
						+ String.valueOf(empDataObj[0][0]) + " on "
						+ date.toString() } });
				generatedByData.setCellAlignment(new int[] { 0 });
				generatedByData.setCellWidth(new int[] { 100 });
				
				generatedByData.setBorderDetail(0);
				// generatedByData.setBorderDetail(1);
				//generatedByData.setHeaderTable(true);
				generatedByData.setBlankRowsAbove(1);
				rg.addTableToDoc(generatedByData);
				
			}*/
			
		}else {
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			
			noData.setBodyFontDetails(FontFamily.HELVETICA, 7, Font.BOLD, new BaseColor(0, 0, 0));
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}	
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rg;
	}

}
