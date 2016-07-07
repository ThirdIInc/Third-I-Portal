package org.struts.action.PAS.GoalSetting;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.MyTeamGoalReport;
import org.paradyne.model.PAS.GoalSetting.MyTeamGoalReportModel;
import org.struts.lib.ParaActionSupport;

public class MyTeamGoalReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(MyTeamGoalReportAction.class);
  MyTeamGoalReport teamGoalReport;

  public void prepare_local()
    throws Exception
  {
    this.teamGoalReport = new MyTeamGoalReport();
    this.teamGoalReport.setMenuCode(5010);
  }

  public Object getModel()
  {
    return this.teamGoalReport;
  }

  public String genReport() {
    MyTeamGoalReportModel model = new MyTeamGoalReportModel();
    model.initiate(this.context, this.session);
    model.genReport(this.teamGoalReport, this.response, this.request, "");
    model.terminate();
    return null;
  }

  public String f9GoalAction()
    throws Exception
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

  public String f9employee()
    throws Exception
  {
    try
    {
      String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, EMP_ID  FROM HRMS_EMP_OFFC WHERE  EMP_ID<>" + 
        this.teamGoalReport.getUserEmpId() + " AND EMP_STATUS='S'" + 
        " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER=" + this.teamGoalReport.getUserEmpId() + 
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

  public MyTeamGoalReport getTeamGoalReport()
  {
    return this.teamGoalReport;
  }

  public void setTeamGoalReport(MyTeamGoalReport teamGoalReport) {
    this.teamGoalReport = teamGoalReport;
  }

  public final String mailReport()
  {
    try
    {
      MyTeamGoalReportModel model = new MyTeamGoalReportModel();
      model.initiate(this.context, this.session);
      String poolName = String.valueOf(this.session
        .getAttribute("session_pool"));
      if ((!poolName.equals("")) && (poolName != null)) {
        poolName = "/" + poolName;
      }
      String reportPath = getText("data_path") + "/Report/Master" + 
        poolName + "/";
      model.genReport(this.teamGoalReport, this.response, this.request, reportPath);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "mailReport";
  }
}