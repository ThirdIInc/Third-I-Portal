package org.struts.action.PAS.Competency;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.SelfCompentencyAssesment;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.PAS.Competency.ReviewerCompetencyAssessmentModel;
import org.paradyne.model.PAS.Competency.SelfCompentencyAssesmentApprovalModel;
import org.paradyne.model.PAS.Competency.SelfCompentencyAssesmentModel;
import org.paradyne.model.PAS.GoalSetting.EmployeeGoalSettingModel;
import org.struts.lib.ParaActionSupport;

public class SelfCompentencyAssesmentAction extends ParaActionSupport
{
  private static Logger logger = Logger.getLogger(SelfCompentencyAssesmentAction.class);
  private SelfCompentencyAssesment selfBean;

  public void prepare_local()
    throws Exception
  {
    this.selfBean = new SelfCompentencyAssesment();
    this.selfBean.setMenuCode(2250);
  }

  public Object getModel()
  {
    return this.selfBean;
  }

  public SelfCompentencyAssesment getSelfBean()
  {
    return this.selfBean;
  }

  public void setSelfBean(SelfCompentencyAssesment selfBean)
  {
    this.selfBean = selfBean;
  }

  public String input()
  {
    try
    {
      String source = this.request.getParameter("src");
      if (source != null) {
        this.selfBean.setSource(source);
      }
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);
      model.getPendingRecords(this.selfBean, this.request);
      model.terminate();
      this.selfBean.setListType("pending");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "input";
  }

  public String getProcessedList()
  {
    try
    {
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);
      model.getProcessedRecords(this.selfBean, this.request);
      model.terminate();
      this.selfBean.setListType("processed");
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
        this.selfBean.setSource(source);
        this.selfBean.setListType("pending");
      }

      String competencyIDItr = this.request.getParameter("competencyIDItr");
      String competencyAssementIDItr = this.request.getParameter("competencyAssementIDItr");
      String competencyNameItr = this.request.getParameter("competencyNameItr");
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);
      model.getCompetencyDetails(this.selfBean, competencyIDItr, competencyAssementIDItr, competencyNameItr);
      Object[][] competencyCodeObj = model.getSelfCompetencyCode(this.request, this.selfBean, competencyAssementIDItr);
      if ((competencyCodeObj != null) && (competencyCodeObj.length > 0)) {
        this.selfBean.setCompetencyKey(String.valueOf(competencyCodeObj[0][0]));
        this.selfBean.setCompetencyID(Utility.checkNull(String.valueOf(competencyCodeObj[0][1])));
      }

      model.getEmployeeDetails(this.selfBean, this.selfBean.getUserEmpId());

      model.competencyTitleDropDown(this.request, this.selfBean, competencyAssementIDItr);

      model.setMappedCategory(this.selfBean, this.selfBean.getCompetencyID());
      model.getCompetencyHeaderAndLevel(this.request, this.selfBean, competencyAssementIDItr, this.selfBean.getCompetencyID());
      model.getRatingAndCommentsIfAlreadyGiven(this.selfBean, competencyAssementIDItr, this.selfBean.getCompetencyID());
      this.selfBean.setFirstDetailPageFlag(true);
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
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);
      model.getCurrentStausFromSaveAndNextPage(this.selfBean, this.selfBean.getCompetencyAssementID(), this.selfBean.getCompetencyID());
      this.selfBean.setCompetencyKey(String.valueOf(Integer.parseInt(this.selfBean.getCompetencyKey()) + 1));
      boolean result = model.updateCompetencyDetails(this.selfBean);
      if (result) {
        addActionMessage("Record Saved Successfully.");
        sendProcessManagerAlertDraft();
        this.selfBean.setSelfComments("");
        this.selfBean.setSelfRating("1");
      }

      this.selfBean.setCompetencyTitleDropDown("0-0");
      model.competencyTitleDropDown(this.request, this.selfBean, this.selfBean.getCompetencyAssementID());

      model.getAllRatingsAndComments(this.selfBean, this.selfBean.getCompetencyAssementID(), this.request);
      model.setMappedCategory(this.selfBean, this.selfBean.getCompetencyID());
      model.getCompetencyHeaderAndLevel(this.request, this.selfBean, this.selfBean.getCompetencyAssementID(), this.selfBean.getCompetencyID());
      model.getRatingAndCommentsIfAlreadyGiven(this.selfBean, this.selfBean.getCompetencyAssementID(), this.selfBean.getCompetencyID());
      this.selfBean.setSecondDetailPageFlag(true);
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
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);
      this.selfBean.setCompetencyKey(String.valueOf(Integer.parseInt(this.selfBean.getCompetencyKey()) - 1));
      boolean result = model.updateCompetencyDetails(this.selfBean);
      if (result) {
        addActionMessage("Record Saved Successfully.");
        this.selfBean.setSelfComments("");
        this.selfBean.setSelfRating("1");
      }

      this.selfBean.setCompetencyTitleDropDown("0-0");
      model.competencyTitleDropDown(this.request, this.selfBean, this.selfBean.getCompetencyAssementID());

      model.setMappedCategory(this.selfBean, this.selfBean.getCompetencyID());
      model.getCompetencyHeaderAndLevel(this.request, this.selfBean, this.selfBean.getCompetencyAssementID(), this.selfBean.getCompetencyID());
      model.getRatingAndCommentsIfAlreadyGiven(this.selfBean, this.selfBean.getCompetencyAssementID(), this.selfBean.getCompetencyID());

      if (this.selfBean.getCompetencyKey().equals("1"))
        this.selfBean.setFirstDetailPageFlag(true);
      else {
        this.selfBean.setSecondDetailPageFlag(true);
      }
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String previousFromFinalPage()
  {
    try
    {
      String[] competencyIDFinalizeItr = this.request.getParameterValues("competencyIDFinalizeItr");
      String[] ratingFinalizeItr = this.request.getParameterValues("ratingFinalizeItr");
      String[] commentsFinalizeItr = this.request.getParameterValues("commentsFinalizeItr");
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);
      this.selfBean.setCompetencyKey(String.valueOf(Integer.parseInt(this.selfBean.getCompetencyKey()) - 1));
      boolean result = model.updateAllRatingsAndComments(this.selfBean, competencyIDFinalizeItr, ratingFinalizeItr, commentsFinalizeItr);
      if (result) {
        addActionMessage("Record Saved Successfully.");
      }
      if (this.selfBean.getCompetencyKey().equals("1"))
        this.selfBean.setFirstDetailPageFlag(true);
      else {
        this.selfBean.setSecondDetailPageFlag(true);
      }

      this.selfBean.setCompetencyTitleDropDown("0-0");
      model.competencyTitleDropDown(this.request, this.selfBean, this.selfBean.getCompetencyAssementID());

      model.setMappedCategory(this.selfBean, this.selfBean.getCompetencyID());
      model.getCompetencyHeaderAndLevel(this.request, this.selfBean, this.selfBean.getCompetencyAssementID(), this.selfBean.getCompetencyID());
      model.getRatingAndCommentsIfAlreadyGiven(this.selfBean, this.selfBean.getCompetencyAssementID(), this.selfBean.getCompetencyID());
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String finalizeRecord()
  {
    boolean reportNotDefined = false;
    try {
      String[] competencyIDFinalizeItr = this.request.getParameterValues("competencyIDFinalizeItr");
      String[] ratingFinalizeItr = this.request.getParameterValues("ratingFinalizeItr");
      String[] commentsFinalizeItr = this.request.getParameterValues("commentsFinalizeItr");
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);

      EmployeeGoalSettingModel goalSettingModel = new EmployeeGoalSettingModel();
      goalSettingModel.initiate(this.context, this.session);

      String query = "SELECT COMP_REPORTING FROM HRMS_COMP_CONFIG  INNER JOIN HRMS_COMP_ASSESMENT_HDR ON HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID=HRMS_COMP_CONFIG.COMP_ID  WHERE  COMP_EMP_ID=" + 
        this.selfBean.getUserEmpId() + " AND COMP_ASSESMENT_ID=" + this.selfBean.getCompetencyAssementID();
      System.out.println(" query  " + query);
      Object[][] strObj = model.getSqlModel().getSingleResult(query);
      String reportingType = "goal";
      if ((strObj != null) && (strObj.length > 0)) {
        reportingType = String.valueOf(strObj[0][0]);
      }

      Object[][] empFlow = goalSettingModel.generateEmpFlow(this.selfBean.getUserEmpId(), Integer.parseInt("1"), reportingType);

      if ((empFlow != null) && (empFlow.length > 0)) {
        boolean result = model.finalizeCompetencyDetails(
          this.selfBean, competencyIDFinalizeItr, 
          ratingFinalizeItr, commentsFinalizeItr, empFlow);
        if (result) {
          addActionMessage("Record Finalized Successfully.");
          this.selfBean.setBackToListPageFlag(true);
          this.selfBean.setFinalizeSaveAndPreviousPageFlag(false);

          removeCompetencyProcessManagerAlert();

          sendSystemGeneratedMailToApplicant(this.selfBean);
          sendMailToApprover(this.selfBean, 
            String.valueOf(empFlow[0][0]));
        }
        model.getAllRatingsAndComments(this.selfBean, 
          this.selfBean.getCompetencyAssementID(), this.request);
      }
      else {
        addActionMessage("Reporting Structure is not defined, Please contact your HR Manager");
        this.selfBean.setBackToListPageFlag(true);
        this.selfBean.setFinalizeSaveAndPreviousPageFlag(true);
        this.selfBean.setFinalPageFlag(true);
        this.selfBean.setNonFinalPageFlag(false);
        reportNotDefined = true;
        model.getAllRatingsAndComments(this.selfBean, 
          this.selfBean.getCompetencyAssementID(), this.request);
      }

      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.selfBean.getSource().equals("mymessages"))
      return "mymessages";
    if (this.selfBean.getSource().equals("myservices")) {
      if (reportNotDefined) {
        input();
        return "input";
      }
      return "serviceJsp";
    }

    return "success";
  }

  private void sendMailToApprover(SelfCompentencyAssesment bean, String approverID)
  {
    try
    {
      EmailTemplateBody templateApp = new EmailTemplateBody();
      templateApp.initiate(this.context, this.session);
      templateApp.setEmailTemplate("SELF COMPETENCY ASSESSMENT MAIL TO APPROVER");
      templateApp.getTemplateQueries();
      EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
      templateQueryApp1.setParameter(1, bean.getUserEmpId());

      EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);
      templateQueryApp2.setParameter(1, approverID);

      EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
      templateQueryApp3.setParameter(1, bean.getMasterCompetencyCode());
      templateQueryApp3.setParameter(2, bean.getUserEmpId());
      templateQueryApp3.setParameter(3, bean.getCompetencyAssementID());

      EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
      templateQueryApp4.setParameter(1, bean.getUserEmpId());
      templateQueryApp4.setParameter(2, bean.getMasterCompetencyCode());

      EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
      templateQueryApp5.setParameter(1, bean.getMasterCompetencyCode());
      templateQueryApp5.setParameter(2, bean.getUserEmpId());
      templateQueryApp5.setParameter(3, bean.getCompetencyAssementID());

      EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
      templateQueryApp6.setParameter(1, bean.getMasterCompetencyCode());
      templateQueryApp6.setParameter(2, bean.getCompetencyAssementID());
      templateQueryApp6.setParameter(3, bean.getUserEmpId());

      templateApp.configMailAlert();

      String link = "/pas/SelfCompentencyAssessmentApproval_getCompetencyDetails.action";
      String linkParam = "competencyIDItr=" + bean.getMasterCompetencyCode() + "&competencyAssesmentIDItr=" + bean.getCompetencyAssementID() + "&competencyAssesmentEmployeeIDItr=" + bean.getUserEmpId() + "&competencyAssesmentLevelItr=1";
      templateApp.sendProcessManagerAlert(approverID, "Competency", "A", bean.getMasterCompetencyCode(), "1", linkParam, link, "Pending", 
        bean.getUserEmpId(), "0", "", "", bean.getUserEmpId());

      templateApp.sendApplicationMail();
      templateApp.clearParameters();
      templateApp.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void sendSystemGeneratedMailToApplicant(SelfCompentencyAssesment bean)
  {
    try
    {
      EmailTemplateBody templateApp = new EmailTemplateBody();
      templateApp.initiate(this.context, this.session);
      templateApp.setEmailTemplate("SELF COMPETENCY ASSESSMENT SYSTEM MAIL TO APPLICANT");
      templateApp.getTemplateQueries();
      EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);

      EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);
      templateQueryApp2.setParameter(1, bean.getUserEmpId());

      EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
      templateQueryApp3.setParameter(1, bean.getMasterCompetencyCode());
      templateQueryApp3.setParameter(2, bean.getUserEmpId());
      templateQueryApp3.setParameter(3, bean.getCompetencyAssementID());

      EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
      templateQueryApp4.setParameter(1, bean.getUserEmpId());
      templateQueryApp4.setParameter(2, bean.getMasterCompetencyCode());

      EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
      templateQueryApp5.setParameter(1, bean.getMasterCompetencyCode());
      templateQueryApp5.setParameter(2, bean.getUserEmpId());
      templateQueryApp5.setParameter(3, bean.getCompetencyAssementID());

      EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
      templateQueryApp6.setParameter(1, bean.getMasterCompetencyCode());
      templateQueryApp6.setParameter(2, bean.getCompetencyAssementID());
      templateQueryApp6.setParameter(3, bean.getUserEmpId());

      templateApp.configMailAlert();
      String link = "/pas/SelfCompentencyAssesment_viewProcessedDetails.action";
      String linkParam = "competencyIDItr=" + bean.getMasterCompetencyCode() + "&competencyAssementIDItr=" + bean.getCompetencyAssementID();
      templateApp.sendProcessManagerAlert("", "Competency", "I", bean.getMasterCompetencyCode(), "1", linkParam, link, "Pending", 
        bean.getUserEmpId(), "0", "", bean.getUserEmpId(), bean.getUserEmpId());
      templateApp.sendApplicationMail();
      templateApp.clearParameters();
      templateApp.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String viewProcessedDetails()
  {
    try
    {
      String source = this.request.getParameter("src");
      if (source != null) {
        this.selfBean.setSource(source);
        this.selfBean.setListType("processed");
      }
      String competencyAssementIDItr = this.request.getParameter("competencyAssementIDItr");
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);
      if (competencyAssementIDItr != null) {
        this.selfBean.setCompetencyAssementID(competencyAssementIDItr);
      }

      String competencyIDItr = this.request.getParameter("competencyIDItr");
      if (competencyIDItr != null) {
        this.selfBean.setMasterCompetencyCode(competencyIDItr);
      }

      String applicantCode = this.request.getParameter("applicantCodeFromMypageAlert");
      if (applicantCode == null) {
        applicantCode = this.selfBean.getUserEmpId();
      }
      model.getApproverRatingsAndCommentsInProcessedList(this.selfBean, competencyAssementIDItr, this.request, applicantCode);

      model.getEmployeeDetails(this.selfBean, applicantCode);

      model.checkForSignOffEscalationWorkFlow(this.selfBean, applicantCode);
      model.getReviewDetailsTableData(this.selfBean, competencyAssementIDItr, applicantCode);
      this.selfBean.setFinalPageFlag(false);
      this.selfBean.setNonFinalPageFlag(false);
      this.selfBean.setBackToListPageFlag(true);
      this.selfBean.setFinalizeSaveAndPreviousPageFlag(false);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String setRespectiveCompetencyTileData()
  {
    try
    {
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);

      this.selfBean.setCompetencyTitleDropDown(this.selfBean.getCompetencyTitleDropDown());
      model.competencyTitleDropDown(this.request, this.selfBean, this.selfBean.getCompetencyAssementID());

      String selectedCompetencyTitle = this.selfBean.getCompetencyTitleDropDown();
      String[] result = selectedCompetencyTitle.split("-");
      this.selfBean.setCompetencyKey(String.valueOf(result[0]));
      this.selfBean.setCompetencyID(String.valueOf(result[1]));
      if (this.selfBean.getCompetencyKey().equals("1"))
        this.selfBean.setFirstDetailPageFlag(true);
      else {
        this.selfBean.setSecondDetailPageFlag(true);
      }
      model.getAllRatingsAndComments(this.selfBean, this.selfBean.getCompetencyAssementID(), this.request);
      model.setMappedCategory(this.selfBean, this.selfBean.getCompetencyID());
      model.getCompetencyHeaderAndLevel(this.request, this.selfBean, this.selfBean.getCompetencyAssementID(), this.selfBean.getCompetencyID());
      model.getRatingAndCommentsIfAlreadyGiven(this.selfBean, this.selfBean.getCompetencyAssementID(), this.selfBean.getCompetencyID());
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String acceptRejectSignOff()
  {
    try
    {
      String status = this.request.getParameter("status");
      String statusToUpdate = "";
      if ("Accept".equals(status))
        statusToUpdate = "A";
      else {
        statusToUpdate = "R";
      }
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);
      boolean result = model.updateSignOffStatus(this.selfBean, statusToUpdate);
      if (result) {
        if ("Accept".equals(status))
          addActionMessage("Sign off accepted successfully.");
        else {
          addActionMessage("Sign off rejected successfully.");
        }
        sendSignOffMailToAllApprovers(this.selfBean);
      }
      input();
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.selfBean.getSource().equals("mymessages"))
      return "mymessages";
    if (this.selfBean.getSource().equals("myservices")) {
      return "serviceJsp";
    }
    return "input";
  }

  private void sendSignOffMailToAllApprovers(SelfCompentencyAssesment bean)
  {
    try
    {
      removeCompetencyProcessManagerAlert();

      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);

      EmailTemplateBody templateApp = new EmailTemplateBody();
      templateApp.initiate(this.context, this.session);
      templateApp.setEmailTemplate("SELF COMPETENCY ASSESSMENT SIGNOFF MAIL TO APPROVER");
      templateApp.getTemplateQueries();
      EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
      templateQueryApp1.setParameter(1, bean.getUserEmpId());

      EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);
      templateQueryApp2.setParameter(1, bean.getUserEmpId());

      EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
      templateQueryApp3.setParameter(1, bean.getMasterCompetencyCode());
      templateQueryApp3.setParameter(2, bean.getUserEmpId());
      templateQueryApp3.setParameter(3, bean.getCompetencyAssementID());

      EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
      templateQueryApp4.setParameter(1, bean.getUserEmpId());
      templateQueryApp4.setParameter(2, bean.getMasterCompetencyCode());

      EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
      templateQueryApp5.setParameter(1, bean.getMasterCompetencyCode());
      templateQueryApp5.setParameter(2, bean.getUserEmpId());
      templateQueryApp5.setParameter(3, bean.getCompetencyAssementID());

      EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
      templateQueryApp6.setParameter(1, bean.getMasterCompetencyCode());
      templateQueryApp6.setParameter(2, bean.getCompetencyAssementID());
      templateQueryApp6.setParameter(3, bean.getUserEmpId());

      templateApp.configMailAlert();

      String approversQuery = "SELECT DISTINCT HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID FROM HRMS_COMP_EMP_ASSESSMENT_DTL  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + 
        bean.getUserEmpId() + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + bean.getCompetencyAssementID();
      Object[][] approversObj = model.getSqlModel().getSingleResult(approversQuery);
      if ((approversObj != null) && (approversObj.length > 0)) {
        String[] finalApprovers = new String[approversObj.length];
        String finalCcId = "";
        for (int i = 0; i < finalApprovers.length; i++) {
          finalApprovers[i] = String.valueOf(approversObj[i][0]);
          finalCcId = finalCcId + finalApprovers[i] + ",";
        }
        finalCcId = finalCcId.substring(0, finalCcId.length() - 1);
        if ((finalApprovers != null) && (finalApprovers.length > 0)) {
          templateApp.sendApplicationMailToKeepInfo(finalApprovers);
        }

        String link = "/pas/SelfCompentencyAssesment_viewProcessedDetails.action";
        String linkParam = "competencyIDItr=" + bean.getMasterCompetencyCode() + "&competencyAssementIDItr=" + bean.getCompetencyAssementID() + "&applicantCodeFromMypageAlert=" + bean.getUserEmpId();
        templateApp.sendProcessManagerAlert("", "Competency", "I", bean.getMasterCompetencyCode(), "1", linkParam, link, "SignOffStatus", 
          bean.getUserEmpId(), "0", finalCcId, bean.getUserEmpId(), bean.getUserEmpId());
      }

      templateApp.sendApplicationMail();
      templateApp.clearParameters();
      templateApp.terminate();
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String updateRecordForReviewer()
  {
    try
    {
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);
      String[] reviewApproverIDItr = this.request.getParameterValues("reviewApproverIDItr");
      String[] reviewAgreeDisAgreeFlag = this.request.getParameterValues("reviewAgreeDisAgreeFlag");
      String[] reviewCommentsItr = this.request.getParameterValues("reviewCommentsItr");
      boolean result = model.updateRecordForReviewer(this.selfBean, reviewApproverIDItr, reviewAgreeDisAgreeFlag, reviewCommentsItr);
      if (result) {
        addActionMessage("Records submitted successfully.");
        sendMailToReviewer(this.selfBean);
      }
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.selfBean.getSource().equals("mymessages"))
      return "mymessages";
    if (this.selfBean.getSource().equals("myservices")) {
      return "serviceJsp";
    }
    input();
    return "input";
  }

  private void sendMailToReviewer(SelfCompentencyAssesment bean)
  {
    try
    {
      removeCompetencyProcessManagerAlert();

      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);
      String reviewerCodeQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_REVIEWER_ID FROM HRMS_COMP_EMP_ASSESSMENT_HDR  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = " + 
        bean.getMasterCompetencyCode() + 
        " AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = " + bean.getUserEmpId() + 
        " AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = " + bean.getCompetencyAssementID();
      Object[][] reviewerCodeObj = model.getSqlModel().getSingleResult(reviewerCodeQuery);
      if ((reviewerCodeObj != null) && (reviewerCodeObj.length > 0)) {
        EmailTemplateBody templateApp = new EmailTemplateBody();
        templateApp.initiate(this.context, this.session);
        templateApp.setEmailTemplate("SELF COMPETENCY ASSESSMENT MAIL TO REVIEWER");
        templateApp.getTemplateQueries();
        EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
        templateQueryApp1.setParameter(1, bean.getUserEmpId());

        EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);
        templateQueryApp2.setParameter(1, String.valueOf(reviewerCodeObj[0][0]));

        EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
        templateQueryApp3.setParameter(1, bean.getMasterCompetencyCode());
        templateQueryApp3.setParameter(2, bean.getUserEmpId());
        templateQueryApp3.setParameter(3, bean.getCompetencyAssementID());

        EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
        templateQueryApp4.setParameter(1, bean.getUserEmpId());
        templateQueryApp4.setParameter(2, bean.getMasterCompetencyCode());

        EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
        templateQueryApp5.setParameter(1, bean.getMasterCompetencyCode());
        templateQueryApp5.setParameter(2, bean.getUserEmpId());
        templateQueryApp5.setParameter(3, bean.getCompetencyAssementID());

        EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
        templateQueryApp6.setParameter(1, bean.getMasterCompetencyCode());
        templateQueryApp6.setParameter(2, bean.getUserEmpId());
        templateQueryApp6.setParameter(3, bean.getCompetencyAssementID());

        EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
        templateQueryApp7.setParameter(1, bean.getMasterCompetencyCode());
        templateQueryApp7.setParameter(2, bean.getCompetencyAssementID());
        templateQueryApp7.setParameter(3, bean.getUserEmpId());

        templateApp.configMailAlert();

        String reviewerLink = "/pas/ReviewerCompetencyAssessment_getCompetencyDetails.action";
        String reviewerLinkParam = "competencyIDItr=" + bean.getMasterCompetencyCode() + "&competencyAssessmentIDItr=" + bean.getCompetencyAssementID() + "&competencyAssessmentEmployeeIDItr=" + bean.getUserEmpId();
        templateApp.sendProcessManagerAlert(String.valueOf(reviewerCodeObj[0][0]), "Competency", "A", bean.getMasterCompetencyCode(), "1", reviewerLinkParam, reviewerLink, "Pending for Reviewer", 
          bean.getUserEmpId(), "0", "", "", bean.getUserEmpId());

        sendProcessManagerAlertForReviewer(String.valueOf(reviewerCodeObj[0][0]));
        String escalationGroupMailIDQuery = "SELECT NVL(HRMS_COMP_CONFIG.COMP_ESCALATION_EMAIL,'') FROM HRMS_COMP_CONFIG WHERE HRMS_COMP_CONFIG.COMP_ID = " + bean.getMasterCompetencyCode();
        Object[][] escalationGroupMailIDObj = model.getSqlModel().getSingleResult(escalationGroupMailIDQuery);
        String[] splitedEmailds = (String[])null;
        String[] attachFile = (String[])null;
        if ((escalationGroupMailIDObj != null) && (escalationGroupMailIDObj.length > 0)) {
          splitedEmailds = String.valueOf(escalationGroupMailIDObj[0][0]).split(";");
        }
        if ((splitedEmailds != null) && (splitedEmailds.length > 0)) {
          templateApp.sendMailToCCEmailIdsWithAttachment(splitedEmailds, 
            attachFile);
        }

        templateApp.sendApplicationMail();
        templateApp.clearParameters();
        templateApp.terminate();
      }
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendProcessManagerAlertDraft()
  {
    try
    {
      FileInputStream alertFin = new FileInputStream(getText("data_path") + "/Alerts/alertLinks.properties");
      Properties alertProp = new Properties();
      alertProp.load(alertFin);
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String msgType = "A";
      String empID = this.selfBean.getUserEmpId();
      String module = "Competency";
      String applnID = this.selfBean.getMasterCompetencyCode();
      String level = "1";

      String link = "/pas/SelfCompentencyAssesment_getCompetencyDetails.action";
      String linkParam = "competencyIDItr=" + this.selfBean.getMasterCompetencyCode() + "&competencyAssementIDItr=" + this.selfBean.getCompetencyAssementID() + "&competencyNameItr=" + this.selfBean.getHiddenCompetencyName();
      String message = alertProp.getProperty("draftAlertMessage");
      message = message.replace("<#EMP_NAME#>", this.selfBean.getEmpName().trim());
      message = message.replace("<#APPL_TYPE#>", module);
      template.sendProcessManagerAlertDraft(empID, module, msgType, applnID, level, linkParam, link, message, "Draft", empID, empID);
      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendProcessManagerAlertForReviewer(String reviewerId)
  {
    try
    {
      SelfCompentencyAssesmentModel model = new SelfCompentencyAssesmentModel();
      model.initiate(this.context, this.session);
      Object[][] reviewerObj = model.getSqlModel().getSingleResult("SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID=" + reviewerId);
      String reviewerName = (reviewerObj != null) && (reviewerObj.length > 0) ? String.valueOf(reviewerObj[0][0]) : "";
      String applicantID = this.selfBean.getEmpId();

      Object[][] competencyDetails = getCompetencyAndApplicantDetails();
      String competencyName = "";
      String applicantName = "";
      if ((competencyDetails != null) && (competencyDetails.length > 0)) {
        competencyName = String.valueOf(competencyDetails[0][0]);
        applicantName = String.valueOf(competencyDetails[0][1]);
      }

      String message = "Self competency assessment " + competencyName + " of " + applicantName + " has been sent to " + reviewerName + " for review";
      String actualStataus = "review";

      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String applicantLink = "/pas/SelfCompentencyAssesment_viewProcessedDetails.action";
      String applicantLinkParam = "competencyIDItr=" + this.selfBean.getMasterCompetencyCode() + "&competencyAssementIDItr=" + this.selfBean.getCompetencyAssementID() + "&applicantCodeFromMypageAlert=" + this.selfBean.getUserEmpId();
      template.sendProcessManagerAlertWithdraw(applicantID, "Competency", "I", 
        this.selfBean.getMasterCompetencyCode(), "1", applicantLinkParam, applicantLink, message, 
        actualStataus, applicantID, this.selfBean.getUserEmpId(), "", "");

      String processedApproverID = getAllApproverID(this.selfBean.getUserEmpId(), this.selfBean.getCompetencyAssementID(), "0");
      String approverLink = "/pas/SelfCompentencyAssessmentApproval_getProcessedCompetencyDetails.action";
      String approverLinkParam = "competencyIDItr=" + this.selfBean.getMasterCompetencyCode() + "&competencyAssesmentIDItr=" + this.selfBean.getCompetencyAssementID() + "&competencyAssesmentEmployeeIDItr=" + this.selfBean.getUserEmpId();
      template.sendProcessManagerAlertWithdraw("", "Competency", "I", 
        this.selfBean.getMasterCompetencyCode(), "1", approverLinkParam, approverLink, message, 
        actualStataus, applicantID, this.selfBean.getUserEmpId(), processedApproverID, "");

      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void removeCompetencyProcessManagerAlert()
  {
    try
    {
      ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
      processAlerts.initiate(this.context, this.session);
      processAlerts.removeProcessAlert(String.valueOf(this.selfBean.getMasterCompetencyCode()), "Competency");
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
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID NOT IN (" + currentUser + ")");
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

  public Object[][] getCompetencyAndApplicantDetails()
  {
    Object[][] competencyNameObj = (Object[][])null;
    try {
      ReviewerCompetencyAssessmentModel model = new ReviewerCompetencyAssessmentModel();
      model.initiate(this.context, this.session);
      competencyNameObj = model.getSqlModel().getSingleResult("SELECT NVL(HRMS_COMP_CONFIG.COMP_NAME,''), NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,'')  FROM HRMS_COMP_ASSESMENT_HDR  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID = HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID)   WHERE HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID = " + 
        this.selfBean.getMasterCompetencyCode() + 
        " AND HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID =  " + this.selfBean.getCompetencyAssementID() + 
        " AND HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID = " + this.selfBean.getUserEmpId());
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return competencyNameObj;
  }
}