package org.struts.action.PAS.Competency;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.EmpCompitencyReport;
import org.paradyne.model.PAS.Competency.EmpCompitencyReportModel;
import org.struts.lib.ParaActionSupport;

public class EmpCompitencyReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(EmpCompitencyReportAction.class);
  EmpCompitencyReport empCompReport;

  public void prepare_local()
    throws Exception
  {
    this.empCompReport = new EmpCompitencyReport();
    this.empCompReport.setMenuCode(5027);
  }

  public EmpCompitencyReport getEmpCompReport()
  {
    return this.empCompReport;
  }

  public void setEmpCompReport(EmpCompitencyReport empCompReport) {
    this.empCompReport = empCompReport;
  }

  public Object getModel()
  {
    return this.empCompReport;
  }

  public String f9CompAction()
    throws Exception
  {
    String query = "SELECT  COMP_NAME,COMP_ID FROM HRMS_COMP_CONFIG WHERE COMP_PUBLISH_STATUS=2 ORDER BY HRMS_COMP_CONFIG.COMP_ID ";

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

  public String genReport() {
    EmpCompitencyReportModel model = new EmpCompitencyReportModel();
    model.initiate(this.context, this.session);
    model.genReport(this.empCompReport, this.response, this.request, "");
    model.terminate();
    return null;
  }

  public final String mailReport() {
    try {
      EmpCompitencyReportModel model = new EmpCompitencyReportModel();
      model.initiate(this.context, this.session);
      String poolName = String.valueOf(this.session
        .getAttribute("session_pool"));
      if ((!poolName.equals("")) && (poolName != null)) {
        poolName = "\\" + poolName;
      }
      String reportPath = getText("data_path") + "\\Report\\Master" + 
        poolName + "\\";
      model.genReport(this.empCompReport, this.response, this.request, reportPath);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "mailReport";
  }
}