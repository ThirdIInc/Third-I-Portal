package org.struts.action.PAS.Competency;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompetencyStatusMisReport;
import org.paradyne.model.PAS.Competency.CompetencyStatusMisReportModel;
import org.struts.action.PAS.GoalSetting.GoalSettingStatusMisReportAction;
import org.struts.lib.ParaActionSupport;

public class CompetencyStatusMisReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(GoalSettingStatusMisReportAction.class);
  CompetencyStatusMisReport empGoalReport;

  public void prepare_local()
    throws Exception
  {
    this.empGoalReport = new CompetencyStatusMisReport();
    this.empGoalReport.setMenuCode(5033);
  }

  public Object getModel()
  {
    return this.empGoalReport;
  }

  public String genReport() {
    CompetencyStatusMisReportModel model = new CompetencyStatusMisReportModel();
    model.initiate(this.context, this.session);
    logger.info("ShowCompAssesmentSelfReport==" + this.empGoalReport.getShowCompAssesmentSelfReport());
    logger.info("ShowCompAssesmentManagerReport==" + this.empGoalReport.getShowCompAssesmentManagerReport());
    model.genReport(this.empGoalReport, this.response, this.request, "");
    model.terminate();
    return null;
  }

  public String f9Division() {
    try {
      String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION WHERE IS_ACTIVE='Y' ";
      if ((this.empGoalReport.getUserProfileDivision() != null) && (this.empGoalReport.getUserProfileDivision().length() > 0)) {
        query = query + " AND DIV_ID IN(" + this.empGoalReport.getUserProfileDivision() + ") ";
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

  public String f9CompAction() throws Exception
  {
    String query = "SELECT COMP_NAME,TO_CHAR(COMP_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(COMP_TO_DATE,'DD-MM-YYYY'),COMP_ID FROM HRMS_COMP_CONFIG  WHERE COMP_PUBLISH_STATUS =2 ";

    query = query + " ORDER BY COMP_ID ";
    String[] headers = { getMessage("compName"), 
      getMessage("compFromDate"), getMessage("compToDate") };
    String[] headerWidth = { "40", "30", "30" };

    String[] fieldNames = { "compName", "compFrmDate", "compToDate", 
      "compId" };

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
      String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,  EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'   ORDER BY UPPER(ENAME) ";

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
      CompetencyStatusMisReportModel model = new CompetencyStatusMisReportModel();
      model.initiate(this.context, this.session);
      String poolName = String.valueOf(this.session
        .getAttribute("session_pool"));
      if ((!poolName.equals("")) && (poolName != null)) {
        poolName = "\\" + poolName;
      }
      String reportPath = getText("data_path") + "\\Report\\Master" + 
        poolName + "\\";
      model.genReport(this.empGoalReport, this.response, this.request, reportPath);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "mailReport";
  }
  public CompetencyStatusMisReport getEmpGoalReport() {
    return this.empGoalReport;
  }

  public void setEmpGoalReport(CompetencyStatusMisReport empGoalReport) {
    this.empGoalReport = empGoalReport;
  }
}