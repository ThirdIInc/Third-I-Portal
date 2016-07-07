package org.struts.action.PAS.Competency;

import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompInitialization;
import org.paradyne.model.PAS.Competency.CompInitializationModel;
import org.struts.lib.ParaActionSupport;

public class CompInitializationAction extends ParaActionSupport
{
  CompInitialization compInitialization;
  static Logger logger = Logger.getLogger(CompInitializationAction.class);

  public void prepare_local() throws Exception
  {
    this.compInitialization = new CompInitialization();
    this.compInitialization.setMenuCode(2269);
  }

  public Object getModel()
  {
    return this.compInitialization;
  }

  public String input() {
    try {
      CompInitializationModel model = new CompInitializationModel();
      model.initiate(this.context, this.session);
      model.data(this.compInitialization, this.request);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    getNavigationPanel(1);
    return "success";
  }

  public String addNew() {
    try {
      getNavigationPanel(2);
      System.out.println("In addNew Method >>>>>>>>>>>>>>>>>>>>>>>>");
      return "addnew";
    } catch (Exception e) {
      logger.error("Exception in addNew in action:" + e);
    }return null;
  }

  public String publishGoal()
  {
    CompInitializationModel model = new CompInitializationModel();
    model.initiate(this.context, this.session);
    try {
      save();
      model.updatePublishStatus(this.compInitialization.getCompId(), "2");
    }
    catch (Exception localException)
    {
    }
    getNavigationPanel(4);
    this.compInitialization.setEnableAll("N");
    return "addnew";
  }

  public String callforedit() {
    String autoGoalCode = this.request.getParameter("autoCode");
    String status = this.request.getParameter("status");
    try
    {
      CompInitializationModel model = new CompInitializationModel();
      model.initiate(this.context, this.session);
      model.setData(autoGoalCode, this.compInitialization);
      model.showReviewDates(this.compInitialization);
      model.categoryData(this.compInitialization);
      model.goalRatingDetails(this.compInitialization);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.compInitialization.setEnableAll("N");
    if (status.equals("Draft")) {
      getNavigationPanel(3);
      this.compInitialization.setEnableAll("N");
    } else {
      this.compInitialization.setCalcRatingFlag(true);
      getNavigationPanel(4);
      this.compInitialization.setEnableAll("N");
    }
    return "addnew";
  }

  public String save() throws Exception {
    CompInitializationModel model = new CompInitializationModel();
    String result = "";
    String[] category = this.request.getParameterValues("categoryIt");
    String[] categoryWeightage = this.request.getParameterValues("catWeightage");

    model.initiate(this.context, this.session);
    if (String.valueOf(this.compInitialization.getCompId()).equals("")) {
      logger.info("into add method");
      result = model.addGoal(this.compInitialization, category, categoryWeightage, this.request);
      if (result == "Saved") {
        if (this.request.getParameter("pushlishFlag").equals("1"))
        {
          addActionMessage("Record published successfully.");
        }
        else addActionMessage("Record saved successfully.");

        getNavigationPanel(3);
        model.setData(this.compInitialization.getCompId(), this.compInitialization);
        model.categoryData(this.compInitialization);
        this.compInitialization.setEnableAll("N");
      }
      else {
        if (this.request.getParameter("pushlishFlag").equals("1"))
        {
          addActionMessage("Record can  not be  published.");
        }
        else addActionMessage("Record can not be added.");

        getNavigationPanel(2);
      }
    }
    else
    {
      result = model.updateGoal(this.compInitialization, category, categoryWeightage, this.request);
      if (result == "modified") {
        model.categoryData(this.compInitialization);
        model.goalRatingDetails(this.compInitialization);
        if (this.request.getParameter("pushlishFlag").equals("1"))
        {
          addActionMessage("Record published  successfully.");
        }
        else addActionMessage("Record updated successfully.");

        getNavigationPanel(3);
        this.compInitialization.setEnableAll("N");
      } else {
        if (this.request.getParameter("pushlishFlag").equals("1"))
        {
          addActionMessage("Record can not be published.");
        }
        else addActionMessage("Record can not be updated.");

        getNavigationPanel(2);
      }
    }

    model.categoryData(this.compInitialization);
    model.getDateRow(this.compInitialization, this.request);
    model.terminate();
    return "addnew";
  }

  public String reset() throws Exception {
    this.compInitialization.setCompName("");
    this.compInitialization.setCompfromDate("");
    this.compInitialization.setComptoDate("");
    this.compInitialization.setAppraisalCode("");
    this.compInitialization.setAppraisalName("");
    this.compInitialization.setReportingType("");
    this.compInitialization.setCompWeightage("");
    this.compInitialization.setAppWeightage("");
    this.compInitialization.setSelfAsstWeightage("");
    this.compInitialization.setManagerAsstWeightage("");
    this.compInitialization.setRatingrangeupto("");
    getNavigationPanel(2);
    return "addnew";
  }

  public String delete() throws Exception {
    CompInitializationModel model = new CompInitializationModel();
    model.initiate(this.context, this.session);
    String autoGoalCode = this.request.getParameter("autoCode");
    System.out.println("=========AUTO Competency CODE=======" + autoGoalCode);
    if (model.deleteGoal(this.compInitialization.getCompId())) {
      getNavigationPanel(1);
      addActionMessage("Competency deleted successfully");
      model.data(this.compInitialization, this.request);
    } else {
      model.setData(autoGoalCode, this.compInitialization);
      model.showReviewDates(this.compInitialization);
      model.categoryData(this.compInitialization);
      model.goalRatingDetails(this.compInitialization);
      addActionMessage("Competency can not be deleted");

      getNavigationPanel(3);
    }

    model.terminate();
    return "success";
  }

  public String cancel() throws Exception {
    this.compInitialization.setCompName("");
    this.compInitialization.setCompfromDate("");
    this.compInitialization.setComptoDate("");
    this.compInitialization.setCompId("");
    getNavigationPanel(1);
    input();
    return "success";
  }

  public String back() throws Exception {
    this.compInitialization.setCompName("");
    this.compInitialization.setCompfromDate("");
    this.compInitialization.setComptoDate("");
    this.compInitialization.setCompId("");
    getNavigationPanel(1);
    input();
    return "success";
  }

  public String edit() {
    CompInitializationModel model = new CompInitializationModel();
    model.initiate(this.context, this.session);
    model.setData(this.compInitialization.getCompId(), this.compInitialization);
    model.getDateRow(this.compInitialization, this.request);
    model.categoryData(this.compInitialization);
    model.goalRatingDetails(this.compInitialization);
    getNavigationPanel(2);
    model.terminate();
    return "addnew";
  }

  public String setSearchedRecord()
  {
    try {
      CompInitializationModel model = new CompInitializationModel();
      model.initiate(this.context, this.session);
      model.setData(this.compInitialization.getCompId(), this.compInitialization);
      model.showReviewDates(this.compInitialization);
      model.categoryData(this.compInitialization);
      model.goalRatingDetails(this.compInitialization);
      logger.info("bean.getStastus==" + 
        this.compInitialization.getCompPublishStatus());
      if (this.compInitialization.getCompPublishStatus().equals("1")) {
        getNavigationPanel(3);
      } else {
        this.compInitialization.setCalcRatingFlag(true);
        getNavigationPanel(4);
      }

      this.compInitialization.setEnableAll("N");
      model.terminate();
    }
    catch (Exception localException) {
    }
    return "addnew";
  }

  public String f9action() throws Exception
  {
    String query = " SELECT COMP_NAME, TO_CHAR(COMP_FROM_DATE,'DD-MM-YYYY') ,TO_CHAR(COMP_TO_DATE,'DD-MM-YYYY'),DECODE(COMP_PUBLISH_STATUS,'1','Draft','2','Published'),COMP_ID FROM HRMS_COMP_CONFIG";

    String[] headers = { getMessage("compPeriod"), 
      getMessage("compFromDate"), getMessage("compToDate"), 
      getMessage("compStatus") };

    String[] headerWidth = { "30", "30", "30", "15" };

    String[] fieldNames = { "compName", "compfromDate", "comptoDate", 
      "compPublishStatus", "compId" };

    int[] columnIndex = { 0, 1, 2, 3, 4 };

    String submitFlag = "true";

    String submitToMethod = "CompetencyInitialization_setSearchedRecord.action";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String addDateRow() {
    CompInitializationModel model = new CompInitializationModel();
    model.initiate(this.context, this.session);
    model.addDateRow(this.compInitialization, this.request);

    String[] srNo = this.request.getParameterValues("srNo");
    String[] category = this.request.getParameterValues("categoryIt");

    String[] weightage = this.request.getParameterValues("reviewWeightage");

    String[] categoryWeightage = this.request.getParameterValues("catWeightage");

    if (category != null) {
      model.addItem(this.compInitialization, srNo, category, categoryWeightage, 0);
    }
    model.terminate();

    getNavigationPanel(2);
    model.terminate();
    return "addnew";
  }

  public String showReviewDates() {
    CompInitializationModel model = new CompInitializationModel();
    model.initiate(this.context, this.session);
    model.showReviewDates(this.compInitialization);
    getNavigationPanel(2);
    model.terminate();
    return "addnew";
  }

  public String removeDate() {
    CompInitializationModel model = new CompInitializationModel();
    model.initiate(this.context, this.session);
    model.removeDate(this.compInitialization, this.request);
    this.compInitialization.setParaId("");
    String[] srNo = this.request.getParameterValues("srNo");
    String[] category = this.request.getParameterValues("categoryIt");
    String[] categoryWeightage = this.request.getParameterValues("catWeightage");

    if (category != null) {
      model.addItem(this.compInitialization, srNo, category, categoryWeightage, 0);
    }

    getNavigationPanel(2);
    model.terminate();
    return "addnew";
  }

  public String multipleDelete()
  {
    CompInitializationModel model = new CompInitializationModel();
    model.initiate(this.context, this.session);
    String goalIdForDelete = this.compInitialization.getParaId();
    logger.info("goalIdForDelete====" + goalIdForDelete);
    if (goalIdForDelete.lastIndexOf(",") == goalIdForDelete.length() - 1) {
      goalIdForDelete = goalIdForDelete.substring(0, 
        goalIdForDelete.length() - 1);
    }
    logger.info("goalIdForDelete after====" + goalIdForDelete);
    if (model.deleteGoal(goalIdForDelete))
    {
      addActionMessage("Competency deleted successfully");
    }
    else addActionMessage("Competency can not be deleted");

    getNavigationPanel(1);
    model.data(this.compInitialization, this.request);
    model.terminate();

    return "success";
  }

  public String f9AppCal() throws Exception
  {
    String query = " SELECT APPR_CAL_CODE,APPR_ID FROM PAS_APPR_CALENDAR\t ORDER BY APPR_ID";

    String[] headers = { "Appraisal Code" };

    String[] headerWidth = { "50" };

    String[] fieldNames = { "appraisalName", "appraisalCode" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    logger.info("4");
    return "f9page";
  }

  public String addItem() throws Exception {
    String[] srNo = this.request.getParameterValues("srNo");
    String[] category = this.request.getParameterValues("categoryIt");
    CompInitializationModel model = new CompInitializationModel();
    String[] categoryWeightage = this.request.getParameterValues("catWeightage");
    model.initiate(this.context, this.session);

    if (category != null) {
      model.getDuplicate(this.compInitialization, srNo, category, categoryWeightage, 1);
    }
    if (this.compInitialization.getHiddenEdit().equals("")) {
      model.addItem(this.compInitialization, srNo, category, categoryWeightage, 1);
    }
    model.terminate();
    this.compInitialization.setCategory1("");
    this.compInitialization.setSubcode("");
    this.compInitialization.setHiddenEdit("");
    this.compInitialization.setParaId("");

    getNavigationPanel(2);
    model.getDateRow(this.compInitialization, this.request);
    return "addnew";
  }

  public String removeGoalsCategories()
  {
    String[] srNo = this.request.getParameterValues("srNo");
    String[] category = this.request.getParameterValues("categoryIt");
    String[] categoryWeightage = this.request.getParameterValues("catWeightage");
    CompInitializationModel model = new CompInitializationModel();
    model.initiate(this.context, this.session);
    model.removeGoals(this.compInitialization, category, srNo, categoryWeightage, this.request);
    this.compInitialization.setCategory1("");
    this.compInitialization.setSrNo("");
    this.compInitialization.setSubcode("");
    this.compInitialization.setHiddenEdit("");
    this.compInitialization.setParaId("");
    model.getDateRow(this.compInitialization, this.request);
    model.terminate();
    getNavigationPanel(2);
    return "addnew";
  }

  public String calculateRating() {
    try {
      CompInitializationModel model = new CompInitializationModel();
      String goalId = this.compInitialization.getCompId();
      model.initiate(this.context, this.session);
      model.getCalculatedRatingsByGoalId(this.compInitialization, goalId);
      model.terminate();
      getNavigationPanel(4);
      this.compInitialization.setCalcRatingFlag(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "addnew";
  }

  public String report()
  {
    String compId = this.compInitialization.getCompId();
    CompInitializationModel model = new CompInitializationModel();
    model.initiate(this.context, this.session);
    model.generateReport(this.compInitialization, this.response, this.request, compId, "");
    model.terminate();
    return null;
  }
}