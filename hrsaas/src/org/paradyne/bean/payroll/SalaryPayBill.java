package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/*
 * Author:Mangesh
 */
public class SalaryPayBill  extends BeanBase implements Serializable {
	private String month="";
	private String year="";
	private String typeName="";
	private String radioChk="";
	private String report="";
	private String onHold=""; 
	private String divCode="";
	private String branchName="";
	private String branchCode="";
	private String divName="";
	private String checkFlag="N";
	private String chkBrnOrder="N";
	private String chkDeptOrder="N";
	private String chkDesgOrder="N";
	private String paybillFor="";
	private String paybillName="";
	private String paybillId="";
	
	private String rowNumber;
	private String columnNumber;
	
	private String ledgerCode;
	private String arrLedgerCode;
	
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRadioChk() {
		return radioChk;
	}
	public void setRadioChk(String radioChk) {
		this.radioChk = radioChk;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getOnHold() {
		return onHold;
	}
	public void setOnHold(String onHold) {
		this.onHold = onHold;
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
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	
	public String getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getColumnNumber() {
		return columnNumber;
	}
	public void setColumnNumber(String columnNumber) {
		this.columnNumber = columnNumber;
	}
	public String getLedgerCode() {
		return ledgerCode;
	}
	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}
	public String getArrLedgerCode() {
		return arrLedgerCode;
	}
	public void setArrLedgerCode(String arrLedgerCode) {
		this.arrLedgerCode = arrLedgerCode;
	}
	public String getChkBrnOrder() {
		return chkBrnOrder;
	}
	public void setChkBrnOrder(String chkBrnOrder) {
		this.chkBrnOrder = chkBrnOrder;
	}
	public String getChkDeptOrder() {
		return chkDeptOrder;
	}
	public void setChkDeptOrder(String chkDeptOrder) {
		this.chkDeptOrder = chkDeptOrder;
	}
	public String getChkDesgOrder() {
		return chkDesgOrder;
	}
	public void setChkDesgOrder(String chkDesgOrder) {
		this.chkDesgOrder = chkDesgOrder;
	}
	public String getPaybillFor() {
		return paybillFor;
	}
	public void setPaybillFor(String paybillFor) {
		this.paybillFor = paybillFor;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getPaybillName() {
		return paybillName;
	}
	public void setPaybillName(String paybillName) {
		this.paybillName = paybillName;
	}
	public String getPaybillId() {
		return paybillId;
	}
	public void setPaybillId(String paybillId) {
		this.paybillId = paybillId;
	}
}
