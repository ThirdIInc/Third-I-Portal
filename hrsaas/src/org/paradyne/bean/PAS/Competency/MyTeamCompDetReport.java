package org.paradyne.bean.PAS.Competency;

import org.paradyne.lib.BeanBase;

public class MyTeamCompDetReport extends BeanBase
{
  private String compName = "";
  private String compFromDate = "";
  private String compToDate = "";
  private String compId = "";
  private String reportAction = "";
  private String report = "";
  private String divisionId;
  private String divisionName;
  private String desgId;
  private String desgName;

  public String getCompName()
  {
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
  public String getCompId() {
    return this.compId;
  }
  public void setCompId(String compId) {
    this.compId = compId;
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
}