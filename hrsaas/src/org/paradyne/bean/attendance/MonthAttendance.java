package org.paradyne.bean.attendance;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author Bhushan
 * @date Jun 23, 2008
**/

public class MonthAttendance extends BeanBase
{
	private String attdnCode;
	private String month;
	private String monthName;
	private String year;
	private String empCode;
	private String empToken;
	private String empName;
	private String newECode;
	private String newEToken;
	private String newEName;
	private String newJoinDate;
	private String payBillNo = "";
	private String payBillName = "";
	private String typeCode = "";
	private String typeName = "";
	private String brnCode = "";
	private String brnName = "";
	private String deptCode = "";
	private String deptName = "";
	private String divCode = "";
	private String divName = "";
	private boolean flag = false;
	private ArrayList<Object> attdnList;
	private String eSave;
	private String eId;
	private String eToken;
	private String eName;
	private String eJoinDate;
	private String attdnDays;
	private String weeklyOffs;
	private String holidays;
	private String paidLevs;
	private String unPaidLevs;
	private String salDays;
	private String presentDays;
	private String lateMarks;
	private String halfDays;
	private boolean brnFlag = false;
	private boolean deptFlag = false;
	private boolean payBillFlag = false;
	private boolean typeFlag = false;
	private boolean divFlag = false;
	private boolean attConnFlag = false;
	private String status;
	private boolean statusFlag = false;
	private boolean lockFlag = false;
	private boolean savedFlag = false;
	private boolean searchFlag = false;
	private boolean brnHDayFlag = false;
	private String hiddenEmpid;
	private String rowid;
	private String hdPage;
	private String fromTotRec;
	private String toTotRec;
	private Object[][] eIds = null;
	private boolean empSearch = false;
	private double paidLM;
	private double paidHD;
	private double unpaidLM;
	private double unpaidHD;
	private boolean levConnFlag = false;
	private boolean unlockFlag = false;
	private boolean reCal = false;
	private boolean process = false;
	private String totAbs;
	private String fromDate;
	private String toDate;
	private boolean firstSave = false;
	private boolean dailyConFlag = false;
	private String recoveryFlag = "false";
	private String recoveryDays ;
	
	public String getAttdnCode() {
		return attdnCode;
	}
	public void setAttdnCode(String attdnCode) {
		this.attdnCode = attdnCode;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getNewECode() {
		return newECode;
	}
	public void setNewECode(String newECode) {
		this.newECode = newECode;
	}
	public String getNewEToken() {
		return newEToken;
	}
	public void setNewEToken(String newEToken) {
		this.newEToken = newEToken;
	}
	public String getNewEName() {
		return newEName;
	}
	public void setNewEName(String newEName) {
		this.newEName = newEName;
	}
	public String getNewJoinDate() {
		return newJoinDate;
	}
	public void setNewJoinDate(String newJoinDate) {
		this.newJoinDate = newJoinDate;
	}
	public String getPayBillNo() {
		return payBillNo;
	}
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}
	public String getPayBillName() {
		return payBillName;
	}
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getBrnCode() {
		return brnCode;
	}
	public void setBrnCode(String brnCode) {
		this.brnCode = brnCode;
	}
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public ArrayList<Object> getAttdnList() {
		return attdnList;
	}
	public void setAttdnList(ArrayList<Object> attdnList) {
		this.attdnList = attdnList;
	}
	public String getESave() {
		return eSave;
	}
	public void setESave(String save) {
		eSave = save;
	}
	public String getEId() {
		return eId;
	}
	public void setEId(String id) {
		eId = id;
	}
	public String getEToken() {
		return eToken;
	}
	public void setEToken(String token) {
		eToken = token;
	}
	public String getEName() {
		return eName;
	}
	public void setEName(String name) {
		eName = name;
	}
	public String getEJoinDate() {
		return eJoinDate;
	}
	public void setEJoinDate(String joinDate) {
		eJoinDate = joinDate;
	}
	public String getAttdnDays() {
		return attdnDays;
	}
	public void setAttdnDays(String attdnDays) {
		this.attdnDays = attdnDays;
	}
	public String getWeeklyOffs() {
		return weeklyOffs;
	}
	public void setWeeklyOffs(String weeklyOffs) {
		this.weeklyOffs = weeklyOffs;
	}
	public String getHolidays() {
		return holidays;
	}
	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}
	public String getPaidLevs() {
		return paidLevs;
	}
	public void setPaidLevs(String paidLevs) {
		this.paidLevs = paidLevs;
	}
	public String getUnPaidLevs() {
		return unPaidLevs;
	}
	public void setUnPaidLevs(String unPaidLevs) {
		this.unPaidLevs = unPaidLevs;
	}
	public String getSalDays() {
		return salDays;
	}
	public void setSalDays(String salDays) {
		this.salDays = salDays;
	}
	public String getPresentDays() {
		return presentDays;
	}
	public void setPresentDays(String presentDays) {
		this.presentDays = presentDays;
	}
	public String getLateMarks() {
		return lateMarks;
	}
	public void setLateMarks(String lateMarks) {
		this.lateMarks = lateMarks;
	}
	public String getHalfDays() {
		return halfDays;
	}
	public void setHalfDays(String halfDays) {
		this.halfDays = halfDays;
	}
	public boolean isBrnFlag() {
		return brnFlag;
	}
	public void setBrnFlag(boolean brnFlag) {
		this.brnFlag = brnFlag;
	}
	public boolean isDeptFlag() {
		return deptFlag;
	}
	public void setDeptFlag(boolean deptFlag) {
		this.deptFlag = deptFlag;
	}
	public boolean isPayBillFlag() {
		return payBillFlag;
	}
	public void setPayBillFlag(boolean payBillFlag) {
		this.payBillFlag = payBillFlag;
	}
	public boolean isTypeFlag() {
		return typeFlag;
	}
	public void setTypeFlag(boolean typeFlag) {
		this.typeFlag = typeFlag;
	}
	public boolean isDivFlag() {
		return divFlag;
	}
	public void setDivFlag(boolean divFlag) {
		this.divFlag = divFlag;
	}
	public boolean isAttConnFlag() {
		return attConnFlag;
	}
	public void setAttConnFlag(boolean attConnFlag) {
		this.attConnFlag = attConnFlag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(boolean statusFlag) {
		this.statusFlag = statusFlag;
	}
	public boolean isLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(boolean lockFlag) {
		this.lockFlag = lockFlag;
	}
	public boolean isSavedFlag() {
		return savedFlag;
	}
	public void setSavedFlag(boolean savedFlag) {
		this.savedFlag = savedFlag;
	}
	public boolean isSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(boolean searchFlag) {
		this.searchFlag = searchFlag;
	}
	public boolean isBrnHDayFlag() {
		return brnHDayFlag;
	}
	public void setBrnHDayFlag(boolean brnHDayFlag) {
		this.brnHDayFlag = brnHDayFlag;
	}
	public String getHiddenEmpid() {
		return hiddenEmpid;
	}
	public void setHiddenEmpid(String hiddenEmpid) {
		this.hiddenEmpid = hiddenEmpid;
	}
	public String getRowid() {
		return rowid;
	}
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	public String getHdPage() {
		return hdPage;
	}
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}
	public String getFromTotRec() {
		return fromTotRec;
	}
	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}
	public String getToTotRec() {
		return toTotRec;
	}
	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}
	public Object[][] getEIds() {
		return eIds;
	}
	public void setEIds(Object[][] ids) {
		eIds = ids;
	}
	public boolean isEmpSearch() {
		return empSearch;
	}
	public void setEmpSearch(boolean empSearch) {
		this.empSearch = empSearch;
	}
	public double getPaidLM() {
		return paidLM;
	}
	public void setPaidLM(double paidLM) {
		this.paidLM = paidLM;
	}
	public double getPaidHD() {
		return paidHD;
	}
	public void setPaidHD(double paidHD) {
		this.paidHD = paidHD;
	}
	public double getUnpaidLM() {
		return unpaidLM;
	}
	public void setUnpaidLM(double unpaidLM) {
		this.unpaidLM = unpaidLM;
	}
	public double getUnpaidHD() {
		return unpaidHD;
	}
	public void setUnpaidHD(double unpaidHD) {
		this.unpaidHD = unpaidHD;
	}
	public boolean isLevConnFlag() {
		return levConnFlag;
	}
	public void setLevConnFlag(boolean levConnFlag) {
		this.levConnFlag = levConnFlag;
	}
	public boolean isUnlockFlag() {
		return unlockFlag;
	}
	public void setUnlockFlag(boolean unlockFlag) {
		this.unlockFlag = unlockFlag;
	}
	public boolean isReCal() {
		return reCal;
	}
	public void setReCal(boolean reCal) {
		this.reCal = reCal;
	}
	public boolean isProcess() {
		return process;
	}
	public void setProcess(boolean process) {
		this.process = process;
	}
	public String getTotAbs() {
		return totAbs;
	}
	public void setTotAbs(String totAbs) {
		this.totAbs = totAbs;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public boolean isFirstSave() {
		return firstSave;
	}
	public void setFirstSave(boolean firstSave) {
		this.firstSave = firstSave;
	}
	public boolean isDailyConFlag() {
		return dailyConFlag;
	}
	public void setDailyConFlag(boolean dailyConFlag) {
		this.dailyConFlag = dailyConFlag;
	}
	
	public String getRecoveryFlag() {
		return recoveryFlag;
	}
	public void setRecoveryFlag(String recoveryFlag) {
		this.recoveryFlag = recoveryFlag;
	}
	public String getRecoveryDays() {
		return recoveryDays;
	}
	public void setRecoveryDays(String recoveryDays) {
		this.recoveryDays = recoveryDays;
	}	
}