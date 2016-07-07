/**
 * 
 */
package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

/**
 * @author Pankaj_Jain , Modified by Mangesh Jadhav
 *
 */
public class SalaryJV extends BeanBase {

	private String divCode="";
	private String divName="";
	private String fromMonth;
	private String toMonth;
	private String fromYear;
	private String toYear;
	private String brnFlag;
	private String deptFlag;
	private String empTypeFlag;
	private String reportType;
	private String costCenterFlag="false";
	private String employeeFlag="false";
	private String creditCode="";
	private String creditAbbr="";
	private String creditHead="";
	private String debitCode="";
	private String debitAbbr="";
	private String debitHead="";
	private String tradeFlag="false";
	private String onHold="";
	
	public String getOnHold() {
		return onHold;
	}
	public void setOnHold(String onHold) {
		this.onHold = onHold;
	}
	public String getCostCenterFlag() {
		return costCenterFlag;
	}
	public void setCostCenterFlag(String costCenterFlag) {
		this.costCenterFlag = costCenterFlag;
	}
	public String getEmployeeFlag() {
		return employeeFlag;
	}
	public void setEmployeeFlag(String employeeFlag) {
		this.employeeFlag = employeeFlag;
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
	public String getFromMonth() {
		return fromMonth;
	}
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}
	public String getToMonth() {
		return toMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
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
	public String getBrnFlag() {
		return brnFlag;
	}
	public void setBrnFlag(String brnFlag) {
		this.brnFlag = brnFlag;
	}
	public String getDeptFlag() {
		return deptFlag;
	}
	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}
	public String getEmpTypeFlag() {
		return empTypeFlag;
	}
	public void setEmpTypeFlag(String empTypeFlag) {
		this.empTypeFlag = empTypeFlag;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditAbbr() {
		return creditAbbr;
	}
	public void setCreditAbbr(String creditAbbr) {
		this.creditAbbr = creditAbbr;
	}
	public String getCreditHead() {
		return creditHead;
	}
	public void setCreditHead(String creditHead) {
		this.creditHead = creditHead;
	}
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getDebitAbbr() {
		return debitAbbr;
	}
	public void setDebitAbbr(String debitAbbr) {
		this.debitAbbr = debitAbbr;
	}
	public String getDebitHead() {
		return debitHead;
	}
	public void setDebitHead(String debitHead) {
		this.debitHead = debitHead;
	}
	public String getTradeFlag() {
		return tradeFlag;
	}
	public void setTradeFlag(String tradeFlag) {
		this.tradeFlag = tradeFlag;
	}
	
}
