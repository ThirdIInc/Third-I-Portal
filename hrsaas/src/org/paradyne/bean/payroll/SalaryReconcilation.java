/**
 * 
 */
package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

/**
 * @author Pankaj_Jain
 *
 */
public class SalaryReconcilation extends BeanBase {

	private String monthFor;
	private String yearFor;
	private String monthTo;
	private String yearTo;
	private String divCode;
	private String divName;
	private String reportType;
	
	public String getMonthFor() {
		return monthFor;
	}
	public void setMonthFor(String monthFor) {
		this.monthFor = monthFor;
	}
	public String getYearFor() {
		return yearFor;
	}
	public void setYearFor(String yearFor) {
		this.yearFor = yearFor;
	}
	public String getMonthTo() {
		return monthTo;
	}
	public void setMonthTo(String monthTo) {
		this.monthTo = monthTo;
	}
	public String getYearTo() {
		return yearTo;
	}
	public void setYearTo(String yearTo) {
		this.yearTo = yearTo;
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
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
}
