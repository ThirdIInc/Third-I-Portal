package org.paradyne.model.PAS.GoalSetting;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.ReviewerGoalAssessment;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;

public class ReviewerGoalAssessmentModel extends ModelBase
{
  static Logger logger = Logger.getLogger(ReviewerGoalAssessmentModel.class);
  HashMap<String, Object[][]> ratingData = null;

  public ArrayList<Object> getPendingGoalAssmtList(String userEmpId) {
    ArrayList goalList = null;
    try {
      String query = "SELECT HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID,GOAL_NAME,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID, \t(HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'),NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  '),EMP_GOAL_ASSESSMENT_ID,HRMS_GOAL_CONFIG.GOAL_ID as confgoalid \t FROM HRMS_GOAL_EMP_ASSESSMENT_HDR \t INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID \t inner join HRMS_GOAL_EMP_HDR on(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) \t LEFT JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID  \t LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  \t LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  \t LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  \t WHERE GOAL_ASSESSMENT_STATUS IN(2,5) AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEWER_ID = ?  AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEWER_STATUS='N' ORDER BY GOAL_ID,GOAL_ASSESSMENT_DATE ";
      System.out.println("The User Id : " + userEmpId);
      Object[][] listData = getSqlModel().getSingleResult(query, 
        new Object[] { userEmpId });
      if ((listData != null) && (listData.length != 0)) {
        goalList = new ArrayList();
        for (int i = 0; i < listData.length; i++) {
          ReviewerGoalAssessment bean1 = new ReviewerGoalAssessment();
          bean1.setGoalId(checkNull(String.valueOf(listData[i][0])));
          bean1.setGoalName(checkNull(String.valueOf(listData[i][1])));
          bean1.setEmpId(checkNull(String.valueOf(listData[i][2])));
          bean1.setEmpName(checkNull(String.valueOf(listData[i][3])));
          bean1.setScheduledAssessmentDateList(checkNull(String.valueOf(listData[i][4])));
          bean1.setEmpDept(checkNull(String.valueOf(listData[i][5])));
          bean1.setEmpDesg(checkNull(String.valueOf(listData[i][6])));
          bean1.setGoalAssessmentId(checkNull(String.valueOf(listData[i][7])));
          bean1.setConfgoalId(checkNull(String.valueOf(listData[i][8])));
          goalList.add(bean1);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return goalList;
  }

  public Object[][] getEmployeeDetails(String empId, String goalId) {
    Object[][] empData = (Object[][])null;
    try {
      String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,(HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  NVL(HRMS_CENTER.CENTER_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,'  '),NVL(HRMS_RANK.RANK_NAME,'  '),  (AS2.TITLE_NAME||' '||AS1.EMP_FNAME||' '||NVL(AS1.EMP_MNAME,'')|| ' ' || AS1.EMP_LNAME),TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),  DECODE(IS_GOAL_CATEGORY,'N','false','Y','true','false'),DECODE(GOAL_ACHIEVED_FLAG,'N','false','Y','true','false'),TO_CHAR(SYSDATE,'DD-MM-YYYY'),NVL(GOAL_RATING_RANGE_DESC,'') FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  LEFT JOIN HRMS_EMP_OFFC AS1 ON (HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = AS1.EMP_ID)  LEFT JOIN HRMS_TITLE AS2  ON(AS1.EMP_TITLE_CODE = AS2.TITLE_CODE)  INNER JOIN HRMS_GOAL_EMP_HDR ON (HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID AND GOAL_HDR_ID=" + 
        goalId + ")" + 
        " INNER JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_EMP_HDR.GOAL_ID=HRMS_GOAL_CONFIG.GOAL_ID)" + 
        " WHERE HRMS_EMP_OFFC.EMP_ID = " + empId;
      empData = getSqlModel().getSingleResult(query);
    } catch (Exception e) {
      logger.error("Exception in getEmployeeDetails " + e);
    }
    return empData;
  }

  public void getProcessedList(ReviewerGoalAssessment bean, HttpServletRequest request)
  {
    ArrayList goalList = new ArrayList();
    try
    {
      String query = "SELECT HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID,GOAL_NAME,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID, \t(HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'),NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  '),EMP_GOAL_ASSESSMENT_ID,HRMS_GOAL_CONFIG.GOAL_ID as confgoalid \t FROM HRMS_GOAL_EMP_ASSESSMENT_HDR \t INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID \t inner join HRMS_GOAL_EMP_HDR on(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) \t LEFT JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID  \t LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  \t LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  \t LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  \t WHERE GOAL_ASSESSMENT_STATUS IN(2,5) AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEWER_ID = ?  AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEWER_STATUS='Y' ORDER BY GOAL_ID,GOAL_ASSESSMENT_DATE ";

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
          ReviewerGoalAssessment bean1 = new ReviewerGoalAssessment();
          bean1.setGoalId(String.valueOf(listData[i][0]));
          bean1.setGoalName(String.valueOf(listData[i][1]));
          bean1.setEmpId(String.valueOf(listData[i][2]));
          bean1.setEmpName(String.valueOf(listData[i][3]));

          bean1.setScheduledAssessmentDateList(
            String.valueOf(listData[i][4]));
          bean1.setConfgoalId(String.valueOf(listData[i][8]));

          bean1.setGoalAssessmentId(String.valueOf(listData[i][7]));
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

  public ArrayList<Object> getSavedRating(String goalId, String empId, String assmtId, ReviewerGoalAssessment bean, String confgoalId)
  {
    ArrayList goalList = new ArrayList();
    try
    {
      String query = " SELECT SELF_GOAL_DTL_ID,GOAL_DESCRIPTION, TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY')  ,SELF_GOAL_RATING,NVL(SELF_GOAL_COMMENT,'')  ,NVL(GOAL_COMMENTS,''), NVL(GOAL_WEIGHTAGE,0),NVL(GOAL_DTL_STATUS,'A'),GOAL_CATEGORY_CODE,NVL(GOAL_CATEGORY,' '),IS_GOAL_ACHIEVED FROM HRMS_GOAL_SELF_ASSESSMENT_DTL\t\t INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID=" + 
        assmtId + ") " + 
        " LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) ";

      Object[][] goalData = getSqlModel().getSingleResult(query);
      if ((goalData != null) && (goalData.length > 0)) {
        for (int i = 0; i < goalData.length; i++) {
          ReviewerGoalAssessment bean1 = new ReviewerGoalAssessment();
          bean1.setIndividualGoalId(Utility.checkNull(
            String.valueOf(goalData[i][0])));
          bean1.setGoalDescription(Utility.checkNull(
            String.valueOf(goalData[i][1])));
          bean1.setGoalAchDate(Utility.checkNull(
            String.valueOf(goalData[i][2])));
          bean1.setGoalRating(Utility.checkNull(
            String.valueOf(goalData[i][3])));
          bean1.setComment(Utility.checkNull(
            String.valueOf(goalData[i][4])));
          bean1.setAppComments(Utility.checkNull(String.valueOf(goalData[i][5])));
          bean1.setAppWeightage(Utility.checkNull(String.valueOf(goalData[i][6])));
          bean1.setIsGoalCategory(bean.getIsGoalCategory());
          bean1.setIsGoalAchievedFlag(bean.getIsGoalAchievedFlag());
          TreeMap map = setRatingList(confgoalId);

          bean1.setTmap(map);
          try
          {
            ArrayList managerRatingList = setManagersRatingList(bean.getGoalAssessmentId(), Utility.checkNull(String.valueOf(goalData[i][0])));
            bean1.setManagerRatingList(managerRatingList);
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          bean1.setGoalDtlStatus(Utility.checkNull(String.valueOf(goalData[i][7])) + "_");
          bean1.setGoalCategoryCodeList(Utility.checkNull(String.valueOf(goalData[i][8])));
          bean1.setGoalCategoryNameList(Utility.checkNull(String.valueOf(goalData[i][9])));
          bean1.setGoalAchievedList(Utility.checkNull(String.valueOf(goalData[i][10])));

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

  public ArrayList<Object> getGoalDetails(String goalId, String empId, ReviewerGoalAssessment bean, String confgoalId) {
    ArrayList goalList = null;
    try {
      String query = " SELECT GOAL_DTL_ID,GOAL_DESCRIPTION,TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY')  ,NVL(GOAL_COMMENTS,''), NVL(GOAL_WEIGHTAGE,0),NVL(GOAL_DTL_STATUS,'A'), GOAL_CATEGORY_CODE,NVL(GOAL_CATEGORY,' ')FROM HRMS_GOAL_EMP_DTL  LEFT JOIN HRMS_GOAL_EMP_HDR On HRMS_GOAL_EMP_HDR.GOAL_HDR_ID = HRMS_GOAL_EMP_DTL.GOAL_HDR_ID  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID)  WHERE GOAL_DTL_STATUS='A' AND HRMS_GOAL_EMP_HDR.GOAL_HDR_ID = " + 
        goalId + " AND EMP_ID = " + empId;
      Object[][] goalData = getSqlModel().getSingleResult(query);
      if ((goalData != null) && (goalData.length > 0)) {
        goalList = new ArrayList();
        for (int i = 0; i < goalData.length; i++) {
          ReviewerGoalAssessment bean1 = new ReviewerGoalAssessment();
          bean1.setIndividualGoalId(checkNull(String.valueOf(goalData[i][0])));
          bean1.setGoalDescription(checkNull(String.valueOf(goalData[i][1])));
          bean1.setGoalAchDate(checkNull(String.valueOf(goalData[i][2])));
          bean1.setAppComments(checkNull(String.valueOf(goalData[i][3])));
          bean1.setAppWeightage(checkNull(String.valueOf(goalData[i][4])));
          bean1.setIsGoalCategory(bean.getIsGoalCategory());
          bean1.setIsGoalAchievedFlag(bean.getIsGoalAchievedFlag());
          bean1.setGoalDtlStatus(checkNull(String.valueOf(goalData[i][5])) + "_");
          bean1.setGoalCategoryCodeList(Utility.checkNull(String.valueOf(goalData[i][6])));
          bean1.setGoalCategoryNameList(Utility.checkNull(String.valueOf(goalData[i][7])));
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

  public TreeMap setRatingList(String GoaId)
  {
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

  public ArrayList setManagersRatingList(String assessId, String goalId)
  {
    ArrayList tableList = new ArrayList();
    Object[][] goalRating = (Object[][])this.ratingData.get(goalId);
    if ((goalRating != null) && (goalRating.length > 0)) {
      for (int i = 0; i < goalRating.length; i++) {
        ReviewerGoalAssessment beanList = new ReviewerGoalAssessment();
        beanList.setManagerRating(Utility.checkNull(String.valueOf(goalRating[i][0])));
        beanList.setManagerComments(Utility.checkNull(String.valueOf(goalRating[i][1])));
        beanList.setManagerName(Utility.checkNull(String.valueOf(goalRating[i][4])));
        tableList.add(beanList);
      }
    }

    return tableList;
  }
  public void setManagersRating(String assessId) {
    String query = "SELECT NVL(GOAL_RATING,''),NVL(GOAL_COMMENT,''),'false',GOAL_DTL_ID,EMP_FNAME||' '||EMP_LNAME FROM HRMS_GOAL_EMP_ASSESSMENT_DTL LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID) WHERE EMP_GOAL_ASSESSMENT_ID =" + 
      assessId + 
      " ORDER BY GOAL_DTL_ID,ASSESSMENT_DTL_ID ";
    this.ratingData = getSqlModel().getSingleResultMap(query, 3, 2);
  }

  public void getApproverData(ReviewerGoalAssessment bean, String assessmentId)
  {
    try {
      String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME NAME,HRMS_GOAL_ASSESSMENT_APPR_DTL.EMP_GOAL_ASSESSMENT_ID,HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_ID, HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_LEVL,GOAL_EMP_ID,GOAL_SIGNOFF_ACCEPTANCE,NVL(GOAL_EMP_COMMENTS,'') AS EMPLOYEE_COMMENT,NVL(GOAL_REVIEWER_COMMENTS,'') AS REVIEWER_COMMENT FROM  HRMS_GOAL_ASSESSMENT_APPR_DTL INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_ID WHERE HRMS_GOAL_ASSESSMENT_APPR_DTL.EMP_GOAL_ASSESSMENT_ID = " + 
        assessmentId + " ORDER BY HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_LEVL";
      Object[][] data = getSqlModel().getSingleResult(query);
      ArrayList tableList = new ArrayList();
      if ((data != null) && (data.length > 0))
      {
        for (int i = 0; i < data.length; i++)
        {
          ReviewerGoalAssessment beanObj = new ReviewerGoalAssessment();
          beanObj.setApproverToken(checkNull(String.valueOf(data[i][0])));
          beanObj.setApproverName(checkNull(String.valueOf(data[i][1])));
          beanObj.setHiddenassessmentId(checkNull(String.valueOf(data[i][2])));
          beanObj.setHiddenApproverId(checkNull(String.valueOf(data[i][3])));
          beanObj.setHiddenlevel(checkNull(String.valueOf(data[i][4])));
          beanObj.setHiddenempid(checkNull(String.valueOf(data[i][5])));
          System.out.println("String.valueOf(data[" + i + "][6]) :: " + String.valueOf(data[i][6]));

          beanObj.setHiddenacceptflag(checkNull(String.valueOf(data[i][6])));
          beanObj.setEmployeecomment(checkNull(String.valueOf(data[i][7])));
          beanObj.setReviewercomment(checkNull(String.valueOf(data[i][8])));

          tableList.add(beanObj);
          bean.setApproverlst(tableList);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean updateApproveData(String[] assessmentId, String[] hiddenlevel, String[] hiddenacceptflag, String[] reviewerecomment, String[] hiddenApproverId)
  {
    boolean result = false;
    try
    {
      if ((assessmentId != null) && (assessmentId.length > 0))
      {
        for (int i = 0; i < assessmentId.length; i++)
        {
          System.out.println("assessmentId [" + i + "]" + assessmentId[i]);
          System.out.println("hiddenlevel [" + i + "]" + hiddenlevel[i]);
          System.out.println("hiddenacceptflag [" + i + "]" + hiddenacceptflag[i]);
          System.out.println("reviewerecomment [" + i + "]" + reviewerecomment[i]);
          System.out.println("hiddenApproverId [" + i + "]" + hiddenApproverId[i]);
          String updateQuery = "UPDATE HRMS_GOAL_ASSESSMENT_APPR_DTL SET  GOAL_REVIEWER_COMMENTS = '" + reviewerecomment[i] + "' WHERE EMP_GOAL_ASSESSMENT_ID = " + assessmentId[i] + " AND GOAL_APPROVER_LEVL = " + hiddenlevel[i] + " AND GOAL_APPROVER_ID = " + hiddenApproverId[i];
          System.out.println("Update Query ::::: ---- ::: " + updateQuery);
          result = getSqlModel().singleExecute(updateQuery);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return result;
  }

  public boolean updateAssessmentHeader(String assessmentId, String reviewerId)
  {
    boolean result = false;
    try {
      String query = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_REVIEW_DATE=SYSDATE,GOAL_REVIEWER_STATUS = 'Y' WHERE EMP_GOAL_ASSESSMENT_ID = " + 
        assessmentId + 
        " AND GOAL_REVIEWER_ID = " + reviewerId;
      result = getSqlModel().singleExecute(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public void getReviewerStatus(ReviewerGoalAssessment bean, String assessmentId) {
    try {
      String query = "SELECT GOAL_REVIEWER_STATUS FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE EMP_GOAL_ASSESSMENT_ID =" + assessmentId;
      Object[][] data = getSqlModel().getSingleResult(query);

      if ((data != null) && (data.length > 0))
      {
        if (checkNull(String.valueOf(data[0][0])).equals("Y"))
          bean.setListType("");
        else
          bean.setListType("pending");
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