package org.struts.action.PAS.Competency;

import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompetencyDevelopmentPlan;
import org.paradyne.model.PAS.Competency.CompetencyDevelopmentPlanModel;
import org.struts.lib.ParaActionSupport;

public class CompetencyDevelopmentPlanAction extends ParaActionSupport
{
  CompetencyDevelopmentPlan bean = null;

  static Logger logger = Logger.getLogger(ParaActionSupport.class);

  public CompetencyDevelopmentPlan getBean()
  {
    return this.bean;
  }

  public void setBean(CompetencyDevelopmentPlan bean) {
    this.bean = bean;
  }

  public Object getModel()
  {
    return this.bean;
  }

  public String input()
    throws Exception
  {
    CompetencyDevelopmentPlanModel model = new CompetencyDevelopmentPlanModel();
    model.initiate(this.context, this.session);
    model.hasData1(this.bean, this.request);
    getNavigationPanel(1);
    model.terminate();

    return "input";
  }
  public String addNew() {
    try {
      reset();
      getNavigationPanel(2);
      return "success";
    } catch (Exception e) {
      logger.error("Exception in addNew in action:" + e);
    }return null;
  }

  public String cancel()
  {
    try {
      CompetencyDevelopmentPlanModel model = new CompetencyDevelopmentPlanModel();
      model.initiate(this.context, this.session);
      reset();
      model.hasData1(this.bean, this.request);
      getNavigationPanel(1);
      model.terminate();
      return "input";
    } catch (Exception e) {
      logger.error("Exception in addNew in action:" + e);
    }return null;
  }

  public void prepare_local() throws Exception {
    this.bean = new CompetencyDevelopmentPlan();
    this.bean.setMenuCode(5018);
  }

  public void prepare_withLoginProfileDetails()
    throws Exception
  {
  }

  public String callPage()
    throws Exception
  {
    CompetencyDevelopmentPlanModel model = new CompetencyDevelopmentPlanModel();
    model.initiate(this.context, this.session);
    model.hasData1(this.bean, this.request);
    getNavigationPanel(1);
    model.terminate();
    return "input";
  }

  public String calforedit()
    throws Exception
  {
    CompetencyDevelopmentPlanModel model = new CompetencyDevelopmentPlanModel();
    model.initiate(this.context, this.session);
    model.calforedit(this.bean);

    getNavigationPanel(3);
    this.bean.setEnableAll("N");
    model.terminate();
    return "success";
  }

  public String delete()
    throws Exception
  {
    CompetencyDevelopmentPlanModel model = new CompetencyDevelopmentPlanModel();
    model.initiate(this.context, this.session);
    boolean result = model.deleteCompDevSelected(this.bean);
    model.hasData1(this.bean, this.request);
    model.terminate();
    if (result) {
      addActionMessage(getMessage("delete"));
    }
    else {
      addActionMessage(getMessage("del.error"));
    }

    getNavigationPanel(1);
    return "input";
  }

  public String delete1()
    throws Exception
  {
    String[] code = this.request.getParameterValues("hdeleteCode");

    CompetencyDevelopmentPlanModel model = new CompetencyDevelopmentPlanModel();

    model.initiate(this.context, this.session);
    boolean result = model.deleteCompDev(this.bean, code);
    if (result) {
      addActionMessage(getMessage("delete"));
    }
    else {
      addActionMessage(getMessage("multiple.del.error"));
    }

    model.hasData1(this.bean, this.request);
    getNavigationPanel(1);
    model.terminate();

    return "input";
  }

  public String reset()
    throws Exception
  {
    try
    {
      this.bean.setCompDevCode("");
      this.bean.setCompDevName("");
      this.bean.setCompDevDesc("");

      getNavigationPanel(2);
    }
    catch (Exception e) {
      logger.error("Error in reset" + e);
    }
    return "success";
  }

  public String save()
    throws Exception
  {
    CompetencyDevelopmentPlanModel model = new CompetencyDevelopmentPlanModel();
    model.initiate(this.context, this.session);
    System.out.println("############## IN SAVE METHOD #############");

    if (this.bean.getCompDevCode().equals("")) {
      boolean result = model.addCompDev(this.bean);
      if (result) {
        addActionMessage(getMessage("save"));
        getNavigationPanel(3);
        return "success";
      }

      addActionMessage(getMessage("duplicate"));

      getNavigationPanel(2);
      this.bean.setEnableAll("Y");
      return "success";
    }

    boolean result = model.modCompDev(this.bean);
    if (result) {
      addActionMessage(getMessage("update"));
      getNavigationPanel(3);
      return "success";
    }

    addActionMessage(getMessage("duplicate"));

    getNavigationPanel(2);
    this.bean.setEnableAll("Y");
    return "success";
  }

  public String data() throws Exception
  {
    CompetencyDevelopmentPlanModel model = new CompetencyDevelopmentPlanModel();
    model.initiate(this.context, this.session);
    model.data(this.bean, this.request);
    getNavigationPanel(3);
    model.terminate();
    return "success";
  }

  public String f9action() throws Exception {
    String query = " SELECT  HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_PLAN, HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_DESC, HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE FROM HRMS_COMPETENCY_DEV_PLAN ORDER BY COMP_DEV_CODE DESC ";
    String[] headers = { getMessage("development.plan"), getMessage("define.development.desc") };
    String[] headerWidth = { "50", "50" };
    String[] fieldNames = { "compDevName", "compDevDesc", "compDevCode" };
    int[] columnIndex = { 0, 1, 2 };
    String submitFlag = "true";
    String submitToMethod = "CompetencyDevelopmentPlan_data.action";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }
}