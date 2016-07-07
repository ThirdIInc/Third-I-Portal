package org.paradyne.model.PAS.Competency;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.PAS.Competency.SelfCompentencyAssesmentApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;

public class SelfCompentencyAssesmentApprovalModel extends ModelBase
{
  static HashMap<String, Object[][]> map;

  public void getPendingRecords(SelfCompentencyAssesmentApproval selfApprovalBean, HttpServletRequest request)
  {
    try
    {
      String pendingRecordsQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID,  HRMS_COMP_CONFIG.COMP_NAME, HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID, HRMS_EMP_OFFC.EMP_FNAME||' ' ||HRMS_EMP_OFFC.EMP_LNAME,  TO_CHAR(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE,'DD-MM-YYYY'),   HRMS_DEPT.DEPT_NAME, HRMS_RANK.RANK_NAME, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL   FROM HRMS_COMP_EMP_ASSESSMENT_HDR   INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID)  INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID)  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = 'P' AND   HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = " + 
        selfApprovalBean.getUserEmpId();
      Object[][] pendingRecordsObj = getSqlModel().getSingleResult(pendingRecordsQuery);
      List pendingList = new ArrayList();
      String[] pageIndexDrafted = Utility.doPaging(selfApprovalBean.getMyPendingPage(), pendingRecordsObj.length, 20);
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
        selfApprovalBean.setMyPendingPage("1");
      }
      if ((pendingRecordsObj != null) && (pendingRecordsObj.length > 0)) {
        selfApprovalBean.setPendingRecordListFlag(true);
        for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
          SelfCompentencyAssesmentApproval beanItt = new SelfCompentencyAssesmentApproval();
          beanItt.setCompetencyAssesmentIDItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][0])));
          beanItt.setCompetencyIDItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][1])));
          beanItt.setCompetencyNameItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][2])));
          beanItt.setCompetencyAssesmentEmployeeIDItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][3])));
          beanItt.setCompetencyAssesmentEmployeeNameItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][4])));
          beanItt.setCompetencyAssesmentReviewDateItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][5])));
          beanItt.setCompetencyAssesmentDepartmentItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][6])));
          beanItt.setCompetencyAssesmentDesignationItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][7])));
          beanItt.setCompetencyAssesmentLevelItr(Utility.checkNull(String.valueOf(pendingRecordsObj[i][8])));
          pendingList.add(beanItt);
        }
        selfApprovalBean.setPendingRecordList(pendingList);
      } else {
        selfApprovalBean.setPendingEmptyFlag(true);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getProcessedRecords(SelfCompentencyAssesmentApproval selfApprovalBean, HttpServletRequest request)
  {
    try
    {
      String processedRecordsQuery = "SELECT DISTINCT HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID,  HRMS_COMP_CONFIG.COMP_NAME, HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID, HRMS_EMP_OFFC.EMP_FNAME||' ' ||HRMS_EMP_OFFC.EMP_LNAME,  TO_CHAR(HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_DATE,'DD-MM-YYYY'),   HRMS_DEPT.DEPT_NAME, HRMS_RANK.RANK_NAME, TO_CHAR(HRMS_COMP_EMP_ASSESSMENT_HDR.ASSESSMENT_COMPLETED_DATE,'DD-MM-YYYY') , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL   FROM HRMS_COMP_EMP_ASSESSMENT_HDR   INNER JOIN HRMS_COMP_EMP_ASSESSMENT_DTL ON (HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID) INNER JOIN HRMS_COMP_CONFIG ON HRMS_COMP_CONFIG.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID  INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID)  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID = " + 
        selfApprovalBean.getUserEmpId();
      Object[][] processedListRecordsObj = getSqlModel().getSingleResult(processedRecordsQuery);
      List processedList = new ArrayList();
      String[] pageIndexDrafted = Utility.doPaging(selfApprovalBean.getMyProcessedPage(), processedListRecordsObj.length, 20);
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
        selfApprovalBean.setMyProcessedPage("1");
      }

      if ((processedListRecordsObj != null) && (processedListRecordsObj.length > 0)) {
        selfApprovalBean.setProcessedRecordListFlag(true);
        for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
          SelfCompentencyAssesmentApproval beanItt = new SelfCompentencyAssesmentApproval();
          beanItt.setCompetencyAssesmentIDItr(Utility.checkNull(String.valueOf(processedListRecordsObj[i][0])));
          beanItt.setCompetencyIDItr(Utility.checkNull(String.valueOf(processedListRecordsObj[i][1])));
          beanItt.setCompetencyNameItr(Utility.checkNull(String.valueOf(processedListRecordsObj[i][2])));
          beanItt.setCompetencyAssesmentEmployeeIDItr(Utility.checkNull(String.valueOf(processedListRecordsObj[i][3])));
          beanItt.setCompetencyAssesmentEmployeeNameItr(Utility.checkNull(String.valueOf(processedListRecordsObj[i][4])));
          beanItt.setCompetencyAssesmentReviewDateItr(Utility.checkNull(String.valueOf(processedListRecordsObj[i][5])));
          beanItt.setCompetencyAssesmentDepartmentItr(Utility.checkNull(String.valueOf(processedListRecordsObj[i][6])));
          beanItt.setCompetencyAssesmentDesignationItr(Utility.checkNull(String.valueOf(processedListRecordsObj[i][7])));
          beanItt.setCompetencyAssesmentReviewCompletedDateItr(Utility.checkNull(String.valueOf(processedListRecordsObj[i][8])));
          beanItt.setCompetencyAssesmentLevelItr(Utility.checkNull(String.valueOf(processedListRecordsObj[i][9])));
          processedList.add(beanItt);
        }
        selfApprovalBean.setProcessedRecordList(processedList);
      } else {
        selfApprovalBean.setProcessedEmptyFlag(true);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getReassessedRecords(SelfCompentencyAssesmentApproval selfApprovalBean, HttpServletRequest request)
  {
    try
    {
      String reassessedRecordsQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID,  HRMS_COMP_CONFIG.COMP_NAME, HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID, HRMS_EMP_OFFC.EMP_FNAME||' ' ||HRMS_EMP_OFFC.EMP_LNAME,  TO_CHAR(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE,'DD-MM-YYYY'),  HRMS_DEPT.DEPT_NAME, HRMS_RANK.RANK_NAME, TO_CHAR(HRMS_COMP_EMP_ASSESSMENT_HDR.ASSESSMENT_COMPLETED_DATE,'DD-MM-YYYY'), HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_APPROVER_LEVEL   FROM HRMS_COMP_EMP_ASSESSMENT_HDR   INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID)  INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID)  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)  INNER JOIN HRMS_COMP_ASSESSMENT_APPR_DTL ON (HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID AND  HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ASSESSMENT_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID AND  HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID )  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = 'A' AND  HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_APPROVER_ID = " + 
        selfApprovalBean.getUserEmpId() + " AND  " + 
        " HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_SIGNOFF_ACCEPTANCE = 'N'  " + 
        " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_REASSESSMENT_STATUS = 'N' " + 
        " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_REVIEWER_COMMENTS IS NOT NULL" + 
        " ORDER BY HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID,TO_CHAR(HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_DATE,'DD-MM-YYYY')";
      Object[][] reassessedRecordsObj = getSqlModel().getSingleResult(reassessedRecordsQuery);
      List reassessedList = new ArrayList();
      String[] pageIndexDrafted = Utility.doPaging(selfApprovalBean.getMyPage(), reassessedRecordsObj.length, 20);
      if (pageIndexDrafted == null) {
        pageIndexDrafted[0] = "0";
        pageIndexDrafted[1] = "20";
        pageIndexDrafted[2] = "1";
        pageIndexDrafted[3] = "1";
        pageIndexDrafted[4] = "";
      }

      request.setAttribute("totalReAssessedPage", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndexDrafted[2]))));
      request.setAttribute("reAssessedPageNo", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndexDrafted[3]))));
      if (pageIndexDrafted[4].equals("1")) {
        selfApprovalBean.setMyPage("1");
      }
      if ((reassessedRecordsObj != null) && (reassessedRecordsObj.length > 0)) {
        selfApprovalBean.setReAssessedRecordListFlag(true);
        for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
          SelfCompentencyAssesmentApproval beanItt = new SelfCompentencyAssesmentApproval();
          beanItt.setCompetencyAssesmentIDItr(Utility.checkNull(String.valueOf(reassessedRecordsObj[i][0])));
          beanItt.setCompetencyIDItr(Utility.checkNull(String.valueOf(reassessedRecordsObj[i][1])));
          beanItt.setCompetencyNameItr(Utility.checkNull(String.valueOf(reassessedRecordsObj[i][2])));
          beanItt.setCompetencyAssesmentEmployeeIDItr(Utility.checkNull(String.valueOf(reassessedRecordsObj[i][3])));
          beanItt.setCompetencyAssesmentEmployeeNameItr(Utility.checkNull(String.valueOf(reassessedRecordsObj[i][4])));
          beanItt.setCompetencyAssesmentReviewDateItr(Utility.checkNull(String.valueOf(reassessedRecordsObj[i][5])));
          beanItt.setCompetencyAssesmentDepartmentItr(Utility.checkNull(String.valueOf(reassessedRecordsObj[i][6])));
          beanItt.setCompetencyAssesmentDesignationItr(Utility.checkNull(String.valueOf(reassessedRecordsObj[i][7])));
          beanItt.setCompetencyAssesmentReviewCompletedDateItr(Utility.checkNull(String.valueOf(reassessedRecordsObj[i][8])));
          beanItt.setCompetencyAssesmentLevelItr(Utility.checkNull(String.valueOf(reassessedRecordsObj[i][9])));
          reassessedList.add(beanItt);
        }
        selfApprovalBean.setReAssessedRecordList(reassessedList);
      } else {
        selfApprovalBean.setReAssessedEmptyFlag(true);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getAllRatingsAndComments(SelfCompentencyAssesmentApproval selfApprovalBean, String competencyAssesmentIDItr, String competencyAssesmentEmployeeIDItr)
  {
    try
    {
      String allRatingCommetsQuery = "SELECT HRMS_COMPETENCY_HDR.COMPETENCY_TITLE as Competency, HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL as Proficiency_Level, HRMS_COMP_SELF_ASSESMENT.COMP_RATING as emp_rating, HRMS_COMP_SELF_ASSESMENT.COMP_ID  FROM HRMS_COMP_SELF_ASSESMENT  INNER JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = " + 
        competencyAssesmentIDItr + 
        " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr;
      Object[][] allRatingCommentsObj = getSqlModel().getSingleResult(allRatingCommetsQuery);
      if ((allRatingCommentsObj != null) && (allRatingCommentsObj.length > 0)) {
        selfApprovalBean.setAllRatingCommentsPageFlag(true);
        if ((selfApprovalBean.getListType().equals("pending")) || (selfApprovalBean.getListType().trim().equals(""))) {
          selfApprovalBean.setNonProcessListPageFlag(true);
        }

        if (selfApprovalBean.getListType().equals("processed")) {
          selfApprovalBean.setProcessedListPageFlag(true);
        }

        List allRatingCommentsList = new ArrayList();
        for (int i = 0; i < allRatingCommentsObj.length; i++) {
          SelfCompentencyAssesmentApproval beanItr = new SelfCompentencyAssesmentApproval();
          beanItr.setAllRatingCommentsCompetencyNameItr(Utility.checkNull(String.valueOf(allRatingCommentsObj[i][0])));
          beanItr.setAllRatingCommentsProficiencyLevelItr(Utility.checkNull(String.valueOf(allRatingCommentsObj[i][1])));
          beanItr.setAllRatingCommentsEmpRatingItr(Utility.checkNull(String.valueOf(allRatingCommentsObj[i][2])));
          beanItr.setAllRatingCommentsCompetencyIDItr(Utility.checkNull(String.valueOf(allRatingCommentsObj[i][3])));
          String approverRatingCommentsQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT FROM HRMS_COMP_EMP_ASSESSMENT_DTL  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
            competencyAssesmentIDItr + 
            " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE = " + Utility.checkNull(String.valueOf(allRatingCommentsObj[i][3])) + 
            " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr + 
            " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID = " + selfApprovalBean.getUserEmpId();
          Object[][] approverRatingCommentsObj = getSqlModel().getSingleResult(approverRatingCommentsQuery);
          if ((approverRatingCommentsObj != null) && (approverRatingCommentsObj.length > 0)) {
            beanItr.setAllRatingCommentsSelfApproverRatingItr(Utility.checkNull(String.valueOf(approverRatingCommentsObj[0][0])));
            beanItr.setAllRatingCommentsSelfApproverCommentsItr(Utility.checkNull(String.valueOf(approverRatingCommentsObj[0][1])));
          }
          allRatingCommentsList.add(beanItr);
          if (selfApprovalBean.getListType().equals("processed")) {
            beanItr.setProcessedListPageFlag(true);
          }
        }
        selfApprovalBean.setAllRatingAndCommentsIterator(allRatingCommentsList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getApproverRatingsAndCommentsInProcessedList(SelfCompentencyAssesmentApproval selfApprovalBean, String competencyAssesmentIDItr, String competencyAssesmentEmployeeIDItr, HttpServletRequest request)
  {
    try
    {
      if ((selfApprovalBean.getListType().equals("pending")) || (selfApprovalBean.getListType().trim().equals(""))) {
        selfApprovalBean.setNonProcessListPageFlag(true);
      }

      if (selfApprovalBean.getListType().equals("processed")) {
        selfApprovalBean.setProcessedListPageFlag(true);
      }

      if (selfApprovalBean.getListType().equals("reAssessed")) {
        selfApprovalBean.setNonProcessListPageFlag(true);
      }

      String checkDataQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT FROM HRMS_COMP_EMP_ASSESSMENT_DTL  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        competencyAssesmentIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID = " + selfApprovalBean.getUserEmpId();
      Object[][] checkDataObj = getSqlModel().getSingleResult(checkDataQuery);
      String competencyQuery = "";
      Object[][] noOfCompObj = (Object[][])null;
      HashMap competencyMap = null;
      if ((checkDataObj != null) && (checkDataObj.length > 0)) {
        competencyQuery = "SELECT HRMS_COMP_SELF_ASSESMENT.COMP_ID, ROWNUM, HRMS_COMPETENCY_HDR.COMPETENCY_TITLE , HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL,  HRMS_COMP_SELF_ASSESMENT.COMP_RATING, HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS,   HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT , HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING  FROM HRMS_COMP_SELF_ASSESMENT    INNER JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)   INNER JOIN HRMS_COMP_EMP_ASSESSMENT_DTL ON (HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID  AND HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID = " + 
          selfApprovalBean.getUserEmpId() + ")" + 
          " WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = " + competencyAssesmentIDItr + 
          " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr;
        noOfCompObj = getSqlModel().getSingleResult(competencyQuery);
        competencyMap = getSqlModel().getSingleResultMap(competencyQuery, 0, 2);
      } else {
        competencyQuery = "SELECT HRMS_COMP_SELF_ASSESMENT.COMP_ID, ROWNUM, HRMS_COMPETENCY_HDR.COMPETENCY_TITLE , HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL,  HRMS_COMP_SELF_ASSESMENT.COMP_RATING, HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS FROM HRMS_COMP_SELF_ASSESMENT   INNER JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = " + 
          competencyAssesmentIDItr + 
          " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr;
        noOfCompObj = getSqlModel().getSingleResult(competencyQuery);
        competencyMap = getSqlModel().getSingleResultMap(competencyQuery, 0, 2);
      }

      String approverQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE, HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING  FROM HRMS_COMP_EMP_ASSESSMENT_DTL  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID)   WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        competencyAssesmentIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID NOT IN( " + selfApprovalBean.getUserEmpId() + ")" + 
        " ORDER BY HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE DESC";
      HashMap approverMap = getSqlModel().getSingleResultMap(approverQuery, 0, 2);

      String numberOfApproverQuery = " SELECT DISTINCT HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID  FROM HRMS_COMP_EMP_ASSESSMENT_DTL   WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        competencyAssesmentIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID NOT IN( " + selfApprovalBean.getUserEmpId() + ")";
      Object[][] numberOfApproverObj = getSqlModel().getSingleResult(numberOfApproverQuery);

      int totalRows = 0;

      int totalapprovers = 0;
      if ((noOfCompObj != null) && (noOfCompObj.length > 0)) {
        selfApprovalBean.setPendingEmpRatingCommentsPreviousApproverRatingCommentsFlag(true);
        totalRows = noOfCompObj.length;
      }
      if ((numberOfApproverObj != null) && (numberOfApproverObj.length > 0)) {
        totalapprovers = numberOfApproverObj.length;
      }

      Object[][] compObj = new Object[totalRows][7];
      Object[][] competencyCodeObj = new Object[totalRows][1];

      Object[][] approverObj = new Object[totalRows][totalapprovers * 2];

      String[][] headerObj = new String[totalRows][7 + totalapprovers * 2];

      if ((noOfCompObj != null) && (noOfCompObj.length > 0)) {
        for (int i = 0; i < noOfCompObj.length; i++) {
          headerObj[0][0] = "Sr. No.";
          headerObj[0][1] = "Competency";
          headerObj[0][2] = "Profeciency Level";
          headerObj[0][3] = "Employee Rating";
          headerObj[0][4] = "Employee Comments";
          headerObj[0][5] = "Approver Comments";
          headerObj[0][6] = "Approver Rating";

          Object[][] innerCompObj = (Object[][])null;
          try {
            innerCompObj = (Object[][])competencyMap.get(String.valueOf(noOfCompObj[i][0]));
            competencyCodeObj[i][0] = String.valueOf(noOfCompObj[i][0]);
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

                  if (count % 2 == 0) {
                    headerObj[0][(7 + count)] = ("Approver" + noOfAppr);
                    noOfAppr++;
                  } else {
                    headerObj[0][(7 + count)] = "Rating";
                  }

                  count++;
                }
            }
          }
          catch (Exception e) {
            e.printStackTrace();
          }

        }

        Object[][] finalObj = (Object[][])null;
        Object[][] fullyFinalObj = (Object[][])null;
        if ((compObj != null) && (compObj.length > 0)) {
          finalObj = compObj;
        }
        if ((approverObj != null) && (approverObj.length > 0)) {
          finalObj = approverObj;
        }
        if ((compObj != null) && (compObj.length > 0) && (approverObj != null) && (approverObj.length > 0)) {
          finalObj = Utility.joinArrays(compObj, approverObj, 0, 0);
        }

        if ((finalObj != null) && (finalObj.length > 0)) {
          fullyFinalObj = Utility.joinArrays(finalObj, competencyCodeObj, 0, 0);
        }

        request.setAttribute("headerDataObj", headerObj);
        request.setAttribute("approverDataObj", fullyFinalObj);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getProcessedApproverRatingsAndCommentsInProcessedList(SelfCompentencyAssesmentApproval selfApprovalBean, String competencyAssesmentIDItr, String competencyAssesmentEmployeeIDItr, HttpServletRequest request)
  {
    try
    {
      if (selfApprovalBean.getListType().equals("processed")) {
        selfApprovalBean.setProcessedListPageFlag(true);
      }

      String competencyQuery = "SELECT HRMS_COMP_SELF_ASSESMENT.COMP_ID, ROWNUM, HRMS_COMPETENCY_HDR.COMPETENCY_TITLE , HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL,  HRMS_COMP_SELF_ASSESMENT.COMP_RATING, HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS FROM HRMS_COMP_SELF_ASSESMENT   INNER JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = " + 
        competencyAssesmentIDItr + 
        " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr;
      Object[][] noOfCompObj = getSqlModel().getSingleResult(competencyQuery);
      HashMap competencyMap = getSqlModel().getSingleResultMap(competencyQuery, 0, 2);

      String approverQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE,HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING,HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT,NVL(COMP_DEV_PLAN,' ') , TO_CHAR(COMP_DEV_TARGET_DATE,'DD-MM-YYYY')  FROM HRMS_COMP_EMP_ASSESSMENT_DTL  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID)    LEFT JOIN HRMS_COMPETENCY_DEV_PLAN ON(HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_DEV_CODE=HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE)  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        competencyAssesmentIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr + 
        " ORDER BY HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE DESC";

      HashMap approverMap = getSqlModel().getSingleResultMap(approverQuery, 0, 2);

      String numberOfApproverQuery = " SELECT DISTINCT HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID  FROM HRMS_COMP_EMP_ASSESSMENT_DTL   WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        competencyAssesmentIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr;
      Object[][] numberOfApproverObj = getSqlModel().getSingleResult(numberOfApproverQuery);

      int totalRows = 0;

      int totalapprovers = 0;
      if ((noOfCompObj != null) && (noOfCompObj.length > 0)) {
        selfApprovalBean.setProcessedEmpRatingCommentsPreviousApproverRatingCommentsFlag(true);
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
          competencyAssesmentIDItr + "  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr;
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

  public void getPreviousApproverRatingsAndComments(SelfCompentencyAssesmentApproval selfApprovalBean, String competencyAssesmentIDItr, String competencyAssesmentEmployeeIDItr, int level)
  {
    try
    {
      String previousApprRatingCommetsQuery = " SELECT HRMS_COMPETENCY_HDR.COMPETENCY_TITLE, HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL, HRMS_COMP_SELF_ASSESMENT.COMP_RATING, HRMS_COMP_SELF_ASSESMENT.COMP_ID,  HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT  FROM HRMS_COMP_EMP_ASSESSMENT_DTL INNER JOIN HRMS_COMP_SELF_ASSESMENT ON (HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID) INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE)    INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID) WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        competencyAssesmentIDItr + " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + competencyAssesmentEmployeeIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID NOT IN(" + selfApprovalBean.getUserEmpId() + ")";
      Object[][] previousApprRatingCommentsObj = getSqlModel().getSingleResult(previousApprRatingCommetsQuery);
      if ((previousApprRatingCommentsObj != null) && (previousApprRatingCommentsObj.length > 0)) {
        List previousApprRatingCommentsList = new ArrayList();
        selfApprovalBean.setPreviousApproverRatingCommentsPageFlag(true);
        selfApprovalBean.setPreviousApproverRatingCommentsPageFlag(true);
        for (int i = 0; i < previousApprRatingCommentsObj.length; i++) {
          SelfCompentencyAssesmentApproval beanItr = new SelfCompentencyAssesmentApproval();
          beanItr.setAllRatingCommentsCompetencyNameItr(Utility.checkNull(String.valueOf(previousApprRatingCommentsObj[i][0])));
          beanItr.setAllRatingCommentsProficiencyLevelItr(Utility.checkNull(String.valueOf(previousApprRatingCommentsObj[i][1])));
          beanItr.setAllRatingCommentsEmpRatingItr(Utility.checkNull(String.valueOf(previousApprRatingCommentsObj[i][2])));
          beanItr.setAllRatingCommentsCompetencyIDItr(Utility.checkNull(String.valueOf(previousApprRatingCommentsObj[i][3])));
          beanItr.setPreviousApproverName(Utility.checkNull(String.valueOf(previousApprRatingCommentsObj[i][4])));
          beanItr.setAllRatingCommentsSelfApproverRatingItr(Utility.checkNull(String.valueOf(previousApprRatingCommentsObj[i][5])));
          beanItr.setAllRatingCommentsSelfApproverCommentsItr(Utility.checkNull(String.valueOf(previousApprRatingCommentsObj[i][6])));
          previousApprRatingCommentsList.add(beanItr);
        }
        selfApprovalBean.setPreviousApproverRatingCommentsIterator(previousApprRatingCommentsList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object[][] getSelfCompetencyCode(HttpServletRequest request, SelfCompentencyAssesmentApproval selfApprovalBean, String approverCompetencyAssesmentCode, String approverCompetencyEmployeeCode)
  {
    Object[][] cometencyIDFromMap = (Object[][])null;
    try {
      String query = " SELECT ROWNUM, HRMS_COMP_SELF_ASSESMENT.COMP_ID FROM HRMS_COMP_SELF_ASSESMENT WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID=" + approverCompetencyAssesmentCode + " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + approverCompetencyEmployeeCode;
      map = getSqlModel().getSingleResultMap(query, 0, 2);
      cometencyIDFromMap = (Object[][])map.get("1");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return cometencyIDFromMap;
  }

  public void getCompetencyHeaderAndLevel(HttpServletRequest request, SelfCompentencyAssesmentApproval selfApprovalBean, String approverCompetencyID)
  {
    try
    {
      selfApprovalBean.setDetailPageFlag(true);
      selfApprovalBean.setAllRatingCommentsPageFlag(false);
      String headerDataQuery = "SELECT HRMS_COMPETENCY_HDR.COMPETENCY_TITLE, HRMS_COMPETENCY_HDR.COMPETENCY_DESC, HRMS_COMPETENCY_HDR.COMPETENCY_INDICATOR FROM HRMS_COMPETENCY_HDR  WHERE HRMS_COMPETENCY_HDR.COMPETENCY_CODE=" + 
        approverCompetencyID;
      Object[][] headerDataObj = getSqlModel().getSingleResult(headerDataQuery);
      if ((headerDataObj != null) && (headerDataObj.length > 0)) {
        selfApprovalBean.setApproverCompetencyTitle(Utility.checkNull(String.valueOf(headerDataObj[0][0])));
        selfApprovalBean.setApproverCompetencyDescription(Utility.checkNull(String.valueOf(headerDataObj[0][1])));
        selfApprovalBean.setApproverCompetencyIndicator(Utility.checkNull(String.valueOf(headerDataObj[0][2])));
      }

      String detailCompetencyDataQuery = "SELECT DISTINCT HRMS_COMPETENCY_DTL.COMPETENCY_CODE,  HRMS_COMPETENCY_DTL.COMPETENCY_TITLE, HRMS_COMPETENCY_DTL.COMPETENCY_LEVEL_DESC,HRMS_COMPETENCY_DTL.COMPETENCY_LEVEL , CASE WHEN HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL IS NULL THEN 'N' ELSE 'Y' END FROM HRMS_COMPETENCY_DTL LEFT JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMPETENCY_DTL.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE AND HRMS_COMPETENCY_DTL.COMPETENCY_LEVEL=HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL AND HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE = (SELECT HRMS_EMP_OFFC.EMP_RANK FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID = " + 
        selfApprovalBean.getApproverCompetencyEmployeeCode() + ")) " + 
        "WHERE HRMS_COMPETENCY_DTL.COMPETENCY_CODE= " + approverCompetencyID + "  " + 
        "ORDER BY HRMS_COMPETENCY_DTL.COMPETENCY_LEVEL";
      Object[][] detailCompetencyDataObj = getSqlModel().getSingleResult(detailCompetencyDataQuery);
      if ((detailCompetencyDataObj != null) && (detailCompetencyDataObj.length > 0))
        request.setAttribute("detailCompetencyDataObj", detailCompetencyDataObj);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean insertAllRatingAndComments(SelfCompentencyAssesmentApproval selfApprovalBean, String[] allRatingCommentsCompetencyIDItr, String[] allRatingCommentsSelfApproverCommentsItr, String[] allRatingCommentsSelfApproverRatingItr)
  {
    boolean result = false;
    try
    {
      String checkAvailableDataQuery = "SELECT * FROM HRMS_COMP_EMP_ASSESSMENT_DTL WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + selfApprovalBean.getApproverCompetencyAssesmentCode() + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + selfApprovalBean.getApproverCompetencyEmployeeCode() + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID = " + selfApprovalBean.getUserEmpId();
      Object[][] checkAvailableDataObj = getSqlModel().getSingleResult(checkAvailableDataQuery);
      if ((checkAvailableDataObj == null) || (checkAvailableDataObj.length == 0)) {
        Object[][] insertData = new Object[allRatingCommentsCompetencyIDItr.length][6];
        String insertCompetencyDetailsQuery = "";
        if (allRatingCommentsCompetencyIDItr != null) {
          for (int i = 0; i < allRatingCommentsCompetencyIDItr.length; i++) {
            insertData[i][0] = selfApprovalBean.getApproverCompetencyAssesmentCode();
            insertData[i][1] = String.valueOf(allRatingCommentsCompetencyIDItr[i]);
            insertData[i][2] = String.valueOf(allRatingCommentsSelfApproverRatingItr[i]);
            insertData[i][3] = String.valueOf(allRatingCommentsSelfApproverCommentsItr[i]);
            insertData[i][4] = selfApprovalBean.getApproverCompetencyEmployeeCode();
            insertData[i][5] = selfApprovalBean.getUserEmpId();
            insertCompetencyDetailsQuery = " INSERT INTO HRMS_COMP_EMP_ASSESSMENT_DTL (HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID, HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID)  VALUES(?, ?, ?, ?, ?, ?)";
          }

          result = getSqlModel().singleExecute(insertCompetencyDetailsQuery, insertData);
        }
      }
      else if (allRatingCommentsCompetencyIDItr != null) {
        Object[][] updateData = new Object[allRatingCommentsCompetencyIDItr.length][6];
        String updateCompetencyDetailsQuery = "";
        for (int i = 0; i < allRatingCommentsCompetencyIDItr.length; i++) {
          updateData[i][0] = String.valueOf(allRatingCommentsSelfApproverRatingItr[i]);
          updateData[i][1] = String.valueOf(allRatingCommentsSelfApproverCommentsItr[i]);
          updateData[i][2] = selfApprovalBean.getApproverCompetencyAssesmentCode();
          updateData[i][3] = String.valueOf(allRatingCommentsCompetencyIDItr[i]);
          updateData[i][4] = selfApprovalBean.getApproverCompetencyEmployeeCode();
          updateData[i][5] = selfApprovalBean.getUserEmpId();
          updateCompetencyDetailsQuery = " UPDATE HRMS_COMP_EMP_ASSESSMENT_DTL SET HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING = ? , HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT = ?  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE = ?  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID = ? ";
        }

        result = getSqlModel().singleExecute(updateCompetencyDetailsQuery, updateData);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return result;
  }

  public void getEmployeeSelfRatingAndComments(SelfCompentencyAssesmentApproval selfApprovalBean, String approverCompetencyID, String approverCompetencyAssesmentCode, String approverCompetencyEmployeeCode)
  {
    try
    {
      String employeeSelfRatingAndCommentsQuery = " SELECT HRMS_COMP_SELF_ASSESMENT.COMP_RATING, HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS FROM HRMS_COMP_SELF_ASSESMENT  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ID= " + 
        approverCompetencyID + " AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = " + approverCompetencyAssesmentCode + " and HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID =  " + approverCompetencyEmployeeCode;
      Object[][] employeeSelfRatingAndCommentsObj = getSqlModel().getSingleResult(employeeSelfRatingAndCommentsQuery);
      if ((employeeSelfRatingAndCommentsObj != null) && (employeeSelfRatingAndCommentsObj.length > 0)) {
        selfApprovalBean.setEmployeeSelfRating(Utility.checkNull(String.valueOf(employeeSelfRatingAndCommentsObj[0][0])));
        selfApprovalBean.setEmployeeSelfComments(Utility.checkNull(String.valueOf(employeeSelfRatingAndCommentsObj[0][1])));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean updateCompetencyDetails(SelfCompentencyAssesmentApproval selfApprovalBean)
  {
    boolean result = false;
    try {
      Object[][] updateData = new Object[1][10];
      updateData[0][0] = selfApprovalBean.getApproverSelfRating();
      updateData[0][1] = selfApprovalBean.getApproverSelfComments();
      updateData[0][2] = selfApprovalBean.getDevRequired();
      updateData[0][3] = selfApprovalBean.getDevPlanCode();
      updateData[0][4] = selfApprovalBean.getDevTargetDate();
      updateData[0][5] = selfApprovalBean.getApproverdevPlanComment();
      updateData[0][6] = selfApprovalBean.getApproverCompetencyAssesmentCode();
      updateData[0][7] = selfApprovalBean.getApproverCompetencyID();
      updateData[0][8] = selfApprovalBean.getApproverCompetencyEmployeeCode();
      updateData[0][9] = selfApprovalBean.getUserEmpId();
      String query = " SELECT ROWNUM, HRMS_COMP_SELF_ASSESMENT.COMP_ID FROM HRMS_COMP_SELF_ASSESMENT WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID=" + selfApprovalBean.getApproverCompetencyAssesmentCode() + " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + selfApprovalBean.getApproverCompetencyEmployeeCode();
      Object[][] obj1 = getSqlModel().getSingleResult(query);
      if ((obj1 != null) && (obj1.length > 0)) {
        for (int i = 0; i < obj1.length; i++) {
          Object[][] compObj = new Object[1][2];
          compObj[0][0] = String.valueOf(i + 1);
          compObj[0][1] = String.valueOf(obj1[i][1]);
          map.put(String.valueOf(i + 1), compObj);
        }

      }

      String updateCompetencyDetailsQuery = " UPDATE HRMS_COMP_EMP_ASSESSMENT_DTL SET HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING = ? , HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT = ?   ,IS_COMP_DEV_PLAN=?, COMP_DEV_CODE=?,COMP_DEV_TARGET_DATE=TO_DATE(?,'DD-MM-YYYY'),COMP_DEV_COMMENT=?  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE = ?  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID = ? ";

      result = getSqlModel().singleExecute(" UPDATE HRMS_COMP_EMP_ASSESSMENT_DTL SET HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING = ? , HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT = ?   ,IS_COMP_DEV_PLAN=?, COMP_DEV_CODE=?,COMP_DEV_TARGET_DATE=TO_DATE(?,'DD-MM-YYYY'),COMP_DEV_COMMENT=?  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE = ?  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID = ? ", updateData);

      Object[][] obj = (Object[][])map.get(String.valueOf(selfApprovalBean.getApproverCompetencyKey()));
      if (obj != null)
        selfApprovalBean.setApproverCompetencyID(String.valueOf(obj[0][1]));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public void getCurrentStausFromSaveAndNextPage(SelfCompentencyAssesmentApproval selfApprovalBean, String approverCompetencyAssesmentCode, String approverCompetencyKey)
  {
    try
    {
      Object[][] nextKeyAndIdObj = returnCompetencyInfo(selfApprovalBean, selfApprovalBean.getApproverCompetencyKey());
      if (String.valueOf(nextKeyAndIdObj[0][0]).equals(String.valueOf(nextKeyAndIdObj[0][2])))
        selfApprovalBean.setFinalPageFlag(true);
      else
        selfApprovalBean.setFinalPageFlag(false);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void getApproverRatingAndCommentsIfAlreadyGiven(SelfCompentencyAssesmentApproval selfApprovalBean)
  {
    try
    {
      String approverRatingAndCommentsQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT   ,NVL(IS_COMP_DEV_PLAN,'N'), HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_DEV_CODE,NVL(COMP_DEV_PLAN,' ') , TO_CHAR(COMP_DEV_TARGET_DATE,'DD-MM-YYYY'),nvl(COMP_DEV_COMMENT,'')  FROM HRMS_COMP_EMP_ASSESSMENT_DTL LEFT JOIN HRMS_COMPETENCY_DEV_PLAN ON(HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_DEV_CODE=HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE)  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        selfApprovalBean.getApproverCompetencyAssesmentCode() + " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE = " + selfApprovalBean.getApproverCompetencyID() + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + selfApprovalBean.getApproverCompetencyEmployeeCode() + " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID = " + selfApprovalBean.getUserEmpId();
      Object[][] approverRatingAndCommentsObj = getSqlModel().getSingleResult(approverRatingAndCommentsQuery);
      selfApprovalBean.setDevRequired("");
      selfApprovalBean.setDevPlanCode("");
      selfApprovalBean.setDevPlanName("");
      selfApprovalBean.setDevTargetDate("");
      selfApprovalBean.setApproverdevPlanComment("");
      if ((approverRatingAndCommentsObj != null) && (approverRatingAndCommentsObj.length > 0)) {
        selfApprovalBean.setApproverSelfRating(Utility.checkNull(String.valueOf(approverRatingAndCommentsObj[0][0])));
        selfApprovalBean.setApproverSelfComments(Utility.checkNull(String.valueOf(approverRatingAndCommentsObj[0][1])));
        selfApprovalBean.setDevRequired(Utility.checkNull(String.valueOf(approverRatingAndCommentsObj[0][2])));
        selfApprovalBean.setDevPlanCode(Utility.checkNull(String.valueOf(approverRatingAndCommentsObj[0][3])));
        selfApprovalBean.setDevPlanName(Utility.checkNull(String.valueOf(approverRatingAndCommentsObj[0][4])));
        selfApprovalBean.setDevTargetDate(Utility.checkNull(String.valueOf(approverRatingAndCommentsObj[0][5])));
        selfApprovalBean.setApproverdevPlanComment(Utility.checkNull(String.valueOf(approverRatingAndCommentsObj[0][6])));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean changeApproverAssesmentCompetencyStatus(SelfCompentencyAssesmentApproval selfApprovalBean, Object[][] empFlow, int level, String reportingType)
  {
    String statusToUpdate = "";
    String nextApprover = "";
    String levelToUpdate = "";
    boolean result = false;
    try
    {
      String escalationAndSignWorkflowQuery = "SELECT HRMS_COMP_CONFIG.COMP_SIGNOFF_WORKFLOW, HRMS_COMP_CONFIG.COMP_ESCALATION_WORKFLOW FROM HRMS_COMP_CONFIG WHERE HRMS_COMP_CONFIG.COMP_ID = " + selfApprovalBean.getCompetencyMasterCode();
      Object[][] escalationAndSignWorkflowObj = getSqlModel().getSingleResult(escalationAndSignWorkflowQuery);
      boolean signWorkflowStatus = false;
      boolean escalationWorkflowStatus = false;
      String signOffString = "";
      String escalationString = "";
      if ((escalationAndSignWorkflowObj != null) && (escalationAndSignWorkflowObj.length > 0)) {
        signOffString = Utility.checkNull(String.valueOf(escalationAndSignWorkflowObj[0][0]));
        escalationString = Utility.checkNull(String.valueOf(escalationAndSignWorkflowObj[0][1]));
        if (("Y".equals(signOffString)) && ("N".equals(escalationString))) {
          signWorkflowStatus = true;
        }

        if (("Y".equals(signOffString)) && ("Y".equals(escalationString))) {
          signWorkflowStatus = true;
          escalationWorkflowStatus = true;
        }

      }

      if ((!signWorkflowStatus) && (!escalationWorkflowStatus)) {
        if ((empFlow != null) && (empFlow.length > 0)) {
          statusToUpdate = "P";
          nextApprover = String.valueOf(empFlow[0][0]);
          levelToUpdate = String.valueOf(level);

          if (reportingType.equals("goal"))
          {
            if (String.valueOf(empFlow[0][6]).equals("R")) {
              statusToUpdate = "P";
              nextApprover = String.valueOf(empFlow[0][0]);
              levelToUpdate = String.valueOf(level);
            }
          }
          else
          {
            statusToUpdate = "A";
            nextApprover = selfApprovalBean.getUserEmpId();
            levelToUpdate = String.valueOf(level - 1);
          }
        }
        else {
          statusToUpdate = "A";
          nextApprover = selfApprovalBean.getUserEmpId();
          levelToUpdate = String.valueOf(level - 1);
        }

        Object[][] updateData = new Object[1][7];
        updateData[0][0] = statusToUpdate;
        updateData[0][1] = nextApprover;
        updateData[0][2] = levelToUpdate;
        updateData[0][3] = selfApprovalBean.getCompetencyMasterCode();
        updateData[0][4] = selfApprovalBean.getApproverCompetencyEmployeeCode();
        updateData[0][5] = selfApprovalBean.getApproverCompetencyAssesmentCode();
        updateData[0][6] = selfApprovalBean.getUserEmpId();
        String updateCompetencyStatusQuery = "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.ASSESSMENT_COMPLETED_DATE = SYSDATE  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ? ";

        result = getSqlModel().singleExecute(
          "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.ASSESSMENT_COMPLETED_DATE = SYSDATE  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ? ", updateData);
      }

      if ((signWorkflowStatus) && (!escalationWorkflowStatus)) {
        if ((empFlow != null) && (empFlow.length > 0)) {
          statusToUpdate = "P";
          nextApprover = String.valueOf(empFlow[0][0]);
          levelToUpdate = String.valueOf(level);

          if (reportingType.equals("goal"))
          {
            if (String.valueOf(empFlow[0][6]).equals("R")) {
              selfApprovalBean.setFinalPageSignOffFlag(true);
              selfApprovalBean.setFinalPageFlag(false);
              statusToUpdate = "P";
              nextApprover = selfApprovalBean.getUserEmpId();
              levelToUpdate = String.valueOf(level - 1);
            }
          }
          else
          {
            selfApprovalBean.setFinalPageSignOffFlag(true);
            selfApprovalBean.setFinalPageFlag(false);
            statusToUpdate = "P";
            nextApprover = selfApprovalBean.getUserEmpId();
            levelToUpdate = String.valueOf(level - 1);
          }
        }
        else {
          selfApprovalBean.setFinalPageSignOffFlag(true);
          selfApprovalBean.setFinalPageFlag(false);
          statusToUpdate = "P";
          nextApprover = selfApprovalBean.getUserEmpId();
          levelToUpdate = String.valueOf(level - 1);
        }

        Object[][] updateData = new Object[1][7];
        updateData[0][0] = statusToUpdate;
        updateData[0][1] = nextApprover;
        updateData[0][2] = levelToUpdate;
        updateData[0][3] = selfApprovalBean.getCompetencyMasterCode();
        updateData[0][4] = selfApprovalBean.getApproverCompetencyEmployeeCode();
        updateData[0][5] = selfApprovalBean.getApproverCompetencyAssesmentCode();
        updateData[0][6] = selfApprovalBean.getUserEmpId();
        String updateCompetencyStatusQuery = "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.ASSESSMENT_COMPLETED_DATE = SYSDATE  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ? ";

        result = getSqlModel().singleExecute(
          "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.ASSESSMENT_COMPLETED_DATE = SYSDATE  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ? ", updateData);
      }

      if ((signWorkflowStatus) && (escalationWorkflowStatus)) {
        if ((empFlow != null) && (empFlow.length > 0)) {
          statusToUpdate = "P";
          nextApprover = String.valueOf(empFlow[0][0]);
          levelToUpdate = String.valueOf(level);
          if (reportingType.equals("goal"))
          {
            if (String.valueOf(empFlow[0][6]).equals("R")) {
              levelToUpdate = String.valueOf(level - 1);
              if (selfApprovalBean.getListType().equals("reAssessed"))
                selfApprovalBean.setFinalPageSignOffFlag(false);
              else {
                selfApprovalBean.setFinalPageSignOffFlag(true);
              }
              selfApprovalBean.setFinalPageFlag(false);
              String updateReviewerID = "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_REVIEWER_ID = " + nextApprover + 
                " WHERE COMP_ID = " + selfApprovalBean.getCompetencyMasterCode() + " " + 
                " AND EMP_ID = " + selfApprovalBean.getApproverCompetencyEmployeeCode() + " " + 
                " AND COMP_ASSESSMENT_ID = " + selfApprovalBean.getApproverCompetencyAssesmentCode();
              getSqlModel().singleExecute(updateReviewerID);
            }
          }

        }

        Object[][] updateData = new Object[1][7];
        updateData[0][0] = statusToUpdate;
        updateData[0][1] = nextApprover;
        updateData[0][2] = levelToUpdate;
        updateData[0][3] = selfApprovalBean.getCompetencyMasterCode();
        updateData[0][4] = selfApprovalBean.getApproverCompetencyEmployeeCode();
        updateData[0][5] = selfApprovalBean.getApproverCompetencyAssesmentCode();
        updateData[0][6] = selfApprovalBean.getUserEmpId();
        String updateCompetencyStatusQuery = "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.ASSESSMENT_COMPLETED_DATE = SYSDATE  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ? ";

        result = getSqlModel().singleExecute(
          "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL = ?  , HRMS_COMP_EMP_ASSESSMENT_HDR.ASSESSMENT_COMPLETED_DATE = SYSDATE  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID = ? ", updateData);

        if ("reAssessed".equals(selfApprovalBean.getListType())) {
          String updateApprovalDtlQuery = "UPDATE HRMS_COMP_ASSESSMENT_APPR_DTL  SET HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_REASSESSMENT_STATUS ='Y'  WHERE HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ASSESSMENT_ID = " + 
            selfApprovalBean.getApproverCompetencyAssesmentCode() + 
            " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_ID = " + selfApprovalBean.getApproverCompetencyEmployeeCode() + 
            " AND COMP_APPROVER_LEVEL = " + selfApprovalBean.getCompetencyAssesmentLevel() + 
            " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_APPROVER_ID = " + selfApprovalBean.getUserEmpId();
          getSqlModel().singleExecute(updateApprovalDtlQuery);
        } else {
          Object[][] insertData = new Object[1][6];
          insertData[0][0] = selfApprovalBean.getCompetencyMasterCode();
          insertData[0][1] = selfApprovalBean.getApproverCompetencyAssesmentCode();
          insertData[0][2] = statusToUpdate;
          insertData[0][3] = selfApprovalBean.getUserEmpId();
          insertData[0][4] = selfApprovalBean.getApproverCompetencyEmployeeCode();
          insertData[0][5] = String.valueOf(level - 1);
          String insertescalQuery = "INSERT INTO HRMS_COMP_ASSESSMENT_APPR_DTL(COMP_ID, COMP_ASSESSMENT_ID, COMP_ASSESSMENT_STATUS, COMP_APPROVER_ID, ASSESSMENT_COMPLETED_DATE, COMP_EMP_ID, COMP_APPROVER_LEVEL)  VALUES (?,?,?,?,SYSDATE,?,?)";

          result = getSqlModel().singleExecute("INSERT INTO HRMS_COMP_ASSESSMENT_APPR_DTL(COMP_ID, COMP_ASSESSMENT_ID, COMP_ASSESSMENT_STATUS, COMP_APPROVER_ID, ASSESSMENT_COMPLETED_DATE, COMP_EMP_ID, COMP_APPROVER_LEVEL)  VALUES (?,?,?,?,SYSDATE,?,?)", 
            insertData);
        }
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return result;
  }

  public void competencyTitleDropDown(HttpServletRequest request, SelfCompentencyAssesmentApproval selfApprovalBean, String approverCompetencyAssesmentCode, String employeeCode)
  {
    TreeMap competencyTitleDropDownmap = new TreeMap();
    try {
      String query = " SELECT ROWNUM ||'-'|| HRMS_COMP_SELF_ASSESMENT.COMP_ID, HRMS_COMPETENCY_HDR.COMPETENCY_TITLE FROM HRMS_COMP_SELF_ASSESMENT  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)   WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID= " + 
        approverCompetencyAssesmentCode + 
        " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + employeeCode;
      Object[][] selfCompTitleObj = getSqlModel().getSingleResult(query);
      if ((selfCompTitleObj != null) && (selfCompTitleObj.length > 0))
      {
        request.setAttribute("dropDownTitle", selfCompTitleObj);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getEmployeeDetails(SelfCompentencyAssesmentApproval selfApprovalBean, String competencyAssesmentEmployeeID)
  {
    try
    {
      String empQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  NVL(HRMS_CENTER.CENTER_NAME,' '), NVL(HRMS_DEPT.DEPT_NAME,'  '), NVL(HRMS_RANK.RANK_NAME,'  '),  (AS2.TITLE_NAME||' '||AS1.EMP_FNAME||' '||NVL(AS1.EMP_MNAME,'')|| ' ' || AS1.EMP_LNAME)  FROM HRMS_EMP_OFFC    LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)    LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)    LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)    LEFT JOIN HRMS_EMP_OFFC AS1 ON (HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = AS1.EMP_ID)  LEFT JOIN HRMS_TITLE AS2  ON(AS1.EMP_TITLE_CODE = AS2.TITLE_CODE)    WHERE HRMS_EMP_OFFC.EMP_ID = " + 
        competencyAssesmentEmployeeID;
      Object[][] empObjects = getSqlModel().getSingleResult(empQuery);
      if ((empObjects != null) && (empObjects.length > 0)) {
        selfApprovalBean.setEmpId(Utility.checkNull(String.valueOf(empObjects[0][0])));
        selfApprovalBean.setEmpToken(Utility.checkNull(String.valueOf(empObjects[0][1])));
        selfApprovalBean.setEmpName(Utility.checkNull(String.valueOf(empObjects[0][2])));
        selfApprovalBean.setEmpBranch(Utility.checkNull(String.valueOf(empObjects[0][3])));
        selfApprovalBean.setEmpDepartment(Utility.checkNull(String.valueOf(empObjects[0][4])));
        selfApprovalBean.setEmpDesignation(Utility.checkNull(String.valueOf(empObjects[0][5])));
        selfApprovalBean.setEmpReportingTo(Utility.checkNull(String.valueOf(empObjects[0][6])));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean signOffRecord(SelfCompentencyAssesmentApproval selfApprovalBean)
  {
    boolean result = false;
    try {
      Object[][] updateStatusData = new Object[1][5];
      updateStatusData[0][0] = "A";
      updateStatusData[0][1] = "Y";
      updateStatusData[0][2] = selfApprovalBean.getCompetencyMasterCode();
      updateStatusData[0][3] = selfApprovalBean.getApproverCompetencyEmployeeCode();
      updateStatusData[0][4] = selfApprovalBean.getApproverCompetencyAssesmentCode();

      String updateStatusQuery = "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = ?, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_IS_SIGNOFF = ?  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = ? ";

      result = getSqlModel().singleExecute("UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = ?, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_IS_SIGNOFF = ?  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID = ?  AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID = ? ", 
        updateStatusData);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public void setMappedCategory(SelfCompentencyAssesmentApproval selfApprovalBean, String approverCompetencyID)
  {
    try
    {
      String mappedDesignationQuery = "SELECT HRMS_COMP_CATEGORIES.COMP_CATEGORY FROM HRMS_COMPETENCY_ROLE  INNER JOIN HRMS_COMP_CATEGORIES ON (HRMS_COMP_CATEGORIES.COMP_CATEGORY_ID = HRMS_COMPETENCY_ROLE.COMPETENCY_TYPE)  WHERE HRMS_COMPETENCY_ROLE.COMPETENCY_CODE = " + 
        approverCompetencyID;
      Object[][] mappedDesignationObj = getSqlModel()
        .getSingleResult(mappedDesignationQuery);
      if ((mappedDesignationObj != null) && (mappedDesignationObj.length > 0))
        selfApprovalBean.setMappedCategory(Utility.checkNull(
          String.valueOf(mappedDesignationObj[0][0])));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object[][] returnCompetencyInfo(SelfCompentencyAssesmentApproval selfApprovalBean, String competencyKey)
  {
    Object[][] result = (Object[][])null;
    Object[][] compQueryObj = getSqlModel().getSingleResult(" SELECT ROWNUM, HRMS_COMP_SELF_ASSESMENT.COMP_ID FROM HRMS_COMP_SELF_ASSESMENT  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID=" + 
      selfApprovalBean.getApproverCompetencyAssesmentCode() + 
      " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + selfApprovalBean.getApproverCompetencyEmployeeCode());
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