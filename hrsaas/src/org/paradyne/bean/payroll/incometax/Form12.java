package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;
/*
 * @Saipavan voleti
 * Date:14/10/2008
 */
public class Form12 extends BeanBase {
	
	private String fromYear;
	private String  toYear;
	private String  monthName;
	private String divisioncode;
	private String  divisionName;
	
	private String onholdtype;
	private String  saltype;
	private String reportType = "";
	private String reportAction = "";
	
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportAction() {
		return reportAction;
	}
	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
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
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public String getDivisioncode() {
		return divisioncode;
	}
	public void setDivisioncode(String divisioncode) {
		this.divisioncode = divisioncode;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getOnholdtype() {
		return onholdtype;
	}
	public void setOnholdtype(String onholdtype) {
		this.onholdtype = onholdtype;
	}
	public String getSaltype() {
		return saltype;
	}
	public void setSaltype(String saltype) {
		this.saltype = saltype;
	}

}
