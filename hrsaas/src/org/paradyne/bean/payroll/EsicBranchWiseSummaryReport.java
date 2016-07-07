package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

public class EsicBranchWiseSummaryReport extends BeanBase {
	
	
	private String year = "";
	private String month = "";
	private String divId = "";
	private String divName = "";
	private String divAbbr="";
	private String reportType="";

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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getDivAbbr() {
		return divAbbr;
	}
	public void setDivAbbr(String divAbbr) {
		this.divAbbr = divAbbr;
	}
}
