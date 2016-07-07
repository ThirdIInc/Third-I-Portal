package org.struts.action.PAS.Competency;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.MyTeamCompDetReport;
import org.paradyne.model.PAS.Competency.MyTeamCompDetReportModel;
import org.struts.lib.ParaActionSupport;

public class MyTeamCompDetReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(MyTeamCompetencyReportAction.class);
  MyTeamCompDetReport myTeamCompReport;

  public void prepare_local()
    throws Exception
  {
    this.myTeamCompReport = new MyTeamCompDetReport();
    this.myTeamCompReport.setMenuCode(5028);
  }

  public Object getModel()
  {
    return this.myTeamCompReport;
  }

  public MyTeamCompDetReport getMyTeamCompReport() {
    return this.myTeamCompReport;
  }

  public void setMyTeamCompReport(MyTeamCompDetReport myTeamCompReport) {
    this.myTeamCompReport = myTeamCompReport;
  }

  public String genReport()
  {
    MyTeamCompDetReportModel model = new MyTeamCompDetReportModel();
    model.initiate(this.context, this.session);
    model.genReport(this.myTeamCompReport, this.response, this.request, "");
    model.terminate();
    return null;
  }

  public final String mailReport()
  {
    try
    {
      MyTeamCompDetReportModel model = new MyTeamCompDetReportModel();
      model.initiate(this.context, this.session);
      String poolName = String.valueOf(this.session
        .getAttribute("session_pool"));
      if ((!poolName.equals("")) && (poolName != null)) {
        poolName = "\\" + poolName;
      }
      String reportPath = getText("data_path") + "\\Report\\Master" + 
        poolName + "\\";
      model.genReport(this.myTeamCompReport, this.response, this.request, reportPath);
      model.terminate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "mailReport";
  }

  public String f9CompAction()
    throws Exception
  {
    String query = "SELECT  COMP_NAME,COMP_ID FROM HRMS_COMP_CONFIG ORDER BY HRMS_COMP_CONFIG.COMP_ID ";

    String[] headers = { getMessage("compName") };
    String[] headerWidth = { "40" };

    String[] fieldNames = { "compName", "compId" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
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

  public String f9Division()
  {
    try {
      String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
      if ((this.myTeamCompReport.getUserProfileDivision() != null) && (this.myTeamCompReport.getUserProfileDivision().length() > 0)) {
        query = query + " WHERE DIV_ID IN(" + this.myTeamCompReport.getUserProfileDivision() + ") ";
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