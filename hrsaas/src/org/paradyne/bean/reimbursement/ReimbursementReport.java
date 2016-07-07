package org.paradyne.bean.reimbursement;

import org.paradyne.lib.BeanBase;

public class ReimbursementReport extends BeanBase
{
	private String division = "";
	private String divId = "";
	private String branch = "";
	private String branchId = "";
	private String dept = "";
	private String deptId = "";
	private String desg = "";
	private String desgId = "";
	private String fromDate="";
	private String toDate="";
	private String creditCode = "";
	private String creditName = "";
	private String creditType = "";
	private String reportType = "Xls";
	private String accountantID="";
	private String paybillname="";
	private String paybillid="";
	private String gradeId="";
	private String gradeName="";
	private String staticshow="";
	private String reportAction = "";
	
	public String getReportAction() {
		return reportAction;
	}
	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
	}
	public String getStaticshow() {
		return staticshow;
	}
	public void setStaticshow(String staticshow) {
		this.staticshow = staticshow;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getPaybillname() {
		return paybillname;
	}
	public void setPaybillname(String paybillname) {
		this.paybillname = paybillname;
	}
	public String getPaybillid() {
		return paybillid;
	}
	public void setPaybillid(String paybillid) {
		this.paybillid = paybillid;
	}
	public String getAccountantID() {
		return accountantID;
	}
	public void setAccountantID(String accountantID) {
		this.accountantID = accountantID;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
	public String getDesgId() {
		return desgId;
	}
	public void setDesgId(String desgId) {
		this.desgId = desgId;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public String getCreditType() {
		return creditType;
	}
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}