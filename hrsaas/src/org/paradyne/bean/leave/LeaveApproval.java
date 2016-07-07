package org.paradyne.bean.leave;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LeaveApproval extends BeanBase implements Serializable {
	
	
	private String searchStatus ="";
	private String status;
	private String tokenNo="";
	private String date="";
	private String level="";
	private String empCode="";
	private String empName="";
	private String checkStatus="";
	private String app="false";
	private String pen="false";
	private String rej="false";
	private String hol="false";
	private String noData="false";
	private String apprflag="false";
	private String listLength="0";
	private String leaveAppNo="";
	private String  statusRemark="";
	private String statusNew="";
	
	private String myPage;
	private String show;
	private String selectname;
	
	private String listType = "";
	
	
	private String searchEmpCode = "";
	private String  searchEmpName = "";
	private String  searchEmpToken = "";
	
	
	//FIELDS FOR PENDING APPLICATION LIST
	private String pendingStatus= "";
	private ArrayList pendingList;
	
	//FIELDS FOR PENDING APPROVED CANCELL LIST
	private ArrayList penAppCanList;
	private String penAppCanEmpId= "";
	private String penAppCanLevAppNo= "";
	private String penAppCanStatus= "";
	private String penAppCanEmpToken= "";
	private String penAppCanEmpName= "";
	private String penAppCanAppDate= "";
	private String penAppCanLevel= "";
	
	
	//FIELDS FOR APPROVED LIST
	private String appEmpId= "";
	private String appLeaveAppNo= "";
	private String appStatus= "";
	private String appEmpToken= "";
	private String appEmpName= "";
	private String appLevel= "";
	private String applevAppDate= "";
	private ArrayList appList;
	
	//FIELDS FOR REJECTED LIST
	private ArrayList rejList;
	private String rejEmpId= "";
	private String rejLeaveAppNo= "";
	private String rejEmpToken= "";
	private String rejEmpName= "";
	private String rejAppDate= "";
	private String rejStatus= "";
	private String rejLevel= "";
	
	//FIELDS FOR PAGING
	
	private String myPageApproved= "";
	private String myPageRejected= "";
	private String myPagePending= "";
	private String myPageApprovedPending= "";
	
	private String pendingLength="false";
	private String approvedPendingLength="false";
	private String approvedLength="false";
	private String rejectedLength="false";
	
	private String myPage1="";
	
	 
	 
	
	private ArrayList list=null;
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getTokenNo() {
		return tokenNo;
	}
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getPen() {
		return pen;
	}
	public void setPen(String pen) {
		this.pen = pen;
	}
	public String getRej() {
		return rej;
	}
	public void setRej(String rej) {
		this.rej = rej;
	}
	public String getHol() {
		return hol;
	}
	public void setHol(String hol) {
		this.hol = hol;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLeaveAppNo() {
		return leaveAppNo;
	}
	public void setLeaveAppNo(String leaveAppNo) {
		this.leaveAppNo = leaveAppNo;
	}
	public String getStatusRemark() {
		return statusRemark;
	}
	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}
	public String getStatusNew() {
		return statusNew;
	}
	public void setStatusNew(String statusNew) {
		this.statusNew = statusNew;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}
	/**
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}
	/**
	 * @return the selectname
	 */
	public String getSelectname() {
		return selectname;
	}
	/**
	 * @param selectname the selectname to set
	 */
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	
	public ArrayList getPenAppCanList() {
		return penAppCanList;
	}
	public void setPenAppCanList(ArrayList penAppCanList) {
		this.penAppCanList = penAppCanList;
	}
	public String getPenAppCanEmpId() {
		return penAppCanEmpId;
	}
	public void setPenAppCanEmpId(String penAppCanEmpId) {
		this.penAppCanEmpId = penAppCanEmpId;
	}
	public String getPenAppCanLevAppNo() {
		return penAppCanLevAppNo;
	}
	public void setPenAppCanLevAppNo(String penAppCanLevAppNo) {
		this.penAppCanLevAppNo = penAppCanLevAppNo;
	}
	public String getPenAppCanStatus() {
		return penAppCanStatus;
	}
	public void setPenAppCanStatus(String penAppCanStatus) {
		this.penAppCanStatus = penAppCanStatus;
	}
	public String getPenAppCanEmpToken() {
		return penAppCanEmpToken;
	}
	public void setPenAppCanEmpToken(String penAppCanEmpToken) {
		this.penAppCanEmpToken = penAppCanEmpToken;
	}
	public String getPenAppCanEmpName() {
		return penAppCanEmpName;
	}
	public void setPenAppCanEmpName(String penAppCanEmpName) {
		this.penAppCanEmpName = penAppCanEmpName;
	}
	public String getPenAppCanAppDate() {
		return penAppCanAppDate;
	}
	public void setPenAppCanAppDate(String penAppCanAppDate) {
		this.penAppCanAppDate = penAppCanAppDate;
	}
	public String getPendingStatus() {
		return pendingStatus;
	}
	public void setPendingStatus(String pendingStatus) {
		this.pendingStatus = pendingStatus;
	}
	public ArrayList getPendingList() {
		return pendingList;
	}
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}
	public String getPenAppCanLevel() {
		return penAppCanLevel;
	}
	public void setPenAppCanLevel(String penAppCanLevel) {
		this.penAppCanLevel = penAppCanLevel;
	}
	 
	public String getAppEmpId() {
		return appEmpId;
	}
	public void setAppEmpId(String appEmpId) {
		this.appEmpId = appEmpId;
	}
	public String getAppLeaveAppNo() {
		return appLeaveAppNo;
	}
	public void setAppLeaveAppNo(String appLeaveAppNo) {
		this.appLeaveAppNo = appLeaveAppNo;
	}
	 
	public String getAppEmpToken() {
		return appEmpToken;
	}
	public void setAppEmpToken(String appEmpToken) {
		this.appEmpToken = appEmpToken;
	}
	public String getAppEmpName() {
		return appEmpName;
	}
	public void setAppEmpName(String appEmpName) {
		this.appEmpName = appEmpName;
	}
	public String getAppLevel() {
		return appLevel;
	}
	public void setAppLevel(String appLevel) {
		this.appLevel = appLevel;
	}
	public String getApplevAppDate() {
		return applevAppDate;
	}
	public void setApplevAppDate(String applevAppDate) {
		this.applevAppDate = applevAppDate;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public ArrayList getAppList() {
		return appList;
	}
	public void setAppList(ArrayList appList) {
		this.appList = appList;
	}
	public ArrayList getRejList() {
		return rejList;
	}
	public void setRejList(ArrayList rejList) {
		this.rejList = rejList;
	}
	public String getRejEmpId() {
		return rejEmpId;
	}
	public void setRejEmpId(String rejEmpId) {
		this.rejEmpId = rejEmpId;
	}
	public String getRejLeaveAppNo() {
		return rejLeaveAppNo;
	}
	public void setRejLeaveAppNo(String rejLeaveAppNo) {
		this.rejLeaveAppNo = rejLeaveAppNo;
	}
	public String getRejEmpToken() {
		return rejEmpToken;
	}
	public void setRejEmpToken(String rejEmpToken) {
		this.rejEmpToken = rejEmpToken;
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
	public String getRejStatus() {
		return rejStatus;
	}
	public void setRejStatus(String rejStatus) {
		this.rejStatus = rejStatus;
	}
	public String getRejLevel() {
		return rejLevel;
	}
	public void setRejLevel(String rejLevel) {
		this.rejLevel = rejLevel;
	}
	public String getMyPageApproved() {
		return myPageApproved;
	}
	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}
	public String getMyPageRejected() {
		return myPageRejected;
	}
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	public String getMyPagePending() {
		return myPagePending;
	}
	public void setMyPagePending(String myPagePending) {
		this.myPagePending = myPagePending;
	}
	public String getMyPageApprovedPending() {
		return myPageApprovedPending;
	}
	public void setMyPageApprovedPending(String myPageApprovedPending) {
		this.myPageApprovedPending = myPageApprovedPending;
	}
	public String getPendingLength() {
		return pendingLength;
	}
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	public String getApprovedPendingLength() {
		return approvedPendingLength;
	}
	public void setApprovedPendingLength(String approvedPendingLength) {
		this.approvedPendingLength = approvedPendingLength;
	}
	public String getApprovedLength() {
		return approvedLength;
	}
	public void setApprovedLength(String approvedLength) {
		this.approvedLength = approvedLength;
	}
	public String getRejectedLength() {
		return rejectedLength;
	}
	public void setRejectedLength(String rejectedLength) {
		this.rejectedLength = rejectedLength;
	}
	public String getMyPage1() {
		return myPage1;
	}
	public void setMyPage1(String myPage1) {
		this.myPage1 = myPage1;
	}
	public String getSearchEmpCode() {
		return searchEmpCode;
	}
	public void setSearchEmpCode(String searchEmpCode) {
		this.searchEmpCode = searchEmpCode;
	}
	public String getSearchEmpName() {
		return searchEmpName;
	}
	public void setSearchEmpName(String searchEmpName) {
		this.searchEmpName = searchEmpName;
	}
	public String getSearchEmpToken() {
		return searchEmpToken;
	}
	public void setSearchEmpToken(String searchEmpToken) {
		this.searchEmpToken = searchEmpToken;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
 
	
	
	
	
}