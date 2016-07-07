/**
 * 
 */
package org.paradyne.bean.payroll;

import java.io.Serializable;

import org.paradyne.lib.BeanBase;

/**
 * @author Venkatesh
 *
 */
public class SalarySlip extends BeanBase implements Serializable {

	private String salaryEmpCode		= "";
	private String salaryEmpName		= "";
	private String salaryEmpToken		= "";
	private String salaryYear			= "";
	private String salaryMonth			= "";
	private String salLedgerCode="";
	private String checkFlag="N";
	
	public String getSalLedgerCode() {
		return salLedgerCode;
	}
	public void setSalLedgerCode(String salLedgerCode) {
		this.salLedgerCode = salLedgerCode;
	}
	public String getSalaryEmpCode() {
		return salaryEmpCode;
	}
	public void setSalaryEmpCode(String salaryEmpCode) {
		this.salaryEmpCode = salaryEmpCode;
	}
	public String getSalaryEmpName() {
		return salaryEmpName;
	}
	public void setSalaryEmpName(String salaryEmpName) {
		this.salaryEmpName = salaryEmpName;
	}
	public String getSalaryEmpToken() {
		return salaryEmpToken;
	}
	public void setSalaryEmpToken(String salaryEmpToken) {
		this.salaryEmpToken = salaryEmpToken;
	}
	public String getSalaryMonth() {
		return salaryMonth;
	}
	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}
	public String getSalaryYear() {
		return salaryYear;
	}
	public void setSalaryYear(String salaryYear) {
		this.salaryYear = salaryYear;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	
	
	
}
