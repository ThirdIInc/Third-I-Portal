package org.struts.action.PAS.Competency;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompDevPlanMisReport;
import org.paradyne.model.PAS.Competency.CompDevPlanMisReportModel;
import org.struts.lib.ParaActionSupport;

public class CompDevPlanMisReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(CompDevPlanMisReportAction.class);
  CompDevPlanMisReport compDevPlanReport;

  public void prepare_local()
    throws Exception
  {
    this.compDevPlanReport = new CompDevPlanMisReport();
    this.compDevPlanReport.setMenuCode(5021);
  }

  public Object getModel()
  {
    return this.compDevPlanReport;
  }

  public String genReport() {
    CompDevPlanMisReportModel model = new CompDevPlanMisReportModel();
    model.initiate(this.context, this.session);
    model.genReport(this.compDevPlanReport, this.response, this.request, "");
    model.terminate();
    return null;
  }

  public String f9CompAction()
    throws Exception
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

  public String f9devPlan()
    throws Exception
  {
    try
    {
      String query = " SELECT COMP_DEV_PLAN,COMP_DEV_CODE  FROM HRMS_COMPETENCY_DEV_PLAN  ORDER BY COMP_DEV_CODE ";

      String[] headers = { getMessage("devPlan") };

      String[] headerWidth = { "50" };

      String[] fieldNames = { "devPlan", "devPlanId" };

      int[] columnIndex = { 0, 1 };

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

  public final String mailReport()
  {
    try
    {
      CompDevPlanMisReportModel model = new CompDevPlanMisReportModel();
      model.initiate(this.context, this.session);
      String poolName = String.valueOf(this.session
        .getAttribute("session_pool"));
      if ((!poolName.equals("")) && (poolName != null)) {
        poolName = "\\" + poolName;
      }
      String reportPath = getText("data_path") + "\\Report\\Master" + 
        poolName + "\\";
      model.genReport(this.compDevPlanReport, this.response, this.request, reportPath);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "mailReport";
  }

  public CompDevPlanMisReport getCompDevPlanReport() {
    return this.compDevPlanReport;
  }

  public void setCompDevPlanReport(CompDevPlanMisReport compDevPlanReport) {
    this.compDevPlanReport = compDevPlanReport;
  }

  public String f9Division() {
    try {
      String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
      if ((this.compDevPlanReport.getUserProfileDivision() != null) && (this.compDevPlanReport.getUserProfileDivision().length() > 0)) {
        query = query + " WHERE DIV_ID IN(" + this.compDevPlanReport.getUserProfileDivision() + ") ";
      }
      query = query + " ORDER BY UPPER(DIV_NAME) ";

      String[] headers = { getMessage("divisionName") };

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
}