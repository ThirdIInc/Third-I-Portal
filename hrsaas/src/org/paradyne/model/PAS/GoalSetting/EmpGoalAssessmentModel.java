package org.paradyne.model.PAS.GoalSetting;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.EmpGoalAssessment;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;

public class EmpGoalAssessmentModel extends ModelBase
{
  static Logger logger = Logger.getLogger(EmpGoalAssessmentModel.class);
  HashMap<String, Object[][]> ratingData = null;

  public ArrayList<Object> getPendingGoalAssmtList(String userEmpId,EmpGoalAssessment bean, HttpServletRequest request) { ArrayList goalList = null;
  goalList = new ArrayList();  
  try {
      String query = " SELECT HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID,GOAL_NAME,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'),NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  '),EMP_GOAL_ASSESSMENT_ID,  NVL(GOAL_ASSESSER_LEVEL,1),NVL(GOAL_REPORTING,'reporting'),HRMS_GOAL_CONFIG.GOAL_ID as confgoalId FROM HRMS_GOAL_EMP_ASSESSMENT_HDR  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID  inner join HRMS_GOAL_EMP_HDR on(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID)  LEFT JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID  LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  WHERE GOAL_ASSESSMENT_STATUS = 5 AND GOAL_ASSESSER_ID = ?   and GOAL_ASSESSMENT_DATE <= SYSDATE AND GOAL_ASSESSER_ID = ?  ORDER BY GOAL_ID,GOAL_ASSESSMENT_DATE";

      Object[][] listData = getSqlModel().getSingleResult(query, new Object[] { userEmpId, userEmpId });
      String[] pageIndex = Utility.doPaging(bean.getMyPage(), listData.length, 20);
      if (pageIndex == null) {
        pageIndex[0] = "0";
        pageIndex[1] = "20";
        pageIndex[2] = "1";
        pageIndex[3] = "1";
        pageIndex[4] = "";
      }
      request.setAttribute("totalPage", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[2]))));
      request.setAttribute("pageNo", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[3]))));
      if (pageIndex[4].equals("1"))
        bean.setMyPage("1");
      if ((listData != null) && (listData.length != 0)) {
       
        for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
          EmpGoalAssessment bean1 = new EmpGoalAssessment();
          bean1.setGoalId(String.valueOf(listData[i][0]));
          bean1.setGoalName(String.valueOf(listData[i][1]));
          bean1.setEmpId(String.valueOf(listData[i][2]));
          bean1.setEmpName(String.valueOf(listData[i][3]));
          bean1.setScheduledAssessmentDate(String.valueOf(listData[i][4]));
          bean1.setEmpDept(String.valueOf(listData[i][5]));
          bean1.setEmpDesg(String.valueOf(listData[i][6]));
          bean1.setGoalAssessmentId(String.valueOf(listData[i][7]));
          bean1.setAssessLevelList(String.valueOf(listData[i][8]));
          bean1.setReportingTypeList(String.valueOf(listData[i][9]));
          bean1.setConfgoalId(String.valueOf(listData[i][10]));
          goalList.add(bean1);
        }
        bean.setDataList(goalList);
      }
    } catch (Exception e) {
      logger.error("Exception in getPendingGoalAssmtList " + e);
    }
    return goalList; }

  public void getProcessedList(EmpGoalAssessment bean, HttpServletRequest request)
  {
    ArrayList goalList = new ArrayList();
    try {
      String query = " SELECT DISTINCT HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID,GOAL_NAME,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'),TO_CHAR(ASSESSMENT_COMPLETED_DATE,'DD-MM-YYYY'),HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID,   NVL(GOAL_ASSESSER_LEVEL,1),NVL(GOAL_REPORTING,'reporting'),HRMS_GOAL_CONFIG.GOAL_ID as confgoalid FROM HRMS_GOAL_EMP_ASSESSMENT_HDR  INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON (HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID) INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID  INNER JOIN HRMS_GOAL_EMP_HDR on(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID)  LEFT JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID  LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  WHERE HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ASSESSER_ID = ?  ORDER BY GOAL_ID,TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY')";

      Object[][] listData = getSqlModel().getSingleResult(query, new Object[] { bean.getUserEmpId() });
      String[] pageIndex = Utility.doPaging(bean.getMyPage(), listData.length, 20);
      if (pageIndex == null) {
        pageIndex[0] = "0";
        pageIndex[1] = "20";
        pageIndex[2] = "1";
        pageIndex[3] = "1";
        pageIndex[4] = "";
      }
      request.setAttribute("totalPage1", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[2]))));
      request.setAttribute("pageNo1", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[3]))));
      if (pageIndex[4].equals("1"))
        bean.setMyPage("1");
      if ((listData != null) && (listData.length != 0)) {
        for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
          EmpGoalAssessment bean1 = new EmpGoalAssessment();
          bean1.setGoalId(String.valueOf(listData[i][0]));
          bean1.setGoalName(String.valueOf(listData[i][1]));
          bean1.setEmpId(String.valueOf(listData[i][2]));
          bean1.setEmpName(String.valueOf(listData[i][3]));
          bean1.setScheduledAssessmentDate(String.valueOf(listData[i][4]));
          bean1.setCompletedAssessmentDate(Utility.checkNull(String.valueOf(listData[i][5])));
          bean1.setGoalAssessmentId(String.valueOf(listData[i][6]));
          bean1.setAssessLevelList(String.valueOf(listData[i][7]));
          bean1.setReportingTypeList(String.valueOf(listData[i][8]));
          bean1.setConfgoalId(String.valueOf(listData[i][9]));
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

  public void getReassessmentList(EmpGoalAssessment bean, HttpServletRequest request) {
    ArrayList goalList = new ArrayList();
    try {
      String query = " SELECT DISTINCT HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID,GOAL_NAME,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'),TO_CHAR(ASSESSMENT_COMPLETED_DATE,'DD-MM-YYYY'),HRMS_GOAL_EMP_ASSESSMENT_HDR .EMP_GOAL_ASSESSMENT_ID,   NVL(GOAL_ASSESSER_LEVEL,1),NVL(GOAL_REPORTING,'reporting'),HRMS_GOAL_CONFIG.GOAL_ID as confgoalid,NVL(HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_LEVL,1) FROM HRMS_GOAL_EMP_ASSESSMENT_HDR INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON (HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID) INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID INNER JOIN HRMS_GOAL_EMP_HDR on(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) LEFT JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE) INNER JOIN HRMS_GOAL_ASSESSMENT_APPR_DTL on (HRMS_GOAL_ASSESSMENT_APPR_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID and HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_ID=HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ASSESSER_ID) WHERE HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ASSESSER_ID = ? AND HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_SIGNOFF_ACCEPTANCE='N'  AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEWER_STATUS='Y' AND GOAL_REASSESSMENT_STATUS = 'N' ORDER BY GOAL_ID,TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY')";

      Object[][] listData = getSqlModel().getSingleResult(query, new Object[] { bean.getUserEmpId() });
      String[] pageIndex = Utility.doPaging(bean.getMyPage(), listData.length, 20);
      if (pageIndex == null) {
        pageIndex[0] = "0";
        pageIndex[1] = "20";
        pageIndex[2] = "1";
        pageIndex[3] = "1";
        pageIndex[4] = "";
      }
      request.setAttribute("totalPage2", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[2]))));
      request.setAttribute("pageNo2", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[3]))));
      if (pageIndex[4].equals("1"))
        bean.setMyPage("1");
      if ((listData != null) && (listData.length != 0)) {
        for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
          EmpGoalAssessment bean1 = new EmpGoalAssessment();
          bean1.setGoalId(String.valueOf(listData[i][0]));
          bean1.setGoalName(String.valueOf(listData[i][1]));
          bean1.setEmpId(String.valueOf(listData[i][2]));
          bean1.setEmpName(String.valueOf(listData[i][3]));
          bean1.setScheduledAssessmentDate(String.valueOf(listData[i][4]));
          bean1.setCompletedAssessmentDate(Utility.checkNull(String.valueOf(listData[i][5])));
          bean1.setGoalAssessmentId(String.valueOf(listData[i][6]));
          bean1.setAssessLevelList(String.valueOf(listData[i][7]));
          bean1.setReportingTypeList(String.valueOf(listData[i][8]));
          bean1.setConfgoalId(String.valueOf(listData[i][9]));
          bean1.setHiddenlevel(String.valueOf(listData[0][10]));
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

  /**modified by @author prajakta.bhandare for substring functionality
 * @param goalId
 * @param empId
 * @param assmtId
 * @param bean
 * @param confgoalId
 * @return
 */
public ArrayList<Object> getSavedRating(String goalId, String empId, String assmtId, EmpGoalAssessment bean, String confgoalId) {
    ArrayList goalList = new ArrayList();
    try {
      System.out.println("Inside : getSavedRating : ");
      String query = "  SELECT HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ID ,GOAL_DESCRIPTION,TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY'),NVL(GOAL_RATING,''),NVL(HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_COMMENT,''),SELF_GOAL_RATING, SELF_GOAL_COMMENT , NVL(HRMS_GOAL_EMP_DTL.GOAL_COMMENTS,''),GOAL_WEIGHTAGE,NVL(GOAL_DTL_STATUS,'A'),GOAL_CATEGORY_CODE,NVL(GOAL_CATEGORY,' '),DECODE(IS_GOAL_ACHIEVED,'Y','YES','N','NO','O','On-Going','P','Partly Achieved'),NVL(HRMS_GOAL_CATEGORIES.GOAL_CATEGORY_WEIGHTAGE,0)  FROM HRMS_GOAL_EMP_ASSESSMENT_DTL INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND EMP_GOAL_ASSESSMENT_ID=" + 
        assmtId + " AND GOAL_DTL_ASSESSER_ID=" + bean.getUserEmpId() + ") " + 
        "  INNER JOIN HRMS_GOAL_SELF_ASSESSMENT_DTL ON  " + 
        " (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID and SELF_GOAL_ASSESSMENT_ID=" + assmtId + ") " + 
        " LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) ";

      Object[][] goalData = getSqlModel().getSingleResult(query);

      if ((goalData != null) && (goalData.length > 0)) {
        for (int i = 0; i < goalData.length; i++) {
          EmpGoalAssessment bean1 = new EmpGoalAssessment();
          bean1.setIndividualGoalId(Utility.checkNull(String.valueOf(goalData[i][0])));
          bean1.setGoalDescription(Utility.checkNull(String.valueOf(goalData[i][1])));
          if(bean1.getGoalDescription().length() > 15){
        	  bean1.setGoalDescriptionAbbr(bean1.getGoalDescription().substring(0, 14)+"...");
          }//end of if
          else{
        	  bean1.setGoalDescriptionAbbr(bean1.getGoalDescription());
          }//end of else
          bean1.setGoalAchDate(Utility.checkNull(String.valueOf(goalData[i][2])));
          bean1.setGoalRating(Utility.checkNull(String.valueOf(goalData[i][3])));
          bean1.setComment(Utility.checkNull(String.valueOf(goalData[i][4])));
         
          bean1.setSelfRating(Utility.checkNull(String.valueOf(goalData[i][5])));
          bean1.setSelfComments(Utility.checkNull(String.valueOf(goalData[i][6])));
          if(bean1.getSelfComments().length() > 15){
        	  bean1.setSelfCommentsAbbr(bean1.getSelfComments().substring(0, 14)+"...");
          }//end of if
          else{
        	  bean1.setSelfCommentsAbbr(bean1.getSelfComments());
          }//end of else
          bean1.setIsGoalCategory(bean.getIsGoalCategory());
          bean1.setIsGoalAchievedFlag(bean.getIsGoalAchievedFlag());
          bean1.setApplicantComments(String.valueOf(goalData[i][7]));
          if(bean1.getApplicantComments().length() > 15){
        	  bean1.setApplicantCommentsAbbr(bean1.getApplicantComments().substring(0, 14)+"...");
          }//end of if
          else{
        	  bean1.setApplicantCommentsAbbr(bean1.getApplicantComments());
          }//end of else
          ArrayList managerRatingList = setManagersRatingList(bean1, bean.getGoalAssessmentId(), Utility.checkNull(String.valueOf(goalData[i][0])), bean.getUserEmpId(), confgoalId);
          bean1.setManagerRatingList(managerRatingList);
          bean1.setWeightage(String.valueOf(goalData[i][8]));
          bean1.setGoalDtlStatus(checkNull(String.valueOf(goalData[i][9])) + "_");
          bean1.setGoalCategoryCodeList(Utility.checkNull(String.valueOf(goalData[i][10])));
          bean1.setGoalCategoryNameList(Utility.checkNull(String.valueOf(goalData[i][11])));
          if(bean1.getGoalCategoryNameList().length() > 15){
        	  bean1.setGoalCategoryNameListAbbr(bean1.getGoalCategoryNameList().substring(0, 14)+"...");
          }//end of if
          else{
        	  bean1.setGoalCategoryNameListAbbr(bean1.getGoalCategoryNameList());
          }//end of else
          bean1.setGoalAchievedList(Utility.checkNull(String.valueOf(goalData[i][12])));
          bean1.setGoalCategoryWeightage(Utility.checkNull(String.valueOf(goalData[i][13])));

          goalList.add(bean1);
        }
      }
      if (goalList.size() == 0) {
        goalList = getGoalDetails(goalId, empId, assmtId, bean, confgoalId);
        bean.setIsRecordSaved("false");
      }//end of if 
      else {
        bean.setIsRecordSaved("true");
      }//end of else
    } catch (Exception e) {
      logger.error("Exception in getGoalDetails " + e);
      e.printStackTrace();
      goalList = getGoalDetails(goalId, empId, assmtId, bean, confgoalId);
      bean.setIsRecordSaved("false");
    }
    return goalList;
  }

  /**modified by @author prajakta.bhandare for substring functionality
 * @param bean1
 * @param assessId
 * @param goalId
 * @param accessorId
 * @param confgoalId
 * @return
 */
public ArrayList setManagersRatingList(EmpGoalAssessment bean1, String assessId, String goalId, String accessorId, String confgoalId)
  {
    ArrayList tableList = new ArrayList();
    Object[][] goalRating = (Object[][])this.ratingData.get(goalId);
    TreeMap map = setRatingList(confgoalId);
    for (int i = 1; i <= map.size(); i++) {
      System.out.println("..." + map.get(Integer.valueOf(i)));
    }

    if ((goalRating != null) && (goalRating.length > 0))
    {
      for (int i = 0; i < goalRating.length; i++) {
        System.out.println("goalRating[" + i + "][0]" + goalRating[i][0]);
        System.out.println("goalRating[" + i + "][1]" + goalRating[i][1]);
        System.out.println("goalRating[" + i + "][2]" + goalRating[i][2]);
      }
    }

    if ((goalRating != null) && (goalRating.length > 0)) {
      for (int i = 0; i < goalRating.length; i++) {
        EmpGoalAssessment beanList = new EmpGoalAssessment();
        beanList.setManagerRating(Utility.checkNull(String.valueOf(goalRating[i][0])));
        beanList.setManagerComments(Utility.checkNull(String.valueOf(goalRating[i][1])));
        if(beanList.getManagerComments().length() > 15){
        	beanList.setManagerCommentsAbbr(beanList.getManagerComments().substring(0, 14)+"...");
        }//end of if
        else{
        	beanList.setManagerCommentsAbbr(beanList.getManagerComments());
        }//end of else
        beanList.setAccessorFlag(Utility.checkNull(String.valueOf(goalRating[i][2])));

        if (beanList.getAccessorFlag().equals("true"))
        {
          beanList.setHiddengoaldtlid(Utility.checkNull(String.valueOf(goalRating[i][5])));
        }
        beanList.setGoalRating(Utility.checkNull(String.valueOf(goalRating[i][0])));
        beanList.setComment(Utility.checkNull(String.valueOf(goalRating[i][1])));
        beanList.setManagerName(Utility.checkNull(String.valueOf(goalRating[i][4])));
        beanList.setTmap(map);
        tableList.add(beanList);
      }
    }

    return tableList;
  }
  public void setManagersRating(String assessId, String accessorId, String assessmentType) {
    try {
      String query = "";
      System.out.println("Inside : setManagersRating : assessmentType = " + assessmentType);
      if (assessmentType.equals("reassessment"))
        query = "SELECT NVL(GOAL_RATING,''),NVL(GOAL_COMMENT,''),CASE WHEN GOAL_DTL_ASSESSER_ID=" + 
          accessorId + 
          " and HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_SIGNOFF_ACCEPTANCE='N' " + 
          "THEN 'true' ELSE 'false' END ISACCESSER, " + 
          "GOAL_DTL_ID,EMP_FNAME||' '||EMP_LNAME,ASSESSMENT_DTL_ID FROM HRMS_GOAL_EMP_ASSESSMENT_DTL " + 
          "LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID) " + 
          "INNER JOIN HRMS_GOAL_ASSESSMENT_APPR_DTL on (HRMS_GOAL_ASSESSMENT_APPR_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID and " + 
          "HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_ID=HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ASSESSER_ID) " + 
          "INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON (HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID) " + 
          "WHERE EMP_GOAL_ASSESSMENT_ID = " + 
          assessId + 
          " ORDER BY GOAL_DTL_ID,HRMS_GOAL_ASSESSMENT_APPR_DTL.GOAL_APPROVER_LEVL";
      else {
        query = "SELECT NVL(GOAL_RATING,''),NVL(GOAL_COMMENT,''),CASE WHEN GOAL_DTL_ASSESSER_ID=" + 
          accessorId + 
          " THEN 'true' ELSE 'false' END ISACCESSER" + 
          " ,GOAL_DTL_ID,EMP_FNAME||' '||EMP_LNAME,ASSESSMENT_DTL_ID FROM HRMS_GOAL_EMP_ASSESSMENT_DTL" + 
          " LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID)" + 
          " WHERE EMP_GOAL_ASSESSMENT_ID =" + 
          assessId + 
          " ORDER BY GOAL_DTL_ID,HRMS_GOAL_EMP_ASSESSMENT_DTL.ASSESSMENT_DTL_ID ";
      }
      this.ratingData = getSqlModel().getSingleResultMap(query, 3, 2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**modified by @author prajakta.bhandare for substring functionality
 * @param goalId
 * @param empId
 * @param assmtId
 * @param bean
 * @param confgoalId
 * @return
 */
public ArrayList<Object> getGoalDetails(String goalId, String empId, String assmtId, EmpGoalAssessment bean, String confgoalId)
  {
    ArrayList goalList = null;
    try {
      System.out.println("Inside : getGoalDetails : ");
      String query = " SELECT GOAL_DTL_ID,GOAL_DESCRIPTION,TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY'),nvl(SELF_GOAL_RATING,0),nvl(SELF_GOAL_COMMENT,' ')  ,NVL(GOAL_COMMENTS,''),GOAL_WEIGHTAGE,NVL(GOAL_DTL_STATUS,'A'),GOAL_CATEGORY_CODE,NVL(GOAL_CATEGORY,' '),DECODE(IS_GOAL_ACHIEVED,'Y','YES','N','NO','O','On-Going','P','Partly Achieved'),NVL(HRMS_GOAL_CATEGORIES.GOAL_CATEGORY_WEIGHTAGE,0)  FROM HRMS_GOAL_EMP_DTL  LEFT JOIN HRMS_GOAL_EMP_HDR On HRMS_GOAL_EMP_HDR.GOAL_HDR_ID = HRMS_GOAL_EMP_DTL.GOAL_HDR_ID  inner join HRMS_GOAL_SELF_ASSESSMENT_DTL on (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID  and SELF_GOAL_ASSESSMENT_ID=" + 
        assmtId + ") " + 
        " LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) " + 
        " WHERE GOAL_DTL_STATUS='A' AND HRMS_GOAL_EMP_HDR.GOAL_HDR_ID = " + goalId + " AND EMP_ID = " + empId;
      Object[][] goalData = getSqlModel().getSingleResult(query);
      TreeMap map = setRatingList(confgoalId);
      for (int i = 1; i <= map.size(); i++) {
        System.out.println("..." + map.get(Integer.valueOf(i)));
      }
      if ((goalData != null) && (goalData.length > 0)) {
        goalList = new ArrayList();
        for (int i = 0; i < goalData.length; i++) {
          EmpGoalAssessment bean1 = new EmpGoalAssessment();
          bean1.setIndividualGoalId(checkNull(String.valueOf(goalData[i][0])));
          bean1.setGoalDescription(checkNull(String.valueOf(goalData[i][1])));
          if(bean1.getGoalDescription().length() > 15){
        	  bean1.setGoalDescriptionAbbr(bean1.getGoalDescription().substring(0, 14)+"...");
          }//end of if
          else{
        	  bean1.setGoalDescriptionAbbr(bean1.getGoalDescription());
          }//end of else
          bean1.setGoalAchDate(checkNull(String.valueOf(goalData[i][2])));
          bean1.setSelfRating(checkNull(String.valueOf(goalData[i][3])));
          bean1.setSelfComments(checkNull(String.valueOf(goalData[i][4])));
          if(bean1.getSelfComments().length() > 15){
        	  bean1.setSelfCommentsAbbr(bean1.getSelfComments().substring(0, 14)+"...");
          }//end of if
          else{
        	  bean1.setSelfCommentsAbbr(bean1.getSelfComments());
          }//end of else
          bean1.setApplicantComments(checkNull(String.valueOf(goalData[i][5])));
          if(bean1.getApplicantComments().length() > 15){
        	  bean1.setApplicantCommentsAbbr(bean1.getApplicantComments().substring(0, 14)+"...");
          }//end of if
          else{
        	  bean1.setApplicantCommentsAbbr(bean1.getApplicantComments());
          }//end of else
          bean1.setWeightage(checkNull(String.valueOf(goalData[i][6])));
          bean1.setGoalDtlStatus(checkNull(String.valueOf(goalData[i][7])) + "_");
          bean1.setIsGoalCategory(bean.getIsGoalCategory());
          ArrayList managerRatingList = setManagersRatingList(bean1, bean.getGoalAssessmentId(), Utility.checkNull(String.valueOf(goalData[i][0])), bean.getUserEmpId(), confgoalId);
          EmpGoalAssessment managerBean = new EmpGoalAssessment();

          managerBean.setAccessorFlag("true");
          managerBean.setGoalRating("");
          managerBean.setComment("");
          managerBean.setTmap(map);
          managerRatingList.add(managerBean);
          bean1.setManagerRatingList(managerRatingList);
          bean1.setIsGoalAchievedFlag(bean.getIsGoalAchievedFlag());

          bean1.setGoalCategoryCodeList(Utility.checkNull(String.valueOf(goalData[i][8])));
          bean1.setGoalCategoryNameList(Utility.checkNull(String.valueOf(goalData[i][9])));
          if(bean1.getGoalCategoryNameList().length() > 15){
        	  bean1.setGoalCategoryNameListAbbr(bean1.getGoalCategoryNameList().substring(0, 14)+"...");
          }else{
        	  bean1.setGoalCategoryNameListAbbr(bean1.getGoalCategoryNameList());
          }//end of else
          bean1.setGoalAchievedList(Utility.checkNull(String.valueOf(goalData[i][10])));
          bean1.setGoalCategoryWeightage(Utility.checkNull(String.valueOf(goalData[i][11])));

          goalList.add(bean1);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("Exception in getGoalDetails " + e);
    }
    return goalList;
  }

  public Object[][] getEmployeeDetails(String empId, String goalId) {
    Object[][] empData = (Object[][])null;
    try {
      String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,(HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  NVL(HRMS_CENTER.CENTER_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,'  '),NVL(HRMS_RANK.RANK_NAME,'  '),  (AS2.TITLE_NAME||' '||AS1.EMP_FNAME||' '||NVL(AS1.EMP_MNAME,'')|| ' ' || AS1.EMP_LNAME),TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY')  ,DECODE(IS_GOAL_CATEGORY,'N','false','Y','true','false'),DECODE(GOAL_ACHIEVED_FLAG,'N','false','Y','true','false'), GOAL_SIGNOFF_WORKFLOW,NVL(GOAL_RATING_RANGE_DESC,'') , GOAL_NAME FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  LEFT JOIN HRMS_EMP_OFFC AS1 ON (HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = AS1.EMP_ID)  LEFT JOIN HRMS_TITLE AS2  ON(AS1.EMP_TITLE_CODE = AS2.TITLE_CODE)  INNER JOIN HRMS_GOAL_EMP_HDR ON (HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID AND GOAL_HDR_ID=" + 
        goalId + ")" + 
        " INNER JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_EMP_HDR.GOAL_ID=HRMS_GOAL_CONFIG.GOAL_ID)" + 
        " WHERE HRMS_EMP_OFFC.EMP_ID = " + empId;
      empData = getSqlModel().getSingleResult(query);
    } catch (Exception e) {
      logger.error("Exception in getEmployeeDetails " + e);
    }
    return empData;
  }

  public TreeMap setRatingList(String GoaId)
  {
    System.out.println("the conf goal Id : " + GoaId);
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

  public boolean saveRating(EmpGoalAssessment bean, HttpServletRequest request)
  {
    Object[] individualGoalId = request.getParameterValues("individualGoalId");
    Object[] goalRating = (Object[])null;
    Object[] comment = (Object[])null;
    Object[] hiddenassessmentdtl = (Object[])null;
    if (bean.getGoalRatingAccess().equals("true"))
      goalRating = request.getParameterValues("goalRating");
    else {
      goalRating = new Object[individualGoalId.length];
    }//end of else
    if (bean.getGoalCommentsAccess().equals("true"))
      comment = request.getParameterValues("comment");
    else {
      comment = new Object[individualGoalId.length];
    }//end of else
    hiddenassessmentdtl = request.getParameterValues("hiddengoaldtlid");
    if (hiddenassessmentdtl == null) {
      hiddenassessmentdtl = new Object[individualGoalId.length];
    }
    boolean result = false;
    String query = "SELECT * FROM HRMS_GOAL_EMP_ASSESSMENT_DTL WHERE GOAL_DTL_ASSESSER_ID=" + bean.getUserEmpId() + " AND EMP_GOAL_ASSESSMENT_ID=" + bean.getGoalAssessmentId();
    Object[][] data = getSqlModel().getSingleResult(query);

    if ((data != null) && (data.length > 0))
    {
      System.out.println("Inside update if ::: ");
      //Query updated by prajakta B for inserting GOAL_ASSESSER_LEVEL in table
      Object[][] updateRating = new Object[comment.length][6];
      String updateQuery = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_DTL SET GOAL_RATING = ? , GOAL_COMMENT = ?, GOAL_ASSESSMENT_LEVEL=? WHERE GOAL_DTL_ASSESSER_ID = ? AND EMP_GOAL_ASSESSMENT_ID = ? AND ASSESSMENT_DTL_ID = ?";
      try {
        for (int i = 0; i < updateRating.length; i++) {
          System.out.println("goalRating[" + i + "] :: " + goalRating[i]);
          System.out.println("comment[" + i + "] :: " + comment[i]);
          System.out.println("bean.getGoalAssessmentId() :: " + bean.getGoalAssessmentId());
          System.out.println("bean.getUserEmpId() :: " + bean.getUserEmpId());
          System.out.println("hiddenassessmentdtl[" + i + "] :: " + hiddenassessmentdtl[i]);
          updateRating[i][0] = goalRating[i];
          updateRating[i][1] = comment[i];
          updateRating[i][2] = bean.getAssessLevel();
          updateRating[i][3] = bean.getUserEmpId();
          updateRating[i][4] = bean.getGoalAssessmentId();
          updateRating[i][5] = hiddenassessmentdtl[i];
        }

        result = getSqlModel().singleExecute(updateQuery, updateRating);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    else
    {
      System.out.println("Inside insert if ::: ");
      Object[][] maxRatingIdObj = getSqlModel().getSingleResult("SELECT NVL(MAX(ASSESSMENT_DTL_ID),0)+1 FROM HRMS_GOAL_EMP_ASSESSMENT_DTL");
      if ((maxRatingIdObj != null) && (maxRatingIdObj.length > 0))
      {
        int maxRatingId = Integer.parseInt(String.valueOf(maxRatingIdObj[0][0]));

        String insertQuery = "INSERT INTO HRMS_GOAL_EMP_ASSESSMENT_DTL ( EMP_GOAL_ASSESSMENT_ID, ASSESSMENT_DTL_ID, GOAL_DTL_ID, GOAL_RATING, GOAL_COMMENT,GOAL_DTL_ASSESSER_ID,GOAL_ASSESSMENT_LEVEL) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try
        {
          Object[][] insertRating = new Object[comment.length][7];
          for (int i = 0; i < insertRating.length; i++) {
            insertRating[i][0] = bean.getGoalAssessmentId();
            insertRating[i][1] = Integer.valueOf(maxRatingId++);
            insertRating[i][2] = individualGoalId[i];
            insertRating[i][3] = goalRating[i];
            insertRating[i][4] = comment[i];
            insertRating[i][5] = bean.getUserEmpId();
            insertRating[i][6] = bean.getAssessLevel();
          }
          result = getSqlModel().singleExecute(insertQuery, insertRating);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    return result;
  }

  /**modified by @author prajakta.bhandare for substring functionality
 * @param bean
 * @param request
 */
public void getRatingList(EmpGoalAssessment bean, HttpServletRequest request) {
    String[] individualGoalId = request.getParameterValues("individualGoalId");
    String[] goalRating = request.getParameterValues("goalRating");
    String[] comment = request.getParameterValues("comment");
    String[] goalDescription = request.getParameterValues("goalDescription");
    String[] goalAchDate = request.getParameterValues("goalAchDate");

    String[] selfRating = request.getParameterValues("selfRating");

    String[] selfComments = request.getParameterValues("selfComments");

    String[] applicantComments = request.getParameterValues("applicantComments");
    String[] weightage = request.getParameterValues("weightage");
    String[] goalDtlStatus = request.getParameterValues("goalDtlStatus");

    logger.info("getRatingList MODEL");
    try {
      ArrayList tableList = new ArrayList();
      for (int i = 0; i < individualGoalId.length; i++) {
        EmpGoalAssessment beanList = new EmpGoalAssessment();
        beanList.setIndividualGoalId(individualGoalId[i]);
        if (bean.getGoalRatingAccess().equals("true")) {
          beanList.setGoalRating(goalRating[i]);
        }
        if (bean.getGoalCommentsAccess().equals("true")) {
          beanList.setComment(comment[i]);
        }
        beanList.setGoalDescription(goalDescription[i]);
        if(beanList.getGoalDescription().length() > 15){
        	beanList.setGoalDescriptionAbbr(beanList.getGoalDescription().substring(0, 14)+"...");
        }//end of if
        else{
        	beanList.setGoalDescriptionAbbr(beanList.getGoalDescription());
        }//end of else
        beanList.setGoalAchDate(goalAchDate[i]);
        beanList.setSelfRating(selfRating[i]);
        beanList.setSelfComments(selfComments[i]);
        if(beanList.getSelfComments().length() > 15){
        	beanList.setSelfCommentsAbbr(beanList.getSelfComments().substring(0, 14)+"...");
        }//end of if
        else{
        	beanList.setSelfCommentsAbbr(beanList.getSelfComments());
        }//end of else
        beanList.setApplicantComments(applicantComments[i]);
        if(beanList.getApplicantComments().length() > 15){
        	beanList.setApplicantCommentsAbbr(beanList.getApplicantComments().substring(0, 14)+"...");
        }//end of if
        else{
        	beanList.setApplicantCommentsAbbr(beanList.getApplicantComments());
        }//end of else
        beanList.setWeightage(weightage[i]);
        beanList.setGoalDtlStatus(goalDtlStatus[i]);
        tableList.add(beanList);
      }
      if (tableList.size() > 0) {
        logger.info("tableList.size()>0");
        bean.setGoalList(tableList);
      } else {
        bean.setNoData("true");
      }//end of else
    } catch (Exception e) {
      bean.setNoData("true");
    }
  }

  public boolean finalizeAssessment(EmpGoalAssessment bean, String assesmentId, String empId, int level, String reportingType, String confGaolId, String loginuserId, String assessmentType, String assessmentlevel, String goalId)
  {
    boolean result = false;

    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    try
    {
      String escalationFlowVal = "N";
      Object[][] empFlow = (Object[][])null;
      Object[][] escalData = getEsclationWorkFlowDtl(confGaolId);
      if ((escalData != null) && (escalData.length > 0))
        escalationFlowVal = String.valueOf(escalData[0][0]);
      System.out.println("escalationFlowVal--------" + escalationFlowVal);

      String lastAssesmentId = "";
      String LastAssesment = " SELECT EMP_GOAL_ASSESSMENT_ID FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE GOAL_ASSESSMENT_DATE in(SELECT MAX(GOAL_ASSESSMENT_DATE) FROM HRMS_GOAL_ASSESSMENT_CONFIG WHERE GOAL_ID IN (SELECT GOAL_ID FROM HRMS_GOAL_EMP_HDR WHERE GOAL_HDR_ID=" + 
        goalId + ")) AND GOAL_ID=" + goalId;
      Object[][] LastAssesmentObj = getSqlModel().getSingleResult(LastAssesment);
      if ((LastAssesmentObj != null) && (LastAssesmentObj.length > 0)) {
        lastAssesmentId = String.valueOf(LastAssesmentObj[0][0]);
      }

      empFlow = model.generateEmpFlow(empId, level, reportingType);

      if ((reportingType.equals("goal")) && 
        (empFlow != null) && (empFlow.length > 0)) {
        try {
          while (String.valueOf(empFlow[0][3]).equals("N")) {
            level++;
            empFlow = model.generateEmpFlow(empId, level, 
              reportingType);
          }
        }
        catch (Exception localException1)
        {
        }
      }

      if (!assessmentType.equals("reassessment"))
      {
        if ((escalationFlowVal.equals("Y")) && 
          (!lastAssesmentId.equals(assesmentId))) {
          String insertescalQuery = "INSERT INTO HRMS_GOAL_ASSESSMENT_APPR_DTL(GOAL_ID, EMP_GOAL_ASSESSMENT_ID, GOAL_ASSESSMENT_STATUS, GOAL_APPROVER_ID, ASSESSMENT_COMPLETED_DATE, GOAL_EMP_ID, GOAL_APPROVER_LEVL) VALUES (" + 
            confGaolId + 
            "," + 
            assesmentId + 
            ",'2'," + 
            loginuserId + 
            ",sysdate," + 
            empId + 
            "," + (
            level - 1) + 
            ")";
          System.out.println("insertescalQuery = " + insertescalQuery);
          result = getSqlModel().singleExecute(insertescalQuery);
        }

        if ((empFlow != null) && (empFlow.length > 0) && 
          (lastAssesmentId.equals(assesmentId))) {
          String selAppDtl = " SELECT  GOAL_APPROVER_ID FROM  HRMS_GOAL_ASSESSMENT_APPR_DTL WHERE EMP_GOAL_ASSESSMENT_ID=" + bean.getGoalAssessmentId() + " AND GOAL_APPROVER_ID=" + loginuserId;
          Object[][] selAppDtlObj = getSqlModel().getSingleResult(selAppDtl);
          if ((selAppDtlObj != null) && (selAppDtlObj.length > 0)) {
            System.out.println("Allready exisits");
          } else {
            String insertescalQuery = "INSERT INTO HRMS_GOAL_ASSESSMENT_APPR_DTL(GOAL_ID, EMP_GOAL_ASSESSMENT_ID, GOAL_ASSESSMENT_STATUS, GOAL_APPROVER_ID, ASSESSMENT_COMPLETED_DATE, GOAL_EMP_ID, GOAL_APPROVER_LEVL) VALUES (" + 
              confGaolId + 
              "," + 
              assesmentId + 
              ",'2'," + 
              loginuserId + 
              ",sysdate," + 
              empId + 
              "," + (
              level - 1) + 
              ")";
            System.out.println("insertescalQuery = " + insertescalQuery);
            result = getSqlModel().singleExecute(insertescalQuery);
          }//end of else
        }

      }

      if (escalationFlowVal.equals("Y"))
      {
        if ((empFlow != null) && (empFlow.length > 0))
        {
          if (String.valueOf(empFlow[0][6]).equals("A"))
          {
            Object[][] updateObj = new Object[1][3];
            updateObj[0][0] = empFlow[0][2];
            updateObj[0][1] = empFlow[0][0];
            updateObj[0][2] = assesmentId;
            String updateQuery = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_ASSESSER_LEVEL=?,GOAL_ASSESSER_ID=?   WHERE EMP_GOAL_ASSESSMENT_ID =?";

            result = getSqlModel().singleExecute(updateQuery, updateObj);
          }
          else if (!assessmentType.equals("reassessment"))
          {
            if (lastAssesmentId.equals(assesmentId)) {
              getCalculatedRatingsByGoalId(bean);
              bean.setDivScoreDet("true");
              String query = " SELECT  FINAL_RATING FROM  HRMS_GOAL_EMP_HDR WHERE HRMS_GOAL_EMP_HDR.GOAL_HDR_ID =" + 
                bean.getGoalId();
              Object[][] finalObj = getSqlModel().getSingleResult(query);
              if ((finalObj != null) && (finalObj.length > 0)) {
                bean.setFinalRating(Utility.twoDecimals(Double.parseDouble(String.valueOf(finalObj[0][0]))));
              }
              result = true;
              result = getSqlModel()
                .singleExecute(
                "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_REVIEWER_ID= " + empFlow[0][0] + ",GOAL_REVIEWER_STATUS = 'D'" + 
                " WHERE EMP_GOAL_ASSESSMENT_ID =" + 
                assesmentId);
            }
            else {
              result = getSqlModel()
                .singleExecute(
                "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_ASSESSMENT_STATUS =2,ASSESSMENT_COMPLETED_DATE=SYSDATE,GOAL_REVIEWER_ID= " + empFlow[0][0] + ",GOAL_REVIEWER_STATUS = 'D'" + 
                " WHERE EMP_GOAL_ASSESSMENT_ID =" + 
                assesmentId);
            }//end of else

          }
          else
          {
            result = getSqlModel()
              .singleExecute(
              "UPDATE HRMS_GOAL_ASSESSMENT_APPR_DTL SET GOAL_REASSESSMENT_STATUS ='Y' WHERE EMP_GOAL_ASSESSMENT_ID = " + 
              assesmentId + " AND GOAL_EMP_ID = " + empId + " AND GOAL_APPROVER_LEVL = " + assessmentlevel + " AND GOAL_APPROVER_ID = " + loginuserId);
          }//end of else

        }

      }
      else if ((empFlow != null) && (empFlow.length > 0))
      {
    	 /* //Added by Prajakta to update Level and Approver
    	  Object[][] updateObj = new Object[1][3];
			updateObj[0][0] = empFlow[0][2]; // level
			updateObj[0][1] = empFlow[0][0]; // approver id
			updateObj[0][2] = assesmentId; // assesmentId
			String updateQuery = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_ASSESSER_LEVEL=?,GOAL_ASSESSER_ID=?  "
					+ " WHERE EMP_GOAL_ASSESSMENT_ID =?";
			result = getSqlModel().singleExecute(updateQuery, updateObj);
			// Ends Added by Prajakta to update Level and Approver
*/        getCalculatedRatingsByGoalId(bean);
        bean.setDivScoreDet("true");
        String query = " SELECT  FINAL_RATING FROM  HRMS_GOAL_EMP_HDR WHERE HRMS_GOAL_EMP_HDR.GOAL_HDR_ID =" + 
          bean.getGoalId();
        Object[][] finalObj = getSqlModel().getSingleResult(query);
        if ((finalObj != null) && (finalObj.length > 0)) {
          bean.setFinalRating(Utility.twoDecimals(Double.parseDouble(String.valueOf(finalObj[0][0]))));
        }
        result = true;
      }
      else if (lastAssesmentId.equals(assesmentId)) {
        getCalculatedRatingsByGoalId(bean);
        bean.setDivScoreDet("true");
        String query = " SELECT  FINAL_RATING FROM  HRMS_GOAL_EMP_HDR WHERE HRMS_GOAL_EMP_HDR.GOAL_HDR_ID =" + 
          bean.getGoalId();
        Object[][] finalObj = getSqlModel().getSingleResult(query);
        if ((finalObj != null) && (finalObj.length > 0)) {
          bean.setFinalRating(Utility.twoDecimals(Double.parseDouble(String.valueOf(finalObj[0][0]))));
        }
        result = true;
      }
      else {
        result = getSqlModel()
          .singleExecute(
          "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_ASSESSMENT_STATUS =2,ASSESSMENT_COMPLETED_DATE=SYSDATE  WHERE EMP_GOAL_ASSESSMENT_ID =" + 
          assesmentId);
      }//end of else

      if (assessmentType.equals("reassessment")) {
        result = getSqlModel()
          .singleExecute(
          "UPDATE HRMS_GOAL_ASSESSMENT_APPR_DTL SET GOAL_REASSESSMENT_STATUS ='Y' WHERE EMP_GOAL_ASSESSMENT_ID = " + 
          assesmentId + " AND GOAL_EMP_ID = " + empId + " AND GOAL_APPROVER_LEVL = " + assessmentlevel + " AND GOAL_APPROVER_ID = " + loginuserId);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return false;
    }
    model.terminate();
    return result;
  }

  public boolean lastfinalizeAssessment(EmpGoalAssessment bean, int level) {
    boolean result = false;
    try
    {
      String escalationFlowVal = "N";
      Object[][] escalData = getEsclationWorkFlowDtl(bean.getConfgoalId());
      if ((escalData != null) && (escalData.length > 0))
        escalationFlowVal = String.valueOf(escalData[0][0]);
      System.out.println("escalationFlowVal--------" + escalationFlowVal);

      if (!bean.getHiddenassessmentType().equals("reassessment"))
      {
        if (escalationFlowVal.equals("Y"))
        {
          String selAppDtl = " SELECT  GOAL_APPROVER_ID FROM  HRMS_GOAL_ASSESSMENT_APPR_DTL WHERE EMP_GOAL_ASSESSMENT_ID=" + bean.getGoalAssessmentId() + " AND GOAL_APPROVER_ID=" + bean.getUserEmpId();
          Object[][] selAppDtlObj = getSqlModel().getSingleResult(selAppDtl);
          if ((selAppDtlObj != null) && (selAppDtlObj.length > 0)) {
            System.out.println("Allready exisits");
          } else {
            String insertescalQuery = "INSERT INTO HRMS_GOAL_ASSESSMENT_APPR_DTL(GOAL_ID, EMP_GOAL_ASSESSMENT_ID, GOAL_ASSESSMENT_STATUS, GOAL_APPROVER_ID, ASSESSMENT_COMPLETED_DATE, GOAL_EMP_ID, GOAL_APPROVER_LEVL) VALUES (" + 
              bean.getConfgoalId() + 
              "," + 
              bean.getGoalAssessmentId() + 
              ",'2'," + 
              bean.getUserEmpId() + 
              ",sysdate," + 
              bean.getEmpId() + 
              "," + (
              level - 1) + 
              ")";
            System.out.println("insertescalQuery = " + insertescalQuery);
            result = getSqlModel().singleExecute(insertescalQuery);
          }
        }

      }

      result = getSqlModel().singleExecute(
        "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_ASSESSMENT_STATUS =2,ASSESSMENT_COMPLETED_DATE=SYSDATE  WHERE EMP_GOAL_ASSESSMENT_ID =" + 
        bean.getGoalAssessmentId());
    }
    catch (RuntimeException e) {
      e.printStackTrace();
    }
    return result;
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
  public void setAccess(EmpGoalAssessment empGoalAssessment) {
    EmployeeGoalSettingModel model = new EmployeeGoalSettingModel();
    model.initiate(this.context, this.session);
    Object[][] empFlow = (Object[][])null;
    String reportingType = empGoalAssessment.getReportingType();

    System.out.println("reportingType  ::::: " + reportingType);
    if (!reportingType.equals("goal")) {
      empGoalAssessment.setGoalRatingAccess("true");
      empGoalAssessment.setGoalCommentsAccess("true");
    }
    else {
      System.out.println("Access Type  : ::::  " + empGoalAssessment.getHiddenassessmentType());
      System.out.println("empGoalAssessment.getReassessmentlevel() ::: " + empGoalAssessment.getReassessmentlevel());
      if (empGoalAssessment.getHiddenassessmentType().equals("reassessment"))
      {
        empFlow = model.generateEmpFlow(empGoalAssessment.getEmpId(), Integer.parseInt(empGoalAssessment.getReassessmentlevel()), reportingType);
      }
      else
      {
        empFlow = model.generateEmpFlow(empGoalAssessment.getEmpId(), Integer.parseInt(empGoalAssessment.getAssessLevel()), reportingType);
      }
      if ((empFlow != null) && (empFlow.length > 0))
      {
        System.out.println("*******String.valueOf(empFlow[0][0])::" + String.valueOf(empFlow[0][0]));
        System.out.println("*******String.valueOf(empFlow[0][4])::" + String.valueOf(empFlow[0][4]));
        System.out.println("*******String.valueOf(empFlow[0][5])::" + String.valueOf(empFlow[0][5]));

        if (String.valueOf(empFlow[0][0]).equals(empGoalAssessment.getUserEmpId())) {
          if (String.valueOf(empFlow[0][4]).equals("Y"))
            empGoalAssessment.setGoalRatingAccess("true");
          else {
            empGoalAssessment.setGoalRatingAccess("false");
          }
          if (String.valueOf(empFlow[0][5]).equals("Y"))
            empGoalAssessment.setGoalCommentsAccess("true");
          else
            empGoalAssessment.setGoalCommentsAccess("false");
        }
        else {
          empGoalAssessment.setGoalRatingAccess("false");
          empGoalAssessment.setGoalCommentsAccess("false");
        }
      } else {
        empGoalAssessment.setGoalRatingAccess("false");
        empGoalAssessment.setGoalCommentsAccess("false");
      }
    }
    model.terminate();
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("")) || (result.equals("null"))) {
      return "";
    }
    return result;
  }

  public boolean signOffGoal(EmpGoalAssessment empGoalAssessment) {
    boolean result = false;
    try {
      String signOffQuery = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_IS_SIGNOFF = 'Y' WHERE EMP_GOAL_ASSESSMENT_ID = " + 
        empGoalAssessment.getGoalAssessmentId();
      result = getSqlModel().singleExecute(signOffQuery);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public Object[][] getReviewrDet(String empId)
  {
    Object[][] data = (Object[][])null;
    try {
      String query = "SELECT REPORTINGDTL_EMP_ID, REPORTINGDTL_DESG_ID, REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS, REPORTINGDTL_APPROVER_TYPE from HRMS_REPTNG_STRUCTDTL_GOAL where HRMS_REPTNG_STRUCTDTL_GOAL.REPORTINGHDR_CODE IN(SELECT REPORTINGHDR_CODE from HRMS_REPTNG_STRUCTHDR_GOAL where HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_EMP_ID=" + 
        empId + ")" + 
        "AND REPORTINGDTL_APPROVER_TYPE='R'";
      data = getSqlModel().getSingleResult(query);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return data;
  }

  public Object[][] getNoOfCategoryQuestionSelf(String assessmentId, String categoryId)
  {
    Object[][] data = (Object[][])null;
    if (categoryId != null)
    {
      String query = "SELECT COUNT(*) AS NO_OF_CAT_QUESTION FROM HRMS_GOAL_SELF_ASSESSMENT_DTL INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID =" + 
        assessmentId + ")  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) WHERE GOAL_CATEGORY_CODE=" + categoryId;
      data = getSqlModel().getSingleResult(query);
    }

    return data;
  }

  public void getCalculatedRatingsByGoalId(EmpGoalAssessment bean)
  {
    System.out.println("GOAL_ID--------------------->" + bean.getConfgoalId());
    String goalId = bean.getConfgoalId();

    String query = "SELECT NVL(GOAL_RATING_WEIGHTAGE,0) AS GOAL_WT, NVL(GOAL_SELF_WEIGHTAGE,0) AS SELF_WGT, NVL(GOAL_MANAGER_WEIGHTAGE,0) AS MGR_WT, NVL(MULTIPLE_RATING_OPTION,0) AS AVG_RT ,NVL(GOAL_RATING_RANGE_UPTO,5),IS_GOAL_CATEGORY FROM \t HRMS_GOAL_CONFIG WHERE GOAL_ID= " + 
      goalId;
    Object[][] goalObj = getSqlModel().getSingleResult(query);
    if ((goalObj != null) && (goalObj.length > 0))
    {
      double GOAL_WGT = Double.parseDouble(String.valueOf(goalObj[0][0]));

      double SELF_WGT = Double.parseDouble(String.valueOf(goalObj[0][1]));

      double MGR_WGT = Double.parseDouble(String.valueOf(goalObj[0][2]));

      String IS_AVG = String.valueOf(goalObj[0][3]);
      String IS_GOAL_CATEGORY = String.valueOf(goalObj[0][5]);
      System.out.println("IS_GOAL_CATEGORY :: " + IS_GOAL_CATEGORY);
      int REPORTING_LEVEL = Integer.parseInt(bean.getAssessLevel());
      int EMP_SELF_QUEST = 0;

      double EMP_FINAL_RATING = 0.0D;
      System.out.println("REPORTING_LEVEL :" + REPORTING_LEVEL);
      String goalHdrCode = bean.getGoalId();
      String empRating = "";

      double empSelfRatingTotal = 0.0D;

      String mgrRating = "SELECT DISTINCT HRMS_GOAL_EMP_DTL.GOAL_DTL_ID, HRMS_GOAL_EMP_HDR.EMP_ID,HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_EMP_DTL.GOAL_WEIGHTAGE,HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_RATING \t FROM HRMS_GOAL_EMP_HDR  \t INNER JOIN HRMS_GOAL_EMP_DTL ON(HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) \t INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) \t INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON(HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID) \t WHERE HRMS_GOAL_EMP_HDR.GOAL_ID=" + 
        goalId + 
        "  AND HRMS_GOAL_EMP_HDR.EMP_GOAL_APPROVAL_STATUS=3 AND HRMS_GOAL_EMP_HDR.EMP_ID=" + 
        bean.getEmpId();
      Object[][] mrgObj = getSqlModel()
        .getSingleResult(mgrRating);

      if (REPORTING_LEVEL != 1) {
        mgrRating = "SELECT DISTINCT HRMS_GOAL_EMP_ASSESSMENT_DTL.ASSESSMENT_DTL_ID, HRMS_GOAL_EMP_HDR.EMP_ID,HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_EMP_DTL.GOAL_WEIGHTAGE,HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_RATING \t FROM HRMS_GOAL_EMP_HDR  \t INNER JOIN HRMS_GOAL_EMP_DTL ON(HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) \t INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) \t INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON(HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID) \t WHERE HRMS_GOAL_EMP_HDR.GOAL_ID=" + 
          goalId + 
          "  AND HRMS_GOAL_EMP_HDR.EMP_GOAL_APPROVAL_STATUS=3 AND HRMS_GOAL_EMP_HDR.EMP_ID=" + 
          bean.getEmpId();
        mgrRating = mgrRating + " ORDER BY HRMS_GOAL_EMP_ASSESSMENT_DTL.ASSESSMENT_DTL_ID";
      }

      String assessmentIdSql = "SELECT DISTINCT HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID FROM HRMS_GOAL_EMP_ASSESSMENT_DTL LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID) WHERE EMP_GOAL_ASSESSMENT_ID IN (SELECT EMP_GOAL_ASSESSMENT_ID FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE GOAL_ID=" + 
        goalHdrCode + ") ";
      Object[][] assessmentIdObj = getSqlModel().getSingleResult(assessmentIdSql);
      double mgrSelfRatingTotal = 0.0D;
      if ((assessmentIdObj != null) && (assessmentIdObj.length > 0))
      {
        for (int k = 0; k < assessmentIdObj.length; k++)
        {
          empRating = "SELECT SELF_GOAL_DTL_ID,GOAL_DESCRIPTION, TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY'),NVL(GOAL_WEIGHTAGE,0)  ,SELF_GOAL_RATING  ,NVL(GOAL_COMMENTS,''), NVL(GOAL_DTL_STATUS,'A'),GOAL_CATEGORY_CODE,NVL(GOAL_CATEGORY,' '),IS_GOAL_ACHIEVED,GOAL_CATEGORY_WEIGHTAGE FROM HRMS_GOAL_SELF_ASSESSMENT_DTL\t\t INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID =" + assessmentIdObj[k][0] + ")  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) ORDER BY SELF_GOAL_DTL_ID";

          Object[][] ratingObj = getSqlModel().getSingleResult(
            empRating);
          double empSelfRating = 0.0D;

          empSelfRatingTotal = 0.0D;
          double RATING = 5.0D;
          RATING = Double.parseDouble(String.valueOf(goalObj[0][4]));
          double ratingDtl = 0.0D;

          if ((ratingObj != null) && (ratingObj.length > 0)) {
            for (int i = 0; i < ratingObj.length; i++) {
              int noQuestion = 1;
              Object[][] noQuesCatSelfObj = getNoOfCategoryQuestionSelf(String.valueOf(assessmentIdObj[k][0]), String.valueOf(ratingObj[i][7]));
              if ((noQuesCatSelfObj != null) && (noQuesCatSelfObj.length > 0))
              {
                noQuestion = Integer.parseInt(String.valueOf(noQuesCatSelfObj[0][0]));
              }
              if (String.valueOf(ratingObj[i][3]).equals("0"))
              {
                System.out.println("ratingDtl" + ratingDtl);
              } else {
                ratingDtl = Double.parseDouble(
                  String.valueOf(ratingObj[i][4])) / RATING * 100.0D;
                if (IS_GOAL_CATEGORY.equals("Y"))
                {
                  ratingDtl = ratingDtl * (
                    Double.parseDouble(String.valueOf(ratingObj[i][3])) / 100.0D) * (Double.parseDouble(String.valueOf(ratingObj[i][10])) / noQuestion / Double.parseDouble(String.valueOf(ratingObj[i][3])));
                  System.out.println(ratingDtl * (
                    Double.parseDouble(String.valueOf(ratingObj[i][3])) / 100.0D) * (Double.parseDouble(String.valueOf(ratingObj[i][10])) / noQuestion / Double.parseDouble(String.valueOf(ratingObj[i][3]))) + "rating " + String.valueOf(ratingObj[i][3]) + "10" + String.valueOf(ratingObj[i][10]));
                }
                else
                {
                  ratingDtl = ratingDtl * (
                    Double.parseDouble(
                    String.valueOf(ratingObj[i][3])) / 100.0D);
                }
                empSelfRating += ratingDtl;
              }
            }
            System.out.println("TOTAL EMP SELF RATING : " + 
              empSelfRating);

            empSelfRatingTotal = empSelfRating * SELF_WGT / 100.0D;
          }

          System.out.println("TOTAL EMP SELF RATING OUT 20% : " + 
            empSelfRatingTotal);

          mgrSelfRatingTotal = 0.0D;

          mgrRating = "SELECT NVL(GOAL_RATING,0),NVL(GOAL_COMMENT,''),CASE WHEN GOAL_DTL_ASSESSER_ID=2 THEN 'true' ELSE 'false' END ISACCESSER ,GOAL_DTL_ID,EMP_FNAME||' '||EMP_LNAME,EMP_GOAL_ASSESSMENT_ID , HRMS_GOAL_EMP_DTL.GOAL_CATEGORY_CODE\t,GOAL_CATEGORY_WEIGHTAGE,GOAL_DTL_ASSESSER_ID FROM HRMS_GOAL_EMP_ASSESSMENT_DTL LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID) INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND EMP_GOAL_ASSESSMENT_ID = " + 
            assessmentIdObj[k][0] + ") " + 
            "LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) " + 
            "ORDER BY GOAL_DTL_ID";

          mrgObj = getSqlModel().getSingleResult(mgrRating);

          double mgrRatingDtl = 0.0D;
          double secMgrRatingDtl = 0.0D;
          double mgrSelfRating = 0.0D;

          if ((mrgObj != null) && (mrgObj.length > 0))
          {
            if (REPORTING_LEVEL == 1) {
              try {
                System.out.println("Inside First If : mrgObj.length " + mrgObj.length + " ::: REPORTING_LEVEL : " + REPORTING_LEVEL);
                for (int i = 0; i < ratingObj.length; i++) {
                  if (String.valueOf(ratingObj[i][3]).equals("0")) {
                    System.out
                      .println("mgrRatingDtl" + mgrRatingDtl);
                  } else {
                    mgrRatingDtl = Double.parseDouble(
                      String.valueOf(mrgObj[i][0])) / RATING * 100.0D;
                    if (IS_GOAL_CATEGORY.equals("Y"))
                    {
                      int noQuestion = 1;
                      Object[][] noQuesCatSelfObj = getNoOfCategoryQuestionSelf(String.valueOf(assessmentIdObj[k][0]), String.valueOf(mrgObj[i][6]));
                      if ((noQuesCatSelfObj != null) && (noQuesCatSelfObj.length > 0))
                      {
                        noQuestion = Integer.parseInt(String.valueOf(noQuesCatSelfObj[0][0]));
                      }
                      System.out
                        .println("ManagerObject3" + 
                        String.valueOf(ratingObj[i][3]) + "4>>>>>>>>>" + String.valueOf(mrgObj[i][7]));
                      mgrRatingDtl = mgrRatingDtl * (
                        Double.parseDouble(
                        String.valueOf(ratingObj[i][3])) / 100.0D) * (Double.parseDouble(String.valueOf(mrgObj[i][7])) / noQuestion / Double.parseDouble(String.valueOf(ratingObj[i][3])));
                    }
                    else
                    {
                      mgrRatingDtl = mgrRatingDtl * (
                        Double.parseDouble(
                        String.valueOf(ratingObj[i][3])) / 100.0D);
                    }

                    mgrSelfRating += mgrRatingDtl;
                  }
                }

              }
              catch (Exception localException)
              {
              }

            }
            else if (IS_AVG.equals("A")) {
              int count = 0;
              int chkcount = 0;
              for (int i = 0; i < mrgObj.length / 2; i++)
              {
                System.out.println("The manager Id : first :  " + String.valueOf(mrgObj[(chkcount++)][8]));
                String RATING_ACCESS = "N";
                String goalReportingQuery = "SELECT GOAL_REPORTING FROM HRMS_GOAL_CONFIG WHERE GOAL_ID=" + goalId;
                Object[][] reportingObj = getSqlModel().getSingleResult(goalReportingQuery);
                if ((reportingObj != null) && (reportingObj.length > 0) && 
                  (String.valueOf(reportingObj[0][0]).equals("reporting"))) {
                  RATING_ACCESS = "Y";
                }

                String sqlRatingAccess = "SELECT REPORTINGDTL_RATING,REPORTINGDTL_VIEW, REPORTINGDTL_COMMENTS FROM HRMS_REPTNG_STRUCTDTL_GOAL INNER JOIN HRMS_REPTNG_STRUCTHDR_GOAL ON (HRMS_REPTNG_STRUCTDTL_GOAL.REPORTINGHDR_CODE=HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_CODE)WHERE HRMS_REPTNG_STRUCTDTL_GOAL.REPORTINGDTL_EMP_ID=" + 
                  mrgObj[chkcount][8] + " AND \tHRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_EMP_ID=" + bean.getEmpId();
                Object[][] ratingAccessObj = getSqlModel().getSingleResult(sqlRatingAccess);
                if ((ratingAccessObj != null) && (ratingAccessObj.length > 0))
                {
                  RATING_ACCESS = String.valueOf(ratingAccessObj[0][0]);
                }
                System.out.println("RATING_ACCESS ::: " + RATING_ACCESS);
                System.out.println("The manager Id : second :  " + String.valueOf(mrgObj[(chkcount++)][8]));
                if (IS_GOAL_CATEGORY.equals("Y"))
                {
                  int noQuestion = 1;
                  Object[][] noQuesCatSelfObj = getNoOfCategoryQuestionSelf(String.valueOf(assessmentIdObj[k][0]), String.valueOf(mrgObj[count][6]));
                  if ((noQuesCatSelfObj != null) && (noQuesCatSelfObj.length > 0))
                  {
                    noQuestion = Integer.parseInt(String.valueOf(noQuesCatSelfObj[0][0]));
                  }
                  if ((String.valueOf(ratingObj[i][3]).equals("0")) || (String.valueOf(ratingObj[i][10]).equals("0"))) {
                    System.out
                      .println("mgrRatingDtl" + mgrRatingDtl);
                    mgrRatingDtl = 0.0D;
                    System.out
                      .println("Count" + count);
                    count++;
                    count++;
                  } else {
                    if (RATING_ACCESS.equals("Y"))
                    {
                      mgrRatingDtl = (Double.parseDouble(
                        String.valueOf(mrgObj[(count++)][0])) + Double.parseDouble(
                        String.valueOf(mrgObj[(count++)][0]))) / (RATING * 2.0D) * 100.0D;
                    }
                    else
                    {
                      mgrRatingDtl = (Double.parseDouble(
                        String.valueOf(mrgObj[(count++)][0])) + Double.parseDouble(
                        String.valueOf(mrgObj[(count++)][0]))) / RATING * 100.0D;
                    }

                    int cnt = count - 1;

                    mgrRatingDtl = mgrRatingDtl * (
                      Double.parseDouble(String.valueOf(ratingObj[i][3])) / 100.0D) * (Double.parseDouble(String.valueOf(mrgObj[(count - 1)][7])) / noQuestion / Double.parseDouble(String.valueOf(ratingObj[i][3])));
                  }
                } else {
                  if (RATING_ACCESS.equals("Y"))
                  {
                    mgrRatingDtl = (Double.parseDouble(
                      String.valueOf(mrgObj[(count++)][0])) + Double.parseDouble(
                      String.valueOf(mrgObj[(count++)][0]))) / (RATING * 2.0D) * 100.0D;
                  }
                  else {
                    mgrRatingDtl = (Double.parseDouble(
                      String.valueOf(mrgObj[(count++)][0])) + Double.parseDouble(
                      String.valueOf(mrgObj[(count++)][0]))) / RATING * 100.0D;
                  }

                  mgrRatingDtl = mgrRatingDtl * (
                    Double.parseDouble(
                    String.valueOf(ratingObj[i][3])) / 100.0D);
                }
                System.out.println("mgrRatingDtl------------->" + String.valueOf(ratingObj[i][8]) + "------>" + mgrRatingDtl);
                mgrSelfRating += mgrRatingDtl;
                System.out.println("mgrRatingDtl : " + mgrRatingDtl);
              }
            }
            else
            {
              int count = 1;
              for (int i = 0; i < mrgObj.length / 2; i++) {
                if (String.valueOf(ratingObj[i][3]).equals("0")) {
                  System.out
                    .println("mgrRatingDtl" + mgrRatingDtl);
                } else {
                  mgrRatingDtl = Double.parseDouble(
                    String.valueOf(mrgObj[(count++)][0])) / RATING * 100.0D;
                  if (IS_GOAL_CATEGORY.equals("Y"))
                  {
                    int noQuestion = 1;

                    Object[][] noQuesCatSelfObj = getNoOfCategoryQuestionSelf(String.valueOf(assessmentIdObj[k][0]), String.valueOf(mrgObj[i][6]));
                    if ((noQuesCatSelfObj != null) && (noQuesCatSelfObj.length > 0))
                    {
                      noQuestion = Integer.parseInt(String.valueOf(noQuesCatSelfObj[0][0]));
                    }

                    mgrRatingDtl = mgrRatingDtl * (
                      Double.parseDouble(
                      String.valueOf(ratingObj[i][3])) / 100.0D) * (Double.parseDouble(String.valueOf(mrgObj[(i + 1)][7])) / noQuestion / Double.parseDouble(String.valueOf(ratingObj[i][3])));
                  }
                  else
                  {
                    mgrRatingDtl = mgrRatingDtl * (
                      Double.parseDouble(
                      String.valueOf(ratingObj[i][3])) / 100.0D);
                  }
                }

                mgrSelfRating += mgrRatingDtl;
                count++;
              }
            }

            System.out.println("TOTAL MGR RATING : " + 
              mgrSelfRating);
            mgrSelfRatingTotal = mgrSelfRating * MGR_WGT / 100.0D;

            System.out.println("Total of Each Assessment : " + (empSelfRatingTotal + mgrSelfRatingTotal));

            String assementWeigthtageQuery = "SELECT HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_WEIGTAGE FROM HRMS_GOAL_EMP_ASSESSMENT_HDR LEFT JOIN HRMS_GOAL_ASSESSMENT_CONFIG ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE= HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_DATE and GOAL_ID= " + 
              goalId + ") " + 
              "WHERE HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID = " + assessmentIdObj[k][0];
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
          updateObj[0][3] = goalHdrCode;
          updateObj[0][4] = bean.getEmpId();
          updateObj[0][5] = String.valueOf(assessmentIdObj[k][0]);
          System.out.println("updateObj[0][3] :" + updateObj[0][3]);
          System.out.println("updateObj[0][4] :" + updateObj[0][4]);
          String updateQuery = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_REVIEW_SELF_RATING=?, GOAL_REVIEW_MGR_RATING=?, GOAL_REVIEW_FINAL_RATING=? WHERE GOAL_ID=? AND EMP_ID=? AND EMP_GOAL_ASSESSMENT_ID = ?";
          getSqlModel().singleExecute(updateQuery, updateObj);
        }

      }

      System.out.println("mgrSelfRatingTotal ::::: " + mgrSelfRatingTotal);
      Object[][] updateObj = new Object[1][4];

      double goalWgt = EMP_FINAL_RATING;
      double finalWgt = goalWgt;
      updateObj[0][0] = Double.valueOf(goalWgt);
      updateObj[0][1] = Double.valueOf(finalWgt);
      updateObj[0][2] = goalHdrCode;
      updateObj[0][3] = bean.getEmpId();
      String upGoal = "  UPDATE HRMS_GOAL_EMP_HDR SET GOAL_RATING=?, FINAL_RATING=? WHERE GOAL_HDR_ID=? AND EMP_ID=?";
      getSqlModel().singleExecute(upGoal, updateObj);
    }
  }

  public void setGoalCategory(EmpGoalAssessment empGoalAssessment)
  {
    try {
      TreeMap map = new TreeMap();
      String sql = " SELECT GOAL_CATEGORY_ID,GOAL_CATEGORY FROM HRMS_GOAL_CATEGORIES WHERE GOAL_ID=" + empGoalAssessment.getConfgoalId() + " ORDER BY GOAL_CATEGORY_ID";
      Object[][] categoryData = getSqlModel().getSingleResult(sql);
      if ((categoryData != null) && (categoryData.length > 0))
      {
        for (int j = 0; j < categoryData.length; j++)
        {
          map.put(String.valueOf(categoryData[j][0]), 
            String.valueOf(categoryData[j][1]));
        }
      }

      empGoalAssessment.setTmap(map);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}