package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

public class CreditCheckRequestApprover extends BeanBase {
	/** creditID. *	 */
	private String creditID = "";
	/** status. *	 */
	private String status = "";
	
	// Approver Comments List 
	/** approverComments. *	 */
	private String approverComments = "";
	/** apprName. *	 */
	private String apprName = "";
	/** apprComments. *	 */
	private String apprComments = "";
	/** apprDate. *	 */
	private String apprDate = "";
	/** apprStatus. *	 */
	private String apprStatus = "";
	/** approverCommentList. *	 */
	private List<CreditCheckRequestApprover> approverCommentList;
	/** listType. *	 */
	private String listType = "";
	/** myPage. *	 */
	private String myPage = "";
	/** creditHiddenID. *	 */
	private String creditHiddenID = "";
	/** hiddenStatus. *	 */
	private String hiddenStatus = "";
	/** myPageDrafted. *	 */
	private String myPageDrafted = "";
	/** myPagePendingCancel. *	 */
	private String myPagePendingCancel = "";
	/** myPageApproved. *	 */
	private String myPageApproved = "";
	/** myPageRejected. *	 */
	private String myPageRejected = "";
	
	/** pendingIteratorList. *	 */
	private List<CreditCheckRequestApprover> pendingIteratorList;
	/** pendingListLength. *	 */
	private boolean pendingListLength;
	/** pendingCancellationIteratorList. *	 */
	private List<CreditCheckRequestApprover> pendingCancellationIteratorList;
	/** pendingCancellationListLength. *	 */
	private boolean  pendingCancellationListLength;
	/** approveredIteratorList. *	 */
	private List<CreditCheckRequestApprover> approveredIteratorList;
	/** approvedListLength. *	 */
	private boolean approvedListLength;
	/** rejectedIteratorList. *	 */
	private List<CreditCheckRequestApprover> rejectedIteratorList;
	/** rejectedListLength. *	 */
	private boolean rejectedListLength;
	/** requestingPersonNameIterator. *	 */
	private String requestingPersonNameIterator = "";
	/** trackingNumberIterator. *	 */
	private String trackingNumberIterator = "";
	/** companyNameIterator. *	 */
	private String companyNameIterator = "";
	
	/** creditRequestingPersonID. *	 */
	private String creditRequestingPersonID = "";
	/** creditRequestingPersonToken. *	 */
	private String creditRequestingPersonToken = "";
	/** creditRequestingPersonName. *	 */
	private String creditRequestingPersonName = "";
	/** creditAuthorizingPersonID. *	 */
	private String creditAuthorizingPersonID = "";
	/** creditAuthorizingPersonToken. *	 */
	private String creditAuthorizingPersonToken = "";
	/** creditAuthorizingPersonName. *	 */
	private String creditAuthorizingPersonName = "";
	/** companyName. *	 */
	private String companyName = "";
	/** streetAddress. *	 */
	private String streetAddress = "";
	/** phoneNumber. *	 */
	private String phoneNumber = "";
	/** cityId. *	 */
	private String cityId = "";
	/** cityName. *	 */
	private String cityName = "";
	/** state. *	 */
	private String state = "";   
	/** zipCode. *	 */
	private String zipCode = "";
	/** amountOfCreditToBeAdvance. *	 */
	private String amountOfCreditToBeAdvance = "";
	/** isMonthlyAnnually. *	 */
	private String isMonthlyAnnually = "";
	/** isMonthlyAnnuallyRadioOptionValue. *	 */
	private String isMonthlyAnnuallyRadioOptionValue = "";
	/** isExistingCustomer. *	 */
	private String isExistingCustomer = "";
	/** isExistingCustomerRadioOptionValue. *	 */
	private String isExistingCustomerRadioOptionValue = "";
	/** customerName. *	 */
	private String customerName = "";
	/** typeOfEquipment. *	 */
	private String typeOfEquipment = "";
	/** comments. *	 */
	private String comments = "";
	/** numberOfSites. *	 */
	private String numberOfSites = "";
	/** newStatus. *	 */
	private String newStatus = "";
	/** completedByID. *	 */
	private String completedByID = "";
	/** completedBy. *	 */
	private String completedBy = "";
	/** completedDate. *	 */
	private String completedDate = "";
	/** trackingNumber. *	 */
	private String trackingNumber = "";
	
	/**
	 * @return the creditID
	 */
	public final String getCreditID() {
		return this.creditID;
	}
	/**
	 * @param creditID the creditID to set
	 */
	public void setCreditID(final String creditID) {
		this.creditID = creditID;
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
	public List<CreditCheckRequestApprover> getApproverCommentList() {
		return this.approverCommentList;
	}
	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(
			final List<CreditCheckRequestApprover> approverCommentList) {
		this.approverCommentList = approverCommentList;
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
	 * @return the creditHiddenID
	 */
	public String getCreditHiddenID() {
		return this.creditHiddenID;
	}
	/**
	 * @param creditHiddenID the creditHiddenID to set
	 */
	public void setCreditHiddenID(final String creditHiddenID) {
		this.creditHiddenID = creditHiddenID;
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
	 * @return the myPageDrafted
	 */
	public String getMyPageDrafted() {
		return this.myPageDrafted;
	}
	/**
	 * @param myPageDrafted the myPageDrafted to set
	 */
	public void setMyPageDrafted(final String myPageDrafted) {
		this.myPageDrafted = myPageDrafted;
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
	 * @return the pendingIteratorList
	 */
	public List<CreditCheckRequestApprover> getPendingIteratorList() {
		return this.pendingIteratorList;
	}
	/**
	 * @param pendingIteratorList the pendingIteratorList to set
	 */
	public void setPendingIteratorList(
			final List<CreditCheckRequestApprover> pendingIteratorList) {
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
	public void setPendingListLength(final boolean pendingListLength) {
		this.pendingListLength = pendingListLength;
	}
	/**
	 * @return the pendingCancellationIteratorList
	 */
	public List<CreditCheckRequestApprover> getPendingCancellationIteratorList() {
		return this.pendingCancellationIteratorList;
	}
	/**
	 * @param pendingCancellationIteratorList the pendingCancellationIteratorList to set
	 */
	public void setPendingCancellationIteratorList(
			final List<CreditCheckRequestApprover> pendingCancellationIteratorList) {
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
			final boolean pendingCancellationListLength) {
		this.pendingCancellationListLength = pendingCancellationListLength;
	}
	/**
	 * @return the approveredIteratorList
	 */
	public List<CreditCheckRequestApprover> getApproveredIteratorList() {
		return this.approveredIteratorList;
	}
	/**
	 * @param approveredIteratorList the approveredIteratorList to set
	 */
	public void setApproveredIteratorList(
			final List<CreditCheckRequestApprover> approveredIteratorList) {
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
	public List<CreditCheckRequestApprover> getRejectedIteratorList() {
		return this.rejectedIteratorList;
	}
	/**
	 * @param rejectedIteratorList the rejectedIteratorList to set
	 */
	public void setRejectedIteratorList(
			final List<CreditCheckRequestApprover> rejectedIteratorList) {
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
	public void setRejectedListLength(final boolean rejectedListLength) {
		this.rejectedListLength = rejectedListLength;
	}
	/**
	 * @return the requestingPersonNameIterator
	 */
	public String getRequestingPersonNameIterator() {
		return this.requestingPersonNameIterator;
	}
	/**
	 * @param requestingPersonNameIterator the requestingPersonNameIterator to set
	 */
	public void setRequestingPersonNameIterator(
			final String requestingPersonNameIterator) {
		this.requestingPersonNameIterator = requestingPersonNameIterator;
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
	 * @return the companyNameIterator
	 */
	public String getCompanyNameIterator() {
		return this.companyNameIterator;
	}
	/**
	 * @param companyNameIterator the companyNameIterator to set
	 */
	public void setCompanyNameIterator(final String companyNameIterator) {
		this.companyNameIterator = companyNameIterator;
	}
	/**
	 * @return the creditRequestingPersonID
	 */
	public String getCreditRequestingPersonID() {
		return this.creditRequestingPersonID;
	}
	/**
	 * @param creditRequestingPersonID the creditRequestingPersonID to set
	 */
	public void setCreditRequestingPersonID(final String creditRequestingPersonID) {
		this.creditRequestingPersonID = creditRequestingPersonID;
	}
	/**
	 * @return the creditRequestingPersonToken
	 */
	public String getCreditRequestingPersonToken() {
		return this.creditRequestingPersonToken;
	}
	/**
	 * @param creditRequestingPersonToken the creditRequestingPersonToken to set
	 */
	public void setCreditRequestingPersonToken(
			final String creditRequestingPersonToken) {
		this.creditRequestingPersonToken = creditRequestingPersonToken;
	}
	/**
	 * @return the creditRequestingPersonName
	 */
	public String getCreditRequestingPersonName() {
		return this.creditRequestingPersonName;
	}
	/**
	 * @param creditRequestingPersonName the creditRequestingPersonName to set
	 */
	public void setCreditRequestingPersonName(
			final String creditRequestingPersonName) {
		this.creditRequestingPersonName = creditRequestingPersonName;
	}
	/**
	 * @return the creditAuthorizingPersonID
	 */
	public String getCreditAuthorizingPersonID() {
		return this.creditAuthorizingPersonID;
	}
	/**
	 * @param creditAuthorizingPersonID the creditAuthorizingPersonID to set
	 */
	public void setCreditAuthorizingPersonID(final String creditAuthorizingPersonID) {
		this.creditAuthorizingPersonID = creditAuthorizingPersonID;
	}
	/**
	 * @return the creditAuthorizingPersonToken
	 */
	public String getCreditAuthorizingPersonToken() {
		return this.creditAuthorizingPersonToken;
	}
	/**
	 * @param creditAuthorizingPersonToken the creditAuthorizingPersonToken to set
	 */
	public void setCreditAuthorizingPersonToken(
			final String creditAuthorizingPersonToken) {
		this.creditAuthorizingPersonToken = creditAuthorizingPersonToken;
	}
	/**
	 * @return the creditAuthorizingPersonName
	 */
	public String getCreditAuthorizingPersonName() {
		return this.creditAuthorizingPersonName;
	}
	/**
	 * @param creditAuthorizingPersonName the creditAuthorizingPersonName to set
	 */
	public void setCreditAuthorizingPersonName(
			final String creditAuthorizingPersonName) {
		this.creditAuthorizingPersonName = creditAuthorizingPersonName;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return this.companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return this.cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(final String cityId) {
		this.cityId = cityId;
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
	 * @return the amountOfCreditToBeAdvance
	 */
	public String getAmountOfCreditToBeAdvance() {
		return this.amountOfCreditToBeAdvance;
	}
	/**
	 * @param amountOfCreditToBeAdvance the amountOfCreditToBeAdvance to set
	 */
	public void setAmountOfCreditToBeAdvance(final String amountOfCreditToBeAdvance) {
		this.amountOfCreditToBeAdvance = amountOfCreditToBeAdvance;
	}
	/**
	 * @return the isMonthlyAnnually
	 */
	public String getIsMonthlyAnnually() {
		return this.isMonthlyAnnually;
	}
	/**
	 * @param isMonthlyAnnually the isMonthlyAnnually to set
	 */
	public void setIsMonthlyAnnually(final String isMonthlyAnnually) {
		this.isMonthlyAnnually = isMonthlyAnnually;
	}
	/**
	 * @return the isMonthlyAnnuallyRadioOptionValue
	 */
	public String getIsMonthlyAnnuallyRadioOptionValue() {
		return this.isMonthlyAnnuallyRadioOptionValue;
	}
	/**
	 * @param isMonthlyAnnuallyRadioOptionValue the isMonthlyAnnuallyRadioOptionValue to set
	 */
	public void setIsMonthlyAnnuallyRadioOptionValue(
			String isMonthlyAnnuallyRadioOptionValue) {
		this.isMonthlyAnnuallyRadioOptionValue = isMonthlyAnnuallyRadioOptionValue;
	}
	/**
	 * @return the isExistingCustomer
	 */
	public String getIsExistingCustomer() {
		return this.isExistingCustomer;
	}
	/**
	 * @param isExistingCustomer the isExistingCustomer to set
	 */
	public void setIsExistingCustomer(final String isExistingCustomer) {
		this.isExistingCustomer = isExistingCustomer;
	}
	/**
	 * @return the isExistingCustomerRadioOptionValue
	 */
	public String getIsExistingCustomerRadioOptionValue() {
		return this.isExistingCustomerRadioOptionValue;
	}
	/**
	 * @param isExistingCustomerRadioOptionValue the isExistingCustomerRadioOptionValue to set
	 */
	public void setIsExistingCustomerRadioOptionValue(
			final String isExistingCustomerRadioOptionValue) {
		this.isExistingCustomerRadioOptionValue = isExistingCustomerRadioOptionValue;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return this.customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the typeOfEquipment
	 */
	public String getTypeOfEquipment() {
		return this.typeOfEquipment;
	}
	/**
	 * @param typeOfEquipment the typeOfEquipment to set
	 */
	public void setTypeOfEquipment(final String typeOfEquipment) {
		this.typeOfEquipment = typeOfEquipment;
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
	 * @return the numberOfSites
	 */
	public String getNumberOfSites() {
		return this.numberOfSites;
	}
	/**
	 * @param numberOfSites the numberOfSites to set
	 */
	public void setNumberOfSites(final String numberOfSites) {
		this.numberOfSites = numberOfSites;
	}
	/**
	 * @return the newStatus
	 */
	public String getNewStatus() {
		return this.newStatus;
	}
	/**
	 * @param newStatus the newStatus to set
	 */
	public void setNewStatus(final String newStatus) {
		this.newStatus = newStatus;
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
	 * @return the completedBy
	 */
	public String getCompletedBy() {
		return this.completedBy;
	}
	/**
	 * @param completedBy the completedBy to set
	 */
	public void setCompletedBy(final String completedBy) {
		this.completedBy = completedBy;
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
	
	 
}		
