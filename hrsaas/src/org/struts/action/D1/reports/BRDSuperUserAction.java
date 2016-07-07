package org.struts.action.D1.reports;

import org.apache.log4j.Logger;
import org.paradyne.bean.D1.reports.BRDSuperUser;
import org.paradyne.model.D1.reports.BRDSuperUserModel;
import org.struts.lib.ParaActionSupport;

public class BRDSuperUserAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(BRDSuperUserAction.class);
  BRDSuperUser brdSuperUserBean = null;

  public void prepare_local()
    throws Exception
  {
    this.brdSuperUserBean = new BRDSuperUser();
    this.brdSuperUserBean.setMenuCode(5014);
  }

  public Object getModel()
  {
    return this.brdSuperUserBean;
  }

  public BRDSuperUser getBrdSuperUserBean()
  {
    return this.brdSuperUserBean;
  }

  public void setBrdSuperUserBean(BRDSuperUser brdSuperUserBean)
  {
    this.brdSuperUserBean = brdSuperUserBean;
  }

  public String input()
  {
    try
    {
      BRDSuperUserModel model = new BRDSuperUserModel();
      model.initiate(this.context, this.session);
      model.brdCurrentStage(this.brdSuperUserBean);
      model.viewBrdApplicationList(this.brdSuperUserBean, this.request);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "input";
  }

  public String reset()
  {
    try
    {
      this.brdSuperUserBean.setCurrentStage("-1");
      this.brdSuperUserBean.setApplicationStatus("-1");
      this.brdSuperUserBean.setTicketNumber("");
      this.brdSuperUserBean.setInitiatorId("");
      this.brdSuperUserBean.setInitiatorToken("");
      this.brdSuperUserBean.setInitiatorName("");
      this.brdSuperUserBean.setPendingWithEmpId("");
      this.brdSuperUserBean.setPendingWithEmpToken("");
      this.brdSuperUserBean.setPendingWithEmpName("");
      this.brdSuperUserBean.setFromDate("");
      this.brdSuperUserBean.setToDate("");
    } catch (Exception e) {
      e.printStackTrace();
    }
    input();
    return "input";
  }

  public String viewBrdApplicationList()
  {
    try
    {
      BRDSuperUserModel model = new BRDSuperUserModel();
      model.initiate(this.context, this.session);
      model.viewBrdApplicationList(this.brdSuperUserBean, this.request);
      model.brdCurrentStage(this.brdSuperUserBean);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String f9TicketNumber()
  {
    String query = "SELECT HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO FROM HRMS_D1_BUSINESS_REQUIREMENT";
    String[] headers = { "Ticket Number" };
    String[] headerWidth = { "100" };
    String[] fieldNames = { "ticketNumber" };
    int[] columnIndex = new int[1];
    String submitFlag = "false";
    String submitToMethod = "";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
    return "f9page";
  }

  public String f9Initiator()
  {
    String query = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID  FROM HRMS_D1_BUSINESS_REQUIREMENT  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_BY) ";

    String[] fieldNames = { "initiatorToken", "initiatorName", "initiatorId" };
    int[] columnIndex = { 0, 1, 2 };

    String[] headers = { "Initiator Id", "Initiator Name" };

    String[] headerwidth = { "30", "70" };

    String submitFlage = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerwidth, fieldNames, columnIndex, 
      submitFlage, submitToMethod);

    return "f9page";
  }

  public String f9PendingWithEmploye()
  {
    String query = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S'";

    String[] fieldNames = { "pendingWithEmpToken", "pendingWithEmpName", "pendingWithEmpId" };
    int[] columnIndex = { 0, 1, 2 };

    String[] headers = { "Employee Id", "Employee Name" };

    String[] headerwidth = { "30", "70" };

    String submitFlage = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerwidth, fieldNames, columnIndex, 
      submitFlage, submitToMethod);

    return "f9page";
  }
}