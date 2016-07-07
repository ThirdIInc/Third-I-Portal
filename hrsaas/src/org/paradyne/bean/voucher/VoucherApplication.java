package org.paradyne.bean.voucher;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class VoucherApplication extends BeanBase {
	
	private String source ="";
	
	private String apprNameIt="";
	private String srNoIt="";

	private String sSrNo = null;	
	private String selectedVCode = null;
	private String sListType = null;
	private ArrayList<VoucherApplication>lstDraft = new ArrayList<VoucherApplication>();
	private ArrayList <VoucherApplication> lstPending = new ArrayList<VoucherApplication>();
	private ArrayList <VoucherApplication> lstReturn = new ArrayList<VoucherApplication>();	
	private ArrayList <VoucherApplication>lstApproved = new ArrayList<VoucherApplication>();
	private ArrayList <VoucherApplication>lstRejected = new ArrayList<VoucherApplication>();
		
	/* Voucher Application Data */
	private String vCode = null;
	private String ename = null;
	private String voucherType = null;
	private String department = null;
	private String designation = null;
	private String grade = null;
	private String vchDate = null;
	private String voucherNo = null;

	private String tableLength = null;
	private String isApprove = "false";
	private String vchCode = null;
	private String status = null;
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
	private String chkfield = null;
	private String checkEdit = null;
	private String vchEdit = null;
	private String srNo = null;
	private String level = null;
	private String totalCheck = null;
	private String empToken = null;
	private String vproof = null;
	private String hproof = null;
	private String uploadFile = null;
	private String uploadFileName = null;
	
	private ArrayList <VoucherApplication> list = new ArrayList <VoucherApplication>();
	
	/* Approval Details */
	private String appSrNo = null;
	private String prevApproverID = null;
	private String prevApproverName = null;
	private String prevApproverDate = null;
	private String prevApproverStatus = null;
	private String prevApproverComment = null;
	private ArrayList<VoucherApplication> approverCommentList = new ArrayList<VoucherApplication>();
	private String sForward = null;
	
	//private String sShowButton = "false";
	//Updated By Anantha lakshmi
	
	private String approverComments="";
	private ArrayList <VoucherApplication> keepInformedList = null;
	private ArrayList <VoucherApplication> approverList=null;
	private String serialNo="";			
	private String keepInformedEmpCode="";
	private String keepInformedEmpId="";
	private String keepInformedEmpName="";
	private String srNoIterator="";
	private String approverName="";
	private String vchRemark="";
	private String Apprflag="true";
	private String approveLength;
	private String rejectLength;
	
	private String voucherDetailsFlag="";
	private String showFlag ="";
	private String kiEmpToken="";
	private String kiEmpName="";
	private String kiEmpCode ="";
	private String prevAppCommentFlag="";
	private String prevAppCommentListFlag="";
	private String voucherMgrFlg ="";
	private String voucherAdminFlg="";
	private String voucherAccFlg="";
	private String empId="";
	private String apprComments="";
	private String checkStatus="";
	private String editFlag1="";	
	private String checkRemove = "";
	
	//private String sAppCode = null;
	//private String sEmpId = null;
	//private String sEmpName = null;
	//private String sAppDate = null;
	public String getVchRemark() {
		return vchRemark;
	}
	public void setVchRemark(String vchRemark) {
		this.vchRemark = vchRemark;
	}
	public ArrayList<VoucherApplication> getLstDraft() {
		return lstDraft;
	}
	public void setLstDraft(ArrayList<VoucherApplication> lstDraft) {
		this.lstDraft = lstDraft;
	}
	public ArrayList<VoucherApplication> getLstPending() {
		return lstPending;
	}
	public void setLstPending(ArrayList<VoucherApplication> lstPending) {
		this.lstPending = lstPending;
	}
	public ArrayList<VoucherApplication> getLstReturn() {
		return lstReturn;
	}
	public void setLstReturn(ArrayList<VoucherApplication> lstReturn) {
		this.lstReturn = lstReturn;
	}
	public String getSSrNo() {
		return sSrNo;
	}

	public void setSSrNo(String srNo) {
		sSrNo = srNo;
	}

	//public String getSEmpId() {
	//	return sEmpId;
	//}

	//public void setSEmpId(String empId) {
	//	sEmpId = empId;
	//}

	//public String getSEmpName() {
	//	return sEmpName;
	//}

	//public void setSEmpName(String empName) {
	//	sEmpName = empName;
	//}

	//public String getSAppDate() {
	//	return sAppDate;
	//}

	//public void setSAppDate(String appDate) {
	//	sAppDate = appDate;
	//}

	public String getSListType() {
		return sListType;
	}

	public void setSListType(String listType) {
		sListType = listType;
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
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
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
	public String getChkfield() {
		return chkfield;
	}
	public void setChkfield(String chkfield) {
		this.chkfield = chkfield;
	}
	public String getCheckEdit() {
		return checkEdit;
	}
	public void setCheckEdit(String checkEdit) {
		this.checkEdit = checkEdit;
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTotalCheck() {
		return totalCheck;
	}
	public void setTotalCheck(String totalCheck) {
		this.totalCheck = totalCheck;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getVproof() {
		return vproof;
	}
	public void setVproof(String vproof) {
		this.vproof = vproof;
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
	public String getSelectedVCode() {
		return selectedVCode;
	}
	public void setSelectedVCode(String selectedVCode) {
		this.selectedVCode = selectedVCode;
	}
	public String getAppSrNo() {
		return appSrNo;
	}
	public void setAppSrNo(String appSrNo) {
		this.appSrNo = appSrNo;
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
	public String getSForward() {
		return sForward;
	}
	public void setSForward(String forward) {
		sForward = forward;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
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
	public ArrayList<VoucherApplication> getKeepInformedList() {
		return keepInformedList;
	}
	public void setKeepInformedList(ArrayList<VoucherApplication> keepInformedList) {
		this.keepInformedList = keepInformedList;
	}
	public ArrayList<VoucherApplication> getApproverList() {
		return approverList;
	}
	public void setApproverList(ArrayList<VoucherApplication> approverList) {
		this.approverList = approverList;
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
	public String getApprflag() {
		return Apprflag;
	}
	public void setApprflag(String apprflag) {
		Apprflag = apprflag;
	}
	public String getApproveLength() {
		return approveLength;
	}
	public void setApproveLength(String approveLength) {
		this.approveLength = approveLength;
	}
	public String getRejectLength() {
		return rejectLength;
	}
	public void setRejectLength(String rejectLength) {
		this.rejectLength = rejectLength;
	}
	public String getApprNameIt() {
		return apprNameIt;
	}
	public void setApprNameIt(String apprNameIt) {
		this.apprNameIt = apprNameIt;
	}
	public String getSrNoIt() {
		return srNoIt;
	}
	public void setSrNoIt(String srNoIt) {
		this.srNoIt = srNoIt;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getVoucherDetailsFlag() {
		return voucherDetailsFlag;
	}
	public void setVoucherDetailsFlag(String voucherDetailsFlag) {
		this.voucherDetailsFlag = voucherDetailsFlag;
	}
	public String getKiEmpToken() {
		return kiEmpToken;
	}
	public void setKiEmpToken(String kiEmpToken) {
		this.kiEmpToken = kiEmpToken;
	}
	public String getKiEmpName() {
		return kiEmpName;
	}
	public void setKiEmpName(String kiEmpName) {
		this.kiEmpName = kiEmpName;
	}
	public String getKiEmpCode() {
		return kiEmpCode;
	}
	public void setKiEmpCode(String kiEmpCode) {
		this.kiEmpCode = kiEmpCode;
	}
	public String getPrevAppCommentFlag() {
		return prevAppCommentFlag;
	}
	public void setPrevAppCommentFlag(String prevAppCommentFlag) {
		this.prevAppCommentFlag = prevAppCommentFlag;
	}
	public String getPrevAppCommentListFlag() {
		return prevAppCommentListFlag;
	}
	public void setPrevAppCommentListFlag(String prevAppCommentListFlag) {
		this.prevAppCommentListFlag = prevAppCommentListFlag;
	}
	public String getVoucherMgrFlg() {
		return voucherMgrFlg;
	}
	public void setVoucherMgrFlg(String voucherMgrFlg) {
		this.voucherMgrFlg = voucherMgrFlg;
	}
	public String getVoucherAdminFlg() {
		return voucherAdminFlg;
	}
	public void setVoucherAdminFlg(String voucherAdminFlg) {
		this.voucherAdminFlg = voucherAdminFlg;
	}
	public String getVoucherAccFlg() {
		return voucherAccFlg;
	}
	public void setVoucherAccFlg(String voucherAccFlg) {
		this.voucherAccFlg = voucherAccFlg;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public ArrayList<VoucherApplication> getLstApproved() {
		return lstApproved;
	}
	public void setLstApproved(ArrayList<VoucherApplication> lstApproved) {
		this.lstApproved = lstApproved;
	}
	public ArrayList<VoucherApplication> getLstRejected() {
		return lstRejected;
	}
	public void setLstRejected(ArrayList<VoucherApplication> lstRejected) {
		this.lstRejected = lstRejected;
	}
	public String getApprComments() {
		return apprComments;
	}
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public ArrayList<VoucherApplication> getList() {
		return list;
	}
	public void setList(ArrayList<VoucherApplication> list) {
		this.list = list;
	}
	public ArrayList<VoucherApplication> getApproverCommentList() {
		return approverCommentList;
	}
	public void setApproverCommentList(
			ArrayList<VoucherApplication> approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	public String getEditFlag1() {
		return editFlag1;
	}
	public void setEditFlag1(String editFlag1) {
		this.editFlag1 = editFlag1;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getCheckRemove() {
		return checkRemove;
	}
	public void setCheckRemove(String checkRemove) {
		this.checkRemove = checkRemove;
	}
}

