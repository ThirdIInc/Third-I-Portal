package org.paradyne.bean.PAS.GoalSetting;

import org.paradyne.lib.BeanBase;

public class MyTeamGoalRatingReport extends BeanBase
{
  private String goalName = "";
  private String goalFromDate = "";
  private String goalToDate = "";
  private String goalId = "";
  private String reportAction = "";
  private String report = "";
  private String empToken = "";
  private String empName = "";
  private String empId = "";

  public String getGoalName() {
    return this.goalName;
  }
  public void setGoalName(String goalName) {
    this.goalName = goalName;
  }
  public String getGoalFromDate() {
    return this.goalFromDate;
  }
  public void setGoalFromDate(String goalFromDate) {
    this.goalFromDate = goalFromDate;
  }
  public String getGoalToDate() {
    return this.goalToDate;
  }
  public void setGoalToDate(String goalToDate) {
    this.goalToDate = goalToDate;
  }
  public String getGoalId() {
    return this.goalId;
  }
  public void setGoalId(String goalId) {
    this.goalId = goalId;
  }
  public String getReportAction() {
    return this.reportAction;
  }
  public void setReportAction(String reportAction) {
    this.reportAction = reportAction;
  }
  public String getReport() {
    return this.report;
  }
  public void setReport(String report) {
    this.report = report;
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
  public String getEmpId() {
    return this.empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
}