package org.paradyne.bean.voucher;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

public class VoucherApprovalMaster extends BeanBase {
	
	private String status;
	private String voucherNo;
	private String empName;
	private String date;
	private String totalAmt;
	private String checkStatus;
	private String check="false";
	private String vchRemark;
	private String empCode;
	private String level;
	private ArrayList list=null;
	private String app="false";
	private String pen="false";
	private String rej="false";
	private String hol="false";
	private String noData="false";
	private String apprflag="";
	private String listLength="0";
	private String pendingListLength="0"; 
	private String approveListLength="0"; 
	private String rejectListLength="0"; 
	private String tokenNo;
	private String statusNew;
	//Update by Anantha lakshmi
	
	private String chkfield = null;
	
	private String vchEdit = null;
	private String srNo = null;
	
	private String totalCheck = null;
	
	private String vproof = null;
	private String checkEdit = null;
	private String hproof = null;
	private String uploadFile = null;
	private String uploadFileName = null;
	private List lstApproverDetails = new ArrayList();
	private String ename = null;
	private String empToken="";
	private String type = null;
	private String department = null;
	private String designation = null;
	private String grade = null;
	private String vchDate = null;
	private String commentFlag="";
	private String tableLength = null;
	private String isApprove = "false";
	private String vchCode = null;
	
	private String hiddenStatus = null;
	private String VoucherHeadName = null;
	private String VoucherHead = null;
	private String amount = null;
	private String vchHeadCode = null;
	private String remarks = null;
	private String totalamount = null;
	private String details = null;
	private String vhead = null;
	private String vrem = null;
	private String vremark = null;
	private String vamt = null;
	private String vamount = null;
	
	
	private String vCode="";
	private String approverComments="";
	private ArrayList keepInformedList = null;
	private ArrayList approverList=null;
	private String serialNo="";			
	private String keepInformedEmpCode="";
	private String keepInformedEmpId="";
	private String keepInformedEmpName="";
	private String srNoIterator="";
	private String approverName="";
	//private String vchRemark="";
	private String Apprflag="";
	//private String approveLength;
	private String rejectLength;
	
	
	
	private String MyPage="";
	private String aApproveLength="";
	private ArrayList approvedList=null;
	private String listType="";
	private String approveLength="";
	private String listLengthPage="false";
	
	
	private ArrayList rejectedList=null;
	private String rejectedLength="";
	private String myPageRejected="";
	
	private ArrayList pendingList=null;
	private String pendingLength="";
	private String myPagePending="";
	
	public String getApproveLength() {
		return approveLength;
	}
	public void setApproveLength(String approveLength) {
		this.approveLength = approveLength;
	}
	public String getMyPage() {
		return MyPage;
	}
	public void setMyPage(String myPage) {
		MyPage = myPage;
	}
	public String getAApproveLength() {
		return aApproveLength;
	}
	public void setAApproveLength(String approveLength) {
		aApproveLength = approveLength;
	}
	
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getStatusNew() {
		return statusNew;
	}
	public void setStatusNew(String statusNew) {
		this.statusNew = statusNew;
	}
	public String getTokenNo() {
		return tokenNo;
	}
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getPen() {
		return pen;
	}
	public void setPen(String pen) {
		this.pen = pen;
	}
	public String getRej() {
		return rej;
	}
	public void setRej(String rej) {
		this.rej = rej;
	}
	public String getHol() {
		return hol;
	}
	public void setHol(String hol) {
		this.hol = hol;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	
	public String getVchRemark() {
		return vchRemark;
	}
	public void setVchRemark(String vchRemark) {
		this.vchRemark = vchRemark;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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
	public String getRejectedLength() {
		return rejectedLength;
	}
	public void setRejectedLength(String rejectedLength) {
		this.rejectedLength = rejectedLength;
	}
	public String getMyPageRejected() {
		return myPageRejected;
	}
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	public ArrayList getPendingList() {
		return pendingList;
	}
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}
	public String getPendingLength() {
		return pendingLength;
	}
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	public String getMyPagePending() {
		return myPagePending;
	}
	public void setMyPagePending(String myPagePending) {
		this.myPagePending = myPagePending;
	}
	public String getPendingListLength() {
		return pendingListLength;
	}
	public void setPendingListLength(String pendingListLength) {
		this.pendingListLength = pendingListLength;
	}
	public String getApproveListLength() {
		return approveListLength;
	}
	public void setApproveListLength(String approveListLength) {
		this.approveListLength = approveListLength;
	}
	public String getRejectListLength() {
		return rejectListLength;
	}
	public void setRejectListLength(String rejectListLength) {
		this.rejectListLength = rejectListLength;
	}
	public String getListLengthPage() {
		return listLengthPage;
	}
	public void setListLengthPage(String listLengthPage) {
		this.listLengthPage = listLengthPage;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}
	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}
	public ArrayList getApproverList() {
		return approverList;
	}
	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getKeepInformedEmpCode() {
		return keepInformedEmpCode;
	}
	public void setKeepInformedEmpCode(String keepInformedEmpCode) {
		this.keepInformedEmpCode = keepInformedEmpCode;
	}
	public String getKeepInformedEmpId() {
		return keepInformedEmpId;
	}
	public void setKeepInformedEmpId(String keepInformedEmpId) {
		this.keepInformedEmpId = keepInformedEmpId;
	}
	public String getKeepInformedEmpName() {
		return keepInformedEmpName;
	}
	public void setKeepInformedEmpName(String keepInformedEmpName) {
		this.keepInformedEmpName = keepInformedEmpName;
	}
	public String getSrNoIterator() {
		return srNoIterator;
	}
	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public String getRejectLength() {
		return rejectLength;
	}
	public void setRejectLength(String rejectLength) {
		this.rejectLength = rejectLength;
	}
	public String getVCode() {
		return vCode;
	}
	public void setVCode(String code) {
		vCode = code;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getVchDate() {
		return vchDate;
	}
	public void setVchDate(String vchDate) {
		this.vchDate = vchDate;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	public String getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}
	public String getVchCode() {
		return vchCode;
	}
	public void setVchCode(String vchCode) {
		this.vchCode = vchCode;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	public String getVoucherHeadName() {
		return VoucherHeadName;
	}
	public void setVoucherHeadName(String voucherHeadName) {
		VoucherHeadName = voucherHeadName;
	}
	public String getVoucherHead() {
		return VoucherHead;
	}
	public void setVoucherHead(String voucherHead) {
		VoucherHead = voucherHead;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getVchHeadCode() {
		return vchHeadCode;
	}
	public void setVchHeadCode(String vchHeadCode) {
		this.vchHeadCode = vchHeadCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getVhead() {
		return vhead;
	}
	public void setVhead(String vhead) {
		this.vhead = vhead;
	}
	public String getVrem() {
		return vrem;
	}
	public void setVrem(String vrem) {
		this.vrem = vrem;
	}
	public String getVremark() {
		return vremark;
	}
	public void setVremark(String vremark) {
		this.vremark = vremark;
	}
	public String getVamt() {
		return vamt;
	}
	public void setVamt(String vamt) {
		this.vamt = vamt;
	}
	public String getVamount() {
		return vamount;
	}
	public void setVamount(String vamount) {
		this.vamount = vamount;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	public String getChkfield() {
		return chkfield;
	}
	public void setChkfield(String chkfield) {
		this.chkfield = chkfield;
	}
	public String getVchEdit() {
		return vchEdit;
	}
	public void setVchEdit(String vchEdit) {
		this.vchEdit = vchEdit;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getTotalCheck() {
		return totalCheck;
	}
	public void setTotalCheck(String totalCheck) {
		this.totalCheck = totalCheck;
	}
	public String getVproof() {
		return vproof;
	}
	public void setVproof(String vproof) {
		this.vproof = vproof;
	}
	public String getCheckEdit() {
		return checkEdit;
	}
	public void setCheckEdit(String checkEdit) {
		this.checkEdit = checkEdit;
	}
	public String getHproof() {
		return hproof;
	}
	public void setHproof(String hproof) {
		this.hproof = hproof;
	}
	public String getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public List getLstApproverDetails() {
		return lstApproverDetails;
	}
	public void setLstApproverDetails(List lstApproverDetails) {
		this.lstApproverDetails = lstApproverDetails;
	}
	
	
	
}
