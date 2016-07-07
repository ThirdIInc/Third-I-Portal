package org.paradyne.bean.D1;

import java.util.List;
import org.paradyne.lib.BeanBase;

/**
 * @author aa1380
 *
 */
public class ReqToBackOutApprover extends BeanBase {
	// Approver Comments List 
	/** apprName. * */
	private String apprName = "";
	
	/** apprComments. * */
	private String apprComments = "";
	
	/** apprDate. * */
	private String apprDate = "";
	
	/** apprStatus. * */
	private String apprStatus = "";
	
	/** approverCommentList. * */
	private List<ReqToBackOutApprover> approverCommentList;
	
	/** vouherHiddenStatus. * */
	private String vouherHiddenStatus = "";
	
	/** myPageDrafted. * */
	private String myPageDrafted = "";
	
	/** myPagePendingCancel. * */
	private String myPagePendingCancel = "";
	
	/** myPageApproved. * */
	private String myPageApproved = "";
	
	/** myPageRejected. * */
	private String myPageRejected = "";
	
	/** pendingIteratorList. * */
	private List<ReqToBackOutApprover> pendingIteratorList;
	
	/** pendingListLength. * */
	private boolean pendingListLength;
	
	/** pendingCancellationIteratorList. * */
	private List<ReqToBackOutApprover> pendingCancellationIteratorList;
	
	/** pendingCancellationListLength. * */
	private boolean  pendingCancellationListLength;
	
	/** approveredIteratorList. * */
	private List<ReqToBackOutApprover> approveredIteratorList;
	
	/** approvedListLength. * */
	private boolean approvedListLength;
	
	/** rejectedIteratorList. * */
	private List<ReqToBackOutApprover> rejectedIteratorList;
	
	/** rejectedListLength. * */
	private boolean rejectedListLength;
	
	/** vouherHiddenID. * */
	private String vouherHiddenID = "";
	
	/** employeeIDIterator. * */
	private String employeeIDIterator = "";
	
	/** employeeTokenIterator. * */
	private String employeeTokenIterator = "";
	
	/** employeeNameIterator. * */
	private String employeeNameIterator = "";
	
	/** applicationDateIterator. * */
	private String applicationDateIterator;
	
	/** myPage. * */
	private String myPage = "";
	
	/** totalRecords. * */
	private String totalRecords = "";
	
	/** listType. * */
	private String listType = "";
	
	/** requestID. * */
	private String requestID = "";
	
	/** employeeID. * */
	private String employeeID = "";
	
	/** employeeToken. * */
	private String employeeToken = "";
	
	/** fromName. * */
	private String fromName = "";
	
	/** toDate. * */
	private String toDate;
	
	/** vendorName. * */
	private String vendorName = "";
	
	/** vendorNumber. * */
	private String vendorNumber = "";
	
	/** purchaseOrderNumber. * */
	private String purchaseOrderNumber;
	
	/** lineNumber. * */
	private String lineNumber = "";
	
	/** quantity. * */
	private String quantity = "";
	
	/** voucherNumber. * */
	private String voucherNumber = "";
	
	/** myPageRejected. * */
	private String reasonForRequest = "";
	
	/** rma. * */
	private String rma = "";
	
	/** airBillNumber. * */
	private String airBillNumber = "";
	
	/** didVendorIssueCreditMemo. * */
	private String didVendorIssueCreditMemo = "";
	
	/** creditMemoRadio. * */
	private String creditMemoRadio = "";
	
	/** comments. * */
	private String comments = "";
	
	/** myPageRejected. * */
	private String status = "";
	
	/** listTypeDetailPage. * */
	private String listTypeDetailPage = "";
	
	/** approverComments. * */
	private String approverComments = "";
	
	/** trackingNumberIterator. * */
	private String trackingNumberIterator = "";
	
	/** trackingNumber. * */
	private String trackingNumber = "";
	
	/** completedByID. * */
	private String completedByID = "";
	
	/** completedByName. * */
	private String completedByName = "";
	
	/** completedOn. * */
	private String completedOn = "";
	
	/** applicationStatus. * */
	private String applicationStatus = "";
	
	
	/**
	 * @return : String
	 */
	public String getApplicationStatus() {
		return this.applicationStatus;
	}
	

	/**
		 * @param applicationStatus : applicationStatus.
		 */
	public void setApplicationStatus(final String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	/**
	 * @return : final String
	 */
	public final String getTrackingNumberIterator() {
		return this.trackingNumberIterator;
	}
	

	/**
		 * @param trackingNumberIterator : trackingNumberIterator.
		 */
	public void setTrackingNumberIterator(final String trackingNumberIterator) {
		this.trackingNumberIterator = trackingNumberIterator;
	}
	
	/**
	 * @return : final String
	 */
	public final String getTrackingNumber() {
		return this.trackingNumber;
	}
	

	/**
		 * @param trackingNumber : trackingNumber.
		 */
	public void setTrackingNumber(final String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	
	/**
	 * @return : final String
	 */
	public final String getCompletedByID() {
		return this.completedByID;
	}
	

	/**
		 * @param completedByID : completedByID.
		 */
	public void setCompletedByID(final String completedByID) {
		this.completedByID = completedByID;
	}
	
	/**
	 * @return : final String
	 */
	public final String getCompletedByName() {
		return this.completedByName;
	}
	

	/**
		 * @param completedByName : completedByName.
		 */
	public void setCompletedByName(final String completedByName) {
		this.completedByName = completedByName;
	}
	
	/**
	 * @return : final String
	 */
	public final String getCompletedOn() {
		return this.completedOn;
	}
	

	/**
		 * @param completedOn : completedOn.
		 */
	public void setCompletedOn(final String completedOn) {
		this.completedOn = completedOn;
	}
	
	/**
	 * @return : final String
	 */
	public final String getApproverComments() {
		return this.approverComments;
	}
	

	/**
		 * @param approverComments : approverComments.
		 */
	public void setApproverComments(final String approverComments) {
		this.approverComments = approverComments;
	}
	
	/**
	 * @return : List
	 */
	public List<ReqToBackOutApprover> getPendingIteratorList() {
		return this.pendingIteratorList;
	}
	

	/**
		 * @param pendingIteratorList : pendingIteratorList.
		 */
	public void setPendingIteratorList(final List<ReqToBackOutApprover> pendingIteratorList) {
		this.pendingIteratorList = pendingIteratorList;
	}
	
	/**
	 * @return : final String
	 */
	public boolean isPendingListLength() {
		return this.pendingListLength;
	}
	

	/**
		 * @param pendingListLength : pendingListLength.
		 */
	public void setPendingListLength(final boolean pendingListLength) {
		this.pendingListLength = pendingListLength;
	}
	
	/**
	 * @return : List
	 */
	public List<ReqToBackOutApprover> getPendingCancellationIteratorList() {
		return this.pendingCancellationIteratorList;
	}
	

	/**
		 * @param pendingCancellationIteratorList : pendingCancellationIteratorList.
		 */
	public void setPendingCancellationIteratorList(final
			List<ReqToBackOutApprover> pendingCancellationIteratorList) {
		this.pendingCancellationIteratorList = pendingCancellationIteratorList;
	}
	
	/**
	 * @return : final String
	 */
	public boolean isPendingCancellationListLength() {
		return this.pendingCancellationListLength;
	}
	

	/**
		 * @param pendingCancellationListLength : pendingCancellationListLength.
		 */
	public void setPendingCancellationListLength(
			final boolean pendingCancellationListLength) {
		this.pendingCancellationListLength = pendingCancellationListLength;
	}
	
	/**
	 * @return : List
	 */
	public List<ReqToBackOutApprover> getApproveredIteratorList() {
		return this.approveredIteratorList;
	}
	

	/**
		 * @param approveredIteratorList : approveredIteratorList.
		 */
	public void setApproveredIteratorList(final List<ReqToBackOutApprover> approveredIteratorList) {
		this.approveredIteratorList = approveredIteratorList;
	}
	
	/**
	 * @return : final String
	 */
	public boolean isApprovedListLength() {
		return this.approvedListLength;
	}
	

	/**
		 * @param approvedListLength : approvedListLength.
		 */
	public void setApprovedListLength(final boolean approvedListLength) {
		this.approvedListLength = approvedListLength;
	}
	
	/**
	 * @return : List
	 */
	public List<ReqToBackOutApprover> getRejectedIteratorList() {
		return this.rejectedIteratorList;
	}
	

	/**
		 * @param rejectedIteratorList : rejectedIteratorList.
		 */
	public void setRejectedIteratorList(final List<ReqToBackOutApprover> rejectedIteratorList) {
		this.rejectedIteratorList = rejectedIteratorList;
	}
	
	/**
	 * @return : final String
	 */
	public boolean isRejectedListLength() {
		return this.rejectedListLength;
	}
	

	/**
		 * @param rejectedListLength : rejectedListLength.
		 */
	public void setRejectedListLength(final boolean rejectedListLength) {
		this.rejectedListLength = rejectedListLength;
	}
	
	/**
	 * @return : final String
	 */
	public final String getVouherHiddenID() {
		return this.vouherHiddenID;
	}
	
	/**
	 * @param vouherHiddenID : vouherHiddenID.
	 */
	public void setVouherHiddenID(final String vouherHiddenID) {
		this.vouherHiddenID = vouherHiddenID;
	}
	
	/**
	 * @return : final String
	 */
	public final String getEmployeeIDIterator() {
		return this.employeeIDIterator;
	}
	

	/**
		 * @param employeeIDIterator : employeeIDIterator.
		 */
	public void setEmployeeIDIterator(final String employeeIDIterator) {
		this.employeeIDIterator = employeeIDIterator;
	}
	
	/**
	 * @return : final String
	 */
	public final String getEmployeeTokenIterator() {
		return this.employeeTokenIterator;
	}
	

	/**
		 * @param employeeTokenIterator : employeeTokenIterator.
		 */
	public void setEmployeeTokenIterator(final String employeeTokenIterator) {
		this.employeeTokenIterator = employeeTokenIterator;
	}
	
	/**
	 * @return : final String
	 */
	public final String getEmployeeNameIterator() {
		return this.employeeNameIterator;
	}
	

	/**
		 * @param employeeNameIterator : employeeNameIterator.
		 */
	public void setEmployeeNameIterator(final String employeeNameIterator) {
		this.employeeNameIterator = employeeNameIterator;
	}
	
	/**
	 * @return : final String
	 */
	public final String getApplicationDateIterator() {
		return this.applicationDateIterator;
	}
	

	/**
		 * @param applicationDateIterator : applicationDateIterator.
		 */
	public void setApplicationDateIterator(final String applicationDateIterator) {
		this.applicationDateIterator = applicationDateIterator;
	}
	
	/**
	 * @return : final String
	 */
	public final String getMyPage() {
		return this.myPage;
	}
	

	/**
		 * @param myPage : myPage.
		 */
	public void setMyPage(final String myPage) {
		this.myPage = myPage;
	}
	
	/**
	 * @return : final String
	 */
	public final String getTotalRecords() {
		return this.totalRecords;
	}
	

	/**
		 * @param totalRecords : totalRecords.
		 */
	public void setTotalRecords(final String totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	/**
	 * @return : final String
	 */
	public final String getListType() {
		return this.listType;
	}
	

	/**
		 * @param listType : listType.
		 */
	public void setListType(final String listType) {
		this.listType = listType;
	}
	
	/**
	 * @return : final String
	 */
	public final String getRequestID() {
		return this.requestID;
	}
	

	/**
		 * @param requestID : requestID.
		 */
	public void setRequestID(final String requestID) {
		this.requestID = requestID;
	}
	
	/**
	 * @return : final String
	 */
	public final String getEmployeeID() {
		return this.employeeID;
	}
	

	/**
		 * @param employeeID : employeeID.
		 */
	public void setEmployeeID(final String employeeID) {
		this.employeeID = employeeID;
	}
	
	/**
	 * @return : final String
	 */
	public final String getEmployeeToken() {
		return this.employeeToken;
	}
	

	/**
		 * @param employeeToken : employeeToken.
		 */
	public void setEmployeeToken(final String employeeToken) {
		this.employeeToken = employeeToken;
	}
	
	/**
	 * @return : final String
	 */
	public final String getFromName() {
		return this.fromName;
	}
	

	/**
		 * @param fromName : fromName.
		 */
	public void setFromName(final String fromName) {
		this.fromName = fromName;
	}
	
	/**
	 * @return : final String
	 */
	public final String getToDate() {
		return this.toDate;
	}
	

	/**
		 * @param toDate : toDate.
		 */
	public void setToDate(final String toDate) {
		this.toDate = toDate;
	}
	
	/**
	 * @return : final String
	 */
	public final String getVendorName() {
		return this.vendorName;
	}
	

	/**
		 * @param vendorName : vendorName.
		 */
	public void setVendorName(final String vendorName) {
		this.vendorName = vendorName;
	}
	
	/**
	 * @return : final String
	 */
	public final String getVendorNumber() {
		return this.vendorNumber;
	}
	

	/**
		 * @param vendorNumber : vendorNumber.
		 */
	public void setVendorNumber(final String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}
	
	/**
	 * @return : final String
	 */
	public final String getPurchaseOrderNumber() {
		return this.purchaseOrderNumber;
	}
	

	/**
		 * @param purchaseOrderNumber : purchaseOrderNumber.
		 */
	public void setPurchaseOrderNumber(final String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	
	/**
	 * @return : final String
	 */
	public final String getLineNumber() {
		return this.lineNumber;
	}
	

	/**
		 * @param lineNumber : lineNumber.
		 */
	public void setLineNumber(final String lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	/**
	 * @return : final String
	 */
	public final String getQuantity() {
		return this.quantity;
	}
	

	/**
		 * @param quantity : quantity.
		 */
	public void setQuantity(final String quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return : final String
	 */
	public final String getVoucherNumber() {
		return this.voucherNumber;
	}
	

	/**
		 * @param voucherNumber : voucherNumber.
		 */
	public void setVoucherNumber(final String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	
	/**
	 * @return : final String
	 */
	public final String getReasonForRequest() {
		return this.reasonForRequest;
	}
	

	/**
		 * @param reasonForRequest : reasonForRequest.
		 */
	public void setReasonForRequest(final String reasonForRequest) {
		this.reasonForRequest = reasonForRequest;
	}
	
	/**
	 * @return : final String
	 */
	public final String getRma() {
		return this.rma;
	}
	

	/**
		 * @param rma : rma.
		 */
	public void setRma(final String rma) {
		this.rma = rma;
	}
	
	/**
	 * @return : final String
	 */
	public final String getAirBillNumber() {
		return this.airBillNumber;
	}
	

	/**
		 * @param airBillNumber : airBillNumber.
		 */
	public void setAirBillNumber(final String airBillNumber) {
		this.airBillNumber = airBillNumber;
	}
	
	/**
	 * @return : final String
	 */
	public final String getDidVendorIssueCreditMemo() {
		return this.didVendorIssueCreditMemo;
	}
	

	/**
		 * @param didVendorIssueCreditMemo : didVendorIssueCreditMemo.
		 */
	public void setDidVendorIssueCreditMemo(final String didVendorIssueCreditMemo) {
		this.didVendorIssueCreditMemo = didVendorIssueCreditMemo;
	}
	
	/**
	 * @return : final String
	 */
	public final String getCreditMemoRadio() {
		return this.creditMemoRadio;
	}
	

	/**
		 * @param creditMemoRadio : creditMemoRadio.
		 */
	public void setCreditMemoRadio(final String creditMemoRadio) {
		this.creditMemoRadio = creditMemoRadio;
	}
	
	/**
	 * @return : final String
	 */
	public final String getComments() {
		return this.comments;
	}
	

	/**
		 * @param comments : comments.
		 */
	public void setComments(final String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return : final String
	 */
	public final String getStatus() {
		return this.status;
	}
	

	/**
		 * @param status : status.
		 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * @return : final String
	 */
	public final String getListTypeDetailPage() {
		return this.listTypeDetailPage;
	}
	

	/**
		 * @param listTypeDetailPage : listTypeDetailPage.
		 */
	public void setListTypeDetailPage(final String listTypeDetailPage) {
		this.listTypeDetailPage = listTypeDetailPage;
	}
	
	/**
	 * @return : final String
	 */
	public final String getMyPageDrafted() {
		return this.myPageDrafted;
	}
	

	/**
		 * @param myPageDrafted : myPageDrafted.
		 */
	public void setMyPageDrafted(final String myPageDrafted) {
		this.myPageDrafted = myPageDrafted;
	}
	
	/**
	 * @return : final String
	 */
	public final String getMyPagePendingCancel() {
		return this.myPagePendingCancel;
	}
	

	/**
		 * @param myPagePendingCancel : myPagePendingCancel.
		 */
	public void setMyPagePendingCancel(final String myPagePendingCancel) {
		this.myPagePendingCancel = myPagePendingCancel;
	}
	
	/**
	 * @return : final String
	 */
	public final String getMyPageApproved() {
		return this.myPageApproved;
	}
	

	/**
		 * @param myPageApproved : myPageApproved.
		 */
	public void setMyPageApproved(final String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}
	
	/**
	 * @return : final String
	 */
	public final String getMyPageRejected() {
		return this.myPageRejected;
	}
	

	/**
		 * @param myPageRejected : myPageRejected.
		 */
	public void setMyPageRejected(final String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	
	/**
	 * @return : final String
	 */
	public final String getVouherHiddenStatus() {
		return this.vouherHiddenStatus;
	}
	

	/**
		 * @param vouherHiddenStatus : vouherHiddenStatus.
		 */
	public void setVouherHiddenStatus(final String vouherHiddenStatus) {
		this.vouherHiddenStatus = vouherHiddenStatus;
	}
	
	/**
	 * @return : final String
	 */
	public final String getApprName() {
		return this.apprName;
	}
	

	/**
		 * @param apprName : apprName.
		 */
	public void setApprName(final String apprName) {
		this.apprName = apprName;
	}
	
	/**
	 * @return : final String
	 */
	public final String getApprComments() {
		return this.apprComments;
	}
	

	/**
		 * @param apprComments : apprComments.
		 */
	public void setApprComments(final String apprComments) {
		this.apprComments = apprComments;
	}
	
	/**
	 * @return : final String
	 */
	public final String getApprDate() {
		return this.apprDate;
	}
	

	/**
		 * @param apprDate : apprDate.
		 */
	public void setApprDate(final String apprDate) {
		this.apprDate = apprDate;
	}
	
	/**
	 * @return : final String
	 */
	public final String getApprStatus() {
		return this.apprStatus;
	}
	

	/**
		 * @param apprStatus : apprStatus.
		 */
	public void setApprStatus(final String apprStatus) {
		this.apprStatus = apprStatus;
	}
	
	/**
	 * @return : List
	 */
	public List<ReqToBackOutApprover> getApproverCommentList() {
		return this.approverCommentList;
	}
	

	/**
		 * @param approverCommentList : approverCommentList.
		 */
	public void setApproverCommentList(List<ReqToBackOutApprover> approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	
}
