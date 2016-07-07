package org.paradyne.model.PAS.Competency;

import com.itextpdf.text.BaseColor;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompInitialization;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class CompInitializationModel extends ModelBase
{
  static Logger logger = Logger.getLogger(CompInitializationModel.class);

  public String addGoal(CompInitialization bean, String[] category, String[] categoryWeightage, HttpServletRequest request)
  {
    Object[][] maxCompIdObj = getSqlModel().getSingleResult(
      "SELECT NVL(MAX(COMP_ID),0)+1 FROM HRMS_COMP_CONFIG");
    String maxCompId = String.valueOf(maxCompIdObj[0][0]);
    Object[][] addCompObj = new Object[1][17];
    addCompObj[0][0] = maxCompId;
    addCompObj[0][1] = bean.getCompName();
    addCompObj[0][2] = bean.getCompfromDate();
    addCompObj[0][3] = bean.getComptoDate();
    addCompObj[0][4] = "1";
    addCompObj[0][5] = bean.getAppraisalCode();
    addCompObj[0][6] = bean.getReportingType();
    addCompObj[0][7] = bean.getIsSignOffRequired();
    addCompObj[0][8] = bean.getCompWeightage();
    addCompObj[0][9] = bean.getAppWeightage();
    addCompObj[0][10] = bean.getSelfAsstWeightage();
    addCompObj[0][11] = bean.getManagerAsstWeightage();
    addCompObj[0][12] = bean.getMultipleManagerRatingRadio();
    addCompObj[0][13] = bean.getIsEscalation();
    addCompObj[0][14] = bean.getEscalationMailId();
    addCompObj[0][15] = bean.getIsCategoryReq();
    addCompObj[0][16] = bean.getHiddenConfRating();

    String query = "INSERT INTO HRMS_COMP_CONFIG( COMP_ID, COMP_NAME, COMP_FROM_DATE, COMP_TO_DATE, COMP_PUBLISH_STATUS ,APPRAISAL_CODE,COMP_REPORTING, COMP_SIGNOFF_WORKFLOW,COMP_RATING_WEIGHTAGE,APPRAISAL_RATING_WEIGHTAGE, COMP_SELF_WEIGHTAGE, COMP_MANAGER_WEIGHTAGE, MULTIPLE_RATING_OPTION,COMP_ESCALATION_WORKFLOW, COMP_ESCALATION_EMAIL,IS_COMP_CATEGORY,COMP_RATING_FLAG)VALUES (?, ?, TO_DATE(?, 'DD-MM-YYYY'), TO_DATE(?, 'DD-MM-YYYY'), ?,?,?,DECODE(?,'true','Y','false','N'),?,?,?,?,?,DECODE(?,'true','Y','false','N'),?,DECODE(?,'true','Y','false','N'),?)";

    boolean result = getSqlModel().singleExecute(query, addCompObj);
    if (result) {
      bean.setCompId(maxCompId);
      boolean result1 = addCategory(bean, category, categoryWeightage);
      saveReviewDates(bean, request);
      return "Saved";
    }
    return "Not saved";
  }

  public void data(CompInitialization bean, HttpServletRequest request)
  {
    String query = "SELECT COMP_ID, COMP_NAME, TO_CHAR(COMP_FROM_DATE,'DD-MM-YYYY') ,TO_CHAR(COMP_TO_DATE,'DD-MM-YYYY') , DECODE(COMP_PUBLISH_STATUS,'1','Draft','2','Published') FROM HRMS_COMP_CONFIG";
    Object[][] obj = getSqlModel().getSingleResult(query);
    String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length, 20);
    if (pageIndex == null) {
      pageIndex[0] = "0";
      pageIndex[1] = "20";
      pageIndex[2] = "1";
      pageIndex[3] = "1";
      pageIndex[4] = "";
    }
    request.setAttribute("totalPage", Integer.valueOf(Integer.parseInt(
      String.valueOf(pageIndex[2]))));
    request.setAttribute("pageNo", Integer.valueOf(Integer.parseInt(
      String.valueOf(pageIndex[3]))));
    if (pageIndex[4].equals("1")) {
      bean.setMyPage("1");
    }
    ArrayList list = new ArrayList();
    if ((obj != null) && (obj.length > 0)) {
      int i = Integer.parseInt(pageIndex[0]);
      for (; i < 
        Integer.parseInt(pageIndex[1]); i++) {
        CompInitialization bean1 = new CompInitialization();
        bean1.setCompIdItt(String.valueOf(obj[i][0]));
        bean1.setCompNameItt(String.valueOf(obj[i][1]));
        bean1.setCompfromDateItt(String.valueOf(obj[i][2]));
        bean1.setComptoDateItt(String.valueOf(obj[i][3]));
        bean1.setCompStatusItt(String.valueOf(obj[i][4]));
        list.add(bean1);
      }
    }
    bean.setIteratorlist(list);
  }

  public void getDateRow(CompInitialization bean, HttpServletRequest request) {
    String[] reviewDate = request.getParameterValues("reviewDate");
    String[] reviewWeightage = request.getParameterValues("reviewWeightage");
    ArrayList tableList = new ArrayList();
    try
    {
      if ((reviewDate != null) && (reviewDate.length > 0))
      {
        for (int i = 0; i < reviewDate.length; i++) {
          CompInitialization beanList = new CompInitialization();
          beanList.setReviewDate(reviewDate[i]);
          beanList.setReviewWeightage(reviewWeightage[i]);
          tableList.add(beanList);
        }
        bean.setReviewDateList(tableList);
      }
    } catch (Exception localException) {
    }
  }

  public void addDateRow(CompInitialization bean, HttpServletRequest request) {
    String[] reviewDate = request.getParameterValues("reviewDate");
    String[] weightage = request.getParameterValues("reviewWeightage");
    int totalWeightage = 0;
    ArrayList tableList = new ArrayList();
    try
    {
      if ((reviewDate != null) && (reviewDate.length > 0))
      {
        for (int i = 0; i < reviewDate.length; i++) {
          CompInitialization beanList = new CompInitialization();
          beanList.setReviewDate(reviewDate[i]);
          beanList.setReviewWeightage(weightage[i]);
          totalWeightage += Integer.parseInt(weightage[i] != null ? weightage[i] : "0");
          tableList.add(beanList);
        }

        bean.setReviewDate("");
        bean.setReviewWeightage("");
        tableList.add(bean);
        bean.setReviewDateList(tableList);
        bean.setTotalreviewWeight(String.valueOf(totalWeightage));
      } else {
        bean.setReviewDate("");
        bean.setReviewWeightage("");
        tableList.add(bean);
        bean.setReviewDateList(tableList);
        bean.setTotalreviewWeight(bean.getTotalreviewWeight());
      }
    } catch (Exception e) {
      bean.setReviewDate("");
      bean.setReviewWeightage("");
      tableList.add(bean);
      bean.setReviewDateList(tableList);
    }
  }

  public void showReviewDates(CompInitialization bean) {
    String query = "SELECT TO_CHAR(COMP_ASSESSMENT_DATE,'DD-MM-YYYY'),NVL(COMP_ASSESSMENT_WEIGTAGE,0) FROM HRMS_COMP_ASSESSMENT_CONFIG WHERE COMP_ID= " + 
      bean.getCompId();
    Object[][] reviewDateObj = getSqlModel().getSingleResult(query);

    ArrayList tableList = new ArrayList();
    int totalReviewWeight = 0;
    try
    {
      if ((reviewDateObj != null) && (reviewDateObj.length > 0)) {
        for (int i = 0; i < reviewDateObj.length; i++) {
          CompInitialization beanList = new CompInitialization();
          beanList.setReviewDate(Utility.checkNull(
            String.valueOf(reviewDateObj[i][0])));
          beanList.setReviewWeightage(Utility.checkNull(String.valueOf(reviewDateObj[i][1])));
          totalReviewWeight += Integer.parseInt(String.valueOf(reviewDateObj[i][1]));
          tableList.add(beanList);
        }
      }
      bean.setReviewDateList(tableList);
      bean.setTotalreviewWeight(String.valueOf(totalReviewWeight));
    }
    catch (Exception localException) {
    }
  }

  public void removeDate(CompInitialization bean, HttpServletRequest request) {
    String[] reviewDate = request.getParameterValues("reviewDate");
    String[] weightage = request.getParameterValues("reviewWeightage");
    ArrayList tableList = new ArrayList();
    int totalWeightage = 0;
    try {
      logger.info("bean.getParaId()==" + bean.getParaId());
      if ((reviewDate != null) && (reviewDate.length > 0))
      {
        for (int i = 0; i < reviewDate.length; i++) {
          CompInitialization beanList = new CompInitialization();
          if (!bean.getParaId().equals(i)) {
            beanList.setReviewDate(reviewDate[i]);
            beanList.setReviewWeightage(weightage[i]);
            totalWeightage += Integer.parseInt(weightage[i] != null ? weightage[i] : "0");
            tableList.add(beanList);
          }
        }

        bean.setReviewDateList(tableList);
        bean.setTotalreviewWeight(String.valueOf(totalWeightage));
      }
    }
    catch (Exception localException)
    {
    }
  }

  public String updateGoal(CompInitialization bean, String[] category, String[] categoryWeightage, HttpServletRequest request)
  {
    Object[][] addCompObj = new Object[1][16];
    addCompObj[0][0] = bean.getCompName();
    addCompObj[0][1] = bean.getCompfromDate();
    addCompObj[0][2] = bean.getComptoDate();
    addCompObj[0][3] = bean.getAppraisalCode();
    addCompObj[0][4] = bean.getReportingType();
    addCompObj[0][5] = bean.getIsSignOffRequired();
    addCompObj[0][6] = bean.getIsCategoryReq();
    addCompObj[0][7] = bean.getCompWeightage();
    addCompObj[0][8] = bean.getAppWeightage();
    addCompObj[0][9] = bean.getSelfAsstWeightage();
    addCompObj[0][10] = bean.getManagerAsstWeightage();
    addCompObj[0][11] = bean.getMultipleManagerRatingRadio();
    addCompObj[0][12] = bean.getIsEscalation();
    addCompObj[0][13] = bean.getEscalationMailId();
    addCompObj[0][14] = bean.getHiddenConfRating();
    addCompObj[0][15] = bean.getCompId();
    String query = "UPDATE HRMS_COMP_CONFIG SET COMP_NAME=?, COMP_FROM_DATE=TO_DATE(?,'DD-MM-YYYY'), COMP_TO_DATE=TO_DATE(?,'DD-MM-YYYY'),APPRAISAL_CODE=?,COMP_REPORTING=?, COMP_SIGNOFF_WORKFLOW=DECODE(?,'true','Y','false','N'),IS_COMP_CATEGORY=DECODE(?,'true','Y','false','N'), COMP_RATING_WEIGHTAGE=?,APPRAISAL_RATING_WEIGHTAGE=?, COMP_SELF_WEIGHTAGE=?, COMP_MANAGER_WEIGHTAGE=?, MULTIPLE_RATING_OPTION=?,COMP_ESCALATION_WORKFLOW=DECODE(?,'true','Y','false','N'), COMP_ESCALATION_EMAIL=?,COMP_RATING_FLAG=? WHERE COMP_ID=? ";

    boolean result = getSqlModel().singleExecute(query, addCompObj);
    if (result) {
      String queryDel = "DELETE FROM HRMS_COMP_CATEGORIES WHERE COMP_ID= " + bean.getCompId();
      boolean result1 = getSqlModel().singleExecute(queryDel);
      if ((result1) && 
        (category != null)) {
        result1 = addCategory(bean, category, categoryWeightage);
      }
      saveReviewDates(bean, request);
      data(bean, request);
      return "modified";
    }
    return "Not saved";
  }

  public boolean addCategory(CompInitialization cadMaster, String[] category, String[] categoryWeightage)
  {
    boolean result = false;

    Object[][] addObj = new Object[1][2];
    if (category != null) {
      for (int i = 0; i < category.length; i++) {
        addObj[0][0] = cadMaster.getCompId();
        addObj[0][1] = category[i];

        String query = " INSERT INTO HRMS_COMP_CATEGORIES (COMP_ID,COMP_CATEGORY,COMP_CATEGORY_ID) VALUES ( ? ,? ,(SELECT NVL(MAX(COMP_CATEGORY_ID),0)+1 FROM HRMS_COMP_CATEGORIES))";
        result = getSqlModel().singleExecute(query, addObj);
      }
    }
    return result;
  }

  public boolean updatePublishStatus(String goalId, String status) {
    String updateQuery = "UPDATE HRMS_COMP_CONFIG SET COMP_PUBLISH_STATUS = " + 
      status + " WHERE COMP_ID = " + goalId;

    return getSqlModel().singleExecute(updateQuery);
  }

  public boolean saveReviewDates(CompInitialization bean, HttpServletRequest request)
  {
    boolean result = false;
    String[] reviewDates = request.getParameterValues("reviewDate");
    String[] reviewAssessmentWeight = request.getParameterValues("reviewWeightage");

    result = getSqlModel().singleExecute(
      "DELETE FROM HRMS_COMP_ASSESSMENT_CONFIG WHERE COMP_ID =" + 
      bean.getCompId());
    int maxGoalDtlId = 1;
    if (result) {
      Object[][] maxGoalDtlObj = getSqlModel()
        .getSingleResult(
        "SELECT NVL(MAX(COMP_ASSESSMENT_ID),0)+1 FROM HRMS_COMP_ASSESSMENT_CONFIG");
      maxGoalDtlId = 
        Integer.parseInt(String.valueOf(maxGoalDtlObj[0][0]));
    }
    String insertDtlQuery = "INSERT INTO HRMS_COMP_ASSESSMENT_CONFIG(COMP_ID, COMP_ASSESSMENT_ID, COMP_ASSESSMENT_DATE,COMP_ASSESSMENT_WEIGTAGE) VALUES(?,?,TO_DATE(?,'DD-MM-YYYY'),?)";

    Object[][] insertDtlObj = new Object[reviewDates.length][4];
    for (int i = 0; i < insertDtlObj.length; i++) {
      insertDtlObj[i][0] = bean.getCompId();
      insertDtlObj[i][1] = Integer.valueOf(maxGoalDtlId + i);
      insertDtlObj[i][2] = reviewDates[i];
      insertDtlObj[i][3] = reviewAssessmentWeight[i];
    }
    if (result) {
      result = getSqlModel().singleExecute(insertDtlQuery, insertDtlObj);
    }

    return result;
  }

  public void setData(String autoCompCode, CompInitialization compInitialization)
  {
    try
    {
      String query = " SELECT COMP_ID, COMP_NAME, TO_CHAR(COMP_FROM_DATE,'DD-MM-YYYY') ,TO_CHAR(COMP_TO_DATE,'DD-MM-YYYY') , COMP_PUBLISH_STATUS ,APPRAISAL_CODE,APPR_CAL_CODE,NVL(COMP_REPORTING,'reporting'), DECODE(IS_COMP_CATEGORY,'Y','true','N','false','false'),  DECODE(COMP_SIGNOFF_WORKFLOW,'Y','true','N','false','false'),DECODE(COMP_ESCALATION_WORKFLOW,'Y','true','N','false'), COMP_ESCALATION_EMAIL,DECODE(NVL(MULTIPLE_RATING_OPTION,'F'),'F','true','A','false'),COMP_RATING_FLAG FROM HRMS_COMP_CONFIG LEFT JOIN  PAS_APPR_CALENDAR ON (PAS_APPR_CALENDAR.APPR_ID=HRMS_COMP_CONFIG.APPRAISAL_CODE) \t WHERE COMP_ID=" + 
        autoCompCode;
      Object[][] obj = getSqlModel().getSingleResult(query);
      if ((obj != null) && (obj.length > 0)) {
        compInitialization.setCompId(String.valueOf(obj[0][0]));
        compInitialization.setCompName(String.valueOf(obj[0][1]));
        compInitialization.setCompfromDate(String.valueOf(obj[0][2]));
        compInitialization.setComptoDate(String.valueOf(obj[0][3]));
        compInitialization.setCompPublishStatus(String.valueOf(obj[0][4]));
        compInitialization.setAppraisalCode(checkNull(String.valueOf(obj[0][5])));
        compInitialization.setAppraisalName(checkNull(String.valueOf(obj[0][6])));
        compInitialization.setReportingType(checkNull(String.valueOf(obj[0][7])));
        compInitialization.setIsCategoryReq(checkNull(String.valueOf(obj[0][8])));

        compInitialization.setIsSignOffRequired(checkNull(String.valueOf(obj[0][9])));

        compInitialization.setIsEscalation(checkNull(String.valueOf(obj[0][10])));
        compInitialization.setEscalationMailId(checkNull(String.valueOf(obj[0][11])));
        compInitialization.setMultipleManagerRatingRadio(checkNull(String.valueOf(obj[0][12])));
        compInitialization.setHiddenConfRating(checkNull(String.valueOf(obj[0][13])));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public boolean deleteGoal(String compId)
  {
    boolean res = getSqlModel().singleExecute(
      "DELETE FROM HRMS_COMP_CATEGORIES WHERE COMP_ID IN(" + compId + 
      ")");
    if (res) {
      res = getSqlModel().singleExecute(
        "DELETE FROM HRMS_COMP_CONFIG WHERE COMP_ID IN(" + compId + 
        ")");
      if (res) {
        res = getSqlModel().singleExecute(
          "DELETE FROM HRMS_COMP_ASSESSMENT_CONFIG WHERE COMP_ID IN (" + 
          compId + ")");
      }
    }
    return res;
  }

  public void addItem(CompInitialization goalInt, String[] srNo, String[] category, String[] categoryWeightage, int check)
  {
    ArrayList tableList = new ArrayList();

    if (srNo != null) {
      for (int i = 0; i < srNo.length; i++) {
        CompInitialization goalInit = new CompInitialization();
        if (goalInt.getParaId().equals(String.valueOf(i + 1))) {
          goalInit.setSrNo(String.valueOf(i + 1));
          goalInit.setCategoryIt(goalInt.getCategory1());
        }
        else
        {
          goalInit.setSrNo(String.valueOf(i + 1));
          goalInit.setCategoryIt(category[i]);
        }

        tableList.add(goalInit);
      }
    }

    if ((check == 1) && 
      (goalInt.getParaId().equals(""))) {
      CompInitialization goalInit = new CompInitialization();
      goalInit.setCategoryIt(goalInt.getCategory1());

      tableList.add(goalInit);
    }

    goalInt.setList(tableList);
  }

  public void getDuplicate(CompInitialization goalInt, String[] srNo, String[] category, String[] categoryWeightage, int check)
  {
    ArrayList tableList = new ArrayList();
    if (srNo != null)
    {
      for (int i = 0; i < srNo.length; i++) {
        CompInitialization bean = new CompInitialization();
        bean.setSrNo(String.valueOf(i + 1));
        bean.setCategoryIt(category[i]);

        tableList.add(bean);
      }
      goalInt.setList(tableList);
    }
  }

  public void categoryData(CompInitialization goalInit)
  {
    try {
      String query = " SELECT COMP_CATEGORY,nvl(COMP_CATEGORY_WEIGHTAGE,0) FROM HRMS_COMP_CATEGORIES WHERE COMP_ID=" + 
        goalInit.getCompId();
      Object[][] obj = getSqlModel().getSingleResult(query);

      ArrayList list = new ArrayList();
      int totalCatWeightage = 0;
      for (int i = 0; i < obj.length; i++) {
        CompInitialization bean1 = new CompInitialization();
        bean1.setCategoryIt(String.valueOf(obj[i][0]));
        bean1.setSrNo(String.valueOf(i + 1));

        list.add(bean1);
      }
      goalInit.setList(list);

      if (list.size() == 0)
        goalInit.setTableLength("0");
      else
        goalInit.setTableLength("1");
    }
    catch (Exception localException) {
    }
  }

  public void goalRatingDetails(CompInitialization bean) {
    try {
      System.out.println("======COMP ID========" + bean.getCompId());
      CompInitialization bean1 = new CompInitialization();
      String query = " SELECT COMP_RATING_WEIGHTAGE,APPRAISAL_RATING_WEIGHTAGE,COMP_SELF_WEIGHTAGE, COMP_MANAGER_WEIGHTAGE, DECODE(NVL(MULTIPLE_RATING_OPTION,'F'),'F','true','A','false') FROM HRMS_COMP_CONFIG   LEFT JOIN  PAS_APPR_CALENDAR ON (PAS_APPR_CALENDAR.APPR_ID=HRMS_COMP_CONFIG.APPRAISAL_CODE) \t WHERE COMP_ID=" + 
        bean.getCompId();
      Object[][] obj = getSqlModel().getSingleResult(query);
      if ((obj != null) && (obj.length > 0)) {
        bean.setCompWeightage(checkNull(String.valueOf(obj[0][0])));
        bean.setAppWeightage(checkNull(String.valueOf(obj[0][1])));
        bean.setSelfAsstWeightage(checkNull(String.valueOf(obj[0][2])));
        bean.setManagerAsstWeightage(checkNull(String.valueOf(obj[0][3])));
        bean.setMultipleManagerRatingRadio(checkNull(String.valueOf(obj[0][4])));
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void removeGoals(CompInitialization bean, String[] cat, String[] srNo, String[] categoryWeightage, HttpServletRequest request)
  {
    ArrayList goalList = new ArrayList();
    try
    {
      if ((srNo != null) || (srNo.length > 0)) {
        for (int i = 0; i < cat.length; i++) {
          CompInitialization goalInit = new CompInitialization();
          if (!bean.getParaId().equals(String.valueOf(i + 1))) {
            goalInit.setSrNo(String.valueOf(i + 1));
            goalInit.setCategoryIt(cat[i]);

            goalList.add(goalInit);
          }
        }
      }

      bean.setList(goalList);
    }
    catch (Exception localException)
    {
    }
  }

  public void getCalculatedRatingsByGoalId(CompInitialization goalInitialization, String goalId)
  {
    String query = "SELECT NVL(COMP_RATING_WEIGHTAGE,0) AS COMP_WT,NVL(COMP_SELF_WEIGHTAGE,0) AS SELF_WT,NVL(COMP_MANAGER_WEIGHTAGE,0) AS MGR_WT , NVL(MULTIPLE_RATING_OPTION,0) AS AVG_RT , NVL(APPRAISAL_RATING_WEIGHTAGE,0) AS APPR_WT ,APPRAISAL_CODE ,NVL(COMP_RATING_RANGE_UPTO,5),COMP_RATING_FLAG FROM HRMS_COMP_CONFIG WHERE COMP_ID = " + 
      goalId;
    Object[][] compObj = getSqlModel().getSingleResult(query);
    if ((compObj != null) && (compObj.length > 0)) {
      double GOAL_WGT = Double.parseDouble(String.valueOf(compObj[0][0]));

      double SELF_WGT = Double.parseDouble(String.valueOf(compObj[0][1]));

      double MGR_WGT = Double.parseDouble(String.valueOf(compObj[0][2]));

      double APPR_WGT = Double.parseDouble(String.valueOf(compObj[0][4]));
      String IS_AVG = String.valueOf(compObj[0][3]);

      int REPORTING_LEVEL = 1;
      int EMP_SELF_QUEST = 0;

      String apprisalCode = String.valueOf(compObj[0][5]);

      String IS_CONFG_RATING = String.valueOf(compObj[0][7]);

      String apprQuery = "SELECT HRMS_EMP_OFFC.EMP_Id,APPR_FINAL_SCORE FROM PAS_APPR_SCORE  \tINNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_SCORE.EMP_ID ) \tWHERE APPR_ID =" + 
        apprisalCode + "  ORDER BY EMP_ID ";
      Object[][] apprScore = getSqlModel().getSingleResult(apprQuery);
      String empQuery = "SELECT DISTINCT EMP_ID,nvl(COMP_ASSESSER_LEVEL,0) FROM HRMS_COMP_EMP_ASSESSMENT_HDR WHERE COMP_ID =" + 
        goalId;
      Object[][] empObj = getSqlModel().getSingleResult(empQuery);
      double APPR_SCORE = 0.0D;
      if ((empObj != null) && (empObj.length > 0))
        for (int j = 0; j < empObj.length; j++) {
          double EMP_FINAL_RATING = 0.0D;
          REPORTING_LEVEL = Integer.parseInt(String.valueOf(empObj[j][1]));
          String empRating = "";
          APPR_SCORE = 0.0D;
          double empSelfRatingTotal = 0.0D;
          if ((apprScore != null) && (apprScore.length > 0)) {
            for (int i = 0; i < apprScore.length; i++) {
              if (String.valueOf(apprScore[i][0]).equals(String.valueOf(empObj[j][0]))) {
                APPR_SCORE = Double.parseDouble(String.valueOf(apprScore[i][1]));
              }
            }

          }

          String assessmentIdSql = "SELECT DISTINCT HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID FROM HRMS_COMP_EMP_ASSESSMENT_DTL INNER JOIN HRMS_COMP_EMP_ASSESSMENT_HDR ON (HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID) WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = " + 
            goalId + " ";
          Object[][] assessmentIdObj = getSqlModel().getSingleResult(assessmentIdSql);
          double mgrSelfRatingTotal = 0.0D;
          if ((assessmentIdObj != null) && (assessmentIdObj.length > 0))
          {
            for (int k = 0; k < assessmentIdObj.length; k++)
            {
              empRating = "SELECT DISTINCT NVL(COMP_RATING,0),NVL(COMPETENCY_WEIGHTAGE,0),HRMS_COMPETENCY_ROLE.COMPETENCY_CODE,COMPETENCY_LEVEL FROM HRMS_COMP_SELF_ASSESMENT INNER JOIN HRMS_EMP_OFFC ON (HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID=HRMS_EMP_OFFC.EMP_ID) INNER JOIN HRMS_COMP_EMP_ASSESSMENT_HDR ON HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID =HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID INNER JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMP_SELF_ASSESMENT.COMP_ID = HRMS_COMPETENCY_ROLE.COMPETENCY_CODE AND HRMS_EMP_OFFC.EMP_RANK=HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE) WHERE  COMP_EMP_ID = " + 
                empObj[j][0] + " AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID= " + goalId + " ORDER BY HRMS_COMPETENCY_ROLE.COMPETENCY_CODE";

              Object[][] ratingObj = getSqlModel().getSingleResult(
                empRating);

              double empSelfRating = 0.0D;

              empSelfRatingTotal = 0.0D;
              double RATING = 5.0D;

              double ratingDtl = 0.0D;

              if ((ratingObj != null) && (ratingObj.length > 0)) {
                for (int i = 0; i < ratingObj.length; i++)
                {
                  RATING = Double.parseDouble(String.valueOf(ratingObj[i][3]));
                  ratingDtl = Double.parseDouble(
                    String.valueOf(ratingObj[i][0])) / RATING * 100.0D;

                  ratingDtl = ratingDtl * (
                    Double.parseDouble(
                    String.valueOf(ratingObj[i][1])) / 100.0D);

                  empSelfRating += ratingDtl;
                }

                System.out.println("TOTAL EMP SELF RATING : " + 
                  empSelfRating);

                empSelfRatingTotal = empSelfRating * SELF_WGT / 100.0D;
              }

              System.out.println("TOTAL EMP SELF RATING OUT 20% : " + 
                empSelfRatingTotal);

              mgrSelfRatingTotal = 0.0D;
              String mgrRating = "SELECT NVL(COMP_RATING,0),NVL(COMP_COMMENT,''),CASE WHEN COMP_ASSESER_ID=2 THEN 'true' ELSE 'false' END ISACCESSER ,COMP_ASSESSMENT_ID FROM HRMS_COMP_EMP_ASSESSMENT_DTL WHERE COMP_ASSESSMENT_ID = " + assessmentIdObj[k][0] + " AND COMP_EMP_ID=" + empObj[j][0] + " ORDER BY COMPETENCY_CODE";

              Object[][] mrgObj = getSqlModel().getSingleResult(mgrRating);

              double mgrRatingDtl = 0.0D;
              double secMgrRatingDtl = 0.0D;
              double mgrSelfRating = 0.0D;
              if ((mrgObj != null) && (mrgObj.length > 0)) {
                if (REPORTING_LEVEL == 1) {
                  for (int i = 0; i < ratingObj.length; i++) {
                    RATING = Double.parseDouble(String.valueOf(ratingObj[i][3]));
                    mgrRatingDtl = Double.parseDouble(
                      String.valueOf(mrgObj[i][0])) / RATING * 100.0D;
                    if ((ratingObj != null) && (ratingObj.length > 0))
                    {
                      mgrRatingDtl = mgrRatingDtl * (
                        Double.parseDouble(
                        String.valueOf(ratingObj[i][1])) / 100.0D);
                    }

                    mgrSelfRating += mgrRatingDtl;
                    System.out.println("mgrRatingDtl : " + mgrRatingDtl);
                  }

                }
                else if (IS_AVG.equals("A")) {
                  int count = 0;
                  for (int i = 0; i < mrgObj.length / 2; i++) {
                    RATING = Double.parseDouble(String.valueOf(ratingObj[i][3]));
                    mgrRatingDtl = (Double.parseDouble(
                      String.valueOf(mrgObj[(count++)][0])) + Double.parseDouble(
                      String.valueOf(mrgObj[(count++)][0]))) / (RATING * 2.0D) * 100.0D;
                    System.out.println("mgrRatingDtl : AVG :   " + mgrRatingDtl + "String.valueOf(ratingObj[i][1]) :: " + String.valueOf(ratingObj[i][1]));
                    if ((ratingObj != null) && (ratingObj.length > 0))
                    {
                      mgrRatingDtl = mgrRatingDtl * (
                        Double.parseDouble(
                        String.valueOf(ratingObj[i][1])) / 100.0D);
                    }

                    mgrSelfRating += mgrRatingDtl;
                    System.out.println("mgrRatingDtl : " + mgrRatingDtl);
                  }
                }
                else {
                  int count = 0;
                  for (int i = 0; i < mrgObj.length / 2; i++) {
                    RATING = Double.parseDouble(String.valueOf(ratingObj[i][3]));
                    mgrRatingDtl = Double.parseDouble(
                      String.valueOf(mrgObj[(count++)][0])) / RATING * 100.0D;
                    if ((ratingObj != null) && (ratingObj.length > 0))
                    {
                      mgrRatingDtl = mgrRatingDtl * (
                        Double.parseDouble(
                        String.valueOf(ratingObj[i][1])) / 100.0D);
                    }

                    mgrSelfRating += mgrRatingDtl;
                    System.out.println("mgrRatingDtl : " + mgrRatingDtl);

                    mgrSelfRating += mgrRatingDtl;
                    count++;
                  }

                }

                System.out.println("TOTAL MGR RATING : " + 
                  mgrSelfRating);
                mgrSelfRatingTotal = mgrSelfRating * MGR_WGT / 100.0D;

                System.out.println("Total of Each Assessment : " + (empSelfRatingTotal + mgrSelfRatingTotal));
                String assementWeigthtageQuery = "SELECT NVL(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_WEIGTAGE,0) FROM HRMS_COMP_EMP_ASSESSMENT_HDR LEFT JOIN HRMS_COMP_ASSESSMENT_CONFIG ON(HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID=HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID AND HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID=" + goalId + ") WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID=" + assessmentIdObj[k][0] + " AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID=" + String.valueOf(empObj[j][0]);
                Object[][] assessmentWeightObj = getSqlModel().getSingleResult(
                  assementWeigthtageQuery);
                if ((assessmentWeightObj != null) && (assessmentWeightObj.length > 0))
                {
                  EMP_FINAL_RATING += (empSelfRatingTotal + mgrSelfRatingTotal) * Double.parseDouble(String.valueOf(assessmentWeightObj[0][0])) / 100.0D;
                }

              }

              System.out.println("TOTAL MGR RATING IN 80%  : " + 
                mgrSelfRatingTotal);
              System.out.println("Total Final Rating : " + EMP_FINAL_RATING);

              Object[][] updateObj = new Object[1][6];
              updateObj[0][0] = Double.valueOf(empSelfRatingTotal);
              updateObj[0][1] = Double.valueOf(mgrSelfRatingTotal);

              updateObj[0][2] = Double.valueOf(empSelfRatingTotal + mgrSelfRatingTotal);
              updateObj[0][3] = goalId;
              updateObj[0][4] = String.valueOf(empObj[j][0]);
              updateObj[0][5] = String.valueOf(assessmentIdObj[k][0]);
              System.out.println("updateObj[0][3] :" + updateObj[0][3]);
              System.out.println("updateObj[0][4] :" + updateObj[0][4]);
              String updateQuery = "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET COMP_REVIEW_SELF_RATING=?, COMP_REVIEW_MGR_RATING=?, COMP_REVIEW_FINAL_RATING=? WHERE COMP_ID=? AND EMP_ID=? AND COMP_ASSESSMENT_ID = ?";
              getSqlModel().singleExecute(updateQuery, updateObj);
            }

          }

          System.out.println("mgrSelfRatingTotal ::::: " + mgrSelfRatingTotal);
          Object[][] updateObj = new Object[1][3];

          double goalWgt = 0.0D;
          if (IS_CONFG_RATING.equals("Y"))
          {
            if (EMP_FINAL_RATING > 100.0D)
            {
              EMP_FINAL_RATING = 100.0D;
            }
          }

          goalWgt = EMP_FINAL_RATING;
          updateObj[0][0] = Double.valueOf(goalWgt);
          updateObj[0][1] = String.valueOf(empObj[j][0]);
          updateObj[0][2] = goalId;

          String upGoal = "  UPDATE HRMS_COMP_EMP SET FINAL_SCORE=? WHERE COMP_EMP_ID = ? AND COMP_ID = ?";
          getSqlModel().singleExecute(upGoal, updateObj);
        }
    }
  }

  public Object[][] getNoOfCategoryQuestionSelf(String assessmentId, String categoryId)
  {
    Object[][] data = (Object[][])null;
    if (categoryId != null)
    {
      String query = "SELECT COUNT(*) AS NO_OF_CAT_QUESTION FROM HRMS_COMP_SELF_ASSESSMENT_DTL INNER JOIN HRMS_COMP_EMP_DTL ON (HRMS_COMP_SELF_ASSESSMENT_DTL.SELF_COMP_DTL_ID=HRMS_COMP_EMP_DTL.COMP_DTL_ID AND SELF_COMP_ASSESSMENT_ID =" + 
        assessmentId + ")  LEFT JOIN HRMS_COMP_CATEGORIES ON(COMP_CATEGORY_CODE=COMP_CATEGORY_ID) WHERE COMP_CATEGORY_CODE=" + categoryId;
      data = getSqlModel().getSingleResult(query);
    }

    return data;
  }

  public void generateReport(CompInitialization compInitialization, HttpServletResponse response, HttpServletRequest request, String compId, String reportPath)
  {
    try
    {
      ReportGenerator rg = null;
      String reportType = "XLS";
      String title = "Competency Score Details";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "Competency Score Details_" + Utility.getRandomNumber(1000);
      rds.setFileName(fileName);

      rds.setReportName(title);

      rds.setPageOrientation("landscape");
      rds.setUserEmpId(compInitialization.getUserEmpId());
      rds.setReportType(reportType);
      rds.setTotalColumns(8);

      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, this.session, this.context, request);
      } else {
        logger.info("################# ATTACHMENT PATH #############" + reportPath + fileName);
        rg = new ReportGenerator(rds, reportPath, this.session, this.context, request);

        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
        request.setAttribute("action", "CompetencyInitialization_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }

      String queryDes = "SELECT ROWNUM,EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), NVL(HRMS_DEPT.DEPT_NAME,' '), NVL(HRMS_CENTER.CENTER_NAME,''), NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_CADRE.CADRE_NAME,' '), NVL(TO_CHAR(FINAL_SCORE,9999999999990.99),'0')  FROM HRMS_COMP_EMP LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP.COMP_EMP_ID )  LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  LEFT JOIN HRMS_CADRE ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_CADRE.CADRE_ID)  LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID\t= HRMS_EMP_OFFC.EMP_DIV)  WHERE HRMS_COMP_EMP.COMP_ID = " + 
        compId;

      Object[][] data = getSqlModel().getSingleResult(queryDes);

      if ((data != null) && (data.length > 0)) {
        TableDataSet tdstable = new TableDataSet();
        tdstable.setHeader(new String[] { "Sr.No", "Employee Token", "Employee Name", 
          "Department", "Branch", "Designation", "Grade", "Final Score" });
        tdstable.setData(data);
        tdstable.setHeaderLines(true);
        tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
        tdstable.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 0, 0, 1 });
        tdstable.setCellWidth(new int[] { 7, 25, 25, 25, 25, 50, 25, 25 });
        tdstable.setHeaderBorderDetail(3);
        tdstable.setBorderDetail(3);
        tdstable.setBorder(Boolean.valueOf(true));
        tdstable.setBlankRowsAbove(1);
        tdstable.setHeaderTable(true);
        tdstable.setBlankRowsBelow(1);
        rg.addTableToDoc(tdstable);
      } else {
        TableDataSet noData = new TableDataSet();
        Object[][] noDataObj = new Object[1][1];
        noDataObj[0][0] = "No records available";

        noData.setData(noDataObj);
        noData.setCellAlignment(new int[] { 1 });
        noData.setCellWidth(new int[] { 100 });

        noData.setBorder(Boolean.valueOf(false));
        rg.addTableToDoc(noData);
      }

      rg.process();
      if (reportPath.equals("")) {
        rg.createReport(response);
      }
      else
      {
        rg.saveReport(response);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}