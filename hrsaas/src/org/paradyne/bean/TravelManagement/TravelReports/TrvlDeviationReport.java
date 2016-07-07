/**
 * 
 */
package org.paradyne.bean.TravelManagement.TravelReports;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0623
 *
 */
public class TrvlDeviationReport extends BeanBase {
	private String reportType = "";
	private String fromDate="";
	private String toDate="";

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
