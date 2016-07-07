/* @author: Bhushan Dasare   @date: July 3, 2009 */

package org.struts.action.attendance;

import java.io.PrintWriter;
import java.util.Calendar;
import org.paradyne.bean.attendance.ViewMonthAttendance;
import org.paradyne.lib.Utility;
import org.paradyne.model.attendance.MonthAttendanceProcessModel;
import org.paradyne.model.attendance.ViewMonthAttendanceModel;
import org.paradyne.model.leave.LeavePolicyData;

/**
 * To view and modify already processed attendance
 */

public class ViewMonthAttendanceAction extends org.struts.lib.ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ViewMonthAttendanceAction.class);
	ViewMonthAttendance bean;
	
	/**
	 * Add new employee
	 */
	public void addEmployee() {
		try {
			ViewMonthAttendanceModel model = new ViewMonthAttendanceModel();
			model.initiate(context, session);
			
			// add employee, set message whether employee is added successfully or not
			String message = model.addEmployee(request);
			
			// set message in a response
			response.setContentType("text/html");
			PrintWriter printWriter = response.getWriter();
			printWriter.print(message);
			printWriter.flush();
		} catch(Exception e) {
			logger.error("Exception in addEmployee in action:" + e);
		}
	}
	
	/**
	 * Delete existing employee
	 */
	public void deleteAttendance() {
		try {
			ViewMonthAttendanceModel model = new ViewMonthAttendanceModel();
			model.initiate(context, session);
			
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			String attendanceCode = request.getParameter("attendanceCode");
			String deletedRecords = request.getParameter("deletedRecords"); // get deleted employees
			
			// delete employee, set message whether employee is deleted successfully or not
			String message = model.deleteAttendance(month, year, deletedRecords, attendanceCode);
			
			// set message in a response
			response.setContentType("text/html");
			PrintWriter printWriter = response.getWriter();
			printWriter.print(message);
			printWriter.flush();
			
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in deleteAttendance in action:" + e);
		}
	}

	/**
	 * Opens a popup window containing a list of employees whose attendance is not processed as per the filters selected
	 * @return String f9page
	 */
	public String f9AddEmployee() {
		try {
			String month = bean.getMonth();
			String year = bean.getYear();
			String attendanceCode = bean.getAttendanceCode();
			
			// get filters selected
			String[] listOfFilters = getListOfFilters();
			
			String f9AddEmployeeSql = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, CENTER_NAME, EMP_ID, EMP_DIV " +
			" FROM HRMS_EMP_OFFC " +
			" INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) ";
			f9AddEmployeeSql += getprofileQuery(bean);
			f9AddEmployeeSql += " AND EMP_STATUS = 'S' AND EMP_REGULAR_DATE <= TO_DATE((SELECT CASE WHEN CONF_DAILY_ATT_CONNECT_FLAG = 'Y' " + 
			" AND CONF_SALARY_START_FLAG = 'P' THEN CONF_SALARY_START_DATE " +
			" ELSE TO_NUMBER(TO_CHAR(LAST_DAY(TO_DATE('01-" + month + "-" + year + "', 'DD-MM-YYYY')), 'DD')) END AS LAST_DAY " +
			" FROM HRMS_ATTENDANCE_CONF) || '-" + month + "-" + year + "', 'DD-MM-YYYY') AND " +
			" EMP_ID NOT IN(SELECT ATTN_EMP_ID FROM HRMS_MONTH_ATTENDANCE_" + year + " WHERE ATTN_CODE = " + attendanceCode + ")";
			f9AddEmployeeSql = setEmployeeOffciceFiletrs(listOfFilters, f9AddEmployeeSql);
		 	
			f9AddEmployeeSql += " ORDER BY UPPER(CENTER_NAME), UPPER(EMP_FNAME || ' ' || EMP_LNAME), EMP_TOKEN ";
			
			String [] headers = {getMessage("employee.id"), getMessage("employeeName"), getMessage("branch")};
			
			String [] headerWidth = {"20", "40", "40"};
			
			String [] fieldNames = {"newEmpToken", "newEmpName", "newEmpBranchName", "newEmpId", "newEmpDiv"};
			
			int [] columnIndex = {0, 1, 2, 3, 4};
			
			String submitFlag = "false";
			
			String submitToMethod = "";
			
			setF9Window(f9AddEmployeeSql, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9AddEmployee in action:" + e);
			return "";
		}
	}
	
	/**
	 * Opens a popup window containing a list of employees as per the filters selected
	 * @return
	 */
	public String f9SearchEmployee() {
		try {
			String month = bean.getMonth();
			String year = bean.getYear();
			
			// get filters selected
			String[] listOfFilters = getListOfFilters();
			
			String f9SearchEmployeeSql = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID " +
			" FROM HRMS_MONTH_ATTENDANCE_" + year +
			" INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_CODE) " +
			" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_EMP_ID) " +
			" WHERE EMP_STATUS = 'S' AND LEDGER_MONTH = " + month + " AND LEDGER_YEAR = " + year;
			f9SearchEmployeeSql = setEmployeeOffciceFiletrs(listOfFilters, f9SearchEmployeeSql);
		 
			f9SearchEmployeeSql += " ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";
			
			String [] headers = {getMessage("employee.id"), getMessage("employeeName")};
			
			String [] headerWidth = {"30", "80"};
			
			String [] fieldNames = {"searchEmpToken", "searchEmpName", "searchEmpId"};
			
			int [] columnIndex = {0, 1, 2};
			
			String submitFlag = "false";
			
			String submitToMethod = "";
			
			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9SearchEmployee:" + e);
			return "";
		}
	}

	/**
	 * Getter for ViewMonthAttendance object
	 * @return ViewMonthAttendance object
	 */
	public ViewMonthAttendance getBean() {
		return bean;
	}
	
	/**
	 * Get selected filters
	 * @return String[] as list of filters selected
	 */
	private String[] getListOfFilters() {
		String[] listOfFilters = new String[5];
		try {
			// Get the filters from a page			
			String branchId = bean.getBranchId();
			String departmentId = bean.getDepartmentId();
			String payBillId = bean.getPayBillId();
			String employeeTypeId = bean.getEmployeeTypeId();
			String divisionId = bean.getDivisionId();
			
			// Set list of  selected filters
			listOfFilters[0] = branchId;
			listOfFilters[1] = departmentId;
			listOfFilters[2] = payBillId;
			listOfFilters[3] = employeeTypeId;
			listOfFilters[4] = divisionId;
		} catch(Exception e) {
			logger.error("Exception in getListOfFilters in action:" + e);
		}
		return listOfFilters;
	}
	
	/* 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return bean;
	}
	
	/**
	 * Locks the attendance
	 */
	public void lockAttendance() {
		try {
			String month = bean.getMonth();
			String year = bean.getYear();
			String attendanceCode = bean.getAttendanceCode();
			
			// get lock flag e.g. true - to lock the attendance, false - to unlock the attendance
			boolean lockFlag = Boolean.parseBoolean(request.getParameter("lockFlag"));
			
			// get old status
			String oldStatus = bean.getStatus();
			
			/**
			 * According to lock flag set corresponding status. If salary is already started, then after unlocking attendance 
			 * status will be ATTN_UNLOCK, other wise status will be ATTN_READY.
			 * In reverse situation, if salary is started, then after locking attendance status will be SAL_START, 
			 * other wise status will be ATTN_READY
			 */
			String newStatus = "";
			
			// If lock flag is true, then lock the attendance. If previous status is ATTN_UNLOCK, then set new status to SAL_START,
			// other wise set status to ATTN_READY
			if(lockFlag) {
				if(oldStatus.trim().equals("ATTN_UNLOCK")) {
					newStatus = "SAL_START";
				} else {
					newStatus = "ATTN_READY";
				}
			}
			
			// If lock flag is false, then unlock the attendance. If previous status is SAL_START, then set new status to ATTN_UNLOCK,
			// other wise set status to ATTN_START
			else {
				if(oldStatus.trim().equals("SAL_START")) {
					newStatus = "ATTN_UNLOCK";
				} else {
					newStatus = "ATTN_START";
				}
			}
			
			bean.setLockAttendance(lockFlag);
			bean.setStatus(newStatus);
			
			ViewMonthAttendanceModel model = new ViewMonthAttendanceModel();
			model.initiate(context, session);
			
			// lock or unlock the attendance, set message whether attendance is lock or unlock successfully or not
			String message = model.lockAttendance(month, year, attendanceCode, newStatus);
			message += "," + lockFlag + "," + newStatus;
			
			model.terminate();
			
			// set message in response
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(message);
			out.flush();
		} catch(Exception e) {
			logger.error("Exception in lockAttendance:" + e);
		}
	}
	
	/* 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		bean = new ViewMonthAttendance();
		bean.setMenuCode(905);
	}

	/* 
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	/**
	 * Call when page loads at first time. Set the current year
	 */
	public void prepare_withLoginProfileDetails() {
		try {
			// Set the current year
			Calendar c = Calendar.getInstance();
			bean.setYear(String.valueOf(c.get(Calendar.YEAR)));
		} catch(Exception e) {
			logger.error("Exception in prepare_withLoginProfileDetails in action: " + e);
		}
	}

	/**
	 * Recalculate entire attendance
	 * @return String as "viewAttendanceDetails"
	 */
	public String recalculateAttendance() {
		try {
			ViewMonthAttendanceModel model = new ViewMonthAttendanceModel();
			model.initiate(context, session);
			
			// set name of month
			String month = bean.getMonth();
			String monthName = Utility.month(Integer.parseInt(month));
			bean.setMonthName(monthName);
			
			boolean lmInHrsEnabled = Boolean.parseBoolean(request.getParameter("lmInHrsEnabled"));
			boolean leaveUnplanFlag = Boolean.parseBoolean(request.getParameter("leaveUnplanFlag"));
			boolean leaveUnauthFlag = Boolean.parseBoolean(request.getParameter("leaveUnauthFlag"));
			
			String attendanceCode = bean.getAttendanceCode();
			String employeeCode = bean.getEmpId();
			String year = bean.getYear();
			boolean flag =false;
			model.updateLeaveBalanceAndDeleteMonthAttendance(attendanceCode, employeeCode, year);
			
				Object[][] reCalculatedAttendance = model.recalculateAttendance(request, lmInHrsEnabled);
				
				model.terminate();
				
				// set attendance data
				if(lmInHrsEnabled) {
					bean.setAttendanceDays(String.valueOf(reCalculatedAttendance[0][13]));
					bean.setAttendanceHrs(String.valueOf(reCalculatedAttendance[0][14]));
					bean.setWeeklyOffs(String.valueOf(reCalculatedAttendance[0][2]));
					bean.setHolidays(String.valueOf(reCalculatedAttendance[0][1]));
					bean.setLateMarks(String.valueOf(reCalculatedAttendance[0][3]));
					bean.setLateMarksHrs(String.valueOf(reCalculatedAttendance[0][4]));
					bean.setHalfDays(String.valueOf(reCalculatedAttendance[0][5]));
					bean.setPaidLeaves(String.valueOf(reCalculatedAttendance[0][6]));
					bean.setPaidLeavesHrs(String.valueOf(reCalculatedAttendance[0][7]));
					bean.setUnPaidLeaves(String.valueOf(reCalculatedAttendance[0][9]));
					bean.setUnPaidLeavesHrs(String.valueOf(reCalculatedAttendance[0][10]));
					bean.setSystemUnPaidLeaves(String.valueOf(reCalculatedAttendance[0][12]));
					bean.setSalaryDays(String.valueOf(reCalculatedAttendance[0][17]));
					bean.setSalaryHrs(String.valueOf(reCalculatedAttendance[0][18]));
					bean.setTotalAttendanceDays(String.valueOf(reCalculatedAttendance[0][20]));
				} else {
					bean.setAttendanceDays(String.valueOf(reCalculatedAttendance[0][13]));
					bean.setWeeklyOffs(String.valueOf(reCalculatedAttendance[0][2]));
					bean.setHolidays(String.valueOf(reCalculatedAttendance[0][1]));
					bean.setLateMarks(String.valueOf(reCalculatedAttendance[0][3]));
					bean.setHalfDays(String.valueOf(reCalculatedAttendance[0][5]));			
					bean.setPaidLeaves(String.valueOf(reCalculatedAttendance[0][6]));
					bean.setUnPaidLeaves(String.valueOf(reCalculatedAttendance[0][9]));
					bean.setSystemUnPaidLeaves(String.valueOf(reCalculatedAttendance[0][12]));
					bean.setSalaryDays(String.valueOf(reCalculatedAttendance[0][17]));
					bean.setTotalAttendanceDays(String.valueOf(reCalculatedAttendance[0][20]));
				}
				
				if(leaveUnplanFlag) {
					bean.setPenaltiesAdjusted(String.valueOf(reCalculatedAttendance[0][8]));
					bean.setPenaltiesUnAdjusted(String.valueOf(reCalculatedAttendance[0][11]));
				}
				
				if(leaveUnauthFlag) {
					bean.setUnauthAdjusted(String.valueOf(reCalculatedAttendance[0][15]));
					bean.setUnauthUnAdjusted(String.valueOf(reCalculatedAttendance[0][16]));
				}
				bean.setRecoveryDays(String.valueOf(reCalculatedAttendance[0][21]));
			
			// recalculate the attendance
			
		} catch(Exception e) {
			logger.error("Exception in recalculateAttendance in action:" + e);
		}
		return "viewAttendanceDetails";
	}
	
	
	/**
	 * Recalculate leave details
	 * @return String as "viewAttendanceDetails"
	 */
	public String recalculateAttendanceDetails() {
		try {
			ViewMonthAttendanceModel model = new ViewMonthAttendanceModel();
			model.initiate(context, session);
			
			MonthAttendanceProcessModel monthAtdnProc = new MonthAttendanceProcessModel();
			monthAtdnProc.initiate(context, session);
			
			// set month name
			String month = bean.getMonth();
			String monthName = Utility.month(Integer.parseInt(month));
			bean.setMonthName(monthName);
			
			// get attendance details
			String empId = request.getParameter("empId");
			double attendanceDays = Double.parseDouble(request.getParameter("attendanceDays"));
			double totalAttendanceDays = Double.parseDouble(request.getParameter("totalAttendanceDays"));
			double weeklyOffs = Double.parseDouble(request.getParameter("weeklyOffs"));
			double holidays = Double.parseDouble(request.getParameter("holidays"));
			int lateMarks = (int) Double.parseDouble(request.getParameter("lateMarks"));
			String originalLateMarks = request.getParameter("originalLateMarks");			
			int halfDays = (int) Double.parseDouble(request.getParameter("halfDays"));
			String originalHalfDays = request.getParameter("originalHalfDays");
			double unPaidLeaves = Double.parseDouble(request.getParameter("unPaidLeaves"));
			double systemUnPaidLeaves = Double.parseDouble(request.getParameter("systemUnPaidLeaves"));
			double salaryDays = Double.parseDouble(request.getParameter("salaryDays"));
			int lmCount = Integer.parseInt(request.getParameter("lmCount"));
			double lmAdjustLeaveDays = Double.parseDouble(request.getParameter("lmAdjustLeaveDays"));
			String lmAdjustLeaveTypes = request.getParameter("lmAdjustLeaveTypes");
			String hdDeductLeaveTypes = request.getParameter("hdDeductLeaveTypes");
			
			String workingHours = request.getParameter("workingHours");
			monthAtdnProc.setWorkingHours(Double.parseDouble(workingHours));
			
			boolean lmInHrsEnabled = Boolean.parseBoolean(request.getParameter("lmInHrsEnabled"));
			boolean leaveUnauthFlag = Boolean.parseBoolean(request.getParameter("leaveUnauthFlag"));
			
			String attendanceHrs = "", originalLateMarksHrs = "", lateMarksHrs = "", salaryHrs = "", unPaidLeavesHrs = "", 
			lmDeductNonRegLeaveTypes = "";
			int attendanceMins = 0, lateMarksMins = 0, unPaidLeavesMins = 0, salaryMins = 0;
			
			if(lmInHrsEnabled) {
				attendanceHrs = request.getParameter("attendanceHrs");
				attendanceMins = monthAtdnProc.getMinutes(attendanceDays);
				attendanceMins += model.getMinutes(attendanceHrs);
				
				lateMarksHrs = request.getParameter("lateMarksHrs");
				lateMarksMins = monthAtdnProc.getMinutes(lateMarks);
				lateMarksMins += model.getMinutes(lateMarksHrs);
				
				unPaidLeavesHrs = request.getParameter("unPaidLeavesHrs");
				unPaidLeavesMins = monthAtdnProc.getMinutes(unPaidLeaves);
				unPaidLeavesMins += model.getMinutes(unPaidLeavesHrs);
				
				salaryHrs = request.getParameter("salaryHrs");
				salaryMins = monthAtdnProc.getMinutes(salaryDays);
				salaryMins += model.getMinutes(salaryHrs);
				
				originalLateMarksHrs = request.getParameter("originalLateMarksHrs");
				lmDeductNonRegLeaveTypes = request.getParameter("lmDeductNonRegLeaveTypes");
			}
			
			// get leave details
			String[] leaveId = request.getParameterValues("leaveId");
			String[] leaveType = request.getParameterValues("leaveType");
			String[] openingBalance = request.getParameterValues("openingBalance");
			String[] paidLeavesAdjusted = request.getParameterValues("paidLeavesAdjusted");
			String[] lateMarksAdjusted = request.getParameterValues("lateMarksAdjusted");
			String[] halfDaysAdjusted = request.getParameterValues("halfDaysAdjusted");
			String[] manuallyAdjusted = request.getParameterValues("manuallyAdjusted");
			String[] currentBalance = request.getParameterValues("currentBalance");
			String[] unauthorisedAdj = null;
			if(leaveUnauthFlag) {
				unauthorisedAdj = request.getParameterValues("unauthorisedAdj");
			}
			
			String[] openingHrsBalance = null, lateMarksHrsAdjusted = null, manuallyHrsAdjusted = null, currentHrsBalance = null;
			
			if(lmInHrsEnabled) {
				openingHrsBalance = request.getParameterValues("openingHrsBalance");
				lateMarksHrsAdjusted = request.getParameterValues("lateMarksHrsAdjusted");
				manuallyHrsAdjusted = request.getParameterValues("manuallyHrsAdjusted");
				currentHrsBalance = request.getParameterValues("currentHrsBalance");
			}
			
			// prepare an object having original leave details
			Object[][] originalLeaveDetails = new Object[leaveId.length][9];
			
			for(int i = 0; i < originalLeaveDetails.length; i++) {
				originalLeaveDetails[i][0] = leaveId[i];
				originalLeaveDetails[i][1] = leaveType[i];
				
				// get opening balance
				if(lmInHrsEnabled) {
					int openBalMins = monthAtdnProc.getMinutes(Double.parseDouble(openingBalance[i]));
					openBalMins += model.getMinutes(openingHrsBalance[i]);
					originalLeaveDetails[i][2] = openBalMins;
				} else {
					originalLeaveDetails[i][2] = openingBalance[i];
				}
				
				// get paid leaves adjusted
				originalLeaveDetails[i][3] = paidLeavesAdjusted[i];
				
				// get late marks adjusted
				if(lmInHrsEnabled) {
					int lateMarkMinsAdjusted = monthAtdnProc.getMinutes(Double.parseDouble(lateMarksAdjusted[i]));
					lateMarkMinsAdjusted += model.getMinutes(lateMarksHrsAdjusted[i]);
					originalLeaveDetails[i][4] = lateMarkMinsAdjusted;
				} else {
					originalLeaveDetails[i][4] = lateMarksAdjusted[i];
				}
				
				// get half days adjusted
				if(lmInHrsEnabled) {
					originalLeaveDetails[i][5] = monthAtdnProc.getMinutes(Double.parseDouble(halfDaysAdjusted[i]));
				} else {
					originalLeaveDetails[i][5] = halfDaysAdjusted[i];
				}
				
				// get manually adjusted
				if(lmInHrsEnabled) {
					int manualMinsAdjusted = monthAtdnProc.getMinutes(Double.parseDouble(manuallyAdjusted[i]));
					manualMinsAdjusted += model.getMinutes(manuallyHrsAdjusted[i]);
					originalLeaveDetails[i][6] = manualMinsAdjusted;
				} else {
					originalLeaveDetails[i][6] = manuallyAdjusted[i];
				}
				
				// get closing balance
				if(lmInHrsEnabled) {
					int closeBalMins = monthAtdnProc.getMinutes(Double.parseDouble(currentBalance[i]));
					closeBalMins += model.getMinutes(currentHrsBalance[i]);
					originalLeaveDetails[i][7] = closeBalMins;
				} else {
					originalLeaveDetails[i][7] = currentBalance[i];
				}
				
				// get unauthorised adjusted
				if(leaveUnauthFlag) {
					originalLeaveDetails[i][8] = unauthorisedAdj[i];
				} else {
					originalLeaveDetails[i][8] = "0.0";
				}				
			}
			
			// recalculate leave details
			Object[] detailsOfAttendance = model.recalculateAttendanceDetails(request, originalLeaveDetails, empId, lmInHrsEnabled, monthAtdnProc, 
				lateMarks, lmCount, lmAdjustLeaveDays, lmAdjustLeaveTypes, lateMarksMins, halfDays, lmDeductNonRegLeaveTypes, hdDeductLeaveTypes, 
				systemUnPaidLeaves, salaryMins, unPaidLeavesMins, salaryDays, unPaidLeaves, totalAttendanceDays, weeklyOffs, holidays);
			
			if(lmInHrsEnabled) {
				/**
				 * Get newly calculated attendance days and salary days
				 */
				double newAttendanceDays = Double.parseDouble(String.valueOf(detailsOfAttendance[0]));
				String newAttendanceHours = String.valueOf(detailsOfAttendance[1]);
				
				String newPaidLeaveDays = String.valueOf(detailsOfAttendance[2]);
				String newPaidLeaveHours = String.valueOf(detailsOfAttendance[3]);
				
				String newUnPaidLeaveDays = String.valueOf(detailsOfAttendance[4]);
				String newUnPaidLeaveHours = String.valueOf(detailsOfAttendance[5]);
				
				double newSalaryDays = Double.parseDouble(String.valueOf(detailsOfAttendance[6]));
				String newSalaryHours = String.valueOf(detailsOfAttendance[7]);
				
				/**
				 * If newly calculated attendance days or salary days are less than 0, then set late marks and half days to original,
				 * set original leave details and print message. I not, set newly calculated attendance days and salary days, 
				 * paid and unpaid leave days
				 */
				// attendance days are less than 0
				if(newAttendanceDays < 0) {
					addActionMessage(getMessage("attendanceDays") + " cannot be less than 0!");
					
					bean.setLateMarks(originalLateMarks);
					bean.setLateMarksHrs(originalLateMarksHrs);
					bean.setHalfDays(originalHalfDays);
					
					request.setAttribute("viewAttendanceDetails", originalLeaveDetails);
				}
				
				// salary days are less than 0
				else if(newSalaryDays < 0) {
					addActionMessage(getMessage("salaryDays") + " cannot be less than 0!");
					
					bean.setLateMarks(originalLateMarks);
					bean.setLateMarksHrs(originalLateMarksHrs);
					bean.setHalfDays(originalHalfDays);
					
					request.setAttribute("viewAttendanceDetails", originalLeaveDetails);
				} else {
					bean.setAttendanceDays(String.valueOf(newAttendanceDays));
					bean.setAttendanceHrs(newAttendanceHours);
					
					bean.setPaidLeaves(newPaidLeaveDays);
					bean.setPaidLeavesHrs(newPaidLeaveHours);
					
					bean.setUnPaidLeaves(newUnPaidLeaveDays);
					bean.setUnPaidLeavesHrs(newUnPaidLeaveHours);
					
					bean.setSalaryDays(String.valueOf(newSalaryDays));
					bean.setSalaryHrs(newSalaryHours);
				}
			} else {
				/**
				 * Get newly calculated attendance days and salary days
				 */
				double newAttendanceDays = Double.parseDouble(String.valueOf(detailsOfAttendance[0]));				
				String newPaidLeaveDays = String.valueOf(detailsOfAttendance[1]);				
				String newUnPaidLeaveDays = String.valueOf(detailsOfAttendance[2]);				
				double newSalaryDays = Double.parseDouble(String.valueOf(detailsOfAttendance[3]));
				
				/**
				 * If newly calculated attendance days or salary days are less than 0, then set late marks and half days to original,
				 * set original leave details and print message. I not, set newly calculated attendance days and salary days, 
				 * paid and unpaid leave days
				 */
				// attendance days are less than 0
				if(newAttendanceDays < 0) {
					addActionMessage(getMessage("attendanceDays") + " cannot be less than 0!");
					
					bean.setLateMarks(originalLateMarks);
					bean.setLateMarksHrs(originalLateMarksHrs);
					bean.setHalfDays(originalHalfDays);
					
					request.setAttribute("viewAttendanceDetails", originalLeaveDetails);
				}
				
				// salary days are less than 0
				else if(newSalaryDays < 0) {
					addActionMessage(getMessage("salaryDays") + " cannot be less than 0!");
					
					bean.setLateMarks(originalLateMarks);
					bean.setLateMarksHrs(originalLateMarksHrs);
					bean.setHalfDays(originalHalfDays);
					
					request.setAttribute("viewAttendanceDetails", originalLeaveDetails);
				} else {
					bean.setAttendanceDays(String.valueOf(newAttendanceDays));
					bean.setPaidLeaves(newPaidLeaveDays);
					bean.setUnPaidLeaves(newUnPaidLeaveDays);
					bean.setSalaryDays(String.valueOf(newSalaryDays));
				}
			}
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in recalculate in action:" + e);
		}
		return "viewAttendanceDetails";
	}
	
	/**
	 * Clears the fields and set current year
	 * @return String as SUCCESS
	 */
	public String reset() {
		try {
			bean.setMonth("");
			
			// Set the current year
			Calendar c = Calendar.getInstance();
			bean.setYear(String.valueOf(c.get(Calendar.YEAR)));
			
			bean.setDivisionId("");
			bean.setDivisionName("");
			bean.setBranchId("");
			bean.setBranchName("");
			bean.setDepartmentId("");
			bean.setDepartmentName("");
			bean.setEmployeeTypeId("");
			bean.setEmployeeTypeName("");
			bean.setPayBillId("");
			bean.setPayBilName("");
			bean.setSearchEmpId("");
			bean.setSearchEmpName("");
			bean.setAttendanceCode("");
			bean.setShowLockUnlock(false);
			bean.setLockAttendance(false);
		} catch(Exception e) {
			logger.error("Exception in reset in action:" + e);
		}
		return SUCCESS;
	}

	/**
	 * Saves the attendance details and leave details, update the leave balances
	 * @return String as "viewAttendanceDetails"
	 */
	public String saveAttendance() {
		try {
			ViewMonthAttendanceModel model = new ViewMonthAttendanceModel();
			model.initiate(context, session);
			
			// save attendance, get message as attendance is saved or not
			String message = model.saveAttendance(request);
			request.setAttribute("saveMessage", message);
			
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in saveAttendance:" + e);
		}
		return "viewAttendanceDetails";
	}

	/**
	 * Setter for ViewMonthAttendance object
	 * @param bean: ViewMonthAttendance object
	 */
	public void setBean(ViewMonthAttendance bean) {
		this.bean = bean;
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
			return sqlQuery;
		} catch (Exception e) {
			logger.error("Exception in setEmployeeOffciceFiletrs:" + e);
			return "";
		}
	}

	/**
	 * View processed attendance
	 * @return String as SUCCESS
	 */
	public String viewAttendance() {
		try {
			ViewMonthAttendanceModel model = new ViewMonthAttendanceModel();
			model.initiate(context, session);
			
			// Get the filters from a page
			String month = bean.getMonth();
			String year = bean.getYear();
			String branchId = bean.getBranchId();
			String departmentId = bean.getDepartmentId();
			String payBillId = bean.getPayBillId();
			String employeeTypeId = bean.getEmployeeTypeId();
			String divisionId = bean.getDivisionId();
			String searchEmpId = bean.getSearchEmpId();
			System.out.println(" isRecoveryFlowFlag    " +bean.isRecoveryFlowFlag());
			// Set list of selected filters
			String[] listOfFilters = new String[6];
			listOfFilters[0] = branchId;
			listOfFilters[1] = departmentId;
			listOfFilters[2] = payBillId;
			listOfFilters[3] = employeeTypeId;
			listOfFilters[4] = divisionId;
			listOfFilters[5] = searchEmpId;
			
			// Get saved attendance records
			Object[][] viewAttendance = model.viewAttendance(month, year, listOfFilters);			
			
			/**
			 * If attendance records are available, then only view them on a page. 
			 * Other wise view message that 'Attendance not available' 
			 */
			if(viewAttendance != null && viewAttendance.length > 0) {
				if(String.valueOf(viewAttendance[0][15]).equals("ATTN_START") 
						|| String.valueOf(viewAttendance[0][15]).equals("ATTN_UNLOCK")) {
					bean.setShowLockUnlock(true);
					bean.setLockAttendance(false);
				} else if(String.valueOf(viewAttendance[0][15]).equals("ATTN_READY") 
						|| String.valueOf(viewAttendance[0][15]).equals("SAL_START")) {
					bean.setShowLockUnlock(true);
					bean.setLockAttendance(true);
				} else {
					bean.setShowLockUnlock(false);
					bean.setLockAttendance(true);
				}
				bean.setRecoveryFlowFlag(model.getRecoveryFlowFlag());
				request.setAttribute("viewAttendance", viewAttendance);
				bean.setStatus(String.valueOf(viewAttendance[0][15]));
			} else {
				addActionMessage("Attendance not available!");
			}
			
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in viewAttendance:" + e);
		}
		return SUCCESS;
	}

	/**
	 * View processed attendance details and leave details for an employee
	 * @return String as SUCCESS
	 */
	public String viewAttendanceDetails() {
		try {
			ViewMonthAttendanceModel model = new ViewMonthAttendanceModel();
			model.initiate(context, session);
			
			// get attendance details
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			String attendanceCode = request.getParameter("attendanceCode");
			String empId = request.getParameter("empId");
			String recordNo = request.getParameter("recordNo");
			
			// set current month
			String monthName = Utility.month(Integer.parseInt(month));
			bean.setMonthName(monthName);
			
			// get attendance details
			Object[][] attendanceDetails = model.getAttendanceDetails(month, year, empId, attendanceCode);
			
			// set attendance details on a page
			bean.setEmpId(String.valueOf(attendanceDetails[0][0]));
			bean.setEmpToken(String.valueOf(attendanceDetails[0][1]));
			bean.setEmpName(String.valueOf(attendanceDetails[0][2]));
			bean.setEmpBranch(String.valueOf(attendanceDetails[0][3]));
			bean.setAttendanceDays(String.valueOf(attendanceDetails[0][4]));
			bean.setAttendanceHrs(String.valueOf(attendanceDetails[0][5]));
			bean.setWeeklyOffs(String.valueOf(attendanceDetails[0][6]));
			bean.setHolidays(String.valueOf(attendanceDetails[0][7]));
			bean.setLateMarks(String.valueOf(attendanceDetails[0][8]));
			bean.setLateMarksHrs(String.valueOf(attendanceDetails[0][9]));
			bean.setHalfDays(String.valueOf(attendanceDetails[0][10]));
			bean.setPaidLeaves(String.valueOf(attendanceDetails[0][11]));
			bean.setPaidLeavesHrs(String.valueOf(attendanceDetails[0][12]));
			bean.setPenaltiesAdjusted(String.valueOf(attendanceDetails[0][13]));
			bean.setUnauthAdjusted(String.valueOf(attendanceDetails[0][14]));
			bean.setUnPaidLeaves(String.valueOf(attendanceDetails[0][15]));
			bean.setUnPaidLeavesHrs(String.valueOf(attendanceDetails[0][16]));
			bean.setPenaltiesUnAdjusted(String.valueOf(attendanceDetails[0][17]));
			bean.setUnauthUnAdjusted(String.valueOf(attendanceDetails[0][18]));
			bean.setSystemUnPaidLeaves(String.valueOf(attendanceDetails[0][19]));
			bean.setSalaryDays(String.valueOf(attendanceDetails[0][20]));
			bean.setSalaryHrs(String.valueOf(attendanceDetails[0][21]));
			bean.setTotalAttendanceDays(String.valueOf(attendanceDetails[0][22]));
			bean.setEmpBranchId(String.valueOf(attendanceDetails[0][23]));
			bean.setEmpDivisionId(String.valueOf(attendanceDetails[0][24]));
			bean.setEmpDepartmentId(String.valueOf(attendanceDetails[0][25]));
			bean.setEmpDesignationId(String.valueOf(attendanceDetails[0][26]));
			bean.setEmpTypeId(String.valueOf(attendanceDetails[0][27]));
			bean.setEmpJoinDate(String.valueOf(attendanceDetails[0][28]));
			bean.setAutoPresentLateMark(Boolean.parseBoolean(String.valueOf(attendanceDetails[0][29])));
			bean.setAutoPresentHalfDay(Boolean.parseBoolean(String.valueOf(attendanceDetails[0][30])));
			bean.setAutoPresentAbsent(Boolean.parseBoolean(String.valueOf(attendanceDetails[0][31])));
			bean.setLmEnabled(Boolean.parseBoolean(String.valueOf(attendanceDetails[0][32])));
			bean.setLmInNoEnabled(Boolean.parseBoolean(String.valueOf(attendanceDetails[0][33])));
			bean.setLmCount(String.valueOf(attendanceDetails[0][34]));
			bean.setLmAdjustLeaveDays(String.valueOf(attendanceDetails[0][35]));
			bean.setLmAdjustLeaveTypes(String.valueOf(attendanceDetails[0][36]));
			bean.setLmInHrsEnabled(Boolean.parseBoolean(String.valueOf(attendanceDetails[0][37])));
			bean.setLmDeductNonRegLeaveTypes(String.valueOf(attendanceDetails[0][38]));
			bean.setHdEnabled(Boolean.parseBoolean(String.valueOf(attendanceDetails[0][39])));
			bean.setHdDeductLeaveTypes(String.valueOf(attendanceDetails[0][40]));
			bean.setWoFixedEnabled(Boolean.parseBoolean(String.valueOf(attendanceDetails[0][41])));
			bean.setWoDaysForWeek1(String.valueOf(attendanceDetails[0][42]));
			bean.setWoDaysForWeek2(String.valueOf(attendanceDetails[0][43]));
			bean.setWoDaysForWeek3(String.valueOf(attendanceDetails[0][44]));
			bean.setWoDaysForWeek4(String.valueOf(attendanceDetails[0][45]));
			bean.setWoDaysForWeek5(String.valueOf(attendanceDetails[0][46]));
			bean.setWoDaysForWeek6(String.valueOf(attendanceDetails[0][47]));
			bean.setWorkingHours(String.valueOf(attendanceDetails[0][48]));
			bean.setRecoveryDays(String.valueOf(attendanceDetails[0][49]));
			bean.setRecordNo(recordNo);
			
			String empDivisionId = bean.getEmpDivisionId();
			String empBranchId = bean.getEmpBranchId();
			String empDepartmentId = bean.getEmpDepartmentId();
			String empDesignationId = bean.getEmpDesignationId();
			String empTypeId = bean.getEmpTypeId();
			
			// Set recovery info
			
			bean.setRecoveryFlowFlag(model.getRecoveryFlowFlag());
			
			// Set leave policy information
			LeavePolicyData leavePolicyData = new LeavePolicyData(empDivisionId);
			leavePolicyData.initiate(context, session);
			
			leavePolicyData.setLeavePolicyObject();
			Object[][] leavePolicies = leavePolicyData.getLeavePolicyObj();
			
			// Get Leave Policy Id for an employee
			String leavePolicyId = leavePolicyData.getLeavePolicyCode(empId, empDivisionId, empDepartmentId, empDesignationId, empBranchId, empTypeId);
			
			if(leavePolicies != null && leavePolicies.length > 0) {
				for(int i = 0; i < leavePolicies.length; i++) {
					if(leavePolicyId.equals(String.valueOf(leavePolicies[i][2]))) {
						bean.setLeaveUnplanFlag(Boolean.parseBoolean(String.valueOf(leavePolicies[i][7])));
						bean.setLeaveUnauthFlag(Boolean.parseBoolean(String.valueOf(leavePolicies[i][3])));
					}
				}
			}
			
			leavePolicyData.terminate();
			
			boolean lmInHrsEnabled = bean.isLmInHrsEnabled();
			double workingHours = Double.parseDouble(bean.getWorkingHours());
			
			Object[][] viewAttendanceDetails = model.viewAttendanceDetails(month, year, empId, leavePolicyId, attendanceCode, leavePolicies, 
				lmInHrsEnabled, workingHours);
			
			request.setAttribute("viewAttendanceDetails", viewAttendanceDetails);
			
			return "viewAttendanceDetails";
		} catch(Exception e) {
			logger.error("Exception in viewAttendanceDetails:" + e);
			return "viewAttendanceDetails";
		}
	}
}