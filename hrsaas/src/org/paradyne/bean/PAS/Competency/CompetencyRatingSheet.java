package org.paradyne.bean.PAS.Competency;

import org.paradyne.lib.BeanBase;

public class CompetencyRatingSheet extends BeanBase
{
  private String compName;
  private String compId;
  private String report = "";
  private String compfromDate;
  private String compToDate;
  private String divisionName;
  private String divisionId;
  private String desgId;
  private String desgName;

  public String getDesgId()
  {
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
}