package org.paradyne.bean.PAS.Competency;

import org.paradyne.lib.BeanBase;

public class MyTeamCompetencyReport extends BeanBase
{
  private String compName = "";
  private String compFromDate = "";
  private String compToDate = "";
  private String compId = "";
  private String reportAction = "";
  private String report = "";
  private String empToken = "";
  private String empName = "";
  private String empId = "";

  public String getEmpToken() { return this.empToken; }

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
  public String getCompName() {
    return this.compName;
  }
  public void setCompName(String compName) {
    this.compName = compName;
  }
  public String getCompFromDate() {
    return this.compFromDate;
  }
  public void setCompFromDate(String compFromDate) {
    this.compFromDate = compFromDate;
  }
  public String getCompToDate() {
    return this.compToDate;
  }
  public void setCompToDate(String compToDate) {
    this.compToDate = compToDate;
  }
  public String getCompId() {
    return this.compId;
  }
  public void setCompId(String compId) {
    this.compId = compId;
  }
}