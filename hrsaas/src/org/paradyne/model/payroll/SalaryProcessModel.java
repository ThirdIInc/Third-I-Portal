package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.SalaryProcess;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;
import org.paradyne.model.payroll.salary.UploadCreditModel;

/**
 * @author : AA0418 Prakash Shetkar * Date : May 11, 2010
 * @modified by : AA0554 Mangesh Jadhav Date : June 25, 2010
 * 
 */
public class SalaryProcessModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SalaryProcessModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	Object[][] typeData = null, braData = null, pf_data_obj = null,
			ptax_data_obj = null, esi_data_obj = null;
	HashMap<String, String> recalCreditMap = new HashMap<String, String>();
	HashMap<String, String> recalDebitMap = new HashMap<String, String>();
	HashMap<String, String> recalSalMap = new HashMap<String, String>();

	/**
	 * Get the filters, like branch, department, paybill, employee type,
	 * division, from Payroll Settings
	 * 
	 * @return Object[][] as list of filters
	 */
	public Object[][] getPayrollConfig() {
		Object[][] salaryFiltersObj = null;
		try {
			String attendanceFiltersSql = " SELECT DECODE(NVL(CONF_BRN_FLAG,'N'), 'Y', 'true','N','false') AS BRANCH_FLAG, "
					+ " DECODE(NVL(CONF_DEPT_FLAG,'N'), 'Y', 'true', 'N', 'false') AS DEPARTMENT_FLAG, "
					+ " DECODE(NVL(CONF_PAYBILL_FLAG,'N'), 'Y', 'true', 'N', 'false') AS PAYBILL_FLAG, "
					+ " DECODE(NVL(CONF_EMPTYPE_FLAG,'N'), 'Y', 'true', 'N', 'false') AS EMPLOYEE_TYPE_FLAG, "
					+ " DECODE(NVL(CONF_DIVISION_FLAG,'N'), 'Y', 'true', 'N', 'false') AS DIVISION_FLAG, "
					+
					/*
					 * recovery flag removed from query
					 */
					" CONF_RECORDS_PER_PAGE,NVL(CONF_JOINDAYS_FLAG,'N'),NVL(CONF_RECOVERY_FLAG,'N'), NVL(CONF_PROFHANDI_FLAG,'N'),"
					+
					// "
					// CONF_RECORDS_PER_PAGE,NVL(CONF_JOINDAYS_FLAG,'N'),NVL(CONF_RECOVERY_FLAG,'N'),
					// NVL(CONF_PROFHANDI_FLAG,'N'),"+

					" NVL(CONF_TAX_WORKFLOW_FLAG,'N'),NVL(CONF_VPF,'N'), "
					+
					/*
					 * recovery debit code removed from query
					 */
					// "
					// NVL(CONF_CREDIT_ROUND,0),NVL(CONF_TOTCREDIT_OPTION,0),NVL(CONF_TOTDEBIT_ROUND,0),NVL(CONF_NETPAY_ROUND,0),NVL(CONF_RECOVERY_DEBIT,0)"+
					" NVL(CONF_CREDIT_ROUND,0),NVL(CONF_TOTCREDIT_OPTION,0),NVL(CONF_TOTDEBIT_ROUND,0),NVL(CONF_NETPAY_ROUND,0),0"
					+ " ,NVL(CONF_EPF,'Y') FROM HRMS_SALARY_CONF ";

			salaryFiltersObj = getSqlModel().getSingleResult(
					attendanceFiltersSql);
		} catch (Exception e) {
			logger.error("Exception in getFilters:" + e);
		}
		return salaryFiltersObj;
	}

	/**
	 * Method : input. 
	 * Purpose : this method is used to set initial list page
	 * 
	 * @return String
	 */
	public Object[][] getLwfConfig() {
		Object[][] lwfConfigObj = null;
		try {
			String lwfQuery = " SELECT LWF_APPLICABLE,LWF_DEBIT_CODE,LWF_CREDIT_CODE FROM HRMS_LWF_CONFIGURATION  ";
			lwfConfigObj = getSqlModel().getSingleResult(lwfQuery);
		} catch (Exception e) {
			logger.error("Exception in getLwfConfig:" + e);
		}
		return lwfConfigObj;
	}

	/**
	 * Purpose : this method is used to check whether entered table exists or
	 * not
	 * 
	 * @param year
	 * @return boolean
	 */
	public boolean isTableExist(String year) {
		boolean result = false;
		Object[][] tableObj = null;
		try {
			String tableQuery = "SELECT * FROM HRMS_SALARY_" + year;
			tableObj = getSqlModel().getSingleResult(tableQuery);
		} catch (Exception e) {
			logger.error("Exception in isTableExist:" + e);
		}
		if (tableObj != null) {
			result = true;
		}
		return result;
	} // end of method tableExist()

	/**
	 * Purpose : this method is used to check the status of salary process,
	 * i.e., 'ATTN_START','ATTN_READY','SAL_START','SAL_FINAL'
	 * 
	 * @param bean
	 * @return status
	 */
	public String checkSalaryProcessStatus(SalaryProcess bean,
			String[] listOfFilters) {
		String status = "";
		try {
			String lockQuery = " SELECT LEDGER_STATUS,LEDGER_MONTH,LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE  LEDGER_MONTH ="
					+ bean.getMonth() + " AND LEDGER_YEAR = " + bean.getYear();
			lockQuery = setSalaryLedgerFiletrs(listOfFilters, lockQuery);
			Object[][] statusObj = getSqlModel().getSingleResult(lockQuery);
			if (statusObj != null && statusObj.length > 0) {
				status = String.valueOf(statusObj[0][0]);
				bean.setLedgerCode(String.valueOf(statusObj[0][2]));
			}
		} catch (Exception e) {
			logger.error("Exception in checkSalaryProcessStatus:" + e);
		}
		return status;
	} // end of method checkProcessStatus()

	/**
	 * Purpose : this method is used to setfilters
	 * @param listOfFilters
	 * @return string
	 */
	public String setEmpFiletrs(String[] listOfFilters) {
		String filterQuery = "";
		try {
			// if branch is selected
			if (!listOfFilters[0].equals("")) {
				filterQuery += " AND EMP_CENTER = " + listOfFilters[0];
			}
			// if department is selected
			if (!listOfFilters[1].equals("")) {
				filterQuery += " AND EMP_DEPT = " + listOfFilters[1];
			}
			// if paybill group is selected
			if (!listOfFilters[2].equals("")) {
				filterQuery += " AND EMP_PAYBILL = " + listOfFilters[2];
			}
			// if employee type is selected
			if (!listOfFilters[3].equals("")) {
				filterQuery += " AND EMP_TYPE = " + listOfFilters[3];
			}
			// if division is selected
			if (!listOfFilters[4].equals("")) {

				filterQuery += " AND SAL_DIVISION = " + listOfFilters[4];
			}
		} catch (Exception e) {
			logger.error("Exception in setSalaryLedgerFiletrs:" + e);
		}
		return filterQuery;
	}

	/**
	 * Purpose : this method is used to get performance configuration
	 * @return object
	 */
	public Object[][] getPerformanceConfig() {
		String query = "";
		Object[][] performanceData = null;
		try {
			query = "SELECT PERF_IS_ACTIVE , PERF_CREDIT_CODE, PERF_RATING_TYPE,PERF_RATING_FORMULA  FROM HRMS_PERFORMANCE_CONF WHERE PERF_IS_ACTIVE='Y'";
			performanceData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("Exception in setSalaryLedgerFiletrs:" + e);
		}
		return performanceData;
	}

	/**
	 * Purpose : this method is used to get employee rating data
	 * @param listOfFilters
	 * @param month
	 * @param year
	 * @return map
	 */
	public HashMap<String, String> getEmpRatingData(String empIdString,
			String month, String year) {
		String query = "";
		HashMap<String, String> performanceDataMap = null;
		Object[][] performanceDataObj = null;
		int intMonth = Integer.parseInt(month) - 1;
		int intYear = Integer.parseInt(year);
		if (intMonth == 0) {
			intMonth = 12;
			intYear = intYear - 1;
		}
		try {
			query = " SELECT RATING_EMP_ID, RATING FROM HRMS_EMP_MONTHLY_RATING"
					+ " INNER JOIN HRMS_EMP_OFFC ON(EMP_ID=RATING_EMP_ID)"
					+ "WHERE RATING_MONTH="
					+ intMonth
					+ " AND RATING_YEAR="
					+ intYear+" "+Utility.getConcatenatedIds("RATING_EMP_ID", empIdString);
			// if branch is selected
			/*if (!listOfFilters[0].equals("")) {
				query += " AND EMP_CENTER = " + listOfFilters[0];
			}
			// if department is selected
			if (!listOfFilters[1].equals("")) {
				query += " AND EMP_DEPT = " + listOfFilters[1];
			}
			// if paybill group is selected
			if (!listOfFilters[2].equals("")) {
				query += " AND EMP_PAYBILL = " + listOfFilters[2];
			}
			// if employee type is selected
			if (!listOfFilters[3].equals("")) {
				query += " AND EMP_TYPE = " + listOfFilters[3];
			}
			// if division is selected
			if (!listOfFilters[4].equals("")) {
				query += " AND EMP_DIV = " + listOfFilters[4];
			}*/
			performanceDataObj = getSqlModel().getSingleResult(query);
			if (performanceDataObj != null && performanceDataObj.length > 0) {
				performanceDataMap = new HashMap<String, String>();
				for (int i = 0; i < performanceDataObj.length; i++) {
					performanceDataMap.put(String
							.valueOf(performanceDataObj[i][0]), String
							.valueOf(performanceDataObj[i][1]));

				}
			}
			// performanceDataMap=getSqlModel().getSingleResultMap(query,0,2);
		} catch (Exception e) {
			logger.error("Exception in setSalaryLedgerFiletrs:" + e);
		}
		return performanceDataMap;
	}

	/**
	 * Purpose : this method is used to get employee CTC
	 * @param listOfFilters
	 * @return mapo
	 */
	public HashMap<String, String> getEmpCTCData(String empIdString) {
		String query = "";
		HashMap<String, String> ctcDataMap = null;
		Object[][] ctcDataObj = null;
		try {
			query = " SELECT HRMS_SALARY_MISC.EMP_ID, NVL(CTC,0) FROM HRMS_EMP_OFFC"
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) WHERE 1=1  "
					+ Utility.getConcatenatedIds("HRMS_EMP_OFFC.EMP_ID", empIdString);
			/*// if branch is selected
			if (!listOfFilters[0].equals("")) {
				query += " AND EMP_CENTER = " + listOfFilters[0];
			}
			// if department is selected
			if (!listOfFilters[1].equals("")) {
				query += " AND EMP_DEPT = " + listOfFilters[1];
			}
			// if paybill group is selected
			if (!listOfFilters[2].equals("")) {
				query += " AND EMP_PAYBILL = " + listOfFilters[2];
			}
			// if employee type is selected
			if (!listOfFilters[3].equals("")) {
				query += " AND EMP_TYPE = " + listOfFilters[3];
			}
			// if division is selected
			if (!listOfFilters[4].equals("")) {
				query += " AND EMP_DIV = " + listOfFilters[4];
			}*/
			ctcDataObj = getSqlModel().getSingleResult(query);
			if (ctcDataObj != null && ctcDataObj.length > 0) {
				ctcDataMap = new HashMap<String, String>();
				for (int i = 0; i < ctcDataObj.length; i++) {
					ctcDataMap.put(String.valueOf(ctcDataObj[i][0]), String
							.valueOf(ctcDataObj[i][1]));

				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpCTCData:" + e);
		}
		return ctcDataMap;
	}

	/**
	 * Purpose : this method is used to set salary process filter
	 * @param listOfFilters
	 * @param sqlQuery
	 * @return
	 */
	private String setSalaryLedgerFiletrs(String[] listOfFilters,
			String sqlQuery) {
		try {
			// if branch is selected
			if (!listOfFilters[0].equals("")) {
				sqlQuery += " AND LEDGER_BRN = " + listOfFilters[0];
			}
			// if department is selected
			if (!listOfFilters[1].equals("")) {
				sqlQuery += " AND LEDGER_DEPT = " + listOfFilters[1];
			}
			// if paybill group is selected
			if (!listOfFilters[2].equals("")) {
				sqlQuery += " AND LEDGER_PAYBILL = " + listOfFilters[2];
			}
			// if employee type is selected
			if (!listOfFilters[3].equals("")) {
				sqlQuery += " AND LEDGER_EMPTYPE = " + listOfFilters[3];
			}
			// if division is selected
			if (!listOfFilters[4].equals("")) {
				sqlQuery += " AND LEDGER_DIVISION = " + listOfFilters[4];
			}
		} catch (Exception e) {
			logger.error("Exception in setSalaryLedgerFiletrs:" + e);
		}
		return sqlQuery;
	}

	/**
	 * Purpose : this method is used to get employee attendance information
	 * @param year
	 * @param ledgerCode
	 * @param empCode
	 * @return object
	 */
	public Object[][] getAttendanceEmployeeToProcess(String year,
			String ledgerCode, String empCode) {
		Object[][] empObj = null;
		try {
			// for selecting the employee from monthly attendance for selected
			// ledgercode
			String hrsQuery = "NVL(ATTN_SAL_DAYS,0)||'d:'||NVL(TO_CHAR(ATTN_SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(ATTN_SAL_HRS,'MI'),'00')||'m'";

			String selectSalary = " SELECT HRMS_EMP_OFFC.EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME,EMP_CENTER,CENTER_LOCATION,nvl(CENTER_PTAX_STATE,15),EMP_TYPE, "
					+ hrsQuery
					+
					// " ,NVL(EMP_ISHANDICAP,'N')," + handicapped condition
					// removed
					" ,NVL(SAL_PTAX_FLAG,'Y'),"
					+ " NVL(SAL_EPF_FLAG,'N'),NVL(SAL_VPF_FLAG,'N'),NVL(SAL_GPF_FLAG,'N'),NVL(SAL_PFTRUST_FLAG,'N'),"
					+
					/*
					 * recovery flag removed
					 */
					// "
					// NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),'00:00'),NVL(SAL_LWF_FLAG,'N'),NVL(ATTN_RECOVERY_DAYS,0)
					// "+
					" NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),'00:00'),NVL(SAL_LWF_FLAG,'N'),0  "
					+ " ,NVL(EMP_ONHOLD_FLAG,'N'),EMP_DIV  ,NVL(EMP_RANK,0),NVL(EMP_CADRE,0) FROM HRMS_MONTH_ATTENDANCE_"
					+ year
					+ " INNER JOIN HRMS_EMP_OFFC  on HRMS_MONTH_ATTENDANCE_"
					+ year
					+ ".ATTN_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) "
					+ " LEFT JOIN HRMS_EMP_PERS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_PERS.EMP_ID )"
					+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_PTAX_STATE) "
					+ " LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_MONTH_ATTENDANCE_"
					+ year
					+ ".ATTN_EMP_ID "
					+ " LEFT JOIN HRMS_SHIFT ON  HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID "
					+ " WHERE ATTN_CODE = " + ledgerCode;
			if (!empCode.equals("")) {
				selectSalary += " AND HRMS_EMP_OFFC.EMP_ID IN(" + empCode
						+ ") ";
			}
			selectSalary += " ORDER BY UPPER(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";

			empObj = getSqlModel().getSingleResult(selectSalary);

		} catch (Exception e) {
			logger.error("Exception in getEmployee:" + e);
		}
		return empObj;
	}

	/**
	 * Purpose : this method is used to getcredit code
	 * @return object
	 */
	public Object[][] getCreditCodes() {
		Object[][] resultObj = null;
		try {
			String query = " SELECT HRMS_CREDIT_HEAD.CREDIT_CODE,NVL(CREDIT_APPLICABLE_ESI,'N'),NVL(CREDIT_APPLICABLE_PTAX,'N'),NVL(CREDIT_TYPE,1),NVL(CREDIT_NAME,' '),NVL(CREDIT_FORMULA,'0'),NVL(CREDIT_MAXCAP,0) FROM  HRMS_CREDIT_HEAD WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE ";
			resultObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("Exception in getCreditCodes:" + e);
		}
		return resultObj;
	}

	/**
	 * Purpose : this method is used to get debit codes
	 * @return object
	 */
	public Object[][] getDebitCodes() {
		Object[][] resultObj = null;
		try {
			String query = " SELECT HRMS_DEBIT_HEAD.DEBIT_CODE,NVL(DEBIT_ROUND,0),NVL(DEBIT_TYPE,'SG'),NVL(DEBIT_FORMULA,'0'),NVL(DEBIT_NAME,' ') FROM  HRMS_DEBIT_HEAD where HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' order BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE ";
			resultObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("Exception in getCreditCodes:" + e);
		}
		return resultObj;
	}

	/**
	 * Purpose : this method is used to get employee credit map
	 * @param listOfFilters
	 * @return map
	 */
	public HashMap<String, Object[][]> getEmpCreditMap(String empIdString) {
		HashMap<String, Object[][]> empCreditMap = new HashMap<String, Object[][]>();
		try {
			String creditQuery = " SELECT HRMS_EMP_OFFC.EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0),NVL(CREDIT_APPLICABLE_ESI,'N'),NVL(CREDIT_APPLICABLE_PTAX,'N'), "
					+ " NVL(CREDIT_TYPE,1),NVL(CREDIT_FORMULA,'0'),NVL(CREDIT_MAXCAP,0) FROM HRMS_CREDIT_HEAD "
					+ " LEFT JOIN HRMS_EMP_CREDIT ON HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "+Utility.getConcatenatedIds("HRMS_EMP_OFFC.EMP_ID", empIdString);
			
			// if branch is selected
			/*if (!listOfFilters[0].equals("")) {
				creditQuery += " AND HRMS_EMP_OFFC.EMP_CENTER="
						+ listOfFilters[0];
			}
			// if department is selected
			if (!listOfFilters[1].equals("")) {
				creditQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = "
						+ listOfFilters[1];
			}
			// if paybill group is selected
			if (!listOfFilters[2].equals("")) {
				creditQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL = "
						+ listOfFilters[2];
			}
			// if employee type is selected
			if (!listOfFilters[3].equals("")) {
				creditQuery += " AND HRMS_EMP_OFFC.EMP_TYPE = "
						+ listOfFilters[3];
			}
			// if division is selected
			if (!listOfFilters[4].equals("")) {
				creditQuery += " AND HRMS_EMP_OFFC.EMP_DIV = "
						+ listOfFilters[4];
			}*/
			creditQuery += " ORDER BY EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE ";

			empCreditMap = getSqlModel().getSingleResultMap(creditQuery, 0, 2);
		} catch (Exception e) {
			logger.error("Exception in getEmpCreditMap:" + e);
		}
		return empCreditMap;
	}

	/**
	 * Purpose : this method is used to get employee debit map
	 * @param listOfFilters
	 * @return
	 */
	public HashMap<String, Object[][]> getEmpDebitMap(String empIdString) {
		HashMap<String, Object[][]> empDebitMap = new HashMap<String, Object[][]>();
		try {
			String debitQuery = " SELECT HRMS_EMP_OFFC.EMP_ID,HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(DEBIT_AMT,0),NVL(DEBIT_ROUND,0),NVL(DEBIT_TYPE,'SG'),NVL(DEBIT_FORMULA,'0') "
					+ " FROM HRMS_DEBIT_HEAD "
					+ " LEFT JOIN HRMS_EMP_DEBIT ON HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_DEBIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' "+Utility.getConcatenatedIds("HRMS_EMP_OFFC.EMP_ID", empIdString);
			// if branch is selected
			/*if (!listOfFilters[0].equals("")) {
				debitQuery += " AND HRMS_EMP_OFFC.EMP_CENTER="
						+ listOfFilters[0];
			}
			// if department is selected
			if (!listOfFilters[1].equals("")) {
				debitQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = "
						+ listOfFilters[1];
			}
			// if paybill group is selected
			if (!listOfFilters[2].equals("")) {
				debitQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL = "
						+ listOfFilters[2];
			}
			// if employee type is selected
			if (!listOfFilters[3].equals("")) {
				debitQuery += " AND HRMS_EMP_OFFC.EMP_TYPE = "
						+ listOfFilters[3];
			}
			// if division is selected
			if (!listOfFilters[4].equals("")) {
				debitQuery += " AND HRMS_EMP_OFFC.EMP_DIV = "
						+ listOfFilters[4];
			}*/
			debitQuery += " ORDER BY EMP_ID,HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE ";
			empDebitMap = getSqlModel().getSingleResultMap(debitQuery, 0, 2);
		} catch (Exception e) {
			logger.error("Exception in getEmpDebitMap:" + e);
		}
		return empDebitMap;
	}

	/**
	 * Purpose : this method is used to get make employee size object
	 * @param empMap
	 * @param creditCodes
	 * @return
	 */
	public HashMap<String, Object[][]> makeEmpObjSameSize(
			HashMap<String, Object[][]> empMap, Object[][] creditCodes) {
		try {
			if (empMap != null && empMap.size() > 0) {
				for (Iterator k = empMap.keySet().iterator(); k.hasNext();) {
					Object[][] tempObj = empMap.get(String.valueOf(k.next()));
					Object[][] modifiedObj = new Object[creditCodes.length][tempObj[0].length];
					if (tempObj != null && tempObj.length > 0) {
						int j = 0;
						try {
							for (int i = 0; i < creditCodes.length; i++) {
								if (j < tempObj.length
										&& String
												.valueOf(creditCodes[i][0])
												.equals(
														String
																.valueOf(tempObj[j][1]))) {

									for (int l = 0; l < modifiedObj[0].length; l++) {
										modifiedObj[i][l] = tempObj[j][l];
									}
									j++;
								} else {
									int count = 0;
									for (int l = 0; l < modifiedObj[0].length; l++) {
										if (l == 0) {
											modifiedObj[i][l] = tempObj[0][0];
											count = 0;
										} else if (l == 1)
											modifiedObj[i][l] = creditCodes[i][0];
										else if (l == 2)
											modifiedObj[i][l] = 0;
										else {
											modifiedObj[i][l] = creditCodes[i][count++];
										}
									}
								}
							}
						} catch (Exception e) {
							logger
									.error("Exception in processEmpCredit inner loop:"
											+ e);
							e.printStackTrace();
						}
						empMap.put(String.valueOf(tempObj[0][0]), modifiedObj);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		return empMap;
	}

	/**
	 * Purpose : this method is used to get monthly employee credit 
	 * @param empCreditObj
	 * @param attnDays
	 * @param shiftHrs
	 * @param month
	 * @param year
	 * @param joinDaysFlag
	 * @param creditRound
	 * @param empRating
	 * @param ratingConfigObj
	 * @return object
	 */
	public Object[][] getMonthlyEmpCredit(Object[][] empCreditObj,
			String attnDays, String shiftHrs, String month, String year,
			String joinDaysFlag, String creditRound, String[] empRating,
			Object[][] ratingConfigObj) {
		Object[][] month_credit_amount = null;
		Object[][] emp_month_credit = null;
		String empRatingData[] = new String[2];
		empRatingData[0] = empRating[0];
		empRatingData[1] = empRating[1];
		try {
			if (empCreditObj != null && empCreditObj.length > 0) {
				Object[] daysHrsMinObj = getDaysWithHrsAndMinutes(attnDays);

				String salDays = String.valueOf(daysHrsMinObj[0]);
				String SalMinutes = convertToMinutes(String
						.valueOf(daysHrsMinObj[1]));
				String shiftMinutes = convertToMinutes(shiftHrs);
				// this section is used to get maximum days of Month
				Calendar cal = Calendar.getInstance();
				cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
				int daysOfMonth = cal
						.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

				month_credit_amount = new Object[empCreditObj.length][4];
				emp_month_credit = new Object[empCreditObj.length][2];
				for (int i = 0; i < emp_month_credit.length; i++) {

					emp_month_credit[i][0] = String.valueOf(empCreditObj[i][1]);
					emp_month_credit[i][1] = String.valueOf(empCreditObj[i][2]);
				}
				for (int i = 0; i < empCreditObj.length; i++) {
					double hourCreditAmt = 0;
					double monthlyCreditAmount = 0;
					double monthlyCTCAmount = Double
							.parseDouble(checkNull_Zero(empRating[1]));
					month_credit_amount[i][0] = String
							.valueOf(empCreditObj[i][1]); // credit code
					if (attnDays.equals("0d:00h:00m")) {
						monthlyCreditAmount = 0.0;
					} else if (String.valueOf(empCreditObj[i][5]).equals("2")) { // if credit type is fixed
						monthlyCreditAmount = (Double
								.parseDouble(checkNull_Zero(String
										.valueOf(empCreditObj[i][2]))));
					} else if (String.valueOf(empCreditObj[i][5]).equals("3")) { // if credit type is formula
						monthlyCreditAmount = (Double
								.parseDouble(checkNull_Zero(String
										.valueOf(empCreditObj[i][2]))));
						if(monthlyCreditAmount>0){  // calculate only if credit conf amount >0
						monthlyCreditAmount = Utility
								.expressionEvaluate(new Utility()
										.generateFormulaPay(
												emp_month_credit,
												String
														.valueOf(empCreditObj[i][6]),
												context, session));
						try {
							if (Double.parseDouble(SalMinutes) != 0
									&& Double.parseDouble(shiftMinutes) != 0) {
								if (joinDaysFlag.equals("N")) {  // calculate for joining month
									hourCreditAmt = ((monthlyCreditAmount * Double
											.parseDouble(SalMinutes)) / (daysOfMonth * Double
											.parseDouble(shiftMinutes)));
								} else {
									if (Double.parseDouble(String
											.valueOf(salDays)) == daysOfMonth) {
										hourCreditAmt = ((monthlyCreditAmount * Double
												.parseDouble(SalMinutes)) / (daysOfMonth * Double
												.parseDouble(shiftMinutes)));
									} else {
										hourCreditAmt = ((monthlyCreditAmount * Double
												.parseDouble(SalMinutes)) / (30 * Double
												.parseDouble(shiftMinutes)));
									}
								}
							}

						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpCredit inner lop for hrs amt calculation:"
											+ e);
							hourCreditAmt = 0;
						}

						try {
							if (joinDaysFlag.equals("N")) { // calculate for joining month
								monthlyCreditAmount = (((monthlyCreditAmount * Double
										.parseDouble(String.valueOf(salDays))) / daysOfMonth) + hourCreditAmt);
							} else {
								if (Double.parseDouble(String.valueOf(salDays)) == daysOfMonth) {
									monthlyCreditAmount = ((monthlyCreditAmount * Double
											.parseDouble(String
													.valueOf(salDays))) / daysOfMonth)
											+ hourCreditAmt;
								} else {
									monthlyCreditAmount = ((monthlyCreditAmount * Double
											.parseDouble(String
													.valueOf(salDays))) / daysOfMonth)
											+ hourCreditAmt;
								}
							}
						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpCredit inner lop for days amt calculation:"
											+ e);
							monthlyCreditAmount = 0;
						}
						// condition to check the maximim value of the credit
						if (Double.parseDouble(String
								.valueOf(empCreditObj[i][7])) > 0
								&& monthlyCreditAmount > Double
										.parseDouble(String
												.valueOf(empCreditObj[i][7]))) {
							monthlyCreditAmount = Double.parseDouble(String
									.valueOf(empCreditObj[i][7]));
						}
						} // end zero value condition
					}

					else {   //if credit type is based on salary days
						try {
							if (Double.parseDouble(SalMinutes) != 0
									&& Double.parseDouble(shiftMinutes) != 0) {
								if (joinDaysFlag.equals("N")) {  // calculate for joining month
									hourCreditAmt = ((Double
											.parseDouble(checkNull_Zero(String
													.valueOf(empCreditObj[i][2]))) * Double
											.parseDouble(SalMinutes)) / (daysOfMonth * Double
											.parseDouble(shiftMinutes)));
								} else {
									if (Double.parseDouble(String
											.valueOf(salDays)) == daysOfMonth) {
										hourCreditAmt = ((Double
												.parseDouble(checkNull_Zero(String
														.valueOf(empCreditObj[i][2]))) * Double
												.parseDouble(SalMinutes)) / (daysOfMonth * Double
												.parseDouble(shiftMinutes)));
									} else {
										hourCreditAmt = ((Double
												.parseDouble(checkNull_Zero(String
														.valueOf(empCreditObj[i][2]))) * Double
												.parseDouble(SalMinutes)) / (30 * Double
												.parseDouble(shiftMinutes)));
									}
								}
							}

						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpCredit inner lop for hrs amt calculation:"
											+ e);
							hourCreditAmt = 0;
						}

						try {
							if (joinDaysFlag.equals("N")) {  // calculate for joining month
								monthlyCreditAmount = (((Double
										.parseDouble(checkNull_Zero(String
												.valueOf(empCreditObj[i][2]))) * Double
										.parseDouble(String.valueOf(salDays))) / daysOfMonth) + hourCreditAmt);
								monthlyCTCAmount = monthlyCTCAmount
										* (Double.parseDouble(String
												.valueOf(salDays)) / daysOfMonth);
							} else {
								if (Double.parseDouble(String.valueOf(salDays)) == daysOfMonth) {
									monthlyCreditAmount = ((Double
											.parseDouble(checkNull_Zero(String
													.valueOf(empCreditObj[i][2]))) * Double
											.parseDouble(String
													.valueOf(salDays))) / daysOfMonth)
											+ hourCreditAmt;
									monthlyCTCAmount = monthlyCTCAmount
											* (Double.parseDouble(String
													.valueOf(salDays)) / daysOfMonth);
								} else {
									monthlyCreditAmount = ((Double
											.parseDouble(checkNull_Zero(String
													.valueOf(empCreditObj[i][2]))) * Double
											.parseDouble(String
													.valueOf(salDays))) / daysOfMonth)
											+ hourCreditAmt;
									monthlyCTCAmount = monthlyCTCAmount
											* (Double.parseDouble(String
													.valueOf(salDays)) / daysOfMonth);
								}
							}
						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpCredit inner lop for days amt calculation:"
											+ e);
							monthlyCreditAmount = 0;
						}
					}
					empRatingData[1] = String.valueOf(monthlyCTCAmount);
					if (empRatingData[0] != null
							&& !empRatingData[0].equals("")
							&& !empRatingData[0].equals("null")) {
						if (ratingConfigObj != null
								&& ratingConfigObj.length > 0) {
							if (String.valueOf(ratingConfigObj[0][1]).equals(
									String.valueOf(empCreditObj[i][1]))) {
								// logger.info("emp_id==="+String.valueOf(empCreditObj[0][0]));
								monthlyCreditAmount = getMonthlyPerformanceAmt(
										monthlyCreditAmount, empRatingData,
										ratingConfigObj);
							}
						}
					}
					month_credit_amount[i][1] = Utility
							.twoDecimals(roundCheck(Integer
									.parseInt(creditRound), monthlyCreditAmount));
					month_credit_amount[i][2] = String
							.valueOf(empCreditObj[i][3]);
					month_credit_amount[i][3] = String
							.valueOf(empCreditObj[i][4]);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getMonthlyEmpCredit:" + e);
		}
		return month_credit_amount;
	}

	/**
	 * Purpose : this method is used to get monthly employee debit
	 * @param debitHead
	 * @param monthCreditObj
	 * @param empData
	 * @param grossCreditObj
	 * @param pf_configData
	 * @param pfTrust_configData
	 * @param vpf_configData
	 * @param esi_configData
	 * @param lwfCodeList
	 * @param lwfMap
	 * @param incomeTax_configData
	 * @param emp_loanObject
	 * @param emp_licObject
	 * @param generalData
	 * @param miscSalaryMap
	 * @param empId
	 * @param lwfCreditApplicableMap
	 * @param lwfApplicableEmpGradeMap
	 * @param lwfApplicableEmpDesgMap
	 * @return ArrayList
	 */
	public ArrayList getMonthlyEmpDebit(Object[][] debitHead,
			Object[][] monthCreditObj, String[] empData,
			Object[][] grossCreditObj, Object[][] pf_configData,
			Object[][] pfTrust_configData, Object[][] vpf_configData,
			Object[][] esi_configData, ArrayList<Object[]> lwfCodeList,
			HashMap<String, Object[][]> lwfMap,
			Object[][] incomeTax_configData, Object[][] emp_loanObject,
			Object[][] emp_licObject, String[] generalData,
			HashMap<String, Object[][]> miscSalaryMap, String empId,
			HashMap<String, Object[][]> lwfCreditApplicableMap,
			HashMap<String, Object[][]> lwfApplicableEmpGradeMap,
			HashMap<String, Object[][]> lwfApplicableEmpDesgMap) {
		// debitHead[0][4] is debit type i.e.formula,fixed,system generated and
		// debitHead[0][5]is formula value or fixed amount value
		double monthCreditTotal = 0, grossCreditTotal = 0;
		double pfAmt = 0, vpfAmt = 0, pfTrustAmt = 0;
		double monthESICreditTotal = 0, esiAmt = 0, grossESICreditTotal = 0;
		double monthPTAXCreditTotal = 0, ptaxAmt = 0;
		double lwfAmt = 0;
		double loanAmt = 0;
		double licAmt = 0;
		double recoveryAmt = 0;
		double monthDebitTotal = 0;
		double monthNetPayTotal = 0;

		ArrayList dataList = new ArrayList();
		Object[][] month_Debit_amount = null;
		Object[][] ptax_configData = null;
		try {
			String[] dataString = null;

			String EPFApplicability = empData[0];
			String empTypeId = empData[1];
			String branchId = empData[2];
			String pfTrustApplicability = empData[3];
			String vpfApplicability = empData[4];
			String emp_id = empData[5];
			String empPTAXFlag = empData[6];
			String location = empData[7];
			String empLwfApplicability = empData[8];
			// String recoveryDays = empData[9];

			Object[] daysHrsMinObj = getDaysWithHrsAndMinutes(empData[10]);
			double salDays = Double.parseDouble(String
					.valueOf(daysHrsMinObj[0]));

			String path = generalData[0];
			String month = generalData[1];
			String year = generalData[2];
			String comLedgerCode = generalData[3];
			String lwfApplicability = generalData[5];
			String lwfCreditHead = generalData[6];

			// String lwfDebitHead = generalData [7];

			String lwfDebitHead = getEmpLWFDebitCode(location, lwfCodeList);

			String incomeTaxFlag = generalData[8];
			String creditTotalRound = generalData[9];
			String debitTotalRound = generalData[10];
			String netPayRound = generalData[11];
			// String recoveryFlag = generalData [12];
			// String recoveryDebitCode = generalData [13];
			// String divEsicFlag = generalData [14]; // Division-wise ESIC flag

			Calendar cal = Calendar.getInstance();

			cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));

			int daysOfMonth = cal
					.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

			if (EPFApplicability.equals("Y")) {
				try {
					if (pf_configData != null && pf_configData.length > 0) {
						dataString = new String[3];
						dataString[0] = empTypeId;
						dataString[1] = branchId;
						dataString[2] = path;
						Object[][] empGrossObj = Utility.removeColumns(
								grossCreditObj, new int[] { 0, 5 });
						pfAmt = getEmpPFAmt(empGrossObj, monthCreditObj,
								pf_configData, dataString);
					}
				} catch (Exception e) {
					logger
							.error("Exception in getMonthlyEmpDebit -- epfCalculation :"
									+ e);
					pfAmt = 0;
				}
			} else if (pfTrustApplicability.equals("Y")) {
				try {
					if (pfTrust_configData != null
							&& pfTrust_configData.length > 0) {
						pfTrustAmt = getEmpPftrustAmt(monthCreditObj,
								pfTrust_configData);
					}
				} catch (Exception e) {
					logger
							.error("Exception in getMonthlyEmpDebit -- pfTrustCalculation :"
									+ e);
					pfTrustAmt = 0;
				}
			}

			if (vpf_configData != null && vpf_configData.length > 0) {
				try {
					if (vpfApplicability.equals("Y")) {
						vpfAmt = getEmpVPFAmt(monthCreditObj, vpf_configData);
					}
				} catch (Exception e) {
					logger
							.error("Exception in getMonthlyEmpDebit -- vpfCalculation :"
									+ e);
				}
			}

			if (monthCreditObj != null && monthCreditObj.length > 0) {
				for (int i = 0; i < monthCreditObj.length; i++) {

					try {// totalCredit
						monthCreditTotal += Double.parseDouble(String
								.valueOf(monthCreditObj[i][1]));
					} catch (Exception e) {
						logger
								.error("Exception in getMonthlyEmpDebit -- monthCreditTotal :"
										+ e);
					}

					try {// totalESICCredit
						if (String.valueOf(monthCreditObj[i][2]).trim().equals(
								"Y")) {
							monthESICreditTotal += Double.parseDouble(String
									.valueOf(monthCreditObj[i][1]));
						}
					} catch (Exception e) {
						logger
								.error("Exception in getMonthlyEmpDebit -- monthESICreditTotal :"
										+ e);
					}

					try {// totalPTAXCredit
						if (String.valueOf(monthCreditObj[i][3]).trim().equals(
								"Y")) {
							monthPTAXCreditTotal += Double.parseDouble(String
									.valueOf(monthCreditObj[i][1]));
						}
					} catch (Exception e) {
						logger
								.error("Exception in getMonthlyEmpDebit -- monthPTAXCreditTotal :"
										+ e);
					}
				}

				try {
					monthCreditTotal = roundCheck(Integer
							.parseInt(creditTotalRound), monthCreditTotal);
					monthESICreditTotal = roundCheck(Integer
							.parseInt(creditTotalRound), monthESICreditTotal);
					monthPTAXCreditTotal = roundCheck(Integer
							.parseInt(creditTotalRound), monthPTAXCreditTotal);
				} catch (Exception e) {
					logger
							.error("Exception in getMonthlyEmpDebit -- applying roundCheck to monthCreditTotal :"
									+ e);
				}
			}

			if (grossCreditObj != null && grossCreditObj.length > 0) {
				for (int i = 0; i < grossCreditObj.length; i++) {

					try {
						grossCreditTotal += Double.parseDouble(String
								.valueOf(grossCreditObj[i][2]));
					} catch (Exception e) {
						logger
								.error("Exception in getMonthlyEmpDebit -- grossCreditTotal :"
										+ e);
					}

					try {
						if (String.valueOf(grossCreditObj[i][3]).trim().equals(
								"Y")) {
							grossESICreditTotal += Double.parseDouble(String
									.valueOf(grossCreditObj[i][2]));
						}
					} catch (Exception e) {
						logger
								.error("Exception in getMonthlyEmpDebit -- grossESICreditTotal :"
										+ e);
					}
				}
				try {

				} catch (Exception e) {
					logger
							.error("Exception in getMonthlyEmpDebit -- applying roundCheck grossCreditTotal:"
									+ e);
				}
			}

			esi_configData = getESIData(path, month, year);
			// if(esi_configData != null && esi_configData.length > 0 &&
			// divEsicFlag.equals("Y")) {
			if (esi_configData != null && esi_configData.length > 0) {
				try {
					dataString = new String[6];
					dataString[0] = empTypeId;
					dataString[1] = branchId;
					dataString[2] = path;
					dataString[3] = month;
					dataString[4] = year;
					dataString[5] = emp_id;
					esiAmt = getEmpESIAmt(esi_configData, dataString,
							grossESICreditTotal, monthESICreditTotal,
							comLedgerCode);
					
					logger.info("the final ESIC value ="+esiAmt);
					
				} catch (Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- esiAmt :"
							+ e);
				}
			}

			if (empPTAXFlag.equals("N")) {
				ptaxAmt = 0;
			} else {
				try {
					// System.out.println("----");
					ptax_configData = getPtaxAmount(path, month, year,
							location, monthPTAXCreditTotal);
					if (ptax_configData != null && ptax_configData.length > 0) {
						// System.out.println("----:"+ptax_configData);
						dataString = new String[4];
						dataString[0] = empTypeId;
						dataString[1] = branchId;
						dataString[2] = path;
						dataString[3] = month;
						ptaxAmt = getEmpPTAXAmt(ptax_configData, dataString);
					}
				} catch (Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- ptaxAmt :"
							+ e);
				}
			}

			// if(lwfApplicability.equals("Y")) {
			// if(empLwfApplicability.equals("Y")) {
			try {
				if (lwfCodeList != null && lwfCodeList.size() > 0) {
					dataString = new String[6];
					dataString[0] = location;
					dataString[1] = emp_id;
					dataString[2] = lwfCreditHead;

					dataString[3] = empData[11];// DIV
					dataString[4] = empData[12];// DESG
					dataString[5] = empData[13];// GRADE
					lwfAmt = getEmpLwfAmt(monthCreditObj, lwfCodeList, lwfMap,
							dataString, lwfCreditApplicableMap,
							lwfApplicableEmpGradeMap, lwfApplicableEmpDesgMap);
				}
			} catch (Exception e) {
				logger.error("Exception in getMonthlyEmpDebit -- lwfAmt :" + e);
			}
			// }
			// }
			// recovery amount calculation added by Mangesh
			/*
			 * if(recoveryFlag.equals("Y")) {
			 * 
			 * try { if(recoveryDebitCode != null && !
			 * recoveryDebitCode.equals("0") && ! recoveryDays.equals("0")) {
			 * dataString = new String [5]; dataString[0]=month;
			 * dataString[1]=year; dataString[2]=recoveryDebitCode;
			 * dataString[3]=recoveryDays; dataString[4]=emp_id; recoveryAmt =
			 * getEmpRecoveryAmt(dataString); } } catch (Exception e) {
			 * logger.error("Exception in getMonthlyEmpDebit -- recoveryAmt :" +
			 * e); }
			 *  }
			 */
			/*
			 * try{ for (int i = 0; i < debitHead.length; i++) { for (int j = 0;
			 * j < debitHead[0].length; j++) {
			 * logger.info("debitHead["+i+"]["+j+"]==="+debitHead[i][j]); } }
			 * }catch (Exception e) { logger.info("exception in printing debit
			 * head"); }
			 */
			if (debitHead != null && debitHead.length > 0) {
				month_Debit_amount = new Object[debitHead.length][2];
				for (int i = 0; i < debitHead.length; i++) {

					month_Debit_amount[i][0] = debitHead[i][1];
					month_Debit_amount[i][1] = Utility
							.twoDecimals(roundCheck(Integer.parseInt(String
									.valueOf(debitHead[i][3])), Double
									.parseDouble(String
											.valueOf(debitHead[i][2]))));

					if (String.valueOf(debitHead[i][4]).equals("SG")) {
						try {
							if (pf_configData != null
									&& pf_configData.length > 0) {
								if (String.valueOf(debitHead[i][1]).trim()
										.equals(
												String.valueOf(
														pf_configData[0][0])
														.trim())) {
									month_Debit_amount[i][1] = (roundCheck(
											Integer.parseInt(String
													.valueOf(debitHead[i][3])),
											pfAmt));
								}
							}
						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpDebit -- setting debitHead pfAmt-- :"
											+ e);
						}
						try {
							if (ptax_configData != null
									&& ptax_configData.length > 0) {
								if (String.valueOf(debitHead[i][1]).trim()
										.equals(
												String.valueOf(
														ptax_configData[0][7])
														.trim())) {
									month_Debit_amount[i][1] = Utility
											.twoDecimals(roundCheck(
													Integer
															.parseInt(String
																	.valueOf(debitHead[i][3])),
													ptaxAmt));
								}
							}
						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpDebit -- setting debitHead ptaxAmt-- :"
											+ e);
						}
						try {
							if (esi_configData != null
									&& esi_configData.length > 0) {
								if (String.valueOf(debitHead[i][1]).trim()
										.equals(
												String.valueOf(
														esi_configData[0][0])
														.trim())) {
									month_Debit_amount[i][1] = (roundCheck(
											Integer.parseInt(String
													.valueOf(debitHead[i][3])),
											esiAmt));
								}
							}
						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpDebit -- setting debitHead esiAmt-- :"
											+ e);
						}

						try {
							if (String.valueOf(debitHead[i][1]).trim().equals(
									lwfDebitHead)) {
								month_Debit_amount[i][1] = Utility
										.twoDecimals(roundCheck(
												Integer
														.parseInt(String
																.valueOf(debitHead[i][3])),
												(Double
														.parseDouble(String
																.valueOf(month_Debit_amount[i][1])) + lwfAmt)));
							}
						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpDebit -- setting debitHead lwfAmt-- :"
											+ e);
						}

						/*
						 * try { if(recoveryFlag != null &&
						 * recoveryFlag.equals("Y") ) { if
						 * (String.valueOf(debitHead[i][1]).trim().equals(recoveryDebitCode)) {
						 * month_Debit_amount[i][1] =
						 * Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])),
						 * recoveryAmt)); } } }catch(Exception e) {
						 * logger.error("Exception in getMonthlyEmpDebit --
						 * setting debitHead ptaxAmt-- :" + e); }
						 */

						try {
							if (incomeTaxFlag.equals("Y")) {

							} else {
								if (incomeTax_configData != null
										&& incomeTax_configData.length > 0) {
									if (String
											.valueOf(debitHead[i][1])
											.trim()
											.equals(
													String
															.valueOf(
																	incomeTax_configData[0][0])
															.trim()))
										month_Debit_amount[i][1] = "0";
								}
							}
						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpDebit -- setting debitHead incomeTax-- :"
											+ e);
						}
						try {
							if (pfTrust_configData != null
									&& pfTrust_configData.length > 0) {
								if (pfTrustApplicability.equals("Y"))
									if (String
											.valueOf(debitHead[i][1])
											.trim()
											.equals(
													String
															.valueOf(
																	pfTrust_configData[0][0])
															.trim())) {
										month_Debit_amount[i][1] = Utility
												.twoDecimals(roundCheck(
														Integer
																.parseInt(String
																		.valueOf(debitHead[i][3])),
														pfTrustAmt));
									}
							}
						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpDebit -- setting debitHead pfTrustAmt-- :"
											+ e);
						}
						try {
							if (vpf_configData != null
									&& vpf_configData.length > 0) {
								if (vpfApplicability.equals("Y")) {
									double vpfMaxAmt = Utility
											.expressionEvaluate(new Utility()
													.generateFormulaPay(
															monthCreditObj,
															String
																	.valueOf(vpf_configData[0][3]),
															context, session));
									if (String.valueOf(vpf_configData[0][2])
											.equals("FR")) {
										if (String
												.valueOf(debitHead[i][1])
												.trim()
												.equals(
														String
																.valueOf(
																		vpf_configData[0][1])
																.trim())) {
											month_Debit_amount[i][1] = Utility
													.twoDecimals(roundCheck(
															Integer
																	.parseInt(String
																			.valueOf(debitHead[i][3])),
															vpfAmt));
										}
									} else {
										if (String
												.valueOf(debitHead[i][1])
												.trim()
												.equals(
														String
																.valueOf(
																		vpf_configData[0][1])
																.trim())) {
											if (Double.parseDouble(String
													.valueOf(debitHead[i][2])) > vpfMaxAmt)
												month_Debit_amount[i][1] = Utility
														.twoDecimals(roundCheck(
																Integer
																		.parseInt(String
																				.valueOf(debitHead[i][3])),
																vpfMaxAmt));
											else
												month_Debit_amount[i][1] = Utility
														.twoDecimals(roundCheck(
																Integer
																		.parseInt(String
																				.valueOf(debitHead[i][3])),
																Double
																		.parseDouble(String
																				.valueOf(debitHead[i][2]))));
										}
									}
								} else if (String.valueOf(debitHead[i][1])
										.trim().equals(
												String.valueOf(
														vpf_configData[0][1])
														.trim())) {
									month_Debit_amount[i][1] = "0";

								}
							}
						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpDebit -- setting debitHead vpfAmt-- :"
											+ e);
						}
						try {
							if (emp_loanObject != null
									&& emp_loanObject.length > 0) {
								for (int j = 0; j < emp_loanObject.length; j++) {
									if (String
											.valueOf(debitHead[i][1])
											.trim()
											.equals(
													String
															.valueOf(
																	emp_loanObject[j][2])
															.trim())) {
										loanAmt = loanAmt
												+ (Double
														.parseDouble(Utility
																.twoDecimals(String
																		.valueOf(emp_loanObject[j][0]))));
										month_Debit_amount[i][1] = Utility
												.twoDecimals(roundCheck(
														Integer
																.parseInt(String
																		.valueOf(debitHead[i][3])),
														loanAmt));
									}
								}
								loanAmt = 0;
							}
						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpDebit -- setting debitHead loanAmt-- :"
											+ e);
						}
						try {
							if (emp_licObject != null
									&& emp_licObject.length > 0) {
								for (int j = 0; j < emp_licObject.length; j++) {
									if (String
											.valueOf(debitHead[i][1])
											.trim()
											.equals(
													String
															.valueOf(
																	emp_licObject[j][2])
															.trim())) {
										licAmt = licAmt
												+ (Double
														.parseDouble(Utility
																.twoDecimals(String
																		.valueOf(emp_licObject[j][1]))));
										month_Debit_amount[i][1] = Utility
												.twoDecimals(roundCheck(
														Integer
																.parseInt(String
																		.valueOf(debitHead[i][3])),
														licAmt));
									}
								}
								licAmt = 0;
							}
						} catch (Exception e) {
							logger
									.error("Exception in getMonthlyEmpDebit -- setting debitHead licAmt-- :"
											+ e);
						}
					} else { // if debit head is other than PF,
								// LWF,ESIC,INCOME TAX, TAX,PTAX

						String debitType = String.valueOf(debitHead[i][4]);

						// logger.info("debitType===="+debitType);
						/*
						 * for (int j = 0; j < monthCreditObj.length; j++) { for
						 * (int k = 0; k < monthCreditObj[0].length; k++) {
						 * logger.info("monthCreditObj["+j+"]["+k+"]===="+monthCreditObj[j][k]); } }
						 */
						if (debitType.equals("FR")) { // debit formula
							try {
								String debitFormula = String
										.valueOf(debitHead[i][5]);
								double debitAmt = Utility
										.expressionEvaluate(new Utility()
												.generateFormulaPay(
														monthCreditObj,
														debitFormula, context,
														session));
								month_Debit_amount[i][1] = Utility
										.twoDecimals(roundCheck(
												Integer
														.parseInt(String
																.valueOf(debitHead[i][3])),
												debitAmt));
							} catch (Exception e) {
								month_Debit_amount[i][1] = "0";
								logger
										.error("exception in debit calculation by formula"
												+ e);
							}
						} else {
							String debitValue = String.valueOf(debitHead[i][2]);
							if (debitType.equals("FX")) { // fixed
								if (empData[10].equals("0d:00h:00m")
										|| salDays == 0) {
									month_Debit_amount[i][1] = 0;
								} else {
									month_Debit_amount[i][1] = Utility
											.twoDecimals(roundCheck(
													Integer
															.parseInt(String
																	.valueOf(debitHead[i][3])),
													Double
															.parseDouble(debitValue)));
								}
							} else if (debitType.equals("SD")) { // as per
																	// salary
																	// days.
								double debitDaysAmt = 0;
								debitDaysAmt = Double.parseDouble(debitValue)
										/ daysOfMonth * salDays;

								month_Debit_amount[i][1] = Utility
										.twoDecimals(roundCheck(
												Integer
														.parseInt(String
																.valueOf(debitHead[i][3])),
												debitDaysAmt));
							}
						}

					}
					monthDebitTotal += Double.parseDouble(String
							.valueOf(month_Debit_amount[i][1]));

					// END OF MAIN FOR LOOP
					if (monthCreditTotal < monthDebitTotal) {
						monthDebitTotal -= Double.parseDouble(String
								.valueOf(month_Debit_amount[i][1]));
						month_Debit_amount[i][1] = "0";
					}

				}
				/*
				 * try { monthDebitTotal =
				 * roundCheck(Integer.parseInt(debitTotalRound),
				 * monthDebitTotal); }catch(Exception e) {
				 * logger.error("Exception in getMonthlyEmpDebit -- applying
				 * roundCheck monthDebitTotal-- :" + e); }
				 */
			}// END OF MAIN FOR LOOP

			// CODING FOR MISC UPLOADED..IF IS OVERIDE='Y' THEN ORRIDE IT ELSE
			// APPEND THE VALUES
			// key=empid+creditcode+C
			// logger.info("miscSalaryMap=="+miscSalaryMap.size());
			if (miscSalaryMap != null && miscSalaryMap.size() > 0) {
				monthDebitTotal = 0.0;
				for (int k = 0; k < month_Debit_amount.length; k++) {
					String key = empId + "#" + month_Debit_amount[k][0] + "#D";
					// System.out.println("key:::::::::"+key);
					if (miscSalaryMap != null && miscSalaryMap.size() > 0) {
						Object[][] miscObj = miscSalaryMap.get(key);
						if (miscObj != null && miscObj.length > 0) {
							if (String.valueOf(miscObj[0][2]).equals("Y")) {
								month_Debit_amount[k][1] = miscObj[0][1];// amt
							} else {
								month_Debit_amount[k][1] = Double
										.parseDouble(String
												.valueOf(month_Debit_amount[k][1]))
										+ Double.parseDouble(String
												.valueOf(miscObj[0][1]));// amt
							}
						}

						monthDebitTotal += Double.parseDouble(String
								.valueOf(month_Debit_amount[k][1]));
						if (monthCreditTotal < monthDebitTotal) {
							monthDebitTotal -= Double.parseDouble(String
									.valueOf(month_Debit_amount[k][1]));
							month_Debit_amount[k][1] = "0";
						}

					}
				}// END MISC FOR LOOP
			}
			try {
				monthDebitTotal = roundCheck(Integer.parseInt(debitTotalRound),
						monthDebitTotal);
			} catch (Exception e) {
				logger
						.error("Exception in getMonthlyEmpDebit -- applying roundCheck monthDebitTotal-- :"
								+ e);
			}

			try {
				monthNetPayTotal = monthCreditTotal - monthDebitTotal;
				monthNetPayTotal = roundCheck(Integer.parseInt(netPayRound),
						monthNetPayTotal);
			} catch (Exception e) {
				logger
						.error("Exception in getMonthlyEmpDebit -- applying roundCheck netPay-- :"
								+ e);
			}

		} catch (Exception e) {
			logger.error("Exception in getMonthlyEmpDebit:" + e);
			e.printStackTrace();
		}

		/*
		 * try{ for (int i = 0; i < month_Debit_amount.length; i++) { for (int j =
		 * 0; j < month_Debit_amount[0].length; j++) {
		 * logger.info("month_Debit_amount["+i+"]["+j+"]==="+month_Debit_amount[i][j]); } }
		 * }catch (Exception e) { logger.info("exception in printing
		 * month_Debit_amount"); }
		 */
		dataList.add(0, month_Debit_amount);
		dataList.add(1, new Object[][] { { Utility
				.twoDecimals(monthCreditTotal) } });
		dataList.add(2,
				new Object[][] { { Utility.twoDecimals(monthDebitTotal) } });
		dataList.add(3, new Object[][] { { Utility
				.twoDecimals(monthNetPayTotal) } });
		return dataList;
	}

	/**
	 * Purpose : this method is used to getemployee pf amount
	 * @param creditSalObj
	 * @param PF_ConfigObj
	 * @param dataString
	 * @return double
	 */
	public double getEmpPFAmt(Object[][] creditSalObj, Object[][] PF_ConfigObj,
			String[] dataString) {
		double pf_amt = 0;
		double pfEmoluments = 0;
		// double pfEmolumentsSal=0;

		try {
			String typeId = dataString[0];
			String branchId = dataString[1];
			String path = dataString[2];
			if (PF_ConfigObj != null && PF_ConfigObj.length > 0) {
				pfEmoluments = Utility
						.expressionEvaluate(new Utility().generateFormulaPay(
								creditSalObj, String
										.valueOf(PF_ConfigObj[0][1]), context,
								session));
				pfEmoluments = Double.parseDouble(formatter
						.format(pfEmoluments));
				// pfEmolumentsSal = Utility.expressionEvaluate(new
				// Utility().generateFormulaPay(creditSalObj,String.valueOf(PF_ConfigObj[0][1]),
				// context,session));
				// pfEmolumentsSal=Double.parseDouble(formatter.format(pfEmolumentsSal));

				boolean minPFEmolumentCheck = getEmpTypeMinAmtChkCondition(
						typeId, 4, path);
				if (minPFEmolumentCheck) {
					if (!String.valueOf(PF_ConfigObj[0][5]).trim().equals("0")) {

						if (String.valueOf(PF_ConfigObj[0][5]).trim().equals(
								"1")
								&& pfEmoluments == Double.parseDouble(String
										.valueOf(PF_ConfigObj[0][4]))) {
							pf_amt = getpfAmtWithRuleCheck(String
									.valueOf(PF_ConfigObj[0][6]), pfEmoluments,
									String.valueOf(PF_ConfigObj[0][7]), String
											.valueOf(PF_ConfigObj[0][2]));
						}
						if (String.valueOf(PF_ConfigObj[0][5]).trim().equals(
								"2")
								&& pfEmoluments < Double.parseDouble(String
										.valueOf(PF_ConfigObj[0][4]))) {
							pf_amt = getpfAmtWithRuleCheck(String
									.valueOf(PF_ConfigObj[0][6]), pfEmoluments,
									String.valueOf(PF_ConfigObj[0][7]), String
											.valueOf(PF_ConfigObj[0][2]));
						}
						if (String.valueOf(PF_ConfigObj[0][5]).trim().equals(
								"3")
								&& pfEmoluments > Double.parseDouble(String
										.valueOf(PF_ConfigObj[0][4]))) {
							pf_amt = getpfAmtWithRuleCheck(String
									.valueOf(PF_ConfigObj[0][6]), pfEmoluments,
									String.valueOf(PF_ConfigObj[0][7]), String
											.valueOf(PF_ConfigObj[0][2]));
						}
						if (String.valueOf(PF_ConfigObj[0][5]).trim().equals(
								"4")
								&& pfEmoluments <= Double.parseDouble(String
										.valueOf(PF_ConfigObj[0][4]))) {
							pf_amt = getpfAmtWithRuleCheck(String
									.valueOf(PF_ConfigObj[0][6]), pfEmoluments,
									String.valueOf(PF_ConfigObj[0][7]), String
											.valueOf(PF_ConfigObj[0][2]));
						}
						if (String.valueOf(PF_ConfigObj[0][5]).trim().equals(
								"5")
								&& pfEmoluments >= Double.parseDouble(String
										.valueOf(PF_ConfigObj[0][4]))) {
							pf_amt = getpfAmtWithRuleCheck(String
									.valueOf(PF_ConfigObj[0][6]), pfEmoluments,
									String.valueOf(PF_ConfigObj[0][7]), String
											.valueOf(PF_ConfigObj[0][2]));
						}

					} else {
						pf_amt = ((pfEmoluments * Double.parseDouble(String
								.valueOf(PF_ConfigObj[0][2]))) / 100);
					}
				} else {
					// pf_amt = ((pfEmoluments *
					// Double.parseDouble(String.valueOf(PF_ConfigObj[0][2])))/100);
					pf_amt = getpfAmtWithRuleCheck(String
							.valueOf(PF_ConfigObj[0][6]), pfEmoluments, String
							.valueOf(PF_ConfigObj[0][7]), String
							.valueOf(PF_ConfigObj[0][2]));
				}

			} else
				pf_amt = 0;

		} catch (Exception e) {
			logger.error("Exception in getEmpPFAmt:" + e);
			pf_amt = 0;
		}
		return pf_amt;
	}

	/**
	 * Purpose : this method is used to getemployee pf amount
	 * @param creditConfigObj
	 * @param creditSalObj
	 * @param PF_ConfigObj
	 * @param dataString
	 * @return double
	 */
	public double getEmpPFAmt(Object[][] creditConfigObj,
			Object[][] creditSalObj, Object[][] PF_ConfigObj,
			String[] dataString) {
		double pf_amt = 0;
		double pfEmoluments = 0;
		double pfEmolumentsSal = 0;
		if (creditConfigObj == null || creditConfigObj.length == 0) {
			creditConfigObj = creditSalObj;
		}
		try {
			String typeId = dataString[0];
			String branchId = dataString[1];
			String path = dataString[2];
			if (PF_ConfigObj != null && PF_ConfigObj.length > 0) {
				pfEmoluments = Utility
						.expressionEvaluate(new Utility().generateFormulaPay(
								creditConfigObj, String
										.valueOf(PF_ConfigObj[0][1]), context,
								session));
				pfEmoluments = Double.parseDouble(formatter
						.format(pfEmoluments));
				pfEmolumentsSal = Utility
						.expressionEvaluate(new Utility().generateFormulaPay(
								creditSalObj, String
										.valueOf(PF_ConfigObj[0][1]), context,
								session));
				pfEmolumentsSal = Double.parseDouble(formatter
						.format(pfEmolumentsSal));

				boolean minPFEmolumentCheck = getEmpTypeMinAmtChkCondition(
						typeId, 4, path);
				if (minPFEmolumentCheck) {
					if (!String.valueOf(PF_ConfigObj[0][5]).trim().equals("0")) {

						if (String.valueOf(PF_ConfigObj[0][5]).trim().equals(
								"1")
								&& pfEmoluments == Double.parseDouble(String
										.valueOf(PF_ConfigObj[0][4]))) {
							pf_amt = getpfAmtWithRuleCheck(String
									.valueOf(PF_ConfigObj[0][6]),
									pfEmolumentsSal, String
											.valueOf(PF_ConfigObj[0][7]),
									String.valueOf(PF_ConfigObj[0][2]));
						}
						if (String.valueOf(PF_ConfigObj[0][5]).trim().equals(
								"2")
								&& pfEmoluments < Double.parseDouble(String
										.valueOf(PF_ConfigObj[0][4]))) {
							pf_amt = getpfAmtWithRuleCheck(String
									.valueOf(PF_ConfigObj[0][6]),
									pfEmolumentsSal, String
											.valueOf(PF_ConfigObj[0][7]),
									String.valueOf(PF_ConfigObj[0][2]));
						}
						if (String.valueOf(PF_ConfigObj[0][5]).trim().equals(
								"3")
								&& pfEmoluments > Double.parseDouble(String
										.valueOf(PF_ConfigObj[0][4]))) {
							pf_amt = getpfAmtWithRuleCheck(String
									.valueOf(PF_ConfigObj[0][6]),
									pfEmolumentsSal, String
											.valueOf(PF_ConfigObj[0][7]),
									String.valueOf(PF_ConfigObj[0][2]));
						}
						if (String.valueOf(PF_ConfigObj[0][5]).trim().equals(
								"4")
								&& pfEmoluments <= Double.parseDouble(String
										.valueOf(PF_ConfigObj[0][4]))) {
							pf_amt = getpfAmtWithRuleCheck(String
									.valueOf(PF_ConfigObj[0][6]),
									pfEmolumentsSal, String
											.valueOf(PF_ConfigObj[0][7]),
									String.valueOf(PF_ConfigObj[0][2]));
						}
						if (String.valueOf(PF_ConfigObj[0][5]).trim().equals(
								"5")
								&& pfEmoluments >= Double.parseDouble(String
										.valueOf(PF_ConfigObj[0][4]))) {
							pf_amt = getpfAmtWithRuleCheck(String
									.valueOf(PF_ConfigObj[0][6]),
									pfEmolumentsSal, String
											.valueOf(PF_ConfigObj[0][7]),
									String.valueOf(PF_ConfigObj[0][2]));
						}

					} else {
						pf_amt = ((pfEmolumentsSal * Double.parseDouble(String
								.valueOf(PF_ConfigObj[0][2]))) / 100);
					}
				} else {
					// pf_amt = ((pfEmoluments *
					// Double.parseDouble(String.valueOf(PF_ConfigObj[0][2])))/100);
					pf_amt = getpfAmtWithRuleCheck(String
							.valueOf(PF_ConfigObj[0][6]), pfEmolumentsSal,
							String.valueOf(PF_ConfigObj[0][7]), String
									.valueOf(PF_ConfigObj[0][2]));
				}

			} else
				pf_amt = 0;

		} catch (Exception e) {
			logger.error("Exception in getEmpPFAmt:" + e);
			pf_amt = 0;
		}
		return pf_amt;
	}

	/**
	 * Purpose : this method is used to get employee pftrust amount
	 * @param creditObj
	 * @param pfTrust_ConfigObj
	 * @return double
	 */
	public double getEmpPftrustAmt(Object[][] creditObj,
			Object[][] pfTrust_ConfigObj) {
		double pfTrust_amt = 0;
		double pfTrustMaxAmt = 0;
		try {
			if (pfTrust_ConfigObj != null && pfTrust_ConfigObj.length > 0) {
				pfTrust_amt = Utility.expressionEvaluate(new Utility()
						.generateFormulaPay(creditObj, String
								.valueOf(pfTrust_ConfigObj[0][1]), context,
								session));
				pfTrustMaxAmt = Utility.expressionEvaluate(new Utility()
						.generateFormulaPay(creditObj, String
								.valueOf(pfTrust_ConfigObj[0][2]), context,
								session));
				if (pfTrust_amt > pfTrustMaxAmt)
					pfTrust_amt = pfTrustMaxAmt;
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpPftrustAmt:" + e);
			pfTrust_amt = 0;
		}
		return pfTrust_amt;
	}

	/**
	 * Purpose : this method is used to get employee VPF amount
	 * @param creditObj
	 * @param VPF_ConfigObj
	 * @return double
	 */
	public double getEmpVPFAmt(Object[][] creditObj, Object[][] VPF_ConfigObj) {
		double vpf_amt = 0;
		double vpfMaxEmoluments = 0;
		try {
			if (VPF_ConfigObj != null && VPF_ConfigObj.length > 0) {
				vpfMaxEmoluments = Utility
						.expressionEvaluate(new Utility().generateFormulaPay(
								creditObj, String.valueOf(VPF_ConfigObj[0][3]),
								context, session));
				if (String.valueOf(VPF_ConfigObj[0][2]).equals("FR")) {
					vpf_amt = Utility.expressionEvaluate(new Utility()
							.generateFormulaPay(creditObj, String
									.valueOf(VPF_ConfigObj[0][0]), context,
									session));
					if (vpf_amt > vpfMaxEmoluments)
						vpf_amt = vpfMaxEmoluments;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpVPFAmt:" + e);
			vpf_amt = 0;
		}
		return vpf_amt;
	}

	/**
	 * Purpose : this method is used to get employee esi amount
	 * @param ESI_ConfigObj
	 * @param dataString
	 * @param grossESICreditTotal
	 * @param monthESICreditTotal
	 * @param comLedgerCode
	 * @return double
	 */
	public double getEmpESIAmt(Object[][] ESI_ConfigObj, String[] dataString,
			double grossESICreditTotal, double monthESICreditTotal,
			String comLedgerCode) {
		double esi_amt = 0;
		try {
			
			logger.info("to start calculate ESIC");
			
			String typeId = dataString[0];
			String branchId = dataString[1];
			String path = dataString[2];
			String month = dataString[3];
			String year = dataString[4];
			String emp_id = dataString[5];
			/*
			 * for (int i = 0; i < ESI_ConfigObj.length; i++) { for (int j = 0;
			 * j < ESI_ConfigObj[0].length; j++) {
			 * logger.info("ESI_ConfigObj["+i+"]["+j+"]=="+ESI_ConfigObj[i][j]); } }
			 */
			String previousEsic = "";
			if (ESI_ConfigObj != null && ESI_ConfigObj.length > 0) {
				boolean empTypeBranchCheck = getEmpTypeBranchApplicabilityChk(
						typeId, branchId, 1, path);
				if (empTypeBranchCheck) {
					
					logger.info("ESIC branch check yes");
					
					if (grossESICreditTotal <= Integer.parseInt(String
							.valueOf(ESI_ConfigObj[0][4]))) {
						esi_amt = (monthESICreditTotal
								* Double.parseDouble(String
										.valueOf(ESI_ConfigObj[0][2])) / 100);
					} else if (month
							.equals(String.valueOf(ESI_ConfigObj[0][5]))) {										
						// If ESI start month and end month is equal then
						// straight away esi will be calculated.
						if (grossESICreditTotal <= Integer.parseInt(String
								.valueOf(ESI_ConfigObj[0][4]))) {
							esi_amt = (monthESICreditTotal
									* Double.parseDouble(String
											.valueOf(ESI_ConfigObj[0][2])) / 100);
						}
					} else if (grossESICreditTotal >= Integer.parseInt(String
							.valueOf(ESI_ConfigObj[0][4]))) {
						
						logger.info("credit greater than 15000");
						
						/**
						 * if not, system will check whether esi deducted on the
						 * specified month or not this method returns status of
						 * esi whether to deduct or not. It it returns "NP"
						 * means salary has not been processed for specified esi
						 * cutoff months, then condition will remain same depend
						 * on gross esi will be deducted. If it returns "CE"
						 * means esi deducted on cut off months hence esi will
						 * be deducted irrespective of gorss
						 */
						if (comLedgerCode.equals("spilt")) {
							
							logger.info("for the year split");
							
							previousEsic = getPreESICSpilt(month, year, emp_id,
									ESI_ConfigObj, comLedgerCode);
							
							logger.info("for the year split: pre esic :"+previousEsic);
							
						} else {
							
							logger.info("no year split");
							
							previousEsic = getPreESIC(month, year, emp_id,
									ESI_ConfigObj, comLedgerCode);
							
							logger.info("no year split : pre esic :"+previousEsic);
						}
						if (previousEsic.equals("true")) {
							
							logger.info("previous ESIC true");
							
							esi_amt = (monthESICreditTotal
									* Double.parseDouble(String
											.valueOf(ESI_ConfigObj[0][2])) / 100);
						}
					}
				} else {
					esi_amt = 0;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpESIAmt:" + e);
			esi_amt = 0;
		}
		return esi_amt;
	}

	/**
	 * Purpose : this method is used to get employee ptax amount
	 * @param PTAX_ConfigObj
	 * @param dataString
	 * @return double
	 */
	public double getEmpPTAXAmt(Object[][] PTAX_ConfigObj, String[] dataString) {
		double ptax_amt = 0;
		try {
			String typeId = dataString[0];
			String branchId = dataString[1];
			String path = dataString[2];
			String month = dataString[3];
			if (PTAX_ConfigObj != null && PTAX_ConfigObj.length > 0) {
				boolean empTypeBranchCheck = getEmpTypeBranchApplicabilityChk(
						typeId, branchId, 2, path);
				if (empTypeBranchCheck) {
					ptax_amt = getPtaxWithMonthCheck(month, PTAX_ConfigObj);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpPTAXAmt:" + e);
			ptax_amt = 0;
		}
		return ptax_amt;
	}

	/**
	 *  Purpose : this method is used to get lwfamount
	 * @param creditObj
	 * @param lwfCodeList
	 * @param lwfMap
	 * @param dataString
	 * @param lwfCreditApplicableMap
	 * @param lwfApplicableEmpGradeMap
	 * @param lwfApplicableEmpDesgMap
	 * @return double
	 */
	public double getEmpLwfAmt(Object[][] creditObj,
			ArrayList<Object[]> lwfCodeList,
			HashMap<String, Object[][]> lwfMap, String[] dataString,
			HashMap<String, Object[][]> lwfCreditApplicableMap,
			HashMap<String, Object[][]> lwfApplicableEmpGradeMap,
			HashMap<String, Object[][]> lwfApplicableEmpDesgMap) {
		double lwf_amt = 0;
		try {
			String location = dataString[0];
			String emp_id = dataString[1];
			String lwfCreditHead = dataString[2];
			String lwfCode = getEmpLWFCode(location, lwfCodeList);
			/**
			 * CHECK EMPLOYEE APPLICABLE
			 */
			boolean result = true;
			// IF NOT APPLICABLE GRADE
			if (String.valueOf(dataString[5]).equals("0")) {
				result = false;
			} else {
				result = true;
			}
			if (lwfApplicableEmpGradeMap != null
					&& lwfApplicableEmpGradeMap.size() > 0) {
				// key=divcode+lwfcode
				String key = dataString[3] + "#" + lwfCode + "#"
						+ dataString[5];
				Object[][] obj = lwfApplicableEmpGradeMap.get(key);
				if (obj != null && obj.length > 0) {
					for (int i = 0; i < obj.length; i++) {
						if (dataString[5].equals(String.valueOf(obj[i][2]))) {
							result = false;
						}
					}
				}
			}

			if (lwfApplicableEmpDesgMap != null
					&& lwfApplicableEmpDesgMap.size() > 0) {
				// key=divcode+lwfcode
				String key = dataString[3] + "#" + lwfCode + "#"
						+ dataString[4];
				Object[][] obj = lwfApplicableEmpDesgMap.get(key);
				if (obj != null && obj.length > 0) {
					for (int i = 0; i < obj.length; i++) {
						if (dataString[4].equals(String.valueOf(obj[i][1]))) {
							result = false;
						}
					}
				}
			}
			Object[][] slabObj = lwfMap.get(lwfCode);
			if (slabObj != null && slabObj.length > 0 && result) {
				lwf_amt = getLWFAmount(emp_id, slabObj, lwfCreditHead,
						creditObj, lwfCreditApplicableMap);
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpLwfAmt:" + e);
			lwf_amt = 0;
		}
		return lwf_amt;
	}

	/**
	 * Purpose : this method is used to find out the professional tax amount
	 * 
	 * @param location,
	 *            month, grossSal, taxData
	 * @return professional tax amount
	 */
	public double getPtaxWithMonthCheck(String month, Object[][] ptaxData) {
		try {
			if (ptaxData != null && ptaxData.length > 0) {
				/**
				 * checking whether the processing month is variable or not. If
				 * month is equal to variable month of professional tax data
				 * then the variable amount will be returned, else fixed amount
				 * will be returned
				 */
				if (month.equals(String.valueOf(ptaxData[0][4]).trim())) {
					return Double.parseDouble(checkNull_Zero(String
							.valueOf(ptaxData[0][6])));
				} else {
					return Double.parseDouble(checkNull_Zero(String
							.valueOf(ptaxData[0][5])));
				}
			} else
				return 0;
		} catch (Exception e) {
			logger.error("Exception in getPtaxWithMonthCheck:" + e);
			return 0;
		}
	} // end of getEmpPtax()

	/**
	 * Purpose : this method is used to get previous esi split 
	 * @param month
	 * @param year
	 * @param empId
	 * @param esi_data
	 * @param comLedgerCode
	 * @return string
	 */
	public String getPreESICSpilt(String month, String year, String empId,
			Object[][] esi_data, String comLedgerCode) {
		String result = "false";
		String monthCutoff = "";
		String yearCutoff = "";
		String ledgerMonth = "";
		try {
			String ledgerCodeNext = "", ledgerCodePre = "";
			monthCutoff = String.valueOf(esi_data[0][6]);
			for (int i = Integer.parseInt(monthCutoff); i <= 12; i++) {
				if (i == 12) {
					ledgerMonth += String.valueOf(i);
				} else {
					ledgerMonth += String.valueOf(i) + ",";
				}
			}
			yearCutoff = String.valueOf(Integer.parseInt(year) - 1);
			ledgerCodePre = getPrevLedger(ledgerMonth, yearCutoff);
			
			logger.info("the previous ledgers :"+ledgerCodePre);

			if (ledgerCodePre.equals("") || ledgerCodePre.equals("null"))
				return "false";

			String queryPrevYear = "select nvl(SAL_AMOUNT,0) from HRMS_SAL_DEBITS_"
					+ yearCutoff
					+ " where emp_id="
					+ empId
					+ " "
					+ " and sal_debit_code="
					+ String.valueOf(esi_data[0][0])
					+ " and sal_ledger_code in(" + ledgerCodePre + ") ";

			Object preEsiPrev[][] = getSqlModel()
					.getSingleResult(queryPrevYear);

			if (preEsiPrev != null) {
				if (preEsiPrev.length > 0) {
					for (int i = 0; i < preEsiPrev.length; i++) {
						if (!String.valueOf(preEsiPrev[i][0]).equals("0")) {
							return "true";
						}
					}
				}
			}
			ledgerMonth = "";
			for (int i = 1; i <= Integer.parseInt(month); i++) {
				if (i == Integer.parseInt(month)) {
					ledgerMonth += String.valueOf(i);
				} else {
					ledgerMonth += String.valueOf(i) + ",";
				}
			}
			ledgerCodeNext = getPrevLedger(ledgerMonth, year);
			
			logger.info("the previous ledgers :"+ledgerCodeNext);
			
			if (ledgerCodeNext.equals("") || ledgerCodeNext.equals("null"))
				return "false";

			String queryCuurYear = "select nvl(SAL_AMOUNT,0) from HRMS_SAL_DEBITS_"
					+ year
					+ " where emp_id="
					+ empId
					+ " "
					+ " and sal_debit_code="
					+ String.valueOf(esi_data[0][0])
					+ " and sal_ledger_code in(" + ledgerCodeNext + ") ";

			Object preEsiCuur[][] = getSqlModel()
					.getSingleResult(queryCuurYear);

			if (preEsiCuur != null) {
				if (preEsiCuur.length > 0) {
					for (int i = 0; i < preEsiCuur.length; i++) {
						if (!String.valueOf(preEsiCuur[i][0]).equals("0")) {
							return "true";
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getPreESICSpilt ---------" + e);
		}
		return result;
	}

	/**
	 * Purpose : this method is used to get previous ledger code
	 * @param ledgerMonth
	 * @param year
	 * @return string
	 */
	public String getPrevLedger(String ledgerMonth, String year) {

		String ledgerQuery = "Select ledger_code from hrms_salary_ledger where ledger_month IN ("
				+ ledgerMonth
				+ ") and ledger_year="
				+ year
				+ " and LEDGER_STATUS IN ('SAL_FINAL')";
		String ledgerCode = "";
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
		return ledgerCode;
	}

	/**
	 * Purpose : this method is used to get esi
	 * @param month
	 * @param year
	 * @param empId
	 * @param esi_data
	 * @param comLedgerCode
	 * @return string
	 */
	public String getPreESIC(String month, String year, String empId,
			Object[][] esi_data, String comLedgerCode) {
		String result = "false";
		try {
			if (comLedgerCode.equals("") || comLedgerCode.equals("null"))
				return "false";

			String query = "select nvl(SAL_AMOUNT,0) from HRMS_SAL_DEBITS_"
					+ year + " where emp_id=" + empId + " "
					+ " and sal_debit_code=" + String.valueOf(esi_data[0][0])
					+ " and sal_ledger_code in(" + comLedgerCode + ") ";

			Object preEsi[][] = getSqlModel().getSingleResult(query);
			if (preEsi != null) {
				if (preEsi.length > 0) {
					for (int i = 0; i < preEsi.length; i++) {
						if (!String.valueOf(preEsi[i][0]).equals("0")) {
							return "true";
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getPreESIC ---------" + e);
		}
		return result;
	}

	
	/**check the employee location with lwfstate return respective lwfcode if
	 * matching condition
	 * @param location
	 * @param lwfCodeList
	 * @return string
	 */
	public String getEmpLWFCode(String location, ArrayList<Object[]> lwfCodeList) {
		String lwfCode = null;
		try {
			if (lwfCodeList != null && lwfCodeList.size() > 0) {
				for (int i = 0; i < lwfCodeList.size(); i++) {
					Object[] temp = lwfCodeList.get(i);
					if (location.equals(String.valueOf(temp[1]).trim())) {
						lwfCode = String.valueOf(temp[0]);
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception getting getEmpLWFCode  ---------" + e);
		}
		return lwfCode;
	}
 
	/**
	 * check the employee location with lwfstate return respective lwfcode ifmatching condition
	 * @param location
	 * @param lwfCodeList
	 * @return string
	 */
	public String getEmpLWFDebitCode(String location,
			ArrayList<Object[]> lwfCodeList) {
		String lwfDebitCode = null;
		try {
			if (lwfCodeList != null && lwfCodeList.size() > 0) {
				for (int i = 0; i < lwfCodeList.size(); i++) {
					Object[] temp = lwfCodeList.get(i);
					if (location.equals(String.valueOf(temp[1]).trim())) {
						lwfDebitCode = String.valueOf(temp[3]);
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception getting getEmpLWFCode  ---------" + e);
		}
		return lwfDebitCode;
	}

	/**
	 * Purpose : this method is used to get max LWFCodes as per the states by checking the effective date
	 * 
	 * @param year
	 * @return boolean
	 */
	public ArrayList<Object[]> getLWFCodes(String month, String year) {
		Object[][] result = null;
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			if (Integer.parseInt(month) <= 9)
				month = "0" + month;
			String query = " SELECT LWF_CODE,LWF_STATE_CODE,LWF_MONTH_DEDUCTIONS,NVL(LWF_DEBIT_CODE,0) FROM HRMS_LWF_SLAB_HDR T1 "
					+ " WHERE T1.LWF_EFFECTIVE_FROM = (SELECT MAX(LWF_EFFECTIVE_FROM)FROM HRMS_LWF_SLAB_HDR T2 "
					+ " WHERE TO_CHAR(T2.LWF_EFFECTIVE_FROM,'yyyy-mm') <= '"
					+ year
					+ "-"
					+ month
					+ "' AND T1.LWF_STATE_CODE = T2.LWF_STATE_CODE ) "
					+ " ORDER BY LWF_STATE_CODE ";
			result = getSqlModel().getSingleResult(query);
			for (int i = 0; i < result.length; i++) {
				if (String.valueOf(result[i][2]).contains(month)) {
					list.add(result[i]);
				}
			}
		} catch (Exception e) {
			logger
					.error("Exception getting getLWFCodes as per the state and effective dates ---------"
							+ e);
		}
		return list;
	}

	/**
	 * Purpose :  this method is used to get lwf slab
	 * @param lwfCodeList
	 * @return hashmap
	 */
	public HashMap<String, Object[][]> getLWFSlabs(
			ArrayList<Object[]> lwfCodeList) {
		HashMap<String, Object[][]> map = new HashMap<String, Object[][]>();
		try {
			if (lwfCodeList != null && lwfCodeList.size() > 0) {
				for (int i = 0; i < lwfCodeList.size(); i++) {
					Object[] tempObj = lwfCodeList.get(i);

					String query = " SELECT LWF_SLAB_FROM,LWF_SLAB_TO,LWF_EMP_CONTRIB,LWF_EMPLR_CONTRIB FROM HRMS_LWF_SLAB_DTL "
							+ " WHERE LWF_CODE = "
							+ String.valueOf(tempObj[0])
							+ " ORDER BY LWF_DTL_CODE ";
					Object[][] result = getSqlModel().getSingleResult(query);
					if (result != null && result.length > 0) {
						map.put(String.valueOf(tempObj[0]), result);
					}
				}
			}
		} catch (Exception e) {
			logger
					.error("Exception getting getLWSlabs for the lwfcodes with this month as deduction month ---------"
							+ e);
		}
		return map;
	}

	/**
	 * Purpose :  this method is used to get lwf amount
	 * @param emp_id
	 * @param lwfSlab
	 * @param lwfCreditHead
	 * @param creditObject
	 * @param lwfCreditApplicableMap
	 * @return double
	 */
	public double getLWFAmount(String emp_id, Object[][] lwfSlab,
			String lwfCreditHead, Object[][] creditObject,
			HashMap<String, Object[][]> lwfCreditApplicableMap) {
		double lwfAmount = 0;
		try {
			double basicAmt = 0.0;
			for (int i = 0; i < creditObject.length; i++) {
				if (lwfCreditApplicableMap != null
						&& lwfCreditApplicableMap.size() > 0) {
					String key = String.valueOf(creditObject[i][0]) + "#"
							+ String.valueOf(creditObject[i][0]);
					Object[][] obj = lwfCreditApplicableMap.get(key);
					if (obj != null && obj.length > 0) {
						basicAmt += Double.parseDouble(String
								.valueOf(creditObject[i][1]));
					}
				}
			}
			for (int i = 0; i < lwfSlab.length; i++) {
				if (Math.floor(basicAmt) >= Double.parseDouble(String
						.valueOf(lwfSlab[i][0]))
						&& Math.floor(basicAmt) <= Double.parseDouble(String
								.valueOf(lwfSlab[i][1]))) {
					lwfAmount = Double.parseDouble(String.valueOf(String
							.valueOf(lwfSlab[i][2])));
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Exception getting getEmpLWFAmount  ---------" + e);
			lwfAmount = 0;
		}
		return lwfAmount;
	}

	/**
	 * Purpose :  this method is used to convertdays into day with minuts
	 * @param days
	 * @return object
	 */
	public Object[] getDaysWithHrsAndMinutes(String days) {
		Object[] data = new Object[2];
		try {
			if (days.contains("d")) {
				Object[] daysData = days.split(":");
				data[0] = String.valueOf(daysData[0]).substring(0,
						String.valueOf(daysData[0]).length() - 1);
				data[1] = String.valueOf(daysData[1]).substring(0,
						String.valueOf(daysData[1]).length() - 1)
						+ ":"
						+ String.valueOf(daysData[2]).substring(0,
								String.valueOf(daysData[2]).length() - 1);
			} else {
				data[0] = days;
				data[1] = "00:00";
			}
		} catch (Exception e) {
			logger
					.error("Exception in getDaysWithHrsAndMinutes to split the days object to hrs and minutes ---------"
							+ e);
			data[0] = "00";
			data[1] = "00:00";
		}
		return data;
	}

	/**
	 * Purpose :  this method is used to convert shift hrs into minutes
	 * @param shiftTime
	 * @return string
	 */
	public String convertToMinutes(String shiftTime) {
		double minutes = 0;
		try {
			String[] data = shiftTime.split(":");
			minutes = Double.parseDouble(String.valueOf(data[1]))
					+ (Double.parseDouble(String.valueOf(data[0])) * 60);

		} catch (Exception e) {
			logger
					.error("Exception in convertToMinutes getting minutes for hrs ---------"
							+ e);
			minutes = 0;
		}
		return String.valueOf(minutes);
	}

	/**
	 * Purpose :  this method is used to get format salary days
	 * @param days
	 * @return string
	 */
	public String formatSalDays(String days) {
		String result = "";
		try {
			if (days.contains("d")) {
				Object[] daysData = days.split(":");
				int hrs = Integer
						.parseInt(String.valueOf(daysData[1]).substring(0,
								String.valueOf(daysData[1]).length() - 1));
				int mm = Integer
						.parseInt(String.valueOf(daysData[2]).substring(0,
								String.valueOf(daysData[2]).length() - 1));
				if (hrs == 0 && mm == 0)
					result = String.valueOf(daysData[0]).substring(0,
							String.valueOf(daysData[0]).length() - 1);
				else
					result = days;
			} else {
				result = days;
			}
		} catch (Exception e) {
			logger
					.error("Exception getting getViewDays to split the days object to hrs ---------"
							+ e);
		}
		return result;
	}

	/**
	 * Purpose :  this method is used to process salary process
	 * @param dataString
	 * @param listOfFilters
	 * @param empObj
	 * @param recalculateFlag
	 * @return boolean
	 */
	public boolean processSalary(String[] dataString, String[] listOfFilters,
			Object empObj[][], String recalculateFlag) {
		boolean result = false;
		try {
			// get the employee from attendance for specified ledgerCode
			// Object empObj[][] =
			// getAttendanceEmployeeToProcess(String.valueOf(dataString[1]),String.valueOf(dataString[2]));
			Object[][] creditInsertObj = null;
			Object[][] debitInsertObj = null;
			String empIdString="0";
			if (empObj != null && empObj.length > 0) {
				
				for (int i = 0; i < empObj.length; i++) {
					empIdString+=","+String.valueOf(empObj[i][0]);
				}
			}
			Object perfConfigData[][] = getPerformanceConfig();
			boolean ratingFlag = false;
			HashMap<String, String> empRatingMap = null;
			HashMap<String, String> empCTCMap = null;
			if (perfConfigData != null && perfConfigData.length > 0) {
				empRatingMap = getEmpRatingData(empIdString, String
						.valueOf(dataString[0]), String.valueOf(dataString[1]));
				empCTCMap = getEmpCTCData(empIdString);
				ratingFlag = true;
			}
			
			if (empObj != null && empObj.length > 0) {				
				String[] generalData = new String[15];
				HashMap<String, Object[][]> empCreditConfigMap = new HashMap<String, Object[][]>();
				HashMap<String, Object[][]> empDebitConfigMap = new HashMap<String, Object[][]>();
				HashMap<String, Object[][]> empMonthCreditMap = new HashMap<String, Object[][]>();
				HashMap<String, Object[][]> empMonthDebitMap = new HashMap<String, Object[][]>();
				HashMap<String, Object[][]> empTotalMap = new HashMap<String, Object[][]>();

				/**
				 * LOAD SALARY ZONE,SALARYZONEBRANCH,ESI PARAMETER, PF
				 * PARAMETER, PTAX PARAMETER
				 */
				loadPayrollSetting();

				/*
				 * LOAD EMPLOYEE MISC UPLOAD
				 */
				HashMap<String, Object[][]> miscSalaryMap = getSalMiscUpload(
						String.valueOf(dataString[0]), String
								.valueOf(dataString[1]));

				// get the creditCodes from creditHead master with
				// periodicity=monthly and payflag=yes
				Object[][] creditHeadObj = getCreditCodes();
				if (creditHeadObj != null && creditHeadObj.length > 0) {
					// get the employee credits from employee credit
					// configuration
					empCreditConfigMap = getEmpCreditMap(empIdString);
					// if(empCreditConfigMap != null &&
					// empCreditConfigMap.size() > 0) {

					// not used now...
					// to make same no of credits for all employee
					// empCreditConfigMap =
					// makeEmpObjSameSize(empCreditConfigMap, creditHeadObj);

					for (int i = 0; i < empObj.length; i++) {

						Object[][] tempCreditObj = makeEmpObjSameSize(
								empCreditConfigMap.get(String
										.valueOf(empObj[i][0])), creditHeadObj,
								String.valueOf(empObj[i][0]));
						// not used now...
						// Object [][] tempCreditObj =
						// empCreditConfigMap.get(String.valueOf(empObj[i][0]));
						String empMonthRating = "";
						String empCTC = "";
						String[] empRatingData = new String[2];

						try {
							if (ratingFlag) {
								empMonthRating = String.valueOf(empRatingMap
										.get(String.valueOf(empObj[i][0])));
								empCTC = String.valueOf(empCTCMap.get(String
										.valueOf(empObj[i][0])));
								empRatingData[0] = empMonthRating; // emp
																	// rating
								empRatingData[1] = String.valueOf(Double
										.parseDouble(empCTC) / 12); // monthly
																	// CTC
							}
						} catch (Exception e) {
							empMonthRating = "";
						}
						Object[][] tempSalCredit = getMonthlyEmpCredit(
								tempCreditObj, String.valueOf(empObj[i][7]), // attendance days
								String.valueOf(empObj[i][13]),// shift hours
								String.valueOf(dataString[0]),// month
								String.valueOf(dataString[1]),// year
								String.valueOf(dataString[3]),// joiningDaysFlag
								String.valueOf(dataString[4]),// creditRound
								empRatingData, perfConfigData); // monthlyrating
						/**
						 * ADD OR APPEND MISC SALARY UPLOAD
						 */

						tempSalCredit = addAppendMiscSalary(tempSalCredit,
								miscSalaryMap, String.valueOf(empObj[i][0]));
						empMonthCreditMap.put(String.valueOf(empObj[i][0]),
								tempSalCredit);
						tempSalCredit = null;
						tempCreditObj = null;
					}
					creditInsertObj = createInsertObj(empMonthCreditMap,
							empObj, creditHeadObj);
					// }//
				}
				// get the debitCodes from creditHead master with
				// periodicity=monthly and payflag=yes

				Object[][] esi_configData = getESIData(String
						.valueOf(dataString[5]), String.valueOf(dataString[0]),
						String.valueOf(dataString[1]));
				NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();
				nonModel.initiate(context, session);
				Object[][] debitHeadObj = getDebitCodes();
				if (debitHeadObj != null && debitHeadObj.length > 0) {
					generalData[0] = String.valueOf(dataString[5]);// path
					generalData[1] = String.valueOf(dataString[0]);// month
					generalData[2] = String.valueOf(dataString[1]);// year
					generalData[3] = "0";
					if (esi_configData != null && esi_configData.length > 0) {
						generalData[3] = nonModel.prevLedger(dataString[0],
								dataString[1], esi_configData);// comLedgerCode;//comLedgerCode
					}
					generalData[4] = String.valueOf(dataString[9]);// PTAXhandicapFlag
					generalData[5] = String.valueOf(dataString[7]);// lwfApplicability
					generalData[6] = String.valueOf(dataString[10]);// lwfCreditHead
					generalData[7] = String.valueOf(dataString[11]);// lwfDebitHead
					generalData[8] = String.valueOf(dataString[12]);// incomeTaxFlag
					generalData[9] = String.valueOf(dataString[13]);// creditTotalRound
					generalData[10] = String.valueOf(dataString[14]);// debitTotalRound
					generalData[11] = String.valueOf(dataString[15]);// netPayRound
					generalData[12] = "N"; // String.valueOf(dataString[16]);//recoveryFlag
					generalData[13] = "0"; // String.valueOf(dataString[17]);//recovery
					// generalData [14] = String.valueOf(dataString[18]);//
					// Divisionwise ESIC flag)
					// get the employee debits from employee debitt
					// configuration
					empDebitConfigMap = getEmpDebitMap(empIdString);
					// if(empDebitConfigMap != null && empDebitConfigMap.size()
					// > 0) {

					// not used now...
					// to make same no of debits for all employee
					// empDebitConfigMap = makeEmpObjSameSize(empDebitConfigMap,
					// debitHeadObj);

					Object[][] vpf_configData = null;
					Object[][] incomeTax_configData = null;
					HashMap<String, Object[][]> lwfMap = null;
					HashMap<String, Object[][]> loanMap = getEmpLoanMap(String
							.valueOf(dataString[0]), String
							.valueOf(dataString[1]));
					HashMap<String, Object[][]> licMap = getEmpLicMap();
					ArrayList<Object[]> lwfCodeList = null;

					// parameter -- path,month,year
					Object[][] pf_configData = null;
					if (String.valueOf(dataString[19]).equals("Y")) {
						pf_configData = getPFData(
								String.valueOf(dataString[5]), String
										.valueOf(dataString[0]), String
										.valueOf(dataString[1]));
					}

					Object[][] pfTrust_configData = getPFTrustData();
					// parameter -- path,month,year

					// vpfFlag check
					if (String.valueOf(dataString[6]).equals("Y"))
						vpf_configData = getVPFConfig();
					// lwfFlag check

					// parameter -- month,year
					lwfCodeList = getLWFCodes(String.valueOf(dataString[0]),
							String.valueOf(dataString[1]));
					lwfMap = getLWFSlabs(lwfCodeList);
					HashMap<String, Object[][]> lwfCreditApplicableMap = getLWFCreditApplicable();
					HashMap<String, Object[][]> lwfApplicableEmpGradeMap = getLWFNotApplicableEmpGrade();
					HashMap<String, Object[][]> lwfApplicableEmpDesgMap = getLWFNotApplicableEmpDesg();

					if (String.valueOf(dataString[12]).equals("Y")) {
						// parameter -- month,year
						incomeTax_configData = getIncomeTaxConfigObject(String
								.valueOf(dataString[0]), String
								.valueOf(dataString[1]));
					}
					for (int i = 0; i < empObj.length; i++) {

						Object[][] tempMonthCreditObj = empMonthCreditMap
								.get(String.valueOf(empObj[i][0]));
						Object[][] tempGrossCreditObj = empCreditConfigMap
								.get(String.valueOf(empObj[i][0]));
						Object[][] tempDebitObj = makeEmpObjSameSizeDebit(
								empDebitConfigMap.get(String
										.valueOf(empObj[i][0])), debitHeadObj,
								String.valueOf(empObj[i][0]));
						Object[][] empLoanObj = loanMap.get(String
								.valueOf(empObj[i][0]));
						Object[][] empLicObj = licMap.get(String
								.valueOf(empObj[i][0]));
						// not used now...
						// Object [][] tempDebitObj =
						// empDebitConfigMap.get(String.valueOf(empObj[i][0]));
						String[] empData = new String[14];
						empData[0] = String.valueOf(empObj[i][9]);// epfApplicability
						empData[1] = String.valueOf(empObj[i][6]);// empTypeId
						empData[2] = String.valueOf(empObj[i][3]);// branchId
						empData[3] = String.valueOf(empObj[i][12]);// pftrustApplicability
						empData[4] = String.valueOf(empObj[i][10]);// vpfApplicability
						empData[5] = String.valueOf(empObj[i][0]);// empid
						empData[6] = String.valueOf(empObj[i][8]);// PTAX
						empData[7] = String.valueOf(empObj[i][5]);// location
						empData[8] = String.valueOf(empObj[i][14]);// empLwfApplicability
						empData[9] = String.valueOf(empObj[i][15]);// recovery days

						empData[10] = String.valueOf(empObj[i][7]);// salDays
						empData[11] = String.valueOf(empObj[i][17]);// DIV
						empData[12] = String.valueOf(empObj[i][18]);// DESG
						empData[13] = String.valueOf(empObj[i][19]);// GRADE

						ArrayList dataList = getMonthlyEmpDebit(tempDebitObj,
								tempMonthCreditObj, empData,
								tempGrossCreditObj, pf_configData,
								pfTrust_configData, vpf_configData,
								esi_configData, lwfCodeList, lwfMap,
								incomeTax_configData, empLoanObj, empLicObj,
								generalData, miscSalaryMap, String
										.valueOf(empObj[i][0]),
								lwfCreditApplicableMap,
								lwfApplicableEmpGradeMap,
								lwfApplicableEmpDesgMap);

						Object[][] tempSalDebit = (Object[][]) dataList.get(0);
						Object[][] totalCredit = (Object[][]) dataList.get(1);
						Object[][] totalDebit = (Object[][]) dataList.get(2);
						Object[][] netPay = (Object[][]) dataList.get(3);

						empMonthDebitMap.put(String.valueOf(empObj[i][0]),
								tempSalDebit);
						Object[][] totalObj = new Object[1][3];
						totalObj[0][0] = String.valueOf(totalCredit[0][0]);
						totalObj[0][1] = String.valueOf(totalDebit[0][0]);
						totalObj[0][2] = String.valueOf(netPay[0][0]);

						empTotalMap.put(String.valueOf(empObj[i][0]), totalObj);

						tempMonthCreditObj = null;
						tempGrossCreditObj = null;
						tempDebitObj = null;
						empLoanObj = null;
						empLicObj = null;
						tempSalDebit = null;
						totalCredit = null;
						totalDebit = null;
						totalObj = null;
					}
					debitInsertObj = createInsertObj(empMonthDebitMap, empObj,
							debitHeadObj);
					// }
				}
				if (recalculateFlag.equals("")) {
					result = saveSalary(creditInsertObj, debitInsertObj,
							empObj, empTotalMap, dataString, empIdString);
				} else {
					/*
					 * FOR RECALCULATE
					 */
					result = true;

					if (empObj != null && empObj.length > 0) {
						for (int i = 0; i < empObj.length; i++) {
							Object[] data = getDaysWithHrsAndMinutes(String
									.valueOf(empObj[i][7]));
							recalSalMap.put(String.valueOf(empObj[i][0]),
									String.valueOf(data[0]));
						}
					}

					if (creditInsertObj != null && creditInsertObj.length > 0) {
						for (int j = 0; j < creditInsertObj.length; j++) {
							recalCreditMap.put(String
									.valueOf(creditInsertObj[j][0])
									+ "#"
									+ String.valueOf(creditInsertObj[j][1]),
									String.valueOf(creditInsertObj[j][2]));
						}
					}
					if (debitInsertObj != null && debitInsertObj.length > 0) {
						for (int j = 0; j < debitInsertObj.length; j++) {
							recalDebitMap.put(String
									.valueOf(debitInsertObj[j][0])
									+ "#"
									+ String.valueOf(debitInsertObj[j][1]),
									String.valueOf(debitInsertObj[j][2]));
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Purpose :  this method is used to get list of LWF credit applicable
	 * @return hashmap
	 */
	public HashMap<String, Object[][]> getLWFCreditApplicable() {
		String query = "  SELECT CREDIT_CODE,CREDIT_APPLICABLE_LWF,CREDIT_CODE||'#'||CREDIT_CODE FROM HRMS_CREDIT_HEAD WHERE CREDIT_APPLICABLE_LWF='Y'";
		HashMap<String, Object[][]> map = getSqlModel().getSingleResultMap(
				query, 2, 2);
		return map;
	}

	/**
	 * Purpose :  this method is used to get LWF not applicable employee list
	 * @return HashMap
	 */
	public HashMap<String, Object[][]> getLWFNotApplicableEmpGrade() {
		String query = "  SELECT LWF_DIV,LWF_DESG,LWF_GRADE,LWF_DIV||'#'||LWF_CODE||'#'||LWF_GRADE FROM HRMS_LWF_EMP_APPLICABLE   WHERE LWF_APPLICABLE='N' AND ( LWF_DESG IS NOT NULL OR LWF_GRADE IS NOT NULL ) 	";
		HashMap<String, Object[][]> map = getSqlModel().getSingleResultMap(
				query, 3, 2);
		return map;
	}

	/**
	 * Purpose :  this method is used to get LWF applicableemployee
	 * @return Hashmap
	 */
	public HashMap<String, Object[][]> getLWFNotApplicableEmpDesg() {
		String query = "  SELECT LWF_DIV,LWF_DESG,LWF_GRADE,LWF_DIV||'#'||LWF_CODE||'#'||LWF_DESG FROM HRMS_LWF_EMP_APPLICABLE   WHERE LWF_APPLICABLE='N' AND ( LWF_DESG IS NOT NULL OR LWF_GRADE IS NOT NULL ) 	";
		HashMap<String, Object[][]> map = getSqlModel().getSingleResultMap(
				query, 3, 2);
		return map;
	}

	/**
	 * Purpose :  this method is used to add and append misc salary
	 * @param tempSalCredit
	 * @param miscSalaryMap
	 * @param empId
	 * @return object
	 */
	private Object[][] addAppendMiscSalary(Object[][] tempSalCredit,
			HashMap<String, Object[][]> miscSalaryMap, String empId) {
		if (tempSalCredit != null && tempSalCredit.length > 0)
			for (int i = 0; i < tempSalCredit.length; i++) {
				String key = empId + "#" + tempSalCredit[i][0] + "#C";
				if (miscSalaryMap != null && miscSalaryMap.size() > 0) {
					Object[][] miscObj = miscSalaryMap.get(key);
					if (miscObj != null && miscObj.length > 0) {
						if (String.valueOf(miscObj[0][2]).equals("Y")) {
							tempSalCredit[i][1] = miscObj[0][1];// amt
						} else {
							tempSalCredit[i][1] = Double.parseDouble(String
									.valueOf(tempSalCredit[i][1]))
									+ Double.parseDouble(String
											.valueOf(miscObj[0][1]));// amt
						}
					}
				}
			}
		return tempSalCredit;
	}

	/**
	 * Purpose :  this method is used to save employee salary
	 * @param creditObject
	 * @param debitObject
	 * @param empObj
	 * @param empTotalMap
	 * @param dataString
	 * @param listOfFilters
	 * @return boolean
	 */

	public boolean saveSalary(Object[][] creditObject, Object[][] debitObject,
			Object[][] empObj, HashMap empTotalMap, String[] dataString,
			String empIdString) {
		boolean result = false;
		try {
			String ledgerCode = dataString[2];
			String year = dataString[1];
			String month = dataString[0];

			Object[][] resignObj = getResignEmp();

			String insertSalaryQuery = "INSERT INTO HRMS_SALARY_"
					+ year
					+ " (SAL_LEDGER_CODE,emp_id , SAL_TOTAL_DEBIT ,SAL_TOTAL_CREDIT,SAL_NET_SALARY,SAL_DAYS,SAL_ONHOLD,SAL_HRS,SAL_MONTH, SAL_YEAR ) "
					+ " VALUES(?,?,?,?,?,?,?,to_date(?,'HH24:MI')," + month
					+ "," + year + ") ";

			String insertSalCreditQuery = "INSERT INTO HRMS_SAL_CREDITS_"
					+ year
					+ " (EMP_ID , SAL_CREDIT_CODE ,SAL_AMOUNT,SAL_LEDGER_CODE,SAL_MONTH, SAL_YEAR) VALUES(?,?,?,'"
					+ ledgerCode + "'," + month + "," + year + ")";

			String insertSalDebitQuery = "INSERT INTO HRMS_SAL_DEBITS_"
					+ year
					+ " (EMP_ID, SAL_DEBIT_CODE ,SAL_AMOUNT,SAL_LEDGER_CODE,SAL_MONTH, SAL_YEAR) VALUES(?,?,?,'"
					+ ledgerCode + "'," + month + "," + year + ")";

			if (empObj != null && empObj.length > 0) {

				Object[][] salDelObj = new Object[empObj.length][1];

				Object[][] salaryInsertObj = new Object[empObj.length][8];

				for (int i = 0; i < empObj.length; i++) {
					double creditTotal = 0;
					double debitTotal = 0;
					double netPay = 0;

					salDelObj[i][0] = String.valueOf(empObj[i][0]);

					Object[][] tempObj = (Object[][]) empTotalMap.get(String
							.valueOf(empObj[i][0]));
					if (tempObj != null && tempObj.length > 0) {
						creditTotal = Double.parseDouble(String
								.valueOf(tempObj[0][0]));
						debitTotal = Double.parseDouble(String
								.valueOf(tempObj[0][1]));
						netPay = Double.parseDouble(String
								.valueOf(tempObj[0][2]));
					}
					salaryInsertObj[i][0] = ledgerCode;
					salaryInsertObj[i][1] = String.valueOf(empObj[i][0]);
					salaryInsertObj[i][2] = debitTotal;
					salaryInsertObj[i][3] = creditTotal;
					salaryInsertObj[i][4] = netPay;
					Object[] data = getDaysWithHrsAndMinutes(String
							.valueOf(empObj[i][7]));
					salaryInsertObj[i][5] = data[0];
					if (resignObj != null && resignObj.length > 0) {
						for (int j = 0; j < resignObj.length; j++) {
							if (String.valueOf(resignObj[j][0]).equals(
									String.valueOf(empObj[i][0]))) {
								salaryInsertObj[i][6] = "Y";
								break;
							} else
								salaryInsertObj[i][6] = "N";
						}
					} else
						salaryInsertObj[i][6] = "N";

					if (String.valueOf(empObj[i][16]).equals("Y")) {
						salaryInsertObj[i][6] = "Y";
					}

					salaryInsertObj[i][7] = data[1];
				}

				try {
					// FIRST DELETE THE DATA
					String query = "DELETE FROM HRMS_SALARY_" + year
							+ " WHERE emp_id=? AND SAL_MONTH=" + month
							+ " AND SAL_YEAR= " + year;
					getSqlModel().singleExecute(query, salDelObj);
					// inserting the records into main salary table
					getSqlModel().singleExecute(insertSalaryQuery,
							salaryInsertObj);
				} catch (Exception e) {
					logger
							.error("Exception in saveSalary HRMS_SALARY_ table -- "
									+ e);
				}

				try {
					// FIRST DELETE THE DATA
					String delCreditquery = "DELETE FROM HRMS_SAL_CREDITS_"
							+ year + " WHERE emp_id=? AND SAL_MONTH=" + month
							+ " AND SAL_YEAR= " + year;
					getSqlModel().singleExecute(delCreditquery, salDelObj);

					// inserting the records into salary credit table
					getSqlModel().singleExecute(insertSalCreditQuery,
							creditObject);

				} catch (Exception e) {
					logger
							.error("Exception in saveSalary HRMS_SAL_CREDITS_ table -- "
									+ e);
				}

				try {
					// FIRST DELETE THE DATA
					String delCreditquery = "DELETE FROM HRMS_SAL_DEBITS_"
							+ year + " WHERE emp_id=? AND SAL_MONTH=" + month
							+ " AND SAL_YEAR= " + year;
					getSqlModel().singleExecute(delCreditquery, salDelObj);
					// inserting the records into salary debit table
					getSqlModel().singleExecute(insertSalDebitQuery,
							debitObject);
				} catch (Exception e) {
					logger
							.error("Exception in saveSalary HRMS_SAL_DEBITS_ table -- "
									+ e);
				}
				result = true;

				/**
				 * this query is used to retrieve center,emp type, rank,
				 * division, department, pay bill of employees from official
				 * details
				 */
				String selectQuery = "SELECT EMP_CENTER,EMP_RANK,EMP_PAYBILL,EMP_TYPE,EMP_DEPT,EMP_DIV,EMP_CADRE,EMP_SAL_GRADE,SAL_ACCNO_REGULAR,SAL_MICR_REGULAR,HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC "
						+ "  LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) where 1=1 "+Utility.getConcatenatedIds("HRMS_EMP_OFFC.EMP_ID", empIdString);

			/*	// if branch is selected
				if (!listOfFilters[0].equals("")) {
					selectQuery += " AND HRMS_EMP_OFFC.EMP_CENTER="
							+ listOfFilters[0];
				}
				// if department is selected
				if (!listOfFilters[1].equals("")) {
					selectQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = "
							+ listOfFilters[1];
				}
				// if paybill group is selected
				if (!listOfFilters[2].equals("")) {
					selectQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL = "
							+ listOfFilters[2];
				}
				// if employee type is selected
				if (!listOfFilters[3].equals("")) {
					selectQuery += " AND HRMS_EMP_OFFC.EMP_TYPE = "
							+ listOfFilters[3];
				}
				// if division is selected
				if (!listOfFilters[4].equals("")) {
					selectQuery += " AND HRMS_EMP_OFFC.EMP_DIV = "
							+ listOfFilters[4];
				}*/

				Object[][] empData = getSqlModel().getSingleResult(selectQuery);

				// below query is used to update center,emp type, rank,
				// division, department, pay bill of employees
				String updateEmpData = " UPDATE HRMS_SALARY_"
						+ year
						+ " SET SAL_EMP_CENTER =?,SAL_EMP_RANK =?,SAL_EMP_PAYBILL =?,SAL_EMP_TYPE =?,SAL_DEPT=?,SAL_DIVISION=?,SAL_EMP_GRADE=?,SAL_EMP_SAL_GRADE=?"
						+ " ,SAL_EMP_ACC_NO=? ,SAL_EMP_BANK_MISC_CODE=? WHERE EMP_ID =? AND SAL_LEDGER_CODE ='"
						+ ledgerCode + "' ";

				getSqlModel().singleExecute(updateEmpData, empData);
			}
		} catch (Exception e) {
			logger.error("Exception in saveSalary  ---------" + e);
			return false;
		}
		return result;
	}

	/**
	 * Purpose :  this method is used to save processed salary 
	 * @param attCode
	 * @return
	 */
	public boolean saveSalProcessStatus(String attCode) {
		boolean result = false;
		// this query is to set ledger status to SAL_START when salary is
		// processed for first time
		String lockQuery = "UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS='SAL_START' WHERE LEDGER_CODE="
				+ attCode;
		result = getSqlModel().singleExecute(lockQuery);
		return result;
	} // end of method saveProcessStatus()

	/**
	 * Purpose :  this method is used to delete salary process
	 * @param ledgerCode
	 * @param year
	 * @return boolean
	 */
	public boolean deleteSalary(String ledgerCode, String year) {
		boolean result = false;
		try {
			String salQuery = " DELETE FROM HRMS_SALARY_" + year
					+ "  WHERE SAL_LEDGER_CODE = " + ledgerCode;
			String creditQuery = " DELETE FROM HRMS_SAL_CREDITS_" + year
					+ "  WHERE SAL_LEDGER_CODE=" + ledgerCode;
			String debitQuery = " DELETE FROM HRMS_SAL_DEBITS_" + year
					+ " WHERE SAL_LEDGER_CODE=" + ledgerCode;
			String statusQuery = " UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS='ATTN_READY' WHERE LEDGER_CODE="
					+ ledgerCode;

			getSqlModel().singleExecute(debitQuery);
			getSqlModel().singleExecute(creditQuery);
			getSqlModel().singleExecute(salQuery);
			getSqlModel().singleExecute(statusQuery);
			result = true;
		} catch (Exception e) {
			logger.error("Exception in deleteSalary  ---------" + e);
		}
		return result;
	}

	/**
	 * Purpose :  this method is used to the salary process
	 * @param ledgerCode
	 * @param bean
	 * @return boolean
	 */
	public boolean lockSalary(String ledgerCode, SalaryProcess bean) {
		Object[][] loan_appl_id = null;
		try {
			NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();

			// this method return the loan application ids for which loan paid
			// flag has to be updated
			nonModel.initiate(context, session);
			loan_appl_id = nonModel.getLoanInstPaid(bean.getMonth(), bean
					.getYear(), bean.getLedgerCode());
			nonModel.terminate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (loan_appl_id != null) {
			try {
				// updating HRMS_LOAN_INSTALMENT table with
				// LOAN_INSTALLMENT_IS_PAID='Y' of retrieved applications
				String updLoan = "UPDATE HRMS_LOAN_INSTALMENT SET LOAN_INSTALLMENT_IS_PAID='Y' WHERE "
						+ " LOAN_APPL_CODE=? AND LOAN_INSTAL_MONTH="
						+ bean.getMonth()
						+ " AND "
						+ " LOAN_INSTAL_YEAR="
						+ bean.getYear() + " ";
				getSqlModel().singleExecute(updLoan, loan_appl_id);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			/**
			 * IF MISS THE INSTALLMENT
			 */

		}
		boolean result = false;
		try {
			String lockQuery = " UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS='SAL_FINAL' WHERE LEDGER_CODE="
					+ ledgerCode;
			result = getSqlModel().singleExecute(lockQuery);
		} catch (Exception e) {
			logger.error("Exception in lockSalary  ---------" + e);
		}

		/**
		 * SAVE YEAR TO DATE VALUES
		 */
		String month = bean.getMonth();
		String year = bean.getYear();

		String branch = bean.getBranchId();
		String dept = bean.getDepartmentId();
		String paybill = bean.getPayBillId();
		String emptype = bean.getEmployeeTypeId();
		String div = bean.getDivisionId();

		// double startTimeAll=System.currentTimeMillis();
		int prvMonth = Integer.parseInt(month) == 1 ? 12 : Integer
				.parseInt(month) - 1;
		int prvYear = Integer.parseInt(month) == 1 ? Integer.parseInt(year) - 1
				: Integer.parseInt(year);

		/*
		 * String query="SELECT SAL_MONTH, SAL_YEAR, EMP_ID FROM
		 * HRMS_SALARY_"+year+" " +" WHERE SAL_MONTH="+month+" AND
		 * SAL_YEAR="+year+" SAL_DIVISION= "+div;
		 */

		String query = "SELECT 0, HRMS_SAL_CREDITS_" + year
				+ ".SAL_CREDIT_CODE,HRMS_SAL_CREDITS_" + year
				+ ".EMP_ID FROM HRMS_SALARY_" + year + " "
				+ "		INNER JOIN  HRMS_SAL_CREDITS_" + year
				+ " ON( HRMS_SAL_CREDITS_" + year + ".EMP_ID=HRMS_SALARY_"
				+ year + ".EMP_ID" + "		AND HRMS_SAL_CREDITS_" + year
				+ ".SAL_LEDGER_CODE=HRMS_SALARY_" + year
				+ ".SAL_LEDGER_CODE AND HRMS_SALARY_" + year + ".SAL_MONTH="
				+ month + " AND HRMS_SALARY_" + year + ".SAL_YEAR =" + year
				+ " )" + "		WHERE SAL_DIVISION= " + div;

		if (!branch.equals("")) {
			query += " AND SAL_EMP_CENTER=" + branch;
		}
		if (!dept.equals("")) {
			query += " AND SAL_DEPT=" + dept;
		}
		if (!paybill.equals("")) {
			query += " AND SAL_EMP_PAYBILL=" + paybill;
		}
		if (!emptype.equals("")) {
			query += " AND SAL_EMP_TYPE=" + emptype;
		}
		// double startTime = System.currentTimeMillis();
		Object[][] obj = getSqlModel().getSingleResult(query);
		// logger.info("time for select
		// credut=="+(System.currentTimeMillis()-startTime));
		String value = " NVL(YEAR_TODATE_SAL_AMOUNT,0) ";
		if (month.equals("4")) {
			value = "0";
		}

		if (obj != null && obj.length > 0) {

			String empPrevCrediQuery = "SELECT " + value + ",HRMS_SAL_CREDITS_"
					+ prvYear
					+ ".EMP_ID||'#'||SAL_CREDIT_CODE FROM HRMS_SAL_CREDITS_"
					+ prvYear + " INNER JOIN HRMS_SALARY_" + prvYear
					+ " on (HRMS_SAL_CREDITS_" + prvYear
					+ ".SAL_MONTH=HRMS_SALARY_" + prvYear + ".SAL_MONTH and "
					+ " HRMS_SAL_CREDITS_" + prvYear + ".EMP_ID=HRMS_SALARY_"
					+ prvYear + ".EMP_ID)" + " WHERE HRMS_SAL_CREDITS_"
					+ prvYear + ".SAL_MONTH=" + prvMonth + " AND SAL_DIVISION="
					+ div + " ORDER BY HRMS_SAL_CREDITS_" + prvYear
					+ ".EMP_ID,SAL_CREDIT_CODE";
			Object[][] empPrevCrediObj = getSqlModel().getSingleResult(
					empPrevCrediQuery);
			HashMap empPrevCrediMap = new HashMap();
			for (int i = 0; i < empPrevCrediObj.length; i++) {
				empPrevCrediMap.put(String.valueOf(empPrevCrediObj[i][1]),
						String.valueOf(empPrevCrediObj[i][0]));

			}
			for (int i = 0; i < obj.length; i++) {
				try {
					String key = String.valueOf(obj[i][2]) + "#"
							+ String.valueOf(obj[i][1]);
					String prevYearToDate = String.valueOf(empPrevCrediMap
							.get(key));
					if (prevYearToDate != null && !prevYearToDate.equals("")
							&& !prevYearToDate.equals("null"))
						obj[i][0] = String.valueOf(empPrevCrediMap.get(key));
					else
						obj[i][0] = "0";
				} catch (Exception e) {
					obj[i][0] = "0";
				}
			}
			String updateQuery = "UPDATE HRMS_SAL_CREDITS_" + year
					+ " SET YEAR_TODATE_SAL_AMOUNT=SAL_AMOUNT+ ?"
					// +" NVL((SELECT "+value+" FROM
					// HRMS_SAL_CREDITS_"+prvYear+" WHERE SAL_MONTH="+prvMonth+"
					// AND SAL_YEAR="+prvYear+" AND SAL_CREDIT_CODE=? AND
					// EMP_ID=?),0) "
					+ "	WHERE SAL_MONTH=" + month + " AND SAL_YEAR=" + year
					+ " AND SAL_CREDIT_CODE=? AND EMP_ID=? ";
			// startTime=System.currentTimeMillis();
			getSqlModel().singleExecute(updateQuery, obj);
			// logger.info("time for update
			// credit=="+(System.currentTimeMillis()-startTime));
		}

		String debitQuery = "SELECT  0,HRMS_SAL_DEBITS_" + year
				+ ".SAL_DEBIT_CODE ,HRMS_SAL_DEBITS_" + year
				+ ".EMP_ID AS CODE FROM HRMS_SALARY_" + year + " "
				+ "		INNER JOIN  HRMS_SAL_DEBITS_" + year
				+ " ON( HRMS_SAL_DEBITS_" + year + ".EMP_ID=HRMS_SALARY_"
				+ year + ".EMP_ID" + "		AND HRMS_SAL_DEBITS_" + year
				+ ".SAL_LEDGER_CODE=HRMS_SALARY_" + year
				+ ".SAL_LEDGER_CODE AND HRMS_SALARY_" + year + ".SAL_MONTH="
				+ month + " AND HRMS_SALARY_" + year + ".SAL_YEAR =" + year
				+ " )" + "		WHERE SAL_DIVISION= " + div;

		if (!branch.equals("")) {
			debitQuery += " AND SAL_EMP_CENTER=" + branch;
		}
		if (!dept.equals("")) {
			debitQuery += " AND SAL_DEPT=" + dept;
		}
		if (!paybill.equals("")) {
			debitQuery += " AND SAL_EMP_PAYBILL=" + paybill;
		}
		if (!emptype.equals("")) {
			debitQuery += " AND SAL_EMP_TYPE=" + emptype;
		}
		// startTime=System.currentTimeMillis();
		Object[][] debitObj = getSqlModel().getSingleResult(debitQuery);
		// logger.info("time for select
		// debit=="+(System.currentTimeMillis()-startTime));

		if (debitObj != null && debitObj.length > 0) {
			String empPrevDebitQuery = "SELECT " + value + ",HRMS_SAL_DEBITS_"
					+ prvYear
					+ ".EMP_ID||'#'||SAL_DEBIT_CODE FROM HRMS_SAL_DEBITS_"
					+ prvYear + " INNER JOIN HRMS_SALARY_" + prvYear
					+ " on (HRMS_SAL_DEBITS_" + prvYear
					+ ".SAL_MONTH=HRMS_SALARY_" + prvYear + ".SAL_MONTH and "
					+ " HRMS_SAL_DEBITS_" + prvYear + ".EMP_ID=HRMS_SALARY_"
					+ prvYear + ".EMP_ID)" + " WHERE HRMS_SAL_DEBITS_"
					+ prvYear + ".SAL_MONTH=" + prvMonth
					+ " ORDER BY HRMS_SAL_DEBITS_" + prvYear
					+ ".EMP_ID,SAL_DEBIT_CODE";
			Object[][] empPrevDebitObj = getSqlModel().getSingleResult(
					empPrevDebitQuery);
			HashMap empPrevDebitMap = new HashMap();
			for (int i = 0; i < empPrevDebitObj.length; i++) {
				empPrevDebitMap.put(String.valueOf(empPrevDebitObj[i][1]),
						String.valueOf(empPrevDebitObj[i][0]));

			}
			for (int i = 0; i < debitObj.length; i++) {
				try {
					String key = String.valueOf(obj[i][2]) + "#"
							+ String.valueOf(obj[i][1]);
					String prevYearToDate = String.valueOf(empPrevDebitMap
							.get(key));
					if (prevYearToDate != null && !prevYearToDate.equals("")
							&& !prevYearToDate.equals("null"))
						debitObj[i][0] = prevYearToDate;
					else {
						debitObj[i][0] = "0";
					}
				} catch (Exception e) {
					debitObj[i][0] = "0";
				}
			}

			String updateQuery = "UPDATE HRMS_SAL_DEBITS_" + year
					+ " SET YEAR_TODATE_SAL_AMOUNT=SAL_AMOUNT+? "
					// +" NVL((SELECT "+value+" FROM HRMS_SAL_DEBITS_"+prvYear+"
					// WHERE SAL_MONTH="+prvMonth+" AND SAL_YEAR="+prvYear+" AND
					// SAL_DEBIT_CODE=? AND EMP_ID=?),0) "
					+ "	WHERE SAL_MONTH=" + month + " AND SAL_YEAR=" + year
					+ " AND SAL_DEBIT_CODE=? AND EMP_ID=? ";
			// startTime=System.currentTimeMillis();
			getSqlModel().singleExecute(updateQuery, debitObj);
			// logger.info("time for update
			// debit=="+(System.currentTimeMillis()-startTime));
		}

		// logger.info("time for
		// final=="+(System.currentTimeMillis()-startTimeAll));
		return result;
	}

	/**
	 * Purpose :  this method is used to lock salary
	 * @param ledgerCode
	 * @param bean
	 * @return boolean
	 */
	public boolean unlockSalary(String ledgerCode, SalaryProcess bean) {
		boolean result = false;

		Object[][] loan_appl_id = null;
		try {
			NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();

			// this method return the loan application ids for which loan paid
			// flag has to be updated
			nonModel.initiate(context, session);
			loan_appl_id = nonModel.getLoanInstPaid(bean.getMonth(), bean
					.getYear(), bean.getLedgerCode());
			nonModel.terminate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (loan_appl_id != null) {
			try {
				// updating HRMS_LOAN_INSTALMENT table with
				// LOAN_INSTALLMENT_IS_PAID='Y' of retrieved applications
				String updLoan = "UPDATE HRMS_LOAN_INSTALMENT SET LOAN_INSTALLMENT_IS_PAID='N' WHERE "
						+ " LOAN_APPL_CODE=? AND LOAN_INSTAL_MONTH="
						+ bean.getMonth()
						+ " AND "
						+ " LOAN_INSTAL_YEAR="
						+ bean.getYear() + " ";
				getSqlModel().singleExecute(updLoan, loan_appl_id);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		try {
			String lockQuery = " UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS='SAL_START' WHERE LEDGER_CODE="
					+ ledgerCode;
			result = getSqlModel().singleExecute(lockQuery);
		} catch (Exception e) {
			logger.error("Exception in unlockSalary  ---------" + e);
		}
		return result;
	}

	/**
	 * Purpose :  this method is used to get resigned employee 
	 * @return
	 */
	public Object[][] getResignEmp() {
		Object emp_id[][] = null;
		try {
			String selectSalary = " SELECT DISTINCT RESIGN_EMP FROM HRMS_RESIGN WHERE RESIGN_WITHDRAWN !='Y' ";
			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			logger.error("exception in query execution of getResignEmp" + e);
		}
		return emp_id;
	} // end of method getResignEmp()

	/**
	 *Purpose :  this method is used to get ptax amount
	 * DOM Parser
	 * 
	 * @param path,
	 * @return object
	 */
	public Object[][] getPtaxAmount(String path, String month, String year,
			String location, double grossSal) {
		Object ptax_amt[][] = null;
		Object ptax_data[][] = null;
		// Document document=null;
		try {
			/*
			 * path = path +"/profTax.xml"; document = new
			 * XMLReaderWriter().parse(new File(path)); List fonts =
			 * document.selectNodes("//PAYROLL/PTAX"); ptax_amt = new
			 * String[fonts.size()][5]; int count = 0; for (Iterator iter =
			 * fonts.iterator(); iter.hasNext();) { Element font = (Element)
			 * iter.next(); ptax_amt[count][0] =
			 * font.attributeValue("code").trim(); ptax_amt[count][1] =
			 * font.attributeValue("location").trim(); ptax_amt[count][2] =
			 * font.attributeValue("frmAmt").trim(); ptax_amt[count][3] =
			 * font.attributeValue("toAmt").trim(); ptax_amt[count][4] =
			 * font.attributeValue("effectiveDate").trim(); count++; }
			 *//**
				 * this method is used to get professional tax code of previous
				 * latest effective date from processing month and year
				 */
			/*
			 * String ptaxCode =
			 * getPtaxDebitCode(ptax_amt,month,year,location,grossSal);
			 * if(ptaxCode==null){ ptaxCode="0"; } // these statements are used
			 * to read from xml entries for that particular ptaxCode using SAX
			 * parser SaxParserPtax sax = new SaxParserPtax(); String reqXml =
			 * new Utility().readFileAsString(path); ptax_data = sax.parse(new
			 * InputSource(new StringReader(reqXml)),ptaxCode);
			 */

			if (ptax_data_obj != null && ptax_data_obj.length > 0) {
				ptax_amt = new String[ptax_data_obj.length][5];
				for (int i = 0; i < ptax_data_obj.length; i++) {
					ptax_amt[i][0] = String.valueOf(ptax_data_obj[i][0]);// code
					ptax_amt[i][1] = String.valueOf(ptax_data_obj[i][1]);// location
					ptax_amt[i][2] = String.valueOf(ptax_data_obj[i][2]);// frmAmt
					ptax_amt[i][3] = String.valueOf(ptax_data_obj[i][3]);// toAmt
					ptax_amt[i][4] = String.valueOf(ptax_data_obj[i][7]);// effectiveDate
				}

				String ptaxCode = getPtaxDebitCode(ptax_amt, month, year,
						location, grossSal);
				if (ptaxCode != null) {
					for (int i = 0; i < ptax_data_obj.length; i++) {
						if (String.valueOf(ptax_data_obj[i][0])
								.equals(ptaxCode)) {
							ptax_data = new Object[1][8];
							ptax_data[0][0] = ptax_data_obj[i][0];// code
							ptax_data[0][1] = ptax_data_obj[i][1];// location
							ptax_data[0][2] = ptax_data_obj[i][2];// frmAmt
							ptax_data[0][3] = ptax_data_obj[i][3];// toAmt
							ptax_data[0][4] = ptax_data_obj[i][4];// varMonth
							ptax_data[0][5] = ptax_data_obj[i][5];// fixamt
							ptax_data[0][6] = ptax_data_obj[i][6];// varAmt
							ptax_data[0][7] = ptax_data_obj[i][8];// debitCode
						}
					}
				}

			}

		} catch (Exception e) {
			logger.error("Exception in getPtaxAmount  ---------" + e);
		}
		return ptax_data;
	}

	/**
	 * Purpose : this method is used to find out the professional code for particular
	 * location and Gross Salary slab and previous latest effective date
	 * depending on process month and year from the professinal tax parameter
	 * 
	 * @param taxData,
	 *            month, year, location, grossSal
	 * @return ptax code
	 */
	public String getPtaxDebitCode(Object[][] taxData, String month,
			String year, String location, double grossSal) {
		try {
			grossSal = Math.floor(grossSal);
			for (int i = 0; i < taxData.length; i++) {
				// getting month, year from effective date field in professional
				// tax parameter
				String effmonth = String.valueOf(taxData[i][4]).substring(3, 5);
				String effyear = String.valueOf(taxData[i][4]).substring(6, 10);
				if (String.valueOf(location).trim().equals(
						String.valueOf(taxData[i][1]).trim())) {
					if (grossSal >= Integer.parseInt(String.valueOf(
							taxData[i][2]).trim())
							&& grossSal <= Double.parseDouble(String.valueOf(
									taxData[i][3]).trim())) {
						// comparing to get professional tax code of previous
						// latest effective date from processing month and year
						if ((Integer.parseInt(effmonth) <= Integer
								.parseInt(month) && Integer.parseInt(effyear) <= Integer
								.parseInt(year))
								|| (Integer.parseInt(effmonth) >= Integer
										.parseInt(month) && Integer
										.parseInt(effyear) < Integer
										.parseInt(year))) {
							return String.valueOf(taxData[i][0]);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getPtaxDebitCode  ---------" + e);
		}
		return "0";
	} // end of method getPtaxDebitCode()

	/**
	 * Purpose : this method is used to get PF parameter details from XML file
	 * @param path
	 *            where XML file stored using SAX parser
	 * @return pf_data Object with PF parameter details
	 */
	public Object[][] getPFData(String path, String month, String year) {
		Object[][] pf_data = null;
		Object[] pf_dates = null;
		// Document document=null;
		try {
			/*
			 * path = path +"/pfParameter.xml"; document = new
			 * XMLReaderWriter().parse(new File(path)); List fonts =
			 * document.selectNodes("//PAYROLL/PFPARAM"); pf_dates= new
			 * String[fonts.size()]; int count = 0; // this loop is used to set
			 * values from List 'fonts' to Object 'credit_header' for (Iterator
			 * iter = fonts.iterator(); iter.hasNext();) { Element font =
			 * (Element) iter.next(); pf_dates[count] =
			 * font.attributeValue("date").trim(); count++; } // end of for loop
			 * //this method is used to get effective date depending on process
			 * month and year String pfEffDate = getDate(pf_dates,month,year); //
			 * these statements are used to read from xml record for that
			 * particular effective date using SAX parser SaxParserPf sax = new
			 * SaxParserPf(); String reqXml = new
			 * Utility().readFileAsString(path); pf_data = sax.parse(new
			 * InputSource(new StringReader(reqXml)),pfEffDate);
			 */

			if (pf_data_obj != null && pf_data_obj.length > 0) {
				pf_dates = new String[pf_data_obj.length];
				for (int i = 0; i < pf_data_obj.length; i++) {
					pf_dates[i] = String.valueOf(pf_data_obj[i][3]);
				}
				String pfEffDate = getDate(pf_dates, month, year);
				if (pfEffDate != null && pfEffDate.length() > 0) {
					for (int i = 0; i < pf_data_obj.length; i++) {
						if (String.valueOf(pf_data_obj[i][3]).equals(pfEffDate)) {
							pf_data = new Object[1][8];
							pf_data[0][0] = pf_data_obj[i][0];
							pf_data[0][1] = pf_data_obj[i][1];
							pf_data[0][2] = pf_data_obj[i][2];
							pf_data[0][3] = pf_data_obj[i][3];
							pf_data[0][4] = pf_data_obj[i][4];
							pf_data[0][5] = pf_data_obj[i][5];
							pf_data[0][6] = pf_data_obj[i][6];
							pf_data[0][7] = pf_data_obj[i][7];
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error("Exception in getPFData  ---------" + e);
		}
		return pf_data;
	} // end of method getPFData()

	/**
	 * Purpose : this method is used to get ESI parameter details from XML file
	 * 
	 * @param path
	 *            where XML file stored using SAX parser
	 * @return pf_data Object with PF parameter details
	 */
	public Object[][] getESIData(String path, String month, String year) {
		Object[][] esi_data = null;
		// Document document=null;
		Object[] esi_dates = null;
		try {

			if (esi_data_obj != null && esi_data_obj.length > 0) {
				esi_dates = new String[esi_data_obj.length];
				for (int i = 0; i < esi_data_obj.length; i++) {
					esi_dates[i] = String.valueOf(esi_data_obj[i][3]);
				}
				String esiEffDate = getDate(esi_dates, month, year);
				if (esiEffDate != null && esiEffDate.length() > 0) {
					for (int i = 0; i < esi_data_obj.length; i++) {
						if (String.valueOf(esi_data_obj[i][3]).equals(
								esiEffDate)) {
							esi_data = new Object[1][7];
							esi_data[0][0] = esi_data_obj[i][0];
							esi_data[0][1] = esi_data_obj[i][1];
							esi_data[0][2] = esi_data_obj[i][2];
							esi_data[0][3] = esi_data_obj[i][3];
							esi_data[0][4] = esi_data_obj[i][4];
							esi_data[0][5] = esi_data_obj[i][5];
							esi_data[0][6] = esi_data_obj[i][6];
							// esi_data[0][7]=esi_data_obj[i][7];
						}
					}
				}
			}



		} catch (Exception e) {
			e.printStackTrace();
		}
		return esi_data;
	} // end of method gerESIData()

	/**
	 * this method is used to get effective date depending on process month and
	 * year
	 * 
	 * @param pfDate --
	 *            parameter data
	 * @param month --
	 *            processing month
	 * @param year --
	 *            processing year
	 * @return effective date
	 */
	public String getDate(Object[] paraObj, String month, String year) {
		try {
			for (int i = 0; i < paraObj.length; i++) {
				// getting month, year from effective date field in parameters
				String effmonth = String.valueOf(paraObj[i]).substring(3, 5);
				String effyear = String.valueOf(paraObj[i]).substring(6, 10);

				// comparing to get previous latest effective date from
				// processing month and year
				if ((Integer.parseInt(effmonth) <= Integer.parseInt(month) && Integer
						.parseInt(effyear) <= Integer.parseInt(year))
						|| (Integer.parseInt(effmonth) >= Integer
								.parseInt(month) && Integer.parseInt(effyear) < Integer
								.parseInt(year))) {
					return String.valueOf(paraObj[i]);
				}
			}
		} catch (Exception e) {
			logger.error(
					"exception in getting date for pf and esi in getDate()", e);
		}
		return "";
	} // end of method getDate()

	/**
	 * Purpose : this method is used to pf trust data
	 * @return
	 */
	public Object[][] getPFTrustData() {
		Object[][] pf_trust_data = null;
		try {

			String pfTrustQuery = " SELECT PFT_DEBIT_CODE,PFT_DEDUCTION,PFT_MAX_DEDUCTION,PFT_LOAN_CODE "
					+ " FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE=(SELECT MAX(PFT_EFFECTIVE_DATE)"
					+ " FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE) ";
			pf_trust_data = getSqlModel().getSingleResult(pfTrustQuery);
		} catch (Exception e) {
			logger.error("Exception while getting the pftrust data---------"
					+ e);
		}
		return pf_trust_data;
	}

	/**
	 * Purpose : this method is used to get vpf configuration
	 * @return object
	 */
	public Object[][] getVPFConfig() {
		Object[][] resultObj = null;
		try {
			String query = " SELECT VPF_DEDUCTION, VPF_DEBIT_CODE, VPF_DEDUCTION_TYPE, VPF_MAX_DEDUCTION FROM HRMS_VPF_CONF ";
			resultObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("Exception getting getVPFConfig  ---------" + e);
		}
		return resultObj;
	}

	/**
	 *  this method will check whether employeeBranch and employeeType is having
	 *  pfApplicability,esicApplicability,ptApplicablity on or off
	 * @param typeId
	 * @param branchId
	 * @param index
	 * @param path
	 * @return
	 */
	public boolean getEmpTypeBranchApplicabilityChk(String typeId,
			String branchId, int index, String path) {
		boolean branchFlag = true;
		boolean empTypeFlag = true;
		try {
			/*
			 * String pathBranch = path +"/salaryZone_branch.xml";
			 * SaxParserBranch sax = new SaxParserBranch(); String reqXml = new
			 * Utility().readFileAsString(pathBranch); Object[][] braData =
			 * sax.parse(new InputSource(new StringReader(reqXml)),branchId);
			 */

			/*
			 * String pathType = path +"/salaryZone_emptype.xml"; SaxParserType
			 * saxType = new SaxParserType(); String reqXmlType = new
			 * Utility().readFileAsString(pathType); Object[][] typeData =
			 * saxType.parse(new InputSource(new
			 * StringReader(reqXmlType)),typeId);
			 */

			try {
				for (int i = 0; i < typeData.length; i++) {
					if (String.valueOf(typeData[i][0]).equals(typeId)) {
						if (!typeData[i][index].equals("Y")) {
							// return false;
							empTypeFlag = false;
						}
					}
				}
			} catch (RuntimeException e) {
				logger.error(e.getMessage());
			}
			if (braData != null && braData.length > 0) {
				for (int i = 0; i < braData.length; i++) {
					if (String.valueOf(braData[i][0]).equals(branchId)) {
						if (!braData[i][index].equals("Y")) {
							// return false;
							branchFlag = false;
						}
					}
				}
			}
		} catch (Exception e) {
			logger
					.error("Exception in getEmpTypeBranchApplicabilityChk  ---------"
							+ e);
			// e.printStackTrace();
		}
		if (branchFlag && empTypeFlag) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * Purpose : this method will check whether Minimum amount condition is on or off for
	 * particular employee type
	 * @param typeId
	 * @param index
	 * @param path
	 * @return
	 */
	public boolean getEmpTypeMinAmtChkCondition(String typeId, int index,
			String path) {
		try {
			try {
				if (typeData != null && typeData.length > 0) {
					for (int i = 0; i < typeData.length; i++) {
						if (String.valueOf(typeData[i][0]).equals(typeId)) {
							if (!typeData[i][index].equals("Y")) {
								return false;
							}
						}
					}
				}
			} catch (Exception e) {
				logger
						.error("Exception while checking the emp type and min amount condition 4 PF---------"
								+ e);
			}

		} catch (Exception e) {
			logger.error("Exception in getEmpTypeMinAmtChkCondition  ---------"
					+ e);
		}
		return true;
	}

	/**
	 * Purpose : this method is used to get pf amount 
	 * @param pfMaxEmolCheck
	 * @param salCredit
	 * @param maxEmol
	 * @param pfPercentage
	 * @return double
	 */
	public double getpfAmtWithRuleCheck(String pfMaxEmolCheck,
			double salCredit, String maxEmol, String pfPercentage) {
		double pf_amount = 0;
		try {

			if (String.valueOf(pfMaxEmolCheck).equals("Y")) {
				if (salCredit <= Double.parseDouble(String.valueOf(maxEmol)))
					pf_amount = ((salCredit * Double.parseDouble(pfPercentage)) / 100);
				else
					pf_amount = ((Double.parseDouble(String.valueOf(maxEmol)) * Double
							.parseDouble(pfPercentage)) / 100);
			} else
				pf_amount = ((salCredit * Double.parseDouble(pfPercentage)) / 100);

		} catch (Exception e) {
			logger.error("Exception in getpfAmtWithRuleCheck  ---------" + e);
			pf_amount = 0;
		}
		return pf_amount;
	}

	/**
	 * Purpose : this method is used to get income tax configuration
	 * @param month
	 * @param year
	 * @return object
	 */
	public Object[][] getIncomeTaxConfigObject(String month, String year) {
		Object[][] resultObj = null;
		try {
			String query = " SELECT TDS_DEBIT_CODE FROM HRMS_TAX_PARAMETER WHERE TO_CHAR(TDS_EFF_DATE,'dd-MON-yyyy') =  (select max(TDS_EFF_DATE) "
					+ " from HRMS_TAX_PARAMETER where to_char(TDS_EFF_DATE,'yyyy-mm')<='"
					+ year + "-" + month + "')";
			resultObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("Exception getting the incometax debit code ---------"
					+ e);
		}
		return resultObj;
	}

	/**
	 * Purpose : this method is used to set null value as zero
	 * @param result
	 * @return
	 */
	public static String checkNull_Zero(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "0";
		} else {
			return result;
		}
	}

	/**
	 * Purpose : this method is used to round ofvalue
	 * @param ch
	 * @param amount
	 * @return
	 */
	public double roundCheck(int ch, double amount) {
		try {

			switch (ch) {
			case 0:
				return (Double.parseDouble(formatter.format(amount)));

			case 1:
				return Math.round(Double.parseDouble(formatter.format(amount)));

			case 2:
				return Math.floor(Double.parseDouble(formatter.format(amount)));

			case 3:
				return Math.ceil(Double.parseDouble(formatter.format(amount)));

			case 4:
				if (!(Math.round((amount)) % 10 == 0))
					return Double
							.parseDouble(formatter
									.format((Math.round(amount) + (10 - (Math
											.round(amount) % 10)))));
				else
					return Math.round(amount);

			default:
				return amount;
			}
		} catch (Exception e) {
			logger.error("Exception in getting roundCheck  ---------" + e);
			return Double.parseDouble(formatter.format(amount));
		}
	}

	/**
	 * Purpose : this method is used to get loan data
	 * @param month
	 * @param year
	 * @returnHashMap
	 */
	public HashMap<String, Object[][]> getEmpLoanMap(String month, String year) {
		HashMap<String, Object[][]> empLoanMap = new HashMap<String, Object[][]>();
		try {
			String loanQuery = " SELECT HRMS_LOAN_INSTALMENT.LOAN_INSTAL_AMT,HRMS_LOAN_MASTER.LOAN_CODE, "
					+ " HRMS_LOAN_MASTER.LOAN_DEBIT_CODE,HRMS_LOAN_INSTALMENT.LOAN_EMP_ID FROM HRMS_LOAN_INSTALMENT "
					+ " INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_INSTALMENT.LOAN_APPL_CODE) "
					+ " INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE =  HRMS_LOAN_APPLICATION.LOAN_CODE ) "
					+ " WHERE LOAN_INSTAL_MONTH = "
					+ month
					+ " and LOAN_INSTAL_year = "
					+ year
					+ " AND LOAN_INSTALLMENT_IS_PAID='N' "
					+ " ORDER BY HRMS_LOAN_INSTALMENT.LOAN_EMP_ID ";
			empLoanMap = getSqlModel().getSingleResultMap(loanQuery, 3, 2);
		} catch (Exception e) {
			logger.error("Exception in getEmpLoanMap:" + e);
		}
		return empLoanMap;
	}

	/**
	 * Purpose:this method is used to get lic data
	 * @return HashMap
	 */
	public HashMap<String, Object[][]> getEmpLicMap() {
		HashMap<String, Object[][]> empLicMap = new HashMap<String, Object[][]>();
		try {
			String licQuery = " SELECT EMP_ID,SUM(LIC_MONTHLY_PREMIUM),LIC_PAID_IN_DEBIT_CODE FROM HRMS_LIC "
					+ " GROUP BY EMP_ID,LIC_PAID_IN_DEBIT_CODE ORDER BY EMP_ID,LIC_PAID_IN_DEBIT_CODE ";
			empLicMap = getSqlModel().getSingleResultMap(licQuery, 0, 2);
		} catch (Exception e) {
			logger.error("Exception in getEmpLoanMap:" + e);
		}
		return empLicMap;
	}

	/**
	 * makeSavedEmpObjSameSize
	 * @param creditMap
	 * @param empIdObj
	 * @param creditHead
	 * @return object
	 */
	public Object[][] createInsertObj(HashMap<String, Object[][]> creditMap,
			Object[][] empIdObj, Object[][] creditHead) {
		Object[][] returnObj = null;
		try {
			if (creditMap != null && creditMap.size() > 0) {
				if (empIdObj != null && empIdObj.length > 0) {
					returnObj = new Object[empIdObj.length * creditHead.length][3];
					int creditCount = 0;
					for (int i = 0; i < empIdObj.length; i++) {
						Object[][] tempCredit = creditMap.get(String
								.valueOf(empIdObj[i][0]));
						try {
							if (tempCredit != null || tempCredit.length > 0) {
								for (int j = 0; j < tempCredit.length; j++) {
									returnObj[creditCount][0] = String
											.valueOf(empIdObj[i][0]);
									returnObj[creditCount][1] = String
											.valueOf(tempCredit[j][0]);
									returnObj[creditCount][2] = String
											.valueOf(tempCredit[j][1]);
									creditCount++;
								}
							} else {
								for (int j = 0; j < creditHead.length; j++) {
									returnObj[creditCount][0] = String
											.valueOf(empIdObj[i][0]);
									returnObj[creditCount][1] = String
											.valueOf(creditHead[j][0]);
									returnObj[creditCount][2] = "0";
									creditCount++;
								}
							}
						} catch (Exception e) {
							for (int j = 0; j < creditHead.length; j++) {
								returnObj[creditCount][0] = String
										.valueOf(empIdObj[i][0]);
								returnObj[creditCount][1] = String
										.valueOf(creditHead[j][0]);
								returnObj[creditCount][2] = "0";
								creditCount++;
							}
						}
					} // end of empIdObj for loop
				} // end of empIdObj if
			} // end of creditMap if
			else {
				if (empIdObj != null && empIdObj.length > 0) {
					returnObj = new Object[empIdObj.length * creditHead.length][3];
					int creditCount = 0;
					for (int i = 0; i < empIdObj.length; i++) {
						try {
							for (int j = 0; j < creditHead.length; j++) {
								returnObj[creditCount][0] = String
										.valueOf(empIdObj[i][0]);
								returnObj[creditCount][1] = String
										.valueOf(creditHead[j][0]);
								returnObj[creditCount][2] = "0";
								creditCount++;
							}

						} catch (Exception e) {
							for (int j = 0; j < creditHead.length; j++) {
								returnObj[creditCount][0] = String
										.valueOf(empIdObj[i][0]);
								returnObj[creditCount][1] = String
										.valueOf(creditHead[j][0]);
								returnObj[creditCount][2] = "0";
								creditCount++;
							}
						}
					} // end of empIdObj for loop
				} // end of empIdObj if
			}
		} catch (Exception e) {
			logger.error("Exception in createInsertObj:" + e);
		}
		return returnObj;
	}

	/**
	 * 
	 * @param tempObj
	 * @param debitCodes
	 * @param empId
	 * @return
	 */
	public Object[][] makeEmpObjSameSizeDebit(Object[][] tempObj,
			Object[][] debitCodes, String empId) {
		Object[][] modifiedObj = null;
		try {
			modifiedObj = new Object[debitCodes.length][6];
			if (tempObj != null && tempObj.length > 0) {

				int j = 0;
				try {
					for (int i = 0; i < debitCodes.length; i++) {
						if (j < tempObj.length
								&& String.valueOf(debitCodes[i][0]).equals(
										String.valueOf(tempObj[j][1]))) {

							for (int l = 0; l < modifiedObj[0].length; l++) {
								modifiedObj[i][l] = tempObj[j][l];
							}
							j++;
	
						} else {
							modifiedObj[i][0] = tempObj[0][0];
							modifiedObj[i][1] = debitCodes[i][0];
							modifiedObj[i][2] = 0;
							modifiedObj[i][3] = debitCodes[i][1];
							modifiedObj[i][4] = debitCodes[i][2];
							modifiedObj[i][5] = debitCodes[i][3];
						}
					}
				} catch (Exception e) {
					logger.error("Exception in processEmpCredit inner loop:"
							+ e);
					e.printStackTrace();
				}
			} else {
				for (int i = 0; i < debitCodes.length; i++) {
					modifiedObj[i][0] = empId;
					modifiedObj[i][1] = debitCodes[i][0];
					modifiedObj[i][2] = 0;
					modifiedObj[i][3] = debitCodes[i][1];
					modifiedObj[i][4] = debitCodes[i][2];
					modifiedObj[i][5] = debitCodes[i][3];
				}
			}
		} catch (Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		return modifiedObj;
	}

	/**
	 *  Purpose : this method is used to makeSavedEmpObjSameSize
	 * @param tempObj
	 * @param creditCodes
	 * @param empId
	 * @return object 
	 */
	public Object[][] makeSavedEmpObjSameSize(Object[][] tempObj,
			Object[][] creditCodes, String empId) {
		Object[][] modifiedObj = null;
		try {
			if (tempObj != null && tempObj.length > 0) {
				modifiedObj = new Object[creditCodes.length][tempObj[0].length];
				int j = 0;
				try {
					for (int i = 0; i < creditCodes.length; i++) {
						if (j < tempObj.length
								&& String.valueOf(creditCodes[i][0]).equals(
										String.valueOf(tempObj[j][0]))) {

							modifiedObj[i][0] = tempObj[0][0];// creditcode
							modifiedObj[i][1] = tempObj[j][1];// amt
							modifiedObj[i][2] = tempObj[j++][2];// emp_id
						} else {
		
							modifiedObj[i][0] = creditCodes[i][0];
							modifiedObj[i][1] = 0;
							modifiedObj[i][2] = empId;

						}
					}
				} catch (Exception e) {
					logger.error("Exception in processEmpCredit inner loop:"
							+ e);
					e.printStackTrace();
				}
			} else {

			}
		} catch (Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		return modifiedObj;
	}

	/**
	 *  Purpose : this method is used 
	 * @param tempObj
	 * @param creditCodes
	 * @param empId
	 * @return object
	 */
	public Object[][] makeEmpObjSameSize(Object[][] tempObj,
			Object[][] creditCodes, String empId) {
		Object[][] modifiedObj = null;
		try {
			if (tempObj != null && tempObj.length > 0) {
				modifiedObj = new Object[creditCodes.length][tempObj[0].length];
				int j = 0;
				try {
					for (int i = 0; i < creditCodes.length; i++) {
						if (j < tempObj.length
								&& String.valueOf(creditCodes[i][0]).equals(
										String.valueOf(tempObj[j][1]))) {

							/*
							 * for (int l = 0; l < modifiedObj[0].length; l++) {
							 * modifiedObj[i][l] = tempObj[j][l]; } j++;
							 */
							modifiedObj[i][0] = tempObj[0][0];// emp_id
							modifiedObj[i][1] = tempObj[j][1];// creditcode
							modifiedObj[i][2] = tempObj[j][2];// amt
							modifiedObj[i][3] = tempObj[j][3];// esi_applicable
							modifiedObj[i][4] = tempObj[j][4];// ptax_applicable
							modifiedObj[i][5] = tempObj[j][5];// credit type
							modifiedObj[i][6] = tempObj[j][6];// credit formula
							modifiedObj[i][7] = tempObj[j++][7];// credit max value

						} else {
	
							modifiedObj[i][0] = tempObj[0][0];
							modifiedObj[i][1] = creditCodes[i][0];
							modifiedObj[i][2] = 0;
							modifiedObj[i][3] = creditCodes[i][1];
							modifiedObj[i][4] = creditCodes[i][2];
							modifiedObj[i][5] = creditCodes[i][3];
							modifiedObj[i][6] = creditCodes[i][5]; // credit formula
							modifiedObj[i][7] = creditCodes[i][6]; // credit max value
						}
					}
				} catch (Exception e) {
					logger.error("Exception in processEmpCredit inner loop:"
							+ e);
					e.printStackTrace();
				}
			} else {
				modifiedObj = new Object[creditCodes.length][8];
				for (int i = 0; i < creditCodes.length; i++) {
					modifiedObj[i][0] = empId;
					modifiedObj[i][1] = creditCodes[i][0];
					modifiedObj[i][2] = 0;
					modifiedObj[i][3] = creditCodes[i][1];
					modifiedObj[i][4] = creditCodes[i][2];
					modifiedObj[i][5] = creditCodes[i][3];
					modifiedObj[i][6] = creditCodes[i][5]; // credit formula
					modifiedObj[i][7] = creditCodes[i][6]; // credit max value
				}

			}
		} catch (Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		return modifiedObj;
	}

	/**
	 *  Purpose : this method is used to check null
	 * @param result
	 * @return string
	 */
	public static String checkNull_Space(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 *  Purpose : this method is used to view selected employee credit debit details
	 * @param dataString
	 * @param request
	 * @param bean
	 * @param attnEmpIdSaved
	 * @param listOfFilters
	 * @return object
	 */
	public Object[][] getEmployeeDataToView(String[] dataString,
			HttpServletRequest request, SalaryProcess bean,
			Object[][] attnEmpIdSaved, String[] listOfFilters) {
		Object[][] allEmpRow = null;
		try {
			String path = dataString[0];
			String year = dataString[1];
			String attnCode = dataString[2];
			String recordPerPage = dataString[3];

			Object[][] creditHead = getCreditHeader(path);
			Object[][] debitHead = getDebitHeader(path);

			for (int i = 0; i < debitHead.length; i++) {
				for (int j = 0; j < debitHead[0].length; j++) {
					System.out.println("debitHead:" + debitHead[i][j]);
				}
			}
			for (int i = 0; i < creditHead.length; i++) {
				for (int j = 0; j < creditHead[0].length; j++) {
					System.out.println("creditHead:" + creditHead[i][j]);
				}
			}

			request.setAttribute("debitLength", debitHead);
			request.setAttribute("creditLength", creditHead);

			ArrayList<SalaryProcess> creditNames = new ArrayList<SalaryProcess>();
			ArrayList<SalaryProcess> debitNames = new ArrayList<SalaryProcess>();
			for (int i = 0; i < creditHead.length; i++) {
				SalaryProcess creditName = new SalaryProcess();
				creditName.setCreditCode(String.valueOf(creditHead[i][0]));
				creditName.setCreditName(String.valueOf(creditHead[i][1]));
				creditNames.add(creditName);
			}
			bean.setCreditHeader(creditNames);

			for (int i = 0; i < debitHead.length; i++) {
				SalaryProcess debitName = new SalaryProcess();
				debitName.setDebitCode(String.valueOf(debitHead[i][1]));
				debitName.setDebitName(String.valueOf(debitHead[i][1]));
				debitNames.add(debitName);
			}
			bean.setDebitHeader(debitNames);
			doPaging(bean, attnEmpIdSaved.length, attnEmpIdSaved, request,
					"false", Integer.parseInt(recordPerPage));

			int From_TOT = Integer.parseInt(bean.getFromTotRec());
			int To_TOT = Integer.parseInt(bean.getToTotRec());

			HashMap<String, Object[][]> empSavedCreditMap = getSavedSalCreditMap(
					attnCode, year);
			HashMap<String, Object[][]> empSavedDebitMap = getSavedSalDebitMap(
					attnCode, year);

			allEmpRow = new Object[To_TOT - From_TOT][];

			Object[][] salEmpIdSaved = getSalEmployeeToView(attnCode, year);
			int count = 0;
			for (int i = From_TOT; i < To_TOT; i++) {
				String[] tempEmp = new String[9];
				tempEmp[0] = String.valueOf(attnEmpIdSaved[i][0]);// empid
				tempEmp[1] = String.valueOf(attnEmpIdSaved[i][1]);// token
				tempEmp[2] = String.valueOf(attnEmpIdSaved[i][2]);// name
				tempEmp[3] = String.valueOf(attnEmpIdSaved[i][7]);// attnDays
				tempEmp[4] = String.valueOf(attnEmpIdSaved[i][8]);// salDays
				tempEmp[5] = String.valueOf(attnEmpIdSaved[i][9]);// salOnHold
				tempEmp[6] = String.valueOf(dataString[4]);// totalCreditRound
				tempEmp[7] = String.valueOf(dataString[5]);// totalDebitRound
				tempEmp[8] = String.valueOf(dataString[6]);// netPayRound

				Object[][] tempCredit = empSavedCreditMap.get(String
						.valueOf(attnEmpIdSaved[i][0]));
				tempCredit = makeSavedEmpObjSameSize(tempCredit, creditHead,
						String.valueOf(attnEmpIdSaved[i][0]));
				Object[][] tempDebit = empSavedDebitMap.get(String
						.valueOf(attnEmpIdSaved[i][0]));

				String empIdSaved = getEmpSaveStatus(salEmpIdSaved, String
						.valueOf(attnEmpIdSaved[i][0]));

				if (empIdSaved.equals("Y")) {
					Object[][] oneEmpRow = getEmpSalRow(tempEmp, tempCredit,
							tempDebit, creditHead, debitHead);
					allEmpRow[count] = oneEmpRow[0];
					tempCredit = null;
					tempDebit = null;
				} else {
					String[] dataStringNewEmp = new String[18];

					dataStringNewEmp[0] = bean.getMonth();
					dataStringNewEmp[1] = bean.getYear();
					dataStringNewEmp[2] = bean.getLedgerCode();
					dataStringNewEmp[3] = bean.getJoinDaysFlag();
					dataStringNewEmp[4] = bean.getCreditRound();
					dataStringNewEmp[5] = path;
					dataStringNewEmp[6] = bean.getVpfFlag();
					dataStringNewEmp[7] = bean.getLwfFlag();
					dataStringNewEmp[8] = getPrevLedger(bean.getMonth(), bean
							.getYear());
					dataStringNewEmp[9] = bean.getProfHandiFLag();
					dataStringNewEmp[10] = bean.getLwfCreditCode();
					dataStringNewEmp[11] = bean.getLwfDebitCode();
					dataStringNewEmp[12] = bean.getIncomeTaxFlag();
					dataStringNewEmp[13] = bean.getTotalCreditRound();
					dataStringNewEmp[14] = bean.getTotalDebitRound();
					dataStringNewEmp[15] = bean.getNetPayRound();
					dataStringNewEmp[16] = "N"; // salProcess.getRecoveryFlag();
					dataStringNewEmp[17] = "0"; // salProcess.getRecoveryDebitCode();
					allEmpRow[count] = (recalSalary(
							request,
							new String[] { String.valueOf(attnEmpIdSaved[i][0]) },
							"SAL_START", path, bean, dataStringNewEmp,
							listOfFilters))[0];
				}
				count++;
			}
			request.setAttribute("index", count);
		} catch (Exception e) {
			logger.error("Exception in getemployeeDataToView:" + e);
			e.printStackTrace();
			return new Object[1][1];
		}
		return allEmpRow;
	}

	/**
	 *  Purpose : GET CREDIT CODE AND CREDIT ABBR
	 * 
	 * @param String
	 *            'path', where Credit Head XML file has been stored
	 * @return CREDITS Object 'credit_header'
	 */
	public Object[][] getCreditHeader(String path) {
		Object credit_header[][] = null;
		try {
			String query = "SELECT CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD	ORDER BY CREDIT_CODE";
			credit_header = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return credit_header;
	} // end of method getCreditHeader()

	/**
	 *  Purpose : GET CREDIT CODE AND CREDIT ABBR
	 * 
	 * @param String
	 *  'path', where Credit Head XML file has been stored
	 * @return DEBITS Object 'debit_header'
	 */
	public Object[][] getDebitHeader(String path) {
		Object debit_header[][] = null;
		try {
			String query = "SELECT DEBIT_CODE, DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";
			debit_header = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return debit_header;
	} // end of method getDebitHeader()

	/**
	 *  Purpose : GET EMP_ID THOSE FULLFILL SORTING CRITERIA from month attendance table
	 * 
	 * @param NonIndustrialSalary
	 *            object 'nonIndustrialSalary', 'proMonth' process month
	 * @return EMPLOYEE Object 'emp_id[][]' with required info
	 */
	public Object[][] getAttnEmployeeToView(String year, String attnCode,
			String filterQuery) {
		Object emp_id[][] = null;
		try {
			/**
			 * FOR SELECTING THE EMPLOYEE THOSE FULL FILL SORTING CRITERIA FROM
			 * MONTHLY ATTENDANCE AND ALSO GETTING SALARY DAYS AND ONHOLD INFO
			 * FROM SALARY TABLE
			 */
			String salHrsQuery = "NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m'";

			String attHrsQuery = "NVL(ATTN_SAL_DAYS,0)||'d:'||NVL(TO_CHAR(ATTN_SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(ATTN_SAL_HRS,'MI'),'00')||'m'";

			String selectSalary = " SELECT HRMS_EMP_OFFC.EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME,EMP_CENTER,CENTER_LOCATION,nvl(CENTER_PTAX_STATE,15),EMP_TYPE , "
					+ attHrsQuery
					+ ","
					+ salHrsQuery
					+
					// " ,NVL(SAL_ONHOLD,'N'), NVL(EMP_ISHANDICAP,'N')," +
					" ,NVL(SAL_ONHOLD,'N'), NVL(SAL_PTAX_FLAG,'Y'),"
					+ " NVL(SAL_EPF_FLAG,'N'),NVL(SAL_VPF_FLAG,'N'),NVL(SAL_GPF_FLAG,'N'),NVL(SAL_PFTRUST_FLAG,'N'),"
					+
					/*
					 * recovery days removed
					 */
					// "
					// NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),'00:00'),NVL(SAL_LWF_FLAG,'N'),NVL(ATTN_RECOVERY_DAYS,0)
					// "+
					" NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),'00:00'),NVL(SAL_LWF_FLAG,'N'),0 "
					+ " FROM HRMS_MONTH_ATTENDANCE_"
					+ year
					+ " INNER JOIN HRMS_EMP_OFFC  ON HRMS_MONTH_ATTENDANCE_"
					+ year
					+ ".ATTN_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) "
					+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_PTAX_STATE) "
					+ " LEFT JOIN HRMS_EMP_PERS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_PERS.EMP_ID )"
					+ " LEFT JOIN HRMS_SALARY_"
					+ year
					+ " ON (HRMS_SALARY_"
					+ year
					+ ". EMP_ID  = HRMS_MONTH_ATTENDANCE_"
					+ year
					+ ".ATTN_EMP_ID AND SAL_LEDGER_CODE="
					+ attnCode
					+ ")"
					+ " LEFT JOIN HRMS_SALARY_MISC ON ( HRMS_SALARY_MISC.EMP_ID = HRMS_MONTH_ATTENDANCE_"
					+ year
					+ ".ATTN_EMP_ID ) "
					+ " LEFT JOIN HRMS_SHIFT ON  HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID "
					+ " WHERE 1=1 ";
			String where = filterQuery;
			try {

				where = where
						+ " AND ATTN_CODE="
						+ attnCode
						+ " order by UPPER(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";
				selectSalary = selectSalary + where;
			} catch (Exception e) {
				logger
						.error(
								"exception in getting attCode selectSalary in getEmpId()",
								e);
			}

			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			logger.error("exception in getSavedEmployee " + e);
		}
		return emp_id;
	}

	/**
	 *  Purpose : THIS METHOD IS USED TO GET CREDIT CODE AND VALUE OF PROCESSED SALARY OF
	 * EMPLOYEE
	 * 
	 * @return CREDIT AMOUNT OF PROCESSED SALARY 'credit_amount'
	 */
	public HashMap<String, Object[][]> getSavedSalCreditMap(String ledgerCode,
			String year) {
		HashMap<String, Object[][]> creditMap = null;
		try {
			String selectCredits = " SELECT   CREDIT_CODE,NVL(SAL_AMOUNT,0),EMP_ID  FROM HRMS_CREDIT_HEAD "
					+ " LEFT JOIN HRMS_SAL_CREDITS_"
					+ year
					+ " ON (SAL_CREDIT_CODE = CREDIT_CODE AND SAL_LEDGER_CODE='"
					+ ledgerCode
					+ "') "
					+ " WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE ";
			creditMap = getSqlModel().getSingleResultMap(selectCredits, 2, 2);
		} catch (Exception e) {
			logger.error("exception in getSavedSalCreditMap " + e);
		}
		return creditMap;
	}

	/**
	 * Purpose : this method is used to get debit code and value of processed salary of
	 * employee
	 * 
	 * @param year
	 * @return boolean
	 */
	public HashMap<String, Object[][]> getSavedSalDebitMap(String ledgerCode,
			String year) {
		HashMap<String, Object[][]> debitMap = null;
		try {

			String selectDebits = "SELECT  DEBIT_CODE ,NVL(SAL_AMOUNT,0),DEBIT_BALANCE_FLAG,EMP_ID FROM HRMS_DEBIT_HEAD "
					+ "  LEFT JOIN  HRMS_SAL_DEBITS_"
					+ year
					+ " ON (SAL_DEBIT_CODE = DEBIT_CODE AND SAL_LEDGER_CODE='"
					+ ledgerCode
					+ "') WHERE "
					+ " HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' ANd DEBIT_PAYFLAG='Y' ORDER BY EMP_ID,DEBIT_PRIORITY,SAL_DEBIT_CODE ";

			debitMap = getSqlModel().getSingleResultMap(selectDebits, 3, 2);

		} catch (Exception e) {
			logger.error("exception in getSavedSalCreditMap " + e);
		}
		return debitMap;
	}

	/**
	 * Purpose : this method is used to get list of processed records
	 * @param year
	 * @return boolean
	 */
	public void getSalProcessList(SalaryProcess salProcess,
			HttpServletRequest request) {

		try {
			String listQuery = " SELECT DECODE(LEDGER_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ATTN_MONTH, "
					+ " LEDGER_YEAR, TYPE_NAME, PAYBILL_GROUP, DEPT_NAME, CENTER_NAME, DIV_NAME, LEDGER_STATUS, "
					+ " TYPE_ID,PAYBILL_ID,DEPT_ID,CENTER_ID,DIV_ID, LEDGER_CODE,LEDGER_MONTH,NVL(LEDGER_PROCESS_TYPE,'N')   "
					+ " FROM HRMS_SALARY_LEDGER  "
					+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID =  HRMS_SALARY_LEDGER.LEDGER_EMPTYPE) "
					+ " LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_SALARY_LEDGER.LEDGER_PAYBILL) "
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =  HRMS_SALARY_LEDGER.LEDGER_DEPT) "
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =  HRMS_SALARY_LEDGER.LEDGER_BRN) "
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_SALARY_LEDGER.LEDGER_DIVISION) "
					+ " WHERE LEDGER_STATUS IN ('SAL_START','SAL_FINAL','ATTN_UNLOCK','ATTN_READY') ";

			if (salProcess.getUserProfileDivision() != null
					&& salProcess.getUserProfileDivision().length() > 0) {
				listQuery += " AND DIV_ID IN("
						+ salProcess.getUserProfileDivision() + ") ";
			}

			listQuery += " ORDER BY LEDGER_YEAR DESC, LEDGER_MONTH DESC ";

			Object[][] listObj = getSqlModel().getSingleResult(listQuery);

			String[] pageIndex = Utility.doPaging(salProcess.getMyPage(),
					listObj.length, 10);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				salProcess.setMyPage("1");

			ArrayList<Object> list = new ArrayList<Object>();

			if (listObj != null && listObj.length > 0) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {

					SalaryProcess bean1 = new SalaryProcess();

					bean1.setListMonthName(checkNull_Space(String
							.valueOf(listObj[i][0])));
					bean1.setListYear(checkNull_Space(String
							.valueOf(listObj[i][1])));
					bean1.setListEmpTypeName(checkNull_Space(String
							.valueOf(listObj[i][2])));
					bean1.setListPayBillName(checkNull_Space(String
							.valueOf(listObj[i][3])));
					bean1.setListDeptName(checkNull_Space(String
							.valueOf(listObj[i][4])));
					bean1.setListBranchName(checkNull_Space(String
							.valueOf(listObj[i][5])));
					bean1.setListDivName(checkNull_Space(String
							.valueOf(listObj[i][6])));
					bean1.setListLedgerStatus(checkNull_Space(String
							.valueOf(listObj[i][7])));
					bean1.setListEmpTypeId(checkNull_Space(String
							.valueOf(listObj[i][8])));
					bean1.setListPayBillId(checkNull_Space(String
							.valueOf(listObj[i][9])));
					bean1.setListDeptId(checkNull_Space(String
							.valueOf(listObj[i][10])));
					bean1.setListBranchId(checkNull_Space(String
							.valueOf(listObj[i][11])));
					bean1.setListDivId(checkNull_Space(String
							.valueOf(listObj[i][12])));
					bean1.setListLedgerCode(checkNull_Space(String
							.valueOf(listObj[i][13])));
					bean1.setListMonthCode(checkNull_Space(String
							.valueOf(listObj[i][14])));
					bean1.setListUploadSalaryFlag("N"
							+ checkNull_Space(String.valueOf(listObj[i][15])));
					list.add(bean1);
				}
				salProcess.setTotalRecords(String.valueOf(listObj.length));
			} else {
				salProcess.setListLength("false");
			}
			if (list.size() > 0)
				salProcess.setListLength("true");
			else
				salProcess.setListLength("false");

			salProcess.setIteratorList(list);
		} catch (NumberFormatException e) {
			logger.error("Exception in getSalProcessList:" + e);
		}
	}

	/**
	 * Purpose : this method is used to set pagination in jsp page, and process the
	 * Employee Object by setting boundaries depending on number of pages per
	 * page in Payroll settings
	 * 
	 * @param bean,
	 *            empLength, empObj, request, empSearch
	 */
	public void doPaging(SalaryProcess bean, int empLength, Object[][] empObj,
			HttpServletRequest request, String empSearch, int totalRec) {
		try {
			double row1 = 0.0;
			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int searchCount = 0;
			int totalPage = 0;
			String status = "false";

			try {
				row1 = (double) empLength / totalRec;
				java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(row1);
				totalPage = Integer.parseInt(bigDecRow1.setScale(0,
						java.math.BigDecimal.ROUND_UP).toString());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			if (String.valueOf(bean.getHdPage()).equals("null")
					|| String.valueOf(bean.getHdPage()).equals("")) {
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if (toTotRec > empLength) {
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			} else {
				if (empSearch.equals("true")) {
					for (int j = 0; j < empObj.length; j++) {
						// if(String.valueOf(empObj[j][0]).equals(bean.getEmpSearchId()))
						if (false) {
							status = "true";
							break;
						}
					}
					if (status.equals("true")) {
						for (int i = 0; i < empLength; i++) {
							// if(!String.valueOf(empObj[i][0]).equals(bean.getEmpSearchId()))
							if (false) {
								searchCount = i;
							} else {
								searchCount = searchCount + 2;
								break;
							}
						}
						if (searchCount == 0) {
							searchCount = 1;
						}
						try {
							row1 = (double) searchCount / totalRec;
							java.math.BigDecimal value2 = new java.math.BigDecimal(
									row1);
							int pageSearch = Integer.parseInt(value2.setScale(
									0, java.math.BigDecimal.ROUND_UP)
									.toString());
							pageNo = Integer.parseInt(String
									.valueOf(pageSearch));
						} catch (RuntimeException e) {
							logger.error(e.getMessage());
						}
					} else {
						pageNo = Integer.parseInt(bean.getHdPage());
					}
					if (pageNo == 1) {
						fromTotRec = 0;
						toTotRec = totalRec;
					} else {
						toTotRec = toTotRec * pageNo;
						fromTotRec = (toTotRec - totalRec);
					}
					if (toTotRec > empLength) {
						toTotRec = empLength;
					}
				} else {
					pageNo = Integer.parseInt(bean.getHdPage());

					if (pageNo == 1) {
						fromTotRec = 0;
						toTotRec = totalRec;
					} else {
						toTotRec = toTotRec * pageNo;
						fromTotRec = toTotRec - totalRec;
					}
					if (toTotRec > empLength) {
						toTotRec = empLength;
					}
				}
			}
			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("PageNo", pageNo);
			request.setAttribute("To_TOT", toTotRec);
			request.setAttribute("From_TOT", fromTotRec);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	} // end of method doPaging()

	/**
	 * Purpose : this method is used to get employee salary
	 * @param ledgerCode
	 * @param year
	 * @return object
	 */
	public Object[][] getSalEmployeeToView(String ledgerCode, String year) {
		Object[][] emp_id = null;
		try {
			// this query will return already saved employees in salary table
			// for the specified month
			String query = "Select emp_id from hrms_salary_" + year
					+ " where sal_ledger_code=" + ledgerCode;
			emp_id = getSqlModel().getSingleResult(query);

		} catch (Exception e) {
			logger.error("Exception in getSalEmployeeToView:" + e);
		}
		return emp_id;
	}// end of method getEmpIdSave()

	/**
	 * Purpose : this method is used to get employee row salary
	 * @param dataString
	 * @param empCredit
	 * @param empDebit
	 * @param creditLength
	 * @param debitLength
	 * @return object
	 */
	public Object[][] getEmpSalRow(String[] dataString, Object[][] empCredit,
			Object[][] empDebit, Object[][] creditLength, Object[][] debitLength) {
		Object[][] returnData = null;
		try {
			String emp_id = dataString[0];
			String emp_token = dataString[1];
			String emp_name = dataString[2];
			String attnDays = dataString[3];
			String salDays = dataString[4];
			String salOnHold = dataString[5];
			String totalCreditRound = dataString[6];
			String totalDebitRound = dataString[7];
			String netPayRound = dataString[8];

			double totalCredit = 0;
			double totalDebit = 0;
			double netPay = 0;
			int total_coulum = creditLength.length + debitLength.length + 9;
			returnData = new Object[1][total_coulum];
			try {
				returnData[0][0] = emp_id;
				returnData[0][1] = emp_token;
				returnData[0][2] = emp_name;
			} catch (Exception e) {
				logger.info("exception in getEmpSalRow setting empData" + e);
			}
			int k = 0;
			int c = 0;
			/**
			 * for loop is decrement by 5 because we already set the emp_id ,
			 * emp_token and emp_name in row and after the loop completion three
			 * more field has add like total credit , total debit and net pay
			 */
			for (int j = 0; j < total_coulum - 8; j++) {
				if (j < creditLength.length) {
					/**
					 * THIS LOOP WILL RUN FOR THE NO OF CREDIT LENGTH
					 */
					try {
						returnData[0][j + 3] = "0";
						try {
							/**
							 * filter the null amount if if credit amount is
							 * null it will treated as zero
							 */
							if (empCredit[c][1] != null)
								returnData[0][j + 3] = Utility
										.twoDecimals(String
												.valueOf((empCredit[c][1])));
							// ADD THE CREDIT INTO TOTAL CREDIT VARIABLE
							totalCredit = Math.round((totalCredit + Double
									.parseDouble(String
											.valueOf(returnData[0][j + 3])))
									* Math.pow(10, 2))
									/ Math.pow(10, 2);
						} catch (Exception e) {
							returnData[0][j + 3] = "0";
						}
						c++;
					} catch (Exception e) {
						logger
								.info("Exception in getEmpSalRow in  credit "
										+ e);
					}
				} else if (j == creditLength.length) {
					/**
					 * this is the palace where total credit will take place
					 * after all credit
					 */
					totalCredit = roundCheck(
							Integer.parseInt(totalCreditRound), totalCredit);
					returnData[0][j + 3] = Utility.twoDecimals(totalCredit);
				} else if (j > creditLength.length) {
					// FOR INSERTING DEBIT BECOZ AFTER CREDIT DEBIT TAKE PLACE
					returnData[0][j + 3] = "0.00";
					try {
						if (empDebit[k][1] == null) {
							{
								// if debit amount is null set amount to zero
								empDebit[k][1] = 0.00;
							}
						}
						returnData[0][j + 3] = Utility.twoDecimals(String
								.valueOf(empDebit[k][1]));
					} catch (Exception e) {
						logger.info("Exception in getEmpSalRow in debit " + e);
					}
					// add debit amount into total debit
					try {
						totalDebit = Math.round((totalDebit + Double
								.parseDouble(String.valueOf(empDebit[k][1])))
								* Math.pow(10, 2))
								/ Math.pow(10, 2);

					} catch (Exception e) {
						logger.info("Exception in getEmpSalRow in total debit "
								+ e);
					}
					k = k + 1;
				}
				totalDebit = roundCheck(Integer.parseInt(totalDebitRound),
						totalDebit);
				netPay = Math.round((totalCredit - totalDebit)
						* Math.pow(10, 2))
						/ Math.pow(10, 2);
				netPay = roundCheck(Integer.parseInt(netPayRound), netPay);
				returnData[0][j + 4] = Utility.twoDecimals(String
						.valueOf(totalDebit));
				returnData[0][j + 5] = Utility.twoDecimals(String
						.valueOf(netPay));
				if (totalDebit > totalCredit) {
					returnData[0][j + 4] = Utility.twoDecimals(String
							.valueOf(totalDebit));
					returnData[0][j + 5] = String.valueOf(0.00);
				}
				returnData[0][j + 6] = formatSalDays(salDays);
				returnData[0][j + 7] = salOnHold;
				returnData[0][j + 8] = formatSalDays(attnDays);
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpSalRow:" + e);
		}
		return returnData;
	}

	/**
	 * this method is used to check whether employee Salary is already processed
	 * or not
	 * @param empIdSave
	 * @param empId
	 * @return status - 'Y'/'N'
	 */
	public String getEmpSaveStatus(Object[][] salEmpIdSavd, String empId) {
		String status = "N";
		try {
			if (salEmpIdSavd != null) {
				if (salEmpIdSavd.length > 0) {
					for (int i = 0; i < salEmpIdSavd.length; i++) {
						if (String.valueOf(salEmpIdSavd[i][0]).trim().equals(
								empId.trim())) {
							status = "Y";
						}
					} // end of for loop
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpSaveStatus:" + e);
		}
		return status;
	} // end of method getStatusSave()

	/**
	 * Purpose : this method is used to get ledger code
	 * @param bean
	 * @param ledgerStatus
	 * @return object
	 */
	public Object[][] getLedgerCode(SalaryProcess bean, String ledgerStatus) {
		Object ledger_code[][] = null;
		try {

			/*
			 * this query is used to get ledger code from hrms_salary_ledger
			 * table by applying filters. Retrived ledger code will be send to
			 * getsalary() method
			 */
			String attQuery = "SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where LEDGER_MONTH="
					+ bean.getMonth()
					+ " and LEDGER_YEAR="
					+ bean.getYear()
					+ " " + " AND LEDGER_STATUS='" + ledgerStatus + "'";
			// " and EMP_TYPE="+nonIndustrialSalary.getTypeCode()+" and
			// PAYBILL_ID="+nonIndustrialSalary.getPayBillNo();
			if (!bean.getBranchId().equals("")) {
				attQuery = attQuery + " AND LEDGER_BRN=" + bean.getBranchId();
			}
			if (!bean.getEmployeeTypeId().equals("")) {
				attQuery = attQuery + " AND LEDGER_EMPTYPE="
						+ bean.getEmployeeTypeId();
			}
			if (!bean.getPayBillId().equals("")) {
				attQuery = attQuery + " AND LEDGER_PAYBILL="
						+ bean.getPayBillId();
			}
			if (!bean.getDepartmentId().equals("")) {
				attQuery = attQuery + " AND LEDGER_DEPT="
						+ bean.getDepartmentId();
			}
			if (!bean.getDivisionId().equals("")) {
				attQuery = attQuery + " AND LEDGER_DIVISION="
						+ bean.getDivisionId();
			}

			ledger_code = getSqlModel().getSingleResult(attQuery);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ledger_code;

	} // end of method getLedgerCode()

	/**
	 * Purpose : this method is used to recalculate salary
	 * @param request
	 * @param recal_emp
	 * @param ledgerStatus
	 * @param path
	 * @param bean
	 * @param dataString
	 * @param listOfFilters
	 * @return object
	 */
	public Object[][] recalSalary(HttpServletRequest request,
			String[] recal_emp, String ledgerStatus, String path,
			SalaryProcess bean, String[] dataString, String[] listOfFilters) {

		String month = bean.getMonth();
		String year = bean.getYear();
		try {
			Object credit_header[][] = getCreditHeader(path);
			Object debit_header[][] = getDebitHeader(path);
			request.setAttribute("debitLength", debit_header);
			request.setAttribute("creditLength", credit_header);

			Object[][] esi_data = getESIData(path, month, year);

			/*
			 * String comLedgerCode =
			 * prevLedger(bean.getMonth(),bean.getYear());
			 * bean.setComLedgerCode(comLedgerCode);
			 */
			Object[][] attCode = getLedgerCode(bean, ledgerStatus);
			if (attCode != null) {
				if (attCode.length > 0) {
					bean.setLedgerCode(attCode[0][0].toString());
				}
			}

			String filterQuery = setEmpFiletrs(listOfFilters);
			String empIdString = "0";

			Object emp_id[][] = getAttnEmployeeToView(year, bean
					.getLedgerCode(), filterQuery);
			if (recal_emp == null) {
				return null;
			}
			Object[][] recalEmpObj = new Object[recal_emp.length][emp_id[0].length];
			int rowCnt = 0;
			if (emp_id!=null && emp_id.length > 0) {
				// this loop is used to set employee details who are selected
				// for recalculation
				for (int i = 0; i < emp_id.length; i++) {
					empIdString+=""+String.valueOf(emp_id[i][0]);
				}
				
				try {
					for (int i = 0; i < recalEmpObj.length; i++) {
						for (int j = 0; j < emp_id.length; j++) {
							if (String.valueOf(recal_emp[i]).equals(
									String.valueOf(emp_id[j][0]))) {
								recalEmpObj[rowCnt][0] = emp_id[j][0]; // emp_id
								recalEmpObj[rowCnt][1] = emp_id[j][1]; // emp_token
								recalEmpObj[rowCnt][2] = emp_id[j][2]; // emp_name
								recalEmpObj[rowCnt][3] = emp_id[j][3]; // emp_center
								recalEmpObj[rowCnt][4] = emp_id[j][4]; // center_location
								recalEmpObj[rowCnt][5] = emp_id[j][5]; // ptax state
								recalEmpObj[rowCnt][6] = emp_id[j][6]; // emp type
								recalEmpObj[rowCnt][7] = emp_id[j][7]; // attn DAYS
								recalEmpObj[rowCnt][8] = emp_id[j][8];// saldays
								recalEmpObj[rowCnt][9] = emp_id[j][9];// onhold
								recalEmpObj[rowCnt][10] = emp_id[j][10];// PTAX
								recalEmpObj[rowCnt][11] = emp_id[j][11];// SALARY EPF FLAG
								recalEmpObj[rowCnt][12] = emp_id[j][12];// SALARY VPF FLAG
								recalEmpObj[rowCnt][13] = emp_id[j][13];// SALARY GPF FLAG
								recalEmpObj[rowCnt][14] = emp_id[j][14];// SALARY FLAG
								recalEmpObj[rowCnt][15] = emp_id[j][15];// shifthrs
								recalEmpObj[rowCnt][16] = emp_id[j][16];// //lwfApplicability

								rowCnt++;
							}
						} // end of innder for loop
					} // end of outer for loop
				} catch (RuntimeException e) {
					logger.error(e.getMessage());
				}
			}

			Object[][] resing_empid = getResignEmp();
			ArrayList<SalaryProcess> creditNames = new ArrayList<SalaryProcess>();
			ArrayList<SalaryProcess> debitNames = new ArrayList<SalaryProcess>();
			request.setAttribute("resignEmp", resing_empid);
			for (int i = 0; i < credit_header.length; i++) {
				/**
				 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER
				 * THIS LOOP IS USED
				 */
				SalaryProcess creditName = new SalaryProcess();
				creditName.setCreditCode(String.valueOf(credit_header[i][0]));
				creditName.setCreditName(String.valueOf(credit_header[i][1]));
				creditNames.add(creditName);
			}

			bean.setCreditHeader(creditNames);

			for (int i = 0; i < debit_header.length; i++) {
				/**
				 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
				 */
				SalaryProcess debitName = new SalaryProcess();
				debitName.setDebitCode(String.valueOf(debit_header[i][0]));
				debitName.setDebitName(String.valueOf(debit_header[i][1]));
				debitNames.add(debitName);
			}
			bean.setDebitHeader(debitNames);

			int r = recalEmpObj.length;
			Object[][] rows = new Object[r][];

			Object[][] vpf_configData = null;
			Object[][] incomeTax_configData = null;
			HashMap<String, Object[][]> lwfMap = null;
			HashMap<String, Object[][]> loanMap = getEmpLoanMap(String
					.valueOf(dataString[0]), String.valueOf(dataString[1]));
			HashMap<String, Object[][]> licMap = getEmpLicMap();
			ArrayList<Object[]> lwfCodeList = null;
			// parameter -- path,month,year
			Object[][] pf_configData = getPFData(String.valueOf(dataString[5]),
					String.valueOf(dataString[0]), String
							.valueOf(dataString[1]));
			Object[][] pfTrust_configData = getPFTrustData();
			// parameter -- path,month,year
			Object[][] esi_configData = getESIData(String
					.valueOf(dataString[5]), String.valueOf(dataString[0]),
					String.valueOf(dataString[1]));
			// vpfFlag check
			if (String.valueOf(dataString[6]).equals("Y"))
				vpf_configData = getVPFConfig();
			// lwfFlag check

			// parameter -- month,year
			lwfCodeList = getLWFCodes(String.valueOf(dataString[0]), String
					.valueOf(dataString[1]));
			lwfMap = getLWFSlabs(lwfCodeList);
			HashMap<String, Object[][]> lwfCreditApplicableMap = getLWFCreditApplicable();
			HashMap<String, Object[][]> lwfApplicableEmpGradeMap = getLWFNotApplicableEmpGrade();
			HashMap<String, Object[][]> lwfApplicableEmpDesgMap = getLWFNotApplicableEmpDesg();
			if (String.valueOf(dataString[12]).equals("Y")) {
				// parameter -- month,year
				incomeTax_configData = getIncomeTaxConfigObject(String
						.valueOf(dataString[0]), String.valueOf(dataString[1]));
			}
			HashMap<String, Object[][]> empCreditConfigMap = new HashMap<String, Object[][]>();
			HashMap<String, Object[][]> empDebitConfigMap = new HashMap<String, Object[][]>();
			// HashMap<String, Object[][]> empMonthCreditMap
			// =getEmpCreditMap(listOfFilters);
			// HashMap<String, Object[][]> empMonthDebitMap = new
			// HashMap<String, Object[][]> ();
			HashMap<String, Object[][]> empTotalMap = new HashMap<String, Object[][]>();

			/**
			 * LOAD SALARY ZONE,SALARYZONEBRANCH,ESI PARAMETER, PF PARAMETER,
			 * PTAX PARAMETER
			 */
			loadPayrollSetting();

			/*
			 * LOAD EMPLOYEE MISC UPLOAD
			 */
			HashMap<String, Object[][]> miscSalaryMap = getSalMiscUpload(String
					.valueOf(dataString[0]), String.valueOf(dataString[1]));

			empCreditConfigMap = getEmpCreditMap(empIdString);
			Object[][] creditHeadObj = getCreditCodes();
			Object[][] debitHeadObj = getDebitCodes();
			String[] generalData = new String[15];
			NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();
			nonModel.initiate(context, session);
			if (debitHeadObj != null && debitHeadObj.length > 0) {
				generalData[0] = String.valueOf(dataString[5]);// path
				generalData[1] = String.valueOf(dataString[0]);// month
				generalData[2] = String.valueOf(dataString[1]);// year
				generalData[3] = nonModel.prevLedger(bean.getMonth(), bean
						.getYear(), esi_configData);// comLedgerCode
				generalData[4] = String.valueOf(dataString[9]);// PTAXhandicapFlag
				generalData[5] = String.valueOf(dataString[7]);// lwfApplicability
				generalData[6] = String.valueOf(dataString[10]);// lwfCreditHead
				generalData[7] = String.valueOf(dataString[11]);// lwfDebitHead
				generalData[8] = String.valueOf(dataString[12]);// incomeTaxFlag
				generalData[9] = String.valueOf(dataString[13]);// creditTotalRound
				generalData[10] = String.valueOf(dataString[14]);// debitTotalRound
				generalData[11] = String.valueOf(dataString[15]);// netPayRound
				generalData[12] = String.valueOf(dataString[16]);// recoveryFlag
				generalData[13] = String.valueOf(dataString[17]);// recovery
				// generalData [14] = String.valueOf(dataString[18]); //
				// Division-wise ESIC flag

				// get the employee debits from employee debitt configuration
				empDebitConfigMap = getEmpDebitMap(empIdString);

			}
			Object perfConfigData[][] = getPerformanceConfig();
			boolean ratingFlag = false;
			HashMap<String, String> empRatingMap = null;
			HashMap<String, String> empCTCMap = null;
			if (perfConfigData != null && perfConfigData.length > 0) {
				empRatingMap = getEmpRatingData(empIdString, String
						.valueOf(dataString[0]), String.valueOf(dataString[1]));
				empCTCMap = getEmpCTCData(empIdString);
				ratingFlag = true;
			}
			nonModel.terminate();
			for (int i = 0; i < recalEmpObj.length; i++) {
				/**
				 * getRow METHOD RETURN A COMLETE ROW FOR A EMPLOYEE AND SET
				 * EACH ROW IN A 2 DIMENSIONSION ARRARY THAT ROWS LENNGTH IS
				 * EQUAL TO TOTAL NO OF EMPLPYEE
				 */
				try {

					Object[][] tempCreditObj = makeEmpObjSameSize(
							empCreditConfigMap.get(String
									.valueOf(recalEmpObj[i][0])),
							creditHeadObj, String.valueOf(recalEmpObj[i][0]));
					// not used now...
					// Object [][] tempCreditObj =
					// empCreditConfigMap.get(String.valueOf(empObj[i][0]));
					String empMonthRating = "";
					String empCTC = "";
					String[] empRatingData = new String[2];

					try {
						if (ratingFlag) {
							empMonthRating = String.valueOf(empRatingMap
									.get(String.valueOf(recalEmpObj[i][0])));
							empCTC = String.valueOf(empCTCMap.get(String
									.valueOf(recalEmpObj[i][0])));
							empRatingData[0] = empMonthRating; // emp rating
							empRatingData[1] = String.valueOf(Double
									.parseDouble(empCTC) / 12); // monthly CTC
						}
					} catch (Exception e) {
						empMonthRating = "";
					}
					Object[][] tempSalCredit = getMonthlyEmpCredit(
							tempCreditObj, String.valueOf(recalEmpObj[i][7]), // attendance days
							String.valueOf(recalEmpObj[i][15]),// shift hours
							String.valueOf(dataString[0]),// month
							String.valueOf(dataString[1]),// year
							String.valueOf(dataString[3]),// joiningDaysFlag
							String.valueOf(dataString[4]),// creditRound
							empRatingData, perfConfigData); // monthly rating

					/**
					 * ADD OR APPEND MISC SALARY UPLOAD
					 */

					tempSalCredit = addAppendMiscSalary(tempSalCredit,
							miscSalaryMap, String.valueOf(recalEmpObj[i][0]));

					Object[][] tempGrossCreditObj = empCreditConfigMap
							.get(String.valueOf(recalEmpObj[i][0]));
					Object[][] tempDebitObj = makeEmpObjSameSizeDebit(
							empDebitConfigMap.get(String
									.valueOf(recalEmpObj[i][0])), debitHeadObj,
							String.valueOf(recalEmpObj[i][0]));
					Object[][] empLoanObj = loanMap.get(String
							.valueOf(recalEmpObj[i][0]));
					Object[][] empLicObj = licMap.get(String
							.valueOf(recalEmpObj[i][0]));
					// not used now...
					// Object [][] tempDebitObj =
					// empDebitConfigMap.get(String.valueOf(recalEmpObj[i][0]));
					String[] empData = new String[14];
					empData[0] = String.valueOf(recalEmpObj[i][11]);// epfApplicability
					empData[1] = String.valueOf(recalEmpObj[i][6]);// empTypeId
					empData[2] = String.valueOf(recalEmpObj[i][3]);// branchId
					empData[3] = String.valueOf(recalEmpObj[i][14]);// pftrustApplicability
					empData[4] = String.valueOf(recalEmpObj[i][12]);// vpfApplicability
					empData[5] = String.valueOf(recalEmpObj[i][0]);// empid
					empData[6] = String.valueOf(recalEmpObj[i][10]);// PTAX flag
					empData[7] = String.valueOf(recalEmpObj[i][5]);// location
					empData[8] = String.valueOf(recalEmpObj[i][16]);// empLwfApplicability
					empData[9] = String.valueOf(recalEmpObj[i][17]);// recoverydays
					empData[10] = String.valueOf(recalEmpObj[i][8]);// salDays
					empData[11] = String.valueOf(recalEmpObj[i][17]);// DIV
					empData[12] = String.valueOf(recalEmpObj[i][18]);// DESG
					empData[13] = String.valueOf(recalEmpObj[i][19]);// GRADE

					ArrayList dataList = getMonthlyEmpDebit(tempDebitObj,
							tempSalCredit, empData, tempGrossCreditObj,
							pf_configData, pfTrust_configData, vpf_configData,
							esi_data, lwfCodeList, lwfMap,
							incomeTax_configData, empLoanObj, empLicObj,
							generalData, miscSalaryMap, String
									.valueOf(recalEmpObj[i][0]),
							lwfCreditApplicableMap, lwfApplicableEmpGradeMap,
							lwfApplicableEmpDesgMap);

					Object[][] tempSalDebit = (Object[][]) dataList.get(0);
					Object[][] totalCreditObj = (Object[][]) dataList.get(1);
					Object[][] totalDebitObj = (Object[][]) dataList.get(2);
					Object[][] netPayObj = (Object[][]) dataList.get(3);

					// empMonthDebitMap.put(String.valueOf(recalEmpObj[i][0]),
					// tempSalDebit);
					Object[][] totalObj = new Object[1][3];
					totalObj[0][0] = String.valueOf(totalCreditObj[0][0]);
					totalObj[0][1] = String.valueOf(totalDebitObj[0][0]);
					totalObj[0][2] = String.valueOf(netPayObj[0][0]);

					empTotalMap
							.put(String.valueOf(recalEmpObj[i][0]), totalObj);

					tempGrossCreditObj = null;
					tempDebitObj = null;
					empLoanObj = null;
					empLicObj = null;
					// tempSalDebit = null;
					totalCreditObj = null;
					totalDebitObj = null;
					totalObj = null;

					double totalCredit = 0;
					double totalDebit = 0;
					double netPay = 0;
					/*
					 * double creditamt = 0; int lenDebit = 0; int lopDays = 0;
					 * int totalDays = 30;
					 */

					int total_coulum = creditHeadObj.length
							+ debitHeadObj.length + 9;
					Object[][] total_amount = new Object[1][total_coulum];

					total_amount[0][0] = String.valueOf(recalEmpObj[i][0]);
					total_amount[0][1] = String.valueOf(recalEmpObj[i][1]);
					total_amount[0][2] = String.valueOf(recalEmpObj[i][2]);

					int k = 0;
					int c = 0;

					for (int j = 0; j < total_coulum - 8; j++) {
						if (j < creditHeadObj.length) {
							/**
							 * THIS LOOP WILL RUN FOR THE NO OF CREDIT LENGTH
							 */
							try {

								total_amount[0][j + 3] = "0";
								try {
									/**
									 * filter the null amount if if credit
									 * amount is null it will treated as zero
									 */
									if (tempSalCredit[c][1] != null)
										total_amount[0][j + 3] = Utility
												.twoDecimals(String
														.valueOf((tempSalCredit[c][1])));

									// ADD THE CREDIT INTO TOTAL CREDIT VARIABLE
									totalCredit = Math
											.round((totalCredit + Double
													.parseDouble(String
															.valueOf(total_amount[0][j + 3])))
													* Math.pow(10, 2))
											/ Math.pow(10, 2);

								} catch (Exception e) {

								}
								c++;
							} catch (Exception e) {
								logger
										.info("Error in if  loop which is credit ");
								logger.error(e.getMessage());
							}
						} else if (j == creditHeadObj.length) {
							/**
							 * this is the palace where total credit will take
							 * place after all credit
							 */
							totalCredit = roundCheck(Integer.parseInt(bean
									.getTotalCreditRound()), totalCredit);
							total_amount[0][j + 3] = Utility
									.twoDecimals(totalCredit);

						} else if (j > creditHeadObj.length) {

							// FOR INSERTING DEBIT BECOZ AFTER CREDIT DEBIT TAKE
							// PLACE
							total_amount[0][j + 3] = "0.00";
							try {
								if (tempSalDebit[k][1] == null) {
									{
										// if debit amount is null set amount to
										// zero
										tempSalDebit[k][1] = 0.00;
									}
								}

								total_amount[0][j + 3] = Utility
										.twoDecimals(String
												.valueOf(tempSalDebit[k][1]));

							} catch (Exception e) {
								logger.error(e.getMessage());
							}
							// add debit amount into total debit
							try {
								totalDebit = Math.round((totalDebit + Double
										.parseDouble(String
												.valueOf(tempSalDebit[k][1])))
										* Math.pow(10, 2))
										/ Math.pow(10, 2);

							} catch (Exception e) {
								logger
										.error("exception in  total debit cal",
												e);
							}
							k = k + 1;
						}
						totalDebit = roundCheck(Integer.parseInt(bean
								.getTotalDebitRound()), totalDebit);
						netPay = Math.round((totalCredit - totalDebit)
								* Math.pow(10, 2))
								/ Math.pow(10, 2);
						netPay = roundCheck(Integer.parseInt(bean
								.getNetPayRound()), netPay);
						total_amount[0][j + 4] = Utility.twoDecimals(String
								.valueOf(totalDebit));
						total_amount[0][j + 5] = Utility.twoDecimals(String
								.valueOf(netPay));
						if (totalDebit > totalCredit) {
							total_amount[0][j + 4] = Utility.twoDecimals(String
									.valueOf(totalDebit));
							total_amount[0][j + 5] = String.valueOf(0.00);
						}
						total_amount[0][j + 6] = getViewDays(String
								.valueOf(recalEmpObj[i][7])); // sal days
						total_amount[0][j + 7] = String
								.valueOf(recalEmpObj[i][9]); // sal onhold
						total_amount[0][j + 8] = getViewDays(String
								.valueOf(recalEmpObj[i][7])); // att days //
					}

					rows[i] = total_amount[0];
				} catch (RuntimeException e) {
					logger.error(e.getMessage());
				}
			}

			return rows;
		} catch (Exception e) {
			logger.error("Exception in recalSalary()", e);
			return null;
		}
	} // end of method recalSalary()

	/**
	 * Purpose : this method is used to save on hold employee
	 * @param emp_id
	 * @param year
	 * @param attCode
	 * @return boolean
	 */
	public boolean saveOnHold(String emp_id[], String year, String attCode) {
		boolean result = false;
		try {
			// this query is used to update Onhold flag to 'Y'(Yes) for selected
			// employees for the ledger code.
			String updateEmpData = " UPDATE HRMS_SALARY_" + year
					+ " SET SAL_ONHOLD ='Y'"
					+ " WHERE EMP_ID =? AND SAL_LEDGER_CODE =" + attCode + " ";

			Object[][] insertData = new Object[emp_id.length][1];

			for (int i = 0; i < emp_id.length; i++) {
				insertData[i][0] = emp_id[i];
			}
			result = getSqlModel().singleExecute(updateEmpData, insertData);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	} // end of method saveOnHold()

	/**
	 * Purpose : this method is used to remove on hold employee
	 * @param emp_id
	 * @param year
	 * @param attCode
	 * @return boolean
	 */
	public boolean removeOnHold(String emp_id[], String year, String attCode) {
		boolean result = false;
		try {
			// this query is used to update Onhold flag to 'N'(No) for selected
			// employees for the ledger code.
			String updateEmpData = " UPDATE HRMS_SALARY_"
					+ year
					+ " SET SAL_ONHOLD ='N' WHERE EMP_ID =? AND SAL_LEDGER_CODE ="
					+ attCode + " ";
			Object[][] insertData = new Object[emp_id.length][1];
			for (int i = 0; i < emp_id.length; i++) {
				insertData[i][0] = emp_id[i];
			}
			result = getSqlModel().singleExecute(updateEmpData, insertData);
		} catch (Exception e) {
			logger.error("Exception in removeOnHold:" + e);
		}
		return result;
	} // end of method removeOnHold()

	/**
	 * Purpose : this method is used to download the template
	 * @param bean
	 * @param response
	 */
	public void downloadTemplate(SalaryProcess bean,
			HttpServletResponse response) {

		String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME "
				+ ",NVL('',0),CENTER_NAME,DEPT_NAME,EMP_ID FROM HRMS_EMP_OFFC  "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ "INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " WHERE EMP_ID IN (SELECT ATTN_EMP_ID FROM HRMS_MONTH_ATTENDANCE_"
				+ bean.getYear()
				+ " WHERE ATTN_CODE ="
				+ bean.getLedgerCode()
				+ ")"
				+ "  ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
		Object empList[][] = getSqlModel().getSingleResult(query);

		try {
			String title = "Employee List";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Xls", title);
			String colName[] = { "Employee Code", "Employee Name", "Amount",
					"Branch", "Department" };
			int cellwidth[] = { 10, 30, 10, 15, 35 };
			int alignment[] = { 0, 0, 0, 0, 0 };
			rg.tableBodyPayDown(colName, empList, cellwidth, alignment);
			rg.createReport(response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Purpose : this method is used to upload credits
	 * @param salProcess
	 * @param empObjFrmFile
	 * @param path
	 * @return string
	 */
	public String updCredits(SalaryProcess salProcess,
			Object[][] empObjFrmFile, String path) {

		try {
			String empQuery = "select emp_id,emp_token from hrms_emp_offc ";
			ArrayList addList = new ArrayList();
			Object[][] empObj = getSqlModel().getSingleResult(empQuery);

			for (int i = 0; i < empObj.length; i++) {

				try {
					for (int j = 0; j < empObjFrmFile.length; j++) {
						if (String.valueOf(empObj[i][1]).trim().equals(
								String.valueOf(empObjFrmFile[j][1]).trim())) {
							if (String.valueOf(empObj[i][0]).equals("null")
									|| String.valueOf(empObj[i][0])
											.equals(null)
									|| String.valueOf(empObj[i][0]).trim()
											.equals("")) {
								addList.add("0");
								addList
										.add(String
												.valueOf(empObjFrmFile[j][0]));
								addList.add("0");
								break;
							} else {
								addList.add(String.valueOf(empObj[i][0]));
								addList
										.add(String
												.valueOf(empObjFrmFile[j][0]));
								addList
										.add(String
												.valueOf(empObjFrmFile[j][2]));
								break;
							}
						}

					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}

			Object[][] finalObj = new Object[addList.size() / 3][3];
			int index = 0;
			try {
				for (int i = 0; i < addList.size(); i++) {
					finalObj[index][1] = addList.get(i++);
					// i++;
					finalObj[index][0] = addList.get(i++);
					finalObj[index][2] = addList.get(i);
					index++;
				}
			} catch (RuntimeException e) {
				logger.error(e.getMessage());
			}
			String ledgerCode = salProcess.getLedgerCode();

			boolean result = false;
			String empIdString = "";
			Object[][] deleteCreditObj = new Object[finalObj.length][2];
			String deleteCreditQuery = "DELETE FROM HRMS_SAL_CREDITS_"
					+ salProcess.getYear()
					+ " WHERE EMP_ID=? AND SAL_LEDGER_CODE IN("
					+ salProcess.getLedgerCode() + ") "
					+ " AND SAL_CREDIT_CODE=?";
			for (int i = 0; i < deleteCreditObj.length; i++) {
				deleteCreditObj[i][0] = finalObj[i][1];
				deleteCreditObj[i][1] = finalObj[i][2];
			}
			if (getSqlModel().singleExecute(deleteCreditQuery, deleteCreditObj)) {
				try {
					String insertCreditQuery = "INSERT INTO  HRMS_SAL_CREDITS_"
							+ salProcess.getYear()
							+ " (SAL_AMOUNT,EMP_ID,SAL_LEDGER_CODE,SAL_CREDIT_CODE) VALUES(?,?,"
							+ ledgerCode + ",?) ";

					result = getSqlModel().singleExecute(insertCreditQuery,
							finalObj);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}

			try {
				logger.info("finalObj.length" + finalObj.length);
				Object[] empIdObj = new Object[finalObj.length];
				for (int i = 0; i < finalObj.length; i++) {
					empIdString = empIdString + String.valueOf(finalObj[i][1])
							+ ",";
					empIdObj[i] = String.valueOf(finalObj[i][1]);
				}
				empIdString = empIdString
						.substring(0, empIdString.length() - 1);
				logger.info("empIdString " + empIdString);

				if (result) {
					UploadCreditModel uploadModel = new UploadCreditModel();
					uploadModel.initiate(context, session);
					HashMap creditConfDataMap = uploadModel
							.getCreditConfMap(empIdString);
					HashMap creditDataMap = uploadModel.getCreditMap(
							ledgerCode, salProcess.getYear(), empIdString);
					String typeBranch = "SELECT EMP_TYPE,EMP_CENTER,CENTER_LOCATION,nvl(CENTER_PTAX_STATE,15),NVL(SAL_PTAX_FLAG,'Y'),HRMS_EMP_OFFC.EMP_ID,NVL(SAL_EPF_FLAG,'N') from hrms_emp_offc "
							+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
							+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) "
							+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_PTAX_STATE) "
							+ "WHERE 1=1 "
							+ Utility.getConcatenatedIds(
									"HRMS_EMP_OFFC.EMP_ID", empIdString) + "";

					HashMap empSalDetailsMap = getSqlModel()
							.getSingleResultMap(typeBranch, 5, 2);
					HashMap debitRoundMap = new HashMap();
					String roundQuery = "SELECT NVL(DEBIT_ROUND,0),DEBIT_CODE FROM HRMS_DEBIT_HEAD ";

					Object[][] roundObj = getSqlModel().getSingleResult(
							roundQuery);
					for (int i = 0; i < roundObj.length; i++) {
						debitRoundMap.put(String.valueOf(roundObj[i][1]),
								String.valueOf(roundObj[i][0]));
					}

					NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();
					nonModel.initiate(context, session);

					String month = salProcess.getMonth();
					String year = salProcess.getYear();

					if (creditDataMap != null) {
						Object[][] credit_amount = null;
						Object[][] gross_amount = null;
						Object[][] debitObject = new Object[finalObj.length * 3][3];
						double pf_amount = 0, esi_amount = 0, ptax_amount = 0;
						int debitObjCOunt = 0;

						for (int i = 0; i < finalObj.length; i++) {
							try {
								credit_amount = (Object[][]) creditDataMap
										.get(String.valueOf(empIdObj[i]));

								String[] dataString = new String[6];
								String empType = "";
								String empBranch = "";
								String empBranchLocation = "";
								String empPTAXState = "";
								String empPTAXFlag = "";
								String empPFFlag = "";
								Object[][] typeBranchObj = (Object[][]) empSalDetailsMap
										.get(String.valueOf(empIdObj[i]));
								try {
									empType = String
											.valueOf(typeBranchObj[0][0]);
									empBranch = String
											.valueOf(typeBranchObj[0][1]);
									empBranchLocation = String
											.valueOf(typeBranchObj[0][2]);
									empPTAXState = String
											.valueOf(typeBranchObj[0][3]);
									empPTAXFlag = String
											.valueOf(typeBranchObj[0][4]);
									empPFFlag = String
											.valueOf(typeBranchObj[0][6]);
								} catch (Exception e) {
									// TODO: handle exception
								}
								dataString[0] = empType;
								dataString[1] = empBranch;
								dataString[2] = path;
								dataString[3] = salProcess.getMonth();
								dataString[4] = salProcess.getYear();
								dataString[5] = String.valueOf(empIdObj[i]);
								Object[][] pf_data = null;
								try {
									pf_data = nonModel.getPFData(path, month,
											year);

									salProcess.setPfCode(String
											.valueOf(pf_data[0][0]));
								} catch (Exception e) {
									logger
											.error(
													"exception in getPFData setting",
													e);
								}
								if (empPFFlag.equals("N")) {
									pf_amount = 0;
								} else {
									Object[][] PF_ConfigObj = getPFData(path,
											salProcess.getMonth(), salProcess
													.getYear());

									pf_amount = getEmpPFAmt(null,
											credit_amount, PF_ConfigObj,
											dataString);
								}
								logger.info("empPFFlag-----------" + empPFFlag);
								logger.info("pf_amount-----------" + pf_amount);
								debitObject[debitObjCOunt][0] = pf_amount;
								debitObject[debitObjCOunt][1] = salProcess
										.getPfCode();
								debitObject[debitObjCOunt][2] = String
										.valueOf(empIdObj[i]);

								debitObjCOunt++;

								gross_amount = (Object[][]) creditConfDataMap
										.get(String.valueOf(empIdObj[i]));

								Object[][] esi_configData = null;
								String comLedgerCode = "";
								try {
									esi_configData = getESIData(path,
											salProcess.getMonth(), salProcess
													.getYear());

									comLedgerCode = nonModel.prevLedger(month,
											year, esi_configData);
								} catch (Exception e) {
									// TODO: handle exception
								}
								double totalESICreditAmount = 0, totalESICGross = 0;
								if (credit_amount != null) {
									for (int k = 0; k < credit_amount.length; k++) {
										try {
											if (String.valueOf(
													credit_amount[k][2]).trim()
													.equals("Y")) {
												totalESICreditAmount += Double
														.parseDouble(String
																.valueOf(credit_amount[k][1]));
											}
										} catch (Exception e) {
										}
									}
								}
								if (gross_amount != null) {
									for (int m = 0; m < gross_amount.length; m++) {
										try {
											if (String.valueOf(
													gross_amount[m][2]).trim()
													.equals("Y")) {
												totalESICGross += Double
														.parseDouble(String
																.valueOf(gross_amount[m][1]));
											}
										} catch (Exception e) {
											// TODO Auto-generated catch block
										}

									}
								}
								try {

									esi_amount = getEmpESIAmt(esi_configData,
											dataString, totalESICGross,
											totalESICreditAmount, comLedgerCode);

									salProcess.setEsiCode(String
											.valueOf(esi_configData[0][0]));
									/*
									 * if(!divEsicFlag.equals("Y")){
									 * esi_amount=0; }
									 */
								} catch (Exception e) {
									logger
											.info(" exception in esi_amount-----------"
													+ e);
								}
								debitObject[debitObjCOunt][0] = esi_amount;
								debitObject[debitObjCOunt][1] = salProcess
										.getEsiCode();
								debitObject[debitObjCOunt][2] = String
										.valueOf(empIdObj[i]);

								debitObjCOunt++;

								ptax_amount = getPTAXAmount(credit_amount,
										path, salProcess.getMonth(), salProcess
												.getYear(), empType, empBranch,
										empPTAXState, salProcess);

								if (empPTAXFlag.equals("N")) {
									ptax_amount = 0;
								}
								if (salProcess.getPtaxCode().equals("null")
										|| salProcess.getPtaxCode()
												.equals(null)
										|| salProcess.getPtaxCode().equals("")) {

									debitObject[debitObjCOunt][0] = "0";
									debitObject[debitObjCOunt][1] = "0";
									debitObject[debitObjCOunt][2] = String
											.valueOf(empIdObj[i]);

								} else {
									debitObject[debitObjCOunt][0] = ptax_amount;
									debitObject[debitObjCOunt][1] = salProcess
											.getPtaxCode();
									debitObject[debitObjCOunt][2] = String
											.valueOf(empIdObj[i]);
								}

								debitObjCOunt++;

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						// debitObject = getRoundCheckValues1(debitObject);
						for (int j = 0; j < debitObject.length; j++) {
							int debitRound = 0;
							try {
								debitRound = Integer.parseInt(String
										.valueOf(debitRoundMap.get(String
												.valueOf(debitObject[j][1]))));
							} catch (Exception e) {
								// TODO: handle exception
							}
							double debitAmt = Double.parseDouble(String
									.valueOf(debitObject[j][0]));
							debitObject[j][0] = Utility.twoDecimals(roundCheck(
									debitRound, debitAmt));
						}

						String updateCredit = "update hrms_sal_debits_"
								+ salProcess.getYear()
								+ " set SAL_AMOUNT=? where SAL_debit_CODE=? and emp_id=? and sal_ledger_code in("
								+ ledgerCode + ")";
						getSqlModel().singleExecute(updateCredit, debitObject);
					}

					uploadModel.terminate();
					nonModel.terminate();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String creditSumQuery = " select emp_id,sum(sal_amount) from hrms_sal_credits_"
						+ salProcess.getYear()
						+ " where "
						+ " emp_id in ("
						+ empIdString
						+ ") and sal_ledger_code in("
						+ ledgerCode + ") group by emp_id order by emp_id ";
				Object[][] creditSumObject = getSqlModel().getSingleResult(
						creditSumQuery);

				String debitSumQuery = " select emp_id,sum(sal_amount) from hrms_sal_debits_"
						+ salProcess.getYear()
						+ " where "
						+ " emp_id in ("
						+ empIdString
						+ ") and sal_ledger_code in("
						+ ledgerCode + ") group by emp_id order by emp_id ";

				Object[][] debitSumObject = getSqlModel().getSingleResult(
						debitSumQuery);

				Object[][] finalSalaryObject = new Object[creditSumObject.length][4];
				for (int i = 0; i < creditSumObject.length; i++) {
					finalSalaryObject[i][0] = creditSumObject[i][1];
					finalSalaryObject[i][1] = debitSumObject[i][1];
					finalSalaryObject[i][2] = Double.parseDouble(String
							.valueOf(creditSumObject[i][1]))
							- Double.parseDouble(String
									.valueOf(debitSumObject[i][1]));
					finalSalaryObject[i][3] = creditSumObject[i][0];
				}

				String updAmt = "UPDATE HRMS_SALARY_"
						+ salProcess.getYear()
						+ " SET SAL_TOTAL_CREDIT =?,SAL_TOTAL_DEBIT=? ,SAL_NET_SALARY = ?"
						+ " where SAL_LEDGER_CODE IN (" + ledgerCode
						+ ") AND  EMP_ID=?";
				getSqlModel().singleExecute(updAmt, finalSalaryObject);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

		}
		return "2";

	}

	/**
	 * Purpose : this method is used to get Ptax amount
	 * @param credit_amount
	 * @param path
	 * @param month
	 * @param year
	 * @param typeId
	 * @param branchId
	 * @param location
	 * @param salProcess
	 * @return boolean
	 */
	public double getPTAXAmount(Object[][] credit_amount, String path,
			String month, String year, String typeId, String branchId,
			String location, SalaryProcess salProcess) {
		float totalPTAXCreditAmount = 0;
		double ptax_amount = 0;
		NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();
		Object[][] ptax_data = null;
		try {
			if (credit_amount != null) {
				for (int i = 0; i < credit_amount.length; i++) {
					try {
						if (String.valueOf(credit_amount[i][3]).trim().equals(
								"Y")) {
							totalPTAXCreditAmount += Double.parseDouble(String
									.valueOf(credit_amount[i][1]));
						}

					} catch (Exception e) {

					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		try {

			// logger.info("ptax gross---------"+totalPTAXCreditAmount);
			ptax_data = nonModel.getPtaxAmount(path, month, year, location,
					totalPTAXCreditAmount);// nonIndustrialSalary.getPtaxLoc();
			salProcess.setPtaxCode(String.valueOf(ptax_data[0][7]));
			if (nonModel.getTypeBraChk(typeId, branchId, 2, path)) {
				if (location.equals("") || location.equals("null")) {
					ptax_amount = 0;
				} else {
					try {
						ptax_amount = nonModel.getEmpPtax(month, ptax_data);
					} catch (Exception e) {
						logger.error("exception in ptax setting", e);
					}
				} // end of else statement
			} // end of if statement

		} catch (Exception e) {
			logger.error("exception in ptax calculation", e);
		}

		return ptax_amount;
	}

	/**
	 * Purpose : this method is used to upload debits
	 * 
	 * @param bean
	 * @return boolean
	 */
	public String updDebits(SalaryProcess salProcess, Object[][] add,
			String debitCode) {
		try {

			String getTDSQuery = "SELECT EMP_ID,SUM(NVL(SALARY_AMOUNT,0)) FROM HRMS_MISC_SALARY_UPLOAD  "
					+ "	WHERE MONTH="
					+ salProcess.getMonth()
					+ " AND YEAR="
					+ salProcess.getYear()
					+ " AND UPLOAD_PAY_TYPE='D' AND DISPLAY_FLAG='N' AND SALARY_CODE="
					+ debitCode + "	GROUP BY EMP_ID";
			HashMap<String, Object[][]> tdsMap = getSqlModel()
					.getSingleResultMap(getTDSQuery, 0, 2);

			String ledgerCode = "";
			String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE "
					+ "ledger_month="
					+ salProcess.getMonth()
					+ " and ledger_year="
					+ salProcess.getYear()
					+ " and LEDGER_STATUS='SAL_START'";
			Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
			if (ledgerData != null && ledgerData.length > 0) {
				for (int i = 0; i < ledgerData.length; i++) {
					if (i == ledgerData.length - 1) {
						ledgerCode += ledgerData[i][0];
					} else {
						ledgerCode += ledgerData[i][0] + ",";
					}
				}
				// salProcess.setSalLedgerCode(String.valueOf(ledgerCode));
			} else {
				return "1";
			}
			String empQuery = "select emp_id,emp_token from hrms_emp_offc ";
			String year = salProcess.getYear();
			empQuery = "SELECT HRMS_EMP_OFFC.emp_id,HRMS_EMP_OFFC.emp_token FROM HRMS_EMP_OFFC"
					+ "	 INNER JOIN HRMS_SALARY_"
					+ year
					+ " ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"
					+ year
					+ ".EMP_ID)"
					+ "	 WHERE SAL_LEDGER_CODE IN("
					+ ledgerCode
					+ ") AND (NVL(SAL_DAYS,0)>0)";

			ArrayList addList = new ArrayList();
			Object[][] empObj = getSqlModel().getSingleResult(empQuery);
			for (int i = 0; i < empObj.length; i++) {
				try {
					for (int j = 0; j < add.length; j++) {
						if (String.valueOf(empObj[i][1]).trim().equals(
								String.valueOf(add[j][1]).trim())) {
							if (String.valueOf(empObj[i][0]).equals("null")
									|| String.valueOf(empObj[i][0])
											.equals(null)
									|| String.valueOf(empObj[i][0]).trim()
											.equals("")) {
								addList.add("0"); // employee Id
								addList.add(String.valueOf(add[j][0])); // debitamount
																		// amount
								break;
							} else {
								addList.add(String.valueOf(empObj[i][0])); // employee
																			// Id
								double debitAMount = Double
										.parseDouble(checkNull_Zero(String
												.valueOf(add[j][0])));
								if (tdsMap != null && tdsMap.size() > 0) {
									Object[][] obj = tdsMap.get(String
											.valueOf(empObj[i][0]));
									if (obj != null && obj.length > 0) {
										debitAMount += Double
												.parseDouble(String
														.valueOf(obj[0][1]));
									}
								}
								addList.add(String.valueOf(formatter
										.format(debitAMount))); // debit amount
								break;
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			Object[][] finalObj = new Object[addList.size() / 2][2];
			int index = 0;
			try {
				for (int i = 0; i < addList.size(); i++) {
					finalObj[index][1] = addList.get(i); // employee Id
					i++;
					finalObj[index][0] = addList.get(i); // debit amount
					index++;
				}
			} catch (RuntimeException e) {
				logger.error(e.getMessage());
			}

			String updDebQuery = "update hrms_sal_debits_"
					+ salProcess.getYear()
					+ " set sal_amount=? where emp_id=? and sal_ledger_code in("
					+ ledgerCode + ") " + " and sal_debit_code=" + debitCode;
			boolean result = getSqlModel().singleExecute(updDebQuery, finalObj);
			// ////////After sal Amt is Updated from Xls. We also have to Update
			// Total Debit and Net Salary//////////
			Object[][] updateObj = new Object[finalObj.length][3];
			if (result) {
				String empIdString = "";
				for (int i = 0; i < finalObj.length; i++) {
					empIdString = empIdString + String.valueOf(finalObj[i][1])
							+ ",";
				}
				empIdString = empIdString
						.substring(0, empIdString.length() - 1);
				String totCreditQuery = "SELECT SUM(SAL_AMOUNT),EMP_ID FROM HRMS_SAL_CREDITS_"
						+ salProcess.getYear()
						+ " WHERE 1=1 "
						+ Utility.getConcatenatedIds("EMP_ID", empIdString)
						+ " "
						+ " AND SAL_LEDGER_CODE IN ("
						+ ledgerCode
						+ ") GROUP BY EMP_ID ";
				Object[][] totCreditObj1 = getSqlModel().getSingleResult(
						totCreditQuery);
				HashMap totCreditMap = new HashMap();
				for (int i = 0; i < totCreditObj1.length; i++) {
					totCreditMap.put(String.valueOf(totCreditObj1[i][1]),
							String.valueOf(totCreditObj1[i][0]));
				}
				String totDebitQuery = "SELECT SUM(SAL_AMOUNT),EMP_ID FROM HRMS_SAL_DEBITS_"
						+ salProcess.getYear()
						+ " WHERE 1=1 "
						+ Utility.getConcatenatedIds("EMP_ID", empIdString)
						+ " "
						+ " AND SAL_LEDGER_CODE IN ("
						+ ledgerCode
						+ ") GROUP BY EMP_ID ";
				Object[][] totDebitObj1 = getSqlModel().getSingleResult(
						totDebitQuery);
				HashMap totDebitMap = new HashMap();
				for (int i = 0; i < totDebitObj1.length; i++) {
					totDebitMap.put(String.valueOf(totDebitObj1[i][1]), String
							.valueOf(totDebitObj1[i][0]));
				}
				for (int z = 0; z < finalObj.length; z++) {
					double empTotCredit = 0;
					double empTotDebit = 0;
					try {
						empTotCredit = Double.parseDouble(String
								.valueOf(totCreditMap.get(String
										.valueOf(finalObj[z][1]))));
						empTotDebit = Double.parseDouble(String
								.valueOf(totDebitMap.get(String
										.valueOf(finalObj[z][1]))));
					} catch (Exception e) {
						// TODO: handle exception
					}
					updateObj[z][0] = empTotDebit;
					updateObj[z][1] = empTotCredit - empTotDebit;
					updateObj[z][2] = String.valueOf(finalObj[z][1]);
				}
			}
			String updAmt = "UPDATE HRMS_SALARY_" + salProcess.getYear()
					+ " SET SAL_TOTAL_DEBIT =?, SAL_NET_SALARY = ?"
					+ " where SAL_LEDGER_CODE IN (" + ledgerCode
					+ ") AND  EMP_ID=?";
			getSqlModel().singleExecute(updAmt, updateObj);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return "2";
	}

	/**
	 * Purpose : this method is used to get days
	 * 
	 * @param days
	 * @return string
	 */

	public String getViewDays(String days) {
		String result = "";
		try {
			if (days.contains("d")) {

				Object[] daysData = days.split(":");

				int hrs = Integer
						.parseInt(String.valueOf(daysData[1]).substring(0,
								String.valueOf(daysData[1]).length() - 1));
				int mm = Integer
						.parseInt(String.valueOf(daysData[2]).substring(0,
								String.valueOf(daysData[2]).length() - 1));

				if (hrs == 0 && mm == 0)
					result = String.valueOf(daysData[0]).substring(0,
							String.valueOf(daysData[0]).length() - 1);
				else
					result = days;
			} else {
				result = days;
			}

		} catch (Exception e) {
			logger
					.error("Exception getting getViewDays to split the days object to hrs ---------"
							+ e);

		}
		return result;
	}

	/**
	 * Purpose : this method is used to sane employee salary after recalculate
	 * 
	 * @param rows
	 * @param debits
	 * @param credits
	 * @param emp_id
	 * @param month
	 * @param year
	 * @param total_credit
	 * @param total_debit
	 * @param empType
	 * @param salDays
	 * @param onHold
	 * @param ledgerCode
	 * @param branchCode
	 * @param typeCode
	 * @param payBillNo
	 * @param deptCode
	 * @param divCode
	 * @param recoveryFlag
	 * @return boolean
	 */
	public boolean saveSalaryAfterRecalculate(Object[][] rows,
			Object[][] debits, Object[][] credits, String[] emp_id,
			String month, String year, String[] total_credit,
			String[] total_debit, String empType, String[] salDays,
			String[] onHold, String ledgerCode, String branchCode,
			String typeCode, String payBillNo, String deptCode, String divCode,
			String recoveryFlag) {
		boolean record = false;

		String deleteQuery = "";
		String insertQuery = "";
		String empCode = "";
		try {
			boolean update = false;
			Object[][] insertData = new Object[emp_id.length][8];
			Object[][] deleteData = new Object[emp_id.length][1];

			try {
				deleteQuery = "DELETE FROM HRMS_SALARY_" + year
						+ "  WHERE SAL_LEDGER_CODE=" + ledgerCode
						+ " AND EMP_ID=?";

				insertQuery = "INSERT INTO HRMS_SALARY_"
						+ year
						+ " (SAL_LEDGER_CODE,emp_id , SAL_TOTAL_DEBIT ,SAL_TOTAL_CREDIT,SAL_NET_SALARY,SAL_DAYS,SAL_ONHOLD,SAL_HRS ) VALUES(?,?,?,?,?,?,?,to_date(?,'HH24:MI'))";

				Object[][] deleteCredit = new Object[emp_id.length][1];

				String deleteCreditQuery = "DELETE FROM HRMS_SAL_CREDITS_"
						+ year + "  WHERE SAL_LEDGER_CODE=" + ledgerCode
						+ " AND EMP_ID=?";

				String deleteDebitQuery = "DELETE FROM HRMS_SAL_DEBITS_" + year
						+ " WHERE SAL_LEDGER_CODE=" + ledgerCode
						+ " AND EMP_ID=?";
				for (int i = 0; i < emp_id.length; i++) {
					// deleteCredit[i][0] = month;
					deleteCredit[i][0] = emp_id[i];
					empCode += String.valueOf(emp_id[i]) + ",";
				}
				empCode = empCode.substring(0, empCode.length() - 1);

				// deleting all the credit records of employees for particular
				// ledger code
				update = getSqlModel().singleExecute(deleteCreditQuery,
						deleteCredit);
				// deleting all the debit records of employees for particular
				// ledger code
				update = getSqlModel().singleExecute(deleteDebitQuery,
						deleteCredit);
			} catch (RuntimeException e1) {
				logger.error(e1.getMessage());
			}

			String insertCredit = "INSERT INTO HRMS_SAL_CREDITS_"
					+ year
					+ "(EMP_ID , SAL_CREDIT_CODE ,SAL_AMOUNT,SAL_LEDGER_CODE) VALUES(?,?,?,'"
					+ ledgerCode + "')";

			String insertDebit = "INSERT INTO HRMS_SAL_DEBITS_"
					+ year
					+ " (EMP_ID, SAL_DEBIT_CODE ,SAL_AMOUNT,SAL_LEDGER_CODE) VALUES(?,?,?,'"
					+ ledgerCode + "')";
			int debitCount = 0;
			int creditCount = 0;

			Object[][] creditData = new Object[emp_id.length * credits.length][3];
			Object[][] debitData = new Object[emp_id.length * debits.length][3];
			for (int i = 0; i < emp_id.length; i++) {
				try {
					double totalDebit;
					double totalCredit;

					int k = 0;

					for (int j = 0; j < credits.length + debits.length; j++) {
						if (j < credits.length) {

							creditData[creditCount][0] = emp_id[i]; // emp_id
							creditData[creditCount][1] = credits[j][0]; // credit_code
							creditData[creditCount][2] = rows[i][j]; // amount
							creditCount++;
						} else {

							debitData[debitCount][0] = emp_id[i]; // emp_id
							debitData[debitCount][1] = debits[k][0]; // debit_code
							debitData[debitCount][2] = rows[i][j]; // amount
							debitCount++;
							k++;

						}

					}// this is end of c.length + d.length for loop

					deleteData[i][0] = emp_id[i];
					// setting employee wise values to insert into main salary
					// table
					insertData[i][0] = ledgerCode;
					insertData[i][1] = emp_id[i];
					insertData[i][2] = total_debit[i];
					insertData[i][3] = total_credit[i];
					Object[] data = getDaysWithMinutes(salDays[i]);
					insertData[i][5] = data[0];
					insertData[i][6] = onHold[i];
					insertData[i][7] = data[1];

					totalDebit = 0;
					totalCredit = 0;
					totalCredit = Double.parseDouble(total_credit[i]);
					totalDebit = Double.parseDouble(total_debit[i]);
					insertData[i][4] = Math.round((totalCredit - totalDebit)
							* Math.pow(10, 2))
							/ Math.pow(10, 2);
					// insertData[i][4] =
					// Utility.twoDecimals((totalCredit-totalDebit)) ;

				} catch (Exception e1) {
					logger.error(e1.getMessage());
				}

			}
			try {
				// deleting the records of main salary table
				getSqlModel().singleExecute(deleteQuery, deleteData);
				// inserting the records into main salary table
				getSqlModel().singleExecute(insertQuery, insertData);
			} catch (Exception e1) {
				logger.error("exception in deleting and inserting data", e1);
			}

			// batch inserting into hrms_sal_credits_year and
			// hrms_sal_debits_year
			try {
				// this query is used to insert all credits of all employees
				getSqlModel().singleExecute(insertCredit, creditData);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			try {
				// this query is used to insert all debits of all employees
				getSqlModel().singleExecute(insertDebit, debitData);

			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			/**
			 * this query is used to retrieve center,emp type, rank, division,
			 * department, pay bill of employees from official details
			 */
			String selectQuery = "SELECT EMP_CENTER,EMP_RANK,EMP_PAYBILL,EMP_TYPE,EMP_DEPT,EMP_DIV,EMP_CADRE,EMP_SAL_GRADE,EMP_ID FROM HRMS_EMP_OFFC where 1=1";
			// +" WHERE EMP_PAYBILL ='"+paybill+"' AND EMP_TYPE = '"+empType+"'
			// ";
			if (!branchCode.equals("")) {
				selectQuery = selectQuery + " AND EMP_CENTER=" + branchCode;
			}

			if (!typeCode.equals("")) {
				selectQuery = selectQuery + " AND EMP_TYPE=" + typeCode;
			}

			if (!payBillNo.equals("")) {
				selectQuery = selectQuery + " AND EMP_PAYBILL=" + payBillNo;
			}

			if (!deptCode.equals("")) {
				selectQuery = selectQuery + " AND EMP_DEPT=" + deptCode;
			}

			if (!divCode.equals("")) {
				selectQuery = selectQuery + " AND EMP_DIV=" + divCode;
			}

			Object[][] empData = getSqlModel().getSingleResult(selectQuery);

			// below query is used to update center,emp type, rank, division,
			// department, pay bill of employees
			String updateEmpData = " UPDATE HRMS_SALARY_"
					+ year
					+ " SET SAL_EMP_CENTER =?,SAL_EMP_RANK =?,SAL_EMP_PAYBILL =?,SAL_EMP_TYPE =?,SAL_DEPT=?,SAL_DIVISION=?,SAL_EMP_GRADE=?,SAL_EMP_SAL_GRADE=?"
					+ " WHERE EMP_ID =? AND SAL_LEDGER_CODE ='" + ledgerCode
					+ "' ";
			getSqlModel().singleExecute(updateEmpData, empData);

			try {
				if (recoveryFlag.equals("Y")) {

					/*
					 * String delQuery = " DELETE FROM
					 * HRMS_SAL_RECVCDTS_"+year+" WHERE SAL_LEDGER_CODE
					 * ="+ledgerCode;
					 * 
					 * getSqlModel().singleExecute(delQuery);
					 * 
					 * String selectCredits = " SELECT
					 * EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0)
					 * FROM HRMS_CREDIT_HEAD " +" LEFT JOIN HRMS_EMP_CREDIT ON
					 * (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE )" +"
					 * where HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND
					 * CREDIT_PAYFLAG='Y' AND EMP_ID IN ("+empCode+")" +" order
					 * BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
					 * 
					 * Object[][] credit_amount =
					 * getSqlModel().getSingleResult(selectCredits);
					 * 
					 * String inserQuery =" INSERT INTO
					 * HRMS_SAL_RECVCDTS_"+year+"
					 * (SAL_LEDGER_CODE,EMP_ID,SAL_CREDIT_CODE,SAL_AMOUNT)
					 * VALUES " +" ("+ledgerCode+",?,?,?)";
					 * 
					 * getSqlModel().singleExecute(inserQuery, credit_amount);
					 */
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			record = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return record;
	}

	/**
	 * Purpose : method to calculate recovery employee amount -By Mangesh
	 */
	public double getEmpRecoveryAmt(String dataString[]) {
		double recoveryAmt = 0.0;
		int month = Integer.parseInt(dataString[0]);
		int year = Integer.parseInt(dataString[1]);
		String recoveryDebitCode = dataString[2];
		String recoveryDays = dataString[3];
		String emp_id = dataString[4];

		if (month == 1) {
			month = 12;
			year -= year;
		} else {
			month = month - 1;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
		int daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

		String prevGrossQuery = "SELECT NVL(SUM(SAL_NET_SALARY),0) FROM HRMS_SALARY_"
				+ year
				+ " LEFT JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="
				+ month
				+ " AND LEDGER_YEAR="
				+ year
				+ ")"
				+ " WHERE EMP_ID ="
				+ emp_id;

		String prevRecAmtQuery = "SELECT NVL(SUM(SAL_AMOUNT),0) FROM HRMS_SAL_DEBITS_"
				+ year
				+ " LEFT JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="
				+ month
				+ " AND LEDGER_YEAR="
				+ year
				+ ")"
				+ " WHERE EMP_ID ="
				+ emp_id + " AND SAL_DEBIT_CODE=" + recoveryDebitCode;

		Object[][] prevGrossAmtObj = getSqlModel().getSingleResult(
				prevGrossQuery);
		Object[][] prevRecAmt = getSqlModel().getSingleResult(prevRecAmtQuery);

		double prevGrossAmt = Double.parseDouble(String
				.valueOf(prevGrossAmtObj[0][0]))
				+ Double.parseDouble(String.valueOf(prevRecAmt[0][0]));

		recoveryAmt = prevGrossAmt / daysOfMonth; // recovery amount per day

		recoveryAmt = recoveryAmt * Double.parseDouble(recoveryDays);

		return recoveryAmt;
	}

	/**
	 * Purpose : this method is used to calculate tax
	 * 
	 * @param empList
	 * @param frmYear
	 * @param salMonth
	 * @return boolean
	 */
	public boolean recalculateTax(Object[][] empList, int frmYear,
			String salMonth) {

		/**
		 * Following code calculates the tax and updates tax process
		 */
		try {

			CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
			taxmodel.initiate(context, session);
			logger.info("I m calling tax calculation method");
			/*
			 * empList = new Object[1][1]; empList [0][0]="1118";
			 */
			if (empList != null && empList.length > 0)
				taxmodel.calculateTaxThreadSalary(empList, String
						.valueOf(frmYear), String.valueOf(frmYear + 1),
						salMonth);
			taxmodel.terminate();
		} catch (Exception e) {
			logger.error("Exception in calling calculateTax : " + e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Purpose : this method is used to get tds debit code for selected from
	 * yeat and to year
	 * 
	 * @param frmYear
	 * @param toYear
	 * @returnstring
	 */

	public String getTdsDebitCode(String frmYear, String toYear) {
		String query = "  SELECT NVL(TDS_DEBIT_CODE,0) from HRMS_TAX_PARAMETER WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear + " AND TDS_FINANCIALYEAR_TO = " + toYear + " ";
		Object[][] tdsDebitCodeObj = getSqlModel().getSingleResult(query);
		String tdsDebitCode = "0";
		try {
			if (tdsDebitCodeObj != null && tdsDebitCodeObj.length > 0) {
				tdsDebitCode = String.valueOf(tdsDebitCodeObj[0][0]);
			}
		} catch (Exception e) {
			tdsDebitCode = "0";
			logger.error("TDS debit code not found" + e);
		}
		return tdsDebitCode;
	}

	/**
	 * Purpose : this method is used to get tds employee amount
	 * 
	 * @param empList
	 * @param salYear
	 * @param salLedger
	 * @param fromYear
	 * @return object
	 */
	public Object[][] getEmpListWithDebitAmt(Object[][] empList,
			String salYear, String salLedger, int fromYear) {
		Object[][] empListWithDebitAmt = new Object[empList.length][3];

		String empIdString = "";
		for (int i = 0; i < empListWithDebitAmt.length; i++) {
			empListWithDebitAmt[i][0] = "0";
			empListWithDebitAmt[i][1] = empList[i][1]; // emp_token
			empListWithDebitAmt[i][2] = empList[i][0]; // emp_id
			if (i != empListWithDebitAmt.length - 1)
				empIdString += String.valueOf(empList[i][0]) + ",";
			else {
				empIdString += String.valueOf(empList[i][0]);
			}
		}
		String tdsAmtQuery = " SELECT TDS_TAXPERMONTH,TDS_EMP_ID FROM HRMS_TDS WHERE TDS_FROM_YEAR="
				+ fromYear
				+ "  "
				+ Utility.getConcatenatedIds("TDS_EMP_ID", empIdString)
				+ " ORDER BY TDS_EMP_ID";
		Object[][] tempObj = getSqlModel().getSingleResult(tdsAmtQuery);

		for (int i = 0; i < empListWithDebitAmt.length; i++) {
			for (int j = 0; j < tempObj.length; j++) {
				if (String.valueOf(empListWithDebitAmt[i][2]).equals(
						String.valueOf(tempObj[j][1]))) {
					empListWithDebitAmt[i][0] = tempObj[j][0]; // debit amount
					break;
				}
			} // end of tempObj for loop
		} // end of empListWithDebitAmt for loop

		return empListWithDebitAmt;
	}

	/**
	 * Purpose : this method is used to get day with minutes
	 * @param days
	 */
	public Object[] getDaysWithMinutes(String days) {
		Object[] data = new Object[2];
		try {

			if (days.contains("d")) {

				Object[] daysData = days.split(":");

				data[0] = String.valueOf(daysData[0]).substring(0,
						String.valueOf(daysData[0]).length() - 1);
				data[1] = String.valueOf(daysData[1]).substring(0,
						String.valueOf(daysData[1]).length() - 1)
						+ ":"
						+ String.valueOf(daysData[2]).substring(0,
								String.valueOf(daysData[2]).length() - 1);
			} else {

				data[0] = days;
				data[1] = "00:00";
			}

		} catch (Exception e) {
			logger
					.error("Exception getting getDaysWithMinutes to split the days object to hrs ---------"
							+ e);
			data[0] = "00";
			data[1] = "00:00";
		}
		return data;
	}

	/**
	 * Purpose : this method is used to set tds amount as zero
	 * @param empList
	 * @param ledgerCode
	 * @param year
	 * @param debitCode
	 */
	public void setTdsAmountToZero(Object[][] empList, String ledgerCode,
			String year, String debitCode) {

		String query = "UPDATE HRMS_SAL_DEBITS_"
				+ year
				+ " SET SAL_AMOUNT =0 WHERE SAL_LEDGER_CODE=? AND EMP_ID=? AND SAL_DEBIT_CODE=? ";

		try {
			Object[][] debitUpdateObj = new Object[empList.length][3];
			for (int i = 0; i < debitUpdateObj.length; i++) {
				debitUpdateObj[i][0] = ledgerCode;
				debitUpdateObj[i][1] = String.valueOf(empList[i][0]);
				debitUpdateObj[i][2] = debitCode;
			}
			getSqlModel().singleExecute(query, debitUpdateObj);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Purpose : this method is used to set other income
	 * @param bean
	 * @return boolean
	 */
	public void setOtherIncomeFlags(SalaryProcess bean) {
		boolean otherIncomeFlag = false;
		String extraWorkQuery = "";
		String leaveEncashQuery = "";
		try {
			extraWorkQuery = "SELECT COUNT(EMP_ID) FROM HRMS_EXTRAWORK_PROCESS_DTL "
					+ " INNER JOIN HRMS_EXTRAWORK_PROCESS_HDR ON (HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_PROCESS_CODE=HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE"
					+ " AND EXTRAWORK_PROCESS_DIVISION="
					+ bean.getDivisionId()
					+ " AND  EXTRAWORK_INCLUDE_SAL_FLAG='Y' AND EXTRAWORK_INCLUDE_SAL_MONTH="
					+ bean.getMonth()
					+ " AND EXTRAWORK_INCLUDE_SAL_YEAR="
					+ bean.getYear()
					+ " )"
					+ " WHERE EXTRAWORK_PROCESS_FLAG='Y'";
			Object[][] extraWorkCount = getSqlModel().getSingleResult(
					extraWorkQuery);

			if (extraWorkCount != null && extraWorkCount.length > 0) {
				if (String.valueOf(extraWorkCount[0][0]).equals("0")) {
					bean.setExtraWorkFlag(false);
				} else {
					otherIncomeFlag = true;
					bean.setExtraWorkFlag(true);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			leaveEncashQuery = " SELECT COUNT(EMP_ID) FROM HRMS_ENCASHMENT_PROCESS_HDR "
					+ " INNER JOIN HRMS_ENCASHMENT_PROCESS_DTL ON (HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE = HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE AND ENCASHMENT_PROCESS_DIVISION="
					+ bean.getDivisionId()
					+ ")"
					+ " WHERE HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT > 0 AND ENCASHMENT_PROCESS_FLAG ='Y' AND ENCASHMENT_INCLUDE_SAL_FLAG='Y' "
					+ " AND ENCASHMENT_INCLUDE_SAL_MONTH="
					+ bean.getMonth()
					+ " AND ENCASHMENT_INCLUDE_SAL_YEAR=" + bean.getYear();
			Object[][] leaveEncashCount = getSqlModel().getSingleResult(
					leaveEncashQuery);

			if (leaveEncashCount != null && leaveEncashCount.length > 0) {
				if (String.valueOf(leaveEncashCount[0][0]).equals("0")) {
					bean.setLeaveEncashFlag(false);
				} else {
					otherIncomeFlag = true;
					bean.setLeaveEncashFlag(true);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		bean.setOtherIncomeFlag(otherIncomeFlag);
	}

	/**
	 * Purpose : this method is used to extrawork allowance amount
	 * @param bean
	 * @param path
	 * @param listOfFilters
	 * @return string
	 */
	public String pullExtraWorkBenefit(SalaryProcess bean, String path,
			String[] listOfFilters) {
		String result = "0";
		String filterQuery = setEmpFiletrs(listOfFilters);
		try {
			String empQuery = " SELECT SUM(CREDIT_AMOUNT),EMP_TOKEN,CREDIT_CODE FROM HRMS_EXTRAWORK_PROCESS_DTL  "
					+ " INNER JOIN HRMS_EXTRAWORK_PROCESS_HDR ON (HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_PROCESS_CODE=HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE"
					+ " AND EXTRAWORK_PROCESS_DIVISION="
					+ bean.getDivisionId()
					+ " AND "
					+ " EXTRAWORK_INCLUDE_SAL_FLAG='Y' AND EXTRAWORK_PROCESS_FLAG='Y' AND EXTRAWORK_INCLUDE_SAL_MONTH="
					+ bean.getMonth()
					+ " AND EXTRAWORK_INCLUDE_SAL_YEAR="
					+ bean.getYear()
					+ " )"
					+ " INNER JOIN HRMS_EXTRAWORK_PROCESS_CREDITS ON (HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_DTL_CODE=HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_DTL_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE CREDIT_AMOUNT>0 "
					+ filterQuery
					+ " GROUP BY EMP_TOKEN,CREDIT_CODE";
			Object[][] empObject = getSqlModel().getSingleResult(empQuery);

			if (empObject != null && empObject.length > 0) {
				result = updCredits(bean, empObject, path);
				if (result.equals("2")) {
					result = String.valueOf(empObject.length);
				} else {
					result = "0";
				}
			}
		} catch (Exception e) {
			result = "0";
		}
		return result;

	}

	/**
	 * Purpose : this method is used to pull leave encashment amount
	 * @param bean
	 * @param path
	 * @param listOfFilters
	 * @return
	 */
	public String pullLeaveEncashment(SalaryProcess bean, String path,
			String[] listOfFilters) {
		String result = "0";
		String filterQuery = setEmpFiletrs(listOfFilters);
		try {
			String empQuery = " SELECT SUM(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT),EMP_TOKEN,ENCASHMENT_CREDIT_CODE FROM HRMS_ENCASHMENT_PROCESS_HDR "
					+ " INNER JOIN HRMS_ENCASHMENT_PROCESS_DTL ON (HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE = HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE AND ENCASHMENT_PROCESS_DIVISION="
					+ bean.getDivisionId()
					+ ")"
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID) "
					+ " WHERE HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT > 0 AND ENCASHMENT_PROCESS_FLAG ='Y' AND ENCASHMENT_INCLUDE_SAL_FLAG='Y' "
					+ " AND ENCASHMENT_INCLUDE_SAL_MONTH="
					+ bean.getMonth()
					+ " AND ENCASHMENT_INCLUDE_SAL_YEAR="
					+ bean.getYear()
					+ " "
					+ filterQuery
					+ " GROUP BY EMP_TOKEN,ENCASHMENT_CREDIT_CODE ";
			Object[][] empObject = getSqlModel().getSingleResult(empQuery);

			if (empObject != null && empObject.length > 0) {
				result = updCredits(bean, empObject, path);
				if (result.equals("2")) {
					result = String.valueOf(empObject.length);
				} else {
					result = "0";
				}
			}
		} catch (Exception e) {
			result = "0";
		}
		return result;

	}

	/**
	 * Purpose : this method is used to pull allowance
	 * @param bean
	 * @return string
	 */
	public String pullAllowance(SalaryProcess bean, String path,
			String[] listOfFilters) {
		String result = "0";
		String filterQuery = setEmpFiletrs(listOfFilters);
		try {
			String empQuery = " SELECT SUM(HRMS_ALLOWANCE_EMP_DTL.ALLW_TOTAL_AMT),EMP_TOKEN,ALLW_CREDIT_HEAD FROM HRMS_ALLOWANCE_HDR"
					+ " INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID = HRMS_ALLOWANCE_HDR.ALLW_ID AND ALLW_DIVISION="
					+ bean.getDivisionId()
					+ ")"
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID) "
					+ " WHERE HRMS_ALLOWANCE_EMP_DTL.ALLW_TOTAL_AMT > 0 AND ALLW_FINALIZE_FLAG ='L' AND ALLW_PAY_IN_SALARY='Y' "
					+ " AND ALLW_PAY_MONTH="
					+ bean.getMonth()
					+ " AND ALLW_PAY_YEAR="
					+ bean.getYear()
					+ " "
					+ filterQuery + " GROUP BY EMP_TOKEN,ALLW_CREDIT_HEAD  ";
			Object[][] empObject = getSqlModel().getSingleResult(empQuery);

			if (empObject != null && empObject.length > 0) {
				result = updCredits(bean, empObject, path);
				if (result.equals("2")) {
					result = String.valueOf(empObject.length);
				} else {
					result = "0";
				}
			}
		} catch (Exception e) {
			result = "0";
		}
		return result;
	}

	/**
	 * Purpose : this method is used to get monthly performance amount
	 * @param creditAmt
	 * @param ratingData
	 */
	public double getMonthlyPerformanceAmt(double creditAmt,
			String[] ratingData, Object[][] ratingConfigObj) {
		/*
		 * ScriptEngineManager manager = new ScriptEngineManager ();
		 * ScriptEngine engine = manager.getEngineByExtension ("js");
		 */
		double monthlyRating = Double.parseDouble(ratingData[0]);
		double fraction = 0;
		double variableAmt = Double.parseDouble(checkNull_Zero(ratingData[1]));
		// Script variables intrate, principal, and months must be defined (via
		// the put() method) prior to evaluating this script.
		try {
			if (String.valueOf(ratingConfigObj[0][2]).equals("F")) {
				// String calcMonthlyPaymentScript =
				// String.valueOf(ratingConfigObj[0][3]); //rating formula
				if (monthlyRating == 120)
					fraction = 18;
				else if (monthlyRating >= 115)
					fraction = 16;
				else if (monthlyRating >= 110)
					fraction = 14;
				else if (monthlyRating >= 105)
					fraction = 12;
				else if (monthlyRating >= 100)
					fraction = 10;
				else if (monthlyRating >= 95)
					fraction = 8;
				else if (monthlyRating >= 90)
					fraction = 6;
				else if (monthlyRating >= 85)
					fraction = 4;
				else if (monthlyRating >= 80)
					fraction = 2;
				else if (monthlyRating >= 75)
					fraction = 0;
				else
					fraction = -1;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (fraction < 0) {
			creditAmt = 0;
		} else
			creditAmt = creditAmt + (variableAmt * fraction / 100);
		logger.info("creditAmt last = " + creditAmt);
		return creditAmt;
	}

	/**
	 * Purpose : this method is used to convert object to hash map conversion
	 * @param year
	 * @return boolean
	 */
	private HashMap convertObjectToHashMap(Object[][] totalDataObject) {
		HashMap<String, Object[][]> dataMap = new HashMap<String, Object[][]>();

		if (totalDataObject == null) {

		} // end of if
		else if (totalDataObject.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				String empId = "";
				for (int i = 0; i < totalDataObject.length; i++) {
					if (i == 0) {
						empId = String.valueOf(totalDataObject[i][0]);
					}
					if (empId.equals(String.valueOf(totalDataObject[i][0]))) {
						v.add(totalDataObject[i]);
					} // end of if
					else {
						Object[][] creditData = new Object[v.size()][totalDataObject[0].length];
						for (int k = 0; k < creditData.length; k++) {
							creditData[k] = (Object[]) v.get(k);
						} // end of loop
						dataMap.put(empId, creditData);
						v = new Vector();
						v.add(totalDataObject[i]);
					} // end of totalDataObject loop

				} // end of empList loop
			} catch (Exception e) {
				logger.error("exception ", e);
			} // end of catch
			// //logger.info("dataMap.get(429)======"+dataMap.get("429"));
		} // end of else

		return dataMap;
	} // end of convertObjectToHashMap method

	/**
	 * Purpose : this method is used to LOAD pf, ptax, esi
	 * 
	 */
	public void loadPayrollSetting() {
		// SET EMPLOYEE TYPE CONFIGURATION
		String query = "SELECT TYPE_ID,  NVL(TYPE_ESI,' '), NVL(TYPE_PT,' '),NVL(TYPE_PF,' '),NVL(TYPE_PF_MIN_AMT,' ') FROM  HRMS_EMP_TYPE ORDER BY TYPE_ID ";
		typeData = getSqlModel().getSingleResult(query);
		// SETBRANCHCONFIGURATION
		String branchQuery = "SELECT CENTER_ID,  NVL(CENTER_ESI,' '), NVL(CENTER_PT,' '),NVL(CENTER_PF,' '),HRMS_LOCATION.LOCATION_CODE,CENTER_ISMETRO	  FROM  HRMS_CENTER  left JOIN  HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_CENTER.CENTER_PTAX_STATE) ORDER BY CENTER_ID ";
		braData = getSqlModel().getSingleResult(branchQuery);
		// SET ESICONFIGURATION
		String esiquery = "SELECT ESI_DEBIT_CODE, ESI_FORMULA, ESI_EMP_PERCENTAGE, TO_CHAR(ESI_DATE,'dd-mm-yyyy'), ESI_GROSS_UPTO,  ESI_MONTH_START, ESI_MONTH_END FROM HRMS_ESI  ORDER BY ESI_DATE DESC";
		esi_data_obj = getSqlModel().getSingleResult(esiquery);
		// SET PF CONFIGURATION
		String pfquery = "SELECT PF_DEBIT_CODE, PF_FORMULA, PF_PERCENTAGE, TO_CHAR(PF_DATE,'dd-mm-yyyy'),PF_DEDUCT_EMOL_AMT,PF_DEDUCT_CRITERIA,PF_EMOL_NO_MAX_LIMIT_CHK,PF_EMOL_MAX_LIMIT  FROM HRMS_PF_CONF  ORDER BY  PF_DATE DESC";
		pf_data_obj = getSqlModel().getSingleResult(pfquery);
		// SET PTAX CONFIGURATION
		String ptaxquery = "SELECT HRMS_PTAX_DTL.PTAX_DTLCODE, HRMS_PTAX_HDR.PTAX_LOCATION,   PTAX_FROMAMT, PTAX_UPTOAMT, NVL(PTAX_VARMTH,''), PTAX_FIXEDAMT,  PTAX_VARAMT ,TO_CHAR(PTAX_DATE,'DD-MM-YYYY'),HRMS_PTAX_HDR.PTAX_DEBIT_CODE FROM HRMS_PTAX_HDR  LEFT JOIN HRMS_PTAX_DTL ON (HRMS_PTAX_DTL.PTAX_CODE = HRMS_PTAX_HDR.PTAX_CODE)  ORDER BY PTAX_DATE DESC ";
		ptax_data_obj = getSqlModel().getSingleResult(ptaxquery);
	}

	/**
	 * Purpose : this method is used to get employee misc salary
	 * @param year
	 * @param month
	 */
	public HashMap<String, Object[][]> getSalMiscUpload(String month,
			String year) {
		String query = "SELECT EMP_ID,NVL(SUM(SALARY_AMOUNT),0),NVL(UPLOAD_IS_OVERWRITE,'N') ,EMP_ID||'#'||SALARY_CODE||'#'||UPLOAD_PAY_TYPE "
				+ "		FROM HRMS_MISC_SALARY_UPLOAD "
				+ "		WHERE MONTH="
				+ month
				+ " AND YEAR="
				+ year
				+ " AND DISPLAY_FLAG='Y' GROUP BY EMP_ID,NVL(UPLOAD_IS_OVERWRITE,'N'),EMP_ID||'#'||SALARY_CODE||'#'||UPLOAD_PAY_TYPE ";
		HashMap<String, Object[][]> map = getSqlModel().getSingleResultMap(
				query, 3, 2);
		return map;

	}

	/**
	 * Purpose : this method is used to show misc salary upload statistics
	 * @param bean
	 * @return
	 */
	public void showMiscStatistics(SalaryProcess bean) {
		String month = bean.getMonth();
		String year = bean.getYear();
		String ledgerCode = bean.getLedgerCode();
		/**
		 * SET MANAGE PREVIOUS ONHOLD EMPLOYEE COUNT
		 */
		String[] listOfFilters = new String[5];
		listOfFilters[0] = bean.getBranchId();
		listOfFilters[1] = bean.getDepartmentId();
		listOfFilters[2] = bean.getPayBillId();
		listOfFilters[3] = bean.getEmployeeTypeId();
		listOfFilters[4] = bean.getDivisionId();

		String query = "SELECT SALARY_CODE,TO_CHAR(CREDIT_NAME),'Credit',COUNT(*),SUM(NVL(SALARY_AMOUNT,0)) FROM HRMS_MISC_SALARY_UPLOAD "
				+ "  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_MISC_SALARY_UPLOAD.EMP_ID) "
				+ "	INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_MISC_SALARY_UPLOAD.SALARY_CODE AND UPLOAD_PAY_TYPE='C') "
				+" INNER JOIN HRMS_SALARY_" + year + " ON(HRMS_SALARY_" + year + ".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SAL_LEDGER_CODE="+ledgerCode+")"
				+ "	WHERE  SALARY_AMOUNT>0  AND DISPLAY_FLAG='Y' AND MONTH="
				+ month + " AND YEAR=" + year + " ";
		/*if (!listOfFilters[4].equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV=  " + listOfFilters[4];
		}
		if (!listOfFilters[0].equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER=  " + listOfFilters[0];
		}
		if (!listOfFilters[1].equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT=  " + listOfFilters[1];
		}
		if (!listOfFilters[2].equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_PAYBILL=  " + listOfFilters[2];
		}
		if (!listOfFilters[3].equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_TYPE=  " + listOfFilters[3];
		}*/
		query += " GROUP BY SALARY_CODE,CREDIT_NAME "
				+ "  UNION ALL  "
				+ " 	SELECT  SALARY_CODE,TO_CHAR(DEBIT_NAME),'Debit',COUNT(*),SUM(NVL(SALARY_AMOUNT,0))  FROM HRMS_MISC_SALARY_UPLOAD  "
				+ "  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_MISC_SALARY_UPLOAD.EMP_ID) "
				+ " 	INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_MISC_SALARY_UPLOAD.SALARY_CODE AND UPLOAD_PAY_TYPE='D')	"
				+" INNER JOIN HRMS_SALARY_" + year + " ON(HRMS_SALARY_" + year + ".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SAL_LEDGER_CODE="+ledgerCode+")"
				+ " 	WHERE  SALARY_AMOUNT>0 AND DISPLAY_FLAG='Y' AND MONTH="
				+ month + " AND YEAR=" + year + " ";
		/*if (!listOfFilters[4].equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV=  " + listOfFilters[4];
		}
		if (!listOfFilters[0].equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER=  " + listOfFilters[0];
		}
		if (!listOfFilters[1].equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT=  " + listOfFilters[1];
		}
		if (!listOfFilters[2].equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_PAYBILL=  " + listOfFilters[2];
		}
		if (!listOfFilters[3].equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_TYPE=  " + listOfFilters[3];
		}*/
		query += " GROUP BY SALARY_CODE,DEBIT_NAME ";

		Object[][] obj = getSqlModel().getSingleResult(query);
		ArrayList list = new ArrayList();
		bean.setMiscList(null);
		double totalAMount = 0.0;
		int totalEmp = 0;
		if (obj != null && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				SalaryProcess subBean = new SalaryProcess();
				subBean.setMiscCode(String.valueOf(obj[i][0]));
				subBean.setMiscName(String.valueOf(obj[i][1]));
				subBean.setMiscType(String.valueOf(obj[i][2]));
				subBean.setMiscNoOfEmp(String.valueOf(obj[i][3]));
				subBean.setMiscTotalAmount(formatter.format(Double
						.parseDouble(String.valueOf(obj[i][4]))));
				totalEmp += Integer.parseInt(String.valueOf(obj[i][3]));
				totalAMount += Double.parseDouble(String.valueOf(obj[i][4]));
				list.add(subBean);
			}
			bean.setMiscTotalEmp(String.valueOf(totalEmp));
			bean.setMiscTotal(formatter.format(Double.parseDouble(String
					.valueOf((totalAMount)))));
			bean.setMiscList(list);

		}

		/**
		 * SET SALARY PROCESS STATISTICS
		 */

		String statisticsQuery = "SELECT HRMS_SALARY_" + year
				+ ".SAL_ONHOLD,HRMS_SALARY_" + year + ".SAL_DAYS,HRMS_SALARY_"
				+ year + ".EMP_ID,NVL(HRMS_SALARY_" + year
				+ ".EMP_MISC_UPLOAD_FLAG,'N') FROM HRMS_SALARY_" + year
				+ "   WHERE SAL_LEDGER_CODE=" + ledgerCode;
		Object[][] ledObj = getSqlModel().getSingleResult(statisticsQuery);
		// HashMap<String,
		// Object[][]>salaryProcessMap=getSqlModel().getSingleResultMap(statisticsQuery,
		// 2, 2);

		String salaryQuery = "SELECT NVL(SAL_ONHOLD,'N'),SAL_DAYS,EMP_ID||'#'||EMP_ID FROM HRMS_SALARY_"
				+ year + "   WHERE SAL_LEDGER_CODE=" + ledgerCode;
		HashMap<String, Object[][]> salMap = getSqlModel().getSingleResultMap(
				salaryQuery, 2, 2);

		// GET ATTENDANCE INFORMATION
		String attnQuery = "SELECT ATTN_EMP_ID,ATTN_SAL_DAYS,NVL(EMP_ONHOLD_FLAG,'N'),ATTN_EMP_ID||'#'||ATTN_EMP_ID FROM HRMS_MONTH_ATTENDANCE_"
				+ year + "  WHERE ATTN_CODE=" + ledgerCode;
		HashMap<String, Object[][]> attnMap = getSqlModel().getSingleResultMap(
				attnQuery, 3, 2);

		String arrearLedgerCode = "0";
		String ledgerQuery = "SELECT ARREARS_CODE,ARREARS_BRANCH,ARREARS_DEPARTMENT,ARREARS_EMPTYPE,ARREARS_PAYBILL FROM HRMS_ARREARS_LEDGER"
				+ "  WHERE ARREARS_PAY_IN_SAL='Y' AND ARREARS_STATUS='L'  AND ARREARS_PAID_MONTH="
				+ month + " AND ARREARS_PAID_YEAR=" + year + "  ";
		if (!listOfFilters[4].equals("")) {
			ledgerQuery += " AND ARREARS_DIVISION=  " + listOfFilters[4];
		}
		if (!listOfFilters[0].equals("")) {
			ledgerQuery += " AND ARREARS_BRANCH=  " + listOfFilters[0];
		}
		if (!listOfFilters[1].equals("")) {
			ledgerQuery += " AND ARREARS_DEPARTMENT=  " + listOfFilters[1];
		}
		if (!listOfFilters[2].equals("")) {
			ledgerQuery += " AND ARREARS_PAYBILL=  " + listOfFilters[2];
		}
		if (!listOfFilters[3].equals("")) {
			ledgerQuery += " AND ARREARS_EMPTYPE=  " + listOfFilters[3];
		}
		Object[][] ledgerObj = getSqlModel().getSingleResult(ledgerQuery);
		if (ledgerObj != null && ledgerObj.length > 0) {
			for (int i = 0; i < ledgerObj.length; i++) {
				arrearLedgerCode += "," + String.valueOf(ledgerObj[i][0]);
			}
		}
		String managerOnHoldquery = "SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,ARREARS_DAYS  ,NVL(ARREARS_NET_AMT,0),ARREARS_MONTH,ARREARS_YEAR  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_ARREARS_"
				+ year
				+ " ON(HRMS_ARREARS_"
				+ year
				+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID )"
				+ "	WHERE ARREARS_PAY_TYPE='O' AND ARREARS_CODE IN("
				+ arrearLedgerCode
				+ ")  ORDER BY  ARREARS_MONTH DESC,EMP_TOKEN ";
		Object[][] managerOnHoldObj = getSqlModel().getSingleResult(
				managerOnHoldquery);

		String arrearQuery = "SELECT  EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,SUM(NVL(ARREARS_NET_AMT,0))  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_ARREARS_"
				+ year
				+ " ON(HRMS_ARREARS_"
				+ year
				+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID ) "
				+ "	WHERE (ARREARS_PAY_TYPE !='O' OR ARREARS_PAY_TYPE IS NULL ) AND ARREARS_CODE IN("
				+ arrearLedgerCode
				+ ") GROUP BY EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ";
		Object[][] arrearObj = getSqlModel().getSingleResult(arrearQuery);

		bean.setProcessedRecord("0");
		bean.setOnHoldRecord("0");
		bean.setStatusChangeRecord("0");
		bean.setZeroSalaryRecord("0");
		bean.setOnHoldPrevRecord("0");
		bean.setArrearsRecord("0");
		bean.setArrearsFlag("false");
		// set employee arrear count
		if (arrearObj != null && arrearObj.length > 0) {
			bean.setArrearsRecord(String.valueOf(arrearObj.length));
			bean.setArrearsFlag("true");
		}
		// SET PREVIOUS ONHOLD EMPLOYEE COUNT
		if (managerOnHoldObj != null && managerOnHoldObj.length > 0) {
			bean.setOnHoldPrevRecord(String.valueOf(managerOnHoldObj.length));
		}
		if (ledObj != null && ledObj.length > 0) {
			bean.setProcessedRecord(String.valueOf(ledObj.length));
			int onholdCOunt = 0;
			int zeroSalDayCOunt = 0;
			int statusChangeCOunt = 0;
			String onHoldEmp = "0";
			String zeroSalDaysEmp = "0";
			String statusChangeEmp = "0";
			String totalEmpployee = "0";
			for (int i = 0; i < ledObj.length; i++) {

				totalEmpployee += "," + String.valueOf(ledObj[i][2]);
				// ONHOLD EMPLOYEE
				if (String.valueOf(ledObj[i][0]).equals("Y")) {
					onholdCOunt++;
					onHoldEmp += "," + String.valueOf(ledObj[i][2]);
				}
				// ZERO SALARY EMPLOYEE
				if (String.valueOf(ledObj[i][1]).equals("0")) {
					zeroSalDayCOunt++;
					zeroSalDaysEmp += "," + String.valueOf(ledObj[i][2]);
				}
				// STATUS CHANGE EMPLOYEE

				// MISC SALARY STATUS CHANGE EMPLOYEE
				if (String.valueOf(ledObj[i][3]).equals("Y")) {
					statusChangeCOunt++;
					statusChangeEmp += "," + String.valueOf(ledObj[i][2]);
				}
			}

			/**
			 * get information from attendance
			 */

			if (attnMap != null && attnMap.size() > 0) {
				for (Iterator iterator = attnMap.keySet().iterator(); iterator
						.hasNext();) {
					String strData = (String) iterator.next();
					// if(strData!=null ){
					Object[][] object = attnMap.get(strData);
					if (object != null && object.length > 0) {
						String attnSalDays = String.valueOf(object[0][1]);
						String sayDays = "";
						String sayOnHold = "";
						String attnOnHold = String.valueOf(object[0][2]);
						String key = String.valueOf(object[0][3]);
						if (salMap != null && salMap.size() > 0) {
							Object[][] salObj = salMap.get(key);
							if (salObj != null && salObj.length > 0) {
								sayDays = String.valueOf(salObj[0][1]);
								sayOnHold = String.valueOf(salObj[0][0]);
							}/*
								 * else{ statusChangeCOunt++;
								 * statusChangeEmp+=","+String.valueOf(object[0][0]); }
								 */
						}
						if (!attnSalDays.equals(sayDays)) {
							statusChangeCOunt++;
							statusChangeEmp += ","
									+ String.valueOf(object[0][0]);
						}	
					}	
				}
			}
			bean.setOnHoldRecord(String.valueOf(onholdCOunt));
			bean.setZeroSalaryRecord(String.valueOf(zeroSalDayCOunt));
			bean.setStatusChangeRecord(String.valueOf(statusChangeCOunt));
			bean.setOnHoldEmp(onHoldEmp);
			bean.setZeroDays(zeroSalDaysEmp);
			bean.setStatusChange(statusChangeEmp);
			bean.setTotalEmp(totalEmpployee);
			bean.setZeroDaysFlag("false");
			if (zeroSalDayCOunt > 0) {
				bean.setZeroDaysFlag("true");
			}
			bean.setStatusChangeFlag("false");
			if (statusChangeCOunt > 0) {
				bean.setStatusChangeFlag("true");
			}

		}
	}

	/**
	 * Purpose : this method is used to show employee saved records
	 * @param bean
	 */
	public void showEmpRecord(SalaryProcess bean) {
		String month = bean.getMonth();
		String year = bean.getYear();
		String ledgerCode = bean.getLedgerCode();
		String empCode = bean.getEditEmpCode();
		/**
		 * SET HOLD AND ONHOLD FLAG
		 */
		bean.setOnHoldFlag("false");
		String onHoldEmp = bean.getOnHoldEmp();
		if (onHoldEmp.contains(",")) {
			String[] str = onHoldEmp.split(",");
			for (int i = 0; i < str.length; i++) {
				if (empCode.equals(str[i])) {
					bean.setOnHoldFlag("true");
				}
			}
		}

		Object[][] salaryFilters = getPayrollConfig();
		int creditTotalRound = 0;
		int debitTotalRound = 0;
		int netPayRound = 0;
		try {
			creditTotalRound = Integer.parseInt(String
					.valueOf(salaryFilters[0][12]));
			debitTotalRound = Integer.parseInt(String
					.valueOf(salaryFilters[0][13]));
			netPayRound = Integer
					.parseInt(String.valueOf(salaryFilters[0][14]));
		} catch (Exception e) {
			// TODO: handle exception
		}
		/**
		 * SET SALARY DAYS
		 */
		String selQuery = "SELECT NVL(SAL_DAYS,0) FROM HRMS_SALARY_" + year
				+ " WHERE EMP_ID=" + empCode + " AND SAL_LEDGER_CODE="
				+ ledgerCode + "";
		Object[][] salObj = getSqlModel().getSingleResult(selQuery);
		if (salObj != null && salObj.length > 0) {
			bean.setEditEmpSalaryDays(String.valueOf(salObj[0][0]));
		}
		bean.setEmpList(null);
		double empcreditTotalAmt = 0.0;
		double empdebitTotalAmt = 0.0;
		/**
		 * SET EMPLOYEE CREDIT DETAILS
		 */
		String creditQuery = "SELECT SAL_CREDIT_CODE,CREDIT_NAME,NVL(SAL_AMOUNT,0) FROM HRMS_SAL_CREDITS_"
				+ year
				+ " "
				+ "	 INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_SAL_CREDITS_"
				+ year
				+ ".SAL_CREDIT_CODE) "
				+ "	 WHERE SAL_LEDGER_CODE="
				+ ledgerCode
				+ " AND  EMP_ID="
				+ empCode
				+ "  ORDER BY CREDIT_PRIORITY,SAL_CREDIT_CODE  ";
		Object[][] obj = getSqlModel().getSingleResult(creditQuery);
		if (obj != null && obj.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < obj.length; i++) {
				SalaryProcess subBean = new SalaryProcess();
				subBean.setEmpcreditCode(String.valueOf(obj[i][0]));
				subBean.setEmpcreditName(String.valueOf(obj[i][1]));
				subBean.setEmpcreditAmt(formatter.format(Double
						.parseDouble(String.valueOf(obj[i][2]))));
				empcreditTotalAmt += Double.parseDouble(String
						.valueOf(obj[i][2]));
				list.add(subBean);
			}
			bean.setEmpList(list);
		}

		/**
		 * SET EMPLOYEE DEBITS
		 */
		bean.setEmpDebitList(null);
		/**
		 * SET EMPLOYEE CREDIT DETAILS
		 */
		String debitQuery = "SELECT SAL_DEBIT_CODE,DEBIT_NAME,NVL(SAL_AMOUNT,0) FROM HRMS_SAL_DEBITS_"
				+ year
				+ " "
				+ "	 INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_SAL_DEBITS_"
				+ year
				+ ".SAL_DEBIT_CODE) "
				+ "	 WHERE SAL_LEDGER_CODE="
				+ ledgerCode
				+ " AND  EMP_ID="
				+ empCode
				+ " ORDER BY DEBIT_PRIORITY,SAL_DEBIT_CODE";
		Object[][] debitobj = getSqlModel().getSingleResult(debitQuery);
		if (debitobj != null && debitobj.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < debitobj.length; i++) {
				SalaryProcess subBean = new SalaryProcess();
				subBean.setEmpdebitCode(String.valueOf(debitobj[i][0]));
				subBean.setEmpdebitName(String.valueOf(debitobj[i][1]));
				subBean.setEmpdebitAmt(formatter.format(Double
						.parseDouble(String.valueOf(debitobj[i][2]))));
				empdebitTotalAmt += Double.parseDouble(String
						.valueOf(debitobj[i][2]));
				list.add(subBean);
			}
			bean.setEmpDebitList(list);
		}
		empdebitTotalAmt = roundCheck(debitTotalRound, empdebitTotalAmt);
		empcreditTotalAmt = roundCheck(creditTotalRound, empcreditTotalAmt);
		bean.setEmpdebitTotalAmt(formatter.format(empdebitTotalAmt));
		bean.setEmpcreditTotalAmt(formatter.format(empcreditTotalAmt));
		// bean.setEmpcreditTotalAmt(formatter.format(Double.parseDouble(String.valueOf(empcreditTotalAmt))));
		bean.setNetSalary(String.valueOf(0));
		if (empcreditTotalAmt > empdebitTotalAmt) {
			// bean.setNetSalary(formatter.format(Double.parseDouble(String.valueOf(empcreditTotalAmt-empdebitTotalAmt))));

			bean.setNetSalary(formatter.format(roundCheck(netPayRound,
					(empcreditTotalAmt - empdebitTotalAmt))));
		}
	}

	/**
	 * Purpose : this method is used to show employee salary credit/debit
	 * details
	 * @param bean
	 * @param request
	 * @return
	 */
	public void showEmpRecalRecord(SalaryProcess bean) {
		String empCode = bean.getEditEmpCode();
		/**
		 * SET HOLD AND ONHOLD FLAG
		 */
		bean.setOnHoldFlag("false");
		String onHoldEmp = bean.getOnHoldEmp();
		if (onHoldEmp.contains(",")) {
			String[] str = onHoldEmp.split(",");
			for (int i = 0; i < str.length; i++) {
				if (empCode.equals(str[i])) {
					bean.setOnHoldFlag("true");
				}
			}
		}

		Object[][] salaryFilters = getPayrollConfig();
		int creditTotalRound = 0;
		int debitTotalRound = 0;
		int netPayRound = 0;
		try {
			creditTotalRound = Integer.parseInt(String
					.valueOf(salaryFilters[0][12]));
			debitTotalRound = Integer.parseInt(String
					.valueOf(salaryFilters[0][13]));
			netPayRound = Integer
					.parseInt(String.valueOf(salaryFilters[0][14]));
		} catch (Exception e) {
			// TODO: handle exception
		}
		/**
		 * SET SALARY DAYS
		 */
		// String selQuery="SELECT NVL(SAL_DAYS,0) FROM HRMS_SALARY_"+year+"
		// WHERE EMP_ID="+empCode+" AND SAL_LEDGER_CODE="+ledgerCode+"";
		if (recalSalMap != null && recalSalMap.size() > 0) {
			String salObj = recalSalMap.get(empCode);
			if (salObj != null && salObj.length() > 0) {
				bean.setEditEmpSalaryDays(String.valueOf(salObj));
			}
		}

		bean.setEmpList(null);
		double empcreditTotalAmt = 0.0;
		double empdebitTotalAmt = 0.0;
		/**
		 * SET EMPLOYEE CREDIT DETAILS
		 */
		String creditQuery = " SELECT HRMS_CREDIT_HEAD.CREDIT_CODE,NVL(CREDIT_NAME,' ') FROM  HRMS_CREDIT_HEAD WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_PRIORITY,CREDIT_CODE  ";
		Object[][] obj = getSqlModel().getSingleResult(creditQuery);
		if (obj != null && obj.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < obj.length; i++) {
				SalaryProcess subBean = new SalaryProcess();
				subBean.setEmpcreditCode(String.valueOf(obj[i][0]));
				subBean.setEmpcreditName(String.valueOf(obj[i][1]));
				if (recalCreditMap != null && recalCreditMap.size() > 0) {
					String key = empCode + "#" + String.valueOf(obj[i][0]);
					String recalCreditAmt = recalCreditMap.get(key);
					if (recalCreditAmt != null && recalCreditAmt.length() > 0) {
						subBean.setEmpcreditAmt(formatter.format(Double
								.parseDouble(recalCreditAmt)));
						empcreditTotalAmt += Double.parseDouble(recalCreditAmt);
					} else {
						recalCreditAmt = "0";
						subBean.setEmpcreditAmt(formatter.format(Double
								.parseDouble(recalCreditAmt)));
						empcreditTotalAmt += Double.parseDouble(recalCreditAmt);
					}
				}

				list.add(subBean);
			}
			bean.setEmpList(list);
		}

		/**
		 * SET EMPLOYEE DEBITS
		 */
		bean.setEmpDebitList(null);
		/**
		 * SET EMPLOYEE CREDIT DETAILS
		 */
		String debitQuery = " SELECT HRMS_DEBIT_HEAD.DEBIT_CODE,NVL(DEBIT_NAME,' ') FROM  HRMS_DEBIT_HEAD where HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' order BY DEBIT_PRIORITY,DEBIT_CODE ";
		Object[][] debitobj = getSqlModel().getSingleResult(debitQuery);
		if (debitobj != null && debitobj.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < debitobj.length; i++) {
				SalaryProcess subBean = new SalaryProcess();
				subBean.setEmpdebitCode(String.valueOf(debitobj[i][0]));
				subBean.setEmpdebitName(String.valueOf(debitobj[i][1]));
				if (recalDebitMap != null && recalDebitMap.size() > 0) {
					String key = empCode + "#" + String.valueOf(debitobj[i][0]);
					String recalCreditAmt = recalDebitMap.get(key);
					if (recalCreditAmt != null && recalCreditAmt.length() > 0) {
						subBean.setEmpdebitAmt(formatter.format(Double
								.parseDouble(recalCreditAmt)));
						empdebitTotalAmt += Double.parseDouble(recalCreditAmt);
					} else {
						recalCreditAmt = "0";
						subBean.setEmpdebitAmt(formatter.format(Double
								.parseDouble(recalCreditAmt)));
						empdebitTotalAmt += Double.parseDouble(recalCreditAmt);
					}
				}

				list.add(subBean);
			}
			bean.setEmpDebitList(list);
		}
		empdebitTotalAmt = roundCheck(debitTotalRound, empdebitTotalAmt);
		empcreditTotalAmt = roundCheck(creditTotalRound, empcreditTotalAmt);
		bean.setEmpdebitTotalAmt(formatter.format(empdebitTotalAmt));
		bean.setEmpcreditTotalAmt(formatter.format(empcreditTotalAmt));
		// bean.setEmpcreditTotalAmt(formatter.format(Double.parseDouble(String.valueOf(empcreditTotalAmt))));
		bean.setNetSalary(String.valueOf(0));
		if (empcreditTotalAmt > empdebitTotalAmt) {
			// bean.setNetSalary(formatter.format(Double.parseDouble(String.valueOf(empcreditTotalAmt-empdebitTotalAmt))));
			bean.setNetSalary(formatter.format(roundCheck(netPayRound,
					(empcreditTotalAmt - empdebitTotalAmt))));
		}
	}

	/**
	 * Purpose : this method is used to save employee credit/debit details
	 * @param bean
	 * @return boolean
	 */
	public boolean saveEmpRecord(SalaryProcess bean, HttpServletRequest request) {
		String[] creditEmp = request.getParameterValues("empcreditAmt");
		String[] creditCode = request.getParameterValues("empcreditCode");
		String[] debitEmp = request.getParameterValues("empdebitAmt");
		String[] empdebitCode = request.getParameterValues("empdebitCode");
		String empCode = bean.getEditEmpCode();
		String month = bean.getMonth();
		String year = bean.getYear();
		String ledgerCode = bean.getLedgerCode();
		String salDays = bean.getEditEmpSalaryDays();
		boolean creditFlag = false;
		if (creditEmp != null && creditEmp.length > 0) {
			Object[][] creditObj = new Object[creditEmp.length][6];
			for (int i = 0; i < creditEmp.length; i++) {
				creditObj[i][0] = empCode;// EMP CODE
				creditObj[i][1] = creditCode[i];// DEBIT CODE
				creditObj[i][2] = checkNull_Zero(creditEmp[i]);// SAL AMOUNT
				creditObj[i][3] = month;// SAL MONTH
				creditObj[i][4] = year;// SAL YEAR
				creditObj[i][5] = ledgerCode;// LEDGER CODE
			}
			String delCreditQuery = "DELETE FROM HRMS_SAL_CREDITS_" + year
					+ " WHERE EMP_ID=" + empCode + " AND SAL_LEDGER_CODE="
					+ ledgerCode;
			creditFlag = getSqlModel().singleExecute(delCreditQuery);
			String creditQuery = "INSERT INTO HRMS_SAL_CREDITS_"
					+ year
					+ "(EMP_ID, SAL_CREDIT_CODE, SAL_AMOUNT, SAL_MONTH, SAL_YEAR, SAL_LEDGER_CODE) VALUES(?,?,?,?,?,?)";
			if (creditFlag)
				creditFlag = getSqlModel()
						.singleExecute(creditQuery, creditObj);
		}
		boolean debitFlag = false;
		if (debitEmp != null && debitEmp.length > 0) {
			Object[][] debitObj = new Object[debitEmp.length][6];
			for (int i = 0; i < debitEmp.length; i++) {
				debitObj[i][0] = empCode;// EMP CODE
				debitObj[i][1] = empdebitCode[i];// DEBIT CODE
				debitObj[i][2] = checkNull_Zero(debitEmp[i]);// SAL AMOUNT
				debitObj[i][3] = month;// SAL MONTH
				debitObj[i][4] = year;// SAL YEAR
				debitObj[i][5] = ledgerCode;// LEDGER CODE
			}
			String delDebitQuery = "DELETE FROM HRMS_SAL_DEBITS_" + year
					+ " WHERE EMP_ID=" + empCode + " AND SAL_LEDGER_CODE="
					+ ledgerCode;
			creditFlag = getSqlModel().singleExecute(delDebitQuery);
			String creditQuery = "INSERT INTO HRMS_SAL_DEBITS_"
					+ year
					+ "(EMP_ID, SAL_DEBIT_CODE, SAL_AMOUNT, SAL_MONTH, SAL_YEAR, SAL_LEDGER_CODE) VALUES(?,?,?,?,?,?)";
			if (creditFlag)
				creditFlag = getSqlModel().singleExecute(creditQuery, debitObj);
		}

		/*
		 * UPDATE TOTAL CREDITS AND TOTAL DEBITS
		 */
		double netSal = 0.0;
		double totalCredit = 0.0;
		double totalDebit = 0.0;
		totalCredit = Double.parseDouble(bean.getEmpcreditTotalAmt());
		totalDebit = Double.parseDouble(bean.getEmpdebitTotalAmt());
		if (totalCredit > totalDebit) {
			netSal = totalCredit - totalDebit;
		}
		String updateQuery = "UPDATE HRMS_SALARY_"
				+ year
				+ " SET SAL_TOTAL_CREDIT=?,SAL_TOTAL_DEBIT=?, SAL_NET_SALARY=?,SAL_DAYS=? WHERE "
				+ "	SAL_MONTH=? AND SAL_YEAR=? AND EMP_ID=?";
		if (bean.getEmpRecalculateSalFlag().equals("Y")) {
			updateQuery = "UPDATE HRMS_SALARY_"
					+ year
					+ " SET SAL_TOTAL_CREDIT=?,SAL_TOTAL_DEBIT=?, SAL_NET_SALARY=?,SAL_DAYS=?,EMP_MISC_UPLOAD_FLAG='N' WHERE "
					+ "	SAL_MONTH=? AND SAL_YEAR=? AND EMP_ID=?";
		}
		Object[][] data = new Object[1][7];
		data[0][0] = totalCredit;
		data[0][1] = totalDebit;
		data[0][2] = netSal;
		data[0][3] = salDays;
		data[0][4] = month;
		data[0][5] = year;
		data[0][6] = empCode;
		getSqlModel().singleExecute(updateQuery, data);

		return creditFlag;
	}

	/**
	 * Purpose : this method is used to manage current salary on hold employee
	 * @param bean
	 * @return boolean
	 */
	public void manageCurrentOnHold(SalaryProcess bean) {
		String totalEmpId = bean.getOnHoldEmp();
		String year = bean.getYear();
		String ledgerCOde = bean.getLedgerCode();
		String query = " SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,NVL(SAL_DAYS,0),NVL(ATTN_SAL_DAYS,0) "
				+ " ,NVL(SAL_NET_SALARY,0) FROM HRMS_EMP_OFFC "
				+ "  INNER JOIN HRMS_SALARY_"
				+ year
				+ " ON(HRMS_SALARY_"
				+ year
				+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SAL_LEDGER_CODE="
				+ ledgerCOde
				+ " ) "
				+ "   LEFT JOIN HRMS_MONTH_ATTENDANCE_"
				+ year
				+ " ON(HRMS_MONTH_ATTENDANCE_"
				+ year
				+ ".ATTN_EMP_ID=HRMS_SALARY_"
				+ year
				+ ".EMP_ID AND ATTN_CODE="
				+ ledgerCOde
				+ " ) "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID IN("
				+ totalEmpId + ")  " + " ORDER BY UPPER(ENAME) ";
		Object[][] obj = getSqlModel().getSingleResult(query);
		bean.setEmpOnholdList(null);
		if (obj != null && obj.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < obj.length; i++) {
				SalaryProcess subBean = new SalaryProcess();
				subBean.setOnholdEmpID(String.valueOf(obj[i][0]));
				subBean.setOnholdEmpToken(String.valueOf(obj[i][1]));
				subBean.setOnholdEmpName(String.valueOf(obj[i][2]));
				subBean.setOnholdSalaryDays(String.valueOf(obj[i][3]));
				subBean.setOnholdNetSalary(String.valueOf(obj[i][5]));
				list.add(subBean);
			}
			bean.setEmpOnholdList(list);
		}

	}

	/**
	 * Purpose : this method is used to manage previous onhold employee user can
	 * add previou on hold employee in current salary process
	 * @param bean
	 * @param month
	 * @param year
	 */
	public void managePreviousOnHold(SalaryProcess bean, String month,
			String year) {
		String[] listOfFilters = new String[5];
		listOfFilters[0] = bean.getBranchId();
		listOfFilters[1] = bean.getDepartmentId();
		listOfFilters[2] = bean.getPayBillId();
		listOfFilters[3] = bean.getEmployeeTypeId();
		listOfFilters[4] = bean.getDivisionId();
		String arrearLedgerCode = "0";
		String ledgerQuery = "SELECT ARREARS_CODE,ARREARS_BRANCH,ARREARS_DEPARTMENT,ARREARS_EMPTYPE,ARREARS_PAYBILL FROM HRMS_ARREARS_LEDGER"
				+ "  WHERE ARREARS_PAID_MONTH="
				+ month
				+ " AND ARREARS_PAY_TYPE='O' AND ARREARS_PAID_YEAR="
				+ year
				+ "  ";
		if (!listOfFilters[4].equals("")) {
			ledgerQuery += " AND ARREARS_DIVISION=  " + listOfFilters[4];
		}
		if (!listOfFilters[0].equals("")) {
			ledgerQuery += " AND ARREARS_BRANCH=  " + listOfFilters[0];
		}
		if (!listOfFilters[1].equals("")) {
			ledgerQuery += " AND ARREARS_DEPARTMENT=  " + listOfFilters[1];
		}
		if (!listOfFilters[2].equals("")) {
			ledgerQuery += " AND ARREARS_PAYBILL=  " + listOfFilters[2];
		}
		if (!listOfFilters[3].equals("")) {
			ledgerQuery += " AND ARREARS_EMPTYPE=  " + listOfFilters[3];
		}
		Object[][] ledgerObj = getSqlModel().getSingleResult(ledgerQuery);
		if (ledgerObj != null && ledgerObj.length > 0) {
			for (int i = 0; i < ledgerObj.length; i++) {
				arrearLedgerCode += "," + String.valueOf(ledgerObj[i][0]);
			}
		}

		String query = "SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,ARREARS_DAYS  ,NVL(ARREARS_NET_AMT,0),ARREARS_MONTH,ARREARS_YEAR  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_ARREARS_"
				+ year
				+ " ON(HRMS_ARREARS_"
				+ year
				+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID )"
				+ "	WHERE ARREARS_PAY_TYPE='O' AND ARREARS_CODE IN("
				+ arrearLedgerCode
				+ ")  ORDER BY  ARREARS_MONTH DESC,EMP_TOKEN ";
		Object[][] obj = getSqlModel().getSingleResult(query);

		bean.setEmpOnholdList(null);
		if (obj != null && obj.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < obj.length; i++) {
				SalaryProcess subBean = new SalaryProcess();
				subBean.setOnholdEmpID(String.valueOf(obj[i][0]));
				subBean.setOnholdEmpToken(String.valueOf(obj[i][1]));
				subBean.setOnholdEmpName(String.valueOf(obj[i][2]));
				subBean.setOnholdSalaryDays(String.valueOf(obj[i][3]));
				subBean.setOnholdNetSalary(String.valueOf(obj[i][4]));
				String submonth = Utility.month(Integer.parseInt(String
						.valueOf(obj[i][5])));
				submonth = submonth.substring(0, 3);
				subBean.setOnholdMonthYear(submonth + "-" + year);
				subBean.setOnholdMonth(String.valueOf(obj[i][5]));
				subBean.setOnholdYear(String.valueOf(obj[i][6]));
				list.add(subBean);
			}
			bean.setEmpOnholdList(list);
		}

	}

	/**
	 * Purpose : this method is used to remove/clear on hold employee
	 * @param bean
	 * @return boolean
	 */
	public boolean removeOnHoldEmp(SalaryProcess bean,
			HttpServletRequest request) {
		boolean result = false;
		String totalEmpId = bean.getOnHoldEmp();
		String year = bean.getYear();
		String month = bean.getMonth();
		String ledgerCOde = bean.getLedgerCode();
		String[] code = request.getParameterValues("onholdCheckBoxH");
		String empCode[] = request.getParameterValues("onholdEmpID");
		if (code != null && code.length > 0) {
			Object[][] obj = new Object[code.length][2];
			Object[][] uploadObj = new Object[code.length][2];
			for (int i = 0; i < code.length; i++) {
				obj[i][0] = "Y";
				uploadObj[i][0] = "O";
				if (String.valueOf(code[i]).equals("Y")) {
					obj[i][0] = "N";
					uploadObj[i][0] = "";
				}
				obj[i][1] = empCode[i];
				uploadObj[i][1] = empCode[i];
			}
			String query = "UPDATE HRMS_SALARY_" + year
					+ " SET SAL_ONHOLD=? WHERE SAL_MONTH=" + month
					+ " AND SAL_YEAR=" + year + " AND SAL_LEDGER_CODE="
					+ ledgerCOde + " and EMP_ID=?";
			result = getSqlModel().singleExecute(query, obj);
			String updateAttnProcess = "UPDATE HRMS_MONTH_ATTENDANCE_" + year
					+ " SET EMP_ONHOLD_FLAG=? WHERE  ATTN_MONTH=" + month
					+ " AND ATTN_YEAR=" + year + " and ATTN_EMP_ID=?";
			getSqlModel().singleExecute(updateAttnProcess, obj);
			String updateAttnUpload = "UPDATE HRMS_UPLOAD_ATTENDANCE_" + year
					+ " SET ATT_EMP_STATUS=? WHERE  ATTN_MONTH=" + month
					+ " AND ATTN_YEAR=" + year + " and ATTN_EMP_ID=?";
			getSqlModel().singleExecute(updateAttnUpload, uploadObj);

		}
		return result;
	}

	/**
	 * Purpose : this method is used to add previous onhold employee
	 * @param bean
	 * @return boolean
	 */
	public boolean addOnHoldPrevEmp(SalaryProcess bean,
			HttpServletRequest request) {
		boolean result = false;
		String totalEmpId = bean.getOnHoldEmp();
		String year = bean.getYear();
		String month = bean.getMonth();
		String ledgerCOde = bean.getLedgerCode();
		/**
		 * 
		 */
		String onHoldMonth = bean.getMonthOnHold();
		String onholdYear = bean.getYearOnHold();
		String onholdEmpId = bean.getEditEmpCode();
		String[] listOfFilters = new String[5];
		listOfFilters[0] = bean.getBranchId();
		listOfFilters[1] = bean.getDepartmentId();
		listOfFilters[2] = bean.getPayBillId();
		listOfFilters[3] = bean.getEmployeeTypeId();
		listOfFilters[4] = bean.getDivisionId();
		String arrearLedgerCode = "";

		String ledgerQuery = "SELECT ARREARS_CODE,ARREARS_BRANCH,ARREARS_DEPARTMENT,ARREARS_EMPTYPE,ARREARS_PAYBILL FROM HRMS_ARREARS_LEDGER"
				+ "  WHERE ARREARS_PAY_IN_SAL='Y' AND ARREARS_PAY_TYPE='O' AND  ARREARS_PAID_MONTH="
				+ month + " AND ARREARS_PAID_YEAR=" + year + "  ";
		if (!listOfFilters[4].equals("")) {
			ledgerQuery += " AND ARREARS_DIVISION=  " + listOfFilters[4];
		}
		if (!listOfFilters[0].equals("")) {
			ledgerQuery += " AND ARREARS_BRANCH=  " + listOfFilters[0];
		}
		if (!listOfFilters[1].equals("")) {
			ledgerQuery += " AND ARREARS_DEPARTMENT=  " + listOfFilters[1];
		}
		if (!listOfFilters[2].equals("")) {
			ledgerQuery += " AND ARREARS_PAYBILL=  " + listOfFilters[2];
		}
		if (!listOfFilters[3].equals("")) {
			ledgerQuery += " AND ARREARS_EMPTYPE=  " + listOfFilters[3];
		}
		Object[][] ledgerObj = getSqlModel().getSingleResult(ledgerQuery);
		if (ledgerObj != null && ledgerObj.length > 0) {
			arrearLedgerCode = String.valueOf(ledgerObj[0][0]);
		} else {
			ledgerQuery = "SELECT NVL(MAX(ARREARS_CODE),0)+1 FROM HRMS_ARREARS_LEDGER";
			ledgerObj = getSqlModel().getSingleResult(ledgerQuery);
			arrearLedgerCode = String.valueOf(ledgerObj[0][0]);
			Object[][] obj = new Object[1][10];
			obj[0][0] = arrearLedgerCode;
			obj[0][1] = month;
			obj[0][2] = year;
			obj[0][3] = listOfFilters[4];// div
			obj[0][4] = listOfFilters[0];// BRANCH
			obj[0][5] = listOfFilters[1];// DEPT
			obj[0][6] = listOfFilters[3];// EMPTYPE
			obj[0][7] = listOfFilters[2];// PAYBILL
			obj[0][8] = month;// paid month
			obj[0][9] = year;// paid year
			String insert = "INSERT INTO HRMS_ARREARS_LEDGER "
					+ "	(ARREARS_CODE,ARREARS_REF_MONTH,ARREARS_REF_YEAR,ARREARS_STATUS,ARREARS_PROCESS_DATE,ARREARS_DIVISION,ARREARS_BRANCH"
					+ "	,ARREARS_DEPARTMENT,ARREARS_EMPTYPE,ARREARS_PAYBILL,ARREARS_TYPE,ARREARS_PAY_IN_SAL,ARREARS_PAY_TYPE,ARREARS_PAID_MONTH,ARREARS_PAID_YEAR) "
					+ "	VALUES(?,?,?,'L',SYSDATE,?,?,?,?,?,'M','Y','O',?,?)";
			getSqlModel().singleExecute(insert, obj);

		}
		// FOR SALARY
		String salaryQUery = "SELECT EMP_ID," + onHoldMonth + ", " + onholdYear
				+ ",SAL_DAYS," + arrearLedgerCode
				+ ",SAL_NET_SALARY,SAL_TOTAL_CREDIT,SAL_TOTAL_DEBIT,'N','O' "
				+ " FROM HRMS_SALARY_" + onholdYear + " WHERE SAL_MONTH="
				+ onHoldMonth + " AND  EMP_ID	  =" + onholdEmpId;
		Object[][] salObj = getSqlModel().getSingleResult(salaryQUery);
		String salaryInsertQuery = " INSERT INTO  HRMS_ARREARS_"
				+ year
				+ "(EMP_ID, ARREARS_MONTH, ARREARS_YEAR, ARREARS_DAYS, ARREARS_CODE, ARREARS_NET_AMT, ARREARS_CREDITS_AMT, ARREARS_DEBITS_AMT, ARREARS_ONHOLD,ARREARS_PAY_TYPE) "
				+ "	VALUES(?,?,?,?,?,?,?,?,?,?)";
		getSqlModel().singleExecute(salaryInsertQuery, salObj);

		// FOR CREDITS
		String creditQUery = "SELECT " + arrearLedgerCode
				+ ",EMP_ID,SAL_CREDIT_CODE," + onHoldMonth + ",SAL_AMOUNT,"
				+ onholdYear + ",'O' FROM HRMS_SAL_CREDITS_" + onholdYear
				+ " WHERE SAL_MONTH=" + onHoldMonth + " AND EMP_ID= "
				+ onholdEmpId;
		Object[][] creditObj = getSqlModel().getSingleResult(creditQUery);
		String creditInsert = "INSERT INTO  HRMS_ARREARS_CREDIT_"
				+ year
				+ " (ARREARS_CODE,ARREARS_EMP_ID,ARREARS_CREDIT_CODE,ARREARS_MONTH,ARREARS_AMT,ARREARS_YEAR,ARREARS_PAY_TYPE) VALUES(?,?,?,?,?,?,?)";
		getSqlModel().singleExecute(creditInsert, creditObj);

		// FOR DEBITS
		String debitQuery = "SELECT " + arrearLedgerCode
				+ ",EMP_ID,SAL_DEBIT_CODE," + onHoldMonth + ",SAL_AMOUNT,"
				+ onholdYear + ",'O' FROM HRMS_SAL_DEBITS_" + onholdYear + " "
				+ "	WHERE SAL_MONTH=" + onHoldMonth + " AND EMP_ID ="
				+ onholdEmpId;
		Object[][] debitObj = getSqlModel().getSingleResult(debitQuery);
		String debitInsert = "INSERT INTO HRMS_ARREARS_DEBIT_"
				+ year
				+ "	(ARREARS_CODE,ARREARS_EMP_ID,ARREARS_DEBIT_CODE,ARREARS_MONTH,ARREARS_AMT,ARREARS_YEAR,ARREARS_PAY_TYPE) "
				+ "	VALUES(?,?,?,?,?,?,?)";
		getSqlModel().singleExecute(debitInsert, debitObj);

		String query = "UPDATE HRMS_SALARY_" + onholdYear
				+ " SET EMP_RELEASE_ONHOLD_MONTH=" + month
				+ ",EMP_RELEASE_ONHOLD_YEAR=" + year + " WHERE SAL_MONTH="
				+ onHoldMonth + " AND SAL_YEAR=" + onholdYear + " AND  EMP_ID="
				+ onholdEmpId;
		result = getSqlModel().singleExecute(query);
		return result;
	}

	/**
	 * Purpose : this method is used to remove previous added on hold employee
	 * @param year
	 * @return boolean
	 */
	public boolean removeOnHoldPrevEmp(SalaryProcess bean,
			HttpServletRequest request) {
		boolean result = false;
		String year = bean.getYear();
		String month = bean.getMonth();
		String ledgerCOde = bean.getLedgerCode();
		String[] code = request.getParameterValues("onholdCheckBoxH");
		String empCode[] = request.getParameterValues("onholdEmpID");

		String[] onholdMonth = request.getParameterValues("onholdMonth");
		String onHoldYear[] = request.getParameterValues("onholdYear");
		if (code != null && code.length > 0) {
			int totalCOunt = 0;
			for (int j = 0; j < code.length; j++) {
				if (String.valueOf(code[j]).equals("Y")) {
					totalCOunt++;
				}
			}
			Object[][] obj = new Object[1][3];
			int cnt = 0;
			for (int i = 0; i < code.length; i++) {
				if (String.valueOf(code[i]).equals("Y")) {
					// obj[cnt][0]=onHoldYear[i];
					obj[cnt][0] = onholdMonth[i];
					obj[cnt][1] = onHoldYear[i];
					obj[cnt][2] = empCode[i];
					String deleteSal = "DELETE FROM HRMS_ARREARS_"
							+ onHoldYear[i]
							+ " WHERE ARREARS_MONTH=? AND ARREARS_YEAR=?  AND EMP_ID=?   AND ARREARS_PAY_TYPE='O' ";
					getSqlModel().singleExecute(deleteSal, obj);
					String deleteCredit = "DELETE FROM HRMS_ARREARS_CREDIT_"
							+ onHoldYear[i]
							+ " WHERE ARREARS_MONTH=? AND ARREARS_YEAR=? AND ARREARS_EMP_ID=? AND ARREARS_PAY_TYPE='O'";
					getSqlModel().singleExecute(deleteCredit, obj);

					String deleteDebit = "DELETE FROM HRMS_ARREARS_DEBIT_"
							+ onHoldYear[i]
							+ " WHERE ARREARS_MONTH=? AND ARREARS_YEAR=? AND ARREARS_EMP_ID=? AND ARREARS_PAY_TYPE='O'";
					getSqlModel().singleExecute(deleteDebit, obj);
					String query = "UPDATE HRMS_SALARY_"
							+ onHoldYear[i]
							+ " SET EMP_RELEASE_ONHOLD_MONTH=null,EMP_RELEASE_ONHOLD_YEAR=null WHERE SAL_MONTH=? AND SAL_YEAR=? AND  EMP_ID=?";
					result = getSqlModel().singleExecute(query, obj);
				}
			}
		}
		return result;
	}

	/**
	 *   Purpose : this method is used to add remove on hold employee
	 * @param year
	 * @return boolean
	 */

	public boolean onHoldEmpRecord(SalaryProcess bean, String status) {
		String year = bean.getYear();
		String month = bean.getMonth();
		String ledgerCOde = bean.getLedgerCode();
		String empCode = bean.getEditEmpCode();
		String uploadStatus = "";
		if (status.equals("Y")) {
			uploadStatus = "O";
		}
		String query = "UPDATE HRMS_SALARY_" + year + " SET SAL_ONHOLD='"
				+ status + "' WHERE SAL_MONTH=" + month + " AND SAL_YEAR="
				+ year + " and EMP_ID=" + empCode;
		String updateAttnProcess = "UPDATE HRMS_MONTH_ATTENDANCE_" + year
				+ " SET EMP_ONHOLD_FLAG='" + status + "' WHERE  ATTN_MONTH="
				+ month + " AND ATTN_YEAR=" + year + " and ATTN_EMP_ID="
				+ empCode;
		getSqlModel().singleExecute(updateAttnProcess);
		String updateAttnUpload = "UPDATE HRMS_UPLOAD_ATTENDANCE_" + year
				+ " SET ATT_EMP_STATUS='" + uploadStatus
				+ "' WHERE  ATTN_MONTH=" + month + " AND ATTN_YEAR=" + year
				+ " and ATTN_EMP_ID=" + empCode;
		getSqlModel().singleExecute(updateAttnUpload);
		return getSqlModel().singleExecute(query);
	}

	/**
	 * Purpose : this method is used to set on load flags
	 * @param bean
	 * 
	 */
	public void setOnloadData(SalaryProcess bean) {
		String query = "SELECT DECODE(LEDGER_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ATTN_MONTH,  LEDGER_YEAR, TYPE_NAME, PAYBILL_GROUP, DEPT_NAME, CENTER_NAME, DIV_NAME, LEDGER_STATUS,  TYPE_ID,PAYBILL_ID,DEPT_ID,CENTER_ID,DIV_ID, LEDGER_CODE,LEDGER_MONTH,NVL(LEDGER_PROCESS_TYPE,'N')   FROM HRMS_SALARY_LEDGER   LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID =  HRMS_SALARY_LEDGER.LEDGER_EMPTYPE)  LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_SALARY_LEDGER.LEDGER_PAYBILL)  LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =  HRMS_SALARY_LEDGER.LEDGER_DEPT)  LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =  HRMS_SALARY_LEDGER.LEDGER_BRN)  LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_SALARY_LEDGER.LEDGER_DIVISION)  WHERE LEDGER_CODE="
				+ bean.getLedgerCode();
		Object[][] obj = getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {
			bean.setMonth(checkNull_Space(String.valueOf(obj[0][14])));
			bean.setYear(checkNull_Space(String.valueOf(obj[0][1])));
			bean
					.setEmployeeTypeName(checkNull_Space(String
							.valueOf(obj[0][2])));
			bean.setEmployeeTypeId(checkNull_Space(String.valueOf(obj[0][8])));
			bean.setPayBillName(checkNull_Space(String.valueOf(obj[0][3])));
			bean.setPayBillId(checkNull_Space(String.valueOf(obj[0][9])));
			bean.setDepartmentName(checkNull_Space(String.valueOf(obj[0][4])));
			bean.setDepartmentId(checkNull_Space(String.valueOf(obj[0][10])));
			bean.setBranchName(checkNull_Space(String.valueOf(obj[0][5])));
			bean.setBranchId(checkNull_Space(String.valueOf(obj[0][11])));
			bean.setDivisionName(checkNull_Space(String.valueOf(obj[0][6])));
			bean.setDivisionId(checkNull_Space(String.valueOf(obj[0][12])));
			bean.setLedgerStatus(checkNull_Space(String.valueOf(obj[0][7])));
			bean.setUploadSalaryFlag("N"
					+ checkNull_Space(String.valueOf(obj[0][15])));
		}

	}

	/**
	 * Purpose : this method is used to generate xls report for misc salary
	 * uploaded credit/debit
	 * 
	 * @param reportType
	 * @param bean
	 * @param response
	 */
	public void genMiscSalaryUpload(String reportType, SalaryProcess bean,
			HttpServletResponse response) {
		String month = bean.getMonth();
		String divisionName = bean.getDivisionName();
		String year = bean.getYear();
		String[] listOfFilters = new String[5];
		listOfFilters[0] = bean.getBranchId();
		listOfFilters[1] = bean.getDepartmentId();
		listOfFilters[2] = bean.getPayBillId();
		listOfFilters[3] = bean.getEmployeeTypeId();
		listOfFilters[4] = bean.getDivisionId();
		String reportName = "Salary Register_" + divisionName + " for "
				+ Utility.month(Integer.parseInt(month)) + "_" + year;
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				reportType, reportName, "A4");
		rg.setFName(reportName + "." + reportType);
		Object[][] title = new Object[1][3];
		title[0][0] = "";
		title[0][1] = "";
		title[0][2] = "Misc Salary Upload of " + divisionName
				+ " for the month of " + Utility.month(Integer.parseInt(month))
				+ "," + year + " ";

		int[] cols = { 20, 20, 50 };
		int[] align = { 0, 0, 1 };
		rg.tableBodyNoCellBorder(title, cols, align, 1);
		String query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(CREDIT_NAME),SALARY_AMOUNT 	,DECODE(UPLOAD_IS_OVERWRITE,'N','Append','Y','Override') "
				+ "  FROM HRMS_MISC_SALARY_UPLOAD  "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_MISC_SALARY_UPLOAD.EMP_ID) "
				+ "   INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_MISC_SALARY_UPLOAD.SALARY_CODE ) "
				+ "	WHERE MONTH="
				+ month
				+ " AND YEAR="
				+ year
				+ " AND SALARY_AMOUNT>0   "
				+ "   AND  UPLOAD_PAY_TYPE='C'   AND EMP_DIV=  "
				+ listOfFilters[4];
		if (!listOfFilters[0].equals("")) {
			query += " AND EMP_CENTER=  " + listOfFilters[0];
		}
		if (!listOfFilters[1].equals("")) {
			query += " AND EMP_DEPT=  " + listOfFilters[1];
		}
		if (!listOfFilters[2].equals("")) {
			query += " AND EMP_PAYBILL=  " + listOfFilters[2];
		}
		if (!listOfFilters[3].equals("")) {
			query += " AND EMP_TYPE=  " + listOfFilters[3];
		}
		query += " UNION SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(DEBIT_NAME),SALARY_AMOUNT 	,DECODE(UPLOAD_IS_OVERWRITE,'N','Append','Y','Override') "
				+ "  FROM HRMS_MISC_SALARY_UPLOAD  "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_MISC_SALARY_UPLOAD.EMP_ID) "
				+ "   INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_MISC_SALARY_UPLOAD.SALARY_CODE ) "
				+ "	WHERE MONTH="
				+ month
				+ " AND YEAR="
				+ year
				+ " AND SALARY_AMOUNT>0   "
				+ "  AND  UPLOAD_PAY_TYPE='D'  AND EMP_DIV=  "
				+ listOfFilters[4];
		if (!listOfFilters[0].equals("")) {
			query += " AND EMP_CENTER=  " + listOfFilters[0];
		}
		if (!listOfFilters[1].equals("")) {
			query += " AND EMP_DEPT=  " + listOfFilters[1];
		}
		if (!listOfFilters[2].equals("")) {
			query += " AND EMP_PAYBILL=  " + listOfFilters[2];
		}
		if (!listOfFilters[3].equals("")) {
			query += " AND EMP_TYPE=  " + listOfFilters[3];
		}
		Object[][] data = getSqlModel().getSingleResult(query);
		String[] header = { "Employee Id", "Employee Name",
				"Credit/Debit Name", "Amount", "Append/Overide" };
		int width[] = { 20, 30, 15, 20, 10 };
		int alignment[] = { 1, 1, 1, 1, 1 };
		rg.tableBody(header, data, width, alignment);
		rg.createReport(response);
	}
}
