package org.struts.action.PAS.GoalSetting;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.GoalAssesMentReport;
import org.paradyne.model.PAS.GoalSetting.GoalAssesmentReportModel;
import org.struts.lib.ParaActionSupport;

public class GoalAssesmentReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(GoalAssesmentReportAction.class);
  GoalAssesMentReport goalAssesReport;

  public void prepare_local()
    throws Exception
  {
    this.goalAssesReport = new GoalAssesMentReport();
    this.goalAssesReport.setMenuCode(5012);
  }

  public Object getModel()
  {
    return this.goalAssesReport;
  }

  public String genReport() {
    GoalAssesmentReportModel model = new GoalAssesmentReportModel();
    model.initiate(this.context, this.session);
    model.genReport(this.goalAssesReport, this.response, this.request, "");
    model.terminate();
    return null;
  }

  public String f9GoalAction()
    throws Exception
  {
	  
    String query =" SELECT GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),"
    		+" TO_CHAR(HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'),HRMS_GOAL_CONFIG.GOAL_ID" 
    		+" FROM HRMS_GOAL_CONFIG"  
    		+" LEFT JOIN HRMS_GOAL_ASSESSMENT_CONFIG on(HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ID)"
    		+" WHERE GOAL_PUBLISH_STATUS =2 "
    		+" ORDER BY GOAL_ID DESC";
    String[] headers = { getMessage("goal"), 
      "Goal From Date", "Goal To Date" ,getMessage("assesmentDate")};
    String[] headerWidth = { "25", "25", "25","25 "};

    String[] fieldNames = { "goalName", "goalFromDate", "goalToDate","assesmentDate", "goalId"};

    int[] columnIndex = { 0, 1, 2, 3 ,4};

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
      String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,  EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' ORDER BY UPPER(ENAME) ";

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

  public GoalAssesMentReport getGoalAssesReport()
  {
    return this.goalAssesReport;
  }

  public void setGoalAssesReport(GoalAssesMentReport goalAssesReport) {
    this.goalAssesReport = goalAssesReport;
  }

  public final String mailReport()
  {
    try
    {
      GoalAssesmentReportModel model = new GoalAssesmentReportModel();
      model.initiate(this.context, this.session);
      String poolName = String.valueOf(this.session
        .getAttribute("session_pool"));
      if ((!poolName.equals("")) && (poolName != null)) {
        poolName = "/" + poolName;
      }
      String reportPath = getText("data_path") + "/Report/Master" + 
        poolName + "/";
      model.genReport(this.goalAssesReport, this.response, this.request, reportPath);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "mailReport";
  }
  
  
}