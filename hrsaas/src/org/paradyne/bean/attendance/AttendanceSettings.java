package org.paradyne.bean.attendance;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class AttendanceSettings extends BeanBase {	
	private String attSettingsCode = "";
	private boolean attendanceFlag;
	private boolean leaveApplicationFlag;
	private boolean branchHolidayFlag;
	private String startDate;
	private String salaryFlag;
	private String compOffCode;
	private String compOffName;
	private String recordPerPage;
	private String  leaveMngtStartMonth ="";
	private String outdoorBlockAfterDays="";
	/*private String leaveTypeForLateMarks;
	private String leaveTypeForHalfDay;
	private String adjustHalfDay;
	private HashMap leaveTypeMap;
	private String noOflateMarksForOneLeave;
	private String lateMarksLeave;
	private String lateMarksLeaveCode;
	private String noOflateMarksForHalfDay;
	private String halfDayLeave;
	private String halfDayLeaveCode;*/
	private String srNo;
	
	private String alertLeave;
	/*private String leaveTypeFlag = "false";
	private String lateMarksFlag = "false";
	private String halfDayFlag = "false";
	private String lateMarksWithHalfDayFlag = "false";*/
	
	private String leaveCode;
	private String leaveName;
	private String leaveAbbr;
	private ArrayList leaveDataList;
	
	private String reportType;
	private String branchName;
	private String branchCode;
	private String divName;
	private String divCode;
	private String timeFormat;
	private String sheetNum;	
	
	private String dateFormat;
	private String ddFormat;
	private String ddSeparator;
	private String mmFormat;
	private String mmSeparator;
	private String yyFormat;
	
	private boolean empFlag;
	private String empCharFrom;
	private String empCharTo;
	private String empColumn;
	private String empColumnNoCsv;
	
	private boolean empNameFlag;
	private String empNameCharFrom;
	private String empNameCharTo;
	private String empNameColumn;
	private String empNameColumnNoCsv;
	
	private boolean dateFlag;
	private String dateCharFrom;
	private String dateCharTo;
	private String dateColumn;
	private String dateColumnNoCsv;
	
	private boolean inOutFlag;
	private boolean inTimeFlag;
	private String inTimeCharFrom;
	private String inTimeCharTo;
	private String inTimeColumn;
	private String inTimeColumnNoCsv;
	private boolean outTimeFlag;
	private String outTimeCharFrom;
	private String outTimeCharTo;
	private String outTimeColumn;
	private String outTimeColumnNoCsv;
	
	private boolean oneTimeFlag;
	private boolean timeFlag;
	private String timeCharFrom;
	private String timeCharTo;
	private String timeColumn;
	private String timeColumnNoCsv;
	private boolean checkFlag;
	private String checkCharFrom;
	private String checkCharTo;
	private String checkColumn;
	private String checkColumnNoCsv;
	
	private boolean workHrsFlag;
	private String workHrsCharFrom;
	private String workHrsCharTo;
	private String workHrsColumn;
	private String workHrsColumnNoCsv;
	
	private boolean shiftFlag;
	private String shiftCharFrom;
	private String shiftCharTo;
	private String shiftColumn;
	private String shiftColumnNoCsv;
	
	private String timeFlagType;
	
	private String leaveCodeHid = "";
	private String leaveAbbrHid = "";
	
	private String leaveCodeHidNext = "";
	private String leaveAbbrHidNext = "";
	
	private String divID;
	private String aID;
	
	private boolean monthlyAttendanceFlag;
	private String monthlyPaidleaveCode ="";
	private String monthlyPaidleave ="";
	
	private String compoffdays="";
	private String  empJoinBefore ="";
	private String  defaultDay;
	
	private String defaultDayHidden;
	private boolean loginAttendanceFlag = false;
	
	private String lateRegularizationCheckBox = "";
	private String halfDayRegularizationCheckBox = "";
	private String absentRegularizationCheckBox = "";
	private String regularTimeRegularizationCheckBox = "";
	private String weekyOffHolidayCheckBox = "";
	private String showExtraWorkBenifitCheckBox = "";
	private boolean otFlag;
	
	public boolean isOtFlag() {
		return otFlag;
	}

	public void setOtFlag(boolean otFlag) {
		this.otFlag = otFlag;
	}

	public String getShowExtraWorkBenifitCheckBox() {
		return showExtraWorkBenifitCheckBox;
	}

	public void setShowExtraWorkBenifitCheckBox(String showExtraWorkBenifitCheckBox) {
		this.showExtraWorkBenifitCheckBox = showExtraWorkBenifitCheckBox;
	}

	public String getWeekyOffHolidayCheckBox() {
		return weekyOffHolidayCheckBox;
	}

	public void setWeekyOffHolidayCheckBox(String weekyOffHolidayCheckBox) {
		this.weekyOffHolidayCheckBox = weekyOffHolidayCheckBox;
	}

	public String getAttSettingsCode() {
		return attSettingsCode;
	}

	public void setAttSettingsCode(String attSettingsCode) {
		this.attSettingsCode = attSettingsCode;
	}

	public boolean isAttendanceFlag() {
		return attendanceFlag;
	}

	public void setAttendanceFlag(boolean attendanceFlag) {
		this.attendanceFlag = attendanceFlag;
	}

	public boolean isLeaveApplicationFlag() {
		return leaveApplicationFlag;
	}

	public void setLeaveApplicationFlag(boolean leaveApplicationFlag) {
		this.leaveApplicationFlag = leaveApplicationFlag;
	}

	public boolean isBranchHolidayFlag() {
		return branchHolidayFlag;
	}

	public void setBranchHolidayFlag(boolean branchHolidayFlag) {
		this.branchHolidayFlag = branchHolidayFlag;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getSalaryFlag() {
		return salaryFlag;
	}

	public void setSalaryFlag(String salaryFlag) {
		this.salaryFlag = salaryFlag;
	}

	public String getCompOffCode() {
		return compOffCode;
	}

	public void setCompOffCode(String compOffCode) {
		this.compOffCode = compOffCode;
	}

	public String getCompOffName() {
		return compOffName;
	}

	public void setCompOffName(String compOffName) {
		this.compOffName = compOffName;
	}

	public String getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(String recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public String getLeaveMngtStartMonth() {
		return leaveMngtStartMonth;
	}

	public void setLeaveMngtStartMonth(String leaveMngtStartMonth) {
		this.leaveMngtStartMonth = leaveMngtStartMonth;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getLeaveAbbr() {
		return leaveAbbr;
	}

	public void setLeaveAbbr(String leaveAbbr) {
		this.leaveAbbr = leaveAbbr;
	}

	public ArrayList getLeaveDataList() {
		return leaveDataList;
	}

	public void setLeaveDataList(ArrayList leaveDataList) {
		this.leaveDataList = leaveDataList;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(String sheetNum) {
		this.sheetNum = sheetNum;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getDdFormat() {
		return ddFormat;
	}

	public void setDdFormat(String ddFormat) {
		this.ddFormat = ddFormat;
	}

	public String getDdSeparator() {
		return ddSeparator;
	}

	public void setDdSeparator(String ddSeparator) {
		this.ddSeparator = ddSeparator;
	}

	public String getMmFormat() {
		return mmFormat;
	}

	public void setMmFormat(String mmFormat) {
		this.mmFormat = mmFormat;
	}

	public String getMmSeparator() {
		return mmSeparator;
	}

	public void setMmSeparator(String mmSeparator) {
		this.mmSeparator = mmSeparator;
	}

	public String getYyFormat() {
		return yyFormat;
	}

	public void setYyFormat(String yyFormat) {
		this.yyFormat = yyFormat;
	}

	public boolean isEmpFlag() {
		return empFlag;
	}

	public void setEmpFlag(boolean empFlag) {
		this.empFlag = empFlag;
	}

	public String getEmpCharFrom() {
		return empCharFrom;
	}

	public void setEmpCharFrom(String empCharFrom) {
		this.empCharFrom = empCharFrom;
	}

	public String getEmpCharTo() {
		return empCharTo;
	}

	public void setEmpCharTo(String empCharTo) {
		this.empCharTo = empCharTo;
	}

	public String getEmpColumn() {
		return empColumn;
	}

	public void setEmpColumn(String empColumn) {
		this.empColumn = empColumn;
	}

	public String getEmpColumnNoCsv() {
		return empColumnNoCsv;
	}

	public void setEmpColumnNoCsv(String empColumnNoCsv) {
		this.empColumnNoCsv = empColumnNoCsv;
	}

	public boolean isEmpNameFlag() {
		return empNameFlag;
	}

	public void setEmpNameFlag(boolean empNameFlag) {
		this.empNameFlag = empNameFlag;
	}

	public String getEmpNameCharFrom() {
		return empNameCharFrom;
	}

	public void setEmpNameCharFrom(String empNameCharFrom) {
		this.empNameCharFrom = empNameCharFrom;
	}

	public String getEmpNameCharTo() {
		return empNameCharTo;
	}

	public void setEmpNameCharTo(String empNameCharTo) {
		this.empNameCharTo = empNameCharTo;
	}

	public String getEmpNameColumn() {
		return empNameColumn;
	}

	public void setEmpNameColumn(String empNameColumn) {
		this.empNameColumn = empNameColumn;
	}

	public String getEmpNameColumnNoCsv() {
		return empNameColumnNoCsv;
	}

	public void setEmpNameColumnNoCsv(String empNameColumnNoCsv) {
		this.empNameColumnNoCsv = empNameColumnNoCsv;
	}

	public boolean isDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(boolean dateFlag) {
		this.dateFlag = dateFlag;
	}

	public String getDateCharFrom() {
		return dateCharFrom;
	}

	public void setDateCharFrom(String dateCharFrom) {
		this.dateCharFrom = dateCharFrom;
	}

	public String getDateCharTo() {
		return dateCharTo;
	}

	public void setDateCharTo(String dateCharTo) {
		this.dateCharTo = dateCharTo;
	}

	public String getDateColumn() {
		return dateColumn;
	}

	public void setDateColumn(String dateColumn) {
		this.dateColumn = dateColumn;
	}

	public String getDateColumnNoCsv() {
		return dateColumnNoCsv;
	}

	public void setDateColumnNoCsv(String dateColumnNoCsv) {
		this.dateColumnNoCsv = dateColumnNoCsv;
	}

	public boolean isInOutFlag() {
		return inOutFlag;
	}

	public void setInOutFlag(boolean inOutFlag) {
		this.inOutFlag = inOutFlag;
	}

	public boolean isInTimeFlag() {
		return inTimeFlag;
	}

	public void setInTimeFlag(boolean inTimeFlag) {
		this.inTimeFlag = inTimeFlag;
	}

	public String getInTimeCharFrom() {
		return inTimeCharFrom;
	}

	public void setInTimeCharFrom(String inTimeCharFrom) {
		this.inTimeCharFrom = inTimeCharFrom;
	}

	public String getInTimeCharTo() {
		return inTimeCharTo;
	}

	public void setInTimeCharTo(String inTimeCharTo) {
		this.inTimeCharTo = inTimeCharTo;
	}

	public String getInTimeColumn() {
		return inTimeColumn;
	}

	public void setInTimeColumn(String inTimeColumn) {
		this.inTimeColumn = inTimeColumn;
	}

	public String getInTimeColumnNoCsv() {
		return inTimeColumnNoCsv;
	}

	public void setInTimeColumnNoCsv(String inTimeColumnNoCsv) {
		this.inTimeColumnNoCsv = inTimeColumnNoCsv;
	}

	public boolean isOutTimeFlag() {
		return outTimeFlag;
	}

	public void setOutTimeFlag(boolean outTimeFlag) {
		this.outTimeFlag = outTimeFlag;
	}

	public String getOutTimeCharFrom() {
		return outTimeCharFrom;
	}

	public void setOutTimeCharFrom(String outTimeCharFrom) {
		this.outTimeCharFrom = outTimeCharFrom;
	}

	public String getOutTimeCharTo() {
		return outTimeCharTo;
	}

	public void setOutTimeCharTo(String outTimeCharTo) {
		this.outTimeCharTo = outTimeCharTo;
	}

	public String getOutTimeColumn() {
		return outTimeColumn;
	}

	public void setOutTimeColumn(String outTimeColumn) {
		this.outTimeColumn = outTimeColumn;
	}

	public String getOutTimeColumnNoCsv() {
		return outTimeColumnNoCsv;
	}

	public void setOutTimeColumnNoCsv(String outTimeColumnNoCsv) {
		this.outTimeColumnNoCsv = outTimeColumnNoCsv;
	}

	public boolean isOneTimeFlag() {
		return oneTimeFlag;
	}

	public void setOneTimeFlag(boolean oneTimeFlag) {
		this.oneTimeFlag = oneTimeFlag;
	}

	public boolean isTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(boolean timeFlag) {
		this.timeFlag = timeFlag;
	}

	public String getTimeCharFrom() {
		return timeCharFrom;
	}

	public void setTimeCharFrom(String timeCharFrom) {
		this.timeCharFrom = timeCharFrom;
	}

	public String getTimeCharTo() {
		return timeCharTo;
	}

	public void setTimeCharTo(String timeCharTo) {
		this.timeCharTo = timeCharTo;
	}

	public String getTimeColumn() {
		return timeColumn;
	}

	public void setTimeColumn(String timeColumn) {
		this.timeColumn = timeColumn;
	}

	public String getTimeColumnNoCsv() {
		return timeColumnNoCsv;
	}

	public void setTimeColumnNoCsv(String timeColumnNoCsv) {
		this.timeColumnNoCsv = timeColumnNoCsv;
	}

	public boolean isCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getCheckCharFrom() {
		return checkCharFrom;
	}

	public void setCheckCharFrom(String checkCharFrom) {
		this.checkCharFrom = checkCharFrom;
	}

	public String getCheckCharTo() {
		return checkCharTo;
	}

	public void setCheckCharTo(String checkCharTo) {
		this.checkCharTo = checkCharTo;
	}

	public String getCheckColumn() {
		return checkColumn;
	}

	public void setCheckColumn(String checkColumn) {
		this.checkColumn = checkColumn;
	}

	public String getCheckColumnNoCsv() {
		return checkColumnNoCsv;
	}

	public void setCheckColumnNoCsv(String checkColumnNoCsv) {
		this.checkColumnNoCsv = checkColumnNoCsv;
	}

	public boolean isWorkHrsFlag() {
		return workHrsFlag;
	}

	public void setWorkHrsFlag(boolean workHrsFlag) {
		this.workHrsFlag = workHrsFlag;
	}

	public String getWorkHrsCharFrom() {
		return workHrsCharFrom;
	}

	public void setWorkHrsCharFrom(String workHrsCharFrom) {
		this.workHrsCharFrom = workHrsCharFrom;
	}

	public String getWorkHrsCharTo() {
		return workHrsCharTo;
	}

	public void setWorkHrsCharTo(String workHrsCharTo) {
		this.workHrsCharTo = workHrsCharTo;
	}

	public String getWorkHrsColumn() {
		return workHrsColumn;
	}

	public void setWorkHrsColumn(String workHrsColumn) {
		this.workHrsColumn = workHrsColumn;
	}

	public String getWorkHrsColumnNoCsv() {
		return workHrsColumnNoCsv;
	}

	public void setWorkHrsColumnNoCsv(String workHrsColumnNoCsv) {
		this.workHrsColumnNoCsv = workHrsColumnNoCsv;
	}

	public boolean isShiftFlag() {
		return shiftFlag;
	}

	public void setShiftFlag(boolean shiftFlag) {
		this.shiftFlag = shiftFlag;
	}

	public String getShiftCharFrom() {
		return shiftCharFrom;
	}

	public void setShiftCharFrom(String shiftCharFrom) {
		this.shiftCharFrom = shiftCharFrom;
	}

	public String getShiftCharTo() {
		return shiftCharTo;
	}

	public void setShiftCharTo(String shiftCharTo) {
		this.shiftCharTo = shiftCharTo;
	}

	public String getShiftColumn() {
		return shiftColumn;
	}

	public void setShiftColumn(String shiftColumn) {
		this.shiftColumn = shiftColumn;
	}

	public String getShiftColumnNoCsv() {
		return shiftColumnNoCsv;
	}

	public void setShiftColumnNoCsv(String shiftColumnNoCsv) {
		this.shiftColumnNoCsv = shiftColumnNoCsv;
	}

	public String getTimeFlagType() {
		return timeFlagType;
	}

	public void setTimeFlagType(String timeFlagType) {
		this.timeFlagType = timeFlagType;
	}

	public String getLeaveCodeHid() {
		return leaveCodeHid;
	}

	public void setLeaveCodeHid(String leaveCodeHid) {
		this.leaveCodeHid = leaveCodeHid;
	}

	public String getLeaveAbbrHid() {
		return leaveAbbrHid;
	}

	public void setLeaveAbbrHid(String leaveAbbrHid) {
		this.leaveAbbrHid = leaveAbbrHid;
	}

	public String getLeaveCodeHidNext() {
		return leaveCodeHidNext;
	}

	public void setLeaveCodeHidNext(String leaveCodeHidNext) {
		this.leaveCodeHidNext = leaveCodeHidNext;
	}

	public String getLeaveAbbrHidNext() {
		return leaveAbbrHidNext;
	}

	public void setLeaveAbbrHidNext(String leaveAbbrHidNext) {
		this.leaveAbbrHidNext = leaveAbbrHidNext;
	}

	public String getDivID() {
		return divID;
	}

	public void setDivID(String divID) {
		this.divID = divID;
	}

	public String getAID() {
		return aID;
	}

	public void setAID(String aid) {
		aID = aid;
	}

	public boolean isMonthlyAttendanceFlag() {
		return monthlyAttendanceFlag;
	}

	public void setMonthlyAttendanceFlag(boolean monthlyAttendanceFlag) {
		this.monthlyAttendanceFlag = monthlyAttendanceFlag;
	}

	public String getMonthlyPaidleaveCode() {
		return monthlyPaidleaveCode;
	}

	public void setMonthlyPaidleaveCode(String monthlyPaidleaveCode) {
		this.monthlyPaidleaveCode = monthlyPaidleaveCode;
	}

	public String getMonthlyPaidleave() {
		return monthlyPaidleave;
	}

	public void setMonthlyPaidleave(String monthlyPaidleave) {
		this.monthlyPaidleave = monthlyPaidleave;
	}

	public String getCompoffdays() {
		return compoffdays;
	}

	public void setCompoffdays(String compoffdays) {
		this.compoffdays = compoffdays;
	}

	public String getEmpJoinBefore() {
		return empJoinBefore;
	}

	public void setEmpJoinBefore(String empJoinBefore) {
		this.empJoinBefore = empJoinBefore;
	}

	
	public boolean isLoginAttendanceFlag() {
		return loginAttendanceFlag;
	}

	
	public void setLoginAttendanceFlag(boolean loginAttendanceFlag) {
		this.loginAttendanceFlag = loginAttendanceFlag;
	}

	public String getDefaultDay() {
		return defaultDay;
	}

	public void setDefaultDay(String defaultDay) {
		this.defaultDay = defaultDay;
	}

	public String getDefaultDayHidden() {
		return defaultDayHidden;
	}

	public void setDefaultDayHidden(String defaultDayHidden) {
		this.defaultDayHidden = defaultDayHidden;
	}
	
public String getAlertLeave() {
		return alertLeave;
	}

	public void setAlertLeave(String alertLeave) {
		this.alertLeave = alertLeave;
	}

	public String getLateRegularizationCheckBox() {
		return lateRegularizationCheckBox;
	}

	public void setLateRegularizationCheckBox(String lateRegularizationCheckBox) {
		this.lateRegularizationCheckBox = lateRegularizationCheckBox;
	}

	public String getHalfDayRegularizationCheckBox() {
		return halfDayRegularizationCheckBox;
	}

	public void setHalfDayRegularizationCheckBox(
			String halfDayRegularizationCheckBox) {
		this.halfDayRegularizationCheckBox = halfDayRegularizationCheckBox;
	}

	public String getAbsentRegularizationCheckBox() {
		return absentRegularizationCheckBox;
	}

	public void setAbsentRegularizationCheckBox(String absentRegularizationCheckBox) {
		this.absentRegularizationCheckBox = absentRegularizationCheckBox;
	}

	public String getRegularTimeRegularizationCheckBox() {
		return regularTimeRegularizationCheckBox;
	}

	public void setRegularTimeRegularizationCheckBox(
			String regularTimeRegularizationCheckBox) {
		this.regularTimeRegularizationCheckBox = regularTimeRegularizationCheckBox;
	}

	public String getOutdoorBlockAfterDays() {
		return outdoorBlockAfterDays;
	}

	public void setOutdoorBlockAfterDays(String outdoorBlockAfterDays) {
		this.outdoorBlockAfterDays = outdoorBlockAfterDays;
	}
}