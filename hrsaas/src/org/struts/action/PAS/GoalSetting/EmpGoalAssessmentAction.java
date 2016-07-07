package org.struts.action.PAS.GoalSetting;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.EmpGoalAssessment;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.PAS.GoalSetting.EmpGoalAssessmentModel;
import org.paradyne.model.PAS.GoalSetting.EmployeeGoalSettingModel;
import org.paradyne.model.PAS.GoalSetting.ReviewerGoalAssessmentModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class EmpGoalAssessmentAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(EmpGoalAssessmentAction.class);
  EmpGoalAssessment empGoalAssessment;

  public void prepare_local()
    throws Exception
  {
    this.empGoalAssessment = new EmpGoalAssessment();
    this.empGoalAssessment.setMenuCode(1072);
  }

  public Object getModel()
  {
    return this.empGoalAssessment;
  }

  public EmpGoalAssessment getEmpGoalAssessment() {
    return this.empGoalAssessment;
  }

  public void setEmpGoalAssessment(EmpGoalAssessment empGoalAssessment) {
    this.empGoalAssessment = empGoalAssessment;
  }

  public String input() {
    System.out.println("==================input========================");
    try {
      EmpGoalAssessmentModel model = new EmpGoalAssessmentModel();
      model.initiate(this.context, this.session);
      this.empGoalAssessment.setListType("pending");
      ArrayList goalList = model
        .getPendingGoalAssmtList(this.empGoalAssessment.getUserEmpId(),this.empGoalAssessment, this.request);
      if ((goalList != null) && (goalList.size() > 0))
        this.empGoalAssessment.setDataList(goalList);
      else {
        this.empGoalAssessment.setNoData("true");
      }
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }
  public String getProcessedList() {
    System.out.println("==================getProcessedList=======================");
    this.empGoalAssessment.setListType("processed");
    EmpGoalAssessmentModel model = new EmpGoalAssessmentModel();
    model.initiate(this.context, this.session);
    model.getProcessedList(this.empGoalAssessment, this.request);
    model.terminate();
    return "success";
  }
   
  public String getReassessmentList() {
    System.out.println("=================================getReassessmentList===============================");
    this.empGoalAssessment.setListType("reassessment");
    EmpGoalAssessmentModel model = new EmpGoalAssessmentModel();
    model.initiate(this.context, this.session);
    model.getReassessmentList(this.empGoalAssessment, this.request);
    model.terminate();
    return "success";
  }

  /**
 * @return String
 * Method to retrieve goal details(JSP layout- mainlayout)
 */
public String retrieveGoalDetails() {
    System.out.println("I========================retrieveGoalDetails========================================");
    String source = this.request.getParameter("src");
    System.out.println("Source----------------------" + source);
    this.empGoalAssessment.setSource(source);
    String goalId = this.request.getParameter("goalId");
    String confgoalId = this.request.getParameter("confgoalId");
    this.empGoalAssessment.setConfgoalId(confgoalId);
    String assessLevel = this.request.getParameter("assessLevel");
    String reportingType = this.request.getParameter("reportingType");
    String goalRatingAccess = this.request.getParameter("goalRatingAccess");
    String goalCommentsAccess = this.request.getParameter("goalCommentsAccess");

    System.out.println("All parameters" + assessLevel + "reportingType" + reportingType + "goalRatingAccess" + goalRatingAccess + "goalCommentsAccess" + goalCommentsAccess);
    if ((reportingType != null) || (!reportingType.equals(""))) this.empGoalAssessment.setReportingType(reportingType);
    if ((assessLevel != null) || (!assessLevel.equals(""))) this.empGoalAssessment.setAssessLevel(assessLevel);
    if ((goalRatingAccess != null) || (!goalRatingAccess.equals(""))) this.empGoalAssessment.setGoalRatingAccess(goalRatingAccess);
    if ((goalCommentsAccess != null) || (!goalCommentsAccess.equals(""))) this.empGoalAssessment.setGoalCommentsAccess(goalRatingAccess);

    System.out.println("confgoalId : " + confgoalId);
    String empId = this.request.getParameter("empId");
    String assessmentId = this.request.getParameter("asmtId");
    String assessmentType = this.request.getParameter("assessmentType") != null ? this.request.getParameter("assessmentType") : "";
    System.out.println("assessmentType :::: --- ::: " + assessmentType);
    this.empGoalAssessment.setHiddenassessmentType(assessmentType);
    EmpGoalAssessmentModel model = new EmpGoalAssessmentModel();
    model.initiate(this.context, this.session);
    Object[][] empObj = model.getEmployeeDetails(empId, goalId);
    model.setManagersRating(assessmentId, this.empGoalAssessment.getUserEmpId(), assessmentType);

    if ((empObj != null) && (empObj.length > 0))
    {
      this.empGoalAssessment.setEmpId(empId);
      this.empGoalAssessment.setEmpToken(String.valueOf(empObj[0][0]));
      this.empGoalAssessment.setEmpName(String.valueOf(empObj[0][1]));
      this.empGoalAssessment.setEmpBrn(String.valueOf(empObj[0][2]));
      this.empGoalAssessment.setEmpDept(String.valueOf(empObj[0][3]));
      this.empGoalAssessment.setEmpDesg(String.valueOf(empObj[0][4]));
      this.empGoalAssessment.setEmpReportingTo(String.valueOf(empObj[0][5]));
      this.empGoalAssessment.setEmpDoj(String.valueOf(empObj[0][6]));
      this.empGoalAssessment.setIsGoalCategory(String.valueOf(empObj[0][7]));
      this.empGoalAssessment.setIsGoalAchievedFlag(String.valueOf(empObj[0][8]));
      this.empGoalAssessment.setIsSignOffRequired(String.valueOf(empObj[0][9]));
      this.empGoalAssessment.setRatingrangedesc(checkNull(String.valueOf(empObj[0][10])));
      this.empGoalAssessment.setGoalId(goalId);
      this.empGoalAssessment.setGoalAssessmentId(assessmentId);
      this.empGoalAssessment.setGoalName(checkNull(String.valueOf(empObj[0][11])));
      ArrayList goalList = model.getSavedRating(goalId, empId, assessmentId, this.empGoalAssessment, confgoalId);
      if ((goalList != null) && (goalList.size() > 0))
        this.empGoalAssessment.setGoalList(goalList);
      else {
        this.empGoalAssessment.setNoData("true");
      }
    }
    System.out.println("The reporting type : " + this.empGoalAssessment.getReportingType());

    Object[][] reassessmentlevelObj = model.getSqlModel()
      .getSingleResult("SELECT GOAL_APPROVER_LEVL FROM HRMS_GOAL_ASSESSMENT_APPR_DTL WHERE GOAL_APPROVER_ID=" + this.empGoalAssessment.getUserEmpId() + 
      " AND GOAL_REASSESSMENT_STATUS = 'N'AND GOAL_SIGNOFF_ACCEPTANCE = 'N' AND EMP_GOAL_ASSESSMENT_ID=" + assessmentId);
    String reassessmentlevel = "";
    if ((reassessmentlevelObj != null) && (reassessmentlevelObj.length > 0)) {
      reassessmentlevel = String.valueOf(reassessmentlevelObj[0][0]);
      this.empGoalAssessment.setReassessmentlevel(reassessmentlevel);
    }
    model.setAccess(this.empGoalAssessment);
    if (this.empGoalAssessment.getListType().equals("pending")) {
      if ((this.empGoalAssessment.getGoalRatingAccess().equals("false")) && 
        (this.empGoalAssessment.getGoalCommentsAccess().equals("false"))) {
        getNavigationPanel(4);
      }
      else if (this.empGoalAssessment.getIsRecordSaved().equals("true"))
        getNavigationPanel(2);
      else {
        getNavigationPanel(1);
      }
    }
    else if (this.empGoalAssessment.getListType().equals("reassessment"))
    {
      getNavigationPanel(1);

      this.empGoalAssessment.setEnableAll("Y");
    }
    else
    {
      String goalStatusQuery = " SELECT GOAL_ASSESSER_ID, GOAL_ASSESSMENT_STATUS, GOAL_IS_SIGNOFF FROM  HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE EMP_GOAL_ASSESSMENT_ID = " + 
        assessmentId;
      Object[][] goalStatus = model.getSqlModel().getSingleResult(goalStatusQuery);
      if (String.valueOf(empObj[0][9]).equals("Y")) {
        if ((goalStatus != null) && (goalStatus.length > 0))
          if ((String.valueOf(goalStatus[0][0]).equals(this.empGoalAssessment.getUserEmpId())) && (String.valueOf(goalStatus[0][1]).equals("2")) && (String.valueOf(goalStatus[0][2]).equals("N"))) {
            getNavigationPanel(5);
            this.empGoalAssessment.setEnableAll("N");
          } else {
            getNavigationPanel(3);
            this.empGoalAssessment.setEnableAll("N");
          }
      }
      else {
        getNavigationPanel(3);
        this.empGoalAssessment.setEnableAll("N");
      }
    }

    model.terminate();

    return "goalview";
  }
  public String getRatingList() {
    System.out.println("====================getRatingList=================================");
    EmpGoalAssessmentModel model = new EmpGoalAssessmentModel();
    model.initiate(this.context, this.session);
    logger.info("getRatingList");

    model.setManagersRating(this.empGoalAssessment.getGoalAssessmentId(), this.empGoalAssessment.getUserEmpId(), this.empGoalAssessment.getHiddenassessmentType());
    ArrayList goalList = model.getSavedRating(this.empGoalAssessment.getGoalId(), this.empGoalAssessment.getEmpId(), this.empGoalAssessment.getGoalAssessmentId(), this.empGoalAssessment, this.empGoalAssessment.getConfgoalId());
    if ((goalList != null) && (goalList.size() > 0))
      this.empGoalAssessment.setGoalList(goalList);
    else {
      this.empGoalAssessment.setNoData("true");
    }
    model.terminate();
    return "goalview";
  }
  public String saveRating() {
    System.out.println("=====================saveRating----------------------------------" + this.empGoalAssessment.getGoalId());
    EmpGoalAssessmentModel model = new EmpGoalAssessmentModel();
    model.initiate(this.context, this.session);
    boolean result = false;
    result = model.saveRating(this.empGoalAssessment, this.request);
    if (result)
    {
      addActionMessage("Record saved successfully");
    }
    else addActionMessage("Record can not be saved");

    getNavigationPanel(2);
    getRatingList();
    model.terminate();
    return "goalview";
  }
  public String finalizeAssessment() {
    System.out.println("----finalizeAssessment------------------------------------------------------------------**************" + this.empGoalAssessment.getGoalId());
    EmpGoalAssessmentModel model = new EmpGoalAssessmentModel();
    model.initiate(this.context, this.session);
    EmployeeGoalSettingModel model1 = new EmployeeGoalSettingModel();
    model1.initiate(this.context, this.session);

    boolean result = false;
    boolean flag = true;
    boolean flag1 = true;
    result = model.saveRating(this.empGoalAssessment, this.request);
    int level = Integer.parseInt(this.empGoalAssessment.getAssessLevel()) + 1;
    System.out.println("In side : finalizeAssessment : " + this.empGoalAssessment.getHiddenassessmentType());
    if (result) {
      String escalationFlowVal = "N";
      Object[][] empFlow = (Object[][])null;
      Object[][] escalData = model.getEsclationWorkFlowDtl(this.empGoalAssessment.getConfgoalId());
      if ((escalData != null) && (escalData.length > 0))
        escalationFlowVal = String.valueOf(escalData[0][0]);
      System.out.println("escalationFlowVal--------" + escalationFlowVal);
      Object[][] ReviewDetObj = (Object[][])null;
      if (escalationFlowVal.equals("Y"))
      {
        String ReviewDet = "SELECT REPORTINGDTL_CODE FROM  HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE in(SELECT HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_CODE FROM HRMS_REPTNG_STRUCTHDR_GOAL where  REPORTINGHDR_EMP_ID=" + 
          this.empGoalAssessment.getEmpId() + ") AND REPORTINGDTL_APPROVER_TYPE='R'";

        String approvarQuery = "SELECT REPORTINGDTL_CODE,REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS FROM  HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE in(SELECT HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_CODE  FROM HRMS_REPTNG_STRUCTHDR_GOAL where  REPORTINGHDR_EMP_ID=" + 
          this.empGoalAssessment.getEmpId() + ") AND REPORTINGDTL_APPROVER_TYPE='A'";
        Object[][] approvarQueryObj = model.getSqlModel().getSingleResult(approvarQuery);

        ReviewDetObj = model.getSqlModel().getSingleResult(ReviewDet);
        if ((ReviewDetObj != null) && (ReviewDetObj.length == 0)) {
          flag = false;
        }

        if ((approvarQueryObj != null) && (approvarQueryObj.length > 0)) {
          for (int i = 0; i < approvarQueryObj.length; i++) {
            if (String.valueOf(approvarQueryObj[i][1]).equals("Y"))
              flag1 = true;
            else
              flag1 = false;
          }
        }
        else {
          flag1 = false;
        }

      }

      if ((flag) && (flag1))
      {
        result = model.finalizeAssessment(this.empGoalAssessment, this.empGoalAssessment.getGoalAssessmentId(), this.empGoalAssessment.getEmpId(), level, this.empGoalAssessment.getReportingType(), this.empGoalAssessment.getConfgoalId(), this.empGoalAssessment.getUserEmpId(), this.empGoalAssessment.getHiddenassessmentType(), this.empGoalAssessment.getReassessmentlevel(), this.empGoalAssessment.getGoalId());
        if (result) {
        //Updated by Prajakta B	
          if (this.empGoalAssessment.getDivScoreDet().equals("false")) {
        //Ends updated by Prajakta B
            addActionMessage("Record finalized successfully");

            empFlow = model1.generateEmpFlow(this.empGoalAssessment.getEmpId(), level, this.empGoalAssessment.getReportingType());

            if ((this.empGoalAssessment.getReportingType().equals("goal")) && 
              (empFlow != null) && (empFlow.length > 0)) {
              try {
            	  while (String.valueOf(empFlow[0][3]).equals("N")){
                  //level++;
                  empFlow = model1.generateEmpFlow(this.empGoalAssessment.getEmpId(), level, 
                    this.empGoalAssessment.getReportingType());

                  if ((empFlow == null) || (empFlow.length <= 0)) break; 
                };
              }
              catch (Exception localException1)
              {
              }

            }

            String applicant = ""; String oldApprover = ""; String newApprover = "";
            try {
              applicant = this.empGoalAssessment.getEmpId();
              oldApprover = this.empGoalAssessment.getUserEmpId();
              if ((empFlow != null) && (empFlow.length > 0)) {
                newApprover = String.valueOf(checkNull(String.valueOf(empFlow[0][0])));
                if (escalationFlowVal.equals("Y"))
                {
                  String reviewerId = checkNull(String.valueOf(empFlow[0][6]));

                  if (reviewerId.equals("R"))
                  {
                    newApprover = "";
                  }
                }
              }
            } catch (Exception e) {
              logger.error(e);
              e.printStackTrace();
            }
            System.out.println("oldApprover : " + oldApprover);
            System.out.println("newApprover : " + newApprover);
            System.out.println("Goal ID" + this.request.getParameter("confgoalId"));
            System.out.println("Goal ID" + this.request.getParameter("goalId"));
            String assesslevel = "SELECT GOAL_ASSESSER_LEVEL FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE GOAL_ID=" + this.empGoalAssessment.getGoalId() + "AND GOAL_IS_SIGNOFF='N'" + "AND EMP_GOAL_ASSESSMENT_ID =" + this.empGoalAssessment.getGoalAssessmentId();
            Object[][] assessLevel = model1.getSqlModel().getSingleResult(assesslevel);
            String assesLevel = "";
            if ((assessLevel != null) && (assessLevel.length > 0)) {
              assesLevel = String.valueOf(assessLevel[0][0]);
            }
            System.out.println("GoalID" + this.empGoalAssessment.getGoalId());
            if (!this.empGoalAssessment.getListType().equals("reassessment"))
              sendMail(this.empGoalAssessment.getEmpId(), oldApprover, this.empGoalAssessment.getGoalAssessmentId(), 
                newApprover, this.empGoalAssessment.getConfgoalId(), this.empGoalAssessment.getGoalId(), 
                this.empGoalAssessment.getReportingType(), assesLevel);
            else
              sendAlertsApprovarApplicant(this.empGoalAssessment.getAssessLevel());
          }
        }
        else {
          System.out.println("Hello");
          addActionMessage("Record can not be finalized");
        }
      } else {
        getNavigationPanel(2);
        addActionMessage("Please Define Reviewer/ And Provide at least View access to the Next Approvar's if defined in Goal Reporting Structure");
      }
      getRatingList();
      String goalStatusQuery = " SELECT GOAL_ASSESSER_ID, GOAL_ASSESSMENT_STATUS, GOAL_IS_SIGNOFF FROM  HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE EMP_GOAL_ASSESSMENT_ID = " + 
        this.empGoalAssessment.getGoalAssessmentId();
      Object[][] goalStatus = model.getSqlModel().getSingleResult(goalStatusQuery);
      System.out.println("Hello" + this.empGoalAssessment.getIsSignOffRequired());
      if (this.empGoalAssessment.getIsSignOffRequired().equals("Y")) {
        System.out.println("Hello Inside button pannel");
        if ((goalStatus != null) && (goalStatus.length > 0)) {
          if ((String.valueOf(goalStatus[0][0]).equals(this.empGoalAssessment.getUserEmpId())) && (String.valueOf(goalStatus[0][1]).equals("2")) && (String.valueOf(goalStatus[0][2]).equals("N"))) {
            System.out.println("Inside goal value");
            getNavigationPanel(5);
            this.empGoalAssessment.setEnableAll("N");
          } else if (!flag) {
            System.out.println("inside flag");
            getNavigationPanel(2);
            this.empGoalAssessment.setEnableAll("N");
          }
          else if (this.empGoalAssessment.getDivScoreDet().equals("true")) {
            System.out.println("Inside div");
            model.setAccess(this.empGoalAssessment);
            getNavigationPanel(2);

            this.empGoalAssessment.setEnableAll("Y");
          }
          else
          {
            System.out.println("Inside goal det");
            getNavigationPanel(3);
            this.empGoalAssessment.setEnableAll("N");
          }
        }
      }
      else if (this.empGoalAssessment.getIsSignOffRequired().equals("N")) {
        if (this.empGoalAssessment.getDivScoreDet().equals("true")) {
          model.setAccess(this.empGoalAssessment);
          System.out.println("Inside div   222222");
          getNavigationPanel(2);
          this.empGoalAssessment.setEnableAll("Y");
        }
        else {
          getNavigationPanel(3);
        }
      } else {
        getNavigationPanel(3);
      }
    }
    else {
      addActionMessage("Error while saving the ratings");
    }
    if (this.empGoalAssessment.getDivScoreDet().equals("false")) {
      this.empGoalAssessment.setEnableAll("N");
    }
    model.terminate();
    return "goalview";
  }

  public String updateGoal() {
    System.out.println("----updateGoal------------------------------------------------------------------**************" + this.empGoalAssessment.getGoalId());
    EmpGoalAssessmentModel model = new EmpGoalAssessmentModel();
    model.initiate(this.context, this.session);
    EmployeeGoalSettingModel model1 = new EmployeeGoalSettingModel();
    model1.initiate(this.context, this.session);
    int level = Integer.parseInt(this.empGoalAssessment.getAssessLevel()) + 1;
    System.out.println("@@@@@@@ Level In EmpGoalAssessment Update Goal-------->"+level);
    this.empGoalAssessment.setDivScoreDet("false");
    boolean result = false;
    Object[][] empFlow = (Object[][])null;

    String escalationFlowVal = "N";
    Object[][] escalData = model.getEsclationWorkFlowDtl(this.empGoalAssessment.getConfgoalId());
    if ((escalData != null) && (escalData.length > 0))
      escalationFlowVal = String.valueOf(escalData[0][0]);
    System.out.println("escalationFlowVal--------" + escalationFlowVal);
    String applicant = ""; String oldApprover = ""; String newApprover = "";
    empFlow = model1.generateEmpFlow(this.empGoalAssessment.getEmpId(), level, this.empGoalAssessment.getReportingType());

    if ((this.empGoalAssessment.getReportingType().equals("goal")) && 
      (empFlow != null) && (empFlow.length > 0)) {
      try {
    	  while (String.valueOf(empFlow[0][3]).equals("N")){
          //level++;
          empFlow = model1.generateEmpFlow(this.empGoalAssessment.getEmpId(), level, 
            this.empGoalAssessment.getReportingType());

          if ((empFlow == null) || (empFlow.length <= 0)) break; 
        };
      }
      catch (Exception localException1)
      {
      }

    }

    applicant = this.empGoalAssessment.getEmpId();
    oldApprover = this.empGoalAssessment.getUserEmpId();
    if ((empFlow != null) && (empFlow.length > 0)) {
      newApprover = String.valueOf(checkNull(String.valueOf(empFlow[0][0])));
    }

    System.out.println("newApprover $$$$$$$$$$$$$ :::::::::: " + newApprover);
    if (newApprover.equals(""))
    {
      result = model.lastfinalizeAssessment(this.empGoalAssessment, Integer.parseInt(this.empGoalAssessment.getAssessLevel()) + 1);
    }
    else {
      Object[][] updateObj = new Object[1][3];
      updateObj[0][0] = empFlow[0][2];
      updateObj[0][1] = empFlow[0][0];
      updateObj[0][2] = this.empGoalAssessment.getGoalAssessmentId();
      String updateQuery = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_ASSESSER_LEVEL=?,GOAL_ASSESSER_ID=?   WHERE EMP_GOAL_ASSESSMENT_ID =?";

      result = model.getSqlModel().singleExecute(updateQuery, updateObj);
    }

    try
    {
      if (escalationFlowVal.equals("Y"))
      {
        String reviewerId = checkNull(String.valueOf(empFlow[0][6]));

        if (reviewerId.equals("R"))
        {
          newApprover = "";
        }
      }
    }
    catch (Exception e) {
      logger.error(e);
      e.printStackTrace();
    }
    System.out.println("oldApprover : " + oldApprover);
    System.out.println("newApprover : " + newApprover);
    System.out.println("Goal ID" + this.request.getParameter("confgoalId"));
    System.out.println("Goal ID" + this.request.getParameter("goalId"));
    String assesslevel = "SELECT GOAL_ASSESSER_LEVEL FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE GOAL_ID=" + this.empGoalAssessment.getGoalId() + "AND GOAL_IS_SIGNOFF='N'" + "AND EMP_GOAL_ASSESSMENT_ID =" + this.empGoalAssessment.getGoalAssessmentId();
    Object[][] assessLevel = model1.getSqlModel().getSingleResult(assesslevel);
    String assesLevel = "";
    if ((assessLevel != null) && (assessLevel.length > 0)) {
      assesLevel = String.valueOf(assessLevel[0][0]);
    }
    System.out.println("GoalID" + this.empGoalAssessment.getGoalId());
    if (!this.empGoalAssessment.getListType().equals("reassessment"))
      sendMail(this.empGoalAssessment.getEmpId(), oldApprover, this.empGoalAssessment.getGoalAssessmentId(), 
        newApprover, this.empGoalAssessment.getConfgoalId(), this.empGoalAssessment.getGoalId(), 
        this.empGoalAssessment.getReportingType(), assesLevel);
    else {
      sendAlertsApprovarApplicant(this.empGoalAssessment.getAssessLevel());
    }

    addActionMessage("Record finalized successfully");

    getRatingList();
    String goalStatusQuery = " SELECT GOAL_ASSESSER_ID, GOAL_ASSESSMENT_STATUS, GOAL_IS_SIGNOFF FROM  HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE EMP_GOAL_ASSESSMENT_ID = " + 
      this.empGoalAssessment.getGoalAssessmentId();
    Object[][] goalStatus = model.getSqlModel().getSingleResult(goalStatusQuery);
    if (this.empGoalAssessment.getIsSignOffRequired().equals("Y")) {
      if ((goalStatus != null) && (goalStatus.length > 0)) {
        if ((String.valueOf(goalStatus[0][0]).equals(this.empGoalAssessment.getUserEmpId())) && (String.valueOf(goalStatus[0][1]).equals("2")) && (String.valueOf(goalStatus[0][2]).equals("N"))) {
          getNavigationPanel(5);
          this.empGoalAssessment.setEnableAll("N");
        } else {
          getNavigationPanel(3);
          this.empGoalAssessment.setEnableAll("N");
        }
      }
    }
    else {
      getNavigationPanel(3);
    }

    this.empGoalAssessment.setEnableAll("N");
    model.terminate();
    return "goalview";
  }

  public void sendMail(String applicantID, String oldApproverId, String applnID, String newApprover, String congGoalId, String goalId, String reportingType, String assesLevel)
  {
    try {
      System.out.println("-----------sendmail----------------------------------------");

      EmpGoalAssessmentModel model = new EmpGoalAssessmentModel();
      model.initiate(this.context, this.session);

      String alertlink = "";
      String linkParam = "";
      String module = "Goal Setting";
      String level = "1";
      try {
        MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
        processAlerts.initiate(this.context, this.session);
        processAlerts.removeProcessAlert(String.valueOf(applnID), 
          module);
      } catch (Exception e) {
        e.printStackTrace();
      }

      System.out.println("Inside sendMail :::: applicantID " + applicantID);
      System.out.println("Inside sendMail :::: oldApproverId  " + oldApproverId);
      System.out.println("Inside sendMail :::: newApprover  " + newApprover);
      System.out.println("Inside sendMail :::: applnID  " + applnID);
      if (!newApprover.equals(""))
      {
        EmailTemplateBody template = new EmailTemplateBody();
        template.initiate(this.context, this.session);
        template
          .setEmailTemplate("Mail to employee regarding employee goal  assessment application submission");
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

          templateQuery3.setParameter(1, applicantID);
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
          templateQuery6.setParameter(1, newApprover);
        }
        catch (Exception localException6) {
        }
        try {
          EmailTemplateQuery templateQuery7 = template
            .getTemplateQuery(7);
          templateQuery7.setParameter(1, congGoalId);
        }
        catch (Exception localException7) {
        }
        try {
          template.configMailAlert();
          try {
            alertlink = "/pas/EmpGoalAssessment_retrieveGoalDetails.action";
            System.out.println("alertlink     alertlink      " + alertlink);

            linkParam = "goalId=" + goalId + "&empId=" + 
              applicantID + "&asmtId=" + applnID + "&confgoalId=" + congGoalId + "&reportingType=" + reportingType + "&assessLevel=" + assesLevel + 
              "&listType=processed&goalRatingAccess=false&goalCommentsAccess=false";

            System.out.println("linkParam     linkParam      " + linkParam);

            template.sendProcessManagerAlert(oldApproverId, module, "I", 
              applnID, level, linkParam, alertlink, "Pending", 
              applicantID, "", "", "", oldApproverId);

            alertlink = "/pas/SelfGoalAssessment_retrieveGoalDetails.action";
            linkParam = "goalId=" + goalId + "&empId=" + 
              applicantID + "&asmtId=" + applnID + "&confGoalId=" + congGoalId + "&reportingType=" + reportingType + "&assessLevel=" + assesLevel + 
              "&listType=processed&goalRatingAccess=false&goalCommentsAccess=false";

            template.sendProcessManagerAlert("", module, "I", 
              applnID, level, linkParam, alertlink, "Pending", 
              applicantID, "", "", applicantID, oldApproverId);
          }
          catch (Exception localException8) {
          }
          template.sendApplicationMail();
          template.clearParameters();
          template.terminate();
        }
        catch (Exception localException9) {
        }
        EmailTemplateBody template_applicant = new EmailTemplateBody();
        template_applicant.initiate(this.context, this.session);

        template_applicant
          .setEmailTemplate("EMPLOYEE GOAL ASSESSMENT APPL-APPROVER1 TO APPROVER2");
        template_applicant.getTemplateQueries();
        try
        {
          EmailTemplateQuery templateQueryApp1 = template_applicant
            .getTemplateQuery(1);
          templateQueryApp1.setParameter(1, oldApproverId);
        }
        catch (Exception localException10) {
        }
        try {
          EmailTemplateQuery templateQueryApp2 = template_applicant
            .getTemplateQuery(2);
          templateQueryApp2.setParameter(1, newApprover);
        }
        catch (Exception localException11)
        {
        }
        try {
          EmailTemplateQuery templateQueryApp3 = template_applicant
            .getTemplateQuery(3);
          templateQueryApp3.setParameter(1, applicantID);
        }
        catch (Exception localException12) {
        }
        try {
          EmailTemplateQuery templateQueryApp4 = template_applicant
            .getTemplateQuery(4);
          templateQueryApp4.setParameter(1, oldApproverId);
        }
        catch (Exception localException13) {
        }
        try {
          EmailTemplateQuery templateQueryApp5 = template_applicant
            .getTemplateQuery(5);
          templateQueryApp5.setParameter(1, applnID);
        }
        catch (Exception localException14) {
        }
        try {
          EmailTemplateQuery templateQueryApp6 = template_applicant
            .getTemplateQuery(6);
          templateQueryApp6.setParameter(1, newApprover);
        }
        catch (Exception localException15) {
        }
        try {
          EmailTemplateQuery templateQueryApp7 = template_applicant
            .getTemplateQuery(7);
          templateQueryApp7.setParameter(1, congGoalId);
        }
        catch (Exception localException16)
        {
        }
        try {
          template_applicant.configMailAlert();

          alertlink = "/pas/EmpGoalAssessment_retrieveGoalDetails.action";
          System.out.println("alertlink     alertlink      " + alertlink);
          linkParam = "goalId=" + goalId + "&empId=" + 
            applicantID + "&asmtId=" + applnID + "&confgoalId=" + congGoalId + "&reportingType=" + reportingType + "&assessLevel=" + assesLevel + 
            "&listType=pending&goalRatingAccess=false&goalCommentsAccess=false";
          System.out.println("linkParam     linkParam      " + linkParam);
          if (!this.empGoalAssessment.getListType().equals("reassessment")) {
            template_applicant.sendProcessManagerAlert(newApprover, module, 
              "A", applnID, "2", linkParam, alertlink, 
              "Pending", applicantID, "", "", "", 
              oldApproverId);
          } else {
            alertlink = "/pas/SelfGoalAssessment_retrieveGoalDetails.action";
            linkParam = "goalId=" + goalId + "&empId=" + 
              applicantID + "&asmtId=" + applnID + "&confGoalId=" + congGoalId + "&reportingType=" + reportingType + "&assessLevel=" + assesLevel + 
              "&listType=processed&goalRatingAccess=false&goalCommentsAccess=false";
            template_applicant.sendProcessManagerAlert("", module, "I", 
              applnID, level, linkParam, alertlink, "Pending", 
              applicantID, "", "", applicantID, oldApproverId);
          }
          template_applicant.sendApplicationMail();
          template_applicant.clearParameters();
          template_applicant.terminate();
        }
        catch (Exception localException17)
        {
        }
      }
      else {
        EmailTemplateBody template = new EmailTemplateBody();
        template.initiate(this.context, this.session);
        template
          .setEmailTemplate("EMPLOYEE GOAL ASSESSMENT APPL-FINAL APPROVER TO APPLICANT");
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

          templateQuery3.setParameter(1, applicantID);
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
        }
        catch (Exception localException22) {
        }
        try {
          EmailTemplateQuery templateQuery6 = template
            .getTemplateQuery(6);
          templateQuery6.setParameter(1, oldApproverId);
        }
        catch (Exception localException23) {
        }
        try {
          EmailTemplateQuery templateQuery7 = template
            .getTemplateQuery(7);
          templateQuery7.setParameter(1, congGoalId);
        }
        catch (Exception localException24) {
        }
        try {
          template.configMailAlert();

          alertlink = "/pas/EmpGoalAssessment_retrieveGoalDetails.action";
          System.out.println("alertlink     alertlink      " + alertlink);

          linkParam = "goalId=" + goalId + "&empId=" + 
            applicantID + "&asmtId=" + applnID + "&confgoalId=" + congGoalId + "&reportingType=" + reportingType + "&assessLevel=" + assesLevel + 
            "&listType=processed&goalRatingAccess=false&goalCommentsAccess=false";
          System.out.println("linkParam     linkParam      " + linkParam);
          template.sendProcessManagerAlert(oldApproverId, module, "I", 
            applnID, level, linkParam, alertlink, "Approved", 
            applicantID, "", "", "", oldApproverId);

          alertlink = "/pas/SelfGoalAssessment_retrieveGoalDetails.action";
          linkParam = "goalId=" + goalId + "&empId=" + 
            applicantID + "&asmtId=" + applnID + "&confGoalId=" + congGoalId + "&reportingType=" + reportingType + "&assessLevel=" + assesLevel + 
            "&listType=processed&goalRatingAccess=false&goalCommentsAccess=false";

          template.sendProcessManagerAlert("", module, "I", 
            applnID, level, linkParam, alertlink, "Pending", 
            applicantID, "", "", applicantID, oldApproverId);
          template.sendApplicationMail();
          template.clearParameters();
          template.terminate();
        }
        catch (Exception localException25)
        {
        }
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public String signOff() {
    System.out.println("=======================================signOff======================");
    boolean result = false;
    boolean reviewFlag = true;
    try {
      EmpGoalAssessmentModel model = new EmpGoalAssessmentModel();
      model.initiate(this.context, this.session);

      String escalationFlowVal = "N";
      Object[][] escalData = model.getEsclationWorkFlowDtl(this.empGoalAssessment.getConfgoalId());
      if ((escalData != null) && (escalData.length > 0)) {
        escalationFlowVal = String.valueOf(escalData[0][0]);
      }
      if (escalationFlowVal.equals("Y"))
      {
        Object[][] ReviewDet = model.getReviewrDet(this.empGoalAssessment.getEmpId());
        if ((ReviewDet == null) && (ReviewDet.length == 0)) {
          reviewFlag = false;
        }
      }
      if (reviewFlag) {
        result = model.signOffGoal(this.empGoalAssessment);
        if (result) {
          addActionMessage("Record sign off successfully");
          sendProcessManagerAlertForApplSubmission();
        }
        else {
          addActionMessage("Could not sign off");
        }
      } else {
        addActionMessage("Unable to Approve Application. Please Define Reviewer in Goal Reporting Structure");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.empGoalAssessment.getSource().equals("mymessages"))
      return "mymessages";
    if (this.empGoalAssessment.getSource().equals("myservices")) {
      return "serviceJsp";
    }

    return input();
  }

  public void sendProcessManagerAlertForApplSubmission() {
    try {
      ReviewerGoalAssessmentModel model = new ReviewerGoalAssessmentModel();
      model.initiate(this.context, this.session);
      String alertlink = "";
      String linkParam = "";
      String assessLevel = "";
      String applicantID = this.empGoalAssessment.getEmpId();
      String approvarID = this.empGoalAssessment.getUserEmpId();
      String applnID = this.empGoalAssessment.getGoalAssessmentId();
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String message = "";
      try
      {
        MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
        processAlerts.initiate(this.context, this.session);
        processAlerts.removeProcessAlert(String.valueOf(applnID), 
          "Goal Setting");
      } catch (Exception e) {
        e.printStackTrace();
      }
      Object[][] assessLevelObj = model.getSqlModel().getSingleResult("SELECT HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSER_LEVEL FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE  HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID =" + applnID);
      if ((assessLevelObj != null) && (assessLevelObj.length > 0)) {
        assessLevel = String.valueOf(assessLevelObj[0][0]);
      }
      Object[][] reprtingType = model.getSqlModel().getSingleResult("SELECT GOAL_REPORTING FROM HRMS_GOAL_CONFIG WHERE GOAL_ID=" + this.empGoalAssessment.getConfgoalId());
      String reportingValue = String.valueOf(reprtingType[0][0]);

      message = "Self Goal Assessment aplication " + this.empGoalAssessment.getGoalName() + " of " + this.empGoalAssessment.getEmpName() + " Sign Off Successfully ";
      alertlink = "/pas/EmpGoalAssessment_retrieveGoalDetails.action";
      linkParam = "goalId=" + this.empGoalAssessment.getGoalId() + "&empId=" + this.empGoalAssessment.getEmpId() + 
        "&asmtId=" + this.empGoalAssessment.getGoalAssessmentId() + "&confgoalId=" + this.empGoalAssessment.getConfgoalId() + 
        "&reportingType=" + reportingValue + "&assessLevel=" + assessLevel + "&listType=processed&goalRatingAccess=false&goalCommentsAccess=false";

      template.sendProcessManagerAlertWithdraw(approvarID, "Goal Setting", "I", 
        applnID, "1", linkParam, alertlink, message, 
        "Processed", "", approvarID, "", approvarID);

      Object[][] selectDateObj = model.getSqlModel().getSingleResult("SELECT GOAL_REVIEWER_STATUS,TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE EMP_GOAL_ASSESSMENT_ID =" + applnID);
      String assesmentDate = "";
      if ((selectDateObj != null) && (selectDateObj.length > 0)) {
        assesmentDate = String.valueOf(selectDateObj[0][1]);
      }
      System.out.println("Date" + assesmentDate);
      alertlink = "/pas/SelfGoalAssessment_retrieveGoalDetails.action";
      linkParam = "goalId=" + this.empGoalAssessment.getGoalId() + "&empId=" + this.empGoalAssessment.getEmpId() + 
        "&asmtId=" + this.empGoalAssessment.getGoalAssessmentId() + "&confGoalId=" + this.empGoalAssessment.getConfgoalId() + 
        "&reportingType=" + reportingValue + "&assessLevel=" + assessLevel + 
        "&assesmentDate=" + assesmentDate + 
        "&listType=processed&goalRatingAccess=false&goalCommentsAccess=false";

      template.sendProcessManagerAlertWithdraw(applicantID, "Goal Setting", "A", 
        applnID, "1", linkParam, alertlink, message, 
        "Processed", applicantID, approvarID, "", "");

      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String checkNull(String result) {
    if ((result == null) || (result.equals("")) || (result.equals("null"))) {
      return "";
    }
    return result;
  }

  public void sendAlertsApprovarApplicant(String assesLevel)
  {
    try
    {
      try {
        MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
        processAlerts.initiate(this.context, this.session);
        processAlerts.removeProcessAlert(String.valueOf(this.empGoalAssessment.getGoalAssessmentId()), 
          "Goal Setting");
      } catch (Exception e) {
        e.printStackTrace();
      }

      String alertlink = "/pas/EmpGoalAssessment_retrieveGoalDetails.action";
      System.out.println("alertlink     alertlink      " + alertlink);

      String linkParam = "goalId=" + this.empGoalAssessment.getGoalId() + "&empId=" + 
        this.empGoalAssessment.getEmpId() + "&asmtId=" + this.empGoalAssessment.getGoalAssessmentId() + "&confgoalId=" + this.empGoalAssessment.getConfgoalId() + "&reportingType=" + 
        this.empGoalAssessment.getReportingType() + "&assessLevel=" + assesLevel + 
        "&listType=processed&goalRatingAccess=false&goalCommentsAccess=false";

      Properties alertProp = new Properties();

      FileInputStream alertFin = new FileInputStream(getText("data_path") + "\\Alerts\\alertLinks.properties");
      alertProp.load(alertFin);
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String ApprovarName = "";
      EmpGoalAssessmentModel model = new EmpGoalAssessmentModel();
      model.initiate(this.context, this.session);
      Object[][] ApprovarNameObj = model.getSqlModel().getSingleResult("SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID = " + this.empGoalAssessment.getUserEmpId());
      if ((ApprovarNameObj != null) && (ApprovarNameObj.length > 0)) {
        ApprovarName = String.valueOf(ApprovarNameObj[0][0]);
      }
      model.terminate();
      String message = alertProp.getProperty("applicationAlertMessage");
      message = message.replace("<#MODULE#>", "Self Goal assessment ");
      message = message.replace("<#EMPLOYEE_NAME#>", this.empGoalAssessment.getEmpName());
      message = message.replace("<#STATUS#>", "Approved");
      message = message.replace("<#APPROVER_NAME#>", ApprovarName);

      template.sendProcessManagerAlertWithdraw("", "Goal Setting", "I", 
        this.empGoalAssessment.getGoalAssessmentId(), "1", linkParam, alertlink, message, 
        "Processed", this.empGoalAssessment.getEmpId(), this.empGoalAssessment.getUserEmpId(), "", this.empGoalAssessment.getUserEmpId());

      String applicantAlertlink = "/pas/SelfGoalAssessment_retrieveGoalDetails.action";
      System.out.println("alertlink     alertlink      " + alertlink);

      String applicantlinkParam = "goalId=" + this.empGoalAssessment.getGoalId() + "&empId=" + 
        this.empGoalAssessment.getEmpId() + "&asmtId=" + this.empGoalAssessment.getGoalAssessmentId() + "&confGoalId=" + this.empGoalAssessment.getConfgoalId() + "&reportingType=" + 
        this.empGoalAssessment.getReportingType() + "&assessLevel=" + assesLevel + 
        "&listType=processed&goalRatingAccess=false&goalCommentsAccess=false";

      String queryDate = "SELECT GOAL_REVIEWER_STATUS,TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE EMP_GOAL_ASSESSMENT_ID =" + this.empGoalAssessment.getGoalAssessmentId();
      Object[][] selectDateObj = model.getSqlModel().getSingleResult(queryDate);
      String assesmentDate = "";
      if ((selectDateObj != null) && (selectDateObj.length > 0)) {
        assesmentDate = String.valueOf(selectDateObj[0][1]);
      }

      String reviewAlertlink = "/pas/ReviewerGoalAssessment_retrieveGoalDetails.action";
      String reviewlinkParam = "goalId=" + this.empGoalAssessment.getGoalId() + "&empId=" + this.empGoalAssessment.getEmpId() + "&asmtId=" + this.empGoalAssessment.getGoalAssessmentId() + "&confGoalId=" + this.empGoalAssessment.getConfgoalId() + "&listType=pending" + "&assesmentDate=" + assesmentDate;
      System.out.println("alertlink     alertlink      " + alertlink);

      Object[][] pendingReAssessmentObj = model.getSqlModel().getSingleResult("SELECT HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_ID, HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_ID, HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_LEVL, HRMS_GOAL_CONFIG.GOAL_REPORTING  FROM HRMS_GOAL_ASSESSMENT_APPR_DTL  INNER JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_ID)  WHERE EMP_GOAL_ASSESSMENT_ID = " + 
        this.empGoalAssessment.getGoalAssessmentId() + 
        " AND GOAL_SIGNOFF_ACCEPTANCE = 'N' AND GOAL_REASSESSMENT_STATUS = 'N'");
      Object[][] reviewerIdObj = model.getSqlModel().getSingleResult("SELECT GOAL_REVIEWER_ID FROM HRMS_GOAL_EMP_ASSESSMENT_HDR where EMP_GOAL_ASSESSMENT_ID =" + this.empGoalAssessment.getGoalAssessmentId());

      if ((pendingReAssessmentObj != null) && (reviewerIdObj != null) && (pendingReAssessmentObj.length > 0) && (reviewerIdObj.length > 0)) {
        Object[][] reviewerNameObj = model.getSqlModel().getSingleResult("SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID = " + reviewerIdObj[0][0]);
        String reviewerName = "";
        if ((reviewerNameObj != null) && (reviewerNameObj.length > 0)) {
          reviewerName = String.valueOf(reviewerNameObj[0][0]);
        }
        message = alertProp.getProperty("approverReviewedForwardedAlertMessgae");
        message = message.replace("<#MODULE#>", "Self Goal assessment ");
        message = message.replace("<#EMPLOYEE#>", this.empGoalAssessment.getEmpName());
        message = message.replace("<#STATUS#>", "reviewed");
        message = message.replace("<#REVIEWER_NAME#>", reviewerName);
        String pendingReaasessApproverLink = "/pas/EmpGoalAssessment_retrieveGoalDetails.action";
        String pendingReaasessApproverLinkParam = "goalId=" + this.empGoalAssessment.getGoalId() + "&empId=" + 
          this.empGoalAssessment.getEmpId() + "&asmtId=" + this.empGoalAssessment.getGoalAssessmentId() + "&confgoalId=" + String.valueOf(pendingReAssessmentObj[0][1]) + "&reportingType=" + String.valueOf(pendingReAssessmentObj[0][3]) + "&assessLevel=" + Integer.parseInt(String.valueOf(pendingReAssessmentObj[0][2])) + 
          "&listType=reassessment&assessmentType=reassessment&goalRatingAccess=false&goalCommentsAccess=false";

        template.sendProcessManagerAlertWithdraw(String.valueOf(pendingReAssessmentObj[0][0]), "Goal Setting", "A", 
          this.empGoalAssessment.getGoalAssessmentId(), "1", pendingReaasessApproverLinkParam, pendingReaasessApproverLink, message, 
          "Pending Reassessment", this.empGoalAssessment.getEmpId(), String.valueOf(reviewerIdObj[0][0]), "", "");
      }

      message = alertProp.getProperty("applicationAlertMessage");
      message = message.replace("<#MODULE#>", "Self Goal assessment ");
      message = message.replace("<#EMPLOYEE_NAME#>", this.empGoalAssessment.getEmpName());
      message = message.replace("<#STATUS#>", "Approved");
      message = message.replace("<#APPROVER_NAME#>", ApprovarName);
      if ((pendingReAssessmentObj != null) && (reviewerIdObj != null) && (pendingReAssessmentObj.length > 0))
        template.sendProcessManagerAlertWithdraw(this.empGoalAssessment.getEmpId(), "Goal Setting", "I", 
          this.empGoalAssessment.getGoalAssessmentId(), "1", applicantlinkParam, applicantAlertlink, message, 
          "Processed", this.empGoalAssessment.getEmpId(), this.empGoalAssessment.getUserEmpId(), "", "");
      else {
        template.sendProcessManagerAlertWithdraw(this.empGoalAssessment.getEmpId(), "Goal Setting", "A", 
          this.empGoalAssessment.getGoalAssessmentId(), "1", applicantlinkParam, applicantAlertlink, message, 
          "Processed", this.empGoalAssessment.getEmpId(), this.empGoalAssessment.getUserEmpId(), "", "");
      }

      template.sendProcessManagerAlertWithdraw("", "Goal Setting", "I", 
        this.empGoalAssessment.getGoalAssessmentId(), "1", reviewlinkParam, reviewAlertlink, message, 
        "Processed", this.empGoalAssessment.getEmpId(), this.empGoalAssessment.getUserEmpId(), "", String.valueOf(reviewerIdObj[0][0]));
      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
 
}