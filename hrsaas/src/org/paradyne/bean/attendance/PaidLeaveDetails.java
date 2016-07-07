package org.paradyne.bean.attendance;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class PaidLeaveDetails extends BeanBase {

	private String leaveCode = "";
	private String leaveName = "";
	private String attCode = "";
	private String month = "";
	private String year = "";
	private String hiddenEmpid;
	private String pLevs;
	private String rowid;
	private String eName = "";
	private String flag = "true";
	private String paidLevs = "true";
	ArrayList leaveList = null;
	private String lateMarks1 = "";
	private String halfDay1 = "";
	private String monthInt = "";
	private double paidLM;
	private double paidHD;
	private double unpaidLM;
	private double unpaidHD;
	private double unpaidLMHD;
	private String originalBal = "";
	private String sysAdjustLeave = "";
	private String manualAdjustLeave = "";
	private String adjustLateMark = "";
	private String adjustHalfDays = "";
	private String balance = "";
	private String hiddenBalance = "";
	private String totalPaidLeave = "";
	private String sysAdjustUnpaid = "";
	private String manualAdjustUnpaid = "";
	private String totalUnPaidLeave = "";	
	private String showPaidFlag ="false";	
	private String unpaidSysAdjust="";
	private String totAbsLD;
	private String attConnFlagLD;
	private String fDate;
	private String tDate;
	
	
	public String getFDate() {
		return fDate;
	}

	public void setFDate(String date) {
		fDate = date;
	}

	public String getTDate() {
		return tDate;
	}

	public void setTDate(String date) {
		tDate = date;
	}

	public String getAttConnFlagLD() {
		return attConnFlagLD;
	}

	public void setAttConnFlagLD(String attConnFlagLD) {
		this.attConnFlagLD = attConnFlagLD;
	}

	public String getTotAbsLD() {
		return totAbsLD;
	}

	public void setTotAbsLD(String totAbsLD) {
		this.totAbsLD = totAbsLD;
	}

	public String getUnpaidSysAdjust() {
		return unpaidSysAdjust;
	}

	public void setUnpaidSysAdjust(String unpaidSysAdjust) {
		this.unpaidSysAdjust = unpaidSysAdjust;
	}

	public String getTotalUnPaidLeave() {
		return totalUnPaidLeave;
	}

	public void setTotalUnPaidLeave(String totalUnPaidLeave) {
		this.totalUnPaidLeave = totalUnPaidLeave;
	}

	public String getSysAdjustUnpaid() {
		return sysAdjustUnpaid;
	}

	public void setSysAdjustUnpaid(String sysAdjustUnpaid) {
		this.sysAdjustUnpaid = sysAdjustUnpaid;
	}

	public String getManualAdjustUnpaid() {
		return manualAdjustUnpaid;
	}

	public void setManualAdjustUnpaid(String manualAdjustUnpaid) {
		this.manualAdjustUnpaid = manualAdjustUnpaid;
	}

	public String getTotalPaidLeave() {
		return totalPaidLeave;
	}

	public void setTotalPaidLeave(String totalPaidLeave) {
		this.totalPaidLeave = totalPaidLeave;
	}

	public String getHiddenBalance() {
		return hiddenBalance;
	}

	public void setHiddenBalance(String hiddenBalance) {
		this.hiddenBalance = hiddenBalance;
	}

	public double getUnpaidLMHD() {
		return unpaidLMHD;
	}

	public void setUnpaidLMHD(double unpaidLMHD) {
		this.unpaidLMHD = unpaidLMHD;
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

	public String getMonthInt() {
		return monthInt;
	}

	public void setMonthInt(String monthInt) {
		this.monthInt = monthInt;
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

	public ArrayList getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(ArrayList leaveList) {
		this.leaveList = leaveList;
	}

	public String getHalfDay1() {
		return halfDay1;
	}

	public void setHalfDay1(String halfDay1) {
		this.halfDay1 = halfDay1;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAttCode() {
		return attCode;
	}

	public void setAttCode(String attCode) {
		this.attCode = attCode;
	}

	public String getEName() {
		return eName;
	}

	public void setEName(String name) {
		eName = name;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPaidLevs() {
		return paidLevs;
	}

	public void setPaidLevs(String paidLevs) {
		this.paidLevs = paidLevs;
	}

	public String getPLevs() {
		return pLevs;
	}

	public void setPLevs(String levs) {
		pLevs = levs;
	}

	public String getLateMarks1() {
		return lateMarks1;
	}

	public void setLateMarks1(String lateMarks1) {
		this.lateMarks1 = lateMarks1;
	}

	public String getOriginalBal() {
		return originalBal;
	}

	public void setOriginalBal(String originalBal) {
		this.originalBal = originalBal;
	}

	public String getSysAdjustLeave() {
		return sysAdjustLeave;
	}

	public void setSysAdjustLeave(String sysAdjustLeave) {
		this.sysAdjustLeave = sysAdjustLeave;
	}

	public String getManualAdjustLeave() {
		return manualAdjustLeave;
	}

	public void setManualAdjustLeave(String manualAdjustLeave) {
		this.manualAdjustLeave = manualAdjustLeave;
	}

	public String getAdjustLateMark() {
		return adjustLateMark;
	}

	public void setAdjustLateMark(String adjustLateMark) {
		this.adjustLateMark = adjustLateMark;
	}

	public String getAdjustHalfDays() {
		return adjustHalfDays;
	}

	public void setAdjustHalfDays(String adjustHalfDays) {
		this.adjustHalfDays = adjustHalfDays;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getShowPaidFlag() {
		return showPaidFlag;
	}

	public void setShowPaidFlag(String showPaidFlag) {
		this.showPaidFlag = showPaidFlag;
	}

}
