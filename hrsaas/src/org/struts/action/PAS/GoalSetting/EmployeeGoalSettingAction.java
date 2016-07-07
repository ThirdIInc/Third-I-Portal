package org.struts.action.PAS.GoalSetting;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.EmployeeGoalSetting;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.PAS.GoalSetting.EmployeeGoalSettingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class EmployeeGoalSettingAction extends ParaActionSupport
{
  EmployeeGoalSetting goalSetting = null;

  static Logger logger = Logger.getLogger(EmployeeGoalSettingAction.class);

  public void prepare_local() throws Exception
  {
    this.goalSetting = new EmployeeGoalSetting();
    this.goalSetting.setMenuCode(1073);
  }

  public Object getModel()
  {
    return this.goalSetting;
  }

  public String input() {
    this.goalSetting.setListType("pending");
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    model.getDraftList(this.goalSetting, this.request);
    model.getSubmitList(this.goalSetting, this.request);
    model.getReturnedList(this.goalSetting, this.request);
    model.getRevisedList(this.goalSetting, this.request);
    resetOnCancel();
    model.terminate();
    getNavigationPanel(1);

    return "success";
  }

  public String getApprovedList()
  {
    this.goalSetting.setListType("approved");
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    model.getApprovedList(this.goalSetting, this.request);
    resetOnCancel();
    model.terminate();
    getNavigationPanel(1);
    return "success";
  }

  public String retriveGoalDetails() {
    System.out.println("In  retriveGoalDetails");
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    String source = this.request.getParameter("src");
    System.out.println("Source----------------------" + source);
    this.goalSetting.setSource(source);
    this.goalSetting.setEmpGoalId(this.request.getParameter("empGoalId"));
    String approvalStatus = this.request.getParameter("approvalStatus");

    if ((approvalStatus.equals("1")) || (approvalStatus.equals("4")))
      getNavigationPanel(3);
    else if (approvalStatus.equals("6"))
      getNavigationPanel(8);
    else {
      getNavigationPanel(4);
    }
    this.goalSetting.setHiddenStatus(approvalStatus);
    model.getEmployeeGoalDetails(this.goalSetting);
    getGoalsList();
    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      model.setGoalCategories(this.goalSetting);
    }
    model.getApproverCommentList(this.goalSetting);
    this.goalSetting.setEnableAll("N");
    return "goalDetails";
  }

  public String retriveForApproval()
  {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    this.goalSetting.setIsApprovalForm("true");
    String source = this.request.getParameter("src");
    this.goalSetting.setSource(source);
    System.out.println("Source----------------------" + source);

    this.goalSetting.setEmpGoalId(this.request.getParameter("empGoalId"));
    String approvalStatus = this.request.getParameter("approvalStatus");
    String reportingType = this.request.getParameter("reportingType");
    String approverCode = this.request.getParameter("approverCode");
    System.out.println("approverCode" + approverCode);
    System.out.println("empGoalId--------------" + this.request.getParameter("empGoalId"));
    System.out.println("reportingType" + reportingType);
    System.out.println("approvalStatus" + approvalStatus);
    this.goalSetting.setReportingType(reportingType);

    if (approvalStatus.equals("2")) {
      getNavigationPanel(5);
    }
    else if (this.goalSetting.getUserEmpId().equals(approverCode))
      getNavigationPanel(7);
    else {
      getNavigationPanel(4);
    }
    this.goalSetting.setIsApprovalGoalClick("true");
    this.goalSetting.setHiddenStatus(approvalStatus);
    model.getEmployeeGoalDetails(this.goalSetting);
    getGoalsList();
    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      model.setGoalCategories(this.goalSetting);
    }
    model.getApproverCommentList(this.goalSetting);
    this.goalSetting.setEnableAll("N");
    return "goalDetails";
  }

  public String addNew()
  {
    String source = this.request.getParameter("src");
    System.out.println("source--------------" + source);
    this.goalSetting.setSource(source);

    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    model.getEmployeeDetails(this.goalSetting);
    this.goalSetting.setIsGoalCategory("false");
    getNavigationPanel(2);
    model.terminate();
    return "goalDetails";
  }

  public String f9GoalAction() throws Exception
  {
    String query = "SELECT GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),HRMS_GOAL_CONFIG.GOAL_ID,NVL(GOAL_REPORTING,'reporting'),DECODE(IS_GOAL_CATEGORY,'N','false','Y','true','false') FROM HRMS_GOAL_CONFIG   LEFT JOIN HRMS_GOAL_ELIGIBLE_EMP ON (HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_ELIGIBLE_EMP.GOAL_ID and (HRMS_GOAL_ELIGIBLE_EMP.EMP_ID=" + 
      this.goalSetting.getUserEmpId() + " )) " + 
      " WHERE GOAL_PUBLISH_STATUS =2 AND HRMS_GOAL_CONFIG.GOAL_ID NOT IN (SELECT GOAL_ID FROM HRMS_GOAL_EMP_HDR WHERE EMP_ID=" + 
      this.goalSetting.getUserEmpId() + ")" + 
      " AND (HRMS_GOAL_ELIGIBLE_EMP.EMP_ID=" + this.goalSetting.getUserEmpId() + ") ";

    query = query + " ORDER BY GOAL_NAME ";
    String[] headers = { getMessage("goalPeriod"), 
      getMessage("goalFromDate"), getMessage("goalToDate") };
    String[] headerWidth = { "40", "30", "30" };

    String[] fieldNames = { "goalPeriod", "goalFromDate", "goalToDate", 
      "goalPeriodId", "reportingType", "isGoalCategory" };

    int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

    String submitFlag = "true";

    String submitToMethod = "EmpGoalSetting_setGoalCategories.action";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String setGoalCategories() {
    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
      model.initiate(this.context, this.session);
      model.setGoalCategories(this.goalSetting);
      model.terminate();
    }
    getNavigationPanel(2);

    return "goalDetails";
  }

  public String f9action() throws Exception {
    String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'), DECODE(EMP_GOAL_APPROVAL_STATUS,'1','Draft','2','Pending','3','Approved','4','Sent Back'),EMP_GOAL_APPROVAL_STATUS, GOAL_HDR_ID  ,DECODE(IS_GOAL_CATEGORY,'N','false','Y','true','false') FROM HRMS_GOAL_EMP_HDR LEFT JOIN HRMS_GOAL_CONFIG ON(HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID) LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) WHERE HRMS_EMP_OFFC.EMP_ID=" + 
      this.goalSetting.getUserEmpId() + " ORDER BY GOAL_NAME ";
    String[] headers = { getMessage("employee"), getMessage("goalPeriod"), 
      getMessage("goalFromDate"), getMessage("goalToDate") };
    String[] headerWidth = { "30", "30", "20", "20" };

    String[] fieldNames = { "empName", "goalPeriod", "goalFromDate", 
      "goalToDate", "approvalStatus", "approvalStatus", "empGoalId", "isGoalCategory" };

    int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7 };

    String submitFlag = "true";

    String submitToMethod = "EmpGoalSetting_retriveGoalDetails.action";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String addGoals() {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    String reportingType = this.goalSetting.getReportingType();
    Object[][] approverObj = model.generateEmpFlow(this.goalSetting.getEmpCode(), 1, reportingType);
    if ((approverObj != null) && (approverObj.length > 0)) {
      model.addGoals(this.goalSetting, this.request, String.valueOf(approverObj[0][0]));
      this.goalSetting.setGoalAchieveDate("");
      this.goalSetting.setGoalDesc("");
      this.goalSetting.setGoalWeightage("");
      this.goalSetting.setParaId("");
      this.goalSetting.setGoalComments("");
      this.goalSetting.setGoalCategoryCode("");
    } else {
      addActionMessage("Reporting structure not defined for the employee\n" + 
        this.goalSetting.getEmpName());
      addActionMessage("Please contact HR manager.");
    }

    if (this.goalSetting.getIsApprovalForm().equals("true")) {
      getNavigationPanel(6);
    }
    else if (!this.goalSetting.getHiddenStatus().equals("6"))
      getNavigationPanel(2);
    else {
      getNavigationPanel(9);
    }
    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      model.setGoalCategories(this.goalSetting);
    }
    model.getApproverCommentList(this.goalSetting);
    model.terminate();
    return "goalDetails";
  }

  public String callforEdit() {
    String rowId = this.goalSetting.getParaId();
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    model.getGoalDetailsEdit(rowId, this.goalSetting, this.request);
    if (this.goalSetting.getIsApprovalForm().equals("true")) {
      getNavigationPanel(6);
    }
    else if (!this.goalSetting.getHiddenStatus().equals("6"))
      getNavigationPanel(2);
    else {
      getNavigationPanel(9);
    }
    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      model.setGoalCategories(this.goalSetting);
    }
    model.getApproverCommentList(this.goalSetting);
    model.terminate();
    return "goalDetails";
  }
  public String showGoalsList() {
    getNavigationPanel(2);
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    model.getGoalsList(this.goalSetting, this.request);
    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      model.setGoalCategories(this.goalSetting);
    }
    model.terminate();
    return "goalDetails";
  }

  public String reset() {
    getNavigationPanel(2);
    this.goalSetting.setGoalPeriod("");
    this.goalSetting.setGoalPeriodId("");
    this.goalSetting.setGoalDesc("");
    this.goalSetting.setGoalAchieveDate("");
    this.goalSetting.setGoalFromDate("");
    this.goalSetting.setGoalToDate("");
    this.goalSetting.setParaId("");
    this.goalSetting.setGoalWeightage("");
    this.goalSetting.setGoalComments("");
    this.goalSetting.setGoalCategoryCode("");
    setGoalCategories();

    return "goalDetails";
  }

  public String resetOnCancel() {
    getNavigationPanel(2);
    this.goalSetting.setGoalPeriod("");
    this.goalSetting.setGoalPeriodId("");
    this.goalSetting.setGoalDesc("");
    this.goalSetting.setGoalAchieveDate("");
    this.goalSetting.setGoalFromDate("");
    this.goalSetting.setGoalToDate("");
    this.goalSetting.setParaId("");
    this.goalSetting.setGoalComments("");
    this.goalSetting.setEmpGoalId("");
    this.goalSetting.setGoalCategoryCode("");
    return "goalDetails";
  }

  public String getGoalsList() {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    model.getGoalsList(this.goalSetting, this.request);
    model.terminate();
    return "goalDetails";
  }

  public String removeGoals() {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    model.removeGoals(this.goalSetting, this.request);
    String source = this.request.getParameter("source");
    System.out.println("source in Delete--------------" + source);
    this.goalSetting.setSource(source);
    this.goalSetting.getLevel();
    if (this.goalSetting.getIsApprovalForm().equals("true"))
      getNavigationPanel(6);
    else if (!this.goalSetting.getHiddenStatus().equals("6"))
      getNavigationPanel(2);
    else {
      getNavigationPanel(9);
    }
    this.goalSetting.setParaId("");
    this.goalSetting.setGoalDesc("");
    this.goalSetting.setGoalAchieveDate("");
    this.goalSetting.setGoalWeightage("");
    this.goalSetting.setGoalComments("");
    getGoalsList();
    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      model.setGoalCategories(this.goalSetting);
    }
    model.terminate();
    return "goalDetails";
  }

  public String saveAsDraft()
  {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    String reportingType = this.goalSetting.getReportingType();
    Object[][] approverObj = model.generateEmpFlow(this.goalSetting.getEmpCode(), 1, reportingType);

    boolean result = false;
    try {
      if ((approverObj != null) || (approverObj.length > 0)) {
        result = model.saveAsDraft(this.goalSetting, this.request, 
          String.valueOf(approverObj[0][0]));

        if (result) {
          sendProcessManagerAlertDraft();
          addActionMessage("Goals have been drafted successfully.");
        } else {
          addActionMessage("Error while setting the goals");
        }
        getNavigationPanel(3);
        this.goalSetting.setEnableAll("N");
      } else {
        addActionMessage("Reporting structure not defined for the employee\n" + 
          this.goalSetting.getEmpName());
        addActionMessage("Please contact HR manager.");
        getNavigationPanel(2);
      }
    } catch (Exception e) {
      addActionMessage("Reporting structure not defined for the employee\n" + 
        this.goalSetting.getEmpName());
      addActionMessage("Please contact HR manager.");
      getNavigationPanel(2);
    }

    model.getGoalsList(this.goalSetting, this.request);
    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      model.setGoalCategories(this.goalSetting);
    }
    model.terminate();
    if (this.goalSetting.getSource().equals("mymessages"))
      return "mymessages";
    if (this.goalSetting.getSource().equals("myservices")) {
      return "serviceJsp";
    }

    return "goalDetails";
  }

  public void sendProcessManagerAlertDraft()
  {
    try
    {
      FileInputStream alertFin = new FileInputStream(getText("data_path") + 
        "\\Alerts\\alertLinks.properties");
      Properties alertProp = new Properties();
      alertProp.load(alertFin);
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String msgType = "A";
      String applicantID = this.goalSetting.getEmpCode();
      String module = "Goal Setting";
      String applnID = this.goalSetting.getEmpGoalId();
      String level = "1";

      String link = "/pas/EmpGoalSetting_edit.action";
      String linkParam = "empGoalId=" + applnID + "&empCode=" + 
        applicantID + "&hiddenStatus=1";

      String message = alertProp.getProperty("draftAlertMessage");
      message = message.replace("<#EMP_NAME#>", this.goalSetting.getEmpName().trim());
      message = message.replace("<#APPL_TYPE#>", "Goal Setting");
      template.sendProcessManagerAlertDraft(applicantID, module, msgType, 
        applnID, level, linkParam, link, message, "Draft", 
        applicantID, applicantID);
      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String sendForApproval() {
    System.out.println("In sendForApproval" + this.goalSetting.getSource());
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    String reportingType = this.goalSetting.getReportingType();

    Object[][] approverObj = model.generateEmpFlow(this.goalSetting.getEmpCode(), 1, reportingType);
    System.out.println("approverObj :: " + approverObj);
    boolean result = false;
    try {
      if ((approverObj != null) || (approverObj.length > 0)) {
        result = model.saveAsDraft(this.goalSetting, this.request, 
          String.valueOf(approverObj[0][0]));
        result = model.updateApprovalStatus(this.goalSetting.getEmpGoalId(), 
          "2");

        if (result) {
          addActionMessage("Goals have been sent for approval.");
          try
          {
            MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
            processAlerts.initiate(this.context, this.session);
            processAlerts.removeProcessAlert(this.goalSetting.getEmpGoalId(), "Goal Setting");
            processAlerts.terminate();
          } catch (Exception e) {
            e.printStackTrace();
          }
          sendMail(this.goalSetting.getUserEmpId(), 
            String.valueOf(approverObj[0][0]), 
            this.goalSetting.getEmpGoalId(), this.goalSetting.getGoalPeriodId(), reportingType);
          model.getEmployeeGoalDetails(this.goalSetting);
          if (this.goalSetting.getSource().equals("mymessages"))
            return "mymessages";
          if (this.goalSetting.getSource().equals("myservices")) {
            return "serviceJsp";
          }

          return input();
        }

        addActionMessage("Error while setting the goals.");

        getNavigationPanel(4);
        this.goalSetting.setEnableAll("N");
      } else {
        addActionMessage("Reporting structure not defined for the employee\n" + 
          this.goalSetting.getEmpName());
        addActionMessage("Please contact HR manager.");
        getNavigationPanel(2);
      }
    } catch (Exception e) {
      addActionMessage("Reporting structure not defined for the employee\n" + 
        this.goalSetting.getEmpName());
      addActionMessage("Please contact HR manager.");
      getNavigationPanel(2);
    }

    showGoalsList();
    model.getApproverCommentList(this.goalSetting);
    model.terminate();

    return "goalDetails";
  }

  public void sendMail(String applicantID, String approverID, String applnID, String goalId, String reportingType)
  {
    try {
      System.out.println("applicantID " + applicantID);
      System.out.println("approverID  " + approverID);
      System.out.println("applnID  " + applnID);
      System.out.println("goalId  " + goalId);
      System.out.println("reportingType  " + reportingType);

      String alertlink = "";
      String linkParam = "";
      String module = "Goal Setting";
      String level = "1";

      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      template
        .setEmailTemplate("Mail to employee regarding goal application submission");
      template.getTemplateQueries();
      try
      {
        EmailTemplateQuery templateQuery1 = template
          .getTemplateQuery(1);
        templateQuery1.setParameter(1, approverID);
      }
      catch (Exception localException1) {
      }
      try {
        EmailTemplateQuery templateQuery2 = template
          .getTemplateQuery(2);
        templateQuery2.setParameter(1, applicantID);
      }
      catch (Exception localException2)
      {
      }
      try {
        EmailTemplateQuery templateQuery3 = template
          .getTemplateQuery(3);

        templateQuery3.setParameter(1, applnID);
      }
      catch (Exception localException3) {
      }
      try {
        EmailTemplateQuery templateQuery4 = template
          .getTemplateQuery(4);
        templateQuery4.setParameter(1, approverID);
      }
      catch (Exception localException4) {
      }
      try {
        EmailTemplateQuery templateQuery5 = template
          .getTemplateQuery(5);
        templateQuery5.setParameter(1, applnID);
      }
      catch (Exception localException5) {
      }
      try {
        EmailTemplateQuery templateQuery6 = template
          .getTemplateQuery(6);
        templateQuery6.setParameter(1, goalId);
      }
      catch (Exception localException6) {
      }
      try {
        template.configMailAlert();
        try {
          alertlink = "/pas/EmpGoalSetting_retriveGoalDetails.action";
          System.out.println("alertlink          " + alertlink);
          linkParam = "empGoalId=" + applnID + "&empCode=" + 
            applicantID + "&approvalStatus=2";
          System.out.println("linkParam          " + linkParam);

          template.sendProcessManagerAlert("", module, "I", 
            applnID, level, linkParam, alertlink, "Pending", 
            applicantID, "", "", applicantID, applicantID);
        } catch (Exception e) {
          e.printStackTrace();
        }
        template.sendApplicationMail();
        template.clearParameters();
        template.terminate();
      }
      catch (Exception localException7) {
      }
      EmailTemplateBody template_applicant = new EmailTemplateBody();
      template_applicant.initiate(this.context, this.session);

      template_applicant.initiate(this.context, this.session);
      alertlink = "/pas/EmpGoalSetting_retriveForApproval.action";
      System.out.println("alertlink     alertlink      " + alertlink);
      linkParam = "empGoalId=" + applnID + "&approvalStatus=2" + "&reportingType=" + reportingType + "&approverCode=" + approverID;
      System.out.println("linkParam     linkParam      " + linkParam);

      template_applicant
        .setEmailTemplate("GOAL APPL-APPLICANT TO APPROVER");
      template_applicant.getTemplateQueries();
      try
      {
        EmailTemplateQuery templateQueryApp1 = template_applicant
          .getTemplateQuery(1);
        templateQueryApp1.setParameter(1, applicantID);
      }
      catch (Exception localException8) {
      }
      try {
        EmailTemplateQuery templateQueryApp2 = template_applicant
          .getTemplateQuery(2);
        templateQueryApp2.setParameter(1, approverID);
      }
      catch (Exception localException9)
      {
      }
      try {
        EmailTemplateQuery templateQueryApp3 = template_applicant
          .getTemplateQuery(3);
        templateQueryApp3.setParameter(1, applicantID);
      }
      catch (Exception localException10) {
      }
      try {
        EmailTemplateQuery templateQueryApp4 = template_applicant
          .getTemplateQuery(4);
        templateQueryApp4.setParameter(1, approverID);
      }
      catch (Exception localException11) {
      }
      try {
        EmailTemplateQuery templateQueryApp5 = template_applicant
          .getTemplateQuery(5);
        templateQueryApp5.setParameter(1, applnID);
      }
      catch (Exception localException12) {
      }
      try {
        EmailTemplateQuery templateQueryApp6 = template_applicant
          .getTemplateQuery(6);
        templateQueryApp6.setParameter(1, goalId);
      }
      catch (Exception localException13) {
      }
      try {
        template_applicant.configMailAlert();
        try {
          template_applicant.sendProcessManagerAlert(approverID, module, "A", applnID, level, linkParam, alertlink, "Pending", applicantID, "", "", "", applicantID);
        } catch (Exception e) {
          e.printStackTrace();
        }

        template_applicant.sendApplicationMail();
        template_applicant.clearParameters();
        template_applicant.terminate();
      }
      catch (Exception localException14) {
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String saveByApprover() {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    boolean result = model.saveAsDraft(this.goalSetting, this.request, 
      this.goalSetting.getUserEmpId());
    result = model.updateApprovalStatus(this.goalSetting.getEmpGoalId(), "2");
    if (result) {
      addActionMessage("Goals have been saved successfully.");
      getNavigationPanel(5);
      this.goalSetting.setEnableAll("N");
    } else {
      addActionMessage("Error while saving the goals");
      getNavigationPanel(6);
    }
    model.getGoalsList(this.goalSetting, this.request);
    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      model.setGoalCategories(this.goalSetting);
    }
    return "goalDetails";
  }

  public String approveApplication() {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    String reportingType = this.goalSetting.getReportingType();
    String esclationWrkFlow = "N";

    Object[][] escalData = model.getEsclationWorkFlowDtl(this.goalSetting.getGoalPeriodId());
    if ((escalData != null) && (escalData.length > 0))
      esclationWrkFlow = String.valueOf(escalData[0][0]);
    System.out.println("The escalation Work Flow Flag Val ::::::::::::::" + esclationWrkFlow);

    System.out.println("The escalation Work Flow Flag Val ::::::::::::::" + this.goalSetting.getLevel());

    boolean result = false;
    Object[][] empFlow = model.generateEmpFlow(this.goalSetting.getEmpCode(), 
      Integer.parseInt(this.goalSetting.getLevel()) + 1, reportingType);
    result = model.approveGoal(this.goalSetting, empFlow, esclationWrkFlow);

    if ((empFlow != null) && (empFlow.length > 0)) {
      System.out.println("The length of empFlow :::::::: -------- " + empFlow.length);
    }
    getNavigationPanel(4);
    if (result) {
      model.insertApproverComments(this.goalSetting, "3");
      this.goalSetting.setEnableAll("N");
      addActionMessage("Goals has been approved successfully.");
      String applicant = ""; String oldApprover = ""; String newApprover = "";
      try {
        applicant = this.goalSetting.getEmpCode();
        oldApprover = this.goalSetting.getUserEmpId();
        newApprover = String.valueOf(empFlow[0][0]);

        if (String.valueOf(empFlow[0][6]).equals("R"))
        {
          newApprover = "";
        }
      }
      catch (Exception e)
      {
        logger.error(e);
        e.printStackTrace();
      }
      System.out.println("oldApprover : " + oldApprover);
      System.out.println("newApprover : " + newApprover);

      String[] approverIds = (String[])null;
      sendMailToApprover(this.goalSetting.getEmpCode(), oldApprover, this.goalSetting.getEmpGoalId(), approverIds, newApprover, reportingType);
    }
    else
    {
      getNavigationPanel(5);

      addActionMessage("Unable to Approve Application. Please Define Reviewer in Goal Reporting Structure");
    }

    model.getEmployeeGoalDetails(this.goalSetting);
    getGoalsList();
    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      model.setGoalCategories(this.goalSetting);
    }
    model.getApproverCommentList(this.goalSetting);
    return "goalDetails";
  }

  public String reviseApplication() {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    boolean result = false;
    result = model.updateApprovalStatus(this.goalSetting.getEmpGoalId(), "6");
    getNavigationPanel(4);
    if (result) {
      model.insertApproverComments(this.goalSetting, "6");
      this.goalSetting.setEnableAll("N");
      addActionMessage("Goals has been sent for rivision successfully.");
    }
    else
    {
      addActionMessage("Error while saving the goals");
    }

    model.getEmployeeGoalDetails(this.goalSetting);
    getGoalsList();
    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      model.setGoalCategories(this.goalSetting);
    }
    model.getApproverCommentList(this.goalSetting);
    return "goalDetails";
  }

  public String sentBackApplication() {
    try {
      String reportingType = this.goalSetting.getReportingType();
      EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
      model.initiate(this.context, this.session);
      boolean result = model.updateApprovalStatus(
        this.goalSetting.getEmpGoalId(), "4");

      int level = Integer.parseInt(this.goalSetting.getLevel()) - 1;
      Object[][] empFlow = model.generateEmpFlow(this.goalSetting.getEmpCode(), 
        level, reportingType);
      String approvers = "";
      if ((empFlow != null) && (empFlow.length > 0)) {
        approvers = String.valueOf(empFlow[0][0]);
        try {
          do {
            level--;
            try {
              empFlow = model.generateEmpFlow(this.goalSetting.getEmpCode(), level, reportingType);
              approvers = approvers + "," + String.valueOf(empFlow[0][0]);
            } catch (Exception e) {
              e.printStackTrace();
            }
            if (empFlow == null) break; 
          }
          while (empFlow.length > 0);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }

      }

      String[] splittedApprovers = approvers.split(",");

      if (result) {
        model.insertApproverComments(this.goalSetting, "4");
        this.goalSetting.setEnableAll("N");
        addActionMessage("Goals have been sent back successfully.");
        sendMailApprover(this.goalSetting.getEmpCode(), 
          this.goalSetting.getUserEmpId(), this.goalSetting.getEmpGoalId(), splittedApprovers, reportingType);
      } else {
        addActionMessage("Error while saving the goals");
      }
      model.getEmployeeGoalDetails(this.goalSetting);
      getGoalsList();
      if (this.goalSetting.getIsGoalCategory().equals("true")) {
        model.setGoalCategories(this.goalSetting);
      }
      model.getApproverCommentList(this.goalSetting);
    } catch (Exception e) {
      e.printStackTrace();
    }
    getNavigationPanel(4);
    this.goalSetting.setFlag("true");
    return "goalDetails";
  }

  public String edit()
  {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);

    String source = this.request.getParameter("source");
    System.out.println("source in EDIT--------------" + source);
    this.goalSetting.setSource(source);
    String hiddenstatus = this.request.getParameter("hiddenStatus");
    System.out.println("HiddenStatus-----------" + hiddenstatus);
    this.goalSetting.setApprovalStatus(this.goalSetting.getHiddenStatus());
    this.goalSetting.setEmpGoalId(this.request.getParameter("empGoalId"));
    if (this.goalSetting.getIsApprovalForm().equals("true"))
      getNavigationPanel(6);
    else if (!this.goalSetting.getHiddenStatus().equals("6"))
      getNavigationPanel(2);
    else {
      getNavigationPanel(9);
    }
    model.getEmployeeGoalDetails(this.goalSetting);
    getGoalsList();

    if (this.goalSetting.getIsGoalCategory().equals("true")) {
      model.setGoalCategories(this.goalSetting);
    }
    model.getApproverCommentList(this.goalSetting);
    model.terminate();

    return "goalDetails";
  }

  public String deleteEmpGoals()
  {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    if (model.deleteEmpGoals(this.goalSetting.getEmpGoalId())) {
      getNavigationPanel(1);
      addActionMessage("Goal deleted successfully");
    } else {
      addActionMessage("Goal can not be deleted");
      getNavigationPanel(3);
    }
    this.goalSetting.setGoalPeriod("");
    this.goalSetting.setGoalPeriodId("");
    this.goalSetting.setGoalDesc("");
    this.goalSetting.setGoalAchieveDate("");
    this.goalSetting.setGoalFromDate("");
    this.goalSetting.setGoalToDate("");
    this.goalSetting.setParaId("");
    this.goalSetting.setGoalComments("");
    this.goalSetting.setEmpGoalId("");
    model.terminate();
    return input();
  }

  public String genReport() {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    String[] labels = { getMessage("employee.id"), getMessage("employee"), getMessage("goalPeriod"), getMessage("status"), 
      getMessage("goalFromDate"), getMessage("goalToDate"), getMessage("goalList"), getMessage("goalSrno"), getMessage("goalDesc"), getMessage("goalComments"), 
      getMessage("goalAchieveDate"), getMessage("goalWeightage"), getMessage("approverCommentsList"), getMessage("apprSrno"), getMessage("apprName"), getMessage("apprDate"), 
      getMessage("apprStatus"), getMessage("apprComment"), getMessage("goal.category") };
    model.genReport(this.goalSetting, this.response, this.request, labels);
    return null;
  }

  public void sendMailToApprover(String applicantID, String oldApproverId, String applnID, String[] approverIds, String newApprover, String reportingType)
  {
    try {
      System.out.println("applicantID " + applicantID);
      System.out.println("oldApproverId  " + oldApproverId);
      System.out.println("applnID  " + applnID);
      String module = "Goal Setting";
      String level = "1";
      String alertlink = "";
      String linkParam = "";
      String confGoalId = "";
      EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
      model.initiate(this.context, this.session);
      String query = "SELECT GOAL_ID FROM HRMS_GOAL_EMP_HDR WHERE GOAL_HDR_ID=" + applnID;
      Object[][] confGoalIdDet = model.getSqlModel().getSingleResult(query);
      if ((confGoalIdDet != null) && (confGoalIdDet.length > 0)) {
        confGoalId = String.valueOf(confGoalIdDet[0][0]);
      }
      model.terminate();
      try {
        MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
        processAlerts.initiate(this.context, this.session);
        processAlerts.removeProcessAlert(String.valueOf(applnID), 
          module);
      } catch (Exception e) {
        e.printStackTrace();
      }

      if (!newApprover.equals(""))
      {
        EmailTemplateBody template = new EmailTemplateBody();
        template.initiate(this.context, this.session);
        template
          .setEmailTemplate("GOAL SETTING APPL - APPROVER1 TO APPLICANT");
        template.getTemplateQueries();
        try
        {
          EmailTemplateQuery templateQuery1 = template
            .getTemplateQuery(1);
          templateQuery1.setParameter(1, oldApproverId);
        }
        catch (Exception localException1) {
        }
        try {
          EmailTemplateQuery templateQuery2 = template
            .getTemplateQuery(2);
          templateQuery2.setParameter(1, applicantID);
        }
        catch (Exception localException2)
        {
        }
        try {
          EmailTemplateQuery templateQuery3 = template
            .getTemplateQuery(3);

          templateQuery3.setParameter(1, applnID);
        }
        catch (Exception localException3) {
        }
        try {
          EmailTemplateQuery templateQuery4 = template
            .getTemplateQuery(4);
          templateQuery4.setParameter(1, oldApproverId);
        }
        catch (Exception localException4) {
        }
        try {
          EmailTemplateQuery templateQuery5 = template
            .getTemplateQuery(5);
          templateQuery5.setParameter(1, applnID);
        }
        catch (Exception localException5) {
        }
        try {
          EmailTemplateQuery templateQuery6 = template
            .getTemplateQuery(6);
          templateQuery6.setParameter(1, confGoalId);
        }
        catch (Exception localException6) {
        }
        try {
          EmailTemplateQuery templateQuery7 = template
            .getTemplateQuery(7);
          templateQuery7.setParameter(1, applnID);
        }
        catch (Exception localException7) {
        }
        try {
          template.configMailAlert();
          alertlink = "/pas/EmpGoalSetting_retriveForApproval.action";
          System.out.println("alertlink     alertlink      " + alertlink);
          linkParam = "empGoalId=" + applnID + "&approvalStatus=3" + "&reportingType=" + reportingType + "&approverCode=" + newApprover;
          System.out.println("linkParam     linkParam      " + linkParam);

          template.sendProcessManagerAlert(oldApproverId, module, "I", 
            applnID, level, linkParam, alertlink, "Pending", 
            applicantID, "", "", applicantID, oldApproverId);

          if ((approverIds != null) && (approverIds.length > 0))
            template.sendApplicationMailToKeepInfo(approverIds);
          else {
            template.sendApplicationMail();
          }
          template.clearParameters();
          template.terminate();
        }
        catch (Exception localException8)
        {
        }
        EmailTemplateBody template1 = new EmailTemplateBody();
        template1.initiate(this.context, this.session);
        template1
          .setEmailTemplate("GOAL SETTING APPL - APPROVER1 TO APPROVER2");
        template1.getTemplateQueries();
        try
        {
          EmailTemplateQuery apptemplateQuery1 = template1
            .getTemplateQuery(1);
          apptemplateQuery1.setParameter(1, oldApproverId);
        }
        catch (Exception localException9) {
        }
        try {
          EmailTemplateQuery apptemplateQuery2 = template1
            .getTemplateQuery(2);
          apptemplateQuery2.setParameter(1, newApprover);
        }
        catch (Exception localException10)
        {
        }
        try {
          EmailTemplateQuery apptemplateQuery3 = template1
            .getTemplateQuery(3);

          apptemplateQuery3.setParameter(1, applnID);
        }
        catch (Exception localException11) {
        }
        try {
          EmailTemplateQuery apptemplateQuery4 = template1
            .getTemplateQuery(4);
          apptemplateQuery4.setParameter(1, oldApproverId);
        }
        catch (Exception localException12) {
        }
        try {
          EmailTemplateQuery apptemplateQuery5 = template1
            .getTemplateQuery(5);
          apptemplateQuery5.setParameter(1, applnID);
        }
        catch (Exception localException13) {
        }
        try {
          EmailTemplateQuery apptemplateQuery6 = template1
            .getTemplateQuery(6);
          apptemplateQuery6.setParameter(1, confGoalId);
        }
        catch (Exception localException14) {
        }
        try {
          EmailTemplateQuery apptemplateQuery7 = template1
            .getTemplateQuery(7);
          apptemplateQuery7.setParameter(1, newApprover);
        }
        catch (Exception localException15) {
        }
        try {
          EmailTemplateQuery apptemplateQuery8 = template1
            .getTemplateQuery(8);
          apptemplateQuery8.setParameter(1, applnID);
        }
        catch (Exception localException16) {
        }
        try {
          template1.configMailAlert();
          alertlink = "/pas/EmpGoalSetting_retriveForApproval.action";
          System.out.println("alertlink     alertlink      " + alertlink);
          linkParam = "empGoalId=" + applnID + "&approvalStatus=2" + "&reportingType=" + reportingType + "&approverCode=" + newApprover;
          System.out.println("linkParam     linkParam      " + linkParam);
          template1.sendProcessManagerAlert(newApprover, module, 
            "A", applnID, "2", linkParam, alertlink, 
            "Pending", applicantID, "", "", "", 
            oldApproverId);

          if ((approverIds != null) && (approverIds.length > 0))
            template1.sendApplicationMailToKeepInfo(approverIds);
          else {
            template1.sendApplicationMail();
          }
          template1.clearParameters();
          template1.terminate();
        }
        catch (Exception localException17)
        {
        }
      }
      else
      {
        EmailTemplateBody template = new EmailTemplateBody();
        template.initiate(this.context, this.session);
        template
          .setEmailTemplate("Mail to employee regarding goal approval");
        template.getTemplateQueries();
        try
        {
          EmailTemplateQuery templateQuery1 = template
            .getTemplateQuery(1);
          templateQuery1.setParameter(1, oldApproverId);
        }
        catch (Exception localException18) {
        }
        try {
          EmailTemplateQuery templateQuery2 = template
            .getTemplateQuery(2);
          templateQuery2.setParameter(1, applicantID);
        }
        catch (Exception localException19)
        {
        }
        try {
          EmailTemplateQuery templateQuery3 = template
            .getTemplateQuery(3);

          templateQuery3.setParameter(1, applnID);
        }
        catch (Exception localException20) {
        }
        try {
          EmailTemplateQuery templateQuery4 = template
            .getTemplateQuery(4);
          templateQuery4.setParameter(1, oldApproverId);
        }
        catch (Exception localException21) {
        }
        try {
          EmailTemplateQuery templateQuery5 = template
            .getTemplateQuery(5);
          templateQuery5.setParameter(1, applnID);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          EmailTemplateQuery templateQuery6 = template
            .getTemplateQuery(6);
          templateQuery6.setParameter(1, applnID);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try
        {
          EmailTemplateQuery templateQuery7 = template
            .getTemplateQuery(7);
          templateQuery7.setParameter(1, confGoalId);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          template.configMailAlert();
          alertlink = "/pas/EmpGoalSetting_retriveForApproval.action";
          System.out.println("alertlink     alertlink      " + alertlink);
          linkParam = "empGoalId=" + applnID + "&approvalStatus=3" + "&reportingType=" + reportingType + "&approverCode=" + oldApproverId;
          System.out.println("linkParam     linkParam      " + linkParam);
          template.sendProcessManagerAlert(oldApproverId, module, "I", 
            applnID, level, linkParam, alertlink, "Approved", 
            applicantID, "", "", applicantID, oldApproverId);
          if ((approverIds != null) && (approverIds[0].length() > 0))
            template.sendApplicationMailToKeepInfo(approverIds);
          else {
            template.sendApplicationMail();
          }
          template.clearParameters();
          template.terminate();
        }
        catch (Exception localException22)
        {
        }
      }
    }
    catch (Exception localException23)
    {
    }
  }

  public void sendMailApprover(String applicantID, String approverID, String applnID, String[] approverIds, String reportingType)
  {
    try {
      System.out.println("applicantID " + applicantID);
      System.out.println("approverID  " + approverID);
      System.out.println("applnID  " + applnID);

      String module = "Goal Setting";
      String level = "1";
      String alertlink = "";
      String linkParam = "";
      String confGoalId = "";
      EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
      model.initiate(this.context, this.session);
      String query = "SELECT GOAL_ID FROM HRMS_GOAL_EMP_HDR WHERE GOAL_HDR_ID=" + applnID;
      Object[][] confGoalIdDet = model.getSqlModel().getSingleResult(query);
      if ((confGoalIdDet != null) && (confGoalIdDet.length > 0)) {
        confGoalId = String.valueOf(confGoalIdDet[0][0]);
      }
      model.terminate();
      try {
        MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
        processAlerts.initiate(this.context, this.session);
        processAlerts.removeProcessAlert(String.valueOf(applnID), 
          module);
      } catch (Exception e) {
        e.printStackTrace();
      }
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      template
        .setEmailTemplate("Mail to employee regarding goal approval");
      template.getTemplateQueries();
      try
      {
        EmailTemplateQuery templateQuery1 = template
          .getTemplateQuery(1);
        templateQuery1.setParameter(1, approverID);
      }
      catch (Exception localException1) {
      }
      try {
        EmailTemplateQuery templateQuery2 = template
          .getTemplateQuery(2);
        templateQuery2.setParameter(1, applicantID);
      }
      catch (Exception localException2)
      {
      }
      try {
        EmailTemplateQuery templateQuery3 = template
          .getTemplateQuery(3);

        templateQuery3.setParameter(1, applnID);
      }
      catch (Exception localException3) {
      }
      try {
        EmailTemplateQuery templateQuery4 = template
          .getTemplateQuery(4);
        templateQuery4.setParameter(1, approverID);
      }
      catch (Exception localException4) {
      }
      try {
        EmailTemplateQuery templateQuery5 = template
          .getTemplateQuery(5);
        templateQuery5.setParameter(1, applnID);
      }
      catch (Exception localException5) {
      }
      try {
        EmailTemplateQuery templateQuery6 = template
          .getTemplateQuery(6);
        templateQuery6.setParameter(1, applnID);
      }
      catch (Exception localException6) {
      }
      try {
        EmailTemplateQuery templateQuery7 = template
          .getTemplateQuery(7);
        templateQuery7.setParameter(1, confGoalId);
      }
      catch (Exception localException7) {
      }
      try {
        template.configMailAlert();

        alertlink = "/pas/EmpGoalSetting_edit.action";
        System.out.println("alertlink     alertlink      " + alertlink);
        linkParam = "empGoalId=" + applnID + "&hiddenStatus=4" + "&reportingType=" + reportingType + "&approverCode=" + approverID;
        System.out.println("linkParam     linkParam      " + linkParam);
        template.sendProcessManagerAlert("", module, "A", 
          applnID, level, linkParam, alertlink, "Approved", 
          applicantID, "", "", applicantID, approverID);

        alertlink = "/pas/EmpGoalSetting_retriveForApproval.action";
        linkParam = "empGoalId=" + applnID + "&approvalStatus=3" + "&reportingType=" + reportingType + "&approverCode=";
        template.sendProcessManagerAlert(approverID, module, "I", 
          applnID, level, linkParam, alertlink, "Approved", 
          applicantID, "", "", "", approverID);
        if ((approverIds != null) && (approverIds[0].length() > 0))
          template.sendApplicationMailToKeepInfo(approverIds);
        else {
          template.sendApplicationMail();
        }
        template.clearParameters();
        template.terminate();
      }
      catch (Exception localException8) {
      }
    }
    catch (Exception localException9) {
    }
  }

  public void sendMailApproverAfterSendBack(String applicantID, String approverID, String applnID) {
    try {
      System.out.println("applicantID " + applicantID);
      System.out.println("approverID  " + approverID);
      System.out.println("applnID  " + applnID);
      String confGoalId = "";
      EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
      model.initiate(this.context, this.session);
      String query = "SELECT GOAL_ID FROM HRMS_GOAL_EMP_HDR WHERE GOAL_HDR_ID=" + applnID;
      Object[][] confGoalIdDet = model.getSqlModel().getSingleResult(query);
      if ((confGoalIdDet != null) && (confGoalIdDet.length > 0)) {
        confGoalId = String.valueOf(confGoalIdDet[0][0]);
      }
      model.terminate();
      MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
      processAlerts.initiate(this.context, this.session);
      processAlerts.removeProcessAlert(String.valueOf(applnID), 
        "Goal Setting");

      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      template
        .setEmailTemplate("Mail to employee regarding goal approval");
      template.getTemplateQueries();
      try
      {
        EmailTemplateQuery templateQuery1 = template
          .getTemplateQuery(1);
        templateQuery1.setParameter(1, approverID);
      }
      catch (Exception localException) {
      }
      try {
        EmailTemplateQuery templateQuery2 = template
          .getTemplateQuery(2);
        templateQuery2.setParameter(1, applicantID);
      }
      catch (Exception localException1)
      {
      }
      try {
        EmailTemplateQuery templateQuery3 = template
          .getTemplateQuery(3);

        templateQuery3.setParameter(1, applnID);
      }
      catch (Exception localException2) {
      }
      try {
        EmailTemplateQuery templateQuery4 = template
          .getTemplateQuery(4);
        templateQuery4.setParameter(1, approverID);
      }
      catch (Exception localException3) {
      }
      try {
        EmailTemplateQuery templateQuery5 = template
          .getTemplateQuery(5);
        templateQuery5.setParameter(1, applnID);
      }
      catch (Exception localException4) {
      }
      try {
        EmailTemplateQuery templateQuery6 = template
          .getTemplateQuery(6);
        templateQuery6.setParameter(1, applnID);
      }
      catch (Exception localException5) {
      }
      try {
        EmailTemplateQuery templateQuery7 = template
          .getTemplateQuery(7);
        templateQuery7.setParameter(1, confGoalId);
      }
      catch (Exception localException6) {
      }
      try {
        template.configMailAlert();
        String alertlink = "/pas/EmpGoalSetting_edit.action";
        System.out.println("alertlink     alertlink      " + alertlink);
        String linkParam = "empGoalId=" + applnID + "&hiddenStatus=4" + "&approverCode=" + approverID;
        template.sendProcessManagerAlert(approverID, "Goal Setting", "A", 
          applnID, "1", linkParam, alertlink, "Pending", 
          applicantID, "", "", applicantID, approverID);
        template.sendApplicationMail();
        template.clearParameters();
        template.terminate();
      } catch (Exception localException7) {
      }
    }
    catch (Exception localException8) {
    }
  }

  public String getCompetency() {
    EmployeeGoalSettingModel empGoalSetModel = new EmployeeGoalSettingModel();
    empGoalSetModel.initiate(this.context, this.session);
    empGoalSetModel.getCompetencyData(this.goalSetting);
    return "competencyjsp";
  }

  public EmployeeGoalSetting getGoalSetting()
  {
    return this.goalSetting;
  }

  public void setGoalSetting(EmployeeGoalSetting goalSetting) {
    this.goalSetting = goalSetting;
  }
}