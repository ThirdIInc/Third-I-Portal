package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;

public class EmployeeWisePerqsReport extends BeanBase {
	
	private String fromYear="";
	private String toYear="";
	private String fromMonth = "";	
	private String toMonth = "";
	private String divName="";
	private String brnName="";
	private String deptName="";
	private String typeName="";
	private String divId="";
	private String brnId="";
	private String deptId="";
	private String typeId="";
	private String reportType="";
	private String taxableFlag = "";
	
	/**
	 * @return the taxableFlag
	 */
	public String getTaxableFlag() {
		return taxableFlag;
	}
	/**
	 * @param taxableFlag the taxableFlag to set
	 */
	public void setTaxableFlag(String taxableFlag) {
		this.taxableFlag = taxableFlag;
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
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getBrnId() {
		return brnId;
	}
	public void setBrnId(String brnId) {
		this.brnId = brnId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return the fromMonth
	 */
	public String getFromMonth() {
		return fromMonth;
	}
	/**
	 * @param fromMonth the fromMonth to set
	 */
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}
	/**
	 * @return the toMonth
	 */
	public String getToMonth() {
		return toMonth;
	}
	/**
	 * @param toMonth the toMonth to set
	 */
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	
}
