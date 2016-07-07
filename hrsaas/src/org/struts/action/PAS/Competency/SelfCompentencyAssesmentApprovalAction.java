package org.struts.action.PAS.Competency;

import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.SelfCompentencyAssesmentApproval;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.PAS.Competency.ReviewerCompetencyAssessmentModel;
import org.paradyne.model.PAS.Competency.SelfCompentencyAssesmentApprovalModel;
import org.paradyne.model.PAS.GoalSetting.EmployeeGoalSettingModel;
import org.struts.lib.ParaActionSupport;

public class SelfCompentencyAssesmentApprovalAction extends ParaActionSupport
{
  private static Logger logger = Logger.getLogger(SelfCompentencyAssesmentApprovalAction.class);
  private SelfCompentencyAssesmentApproval selfApprovalBean;

  public void prepare_local()
    throws Exception
  {
    this.selfApprovalBean = new SelfCompentencyAssesmentApproval();
    this.selfApprovalBean.setMenuCode(2254);
  }

  public Object getModel()
  {
    return this.selfApprovalBean;
  }

  public SelfCompentencyAssesmentApproval getSelfApprovalBean()
  {
    return this.selfApprovalBean;
  }

  public void setSelfApprovalBean(SelfCompentencyAssesmentApproval selfApprovalBean)
  {
    this.selfApprovalBean = selfApprovalBean;
  }

  public String input()
  {
    try
    {
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);
      model.getPendingRecords(this.selfApprovalBean, this.request);
      this.selfApprovalBean.setListType("pending");
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "input";
  }

  public String getProcessedList()
  {
    try
    {
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);
      model.getProcessedRecords(this.selfApprovalBean, this.request);
      this.selfApprovalBean.setListType("processed");
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "input";
  }

  public String getReassessedRecords()
  {
    try
    {
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);
      model.getReassessedRecords(this.selfApprovalBean, this.request);
      this.selfApprovalBean.setListType("reAssessed");
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "input";
  }

  public String getCompetencyDetails()
  {
    try
    {
      String source = this.request.getParameter("src");
      if (source != null) {
        this.selfApprovalBean.setSource(source);
      }

      String listTypeFromMypage = this.request.getParameter("listTypeFromMypage");
      if (listTypeFromMypage == null)
        this.selfApprovalBean.setListType("pending");
      else {
        this.selfApprovalBean.setListType("reAssessed");
      }

      String competencyIDItr = this.request.getParameter("competencyIDItr");
      if (competencyIDItr != null) {
        this.selfApprovalBean.setCompetencyMasterCode(competencyIDItr);
      }

      String competencyAssesmentIDItr = this.request.getParameter("competencyAssesmentIDItr");
      if (competencyAssesmentIDItr != null) {
        this.selfApprovalBean.setApproverCompetencyAssesmentCode(competencyAssesmentIDItr);
      }

      String competencyAssesmentEmployeeIDItr = this.request.getParameter("competencyAssesmentEmployeeIDItr");
      if (competencyAssesmentEmployeeIDItr != null) {
        this.selfApprovalBean.setApproverCompetencyEmployeeCode(competencyAssesmentEmployeeIDItr);
      }

      String competencyAssesmentLevelItr = this.request.getParameter("competencyAssesmentLevelItr");
      if (competencyAssesmentLevelItr != null) {
        this.selfApprovalBean.setCompetencyAssesmentLevel(competencyAssesmentLevelItr);
      }
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);

      model.getEmployeeDetails(this.selfApprovalBean, competencyAssesmentEmployeeIDItr);

      model.getApproverRatingsAndCommentsInProcessedList(this.selfApprovalBean, competencyAssesmentIDItr, competencyAssesmentEmployeeIDItr, this.request);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String getProcessedCompetencyDetails()
  {
    try
    {
      String source = this.request.getParameter("src");
      if (source != null) {
        this.selfApprovalBean.setSource(source);
        this.selfApprovalBean.setListType("processed");
      }

      String competencyIDItr = this.request.getParameter("competencyIDItr");
      if (competencyIDItr != null) {
        this.selfApprovalBean.setCompetencyMasterCode(competencyIDItr);
      }

      String competencyAssesmentIDItr = this.request.getParameter("competencyAssesmentIDItr");
      if (competencyAssesmentIDItr != null) {
        this.selfApprovalBean.setApproverCompetencyAssesmentCode(competencyAssesmentIDItr);
      }

      String competencyAssesmentEmployeeIDItr = this.request.getParameter("competencyAssesmentEmployeeIDItr");
      if (competencyAssesmentEmployeeIDItr != null) {
        this.selfApprovalBean.setApproverCompetencyEmployeeCode(competencyAssesmentEmployeeIDItr);
      }

      String competencyAssesmentLevelItr = this.request.getParameter("competencyAssesmentLevelItr");
      if (competencyAssesmentLevelItr != null) {
        this.selfApprovalBean.setCompetencyAssesmentLevel(competencyAssesmentLevelItr);
      }

      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);
      model.getEmployeeDetails(this.selfApprovalBean, competencyAssesmentEmployeeIDItr);
      model.getProcessedApproverRatingsAndCommentsInProcessedList(this.selfApprovalBean, competencyAssesmentIDItr, competencyAssesmentEmployeeIDItr, this.request);

      String escalationAndSignWorkflowQuery = "SELECT HRMS_COMP_CONFIG.COMP_SIGNOFF_WORKFLOW, HRMS_COMP_CONFIG.COMP_ESCALATION_WORKFLOW FROM HRMS_COMP_CONFIG WHERE HRMS_COMP_CONFIG.COMP_ID = " + this.selfApprovalBean.getCompetencyMasterCode();
      Object[][] escalationAndSignWorkflowObj = model.getSqlModel().getSingleResult(escalationAndSignWorkflowQuery);
      boolean signWorkflowStatus = false;
      boolean escalationWorkflowStatus = false;
      String signOffString = "";
      String escalationString = "";
      if ((escalationAndSignWorkflowObj != null) && (escalationAndSignWorkflowObj.length > 0)) {
        signOffString = Utility.checkNull(String.valueOf(escalationAndSignWorkflowObj[0][0]));
        escalationString = Utility.checkNull(String.valueOf(escalationAndSignWorkflowObj[0][1]));
        if (("Y".equals(signOffString)) && ("N".equals(escalationString))) {
          signWorkflowStatus = true;
        }

        if (("Y".equals(signOffString)) && ("Y".equals(escalationString))) {
          signWorkflowStatus = true;
          escalationWorkflowStatus = true;
        }
      }

      if ((signWorkflowStatus) || (escalationWorkflowStatus)) {
        EmployeeGoalSettingModel goalSettingModel = new EmployeeGoalSettingModel();
        goalSettingModel.initiate(this.context, this.session);
        String competencyLevelQuery = "SELECT HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_APPROVER_LEVEL FROM HRMS_COMP_ASSESSMENT_APPR_DTL  WHERE HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ID= " + 
          this.selfApprovalBean.getCompetencyMasterCode() + 
          " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ASSESSMENT_ID= " + this.selfApprovalBean.getApproverCompetencyAssesmentCode() + 
          " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_APPROVER_ID = " + this.selfApprovalBean.getUserEmpId();
        Object[][] competencyLevelObj = model.getSqlModel().getSingleResult(competencyLevelQuery);
        if ((competencyLevelObj != null) && (competencyLevelObj.length > 0)) {
          int level = Integer.parseInt(String.valueOf(competencyLevelObj[0][0])) + 1;
          Object[][] empFlow = goalSettingModel.generateEmpFlow(this.selfApprovalBean.getApproverCompetencyEmployeeCode(), level, "goal");
          String checkSignOffStatusQuery = " SELECT HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_IS_SIGNOFF FROM HRMS_COMP_EMP_ASSESSMENT_HDR WHERE  HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = " + 
            this.selfApprovalBean.getCompetencyMasterCode() + 
            " AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = " + this.selfApprovalBean.getApproverCompetencyEmployeeCode() + 
            " AND  HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = " + this.selfApprovalBean.getApproverCompetencyAssesmentCode();
          Object[][] checkSignOffStatusObj = model.getSqlModel().getSingleResult(checkSignOffStatusQuery);

          if ((empFlow != null) && (empFlow.length > 0)) {
            if ((String.valueOf(empFlow[0][6]).equals("R")) && 
              (String.valueOf(checkSignOffStatusObj[0][0]).equals("N"))) {
              this.selfApprovalBean.setSignOffForFinalizedRecordFlag(true);
              this.selfApprovalBean.setProcessedListPageFlag(false);
              this.selfApprovalBean.setNonProcessListPageFlag(false);
            }

          }
          else if (String.valueOf(checkSignOffStatusObj[0][0]).equals("N")) {
            this.selfApprovalBean.setSignOffForFinalizedRecordFlag(true);
            this.selfApprovalBean.setProcessedListPageFlag(false);
            this.selfApprovalBean.setNonProcessListPageFlag(false);
          }
        }

      }

      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String saveAndNextFromAllRatingCommets()
  {
    try
    {
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);
      String[] allRatingCommentsCompetencyIDItr = this.request.getParameterValues("allRatingCommentsCompetencyIDItr");
      String[] allRatingCommentsSelfApproverCommentsItr = this.request.getParameterValues("allRatingCommentsSelfApproverCommentsItr");
      String[] allRatingCommentsSelfApproverRatingItr = this.request.getParameterValues("allRatingCommentsSelfApproverRatingItr");
      boolean result = model.insertAllRatingAndComments(this.selfApprovalBean, allRatingCommentsCompetencyIDItr, allRatingCommentsSelfApproverCommentsItr, allRatingCommentsSelfApproverRatingItr);
      if (result) {
        addActionMessage("Record Saved Successfully.");
      }
      Object[][] competencyCodeObj = model.getSelfCompetencyCode(this.request, this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode());
      if ((competencyCodeObj != null) && (competencyCodeObj.length > 0)) {
        this.selfApprovalBean.setApproverCompetencyKey(String.valueOf(competencyCodeObj[0][0]));
        this.selfApprovalBean.setApproverCompetencyID(Utility.checkNull(String.valueOf(competencyCodeObj[0][1])));
      }

      this.selfApprovalBean.setCompetencyTitleDropDown("0-0");
      this.selfApprovalBean.setCompetencyTitleDropDown(this.selfApprovalBean.getCompetencyTitleDropDown());
      model.competencyTitleDropDown(this.request, this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode());

      model.getCurrentStausFromSaveAndNextPage(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyKey());
      model.setMappedCategory(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID());
      model.getCompetencyHeaderAndLevel(this.request, this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID());
      model.getEmployeeSelfRatingAndComments(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID(), this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode());
      model.getApproverRatingAndCommentsIfAlreadyGiven(this.selfApprovalBean);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String saveAndNext()
  {
    try
    {
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);
      this.selfApprovalBean.setApproverCompetencyKey(String.valueOf(Integer.parseInt(this.selfApprovalBean.getApproverCompetencyKey()) + 1));
      boolean result = model.updateCompetencyDetails(this.selfApprovalBean);
      if (result) {
        addActionMessage("Record Saved Successfully.");
        this.selfApprovalBean.setApproverSelfComments("");
        this.selfApprovalBean.setApproverSelfRating("1");
      }

      this.selfApprovalBean.setCompetencyTitleDropDown("0-0");
      model.competencyTitleDropDown(this.request, this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode());
      model.getCurrentStausFromSaveAndNextPage(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyKey());

      model.setMappedCategory(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID());
      model.getCompetencyHeaderAndLevel(this.request, this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID());
      model.getEmployeeSelfRatingAndComments(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID(), this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode());
      model.getApproverRatingAndCommentsIfAlreadyGiven(this.selfApprovalBean);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String saveAndPrevious()
  {
    try
    {
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);
      this.selfApprovalBean.setApproverCompetencyKey(String.valueOf(Integer.parseInt(this.selfApprovalBean.getApproverCompetencyKey()) - 1));
      boolean result = model.updateCompetencyDetails(this.selfApprovalBean);
      if (result) {
        addActionMessage("Record Saved Successfully.");
        this.selfApprovalBean.setApproverSelfComments("");
        this.selfApprovalBean.setApproverSelfRating("1");
      }
      this.selfApprovalBean.setDevRequired("");
      this.selfApprovalBean.setDevPlanCode("");
      this.selfApprovalBean.setDevPlanName("");
      this.selfApprovalBean.setDevTargetDate("");
      this.selfApprovalBean.setApproverdevPlanComment("");
      System.out.println("getApproverCompetencyKey::" + this.selfApprovalBean.getApproverCompetencyKey());
      if (this.selfApprovalBean.getApproverCompetencyKey().equals("0")) {
        model.getApproverRatingsAndCommentsInProcessedList(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode(), this.request);
      }
      else
      {
        this.selfApprovalBean.setCompetencyTitleDropDown("0-0");
        model.getCurrentStausFromSaveAndNextPage(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyKey());
        model.competencyTitleDropDown(this.request, this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode());

        model.setMappedCategory(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID());
        model.getCompetencyHeaderAndLevel(this.request, this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID());
        model.getEmployeeSelfRatingAndComments(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID(), this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode());
        model.getApproverRatingAndCommentsIfAlreadyGiven(this.selfApprovalBean);
      }
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String finalizeRecord()
  {
    String finalResult = "";
    try {
      EmployeeGoalSettingModel goalSettingModel = new EmployeeGoalSettingModel();
      goalSettingModel.initiate(this.context, this.session);
      int level = Integer.parseInt(this.selfApprovalBean.getCompetencyAssesmentLevel()) + 1;
      String query = "SELECT COMP_REPORTING FROM HRMS_COMP_CONFIG  INNER JOIN HRMS_COMP_ASSESMENT_HDR ON HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID=HRMS_COMP_CONFIG.COMP_ID  WHERE  COMP_EMP_ID=" + 
        this.selfApprovalBean.getApproverCompetencyEmployeeCode() + " AND COMP_ASSESMENT_ID=" + this.selfApprovalBean.getApproverCompetencyAssesmentCode();
      System.out.println(" query  " + query);
      Object[][] strObj = goalSettingModel.getSqlModel().getSingleResult(query);
      String reportingType = "goal";
      if ((strObj != null) && (strObj.length > 0)) {
        reportingType = String.valueOf(strObj[0][0]);
      }
      System.out.println("....reportingType-- " + reportingType);

      Object[][] empFlow = goalSettingModel.generateEmpFlow(this.selfApprovalBean.getApproverCompetencyEmployeeCode(), level, reportingType);
      if ((empFlow != null) && (empFlow.length > 0)) {
        System.out.println("Next Approver ID >>" + String.valueOf(empFlow[0][0]));
      }
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);
      this.selfApprovalBean.setApproverCompetencyKey(String.valueOf(Integer.parseInt(this.selfApprovalBean.getApproverCompetencyKey())));
      model.updateCompetencyDetails(this.selfApprovalBean);

      this.selfApprovalBean.setCompetencyTitleDropDown("0-0");
      model.competencyTitleDropDown(this.request, this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode());
      model.getCurrentStausFromSaveAndNextPage(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyKey());

      model.setMappedCategory(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID());
      model.getCompetencyHeaderAndLevel(this.request, this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID());
      model.getEmployeeSelfRatingAndComments(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID(), this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode());
      model.getApproverRatingAndCommentsIfAlreadyGiven(this.selfApprovalBean);
      boolean result = model.changeApproverAssesmentCompetencyStatus(this.selfApprovalBean, empFlow, level, reportingType);
      if (result) {
        addActionMessage("Record Finalized Successfully.");
        this.selfApprovalBean.setAfterFinalizedFlag(true);

        String applicant = ""; String oldApprover = ""; String newApprover = "";
        try {
          applicant = this.selfApprovalBean.getApproverCompetencyEmployeeCode();
          oldApprover = this.selfApprovalBean.getUserEmpId();
          if ((empFlow != null) && (empFlow.length > 0)) {
            newApprover = String.valueOf(empFlow[0][0]);

            if (this.selfApprovalBean.getListType().equals("reAssessed"))
              newApprover = "";
          }
          else {
            newApprover = "";
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        removeCompetencyProcessManagerAlert();

        sendMail(this.selfApprovalBean, this.selfApprovalBean.getCompetencyMasterCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode(), oldApprover, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), newApprover, level);
      }

      model.terminate();
      if (this.selfApprovalBean.isFinalPageSignOffFlag()) {
        finalResult = "success";
      }
      else if (this.selfApprovalBean.getSource().equals("mymessages")) {
        finalResult = "mymessages";
      } else if (this.selfApprovalBean.getSource().equals("myservices")) {
        finalResult = "serviceJsp";
      } else {
        input();
        finalResult = "input";
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return finalResult;
  }

  private void sendMail(SelfCompentencyAssesmentApproval approvalBean, String masterCompetencyCode, String applicantCode, String oldApprover, String competencyAssessmentCode, String newApprover, int level)
  {
    try
    {
      if (!newApprover.equals(""))
      {
        removeCompetencyProcessManagerAlert();

        EmailTemplateBody appl_templateApp = new EmailTemplateBody();
        appl_templateApp.initiate(this.context, this.session);
        appl_templateApp.setEmailTemplate("COMPETENCY ASSESSMENT APPROVAL MAIL TO APPLICANT");
        appl_templateApp.getTemplateQueries();

        EmailTemplateQuery appl_templateQueryApp1 = appl_templateApp.getTemplateQuery(1);
        appl_templateQueryApp1.setParameter(1, approvalBean.getUserEmpId());

        EmailTemplateQuery appl_templateQueryApp2 = appl_templateApp.getTemplateQuery(2);
        appl_templateQueryApp2.setParameter(1, applicantCode);

        EmailTemplateQuery appl_templateQueryApp3 = appl_templateApp.getTemplateQuery(3);
        appl_templateQueryApp3.setParameter(1, approvalBean.getUserEmpId());
        appl_templateQueryApp3.setParameter(2, masterCompetencyCode);
        appl_templateQueryApp3.setParameter(3, applicantCode);
        appl_templateQueryApp3.setParameter(4, competencyAssessmentCode);

        EmailTemplateQuery appl_templateQueryApp4 = appl_templateApp.getTemplateQuery(4);
        appl_templateQueryApp4.setParameter(1, applicantCode);
        appl_templateQueryApp4.setParameter(2, masterCompetencyCode);

        EmailTemplateQuery appl_templateQueryApp5 = appl_templateApp.getTemplateQuery(5);
        appl_templateQueryApp5.setParameter(1, masterCompetencyCode);
        appl_templateQueryApp5.setParameter(2, applicantCode);
        appl_templateQueryApp5.setParameter(3, competencyAssessmentCode);

        EmailTemplateQuery appl_templateQueryApp6 = appl_templateApp.getTemplateQuery(6);
        appl_templateQueryApp6.setParameter(1, approvalBean.getUserEmpId());

        EmailTemplateQuery appl_templateQueryApp7 = appl_templateApp.getTemplateQuery(7);
        appl_templateQueryApp7.setParameter(1, masterCompetencyCode);
        appl_templateQueryApp7.setParameter(2, competencyAssessmentCode);
        appl_templateQueryApp7.setParameter(3, applicantCode);

        appl_templateApp.configMailAlert();

        String applicantLink = "/pas/SelfCompentencyAssesment_viewProcessedDetails.action";
        String applicantLinkParam = "competencyIDItr=" + masterCompetencyCode + "&competencyAssementIDItr=" + competencyAssessmentCode;
        appl_templateApp.sendProcessManagerAlert(applicantCode, "Competency", "I", masterCompetencyCode, "1", applicantLinkParam, applicantLink, "Pending", 
          applicantCode, "0", "", "", approvalBean.getUserEmpId());

        String currentApproverLink = "/pas/SelfCompentencyAssessmentApproval_getProcessedCompetencyDetails.action";
        String currentApproverLinkParam = "competencyIDItr=" + masterCompetencyCode + "&competencyAssesmentIDItr=" + competencyAssessmentCode + "&competencyAssesmentEmployeeIDItr=" + applicantCode;
        appl_templateApp.sendProcessManagerAlert("", "Competency", "I", masterCompetencyCode, "1", currentApproverLinkParam, currentApproverLink, "Finalized", 
          applicantCode, "0", "", approvalBean.getUserEmpId(), approvalBean.getUserEmpId());

        appl_templateApp.sendApplicationMail();
        appl_templateApp.clearParameters();
        appl_templateApp.terminate();

        EmailTemplateBody templateApp = new EmailTemplateBody();
        templateApp.initiate(this.context, this.session);
        templateApp.setEmailTemplate("COMPETENCY ASSESSMENT APPROVAL MAIL TO NEXT APPROVER");
        templateApp.getTemplateQueries();
        EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
        templateQueryApp1.setParameter(1, approvalBean.getUserEmpId());

        EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);
        templateQueryApp2.setParameter(1, newApprover);

        EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
        templateQueryApp3.setParameter(1, approvalBean.getUserEmpId());
        templateQueryApp3.setParameter(2, masterCompetencyCode);
        templateQueryApp3.setParameter(3, applicantCode);
        templateQueryApp3.setParameter(4, competencyAssessmentCode);

        EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
        templateQueryApp4.setParameter(1, applicantCode);
        templateQueryApp4.setParameter(2, masterCompetencyCode);

        EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
        templateQueryApp5.setParameter(1, masterCompetencyCode);
        templateQueryApp5.setParameter(2, applicantCode);
        templateQueryApp5.setParameter(3, competencyAssessmentCode);

        EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
        templateQueryApp6.setParameter(1, approvalBean.getUserEmpId());

        EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
        templateQueryApp7.setParameter(1, masterCompetencyCode);
        templateQueryApp7.setParameter(2, competencyAssessmentCode);
        templateQueryApp7.setParameter(3, applicantCode);

        templateApp.configMailAlert();
        String approverLink = "/pas/SelfCompentencyAssessmentApproval_getCompetencyDetails.action";
        String approverLinkParam = "competencyIDItr=" + masterCompetencyCode + "&competencyAssesmentIDItr=" + competencyAssessmentCode + "&competencyAssesmentEmployeeIDItr=" + applicantCode + "&competencyAssesmentLevelItr=" + String.valueOf(level);
        templateApp.sendProcessManagerAlert(newApprover, "Competency", "A", masterCompetencyCode, "1", approverLinkParam, approverLink, "Pending", 
          approvalBean.getUserEmpId(), "0", "", "", approvalBean.getUserEmpId());

        templateApp.sendApplicationMail();
        templateApp.clearParameters();
        templateApp.terminate();
      }
      else
      {
        removeCompetencyProcessManagerAlert();

        EmailTemplateBody appl_templateApp = new EmailTemplateBody();
        appl_templateApp.initiate(this.context, this.session);
        appl_templateApp.setEmailTemplate("COMPETENCY ASSESSMENT APPROVAL MAIL FROM FINAL APPROVER TO APPLICANT");
        appl_templateApp.getTemplateQueries();

        EmailTemplateQuery appl_templateQueryApp1 = appl_templateApp.getTemplateQuery(1);
        appl_templateQueryApp1.setParameter(1, approvalBean.getUserEmpId());

        EmailTemplateQuery appl_templateQueryApp2 = appl_templateApp.getTemplateQuery(2);
        appl_templateQueryApp2.setParameter(1, applicantCode);

        EmailTemplateQuery appl_templateQueryApp3 = appl_templateApp.getTemplateQuery(3);
        appl_templateQueryApp3.setParameter(1, approvalBean.getUserEmpId());
        appl_templateQueryApp3.setParameter(2, masterCompetencyCode);
        appl_templateQueryApp3.setParameter(3, applicantCode);
        appl_templateQueryApp3.setParameter(4, competencyAssessmentCode);

        EmailTemplateQuery appl_templateQueryApp4 = appl_templateApp.getTemplateQuery(4);
        appl_templateQueryApp4.setParameter(1, applicantCode);
        appl_templateQueryApp4.setParameter(2, masterCompetencyCode);

        EmailTemplateQuery appl_templateQueryApp5 = appl_templateApp.getTemplateQuery(5);
        appl_templateQueryApp5.setParameter(1, masterCompetencyCode);
        appl_templateQueryApp5.setParameter(2, applicantCode);
        appl_templateQueryApp5.setParameter(3, competencyAssessmentCode);

        EmailTemplateQuery appl_templateQueryApp6 = appl_templateApp.getTemplateQuery(6);
        appl_templateQueryApp6.setParameter(1, approvalBean.getUserEmpId());

        EmailTemplateQuery appl_templateQueryApp7 = appl_templateApp.getTemplateQuery(7);
        appl_templateQueryApp7.setParameter(1, masterCompetencyCode);
        appl_templateQueryApp7.setParameter(2, competencyAssessmentCode);
        appl_templateQueryApp7.setParameter(3, applicantCode);

        appl_templateApp.configMailAlert();

        String applicantLink = "/pas/SelfCompentencyAssesment_viewProcessedDetails.action";
        String applicantLinkParam = "competencyIDItr=" + masterCompetencyCode + "&competencyAssementIDItr=" + competencyAssessmentCode;
        appl_templateApp.sendProcessManagerAlert(applicantCode, "Competency", "I", masterCompetencyCode, "1", applicantLinkParam, applicantLink, "Finalized", 
          applicantCode, "0", "", "", approvalBean.getUserEmpId());

        SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
        model.initiate(this.context, this.session);
        Object[][] checkForStatusAndWorkflowFlag = model.getSqlModel().getSingleResult("SELECT HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_IS_SIGNOFF,  NVL(HRMS_COMP_CONFIG.COMP_SIGNOFF_WORKFLOW,'N'), NVL(HRMS_COMP_CONFIG.COMP_ESCALATION_WORKFLOW,'N')  FROM HRMS_COMP_EMP_ASSESSMENT_HDR  INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID)  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = " + 
          masterCompetencyCode + 
          " AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = " + applicantCode + 
          " AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = " + competencyAssessmentCode);
        String processedApproverID = getAllApproverID(applicantCode, competencyAssessmentCode, approvalBean.getUserEmpId());
        String processedApproverLink = "/pas/SelfCompentencyAssessmentApproval_getProcessedCompetencyDetails.action";
        String processedApproverLinkParam = "competencyIDItr=" + masterCompetencyCode + "&competencyAssesmentIDItr=" + competencyAssessmentCode + "&competencyAssesmentEmployeeIDItr=" + applicantCode;
        if ((checkForStatusAndWorkflowFlag != null) && (checkForStatusAndWorkflowFlag.length > 0)) {
          if ((String.valueOf(checkForStatusAndWorkflowFlag[0][2]).equals("Y")) && (String.valueOf(checkForStatusAndWorkflowFlag[0][3]).equals("N"))) {
            if ((String.valueOf(checkForStatusAndWorkflowFlag[0][0]).equals("P")) && (String.valueOf(checkForStatusAndWorkflowFlag[0][1]).equals("N"))) {
              String approverLink = "/pas/SelfCompentencyAssessmentApproval_getCompetencyDetails.action";
              String approverLinkParam = "competencyIDItr=" + masterCompetencyCode + "&competencyAssesmentIDItr=" + competencyAssessmentCode + "&competencyAssesmentEmployeeIDItr=" + applicantCode + "&competencyAssesmentLevelItr=" + String.valueOf(level);
              appl_templateApp.sendProcessManagerAlert("", "Competency", "A", masterCompetencyCode, "1", approverLinkParam, approverLink, "Finalized", 
                applicantCode, "0", "", approvalBean.getUserEmpId(), approvalBean.getUserEmpId());
              appl_templateApp.sendProcessManagerAlert("", "Competency", "I", masterCompetencyCode, "1", processedApproverLinkParam, "/pas/SelfCompentencyAssessmentApproval_getProcessedCompetencyDetails.action", "Finalized", 
                applicantCode, "0", processedApproverID, "", approvalBean.getUserEmpId());
            }
          }
          else appl_templateApp.sendProcessManagerAlert("", "Competency", "I", masterCompetencyCode, "1", processedApproverLinkParam, "/pas/SelfCompentencyAssessmentApproval_getProcessedCompetencyDetails.action", "Finalized", 
              applicantCode, "0", processedApproverID, approvalBean.getUserEmpId(), approvalBean.getUserEmpId());

        }

        model.terminate();
        appl_templateApp.sendApplicationMail();
        appl_templateApp.clearParameters();
        appl_templateApp.terminate();
      }

      if (approvalBean.getListType().equals("reAssessed"))
        sendAlertToReviewerApproverApplicant(approvalBean, masterCompetencyCode, competencyAssessmentCode, applicantCode);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String setRespectiveCompetencyTileData()
  {
    try
    {
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);

      this.selfApprovalBean.setCompetencyTitleDropDown(this.selfApprovalBean.getCompetencyTitleDropDown());
      model.competencyTitleDropDown(this.request, this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode());

      String selectedCompetencyTitle = this.selfApprovalBean.getCompetencyTitleDropDown();
      String[] result = selectedCompetencyTitle.split("-");
      this.selfApprovalBean.setApproverCompetencyKey(String.valueOf(result[0]));
      this.selfApprovalBean.setApproverCompetencyID(String.valueOf(result[1]));
      model.getCurrentStausFromSaveAndNextPage(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyKey());
      model.setMappedCategory(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID());
      model.getCompetencyHeaderAndLevel(this.request, this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID());
      model.getEmployeeSelfRatingAndComments(this.selfApprovalBean, this.selfApprovalBean.getApproverCompetencyID(), this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getApproverCompetencyEmployeeCode());
      model.getApproverRatingAndCommentsIfAlreadyGiven(this.selfApprovalBean);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String signOff()
  {
    boolean result = false;
    try {
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);
      result = model.signOffRecord(this.selfApprovalBean);
      if (result) {
        addActionMessage("Record Signoff Successfully.");
        sendSignOffAlertToApplicantAndApprover();
      }
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.selfApprovalBean.getSource().equals("mymessages"))
      return "mymessages";
    if (this.selfApprovalBean.getSource().equals("myservices")) {
      return "serviceJsp";
    }
    input();
    return "input";
  }

  public void sendSignOffAlertToApplicantAndApprover()
  {
    try
    {
      removeCompetencyProcessManagerAlert();

      ReviewerCompetencyAssessmentModel model = new ReviewerCompetencyAssessmentModel();
      model.initiate(this.context, this.session);
      Object[][] approverObj = model.getSqlModel().getSingleResult("SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID=" + this.selfApprovalBean.getUserEmpId());
      String approverName = (approverObj != null) && (approverObj.length > 0) ? String.valueOf(approverObj[0][0]) : "";
      String applicantID = this.selfApprovalBean.getEmpId();
      String competencyName = "";
      String employeeName = "";
      Object[][] competencyNameObj = getCompetencyAndApplicantDetails();
      if ((competencyNameObj != null) && (competencyNameObj.length > 0)) {
        competencyName = String.valueOf(competencyNameObj[0][0]);
        employeeName = String.valueOf(competencyNameObj[0][1]);
      }
      String status = "SignOff";
      String message = "Self competency assessment " + competencyName + " of " + employeeName + " has been SignOff by " + approverName;

      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);

      String applicantLink = "/pas/SelfCompentencyAssesment_viewProcessedDetails.action";
      String applicantLinkParam = "competencyIDItr=" + this.selfApprovalBean.getCompetencyMasterCode() + "&competencyAssementIDItr=" + this.selfApprovalBean.getApproverCompetencyAssesmentCode();

      template.sendProcessManagerAlertWithdraw(applicantID, "Competency", "A", 
        this.selfApprovalBean.getCompetencyMasterCode(), "1", applicantLinkParam, "/pas/SelfCompentencyAssesment_viewProcessedDetails.action", message, 
        "SignOff", applicantID, this.selfApprovalBean.getUserEmpId(), "", "");

      String processedApproverID = getAllApproverID(this.selfApprovalBean.getApproverCompetencyEmployeeCode(), this.selfApprovalBean.getApproverCompetencyAssesmentCode(), this.selfApprovalBean.getUserEmpId());
      String approverLink = "/pas/SelfCompentencyAssessmentApproval_getProcessedCompetencyDetails.action";
      String approverLinkParam = "competencyIDItr=" + this.selfApprovalBean.getCompetencyMasterCode() + "&competencyAssesmentIDItr=" + this.selfApprovalBean.getApproverCompetencyAssesmentCode() + "&competencyAssesmentEmployeeIDItr=" + this.selfApprovalBean.getApproverCompetencyEmployeeCode();
      template.sendProcessManagerAlertWithdraw(this.selfApprovalBean.getUserEmpId(), "Competency", "I", 
        this.selfApprovalBean.getCompetencyMasterCode(), "1", approverLinkParam, "/pas/SelfCompentencyAssessmentApproval_getProcessedCompetencyDetails.action", message, 
        "SignOff", applicantID, this.selfApprovalBean.getUserEmpId(), processedApproverID, "");

      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void sendAlertToReviewerApproverApplicant(SelfCompentencyAssesmentApproval approvalBean, String masterCompetencyCode, String competencyAssessmentCode, String applicantCode)
  {
    try
    {
      removeCompetencyProcessManagerAlert();

      ReviewerCompetencyAssessmentModel model = new ReviewerCompetencyAssessmentModel();
      model.initiate(this.context, this.session);
      Object[][] approverObj = model.getSqlModel().getSingleResult("SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID=" + this.selfApprovalBean.getUserEmpId());
      String approverName = (approverObj != null) && (approverObj.length > 0) ? String.valueOf(approverObj[0][0]) : "";
      String competencyName = "";
      String employeeName = "";
      Object[][] competencyNameObj = getCompetencyAndApplicantDetails();
      if ((competencyNameObj != null) && (competencyNameObj.length > 0)) {
        competencyName = String.valueOf(competencyNameObj[0][0]);
        employeeName = String.valueOf(competencyNameObj[0][1]);
      }

      Object[][] reviewerObj = model.getSqlModel().getSingleResult("SELECT NVL(HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_REVIEWER_ID,0) FROM HRMS_COMP_EMP_ASSESSMENT_HDR  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = " + 
        masterCompetencyCode + 
        " AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = " + applicantCode + 
        " AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = " + competencyAssessmentCode);
      String reviewerID = "";
      if ((reviewerObj != null) && (reviewerObj.length > 0)) {
        reviewerID = String.valueOf(reviewerObj[0][0]);
      }
      String status = "Reassessed";
      String message = "Self competency assessment " + competencyName + " of " + employeeName + " has been " + "Reassessed" + " by " + approverName;

      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String reviewerLink = "/pas/ReviewerCompetencyAssessment_getCompetencyDetails.action";
      String reviewerLinkParam = "competencyIDItr=" + masterCompetencyCode + "&competencyAssessmentIDItr=" + competencyAssessmentCode + "&competencyAssessmentEmployeeIDItr=" + applicantCode + "&listTypeStatus=processed";
      template.sendProcessManagerAlertWithdraw(reviewerID, "Competency", "I", 
        masterCompetencyCode, "1", reviewerLinkParam, "/pas/ReviewerCompetencyAssessment_getCompetencyDetails.action", message, 
        "reAssessed", applicantCode, approvalBean.getUserEmpId(), "", "");

      String approverID = getAllApproverID(applicantCode, competencyAssessmentCode, approvalBean.getUserEmpId());
      String approverLink = "/pas/SelfCompentencyAssessmentApproval_getProcessedCompetencyDetails.action";
      String approverLinkParam = "competencyIDItr=" + masterCompetencyCode + "&competencyAssesmentIDItr=" + competencyAssessmentCode + "&competencyAssesmentEmployeeIDItr=" + applicantCode;
      template.sendProcessManagerAlertWithdraw(approvalBean.getUserEmpId(), "Competency", "I", 
        masterCompetencyCode, "1", approverLinkParam, "/pas/SelfCompentencyAssessmentApproval_getProcessedCompetencyDetails.action", message, 
        "reAssessed", applicantCode, approvalBean.getUserEmpId(), approverID, "");

      String applicantLink = "/pas/SelfCompentencyAssesment_viewProcessedDetails.action";
      String applicantLinkParam = "competencyIDItr=" + masterCompetencyCode + "&competencyAssementIDItr=" + competencyAssessmentCode;
      template.sendProcessManagerAlertWithdraw(applicantCode, "Competency", "A", 
        masterCompetencyCode, "1", applicantLinkParam, "/pas/SelfCompentencyAssesment_viewProcessedDetails.action", message, 
        "reAssessed", applicantCode, approvalBean.getUserEmpId(), "", "");

      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object[][] getCompetencyAndApplicantDetails()
  {
    Object[][] competencyNameObj = (Object[][])null;
    try {
      ReviewerCompetencyAssessmentModel model = new ReviewerCompetencyAssessmentModel();
      model.initiate(this.context, this.session);
      competencyNameObj = model.getSqlModel().getSingleResult("SELECT HRMS_COMP_CONFIG.COMP_NAME, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME  FROM HRMS_COMP_ASSESMENT_HDR  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID = HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID)   WHERE HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID = " + 
        this.selfApprovalBean.getCompetencyMasterCode() + 
        " AND HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID =  " + this.selfApprovalBean.getApproverCompetencyAssesmentCode() + 
        " AND HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID = " + this.selfApprovalBean.getApproverCompetencyEmployeeCode());
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return competencyNameObj;
  }

  public void removeCompetencyProcessManagerAlert()
  {
    try
    {
      ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
      processAlerts.initiate(this.context, this.session);
      processAlerts.removeProcessAlert(this.selfApprovalBean.getCompetencyMasterCode(), "Competency");
      processAlerts.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getAllApproverID(String applicantCode, String competencyAssessmentCode, String currentUser)
  {
    String approverID = "";
    try {
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);
      Object[][] approverObj = model.getSqlModel().getSingleResult("SELECT DISTINCT NVL(HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID,0) FROM HRMS_COMP_EMP_ASSESSMENT_DTL  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + 
        applicantCode + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + competencyAssessmentCode + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID  NOT IN (" + currentUser + ")");
      if ((approverObj != null) && (approverObj.length > 0)) {
        for (int i = 0; i < approverObj.length; i++) {
          approverID = approverID + String.valueOf(approverObj[i][0]) + ",";
        }
        approverID = approverID.substring(0, approverID.length() - 1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return approverID;
  }

  public String f9DevPlan()
  {
    String query = "SELECT COMP_DEV_PLAN,COMP_DEV_CODE FROM HRMS_COMPETENCY_DEV_PLAN ORDER BY COMP_DEV_CODE";
    String[] headers = { "Developement Plan" };

    String[] headerWidth = { "80" };
    String[] fieldNames = { "devPlanName", "devPlanCode" };
    int[] columnIndex = { 0, 1 };
    String submitFlag = "false";
    String submitToMethod = "";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
    return "f9page";
  }
}