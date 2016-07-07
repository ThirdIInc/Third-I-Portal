/**
 * @author Anantha Lakshmi
 * Created on : 7th March 2011 
 */

package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CustomerConciergeServiceRequest extends BeanBase {

	
	
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

	
	
	private String custName="";
	private String streetAddr="";
	private String custPinCode="";
	private String custCity="";
	private String custState="";
	private String mngrName="";
	private String numAlertPads="";
	private String maxOrder="";
	
	boolean approvedListFlag=false;
	boolean rejectedListFlag=false;
	boolean pendingListFlag=false;
	boolean draftListFlag=false;
	
	
	
	// Approver Comments List Begins
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";
	ArrayList approverCommentList = null;
	boolean approverCommentsFlag = false;
	// Approver Comments List Ends
	
	
	
	private String comments = "";
	
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getStreetAddr() {
		return streetAddr;
	}
	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}
	public String getCustPinCode() {
		return custPinCode;
	}
	public void setCustPinCode(String custPinCode) {
		this.custPinCode = custPinCode;
	}
	public String getCustCity() {
		return custCity;
	}
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
	}
	public String getMngrName() {
		return mngrName;
	}
	public void setMngrName(String mngrName) {
		this.mngrName = mngrName;
	}
	public String getNumAlertPads() {
		return numAlertPads;
	}
	public void setNumAlertPads(String numAlertPads) {
		this.numAlertPads = numAlertPads;
	}
	public String getMaxOrder() {
		return maxOrder;
	}
	public void setMaxOrder(String maxOrder) {
		this.maxOrder = maxOrder;
	}
	public boolean isApprovedListFlag() {
		return approvedListFlag;
	}
	public void setApprovedListFlag(boolean approvedListFlag) {
		this.approvedListFlag = approvedListFlag;
	}
	public boolean isRejectedListFlag() {
		return rejectedListFlag;
	}
	public void setRejectedListFlag(boolean rejectedListFlag) {
		this.rejectedListFlag = rejectedListFlag;
	}
	public boolean isPendingListFlag() {
		return pendingListFlag;
	}
	public void setPendingListFlag(boolean pendingListFlag) {
		this.pendingListFlag = pendingListFlag;
	}
	public boolean isDraftListFlag() {
		return draftListFlag;
	}
	public void setDraftListFlag(boolean draftListFlag) {
		this.draftListFlag = draftListFlag;
	}
	
}
