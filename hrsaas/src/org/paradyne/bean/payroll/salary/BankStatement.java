package org.paradyne.bean.payroll.salary;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/*
 * 
 * @author Hemant Nagam
 */
public class BankStatement extends BeanBase{
	
	private String payBill="";
	private String bank="";
	private String bankCode="";
	private String month="";
	private String year="";
	
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
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
