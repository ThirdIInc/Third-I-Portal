package org.struts.action.PAS.GoalSetting;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.ReviewerGoalAssessment;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.model.PAS.GoalSetting.ReviewerGoalAssessmentModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class ReviewerGoalAssessmentAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(ReviewerGoalAssessmentAction.class);
  ReviewerGoalAssessment reviewerGoalAssessment;

  public void prepare_local()
    throws Exception
  {
    this.reviewerGoalAssessment = new ReviewerGoalAssessment();
    this.reviewerGoalAssessment.setMenuCode(2253);
  }

  public Object getModel()
  {
    return this.reviewerGoalAssessment;
  }

  public ReviewerGoalAssessment getReviewerGoalAssessment() {
    return this.reviewerGoalAssessment;
  }

  public void setReviewerGoalAssessment(ReviewerGoalAssessment reviewerGoalAssessment)
  {
    this.reviewerGoalAssessment = reviewerGoalAssessment;
  }

  public String input() {
    try {
      ReviewerGoalAssessmentModel model = new ReviewerGoalAssessmentModel();
      model.initiate(this.context, this.session);
      this.reviewerGoalAssessment.setListType("pending");
      ArrayList goalList = model
        .getPendingGoalAssmtList(this.reviewerGoalAssessment.getUserEmpId());
      if ((goalList != null) && (goalList.size() > 0))
        this.reviewerGoalAssessment.setDataList(goalList);
      else {
        this.reviewerGoalAssessment.setNoData("true");
      }
      model.terminate();
    }
    catch (Exception localException) {
    }
    return "success";
  }

  public String getProcessedList() {
    try {
      this.reviewerGoalAssessment.setListType("processed");
      ReviewerGoalAssessmentModel model = new ReviewerGoalAssessmentModel();
      model.initiate(this.context, this.session);
      model.getProcessedList(this.reviewerGoalAssessment, this.request);
      model.terminate();
    }
    catch (Exception localException) {
    }
    return "success";
  }

  public String retrieveGoalDetails() {
    try {
      String escalationval = "N";
      System.out.println("In retrieveGoalDetails : ");
      String source = this.request.getParameter("src");
      System.out.println("Source----------------------" + source);
      this.reviewerGoalAssessment.setSource(source);
      String goalId = this.request.getParameter("goalId");
      String confgoalid = this.request.getParameter("confGoalId");
      String empId = this.request.getParameter("empId");
      String assessmentId = this.request.getParameter("asmtId");
      String assesmentDate = this.request.getParameter("assesmentDate");
      this.reviewerGoalAssessment.setScheduledAssessmentDate(assesmentDate);
      System.out.println("assesmentDate" + assesmentDate);
      System.out.println("assessmentId : " + assessmentId);
      ReviewerGoalAssessmentModel model = new ReviewerGoalAssessmentModel();
      model.initiate(this.context, this.session);
      Object[][] empObj = model.getEmployeeDetails(empId, goalId);
      model.setManagersRating(assessmentId);
      System.out.println("Goal Id : " + goalId);
      System.out.println("confgoalid : " + confgoalid);
      Object[][] escalData = model.getEsclationWorkFlowDtl(confgoalid);
      if ((escalData != null) && (escalData.length > 0))
        escalationval = String.valueOf(escalData[0][0]);
      System.out.println("The escalation Work Flow Flag Val ::::::::::::::" + escalationval);
      if ((empObj != null) && (empObj.length > 0))
      {
        this.reviewerGoalAssessment.setEmpId(empId);
        this.reviewerGoalAssessment.setGoalId(goalId);
        this.reviewerGoalAssessment.setGoalAssessmentId(assessmentId);
        this.reviewerGoalAssessment.setEmpToken(String.valueOf(empObj[0][0]));
        this.reviewerGoalAssessment.setEmpName(String.valueOf(empObj[0][1]));
        this.reviewerGoalAssessment.setEmpBrn(String.valueOf(empObj[0][2]));
        this.reviewerGoalAssessment.setEmpDept(String.valueOf(empObj[0][3]));
        this.reviewerGoalAssessment.setEmpDesg(String.valueOf(empObj[0][4]));
        this.reviewerGoalAssessment.setEmpReportingTo(
          String.valueOf(empObj[0][5]));
        this.reviewerGoalAssessment.setEmpDoj(String.valueOf(empObj[0][6]));
        this.reviewerGoalAssessment.setIsGoalCategory(String.valueOf(empObj[0][7]));
        this.reviewerGoalAssessment.setIsGoalAchievedFlag(String.valueOf(empObj[0][8]));
        this.reviewerGoalAssessment.setSysDate(String.valueOf(empObj[0][9]));
        this.reviewerGoalAssessment.setRatingrangedesc(checkNull(String.valueOf(empObj[0][10])));

        ArrayList goalList = model.getSavedRating(goalId, 
          empId, assessmentId, this.reviewerGoalAssessment, confgoalid);
        if ((goalList != null) && (goalList.size() > 0))
          this.reviewerGoalAssessment.setGoalList(goalList);
        else {
          this.reviewerGoalAssessment.setNoData("true");
        }
      }
      model.getReviewerStatus(this.reviewerGoalAssessment, assessmentId);
      System.out.println("The list Type : " + this.reviewerGoalAssessment.getListType());
      model.getApproverData(this.reviewerGoalAssessment, assessmentId);

      if (this.reviewerGoalAssessment.getListType().equals("pending"))
        getNavigationPanel(5);
      else
        getNavigationPanel(3);
      this.reviewerGoalAssessment.setEsclationworkflowFlag("true");
      this.reviewerGoalAssessment.setEnableAll("N");

      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "goalviewPage";
  }

  public String saveAcceptAssesment()
  {
    System.out.println("-----------------------------saveAcceptAssesment------------------------------");
    try {
      String source = this.request.getParameter("source");
      System.out.println("Source--------------------" + source);
      this.reviewerGoalAssessment.setSource(source);

      ReviewerGoalAssessmentModel model = new ReviewerGoalAssessmentModel();
      model.initiate(this.context, this.session);
      String[] assessmentId = this.request.getParameterValues("hiddenassessmentId");
      String[] hiddenlevel = this.request.getParameterValues("hiddenlevel");
      String[] hiddenacceptflag = this.request.getParameterValues("hiddenacceptflag");
      String[] reviewercomment = this.request.getParameterValues("reviewercomment");
      String[] hiddenApproverId = this.request.getParameterValues("hiddenApproverId");
      if (assessmentId != null)
        System.out.println("assessmentId :: Length  : : " + assessmentId.length);
      System.out.println("hiddenlevel :: Length  : : " + hiddenlevel.length);
      System.out.println("hiddenacceptflag :: Length  : : " + hiddenacceptflag.length);
      System.out.println("employeecomment :: Length  : : " + reviewercomment.length);
      System.out.println("hiddenApproverId :: Length  : : " + hiddenApproverId.length);
      boolean result = model.updateApproveData(assessmentId, hiddenlevel, hiddenacceptflag, reviewercomment, hiddenApproverId);
      System.out.println("reviewerGoalAssessment.getGoalAssessmentId() : " + this.reviewerGoalAssessment.getGoalAssessmentId());
      result = model.updateAssessmentHeader(this.reviewerGoalAssessment.getGoalAssessmentId(), this.reviewerGoalAssessment.getUserEmpId());

      Object[][] confGoalID1 = model.getSqlModel().getSingleResult("SELECT GOAL_ID FROM HRMS_GOAL_EMP_HDR WHERE GOAL_HDR_ID=" + this.reviewerGoalAssessment.getGoalId());
      Object[][] reprtingType1 = model.getSqlModel().getSingleResult("SELECT GOAL_REPORTING FROM HRMS_GOAL_CONFIG WHERE GOAL_ID=" + confGoalID1[0][0]);
      String reprtingType = String.valueOf(reprtingType1[0][0]);
      String confGoalID = String.valueOf(confGoalID1[0][0]);

      sendProcessManagerAlertForApplSubmission(this.reviewerGoalAssessment.getGoalAssessmentId(), this.reviewerGoalAssessment.getUserEmpId(), 
        reprtingType, confGoalID);
      model.terminate();
      getNavigationPanel(3);
      this.reviewerGoalAssessment.setEnableAll("N");
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.reviewerGoalAssessment.getSource().equals("mymessages"))
      return "mymessages";
    if (this.reviewerGoalAssessment.getSource().equals("myservices")) {
      return "serviceJsp";
    }

    return "goalviewPage";
  }

  public void sendProcessManagerAlertForApplSubmission(String applnID, String reviewerID, String reportingValue, String ConfGoalIDValue)
  {
    try
    {
      ReviewerGoalAssessmentModel model = new ReviewerGoalAssessmentModel();
      model.initiate(this.context, this.session);
      String alertlink = "";
      String linkParam = "";
      String reviewerName = "";
      String assessLevel = "";
      String assesmentDate = "";
      String applicantID = this.reviewerGoalAssessment.getEmpId();
      String employeeName = this.reviewerGoalAssessment.getEmpName();

      FileInputStream alertFin = new FileInputStream(getText("data_path") + "\\Alerts\\alertLinks.properties");
      Properties alertProp = new Properties();
      alertProp.load(alertFin);
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String message = "";
      Object[][] assessLevelObj = model.getSqlModel()
        .getSingleResult("SELECT HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSER_LEVEL,TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE  HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID =" + 
        applnID);
      if ((assessLevelObj != null) && (assessLevelObj.length > 0)) {
        assessLevel = String.valueOf(assessLevelObj[0][0]);
        assesmentDate = String.valueOf(assessLevelObj[0][1]);
      }

      Object[][] reviewerNameObj = model.getSqlModel().getSingleResult("SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID = " + this.reviewerGoalAssessment.getUserEmpId());
      if ((reviewerNameObj != null) && (reviewerNameObj.length > 0)) {
        reviewerName = String.valueOf(reviewerNameObj[0][0]);
      }

      try
      {
        MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
        processAlerts.initiate(this.context, this.session);
        processAlerts.removeProcessAlert(String.valueOf(applnID), 
          "Goal Setting");
      } catch (Exception e) {
        e.printStackTrace();
      }

      Object[][] approvarObj = model.getSqlModel().getSingleResult("SELECT HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_ID FROM HRMS_GOAL_ASSESSMENT_APPR_DTL WHERE HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_SIGNOFF_ACCEPTANCE='N' AND HRMS_GOAL_ASSESSMENT_APPR_DTL.EMP_GOAL_ASSESSMENT_ID =" + applnID);

      String finalApproverId = "";
      if ((approvarObj != null) && (approvarObj.length > 0)) {
        for (int i = 0; i < approvarObj.length; i++) {
          finalApproverId = finalApproverId + String.valueOf(approvarObj[i][0]) + ",";
        }
        finalApproverId = finalApproverId.substring(0, finalApproverId.length() - 1);
      }

      message = alertProp.getProperty("approverReviewedForwardedAlertMessgae");
      message = message.replace("<#MODULE#>", "Employee Goal Reassessment");
      message = message.replace("<#EMPLOYEE#>", employeeName);
      message = message.replace("<#STATUS#>", "reviewed");
      message = message.replace("<#REVIEWER_NAME#>", reviewerName);

      if (!checkNull(finalApproverId).equals("")) {
        alertlink = "/pas/EmpGoalAssessment_retrieveGoalDetails.action";
        System.out.println("alertlink     alertlink      " + alertlink);

        linkParam = "goalId=" + this.reviewerGoalAssessment.getGoalId() + "&empId=" + 
          applicantID + "&asmtId=" + applnID + "&confgoalId=" + ConfGoalIDValue + "&reportingType=" + reportingValue + "&assessLevel=" + Integer.parseInt(assessLevel) + 
          "&listType=reassessment&assessmentType=reassessment&goalRatingAccess=false&goalCommentsAccess=false";
        System.out.println("linkParam     linkParam      " + linkParam);
        template.sendProcessManagerAlertWithdraw("", "Goal Setting", "A", 
          applnID, "1", linkParam, alertlink, message, 
          "Pending", applicantID, this.reviewerGoalAssessment.getUserEmpId(), finalApproverId, "");
      }

      message = alertProp.getProperty("applicationAlertMessage");
      message = message.replace("<#MODULE#>", "Self Goal assessment ");
      message = message.replace("<#EMPLOYEE_NAME#>", employeeName);
      message = message.replace("<#STATUS#>", "reviewed");
      message = message.replace("<#APPROVER_NAME#>", reviewerName);

      alertlink = "/pas/SelfGoalAssessment_retrieveGoalDetails.action";

      System.out.println("Date" + assesmentDate);
      linkParam = "goalId=" + this.reviewerGoalAssessment.getGoalId() + "&empId=" + applicantID + 
        "&asmtId=" + applnID + "&confGoalId=" + ConfGoalIDValue + "&assesmentDate=" + assesmentDate + "&listType=processed" + "&assesmentDate=" + assesmentDate;

      template.sendProcessManagerAlertWithdraw(applicantID, "Goal Setting", "I", 
        applnID, "1", linkParam, alertlink, message, 
        "Processed", applicantID, reviewerID, "", "");

      alertlink = "/pas/ReviewerGoalAssessment_retrieveGoalDetails.action";
      linkParam = "goalId=" + this.reviewerGoalAssessment.getGoalId() + 
        "&empId=" + applicantID + "&asmtId=" + applnID + "&confGoalId=" + ConfGoalIDValue + "&listType=processed" + "&assesmentDate=" + assesmentDate;
      template.sendProcessManagerAlertWithdraw(reviewerID, "Goal Setting", "I", 
        applnID, "1", linkParam, alertlink, message, 
        "Processed", applicantID, reviewerID, "", reviewerID);

      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("")) || (result.equals("null"))) {
      return "";
    }
    return result;
  }
}