package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.List;
import org.paradyne.lib.BeanBase;

/**
 * Bhushan Dasare Feb 15, 2011
 */

public class ClassEnrollmentForm extends BeanBase {

	private String classEnrollmentId = "";
	private String hiddenCode = "";
	private String courseId = "";
	private String courseName = "";
	private String courseLocationName = "";
	private String startDate = "";
	private String endDate = "";
	private String employeeToken = "";
	private String employeeName = "";
	private String employeeCode = "";
	private String deptNumber = "";
	private String deptName = "";
	private String attendeeToken = "";
	private String attendeeName = "";
	private String attendeeCode= "";
	private String location= "";
	private String streetAddress="";
	private String cityName= "";
	private String cityId = "";
	private String stateName= "";
	private String stateZip="";
	private String providence = "";
	private String canadaZipcode = "";
	private String phoneNumber = "";
	private String faxNumber = "";
	private String emailAddress = "";
	
	private String initiatorSign="";
	private String initiatorSubmissionDate="";
	private String managerSign="";
	private String managerSubmissionDate="";
	
	private String approverName = "";
	private ArrayList approverList;
	
	private boolean secondAppFlag= false;
	private String selectapproverName = "";
	private String secondApproverId="";
	private String secondApproverToken = "";
	private String secondApproverName = "";
	private String firstApproverCode = "";
	private String firstApproverToken = "";
	
	
	private String applDate = "";
	private String country = "";
	private String status= "";
	
	
	private String approverCode = "";
	
	// variables for paging
	private String approveLength = "false";
	private String approveCancelLength = "false";
	private String myPageCancelApproved = "";
	private String myPageCancelled = "";
	private String cancelledLength = "false";
	private String myPageApproved = "";
	private String draftLength = "";
	private String myPageDraft = "";
	private String sendbackLength = "";
	private String myPageSendBack = "";

	private String pendingLength = "";
	private String myPagePending = "";

	// added for paging

	private String myPageDrafted = "";
	private String myPageInProcess = "";
	private String myPageSentBack = "";
	// private String myPageApproved = "";
	private String myPageApprovedCancel = "";
	private String myPageCancel = "";
	private String myPageRejected = "";
	private String myPageCancelRejected = "";

	ArrayList draftVoucherIteratorList = null;
	boolean draftVoucherListLength = false;
	ArrayList inProcessVoucherIteratorList = null;
	boolean inProcessVoucherListLength = false;
	ArrayList sentBackVoucherIteratorList = null;
	 boolean sentBackVoucherListLength = false;
	ArrayList approvedApplicationIteratorList = null;
	private boolean approvedApplicationListLength = false;
	ArrayList approvedCancellationApplicationIteratorList = null;
	private boolean approvedCancellationApplicationListLength = false;
	ArrayList cancelledVoucherIteratorList = null;
	private boolean cancelledVoucherListLength = false;
	ArrayList rejectedApplicationIteratorList = null;
	private boolean rejectedApplicationListLength = false;
	ArrayList rejectedCancelApplicationIteratorList = null;
	private boolean rejectedCancelApplicationListLength = false;
	private String listType = "";
	private String myPage = "";
	
	private String enrollmentHiddenID = "";
	private String listTypeDetailPage = "";
	
	boolean approverCommentFlag = false;
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";
	ArrayList approverCommentList = null;
	private String approverToken = "";
	public String trackingNo="";
	private String initiatorCode = "";
	private String initiatorDate = "";
	private String initiatorToken = "";
	private String initiatorName = "";
	private String locationOption = "";
	private String deptCode = "";
	
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getLocationOption() {
		return locationOption;
	}
	public void setLocationOption(String locationOption) {
		this.locationOption = locationOption;
	}
	public String getInitiatorCode() {
		return initiatorCode;
	}
	public void setInitiatorCode(String initiatorCode) {
		this.initiatorCode = initiatorCode;
	}
	public String getInitiatorDate() {
		return initiatorDate;
	}
	public void setInitiatorDate(String initiatorDate) {
		this.initiatorDate = initiatorDate;
	}
	public String getInitiatorToken() {
		return initiatorToken;
	}
	public void setInitiatorToken(String initiatorToken) {
		this.initiatorToken = initiatorToken;
	}
	public String getInitiatorName() {
		return initiatorName;
	}
	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
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
	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}
	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	public boolean isApproverCommentFlag() {
		return approverCommentFlag;
	}
	public void setApproverCommentFlag(boolean approverCommentFlag) {
		this.approverCommentFlag = approverCommentFlag;
	}
	public String getListTypeDetailPage() {
		return listTypeDetailPage;
	}
	public void setListTypeDetailPage(String listTypeDetailPage) {
		this.listTypeDetailPage = listTypeDetailPage;
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
	public String getApproveLength() {
		return approveLength;
	}
	public void setApproveLength(String approveLength) {
		this.approveLength = approveLength;
	}
	public String getApproveCancelLength() {
		return approveCancelLength;
	}
	public void setApproveCancelLength(String approveCancelLength) {
		this.approveCancelLength = approveCancelLength;
	}
	public String getMyPageCancelApproved() {
		return myPageCancelApproved;
	}
	public void setMyPageCancelApproved(String myPageCancelApproved) {
		this.myPageCancelApproved = myPageCancelApproved;
	}
	public String getMyPageCancelled() {
		return myPageCancelled;
	}
	public void setMyPageCancelled(String myPageCancelled) {
		this.myPageCancelled = myPageCancelled;
	}
	public String getCancelledLength() {
		return cancelledLength;
	}
	public void setCancelledLength(String cancelledLength) {
		this.cancelledLength = cancelledLength;
	}
	public String getMyPageApproved() {
		return myPageApproved;
	}
	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}
	public String getDraftLength() {
		return draftLength;
	}
	public void setDraftLength(String draftLength) {
		this.draftLength = draftLength;
	}
	public String getMyPageDraft() {
		return myPageDraft;
	}
	public void setMyPageDraft(String myPageDraft) {
		this.myPageDraft = myPageDraft;
	}
	public String getSendbackLength() {
		return sendbackLength;
	}
	public void setSendbackLength(String sendbackLength) {
		this.sendbackLength = sendbackLength;
	}
	public String getMyPageSendBack() {
		return myPageSendBack;
	}
	public void setMyPageSendBack(String myPageSendBack) {
		this.myPageSendBack = myPageSendBack;
	}
	public String getPendingLength() {
		return pendingLength;
	}
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	public String getMyPagePending() {
		return myPagePending;
	}
	public void setMyPagePending(String myPagePending) {
		this.myPagePending = myPagePending;
	}
	public String getMyPageDrafted() {
		return myPageDrafted;
	}
	public void setMyPageDrafted(String myPageDrafted) {
		this.myPageDrafted = myPageDrafted;
	}
	public String getMyPageInProcess() {
		return myPageInProcess;
	}
	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}
	public String getMyPageSentBack() {
		return myPageSentBack;
	}
	public void setMyPageSentBack(String myPageSentBack) {
		this.myPageSentBack = myPageSentBack;
	}
	public String getMyPageApprovedCancel() {
		return myPageApprovedCancel;
	}
	public void setMyPageApprovedCancel(String myPageApprovedCancel) {
		this.myPageApprovedCancel = myPageApprovedCancel;
	}
	public String getMyPageCancel() {
		return myPageCancel;
	}
	public void setMyPageCancel(String myPageCancel) {
		this.myPageCancel = myPageCancel;
	}
	public String getMyPageRejected() {
		return myPageRejected;
	}
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	public String getMyPageCancelRejected() {
		return myPageCancelRejected;
	}
	public void setMyPageCancelRejected(String myPageCancelRejected) {
		this.myPageCancelRejected = myPageCancelRejected;
	}
	public ArrayList getDraftVoucherIteratorList() {
		return draftVoucherIteratorList;
	}
	public void setDraftVoucherIteratorList(ArrayList draftVoucherIteratorList) {
		this.draftVoucherIteratorList = draftVoucherIteratorList;
	}
	public boolean isDraftVoucherListLength() {
		return draftVoucherListLength;
	}
	public void setDraftVoucherListLength(boolean draftVoucherListLength) {
		this.draftVoucherListLength = draftVoucherListLength;
	}
	public ArrayList getInProcessVoucherIteratorList() {
		return inProcessVoucherIteratorList;
	}
	public void setInProcessVoucherIteratorList(
			ArrayList inProcessVoucherIteratorList) {
		this.inProcessVoucherIteratorList = inProcessVoucherIteratorList;
	}
	public boolean isInProcessVoucherListLength() {
		return inProcessVoucherListLength;
	}
	public void setInProcessVoucherListLength(boolean inProcessVoucherListLength) {
		this.inProcessVoucherListLength = inProcessVoucherListLength;
	}
	public ArrayList getSentBackVoucherIteratorList() {
		return sentBackVoucherIteratorList;
	}
	public void setSentBackVoucherIteratorList(ArrayList sentBackVoucherIteratorList) {
		this.sentBackVoucherIteratorList = sentBackVoucherIteratorList;
	}
	public boolean isSentBackVoucherListLength() {
		return sentBackVoucherListLength;
	}
	public void setSentBackVoucherListLength(boolean sentBackVoucherListLength) {
		this.sentBackVoucherListLength = sentBackVoucherListLength;
	}
	
	public ArrayList getApprovedApplicationIteratorList() {
		return approvedApplicationIteratorList;
	}
	public void setApprovedApplicationIteratorList(
			ArrayList approvedApplicationIteratorList) {
		this.approvedApplicationIteratorList = approvedApplicationIteratorList;
	}
	public boolean isApprovedApplicationListLength() {
		return approvedApplicationListLength;
	}
	public void setApprovedApplicationListLength(
			boolean approvedApplicationListLength) {
		this.approvedApplicationListLength = approvedApplicationListLength;
	}
	
	public ArrayList getApprovedCancellationApplicationIteratorList() {
		return approvedCancellationApplicationIteratorList;
	}
	public void setApprovedCancellationApplicationIteratorList(
			ArrayList approvedCancellationApplicationIteratorList) {
		this.approvedCancellationApplicationIteratorList = approvedCancellationApplicationIteratorList;
	}
	public boolean isApprovedCancellationApplicationListLength() {
		return approvedCancellationApplicationListLength;
	}
	public void setApprovedCancellationApplicationListLength(
			boolean approvedCancellationApplicationListLength) {
		this.approvedCancellationApplicationListLength = approvedCancellationApplicationListLength;
	}
	public ArrayList getCancelledVoucherIteratorList() {
		return cancelledVoucherIteratorList;
	}
	public void setCancelledVoucherIteratorList(
			ArrayList cancelledVoucherIteratorList) {
		this.cancelledVoucherIteratorList = cancelledVoucherIteratorList;
	}
	public boolean isCancelledVoucherListLength() {
		return cancelledVoucherListLength;
	}
	public void setCancelledVoucherListLength(boolean cancelledVoucherListLength) {
		this.cancelledVoucherListLength = cancelledVoucherListLength;
	}
	
	public ArrayList getRejectedApplicationIteratorList() {
		return rejectedApplicationIteratorList;
	}
	public void setRejectedApplicationIteratorList(
			ArrayList rejectedApplicationIteratorList) {
		this.rejectedApplicationIteratorList = rejectedApplicationIteratorList;
	}
	public boolean isRejectedApplicationListLength() {
		return rejectedApplicationListLength;
	}
	public void setRejectedApplicationListLength(
			boolean rejectedApplicationListLength) {
		this.rejectedApplicationListLength = rejectedApplicationListLength;
	}
	
	
	public ArrayList getRejectedCancelApplicationIteratorList() {
		return rejectedCancelApplicationIteratorList;
	}
	public void setRejectedCancelApplicationIteratorList(
			ArrayList rejectedCancelApplicationIteratorList) {
		this.rejectedCancelApplicationIteratorList = rejectedCancelApplicationIteratorList;
	}
	public boolean isRejectedCancelApplicationListLength() {
		return rejectedCancelApplicationListLength;
	}
	public void setRejectedCancelApplicationListLength(
			boolean rejectedCancelApplicationListLength) {
		this.rejectedCancelApplicationListLength = rejectedCancelApplicationListLength;
	}
	public String getApproverCode() {
		return approverCode;
	}
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getApplDate() {
		return applDate;
	}
	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}
	public String getFirstApproverCode() {
		return firstApproverCode;
	}
	public void setFirstApproverCode(String firstApproverCode) {
		this.firstApproverCode = firstApproverCode;
	}
	public String getFirstApproverToken() {
		return firstApproverToken;
	}
	public void setFirstApproverToken(String firstApproverToken) {
		this.firstApproverToken = firstApproverToken;
	}
	public boolean isSecondAppFlag() {
		return secondAppFlag;
	}
	public void setSecondAppFlag(boolean secondAppFlag) {
		this.secondAppFlag = secondAppFlag;
	}
	public String getSelectapproverName() {
		return selectapproverName;
	}
	public void setSelectapproverName(String selectapproverName) {
		this.selectapproverName = selectapproverName;
	}
	public String getSecondApproverId() {
		return secondApproverId;
	}
	public void setSecondApproverId(String secondApproverId) {
		this.secondApproverId = secondApproverId;
	}
	public String getSecondApproverToken() {
		return secondApproverToken;
	}
	public void setSecondApproverToken(String secondApproverToken) {
		this.secondApproverToken = secondApproverToken;
	}
	public String getSecondApproverName() {
		return secondApproverName;
	}
	public void setSecondApproverName(String secondApproverName) {
		this.secondApproverName = secondApproverName;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public ArrayList getApproverList() {
		return approverList;
	}
	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}
	public String getClassEnrollmentId() {
		return classEnrollmentId;
	}
	public void setClassEnrollmentId(String classEnrollmentId) {
		this.classEnrollmentId = classEnrollmentId;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseLocationName() {
		return courseLocationName;
	}
	public void setCourseLocationName(String courseLocationName) {
		this.courseLocationName = courseLocationName;
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
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getDeptNumber() {
		return deptNumber;
	}
	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAttendeeToken() {
		return attendeeToken;
	}
	public void setAttendeeToken(String attendeeToken) {
		this.attendeeToken = attendeeToken;
	}
	public String getAttendeeName() {
		return attendeeName;
	}
	public void setAttendeeName(String attendeeName) {
		this.attendeeName = attendeeName;
	}
	public String getAttendeeCode() {
		return attendeeCode;
	}
	public void setAttendeeCode(String attendeeCode) {
		this.attendeeCode = attendeeCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateZip() {
		return stateZip;
	}
	public void setStateZip(String stateZip) {
		this.stateZip = stateZip;
	}
	public String getProvidence() {
		return providence;
	}
	public void setProvidence(String providence) {
		this.providence = providence;
	}
	public String getCanadaZipcode() {
		return canadaZipcode;
	}
	public void setCanadaZipcode(String canadaZipcode) {
		this.canadaZipcode = canadaZipcode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getInitiatorSign() {
		return initiatorSign;
	}
	public void setInitiatorSign(String initiatorSign) {
		this.initiatorSign = initiatorSign;
	}
	public String getInitiatorSubmissionDate() {
		return initiatorSubmissionDate;
	}
	public void setInitiatorSubmissionDate(String initiatorSubmissionDate) {
		this.initiatorSubmissionDate = initiatorSubmissionDate;
	}
	public String getManagerSign() {
		return managerSign;
	}
	public void setManagerSign(String managerSign) {
		this.managerSign = managerSign;
	}
	public String getManagerSubmissionDate() {
		return managerSubmissionDate;
	}
	public void setManagerSubmissionDate(String managerSubmissionDate) {
		this.managerSubmissionDate = managerSubmissionDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEnrollmentHiddenID() {
		return enrollmentHiddenID;
	}
	public void setEnrollmentHiddenID(String enrollmentHiddenID) {
		this.enrollmentHiddenID = enrollmentHiddenID;
	}
	public String getApproverToken() {
		return approverToken;
	}
	public void setApproverToken(String approverToken) {
		this.approverToken = approverToken;
	}
	
	
	
	
}