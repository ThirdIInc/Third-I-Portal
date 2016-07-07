package org.paradyne.model.PAS.GoalSetting;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.SelfGoalAssessment;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;

/**
 * @Modified By prajakta.bhandare
 * @date 13 May 2013
 */
public class SelfGoalAssessmentModel extends ModelBase
{
  static Logger logger = Logger.getLogger(SelfGoalAssessmentModel.class);
  HashMap<String, Object[][]> ratingData = null;

  public ArrayList<Object> getPendingGoalAssmtList(String userEmpId) { ArrayList goalList = null;
    try
    {
      String query = "   SELECT HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID,GOAL_NAME,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID, \t(HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), \t TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'),NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  '),EMP_GOAL_ASSESSMENT_ID,HRMS_GOAL_CONFIG.GOAL_ID AS CONFGOALID, NVL(GOAL_REPORTING,'reporting'),NVL(GOAL_ASSESSER_LEVEL,1)\t FROM HRMS_GOAL_EMP_ASSESSMENT_HDR  \t INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID \t INNER JOIN HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) \t LEFT JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID  \t LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  \t LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  \t LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) WHERE GOAL_ASSESSMENT_STATUS = 1 AND GOAL_ASSESSMENT_DATE <=SYSDATE AND HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID =" + 
        userEmpId + " " + 
        "UNION ALL " + 
        "SELECT * FROM " + 
        "(SELECT HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID AS GOAL_ID,GOAL_NAME,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID AS EMPID, \t(HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) AS NAME, \t TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') ASSESSMENT_DATE,NVL(HRMS_DEPT.DEPT_NAME,' ') AS DEPARTMENT,NVL(HRMS_RANK.RANK_NAME,'  ') AS RANK,EMP_GOAL_ASSESSMENT_ID,HRMS_GOAL_CONFIG.GOAL_ID AS CONFGOALID, NVL(GOAL_REPORTING,'reporting') AS GOAL_TYPE,NVL(GOAL_ASSESSER_LEVEL,1) AS ASSESSOR_ID\t  FROM HRMS_GOAL_EMP_ASSESSMENT_HDR  \t INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID \t INNER JOIN HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) \t LEFT JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID  \t LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  \t LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  \t LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) " + 
        "WHERE GOAL_ASSESSMENT_STATUS = 1 " + 
        "AND GOAL_ASSESSMENT_DATE >SYSDATE " + 
        "AND HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID =" + userEmpId + " " + 
        "ORDER BY GOAL_ASSESSMENT_DATE) WHERE ROWNUM=1 ";

      System.out.println("userEmpId----------------------" + userEmpId);

      Object[][] listData = getSqlModel().getSingleResult(query);
      if ((listData != null) && (listData.length != 0)) {
        goalList = new ArrayList();
        for (int i = 0; i < listData.length; i++) {
          SelfGoalAssessment bean1 = new SelfGoalAssessment();
          bean1.setGoalId(String.valueOf(listData[i][0]));
          bean1.setGoalName(String.valueOf(listData[i][1]));
          bean1.setEmpId(String.valueOf(listData[i][2]));
          bean1.setEmpName(String.valueOf(listData[i][3]));
          bean1.setScheduledAssessmentDateList(String.valueOf(listData[i][4]));
          bean1.setEmpDept(String.valueOf(listData[i][5]));
          bean1.setEmpDesg(String.valueOf(listData[i][6]));
          bean1.setGoalAssessmentId(String.valueOf(listData[i][7]));
          bean1.setConfgoalId(String.valueOf(listData[i][8]));
          bean1.setReportingType(String.valueOf(listData[i][9]));
          bean1.setAssessLevel(String.valueOf(listData[i][10]));
          goalList.add(bean1);
        }
      }
    } catch (Exception e) {
      logger.error("Exception in getPendingGoalAssmtList " + e);
    }
    return goalList;
  }

  public Object[][] getEsclationWorkFlowDtl(String GoalId)
  {
    Object[][] data = (Object[][])null;
    try {
      String query = "SELECT NVL(GOAL_ESCALATION_WORKFLOW,'N'), NVL(GOAL_ESCALATION_EMAIL,'') FROM HRMS_GOAL_CONFIG WHERE GOAL_ID = " + 
        GoalId;
      data = getSqlModel().getSingleResult(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return data;
  }

  public void getApproverData(SelfGoalAssessment bean, String assessmentId)
  {
    try
    {
      String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME NAME,HRMS_GOAL_ASSESSMENT_APPR_DTL.EMP_GOAL_ASSESSMENT_ID,HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_ID, HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_LEVL,GOAL_EMP_ID,GOAL_SIGNOFF_ACCEPTANCE,NVL(GOAL_EMP_COMMENTS,'') AS EMPLOYEE_COMMENT FROM  HRMS_GOAL_ASSESSMENT_APPR_DTL INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_ID WHERE HRMS_GOAL_ASSESSMENT_APPR_DTL.EMP_GOAL_ASSESSMENT_ID = " + 
        assessmentId + " ORDER BY HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_LEVL";
      Object[][] data = getSqlModel().getSingleResult(query);
      ArrayList tableList = new ArrayList();
      if ((data != null) && (data.length > 0))
      {
        for (int i = 0; i < data.length; i++)
        {
          SelfGoalAssessment beanObj = new SelfGoalAssessment();
          beanObj.setApproverToken(checkNull(String.valueOf(data[i][0])));
          beanObj.setApproverName(checkNull(String.valueOf(data[i][1])));
          beanObj.setHiddenassessmentId(checkNull(String.valueOf(data[i][2])));
          beanObj.setHiddenApproverId(checkNull(String.valueOf(data[i][3])));
          beanObj.setHiddenlevel(checkNull(String.valueOf(data[i][4])));
          beanObj.setHiddenempid(checkNull(String.valueOf(data[i][5])));
          System.out.println("String.valueOf(data[" + i + "][6]) :: " + String.valueOf(data[i][6]));

          beanObj.setHiddenacceptflag(checkNull(String.valueOf(data[i][6])));
          beanObj.setEmployeecomment(checkNull(String.valueOf(data[i][7])));

          tableList.add(beanObj);
          bean.setApproverlst(tableList);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getProcessedList(SelfGoalAssessment bean, HttpServletRequest request)
  {
    ArrayList goalList = new ArrayList();
    try
    {
      String query = "  SELECT HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID,GOAL_NAME,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID, \t(HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), \t EMP_GOAL_ASSESSMENT_ID,TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'),HRMS_GOAL_CONFIG.GOAL_ID as confgoalid \t FROM HRMS_GOAL_EMP_ASSESSMENT_HDR \t INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID \t inner join HRMS_GOAL_EMP_HDR on(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) \t LEFT JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID  \t LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  \t LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  \t LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  \t WHERE GOAL_ASSESSMENT_STATUS IN(2,5) AND HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID = ? \t ORDER BY GOAL_ID,GOAL_ASSESSMENT_DATE ";

      Object[][] listData = getSqlModel().getSingleResult(query, 
        new Object[] { bean.getUserEmpId() });
      String[] pageIndex = Utility.doPaging(bean.getMyPage(), 
        listData.length, 20);
      if (pageIndex == null) {
        pageIndex[0] = "0";
        pageIndex[1] = "20";
        pageIndex[2] = "1";
        pageIndex[3] = "1";
        pageIndex[4] = "";
      }
      request.setAttribute("totalPage1", Integer.valueOf(Integer.parseInt(
        String.valueOf(pageIndex[2]))));

      request.setAttribute("pageNo1", Integer.valueOf(Integer.parseInt(
        String.valueOf(pageIndex[3]))));
      if (pageIndex[4].equals("1"))
        bean.setMyPage("1");
      if ((listData != null) && (listData.length != 0)) {
        int i = Integer.parseInt(pageIndex[0]);
        for (; i < 
          Integer.parseInt(pageIndex[1]); i++) {
          SelfGoalAssessment bean1 = new SelfGoalAssessment();
          bean1.setGoalId(String.valueOf(listData[i][0]));
          bean1.setGoalName(String.valueOf(listData[i][1]));
          bean1.setEmpId(String.valueOf(listData[i][2]));
          bean1.setEmpName(String.valueOf(listData[i][3]));

          bean1.setScheduledAssessmentDateList(
            String.valueOf(listData[i][5]));
          bean1.setConfgoalId(String.valueOf(listData[i][6]));

          bean1.setGoalAssessmentId(String.valueOf(listData[i][4]));
          goalList.add(bean1);
        }
        bean.setProcessedList(goalList);
      } else {
        bean.setNoData("true");
      }
    } catch (Exception e) {
      logger.error("Exception in getProcessedList " + e);
    }
  }

  public void getReassessmentList(SelfGoalAssessment bean, HttpServletRequest request)
  {
    ArrayList goalList = new ArrayList();
    try
    {
      String query = " SELECT DISTINCT HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID,GOAL_NAME,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'),TO_CHAR(ASSESSMENT_COMPLETED_DATE,'DD-MM-YYYY'),HRMS_GOAL_EMP_ASSESSMENT_HDR .EMP_GOAL_ASSESSMENT_ID,   NVL(GOAL_ASSESSER_LEVEL,1),NVL(GOAL_REPORTING,'reporting'),HRMS_GOAL_CONFIG.GOAL_ID as confgoalid,NVL(HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_LEVL,1) FROM HRMS_GOAL_EMP_ASSESSMENT_HDR INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON (HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID) INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID INNER JOIN HRMS_GOAL_EMP_HDR on(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) LEFT JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE) INNER JOIN HRMS_GOAL_ASSESSMENT_APPR_DTL on (HRMS_GOAL_ASSESSMENT_APPR_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID and HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_ID=HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ASSESSER_ID) WHERE HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_EMP_ID = ? AND HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_SIGNOFF_ACCEPTANCE='N'  AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEWER_STATUS='Y' AND GOAL_REASSESSMENT_STATUS = 'Y' ORDER BY GOAL_ID,TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY')";

      Object[][] listData = getSqlModel().getSingleResult(query, 
        new Object[] { bean.getUserEmpId() });
      String[] pageIndex = Utility.doPaging(bean.getMyPage(), 
        listData.length, 20);
      if (pageIndex == null) {
        pageIndex[0] = "0";
        pageIndex[1] = "20";
        pageIndex[2] = "1";
        pageIndex[3] = "1";
        pageIndex[4] = "";
      }
      request.setAttribute("totalPage2", Integer.valueOf(Integer.parseInt(
        String.valueOf(pageIndex[2]))));

      request.setAttribute("pageNo2", Integer.valueOf(Integer.parseInt(
        String.valueOf(pageIndex[3]))));
      if (pageIndex[4].equals("1"))
        bean.setMyPage("1");
      if ((listData != null) && (listData.length != 0)) {
        int i = Integer.parseInt(pageIndex[0]);
        for (; i < 
          Integer.parseInt(pageIndex[1]); i++) {
          SelfGoalAssessment bean1 = new SelfGoalAssessment();
          bean1.setGoalId(String.valueOf(listData[i][0]));
          bean1.setGoalName(String.valueOf(listData[i][1]));
          bean1.setEmpId(String.valueOf(listData[i][2]));
          bean1.setEmpName(String.valueOf(listData[i][3]));

          bean1.setScheduledAssessmentDateList(
            String.valueOf(listData[i][5]));
          bean1.setConfgoalId(String.valueOf(listData[i][6]));

          bean1.setGoalAssessmentId(String.valueOf(listData[i][4]));
          goalList.add(bean1);
        }
        bean.setReassessmentList(goalList);
      } else {
        bean.setNoData("true");
      }
    } catch (Exception e) {
      logger.error("Exception in getReassessmentList " + e);
    }
  }

  public Object[][] getEmployeeDetails(String empId, String goalId) {
    Object[][] empData = (Object[][])null;
    try {
      String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,(HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  NVL(HRMS_CENTER.CENTER_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,'  '),NVL(HRMS_RANK.RANK_NAME,'  '),  (AS2.TITLE_NAME||' '||AS1.EMP_FNAME||' '||NVL(AS1.EMP_MNAME,'')|| ' ' || AS1.EMP_LNAME),TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),  DECODE(IS_GOAL_CATEGORY,'N','false','Y','true','false'),DECODE(GOAL_ACHIEVED_FLAG,'N','false','Y','true','false'),TO_CHAR(SYSDATE,'DD-MM-YYYY'),NVL(GOAL_RATING_RANGE_DESC,'')  ,NVL(GOAL_REPORTING,'reporting'),NVL(GOAL_ASSESSER_LEVEL,1),nvl(GOAL_NAME,'')\tFROM HRMS_EMP_OFFC  LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  LEFT JOIN HRMS_EMP_OFFC AS1 ON (HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = AS1.EMP_ID)  LEFT JOIN HRMS_TITLE AS2  ON(AS1.EMP_TITLE_CODE = AS2.TITLE_CODE)  INNER JOIN HRMS_GOAL_EMP_HDR ON (HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID AND GOAL_HDR_ID=" + 
        goalId + ")" + 
        "INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON (HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=" + goalId + ")" + 
        " INNER JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_EMP_HDR.GOAL_ID=HRMS_GOAL_CONFIG.GOAL_ID)" + 
        " WHERE HRMS_EMP_OFFC.EMP_ID = " + empId;
      empData = getSqlModel().getSingleResult(query);
    } catch (Exception e) {
      logger.error("Exception in getEmployeeDetails " + e);
    }
    return empData;
  }

  public ArrayList<Object> getSavedRating(String goalId, String empId, String assmtId, SelfGoalAssessment bean, String confgoalId)
  {
    ArrayList goalList = new ArrayList();
    try
    {
      String query = " SELECT SELF_GOAL_DTL_ID,GOAL_DESCRIPTION, TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY')  ,SELF_GOAL_RATING,NVL(SELF_GOAL_COMMENT,'')  ,NVL(GOAL_COMMENTS,''), NVL(GOAL_WEIGHTAGE,0),NVL(GOAL_DTL_STATUS,'A'),GOAL_CATEGORY_CODE,NVL(GOAL_CATEGORY,' '),IS_GOAL_ACHIEVED,nvl(GOAL_CATEGORY_WEIGHTAGE,'') FROM HRMS_GOAL_SELF_ASSESSMENT_DTL\t\t INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID=" + 
        assmtId + ") " + 
        " LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) ";

      Object[][] goalData = getSqlModel().getSingleResult(query);
      if ((goalData != null) && (goalData.length > 0)) {
        for (int i = 0; i < goalData.length; i++) {
          SelfGoalAssessment bean1 = new SelfGoalAssessment();
          bean1.setIndividualGoalId(Utility.checkNull(
            String.valueOf(goalData[i][0])));
          bean1.setGoalDescription(Utility.checkNull(
            String.valueOf(goalData[i][1])));
          if(bean1.getGoalDescription().length() > 15){//if length greater than 15
        	  bean1.setGoalDescriptionAbbr(bean1.getGoalDescription().substring(0, 14)+"...");
          }//end of if length greater than 15
          else{
        	  bean1.setGoalDescriptionAbbr(bean1.getGoalDescription());
          }//end of else
          bean1.setGoalAchDate(Utility.checkNull(
            String.valueOf(goalData[i][2])));
          bean1.setGoalRating(Utility.checkNull(
            String.valueOf(goalData[i][3])));
          bean1.setComment(Utility.checkNull(
            String.valueOf(goalData[i][4])));
          bean1.setAppComments(Utility.checkNull(String.valueOf(goalData[i][5])));
          if(bean1.getAppComments().length() > 15){//if length greater than 15
        	  bean1.setAppCommentsAbbr(bean1.getAppComments().substring(0, 14)+"...");
          }//end of if length greater than 15
          else{
        	  bean1.setAppCommentsAbbr(bean1.getAppComments());
          }//end of else
          bean1.setAppWeightage(Utility.checkNull(String.valueOf(goalData[i][6])));
          bean1.setIsGoalCategory(bean.getIsGoalCategory());
          bean1.setIsGoalAchievedFlag(bean.getIsGoalAchievedFlag());
          TreeMap map = setRatingList(confgoalId);

          bean1.setTmap(map);
          try {
            if (bean.getListType().equals("processed")) {
              ArrayList managerRatingList = setManagersRatingList(bean.getGoalAssessmentId(), Utility.checkNull(String.valueOf(goalData[i][0])));
              bean1.setManagerRatingList(managerRatingList);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
          bean1.setGoalDtlStatus(Utility.checkNull(String.valueOf(goalData[i][7])) + "_");
          bean1.setGoalCategoryCodeList(Utility.checkNull(String.valueOf(goalData[i][8])));
          bean1.setGoalCategoryNameList(Utility.checkNull(String.valueOf(goalData[i][9])));
          bean1.setGoalAchievedList(Utility.checkNull(String.valueOf(goalData[i][10])));
          bean1.setRevieweWeight(Utility.checkNull(String.valueOf(goalData[i][11])));

          goalList.add(bean1);
        }
      }
      if (goalList.size() == 0) {
        goalList = getGoalDetails(goalId, empId, bean, confgoalId);
        bean.setIsRecordSaved("false");
      } else {
        bean.setIsRecordSaved("true");
      }
    } catch (Exception e) {
      logger.error("Exception in getGoalDetails " + e);
      goalList = getGoalDetails(goalId, empId, bean, confgoalId);
      bean.setIsRecordSaved("false");
    }
    return goalList;
  }

  public ArrayList<Object> getGoalDetails(String goalId, String empId, SelfGoalAssessment bean, String confgoalId) {
    ArrayList goalList = null;
    try {
      String query = " SELECT GOAL_DTL_ID,GOAL_DESCRIPTION,TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY')  ,NVL(GOAL_COMMENTS,''), NVL(GOAL_WEIGHTAGE,0),NVL(GOAL_DTL_STATUS,'A'), GOAL_CATEGORY_CODE,NVL(GOAL_CATEGORY,' '),nvl(GOAL_CATEGORY_WEIGHTAGE,'') FROM HRMS_GOAL_EMP_DTL  LEFT JOIN HRMS_GOAL_EMP_HDR On HRMS_GOAL_EMP_HDR.GOAL_HDR_ID = HRMS_GOAL_EMP_DTL.GOAL_HDR_ID  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID)  WHERE GOAL_DTL_STATUS='A' AND HRMS_GOAL_EMP_HDR.GOAL_HDR_ID = " + 
        goalId + " AND EMP_ID = " + empId;
      Object[][] goalData = getSqlModel().getSingleResult(query);
      if ((goalData != null) && (goalData.length > 0)) {
        goalList = new ArrayList();
        for (int i = 0; i < goalData.length; i++) {
          SelfGoalAssessment bean1 = new SelfGoalAssessment();
          bean1.setIndividualGoalId(checkNull(String.valueOf(goalData[i][0])));
          bean1.setGoalDescription(checkNull(String.valueOf(goalData[i][1])));
          if(bean1.getGoalDescription().length() > 15){//if length greater than 15
        	  bean1.setGoalDescriptionAbbr(bean1.getGoalDescription().substring(0, 14)+"...");
          }//end of if length greater than 15
          else{
        	  bean1.setGoalDescriptionAbbr(bean1.getGoalDescription());
          }//end of else
          bean1.setGoalAchDate(checkNull(String.valueOf(goalData[i][2])));
          bean1.setAppComments(checkNull(String.valueOf(goalData[i][3])));
          if(bean1.getAppComments().length() > 15){//if length greater than 15
        	  bean1.setAppCommentsAbbr(bean1.getAppComments().substring(0, 14)+"...");
          }//end of if length greater than 15
          else{
        	  bean1.setAppCommentsAbbr(bean1.getAppComments());
          }//end of else
          bean1.setAppWeightage(checkNull(String.valueOf(goalData[i][4])));
          bean1.setIsGoalCategory(bean.getIsGoalCategory());
          bean1.setIsGoalAchievedFlag(bean.getIsGoalAchievedFlag());
          bean1.setGoalDtlStatus(checkNull(String.valueOf(goalData[i][5])) + "_");
          bean1.setGoalCategoryCodeList(Utility.checkNull(String.valueOf(goalData[i][6])));
          bean1.setGoalCategoryNameList(Utility.checkNull(String.valueOf(goalData[i][7])));
          bean1.setRevieweWeight(Utility.checkNull(String.valueOf(goalData[i][8])));
          TreeMap map = setRatingList(confgoalId);

          bean1.setTmap(map);
          goalList.add(bean1);
        }
      }
    } catch (Exception e) {
      logger.error("Exception in getGoalDetails " + e);
    }
    return goalList;
  }

  public boolean saveRating(SelfGoalAssessment bean, HttpServletRequest request)
  {
    Object[] individualGoalId = request
      .getParameterValues("individualGoalId");
    Object[] goalRating = request.getParameterValues("goalRating");
    Object[] comment = request.getParameterValues("comment");
    Object[] goalAchievedList = request.getParameterValues("goalAchievedList");

    boolean result = false;
    if (getSqlModel().singleExecute(
      "DELETE FROM HRMS_GOAL_SELF_ASSESSMENT_DTL WHERE SELF_GOAL_ASSESSMENT_ID=" + 
      bean.getGoalAssessmentId())) {
      Object[][] maxRatingIdObj = getSqlModel()
        .getSingleResult(
        "SELECT NVL(MAX( SELF_ASSESSMENT_DTL_ID ),0)+1 FROM HRMS_GOAL_SELF_ASSESSMENT_DTL");
      int maxRatingId = Integer.parseInt(
        String.valueOf(maxRatingIdObj[0][0]));

      String insertQuery = "INSERT INTO HRMS_GOAL_SELF_ASSESSMENT_DTL ( SELF_GOAL_ASSESSMENT_ID ,  SELF_ASSESSMENT_DTL_ID ,  SELF_GOAL_DTL_ID , SELF_GOAL_RATING,  SELF_GOAL_COMMENT  ) VALUES (?, ?, ?, ?, ?)";
      try
      {
        Object[][] insertRating = new Object[comment.length][5];
        for (int i = 0; i < insertRating.length; i++) {
          insertRating[i][0] = bean.getGoalAssessmentId();
          insertRating[i][1] = Integer.valueOf(maxRatingId++);
          insertRating[i][2] = individualGoalId[i];
          insertRating[i][3] = goalRating[i];
          insertRating[i][4] = comment[i];
        }
        result = getSqlModel().singleExecute(insertQuery, insertRating);

        if ((result) && 
          (bean.getIsGoalAchievedFlag().equals("true")))
        {
          String updateAchFlagQuery = "UPDATE HRMS_GOAL_EMP_DTL SET IS_GOAL_ACHIEVED=? WHERE GOAL_DTL_ID=?";
          Object[][] updateAchieveFlagObj = new Object[comment.length][2];
          for (int i = 0; i < updateAchieveFlagObj.length; i++) {
            updateAchieveFlagObj[i][0] = goalAchievedList[i];
            updateAchieveFlagObj[i][1] = individualGoalId[i];
          }
          result = getSqlModel().singleExecute(updateAchFlagQuery, updateAchieveFlagObj);
        }
      }
      catch (Exception localException)
      {
      }
    }
    return result;
  }

  public void getRatingList(SelfGoalAssessment bean, HttpServletRequest request)
  {
    try {
      String[] individualGoalId = request
        .getParameterValues("individualGoalId");
      String[] goalRating = request.getParameterValues("goalRating");
      String[] comment = request.getParameterValues("comment");
      String[] goalDescription = request
        .getParameterValues("goalDescription");
      String[] goalAchDate = request.getParameterValues("goalAchDate");
      String[] appComments = request.getParameterValues("appComments");
      String[] appWeightage = request.getParameterValues("appWeightage");
      String[] goalDtlStatus = request.getParameterValues("goalDtlStatus");
      String[] goalCategoryNameList = request.getParameterValues("goalCategoryNameList");
      String[] goalAchievedList = request.getParameterValues("goalAchievedList");
      logger.info("getRatingList MODEL");
      try {
        ArrayList tableList = new ArrayList();
        for (int i = 0; i < comment.length; i++) {
          SelfGoalAssessment beanList = new SelfGoalAssessment();
          beanList.setIndividualGoalId(individualGoalId[i]);
          beanList.setGoalRating(goalRating[i]);
          beanList.setComment(comment[i]);
          beanList.setGoalDescription(goalDescription[i]);
          if(beanList.getGoalDescription().length() > 15){//if length greater than 15
        	  beanList.setGoalDescriptionAbbr(beanList.getGoalDescription().substring(0, 14)+"...");
          }//end of if length greater than 15
          else{
        	  beanList.setGoalDescriptionAbbr(beanList.getGoalDescription());
          }//end of else
          beanList.setGoalAchDate(goalAchDate[i]);
          beanList.setAppComments(appComments[i]);
          if(beanList.getAppComments().length() > 15){//if length greater than 15
        	  beanList.setAppCommentsAbbr(beanList.getAppComments().substring(0, 14)+"...");
          }//end of if length greater than 15
          else{
        	  beanList.setAppCommentsAbbr(beanList.getAppComments());
          }//end of else
          beanList.setAppWeightage(appWeightage[i]);
          beanList.setGoalDtlStatus(goalDtlStatus[i]);
          beanList.setIsGoalCategory(bean.getIsGoalCategory());
          beanList.setIsGoalAchievedFlag(bean.getIsGoalAchievedFlag());
          if (bean.getIsGoalCategory().equals("true")) {
            beanList.setGoalCategoryNameList(goalCategoryNameList[i]);
          }
          if (bean.getIsGoalAchievedFlag().equals("true")) {
            beanList.setGoalAchievedList(goalAchievedList[i]);
          }
          tableList.add(beanList);
        }
        if (tableList.size() > 0) {
          logger.info("tableList.size()>0");
          bean.setGoalList(tableList);
        } else {
          bean.setNoData("true");
        }
      } catch (Exception e) {
        bean.setNoData("true");
      }
    }
    catch (Exception localException1)
    {
    }
  }

  public boolean finalizeAssesment(String goalAssessmentId, String goalId, String approverId)
  {
    boolean reult = false;

    String updateAssessmentStatus = " update  HRMS_GOAL_EMP_ASSESSMENT_HDR   set GOAL_ASSESSMENT_STATUS=5 ";
    		if(!approverId.equals("")){
    			updateAssessmentStatus+=" , GOAL_ASSESSER_ID="+approverId ;
    		}
    		updateAssessmentStatus+=" where EMP_GOAL_ASSESSMENT_ID="+goalAssessmentId;

    reult = getSqlModel().singleExecute(updateAssessmentStatus);

    return reult;
  }

  public TreeMap setRatingList(String GoaId) {
    String query = "SELECT GOAL_RATING_RANGE_UPTO FROM HRMS_GOAL_CONFIG WHERE GOAL_ID = " + GoaId;
    Object[][] data = getSqlModel().getSingleResult(query);
    int ratingupto = 5;
    if ((data != null) && (data.length > 0))
      ratingupto = Integer.parseInt(String.valueOf(data[0][0]));
    System.out.println("The rating upto : " + ratingupto);
    TreeMap map = new TreeMap();

    for (int i = 1; i <= ratingupto; i++) {
      map.put(Integer.valueOf(i), Integer.valueOf(i));
    }

    return map;
  }

  public void setManagersRating(String assessId) {
    String query = "SELECT NVL(GOAL_RATING,''),NVL(GOAL_COMMENT,''),'false',GOAL_DTL_ID,EMP_FNAME||' '||EMP_LNAME FROM HRMS_GOAL_EMP_ASSESSMENT_DTL LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID) WHERE EMP_GOAL_ASSESSMENT_ID =" + 
      assessId + 
      " ORDER BY GOAL_DTL_ID,ASSESSMENT_DTL_ID ";
    this.ratingData = getSqlModel().getSingleResultMap(query, 3, 2);
  }

  public ArrayList setManagersRatingList(String assessId, String goalId) {
    ArrayList tableList = new ArrayList();
    Object[][] goalRating = (Object[][])this.ratingData.get(goalId);
    if ((goalRating != null) && (goalRating.length > 0)) {
      for (int i = 0; i < goalRating.length; i++) {
        SelfGoalAssessment beanList = new SelfGoalAssessment();
        beanList.setManagerRating(Utility.checkNull(String.valueOf(goalRating[i][0])));
        beanList.setManagerComments(Utility.checkNull(String.valueOf(goalRating[i][1])));
        beanList.setManagerName(Utility.checkNull(String.valueOf(goalRating[i][4])));
        tableList.add(beanList);
      }
    }

    return tableList;
  }
  public String checkNull(String result) {
    if ((result == null) || (result.equals("")) || (result.equals("null"))) {
      return "";
    }
    return result;
  }

  public boolean acceptRejectSignOffGoal(SelfGoalAssessment selfGoalAssessment, String goalSignOffStatus)
  {
    boolean result = false;
    try {
      String query = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_SIGNOFF_ACCEPTANCE = '" + goalSignOffStatus + "'" + 
        " WHERE EMP_GOAL_ASSESSMENT_ID = " + 
        selfGoalAssessment.getGoalAssessmentId();
      result = getSqlModel().singleExecute(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public boolean updateApproveData(String[] assessmentId, String[] hiddenlevel, String[] hiddenacceptflag, String[] employeecomment, String[] hiddenApproverId)
  {
    boolean result = false;
    try
    {
      if ((assessmentId != null) && (assessmentId.length > 0))
      {
        Object[][] data = new Object[assessmentId.length][5];
        for (int i = 0; i < assessmentId.length; i++)
        {
          System.out.println("assessmentId [" + i + "]" + assessmentId[i]);
          System.out.println("hiddenlevel [" + i + "]" + hiddenlevel[i]);
          System.out.println("hiddenacceptflag [" + i + "]" + hiddenacceptflag[i]);
          System.out.println("employeecomment [" + i + "]" + employeecomment[i]);
          System.out.println("hiddenApproverId [" + i + "]" + hiddenApproverId[i]);
          data[i][0] = hiddenacceptflag[i];
          data[i][1] = employeecomment[i];
          data[i][2] = assessmentId[i];
          data[i][3] = hiddenlevel[i];
          data[i][4] = hiddenApproverId[i];
        }

        String updateQuery = "UPDATE HRMS_GOAL_ASSESSMENT_APPR_DTL SET GOAL_SIGNOFF_ACCEPTANCE = ? , GOAL_EMP_COMMENTS = ?, GOAL_REASSESSMENT_STATUS = 'N' WHERE EMP_GOAL_ASSESSMENT_ID = ? AND GOAL_APPROVER_LEVL = ? AND GOAL_APPROVER_ID = ?";
        result = getSqlModel().singleExecute(updateQuery, data);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public boolean updateAssessmentHeader(String assessmentId, String loginId)
  {
    boolean result = false;
    try {
      String query = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_REVIEWER_STATUS = 'N'   WHERE EMP_GOAL_ASSESSMENT_ID = " + 
        assessmentId + 
        " AND EMP_ID = " + loginId;
      result = getSqlModel().singleExecute(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}