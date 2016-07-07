package org.paradyne.bean.helpdesk;

import org.paradyne.lib.BeanBase;

public class HelpDeskTechMISReport extends BeanBase {
	private String fromDate;
	private String toDate;
	private String reportType  = "";
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
}
