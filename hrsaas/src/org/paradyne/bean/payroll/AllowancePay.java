package org.paradyne.bean.payroll;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AllowancePay extends BeanBase {

	private String creditCode = "";
	private String creditName = "";
	private String employeeToken = "";
	private String employeeName = "";
	private String employeeCode = "";
	private String processingDate = "";
	private String creditAmount = "";
	private String month = "";
	private String year = "";
	private String remarks = "";

	private String empToken = "";
	private String employeeId = "";
	private String empName = "";
	private String creditCodeItt = "";
	private String creditNameItt = "";
	private String  creditAmountItt = "";
	private String remarksItt = "";
	
	private String allowanceCode ="";
	
	private String hiddenEdit ="";
	
	
	private String  showFlag  ="false";
	
	private String monthItt ="";
	private String yearItt ="";
	
	private String hiddenMonthEdit ="";
	private String hiddenYearEdit ="";

	private ArrayList list = null;

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
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

	public String getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(String processingDate) {
		this.processingDate = processingDate;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCreditCodeItt() {
		return creditCodeItt;
	}

	public void setCreditCodeItt(String creditCodeItt) {
		this.creditCodeItt = creditCodeItt;
	}

	public String getCreditNameItt() {
		return creditNameItt;
	}

	public void setCreditNameItt(String creditNameItt) {
		this.creditNameItt = creditNameItt;
	}

	public String getRemarksItt() {
		return remarksItt;
	}

	public void setRemarksItt(String remarksItt) {
		this.remarksItt = remarksItt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreditAmountItt() {
		return creditAmountItt;
	}

	public void setCreditAmountItt(String creditAmountItt) {
		this.creditAmountItt = creditAmountItt;
	}

	public String getAllowanceCode() {
		return allowanceCode;
	}

	public void setAllowanceCode(String allowanceCode) {
		this.allowanceCode = allowanceCode;
	}

	public String getHiddenEdit() {
		return hiddenEdit;
	}

	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}

	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getMonthItt() {
		return monthItt;
	}

	public void setMonthItt(String monthItt) {
		this.monthItt = monthItt;
	}

	public String getYearItt() {
		return yearItt;
	}

	public void setYearItt(String yearItt) {
		this.yearItt = yearItt;
	}

	public String getHiddenMonthEdit() {
		return hiddenMonthEdit;
	}

	public void setHiddenMonthEdit(String hiddenMonthEdit) {
		this.hiddenMonthEdit = hiddenMonthEdit;
	}

	public String getHiddenYearEdit() {
		return hiddenYearEdit;
	}

	public void setHiddenYearEdit(String hiddenYearEdit) {
		this.hiddenYearEdit = hiddenYearEdit;
	}

}
