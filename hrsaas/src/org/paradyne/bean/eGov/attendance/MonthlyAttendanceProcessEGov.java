/**
 * @author Shashikant DOke
 * @date June 30, 2011
 */
package org.paradyne.bean.eGov.attendance;

import org.paradyne.lib.BeanBase;

public class MonthlyAttendanceProcessEGov extends BeanBase {
	private boolean branchFlag = false;
	private boolean departmentFlag = false;
	private boolean payBillFlag = false;
	private boolean employeeTypeFlag = false;
	private boolean divisionFlag = false;
	private String month="";
	private String year="";
	private String branchId="";
	private String branchName="";
	private String departmentId="";
	private String departmentName="";
	private String payBillId="";
	private String payBilName="";
	private String employeeTypeId="";
	private String employeeTypeName="";
	private String divisionId="";
	private String divisionName="";
	
	
	/**
	 * @return the branchFlag
	 */
	public boolean isBranchFlag() {
		return branchFlag;
	}
	/**
	 * @param branchFlag the branchFlag to set
	 */
	public void setBranchFlag(boolean branchFlag) {
		this.branchFlag = branchFlag;
	}
	/**
	 * @return the departmentFlag
	 */
	public boolean isDepartmentFlag() {
		return departmentFlag;
	}
	/**
	 * @param departmentFlag the departmentFlag to set
	 */
	public void setDepartmentFlag(boolean departmentFlag) {
		this.departmentFlag = departmentFlag;
	}
	/**
	 * @return the payBillFlag
	 */
	public boolean isPayBillFlag() {
		return payBillFlag;
	}
	/**
	 * @param payBillFlag the payBillFlag to set
	 */
	public void setPayBillFlag(boolean payBillFlag) {
		this.payBillFlag = payBillFlag;
	}
	/**
	 * @return the employeeTypeFlag
	 */
	public boolean isEmployeeTypeFlag() {
		return employeeTypeFlag;
	}
	/**
	 * @param employeeTypeFlag the employeeTypeFlag to set
	 */
	public void setEmployeeTypeFlag(boolean employeeTypeFlag) {
		this.employeeTypeFlag = employeeTypeFlag;
	}
	/**
	 * @return the divisionFlag
	 */
	public boolean isDivisionFlag() {
		return divisionFlag;
	}
	/**
	 * @param divisionFlag the divisionFlag to set
	 */
	public void setDivisionFlag(boolean divisionFlag) {
		this.divisionFlag = divisionFlag;
	}
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
}
