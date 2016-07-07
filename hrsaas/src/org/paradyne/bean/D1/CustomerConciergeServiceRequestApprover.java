/**
 * @author  Anantha lakshmi 
 *  
 */

package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CustomerConciergeServiceRequestApprover extends BeanBase {
	private String purchaseID = "";  
	private String custName="";
	private String streetAddr="";
	private String custPinCode="";
	private String custCity="";
	private String custState="";
	private String mngrName="";
	private String numAlertPads="";
	private String maxOrder="";
	
	
	
	// Approver Comments List 
	private String approverComments = "";
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";
	ArrayList approverCommentList = null;
	
	private String listType = "";
	private String myPage = "";
	private String purchaseHiddenID = "";
	private String hiddenStatus = "";
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
	
	private String trackingNumIterator = "";
	private String completedByIterator = "";
	
	private String comments = "";
	
	private String completedBy = "";
	private String completedByID = "";
	private String completedDate = "";
	private String status = "";
	private String applicationStatus = "";
	private String trackingNumber = "";

	private String approverCommentFlag=""; 
	
	private String applicationID="";
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
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
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
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
	
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getCompletedBy() {
		return completedBy;
	}
	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}
	public String getCompletedByID() {
		return completedByID;
	}
	public void setCompletedByID(String completedByID) {
		this.completedByID = completedByID;
	}
	public String getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}
	public String getPurchaseID() {
		return purchaseID;
	}
	public void setPurchaseID(String purchaseID) {
		this.purchaseID = purchaseID;
	}
	
	public String getCompletedByIterator() {
		return completedByIterator;
	}
	public void setCompletedByIterator(String completedByIterator) {
		this.completedByIterator = completedByIterator;
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
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getApproverCommentFlag() {
		return approverCommentFlag;
	}
	public void setApproverCommentFlag(String approverCommentFlag) {
		this.approverCommentFlag = approverCommentFlag;
	}
}
