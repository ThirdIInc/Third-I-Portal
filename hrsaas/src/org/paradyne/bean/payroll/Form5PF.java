/**
 * 
 */
package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

/**
 * @author reebaj
 *
 */
public class Form5PF extends BeanBase {

	/**
	 * 
	 */
	public Form5PF() {
		// TODO Auto-generated constructor stub
	}
	
	private String  month="";
	private String  year="";
	private String  division="";
	private String  divCode = "";
	private String	reportType = ""; 
	
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
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
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
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
