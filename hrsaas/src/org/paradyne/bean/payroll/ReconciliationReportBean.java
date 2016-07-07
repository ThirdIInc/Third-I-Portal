/**
 * 
 */
package org.paradyne.bean.payroll;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * Bean for MonthlyLeaveRegistry application.
 * Date : 30 Nov'2011
 *
 */
public class ReconciliationReportBean extends BeanBase {
	
	private String month="";
	private String year="";
	private String payBillNo="";
	
	private String empId="";
	private String empName="";
	private String empToken="";
	private String payName="";
	
	private String typeCode="";
	private String typeName="";
	private String deptCode="";
	private String deptName="";
	private String branchCode=""; 
	private String branchName="";
	
	private String report="";
	
	
	private String divCode="";
	private String divName="";
	
	
	private String paybillId = "";
	private String paybillName = "";
	private String cadreCode = "";
	private String cadreName = "";
	private String prevMonth = "";
	private String prevYear = "";
	private String previousMonth = "";
	
	public String getPreviousMonth() {
		return previousMonth;
	}
	public void setPreviousMonth(String previousMonth) {
		this.previousMonth = previousMonth;
	}
	public String getPrevMonth() {
		return prevMonth;
	}
	public void setPrevMonth(String prevMonth) {
		this.prevMonth = prevMonth;
	}
	public String getPrevYear() {
		return prevYear;
	}
	public void setPrevYear(String prevYear) {
		this.prevYear = prevYear;
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
	public String getPayBillNo() {
		return payBillNo;
	}
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
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
	
	public String getPaybillId() {
		return paybillId;
	}
	public void setPaybillId(String paybillId) {
		this.paybillId = paybillId;
	}
	public String getPaybillName() {
		return paybillName;
	}
	public void setPaybillName(String paybillName) {
		this.paybillName = paybillName;
	}
	public String getCadreCode() {
		return cadreCode;
	}
	public void setCadreCode(String cadreCode) {
		this.cadreCode = cadreCode;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	
	
	
	
}
