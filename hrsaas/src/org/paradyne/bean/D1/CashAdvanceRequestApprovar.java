package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

public class CashAdvanceRequestApprovar extends BeanBase {
	
	private String myPageDrafted = "";
	private String myPagePendingCancel = "";
	private String myPageApproved = "";
	private String myPageRejected = "";
	
	
	ArrayList pendingIteratorList = null;
	private boolean pendingListLength = false;
	ArrayList pendingCancellationIteratorList = null;
	private boolean  pendingCancellationListLength = false;
	ArrayList approveredIteratorList = null;
	private boolean approvedListLength = false;
	ArrayList rejectedIteratorList = null;
	private boolean rejectedListLength = false;
	
	private String cashAdvApprHiddenID = "";
	private String employeeIDIterator = "";
	private String employeeTokenIterator = "";
	private String employeeNameIterator = "";
	private String applicationDateIterator ="";
	private String myPage = "";
	private String totalRecords = "";
	
	private String noOfEmployeeTravel = "";
	private String employeeAddress = "";
	private String businessPurpose = "";
	private String tripDuration = "";
	private String advanceAmount = "";
	private String advanceNeededDate = "";
	private String comments = "";
	private String departmentCode= "";
	private String departmentName = "";
	
	private String designation = "";
	private String status = "";
	private String listTypeDetailPage = "";
	private String approverComments = "";
	
	
	
	///////////////////added ganesh
	
	private List pendingAppList;
	private List pendingCancelAppList;
	private List approvedAppList;
	private List rejectedAppList;

	private boolean pagingForPendingApp;
	private boolean pagingForPendingCancelApp;
	private boolean pagingForApprovedApp;
	private boolean pagingForRejectedApp;

	private String pageForPendingApp;
	private String pageForPendingCancelApp;
	private String pageForApprovedApp;
	private String pageForRejectedApp;

	private String listType;
	
	// Approver Comments List 
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";
	ArrayList approverCommentList = null;
	private String enrollHiddenStatus = "";
	private String classEnrollmentApproverId = "";
	private String requesterID = "";
	private String level = "";
	private String enrollmentHiddenID="";
	private String courseId = "";
	private String applDate = "";
	private String employeeName = "";
	private String employeeToken = "";
	
	private String startDate = "";
	private String endDate = "";
	
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
	
	private String courseName = "";
	private String courseLocationName = "";
	
	private String country = "";
	private String applnStatus = "";
	private String approverToken = "";
	
	private String approverCode = "";
	public String trackingNo="";
	
	private String initiatorCode = "";
	private String initiatorDate = "";
	private String initiatorToken = "";
	private String initiatorName = "";
	private String locationOption = "";
	boolean approverCommentsFlag = false;
	
	private String hrApprover = "";
	
	public boolean isApproverCommentsFlag() {
		return approverCommentsFlag;
	}
	public void setApproverCommentsFlag(boolean approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
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
	public String getLocationOption() {
		return locationOption;
	}
	public void setLocationOption(String locationOption) {
		this.locationOption = locationOption;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getApplDate() {
		return applDate;
	}
	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getRequesterID() {
		return requesterID;
	}
	public void setRequesterID(String requesterID) {
		this.requesterID = requesterID;
	}
	public String getEnrollHiddenStatus() {
		return enrollHiddenStatus;
	}
	public void setEnrollHiddenStatus(String enrollHiddenStatus) {
		this.enrollHiddenStatus = enrollHiddenStatus;
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
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	public ArrayList getPendingIteratorList() {
		return pendingIteratorList;
	}
	public void setPendingIteratorList(ArrayList pendingIteratorList) {
		this.pendingIteratorList = pendingIteratorList;
	}
	public boolean isPendingListLength() {
		return pendingListLength;
	}
	public void setPendingListLength(boolean pendingListLength) {
		this.pendingListLength = pendingListLength;
	}
	public ArrayList getPendingCancellationIteratorList() {
		return pendingCancellationIteratorList;
	}
	public void setPendingCancellationIteratorList(
			ArrayList pendingCancellationIteratorList) {
		this.pendingCancellationIteratorList = pendingCancellationIteratorList;
	}
	public boolean isPendingCancellationListLength() {
		return pendingCancellationListLength;
	}
	public void setPendingCancellationListLength(
			boolean pendingCancellationListLength) {
		this.pendingCancellationListLength = pendingCancellationListLength;
	}
	public ArrayList getApproveredIteratorList() {
		return approveredIteratorList;
	}
	public void setApproveredIteratorList(ArrayList approveredIteratorList) {
		this.approveredIteratorList = approveredIteratorList;
	}
	public boolean isApprovedListLength() {
		return approvedListLength;
	}
	public void setApprovedListLength(boolean approvedListLength) {
		this.approvedListLength = approvedListLength;
	}
	public ArrayList getRejectedIteratorList() {
		return rejectedIteratorList;
	}
	public void setRejectedIteratorList(ArrayList rejectedIteratorList) {
		this.rejectedIteratorList = rejectedIteratorList;
	}
	public boolean isRejectedListLength() {
		return rejectedListLength;
	}
	public void setRejectedListLength(boolean rejectedListLength) {
		this.rejectedListLength = rejectedListLength;
	}
	
	public String getEmployeeIDIterator() {
		return employeeIDIterator;
	}
	public void setEmployeeIDIterator(String employeeIDIterator) {
		this.employeeIDIterator = employeeIDIterator;
	}
	public String getEmployeeTokenIterator() {
		return employeeTokenIterator;
	}
	public void setEmployeeTokenIterator(String employeeTokenIterator) {
		this.employeeTokenIterator = employeeTokenIterator;
	}
	public String getEmployeeNameIterator() {
		return employeeNameIterator;
	}
	public void setEmployeeNameIterator(String employeeNameIterator) {
		this.employeeNameIterator = employeeNameIterator;
	}
	public String getApplicationDateIterator() {
		return applicationDateIterator;
	}
	public void setApplicationDateIterator(String applicationDateIterator) {
		this.applicationDateIterator = applicationDateIterator;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getListTypeDetailPage() {
		return listTypeDetailPage;
	}
	public void setListTypeDetailPage(String listTypeDetailPage) {
		this.listTypeDetailPage = listTypeDetailPage;
	}
	public String getMyPageDrafted() {
		return myPageDrafted;
	}
	public void setMyPageDrafted(String myPageDrafted) {
		this.myPageDrafted = myPageDrafted;
	}
	public String getMyPagePendingCancel() {
		return myPagePendingCancel;
	}
	public void setMyPagePendingCancel(String myPagePendingCancel) {
		this.myPagePendingCancel = myPagePendingCancel;
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
	public List getPendingAppList() {
		return pendingAppList;
	}
	public void setPendingAppList(List pendingAppList) {
		this.pendingAppList = pendingAppList;
	}
	public List getPendingCancelAppList() {
		return pendingCancelAppList;
	}
	public void setPendingCancelAppList(List pendingCancelAppList) {
		this.pendingCancelAppList = pendingCancelAppList;
	}
	public List getApprovedAppList() {
		return approvedAppList;
	}
	public void setApprovedAppList(List approvedAppList) {
		this.approvedAppList = approvedAppList;
	}
	public List getRejectedAppList() {
		return rejectedAppList;
	}
	public void setRejectedAppList(List rejectedAppList) {
		this.rejectedAppList = rejectedAppList;
	}
	public boolean isPagingForPendingApp() {
		return pagingForPendingApp;
	}
	public void setPagingForPendingApp(boolean pagingForPendingApp) {
		this.pagingForPendingApp = pagingForPendingApp;
	}
	public boolean isPagingForPendingCancelApp() {
		return pagingForPendingCancelApp;
	}
	public void setPagingForPendingCancelApp(boolean pagingForPendingCancelApp) {
		this.pagingForPendingCancelApp = pagingForPendingCancelApp;
	}
	public boolean isPagingForApprovedApp() {
		return pagingForApprovedApp;
	}
	public void setPagingForApprovedApp(boolean pagingForApprovedApp) {
		this.pagingForApprovedApp = pagingForApprovedApp;
	}
	public boolean isPagingForRejectedApp() {
		return pagingForRejectedApp;
	}
	public void setPagingForRejectedApp(boolean pagingForRejectedApp) {
		this.pagingForRejectedApp = pagingForRejectedApp;
	}
	public String getPageForPendingApp() {
		return pageForPendingApp;
	}
	public void setPageForPendingApp(String pageForPendingApp) {
		this.pageForPendingApp = pageForPendingApp;
	}
	public String getPageForPendingCancelApp() {
		return pageForPendingCancelApp;
	}
	public void setPageForPendingCancelApp(String pageForPendingCancelApp) {
		this.pageForPendingCancelApp = pageForPendingCancelApp;
	}
	public String getPageForApprovedApp() {
		return pageForApprovedApp;
	}
	public void setPageForApprovedApp(String pageForApprovedApp) {
		this.pageForApprovedApp = pageForApprovedApp;
	}
	public String getPageForRejectedApp() {
		return pageForRejectedApp;
	}
	public void setPageForRejectedApp(String pageForRejectedApp) {
		this.pageForRejectedApp = pageForRejectedApp;
	}
	public String getClassEnrollmentApproverId() {
		return classEnrollmentApproverId;
	}
	public void setClassEnrollmentApproverId(String classEnrollmentApproverId) {
		this.classEnrollmentApproverId = classEnrollmentApproverId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getEnrollmentHiddenID() {
		return enrollmentHiddenID;
	}
	public void setEnrollmentHiddenID(String enrollmentHiddenID) {
		this.enrollmentHiddenID = enrollmentHiddenID;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getApproverCode() {
		return approverCode;
	}
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
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
	public String getApplnStatus() {
		return applnStatus;
	}
	public void setApplnStatus(String applnStatus) {
		this.applnStatus = applnStatus;
	}
	public String getApproverToken() {
		return approverToken;
	}
	public void setApproverToken(String approverToken) {
		this.approverToken = approverToken;
	}
	public String getHrApprover() {
		return hrApprover;
	}
	public void setHrApprover(String hrApprover) {
		this.hrApprover = hrApprover;
	}
	public String getCashAdvApprHiddenID() {
		return cashAdvApprHiddenID;
	}
	public void setCashAdvApprHiddenID(String cashAdvApprHiddenID) {
		this.cashAdvApprHiddenID = cashAdvApprHiddenID;
	}
	public String getNoOfEmployeeTravel() {
		return noOfEmployeeTravel;
	}
	public void setNoOfEmployeeTravel(String noOfEmployeeTravel) {
		this.noOfEmployeeTravel = noOfEmployeeTravel;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	public String getBusinessPurpose() {
		return businessPurpose;
	}
	public void setBusinessPurpose(String businessPurpose) {
		this.businessPurpose = businessPurpose;
	}
	public String getTripDuration() {
		return tripDuration;
	}
	public void setTripDuration(String tripDuration) {
		this.tripDuration = tripDuration;
	}
	public String getAdvanceAmount() {
		return advanceAmount;
	}
	public void setAdvanceAmount(String advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	public String getAdvanceNeededDate() {
		return advanceNeededDate;
	}
	public void setAdvanceNeededDate(String advanceNeededDate) {
		this.advanceNeededDate = advanceNeededDate;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	
}
