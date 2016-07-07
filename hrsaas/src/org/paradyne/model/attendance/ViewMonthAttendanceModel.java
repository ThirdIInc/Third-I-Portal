/* @author: Bhushan Dasare   @date: July 3, 2009 */

package org.paradyne.model.attendance;

import java.util.ArrayList;
import org.paradyne.model.leave.LeavePolicyData;

/**
 * To write a business logic to view and modify already processed attendance
 */

public class ViewMonthAttendanceModel extends org.paradyne.lib.ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ViewMonthAttendanceModel.class);
	
	/**
	 * Add an employee into already processed attendance
	 * @param request: HttpServletRequest object used to get the details of an employee who is to be added
	 * @return String as calculated attendance appended with the message
	 */
	public String addEmployee(javax.servlet.http.HttpServletRequest request) {
		String message = "";
		try {
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			String attendanceCode = request.getParameter("attendanceCode");
			
			// get information of an employee who is to be added
			String newEmpId = request.getParameter("newEmpId");
			String newEmpToken = request.getParameter("newEmpToken");
			String newEmpName = request.getParameter("newEmpName");
			String newEmpBranchName = request.getParameter("newEmpBranchName");
			String newEmpDiv = request.getParameter("newEmpDiv");
			
			Object[][] employee = getEmployeeDetails(newEmpId);
			boolean lmInHrsEnabled = Boolean.parseBoolean(String.valueOf(employee[0][15]));
			
			MonthAttendanceProcessModel monthAtdnProc = new MonthAttendanceProcessModel();
			monthAtdnProc.initiate(context, session);
			
			// calculate the attendance
			Object[][] calculatedAttendnace = attendanceProcess(month, year, newEmpId, newEmpDiv, employee, false, lmInHrsEnabled, 
				monthAtdnProc, request);
			
			// Save calculated attendance
			boolean isDataSaved = monthAtdnProc.saveAttendance(year, attendanceCode, calculatedAttendnace);
			
			/**
			 * Set the response text
			 */
			if(isDataSaved) {
				// attendance days
				String attendanceDays = String.valueOf(calculatedAttendnace[0][13]);
				if(lmInHrsEnabled) {
					String[] attendanceHrs = String.valueOf(calculatedAttendnace[0][14]).split(":");
					attendanceDays += " d " + attendanceHrs[0] + " h " + attendanceHrs[1] + " m";
				}
				
				// late marks
				String lateMarks = String.valueOf(calculatedAttendnace[0][3]);
				if(lmInHrsEnabled) {
					String[] lateMarkHrs = String.valueOf(calculatedAttendnace[0][4]).split(":");
					lateMarks += " d " + lateMarkHrs[0] + " h " + lateMarkHrs[1] + " m";
				}
				
				// paid leaves
				double paidLeaves = Double.parseDouble(String.valueOf(calculatedAttendnace[0][6]));
				double penaltyAdjusted = Double.parseDouble(String.valueOf(calculatedAttendnace[0][8]));
				double unauthAdjusted = Double.parseDouble(String.valueOf(calculatedAttendnace[0][15]));
				String totalPaidLeaves = String.valueOf(paidLeaves + penaltyAdjusted + unauthAdjusted);				
				if(lmInHrsEnabled) {
					String[] paidLeavesHrs = String.valueOf(calculatedAttendnace[0][7]).split(":");
					totalPaidLeaves += " d " + paidLeavesHrs[0] + " h " + paidLeavesHrs[1] + " m";
				}
				
				// unpaid leaves
				double unpaidLeaves = Double.parseDouble(String.valueOf(calculatedAttendnace[0][9]));
				double penaltyUnAdjusted = Double.parseDouble(String.valueOf(calculatedAttendnace[0][11]));
				double unauthUnAdjusted = Double.parseDouble(String.valueOf(calculatedAttendnace[0][16]));
				String totalUnpaidLeaves = String.valueOf(unpaidLeaves + penaltyUnAdjusted + unauthUnAdjusted);				
				if(lmInHrsEnabled) {
					String[] unpaidLeavesHrs = String.valueOf(calculatedAttendnace[0][10]).split(":");
					totalUnpaidLeaves += " d " + unpaidLeavesHrs[0] + " h " + unpaidLeavesHrs[1] + " m";
				}
				
				// salary days
				String salaryDays = String.valueOf(calculatedAttendnace[0][17]);
				if(lmInHrsEnabled) {
					String[] salaryHrs = String.valueOf(calculatedAttendnace[0][18]).split(":");
					salaryDays += " d " + salaryHrs[0] + " h " + salaryHrs[1] + " m";
				}
				
				String calculatedAttendance = newEmpId + "," + newEmpToken + "," + newEmpName + "," + newEmpBranchName 
				+ "," + attendanceDays // attendanceDays
				+ "," + String.valueOf(calculatedAttendnace[0][2]) // weeklyOffs
				+ "," + String.valueOf(calculatedAttendnace[0][1]) // holidays
				+ "," + lateMarks // lateMarks
				+ "," + String.valueOf(calculatedAttendnace[0][5]) // halfDays
				+ "," + totalPaidLeaves // paidLeaves
				+ "," + totalUnpaidLeaves // unPaidLeaves
				+ "," + String.valueOf(calculatedAttendnace[0][12]) // systemUnPaidLeaves
				+ "," + salaryDays // salaryDays
				+ "," + String.valueOf(calculatedAttendnace[0][20]); // totalAttendanceDays
				
				message = calculatedAttendance + "@" + "Employee added successfully!";
			} else {
				message = "N@Employee cannot be added!";
			}
		} catch(Exception e) {
			logger.error("Exception in attendanceProcess:" + e);
			message = "N@Employee cannot be added!";
		}
		return message;
	}
	
	/**
	 * Calculate the attendance
	 * @param month: Month for which attendance is to be calculated
	 * @param year: Year for which attendance is to be calculated
	 * @param empId: Employee for which attendance is to be calculated
	 * @param empDiv: Employee's division id
	 * @param empDept: Employee's department id
	 * @param empDesg: Employee's designation id
	 * @param empBranch: Employee's branch id
	 * @param empType: Employee's type id
	 * @param empShift: Employee's shift id
	 * @param empJoinDate: Employee's joining date
	 * @param reCalculate: Recalculate flag, while recalculation it is true, other wise false
	 * @param request: HttpServletRequest object
	 * @return Object[][] as calculated object
	 */
	private Object[][] attendanceProcess(String month, String year, String empId, String empDiv, Object[][] employee, boolean reCalculate, 
		boolean lmInHrsEnabled, MonthAttendanceProcessModel monthAtdnProc, javax.servlet.http.HttpServletRequest request) {
		Object[][] calculatedAttendnace = null;
		try {
			monthAtdnProc.loadAttendanceSettings(); // Load attendance settings
			Object[][] attendanceSettings = monthAtdnProc.getAttendanceSettings();
			
			boolean leaveConnFlag = Boolean.parseBoolean(String.valueOf(attendanceSettings[0][0]));
			boolean branchHoliDayFlag = Boolean.parseBoolean(String.valueOf(attendanceSettings[0][1]));
			boolean dailyAttnConnFlag = Boolean.parseBoolean(String.valueOf(attendanceSettings[0][2]));
			boolean uploadAttnConnFlag = Boolean.parseBoolean(String.valueOf(attendanceSettings[0][6]));
			
			// If month is in between 1 to 9, then append 0 before the month
			month = Integer.parseInt(month) < 10 ? "0" + month : month;
			
			// Calculate fromDate and toDate
			String salaryStartDate = String.valueOf(attendanceSettings[0][3]);
			String salaryStartMonth = String.valueOf(attendanceSettings[0][4]);
			
			String fromDate = monthAtdnProc.setFromDate(dailyAttnConnFlag, month, year, salaryStartDate, salaryStartMonth);
			String toDate = monthAtdnProc.setToDate(dailyAttnConnFlag, month, year, salaryStartDate, salaryStartMonth);
			
			// Set leave policy information
			LeavePolicyData leavePolicyData = new LeavePolicyData(empDiv);
			leavePolicyData.initiate(context, session);
			leavePolicyData.setLeavePolicyObject();
			
			String attnConfQuery= "SELECT NVL(CONF_DEFAULT_DAYS_FLAG,'A') FROM HRMS_ATTENDANCE_CONF";
			Object [][] attnConfObj=getSqlModel().getSingleResult(attnConfQuery);
			// Load attendance details
			
			monthAtdnProc.loadAttendanceDetails(empId, leavePolicyData, branchHoliDayFlag, dailyAttnConnFlag, uploadAttnConnFlag, fromDate, toDate, 
				month, year, salaryStartMonth,String.valueOf(attnConfObj[0][0]));
			
			// if recalculation is to be done
			if(reCalculate) {
				boolean leaveUnauthFlag = Boolean.parseBoolean(request.getParameter("leaveUnauthFlag"));
				
				// get leave details
				String[] leaveId = request.getParameterValues("leaveId");
				String[] openingBalance = request.getParameterValues("openingBalance");				
				String[] paidLeavesAdjusted = request.getParameterValues("paidLeavesAdjusted");
				String[] unauthorisedAdj = null;
				if(leaveUnauthFlag) {
					unauthorisedAdj = request.getParameterValues("unauthorisedAdj");
				}
				String[] lateMarksAdjusted = request.getParameterValues("lateMarksAdjusted");				
				String[] halfDaysAdjusted = request.getParameterValues("halfDaysAdjusted");
				String[] manuallyAdjusted = request.getParameterValues("manuallyAdjusted");				
				String[] currentBalance = request.getParameterValues("currentBalance");
				
				String[] openingHrsBalance = null, lateMarksHrsAdjusted = null, manuallyHrsAdjusted = null, currentHrsBalance = null;
				
				if(lmInHrsEnabled) {
					openingHrsBalance = request.getParameterValues("openingHrsBalance");
					lateMarksHrsAdjusted = request.getParameterValues("lateMarksHrsAdjusted");
					manuallyHrsAdjusted = request.getParameterValues("manuallyHrsAdjusted");
					currentHrsBalance = request.getParameterValues("currentHrsBalance");
				}
				
				if(leaveId != null && leaveId.length > 0) {
					// create an object for leave balances
					Object[][] leavesAndBalances = new Object[leaveId.length][7];
					
					for(int i = 0; i < leaveId.length; i++) {
						leavesAndBalances[i][0] = empId;
						leavesAndBalances[i][1] = leaveId[i];
						leavesAndBalances[i][2] = openingBalance[i];
						leavesAndBalances[i][3] = paidLeavesAdjusted[i];
						
						double adjustedUnauth = 0.0;
						if(leaveUnauthFlag) {
							adjustedUnauth = Double.parseDouble(String.valueOf(unauthorisedAdj[i]));
						}
						double adjustedLateMarks = Double.parseDouble(String.valueOf(lateMarksAdjusted[i]));
						double adjustedHalfDays = Double.parseDouble(String.valueOf(halfDaysAdjusted[i]));
						double adjustedManually = Double.parseDouble(String.valueOf(manuallyAdjusted[i]));
						double closingBalance = Double.parseDouble(String.valueOf(currentBalance[i]));
						
						// set closing balance to original
						closingBalance += adjustedUnauth + adjustedLateMarks + adjustedHalfDays + adjustedManually;
						leavesAndBalances[i][4] = closingBalance;
						
						if(lmInHrsEnabled) {
							/**
							 * Calculate Opening Balance in Minutes
							 */
							int totalOpenBalMins = 0;
							
							double openBalDays = Double.parseDouble(openingBalance[i]);
							totalOpenBalMins += monthAtdnProc.getMinutes(openBalDays);							
							totalOpenBalMins += getMinutes(openingHrsBalance[i]);
							
							leavesAndBalances[i][5] = totalOpenBalMins;
							
							/**
							 * Calculate Closing Balance in minutes
							 */
							int totalCloseBalMins = 0;
							
							totalCloseBalMins += monthAtdnProc.getMinutes(closingBalance);
							totalCloseBalMins += getMinutes(lateMarksHrsAdjusted[i]);
							totalCloseBalMins += getMinutes(manuallyHrsAdjusted[i]);
							totalCloseBalMins += getMinutes(currentHrsBalance[i]);
							
							leavesAndBalances[i][6] = totalCloseBalMins;
						} else {
							leavesAndBalances[i][5] = 0; // opening balance in minutes
							leavesAndBalances[i][6] = 0; // closing balance in minutes
						}
					}
					//monthAtdnProc.setLeavesAndBalances(leavesAndBalances);
				}
			}
			
			// Calculate the attendance
			calculatedAttendnace = monthAtdnProc.getCalculatedAttendance(employee, fromDate, toDate, month, year, branchHoliDayFlag, leaveConnFlag, 
				dailyAttnConnFlag, uploadAttnConnFlag, leavePolicyData);
		} catch(Exception e) {
			logger.error("Exception in attendanceProcess:" + e);
		}
		return calculatedAttendnace;
	}
	
	public String deleteAttendance(String month, String year, String deletedRecords, String attendanceCode) {
		String message = "";
		try {
			/**
			 * Delete records from HRMS_MONTH_ATTENDANCE_<year>
			 */
			String deleteAttendance = " DELETE FROM HRMS_MONTH_ATTENDANCE_" + year +
			" WHERE ATTN_CODE = " + attendanceCode + " AND ATTN_EMP_ID IN(" + deletedRecords + ") ";
			boolean isRecordDeleted = getSqlModel().singleExecute(deleteAttendance);
			
			Object[][] attendanceDetailsObj = null;
			
			/**
			 * Delete records from HRMS_MONTH_ATT_DTL_<year> and update leave balances in HRMS_LEAVE_BALANCE by deducting late marks 
			 * and half days adjusted
			 */
			if(isRecordDeleted) {
				String attendanceDetailsSql = " SELECT NVL(CASE WHEN (CASE WHEN (SELECT CONF_LEAVE_CONNECT_FLAG " +
				" FROM HRMS_ATTENDANCE_CONF) = 'Y' THEN NVL(LEAVE_DAYS, 0) ELSE 0 END) < ATT_LEAVE_ADJUST " +
				" THEN ATT_LEAVE_ADJUST - (CASE WHEN (SELECT CONF_LEAVE_CONNECT_FLAG FROM HRMS_ATTENDANCE_CONF) = 'Y' " +
				" THEN NVL(LEAVE_DAYS, 0) ELSE 0 END) ELSE 0 END, 0) + NVL(ATT_LATEMARK_ADJUST, 0) + NVL(ATT_HALFDAY_ADJUST, 0) + " +
				" NVL(ATT_MANUAL_ADJUST, 0) + NVL(ATT_UNAUTHORISED_ADJUST, 0) AS ADJUSTED, ATT_EMP_CODE, ATT_LEAVE_CODE " +
				" FROM HRMS_MONTH_ATT_DTL_" + year +
				" LEFT JOIN HRMS_LEAVE_DTLHISTORY ON(HRMS_LEAVE_DTLHISTORY.LEAVE_CODE = HRMS_MONTH_ATT_DTL_" + year + ".ATT_LEAVE_CODE " +
				" AND HRMS_LEAVE_DTLHISTORY.EMP_ID = HRMS_MONTH_ATT_DTL_" + year + ".ATT_EMP_CODE) " +
				" WHERE ATT_CODE = " + attendanceCode + " AND ATT_EMP_CODE IN (" + deletedRecords + ") ";
				attendanceDetailsObj = getSqlModel().getSingleResult(attendanceDetailsSql);

				String deleteAttendanceDetails = " DELETE FROM HRMS_MONTH_ATT_DTL_" + year +
				" WHERE ATT_CODE = " + attendanceCode + " AND ATT_EMP_CODE IN (" + deletedRecords + ") ";
				isRecordDeleted = getSqlModel().singleExecute(deleteAttendanceDetails);
			}

			if(isRecordDeleted) {
				if(attendanceDetailsObj != null && attendanceDetailsObj.length > 0) {
					String updateLeaveBalances = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? " +
					" WHERE EMP_ID = ? AND LEAVE_CODE = ? ";
					isRecordDeleted = getSqlModel().singleExecute(updateLeaveBalances, attendanceDetailsObj);
				}
			}

			if(isRecordDeleted) {
				message = "Employee deleted successfully!";
			} else {
				message = "Employee cannot be deleted!";
			}
		} catch(Exception e) {
			logger.error("Exception in deleteAttendance:" + e);
			message = "Employee cannot be deleted!";
		}
		return message;
	}
	
	public Object[][] getAttendanceDetails(String month, String year, String empId, String attendanceCode) {
		Object[][] attendanceDetails = null;
		try {
			
			
			String attendanceDetailsSql = " SELECT ATTN_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_LNAME AS EMP_NAME, CENTER_NAME, " +
			" NVL(ATTN_DAYS, 0) AS ATTN_DAYS, NVL(TO_CHAR(ATTN_HRS, 'HH24:MI'), '00:00') AS ATTN_HRS, " +
			" NVL(ATTN_WOFF, 0) AS ATTN_WOFF, NVL(ATTN_HOLIDAY, 0) AS ATTN_HOLIDAY, NVL(ATTN_LATE_MARKS, 0) AS ATTN_LATE_MARKS, " +
			" NVL(TO_CHAR(ATTN_LATE_MARKS_HRS, 'HH24:MI'), '00:00') AS ATTN_LATE_MARKS_HRS, " +
			" NVL(ATTN_HALF_DAYS, 0) AS ATTN_HALF_DAYS, NVL(ATTN_PAID_LEVS, 0) AS ATTN_PAID_LEVS, " +
			" NVL(TO_CHAR(ATTN_PAID_LEVS_HRS, 'HH24:MI'), '00:00') AS ATTN_PAID_LEVS_HRS, " +
			" NVL(ATTN_PENALTY_ADJUSTED, 0) AS ATTN_PENALTY_ADJUSTED, NVL(ATTN_UNAUTH_ADJUSTED, 0) AS ATTN_UNAUTH_ADJUSTED, " +
			" NVL(ATTN_UNPAID_LEVS, 0) AS ATTN_UNPAID_LEVS, " +
			" NVL(TO_CHAR(ATTN_UNPAID_LEVS_HRS, 'HH24:MI'), '00:00') AS ATTN_UNPAID_LEVS_HRS, " +
			" NVL(ATTN_PENALTY_UNADJUSTED, 0) AS ATTN_PENALTY_UNADJUSTED, NVL(ATTN_UNAUTH_UNADJUSTED, 0) AS ATTN_UNAUTH_UNADJUSTED, " +
			" NVL(ATTN_SYSTEM_UNPAID, 0) AS ATTN_SYSTEM_UNPAID, NVL(ATTN_SAL_DAYS, 0) AS ATTN_SAL_DAYS, " +
			" NVL(TO_CHAR(ATTN_SAL_HRS, 'HH24:MI'), '00:00') AS ATTN_SAL_HRS, " +
			" CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN ((TO_NUMBER(TO_CHAR(LAST_DAY(TO_DATE('01-" + month + "-" + year + "', 'DD-MM-YYYY')), 'DD')) - " +
			" TO_NUMBER(TO_CHAR((CASE WHEN TO_CHAR(EMP_REGULAR_DATE, 'MM') = '" + month + "' THEN EMP_REGULAR_DATE " +
			" ELSE TO_DATE('01-" + month + "-" + year + "', 'DD-MM-YYYY') END), 'DD')) + 1) * " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0) + " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) * 60 " +
			" ELSE TO_NUMBER(TO_CHAR(LAST_DAY(TO_DATE('01-" + month + "-" + year + "', 'DD-MM-YYYY')), 'DD')) - " +
			" TO_NUMBER(TO_CHAR((CASE WHEN TO_CHAR(EMP_REGULAR_DATE, 'MM') = '" + month + "' THEN EMP_REGULAR_DATE " +
			" ELSE TO_DATE('01-" + month + "-" + year + "', 'DD-MM-YYYY') END), 'DD')) + 1 END AS TOTAL_ATTENDANCE_DAYS, EMP_CENTER, " +
			" EMP_DIV, EMP_DEPT, EMP_RANK, EMP_TYPE, TO_CHAR(EMP_REGULAR_DATE, 'DD-MM-YYYY') AS JOIN_DATE, " +
			" NVL(DECODE(AUTO_PRESENT_LATE_MARK, 'Y', 'true', 'N', 'false'), 'false') AS AUTO_PRESENT_LATE_MARK, " +
			" NVL(DECODE(AUTO_PRESENT_HALF_DAY, 'Y', 'true', 'N', 'false'), 'false') AS AUTO_PRESENT_HALF_DAY, " +
			" NVL(DECODE(AUTO_PRESENT_ABSENT, 'Y', 'true', 'N', 'false'), 'false') AS AUTO_PRESENT_ABSENT, " +
			" NVL(DECODE(SFT_LM_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_LM_ISENABLED, " +
			" NVL(DECODE(SFT_LM_NUMBER_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_LM_NUMBER_ISENABLED, " +
			" NVL(SFT_ADJUST_LM_COUNT, 0) AS SFT_ADJUST_LM_COUNT, NVL(SFT_ADJUST_LM_LEVDAYS, 0) AS SFT_ADJUST_LM_LEVDAYS, " +
			" NVL(SFT_ADJUST_LM_LEVTYPE, '') AS SFT_ADJUST_LM_LEVTYPE, " +
			" NVL(DECODE(SFT_LM_HRS_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_LM_HRS_ISENABLED, " +
			" NVL(SFT_DED_NONREGL_LM_LEVTYPE, '') AS SFT_DED_NONREGL_LM_LEVTYPE, " +
			" NVL(DECODE(SFT_HD_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_HD_ISENABLED, " +
			" NVL(SFT_DED_HD_LEVTYPE, '') AS SFT_DED_HD_LEVTYPE, " +
			" NVL(DECODE(SFT_FIXED_WO_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_FIXED_WO_ISENABLED, " +
			" NVL(SHIFT_WEEK_1, '') AS SHIFT_WEEK_1, NVL(SHIFT_WEEK_2, '') AS SHIFT_WEEK_2, NVL(SHIFT_WEEK_3, '') AS SHIFT_WEEK_3, " +
			" NVL(SHIFT_WEEK_4, '') AS SHIFT_WEEK_4, NVL(SHIFT_WEEK_5, '') AS SHIFT_WEEK_5, NVL(SHIFT_WEEK_6, '') AS SHIFT_WEEK_6, " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0) + " +
			" (NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) AS SFT_WORK_HRS, NVL(ATTN_RECOVERY_DAYS,0) " +
			" FROM HRMS_MONTH_ATTENDANCE_" + year +
			" INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_CODE = HRMS_SALARY_LEDGER.LEDGER_CODE) " +
			" INNER JOIN HRMS_EMP_OFFC ON(HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_EMP_ID = HRMS_EMP_OFFC.EMP_ID) " +
			" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " +
			" LEFT JOIN HRMS_AUTO_PRESENT ON (HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID = HRMS_EMP_OFFC.EMP_ID) " +
			" INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
			" WHERE ATTN_EMP_ID = " + empId + " AND ATTN_CODE = " + attendanceCode;
			
			attendanceDetails = getSqlModel().getSingleResult(attendanceDetailsSql);
		} catch(Exception e) {
			logger.error("Exception in getAttendanceDetails:" + e);
		}
		return attendanceDetails;
	}

	private Object[][] getEmployeeDetails(String empId) {
		Object[][] employee = null;
		try {
			String employeeSql = " SELECT EMP_ID, EMP_DIV, EMP_DEPT, EMP_RANK, EMP_CENTER, EMP_TYPE, " +
			" TO_CHAR(EMP_REGULAR_DATE, 'DD-MM-YYYY') AS JOIN_DATE, " +
			" NVL(DECODE(AUTO_PRESENT_LATE_MARK, 'Y', 'true', 'N', 'false'), 'false') AS AUTO_PRESENT_LATE_MARK, " +
			" NVL(DECODE(AUTO_PRESENT_HALF_DAY, 'Y', 'true', 'N', 'false'), 'false') AS AUTO_PRESENT_HALF_DAY, " +
			" NVL(DECODE(AUTO_PRESENT_ABSENT, 'Y', 'true', 'N', 'false'), 'false') AS AUTO_PRESENT_ABSENT, " +
			" NVL(DECODE(SFT_LM_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_LM_ISENABLED, " +
			" NVL(DECODE(SFT_LM_NUMBER_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_LM_NUMBER_ISENABLED, " +
			" NVL(SFT_ADJUST_LM_COUNT, 0) AS SFT_ADJUST_LM_COUNT, NVL(SFT_ADJUST_LM_LEVDAYS, 0) AS SFT_ADJUST_LM_LEVDAYS, " +
			" NVL(SFT_ADJUST_LM_LEVTYPE, '') AS SFT_ADJUST_LM_LEVTYPE, " +
			" NVL(DECODE(SFT_LM_HRS_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_LM_HRS_ISENABLED, " +
			" NVL(SFT_DED_NONREGL_LM_LEVTYPE, '') AS SFT_DED_NONREGL_LM_LEVTYPE, " +
			" NVL(DECODE(SFT_HD_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_HD_ISENABLED, " +
			" NVL(SFT_DED_HD_LEVTYPE, '') AS SFT_DED_HD_LEVTYPE, " +
			" NVL(DECODE(SFT_FIXED_WO_ISENABLED, 'Y', 'true', 'N', 'false'), 'false') AS SFT_FIXED_WO_ISENABLED, " +
			" NVL(SHIFT_WEEK_1, '') AS SHIFT_WEEK_1, NVL(SHIFT_WEEK_2, '') AS SHIFT_WEEK_2, NVL(SHIFT_WEEK_3, '') AS SHIFT_WEEK_3, " +
			" NVL(SHIFT_WEEK_4, '') AS SHIFT_WEEK_4, NVL(SHIFT_WEEK_5, '') AS SHIFT_WEEK_5, NVL(SHIFT_WEEK_6, '') AS SHIFT_WEEK_6, " +
			" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0) + " +
			" (NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) AS SFT_WORK_HRS " +
			" FROM HRMS_EMP_OFFC " +
			" INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
			" LEFT JOIN HRMS_AUTO_PRESENT ON (HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID = HRMS_EMP_OFFC.EMP_ID) " +
			" WHERE EMP_ID = " + empId;
			employee = getSqlModel().getSingleResult(employeeSql);
		} catch(Exception e) {
			logger.error("Exception in getEmployeeDetails:" + e);
		}
		return employee;
	}
	
	private Object[][] getEmployeeInfo(javax.servlet.http.HttpServletRequest request) {
		Object[][] employee = null;
		try {
			// create employee's object
			employee = new Object[1][27];
			
			employee[0][0] = request.getParameter("empId");
			employee[0][1] = request.getParameter("empDivisionId");
			employee[0][2] = request.getParameter("empDepartmentId");
			employee[0][3] = request.getParameter("empDesignationId");
			employee[0][4] = request.getParameter("empBranchId");
			employee[0][5] = request.getParameter("empTypeId");
			employee[0][6] = request.getParameter("empJoinDate");
			
			employee[0][7] = Boolean.parseBoolean(request.getParameter("autoPresentLateMark"));
			employee[0][8] = Boolean.parseBoolean(request.getParameter("autoPresentHalfDay"));
			employee[0][9] = Boolean.parseBoolean(request.getParameter("autoPresentAbsent"));
			
			employee[0][10] = Boolean.parseBoolean(request.getParameter("lmEnabled"));
			employee[0][11] = Boolean.parseBoolean(request.getParameter("lmInNoEnabled"));
			employee[0][12] = Integer.parseInt(request.getParameter("lmCount"));
			employee[0][13] = Double.parseDouble(request.getParameter("lmAdjustLeaveDays"));
			employee[0][14] = request.getParameter("lmAdjustLeaveTypes");
			
			boolean lmInHrsEnabled = Boolean.parseBoolean(request.getParameter("lmInHrsEnabled"));
			employee[0][15] = lmInHrsEnabled;
			employee[0][16] = request.getParameter("lmDeductNonRegLeaveTypes");
			
			employee[0][17] = Boolean.parseBoolean(request.getParameter("hdEnabled"));
			employee[0][18] = request.getParameter("hdDeductLeaveTypes");
			
			employee[0][19] = Boolean.parseBoolean(request.getParameter("woFixedEnabled"));
			employee[0][20] = request.getParameter("woDaysForWeek1");
			employee[0][21] = request.getParameter("woDaysForWeek2");
			employee[0][22] = request.getParameter("woDaysForWeek3");
			employee[0][23] = request.getParameter("woDaysForWeek4");
			employee[0][24] = request.getParameter("woDaysForWeek5");
			employee[0][25] = request.getParameter("woDaysForWeek6");
			
			employee[0][26] = request.getParameter("workingHours");
		} catch(Exception e) {
			logger.error("Exception in getEmployeeInfo:" + e);
		}
		return employee;
	}

	public int getMinutes(String hours) {
		int minutes = 0;
		try {
			String[] hoursAndMins = hours.split(":");
			minutes = (Integer.parseInt(hoursAndMins[0]) * 60) + Integer.parseInt(hoursAndMins[1]);
		} catch(Exception e) {
			logger.error("Exception in getMinutes:" + e);
		}
		return minutes;
	}

	private Object[][] getRevisedAttendanceDetails(Object[][] viewAttendanceDetails, boolean lmInHrsEnabled, double workingHours) {
		Object[][] attendanceDetails = null;
		try {
			if(viewAttendanceDetails != null && viewAttendanceDetails.length > 0) {
				MonthAttendanceProcessModel monthAtdnProc = new MonthAttendanceProcessModel();
				monthAtdnProc.setWorkingHours(workingHours);
				
				if(lmInHrsEnabled) {
					attendanceDetails = new Object[viewAttendanceDetails.length][13];
				} else {
					attendanceDetails = new Object[viewAttendanceDetails.length][9];
				}
				
				for(int i = 0; i < viewAttendanceDetails.length; i++) {
					attendanceDetails[i][0] = viewAttendanceDetails[i][0]; // leave code
					attendanceDetails[i][1] = viewAttendanceDetails[i][1]; // leave abbr
					
					if(lmInHrsEnabled) {
						/**
						 * Opening balance
						 */
						// calculate total minutes
						int openingBalanceMins = Integer.parseInt(String.valueOf(viewAttendanceDetails[i][3]));
						
						// divide total minutes into days and remaining minutes
						String[] openingBalDaysAndMins = monthAtdnProc.getDaysAndRemainingMins(openingBalanceMins).split(",");
						
						// get days from minutes
						attendanceDetails[i][2] = openingBalDaysAndMins[0];
						
						// calculate hours from remaining minutes
						attendanceDetails[i][3] = monthAtdnProc.getHours(Integer.parseInt(openingBalDaysAndMins[1]));
						
						/**
						 * Leaves Adjusted
						 */
						attendanceDetails[i][4] = viewAttendanceDetails[i][4];
						
						/**
						 * Unauthorised Adjusted
						 */
						attendanceDetails[i][5] = viewAttendanceDetails[i][5];
						
						/**
						 * Late Marks Adjusted
						 */
						// calculate total minutes
						int lateMarkMinsAdjusted = Integer.parseInt(String.valueOf(viewAttendanceDetails[i][7]));
						
						// divide total minutes into days and remaining minutes
						String[] lateMarkDaysAndMins = monthAtdnProc.getDaysAndRemainingMins(lateMarkMinsAdjusted).split(",");
						
						// get days from minutes
						attendanceDetails[i][6] = lateMarkDaysAndMins[0];
						
						// calculate hours from remaining minutes
						attendanceDetails[i][7] = monthAtdnProc.getHours(Integer.parseInt(lateMarkDaysAndMins[1]));
						
						/**
						 * Half Days Adjusted
						 */
						attendanceDetails[i][8] = viewAttendanceDetails[i][8];
						
						/**
						 * Manually Adjusted
						 */
						// calculate total minutes
						int manuallyMinsAdjusted = Integer.parseInt(String.valueOf(viewAttendanceDetails[i][10]));
						
						// divide total minutes into days and remaining minutes
						String[] manualDaysAndMins = monthAtdnProc.getDaysAndRemainingMins(manuallyMinsAdjusted).split(",");
						
						// get days from minutes
						attendanceDetails[i][9] = manualDaysAndMins[0];
						
						// calculate hours from remaining minutes
						attendanceDetails[i][10] = monthAtdnProc.getHours(Integer.parseInt(manualDaysAndMins[1]));
						
						/**
						 * Closing Balance
						 */
						// calculate total minutes
						int closingBalanceMins = Integer.parseInt(String.valueOf(viewAttendanceDetails[i][12]));
						
						// divide total minutes into days and remaining minutes
						String[] closingBalDaysAndMins = monthAtdnProc.getDaysAndRemainingMins(closingBalanceMins).split(",");
						
						// get days from minutes
						attendanceDetails[i][11] = closingBalDaysAndMins[0];
						
						// calculate hours from remaining minutes
						attendanceDetails[i][12] = monthAtdnProc.getHours(Integer.parseInt(closingBalDaysAndMins[1]));
					} else {
						/**
						 * Opening Balance
						 */
						attendanceDetails[i][2] = viewAttendanceDetails[i][2];
						
						/**
						 * Leaves Adjusted
						 */
						attendanceDetails[i][3] = viewAttendanceDetails[i][4];
						
						/**
						 * Unauthorised Adjusted
						 */
						attendanceDetails[i][4] = viewAttendanceDetails[i][5];
						
						/**
						 * Late Marks Adjusted
						 */
						attendanceDetails[i][5] = viewAttendanceDetails[i][6];
						
						/**
						 * Half Days Adjusted
						 */
						attendanceDetails[i][6] = viewAttendanceDetails[i][8];
						
						/**
						 * Manually Adjusted
						 */
						attendanceDetails[i][7] = viewAttendanceDetails[i][9];
						
						/**
						 * Closing Balance
						 */
						attendanceDetails[i][8] = viewAttendanceDetails[i][11];
					}
				}
				monthAtdnProc.terminate();
			}
		} catch(Exception e) {
			logger.error("Exception in getRevisedAttendanceDetails:" + e);
		}
		return attendanceDetails;
	}
	
	/**
	 * Locks the Attendance
	 * @param month: Month of attendance to lock
	 * @param year: Year of attendance to lock
	 * @param listOfFilters: Contains list of filters selected from a page
	 * @return String as message whether attendance is locked successfully or not
	 */
	public String lockAttendance(String month, String year, String attendanceCode, String newStatus) {
		String message = "Record cannot be locked!";
		try {
			MonthAttendanceProcessModel monthAttendanceProcessModel = new MonthAttendanceProcessModel();
			monthAttendanceProcessModel.initiate(context, session);
			
			String lockAttendanceSql = " UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS = '" + newStatus + "' WHERE LEDGER_CODE =  " + attendanceCode;
			boolean isDataSaved = getSqlModel().singleExecute(lockAttendanceSql);
			
			if(isDataSaved) {
				if(newStatus.equals("ATTN_START") || newStatus.equals("ATTN_UNLOCK")) {
					message = "Record unlocked successfully!";
				} else {
					message = "Record locked successfully!";
				}
			}
		} catch(Exception e) {
			logger.error("Exception in lockAttendance:" + e);
			
			if(newStatus.equals("ATTN_START") || newStatus.equals("ATTN_UNLOCK")) {
				message = "Record cannot be unlocked!";
			} else {
				message = "Record cannot be locked!";
			}
		}
		return message;
	}
	
	public Object[][] recalculateAttendance(javax.servlet.http.HttpServletRequest request, boolean lmInHrsEnabled) {
		Object[][] reCalculatedAttendance = null;
		try {
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			
			Object[][] employee = getEmployeeInfo(request);
			
			String empId = String.valueOf(employee[0][0]);
			String empDiv = String.valueOf(employee[0][1]);
			
			MonthAttendanceProcessModel monthAtdnProc = new MonthAttendanceProcessModel();
			monthAtdnProc.initiate(context, session);
			monthAtdnProc.setWorkingHours(Double.parseDouble(String.valueOf(employee[0][26])));
			
			reCalculatedAttendance = attendanceProcess(month, year, empId, empDiv, employee, true, lmInHrsEnabled, monthAtdnProc, request);
			
			// get attendance details
			Object[][] attendanceDetails = (Object[][]) reCalculatedAttendance[0][19];
			
			Object[][] viewAttendanceDetails = null;
			
			if(attendanceDetails != null && attendanceDetails.length > 0) {
				String[] leaveType = request.getParameterValues("leaveType");
				
				if(lmInHrsEnabled) {
					viewAttendanceDetails = recalculateAttendanceOnHoursBasis(attendanceDetails, leaveType, monthAtdnProc);
				} else {
					viewAttendanceDetails = recalculateAttendanceOnDaysBasis(attendanceDetails, leaveType);
				}
			}
			
			request.setAttribute("viewAttendanceDetails", viewAttendanceDetails);			
		} catch(Exception e) {
			logger.error("Exception in recalculateAttendance:" + e);
		}
		return reCalculatedAttendance;
	}
	
	public Object[] recalculateAttendanceDetails(javax.servlet.http.HttpServletRequest request, Object[][] originalLeaveDetails, String empId, 
		boolean lmInHrsEnabled, MonthAttendanceProcessModel monthAtdnProc, int lateMarks, int lmCount, double lmAdjustLeaveDays, 
		String lmAdjustLeaveTypes, int lateMarksMins, int halfDays, String lmDeductNonRegLeaveTypes, String hdDeductLeaveTypes, 
		double systemUnPaidLeaves, int salaryMins, int unPaidLeavesMins, double salaryDays, double unPaidLeaves, double totalAttendanceDays, 
		double weeklyOffs, double holidays) {
		Object[] detailsOfAttendance = null;
		try {
			if(originalLeaveDetails != null && originalLeaveDetails.length > 0) {
				int originalTotalManuallyMinsAdjusted = 0;
				double originalTotalManuallyAdjusted = 0.0;
				
				// prepare object for recalculation
				Object[][] leaveDetails = new Object[originalLeaveDetails.length][7];
				
				for(int i = 0; i < originalLeaveDetails.length; i++) {
					leaveDetails[i][0] = empId;					
					leaveDetails[i][1] = originalLeaveDetails[i][0]; // leaveId
					
					// openingBalance
					if(lmInHrsEnabled) {
						leaveDetails[i][2] = String.valueOf(originalLeaveDetails[i][2]);
					} else {
						double openingBalance = Double.parseDouble(String.valueOf(originalLeaveDetails[i][2]));
						leaveDetails[i][2] = Math.round(openingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
					}
					
					// paid leaves
					leaveDetails[i][3] = String.valueOf(originalLeaveDetails[i][3]);
					
					// late marks
					int adjustedLateMarksMins = 0;
					double adjustedLateMarks = 0.0;
					if(lmInHrsEnabled) {
						adjustedLateMarksMins = Integer.parseInt(String.valueOf(originalLeaveDetails[i][4]));
						leaveDetails[i][4] = 0;
					} else {
						adjustedLateMarks = Double.parseDouble(String.valueOf(originalLeaveDetails[i][4]));
						leaveDetails[i][4] = 0.0;
					}
					
					// half days
					int adjustedHalfDaysMins = 0;
					double adjustedHalfDays = 0.0;
					if(lmInHrsEnabled) {
						adjustedHalfDaysMins = Integer.parseInt(String.valueOf(originalLeaveDetails[i][5]));
						leaveDetails[i][5] = 0;
					} else {
						adjustedHalfDays = Double.parseDouble(String.valueOf(originalLeaveDetails[i][5]));
						leaveDetails[i][5] = 0.0;
					}					
					
					// closing balance
					int closingBalanceMins = 0;
					double closingBalance = 0;
					if(lmInHrsEnabled) {
						closingBalanceMins = Integer.parseInt(String.valueOf(originalLeaveDetails[i][7])) + adjustedLateMarksMins +
						adjustedHalfDaysMins;
						leaveDetails[i][6] = closingBalanceMins;
					} else {
						closingBalance = Double.parseDouble(String.valueOf(originalLeaveDetails[i][7])) + adjustedLateMarks + 
						adjustedHalfDays;
						leaveDetails[i][6] = Math.round(closingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
					}					
					
					// calculate original total manually adjusted
					if(lmInHrsEnabled) {
						originalTotalManuallyMinsAdjusted += Integer.parseInt(String.valueOf(originalLeaveDetails[i][6]));
					} else {
						originalTotalManuallyAdjusted += Double.parseDouble(String.valueOf(originalLeaveDetails[i][6]));
					}
				}
				
				// load attendance settings
				monthAtdnProc.loadAttendanceSettings();
				
				// adjust late marks and half days newly
				Object[][] attendanceDetails = null;
				if(lmInHrsEnabled) {
					attendanceDetails = monthAtdnProc.adjustLateMarksHalfDaysOnHoursBasis(leaveDetails, lateMarksMins, halfDays, 
						lmDeductNonRegLeaveTypes, hdDeductLeaveTypes);
				} else {
					attendanceDetails = monthAtdnProc.adjustLateMarksHalfDaysOnDaysBasis(leaveDetails, lateMarks, halfDays, lmCount, 
						lmAdjustLeaveDays, lmAdjustLeaveTypes, hdDeductLeaveTypes);
				}
				
				if(attendanceDetails != null && attendanceDetails.length > 0) {
					// prepare object to view newly calculation leave details
					Object[][] viewAttendanceDetails = null;
					
					double totalAdjustedManually = 0.0, totalPaidLeaves = 0.0;
					int totalAdjustedManuallyMins = 0, totalPaidLeavesMins = 0;
					
					if(lmInHrsEnabled) {
						viewAttendanceDetails = new Object[attendanceDetails.length][13];
						
						for(int i = 0; i < attendanceDetails.length; i++) {
							viewAttendanceDetails[i][0] = attendanceDetails[i][1]; // leaveId
							viewAttendanceDetails[i][1] = originalLeaveDetails[i][1]; // leaveType
							
							// adjusted paid leaves
							double adjustedPaidLeaves = Double.parseDouble(String.valueOf(attendanceDetails[i][3]));
							int adjustedPaidLeavesMins = monthAtdnProc.getMinutes(adjustedPaidLeaves);
							viewAttendanceDetails[i][4] = String.valueOf(attendanceDetails[i][3]);
							
							// adjusted unauthorised
							double adjustedUnauth = Double.parseDouble(String.valueOf(originalLeaveDetails[i][8]));
							int adjustedUnauthMins = monthAtdnProc.getMinutes(adjustedUnauth);
							viewAttendanceDetails[i][5] = String.valueOf(originalLeaveDetails[i][8]);
							
							// adjusted late marks
							int adjustedLateMarksMins = Integer.parseInt(String.valueOf(attendanceDetails[i][4]));
							String[] lmDaysAndRemMins = monthAtdnProc.getDaysAndRemainingMins(adjustedLateMarksMins).split(",");
							viewAttendanceDetails[i][6] = lmDaysAndRemMins[0];
							viewAttendanceDetails[i][7] = monthAtdnProc.getHours(Integer.parseInt(lmDaysAndRemMins[1]));
							
							// adjusted half days
							double adjustedHalfDays = Double.parseDouble(String.valueOf(attendanceDetails[i][5]));
							int adjustedHalfDaysMins = monthAtdnProc.getMinutes(adjustedHalfDays);
							viewAttendanceDetails[i][8] = String.valueOf(attendanceDetails[i][5]);
							
							// adjusted manually
							int adjustedManuallyMins = Integer.parseInt(String.valueOf(originalLeaveDetails[i][6]));
							String[] manualDaysAndRemMins = monthAtdnProc.getDaysAndRemainingMins(adjustedManuallyMins).split(",");
							viewAttendanceDetails[i][9] = manualDaysAndRemMins[0];
							viewAttendanceDetails[i][10] = monthAtdnProc.getHours(Integer.parseInt(manualDaysAndRemMins[1]));
							
							// closing balance
							int closingBalanceMins = Integer.parseInt(String.valueOf(attendanceDetails[i][6]));
							String[] closeBalDaysAndRemMins = monthAtdnProc.getDaysAndRemainingMins(closingBalanceMins).split(",");
							viewAttendanceDetails[i][11] = closeBalDaysAndRemMins[0];
							viewAttendanceDetails[i][12] = monthAtdnProc.getHours(Integer.parseInt(closeBalDaysAndRemMins[1]));
							
							// opening balance
							int openingBalanceMins = adjustedPaidLeavesMins + adjustedUnauthMins + adjustedLateMarksMins + 
							adjustedHalfDaysMins + adjustedManuallyMins + closingBalanceMins;
							String[] openBalDaysAndRemMins = monthAtdnProc.getDaysAndRemainingMins(openingBalanceMins).split(",");
							viewAttendanceDetails[i][2] = openBalDaysAndRemMins[0];
							viewAttendanceDetails[i][3] = monthAtdnProc.getHours(Integer.parseInt(openBalDaysAndRemMins[1]));
							
							// calculate total adjusted manually
							totalAdjustedManuallyMins += adjustedManuallyMins;
							
							// calculate total paid leaves
							totalPaidLeavesMins += adjustedPaidLeavesMins + adjustedLateMarksMins + adjustedHalfDaysMins + adjustedManuallyMins;
						}
					} else {
						viewAttendanceDetails = new Object[attendanceDetails.length][9];
						
						for(int i = 0; i < attendanceDetails.length; i++) {
							viewAttendanceDetails[i][0] = attendanceDetails[i][1]; // leaveId
							viewAttendanceDetails[i][1] = originalLeaveDetails[i][1]; // leaveType
							
							// adjusted paid leaves
							double adjustedPaidLeaves = Double.parseDouble(String.valueOf(attendanceDetails[i][3]));
							viewAttendanceDetails[i][3] = String.valueOf(attendanceDetails[i][3]);
							
							// adjusted unauthorised
							double adjustedUnauth = Double.parseDouble(String.valueOf(originalLeaveDetails[i][8]));
							viewAttendanceDetails[i][4] = String.valueOf(originalLeaveDetails[i][8]);
							
							// adjusted late marks
							double adjustedLateMarks = Double.parseDouble(String.valueOf(attendanceDetails[i][4]));
							viewAttendanceDetails[i][5] = String.valueOf(attendanceDetails[i][4]);
							
							// adjusted half days
							double adjustedHalfDays = Double.parseDouble(String.valueOf(attendanceDetails[i][5]));
							viewAttendanceDetails[i][6] = String.valueOf(attendanceDetails[i][5]);
							
							// adjusted manually							
							double adjustedManually = Double.parseDouble(String.valueOf(originalLeaveDetails[i][6]));
							viewAttendanceDetails[i][7] = String.valueOf(originalLeaveDetails[i][6]);
							
							// closing balance							
							double closingBalance = Double.parseDouble(String.valueOf(attendanceDetails[i][6]));
							viewAttendanceDetails[i][8] = Math.round(closingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
							
							double openingBalance = adjustedPaidLeaves + adjustedUnauth + adjustedLateMarks + adjustedHalfDays + 
							adjustedManually + closingBalance;
							viewAttendanceDetails[i][2] = Math.round(openingBalance * Math.pow(10, 2)) / Math.pow(10, 2);						
							
							// calculate total adjusted manually
							totalAdjustedManually += adjustedManually;
							
							// calculate total paid leaves
							totalPaidLeaves += adjustedPaidLeaves + adjustedLateMarks + adjustedHalfDays + adjustedManually;
						}
					}
										
					// set newly calculated leave details on a page
					request.setAttribute("viewAttendanceDetails", viewAttendanceDetails);
					
					if(lmInHrsEnabled) {
						// get unpaid late marks minutes and half days after calculation
						int unPaidLateMarksMins = monthAtdnProc.getNumOfUnPaidLateMarkMins();
						
						double unPaidHalfDays = monthAtdnProc.getNumOfUnPaidHalfDays();
						int unPaidHalfDaysMins = monthAtdnProc.getMinutes(unPaidHalfDays);
						
						// calculate total unpaid leave minutes
						int systemUnPaidLeavesMins = monthAtdnProc.getMinutes(systemUnPaidLeaves);
						int totalUnPaidLeavesMins = systemUnPaidLeavesMins + unPaidLateMarksMins + unPaidHalfDaysMins;
						
						// calculate attendance minutes
						int totalAttendanceDaysMins = (int) totalAttendanceDays;
						int weeklyOffsMins = monthAtdnProc.getMinutes(weeklyOffs);
						int holidaysMins = monthAtdnProc.getMinutes(holidays);
						int attendanceMins = totalAttendanceDaysMins - (weeklyOffsMins + holidaysMins + totalPaidLeavesMins + totalUnPaidLeavesMins);
						
						// calculate salary minutes
						salaryMins = (salaryMins + unPaidLeavesMins) - totalUnPaidLeavesMins;
						
						/**
						 * prepare object to set attendance details on a page
						 */
						detailsOfAttendance = new Object[8];
						// calculate attendance days and hours
						String[] atdnDaysAndRemMins = monthAtdnProc.getDaysAndRemainingMins(attendanceMins).split(",");
						detailsOfAttendance[0] = atdnDaysAndRemMins[0]; // days
						detailsOfAttendance[1] = monthAtdnProc.getHours(Integer.parseInt(atdnDaysAndRemMins[1])); // hours
						
						// calculate total paid leave days and hours
						String[] totalPaidLeaveDaysAndMins = monthAtdnProc.getDaysAndRemainingMins(totalPaidLeavesMins).split(",");
						detailsOfAttendance[2] = totalPaidLeaveDaysAndMins[0]; // days
						detailsOfAttendance[3] = monthAtdnProc.getHours(Integer.parseInt(totalPaidLeaveDaysAndMins[1])); // hours
						
						// calculate total unpaid leave days and hours
						String[] totalUnPaidLeaveDaysAndMins = monthAtdnProc.getDaysAndRemainingMins(totalUnPaidLeavesMins).split(",");
						detailsOfAttendance[4] = totalUnPaidLeaveDaysAndMins[0]; // days
						detailsOfAttendance[5] = monthAtdnProc.getHours(Integer.parseInt(totalUnPaidLeaveDaysAndMins[1])); // hours
						
						// calculate salary days and hours
						String[] salaryDaysAndRemMins = monthAtdnProc.getDaysAndRemainingMins(salaryMins).split(",");
						detailsOfAttendance[6] = salaryDaysAndRemMins[0]; // days
						detailsOfAttendance[7] = monthAtdnProc.getHours(Integer.parseInt(salaryDaysAndRemMins[1])); // hours
					} else {
						// get unpaid late marks and half days after calculation
						double unPaidLateMarks = monthAtdnProc.getNumOfUnPaidLateMarks();						
						double unPaidHalfDays = monthAtdnProc.getNumOfUnPaidHalfDays();
						
						// calculate total unpaid leaves
						double totalUnPaidLeaves = systemUnPaidLeaves + unPaidLateMarks + unPaidHalfDays;
						
						// calculate attendance days
						double attendanceDays = totalAttendanceDays - (weeklyOffs + holidays + totalPaidLeaves + totalUnPaidLeaves);
						attendanceDays = Math.round(attendanceDays * Math.pow(10, 2)) / Math.pow(10, 2);
						
						// calculate salary days
						salaryDays = (salaryDays + unPaidLeaves) - totalUnPaidLeaves;
						
						/**
						 * prepare object to set attendance details on a page
						 */
						detailsOfAttendance = new Double[4];
						detailsOfAttendance[0] = attendanceDays;
						detailsOfAttendance[1] = totalPaidLeaves;
						detailsOfAttendance[2] = totalUnPaidLeaves;
						detailsOfAttendance[3] = salaryDays;
					}					
				}
			}
		} catch(Exception e) {
			logger.error("Exception in recalculateAttendanceDetails:" + e);
		}
		return detailsOfAttendance;
	}
	
	private Object[][] recalculateAttendanceOnDaysBasis(Object[][] attendanceDetails, String[] leaveType) {
		Object[][] viewAttendanceDetails = null;
		try {
			viewAttendanceDetails = new Object[attendanceDetails.length][9];
			
			for(int i = 0; i < attendanceDetails.length; i++) {
				viewAttendanceDetails[i][0] = attendanceDetails[i][1]; // leaveId
				viewAttendanceDetails[i][1] = leaveType[i]; // leaveType

				// paid leave adjusted
				double adjustedPaidLeaves = Double.parseDouble(String.valueOf(attendanceDetails[i][4]));
				viewAttendanceDetails[i][3] = adjustedPaidLeaves;
				
				// unauthorised adjusted
				double adjustedUnauth = Double.parseDouble(String.valueOf(attendanceDetails[i][10]));
				viewAttendanceDetails[i][4] = adjustedUnauth;
				
				// late marks adjusted in days
				double adjustedLateMarks = Double.parseDouble(String.valueOf(attendanceDetails[i][5]));
				viewAttendanceDetails[i][5] = adjustedLateMarks;
				
				// half days adjusted
				double adjustedHalfDays = Double.parseDouble(String.valueOf(attendanceDetails[i][7]));
				viewAttendanceDetails[i][6] = adjustedHalfDays;
				
				// mannually adjusted in days
				viewAttendanceDetails[i][7] = 0.0;
				
				// closing balance in days
				double closingBalance = Double.parseDouble(String.valueOf(attendanceDetails[i][8]));
				viewAttendanceDetails[i][8] = Math.round(closingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
				
				// opening balance in days
				double openingBalance = adjustedPaidLeaves + adjustedUnauth + adjustedLateMarks + adjustedHalfDays + closingBalance;
				viewAttendanceDetails[i][2] = Math.round(openingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
			}		
		} catch(Exception e) {
			logger.error("Exception in recalculateAttendanceOnDaysBasis:" + e);
		}
		return viewAttendanceDetails;
	}

	private Object[][] recalculateAttendanceOnHoursBasis(Object[][] attendanceDetails, String[] leaveType, 
		MonthAttendanceProcessModel monthAtdnProc) {
		Object[][] viewAttendanceDetails = null;
		try {
			viewAttendanceDetails = new Object[attendanceDetails.length][13];
			
			for(int i = 0; i < attendanceDetails.length; i++) {
				viewAttendanceDetails[i][0] = attendanceDetails[i][1]; // leaveId
				viewAttendanceDetails[i][1] = leaveType[i]; // leaveType

				// paid leave adjusted
				double adjustedPaidLeaves = Double.parseDouble(String.valueOf(attendanceDetails[i][4]));
				viewAttendanceDetails[i][4] = adjustedPaidLeaves;
				
				// unauthorised adjusted
				double adjustedUnauth = Double.parseDouble(String.valueOf(attendanceDetails[i][10]));
				viewAttendanceDetails[i][5] = adjustedUnauth;
				
				// late marks adjusted in days
				double adjustedLateMarks = Double.parseDouble(String.valueOf(attendanceDetails[i][5]));
				viewAttendanceDetails[i][6] = adjustedLateMarks;
				
				// late marks adjusted in hrs
				int lateMarkMinsAdjusted = getMinutes(String.valueOf(attendanceDetails[i][6]));
				viewAttendanceDetails[i][7] = String.valueOf(attendanceDetails[i][6]);
				
				// half days adjusted
				double adjustedHalfDays = Double.parseDouble(String.valueOf(attendanceDetails[i][7]));
				viewAttendanceDetails[i][8] = adjustedHalfDays;
				
				// mannually adjusted in days
				viewAttendanceDetails[i][9] = 0.0;
				
				// mannually adjusted in hrs
				viewAttendanceDetails[i][10] = "00:00";
				
				// closing balance in days
				double closingBalance = Double.parseDouble(String.valueOf(attendanceDetails[i][8]));
				viewAttendanceDetails[i][11] = Math.round(closingBalance * Math.pow(10, 2)) / Math.pow(10, 2);
				
				// closing balance in hours
				int closingBalMins = getMinutes(String.valueOf(attendanceDetails[i][9]));
				viewAttendanceDetails[i][12] = String.valueOf(attendanceDetails[i][9]);
				
				// opening balance in days
				int totalMinutes = lateMarkMinsAdjusted + closingBalMins;						
				double totalDays = adjustedPaidLeaves + adjustedUnauth + adjustedLateMarks + adjustedHalfDays + closingBalance;
				totalMinutes += monthAtdnProc.getMinutes(totalDays);
				
				// divide total minutes into days and remaining minutes
				String[] openingBalDaysAndMins = monthAtdnProc.getDaysAndRemainingMins(totalMinutes).split(",");
				
				// opening balance in days
				viewAttendanceDetails[i][2] = openingBalDaysAndMins[0];
				
				// opening balance in hrs
				viewAttendanceDetails[i][3] = monthAtdnProc.getHours(Integer.parseInt(openingBalDaysAndMins[1]));
			}		
		} catch(Exception e) {
			logger.error("Exception in recalculateAttendanceOnHoursBasis:" + e);
		}
		return viewAttendanceDetails;
	}
	
	public String saveAttendance(javax.servlet.http.HttpServletRequest request) {
		String message = "";
		try {
			boolean lmInHrsEnabled = Boolean.parseBoolean(request.getParameter("lmInHrsEnabled"));
			boolean leaveUnauthFlag = Boolean.parseBoolean(request.getParameter("leaveUnauthFlag"));
			boolean leaveUnplanFlag = Boolean.parseBoolean(request.getParameter("leaveUnplanFlag"));
			
			String year = request.getParameter("year");
			String attendanceCode = request.getParameter("attendanceCode");
			String empId = request.getParameter("empId");
			String attendanceDays = request.getParameter("attendanceDays");
			String weeklyOffs = request.getParameter("weeklyOffs");
			String holidays = request.getParameter("holidays");
			String lateMarks = request.getParameter("lateMarks");
			String halfDays = request.getParameter("halfDays");
			String paidLeaves = request.getParameter("paidLeaves");
			String unPaidLeaves = request.getParameter("unPaidLeaves");
			String salaryDays = request.getParameter("salaryDays");
			String recoveryDays = request.getParameter("recoveryDays");	
			
			String[] leaveId = request.getParameterValues("leaveId");
			String[] leaveType = request.getParameterValues("leaveType");
			String[] openingBalance = request.getParameterValues("openingBalance");
			String[] paidLeavesAdjusted = request.getParameterValues("paidLeavesAdjusted");
			String[] unauthorisedAdj = request.getParameterValues("unauthorisedAdj");
			String[] lateMarksAdjusted = request.getParameterValues("lateMarksAdjusted");
			String[] halfDaysAdjusted = request.getParameterValues("halfDaysAdjusted");
			String[] manuallyAdjusted = request.getParameterValues("manuallyAdjusted");
			String[] currentBalance = request.getParameterValues("currentBalance");	
			
			
			String attendanceHrs = "00:00", lateMarksHrs = "00:00", paidLeavesHrs = "00:00", unPaidLeavesHrs = "00:00", 
			salaryHrs = "00:00";
			String[] openingHrsBalance = null, lateMarksHrsAdjusted = null, manuallyHrsAdjusted = null, currentHrsBalance = null;
			if(lmInHrsEnabled) {
				attendanceHrs = request.getParameter("attendanceHrs");
				lateMarksHrs = request.getParameter("lateMarksHrs");
				paidLeavesHrs = request.getParameter("paidLeavesHrs");
				unPaidLeavesHrs = request.getParameter("unPaidLeavesHrs");
				salaryHrs = request.getParameter("salaryHrs");
				
				openingHrsBalance = request.getParameterValues("openingHrsBalance");
				lateMarksHrsAdjusted = request.getParameterValues("lateMarksHrsAdjusted");
				manuallyHrsAdjusted = request.getParameterValues("manuallyHrsAdjusted");
				currentHrsBalance = request.getParameterValues("currentHrsBalance");
			}			
			
			String penaltiesAdjusted = "0", penaltiesUnAdjusted = "0";
			if(leaveUnplanFlag) {
				penaltiesAdjusted = request.getParameter("penaltiesAdjusted");
				penaltiesUnAdjusted = request.getParameter("penaltiesUnAdjusted");
			}			
			
			String unauthAdjusted = "0", unauthUnAdjusted = "0";
			if(leaveUnauthFlag) {
				unauthAdjusted = request.getParameter("unauthAdjusted");
				unauthUnAdjusted = request.getParameter("unauthUnAdjusted");
			}
			
			Object[][] saveAttendanceDetails = null;
			Object[][] updateClosingBalance = null;
			Object[][] viewAttendanceDetails = null;
			
			if(leaveId != null && leaveId.length > 0) {
				saveAttendanceDetails = new Object[leaveId.length][14];
				updateClosingBalance = new Object[leaveId.length][4];
    			
				if(lmInHrsEnabled) {
					viewAttendanceDetails = new Object[leaveId.length][13];
				} else {
					viewAttendanceDetails = new Object[leaveId.length][9];
				}
			}
			
			String saveAttendanceSql = " UPDATE HRMS_MONTH_ATTENDANCE_" + year + " SET ATTN_DAYS = " + attendanceDays + ", " +
			" ATTN_HRS = TO_DATE('" + attendanceHrs + "', 'HH24:MI'), ATTN_WOFF = " + weeklyOffs + ", ATTN_HOLIDAY = " + holidays + ", " +
			" ATTN_LATE_MARKS = " + lateMarks + ", ATTN_LATE_MARKS_HRS = TO_DATE('" + lateMarksHrs + "', 'HH24:MI'), " +
			" ATTN_HALF_DAYS = " + halfDays + ", ATTN_PAID_LEVS = " + paidLeaves + " , " +
			" ATTN_PAID_LEVS_HRS = TO_DATE('" + paidLeavesHrs + "', 'HH24:MI'), ATTN_PENALTY_ADJUSTED = " + penaltiesAdjusted + ", " +
			" ATTN_UNAUTH_ADJUSTED = " + unauthAdjusted + ", ATTN_UNPAID_LEVS = " + unPaidLeaves + ", " +
			" ATTN_UNPAID_LEVS_HRS = TO_DATE('" + unPaidLeavesHrs + "', 'HH24:MI'), ATTN_PENALTY_UNADJUSTED = " + penaltiesUnAdjusted + ", " +
			" ATTN_UNAUTH_UNADJUSTED = " + unauthUnAdjusted + ", ATTN_SAL_DAYS = " + salaryDays + ", " +
			" ATTN_SAL_HRS = TO_DATE('" + salaryHrs + "', 'HH24:MI'),ATTN_RECOVERY_DAYS = "+recoveryDays+ 
			" WHERE ATTN_EMP_ID = " + empId + " AND ATTN_CODE = " + attendanceCode;
			
			boolean isDataSaved = getSqlModel().singleExecute(saveAttendanceSql);
			
			if(isDataSaved) {
				if(leaveId != null && leaveId.length > 0) {
					for(int i = 0; i < leaveId.length; i++) {
						// update attendance details
						saveAttendanceDetails[i][0] = attendanceCode;
						saveAttendanceDetails[i][1] = empId;
						saveAttendanceDetails[i][2] = leaveId[i];
						saveAttendanceDetails[i][3] = openingBalance[i];
						if(lmInHrsEnabled) {
							saveAttendanceDetails[i][4] = openingHrsBalance[i];
						} else {
							saveAttendanceDetails[i][4] = "00:00";
						}
						saveAttendanceDetails[i][5] = paidLeavesAdjusted[i];
						if(leaveUnauthFlag) {
							saveAttendanceDetails[i][6] = unauthorisedAdj[i];
						} else {
							saveAttendanceDetails[i][6] = "0.0";
						}						
						saveAttendanceDetails[i][7] = lateMarksAdjusted[i];
						if(lmInHrsEnabled) {
							saveAttendanceDetails[i][8] = lateMarksHrsAdjusted[i];
						} else {
							saveAttendanceDetails[i][8] = "00:00";
						}						
						saveAttendanceDetails[i][9] = halfDaysAdjusted[i];
						saveAttendanceDetails[i][10] = manuallyAdjusted[i];
						if(lmInHrsEnabled) {
							saveAttendanceDetails[i][11] = manuallyHrsAdjusted[i];
						} else {
							saveAttendanceDetails[i][11] = "00:00";
						}						
						saveAttendanceDetails[i][12] = currentBalance[i];
						if(lmInHrsEnabled) {
							saveAttendanceDetails[i][13] = currentHrsBalance[i];
						} else {
							saveAttendanceDetails[i][13] = "00:00";
						}
						
						// update leave balances
						updateClosingBalance[i][0] = currentBalance[i];
						if(lmInHrsEnabled) {
							updateClosingBalance[i][1] = currentHrsBalance[i];
						} else {
							updateClosingBalance[i][1] = "00:00";
						}
						updateClosingBalance[i][2] = empId;
						updateClosingBalance[i][3] = leaveId[i];
						
						// set attendance details on a page
						if(lmInHrsEnabled) {
							viewAttendanceDetails[i][0] = leaveId[i];
							viewAttendanceDetails[i][1] = leaveType[i];
							viewAttendanceDetails[i][2] = openingBalance[i];
							viewAttendanceDetails[i][3] = openingHrsBalance[i];
							viewAttendanceDetails[i][4] = paidLeavesAdjusted[i];
							if(leaveUnauthFlag) {
								viewAttendanceDetails[i][5] = unauthorisedAdj[i];
							} else {
								viewAttendanceDetails[i][5] = "0.0";
							}							
							viewAttendanceDetails[i][6] = lateMarksAdjusted[i];
							viewAttendanceDetails[i][7] = lateMarksHrsAdjusted[i];														
							viewAttendanceDetails[i][8] = halfDaysAdjusted[i];
							viewAttendanceDetails[i][9] = manuallyAdjusted[i];
							viewAttendanceDetails[i][10] = manuallyHrsAdjusted[i];
							viewAttendanceDetails[i][11] = currentBalance[i];
							viewAttendanceDetails[i][12] = currentHrsBalance[i];
						} else {
							viewAttendanceDetails[i][0] = leaveId[i];
							viewAttendanceDetails[i][1] = leaveType[i];
							viewAttendanceDetails[i][2] = openingBalance[i];
							viewAttendanceDetails[i][3] = paidLeavesAdjusted[i];
							if(leaveUnauthFlag) {
								viewAttendanceDetails[i][4] = unauthorisedAdj[i];
							} else {
								viewAttendanceDetails[i][4] = "0.0";
							}
							viewAttendanceDetails[i][5] = lateMarksAdjusted[i];
							viewAttendanceDetails[i][6] = halfDaysAdjusted[i];
							viewAttendanceDetails[i][7] = manuallyAdjusted[i];
							viewAttendanceDetails[i][8] = currentBalance[i];
						}
					}
					
					String deleteAttendanceDetailsSql = " DELETE FROM HRMS_MONTH_ATT_DTL_" + year + 
					" WHERE ATT_EMP_CODE = " + empId + " AND ATT_CODE = " + attendanceCode;
					isDataSaved = getSqlModel().singleExecute(deleteAttendanceDetailsSql);
					
					if(isDataSaved) {
						/**
						 * Insert into HRMS_MONTH_ATT_DTL_<year>
						 */				
						String saveAttendanceDetailSql = " INSERT INTO HRMS_MONTH_ATT_DTL_" + year + 
						" (ATT_CODE, ATT_EMP_CODE, ATT_LEAVE_CODE, ATT_LEAVE_AVAILABLE, ATT_LEAVE_AVAILABLE_HRS, ATT_LEAVE_ADJUST, " +
						" ATT_UNAUTHORISED_ADJUST, ATT_LATEMARK_ADJUST, ATT_LATEMARK_ADJUST_HRS, ATT_HALFDAY_ADJUST, " +
						" ATT_MANUAL_ADJUST, ATT_MANUAL_ADJUST_HRS, ATT_LEAVE_BALANCE, ATT_LEAVE_BALANCE_HRS) " +
						" VALUES(?, ?, ?, ?, TO_DATE(?, 'HH24:MI'), ?, ?, ?, TO_DATE(?, 'HH24:MI'), ?, ?, TO_DATE(?, 'HH24:MI'), ?, " +
						" TO_DATE(?, 'HH24:MI')) ";
						
						isDataSaved = getSqlModel().singleExecute(saveAttendanceDetailSql, saveAttendanceDetails);
					}
					
					if(isDataSaved) {
						String updateClosingBalanceSql = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = ?, " +
						" LEAVE_HRS_CLOSING_BALANCE = TO_DATE(?, 'HH24:MI') WHERE EMP_ID = ? AND LEAVE_CODE = ? ";
						
						isDataSaved = getSqlModel().singleExecute(updateClosingBalanceSql, updateClosingBalance);
					}
				}
			}
			
			if(isDataSaved) {
				message = "Attendance saved successfully!";
			} else {
				message = "Attendance cannot be saved!";
			}
			request.setAttribute("viewAttendanceDetails", viewAttendanceDetails);
		} catch(Exception e) {
			logger.error("Exception in saveAttendance:" + e);
			message = "Attendance cannot be saved!";
		}
		return message;
	}
	
	/**
	 * Set the selected filters in WHERE condition in Sql query while getting saved attendance records, used to retrieve list of 
	 * employees
	 * @param listOfIdFilters: Contains list of filters selected from a page
	 * @param sqlQuery: Sql query need to be concatenated by filters in WHERE condition
	 * @return String as concatenated Sql query
	 */
	public String setEmployeeOffciceFiletrs(String[] listOfFilters, String sqlQuery) {
		try {
			// if branch is selected
			if(!listOfFilters[0].equals("")) {
				sqlQuery += " AND EMP_CENTER = " + listOfFilters[0];
			}
			
			// if department is selected
			if(!listOfFilters[1].equals("")) {
				sqlQuery += " AND EMP_DEPT = " + listOfFilters[1];
			}
			
			// if paybill group is selected
			if(!listOfFilters[2].equals("")) {
				sqlQuery += " AND EMP_PAYBILL = " + listOfFilters[2];
			}
			
			// if employee type is selected
			if(!listOfFilters[3].equals("")) {
				sqlQuery += " AND EMP_TYPE = " + listOfFilters[3];
			}
			
			// if division is selected
			if(!listOfFilters[4].equals("")) {
				sqlQuery += " AND EMP_DIV = " + listOfFilters[4];
			}
			
			// if employee is selected
			if(!listOfFilters[5].equals("")) {
				sqlQuery += " AND EMP_ID = " + listOfFilters[5];
			}
			return sqlQuery;
		} catch (Exception e) {
			logger.error("Exception in setEmployeeOffciceFiletrs:" + e);
			return "";
		}
	}
	
	public Object[][] viewAttendance(String month, String year, String[] listOfFilters) {
		Object[][] viewAttendance = null;
		try {
			String viewAttendanceSql = " SELECT ATTN_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME " +
			" AS EMP_NAME, CENTER_NAME, CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN NVL(ATTN_DAYS, 0) || ' d ' || " +
			" NVL(TO_CHAR(ATTN_HRS, 'HH24'), '00') ||  ' h ' || NVL(TO_CHAR(ATTN_HRS, 'MI'), '00') || ' m' " +
			" ELSE TO_CHAR(NVL(ATTN_DAYS, 0)) END AS ATTN_DAYS, NVL(ATTN_WOFF, 0) AS ATTN_WOFF,  NVL(ATTN_HOLIDAY, 0) " +
			" AS ATTN_HOLIDAY, CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN NVL(ATTN_LATE_MARKS, 0) || ' d ' ||  " +
			" NVL(TO_CHAR(ATTN_LATE_MARKS_HRS, 'HH24'), '00') || ' h ' ||  NVL(TO_CHAR(ATTN_LATE_MARKS_HRS, 'MI'), '00') || ' m' " +
			" ELSE TO_CHAR(NVL(ATTN_LATE_MARKS, 0)) END AS ATTN_LATE_MARKS_HRS, NVL(ATTN_HALF_DAYS, 0) AS ATTN_HALF_DAYS, " +
			" CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN NVL((ATTN_PAID_LEVS + ATTN_PENALTY_ADJUSTED + ATTN_UNAUTH_ADJUSTED), 0) || " +
			" ' d ' || NVL(TO_CHAR(ATTN_PAID_LEVS_HRS, 'HH24'), '00') || ' h ' || NVL(TO_CHAR(ATTN_PAID_LEVS_HRS, 'MI'), '00') || " +
			" ' m' ELSE TO_CHAR(NVL((ATTN_PAID_LEVS + ATTN_PENALTY_ADJUSTED + ATTN_UNAUTH_ADJUSTED), 0)) END AS ATTN_PAID_LEVS_HRS, " +
			" CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN NVL((ATTN_UNPAID_LEVS + ATTN_PENALTY_UNADJUSTED + ATTN_UNAUTH_UNADJUSTED), 0) " +
			" ||  ' d ' || NVL(TO_CHAR(ATTN_UNPAID_LEVS_HRS, 'HH24'), '00') || ' h ' || NVL(TO_CHAR(ATTN_UNPAID_LEVS_HRS, 'MI'), '00') " +
			" ||  ' m' ELSE TO_CHAR(NVL((ATTN_UNPAID_LEVS + ATTN_PENALTY_UNADJUSTED + ATTN_UNAUTH_UNADJUSTED), 0)) END " +
			" AS ATTN_UNPAID_LEVS_HRS,  NVL(ATTN_SYSTEM_UNPAID, 0) AS ATTN_SYSTEM_UNPAID, " +
			" CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN NVL(ATTN_SAL_DAYS, 0) ||  ' d ' || NVL(TO_CHAR(ATTN_SAL_HRS, 'HH24'), '00') " +
			" || ' h ' || NVL(TO_CHAR(ATTN_SAL_HRS, 'MI'), '00') || ' m'  ELSE TO_CHAR(NVL(ATTN_SAL_DAYS, 0)) END AS ATTN_SAL_HRS, " +
			" TO_CHAR(LAST_DAY(TO_DATE('01-1-2006', 'DD-MM-YYYY')), 'DD') AS TOTAL_ATTENDANCE_DAYS, ATTN_CODE, LEDGER_STATUS, " +
			"  NVL(ATTN_RECOVERY_DAYS,0) FROM HRMS_MONTH_ATTENDANCE_" + year +
			" INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_CODE = HRMS_SALARY_LEDGER.LEDGER_CODE) " +
			" INNER JOIN HRMS_EMP_OFFC ON(HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_EMP_ID = HRMS_EMP_OFFC.EMP_ID) " +
			" INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
			" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " +
			" WHERE LEDGER_MONTH = " + month + " AND LEDGER_YEAR = " + year;
			viewAttendanceSql = setEmployeeOffciceFiletrs(listOfFilters, viewAttendanceSql);
			viewAttendanceSql += " ORDER BY UPPER(CENTER_NAME), UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";
			viewAttendance = getSqlModel().getSingleResult(viewAttendanceSql);
		} catch(Exception e) {
			logger.error("Exception in viewAttendance:" + e);
		}
		return viewAttendance;
	}
	
	public Object[][] viewAttendanceDetails(String month, String year, String empId, String leavePolicyId, String attendanceCode, 
		Object[][] leavePolicies, boolean lmInHrsEnabled, double workingHours) {
		Object[][] viewAttendanceDetails = null;
		try {
			MonthAttendanceProcessModel monthAtdnProc = new MonthAttendanceProcessModel();
			monthAtdnProc.initiate(context, session);
			
			monthAtdnProc.setLeavePolicies(leavePolicies);
			
			// Get Paid Leave Ids from Leave Policy
			ArrayList<String> paidLeaveIds = monthAtdnProc.getLeaveIdsFromPolicy(leavePolicyId, "P");
						
			monthAtdnProc.terminate();
			
			String paidLeavesCode = "";
			if(paidLeaveIds.size() > 0) {
				for(int i = 0; i < paidLeaveIds.size(); i++) {
					if(i == (paidLeaveIds.size() - 1)) {
						paidLeavesCode += paidLeaveIds.get(i);
					} else {
						paidLeavesCode += paidLeaveIds.get(i) + ",";
					}
				}
			}
			
			String viewAttendanceDetailsSql =
				" SELECT LEAVE_CODE, LEAVE_ABBR, " +
				// opening balance
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE NVL(ATT_LEAVE_ADJUST, 0) END + " +
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' OR AUTO_PRESENT_LATE_MARK = 'Y' THEN 0 ELSE NVL(ATT_LATEMARK_ADJUST, 0) END + " +
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' OR AUTO_PRESENT_HALF_DAY = 'Y' THEN 0 ELSE NVL(ATT_HALFDAY_ADJUST, 0) END + " +
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE NVL(ATT_HALFDAY_ADJUST, 0) END + " +
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE NVL(ATT_UNAUTHORISED_ADJUST, 0) END + " +
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN (NVL(ATT_LEAVE_ADJUST, 0) + NVL(ATT_LATEMARK_ADJUST, 0) + NVL(ATT_HALFDAY_ADJUST, 0) + " +
				" NVL(ATT_UNAUTHORISED_ADJUST, 0) + NVL(ATT_MANUAL_ADJUST, 0) + NVL(LEAVE_CLOSING_BALANCE, 0)) " +
				" ELSE NVL(LEAVE_CLOSING_BALANCE, 0) END AS OPENING_BALANCE, " +
				
				/**
				 * Calculate opening balance. 
				 * total adjusted days in minutes + late marks in hrs + manually adjusted in hrs + closing balance in hrs
				 */
				// total adjusted days in minutes
				" CEIL(CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN ((CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE NVL(ATT_LEAVE_ADJUST, 0) END + " +
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' OR AUTO_PRESENT_LATE_MARK = 'Y' THEN 0 ELSE NVL(ATT_LATEMARK_ADJUST, 0) END + " +
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' OR AUTO_PRESENT_HALF_DAY = 'Y' THEN 0 ELSE NVL(ATT_HALFDAY_ADJUST, 0) END + " +
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE NVL(ATT_UNAUTHORISED_ADJUST, 0) END + " +
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE NVL(ATT_MANUAL_ADJUST, 0) END + " +
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN (NVL(ATT_LEAVE_ADJUST, 0) + NVL(ATT_LATEMARK_ADJUST, 0) +  NVL(ATT_HALFDAY_ADJUST, 0) + " +
				" NVL(ATT_MANUAL_ADJUST, 0) + NVL(LEAVE_CLOSING_BALANCE, 0)) ELSE NVL(LEAVE_CLOSING_BALANCE, 0) END) * " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0) + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) * 60 + " +
				// late marks in hrs
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' OR AUTO_PRESENT_LATE_MARK = 'Y' THEN 0 " +
				" ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATEMARK_ADJUST_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATEMARK_ADJUST_HRS, 'HH24:MI'), 4, 5)), 0) END + " +
				// manually adjusted in hrs
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 " +
				" ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_MANUAL_ADJUST_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_MANUAL_ADJUST_HRS, 'HH24:MI'), 4, 5)), 0) END + " +
				// closing balance in hrs
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATEMARK_ADJUST_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATEMARK_ADJUST_HRS, 'HH24:MI'), 4, 5)), 0) + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_MANUAL_ADJUST_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_MANUAL_ADJUST_HRS, 'HH24:MI'), 4, 5)), 0) + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 4, 5)), 0) " +
				" ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 4, 5)), 0) END " +
				" ELSE 0 END) AS LEAVE_OPENING_BALANCE_IN_MINS, " +
				
				// leaves adjusted
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE NVL(ATT_LEAVE_ADJUST, 0) END AS LEAVES_ADJUSTED, " +
				
				// unauthorised adjusted days
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE NVL(ATT_UNAUTHORISED_ADJUST, 0) END AS ATT_UNAUTHORISED_ADJUST, " +
				
				// late marks adjusted
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' OR AUTO_PRESENT_LATE_MARK = 'Y' THEN 0 " +
				" ELSE NVL(ATT_LATEMARK_ADJUST, 0) END AS LATE_MARKS_ADJUSTED, " +
				
				// late marks adjusted in minutes
				" CEIL(CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN (CASE WHEN AUTO_PRESENT_ABSENT = 'Y' OR AUTO_PRESENT_LATE_MARK = 'Y' " +
				" THEN 0 ELSE NVL(ATT_LATEMARK_ADJUST, 0) END * " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0) + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATEMARK_ADJUST_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATEMARK_ADJUST_HRS, 'HH24:MI'), 4, 5)), 0) ELSE 0 END) AS LATEMARK_ADJUST_IN_MINS, " +
				
				// half days adjusted
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' OR AUTO_PRESENT_HALF_DAY = 'Y' THEN 0 " +
				" ELSE NVL(ATT_HALFDAY_ADJUST, 0) END AS HALF_DAYS_ADJUSTED, " +
				
				// manually adjusted
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 ELSE NVL(ATT_MANUAL_ADJUST, 0) END AS MANUALLY_ADJUSTED, " +
				
				// manually adjusted in minutes
				" CEIL(CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN (CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN 0 " +
				" ELSE NVL(ATT_MANUAL_ADJUST, 0) END * " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0) + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_MANUAL_ADJUST_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_MANUAL_ADJUST_HRS, 'HH24:MI'), 4, 5)), 0) ELSE 0 END) AS MANUAL_ADJUST_IN_MINS, " +
				
				// closing balance
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN (NVL(ATT_LEAVE_ADJUST, 0) + NVL(ATT_LATEMARK_ADJUST, 0) + " +
				" NVL(ATT_HALFDAY_ADJUST, 0) + NVL(ATT_UNAUTHORISED_ADJUST, 0) + NVL(ATT_MANUAL_ADJUST, 0) + NVL(LEAVE_CLOSING_BALANCE, 0)) " +
				" ELSE NVL(LEAVE_CLOSING_BALANCE, 0) END AS LEAVE_CLOSING_BALANCE, " +
				
				// closing balance in minutes
				" CEIL(CASE WHEN SFT_LM_HRS_ISENABLED = 'Y' THEN " +
				" (CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN (NVL(ATT_LEAVE_ADJUST, 0) + NVL(ATT_LATEMARK_ADJUST, 0) + " +
				" NVL(ATT_HALFDAY_ADJUST, 0) + NVL(ATT_UNAUTHORISED_ADJUST, 0) + NVL(ATT_MANUAL_ADJUST, 0) + NVL(LEAVE_CLOSING_BALANCE, 0)) " +
				" ELSE NVL(LEAVE_CLOSING_BALANCE, 0) END * NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 1, 2)), 0) + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), 4, 5)), 0) / 60) * 60 + " +
				" CASE WHEN AUTO_PRESENT_ABSENT = 'Y' THEN " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATEMARK_ADJUST_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATEMARK_ADJUST_HRS, 'HH24:MI'), 4, 5)), 0) + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_MANUAL_ADJUST_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_MANUAL_ADJUST_HRS, 'HH24:MI'), 4, 5)), 0) + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 4, 5)), 0) " +
				" ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 1, 2)), 0) * 60 + " +
				" NVL(TO_NUMBER(SUBSTR(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'), 4, 5)), 0) END ELSE 0 END) AS LEAVE_CLOSING_BALANCE_IN_MINS " +
				
				" FROM HRMS_LEAVE_BALANCE " +
				" INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID = " + empId + ") " +
				" LEFT JOIN HRMS_MONTH_ATT_DTL_" + year + " ON(HRMS_LEAVE.LEAVE_ID = HRMS_MONTH_ATT_DTL_" + year + ".ATT_LEAVE_CODE " +
				" AND HRMS_LEAVE_BALANCE.EMP_ID = HRMS_MONTH_ATT_DTL_" + year + ".ATT_EMP_CODE AND EMP_ID = " + empId + ") " +
				" LEFT JOIN HRMS_AUTO_PRESENT ON (HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID = HRMS_LEAVE_BALANCE.EMP_ID) " +
				" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LEAVE_BALANCE.EMP_ID) " +
				" INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_EMP_OFFC.EMP_SHIFT) " +
				" WHERE ATT_CODE = " + attendanceCode + " AND LEAVE_CODE IN(" + paidLeavesCode + ") ORDER BY ATT_LEAVE_CODE ";
			
			viewAttendanceDetails = getSqlModel().getSingleResult(viewAttendanceDetailsSql);
			
			viewAttendanceDetails = getRevisedAttendanceDetails(viewAttendanceDetails, lmInHrsEnabled, workingHours);
		} catch(Exception e) {
			logger.error("Exception in viewAttendanceDetails:" + e);
		}
		return viewAttendanceDetails;
	}
	public boolean getRecoveryFlowFlag(){
		String query="SELECT DECODE(NVL(CONF_RECOVERY_FLAG,'N'),'Y','true','N','false') FROM HRMS_SALARY_CONF";
		
		Object[][]recObj=getSqlModel().getSingleResult(query);
		return Boolean.parseBoolean(String.valueOf(recObj[0][0]));
	}

	public boolean updateLeaveBalanceAndDeleteMonthAttendance(String attendanceCode, String employeeCode, String year) {
		boolean result = false;
		try {
			String getAttendanceDetailQuery = "SELECT ATT_LEAVE_CODE, NVL(ATT_LEAVE_ADJUST + ATT_LATEMARK_ADJUST + " +
			"ATT_HALFDAY_ADJUST + ATT_MANUAL_ADJUST + ATT_UNAUTHORISED_ADJUST,0) - NVL(SUM(DTL.LEAVE_DAYS), 0) " +
			"FROM  HRMS_MONTH_ATT_DTL_"+year+" ATT_DTL " +
			"LEFT JOIN HRMS_LEAVE_DTLHISTORY DTL ON(DTL.EMP_ID = ATT_DTL.ATT_EMP_CODE AND " +
			"DTL.LEAVE_CODE = ATT_LEAVE_CODE) " +
			"WHERE ATT_EMP_CODE = "+ employeeCode +" AND ATT_CODE = " + attendanceCode +
			"GROUP BY ATT_LEAVE_CODE, NVL(ATT_LEAVE_ADJUST + ATT_LATEMARK_ADJUST + ATT_HALFDAY_ADJUST + " +
			"ATT_MANUAL_ADJUST + ATT_UNAUTHORISED_ADJUST,0)";
			
			Object[][] getAttendanceDetailObj = getSqlModel().getSingleResult(getAttendanceDetailQuery);
			
			if(getAttendanceDetailObj !=null && getAttendanceDetailObj.length>0){
				Object[][] updateLeaveBalance = new Object[getAttendanceDetailObj.length][3];
				String updateLeaveQuery = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE= LEAVE_CLOSING_BALANCE + ? WHERE EMP_ID=? AND LEAVE_CODE=?";
				
				for (int i = 0; i < getAttendanceDetailObj.length; i++) {
					updateLeaveBalance[i][0]=getAttendanceDetailObj[i][1]; //LEAVE BALANCE
					updateLeaveBalance[i][1]=employeeCode;
					updateLeaveBalance[i][2]=getAttendanceDetailObj[i][0]; //LEAVE CODE
				}
				result = getSqlModel().singleExecute(updateLeaveQuery,updateLeaveBalance);
				if(result){
					String delQuery = "DELETE FROM HRMS_MONTH_ATT_DTL_"+year+" WHERE ATT_EMP_CODE="+employeeCode+" AND ATT_CODE="+attendanceCode;
					result = getSqlModel().singleExecute(delQuery);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}