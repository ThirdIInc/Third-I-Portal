package org.paradyne.bean.PAS.Competency;

import org.paradyne.lib.BeanBase;

public class CompDevPlanMisReport extends BeanBase
{
  private String devPlan;
  private String devPlanId;
  private String compName;
  private String compId;
  private String report = "";
  private String compfromDate;
  private String compToDate;
  private String divisionName;
  private String divisionId;

  public String getDevPlan()
  {
    return this.devPlan;
  }
  public String getDivisionName() {
    return this.divisionName;
  }
  public void setDivisionName(String divisionName) {
    this.divisionName = divisionName;
  }
  public String getDivisionId() {
    return this.divisionId;
  }
  public void setDivisionId(String divisionId) {
    this.divisionId = divisionId;
  }
  public void setDevPlan(String devPlan) {
    this.devPlan = devPlan;
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
  public String getCompfromDate() {
    return this.compfromDate;
  }
  public void setCompfromDate(String compfromDate) {
    this.compfromDate = compfromDate;
  }
  public String getCompToDate() {
    return this.compToDate;
  }
  public void setCompToDate(String compToDate) {
    this.compToDate = compToDate;
  }
  public String getDevPlanId() {
    return this.devPlanId;
  }
  public void setDevPlanId(String devPlanId) {
    this.devPlanId = devPlanId;
  }
}