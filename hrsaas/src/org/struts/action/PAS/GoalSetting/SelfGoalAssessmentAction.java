package org.struts.action.PAS.GoalSetting;

import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.SelfGoalAssessment;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.PAS.GoalSetting.EmployeeGoalSettingModel;
import org.paradyne.model.PAS.GoalSetting.SelfGoalAssessmentModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class SelfGoalAssessmentAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(SelfGoalAssessmentAction.class);
  SelfGoalAssessment selfGoalAssessment;

  public void prepare_local()
    throws Exception
  {
    this.selfGoalAssessment = new SelfGoalAssessment();
    this.selfGoalAssessment.setMenuCode(1139);
  }

  public Object getModel()
  {
    return this.selfGoalAssessment;
  }

  public SelfGoalAssessment getSelfGoalAssessment() {
    return this.selfGoalAssessment;
  }

  public void setSelfGoalAssessment(SelfGoalAssessment selfGoalAssessment) {
    this.selfGoalAssessment = selfGoalAssessment;
  }

  public String input()
  {
    try {
      System.out.println("In input-------------------------------------------");
      SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
      model.initiate(this.context, this.session);
      this.selfGoalAssessment.setListType("pending");
      ArrayList goalList = model
        .getPendingGoalAssmtList(this.selfGoalAssessment.getUserEmpId());
      if ((goalList != null) && (goalList.size() > 0))
        this.selfGoalAssessment.setDataList(goalList);
      else {
        this.selfGoalAssessment.setNoData("true");
      }
      model.terminate();
    }
    catch (Exception localException) {
    }
    return "success";
  }

  public String getProcessedList() {
    try {
      System.out.println("in getProcessedList--------------------------------");
      this.selfGoalAssessment.setListType("processed");
      SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
      model.initiate(this.context, this.session);
      model.getProcessedList(this.selfGoalAssessment, this.request);
      model.terminate();
    }
    catch (Exception localException) {
    }
    return "success";
  }
  public String getReassessmentList() {
    try {
      System.out.println("in getReassessmentList-------------------------------");
      this.selfGoalAssessment.setListType("reassessment");
      SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
      model.initiate(this.context, this.session);
      model.getReassessmentList(this.selfGoalAssessment, this.request);
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String saveAcceptAssesment()
  {
    try {
      System.out.println("in------------saveAcceptAssesment");
      SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
      model.initiate(this.context, this.session);
      String[] assessmentId = this.request.getParameterValues("hiddenassessmentId");
      String[] hiddenlevel = this.request.getParameterValues("hiddenlevel");
      String[] hiddenacceptflag = this.request.getParameterValues("hiddenacceptflag");
      String[] employeecomment = this.request.getParameterValues("employeecomment");
      String[] hiddenApproverId = this.request.getParameterValues("hiddenApproverId");
      if (assessmentId != null)
      {
        String escalationFlowVal = "N";
        boolean flag = true;
        Object[][] escalData = model.getEsclationWorkFlowDtl(this.selfGoalAssessment.getConfgoalId());
        if ((escalData != null) && (escalData.length > 0))
          escalationFlowVal = String.valueOf(escalData[0][0]);
        System.out.println("escalationFlowVal--------" + escalationFlowVal);
        Object[][] ReviewDetObj = (Object[][])null;
        if (escalationFlowVal.equals("Y"))
        {
          String ReviewDet = "SELECT REPORTINGDTL_CODE FROM  HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE in(SELECT HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_CODE FROM HRMS_REPTNG_STRUCTHDR_GOAL where  REPORTINGHDR_EMP_ID=" + 
            this.selfGoalAssessment.getEmpId() + ") AND REPORTINGDTL_APPROVER_TYPE='R'";

          ReviewDetObj = model.getSqlModel().getSingleResult(ReviewDet);
          if ((ReviewDetObj == null) && (ReviewDetObj.length == 0)) {
            flag = false;
          }
        }

        if (flag) {
          boolean result = model.updateApproveData(assessmentId, hiddenlevel, hiddenacceptflag, employeecomment, hiddenApproverId);
          model.updateAssessmentHeader(this.selfGoalAssessment.getGoalAssessmentId(), this.selfGoalAssessment.getUserEmpId());
          if (result)
          {
            try
            {
              MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
              processAlerts.initiate(this.context, this.session);
              processAlerts.removeProcessAlert(this.selfGoalAssessment.getGoalAssessmentId(), "Goal Setting");
              processAlerts.terminate();
            } catch (Exception e) {
              e.printStackTrace();
            }

            sendMailReviewer(this.selfGoalAssessment.getEmpId(), 
              this.selfGoalAssessment.getUserEmpId(), this.selfGoalAssessment.getGoalAssessmentId(), 
              this.selfGoalAssessment.getConfgoalId(), this.selfGoalAssessment.getGoalId());
          }
        }
      }
      else
      {
        addActionMessage("Unable to Submit Application. Please Define Reviewer in Goal Reporting Structure");
      }
      model.terminate();
      getNavigationPanel(3);
      this.selfGoalAssessment.setEnableAll("N");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return input();
  }

  public String retrieveGoalDetails()
  {
    try
    {
      System.out.println("retrieveGoalDetailsretrieveGoalDetailsretrieveGoalDetailsretrieveGoalDetailsretrieveGoalDetailsretrieveGoalDetails");
      String source = this.request.getParameter("src");
      System.out.println("Source----------------------" + source);
      this.selfGoalAssessment.setSource(source);
      String escalationval = "N";
      System.out.println("In retrieveGoalDetails : ");
      String goalId = this.request.getParameter("goalId");
      String confgoalid = this.request.getParameter("confGoalId");
      String assesmentDate = this.request.getParameter("assesmentDate");
      System.out.println("assesmentDate" + assesmentDate);
      this.selfGoalAssessment.setScheduledAssessmentDate(assesmentDate);
      String empId = this.request.getParameter("empId");
      String assessmentId = this.request.getParameter("asmtId");
      System.out.println("assessmentId : " + assessmentId);
      SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
      model.initiate(this.context, this.session);
      Object[][] empObj = model.getEmployeeDetails(empId, goalId);
      model.setManagersRating(assessmentId);
      System.out.println("Goal Id : " + goalId);
      System.out.println("confgoalid : " + confgoalid);
      this.selfGoalAssessment.setConfgoalId(confgoalid);
      Object[][] escalData = model.getEsclationWorkFlowDtl(confgoalid);
      if ((escalData != null) && (escalData.length > 0))
        escalationval = String.valueOf(escalData[0][0]);
      System.out.println("The escalation Work Flow Flag Val ::::::::::::::" + escalationval);
      if ((empObj != null) && (empObj.length > 0))
      {
        this.selfGoalAssessment.setEmpId(empId);
        this.selfGoalAssessment.setGoalId(goalId);
        this.selfGoalAssessment.setGoalAssessmentId(assessmentId);
        this.selfGoalAssessment.setEmpToken(String.valueOf(empObj[0][0]));
        this.selfGoalAssessment.setEmpName(String.valueOf(empObj[0][1]));
        this.selfGoalAssessment.setEmpBrn(String.valueOf(empObj[0][2]));
        this.selfGoalAssessment.setEmpDept(String.valueOf(empObj[0][3]));
        this.selfGoalAssessment.setEmpDesg(String.valueOf(empObj[0][4]));
        this.selfGoalAssessment.setEmpReportingTo(
          String.valueOf(empObj[0][5]));
        this.selfGoalAssessment.setEmpDoj(String.valueOf(empObj[0][6]));
        this.selfGoalAssessment.setIsGoalCategory(String.valueOf(empObj[0][7]));
        this.selfGoalAssessment.setIsGoalAchievedFlag(String.valueOf(empObj[0][8]));
        this.selfGoalAssessment.setSysDate(String.valueOf(empObj[0][9]));
        this.selfGoalAssessment.setRatingrangedesc(checkNull(String.valueOf(empObj[0][10])));
        this.selfGoalAssessment.setReportingType(checkNull(String.valueOf(empObj[0][11])));
        this.selfGoalAssessment.setAssessLevel(checkNull(String.valueOf(empObj[0][12])));
        this.selfGoalAssessment.setGoalName(checkNull(String.valueOf(empObj[0][13])));
        ArrayList goalList = model.getSavedRating(goalId, 
          empId, assessmentId, this.selfGoalAssessment, confgoalid);
        if ((goalList != null) && (goalList.size() > 0))
          this.selfGoalAssessment.setGoalList(goalList);
        else {
          this.selfGoalAssessment.setNoData("true");
        }
      }
      if (this.selfGoalAssessment.getListType().equals("pending")) {
        if (this.selfGoalAssessment.getIsRecordSaved().equals("true"))
          getNavigationPanel(2);
        else
          getNavigationPanel(1);
      }
      else {
        String goalStatusQuery = " SELECT GOAL_ASSESSMENT_STATUS, GOAL_IS_SIGNOFF, GOAL_SIGNOFF_ACCEPTANCE FROM  HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE EMP_GOAL_ASSESSMENT_ID = " + 
          assessmentId;
        Object[][] goalStatus = model.getSqlModel().getSingleResult(goalStatusQuery);

        if ((goalStatus != null) && (goalStatus.length > 0)) {
          if ((String.valueOf(goalStatus[0][0]).equals("2")) && (String.valueOf(goalStatus[0][1]).equals("Y")) && (String.valueOf(goalStatus[0][2]).equals("N"))) {
            if (escalationval.equals("Y"))
            {
              System.out.println("In this if case :;;");
              model.getApproverData(this.selfGoalAssessment, assessmentId);
              String query = "SELECT GOAL_EMP_COMMENTS FROM HRMS_GOAL_ASSESSMENT_APPR_DTL WHERE EMP_GOAL_ASSESSMENT_ID =" + this.selfGoalAssessment.getGoalAssessmentId() + " AND GOAL_EMP_ID = " + this.selfGoalAssessment.getEmpId() + " AND GOAL_EMP_COMMENTS is null AND GOAL_SIGNOFF_ACCEPTANCE IS NULL";
              System.out.println("The Query : : " + query);
              Object[][] data = model.getSqlModel().getSingleResult(query);
              System.out.println("data::::::----" + data);
              System.out.println("The Data Length :: " + data.length);
              if ((data != null) && (data.length > 0))
              {
                getNavigationPanel(5);
              }
              else
              {
                String sqlQuery = "SELECT HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_REASSESSMENT_STATUS  FROM HRMS_GOAL_ASSESSMENT_APPR_DTL INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_ASSESSMENT_APPR_DTL.EMP_GOAL_ASSESSMENT_ID) WHERE HRMS_GOAL_ASSESSMENT_APPR_DTL.EMP_GOAL_ASSESSMENT_ID = " + this.selfGoalAssessment.getGoalAssessmentId() + " AND HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_EMP_ID = " + this.selfGoalAssessment.getEmpId() + " AND HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_SIGNOFF_ACCEPTANCE='N' AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEWER_STATUS='Y'";
                Object[][] dataObj = model.getSqlModel().getSingleResult(sqlQuery);
                if ((dataObj != null) && (dataObj.length > 0))
                {
                  int count = 0;
                  for (int i = 0; i < dataObj.length; i++)
                  {
                    if (String.valueOf(dataObj[i][0]).equals("Y"))
                      count++;
                  }
                  System.out.println("count ::: " + count);
                  System.out.println("dataObj.length ::: " + dataObj.length);
                  if (count == dataObj.length)
                  {
                    System.out.println("Inside If :: ");
                    getNavigationPanel(6);
                  }
                  else
                  {
                    System.out.println("Inside else :: ");
                    getNavigationPanel(3);
                  }

                }
                else
                {
                  getNavigationPanel(3);
                }

              }

              this.selfGoalAssessment.setEsclationworkflowFlag("true");
              this.selfGoalAssessment.setEnableAll("N");
            }
            else
            {
              System.out.println("In this else case :;;");
              getNavigationPanel(4);
              this.selfGoalAssessment.setEnableAll("N");
            }
          }
          else {
            getNavigationPanel(3);
            this.selfGoalAssessment.setEnableAll("N");
          }
        }
      }
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "goalviewPage";
  }

  public String saveRating()
  {
    System.out.println("------------------------saveRating");
    SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
    model.initiate(this.context, this.session);
    boolean result = false;
    result = model.saveRating(this.selfGoalAssessment, this.request);
    if (result) {
      getRatingList();
      addActionMessage("Record saved successfully");
    } else {
      addActionMessage("Record can not be saved");
    }
    getNavigationPanel(2);

    model.terminate();
    return "goalviewPage";
  }

  public String getRatingList()
  {
    try {
      System.out.println("-----------------------getRatingList-----------------");
      SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
      model.initiate(this.context, this.session);
      logger.info("getRatingList");
      if (this.selfGoalAssessment.getListType().equals("processed")) {
        model.setManagersRating(this.selfGoalAssessment.getGoalAssessmentId());
      }
      System.out.println(" getRatingList   : selfGoalAssessment.getConfgoalId() " + this.selfGoalAssessment.getConfgoalId());
      ArrayList goalList = model.getSavedRating(this.selfGoalAssessment.getGoalId(), this.selfGoalAssessment.getEmpId(), this.selfGoalAssessment.getGoalAssessmentId(), this.selfGoalAssessment, this.selfGoalAssessment.getConfgoalId());

      if ((goalList != null) && (goalList.size() > 0))
        this.selfGoalAssessment.setGoalList(goalList);
      else {
        this.selfGoalAssessment.setNoData("true");
      }

      model.terminate();
    }
    catch (Exception localException) {
    }
    return "goalviewPage";
  }

  public String finalizeAssesment() {
    try {
      System.out.println("----------finalizeAssesment-------------------" + this.selfGoalAssessment.getListType());
      System.out.println("----------Reprting type-------------------" + this.selfGoalAssessment.getReportingType());
      System.out.println("----------assetment level-------------------" + this.selfGoalAssessment.getAssessLevel());
      EmployeeGoalSettingModel repModel = new EmployeeGoalSettingModel();
      repModel.initiate(this.context, this.session);
      SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
      model.initiate(this.context, this.session);
      String goalAssessLevel = " SELECT GOAL_ASSESSER_LEVEL FROM  HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE GOAL_ID =" + 
        this.selfGoalAssessment.getGoalId() + "AND EMP_GOAL_ASSESSMENT_ID=" + this.selfGoalAssessment.getGoalAssessmentId();

      Object[][] goalAssessLevel1 = model.getSqlModel().getSingleResult(goalAssessLevel);
      String asserLevel = (goalAssessLevel1 != null) && (goalAssessLevel1.length > 0) ? String.valueOf(goalAssessLevel1[0][0]) : "";
     
      System.out.println("approverId   Lakki  : "+this.selfGoalAssessment.getReportingType());
      Object[][] empFlow = repModel.generateEmpFlow(this.selfGoalAssessment.getEmpId(), 
    	      Integer.parseInt(asserLevel), this.selfGoalAssessment.getReportingType());
      repModel.terminate();
      String approverId="";
      if ((empFlow != null) && (empFlow.length > 0)) {
    	  approverId=String.valueOf(empFlow[0][0]);
    	    System.out.println("approverId   Lakki"+approverId);
    	    }
      
      boolean result = false;
      boolean flag = false;
      result = model.saveRating(this.selfGoalAssessment, this.request);

      if (result)
      {
        if (this.selfGoalAssessment.getReportingType().equals("goal")) {
          String approvarQuery = "SELECT REPORTINGDTL_CODE,REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS FROM  HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE in(SELECT HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_CODE  FROM HRMS_REPTNG_STRUCTHDR_GOAL where  REPORTINGHDR_EMP_ID=" + 
            this.selfGoalAssessment.getEmpId() + ") AND REPORTINGDTL_APPROVER_TYPE='A'";
          Object[][] approvarQueryObj = model.getSqlModel().getSingleResult(approvarQuery);
          if ((approvarQueryObj != null) && (approvarQueryObj.length > 0)) {
            for (int i = 0; i < approvarQueryObj.length; i++) {
              if (String.valueOf(approvarQueryObj[i][1]).equals("Y"))
                flag = true;
              else
                flag = false;
            }
          }
          else
            flag = false;
        }
        else {
          flag = true;
        }
        if (flag) {
          result = model.finalizeAssesment(
            this.selfGoalAssessment.getGoalAssessmentId(), this.selfGoalAssessment.getGoalId(),approverId);
          if (result)
          {
            try {
              MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
              processAlerts.initiate(this.context, this.session);
              processAlerts.removeProcessAlert(this.selfGoalAssessment.getGoalId(), "Goal Setting");
              processAlerts.terminate();
            } catch (Exception e) {
              e.printStackTrace();
            }
            addActionMessage("Record finalized successfully");
            sendMail(this.selfGoalAssessment.getEmpId(), this.selfGoalAssessment.getUserEmpId(), 
              this.selfGoalAssessment.getGoalAssessmentId(), this.selfGoalAssessment.getGoalId(), 
              this.selfGoalAssessment.getConfgoalId(), this.selfGoalAssessment.getReportingType(), 
              asserLevel);
          } else {
            addActionMessage("Record can not be finalized");
          }
        }
        else {
          addActionMessage("Please give  View/Rating  access to the Approvers in goal Reporting structure ");
        }
      }
      getRatingList();
      getNavigationPanel(3);
      this.selfGoalAssessment.setEnableAll("N");
      model.terminate();
    }
    catch (Exception localException1) {
    }
    return input();
  }

  public void sendMail(String applicantID, String approverID, String applnID, String goalId, String confGoalId, String reprtingType, String assetLevel)
  {
    try
    {
      System.out.println("------------------sendMail-------------------------");
      System.out.println("applicantID" + applicantID);
      System.out.println("approverID" + approverID);
      System.out.println("applnID" + applnID);
      System.out.println("goalId" + goalId);
      System.out.println("reprotingType" + reprtingType);
      SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
      model.initiate(this.context, this.session);

      String alertlink = "";
      String linkParam = "";
      String module = "Goal Setting";
      String level = "1";

      String query = " SELECT GOAL_ASSESSER_ID FROM  HRMS_GOAL_EMP_ASSESSMENT_HDR  WHERE EMP_GOAL_ASSESSMENT_ID=" + 
        applnID;

      Object[][] approver = model.getSqlModel().getSingleResult(query);

      if ((approver != null) && (approver.length > 0))
      {
        approverID = String.valueOf(approver[0][0]);

        System.out.println("applicantID " + applicantID);
        System.out.println("approverID  " + approverID);
        System.out.println("applnID  " + applnID);

        EmailTemplateBody template = new EmailTemplateBody();
        template.initiate(this.context, this.session);
        template
          .setEmailTemplate("Mail to employee regarding self goal  assessment application submission");
        template.getTemplateQueries();
        try
        {
          EmailTemplateQuery templateQuery1 = template
            .getTemplateQuery(1);
          templateQuery1.setParameter(1, applicantID);
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

          templateQuery3.setParameter(1, applicantID);
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
          templateQuery6.setParameter(1, confGoalId);
        }
        catch (Exception localException6) {
        }
        try {
          template.configMailAlert();

          alertlink = "/pas/SelfGoalAssessment_retrieveGoalDetails.action";

          String confgoalid = this.request.getParameter("confGoalId");
          String empId = this.request.getParameter("empId");
          String goalId1 = this.request.getParameter("goalId");
          String assessmentId = this.request.getParameter("asmtId");

          Object[][] selectDateObj = model.getSqlModel().getSingleResult("SELECT GOAL_REVIEWER_STATUS,TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE EMP_GOAL_ASSESSMENT_ID =" + applnID);
          String assesmentDate = "";
          if ((selectDateObj != null) && (selectDateObj.length > 0)) {
            assesmentDate = String.valueOf(selectDateObj[0][1]);
          }
          linkParam = "goalId=" + goalId + "&empId=" + 
            applicantID + "&asmtId=" + applnID + "&confGoalId=" + confGoalId + "&assesmentDate=" + assesmentDate + "&listType=processed";

          template.sendProcessManagerAlert("", module, 
            "I", applnID, level, linkParam, alertlink, 
            "Pending", applicantID, "", 
            "", applicantID, applicantID);

          template.sendApplicationMail();
          template.clearParameters();
          template.terminate();
        }
        catch (Exception localException7)
        {
        }

        EmailTemplateBody template_applicant = new EmailTemplateBody();
        template_applicant.initiate(this.context, this.session);

        template_applicant
          .setEmailTemplate("SELF GOAL ASSESSMENT  APPL-APPLICANT TO APPROVER");
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
          templateQueryApp6.setParameter(1, confGoalId);
        }
        catch (Exception localException13) {
        }
        try {
          template_applicant.configMailAlert();

          alertlink = "/pas/EmpGoalAssessment_retrieveGoalDetails.action";
          System.out.println("alertlink          " + alertlink);
          linkParam = "goalId=" + goalId + "&empId=" + 
            applicantID + "&asmtId=" + applnID + "&confgoalId=" + confGoalId + "&reportingType=" + reprtingType + "&assessLevel=" + assetLevel + 
            "&listType=pending&goalRatingAccess=false&goalCommentsAccess=false";
          try
          {
            template_applicant.sendProcessManagerAlert(approverID, module, "A", applnID, level, linkParam, alertlink, "Pending", applicantID, "", "", "", applicantID);
          } catch (Exception e) {
            e.printStackTrace();
          }

          template_applicant.sendApplicationMail();
          template_applicant.clearParameters();
          template_applicant.terminate();
        }
        catch (Exception localException14)
        {
        }
      }
    }
    catch (Exception localException15)
    {
    }
  }

  public void sendMailReviewer(String applicantID, String approverID, String applnID, String confgoalId, String goalId) {
    try {
      System.out.println("-------------------sendMailReviewer--------------------");

      SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
      model.initiate(this.context, this.session);

      String query = " SELECT GOAL_REVIEWER_ID,GOAL_ASSESSER_ID FROM  HRMS_GOAL_EMP_ASSESSMENT_HDR  WHERE EMP_GOAL_ASSESSMENT_ID=" + 
        applnID;

      Object[][] approver = model.getSqlModel().getSingleResult(query);

      String reviewerId = "";
      String alertlink = "";
      String linkParam = "";
      String module = "Goal Setting";
      String level = "1";

      if ((approver != null) && (approver.length > 0))
      {
        reviewerId = String.valueOf(approver[0][0]);
        approverID = String.valueOf(approver[0][1]);

        System.out.println("reviewerId " + reviewerId);

        System.out.println("applicantID " + applicantID);
        System.out.println("approverID  " + approverID);
        System.out.println("applnID  " + applnID);

        EmailTemplateBody template = new EmailTemplateBody();
        template.initiate(this.context, this.session);
        template
          .setEmailTemplate("Mail to reviewer for review goal assessment");
        template.getTemplateQueries();
        try
        {
          EmailTemplateQuery templateQuery1 = template
            .getTemplateQuery(1);
          templateQuery1.setParameter(1, applicantID);
        }
        catch (Exception localException) {
        }
        try {
          EmailTemplateQuery templateQuery2 = template
            .getTemplateQuery(2);
          templateQuery2.setParameter(1, reviewerId);
        }
        catch (Exception localException1)
        {
        }
        try {
          EmailTemplateQuery templateQuery3 = template
            .getTemplateQuery(3);

          templateQuery3.setParameter(1, applicantID);
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
          templateQuery6.setParameter(1, reviewerId);
        }
        catch (Exception localException5) {
        }
        try {
          EmailTemplateQuery templateQuery7 = template
            .getTemplateQuery(7);
          templateQuery7.setParameter(1, confgoalId);
        }
        catch (Exception localException6) {
        }
        try {
          EmailTemplateQuery templateQuery8 = template
            .getTemplateQuery(8);
          templateQuery8.setParameter(1, applnID);
        }
        catch (Exception localException7) {
        }
        try {
          template.configMailAlert();
          String sqlQuery = "SELECT NVL(GOAL_ESCALATION_EMAIL,'') FROM HRMS_GOAL_CONFIG WHERE GOAL_ID = " + confgoalId;
          Object[][] selectDataObj = model.getSqlModel().getSingleResult(sqlQuery);
          String[] splitedEmailds = (String[])null;
          String[] attachFile = (String[])null;
          if ((selectDataObj != null) && (selectDataObj.length > 0)) {
            splitedEmailds = String.valueOf(selectDataObj[0][0]).split(";");
          }
          if ((splitedEmailds != null) && (splitedEmailds.length > 0)) {
            template.sendMailToCCEmailIdsWithAttachment(splitedEmailds, 
              attachFile);
          }

          String queryDate = "SELECT GOAL_REVIEWER_STATUS,TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE EMP_GOAL_ASSESSMENT_ID =" + applnID;
          Object[][] selectDateObj = model.getSqlModel().getSingleResult(queryDate);
          String assesmentDate = "";
          if ((selectDateObj != null) && (selectDateObj.length > 0)) {
            assesmentDate = String.valueOf(selectDateObj[0][1]);
          }
          System.out.println("Date" + assesmentDate);

          alertlink = "/pas/ReviewerGoalAssessment_retrieveGoalDetails.action";
          linkParam = "goalId=" + goalId + "&empId=" + applicantID + "&asmtId=" + applnID + "&confGoalId=" + confgoalId + "&listType=pending" + "&assesmentDate=" + assesmentDate;
          template.sendProcessManagerAlert(reviewerId, module, "A", applnID, 
            level, linkParam, alertlink, "Pending", applicantID, 
            "", "", "", applicantID);
          linkParam = "goalId=" + goalId + "&empId=" + applicantID + "&asmtId=" + applnID + "&confGoalId=" + confgoalId + "&listType=";

          alertlink = "/pas/SelfGoalAssessment_retrieveGoalDetails.action";

          linkParam = "goalId=" + goalId + "&empId=" + applicantID + "&asmtId=" + applnID + 
            "&confGoalId=" + confgoalId + "&listType=processed" + "&assesmentDate=" + assesmentDate;
          template.sendProcessManagerAlert("", module, "I", applnID, 
            level, linkParam, alertlink, "Pending", applicantID, 
            "", "", applicantID, applicantID);
          template.sendApplicationMail();
          template.clearParameters();
          template.terminate();
        }
        catch (Exception localException8)
        {
        }
      }
    }
    catch (Exception localException9)
    {
    }
  }

  public String acceptRejectSignOff()
  {
    boolean result = false;
    try {
      System.out.println("------------------------acceptRejectSignOff----------------------");
      SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
      model.initiate(this.context, this.session);
      String goalSignOffStatus = this.selfGoalAssessment.getSignOffStatus();
      result = model.acceptRejectSignOffGoal(this.selfGoalAssessment, goalSignOffStatus);
      if (result) {
        if (goalSignOffStatus.equals("Y"))
          addActionMessage("Evaluation sign off accepted");
        else
          addActionMessage("Evaluation sign off rejected");
        try
        {
          MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
          processAlerts.initiate(this.context, this.session);
          processAlerts.removeProcessAlert(this.selfGoalAssessment.getGoalAssessmentId(), "Goal Setting");
          processAlerts.terminate();
        } catch (Exception e) {
          e.printStackTrace();
        }
        sendSignOffStatusMail(this.selfGoalAssessment.getUserEmpId(), this.selfGoalAssessment.getGoalAssessmentId());
      } else {
        addActionMessage("Error processing sign off");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return input();
  }

  public void sendSignOffStatusMail(String applicantID, String applnID) {
    String linkParam = "";
    String alertlink = "";
    String module = "Goal Setting";
    String level = "1";
    try {
      System.out.println("sendSignOffStatusMail");
      SelfGoalAssessmentModel model = new SelfGoalAssessmentModel();
      model.initiate(this.context, this.session);
      String query = " SELECT GOAL_ID FROM  HRMS_GOAL_EMP_ASSESSMENT_HDR  WHERE EMP_GOAL_ASSESSMENT_ID=" + 
        applnID;

      Object[][] goalId = model.getSqlModel().getSingleResult(query);

      String approverID = "";
      String goalPathId = "";
      String finalApproverID = "";
      String[] approverIds = (String[])null;
      if ((goalId != null) && (goalId.length > 0)) {
        goalPathId = String.valueOf(goalId[0][0]);
        String approverQuery = " SELECT GOAL_APPROVER FROM HRMS_GOAL_PATH WHERE GOAL_CODE=" + 
          goalPathId;
        Object[][] approverIdsObj = model.getSqlModel().getSingleResult(approverQuery);

        if ((approverIdsObj != null) && (approverIdsObj.length > 0))
        {
          approverIds = new String[approverIdsObj.length];
          for (int i = 0; i < approverIdsObj.length; i++) {
            approverIds[i] = String.valueOf(approverIdsObj[i][0]);
            finalApproverID = String.valueOf(approverIdsObj[i][0]) + ",";
          }
        }
      }
      finalApproverID = finalApproverID.substring(0, finalApproverID.length() - 1);

      EmailTemplateBody template_applicant = new EmailTemplateBody();
      template_applicant.initiate(this.context, this.session);

      template_applicant
        .setEmailTemplate("SELF GOAL ASSESSMENT SIGNOFF MAIL TO APPROVER");
      template_applicant.getTemplateQueries();
      try
      {
        EmailTemplateQuery templateQueryApp1 = template_applicant
          .getTemplateQuery(1);
        templateQueryApp1.setParameter(1, applicantID);
      }
      catch (Exception localException1) {
      }
      try {
        EmailTemplateQuery templateQueryApp2 = template_applicant
          .getTemplateQuery(2);
        templateQueryApp2.setParameter(1, applicantID);
      }
      catch (Exception localException2)
      {
      }
      try {
        EmailTemplateQuery templateQueryApp3 = template_applicant
          .getTemplateQuery(3);
        templateQueryApp3.setParameter(1, applicantID);
      }
      catch (Exception localException3) {
      }
      try {
        EmailTemplateQuery templateQueryApp4 = template_applicant
          .getTemplateQuery(4);
        templateQueryApp4.setParameter(1, applnID);
      }
      catch (Exception localException4) {
      }
      try {
        EmailTemplateQuery templateQueryApp5 = template_applicant
          .getTemplateQuery(5);
        templateQueryApp5.setParameter(1, goalPathId);
      }
      catch (Exception localException5) {
      }
      try {
        EmailTemplateQuery templateQueryApp6 = template_applicant
          .getTemplateQuery(6);
        templateQueryApp6.setParameter(1, applnID);
      }
      catch (Exception localException6)
      {
      }
      try {
        EmailTemplateQuery templateQueryApp7 = template_applicant
          .getTemplateQuery(7);
        templateQueryApp7.setParameter(1, applicantID);
      }
      catch (Exception localException7) {
      }
      try {
        template_applicant.configMailAlert();

        Object[][] selectDateObj = model.getSqlModel().getSingleResult("SELECT GOAL_REVIEWER_STATUS,TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE EMP_GOAL_ASSESSMENT_ID =" + applnID);
        String assesmentDate = "";
        if ((selectDateObj != null) && (selectDateObj.length > 0)) {
          assesmentDate = String.valueOf(selectDateObj[0][1]);
        }

        alertlink = "/pas/SelfGoalAssessment_retrieveGoalDetails.action";
        linkParam = "goalId=" + goalPathId + "&empId=" + applicantID + "&asmtId=" + applnID + 
          "&confGoalId=" + goalPathId + "&listType=" + "&assesmentDate=" + assesmentDate;
        template_applicant.sendProcessManagerAlert("", module, "I", applnID, 
          level, linkParam, alertlink, "Pending", applicantID, 
          "", finalApproverID, applicantID, applicantID);

        if ((approverIds != null) && (approverIds.length > 0)) {
          template_applicant.sendApplicationMailToKeepInfo(approverIds);

          template_applicant.clearParameters();
          template_applicant.terminate();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String checkNull(String result) {
    if ((result == null) || (result.equals("")) || (result.equals("null"))) {
      return "";
    }
    return result;
  }
}