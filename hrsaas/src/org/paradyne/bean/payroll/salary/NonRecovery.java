package org.paradyne.bean.payroll.salary;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/*
 * 
 * @author Hemant Nagam
 */
public class NonRecovery extends BeanBase{
	
	private String payBill="";	
	private String month="";
	private String year="";
	private String debitCode="";
	private String debitHead="";
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getDebitHead() {
		return debitHead;
	}
	public void setDebitHead(String debitHead) {
		this.debitHead = debitHead;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getPayBill() {
		return payBill;
	}
	public void setPayBill(String payBill) {
		this.payBill = payBill;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
		
}
