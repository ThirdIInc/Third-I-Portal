package org.paradyne.bean.settlement;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class ResignationApplication extends BeanBase
{
  private String draftResignEmpId = "";
  private String draftResignAppNo = "";
  private String resignAppStatus = "";
  private String resignEmpToken = "";
  private String resignEmpName = "";
  private String submitEmpId = "";
  private String submitResignAppNo = "";
  private String withdrawnEmpId = "";
  private String withdrawnAppNo = "";
  private String resignAppDate = "";
  private ArrayList draftList = null;
  private ArrayList submitList = null;
  private ArrayList withdrawnList = null;
  private String listType = "";
  private String empToken = "";
  private String empName = "";
  private String empCode = "";
  private String designationName = "";
  private String branchName = "";
  private String dateOfJoin = "";
  private String resignDate = "";
  private String seperationDate = "";
  private String appComment = "";
  private String appReason = "";
  private String status = "";
  private String resignCode = "";
  private String approvedResignAppNo = "";
  private String approvedResignEmpId = "";
  private ArrayList approvedList = null;
  private ArrayList rejectedList = null;
  private String rejectedResignAppNo = "";
  private String rejectedResignEmpId = "";
  private String hiddenStatus = "";
  private String level = "";
  private String imageFlag = "true";
  private String approvalFlag = "false";
  private String checkApproveRejectStatus = "";
  private String acceptDate = "";
  private String approverComments = "";
  private String buttonFlag = "false";
  private String closeBtnFlag = "false";
  private String approverDtlFlag = "false";
  private String showFlag = "true";
  private String prevAppCommentListFlag = "false";
  private ArrayList approverCommentList = null;
  private String appSrNo = "";
  private String prevApproverID = "";
  private String prevApproverName = "";
  private String prevApproverDate = "";
  private String prevApproverStatus = "";
  private String prevApproverComment = "";
  private String seperationDateActual = "";
  private String hrApprovalFlag = "false";
  private String noticePeriodActual = "";
  private String waveOffPeriod = "";
  private String apprName = "";
  private String hrComments = "";
  private String withDrawn = "";
  private String noticeperiodselect = "";
  private String noticePeriod = "";
  private String statusSelect = "";
  private String employeeId = "";
  private String employeeToken = "";
  private String employeeName = "";
  private ArrayList keepInformedList = null;
  private String serialNo = "";
  private String keepInformedEmpName = "";
  private String keepInformedEmpId = "";
  private String checkRemove = "";
  private ArrayList approverList = null;
  private String approverName = "";
  private String srNoIterator = "";
  private String source = "";

  public String getSource() {
    return this.source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public ArrayList getApproverList() {
    return this.approverList;
  }

  public void setApproverList(ArrayList approverList) {
    this.approverList = approverList;
  }

  public String getApproverName() {
    return this.approverName;
  }

  public void setApproverName(String approverName) {
    this.approverName = approverName;
  }

  public String getSrNoIterator() {
    return this.srNoIterator;
  }

  public void setSrNoIterator(String srNoIterator) {
    this.srNoIterator = srNoIterator;
  }

  public String getCheckRemove() {
    return this.checkRemove;
  }

  public void setCheckRemove(String checkRemove) {
    this.checkRemove = checkRemove;
  }

  public String getSerialNo() {
    return this.serialNo;
  }

  public void setSerialNo(String serialNo) {
    this.serialNo = serialNo;
  }

  public String getKeepInformedEmpName() {
    return this.keepInformedEmpName;
  }

  public void setKeepInformedEmpName(String keepInformedEmpName) {
    this.keepInformedEmpName = keepInformedEmpName;
  }

  public String getKeepInformedEmpId() {
    return this.keepInformedEmpId;
  }

  public void setKeepInformedEmpId(String keepInformedEmpId) {
    this.keepInformedEmpId = keepInformedEmpId;
  }

  public ArrayList getKeepInformedList() {
    return this.keepInformedList;
  }

  public void setKeepInformedList(ArrayList keepInformedList) {
    this.keepInformedList = keepInformedList;
  }

  public String getEmployeeId() {
    return this.employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeToken() {
    return this.employeeToken;
  }

  public void setEmployeeToken(String employeeToken) {
    this.employeeToken = employeeToken;
  }

  public String getEmployeeName() {
    return this.employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getAcceptDate() {
    return this.acceptDate;
  }

  public void setAcceptDate(String acceptDate) {
    this.acceptDate = acceptDate;
  }

  public String getApproverComments() {
    return this.approverComments;
  }

  public void setApproverComments(String approverComments) {
    this.approverComments = approverComments;
  }

  public String getCheckApproveRejectStatus() {
    return this.checkApproveRejectStatus;
  }

  public void setCheckApproveRejectStatus(String checkApproveRejectStatus) {
    this.checkApproveRejectStatus = checkApproveRejectStatus;
  }

  public ArrayList getRejectedList() {
    return this.rejectedList;
  }

  public void setRejectedList(ArrayList rejectedList) {
    this.rejectedList = rejectedList;
  }

  public String getRejectedResignAppNo() {
    return this.rejectedResignAppNo;
  }

  public void setRejectedResignAppNo(String rejectedResignAppNo) {
    this.rejectedResignAppNo = rejectedResignAppNo;
  }

  public String getRejectedResignEmpId() {
    return this.rejectedResignEmpId;
  }

  public void setRejectedResignEmpId(String rejectedResignEmpId) {
    this.rejectedResignEmpId = rejectedResignEmpId;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getEmpToken() {
    return this.empToken;
  }

  public void setEmpToken(String empToken) {
    this.empToken = empToken;
  }

  public String getEmpName() {
    return this.empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getEmpCode() {
    return this.empCode;
  }

  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }

  public String getDesignationName() {
    return this.designationName;
  }

  public void setDesignationName(String designationName) {
    this.designationName = designationName;
  }

  public String getBranchName() {
    return this.branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public String getDateOfJoin() {
    return this.dateOfJoin;
  }

  public void setDateOfJoin(String dateOfJoin) {
    this.dateOfJoin = dateOfJoin;
  }

  public String getSeperationDate() {
    return this.seperationDate;
  }

  public void setSeperationDate(String seperationDate) {
    this.seperationDate = seperationDate;
  }

  public String getAppComment() {
    return this.appComment;
  }

  public void setAppComment(String appComment) {
    this.appComment = appComment;
  }

  public String getAppReason() {
    return this.appReason;
  }

  public void setAppReason(String appReason) {
    this.appReason = appReason;
  }

  public String getResignDate() {
    return this.resignDate;
  }

  public void setResignDate(String resignDate) {
    this.resignDate = resignDate;
  }

  public String getResignCode() {
    return this.resignCode;
  }

  public void setResignCode(String resignCode) {
    this.resignCode = resignCode;
  }

  public String getDraftResignEmpId() {
    return this.draftResignEmpId;
  }

  public void setDraftResignEmpId(String draftResignEmpId) {
    this.draftResignEmpId = draftResignEmpId;
  }

  public String getDraftResignAppNo() {
    return this.draftResignAppNo;
  }

  public void setDraftResignAppNo(String draftResignAppNo) {
    this.draftResignAppNo = draftResignAppNo;
  }

  public String getResignAppStatus() {
    return this.resignAppStatus;
  }

  public void setResignAppStatus(String resignAppStatus) {
    this.resignAppStatus = resignAppStatus;
  }

  public String getResignEmpToken() {
    return this.resignEmpToken;
  }

  public void setResignEmpToken(String resignEmpToken) {
    this.resignEmpToken = resignEmpToken;
  }

  public String getResignEmpName() {
    return this.resignEmpName;
  }

  public void setResignEmpName(String resignEmpName) {
    this.resignEmpName = resignEmpName;
  }

  public String getSubmitEmpId() {
    return this.submitEmpId;
  }

  public void setSubmitEmpId(String submitEmpId) {
    this.submitEmpId = submitEmpId;
  }

  public String getSubmitResignAppNo() {
    return this.submitResignAppNo;
  }

  public void setSubmitResignAppNo(String submitResignAppNo) {
    this.submitResignAppNo = submitResignAppNo;
  }

  public String getWithdrawnEmpId() {
    return this.withdrawnEmpId;
  }

  public void setWithdrawnEmpId(String withdrawnEmpId) {
    this.withdrawnEmpId = withdrawnEmpId;
  }

  public String getWithdrawnAppNo() {
    return this.withdrawnAppNo;
  }

  public void setWithdrawnAppNo(String withdrawnAppNo) {
    this.withdrawnAppNo = withdrawnAppNo;
  }

  public ArrayList getDraftList() {
    return this.draftList;
  }

  public void setDraftList(ArrayList draftList) {
    this.draftList = draftList;
  }

  public ArrayList getSubmitList() {
    return this.submitList;
  }

  public void setSubmitList(ArrayList submitList) {
    this.submitList = submitList;
  }

  public ArrayList getWithdrawnList() {
    return this.withdrawnList;
  }

  public void setWithdrawnList(ArrayList withdrawnList) {
    this.withdrawnList = withdrawnList;
  }

  public String getListType() {
    return this.listType;
  }

  public void setListType(String listType) {
    this.listType = listType;
  }

  public String getResignAppDate() {
    return this.resignAppDate;
  }

  public void setResignAppDate(String resignAppDate) {
    this.resignAppDate = resignAppDate;
  }

  public String getApprovedResignAppNo() {
    return this.approvedResignAppNo;
  }

  public void setApprovedResignAppNo(String approvedResignAppNo) {
    this.approvedResignAppNo = approvedResignAppNo;
  }

  public String getApprovedResignEmpId() {
    return this.approvedResignEmpId;
  }

  public void setApprovedResignEmpId(String approvedResignEmpId) {
    this.approvedResignEmpId = approvedResignEmpId;
  }

  public ArrayList getApprovedList() {
    return this.approvedList;
  }

  public void setApprovedList(ArrayList approvedList) {
    this.approvedList = approvedList;
  }

  public String getHiddenStatus() {
    return this.hiddenStatus;
  }

  public void setHiddenStatus(String hiddenStatus) {
    this.hiddenStatus = hiddenStatus;
  }

  public String getLevel() {
    return this.level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getImageFlag() {
    return this.imageFlag;
  }

  public void setImageFlag(String imageFlag) {
    this.imageFlag = imageFlag;
  }

  public String getApprovalFlag() {
    return this.approvalFlag;
  }

  public void setApprovalFlag(String approvalFlag) {
    this.approvalFlag = approvalFlag;
  }

  public String getButtonFlag() {
    return this.buttonFlag;
  }

  public void setButtonFlag(String buttonFlag) {
    this.buttonFlag = buttonFlag;
  }

  public String getCloseBtnFlag() {
    return this.closeBtnFlag;
  }

  public void setCloseBtnFlag(String closeBtnFlag) {
    this.closeBtnFlag = closeBtnFlag;
  }

  public String getApproverDtlFlag() {
    return this.approverDtlFlag;
  }

  public void setApproverDtlFlag(String approverDtlFlag) {
    this.approverDtlFlag = approverDtlFlag;
  }

  public String getShowFlag() {
    return this.showFlag;
  }

  public void setShowFlag(String showFlag) {
    this.showFlag = showFlag;
  }

  public String getPrevAppCommentListFlag() {
    return this.prevAppCommentListFlag;
  }

  public void setPrevAppCommentListFlag(String prevAppCommentListFlag) {
    this.prevAppCommentListFlag = prevAppCommentListFlag;
  }

  public ArrayList getApproverCommentList() {
    return this.approverCommentList;
  }

  public void setApproverCommentList(ArrayList approverCommentList) {
    this.approverCommentList = approverCommentList;
  }

  public String getAppSrNo() {
    return this.appSrNo;
  }

  public void setAppSrNo(String appSrNo) {
    this.appSrNo = appSrNo;
  }

  public String getPrevApproverID() {
    return this.prevApproverID;
  }

  public void setPrevApproverID(String prevApproverID) {
    this.prevApproverID = prevApproverID;
  }

  public String getPrevApproverName() {
    return this.prevApproverName;
  }

  public void setPrevApproverName(String prevApproverName) {
    this.prevApproverName = prevApproverName;
  }

  public String getPrevApproverDate() {
    return this.prevApproverDate;
  }

  public void setPrevApproverDate(String prevApproverDate) {
    this.prevApproverDate = prevApproverDate;
  }

  public String getPrevApproverStatus() {
    return this.prevApproverStatus;
  }

  public void setPrevApproverStatus(String prevApproverStatus) {
    this.prevApproverStatus = prevApproverStatus;
  }

  public String getPrevApproverComment() {
    return this.prevApproverComment;
  }

  public void setPrevApproverComment(String prevApproverComment) {
    this.prevApproverComment = prevApproverComment;
  }

  public String getSeperationDateActual() {
    return this.seperationDateActual;
  }

  public void setSeperationDateActual(String seperationDateActual) {
    this.seperationDateActual = seperationDateActual;
  }

  public String getHrApprovalFlag() {
    return this.hrApprovalFlag;
  }

  public void setHrApprovalFlag(String hrApprovalFlag) {
    this.hrApprovalFlag = hrApprovalFlag;
  }

  public String getNoticePeriodActual() {
    return this.noticePeriodActual;
  }

  public void setNoticePeriodActual(String noticePeriodActual) {
    this.noticePeriodActual = noticePeriodActual;
  }

  public String getWaveOffPeriod() {
    return this.waveOffPeriod;
  }

  public void setWaveOffPeriod(String waveOffPeriod) {
    this.waveOffPeriod = waveOffPeriod;
  }

  public String getApprName() {
    return this.apprName;
  }

  public void setApprName(String apprName) {
    this.apprName = apprName;
  }

  public String getHrComments() {
    return this.hrComments;
  }

  public void setHrComments(String hrComments) {
    this.hrComments = hrComments;
  }

  public String getWithDrawn() {
    return this.withDrawn;
  }

  public void setWithDrawn(String withDrawn) {
    this.withDrawn = withDrawn;
  }

  public String getNoticeperiodselect() {
    return this.noticeperiodselect;
  }

  public void setNoticeperiodselect(String noticeperiodselect) {
    this.noticeperiodselect = noticeperiodselect;
  }

  public String getNoticePeriod() {
    return this.noticePeriod;
  }

  public void setNoticePeriod(String noticePeriod) {
    this.noticePeriod = noticePeriod;
  }

  public String getStatusSelect() {
    return this.statusSelect;
  }

  public void setStatusSelect(String statusSelect) {
    this.statusSelect = statusSelect;
  }
}