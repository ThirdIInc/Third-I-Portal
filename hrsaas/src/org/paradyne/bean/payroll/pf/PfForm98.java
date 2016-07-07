package org.paradyne.bean.payroll.pf;

import org.paradyne.lib.BeanBase;

/**
 * 
 * @author REEBA JOSEPH
 * 01 NOVEMBER 2010
 *
 */

public class PfForm98 extends BeanBase {

	private String empToken 	= "";
	private String empName 		= "";
	private String empCode 		= "";
	private String branchName 	= "";
	private String empStatus 	= "";
	private String divName 		= "";
	private String deptName 	= "";
	private String desgName 	= "";
	private String empPfNo	 	= "";
	private String fromMonth 	= "";
	private String fromYear 	= "";
	private String toMonth 		= "";
	private String toYear 		= "";
	
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
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getEmpPfNo() {
		return empPfNo;
	}
	public void setEmpPfNo(String empPfNo) {
		this.empPfNo = empPfNo;
	}
	public String getFromMonth() {
		return fromMonth;
	}
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToMonth() {
		return toMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	
	
}
