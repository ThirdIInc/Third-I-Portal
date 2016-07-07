package org.paradyne.bean.PAS.GoalSetting;

import org.paradyne.lib.BeanBase;

public class EmpGoalReport extends BeanBase
{
  private String divisionId;
  private String divisionName;
  private String branchId;
  private String branchName;
  private String deptId;
  private String deptName;
  private String desgId;
  private String desgName;
  private String empToken;
  private String empId;
  private String empName;
  private String goalStatus;
  private String reportType;
  private String showSelfAssessment;
  private String showManagerAssessment;
  private String showPendingEmployee;
  private String goalName;
  private String goalId;
  private String report = "";

  public String getGoalId() {
    return this.goalId;
  }
  public void setGoalId(String goalId) {
    this.goalId = goalId;
  }
  public String getDivisionId() {
    return this.divisionId;
  }
  public void setDivisionId(String divisionId) {
    this.divisionId = divisionId;
  }
  public String getDivisionName() {
    return this.divisionName;
  }
  public void setDivisionName(String divisionName) {
    this.divisionName = divisionName;
  }
  public String getBranchId() {
    return this.branchId;
  }
  public void setBranchId(String branchId) {
    this.branchId = branchId;
  }
  public String getBranchName() {
    return this.branchName;
  }
  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }
  public String getDeptId() {
    return this.deptId;
  }
  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }
  public String getDeptName() {
    return this.deptName;
  }
  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
  public String getDesgId() {
    return this.desgId;
  }
  public void setDesgId(String desgId) {
    this.desgId = desgId;
  }
  public String getDesgName() {
    return this.desgName;
  }
  public void setDesgName(String desgName) {
    this.desgName = desgName;
  }
  public String getEmpToken() {
    return this.empToken;
  }
  public void setEmpToken(String empToken) {
    this.empToken = empToken;
  }
  public String getEmpId() {
    return this.empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getEmpName() {
    return this.empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  public String getGoalStatus() {
    return this.goalStatus;
  }
  public void setGoalStatus(String goalStatus) {
    this.goalStatus = goalStatus;
  }
  public String getReportType() {
    return this.reportType;
  }
  public void setReportType(String reportType) {
    this.reportType = reportType;
  }
  public String getShowSelfAssessment() {
    return this.showSelfAssessment;
  }
  public void setShowSelfAssessment(String showSelfAssessment) {
    this.showSelfAssessment = showSelfAssessment;
  }
  public String getShowManagerAssessment() {
    return this.showManagerAssessment;
  }
  public void setShowManagerAssessment(String showManagerAssessment) {
    this.showManagerAssessment = showManagerAssessment;
  }
  public String getShowPendingEmployee() {
    return this.showPendingEmployee;
  }
  public void setShowPendingEmployee(String showPendingEmployee) {
    this.showPendingEmployee = showPendingEmployee;
  }
  public String getGoalName() {
    return this.goalName;
  }
  public void setGoalName(String goalName) {
    this.goalName = goalName;
  }
  public String getReport() {
    return this.report;
  }
  public void setReport(String report) {
    this.report = report;
  }
}