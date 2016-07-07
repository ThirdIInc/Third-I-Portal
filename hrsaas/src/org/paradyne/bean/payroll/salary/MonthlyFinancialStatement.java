package org.paradyne.bean.payroll.salary;

import org.paradyne.lib.BeanBase;

public class MonthlyFinancialStatement extends BeanBase {
private String month="";
private String year="";
private String divisionId="";
private String divisionName="";
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
public String getDivisionId() {
	return divisionId;
}
public void setDivisionId(String divisionId) {
	this.divisionId = divisionId;
}
public String getDivisionName() {
	return divisionName;
}
public void setDivisionName(String divisionName) {
	this.divisionName = divisionName;
}
}
