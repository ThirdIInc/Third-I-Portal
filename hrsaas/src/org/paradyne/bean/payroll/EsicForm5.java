package org.paradyne.bean.payroll;
import org.paradyne.lib.BeanBase;


public class EsicForm5 extends BeanBase {
	
	private String divisionAbbrevation = "";
	private String reportType;
	private String frmMonth="";
	private String frmYear="";
	private String divId="";
	private String divName="";
	private String brnId="";
	private String brnName="";
	private String deptId="";
	private String deptName="";
	private String typeId="";
	private String typeName="";
	private String toMonth="";
	private String toYear="";
	private String contributionPeriod; 
	private String year; 
	private String fromYear; 
		
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getBrnId() {
		return brnId;
	}
	public void setBrnId(String brnId) {
		this.brnId = brnId;
	}
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getToMonth() {
		return toMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getFrmMonth() {
		return frmMonth;
	}
	public void setFrmMonth(String frmMonth) {
		this.frmMonth = frmMonth;
	}
	public String getFrmYear() {
		return frmYear;
	}
	public void setFrmYear(String frmYear) {
		this.frmYear = frmYear;
	}
	public String getContributionPeriod() {
		return contributionPeriod;
	}
	public void setContributionPeriod(String contributionPeriod) {
		this.contributionPeriod = contributionPeriod;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getDivisionAbbrevation() {
		return divisionAbbrevation;
	}
	public void setDivisionAbbrevation(String divisionAbbrevation) {
		this.divisionAbbrevation = divisionAbbrevation;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
}
