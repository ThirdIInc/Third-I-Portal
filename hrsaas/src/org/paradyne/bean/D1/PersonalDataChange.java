package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.List;
import org.paradyne.lib.BeanBase;

/**
 * Bhushan Dasare Feb 15, 2011
 */

public class PersonalDataChange extends BeanBase {

	// added by bhushan start
	private boolean forApproval;
	private String persDataChangeId;
	private String approverComments;
	private boolean readOnlyDetails;
	private String relationAddress;
	private String level;
	private List<Object> approverCommentList;
	private String apprName;
	private String apprComments;
	private String apprDate;
	private String apprStatus;
	// added by bhushan end

	// added by ganesh start
	private String firstApproverCode = "";
	private String firstApproverToken = "";
	private String personalDataId = "";
	private String employeeCode = "";
	private String empToken = "";
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
	private String empName = "";
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
	private String listType = "";
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
	private boolean draftVoucherListLength = false;
	ArrayList inProcessVoucherIteratorList = null;
	private boolean inProcessVoucherListLength = false;
	ArrayList sentBackVoucherIteratorList = null;
	private boolean sentBackVoucherListLength = false;
	ArrayList approvedVoucherIteratorList = null;
	private boolean approvedVoucherListLength = false;
	ArrayList approvedCancellationVoucherIteratorList = null;
	private boolean approvedCancellationVoucherListLength = false;
	ArrayList cancelledVoucherIteratorList = null;
	private boolean cancelledVoucherListLength = false;
	ArrayList rejectedVoucherIteratorList = null;
	private boolean rejectedVoucherListLength = false;
	ArrayList rejectedCancelVoucherIteratorList = null;
	private boolean rejectedCancelVoucherListLength = false;
	
	private String secondApproverId="";
	private String secondApproverToken = "";
	private String secondApproverName = "";
	boolean approverCommentFlag = false;
	public String trackingNo="";
	private String initiatorCode = "";
	private String initiatorDate = "";
	private String initiatorToken = "";
	private String initiatorName = "";
	private String applicationDate = "";
	private String streetAddress = "";
	
	private String deptCode= "";
	private String deptName= "";
	private String emailAddress="";
	private String empDeptCode="";
	private String empDeptName="";

	// added by ganesh end

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

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public boolean isApproverCommentFlag() {
		return approverCommentFlag;
	}

	public void setApproverCommentFlag(boolean approverCommentFlag) {
		this.approverCommentFlag = approverCommentFlag;
	}

	/**
	 * @return the applnStatus
	 */
	public String getApplnStatus() {
		return applnStatus;
	}

	/**
	 * @return the apprComments
	 */
	public String getApprComments() {
		return apprComments;
	}

	/**
	 * @return the apprDate
	 */
	public String getApprDate() {
		return apprDate;
	}

	/**
	 * @return the apprName
	 */
	public String getApprName() {
		return apprName;
	}

	public String getApproveCancelLength() {
		return approveCancelLength;
	}

	/**
	 * @return the approvedCancellationList
	 */
	public ArrayList<Object> getApprovedCancellationList() {
		return approvedCancellationList;
	}

	public ArrayList getApprovedCancellationVoucherIteratorList() {
		return approvedCancellationVoucherIteratorList;
	}

	/**
	 * @return the approvedList
	 */
	public ArrayList<Object> getApprovedList() {
		return approvedList;
	}

	public ArrayList getApprovedVoucherIteratorList() {
		return approvedVoucherIteratorList;
	}

	public String getApproveLength() {
		return approveLength;
	}

	/**
	 * @return the approverCode
	 */
	public String getApproverCode() {
		return approverCode;
	}

	/**
	 * @return the approverCommentList
	 */
	public List<Object> getApproverCommentList() {
		return approverCommentList;
	}

	/**
	 * @return the approverComments
	 */
	public String getApproverComments() {
		return approverComments;
	}

	public ArrayList getApproverList() {
		return approverList;
	}

	/**
	 * @return the approverName
	 */
	public String getApproverName() {
		return approverName;
	}

	/**
	 * @return the approverToken
	 */
	public String getApproverToken() {
		return approverToken;
	}

	/**
	 * @return the apprStatus
	 */
	public String getApprStatus() {
		return apprStatus;
	}

	/**
	 * @return the areaType
	 */
	public String getAreaType() {
		return areaType;
	}

	public String getCancelledLength() {
		return cancelledLength;
	}

	/**
	 * @return the cancelledList
	 */
	public ArrayList<Object> getCancelledList() {
		return cancelledList;
	}

	public ArrayList getCancelledVoucherIteratorList() {
		return cancelledVoucherIteratorList;
	}

	/**
	 * @return the checkStatus
	 */
	public String getCheckStatus() {
		return checkStatus;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the deptNumber
	 */
	public String getDeptNumber() {
		return deptNumber;
	}

	public String getDraftLength() {
		return draftLength;
	}

	/**
	 * @return the draftList
	 */
	public ArrayList<Object> getDraftList() {
		return draftList;
	}

	public ArrayList getDraftVoucherIteratorList() {
		return draftVoucherIteratorList;
	}

	/**
	 * @return the emergencyContact
	 */
	public String getEmergencyContact() {
		return emergencyContact;
	}

	/**
	 * @return the emergencyName
	 */
	public String getEmergencyName() {
		return emergencyName;
	}

	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @return the employeeToken
	 */
	public String getEmployeeToken() {
		return employeeToken;
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
	 * @return the firstApproverCode
	 */
	public String getFirstApproverCode() {
		return firstApproverCode;
	}

	public String getFirstApproverToken() {
		return firstApproverToken;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the hiddenCode
	 */
	public String getHiddenCode() {
		return hiddenCode;
	}

	/**
	 * @return the homePhoneNumber
	 */
	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}

	/**
	 * @return the homePhoneNumberEmergency
	 */
	public String getHomePhoneNumberEmergency() {
		return homePhoneNumberEmergency;
	}

	/**
	 * @return the hrApproverEmpId
	 */
	public String getHrApproverEmpId() {
		return hrApproverEmpId;
	}

	/**
	 * @return the hrApproverEmpName
	 */
	public String getHrApproverEmpName() {
		return hrApproverEmpName;
	}

	/**
	 * @return the hrApproverEmpToken
	 */
	public String getHrApproverEmpToken() {
		return hrApproverEmpToken;
	}

	/**
	 * @return the initialName
	 */
	public String getInitialName() {
		return initialName;
	}

	public ArrayList getInProcessVoucherIteratorList() {
		return inProcessVoucherIteratorList;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @return the listType
	 */
	public String getListType() {
		return listType;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}

	/**
	 * @return the moveDate
	 */
	public String getMoveDate() {
		return moveDate;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	public String getMyPageApproved() {
		return myPageApproved;
	}

	public String getMyPageApprovedCancel() {
		return myPageApprovedCancel;
	}

	public String getMyPageCancel() {
		return myPageCancel;
	}

	public String getMyPageCancelApproved() {
		return myPageCancelApproved;
	}

	public String getMyPageCancelled() {
		return myPageCancelled;
	}

	public String getMyPageCancelRejected() {
		return myPageCancelRejected;
	}

	public String getMyPageDraft() {
		return myPageDraft;
	}

	public String getMyPageDrafted() {
		return myPageDrafted;
	}

	public String getMyPageInProcess() {
		return myPageInProcess;
	}

	public String getMyPagePending() {
		return myPagePending;
	}

	public String getMyPageRejected() {
		return myPageRejected;
	}

	public String getMyPageSendBack() {
		return myPageSendBack;
	}

	public String getMyPageSentBack() {
		return myPageSentBack;
	}

	public String getPendingLength() {
		return pendingLength;
	}

	/**
	 * @return the pendingList
	 */
	public ArrayList<Object> getPendingList() {
		return pendingList;
	}

	/**
	 * @return the persDataChangeId
	 */
	public String getPersDataChangeId() {
		return persDataChangeId;
	}

	/**
	 * @return the personalDataId
	 */
	public String getPersonalDataId() {
		return personalDataId;
	}

	public ArrayList<Object> getRejectedCancellationList() {
		return rejectedCancellationList;
	}

	public ArrayList getRejectedCancelVoucherIteratorList() {
		return rejectedCancelVoucherIteratorList;
	}

	/**
	 * @return the rejectedList
	 */
	public ArrayList<Object> getRejectedList() {
		return rejectedList;
	}

	public ArrayList getRejectedVoucherIteratorList() {
		return rejectedVoucherIteratorList;
	}

	/**
	 * @return the relationAddress
	 */
	public String getRelationAddress() {
		return relationAddress;
	}

	/**
	 * @return the relationCode
	 */
	public String getRelationCode() {
		return relationCode;
	}

	/**
	 * @return the relationName
	 */
	public String getRelationName() {
		return relationName;
	}

	/**
	 * @return the relationType
	 */
	public String getRelationType() {
		return relationType;
	}

	/**
	 * @return the sameAddressType
	 */
	public String getSameAddressType() {
		return sameAddressType;
	}

	/**
	 * @return the selectapproverName
	 */
	public String getSelectapproverName() {
		return selectapproverName;
	}

	public String getSendbackLength() {
		return sendbackLength;
	}

	/**
	 * @return the sendBackList
	 */
	public ArrayList<Object> getSendBackList() {
		return sendBackList;
	}

	public ArrayList getSentBackVoucherIteratorList() {
		return sentBackVoucherIteratorList;
	}

	public String getSrNoIterator() {
		return srNoIterator;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @return the submitList
	 */
	public ArrayList<Object> getSubmitList() {
		return submitList;
	}

	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @return the workPhone
	 */
	public String getWorkPhone() {
		return workPhone;
	}

	/**
	 * @return the workPhoneNumber
	 */
	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}

	/**
	 * @return the workPhoneNumberEmergency
	 */
	public String getWorkPhoneNumberEmergency() {
		return workPhoneNumberEmergency;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @return the approvedCancellationFlag
	 */
	public boolean isApprovedCancellationFlag() {
		return approvedCancellationFlag;
	}

	public boolean isApprovedCancellationVoucherListLength() {
		return approvedCancellationVoucherListLength;
	}

	/**
	 * @return the approvedFlag
	 */
	public boolean isApprovedFlag() {
		return approvedFlag;
	}

	public boolean isApprovedVoucherListLength() {
		return approvedVoucherListLength;
	}

	public boolean isCancelledVoucherListLength() {
		return cancelledVoucherListLength;
	}

	public boolean isDraftVoucherListLength() {
		return draftVoucherListLength;
	}

	/**
	 * @return the forApproval
	 */
	public boolean isForApproval() {
		return forApproval;
	}

	public boolean isInProcessVoucherListLength() {
		return inProcessVoucherListLength;
	}

	public boolean isPendingFlag() {
		return pendingFlag;
	}

	/**
	 * @return the readOnlyDetails
	 */
	public boolean isReadOnlyDetails() {
		return readOnlyDetails;
	}

	public boolean isRejectedCancelVoucherListLength() {
		return rejectedCancelVoucherListLength;
	}

	public boolean isRejectedVoucherListLength() {
		return rejectedVoucherListLength;
	}

	/**
	 * @return the revokeFlag
	 */
	public boolean isRevokeFlag() {
		return revokeFlag;
	}

	public boolean isSentBackVoucherListLength() {
		return sentBackVoucherListLength;
	}

	/**
	 * @param applnStatus the applnStatus to set
	 */
	public void setApplnStatus(String applnStatus) {
		this.applnStatus = applnStatus;
	}

	/**
	 * @param apprComments the apprComments to set
	 */
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}

	/**
	 * @param apprDate the apprDate to set
	 */
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}

	/**
	 * @param apprName the apprName to set
	 */
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

	public void setApproveCancelLength(String approveCancelLength) {
		this.approveCancelLength = approveCancelLength;
	}

	/**
	 * @param approvedCancellationFlag the approvedCancellationFlag to set
	 */
	public void setApprovedCancellationFlag(boolean approvedCancellationFlag) {
		this.approvedCancellationFlag = approvedCancellationFlag;
	}

	/**
	 * @param approvedCancellationList the approvedCancellationList to set
	 */
	public void setApprovedCancellationList(ArrayList<Object> approvedCancellationList) {
		this.approvedCancellationList = approvedCancellationList;
	}

	public void setApprovedCancellationVoucherIteratorList(ArrayList approvedCancellationVoucherIteratorList) {
		this.approvedCancellationVoucherIteratorList = approvedCancellationVoucherIteratorList;
	}

	public void setApprovedCancellationVoucherListLength(boolean approvedCancellationVoucherListLength) {
		this.approvedCancellationVoucherListLength = approvedCancellationVoucherListLength;
	}

	/**
	 * @param approvedFlag the approvedFlag to set
	 */
	public void setApprovedFlag(boolean approvedFlag) {
		this.approvedFlag = approvedFlag;
	}

	/**
	 * @param approvedList the approvedList to set
	 */
	public void setApprovedList(ArrayList<Object> approvedList) {
		this.approvedList = approvedList;
	}

	public void setApprovedVoucherIteratorList(ArrayList approvedVoucherIteratorList) {
		this.approvedVoucherIteratorList = approvedVoucherIteratorList;
	}

	public void setApprovedVoucherListLength(boolean approvedVoucherListLength) {
		this.approvedVoucherListLength = approvedVoucherListLength;
	}

	public void setApproveLength(String approveLength) {
		this.approveLength = approveLength;
	}

	/**
	 * @param approverCode the approverCode to set
	 */
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}

	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(List<Object> approverCommentList) {
		this.approverCommentList = approverCommentList;
	}

	/**
	 * @param approverComments the approverComments to set
	 */
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}

	/**
	 * @param approverName the approverName to set
	 */
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	/**
	 * @param approverToken the approverToken to set
	 */
	public void setApproverToken(String approverToken) {
		this.approverToken = approverToken;
	}

	/**
	 * @param apprStatus the apprStatus to set
	 */
	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}

	/**
	 * @param areaType the areaType to set
	 */
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public void setCancelledLength(String cancelledLength) {
		this.cancelledLength = cancelledLength;
	}

	/**
	 * @param cancelledList the cancelledList to set
	 */
	public void setCancelledList(ArrayList<Object> cancelledList) {
		this.cancelledList = cancelledList;
	}

	public void setCancelledVoucherIteratorList(ArrayList cancelledVoucherIteratorList) {
		this.cancelledVoucherIteratorList = cancelledVoucherIteratorList;
	}

	public void setCancelledVoucherListLength(boolean cancelledVoucherListLength) {
		this.cancelledVoucherListLength = cancelledVoucherListLength;
	}

	/**
	 * @param checkStatus the checkStatus to set
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param deptNumber the deptNumber to set
	 */
	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}

	public void setDraftLength(String draftLength) {
		this.draftLength = draftLength;
	}

	/**
	 * @param draftList the draftList to set
	 */
	public void setDraftList(ArrayList<Object> draftList) {
		this.draftList = draftList;
	}

	public void setDraftVoucherIteratorList(ArrayList draftVoucherIteratorList) {
		this.draftVoucherIteratorList = draftVoucherIteratorList;
	}

	public void setDraftVoucherListLength(boolean draftVoucherListLength) {
		this.draftVoucherListLength = draftVoucherListLength;
	}

	/**
	 * @param emergencyContact the emergencyContact to set
	 */
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	/**
	 * @param emergencyName the emergencyName to set
	 */
	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @param employeeCode the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @param employeeToken the employeeToken to set
	 */
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
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
	 * @param firstApproverCode the firstApproverCode to set
	 */
	public void setFirstApproverCode(String firstApproverCode) {
		this.firstApproverCode = firstApproverCode;
	}

	public void setFirstApproverToken(String firstApproverToken) {
		this.firstApproverToken = firstApproverToken;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param forApproval the forApproval to set
	 */
	public void setForApproval(boolean forApproval) {
		this.forApproval = forApproval;
	}

	/**
	 * @param hiddenCode the hiddenCode to set
	 */
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}

	/**
	 * @param homePhoneNumber the homePhoneNumber to set
	 */
	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}

	/**
	 * @param homePhoneNumberEmergency the homePhoneNumberEmergency to set
	 */
	public void setHomePhoneNumberEmergency(String homePhoneNumberEmergency) {
		this.homePhoneNumberEmergency = homePhoneNumberEmergency;
	}

	/**
	 * @param hrApproverEmpId the hrApproverEmpId to set
	 */
	public void setHrApproverEmpId(String hrApproverEmpId) {
		this.hrApproverEmpId = hrApproverEmpId;
	}

	/**
	 * @param hrApproverEmpName the hrApproverEmpName to set
	 */
	public void setHrApproverEmpName(String hrApproverEmpName) {
		this.hrApproverEmpName = hrApproverEmpName;
	}

	/**
	 * @param hrApproverEmpToken the hrApproverEmpToken to set
	 */
	public void setHrApproverEmpToken(String hrApproverEmpToken) {
		this.hrApproverEmpToken = hrApproverEmpToken;
	}

	/**
	 * @param initialName the initialName to set
	 */
	public void setInitialName(String initialName) {
		this.initialName = initialName;
	}

	public void setInProcessVoucherIteratorList(ArrayList inProcessVoucherIteratorList) {
		this.inProcessVoucherIteratorList = inProcessVoucherIteratorList;
	}

	public void setInProcessVoucherListLength(boolean inProcessVoucherListLength) {
		this.inProcessVoucherListLength = inProcessVoucherListLength;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @param listType the listType to set
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	/**
	 * @param moveDate the moveDate to set
	 */
	public void setMoveDate(String moveDate) {
		this.moveDate = moveDate;
	}

	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}

	public void setMyPageApprovedCancel(String myPageApprovedCancel) {
		this.myPageApprovedCancel = myPageApprovedCancel;
	}

	public void setMyPageCancel(String myPageCancel) {
		this.myPageCancel = myPageCancel;
	}

	public void setMyPageCancelApproved(String myPageCancelApproved) {
		this.myPageCancelApproved = myPageCancelApproved;
	}

	public void setMyPageCancelled(String myPageCancelled) {
		this.myPageCancelled = myPageCancelled;
	}

	public void setMyPageCancelRejected(String myPageCancelRejected) {
		this.myPageCancelRejected = myPageCancelRejected;
	}

	public void setMyPageDraft(String myPageDraft) {
		this.myPageDraft = myPageDraft;
	}

	public void setMyPageDrafted(String myPageDrafted) {
		this.myPageDrafted = myPageDrafted;
	}

	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}

	public void setMyPagePending(String myPagePending) {
		this.myPagePending = myPagePending;
	}

	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}

	public void setMyPageSendBack(String myPageSendBack) {
		this.myPageSendBack = myPageSendBack;
	}

	public void setMyPageSentBack(String myPageSentBack) {
		this.myPageSentBack = myPageSentBack;
	}

	public void setPendingFlag(boolean pendingFlag) {
		this.pendingFlag = pendingFlag;
	}

	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}

	/**
	 * @param pendingList the pendingList to set
	 */
	public void setPendingList(ArrayList<Object> pendingList) {
		this.pendingList = pendingList;
	}

	/**
	 * @param persDataChangeId the persDataChangeId to set
	 */
	public void setPersDataChangeId(String persDataChangeId) {
		this.persDataChangeId = persDataChangeId;
	}

	/**
	 * @param personalDataId the personalDataId to set
	 */
	public void setPersonalDataId(String personalDataId) {
		this.personalDataId = personalDataId;
	}

	/**
	 * @param readOnlyDetails the readOnlyDetails to set
	 */
	public void setReadOnlyDetails(boolean readOnlyDetails) {
		this.readOnlyDetails = readOnlyDetails;
	}

	public void setRejectedCancellationList(ArrayList<Object> rejectedCancellationList) {
		this.rejectedCancellationList = rejectedCancellationList;
	}

	public void setRejectedCancelVoucherIteratorList(ArrayList rejectedCancelVoucherIteratorList) {
		this.rejectedCancelVoucherIteratorList = rejectedCancelVoucherIteratorList;
	}

	public void setRejectedCancelVoucherListLength(boolean rejectedCancelVoucherListLength) {
		this.rejectedCancelVoucherListLength = rejectedCancelVoucherListLength;
	}

	/**
	 * @param rejectedList the rejectedList to set
	 */
	public void setRejectedList(ArrayList<Object> rejectedList) {
		this.rejectedList = rejectedList;
	}

	public void setRejectedVoucherIteratorList(ArrayList rejectedVoucherIteratorList) {
		this.rejectedVoucherIteratorList = rejectedVoucherIteratorList;
	}

	public void setRejectedVoucherListLength(boolean rejectedVoucherListLength) {
		this.rejectedVoucherListLength = rejectedVoucherListLength;
	}

	/**
	 * @param relationAddress the relationAddress to set
	 */
	public void setRelationAddress(String relationAddress) {
		this.relationAddress = relationAddress;
	}

	/**
	 * @param relationCode the relationCode to set
	 */
	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	/**
	 * @param relationName the relationName to set
	 */
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	/**
	 * @param relationType the relationType to set
	 */
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	/**
	 * @param revokeFlag the revokeFlag to set
	 */
	public void setRevokeFlag(boolean revokeFlag) {
		this.revokeFlag = revokeFlag;
	}

	/**
	 * @param sameAddressType the sameAddressType to set
	 */
	public void setSameAddressType(String sameAddressType) {
		this.sameAddressType = sameAddressType;
	}

	/**
	 * @param selectapproverName the selectapproverName to set
	 */
	public void setSelectapproverName(String selectapproverName) {
		this.selectapproverName = selectapproverName;
	}

	public void setSendbackLength(String sendbackLength) {
		this.sendbackLength = sendbackLength;
	}

	/**
	 * @param sendBackList the sendBackList to set
	 */
	public void setSendBackList(ArrayList<Object> sendBackList) {
		this.sendBackList = sendBackList;
	}

	public void setSentBackVoucherIteratorList(ArrayList sentBackVoucherIteratorList) {
		this.sentBackVoucherIteratorList = sentBackVoucherIteratorList;
	}

	public void setSentBackVoucherListLength(boolean sentBackVoucherListLength) {
		this.sentBackVoucherListLength = sentBackVoucherListLength;
	}

	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @param submitList the submitList to set
	 */
	public void setSubmitList(ArrayList<Object> submitList) {
		this.submitList = submitList;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @param workPhone the workPhone to set
	 */
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	/**
	 * @param workPhoneNumber the workPhoneNumber to set
	 */
	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}

	/**
	 * @param workPhoneNumberEmergency the workPhoneNumberEmergency to set
	 */
	public void setWorkPhoneNumberEmergency(String workPhoneNumberEmergency) {
		this.workPhoneNumberEmergency = workPhoneNumberEmergency;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	public boolean isSecondAppFlag() {
		return secondAppFlag;
	}

	public void setSecondAppFlag(boolean secondAppFlag) {
		this.secondAppFlag = secondAppFlag;
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

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

}