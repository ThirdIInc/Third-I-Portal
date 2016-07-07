/**
 * 
 */
package org.paradyne.bean.payroll.salary;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 *
 */
public class DebitRecoveryReport extends BeanBase {
	
	private String month = "";
	private String year = "";
	private String debitCode = "";
	private String debitHead = "";
	private String onHold = "";
	private String reportType="";
	private String branchCode = "";
	private String branchName="";
	private String divId="";
	private String divName="";
	private String payBillId="";
	private String payBillName="";
	private String divisionAbbrevation="";
	
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivisionAbbrevation() {
		return divisionAbbrevation;
	}
	public void setDivisionAbbrevation(String divisionAbbrevation) {
		this.divisionAbbrevation = divisionAbbrevation;
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
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getOnHold() {
		return onHold;
	}
	public void setOnHold(String onHold) {
		this.onHold = onHold;
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
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getDebitHead() {
		return debitHead;
	}
	public void setDebitHead(String debitHead) {
		this.debitHead = debitHead;
	}
	public String getPayBillId() {
		return payBillId;
	}
	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}
	public String getPayBillName() {
		return payBillName;
	}
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}

}
