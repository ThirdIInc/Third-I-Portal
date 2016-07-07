package org.paradyne.bean.leave;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LeaveApplication extends BeanBase {
	private boolean ncFlagForComment=false;
	private boolean ncFlagForCancelApp=false;
	private boolean ncFlagForDraft=false;
	private boolean ncFlagForApprovePending=false;
	private boolean newChangesFlag=false;
	private String source="";
	private String isHalfDayLeaveItt ="";	
	private String isHalfDayLeave="";
	private String leaveCode = "";
	private String empCode = "";
	private String tokenNo = "";
	private String empName = "";
	private String center = "";
	private String department = "";
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
	private String status = "";
	private String forwardName = "";
	private String forwardId = "";
	private String forwardFor = "";
	private String isOfficer = "";
	private String hiddenDate = "";
	private String zeroBalance = "";
	private String advanceAppl = "";
	private String paracode = "";
	private String srNo = "";
	private String contactDetails = "";
	private String slevCode = "";
	private String slevType;
	private String sleaveFromDtl = "";
	private String sleaveToDtl = "";
	private String slevClosingBalance = "";
	private String levTotal = "";
	private String hiddenStatus = "";
	private String level;
	private String checkEdit = "";
	private String tableLength = "";
	private ArrayList att = null;
	private String availBalance = "";
	private String closeBalance = "";
	private String isApprove = "false";
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
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String approverDetails = "false";
	private ArrayList approveList = null;
	
	private String isAdminApprovalClick = "false";

	// FIELDS FOR LEAVE CANCEL APPLICATION
	private String branchName = "";
	private String designation = "";
	private String empId = "";
	private ArrayList cancelList = null;
	private String test = "true";
	private String levstatus = "";
	private String eId = "";

	// FIELDS FOR LEAVE CANCEL APPLICATION
	private String empToken = "";
	private String leaveType = "";
	private String leaveTypeCode = "";
	private String leaveFromDate = "";
	private String leaveTodate = "";
	private String leaveDays = "";
	private ArrayList list = null;
	private String policyCode = "";
	private String levAppCode = "";
	private String appDate = "";

	private String compOffDays = "";
	private String empGender = "";
	private String oldFromDate = "";
	private String oldToDate = "";
	private String negativeAllowBal = "";

	// FILDS FOR HALF KEEP INFORMED TO
	private String employeeName = "";
	private String employeeId = "";
	private String employeeToken = "";
	private ArrayList keepInformedList = null;
	private String serialNo = "";
	private String keepInformedEmpName = "";
	private String keepInformedEmpId = "";
	private String checkRemove = "";

	// FILDS FOR HALF DAY TYPE
	private String halfDayType = "";
	private String halfDayFlag = "";

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

	// VARIABLES FOR CANCELLED LIST cancelled

	private ArrayList cancelledList;
	private String cancelledEmpId = "";
	private String cancelledLeaveAppNo = "";

	// VARIABLES FOR REJECTED LIST rejected

	private ArrayList rejectedList;
	private String rejectedEmpId = "";
	private String rejectedLeaveAppNo = "";
	
	// VARIABLES FOR CANCELLED APPROVED LIST  
	private ArrayList cancelledApprovedList;
	private String  cancelledApprovedEmpId= "";
	private String  cancelledApprovedLeaveAppNo= "";
	
	// VARIABLES FOR CANCELLED REJECTED LIST  
	private String appCanrejectedEmpId= "";
	private String appCanrejectedLeaveAppNo= "";
	private ArrayList approveCancelrejectList;

	// VARIABLES FOR LIST
	private String levEmpToken = "";
	private String levEmpName = "";
	private String levAppDate = "";	
	private String listType = "";
	private String levAppStatus="";
	
	//variables for paging
	private String totalRecords="";
	
	//variables for paging
	private String myPageApproved="";
	private String myPageRejected="";
	private String myPageCancelled="";
	private String uploadProofFlag="false";
	 
	private String isProofRequired="";
	
	private String checkMeForhalfTodate="";
	
	private String halfDayTypeToDate="";
	
	private String halfDayFlagTodate="";

	//FIELDS FOR UPLOAD PROOF
	private String proofSrNo="";
	private ArrayList proofList;
	private String proofName="";
	private String uploadFileName="";
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
	private String maternityLeaveCode="";
	private ArrayList pullFrmList;
	
	private String poolLevCode="";
	private String poolLevName="";
	private String poolLevBalance="";
	private String isPoolDefine="false";
	private String adjustedPoolLevAmt="";
	
	private String leaveReasonName ="";

	private String leaveReasonCode ="";
	
	/**
	 * ADDED BY SHASHIKANT FOR HRS
	 * @return
	 */
	private String flagHrs="";
	private String leaveTypeCodeHrs="";
	private String leaveTypeNameHrs="";
	private String dateHrs="";
	private String fromTime="";
	private String toTime="";
	private String availableBalanceDay="";
	private String availableBalanceHrs="";
	private String shiftTime="";
	private String diffTime="";
	private String editFlagHrs="true";
	
	public String getEditFlagHrs() {
		return editFlagHrs;
	}

	public void setEditFlagHrs(String editFlagHrs) {
		this.editFlagHrs = editFlagHrs;
	}

	public String getDiffTime() {
		return diffTime;
	}

	public void setDiffTime(String diffTime) {
		this.diffTime = diffTime;
	}

	public String getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(String shiftTime) {
		this.shiftTime = shiftTime;
	}

	public String getLeaveTypeCodeHrs() {
		return leaveTypeCodeHrs;
	}

	public void setLeaveTypeCodeHrs(String leaveTypeCodeHrs) {
		this.leaveTypeCodeHrs = leaveTypeCodeHrs;
	}

	public String getLeaveTypeNameHrs() {
		return leaveTypeNameHrs;
	}

	public void setLeaveTypeNameHrs(String leaveTypeNameHrs) {
		this.leaveTypeNameHrs = leaveTypeNameHrs;
	}

	public String getDateHrs() {
		return dateHrs;
	}

	public void setDateHrs(String dateHrs) {
		this.dateHrs = dateHrs;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getAvailableBalanceDay() {
		return availableBalanceDay;
	}

	public void setAvailableBalanceDay(String availableBalanceDay) {
		this.availableBalanceDay = availableBalanceDay;
	}

	public String getAvailableBalanceHrs() {
		return availableBalanceHrs;
	}

	public void setAvailableBalanceHrs(String availableBalanceHrs) {
		this.availableBalanceHrs = availableBalanceHrs;
	}

	public String getFlagHrs() {
		return flagHrs;
	}

	public void setFlagHrs(String flagHrs) {
		this.flagHrs = flagHrs;
	}

	public String getLeaveReasonName() {
		return leaveReasonName;
	}

	public void setLeaveReasonName(String leaveReasonName) {
		this.leaveReasonName = leaveReasonName;
	}

	public String getLeaveReasonCode() {
		return leaveReasonCode;
	}

	public void setLeaveReasonCode(String leaveReasonCode) {
		this.leaveReasonCode = leaveReasonCode;
	}

	public ArrayList getPullFrmList() {
		return pullFrmList;
	}

	public void setPullFrmList(ArrayList pullFrmList) {
		this.pullFrmList = pullFrmList;
	}

	public String getPoolLevCode() {
		return poolLevCode;
	}

	public void setPoolLevCode(String poolLevCode) {
		this.poolLevCode = poolLevCode;
	}

	public String getPoolLevName() {
		return poolLevName;
	}

	public void setPoolLevName(String poolLevName) {
		this.poolLevName = poolLevName;
	}

	public String getMaternityLeaveCode() {
		return maternityLeaveCode;
	}

	public void setMaternityLeaveCode(String maternityLeaveCode) {
		this.maternityLeaveCode = maternityLeaveCode;
	}

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

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getHalfDayFlagTodate() {
		return halfDayFlagTodate;
	}

	public void setHalfDayFlagTodate(String halfDayFlagTodate) {
		this.halfDayFlagTodate = halfDayFlagTodate;
	}

	public String getHalfDayTypeToDate() {
		return halfDayTypeToDate;
	}

	public void setHalfDayTypeToDate(String halfDayTypeToDate) {
		this.halfDayTypeToDate = halfDayTypeToDate;
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

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
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

	public String getHalfDayType() {
		return halfDayType;
	}

	public void setHalfDayType(String halfDayType) {
		this.halfDayType = halfDayType;
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

	public String getNegativeAllowBal() {
		return negativeAllowBal;
	}

	public void setNegativeAllowBal(String negativeAllowBal) {
		this.negativeAllowBal = negativeAllowBal;
	}

	public String getOldFromDate() {
		return oldFromDate;
	}

	public void setOldFromDate(String oldFromDate) {
		this.oldFromDate = oldFromDate;
	}

	public String getOldToDate() {
		return oldToDate;
	}

	public void setOldToDate(String oldToDate) {
		this.oldToDate = oldToDate;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public String getCompOffDays() {
		return compOffDays;
	}

	public void setCompOffDays(String compOffDays) {
		this.compOffDays = compOffDays;
	}

	public String getLevAppCode() {
		return levAppCode;
	}

	public void setLevAppCode(String levAppCode) {
		this.levAppCode = levAppCode;
	}

	public String getEId() {
		return eId;
	}

	public void setEId(String id) {
		eId = id;
	}

	public String getLevstatus() {
		return levstatus;
	}

	public void setLevstatus(String levstatus) {
		this.levstatus = levstatus;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
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

	public String getHiddenStatus() {
		return hiddenStatus;
	}

	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCheckEdit() {
		return checkEdit;
	}

	public void setCheckEdit(String checkEdit) {
		this.checkEdit = checkEdit;
	}

	public String getTableLength() {
		return tableLength;
	}

	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
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

	public String getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}

	public String getIsLeaveApp() {
		return isLeaveApp;
	}

	public void setIsLeaveApp(String isLeaveApp) {
		this.isLeaveApp = isLeaveApp;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public ArrayList getCancelList() {
		return cancelList;
	}

	public void setCancelList(ArrayList cancelList) {
		this.cancelList = cancelList;
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

	public String getApprName() {
		return apprName;
	}

	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

	public String getApprComments() {
		return apprComments;
	}

	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}

	public String getApprDate() {
		return apprDate;
	}

	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}

	public ArrayList getApproveList() {
		return approveList;
	}

	public void setApproveList(ArrayList approveList) {
		this.approveList = approveList;
	}

	public String getApproverDetails() {
		return approverDetails;
	}

	public void setApproverDetails(String approverDetails) {
		this.approverDetails = approverDetails;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveTypeCode() {
		return leaveTypeCode;
	}

	public void setLeaveTypeCode(String leaveTypeCode) {
		this.leaveTypeCode = leaveTypeCode;
	}

	public String getLeaveFromDate() {
		return leaveFromDate;
	}

	public void setLeaveFromDate(String leaveFromDate) {
		this.leaveFromDate = leaveFromDate;
	}

	public String getLeaveTodate() {
		return leaveTodate;
	}

	public void setLeaveTodate(String leaveTodate) {
		this.leaveTodate = leaveTodate;
	}

	public String getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(String leaveDays) {
		this.leaveDays = leaveDays;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the policyCode
	 */
	public String getPolicyCode() {
		return policyCode;
	}

	/**
	 * @param policyCode
	 *            the policyCode to set
	 */
	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

	/**
	 * @return the appDate
	 */
	public String getAppDate() {
		return appDate;
	}

	/**
	 * @param appDate
	 *            the appDate to set
	 */
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getKeepInformedEmpName() {
		return keepInformedEmpName;
	}

	public void setKeepInformedEmpName(String keepInformedEmpName) {
		this.keepInformedEmpName = keepInformedEmpName;
	}

	public String getKeepInformedEmpId() {
		return keepInformedEmpId;
	}

	public void setKeepInformedEmpId(String keepInformedEmpId) {
		this.keepInformedEmpId = keepInformedEmpId;
	}

	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	public String getHalfDayFlag() {
		return halfDayFlag;
	}

	public void setHalfDayFlag(String halfDayFlag) {
		this.halfDayFlag = halfDayFlag;
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

	public ArrayList getCancelledList() {
		return cancelledList;
	}

	public void setCancelledList(ArrayList cancelledList) {
		this.cancelledList = cancelledList;
	}

	public String getCancelledLeaveAppNo() {
		return cancelledLeaveAppNo;
	}

	public void setCancelledLeaveAppNo(String cancelledLeaveAppNo) {
		this.cancelledLeaveAppNo = cancelledLeaveAppNo;
	}

	public String getCancelledEmpId() {
		return cancelledEmpId;
	}

	public void setCancelledEmpId(String cancelledEmpId) {
		this.cancelledEmpId = cancelledEmpId;
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

	public String getLevEmpToken() {
		return levEmpToken;
	}

	public void setLevEmpToken(String levEmpToken) {
		this.levEmpToken = levEmpToken;
	}

	public String getLevEmpName() {
		return levEmpName;
	}

	public void setLevEmpName(String levEmpName) {
		this.levEmpName = levEmpName;
	}

	public String getLevAppDate() {
		return levAppDate;
	}

	public void setLevAppDate(String levAppDate) {
		this.levAppDate = levAppDate;
	}

	public String getLevAppStatus() {
		return levAppStatus;
	}

	public void setLevAppStatus(String levAppStatus) {
		this.levAppStatus = levAppStatus;
	}

	public String getUploadProofFlag() {
		return uploadProofFlag;
	}

	public void setUploadProofFlag(String uploadProofFlag) {
		this.uploadProofFlag = uploadProofFlag;
	}

	public String getCheckMeForhalfTodate() {
		return checkMeForhalfTodate;
	}

	public void setCheckMeForhalfTodate(String checkMeForhalfTodate) {
		this.checkMeForhalfTodate = checkMeForhalfTodate;
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

	public ArrayList getCancelledApprovedList() {
		return cancelledApprovedList;
	}

	public void setCancelledApprovedList(ArrayList cancelledApprovedList) {
		this.cancelledApprovedList = cancelledApprovedList;
	}

	public String getCancelledApprovedEmpId() {
		return cancelledApprovedEmpId;
	}

	public void setCancelledApprovedEmpId(String cancelledApprovedEmpId) {
		this.cancelledApprovedEmpId = cancelledApprovedEmpId;
	}

	public String getCancelledApprovedLeaveAppNo() {
		return cancelledApprovedLeaveAppNo;
	}

	public void setCancelledApprovedLeaveAppNo(String cancelledApprovedLeaveAppNo) {
		this.cancelledApprovedLeaveAppNo = cancelledApprovedLeaveAppNo;
	}

	public String getAppCanrejectedEmpId() {
		return appCanrejectedEmpId;
	}

	public void setAppCanrejectedEmpId(String appCanrejectedEmpId) {
		this.appCanrejectedEmpId = appCanrejectedEmpId;
	}

	public String getAppCanrejectedLeaveAppNo() {
		return appCanrejectedLeaveAppNo;
	}

	public void setAppCanrejectedLeaveAppNo(String appCanrejectedLeaveAppNo) {
		this.appCanrejectedLeaveAppNo = appCanrejectedLeaveAppNo;
	}
 
	public ArrayList getApproveCancelrejectList() {
		return approveCancelrejectList;
	}

	public void setApproveCancelrejectList(ArrayList approveCancelrejectList) {
		this.approveCancelrejectList = approveCancelrejectList;
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

	public String getPoolLevBalance() {
		return poolLevBalance;
	}

	public void setPoolLevBalance(String poolLevBalance) {
		this.poolLevBalance = poolLevBalance;
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

	public String getIsHalfDayLeave() {
		return isHalfDayLeave;
	}

	public void setIsHalfDayLeave(String isHalfDayLeave) {
		this.isHalfDayLeave = isHalfDayLeave;
	}

	public String getIsHalfDayLeaveItt() {
		return isHalfDayLeaveItt;
	}

	public void setIsHalfDayLeaveItt(String isHalfDayLeaveItt) {
		this.isHalfDayLeaveItt = isHalfDayLeaveItt;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the newChangesFlag
	 */
	public boolean isNewChangesFlag() {
		return newChangesFlag;
	}

	/**
	 * @param newChangesFlag the newChangesFlag to set
	 */
	public void setNewChangesFlag(boolean newChangesFlag) {
		this.newChangesFlag = newChangesFlag;
	}

	/**
	 * @return the ncFlagForApprovePending
	 */
	public boolean isNcFlagForApprovePending() {
		return ncFlagForApprovePending;
	}

	/**
	 * @param ncFlagForApprovePending the ncFlagForApprovePending to set
	 */
	public void setNcFlagForApprovePending(boolean ncFlagForApprovePending) {
		this.ncFlagForApprovePending = ncFlagForApprovePending;
	}

	/**
	 * @return the ncFlagForDraft
	 */
	public boolean isNcFlagForDraft() {
		return ncFlagForDraft;
	}

	/**
	 * @param ncFlagForDraft the ncFlagForDraft to set
	 */
	public void setNcFlagForDraft(boolean ncFlagForDraft) {
		this.ncFlagForDraft = ncFlagForDraft;
	}

	/**
	 * @return the ncFlagForCancelApp
	 */
	public boolean isNcFlagForCancelApp() {
		return ncFlagForCancelApp;
	}

	/**
	 * @param ncFlagForCancelApp the ncFlagForCancelApp to set
	 */
	public void setNcFlagForCancelApp(boolean ncFlagForCancelApp) {
		this.ncFlagForCancelApp = ncFlagForCancelApp;
	}

	/**
	 * @return the ncFlagForComment
	 */
	public boolean isNcFlagForComment() {
		return ncFlagForComment;
	}

	/**
	 * @param ncFlagForComment the ncFlagForComment to set
	 */
	public void setNcFlagForComment(boolean ncFlagForComment) {
		this.ncFlagForComment = ncFlagForComment;
	}

}
