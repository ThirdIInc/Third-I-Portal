package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1381
 *
 */
public class ClassRequestBean extends BeanBase {

	/***Class name variable.*/
	private String className = "";
	/***Class Description variable.*/
	private String classDescription = "";
	/***Class Division variable.*/
	private String classDivision = "";
	/***Pre Requisite variable.*/
	private String preRequisite = "";
	/***Instructor Name variable.*/
	private String instructorName = "";
	/***Start Date variable.*/
	private String startDate = "";
	/***End Date variable.*/
	private String endDate = "";
	/***Duration of class variable.*/
	private String durationofClass = "";
	/***Start Time variable.*/
	private String startTime = "";
	/***End Time variable.*/
	private String endTime = "";
	/***Class Room variable.*/
	private String classRoom = "";
	/***Max no of Students variable.*/
	private String maxnumOfStudents = "";
	/***Location Name  variable.*/
	private String location = "";
	/***Address field variable.*/
	private String address = "";
	/***Telephone variable.*/
	private String telephone = "";
	/***Contact Name variable.*/
	private String contactName = "";
	/***Hotel Information variable.*/
	private String hotelInformation = "";
	/***Hotel Attachments variable.*/
	private String hotelAttachments = "";
	/***Comments variable.*/
	private String comments = "";
	/***My Page variable.*/
	private String myPage = "";
	/***ModeLength variable.*/
	private String modeLength = "";
	/***Total no of records variable.*/
	private String totalNoOfRecords = "";
	/***Class Code variable.*/
	private String classCode = "";
	/***HdeleteCode hidden variable.*/
	private String hdeleteCode = "";
	/***Displaying class list.*/
	private List<ClassRequestBean> classList;
	/***Division Hidden ID.*/
	private String divId = "";
	/***Location  Hidden ID.*/
	private String locId = "";
	/***Employee  Hidden ID.*/
	private String employeeId = "";

	/* FilesUpload Functionality */
	/***Upload File Additional Info Doc Flag.*/
	private String uploadFileAdditionalInfoDocFlag = "";
	/***Upload File SrNo.*/
	private String uploadFileSrNo = "";
	/***Data Path Hidden field.*/
	private String dataPath = "";
	/***Hotel File Hidden field.*/
	private String hotelFile = "";
	/***Added File.*/
	private String addedFile = "";
	/***Class Application Date.*/
	private String classAppDate = "";
	/***Applicatioin Status.*/
	private String applnStatus = "";
	/***Application Actual Status.*/
	private String applnActualStatus = "";
	/***Status field.*/
	private String status = "";
	/***For Approval.*/
	private boolean forApproval;
	/***Read Only Details field.*/
	private boolean readOnlyDetails;

	/* List Starts */
	/***pedingList.*/
	private List<ClassRequestBean> pedingList;
	/*** returnList.*/
	private List<ClassRequestBean> returnList;
	/***draftList.*/
	private List<ClassRequestBean> draftList;
	/***approvedList.*/
	private List<ClassRequestBean> approvedList;
	/***approvedCancelList.*/
	private List<ClassRequestBean> approvedCancelList;
	/***cancelList.*/
	private List<ClassRequestBean> cancelList;
	/*** rejectedList.*/
	private List<ClassRequestBean> rejectedList;
	/***approvedRejectList.*/
	private List<ClassRequestBean> approvedRejectList;

	/* Paging related hidden fields */
	/***myPageReturn.*/
	private String myPageReturn = "";
	/***myPageApproved.*/
	private String myPageApproved = "";
	/***myPageApprovedList.*/
	private String myPageApprovedList = "";
	/***myPageApprovedCancelList.*/
	private String myPageApprovedCancelList = "";
	/***myPageCancel.*/
	private String myPageCancel = "";
	/***myPageReject.*/
	private String myPageReject = "";
	/***myPageRejected.*/
	private String myPageRejected = "";
	/***myPageCancelled.*/
	private String myPageCancelled = "";
	/***Employee  Hidden ID.*/
	private String myPageCancelRejected = "";
	/***Employee  Hidden ID.*/
	private String myPageCancelApproved = "";
	/***Employee  Hidden ID.*/
	private String myPageRejectCancel = "";

	/*Approver Fields */
	/***Employee  Hidden ID.*/
	private List<ClassRequestBean> approverCommentList;
	
	
	/***Approver Name.*/
	private String apprName = "";
	/***Approver Comments.*/
	private String apprComments = "";
	/***Approver Date.*/
	private String apprDate = "";
	/***Approver Status.*/
	private String apprStatus = "";
	/***Approver Comments.*/
	private String ApproverComments = "";
	/***Employee ID.*/
	private String empId = "";
	
	/*Flags related to list*/
	
	/***Draft List Length purpose.*/
	private boolean draftVoucherListLength;
	/***Pending List Length purpose.*/
	private boolean pedingListListLength;
	/***Employee  List Length purpose.*/
	private boolean returnListLength;
	/***Approved  List Length purposeD.*/
	private boolean approvedListLength;
	/***Approved Cancelled List Length purpose.*/
	private boolean approvedCancelListLength;
	/***Cancelled List Length purpose.*/
	private boolean cancelListLength;
	/***Rejected List Length purpose.*/
	private boolean rejectedListLength;
	/***Approved List Length purpose.*/
	private boolean approvedRejectListLength;

	/*Change Request */
	
	/***Tracking Number.*/
	private String trackingNum = "";
	/***Completed By name .*/
	private String completedBy = "";
	/***Completed By name  Hidden ID.*/ 
	private String completedByID = "";
	/***Completed by Date.*/
	private String completedDate = "";
	
	
	

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
	 * @return the approvedCancelList
	 */
	public List<ClassRequestBean> getApprovedCancelList() {
		return this.approvedCancelList;
	}

	/**
	 * @param approvedCancelList
	 *            the approvedCancelList to set
	 */
	public void setApprovedCancelList(final List<ClassRequestBean> approvedCancelList) {
		this.approvedCancelList = approvedCancelList;
	}

	/**
	 * @return the myPageApprovedList
	 */
	public String getMyPageApprovedList() {
		return this.myPageApprovedList;
	}

	/**
	 * @param myPageApprovedList
	 *            the myPageApprovedList to set
	 */
	public void setMyPageApprovedList(final String myPageApprovedList) {
		this.myPageApprovedList = myPageApprovedList;
	}

	/**
	 * @return the myPageApprovedCancelList
	 */
	public String getMyPageApprovedCancelList() {
		return this.myPageApprovedCancelList;
	}

	/**
	 * @param myPageApprovedCancelList
	 *            the myPageApprovedCancelList to set
	 */
	public void setMyPageApprovedCancelList(final String myPageApprovedCancelList) {
		this.myPageApprovedCancelList = myPageApprovedCancelList;
	}

	/**
	 * @return the myPageReturn
	 */
	public String getMyPageReturn() {
		return this.myPageReturn;
	}

	/**
	 * @param myPageReturn
	 *            the myPageReturn to set
	 */
	public void setMyPageReturn(final String myPageReturn) {
		this.myPageReturn = myPageReturn;
	}

	/**
	 * @return the myPageApproved
	 */
	public String getMyPageApproved() {
		return this.myPageApproved;
	}

	/**
	 * @param myPageApproved
	 *            the myPageApproved to set
	 */
	public void setMyPageApproved(final String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}

	/**
	 * @return the dataPath
	 */
	public String getDataPath() {
		return this.dataPath;
	}

	/**
	 * @param dataPath
	 *            the dataPath to set
	 */
	public void setDataPath(final String dataPath) {
		this.dataPath = dataPath;
	}

	/**
	 * @return the uploadFileSrNo
	 */
	public String getUploadFileSrNo() {
		return this.uploadFileSrNo;
	}

	/**
	 * @param uploadFileSrNo
	 *            the uploadFileSrNo to set
	 */
	public void setUploadFileSrNo(final String uploadFileSrNo) {
		this.uploadFileSrNo = uploadFileSrNo;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * @param className
	 *            the className to set
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
	 * @param classDescription
	 *            the classDescription to set
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
	 * @param classDivision
	 *            the classDivision to set
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
	 * @param preRequisite
	 *            the preRequisite to set
	 */
	public void setPreRequisite(final String preRequisite) {
		this.preRequisite = preRequisite;
	}

	/**
	 * @return the instructorName
	 */
	public String getInstructorName() {
		return this.instructorName;
	}

	/**
	 * @param instructorName the instructorName to set
	 */
	public void setInstructorName(final String instructorName) {
		this.instructorName = instructorName;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return this.startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
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
	 * @param endDate
	 *            the endDate to set
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
	 * @param durationofClass
	 *            the durationofClass to set
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
	 * @param startTime
	 *            the startTime to set
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
	 * @param endTime
	 *            the endTime to set
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
	 * @param classRoom
	 *            the classRoom to set
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
	 * @param maxnumOfStudents
	 *            the maxnumOfStudents to set
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
	 * @param location
	 *            the location to set
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
	 * @param address
	 *            the address to set
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
	 * @param telephone
	 *            the telephone to set
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
	 * @param contactName
	 *            the contactName to set
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
	 * @param hotelInformation
	 *            the hotelInformation to set
	 */
	public void setHotelInformation(final String hotelInformation) {
		this.hotelInformation = hotelInformation;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * @param comments
	 *            the comments to set
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
	 * @param myPage
	 *            the myPage to set
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
	 * @param modeLength
	 *            the modeLength to set
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
	 * @param totalNoOfRecords
	 *            the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(final String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}

	/**
	 * @return the classCode
	 */
	public String getClassCode() {
		return this.classCode;
	}

	/**
	 * @param classCode
	 *            the classCode to set
	 */
	public void setClassCode(final String classCode) {
		this.classCode = classCode;
	}

	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return this.hdeleteCode;
	}

	/**
	 * @param hdeleteCode
	 *            the hdeleteCode to set
	 */
	public void setHdeleteCode(final String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	/**
	 * @return the classList
	 */
	public List<ClassRequestBean> getClassList() {
		return this.classList;
	}

	/**
	 * @param classList
	 *            the classList to set
	 */
	public void setClassList(final List<ClassRequestBean> classList) {
		this.classList = classList;
	}

	/**
	 * @return the divId
	 */
	public String getDivId() {
		return this.divId;
	}

	/**
	 * @param divId
	 *            the divId to set
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
	 * @param locId
	 *            the locId to set
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
	 * @param uploadFileAdditionalInfoDocFlag
	 *            the uploadFileAdditionalInfoDocFlag to set
	 */
	public void setUploadFileAdditionalInfoDocFlag(final 
			String uploadFileAdditionalInfoDocFlag) {
		this.uploadFileAdditionalInfoDocFlag = uploadFileAdditionalInfoDocFlag;
	}

	/**
	 * @return the hotelAttachments
	 */
	public String getHotelAttachments() {
		return this.hotelAttachments;
	}

	/**
	 * @param hotelAttachments
	 *            the hotelAttachments to set
	 */
	public void setHotelAttachments(final String hotelAttachments) {
		this.hotelAttachments = hotelAttachments;
	}

	/**
	 * @return the classAppDate
	 */
	public String getClassAppDate() {
		return this.classAppDate;
	}

	/**
	 * @param classAppDate
	 *            the classAppDate to set
	 */
	public void setClassAppDate(final String classAppDate) {
		this.classAppDate = classAppDate;
	}

	/**
	 * @return the pedingList
	 */
	public List<ClassRequestBean> getPedingList() {
		return this.pedingList;
	}

	/**
	 * @param pedingList
	 *            the pedingList to set
	 */
	public void setPedingList(final List<ClassRequestBean> pedingList) {
		this.pedingList = pedingList;
	}

	/**
	 * @return the returnList
	 */
	public List<ClassRequestBean> getReturnList() {
		return this.returnList;
	}

	/**
	 * @param returnList
	 *            the returnList to set
	 */
	public void setReturnList(final List<ClassRequestBean> returnList) {
		this.returnList = returnList;
	}

	/**
	 * @return the draftList
	 */
	public List<ClassRequestBean> getDraftList() {
		return this.draftList;
	}

	/**
	 * @param draftList
	 *            the draftList to set
	 */
	public void setDraftList(final List<ClassRequestBean> draftList) {
		this.draftList = draftList;
	}

	/**
	 * @return the applnStatus
	 */
	public String getApplnStatus() {
		return this.applnStatus;
	}

	/**
	 * @param applnStatus
	 *            the applnStatus to set
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
	 * @param applnActualStatus
	 *            the applnActualStatus to set
	 */
	public void setApplnActualStatus(final String applnActualStatus) {
		this.applnActualStatus = applnActualStatus;
	}

	/**
	 * @return the cancelList
	 */
	public List<ClassRequestBean> getCancelList() {
		return this.cancelList;
	}

	/**
	 * @param cancelList
	 *            the cancelList to set
	 */
	public void setCancelList(final List<ClassRequestBean> cancelList) {
		this.cancelList = cancelList;
	}

	/**
	 * @return the myPageCancel
	 */
	public String getMyPageCancel() {
		return this.myPageCancel;
	}

	/**
	 * @param myPageCancel
	 *            the myPageCancel to set
	 */
	public void setMyPageCancel(final String myPageCancel) {
		this.myPageCancel = myPageCancel;
	}

	/**
	 * @return the myPageReject
	 */
	public String getMyPageReject() {
		return this.myPageReject;
	}

	/**
	 * @param myPageReject
	 *            the myPageReject to set
	 */
	public void setMyPageReject(final String myPageReject) {
		this.myPageReject = myPageReject;
	}

	/**
	 * @return the myPageRejected
	 */
	public String getMyPageRejected() {
		return this.myPageRejected;
	}

	/**
	 * @param myPageRejected
	 *            the myPageRejected to set
	 */
	public void setMyPageRejected(final String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}

	/**
	 * @return the myPageCancelled
	 */
	public String getMyPageCancelled() {
		return this.myPageCancelled;
	}

	/**
	 * @param myPageCancelled
	 *            the myPageCancelled to set
	 */
	public void setMyPageCancelled(final String myPageCancelled) {
		this.myPageCancelled = myPageCancelled;
	}

	/**
	 * @return the myPageCancelRejected
	 */
	public String getMyPageCancelRejected() {
		return this.myPageCancelRejected;
	}

	/**
	 * @param myPageCancelRejected
	 *            the myPageCancelRejected to set
	 */
	public void setMyPageCancelRejected(final String myPageCancelRejected) {
		this.myPageCancelRejected = myPageCancelRejected;
	}

	/**
	 * @return the myPageCancelApproved
	 */
	public String getMyPageCancelApproved() {
		return this.myPageCancelApproved;
	}

	/**
	 * @param myPageCancelApproved
	 *            the myPageCancelApproved to set
	 */
	public void setMyPageCancelApproved(final String myPageCancelApproved) {
		this.myPageCancelApproved = myPageCancelApproved;
	}

	/**
	 * @return the myPageRejectCancel
	 */
	public String getMyPageRejectCancel() {
		return this.myPageRejectCancel;
	}

	/**
	 * @param myPageRejectCancel
	 *            the myPageRejectCancel to set
	 */
	public void setMyPageRejectCancel(final String myPageRejectCancel) {
		this.myPageRejectCancel = myPageRejectCancel;
	}

	
	/**
	 * @return the approvedRejectList
	 */
	public List<ClassRequestBean> getApprovedRejectList() {
		return this.approvedRejectList;
	}

	/**
	 * @param approvedRejectList
	 *            the approvedRejectList to set
	 */
	public void setApprovedRejectList(final List<ClassRequestBean> approvedRejectList) {
		this.approvedRejectList = approvedRejectList;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @param status
	 *            the status to set
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
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(final String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the approverCommentList
	 */
	public List<ClassRequestBean> getApproverCommentList() {
		return this.approverCommentList;
	}

	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(final List<ClassRequestBean> approverCommentList) {
		this.approverCommentList = approverCommentList;
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
	 * @return the addedFile
	 */
	public String getAddedFile() {
		return this.addedFile;
	}

	/**
	 * @param addedFile the addedFile to set
	 */
	public void setAddedFile(final String addedFile) {
		this.addedFile = addedFile;
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
	public void setDraftVoucherListLength(final boolean draftVoucherListLength) {
		this.draftVoucherListLength = draftVoucherListLength;
	}

	/**
	 * @return the pedingListListLength
	 */
	public boolean isPedingListListLength() {
		return this.pedingListListLength;
	}

	/**
	 * @param pedingListListLength the pedingListListLength to set
	 */
	public void setPedingListListLength(final boolean pedingListListLength) {
		this.pedingListListLength = pedingListListLength;
	}

	/**
	 * @return the returnListLength
	 */
	public boolean isReturnListLength() {
		return this.returnListLength;
	}

	/**
	 * @param returnListLength the returnListLength to set
	 */
	public void setReturnListLength(final boolean returnListLength) {
		this.returnListLength = returnListLength;
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
	 * @return the approvedCancelListLength
	 */
	public boolean isApprovedCancelListLength() {
		return this.approvedCancelListLength;
	}

	/**
	 * @param approvedCancelListLength the approvedCancelListLength to set
	 */
	public void setApprovedCancelListLength(final boolean approvedCancelListLength) {
		this.approvedCancelListLength = approvedCancelListLength;
	}

	/**
	 * @return the cancelListLength
	 */
	public boolean isCancelListLength() {
		return this.cancelListLength;
	}

	/**
	 * @param cancelListLength the cancelListLength to set
	 */
	public void setCancelListLength(final boolean cancelListLength) {
		this.cancelListLength = cancelListLength;
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
	 * @return the approvedRejectListLength
	 */
	public boolean isApprovedRejectListLength() {
		return this.approvedRejectListLength;
	}

	/**
	 * @param approvedRejectListLength the approvedRejectListLength to set
	 */
	public void setApprovedRejectListLength(final boolean approvedRejectListLength) {
		this.approvedRejectListLength = approvedRejectListLength;
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
	 * @return the approvedList
	 */
	public List<ClassRequestBean> getApprovedList() {
		return this.approvedList;
	}

	/**
	 * @param approvedList the approvedList to set
	 */
	public void setApprovedList(final List<ClassRequestBean> approvedList) {
		this.approvedList = approvedList;
	}

	/**
	 * @return the rejectedList
	 */
	public List<ClassRequestBean> getRejectedList() {
		return rejectedList;
	}

	/**
	 * @param rejectedList the rejectedList to set
	 */
	public void setRejectedList(List<ClassRequestBean> rejectedList) {
		this.rejectedList = rejectedList;
	}

}
