package org.paradyne.bean.PAS.GoalSetting;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class EmployeeGoalApproval extends BeanBase
{
  private String listType;
  private String myPageApproved;
  private String myPage;
  private String pendingLength;
  private String empGoalId;
  private String empCode;
  private String empToken;
  private String empName;
  private String goalPeriod;
  private String status;
  private String approvedLength;
  private String approverCodeList;
  private String reportingType = "";

  ArrayList pendingList = new ArrayList();
  ArrayList approvedList = new ArrayList();

  public String getStatus() { return this.status; }

  public void setStatus(String status) {
    this.status = status;
  }
  public String getApprovedLength() {
    return this.approvedLength;
  }
  public void setApprovedLength(String approvedLength) {
    this.approvedLength = approvedLength;
  }
  public ArrayList getApprovedList() {
    return this.approvedList;
  }
  public void setApprovedList(ArrayList approvedList) {
    this.approvedList = approvedList;
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
  public String getMyPageApproved() {
    return this.myPageApproved;
  }
  public void setMyPageApproved(String myPageApproved) {
    this.myPageApproved = myPageApproved;
  }
  public String getMyPage() {
    return this.myPage;
  }
  public void setMyPage(String myPage) {
    this.myPage = myPage;
  }
  public String getPendingLength() {
    return this.pendingLength;
  }
  public void setPendingLength(String pendingLength) {
    this.pendingLength = pendingLength;
  }
  public String getEmpGoalId() {
    return this.empGoalId;
  }
  public void setEmpGoalId(String empGoalId) {
    this.empGoalId = empGoalId;
  }
  public String getEmpCode() {
    return this.empCode;
  }
  public void setEmpCode(String empCode) {
    this.empCode = empCode;
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
  public String getGoalPeriod() {
    return this.goalPeriod;
  }
  public void setGoalPeriod(String goalPeriod) {
    this.goalPeriod = goalPeriod;
  }
  public String getReportingType() {
    return this.reportingType;
  }
  public void setReportingType(String reportingType) {
    this.reportingType = reportingType;
  }
  public String getApproverCodeList() {
    return this.approverCodeList;
  }
  public void setApproverCodeList(String approverCodeList) {
    this.approverCodeList = approverCodeList;
  }
}