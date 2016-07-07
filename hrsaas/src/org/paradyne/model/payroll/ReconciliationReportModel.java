package org.paradyne.model.payroll;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lsmp.djep.xjep.function.FromBase;
import org.paradyne.bean.payroll.ReconciliationReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;

import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font.FontFamily;

/**
 * @author aa1385 Date:30-11-2011
 */
public class ReconciliationReportModel extends ModelBase {
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	/**
	 * report type Pdf.
	 */
	public static final String REPORT_TYPE_PDF = "Pdf";
	/**
	 * Log4j logger.
	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ReconciliationReportModel.class);

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

	public void getReport(ReconciliationReportBean bean,
			HttpServletResponse response, HttpServletRequest request,
			String reportPath) {

		try {

			ReportDataSet rds = new ReportDataSet();

			final String month = bean.getMonth(); // month
			// String prevMonth = ""+(Integer.parseInt(month)-1);
			// System.out.println("prevMonth=="+prevMonth);
			final String year = bean.getYear(); // year
			String type = bean.getReport();
			rds.setReportType(type);
			String fileName = "Direct Reconciliation Report" + Utility.getRandomNumber(1000);
			String reportPathName = reportPath + fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("Direct Reconciliation Report");
			rds.setUserEmpId(bean.getUserEmpId());
			//rds.setPageSize("A4");
			rds.setPageSize("TABLOID");
			rds.setPageOrientation("landscape");
			//rds.setTotalColumns(40);
			/*
			 * rds.setMarginBottom(25f); rds.setMarginLeft(25f);
			 * rds.setMarginRight(25f);
			 */
			rds.setShowPageNo(true);
			// rds.setMarginTop(25f);
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
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action",
						"ReconciliationReport_input.action");
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

	private org.paradyne.lib.ireportV2.ReportGenerator getReport(
			org.paradyne.lib.ireportV2.ReportGenerator rg,
			ReconciliationReportBean bean) {

		try {

			String title = " Direct Reconciliation Report  ";
			Date date = new Date();
			final String month = bean.getMonth(); // month
			final String year = bean.getYear(); // year
			final String prevMonth = bean.getPrevMonth(); // previous month
			final String prevYear = bean.getPrevYear(); // previous year
			String filters ="";
			/* Setting filter details */
			/*String filters = "Period : "
					+ Utility.month(Integer.parseInt(month)) + " " + year + "";*/

			if (!bean.getDivName().equals("")) {
				filters += "\n\nDivision: " + bean.getDivName();
			}

			if (!bean.getBranchName().equals("")) {
				filters += "\n\nBranch: " + bean.getBranchName();
			}

			if (!bean.getDeptName().equals("")) {
				filters += "\n\nDepartment: " + bean.getDeptName();
			}

			if (!bean.getPaybillName().equals("")) {
				filters += "\n\nPaybill: " + bean.getPaybillName();
			}

			if (!bean.getTypeName().equals("")) {
				filters += "\n\nEmployee Type: " + bean.getTypeName();
			}
			if (!bean.getEmpName().equals("")) {
				filters += "\n\nEmployee Name: " + bean.getEmpName();
			}
			if (!bean.getCadreName().equals("")) {
				filters += "\n\nGrade: " + bean.getCadreName();
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBodyFontStyle(1);
			//filterData.setCellColSpan(new int[]{45});
			// filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 8,
			// Font.NORMAL, new BaseColor(0, 0, 0));
			filterData.setBorder(false);
			// filterData.setBlankRowsBelow(1);
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
			if (!bean.getEmpName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year + ".EMP_ID IN ( "
						+ bean.getEmpId()+") ";
				System.out.println("+bean.getEmpCode() =" + bean.getEmpId());
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
			if (!bean.getTypeName().equals("")) {
				whereClause += " AND HRMS_SALARY_" + year + ".SAL_EMP_TYPE IN ( "
						+ bean.getTypeCode()+") ";
				System.out
						.println("+bean.getTypeCode() =" + bean.getTypeCode());
			}

			// whereClause +=" ORDER BY SAL_LEDGER_CODE DESC";
			
			
			String prevWhereClause = "";

			if (!bean.getBranchName().equals("")) {
				prevWhereClause += " AND HRMS_SALARY_" + prevYear + ".SAL_EMP_CENTER IN ("
						+ bean.getBranchCode()+") ";
				System.out.println("+bean.getBranchCode() ="
						+ bean.getBranchCode());
			}
			if (!bean.getDivName().equals("")) {
				prevWhereClause += " AND HRMS_SALARY_" + prevYear + ".SAL_DIVISION IN ( "+ bean.getDivCode()+") ";
				System.out.println("+bean.getDivId() =" + bean.getDivCode());
			}
			if (!bean.getDeptName().equals("")) {
				prevWhereClause += " AND HRMS_SALARY_" + prevYear + ".SAL_DEPT IN ( "
						+ bean.getDeptCode()+") ";
				System.out.println("+bean.getDeptId() =" + bean.getDeptCode());
			}
			if (!bean.getEmpName().equals("")) {
				prevWhereClause += " AND HRMS_SALARY_" + prevYear + ".EMP_ID IN ( "
						+ bean.getEmpId()+") ";
				System.out.println("+bean.getEmpCode() =" + bean.getEmpId());
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
			if (!bean.getTypeName().equals("")) {
				prevWhereClause += " AND HRMS_SALARY_" + prevYear + ".SAL_EMP_TYPE IN ( "
						+ bean.getTypeCode()+") ";
				System.out
						.println("+bean.getTypeCode() =" + bean.getTypeCode());
			}
			

			// Current Month
			String currentmonthQuery = " SELECT HRMS_SALARY_LEDGER.LEDGER_CODE, HRMS_SALARY_LEDGER.LEDGER_MONTH, HRMS_SALARY_LEDGER.LEDGER_YEAR , "
					+ "HRMS_SALARY_LEDGER.LEDGER_DIVISION FROM HRMS_SALARY_LEDGER WHERE HRMS_SALARY_LEDGER.LEDGER_MONTH = "
					+ bean.getMonth()
					+ " AND HRMS_SALARY_LEDGER.LEDGER_YEAR = "
					+ bean.getYear()
					+ " AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN ( " + bean.getDivCode()+")";

			Object[][] expDetail = getSqlModel().getSingleResult(
					currentmonthQuery);

			// Previous Month
			String prevMonthQuery = " SELECT HRMS_SALARY_LEDGER.LEDGER_CODE, HRMS_SALARY_LEDGER.LEDGER_MONTH, HRMS_SALARY_LEDGER.LEDGER_YEAR , "
					+ "HRMS_SALARY_LEDGER.LEDGER_DIVISION FROM HRMS_SALARY_LEDGER WHERE HRMS_SALARY_LEDGER.LEDGER_MONTH = "
					+ prevMonth
					+ " AND HRMS_SALARY_LEDGER.LEDGER_YEAR = "
					+ bean.getPrevYear()
					+ " AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN ( " + bean.getDivCode()+")";

			Object[][] prevMonthDetail = getSqlModel().getSingleResult(
					prevMonthQuery);
			System.out.println("prevMonthDetail.lenght=="
					+ prevMonthDetail.length);

			String creditTypeQuery = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, HRMS_CREDIT_HEAD.CREDIT_NAME, HRMS_CREDIT_HEAD.CREDIT_ABBR FROM HRMS_CREDIT_HEAD  WHERE HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' ";
			// creditTypeQuery += " WHERE LEAVE_ID=" + expDetail[0][0];
			// for credit type
			Object[][] creditDetail = getSqlModel().getSingleResult(
					creditTypeQuery);

			String debitTypeQuery = "SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, HRMS_DEBIT_HEAD.DEBIT_NAME, HRMS_DEBIT_HEAD.DEBIT_ABBR FROM HRMS_DEBIT_HEAD WHERE HRMS_DEBIT_HEAD.DEBIT_PAYFLAG ='Y'";
			debitTypeQuery += " ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE ";
			// for credit type
			Object[][] debitDetail = getSqlModel().getSingleResult(
					debitTypeQuery);

			// if (expDetail != null && expDetail.length > 0) {
			// For Current Month ::
			String empTypeQuery = "SELECT HRMS_SALARY_"+ year+ ".EMP_ID ,HRMS_SALARY_"+ year+ ".SAL_LEDGER_CODE ,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , HRMS_CENTER.CENTER_NAME,HRMS_DEPT.DEPT_NAME,HRMS_SALARY_"+ year+ ".SAL_TOTAL_CREDIT ,HRMS_SALARY_"+ year+ ".SAL_TOTAL_DEBIT,HRMS_SALARY_"+ year+ ".SAL_NET_SALARY,HRMS_EMP_OFFC.EMP_CENTER,HRMS_EMP_OFFC.EMP_DEPT  "
					+ "FROM HRMS_SALARY_"+ year+ " "
					+ "INNER join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"+ year+ ".EMP_ID) "
					+ "left join HRMS_CENTER on(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ "left join HRMS_DEPT on(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ " WHERE HRMS_SALARY_"+ year+ ".SAL_MONTH = " + bean.getMonth()
					+ " AND   HRMS_SALARY_"+ year+ ".SAL_YEAR=" + year+ " "
					+" AND HRMS_SALARY_" + year+ ".EMP_ID IN (SELECT HRMS_SALARY_" + prevYear+ ".EMP_ID  "
					+" FROM HRMS_SALARY_" + prevYear+ " "
					+" INNER join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_" + prevYear+ ".EMP_ID) "
					+" left join HRMS_CENTER on(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+" left join HRMS_DEPT on(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " +
							"WHERE HRMS_SALARY_"+ prevYear+ ".SAL_MONTH = "+prevMonth+" AND HRMS_SALARY_"+ prevYear+ ".SAL_YEAR=" + prevYear+ " AND HRMS_SALARY_" + prevYear + ".SAL_DIVISION IN ( "+ bean.getDivCode()+")   )";

			empTypeQuery += whereClause;

			// for emp type
			Object[][] empTypeDetail = getSqlModel().getSingleResult(
					empTypeQuery);
			// For Previous month::
			/*String prevMonthTypeQuery = "SELECT HRMS_SALARY_"+ prevYear+ ".EMP_ID ,HRMS_SALARY_"+ prevYear+ ".SAL_LEDGER_CODE ,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,HRMS_SALARY_"+ prevYear+ ".SAL_TOTAL_CREDIT ,HRMS_SALARY_"+ prevYear+ ".SAL_TOTAL_DEBIT,HRMS_SALARY_"+ prevYear+ ".SAL_NET_SALARY ,HRMS_EMP_OFFC.EMP_CENTER, HRMS_CENTER.CENTER_NAME,HRMS_EMP_OFFC.EMP_DEPT,HRMS_DEPT.DEPT_NAME "
					+ "FROM HRMS_SALARY_"+ prevYear+ " "
					+ "INNER join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"+ prevYear+ ".EMP_ID) "
					+ "left join HRMS_CENTER on(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ "left join HRMS_DEPT on(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ "WHERE HRMS_SALARY_"+ prevYear+ ".SAL_MONTH = " + bean.getPrevMonth()
					+ " AND HRMS_SALARY_"+ prevYear+ ".SAL_YEAR=" + bean.getPrevYear();
			
			prevMonthTypeQuery += prevWhereClause;

			// for emp type
			Object[][] prevMonthTypeDetail = getSqlModel().getSingleResult(
					prevMonthTypeQuery);*/

			// current credit month
			String creditHead = "SELECT  DISTINCT  HRMS_CREDIT_HEAD.CREDIT_CODE, HRMS_CREDIT_HEAD.CREDIT_NAME, HRMS_CREDIT_HEAD.CREDIT_ABBR FROM HRMS_CREDIT_HEAD "
					+ "INNER JOIN HRMS_SAL_CREDITS_"+ year+ " ON(HRMS_SAL_CREDITS_"	+ year+ ".SAL_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)"
					+ "INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"	+ year+ ".SAL_LEDGER_CODE) "
					+ " WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "+bean.getDivCode()+" ) AND HRMS_SAL_CREDITS_"+ year+ ".SAL_MONTH=" + month + " AND HRMS_SAL_CREDITS_"+ year+ ".SAL_AMOUNT>0 ";
			Object[][] creditHeadObj = getSqlModel()
					.getSingleResult(creditHead);

			/*// previous credit month
			String prevCreditHead = "SELECT  DISTINCT  HRMS_CREDIT_HEAD.CREDIT_CODE, HRMS_CREDIT_HEAD.CREDIT_NAME, HRMS_CREDIT_HEAD.CREDIT_ABBR FROM HRMS_CREDIT_HEAD "
					+ "INNER JOIN HRMS_SAL_CREDITS_"+ prevYear+ " ON(HRMS_SAL_CREDITS_"+ prevYear+ ".SAL_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)"
					+ " WHERE HRMS_SAL_CREDITS_"+ prevYear+ ".SAL_MONTH=" + prevMonth + " AND HRMS_SAL_CREDITS_"+ prevYear+ ".SAL_AMOUNT>0 ";
			Object[][] prevCreditHeadObj = getSqlModel().getSingleResult(
					prevCreditHead);*/

			// current emp credit month
			String creditEmpQuery = "SELECT  HRMS_SAL_CREDITS_"+ year	+ ".EMP_ID, HRMS_SAL_CREDITS_"+ year	+ ".SAL_CREDIT_CODE, HRMS_SAL_CREDITS_"+ year	+ ".SAL_AMOUNT FROM HRMS_CREDIT_HEAD "
					+ "	INNER JOIN HRMS_SAL_CREDITS_"+ year	+ " ON(HRMS_SAL_CREDITS_"+ year	+ ".SAL_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)"
					+ "	 WHERE HRMS_SAL_CREDITS_"+ year	+ ".SAL_MONTH=" + bean.getMonth();
			Object[][] creditEmpObj = getSqlModel().getSingleResult(
					creditEmpQuery);
			HashMap<String, String> creditMap = new HashMap<String, String>();

			// previous emp credit month
			String prevCreditEmpQuery = "SELECT  HRMS_SAL_CREDITS_"+ prevYear	+ ".EMP_ID, HRMS_SAL_CREDITS_"+ prevYear	+ ".SAL_CREDIT_CODE, HRMS_SAL_CREDITS_"+ prevYear	+ ".SAL_AMOUNT FROM HRMS_CREDIT_HEAD "
					+ "	INNER JOIN HRMS_SAL_CREDITS_"+ prevYear	+ " ON(HRMS_SAL_CREDITS_"+ prevYear	+ ".SAL_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)"
					+ "	 WHERE HRMS_SAL_CREDITS_"+ prevYear	+ ".SAL_MONTH=" + bean.getPrevMonth();
			Object[][] prevCreditEmpObj = getSqlModel().getSingleResult(
					prevCreditEmpQuery);
			HashMap<String, String> prevCreditMap = new HashMap<String, String>();

			if (creditEmpObj != null && creditEmpObj.length > 0) {
				for (int i = 0; i < creditEmpObj.length; i++) {
					creditMap.put(String.valueOf(creditEmpObj[i][0]) + "#"
							+ String.valueOf(creditEmpObj[i][1]), String
							.valueOf(creditEmpObj[i][2]));
				}

			}

			if (prevCreditEmpObj != null && prevCreditEmpObj.length > 0) {
				for (int i = 0; i < prevCreditEmpObj.length; i++) {
					prevCreditMap.put(String.valueOf(prevCreditEmpObj[i][0])
							+ "#" + String.valueOf(prevCreditEmpObj[i][1]),
							String.valueOf(prevCreditEmpObj[i][2]));
				}

			}
			
		

			// current debit month
			String debitHead = "SELECT DISTINCT  HRMS_DEBIT_HEAD.DEBIT_CODE, HRMS_DEBIT_HEAD.DEBIT_NAME, HRMS_DEBIT_HEAD.DEBIT_ABBR FROM HRMS_DEBIT_HEAD "
					+ " INNER JOIN HRMS_SAL_DEBITS_"+ year+ " ON(HRMS_SAL_DEBITS_"+ year+ ".SAL_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)"
					+ "INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"	+ year+ ".SAL_LEDGER_CODE) "
					+ " WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "+bean.getDivCode()+" ) AND HRMS_SAL_DEBITS_"+ year+ ".SAL_MONTH =" + month + " AND HRMS_SAL_DEBITS_"+ year+ ".SAL_AMOUNT>0 ";
			Object[][] debitHeadObj = getSqlModel().getSingleResult(debitHead);

			// previous debit month
			String prevDebitHead = "SELECT DISTINCT  HRMS_DEBIT_HEAD.DEBIT_CODE, HRMS_DEBIT_HEAD.DEBIT_NAME, HRMS_DEBIT_HEAD.DEBIT_ABBR FROM HRMS_DEBIT_HEAD "
					+ " INNER JOIN HRMS_SAL_DEBITS_"+ prevYear+ " ON(HRMS_SAL_DEBITS_"+ prevYear+ ".SAL_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)"
					+ "INNER JOIN  HRMS_SALARY_LEDGER ON ( HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"	+ prevYear+ ".SAL_LEDGER_CODE) "
					+ " WHERE  HRMS_SALARY_LEDGER.LEDGER_DIVISION IN( "+bean.getDivCode()+" ) AND HRMS_SAL_DEBITS_"+ prevYear+ ".SAL_MONTH =" + prevMonth + " AND HRMS_SAL_DEBITS_"+ prevYear+ ".SAL_AMOUNT>0 ";
			Object[][] prevDebitHeadObj = getSqlModel().getSingleResult(
					prevDebitHead);

			// current emp debit month
			String debitEmpQuery = "SELECT  HRMS_SAL_DEBITS_"+ year+ ".EMP_ID, HRMS_SAL_DEBITS_"+ year+ ".SAL_DEBIT_CODE, HRMS_SAL_DEBITS_"+ year+ ".SAL_AMOUNT FROM HRMS_DEBIT_HEAD "
					+ "	INNER JOIN HRMS_SAL_DEBITS_"+ year+ " ON(HRMS_SAL_DEBITS_"+ year+ ".SAL_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)"
					+ "	 WHERE HRMS_SAL_DEBITS_"+ year+ ".SAL_MONTH=" + bean.getMonth();
			Object[][] debitEmpObj = getSqlModel().getSingleResult(
					debitEmpQuery);
			HashMap<String, String> debitMap = new HashMap<String, String>();

			// previous emp debit month
			String prevDebitEmpQuery = "SELECT  HRMS_SAL_DEBITS_"+ prevYear+ ".EMP_ID, HRMS_SAL_DEBITS_"+ prevYear+ ".SAL_DEBIT_CODE, HRMS_SAL_DEBITS_"+ prevYear+ ".SAL_AMOUNT FROM HRMS_DEBIT_HEAD "
					+ "	INNER JOIN HRMS_SAL_DEBITS_"+ prevYear+ " ON(HRMS_SAL_DEBITS_"+ prevYear+ ".SAL_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)"
					+ "	 WHERE HRMS_SAL_DEBITS_"+ prevYear+ ".SAL_MONTH=" + prevMonth;
			Object[][] prevDebitEmpObj = getSqlModel().getSingleResult(
					prevDebitEmpQuery);
			HashMap<String, String> prevDebitMap = new HashMap<String, String>();

			if (debitEmpObj != null && debitEmpObj.length > 0) {
				for (int i = 0; i < debitEmpObj.length; i++) {
					debitMap.put(String.valueOf(debitEmpObj[i][0]) + "#"
							+ String.valueOf(debitEmpObj[i][1]), String
							.valueOf(debitEmpObj[i][2]));
				}

			}

			if (prevDebitEmpObj != null && prevDebitEmpObj.length > 0) {
				for (int i = 0; i < prevDebitEmpObj.length; i++) {
					prevDebitMap.put(String.valueOf(prevDebitEmpObj[i][0])
							+ "#" + String.valueOf(prevDebitEmpObj[i][1]),
							String.valueOf(prevDebitEmpObj[i][2]));
				}

			}

			// For New Joinee ::
			String newJoineeQuery = "SELECT HRMS_SALARY_"+ year+ ".EMP_ID ,HRMS_SALARY_"+ year+ ".SAL_LEDGER_CODE ,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , HRMS_CENTER.CENTER_NAME,HRMS_DEPT.DEPT_NAME,HRMS_SALARY_"+ year+ ".SAL_TOTAL_CREDIT ,HRMS_SALARY_"+ year+ ".SAL_TOTAL_DEBIT,HRMS_SALARY_"+ year+ ".SAL_NET_SALARY,HRMS_EMP_OFFC.EMP_CENTER,HRMS_EMP_OFFC.EMP_DEPT  "
					+ "FROM HRMS_SALARY_"+ year+ " "
					+ "INNER join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"+ year+ ".EMP_ID) "
					+ "left join HRMS_CENTER on(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ "left join HRMS_DEPT on(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ " WHERE HRMS_SALARY_"+ year+ ".SAL_MONTH = "
					+ bean.getMonth()
					+ " AND HRMS_SALARY_"+ year+ ".SAL_YEAR="
					+ year
					+ " "
					+ "AND HRMS_SALARY_"
					+ year
					+ ".EMP_ID NOT IN( SELECT HRMS_SALARY_"
					+ prevYear
					+ ".EMP_ID "
					+ "FROM HRMS_SALARY_"
					+ prevYear
					+ " "
					+ "INNER join HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"
					+ prevYear
					+ ".EMP_ID) "
					+ "left join HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ "left join HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ "WHERE HRMS_SALARY_"+ prevYear+ ".SAL_MONTH = " + prevMonth + "  AND HRMS_SALARY_"+ prevYear+ ".SAL_YEAR="
					+ prevYear + " AND HRMS_SALARY_" + prevYear + ".SAL_DIVISION IN ( "+ bean.getDivCode()+")   ) ";

			newJoineeQuery += whereClause;

			Object[][] newJoineeDetailObj = getSqlModel().getSingleResult(
					newJoineeQuery);

			// For Separation Details ::
			String separationQuery = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , HRMS_CENTER.CENTER_NAME,HRMS_DEPT.DEPT_NAME, HRMS_SALARY_"
					+ prevYear
					+ ".EMP_ID ,HRMS_SALARY_"+ prevYear	+ ".SAL_LEDGER_CODE ,HRMS_SALARY_"+ prevYear	+ ".SAL_TOTAL_CREDIT ,HRMS_SALARY_"+ prevYear	+ ".SAL_TOTAL_DEBIT,HRMS_SALARY_"+ prevYear	+ ".SAL_NET_SALARY,HRMS_EMP_OFFC.EMP_CENTER,HRMS_EMP_OFFC.EMP_DEPT  "
					+ "FROM HRMS_SALARY_"+ prevYear	+ " "
					+ "INNER join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"
					+ prevYear
					+ ".EMP_ID) "
					+ "left join HRMS_CENTER on(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ "left join HRMS_DEPT on(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ " WHERE HRMS_SALARY_"+ prevYear	+ ".SAL_MONTH = "
					+ prevMonth
					+ " AND HRMS_SALARY_"+ prevYear	+ ".SAL_YEAR="
					+ prevYear
					+ " "
					+ "AND HRMS_SALARY_"
					+ prevYear
					+ ".EMP_ID NOT IN( SELECT HRMS_SALARY_"
					+ year
					+ ".EMP_ID "
					+ "FROM HRMS_SALARY_"
					+ year
					+ " "
					+ "INNER join HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"
					+ year
					+ ".EMP_ID) "
					+ "left join HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ "left join HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ "WHERE HRMS_SALARY_"+ year	+ ".SAL_MONTH = "
					+ bean.getMonth()
					+ "  AND HRMS_SALARY_"+ year	+ ".SAL_YEAR=" + year + " AND HRMS_SALARY_" + year + ".SAL_DIVISION IN ( "+ bean.getDivCode()+")   ) ";

			separationQuery += prevWhereClause;

			Object[][] separationDetailObj = getSqlModel().getSingleResult(
					separationQuery);

			String[] header = null;

			header = new String[creditHeadObj.length + debitHeadObj.length + 5];
			int[] bcellAlign = new int[header.length];
			int[] bcellWidth = new int[header.length];
			boolean[] bcellwrap = new boolean[header.length];
		//	header[0] = "Sr. No.";
			header[0] = "Employee ID";
			header[1] = "Employee Name";
			//header[2] = "Branch";
		//	header[3] = "Department";

			int count = 2;
			for (int i = 0; i < creditHeadObj.length; i++) {
				header[count] = String.valueOf(creditHeadObj[i][2]);
				bcellwrap[count]=false;
				count++;
			}
			header[count] = "Gross Income";
			bcellWidth[count] = 40;
			count++;
			for (int j = 0; j < debitHeadObj.length; j++) {
				header[count] = String.valueOf(debitHeadObj[j][2]);
				bcellwrap[count]=true;
				count++;

			}
			header[count] = "Gross Deductions";
			bcellWidth[count] = 50;
			bcellwrap[count]=true;
			count++;
			header[count] = "Net Pay";
			bcellWidth[count] = 40;
			bcellwrap[count]=true;

			for (int i = 0; i < header.length; i++) {
				
				if (i == 0) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 40;
					bcellwrap[i]=false;
				}else if (i == 1) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 40;
					bcellwrap[i]=false;
				}else {
					bcellAlign[i] = 2;
					bcellWidth[i] = 30;
					bcellwrap[i]=true;
				}
			}

			Object[][] objTotalTabularData = new Object[empTypeDetail.length][creditHeadObj.length
					+ debitHeadObj.length + 5];

			Object[][] newJoinObjTotalTabularData = new Object[newJoineeDetailObj.length][creditHeadObj.length
					+ debitHeadObj.length + 5];

			Object[][] totalByColumn = new Object[1][creditHeadObj.length
					+ debitHeadObj.length + 5];

			Object[][] newJoinTotalByColumn = new Object[1][creditHeadObj.length
					+ debitHeadObj.length + 5];

			int countSr = 1;
			int countNewJoin = 1;
			

	/*Arresrs Credit Details start*/
			
			// current month - arrears credit details
			String arrEmpQuery = "SELECT  HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID, HRMS_ARREARS_CREDIT_"+year+".ARREARS_CREDIT_CODE, HRMS_ARREARS_CREDIT_"+year+".ARREARS_AMT "
							+" FROM HRMS_CREDIT_HEAD "
							+" INNER JOIN HRMS_ARREARS_CREDIT_"+year+"  ON(HRMS_ARREARS_CREDIT_"+year+".ARREARS_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)"
							+"  WHERE HRMS_ARREARS_CREDIT_"+ year+ ".ARREARS_PAY_TYPE='A' AND  HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE IN( "
							+"	SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+month 
							+"	AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="+year+" AND HRMS_ARREARS_LEDGER.ARREARS_DIVISION="+bean.getDivCode()+")";
			Object[][] arrEmpObj = getSqlModel().getSingleResult(
					arrEmpQuery);
			HashMap<String, String> arrMap = new HashMap<String, String>();
			
			
			// previous month - arrears details
			String arrEmpPrevQuery = "SELECT  HRMS_ARREARS_CREDIT_"+prevYear+".ARREARS_EMP_ID, HRMS_ARREARS_CREDIT_"+prevYear+".ARREARS_CREDIT_CODE, HRMS_ARREARS_CREDIT_"+prevYear+".ARREARS_AMT "
							+" FROM HRMS_CREDIT_HEAD "
							+" INNER JOIN HRMS_ARREARS_CREDIT_"+prevYear+"  ON(HRMS_ARREARS_CREDIT_"+prevYear+".ARREARS_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)"
							+"  WHERE HRMS_ARREARS_CREDIT_"+ prevYear+ ".ARREARS_PAY_TYPE='A' AND  HRMS_ARREARS_CREDIT_"+prevYear+".ARREARS_CODE IN( "
							+"	SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+prevMonth 
							+"	AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="+prevYear+" AND HRMS_ARREARS_LEDGER.ARREARS_DIVISION="+bean.getDivCode()+")";
			Object[][] arrEmpPrevObj = getSqlModel().getSingleResult(
					arrEmpPrevQuery);
			HashMap<String, String> arrPrevMap = new HashMap<String, String>();
			
			
			if (arrEmpObj != null && arrEmpObj.length > 0) {
				for (int i = 0; i < arrEmpObj.length; i++) {
					arrMap.put(String.valueOf(arrEmpObj[i][0]) + "#"
							+ String.valueOf(arrEmpObj[i][1]), String
							.valueOf(arrEmpObj[i][2]));
				}

			}

			if (arrEmpPrevObj != null && arrEmpPrevObj.length > 0) {
				for (int i = 0; i < arrEmpPrevObj.length; i++) {
					arrPrevMap.put(String.valueOf(arrEmpPrevObj[i][0])
							+ "#" + String.valueOf(arrEmpPrevObj[i][1]),
							String.valueOf(arrEmpPrevObj[i][2]));
				}

			}
			
			/*Arresrs Credit Details end*/
			
			/*Arresrs Debit Details start*/
			
			// current month - arrears credit details
			String arrEmpDbtQuery = "SELECT  HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID, HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE, HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT "
							+" FROM HRMS_DEBIT_HEAD "
							+" INNER JOIN HRMS_ARREARS_DEBIT_"+year+"  ON(HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)"
							+"  WHERE HRMS_ARREARS_DEBIT_"+ year+ ".ARREARS_PAY_TYPE='A' AND  HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE IN( "
							+"	SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+month 
							+"	AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="+year+" AND HRMS_ARREARS_LEDGER.ARREARS_DIVISION="+bean.getDivCode()+")";
			Object[][] arrEmpDbtObj = getSqlModel().getSingleResult(
					arrEmpDbtQuery);
			HashMap<String, String> arrDbtMap = new HashMap<String, String>();
			
			// previous month - arrears details
			
			String arrEmpDbtPrevQuery = "SELECT  HRMS_ARREARS_DEBIT_"+prevYear+".ARREARS_EMP_ID, HRMS_ARREARS_DEBIT_"+prevYear+".ARREARS_DEBIT_CODE, HRMS_ARREARS_DEBIT_"+prevYear+".ARREARS_AMT "
					+" FROM HRMS_DEBIT_HEAD "
					+" INNER JOIN HRMS_ARREARS_DEBIT_"+prevYear+"  ON(HRMS_ARREARS_DEBIT_"+prevYear+".ARREARS_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)"
					+"  WHERE HRMS_ARREARS_DEBIT_"+ prevYear+ ".ARREARS_PAY_TYPE='A' AND  HRMS_ARREARS_DEBIT_"+prevYear+".ARREARS_CODE IN( "
							+"	SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+prevMonth 
							+"	AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="+prevYear+" AND HRMS_ARREARS_LEDGER.ARREARS_DIVISION="+bean.getDivCode()+")";
				
			Object[][] arrEmpDbtPrevObj = getSqlModel().getSingleResult(
						arrEmpDbtPrevQuery);
			HashMap<String, String> arrDbtPrevMap = new HashMap<String, String>();

			if (arrEmpDbtObj != null && arrEmpDbtObj.length > 0) {
				for (int i = 0; i < arrEmpDbtObj.length; i++) {
					arrDbtMap.put(String.valueOf(arrEmpDbtObj[i][0]) + "#"
							+ String.valueOf(arrEmpDbtObj[i][1]), String
							.valueOf(arrEmpDbtObj[i][2]));
				}

			}

			if (arrEmpDbtPrevObj != null && arrEmpDbtPrevObj.length > 0) {
				for (int i = 0; i < arrEmpDbtPrevObj.length; i++) {
					arrDbtPrevMap.put(String.valueOf(arrEmpDbtPrevObj[i][0])
							+ "#" + String.valueOf(arrEmpDbtPrevObj[i][1]),
							String.valueOf(arrEmpDbtPrevObj[i][2]));
				}

			}



			
			/*Arresrs Debit Details end*/
			
			
			
		//	if (empTypeDetail != null && empTypeDetail.length > 0) {	
			
			if (empTypeDetail != null && empTypeDetail.length > 0) {

				for (int j = 0; j < empTypeDetail.length; j++) {
					//objTotalTabularData[j][0] = countSr++;// token
					objTotalTabularData[j][0] = empTypeDetail[j][2]; // emp
																		// token
					objTotalTabularData[j][1] = empTypeDetail[j][3]; // emp
																		// name
					//objTotalTabularData[j][2] = empTypeDetail[j][4]; // branch
					//objTotalTabularData[j][3] = empTypeDetail[j][5]; // department
					int COUNT = 2;
					double totalCredit = 0.0;

					double creditValueTotal = 0.0;
					double crdValueTotal = 0.0;

					for (int i = 0; i < creditHeadObj.length; i++) {
						objTotalTabularData[j][COUNT] = "0.0";

						String key = empTypeDetail[j][0] + "#"
								+ creditHeadObj[i][0];
						String prevKey = empTypeDetail[j][0] + "#"
								+ creditHeadObj[i][0];

						String value = creditMap.get(key);
						String prevCreditValue = prevCreditMap.get(prevKey);
						
						
						String arrearCurrent = arrMap.get(key);
						String arreraPrevCreditValue = arrPrevMap.get(prevKey);
						double currValue=0.0;
						double prevValue=0.0;
						double currCrdValue=0.0;
						double prevCrdValue=0.0;
						
						if (arrearCurrent != null) {
							currValue=Double.parseDouble(arrearCurrent);
							
						}
						if (arreraPrevCreditValue != null) {
							prevValue=Double.parseDouble(arreraPrevCreditValue);
							
						}
						
						if (value != null) {
							currCrdValue=Double.parseDouble(value);
							
						}
						if (prevCreditValue != null) {
							prevCrdValue=Double.parseDouble(prevCreditValue);
							
						}
						
							objTotalTabularData[j][COUNT] = formatter
									.format((currValue+currCrdValue)-(prevValue+prevCrdValue));
							totalCredit += (currValue+currCrdValue)-(prevValue+prevCrdValue);

							// total credit
							creditValueTotal = Double
									.parseDouble(checkNullValue(String
											.valueOf(totalByColumn[0][COUNT])));
							crdValueTotal = Double
									.parseDouble(checkNullValue(String
											.valueOf(objTotalTabularData[j][COUNT])));
							totalByColumn[0][COUNT] = formatter.format(creditValueTotal
									+ crdValueTotal);
							
					
						COUNT++;
					}

					objTotalTabularData[j][COUNT] = formatter.format(Double
							.parseDouble(String.valueOf(totalCredit)));// TOTAL
																		// CRDIT



					totalByColumn[0][COUNT] = formatter.format(Double
							.parseDouble(checkNullValue(String
									.valueOf(totalByColumn[0][COUNT])))
							+ Double.parseDouble(checkNullValue(String
									.valueOf(objTotalTabularData[j][COUNT]))));
					
					COUNT++;

					double totalDebit = 0.0;
					double crValueTotal = 0.0;
					double drValueTotal = 0.0;
					for (int i = 0; i < debitHeadObj.length; i++) {
						objTotalTabularData[j][COUNT] = "0.0";

						String key = empTypeDetail[j][0] + "#"
								+ debitHeadObj[i][0];
						String prevKey = empTypeDetail[j][0] + "#"
								+ debitHeadObj[i][0];

						String value = debitMap.get(key);
						String prevDebitValue = prevDebitMap.get(prevKey);

						
						String arrearDebitCurrent = arrDbtMap.get(key);
						String arreraPrevDebitValue = arrDbtPrevMap.get(prevKey);
						
						
						double currValue=0.0;
						double prevValue=0.0;
						
						double currCrdValue=0.0;
						double prevCrdValue=0.0;
						
						
						if (arrearDebitCurrent != null) {
							currValue=Double.parseDouble(arrearDebitCurrent);
							
						}
						if (arreraPrevDebitValue != null) {
							prevValue=Double.parseDouble(arreraPrevDebitValue);
							
						}
						
						
						if (value != null) {
							currCrdValue=Double.parseDouble(value);
							
						}
						if (prevDebitValue != null) {
							prevCrdValue=Double.parseDouble(prevDebitValue);
							
						}
						
						if (value != null) {
							objTotalTabularData[j][COUNT] = formatter
									.format((currValue+currCrdValue)-(prevValue+prevCrdValue));
							totalDebit += (currValue+currCrdValue)-(prevValue+prevCrdValue);

							// total debit
							crValueTotal = Double
									.parseDouble(checkNullValue(String
											.valueOf(totalByColumn[0][COUNT])));
							drValueTotal = Double
									.parseDouble(checkNullValue(String
											.valueOf(objTotalTabularData[j][COUNT])));
							totalByColumn[0][COUNT] = formatter.format(crValueTotal
									+ drValueTotal);

						}
						COUNT++;
					}
					objTotalTabularData[j][COUNT] = formatter.format(Double
							.parseDouble(String.valueOf(totalDebit)));// TOTAL
																		// DEBIT

					totalByColumn[0][COUNT] = formatter.format(Double
							.parseDouble(checkNullValue(String
									.valueOf(totalByColumn[0][COUNT])))
							+ Double.parseDouble(checkNullValue(String
									.valueOf(objTotalTabularData[j][COUNT]))));

					COUNT++;
					objTotalTabularData[j][COUNT] = formatter.format(totalCredit - totalDebit);

					//Net Total i.e (totalCredit - totalDebit)
					totalByColumn[0][COUNT] =  formatter.format(Double
							.parseDouble(checkNullValue(String
									.valueOf(totalByColumn[0][COUNT])))
							+ Double.parseDouble(checkNullValue(String
									.valueOf(objTotalTabularData[j][COUNT]))));

				}

				
				Object recBetObj[][] = new Object[1][1];

				recBetObj[0][0] = "Reconciliation details  of "+ Utility.month(Integer.parseInt(month)) + " "
				+ year + " in comparision with " + Utility.month(Integer.parseInt(prevMonth))
				+ " " + prevYear + " ";
				/*recBetObj[0][1] = "" + Utility.month(Integer.parseInt(month)) + " "
						+ year + " & " + Utility.month(Integer.parseInt(prevMonth))
						+ " " + year + " ";*/

				TableDataSet recBetDataSet = new TableDataSet();
				recBetDataSet.setData(recBetObj);
				recBetDataSet.setCellWidth(new int[] { 100 });
				recBetDataSet.setCellAlignment(new int[] { 0 });
				recBetDataSet.setCellColSpan(new int[]{45});
				recBetDataSet.setBodyFontStyle(1);  
				////recBetDataSet.setCellColSpan();
				recBetDataSet.setBlankRowsAbove(1);
				// recBetDataSet.setBlankRowsBelow(2);
				recBetDataSet.setBorderDetail(0);
			// recBetDataSet.setBodyFontDetails(Font.FontFamily.HELVETICA, 8,
				// Font.NORMAL, new BaseColor(0, 0, 0));
				rg.addTableToDoc(recBetDataSet);
				
				
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(header);
				tdstable.setHeaderBorderDetail(3);
				//tdstable.setHeaderLines(true);
				//tdstable.setCellColSpan(new int[]{40});
				tdstable.setCellNoWrap(bcellwrap);
				//tdstable.setHeaderBorderColor(new BaseColor(255, 0, 0));
				tdstable.setData(objTotalTabularData);
				//tdstable.setCellColSpan(new int[]{objTotalTabularData.length});
				tdstable.setCellAlignment(bcellAlign);
				tdstable.setCellWidth(bcellWidth);
				tdstable.setBorderDetail(3);
				tdstable.setHeaderTable(true);
				
				// tdstable.setHeaderBGColor(new BaseColor(200, 200, 200));
				// tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);
				rg.seperatorLine();

				// for (int i = 0; i < objTotalTabularData.length; i++) {

				totalByColumn[0][0] = " ";
				totalByColumn[0][1] = "Total ";
				//totalByColumn[0][2] = " ";
				// for (int i = 0; i < expDetail.length; i++) {
				// totalByColumn[0][3] = empTypeDetail.length;
				// }

				TableDataSet tdstableTotal = new TableDataSet();
				// tdstableTotal.setHeader(columns);
				tdstableTotal.setData(totalByColumn);
				tdstableTotal.setCellAlignment(bcellAlign);
				tdstableTotal.setCellWidth(bcellWidth);
			//	tdstableTotal.setCellNoWrap(bcellwrap);
				tdstableTotal.setBorderLines(true);
				tdstableTotal.setBodyFontStyle(1);
				// tdstableTotal.setBodyFontDetails(FontFamily.HELVETICA, 7,
				// Font.BOLD, new BaseColor(0, 0, 0));
				// tdstableTotal.setHeaderBGColor(new BaseColor(200, 200, 200));
				rg.addTableToDoc(tdstableTotal);
				//rg.addProperty(rg.PAGE_BREAK);
				//rg.addProperty(rg.PAGE_BREAK);
			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "Reconciliation details  of "+ Utility.month(Integer.parseInt(month)) + " "
				+ year + " in comparision with " + Utility.month(Integer.parseInt(prevMonth))
				+ " " + prevYear + " : No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontStyle(1);
				// noData.setBodyFontDetails(FontFamily.HELVETICA, 7, Font.BOLD,
				// new BaseColor(0, 0, 0));
				noData.setBlankRowsAbove(1);
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}

			

			// New Joine Details Start
			if (newJoineeDetailObj != null && newJoineeDetailObj.length > 0) {

				Object[][] dateData = new Object[1][1];
				dateData[0][0] = "New Joinee in  "
						+ Utility.month(Integer.parseInt(month)) + "-" + year;
				int[] cellWidthDateHeader = { 100 };
				int[] cellAlignDateHeader = { 0 };
				TableDataSet tableheadingDateData = new TableDataSet();
				tableheadingDateData.setData(dateData);
				tableheadingDateData.setCellWidth(cellWidthDateHeader);
				tableheadingDateData.setCellAlignment(cellAlignDateHeader);
				// tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA,
				// 6, Font.NORMAL, new BaseColor(0, 0, 0));
				tableheadingDateData.setBorderDetail(0);
				// tableheadingDateData.setHeaderTable(true);
				tableheadingDateData.setBlankRowsAbove(1);
				tableheadingDateData.setBodyFontStyle(1);
				rg.addTableToDoc(tableheadingDateData);
				
				for (int j = 0; j < newJoineeDetailObj.length; j++) {
					//newJoinObjTotalTabularData[j][0] = countNewJoin++;// token
					newJoinObjTotalTabularData[j][0] = newJoineeDetailObj[j][2]; // emp
																					// token
					newJoinObjTotalTabularData[j][1] = newJoineeDetailObj[j][3]; // emp
																					// name
				//	newJoinObjTotalTabularData[j][2] = newJoineeDetailObj[j][4]; // branch
				//	newJoinObjTotalTabularData[j][3] = newJoineeDetailObj[j][5]; // department
					int COUNT = 2;
					double totalCredit = 0.0;

					double creditValueTotal = 0.0;
					double crdValueTotal = 0.0;

					for (int i = 0; i < creditHeadObj.length; i++) {
						newJoinObjTotalTabularData[j][COUNT] = "0.0";

						String key = newJoineeDetailObj[j][0] + "#"
								+ creditHeadObj[i][0];
						// String
						// prevKey=newJoineeDetailObj[j][0]+"#"+creditHeadObj[i][0];

						String value = creditMap.get(key);
						// String prevCreditValue=prevCreditMap.get(prevKey);

						if (value != null) {
							newJoinObjTotalTabularData[j][COUNT] = formatter
							.format(Double
									.parseDouble(checkNullValue(String
											.valueOf(value))));
							totalCredit += Double
									.parseDouble(checkNullValue(String
											.valueOf(value)));

							// total credit
							creditValueTotal = Double
									.parseDouble(checkNullValue(String
											.valueOf(newJoinTotalByColumn[0][COUNT])));
							crdValueTotal = Double
									.parseDouble(checkNullValue(String
											.valueOf(newJoinObjTotalTabularData[j][COUNT])));
							newJoinTotalByColumn[0][COUNT] = formatter.format(creditValueTotal
									+ crdValueTotal);

						}
						COUNT++;
					}

					newJoinObjTotalTabularData[j][COUNT] = formatter.format(totalCredit);// TOTAL CRDIT

					
					newJoinTotalByColumn[0][COUNT] = formatter.format(Double
							.parseDouble(checkNullValue(String
									.valueOf(newJoinTotalByColumn[0][COUNT])))
							+ Double
									.parseDouble(checkNullValue(String
											.valueOf(newJoinObjTotalTabularData[j][COUNT]))));

					COUNT++;
					double totalDebit = 0.0;
					double crValueTotal = 0.0;
					double drValueTotal = 0.0;
					for (int i = 0; i < debitHeadObj.length; i++) {
						newJoinObjTotalTabularData[j][COUNT] = "0.0";

						String key = newJoineeDetailObj[j][0] + "#"
								+ debitHeadObj[i][0];
						// String
						// prevKey=empTypeDetail[j][0]+"#"+debitHeadObj[i][0];

						String value = debitMap.get(key);
						// String prevDebitValue=prevDebitMap.get(prevKey);

						if (value != null) {
							newJoinObjTotalTabularData[j][COUNT] = formatter
							.format(Double
									.parseDouble(checkNullValue(String
											.valueOf(value))));
							totalDebit += Double
									.parseDouble(checkNullValue(String
											.valueOf(value)));

							// total debit
							crValueTotal = Double
									.parseDouble(checkNullValue(String
											.valueOf(newJoinTotalByColumn[0][COUNT])));
							drValueTotal = Double
									.parseDouble(checkNullValue(String
											.valueOf(newJoinObjTotalTabularData[j][COUNT])));
							newJoinTotalByColumn[0][COUNT] = formatter.format(crValueTotal
									+ drValueTotal);

						}
						COUNT++;
					}
					newJoinObjTotalTabularData[j][COUNT] = formatter.format(totalDebit);// TOTAL DEBIT

					newJoinTotalByColumn[0][COUNT] = formatter.format(Double
							.parseDouble(checkNullValue(String
									.valueOf(newJoinTotalByColumn[0][COUNT])))
							+ Double
									.parseDouble(checkNullValue(String
											.valueOf(newJoinObjTotalTabularData[j][COUNT]))));

					COUNT++;
					newJoinObjTotalTabularData[j][COUNT] = formatter.format(totalCredit
							- totalDebit);// TOTAL DEBIT

					newJoinTotalByColumn[0][COUNT] = formatter.format(Double
							.parseDouble(checkNullValue(String
									.valueOf(newJoinTotalByColumn[0][COUNT])))
							+ Double
									.parseDouble(checkNullValue(String
											.valueOf(newJoinObjTotalTabularData[j][COUNT]))));
				}

				TableDataSet newJoinTdstable = new TableDataSet();
				newJoinTdstable.setHeader(header);
				//newJoinTdstable.setHeaderLines(true);
				newJoinTdstable.setHeaderBorderDetail(3);
				//newJoinTdstable.setHeaderBorderColor(new BaseColor(255, 0, 0));
				newJoinTdstable.setData(newJoinObjTotalTabularData);
				//newJoinTdstable.setCellNoWrap(bcellwrap);
				newJoinTdstable.setCellAlignment(bcellAlign);
				newJoinTdstable.setCellWidth(bcellWidth);
				newJoinTdstable.setBorderDetail(3);
				newJoinTdstable.setHeaderTable(true);
				rg.addTableToDoc(newJoinTdstable);
				rg.seperatorLine();

				newJoinTotalByColumn[0][0] = "";
				newJoinTotalByColumn[0][1] = "Total";
			//	newJoinTotalByColumn[0][2] = " ";
				// for (int i = 0; i < expDetail.length; i++) {
				// totalByColumn[0][3] = empTypeDetail.length;
				// }

				TableDataSet newJoinTdstableTotal = new TableDataSet();
				// tdstableTotal.setHeader(columns);
				newJoinTdstableTotal.setData(newJoinTotalByColumn);
				newJoinTdstableTotal.setCellAlignment(bcellAlign);
				newJoinTdstableTotal.setCellWidth(bcellWidth);
				//newJoinTdstableTotal.setCellNoWrap(bcellwrap);
				newJoinTdstableTotal.setBorderLines(true);
				newJoinTdstableTotal.setBodyFontStyle(1);
				// tdstableTotal.setBodyFontDetails(FontFamily.HELVETICA, 7,
				// Font.BOLD, new BaseColor(0, 0, 0));
				// tdstableTotal.setHeaderBGColor(new BaseColor(200, 200, 200));
				rg.addTableToDoc(newJoinTdstableTotal);
				//rg.seperatorLine();

			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "New Joinee in  "
					+ Utility.month(Integer.parseInt(month)) + "-" + year+": No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontStyle(1);
				// noData.setBodyFontDetails(FontFamily.HELVETICA, 7, Font.BOLD,
				// new BaseColor(0, 0, 0));
				noData.setBlankRowsAbove(1);
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}

			

			String[] columns = new String[] { "Employee Id",
					"Employee name", "Branch", "Department" };

			int[] cellWidthChallan = {  22, 20, 20, 30 };
			int[] cellAlignChallan = {  0, 0, 0, 0 };

			if (separationDetailObj != null && separationDetailObj.length > 0) {
				
				// Separation Details start
				Object[][] separationData = new Object[1][1];
				separationData[0][0] = "Separations in  "
						+ Utility.month(Integer.parseInt(month)) + "-" + year;
				int[] cellWidthSeparationHeader = { 100 };
				int[] cellAlignSeparationHeader = { 0 };
				TableDataSet separationTableheadingDateData = new TableDataSet();
				separationTableheadingDateData.setData(separationData);
				separationTableheadingDateData
						.setCellWidth(cellWidthSeparationHeader);
				separationTableheadingDateData
						.setCellAlignment(cellAlignSeparationHeader);
				// tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA,
				// 6, Font.NORMAL, new BaseColor(0, 0, 0));
				separationTableheadingDateData.setBorderDetail(0);
				// tableheadingDateData.setHeaderTable(true);
				separationTableheadingDateData.setBlankRowsAbove(1);
				separationTableheadingDateData.setBodyFontStyle(1);
				rg.addTableToDoc(separationTableheadingDateData);
				
				
				TableDataSet separationTdstable = new TableDataSet();
				separationTdstable.setHeader(columns);
				//separationTdstable.setHeaderLines(true);
				separationTdstable.setHeaderBorderDetail(3);
				separationTdstable
						.setHeaderBorderColor(new BaseColor(255, 0, 0));
				separationTdstable.setData(separationDetailObj);
				separationTdstable.setCellAlignment(cellAlignChallan);
				separationTdstable.setCellWidth(cellWidthChallan);
				separationTdstable.setBorderDetail(3);
				separationTdstable.setHeaderTable(true);
				rg.addTableToDoc(separationTdstable);
				//rg.seperatorLine();
			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "Separations in  "
					+ Utility.month(Integer.parseInt(month)) + "-" + year+": No records available";
				noData.setBodyFontStyle(1);
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBlankRowsAbove(1);
				// noData.setBodyFontDetails(FontFamily.HELVETICA, 7, Font.BOLD,
				// new BaseColor(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}
			
			/*} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "Reconciliation details  of "+ Utility.month(Integer.parseInt(month)) + " "
				+ year + " in comparision with " + Utility.month(Integer.parseInt(prevMonth))
				+ " " + prevYear + " : No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontStyle(1);
				// noData.setBodyFontDetails(FontFamily.HELVETICA, 7, Font.BOLD,
				// new BaseColor(0, 0, 0));
				noData.setBlankRowsAbove(1);
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}*/

			/*String footerQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_RANK,RANK_NAME "
					+ " FROM HRMS_EMP_OFFC "
					+ "INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
					+ "WHERE EMP_ID=" + bean.getUserEmpId();

			// for credit emp type
			Object[][] footerDetail = getSqlModel()
					.getSingleResult(footerQuery);

			TableDataSet generatedByData = new TableDataSet();
			generatedByData.setData(new Object[][] { { "Generated By : "
					+ String.valueOf(footerDetail[0][1]) + " on "
					+ date.toString() } });
			generatedByData.setCellAlignment(new int[] { 0 });
			generatedByData.setCellWidth(new int[] { 100 });
			// generatedByData.setBodyFontDetails(FontFamily.HELVETICA, 7,
			// Font.BOLD, new BaseColor(0, 0, 0));
			generatedByData.setBorderDetail(0);
			// generatedByData.setBorderDetail(1);
			// generatedByData.setHeaderTable(true);
			generatedByData.setBlankRowsAbove(1);
			rg.addTableToDoc(generatedByData);*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

}
