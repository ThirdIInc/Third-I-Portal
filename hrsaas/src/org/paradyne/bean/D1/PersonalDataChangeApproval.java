package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.List;
import org.paradyne.lib.BeanBase;

/**
 * Bhushan Dasare Feb 15, 2011
 */

public class PersonalDataChangeApproval extends BeanBase {

	private String persDataChangeApproverId;
	private String empToken;
	private String empName;
	private String applicationDate;

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
	// added ganesh
	
	
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
	
	private String listTypeDetailPage = "";
	private String approverComments = "";
	
	// added by ganesh start
	private String firstApproverCode = "";
	private String firstApproverToken = "";
	private String personalDataId = "";
	private String employeeCode = "";

	private String deptNumber = "";
	private String areaType = "";
	private String zip = "";
	private String workPhone = "";
	private String firstName = "";
	private String initialName = "";
	private String lastName = "";
	private String maritalStatus = "";
	private String city = "";
	private String country = "";
	private String stateName = "";
	private String homePhoneNumber = "";
	private String workPhoneNumber = "";
	private String moveDate = "";
	private String emergencyContact = "";
	private String relationType = "";
	private String sameAddressType = "";
	private String homePhoneNumberEmergency = "";
	private String workPhoneNumberEmergency = "";
	private String employeeName = "";
	private String employeeToken = "";
	private String hrApproverEmpId;
	private String hrApproverEmpToken;
	private String hrApproverEmpName;
	
	private String applnStatus = "";
	private String myPage = "";
	private String modeLength = "";
	private String totalRecords = "";
	private String hiddenCode = "";
	ArrayList<Object> draftList = null;
	private String cityId;
	private String cityName;
	private String approverCode = "";
	private String approverToken = "";
	private String approverName = "";
	ArrayList<Object> pendingList = null;
	private String relationCode = "";
	private String relationName = "";
	private String emergencyName = "";
	
	ArrayList<Object> approvedList = null;
	ArrayList<Object> cancelledList = null;
	ArrayList<Object> rejectedList = null;
	ArrayList<Object> sendBackList = null;
	private String checkStatus = "";
	private boolean revokeFlag = false;
	
	
	private boolean secondAppFlag= false;
	private String selectapproverName = "";
	ArrayList<Object> approvedCancellationList = null;
	private boolean approvedCancellationFlag = false;
	private boolean approvedFlag = false;
	ArrayList<Object> submitList = null;
	private boolean pendingFlag = false;
	private String empCode = "";
	ArrayList<Object> rejectedCancellationList = null;
	// FILDS FOR APPROVER LIST
	private ArrayList approverList;
	private String srNoIterator = "";
	
	private String relationAddress;
	private String level;
	private List<Object> approverCommentList;
	private String apprName;
	private String apprComments;
	private String apprDate;
	private String apprStatus;
	public String trackingNo="";
	
	private String initiatorCode = "";
	private String initiatorDate = "";
	private String initiatorToken = "";
	private String initiatorName = "";
	private String streetAddress = "";
	boolean approverCommentsFlag = false;
	private String deptCode= "";
	private String deptName= "";

	private String emailAddress="";
	private String empDeptCode="";
	private String empDeptName="";
	
	public String getEmpDeptCode() {
		return empDeptCode;
	}

	public void setEmpDeptCode(String empDeptCode) {
		this.empDeptCode = empDeptCode;
	}

	public String getEmpDeptName() {
		return empDeptName;
	}

	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public boolean isApproverCommentsFlag() {
		return approverCommentsFlag;
	}

	public void setApproverCommentsFlag(boolean approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public String getListTypeDetailPage() {
		return listTypeDetailPage;
	}

	public void setListTypeDetailPage(String listTypeDetailPage) {
		this.listTypeDetailPage = listTypeDetailPage;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
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

	/**
	 * @return the applicationDate
	 */
	public String getApplicationDate() {
		return applicationDate;
	}

	/**
	 * @return the approvedAppList
	 */
	public List getApprovedAppList() {
		return approvedAppList;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @return the listType
	 */
	public String getListType() {
		return listType;
	}

	/**
	 * @return the pageForApprovedApp
	 */
	public String getPageForApprovedApp() {
		return pageForApprovedApp;
	}

	/**
	 * @return the pageForPendingApp
	 */
	public String getPageForPendingApp() {
		return pageForPendingApp;
	}

	/**
	 * @return the pageForPendingCancelApp
	 */
	public String getPageForPendingCancelApp() {
		return pageForPendingCancelApp;
	}

	/**
	 * @return the pageForRejectedApp
	 */
	public String getPageForRejectedApp() {
		return pageForRejectedApp;
	}

	/**
	 * @return the pendingAppList
	 */
	public List getPendingAppList() {
		return pendingAppList;
	}

	/**
	 * @return the pendingCancelAppList
	 */
	public List getPendingCancelAppList() {
		return pendingCancelAppList;
	}

	
	/**
	 * @return the rejectedAppList
	 */
	public List getRejectedAppList() {
		return rejectedAppList;
	}

	/**
	 * @return the pagingForApprovedApp
	 */
	public boolean isPagingForApprovedApp() {
		return pagingForApprovedApp;
	}

	/**
	 * @return the pagingForPendingApp
	 */
	public boolean isPagingForPendingApp() {
		return pagingForPendingApp;
	}

	/**
	 * @return the pagingForPendingCancelApp
	 */
	public boolean isPagingForPendingCancelApp() {
		return pagingForPendingCancelApp;
	}

	/**
	 * @return the pagingForRejectedApp
	 */
	public boolean isPagingForRejectedApp() {
		return pagingForRejectedApp;
	}

	/**
	 * @param applicationDate the applicationDate to set
	 */
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	/**
	 * @param approvedAppList the approvedAppList to set
	 */
	public void setApprovedAppList(List approvedAppList) {
		this.approvedAppList = approvedAppList;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @param listType the listType to set
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}

	/**
	 * @param pageForApprovedApp the pageForApprovedApp to set
	 */
	public void setPageForApprovedApp(String pageForApprovedApp) {
		this.pageForApprovedApp = pageForApprovedApp;
	}

	/**
	 * @param pageForPendingApp the pageForPendingApp to set
	 */
	public void setPageForPendingApp(String pageForPendingApp) {
		this.pageForPendingApp = pageForPendingApp;
	}

	/**
	 * @param pageForPendingCancelApp the pageForPendingCancelApp to set
	 */
	public void setPageForPendingCancelApp(String pageForPendingCancelApp) {
		this.pageForPendingCancelApp = pageForPendingCancelApp;
	}

	/**
	 * @param pageForRejectedApp the pageForRejectedApp to set
	 */
	public void setPageForRejectedApp(String pageForRejectedApp) {
		this.pageForRejectedApp = pageForRejectedApp;
	}

	/**
	 * @param pagingForApprovedApp the pagingForApprovedApp to set
	 */
	public void setPagingForApprovedApp(boolean pagingForApprovedApp) {
		this.pagingForApprovedApp = pagingForApprovedApp;
	}

	/**
	 * @param pagingForPendingApp the pagingForPendingApp to set
	 */
	public void setPagingForPendingApp(boolean pagingForPendingApp) {
		this.pagingForPendingApp = pagingForPendingApp;
	}

	/**
	 * @param pagingForPendingCancelApp the pagingForPendingCancelApp to set
	 */
	public void setPagingForPendingCancelApp(boolean pagingForPendingCancelApp) {
		this.pagingForPendingCancelApp = pagingForPendingCancelApp;
	}

	/**
	 * @param pagingForRejectedApp the pagingForRejectedApp to set
	 */
	public void setPagingForRejectedApp(boolean pagingForRejectedApp) {
		this.pagingForRejectedApp = pagingForRejectedApp;
	}

	/**
	 * @param pendingAppList the pendingAppList to set
	 */
	public void setPendingAppList(List pendingAppList) {
		this.pendingAppList = pendingAppList;
	}

	/**
	 * @param pendingCancelAppList the pendingCancelAppList to set
	 */
	public void setPendingCancelAppList(List pendingCancelAppList) {
		this.pendingCancelAppList = pendingCancelAppList;
	}

	

	/**
	 * @param rejectedAppList the rejectedAppList to set
	 */
	public void setRejectedAppList(List rejectedAppList) {
		this.rejectedAppList = rejectedAppList;
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

	public String getPersonalDataId() {
		return personalDataId;
	}

	public void setPersonalDataId(String personalDataId) {
		this.personalDataId = personalDataId;
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

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getInitialName() {
		return initialName;
	}

	public void setInitialName(String initialName) {
		this.initialName = initialName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}

	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}

	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}

	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}

	public String getMoveDate() {
		return moveDate;
	}

	public void setMoveDate(String moveDate) {
		this.moveDate = moveDate;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public String getSameAddressType() {
		return sameAddressType;
	}

	public void setSameAddressType(String sameAddressType) {
		this.sameAddressType = sameAddressType;
	}

	public String getHomePhoneNumberEmergency() {
		return homePhoneNumberEmergency;
	}

	public void setHomePhoneNumberEmergency(String homePhoneNumberEmergency) {
		this.homePhoneNumberEmergency = homePhoneNumberEmergency;
	}

	public String getWorkPhoneNumberEmergency() {
		return workPhoneNumberEmergency;
	}

	public void setWorkPhoneNumberEmergency(String workPhoneNumberEmergency) {
		this.workPhoneNumberEmergency = workPhoneNumberEmergency;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	public String getHrApproverEmpId() {
		return hrApproverEmpId;
	}

	public void setHrApproverEmpId(String hrApproverEmpId) {
		this.hrApproverEmpId = hrApproverEmpId;
	}

	public String getHrApproverEmpToken() {
		return hrApproverEmpToken;
	}

	public void setHrApproverEmpToken(String hrApproverEmpToken) {
		this.hrApproverEmpToken = hrApproverEmpToken;
	}

	public String getHrApproverEmpName() {
		return hrApproverEmpName;
	}

	public void setHrApproverEmpName(String hrApproverEmpName) {
		this.hrApproverEmpName = hrApproverEmpName;
	}

	public String getApplnStatus() {
		return applnStatus;
	}

	public void setApplnStatus(String applnStatus) {
		this.applnStatus = applnStatus;
	}

	public String getModeLength() {
		return modeLength;
	}

	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	public String getHiddenCode() {
		return hiddenCode;
	}

	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}

	public ArrayList<Object> getDraftList() {
		return draftList;
	}

	public void setDraftList(ArrayList<Object> draftList) {
		this.draftList = draftList;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getApproverCode() {
		return approverCode;
	}

	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}

	public String getApproverToken() {
		return approverToken;
	}

	public void setApproverToken(String approverToken) {
		this.approverToken = approverToken;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public ArrayList<Object> getPendingList() {
		return pendingList;
	}

	public void setPendingList(ArrayList<Object> pendingList) {
		this.pendingList = pendingList;
	}

	public String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getEmergencyName() {
		return emergencyName;
	}

	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}

	public ArrayList<Object> getApprovedList() {
		return approvedList;
	}

	public void setApprovedList(ArrayList<Object> approvedList) {
		this.approvedList = approvedList;
	}

	public ArrayList<Object> getCancelledList() {
		return cancelledList;
	}

	public void setCancelledList(ArrayList<Object> cancelledList) {
		this.cancelledList = cancelledList;
	}

	public ArrayList<Object> getRejectedList() {
		return rejectedList;
	}

	public void setRejectedList(ArrayList<Object> rejectedList) {
		this.rejectedList = rejectedList;
	}

	public ArrayList<Object> getSendBackList() {
		return sendBackList;
	}

	public void setSendBackList(ArrayList<Object> sendBackList) {
		this.sendBackList = sendBackList;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public boolean isRevokeFlag() {
		return revokeFlag;
	}

	public void setRevokeFlag(boolean revokeFlag) {
		this.revokeFlag = revokeFlag;
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

	public ArrayList<Object> getApprovedCancellationList() {
		return approvedCancellationList;
	}

	public void setApprovedCancellationList(
			ArrayList<Object> approvedCancellationList) {
		this.approvedCancellationList = approvedCancellationList;
	}

	public boolean isApprovedCancellationFlag() {
		return approvedCancellationFlag;
	}

	public void setApprovedCancellationFlag(boolean approvedCancellationFlag) {
		this.approvedCancellationFlag = approvedCancellationFlag;
	}

	public boolean isApprovedFlag() {
		return approvedFlag;
	}

	public void setApprovedFlag(boolean approvedFlag) {
		this.approvedFlag = approvedFlag;
	}

	public ArrayList<Object> getSubmitList() {
		return submitList;
	}

	public void setSubmitList(ArrayList<Object> submitList) {
		this.submitList = submitList;
	}

	public boolean isPendingFlag() {
		return pendingFlag;
	}

	public void setPendingFlag(boolean pendingFlag) {
		this.pendingFlag = pendingFlag;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public ArrayList<Object> getRejectedCancellationList() {
		return rejectedCancellationList;
	}

	public void setRejectedCancellationList(
			ArrayList<Object> rejectedCancellationList) {
		this.rejectedCancellationList = rejectedCancellationList;
	}

	public ArrayList getApproverList() {
		return approverList;
	}

	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}

	public String getSrNoIterator() {
		return srNoIterator;
	}

	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
	}

	public String getRelationAddress() {
		return relationAddress;
	}

	public void setRelationAddress(String relationAddress) {
		this.relationAddress = relationAddress;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<Object> getApproverCommentList() {
		return approverCommentList;
	}

	public void setApproverCommentList(List<Object> approverCommentList) {
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

	public String getPersDataChangeApproverId() {
		return persDataChangeApproverId;
	}

	public void setPersDataChangeApproverId(String persDataChangeApproverId) {
		this.persDataChangeApproverId = persDataChangeApproverId;
	}
}