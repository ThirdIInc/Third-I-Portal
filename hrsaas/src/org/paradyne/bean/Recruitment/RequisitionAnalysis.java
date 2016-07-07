package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class RequisitionAnalysis extends BeanBase{
	
	
	private String divison="";
	private String branch=""; 
	private String department=""; 
	private String designation=""; 
	private String reqDate=""; 
	private String noOfPersons=""; 
	private String avgCTC=""; 
	private String totalCTC="";
	private String requisitionCode="";
	
	private String divisonCode=""; 
	private String branchCode=""; 
	private String deptCode="";
	private String designationCode="";
	private String requisitionHiddenCode="";
	
	private String selectname="";
	private String show="";
	private String hiddencode="";
	private String noData="false";
	private String myPage="";
	private ArrayList requisitionList=null;
	private String reqLength="";
	
	private String fromTotRec="";
	private String toTotRec="";
	private String hdPage="";
	private String common="";
	
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public String getDivison() {
		return divison;
	}
	public void setDivison(String divison) {
		this.divison = divison;
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
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getNoOfPersons() {
		return noOfPersons;
	}
	public void setNoOfPersons(String noOfPersons) {
		this.noOfPersons = noOfPersons;
	}
	public String getAvgCTC() {
		return avgCTC;
	}
	public void setAvgCTC(String avgCTC) {
		this.avgCTC = avgCTC;
	}
	public String getTotalCTC() {
		return totalCTC;
	}
	public void setTotalCTC(String totalCTC) {
		this.totalCTC = totalCTC;
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
	public String getRequisitionCode() {
		return requisitionCode;
	}
	public void setRequisitionCode(String requisitionCode) {
		this.requisitionCode = requisitionCode;
	}
	public String getRequisitionHiddenCode() {
		return requisitionHiddenCode;
	}
	public void setRequisitionHiddenCode(String requisitionHiddenCode) {
		this.requisitionHiddenCode = requisitionHiddenCode;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
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
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getReqLength() {
		return reqLength;
	}
	public void setReqLength(String reqLength) {
		this.reqLength = reqLength;
	}
	public ArrayList getRequisitionList() {
		return requisitionList;
	}
	public void setRequisitionList(ArrayList requisitionList) {
		this.requisitionList = requisitionList;
	}
	public String getFromTotRec() {
		return fromTotRec;
	}
	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}
	public String getToTotRec() {
		return toTotRec;
	}
	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}
	public String getHdPage() {
		return hdPage;
	}
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}
}
