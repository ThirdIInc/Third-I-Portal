package org.paradyne.bean.settlement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class DepartmentClearance extends BeanBase {
	
	
	private String resignAppNo = "";
	private String  empCode = "";
	private String  tokenNo = "";
	private String  empName = "";
	private String  date = "";
	private String  pendingStatus = "";
	private String listType = "";
	private ArrayList pendingList=null ;
	private String resignCode = "";
	private String checkApproveRejectStatus = "";
	private String designationName ="";
	private String employeeCode ="";
	private String empToken ="";
	private String employeeName ="";
	private String branchName ="";
	private String departmentName ="";
	private String resignDate ="";
	private String acceptDate ="";
	private String seperationDate ="";
	private String comments ="";
	private ArrayList list = null ;
	private String srNo ="";
	private String deptCode ="";
	private String isClearCheck ="";
	private String checkListName ="";
	private String chkListComments ="";
	private String chkSubmitTest = "";
	private String  status  = "";
	private String  checkListCode  = "";
	
	private String clearResignAppNo = "";
	private String clearStatus = "";
	private String clearEmpCode = "";
	private ArrayList clearList =null ;
	
	private ArrayList deptList =null ;
	private String clearListFlag="false";
	
	private String chkSubmit="";
	
	private String applicationDate="";
	
	private String department="";
	private String departmentCode="";
	private String chkSubmitFlag="";
	
	private String clearFlag="false"; 
	
	
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getChkSubmit() {
		return chkSubmit;
	}
	public void setChkSubmit(String chkSubmit) {
		this.chkSubmit = chkSubmit;
	}
	public String getCheckListCode() {
		return checkListCode;
	}
	public void setCheckListCode(String checkListCode) {
		this.checkListCode = checkListCode;
	}
	public String getChkSubmitTest() {
		return chkSubmitTest;
	}
	public void setChkSubmitTest(String chkSubmitTest) {
		this.chkSubmitTest = chkSubmitTest;
	}
	public String getIsClearCheck() {
		return isClearCheck;
	}
	public void setIsClearCheck(String isClearCheck) {
		this.isClearCheck = isClearCheck;
	}
	public String getCheckListName() {
		return checkListName;
	}
	public void setCheckListName(String checkListName) {
		this.checkListName = checkListName;
	}
	public String getChkListComments() {
		return chkListComments;
	}
	public void setChkListComments(String chkListComments) {
		this.chkListComments = chkListComments;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
 
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getResignDate() {
		return resignDate;
	}
	public void setResignDate(String resignDate) {
		this.resignDate = resignDate;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getSeperationDate() {
		return seperationDate;
	}
	public void setSeperationDate(String seperationDate) {
		this.seperationDate = seperationDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getResignCode() {
		return resignCode;
	}
	public void setResignCode(String resignCode) {
		this.resignCode = resignCode;
	}
	public String getCheckApproveRejectStatus() {
		return checkApproveRejectStatus;
	}
	public void setCheckApproveRejectStatus(String checkApproveRejectStatus) {
		this.checkApproveRejectStatus = checkApproveRejectStatus;
	}
	public ArrayList getPendingList() {
		return pendingList;
	}
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getResignAppNo() {
		return resignAppNo;
	}
	public void setResignAppNo(String resignAppNo) {
		this.resignAppNo = resignAppNo;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getTokenNo() {
		return tokenNo;
	}
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPendingStatus() {
		return pendingStatus;
	}
	public void setPendingStatus(String pendingStatus) {
		this.pendingStatus = pendingStatus;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClearResignAppNo() {
		return clearResignAppNo;
	}
	public void setClearResignAppNo(String clearResignAppNo) {
		this.clearResignAppNo = clearResignAppNo;
	}
	public String getClearStatus() {
		return clearStatus;
	}
	public void setClearStatus(String clearStatus) {
		this.clearStatus = clearStatus;
	}
	public String getClearEmpCode() {
		return clearEmpCode;
	}
	public void setClearEmpCode(String clearEmpCode) {
		this.clearEmpCode = clearEmpCode;
	}
	public ArrayList getClearList() {
		return clearList;
	}
	public void setClearList(ArrayList clearList) {
		this.clearList = clearList;
	}
	public String getClearListFlag() {
		return clearListFlag;
	}
	public void setClearListFlag(String clearListFlag) {
		this.clearListFlag = clearListFlag;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public ArrayList getDeptList() {
		return deptList;
	}
	public void setDeptList(ArrayList deptList) {
		this.deptList = deptList;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getChkSubmitFlag() {
		return chkSubmitFlag;
	}
	public void setChkSubmitFlag(String chkSubmitFlag) {
		this.chkSubmitFlag = chkSubmitFlag;
	}
	/**
	 * @return the clearFlag
	 */
	public String getClearFlag() {
		return clearFlag;
	}
	/**
	 * @param clearFlag the clearFlag to set
	 */
	public void setClearFlag(String clearFlag) {
		this.clearFlag = clearFlag;
	}
	
}
