package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

public class Form3a extends BeanBase {
	private String empId = "";
	private String empToken = "";
	private String empName = "";
	private String fromMonth = "";
	private String fromYear = "";
	private String toMonth = "";
	private String toYear = "";
	private String rptType = "";
	private String centerName = "";
	private String rankName = "";
	private String reportType = "";
	
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getRptType() {
		return rptType;
	}
	public void setRptType(String rptType) {
		this.rptType = rptType;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return the fromMonth
	 */
	public String getFromMonth() {
		return fromMonth;
	}
	/**
	 * @param fromMonth the fromMonth to set
	 */
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}
	/**
	 * @return the toMonth
	 */
	public String getToMonth() {
		return toMonth;
	}
	/**
	 * @param toMonth the toMonth to set
	 */
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	
}
