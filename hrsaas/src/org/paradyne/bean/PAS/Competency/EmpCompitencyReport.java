package org.paradyne.bean.PAS.Competency;

import org.paradyne.lib.BeanBase;

public class EmpCompitencyReport extends BeanBase
{
  private String empToken;
  private String empId;
  private String empName;
  private String reportType;
  private String compName;
  private String compId;
  private String report = "";
  private String compFromDate = "";
  private String compToDate = "";

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
  public String getReportType() {
    return this.reportType;
  }
  public void setReportType(String reportType) {
    this.reportType = reportType;
  }
  public String getCompName() {
    return this.compName;
  }
  public void setCompName(String compName) {
    this.compName = compName;
  }
  public String getCompId() {
    return this.compId;
  }
  public void setCompId(String compId) {
    this.compId = compId;
  }
  public String getReport() {
    return this.report;
  }
  public void setReport(String report) {
    this.report = report;
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
}