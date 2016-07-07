package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1380.
 *
 */
public class ReqToBackOutVoucher extends BeanBase {
	/**	 apprName. *	 */
	private String apprName;
	
	/**	 apprComments. *	 */
	private String apprComments;
	
	/**	 apprDate. *	 */
	private String apprDate;
	
	/**	 apprStatus. *	 */
	private String apprStatus;
	
	/**	 approverCommentList. *	 */
	private List<ReqToBackOutVoucher> approverCommentList;
	
	/**	 approverCommentsFlag. *	 */
	private boolean approverCommentsFlag;

	/**	 myPageDrafted. *	 */
	private String myPageDrafted;
	
	/**	 myPageInProcess. *	 */
	private String myPageInProcess;
	
	/**	 myPageSentBack. *	 */
	private String myPageSentBack;
	
	/**	 myPageApproved. *	 */
	private String myPageApproved;
	
	/**	 myPageApprovedCancel. *	 */
	private String myPageApprovedCancel;
	
	/**	 myPageCancel. *	 */
	private String myPageCancel;
	
	/**	 myPageRejected. *	 */
	private String myPageRejected;
	
	/**	 myPageCancelRejected. *	 */
	private String myPageCancelRejected;

	/**	 draftVoucherIteratorList. *	 */
	private List<ReqToBackOutVoucher> draftVoucherIteratorList;
	
	/**	 draftVoucherListLength. *	 */
	private boolean draftVoucherListLength;
	
	/**	 inProcessVoucherIteratorList. *	 */
	private List<ReqToBackOutVoucher> inProcessVoucherIteratorList;
	
	/**	 inProcessVoucherListLength. *	 */
	private boolean inProcessVoucherListLength;
	
	/**	 sentBackVoucherIteratorList. *	 */
	private List<ReqToBackOutVoucher> sentBackVoucherIteratorList;
	
	/**	 sentBackVoucherListLength. *	 */
	private boolean sentBackVoucherListLength;
	
	/**	 approvedVoucherIteratorList. *	 */
	private List<ReqToBackOutVoucher> approvedVoucherIteratorList;
	
	/**	 approvedVoucherListLength. *	 */
	private boolean approvedVoucherListLength;
	
	/**	 approvedCancellationVoucherIteratorList. *	 */
	private List<ReqToBackOutVoucher> approvedCancellationVoucherIteratorList;
	
	/**	 approvedCancellationVoucherListLength. *	 */
	private boolean approvedCancellationVoucherListLength;
	
	/**	 cancelledVoucherIteratorList. *	 */
	private List<ReqToBackOutVoucher> cancelledVoucherIteratorList;
	
	/**	 cancelledVoucherListLength. *	 */
	private boolean cancelledVoucherListLength;
	
	/**	 rejectedVoucherIteratorList. *	 */
	private List<ReqToBackOutVoucher> rejectedVoucherIteratorList;
	
	/**	 rejectedVoucherListLength. *	 */
	private boolean rejectedVoucherListLength;
	
	/**	 rejectedCancelVoucherIteratorList. *	 */
	private List<ReqToBackOutVoucher> rejectedCancelVoucherIteratorList;
	
	/**	 rejectedCancelVoucherListLength. *	 */
	private boolean rejectedCancelVoucherListLength;

	/** vouherHiddenID. *	 */
	private String vouherHiddenID;
	
	/** vouherHiddenStatus. *	 */
	private String vouherHiddenStatus;
	
	/** applicationStatus. *	 */
	private String applicationStatus;
	
	/** trackingNumberIterator. *	 */
	private String trackingNumberIterator;
	
	/** employeeNameIterator. *	 */
	private String employeeNameIterator;
	
	/** applicationDateIterator. *	 */
	private String applicationDateIterator;
	
	/** myPage. *	 */
	private String myPage;
	
	/** totalRecords. *	 */
	private String totalRecords;
	
	/** listType. *	 */
	private String listType;
	
	/** requestID. *	 */
	private String requestID;
	
	/** employeeID. *	 */
	private String employeeID;
	
	/** employeeToken. *	 */
	private String employeeToken;
	
	/** fromName. *	 */
	private String fromName;
	
	/** toDate. *	 */
	private String toDate;
	
	/** vendorName. *	 */
	private String vendorName;
	
	/** vendorID. *	 */
	private String vendorID;
	
	/** vendorNumber. *	 */
	private String vendorNumber;
	
	/** purchaseOrderNumber. *	 */
	private String purchaseOrderNumber;
	
	/** lineNumber. *	 */
	private String lineNumber;
	
	/** quantity. *	 */
	private String quantity;
	
	/** voucherNumber. *	 */
	private String voucherNumber;
	
	/** reasonForRequest. *	 */
	private String reasonForRequest;
	
	/** rma. *	 */
	private String rma;
	
	/**	 * airBillNumber.	 */
	private String airBillNumber;
	
	
	/**
	 * didVendorIssueCreditMemo.
	 */
	private String didVendorIssueCreditMemo;
	
	
	/**
	 * creditMemoRadio.
	 */
	private String creditMemoRadio;
	
	
	/**
	 * comments.
	 */
	private String comments;
	
	
	/**
	 * status.
	 */
	private String status;
	
	
	/**
	 * listTypeDetailPage.
	 */
	private String listTypeDetailPage;
	
	/**
	 * trackingNumber.
	 */
	private String trackingNumber;
	
	
	/**
	 * completedByID.
	 */
	private String completedByID;
	
	/**
	 * completedByName.
	 */
	private String completedByName;
	
	/**
	 * completedOn.
	 */
	private String completedOn;

	/**
	 * @return : String
	 */
	public String getCompletedOn() {
		return this.completedOn;
	}

	/**
	 * @param completedOn : completedOn.
	 */
	public void setCompletedOn(final String completedOn) {
		this.completedOn = completedOn;
	}

	/**
	 * @return : String
	 */
	public String getCompletedByName() {
		return this.completedByName;
	}

	/**
	 * @param completedByName : completedByName.
	 */
	public void setCompletedByName(final String completedByName) {
		this.completedByName = completedByName;
	}

	/**
	 * @return : String
	 */
	public String getCompletedByID() {
		return this.completedByID;
	}

	/**
	 * @param completedByID : completedByID.
	 */
	public void setCompletedByID(final String completedByID) {
		this.completedByID = completedByID;
	}

	/**
	 * @return : String
	 */
	public String getTrackingNumber() {
		return this.trackingNumber;
	}

	/**
	 * @param trackingNumber : trackingNumber.
	 */
	public void setTrackingNumber(final String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	/**
	 * @return : String
	 */
	public String getListTypeDetailPage() {
		return this.listTypeDetailPage;
	}

	/**
	 * @param listTypeDetailPage : listTypeDetailPage.
	 */
	public void setListTypeDetailPage(final String listTypeDetailPage) {
		this.listTypeDetailPage = listTypeDetailPage;
	}

	/**
	 * @return : ArrayList
	 */
	public List<ReqToBackOutVoucher> getDraftVoucherIteratorList() {
		return this.draftVoucherIteratorList;
	}

	/**
	 * @param draftVoucherIteratorList : draftVoucherIteratorList.
	 */
	public void setDraftVoucherIteratorList(final List<ReqToBackOutVoucher> 
		draftVoucherIteratorList) {
		this.draftVoucherIteratorList = draftVoucherIteratorList;
	}
	
	/**
	 * @return : boolean
	 */
	public boolean isDraftVoucherListLength() {
		return this.draftVoucherListLength;
	}

	/**
	 * @param draftVoucherListLength : draftVoucherListLength.
	 */
	public void setDraftVoucherListLength(final boolean 
			draftVoucherListLength) {
		this.draftVoucherListLength = draftVoucherListLength;
	}

	/**
	 * @return : List<ReqToBackOutVoucher>
	 */
	public List<ReqToBackOutVoucher> getInProcessVoucherIteratorList() {
		return this.inProcessVoucherIteratorList;
	}

	/**
	 * @param inProcessVoucherIteratorList : inProcessVoucherIteratorList.
	 */
	public void setInProcessVoucherIteratorList(
			final List<ReqToBackOutVoucher> inProcessVoucherIteratorList) {
		this.inProcessVoucherIteratorList = inProcessVoucherIteratorList;
	}

	/**
	 * @return : boolean
	 */
	public boolean isInProcessVoucherListLength() {
		return this.inProcessVoucherListLength;
	}

	/**
	 * @param inProcessVoucherListLength : inProcessVoucherListLength.
	 */
	public void setInProcessVoucherListLength(final boolean 
			inProcessVoucherListLength) {
		this.inProcessVoucherListLength = inProcessVoucherListLength;
	}

	/**
	 * @return : String
	 */
	public String getVouherHiddenID() {
		return this.vouherHiddenID;
	}

	/**
	 * @param vouherHiddenID : vouherHiddenID.
	 */
	public void setVouherHiddenID(final String vouherHiddenID) {
		this.vouherHiddenID = vouherHiddenID;
	}

	/**
	 * @return : String
	 */
	public String getTrackingNumberIterator() {
		return this.trackingNumberIterator;
	}

	/**
	 * @param trackingNumberIterator : trackingNumberIterator.
	 */
	public void setTrackingNumberIterator(final String trackingNumberIterator) {
		this.trackingNumberIterator = trackingNumberIterator;
	}

	/**
	 * @return : String
	 */
	public String getEmployeeNameIterator() {
		return this.employeeNameIterator;
	}

	/**
	 * @param employeeNameIterator : employeeNameIterator.
	 */
	public void setEmployeeNameIterator(final String employeeNameIterator) {
		this.employeeNameIterator = employeeNameIterator;
	}

	/**
	 * @return : String
	 */
	public String getApplicationDateIterator() {
		return this.applicationDateIterator;
	}

	/**
	 * @param applicationDateIterator : applicationDateIterator.
	 */
	public void setApplicationDateIterator(final String 
			applicationDateIterator) {
		this.applicationDateIterator = applicationDateIterator;
	}

	/**
	 * @return : String
	 */
	public String getMyPage() {
		return this.myPage;
	}

	/**
	 * @param myPage :myPage.
	 */
	public void setMyPage(final String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return : String
	 */
	public String getTotalRecords() {
		return this.totalRecords;
	}

	/**
	 * @param totalRecords : totalRecords.
	 */
	public void setTotalRecords(final String totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return : String
	 */
	public String getRequestID() {
		return this.requestID;
	}

	/**
	 * @param requestID : requestID.
	 */
	public void setRequestID(final String requestID) {
		this.requestID = requestID;
	}

	/**
	 * @return : String
	 */
	public String getEmployeeID() {
		return this.employeeID;
	}

	/**
	 * @param employeeID : employeeID.
	 */
	public void setEmployeeID(final String employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return : String
	 */
	public String getFromName() {
		return this.fromName;
	}

	/**
	 * @param fromName : fromName.
	 */
	public void setFromName(final String fromName) {
		this.fromName = fromName;
	}

	/**
	 * @return : String
	 */
	public String getToDate() {
		return this.toDate;
	}

	/**
	 * @param toDate : toDate.
	 */
	public void setToDate(final String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return : String
	 */
	public String getVendorName() {
		return this.vendorName;
	}

	/**
	 * @param vendorName : vendorName.
	 */
	public void setVendorName(final String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * @return : String
	 */
	public String getVendorNumber() {
		return this.vendorNumber;
	}

	/**
	 * @param vendorNumber : vendorNumber.
	 */
	public void setVendorNumber(final String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}
	
	/**
	 * @return : String
	 */
	public String getLineNumber() {
		return this.lineNumber;
	}

	/**
	 * @param lineNumber : lineNumber.
	 */
	public void setLineNumber(final String lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * @return : String
	 */
	public String getQuantity() {
		return this.quantity;
	}

	/**
	 * @param quantity : quantity.
	 */
	public void setQuantity(final String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return : String
	 */
	public String getVoucherNumber() {
		return this.voucherNumber;
	}

	/**
	 * @param voucherNumber : voucherNumber.
	 */
	public void setVoucherNumber(final String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	/**
	 * @return : String
	 */
	public String getReasonForRequest() {
		return this.reasonForRequest;
	}

	/**
	 * @param reasonForRequest : reasonForRequest.
	 */
	public void setReasonForRequest(final String reasonForRequest) {
		this.reasonForRequest = reasonForRequest;
	}

	/**
	 * @return : String
	 */
	public String getRma() {
		return this.rma;
	}

	/**
	 * @param rma : rma.
	 */
	public void setRma(final String rma) {
		this.rma = rma;
	}
	
	/**
	 * @return : String
	 */
	public String getAirBillNumber() {
		return this.airBillNumber;
	}

	/**
	 * @param airBillNumber : airBillNumber.
	 */
	public void setAirBillNumber(final String airBillNumber) {
		this.airBillNumber = airBillNumber;
	}
	
	/**
	 * @return : String
	 */
	public String getDidVendorIssueCreditMemo() {
		return this.didVendorIssueCreditMemo;
	}

	/**
	 * @param didVendorIssueCreditMemo : didVendorIssueCreditMemo.
	 */
	public void setDidVendorIssueCreditMemo(final String 
			didVendorIssueCreditMemo) {
		this.didVendorIssueCreditMemo = didVendorIssueCreditMemo;
	}
	
	/**
	 * @return : String
	 */
	public String getCreditMemoRadio() {
		return this.creditMemoRadio;
	}

	/**
	 * @param creditMemoRadio : creditMemoRadio.
	 */
	public void setCreditMemoRadio(final String creditMemoRadio) {
		this.creditMemoRadio = creditMemoRadio;
	}
	
	/**
	 * @return : String
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * @param comments : comments.
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return : String
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @param status : status.
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * @return : String
	 */
	public String getListType() {
		return this.listType;
	}

	/**
	 * @param listType : listType.
	 */
	public void setListType(final String listType) {
		this.listType = listType;
	}
	
	/**
	 * @return : List<ReqToBackOutVoucher>
	 */
	public List<ReqToBackOutVoucher> getApprovedVoucherIteratorList() {
		return this.approvedVoucherIteratorList;
	}

	/**
	 * @param approvedVoucherIteratorList : approvedVoucherIteratorList.
	 */
	public void setApprovedVoucherIteratorList(
			final List<ReqToBackOutVoucher> approvedVoucherIteratorList) {
		this.approvedVoucherIteratorList = approvedVoucherIteratorList;
	}
	
	/**
	 * @return : boolean
	 */
	public boolean isApprovedVoucherListLength() {
		return this.approvedVoucherListLength;
	}

	/**
	 * @param approvedVoucherListLength : approvedVoucherListLength.
	 */
	public void setApprovedVoucherListLength(final boolean 
			approvedVoucherListLength) {
		this.approvedVoucherListLength = approvedVoucherListLength;
	}
	
	/**
	 * @return : List<ReqToBackOutVoucher>
	 */
	public List<ReqToBackOutVoucher> 
	getApprovedCancellationVoucherIteratorList() {
		return this.approvedCancellationVoucherIteratorList;
	}

	/**
	 * @param approvedCancellationVoucherIteratorList : 
	 * approvedCancellationVoucherIteratorList.
	 */
	public void setApprovedCancellationVoucherIteratorList(
			final List<ReqToBackOutVoucher> 
			approvedCancellationVoucherIteratorList) {
		this.approvedCancellationVoucherIteratorList = 
			approvedCancellationVoucherIteratorList;
	}
	
	/**
	 * @return : boolean
	 */
	public boolean isApprovedCancellationVoucherListLength() {
		return this.approvedCancellationVoucherListLength;
	}

	/**
	 * @param approvedCancellationVoucherListLength : 
	 * approvedCancellationVoucherListLength.
	 */
	public void setApprovedCancellationVoucherListLength(
			final boolean approvedCancellationVoucherListLength) {
		this.approvedCancellationVoucherListLength = 
			approvedCancellationVoucherListLength;
	}
	
	/**
	 * @return : List<ReqToBackOutVoucher>
	 */
	public List<ReqToBackOutVoucher> getCancelledVoucherIteratorList() {
		return this.cancelledVoucherIteratorList;
	}

	/**
	 * @param cancelledVoucherIteratorList : cancelledVoucherIteratorList.
	 */
	public void setCancelledVoucherIteratorList(
			final List<ReqToBackOutVoucher> cancelledVoucherIteratorList) {
		this.cancelledVoucherIteratorList = cancelledVoucherIteratorList;
	}

	/**
	 * @return : boolean
	 */
	public boolean isCancelledVoucherListLength() {
		return this.cancelledVoucherListLength;
	}

	/**
	 * @param cancelledVoucherListLength : cancelledVoucherListLength.
	 */
	public void setCancelledVoucherListLength(final boolean 
			cancelledVoucherListLength) {
		this.cancelledVoucherListLength = cancelledVoucherListLength;
	}
	
	/**
	 * @return : List<ReqToBackOutVoucher>
	 */
	public List<ReqToBackOutVoucher> getRejectedVoucherIteratorList() {
		return this.rejectedVoucherIteratorList;
	}

	/**
	 * @param rejectedVoucherIteratorList : rejectedVoucherIteratorList.
	 */
	public void setRejectedVoucherIteratorList(
			final List<ReqToBackOutVoucher> rejectedVoucherIteratorList) {
		this.rejectedVoucherIteratorList = rejectedVoucherIteratorList;
	}
	
	/**
	 * @return : boolean
	 */
	public boolean isRejectedVoucherListLength() {
		return this.rejectedVoucherListLength;
	}

	/**
	 * @param rejectedVoucherListLength : rejectedVoucherListLength.
	 */
	public void setRejectedVoucherListLength(final boolean 
			rejectedVoucherListLength) {
		this.rejectedVoucherListLength = rejectedVoucherListLength;
	}
	
	/**
	 * @return : List<ReqToBackOutVoucher>
	 */
	public List<ReqToBackOutVoucher> getRejectedCancelVoucherIteratorList() {
		return this.rejectedCancelVoucherIteratorList;
	}

	/**
	 * @param rejectedCancelVoucherIteratorList : 
	 * rejectedCancelVoucherIteratorList.
	 */
	public void setRejectedCancelVoucherIteratorList(
			final List<ReqToBackOutVoucher> rejectedCancelVoucherIteratorList) {
		this.rejectedCancelVoucherIteratorList = 
			rejectedCancelVoucherIteratorList;
	}

	/**
	 * @return : boolean
	 */
	public boolean isRejectedCancelVoucherListLength() {
		return this.rejectedCancelVoucherListLength;
	}

	/**
	 * @param rejectedCancelVoucherListLength : rejectedCancelVoucherListLength.
	 */
	public void setRejectedCancelVoucherListLength(
			final boolean rejectedCancelVoucherListLength) {
		this.rejectedCancelVoucherListLength = rejectedCancelVoucherListLength;
	}
	
	/**
	 * @return : String
	 */
	public String getEmployeeToken() {
		return this.employeeToken;
	}

	/**
	 * @param employeeToken : employeeToken.
	 */
	public void setEmployeeToken(final String employeeToken) {
		this.employeeToken = employeeToken;
	}
	
	/**
	 * @return : List<ReqToBackOutVoucher>
	 */
	public List<ReqToBackOutVoucher> getSentBackVoucherIteratorList() {
		return this.sentBackVoucherIteratorList;
	}

	/**
	 * @param sentBackVoucherIteratorList : sentBackVoucherIteratorList.
	 */
	public void setSentBackVoucherIteratorList(
			final List<ReqToBackOutVoucher> sentBackVoucherIteratorList) {
		this.sentBackVoucherIteratorList = sentBackVoucherIteratorList;
	}
	
	/**
	 * @return : boolean
	 */
	public boolean isSentBackVoucherListLength() {
		return this.sentBackVoucherListLength;
	}

	/**
	 * @param sentBackVoucherListLength : sentBackVoucherListLength.
	 */
	public void setSentBackVoucherListLength(final boolean 
			sentBackVoucherListLength) {
		this.sentBackVoucherListLength = sentBackVoucherListLength;
	}
	
	/**
	 * @return : String
	 */
	public String getPurchaseOrderNumber() {
		return this.purchaseOrderNumber;
	}

	/**
	 * @param purchaseOrderNumber : purchaseOrderNumber.
	 */
	public void setPurchaseOrderNumber(final String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	
	/**
	 * @return : String
	 */
	public String getMyPageDrafted() {
		return this.myPageDrafted;
	}

	/**
	 * @param myPageDrafted : myPageDrafted.
	 */
	public void setMyPageDrafted(final String myPageDrafted) {
		this.myPageDrafted = myPageDrafted;
	}
	
	/**
	 * @return : String
	 */
	public String getMyPageInProcess() {
		return this.myPageInProcess;
	}

	/**
	 * @param myPageInProcess : myPageInProcess.
	 */
	public void setMyPageInProcess(final String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}
	
	/**
	 * @return : String
	 */
	public String getMyPageSentBack() {
		return this.myPageSentBack;
	}

	/**
	 * @param myPageSentBack : myPageSentBack.
	 */
	public void setMyPageSentBack(final String myPageSentBack) {
		this.myPageSentBack = myPageSentBack;
	}

	/**
	 * @return : String
	 */
	public String getMyPageApproved() {
		return this.myPageApproved;
	}

	/**
	 * @param myPageApproved : myPageApproved.
	 */
	public void setMyPageApproved(final String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}

	/**
	 * @return : String
	 */
	public String getMyPageApprovedCancel() {
		return this.myPageApprovedCancel;
	}

	/**
	 * @param myPageApprovedCancel : myPageApprovedCancel.
	 */
	public void setMyPageApprovedCancel(final String myPageApprovedCancel) {
		this.myPageApprovedCancel = myPageApprovedCancel;
	}

	/**
	 * @return : String
	 */
	public String getMyPageCancel() {
		return this.myPageCancel;
	}

	
	/**
	 * @param myPageCancel : myPageCancel.
	 */
	public void setMyPageCancel(final String myPageCancel) {
		this.myPageCancel = myPageCancel;
	}

	/**
	 * @return : String
	 */
	public String getMyPageRejected() {
		return this.myPageRejected;
	}

	/**
	 * @param myPageRejected : myPageRejected.
	 */
	public void setMyPageRejected(final String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}

	/**
	 * @return : String
	 */
	public String getMyPageCancelRejected() {
		return this.myPageCancelRejected;
	}

	/**
	 * @param myPageCancelRejected : myPageCancelRejected.
	 */
	public void setMyPageCancelRejected(final String myPageCancelRejected) {
		this.myPageCancelRejected = myPageCancelRejected;
	}

	/**
	 * @return : String
	 */
	public String getApprName() {
		return this.apprName;
	}

	/**
	 * @param apprName : apprName.
	 */
	public void setApprName(final String apprName) {
		this.apprName = apprName;
	}

	/**
	 * @return : String
	 */
	public String getApprComments() {
		return this.apprComments;
	}

	/**
	 * @param apprComments : apprComments.
	 */
	public void setApprComments(final String apprComments) {
		this.apprComments = apprComments;
	}

	/**
	 * @return : String
	 */
	public String getApprDate() {
		return this.apprDate;
	}

	/**
	 * @param apprDate : apprDate.
	 */
	public void setApprDate(final String apprDate) {
		this.apprDate = apprDate;
	}

	/**
	 * @return : String
	 */
	public String getApprStatus() {
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
	public List<ReqToBackOutVoucher> getApproverCommentList() {
		return this.approverCommentList;
	}

	/**
	 * @param approverCommentList : approverCommentList.
	 */
	public void setApproverCommentList(final List<ReqToBackOutVoucher> 
	approverCommentList) {
		this.approverCommentList = approverCommentList;
	}

	/**
	 * @return : boolean
	 */
	public boolean isApproverCommentsFlag() {
		return this.approverCommentsFlag;
	}

	/**
	 * @param approverCommentsFlag : approverCommentsFlag.
	 */
	public void setApproverCommentsFlag(final boolean approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
	}

	/**
	 * @return : String
	 */
	public String getVendorID() {
		return this.vendorID;
	}

	/**
	 * @param vendorID : vendorID.
	 */
	public void setVendorID(final String vendorID) {
		this.vendorID = vendorID;
	}

	/**
	 * @return : String
	 */
	public String getVouherHiddenStatus() {
		return this.vouherHiddenStatus;
	}

	/**
	 * @param vouherHiddenStatus : vouherHiddenStatus.
	 */
	public void setVouherHiddenStatus(final String vouherHiddenStatus) {
		this.vouherHiddenStatus = vouherHiddenStatus;
	}

	/**
	 * @return : String
	 */
	public String getApplicationStatus() {
		return this.applicationStatus;
	}

	/**
	 * @param applicationStatus : applicationStatus
	 */
	public void setApplicationStatus(final String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

}
