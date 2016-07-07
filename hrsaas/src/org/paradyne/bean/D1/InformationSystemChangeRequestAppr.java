package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class InformationSystemChangeRequestAppr extends BeanBase {
	/** * forwardToRoleMap. : Map for role. */
	private TreeMap<String, String> forwardToRoleMap;
	private String selectForwardToRole = "";
	private String forwardToEmployeeId = "";
	private String forwardToEmployeeToken = "";
	private String forwardToEmployeeName = "";
	private String assignmentComments = "";
	private boolean itInfoSystemChangeGroupFlag = false;
	private boolean forwardedEmployeeFlag = false;
	private boolean activityAssignmentForwardEmpFlag = false;
	
	private String infoSysReqApprId = "";
	private String hiddenCode = "";
	private String dataPath = "";
	
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
	
	
	private String myPage = "";
	private String totalRecords = "";
	
	private String changeTitle = "";
	private String changeSchedularOccur="";
	private String changeCategory="";
	private String reason="";
	private String whatChange="";
	private String impactChange="";
	private String riskAssociatedChange="";
	private String expectResult="";
	private String currentStatusChange="";
	private String detailPlanAction="";
	private String uploadFileName="";
	private String uploadFileSrNo="";
	private String optionalProjectPlanAttachment="";
	private String uploadOptionalFileName="";
	private String backoutPlanEstimate="";
	private String whoPerformChangeTesting="";
	private String howChangeTested="";
	private String updateOptional="";
	private String describeChange="";
	private String identifyImprovement="";
	private String comments="";
	private String firstApproverCode="";
	private String firstApproverToken="";
	private String approverName="";
	private String srNoIterator="";
	private String approverToken="";
	private String selectapproverName="";
	private String approverCode="";
	private String initiatorCode="";
	private String initiatorName="";
	private String initiatorDate="";
	private String employeeCode = "";
	private String employeeName = "";
	
	private String uploadLocFileName = "";
	
	ArrayList proofList;
	private String proofSrNo="";
	private String proofName="";
	private String proofFileName="";
	private String checkRemoveUpload="";
	
	private String uploadFileNameOptional = "";
	
	ArrayList proofListOptional;
	private String proofSrNoOptional="";
	private String proofNameOptional="";
	private String proofFileNameOptional="";
	private String checkRemoveUploadOptional="";
	private String ittproofNameOptional = "";
	
	private String ittproofName="";
	private String dataPath1 = "";
	private String applDate="";
	private ArrayList approverList;
	private String status="";
	
	
	
	///////////////////added ganesh
	
	private List pendingAppList;
	private List pendingCancelAppList;
	private List approvedAppList;
	private List rejectedAppList;

	private boolean pagingForPendingApp;
	private boolean pagingForPendingCancelApp;
	private boolean pagingForApprovedApp;
	private boolean pagingForRejectedApp;

	private String pageForPendingApp;
	private String pageForPendingCancelApp;
	private String pageForApprovedApp;
	private String pageForRejectedApp;

	private String listType;
	
	// Approver Comments List 
	private String approverRole = "";
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";
	ArrayList approverCommentList = null;
	private String requesterID = "";
	private String level = "";
	
	private String employeeToken = "";
	private boolean secondAppFlag= false;

	private String secondApproverId="";
	private String secondApproverToken = "";
	private String secondApproverName = "";
	
	private String applnStatus = "";
	public String trackingNo="";

	private String initiatorToken = "";
	private String locationOption = "";
	boolean approverCommentsFlag = false;
	private String ListTypeDetailPage = "";
	private String approverComments = "";
	private String itApprover = "";
	
	boolean feedbackSubmitFlag = false;
	private String optionalProjectPlan="";
	
	ArrayList closedIteratorList = null;
	private boolean closedListLength = false;
	private String myPageClosed = "";
	
	private String changeTitleItr = "";
	private String uploadFileNameApprover = "";
	private String uploadFileNameApproverItr = "";
	/**
	 * @return the forwardToRoleMap
	 */
	public TreeMap<String, String> getForwardToRoleMap() {
		return this.forwardToRoleMap;
	}
	/**
	 * @param forwardToRoleMap the forwardToRoleMap to set
	 */
	public void setForwardToRoleMap(TreeMap<String, String> forwardToRoleMap) {
		this.forwardToRoleMap = forwardToRoleMap;
	}
	/**
	 * @return the selectForwardToRole
	 */
	public String getSelectForwardToRole() {
		return this.selectForwardToRole;
	}
	/**
	 * @param selectForwardToRole the selectForwardToRole to set
	 */
	public void setSelectForwardToRole(String selectForwardToRole) {
		this.selectForwardToRole = selectForwardToRole;
	}
	/**
	 * @return the forwardToEmployeeId
	 */
	public String getForwardToEmployeeId() {
		return this.forwardToEmployeeId;
	}
	/**
	 * @param forwardToEmployeeId the forwardToEmployeeId to set
	 */
	public void setForwardToEmployeeId(String forwardToEmployeeId) {
		this.forwardToEmployeeId = forwardToEmployeeId;
	}
	/**
	 * @return the forwardToEmployeeToken
	 */
	public String getForwardToEmployeeToken() {
		return this.forwardToEmployeeToken;
	}
	/**
	 * @param forwardToEmployeeToken the forwardToEmployeeToken to set
	 */
	public void setForwardToEmployeeToken(String forwardToEmployeeToken) {
		this.forwardToEmployeeToken = forwardToEmployeeToken;
	}
	/**
	 * @return the forwardToEmployeeName
	 */
	public String getForwardToEmployeeName() {
		return this.forwardToEmployeeName;
	}
	/**
	 * @param forwardToEmployeeName the forwardToEmployeeName to set
	 */
	public void setForwardToEmployeeName(String forwardToEmployeeName) {
		this.forwardToEmployeeName = forwardToEmployeeName;
	}
	/**
	 * @return the assignmentComments
	 */
	public String getAssignmentComments() {
		return this.assignmentComments;
	}
	/**
	 * @param assignmentComments the assignmentComments to set
	 */
	public void setAssignmentComments(String assignmentComments) {
		this.assignmentComments = assignmentComments;
	}
	/**
	 * @return the itInfoSystemChangeGroupFlag
	 */
	public boolean isItInfoSystemChangeGroupFlag() {
		return this.itInfoSystemChangeGroupFlag;
	}
	/**
	 * @param itInfoSystemChangeGroupFlag the itInfoSystemChangeGroupFlag to set
	 */
	public void setItInfoSystemChangeGroupFlag(boolean itInfoSystemChangeGroupFlag) {
		this.itInfoSystemChangeGroupFlag = itInfoSystemChangeGroupFlag;
	}
	/**
	 * @return the forwardedEmployeeFlag
	 */
	public boolean isForwardedEmployeeFlag() {
		return this.forwardedEmployeeFlag;
	}
	/**
	 * @param forwardedEmployeeFlag the forwardedEmployeeFlag to set
	 */
	public void setForwardedEmployeeFlag(boolean forwardedEmployeeFlag) {
		this.forwardedEmployeeFlag = forwardedEmployeeFlag;
	}
	/**
	 * @return the activityAssignmentForwardEmpFlag
	 */
	public boolean isActivityAssignmentForwardEmpFlag() {
		return this.activityAssignmentForwardEmpFlag;
	}
	/**
	 * @param activityAssignmentForwardEmpFlag the activityAssignmentForwardEmpFlag to set
	 */
	public void setActivityAssignmentForwardEmpFlag(
			boolean activityAssignmentForwardEmpFlag) {
		this.activityAssignmentForwardEmpFlag = activityAssignmentForwardEmpFlag;
	}
	/**
	 * @return the infoSysReqApprId
	 */
	public String getInfoSysReqApprId() {
		return this.infoSysReqApprId;
	}
	/**
	 * @param infoSysReqApprId the infoSysReqApprId to set
	 */
	public void setInfoSysReqApprId(String infoSysReqApprId) {
		this.infoSysReqApprId = infoSysReqApprId;
	}
	/**
	 * @return the hiddenCode
	 */
	public String getHiddenCode() {
		return this.hiddenCode;
	}
	/**
	 * @param hiddenCode the hiddenCode to set
	 */
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
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
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
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
	public void setMyPageDrafted(String myPageDrafted) {
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
	public void setMyPagePendingCancel(String myPagePendingCancel) {
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
	public void setMyPageApproved(String myPageApproved) {
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
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	/**
	 * @return the pendingIteratorList
	 */
	public ArrayList getPendingIteratorList() {
		return this.pendingIteratorList;
	}
	/**
	 * @param pendingIteratorList the pendingIteratorList to set
	 */
	public void setPendingIteratorList(ArrayList pendingIteratorList) {
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
	public ArrayList getPendingCancellationIteratorList() {
		return this.pendingCancellationIteratorList;
	}
	/**
	 * @param pendingCancellationIteratorList the pendingCancellationIteratorList to set
	 */
	public void setPendingCancellationIteratorList(
			ArrayList pendingCancellationIteratorList) {
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
	public ArrayList getApproveredIteratorList() {
		return this.approveredIteratorList;
	}
	/**
	 * @param approveredIteratorList the approveredIteratorList to set
	 */
	public void setApproveredIteratorList(ArrayList approveredIteratorList) {
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
	public void setApprovedListLength(boolean approvedListLength) {
		this.approvedListLength = approvedListLength;
	}
	/**
	 * @return the rejectedIteratorList
	 */
	public ArrayList getRejectedIteratorList() {
		return this.rejectedIteratorList;
	}
	/**
	 * @param rejectedIteratorList the rejectedIteratorList to set
	 */
	public void setRejectedIteratorList(ArrayList rejectedIteratorList) {
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
	 * @return the myPage
	 */
	public String getMyPage() {
		return this.myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
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
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/**
	 * @return the changeTitle
	 */
	public String getChangeTitle() {
		return this.changeTitle;
	}
	/**
	 * @param changeTitle the changeTitle to set
	 */
	public void setChangeTitle(String changeTitle) {
		this.changeTitle = changeTitle;
	}
	/**
	 * @return the changeSchedularOccur
	 */
	public String getChangeSchedularOccur() {
		return this.changeSchedularOccur;
	}
	/**
	 * @param changeSchedularOccur the changeSchedularOccur to set
	 */
	public void setChangeSchedularOccur(String changeSchedularOccur) {
		this.changeSchedularOccur = changeSchedularOccur;
	}
	/**
	 * @return the changeCategory
	 */
	public String getChangeCategory() {
		return this.changeCategory;
	}
	/**
	 * @param changeCategory the changeCategory to set
	 */
	public void setChangeCategory(String changeCategory) {
		this.changeCategory = changeCategory;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return this.reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the whatChange
	 */
	public String getWhatChange() {
		return this.whatChange;
	}
	/**
	 * @param whatChange the whatChange to set
	 */
	public void setWhatChange(String whatChange) {
		this.whatChange = whatChange;
	}
	/**
	 * @return the impactChange
	 */
	public String getImpactChange() {
		return this.impactChange;
	}
	/**
	 * @param impactChange the impactChange to set
	 */
	public void setImpactChange(String impactChange) {
		this.impactChange = impactChange;
	}
	/**
	 * @return the riskAssociatedChange
	 */
	public String getRiskAssociatedChange() {
		return this.riskAssociatedChange;
	}
	/**
	 * @param riskAssociatedChange the riskAssociatedChange to set
	 */
	public void setRiskAssociatedChange(String riskAssociatedChange) {
		this.riskAssociatedChange = riskAssociatedChange;
	}
	/**
	 * @return the expectResult
	 */
	public String getExpectResult() {
		return this.expectResult;
	}
	/**
	 * @param expectResult the expectResult to set
	 */
	public void setExpectResult(String expectResult) {
		this.expectResult = expectResult;
	}
	/**
	 * @return the currentStatusChange
	 */
	public String getCurrentStatusChange() {
		return this.currentStatusChange;
	}
	/**
	 * @param currentStatusChange the currentStatusChange to set
	 */
	public void setCurrentStatusChange(String currentStatusChange) {
		this.currentStatusChange = currentStatusChange;
	}
	/**
	 * @return the detailPlanAction
	 */
	public String getDetailPlanAction() {
		return this.detailPlanAction;
	}
	/**
	 * @param detailPlanAction the detailPlanAction to set
	 */
	public void setDetailPlanAction(String detailPlanAction) {
		this.detailPlanAction = detailPlanAction;
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
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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
	public void setUploadFileSrNo(String uploadFileSrNo) {
		this.uploadFileSrNo = uploadFileSrNo;
	}
	/**
	 * @return the optionalProjectPlanAttachment
	 */
	public String getOptionalProjectPlanAttachment() {
		return this.optionalProjectPlanAttachment;
	}
	/**
	 * @param optionalProjectPlanAttachment the optionalProjectPlanAttachment to set
	 */
	public void setOptionalProjectPlanAttachment(
			String optionalProjectPlanAttachment) {
		this.optionalProjectPlanAttachment = optionalProjectPlanAttachment;
	}
	/**
	 * @return the uploadOptionalFileName
	 */
	public String getUploadOptionalFileName() {
		return this.uploadOptionalFileName;
	}
	/**
	 * @param uploadOptionalFileName the uploadOptionalFileName to set
	 */
	public void setUploadOptionalFileName(String uploadOptionalFileName) {
		this.uploadOptionalFileName = uploadOptionalFileName;
	}
	/**
	 * @return the backoutPlanEstimate
	 */
	public String getBackoutPlanEstimate() {
		return this.backoutPlanEstimate;
	}
	/**
	 * @param backoutPlanEstimate the backoutPlanEstimate to set
	 */
	public void setBackoutPlanEstimate(String backoutPlanEstimate) {
		this.backoutPlanEstimate = backoutPlanEstimate;
	}
	/**
	 * @return the whoPerformChangeTesting
	 */
	public String getWhoPerformChangeTesting() {
		return this.whoPerformChangeTesting;
	}
	/**
	 * @param whoPerformChangeTesting the whoPerformChangeTesting to set
	 */
	public void setWhoPerformChangeTesting(String whoPerformChangeTesting) {
		this.whoPerformChangeTesting = whoPerformChangeTesting;
	}
	/**
	 * @return the howChangeTested
	 */
	public String getHowChangeTested() {
		return this.howChangeTested;
	}
	/**
	 * @param howChangeTested the howChangeTested to set
	 */
	public void setHowChangeTested(String howChangeTested) {
		this.howChangeTested = howChangeTested;
	}
	/**
	 * @return the updateOptional
	 */
	public String getUpdateOptional() {
		return this.updateOptional;
	}
	/**
	 * @param updateOptional the updateOptional to set
	 */
	public void setUpdateOptional(String updateOptional) {
		this.updateOptional = updateOptional;
	}
	/**
	 * @return the describeChange
	 */
	public String getDescribeChange() {
		return this.describeChange;
	}
	/**
	 * @param describeChange the describeChange to set
	 */
	public void setDescribeChange(String describeChange) {
		this.describeChange = describeChange;
	}
	/**
	 * @return the identifyImprovement
	 */
	public String getIdentifyImprovement() {
		return this.identifyImprovement;
	}
	/**
	 * @param identifyImprovement the identifyImprovement to set
	 */
	public void setIdentifyImprovement(String identifyImprovement) {
		this.identifyImprovement = identifyImprovement;
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
	public void setComments(String comments) {
		this.comments = comments;
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
	public void setFirstApproverCode(String firstApproverCode) {
		this.firstApproverCode = firstApproverCode;
	}
	/**
	 * @return the firstApproverToken
	 */
	public String getFirstApproverToken() {
		return this.firstApproverToken;
	}
	/**
	 * @param firstApproverToken the firstApproverToken to set
	 */
	public void setFirstApproverToken(String firstApproverToken) {
		this.firstApproverToken = firstApproverToken;
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
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	/**
	 * @return the srNoIterator
	 */
	public String getSrNoIterator() {
		return this.srNoIterator;
	}
	/**
	 * @param srNoIterator the srNoIterator to set
	 */
	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
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
	public void setApproverToken(String approverToken) {
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
	public void setSelectapproverName(String selectapproverName) {
		this.selectapproverName = selectapproverName;
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
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	/**
	 * @return the initiatorCode
	 */
	public String getInitiatorCode() {
		return this.initiatorCode;
	}
	/**
	 * @param initiatorCode the initiatorCode to set
	 */
	public void setInitiatorCode(String initiatorCode) {
		this.initiatorCode = initiatorCode;
	}
	/**
	 * @return the initiatorName
	 */
	public String getInitiatorName() {
		return this.initiatorName;
	}
	/**
	 * @param initiatorName the initiatorName to set
	 */
	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}
	/**
	 * @return the initiatorDate
	 */
	public String getInitiatorDate() {
		return this.initiatorDate;
	}
	/**
	 * @param initiatorDate the initiatorDate to set
	 */
	public void setInitiatorDate(String initiatorDate) {
		this.initiatorDate = initiatorDate;
	}
	/**
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return this.employeeCode;
	}
	/**
	 * @param employeeCode the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return this.employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the uploadLocFileName
	 */
	public String getUploadLocFileName() {
		return this.uploadLocFileName;
	}
	/**
	 * @param uploadLocFileName the uploadLocFileName to set
	 */
	public void setUploadLocFileName(String uploadLocFileName) {
		this.uploadLocFileName = uploadLocFileName;
	}
	/**
	 * @return the proofList
	 */
	public ArrayList getProofList() {
		return this.proofList;
	}
	/**
	 * @param proofList the proofList to set
	 */
	public void setProofList(ArrayList proofList) {
		this.proofList = proofList;
	}
	/**
	 * @return the proofSrNo
	 */
	public String getProofSrNo() {
		return this.proofSrNo;
	}
	/**
	 * @param proofSrNo the proofSrNo to set
	 */
	public void setProofSrNo(String proofSrNo) {
		this.proofSrNo = proofSrNo;
	}
	/**
	 * @return the proofName
	 */
	public String getProofName() {
		return this.proofName;
	}
	/**
	 * @param proofName the proofName to set
	 */
	public void setProofName(String proofName) {
		this.proofName = proofName;
	}
	/**
	 * @return the proofFileName
	 */
	public String getProofFileName() {
		return this.proofFileName;
	}
	/**
	 * @param proofFileName the proofFileName to set
	 */
	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
	}
	/**
	 * @return the checkRemoveUpload
	 */
	public String getCheckRemoveUpload() {
		return this.checkRemoveUpload;
	}
	/**
	 * @param checkRemoveUpload the checkRemoveUpload to set
	 */
	public void setCheckRemoveUpload(String checkRemoveUpload) {
		this.checkRemoveUpload = checkRemoveUpload;
	}
	/**
	 * @return the uploadFileNameOptional
	 */
	public String getUploadFileNameOptional() {
		return this.uploadFileNameOptional;
	}
	/**
	 * @param uploadFileNameOptional the uploadFileNameOptional to set
	 */
	public void setUploadFileNameOptional(String uploadFileNameOptional) {
		this.uploadFileNameOptional = uploadFileNameOptional;
	}
	/**
	 * @return the proofListOptional
	 */
	public ArrayList getProofListOptional() {
		return this.proofListOptional;
	}
	/**
	 * @param proofListOptional the proofListOptional to set
	 */
	public void setProofListOptional(ArrayList proofListOptional) {
		this.proofListOptional = proofListOptional;
	}
	/**
	 * @return the proofSrNoOptional
	 */
	public String getProofSrNoOptional() {
		return this.proofSrNoOptional;
	}
	/**
	 * @param proofSrNoOptional the proofSrNoOptional to set
	 */
	public void setProofSrNoOptional(String proofSrNoOptional) {
		this.proofSrNoOptional = proofSrNoOptional;
	}
	/**
	 * @return the proofNameOptional
	 */
	public String getProofNameOptional() {
		return this.proofNameOptional;
	}
	/**
	 * @param proofNameOptional the proofNameOptional to set
	 */
	public void setProofNameOptional(String proofNameOptional) {
		this.proofNameOptional = proofNameOptional;
	}
	/**
	 * @return the proofFileNameOptional
	 */
	public String getProofFileNameOptional() {
		return this.proofFileNameOptional;
	}
	/**
	 * @param proofFileNameOptional the proofFileNameOptional to set
	 */
	public void setProofFileNameOptional(String proofFileNameOptional) {
		this.proofFileNameOptional = proofFileNameOptional;
	}
	/**
	 * @return the checkRemoveUploadOptional
	 */
	public String getCheckRemoveUploadOptional() {
		return this.checkRemoveUploadOptional;
	}
	/**
	 * @param checkRemoveUploadOptional the checkRemoveUploadOptional to set
	 */
	public void setCheckRemoveUploadOptional(String checkRemoveUploadOptional) {
		this.checkRemoveUploadOptional = checkRemoveUploadOptional;
	}
	/**
	 * @return the ittproofNameOptional
	 */
	public String getIttproofNameOptional() {
		return this.ittproofNameOptional;
	}
	/**
	 * @param ittproofNameOptional the ittproofNameOptional to set
	 */
	public void setIttproofNameOptional(String ittproofNameOptional) {
		this.ittproofNameOptional = ittproofNameOptional;
	}
	/**
	 * @return the ittproofName
	 */
	public String getIttproofName() {
		return this.ittproofName;
	}
	/**
	 * @param ittproofName the ittproofName to set
	 */
	public void setIttproofName(String ittproofName) {
		this.ittproofName = ittproofName;
	}
	/**
	 * @return the dataPath1
	 */
	public String getDataPath1() {
		return this.dataPath1;
	}
	/**
	 * @param dataPath1 the dataPath1 to set
	 */
	public void setDataPath1(String dataPath1) {
		this.dataPath1 = dataPath1;
	}
	/**
	 * @return the applDate
	 */
	public String getApplDate() {
		return this.applDate;
	}
	/**
	 * @param applDate the applDate to set
	 */
	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}
	/**
	 * @return the approverList
	 */
	public ArrayList getApproverList() {
		return this.approverList;
	}
	/**
	 * @param approverList the approverList to set
	 */
	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
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
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the pendingAppList
	 */
	public List getPendingAppList() {
		return this.pendingAppList;
	}
	/**
	 * @param pendingAppList the pendingAppList to set
	 */
	public void setPendingAppList(List pendingAppList) {
		this.pendingAppList = pendingAppList;
	}
	/**
	 * @return the pendingCancelAppList
	 */
	public List getPendingCancelAppList() {
		return this.pendingCancelAppList;
	}
	/**
	 * @param pendingCancelAppList the pendingCancelAppList to set
	 */
	public void setPendingCancelAppList(List pendingCancelAppList) {
		this.pendingCancelAppList = pendingCancelAppList;
	}
	/**
	 * @return the approvedAppList
	 */
	public List getApprovedAppList() {
		return this.approvedAppList;
	}
	/**
	 * @param approvedAppList the approvedAppList to set
	 */
	public void setApprovedAppList(List approvedAppList) {
		this.approvedAppList = approvedAppList;
	}
	/**
	 * @return the rejectedAppList
	 */
	public List getRejectedAppList() {
		return this.rejectedAppList;
	}
	/**
	 * @param rejectedAppList the rejectedAppList to set
	 */
	public void setRejectedAppList(List rejectedAppList) {
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
	public void setPagingForPendingApp(boolean pagingForPendingApp) {
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
	public void setPagingForPendingCancelApp(boolean pagingForPendingCancelApp) {
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
	public void setPagingForApprovedApp(boolean pagingForApprovedApp) {
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
	public void setPagingForRejectedApp(boolean pagingForRejectedApp) {
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
	public void setPageForPendingApp(String pageForPendingApp) {
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
	public void setPageForPendingCancelApp(String pageForPendingCancelApp) {
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
	public void setPageForApprovedApp(String pageForApprovedApp) {
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
	public void setPageForRejectedApp(String pageForRejectedApp) {
		this.pageForRejectedApp = pageForRejectedApp;
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
	public void setListType(String listType) {
		this.listType = listType;
	}
	/**
	 * @return the approverRole
	 */
	public String getApproverRole() {
		return this.approverRole;
	}
	/**
	 * @param approverRole the approverRole to set
	 */
	public void setApproverRole(String approverRole) {
		this.approverRole = approverRole;
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
	public void setApprName(String apprName) {
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
	public void setApprComments(String apprComments) {
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
	public void setApprDate(String apprDate) {
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
	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}
	/**
	 * @return the approverCommentList
	 */
	public ArrayList getApproverCommentList() {
		return this.approverCommentList;
	}
	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	/**
	 * @return the requesterID
	 */
	public String getRequesterID() {
		return this.requesterID;
	}
	/**
	 * @param requesterID the requesterID to set
	 */
	public void setRequesterID(String requesterID) {
		this.requesterID = requesterID;
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
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the employeeToken
	 */
	public String getEmployeeToken() {
		return this.employeeToken;
	}
	/**
	 * @param employeeToken the employeeToken to set
	 */
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
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
	public void setSecondAppFlag(boolean secondAppFlag) {
		this.secondAppFlag = secondAppFlag;
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
	public void setSecondApproverId(String secondApproverId) {
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
	public void setSecondApproverToken(String secondApproverToken) {
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
	public void setSecondApproverName(String secondApproverName) {
		this.secondApproverName = secondApproverName;
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
	public void setApplnStatus(String applnStatus) {
		this.applnStatus = applnStatus;
	}
	/**
	 * @return the trackingNo
	 */
	public String getTrackingNo() {
		return this.trackingNo;
	}
	/**
	 * @param trackingNo the trackingNo to set
	 */
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	/**
	 * @return the initiatorToken
	 */
	public String getInitiatorToken() {
		return this.initiatorToken;
	}
	/**
	 * @param initiatorToken the initiatorToken to set
	 */
	public void setInitiatorToken(String initiatorToken) {
		this.initiatorToken = initiatorToken;
	}
	/**
	 * @return the locationOption
	 */
	public String getLocationOption() {
		return this.locationOption;
	}
	/**
	 * @param locationOption the locationOption to set
	 */
	public void setLocationOption(String locationOption) {
		this.locationOption = locationOption;
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
	 * @return the listTypeDetailPage
	 */
	public String getListTypeDetailPage() {
		return this.ListTypeDetailPage;
	}
	/**
	 * @param listTypeDetailPage the listTypeDetailPage to set
	 */
	public void setListTypeDetailPage(String listTypeDetailPage) {
		this.ListTypeDetailPage = listTypeDetailPage;
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
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	/**
	 * @return the itApprover
	 */
	public String getItApprover() {
		return this.itApprover;
	}
	/**
	 * @param itApprover the itApprover to set
	 */
	public void setItApprover(String itApprover) {
		this.itApprover = itApprover;
	}
	/**
	 * @return the feedbackSubmitFlag
	 */
	public boolean isFeedbackSubmitFlag() {
		return this.feedbackSubmitFlag;
	}
	/**
	 * @param feedbackSubmitFlag the feedbackSubmitFlag to set
	 */
	public void setFeedbackSubmitFlag(boolean feedbackSubmitFlag) {
		this.feedbackSubmitFlag = feedbackSubmitFlag;
	}
	/**
	 * @return the optionalProjectPlan
	 */
	public String getOptionalProjectPlan() {
		return this.optionalProjectPlan;
	}
	/**
	 * @param optionalProjectPlan the optionalProjectPlan to set
	 */
	public void setOptionalProjectPlan(String optionalProjectPlan) {
		this.optionalProjectPlan = optionalProjectPlan;
	}
	/**
	 * @return the closedIteratorList
	 */
	public ArrayList getClosedIteratorList() {
		return this.closedIteratorList;
	}
	/**
	 * @param closedIteratorList the closedIteratorList to set
	 */
	public void setClosedIteratorList(ArrayList closedIteratorList) {
		this.closedIteratorList = closedIteratorList;
	}
	/**
	 * @return the closedListLength
	 */
	public boolean isClosedListLength() {
		return this.closedListLength;
	}
	/**
	 * @param closedListLength the closedListLength to set
	 */
	public void setClosedListLength(boolean closedListLength) {
		this.closedListLength = closedListLength;
	}
	/**
	 * @return the myPageClosed
	 */
	public String getMyPageClosed() {
		return this.myPageClosed;
	}
	/**
	 * @param myPageClosed the myPageClosed to set
	 */
	public void setMyPageClosed(String myPageClosed) {
		this.myPageClosed = myPageClosed;
	}
	/**
	 * @return the changeTitleItr
	 */
	public String getChangeTitleItr() {
		return this.changeTitleItr;
	}
	/**
	 * @param changeTitleItr the changeTitleItr to set
	 */
	public void setChangeTitleItr(String changeTitleItr) {
		this.changeTitleItr = changeTitleItr;
	}
	/**
	 * @return the uploadFileNameApprover
	 */
	public String getUploadFileNameApprover() {
		return this.uploadFileNameApprover;
	}
	/**
	 * @param uploadFileNameApprover the uploadFileNameApprover to set
	 */
	public void setUploadFileNameApprover(String uploadFileNameApprover) {
		this.uploadFileNameApprover = uploadFileNameApprover;
	}
	/**
	 * @return the uploadFileNameApproverItr
	 */
	public String getUploadFileNameApproverItr() {
		return this.uploadFileNameApproverItr;
	}
	/**
	 * @param uploadFileNameApproverItr the uploadFileNameApproverItr to set
	 */
	public void setUploadFileNameApproverItr(String uploadFileNameApproverItr) {
		this.uploadFileNameApproverItr = uploadFileNameApproverItr;
	}
	
		
	
}
