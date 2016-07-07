package org.struts.action.PAS.Competency;

import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompetencyMaster;
import org.paradyne.model.PAS.Competency.CompetencyMasterModel;
import org.struts.lib.ParaActionSupport;

public class CompetencyMasterAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(CompetencyMasterAction.class);
  CompetencyMaster competency;

  public void prepare_local()
    throws Exception
  {
    this.competency = new CompetencyMaster();
    this.competency.setMenuCode(2259);
  }

  public Object getModel()
  {
    return this.competency;
  }

  public CompetencyMaster getCompetency() {
    return this.competency;
  }

  public void setCompetency(CompetencyMaster competency) {
    this.competency = competency;
  }

  public String input() throws Exception {
    CompetencyMasterModel model = new CompetencyMasterModel();
    model.initiate(this.context, this.session);
    model.competencyData(this.competency, this.request);
    model.terminate();
    getNavigationPanel(1);
    return "input";
  }

  public String f9action()
    throws Exception
  {
    String query = " SELECT COMPETENCY_TITLE, COMPETENCY_DESC,  COMPETENCY_CODE FROM HRMS_COMPETENCY_HDR  ORDER BY COMPETENCY_CODE ";

    String[] headers = { getMessage("competencyTitle"), 
      getMessage("competencyDesc") };

    String[] headerWidth = { "33", "33" };

    String[] fieldNames = { "competencyTitle", "competencyDesc", "competencyCode" };

    int[] columnIndex = { 0, 1, 2 };

    String submitFlag = "true";

    String submitToMethod = "CompetencyMaster_showRecordData.action";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }
  public String showRecordData() throws Exception {
    CompetencyMasterModel model = new CompetencyMasterModel();
    model.initiate(this.context, this.session);
    model.showRecord(this.competency);
    getNavigationPanel(3);
    model.terminate();
    return "competencyData";
  }
  public String addNew() {
    try {
      reset();
      this.competency.setCompetencyCode("");
      getNavigationPanel(2);
      return "competencyData";
    } catch (Exception e) {
      logger.error("Exception in addNew in action:" + e);
    }return null;
  }

  public String report() throws Exception
  {
    CompetencyMasterModel model = new CompetencyMasterModel();
    model.initiate(this.context, this.session);
    String[] label = { "Sr.No", getMessage("competencyTitle"), 
      getMessage("competencyDesc"), getMessage("competencyIndicator") };
    model.getReport(this.competency, this.request, this.response, this.context, this.session, label);

    model.terminate();
    return null;
  }

  public String addItem()
  {
    try
    {
      CompetencyMasterModel model = new CompetencyMasterModel();
      model.initiate(this.context, this.session);
      String[] competencyLevel = this.request
        .getParameterValues("competencyLevel");
      String[] competencyLevelTitle = this.request
        .getParameterValues("competencyLevelTitle");
      String[] competencyLevelDesc = this.request
        .getParameterValues("competencyLevelDesc");

      if (this.competency.getHiddenEdit().equals("")) {
        boolean res = model.addItem(this.competency, competencyLevel, competencyLevelTitle, competencyLevelDesc);
        if (!res)
          addActionMessage("Duplicate records should not allowed");
      }
      else {
        boolean res = model.moditem(this.competency, competencyLevel, competencyLevelTitle, competencyLevelDesc);
        if (res) {
          addActionMessage("Duplicate records should not allowed");
        }
      }
      this.competency.setCompLevel("");
      this.competency.setCompLevelDesc("");
      this.competency.setCompLevelTitle("");
      this.competency.setHiddenEdit("");
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    getNavigationPanel(2);
    this.competency.setEnableAll("Y");
    return "competencyData";
  }

  public String reset()
    throws Exception
  {
    this.competency.setCompetencyCode("");
    this.competency.setCompetencyDesc("");
    this.competency.setCompetencyIndicator("");
    this.competency.setCompetencyLevel("");
    this.competency.setCompetencyLevelDesc("");
    this.competency.setCompetencyLevelList(null);
    this.competency.setCompetencyLevelTitle("");
    this.competency.setCompetencylist(null);
    this.competency.setCompetencyTitle("");
    this.competency.setCompLevel("");
    this.competency.setCompLevelDesc("");
    this.competency.setCompLevelTitle("");
    getNavigationPanel(2);
    return "competencyData";
  }

  public String cancel() throws Exception {
    input();
    getNavigationPanel(1);
    return "input";
  }

  public String callPage() throws Exception {
    CompetencyMasterModel model = new CompetencyMasterModel();
    model.initiate(this.context, this.session);
    model.competencyData(this.competency, this.request);
    getNavigationPanel(1);
    model.terminate();
    return "input";
  }

  public String save() throws Exception
  {
    String[] competencyCode = this.request.getParameterValues("competencyCode");
    String[] competencyLevel = this.request.getParameterValues("competencyLevel");
    String[] competencyLevelTitle = this.request.getParameterValues("competencyLevelTitle");
    String[] competencyLevelDesc = this.request.getParameterValues("competencyLevelDesc");

    CompetencyMasterModel model = new CompetencyMasterModel();
    model.initiate(this.context, this.session);

    if (this.competency.getCompetencyCode().equals("")) {
      System.out.println("======SAVE===IF===============");
      boolean result = model.addCompentency(this.competency, competencyLevel, competencyLevelTitle, competencyLevelDesc);
      if (result) {
        addActionMessage(getText("addMessage", ""));
        model.showRecord(this.competency);
        getNavigationPanel(3);
        return "competencyData";
      }
      addActionMessage("Duplicate entry found.");
      reset();
      getNavigationPanel(1);
      return "success";
    }

    System.out.println("------------------------------------");
    boolean result = model.modCompentency(this.competency, competencyCode, competencyLevel, competencyLevelTitle, competencyLevelDesc);
    if (result) {
      addActionMessage(getText("modMessage", ""));
      model.showRecord(this.competency);
      getNavigationPanel(3);
      return "competencyData";
    }
    addActionMessage("Duplicate entry found.");
    reset();
    getNavigationPanel(1);
    return "success";
  }

  public String calforedit()
    throws Exception
  {
    CompetencyMasterModel model = new CompetencyMasterModel();
    model.initiate(this.context, this.session);
    model.calforedit(this.competency);
    model.competencyData(this.competency, this.request);
    getNavigationPanel(3);
    this.competency.setEnableAll("N");
    model.terminate();
    return "competencyData";
  }

  public String delete() throws Exception {
    System.out.println("=========delete===========");

    CompetencyMasterModel model = new CompetencyMasterModel();
    model.initiate(this.context, this.session);
    boolean result = model.deleteCompetency(this.competency);
    if (result) {
      addActionMessage("Record deleted successfully !");
      reset();
    }
    else {
      addActionMessage("This record is referenced in other resources.So cannot be deleted. !");
    }model.competencyData(this.competency, this.request);
    getNavigationPanel(1);
    model.terminate();

    return "success";
  }

  public String deletequestion() throws Exception {
    CompetencyMasterModel model = new CompetencyMasterModel();
    model.initiate(this.context, this.session);
    String[] delques = this.request.getParameterValues("hdeleteCode");
    boolean result = model.checkeddeletecompetency(this.competency, delques);
    if (result)
      addActionMessage(getText("delMessage", ""));
    else {
      addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
    }

    model.competencyData(this.competency, this.request);
    getNavigationPanel(1);
    model.terminate();
    return "success";
  }

  public String deleteLevelDtl()
  {
    CompetencyMasterModel model = new CompetencyMasterModel();
    model.initiate(this.context, this.session);
    String compCode = this.request.getParameter("competencyCode");
    String levelCode = this.request.getParameter("levelCode");
    String[] level = this.request.getParameterValues("competencyLevel");
    String[] levelTitle = this.request.getParameterValues("competencyLevelTitle");
    String[] levelDesc = this.request.getParameterValues("competencyLevelDesc");
    String[] comCodeIt = this.request.getParameterValues("competencyCodeIt");
    model.deleteLevel(this.competency, levelCode, compCode, level, levelTitle, levelDesc, comCodeIt);

    model.terminate();
    getNavigationPanel(2);
    return "competencyData";
  }
}