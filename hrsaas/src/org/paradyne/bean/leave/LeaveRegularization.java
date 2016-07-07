package org.paradyne.bean.leave;

import org.paradyne.lib.BeanBase;
import java.util.ArrayList;

public class LeaveRegularization extends BeanBase {
	
	private String empToken;
	private String empName;
	private String empID;
	private String empCenter;
	private String empRank;
	private String leaveFrom;
	private String leaveTo;
	private String chkFlag;
	private String date;
	private String amount;
	private String leaveCode;
	private String totalAmt="";	
	private String isFromEdit="";
	
	private String flag="";
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getIsFromEdit() {
		return isFromEdit;
	}
	public void setIsFromEdit(String isFromEdit) {
		this.isFromEdit = isFromEdit;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getChkFlag() {
		return chkFlag;
	}
	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
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
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpCenter() {
		return empCenter;
	}
	public void setEmpCenter(String empCenter) {
		this.empCenter = empCenter;
	}
	public String getEmpRank() {
		return empRank;
	}
	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}
	public String getLeaveFrom() {
		return leaveFrom;
	}
	public void setLeaveFrom(String leaveFrom) {
		this.leaveFrom = leaveFrom;
	}
	public String getLeaveTo() {
		return leaveTo;
	}
	public void setLeaveTo(String leaveTo) {
		this.leaveTo = leaveTo;
	}
	public String getLeaveCode() {
		return leaveCode;
	}
	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

}
