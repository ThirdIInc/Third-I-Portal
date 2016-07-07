package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

public class TerminationApproval extends BeanBase {

	/** * listType. */
	private String listType = "";
	/** * myPage. */
	private String myPage = "";
	/** * myPagePendingCancel. */
	private String myPagePendingCancel = "";
	/** * myPageApproved. */
	private String myPageApproved = "";
	/** * myPageRejected. */
	private String myPageRejected = "";

	/** * pendingIteratorList. */
	private List<TerminationApproval> pendingIteratorList;
	/** * pendingListLength. */
	private boolean pendingListLength;
	/** * approveredIteratorList. */
	private List<TerminationApproval> approveredIteratorList;
	/** * approvedListLength. */
	private boolean approvedListLength;
	/** * rejectedIteratorList. */
	private List<TerminationApproval> rejectedIteratorList;
	/** * rejectedListLength. */
	private boolean rejectedListLength;

	/** * terminationHiddenID. */
	private String terminationHiddenID = "";
	/** * terminationHiddenStatus. */
	private String terminationHiddenStatus = "";
	/** * trackingNumIterator. */
	private String trackingNumIterator = "";
	/** * employeeNameIterator. */
	private String employeeNameIterator = "";
	/** * terminationDateIterator. */
	private String terminationDateIterator = "";

	/** * employeeNumber. */
	private String employeeNumber = "";
	/** * employeeToken. */
	private String employeeToken = "";
	/** * employeeName. */
	private String employeeName = "";
	/** * homeAddress. */
	private String homeAddress = "";
	/** * zipCode. */
	private String zipCode = "";
	/** * cityID. */
	private String cityID = "";
	/** * cityName. */
	private String cityName = "";
	/** * state. */
	private String state = "";
	/** * deptID. */
	private String deptID = "";
	/** * depetNumber. */
	private String depetNumber = "";
	/** * executiveID. */
	private String executiveID = "";
	/** * executiveName. */
	private String executiveName = "";
	/** * applicationStatus. */
	private String applicationStatus = "";
	/** * terminationDate. */
	private String terminationDate = "";
	/** * lastDayWorkDate. */
	private String lastDayWorkDate = "";
	/** * ifTerDateAndLastDayWorkDateDiffer. */
	private String ifTerDateAndLastDayWorkDateDiffer = "";
	/** * ifOtherTerminationReasonTextArea. */
	private String ifOtherTerminationReasonTextArea = "";
	/** * terminationType. */
	private String terminationType = "";
	/** * voluntaryTerminationReason. */
	private String voluntaryTerminationReason = "";
	/** * inVoluntaryTerminationReason. */
	private String inVoluntaryTerminationReason = "";
	/** * otherTerminationReason. */
	private String otherTerminationReason = "";
	/** * eligibleToRehire. */
	private String eligibleToRehire = "";
	/** * ifNoOrProvisional. */
	private String ifNoOrProvisional = "";
	/** * vacationHrsTaken. */
	private String vacationHrsTaken = "";
	/** * commentsEntered. */
	private String commentsEntered = "";
	/** * completedByID. */
	private String completedByID = "";
	/** * completedByName. */
	private String completedByName = "";
	/** * completedDate. */
	private String completedDate = "";
	/** * terminationID. */
	private String terminationID = "";
	/** * status. */
	private String status = "";
	/** * terminationTypeRadioOptionValue. */
	private String terminationTypeRadioOptionValue = "";
	/** * trackingNumber. */
	private String trackingNumber = "";

	/** * approverComments. */
	private String approverComments = "";
	/** * apprName. */
	private String apprName = "";
	/** * apprComments. */
	private String apprComments = "";
	/** * apprDate. */
	private String apprDate = "";
	/** * apprStatus. */
	private String apprStatus = "";
	/** * approverCommentList. */
	private List<TerminationApproval> approverCommentList;

	/** * managerToken. */
	private String managerToken = "";
	/** * managerCode. */
	private String managerCode = "";
	/** * managerName. */
	private String managerName = "";

	/**
	 * @return the managerName.
	 */
	public String getManagerName() {
		return managerName;
	}
	/**
	 * @param managerName the managerName to set.
	 */
	public void setManagerName(final String managerName) {
		this.managerName = managerName;
	}

	/**
	 * @return the applicationStatus.
	 */
	public String getApplicationStatus() {
		return applicationStatus;
	}

	/**
	 * @param applicationStatus the applicationStatus to set.
	 */
	public void setApplicationStatus(final String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	/**
	 * @return the terminationID.
	 */
	public String getTerminationID() {
		return terminationID;
	}

	/**
	 * @param terminationID the terminationID to set.
	 */
	public void setTerminationID(final String terminationID) {
		this.terminationID = terminationID;
	}

	/**
	 * @return the listType.
	 */
	public String getListType() {
		return listType;
	}

	/**
	 * @param listType the listType to set.
	 */
	public void setListType(final String listType) {
		this.listType = listType;
	}

	/**
	 * @return the myPage.
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @param myPage the myPage to set.
	 */
	public void setMyPage(final String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return the myPagePendingCancel.
	 */
	public String getMyPagePendingCancel() {
		return myPagePendingCancel;
	}

	/**
	 * @param myPagePendingCancel the myPagePendingCancel to set.
	 */
	public void setMyPagePendingCancel(final String myPagePendingCancel) {
		this.myPagePendingCancel = myPagePendingCancel;
	}

	/**
	 * @return the myPageApproved.
	 */
	public String getMyPageApproved() {
		return myPageApproved;
	}

	/**
	 * @param myPageApproved the myPageApproved to set.
	 */
	public void setMyPageApproved(final String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}

	/**
	 * @return the myPageRejected.
	 */
	public String getMyPageRejected() {
		return myPageRejected;
	}

	/**
	 * @param myPageRejected the myPageRejected to set.
	 */
	public void setMyPageRejected(final String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}

	/**
	 * @return the pendingIteratorList.
	 */
	public List<TerminationApproval> getPendingIteratorList() {
		return pendingIteratorList;
	}

	/**
	 * @param pendingIteratorList the pendingIteratorList to set.
	 */
	public void setPendingIteratorList(
			List<TerminationApproval> pendingIteratorList) {
		this.pendingIteratorList = pendingIteratorList;
	}

	/**
	 * @return the pendingListLength.
	 */
	public boolean isPendingListLength() {
		return pendingListLength;
	}

	/**
	 * @param pendingListLength the pendingListLength to set.
	 */
	public void setPendingListLength(final boolean pendingListLength) {
		this.pendingListLength = pendingListLength;
	}

	/**
	 * @return the approveredIteratorList.
	 */
	public List<TerminationApproval> getApproveredIteratorList() {
		return approveredIteratorList;
	}

	/**
	 * @param approveredIteratorList the approveredIteratorList to set.
	 */
	public void setApproveredIteratorList(
			List<TerminationApproval> approveredIteratorList) {
		this.approveredIteratorList = approveredIteratorList;
	}

	/**
	 * @return the approvedListLength.
	 */
	public boolean isApprovedListLength() {
		return approvedListLength;
	}

	/**
	 * @param approvedListLength the approvedListLength to set.
	 */
	public void setApprovedListLength(final boolean approvedListLength) {
		this.approvedListLength = approvedListLength;
	}

	/**
	 * @return the rejectedIteratorList.
	 */
	public List<TerminationApproval> getRejectedIteratorList() {
		return rejectedIteratorList;
	}

	/**
	 * @param rejectedIteratorList the rejectedIteratorList to set.
	 */
	public void setRejectedIteratorList(
			List<TerminationApproval> rejectedIteratorList) {
		this.rejectedIteratorList = rejectedIteratorList;
	}

	/**
	 * @return the rejectedListLength.
	 */
	public boolean isRejectedListLength() {
		return rejectedListLength;
	}

	/**
	 * @param rejectedListLength the rejectedListLength to set.
	 */
	public void setRejectedListLength(final boolean rejectedListLength) {
		this.rejectedListLength = rejectedListLength;
	}

	/**
	 * @return the terminationHiddenID.
	 */
	public String getTerminationHiddenID() {
		return terminationHiddenID;
	}

	/**
	 * @param terminationHiddenID the terminationHiddenID to set.
	 */
	public void setTerminationHiddenID(final String terminationHiddenID) {
		this.terminationHiddenID = terminationHiddenID;
	}

	/**
	 * @return the terminationHiddenStatus.
	 */
	public String getTerminationHiddenStatus() {
		return terminationHiddenStatus;
	}

	/**
	 * @param terminationHiddenStatus the terminationHiddenStatus to set.
	 */
	public void setTerminationHiddenStatus(final String terminationHiddenStatus) {
		this.terminationHiddenStatus = terminationHiddenStatus;
	}

	/**
	 * @return the trackingNumIterator.
	 */
	public String getTrackingNumIterator() {
		return trackingNumIterator;
	}

	/**
	 * @param trackingNumIterator the trackingNumIterator to set.
	 */
	public void setTrackingNumIterator(final String trackingNumIterator) {
		this.trackingNumIterator = trackingNumIterator;
	}

	/**
	 * @return the employeeNameIterator.
	 */
	public String getEmployeeNameIterator() {
		return employeeNameIterator;
	}

	/**
	 * @param employeeNameIterator the employeeNameIterator to set.
	 */
	public void setEmployeeNameIterator(final String employeeNameIterator) {
		this.employeeNameIterator = employeeNameIterator;
	}

	/**
	 * @return the terminationDateIterator.
	 */
	public String getTerminationDateIterator() {
		return terminationDateIterator;
	}

	/**
	 * @param terminationDateIterator the terminationDateIterator to set.
	 */
	public void setTerminationDateIterator(final String terminationDateIterator) {
		this.terminationDateIterator = terminationDateIterator;
	}

	/**
	 * @return the employeeNumber.
	 */
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * @param employeeNumber the employeeNumber to set.
	 */
	public void setEmployeeNumber(final String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * @return the employeeToken.
	 */
	public String getEmployeeToken() {
		return employeeToken;
	}

	/**
	 * @param employeeToken the employeeToken to set.
	 */
	public void setEmployeeToken(final String employeeToken) {
		this.employeeToken = employeeToken;
	}

	/**
	 * @return the employeeName.
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set.
	 */
	public void setEmployeeName(final String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the homeAddress.
	 */
	public String getHomeAddress() {
		return homeAddress;
	}

	/**
	 * @param homeAddress the homeAddress to set.
	 */
	public void setHomeAddress(final String homeAddress) {
		this.homeAddress = homeAddress;
	}

	/**
	 * @return the zipCode.
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set.
	 */
	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the cityID.
	 */
	public String getCityID() {
		return cityID;
	}

	/**
	 * @param cityID the cityID to set.
	 */
	public void setCityID(final String cityID) {
		this.cityID = cityID;
	}

	/**
	 * @return the cityName.
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set.
	 */
	public void setCityName(final String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set.
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @return the deptID.
	 */
	public String getDeptID() {
		return deptID;
	}

	/**
	 * @param deptID the deptID to set.
	 */
	public void setDeptID(final String deptID) {
		this.deptID = deptID;
	}

	/**
	 * @return the depetNumber.
	 */
	public String getDepetNumber() {
		return depetNumber;
	}

	/**
	 * @param depetNumber the depetNumber to set.
	 */
	public void setDepetNumber(final String depetNumber) {
		this.depetNumber = depetNumber;
	}

	/**
	 * @return the executiveID.
	 */
	public String getExecutiveID() {
		return executiveID;
	}

	/**
	 * @param executiveID the executiveID to set.
	 */
	public void setExecutiveID(final String executiveID) {
		this.executiveID = executiveID;
	}

	/**
	 * @return the executiveName.
	 */
	public String getExecutiveName() {
		return executiveName;
	}

	/**
	 * @param executiveName the executiveName to set.
	 */
	public void setExecutiveName(final String executiveName) {
		this.executiveName = executiveName;
	}

	/**
	 * @return the terminationDate.
	 */
	public String getTerminationDate() {
		return terminationDate;
	}

	/**
	 * @param terminationDate the terminationDate to set.
	 */
	public void setTerminationDate(final String terminationDate) {
		this.terminationDate = terminationDate;
	}

	/**
	 * @return the lastDayWorkDate.
	 */
	public String getLastDayWorkDate() {
		return lastDayWorkDate;
	}

	/**
	 * @param lastDayWorkDate  the lastDayWorkDate to set.
	 */
	public void setLastDayWorkDate(final String lastDayWorkDate) {
		this.lastDayWorkDate = lastDayWorkDate;
	}

	/**
	 * @return the ifTerDateAndLastDayWorkDateDiffer.
	 */
	public String getIfTerDateAndLastDayWorkDateDiffer() {
		return ifTerDateAndLastDayWorkDateDiffer;
	}

	/**
	 * @param ifTerDateAndLastDayWorkDateDiffer the ifTerDateAndLastDayWorkDateDiffer to set.
	 */
	public void setIfTerDateAndLastDayWorkDateDiffer(
			String ifTerDateAndLastDayWorkDateDiffer) {
		this.ifTerDateAndLastDayWorkDateDiffer = ifTerDateAndLastDayWorkDateDiffer;
	}

	/**
	 * @return the ifOtherTerminationReasonTextArea.
	 */
	public String getIfOtherTerminationReasonTextArea() {
		return ifOtherTerminationReasonTextArea;
	}

	/**
	 * @param ifOtherTerminationReasonTextArea the ifOtherTerminationReasonTextArea to set.
	 */
	public void setIfOtherTerminationReasonTextArea(
			String ifOtherTerminationReasonTextArea) {
		this.ifOtherTerminationReasonTextArea = ifOtherTerminationReasonTextArea;
	}

	/**
	 * @return the terminationType.
	 */
	public String getTerminationType() {
		return terminationType;
	}

	/**
	 * @param terminationType the terminationType to set.
	 */
	public void setTerminationType(final String terminationType) {
		this.terminationType = terminationType;
	}

	/**
	 * @return the voluntaryTerminationReason.
	 */
	public String getVoluntaryTerminationReason() {
		return voluntaryTerminationReason;
	}

	/**
	 * @param voluntaryTerminationReason the voluntaryTerminationReason to set.
	 */
	public void setVoluntaryTerminationReason(final String voluntaryTerminationReason) {
		this.voluntaryTerminationReason = voluntaryTerminationReason;
	}

	/**
	 * @return the inVoluntaryTerminationReason.
	 */
	public String getInVoluntaryTerminationReason() {
		return inVoluntaryTerminationReason;
	}

	/**
	 * @param inVoluntaryTerminationReason the inVoluntaryTerminationReason to set.
	 */
	public void setInVoluntaryTerminationReason(
			String inVoluntaryTerminationReason) {
		this.inVoluntaryTerminationReason = inVoluntaryTerminationReason;
	}

	/**
	 * @return the otherTerminationReason.
	 */
	public String getOtherTerminationReason() {
		return otherTerminationReason;
	}

	/**
	 * @param otherTerminationReason the otherTerminationReason to set.
	 */
	public void setOtherTerminationReason(final String otherTerminationReason) {
		this.otherTerminationReason = otherTerminationReason;
	}

	/**
	 * @return the eligibleToRehire.
	 */
	public String getEligibleToRehire() {
		return eligibleToRehire;
	}

	/**
	 * @param eligibleToRehire the eligibleToRehire to set.
	 */
	public void setEligibleToRehire(final String eligibleToRehire) {
		this.eligibleToRehire = eligibleToRehire;
	}

	/**
	 * @return the ifNoOrProvisional.
	 */
	public String getIfNoOrProvisional() {
		return ifNoOrProvisional;
	}

	/**
	 * @param ifNoOrProvisional the ifNoOrProvisional to set.
	 */
	public void setIfNoOrProvisional(final String ifNoOrProvisional) {
		this.ifNoOrProvisional = ifNoOrProvisional;
	}

	/**
	 * @return the vacationHrsTaken.
	 */
	public String getVacationHrsTaken() {
		return vacationHrsTaken;
	}

	/**
	 * @param vacationHrsTaken the vacationHrsTaken to set.
	 */
	public void setVacationHrsTaken(final String vacationHrsTaken) {
		this.vacationHrsTaken = vacationHrsTaken;
	}

	/**
	 * @return the commentsEntered.
	 */
	public String getCommentsEntered() {
		return commentsEntered;
	}

	/**
	 * @param commentsEntered the commentsEntered to set.
	 */
	public void setCommentsEntered(final String commentsEntered) {
		this.commentsEntered = commentsEntered;
	}

	/**
	 * @return the completedByID.
	 */
	public String getCompletedByID() {
		return completedByID;
	}

	/**
	 * @param completedByID the completedByID to set.
	 */
	public void setCompletedByID(final String completedByID) {
		this.completedByID = completedByID;
	}

	/**
	 * @return the completedByName.
	 */
	public String getCompletedByName() {
		return completedByName;
	}

	/**
	 * @param completedByName the completedByName to set.
	 */
	public void setCompletedByName(final String completedByName) {
		this.completedByName = completedByName;
	}

	/**
	 * @return the completedDate.
	 */
	public String getCompletedDate() {
		return completedDate;
	}

	/**
	 * @param completedDate the completedDate to set.
	 */
	public void setCompletedDate(final String completedDate) {
		this.completedDate = completedDate;
	}

	/**
	 * @return the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set.
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @return the terminationTypeRadioOptionValue.
	 */
	public String getTerminationTypeRadioOptionValue() {
		return terminationTypeRadioOptionValue;
	}

	/**
	 * @param terminationTypeRadioOptionValue the terminationTypeRadioOptionValue to set.
	 */
	public void setTerminationTypeRadioOptionValue(
			String terminationTypeRadioOptionValue) {
		this.terminationTypeRadioOptionValue = terminationTypeRadioOptionValue;
	}

	/**
	 * @return the trackingNumber.
	 */
	public String getTrackingNumber() {
		return trackingNumber;
	}

	/**
	 * @param trackingNumber the trackingNumber to set.
	 */
	public void setTrackingNumber(final String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	/**
	 * @return the approverComments.
	 */
	public String getApproverComments() {
		return approverComments;
	}

	/**
	 * @param approverComments the approverComments to set.
	 */
	public void setApproverComments(final String approverComments) {
		this.approverComments = approverComments;
	}

	/**
	 * @return the apprName.
	 */
	public String getApprName() {
		return apprName;
	}

	/**
	 * @param apprName the apprName to set.
	 */
	public void setApprName(final String apprName) {
		this.apprName = apprName;
	}

	/**
	 * @return the apprComments.
	 */
	public String getApprComments() {
		return apprComments;
	}

	/**
	 * @param apprComments the apprComments to set.
	 */
	public void setApprComments(final String apprComments) {
		this.apprComments = apprComments;
	}

	/**
	 * @return the apprDate.
	 */
	public String getApprDate() {
		return apprDate;
	}

	/**
	 * @param apprDate the apprDate to set.
	 */
	public void setApprDate(final String apprDate) {
		this.apprDate = apprDate;
	}

	/**
	 * @return the apprStatus.
	 */
	public String getApprStatus() {
		return apprStatus;
	}

	/**
	 * @param apprStatus the apprStatus to set.
	 */
	public void setApprStatus(final String apprStatus) {
		this.apprStatus = apprStatus;
	}

	/**
	 * @return the approverCommentList.
	 */
	public List <TerminationApproval>getApproverCommentList() {
		return approverCommentList;
	}

	/**
	 * @param approverCommentList the approverCommentList to set.
	 */
	public void setApproverCommentList(List<TerminationApproval>approverCommentList) {
		this.approverCommentList = approverCommentList;
	}

	/**
	 * @return the managerToken.
	 */
	public String getManagerToken() {
		return managerToken;
	}

	/**
	 * @param managerToken the managerToken to set.
	 */
	public void setManagerToken(final String managerToken) {
		this.managerToken = managerToken;
	}

	/**
	 * @return the managerCode.
	 */
	public String getManagerCode() {
		return managerCode;
	}

	/**
	 * @param managerCode the managerCode to set.
	 */
	public void setManagerCode(final String managerCode) {
		this.managerCode = managerCode;
	}

}
