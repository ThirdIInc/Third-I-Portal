package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

public class CapitalExpenditureApproval extends BeanBase {

	/**	 . *	 */
	private String trackingNumber = "";
	/**	 . *	 */
	private String applicationStatus = "";
	/**	 . *	 */
	private String dataPath = "";
	/**	 . *	 */
	private String capitalExpID = "";
	/**	 . *	 */
	private String status = "";
	/**	 . *	 */
	private String approverComments = "";
	/**	 . *	 */
	private String capitalHiddenID = "";
	/**	 . *	 */
	private String hiddenStatus = "";
	/**	 . *	 */
	private String trackingNumberIterator = "";
	/**	 . *	 */
	private String completedByIterator = "";
	/**	 . *	 */
	private String completedDateIterator = "";
	
	// Approver Comments List 
	/**	 . *	 */
	private String apprName = "";
	/**	 . *	 */
	private String apprComments = "";
	/**	 . *	 */
	private String apprDate = "";
	/**	 . *	 */
	private String apprStatus = "";
	/**	 . *	 */
	private List<CapitalExpenditureApproval> approverCommentList;
	/**	 . *	 */
	private boolean approverCommentsFlag;
	/**	 . *	 */
	private String myPage = "";
	/**	 . *	 */
	private String myPagePendingCancel = "";
	/**	 . *	 */
	private String myPageApproved = "";
	/**	 . *	 */
	private String myPageRejected = "";
	/**	 . *	 */
	private String totalRecords = "";
	/**	 . *	 */
	private String listType = "";
	/**	 . *	 */
	private List<CapitalExpenditureApproval> pendingIteratorList;
	/**	 . *	 */
	private boolean pendingListLength;
	/**	 . *	 */
	private List<CapitalExpenditureApproval> pendingCancellationIteratorList;
	/**	 . *	 */
	private boolean  pendingCancellationListLength;
	/**	 . *	 */
	private List<CapitalExpenditureApproval> approveredIteratorList;
	/**	 . *	 */
	private boolean approvedListLength;
	/**	 . *	 */
	private List<CapitalExpenditureApproval> rejectedIteratorList;
	/**	 . *	 */
	private boolean rejectedListLength;
	/**	 . *	 */
	private String submittedByName = "";
	/**	 . *	 */
	private String submittedByID = "";
	/**	 . *	 */
	private String submittedDate = "";
	/**	 . *	 */
	private String originalCheckbox = "";
	/**	 . *	 */
	private String localPurchaseCheckbox = "";
	/**	 . *	 */
	private String purchaseDeptCheckbox = "";
	/**	 . *	 */
	private String computerITCheckbox = "";
	/**	 . *	 */
	private String businessJustification = "";
	/**	 . *	 */
	private String reasonForLocalPurchase = "";
	
	//General Information Labels Begins
	/**	 . *	 */
	private String departmentID = "";
	/**	 . *	 */
	private String departmentNumber = "";
	/**	 . *	 */
	private String dateRequired = "";
	/**	 . *	 */
	private String locationID = "";
	/**	 . *	 */
	private String ifOtherLocation = "";
	/**	 . *	 */
	private String location = "";
	/**	 . *	 */
	private String shipToCompany = "";
	/**	 . *	 */
	private String cityID = "";
	/**	 . *	 */
	private String city = "";
	/**	 . *	 */
	private String state = "";
	/**	 . *	 */
	private String streetAddress = "";
	/**	 . *	 */
	private String zipCode = "";
	/**	 . *	 */
	private String attention = "";
	/**	 . *	 */
	private String telePhoneNumber = "";
	//General Information Labels Ends	
	
	//Detail Table List Labels Begins
	/**	 . *	 */
	private List<CapitalExpenditureApproval> detailIteratorList;
	/**	 . *	 */
	private boolean detailListLengthFlag;
	/**	 . *	 */
	private List<CapitalExpenditureApproval> accountOnlyIteratorList;
	/**	 . *	 */
	private String checkDelete = "";
	/**	 . *	 */
	private String quantity = "";
	/**	 . *	 */
	private String description = "";
	/**	 . *	 */
	private String vendorID = "";
	/**	 . *	 */
	private String vendorName = "";
	/**	 . *	 */
	private String unitPrice = "";
	/**	 . *	 */
	private String totalCost = "";
	/**	 . *	 */
	private String descItemNumberItr = "";
	/**	 . *	 */
	private String descHiddenIDItr = "";
	/**	 . *	 */
	private String descQuantityItr = "";
	/**	 . *	 */
	private String descDescriptionItr = "";
	/**	 . *	 */
	private String descVendorIDItr = "";
	/**	 . *	 */
	private String descVendorNameItr = "";
	/**	 . *	 */
	private String descUnitPriceItr = "";
	/**	 . *	 */
	private String descTotalCostItr = "";
	/**	 . *	 */
	//Detail Table List Labels Ends	
	
	// Accounting Use Only Labels Begins
	/**	 . *	 */
	private String tagNumber = "";
	/**	 . *	 */
	private String serialNumber = "";
	/**	 . *	 */
	private String CEARPrice = "";
	/**	 . *	 */
	private String accountItemNumberItr = "";
	/**	 . *	 */
	private String accountHiddenIDItr = "";
	/**	 . *	 */
	private String accountTagNumberItr = "";
	/**	 . *	 */
	private String accountSerialNumberItr = "";
	/**	 . *	 */
	private String accountCEARPriceItr = "";
	/**	 . *	 */
	private String checkAccountDelete = "";
	/**	 . *	 */
	private List<CapitalExpenditureApproval> accountIteratorList;
	/**	 . *	 */
	private boolean accountListLengthFlag;
	/**	 . *	 */
	private double subTotalCEARPriceList;
	// Accounting Use Only Labels Ends 
	
	
	//Quotes/Attachment and Additional Comments Labels Begins
	/**	 . *	 */
	private String quotesAttached = "";
	/**	 . *	 */
	private String quotesAttachedRadioOptionValue = "";
	/**	 . *	 */
	private String quotesReason = "";
	/**	 . *	 */
	private String uploadFileName = "";
	/**	 . *	 */
	private boolean uploadFileNameFlag;
	/**	 . *	 */
	private String comments = "";
	//Quotes/Attachment and Additional Comments Labels Ends	
	
	//Calculation Table Labels Begins
	/**	 . *	 */
	private String costSubTotal = "";
	/**	 . *	 */
	private String invoicePoNumber = "";
	/**	 . *	 */
	private String assetSubTotal = "";
	/**	 . *	 */
	private String costInstallation = "";
	/**	 . *	 */
	private String invoiceVendorID = "";
	/**	 . *	 */
	private String invoiceVendorNumber = "";
	/**	 . *	 */
	private String actualInstallation = "";
	/**	 . *	 */
	private String costMaterial = "";
	/**	 . *	 */
	private String invoiceTotal = "";
	/**	 . *	 */
	private String actualMaterial = "";
	/**	 . *	 */
	private String costFreight = "";
	/**	 . *	 */
	private String actualFreight = "";
	/**	 . *	 */
	private String costTax = "";
	/**	 . *	 */
	private String dateTagged = "";
	/**	 . *	 */
	private String actualTax = "";
	/**	 . *	 */
	private String costTotal = "";
	/**	 . *	 */
	private String actualTotal = "";
	//Calculation Table Labels Ends	
	/**	 . *	 */
	private double subTotalUnitPrice;
	/**	 . *	 */
	private String subTotalTotalCost = "";
	/**	 . *	 */
	private double subTotalCEAR;
	/**	 . *	 */
	private String forwardedApproverID = "";
	/**	 . *	 */
	private String forwardedApproverToken = "";
	/**	 . *	 */
	private String forwardedApproverName = "";
	/**	 . *	 */
	private String forwardedApproverType = "";
	
	//Table Labels Begins
	private String quantity1 = "";
	private String description1 = "";
	private String vendorID1 = "";
	private String vendorName1 = "";
	private String unitPrice1 = "";
	private String totalCost1 = "";
	private String tagNumber1 = "";
	private String serialNumber1 = "";
	private String cearPrice1 = "";
	
	private String quantity2 = "";
	private String description2 = "";
	private String vendorID2 = "";
	private String vendorName2 = "";
	private String unitPrice2 = "";
	private String totalCost2 = "";
	private String tagNumber2 = "";
	private String serialNumber2 = "";
	private String cearPrice2 = "";
	
	private String quantity3 = "";
	private String description3 = "";
	private String vendorID3 = "";
	private String vendorName3 = "";
	private String unitPrice3 = "";
	private String totalCost3 = "";
	private String tagNumber3 = "";
	private String serialNumber3 = "";
	private String cearPrice3 = "";
	
	private String quantity4 = "";
	private String description4 = "";
	private String vendorID4 = "";
	private String vendorName4 = "";
	private String unitPrice4 = "";
	private String totalCost4 = "";
	private String tagNumber4 = "";
	private String serialNumber4 = "";
	private String cearPrice4 = "";
	
	private String quantity5 = "";
	private String description5 = "";
	private String vendorID5 = "";
	private String vendorName5 = "";
	private String unitPrice5 = "";
	private String totalCost5 = "";
	private String tagNumber5 = "";
	private String serialNumber5 = "";
	private String cearPrice5 = "";
	
	/**	 quantity6. *	 */
	private String quantity6 = "";
	/**	 . *	 */
	private String description6 = "";
	/**	 . *	 */
	private String vendorID6 = "";
	/**	 . *	 */
	private String vendorName6 = "";
	/**	 . *	 */
	private String unitPrice6 = "";
	/**	 . *	 */
	private String totalCost6 = "";
	/**	 . *	 */
	private String tagNumber6 = "";
	/**	 . *	 */
	private String serialNumber6 = "";
	/**	 . *	 */
	private String cearPrice6 = "";
	
	/**	 quantity7. *	 */
	private String quantity7 = "";
	/**	 . *	 */
	private String description7 = "";
	/**	 . *	 */
	private String vendorID7 = "";
	/**	 . *	 */
	private String vendorName7 = "";
	/**	 . *	 */
	private String unitPrice7 = "";
	/**	 . *	 */
	private String totalCost7 = "";
	/**	 . *	 */
	private String tagNumber7 = "";
	/**	 . *	 */
	private String serialNumber7 = "";
	/**	 . *	 */
	private String cearPrice7 = "";
	
	/**	 quantity9. *	 */
	private String quantity8 = "";
	/**	 quantity9. *	 */
	private String description8 = "";
	/**	 quantity9. *	 */
	private String vendorID8 = "";
	/**	 quantity9. *	 */
	private String vendorName8 = "";
	/**	 quantity9. *	 */
	private String unitPrice8 = "";
	/**	 quantity9. *	 */
	private String totalCost8 = "";
	/**	 quantity9. *	 */
	private String tagNumber8 = "";
	/**	 quantity9. *	 */
	private String serialNumber8 = "";
	/**	 quantity9. *	 */
	private String cearPrice8 = "";
	
	
	/**	 quantity9. *	 */
	private String quantity9 = "";
	/**	 . *	 */
	private String description9 = "";
	/**	 . *	 */
	private String vendorID9 = "";
	/**	 . *	 */
	private String vendorName9 = "";
	/**	 . *	 */
	private String unitPrice9 = "";
	/**	 . *	 */
	private String totalCost9 = "";
	/**	 . *	 */
	private String tagNumber9 = "";
	/**	 . *	 */
	private String serialNumber9 = "";
	/**	 . *	 */
	private String cearPrice9 = "";
	
	/**	 . *	 */
	private String quantity10 = "";
	/**	 . *	 */
	private String description10 = "";
	/**	 . *	 */
	private String vendorID10 = "";
	/**	 . *	 */
	private String vendorName10 = "";
	/**	 . *	 */
	private String unitPrice10 = "";
	/**	 . *	 */
	private String totalCost10 = "";
	/**	 . *	 */
	private String tagNumber10 = "";
	/**	 . *	 */
	private String serialNumber10 = "";
	/**	 . *	 */
	private String cearPrice10 = "";
	/**	 . *	 */
	private String hiddenForwardedType = "";
	//Table Labels Ends
	
	/**	 . *	 */
	private boolean financeApprovalFlag;
	/**	 . *	 */
	private boolean otherApproverFlag;
	/**	 . *	 */
	private String ppoNumber = "";
	/**	 . *	 */
	private String ppoAttachement = "";
	/**	 . *	 */
	private boolean ppoFlag;
	//added by nilesh on 20th Dec 11
	/**	 . *	 */
	private boolean f9Flag;
	/**	 . *	 */
	private boolean displayFlag;
	
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
	 * @return the dataPath
	 */
	public String getDataPath() {
		return this.dataPath;
	}
	/**
	 * @param dataPath the dataPath to set
	 */
	public void setDataPath(final String dataPath) {
		this.dataPath = dataPath;
	}
	/**
	 * @return the capitalExpID
	 */
	public String getCapitalExpID() {
		return this.capitalExpID;
	}
	/**
	 * @param capitalExpID the capitalExpID to set
	 */
	public void setCapitalExpID(final String capitalExpID) {
		this.capitalExpID = capitalExpID;
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
	 * @return the capitalHiddenID
	 */
	public String getCapitalHiddenID() {
		return this.capitalHiddenID;
	}
	/**
	 * @param capitalHiddenID the capitalHiddenID to set
	 */
	public void setCapitalHiddenID(final String capitalHiddenID) {
		this.capitalHiddenID = capitalHiddenID;
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
	 * @return the trackingNumberIterator
	 */
	public String getTrackingNumberIterator() {
		return this.trackingNumberIterator;
	}
	/**
	 * @param trackingNumberIterator the trackingNumberIterator to set
	 */
	public void setTrackingNumberIterator(final String trackingNumberIterator) {
		this.trackingNumberIterator = trackingNumberIterator;
	}
	/**
	 * @return the completedByIterator
	 */
	public String getCompletedByIterator() {
		return this.completedByIterator;
	}
	/**
	 * @param completedByIterator the completedByIterator to set
	 */
	public void setCompletedByIterator(final String completedByIterator) {
		this.completedByIterator = completedByIterator;
	}
	/**
	 * @return the completedDateIterator
	 */
	public String getCompletedDateIterator() {
		return this.completedDateIterator;
	}
	/**
	 * @param completedDateIterator the completedDateIterator to set
	 */
	public void setCompletedDateIterator(final String completedDateIterator) {
		this.completedDateIterator = completedDateIterator;
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
	public List<CapitalExpenditureApproval> getApproverCommentList() {
		return this.approverCommentList;
	}
	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(
			List<CapitalExpenditureApproval> approverCommentList) {
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
	public void setMyPage(final String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the myPagePendingCancel
	 */
	public String getMyPagePendingCancel() {
		return this.myPagePendingCancel;
	}
	/**
	 * @param myPagePendingCancel the myPagePendingCancel to set
	 */
	public void setMyPagePendingCancel(final String myPagePendingCancel) {
		this.myPagePendingCancel = myPagePendingCancel;
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
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return this.totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(final String totalRecords) {
		this.totalRecords = totalRecords;
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
	 * @return the pendingIteratorList
	 */
	public List<CapitalExpenditureApproval> getPendingIteratorList() {
		return this.pendingIteratorList;
	}
	/**
	 * @param pendingIteratorList the pendingIteratorList to set
	 */
	public void setPendingIteratorList(
			List<CapitalExpenditureApproval> pendingIteratorList) {
		this.pendingIteratorList = pendingIteratorList;
	}
	/**
	 * @return the pendingListLength
	 */
	public boolean isPendingListLength() {
		return this.pendingListLength;
	}
	/**
	 * @param pendingListLength the pendingListLength to set
	 */
	public void setPendingListLength(boolean pendingListLength) {
		this.pendingListLength = pendingListLength;
	}
	/**
	 * @return the pendingCancellationIteratorList
	 */
	public List<CapitalExpenditureApproval> getPendingCancellationIteratorList() {
		return this.pendingCancellationIteratorList;
	}
	/**
	 * @param pendingCancellationIteratorList the pendingCancellationIteratorList to set
	 */
	public void setPendingCancellationIteratorList(
			List<CapitalExpenditureApproval> pendingCancellationIteratorList) {
		this.pendingCancellationIteratorList = pendingCancellationIteratorList;
	}
	/**
	 * @return the pendingCancellationListLength
	 */
	public boolean isPendingCancellationListLength() {
		return this.pendingCancellationListLength;
	}
	/**
	 * @param pendingCancellationListLength the pendingCancellationListLength to set
	 */
	public void setPendingCancellationListLength(
			boolean pendingCancellationListLength) {
		this.pendingCancellationListLength = pendingCancellationListLength;
	}
	/**
	 * @return the approveredIteratorList
	 */
	public List<CapitalExpenditureApproval> getApproveredIteratorList() {
		return this.approveredIteratorList;
	}
	/**
	 * @param approveredIteratorList the approveredIteratorList to set
	 */
	public void setApproveredIteratorList(
			List<CapitalExpenditureApproval> approveredIteratorList) {
		this.approveredIteratorList = approveredIteratorList;
	}
	/**
	 * @return the approvedListLength
	 */
	public boolean isApprovedListLength() {
		return this.approvedListLength;
	}
	/**
	 * @param approvedListLength the approvedListLength to set
	 */
	public void setApprovedListLength(final boolean approvedListLength) {
		this.approvedListLength = approvedListLength;
	}
	/**
	 * @return the rejectedIteratorList
	 */
	public List<CapitalExpenditureApproval> getRejectedIteratorList() {
		return this.rejectedIteratorList;
	}
	/**
	 * @param rejectedIteratorList the rejectedIteratorList to set
	 */
	public void setRejectedIteratorList(
			List<CapitalExpenditureApproval> rejectedIteratorList) {
		this.rejectedIteratorList = rejectedIteratorList;
	}
	/**
	 * @return the rejectedListLength
	 */
	public boolean isRejectedListLength() {
		return this.rejectedListLength;
	}
	/**
	 * @param rejectedListLength the rejectedListLength to set
	 */
	public void setRejectedListLength(boolean rejectedListLength) {
		this.rejectedListLength = rejectedListLength;
	}
	/**
	 * @return the submittedByName
	 */
	public String getSubmittedByName() {
		return this.submittedByName;
	}
	/**
	 * @param submittedByName the submittedByName to set
	 */
	public void setSubmittedByName(final String submittedByName) {
		this.submittedByName = submittedByName;
	}
	/**
	 * @return the submittedByID
	 */
	public String getSubmittedByID() {
		return this.submittedByID;
	}
	/**
	 * @param submittedByID the submittedByID to set
	 */
	public void setSubmittedByID(final String submittedByID) {
		this.submittedByID = submittedByID;
	}
	/**
	 * @return the submittedDate
	 */
	public String getSubmittedDate() {
		return this.submittedDate;
	}
	/**
	 * @param submittedDate the submittedDate to set
	 */
	public void setSubmittedDate(final String submittedDate) {
		this.submittedDate = submittedDate;
	}
	/**
	 * @return the originalCheckbox
	 */
	public String getOriginalCheckbox() {
		return this.originalCheckbox;
	}
	/**
	 * @param originalCheckbox the originalCheckbox to set
	 */
	public void setOriginalCheckbox(final String originalCheckbox) {
		this.originalCheckbox = originalCheckbox;
	}
	/**
	 * @return the localPurchaseCheckbox
	 */
	public String getLocalPurchaseCheckbox() {
		return this.localPurchaseCheckbox;
	}
	/**
	 * @param localPurchaseCheckbox the localPurchaseCheckbox to set
	 */
	public void setLocalPurchaseCheckbox(final String localPurchaseCheckbox) {
		this.localPurchaseCheckbox = localPurchaseCheckbox;
	}
	/**
	 * @return the purchaseDeptCheckbox
	 */
	public String getPurchaseDeptCheckbox() {
		return this.purchaseDeptCheckbox;
	}
	/**
	 * @param purchaseDeptCheckbox the purchaseDeptCheckbox to set
	 */
	public void setPurchaseDeptCheckbox(final String purchaseDeptCheckbox) {
		this.purchaseDeptCheckbox = purchaseDeptCheckbox;
	}
	/**
	 * @return the computerITCheckbox
	 */
	public String getComputerITCheckbox() {
		return this.computerITCheckbox;
	}
	/**
	 * @param computerITCheckbox the computerITCheckbox to set
	 */
	public void setComputerITCheckbox(final String computerITCheckbox) {
		this.computerITCheckbox = computerITCheckbox;
	}
	/**
	 * @return the businessJustification
	 */
	public String getBusinessJustification() {
		return this.businessJustification;
	}
	/**
	 * @param businessJustification the businessJustification to set
	 */
	public void setBusinessJustification(final String businessJustification) {
		this.businessJustification = businessJustification;
	}
	/**
	 * @return the reasonForLocalPurchase
	 */
	public String getReasonForLocalPurchase() {
		return this.reasonForLocalPurchase;
	}
	/**
	 * @param reasonForLocalPurchase the reasonForLocalPurchase to set
	 */
	public void setReasonForLocalPurchase(final String reasonForLocalPurchase) {
		this.reasonForLocalPurchase = reasonForLocalPurchase;
	}
	/**
	 * @return the departmentID
	 */
	public String getDepartmentID() {
		return this.departmentID;
	}
	/**
	 * @param departmentID the departmentID to set
	 */
	public void setDepartmentID(final String departmentID) {
		this.departmentID = departmentID;
	}
	/**
	 * @return the departmentNumber
	 */
	public String getDepartmentNumber() {
		return this.departmentNumber;
	}
	/**
	 * @param departmentNumber the departmentNumber to set
	 */
	public void setDepartmentNumber(final String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}
	/**
	 * @return the dateRequired
	 */
	public String getDateRequired() {
		return this.dateRequired;
	}
	/**
	 * @param dateRequired the dateRequired to set
	 */
	public void setDateRequired(final String dateRequired) {
		this.dateRequired = dateRequired;
	}
	/**
	 * @return the locationID
	 */
	public String getLocationID() {
		return this.locationID;
	}
	/**
	 * @param locationID the locationID to set
	 */
	public void setLocationID(final String locationID) {
		this.locationID = locationID;
	}
	/**
	 * @return the ifOtherLocation
	 */
	public String getIfOtherLocation() {
		return this.ifOtherLocation;
	}
	/**
	 * @param ifOtherLocation the ifOtherLocation to set
	 */
	public void setIfOtherLocation(final String ifOtherLocation) {
		this.ifOtherLocation = ifOtherLocation;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(final String location) {
		this.location = location;
	}
	/**
	 * @return the shipToCompany
	 */
	public String getShipToCompany() {
		return this.shipToCompany;
	}
	/**
	 * @param shipToCompany the shipToCompany to set
	 */
	public void setShipToCompany(final String shipToCompany) {
		this.shipToCompany = shipToCompany;
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
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
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
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return this.streetAddress;
	}
	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(final String streetAddress) {
		this.streetAddress = streetAddress;
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
	 * @return the attention
	 */
	public String getAttention() {
		return this.attention;
	}
	/**
	 * @param attention the attention to set
	 */
	public void setAttention(final String attention) {
		this.attention = attention;
	}
	/**
	 * @return the telePhoneNumber
	 */
	public String getTelePhoneNumber() {
		return this.telePhoneNumber;
	}
	/**
	 * @param telePhoneNumber the telePhoneNumber to set
	 */
	public void setTelePhoneNumber(final String telePhoneNumber) {
		this.telePhoneNumber = telePhoneNumber;
	}
	/**
	 * @return the detailIteratorList
	 */
	public List<CapitalExpenditureApproval> getDetailIteratorList() {
		return this.detailIteratorList;
	}
	/**
	 * @param detailIteratorList the detailIteratorList to set
	 */
	public void setDetailIteratorList(
			List<CapitalExpenditureApproval> detailIteratorList) {
		this.detailIteratorList = detailIteratorList;
	}
	/**
	 * @return the detailListLengthFlag
	 */
	public boolean isDetailListLengthFlag() {
		return this.detailListLengthFlag;
	}
	/**
	 * @param detailListLengthFlag the detailListLengthFlag to set
	 */
	public void setDetailListLengthFlag(boolean detailListLengthFlag) {
		this.detailListLengthFlag = detailListLengthFlag;
	}
	/**
	 * @return the accountOnlyIteratorList
	 */
	public List<CapitalExpenditureApproval> getAccountOnlyIteratorList() {
		return this.accountOnlyIteratorList;
	}
	/**
	 * @param accountOnlyIteratorList the accountOnlyIteratorList to set
	 */
	public void setAccountOnlyIteratorList(
			List<CapitalExpenditureApproval> accountOnlyIteratorList) {
		this.accountOnlyIteratorList = accountOnlyIteratorList;
	}
	/**
	 * @return the checkDelete
	 */
	public String getCheckDelete() {
		return this.checkDelete;
	}
	/**
	 * @param checkDelete the checkDelete to set
	 */
	public void setCheckDelete(final String checkDelete) {
		this.checkDelete = checkDelete;
	}
	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return this.quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(final String quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	/**
	 * @return the vendorID
	 */
	public String getVendorID() {
		return this.vendorID;
	}
	/**
	 * @param vendorID the vendorID to set
	 */
	public void setVendorID(final String vendorID) {
		this.vendorID = vendorID;
	}
	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return this.vendorName;
	}
	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(final String vendorName) {
		this.vendorName = vendorName;
	}
	/**
	 * @return the unitPrice
	 */
	public String getUnitPrice() {
		return this.unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(final String unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the totalCost
	 */
	public String getTotalCost() {
		return this.totalCost;
	}
	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(final String totalCost) {
		this.totalCost = totalCost;
	}
	/**
	 * @return the descItemNumberItr
	 */
	public String getDescItemNumberItr() {
		return this.descItemNumberItr;
	}
	/**
	 * @param descItemNumberItr the descItemNumberItr to set
	 */
	public void setDescItemNumberItr(final String descItemNumberItr) {
		this.descItemNumberItr = descItemNumberItr;
	}
	/**
	 * @return the descHiddenIDItr
	 */
	public String getDescHiddenIDItr() {
		return this.descHiddenIDItr;
	}
	/**
	 * @param descHiddenIDItr the descHiddenIDItr to set
	 */
	public void setDescHiddenIDItr(final String descHiddenIDItr) {
		this.descHiddenIDItr = descHiddenIDItr;
	}
	/**
	 * @return the descQuantityItr
	 */
	public String getDescQuantityItr() {
		return this.descQuantityItr;
	}
	/**
	 * @param descQuantityItr the descQuantityItr to set
	 */
	public void setDescQuantityItr(final String descQuantityItr) {
		this.descQuantityItr = descQuantityItr;
	}
	/**
	 * @return the descDescriptionItr
	 */
	public String getDescDescriptionItr() {
		return this.descDescriptionItr;
	}
	/**
	 * @param descDescriptionItr the descDescriptionItr to set
	 */
	public void setDescDescriptionItr(final String descDescriptionItr) {
		this.descDescriptionItr = descDescriptionItr;
	}
	/**
	 * @return the descVendorIDItr
	 */
	public String getDescVendorIDItr() {
		return this.descVendorIDItr;
	}
	/**
	 * @param descVendorIDItr the descVendorIDItr to set
	 */
	public void setDescVendorIDItr(final String descVendorIDItr) {
		this.descVendorIDItr = descVendorIDItr;
	}
	/**
	 * @return the descVendorNameItr
	 */
	public String getDescVendorNameItr() {
		return this.descVendorNameItr;
	}
	/**
	 * @param descVendorNameItr the descVendorNameItr to set
	 */
	public void setDescVendorNameItr(final String descVendorNameItr) {
		this.descVendorNameItr = descVendorNameItr;
	}
	/**
	 * @return the descUnitPriceItr
	 */
	public String getDescUnitPriceItr() {
		return this.descUnitPriceItr;
	}
	/**
	 * @param descUnitPriceItr the descUnitPriceItr to set
	 */
	public void setDescUnitPriceItr(final String descUnitPriceItr) {
		this.descUnitPriceItr = descUnitPriceItr;
	}
	/**
	 * @return the descTotalCostItr
	 */
	public String getDescTotalCostItr() {
		return this.descTotalCostItr;
	}
	/**
	 * @param descTotalCostItr the descTotalCostItr to set
	 */
	public void setDescTotalCostItr(final String descTotalCostItr) {
		this.descTotalCostItr = descTotalCostItr;
	}
	/**
	 * @return the tagNumber
	 */
	public String getTagNumber() {
		return this.tagNumber;
	}
	/**
	 * @param tagNumber the tagNumber to set
	 */
	public void setTagNumber(final String tagNumber) {
		this.tagNumber = tagNumber;
	}
	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return this.serialNumber;
	}
	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
	 * @return the cEARPrice
	 */
	public String getCEARPrice() {
		return this.CEARPrice;
	}
	/**
	 * @param price the cEARPrice to set
	 */
	public void setCEARPrice(final String price) {
		this.CEARPrice = price;
	}
	/**
	 * @return the accountItemNumberItr
	 */
	public String getAccountItemNumberItr() {
		return this.accountItemNumberItr;
	}
	/**
	 * @param accountItemNumberItr the accountItemNumberItr to set
	 */
	public void setAccountItemNumberItr(final String accountItemNumberItr) {
		this.accountItemNumberItr = accountItemNumberItr;
	}
	/**
	 * @return the accountHiddenIDItr
	 */
	public String getAccountHiddenIDItr() {
		return this.accountHiddenIDItr;
	}
	/**
	 * @param accountHiddenIDItr the accountHiddenIDItr to set
	 */
	public void setAccountHiddenIDItr(final String accountHiddenIDItr) {
		this.accountHiddenIDItr = accountHiddenIDItr;
	}
	/**
	 * @return the accountTagNumberItr
	 */
	public String getAccountTagNumberItr() {
		return this.accountTagNumberItr;
	}
	/**
	 * @param accountTagNumberItr the accountTagNumberItr to set
	 */
	public void setAccountTagNumberItr(final String accountTagNumberItr) {
		this.accountTagNumberItr = accountTagNumberItr;
	}
	/**
	 * @return the accountSerialNumberItr
	 */
	public String getAccountSerialNumberItr() {
		return this.accountSerialNumberItr;
	}
	/**
	 * @param accountSerialNumberItr the accountSerialNumberItr to set
	 */
	public void setAccountSerialNumberItr(final String accountSerialNumberItr) {
		this.accountSerialNumberItr = accountSerialNumberItr;
	}
	/**
	 * @return the accountCEARPriceItr
	 */
	public String getAccountCEARPriceItr() {
		return this.accountCEARPriceItr;
	}
	/**
	 * @param accountCEARPriceItr the accountCEARPriceItr to set
	 */
	public void setAccountCEARPriceItr(final String accountCEARPriceItr) {
		this.accountCEARPriceItr = accountCEARPriceItr;
	}
	/**
	 * @return the checkAccountDelete
	 */
	public String getCheckAccountDelete() {
		return this.checkAccountDelete;
	}
	/**
	 * @param checkAccountDelete the checkAccountDelete to set
	 */
	public void setCheckAccountDelete(final String checkAccountDelete) {
		this.checkAccountDelete = checkAccountDelete;
	}
	/**
	 * @return the accountIteratorList
	 */
	public List<CapitalExpenditureApproval> getAccountIteratorList() {
		return this.accountIteratorList;
	}
	/**
	 * @param accountIteratorList the accountIteratorList to set
	 */
	public void setAccountIteratorList(
			final List<CapitalExpenditureApproval> accountIteratorList) {
		this.accountIteratorList = accountIteratorList;
	}
	/**
	 * @return the accountListLengthFlag
	 */
	public boolean isAccountListLengthFlag() {
		return this.accountListLengthFlag;
	}
	/**
	 * @param accountListLengthFlag the accountListLengthFlag to set
	 */
	public void setAccountListLengthFlag(final boolean accountListLengthFlag) {
		this.accountListLengthFlag = accountListLengthFlag;
	}
	/**
	 * @return the subTotalCEARPriceList
	 */
	public double getSubTotalCEARPriceList() {
		return this.subTotalCEARPriceList;
	}
	/**
	 * @param subTotalCEARPriceList the subTotalCEARPriceList to set
	 */
	public void setSubTotalCEARPriceList(final double subTotalCEARPriceList) {
		this.subTotalCEARPriceList = subTotalCEARPriceList;
	}
	/**
	 * @return the quotesAttached
	 */
	public String getQuotesAttached() {
		return this.quotesAttached;
	}
	/**
	 * @param quotesAttached the quotesAttached to set
	 */
	public void setQuotesAttached(final String quotesAttached) {
		this.quotesAttached = quotesAttached;
	}
	/**
	 * @return the quotesAttachedRadioOptionValue
	 */
	public String getQuotesAttachedRadioOptionValue() {
		return this.quotesAttachedRadioOptionValue;
	}
	/**
	 * @param quotesAttachedRadioOptionValue the quotesAttachedRadioOptionValue to set
	 */
	public void setQuotesAttachedRadioOptionValue(
			final String quotesAttachedRadioOptionValue) {
		this.quotesAttachedRadioOptionValue = quotesAttachedRadioOptionValue;
	}
	/**
	 * @return the quotesReason
	 */
	public String getQuotesReason() {
		return this.quotesReason;
	}
	/**
	 * @param quotesReason the quotesReason to set
	 */
	public void setQuotesReason(final String quotesReason) {
		this.quotesReason = quotesReason;
	}
	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return this.uploadFileName;
	}
	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(final String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * @return the uploadFileNameFlag
	 */
	public boolean isUploadFileNameFlag() {
		return this.uploadFileNameFlag;
	}
	/**
	 * @param uploadFileNameFlag the uploadFileNameFlag to set
	 */
	public void setUploadFileNameFlag(final boolean uploadFileNameFlag) {
		this.uploadFileNameFlag = uploadFileNameFlag;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}
	/**
	 * @return the costSubTotal
	 */
	public String getCostSubTotal() {
		return this.costSubTotal;
	}
	/**
	 * @param costSubTotal the costSubTotal to set
	 */
	public void setCostSubTotal(final String costSubTotal) {
		this.costSubTotal = costSubTotal;
	}
	/**
	 * @return the invoicePoNumber
	 */
	public String getInvoicePoNumber() {
		return this.invoicePoNumber;
	}
	/**
	 * @param invoicePoNumber the invoicePoNumber to set
	 */
	public void setInvoicePoNumber(final String invoicePoNumber) {
		this.invoicePoNumber = invoicePoNumber;
	}
	/**
	 * @return the assetSubTotal
	 */
	public String getAssetSubTotal() {
		return this.assetSubTotal;
	}
	/**
	 * @param assetSubTotal the assetSubTotal to set
	 */
	public void setAssetSubTotal(final String assetSubTotal) {
		this.assetSubTotal = assetSubTotal;
	}
	/**
	 * @return the costInstallation
	 */
	public String getCostInstallation() {
		return this.costInstallation;
	}
	/**
	 * @param costInstallation the costInstallation to set
	 */
	public void setCostInstallation(final String costInstallation) {
		this.costInstallation = costInstallation;
	}
	/**
	 * @return the invoiceVendorID
	 */
	public String getInvoiceVendorID() {
		return this.invoiceVendorID;
	}
	/**
	 * @param invoiceVendorID the invoiceVendorID to set
	 */
	public void setInvoiceVendorID(final String invoiceVendorID) {
		this.invoiceVendorID = invoiceVendorID;
	}
	/**
	 * @return the invoiceVendorNumber
	 */
	public String getInvoiceVendorNumber() {
		return this.invoiceVendorNumber;
	}
	/**
	 * @param invoiceVendorNumber the invoiceVendorNumber to set
	 */
	public void setInvoiceVendorNumber(final String invoiceVendorNumber) {
		this.invoiceVendorNumber = invoiceVendorNumber;
	}
	/**
	 * @return the actualInstallation
	 */
	public String getActualInstallation() {
		return this.actualInstallation;
	}
	/**
	 * @param actualInstallation the actualInstallation to set
	 */
	public void setActualInstallation(final String actualInstallation) {
		this.actualInstallation = actualInstallation;
	}
	/**
	 * @return the costMaterial
	 */
	public String getCostMaterial() {
		return this.costMaterial;
	}
	/**
	 * @param costMaterial the costMaterial to set
	 */
	public void setCostMaterial(final String costMaterial) {
		this.costMaterial = costMaterial;
	}
	/**
	 * @return the invoiceTotal
	 */
	public String getInvoiceTotal() {
		return this.invoiceTotal;
	}
	/**
	 * @param invoiceTotal the invoiceTotal to set
	 */
	public void setInvoiceTotal(final String invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}
	/**
	 * @return the actualMaterial
	 */
	public String getActualMaterial() {
		return this.actualMaterial;
	}
	/**
	 * @param actualMaterial the actualMaterial to set
	 */
	public void setActualMaterial(final String actualMaterial) {
		this.actualMaterial = actualMaterial;
	}
	/**
	 * @return the costFreight
	 */
	public String getCostFreight() {
		return this.costFreight;
	}
	/**
	 * @param costFreight the costFreight to set
	 */
	public void setCostFreight(final String costFreight) {
		this.costFreight = costFreight;
	}
	/**
	 * @return the actualFreight
	 */
	public String getActualFreight() {
		return this.actualFreight;
	}
	/**
	 * @param actualFreight the actualFreight to set
	 */
	public void setActualFreight(final String actualFreight) {
		this.actualFreight = actualFreight;
	}
	/**
	 * @return the costTax
	 */
	public String getCostTax() {
		return this.costTax;
	}
	/**
	 * @param costTax the costTax to set
	 */
	public void setCostTax(final String costTax) {
		this.costTax = costTax;
	}
	/**
	 * @return the dateTagged
	 */
	public String getDateTagged() {
		return this.dateTagged;
	}
	/**
	 * @param dateTagged the dateTagged to set
	 */
	public void setDateTagged(final String dateTagged) {
		this.dateTagged = dateTagged;
	}
	/**
	 * @return the actualTax
	 */
	public String getActualTax() {
		return this.actualTax;
	}
	/**
	 * @param actualTax the actualTax to set
	 */
	public void setActualTax(final String actualTax) {
		this.actualTax = actualTax;
	}
	/**
	 * @return the costTotal
	 */
	public String getCostTotal() {
		return this.costTotal;
	}
	/**
	 * @param costTotal the costTotal to set
	 */
	public void setCostTotal(final String costTotal) {
		this.costTotal = costTotal;
	}
	/**
	 * @return the actualTotal
	 */
	public String getActualTotal() {
		return this.actualTotal;
	}
	/**
	 * @param actualTotal the actualTotal to set
	 */
	public void setActualTotal(final String actualTotal) {
		this.actualTotal = actualTotal;
	}
	/**
	 * @return the subTotalUnitPrice
	 */
	public double getSubTotalUnitPrice() {
		return this.subTotalUnitPrice;
	}
	/**
	 * @param subTotalUnitPrice the subTotalUnitPrice to set
	 */
	public void setSubTotalUnitPrice(final double subTotalUnitPrice) {
		this.subTotalUnitPrice = subTotalUnitPrice;
	}
	/**
	 * @return the subTotalTotalCost
	 */
	public String getSubTotalTotalCost() {
		return this.subTotalTotalCost;
	}
	/**
	 * @param subTotalTotalCost the subTotalTotalCost to set
	 */
	public void setSubTotalTotalCost(final String subTotalTotalCost) {
		this.subTotalTotalCost = subTotalTotalCost;
	}
	/**
	 * @return the subTotalCEAR
	 */
	public double getSubTotalCEAR() {
		return this.subTotalCEAR;
	}
	/**
	 * @param subTotalCEAR the subTotalCEAR to set
	 */
	public void setSubTotalCEAR(final double subTotalCEAR) {
		this.subTotalCEAR = subTotalCEAR;
	}
	/**
	 * @return the forwardedApproverID
	 */
	public String getForwardedApproverID() {
		return this.forwardedApproverID;
	}
	/**
	 * @param forwardedApproverID the forwardedApproverID to set
	 */
	public void setForwardedApproverID(final String forwardedApproverID) {
		this.forwardedApproverID = forwardedApproverID;
	}
	/**
	 * @return the forwardedApproverToken
	 */
	public String getForwardedApproverToken() {
		return this.forwardedApproverToken;
	}
	/**
	 * @param forwardedApproverToken the forwardedApproverToken to set
	 */
	public void setForwardedApproverToken(final String forwardedApproverToken) {
		this.forwardedApproverToken = forwardedApproverToken;
	}
	/**
	 * @return the forwardedApproverName
	 */
	public String getForwardedApproverName() {
		return this.forwardedApproverName;
	}
	/**
	 * @param forwardedApproverName the forwardedApproverName to set
	 */
	public void setForwardedApproverName(final String forwardedApproverName) {
		this.forwardedApproverName = forwardedApproverName;
	}
	/**
	 * @return the forwardedApproverType
	 */
	public String getForwardedApproverType() {
		return this.forwardedApproverType;
	}
	/**
	 * @param forwardedApproverType the forwardedApproverType to set
	 */
	public void setForwardedApproverType(final String forwardedApproverType) {
		this.forwardedApproverType = forwardedApproverType;
	}
	/**
	 * @return the quantity1
	 */
	public String getQuantity1() {
		return this.quantity1;
	}
	/**
	 * @param quantity1 the quantity1 to set
	 */
	public void setQuantity1(final String quantity1) {
		this.quantity1 = quantity1;
	}
	/**
	 * @return the description1
	 */
	public String getDescription1() {
		return this.description1;
	}
	/**
	 * @param description1 the description1 to set
	 */
	public void setDescription1(final String description1) {
		this.description1 = description1;
	}
	/**
	 * @return the vendorID1
	 */
	public String getVendorID1() {
		return this.vendorID1;
	}
	/**
	 * @param vendorID1 the vendorID1 to set
	 */
	public void setVendorID1(final String vendorID1) {
		this.vendorID1 = vendorID1;
	}
	/**
	 * @return the vendorName1
	 */
	public String getVendorName1() {
		return this.vendorName1;
	}
	/**
	 * @param vendorName1 the vendorName1 to set
	 */
	public void setVendorName1(final String vendorName1) {
		this.vendorName1 = vendorName1;
	}
	/**
	 * @return the unitPrice1
	 */
	public String getUnitPrice1() {
		return this.unitPrice1;
	}
	/**
	 * @param unitPrice1 the unitPrice1 to set
	 */
	public void setUnitPrice1(final String unitPrice1) {
		this.unitPrice1 = unitPrice1;
	}
	/**
	 * @return the totalCost1
	 */
	public String getTotalCost1() {
		return this.totalCost1;
	}
	/**
	 * @param totalCost1 the totalCost1 to set
	 */
	public void setTotalCost1(final String totalCost1) {
		this.totalCost1 = totalCost1;
	}
	/**
	 * @return the tagNumber1
	 */
	public String getTagNumber1() {
		return this.tagNumber1;
	}
	/**
	 * @param tagNumber1 the tagNumber1 to set
	 */
	public void setTagNumber1(final String tagNumber1) {
		this.tagNumber1 = tagNumber1;
	}
	/**
	 * @return the serialNumber1
	 */
	public String getSerialNumber1() {
		return this.serialNumber1;
	}
	/**
	 * @param serialNumber1 the serialNumber1 to set
	 */
	public void setSerialNumber1(final String serialNumber1) {
		this.serialNumber1 = serialNumber1;
	}
	/**
	 * @return the cearPrice1
	 */
	public String getCearPrice1() {
		return this.cearPrice1;
	}
	/**
	 * @param cearPrice1 the cearPrice1 to set
	 */
	public void setCearPrice1(final String cearPrice1) {
		this.cearPrice1 = cearPrice1;
	}
	/**
	 * @return the quantity2
	 */
	public String getQuantity2() {
		return this.quantity2;
	}
	/**
	 * @param quantity2 the quantity2 to set
	 */
	public void setQuantity2(final String quantity2) {
		this.quantity2 = quantity2;
	}
	/**
	 * @return the description2
	 */
	public String getDescription2() {
		return this.description2;
	}
	/**
	 * @param description2 the description2 to set
	 */
	public void setDescription2(final String description2) {
		this.description2 = description2;
	}
	/**
	 * @return the vendorID2
	 */
	public String getVendorID2() {
		return this.vendorID2;
	}
	/**
	 * @param vendorID2 the vendorID2 to set
	 */
	public void setVendorID2(final String vendorID2) {
		this.vendorID2 = vendorID2;
	}
	/**
	 * @return the vendorName2
	 */
	public String getVendorName2() {
		return this.vendorName2;
	}
	/**
	 * @param vendorName2 the vendorName2 to set
	 */
	public void setVendorName2(final String vendorName2) {
		this.vendorName2 = vendorName2;
	}
	/**
	 * @return the unitPrice2
	 */
	public String getUnitPrice2() {
		return this.unitPrice2;
	}
	/**
	 * @param unitPrice2 the unitPrice2 to set
	 */
	public void setUnitPrice2(final String unitPrice2) {
		this.unitPrice2 = unitPrice2;
	}
	/**
	 * @return the totalCost2
	 */
	public String getTotalCost2() {
		return this.totalCost2;
	}
	/**
	 * @param totalCost2 the totalCost2 to set
	 */
	public void setTotalCost2(final String totalCost2) {
		this.totalCost2 = totalCost2;
	}
	/**
	 * @return the tagNumber2
	 */
	public String getTagNumber2() {
		return this.tagNumber2;
	}
	/**
	 * @param tagNumber2 the tagNumber2 to set
	 */
	public void setTagNumber2(final String tagNumber2) {
		this.tagNumber2 = tagNumber2;
	}
	/**
	 * @return the serialNumber2
	 */
	public String getSerialNumber2() {
		return this.serialNumber2;
	}
	/**
	 * @param serialNumber2 the serialNumber2 to set
	 */
	public void setSerialNumber2(final String serialNumber2) {
		this.serialNumber2 = serialNumber2;
	}
	/**
	 * @return the cearPrice2
	 */
	public String getCearPrice2() {
		return this.cearPrice2;
	}
	/**
	 * @param cearPrice2 the cearPrice2 to set
	 */
	public void setCearPrice2(final String cearPrice2) {
		this.cearPrice2 = cearPrice2;
	}
	/**
	 * @return the quantity3
	 */
	public String getQuantity3() {
		return this.quantity3;
	}
	/**
	 * @param quantity3 the quantity3 to set
	 */
	public void setQuantity3(final String quantity3) {
		this.quantity3 = quantity3;
	}
	/**
	 * @return the description3
	 */
	public String getDescription3() {
		return this.description3;
	}
	/**
	 * @param description3 the description3 to set
	 */
	public void setDescription3(final String description3) {
		this.description3 = description3;
	}
	/**
	 * @return the vendorID3
	 */
	public String getVendorID3() {
		return this.vendorID3;
	}
	/**
	 * @param vendorID3 the vendorID3 to set
	 */
	public void setVendorID3(final String vendorID3) {
		this.vendorID3 = vendorID3;
	}
	/**
	 * @return the vendorName3
	 */
	public String getVendorName3() {
		return this.vendorName3;
	}
	/**
	 * @param vendorName3 the vendorName3 to set
	 */
	public void setVendorName3(final String vendorName3) {
		this.vendorName3 = vendorName3;
	}
	/**
	 * @return the unitPrice3
	 */
	public String getUnitPrice3() {
		return this.unitPrice3;
	}
	/**
	 * @param unitPrice3 the unitPrice3 to set
	 */
	public void setUnitPrice3(final String unitPrice3) {
		this.unitPrice3 = unitPrice3;
	}
	/**
	 * @return the totalCost3
	 */
	public String getTotalCost3() {
		return this.totalCost3;
	}
	/**
	 * @param totalCost3 the totalCost3 to set
	 */
	public void setTotalCost3(final String totalCost3) {
		this.totalCost3 = totalCost3;
	}
	/**
	 * @return the tagNumber3
	 */
	public String getTagNumber3() {
		return this.tagNumber3;
	}
	/**
	 * @param tagNumber3 the tagNumber3 to set
	 */
	public void setTagNumber3(final String tagNumber3) {
		this.tagNumber3 = tagNumber3;
	}
	/**
	 * @return the serialNumber3
	 */
	public String getSerialNumber3() {
		return this.serialNumber3;
	}
	/**
	 * @param serialNumber3 the serialNumber3 to set
	 */
	public void setSerialNumber3(final String serialNumber3) {
		this.serialNumber3 = serialNumber3;
	}
	/**
	 * @return the cearPrice3
	 */
	public String getCearPrice3() {
		return this.cearPrice3;
	}
	/**
	 * @param cearPrice3 the cearPrice3 to set
	 */
	public void setCearPrice3(final String cearPrice3) {
		this.cearPrice3 = cearPrice3;
	}
	/**
	 * @return the quantity4
	 */
	public String getQuantity4() {
		return this.quantity4;
	}
	/**
	 * @param quantity4 the quantity4 to set
	 */
	public void setQuantity4(final String quantity4) {
		this.quantity4 = quantity4;
	}
	/**
	 * @return the description4
	 */
	public String getDescription4() {
		return this.description4;
	}
	/**
	 * @param description4 the description4 to set
	 */
	public void setDescription4(final String description4) {
		this.description4 = description4;
	}
	/**
	 * @return the vendorID4
	 */
	public String getVendorID4() {
		return this.vendorID4;
	}
	/**
	 * @param vendorID4 the vendorID4 to set
	 */
	public void setVendorID4(final String vendorID4) {
		this.vendorID4 = vendorID4;
	}
	/**
	 * @return the vendorName4
	 */
	public String getVendorName4() {
		return this.vendorName4;
	}
	/**
	 * @param vendorName4 the vendorName4 to set
	 */
	public void setVendorName4(final String vendorName4) {
		this.vendorName4 = vendorName4;
	}
	/**
	 * @return the unitPrice4
	 */
	public String getUnitPrice4() {
		return this.unitPrice4;
	}
	/**
	 * @param unitPrice4 the unitPrice4 to set
	 */
	public void setUnitPrice4(final String unitPrice4) {
		this.unitPrice4 = unitPrice4;
	}
	/**
	 * @return the totalCost4
	 */
	public String getTotalCost4() {
		return this.totalCost4;
	}
	/**
	 * @param totalCost4 the totalCost4 to set
	 */
	public void setTotalCost4(final String totalCost4) {
		this.totalCost4 = totalCost4;
	}
	/**
	 * @return the tagNumber4
	 */
	public String getTagNumber4() {
		return this.tagNumber4;
	}
	/**
	 * @param tagNumber4 the tagNumber4 to set
	 */
	public void setTagNumber4(final String tagNumber4) {
		this.tagNumber4 = tagNumber4;
	}
	/**
	 * @return the serialNumber4
	 */
	public String getSerialNumber4() {
		return this.serialNumber4;
	}
	/**
	 * @param serialNumber4 the serialNumber4 to set
	 */
	public void setSerialNumber4(final String serialNumber4) {
		this.serialNumber4 = serialNumber4;
	}
	/**
	 * @return the cearPrice4
	 */
	public String getCearPrice4() {
		return this.cearPrice4;
	}
	/**
	 * @param cearPrice4 the cearPrice4 to set
	 */
	public void setCearPrice4(final String cearPrice4) {
		this.cearPrice4 = cearPrice4;
	}
	/**
	 * @return the quantity5
	 */
	public String getQuantity5() {
		return this.quantity5;
	}
	/**
	 * @param quantity5 the quantity5 to set
	 */
	public void setQuantity5(final String quantity5) {
		this.quantity5 = quantity5;
	}
	/**
	 * @return the description5
	 */
	public String getDescription5() {
		return this.description5;
	}
	/**
	 * @param description5 the description5 to set
	 */
	public void setDescription5(final String description5) {
		this.description5 = description5;
	}
	/**
	 * @return the vendorID5
	 */
	public String getVendorID5() {
		return this.vendorID5;
	}
	/**
	 * @param vendorID5 the vendorID5 to set
	 */
	public void setVendorID5(final String vendorID5) {
		this.vendorID5 = vendorID5;
	}
	/**
	 * @return the vendorName5
	 */
	public String getVendorName5() {
		return this.vendorName5;
	}
	/**
	 * @param vendorName5 the vendorName5 to set
	 */
	public void setVendorName5(final String vendorName5) {
		this.vendorName5 = vendorName5;
	}
	/**
	 * @return the unitPrice5
	 */
	public String getUnitPrice5() {
		return this.unitPrice5;
	}
	/**
	 * @param unitPrice5 the unitPrice5 to set
	 */
	public void setUnitPrice5(final String unitPrice5) {
		this.unitPrice5 = unitPrice5;
	}
	/**
	 * @return the totalCost5
	 */
	public String getTotalCost5() {
		return this.totalCost5;
	}
	/**
	 * @param totalCost5 the totalCost5 to set
	 */
	public void setTotalCost5(final String totalCost5) {
		this.totalCost5 = totalCost5;
	}
	/**
	 * @return the tagNumber5
	 */
	public String getTagNumber5() {
		return this.tagNumber5;
	}
	/**
	 * @param tagNumber5 the tagNumber5 to set
	 */
	public void setTagNumber5(final String tagNumber5) {
		this.tagNumber5 = tagNumber5;
	}
	/**
	 * @return the serialNumber5
	 */
	public String getSerialNumber5() {
		return this.serialNumber5;
	}
	/**
	 * @param serialNumber5 the serialNumber5 to set
	 */
	public void setSerialNumber5(final String serialNumber5) {
		this.serialNumber5 = serialNumber5;
	}
	/**
	 * @return the cearPrice5
	 */
	public String getCearPrice5() {
		return this.cearPrice5;
	}
	/**
	 * @param cearPrice5 the cearPrice5 to set
	 */
	public void setCearPrice5(final String cearPrice5) {
		this.cearPrice5 = cearPrice5;
	}
	/**
	 * @return the quantity6
	 */
	public String getQuantity6() {
		return this.quantity6;
	}
	/**
	 * @param quantity6 the quantity6 to set
	 */
	public void setQuantity6(final String quantity6) {
		this.quantity6 = quantity6;
	}
	/**
	 * @return the description6
	 */
	public String getDescription6() {
		return this.description6;
	}
	/**
	 * @param description6 the description6 to set
	 */
	public void setDescription6(final String description6) {
		this.description6 = description6;
	}
	/**
	 * @return the vendorID6
	 */
	public String getVendorID6() {
		return this.vendorID6;
	}
	/**
	 * @param vendorID6 the vendorID6 to set
	 */
	public void setVendorID6(final String vendorID6) {
		this.vendorID6 = vendorID6;
	}
	/**
	 * @return the vendorName6
	 */
	public String getVendorName6() {
		return this.vendorName6;
	}
	/**
	 * @param vendorName6 the vendorName6 to set
	 */
	public void setVendorName6(final String vendorName6) {
		this.vendorName6 = vendorName6;
	}
	/**
	 * @return the unitPrice6
	 */
	public String getUnitPrice6() {
		return this.unitPrice6;
	}
	/**
	 * @param unitPrice6 the unitPrice6 to set
	 */
	public void setUnitPrice6(final String unitPrice6) {
		this.unitPrice6 = unitPrice6;
	}
	/**
	 * @return the totalCost6
	 */
	public String getTotalCost6() {
		return this.totalCost6;
	}
	/**
	 * @param totalCost6 the totalCost6 to set
	 */
	public void setTotalCost6(final String totalCost6) {
		this.totalCost6 = totalCost6;
	}
	/**
	 * @return the tagNumber6
	 */
	public String getTagNumber6() {
		return this.tagNumber6;
	}
	/**
	 * @param tagNumber6 the tagNumber6 to set
	 */
	public void setTagNumber6(final String tagNumber6) {
		this.tagNumber6 = tagNumber6;
	}
	/**
	 * @return the serialNumber6
	 */
	public String getSerialNumber6() {
		return this.serialNumber6;
	}
	/**
	 * @param serialNumber6 the serialNumber6 to set
	 */
	public void setSerialNumber6(final String serialNumber6) {
		this.serialNumber6 = serialNumber6;
	}
	/**
	 * @return the cearPrice6
	 */
	public String getCearPrice6() {
		return this.cearPrice6;
	}
	/**
	 * @param cearPrice6 the cearPrice6 to set
	 */
	public void setCearPrice6(final String cearPrice6) {
		this.cearPrice6 = cearPrice6;
	}
	/**
	 * @return the quantity7
	 */
	public String getQuantity7() {
		return this.quantity7;
	}
	/**
	 * @param quantity7 the quantity7 to set
	 */
	public void setQuantity7(final String quantity7) {
		this.quantity7 = quantity7;
	}
	/**
	 * @return the description7
	 */
	public String getDescription7() {
		return this.description7;
	}
	/**
	 * @param description7 the description7 to set
	 */
	public void setDescription7(final String description7) {
		this.description7 = description7;
	}
	/**
	 * @return the vendorID7
	 */
	public String getVendorID7() {
		return this.vendorID7;
	}
	/**
	 * @param vendorID7 the vendorID7 to set
	 */
	public void setVendorID7(final String vendorID7) {
		this.vendorID7 = vendorID7;
	}
	/**
	 * @return the vendorName7
	 */
	public String getVendorName7() {
		return this.vendorName7;
	}
	/**
	 * @param vendorName7 the vendorName7 to set
	 */
	public void setVendorName7(final String vendorName7) {
		this.vendorName7 = vendorName7;
	}
	/**
	 * @return the unitPrice7
	 */
	public String getUnitPrice7() {
		return this.unitPrice7;
	}
	/**
	 * @param unitPrice7 the unitPrice7 to set
	 */
	public void setUnitPrice7(final String unitPrice7) {
		this.unitPrice7 = unitPrice7;
	}
	/**
	 * @return the totalCost7
	 */
	public String getTotalCost7() {
		return this.totalCost7;
	}
	/**
	 * @param totalCost7 the totalCost7 to set
	 */
	public void setTotalCost7(final String totalCost7) {
		this.totalCost7 = totalCost7;
	}
	/**
	 * @return the tagNumber7
	 */
	public String getTagNumber7() {
		return this.tagNumber7;
	}
	/**
	 * @param tagNumber7 the tagNumber7 to set
	 */
	public void setTagNumber7(final String tagNumber7) {
		this.tagNumber7 = tagNumber7;
	}
	/**
	 * @return the serialNumber7
	 */
	public String getSerialNumber7() {
		return this.serialNumber7;
	}
	/**
	 * @param serialNumber7 the serialNumber7 to set
	 */
	public void setSerialNumber7(final String serialNumber7) {
		this.serialNumber7 = serialNumber7;
	}
	/**
	 * @return the cearPrice7
	 */
	public String getCearPrice7() {
		return this.cearPrice7;
	}
	/**
	 * @param cearPrice7 the cearPrice7 to set
	 */
	public void setCearPrice7(final String cearPrice7) {
		this.cearPrice7 = cearPrice7;
	}
	/**
	 * @return the quantity8
	 */
	public String getQuantity8() {
		return this.quantity8;
	}
	/**
	 * @param quantity8 the quantity8 to set
	 */
	public void setQuantity8(final String quantity8) {
		this.quantity8 = quantity8;
	}
	/**
	 * @return the description8
	 */
	public String getDescription8() {
		return this.description8;
	}
	/**
	 * @param description8 the description8 to set
	 */
	public void setDescription8(final String description8) {
		this.description8 = description8;
	}
	/**
	 * @return the vendorID8
	 */
	public String getVendorID8() {
		return this.vendorID8;
	}
	/**
	 * @param vendorID8 the vendorID8 to set
	 */
	public void setVendorID8(final String vendorID8) {
		this.vendorID8 = vendorID8;
	}
	/**
	 * @return the vendorName8
	 */
	public String getVendorName8() {
		return this.vendorName8;
	}
	/**
	 * @param vendorName8 the vendorName8 to set
	 */
	public void setVendorName8(final String vendorName8) {
		this.vendorName8 = vendorName8;
	}
	/**
	 * @return the unitPrice8
	 */
	public String getUnitPrice8() {
		return this.unitPrice8;
	}
	/**
	 * @param unitPrice8 the unitPrice8 to set
	 */
	public void setUnitPrice8(final String unitPrice8) {
		this.unitPrice8 = unitPrice8;
	}
	/**
	 * @return the totalCost8
	 */
	public String getTotalCost8() {
		return this.totalCost8;
	}
	/**
	 * @param totalCost8 the totalCost8 to set
	 */
	public void setTotalCost8(final String totalCost8) {
		this.totalCost8 = totalCost8;
	}
	/**
	 * @return the tagNumber8
	 */
	public String getTagNumber8() {
		return this.tagNumber8;
	}
	/**
	 * @param tagNumber8 the tagNumber8 to set
	 */
	public void setTagNumber8(final String tagNumber8) {
		this.tagNumber8 = tagNumber8;
	}
	/**
	 * @return the serialNumber8
	 */
	public String getSerialNumber8() {
		return this.serialNumber8;
	}
	/**
	 * @param serialNumber8 the serialNumber8 to set
	 */
	public void setSerialNumber8(final String serialNumber8) {
		this.serialNumber8 = serialNumber8;
	}
	/**
	 * @return the cearPrice8
	 */
	public String getCearPrice8() {
		return this.cearPrice8;
	}
	/**
	 * @param cearPrice8 the cearPrice8 to set
	 */
	public void setCearPrice8(final String cearPrice8) {
		this.cearPrice8 = cearPrice8;
	}
	/**
	 * @return the quantity9
	 */
	public String getQuantity9() {
		return this.quantity9;
	}
	/**
	 * @param quantity9 the quantity9 to set
	 */
	public void setQuantity9(final String quantity9) {
		this.quantity9 = quantity9;
	}
	/**
	 * @return the description9
	 */
	public String getDescription9() {
		return this.description9;
	}
	/**
	 * @param description9 the description9 to set
	 */
	public void setDescription9(final String description9) {
		this.description9 = description9;
	}
	/**
	 * @return the vendorID9
	 */
	public String getVendorID9() {
		return this.vendorID9;
	}
	/**
	 * @param vendorID9 the vendorID9 to set
	 */
	public void setVendorID9(final String vendorID9) {
		this.vendorID9 = vendorID9;
	}
	/**
	 * @return the vendorName9
	 */
	public String getVendorName9() {
		return this.vendorName9;
	}
	/**
	 * @param vendorName9 the vendorName9 to set
	 */
	public void setVendorName9(final String vendorName9) {
		this.vendorName9 = vendorName9;
	}
	/**
	 * @return the unitPrice9
	 */
	public String getUnitPrice9() {
		return this.unitPrice9;
	}
	/**
	 * @param unitPrice9 the unitPrice9 to set
	 */
	public void setUnitPrice9(final String unitPrice9) {
		this.unitPrice9 = unitPrice9;
	}
	/**
	 * @return the totalCost9
	 */
	public String getTotalCost9() {
		return this.totalCost9;
	}
	/**
	 * @param totalCost9 the totalCost9 to set
	 */
	public void setTotalCost9(final String totalCost9) {
		this.totalCost9 = totalCost9;
	}
	/**
	 * @return the tagNumber9
	 */
	public String getTagNumber9() {
		return this.tagNumber9;
	}
	/**
	 * @param tagNumber9 the tagNumber9 to set
	 */
	public void setTagNumber9(final String tagNumber9) {
		this.tagNumber9 = tagNumber9;
	}
	/**
	 * @return the serialNumber9
	 */
	public String getSerialNumber9() {
		return this.serialNumber9;
	}
	/**
	 * @param serialNumber9 the serialNumber9 to set
	 */
	public void setSerialNumber9(final String serialNumber9) {
		this.serialNumber9 = serialNumber9;
	}
	/**
	 * @return the cearPrice9
	 */
	public String getCearPrice9() {
		return this.cearPrice9;
	}
	/**
	 * @param cearPrice9 the cearPrice9 to set
	 */
	public void setCearPrice9(final String cearPrice9) {
		this.cearPrice9 = cearPrice9;
	}
	/**
	 * @return the quantity10
	 */
	public String getQuantity10() {
		return this.quantity10;
	}
	/**
	 * @param quantity10 the quantity10 to set
	 */
	public void setQuantity10(final String quantity10) {
		this.quantity10 = quantity10;
	}
	/**
	 * @return the description10
	 */
	public String getDescription10() {
		return this.description10;
	}
	/**
	 * @param description10 the description10 to set
	 */
	public void setDescription10(final String description10) {
		this.description10 = description10;
	}
	/**
	 * @return the vendorID10
	 */
	public String getVendorID10() {
		return this.vendorID10;
	}
	/**
	 * @param vendorID10 the vendorID10 to set
	 */
	public void setVendorID10(final String vendorID10) {
		this.vendorID10 = vendorID10;
	}
	/**
	 * @return the vendorName10
	 */
	public String getVendorName10() {
		return this.vendorName10;
	}
	/**
	 * @param vendorName10 the vendorName10 to set
	 */
	public void setVendorName10(final String vendorName10) {
		this.vendorName10 = vendorName10;
	}
	/**
	 * @return the unitPrice10
	 */
	public String getUnitPrice10() {
		return this.unitPrice10;
	}
	/**
	 * @param unitPrice10 the unitPrice10 to set
	 */
	public void setUnitPrice10(final String unitPrice10) {
		this.unitPrice10 = unitPrice10;
	}
	/**
	 * @return the totalCost10
	 */
	public String getTotalCost10() {
		return this.totalCost10;
	}
	/**
	 * @param totalCost10 the totalCost10 to set
	 */
	public void setTotalCost10(final String totalCost10) {
		this.totalCost10 = totalCost10;
	}
	/**
	 * @return the tagNumber10
	 */
	public String getTagNumber10() {
		return this.tagNumber10;
	}
	/**
	 * @param tagNumber10 the tagNumber10 to set
	 */
	public void setTagNumber10(final String tagNumber10) {
		this.tagNumber10 = tagNumber10;
	}
	/**
	 * @return the serialNumber10
	 */
	public String getSerialNumber10() {
		return this.serialNumber10;
	}
	/**
	 * @param serialNumber10 the serialNumber10 to set
	 */
	public void setSerialNumber10(final String serialNumber10) {
		this.serialNumber10 = serialNumber10;
	}
	/**
	 * @return the cearPrice10
	 */
	public String getCearPrice10() {
		return this.cearPrice10;
	}
	/**
	 * @param cearPrice10 the cearPrice10 to set
	 */
	public void setCearPrice10(final String cearPrice10) {
		this.cearPrice10 = cearPrice10;
	}
	/**
	 * @return the hiddenForwardedType
	 */
	public String getHiddenForwardedType() {
		return this.hiddenForwardedType;
	}
	/**
	 * @param hiddenForwardedType the hiddenForwardedType to set
	 */
	public void setHiddenForwardedType(final String hiddenForwardedType) {
		this.hiddenForwardedType = hiddenForwardedType;
	}
	/**
	 * @return the financeApprovalFlag
	 */
	public boolean isFinanceApprovalFlag() {
		return this.financeApprovalFlag;
	}
	/**
	 * @param financeApprovalFlag the financeApprovalFlag to set
	 */
	public void setFinanceApprovalFlag(final boolean financeApprovalFlag) {
		this.financeApprovalFlag = financeApprovalFlag;
	}
	/**
	 * @return the otherApproverFlag
	 */
	public boolean isOtherApproverFlag() {
		return this.otherApproverFlag;
	}
	/**
	 * @param otherApproverFlag the otherApproverFlag to set
	 */
	public void setOtherApproverFlag(final boolean otherApproverFlag) {
		this.otherApproverFlag = otherApproverFlag;
	}
	/**
	 * @return the ppoNumber
	 */
	public String getPpoNumber() {
		return this.ppoNumber;
	}
	/**
	 * @param ppoNumber the ppoNumber to set
	 */
	public void setPpoNumber(final String ppoNumber) {
		this.ppoNumber = ppoNumber;
	}
	/**
	 * @return the ppoAttachement
	 */
	public String getPpoAttachement() {
		return this.ppoAttachement;
	}
	/**
	 * @param ppoAttachement the ppoAttachement to set
	 */
	public void setPpoAttachement(final String ppoAttachement) {
		this.ppoAttachement = ppoAttachement;
	}
	/**
	 * @return the ppoFlag
	 */
	public boolean isPpoFlag() {
		return this.ppoFlag;
	}
	/**
	 * @param ppoFlag the ppoFlag to set
	 */
	public void setPpoFlag(final boolean ppoFlag) {
		this.ppoFlag = ppoFlag;
	}
	/**
	 * @return the f9Flag
	 */
	public boolean isF9Flag() {
		return this.f9Flag;
	}
	/**
	 * @param flag the f9Flag to set
	 */
	public void setF9Flag(final boolean flag) {
		this.f9Flag = flag;
	}
	/**
	 * @return the displayFlag
	 */
	public boolean isDisplayFlag() {
		return this.displayFlag;
	}
	/**
	 * @param displayFlag the displayFlag to set
	 */
	public void setDisplayFlag(final boolean displayFlag) {
		this.displayFlag = displayFlag;
	}
	
	
}
