package org.paradyne.bean.D1;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;



public class InformationSystemChangeRequestForm extends BeanBase {

	private String infoSysReqId = "";
	private String hiddenCode = "";
	private String dataPath = "";
	private String reOpenComments = "";
	private boolean reOpenCommentsFlag = false;
	
	// variables for paging
	private String approveLength = "false";
	private String approveCancelLength = "false";
	private String myPageCancelApproved = "";
	private String myPageCancelled = "";
	private String cancelledLength = "false";
	private String myPageApproved = "";
	private String draftLength = "";
	private String myPageDraft = "";
	private String sendbackLength = "";
	private String myPageSendBack = "";

	private String pendingLength = "";
	private String myPagePending = "";

	// added for paging

	private String myPageDrafted = "";
	private String myPageInProcess = "";
	private String myPageSentBack = "";
	// private String myPageApproved = "";
	private String myPageApprovedCancel = "";
	private String myPageCancel = "";
	private String myPageRejected = "";
	private String myPageCancelRejected = "";

	ArrayList draftVoucherIteratorList = null;
	boolean draftVoucherListLength = false;
	ArrayList inProcessVoucherIteratorList = null;
	boolean inProcessVoucherListLength = false;
	ArrayList sentBackVoucherIteratorList = null;
	 boolean sentBackVoucherListLength = false;
	ArrayList approvedApplicationIteratorList = null;
	private boolean approvedApplicationListLength = false;
	ArrayList approvedCancellationApplicationIteratorList = null;
	private boolean approvedCancellationApplicationListLength = false;
	ArrayList cancelledVoucherIteratorList = null;
	private boolean cancelledVoucherListLength = false;
	ArrayList rejectedApplicationIteratorList = null;
	private boolean rejectedApplicationListLength = false;
	ArrayList rejectedCancelApplicationIteratorList = null;
	private boolean rejectedCancelApplicationListLength = false;
	private String listType = "";
	private String myPage = "";
	
	
	private String listTypeDetailPage = "";
	
	boolean approverCommentFlag = false;
	private String approverRole = "";
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";
	ArrayList approverCommentList = null;
	
	public String trackingNo="";
	
	private String initiatorToken = "";

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
	boolean feedbackFlag = false;
	boolean feedbackSubmitFlag = false;
	private String optionalProjectPlan="";
	
	private String trackingFlag = "";
	private String changeTitleItr = "";
	private String pendingWithNameItr = "";
	private String uploadFileNameApproverItr = "";
	
	
	/**
	 * @return the infoSysReqId
	 */
	public String getInfoSysReqId() {
		return this.infoSysReqId;
	}
	/**
	 * @param infoSysReqId the infoSysReqId to set
	 */
	public void setInfoSysReqId(String infoSysReqId) {
		this.infoSysReqId = infoSysReqId;
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
	 * @return the reOpenComments
	 */
	public String getReOpenComments() {
		return this.reOpenComments;
	}
	/**
	 * @param reOpenComments the reOpenComments to set
	 */
	public void setReOpenComments(String reOpenComments) {
		this.reOpenComments = reOpenComments;
	}
	/**
	 * @return the reOpenCommentsFlag
	 */
	public boolean isReOpenCommentsFlag() {
		return this.reOpenCommentsFlag;
	}
	/**
	 * @param reOpenCommentsFlag the reOpenCommentsFlag to set
	 */
	public void setReOpenCommentsFlag(boolean reOpenCommentsFlag) {
		this.reOpenCommentsFlag = reOpenCommentsFlag;
	}
	/**
	 * @return the approveLength
	 */
	public String getApproveLength() {
		return this.approveLength;
	}
	/**
	 * @param approveLength the approveLength to set
	 */
	public void setApproveLength(String approveLength) {
		this.approveLength = approveLength;
	}
	/**
	 * @return the approveCancelLength
	 */
	public String getApproveCancelLength() {
		return this.approveCancelLength;
	}
	/**
	 * @param approveCancelLength the approveCancelLength to set
	 */
	public void setApproveCancelLength(String approveCancelLength) {
		this.approveCancelLength = approveCancelLength;
	}
	/**
	 * @return the myPageCancelApproved
	 */
	public String getMyPageCancelApproved() {
		return this.myPageCancelApproved;
	}
	/**
	 * @param myPageCancelApproved the myPageCancelApproved to set
	 */
	public void setMyPageCancelApproved(String myPageCancelApproved) {
		this.myPageCancelApproved = myPageCancelApproved;
	}
	/**
	 * @return the myPageCancelled
	 */
	public String getMyPageCancelled() {
		return this.myPageCancelled;
	}
	/**
	 * @param myPageCancelled the myPageCancelled to set
	 */
	public void setMyPageCancelled(String myPageCancelled) {
		this.myPageCancelled = myPageCancelled;
	}
	/**
	 * @return the cancelledLength
	 */
	public String getCancelledLength() {
		return this.cancelledLength;
	}
	/**
	 * @param cancelledLength the cancelledLength to set
	 */
	public void setCancelledLength(String cancelledLength) {
		this.cancelledLength = cancelledLength;
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
	 * @return the draftLength
	 */
	public String getDraftLength() {
		return this.draftLength;
	}
	/**
	 * @param draftLength the draftLength to set
	 */
	public void setDraftLength(String draftLength) {
		this.draftLength = draftLength;
	}
	/**
	 * @return the myPageDraft
	 */
	public String getMyPageDraft() {
		return this.myPageDraft;
	}
	/**
	 * @param myPageDraft the myPageDraft to set
	 */
	public void setMyPageDraft(String myPageDraft) {
		this.myPageDraft = myPageDraft;
	}
	/**
	 * @return the sendbackLength
	 */
	public String getSendbackLength() {
		return this.sendbackLength;
	}
	/**
	 * @param sendbackLength the sendbackLength to set
	 */
	public void setSendbackLength(String sendbackLength) {
		this.sendbackLength = sendbackLength;
	}
	/**
	 * @return the myPageSendBack
	 */
	public String getMyPageSendBack() {
		return this.myPageSendBack;
	}
	/**
	 * @param myPageSendBack the myPageSendBack to set
	 */
	public void setMyPageSendBack(String myPageSendBack) {
		this.myPageSendBack = myPageSendBack;
	}
	/**
	 * @return the pendingLength
	 */
	public String getPendingLength() {
		return this.pendingLength;
	}
	/**
	 * @param pendingLength the pendingLength to set
	 */
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	/**
	 * @return the myPagePending
	 */
	public String getMyPagePending() {
		return this.myPagePending;
	}
	/**
	 * @param myPagePending the myPagePending to set
	 */
	public void setMyPagePending(String myPagePending) {
		this.myPagePending = myPagePending;
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
	 * @return the myPageInProcess
	 */
	public String getMyPageInProcess() {
		return this.myPageInProcess;
	}
	/**
	 * @param myPageInProcess the myPageInProcess to set
	 */
	public void setMyPageInProcess(String myPageInProcess) {
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
	public void setMyPageSentBack(String myPageSentBack) {
		this.myPageSentBack = myPageSentBack;
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
	public void setMyPageApprovedCancel(String myPageApprovedCancel) {
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
	public void setMyPageCancel(String myPageCancel) {
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
	public void setMyPageRejected(String myPageRejected) {
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
	public void setMyPageCancelRejected(String myPageCancelRejected) {
		this.myPageCancelRejected = myPageCancelRejected;
	}
	/**
	 * @return the draftVoucherIteratorList
	 */
	public ArrayList getDraftVoucherIteratorList() {
		return this.draftVoucherIteratorList;
	}
	/**
	 * @param draftVoucherIteratorList the draftVoucherIteratorList to set
	 */
	public void setDraftVoucherIteratorList(ArrayList draftVoucherIteratorList) {
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
	public ArrayList getInProcessVoucherIteratorList() {
		return this.inProcessVoucherIteratorList;
	}
	/**
	 * @param inProcessVoucherIteratorList the inProcessVoucherIteratorList to set
	 */
	public void setInProcessVoucherIteratorList(
			ArrayList inProcessVoucherIteratorList) {
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
	public void setInProcessVoucherListLength(boolean inProcessVoucherListLength) {
		this.inProcessVoucherListLength = inProcessVoucherListLength;
	}
	/**
	 * @return the sentBackVoucherIteratorList
	 */
	public ArrayList getSentBackVoucherIteratorList() {
		return this.sentBackVoucherIteratorList;
	}
	/**
	 * @param sentBackVoucherIteratorList the sentBackVoucherIteratorList to set
	 */
	public void setSentBackVoucherIteratorList(ArrayList sentBackVoucherIteratorList) {
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
	 * @return the approvedApplicationIteratorList
	 */
	public ArrayList getApprovedApplicationIteratorList() {
		return this.approvedApplicationIteratorList;
	}
	/**
	 * @param approvedApplicationIteratorList the approvedApplicationIteratorList to set
	 */
	public void setApprovedApplicationIteratorList(
			ArrayList approvedApplicationIteratorList) {
		this.approvedApplicationIteratorList = approvedApplicationIteratorList;
	}
	/**
	 * @return the approvedApplicationListLength
	 */
	public boolean isApprovedApplicationListLength() {
		return this.approvedApplicationListLength;
	}
	/**
	 * @param approvedApplicationListLength the approvedApplicationListLength to set
	 */
	public void setApprovedApplicationListLength(
			boolean approvedApplicationListLength) {
		this.approvedApplicationListLength = approvedApplicationListLength;
	}
	/**
	 * @return the approvedCancellationApplicationIteratorList
	 */
	public ArrayList getApprovedCancellationApplicationIteratorList() {
		return this.approvedCancellationApplicationIteratorList;
	}
	/**
	 * @param approvedCancellationApplicationIteratorList the approvedCancellationApplicationIteratorList to set
	 */
	public void setApprovedCancellationApplicationIteratorList(
			ArrayList approvedCancellationApplicationIteratorList) {
		this.approvedCancellationApplicationIteratorList = approvedCancellationApplicationIteratorList;
	}
	/**
	 * @return the approvedCancellationApplicationListLength
	 */
	public boolean isApprovedCancellationApplicationListLength() {
		return this.approvedCancellationApplicationListLength;
	}
	/**
	 * @param approvedCancellationApplicationListLength the approvedCancellationApplicationListLength to set
	 */
	public void setApprovedCancellationApplicationListLength(
			boolean approvedCancellationApplicationListLength) {
		this.approvedCancellationApplicationListLength = approvedCancellationApplicationListLength;
	}
	/**
	 * @return the cancelledVoucherIteratorList
	 */
	public ArrayList getCancelledVoucherIteratorList() {
		return this.cancelledVoucherIteratorList;
	}
	/**
	 * @param cancelledVoucherIteratorList the cancelledVoucherIteratorList to set
	 */
	public void setCancelledVoucherIteratorList(
			ArrayList cancelledVoucherIteratorList) {
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
	public void setCancelledVoucherListLength(boolean cancelledVoucherListLength) {
		this.cancelledVoucherListLength = cancelledVoucherListLength;
	}
	/**
	 * @return the rejectedApplicationIteratorList
	 */
	public ArrayList getRejectedApplicationIteratorList() {
		return this.rejectedApplicationIteratorList;
	}
	/**
	 * @param rejectedApplicationIteratorList the rejectedApplicationIteratorList to set
	 */
	public void setRejectedApplicationIteratorList(
			ArrayList rejectedApplicationIteratorList) {
		this.rejectedApplicationIteratorList = rejectedApplicationIteratorList;
	}
	/**
	 * @return the rejectedApplicationListLength
	 */
	public boolean isRejectedApplicationListLength() {
		return this.rejectedApplicationListLength;
	}
	/**
	 * @param rejectedApplicationListLength the rejectedApplicationListLength to set
	 */
	public void setRejectedApplicationListLength(
			boolean rejectedApplicationListLength) {
		this.rejectedApplicationListLength = rejectedApplicationListLength;
	}
	/**
	 * @return the rejectedCancelApplicationIteratorList
	 */
	public ArrayList getRejectedCancelApplicationIteratorList() {
		return this.rejectedCancelApplicationIteratorList;
	}
	/**
	 * @param rejectedCancelApplicationIteratorList the rejectedCancelApplicationIteratorList to set
	 */
	public void setRejectedCancelApplicationIteratorList(
			ArrayList rejectedCancelApplicationIteratorList) {
		this.rejectedCancelApplicationIteratorList = rejectedCancelApplicationIteratorList;
	}
	/**
	 * @return the rejectedCancelApplicationListLength
	 */
	public boolean isRejectedCancelApplicationListLength() {
		return this.rejectedCancelApplicationListLength;
	}
	/**
	 * @param rejectedCancelApplicationListLength the rejectedCancelApplicationListLength to set
	 */
	public void setRejectedCancelApplicationListLength(
			boolean rejectedCancelApplicationListLength) {
		this.rejectedCancelApplicationListLength = rejectedCancelApplicationListLength;
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
	 * @return the listTypeDetailPage
	 */
	public String getListTypeDetailPage() {
		return this.listTypeDetailPage;
	}
	/**
	 * @param listTypeDetailPage the listTypeDetailPage to set
	 */
	public void setListTypeDetailPage(String listTypeDetailPage) {
		this.listTypeDetailPage = listTypeDetailPage;
	}
	/**
	 * @return the approverCommentFlag
	 */
	public boolean isApproverCommentFlag() {
		return this.approverCommentFlag;
	}
	/**
	 * @param approverCommentFlag the approverCommentFlag to set
	 */
	public void setApproverCommentFlag(boolean approverCommentFlag) {
		this.approverCommentFlag = approverCommentFlag;
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
	 * @return the feedbackFlag
	 */
	public boolean isFeedbackFlag() {
		return this.feedbackFlag;
	}
	/**
	 * @param feedbackFlag the feedbackFlag to set
	 */
	public void setFeedbackFlag(boolean feedbackFlag) {
		this.feedbackFlag = feedbackFlag;
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
	 * @return the trackingFlag
	 */
	public String getTrackingFlag() {
		return this.trackingFlag;
	}
	/**
	 * @param trackingFlag the trackingFlag to set
	 */
	public void setTrackingFlag(String trackingFlag) {
		this.trackingFlag = trackingFlag;
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
	 * @return the pendingWithNameItr
	 */
	public String getPendingWithNameItr() {
		return this.pendingWithNameItr;
	}
	/**
	 * @param pendingWithNameItr the pendingWithNameItr to set
	 */
	public void setPendingWithNameItr(String pendingWithNameItr) {
		this.pendingWithNameItr = pendingWithNameItr;
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