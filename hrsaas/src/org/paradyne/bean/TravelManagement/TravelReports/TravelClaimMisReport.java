package org.paradyne.bean.TravelManagement.TravelReports;

import java.util.HashMap;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class TravelClaimMisReport extends BeanBase {
		
	private String applicantToken="";
	private String applicantName="";
	private String applicantId="";
	private String approverToken="";
	private String approverId="";
	private String approverName="";
	private String blockedStatus="";
	private String travelTypeId="";
	private String travelTypeName="";
	private String customerId="";
	private String customerName="";
	private String projectId="";
	private String projectName="";
	private String startDate=""; 
	private String endDate="";
	private String applicationStatus="";
	private String purposeName="";
	private String purposeId="";
	private String reportType="";
	private String gradeId="";
	private String gradeName=""; 
	private String branchCode="";
	private String branchName=""; 
	private String departmentCode="";
	private String departmentName=""; 
	TreeMap map;
	private String zone=""; 
	private String report="";
	private String reportAction="";
	HashMap currencyHashmap;
	private String currencyType = "";
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getReportAction() {
		return reportAction;
	}
	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getApproverToken() {
		return approverToken;
	}
	public void setApproverToken(String approverToken) {
		this.approverToken = approverToken;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public String getBlockedStatus() {
		return blockedStatus;
	}
	public void setBlockedStatus(String blockedStatus) {
		this.blockedStatus = blockedStatus;
	}
	public String getTravelTypeId() {
		return travelTypeId;
	}
	public void setTravelTypeId(String travelTypeId) {
		this.travelTypeId = travelTypeId;
	}
	public String getTravelTypeName() {
		return travelTypeName;
	}
	public void setTravelTypeName(String travelTypeName) {
		this.travelTypeName = travelTypeName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getApplicantToken() {
		return applicantToken;
	}
	public void setApplicantToken(String applicantToken) {
		this.applicantToken = applicantToken;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	public String getPurposeName() {
		return purposeName;
	}
	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}
	public String getPurposeId() {
		return purposeId;
	}
	public void setPurposeId(String purposeId) {
		this.purposeId = purposeId;
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
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public TreeMap getMap() {
		return map;
	}
	public void setMap(TreeMap map) {
		this.map = map;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public HashMap getCurrencyHashmap() {
		return currencyHashmap;
	}
	public void setCurrencyHashmap(HashMap currencyHashmap) {
		this.currencyHashmap = currencyHashmap;
	}
	
	

}
