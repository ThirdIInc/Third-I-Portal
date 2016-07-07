package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

public class PTaxChallanReport extends BeanBase {
	
	private String divCode ="";
	private String divName ="";
	private String month =""; 
	private String year ="";
	private String stateCode =""; 
	private String stateName ="";
	private String divAddress="";
	private String reportType="";
	
	/**
	 * @return
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * @param stateCode
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDivAddress() {
		return divAddress;
	}
	public void setDivAddress(String divAddress) {
		this.divAddress = divAddress;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
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
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
}
