package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ManpowerProjection extends BeanBase{
	
	private String division="";
	private String branch=""; 
	private String department=""; 
	private String designation="";
	
	private String divisonCode=""; 
	private String branchCode=""; 
	private String deptCode="";
	private String designationCode="";
	
	private String exisistManPower="";
	private String manPowerByReq="";
	private String projectManPower="";
	private String exisistCtc="";
	private String avgCtc="";
	private String totalCtc="";
	private String requisitionHidCode="";
	private ArrayList requisitionList=null;
	
	private String myPage="";
	private String reqLength="";
	private String show="";
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
	public String getExisistManPower() {
		return exisistManPower;
	}
	public void setExisistManPower(String exisistManPower) {
		this.exisistManPower = exisistManPower;
	}
	public String getManPowerByReq() {
		return manPowerByReq;
	}
	public void setManPowerByReq(String manPowerByReq) {
		this.manPowerByReq = manPowerByReq;
	}
	public String getProjectManPower() {
		return projectManPower;
	}
	public void setProjectManPower(String projectManPower) {
		this.projectManPower = projectManPower;
	}
	public String getExisistCtc() {
		return exisistCtc;
	}
	public void setExisistCtc(String exisistCtc) {
		this.exisistCtc = exisistCtc;
	}
	public String getAvgCtc() {
		return avgCtc;
	}
	public void setAvgCtc(String avgCtc) {
		this.avgCtc = avgCtc;
	}
	public String getTotalCtc() {
		return totalCtc;
	}
	public void setTotalCtc(String totalCtc) {
		this.totalCtc = totalCtc;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
}