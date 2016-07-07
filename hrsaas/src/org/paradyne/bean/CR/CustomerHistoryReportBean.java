package org.paradyne.bean.CR;

import org.paradyne.lib.BeanBase;

public class CustomerHistoryReportBean extends BeanBase
{
  private String fromDate = "";
  private String toDate = "";
  private String report = "";

  public String getReport()
  {
    return this.report;
  }

  public void setReport(String report)
  {
    this.report = report;
  }

  public String getFromDate()
  {
    return this.fromDate;
  }

  public void setFromDate(String fromDate)
  {
    this.fromDate = fromDate;
  }

  public String getToDate()
  {
    return this.toDate;
  }

  public void setToDate(String toDate)
  {
    this.toDate = toDate;
  }
}