package org.paradyne.bean.voucher;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CashVoucherMaster extends BeanBase {

	 private String source ="";
	private String showFlag="true";
	private String vCode="";
	private String ename="";
	private String empCode="";
	private String type="";
	private String department="";
	private String designation="";
	private String grade="";
	private String vchDate="";
	private String voucherNo;
	private String  checkStatus="";
	private String tableLength="";
	private String isApprove="false";
	private String vchCode="";
	private String status=""; 
	private String hiddenStatus="";
	private String VoucherHeadName="";
	private String VoucherHead="";
	private String amount="";
	private String vchHeadCode=""; 
	private String remarks="";
	private String totalamount="";
	private String details="";
	private String vhead="";
	private String vrem="";
	private String vremark="";
	private String vamt="";
	private String vamount="";
	private String chkfield ="";
	private String checkEdit="";
	private String vchEdit="";
	private String srNo;
	private int AppLevel;
	private String level;
	private String totalCheck="";
	private String empToken="";
	private String vproof;
	private String hproof="";
	private String uploadFile;
	private String uploadFileName;
	private String vchRemark;
	private ArrayList list=null;
	private ArrayList approveList=null;
	private String apprName;
	private String apprDate;
	private String apprComments;
	private String appStatus;
	private String commentFlag= "false";
	private String approverCode;
//updated By Anantha lakshmi
	private ArrayList sendBackList;
	private String kiApprovalRemoveFlag="";
	private String viewAppFlag="";
	private String sendfrAppFlag="";
	private String kiEmpName="";
	private String kiEmpCode="";
	private String kiEmpToken="";
	private String voucherDetailsFlag="";
	private String listType = "";
	private String editFlag1="";
	private String voucherTypeFlag="";
	
	private String voucherMgrFlag="";
	private String voucherAdminFlag="";
	private String voucherAcctFlag="";
	private String voucherAdmin ="";
	private String voucherAcct="";
	
	
	
	public String getEditFlag1() {
		return editFlag1;
	}

	public void setEditFlag1(String editFlag1) {
		this.editFlag1 = editFlag1;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}
	
	public String getApproverCode() {
		return approverCode;
	}
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	public String getApprName() {
		return apprName;
	}
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public String getApprComments() {
		return apprComments;
	}
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}
	public String getTotalCheck() {
		return totalCheck;
	}
	public void setTotalCheck(String totalCheck) {
		this.totalCheck = totalCheck;
	}
	public String getVCode() {
		return vCode;
	}
	public String getVchDate() {
		return vchDate;
	}
	public void setVchDate(String vchDate) {
		this.vchDate = vchDate;
	}
	public void setVCode(String vCode) {
		this.vCode = vCode;
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
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	

	public String getVoucherHead() {
		return VoucherHead;
	}
	public void setVoucherHead(String voucherHead) {
		this.VoucherHead = voucherHead;
	}
	
	public String getVchCode() {
		return vchCode;
	}
	public void setVchCode(String vchCode) {
		this.vchCode = vchCode;
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
	public String getVamt() {
		return vamt;
	}
	public void setVamt(String vamt) {
		this.vamt = vamt;
	}


	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
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
	public String getVamount() {
		return vamount;
	}
	public void setVamount(String vamount) {
		this.vamount = vamount;
	}
	public String getVoucherHeadName() {
		return VoucherHeadName;
	}
	public void setVoucherHeadName(String voucherHeadName) {
		VoucherHeadName = voucherHeadName;
	}
	public String getVremark() {
		return vremark;
	}
	public void setVremark(String vremark) {
		this.vremark = vremark;
	}
	public String getVchHeadCode() {
		return vchHeadCode;
	}
	public void setVchHeadCode(String vchHeadCode) {
		this.vchHeadCode = vchHeadCode;
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
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	public String getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
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
	public void setVproof(String proof) {
		this.vproof = proof;
	}
	public String getHproof() {
		return hproof;
	}
	public void setHproof(String hproof) {
		this.hproof = hproof;
	}
	public ArrayList getApproveList() {
		return approveList;
	}
	public void setApproveList(ArrayList approveList) {
		this.approveList = approveList;
	}
	public String getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
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
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	
	///////////////////////////////


	private String leaveCode = "";
	
	private String tokenNo = "";
	private String empName = "";
	private String center = "";

	private String applicationDate = "";
	private String levCode = "";
	private String leaveId = "";
	private String hdlevType = "";
	private String levType = "";
	private String levOpeningBalance = "";
	private String leaveFromDtl = "";
	private String leaveToDtl = "";
	private String levClosingBalance = "";
	private String leaveTotalDays = "";
	private String prefix = "";
	private String suffix = "";
	private String comments = "";
	private String relieverName = "";
	private String reliever = "";
	
	private String forwardName = "";
	private String forwardId = "";
	private String forwardFor = "";
	private String isOfficer = "";
	private String hiddenDate = "";
	private String zeroBalance = "";
	private String advanceAppl = "";
	private String paracode = "";

	private String contactDetails = "";
	private String slevCode = "";
	private String slevType;
	private String sleaveFromDtl = "";
	private String sleaveToDtl = "";
	private String slevClosingBalance = "";
	private String levTotal = "";

	
	private ArrayList att = null;
	private String availBalance = "";
	private String closeBalance = "";
	
	private String slevLeaveDays = "";
	private String medicalCert = "";
	private String halfDayCheck = "";
	private String approvalFlag = "true";
	private String isAppName = "true";
	private String isLeaveApp = "true";
	private String isAddFlag = "false";
	private String data = "false";
	private String brnHDayFlag = "false";
	private String detailFlag = "false";
	private String avaibal = "false";
	private String leavestatus = "";
	private String chkEdit = "";
	private String onhold = "";
	private String onholdhidden = "";
	private String isEditFlag = "false";
	private String oldLeaveDays = "";
	private String editFlag = "true";
	private String isButtonVisible = "true";
	private String saveDetailFlag = "false";
	private String isApprovalClick = "false";
	private String checkMe = "";
	private String removeFlag = "true";

	private String approverDetails = "false";

	private String isAdminApprovalClick = "false";

	



	// FILDS FOR HALF KEEP INFORMED TO
	private String employeeName = "";
	private String employeeId = "";
	private String employeeToken = "";
	private ArrayList keepInformedList = null;
	private String serialNoIt = "";
	private String keepInformedEmpCodeIt = "";
	private String keepInformedEmpTokenIt = "";
	private String keepInformedEmpNameIt = "";
	
	
	
	private String serialNo= "";
	private String keepInformedEmpCode = "";
	private String keepInformedEmpId = "";
	private String keepInformedEmpName = "";
	
	
	private String checkRemove = "";

	
	// FILDS FOR APPROVER LIST
	private ArrayList approverList;
	private String approverName = "";
	private String srNoIterator = "";

	// VARIABLES FOR DRAFT LIST
	private ArrayList draftList;
	private String draftEmpId = "";
	private String draftLeaveAppNo = "";

	// VARIABLES FOR SUBMIT LIST
	private ArrayList submitList;
	private String submitEmpId = "";
	private String submitLeaveAppNo = "";

	// VARIABLES FOR RETURN LIST
	private ArrayList returnList;
	private String returnEmpId = "";
	private String returnLeaveAppNo = "";

	// VARIABLES FOR APPROVED LIST approved

	private ArrayList approvedList;
	private String approvedEmpId = "";
	private String approvedLeaveAppNo = "";

	
	// VARIABLES FOR REJECTED LIST rejected

	private ArrayList rejectedList;
	private String rejectedEmpId = "";
	private String rejectedLeaveAppNo = "";
	

	//variables for paging
	
	private String totalRecords="";
 
	
	//variables for paging
	private String myPageApproved="";
	private String myPageRejected="";
	private String myPageCancelled="";
	private String uploadProofFlag="false";
	 
	private String isProofRequired="";
	
	

	//FIELDS FOR UPLOAD PROOF
	private String proofSrNo="";
	private ArrayList proofList;
	private String proofName="";
	
	private String dataPath="";
	private String checkRemoveUpload="";
	private String proofFileName="";
	private String userUploadFileName="";
	private String uploadDoc ="";
	private String uploadDocPath="";
	
	private String checkApproveRejectStatus=""; 
	 
	private String draftFlag="true";
	private String commonBtnFlag="true";
	private String deleteBtnFlag="false";
	private String sendAppBtnFlag="false";
	private String cancelBtnFlag="false";
	private String reportBtnFlag="false";
	private String searchResetFlag="false";
	private String isLeaveTypeApp="true";
	 private String approverComments="";
	 
	 
	 
	 private ArrayList approverCommentList;
	 private String appSrNo="";
	 private String prevApproverName="";
	 private String prevApproverDate="";
	 private String prevApproverComment="";
	 private String prevApproverID="";
	 private String  prevAppCommentFlag="false";
	 private String prevAppCommentListFlag="";
	 private String iteratorPenaltyDays="0.0";
	 private String iteratorAdjustPenaltyDays="0.0";
	 private String hiddenAdjustPenaltyDays="0.0";
	 
	 private String hiddenPenaltyDays="0.0";
	 private String hiddenUnAdjustPenaltyDays="0.0";
	 private String iteratorUnAdjustPenaltyDays="0.0";
	 
	private String appRejSendBackFlag="false";
	
	private String myPage="";
	private String prevApproverStatus="";
	
	private String approveAppCanFlag="false";
	
	private String checkApproveCancelStatus="";
	
	 
	
	private String myPageCancelRejected="";
	private String myPageCancelApproved="";
	
	private String approveLength="false";
	
	private String approveCancelLength="false";
	
	private String cancelledLength="false";
	
	private String rejectedLength="false";
	
	private String rejectedCancelLength="false";
	
	private String uploadProofViewFlag="false";
	
	private String iteratorProof="";
	
	private  ArrayList ittUploadList;
	
	private String isPenaltyFlag="false";
	
	private String oldPenaltyAdjDays="";
	
	private String deliveryDate="";
	
	private String expectedDeliveryDate="";
	
	private String isDeliveryDateShow="false";

	private String isPoolDefine="false";
	private String adjustedPoolLevAmt="";
	
	
	
	
	
	

	
	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOldPenaltyAdjDays() {
		return oldPenaltyAdjDays;
	}

	public void setOldPenaltyAdjDays(String oldPenaltyAdjDays) {
		this.oldPenaltyAdjDays = oldPenaltyAdjDays;
	}

	public String getIsPenaltyFlag() {
		return isPenaltyFlag;
	}

	public void setIsPenaltyFlag(String isPenaltyFlag) {
		this.isPenaltyFlag = isPenaltyFlag;
	}

	public ArrayList getIttUploadList() {
		return ittUploadList;
	}

	public void setIttUploadList(ArrayList ittUploadList) {
		this.ittUploadList = ittUploadList;
	}

	public String getIteratorProof() {
		return iteratorProof;
	}

	public void setIteratorProof(String iteratorProof) {
		this.iteratorProof = iteratorProof;
	}

	public String getRejectedCancelLength() {
		return rejectedCancelLength;
	}

	public void setRejectedCancelLength(String rejectedCancelLength) {
		this.rejectedCancelLength = rejectedCancelLength;
	}

	public String getRejectedLength() {
		return rejectedLength;
	}

	public void setRejectedLength(String rejectedLength) {
		this.rejectedLength = rejectedLength;
	}

	public String getApproveCancelLength() {
		return approveCancelLength;
	}

	public void setApproveCancelLength(String approveCancelLength) {
		this.approveCancelLength = approveCancelLength;
	}

	public String getApproveLength() {
		return approveLength;
	}

	public void setApproveLength(String approveLength) {
		this.approveLength = approveLength;
	}

	public String getMyPageCancelRejected() {
		return myPageCancelRejected;
	}

	public void setMyPageCancelRejected(String myPageCancelRejected) {
		this.myPageCancelRejected = myPageCancelRejected;
	}

	public String getMyPageCancelApproved() {
		return myPageCancelApproved;
	}

	public void setMyPageCancelApproved(String myPageCancelApproved) {
		this.myPageCancelApproved = myPageCancelApproved;
	}

	 

	public String getCheckApproveCancelStatus() {
		return checkApproveCancelStatus;
	}

	public void setCheckApproveCancelStatus(String checkApproveCancelStatus) {
		this.checkApproveCancelStatus = checkApproveCancelStatus;
	}

	public String getApproveAppCanFlag() {
		return approveAppCanFlag;
	}

	public void setApproveAppCanFlag(String approveAppCanFlag) {
		this.approveAppCanFlag = approveAppCanFlag;
	}

	public String getPrevApproverStatus() {
		return prevApproverStatus;
	}

	public void setPrevApproverStatus(String prevApproverStatus) {
		this.prevApproverStatus = prevApproverStatus;
	}

	public String getAppRejSendBackFlag() {
		return appRejSendBackFlag;
	}

	public void setAppRejSendBackFlag(String appRejSendBackFlag) {
		this.appRejSendBackFlag = appRejSendBackFlag;
	}

	public String getHiddenPenaltyDays() {
		return hiddenPenaltyDays;
	}

	public void setHiddenPenaltyDays(String hiddenPenaltyDays) {
		this.hiddenPenaltyDays = hiddenPenaltyDays;
	}

	public String getPrevAppCommentListFlag() {
		return prevAppCommentListFlag;
	}

	public void setPrevAppCommentListFlag(String prevAppCommentListFlag) {
		this.prevAppCommentListFlag = prevAppCommentListFlag;
	}

	public String getPrevAppCommentFlag() {
		return prevAppCommentFlag;
	}

	public void setPrevAppCommentFlag(String prevAppCommentFlag) {
		this.prevAppCommentFlag = prevAppCommentFlag;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public String getReportBtnFlag() {
		return reportBtnFlag;
	}

	public void setReportBtnFlag(String reportBtnFlag) {
		this.reportBtnFlag = reportBtnFlag;
	}

	public String getCheckApproveRejectStatus() {
		return checkApproveRejectStatus;
	}

	public void setCheckApproveRejectStatus(String checkApproveRejectStatus) {
		this.checkApproveRejectStatus = checkApproveRejectStatus;
	}

	public String getUploadDoc() {
		return uploadDoc;
	}

	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}

	public String getUploadDocPath() {
		return uploadDocPath;
	}

	public void setUploadDocPath(String uploadDocPath) {
		this.uploadDocPath = uploadDocPath;
	}

	public String getUserUploadFileName() {
		return userUploadFileName;
	}

	public void setUserUploadFileName(String userUploadFileName) {
		this.userUploadFileName = userUploadFileName;
	}

	public String getProofFileName() {
		return proofFileName;
	}

	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
	}

	public String getCheckRemoveUpload() {
		return checkRemoveUpload;
	}

	public void setCheckRemoveUpload(String checkRemoveUpload) {
		this.checkRemoveUpload = checkRemoveUpload;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getIsProofRequired() {
		return isProofRequired;
	}

	public void setIsProofRequired(String isProofRequired) {
		this.isProofRequired = isProofRequired;
	}

	 

	public String getMyPageCancelled() {
		return myPageCancelled;
	}

	public void setMyPageCancelled(String myPageCancelled) {
		this.myPageCancelled = myPageCancelled;
	}

	public String getMyPageRejected() {
		return myPageRejected;
	}

	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}

	public String getMyPageApproved() {
		return myPageApproved;
	}

	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}

	

	public ArrayList getApprovedList() {
		return approvedList;
	}

	public void setApprovedList(ArrayList approvedList) {
		this.approvedList = approvedList;
	}

	public String getApprovedEmpId() {
		return approvedEmpId;
	}

	public void setApprovedEmpId(String approvedEmpId) {
		this.approvedEmpId = approvedEmpId;
	}

	public String getApprovedLeaveAppNo() {
		return approvedLeaveAppNo;
	}

	public void setApprovedLeaveAppNo(String approvedLeaveAppNo) {
		this.approvedLeaveAppNo = approvedLeaveAppNo;
	}

	public ArrayList getReturnList() {
		return returnList;
	}

	public void setReturnList(ArrayList returnList) {
		this.returnList = returnList;
	}

	public String getReturnEmpId() {
		return returnEmpId;
	}

	public void setReturnEmpId(String returnEmpId) {
		this.returnEmpId = returnEmpId;
	}

	public String getReturnLeaveAppNo() {
		return returnLeaveAppNo;
	}

	public void setReturnLeaveAppNo(String returnLeaveAppNo) {
		this.returnLeaveAppNo = returnLeaveAppNo;
	}

	public String getDraftEmpId() {
		return draftEmpId;
	}

	public void setDraftEmpId(String draftEmpId) {
		this.draftEmpId = draftEmpId;
	}

	public String getDraftLeaveAppNo() {
		return draftLeaveAppNo;
	}

	public void setDraftLeaveAppNo(String draftLeaveAppNo) {
		this.draftLeaveAppNo = draftLeaveAppNo;
	}

	

	public String getCheckRemove() {
		return checkRemove;
	}

	public void setCheckRemove(String checkRemove) {
		this.checkRemove = checkRemove;
	}

	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}

	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}



	public String getIsAppName() {
		return isAppName;
	}

	public void setIsAppName(String isAppName) {
		this.isAppName = isAppName;
	}

	public String getApprovalFlag() {
		return approvalFlag;
	}

	public void setApprovalFlag(String approvalFlag) {
		this.approvalFlag = approvalFlag;
	}

	public String getHalfDayCheck() {
		return halfDayCheck;
	}

	public void setHalfDayCheck(String halfDayCheck) {
		this.halfDayCheck = halfDayCheck;
	}

	public String getMedicalCert() {
		return medicalCert;
	}

	public void setMedicalCert(String medicalCert) {
		this.medicalCert = medicalCert;
	}

	public String getAvailBalance() {
		return availBalance;
	}

	public void setAvailBalance(String availBalance) {
		this.availBalance = availBalance;
	}

	public ArrayList getAtt() {
		return att;
	}

	public void setAtt(ArrayList att) {
		this.att = att;
	}

	public String getSlevCode() {
		return slevCode;
	}

	public void setSlevCode(String slevCode) {
		this.slevCode = slevCode;
	}

	public String getSleaveFromDtl() {
		return sleaveFromDtl;
	}

	public void setSleaveFromDtl(String sleaveFromDtl) {
		this.sleaveFromDtl = sleaveFromDtl;
	}

	public String getSleaveToDtl() {
		return sleaveToDtl;
	}

	public void setSleaveToDtl(String sleaveToDtl) {
		this.sleaveToDtl = sleaveToDtl;
	}

	public String getSlevClosingBalance() {
		return slevClosingBalance;
	}

	public void setSlevClosingBalance(String slevClosingBalance) {
		this.slevClosingBalance = slevClosingBalance;
	}

	public String getParacode() {
		return paracode;
	}

	public void setParacode(String paracode) {
		this.paracode = paracode;
	}

	public String getZeroBalance() {
		return zeroBalance;
	}

	public void setZeroBalance(String zeroBalance) {
		this.zeroBalance = zeroBalance;
	}

	public String getAdvanceAppl() {
		return advanceAppl;
	}

	public void setAdvanceAppl(String advanceAppl) {
		this.advanceAppl = advanceAppl;
	}

	public String getHiddenDate() {
		return hiddenDate;
	}

	public void setHiddenDate(String hiddenDate) {
		this.hiddenDate = hiddenDate;
	}

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getLevCode() {
		return levCode;
	}

	public void setLevCode(String levCode) {
		this.levCode = levCode;
	}

	public String getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}

	public String getHdlevType() {
		return hdlevType;
	}

	public void setHdlevType(String hdlevType) {
		this.hdlevType = hdlevType;
	}

	public String getLevType() {
		return levType;
	}

	public void setLevType(String levType) {
		this.levType = levType;
	}

	public String getLevOpeningBalance() {
		return levOpeningBalance;
	}

	public void setLevOpeningBalance(String levOpeningBalance) {
		this.levOpeningBalance = levOpeningBalance;
	}

	public String getLeaveFromDtl() {
		return leaveFromDtl;
	}

	public void setLeaveFromDtl(String leaveFromDtl) {
		this.leaveFromDtl = leaveFromDtl;
	}

	public String getLeaveToDtl() {
		return leaveToDtl;
	}

	public void setLeaveToDtl(String leaveToDtl) {
		this.leaveToDtl = leaveToDtl;
	}

	public String getLevClosingBalance() {
		return levClosingBalance;
	}

	public void setLevClosingBalance(String levClosingBalance) {
		this.levClosingBalance = levClosingBalance;
	}

	public String getLeaveTotalDays() {
		return leaveTotalDays;
	}

	public void setLeaveTotalDays(String leaveTotalDays) {
		this.leaveTotalDays = leaveTotalDays;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRelieverName() {
		return relieverName;
	}

	public void setRelieverName(String relieverName) {
		this.relieverName = relieverName;
	}

	public String getReliever() {
		return reliever;
	}

	public void setReliever(String reliever) {
		this.reliever = reliever;
	}

	

	public String getForwardName() {
		return forwardName;
	}

	public void setForwardName(String forwardName) {
		this.forwardName = forwardName;
	}

	public String getForwardId() {
		return forwardId;
	}

	public void setForwardId(String forwardId) {
		this.forwardId = forwardId;
	}

	public String getForwardFor() {
		return forwardFor;
	}

	public void setForwardFor(String forwardFor) {
		this.forwardFor = forwardFor;
	}

	

	public String getIsOfficer() {
		return isOfficer;
	}

	public void setIsOfficer(String isOfficer) {
		this.isOfficer = isOfficer;
	}

	public String getSlevType() {
		return slevType;
	}

	public void setSlevType(String slevType) {
		this.slevType = slevType;
	}

	public String getLevTotal() {
		return levTotal;
	}

	public void setLevTotal(String levTotal) {
		this.levTotal = levTotal;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	
	public String getCloseBalance() {
		return closeBalance;
	}

	public void setCloseBalance(String closeBalance) {
		this.closeBalance = closeBalance;
	}

	public String getSlevLeaveDays() {
		return slevLeaveDays;
	}

	public void setSlevLeaveDays(String slevLeaveDays) {
		this.slevLeaveDays = slevLeaveDays;
	}

	

	public String getIsLeaveApp() {
		return isLeaveApp;
	}

	public void setIsLeaveApp(String isLeaveApp) {
		this.isLeaveApp = isLeaveApp;
	}

	
	public String getIsAddFlag() {
		return isAddFlag;
	}

	public void setIsAddFlag(String isAddFlag) {
		this.isAddFlag = isAddFlag;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getBrnHDayFlag() {
		return brnHDayFlag;
	}

	public void setBrnHDayFlag(String brnHDayFlag) {
		this.brnHDayFlag = brnHDayFlag;
	}

	public String getDetailFlag() {
		return detailFlag;
	}

	public void setDetailFlag(String detailFlag) {
		this.detailFlag = detailFlag;
	}

	public String getAvaibal() {
		return avaibal;
	}

	public void setAvaibal(String avaibal) {
		this.avaibal = avaibal;
	}

	public String getLeavestatus() {
		return leavestatus;
	}

	public void setLeavestatus(String leavestatus) {
		this.leavestatus = leavestatus;
	}

	public String getOnhold() {
		return onhold;
	}

	public void setOnhold(String onhold) {
		this.onhold = onhold;
	}

	public String getOnholdhidden() {
		return onholdhidden;
	}

	public void setOnholdhidden(String onholdhidden) {
		this.onholdhidden = onholdhidden;
	}

	public String getChkEdit() {
		return chkEdit;
	}

	public void setChkEdit(String chkEdit) {
		this.chkEdit = chkEdit;
	}

	public String getIsEditFlag() {
		return isEditFlag;
	}

	public void setIsEditFlag(String isEditFlag) {
		this.isEditFlag = isEditFlag;
	}

	public String getOldLeaveDays() {
		return oldLeaveDays;
	}

	public void setOldLeaveDays(String oldLeaveDays) {
		this.oldLeaveDays = oldLeaveDays;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getIsButtonVisible() {
		return isButtonVisible;
	}

	public void setIsButtonVisible(String isButtonVisible) {
		this.isButtonVisible = isButtonVisible;
	}

	public String getSaveDetailFlag() {
		return saveDetailFlag;
	}

	public void setSaveDetailFlag(String saveDetailFlag) {
		this.saveDetailFlag = saveDetailFlag;
	}

	public String getIsApprovalClick() {
		return isApprovalClick;
	}

	public void setIsApprovalClick(String isApprovalClick) {
		this.isApprovalClick = isApprovalClick;
	}

	public String getCheckMe() {
		return checkMe;
	}

	public void setCheckMe(String checkMe) {
		this.checkMe = checkMe;
	}

	public String getRemoveFlag() {
		return removeFlag;
	}

	public void setRemoveFlag(String removeFlag) {
		this.removeFlag = removeFlag;
	}



	public String getApproverDetails() {
		return approverDetails;
	}

	public void setApproverDetails(String approverDetails) {
		this.approverDetails = approverDetails;
	}

	
	


	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	

	public ArrayList getApproverList() {
		return approverList;
	}

	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getSrNoIterator() {
		return srNoIterator;
	}

	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
	}

	public ArrayList getDraftList() {
		return draftList;
	}

	public void setDraftList(ArrayList draftList) {
		this.draftList = draftList;
	}

	public ArrayList getSubmitList() {
		return submitList;
	}

	public void setSubmitList(ArrayList submitList) {
		this.submitList = submitList;
	}

	public String getSubmitEmpId() {
		return submitEmpId;
	}

	public void setSubmitEmpId(String submitEmpId) {
		this.submitEmpId = submitEmpId;
	}

	public String getSubmitLeaveAppNo() {
		return submitLeaveAppNo;
	}

	public void setSubmitLeaveAppNo(String submitLeaveAppNo) {
		this.submitLeaveAppNo = submitLeaveAppNo;
	}



	

	public ArrayList getRejectedList() {
		return rejectedList;
	}

	public void setRejectedList(ArrayList rejectedList) {
		this.rejectedList = rejectedList;
	}

	public String getRejectedEmpId() {
		return rejectedEmpId;
	}

	public void setRejectedEmpId(String rejectedEmpId) {
		this.rejectedEmpId = rejectedEmpId;
	}

	public String getRejectedLeaveAppNo() {
		return rejectedLeaveAppNo;
	}

	public void setRejectedLeaveAppNo(String rejectedLeaveAppNo) {
		this.rejectedLeaveAppNo = rejectedLeaveAppNo;
	}

	
	public String getUploadProofFlag() {
		return uploadProofFlag;
	}

	public void setUploadProofFlag(String uploadProofFlag) {
		this.uploadProofFlag = uploadProofFlag;
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

	public String getDraftFlag() {
		return draftFlag;
	}

	public void setDraftFlag(String draftFlag) {
		this.draftFlag = draftFlag;
	}

	 

	public String getCommonBtnFlag() {
		return commonBtnFlag;
	}

	public void setCommonBtnFlag(String commonBtnFlag) {
		this.commonBtnFlag = commonBtnFlag;
	}

	public String getDeleteBtnFlag() {
		return deleteBtnFlag;
	}

	public void setDeleteBtnFlag(String deleteBtnFlag) {
		this.deleteBtnFlag = deleteBtnFlag;
	}

	public String getSendAppBtnFlag() {
		return sendAppBtnFlag;
	}

	public void setSendAppBtnFlag(String sendAppBtnFlag) {
		this.sendAppBtnFlag = sendAppBtnFlag;
	}

	public String getCancelBtnFlag() {
		return cancelBtnFlag;
	}

	public void setCancelBtnFlag(String cancelBtnFlag) {
		this.cancelBtnFlag = cancelBtnFlag;
	}

	public String getSearchResetFlag() {
		return searchResetFlag;
	}

	public void setSearchResetFlag(String searchResetFlag) {
		this.searchResetFlag = searchResetFlag;
	}

	public String getIsLeaveTypeApp() {
		return isLeaveTypeApp;
	}

	public void setIsLeaveTypeApp(String isLeaveTypeApp) {
		this.isLeaveTypeApp = isLeaveTypeApp;
	}

	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}

	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}

	public String getAppSrNo() {
		return appSrNo;
	}

	public void setAppSrNo(String appSrNo) {
		this.appSrNo = appSrNo;
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

	public String getPrevApproverComment() {
		return prevApproverComment;
	}

	public void setPrevApproverComment(String prevApproverComment) {
		this.prevApproverComment = prevApproverComment;
	}

	public String getPrevApproverID() {
		return prevApproverID;
	}

	public void setPrevApproverID(String prevApproverID) {
		this.prevApproverID = prevApproverID;
	}

	public String getIteratorPenaltyDays() {
		return iteratorPenaltyDays;
	}

	public void setIteratorPenaltyDays(String iteratorPenaltyDays) {
		this.iteratorPenaltyDays = iteratorPenaltyDays;
	}

	public String getIteratorAdjustPenaltyDays() {
		return iteratorAdjustPenaltyDays;
	}

	public void setIteratorAdjustPenaltyDays(String iteratorAdjustPenaltyDays) {
		this.iteratorAdjustPenaltyDays = iteratorAdjustPenaltyDays;
	}

	public String getHiddenAdjustPenaltyDays() {
		return hiddenAdjustPenaltyDays;
	}

	public void setHiddenAdjustPenaltyDays(String hiddenAdjustPenaltyDays) {
		this.hiddenAdjustPenaltyDays = hiddenAdjustPenaltyDays;
	}

	public String getHiddenUnAdjustPenaltyDays() {
		return hiddenUnAdjustPenaltyDays;
	}

	public void setHiddenUnAdjustPenaltyDays(String hiddenUnAdjustPenaltyDays) {
		this.hiddenUnAdjustPenaltyDays = hiddenUnAdjustPenaltyDays;
	}

	public String getIteratorUnAdjustPenaltyDays() {
		return iteratorUnAdjustPenaltyDays;
	}

	public void setIteratorUnAdjustPenaltyDays(String iteratorUnAdjustPenaltyDays) {
		this.iteratorUnAdjustPenaltyDays = iteratorUnAdjustPenaltyDays;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	


	public String getCancelledLength() {
		return cancelledLength;
	}

	public void setCancelledLength(String cancelledLength) {
		this.cancelledLength = cancelledLength;
	}

	 

	public String getUploadProofViewFlag() {
		return uploadProofViewFlag;
	}

	public void setUploadProofViewFlag(String uploadProofViewFlag) {
		this.uploadProofViewFlag = uploadProofViewFlag;
	}

	public String getIsDeliveryDateShow() {
		return isDeliveryDateShow;
	}

	public void setIsDeliveryDateShow(String isDeliveryDateShow) {
		this.isDeliveryDateShow = isDeliveryDateShow;
	}



	public String getIsPoolDefine() {
		return isPoolDefine;
	}

	public void setIsPoolDefine(String isPoolDefine) {
		this.isPoolDefine = isPoolDefine;
	}

	public String getAdjustedPoolLevAmt() {
		return adjustedPoolLevAmt;
	}

	public void setAdjustedPoolLevAmt(String adjustedPoolLevAmt) {
		this.adjustedPoolLevAmt = adjustedPoolLevAmt;
	}

	public String getIsAdminApprovalClick() {
		return isAdminApprovalClick;
	}

	public void setIsAdminApprovalClick(String isAdminApprovalClick) {
		this.isAdminApprovalClick = isAdminApprovalClick;
	}

	public String getVoucherDetailsFlag() {
		return voucherDetailsFlag;
	}

	public void setVoucherDetailsFlag(String voucherDetailsFlag) {
		this.voucherDetailsFlag = voucherDetailsFlag;
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

	public String getKiEmpToken() {
		return kiEmpToken;
	}

	public void setKiEmpToken(String kiEmpToken) {
		this.kiEmpToken = kiEmpToken;
	}

	public String getSendfrAppFlag() {
		return sendfrAppFlag;
	}

	public void setSendfrAppFlag(String sendfrAppFlag) {
		this.sendfrAppFlag = sendfrAppFlag;
	}

	public String getViewAppFlag() {
		return viewAppFlag;
	}

	public void setViewAppFlag(String viewAppFlag) {
		this.viewAppFlag = viewAppFlag;
	}

	public String getSerialNoIt() {
		return serialNoIt;
	}

	public void setSerialNoIt(String serialNoIt) {
		this.serialNoIt = serialNoIt;
	}

	public String getKeepInformedEmpCodeIt() {
		return keepInformedEmpCodeIt;
	}

	public void setKeepInformedEmpCodeIt(String keepInformedEmpCodeIt) {
		this.keepInformedEmpCodeIt = keepInformedEmpCodeIt;
	}

	public String getKeepInformedEmpTokenIt() {
		return keepInformedEmpTokenIt;
	}

	public void setKeepInformedEmpTokenIt(String keepInformedEmpTokenIt) {
		this.keepInformedEmpTokenIt = keepInformedEmpTokenIt;
	}

	public String getKeepInformedEmpNameIt() {
		return keepInformedEmpNameIt;
	}

	public void setKeepInformedEmpNameIt(String keepInformedEmpNameIt) {
		this.keepInformedEmpNameIt = keepInformedEmpNameIt;
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

	public String getKiApprovalRemoveFlag() {
		return kiApprovalRemoveFlag;
	}

	public void setKiApprovalRemoveFlag(String kiApprovalRemoveFlag) {
		this.kiApprovalRemoveFlag = kiApprovalRemoveFlag;
	}

	public ArrayList getSendBackList() {
		return sendBackList;
	}

	public void setSendBackList(ArrayList sendBackList) {
		this.sendBackList = sendBackList;
	}

	public String getVoucherTypeFlag() {
		return voucherTypeFlag;
	}

	public void setVoucherTypeFlag(String voucherTypeFlag) {
		this.voucherTypeFlag = voucherTypeFlag;
	}

	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVoucherMgrFlag() {
		return voucherMgrFlag;
	}

	public void setVoucherMgrFlag(String voucherMgrFlag) {
		this.voucherMgrFlag = voucherMgrFlag;
	}

	public String getVoucherAdminFlag() {
		return voucherAdminFlag;
	}

	public void setVoucherAdminFlag(String voucherAdminFlag) {
		this.voucherAdminFlag = voucherAdminFlag;
	}

	public String getVoucherAcctFlag() {
		return voucherAcctFlag;
	}

	public void setVoucherAcctFlag(String voucherAcctFlag) {
		this.voucherAcctFlag = voucherAcctFlag;
	}

	public String getVoucherAcct() {
		return voucherAcct;
	}

	public void setVoucherAcct(String voucherAcct) {
		this.voucherAcct = voucherAcct;
	}

	public String getVoucherAdmin() {
		return voucherAdmin;
	}

	public void setVoucherAdmin(String voucherAdmin) {
		this.voucherAdmin = voucherAdmin;
	}

	public int getAppLevel() {
		return AppLevel;
	}

	public void setAppLevel(int appLevel) {
		AppLevel = appLevel;
	}

	public String getVchRemark() {
		return vchRemark;
	}

	public void setVchRemark(String vchRemark) {
		this.vchRemark = vchRemark;
	}

}
