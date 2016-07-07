package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;

public class InvestmentDeclarationReport extends BeanBase {
	
	private String empId;
	private String empTokenNo="";
	private String empName="";
	private String desigID="";
	private String desigName="";
	private String centerID="";
	private String centerName="";
	private String divisionId="";
	private String divisionName="";
	private String fromYear="";
	private String toYear="";
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpTokenNo() {
		return empTokenNo;
	}
	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDesigID() {
		return desigID;
	}
	public void setDesigID(String desigID) {
		this.desigID = desigID;
	}
	public String getDesigName() {
		return desigName;
	}
	public void setDesigName(String desigName) {
		this.desigName = desigName;
	}
	public String getCenterID() {
		return centerID;
	}
	public void setCenterID(String centerID) {
		this.centerID = centerID;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
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
}
