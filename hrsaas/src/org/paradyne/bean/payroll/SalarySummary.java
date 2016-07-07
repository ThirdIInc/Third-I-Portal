package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

public class SalarySummary extends BeanBase {
	private Object[][] pfDataObj=null;
	private String typeCode="";
	private String typeName="";
	private String month="";
	private String year="";

	
	//Added on Date 25.03.2008 
	private String divCode="";
	private String divName="";
	private String brnCode="";
	private String brnName="";
	private String deptCode="";
	private String deptName="";
	private String onHold="";
	private String reportType="";
	private String desgCode="";
	private String desgName="";
	private String paybillName="";
	private String paybillId="";
	private String checkFlag="N";
	
	private String reportOption="";
	
	private String chkBrnDept="N";
	private String summaryFor;
	
	
	private String branchFlag="";
	private String deptFlag="";
	
	public String getBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}
	public String getDeptFlag() {
		return deptFlag;
	}
	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}
	
	public String getReportOption() {
		return reportOption;
	}
	public void setReportOption(String reportOption) {
		this.reportOption = reportOption;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
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
	public String getBrnCode() {
		return brnCode;
	}
	public void setBrnCode(String brnCode) {
		this.brnCode = brnCode;
	}
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
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
	
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	public String getChkBrnDept() {
		return chkBrnDept;
	}
	public void setChkBrnDept(String chkBrnDept) {
		this.chkBrnDept = chkBrnDept;
	}
	public Object[][] getPfDataObj() {
		return pfDataObj;
	}
	public void setPfDataObj(Object[][] pfDataObj) {
		this.pfDataObj = pfDataObj;
	}
	public String getDesgCode() {
		return desgCode;
	}
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getSummaryFor() {
		return summaryFor;
	}
	public void setSummaryFor(String summaryFor) {
		this.summaryFor = summaryFor;
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