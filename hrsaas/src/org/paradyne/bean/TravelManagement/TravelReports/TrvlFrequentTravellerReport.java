package org.paradyne.bean.TravelManagement.TravelReports;

import org.paradyne.lib.BeanBase;

public class TrvlFrequentTravellerReport extends BeanBase {
	
	private String fromDate="";
	private String toDate="";
	private String reportType="";
	
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

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

}
