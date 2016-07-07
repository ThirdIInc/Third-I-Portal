package org.struts.action.PAS.GoalSetting;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.EmpGoalReport;
import org.paradyne.lib.SqlModel;
import org.paradyne.model.PAS.GoalSetting.EmpGoalReportModel;
import org.struts.lib.ParaActionSupport;

public class EmpGoalReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(EmpGoalReportAction.class);
  EmpGoalReport empGoalReport;

  public void prepare_local()
    throws Exception
  {
    this.empGoalReport = new EmpGoalReport();
    this.empGoalReport.setMenuCode(2005);
  }

  public Object getModel()
  {
    return this.empGoalReport;
  }

  public String genReport() {
    EmpGoalReportModel model = new EmpGoalReportModel();
    model.initiate(this.context, this.session);
    logger.info("showSelfAssessment==" + this.empGoalReport.getShowSelfAssessment());
    logger.info("showManagerAssessment==" + this.empGoalReport.getShowManagerAssessment());
    logger.info("showPendingEmployee==" + this.empGoalReport.getShowPendingEmployee());
    model.genReport(this.empGoalReport, this.response, this.request, "");
    model.terminate();
    return null;
  }

  public String f9Division() {
    try {
      String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
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

    query = query + " ORDER BY GOAL_ID DESC ";
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
      String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

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

  public String f9employee() throws Exception
  {
    try
    {
      EmpGoalReportModel model = new EmpGoalReportModel();
      model.initiate(this.context, this.session);
      String streligibleFlag = "N";
      String query = "";
      String eligibleFlag = "SELECT GOAL_ELIGIBLE_FLAG FROM HRMS_GOAL_CONFIG WHERE GOAL_ID=" + this.empGoalReport.getGoalId();
      Object[][] eligibleFlagObj = model.getSqlModel().getSingleResult(eligibleFlag);
      if ((eligibleFlagObj != null) && (eligibleFlagObj.length > 0)) {
        streligibleFlag = String.valueOf(eligibleFlagObj[0][0]);
      }
      if (streligibleFlag.equals("Y")) {
        query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,  EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'  ORDER BY UPPER(ENAME) ";
      }
      else
      {
        query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,  HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC INNER JOIN HRMS_GOAL_ELIGIBLE_EMP ON(HRMS_GOAL_ELIGIBLE_EMP.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND HRMS_GOAL_ELIGIBLE_EMP.GOAL_ID=" + 
          this.empGoalReport.getGoalId() + ") WHERE EMP_STATUS='S'" + 
          " ORDER BY UPPER(ENAME) ";
      }

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
      String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

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
      String query = " SELECT RANK_NAME, RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME) ";

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

  public EmpGoalReport getEmpGoalReport()
  {
    return this.empGoalReport;
  }

  public void setEmpGoalReport(EmpGoalReport empGoalReport) {
    this.empGoalReport = empGoalReport;
  }

  public final String mailReport()
  {
    try
    {
      EmpGoalReportModel model = new EmpGoalReportModel();
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
  
  public String f9DateAction()
  throws Exception
{
  String query = "SELECT  TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_ASSESSMENT_CONFIG WHERE GOAL_ID=" + empGoalReport.getGoalId();
  query = query + " ORDER BY GOAL_ASSESSMENT_DATE DESC";
  String[] headers = { getMessage("assesmentDate")};
  String[] headerWidth = { "40"};
  String[] fieldNames = { "assesmentDate"};
  int[] columnIndex = {0};
  String submitFlag = "false";
  String submitToMethod = "";
  setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
    submitFlag, submitToMethod);

  return "f9page";
}
}