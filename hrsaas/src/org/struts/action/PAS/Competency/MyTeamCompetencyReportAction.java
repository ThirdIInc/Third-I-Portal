package org.struts.action.PAS.Competency;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.MyTeamCompetencyReport;
import org.paradyne.model.PAS.Competency.MyTeamCompetencyReportModel;
import org.struts.lib.ParaActionSupport;

public class MyTeamCompetencyReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(MyTeamCompetencyReportAction.class);
  MyTeamCompetencyReport myTeamCompReport;

  public void prepare_local()
    throws Exception
  {
    this.myTeamCompReport = new MyTeamCompetencyReport();
    this.myTeamCompReport.setMenuCode(5024);
  }

  public Object getModel()
  {
    return this.myTeamCompReport;
  }

  public MyTeamCompetencyReport getMyTeamCompReport() {
    return this.myTeamCompReport;
  }

  public void setMyTeamCompReport(MyTeamCompetencyReport myTeamCompReport) {
    this.myTeamCompReport = myTeamCompReport;
  }

  public String genReport()
  {
    MyTeamCompetencyReportModel model = new MyTeamCompetencyReportModel();
    model.initiate(this.context, this.session);
    model.getReport(this.myTeamCompReport, this.response, this.request, "");
    model.terminate();
    return null;
  }

  public final String mailReport()
  {
    try
    {
      MyTeamCompetencyReportModel model = new MyTeamCompetencyReportModel();
      model.initiate(this.context, this.session);
      String poolName = String.valueOf(this.session
        .getAttribute("session_pool"));
      if ((!poolName.equals("")) && (poolName != null)) {
        poolName = "/" + poolName;
      }
      String reportPath = getText("data_path") + "/Report/Master" + 
        poolName + "/";
      model.getReport(this.myTeamCompReport, this.response, this.request, reportPath);
      model.terminate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "mailReport";
  }

  public String f9employee()
    throws Exception
  {
    try
    {
      String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,  EMP_ID FROM HRMS_EMP_OFFC where  EMP_ID IN (SELECT HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_ID<>" + 
        this.myTeamCompReport.getUserEmpId() + " " + 
        "CONNECT BY PRIOR  HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_OFFC.EMP_REPORTING_OFFICER " + 
        "START WITH HRMS_EMP_OFFC.EMP_ID=" + this.myTeamCompReport.getUserEmpId() + " ) " + 
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

  public String f9CompAction() throws Exception
  {
    String query = "SELECT COMP_NAME,TO_CHAR(COMP_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(COMP_TO_DATE,'DD-MM-YYYY'),COMP_ID FROM HRMS_COMP_CONFIG WHERE COMP_PUBLISH_STATUS=2 ORDER BY COMP_ID ";

    String[] headers = { "Competency", 
      "Competency From Date", "Competency To Date" };
    String[] headerWidth = { "40", "30", "30" };

    String[] fieldNames = { "compName", "compFromDate", "compToDate", 
      "compId" };

    int[] columnIndex = { 0, 1, 2, 3 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }
}