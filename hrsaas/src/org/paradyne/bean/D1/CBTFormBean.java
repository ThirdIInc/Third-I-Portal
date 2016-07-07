package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1380
 *
 */
public class CBTFormBean extends BeanBase {
	/** cbtID. * */
	private String cbtID;
	
	/** cbtHiddenID. * */
	private String cbtHiddenID;
	
	/** hiddenStatus. * */
	private String hiddenStatus;
	
	/** trackingNumberItr. * */
	private String trackingNumberItr;
	
	/** empNameItr. * */
	private String empNameItr;
	
	/** applicationDateItr. * */
	private String applicationDateItr;
	
	/** listType. * */
	private String listType;
	
	/** myPage. * */
	private String myPage;
	
	/** myPageInProcess. * */
	private String myPageInProcess;
	
	/** myPageSentBack. * */
	private String myPageSentBack;
	
	/** myPageApproved. * */
	private String myPageApproved;
	
	/** myPageApprovedCancel. * */
	private String myPageApprovedCancel;
	
	/** myPageRejected. * */
	private String myPageRejected;
	
	/** myPageCancelRejected. * */
	private String myPageCancelRejected;
	
	/** draftList. * */
	private List<CBTFormBean> draftList;
	
	/** draftListLength. * */
	private boolean draftListLength;
	
	/** inProcessApplicationList. * */
	private List<CBTFormBean> inProcessApplicationList;
	
	/** inProcessListLength. * */
	private boolean inProcessListLength;
	
	/** sendBackList. * */
	private List<CBTFormBean> sendBackList;
	
	/** sendBackLength. * */
	private boolean sendBackLength;
	
	/** approvedList. * */
	private List<CBTFormBean> approvedList;
	
	/** approvedListLength. * */
	private boolean approvedListLength;
	
	/** approvedCancelList. * */
	private List<CBTFormBean> approvedCancelList;
	
	/** approvedCancelListLength. * */
	private boolean approvedCancelListLength;
	
	/** rejectList. * */
	private List<CBTFormBean> rejectList;
	
	/** rejectListLength. * */
	private boolean rejectListLength;
	
	/** rejectCancelList. * */
	private List<CBTFormBean> rejectCancelList;
	
	/** rejectCancelListLength. * */
	private boolean rejectCancelListLength;
	
	/** status. * */
	private String status;
	
	/** employeeToken. * */
	private String employeeToken;
	
	/** employeeName. * */
	private String employeeName;
	
	/** employeeID. * */
	private String employeeID;
	
	/** deptName. * */
	private String deptName;
	
	/** deptNo. * */
	private String deptNo;
	
	/** courseNo. * */
	private String courseNo;
	
	/** courseCode. * */
	private String courseCode;
	
	/** courseDesc. * */
	private String courseDesc;
	
	/** applnStatus. * */
	private String applnStatus;
	
	/** address. * */
	private String address;
	
	/** city. * */
	private String city;
	
	/** state. * */
	private String state;
	
	/** country. * */
	private String country;
	
	/** zip. * */
	private String zip;
	
	/** phNo. * */
	private String phNo;
	
	/** faxNo. * */
	private String faxNo;
	
	/** emailAddress. * */
	private String emailAddress;
	
	/** defaultManagerToken. * */
	private String defaultManagerToken;
	
	/** defaultManagerName. * */
	private String defaultManagerName;
	
	/** defaultManagerID. * */
	private String defaultManagerID;
	
	/** changeMyManagerName. * */
	private String changeMyManagerToken;
	
	/** cbtID. * */
	private String changeMyManagerName;
	
	/** changeMyManagerID. * */
	private String changeMyManagerID;
	
	/** completedByName. * */
	private String completedByName;
	
	/** completedByID. * */
	private String completedByID;
	
	/** completedOn. * */
	private String completedOn;
	
	/** locationRadioValue. * */
	private String locationRadioValue;
	
	/** providence. * */
	private String providence;
	
	/** trackingNumber. * */
	private String trackingNumber;
	
	/** locationName. * */
	private String locationName;
	
	/** approverCommentsFlag. * */
	private boolean approverCommentsFlag;

	/** approverCommentList. * */
	private List<CBTFormBean> approverCommentList;
	
	/** apprName. * */
	private String apprName;
	
	/** apprComments. * */
	private String apprComments;
	
	/** apprDate. * */
	private String apprDate;
	
	/** apprStatus. * */
	private String apprStatus;
	
	/**
	 * @return : String
	 */
	public String getLocationName() {
		return this.locationName;
	}
	
	/**
	 * @param locationName : locationName.
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
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
	public String getProvidence() {
		return this.providence;
	}
	
	/**
	 * @param providence : providence.
	 */
	public void setProvidence(final String providence) {
		this.providence = providence;
	}
	
	/**
	 * @return : String
	 */
	public String getLocationRadioValue() {
		return this.locationRadioValue;
	}
	
	/**
	 * @param locationRadioValue : locationRadioValue.
	 */
	public void setLocationRadioValue(final String locationRadioValue) {
		this.locationRadioValue = locationRadioValue;
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
	 * @return : String
	 */
	public String getEmployeeName() {
		return this.employeeName;
	}
	
	/**
	 * @param employeeName : employeeName.
	 */
	public void setEmployeeName(final String employeeName) {
		this.employeeName = employeeName;
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
	public String getDeptName() {
		return this.deptName;
	}
	
	/**
	 * @param deptName : deptName.
	 */
	public void setDeptName(final String deptName) {
		this.deptName = deptName;
	}
	
	/**
	 * @return : String
	 */
	public String getDeptNo() {
		return this.deptNo;
	}
	
	/**
	 * @param deptNo : deptNo.
	 */
	public void setDeptNo(final String deptNo) {
		this.deptNo = deptNo;
	}
	
	/**
	 * @return : String
	 */
	public String getCourseNo() {
		return this.courseNo;
	}
	
	/**
	 * @param courseNo : courseNo.
	 */
	public void setCourseNo(final String courseNo) {
		this.courseNo = courseNo;
	}
	
	/**
	 * @return : String
	 */
	public String getCourseCode() {
		return this.courseCode;
	}
	
	/**
	 * @param courseCode : courseCode.
	 */
	public void setCourseCode(final String courseCode) {
		this.courseCode = courseCode;
	}
	
	/**
	 * @return : String
	 */
	public String getCourseDesc() {
		return this.courseDesc;
	}
	
	/**
	 * @param courseDesc : courseDesc.
	 */
	public void setCourseDesc(final String courseDesc) {
		this.courseDesc = courseDesc;
	}
	
	/**
	 * @return : String
	 */
	public String getApplnStatus() {
		return this.applnStatus;
	}
	
	/**
	 * @param applnStatus : applnStatus.
	 */
	public void setApplnStatus(final String applnStatus) {
		this.applnStatus = applnStatus;
	}
	
	/**
	 * @return : String
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * @param address : address.
	 */
	public void setAddress(final String address) {
		this.address = address;
	}
	
	/**
	 * @return : String
	 */
	public String getCity() {
		return this.city;
	}
	
	/**
	 * @param city : city.
	 */
	public void setCity(final String city) {
		this.city = city;
	}
	
	/**
	 * @return : String
	 */
	public String getState() {
		return this.state;
	}
	
	/**
	 * @param state : state.
	 */
	public void setState(final String state) {
		this.state = state;
	}
	
	/**
	 * @return : String
	 */
	public String getCountry() {
		return this.country;
	}
	
	/**
	 * @param country : country.
	 */
	public void setCountry(final String country) {
		this.country = country;
	}
	
	/**
	 * @return : String
	 */
	public String getZip() {
		return this.zip;
	}
	
	/**
	 * @param zip : zip.
	 */
	public void setZip(final String zip) {
		this.zip = zip;
	}
	
	/**
	 * @return : String
	 */
	public String getPhNo() {
		return this.phNo;
	}
	
	/**
	 * @param phNo : phNo.
	 */
	public void setPhNo(final String phNo) {
		this.phNo = phNo;
	}
	
	/**
	 * @return : String
	 */
	public String getFaxNo() {
		return this.faxNo;
	}
	
	/**
	 * @param faxNo : faxNo.
	 */
	public void setFaxNo(final String faxNo) {
		this.faxNo = faxNo;
	}
	
	/**
	 * @return : String
	 */
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	/**
	 * @param emailAddress : emailAddress.
	 */
	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * @return : String
	 */
	public String getDefaultManagerToken() {
		return this.defaultManagerToken;
	}
	
	/**
	 * @param defaultManagerToken : defaultManagerToken.
	 */
	public void setDefaultManagerToken(final String defaultManagerToken) {
		this.defaultManagerToken = defaultManagerToken;
	}
	
	/**
	 * @return : String
	 */
	public String getDefaultManagerName() {
		return this.defaultManagerName;
	}
	
	/**
	 * @param defaultManagerName : defaultManagerName.
	 */
	public void setDefaultManagerName(final String defaultManagerName) {
		this.defaultManagerName = defaultManagerName;
	}
	
	/**
	 * @return : String
	 */
	public String getDefaultManagerID() {
		return this.defaultManagerID;
	}
	
	/**
	 * @param defaultManagerID : defaultManagerID.
	 */
	public void setDefaultManagerID(final String defaultManagerID) {
		this.defaultManagerID = defaultManagerID;
	}
	
	/**
	 * @return : String
	 */
	public String getChangeMyManagerToken() {
		return this.changeMyManagerToken;
	}
	
	/**
	 * @param changeMyManagerToken : changeMyManagerToken.
	 */
	public void setChangeMyManagerToken(final String changeMyManagerToken) {
		this.changeMyManagerToken = changeMyManagerToken;
	}
	
	/**
	 * @return : String
	 */
	public String getChangeMyManagerName() {
		return this.changeMyManagerName;
	}
	
	/**
	 * @param changeMyManagerName : changeMyManagerName.
	 */
	public void setChangeMyManagerName(final String changeMyManagerName) {
		this.changeMyManagerName = changeMyManagerName;
	}
	
	/**
	 * @return : String
	 */
	public String getChangeMyManagerID() {
		return this.changeMyManagerID;
	}
	
	/**
	 * @param changeMyManagerID : changeMyManagerID.
	 */
	public void setChangeMyManagerID(final String changeMyManagerID) {
		this.changeMyManagerID = changeMyManagerID;
	}
	
	/**
	 * @return : String
	 */
	public String getCbtID() {
		return this.cbtID;
	}
	
	/**
	 * @param cbtID : cbtID.
	 */
	public void setCbtID(final String cbtID) {
		this.cbtID = cbtID;
	}
	
	/**
	 * @return : String
	 */
	public String getCbtHiddenID() {
		return this.cbtHiddenID;
	}
	
	/**
	 * @param cbtHiddenID : cbtHiddenID.
	 */
	public void setCbtHiddenID(final String cbtHiddenID) {
		this.cbtHiddenID = cbtHiddenID;
	}
	
	/**
	 * @return : String
	 */
	public String getHiddenStatus() {
		return this.hiddenStatus;
	}
	
	/**
	 * @param hiddenStatus : hiddenStatus.
	 */
	public void setHiddenStatus(final String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	
	/**
	 * @return : String
	 */
	public String getTrackingNumberItr() {
		return this.trackingNumberItr;
	}
	
	/**
	 * @param trackingNumberItr : trackingNumberItr.
	 */
	public void setTrackingNumberItr(final String trackingNumberItr) {
		this.trackingNumberItr = trackingNumberItr;
	}
	
	/**
	 * @return : String
	 */
	public String getEmpNameItr() {
		return this.empNameItr;
	}
	
	/**
	 * @param empNameItr : empNameItr.
	 */
	public void setEmpNameItr(final String empNameItr) {
		this.empNameItr = empNameItr;
	}
	
	/**
	 * @return : String
	 */
	public String getApplicationDateItr() {
		return this.applicationDateItr;
	}
	
	/**
	 * @param applicationDateItr : applicationDateItr.
	 */
	public void setApplicationDateItr(final String applicationDateItr) {
		this.applicationDateItr = applicationDateItr;
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
	 * @return : String
	 */
	public String getMyPage() {
		return this.myPage;
	}
	
	/**
	 * @param myPage : myPage.
	 */
	public void setMyPage(final String myPage) {
		this.myPage = myPage;
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
	 * @return : List
	 */
	public List<CBTFormBean> getDraftList() {
		return this.draftList;
	}
	
	/**
	 * @param draftList : draftList.
	 */
	public void setDraftList(final List<CBTFormBean> draftList) {
		this.draftList = draftList;
	}
	
	/**
	 * @return : boolean
	 */
	public boolean isDraftListLength() {
		return this.draftListLength;
	}
	
	/**
	 * @param draftListLength : draftListLength.
	 */
	public void setDraftListLength(final boolean draftListLength) {
		this.draftListLength = draftListLength;
	}
	
	/**
	 * @return : List
	 */
	public List<CBTFormBean> getInProcessApplicationList() {
		return this.inProcessApplicationList;
	}
	
	/**
	 * @param inProcessApplicationList : inProcessApplicationList.
	 */
	public void setInProcessApplicationList(final List<CBTFormBean> inProcessApplicationList) {
		this.inProcessApplicationList = inProcessApplicationList;
	}
	
	/**
	 * @return : boolean
	 */
	public boolean isInProcessListLength() {
		return this.inProcessListLength;
	}
	
	/**
	 * @param inProcessListLength : inProcessListLength.
	 */
	public void setInProcessListLength(final boolean inProcessListLength) {
		this.inProcessListLength = inProcessListLength;
	}
	
	/**
	 * @return : List
	 */
	public List<CBTFormBean> getSendBackList() {
		return this.sendBackList;
	}
	
	/**
	 * @param sendBackList : sendBackList.
	 */
	public void setSendBackList(final List<CBTFormBean> sendBackList) {
		this.sendBackList = sendBackList;
	}
	
	/**
	 * @return : boolean
	 */
	public boolean isSendBackLength() {
		return this.sendBackLength;
	}
	
	/**
	 * @param sendBackLength : sendBackLength.
	 */
	public void setSendBackLength(final boolean sendBackLength) {
		this.sendBackLength = sendBackLength;
	}
	
	/**
	 * @return : List
	 */
	public List<CBTFormBean> getApprovedList() {
		return this.approvedList;
	}
	
	/**
	 * @param approvedList : approvedList.
	 */
	public void setApprovedList(final List<CBTFormBean> approvedList) {
		this.approvedList = approvedList;
	}
	
	/**
	 * @return :  boolean
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
	public List<CBTFormBean> getApprovedCancelList() {
		return this.approvedCancelList;
	}
	
	/**
	 * @param approvedCancelList : approvedCancelList.
	 */
	public void setApprovedCancelList(final List<CBTFormBean> approvedCancelList) {
		this.approvedCancelList = approvedCancelList;
	}
	
	/**
	 * @return : boolean
	 */
	public boolean isApprovedCancelListLength() {
		return this.approvedCancelListLength;
	}
	
	/**
	 * @param approvedCancelListLength : approvedCancelListLength.
	 */
	public void setApprovedCancelListLength(final boolean approvedCancelListLength) {
		this.approvedCancelListLength = approvedCancelListLength;
	}
	
	/**
	 * @return : List
	 */
	public List<CBTFormBean> getRejectList() {
		return this.rejectList;
	}
	
	/**
	 * @param rejectList : rejectList.
	 */
	public void setRejectList(final List<CBTFormBean> rejectList) {
		this.rejectList = rejectList;
	}
	
	/**
	 * @return : boolean
	 */
	public boolean isRejectListLength() {
		return this.rejectListLength;
	}
	
	/**
	 * @param rejectListLength : rejectListLength.
	 */
	public void setRejectListLength(final boolean rejectListLength) {
		this.rejectListLength = rejectListLength;
	}
	
	/**
	 * @return : List
	 */
	public List<CBTFormBean> getRejectCancelList() {
		return this.rejectCancelList;
	}
	
	/**
	 * @param rejectCancelList : rejectCancelList.
	 */
	public void setRejectCancelList(final List<CBTFormBean> rejectCancelList) {
		this.rejectCancelList = rejectCancelList;
	}
	
	/**
	 * @return : boolean
	 */
	public boolean isRejectCancelListLength() {
		return this.rejectCancelListLength;
	}
	
	/**
	 * @param rejectCancelListLength : rejectCancelListLength.
	 */
	public void setRejectCancelListLength(final boolean rejectCancelListLength) {
		this.rejectCancelListLength = rejectCancelListLength;
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
	public List<CBTFormBean> getApproverCommentList() {
		return this.approverCommentList;
	}
	
	/**
	 * @param approverCommentList : approverCommentList.
	 */
	public void setApproverCommentList(final List<CBTFormBean> approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
}
