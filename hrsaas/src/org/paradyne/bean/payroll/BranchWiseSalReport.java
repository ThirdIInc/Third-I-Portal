package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

public class BranchWiseSalReport extends BeanBase {
	
	private String month="";
	private String year="";
	private String divCode="";
	private String divName="";
	private String rowNumber;
	private String columnNumber;
	private String report="";
	private String divAdd="";
	private String divCity="";
	private String onHold="";
	private String ledgerCode="";
	private String arrLedgerCode="";
	private String consolidateCheck="";
	
	
	public String getLedgerCode() {
		return ledgerCode;
	}
	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}
	public String getOnHold() {
		return onHold;
	}
	public void setOnHold(String onHold) {
		this.onHold = onHold;
	}
	public String getDivCity() {
		return divCity;
	}
	public void setDivCity(String divCity) {
		this.divCity = divCity;
	}
	public String getDivAdd() {
		return divAdd;
	}
	public void setDivAdd(String divAdd) {
		this.divAdd = divAdd;
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
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getArrLedgerCode() {
		return arrLedgerCode;
	}
	public void setArrLedgerCode(String arrLedgerCode) {
		this.arrLedgerCode = arrLedgerCode;
	}
	public String getConsolidateCheck() {
		return consolidateCheck;
	}
	public void setConsolidateCheck(String consolidateCheck) {
		this.consolidateCheck = consolidateCheck;
	}
	
	

}
