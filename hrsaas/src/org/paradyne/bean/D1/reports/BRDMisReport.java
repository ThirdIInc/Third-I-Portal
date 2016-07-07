package org.paradyne.bean.D1.reports;

import org.paradyne.lib.BeanBase;

/**
 * @author AA1380 Created on : 16th April 2012
 */
public class BRDMisReport extends BeanBase {
	/** reportType. * */
	private String reportType = "";
	/** ticketNumber. * */
	private String ticketNumber = "";
	/** applicationStatus. * */
	private String applicationStatus = "";
	/** fromDate. * */
	private String fromDate = "";
	/** toDate. * */
	private String toDate = "";
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return this.reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(final String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return the ticketNumber
	 */
	public String getTicketNumber() {
		return this.ticketNumber;
	}
	/**
	 * @param ticketNumber the ticketNumber to set
	 */
	public void setTicketNumber(final String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	/**
	 * @return the applicationStatus
	 */
	public String getApplicationStatus() {
		return this.applicationStatus;
	}
	/**
	 * @param applicationStatus the applicationStatus to set
	 */
	public void setApplicationStatus(final String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return this.fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(final String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return this.toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(final String toDate) {
		this.toDate = toDate;
	}


}
