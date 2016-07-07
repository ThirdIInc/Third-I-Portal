/* Bhushan Dasare June 5, 2009 */

package org.paradyne.bean.attendance;

import org.paradyne.lib.BeanBase;

public class UploadMonthlyAttendance extends BeanBase {
	private String uploadFileName = "";
	private boolean branchFlag = false;
	private boolean departmentFlag = false;
	private boolean payBillFlag = false;
	private boolean employeeTypeFlag = false;
	private boolean divisionFlag = false;
	private String recoveryFlag = "false";
	private String month;
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
	private String employeeName;
	private String empSerachId;
	
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public boolean isBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(boolean branchFlag) {
		this.branchFlag = branchFlag;
	}
	public boolean isDepartmentFlag() {
		return departmentFlag;
	}
	public void setDepartmentFlag(boolean departmentFlag) {
		this.departmentFlag = departmentFlag;
	}
	public boolean isPayBillFlag() {
		return payBillFlag;
	}
	public void setPayBillFlag(boolean payBillFlag) {
		this.payBillFlag = payBillFlag;
	}
	public boolean isEmployeeTypeFlag() {
		return employeeTypeFlag;
	}
	public void setEmployeeTypeFlag(boolean employeeTypeFlag) {
		this.employeeTypeFlag = employeeTypeFlag;
	}
	public boolean isDivisionFlag() {
		return divisionFlag;
	}
	public void setDivisionFlag(boolean divisionFlag) {
		this.divisionFlag = divisionFlag;
	}
	public String getRecoveryFlag() {
		return recoveryFlag;
	}
	public void setRecoveryFlag(String recoveryFlag) {
		this.recoveryFlag = recoveryFlag;
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
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getPayBillId() {
		return payBillId;
	}
	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}
	public String getPayBilName() {
		return payBilName;
	}
	public void setPayBilName(String payBilName) {
		this.payBilName = payBilName;
	}
	public String getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(String employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	public String getEmployeeTypeName() {
		return employeeTypeName;
	}
	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmpSerachId() {
		return empSerachId;
	}
	public void setEmpSerachId(String empSerachId) {
		this.empSerachId = empSerachId;
	}
}