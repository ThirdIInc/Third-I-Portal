package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CTCProjection extends BeanBase{
	
	private String division="";
	private String branch=""; 
	private String department=""; 
	private String designation="";
	
	private String divisonCode=""; 
	private String branchCode=""; 
	private String deptCode="";
	private String designationCode="";
	
	private String totalCtc="";
	private String projectedCtc="";
	private String projectHikes="";
	
	private String requisitionHidCode="";
	private ArrayList requisitionList=null;
	
	private String myPage="";
	private String reqLength="";
	private String show="";
	private String hiddencode="";
	
	private String noData="false";
	
	public String getDivison() {
		return division;
	}
	public void setDivison(String division) {
		this.division = division;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDivisonCode() {
		return divisonCode;
	}
	public void setDivisonCode(String divisonCode) {
		this.divisonCode = divisonCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getReqLength() {
		return reqLength;
	}
	public void setReqLength(String reqLength) {
		this.reqLength = reqLength;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public ArrayList getRequisitionList() {
		return requisitionList;
	}
	public void setRequisitionList(ArrayList requisitionList) {
		this.requisitionList = requisitionList;
	}
	public String getRequisitionHidCode() {
		return requisitionHidCode;
	}
	public void setRequisitionHidCode(String requisitionHidCode) {
		this.requisitionHidCode = requisitionHidCode;
	}
	
	public String getTotalCtc() {
		return totalCtc;
	}
	public void setTotalCtc(String totalCtc) {
		this.totalCtc = totalCtc;
	}
	public String getProjectedCtc() {
		return projectedCtc;
	}
	public void setProjectedCtc(String projectedCtc) {
		this.projectedCtc = projectedCtc;
	}
	public String getProjectHikes() {
		return projectHikes;
	}
	public void setProjectHikes(String projectHikes) {
		this.projectHikes = projectHikes;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
}