package org.paradyne.bean.D1;

import java.util.List;
import org.paradyne.lib.BeanBase;

/**
 * Bhushan Dasare Mar 17, 2011 Bean for report request change application and approval.
 */

public class ReportRequestChange extends BeanBase {

	/**
	 * Used for tracking no.
	 */
	private String trackingNo;
	
	
	/**
	 * Used for new record.
	 */
	private boolean newRecord;
	
	
	/**
	 * Used for requestId.
	 */
	private String reqId;
	
	
	/**
	 * Used for list type.
	 */
	private String listType;
	
	
	/**
	 * Used for page for draft applications.
	 */
	private String pageForDraft;
	
	
	/**
	 * Used for list of draft applications.
	 */
	private List<ReportRequestChange> draftList;
	
	
	/**
	 * Used for page for pending applications.
	 */
	private String pageForPending;
	
	
	/**
	 * Used for list of pending applications.
	 */
	private List<ReportRequestChange> pendingList;
	
	
	/**
	 * Used for page for send back applications.
	 */
	private String pageForSendBack;
	
	
	/**
	 * Used for list of send back applications.
	 */
	private List<ReportRequestChange> sendBackList;
	
	
	/**
	 * Used for page for pending cancel.
	 */
	private String pageForPendingCancel;
	
	
	/**
	 * Used for list pending cancel applications.
	 */
	private List<ReportRequestChange> pendingCancelList;
	
	
	/**
	 * Used for page for approved.
	 */
	private String pageForApproved;
	
	
	/**
	 * Used for list of approved applications.
	 */
	private List<ReportRequestChange> approvedList;
	
	
	/**
	 * Used for page for approve/cancel.
	 */
	private String pageForApproveCancel;
	
	
	/**
	 * Used for list of approve cancel applications.
	 */
	private List<ReportRequestChange> approveCancelList;
	
	
	/**
	 * Used for page for rejected.
	 */
	private String pageForRejected;
	
	
	/**
	 * Used for list of rejected applications.
	 */
	private List<ReportRequestChange> rejectedList;
	
	
	/**
	 * Used for page for rejected cancel.
	 */
	private String pageForRejectedCancel;
	
	
	/**
	 * Used for list of rejected cancel applications.
	 */
	private List<ReportRequestChange> rejectedCancelList;
	
	
	/**
	 * Used for requestId.
	 */
	private String requestId;
	
	
	/**
	 * Used for requester id.
	 */
	private String requestorId;
	
	
	/**
	 * Used for requester token.
	 */
	private String requestorToken;
	
	
	/**
	 * Used for requester name.
	 */
	private String requestorName;
	
	
	/**
	 * Used for requester designation.
	 */
	private String requestorDesgn;
	
	
	/**
	 * Used for requester phone.
	 */
	private String requestorPhone;
	
	
	/**
	 * Used for request date.
	 */
	private String requestDate;
	
	
	/**
	 * Used for request type.
	 */
	private String requestType;
	
	
	/**
	 * Used for customer.
	 */
	private String customer;
	
	
	/**
	 * Used for report title.
	 */
	private String reportTitle;
	
	
	/**
	 * Used for request details.
	 */
	private String details;
	
	
	/**
	 * Used for file path.
	 */
	private String dataPath;
	
	
	/**
	 * Used for file to be attached.
	 */
	private String reportFile;
	
	
	/**
	 * Used for attached file.
	 */
	private String addedFile;
	
	
	/**
	 * Used for status.
	 */
	private String status;
	
	
	/**
	 * Used for approver comments.
	 */
	private String approverComments;
	
	
	/**
	 * Used for list of approver comments.
	 */
	private List<ReportRequestChange> approverCommentsList;
	
	
	/**
	 * Used for approver name.
	 */
	private String apprName;
	
	
	/**
	 * Used for approver comments.
	 */
	private String apprComments;
	
	
	/**
	 * Used for approve date.
	 */
	private String apprDate;
	
	
	/**
	 * Used for status of approval.
	 */
	private String apprStatus;
	
	
	/**
	 * Used for default approver id.
	 */
	private String defApproverId;
	
	
	/**
	 * Used for default approver.
	 */
	private String defApproverToken;
	
	
	/**
	 * Used for default approver name.
	 */
	private String defApproverName;
	
	
	/**
	 * Used for new approver id.
	 */
	private String newApproverId;
	
	
	/**
	 * Used for new approver token.
	 */
	private String newApproverToken;
	
	
	/**
	 * Used for new approver name.
	 */
	private String newApproverName;
	
	
	/**
	 * Used for next approver id.
	 */
	private String nextApproverId;
	
	
	/**
	 * Used for next approver token.
	 */
	private String nextApproverToken;
	
	
	/**
	 * Used for next approver name.
	 */
	private String nextApproverName;
	
	
	/**
	 * Used for initiator id.
	 */
	private String initiatorId;
	
	
	/**
	 * Used for initiator name.
	 */
	private String initiatorName;
	
	
	/**
	 * Used for completed on.
	 */
	private String completedOn;
	
	
	/**
	 * Used for IT approvers exist or not.
	 */
	private boolean itApproversExist;
	
	/**
	 * Used for show next approver or not.
	 */
	private boolean showNextApprover;
	
	/**
	 * Used for show approver comments or not.
	 */
	private boolean showApproverComments;
	
	/**
	 * Used for user is manager or not.
	 */
	private boolean userAManager;
	
	/**
	 * Used for user is IT approver or not.
	 */
	private boolean userAITApprover;

	/**
	 * @return the addedFile
	 */
	public String getAddedFile() {
		return this.addedFile;
	}

	/**
	 * @return this.the apprComments
	 */
	public String getApprComments() {
		return this.apprComments;
	}

	/**
	 * @return this.the apprDate
	 */
	public String getApprDate() {
		return this.apprDate;
	}

	/**
	 * @return this.the apprName
	 */
	public String getApprName() {
		return this.apprName;
	}

	/**
	 * @return this.the approveCancelList
	 */
	public List<ReportRequestChange> getApproveCancelList() {
		return this.approveCancelList;
	}

	/**
	 * @return this.the approvedList
	 */
	public List<ReportRequestChange> getApprovedList() {
		return this.approvedList;
	}

	/**
	 * @return this.the approverComments
	 */
	public String getApproverComments() {
		return this.approverComments;
	}

	/**
	 * @return this.the approverCommentsList
	 */
	public List<ReportRequestChange> getApproverCommentsList() {
		return this.approverCommentsList;
	}

	/**
	 * @return this.the apprStatus
	 */
	public String getApprStatus() {
		return this.apprStatus;
	}

	/**
	 * @return this.the completedOn
	 */
	public String getCompletedOn() {
		return this.completedOn;
	}

	/**
	 * @return this.the customer
	 */
	public String getCustomer() {
		return this.customer;
	}

	/**
	 * @return this.the dataPath
	 */
	public String getDataPath() {
		return this.dataPath;
	}

	/**
	 * @return this.the defApproverId
	 */
	public String getDefApproverId() {
		return this.defApproverId;
	}

	/**
	 * @return this.the defApproverName
	 */
	public String getDefApproverName() {
		return this.defApproverName;
	}

	/**
	 * @return this.the defApproverToken
	 */
	public String getDefApproverToken() {
		return this.defApproverToken;
	}

	/**
	 * @return this.the details
	 */
	public String getDetails() {
		return this.details;
	}

	/**
	 * @return this.the draftList
	 */
	public List<ReportRequestChange> getDraftList() {
		return this.draftList;
	}

	/**
	 * @return this.the initiatorId
	 */
	public String getInitiatorId() {
		return this.initiatorId;
	}

	/**
	 * @return this.the initiatorName
	 */
	public String getInitiatorName() {
		return this.initiatorName;
	}

	/**
	 * @return this.the listType
	 */
	public String getListType() {
		return this.listType;
	}

	/**
	 * @return this.the newApproverId
	 */
	public String getNewApproverId() {
		return this.newApproverId;
	}

	/**
	 * @return this.the newApproverName
	 */
	public String getNewApproverName() {
		return this.newApproverName;
	}

	/**
	 * @return this.the newApproverToken
	 */
	public String getNewApproverToken() {
		return this.newApproverToken;
	}

	/**
	 * @return this.the nextApproverId
	 */
	public String getNextApproverId() {
		return this.nextApproverId;
	}

	/**
	 * @return this.the nextApproverName
	 */
	public String getNextApproverName() {
		return this.nextApproverName;
	}

	/**
	 * @return this.the nextApproverToken
	 */
	public String getNextApproverToken() {
		return this.nextApproverToken;
	}

	/**
	 * @return this.the pageForApproveCancel
	 */
	public String getPageForApproveCancel() {
		return this.pageForApproveCancel;
	}

	/**
	 * @return this.the pageForApproved
	 */
	public String getPageForApproved() {
		return this.pageForApproved;
	}

	/**
	 * @return this.the pageForDraft
	 */
	public String getPageForDraft() {
		return this.pageForDraft;
	}

	/**
	 * @return this.the pageForPending
	 */
	public String getPageForPending() {
		return this.pageForPending;
	}

	/**
	 * @return this.the pageForPendingCancel
	 */
	public String getPageForPendingCancel() {
		return this.pageForPendingCancel;
	}

	/**
	 * @return this.the pageForRejected
	 */
	public String getPageForRejected() {
		return this.pageForRejected;
	}

	/**
	 * @return this.the pageForRejectedCancel
	 */
	public String getPageForRejectedCancel() {
		return this.pageForRejectedCancel;
	}

	/**
	 * @return this.the pageForSendBack
	 */
	public String getPageForSendBack() {
		return this.pageForSendBack;
	}

	/**
	 * @return this.the pendingCancelList
	 */
	public List<ReportRequestChange> getPendingCancelList() {
		return this.pendingCancelList;
	}

	/**
	 * @return this.the pendingList
	 */
	public List<ReportRequestChange> getPendingList() {
		return this.pendingList;
	}

	/**
	 * @return this.the rejectedCancelList
	 */
	public List<ReportRequestChange> getRejectedCancelList() {
		return this.rejectedCancelList;
	}

	/**
	 * @return this.the rejectedList
	 */
	public List<ReportRequestChange> getRejectedList() {
		return this.rejectedList;
	}

	/**
	 * @return this.the reportFile
	 */
	public String getReportFile() {
		return this.reportFile;
	}

	/**
	 * @return this.the reportTitle
	 */
	public String getReportTitle() {
		return this.reportTitle;
	}

	/**
	 * @return this.the reqId
	 */
	public String getReqId() {
		return this.reqId;
	}

	/**
	 * @return this.the requestDate
	 */
	public String getRequestDate() {
		return this.requestDate;
	}

	/**
	 * @return this.the requestId
	 */
	public String getRequestId() {
		return this.requestId;
	}

	/**
	 * @return this.the requestorDesgn
	 */
	public String getRequestorDesgn() {
		return this.requestorDesgn;
	}

	/**
	 * @return this.the requestorId
	 */
	public String getRequestorId() {
		return this.requestorId;
	}

	/**
	 * @return this.the requestorName
	 */
	public String getRequestorName() {
		return this.requestorName;
	}

	/**
	 * @return this.the requestorPhone
	 */
	public String getRequestorPhone() {
		return this.requestorPhone;
	}

	/**
	 * @return this.the requestorToken
	 */
	public String getRequestorToken() {
		return this.requestorToken;
	}

	/**
	 * @return this.the requestType
	 */
	public String getRequestType() {
		return this.requestType;
	}

	/**
	 * @return this.the sendBackList
	 */
	public List<ReportRequestChange> getSendBackList() {
		return this.sendBackList;
	}

	/**
	 * @return this.the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @return this.the trackingNo
	 */
	public String getTrackingNo() {
		return this.trackingNo;
	}

	/**
	 * @return this.the itApproversExist
	 */
	public boolean isItApproversExist() {
		return this.itApproversExist;
	}

	/**
	 * @return this.the newRecord
	 */
	public boolean isNewRecord() {
		return this.newRecord;
	}

	/**
	 * @return this.the showApproverComments
	 */
	public boolean isShowApproverComments() {
		return this.showApproverComments;
	}

	/**
	 * @return this.the showNextApprover
	 */
	public boolean isShowNextApprover() {
		return this.showNextApprover;
	}

	/**
	 * @return this.the userAITApprover
	 */
	public boolean isUserAITApprover() {
		return this.userAITApprover;
	}

	/**
	 * @return this.the userAManager
	 */
	public boolean isUserAManager() {
		return this.userAManager;
	}

	/**
	 * @param addedFile the addedFile to set
	 */
	public void setAddedFile(final String addedFile) {
		this.addedFile = addedFile;
	}

	/**
	 * @param apprComments the apprComments to set
	 */
	public void setApprComments(final String apprComments) {
		this.apprComments = apprComments;
	}

	/**
	 * @param apprDate the apprDate to set
	 */
	public void setApprDate(final String apprDate) {
		this.apprDate = apprDate;
	}

	/**
	 * @param apprName the apprName to set
	 */
	public void setApprName(final String apprName) {
		this.apprName = apprName;
	}

	/**
	 * @param approveCancelList the approveCancelList to set
	 */
	public void setApproveCancelList(final List<ReportRequestChange> approveCancelList) {
		this.approveCancelList = approveCancelList;
	}

	/**
	 * @param approvedList the approvedList to set
	 */
	public void setApprovedList(final List<ReportRequestChange> approvedList) {
		this.approvedList = approvedList;
	}

	/**
	 * @param approverComments the approverComments to set
	 */
	public void setApproverComments(final String approverComments) {
		this.approverComments = approverComments;
	}

	/**
	 * @param approverCommentsList the approverCommentsList to set
	 */
	public void setApproverCommentsList(final List<ReportRequestChange> approverCommentsList) {
		this.approverCommentsList = approverCommentsList;
	}

	/**
	 * @param apprStatus the apprStatus to set
	 */
	public void setApprStatus(final String apprStatus) {
		this.apprStatus = apprStatus;
	}

	/**
	 * @param completedOn the completedOn to set
	 */
	public void setCompletedOn(final String completedOn) {
		this.completedOn = completedOn;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(final String customer) {
		this.customer = customer;
	}

	/**
	 * @param dataPath the dataPath to set
	 */
	public void setDataPath(final String dataPath) {
		this.dataPath = dataPath;
	}

	/**
	 * @param defApproverId the defApproverId to set
	 */
	public void setDefApproverId(final String defApproverId) {
		this.defApproverId = defApproverId;
	}

	/**
	 * @param defApproverName the defApproverName to set
	 */
	public void setDefApproverName(final String defApproverName) {
		this.defApproverName = defApproverName;
	}

	/**
	 * @param defApproverToken the defApproverToken to set
	 */
	public void setDefApproverToken(final String defApproverToken) {
		this.defApproverToken = defApproverToken;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(final String details) {
		this.details = details;
	}

	/**
	 * @param draftList the draftList to set
	 */
	public void setDraftList(final List<ReportRequestChange> draftList) {
		this.draftList = draftList;
	}

	/**
	 * @param initiatorId the initiatorId to set
	 */
	public void setInitiatorId(final String initiatorId) {
		this.initiatorId = initiatorId;
	}

	/**
	 * @param initiatorName the initiatorName to set
	 */
	public void setInitiatorName(final String initiatorName) {
		this.initiatorName = initiatorName;
	}

	/**
	 * @param itApproversExist the itApproversExist to set
	 */
	public void setItApproversExist(final boolean itApproversExist) {
		this.itApproversExist = itApproversExist;
	}

	/**
	 * @param listType the listType to set
	 */
	public void setListType(final String listType) {
		this.listType = listType;
	}

	/**
	 * @param newApproverId the newApproverId to set
	 */
	public void setNewApproverId(final String newApproverId) {
		this.newApproverId = newApproverId;
	}

	/**
	 * @param newApproverName the newApproverName to set
	 */
	public void setNewApproverName(final String newApproverName) {
		this.newApproverName = newApproverName;
	}

	/**
	 * @param newApproverToken the newApproverToken to set
	 */
	public void setNewApproverToken(final String newApproverToken) {
		this.newApproverToken = newApproverToken;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(final boolean newRecord) {
		this.newRecord = newRecord;
	}

	/**
	 * @param nextApproverId the nextApproverId to set
	 */
	public void setNextApproverId(final String nextApproverId) {
		this.nextApproverId = nextApproverId;
	}

	/**
	 * @param nextApproverName the nextApproverName to set
	 */
	public void setNextApproverName(final String nextApproverName) {
		this.nextApproverName = nextApproverName;
	}

	/**
	 * @param nextApproverToken the nextApproverToken to set
	 */
	public void setNextApproverToken(final String nextApproverToken) {
		this.nextApproverToken = nextApproverToken;
	}

	/**
	 * @param pageForApproveCancel the pageForApproveCancel to set
	 */
	public void setPageForApproveCancel(final String pageForApproveCancel) {
		this.pageForApproveCancel = pageForApproveCancel;
	}

	/**
	 * @param pageForApproved the pageForApproved to set
	 */
	public void setPageForApproved(final String pageForApproved) {
		this.pageForApproved = pageForApproved;
	}

	/**
	 * @param pageForDraft the pageForDraft to set
	 */
	public void setPageForDraft(final String pageForDraft) {
		this.pageForDraft = pageForDraft;
	}

	/**
	 * @param pageForPending the pageForPending to set
	 */
	public void setPageForPending(final String pageForPending) {
		this.pageForPending = pageForPending;
	}

	/**
	 * @param pageForPendingCancel the pageForPendingCancel to set
	 */
	public void setPageForPendingCancel(final String pageForPendingCancel) {
		this.pageForPendingCancel = pageForPendingCancel;
	}

	/**
	 * @param pageForRejected the pageForRejected to set
	 */
	public void setPageForRejected(final String pageForRejected) {
		this.pageForRejected = pageForRejected;
	}

	/**
	 * @param pageForRejectedCancel the pageForRejectedCancel to set
	 */
	public void setPageForRejectedCancel(final String pageForRejectedCancel) {
		this.pageForRejectedCancel = pageForRejectedCancel;
	}

	/**
	 * @param pageForSendBack the pageForSendBack to set
	 */
	public void setPageForSendBack(final String pageForSendBack) {
		this.pageForSendBack = pageForSendBack;
	}

	/**
	 * @param pendingCancelList the pendingCancelList to set
	 */
	public void setPendingCancelList(final List<ReportRequestChange> pendingCancelList) {
		this.pendingCancelList = pendingCancelList;
	}

	/**
	 * @param pendingList the pendingList to set
	 */
	public void setPendingList(final List<ReportRequestChange> pendingList) {
		this.pendingList = pendingList;
	}

	/**
	 * @param rejectedCancelList the rejectedCancelList to set
	 */
	public void setRejectedCancelList(final List<ReportRequestChange> rejectedCancelList) {
		this.rejectedCancelList = rejectedCancelList;
	}

	/**
	 * @param rejectedList the rejectedList to set
	 */
	public void setRejectedList(final List<ReportRequestChange> rejectedList) {
		this.rejectedList = rejectedList;
	}

	/**
	 * @param reportFile the reportFile to set
	 */
	public void setReportFile(final String reportFile) {
		this.reportFile = reportFile;
	}

	/**
	 * @param reportTitle the reportTitle to set
	 */
	public void setReportTitle(final String reportTitle) {
		this.reportTitle = reportTitle;
	}

	/**
	 * @param reqId the reqId to set
	 */
	public void setReqId(final String reqId) {
		this.reqId = reqId;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(final String requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(final String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @param requestorDesgn the requestorDesgn to set
	 */
	public void setRequestorDesgn(final String requestorDesgn) {
		this.requestorDesgn = requestorDesgn;
	}

	/**
	 * @param requestorId the requestorId to set
	 */
	public void setRequestorId(final String requestorId) {
		this.requestorId = requestorId;
	}

	/**
	 * @param requestorName the requestorName to set
	 */
	public void setRequestorName(final String requestorName) {
		this.requestorName = requestorName;
	}

	/**
	 * @param requestorPhone the requestorPhone to set
	 */
	public void setRequestorPhone(final String requestorPhone) {
		this.requestorPhone = requestorPhone;
	}

	/**
	 * @param requestorToken the requestorToken to set
	 */
	public void setRequestorToken(final String requestorToken) {
		this.requestorToken = requestorToken;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(final String requestType) {
		this.requestType = requestType;
	}

	/**
	 * @param sendBackList the sendBackList to set
	 */
	public void setSendBackList(final List<ReportRequestChange> sendBackList) {
		this.sendBackList = sendBackList;
	}

	/**
	 * @param showApproverComments the showApproverComments to set
	 */
	public void setShowApproverComments(final boolean showApproverComments) {
		this.showApproverComments = showApproverComments;
	}

	/**
	 * @param showNextApprover the showNextApprover to set
	 */
	public void setShowNextApprover(final boolean showNextApprover) {
		this.showNextApprover = showNextApprover;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @param trackingNo the trackingNo to set
	 */
	public void setTrackingNo(final String trackingNo) {
		this.trackingNo = trackingNo;
	}

	/**
	 * @param userAITApprover the userAITApprover to set
	 */
	public void setUserAITApprover(final boolean userAITApprover) {
		this.userAITApprover = userAITApprover;
	}

	/**
	 * @param userAManager the userAManager to set
	 */
	public void setUserAManager(final boolean userAManager) {
		this.userAManager = userAManager;
	}
}
