package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ScoreBalancer extends BeanBase {
	
	private String apprCode;
	private String startDate;
	private String endDate;
	private String apprId;
	private String templateCode;
	
	private ArrayList empList;
	private String empId;
	private String empName;
	private String deptName;
	private String appraiserName;
	private String apprScore;
	private String adjustFactor;
	private String apprFinalScore;
	private String oprand;
	
	private String myPage;
	private String show;
	private String noData = "false";
	private String finalizeStatus="";
	
	private String divName="";
	private String divCode="";
	private String branchName="";
	private String branchCode="";
	private String deptNameUp="";
	private String deptCode="";
	private String apprEmpCode="";
	private String apprEmpName="";
	private String ratingType;
	private String maxRating;
	
	
	
	
		
	public String getRatingType() {
		return ratingType;
	}
	public void setRatingType(String ratingType) {
		this.ratingType = ratingType;
	}
	public String getMaxRating() {
		return maxRating;
	}
	public void setMaxRating(String maxRating) {
		this.maxRating = maxRating;
	}
	public String getFinalizeStatus() {
		return finalizeStatus;
	}
	public void setFinalizeStatus(String finalizeStatus) {
		this.finalizeStatus = finalizeStatus;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getOprand() {
		return oprand;
	}
	public void setOprand(String oprand) {
		this.oprand = oprand;
	}
	public ArrayList getEmpList() {
		return empList;
	}
	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}

	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getApprScore() {
		return apprScore;
	}
	public void setApprScore(String apprScore) {
		this.apprScore = apprScore;
	}
	public String getApprFinalScore() {
		return apprFinalScore;
	}
	public void setApprFinalScore(String apprFinalScore) {
		this.apprFinalScore = apprFinalScore;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
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
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getAdjustFactor() {
		return adjustFactor;
	}
	public void setAdjustFactor(String adjustFactor) {
		this.adjustFactor = adjustFactor;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAppraiserName() {
		return appraiserName;
	}
	public void setAppraiserName(String appraiserName) {
		this.appraiserName = appraiserName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
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
	public String getDeptNameUp() {
		return deptNameUp;
	}
	public void setDeptNameUp(String deptNameUp) {
		this.deptNameUp = deptNameUp;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getApprEmpCode() {
		return apprEmpCode;
	}
	public void setApprEmpCode(String apprEmpCode) {
		this.apprEmpCode = apprEmpCode;
	}
	public String getApprEmpName() {
		return apprEmpName;
	}
	public void setApprEmpName(String apprEmpName) {
		this.apprEmpName = apprEmpName;
	}

}
