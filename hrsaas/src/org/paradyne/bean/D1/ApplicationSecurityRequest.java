package org.paradyne.bean.D1;

import java.util.List;
import org.paradyne.lib.BeanBase;

/**
 * Bhushan Dasare Feb 28, 2011
 */

public class ApplicationSecurityRequest extends BeanBase {

	private String listType;
	private List<ApplicationSecurityRequest> draftList;
	private String pageForDraft;
	private List<ApplicationSecurityRequest> applnInProcList;
	private String pageForPending;
	private List<ApplicationSecurityRequest> sendBackList;
	private String pageForSendBack;
	private List<ApplicationSecurityRequest> approveList;
	private String pageForApprove;
	private List<ApplicationSecurityRequest> approveCancelList;
	private String pageForApproveCancel;
	private String applnSecReqId;

	private List<ApplicationSecurityRequest> pendingList;
	private List<ApplicationSecurityRequest> pendingCancelList;
	private String pageForPendingCancel;
	private List<ApplicationSecurityRequest> rejectedList;
	private String pageForRejected;
	private List<ApplicationSecurityRequest> rejectedCancelList;
	private String pageForRejectedCancel;
	private String applnStatus;
	private String approverComments;
	private List<ApplicationSecurityRequest> approverCommentsList;
	private String apprName;
	private String apprComments;
	private String apprDate;
	private String apprStatus;

	private String applicationId;
	private String requestDate;
	private String requestType;
	private String status;
	private String dataPath;
	private String mgrId;
	private String mgrToken;
	private String mgrName;
	private String empIniName;//Added by Nilesh
	
	private String mgrDivision;
	private String mgrDesignation;
	private String mgrDepartment;
	private String mgrCity;
	private String mgrState;
	private String mgrPincode;
	private String mgrWorkPhone;
	private String mgrExt;
	private String mgrFax;
	private String mgrEmail;
	private String empFileRequest;
	private String empFile;
	private String addedFile;
	private String empId;
	private String empToken;
	private String empName;
	private String empDesignation;
	private String empType;
	private String hdnEmpType;
	private String empExpDate;
	private String copyMgrDiv;
	private String copyMgrDept;
	private String copyMgrCity;
	private String copyMgrState;
	private String copyMgrPincode;
	private String copyMgrEmail;
	private String copyMgrWorkPhone;
	private String copyMgrExt;
	private String copyMgrFax;
	private String hdnApplications;
	private String hdnSections;
	private String asteaWorkgroup;
	private String asteaFieldRole;
	private String asteaFieldDefaultWarehouse;
	private String asteaFinanceRole;
	private String asteaLogisticsRole;
	private String unixHost1;
	private String unixHost2;
	private String unixHost3;
	private String unixHost4;
	private String unixHostAccess;
	private String unixGroup;
	private String ntHost1;
	private String ntHost2;
	private String ntHost3;
	private String ntHost4;
	private String ntHostAccess;
	private String ntGroup;
	private String frontDoorAccess;
	private String dataRoomAccess;
	private String pictureIdAccess;
	private String agency;
	private String comments;

	private String initiatorId;
	private String initiatorName;
	private String completedOn;
	private String trackingNo;

	//Newly Added Fields By Nilesh Dhandare on 31st May 2011
	private boolean pendingListLength = false;
	private boolean pendingCancelListLength = false;
	private boolean approvedListLength = false;
	private boolean approvedCancelListLength = false;
	private boolean rejectedListLength = false;
	private boolean rejectedCancelListLength = false;
	
	
	//Added by Nilesh D on 5th Dec 2011
	
	private String hwSwHiddenSearchId = "";
	private String trackingNumIterator = "";
	private String requesterNameIterator = "";
	private String applicationDateIterator = "";
	private String applicationStatus = "";
	
	private String  hiddenSearchId = "";
	
	private String  appSecStatus  = "";
	
	private String trackingFlag = "";
	private String requestTypeItr = "";
	/**
	 * @return the trackingFlag
	 */
	public String getTrackingFlag() {
		return trackingFlag;
	}

	/**
	 * @param trackingFlag the trackingFlag to set
	 */
	public void setTrackingFlag(String trackingFlag) {
		this.trackingFlag = trackingFlag;
	}

	/**
	 * @return the appSecStatus
	 */
	public String getAppSecStatus() {
		return appSecStatus;
	}

	/**
	 * @param appSecStatus the appSecStatus to set
	 */
	public void setAppSecStatus(String appSecStatus) {
		this.appSecStatus = appSecStatus;
	}

	/**
	 * @return the hiddenSearchId
	 */
	public String getHiddenSearchId() {
		return hiddenSearchId;
	}

	/**
	 * @param hiddenSearchId the hiddenSearchId to set
	 */
	public void setHiddenSearchId(String hiddenSearchId) {
		this.hiddenSearchId = hiddenSearchId;
	}

	/**
	 * @return the hwSwHiddenSearchId
	 */
	public String getHwSwHiddenSearchId() {
		return hwSwHiddenSearchId;
	}

	/**
	 * @param hwSwHiddenSearchId the hwSwHiddenSearchId to set
	 */
	public void setHwSwHiddenSearchId(String hwSwHiddenSearchId) {
		this.hwSwHiddenSearchId = hwSwHiddenSearchId;
	}

	/**
	 * @return the trackingNumIterator
	 */
	public String getTrackingNumIterator() {
		return trackingNumIterator;
	}

	/**
	 * @param trackingNumIterator the trackingNumIterator to set
	 */
	public void setTrackingNumIterator(String trackingNumIterator) {
		this.trackingNumIterator = trackingNumIterator;
	}

	/**
	 * @return the requesterNameIterator
	 */
	public String getRequesterNameIterator() {
		return requesterNameIterator;
	}

	/**
	 * @param requesterNameIterator the requesterNameIterator to set
	 */
	public void setRequesterNameIterator(String requesterNameIterator) {
		this.requesterNameIterator = requesterNameIterator;
	}

	/**
	 * @return the applicationDateIterator
	 */
	public String getApplicationDateIterator() {
		return applicationDateIterator;
	}

	/**
	 * @param applicationDateIterator the applicationDateIterator to set
	 */
	public void setApplicationDateIterator(String applicationDateIterator) {
		this.applicationDateIterator = applicationDateIterator;
	}

	/**
	 * @return the addedFile
	 */
	public String getAddedFile() {
		return addedFile;
	}

	/**
	 * @return the agency
	 */
	public String getAgency() {
		return agency;
	}

	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @return the applnInProcList
	 */
	public List<ApplicationSecurityRequest> getApplnInProcList() {
		return applnInProcList;
	}

	/**
	 * @return the applnSecReqId
	 */
	public String getApplnSecReqId() {
		return applnSecReqId;
	}

	/**
	 * @return the applnStatus
	 */
	public String getApplnStatus() {
		return applnStatus;
	}

	/**
	 * @return the apprComments
	 */
	public String getApprComments() {
		return apprComments;
	}

	/**
	 * @return the apprDate
	 */
	public String getApprDate() {
		return apprDate;
	}

	/**
	 * @return the apprName
	 */
	public String getApprName() {
		return apprName;
	}

	/**
	 * @return the approveCancelList
	 */
	public List<ApplicationSecurityRequest> getApproveCancelList() {
		return approveCancelList;
	}

	/**
	 * @return the approveList
	 */
	public List<ApplicationSecurityRequest> getApproveList() {
		return approveList;
	}

	/**
	 * @return the approverComments
	 */
	public String getApproverComments() {
		return approverComments;
	}

	/**
	 * @return the approverCommentsList
	 */
	public List<ApplicationSecurityRequest> getApproverCommentsList() {
		return approverCommentsList;
	}

	/**
	 * @return the apprStatus
	 */
	public String getApprStatus() {
		return apprStatus;
	}

	/**
	 * @return the asteaFieldDefaultWarehouse
	 */
	public String getAsteaFieldDefaultWarehouse() {
		return asteaFieldDefaultWarehouse;
	}

	/**
	 * @return the asteaFieldRole
	 */
	public String getAsteaFieldRole() {
		return asteaFieldRole;
	}

	/**
	 * @return the asteaFinanceRole
	 */
	public String getAsteaFinanceRole() {
		return asteaFinanceRole;
	}

	/**
	 * @return the asteaLogisticsRole
	 */
	public String getAsteaLogisticsRole() {
		return asteaLogisticsRole;
	}

	/**
	 * @return the asteaWorkgroup
	 */
	public String getAsteaWorkgroup() {
		return asteaWorkgroup;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @return the completedOn
	 */
	public String getCompletedOn() {
		return completedOn;
	}

	/**
	 * @return the copyMgrCity
	 */
	public String getCopyMgrCity() {
		return copyMgrCity;
	}

	/**
	 * @return the copyMgrDept
	 */
	public String getCopyMgrDept() {
		return copyMgrDept;
	}

	/**
	 * @return the copyMgrDiv
	 */
	public String getCopyMgrDiv() {
		return copyMgrDiv;
	}

	/**
	 * @return the copyMgrEmail
	 */
	public String getCopyMgrEmail() {
		return copyMgrEmail;
	}

	/**
	 * @return the copyMgrExt
	 */
	public String getCopyMgrExt() {
		return copyMgrExt;
	}

	/**
	 * @return the copyMgrFax
	 */
	public String getCopyMgrFax() {
		return copyMgrFax;
	}

	/**
	 * @return the copyMgrPincode
	 */
	public String getCopyMgrPincode() {
		return copyMgrPincode;
	}

	/**
	 * @return the copyMgrState
	 */
	public String getCopyMgrState() {
		return copyMgrState;
	}

	/**
	 * @return the copyMgrWorkPhone
	 */
	public String getCopyMgrWorkPhone() {
		return copyMgrWorkPhone;
	}

	/**
	 * @return the dataPath
	 */
	public String getDataPath() {
		return dataPath;
	}

	/**
	 * @return the dataRoomAccess
	 */
	public String getDataRoomAccess() {
		return dataRoomAccess;
	}

	/**
	 * @return the draftList
	 */
	public List<ApplicationSecurityRequest> getDraftList() {
		return draftList;
	}

	/**
	 * @return the empDesignation
	 */
	public String getEmpDesignation() {
		return empDesignation;
	}

	/**
	 * @return the empExpDate
	 */
	public String getEmpExpDate() {
		return empExpDate;
	}

	/**
	 * @return the empFile
	 */
	public String getEmpFile() {
		return empFile;
	}

	/**
	 * @return the empFileRequest
	 */
	public String getEmpFileRequest() {
		return empFileRequest;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @return the empType
	 */
	public String getEmpType() {
		return empType;
	}

	/**
	 * @return the frontDoorAccess
	 */
	public String getFrontDoorAccess() {
		return frontDoorAccess;
	}

	/**
	 * @return the hdnApplications
	 */
	public String getHdnApplications() {
		return hdnApplications;
	}

	/**
	 * @return the hdnEmpType
	 */
	public String getHdnEmpType() {
		return hdnEmpType;
	}

	/**
	 * @return the hdnSections
	 */
	public String getHdnSections() {
		return hdnSections;
	}

	/**
	 * @return the initiatorId
	 */
	public String getInitiatorId() {
		return initiatorId;
	}

	/**
	 * @return the initiatorName
	 */
	public String getInitiatorName() {
		return initiatorName;
	}

	/**
	 * @return the listType
	 */
	public String getListType() {
		return listType;
	}

	/**
	 * @return the mgrCity
	 */
	public String getMgrCity() {
		return mgrCity;
	}

	/**
	 * @return the mgrDepartment
	 */
	public String getMgrDepartment() {
		return mgrDepartment;
	}

	/**
	 * @return the mgrDesignation
	 */
	public String getMgrDesignation() {
		return mgrDesignation;
	}

	/**
	 * @return the mgrDivision
	 */
	public String getMgrDivision() {
		return mgrDivision;
	}

	/**
	 * @return the mgrEmail
	 */
	public String getMgrEmail() {
		return mgrEmail;
	}

	/**
	 * @return the mgrExt
	 */
	public String getMgrExt() {
		return mgrExt;
	}

	/**
	 * @return the mgrFax
	 */
	public String getMgrFax() {
		return mgrFax;
	}

	/**
	 * @return the mgrId
	 */
	public String getMgrId() {
		return mgrId;
	}

	/**
	 * @return the mgrName
	 */
	public String getMgrName() {
		return mgrName;
	}

	/**
	 * @return the mgrPincode
	 */
	public String getMgrPincode() {
		return mgrPincode;
	}

	/**
	 * @return the mgrState
	 */
	public String getMgrState() {
		return mgrState;
	}

	/**
	 * @return the mgrToken
	 */
	public String getMgrToken() {
		return mgrToken;
	}

	/**
	 * @return the mgrWorkPhone
	 */
	public String getMgrWorkPhone() {
		return mgrWorkPhone;
	}

	/**
	 * @return the ntGroup
	 */
	public String getNtGroup() {
		return ntGroup;
	}

	/**
	 * @return the ntHost1
	 */
	public String getNtHost1() {
		return ntHost1;
	}

	/**
	 * @return the ntHost2
	 */
	public String getNtHost2() {
		return ntHost2;
	}

	/**
	 * @return the ntHost3
	 */
	public String getNtHost3() {
		return ntHost3;
	}

	/**
	 * @return the ntHost4
	 */
	public String getNtHost4() {
		return ntHost4;
	}

	/**
	 * @return the ntHostAccess
	 */
	public String getNtHostAccess() {
		return ntHostAccess;
	}

	/**
	 * @return the pageForApprove
	 */
	public String getPageForApprove() {
		return pageForApprove;
	}

	/**
	 * @return the pageForApproveCancel
	 */
	public String getPageForApproveCancel() {
		return pageForApproveCancel;
	}

	/**
	 * @return the pageForDraft
	 */
	public String getPageForDraft() {
		return pageForDraft;
	}

	/**
	 * @return the pageForPending
	 */
	public String getPageForPending() {
		return pageForPending;
	}

	/**
	 * @return the pageForPendingCancel
	 */
	public String getPageForPendingCancel() {
		return pageForPendingCancel;
	}

	/**
	 * @return the pageForRejected
	 */
	public String getPageForRejected() {
		return pageForRejected;
	}

	/**
	 * @return the pageForRejectedCancel
	 */
	public String getPageForRejectedCancel() {
		return pageForRejectedCancel;
	}

	/**
	 * @return the pageForSendBack
	 */
	public String getPageForSendBack() {
		return pageForSendBack;
	}

	/**
	 * @return the pendingCancelList
	 */
	public List<ApplicationSecurityRequest> getPendingCancelList() {
		return pendingCancelList;
	}

	/**
	 * @return the pendingList
	 */
	public List<ApplicationSecurityRequest> getPendingList() {
		return pendingList;
	}

	/**
	 * @return the pictureIdAccess
	 */
	public String getPictureIdAccess() {
		return pictureIdAccess;
	}

	/**
	 * @return the rejectedCancelList
	 */
	public List<ApplicationSecurityRequest> getRejectedCancelList() {
		return rejectedCancelList;
	}

	/**
	 * @return the rejectedList
	 */
	public List<ApplicationSecurityRequest> getRejectedList() {
		return rejectedList;
	}

	/**
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @return the sendBackList
	 */
	public List<ApplicationSecurityRequest> getSendBackList() {
		return sendBackList;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the trackingNo
	 */
	public String getTrackingNo() {
		return trackingNo;
	}

	/**
	 * @return the unixGroup
	 */
	public String getUnixGroup() {
		return unixGroup;
	}

	/**
	 * @return the unixHost1
	 */
	public String getUnixHost1() {
		return unixHost1;
	}

	/**
	 * @return the unixHost2
	 */
	public String getUnixHost2() {
		return unixHost2;
	}

	/**
	 * @return the unixHost3
	 */
	public String getUnixHost3() {
		return unixHost3;
	}

	/**
	 * @return the unixHost4
	 */
	public String getUnixHost4() {
		return unixHost4;
	}

	/**
	 * @return the unixHostAccess
	 */
	public String getUnixHostAccess() {
		return unixHostAccess;
	}

	/**
	 * @param addedFile the addedFile to set
	 */
	public void setAddedFile(String addedFile) {
		this.addedFile = addedFile;
	}

	/**
	 * @param agency the agency to set
	 */
	public void setAgency(String agency) {
		this.agency = agency;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @param applnInProcList the applnInProcList to set
	 */
	public void setApplnInProcList(List<ApplicationSecurityRequest> applnInProcList) {
		this.applnInProcList = applnInProcList;
	}

	/**
	 * @param applnSecReqId the applnSecReqId to set
	 */
	public void setApplnSecReqId(String applnSecReqId) {
		this.applnSecReqId = applnSecReqId;
	}

	/**
	 * @param applnStatus the applnStatus to set
	 */
	public void setApplnStatus(String applnStatus) {
		this.applnStatus = applnStatus;
	}

	/**
	 * @param apprComments the apprComments to set
	 */
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}

	/**
	 * @param apprDate the apprDate to set
	 */
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}

	/**
	 * @param apprName the apprName to set
	 */
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

	/**
	 * @param approveCancelList the approveCancelList to set
	 */
	public void setApproveCancelList(List<ApplicationSecurityRequest> approveCancelList) {
		this.approveCancelList = approveCancelList;
	}

	/**
	 * @param approveList the approveList to set
	 */
	public void setApproveList(List<ApplicationSecurityRequest> approveList) {
		this.approveList = approveList;
	}

	/**
	 * @param approverComments the approverComments to set
	 */
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	/**
	 * @param approverCommentsList the approverCommentsList to set
	 */
	public void setApproverCommentsList(List<ApplicationSecurityRequest> approverCommentsList) {
		this.approverCommentsList = approverCommentsList;
	}

	/**
	 * @param apprStatus the apprStatus to set
	 */
	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}

	/**
	 * @param asteaFieldDefaultWarehouse the asteaFieldDefaultWarehouse to set
	 */
	public void setAsteaFieldDefaultWarehouse(String asteaFieldDefaultWarehouse) {
		this.asteaFieldDefaultWarehouse = asteaFieldDefaultWarehouse;
	}

	/**
	 * @param asteaFieldRole the asteaFieldRole to set
	 */
	public void setAsteaFieldRole(String asteaFieldRole) {
		this.asteaFieldRole = asteaFieldRole;
	}

	/**
	 * @param asteaFinanceRole the asteaFinanceRole to set
	 */
	public void setAsteaFinanceRole(String asteaFinanceRole) {
		this.asteaFinanceRole = asteaFinanceRole;
	}

	/**
	 * @param asteaLogisticsRole the asteaLogisticsRole to set
	 */
	public void setAsteaLogisticsRole(String asteaLogisticsRole) {
		this.asteaLogisticsRole = asteaLogisticsRole;
	}

	/**
	 * @param asteaWorkgroup the asteaWorkgroup to set
	 */
	public void setAsteaWorkgroup(String asteaWorkgroup) {
		this.asteaWorkgroup = asteaWorkgroup;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @param completedOn the completedOn to set
	 */
	public void setCompletedOn(String completedOn) {
		this.completedOn = completedOn;
	}

	/**
	 * @param copyMgrCity the copyMgrCity to set
	 */
	public void setCopyMgrCity(String copyMgrCity) {
		this.copyMgrCity = copyMgrCity;
	}

	/**
	 * @param copyMgrDept the copyMgrDept to set
	 */
	public void setCopyMgrDept(String copyMgrDept) {
		this.copyMgrDept = copyMgrDept;
	}

	/**
	 * @param copyMgrDiv the copyMgrDiv to set
	 */
	public void setCopyMgrDiv(String copyMgrDiv) {
		this.copyMgrDiv = copyMgrDiv;
	}

	/**
	 * @param copyMgrEmail the copyMgrEmail to set
	 */
	public void setCopyMgrEmail(String copyMgrEmail) {
		this.copyMgrEmail = copyMgrEmail;
	}

	/**
	 * @param copyMgrExt the copyMgrExt to set
	 */
	public void setCopyMgrExt(String copyMgrExt) {
		this.copyMgrExt = copyMgrExt;
	}

	/**
	 * @param copyMgrFax the copyMgrFax to set
	 */
	public void setCopyMgrFax(String copyMgrFax) {
		this.copyMgrFax = copyMgrFax;
	}

	/**
	 * @param copyMgrPincode the copyMgrPincode to set
	 */
	public void setCopyMgrPincode(String copyMgrPincode) {
		this.copyMgrPincode = copyMgrPincode;
	}

	/**
	 * @param copyMgrState the copyMgrState to set
	 */
	public void setCopyMgrState(String copyMgrState) {
		this.copyMgrState = copyMgrState;
	}

	/**
	 * @param copyMgrWorkPhone the copyMgrWorkPhone to set
	 */
	public void setCopyMgrWorkPhone(String copyMgrWorkPhone) {
		this.copyMgrWorkPhone = copyMgrWorkPhone;
	}

	/**
	 * @param dataPath the dataPath to set
	 */
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	/**
	 * @param dataRoomAccess the dataRoomAccess to set
	 */
	public void setDataRoomAccess(String dataRoomAccess) {
		this.dataRoomAccess = dataRoomAccess;
	}

	/**
	 * @param draftList the draftList to set
	 */
	public void setDraftList(List<ApplicationSecurityRequest> draftList) {
		this.draftList = draftList;
	}

	/**
	 * @param empDesignation the empDesignation to set
	 */
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	/**
	 * @param empExpDate the empExpDate to set
	 */
	public void setEmpExpDate(String empExpDate) {
		this.empExpDate = empExpDate;
	}

	/**
	 * @param empFile the empFile to set
	 */
	public void setEmpFile(String empFile) {
		this.empFile = empFile;
	}

	/**
	 * @param empFileRequest the empFileRequest to set
	 */
	public void setEmpFileRequest(String empFileRequest) {
		this.empFileRequest = empFileRequest;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @param empType the empType to set
	 */
	public void setEmpType(String empType) {
		this.empType = empType;
	}

	/**
	 * @param frontDoorAccess the frontDoorAccess to set
	 */
	public void setFrontDoorAccess(String frontDoorAccess) {
		this.frontDoorAccess = frontDoorAccess;
	}

	/**
	 * @param hdnApplications the hdnApplications to set
	 */
	public void setHdnApplications(String hdnApplications) {
		this.hdnApplications = hdnApplications;
	}

	/**
	 * @param hdnEmpType the hdnEmpType to set
	 */
	public void setHdnEmpType(String hdnEmpType) {
		this.hdnEmpType = hdnEmpType;
	}

	/**
	 * @param hdnSections the hdnSections to set
	 */
	public void setHdnSections(String hdnSections) {
		this.hdnSections = hdnSections;
	}

	/**
	 * @param initiatorId the initiatorId to set
	 */
	public void setInitiatorId(String initiatorId) {
		this.initiatorId = initiatorId;
	}

	/**
	 * @param initiatorName the initiatorName to set
	 */
	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	/**
	 * @param listType the listType to set
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}

	/**
	 * @param mgrCity the mgrCity to set
	 */
	public void setMgrCity(String mgrCity) {
		this.mgrCity = mgrCity;
	}

	/**
	 * @param mgrDepartment the mgrDepartment to set
	 */
	public void setMgrDepartment(String mgrDepartment) {
		this.mgrDepartment = mgrDepartment;
	}

	/**
	 * @param mgrDesignation the mgrDesignation to set
	 */
	public void setMgrDesignation(String mgrDesignation) {
		this.mgrDesignation = mgrDesignation;
	}

	/**
	 * @param mgrDivision the mgrDivision to set
	 */
	public void setMgrDivision(String mgrDivision) {
		this.mgrDivision = mgrDivision;
	}

	/**
	 * @param mgrEmail the mgrEmail to set
	 */
	public void setMgrEmail(String mgrEmail) {
		this.mgrEmail = mgrEmail;
	}

	/**
	 * @param mgrExt the mgrExt to set
	 */
	public void setMgrExt(String mgrExt) {
		this.mgrExt = mgrExt;
	}

	/**
	 * @param mgrFax the mgrFax to set
	 */
	public void setMgrFax(String mgrFax) {
		this.mgrFax = mgrFax;
	}

	/**
	 * @param mgrId the mgrId to set
	 */
	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}

	/**
	 * @param mgrName the mgrName to set
	 */
	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}

	/**
	 * @param mgrPincode the mgrPincode to set
	 */
	public void setMgrPincode(String mgrPincode) {
		this.mgrPincode = mgrPincode;
	}

	/**
	 * @param mgrState the mgrState to set
	 */
	public void setMgrState(String mgrState) {
		this.mgrState = mgrState;
	}

	/**
	 * @param mgrToken the mgrToken to set
	 */
	public void setMgrToken(String mgrToken) {
		this.mgrToken = mgrToken;
	}

	/**
	 * @param mgrWorkPhone the mgrWorkPhone to set
	 */
	public void setMgrWorkPhone(String mgrWorkPhone) {
		this.mgrWorkPhone = mgrWorkPhone;
	}

	/**
	 * @param ntGroup the ntGroup to set
	 */
	public void setNtGroup(String ntGroup) {
		this.ntGroup = ntGroup;
	}

	/**
	 * @param ntHost1 the ntHost1 to set
	 */
	public void setNtHost1(String ntHost1) {
		this.ntHost1 = ntHost1;
	}

	/**
	 * @param ntHost2 the ntHost2 to set
	 */
	public void setNtHost2(String ntHost2) {
		this.ntHost2 = ntHost2;
	}

	/**
	 * @param ntHost3 the ntHost3 to set
	 */
	public void setNtHost3(String ntHost3) {
		this.ntHost3 = ntHost3;
	}

	/**
	 * @param ntHost4 the ntHost4 to set
	 */
	public void setNtHost4(String ntHost4) {
		this.ntHost4 = ntHost4;
	}

	/**
	 * @param ntHostAccess the ntHostAccess to set
	 */
	public void setNtHostAccess(String ntHostAccess) {
		this.ntHostAccess = ntHostAccess;
	}

	/**
	 * @param pageForApprove the pageForApprove to set
	 */
	public void setPageForApprove(String pageForApprove) {
		this.pageForApprove = pageForApprove;
	}

	/**
	 * @param pageForApproveCancel the pageForApproveCancel to set
	 */
	public void setPageForApproveCancel(String pageForApproveCancel) {
		this.pageForApproveCancel = pageForApproveCancel;
	}

	/**
	 * @param pageForDraft the pageForDraft to set
	 */
	public void setPageForDraft(String pageForDraft) {
		this.pageForDraft = pageForDraft;
	}

	/**
	 * @param pageForPending the pageForPending to set
	 */
	public void setPageForPending(String pageForPending) {
		this.pageForPending = pageForPending;
	}

	/**
	 * @param pageForPendingCancel the pageForPendingCancel to set
	 */
	public void setPageForPendingCancel(String pageForPendingCancel) {
		this.pageForPendingCancel = pageForPendingCancel;
	}

	/**
	 * @param pageForRejected the pageForRejected to set
	 */
	public void setPageForRejected(String pageForRejected) {
		this.pageForRejected = pageForRejected;
	}

	/**
	 * @param pageForRejectedCancel the pageForRejectedCancel to set
	 */
	public void setPageForRejectedCancel(String pageForRejectedCancel) {
		this.pageForRejectedCancel = pageForRejectedCancel;
	}

	/**
	 * @param pageForSendBack the pageForSendBack to set
	 */
	public void setPageForSendBack(String pageForSendBack) {
		this.pageForSendBack = pageForSendBack;
	}

	/**
	 * @param pendingCancelList the pendingCancelList to set
	 */
	public void setPendingCancelList(List<ApplicationSecurityRequest> pendingCancelList) {
		this.pendingCancelList = pendingCancelList;
	}

	/**
	 * @param pendingList the pendingList to set
	 */
	public void setPendingList(List<ApplicationSecurityRequest> pendingList) {
		this.pendingList = pendingList;
	}

	/**
	 * @param pictureIdAccess the pictureIdAccess to set
	 */
	public void setPictureIdAccess(String pictureIdAccess) {
		this.pictureIdAccess = pictureIdAccess;
	}

	/**
	 * @param rejectedCancelList the rejectedCancelList to set
	 */
	public void setRejectedCancelList(List<ApplicationSecurityRequest> rejectedCancelList) {
		this.rejectedCancelList = rejectedCancelList;
	}

	/**
	 * @param rejectedList the rejectedList to set
	 */
	public void setRejectedList(List<ApplicationSecurityRequest> rejectedList) {
		this.rejectedList = rejectedList;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**
	 * @param sendBackList the sendBackList to set
	 */
	public void setSendBackList(List<ApplicationSecurityRequest> sendBackList) {
		this.sendBackList = sendBackList;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param trackingNo the trackingNo to set
	 */
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	/**
	 * @param unixGroup the unixGroup to set
	 */
	public void setUnixGroup(String unixGroup) {
		this.unixGroup = unixGroup;
	}

	/**
	 * @param unixHost1 the unixHost1 to set
	 */
	public void setUnixHost1(String unixHost1) {
		this.unixHost1 = unixHost1;
	}

	/**
	 * @param unixHost2 the unixHost2 to set
	 */
	public void setUnixHost2(String unixHost2) {
		this.unixHost2 = unixHost2;
	}

	/**
	 * @param unixHost3 the unixHost3 to set
	 */
	public void setUnixHost3(String unixHost3) {
		this.unixHost3 = unixHost3;
	}

	/**
	 * @param unixHost4 the unixHost4 to set
	 */
	public void setUnixHost4(String unixHost4) {
		this.unixHost4 = unixHost4;
	}

	/**
	 * @param unixHostAccess the unixHostAccess to set
	 */
	public void setUnixHostAccess(String unixHostAccess) {
		this.unixHostAccess = unixHostAccess;
	}

	/**
	 * @return the empIniName
	 */
	public String getEmpIniName() {
		return empIniName;
	}

	/**
	 * @param empIniName the empIniName to set
	 */
	public void setEmpIniName(String empIniName) {
		this.empIniName = empIniName;
	}

	/**
	 * @return the pendingListLength
	 */
	public boolean isPendingListLength() {
		return pendingListLength;
	}

	/**
	 * @param pendingListLength the pendingListLength to set
	 */
	public void setPendingListLength(boolean pendingListLength) {
		this.pendingListLength = pendingListLength;
	}

	/**
	 * @return the pendingCancelListLength
	 */
	public boolean isPendingCancelListLength() {
		return pendingCancelListLength;
	}

	/**
	 * @param pendingCancelListLength the pendingCancelListLength to set
	 */
	public void setPendingCancelListLength(boolean pendingCancelListLength) {
		this.pendingCancelListLength = pendingCancelListLength;
	}

	/**
	 * @return the approvedListLength
	 */
	public boolean isApprovedListLength() {
		return approvedListLength;
	}

	/**
	 * @param approvedListLength the approvedListLength to set
	 */
	public void setApprovedListLength(boolean approvedListLength) {
		this.approvedListLength = approvedListLength;
	}

	/**
	 * @return the approvedCancelListLength
	 */
	public boolean isApprovedCancelListLength() {
		return approvedCancelListLength;
	}

	/**
	 * @param approvedCancelListLength the approvedCancelListLength to set
	 */
	public void setApprovedCancelListLength(boolean approvedCancelListLength) {
		this.approvedCancelListLength = approvedCancelListLength;
	}

	/**
	 * @return the rejectedListLength
	 */
	public boolean isRejectedListLength() {
		return rejectedListLength;
	}

	/**
	 * @param rejectedListLength the rejectedListLength to set
	 */
	public void setRejectedListLength(boolean rejectedListLength) {
		this.rejectedListLength = rejectedListLength;
	}

	/**
	 * @return the rejectedCancelListLength
	 */
	public boolean isRejectedCancelListLength() {
		return rejectedCancelListLength;
	}

	/**
	 * @param rejectedCancelListLength the rejectedCancelListLength to set
	 */
	public void setRejectedCancelListLength(boolean rejectedCancelListLength) {
		this.rejectedCancelListLength = rejectedCancelListLength;
	}

	/**
	 * @return the applicationStatus
	 */
	public String getApplicationStatus() {
		return applicationStatus;
	}

	/**
	 * @param applicationStatus the applicationStatus to set
	 */
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	/**
	 * @return the requestTypeItr
	 */
	public String getRequestTypeItr() {
		return this.requestTypeItr;
	}

	/**
	 * @param requestTypeItr the requestTypeItr to set
	 */
	public void setRequestTypeItr(String requestTypeItr) {
		this.requestTypeItr = requestTypeItr;
	}
}