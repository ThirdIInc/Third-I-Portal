package org.paradyne.bean.Loan;

import org.paradyne.lib.BeanBase;

public class LoanIndex extends BeanBase {

	private String loanAppCode ;
	private String  empToken;
	private String empname;
	private String empCode;
	private String loanType;
	private String appDate;
	private String reportType = "";
	private String trackingNumber = "";
	private String sanctionAmount = "";
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getLoanAppCode() {
		return loanAppCode;
	}
	public void setLoanAppCode(String loanAppCode) {
		this.loanAppCode = loanAppCode;
	}
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
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getSanctionAmount() {
		return sanctionAmount;
	}
	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}
	
}
