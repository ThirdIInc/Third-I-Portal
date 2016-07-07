package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;
/**
 * @author Dipti
 *
 */

public class FormV extends BeanBase {
	private String finmonth="";
	private String year="";
	private String division="";
	private String divCode="";
	private String branchCode="";
	private String branch="";
	private String date="";
	private String challan="";
	private String reportType ="";
	
	public String getChallan() {
		return challan;
	}
	public void setChallan(String challan) {
		this.challan = challan;
	}	
	/**
	 * @return the finmonth
	 */
	public String getFinmonth() {
		return finmonth;
	}
	/**
	 * @param finmonth the finmonth to set
	 */
	public void setFinmonth(String finmonth) {
		this.finmonth = finmonth;
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
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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
