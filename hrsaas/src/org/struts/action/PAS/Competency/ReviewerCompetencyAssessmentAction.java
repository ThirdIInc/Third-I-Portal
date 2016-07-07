package org.struts.action.PAS.Competency;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.ReviewerCompetencyAssessment;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.PAS.Competency.ReviewerCompetencyAssessmentModel;
import org.paradyne.model.PAS.Competency.SelfCompentencyAssesmentApprovalModel;
import org.struts.lib.ParaActionSupport;

public class ReviewerCompetencyAssessmentAction extends ParaActionSupport
{
  private static Logger logger = Logger.getLogger(ReviewerCompetencyAssessmentAction.class);
  private ReviewerCompetencyAssessment reviewerBean;

  public void prepare_local()
    throws Exception
  {
    this.reviewerBean = new ReviewerCompetencyAssessment();
    this.reviewerBean.setMenuCode(2258);
  }

  public Object getModel()
  {
    return this.reviewerBean;
  }

  public ReviewerCompetencyAssessment getReviewerBean()
  {
    return this.reviewerBean;
  }

  public void setReviewerBean(ReviewerCompetencyAssessment reviewerBean)
  {
    this.reviewerBean = reviewerBean;
  }

  public String input()
  {
    try
    {
      ReviewerCompetencyAssessmentModel model = new ReviewerCompetencyAssessmentModel();
      model.initiate(this.context, this.session);
      model.getPendingListData(this.reviewerBean, this.request);
      this.reviewerBean.setListType("pending");
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
      ReviewerCompetencyAssessmentModel model = new ReviewerCompetencyAssessmentModel();
      model.initiate(this.context, this.session);
      model.getProcessedListData(this.reviewerBean, this.request);
      this.reviewerBean.setListType("processed");
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
        this.reviewerBean.setSource(source);
      }

      String listTypeStatus = this.request.getParameter("listTypeStatus");
      if (listTypeStatus != null)
        this.reviewerBean.setListType("processed");
      else {
        this.reviewerBean.setListType("pending");
      }

      ReviewerCompetencyAssessmentModel model = new ReviewerCompetencyAssessmentModel();
      model.initiate(this.context, this.session);
      String competencyIDItr = this.request.getParameter("competencyIDItr");
      if (competencyIDItr != null) {
        this.reviewerBean.setMasterCompetencyCode(competencyIDItr);
      }
      String competencyAssessmentIDItr = this.request.getParameter("competencyAssessmentIDItr");
      if (competencyAssessmentIDItr != null) {
        this.reviewerBean.setCompetencyAssementID(competencyAssessmentIDItr);
      }

      String competencyAssessmentEmployeeIDItr = this.request.getParameter("competencyAssessmentEmployeeIDItr");
      if (competencyAssessmentEmployeeIDItr != null) {
        this.reviewerBean.setCompetencyEmployeeCode(competencyAssessmentEmployeeIDItr);
      }

      String competencyAssessmentLevelItr = this.request.getParameter("competencyAssessmentLevelItr");
      if (competencyAssessmentLevelItr != null) {
        this.reviewerBean.setCompetencyAssesmentLevel(competencyAssessmentLevelItr);
      }

      model.getApproverRatingsAndComments(this.reviewerBean, competencyAssessmentIDItr, competencyAssessmentEmployeeIDItr, this.request);
      model.getEmployeeDetails(this.reviewerBean, competencyAssessmentEmployeeIDItr);
      model.getReviewDetailsTableData(this.reviewerBean, competencyAssessmentIDItr, competencyAssessmentEmployeeIDItr);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String submitRecord()
  {
    try
    {
      String[] reviewApproverIDItr = this.request.getParameterValues("reviewApproverIDItr");
      String[] reviewReviewerCommentsItr = this.request.getParameterValues("reviewReviewerCommentsItr");
      ReviewerCompetencyAssessmentModel model = new ReviewerCompetencyAssessmentModel();
      model.initiate(this.context, this.session);
      boolean result = model.updateApproverDetails(this.reviewerBean, reviewApproverIDItr, reviewReviewerCommentsItr);
      if (result) {
        addActionMessage("Record submited successfully.");
        sendReAssessmentMailToApprover(this.reviewerBean);
      } else {
        addActionMessage("unable to submit record.");
      }
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.reviewerBean.getSource().equals("mymessages"))
      return "mymessages";
    if (this.reviewerBean.getSource().equals("myservices")) {
      return "serviceJsp";
    }
    return "input";
  }

  private void sendReAssessmentMailToApprover(ReviewerCompetencyAssessment rvrBean)
  {
    try
    {
      removeCompetencyProcessManagerAlert();

      ReviewerCompetencyAssessmentModel model = new ReviewerCompetencyAssessmentModel();
      model.initiate(this.context, this.session);
      String findApproverForReAssessmentQuery = "SELECT HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_APPROVER_ID, HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_APPROVER_LEVEL FROM HRMS_COMP_ASSESSMENT_APPR_DTL  WHERE HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ID = " + 
        rvrBean.getMasterCompetencyCode() + 
        " AND  HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_ID = " + rvrBean.getCompetencyEmployeeCode() + 
        " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ASSESSMENT_ID = " + rvrBean.getCompetencyAssementID() + 
        " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_SIGNOFF_ACCEPTANCE = 'N'";
      Object[][] findApproverForReAssessmentObj = model.getSqlModel().getSingleResult(findApproverForReAssessmentQuery);
      if ((findApproverForReAssessmentObj != null) && (findApproverForReAssessmentObj.length > 0)) {
        for (int i = 0; i < findApproverForReAssessmentObj.length; i++) {
          EmailTemplateBody templateApp = new EmailTemplateBody();
          templateApp.initiate(this.context, this.session);
          templateApp.setEmailTemplate("COMPETENCY ASSESSMENT APPROVAL REASSESSMENT MAIL TO APPROVER");
          templateApp.getTemplateQueries();
          EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
          templateQueryApp1.setParameter(1, rvrBean.getUserEmpId());

          EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);
          templateQueryApp2.setParameter(1, String.valueOf(findApproverForReAssessmentObj[i][0]));

          EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
          templateQueryApp3.setParameter(1, rvrBean.getUserEmpId());
          templateQueryApp3.setParameter(2, rvrBean.getMasterCompetencyCode());
          templateQueryApp3.setParameter(3, rvrBean.getCompetencyEmployeeCode());
          templateQueryApp3.setParameter(4, rvrBean.getCompetencyAssementID());

          EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
          templateQueryApp4.setParameter(1, rvrBean.getCompetencyEmployeeCode());
          templateQueryApp4.setParameter(2, rvrBean.getMasterCompetencyCode());

          EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
          templateQueryApp5.setParameter(1, rvrBean.getMasterCompetencyCode());
          templateQueryApp5.setParameter(2, rvrBean.getCompetencyEmployeeCode());
          templateQueryApp5.setParameter(3, rvrBean.getCompetencyAssementID());

          EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
          templateQueryApp6.setParameter(1, rvrBean.getMasterCompetencyCode());
          templateQueryApp6.setParameter(2, rvrBean.getCompetencyEmployeeCode());
          templateQueryApp6.setParameter(3, rvrBean.getCompetencyAssementID());
          templateQueryApp6.setParameter(4, String.valueOf(findApproverForReAssessmentObj[i][0]));

          EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
          templateQueryApp7.setParameter(1, rvrBean.getMasterCompetencyCode());
          templateQueryApp7.setParameter(2, rvrBean.getCompetencyAssementID());
          templateQueryApp7.setParameter(3, rvrBean.getCompetencyEmployeeCode());

          templateApp.configMailAlert();

          String approverLink = "/pas/SelfCompentencyAssessmentApproval_getCompetencyDetails.action";
          String approverLinkParam = "competencyIDItr=" + rvrBean.getMasterCompetencyCode() + "&competencyAssesmentIDItr=" + rvrBean.getCompetencyAssementID() + "&competencyAssesmentEmployeeIDItr=" + rvrBean.getCompetencyEmployeeCode() + "&competencyAssesmentLevelItr=" + String.valueOf(findApproverForReAssessmentObj[i][1]) + "&listTypeFromMypage=reAssessed";
          templateApp.sendProcessManagerAlert(String.valueOf(findApproverForReAssessmentObj[i][0]), "Competency", "A", rvrBean.getMasterCompetencyCode(), "1", approverLinkParam, "/pas/SelfCompentencyAssessmentApproval_getCompetencyDetails.action", "Pending", 
            rvrBean.getCompetencyEmployeeCode(), "0", "", "", rvrBean.getUserEmpId());

          templateApp.sendApplicationMail();
          templateApp.clearParameters();
          templateApp.terminate();
        }
      }
      sendProcessManagerAlertForApplSubmission(rvrBean, rvrBean.getMasterCompetencyCode(), rvrBean.getUserEmpId());
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendProcessManagerAlertForApplSubmission(ReviewerCompetencyAssessment rvrBean, String applicationCode, String approverId)
  {
    try
    {
      ReviewerCompetencyAssessmentModel model = new ReviewerCompetencyAssessmentModel();
      model.initiate(this.context, this.session);
      Object[][] approverObj = model.getSqlModel().getSingleResult("SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID=" + approverId);
      String approverName = (approverObj != null) && (approverObj.length > 0) ? String.valueOf(approverObj[0][0]) : "";
      String applicantID = rvrBean.getEmpId();
      String competencyName = "";
      String employeeName = "";
      Object[][] competencyNameObj = model.getSqlModel().getSingleResult("SELECT HRMS_COMP_CONFIG.COMP_NAME, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_COMP_ASSESMENT_HDR  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID = HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID)   WHERE HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID = " + 
        rvrBean.getMasterCompetencyCode() + 
        " AND HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID =  " + rvrBean.getCompetencyAssementID() + 
        " AND HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID = " + rvrBean.getCompetencyEmployeeCode());
      if ((competencyNameObj != null) && (competencyNameObj.length > 0)) {
        competencyName = String.valueOf(competencyNameObj[0][0]);
        employeeName = String.valueOf(competencyNameObj[0][1]);
      }
      String actualStataus = "reviewed";
      String message = "Self competency assessment " + competencyName + " of " + employeeName + " has been " + "reviewed" + " by " + approverName;

      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String applicantLink = "/pas/SelfCompentencyAssesment_viewProcessedDetails.action";
      String applicantLinkParam = "competencyIDItr=" + rvrBean.getMasterCompetencyCode() + "&competencyAssementIDItr=" + rvrBean.getCompetencyAssementID() + "&applicantCodeFromMypageAlert=" + rvrBean.getCompetencyEmployeeCode();
      template.sendProcessManagerAlertWithdraw(applicantID, "Competency", "I", 
        applicationCode, "1", applicantLinkParam, "/pas/SelfCompentencyAssesment_viewProcessedDetails.action", message, 
        "reviewed", applicantID, approverId, "", "");

      String reviewerLink = "/pas/ReviewerCompetencyAssessment_getCompetencyDetails.action";
      String reviewerLinkParam = "competencyIDItr=" + rvrBean.getMasterCompetencyCode() + "&competencyAssessmentIDItr=" + rvrBean.getCompetencyAssementID() + "&competencyAssessmentEmployeeIDItr=" + rvrBean.getCompetencyEmployeeCode() + "&listTypeStatus=processed";
      template.sendProcessManagerAlertWithdraw(approverId, "Competency", "I", 
        applicationCode, "1", reviewerLinkParam, "/pas/ReviewerCompetencyAssessment_getCompetencyDetails.action", message, 
        "reviewed", applicantID, approverId, "", "");

      Object[][] approverNotToIncludeObj = model.getSqlModel().getSingleResult("SELECT NVL(HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_APPROVER_ID,0) FROM HRMS_COMP_ASSESSMENT_APPR_DTL  WHERE HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ID =  " + 
        rvrBean.getMasterCompetencyCode() + 
        " AND  HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_ID =  " + rvrBean.getCompetencyEmployeeCode() + 
        " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ASSESSMENT_ID = " + rvrBean.getCompetencyAssementID() + 
        " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_SIGNOFF_ACCEPTANCE = 'N'");
      String approverNotToInclude = "0";
      if ((approverNotToIncludeObj != null) && (approverNotToIncludeObj.length > 0)) {
        for (int i = 0; i < approverNotToIncludeObj.length; i++) {
          approverNotToInclude = approverNotToInclude + String.valueOf(approverNotToIncludeObj[i][0]) + ",";
        }
        approverNotToInclude = approverNotToInclude.substring(0, approverNotToInclude.length() - 1);
      }

      String processedApproverID = getAllApproverID(rvrBean.getCompetencyEmployeeCode(), rvrBean.getCompetencyAssementID(), approverNotToInclude);
      String processedApproverLink = "/pas/SelfCompentencyAssessmentApproval_getProcessedCompetencyDetails.action";
      String processedApproverLinkParam = "competencyIDItr=" + rvrBean.getMasterCompetencyCode() + "&competencyAssesmentIDItr=" + rvrBean.getCompetencyAssementID() + "&competencyAssesmentEmployeeIDItr=" + rvrBean.getCompetencyEmployeeCode();
      template.sendProcessManagerAlertWithdraw("", "Competency", "I", rvrBean.getMasterCompetencyCode(), "1", 
        processedApproverLinkParam, "/pas/SelfCompentencyAssessmentApproval_getProcessedCompetencyDetails.action", message, 
        "reviewed", applicantID, rvrBean.getUserEmpId(), processedApproverID, "");
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
      processAlerts.removeProcessAlert(this.reviewerBean.getMasterCompetencyCode(), "Competency");
      processAlerts.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getAllApproverID(String applicantCode, String competencyAssessmentCode, String approverIdNotToInclude)
  {
    String approverID = "";
    try {
      SelfCompentencyAssesmentApprovalModel model = new SelfCompentencyAssesmentApprovalModel();
      model.initiate(this.context, this.session);
      Object[][] approverObj = model.getSqlModel().getSingleResult("SELECT DISTINCT NVL(HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID,0) FROM HRMS_COMP_EMP_ASSESSMENT_DTL  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + 
        applicantCode + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + competencyAssessmentCode + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID NOT IN (" + approverIdNotToInclude + ")");
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
}