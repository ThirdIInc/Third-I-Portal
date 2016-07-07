/* Bhushan Dasare June 5, 2009 */

package org.paradyne.model.attendance;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.paradyne.bean.attendance.UploadMonthlyAttendance;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.attendance.MonthAttendanceProcessModel;
import org.paradyne.model.leave.LeavePolicyData;

public class UploadMonthlyAttendanceModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(UploadMonthlyAttendanceModel.class);
	private Object[][] leavePolicies = null;

	public Object[][] callAttendance(String month, String year,
			String concatEmployeeIds, String salaryStartFlag, String fromDay) {
		String sql = "";
		String toDate = "";
		String fromDate = "";

		if (salaryStartFlag.equals("P")) {
			int endDate = Integer.parseInt(fromDay);
			endDate -= 1;
			toDate = endDate + "-" + month + "-" + year;
			if (month.equals("1")) {
				String dateYear = String.valueOf(Integer.parseInt(year) - 1);
				fromDate = fromDay + "-12-" + dateYear;
				sql = " SELECT ATT_EMP_ID, SUM(NVL(LATE_MARKS, 0)) AS LATE_MARKS, SUM(NVL(HALF_DAYS, 0)) AS HALF_DAYS FROM ( "
						+ " 	SELECT ATT_EMP_ID, CASE WHEN ATT_STATUS_TWO = 'DL' THEN COUNT(*) * 2 WHEN ATT_STATUS_TWO IN('LC', 'EL') "
						+ "	THEN COUNT(*) END AS LATE_MARKS, CASE WHEN ATT_STATUS_TWO = 'HD' THEN COUNT(*) END AS HALF_DAYS, "
						+ "	TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS LATE_MARK_DATE FROM HRMS_DAILY_ATTENDANCE_"
						+ (Integer.parseInt(year) - 1)
						+ "	WHERE ATT_STATUS_ONE = 'PR' AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY "
						+ "	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS = 'A' AND LEAVE_DAYS = 0.5) "
						+ "	GROUP BY ATT_EMP_ID, ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_DATE "
						+ "	UNION ALL "
						+ "	SELECT ATT_EMP_ID, CASE WHEN ATT_STATUS_TWO = 'DL' THEN COUNT(*) * 2 WHEN ATT_STATUS_TWO IN('LC', 'EL') "
						+ "	THEN COUNT(*) END LATE_MARKS, CASE WHEN ATT_STATUS_TWO = 'HD' THEN COUNT(*) END AS HALF_DAYS, "
						+ "	TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS LATE_MARK_DATE FROM HRMS_DAILY_ATTENDANCE_"
						+ year
						+ "	WHERE ATT_STATUS_ONE = 'PR' AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY "
						+ "	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS = 'A' AND LEAVE_DAYS = 0.5) "
						+ "	GROUP BY ATT_EMP_ID, ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_DATE "
						+ " ) "
						+ " WHERE TO_DATE(LATE_MARK_DATE, 'DD-MM-YYYY') BETWEEN TO_DATE('"
						+ fromDate
						+ "', 'DD-MM-YYYY') "
						+ " AND TO_DATE('"
						+ toDate
						+ "', 'DD-MM-YYYY') "
						+ Utility.getConcatenatedIds("ATT_EMP_ID",
								concatEmployeeIds) + // get employee ids
														// separated by comma
						" GROUP BY ATT_EMP_ID ORDER BY ATT_EMP_ID ";
			} else {
				String dateMonth = String.valueOf(Integer.parseInt(month) - 1);
				String fromDateSal = fromDay + "-" + dateMonth + "-" + year;
				sql = " SELECT ATT_EMP_ID, CASE WHEN SUM(NVL(LATE_MARKS, 0)) = 0 THEN '0' "
						+ " ELSE CAST(SUM(NVL(LATE_MARKS, 0)) AS VARCHAR2(4)) END AS LATE_MARKS, "
						+ " CASE WHEN SUM(NVL(HALF_DAYS, 0)) = 0 THEN '0' ELSE CAST(SUM(NVL(HALF_DAYS, 0)) AS VARCHAR2(4)) END AS HALF_DAYS "
						+ " FROM ( "
						+ " 	SELECT ATT_EMP_ID, CASE WHEN ATT_STATUS_TWO = 'DL' THEN COUNT(*) * 2  WHEN ATT_STATUS_TWO IN('LC', 'EL') "
						+ " 	THEN COUNT(*) END AS LATE_MARKS, CASE WHEN ATT_STATUS_TWO = 'HD' THEN COUNT(*) END AS HALF_DAYS "
						+ " 	FROM HRMS_DAILY_ATTENDANCE_"
						+ year
						+ " 	WHERE ATT_DATE BETWEEN TO_DATE('"
						+ fromDateSal
						+ "', 'DD-MM-YYYY') AND TO_DATE('"
						+ toDate
						+ "', 'DD-MM-YYYY')"
						+ " 	AND ATT_STATUS_ONE = 'PR' AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY "
						+ "	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS = 'A' AND LEAVE_DAYS = 0.5) "
						+ " 	GROUP BY ATT_STATUS_TWO, ATT_EMP_ID "
						+ " ) "
						+ " WHERE 1 = 1 "
						+ Utility.getConcatenatedIds("ATT_EMP_ID",
								concatEmployeeIds)
						+ " GROUP BY ATT_EMP_ID ORDER BY ATT_EMP_ID ";
			}
		} else {
			sql = " SELECT ATT_EMP_ID, CASE WHEN SUM(NVL(LATE_MARKS, 0)) = 0 THEN '0' "
					+ " ELSE CAST(SUM(NVL(LATE_MARKS, 0)) AS VARCHAR2(4)) END AS LATE_MARKS, "
					+ " CASE WHEN SUM(NVL(HALF_DAYS, 0)) = 0 THEN '0' ELSE CAST(SUM(NVL(HALF_DAYS, 0)) AS VARCHAR2(4)) END AS HALF_DAYS "
					+ " FROM ( "
					+ " 	SELECT ATT_EMP_ID, CASE WHEN ATT_STATUS_TWO = 'DL' THEN COUNT(*) * 2  WHEN ATT_STATUS_TWO IN('LC', 'EL') "
					+ " 	THEN COUNT(*) END AS LATE_MARKS, CASE WHEN ATT_STATUS_TWO = 'HD' THEN COUNT(*) END AS HALF_DAYS "
					+ "	FROM HRMS_DAILY_ATTENDANCE_"
					+ year
					+ " 	WHERE ATT_DATE BETWEEN TO_DATE('01-"
					+ month
					+ "-"
					+ year
					+ "', 'DD-MM-YYYY') AND "
					+ "	LAST_DAY(TO_DATE('01-"
					+ month
					+ "-"
					+ year
					+ "', 'DD-MM-YYYY')) AND ATT_STATUS_ONE = 'PR' "
					+ "	AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY "
					+ "	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS = 'A' AND LEAVE_DAYS = 0.5) "
					+ " 	GROUP BY ATT_STATUS_TWO, ATT_EMP_ID "
					+ " ) "
					+ " WHERE 1 = 1 "
					+ Utility.getConcatenatedIds("ATT_EMP_ID",
							concatEmployeeIds)
					+ " GROUP BY ATT_EMP_ID ORDER BY ATT_EMP_ID ";
		}
		Object[][] attData = getSqlModel().getSingleResult(sql);
		if (attData != null && attData.length > 0) {
			return attData;
		} else {
			return null;
		}
	}

	public Object[][] callCheckWorkFlow() {
		String sqlSet = " SELECT HRMS_ATTENDANCE_CONF.CONF_DAILY_ATT_CONNECT_FLAG, HRMS_ATTENDANCE_CONF.CONF_LEAVE_CONNECT_FLAG, HRMS_ATTENDANCE_CONF.CONF_SALARY_START_FLAG,"
				+ " HRMS_ATTENDANCE_CONF.CONF_SALARY_START_DATE, CONF_UPLOAD_MONTH_ATT_FLAG,NVL(CONF_RECOVERY_FLAG,'N') FROM HRMS_ATTENDANCE_CONF,HRMS_SALARY_CONF ";
		Object[][] flagObj = getSqlModel().getSingleResult(sqlSet);
		return flagObj;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void downloadCalculateFile(UploadMonthlyAttendance bean,
			HttpServletResponse response) {
		// for Auto present Hashmap

		String autoSql = "SELECT AUTO_PRESENT_EMP_ID, AUTO_PRESENT_LATE_MARK, AUTO_PRESENT_HALF_DAY,AUTO_PRESENT_ABSENT FROM HRMS_AUTO_PRESENT ";
		Object[][] autoData = getSqlModel().getSingleResult(autoSql);

		HashMap lateMarkMap = new HashMap();
		HashMap halfDayMap = new HashMap();
		HashMap unpaidMap = new HashMap();
		
		if (autoData != null && autoData.length > 0) {
			for (int i = 0; i < autoData.length; i++) {
				lateMarkMap.put(String.valueOf(autoData[i][0]), String
						.valueOf(autoData[i][1]));
				halfDayMap.put(String.valueOf(autoData[i][0]), String
						.valueOf(autoData[i][2]));
				unpaidMap.put(String.valueOf(autoData[i][0]), String
						.valueOf(autoData[i][3]));
			}
		}

		String title = "Employee List_"
				+ Utility.month(Integer.parseInt(bean.getMonth())) + "_"
				+ bean.getYear();
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Xls",
				title);
		Object[][] flagObj = callCheckWorkFlow();

		// get all employee according to the filters
		Object[][] empObj = setEmpOffcFiletrs(bean, String
				.valueOf(flagObj[0][2]), String.valueOf(flagObj[0][3]));

		String concatEmployeeIds = "";
		Object[][] attData = null;
		Object[][] leaveCodeData = null;
		LeavePolicyData leavePolicyData = null;

		if (empObj != null && empObj.length > 0) {
			for (int i = 0; i < empObj.length; i++) {
				if (i == (empObj.length - 1)) {
					concatEmployeeIds += String.valueOf(empObj[i][1]);
				} else {
					concatEmployeeIds += String.valueOf(empObj[i][1]) + ",";
				}
			}
		} // end of if for canocat all empId

		String attWorkFlow = "";
		String leaveWorkFlow = "";
		String recoveryWorkFlow = "";
		int dynamicCol = 0;
		if (flagObj != null && flagObj.length > 0) {
			attWorkFlow = "" + flagObj[0][0];
			leaveWorkFlow = "" + flagObj[0][1];
			recoveryWorkFlow = "" + flagObj[0][5];
			if (attWorkFlow.equals("Y")) {
				attData = callAttendance(bean.getMonth(), bean.getYear(),
						concatEmployeeIds, String.valueOf(flagObj[0][2]),
						String.valueOf(flagObj[0][3]));
			}

			if (leaveWorkFlow.equals("Y")) {
				leaveCodeData = getLeaveCode(bean.getMonth(), bean.getYear(),
						concatEmployeeIds, String.valueOf(flagObj[0][2]),
						String.valueOf(flagObj[0][3]));

				// Set leave policy information
				leavePolicyData = new LeavePolicyData(bean.getDivisionId());
				leavePolicyData.initiate(context, session);
				leavePolicyData.setLeavePolicyObject();
			}
		}
		if (recoveryWorkFlow.equals("Y")) {
			dynamicCol = 1;
		}
		Object[][] totalObj = new Object[empObj.length][7 + dynamicCol];
		// for attendance late marks and half days
		for (int i = 0; i < empObj.length; i++) {
			String empId = String.valueOf(empObj[i][1]);
			String empDiv = String.valueOf(empObj[i][4]);
			String empDept = String.valueOf(empObj[i][5]);
			String empDesg = String.valueOf(empObj[i][6]);
			String empBranch = String.valueOf(empObj[i][7]);
			String empType = String.valueOf(empObj[i][8]);

			totalObj[i][0] = empObj[i][0]; // employee token
			totalObj[i][1] = empObj[i][2]; // employee name
			totalObj[i][2] = empObj[i][3]; // branch name

			// for late marks and half days
			boolean attFlag = false;

			if (attWorkFlow.equals("Y")) {
				if (attData != null && attData.length > 0) {
					for (int j = 0; j < attData.length; j++) {
						if (empObj[i][1].equals(attData[j][0])) {
							totalObj[i][3] = attData[j][1]; // late Marks
							totalObj[i][4] = attData[j][2]; // Half Days
							attFlag = true;
							break;
						}
					}
				}
			} else {
				totalObj[i][3] = "0";
				totalObj[i][4] = "0";
			}
			if (attFlag == false) {
				totalObj[i][3] = "0"; // blank late Marks when not attendance
				totalObj[i][4] = "0"; // blank half days when not attendance
			}
			if (lateMarkMap.get(empId) != null
					&& lateMarkMap.get(empId).equals("Y")) { // checking the
																// Auto present
																// flag for late
																// Marks
				totalObj[i][3] = "0";
			}
			if (halfDayMap.get(empId) != null
					&& halfDayMap.get(empId).equals("Y")) { // checking the Auto
															// present flag for
															// Half Day
				totalObj[i][4] = "0";
			}

			// for paid and unpaid leave
			if (leaveWorkFlow.equals("Y")) {
				Object[][] leave = getLeavePaidUnpaid(concatEmployeeIds,
						leavePolicyData, leaveCodeData, empId, empDiv, empDept,
						empDesg, empBranch, empType);

				totalObj[i][5] = checkNull(String.valueOf(leave[0][0])).equals(
						"") ? "0" : String.valueOf(leave[0][0]);

				if (unpaidMap.get(empId) != null
						&& unpaidMap.get(empId).equals("Y")) { // check for
																// Auto present
																// flag
					totalObj[i][6] = "0";
				} else {
					totalObj[i][6] = checkNull(String.valueOf(leave[0][1]))
							.equals("") ? "0" : String.valueOf(leave[0][1]);
				}
			} else { // when work flow is false
				totalObj[i][5] = "0.0";
				totalObj[i][6] = "0.0";
			}
			if (dynamicCol > 0) {
				totalObj[i][7] = "0";
			}
		}

		try {
			if (recoveryWorkFlow.equals("Y")) {

				String abc[] = { "Employee Id", "Employee Name", "Branch Name",
						"Unadjusted Late Marks", "Unadjusted Half Days",
						"Paid Leaves", "Unpaid Leaves", "Recovery Days" };
				int cellwidth[] = { 15, 30, 30, 25, 25, 16, 20, 20 };
				int alignment[] = { 0, 0, 0, 1, 1, 1, 1, 1 };
				int colsAsDouble[] = { 3, 4, 5, 6 };
				rg.tableBodyAsText(abc, totalObj, cellwidth, alignment,
						colsAsDouble);
			} else {
				String abc[] = { "Employee Id", "Employee Name", "Branch Name",
						"Unadjusted Late Marks", "Unadjusted Half Days",
						"Paid Leaves", "Unpaid Leaves" };
				int cellwidth[] = { 15, 30, 30, 25, 25, 16, 20 };
				int alignment[] = { 0, 0, 0, 1, 1, 1, 1 };
				int colsAsDouble[] = { 3, 4, 5, 6 };
				rg.tableBodyAsText(abc, totalObj, cellwidth, alignment,
						colsAsDouble);
			}
			rg.createReport(response);
		} catch (Exception e) {
			logger.error("Exception in downloadCalculateFile:" + e);
		}
	} // end of downloadCalculateFile

	/**
	 * Get the filters, like branch, department, paybill, employee type,
	 * division, from Payroll Settings
	 * 
	 * @return Object[][] as list of filters
	 */
	public Object[][] getFilters() {
		Object[][] attendanceFiltersObj = null;
		try {
			String attendanceFiltersSql = " SELECT DECODE(CONF_BRN_FLAG, 'Y', 'true','N','false') AS BRANCH_FLAG, "
					+ " DECODE(CONF_DEPT_FLAG, 'Y', 'true', 'N', 'false') AS DEPARTMENT_FLAG, "
					+ " DECODE(CONF_PAYBILL_FLAG, 'Y', 'true', 'N', 'false') AS PAYBILL_FLAG, "
					+ " DECODE(CONF_EMPTYPE_FLAG, 'Y', 'true', 'N', 'false') AS EMPLOYEE_TYPE_FLAG, "
					+ " DECODE(CONF_DIVISION_FLAG, 'Y', 'true', 'N', 'false') AS DIVISION_FLAG FROM HRMS_SALARY_CONF ";
			attendanceFiltersObj = getSqlModel().getSingleResult(
					attendanceFiltersSql);
		} catch (Exception e) {
			logger.error("Exception in getFilters:" + e);
		}
		return attendanceFiltersObj;
	}

	// getting the leave code and leave days
	public Object[][] getLeaveCode(String month, String year,
			String concatEmployeeIds, String salaryStartFlag, String fromDay) {
		MonthAttendanceProcessModel monProcess = new MonthAttendanceProcessModel();
		String sqlLeave = "";
		String toDate = "";
		String fromDate = "";

		int endDate = Integer.parseInt(fromDay);
		endDate -= 1;

		if (salaryStartFlag.equals("P")) {
			if (month.equals("1")) {
				String DateYear = String.valueOf(Integer.parseInt(year) - 1);
				fromDate = fromDay + "-12-" + DateYear;
			} else {
				String DateMonth = String.valueOf(Integer.parseInt(month) - 1);
				fromDate = fromDay + "-" + DateMonth + "-" + year;
			}
			toDate = endDate + "-" + month + "-" + year;

			sqlLeave = " SELECT HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE, SUM(NVL(LEAVE_DAYS, 0)) AS LEAVE_DAYS "
					+ " FROM HRMS_LEAVE_BALANCE "
					+ " LEFT JOIN HRMS_LEAVE_DTLHISTORY ON(HRMS_LEAVE_DTLHISTORY.EMP_ID = HRMS_LEAVE_BALANCE.EMP_ID "
					+ " AND HRMS_LEAVE_DTLHISTORY.LEAVE_CODE = HRMS_LEAVE_BALANCE.LEAVE_CODE AND LEAVE_DTL_STATUS = 'A' "
					+ " AND LEAVE_FROM_DATE BETWEEN TO_DATE('"
					+ fromDate
					+ "', 'DD-MM-YYYY') "
					+ " AND TO_DATE('"
					+ toDate
					+ "', 'DD-MM-YYYY'))"
					+ " WHERE 1 = 1 "
					+ Utility.getConcatenatedIds("HRMS_LEAVE_BALANCE.EMP_ID",
							concatEmployeeIds)
					+ " GROUP BY HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE, LEAVE_OPENING_BALANCE, LEAVE_CLOSING_BALANCE "
					+ " ORDER BY HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE ";
		} else {
			sqlLeave = " SELECT HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE, SUM(NVL(LEAVE_DAYS, 0)) AS LEAVE_DAYS "
					+ " FROM HRMS_LEAVE_BALANCE "
					+ " LEFT JOIN HRMS_LEAVE_DTLHISTORY ON(HRMS_LEAVE_DTLHISTORY.EMP_ID = HRMS_LEAVE_BALANCE.EMP_ID "
					+ " AND HRMS_LEAVE_DTLHISTORY.LEAVE_CODE = HRMS_LEAVE_BALANCE.LEAVE_CODE "
					+ " AND LEAVE_DTL_STATUS = 'A' AND LEAVE_FROM_DATE >= TO_DATE('01-"
					+ month
					+ "-"
					+ year
					+ "', 'DD-MM-YYYY') "
					+ " AND LEAVE_TO_DATE <= LAST_DAY(TO_DATE('01-"
					+ month
					+ "-"
					+ year
					+ "', 'DD-MM-YYYY'))) "
					+ " WHERE 1 = 1 "
					+ Utility.getConcatenatedIds("HRMS_LEAVE_BALANCE.EMP_ID",
							concatEmployeeIds)
					+ " GROUP BY HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE, LEAVE_OPENING_BALANCE, LEAVE_CLOSING_BALANCE "
					+ " ORDER BY HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE ";
		}
		Object[][] leaveData = getSqlModel().getSingleResult(sqlLeave);
		if (leaveData != null && leaveData.length > 0) {
			return leaveData;
		} else {
			return null;
		}
	}

	// leave iD as per policy
	private ArrayList<String> getLeaveIdsFromPolicy(
			LeavePolicyData leavePolicyData, String empId, String empDiv,
			String empDept, String empDesg, String empBranch, String empType,
			String leaveType) {
		ArrayList<String> leaveIdsFromPolicy = new ArrayList<String>();
		try {
			if (leavePolicies != null && leavePolicies.length > 0) {
				// Get Leave Policy Id for an employee
				String leavePolicyId = leavePolicyData.getLeavePolicyCode(
						empId, empDiv, empDept, empDesg, empBranch, empType);

				for (int i = 0; i < leavePolicies.length; i++) {
					// leave policy id for an employee matches with policy ids
					// in leavePolicies and
					// leave type in leavePolicies matches with leaveType (P/U),
					// then get leave id
					if (leavePolicyId.equals(String
							.valueOf(leavePolicies[i][2]))
							&& String.valueOf(leavePolicies[i][1])
									.equalsIgnoreCase(leaveType)) {
						leaveIdsFromPolicy.add(String
								.valueOf(leavePolicies[i][0])); // added leave
																// code
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getLeaveIdsFromPolicy:" + e);
		}
		return leaveIdsFromPolicy;
	}

	public Object[][] getLeavePaidUnpaid(String concatEmployeeIds,
			LeavePolicyData leavePolicyData, Object[][] leaveCodeData,
			String empId, String empDiv, String empDept, String empDesg,
			String empBranch, String empType) {
		Object[][] leavePaidUnpaid = new Object[1][2];

		// get Policy Id
		leavePolicies = leavePolicyData.getLeavePolicyObj();
		if (leaveCodeData != null && leaveCodeData.length > 0) {
			double paidLeaves = 0.0;
			double unPaidLeaves = 0.0;
			leavePaidUnpaid[0][0] = "0.0";
			leavePaidUnpaid[0][1] = "0.0";

			for (int j = 0; j < leaveCodeData.length; j++) { // leave code
																// for loop
				if (empId.equals(String.valueOf(leaveCodeData[j][0]))) { // verify
																			// employee
																			// id
					// for paid leaves calculation
					ArrayList<String> paidLeaveIds = getLeaveIdsFromPolicy(
							leavePolicyData, empId, empDiv, empDept, empDesg,
							empBranch, empType, "P");

					if (paidLeaveIds != null) {
						for (int k = 0; k < paidLeaveIds.size(); k++) {
							if (paidLeaveIds.get(k).equals(
									String.valueOf(leaveCodeData[j][1]))) {
								// set the paid leaves
								leavePaidUnpaid[0][0] = paidLeaves
										+ (Double.parseDouble(String
												.valueOf(leaveCodeData[j][2])));
								paidLeaves = Double.parseDouble(String
										.valueOf(leavePaidUnpaid[0][0]));
							}
						}
					}

					// for unpaid leaves calculation
					ArrayList<String> unPaidLeaveIds = getLeaveIdsFromPolicy(
							leavePolicyData, empId, empDiv, empDept, empDesg,
							empBranch, empType, "U");
					if (unPaidLeaveIds != null) {
						for (int m = 0; m < unPaidLeaveIds.size(); m++) {
							if (unPaidLeaveIds.get(m).equals(
									String.valueOf(leaveCodeData[j][1]))) {
								// set the unPaid leaves
								leavePaidUnpaid[0][1] = unPaidLeaves
										+ (Double.parseDouble(String
												.valueOf(leaveCodeData[j][2])));
								unPaidLeaves = Double.parseDouble(String
										.valueOf(leavePaidUnpaid[0][1]));
							}
						}
					}
				}
			} // end of leave code for loop
		}
		return leavePaidUnpaid;
	}

	/**
	 * insert the file data in to the database according the applied filter.
	 * 
	 * @param fileEmpObj
	 * @param bean
	 * @return
	 */
	public boolean saveFilterEmp(Object[][] fileEmpObj,
			UploadMonthlyAttendance bean) {

		String autoSql = "SELECT AUTO_PRESENT_EMP_ID, AUTO_PRESENT_LATE_MARK, AUTO_PRESENT_HALF_DAY,AUTO_PRESENT_ABSENT FROM HRMS_AUTO_PRESENT ";
		Object[][] autoData = getSqlModel().getSingleResult(autoSql);

		HashMap lateMarkMap = new HashMap();
		HashMap halfDayMap = new HashMap();
		HashMap unpaidMap = new HashMap();
		if (autoData != null && autoData.length > 0) {
			for (int i = 0; i < autoData.length; i++) {
				lateMarkMap.put(String.valueOf(autoData[i][0]), String
						.valueOf(autoData[i][1]));
				halfDayMap.put(String.valueOf(autoData[i][0]), String
						.valueOf(autoData[i][2]));
				unpaidMap.put(String.valueOf(autoData[i][0]), String
						.valueOf(autoData[i][3]));
			}
		}

		Object[][] flagObj = callCheckWorkFlow();
		// get all the employee from HRMS_EMP_OFFC according to the filter
		// verifying the employee id is valid or not according to
		// filter and calculate the length of valid employee id
		Object[][] filterEmpObj = setEmpOffcFiletrs(bean, String
				.valueOf(flagObj[0][2]), String.valueOf(flagObj[0][3]));
		int objLength = 0;
		for (int i = 0; i < filterEmpObj.length; i++) {
			for (int j = 0; j < fileEmpObj.length; j++) {
				if (String.valueOf(filterEmpObj[i][0]).equals(
						String.valueOf(fileEmpObj[j][2]))) {
					objLength++;
					break;
				}
			}
		}

		// merging the data from file whose employee id is valid according to
		// the applied filter
		Object[][] emoObj = new Object[objLength][fileEmpObj[0].length];
		int count = 0;
		String duplicateEmpId = "";
		for (int i = 0; i < filterEmpObj.length; i++) {
			for (int j = 0; j < fileEmpObj.length; j++) {
				if (String.valueOf(filterEmpObj[i][0]).equals(
						String.valueOf(fileEmpObj[j][2]))) {
					emoObj[count][0] = fileEmpObj[j][0];
					emoObj[count][1] = fileEmpObj[j][1];
					emoObj[count][2] = filterEmpObj[i][1];
					emoObj[count][3] = fileEmpObj[j][3];
					emoObj[count][4] = fileEmpObj[j][4];
					emoObj[count][5] = fileEmpObj[j][5];
					emoObj[count][6] = fileEmpObj[j][6];
					if (String.valueOf(flagObj[0][5]).equals("Y")) {
						emoObj[count][7] = fileEmpObj[j][7];
					}

					if (lateMarkMap.get(String.valueOf(filterEmpObj[i][1])) != null
							&& lateMarkMap.get(
									String.valueOf(filterEmpObj[i][1])).equals(
									"Y")) { // checking the Auto present flag
											// for late Marks
						emoObj[count][3] = "0";
					}
					if (halfDayMap.get(String.valueOf(filterEmpObj[i][1])) != null
							&& halfDayMap.get(
									String.valueOf(filterEmpObj[i][1])).equals(
									"Y")) { // checking the Auto present flag
											// for Half Day
						emoObj[count][4] = "0";
					}
					if (unpaidMap.get(String.valueOf(filterEmpObj[i][1])) != null
							&& unpaidMap
									.get(String.valueOf(filterEmpObj[i][1]))
									.equals("Y")) { // checking the Auto present
													// flag for Unpaid leaves
						emoObj[count][6] = "0";
					}

					if (duplicateEmpId.equals("")) {
						duplicateEmpId += "" + emoObj[count][2];
					} else {
						duplicateEmpId += "," + emoObj[count][2];
					}
					count++;
					break;
				}
			}
		}

		/*
		 * for (int i = 0; i < emoObj.length; i++) { for (int j = 0; j <
		 * emoObj[0].length; j++) {
		 * logger.info("emoObj["+i+"]["+j+"]=="+emoObj[i][j]); } }
		 */
		// for delete the previous duplicate record from the table whose
		// attendance is already uploaded.
		boolean res = false;
		if (!duplicateEmpId.equals("")) {
			String delSql = " DELETE FROM HRMS_UPLOAD_ATTENDANCE_"
					+ bean.getYear() + " WHERE ATTN_MONTH =" + bean.getMonth()
					+ " AND ATTN_YEAR =" + bean.getYear()
					+ Utility.getConcatenatedIds("ATTN_EMP_ID", duplicateEmpId);
			res = getSqlModel().singleExecute(delSql);
		}

		// insert the data of file into the table.
		if (emoObj != null && emoObj.length > 0) {
			String sqlQuery = "";
			if (String.valueOf(flagObj[0][5]).equals("Y")) {
				sqlQuery = " INSERT INTO HRMS_UPLOAD_ATTENDANCE_"
						+ bean.getYear()
						+ " (ATTN_MONTH, ATTN_YEAR , ATTN_EMP_ID, "
						+ " ATTN_LATE_MARKS, ATTN_HALF_DAYS, ATTN_PAID_LEAVES, ATTN_UNPAID_LEAVES,ATTN_RECOVERY_DAYS) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
			} else {
				sqlQuery = " INSERT INTO HRMS_UPLOAD_ATTENDANCE_"
						+ bean.getYear()
						+ " (ATTN_MONTH, ATTN_YEAR , ATTN_EMP_ID, "
						+ " ATTN_LATE_MARKS, ATTN_HALF_DAYS, ATTN_PAID_LEAVES, ATTN_UNPAID_LEAVES) VALUES (?, ?, ?, ?, ?, ?, ?) ";
			}
			res = getSqlModel().singleExecute(sqlQuery, emoObj);
		}
		return res;
	}

	/**
	 * Set filters to records which are coming from Employee Office
	 * 
	 * @param bean
	 * @param sqlQuery
	 * @return String as sql query
	 */
	public Object[][] setEmpOffcFiletrs(UploadMonthlyAttendance bean,
			String salaryStartFlag, String fromDay) {
		try {
			String typeCode = bean.getEmployeeTypeId();
			String payBillNo = bean.getPayBillId();
			String brnCode = bean.getBranchId();
			String deptCode = bean.getDepartmentId();
			String divCode = bean.getDivisionId();
			String empSearchId = bean.getEmpSerachId();

			String sqlQuery = " SELECT EMP_TOKEN, EMP_ID, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, "
					+ " CENTER_NAME, EMP_DIV, EMP_DEPT, EMP_RANK, EMP_CENTER, EMP_TYPE FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER) "
					+ " WHERE EMP_STATUS = 'S' ";

			if (salaryStartFlag.equals("P")) {
				String date = "";
				String month = "";
				String Year = "";
				/*if (bean.getMonth().equals("1")) {
					month = "12";
					Year = String.valueOf(Integer.parseInt(bean.getYear()) - 1);
					date = fromDay + "-" + month + "-" + Year;
				} else {
					month = bean.getMonth();
					Year = bean.getYear();
					date = fromDay + "-" + month + "-" + Year;
				}*/
				month = bean.getMonth();
				Year = bean.getYear();
				date = fromDay + "-" + month + "-" + Year;

				sqlQuery += " AND EMP_REGULAR_DATE <= TO_DATE('" + date
						+ "','DD-MM-YYYY')";
			} else {
				sqlQuery += " AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('01-"
						+ bean.getMonth() + "-" + bean.getYear()
						+ "', 'DD-MM-YYYY'))";
			}
			if (!typeCode.equals("")) {
				sqlQuery += " AND EMP_TYPE = " + typeCode;
			}
			if (!payBillNo.equals("")) {
				sqlQuery += " AND EMP_PAYBILL = " + payBillNo;
			}
			if (!brnCode.equals("")) {
				sqlQuery += " AND EMP_CENTER = " + brnCode;
			}
			if (!deptCode.equals("")) {
				sqlQuery += " AND EMP_DEPT = " + deptCode;
			}
			if (!divCode.equals("")) {
				sqlQuery += " AND EMP_DIV = " + divCode;
			}
			if (!empSearchId.equals("")) {
				sqlQuery += " AND EMP_ID = " + empSearchId;
			}

			sqlQuery += " ORDER BY UPPER(CENTER_NAME), UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), UPPER(EMP_TOKEN) ";
			return getSqlModel().getSingleResult(sqlQuery);
		} catch (Exception e) {
			logger.error("Exception in setEmpOffcFiletrs:" + e);
			return null;
		} // end of try-catch block
	} // end of setEmpOffcFiletrs

	public String uploadMonthlySheet(UploadMonthlyAttendance bean,
			String recDaysWorkFlow) {
		String filePath = context.getRealPath("/") + "pages/images/"
				+ session.getAttribute("session_pool") + "/attendance/"
				+ bean.getUploadFileName();
		String fileExtension = filePath.substring(filePath.length() - 3,
				filePath.length());

		// for wrong format
		if (!(fileExtension.equalsIgnoreCase("xls"))) {
			return "notValidFormat";
		}

		HSSFWorkbook wb = null;
		try {
			InputStream myXls = new FileInputStream(filePath);
			wb = new HSSFWorkbook(myXls);
		} catch (Exception e) {
			logger.error(e);
		}

		try {
			Vector vect1 = new Vector();
			HSSFSheet sheet1 = wb.getSheetAt(0);

			// for no data in file
			if (!(sheet1.getLastRowNum() > 0)) {
				return "noDataInfile";
			}

			String employeeError = "";
			boolean errorFlag = false;
			for (int j = 1; j <= sheet1.getLastRowNum(); j++) {
				String localEmpToken = "";
				String EmpTokenName = "";
				HSSFRow row = sheet1.getRow(j);
				HSSFCell empToken = row.getCell((short) 0);
				HSSFCell latemark = row.getCell((short) 3);
				HSSFCell halfday = row.getCell((short) 4);
				HSSFCell paidLeave = row.getCell((short) 5);
				HSSFCell unpaidLeave = row.getCell((short) 6);
				HSSFCell recoveryDays = row.getCell((short) 7);

				// if emp token is valid and other is null then added the value
				// in vector.
				try {
					if (empToken != null) {
						if (empToken.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							vect1.add(String.valueOf((int) empToken
									.getNumericCellValue()));
							EmpTokenName = String.valueOf((int) empToken
									.getNumericCellValue());
						} else if (empToken.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							vect1.add(String.valueOf(empToken
									.getStringCellValue().replaceAll("'", "")));
							EmpTokenName = String.valueOf(empToken
									.getStringCellValue().replaceAll("'", ""));
						}
					}
				} catch (Exception e) {
					vect1.add("");
				}// end of if

				// for lateMarks
				try {
					if (latemark != null) {
						String lateMarkVal = "";
						double lateMarkDig = 0.0;
						if (latemark.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							lateMarkVal = String.valueOf(latemark
									.getNumericCellValue());
						} else if (latemark.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							lateMarkVal = String.valueOf(latemark
									.getStringCellValue().replaceAll("'", "")
									.trim());
						}
						if (!lateMarkVal.equals("")) {
							lateMarkDig = Double.parseDouble(lateMarkVal);
							if (lateMarkDig >= 0) {
								vect1.add(lateMarkDig);
							} else {
								vect1.add("0");
								localEmpToken = EmpTokenName;
								errorFlag = true;
							}
						} else {
							vect1.add("0");
						}
					} else {
						vect1.add("0");
					}
				} catch (Exception e) {
					vect1.add("0");
					errorFlag = true;
					localEmpToken = EmpTokenName;
				}

				// for halfDays
				try {
					if (halfday != null) {
						String halfdayVal = "";
						double halfdayDig = 0.0;
						if (halfday.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							halfdayVal = String.valueOf(halfday
									.getNumericCellValue());
						} else if (halfday.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							halfdayVal = String.valueOf(halfday
									.getStringCellValue().replaceAll("'", "")
									.trim());
						}
						if (!halfdayVal.equals("")) {
							halfdayDig = Double.parseDouble(halfdayVal);
							if (halfdayDig >= 0) {
								vect1.add(halfdayDig);
							} else {
								vect1.add("0");
								localEmpToken = EmpTokenName;
								errorFlag = true;
							}
						} else {
							vect1.add("0");
						}
					} else {
						vect1.add("0");
					}
				} catch (Exception e) {
					vect1.add("0");
					errorFlag = true;
					localEmpToken = EmpTokenName;
				}

				// for paidLevaes
				try {
					if (paidLeave != null) {
						String paidLeaveVal = "";
						double paidLeaveDig = 0.0;
						if (paidLeave.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							paidLeaveVal = String.valueOf(paidLeave
									.getNumericCellValue());
						} else if (paidLeave.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							paidLeaveVal = String.valueOf(paidLeave
									.getStringCellValue().replaceAll("'", "")
									.trim());
						}
						if (!paidLeaveVal.equals("")) {
							paidLeaveDig = Double.parseDouble(paidLeaveVal);
							if (paidLeaveDig >= 0) {
								vect1.add(paidLeaveDig);
							} else {
								vect1.add("0");
								localEmpToken = EmpTokenName;
								errorFlag = true;
							}
						} else {
							vect1.add("0");
						}
					} else {
						vect1.add("0");
					} // end of if
				} catch (Exception e) {
					vect1.add("0");
					errorFlag = true;
					localEmpToken = EmpTokenName;
				}
				// for unpaidLevaes
				try {
					if (unpaidLeave != null) {
						String unpaidLeaveVal = "";
						double unpaidLeaveDig = 0.0;
						if (unpaidLeave.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							unpaidLeaveVal = String.valueOf(unpaidLeave
									.getNumericCellValue());
						} else if (paidLeave.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							unpaidLeaveVal = String.valueOf(unpaidLeave
									.getStringCellValue().replaceAll("'", "")
									.trim());
						}
						if (!unpaidLeaveVal.equals("")) {
							unpaidLeaveDig = Double.parseDouble(unpaidLeaveVal);
							if (unpaidLeaveDig >= 0) {
								vect1.add(unpaidLeaveDig);
							} else {
								vect1.add("0");
								localEmpToken = EmpTokenName;
								errorFlag = true;
							}
						} else {
							vect1.add("0");
						}
					} else {
						vect1.add("0");
					}
				} catch (Exception e) {
					vect1.add("0");
					errorFlag = true;
					localEmpToken = EmpTokenName;
				}

				// for recoveryDays
				if (recDaysWorkFlow.equals("Y")) {
					try {
						if (recoveryDays != null) {
							String recDayVal = "";
							double recDayDig = 0.0;
							if (recoveryDays.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								recDayVal = String.valueOf(recoveryDays
										.getNumericCellValue());
							} else if (recoveryDays.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								recDayVal = String.valueOf(recoveryDays
										.getStringCellValue().replaceAll("'",
												"").trim());
							}
							if (!recDayVal.equals("")) {
								recDayDig = Double.parseDouble(recDayVal);
								if (recDayDig >= 0) {
									vect1.add(recDayDig);
								} else {
									vect1.add("0");
									localEmpToken = EmpTokenName;
									errorFlag = true;
								}
							} else {
								vect1.add("0");
							}
						} else {
							vect1.add("0");
						}
					} catch (Exception e) {
						vect1.add("0");
						errorFlag = true;
						localEmpToken = EmpTokenName;
					}
				}
				if (!localEmpToken.equals("")) {
					employeeError += "," + localEmpToken;
				}
			}

			int count = 0;
			String[][] obj = null;
			if (recDaysWorkFlow.equals("Y")) {
				obj = new String[vect1.size() / 6][8];
			} else {
				obj = new String[vect1.size() / 5][7];
			}
			for (int i = 0; i < vect1.size() / 6; i++) {
				obj[i][0] = bean.getMonth();
				obj[i][1] = bean.getYear();
				obj[i][2] = (String.valueOf(vect1.get(count++)));
				obj[i][3] = (String.valueOf(vect1.get(count++)));
				obj[i][4] = (String.valueOf(vect1.get(count++)));
				obj[i][5] = (String.valueOf(vect1.get(count++)));
				obj[i][6] = (String.valueOf(vect1.get(count++)));
				if (recDaysWorkFlow.equals("Y")) {
					obj[i][7] = (String.valueOf(vect1.get(count++)));
				}
			}
			boolean res = saveFilterEmp(obj, bean);
			if (res && errorFlag == false) {
				return "success";
			}
			if (res && errorFlag) {
				return "successWithError" + employeeError;
			}
		} catch (Exception e) {
			logger.error("Exception in uploadMonthlySheet:" + e);
			e.printStackTrace();
		}
		return "notSuccess";
	}
}