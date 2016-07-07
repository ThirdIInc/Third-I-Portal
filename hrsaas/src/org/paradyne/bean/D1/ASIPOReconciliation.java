/**
 * @author aa1380 : manish sakpal
 * Created on : 7th March 2011 
 */

package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ASIPOReconciliation extends BeanBase {

	private String applicationID = "";
	private String status = "";
	private String listType = "";
	private String totalRecords = "";
	private String myPage = "";
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
	
	private String purchaseHiddenID = "";
	private String hiddenStatus = "";
	private String trackingNumIterator = "";
	private String completedByIterator = "";
	private String partsReceivedDateIterator = "";
	
	// Approver Comments List Begins
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";
	ArrayList approverCommentList = null;
	boolean approverCommentsFlag = false;
	// Approver Comments List Ends
	
	
	private String purchaseRequisition = "";
	private String datePartsWereReceived = "";
	private String purchaseOrderNumberOnSlip = "";
	private String decisionOnePartNumber = "";
	private String vendorName = "";
	private String comments = "";
	private String purchaseOrderNumber = "";
	private String problemDescription = "";
	private String completedBy = "";
	private String completedByID = "";
	private String completedDate = "";
	private String applicationStatus = "";
	private String trackingNumber = "";
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPurchaseRequisition() {
		return purchaseRequisition;
	}
	public void setPurchaseRequisition(String purchaseRequisition) {
		this.purchaseRequisition = purchaseRequisition;
	}
	
	public String getDatePartsWereReceived() {
		return datePartsWereReceived;
	}
	public void setDatePartsWereReceived(String datePartsWereReceived) {
		this.datePartsWereReceived = datePartsWereReceived;
	}
	public String getPurchaseOrderNumberOnSlip() {
		return purchaseOrderNumberOnSlip;
	}
	public void setPurchaseOrderNumberOnSlip(String purchaseOrderNumberOnSlip) {
		this.purchaseOrderNumberOnSlip = purchaseOrderNumberOnSlip;
	}
	public String getDecisionOnePartNumber() {
		return decisionOnePartNumber;
	}
	public void setDecisionOnePartNumber(String decisionOnePartNumber) {
		this.decisionOnePartNumber = decisionOnePartNumber;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
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
	public String getMyPageApproved() {
		return myPageApproved;
	}
	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
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
	public ArrayList getApprovedVoucherIteratorList() {
		return approvedVoucherIteratorList;
	}
	public void setApprovedVoucherIteratorList(ArrayList approvedVoucherIteratorList) {
		this.approvedVoucherIteratorList = approvedVoucherIteratorList;
	}
	public boolean isApprovedVoucherListLength() {
		return approvedVoucherListLength;
	}
	public void setApprovedVoucherListLength(boolean approvedVoucherListLength) {
		this.approvedVoucherListLength = approvedVoucherListLength;
	}
	public ArrayList getApprovedCancellationVoucherIteratorList() {
		return approvedCancellationVoucherIteratorList;
	}
	public void setApprovedCancellationVoucherIteratorList(
			ArrayList approvedCancellationVoucherIteratorList) {
		this.approvedCancellationVoucherIteratorList = approvedCancellationVoucherIteratorList;
	}
	public boolean isApprovedCancellationVoucherListLength() {
		return approvedCancellationVoucherListLength;
	}
	public void setApprovedCancellationVoucherListLength(
			boolean approvedCancellationVoucherListLength) {
		this.approvedCancellationVoucherListLength = approvedCancellationVoucherListLength;
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
	public String getTrackingNumIterator() {
		return trackingNumIterator;
	}
	public void setTrackingNumIterator(String trackingNumIterator) {
		this.trackingNumIterator = trackingNumIterator;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public void setCancelledVoucherListLength(boolean cancelledVoucherListLength) {
		this.cancelledVoucherListLength = cancelledVoucherListLength;
	}
	public ArrayList getRejectedVoucherIteratorList() {
		return rejectedVoucherIteratorList;
	}
	public void setRejectedVoucherIteratorList(ArrayList rejectedVoucherIteratorList) {
		this.rejectedVoucherIteratorList = rejectedVoucherIteratorList;
	}
	public boolean isRejectedVoucherListLength() {
		return rejectedVoucherListLength;
	}
	public void setRejectedVoucherListLength(boolean rejectedVoucherListLength) {
		this.rejectedVoucherListLength = rejectedVoucherListLength;
	}
	public ArrayList getRejectedCancelVoucherIteratorList() {
		return rejectedCancelVoucherIteratorList;
	}
	public void setRejectedCancelVoucherIteratorList(
			ArrayList rejectedCancelVoucherIteratorList) {
		this.rejectedCancelVoucherIteratorList = rejectedCancelVoucherIteratorList;
	}
	public boolean isRejectedCancelVoucherListLength() {
		return rejectedCancelVoucherListLength;
	}
	public void setRejectedCancelVoucherListLength(
			boolean rejectedCancelVoucherListLength) {
		this.rejectedCancelVoucherListLength = rejectedCancelVoucherListLength;
	}
	public String getPurchaseHiddenID() {
		return purchaseHiddenID;
	}
	public void setPurchaseHiddenID(String purchaseHiddenID) {
		this.purchaseHiddenID = purchaseHiddenID;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	
	public String getCompletedByIterator() {
		return completedByIterator;
	}
	public void setCompletedByIterator(String completedByIterator) {
		this.completedByIterator = completedByIterator;
	}
	public String getPartsReceivedDateIterator() {
		return partsReceivedDateIterator;
	}
	public void setPartsReceivedDateIterator(String partsReceivedDateIterator) {
		this.partsReceivedDateIterator = partsReceivedDateIterator;
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
	public boolean isApproverCommentsFlag() {
		return approverCommentsFlag;
	}
	public void setApproverCommentsFlag(boolean approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
	}
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	public String getProblemDescription() {
		return problemDescription;
	}
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	public String getCompletedBy() {
		return completedBy;
	}
	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}
	public String getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}
	public String getCompletedByID() {
		return completedByID;
	}
	public void setCompletedByID(String completedByID) {
		this.completedByID = completedByID;
	}
}
