package org.struts.action.PAS.Competency;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.RoleWiseCompMisReport;
import org.paradyne.model.PAS.Competency.RoleWiseCompMisReportModel;
import org.struts.lib.ParaActionSupport;

public class RoleWiseCompMisReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(RoleWiseCompMisReportAction.class);
  RoleWiseCompMisReport roleWiseCompReport;

  public void prepare_local()
    throws Exception
  {
    this.roleWiseCompReport = new RoleWiseCompMisReport();
    this.roleWiseCompReport.setMenuCode(5022);
  }

  public Object getModel()
  {
    return this.roleWiseCompReport;
  }

  public RoleWiseCompMisReport getRoleWiseCompReport() {
    return this.roleWiseCompReport;
  }

  public void setRoleWiseCompReport(RoleWiseCompMisReport roleWiseCompReport) {
    this.roleWiseCompReport = roleWiseCompReport;
  }

  public String f9CompAction() throws Exception
  {
    String query = "SELECT COMPETENCY_TITLE,COMPETENCY_CODE from HRMS_COMPETENCY_HDR ORDER BY HRMS_COMPETENCY_HDR.COMPETENCY_CODE ";

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

  public String f9Rank() throws Exception {
    String query = "SELECT RANK_NAME,RANK_ID FROM HRMS_RANK ORDER BY RANK_ID ";

    String[] headers = { getMessage("desgName") };
    String[] headerWidth = { "40" };

    String[] fieldNames = { "role", "roleId" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String f9Catagory() throws Exception {
    String query = "SELECT COMP_CATEGORY ,COMP_CATEGORY_ID FROM HRMS_COMP_CATEGORIES ORDER BY COMP_CATEGORY_ID ";

    String[] headers = { getMessage("compCatagory") };
    String[] headerWidth = { "40" };

    String[] fieldNames = { "compCatagory", "compCatcode" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }
  public String genReport() {
    RoleWiseCompMisReportModel model = new RoleWiseCompMisReportModel();
    model.initiate(this.context, this.session);
    model.genReport(this.roleWiseCompReport, this.response, this.request, "");
    model.terminate();
    return null;
  }

  public final String mailReport() {
    try {
      RoleWiseCompMisReportModel model = new RoleWiseCompMisReportModel();
      model.initiate(this.context, this.session);
      String poolName = String.valueOf(this.session
        .getAttribute("session_pool"));
      if ((!poolName.equals("")) && (poolName != null)) {
        poolName = "/" + poolName;
      }
      String reportPath = getText("data_path") + "/Report/Master" + 
        poolName + "/";
      model.genReport(this.roleWiseCompReport, this.response, this.request, reportPath);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "mailReport";
  }
}