package org.paradyne.bean.settlement;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class ResignationHRApproval extends BeanBase
{
  private String listType = "";
  private String tokenNo = "";
  private String resignAppNo = "";
  private String pendingStatus = "";
  private String empCode = "";
  private String empName = "";
  private String date = "";
  private String level = "";

  private String appEmpId = "";
  private String appResignAppNo = "";
  private String appEmpToken = "";
  private String appResignAppDate = "";
  private String appStatus = "";
  private String appEmpName = "";
  private String appLevel = "";

  private String rejResignAppNo = "";
  private String rejEmpId = "";
  private String rejLevel = "";
  private String rejEmpToken = "";
  private String rejEmpName = "";
  private String rejResignAppDate = "";
  private String rejStatus = "";

  private ArrayList pendingList = null;
  private ArrayList appList = null;
  private ArrayList rejList = null;

  private String employeeCode = "";
  private String empToken = "";
  private String employeeName = "";
  private String designationName = "";
  private String branchName = "";
  private String dateOfJoin = "";
  private String resignDate = "";
  private String seperationDate = "";
  private String hiddenStatus = "";
  private String appComment = "";
  private String appReason = "";
  private String acceptDate = "";
  private String actualSeperationDate = "";
  private String approverComments = "";
  private String status = "";
  private String noticePeriodActual = "";
  private String noticeperiodselect = "";
  private String apprName = "";
  private String apprCode = "";
  private String apprToken = "";
  private String hrComments = "";

  private String resignCode = "";
  private String checkApproveRejectStatus = "";
  private String waveOffPeriod = "";
  private String withDrawn = "";
  private String showFlag = "true";
  private String approvalFlag = "true";
  private String applicationStatus = "";
  private String noticePeriod = "";

  private String deptCode = "";
  private String cardeName = "";

  public String getShowFlag() { return this.showFlag; }

  public void setShowFlag(String showFlag)
  {
    this.showFlag = showFlag;
  }

  public String getWaveOffPeriod() {
    return this.waveOffPeriod;
  }

  public void setWaveOffPeriod(String waveOffPeriod) {
    this.waveOffPeriod = waveOffPeriod;
  }

  public String getResignCode() {
    return this.resignCode;
  }

  public void setResignCode(String resignCode) {
    this.resignCode = resignCode;
  }

  public String getCheckApproveRejectStatus() {
    return this.checkApproveRejectStatus;
  }

  public void setCheckApproveRejectStatus(String checkApproveRejectStatus) {
    this.checkApproveRejectStatus = checkApproveRejectStatus;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getNoticePeriodActual() {
    return this.noticePeriodActual;
  }

  public void setNoticePeriodActual(String noticePeriodActual) {
    this.noticePeriodActual = noticePeriodActual;
  }

  public String getNoticeperiodselect() {
    return this.noticeperiodselect;
  }

  public void setNoticeperiodselect(String noticeperiodselect) {
    this.noticeperiodselect = noticeperiodselect;
  }

  public String getApprName() {
    return this.apprName;
  }

  public void setApprName(String apprName) {
    this.apprName = apprName;
  }

  public String getApprCode() {
    return this.apprCode;
  }

  public void setApprCode(String apprCode) {
    this.apprCode = apprCode;
  }

  public String getHrComments() {
    return this.hrComments;
  }

  public void setHrComments(String hrComments) {
    this.hrComments = hrComments;
  }

  public String getRejResignAppNo() {
    return this.rejResignAppNo;
  }

  public void setRejResignAppNo(String rejResignAppNo) {
    this.rejResignAppNo = rejResignAppNo;
  }

  public String getRejEmpId() {
    return this.rejEmpId;
  }

  public void setRejEmpId(String rejEmpId) {
    this.rejEmpId = rejEmpId;
  }

  public String getRejLevel() {
    return this.rejLevel;
  }

  public void setRejLevel(String rejLevel) {
    this.rejLevel = rejLevel;
  }

  public String getRejEmpToken() {
    return this.rejEmpToken;
  }

  public void setRejEmpToken(String rejEmpToken) {
    this.rejEmpToken = rejEmpToken;
  }

  public String getRejEmpName() {
    return this.rejEmpName;
  }

  public void setRejEmpName(String rejEmpName) {
    this.rejEmpName = rejEmpName;
  }

  public String getRejResignAppDate() {
    return this.rejResignAppDate;
  }

  public void setRejResignAppDate(String rejResignAppDate) {
    this.rejResignAppDate = rejResignAppDate;
  }

  public String getRejStatus() {
    return this.rejStatus;
  }

  public void setRejStatus(String rejStatus) {
    this.rejStatus = rejStatus;
  }

  public ArrayList getRejList() {
    return this.rejList;
  }

  public void setRejList(ArrayList rejList) {
    this.rejList = rejList;
  }

  public String getAppEmpId() {
    return this.appEmpId;
  }

  public void setAppEmpId(String appEmpId) {
    this.appEmpId = appEmpId;
  }

  public String getAppResignAppNo() {
    return this.appResignAppNo;
  }

  public void setAppResignAppNo(String appResignAppNo) {
    this.appResignAppNo = appResignAppNo;
  }

  public String getAppEmpToken() {
    return this.appEmpToken;
  }

  public void setAppEmpToken(String appEmpToken) {
    this.appEmpToken = appEmpToken;
  }

  public String getAppResignAppDate() {
    return this.appResignAppDate;
  }

  public void setAppResignAppDate(String appResignAppDate) {
    this.appResignAppDate = appResignAppDate;
  }

  public String getAppStatus() {
    return this.appStatus;
  }

  public void setAppStatus(String appStatus) {
    this.appStatus = appStatus;
  }

  public String getAppEmpName() {
    return this.appEmpName;
  }

  public void setAppEmpName(String appEmpName) {
    this.appEmpName = appEmpName;
  }

  public String getAppLevel() {
    return this.appLevel;
  }

  public void setAppLevel(String appLevel) {
    this.appLevel = appLevel;
  }

  public ArrayList getAppList() {
    return this.appList;
  }

  public void setAppList(ArrayList appList) {
    this.appList = appList;
  }

  public ArrayList getPendingList() {
    return this.pendingList;
  }

  public void setPendingList(ArrayList pendingList) {
    this.pendingList = pendingList;
  }

  public String getListType() {
    return this.listType;
  }

  public void setListType(String listType) {
    this.listType = listType;
  }

  public String getTokenNo() {
    return this.tokenNo;
  }

  public void setTokenNo(String tokenNo) {
    this.tokenNo = tokenNo;
  }

  public String getResignAppNo() {
    return this.resignAppNo;
  }

  public void setResignAppNo(String resignAppNo) {
    this.resignAppNo = resignAppNo;
  }

  public String getPendingStatus() {
    return this.pendingStatus;
  }

  public void setPendingStatus(String pendingStatus) {
    this.pendingStatus = pendingStatus;
  }

  public String getEmpCode() {
    return this.empCode;
  }

  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }

  public String getEmpName() {
    return this.empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getLevel() {
    return this.level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getApprToken() {
    return this.apprToken;
  }

  public void setApprToken(String apprToken) {
    this.apprToken = apprToken;
  }

  public String getEmployeeCode() {
    return this.employeeCode;
  }

  public void setEmployeeCode(String employeeCode) {
    this.employeeCode = employeeCode;
  }

  public String getEmpToken() {
    return this.empToken;
  }

  public void setEmpToken(String empToken) {
    this.empToken = empToken;
  }

  public String getEmployeeName() {
    return this.employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
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

  public String getResignDate() {
    return this.resignDate;
  }

  public void setResignDate(String resignDate) {
    this.resignDate = resignDate;
  }

  public String getSeperationDate() {
    return this.seperationDate;
  }

  public void setSeperationDate(String seperationDate) {
    this.seperationDate = seperationDate;
  }

  public String getHiddenStatus() {
    return this.hiddenStatus;
  }

  public void setHiddenStatus(String hiddenStatus) {
    this.hiddenStatus = hiddenStatus;
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

  public String getAcceptDate() {
    return this.acceptDate;
  }

  public void setAcceptDate(String acceptDate) {
    this.acceptDate = acceptDate;
  }

  public String getActualSeperationDate() {
    return this.actualSeperationDate;
  }

  public void setActualSeperationDate(String actualSeperationDate) {
    this.actualSeperationDate = actualSeperationDate;
  }

  public String getApproverComments() {
    return this.approverComments;
  }

  public void setApproverComments(String approverComments) {
    this.approverComments = approverComments;
  }

  public String getWithDrawn() {
    return this.withDrawn;
  }

  public void setWithDrawn(String withDrawn) {
    this.withDrawn = withDrawn;
  }

  public String getApprovalFlag() {
    return this.approvalFlag;
  }

  public void setApprovalFlag(String approvalFlag) {
    this.approvalFlag = approvalFlag;
  }

  public String getApplicationStatus() {
    return this.applicationStatus;
  }

  public void setApplicationStatus(String applicationStatus) {
    this.applicationStatus = applicationStatus;
  }

  public String getNoticePeriod() {
    return this.noticePeriod;
  }

  public void setNoticePeriod(String noticePeriod) {
    this.noticePeriod = noticePeriod;
  }

  public String getDeptCode() {
    return this.deptCode;
  }

  public void setDeptCode(String deptCode) {
    this.deptCode = deptCode;
  }

  public String getCardeName() {
    return this.cardeName;
  }

  public void setCardeName(String cardeName) {
    this.cardeName = cardeName;
  }
}