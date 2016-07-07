package org.paradyne.bean.PAS;

import org.paradyne.lib.BeanBase;

/**
 * @modified by @author Prajakta.Bhandare
 * @date 25 April 2013
 */
public class AppraisalScoreBalancerReport extends BeanBase
{
  private String sAppCode = null;
  private String sAppStartDate = null;
  private String sAppEndDate = null;
  private String sAppId = null;
  private String report="";//Added by prajakta for report

  /**
 * @return report
 */
public String getReport() {
	return report;
}

/**
 * @param report
 * the report to set
 */
public void setReport(String report) {
	this.report = report;
}

public String getSAppCode() { return this.sAppCode; }

  public void setSAppCode(String appCode) {
    this.sAppCode = appCode;
  }
  public String getSAppStartDate() {
    return this.sAppStartDate;
  }
  public void setSAppStartDate(String appStartDate) {
    this.sAppStartDate = appStartDate;
  }
  public String getSAppEndDate() {
    return this.sAppEndDate;
  }
  public void setSAppEndDate(String appEndDate) {
    this.sAppEndDate = appEndDate;
  }
  public String getSAppId() {
    return this.sAppId;
  }
  public void setSAppId(String appId) {
    this.sAppId = appId;
  }
}