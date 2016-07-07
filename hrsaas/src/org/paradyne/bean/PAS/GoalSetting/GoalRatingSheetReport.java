package org.paradyne.bean.PAS.GoalSetting;

import org.paradyne.lib.BeanBase;

public class GoalRatingSheetReport extends BeanBase
{
  private String fromDate;
  private String toDate;
  private String empToken;
  private String empId;
  private String empName;
  private String goalName;
  private String goalId;
  private String report = "";

  public String getFromDate() {
    return this.fromDate;
  }
  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }
  public String getToDate() {
    return this.toDate;
  }
  public void setToDate(String toDate) {
    this.toDate = toDate;
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

  public String getGoalName() {
    return this.goalName;
  }
  public void setGoalName(String goalName) {
    this.goalName = goalName;
  }
  public String getGoalId() {
    return this.goalId;
  }
  public void setGoalId(String goalId) {
    this.goalId = goalId;
  }
  public String getReport() {
    return this.report;
  }
  public void setReport(String report) {
    this.report = report;
  }
}