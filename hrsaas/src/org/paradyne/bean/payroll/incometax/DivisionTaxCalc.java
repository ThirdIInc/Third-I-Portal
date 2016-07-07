/**
 * 
 */
package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0554
 *
 */
public class DivisionTaxCalc extends BeanBase {
	
	private String employeeId;
	private String employeeToken;
	private String employeeName;
	private String branchCode;
	private String branchName;
	private String divCode;
	private String divName;
	private String fromYear;
	private String toYear;
	private String deptCode;
	private String deptName;
	//Added by Ganesh on 10/10/2011
	private String payBillNo = "";
	private String payBillName = "";
	private String empTypeCode = "";
	private String empTypeName = "";
	private String declaredInvestments = "";
	private String verifiedInvestments = "";
	
	
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
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	public String getEmpTypeName() {
		return empTypeName;
	}
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}
	public String getDeclaredInvestments() {
		return declaredInvestments;
	}
	public void setDeclaredInvestments(String declaredInvestments) {
		this.declaredInvestments = declaredInvestments;
	}
	public String getVerifiedInvestments() {
		return verifiedInvestments;
	}
	public void setVerifiedInvestments(String verifiedInvestments) {
		this.verifiedInvestments = verifiedInvestments;
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
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
