package org.paradyne.bean.event;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CeoMessageList extends BeanBase {
	
	private String listType ="";
	private String messageNo ="";
	private String empCode ="";
	private String pendingStatus ="";
	private String empName ="";
	private String date ="";
	private String pendingLength= "false";
	private String myPage="";
	private String totalApprovedRecords="";
	private String totalRejectedRecords="";
	private String totalPendingRecords="";
	private String tokenNo ="";
	private ArrayList pendingList =null ;
	private String employeeCode="";
	
	private String appMessageNo ="";
	private String appStatus ="";
	private String appEmpName ="";
	private String appAppDate ="";
	private String appEmpId ="";
	private String appEmpToken ="";
	private String approvedLength = "false";
	private String myPageApproved ="";
	private ArrayList appList =null;
	
	private String  rejMessageNo  ="";
	private String  rejStatus  =""; 
	private String  rejEmpName =""; 
	private String  rejAppDate  =""; 
	private String  rejEmpToken ="";
	private String  rejEmpId  ="";
	private ArrayList  rejList =null ;
	
	private String rejectedLength ="false";
	private String myPageRejected ="";
	
	private String employeeName ="";
	private String divisionName ="";
	private String employeeToken ="";
	private String deptName ="";
	private String subjectName ="";
	private String 	descName ="";
	private String showIdentity ="";
	private String hiddenMessageCode ="";
	
	private String checkAcceptRejectStatus ="";
	
	private boolean showFlag =false;
	private String myPageInProcess="";
	
	public String getMyPageInProcess() {
		return myPageInProcess;
	}
	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}
	public boolean isShowFlag() {
		return showFlag;
	}
	public void setShowFlag(boolean showFlag) {
		this.showFlag = showFlag;
	}
	public String getCheckAcceptRejectStatus() {
		return checkAcceptRejectStatus;
	}
	public void setCheckAcceptRejectStatus(String checkAcceptRejectStatus) {
		this.checkAcceptRejectStatus = checkAcceptRejectStatus;
	}
	public String getHiddenMessageCode() {
		return hiddenMessageCode;
	}
	public void setHiddenMessageCode(String hiddenMessageCode) {
		this.hiddenMessageCode = hiddenMessageCode;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getDescName() {
		return descName;
	}
	public void setDescName(String descName) {
		this.descName = descName;
	}
	public String getShowIdentity() {
		return showIdentity;
	}
	public void setShowIdentity(String showIdentity) {
		this.showIdentity = showIdentity;
	}
	public String getRejectedLength() {
		return rejectedLength;
	}
	public void setRejectedLength(String rejectedLength) {
		this.rejectedLength = rejectedLength;
	}
	public String getMyPageRejected() {
		return myPageRejected;
	}
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	public String getRejMessageNo() {
		return rejMessageNo;
	}
	public void setRejMessageNo(String rejMessageNo) {
		this.rejMessageNo = rejMessageNo;
	}
	public String getRejStatus() {
		return rejStatus;
	}
	public void setRejStatus(String rejStatus) {
		this.rejStatus = rejStatus;
	}
	public String getRejEmpName() {
		return rejEmpName;
	}
	public void setRejEmpName(String rejEmpName) {
		this.rejEmpName = rejEmpName;
	}
	public String getRejAppDate() {
		return rejAppDate;
	}
	public void setRejAppDate(String rejAppDate) {
		this.rejAppDate = rejAppDate;
	}
	public String getRejEmpToken() {
		return rejEmpToken;
	}
	public void setRejEmpToken(String rejEmpToken) {
		this.rejEmpToken = rejEmpToken;
	}
	public String getRejEmpId() {
		return rejEmpId;
	}
	public void setRejEmpId(String rejEmpId) {
		this.rejEmpId = rejEmpId;
	}
	 
	public ArrayList getRejList() { 
		return rejList;
	}
	public void setRejList(ArrayList rejList) {
		this.rejList = rejList;
	}
	public ArrayList getAppList() {
		return appList;
	}
	public void setAppList(ArrayList appList) {
		this.appList = appList;
	}
	public String getAppMessageNo() {
		return appMessageNo;
	}
	public void setAppMessageNo(String appMessageNo) {
		this.appMessageNo = appMessageNo;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getAppEmpName() {
		return appEmpName;
	}
	public void setAppEmpName(String appEmpName) {
		this.appEmpName = appEmpName;
	}
	public String getAppAppDate() {
		return appAppDate;
	}
	public void setAppAppDate(String appAppDate) {
		this.appAppDate = appAppDate;
	}
	public String getAppEmpId() {
		return appEmpId;
	}
	public void setAppEmpId(String appEmpId) {
		this.appEmpId = appEmpId;
	}
	public String getAppEmpToken() {
		return appEmpToken;
	}
	public void setAppEmpToken(String appEmpToken) {
		this.appEmpToken = appEmpToken;
	}
	public ArrayList getPendingList() {
		return pendingList;
	}
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}
	public String getTokenNo() {
		return tokenNo;
	}
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getMessageNo() {
		return messageNo;
	}
	public void setMessageNo(String messageNo) {
		this.messageNo = messageNo;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getPendingStatus() {
		return pendingStatus;
	}
	public void setPendingStatus(String pendingStatus) {
		this.pendingStatus = pendingStatus;
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
	public String getPendingLength() {
		return pendingLength;
	}
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	public String getApprovedLength() {
		return approvedLength;
	}
	public void setApprovedLength(String approvedLength) {
		this.approvedLength = approvedLength;
	}
	public String getMyPageApproved() {
		return myPageApproved;
	}
	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getTotalApprovedRecords() {
		return totalApprovedRecords;
	}
	public void setTotalApprovedRecords(String totalApprovedRecords) {
		this.totalApprovedRecords = totalApprovedRecords;
	}
	public String getTotalRejectedRecords() {
		return totalRejectedRecords;
	}
	public void setTotalRejectedRecords(String totalRejectedRecords) {
		this.totalRejectedRecords = totalRejectedRecords;
	}
	public String getTotalPendingRecords() {
		return totalPendingRecords;
	}
	public void setTotalPendingRecords(String totalPendingRecords) {
		this.totalPendingRecords = totalPendingRecords;
	}
	

}
