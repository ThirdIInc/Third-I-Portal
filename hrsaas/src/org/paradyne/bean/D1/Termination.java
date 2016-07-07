package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;
/** * @author aa1380 : Manish Sakpal. * */
public class Termination extends BeanBase {
	/** * terminationID.*/
	private String terminationID = "";
	/** * status.*/
	private String status = "";
	/** * listType.*/
	private String listType = "";
	/** * myPage.*/
	private String myPage = "";
	/** * myPageInProcess.*/
	private String myPageInProcess = "";
	/** * myPageSentBack.*/
	private String myPageSentBack = "";
	/** * myPageApproved.*/
	private String myPageApproved = "";
	/** * myPageRejected.*/
	private String myPageRejected = "";
	/** * draftVoucherIteratorList.*/
	private List<Termination> draftVoucherIteratorList;
	/** * draftVoucherListLength.*/
	private boolean draftVoucherListLength;
	/** * inProcessVoucherIteratorList.*/
	private List<Termination> inProcessVoucherIteratorList;
	/** * inProcessVoucherListLength.*/
	private boolean inProcessVoucherListLength;
	/** * sentBackVoucherIteratorList.*/
	private List<Termination> sentBackVoucherIteratorList;
	/** * sentBackVoucherListLength.*/
	private boolean sentBackVoucherListLength;
	/** * approvedVoucherIteratorList.*/
	private List<Termination> approvedVoucherIteratorList;
	/** * approvedVoucherListLength.*/
	private boolean approvedVoucherListLength;
	/** * rejectedVoucherIteratorList.*/
	private List<Termination> rejectedVoucherIteratorList;
	/** * rejectedVoucherListLength.*/
	private boolean rejectedVoucherListLength;
	
	/** * terminationHiddenID.*/	
	private String terminationHiddenID = "";
	/** * hiddenStatus.*/
	private String hiddenStatus = "";
	/** * trackingNumIterator.*/
	private String trackingNumIterator = "";
	/** * employeeNameIterator.*/
	private String employeeNameIterator = "";
	/** * terminationDateIterator.*/
	private String terminationDateIterator = "";
	
	/** * employeeNumber.*/
	private String employeeNumber = "";
	/** * employeeToken.*/
	private String employeeToken = "";
	/** * employeeName.*/
	private String employeeName = "";
	/** * homeAddress.*/
	private String homeAddress = "";
	/** * zipCode.*/
	private String zipCode = "";
	/** * cityID.*/
	private String cityID = "";
	/** * cityName.*/
	private String cityName = "";
	/** * state.*/
	private String state = "";
	/** * terminationDate.*/
	private String terminationDate = "";
	/** * lastDayWorkDate.*/
	private String lastDayWorkDate = "";
	/** * ifTerDateAndLastDayWorkDateDiffer.*/
	private String ifTerDateAndLastDayWorkDateDiffer = "";
	/** * terminationType.*/
	private String terminationType = "";
	/** * terminationTypeRadioOptionValue.*/
	private String terminationTypeRadioOptionValue = "";
	/** * terminationReason.*/
	private String terminationReason = "";
	
	/** * voluntaryTerminationReason.*/
	private String voluntaryTerminationReason = "";
	/** * inVoluntaryTerminationReason.*/
	private String inVoluntaryTerminationReason = "";
	/** * otherTerminationReason.*/
	private String otherTerminationReason = "";
	
	/** * eligibleToRehire.*/
	private String eligibleToRehire = "";
	/** * ifNoOrProvisional.*/
	private String ifNoOrProvisional = "";
	/** * deptID.*/
	private String deptID = "";
	/** * depetNumber.*/
	private String depetNumber = "";
	
	/** * executiveID.*/
	private String executiveID = "";
	/** * executiveName.*/
	private String executiveName = "";
	/** * managerName.*/
	private String managerName = "";
	/** * managerPhone.*/
	private String managerPhone = "";
	/** * vacationHrsTaken.*/
	private String vacationHrsTaken = "";

	/** * commentsEntered.*/
	private String commentsEntered = "";
	/** * completedByID.*/
	private String completedByID = "";
	/** * completedByName.*/	
	private String completedByName = "";
	/** * completedDate.*/
	private String completedDate = "";
	
	/** * ifOtherTerminationReasonTextArea.*/	
	private String ifOtherTerminationReasonTextArea = "";
	/** * applicationStatus.*/
	private String applicationStatus = "";	
	/** * trackingNumber.*/
	private String trackingNumber = "";
	
	/** * approverCommentFlag.*/
	private boolean approverCommentFlag = false;
	/** * approverComments.*/
	private String approverComments = "";
	/** * apprName.*/
	private String apprName = "";	
	/** * apprComments.*/
	private String apprComments = "";
	/** * apprDate.*/
	private String apprDate = "";
	/** * apprStatus.*/
	private String apprStatus = "";
	/** * approverCommentList.*/
	private List<Termination> approverCommentList;
	/** * managerToken.*/
	private String managerToken = "";
	/** * managerCode.*/
	private String managerCode = "";
	
	/**
	 * @return the approverComments
	 */
	public String getApproverComments() {
		return this.approverComments;
	}
	/**
	 * @param approverComments the approverComments to set
	 */
	public void setApproverComments(final String approverComments) {
		this.approverComments = approverComments;
	}
	/**
	 * @return the apprName
	 */
	public String getApprName() {
		return this.apprName;
	}
	/** 
	 * @param apprName the apprName to set
	 */
	public void setApprName(final String apprName) {
		this.apprName = apprName;
	}
	/**
	 * @return the apprComments
	 */
	public String getApprComments() {
		return this.apprComments;
	}
	/**
	 * @param apprComments the apprComments to set
	 */
	public void setApprComments(final String apprComments) {
		this.apprComments = apprComments;
	}
	/**
	 * @return the apprDate
	 */
	public String getApprDate() {
		return this.apprDate;
	}
	/**
	 * @param apprDate the apprDate to set
	 */
	public void setApprDate(final String apprDate) {
		this.apprDate = apprDate;
	}
	/**
	 * @return the apprStatus
	 */
	public String getApprStatus() {
		return this.apprStatus;
	}
	/**
	 * @param apprStatus the apprStatus to set
	 */
	public void setApprStatus(final String apprStatus) {
		this.apprStatus = apprStatus;
	}
	/**
	 * @return the approverCommentList
	 */
	public List<Termination> getApproverCommentList() {
		return this.approverCommentList;
	}
	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(final List<Termination> approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	/**
	 * @return the trackingNumber
	 */
	public String getTrackingNumber() {
		return this.trackingNumber;
	}
	/**
	 * @param trackingNumber the trackingNumber to set
	 */
	public void setTrackingNumber(final String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	/**
	 * @return the applicationStatus
	 */
	public String getApplicationStatus() {
		return this.applicationStatus;
	}
	/**
	 * @param applicationStatus the applicationStatus to set
	 */
	public void setApplicationStatus(final String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	/**
	 * @return the terminationID
	 */
	public String getTerminationID() {
		return this.terminationID;
	}
	/**
	 * @param terminationID the terminationID
	 */
	public void setTerminationID(final String terminationID) {
		this.terminationID = terminationID;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return this.myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(final String myPage) {
		this.myPage = myPage;
	}
	
	/**
	 * @return the terminationHiddenID
	 */
	public String getTerminationHiddenID() {
		return this.terminationHiddenID;
	}
	/**
	 * @param terminationHiddenID the terminationHiddenID to set
	 */
	public void setTerminationHiddenID(final String terminationHiddenID) {
		this.terminationHiddenID = terminationHiddenID;
	}
	/**
	 * @return the hiddenStatus
	 */
	public String getHiddenStatus() {
		return this.hiddenStatus;
	}
	/**
	 * @param hiddenStatus the hiddenStatus to set
	 */
	public void setHiddenStatus(final String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	
	/**
	 * @return the trackingNumIterator
	 */
	public String getTrackingNumIterator() {
		return this.trackingNumIterator;
	}
	/**
	 * @param trackingNumIterator the trackingNumIterator to set
	 */
	public void setTrackingNumIterator(final String trackingNumIterator) {
		this.trackingNumIterator = trackingNumIterator;
	}
	/**
	 * @return the employeeNameIterator
	 */
	public String getEmployeeNameIterator() {
		return this.employeeNameIterator;
	}
	/**
	 * @param employeeNameIterator the employeeNameIterator to set
	 */
	public void setEmployeeNameIterator(final String employeeNameIterator) {
		this.employeeNameIterator = employeeNameIterator;
	}
	/**
	 * @return the terminationDateIterator
	 */
	public String getTerminationDateIterator() {
		return this.terminationDateIterator;
	}
	/**
	 * @param terminationDateIterator the terminationDateIterator to set
	 */
	public void setTerminationDateIterator(final String terminationDateIterator) {
		this.terminationDateIterator = terminationDateIterator;
	}
	/**
	 * @return the commentsEntered
	 */
	public String getCommentsEntered() {
		return this.commentsEntered;
	}
	/**
	 * @param commentsEntered the commentsEntered to set
	 */
	public void setCommentsEntered(final String commentsEntered) {
		this.commentsEntered = commentsEntered;
	}
	/**
	 * @return the completedByID
	 */
	public String getCompletedByID() {
		return this.completedByID;
	}
	/**
	 * @param completedByID the completedByID to set
	 */
	public void setCompletedByID(final String completedByID) {
		this.completedByID = completedByID;
	}
	/**
	 * @return the completedByName
	 */
	public String getCompletedByName() {
		return this.completedByName;
	}
	/**
	 * @param completedByName the completedByName to set
	 */
	public void setCompletedByName(final String completedByName) {
		this.completedByName = completedByName;
	}
	/**
	 * @return the completedDate
	 */
	public String getCompletedDate() {
		return this.completedDate;
	}
	/**
	 * @param completedDate the completedDate to set
	 */
	public void setCompletedDate(final String completedDate) {
		this.completedDate = completedDate;
	}
	/**
	 * @return the employeeNumber
	 */
	public String getEmployeeNumber() {
		return this.employeeNumber;
	}
	/**
	 * @param employeeNumber the employeeNumber to set
	 */
	public void setEmployeeNumber(final String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	/**
	 * @return the employeeToken
	 */
	public String getEmployeeToken() {
		return this.employeeToken;
	}
	/**
	 * @param employeeToken the employeeToken to set
	 */
	public void setEmployeeToken(final String employeeToken) {
		this.employeeToken = employeeToken;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return this.employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(final String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the homeAddress
	 */
	public String getHomeAddress() {
		return this.homeAddress;
	}
	/**
	 * @param homeAddress the homeAddress to set
	 */
	public void setHomeAddress(final String homeAddress) {
		this.homeAddress = homeAddress;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return this.zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the cityID
	 */
	public String getCityID() {
		return this.cityID;
	}
	/**
	 * @param cityID the cityID to set
	 */
	public void setCityID(final String cityID) {
		this.cityID = cityID;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return this.cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(final String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}
	/**
	 * @return the terminationDate
	 */
	public String getTerminationDate() {
		return this.terminationDate;
	}
	/**
	 * @param terminationDate the terminationDate to set
	 */
	public void setTerminationDate(final String terminationDate) {
		this.terminationDate = terminationDate;
	}
	/**
	 * @return the lastDayWorkDate
	 */
	public String getLastDayWorkDate() {
		return this.lastDayWorkDate;
	}
	/**
	 * @param lastDayWorkDate the lastDayWorkDate to set
	 */
	public void setLastDayWorkDate(final String lastDayWorkDate) {
		this.lastDayWorkDate = lastDayWorkDate;
	}
	/**
	 * @return the ifTerDateAndLastDayWorkDateDiffer
	 */
	public String getIfTerDateAndLastDayWorkDateDiffer() {
		return this.ifTerDateAndLastDayWorkDateDiffer;
	}
	/**
	 * @param ifTerDateAndLastDayWorkDateDiffer the ifTerDateAndLastDayWorkDateDiffer to set
	 */
	public void setIfTerDateAndLastDayWorkDateDiffer(
			final String ifTerDateAndLastDayWorkDateDiffer) {
		this.ifTerDateAndLastDayWorkDateDiffer = ifTerDateAndLastDayWorkDateDiffer;
	}
	/**
	 * @return the terminationType
	 */
	public String getTerminationType() {
		return this.terminationType;
	}
	/**
	 * @param terminationType the terminationType to set
	 */
	public void setTerminationType(final String terminationType) {
		this.terminationType = terminationType;
	}
	/**
	 * @return the terminationTypeRadioOptionValue
	 */
	public String getTerminationTypeRadioOptionValue() {
		return this.terminationTypeRadioOptionValue;
	}
	/**
	 * @param terminationTypeRadioOptionValue the terminationTypeRadioOptionValue to set
	 */
	public void setTerminationTypeRadioOptionValue(
			final String terminationTypeRadioOptionValue) {
		this.terminationTypeRadioOptionValue = terminationTypeRadioOptionValue;
	}
	/**
	 * @return the terminationReason
	 */
	public String getTerminationReason() {
		return this.terminationReason;
	}
	/**
	 * @param terminationReason the terminationReason to set
	 */
	public void setTerminationReason(final String terminationReason) {
		this.terminationReason = terminationReason;
	}
	/**
	 * @return the eligibleToRehire
	 */
	public String getEligibleToRehire() {
		return this.eligibleToRehire;
	}
	/**
	 * @param eligibleToRehire the eligibleToRehire to set
	 */
	public void setEligibleToRehire(final String eligibleToRehire) {
		this.eligibleToRehire = eligibleToRehire;
	}
	/**
	 * @return the ifNoOrProvisional
	 */
	public String getIfNoOrProvisional() {
		return this.ifNoOrProvisional;
	}
	/**
	 * @param ifNoOrProvisional the ifNoOrProvisional to set
	 */
	public void setIfNoOrProvisional(final String ifNoOrProvisional) {
		this.ifNoOrProvisional = ifNoOrProvisional;
	}
	/**
	 * @return the deptID
	 */
	public String getDeptID() {
		return this.deptID;
	}
	/**
	 * @param deptID the deptID to set
	 */
	public void setDeptID(final String deptID) {
		this.deptID = deptID;
	}
	/**
	 * @return the depetNumber
	 */
	public String getDepetNumber() {
		return this.depetNumber;
	}
	/**
	 * @param depetNumber the depetNumber to set
	 */
	public void setDepetNumber(final String depetNumber) {
		this.depetNumber = depetNumber;
	}
	/**
	 * @return the executiveID
	 */
	public String getExecutiveID() {
		return this.executiveID;
	}
	/**
	 * @param executiveID the executiveID to set
	 */
	public void setExecutiveID(final String executiveID) {
		this.executiveID = executiveID;
	}
	/**
	 * @return the executiveName
	 */
	public String getExecutiveName() {
		return this.executiveName;
	}
	/**
	 * @param executiveName the executiveName to set
	 */
	public void setExecutiveName(final String executiveName) {
		this.executiveName = executiveName;
	}
	/**
	 * @return the managerName
	 */
	public String getManagerName() {
		return this.managerName;
	}
	/**
	 * @param managerName the managerName to set
	 */
	public void setManagerName(final String managerName) {
		this.managerName = managerName;
	}
	/**
	 * @return the managerPhone
	 */
	public String getManagerPhone() {
		return this.managerPhone;
	}
	/**
	 * @param managerPhone the managerPhone to set
	 */
	public void setManagerPhone(final String managerPhone) {
		this.managerPhone = managerPhone;
	}
	/**
	 * @return the vacationHrsTaken
	 */
	public String getVacationHrsTaken() {
		return this.vacationHrsTaken;
	}
	/**
	 * @param vacationHrsTaken the vacationHrsTaken to set
	 */
	public void setVacationHrsTaken(final String vacationHrsTaken) {
		this.vacationHrsTaken = vacationHrsTaken;
	}
	/**
	 * @return the voluntaryTerminationReason
	 */
	public String getVoluntaryTerminationReason() {
		return this.voluntaryTerminationReason;
	}
	/**
	 * @param voluntaryTerminationReason the voluntaryTerminationReason to set
	 */
	public void setVoluntaryTerminationReason(final String voluntaryTerminationReason) {
		this.voluntaryTerminationReason = voluntaryTerminationReason;
	}
	/**
	 * @return the inVoluntaryTerminationReason
	 */
	public String getInVoluntaryTerminationReason() {
		return this.inVoluntaryTerminationReason;
	}
	/**
	 * @param inVoluntaryTerminationReason the inVoluntaryTerminationReason to set
	 */
	public void setInVoluntaryTerminationReason(final String inVoluntaryTerminationReason) {
		this.inVoluntaryTerminationReason = inVoluntaryTerminationReason;
	}
	/**
	 * @return the otherTerminationReason
	 */
	public String getOtherTerminationReason() {
		return this.otherTerminationReason;
	}
	/**
	 * @param otherTerminationReason the otherTerminationReason to set
	 */
	public void setOtherTerminationReason(final String otherTerminationReason) {
		this.otherTerminationReason = otherTerminationReason;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * @return the listType
	 */
	public String getListType() {
		return this.listType;
	}
	/**
	 * @param listType the listType to set
	 */
	public void setListType(final String listType) {
		this.listType = listType;
	}
	
	/**
	 * @return the ifOtherTerminationReasonTextArea
	 */
	public String getIfOtherTerminationReasonTextArea() {
		return this.ifOtherTerminationReasonTextArea;
	}
	/**
	 * @param ifOtherTerminationReasonTextArea the ifOtherTerminationReasonTextArea to set
	 */
	public void setIfOtherTerminationReasonTextArea(
			final String ifOtherTerminationReasonTextArea) {
		this.ifOtherTerminationReasonTextArea = ifOtherTerminationReasonTextArea;
	}
	/**
	 * @return the myPageInProcess
	 */
	public String getMyPageInProcess() {
		return this.myPageInProcess;
	}
	/**
	 * @param myPageInProcess the myPageInProcess to set
	 */
	public void setMyPageInProcess(final String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}
	/**
	 * @return themyPageSentBack
	 */
	public String getMyPageSentBack() {
		return this.myPageSentBack;
	}
	/**
	 * @param myPageSentBack the myPageSentBack to set
	 */
	public void setMyPageSentBack(final String myPageSentBack) {
		this.myPageSentBack = myPageSentBack;
	}
	/**
	 * @return the myPageApproved
	 */
	public String getMyPageApproved() {
		return this.myPageApproved;
	}
	/**
	 * @param myPageApproved the myPageApproved to set
	 */
	public void setMyPageApproved(final String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}
	/**
	 * @return the myPageRejected
	 */
	public String getMyPageRejected() {
		return this.myPageRejected;
	}
	/**
	 * @param myPageRejected the myPageRejected to set
	 */
	public void setMyPageRejected(final String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	/**
	 * @return the draftVoucherIteratorList
	 */
	public List<Termination> getDraftVoucherIteratorList() {
		return this.draftVoucherIteratorList;
	}
	/**
	 * @param draftVoucherIteratorList the draftVoucherIteratorList to set
	 */
	public void setDraftVoucherIteratorList(final List<Termination> draftVoucherIteratorList) {
		this.draftVoucherIteratorList = draftVoucherIteratorList;
	}
	/**
	 * @return the draftVoucherListLength
	 */
	public boolean isDraftVoucherListLength() {
		return this.draftVoucherListLength;
	}
	/**
	 * @param draftVoucherListLength the draftVoucherListLength to set
	 */
	public void setDraftVoucherListLength(final boolean draftVoucherListLength) {
		this.draftVoucherListLength = draftVoucherListLength;
	}
	/**
	 * @return the inProcessVoucherIteratorList
	 */
	public List<Termination> getInProcessVoucherIteratorList() {
		return this.inProcessVoucherIteratorList;
	}
	/**
	 * @param inProcessVoucherIteratorList the inProcessVoucherIteratorList to set
	 */
	public void setInProcessVoucherIteratorList(
			final List<Termination> inProcessVoucherIteratorList) {
		this.inProcessVoucherIteratorList = inProcessVoucherIteratorList;
	}
	/**
	 * @return the inProcessVoucherListLength
	 */
	public boolean isInProcessVoucherListLength() {
		return this.inProcessVoucherListLength;
	}
	/**
	 * @param inProcessVoucherListLength the inProcessVoucherListLength to set
	 */
	public void setInProcessVoucherListLength(final boolean inProcessVoucherListLength) {
		this.inProcessVoucherListLength = inProcessVoucherListLength;
	}
	/**
	 * @return the sentBackVoucherIteratorList
	 */
	public List<Termination> getSentBackVoucherIteratorList() {
		return this.sentBackVoucherIteratorList;
	}
	/**
	 * @param sentBackVoucherIteratorList the sentBackVoucherIteratorList to set
	 */
	public void setSentBackVoucherIteratorList(final List<Termination> sentBackVoucherIteratorList) {
		this.sentBackVoucherIteratorList = sentBackVoucherIteratorList;
	}
	/**
	 * @return the sentBackVoucherListLength
	 */
	public boolean isSentBackVoucherListLength() {
		return this.sentBackVoucherListLength;
	}
	/**
	 * @param sentBackVoucherListLength the sentBackVoucherListLength to set
	 */
	public void setSentBackVoucherListLength(final boolean sentBackVoucherListLength) {
		this.sentBackVoucherListLength = sentBackVoucherListLength;
	}
	/**
	 * @return the approvedVoucherIteratorList 
	 */
	public List<Termination> getApprovedVoucherIteratorList() {
		return this.approvedVoucherIteratorList;
	}
	/**
	 * @param approvedVoucherIteratorList the approvedVoucherIteratorList to set
	 */
	public void setApprovedVoucherIteratorList(final List<Termination> approvedVoucherIteratorList) {
		this.approvedVoucherIteratorList = approvedVoucherIteratorList;
	}
	/**
	 * @return the approvedVoucherListLength
	 */
	public boolean isApprovedVoucherListLength() {
		return this.approvedVoucherListLength;
	}
	/**
	 * @param approvedVoucherListLength the approvedVoucherListLength to set
	 */
	public void setApprovedVoucherListLength(final boolean approvedVoucherListLength) {
		this.approvedVoucherListLength = approvedVoucherListLength;
	}
	/**
	 * @return the rejectedVoucherIteratorList
	 */
	public List<Termination> getRejectedVoucherIteratorList() {
		return this.rejectedVoucherIteratorList;
	}
	/**
	 * @param rejectedVoucherIteratorList the rejectedVoucherIteratorList to set
	 */
	public void setRejectedVoucherIteratorList(final List<Termination> rejectedVoucherIteratorList) {
		this.rejectedVoucherIteratorList = rejectedVoucherIteratorList;
	}
	/**
	 * @return the rejectedVoucherListLength
	 */
	public boolean isRejectedVoucherListLength() {
		return this.rejectedVoucherListLength;
	}
	/**
	 * @param rejectedVoucherListLength the rejectedVoucherListLength to set
	 */
	public void setRejectedVoucherListLength(final boolean rejectedVoucherListLength) {
		this.rejectedVoucherListLength = rejectedVoucherListLength;
	}
	/**
	 * @return the approverCommentFlag
	 */
	public boolean isApproverCommentFlag() {
		return this.approverCommentFlag;
	}
	/**
	 * @param approverCommentFlag the approverCommentFlag to set
	 */
	public void setApproverCommentFlag(final boolean approverCommentFlag) {
		this.approverCommentFlag = approverCommentFlag;
	}
        /**
         * @return the managerToken
         */
        public String getManagerToken() {
                return this.managerToken;
        }
        /**
         * @param managerToken the managerToken to set
         */
        public void setManagerToken(final String managerToken) {
                this.managerToken = managerToken;
        }
        /**
         * @return the managerCode 
         */
        public String getManagerCode() {
                return this.managerCode;
        }
        /**
         * @param managerCode the managerCode to set
         */
        public void setManagerCode(final String managerCode) {
                this.managerCode = managerCode;
        }
}
