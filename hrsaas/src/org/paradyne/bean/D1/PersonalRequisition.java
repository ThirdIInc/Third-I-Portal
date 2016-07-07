package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class PersonalRequisition extends BeanBase {
	public String fileheaderName="";
	public String trackingNo="";
	private String forwardedNameType = "";
	// Approver Comments List 
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";
	ArrayList approverCommentList = null;
	boolean approverCommentsFlag = false;
	
	private String myPage = "";
	private String myPageDrafted = "";
	private String myPageInProcess = "";
	private String myPageSentBack = "";
	private String myPageApproved = "";
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
	private boolean cancelledVoucherListLength =  false;
	ArrayList rejectedVoucherIteratorList = null;
	private boolean rejectedVoucherListLength =  false;
	ArrayList rejectedCancelVoucherIteratorList = null;
	private boolean rejectedCancelVoucherListLength =  false;
	
	private String requestID = "";
	private String personalHiddenID = "";
	private String personalRequisitionID = "";
	private String positionTitle = "";
	private String positionTitleName = "";
	private String requisition = "";
	private String bandId = "";
	private String bandName = "";
	private String maxSalary = "";
	private String positionDate = "";
	private String deptCode = "";
	private String deptName = "";
	private String executiveCode = "";
	private String executiveName = "";
	private String workLocation = "";
	private String hiringManagerToken = "";
	private String hiringManagerName = "";
	private String hiringManagerCode = "";
	private String hiringManagerPhoneNumber = "";
	private String hiringManagerFaxNumber = "";
	private String hiringManagerEmailAddress = "";
	private String approvedExistingJob = "";
	private String newJobExist = "";
	private String headCount = "";
	private String replacementType = "";
	private String reqReplacing = "";
	private String terminationDate = "";
	private String positionType = "";
	private String temporaryType = "";
	private String signTimecardId = "";
	private String signTimecardName = "";
	private String maximumBillRate = "";
	private String overtimeRequired = "";
	private String numberOfOvertime = "";
	private String durationOfAssignment = "";
	private String reasonForTemporaryNeed = "";
	private String variableWorkfroceRate = "";
	private String rateType = "";
	private String durationOfVariableAssignment = "";
	private String reasonForVariableWorkforceNeed = "";
	private String budget = "";
	private String stdHourPerWeek = "";
	private String fulltimeParttime = "";
	private String educationRequirements = "";
	private String experienceRequirement ="";
	private String essentialPositionRequirements = "";
	private String reason = "";
	private String listType = "";
	private String approverCode = "";
	private String approverName = "";
	private String approverToken = "";
	private String employeeCode = "";
	private String listTypeDetailPage = "";
	private String status = "";
	private String agencyName = "";
	private String contractorName = "";
	private String contractorPhoneNumber = "";
	private String contractorEmailAddress = "";
	private String persReqStatus = "";
	private String userProfileRadioOptionValue = "";
	private String forwardedForApprovar = "";
	private String applicationDate = "";
	private String rateTypeRadioOptionValue = "";
	private String initiatorCode = "";
	private String initiatorDate = "";
	private String initiatorToken = "";
	private String initiatorName = "";
	private String reqReplacingToken="";
	private String reqReplacingName="";
	private String reqReplacingCode="";
	
	private String  cancellationFlag = "false";
	private String positionRequiredDateItr = "";
	/**
	 * @return the fileheaderName
	 */
	public String getFileheaderName() {
		return this.fileheaderName;
	}
	/**
	 * @param fileheaderName the fileheaderName to set
	 */
	public void setFileheaderName(String fileheaderName) {
		this.fileheaderName = fileheaderName;
	}
	/**
	 * @return the trackingNo
	 */
	public String getTrackingNo() {
		return this.trackingNo;
	}
	/**
	 * @param trackingNo the trackingNo to set
	 */
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	/**
	 * @return the forwardedNameType
	 */
	public String getForwardedNameType() {
		return this.forwardedNameType;
	}
	/**
	 * @param forwardedNameType the forwardedNameType to set
	 */
	public void setForwardedNameType(String forwardedNameType) {
		this.forwardedNameType = forwardedNameType;
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
	public void setApprName(String apprName) {
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
	public void setApprComments(String apprComments) {
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
	public void setApprDate(String apprDate) {
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
	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}
	/**
	 * @return the approverCommentList
	 */
	public ArrayList getApproverCommentList() {
		return this.approverCommentList;
	}
	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	/**
	 * @return the approverCommentsFlag
	 */
	public boolean isApproverCommentsFlag() {
		return this.approverCommentsFlag;
	}
	/**
	 * @param approverCommentsFlag the approverCommentsFlag to set
	 */
	public void setApproverCommentsFlag(boolean approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
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
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the myPageDrafted
	 */
	public String getMyPageDrafted() {
		return this.myPageDrafted;
	}
	/**
	 * @param myPageDrafted the myPageDrafted to set
	 */
	public void setMyPageDrafted(String myPageDrafted) {
		this.myPageDrafted = myPageDrafted;
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
	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}
	/**
	 * @return the myPageSentBack
	 */
	public String getMyPageSentBack() {
		return this.myPageSentBack;
	}
	/**
	 * @param myPageSentBack the myPageSentBack to set
	 */
	public void setMyPageSentBack(String myPageSentBack) {
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
	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}
	/**
	 * @return the myPageApprovedCancel
	 */
	public String getMyPageApprovedCancel() {
		return this.myPageApprovedCancel;
	}
	/**
	 * @param myPageApprovedCancel the myPageApprovedCancel to set
	 */
	public void setMyPageApprovedCancel(String myPageApprovedCancel) {
		this.myPageApprovedCancel = myPageApprovedCancel;
	}
	/**
	 * @return the myPageCancel
	 */
	public String getMyPageCancel() {
		return this.myPageCancel;
	}
	/**
	 * @param myPageCancel the myPageCancel to set
	 */
	public void setMyPageCancel(String myPageCancel) {
		this.myPageCancel = myPageCancel;
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
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	/**
	 * @return the myPageCancelRejected
	 */
	public String getMyPageCancelRejected() {
		return this.myPageCancelRejected;
	}
	/**
	 * @param myPageCancelRejected the myPageCancelRejected to set
	 */
	public void setMyPageCancelRejected(String myPageCancelRejected) {
		this.myPageCancelRejected = myPageCancelRejected;
	}
	/**
	 * @return the draftVoucherIteratorList
	 */
	public ArrayList getDraftVoucherIteratorList() {
		return this.draftVoucherIteratorList;
	}
	/**
	 * @param draftVoucherIteratorList the draftVoucherIteratorList to set
	 */
	public void setDraftVoucherIteratorList(ArrayList draftVoucherIteratorList) {
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
	public void setDraftVoucherListLength(boolean draftVoucherListLength) {
		this.draftVoucherListLength = draftVoucherListLength;
	}
	/**
	 * @return the inProcessVoucherIteratorList
	 */
	public ArrayList getInProcessVoucherIteratorList() {
		return this.inProcessVoucherIteratorList;
	}
	/**
	 * @param inProcessVoucherIteratorList the inProcessVoucherIteratorList to set
	 */
	public void setInProcessVoucherIteratorList(
			ArrayList inProcessVoucherIteratorList) {
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
	public void setInProcessVoucherListLength(boolean inProcessVoucherListLength) {
		this.inProcessVoucherListLength = inProcessVoucherListLength;
	}
	/**
	 * @return the sentBackVoucherIteratorList
	 */
	public ArrayList getSentBackVoucherIteratorList() {
		return this.sentBackVoucherIteratorList;
	}
	/**
	 * @param sentBackVoucherIteratorList the sentBackVoucherIteratorList to set
	 */
	public void setSentBackVoucherIteratorList(ArrayList sentBackVoucherIteratorList) {
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
	public void setSentBackVoucherListLength(boolean sentBackVoucherListLength) {
		this.sentBackVoucherListLength = sentBackVoucherListLength;
	}
	/**
	 * @return the approvedVoucherIteratorList
	 */
	public ArrayList getApprovedVoucherIteratorList() {
		return this.approvedVoucherIteratorList;
	}
	/**
	 * @param approvedVoucherIteratorList the approvedVoucherIteratorList to set
	 */
	public void setApprovedVoucherIteratorList(ArrayList approvedVoucherIteratorList) {
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
	public void setApprovedVoucherListLength(boolean approvedVoucherListLength) {
		this.approvedVoucherListLength = approvedVoucherListLength;
	}
	/**
	 * @return the approvedCancellationVoucherIteratorList
	 */
	public ArrayList getApprovedCancellationVoucherIteratorList() {
		return this.approvedCancellationVoucherIteratorList;
	}
	/**
	 * @param approvedCancellationVoucherIteratorList the approvedCancellationVoucherIteratorList to set
	 */
	public void setApprovedCancellationVoucherIteratorList(
			ArrayList approvedCancellationVoucherIteratorList) {
		this.approvedCancellationVoucherIteratorList = approvedCancellationVoucherIteratorList;
	}
	/**
	 * @return the approvedCancellationVoucherListLength
	 */
	public boolean isApprovedCancellationVoucherListLength() {
		return this.approvedCancellationVoucherListLength;
	}
	/**
	 * @param approvedCancellationVoucherListLength the approvedCancellationVoucherListLength to set
	 */
	public void setApprovedCancellationVoucherListLength(
			boolean approvedCancellationVoucherListLength) {
		this.approvedCancellationVoucherListLength = approvedCancellationVoucherListLength;
	}
	/**
	 * @return the cancelledVoucherIteratorList
	 */
	public ArrayList getCancelledVoucherIteratorList() {
		return this.cancelledVoucherIteratorList;
	}
	/**
	 * @param cancelledVoucherIteratorList the cancelledVoucherIteratorList to set
	 */
	public void setCancelledVoucherIteratorList(
			ArrayList cancelledVoucherIteratorList) {
		this.cancelledVoucherIteratorList = cancelledVoucherIteratorList;
	}
	/**
	 * @return the cancelledVoucherListLength
	 */
	public boolean isCancelledVoucherListLength() {
		return this.cancelledVoucherListLength;
	}
	/**
	 * @param cancelledVoucherListLength the cancelledVoucherListLength to set
	 */
	public void setCancelledVoucherListLength(boolean cancelledVoucherListLength) {
		this.cancelledVoucherListLength = cancelledVoucherListLength;
	}
	/**
	 * @return the rejectedVoucherIteratorList
	 */
	public ArrayList getRejectedVoucherIteratorList() {
		return this.rejectedVoucherIteratorList;
	}
	/**
	 * @param rejectedVoucherIteratorList the rejectedVoucherIteratorList to set
	 */
	public void setRejectedVoucherIteratorList(ArrayList rejectedVoucherIteratorList) {
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
	public void setRejectedVoucherListLength(boolean rejectedVoucherListLength) {
		this.rejectedVoucherListLength = rejectedVoucherListLength;
	}
	/**
	 * @return the rejectedCancelVoucherIteratorList
	 */
	public ArrayList getRejectedCancelVoucherIteratorList() {
		return this.rejectedCancelVoucherIteratorList;
	}
	/**
	 * @param rejectedCancelVoucherIteratorList the rejectedCancelVoucherIteratorList to set
	 */
	public void setRejectedCancelVoucherIteratorList(
			ArrayList rejectedCancelVoucherIteratorList) {
		this.rejectedCancelVoucherIteratorList = rejectedCancelVoucherIteratorList;
	}
	/**
	 * @return the rejectedCancelVoucherListLength
	 */
	public boolean isRejectedCancelVoucherListLength() {
		return this.rejectedCancelVoucherListLength;
	}
	/**
	 * @param rejectedCancelVoucherListLength the rejectedCancelVoucherListLength to set
	 */
	public void setRejectedCancelVoucherListLength(
			boolean rejectedCancelVoucherListLength) {
		this.rejectedCancelVoucherListLength = rejectedCancelVoucherListLength;
	}
	/**
	 * @return the requestID
	 */
	public String getRequestID() {
		return this.requestID;
	}
	/**
	 * @param requestID the requestID to set
	 */
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	/**
	 * @return the personalHiddenID
	 */
	public String getPersonalHiddenID() {
		return this.personalHiddenID;
	}
	/**
	 * @param personalHiddenID the personalHiddenID to set
	 */
	public void setPersonalHiddenID(String personalHiddenID) {
		this.personalHiddenID = personalHiddenID;
	}
	/**
	 * @return the personalRequisitionID
	 */
	public String getPersonalRequisitionID() {
		return this.personalRequisitionID;
	}
	/**
	 * @param personalRequisitionID the personalRequisitionID to set
	 */
	public void setPersonalRequisitionID(String personalRequisitionID) {
		this.personalRequisitionID = personalRequisitionID;
	}
	/**
	 * @return the positionTitle
	 */
	public String getPositionTitle() {
		return this.positionTitle;
	}
	/**
	 * @param positionTitle the positionTitle to set
	 */
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}
	/**
	 * @return the positionTitleName
	 */
	public String getPositionTitleName() {
		return this.positionTitleName;
	}
	/**
	 * @param positionTitleName the positionTitleName to set
	 */
	public void setPositionTitleName(String positionTitleName) {
		this.positionTitleName = positionTitleName;
	}
	/**
	 * @return the requisition
	 */
	public String getRequisition() {
		return this.requisition;
	}
	/**
	 * @param requisition the requisition to set
	 */
	public void setRequisition(String requisition) {
		this.requisition = requisition;
	}
	/**
	 * @return the bandId
	 */
	public String getBandId() {
		return this.bandId;
	}
	/**
	 * @param bandId the bandId to set
	 */
	public void setBandId(String bandId) {
		this.bandId = bandId;
	}
	/**
	 * @return the bandName
	 */
	public String getBandName() {
		return this.bandName;
	}
	/**
	 * @param bandName the bandName to set
	 */
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	/**
	 * @return the maxSalary
	 */
	public String getMaxSalary() {
		return this.maxSalary;
	}
	/**
	 * @param maxSalary the maxSalary to set
	 */
	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}
	/**
	 * @return the positionDate
	 */
	public String getPositionDate() {
		return this.positionDate;
	}
	/**
	 * @param positionDate the positionDate to set
	 */
	public void setPositionDate(String positionDate) {
		this.positionDate = positionDate;
	}
	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return this.deptCode;
	}
	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return this.deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the executiveCode
	 */
	public String getExecutiveCode() {
		return this.executiveCode;
	}
	/**
	 * @param executiveCode the executiveCode to set
	 */
	public void setExecutiveCode(String executiveCode) {
		this.executiveCode = executiveCode;
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
	public void setExecutiveName(String executiveName) {
		this.executiveName = executiveName;
	}
	/**
	 * @return the workLocation
	 */
	public String getWorkLocation() {
		return this.workLocation;
	}
	/**
	 * @param workLocation the workLocation to set
	 */
	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}
	/**
	 * @return the hiringManagerToken
	 */
	public String getHiringManagerToken() {
		return this.hiringManagerToken;
	}
	/**
	 * @param hiringManagerToken the hiringManagerToken to set
	 */
	public void setHiringManagerToken(String hiringManagerToken) {
		this.hiringManagerToken = hiringManagerToken;
	}
	/**
	 * @return the hiringManagerName
	 */
	public String getHiringManagerName() {
		return this.hiringManagerName;
	}
	/**
	 * @param hiringManagerName the hiringManagerName to set
	 */
	public void setHiringManagerName(String hiringManagerName) {
		this.hiringManagerName = hiringManagerName;
	}
	/**
	 * @return the hiringManagerCode
	 */
	public String getHiringManagerCode() {
		return this.hiringManagerCode;
	}
	/**
	 * @param hiringManagerCode the hiringManagerCode to set
	 */
	public void setHiringManagerCode(String hiringManagerCode) {
		this.hiringManagerCode = hiringManagerCode;
	}
	/**
	 * @return the hiringManagerPhoneNumber
	 */
	public String getHiringManagerPhoneNumber() {
		return this.hiringManagerPhoneNumber;
	}
	/**
	 * @param hiringManagerPhoneNumber the hiringManagerPhoneNumber to set
	 */
	public void setHiringManagerPhoneNumber(String hiringManagerPhoneNumber) {
		this.hiringManagerPhoneNumber = hiringManagerPhoneNumber;
	}
	/**
	 * @return the hiringManagerFaxNumber
	 */
	public String getHiringManagerFaxNumber() {
		return this.hiringManagerFaxNumber;
	}
	/**
	 * @param hiringManagerFaxNumber the hiringManagerFaxNumber to set
	 */
	public void setHiringManagerFaxNumber(String hiringManagerFaxNumber) {
		this.hiringManagerFaxNumber = hiringManagerFaxNumber;
	}
	/**
	 * @return the hiringManagerEmailAddress
	 */
	public String getHiringManagerEmailAddress() {
		return this.hiringManagerEmailAddress;
	}
	/**
	 * @param hiringManagerEmailAddress the hiringManagerEmailAddress to set
	 */
	public void setHiringManagerEmailAddress(String hiringManagerEmailAddress) {
		this.hiringManagerEmailAddress = hiringManagerEmailAddress;
	}
	/**
	 * @return the approvedExistingJob
	 */
	public String getApprovedExistingJob() {
		return this.approvedExistingJob;
	}
	/**
	 * @param approvedExistingJob the approvedExistingJob to set
	 */
	public void setApprovedExistingJob(String approvedExistingJob) {
		this.approvedExistingJob = approvedExistingJob;
	}
	/**
	 * @return the newJobExist
	 */
	public String getNewJobExist() {
		return this.newJobExist;
	}
	/**
	 * @param newJobExist the newJobExist to set
	 */
	public void setNewJobExist(String newJobExist) {
		this.newJobExist = newJobExist;
	}
	/**
	 * @return the headCount
	 */
	public String getHeadCount() {
		return this.headCount;
	}
	/**
	 * @param headCount the headCount to set
	 */
	public void setHeadCount(String headCount) {
		this.headCount = headCount;
	}
	/**
	 * @return the replacementType
	 */
	public String getReplacementType() {
		return this.replacementType;
	}
	/**
	 * @param replacementType the replacementType to set
	 */
	public void setReplacementType(String replacementType) {
		this.replacementType = replacementType;
	}
	/**
	 * @return the reqReplacing
	 */
	public String getReqReplacing() {
		return this.reqReplacing;
	}
	/**
	 * @param reqReplacing the reqReplacing to set
	 */
	public void setReqReplacing(String reqReplacing) {
		this.reqReplacing = reqReplacing;
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
	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}
	/**
	 * @return the positionType
	 */
	public String getPositionType() {
		return this.positionType;
	}
	/**
	 * @param positionType the positionType to set
	 */
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	/**
	 * @return the temporaryType
	 */
	public String getTemporaryType() {
		return this.temporaryType;
	}
	/**
	 * @param temporaryType the temporaryType to set
	 */
	public void setTemporaryType(String temporaryType) {
		this.temporaryType = temporaryType;
	}
	/**
	 * @return the signTimecardId
	 */
	public String getSignTimecardId() {
		return this.signTimecardId;
	}
	/**
	 * @param signTimecardId the signTimecardId to set
	 */
	public void setSignTimecardId(String signTimecardId) {
		this.signTimecardId = signTimecardId;
	}
	/**
	 * @return the signTimecardName
	 */
	public String getSignTimecardName() {
		return this.signTimecardName;
	}
	/**
	 * @param signTimecardName the signTimecardName to set
	 */
	public void setSignTimecardName(String signTimecardName) {
		this.signTimecardName = signTimecardName;
	}
	/**
	 * @return the maximumBillRate
	 */
	public String getMaximumBillRate() {
		return this.maximumBillRate;
	}
	/**
	 * @param maximumBillRate the maximumBillRate to set
	 */
	public void setMaximumBillRate(String maximumBillRate) {
		this.maximumBillRate = maximumBillRate;
	}
	/**
	 * @return the overtimeRequired
	 */
	public String getOvertimeRequired() {
		return this.overtimeRequired;
	}
	/**
	 * @param overtimeRequired the overtimeRequired to set
	 */
	public void setOvertimeRequired(String overtimeRequired) {
		this.overtimeRequired = overtimeRequired;
	}
	/**
	 * @return the numberOfOvertime
	 */
	public String getNumberOfOvertime() {
		return this.numberOfOvertime;
	}
	/**
	 * @param numberOfOvertime the numberOfOvertime to set
	 */
	public void setNumberOfOvertime(String numberOfOvertime) {
		this.numberOfOvertime = numberOfOvertime;
	}
	/**
	 * @return the durationOfAssignment
	 */
	public String getDurationOfAssignment() {
		return this.durationOfAssignment;
	}
	/**
	 * @param durationOfAssignment the durationOfAssignment to set
	 */
	public void setDurationOfAssignment(String durationOfAssignment) {
		this.durationOfAssignment = durationOfAssignment;
	}
	/**
	 * @return the reasonForTemporaryNeed
	 */
	public String getReasonForTemporaryNeed() {
		return this.reasonForTemporaryNeed;
	}
	/**
	 * @param reasonForTemporaryNeed the reasonForTemporaryNeed to set
	 */
	public void setReasonForTemporaryNeed(String reasonForTemporaryNeed) {
		this.reasonForTemporaryNeed = reasonForTemporaryNeed;
	}
	/**
	 * @return the variableWorkfroceRate
	 */
	public String getVariableWorkfroceRate() {
		return this.variableWorkfroceRate;
	}
	/**
	 * @param variableWorkfroceRate the variableWorkfroceRate to set
	 */
	public void setVariableWorkfroceRate(String variableWorkfroceRate) {
		this.variableWorkfroceRate = variableWorkfroceRate;
	}
	/**
	 * @return the rateType
	 */
	public String getRateType() {
		return this.rateType;
	}
	/**
	 * @param rateType the rateType to set
	 */
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	/**
	 * @return the durationOfVariableAssignment
	 */
	public String getDurationOfVariableAssignment() {
		return this.durationOfVariableAssignment;
	}
	/**
	 * @param durationOfVariableAssignment the durationOfVariableAssignment to set
	 */
	public void setDurationOfVariableAssignment(String durationOfVariableAssignment) {
		this.durationOfVariableAssignment = durationOfVariableAssignment;
	}
	/**
	 * @return the reasonForVariableWorkforceNeed
	 */
	public String getReasonForVariableWorkforceNeed() {
		return this.reasonForVariableWorkforceNeed;
	}
	/**
	 * @param reasonForVariableWorkforceNeed the reasonForVariableWorkforceNeed to set
	 */
	public void setReasonForVariableWorkforceNeed(
			String reasonForVariableWorkforceNeed) {
		this.reasonForVariableWorkforceNeed = reasonForVariableWorkforceNeed;
	}
	/**
	 * @return the budget
	 */
	public String getBudget() {
		return this.budget;
	}
	/**
	 * @param budget the budget to set
	 */
	public void setBudget(String budget) {
		this.budget = budget;
	}
	/**
	 * @return the stdHourPerWeek
	 */
	public String getStdHourPerWeek() {
		return this.stdHourPerWeek;
	}
	/**
	 * @param stdHourPerWeek the stdHourPerWeek to set
	 */
	public void setStdHourPerWeek(String stdHourPerWeek) {
		this.stdHourPerWeek = stdHourPerWeek;
	}
	/**
	 * @return the fulltimeParttime
	 */
	public String getFulltimeParttime() {
		return this.fulltimeParttime;
	}
	/**
	 * @param fulltimeParttime the fulltimeParttime to set
	 */
	public void setFulltimeParttime(String fulltimeParttime) {
		this.fulltimeParttime = fulltimeParttime;
	}
	/**
	 * @return the educationRequirements
	 */
	public String getEducationRequirements() {
		return this.educationRequirements;
	}
	/**
	 * @param educationRequirements the educationRequirements to set
	 */
	public void setEducationRequirements(String educationRequirements) {
		this.educationRequirements = educationRequirements;
	}
	/**
	 * @return the experienceRequirement
	 */
	public String getExperienceRequirement() {
		return this.experienceRequirement;
	}
	/**
	 * @param experienceRequirement the experienceRequirement to set
	 */
	public void setExperienceRequirement(String experienceRequirement) {
		this.experienceRequirement = experienceRequirement;
	}
	/**
	 * @return the essentialPositionRequirements
	 */
	public String getEssentialPositionRequirements() {
		return this.essentialPositionRequirements;
	}
	/**
	 * @param essentialPositionRequirements the essentialPositionRequirements to set
	 */
	public void setEssentialPositionRequirements(
			String essentialPositionRequirements) {
		this.essentialPositionRequirements = essentialPositionRequirements;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return this.reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
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
	public void setListType(String listType) {
		this.listType = listType;
	}
	/**
	 * @return the approverCode
	 */
	public String getApproverCode() {
		return this.approverCode;
	}
	/**
	 * @param approverCode the approverCode to set
	 */
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	/**
	 * @return the approverName
	 */
	public String getApproverName() {
		return this.approverName;
	}
	/**
	 * @param approverName the approverName to set
	 */
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	/**
	 * @return the approverToken
	 */
	public String getApproverToken() {
		return this.approverToken;
	}
	/**
	 * @param approverToken the approverToken to set
	 */
	public void setApproverToken(String approverToken) {
		this.approverToken = approverToken;
	}
	/**
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return this.employeeCode;
	}
	/**
	 * @param employeeCode the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	/**
	 * @return the listTypeDetailPage
	 */
	public String getListTypeDetailPage() {
		return this.listTypeDetailPage;
	}
	/**
	 * @param listTypeDetailPage the listTypeDetailPage to set
	 */
	public void setListTypeDetailPage(String listTypeDetailPage) {
		this.listTypeDetailPage = listTypeDetailPage;
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
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the agencyName
	 */
	public String getAgencyName() {
		return this.agencyName;
	}
	/**
	 * @param agencyName the agencyName to set
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	/**
	 * @return the contractorName
	 */
	public String getContractorName() {
		return this.contractorName;
	}
	/**
	 * @param contractorName the contractorName to set
	 */
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
	/**
	 * @return the contractorPhoneNumber
	 */
	public String getContractorPhoneNumber() {
		return this.contractorPhoneNumber;
	}
	/**
	 * @param contractorPhoneNumber the contractorPhoneNumber to set
	 */
	public void setContractorPhoneNumber(String contractorPhoneNumber) {
		this.contractorPhoneNumber = contractorPhoneNumber;
	}
	/**
	 * @return the contractorEmailAddress
	 */
	public String getContractorEmailAddress() {
		return this.contractorEmailAddress;
	}
	/**
	 * @param contractorEmailAddress the contractorEmailAddress to set
	 */
	public void setContractorEmailAddress(String contractorEmailAddress) {
		this.contractorEmailAddress = contractorEmailAddress;
	}
	/**
	 * @return the persReqStatus
	 */
	public String getPersReqStatus() {
		return this.persReqStatus;
	}
	/**
	 * @param persReqStatus the persReqStatus to set
	 */
	public void setPersReqStatus(String persReqStatus) {
		this.persReqStatus = persReqStatus;
	}
	/**
	 * @return the userProfileRadioOptionValue
	 */
	public String getUserProfileRadioOptionValue() {
		return this.userProfileRadioOptionValue;
	}
	/**
	 * @param userProfileRadioOptionValue the userProfileRadioOptionValue to set
	 */
	public void setUserProfileRadioOptionValue(String userProfileRadioOptionValue) {
		this.userProfileRadioOptionValue = userProfileRadioOptionValue;
	}
	/**
	 * @return the forwardedForApprovar
	 */
	public String getForwardedForApprovar() {
		return this.forwardedForApprovar;
	}
	/**
	 * @param forwardedForApprovar the forwardedForApprovar to set
	 */
	public void setForwardedForApprovar(String forwardedForApprovar) {
		this.forwardedForApprovar = forwardedForApprovar;
	}
	/**
	 * @return the applicationDate
	 */
	public String getApplicationDate() {
		return this.applicationDate;
	}
	/**
	 * @param applicationDate the applicationDate to set
	 */
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	/**
	 * @return the rateTypeRadioOptionValue
	 */
	public String getRateTypeRadioOptionValue() {
		return this.rateTypeRadioOptionValue;
	}
	/**
	 * @param rateTypeRadioOptionValue the rateTypeRadioOptionValue to set
	 */
	public void setRateTypeRadioOptionValue(String rateTypeRadioOptionValue) {
		this.rateTypeRadioOptionValue = rateTypeRadioOptionValue;
	}
	/**
	 * @return the initiatorCode
	 */
	public String getInitiatorCode() {
		return this.initiatorCode;
	}
	/**
	 * @param initiatorCode the initiatorCode to set
	 */
	public void setInitiatorCode(String initiatorCode) {
		this.initiatorCode = initiatorCode;
	}
	/**
	 * @return the initiatorDate
	 */
	public String getInitiatorDate() {
		return this.initiatorDate;
	}
	/**
	 * @param initiatorDate the initiatorDate to set
	 */
	public void setInitiatorDate(String initiatorDate) {
		this.initiatorDate = initiatorDate;
	}
	/**
	 * @return the initiatorToken
	 */
	public String getInitiatorToken() {
		return this.initiatorToken;
	}
	/**
	 * @param initiatorToken the initiatorToken to set
	 */
	public void setInitiatorToken(String initiatorToken) {
		this.initiatorToken = initiatorToken;
	}
	/**
	 * @return the initiatorName
	 */
	public String getInitiatorName() {
		return this.initiatorName;
	}
	/**
	 * @param initiatorName the initiatorName to set
	 */
	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}
	/**
	 * @return the reqReplacingToken
	 */
	public String getReqReplacingToken() {
		return this.reqReplacingToken;
	}
	/**
	 * @param reqReplacingToken the reqReplacingToken to set
	 */
	public void setReqReplacingToken(String reqReplacingToken) {
		this.reqReplacingToken = reqReplacingToken;
	}
	/**
	 * @return the reqReplacingName
	 */
	public String getReqReplacingName() {
		return this.reqReplacingName;
	}
	/**
	 * @param reqReplacingName the reqReplacingName to set
	 */
	public void setReqReplacingName(String reqReplacingName) {
		this.reqReplacingName = reqReplacingName;
	}
	/**
	 * @return the reqReplacingCode
	 */
	public String getReqReplacingCode() {
		return this.reqReplacingCode;
	}
	/**
	 * @param reqReplacingCode the reqReplacingCode to set
	 */
	public void setReqReplacingCode(String reqReplacingCode) {
		this.reqReplacingCode = reqReplacingCode;
	}
	/**
	 * @return the cancellationFlag
	 */
	public String getCancellationFlag() {
		return this.cancellationFlag;
	}
	/**
	 * @param cancellationFlag the cancellationFlag to set
	 */
	public void setCancellationFlag(String cancellationFlag) {
		this.cancellationFlag = cancellationFlag;
	}
	/**
	 * @return the positionRequiredDateItr
	 */
	public String getPositionRequiredDateItr() {
		return this.positionRequiredDateItr;
	}
	/**
	 * @param positionRequiredDateItr the positionRequiredDateItr to set
	 */
	public void setPositionRequiredDateItr(String positionRequiredDateItr) {
		this.positionRequiredDateItr = positionRequiredDateItr;
	}
	
		
	
}
