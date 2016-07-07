/**
 * 
 */
package org.paradyne.bean.helpdesk;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author Reeba_Joseph
 *
 */
public class HelpDesk extends BeanBase {
	
	// ADDED by Prashant
	private boolean reopenFlag = false ; 
	private boolean proofListFlag = false ; 
	private String checkReportingStructure ="";
	private String logFromEmp="";
	private String logToEmp="";
	private String logStatus="";
	private String logDate="";
	private String logComments="";
	private String logLength="false";
	ArrayList<Object> logList;
	
	private String reopenComments ="";
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
	private String status 		= "";
	private String hiddenStatus = "";
	private int level		= 0;
	private String empToken;
	private String levelFlag 	= "false";
	private String callAttndDatet;
	
	private String time 		= "";
	private String hideStatus	= "";
	private String deptName     = "";
	
	private String setFilesFlag	= "false";
	
	//Added by Nilesh on 26th Dec 11
	
	private String source = "";
	
	/**
	 * @return the appFor
	 */
	public String getAppFor() {
		return appFor;
	}
	/**
	 * @param appFor the appFor to set
	 */
	public void setAppFor(String appFor) {
		this.appFor = appFor;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the assToEmpToken
	 */
	public String getAssToEmpToken() {
		return assToEmpToken;
	}
	/**
	 * @param assToEmpToken the assToEmpToken to set
	 */
	public void setAssToEmpToken(String assToEmpToken) {
		this.assToEmpToken = assToEmpToken;
	}
	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}
	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getCallAttndDatet() {
		return callAttndDatet;
	}
	public void setCallAttndDatet(String callAttndDatet) {
		this.callAttndDatet = callAttndDatet;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
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
	public String getApplTextCode() {
		return applTextCode;
	}
	public void setApplTextCode(String applTextCode) {
		this.applTextCode = applTextCode;
	}
	public String getHelpcode() {
		return helpcode;
	}
	public void setHelpcode(String helpcode) {
		this.helpcode = helpcode;
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
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getApplText() {
		return applText;
	}
	public void setApplText(String applText) {
		this.applText = applText;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
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
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}
	public String getAssignFlag() {
		return assignFlag;
	}
	public void setAssignFlag(String assignFlag) {
		this.assignFlag = assignFlag;
	}
	public String getLevelFlag() {
		return levelFlag;
	}
	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
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
	public String getHideStatus() {
		return hideStatus;
	}
	public void setHideStatus(String hideStatus) {
		this.hideStatus = hideStatus;
	}
	/**
	 * @return the draftList
	 */
	public ArrayList getDraftList() {
		return draftList;
	}
	/**
	 * @param draftList the draftList to set
	 */
	public void setDraftList(ArrayList draftList) {
		this.draftList = draftList;
	}
	/**
	 * @return the draftEmpId
	 */
	public String getDraftEmpId() {
		return draftEmpId;
	}
	/**
	 * @param draftEmpId the draftEmpId to set
	 */
	public void setDraftEmpId(String draftEmpId) {
		this.draftEmpId = draftEmpId;
	}
	/**
	 * @return the draftReqNo
	 */
	public String getDraftReqNo() {
		return draftReqNo;
	}
	/**
	 * @param draftReqNo the draftReqNo to set
	 */
	public void setDraftReqNo(String draftReqNo) {
		this.draftReqNo = draftReqNo;
	}
	/**
	 * @return the reopenedList
	 */
	public ArrayList getReopenedList() {
		return reopenedList;
	}
	/**
	 * @param reopenedList the reopenedList to set
	 */
	public void setReopenedList(ArrayList reopenedList) {
		this.reopenedList = reopenedList;
	}
	/**
	 * @return the reopenedEmpId
	 */
	public String getReopenedEmpId() {
		return reopenedEmpId;
	}
	/**
	 * @param reopenedEmpId the reopenedEmpId to set
	 */
	public void setReopenedEmpId(String reopenedEmpId) {
		this.reopenedEmpId = reopenedEmpId;
	}
	/**
	 * @return the reopenedReqNo
	 */
	public String getReopenedReqNo() {
		return reopenedReqNo;
	}
	/**
	 * @param reopenedReqNo the reopenedReqNo to set
	 */
	public void setReopenedReqNo(String reopenedReqNo) {
		this.reopenedReqNo = reopenedReqNo;
	}
	/**
	 * @return the selfList
	 */
	public ArrayList getSelfList() {
		return selfList;
	}
	/**
	 * @param selfList the selfList to set
	 */
	public void setSelfList(ArrayList selfList) {
		this.selfList = selfList;
	}
	/**
	 * @return the selfEmpId
	 */
	public String getSelfEmpId() {
		return selfEmpId;
	}
	/**
	 * @param selfEmpId the selfEmpId to set
	 */
	public void setSelfEmpId(String selfEmpId) {
		this.selfEmpId = selfEmpId;
	}
	/**
	 * @return the selfReqNo
	 */
	public String getSelfReqNo() {
		return selfReqNo;
	}
	/**
	 * @param selfReqNo the selfReqNo to set
	 */
	public void setSelfReqNo(String selfReqNo) {
		this.selfReqNo = selfReqNo;
	}
	/**
	 * @return the assignedList
	 */
	public ArrayList getAssignedList() {
		return assignedList;
	}
	/**
	 * @param assignedList the assignedList to set
	 */
	public void setAssignedList(ArrayList assignedList) {
		this.assignedList = assignedList;
	}
	/**
	 * @return the assignedEmpId
	 */
	public String getAssignedEmpId() {
		return assignedEmpId;
	}
	/**
	 * @param assignedEmpId the assignedEmpId to set
	 */
	public void setAssignedEmpId(String assignedEmpId) {
		this.assignedEmpId = assignedEmpId;
	}
	/**
	 * @return the assignedReqNo
	 */
	public String getAssignedReqNo() {
		return assignedReqNo;
	}
	/**
	 * @param assignedReqNo the assignedReqNo to set
	 */
	public void setAssignedReqNo(String assignedReqNo) {
		this.assignedReqNo = assignedReqNo;
	}
	/**
	 * @return the resolvedList
	 */
	public ArrayList getResolvedList() {
		return resolvedList;
	}
	/**
	 * @param resolvedList the resolvedList to set
	 */
	public void setResolvedList(ArrayList resolvedList) {
		this.resolvedList = resolvedList;
	}
	/**
	 * @return the resolvedEmpId
	 */
	public String getResolvedEmpId() {
		return resolvedEmpId;
	}
	/**
	 * @param resolvedEmpId the resolvedEmpId to set
	 */
	public void setResolvedEmpId(String resolvedEmpId) {
		this.resolvedEmpId = resolvedEmpId;
	}
	/**
	 * @return the resolvedReqNo
	 */
	public String getResolvedReqNo() {
		return resolvedReqNo;
	}
	/**
	 * @param resolvedReqNo the resolvedReqNo to set
	 */
	public void setResolvedReqNo(String resolvedReqNo) {
		this.resolvedReqNo = resolvedReqNo;
	}
	/**
	 * @return the closedList
	 */
	public ArrayList getClosedList() {
		return closedList;
	}
	/**
	 * @param closedList the closedList to set
	 */
	public void setClosedList(ArrayList closedList) {
		this.closedList = closedList;
	}
	/**
	 * @return the closedEmpId
	 */
	public String getClosedEmpId() {
		return closedEmpId;
	}
	/**
	 * @param closedEmpId the closedEmpId to set
	 */
	public void setClosedEmpId(String closedEmpId) {
		this.closedEmpId = closedEmpId;
	}
	/**
	 * @return the closedReqNo
	 */
	public String getClosedReqNo() {
		return closedReqNo;
	}
	/**
	 * @param closedReqNo the closedReqNo to set
	 */
	public void setClosedReqNo(String closedReqNo) {
		this.closedReqNo = closedReqNo;
	}
	/**
	 * @return the reqEmpToken
	 */
	public String getReqEmpToken() {
		return reqEmpToken;
	}
	/**
	 * @param reqEmpToken the reqEmpToken to set
	 */
	public void setReqEmpToken(String reqEmpToken) {
		this.reqEmpToken = reqEmpToken;
	}
	/**
	 * @return the reqEmpName
	 */
	public String getReqEmpName() {
		return reqEmpName;
	}
	/**
	 * @param reqEmpName the reqEmpName to set
	 */
	public void setReqEmpName(String reqEmpName) {
		this.reqEmpName = reqEmpName;
	}
	/**
	 * @return the reqDate
	 */
	public String getReqDate() {
		return reqDate;
	}
	/**
	 * @param reqDate the reqDate to set
	 */
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	/**
	 * @return the listType
	 */
	public String getListType() {
		return listType;
	}
	/**
	 * @param listType the listType to set
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}
	/**
	 * @return the reqStatus
	 */
	public String getReqStatus() {
		return reqStatus;
	}
	/**
	 * @param reqStatus the reqStatus to set
	 */
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
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
		return totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/**
	 * @return the myPageClosed
	 */
	public String getMyPageClosed() {
		return myPageClosed;
	}
	/**
	 * @param myPageClosed the myPageClosed to set
	 */
	public void setMyPageClosed(String myPageClosed) {
		this.myPageClosed = myPageClosed;
	}
	/**
	 * @return the closedLength
	 */
	public String getClosedLength() {
		return closedLength;
	}
	/**
	 * @param closedLength the closedLength to set
	 */
	public void setClosedLength(String closedLength) {
		this.closedLength = closedLength;
	}
	/**
	 * @return the pendingList
	 */
	public ArrayList getPendingList() {
		return pendingList;
	}
	/**
	 * @param pendingList the pendingList to set
	 */
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}
	/**
	 * @return the pendingEmpId
	 */
	public String getPendingEmpId() {
		return pendingEmpId;
	}
	/**
	 * @param pendingEmpId the pendingEmpId to set
	 */
	public void setPendingEmpId(String pendingEmpId) {
		this.pendingEmpId = pendingEmpId;
	}
	/**
	 * @return the pendingReqNo
	 */
	public String getPendingReqNo() {
		return pendingReqNo;
	}
	/**
	 * @param pendingReqNo the pendingReqNo to set
	 */
	public void setPendingReqNo(String pendingReqNo) {
		this.pendingReqNo = pendingReqNo;
	}
	/**
	 * @return the pendingLength
	 */
	public String getPendingLength() {
		return pendingLength;
	}
	/**
	 * @param pendingLength the pendingLength to set
	 */
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	/**
	 * @return the appType
	 */
	public String getAppType() {
		return appType;
	}
	/**
	 * @param appType the appType to set
	 */
	public void setAppType(String appType) {
		this.appType = appType;
	}
	/**
	 * @return the hAppFor
	 */
	public String getHAppFor() {
		return hAppFor;
	}
	/**
	 * @param appFor the hAppFor to set
	 */
	public void setHAppFor(String appFor) {
		hAppFor = appFor;
	}
	/**
	 * @return the reqDeptCode
	 */
	public String getReqDeptCode() {
		return reqDeptCode;
	}
	/**
	 * @param reqDeptCode the reqDeptCode to set
	 */
	public void setReqDeptCode(String reqDeptCode) {
		this.reqDeptCode = reqDeptCode;
	}
	/**
	 * @return the reqDeptName
	 */
	public String getReqDeptName() {
		return reqDeptName;
	}
	/**
	 * @param reqDeptName the reqDeptName to set
	 */
	public void setReqDeptName(String reqDeptName) {
		this.reqDeptName = reqDeptName;
	}
	/**
	 * @return the reqType
	 */
	public String getReqType() {
		return reqType;
	}
	/**
	 * @param reqType the reqType to set
	 */
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	/**
	 * @return the reqTypeCode
	 */
	public String getReqTypeCode() {
		return reqTypeCode;
	}
	/**
	 * @param reqTypeCode the reqTypeCode to set
	 */
	public void setReqTypeCode(String reqTypeCode) {
		this.reqTypeCode = reqTypeCode;
	}
	/**
	 * @return the subReqTypeCode
	 */
	public String getSubReqTypeCode() {
		return subReqTypeCode;
	}
	/**
	 * @param subReqTypeCode the subReqTypeCode to set
	 */
	public void setSubReqTypeCode(String subReqTypeCode) {
		this.subReqTypeCode = subReqTypeCode;
	}
	/**
	 * @return the subReqType
	 */
	public String getSubReqType() {
		return subReqType;
	}
	/**
	 * @param subReqType the subReqType to set
	 */
	public void setSubReqType(String subReqType) {
		this.subReqType = subReqType;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the isappcount
	 */
	public String getIsappcount() {
		return isappcount;
	}
	/**
	 * @param isappcount the isappcount to set
	 */
	public void setIsappcount(String isappcount) {
		this.isappcount = isappcount;
	}
	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * @return the isReqApp
	 */
	public String getIsReqApp() {
		return isReqApp;
	}
	/**
	 * @param isReqApp the isReqApp to set
	 */
	public void setIsReqApp(String isReqApp) {
		this.isReqApp = isReqApp;
	}
	/**
	 * @return the tmap
	 */
	public TreeMap getTmap() {
		return tmap;
	}
	/**
	 * @param tmap the tmap to set
	 */
	public void setTmap(TreeMap tmap) {
		this.tmap = tmap;
	}
	/**
	 * @return the newStatus
	 */
	public String getNewStatus() {
		return newStatus;
	}
	/**
	 * @param newStatus the newStatus to set
	 */
	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}
	/**
	 * @return the statusOrder
	 */
	public String getStatusOrder() {
		return statusOrder;
	}
	/**
	 * @param statusOrder the statusOrder to set
	 */
	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}
	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}
	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * @return the resolvedFlag
	 */
	public String getResolvedFlag() {
		return resolvedFlag;
	}
	/**
	 * @param resolvedFlag the resolvedFlag to set
	 */
	public void setResolvedFlag(String resolvedFlag) {
		this.resolvedFlag = resolvedFlag;
	}
	/**
	 * @return the reqStatusIsLast
	 */
	public String getReqStatusIsLast() {
		return reqStatusIsLast;
	}
	/**
	 * @param reqStatusIsLast the reqStatusIsLast to set
	 */
	public void setReqStatusIsLast(String reqStatusIsLast) {
		this.reqStatusIsLast = reqStatusIsLast;
	}
	/**
	 * @return the reqStatusOrder
	 */
	public String getReqStatusOrder() {
		return reqStatusOrder;
	}
	/**
	 * @param reqStatusOrder the reqStatusOrder to set
	 */
	public void setReqStatusOrder(String reqStatusOrder) {
		this.reqStatusOrder = reqStatusOrder;
	}
	/**
	 * @return the reqStatusName
	 */
	public String getReqStatusName() {
		return reqStatusName;
	}
	/**
	 * @param reqStatusName the reqStatusName to set
	 */
	public void setReqStatusName(String reqStatusName) {
		this.reqStatusName = reqStatusName;
	}
	/**
	 * @return the myFile
	 */
	public File getMyFile() {
		return myFile;
	}
	/**
	 * @param myFile the myFile to set
	 */
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the slaListFlag
	 */
	public String getSlaListFlag() {
		return slaListFlag;
	}
	/**
	 * @param slaListFlag the slaListFlag to set
	 */
	public void setSlaListFlag(String slaListFlag) {
		this.slaListFlag = slaListFlag;
	}
	/**
	 * @return the statusCategory
	 */
	public String getStatusCategory() {
		return statusCategory;
	}
	/**
	 * @param statusCategory the statusCategory to set
	 */
	public void setStatusCategory(String statusCategory) {
		this.statusCategory = statusCategory;
	}
	/**
	 * @return the statusDuration
	 */
	public String getStatusDuration() {
		return statusDuration;
	}
	/**
	 * @param statusDuration the statusDuration to set
	 */
	public void setStatusDuration(String statusDuration) {
		this.statusDuration = statusDuration;
	}
	/**
	 * @return the slaList
	 */
	public ArrayList getSlaList() {
		return slaList;
	}
	/**
	 * @param slaList the slaList to set
	 */
	public void setSlaList(ArrayList slaList) {
		this.slaList = slaList;
	}
	/**
	 * @return the slaName
	 */
	public String getSlaName() {
		return slaName;
	}
	/**
	 * @param slaName the slaName to set
	 */
	public void setSlaName(String slaName) {
		this.slaName = slaName;
	}
	/**
	 * @return the reqDtlListFlag
	 */
	public String getReqDtlListFlag() {
		return reqDtlListFlag;
	}
	/**
	 * @param reqDtlListFlag the reqDtlListFlag to set
	 */
	public void setReqDtlListFlag(String reqDtlListFlag) {
		this.reqDtlListFlag = reqDtlListFlag;
	}
	/**
	 * @return the reqDtlList
	 */
	public ArrayList getReqDtlList() {
		return reqDtlList;
	}
	/**
	 * @param reqDtlList the reqDtlList to set
	 */
	public void setReqDtlList(ArrayList reqDtlList) {
		this.reqDtlList = reqDtlList;
	}
	/**
	 * @return the prevOwner
	 */
	public String getPrevOwner() {
		return prevOwner;
	}
	/**
	 * @param prevOwner the prevOwner to set
	 */
	public void setPrevOwner(String prevOwner) {
		this.prevOwner = prevOwner;
	}
	/**
	 * @return the newOwner
	 */
	public String getNewOwner() {
		return newOwner;
	}
	/**
	 * @param newOwner the newOwner to set
	 */
	public void setNewOwner(String newOwner) {
		this.newOwner = newOwner;
	}
	/**
	 * @return the chkStatus
	 */
	public String getChkStatus() {
		return chkStatus;
	}
	/**
	 * @param chkStatus the chkStatus to set
	 */
	public void setChkStatus(String chkStatus) {
		this.chkStatus = chkStatus;
	}
	/**
	 * @return the chkDate
	 */
	public String getChkDate() {
		return chkDate;
	}
	/**
	 * @param chkDate the chkDate to set
	 */
	public void setChkDate(String chkDate) {
		this.chkDate = chkDate;
	}
	/**
	 * @return the chkComments
	 */
	public String getChkComments() {
		return chkComments;
	}
	/**
	 * @param chkComments the chkComments to set
	 */
	public void setChkComments(String chkComments) {
		this.chkComments = chkComments;
	}
	/**
	 * @return the reopenCloseDesc
	 */
	public String getReopenCloseDesc() {
		return reopenCloseDesc;
	}
	/**
	 * @param reopenCloseDesc the reopenCloseDesc to set
	 */
	public void setReopenCloseDesc(String reopenCloseDesc) {
		this.reopenCloseDesc = reopenCloseDesc;
	}
	/**
	 * @return the reqSubject
	 */
	public String getReqSubject() {
		return reqSubject;
	}
	/**
	 * @param reqSubject the reqSubject to set
	 */
	public void setReqSubject(String reqSubject) {
		this.reqSubject = reqSubject;
	}
	/**
	 * @return the reqToken
	 */
	public String getReqToken() {
		return reqToken;
	}
	/**
	 * @param reqToken the reqToken to set
	 */
	public void setReqToken(String reqToken) {
		this.reqToken = reqToken;
	}
	/**
	 * @return the uploadDoc
	 */
	public String getUploadDoc() {
		return uploadDoc;
	}
	/**
	 * @param uploadDoc the uploadDoc to set
	 */
	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}
	/**
	 * @return the uploadFlag
	 */
	public String getUploadFlag() {
		return uploadFlag;
	}
	/**
	 * @param uploadFlag the uploadFlag to set
	 */
	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	/**
	 * @return the proofSrNo
	 */
	public String getProofSrNo() {
		return proofSrNo;
	}
	/**
	 * @param proofSrNo the proofSrNo to set
	 */
	public void setProofSrNo(String proofSrNo) {
		this.proofSrNo = proofSrNo;
	}
	/**
	 * @return the proofList
	 */
	public ArrayList getProofList() {
		return proofList;
	}
	/**
	 * @param proofList the proofList to set
	 */
	public void setProofList(ArrayList proofList) {
		this.proofList = proofList;
	}
	/**
	 * @return the proofName
	 */
	public String getProofName() {
		return proofName;
	}
	/**
	 * @param proofName the proofName to set
	 */
	public void setProofName(String proofName) {
		this.proofName = proofName;
	}
	/**
	 * @return the dataPath
	 */
	public String getDataPath() {
		return dataPath;
	}
	/**
	 * @param dataPath the dataPath to set
	 */
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	/**
	 * @return the checkRemoveUpload
	 */
	public String getCheckRemoveUpload() {
		return checkRemoveUpload;
	}
	/**
	 * @param checkRemoveUpload the checkRemoveUpload to set
	 */
	public void setCheckRemoveUpload(String checkRemoveUpload) {
		this.checkRemoveUpload = checkRemoveUpload;
	}
	/**
	 * @return the setFilesFlag
	 */
	public String getSetFilesFlag() {
		return setFilesFlag;
	}
	/**
	 * @param setFilesFlag the setFilesFlag to set
	 */
	public void setSetFilesFlag(String setFilesFlag) {
		this.setFilesFlag = setFilesFlag;
	}
	/**
	 * @return the pendingReqDept
	 */
	public String getPendingReqDept() {
		return pendingReqDept;
	}
	/**
	 * @param pendingReqDept the pendingReqDept to set
	 */
	public void setPendingReqDept(String pendingReqDept) {
		this.pendingReqDept = pendingReqDept;
	}
	/**
	 * @return the closedReqDept
	 */
	public String getClosedReqDept() {
		return closedReqDept;
	}
	/**
	 * @param closedReqDept the closedReqDept to set
	 */
	public void setClosedReqDept(String closedReqDept) {
		this.closedReqDept = closedReqDept;
	}
	/**
	 * @return the pendingReqCode
	 */
	public String getPendingReqCode() {
		return pendingReqCode;
	}
	/**
	 * @param pendingReqCode the pendingReqCode to set
	 */
	public void setPendingReqCode(String pendingReqCode) {
		this.pendingReqCode = pendingReqCode;
	}
	/**
	 * @return the closedReqCode
	 */
	public String getClosedReqCode() {
		return closedReqCode;
	}
	/**
	 * @param closedReqCode the closedReqCode to set
	 */
	public void setClosedReqCode(String closedReqCode) {
		this.closedReqCode = closedReqCode;
	}
	public String getRequestForEmpId() {
		return requestForEmpId;
	}
	public void setRequestForEmpId(String requestForEmpId) {
		this.requestForEmpId = requestForEmpId;
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
	public String getIsAssetRequest() {
		return isAssetRequest;
	}
	public void setIsAssetRequest(String isAssetRequest) {
		this.isAssetRequest = isAssetRequest;
	}
	public String getReqRadio() {
		return reqRadio;
	}
	public void setReqRadio(String reqRadio) {
		this.reqRadio = reqRadio;
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
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
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
	public String getRadioCheck() {
		return radioCheck;
	}
	public void setRadioCheck(String radioCheck) {
		this.radioCheck = radioCheck;
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
	public String getCheckReportingStructure() {
		return checkReportingStructure;
	}
	public void setCheckReportingStructure(String checkReportingStructure) {
		this.checkReportingStructure = checkReportingStructure;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isProofListFlag() {
		return proofListFlag;
	}
	public void setProofListFlag(boolean proofListFlag) {
		this.proofListFlag = proofListFlag;
	}
	public boolean isReopenFlag() {
		return reopenFlag;
	}
	public void setReopenFlag(boolean reopenFlag) {
		this.reopenFlag = reopenFlag;
	}
	public String getReopenComments() {
		return reopenComments;
	}
	public void setReopenComments(String reopenComments) {
		this.reopenComments = reopenComments;
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
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
}
