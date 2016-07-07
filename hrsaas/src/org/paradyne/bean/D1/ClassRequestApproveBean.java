package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1381
 *
 */
public class ClassRequestApproveBean extends BeanBase {

	/***Class req appln code. */
	private String classReqAppCode = "";
	/***Class Name. */
	private String className = "";
	/***Class Description. */
	private String classDescription = "";
	/***Class Division. */
	private String classDivision = "";
	/***Class Pre req. */
	private String preRequisite =  "";
	/***Class Instructor ID. */
	private String instructorId = "";
	/***Class Start Date. */
	private String startDate = "";
	/***Class End Date. */
	private String endDate = "";
	/***Duration of Class. */
	private String durationofClass = "";
	/***Class Start Time. */
	private String startTime = "";
	/***Class End Time. */
	private String endTime = "";
	/***Class Room No. */
	private String classRoom = "";
	/***Max students in a Class . */
	private String maxnumOfStudents = "";
	/***Class Location. */
	private String location = "";
	/***Class Address. */
	private String address = "";
	/***Class Contact No. */
	private String telephone = "";
	/***Class Conntact Name. */
	private String contactName = "";
	/***Hotel Information. */
	private String hotelInformation = "";
	/***Hotel Attachments files.*/
	private String hotelAttachments = "";
   
	/***Comments. */
	private String comments = "";
	/***Level. */
	private String level = "";
	/***My Page. */
	private String myPage = "";
	/***Mode Length. */
	private String modeLength = "";
	/***Total No Of Records. */
	private String totalNoOfRecords = "";
	/***Hdelete Code. */
	private String hdeleteCode = "";
	/***Class List. */
	private List<ClassRequestApproveBean> classList;
	/***Division ID hidden field.*/
	private String divId = "";
	/***Location ID hidden field. */
	private String locId = "";

	/* FilesUpload Functionality */
	/***uploadFileAdditionalInfoDocFlag. */
	private String uploadFileAdditionalInfoDocFlag = "";
	/***Class uploadFileSrNo. */
	private String uploadFileSrNo = "";
	/***Data Path. */
	private String dataPath = "";
	/***Attachment hiden field. */
	private String hotelFile = "";
	/***Class Application Date. */
	private String classAppDate = "";
	/***Application Status. */
	private String applnStatus = "";
	/***Appln Actual Status. */
	private String applnActualStatus = "";
	/***Status. */
	private String status = "";
	/***forApproval. */
	private boolean forApproval;
	/***readOnlyDetails. */
	private boolean readOnlyDetails;
	/***appFlag. */
	private boolean appFlag;
	
	/* List Starts */
	/***Class App Code. */
	private String classAppCode = "";
	/***Class ID. */
	private String classId = ""; 
	/***Pending App List. */
	private List<ClassRequestApproveBean> pendingAppList;  
	/***Pending Cancel App List. */
	private List<ClassRequestApproveBean> pendingCancelAppList;
	/***Approved App List.*/
	private List<ClassRequestApproveBean> approvedAppList;
	/***Rejected App Liste. */
	private List<ClassRequestApproveBean> rejectedAppList;
	/***Pending App Paging. */
	private boolean pagingForPendingApp;
	/***Pending Cancel App Paging. */
	private boolean pagingForPendingCancelApp;
	/***Approved App Paging. */
	private boolean pagingForApprovedApp;
	/***Rejected App Paging. */
	private boolean pagingForRejectedApp;
	/***Page For Pending App. */
	private String pageForPendingApp;
	/***Page For Pending Cancel App. */
	private String pageForPendingCancelApp;
	/***Page For Approved App. */
	private String pageForApprovedApp;
	/***Page For Rejected App. */
	private String pageForRejectedApp;


	/* Paging related hidden fields */
	
	/***My Drafted Page. */
	private String myPageDrafted = "";
	/***myPagePendingCancel. */
	private String myPagePendingCancel = "";
	/***myPageApproved. */
	private String myPageApproved = "";
	/***myPageRejected. */
	private String myPageRejected = "";
	/***pendingIteratorList. */
	private List<ClassRequestApproveBean> pendingIteratorList;
	/***Class req appln code. */
	private boolean pendingListLength;
	/***pendingCancellationIteratorList. */
	private List<ClassRequestApproveBean> pendingCancellationIteratorList;
	/***pendingCancellationListLength. */
	private boolean  pendingCancellationListLength;
	/***approveredIteratorList. */
	private List<ClassRequestApproveBean> approveredIteratorList;
	/***approvedListLength. */
	private boolean approvedListLength;
	/***rejectedIteratorList. */
        private List<ClassRequestApproveBean> rejectedIteratorList;
	/***rejectedListLength. */
	private boolean rejectedListLength;
	/***listTypeDetailPage. */
	private String listTypeDetailPage = "";
	/***approverComments. */
	private String approverComments = "";
	/*Approver */
	
	/*Approval Data section on classReqAppData jsp*/
	/***approverCommentList. */
	private List<ClassRequestApproveBean> approverCommentList;
	/***Approver Name. */
	private String apprName;
	/***Approver Comments. */
	private String apprComments;
	/***Approver Date. */
	private String apprDate;
	/***Approver Status. */
	private String apprStatus;
	/***Approver Comments. */
	private String ApproverComments ="";
	
	
	/***ListType. */
	private String listType;
	/***Approver Name. */
	private String approverName = "";
	/***Approver Code. */
	private String approverCode = "";
	/***Approver Token. */
	private String appToken = "";
	/***Employee Id. */
	private String empId = "";
	
	/*Fields need to be delete*/
	/***Class req appln code. */
	private String firstApproverCode = "";
	/***Class req appln code. */
	private String secondApproverId = "";
	/***Class req appln code. */
	private String secondApproverToken = "";
	/***Class req appln code. */
	private String secondApproverName = "";
	/***Class req appln code. */
	private String approverToken = "";
	/***Class req appln code. */
	private String selectapproverName = "";
	/***Class req appln code. */
	private boolean secondAppFlag;
	
	
	/*Tracking Number*/
	/***Tracking Number. */
	private String trackingNum = "";
	/***Completed By. */
	private String completedBy = "";
	/***Completed Date. */
	private String completedDate = "";
	/***Completed By ID. */
	private String completedByID = "";

	
	
	/**
	 * @return the className
	 */
	public String getClassName() {
		return this.className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(final String className) {
		this.className = className;
	}
	/**
	 * @return the classDescription
	 */
	public String getClassDescription() {
		return this.classDescription;
	}
	/**
	 * @param classDescription the classDescription to set
	 */
	public void setClassDescription(final String classDescription) {
		this.classDescription = classDescription;
	}
	/**
	 * @return the classDivision
	 */
	public String getClassDivision() {
		return this.classDivision;
	}
	/**
	 * @param classDivision the classDivision to set
	 */
	public void setClassDivision(final String classDivision) {
		this.classDivision = classDivision;
	}
	/**
	 * @return the preRequisite
	 */
	public String getPreRequisite() {
		return this.preRequisite;
	}
	/**
	 * @param preRequisite the preRequisite to set
	 */
	public void setPreRequisite(final String preRequisite) {
		this.preRequisite = preRequisite;
	}
	/**
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return this.instructorId;
	}
	/**
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(final String instructorId) {
		this.instructorId = instructorId;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return this.startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return this.endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the durationofClass
	 */
	public String getDurationofClass() {
		return this.durationofClass;
	}
	/**
	 * @param durationofClass the durationofClass to set
	 */
	public void setDurationofClass(final String durationofClass) {
		this.durationofClass = durationofClass;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return this.startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(final String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return this.endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(final String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the classRoom
	 */
	public String getClassRoom() {
		return this.classRoom;
	}
	/**
	 * @param classRoom the classRoom to set
	 */
	public void setClassRoom(final String classRoom) {
		this.classRoom = classRoom;
	}
	/**
	 * @return the maxnumOfStudents
	 */
	public String getMaxnumOfStudents() {
		return this.maxnumOfStudents;
	}
	/**
	 * @param maxnumOfStudents the maxnumOfStudents to set
	 */
	public void setMaxnumOfStudents(final String maxnumOfStudents) {
		this.maxnumOfStudents = maxnumOfStudents;
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
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(final String address) {
		this.address = address;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return this.telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(final String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return this.contactName;
	}
	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(final String contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return the hotelInformation
	 */
	public String getHotelInformation() {
		return this.hotelInformation;
	}
	/**
	 * @param hotelInformation the hotelInformation to set
	 */
	public void setHotelInformation(final String hotelInformation) {
		this.hotelInformation = hotelInformation;
	}
	/**
	 * @return the hotelAttachments
	 */
	public String getHotelAttachments() {
		return this.hotelAttachments;
	}
	/**
	 * @param hotelAttachments the hotelAttachments to set
	 */
	public void setHotelAttachments(final String hotelAttachments) {
		this.hotelAttachments = hotelAttachments;
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
	 * @return the modeLength
	 */
	public String getModeLength() {
		return this.modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(final String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the totalNoOfRecords
	 */
	public String getTotalNoOfRecords() {
		return this.totalNoOfRecords;
	}
	/**
	 * @param totalNoOfRecords the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(final String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return this.hdeleteCode;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(final String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	/**
	 * @return the classList
	 */
	public List<ClassRequestApproveBean> getClassList() {
		return this.classList;
	}
	/**
	 * @param classList the classList to set
	 */
	public void setClassList(final List<ClassRequestApproveBean> classList) {
		this.classList = classList;
	}
	/**
	 * @return the divId
	 */
	public String getDivId() {
		return this.divId;
	}
	/**
	 * @param divId the divId to set
	 */
	public void setDivId(final String divId) {
		this.divId = divId;
	}
	/**
	 * @return the locId
	 */
	public String getLocId() {
		return this.locId;
	}
	/**
	 * @param locId the locId to set
	 */
	public void setLocId(final String locId) {
		this.locId = locId;
	}
	/**
	 * @return the uploadFileAdditionalInfoDocFlag
	 */
	public String getUploadFileAdditionalInfoDocFlag() {
		return this.uploadFileAdditionalInfoDocFlag;
	}
	/**
	 * @param uploadFileAdditionalInfoDocFlag the uploadFileAdditionalInfoDocFlag to set
	 */
	public void setUploadFileAdditionalInfoDocFlag(final 
			String uploadFileAdditionalInfoDocFlag) {
		this.uploadFileAdditionalInfoDocFlag = uploadFileAdditionalInfoDocFlag;
	}
	/**
	 * @return the uploadFileSrNo
	 */
	public String getUploadFileSrNo() {
		return this.uploadFileSrNo;
	}
	/**
	 * @param uploadFileSrNo the uploadFileSrNo to set
	 */
	public void setUploadFileSrNo(final String uploadFileSrNo) {
		this.uploadFileSrNo = uploadFileSrNo;
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
	 * @return the classAppDate
	 */
	public String getClassAppDate() {
		return this.classAppDate;
	}
	/**
	 * @param classAppDate the classAppDate to set
	 */
	public void setClassAppDate(final String classAppDate) {
		this.classAppDate = classAppDate;
	}
	/**
	 * @return the applnStatus
	 */
	public String getApplnStatus() {
		return this.applnStatus;
	}
	/**
	 * @param applnStatus the applnStatus to set
	 */
	public void setApplnStatus(final String applnStatus) {
		this.applnStatus = applnStatus;
	}
	/**
	 * @return the applnActualStatus
	 */
	public String getApplnActualStatus() {
		return this.applnActualStatus;
	}
	/**
	 * @param applnActualStatus the applnActualStatus to set
	 */
	public void setApplnActualStatus(final String applnActualStatus) {
		this.applnActualStatus = applnActualStatus;
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
	 * @return the forApproval
	 */
	public boolean isForApproval() {
		return this.forApproval;
	}
	/**
	 * @param forApproval the forApproval to set
	 */
	public void setForApproval(final boolean forApproval) {
		this.forApproval = forApproval;
	}
	/**
	 * @return the readOnlyDetails
	 */
	public boolean isReadOnlyDetails() {
		return this.readOnlyDetails;
	}
	/**
	 * @param readOnlyDetails the readOnlyDetails to set
	 */
	public void setReadOnlyDetails(final boolean readOnlyDetails) {
		this.readOnlyDetails = readOnlyDetails;
	}
	
	
	/**
	 * @return the pendingIteratorList
	 */
	public List<ClassRequestApproveBean> getPendingIteratorList() {
		return this.pendingIteratorList;
	}
	/**
	 * @param pendingIteratorList the pendingIteratorList to set
	 */
	public void setPendingIteratorList(final List<ClassRequestApproveBean> pendingIteratorList) {
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
	public List<ClassRequestApproveBean> getPendingCancellationIteratorList() {
		return this.pendingCancellationIteratorList;
	}
	/**
	 * @param pendingCancellationIteratorList the pendingCancellationIteratorList to set
	 */
	public void setPendingCancellationIteratorList(final 
		List<ClassRequestApproveBean> pendingCancellationIteratorList) {
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
	public void setPendingCancellationListLength(final boolean pendingCancellationListLength) {
		this.pendingCancellationListLength = pendingCancellationListLength;
	}
	/**
	 * @return the approveredIteratorList
	 */
	public List<ClassRequestApproveBean> getApproveredIteratorList() {
		return this.approveredIteratorList;
	}
	/**
	 * @param approveredIteratorList the approveredIteratorList to set
	 */
	public void setApproveredIteratorList(final List<ClassRequestApproveBean> approveredIteratorList) {
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
	public List<ClassRequestApproveBean> getRejectedIteratorList() {
		return this.rejectedIteratorList;
	}
	/**
	 * @param rejectedIteratorList the rejectedIteratorList to set
	 */
	public void setRejectedIteratorList(final List<ClassRequestApproveBean> rejectedIteratorList) {
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
	 * @return the listTypeDetailPage
	 */
	public String getListTypeDetailPage() {
		return this.listTypeDetailPage;
	}
	/**
	 * @param listTypeDetailPage the listTypeDetailPage to set
	 */
	public void setListTypeDetailPage(final String listTypeDetailPage) {
		this.listTypeDetailPage = listTypeDetailPage;
	}
	/**
	 * @return the approverCommentList
	 */
	public List<ClassRequestApproveBean> getApproverCommentList() {
		return this.approverCommentList;
	}
	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(final List<ClassRequestApproveBean> approverCommentList) {
		this.approverCommentList = approverCommentList;
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
	 * @return the approverComments
	 */
	public String getApproverComments() {
		return this.ApproverComments;
	}
	/**
	 * @param approverComments the approverComments to set
	 */
	public void setApproverComments(final String approverComments) {
		this.ApproverComments = approverComments;
	}
	/**
	 * @return the approverName
	 */
	public String getApproverName() {
		return this.approverName;
	}
	/**
	 * @param approverName the approverName to set
	 */
	public void setApproverName(final String approverName) {
		this.approverName = approverName;
	}
	/**
	 * @return the approverCode
	 */
	public String getApproverCode() {
		return this.approverCode;
	}
	/**
	 * @param approverCode the approverCode to set
	 */
	public void setApproverCode(final String approverCode) {
		this.approverCode = approverCode;
	}
	/**
	 * @return the appToken
	 */
	public String getAppToken() {
		return this.appToken;
	}
	/**
	 * @param appToken the appToken to set
	 */
	public void setAppToken(final String appToken) {
		this.appToken = appToken;
	}
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return this.empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(final String empId) {
		this.empId = empId;
	}
	/**
	 * @return the firstApproverCode
	 */
	public String getFirstApproverCode() {
		return this.firstApproverCode;
	}
	/**
	 * @param firstApproverCode the firstApproverCode to set
	 */
	public void setFirstApproverCode(final String firstApproverCode) {
		this.firstApproverCode = firstApproverCode;
	}
	/**
	 * @return the secondApproverId
	 */
	public String getSecondApproverId() {
		return this.secondApproverId;
	}
	/**
	 * @param secondApproverId the secondApproverId to set
	 */
	public void setSecondApproverId(final String secondApproverId) {
		this.secondApproverId = secondApproverId;
	}
	/**
	 * @return the secondApproverToken
	 */
	public String getSecondApproverToken() {
		return this.secondApproverToken;
	}
	/**
	 * @param secondApproverToken the secondApproverToken to set
	 */
	public void setSecondApproverToken(final String secondApproverToken) {
		this.secondApproverToken = secondApproverToken;
	}
	/**
	 * @return the secondApproverName
	 */
	public String getSecondApproverName() {
		return this.secondApproverName;
	}
	/**
	 * @param secondApproverName the secondApproverName to set
	 */
	public void setSecondApproverName(final String secondApproverName) {
		this.secondApproverName = secondApproverName;
	}
	/**
	 * @return the approverToken
	 */
	public String getApproverToken() {
		return this.approverToken;
	}
	/**
	 * @param approverToken the approverToken to set
	 */
	public void setApproverToken(final String approverToken) {
		this.approverToken = approverToken;
	}
	/**
	 * @return the selectapproverName
	 */
	public String getSelectapproverName() {
		return this.selectapproverName;
	}
	/**
	 * @param selectapproverName the selectapproverName to set
	 */
	public void setSelectapproverName(final String selectapproverName) {
		this.selectapproverName = selectapproverName;
	}
	/**
	 * @return the secondAppFlag
	 */
	public boolean isSecondAppFlag() {
		return this.secondAppFlag;
	}
	/**
	 * @param secondAppFlag the secondAppFlag to set
	 */
	public void setSecondAppFlag(final boolean secondAppFlag) {
		this.secondAppFlag = secondAppFlag;
	}
	/**
	 * @return the classReqAppCode
	 */
	public String getClassReqAppCode() {
		return this.classReqAppCode;
	}
	/**
	 * @param classReqAppCode the classReqAppCode to set
	 */
	public void setClassReqAppCode(final String classReqAppCode) {
		this.classReqAppCode = classReqAppCode;
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
	 * @return the level
	 */
	public String getLevel() {
		return this.level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(final String level) {
		this.level = level;
	}
	/**
	 * @return the pendingAppList
	 */
	public List<ClassRequestApproveBean> getPendingAppList() {
		return this.pendingAppList;
	}
	/**
	 * @param pendingAppList the pendingAppList to set
	 */
	public void setPendingAppList(final List<ClassRequestApproveBean> pendingAppList) {
		this.pendingAppList = pendingAppList;
	}
	/**
	 * @return the pendingCancelAppList
	 */
	public List<ClassRequestApproveBean> getPendingCancelAppList() {
		return this.pendingCancelAppList;
	}
	/**
	 * @param pendingCancelAppList the pendingCancelAppList to set
	 */
	public void setPendingCancelAppList(final List<ClassRequestApproveBean> pendingCancelAppList) {
		this.pendingCancelAppList = pendingCancelAppList;
	}
	/**
	 * @return the approvedAppList
	 */
	public List<ClassRequestApproveBean> getApprovedAppList() {
		return this.approvedAppList;
	}
	/**
	 * @param approvedAppList the approvedAppList to set
	 */
	public void setApprovedAppList(final List<ClassRequestApproveBean> approvedAppList) {
		this.approvedAppList = approvedAppList;
	}
	/**
	 * @return the rejectedAppList
	 */
	public List<ClassRequestApproveBean> getRejectedAppList() {
		return this.rejectedAppList;
	}
	/**
	 * @param rejectedAppList the rejectedAppList to set
	 */
	public void setRejectedAppList(final List<ClassRequestApproveBean> rejectedAppList) {
		this.rejectedAppList = rejectedAppList;
	}
	/**
	 * @return the pagingForPendingApp
	 */
	public boolean isPagingForPendingApp() {
		return this.pagingForPendingApp;
	}
	/**
	 * @param pagingForPendingApp the pagingForPendingApp to set
	 */
	public void setPagingForPendingApp(final boolean pagingForPendingApp) {
		this.pagingForPendingApp = pagingForPendingApp;
	}
	/**
	 * @return the pagingForPendingCancelApp
	 */
	public boolean isPagingForPendingCancelApp() {
		return this.pagingForPendingCancelApp;
	}
	/**
	 * @param pagingForPendingCancelApp the pagingForPendingCancelApp to set
	 */
	public void setPagingForPendingCancelApp(final boolean pagingForPendingCancelApp) {
		this.pagingForPendingCancelApp = pagingForPendingCancelApp;
	}
	/**
	 * @return the pagingForApprovedApp
	 */
	public boolean isPagingForApprovedApp() {
		return this.pagingForApprovedApp;
	}
	/**
	 * @param pagingForApprovedApp the pagingForApprovedApp to set
	 */
	public void setPagingForApprovedApp(final boolean pagingForApprovedApp) {
		this.pagingForApprovedApp = pagingForApprovedApp;
	}
	/**
	 * @return the pagingForRejectedApp
	 */
	public boolean isPagingForRejectedApp() {
		return this.pagingForRejectedApp;
	}
	/**
	 * @param pagingForRejectedApp the pagingForRejectedApp to set
	 */
	public void setPagingForRejectedApp(final boolean pagingForRejectedApp) {
		this.pagingForRejectedApp = pagingForRejectedApp;
	}
	/**
	 * @return the pageForPendingApp
	 */
	public String getPageForPendingApp() {
		return this.pageForPendingApp;
	}
	/**
	 * @param pageForPendingApp the pageForPendingApp to set
	 */
	public void setPageForPendingApp(final String pageForPendingApp) {
		this.pageForPendingApp = pageForPendingApp;
	}
	/**
	 * @return the pageForPendingCancelApp
	 */
	public String getPageForPendingCancelApp() {
		return this.pageForPendingCancelApp;
	}
	/**
	 * @param pageForPendingCancelApp the pageForPendingCancelApp to set
	 */
	public void setPageForPendingCancelApp(final String pageForPendingCancelApp) {
		this.pageForPendingCancelApp = pageForPendingCancelApp;
	}
	/**
	 * @return the pageForApprovedApp
	 */
	public String getPageForApprovedApp() {
		return this.pageForApprovedApp;
	}
	/**
	 * @param pageForApprovedApp the pageForApprovedApp to set
	 */
	public void setPageForApprovedApp(final String pageForApprovedApp) {
		this.pageForApprovedApp = pageForApprovedApp;
	}
	/**
	 * @return the pageForRejectedApp
	 */
	public String getPageForRejectedApp() {
		return this.pageForRejectedApp;
	}
	/**
	 * @param pageForRejectedApp the pageForRejectedApp to set
	 */
	public void setPageForRejectedApp(final String pageForRejectedApp) {
		this.pageForRejectedApp = pageForRejectedApp;
	}
	/**
	 * @return the classId
	 */
	public String getClassId() {
		return this.classId;
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(final String classId) {
		this.classId = classId;
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
	 * @return the classAppCode
	 */
	public String getClassAppCode() {
		return this.classAppCode;
	}
	/**
	 * @param classAppCode the classAppCode to set
	 */
	public void setClassAppCode(final String classAppCode) {
		this.classAppCode = classAppCode;
	}
	/**
	 * @return the hotelFile
	 */
	public String getHotelFile() {
		return this.hotelFile;
	}
	/**
	 * @param hotelFile the hotelFile to set
	 */
	public void setHotelFile(final String hotelFile) {
		this.hotelFile = hotelFile;
	}
	/**
	 * @return the trackingNum
	 */
	public String getTrackingNum() {
		return this.trackingNum;
	}
	/**
	 * @param trackingNum the trackingNum to set
	 */
	public void setTrackingNum(final String trackingNum) {
		this.trackingNum = trackingNum;
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
	 * @return the appFlag
	 */
	public boolean isAppFlag() {
		return this.appFlag;
	}
	/**
	 * @param appFlag the appFlag to set
	 */
	public void setAppFlag(final boolean appFlag) {
		this.appFlag = appFlag;
	}
	

}
