package org.paradyne.bean.D1.reports;

import java.util.ArrayList;
import java.util.TreeMap;
import org.paradyne.lib.BeanBase;

public class BRDSuperUser extends BeanBase
{
  private TreeMap<String, String> currentStageList;
  private String currentStage = "";
  private String applicationStatus = "";
  private String ticketNumber = "";
  private String initiatorId = "";
  private String initiatorToken = "";
  private String initiatorName = "";
  private String pendingWithEmpId = "";
  private String pendingWithEmpToken = "";
  private String pendingWithEmpName = "";

  private String myPage = "";
  private boolean brdSuperUserPagingFlag = false;
  private String srNoItr = "";
  private String brdApplicationIdItr = "";
  private String brdTicketNoItr = "";
  private String projectNameItr = "";
  private String expectedDateItr = "";
  private String currentStageItr = "";
  private String pendingWithRoleItr = "";
  private String statusItr = "";
  private String pendingWithNameItr = "";
  ArrayList<BRDSuperUser> applicationList = null;
  private boolean nodataFlag = false;
  private String currentActivityItr = "";
  private String forecastedCompletionDateItr = "";
  private String fromDate = "";
  private String toDate = "";

  public String getSrNoItr()
  {
    return this.srNoItr;
  }

  public void setSrNoItr(String srNoItr)
  {
    this.srNoItr = srNoItr;
  }

  public String getBrdApplicationIdItr()
  {
    return this.brdApplicationIdItr;
  }

  public void setBrdApplicationIdItr(String brdApplicationIdItr)
  {
    this.brdApplicationIdItr = brdApplicationIdItr;
  }

  public String getBrdTicketNoItr()
  {
    return this.brdTicketNoItr;
  }

  public void setBrdTicketNoItr(String brdTicketNoItr)
  {
    this.brdTicketNoItr = brdTicketNoItr;
  }

  public String getProjectNameItr()
  {
    return this.projectNameItr;
  }

  public void setProjectNameItr(String projectNameItr)
  {
    this.projectNameItr = projectNameItr;
  }

  public String getExpectedDateItr()
  {
    return this.expectedDateItr;
  }

  public void setExpectedDateItr(String expectedDateItr)
  {
    this.expectedDateItr = expectedDateItr;
  }

  public String getCurrentStageItr()
  {
    return this.currentStageItr;
  }

  public void setCurrentStageItr(String currentStageItr)
  {
    this.currentStageItr = currentStageItr;
  }

  public String getPendingWithRoleItr()
  {
    return this.pendingWithRoleItr;
  }

  public void setPendingWithRoleItr(String pendingWithRoleItr)
  {
    this.pendingWithRoleItr = pendingWithRoleItr;
  }

  public String getStatusItr()
  {
    return this.statusItr;
  }

  public void setStatusItr(String statusItr)
  {
    this.statusItr = statusItr;
  }

  public String getPendingWithNameItr()
  {
    return this.pendingWithNameItr;
  }

  public void setPendingWithNameItr(String pendingWithNameItr)
  {
    this.pendingWithNameItr = pendingWithNameItr;
  }

  public ArrayList<BRDSuperUser> getApplicationList()
  {
    return this.applicationList;
  }

  public void setApplicationList(ArrayList<BRDSuperUser> applicationList)
  {
    this.applicationList = applicationList;
  }

  public TreeMap<String, String> getCurrentStageList()
  {
    return this.currentStageList;
  }

  public void setCurrentStageList(TreeMap<String, String> currentStageList)
  {
    this.currentStageList = currentStageList;
  }

  public String getCurrentStage()
  {
    return this.currentStage;
  }

  public void setCurrentStage(String currentStage)
  {
    this.currentStage = currentStage;
  }

  public String getApplicationStatus()
  {
    return this.applicationStatus;
  }

  public void setApplicationStatus(String applicationStatus)
  {
    this.applicationStatus = applicationStatus;
  }

  public String getTicketNumber()
  {
    return this.ticketNumber;
  }

  public void setTicketNumber(String ticketNumber)
  {
    this.ticketNumber = ticketNumber;
  }

  public String getInitiatorId()
  {
    return this.initiatorId;
  }

  public void setInitiatorId(String initiatorId)
  {
    this.initiatorId = initiatorId;
  }

  public String getInitiatorToken()
  {
    return this.initiatorToken;
  }

  public void setInitiatorToken(String initiatorToken)
  {
    this.initiatorToken = initiatorToken;
  }

  public String getInitiatorName()
  {
    return this.initiatorName;
  }

  public void setInitiatorName(String initiatorName)
  {
    this.initiatorName = initiatorName;
  }

  public String getPendingWithEmpId()
  {
    return this.pendingWithEmpId;
  }

  public void setPendingWithEmpId(String pendingWithEmpId)
  {
    this.pendingWithEmpId = pendingWithEmpId;
  }

  public String getPendingWithEmpToken()
  {
    return this.pendingWithEmpToken;
  }

  public void setPendingWithEmpToken(String pendingWithEmpToken)
  {
    this.pendingWithEmpToken = pendingWithEmpToken;
  }

  public String getPendingWithEmpName()
  {
    return this.pendingWithEmpName;
  }

  public void setPendingWithEmpName(String pendingWithEmpName)
  {
    this.pendingWithEmpName = pendingWithEmpName;
  }

  public boolean isNodataFlag()
  {
    return this.nodataFlag;
  }

  public void setNodataFlag(boolean nodataFlag)
  {
    this.nodataFlag = nodataFlag;
  }

  public String getMyPage()
  {
    return this.myPage;
  }

  public void setMyPage(String myPage)
  {
    this.myPage = myPage;
  }

  public boolean isBrdSuperUserPagingFlag()
  {
    return this.brdSuperUserPagingFlag;
  }

  public void setBrdSuperUserPagingFlag(boolean brdSuperUserPagingFlag)
  {
    this.brdSuperUserPagingFlag = brdSuperUserPagingFlag;
  }

  public String getCurrentActivityItr()
  {
    return this.currentActivityItr;
  }

  public void setCurrentActivityItr(String currentActivityItr)
  {
    this.currentActivityItr = currentActivityItr;
  }

  public String getForecastedCompletionDateItr()
  {
    return this.forecastedCompletionDateItr;
  }

  public void setForecastedCompletionDateItr(String forecastedCompletionDateItr)
  {
    this.forecastedCompletionDateItr = forecastedCompletionDateItr;
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