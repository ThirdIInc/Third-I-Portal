package org.struts.action.PAS.GoalSetting;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.GoalSettingStatusMisReport;
import org.paradyne.model.PAS.GoalSetting.GoalSettingStatusMisReportModel;
import org.struts.lib.ParaActionSupport;

public class GoalSettingStatusMisReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(GoalSettingStatusMisReportAction.class);
  GoalSettingStatusMisReport empGoalReport;

  public void prepare_local()
    throws Exception
  {
    this.empGoalReport = new GoalSettingStatusMisReport();
    this.empGoalReport.setMenuCode(5026);
  }

  public Object getModel()
  {
    return this.empGoalReport;
  }

  public String genReport() {
    GoalSettingStatusMisReportModel model = new GoalSettingStatusMisReportModel();
    model.initiate(this.context, this.session);
    logger.info("ShowGoalSettingReport=" + this.empGoalReport.getShowGoalSettingReport());
    logger.info("ShowGoalSettingselfReport==" + this.empGoalReport.getShowGoalSettingselfReport());
    logger.info("ShowGoalSettingManagerReport==" + this.empGoalReport.getShowGoalSettingManagerReport());
    logger.info("showPendingEmployee==" + this.empGoalReport.getShowPendingEmployee());

    logger.info("ShowGoalAssesmentReport==" + this.empGoalReport.getShowGoalAssesmentReport());
    logger.info("ShowGoalAssesmentSelfReport==" + this.empGoalReport.getShowGoalAssesmentSelfReport());
    logger.info("ShowGoalAssesmentManagerReport==" + this.empGoalReport.getShowGoalAssesmentManagerReport());
    model.genReport(this.empGoalReport, this.response, this.request, "");
    model.terminate();
    return null;
  }

  public String f9Division() {
    try {
      String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION WHERE IS_ACTIVE='Y'";
      if ((this.empGoalReport.getUserProfileDivision() != null) && (this.empGoalReport.getUserProfileDivision().length() > 0)) {
        query = query + " WHERE DIV_ID IN(" + this.empGoalReport.getUserProfileDivision() + ") ";
      }
      query = query + " ORDER BY UPPER(DIV_NAME) ";

      String[] headers = { getMessage("division") };

      String[] headerWidth = { "100" };

      String[] fieldNames = { "divisionName", "divisionId" };

      int[] columnIndex = { 0, 1 };

      String submitFlag = "false";

      String submitToMethod = "";

      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

      return "f9page";
    } catch (Exception e) {
      logger.error("Exception in f9Division in action:" + e);
    }return "";
  }

  public String f9GoalAction() throws Exception
  {
    String query = "SELECT GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),GOAL_ID FROM HRMS_GOAL_CONFIG  WHERE GOAL_PUBLISH_STATUS =2 ";

    query = query + " ORDER BY GOAL_ID ";
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
  public String f9Branch() {
    try {
      String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER WHERE IS_ACTIVE='Y' ORDER BY UPPER(CENTER_NAME) ";

      String[] headers = { getMessage("branch") };

      String[] headerWidth = { "100" };

      String[] fieldNames = { "branchName", "branchId" };

      int[] columnIndex = { 0, 1 };

      String submitFlag = "false";

      String submitToMethod = "";

      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

      return "f9page";
    } catch (Exception e) {
      logger.error("Exception in f9Branch in action:" + e);
    }return "";
  }

  public String f9employee()
    throws Exception
  {
    try
    {
      String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'  ORDER BY UPPER(ENAME) ";

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
  public String f9Dept() {
    try {
      String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT WHERE IS_ACTIVE='Y' ORDER BY UPPER(DEPT_NAME) ";

      String[] headers = { getMessage("department") };

      String[] headerWidth = { "100" };

      String[] fieldNames = { "deptName", "deptId" };

      int[] columnIndex = { 0, 1 };

      String submitFlag = "false";

      String submitToMethod = "";

      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

      return "f9page";
    } catch (Exception e) {
      logger.error("Exception in f9Department in action:" + e);
    }return "";
  }

  public String f9Desg() {
    try {
      String query = " SELECT RANK_NAME, RANK_ID FROM HRMS_RANK WHERE IS_ACTIVE='Y' ORDER BY UPPER(RANK_NAME) ";

      String[] headers = { getMessage("designation") };

      String[] headerWidth = { "100" };

      String[] fieldNames = { "desgName", "desgId" };

      int[] columnIndex = { 0, 1 };

      String submitFlag = "false";

      String submitToMethod = "";

      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

      return "f9page";
    } catch (Exception e) {
      logger.error("Exception in f9Department in action:" + e);
    }return "";
  }

  public final String mailReport()
  {
    try {
      GoalSettingStatusMisReportModel model = new GoalSettingStatusMisReportModel();
      model.initiate(this.context, this.session);
      String poolName = String.valueOf(this.session
        .getAttribute("session_pool"));
      if ((!poolName.equals("")) && (poolName != null)) {
        poolName = "/" + poolName;
      }
      String reportPath = getText("data_path") + "/Report/Master" + 
        poolName + "/";
      model.genReport(this.empGoalReport, this.response, this.request, reportPath);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "mailReport";
  }
  public GoalSettingStatusMisReport getEmpGoalReport() {
    return this.empGoalReport;
  }

  public void setEmpGoalReport(GoalSettingStatusMisReport empGoalReport) {
    this.empGoalReport = empGoalReport;
  }
}