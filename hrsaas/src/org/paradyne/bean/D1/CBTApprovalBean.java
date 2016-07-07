package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.List;
import org.paradyne.lib.BeanBase;

public class CBTApprovalBean extends BeanBase{
	
	private String listType = "";
	private String myPage = "";
	private String cbtHiddenID = "";
	private String hiddenStatus = "";
	private String myPageApproved = "";
	private String myPageRejected = "";
	
	List<CBTApprovalBean> pendingIteratorList;
	private boolean pendingListLength = false;
	List<CBTApprovalBean> approveredIteratorList = null;
	private boolean approvedListLength = false;
	List<CBTApprovalBean> rejectedIteratorList = null;
	private boolean rejectedListLength = false;
	
	private String cbtID = "";
	private String trackingNumberIterator = "";
	private String empNameIterator = "";
	private String applicationDateIterator = "";
	
	private String status = "";
	private String employeeToken = "";
	private String employeeName = "";
	private String employeeID = "";
	private String deptName = "";
	private String deptNo = "";
	private String courseNo = "";
	private String courseCode = "";
	private String courseDesc = "";
	private String applnStatus = "";
	private String address = "";
	private String city = "";
	private String state = "";
	private String country = "";
	private String zip = "";
	private String phNo = "";
	private String faxNo = "";
	private String emailAddress = "";
	private String defaultManagerToken = "";
	private String defaultManagerName = "";
	private String defaultManagerID = "";
	private String changeMyManagerToken = "";
	private String changeMyManagerName = "";
	private String changeMyManagerID = "";
	private String completedByName = "";
	private String completedByID = "";
	private String completedOn = "";
	private String locationRadioValue = "";
	private String providence = "";
	private String trackingNumber = "";
	private String locationName = "";
	private String approvedID = "";
	
	//Approver comments Section Begins
	private boolean approverCommentsFlag = false;
	List<CBTApprovalBean> approverCommentList = null;
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";
	//Approver comments Section Ends
	private String approverComments = "";
	
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public String getApplnStatus() {
		return applnStatus;
	}
	public void setApplnStatus(String applnStatus) {
		this.applnStatus = applnStatus;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getDefaultManagerToken() {
		return defaultManagerToken;
	}
	public void setDefaultManagerToken(String defaultManagerToken) {
		this.defaultManagerToken = defaultManagerToken;
	}
	public String getDefaultManagerName() {
		return defaultManagerName;
	}
	public void setDefaultManagerName(String defaultManagerName) {
		this.defaultManagerName = defaultManagerName;
	}
	public String getDefaultManagerID() {
		return defaultManagerID;
	}
	public void setDefaultManagerID(String defaultManagerID) {
		this.defaultManagerID = defaultManagerID;
	}
	public String getChangeMyManagerToken() {
		return changeMyManagerToken;
	}
	public void setChangeMyManagerToken(String changeMyManagerToken) {
		this.changeMyManagerToken = changeMyManagerToken;
	}
	public String getChangeMyManagerName() {
		return changeMyManagerName;
	}
	public void setChangeMyManagerName(String changeMyManagerName) {
		this.changeMyManagerName = changeMyManagerName;
	}
	public String getChangeMyManagerID() {
		return changeMyManagerID;
	}
	public void setChangeMyManagerID(String changeMyManagerID) {
		this.changeMyManagerID = changeMyManagerID;
	}
	public String getCompletedByName() {
		return completedByName;
	}
	public void setCompletedByName(String completedByName) {
		this.completedByName = completedByName;
	}
	public String getCompletedByID() {
		return completedByID;
	}
	public void setCompletedByID(String completedByID) {
		this.completedByID = completedByID;
	}
	public String getCompletedOn() {
		return completedOn;
	}
	public void setCompletedOn(String completedOn) {
		this.completedOn = completedOn;
	}
	public String getLocationRadioValue() {
		return locationRadioValue;
	}
	public void setLocationRadioValue(String locationRadioValue) {
		this.locationRadioValue = locationRadioValue;
	}
	public String getProvidence() {
		return providence;
	}
	public void setProvidence(String providence) {
		this.providence = providence;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
	public String getCbtHiddenID() {
		return cbtHiddenID;
	}
	public void setCbtHiddenID(String cbtHiddenID) {
		this.cbtHiddenID = cbtHiddenID;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
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
	public List<CBTApprovalBean> getPendingIteratorList() {
		return pendingIteratorList;
	}
	public void setPendingIteratorList(List<CBTApprovalBean> pendingIteratorList) {
		this.pendingIteratorList = pendingIteratorList;
	}
	public boolean isPendingListLength() {
		return pendingListLength;
	}
	public void setPendingListLength(boolean pendingListLength) {
		this.pendingListLength = pendingListLength;
	}
	public List<CBTApprovalBean> getApproveredIteratorList() {
		return approveredIteratorList;
	}
	public void setApproveredIteratorList(List<CBTApprovalBean> approveredIteratorList) {
		this.approveredIteratorList = approveredIteratorList;
	}
	public boolean isApprovedListLength() {
		return approvedListLength;
	}
	public void setApprovedListLength(boolean approvedListLength) {
		this.approvedListLength = approvedListLength;
	}
	public List<CBTApprovalBean> getRejectedIteratorList() {
		return rejectedIteratorList;
	}
	public void setRejectedIteratorList(List<CBTApprovalBean> rejectedIteratorList) {
		this.rejectedIteratorList = rejectedIteratorList;
	}
	public boolean isRejectedListLength() {
		return rejectedListLength;
	}
	public void setRejectedListLength(boolean rejectedListLength) {
		this.rejectedListLength = rejectedListLength;
	}
	public String getCbtID() {
		return cbtID;
	}
	public void setCbtID(String cbtID) {
		this.cbtID = cbtID;
	}
	public String getTrackingNumberIterator() {
		return trackingNumberIterator;
	}
	public void setTrackingNumberIterator(String trackingNumberIterator) {
		this.trackingNumberIterator = trackingNumberIterator;
	}
	public String getEmpNameIterator() {
		return empNameIterator;
	}
	public void setEmpNameIterator(String empNameIterator) {
		this.empNameIterator = empNameIterator;
	}
	public String getApplicationDateIterator() {
		return applicationDateIterator;
	}
	public void setApplicationDateIterator(String applicationDateIterator) {
		this.applicationDateIterator = applicationDateIterator;
	}
	public boolean isApproverCommentsFlag() {
		return approverCommentsFlag;
	}
	public void setApproverCommentsFlag(boolean approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
	}
	public List<CBTApprovalBean> getApproverCommentList() {
		return approverCommentList;
	}
	public void setApproverCommentList(List<CBTApprovalBean> approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	public String getApprName() {
		return apprName;
	}
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}
	public String getApprComments() {
		return apprComments;
	}
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public String getApprStatus() {
		return apprStatus;
	}
	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}
	public String getApprovedID() {
		return approvedID;
	}
	public void setApprovedID(String approvedID) {
		this.approvedID = approvedID;
	}
	
}
