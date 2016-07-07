/**
 * 
 */
package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

/**
 * @author reebaj
 *
 */
public class Form10PF extends BeanBase {

	/**
	 * 
	 */
	public Form10PF() {
		// TODO Auto-generated constructor stub
	}
	
	private String  month="";
	private String  year="";
	private String  division="";
	private String divCode;
	private String reportType="";
	
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
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
}
