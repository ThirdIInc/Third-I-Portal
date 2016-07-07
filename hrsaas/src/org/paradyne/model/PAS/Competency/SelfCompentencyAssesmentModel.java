package org.paradyne.model.PAS.Competency;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.PAS.Competency.SelfCompentencyAssesment;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.model.PAS.GoalSetting.EmployeeGoalSettingModel;

public class SelfCompentencyAssesmentModel extends ModelBase
{
  static HashMap map;

  public void getPendingRecords(SelfCompentencyAssesment selfBean, HttpServletRequest request)
  {
    try
    {
      String pendingRecordsQuery = " SELECT HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID, HRMS_COMP_CONFIG.COMP_ID, HRMS_COMP_CONFIG.COMP_NAME, TO_CHAR(HRMS_COMP_CONFIG.COMP_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(HRMS_COMP_CONFIG.COMP_TO_DATE,'DD-MM-YYYY'),  HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_STATUS, TO_CHAR(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE,'DD-MM-YYYY')  FROM HRMS_COMP_CONFIG  INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID)  LEFT JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID AND HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID = " + 
        selfBean.getUserEmpId() + ") " + 
        " WHERE HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE <=SYSDATE  AND NVL(HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_STATUS,'N') !='Y' " + 
        " AND HRMS_COMP_CONFIG.COMP_PUBLISH_STATUS = '2'";
      Object[][] pendingRecordsObj = getSqlModel().getSingleResult(pendingRecordsQuery);
      List pendingList = new ArrayList();
      String[] pageIndexDrafted = Utility.doPaging(selfBean.getMyPendingPage(), pendingRecordsObj.length, 20);
      if (pageIndexDrafted == null) {
        pageIndexDrafted[0] = "0";
        pageIndexDrafted[1] = "20";
        pageIndexDrafted[2] = "1";
        pageIndexDrafted[3] = "1";
        pageIndexDrafted[4] = "";
      }

      request.setAttribute("totalPendingPage", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndexDrafted[2]))));
      request.setAttribute("pendingPageNo", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndexDrafted[3]))));
      if (pageIndexDrafted[4].equals("1")) {
        selfBean.setMyPendingPage("1");
      }

      if ((pendingRecordsObj != null) && (pendingRecordsObj.length > 0)) {
        selfBean.setPendingRecordListFlag(true);
        for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
          SelfCompentencyAssesment beanItt = new SelfCompentencyAssesment();
          beanItt.setCompetencyAssementIDItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][0])));
          beanItt.setCompetencyIDItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][1])));
          beanItt.setCompetencyNameItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][2])));
          beanItt.setCompetencyFromDate(Utility.checkNull(String.valueOf(pendingRecordsObj[i][3])));
          beanItt.setCompetencyToDate(Utility.checkNull(String.valueOf(pendingRecordsObj[i][4])));
          beanItt.setCompetencyReviewDateItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][6])));
          pendingList.add(beanItt);
        }
        selfBean.setPendingRecordList(pendingList);
      } else {
        selfBean.setPendingListEmptyFlag(true);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getCompetencyDetails(SelfCompentencyAssesment selfBean, String competencyIDItr, String competencyAssementIDItr, String competencyNameItr)
  {
    try
    {
      if (competencyAssementIDItr != null) {
        selfBean.setCompetencyAssementID(competencyAssementIDItr);
      }

      if (competencyNameItr != null) {
        selfBean.setHiddenCompetencyName(competencyNameItr);
      }

      if (competencyIDItr != null) {
        selfBean.setMasterCompetencyCode(competencyIDItr);
      }

      String designationQuery = "SELECT HRMS_EMP_OFFC.EMP_RANK FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID = " + selfBean.getUserEmpId();
      Object[][] designationObj = getSqlModel().getSingleResult(designationQuery);
      if ((designationObj != null) && (designationObj.length > 0)) {
        selfBean.setCompetencyEmpDesignation(Utility.checkNull(String.valueOf(designationObj[0][0])));
      }

      String checkForCurrentUserDataQuery = "SELECT HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID, HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID, HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID, HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ROLE, HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_DATE, HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_STATUS  FROM HRMS_COMP_ASSESMENT_HDR WHERE HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID = " + 
        competencyIDItr + 
        " AND HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = " + competencyAssementIDItr + 
        " AND HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID = " + selfBean.getUserEmpId();
      Object[][] checkForCurrentUserDataObj = getSqlModel().getSingleResult(checkForCurrentUserDataQuery);
      if ((checkForCurrentUserDataObj == null) || (checkForCurrentUserDataObj.length == 0)) {
        Object[][] insertCurrentUserData = new Object[1][5];
        insertCurrentUserData[0][0] = competencyIDItr;
        insertCurrentUserData[0][1] = competencyAssementIDItr;
        insertCurrentUserData[0][2] = selfBean.getUserEmpId();
        insertCurrentUserData[0][3] = selfBean.getCompetencyEmpDesignation();
        insertCurrentUserData[0][4] = "N";

        String insertCurrentUserDataQuery = "INSERT INTO HRMS_COMP_ASSESMENT_HDR (HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID, HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID, HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID, HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ROLE, HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_DATE, HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_STATUS)  VALUES (?,?,?,?,SYSDATE,?) ";

        boolean result = getSqlModel().singleExecute(
          "INSERT INTO HRMS_COMP_ASSESMENT_HDR (HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID, HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID, HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID, HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ROLE, HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_DATE, HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_STATUS)  VALUES (?,?,?,?,SYSDATE,?) ", insertCurrentUserData);

        String compDet = "SELECT COMP_EMP_ID, COMP_ID FROM HRMS_COMP_EMP WHERE HRMS_COMP_EMP.COMP_EMP_ID=" + selfBean.getUserEmpId() + 
          " AND HRMS_COMP_EMP.COMP_ID=" + competencyIDItr;
        Object[][] compDetObj = getSqlModel().getSingleResult(compDet);
        if ((compDetObj != null) && (compDetObj.length == 0)) {
          String compDetInsertQuery = "INSERT INTO HRMS_COMP_EMP (COMP_EMP_ID, COMP_ID)  VALUES (?,? )";

          Object[][] compDetInsertObj = new Object[1][2];
          compDetInsertObj[0][0] = String.valueOf(selfBean.getUserEmpId());
          compDetInsertObj[0][1] = competencyIDItr;
          getSqlModel().singleExecute(compDetInsertQuery, 
            compDetInsertObj);
        }

        if (result)
        {
          String competencySelectQuery = " SELECT HRMS_COMPETENCY_HDR.COMPETENCY_CODE, HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL,HRMS_COMPETENCY_ROLE.COMPETENCY_SEQUENCE FROM HRMS_COMPETENCY_HDR  INNER JOIN HRMS_COMPETENCY_DTL ON HRMS_COMPETENCY_DTL.COMPETENCY_CODE=HRMS_COMPETENCY_HDR.COMPETENCY_CODE  INNER JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMPETENCY_DTL.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE AND HRMS_COMPETENCY_DTL.COMPETENCY_LEVEL=HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL)  WHERE HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE= " + 
            selfBean.getCompetencyEmpDesignation() + 
            " ORDER BY HRMS_COMPETENCY_ROLE.COMPETENCY_SEQUENCE";
          Object[][] competencySelectObj = getSqlModel().getSingleResult(competencySelectQuery);
          String competencyInsertQuery = "";
          if ((competencySelectObj != null) && (competencySelectObj.length > 0)) {
            Object[][] competencyInsertObj = new Object[competencySelectObj.length][3];
            for (int i = 0; i < competencySelectObj.length; i++) {
              competencyInsertObj[i][0] = Utility.checkNull(String.valueOf(competencySelectObj[i][0]));
              competencyInsertObj[i][1] = Utility.checkNull(String.valueOf(competencySelectObj[i][1]));
              competencyInsertObj[i][2] = Utility.checkNull(String.valueOf(competencySelectObj[i][2]));

              competencyInsertQuery = "INSERT INTO HRMS_COMP_SELF_ASSESMENT (HRMS_COMP_SELF_ASSESMENT.COMP_ID, HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL,  HRMS_COMP_SELF_ASSESMENT.COMP_SEQUENCE, HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID, HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID)  VALUES (?,?,?," + 
                competencyAssementIDItr + "," + selfBean.getUserEmpId() + " )";
            }
            getSqlModel().singleExecute(competencyInsertQuery, 
              competencyInsertObj);
          }
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object[][] getSelfCompetencyCode(HttpServletRequest request, SelfCompentencyAssesment selfBean, String competencyAssesmentIDItr)
  {
    Object[][] cometencyIDFromMap = (Object[][])null;
    try {
      String query = " SELECT ROWNUM, HRMS_COMP_SELF_ASSESMENT.COMP_ID FROM HRMS_COMP_SELF_ASSESMENT  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID= " + 
        competencyAssesmentIDItr + 
        " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + selfBean.getUserEmpId();
      map = getSqlModel().getSingleResultMap(query, 0, 2);
      cometencyIDFromMap = (Object[][])map.get("1");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return cometencyIDFromMap;
  }

  public void competencyTitleDropDown(HttpServletRequest request, SelfCompentencyAssesment selfBean, String competencyAssesmentIDItr)
  {
    try
    {
      String query = " SELECT ROWNUM ||'-'|| HRMS_COMP_SELF_ASSESMENT.COMP_ID, HRMS_COMPETENCY_HDR.COMPETENCY_TITLE FROM HRMS_COMP_SELF_ASSESMENT  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)   WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID= " + 
        competencyAssesmentIDItr + 
        " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + selfBean.getUserEmpId() + 
        " ORDER BY HRMS_COMP_SELF_ASSESMENT.COMP_ID ";
      Object[][] selfCompTitleObj = getSqlModel().getSingleResult(query);
      if ((selfCompTitleObj != null) && (selfCompTitleObj.length > 0)) {
        request.setAttribute("dropDownTitle", selfCompTitleObj);
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void getCompetencyHeaderAndLevel(HttpServletRequest request, SelfCompentencyAssesment selfBean, String competencyAssesmentIDItr, String competencyCode)
  {
    try
    {
      String headerDataQuery = "SELECT HRMS_COMPETENCY_HDR.COMPETENCY_TITLE, HRMS_COMPETENCY_HDR.COMPETENCY_DESC, HRMS_COMPETENCY_HDR.COMPETENCY_INDICATOR FROM HRMS_COMPETENCY_HDR  WHERE HRMS_COMPETENCY_HDR.COMPETENCY_CODE=" + 
        competencyCode;
      Object[][] headerDataObj = getSqlModel().getSingleResult(headerDataQuery);
      if ((headerDataObj != null) && (headerDataObj.length > 0)) {
        selfBean.setCompetencyName(Utility.checkNull(String.valueOf(headerDataObj[0][0])));
        selfBean.setCompetencyDescription(Utility.checkNull(String.valueOf(headerDataObj[0][1])));
        selfBean.setCompetencyIndicator(Utility.checkNull(String.valueOf(headerDataObj[0][2])));
      }

      String detailCompetencyDataQuery = "SELECT DISTINCT HRMS_COMPETENCY_DTL.COMPETENCY_CODE,  HRMS_COMPETENCY_DTL.COMPETENCY_TITLE, HRMS_COMPETENCY_DTL.COMPETENCY_LEVEL_DESC,HRMS_COMPETENCY_DTL.COMPETENCY_LEVEL , CASE WHEN HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL IS NULL THEN 'N' ELSE 'Y' END FROM HRMS_COMPETENCY_DTL LEFT JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMPETENCY_DTL.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE AND HRMS_COMPETENCY_DTL.COMPETENCY_LEVEL=HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL AND HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE = (SELECT HRMS_EMP_OFFC.EMP_RANK FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID = " + 
        selfBean.getUserEmpId() + ")) " + 
        "WHERE HRMS_COMPETENCY_DTL.COMPETENCY_CODE= " + competencyCode + " ORDER BY HRMS_COMPETENCY_DTL.COMPETENCY_LEVEL";
      Object[][] detailCompetencyDataObj = getSqlModel().getSingleResult(detailCompetencyDataQuery);
      if ((detailCompetencyDataObj != null) && (detailCompetencyDataObj.length > 0))
        request.setAttribute("detailCompetencyDataObj", 
          detailCompetencyDataObj);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean updateCompetencyDetails(SelfCompentencyAssesment selfBean)
  {
    boolean result = false;
    Object[][] updateCompetencyObj = new Object[1][5];
    updateCompetencyObj[0][0] = selfBean.getSelfRating();
    updateCompetencyObj[0][1] = selfBean.getSelfComments();
    updateCompetencyObj[0][2] = selfBean.getCompetencyID();
    updateCompetencyObj[0][3] = selfBean.getCompetencyAssementID();
    updateCompetencyObj[0][4] = selfBean.getUserEmpId();

    String updateCompetencyDetailsQuery = "UPDATE HRMS_COMP_SELF_ASSESMENT SET HRMS_COMP_SELF_ASSESMENT.COMP_RATING = ?, HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS = ? WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ID= ? AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID= ?  AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = ? ";

    result = getSqlModel().singleExecute("UPDATE HRMS_COMP_SELF_ASSESMENT SET HRMS_COMP_SELF_ASSESMENT.COMP_RATING = ?, HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS = ? WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ID= ? AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID= ?  AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = ? ", updateCompetencyObj);
    try {
      Object[][] obj = returnCompetencyInfo(selfBean, selfBean.getCompetencyKey());
      if (obj != null)
        selfBean.setCompetencyID(String.valueOf(obj[0][1]));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public void getRatingAndCommentsIfAlreadyGiven(SelfCompentencyAssesment selfBean, String competencyAssementIDItr, String competencyID)
  {
    try
    {
      String ratingCommentsQuery = "SELECT HRMS_COMP_SELF_ASSESMENT.COMP_RATING, HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS FROM HRMS_COMP_SELF_ASSESMENT  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ID = " + 
        competencyID + 
        " AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = " + competencyAssementIDItr + 
        " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + selfBean.getUserEmpId();
      Object[][] ratingCommentsObj = getSqlModel().getSingleResult(ratingCommentsQuery);
      if ((ratingCommentsObj != null) && (ratingCommentsObj.length > 0)) {
        selfBean.setSelfRating(Utility.checkNull(String.valueOf(ratingCommentsObj[0][0])));
        selfBean.setSelfComments(Utility.checkNull(String.valueOf(ratingCommentsObj[0][1])));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getAllRatingsAndComments(SelfCompentencyAssesment selfBean, String competencyAssementIDItr, HttpServletRequest request)
  {
    try
    {
      String assesmentStatusQuery = "SELECT HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_STATUS FROM HRMS_COMP_ASSESMENT_HDR WHERE HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = " + competencyAssementIDItr + 
        " AND HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID = " + selfBean.getUserEmpId();
      Object[][] assesmentStatusObj = getSqlModel().getSingleResult(assesmentStatusQuery);
      if ((assesmentStatusObj != null) && (assesmentStatusObj.length > 0) && 
        (String.valueOf(assesmentStatusObj[0][0]).trim().equals("Y"))) {
        selfBean.setFinalizedFlag(true);
      }

      String allRatingCommetsQuery = "SELECT HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL, HRMS_COMP_SELF_ASSESMENT.COMP_RATING, HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS, HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_STATUS, HRMS_COMP_SELF_ASSESMENT.COMP_ID, HRMS_COMPETENCY_HDR.COMPETENCY_TITLE FROM HRMS_COMP_SELF_ASSESMENT  INNER JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID) WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = " + 
        competencyAssementIDItr + 
        " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + selfBean.getUserEmpId();
      Object[][] allRatingCommentsObj = getSqlModel().getSingleResult(allRatingCommetsQuery);
      if ((allRatingCommentsObj != null) && (allRatingCommentsObj.length > 0)) {
        List allRatingCommentsList = new ArrayList();
        for (int i = 0; i < allRatingCommentsObj.length; i++) {
          SelfCompentencyAssesment beanItr = new SelfCompentencyAssesment();
          beanItr.setProficiencyLevelFinalizeItr(Utility.checkNull(String.valueOf(allRatingCommentsObj[i][0])));
          beanItr.setRatingFinalizeItr(Utility.checkNull(String.valueOf(allRatingCommentsObj[i][1])));
          beanItr.setCommentsFinalizeItr(Utility.checkNull(String.valueOf(allRatingCommentsObj[i][2])));
          if (Utility.checkNull(String.valueOf(allRatingCommentsObj[i][3])).equals("Y")) {
            beanItr.setFinalizedFlag(true);
          }
          beanItr.setCompetencyIDFinalizeItr(Utility.checkNull(String.valueOf(allRatingCommentsObj[i][4])));
          beanItr.setCompetencyNameFinalizeItr(Utility.checkNull(String.valueOf(allRatingCommentsObj[i][5])));
          allRatingCommentsList.add(beanItr);
        }
        selfBean.setAllRatingAndCommentsIterator(allRatingCommentsList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getApproverRatingsAndCommentsInProcessedList(SelfCompentencyAssesment selfBean, String competencyAssementIDItr, HttpServletRequest request, String applicantCode)
  {
    try
    {
      String competencyQuery = "SELECT HRMS_COMP_SELF_ASSESMENT.COMP_ID, ROWNUM, HRMS_COMPETENCY_HDR.COMPETENCY_TITLE , HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL,  HRMS_COMP_SELF_ASSESMENT.COMP_RATING, HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS FROM HRMS_COMP_SELF_ASSESMENT   INNER JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = " + 
        competencyAssementIDItr + 
        " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + applicantCode;
      Object[][] noOfCompObj = getSqlModel().getSingleResult(competencyQuery);
      HashMap competencyMap = getSqlModel().getSingleResultMap(competencyQuery, 0, 2);

      String approverQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE,HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING,HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT,NVL(COMP_DEV_PLAN,' ') , TO_CHAR(COMP_DEV_TARGET_DATE,'DD-MM-YYYY')  FROM HRMS_COMP_EMP_ASSESSMENT_DTL  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID)    LEFT JOIN HRMS_COMPETENCY_DEV_PLAN ON(HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_DEV_CODE=HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE)  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        competencyAssementIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + applicantCode + 
        " ORDER BY HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE DESC";

      HashMap approverMap = getSqlModel().getSingleResultMap(approverQuery, 0, 2);

      String numberOfApproverQuery = " SELECT DISTINCT HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID  FROM HRMS_COMP_EMP_ASSESSMENT_DTL   WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        competencyAssementIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + applicantCode;
      Object[][] numberOfApproverObj = getSqlModel().getSingleResult(numberOfApproverQuery);

      int totalRows = 0;

      int totalapprovers = 0;
      if ((noOfCompObj != null) && (noOfCompObj.length > 0)) {
        selfBean.setProcessedPageApproverRatingCommentsFlag(true);
        totalRows = noOfCompObj.length;
      }
      if ((numberOfApproverObj != null) && (numberOfApproverObj.length > 0)) {
        totalapprovers = numberOfApproverObj.length;
      }

      Object[][] compObj = new Object[totalRows][5];

      Object[][] approverObj = new Object[totalRows][totalapprovers * 4];

      String[][] headerObj = new String[totalRows][5 + totalapprovers * 4];
      String[][] detailObj = new String[totalRows][5 + totalapprovers * 4];
      int chkcnt = 0;
      int hdrchkcnt = 0;
      if ((noOfCompObj != null) && (noOfCompObj.length > 0)) {
        headerObj[0][hdrchkcnt] = "Sr. No.";
        hdrchkcnt++;
        headerObj[0][hdrchkcnt] = "Competency";
        hdrchkcnt++;
        headerObj[0][hdrchkcnt] = "Proficiency Level";
        hdrchkcnt++;
        headerObj[0][hdrchkcnt] = "";
        hdrchkcnt++;
        headerObj[0][hdrchkcnt] = "Employee";
        hdrchkcnt++;

        detailObj[0][chkcnt] = "";
        chkcnt++;
        detailObj[0][chkcnt] = "";
        chkcnt++;
        detailObj[0][chkcnt] = "";
        chkcnt++;
        detailObj[0][chkcnt] = "Rating";
        chkcnt++;
        detailObj[0][chkcnt] = "Comments";
        chkcnt++;
        String queryApp = "SELECT DISTINCT EMP_FNAME||'  ' || EMP_LNAME  FROM HRMS_COMP_EMP_ASSESSMENT_DTL   INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID)  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
          competencyAssementIDItr + "  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + applicantCode;
        Object[][] totApproverObj = getSqlModel().getSingleResult(queryApp);
        int totApplength = totApproverObj.length;

        for (int a = 0; a < totApplength; a++) {
          for (int b = 0; b < 4; b++) {
            if (b == 0) {
              detailObj[0][chkcnt] = "Rating";
              chkcnt++;
              headerObj[0][hdrchkcnt] = " ";
              hdrchkcnt++;
            }

            if (b == 1) {
              headerObj[0][hdrchkcnt] = " ";
              hdrchkcnt++;
              detailObj[0][chkcnt] = "Comments";
              chkcnt++;
            }

            if (b == 2) {
              headerObj[0][hdrchkcnt] = ("Approver" + (a + 1) + " -" + String.valueOf(totApproverObj[a][0]));
              hdrchkcnt++;
              detailObj[0][chkcnt] = "Development Plan";
              chkcnt++;
            }

            if (b == 3) {
              detailObj[0][chkcnt] = "Target Date";
              chkcnt++;
              headerObj[0][hdrchkcnt] = " ";
              hdrchkcnt++;
            }
          }
        }
      }

      if ((noOfCompObj != null) && (noOfCompObj.length > 0)) {
        for (int i = 0; i < noOfCompObj.length; i++)
        {
          Object[][] innerCompObj = (Object[][])null;
          try {
            innerCompObj = (Object[][])competencyMap.get(String.valueOf(noOfCompObj[i][0]));
            if ((innerCompObj != null) && (innerCompObj.length > 0))
              for (int k = 1; k < innerCompObj[0].length; k++)
                compObj[i][(k - 1)] = String.valueOf(innerCompObj[0][k]);
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }

          Object[][] innerObj = (Object[][])null;
          try {
            innerObj = (Object[][])approverMap.get(String.valueOf(noOfCompObj[i][0]));
            if ((innerObj != null) && (innerObj.length > 0)) {
              int count = 0;
              int noOfAppr = 1;
              for (int j = 0; j < innerObj.length; j++)
                for (int j2 = 1; j2 < innerObj[0].length; j2++) {
                  approverObj[i][count] = String.valueOf(innerObj[j][j2]);

                  count++;
                }
            }
          }
          catch (Exception e) {
            e.printStackTrace();
          }

        }

        Object[][] finalObj = (Object[][])null;
        if ((compObj != null) && (compObj.length > 0)) {
          finalObj = compObj;
        }
        if ((approverObj != null) && (approverObj.length > 0)) {
          finalObj = approverObj;
        }
        if ((compObj != null) && (compObj.length > 0) && (approverObj != null) && (approverObj.length > 0)) {
          finalObj = Utility.joinArrays(compObj, approverObj, 0, 0);
        }

        System.out.println("headerDataObj length" + headerObj.length);
        System.out.println("approverDataObj length" + finalObj.length);

        request.setAttribute("headerDataObj", headerObj);
        request.setAttribute("detailObj", detailObj);
        request.setAttribute("approverDataObj", finalObj);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getCurrentStausFromSaveAndNextPage(SelfCompentencyAssesment selfBean, String competencyAssementID, String competencyID)
  {
    try
    {
      Object[][] nextKeyAndIdObj = returnCompetencyInfo(selfBean, selfBean.getCompetencyKey());
      if (String.valueOf(nextKeyAndIdObj[0][0]).equals(String.valueOf(nextKeyAndIdObj[0][2]))) {
        selfBean.setFinalPageFlag(true);
        selfBean.setNonFinalPageFlag(false);
        selfBean.setFinalizeSaveAndPreviousPageFlag(true);
        selfBean.setBackToListPageFlag(true);
      } else {
        selfBean.setFinalPageFlag(false);
        selfBean.setNonFinalPageFlag(true);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean finalizeCompetencyDetails(SelfCompentencyAssesment selfBean, String[] competencyIDFinalizeItr, String[] ratingFinalizeItr, String[] commentsFinalizeItr, Object[][] empFlow)
  {
    boolean result = false;
    try {
      if (competencyIDFinalizeItr != null) {
        Object[][] finalUpdateData = new Object[competencyIDFinalizeItr.length][5];
        for (int i = 0; i < competencyIDFinalizeItr.length; i++) {
          finalUpdateData[i][0] = ratingFinalizeItr[i];
          finalUpdateData[i][1] = commentsFinalizeItr[i];
          finalUpdateData[i][2] = competencyIDFinalizeItr[i];
          finalUpdateData[i][3] = selfBean.getCompetencyAssementID();
          finalUpdateData[i][4] = selfBean.getUserEmpId();
          String updateCompetencyDetailsQuery = "UPDATE HRMS_COMP_SELF_ASSESMENT SET HRMS_COMP_SELF_ASSESMENT.COMP_RATING = ? , HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS = ? , HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_STATUS  = 'Y'  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ID= ?  AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID= ? AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = ? ";

          result = getSqlModel().singleExecute("UPDATE HRMS_COMP_SELF_ASSESMENT SET HRMS_COMP_SELF_ASSESMENT.COMP_RATING = ? , HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS = ? , HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_STATUS  = 'Y'  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ID= ?  AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID= ? AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = ? ", finalUpdateData);
        }
        if (result) {
          String updateHeaderStatusQuery = "UPDATE HRMS_COMP_ASSESMENT_HDR SET HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_STATUS = 'Y' WHERE HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID=" + selfBean.getCompetencyAssementID() + 
            " AND COMP_EMP_ID=" + selfBean.getUserEmpId();
          getSqlModel().singleExecute(updateHeaderStatusQuery);
          selfBean.setFinalPageFlag(true);
          selfBean.setNonFinalPageFlag(false);
        }

        if (result) {
          Object[][] insertObj = new Object[1][4];
          insertObj[0][0] = selfBean.getMasterCompetencyCode();
          insertObj[0][1] = selfBean.getUserEmpId();
          insertObj[0][2] = selfBean.getCompetencyAssementID();
          insertObj[0][3] = ((empFlow != null) && (empFlow.length > 0) ? empFlow[0][0] : "0");
          String insertHeaderQuery = "INSERT INTO HRMS_COMP_EMP_ASSESSMENT_HDR (HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_DATE, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL) VALUES (?,?,?,?,SYSDATE, 'P', 1)";
          getSqlModel().singleExecute("INSERT INTO HRMS_COMP_EMP_ASSESSMENT_HDR (HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_DATE, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL) VALUES (?,?,?,?,SYSDATE, 'P', 1)", insertObj);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public void getProcessedRecords(SelfCompentencyAssesment selfBean, HttpServletRequest request)
  {
    try
    {
      String approvedRecordsQuery = "SELECT HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID, HRMS_COMP_CONFIG.COMP_ID, HRMS_COMP_CONFIG.COMP_NAME, TO_CHAR(HRMS_COMP_CONFIG.COMP_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(HRMS_COMP_CONFIG.COMP_TO_DATE,'DD-MM-YYYY'),  HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_STATUS, TO_CHAR(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE,'DD-MM-YYYY')  FROM HRMS_COMP_CONFIG  INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID)  LEFT JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID AND HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID = " + 
        selfBean.getUserEmpId() + ") " + 
        " WHERE HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE <=SYSDATE  AND NVL(HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_STATUS,'N') ='Y' " + 
        " AND HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID = " + selfBean.getUserEmpId();
      Object[][] approvedRecordsObj = getSqlModel().getSingleResult(approvedRecordsQuery);
      List processList = new ArrayList();
      String[] pageIndexDrafted = Utility.doPaging(selfBean.getMyProcessedPage(), approvedRecordsObj.length, 20);
      if (pageIndexDrafted == null) {
        pageIndexDrafted[0] = "0";
        pageIndexDrafted[1] = "20";
        pageIndexDrafted[2] = "1";
        pageIndexDrafted[3] = "1";
        pageIndexDrafted[4] = "";
      }

      request.setAttribute("totalProcessedPage", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndexDrafted[2]))));
      request.setAttribute("processedPageNo", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndexDrafted[3]))));
      if (pageIndexDrafted[4].equals("1")) {
        selfBean.setMyProcessedPage("1");
      }

      if ((approvedRecordsObj != null) && (approvedRecordsObj.length > 0)) {
        selfBean.setProcessedRecordListFlag(true);
        for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
          SelfCompentencyAssesment beanItt = new SelfCompentencyAssesment();
          beanItt.setCompetencyAssementIDItr(Utility.checkNull(String.valueOf(approvedRecordsObj[i][0])));
          beanItt.setCompetencyIDItr(Utility.checkNull(String.valueOf(approvedRecordsObj[i][1])));
          beanItt.setCompetencyNameItr(Utility.checkNull(String.valueOf(approvedRecordsObj[i][2])));
          beanItt.setCompetencyFromDate(Utility.checkNull(String.valueOf(approvedRecordsObj[i][3])));
          beanItt.setCompetencyToDate(Utility.checkNull(String.valueOf(approvedRecordsObj[i][4])));
          beanItt.setCompetencyReviewDateItr(Utility.checkNull(String.valueOf(approvedRecordsObj[i][6])));
          processList.add(beanItt);
        }
        selfBean.setProcessedRecordList(processList);
      } else {
        selfBean.setProcessListEmptyFlag(true);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean updateAllRatingsAndComments(SelfCompentencyAssesment selfBean, String[] competencyIDFinalizeItr, String[] ratingFinalizeItr, String[] commentsFinalizeItr)
  {
    boolean result = false;
    try {
      if (competencyIDFinalizeItr != null) {
        Object[][] updateData = new Object[competencyIDFinalizeItr.length][5];
        for (int i = 0; i < competencyIDFinalizeItr.length; i++) {
          updateData[i][0] = ratingFinalizeItr[i];
          updateData[i][1] = commentsFinalizeItr[i];
          updateData[i][2] = competencyIDFinalizeItr[i];
          updateData[i][3] = selfBean.getCompetencyAssementID();
          updateData[i][4] = selfBean.getUserEmpId();
          String updateCompetencyDetailsQuery = "UPDATE HRMS_COMP_SELF_ASSESMENT SET HRMS_COMP_SELF_ASSESMENT.COMP_RATING = ? , HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS = ?, HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_STATUS  = 'Y' WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ID= ?   AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = ?   AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = ? ";

          result = getSqlModel().singleExecute("UPDATE HRMS_COMP_SELF_ASSESMENT SET HRMS_COMP_SELF_ASSESMENT.COMP_RATING = ? , HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS = ?, HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_STATUS  = 'Y' WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ID= ?   AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = ?   AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = ? ", updateData);
        }
        if (result) {
          selfBean.setFinalPageFlag(false);
          selfBean.setNonFinalPageFlag(true);
        }

      }

      Object[][] nextKeyAndIdObj = returnCompetencyInfo(selfBean, selfBean.getCompetencyKey());
      if (nextKeyAndIdObj != null)
        selfBean.setCompetencyID(String.valueOf(nextKeyAndIdObj[0][1]));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public void getPreviousApproverRatingsAndComments(SelfCompentencyAssesment selfBean, String competencyAssesmentID, String competencyAssesmentEmployeeID)
  {
    try
    {
      EmployeeGoalSettingModel goalSettingModel = new EmployeeGoalSettingModel();
      goalSettingModel.initiate(this.context, this.session);
      String previousApprRatingCommetsQuery = " SELECT HRMS_COMPETENCY_HDR.COMPETENCY_TITLE, HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL,  HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT  FROM HRMS_COMP_EMP_ASSESSMENT_DTL  INNER JOIN HRMS_COMP_SELF_ASSESMENT ON (HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE)  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE)    INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID)  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        competencyAssesmentID + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + competencyAssesmentEmployeeID;
      Object[][] approverRatingCommentsObj = getSqlModel().getSingleResult(previousApprRatingCommetsQuery);
      if ((approverRatingCommentsObj != null) && (approverRatingCommentsObj.length > 0)) {
        List previousApprRatingCommentsList = new ArrayList();
        selfBean.setApproverRatingCommentsPageFlag(true);
        for (int i = 0; i < approverRatingCommentsObj.length; i++) {
          SelfCompentencyAssesment beanItr = new SelfCompentencyAssesment();
          beanItr.setApproverCompetencyNameItr(Utility.checkNull(String.valueOf(approverRatingCommentsObj[i][0])));
          beanItr.setApproverProficiencyLevelItr(Utility.checkNull(String.valueOf(approverRatingCommentsObj[i][1])));
          beanItr.setApproverNameItr(Utility.checkNull(String.valueOf(approverRatingCommentsObj[i][2])));
          beanItr.setApproverRatingItr(Utility.checkNull(String.valueOf(approverRatingCommentsObj[i][3])));
          beanItr.setApproverCommentsItr(Utility.checkNull(String.valueOf(approverRatingCommentsObj[i][4])));
          previousApprRatingCommentsList.add(beanItr);
        }
        selfBean.setApproverRatingCommentsIterator(previousApprRatingCommentsList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getEmployeeDetails(SelfCompentencyAssesment selfBean, String applicantCode)
  {
    try
    {
      String empQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  NVL(HRMS_CENTER.CENTER_NAME,' '), NVL(HRMS_DEPT.DEPT_NAME,'  '), NVL(HRMS_RANK.RANK_NAME,'  '),  (AS2.TITLE_NAME||' '||AS1.EMP_FNAME||' '||NVL(AS1.EMP_MNAME,'')|| ' ' || AS1.EMP_LNAME)  FROM HRMS_EMP_OFFC    LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)    LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)    LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)    LEFT JOIN HRMS_EMP_OFFC AS1 ON (HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = AS1.EMP_ID)  LEFT JOIN HRMS_TITLE AS2  ON(AS1.EMP_TITLE_CODE = AS2.TITLE_CODE)    WHERE HRMS_EMP_OFFC.EMP_ID = " + 
        applicantCode;
      Object[][] empObjects = getSqlModel().getSingleResult(empQuery);
      if ((empObjects != null) && (empObjects.length > 0)) {
        selfBean.setEmpId(Utility.checkNull(String.valueOf(empObjects[0][0])));
        selfBean.setEmpToken(Utility.checkNull(String.valueOf(empObjects[0][1])));
        selfBean.setEmpName(Utility.checkNull(String.valueOf(empObjects[0][2])));
        selfBean.setEmpBranch(Utility.checkNull(String.valueOf(empObjects[0][3])));
        selfBean.setEmpDepartment(Utility.checkNull(String.valueOf(empObjects[0][4])));
        selfBean.setEmpDesignation(Utility.checkNull(String.valueOf(empObjects[0][5])));
        selfBean.setEmpReportingTo(Utility.checkNull(String.valueOf(empObjects[0][6])));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getReviewDetailsTableData(SelfCompentencyAssesment selfBean, String competencyAssementID, String applicantCode)
  {
    try
    {
      String escalationAndSignWorkflowQuery = "SELECT HRMS_COMP_CONFIG.COMP_SIGNOFF_WORKFLOW, HRMS_COMP_CONFIG.COMP_ESCALATION_WORKFLOW FROM HRMS_COMP_CONFIG WHERE HRMS_COMP_CONFIG.COMP_ID = " + selfBean.getMasterCompetencyCode();
      Object[][] escalationAndSignWorkflowObj = getSqlModel().getSingleResult(escalationAndSignWorkflowQuery);
      String signOffWorkFlow = "";
      String escalationWorkFlow = "";
      if ((escalationAndSignWorkflowObj != null) && (escalationAndSignWorkflowObj.length > 0)) {
        signOffWorkFlow = Utility.checkNull(String.valueOf(escalationAndSignWorkflowObj[0][0]));
        escalationWorkFlow = Utility.checkNull(String.valueOf(escalationAndSignWorkflowObj[0][1]));
      }

      if ((!"N".equals(signOffWorkFlow)) || (!"N".equals(escalationWorkFlow))) {
        String reviewQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_COMMENTS, HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_SIGNOFF_ACCEPTANCE  FROM HRMS_COMP_ASSESSMENT_APPR_DTL  INNER JOIN HRMS_COMP_EMP_ASSESSMENT_HDR ON (HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ID AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ASSESSMENT_ID AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_ID)  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_APPROVER_ID)  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_IS_SIGNOFF = 'Y' AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ASSESSMENT_ID= " + 
          competencyAssementID + 
          " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_ID = " + applicantCode;
        Object[][] reviewObj = getSqlModel().getSingleResult(reviewQuery);
        if ((reviewObj != null) && (reviewObj.length > 0))
        {
          int count = 0;
          String checkReAssessedDataQuery = "SELECT HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_REASSESSMENT_STATUS FROM HRMS_COMP_ASSESSMENT_APPR_DTL  WHERE HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ID= " + 
            selfBean.getMasterCompetencyCode() + 
            " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ASSESSMENT_ID= " + competencyAssementID + 
            " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_ID = " + applicantCode + 
            " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_SIGNOFF_ACCEPTANCE = 'N'";

          Object[][] checkReAssessedDataObj = getSqlModel().getSingleResult(checkReAssessedDataQuery);
          if ((checkReAssessedDataObj != null) || (checkReAssessedDataObj.length > 0)) {
            for (int i = 0; i < checkReAssessedDataObj.length; i++) {
              if (String.valueOf(checkReAssessedDataObj[i][0]).equals("Y")) {
                count++;
              }

            }

          }

          selfBean.setSignOffEscalationWorkFlowONFLAG(true);
          List reviewDataList = new ArrayList();
          for (int i = 0; i < reviewObj.length; i++) {
            SelfCompentencyAssesment innerBean = new SelfCompentencyAssesment();
            innerBean.setReviewApproverIDItr(Utility.checkNull(String.valueOf(reviewObj[i][0])));
            innerBean.setReviewApproverTokenItr(Utility.checkNull(String.valueOf(reviewObj[i][1])));
            innerBean.setReviewApproverNameItr(Utility.checkNull(String.valueOf(reviewObj[i][2])));
            innerBean.setReviewCommentsItr(Utility.checkNull(String.valueOf(reviewObj[i][3])));
            innerBean.setReviewAgreeDisAgreeFlag(Utility.checkNull(String.valueOf(reviewObj[i][4])));

            if (("null".equals(String.valueOf(reviewObj[i][3]))) || ("null".equals(String.valueOf(reviewObj[i][4])))) {
              innerBean.setAllReadyProcessedAgreeDisAgreeFlag(false);
              selfBean.setSignOffAndEscalationWorkflowFlag(true);
            }
            else if (count > 0) {
              if (count == checkReAssessedDataObj.length) {
                innerBean.setAllReadyProcessedAgreeDisAgreeFlag(false);
                selfBean.setAfterReAssesedFlag(true);
                selfBean.setSignOffWorkflowFlag(false);
                selfBean.setSignOffAndEscalationWorkflowFlag(false);
              } else {
                innerBean.setAllReadyProcessedAgreeDisAgreeFlag(true);
                selfBean.setSignOffAndEscalationWorkflowFlag(false);
              }
            } else {
              innerBean.setAllReadyProcessedAgreeDisAgreeFlag(true);
              selfBean.setSignOffAndEscalationWorkflowFlag(false);
            }

            reviewDataList.add(innerBean);
            if (innerBean.isAllReadyProcessedAgreeDisAgreeFlag()) {
              selfBean.setSignOffWorkflowFlag(false);
              selfBean.setSignOffAndEscalationWorkflowFlag(false);
            }
          }
          selfBean.setReviewEmployeeIterator(reviewDataList);
        } else {
          selfBean.setSignOffAndEscalationWorkflowFlag(false);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void checkForSignOffEscalationWorkFlow(SelfCompentencyAssesment selfBean, String applicantCode)
  {
    try
    {
      String escalationAndSignWorkflowQuery = "SELECT HRMS_COMP_CONFIG.COMP_SIGNOFF_WORKFLOW, HRMS_COMP_CONFIG.COMP_ESCALATION_WORKFLOW  FROM HRMS_COMP_CONFIG WHERE HRMS_COMP_CONFIG.COMP_ID = " + 
        selfBean.getMasterCompetencyCode();
      Object[][] escalationAndSignWorkflowObj = getSqlModel().getSingleResult(escalationAndSignWorkflowQuery);
      String signOffString = "";
      String escalationString = "";
      if ((escalationAndSignWorkflowObj != null) && (escalationAndSignWorkflowObj.length > 0)) {
        signOffString = Utility.checkNull(String.valueOf(escalationAndSignWorkflowObj[0][0]));
        escalationString = Utility.checkNull(String.valueOf(escalationAndSignWorkflowObj[0][1]));
        if ((signOffString.equals("Y")) && (escalationString.equals("N"))) {
          String checkSignOffStatusQuery = "SELECT COMP_ASSESSMENT_STATUS FROM HRMS_COMP_EMP_ASSESSMENT_HDR WHERE COMP_ID= " + selfBean.getMasterCompetencyCode() + 
            " AND EMP_ID= " + applicantCode + " AND COMP_ASSESSMENT_ID = " + selfBean.getCompetencyAssementID();
          Object[][] checkSignOffStatusObj = getSqlModel().getSingleResult(checkSignOffStatusQuery);
          if ((checkSignOffStatusObj != null) && (checkSignOffStatusObj.length > 0)) {
            if (String.valueOf(checkSignOffStatusObj[0][0]).equals("A"))
              selfBean.setSignOffWorkflowFlag(true);
            else {
              selfBean.setSignOffWorkflowFlag(false);
            }
          }
        }

        if ((signOffString.equals("Y")) && (escalationString.equals("Y"))) {
          selfBean.setSignOffAndEscalationWorkflowFlag(true);
        }

      }

      String acceptanceFlagQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_SIGNOFF_ACCEPTANCE FROM HRMS_COMP_EMP_ASSESSMENT_HDR  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID= " + 
        selfBean.getMasterCompetencyCode() + 
        " AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID= " + applicantCode + 
        " AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID=" + selfBean.getCompetencyAssementID();
      Object[][] acceptanceObj = getSqlModel().getSingleResult(acceptanceFlagQuery);
      if ((acceptanceObj != null) && (acceptanceObj.length > 0) && (
        (Utility.checkNull(String.valueOf(acceptanceObj[0][0])).equals("A")) || 
        (Utility.checkNull(String.valueOf(acceptanceObj[0][0])).equals("R")))) {
        selfBean.setSignOffWorkflowFlag(false);
        selfBean.setSignOffAndEscalationWorkflowFlag(false);
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public boolean updateSignOffStatus(SelfCompentencyAssesment selfBean, String statusToUpdate)
  {
    boolean result = false;
    try {
      String updateQuery = "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_SIGNOFF_ACCEPTANCE = '" + statusToUpdate + "'" + 
        " WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = " + selfBean.getCompetencyAssementID() + " and HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = " + selfBean.getUserEmpId();
      result = getSqlModel().singleExecute(updateQuery);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public boolean updateRecordForReviewer(SelfCompentencyAssesment selfBean, String[] reviewApproverIDItr, String[] reviewAgreeDisAgreeFlag, String[] reviewCommentsItr)
  {
    boolean result = false;
    try {
      if (reviewApproverIDItr != null) {
        String updateRecordQuery = "";
        Object[][] updateRecordObj = new Object[reviewApproverIDItr.length][3];
        for (int i = 0; i < reviewApproverIDItr.length; i++) {
          updateRecordObj[i][0] = reviewCommentsItr[i];
          updateRecordObj[i][1] = reviewAgreeDisAgreeFlag[i];
          updateRecordObj[i][2] = reviewApproverIDItr[i];
          updateRecordQuery = "UPDATE HRMS_COMP_ASSESSMENT_APPR_DTL SET HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_COMMENTS = ?, HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_SIGNOFF_ACCEPTANCE = ?, HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_REASSESSMENT_STATUS = 'N' WHERE HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_APPROVER_ID = ?";
        }
        result = getSqlModel().singleExecute(updateRecordQuery, updateRecordObj);

        if (result) {
          String updateAssessHdrQuery = "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET COMP_REVIEWER_STATUS = 'N' WHERE COMP_ID = " + selfBean.getMasterCompetencyCode() + 
            " AND EMP_ID= " + selfBean.getUserEmpId() + 
            "AND COMP_ASSESSMENT_ID = " + selfBean.getCompetencyAssementID();
          result = getSqlModel().singleExecute(updateAssessHdrQuery);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public void setMappedCategory(SelfCompentencyAssesment selfBean, String competencyID)
  {
    try
    {
      String mappedDesignationQuery = "SELECT HRMS_COMP_CATEGORIES.COMP_CATEGORY FROM HRMS_COMPETENCY_ROLE  INNER JOIN HRMS_COMP_CATEGORIES ON (HRMS_COMP_CATEGORIES.COMP_CATEGORY_ID = HRMS_COMPETENCY_ROLE.COMPETENCY_TYPE)  WHERE HRMS_COMPETENCY_ROLE.COMPETENCY_CODE = " + 
        competencyID;
      Object[][] mappedDesignationObj = getSqlModel().getSingleResult(mappedDesignationQuery);
      if ((mappedDesignationObj != null) && (mappedDesignationObj.length > 0))
        selfBean.setMappedCategory(Utility.checkNull(String.valueOf(mappedDesignationObj[0][0])));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object[][] returnCompetencyInfo(SelfCompentencyAssesment selfBean, String competencyKey)
  {
    Object[][] result = (Object[][])null;
    Object[][] compQueryObj = getSqlModel().getSingleResult(" SELECT ROWNUM, HRMS_COMP_SELF_ASSESMENT.COMP_ID FROM HRMS_COMP_SELF_ASSESMENT  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID= " + 
      selfBean.getCompetencyAssementID() + 
      " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + selfBean.getUserEmpId());
    if ((compQueryObj != null) && (compQueryObj.length > 0)) {
      result = new Object[1][3];
      for (int i = 0; i < compQueryObj.length; i++) {
        if (competencyKey.equals(String.valueOf(i + 1))) {
          result[0][0] = String.valueOf(i + 1);
          result[0][1] = String.valueOf(compQueryObj[i][1]);
          result[0][2] = Integer.valueOf(compQueryObj.length);
        }
      }
    }
    return result;
  }
}