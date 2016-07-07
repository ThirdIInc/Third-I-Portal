package org.paradyne.bean.kiosk;

import org.paradyne.lib.BeanBase;

public class KioskSalarySlipMaster extends BeanBase {
	private String EmpCode = "";
	private String EmpName = "";
	private String EmpToken = "";
	private String salYear = "";
	private String salMonth = "";
	private String salDivisionName;
	private String salDivisionId="";
	 
	public String getEmpCode() {
		return EmpCode;
	}
	public void setEmpCode(String empCode) {
		EmpCode = empCode;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	public String getEmpToken() {
		return EmpToken;
	}
	public void setEmpToken(String empToken) {
		EmpToken = empToken;
	}
	public String getSalYear() {
		return salYear;
	}
	public void setSalYear(String salYear) {
		this.salYear = salYear;
	}
	public String getSalMonth() {
		return salMonth;
	}
	public void setSalMonth(String salMonth) {
		this.salMonth = salMonth;
	}
	public String getSalDivisionName() {
		return salDivisionName;
	}
	public void setSalDivisionName(String salDivisionName) {
		this.salDivisionName = salDivisionName;
	}
	public String getSalDivisionId() {
		return salDivisionId;
	}
	public void setSalDivisionId(String salDivisionId) {
		this.salDivisionId = salDivisionId;
	}
	
	
}
