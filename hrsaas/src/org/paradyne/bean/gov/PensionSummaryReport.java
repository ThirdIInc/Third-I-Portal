package org.paradyne.bean.gov;

import org.paradyne.lib.BeanBase;

public class PensionSummaryReport extends BeanBase {
public String month="";
public String year="";
public String divCode="";
public String divName="";
//public String month="";
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

}
