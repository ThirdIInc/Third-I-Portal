/**
 * @author ganesh
 * @date 17-01-2011
 *  */
package org.paradyne.bean.Loan;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author ganesh
 * @date 17-01-2011
 * 
 */
public class LoanMISApplicationReport extends BeanBase {

	
	private String status;
	private String divName="";
	private String divId="";
	private String branchName="";
	private String branchCode="";
	private String deptName="";
	private String deptId="";
	
	private String loanAppStatus;
	private String noData;
	private ArrayList list;
	private String hiddenStatus;
	private String empCode;
	private String empToken;
	private String empName;
	private String empId;
		
	private String loanApplCode;
	private String appliedDate;
	private String level;
	private String loanAmount;
	private String loanCode;
	
	private String comment;
	private String approver;
	private String reportType = "";
	private String designationCode = "";
	private String designationName = "";
	
	private String fromDate = "";
	private String toDate = "";
	private String paybillId = "";
	private String paybillName = "";
	private String costCenterId = "";
	private String costCenterName = "";
	private String statusChkAll = "";
	private String statusChkProcessed = "";
	private String statusChkPending = "";
	private String hiddenChechfrmId = "";
	
	
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
	public String getCostCenterId() {
		return costCenterId;
	}
	public void setCostCenterId(String costCenterId) {
		this.costCenterId = costCenterId;
	}
	public String getCostCenterName() {
		return costCenterName;
	}
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	public String getStatusChkAll() {
		return statusChkAll;
	}
	public void setStatusChkAll(String statusChkAll) {
		this.statusChkAll = statusChkAll;
	}
	public String getStatusChkProcessed() {
		return statusChkProcessed;
	}
	public void setStatusChkProcessed(String statusChkProcessed) {
		this.statusChkProcessed = statusChkProcessed;
	}
	public String getStatusChkPending() {
		return statusChkPending;
	}
	public void setStatusChkPending(String statusChkPending) {
		this.statusChkPending = statusChkPending;
	}
	public String getHiddenChechfrmId() {
		return hiddenChechfrmId;
	}
	public void setHiddenChechfrmId(String hiddenChechfrmId) {
		this.hiddenChechfrmId = hiddenChechfrmId;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	public String getLoanApplCode() {
		return loanApplCode;
	}
	public void setLoanApplCode(String loanApplCode) {
		this.loanApplCode = loanApplCode;
	}
	public String getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getLoanAppStatus() {
		return loanAppStatus;
	}
	public void setLoanAppStatus(String loanAppStatus) {
		this.loanAppStatus = loanAppStatus;
	}
	
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
}
