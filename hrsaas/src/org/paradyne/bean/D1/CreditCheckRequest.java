package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1380.
 *
 */
public class CreditCheckRequest extends BeanBase {
	/**	 * NewUpdatedStatus. */
	private String NewUpdatedStatus = "";
	/**	 * creditID. */
	private String creditID = "";
	/**	 * listType. */
	private String listType = "";
	/**	 * myPage. */
	private String myPage = "";
	/**	 * myPageInProcess. */
	private String myPageInProcess = "";
	/**	 * myPageSentBack. */
	private String myPageSentBack = "";
	/**	 * myPageApproved. */
	private String myPageApproved = "";
	/**	 * myPageApprovedCancel. */
	private String myPageApprovedCancel = "";
	/**	 * myPageCancel. */
	private String myPageCancel = "";
	/**	 * myPageRejected. */
	private String myPageRejected = "";
	/**	 * myPageCancelRejected. */
	private String myPageCancelRejected = "";
	/**	 * draftVoucherIteratorList. */
	private List<CreditCheckRequest> draftVoucherIteratorList;
	/**	 * draftVoucherListLength. */
	private boolean draftVoucherListLength;
	/**	 * inProcessVoucherIteratorList. */
	private List<CreditCheckRequest> inProcessVoucherIteratorList;
	/**	 * inProcessVoucherListLength. */
	private boolean inProcessVoucherListLength;
	/**	 * sentBackVoucherIteratorList. */
	private List<CreditCheckRequest> sentBackVoucherIteratorList;
	/**	 * sentBackVoucherListLength. */
	private boolean sentBackVoucherListLength;
	/**	 * approvedVoucherIteratorList. */
	private List<CreditCheckRequest> approvedVoucherIteratorList;
	/**	 * approvedVoucherListLength. */
	private boolean approvedVoucherListLength;
	/**	 * approvedCancellationVoucherIteratorList. */
	private List<CreditCheckRequest> approvedCancellationVoucherIteratorList;
	/**	 * approvedCancellationVoucherListLength. */
	private boolean approvedCancellationVoucherListLength;
	/**	 * cancelledVoucherIteratorList. */
	private List<CreditCheckRequest> cancelledVoucherIteratorList;
	/**	 * cancelledVoucherListLength. */
	private boolean cancelledVoucherListLength =  false;
	/**	 * rejectedVoucherIteratorList. */
	private List<CreditCheckRequest> rejectedVoucherIteratorList;
	/**	 * rejectedVoucherListLength. */
	private boolean rejectedVoucherListLength =  false;
	/**	 * rejectedCancelVoucherIteratorList. */
	private List<CreditCheckRequest> rejectedCancelVoucherIteratorList;
	/**	 * rejectedCancelVoucherListLength. */
	private boolean rejectedCancelVoucherListLength =  false;
	/**	 * creditHiddenID. */
	private String creditHiddenID = "";
	/**	 * hiddenStatus. */
	private String hiddenStatus = "";
	/**	 * requestingPersonNameIterator. */
	private String requestingPersonNameIterator = "";
	/**	 * trackingNumberIterator. */
	private String trackingNumberIterator = "";
	/**	 * companyNameIterator. */
	private String companyNameIterator = "";
	
	// Approver Comments List Begins
	/**	 * apprName. */
	private String apprName = "";
	/**	 * apprComments. */
	private String apprComments = "";
	/**	 * apprDate. */
	private String apprDate = "";
	/**	 * apprStatus. */
	private String apprStatus = "";
	/**	 * approverCommentList. */
	private List<CreditCheckRequest> approverCommentList;
	/**	 * approverCommentsFlag. */
	private boolean approverCommentsFlag;
	
	// Approver Comments List Ends
	
	/**	 * creditRequestingPersonID. */
	private String creditRequestingPersonID = "";
	/**	 * creditRequestingPersonToken. */
	private String creditRequestingPersonToken = "";
	/**	 * creditRequestingPersonName. */
	private String creditRequestingPersonName = "";
	/**	 * creditAuthorizingPersonID. */
	private String creditAuthorizingPersonID = "";
	/**	 * creditAuthorizingPersonToken. */
	private String creditAuthorizingPersonToken = "";
	/**	 * creditAuthorizingPersonName. */
	private String creditAuthorizingPersonName = "";
	/**	 * companyName. */
	private String companyName = "";
	/**	 * streetAddress. */
	private String streetAddress = "";
	/**	 * phoneNumber. */
	private String phoneNumber = "";
	/**	 * cityId. */
	private String cityId = "";
	/**	 * cityName. */
	private String cityName = "";
	/**	 * state. */
	private String state = "";
	/**	 * zipCode. */
	private String zipCode = "";
	/**	 * amountOfCreditToBeAdvance. */
	private String amountOfCreditToBeAdvance = "";
	/**	 * isMonthlyAnnually. */
	private String isMonthlyAnnually = "";
	/**	 * isMonthlyAnnuallyRadioOptionValue. */
	private String isMonthlyAnnuallyRadioOptionValue = "";
	/**	 * isExistingCustomer. */
	private String isExistingCustomer = "";
	/**	 * isExistingCustomerRadioOptionValue. */
	private String isExistingCustomerRadioOptionValue = "";
	/**	 * customerName. */
	private String customerName = "";
	/**	 * typeOfEquipment. */
	private String typeOfEquipment = "";
	/**	 * comments. */
	private String comments = "";
	/**	 * numberOfSites. */
	private String numberOfSites = "";
	/**	 * status. */
	private String status = "";
	/**	 * trackingNumber. */
	private String trackingNumber = "";
	/**	 * completedByID. */
	private String completedByID = "";
	/**	 * completedBy. */
	private String completedBy = "";
	/**	 * completedDate. */
	private String completedDate = "";
	
	/**
	 * @return the newUpdatedStatus
	 */
	public final String getNewUpdatedStatus() {
		return this.NewUpdatedStatus;
	}
	/**
	 * @param newUpdatedStatus the newUpdatedStatus to set
	 */
	public void setNewUpdatedStatus(final String newUpdatedStatus) {
		this.NewUpdatedStatus = newUpdatedStatus;
	}
	/**
	 * @return the creditID
	 */
	public String getCreditID() {
		return this.creditID;
	}
	/**
	 * @param creditID the creditID to set
	 */
	public void setCreditID(final String creditID) {
		this.creditID = creditID;
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
	 * @return the myPageInProcess
	 */
	public String getMyPageInProcess() {
		return this.myPageInProcess;
	}
	/**
	 * @param myPageInProcess the myPageInProcess to set
	 */
	public void setMyPageInProcess(final String myPageInProcess) {
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
	public void setMyPageSentBack(final String myPageSentBack) {
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
	public void setMyPageApproved(final String myPageApproved) {
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
	public void setMyPageApprovedCancel(final String myPageApprovedCancel) {
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
	public void setMyPageCancel(final String myPageCancel) {
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
	public void setMyPageRejected(final String myPageRejected) {
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
	public void setMyPageCancelRejected(final String myPageCancelRejected) {
		this.myPageCancelRejected = myPageCancelRejected;
	}
	/**
	 * @return the draftVoucherIteratorList
	 */
	public List<CreditCheckRequest> getDraftVoucherIteratorList() {
		return this.draftVoucherIteratorList;
	}
	/**
	 * @param draftVoucherIteratorList the draftVoucherIteratorList to set
	 */
	public void setDraftVoucherIteratorList(
			final List<CreditCheckRequest> draftVoucherIteratorList) {
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
	public List<CreditCheckRequest> getInProcessVoucherIteratorList() {
		return this.inProcessVoucherIteratorList;
	}
	/**
	 * @param inProcessVoucherIteratorList the inProcessVoucherIteratorList to set
	 */
	public void setInProcessVoucherIteratorList(
			final List<CreditCheckRequest> inProcessVoucherIteratorList) {
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
	public void setInProcessVoucherListLength(
			final boolean inProcessVoucherListLength) {
		this.inProcessVoucherListLength = inProcessVoucherListLength;
	}
	/**
	 * @return the sentBackVoucherIteratorList
	 */
	public List<CreditCheckRequest> getSentBackVoucherIteratorList() {
		return this.sentBackVoucherIteratorList;
	}
	/**
	 * @param sentBackVoucherIteratorList the sentBackVoucherIteratorList to set
	 */
	public void setSentBackVoucherIteratorList(
			final List<CreditCheckRequest> sentBackVoucherIteratorList) {
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
	public List<CreditCheckRequest> getApprovedVoucherIteratorList() {
		return this.approvedVoucherIteratorList;
	}
	/**
	 * @param approvedVoucherIteratorList the approvedVoucherIteratorList to set
	 */
	public void setApprovedVoucherIteratorList(
			final List<CreditCheckRequest> approvedVoucherIteratorList) {
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
	public List<CreditCheckRequest> getApprovedCancellationVoucherIteratorList() {
		return this.approvedCancellationVoucherIteratorList;
	}
	/**
	 * @param approvedCancellationVoucherIteratorList the approvedCancellationVoucherIteratorList to set
	 */
	public void setApprovedCancellationVoucherIteratorList(
			final List<CreditCheckRequest> approvedCancellationVoucherIteratorList) {
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
			final boolean approvedCancellationVoucherListLength) {
		this.approvedCancellationVoucherListLength = approvedCancellationVoucherListLength;
	}
	/**
	 * @return the cancelledVoucherIteratorList
	 */
	public List<CreditCheckRequest> getCancelledVoucherIteratorList() {
		return this.cancelledVoucherIteratorList;
	}
	/**
	 * @param cancelledVoucherIteratorList the cancelledVoucherIteratorList to set
	 */
	public void setCancelledVoucherIteratorList(
			final List<CreditCheckRequest> cancelledVoucherIteratorList) {
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
	public void setCancelledVoucherListLength(
			final boolean cancelledVoucherListLength) {
		this.cancelledVoucherListLength = cancelledVoucherListLength;
	}
	/**
	 * @return the rejectedVoucherIteratorList
	 */
	public List<CreditCheckRequest> getRejectedVoucherIteratorList() {
		return this.rejectedVoucherIteratorList;
	}
	/**
	 * @param rejectedVoucherIteratorList the rejectedVoucherIteratorList to set
	 */
	public void setRejectedVoucherIteratorList(
			final List<CreditCheckRequest> rejectedVoucherIteratorList) {
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
	public List<CreditCheckRequest> getRejectedCancelVoucherIteratorList() {
		return this.rejectedCancelVoucherIteratorList;
	}
	/**
	 * @param rejectedCancelVoucherIteratorList the rejectedCancelVoucherIteratorList to set
	 */
	public void setRejectedCancelVoucherIteratorList(
			final List<CreditCheckRequest> rejectedCancelVoucherIteratorList) {
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
			final boolean rejectedCancelVoucherListLength) {
		this.rejectedCancelVoucherListLength = rejectedCancelVoucherListLength;
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
	public List<CreditCheckRequest> getApproverCommentList() {
		return this.approverCommentList;
	}
	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(
			final List<CreditCheckRequest> approverCommentList) {
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
	public void setApproverCommentsFlag(final boolean approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
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
			final String isMonthlyAnnuallyRadioOptionValue) {
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
	
		
}
