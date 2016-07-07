package org.paradyne.bean.payroll;
/*
 * auhtor:Pradeep Ku Sahoo
 */

import org.paradyne.lib.BeanBase;

public class RecoveryReport extends BeanBase  {
	
	String month;
	String year;
	String payId;
	String payName;
	String debId;
	String debName;
	public RecoveryReport() { }
	public RecoveryReport(String month, String year, String payId, String payName,String debId,String debName) {
		super();
		this.month = month;
		this.year = year;
		this.payId = payId;
		this.payName = payName;
		this.debId=debId;
		this.debName=debName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDebId() {
		return debId;
	}
	public void setDebId(String debId) {
		this.debId = debId;
	}
	public String getDebName() {
		return debName;
	}
	public void setDebName(String debName) {
		this.debName = debName;
	}
	

}
