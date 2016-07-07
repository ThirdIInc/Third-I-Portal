/**
 * 
 */
package org.paradyne.bean.extraWorkBenefits;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0623
 *
 */
public class ExtraWorkApproval extends BeanBase {
	private String listType = "";
	private String myPage;
	private String show;
	private String selectname;
	
	private String extWrkAppNo="";
	private String empCode="";
	private String empName="";
	private String tokenNo="";
	private String date="";
	private String level="";
	
	//FIELDS FOR PENDING APPLICATION LIST
	private String pendingStatus= "";
	private ArrayList pendingList;
	
	//FIELDS FOR APPROVED LIST
	private String appEmpId= "";
	private String appExtAppNo= "";
	private String appStatus= "";
	private String appEmpToken= "";
	private String appEmpName= "";
	private String appLevel= "";
	private String appAppDate= "";
	private ArrayList appList;
	
	//FIELDS FOR REJECTED LIST
	private ArrayList rejList;
	private String rejEmpId= "";
	private String rejExtAppNo= "";
	private String rejEmpToken= "";
	private String rejEmpName= "";
	private String rejAppDate= "";
	private String rejStatus= "";
	private String rejLevel= "";
	
	//FIELDS FOR PAGING
	
	private String myPageApproved= "";
	private String myPageRejected= "";
	private String myPagePending= "";
	
	private String pendingLength="false";
	private String approvedLength="false";
	private String rejectedLength="false";
	/**
	 * @return the listType
	 */
	public String getListType() {
		return listType;
	}
	/**
	 * @param listType the listType to set
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}
	/**
	 * @return the pendingStatus
	 */
	public String getPendingStatus() {
		return pendingStatus;
	}
	/**
	 * @param pendingStatus the pendingStatus to set
	 */
	public void setPendingStatus(String pendingStatus) {
		this.pendingStatus = pendingStatus;
	}
	/**
	 * @return the pendingList
	 */
	public ArrayList getPendingList() {
		return pendingList;
	}
	/**
	 * @param pendingList the pendingList to set
	 */
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}
	/**
	 * @return the appEmpId
	 */
	public String getAppEmpId() {
		return appEmpId;
	}
	/**
	 * @param appEmpId the appEmpId to set
	 */
	public void setAppEmpId(String appEmpId) {
		this.appEmpId = appEmpId;
	}
	/**
	 * @return the appExtAppNo
	 */
	public String getAppExtAppNo() {
		return appExtAppNo;
	}
	/**
	 * @param appExtAppNo the appExtAppNo to set
	 */
	public void setAppExtAppNo(String appExtAppNo) {
		this.appExtAppNo = appExtAppNo;
	}
	/**
	 * @return the appStatus
	 */
	public String getAppStatus() {
		return appStatus;
	}
	/**
	 * @param appStatus the appStatus to set
	 */
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	/**
	 * @return the appEmpToken
	 */
	public String getAppEmpToken() {
		return appEmpToken;
	}
	/**
	 * @param appEmpToken the appEmpToken to set
	 */
	public void setAppEmpToken(String appEmpToken) {
		this.appEmpToken = appEmpToken;
	}
	/**
	 * @return the appEmpName
	 */
	public String getAppEmpName() {
		return appEmpName;
	}
	/**
	 * @param appEmpName the appEmpName to set
	 */
	public void setAppEmpName(String appEmpName) {
		this.appEmpName = appEmpName;
	}
	/**
	 * @return the appLevel
	 */
	public String getAppLevel() {
		return appLevel;
	}
	/**
	 * @param appLevel the appLevel to set
	 */
	public void setAppLevel(String appLevel) {
		this.appLevel = appLevel;
	}
	/**
	 * @return the appAppDate
	 */
	public String getAppAppDate() {
		return appAppDate;
	}
	/**
	 * @param appAppDate the appAppDate to set
	 */
	public void setAppAppDate(String appAppDate) {
		this.appAppDate = appAppDate;
	}
	/**
	 * @return the appList
	 */
	public ArrayList getAppList() {
		return appList;
	}
	/**
	 * @param appList the appList to set
	 */
	public void setAppList(ArrayList appList) {
		this.appList = appList;
	}
	/**
	 * @return the rejList
	 */
	public ArrayList getRejList() {
		return rejList;
	}
	/**
	 * @param rejList the rejList to set
	 */
	public void setRejList(ArrayList rejList) {
		this.rejList = rejList;
	}
	/**
	 * @return the rejEmpId
	 */
	public String getRejEmpId() {
		return rejEmpId;
	}
	/**
	 * @param rejEmpId the rejEmpId to set
	 */
	public void setRejEmpId(String rejEmpId) {
		this.rejEmpId = rejEmpId;
	}
	/**
	 * @return the rejExtAppNo
	 */
	public String getRejExtAppNo() {
		return rejExtAppNo;
	}
	/**
	 * @param rejExtAppNo the rejExtAppNo to set
	 */
	public void setRejExtAppNo(String rejExtAppNo) {
		this.rejExtAppNo = rejExtAppNo;
	}
	/**
	 * @return the rejEmpToken
	 */
	public String getRejEmpToken() {
		return rejEmpToken;
	}
	/**
	 * @param rejEmpToken the rejEmpToken to set
	 */
	public void setRejEmpToken(String rejEmpToken) {
		this.rejEmpToken = rejEmpToken;
	}
	/**
	 * @return the rejEmpName
	 */
	public String getRejEmpName() {
		return rejEmpName;
	}
	/**
	 * @param rejEmpName the rejEmpName to set
	 */
	public void setRejEmpName(String rejEmpName) {
		this.rejEmpName = rejEmpName;
	}
	/**
	 * @return the rejAppDate
	 */
	public String getRejAppDate() {
		return rejAppDate;
	}
	/**
	 * @param rejAppDate the rejAppDate to set
	 */
	public void setRejAppDate(String rejAppDate) {
		this.rejAppDate = rejAppDate;
	}
	/**
	 * @return the rejStatus
	 */
	public String getRejStatus() {
		return rejStatus;
	}
	/**
	 * @param rejStatus the rejStatus to set
	 */
	public void setRejStatus(String rejStatus) {
		this.rejStatus = rejStatus;
	}
	/**
	 * @return the rejLevel
	 */
	public String getRejLevel() {
		return rejLevel;
	}
	/**
	 * @param rejLevel the rejLevel to set
	 */
	public void setRejLevel(String rejLevel) {
		this.rejLevel = rejLevel;
	}
	/**
	 * @return the myPageApproved
	 */
	public String getMyPageApproved() {
		return myPageApproved;
	}
	/**
	 * @param myPageApproved the myPageApproved to set
	 */
	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}
	/**
	 * @return the myPageRejected
	 */
	public String getMyPageRejected() {
		return myPageRejected;
	}
	/**
	 * @param myPageRejected the myPageRejected to set
	 */
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	/**
	 * @return the myPagePending
	 */
	public String getMyPagePending() {
		return myPagePending;
	}
	/**
	 * @param myPagePending the myPagePending to set
	 */
	public void setMyPagePending(String myPagePending) {
		this.myPagePending = myPagePending;
	}
	/**
	 * @return the pendingLength
	 */
	public String getPendingLength() {
		return pendingLength;
	}
	/**
	 * @param pendingLength the pendingLength to set
	 */
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	/**
	 * @return the approvedLength
	 */
	public String getApprovedLength() {
		return approvedLength;
	}
	/**
	 * @param approvedLength the approvedLength to set
	 */
	public void setApprovedLength(String approvedLength) {
		this.approvedLength = approvedLength;
	}
	/**
	 * @return the rejectedLength
	 */
	public String getRejectedLength() {
		return rejectedLength;
	}
	/**
	 * @param rejectedLength the rejectedLength to set
	 */
	public void setRejectedLength(String rejectedLength) {
		this.rejectedLength = rejectedLength;
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
	/**
	 * @return the extWrkAppNo
	 */
	public String getExtWrkAppNo() {
		return extWrkAppNo;
	}
	/**
	 * @param extWrkAppNo the extWrkAppNo to set
	 */
	public void setExtWrkAppNo(String extWrkAppNo) {
		this.extWrkAppNo = extWrkAppNo;
	}
	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return empCode;
	}
	/**
	 * @param empCode the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the tokenNo
	 */
	public String getTokenNo() {
		return tokenNo;
	}
	/**
	 * @param tokenNo the tokenNo to set
	 */
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
}
