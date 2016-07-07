package org.struts.action.PAS.GoalSetting;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.MyTeamGoalRatingReport;
import org.paradyne.model.PAS.GoalSetting.MyTeamGoalRatingReportModel;
import org.struts.lib.ParaActionSupport;

public class MyTeamGoalRatingReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(MyTeamGoalRatingReportAction.class);
  MyTeamGoalRatingReport myTemGaolReport = null;

  public MyTeamGoalRatingReport getMyTemGaolReport() { return this.myTemGaolReport; }

  public void setMyTemGaolReport(MyTeamGoalRatingReport myTemGaolReport)
  {
    this.myTemGaolReport = myTemGaolReport;
  }

  public void prepare_local() throws Exception {
    this.myTemGaolReport = new MyTeamGoalRatingReport();
    this.myTemGaolReport.setMenuCode(5025);
  }

  public Object getModel()
  {
    return this.myTemGaolReport;
  }

  public String genReport()
  {
    try {
      MyTeamGoalRatingReportModel model = new MyTeamGoalRatingReportModel();
      model.initiate(this.context, this.session);
      model.getReport(this.myTemGaolReport, this.response, this.request, "");
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public final String mailReport()
  {
    try {
      MyTeamGoalRatingReportModel model = new MyTeamGoalRatingReportModel();
      model.initiate(this.context, this.session);
      String poolName = String.valueOf(this.session
        .getAttribute("session_pool"));
      if ((!poolName.equals("")) && (poolName != null)) {
        poolName = "/" + poolName;
      }
      String reportPath = getText("data_path") + "/Report/Master" + 
        poolName + "/";
      model.getReport(this.myTemGaolReport, this.response, this.request, reportPath);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "mailReport";
  }

  public String f9employee()
    throws Exception
  {
    try
    {
      String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, EMP_ID  FROM HRMS_EMP_OFFC WHERE  EMP_ID<>" + 
        this.myTemGaolReport.getUserEmpId() + " AND EMP_STATUS='S'" + 
        " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER=" + this.myTemGaolReport.getUserEmpId() + 
        " ORDER BY UPPER(ENAME) ";

      String[] headers = { getMessage("employee.id"), getMessage("employee") };

      String[] headerWidth = { "20", "80" };

      String[] fieldNames = { "empToken", "empName", "empId" };

      int[] columnIndex = { 0, 1, 2 };

      String submitFlag = "false";

      String submitToMethod = "";

      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return "f9page";
  }

  public String f9GoalAction() throws Exception
  {
    String query = "SELECT GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),GOAL_ID FROM HRMS_GOAL_CONFIG  WHERE GOAL_PUBLISH_STATUS =2 ";

    query = query + " ORDER BY GOAL_NAME ";
    String[] headers = { getMessage("goal"), 
      "Goal From Date", "Goal To Date" };
    String[] headerWidth = { "40", "30", "30" };

    String[] fieldNames = { "goalName", "goalFromDate", "goalToDate", 
      "goalId" };

    int[] columnIndex = { 0, 1, 2, 3 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }
}