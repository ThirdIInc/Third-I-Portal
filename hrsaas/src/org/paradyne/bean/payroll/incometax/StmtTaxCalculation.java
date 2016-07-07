package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;

public class StmtTaxCalculation extends BeanBase {
	private String empName="";
	private String  empId="";
	private String fromYear="";
	private String toYear="";
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
