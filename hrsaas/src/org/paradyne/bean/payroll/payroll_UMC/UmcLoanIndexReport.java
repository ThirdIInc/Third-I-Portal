package org.paradyne.bean.payroll.payroll_UMC;

import org.paradyne.lib.BeanBase;

public class UmcLoanIndexReport extends BeanBase {
	
	private String  empToken;
	private String empname;
	private String empCode;
	private String pendingAmt;
	private String emiAmt;
	private String lastPaidMonth;
	private String lastPaidYear;
	
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getPendingAmt() {
		return pendingAmt;
	}
	public void setPendingAmt(String pendingAmt) {
		this.pendingAmt = pendingAmt;
	}
	public String getEmiAmt() {
		return emiAmt;
	}
	public void setEmiAmt(String emiAmt) {
		this.emiAmt = emiAmt;
	}
	public String getLastPaidMonth() {
		return lastPaidMonth;
	}
	public void setLastPaidMonth(String lastPaidMonth) {
		this.lastPaidMonth = lastPaidMonth;
	}
	public String getLastPaidYear() {
		return lastPaidYear;
	}
	public void setLastPaidYear(String lastPaidYear) {
		this.lastPaidYear = lastPaidYear;
	}

}
