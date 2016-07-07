/* Reeba Joseph 06 OCT 2009 */

package org.paradyne.bean.admin.master;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class ShiftMaster extends BeanBase {
	//FOR SHIFT LIST ITERATOR
	private String myPage;
	private String noData = "false";
	private String modeLength = "false";
	private String totalRecords = "";
	private ArrayList tableList = null;
	private String hiddencode = "";
	private String shiftIDItt = "";
	private String shiftNameItt = "";
	private String shiftStrTimeItt = "";
	private String shiftEndTimeItt = "";
	
	//FOR LEAVE LIST
	private String leaveCodeHid = "";
	private String leaveAbbrHid = "";	
	private String leaveCodeHidNext = "";
	private String leaveAbbrHidNext = "";
	private String srNo;	
	private String leaveCode;
	private String leaveName;
	private String leaveAbbr;
	private String check;
	private ArrayList leaveDataList;
	private String leaveTypeFlag = "false";
	private String checkFlag = "false";
	
	//FOR FIELDS IN JSP DIV 1
	private String shiftID;
	private String shiftName;
	private String shiftStrTime;
	private String shiftEndTime;
	private String reportTimeLate;
	private String reportTimeHalf;
	private String lunchBreakStart;
	private String lunchBreakEnd;
	private String offLeftHalfDay;
	private String offLeftEarly;
	private String shiftWorkHrs;
	private String extraWorkHrs;
	private String shiftNtFlag;
	private String personalTime = "false";
	private String freePersonalTimeOf;
	private String freePersonalTimePer;
	private String adjPersonalTime = "false";
	private String markAbsentAfterThisTime;
	private String flexiTimeAllowed;
	private String markFlexiLateAfterThisTime;
	private String markFlexiHalfDayAfterThisTime;
	private String markFlexiAbsentBeforeThisTime;
	
	//FOR FIELDS IN JSP DIV 2
	private String selWeekDay;
	private String weekDay;
	private String dayOfWeek;
	private String dtlShiftStartTime;
	private String dtlReportTimeLate;
	private String dtlReportTimeHalf;
	private String dtlLunchBreakStart;
	private String dtlLunchBreakEnd;
	private String dtlOffLeftHalfDay;
	private String dtlOffLeftEarly;
	private String dtlShiftEndTime;
	private String dtlShiftWorkHrs;
	private String dtlExtraWorkHrs;
	private String dtlShiftNtFlag;
	private boolean shiftDtlsExists = false;
	private boolean shiftDtlsShown = false;
	
	//FOR FIELDS IN JSP DIV 3
	private String enableMonitor = "false";
	private String bLateMark = "false";
	private String adjustLMCount;
	private String adjustLMLevDays;
	private String lateCombineLeave;
	private String lateCombineLeaveCode;
	private String lmHrsIsEnabled = "false";
	private String lmReglIsEnabled = "false";
	private String lateMarksLeave;
	private String lateMarksLeaveCode;
	private String dedLmInSlabsOf;
	private String nonRegLateMarksLeave;
	private String nonRegLateMarksLeaveCode;
	private String adjustExtraWorkLm = "false";
	private String extraWorkForLM;
	private String extraWorkForLmOf;
	private String waiveOffLateMarks;
	
	//FOR FIELDS IN JSP DIV 4
	private String enableHalfMonitor = "false";
	private String enableHalfDayRegl = "false";
	private String regHalfDayLeave;
	private String regHalfDayLeaveCode;
	private String nonRegHalfDayLeave;
	private String nonRegHalfDayLeaveCode;
	private String adjustExtraWorkHd = "false";
	private String extraWorkForHD;
	private String extraWorkForHdOf;
	
	//FOR FIELDS IN JSP DIV 5
	private String variableWeekOff = "false";
	private String variWoPerMonth;
	private String fixedWeekOff = "false";	
	
	private ArrayList shiftList;
	
	private String report="";
	
	//OT Configuration Fields -- BEGINS
	private String enableOTConfigWorkflow = "";	
	private String actualHoursWorkedOT = "";	
	private String actualOutTimeOT = "";	
	private String regularOtHourlyRateFormulaOT = "";	
	private String weeklyOffHolidayOtHourlyFormulaOT = "";	
	private String doubleOtHourlyFormulaOT = "";
	//OT Configuration Fields -- ENDS

	public String getEnableOTConfigWorkflow() {
		return enableOTConfigWorkflow;
	}

	public void setEnableOTConfigWorkflow(String enableOTConfigWorkflow) {
		this.enableOTConfigWorkflow = enableOTConfigWorkflow;
	}

	public String getActualHoursWorkedOT() {
		return actualHoursWorkedOT;
	}

	public void setActualHoursWorkedOT(String actualHoursWorkedOT) {
		this.actualHoursWorkedOT = actualHoursWorkedOT;
	}

	public String getActualOutTimeOT() {
		return actualOutTimeOT;
	}

	public void setActualOutTimeOT(String actualOutTimeOT) {
		this.actualOutTimeOT = actualOutTimeOT;
	}

	public String getRegularOtHourlyRateFormulaOT() {
		return regularOtHourlyRateFormulaOT;
	}

	public void setRegularOtHourlyRateFormulaOT(String regularOtHourlyRateFormulaOT) {
		this.regularOtHourlyRateFormulaOT = regularOtHourlyRateFormulaOT;
	}

	public String getWeeklyOffHolidayOtHourlyFormulaOT() {
		return weeklyOffHolidayOtHourlyFormulaOT;
	}

	public void setWeeklyOffHolidayOtHourlyFormulaOT(
			String weeklyOffHolidayOtHourlyFormulaOT) {
		this.weeklyOffHolidayOtHourlyFormulaOT = weeklyOffHolidayOtHourlyFormulaOT;
	}

	public String getDoubleOtHourlyFormulaOT() {
		return doubleOtHourlyFormulaOT;
	}

	public void setDoubleOtHourlyFormulaOT(String doubleOtHourlyFormulaOT) {
		this.doubleOtHourlyFormulaOT = doubleOtHourlyFormulaOT;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return this.myPage;
	}

	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return the noData
	 */
	public String getNoData() {
		return this.noData;
	}

	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}

	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return this.modeLength;
	}

	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return this.totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the tableList
	 */
	public ArrayList getTableList() {
		return this.tableList;
	}

	/**
	 * @param tableList the tableList to set
	 */
	public void setTableList(ArrayList tableList) {
		this.tableList = tableList;
	}

	/**
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return this.hiddencode;
	}

	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	/**
	 * @return the shiftIDItt
	 */
	public String getShiftIDItt() {
		return this.shiftIDItt;
	}

	/**
	 * @param shiftIDItt the shiftIDItt to set
	 */
	public void setShiftIDItt(String shiftIDItt) {
		this.shiftIDItt = shiftIDItt;
	}

	/**
	 * @return the shiftNameItt
	 */
	public String getShiftNameItt() {
		return this.shiftNameItt;
	}

	/**
	 * @param shiftNameItt the shiftNameItt to set
	 */
	public void setShiftNameItt(String shiftNameItt) {
		this.shiftNameItt = shiftNameItt;
	}

	/**
	 * @return the shiftStrTimeItt
	 */
	public String getShiftStrTimeItt() {
		return this.shiftStrTimeItt;
	}

	/**
	 * @param shiftStrTimeItt the shiftStrTimeItt to set
	 */
	public void setShiftStrTimeItt(String shiftStrTimeItt) {
		this.shiftStrTimeItt = shiftStrTimeItt;
	}

	/**
	 * @return the shiftEndTimeItt
	 */
	public String getShiftEndTimeItt() {
		return this.shiftEndTimeItt;
	}

	/**
	 * @param shiftEndTimeItt the shiftEndTimeItt to set
	 */
	public void setShiftEndTimeItt(String shiftEndTimeItt) {
		this.shiftEndTimeItt = shiftEndTimeItt;
	}

	/**
	 * @return the leaveCodeHid
	 */
	public String getLeaveCodeHid() {
		return this.leaveCodeHid;
	}

	/**
	 * @param leaveCodeHid the leaveCodeHid to set
	 */
	public void setLeaveCodeHid(String leaveCodeHid) {
		this.leaveCodeHid = leaveCodeHid;
	}

	/**
	 * @return the leaveAbbrHid
	 */
	public String getLeaveAbbrHid() {
		return this.leaveAbbrHid;
	}

	/**
	 * @param leaveAbbrHid the leaveAbbrHid to set
	 */
	public void setLeaveAbbrHid(String leaveAbbrHid) {
		this.leaveAbbrHid = leaveAbbrHid;
	}

	/**
	 * @return the leaveCodeHidNext
	 */
	public String getLeaveCodeHidNext() {
		return this.leaveCodeHidNext;
	}

	/**
	 * @param leaveCodeHidNext the leaveCodeHidNext to set
	 */
	public void setLeaveCodeHidNext(String leaveCodeHidNext) {
		this.leaveCodeHidNext = leaveCodeHidNext;
	}

	/**
	 * @return the leaveAbbrHidNext
	 */
	public String getLeaveAbbrHidNext() {
		return this.leaveAbbrHidNext;
	}

	/**
	 * @param leaveAbbrHidNext the leaveAbbrHidNext to set
	 */
	public void setLeaveAbbrHidNext(String leaveAbbrHidNext) {
		this.leaveAbbrHidNext = leaveAbbrHidNext;
	}

	/**
	 * @return the srNo
	 */
	public String getSrNo() {
		return this.srNo;
	}

	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	/**
	 * @return the leaveCode
	 */
	public String getLeaveCode() {
		return this.leaveCode;
	}

	/**
	 * @param leaveCode the leaveCode to set
	 */
	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	/**
	 * @return the leaveName
	 */
	public String getLeaveName() {
		return this.leaveName;
	}

	/**
	 * @param leaveName the leaveName to set
	 */
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	/**
	 * @return the leaveAbbr
	 */
	public String getLeaveAbbr() {
		return this.leaveAbbr;
	}

	/**
	 * @param leaveAbbr the leaveAbbr to set
	 */
	public void setLeaveAbbr(String leaveAbbr) {
		this.leaveAbbr = leaveAbbr;
	}

	/**
	 * @return the check
	 */
	public String getCheck() {
		return this.check;
	}

	/**
	 * @param check the check to set
	 */
	public void setCheck(String check) {
		this.check = check;
	}

	/**
	 * @return the leaveDataList
	 */
	public ArrayList getLeaveDataList() {
		return this.leaveDataList;
	}

	/**
	 * @param leaveDataList the leaveDataList to set
	 */
	public void setLeaveDataList(ArrayList leaveDataList) {
		this.leaveDataList = leaveDataList;
	}

	/**
	 * @return the leaveTypeFlag
	 */
	public String getLeaveTypeFlag() {
		return this.leaveTypeFlag;
	}

	/**
	 * @param leaveTypeFlag the leaveTypeFlag to set
	 */
	public void setLeaveTypeFlag(String leaveTypeFlag) {
		this.leaveTypeFlag = leaveTypeFlag;
	}

	/**
	 * @return the checkFlag
	 */
	public String getCheckFlag() {
		return this.checkFlag;
	}

	/**
	 * @param checkFlag the checkFlag to set
	 */
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	/**
	 * @return the shiftID
	 */
	public String getShiftID() {
		return this.shiftID;
	}

	/**
	 * @param shiftID the shiftID to set
	 */
	public void setShiftID(String shiftID) {
		this.shiftID = shiftID;
	}

	/**
	 * @return the shiftName
	 */
	public String getShiftName() {
		return this.shiftName;
	}

	/**
	 * @param shiftName the shiftName to set
	 */
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	/**
	 * @return the shiftStrTime
	 */
	public String getShiftStrTime() {
		return this.shiftStrTime;
	}

	/**
	 * @param shiftStrTime the shiftStrTime to set
	 */
	public void setShiftStrTime(String shiftStrTime) {
		this.shiftStrTime = shiftStrTime;
	}

	/**
	 * @return the shiftEndTime
	 */
	public String getShiftEndTime() {
		return this.shiftEndTime;
	}

	/**
	 * @param shiftEndTime the shiftEndTime to set
	 */
	public void setShiftEndTime(String shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}

	/**
	 * @return the reportTimeLate
	 */
	public String getReportTimeLate() {
		return this.reportTimeLate;
	}

	/**
	 * @param reportTimeLate the reportTimeLate to set
	 */
	public void setReportTimeLate(String reportTimeLate) {
		this.reportTimeLate = reportTimeLate;
	}

	/**
	 * @return the reportTimeHalf
	 */
	public String getReportTimeHalf() {
		return this.reportTimeHalf;
	}

	/**
	 * @param reportTimeHalf the reportTimeHalf to set
	 */
	public void setReportTimeHalf(String reportTimeHalf) {
		this.reportTimeHalf = reportTimeHalf;
	}

	/**
	 * @return the lunchBreakStart
	 */
	public String getLunchBreakStart() {
		return this.lunchBreakStart;
	}

	/**
	 * @param lunchBreakStart the lunchBreakStart to set
	 */
	public void setLunchBreakStart(String lunchBreakStart) {
		this.lunchBreakStart = lunchBreakStart;
	}

	/**
	 * @return the lunchBreakEnd
	 */
	public String getLunchBreakEnd() {
		return this.lunchBreakEnd;
	}

	/**
	 * @param lunchBreakEnd the lunchBreakEnd to set
	 */
	public void setLunchBreakEnd(String lunchBreakEnd) {
		this.lunchBreakEnd = lunchBreakEnd;
	}

	/**
	 * @return the offLeftHalfDay
	 */
	public String getOffLeftHalfDay() {
		return this.offLeftHalfDay;
	}

	/**
	 * @param offLeftHalfDay the offLeftHalfDay to set
	 */
	public void setOffLeftHalfDay(String offLeftHalfDay) {
		this.offLeftHalfDay = offLeftHalfDay;
	}

	/**
	 * @return the offLeftEarly
	 */
	public String getOffLeftEarly() {
		return this.offLeftEarly;
	}

	/**
	 * @param offLeftEarly the offLeftEarly to set
	 */
	public void setOffLeftEarly(String offLeftEarly) {
		this.offLeftEarly = offLeftEarly;
	}

	/**
	 * @return the shiftWorkHrs
	 */
	public String getShiftWorkHrs() {
		return this.shiftWorkHrs;
	}

	/**
	 * @param shiftWorkHrs the shiftWorkHrs to set
	 */
	public void setShiftWorkHrs(String shiftWorkHrs) {
		this.shiftWorkHrs = shiftWorkHrs;
	}

	/**
	 * @return the extraWorkHrs
	 */
	public String getExtraWorkHrs() {
		return this.extraWorkHrs;
	}

	/**
	 * @param extraWorkHrs the extraWorkHrs to set
	 */
	public void setExtraWorkHrs(String extraWorkHrs) {
		this.extraWorkHrs = extraWorkHrs;
	}

	/**
	 * @return the shiftNtFlag
	 */
	public String getShiftNtFlag() {
		return this.shiftNtFlag;
	}

	/**
	 * @param shiftNtFlag the shiftNtFlag to set
	 */
	public void setShiftNtFlag(String shiftNtFlag) {
		this.shiftNtFlag = shiftNtFlag;
	}

	/**
	 * @return the personalTime
	 */
	public String getPersonalTime() {
		return this.personalTime;
	}

	/**
	 * @param personalTime the personalTime to set
	 */
	public void setPersonalTime(String personalTime) {
		this.personalTime = personalTime;
	}

	/**
	 * @return the freePersonalTimeOf
	 */
	public String getFreePersonalTimeOf() {
		return this.freePersonalTimeOf;
	}

	/**
	 * @param freePersonalTimeOf the freePersonalTimeOf to set
	 */
	public void setFreePersonalTimeOf(String freePersonalTimeOf) {
		this.freePersonalTimeOf = freePersonalTimeOf;
	}

	/**
	 * @return the freePersonalTimePer
	 */
	public String getFreePersonalTimePer() {
		return this.freePersonalTimePer;
	}

	/**
	 * @param freePersonalTimePer the freePersonalTimePer to set
	 */
	public void setFreePersonalTimePer(String freePersonalTimePer) {
		this.freePersonalTimePer = freePersonalTimePer;
	}

	/**
	 * @return the adjPersonalTime
	 */
	public String getAdjPersonalTime() {
		return this.adjPersonalTime;
	}

	/**
	 * @param adjPersonalTime the adjPersonalTime to set
	 */
	public void setAdjPersonalTime(String adjPersonalTime) {
		this.adjPersonalTime = adjPersonalTime;
	}

	/**
	 * @return the weekDay
	 */
	public String getWeekDay() {
		return this.weekDay;
	}

	/**
	 * @param weekDay the weekDay to set
	 */
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	/**
	 * @return the dayOfWeek
	 */
	public String getDayOfWeek() {
		return this.dayOfWeek;
	}

	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * @return the selWeekDay
	 */
	public String getSelWeekDay() {
		return this.selWeekDay;
	}

	/**
	 * @param selWeekDay the selWeekDay to set
	 */
	public void setSelWeekDay(String selWeekDay) {
		this.selWeekDay = selWeekDay;
	}

	/**
	 * @return the dtlShiftStartTime
	 */
	public String getDtlShiftStartTime() {
		return this.dtlShiftStartTime;
	}

	/**
	 * @param dtlShiftStartTime the dtlShiftStartTime to set
	 */
	public void setDtlShiftStartTime(String dtlShiftStartTime) {
		this.dtlShiftStartTime = dtlShiftStartTime;
	}

	/**
	 * @return the dtlReportTimeLate
	 */
	public String getDtlReportTimeLate() {
		return this.dtlReportTimeLate;
	}

	/**
	 * @param dtlReportTimeLate the dtlReportTimeLate to set
	 */
	public void setDtlReportTimeLate(String dtlReportTimeLate) {
		this.dtlReportTimeLate = dtlReportTimeLate;
	}

	/**
	 * @return the dtlReportTimeHalf
	 */
	public String getDtlReportTimeHalf() {
		return this.dtlReportTimeHalf;
	}

	/**
	 * @param dtlReportTimeHalf the dtlReportTimeHalf to set
	 */
	public void setDtlReportTimeHalf(String dtlReportTimeHalf) {
		this.dtlReportTimeHalf = dtlReportTimeHalf;
	}

	/**
	 * @return the dtlLunchBreakStart
	 */
	public String getDtlLunchBreakStart() {
		return this.dtlLunchBreakStart;
	}

	/**
	 * @param dtlLunchBreakStart the dtlLunchBreakStart to set
	 */
	public void setDtlLunchBreakStart(String dtlLunchBreakStart) {
		this.dtlLunchBreakStart = dtlLunchBreakStart;
	}

	/**
	 * @return the dtlLunchBreakEnd
	 */
	public String getDtlLunchBreakEnd() {
		return this.dtlLunchBreakEnd;
	}

	/**
	 * @param dtlLunchBreakEnd the dtlLunchBreakEnd to set
	 */
	public void setDtlLunchBreakEnd(String dtlLunchBreakEnd) {
		this.dtlLunchBreakEnd = dtlLunchBreakEnd;
	}

	/**
	 * @return the dtlOffLeftHalfDay
	 */
	public String getDtlOffLeftHalfDay() {
		return this.dtlOffLeftHalfDay;
	}

	/**
	 * @param dtlOffLeftHalfDay the dtlOffLeftHalfDay to set
	 */
	public void setDtlOffLeftHalfDay(String dtlOffLeftHalfDay) {
		this.dtlOffLeftHalfDay = dtlOffLeftHalfDay;
	}

	/**
	 * @return the dtlOffLeftEarly
	 */
	public String getDtlOffLeftEarly() {
		return this.dtlOffLeftEarly;
	}

	/**
	 * @param dtlOffLeftEarly the dtlOffLeftEarly to set
	 */
	public void setDtlOffLeftEarly(String dtlOffLeftEarly) {
		this.dtlOffLeftEarly = dtlOffLeftEarly;
	}

	/**
	 * @return the dtlShiftEndTime
	 */
	public String getDtlShiftEndTime() {
		return this.dtlShiftEndTime;
	}

	/**
	 * @param dtlShiftEndTime the dtlShiftEndTime to set
	 */
	public void setDtlShiftEndTime(String dtlShiftEndTime) {
		this.dtlShiftEndTime = dtlShiftEndTime;
	}

	/**
	 * @return the dtlShiftWorkHrs
	 */
	public String getDtlShiftWorkHrs() {
		return this.dtlShiftWorkHrs;
	}

	/**
	 * @param dtlShiftWorkHrs the dtlShiftWorkHrs to set
	 */
	public void setDtlShiftWorkHrs(String dtlShiftWorkHrs) {
		this.dtlShiftWorkHrs = dtlShiftWorkHrs;
	}

	/**
	 * @return the dtlExtraWorkHrs
	 */
	public String getDtlExtraWorkHrs() {
		return this.dtlExtraWorkHrs;
	}

	/**
	 * @param dtlExtraWorkHrs the dtlExtraWorkHrs to set
	 */
	public void setDtlExtraWorkHrs(String dtlExtraWorkHrs) {
		this.dtlExtraWorkHrs = dtlExtraWorkHrs;
	}

	/**
	 * @return the dtlShiftNtFlag
	 */
	public String getDtlShiftNtFlag() {
		return this.dtlShiftNtFlag;
	}

	/**
	 * @param dtlShiftNtFlag the dtlShiftNtFlag to set
	 */
	public void setDtlShiftNtFlag(String dtlShiftNtFlag) {
		this.dtlShiftNtFlag = dtlShiftNtFlag;
	}

	/**
	 * @return the isShiftDtlsShown
	 */
	public boolean isShiftDtlsShown() {
		return this.shiftDtlsShown;
	}	

	/**
	 * @param isShiftDtlsShown the isShiftDtlsShown to set
	 */
	public void setShiftDtlsShown(boolean shiftDtlsShown) {
		this.shiftDtlsShown = shiftDtlsShown;
	}

	/**
	 * @return the shiftDtlsExists
	 */
	public boolean isShiftDtlsExists() {
		return this.shiftDtlsExists;
	}

	
	/**
	 * @param shiftDtlsExists the shiftDtlsExists to set
	 */
	public void setShiftDtlsExists(boolean shiftDtlsExists) {
		this.shiftDtlsExists = shiftDtlsExists;
	}

	
	/**
	 * @return the enableMonitor
	 */
	public String getEnableMonitor() {
		return this.enableMonitor;
	}

	/**
	 * @param enableMonitor the enableMonitor to set
	 */
	public void setEnableMonitor(String enableMonitor) {
		this.enableMonitor = enableMonitor;
	}

	/**
	 * @return the bLateMark
	 */
	public String getBLateMark() {
		return this.bLateMark;
	}

	/**
	 * @param lateMark the bLateMark to set
	 */
	public void setBLateMark(String lateMark) {
		this.bLateMark = lateMark;
	}

	/**
	 * @return the adjustLMCount
	 */
	public String getAdjustLMCount() {
		return this.adjustLMCount;
	}

	/**
	 * @param adjustLMCount the adjustLMCount to set
	 */
	public void setAdjustLMCount(String adjustLMCount) {
		this.adjustLMCount = adjustLMCount;
	}

	/**
	 * @return the adjustLMLevDays
	 */
	public String getAdjustLMLevDays() {
		return this.adjustLMLevDays;
	}

	/**
	 * @param adjustLMLevDays the adjustLMLevDays to set
	 */
	public void setAdjustLMLevDays(String adjustLMLevDays) {
		this.adjustLMLevDays = adjustLMLevDays;
	}

	/**
	 * @return the lateCombineLeave
	 */
	public String getLateCombineLeave() {
		return this.lateCombineLeave;
	}

	/**
	 * @param lateCombineLeave the lateCombineLeave to set
	 */
	public void setLateCombineLeave(String lateCombineLeave) {
		this.lateCombineLeave = lateCombineLeave;
	}

	/**
	 * @return the lateCombineLeaveCode
	 */
	public String getLateCombineLeaveCode() {
		return this.lateCombineLeaveCode;
	}

	/**
	 * @param lateCombineLeaveCode the lateCombineLeaveCode to set
	 */
	public void setLateCombineLeaveCode(String lateCombineLeaveCode) {
		this.lateCombineLeaveCode = lateCombineLeaveCode;
	}

	/**
	 * @return the lmHrsIsEnabled
	 */
	public String getLmHrsIsEnabled() {
		return this.lmHrsIsEnabled;
	}

	/**
	 * @param lmHrsIsEnabled the lmHrsIsEnabled to set
	 */
	public void setLmHrsIsEnabled(String lmHrsIsEnabled) {
		this.lmHrsIsEnabled = lmHrsIsEnabled;
	}

	/**
	 * @return the lmReglIsEnabled
	 */
	public String getLmReglIsEnabled() {
		return this.lmReglIsEnabled;
	}

	/**
	 * @param lmReglIsEnabled the lmReglIsEnabled to set
	 */
	public void setLmReglIsEnabled(String lmReglIsEnabled) {
		this.lmReglIsEnabled = lmReglIsEnabled;
	}

	/**
	 * @return the lateMarksLeave
	 */
	public String getLateMarksLeave() {
		return this.lateMarksLeave;
	}

	/**
	 * @param lateMarksLeave the lateMarksLeave to set
	 */
	public void setLateMarksLeave(String lateMarksLeave) {
		this.lateMarksLeave = lateMarksLeave;
	}

	/**
	 * @return the lateMarksLeaveCode
	 */
	public String getLateMarksLeaveCode() {
		return this.lateMarksLeaveCode;
	}

	/**
	 * @param lateMarksLeaveCode the lateMarksLeaveCode to set
	 */
	public void setLateMarksLeaveCode(String lateMarksLeaveCode) {
		this.lateMarksLeaveCode = lateMarksLeaveCode;
	}

	/**
	 * @return the dedLmInSlabsOf
	 */
	public String getDedLmInSlabsOf() {
		return this.dedLmInSlabsOf;
	}

	/**
	 * @param dedLmInSlabsOf the dedLmInSlabsOf to set
	 */
	public void setDedLmInSlabsOf(String dedLmInSlabsOf) {
		this.dedLmInSlabsOf = dedLmInSlabsOf;
	}

	/**
	 * @return the nonRegLateMarksLeave
	 */
	public String getNonRegLateMarksLeave() {
		return this.nonRegLateMarksLeave;
	}

	/**
	 * @param nonRegLateMarksLeave the nonRegLateMarksLeave to set
	 */
	public void setNonRegLateMarksLeave(String nonRegLateMarksLeave) {
		this.nonRegLateMarksLeave = nonRegLateMarksLeave;
	}

	/**
	 * @return the nonRegLateMarksLeaveCode
	 */
	public String getNonRegLateMarksLeaveCode() {
		return this.nonRegLateMarksLeaveCode;
	}

	/**
	 * @param nonRegLateMarksLeaveCode the nonRegLateMarksLeaveCode to set
	 */
	public void setNonRegLateMarksLeaveCode(String nonRegLateMarksLeaveCode) {
		this.nonRegLateMarksLeaveCode = nonRegLateMarksLeaveCode;
	}

	/**
	 * @return the adjustExtraWorkLm
	 */
	public String getAdjustExtraWorkLm() {
		return this.adjustExtraWorkLm;
	}

	/**
	 * @param adjustExtraWorkLm the adjustExtraWorkLm to set
	 */
	public void setAdjustExtraWorkLm(String adjustExtraWorkLm) {
		this.adjustExtraWorkLm = adjustExtraWorkLm;
	}

	/**
	 * @return the extraWorkForLM
	 */
	public String getExtraWorkForLM() {
		return this.extraWorkForLM;
	}

	/**
	 * @param extraWorkForLM the extraWorkForLM to set
	 */
	public void setExtraWorkForLM(String extraWorkForLM) {
		this.extraWorkForLM = extraWorkForLM;
	}

	/**
	 * @return the extraWorkForLmOf
	 */
	public String getExtraWorkForLmOf() {
		return this.extraWorkForLmOf;
	}

	/**
	 * @param extraWorkForLmOf the extraWorkForLmOf to set
	 */
	public void setExtraWorkForLmOf(String extraWorkForLmOf) {
		this.extraWorkForLmOf = extraWorkForLmOf;
	}

	/**
	 * @return the enableHalfMonitor
	 */
	public String getEnableHalfMonitor() {
		return this.enableHalfMonitor;
	}

	/**
	 * @param enableHalfMonitor the enableHalfMonitor to set
	 */
	public void setEnableHalfMonitor(String enableHalfMonitor) {
		this.enableHalfMonitor = enableHalfMonitor;
	}

	/**
	 * @return the enableHalfDayRegl
	 */
	public String getEnableHalfDayRegl() {
		return this.enableHalfDayRegl;
	}

	/**
	 * @param enableHalfDayRegl the enableHalfDayRegl to set
	 */
	public void setEnableHalfDayRegl(String enableHalfDayRegl) {
		this.enableHalfDayRegl = enableHalfDayRegl;
	}

	/**
	 * @return the regHalfDayLeave
	 */
	public String getRegHalfDayLeave() {
		return this.regHalfDayLeave;
	}

	/**
	 * @param regHalfDayLeave the regHalfDayLeave to set
	 */
	public void setRegHalfDayLeave(String regHalfDayLeave) {
		this.regHalfDayLeave = regHalfDayLeave;
	}

	/**
	 * @return the regHalfDayLeaveCode
	 */
	public String getRegHalfDayLeaveCode() {
		return this.regHalfDayLeaveCode;
	}

	/**
	 * @param regHalfDayLeaveCode the regHalfDayLeaveCode to set
	 */
	public void setRegHalfDayLeaveCode(String regHalfDayLeaveCode) {
		this.regHalfDayLeaveCode = regHalfDayLeaveCode;
	}

	/**
	 * @return the nonRegHalfDayLeave
	 */
	public String getNonRegHalfDayLeave() {
		return this.nonRegHalfDayLeave;
	}

	/**
	 * @param nonRegHalfDayLeave the nonRegHalfDayLeave to set
	 */
	public void setNonRegHalfDayLeave(String nonRegHalfDayLeave) {
		this.nonRegHalfDayLeave = nonRegHalfDayLeave;
	}

	/**
	 * @return the nonRegHalfDayLeaveCode
	 */
	public String getNonRegHalfDayLeaveCode() {
		return this.nonRegHalfDayLeaveCode;
	}

	/**
	 * @param nonRegHalfDayLeaveCode the nonRegHalfDayLeaveCode to set
	 */
	public void setNonRegHalfDayLeaveCode(String nonRegHalfDayLeaveCode) {
		this.nonRegHalfDayLeaveCode = nonRegHalfDayLeaveCode;
	}

	/**
	 * @return the adjustExtraWorkHd
	 */
	public String getAdjustExtraWorkHd() {
		return this.adjustExtraWorkHd;
	}

	/**
	 * @param adjustExtraWorkHd the adjustExtraWorkHd to set
	 */
	public void setAdjustExtraWorkHd(String adjustExtraWorkHd) {
		this.adjustExtraWorkHd = adjustExtraWorkHd;
	}

	/**
	 * @return the extraWorkForHD
	 */
	public String getExtraWorkForHD() {
		return this.extraWorkForHD;
	}

	/**
	 * @param extraWorkForHD the extraWorkForHD to set
	 */
	public void setExtraWorkForHD(String extraWorkForHD) {
		this.extraWorkForHD = extraWorkForHD;
	}

	/**
	 * @return the extraWorkForHdOf
	 */
	public String getExtraWorkForHdOf() {
		return this.extraWorkForHdOf;
	}

	/**
	 * @param extraWorkForHdOf the extraWorkForHdOf to set
	 */
	public void setExtraWorkForHdOf(String extraWorkForHdOf) {
		this.extraWorkForHdOf = extraWorkForHdOf;
	}

	/**
	 * @return the variableWeekOff
	 */
	public String getVariableWeekOff() {
		return this.variableWeekOff;
	}

	/**
	 * @param variableWeekOff the variableWeekOff to set
	 */
	public void setVariableWeekOff(String variableWeekOff) {
		this.variableWeekOff = variableWeekOff;
	}

	/**
	 * @return the variWoPerMonth
	 */
	public String getVariWoPerMonth() {
		return this.variWoPerMonth;
	}

	/**
	 * @param variWoPerMonth the variWoPerMonth to set
	 */
	public void setVariWoPerMonth(String variWoPerMonth) {
		this.variWoPerMonth = variWoPerMonth;
	}

	/**
	 * @return the fixedWeekOff
	 */
	public String getFixedWeekOff() {
		return this.fixedWeekOff;
	}

	/**
	 * @param fixedWeekOff the fixedWeekOff to set
	 */
	public void setFixedWeekOff(String fixedWeekOff) {
		this.fixedWeekOff = fixedWeekOff;
	}

	/**
	 * @return the shiftList
	 */
	public ArrayList getShiftList() {
		return this.shiftList;
	}

	/**
	 * @param shiftList the shiftList to set
	 */
	public void setShiftList(ArrayList shiftList) {
		this.shiftList = shiftList;
	}
	
	public String getMarkAbsentAfterThisTime() {
		return markAbsentAfterThisTime;
	}

	public void setMarkAbsentAfterThisTime(String markAbsentAfterThisTime) {
		this.markAbsentAfterThisTime = markAbsentAfterThisTime;
	}

	public String getFlexiTimeAllowed() {
		return flexiTimeAllowed;
	}

	public void setFlexiTimeAllowed(String flexiTimeAllowed) {
		this.flexiTimeAllowed = flexiTimeAllowed;
	}

	public String getMarkFlexiLateAfterThisTime() {
		return markFlexiLateAfterThisTime;
	}

	public void setMarkFlexiLateAfterThisTime(String markFlexiLateAfterThisTime) {
		this.markFlexiLateAfterThisTime = markFlexiLateAfterThisTime;
	}

	public String getMarkFlexiHalfDayAfterThisTime() {
		return markFlexiHalfDayAfterThisTime;
	}

	public void setMarkFlexiHalfDayAfterThisTime(
			String markFlexiHalfDayAfterThisTime) {
		this.markFlexiHalfDayAfterThisTime = markFlexiHalfDayAfterThisTime;
	}

	public String getWaiveOffLateMarks() {
		return waiveOffLateMarks;
	}

	public void setWaiveOffLateMarks(String waiveOffLateMarks) {
		this.waiveOffLateMarks = waiveOffLateMarks;
	}

	public String getMarkFlexiAbsentBeforeThisTime() {
		return markFlexiAbsentBeforeThisTime;
	}

	public void setMarkFlexiAbsentBeforeThisTime(
			String markFlexiAbsentBeforeThisTime) {
		this.markFlexiAbsentBeforeThisTime = markFlexiAbsentBeforeThisTime;
	}
}