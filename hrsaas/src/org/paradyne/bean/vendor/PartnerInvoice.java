package org.paradyne.bean.vendor;
/**
 * @ author Archana Salunkhe
 * @ Purpose : Provide Partner Invoice Creation Functionality
 * @ Date : 27-Mar-2012
 */
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/*
 * Developed for Partner Invoice Creation
 */

public class PartnerInvoice extends BeanBase{
	
	private String uploadFileName ="";
	private String invoiceID ="";
	private String partnerName = "";
	private String partnerCode = "";
	private String invoiceDate = "";
	private String partnerType = "";
	private String creationFromDt = "";
	private String projectName = "";
	private String creationToDt = "";
	private String otherProject = "";
	private String goalAchivement = "";
	private String invoiceAmount = "";
	private String partnerComments = "";
	private String status = "";
	private String invoiceNumber ="";
	private String invoiceApproverID="";
	private String invoiceLevel ="";
	private String hiddenStatus ="";	
	/*List Variables*/
	private String invoicePartnerCode = "";
	private String invoicePartnerName = "";
	private String AppDate = "";	
	private String invoiceAppStatus="";
	private String listType = "";
	private String buttonFlag = "";
	private String backButtonFlg ="";
	private String backButtonApprovalFlg ="";
	
	/*Variables for Pending List*/
	private ArrayList pendingList;
	private String pendingPartnerCode ="";
	private String pendingInvoiceID ="";
	
	/*Variables for Draft List*/
	private ArrayList draftList;
	private String draftPartnerCode ="";
	private String draftInvoiceID="";
	
	/*Variables for Approved List*/
	private ArrayList approvedList;
	
	/*Variables for Rejected List*/
	private ArrayList rejectedList;
	
	/*Approver Comments*/
	private String approverCommentFlag ="";
    private String approverComment="";
    private ArrayList approverCommentList;
    private String  isApproverCommentList="";
    private String approverID ="";
    private String approverName="";
    private String approveDate="";
    private String approveStatus="";
    
    /*Check Status of Approve, Reject Send Back*/
    private String chkStatus ="";
    		
    /*For Pagination*/
    private String myPage="";
    private String draftLength="false";
    private String pendingPartnerLen="false";
    private String approvePartnerLen="false";
    private String rejectPartnerLen="false";
    
	public String getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}
	public String getCreationFromDt() {
		return creationFromDt;
	}
	public void setCreationFromDt(String creationFromDt) {
		this.creationFromDt = creationFromDt;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCreationToDt() {
		return creationToDt;
	}
	public void setCreationToDt(String creationToDt) {
		this.creationToDt = creationToDt;
	}
	public String getOtherProject() {
		return otherProject;
	}
	public void setOtherProject(String otherProject) {
		this.otherProject = otherProject;
	}
	public String getGoalAchivement() {
		return goalAchivement;
	}
	public void setGoalAchivement(String goalAchivement) {
		this.goalAchivement = goalAchivement;
	}
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getPartnerComments() {
		return partnerComments;
	}
	public void setPartnerComments(String partnerComments) {
		this.partnerComments = partnerComments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}	
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public ArrayList getPendingList() {
		return pendingList;
	}
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}	
	public String getPendingInvoiceID() {
		return pendingInvoiceID;
	}
	public void setPendingInvoiceID(String pendingInvoiceID) {
		this.pendingInvoiceID = pendingInvoiceID;
	}	
	public String getAppDate() {
		return AppDate;
	}
	public void setAppDate(String appDate) {
		AppDate = appDate;
	}
	public String getInvoiceAppStatus() {
		return invoiceAppStatus;
	}
	public void setInvoiceAppStatus(String invoiceAppStatus) {
		this.invoiceAppStatus = invoiceAppStatus;
	}
	public String getInvoicePartnerCode() {
		return invoicePartnerCode;
	}
	public void setInvoicePartnerCode(String invoicePartnerCode) {
		this.invoicePartnerCode = invoicePartnerCode;
	}
	public String getInvoicePartnerName() {
		return invoicePartnerName;
	}
	public void setInvoicePartnerName(String invoicePartnerName) {
		this.invoicePartnerName = invoicePartnerName;
	}
	public String getPendingPartnerCode() {
		return pendingPartnerCode;
	}
	public void setPendingPartnerCode(String pendingPartnerCode) {
		this.pendingPartnerCode = pendingPartnerCode;
	}
	public ArrayList getDraftList() {
		return draftList;
	}
	public void setDraftList(ArrayList draftList) {
		this.draftList = draftList;
	}
	public String getDraftPartnerCode() {
		return draftPartnerCode;
	}
	public void setDraftPartnerCode(String draftPartnerCode) {
		this.draftPartnerCode = draftPartnerCode;
	}
	public String getDraftInvoiceID() {
		return draftInvoiceID;
	}
	public void setDraftInvoiceID(String draftInvoiceID) {
		this.draftInvoiceID = draftInvoiceID;
	}	
	public String getInvoiceApproverID() {
		return invoiceApproverID;
	}
	public void setInvoiceApproverID(String invoiceApproverID) {
		this.invoiceApproverID = invoiceApproverID;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getButtonFlag() {
		return buttonFlag;
	}
	public void setButtonFlag(String buttonFlag) {
		this.buttonFlag = buttonFlag;
	}
	public String getApproverCommentFlag() {
		return approverCommentFlag;
	}
	public void setApproverCommentFlag(String approverCommentFlag) {
		this.approverCommentFlag = approverCommentFlag;
	}
	public String getApproverComment() {
		return approverComment;
	}
	public void setApproverComment(String approverComment) {
		this.approverComment = approverComment;
	}
	public ArrayList getApprovedList() {
		return approvedList;
	}
	public void setApprovedList(ArrayList approvedList) {
		this.approvedList = approvedList;
	}
	public ArrayList getRejectedList() {
		return rejectedList;
	}
	public void setRejectedList(ArrayList rejectedList) {
		this.rejectedList = rejectedList;
	}
	public String getInvoiceLevel() {
		return invoiceLevel;
	}
	public void setInvoiceLevel(String invoiceLevel) {
		this.invoiceLevel = invoiceLevel;
	}
	public String getChkStatus() {
		return chkStatus;
	}
	public void setChkStatus(String chkStatus) {
		this.chkStatus = chkStatus;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public String getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
	}
	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}
	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	public String getIsApproverCommentList() {
		return isApproverCommentList;
	}
	public void setIsApproverCommentList(String isApproverCommentList) {
		this.isApproverCommentList = isApproverCommentList;
	}
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public String getApproverID() {
		return approverID;
	}
	public void setApproverID(String approverID) {
		this.approverID = approverID;
	}
	public String getBackButtonFlg() {
		return backButtonFlg;
	}
	public void setBackButtonFlg(String backButtonFlg) {
		this.backButtonFlg = backButtonFlg;
	}
	public String getBackButtonApprovalFlg() {
		return backButtonApprovalFlg;
	}
	public void setBackButtonApprovalFlg(String backButtonApprovalFlg) {
		this.backButtonApprovalFlg = backButtonApprovalFlg;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getDraftLength() {
		return draftLength;
	}
	public void setDraftLength(String draftLength) {
		this.draftLength = draftLength;
	}
	public String getPendingPartnerLen() {
		return pendingPartnerLen;
	}
	public void setPendingPartnerLen(String pendingPartnerLen) {
		this.pendingPartnerLen = pendingPartnerLen;
	}
	public String getApprovePartnerLen() {
		return approvePartnerLen;
	}
	public void setApprovePartnerLen(String approvePartnerLen) {
		this.approvePartnerLen = approvePartnerLen;
	}
	public String getRejectPartnerLen() {
		return rejectPartnerLen;
	}
	public void setRejectPartnerLen(String rejectPartnerLen) {
		this.rejectPartnerLen = rejectPartnerLen;
	}	
}
