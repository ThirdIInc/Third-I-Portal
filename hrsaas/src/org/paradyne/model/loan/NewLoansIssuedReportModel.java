package org.paradyne.model.loan;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Loan.NewLoansIssuedReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class NewLoansIssuedReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(NewLoansIssuedReportModel.class);

	public void getLoanType(NewLoansIssuedReport bean,
			HttpServletRequest request) {
		try {
			Object[][] loanTypeData = getSqlModel()
					.getSingleResult(getQuery(2));

			if (loanTypeData != null) {
				HashMap loanType = new HashMap();
				for (int i = 0; i < loanTypeData.length; i++) {
					loanType.put(String.valueOf(loanTypeData[i][0]), String
							.valueOf(loanTypeData[i][1]));
				}

				/* Sort the List */
				loanType = (HashMap<Object, Object>) org.paradyne.lib.Utility
						.sortMapByValue(loanType, null, true);
				bean.setLstLoanType(loanType);
			}

		} catch (Exception e) {
			logger
					.error("Error in NewLoansIssuedReportModel.getLoanType() method Model : "
							+ e.getMessage());
		}
	}

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(NewLoansIssuedReport misReport) {
		boolean result = false;
		try {
			String query = " SELECT * FROM HRMS_MISREPORT_HDR WHERE UPPER(REPORT_CRITERIA) LIKE '"
					+ misReport.getSettingName().trim().toUpperCase()
					+ "' AND REPORT_TYPE='Loan'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			e.printStackTrace();
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

	public boolean deleteSavedReport(NewLoansIssuedReport misreport) {
		boolean result = false;
		try {
			String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID="
					+ misreport.getReportId();
			String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID="
					+ misreport.getReportId();

			result = getSqlModel().singleExecute(deleteDetail);
			if (result)
				result = getSqlModel().singleExecute(deleteHeader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveReportCriteria(NewLoansIssuedReport misReport) {
		boolean result = false;
		try {
			if (!checkDuplicate(misReport)) {
				Object[][] saveObj = new Object[1][3];
				saveObj[0][0] = checkNull(misReport.getSettingName().trim());
				saveObj[0][1] = checkNull(misReport.getReportTitle().trim());
				saveObj[0][2] = "Loan";
				String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR(REPORT_ID, REPORT_CRITERIA, REPORT_TITLE,REPORT_TYPE)"
						+ " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?,?)";
				result = getSqlModel().singleExecute(insertHeader, saveObj);
				if (result) {
					String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR ";
					Object[][] codeObj = getSqlModel().getSingleResult(
							codeQuery);
					misReport.setReportId(String.valueOf(codeObj[0][0]));
					misReport.setReportTypeStr("Loan");
					if (saveFilters(misReport) && saveColumns(misReport)
							&& saveSortOptions(misReport)
							&& saveAdvancedFilters(misReport)) {
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

	public boolean saveAdvancedFilters(NewLoansIssuedReport misReport) {
		boolean result = false;
		int count = 0;

		try {
			if (!misReport.getLoanDateSelect().equals("")) {
				count++;
			}
			if (!misReport.getLoanFromDate().equals("")) {
				count++;
			}
			if (!misReport.getLoanToDate().equals("")) {
				count++;
			}

			logger.info("Count for Save advance filters : " + count);
			Object[][] addObj = new Object[count][2];
			int int_count = 0;
			if (!misReport.getLoanDateSelect().equals("")) {
				addObj[int_count][0] = "loanDateSelect";
				addObj[int_count][1] = checkNull(misReport.getLoanDateSelect()
						.trim());
				int_count++;
			}
			if (!misReport.getLoanFromDate().equals("")) {
				addObj[int_count][0] = "misreport.loanFromDate";
				addObj[int_count][1] = checkNull(misReport.getLoanFromDate()
						.trim());
				int_count++;
			}
			if (!misReport.getLoanToDate().equals("")) {
				addObj[int_count][0] = "misreport.loanToDate";
				addObj[int_count][1] = checkNull(misReport.getLoanToDate()
						.trim());
				int_count++;
			}

			logger.info("int_count     : " + int_count);
			if (int_count == 0) {
				return true;
			} else {
				String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				Object[][] maxCode = getSqlModel()
						.getSingleResult(maxCodeQuery);

				Object[][] addAdvanceFilters = new Object[int_count][3];
				for (int i = 0; i < addAdvanceFilters.length; i++) {
					addAdvanceFilters[i][0] = maxCode[0][0];
					addAdvanceFilters[i][1] = addObj[i][0];
					logger.info("addAdvanceFilters[" + i + "][1]  : "
							+ addAdvanceFilters[i][1]);
					addAdvanceFilters[i][2] = addObj[i][1];
					logger.info("addAdvanceFilters[" + i + "][2]  : "
							+ addAdvanceFilters[i][2]);

				}
				String insertAdvFilters = "INSERT INTO HRMS_MISREPORT_DTL(REPORT_ID, FIELD_NAME, FIELD_VALUE) "
						+ " VALUES (?, ?, ?) ";
				result = getSqlModel().singleExecute(insertAdvFilters,
						addAdvanceFilters);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveSortOptions(NewLoansIssuedReport misReport) {
		boolean result = false;
		int count = 0;

		try {
			if (!misReport.getSortBy().equals("1")) {
				count++;
			}
			if (!misReport.getHiddenSortBy().equals("")) {
				count++;
			}
			if (!misReport.getSortByAsc().equals("")) {
				count++;
			}
			if (!misReport.getSortByDsc().equals("")) {
				count++;
			}
			if (!misReport.getSortByOrder().equals("")) {
				count++;
			}
			if (!misReport.getThenBy1().equals("1")) {
				count++;
			}
			if (!misReport.getHiddenThenBy1().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder1Asc().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder1Dsc().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder1().equals("")) {
				count++;
			}
			if (!misReport.getThenBy2().equals("1")) {
				count++;
			}
			if (!misReport.getHiddenThenBy2().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder2Asc().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder2Dsc().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder2().equals("")) {
				count++;
			}
			if (!misReport.getHiddenColumns().equals("")) {
				count++;
			}
			logger.info("Count for Save sort options : " + count);
			Object[][] addObj = new Object[count][2];
			int int_count = 0;
			if (!misReport.getSortBy().equals("1")) {
				addObj[int_count][0] = "sortBy";
				addObj[int_count][1] = checkNull(misReport.getSortBy().trim());
				int_count++;
			}
			if (!misReport.getHiddenSortBy().equals("")) {
				addObj[int_count][0] = "hiddenSortBy";
				addObj[int_count][1] = checkNull(misReport.getHiddenSortBy()
						.trim());
				int_count++;
			}
			logger.info("getSortByAsc...." + misReport.getSortByAsc());
			if (!misReport.getSortByAsc().equals("")) {
				addObj[int_count][0] = "sortByAsc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getSortByAsc().trim());
				int_count++;
			}
			logger.info("getSortByDsc...." + misReport.getSortByDsc());
			if (!misReport.getSortByDsc().equals("")) {
				addObj[int_count][0] = "sortByDsc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getSortByDsc().trim());
				int_count++;
			}
			if (!misReport.getSortByOrder().equals("")) {
				addObj[int_count][0] = "sortByOrder";
				addObj[int_count][1] = checkNull(misReport.getSortByOrder()
						.trim());
				int_count++;
			}
			if (!misReport.getThenBy1().equals("1")) {
				addObj[int_count][0] = "thenBy1";
				addObj[int_count][1] = checkNull(misReport.getThenBy1().trim());
				int_count++;
			}
			if (!misReport.getHiddenThenBy1().equals("")) {
				addObj[int_count][0] = "hiddenThenBy1";
				addObj[int_count][1] = checkNull(misReport.getHiddenThenBy1()
						.trim());
				int_count++;
			}
			if (!misReport.getThenByOrder1Asc().equals("")) {
				addObj[int_count][0] = "thenByOrder1Asc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Asc().trim());
				int_count++;
			}
			if (!misReport.getThenByOrder1Dsc().equals("")) {
				addObj[int_count][0] = "thenByOrder1Dsc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Dsc().trim());
				int_count++;
			}
			if (!misReport.getThenByOrder1().equals("")) {
				addObj[int_count][0] = "thenByOrder1";
				addObj[int_count][1] = checkNull(misReport.getThenByOrder1()
						.trim());
				int_count++;
			}
			if (!misReport.getThenBy2().equals("1")) {
				addObj[int_count][0] = "thenBy2";
				addObj[int_count][1] = checkNull(misReport.getThenBy2().trim());
				int_count++;
			}
			if (!misReport.getHiddenThenBy2().equals("")) {
				addObj[int_count][0] = "hiddenThenBy2";
				addObj[int_count][1] = checkNull(misReport.getHiddenThenBy2()
						.trim());
				int_count++;
			}
			if (!misReport.getThenByOrder2Asc().equals("")) {
				addObj[int_count][0] = "thenByOrder2Asc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Asc().trim());
				int_count++;
			}
			if (!misReport.getThenByOrder2Dsc().equals("")) {
				addObj[int_count][0] = "thenByOrder2Dsc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Dsc().trim());
				int_count++;
			}
			if (!misReport.getThenByOrder2().equals("")) {
				addObj[int_count][0] = "thenByOrder2";
				addObj[int_count][1] = checkNull(misReport.getThenByOrder2()
						.trim());
				int_count++;
			}
			if (!misReport.getHiddenColumns().equals("")) {
				addObj[int_count][0] = "hiddenColumns";
				addObj[int_count][1] = checkNull(misReport.getHiddenColumns()
						.trim());
				int_count++;
			}
			logger.info("int_count     : " + int_count);
			if (int_count == 0) {
				return true;
			} else {
				String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR ";
				Object[][] maxCode = getSqlModel()
						.getSingleResult(maxCodeQuery);

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
				String insertSortOptions = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE  ) "
						+ " VALUES (?, ?, ? ) ";
				result = getSqlModel().singleExecute(insertSortOptions,
						addSortOptions);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFilters(NewLoansIssuedReport misReport) {
		boolean result = false;
		int count = 0;

		try {

			if (!misReport.getDivCode().equals("")) {
				count++;
			}// division code
			if (!misReport.getDivCode().equals("")) {
				count++;
			}// division name
			if (!misReport.getDeptCode().equals("")) {
				count++;
			}// benefit for
			if (!misReport.getDeptCode().equals("")) {
				count++;
			} // extra work type
			if (!misReport.getBranchCode().equals("")) {
				count++;
			} // benefit type
			if (!misReport.getBranchCode().equals("")) {
				count++;
			}// employee code
			if (!misReport.getDesignationCode().equals("")) {
				count++;
			}// employee name
			if (!misReport.getDesignationCode().equals("")) {
				count++;
			}// employee token
			if (!misReport.getEmpCode().equals("")) {
				count++;
			}// approver code
			if (!misReport.getEmpCode().equals("")) {
				count++;
			}// approver name
			if (!misReport.getEmpCode().equals("")) {
				count++;
			}// approver token

			if (!misReport.getLoanType().equals("1")) {
				count++;
			}// approver token
			
			//ADDED BY REEBA - IS REFUNDABLE
			if (!misReport.getRefundable().equals("1")) {
				count++;
			}
			logger.info("Count for Save filters : " + count);
			Object[][] addObj = new Object[count][2];
			int int_count = 0;

			if (!misReport.getDivCode().equals("")) {
				addObj[int_count][0] = "divCode";
				addObj[int_count][1] = checkNull(misReport.getDivCode().trim());
				int_count++;
			}
			if (!misReport.getDivName().equals("")) {
				addObj[int_count][0] = "divName";
				addObj[int_count][1] = checkNull(misReport.getDivName().trim());
				int_count++;
			}
			if (!misReport.getDeptCode().equals("")) {
				addObj[int_count][0] = "deptCode";
				addObj[int_count][1] = checkNull(misReport.getDeptCode().trim());
				int_count++;
			}
			if (!misReport.getDeptName().equals("")) {
				addObj[int_count][0] = "deptName";
				addObj[int_count][1] = checkNull(misReport.getDeptName().trim());
				int_count++;
			}
			if (!misReport.getBranchCode().equals("")) {
				addObj[int_count][0] = "branchCode";
				addObj[int_count][1] = checkNull(misReport.getBranchCode()
						.trim());
				int_count++;
			}
			if (!misReport.getBranchName().equals("")) {
				addObj[int_count][0] = "branchName";
				addObj[int_count][1] = checkNull(misReport.getBranchName()
						.trim());
				int_count++;
			}
			if (!misReport.getDesignationCode().equals("")) {
				addObj[int_count][0] = "designationCode";
				addObj[int_count][1] = checkNull(misReport.getDesignationCode()
						.trim());
				int_count++;
			}
			if (!misReport.getDesignationName().equals("")) {
				addObj[int_count][0] = "designationName";
				addObj[int_count][1] = checkNull(misReport.getDesignationName()
						.trim());
				int_count++;
			}
			if (!misReport.getEmpCode().equals("")) {
				addObj[int_count][0] = "empCode";
				addObj[int_count][1] = checkNull(misReport.getEmpCode().trim());
				int_count++;
			}
			if (!misReport.getEmpName().equals("")) {
				addObj[int_count][0] = "empName";
				addObj[int_count][1] = checkNull(misReport.getEmpName().trim());
				int_count++;
			}
			if (!misReport.getEmpToken().equals("")) {
				addObj[int_count][0] = "empToken";
				addObj[int_count][1] = checkNull(misReport.getEmpToken().trim());
				int_count++;
			}
			if (!misReport.getLoanType().equals("1")) {
				addObj[int_count][0] = "loanType";
				addObj[int_count][1] = checkNull(misReport.getLoanType().trim());
				int_count++;
			}
			//ADDED BY REEBA - IS REFUNDABLE
			if (!misReport.getRefundable().equals("1")) {
				addObj[int_count][0] = "refundable";
				addObj[int_count][1] = checkNull(misReport.getRefundable().trim());
				int_count++;
			}
			logger.info("int_count     : " + int_count);
			if (int_count == 0) {
				return true;
			} else {

				String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				Object[][] maxCode = getSqlModel()
						.getSingleResult(maxCodeQuery);

				Object[][] addFilters = new Object[int_count][3];
				for (int i = 0; i < addFilters.length; i++) {
					addFilters[i][0] = maxCode[0][0];
					addFilters[i][1] = addObj[i][0];
					logger.info("addFilters[" + i + "][1]  : "
							+ addFilters[i][1]);
					addFilters[i][2] = addObj[i][1];
					logger.info("addFilters[" + i + "][2]  : "
							+ addFilters[i][2]);

				}
				String insertFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
						+ " VALUES (?, ?, ?) ";
				result = getSqlModel().singleExecute(insertFilters, addFilters);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveColumns(NewLoansIssuedReport misReport) {
		boolean result = false;
		int count = 0;
		try {
			if (misReport.getEmpNameFlag().equals("true")) {
				count++;
			}
			if (misReport.getDivFlag().equals("true")) {
				count++;
			}
			if (misReport.getLoanAmountFlag().equals("true")) {
				count++;
			}
			if (misReport.getLoanTypeFlag().equals("true")) {
				count++;
			}
			if (misReport.getLoanPaymentDateFlag().equals("true")) {
				count++;
			}
			if (misReport.getLoanInstallmentFlag().equals("true")) {
				count++;
			}
			if (misReport.getNoOfInstallFlag().equals("true")) {
				count++;
			}

			logger.info("Count for Save columns : " + count);
			Object[][] addObj = new Object[count][2];
			int int_count = 0;
			if (misReport.getEmpNameFlag().equals("true")) {
				addObj[int_count][0] = "misreport.empNameFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getDivFlag().equals("true")) {
				addObj[int_count][0] = "misreport.divFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getLoanAmountFlag().equals("true")) {
				addObj[int_count][0] = "misreport.loanAmountFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getLoanTypeFlag().equals("true")) {
				addObj[int_count][0] = "misreport.loanTypeFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getLoanPaymentDateFlag().equals("true")) {
				addObj[int_count][0] = "misreport.loanPaymentDateFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getLoanInstallmentFlag().equals("true")) {
				addObj[int_count][0] = "misreport.loanInstallmentFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getNoOfInstallFlag().equals("true")) {
				addObj[int_count][0] = "misreport.noOfInstallFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}

			logger.info("int_count     : " + int_count);
			if (int_count == 0) {
				return true;
			} else {
				String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR  ";
				Object[][] maxCode = getSqlModel()
						.getSingleResult(maxCodeQuery);

				Object[][] addColumns = new Object[int_count][3];
				for (int i = 0; i < addColumns.length; i++) {
					addColumns[i][0] = maxCode[0][0];
					addColumns[i][1] = addObj[i][0];
					logger.info("addColumns[" + i + "][1]  : "
							+ addColumns[i][1]);
					addColumns[i][2] = addObj[i][1];
					logger.info("addColumns[" + i + "][2]  : "
							+ addColumns[i][2]);

				}
				String insertColumns = "INSERT INTO HRMS_MISREPORT_DTL(REPORT_ID, FIELD_NAME, FIELD_VALUE ) "
						+ " VALUES (?, ?, ? ) ";
				result = getSqlModel().singleExecute(insertColumns, addColumns);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setDetailOnScreen(NewLoansIssuedReport misreport) {

		String dtlQuery = "SELECT HRMS_MISREPORT_DTL.REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL "
				+ "  INNER JOIN HRMS_MISREPORT_HDR  ON (HRMS_MISREPORT_HDR.REPORT_ID= HRMS_MISREPORT_DTL.REPORT_ID )"
				+ " WHERE HRMS_MISREPORT_DTL.REPORT_ID ="
				+ misreport.getReportId();

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
					Method modelMethod = NewLoansIssuedReport.class
							.getDeclaredMethod("set" + initCap(methodStr),
									String.class);
					// misreport = MISReport.class.newInstance();
					logger.info(" dtlObj[i][2]     :"
							+ String.valueOf(dtlObj[i][2]));
					modelMethod.invoke(misreport, String.valueOf(dtlObj[i][2]));
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

	public String getReport(NewLoansIssuedReport bean,
			HttpServletResponse response, String[] labelNames,
			HttpServletRequest request) {

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
				reportName = " New Loans Issued MIS Report";
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
			Object[][] creditsObj = null;
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

					// employee name
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
				}
			}
			Object[][] objData1 = null;
			if (finalObj != null && finalObj.length > 0) {
				objData1 = new Object[finalObj.length][count + 1];

				for (int i = 0; i < finalObj.length; ++i) {
					int int_count = 0;
					objData1[i][0] = checkNull(String.valueOf(finalObj[i][0]));// Employee//
					// Token
					if (bean.getEmpNameFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));// Employee//
						// Name
					}
					if (bean.getDivFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getLoanAmountFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getLoanTypeFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getLoanPaymentDateFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));// Employee//
						// Name

					}
					if (bean.getLoanInstallmentFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));//  
					}

					if (bean.getNoOfInstallFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));//  
					}
				}

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
						rg.tableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals("X")) {
						// rg.setFName(reportName + ".xls");
						rg.setFName(reportName);
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
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
						rg.xlsTableBody(str_colNames, objData1, cellWidth,
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
			// TODO: handle exception
		}
		return null;
	}

	private String conditionQuery(NewLoansIssuedReport bean, String[] labelNames) {

		String query = "";

		try {
			query = " FROM HRMS_LOAN_APPLICATION "
					+ " A INNER JOIN HRMS_EMP_OFFC B ON (A.LOAN_EMP_ID = B.EMP_ID)"
					+ " INNER JOIN HRMS_LOAN_PROCESS C ON (A.LOAN_APPL_CODE = C.LOAN_APPL_CODE) ";

			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				query += " WHERE EMP_DIV IN (" + bean.getUserProfileDivision()
						+ ")";
			} else {
				query += "  " + " WHERE 1=1 ";
			}

			/* Loan Type */
			if (!bean.getLoanType().equals("1")) {
				query += " AND A.LOAN_CODE = " + bean.getLoanType();
			}

			/* Division */
			if (!bean.getDivCode().equals("")) {
				query += " AND B.EMP_DIV = " + bean.getDivCode();
			}

			/* Branch */
			if (!bean.getBranchCode().equals("")) {
				query += " AND B.EMP_CENTER = " + bean.getBranchCode();
			}

			/* Department */
			if (!bean.getDeptCode().equals("")) {
				query += " AND B.EMP_DEPT = " + bean.getDeptCode();
			}

			/* Designation */
			if (!bean.getDesignationCode().equals("")) {
				query += " AND B.EMP_RANK = " + bean.getDesignationCode();
			}

			/* Individual Employee */
			if (!bean.getEmpCode().equals("")) {
				query += " AND B.EMP_ID = " + bean.getEmpCode();
			}
			//ADDED BY REEBA - IS REFUNDABLE
			if (!bean.getRefundable().equals("1")) {
				query += " AND A.LOAN_PF_TYPE = '" + bean.getRefundable()+"'";
			}

			if (!bean.getLoanDateSelect().trim().equals("")) {
				if (bean.getLoanDateSelect().trim().equals("ON")) {
					query += " AND C.LOAN_PAYMENT_DATE = TO_DATE('"
							+ bean.getLoanFromDate() + "','DD-MM-YYYY')";
				}

				if (bean.getLoanDateSelect().trim().equals("OB")) {
					query += " AND C.LOAN_PAYMENT_DATE  <= TO_DATE('"
							+ bean.getLoanFromDate() + "','DD-MM-YYYY')";
				}

				if (bean.getLoanDateSelect().trim().equals("OA")) {
					query += " AND C.LOAN_PAYMENT_DATE >= TO_DATE('"
							+ bean.getLoanFromDate() + "','DD-MM-YYYY')";
				}

				if (bean.getLoanDateSelect().trim().equals("BF")) {
					query += " AND C.LOAN_PAYMENT_DATE  < TO_DATE('"
							+ bean.getLoanFromDate() + "','DD-MM-YYYY')";
				}

				if (bean.getLoanDateSelect().trim().equals("AF")) {
					query += " AND C.LOAN_PAYMENT_DATE   > TO_DATE('"
							+ bean.getLoanFromDate() + "','DD-MM-YYYY')";
				}

				if (bean.getLoanDateSelect().trim().equals("BN")) {
					query += " AND C.LOAN_PAYMENT_DATE  >= TO_DATE('"
							+ bean.getLoanFromDate() + "','DD-MM-YYYY') ";

					if (!(bean.getLoanToDate().equals("") || bean
							.getLoanToDate().equals("dd-mm-yyyy"))) {
						query += "AND C.LOAN_PAYMENT_DATE  <= TO_DATE('"
								+ bean.getLoanToDate() + "','DD-MM-YYYY') ";
					}
				}
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
			// TODO: handle exception
		}
		
		 
		return query;
	}

	public String getSortVal(String Status, String[] labelNames) {
		logger.info("Sort Status............" + Status);

		String sql = "";
		if (Status.equals(labelNames[0])) {
			sql = " NVL(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,' ')";
		} else if (Status.equals(labelNames[1])) {
			sql = "(SELECT DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID = B.EMP_DIV) AS DIV_NAME ";

		} else if (Status.equals(labelNames[2])) {
			sql = " TO_CHAR(LOAN_AMOUNT, '99,999,999.99') AS LOAN_AMOUNT ";

		}

		else if (Status.equals(labelNames[3])) {
			sql = " (SELECT LOAN_NAME FROM HRMS_LOAN_MASTER WHERE LOAN_CODE =	A.LOAN_CODE) AS LOAN_NAME ";

		} else if (Status.equals(labelNames[4])) {
			sql = " TO_CHAR(C.LOAN_PAYMENT_DATE,'DD-MON-YYYY') AS LOAN_PAYMENT_DATE ";

		} else if (Status.equals(labelNames[5])) {
			sql = " NVL(TO_CHAR((SELECT CASE WHEN LOAN_INTALMENT_NUMBERS IS NULL  THEN LOAN_REQ_EMI_AMOUNT ELSE LOAN_SANCTION_AMOUNT/LOAN_INTALMENT_NUMBERS END FROM HRMS_LOAN_PROCESS  WHERE LOAN_APPL_CODE = A.LOAN_APPL_CODE),'99,999,999.99'), '-') AS MONTHLY_INSTALLMENT ";

		}

		else if (Status.equals(labelNames[6])) {
			sql = " NVL(TO_CHAR(C.LOAN_INTALMENT_NUMBERS),'-') AS NO_OF_INSTALLEMENT ";

		} else if (Status.equals("Employee Code")) {
			sql = "  EMP_TOKEN ";

		}
		return sql;
	}

	public String getSortOrder(String Status) {

		System.out.println("Status-----------------" + Status);
		String sql = "";
		if (Status.equals("A")) {
			sql = "ASC";
		}
		if (Status.equals("D")) {
			sql = "DESC";
		}
		return sql;
	}

	private String selectQuery(NewLoansIssuedReport bean, String[] labelNames,
			int count, String[] splitComma, HttpServletRequest request) {
		// TODO Auto-generated method stub

		String query = "";
		try {
			String labels = "Employee Code,";
			query = " SELECT  EMP_TOKEN ";

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
						logger.info("in name");
						columnMap
								.put(Integer.parseInt(splitDash[0]),
										" NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ')");
						labelMap.put(1, labelNames[0]);
						count++;
					} else if (splitDash[1].equals(labelNames[1])) {
						// query += " ,NVL(DIV_NAME,' ') ";
						columnMap
								.put(Integer.parseInt(splitDash[0]),
										" (SELECT DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID = B.EMP_DIV) AS DIV_NAME ");
						labelMap.put(2, labelNames[1]);
						count++;
					} else if (splitDash[1].equals(labelNames[2])) {
						// query += " ,NVL(DEPT_NAME,' ') ";
						columnMap
								.put(Integer.parseInt(splitDash[0]),
										"TO_CHAR(LOAN_AMOUNT, '99,999,999.99') AS LOAN_AMOUNT ");
						labelMap.put(3, labelNames[2]);
						count++;
					} else if (splitDash[1].equals(labelNames[3])) {
						// query += " ,NVL(DEPT_NAME,' ') ";
						columnMap
								.put(
										Integer.parseInt(splitDash[0]),
										" (SELECT LOAN_NAME FROM HRMS_LOAN_MASTER WHERE LOAN_CODE =	A.LOAN_CODE) AS LOAN_NAME ");
						labelMap.put(4, labelNames[3]);
						count++;
					} else if (splitDash[1].equals(labelNames[4])) {
						// query += " ,NVL(DEPT_NAME,' ') ";
						columnMap
								.put(Integer.parseInt(splitDash[0]),
										" TO_CHAR(C.LOAN_PAYMENT_DATE,'DD-MON-YYYY') AS LOAN_PAYMENT_DATE ");
						labelMap.put(5, labelNames[4]);
						count++;
					} else if (splitDash[1].equals(labelNames[5])) {
						// query += " ,NVL(DEPT_NAME,' ') ";
						columnMap
								.put(
										Integer.parseInt(splitDash[0]),
										"NVL(TO_CHAR((SELECT CASE WHEN LOAN_INTALMENT_NUMBERS IS NULL  THEN LOAN_REQ_EMI_AMOUNT ELSE LOAN_SANCTION_AMOUNT/LOAN_INTALMENT_NUMBERS END FROM HRMS_LOAN_PROCESS  WHERE LOAN_APPL_CODE = A.LOAN_APPL_CODE),'99,999,999.99'), '-') AS MONTHLY_INSTALLMENT ");
						labelMap.put(6, labelNames[5]);
						count++;
					} else if (splitDash[1].equals(labelNames[6])) {
						// query += " ,NVL(DEPT_NAME,' ') ";
						columnMap
								.put(Integer.parseInt(splitDash[0]),
										" NVL(TO_CHAR(C.LOAN_INTALMENT_NUMBERS),'-') AS NO_OF_INSTALLEMENT ");
						labelMap.put(7, labelNames[6]);
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
			// request.setAttribute("labelValues", labels);

			logger.info("count in selectQuery method : " + count);
			query += " , A.LOAN_EMP_ID " + "#" + count + "#" + labels;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return query;
	}

	public void callViewScreen(NewLoansIssuedReport misreport,
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

				if (finalObj != null && finalObj.length > 0) {

					for (int j = 0; j < finalObj.length; ++j) {
						int int_count = 0;
						int label_count = 0;
						objData1[j][0] = checkNull(String
								.valueOf(finalObj[j][0]));// Employee//
						// Token
						labelObject[0] = "Employee Id";
						if (misreport.getEmpNameFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							// Name
							labelObject[++label_count] = labelNames[0];
						}
						if (misreport.getDivFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[1];
						}
						if (misreport.getLoanAmountFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[2];
						}
						if (misreport.getLoanTypeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[3];
						}
						if (misreport.getLoanPaymentDateFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							// Name
							labelObject[++label_count] = labelNames[4];

						}
						if (misreport.getLoanInstallmentFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							// Name
							labelObject[++label_count] = labelNames[5];
						}

						if (misreport.getNoOfInstallFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee//
							// Name
							labelObject[++label_count] = labelNames[6];
						}

					}

					String[] pageIndex = Utility.doPaging(
							misreport.getMyPage(), objData1.length, 20);
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
 
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer
							.parseInt(pageIndex[1]); i++) {
						for (int j = 0; j < columnLength; j++) {
 ;
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

				}

			} else {
				request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));
				misreport.setNoData("true");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
