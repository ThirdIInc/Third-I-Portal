package org.paradyne.model.payroll;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.extraWorkBenefits.ExtraWorkBenefitMisBean;
import org.paradyne.bean.payroll.BankStatementMis;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class BankStatementMisModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(BankStatementMisModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");

	public boolean deleteSavedReport(BankStatementMis bankStatementMisBean) {
		boolean result = false;
		try {
			String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID="
					+ bankStatementMisBean.getReportId();
			String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID="
					+ bankStatementMisBean.getReportId();
			result = getSqlModel().singleExecute(deleteDetail);
			if (result)
				result = getSqlModel().singleExecute(deleteHeader);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(BankStatementMis bankStatementMisBean) {
		boolean result = false;
		try {
			String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE UPPER(REPORT_CRITERIA) LIKE '"
					+ bankStatementMisBean.getSettingName().trim()
							.toUpperCase()
					+ "' AND REPORT_TYPE='BankStatementMis'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean saveReportCriteria(BankStatementMis bankStatementMisBean) {
		boolean result = false;
		try {
			if (!checkDuplicate(bankStatementMisBean)) {
				Object[][] saveObj = new Object[1][3];
				saveObj[0][0] = checkNull(bankStatementMisBean.getSettingName()
						.trim());
				saveObj[0][1] = checkNull(bankStatementMisBean.getReportTitle()
						.trim());
				saveObj[0][2] = "BankStatementMis";
				String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE,REPORT_TYPE)"
						+ " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?,?)";
				result = getSqlModel().singleExecute(insertHeader, saveObj);
				if (result) {
					String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
					Object[][] codeObj = getSqlModel().getSingleResult(
							codeQuery);
					bankStatementMisBean.setReportId(String
							.valueOf(codeObj[0][0]));
					bankStatementMisBean.setReportTypeStr("BankStatementMis");
					if (saveFilters(bankStatementMisBean)
							&& saveColumns(bankStatementMisBean)
							&& saveSortOptions(bankStatementMisBean)
							&& saveFilters(bankStatementMisBean)) {
						result = true;
					} else
						result = false;
				}
			} else
				result = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveSortOptions(BankStatementMis bankStatementMisBean) {
		boolean result = false;
		int count = 0;
		if (!bankStatementMisBean.getSortBy().equals("1")) {
			count++;
		}
		if (!bankStatementMisBean.getHiddenSortBy().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getSortByAsc().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getSortByDsc().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getSortByOrder().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getThenBy1().equals("1")) {
			count++;
		}
		if (!bankStatementMisBean.getHiddenThenBy1().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getThenByOrder1Asc().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getThenByOrder1Dsc().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getThenByOrder1().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getThenBy2().equals("1")) {
			count++;
		}
		if (!bankStatementMisBean.getHiddenThenBy2().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getThenByOrder2Asc().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getThenByOrder2Dsc().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getThenByOrder2().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getHiddenColumns().equals("")) {
			count++;
		}

		logger.info("Count for Save sort options : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!bankStatementMisBean.getSortBy().equals("1")) {
			addObj[int_count][0] = "sortBy";
			addObj[int_count][1] = checkNull(bankStatementMisBean.getSortBy()
					.trim());
			int_count++;
		}
		if (!bankStatementMisBean.getHiddenSortBy().equals("")) {
			addObj[int_count][0] = "hiddenSortBy";
			addObj[int_count][1] = checkNull(bankStatementMisBean
					.getHiddenSortBy().trim());
			int_count++;
		}
		logger.info("getSortByAsc...." + bankStatementMisBean.getSortByAsc());
		if (!bankStatementMisBean.getSortByAsc().equals("")) {
			addObj[int_count][0] = "sortByAsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getSortByAsc().trim());
			int_count++;
		}
		logger.info("getSortByDsc...." + bankStatementMisBean.getSortByDsc());
		if (!bankStatementMisBean.getSortByDsc().equals("")) {
			addObj[int_count][0] = "sortByDsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getSortByDsc().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getSortByOrder().equals("")) {
			addObj[int_count][0] = "sortByOrder";
			addObj[int_count][1] = checkNull(bankStatementMisBean
					.getSortByOrder().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getThenBy1().equals("1")) {
			addObj[int_count][0] = "thenBy1";
			addObj[int_count][1] = checkNull(bankStatementMisBean.getThenBy1()
					.trim());
			int_count++;
		}
		if (!bankStatementMisBean.getHiddenThenBy1().equals("")) {
			addObj[int_count][0] = "hiddenThenBy1";
			addObj[int_count][1] = checkNull(bankStatementMisBean
					.getHiddenThenBy1().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getThenByOrder1Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Asc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Asc().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getThenByOrder1Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Dsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Dsc().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getThenByOrder1().equals("")) {
			addObj[int_count][0] = "thenByOrder1";
			addObj[int_count][1] = checkNull(bankStatementMisBean
					.getThenByOrder1().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getThenBy2().equals("1")) {
			addObj[int_count][0] = "thenBy2";
			addObj[int_count][1] = checkNull(bankStatementMisBean.getThenBy2()
					.trim());
			int_count++;
		}
		if (!bankStatementMisBean.getHiddenThenBy2().equals("")) {
			addObj[int_count][0] = "hiddenThenBy2";
			addObj[int_count][1] = checkNull(bankStatementMisBean
					.getHiddenThenBy2().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getThenByOrder2Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Asc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Asc().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getThenByOrder2Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Dsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Dsc().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getThenByOrder2().equals("")) {
			addObj[int_count][0] = "thenByOrder2";
			addObj[int_count][1] = checkNull(bankStatementMisBean
					.getThenByOrder2().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getHiddenColumns().equals("")) {
			addObj[int_count][0] = "hiddenColumns";
			addObj[int_count][1] = checkNull(bankStatementMisBean
					.getHiddenColumns().trim());
			int_count++;
		}

		logger.info("int_count     : " + int_count);
		if (int_count == 0) {
			return true;
		} else {
			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addSortOptions = new Object[int_count][3];
			for (int i = 0; i < addSortOptions.length; i++) {
				addSortOptions[i][0] = maxCode[0][0];
				addSortOptions[i][1] = addObj[i][0];
				logger.info("addSortOptions[" + i + "][1]  : "
						+ addSortOptions[i][1]);
				addSortOptions[i][2] = addObj[i][1];
				logger.info("addSortOptions[" + i + "][2]  : "
						+ addSortOptions[i][2]);
			}
			String insertSortOptions = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertSortOptions,
					addSortOptions);

			return result;
		}
	}

	public boolean saveColumns(BankStatementMis bankStatementMisBean) {
		boolean result = false;
		int count = 0;
		if (bankStatementMisBean.getAccNumberFlag().equals("true")) {
			count++;
		}
		if (bankStatementMisBean.getCurrencyCodeFlag().equals("true")) {
			count++;
		}
		if (bankStatementMisBean.getSolIdFlag().equals("true")) {
			count++;
		}
		if (bankStatementMisBean.getCreditdebitFlag().equals("true")) {
			count++;
		}
		if (bankStatementMisBean.getTransactiontypeFlag().equals("true")) {
			count++;
		}
		if (bankStatementMisBean.getIfsccodeFlag().equals("true")) {
			count++;
		}
		/*if (bankStatementMisBean.getEmpcodeFlag().equals("true")) {
			count++;
		}*/
		if (bankStatementMisBean.getEmpnameFlag().equals("true")) {
			count++;
		}
		if (bankStatementMisBean.getTransactionDtlFlag().equals("true")) {
			count++;
		}
		if (bankStatementMisBean.getCustomerrefnoFlag().equals("true")) {
			count++;
		}
		if (bankStatementMisBean.getTransactionDateFlag().equals("true")) {
			count++;
		}
		if (bankStatementMisBean.getBankFlag().equals("true")) {
			count++;
		}
		if (bankStatementMisBean.getBankbrnNameFlag().equals("true")) {
			count++;
		}
		if (bankStatementMisBean.getBeneficaryEmailidFlag().equals("true")) {
			count++;
		}

		logger.info("Count for Save columns -----------------: " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (bankStatementMisBean.getAccNumberFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.accNumberFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (bankStatementMisBean.getCurrencyCodeFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.currencyCodeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (bankStatementMisBean.getSolIdFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.solIdFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (bankStatementMisBean.getCreditdebitFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.creditdebitFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (bankStatementMisBean.getTransactiontypeFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.transactiontypeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (bankStatementMisBean.getIfsccodeFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.ifsccodeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		/*if (bankStatementMisBean.getEmpcodeFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.empcodeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}*/
		if (bankStatementMisBean.getEmpnameFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.empnameFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (bankStatementMisBean.getTransactionDtlFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.transactionDtlFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (bankStatementMisBean.getCustomerrefnoFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.customerrefnoFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (bankStatementMisBean.getTransactionDateFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.transactionDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (bankStatementMisBean.getBankFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.bankFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (bankStatementMisBean.getBankbrnNameFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.bankbrnNameFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (bankStatementMisBean.getBeneficaryEmailidFlag().equals("true")) {
			addObj[int_count][0] = "bankStatementMisBean.beneficaryEmailidFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		logger.info("int_count     ====================: " + int_count);
		if (int_count == 0) {
			return true;
		} else {
			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addColumns = new Object[int_count][3];
			for (int i = 0; i < addColumns.length; i++) {
				addColumns[i][0] = maxCode[0][0];
				addColumns[i][1] = addObj[i][0];
				logger.info("addColumns[" + i + "][1]  : " + addColumns[i][1]);
				addColumns[i][2] = addObj[i][1];
				logger.info("addColumns[" + i + "][2]  : " + addColumns[i][2]);
			}
			String insertColumns = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertColumns, addColumns);
			return result;
		}
	}

	public boolean saveFilters(BankStatementMis bankStatementMisBean) {
		boolean result = false;
		int count = 0;
		
		if (!bankStatementMisBean.getMonth().equals("0")) {
			count++;
		}
		if (!bankStatementMisBean.getYear().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getDivCode().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getDivCode().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getCenterCode().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getCenterCode().equals("")) {
			count++;
		}
		if (!bankStatementMisBean.getBankCode().equals("")) {
			count++;// code
		}
		if (!bankStatementMisBean.getBankCode().equals("")) {
			count++;// name
		}

		logger.info("Count for Save filters : --------------" + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		
		if (!bankStatementMisBean.getMonth().equals("0")) {
			addObj[int_count][0] = "month";
			addObj[int_count][1] = checkNull(bankStatementMisBean.getMonth().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getYear().equals("")) {
			addObj[int_count][0] = "year";
			addObj[int_count][1] = checkNull(bankStatementMisBean.getYear().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getDivCode().equals("")) {
			addObj[int_count][0] = "bankStatementMisBean.divCode";
			addObj[int_count][1] = checkNull(bankStatementMisBean.getDivCode()
					.trim());
			int_count++;
		}
		if (!bankStatementMisBean.getDivName().equals("")) {
			addObj[int_count][0] = "bankStatementMisBean.divName";
			addObj[int_count][1] = checkNull(bankStatementMisBean.getDivName()
					.trim());
			int_count++;
		}
		if (!bankStatementMisBean.getCenterCode().equals("")) {
			addObj[int_count][0] = "bankStatementMisBean.centerCode";
			addObj[int_count][1] = checkNull(bankStatementMisBean
					.getCenterCode().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getCenterName().equals("")) {
			addObj[int_count][0] = "bankStatementMisBean.centerName";
			addObj[int_count][1] = checkNull(bankStatementMisBean
					.getCenterName().trim());
			int_count++;
		}
		if (!bankStatementMisBean.getBankCode().equals("")) {// code
			addObj[int_count][0] = "bankStatementMisBean.bankCode";
			addObj[int_count][1] = checkNull(bankStatementMisBean.getBankCode()
					.trim());
			int_count++;
		}
		if (!bankStatementMisBean.getBankName().equals("")) {// name
			addObj[int_count][0] = "bankStatementMisBean.bankName";
			addObj[int_count][1] = checkNull(bankStatementMisBean.getBankName()
					.trim());
			int_count++;
		}

		logger.info("int_count    -------------------------- : " + int_count);
		if (int_count == 0) {
			return true;
		} else {

			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addFilters = new Object[int_count][3];
			logger.info("addFilters.length    " + addFilters.length);

			for (int i = 0; i < addFilters.length; i++) {
				addFilters[i][0] = maxCode[0][0];
				addFilters[i][1] = addObj[i][0];
				logger.info("addFilters[" + i + "][1]  : " + addFilters[i][1]);
				addFilters[i][2] = addObj[i][1];
				logger.info("addFilters[" + i + "][2]  : " + addFilters[i][2]);
			}
			String insertFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertFilters, addFilters);
			return result;
		}
	}

	public void setDetailOnScreen(BankStatementMis bankStatementMisBean) {

		String dtlQuery = "SELECT HRMS_MISREPORT_DTL.REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL "
				+ "  INNER JOIN HRMS_MISREPORT_HDR  ON (HRMS_MISREPORT_HDR.REPORT_ID= HRMS_MISREPORT_DTL.REPORT_ID )"
				+ " WHERE HRMS_MISREPORT_DTL.REPORT_ID ="
				+ bankStatementMisBean.getReportId();

		Object[][] dtlObj = getSqlModel().getSingleResult(dtlQuery);
		if (dtlObj != null && dtlObj.length > 0) {
			try {
				// Class modelClass = Class.forName(aClass);
				for (int i = 0; i < dtlObj.length; i++) {

					String methodStr = "";
					logger.info("dtlObj[" + i + "][1] "
							+ String.valueOf(dtlObj[i][1]));
					String fieldName = String.valueOf(dtlObj[i][1]).replace(
							".", "-");
					logger.info("fieldName " + fieldName);
					String[] fieldNames = fieldName.split("-");
					if (fieldNames.length > 1)
						methodStr = fieldNames[1];
					else
						methodStr = fieldNames[0];
					logger.info("methodStr  : " + methodStr);
					if (String.valueOf(dtlObj[i][2]).trim().equals("Y")) {
						dtlObj[i][2] = "true";
					}
					if (String.valueOf(dtlObj[i][2]).trim().equals("true")
							&& (String.valueOf(dtlObj[i][1]).trim().equals(
									"sortByAsc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("sortByDsc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("thenByOrder1Asc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("thenByOrder1Dsc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("thenByOrder2Asc") || String
									.valueOf(dtlObj[i][1]).trim().equals(
											"thenByOrder2Dsc"))) {
						dtlObj[i][2] = "checked";
					}
					Method modelMethod = BankStatementMis.class
							.getDeclaredMethod("set" + initCap(methodStr),
									String.class);
					// misreport = MISReport.class.newInstance();
					logger.info(" dtlObj[i][2]     :"
							+ String.valueOf(dtlObj[i][2]));
					modelMethod.invoke(bankStatementMisBean, String
							.valueOf(dtlObj[i][2]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static String initCap(String name) {
		String properName = "";

		try {
			properName = name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length());
		} catch (Exception e) {
			return properName;
		}
		return properName;
	}

	public void getReport(BankStatementMis bean, HttpServletResponse response,
			String[] labelNames, HttpServletRequest request) {
		try {
			String reportType = "";
			if (bean.getReportType().equals("P")) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals("X")) {
				reportType = "Xls";
			}
			if (bean.getReportType().equals("T")) {
				reportType = "Txt";
			}
			logger.info("reportType--------------->" + reportType + "<-------");
			String reportName = "";
			if (!bean.getReportTitle().equals(""))
				reportName = bean.getReportTitle();
			else
				reportName = "Bank Statement MIS Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					reportType, reportName, "");
			rg.addText("\n", 0, 0, 0);

			logger.info("multi select values  : " + bean.getHiddenColumns());
			String mutliSelectValues = bean.getHiddenColumns();

			String splitComma[] = null;
			if (!mutliSelectValues.equals("")) {
				String lastComma = mutliSelectValues.substring(0,
						mutliSelectValues.length() - 1);

				splitComma = lastComma.split(",");
			}

			int count = 0;

			logger.info("counter initial value==========" + count);

			// SELECT QUERY WITH COUNT (COUNT APPENDED AFTER #)
			String queryWithCount = selectQuery(bean, labelNames, count,
					splitComma, request);
			// SPLIT TO GET COUNT VALUE
			String[] splitquery = queryWithCount.split("#");

			String query = splitquery[0];
			count = Integer.parseInt(splitquery[1]);
			logger.info("counter after select query==========" + count);

			String[] str_colNames = new String[count + 1];
			str_colNames[0] = "Employee Code";
			int str_colNames_array = 0;
			int[] cellWidth = new int[count + 1];
			cellWidth[0] = 10;
			int cellWidth_array = 0;
			int[] cellAlign = new int[count + 1];
			cellAlign[0] = 0;
			int cellAlign_array = 0;
			int format_array = 0;

			// QUERY APPENDED WITH CONDITIONS
			query += conditionQuery(bean, labelNames);

			Object[][] finalObj = null;
			Object[][] reportObj = getSqlModel().getSingleResult(query);

			if (reportObj != null && reportObj.length > 0) {
				finalObj = new Object[reportObj.length][reportObj[0].length + 1];
				for (int i = 0; i < reportObj.length; i++) {
					for (int j = 0; j < reportObj[0].length; j++) {
						finalObj[i][j] = reportObj[i][j];
					}
				}
			}

			// CODING FOR HEADERS, WIDTH AND ALIGNMENTS
			if (splitComma != null && splitComma.length > 0) {
				for (int i = 0; i < splitComma.length; i++) {
					String splitDash[] = splitComma[i].split("-");
					logger.info("Key....." + splitDash[0]);
					logger.info("Value....." + splitDash[1]);

					//employee name
					if (splitDash[1].equals(labelNames[0])) {
						str_colNames[++str_colNames_array] = labelNames[0];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[1])) {
						str_colNames[++str_colNames_array] = labelNames[1];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[2])) {
						str_colNames[++str_colNames_array] = labelNames[2];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[3])) {
						str_colNames[++str_colNames_array] = labelNames[3];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[4])) {
						str_colNames[++str_colNames_array] = labelNames[4];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[5])) {
						str_colNames[++str_colNames_array] = labelNames[5];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[6])) {
						str_colNames[++str_colNames_array] = labelNames[6];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[7])) {
						str_colNames[++str_colNames_array] = labelNames[7];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[8])) {
						str_colNames[++str_colNames_array] = labelNames[8];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 2;
					}

					else if (splitDash[1].equals(labelNames[9])) {
						str_colNames[++str_colNames_array] = labelNames[9];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					} else if (splitDash[1].equals(labelNames[10])) {
						str_colNames[++str_colNames_array] = labelNames[10];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					} else if (splitDash[1].equals(labelNames[11])) {
						str_colNames[++str_colNames_array] = labelNames[11];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					} else if (splitDash[1].equals(labelNames[12])) {
						str_colNames[++str_colNames_array] = labelNames[12];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					} else if (splitDash[1].equals(labelNames[13])) {
						str_colNames[++str_colNames_array] = labelNames[13];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					} 

				}

			}

			Object[][] objData1 = null;
			
			if (finalObj != null && finalObj.length > 0) {
				objData1 = new Object[finalObj.length][count + 1];
				for (int i = 0; i < finalObj.length; ++i) {
					int int_count = 0;
					objData1[i][0] = checkNull(String.valueOf(finalObj[i][0]));// Employee Token

					if (bean.getAccNumberFlag().equals("true")) {
						objData1[i][++int_count] = (checkNull(
								String.valueOf(finalObj[i][int_count]))).trim().equals(
								"") ? "" : (String.valueOf(finalObj[i][int_count]));
						
						
					}
					if (bean.getCurrencyCodeFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getSolIdFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getCreditdebitFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getTransactiontypeFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));// Employee//
						// Name

					}
					if (bean.getIfsccodeFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					/*if (bean.getEmpcodeFlag().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}*/

					if (bean.getEmpnameFlag().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));

					}
					if (bean.getTransactionDtlFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getCustomerrefnoFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

					if (bean.getTransactionDateFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getBankFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

					if (bean.getBankbrnNameFlag().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));

					}
					if (bean.getBeneficaryEmailidFlag().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));

					}
					if (bean.getDivisionAccountNoFlag().equals("true")) {

						objData1[i][++int_count] = checkNull(String.valueOf(finalObj[i][int_count]));

					}

				}
				
				String month=Utility.month(Integer.parseInt(bean.getMonth()));
				
				int [] cols1 = {20,20,40};
				int [] align1 = {0,0,1};
				
				Object [][] title = new Object[1][3];
				title [0][0] = "";
				title [0][1] = "";
				title [0][2] = "Bank Statement for the Division "+bean.getDivName()+" ,"+month+" - "+bean.getYear();  

				logger.info("Export all values   : " + bean.getExportAll());
				logger.info("counter for exporting=========="
						+ bean.getReqStatus());
				if (bean.getReqStatus().trim().equals("R"))
					bean.setExportAll("on");
				if (bean.getExportAll().equals("on")) {
					if (bean.getReportType().equals("P")) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBodyNoCellBorder(title,cols1,align1,1); 
						rg.addText("\n",0,0,0);
						rg.tableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals("X")) {
						//rg.setFName(reportName + ".xls");
						rg.setFName(reportName);
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.tableBodyNoCellBorder(title,cols1,align1,1); 
						rg.addText("\n",0,0,0);
						/*rg.xlsTableBody(str_colNames, objData1, cellWidth,
								cellAlign);
						*/
						rg.xlsTableBodyForStatement(str_colNames, objData1, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBodyNoCellBorder(title,cols1,align1,1); 
						rg.addText("\n",0,0,0);
						rg.tableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					}
				} else {
					String[] pageIndex = Utility.doPaging(bean.getMyPage(),
							objData1.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					int numOfRec = Integer.parseInt(pageIndex[1])
							- Integer.parseInt(pageIndex[0]);
					int columnLength = count + 1;

					Object[][] pageObj = new Object[numOfRec][columnLength];
					int z = 0;
					int srNo = 1;
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer
							.parseInt(pageIndex[1]); i++) {
						for (int j = 0; j < columnLength; j++) {
							// logger.info("objData1["+i+"]["+j+"] :
							// "+objData1[i][j]);
							pageObj[z][j] = objData1[i][j];
							// pageObj[z][0] = String.valueOf(srNo);

						}
						z++;
						srNo++;
					}
					if (bean.getReportType().equals("P")) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals("X")) {
						rg.setFName(reportName + ".xls");
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						/*rg.xlsTableBody(str_colNames, objData1, cellWidth,
								cellAlign);
						*/
						rg.xlsTableBodyForStatement(str_colNames, objData1, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					}
				}

			} else {
				rg.addFormatedText("There is no data to display.", 0, 1, 1, 0);
			}
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String conditionQuery(BankStatementMis bean, String[] labelNames) {
		// TODO Auto-generated method stub
		String query = "";

		try {
			String year =bean.getYear();
			query = " FROM HRMS_SALARY_"+year+" "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ "INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+year+".SAL_LEDGER_CODE)"
					+ "LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE LEFT JOIN "
					+ " HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ "LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR)"
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P' ) "
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV =HRMS_DIVISION.DIV_ID) ";
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				query += "  " + " WHERE 1=1 ";
			}
			
			if (!(bean.getMonth().equals("0")) && !(bean.getMonth() == null)
					&& !bean.getMonth().equals("null")) {

				query += " AND LEDGER_MONTH="
						+ bean.getMonth().trim();
			}
			if (!(bean.getYear().equals("")) && !(bean.getYear() == null)
					&& !bean.getYear().equals("null")) {

				query += " AND LEDGER_YEAR="
						+ bean.getYear().trim();
			}
			if (!(bean.getDivCode().equals("")) && !(bean.getDivCode() == null)
					&& !bean.getDivCode().equals("null")) {

				query += " AND SAL_DIVISION=" + bean.getDivCode().trim();
			}
			if (!(bean.getCenterCode().equals(""))
					&& !(bean.getCenterCode() == null)
					&& !bean.getCenterCode().trim().equals("null")) {

				query += " AND SAL_EMP_CENTER=" + bean.getCenterCode();
			}
			if (!(bean.getBankCode().equals(""))
					&& !(bean.getBankCode() == null)
					&& !bean.getBankCode().trim().equals("null")) {

				query += " AND  HRMS_SALARY_MISC.SAL_MICR_REGULAR="
						+ bean.getBankCode();
			}
			// ============ start of sorting ===========
			if (!bean.getSortBy().equals("1") || !bean.getThenBy1().equals("1")
					|| !bean.getThenBy2().equals("1")) {
				query += " ORDER BY ";
			}
			if (!bean.getSortBy().equals("1")) {
				query += getSortVal(bean.getSortBy(), labelNames) + " "
						+ getSortOrder(bean.getSortByOrder());
				if (!bean.getThenBy1().equals("1")
						|| !bean.getThenBy2().equals("1")) {
					query += " , ";
				}
			}
			if (!bean.getThenBy1().equals("1")) {
				query += getSortVal(bean.getThenBy1(), labelNames) + " "
						+ getSortOrder(bean.getThenByOrder1());
				if (!bean.getThenBy2().equals("1")) {
					query += " , ";
				}
			}
			if (!bean.getThenBy2().equals("1")) {
				query += getSortVal(bean.getThenBy2(), labelNames) + " "
						+ getSortOrder(bean.getThenByOrder2());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	private String getSortOrder(String Status) {
		// TODO Auto-generated method stub
		System.out.println("Status-----------------" + Status);
		String sql = "";
		try {

			if (Status.equals("A")) {
				sql = "ASC";
			}
			if (Status.equals("D")) {
				sql = "DESC";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

	private String getSortVal(String Status, String[] labelNames) {
		logger.info("Sort Status............" + Status);

		String sql = "";

		try {
			if (Status.equals(labelNames[0])) {
				sql = "nvl(SAL_ACCNO_REGULAR,' ')";

			} else if (Status.equals(labelNames[1])) {
				sql = "'INR'";

			} else if (Status.equals(labelNames[2])) {
				sql = " substr(SAL_ACCNO_REGULAR,0,4)";

			}

			else if (Status.equals(labelNames[3])) {
				sql = " 'C'";

			} else if (Status.equals(labelNames[4])) {
				sql = " NVL(SAL_NET_SALARY,0) ";

			} else if (Status.equals(labelNames[5])) {
				sql = " NVL(BANK_IFSC_CODE,' ') ";

			} else if (Status.equals(labelNames[6])) {
				sql = " nvl(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ') ";

			} else if (Status.equals(labelNames[7])) {
				sql = "nvl(SAL_ACCNO_REGULAR,' ')||','||'C'||','||NVL(SAL_NET_SALARY,0)||','||EMP_FNAME||' '||EMP_MNAME||''||EMP_LNAME";

			} else if (Status.equals(labelNames[8])) {
				sql = " nvl(CUSTOMER_REF_NO,'') ";

			} else if (Status.equals(labelNames[9])) {
				sql = "nvl(to_char(SYSDATE,'dd-mm-yyyy'),' ') ";

			} else if (Status.equals(labelNames[10])) {
				sql = "nvl(BANK_NAME,'') ";

			} else if (Status.equals(labelNames[11])) {
				sql = "nvl(BRANCH_NAME,' ')  ";

			} else if (Status.equals(labelNames[12])) {
				sql = "nvl(ADD_EMAIL,'') ";

			} else if (Status.equals(labelNames[13])) {
				sql = "nvl(DIV_ACCOUNT_NUMBER,'') ";

			} else if (Status.equals("Employee Code")) {
				sql = " HRMS_EMP_OFFC.EMP_TOKEN ";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

	private String selectQuery(BankStatementMis bean, String[] labelNames,
			int count, String[] splitComma, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String query = "";
		try {

			String labels = "Employee Code,";
			query = " SELECT EMP_TOKEN ";
			if (splitComma != null) {
				// new HASHMAP FOR ORDERING
				LinkedHashMap<Integer, String> columnMap = new LinkedHashMap<Integer, String>();
				LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();

				for (int i = 0; i < splitComma.length; i++) {
					String splitDash[] = splitComma[i].split("-");
					logger.info("Key....." + splitDash[0]);
					logger.info("Value....." + splitDash[1]);

					// labelMap.put(0, "Employee Code");
					if (splitDash[1].trim().equals(labelNames[0].trim())) {
						// DONT APPEND QUERY
						// PUT IN HASHMAP (ORDER NO,FIELD)
						logger.info("in name-----------------------");
						columnMap.put(Integer.parseInt(splitDash[0]),
								"nvl(SAL_ACCNO_REGULAR,' ')");
						labelMap.put(1, labelNames[0]);
						count++;
					} else if (splitDash[1].equals(labelNames[1])) {
						// query += " ,NVL(DIV_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"'INR'");
						labelMap.put(2, labelNames[1]);
						count++;
					} else if (splitDash[1].equals(labelNames[2])) {
						// query += " ,NVL(DIV_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"substr(SAL_ACCNO_REGULAR,0,4)");
						labelMap.put(3, labelNames[2]);
						count++;
					} else if (splitDash[1].equals(labelNames[3])) {
						// query += " ,NVL(DIV_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]), " 'C'");
						labelMap.put(4, labelNames[3]);
						count++;
					} else if (splitDash[1].equals(labelNames[4])) {
						// query += " ,NVL(DEPT_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(SAL_NET_SALARY,0)");
						labelMap.put(5, labelNames[4]);
						count++;
					}

					else if (splitDash[1].equals(labelNames[5])) {
						// query += " ,NVL(CENTER_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"NVL(BANK_IFSC_CODE,' ')");
						labelMap.put(6, labelNames[5]);
						count++;
					}else if (splitDash[1].equals(labelNames[6])) {
						// query += " ,NVL(CENTER_NAME,' ') ";
						columnMap
								.put(Integer.parseInt(splitDash[0]),
										"nvl(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ')");
						labelMap.put(7, labelNames[6]);
						count++;
					} else if (splitDash[1].equals(labelNames[7])) {
						// query += " ,NVL(CENTER_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"nvl(SAL_ACCNO_REGULAR,' ')||','||'C'||','||NVL(SAL_NET_SALARY,0)||','||EMP_FNAME||' '||EMP_MNAME||''||EMP_LNAME ");
						labelMap.put(8, labelNames[7]);
						count++;
					} else if (splitDash[1].equals(labelNames[8])) {
						// query += " ,NVL(CENTER_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"nvl(CUSTOMER_REF_NO,'')");
						labelMap.put(9, labelNames[8]);
						count++;
					} else if (splitDash[1].equals(labelNames[9])) {
						// query += " ,NVL(CENTER_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"nvl(to_char(SYSDATE,'dd-mm-yyyy'),' ')");
						labelMap.put(10, labelNames[9]);
						count++;
					} else if (splitDash[1].equals(labelNames[10])) {
						// query += " ,NVL(CENTER_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"nvl(BANK_NAME,'')");
						labelMap.put(11, labelNames[10]);
						count++;
					} else if (splitDash[1].equals(labelNames[11])) {
						// query += " ,NVL(CENTER_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"nvl(BRANCH_NAME,' ') ");
						labelMap.put(12, labelNames[11]);
						count++;
					} else if (splitDash[1].equals(labelNames[12])) {
						// query += " ,NVL(CENTER_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"nvl(ADD_EMAIL,'')");
						labelMap.put(13, labelNames[12]);
						count++;
					} else if (splitDash[1].equals(labelNames[13])) {
						// query += " ,NVL(CENTER_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"nvl(DIV_ACCOUNT_NUMBER,'')");
						labelMap.put(14, labelNames[13]);
						count++;
					}

				}
				Iterator<Integer> iterator = columnMap.keySet().iterator();
				while (iterator.hasNext()) {
					String mapValues = (String) columnMap.get(iterator.next());
					logger.info("mapValues : " + mapValues);
					query += "," + mapValues;
				}

				Iterator<Integer> labelIter = labelMap.keySet().iterator();
				String labelValues = "";
				while (labelIter.hasNext()) {
					labelValues = (String) labelMap.get(labelIter.next());
					logger.info("labelValues : " + labelValues);
					labels += labelValues + ",";
				}
			}

			logger.info("labels........." + labels);
			//request.setAttribute("labelValues", labels);

			logger.info("count in selectQuery method : " + count);
			query += " ,HRMS_EMP_OFFC.EMP_ID " + "#" + count + "#" + labels;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}
 
	public void callViewScreen(BankStatementMis misreport,
			HttpServletRequest request, String[] labelNames) {
		try {

			logger.info("multi select values  : "
					+ misreport.getHiddenColumns());
			String mutliSelectValues = misreport.getHiddenColumns();
			String splitComma[] = null;
			if (!mutliSelectValues.equals("")) {
				String lastComma = mutliSelectValues.substring(0,
						mutliSelectValues.length() - 1);
				splitComma = lastComma.split(",");
			}

			int count = 0;

			logger.info("counter initial value==========" + count);

			Object[][] creditsObj = null;
			// SELECT QUERY WITH COUNT (COUNT APPENDED AFTER #)
			String queryWithCount = selectQuery(misreport, labelNames, count,
					splitComma, request);
			// SPLIT TO GET COUNT VALUE
			String[] splitquery = queryWithCount.split("#");

			String query = splitquery[0];
			count = Integer.parseInt(splitquery[1]);
			String labels = splitquery[2];
			logger.info("counter after select query==========" + count);
			logger.info("labels after select query==========" + labels);

			logger.info("counter after ctc & salary==========" + count);
			// QUERY APPENDED WITH CONDITIONS
			query += conditionQuery(misreport, labelNames);

			Object[][] finalObj = null;
			Object[][] reportObj = getSqlModel().getSingleResult(query);

			logger.info("Labels before setting..." + labels);
			request.setAttribute("labelValues", labels);

			if (reportObj != null && reportObj.length > 0) {
				finalObj = new Object[reportObj.length][reportObj[0].length + 1];
				for (int i = 0; i < reportObj.length; i++) {
					for (int j = 0; j < reportObj[0].length; j++) {
						finalObj[i][j] = reportObj[i][j];
					}
				}
			}

			Object[][] objData1 = null;
			String[] labelObject = null;

			if (finalObj != null && finalObj.length > 0) {
				objData1 = new Object[finalObj.length][count + 1];
				labelObject = new String[count + 1];
			 
				logger.info("objData1 length------------------------------"
						+ objData1.length);

				misreport.setDataLength(String.valueOf(objData1.length));

				// for (int j = Integer.parseInt(pageIndex[0]); j < Integer
				// .parseInt(pageIndex[1]); j++) {

				if (finalObj != null && finalObj.length > 0) {

					for (int j = 0; j < finalObj.length; ++j) {
						int int_count = 0;
						int label_count = 0;
						objData1[j][0] = checkNull(String
								.valueOf(finalObj[j][0]));// Employee//
						// Token
						labelObject[0] = "Employee Id";

						if (misreport.getAccNumberFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							// Name
							labelObject[++label_count] = labelNames[0];
						}
						if (misreport.getCurrencyCodeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							labelObject[++label_count] = labelNames[1];
						}
						if (misreport.getSolIdFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							labelObject[++label_count] = labelNames[2];
						}
						if (misreport.getCreditdebitFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							labelObject[++label_count] = labelNames[3];
						}
						if (misreport.getTransactiontypeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							labelObject[++label_count] = labelNames[4];

						}
						if (misreport.getIfsccodeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							labelObject[++label_count] = labelNames[5];
						}
						/*if (misreport.getEmpcodeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							labelObject[++label_count] = labelNames[6];
						}*/

						if (misreport.getEmpnameFlag().equals("true")) {

							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							labelObject[++label_count] = labelNames[6];
						}
						if (misreport.getTransactionDtlFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							labelObject[++label_count] = labelNames[7];
						}
						if (misreport.getCustomerrefnoFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							labelObject[++label_count] = labelNames[8];
						}

						if (misreport.getTransactionDateFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							labelObject[++label_count] = labelNames[9];
						}
						if (misreport.getBankFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//

							labelObject[++label_count] = labelNames[10];
						}
						// SERVICE TENURE
						if (misreport.getBankbrnNameFlag().equals("true")) {

							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							labelObject[++label_count] = labelNames[11];
						}
						// EMPLOYEE STATUS
						if (misreport.getBeneficaryEmailidFlag().equals("true")) {

							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count])); 
							 labelObject[++label_count] = labelNames[12];
						}
						if (misreport.getDivisionAccountNoFlag().equals("true")) {

							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count])); 
							 labelObject[++label_count] = labelNames[13];
						}

					}
				}

				String[] pageIndex = Utility.doPaging(misreport.getMyPage(),
						objData1.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					misreport.setMyPage("1");

				int numOfRec = Integer.parseInt(pageIndex[1])
						- Integer.parseInt(pageIndex[0]);
				int columnLength = count + 1;
				Object[][] pageObj = new Object[numOfRec][columnLength];
				int z = 0;
				int srNo = 1;

				System.out.println("Integer.parseInt(pageIndex[0])=========="
						+ Integer.parseInt(pageIndex[0]));

				System.out.println("Integer.parseInt(pageIndex[0])=========="
						+ Integer.parseInt(pageIndex[1]));

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {

					System.out
							.println("pageObj------in first fotr loop 1----columnLength------"
									+ columnLength);
					for (int j = 0; j < columnLength; j++) {

						System.out
								.println("pageObj------in first fotr loop 2222222222---------");
						logger.info("objData1[" + i + "][" + j + "] : "
								+ objData1[i][j]);
						pageObj[z][j] = objData1[i][j];

						System.out.println("pageObj----------------"
								+ pageObj[z][j]);
					}
					z++;
					srNo++;
					request.setAttribute("finalObj", pageObj);
				}
			} else {
				request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));
				misreport.setNoData("true");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}