package org.paradyne.bean.TravelManagement.TravelReports;

import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class AdvanceClaimDisbursementReportBean extends BeanBase {
	private String fromDate="";
	private String toDate="";
	private String paymentMode="";
	private String reportType="";
	
	HashMap currencyHashmap;
	private String currencyType = "";
	
	/**
	 * @return the currencyHashmap
	 */
	public HashMap getCurrencyHashmap() {
		return currencyHashmap;
	}
	/**
	 * @param currencyHashmap the currencyHashmap to set
	 */
	public void setCurrencyHashmap(HashMap currencyHashmap) {
		this.currencyHashmap = currencyHashmap;
	}
	/**
	 * @return the currencyType
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	/**
	 * @param currencyType the currencyType to set
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
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
	
}
