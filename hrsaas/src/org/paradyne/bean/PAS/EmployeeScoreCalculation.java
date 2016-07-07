package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class EmployeeScoreCalculation extends BeanBase {
	
	private String listType = "";
	private String myPage = "";
	private String pendingLength="false";
	private String appraisalId = "";
	private String appraisalCode = "";
	private String appraisalName = "";
	private String departmentId = "";
	private String departmentName = "";
	private String branchId = "";
	private String branchName = "";

	private String appraisalIdItt = "";
	private String employeeIdItt = "";
	private String employeeTokenItt = "";
	private String employeeNameItt = "";
	private String employeeScoreItt = "";
	private String moderatedScoreItt = "";
	private String reviewPanelScoreItt = "";
	private boolean searchFlag = false;
	private ArrayList pendingScoreList;
	
	public String getPendingLength() {
		return pendingLength;
	}

	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getEmployeeIdItt() {
		return employeeIdItt;
	}

	public void setEmployeeIdItt(String employeeIdItt) {
		this.employeeIdItt = employeeIdItt;
	}

	public String getEmployeeTokenItt() {
		return employeeTokenItt;
	}

	public void setEmployeeTokenItt(String employeeTokenItt) {
		this.employeeTokenItt = employeeTokenItt;
	}

	public String getEmployeeNameItt() {
		return employeeNameItt;
	}

	public void setEmployeeNameItt(String employeeNameItt) {
		this.employeeNameItt = employeeNameItt;
	}

	public String getEmployeeScoreItt() {
		return employeeScoreItt;
	}

	public void setEmployeeScoreItt(String employeeScoreItt) {
		this.employeeScoreItt = employeeScoreItt;
	}

	public String getModeratedScoreItt() {
		return moderatedScoreItt;
	}

	public void setModeratedScoreItt(String moderatedScoreItt) {
		this.moderatedScoreItt = moderatedScoreItt;
	}

	public ArrayList getPendingScoreList() {
		return pendingScoreList;
	}

	public void setPendingScoreList(ArrayList pendingScoreList) {
		this.pendingScoreList = pendingScoreList;
	}

	public String getAppraisalIdItt() {
		return appraisalIdItt;
	}

	public void setAppraisalIdItt(String appraisalIdItt) {
		this.appraisalIdItt = appraisalIdItt;
	}

	public String getAppraisalId() {
		return appraisalId;
	}

	public void setAppraisalId(String appraisalId) {
		this.appraisalId = appraisalId;
	}

	public String getAppraisalName() {
		return appraisalName;
	}

	public void setAppraisalName(String appraisalName) {
		this.appraisalName = appraisalName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAppraisalCode() {
		return appraisalCode;
	}

	public void setAppraisalCode(String appraisalCode) {
		this.appraisalCode = appraisalCode;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public boolean isSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(boolean searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getReviewPanelScoreItt() {
		return reviewPanelScoreItt;
	}

	public void setReviewPanelScoreItt(String reviewPanelScoreItt) {
		this.reviewPanelScoreItt = reviewPanelScoreItt;
	}

}
