// @author: Bhushan Dasare   @date: July 3, 2009

package org.paradyne.bean.attendance;

public class ViewMonthAttendance extends org.paradyne.lib.BeanBase {
	private String month;
	private String monthName;
	private String year;
	private String branchId;
	private String branchName;
	private String departmentId;
	private String departmentName;
	private String payBillId;
	private String payBilName;
	private String employeeTypeId;
	private String employeeTypeName;
	private String divisionId;
	private String divisionName;
	private String searchEmpId;
	private String searchEmpName;
	private String attendanceCode;
	private String empId;
	private String empToken;
	private String empName;
	private String empBranch;
	private String attendanceDays;
	private String attendanceHrs;
	private String weeklyOffs;
	private String holidays;
	private String lateMarks;
	private String lateMarksHrs;
	private String halfDays;
	private String paidLeaves;
	private String paidLeavesHrs;
	private String penaltiesAdjusted;
	private String unauthAdjusted;
	private String unPaidLeaves;
	private String unPaidLeavesHrs;
	private String penaltiesUnAdjusted;
	private String unauthUnAdjusted;
	private String systemUnPaidLeaves;
	private String salaryDays;
	private String salaryHrs;
	private String totalAttendanceDays;
	private String empBranchId;
	private String empDivisionId;
	private String empDepartmentId;
	private String empDesignationId;
	private String empTypeId;
	private String empJoinDate;
	private boolean autoPresentLateMark = false;
	private boolean autoPresentHalfDay = false;
	private boolean autoPresentAbsent = false;
	private boolean lmEnabled = false;
	private boolean lmInNoEnabled = false;
	private String lmCount;
	private String lmAdjustLeaveDays;
	private String lmAdjustLeaveTypes;
	private boolean lmInHrsEnabled = false;
	private String lmDeductNonRegLeaveTypes;
	private boolean hdEnabled = false;
	private String hdDeductLeaveTypes;
	private boolean woFixedEnabled = false;
	private String woDaysForWeek1;
	private String woDaysForWeek2;
	private String woDaysForWeek3;
	private String woDaysForWeek4;
	private String woDaysForWeek5;
	private String woDaysForWeek6;
	private String workingHours;
	private String recordNo;
	private boolean lockAttendance = false;
	private boolean showLockUnlock = false;
	private String status;
	private boolean leaveUnplanFlag = false;
	private boolean leaveUnauthFlag = false;
	
	
	private boolean recoveryFlowFlag=false;
	private String recoveryDays="";
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the monthName
	 */
	public String getMonthName() {
		return monthName;
	}
	/**
	 * @param monthName the monthName to set
	 */
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the payBillId
	 */
	public String getPayBillId() {
		return payBillId;
	}
	/**
	 * @param payBillId the payBillId to set
	 */
	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}
	/**
	 * @return the payBilName
	 */
	public String getPayBilName() {
		return payBilName;
	}
	/**
	 * @param payBilName the payBilName to set
	 */
	public void setPayBilName(String payBilName) {
		this.payBilName = payBilName;
	}
	/**
	 * @return the employeeTypeId
	 */
	public String getEmployeeTypeId() {
		return employeeTypeId;
	}
	/**
	 * @param employeeTypeId the employeeTypeId to set
	 */
	public void setEmployeeTypeId(String employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	/**
	 * @return the employeeTypeName
	 */
	public String getEmployeeTypeName() {
		return employeeTypeName;
	}
	/**
	 * @param employeeTypeName the employeeTypeName to set
	 */
	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}
	/**
	 * @return the divisionId
	 */
	public String getDivisionId() {
		return divisionId;
	}
	/**
	 * @param divisionId the divisionId to set
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}
	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	/**
	 * @return the searchEmpId
	 */
	public String getSearchEmpId() {
		return searchEmpId;
	}
	/**
	 * @param searchEmpId the searchEmpId to set
	 */
	public void setSearchEmpId(String searchEmpId) {
		this.searchEmpId = searchEmpId;
	}
	/**
	 * @return the searchEmpName
	 */
	public String getSearchEmpName() {
		return searchEmpName;
	}
	/**
	 * @param searchEmpName the searchEmpName to set
	 */
	public void setSearchEmpName(String searchEmpName) {
		this.searchEmpName = searchEmpName;
	}
	/**
	 * @return the attendanceCode
	 */
	public String getAttendanceCode() {
		return attendanceCode;
	}
	/**
	 * @param attendanceCode the attendanceCode to set
	 */
	public void setAttendanceCode(String attendanceCode) {
		this.attendanceCode = attendanceCode;
	}
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}
	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the empBranch
	 */
	public String getEmpBranch() {
		return empBranch;
	}
	/**
	 * @param empBranch the empBranch to set
	 */
	public void setEmpBranch(String empBranch) {
		this.empBranch = empBranch;
	}
	/**
	 * @return the attendanceDays
	 */
	public String getAttendanceDays() {
		return attendanceDays;
	}
	/**
	 * @param attendanceDays the attendanceDays to set
	 */
	public void setAttendanceDays(String attendanceDays) {
		this.attendanceDays = attendanceDays;
	}
	/**
	 * @return the attendanceHrs
	 */
	public String getAttendanceHrs() {
		return attendanceHrs;
	}
	/**
	 * @param attendanceHrs the attendanceHrs to set
	 */
	public void setAttendanceHrs(String attendanceHrs) {
		this.attendanceHrs = attendanceHrs;
	}
	/**
	 * @return the weeklyOffs
	 */
	public String getWeeklyOffs() {
		return weeklyOffs;
	}
	/**
	 * @param weeklyOffs the weeklyOffs to set
	 */
	public void setWeeklyOffs(String weeklyOffs) {
		this.weeklyOffs = weeklyOffs;
	}
	/**
	 * @return the holidays
	 */
	public String getHolidays() {
		return holidays;
	}
	/**
	 * @param holidays the holidays to set
	 */
	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}
	/**
	 * @return the lateMarks
	 */
	public String getLateMarks() {
		return lateMarks;
	}
	/**
	 * @param lateMarks the lateMarks to set
	 */
	public void setLateMarks(String lateMarks) {
		this.lateMarks = lateMarks;
	}
	/**
	 * @return the lateMarksHrs
	 */
	public String getLateMarksHrs() {
		return lateMarksHrs;
	}
	/**
	 * @param lateMarksHrs the lateMarksHrs to set
	 */
	public void setLateMarksHrs(String lateMarksHrs) {
		this.lateMarksHrs = lateMarksHrs;
	}
	/**
	 * @return the halfDays
	 */
	public String getHalfDays() {
		return halfDays;
	}
	/**
	 * @param halfDays the halfDays to set
	 */
	public void setHalfDays(String halfDays) {
		this.halfDays = halfDays;
	}
	/**
	 * @return the paidLeaves
	 */
	public String getPaidLeaves() {
		return paidLeaves;
	}
	/**
	 * @param paidLeaves the paidLeaves to set
	 */
	public void setPaidLeaves(String paidLeaves) {
		this.paidLeaves = paidLeaves;
	}
	/**
	 * @return the paidLeavesHrs
	 */
	public String getPaidLeavesHrs() {
		return paidLeavesHrs;
	}
	/**
	 * @param paidLeavesHrs the paidLeavesHrs to set
	 */
	public void setPaidLeavesHrs(String paidLeavesHrs) {
		this.paidLeavesHrs = paidLeavesHrs;
	}
	/**
	 * @return the penaltiesAdjusted
	 */
	public String getPenaltiesAdjusted() {
		return penaltiesAdjusted;
	}
	/**
	 * @param penaltiesAdjusted the penaltiesAdjusted to set
	 */
	public void setPenaltiesAdjusted(String penaltiesAdjusted) {
		this.penaltiesAdjusted = penaltiesAdjusted;
	}
	/**
	 * @return the unauthAdjusted
	 */
	public String getUnauthAdjusted() {
		return unauthAdjusted;
	}
	/**
	 * @param unauthAdjusted the unauthAdjusted to set
	 */
	public void setUnauthAdjusted(String unauthAdjusted) {
		this.unauthAdjusted = unauthAdjusted;
	}
	/**
	 * @return the unPaidLeaves
	 */
	public String getUnPaidLeaves() {
		return unPaidLeaves;
	}
	/**
	 * @param unPaidLeaves the unPaidLeaves to set
	 */
	public void setUnPaidLeaves(String unPaidLeaves) {
		this.unPaidLeaves = unPaidLeaves;
	}
	/**
	 * @return the unPaidLeavesHrs
	 */
	public String getUnPaidLeavesHrs() {
		return unPaidLeavesHrs;
	}
	/**
	 * @param unPaidLeavesHrs the unPaidLeavesHrs to set
	 */
	public void setUnPaidLeavesHrs(String unPaidLeavesHrs) {
		this.unPaidLeavesHrs = unPaidLeavesHrs;
	}
	/**
	 * @return the penaltiesUnAdjusted
	 */
	public String getPenaltiesUnAdjusted() {
		return penaltiesUnAdjusted;
	}
	/**
	 * @param penaltiesUnAdjusted the penaltiesUnAdjusted to set
	 */
	public void setPenaltiesUnAdjusted(String penaltiesUnAdjusted) {
		this.penaltiesUnAdjusted = penaltiesUnAdjusted;
	}
	/**
	 * @return the unauthUnAdjusted
	 */
	public String getUnauthUnAdjusted() {
		return unauthUnAdjusted;
	}
	/**
	 * @param unauthUnAdjusted the unauthUnAdjusted to set
	 */
	public void setUnauthUnAdjusted(String unauthUnAdjusted) {
		this.unauthUnAdjusted = unauthUnAdjusted;
	}
	/**
	 * @return the systemUnPaidLeaves
	 */
	public String getSystemUnPaidLeaves() {
		return systemUnPaidLeaves;
	}
	/**
	 * @param systemUnPaidLeaves the systemUnPaidLeaves to set
	 */
	public void setSystemUnPaidLeaves(String systemUnPaidLeaves) {
		this.systemUnPaidLeaves = systemUnPaidLeaves;
	}
	/**
	 * @return the salaryDays
	 */
	public String getSalaryDays() {
		return salaryDays;
	}
	/**
	 * @param salaryDays the salaryDays to set
	 */
	public void setSalaryDays(String salaryDays) {
		this.salaryDays = salaryDays;
	}
	/**
	 * @return the salaryHrs
	 */
	public String getSalaryHrs() {
		return salaryHrs;
	}
	/**
	 * @param salaryHrs the salaryHrs to set
	 */
	public void setSalaryHrs(String salaryHrs) {
		this.salaryHrs = salaryHrs;
	}
	/**
	 * @return the totalAttendanceDays
	 */
	public String getTotalAttendanceDays() {
		return totalAttendanceDays;
	}
	/**
	 * @param totalAttendanceDays the totalAttendanceDays to set
	 */
	public void setTotalAttendanceDays(String totalAttendanceDays) {
		this.totalAttendanceDays = totalAttendanceDays;
	}
	/**
	 * @return the empBranchId
	 */
	public String getEmpBranchId() {
		return empBranchId;
	}
	/**
	 * @param empBranchId the empBranchId to set
	 */
	public void setEmpBranchId(String empBranchId) {
		this.empBranchId = empBranchId;
	}
	/**
	 * @return the empDivisionId
	 */
	public String getEmpDivisionId() {
		return empDivisionId;
	}
	/**
	 * @param empDivisionId the empDivisionId to set
	 */
	public void setEmpDivisionId(String empDivisionId) {
		this.empDivisionId = empDivisionId;
	}
	/**
	 * @return the empDepartmentId
	 */
	public String getEmpDepartmentId() {
		return empDepartmentId;
	}
	/**
	 * @param empDepartmentId the empDepartmentId to set
	 */
	public void setEmpDepartmentId(String empDepartmentId) {
		this.empDepartmentId = empDepartmentId;
	}
	/**
	 * @return the empDesignationId
	 */
	public String getEmpDesignationId() {
		return empDesignationId;
	}
	/**
	 * @param empDesignationId the empDesignationId to set
	 */
	public void setEmpDesignationId(String empDesignationId) {
		this.empDesignationId = empDesignationId;
	}
	/**
	 * @return the empTypeId
	 */
	public String getEmpTypeId() {
		return empTypeId;
	}
	/**
	 * @param empTypeId the empTypeId to set
	 */
	public void setEmpTypeId(String empTypeId) {
		this.empTypeId = empTypeId;
	}
	/**
	 * @return the empJoinDate
	 */
	public String getEmpJoinDate() {
		return empJoinDate;
	}
	/**
	 * @param empJoinDate the empJoinDate to set
	 */
	public void setEmpJoinDate(String empJoinDate) {
		this.empJoinDate = empJoinDate;
	}
	/**
	 * @return the autoPresentLateMark
	 */
	public boolean isAutoPresentLateMark() {
		return autoPresentLateMark;
	}
	/**
	 * @param autoPresentLateMark the autoPresentLateMark to set
	 */
	public void setAutoPresentLateMark(boolean autoPresentLateMark) {
		this.autoPresentLateMark = autoPresentLateMark;
	}
	/**
	 * @return the autoPresentHalfDay
	 */
	public boolean isAutoPresentHalfDay() {
		return autoPresentHalfDay;
	}
	/**
	 * @param autoPresentHalfDay the autoPresentHalfDay to set
	 */
	public void setAutoPresentHalfDay(boolean autoPresentHalfDay) {
		this.autoPresentHalfDay = autoPresentHalfDay;
	}
	/**
	 * @return the autoPresentAbsent
	 */
	public boolean isAutoPresentAbsent() {
		return autoPresentAbsent;
	}
	/**
	 * @param autoPresentAbsent the autoPresentAbsent to set
	 */
	public void setAutoPresentAbsent(boolean autoPresentAbsent) {
		this.autoPresentAbsent = autoPresentAbsent;
	}
	/**
	 * @return the lmEnabled
	 */
	public boolean isLmEnabled() {
		return lmEnabled;
	}
	/**
	 * @param lmEnabled the lmEnabled to set
	 */
	public void setLmEnabled(boolean lmEnabled) {
		this.lmEnabled = lmEnabled;
	}
	/**
	 * @return the lmInNoEnabled
	 */
	public boolean isLmInNoEnabled() {
		return lmInNoEnabled;
	}
	/**
	 * @param lmInNoEnabled the lmInNoEnabled to set
	 */
	public void setLmInNoEnabled(boolean lmInNoEnabled) {
		this.lmInNoEnabled = lmInNoEnabled;
	}
	/**
	 * @return the lmCount
	 */
	public String getLmCount() {
		return lmCount;
	}
	/**
	 * @param lmCount the lmCount to set
	 */
	public void setLmCount(String lmCount) {
		this.lmCount = lmCount;
	}
	/**
	 * @return the lmAdjustLeaveDays
	 */
	public String getLmAdjustLeaveDays() {
		return lmAdjustLeaveDays;
	}
	/**
	 * @param lmAdjustLeaveDays the lmAdjustLeaveDays to set
	 */
	public void setLmAdjustLeaveDays(String lmAdjustLeaveDays) {
		this.lmAdjustLeaveDays = lmAdjustLeaveDays;
	}
	/**
	 * @return the lmAdjustLeaveTypes
	 */
	public String getLmAdjustLeaveTypes() {
		return lmAdjustLeaveTypes;
	}
	/**
	 * @param lmAdjustLeaveTypes the lmAdjustLeaveTypes to set
	 */
	public void setLmAdjustLeaveTypes(String lmAdjustLeaveTypes) {
		this.lmAdjustLeaveTypes = lmAdjustLeaveTypes;
	}
	/**
	 * @return the lmInHrsEnabled
	 */
	public boolean isLmInHrsEnabled() {
		return lmInHrsEnabled;
	}
	/**
	 * @param lmInHrsEnabled the lmInHrsEnabled to set
	 */
	public void setLmInHrsEnabled(boolean lmInHrsEnabled) {
		this.lmInHrsEnabled = lmInHrsEnabled;
	}
	/**
	 * @return the lmDeductNonRegLeaveTypes
	 */
	public String getLmDeductNonRegLeaveTypes() {
		return lmDeductNonRegLeaveTypes;
	}
	/**
	 * @param lmDeductNonRegLeaveTypes the lmDeductNonRegLeaveTypes to set
	 */
	public void setLmDeductNonRegLeaveTypes(String lmDeductNonRegLeaveTypes) {
		this.lmDeductNonRegLeaveTypes = lmDeductNonRegLeaveTypes;
	}
	/**
	 * @return the hdEnabled
	 */
	public boolean isHdEnabled() {
		return hdEnabled;
	}
	/**
	 * @param hdEnabled the hdEnabled to set
	 */
	public void setHdEnabled(boolean hdEnabled) {
		this.hdEnabled = hdEnabled;
	}
	/**
	 * @return the hdDeductLeaveTypes
	 */
	public String getHdDeductLeaveTypes() {
		return hdDeductLeaveTypes;
	}
	/**
	 * @param hdDeductLeaveTypes the hdDeductLeaveTypes to set
	 */
	public void setHdDeductLeaveTypes(String hdDeductLeaveTypes) {
		this.hdDeductLeaveTypes = hdDeductLeaveTypes;
	}
	/**
	 * @return the woFixedEnabled
	 */
	public boolean isWoFixedEnabled() {
		return woFixedEnabled;
	}
	/**
	 * @param woFixedEnabled the woFixedEnabled to set
	 */
	public void setWoFixedEnabled(boolean woFixedEnabled) {
		this.woFixedEnabled = woFixedEnabled;
	}
	/**
	 * @return the woDaysForWeek1
	 */
	public String getWoDaysForWeek1() {
		return woDaysForWeek1;
	}
	/**
	 * @param woDaysForWeek1 the woDaysForWeek1 to set
	 */
	public void setWoDaysForWeek1(String woDaysForWeek1) {
		this.woDaysForWeek1 = woDaysForWeek1;
	}
	/**
	 * @return the woDaysForWeek2
	 */
	public String getWoDaysForWeek2() {
		return woDaysForWeek2;
	}
	/**
	 * @param woDaysForWeek2 the woDaysForWeek2 to set
	 */
	public void setWoDaysForWeek2(String woDaysForWeek2) {
		this.woDaysForWeek2 = woDaysForWeek2;
	}
	/**
	 * @return the woDaysForWeek3
	 */
	public String getWoDaysForWeek3() {
		return woDaysForWeek3;
	}
	/**
	 * @param woDaysForWeek3 the woDaysForWeek3 to set
	 */
	public void setWoDaysForWeek3(String woDaysForWeek3) {
		this.woDaysForWeek3 = woDaysForWeek3;
	}
	/**
	 * @return the woDaysForWeek4
	 */
	public String getWoDaysForWeek4() {
		return woDaysForWeek4;
	}
	/**
	 * @param woDaysForWeek4 the woDaysForWeek4 to set
	 */
	public void setWoDaysForWeek4(String woDaysForWeek4) {
		this.woDaysForWeek4 = woDaysForWeek4;
	}
	/**
	 * @return the woDaysForWeek5
	 */
	public String getWoDaysForWeek5() {
		return woDaysForWeek5;
	}
	/**
	 * @param woDaysForWeek5 the woDaysForWeek5 to set
	 */
	public void setWoDaysForWeek5(String woDaysForWeek5) {
		this.woDaysForWeek5 = woDaysForWeek5;
	}
	/**
	 * @return the woDaysForWeek6
	 */
	public String getWoDaysForWeek6() {
		return woDaysForWeek6;
	}
	/**
	 * @param woDaysForWeek6 the woDaysForWeek6 to set
	 */
	public void setWoDaysForWeek6(String woDaysForWeek6) {
		this.woDaysForWeek6 = woDaysForWeek6;
	}
	/**
	 * @return the workingHours
	 */
	public String getWorkingHours() {
		return workingHours;
	}
	/**
	 * @param workingHours the workingHours to set
	 */
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
	/**
	 * @return the recordNo
	 */
	public String getRecordNo() {
		return recordNo;
	}
	/**
	 * @param recordNo the recordNo to set
	 */
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	/**
	 * @return the lockAttendance
	 */
	public boolean isLockAttendance() {
		return lockAttendance;
	}
	/**
	 * @param lockAttendance the lockAttendance to set
	 */
	public void setLockAttendance(boolean lockAttendance) {
		this.lockAttendance = lockAttendance;
	}
	/**
	 * @return the showLockUnlock
	 */
	public boolean isShowLockUnlock() {
		return showLockUnlock;
	}
	/**
	 * @param showLockUnlock the showLockUnlock to set
	 */
	public void setShowLockUnlock(boolean showLockUnlock) {
		this.showLockUnlock = showLockUnlock;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the leaveUnplanFlag
	 */
	public boolean isLeaveUnplanFlag() {
		return leaveUnplanFlag;
	}
	/**
	 * @param leaveUnplanFlag the leaveUnplanFlag to set
	 */
	public void setLeaveUnplanFlag(boolean leaveUnplanFlag) {
		this.leaveUnplanFlag = leaveUnplanFlag;
	}
	/**
	 * @return the leaveUnauthFlag
	 */
	public boolean isLeaveUnauthFlag() {
		return leaveUnauthFlag;
	}
	/**
	 * @param leaveUnauthFlag the leaveUnauthFlag to set
	 */
	public void setLeaveUnauthFlag(boolean leaveUnauthFlag) {
		this.leaveUnauthFlag = leaveUnauthFlag;
	}
	public boolean isRecoveryFlowFlag() {
		return recoveryFlowFlag;
	}
	public void setRecoveryFlowFlag(boolean recoveryFlowFlag) {
		this.recoveryFlowFlag = recoveryFlowFlag;
	}
	public String getRecoveryDays() {
		return recoveryDays;
	}
	public void setRecoveryDays(String recoveryDays) {
		this.recoveryDays = recoveryDays;
	}
	
}