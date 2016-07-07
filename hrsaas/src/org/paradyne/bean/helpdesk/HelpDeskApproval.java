/**
 * 
 */
package org.paradyne.bean.helpdesk;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class HelpDeskApproval extends BeanBase {
	
	private String approveRejectStatus  = "";
	private String checkReportingStructure ="";
	private String logFromEmp="";
	private String logToEmp="";
	private String logStatus="";
	private String logDate="";
	private String logComments="";
	private String logLength="false";
	ArrayList<Object> logList;
	private String reqMgrStatus = "";
	
	private boolean approverCommentFlag = false ; 
	private boolean approverCommentListFlag = false ; 
	private String prevApproverID  = "";
	private String prevApproverName  = "";
	private String prevApproverDate  = "";
	private String prevApproverStatus  = "";
	private String prevApproverComment  = "";
	private ArrayList approverCommentList =null ; 
	
	private String approverComments = "";
	
	private String radioCheck ="";
	private String reqisitionStatus ="";
	private String decodedReqisitionStatus ="";
	private String requestId = "";
	private String requestForEmpId = "";
	private String reqRadio = "";
	private String assetType = "";
	private String assetTypeCode = "";
	private String isAssetRequest = "";
	private String assetSubType = "";
	private String assetSubTypeCode = "";
	private String isManagerApproval = "";
	private String assetQuantity = "";
	private String initiatorId = "";
	private String initiatorToken = "";
	private String initiatorName = "";
	private String pendingEmpId = "";
	private String pendingEmpToken = "";
	private String pendingEmpName = "";
	private String otherEmpId = "";
	private String otherEmpToken = "";
	private String otherEmpName = "";
	private String deptId = "";
	private String reqManagerStatus = "";
	private String reqEmpStatus = "";
	private String reqEmpContactNo = "";
	private String reqEmpEmailId = "";
	private String reqEmpExtensionNo = "";
	private String decodedReqStatus = "";

	// VARIABLES FOR DRAFT LIST
	private ArrayList draftList;
	private String draftEmpId = "";
	private String draftReqNo = "";

	// VARIABLES FOR SUBMIT LIST
	private ArrayList reopenedList;
	private String reopenedEmpId = "";
	private String reopenedReqNo = "";

	// VARIABLES FOR RETURN LIST
	private ArrayList selfList;
	private String selfEmpId = "";
	private String selfReqNo = "";
	
	// VARIABLES FOR RETURN LIST
	private ArrayList assignedList;
	private String assignedEmpId = "";
	private String assignedReqNo = "";

	// VARIABLES FOR APPROVED LIST approved

	private ArrayList resolvedList;
	private String resolvedEmpId = "";
	private String resolvedReqNo = "";

	// VARIABLES FOR PENDING LIST
	private ArrayList pendingList;
	private String pendingReqNo = "";
	private String reqStatusIsLast="";
	private String reqStatusOrder="";
	private String reqStatusName="";
	private String pendingReqDept = "";
	private String pendingReqCode = "";
	
	// VARIABLES FOR CLOSED LIST rejected

	private ArrayList closedList;
	private String closedEmpId = "";
	private String closedReqNo = "";
	private String closedReqDept = "";
	private String closedReqCode = "";

	// VARIABLES FOR LIST
	private String reqEmpToken = "";
	private String reqEmpName = "";
	private String reqDate = "";
	private String listType = "";
	private String reqStatus = "";
	private String reqSubject = "";
	
	//VARIABLES FOR PAGING
	private String myPage="";
	private String totalRecords="";
	private String myPageClosed="";
	private String closedLength="false";
	private String pendingLength="false";
	
	private String empId = "";
	private String empName = "";
	private String branchName = "";
	private String appDate = "";
	private String appType = "";
	private String appFor;
	private String hAppFor;
	private String reqDeptCode = "";
	private String reqDeptName = "";
	private String reqType = "";
	private String reqTypeCode = "";
	private String subReqTypeCode = "";
	private String subReqType = "";
	private String comments = "";
	private String isappcount = "";
	private String clientName="";
	private String isApprove	= "false";
	private String isReqApp = "true";
	TreeMap tmap;
	private String assToEmpCode;
	private String assToEmpName;
	private String assToEmpToken;
	private String assignFlag	= "false";
	private String newStatus="";
	private String statusOrder="";
	//FIELDS FOR UPLOAD PROOF
	private String proofSrNo="";
	private ArrayList proofList;
	private String proofName="";
	private boolean proofListFlag = false ; 
	private String dataPath="";
	private String checkRemoveUpload="";
	private String uploadFileName="";
	private String uploadDoc="";
	private String uploadFlag="false";
	private String resolvedFlag	= "false";
	private String reopenCloseDesc="";
	
	private File myFile;
	private String contentType;
	
	private String slaListFlag="false";
	private String statusCategory;
	private String statusDuration;
	ArrayList slaList;
	private String slaName;
	
	private String reqDtlListFlag="false";
	ArrayList reqDtlList;
	private String prevOwner;
	private String newOwner;
	private String chkStatus;
	private String chkDate;
	private String chkComments;
	
	
	private String reqToken ="";
	private String helpcode		= "";
	private String department	= "";
	private String desgName		= "";
	private String contactNo	= "";
	private String applText	 	= "";
	private String subject		= "";
	private String description	= "";
	private String applTextCode	= "";
	private String branchCode	= "";
	private String desgCode		= "";
	private String deptCode		= "";
	private String empCode		= "";
	private String status;
	private String hiddenStatus;
	private int level		= 0;
	private String empToken;
	private String levelFlag 	= "false";
	private String callAttndDatet;
	
	private String time 		= "";
	private String hideStatus	= "";
	private String deptName     = "";
	
	private String source    = "";
	
	private String setFilesFlag	= "false";

	public String getRadioCheck() {
		return radioCheck;
	}

	public void setRadioCheck(String radioCheck) {
		this.radioCheck = radioCheck;
	}

	public String getReqisitionStatus() {
		return reqisitionStatus;
	}

	public void setReqisitionStatus(String reqisitionStatus) {
		this.reqisitionStatus = reqisitionStatus;
	}

	public String getDecodedReqisitionStatus() {
		return decodedReqisitionStatus;
	}

	public void setDecodedReqisitionStatus(String decodedReqisitionStatus) {
		this.decodedReqisitionStatus = decodedReqisitionStatus;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequestForEmpId() {
		return requestForEmpId;
	}

	public void setRequestForEmpId(String requestForEmpId) {
		this.requestForEmpId = requestForEmpId;
	}

	public String getReqRadio() {
		return reqRadio;
	}

	public void setReqRadio(String reqRadio) {
		this.reqRadio = reqRadio;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetTypeCode() {
		return assetTypeCode;
	}

	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}

	public String getIsAssetRequest() {
		return isAssetRequest;
	}

	public void setIsAssetRequest(String isAssetRequest) {
		this.isAssetRequest = isAssetRequest;
	}

	public String getAssetSubType() {
		return assetSubType;
	}

	public void setAssetSubType(String assetSubType) {
		this.assetSubType = assetSubType;
	}

	public String getAssetSubTypeCode() {
		return assetSubTypeCode;
	}

	public void setAssetSubTypeCode(String assetSubTypeCode) {
		this.assetSubTypeCode = assetSubTypeCode;
	}

	public String getIsManagerApproval() {
		return isManagerApproval;
	}

	public void setIsManagerApproval(String isManagerApproval) {
		this.isManagerApproval = isManagerApproval;
	}

	public String getAssetQuantity() {
		return assetQuantity;
	}

	public void setAssetQuantity(String assetQuantity) {
		this.assetQuantity = assetQuantity;
	}

	public String getInitiatorId() {
		return initiatorId;
	}

	public void setInitiatorId(String initiatorId) {
		this.initiatorId = initiatorId;
	}

	public String getInitiatorToken() {
		return initiatorToken;
	}

	public void setInitiatorToken(String initiatorToken) {
		this.initiatorToken = initiatorToken;
	}

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public String getPendingEmpId() {
		return pendingEmpId;
	}

	public void setPendingEmpId(String pendingEmpId) {
		this.pendingEmpId = pendingEmpId;
	}

	public String getPendingEmpToken() {
		return pendingEmpToken;
	}

	public void setPendingEmpToken(String pendingEmpToken) {
		this.pendingEmpToken = pendingEmpToken;
	}

	public String getPendingEmpName() {
		return pendingEmpName;
	}

	public void setPendingEmpName(String pendingEmpName) {
		this.pendingEmpName = pendingEmpName;
	}

	public String getOtherEmpId() {
		return otherEmpId;
	}

	public void setOtherEmpId(String otherEmpId) {
		this.otherEmpId = otherEmpId;
	}

	public String getOtherEmpToken() {
		return otherEmpToken;
	}

	public void setOtherEmpToken(String otherEmpToken) {
		this.otherEmpToken = otherEmpToken;
	}

	public String getOtherEmpName() {
		return otherEmpName;
	}

	public void setOtherEmpName(String otherEmpName) {
		this.otherEmpName = otherEmpName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getReqManagerStatus() {
		return reqManagerStatus;
	}

	public void setReqManagerStatus(String reqManagerStatus) {
		this.reqManagerStatus = reqManagerStatus;
	}

	public String getReqEmpStatus() {
		return reqEmpStatus;
	}

	public void setReqEmpStatus(String reqEmpStatus) {
		this.reqEmpStatus = reqEmpStatus;
	}

	public ArrayList getDraftList() {
		return draftList;
	}

	public void setDraftList(ArrayList draftList) {
		this.draftList = draftList;
	}

	public String getDraftEmpId() {
		return draftEmpId;
	}

	public void setDraftEmpId(String draftEmpId) {
		this.draftEmpId = draftEmpId;
	}

	public String getDraftReqNo() {
		return draftReqNo;
	}

	public void setDraftReqNo(String draftReqNo) {
		this.draftReqNo = draftReqNo;
	}

	public ArrayList getReopenedList() {
		return reopenedList;
	}

	public void setReopenedList(ArrayList reopenedList) {
		this.reopenedList = reopenedList;
	}

	public String getReopenedEmpId() {
		return reopenedEmpId;
	}

	public void setReopenedEmpId(String reopenedEmpId) {
		this.reopenedEmpId = reopenedEmpId;
	}

	public String getReopenedReqNo() {
		return reopenedReqNo;
	}

	public void setReopenedReqNo(String reopenedReqNo) {
		this.reopenedReqNo = reopenedReqNo;
	}

	public ArrayList getSelfList() {
		return selfList;
	}

	public void setSelfList(ArrayList selfList) {
		this.selfList = selfList;
	}

	public String getSelfEmpId() {
		return selfEmpId;
	}

	public void setSelfEmpId(String selfEmpId) {
		this.selfEmpId = selfEmpId;
	}

	public String getSelfReqNo() {
		return selfReqNo;
	}

	public void setSelfReqNo(String selfReqNo) {
		this.selfReqNo = selfReqNo;
	}

	public ArrayList getAssignedList() {
		return assignedList;
	}

	public void setAssignedList(ArrayList assignedList) {
		this.assignedList = assignedList;
	}

	public String getAssignedEmpId() {
		return assignedEmpId;
	}

	public void setAssignedEmpId(String assignedEmpId) {
		this.assignedEmpId = assignedEmpId;
	}

	public String getAssignedReqNo() {
		return assignedReqNo;
	}

	public void setAssignedReqNo(String assignedReqNo) {
		this.assignedReqNo = assignedReqNo;
	}

	public ArrayList getResolvedList() {
		return resolvedList;
	}

	public void setResolvedList(ArrayList resolvedList) {
		this.resolvedList = resolvedList;
	}

	public String getResolvedEmpId() {
		return resolvedEmpId;
	}

	public void setResolvedEmpId(String resolvedEmpId) {
		this.resolvedEmpId = resolvedEmpId;
	}

	public String getResolvedReqNo() {
		return resolvedReqNo;
	}

	public void setResolvedReqNo(String resolvedReqNo) {
		this.resolvedReqNo = resolvedReqNo;
	}

	public ArrayList getPendingList() {
		return pendingList;
	}

	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}

	public String getPendingReqNo() {
		return pendingReqNo;
	}

	public void setPendingReqNo(String pendingReqNo) {
		this.pendingReqNo = pendingReqNo;
	}

	public String getReqStatusIsLast() {
		return reqStatusIsLast;
	}

	public void setReqStatusIsLast(String reqStatusIsLast) {
		this.reqStatusIsLast = reqStatusIsLast;
	}

	public String getReqStatusOrder() {
		return reqStatusOrder;
	}

	public void setReqStatusOrder(String reqStatusOrder) {
		this.reqStatusOrder = reqStatusOrder;
	}

	public String getReqStatusName() {
		return reqStatusName;
	}

	public void setReqStatusName(String reqStatusName) {
		this.reqStatusName = reqStatusName;
	}

	public String getPendingReqDept() {
		return pendingReqDept;
	}

	public void setPendingReqDept(String pendingReqDept) {
		this.pendingReqDept = pendingReqDept;
	}

	public String getPendingReqCode() {
		return pendingReqCode;
	}

	public void setPendingReqCode(String pendingReqCode) {
		this.pendingReqCode = pendingReqCode;
	}

	public ArrayList getClosedList() {
		return closedList;
	}

	public void setClosedList(ArrayList closedList) {
		this.closedList = closedList;
	}

	public String getClosedEmpId() {
		return closedEmpId;
	}

	public void setClosedEmpId(String closedEmpId) {
		this.closedEmpId = closedEmpId;
	}

	public String getClosedReqNo() {
		return closedReqNo;
	}

	public void setClosedReqNo(String closedReqNo) {
		this.closedReqNo = closedReqNo;
	}

	public String getClosedReqDept() {
		return closedReqDept;
	}

	public void setClosedReqDept(String closedReqDept) {
		this.closedReqDept = closedReqDept;
	}

	public String getClosedReqCode() {
		return closedReqCode;
	}

	public void setClosedReqCode(String closedReqCode) {
		this.closedReqCode = closedReqCode;
	}

	public String getReqEmpToken() {
		return reqEmpToken;
	}

	public void setReqEmpToken(String reqEmpToken) {
		this.reqEmpToken = reqEmpToken;
	}

	public String getReqEmpName() {
		return reqEmpName;
	}

	public void setReqEmpName(String reqEmpName) {
		this.reqEmpName = reqEmpName;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	public String getReqSubject() {
		return reqSubject;
	}

	public void setReqSubject(String reqSubject) {
		this.reqSubject = reqSubject;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getMyPageClosed() {
		return myPageClosed;
	}

	public void setMyPageClosed(String myPageClosed) {
		this.myPageClosed = myPageClosed;
	}

	public String getClosedLength() {
		return closedLength;
	}

	public void setClosedLength(String closedLength) {
		this.closedLength = closedLength;
	}

	public String getPendingLength() {
		return pendingLength;
	}

	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppFor() {
		return appFor;
	}

	public void setAppFor(String appFor) {
		this.appFor = appFor;
	}

	public String getHAppFor() {
		return hAppFor;
	}

	public void setHAppFor(String appFor) {
		hAppFor = appFor;
	}

	public String getReqDeptCode() {
		return reqDeptCode;
	}

	public void setReqDeptCode(String reqDeptCode) {
		this.reqDeptCode = reqDeptCode;
	}

	public String getReqDeptName() {
		return reqDeptName;
	}

	public void setReqDeptName(String reqDeptName) {
		this.reqDeptName = reqDeptName;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getReqTypeCode() {
		return reqTypeCode;
	}

	public void setReqTypeCode(String reqTypeCode) {
		this.reqTypeCode = reqTypeCode;
	}

	public String getSubReqTypeCode() {
		return subReqTypeCode;
	}

	public void setSubReqTypeCode(String subReqTypeCode) {
		this.subReqTypeCode = subReqTypeCode;
	}

	public String getSubReqType() {
		return subReqType;
	}

	public void setSubReqType(String subReqType) {
		this.subReqType = subReqType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIsappcount() {
		return isappcount;
	}

	public void setIsappcount(String isappcount) {
		this.isappcount = isappcount;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}

	public String getIsReqApp() {
		return isReqApp;
	}

	public void setIsReqApp(String isReqApp) {
		this.isReqApp = isReqApp;
	}

	public TreeMap getTmap() {
		return tmap;
	}

	public void setTmap(TreeMap tmap) {
		this.tmap = tmap;
	}

	public String getAssToEmpCode() {
		return assToEmpCode;
	}

	public void setAssToEmpCode(String assToEmpCode) {
		this.assToEmpCode = assToEmpCode;
	}

	public String getAssToEmpName() {
		return assToEmpName;
	}

	public void setAssToEmpName(String assToEmpName) {
		this.assToEmpName = assToEmpName;
	}

	public String getAssToEmpToken() {
		return assToEmpToken;
	}

	public void setAssToEmpToken(String assToEmpToken) {
		this.assToEmpToken = assToEmpToken;
	}

	public String getAssignFlag() {
		return assignFlag;
	}

	public void setAssignFlag(String assignFlag) {
		this.assignFlag = assignFlag;
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

	public String getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}

	public String getProofSrNo() {
		return proofSrNo;
	}

	public void setProofSrNo(String proofSrNo) {
		this.proofSrNo = proofSrNo;
	}

	public ArrayList getProofList() {
		return proofList;
	}

	public void setProofList(ArrayList proofList) {
		this.proofList = proofList;
	}

	public String getProofName() {
		return proofName;
	}

	public void setProofName(String proofName) {
		this.proofName = proofName;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getCheckRemoveUpload() {
		return checkRemoveUpload;
	}

	public void setCheckRemoveUpload(String checkRemoveUpload) {
		this.checkRemoveUpload = checkRemoveUpload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadDoc() {
		return uploadDoc;
	}

	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}

	public String getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}

	public String getResolvedFlag() {
		return resolvedFlag;
	}

	public void setResolvedFlag(String resolvedFlag) {
		this.resolvedFlag = resolvedFlag;
	}

	public String getReopenCloseDesc() {
		return reopenCloseDesc;
	}

	public void setReopenCloseDesc(String reopenCloseDesc) {
		this.reopenCloseDesc = reopenCloseDesc;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getSlaListFlag() {
		return slaListFlag;
	}

	public void setSlaListFlag(String slaListFlag) {
		this.slaListFlag = slaListFlag;
	}

	public String getStatusCategory() {
		return statusCategory;
	}

	public void setStatusCategory(String statusCategory) {
		this.statusCategory = statusCategory;
	}

	public String getStatusDuration() {
		return statusDuration;
	}

	public void setStatusDuration(String statusDuration) {
		this.statusDuration = statusDuration;
	}

	public ArrayList getSlaList() {
		return slaList;
	}

	public void setSlaList(ArrayList slaList) {
		this.slaList = slaList;
	}

	public String getSlaName() {
		return slaName;
	}

	public void setSlaName(String slaName) {
		this.slaName = slaName;
	}

	public String getReqDtlListFlag() {
		return reqDtlListFlag;
	}

	public void setReqDtlListFlag(String reqDtlListFlag) {
		this.reqDtlListFlag = reqDtlListFlag;
	}

	public ArrayList getReqDtlList() {
		return reqDtlList;
	}

	public void setReqDtlList(ArrayList reqDtlList) {
		this.reqDtlList = reqDtlList;
	}

	public String getPrevOwner() {
		return prevOwner;
	}

	public void setPrevOwner(String prevOwner) {
		this.prevOwner = prevOwner;
	}

	public String getNewOwner() {
		return newOwner;
	}

	public void setNewOwner(String newOwner) {
		this.newOwner = newOwner;
	}

	public String getChkStatus() {
		return chkStatus;
	}

	public void setChkStatus(String chkStatus) {
		this.chkStatus = chkStatus;
	}

	public String getChkDate() {
		return chkDate;
	}

	public void setChkDate(String chkDate) {
		this.chkDate = chkDate;
	}

	public String getChkComments() {
		return chkComments;
	}

	public void setChkComments(String chkComments) {
		this.chkComments = chkComments;
	}

	public String getReqToken() {
		return reqToken;
	}

	public void setReqToken(String reqToken) {
		this.reqToken = reqToken;
	}

	public String getHelpcode() {
		return helpcode;
	}

	public void setHelpcode(String helpcode) {
		this.helpcode = helpcode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getApplText() {
		return applText;
	}

	public void setApplText(String applText) {
		this.applText = applText;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApplTextCode() {
		return applTextCode;
	}

	public void setApplTextCode(String applTextCode) {
		this.applTextCode = applTextCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getDesgCode() {
		return desgCode;
	}

	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHiddenStatus() {
		return hiddenStatus;
	}

	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getLevelFlag() {
		return levelFlag;
	}

	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}

	public String getCallAttndDatet() {
		return callAttndDatet;
	}

	public void setCallAttndDatet(String callAttndDatet) {
		this.callAttndDatet = callAttndDatet;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getHideStatus() {
		return hideStatus;
	}

	public void setHideStatus(String hideStatus) {
		this.hideStatus = hideStatus;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getSetFilesFlag() {
		return setFilesFlag;
	}

	public void setSetFilesFlag(String setFilesFlag) {
		this.setFilesFlag = setFilesFlag;
	}

	public boolean isApproverCommentListFlag() {
		return approverCommentListFlag;
	}

	public void setApproverCommentListFlag(boolean approverCommentListFlag) {
		this.approverCommentListFlag = approverCommentListFlag;
	}

	public String getPrevApproverID() {
		return prevApproverID;
	}

	public void setPrevApproverID(String prevApproverID) {
		this.prevApproverID = prevApproverID;
	}

	public String getPrevApproverName() {
		return prevApproverName;
	}

	public void setPrevApproverName(String prevApproverName) {
		this.prevApproverName = prevApproverName;
	}

	public String getPrevApproverDate() {
		return prevApproverDate;
	}

	public void setPrevApproverDate(String prevApproverDate) {
		this.prevApproverDate = prevApproverDate;
	}

	public String getPrevApproverStatus() {
		return prevApproverStatus;
	}

	public void setPrevApproverStatus(String prevApproverStatus) {
		this.prevApproverStatus = prevApproverStatus;
	}

	public String getPrevApproverComment() {
		return prevApproverComment;
	}

	public void setPrevApproverComment(String prevApproverComment) {
		this.prevApproverComment = prevApproverComment;
	}

	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}

	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}

	public String getApproveRejectStatus() {
		return approveRejectStatus;
	}

	public void setApproveRejectStatus(String approveRejectStatus) {
		this.approveRejectStatus = approveRejectStatus;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public String getCheckReportingStructure() {
		return checkReportingStructure;
	}

	public void setCheckReportingStructure(String checkReportingStructure) {
		this.checkReportingStructure = checkReportingStructure;
	}

	public String getLogFromEmp() {
		return logFromEmp;
	}

	public void setLogFromEmp(String logFromEmp) {
		this.logFromEmp = logFromEmp;
	}

	public String getLogToEmp() {
		return logToEmp;
	}

	public void setLogToEmp(String logToEmp) {
		this.logToEmp = logToEmp;
	}

	public String getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public String getLogComments() {
		return logComments;
	}

	public void setLogComments(String logComments) {
		this.logComments = logComments;
	}

	public String getLogLength() {
		return logLength;
	}

	public void setLogLength(String logLength) {
		this.logLength = logLength;
	}

	public ArrayList<Object> getLogList() {
		return logList;
	}

	public void setLogList(ArrayList<Object> logList) {
		this.logList = logList;
	}

	public String getReqMgrStatus() {
		return reqMgrStatus;
	}

	public void setReqMgrStatus(String reqMgrStatus) {
		this.reqMgrStatus = reqMgrStatus;
	}

	public boolean isApproverCommentFlag() {
		return approverCommentFlag;
	}

	public void setApproverCommentFlag(boolean approverCommentFlag) {
		this.approverCommentFlag = approverCommentFlag;
	}

	public boolean isProofListFlag() {
		return proofListFlag;
	}

	public void setProofListFlag(boolean proofListFlag) {
		this.proofListFlag = proofListFlag;
	}

	public String getReqEmpContactNo() {
		return reqEmpContactNo;
	}

	public void setReqEmpContactNo(String reqEmpContactNo) {
		this.reqEmpContactNo = reqEmpContactNo;
	}

	public String getReqEmpEmailId() {
		return reqEmpEmailId;
	}

	public void setReqEmpEmailId(String reqEmpEmailId) {
		this.reqEmpEmailId = reqEmpEmailId;
	}

	public String getReqEmpExtensionNo() {
		return reqEmpExtensionNo;
	}

	public void setReqEmpExtensionNo(String reqEmpExtensionNo) {
		this.reqEmpExtensionNo = reqEmpExtensionNo;
	}

	public String getDecodedReqStatus() {
		return decodedReqStatus;
	}

	public void setDecodedReqStatus(String decodedReqStatus) {
		this.decodedReqStatus = decodedReqStatus;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
