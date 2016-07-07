package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

public class EsicForm6 extends BeanBase {
	
	private String divisionCode; 
	private String divisionName; 
	private String contributionPeriod; 
	private String year; 
	private String fromYear; 
	private String toYear;
	private String repType;
	private String reportAction = "";
	
	public String getReportAction() {
		return reportAction;
	}
	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
	}
	/**
	 * @return
	 */
	public String getDivisionCode() {
		return divisionCode;
	}
	/**
	 * @param divisionCode
	 */
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	/**
	 * @return
	 */
	public String getDivisionName() {
		return divisionName;
	}
	/**
	 * @param divisionName
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	/**
	 * @return
	 */
	public String getContributionPeriod() {
		return contributionPeriod;
	}
	/**
	 * @param contributionPeriod
	 */
	public void setContributionPeriod(String contributionPeriod) {
		this.contributionPeriod = contributionPeriod;
	}
	/**
	 * @return
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return
	 */
	public String getFromYear() {
		return fromYear;
	}
	/**
	 * @param fromYear
	 */
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	/**
	 * @return
	 */
	public String getToYear() {
		return toYear;
	}
	/**
	 * @param toYear
	 */
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getRepType() {
		return repType;
	}
	public void setRepType(String repType) {
		this.repType = repType;
	}
	
}
