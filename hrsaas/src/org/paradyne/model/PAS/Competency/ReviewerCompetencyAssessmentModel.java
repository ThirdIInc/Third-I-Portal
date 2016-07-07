package org.paradyne.model.PAS.Competency;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.PAS.Competency.ReviewerCompetencyAssessment;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;

public class ReviewerCompetencyAssessmentModel extends ModelBase
{
  public void getPendingListData(ReviewerCompetencyAssessment reviewerBean, HttpServletRequest request)
  {
    try
    {
      String pendingRecordsQuery = "SELECT DISTINCT HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID,  HRMS_COMP_CONFIG.COMP_NAME, HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID, HRMS_EMP_OFFC.EMP_FNAME||' ' ||HRMS_EMP_OFFC.EMP_LNAME,  TO_CHAR(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE,'DD-MM-YYYY'),   HRMS_DEPT.DEPT_NAME, HRMS_RANK.RANK_NAME, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL   FROM HRMS_COMP_EMP_ASSESSMENT_HDR   INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID)  INNER JOIN HRMS_COMP_ASSESSMENT_APPR_DTL ON (HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ASSESSMENT_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID) INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID)  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS IN ('P','A') AND   HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_REVIEWER_STATUS='N' AND  HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_REVIEWER_ID = " + 
        reviewerBean.getUserEmpId() + 
        " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_SIGNOFF_ACCEPTANCE IS NOT NULL ";
      Object[][] pendingRecordsObj = getSqlModel().getSingleResult(pendingRecordsQuery);
      List pendingList = new ArrayList();
      String[] pageIndexDrafted = Utility.doPaging(reviewerBean.getMyPage(), pendingRecordsObj.length, 20);
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
        reviewerBean.setMyPage("1");
      }

      if ((pendingRecordsObj != null) && (pendingRecordsObj.length > 0)) {
        reviewerBean.setPendingListLengthFlag(true);
        for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
          ReviewerCompetencyAssessment beanItt = new ReviewerCompetencyAssessment();
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
        reviewerBean.setPendingIteratorList(pendingList);
      } else {
        reviewerBean.setPendingListLengthFlag(false);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getProcessedListData(ReviewerCompetencyAssessment reviewerBean, HttpServletRequest request)
  {
    try
    {
      String processedRecordsQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID,  HRMS_COMP_CONFIG.COMP_NAME, HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID, HRMS_EMP_OFFC.EMP_FNAME||' ' ||HRMS_EMP_OFFC.EMP_LNAME,  TO_CHAR(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE,'DD-MM-YYYY'),   HRMS_DEPT.DEPT_NAME, HRMS_RANK.RANK_NAME, HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL   FROM HRMS_COMP_EMP_ASSESSMENT_HDR   INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID)  INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID)  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = 'A' AND   HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_REVIEWER_STATUS='Y' AND  HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_REVIEWER_ID = " + 
        reviewerBean.getUserEmpId();
      Object[][] processedRecordsObj = getSqlModel().getSingleResult(processedRecordsQuery);
      List processedList = new ArrayList();
      String[] pageIndexDrafted = Utility.doPaging(reviewerBean.getMyProcessedPage(), processedRecordsObj.length, 20);
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
        reviewerBean.setMyProcessedPage("1");
      }

      if ((processedRecordsObj != null) && (processedRecordsObj.length > 0)) {
        reviewerBean.setProcessedListLengthFlag(true);
        for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
          ReviewerCompetencyAssessment beanItt = new ReviewerCompetencyAssessment();
          beanItt.setCompetencyAssesmentIDItr(Utility.checkNull(String.valueOf(processedRecordsObj[i][0])));
          beanItt.setCompetencyIDItr(Utility.checkNull(String.valueOf(processedRecordsObj[i][1])));
          beanItt.setCompetencyNameItr(Utility.checkNull(String.valueOf(processedRecordsObj[i][2])));
          beanItt.setCompetencyAssesmentEmployeeIDItr(Utility.checkNull(String.valueOf(processedRecordsObj[i][3])));
          beanItt.setCompetencyAssesmentEmployeeNameItr(Utility.checkNull(String.valueOf(processedRecordsObj[i][4])));
          beanItt.setCompetencyAssesmentReviewDateItr(Utility.checkNull(String.valueOf(processedRecordsObj[i][5])));
          beanItt.setCompetencyAssesmentDepartmentItr(Utility.checkNull(String.valueOf(processedRecordsObj[i][6])));
          beanItt.setCompetencyAssesmentDesignationItr(Utility.checkNull(String.valueOf(processedRecordsObj[i][7])));
          beanItt.setCompetencyAssesmentLevelItr(Utility.checkNull(String.valueOf(processedRecordsObj[i][8])));
          processedList.add(beanItt);
        }
        reviewerBean.setProcessedIteratorList(processedList);
      } else {
        reviewerBean.setPendingListLengthFlag(false);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getApproverRatingsAndComments(ReviewerCompetencyAssessment reviewerBean, String competencyAssementIDItr, String competencyAssessmentEmployeeID, HttpServletRequest request)
  {
    try
    {
      String competencyQuery = "SELECT HRMS_COMP_SELF_ASSESMENT.COMP_ID, ROWNUM, HRMS_COMPETENCY_HDR.COMPETENCY_TITLE , HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL,  HRMS_COMP_SELF_ASSESMENT.COMP_RATING, HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS FROM HRMS_COMP_SELF_ASSESMENT   INNER JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)  WHERE HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = " + 
        competencyAssementIDItr + 
        " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + competencyAssessmentEmployeeID;
      Object[][] noOfCompObj = getSqlModel().getSingleResult(competencyQuery);
      HashMap competencyMap = getSqlModel().getSingleResultMap(competencyQuery, 0, 2);

      String approverQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE, HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME, HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING  FROM HRMS_COMP_EMP_ASSESSMENT_DTL  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID)   WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        competencyAssementIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + competencyAssessmentEmployeeID + 
        " ORDER BY HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE DESC";
      HashMap approverMap = getSqlModel().getSingleResultMap(approverQuery, 0, 2);

      String numberOfApproverQuery = " SELECT DISTINCT HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID  FROM HRMS_COMP_EMP_ASSESSMENT_DTL   WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
        competencyAssementIDItr + 
        " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + competencyAssessmentEmployeeID;
      Object[][] numberOfApproverObj = getSqlModel().getSingleResult(numberOfApproverQuery);

      int totalRows = 0;

      int totalapprovers = 0;
      if ((noOfCompObj != null) && (noOfCompObj.length > 0)) {
        totalRows = noOfCompObj.length;
      }
      if ((numberOfApproverObj != null) && (numberOfApproverObj.length > 0)) {
        totalapprovers = numberOfApproverObj.length;
      }

      Object[][] compObj = new Object[totalRows][5];

      Object[][] approverObj = new Object[totalRows][totalapprovers * 2];

      String[][] headerObj = new String[totalRows][5 + totalapprovers * 2];

      if ((noOfCompObj != null) && (noOfCompObj.length > 0)) {
        for (int i = 0; i < noOfCompObj.length; i++) {
          headerObj[0][0] = "Sr. No.";
          headerObj[0][1] = "Competency";
          headerObj[0][2] = "Profeciency Level";
          headerObj[0][3] = "Rating";
          headerObj[0][4] = "Comments";

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

                  if (count % 2 == 0) {
                    headerObj[0][(5 + count)] = ("Approver" + noOfAppr);
                    noOfAppr++;
                  } else {
                    headerObj[0][(5 + count)] = "Rating";
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
        request.setAttribute("approverDataObj", finalObj);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getEmployeeDetails(ReviewerCompetencyAssessment reviewerBean, String competencyAssessmentEmployeeIDItr)
  {
    try
    {
      String empQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  NVL(HRMS_CENTER.CENTER_NAME,' '), NVL(HRMS_DEPT.DEPT_NAME,'  '), NVL(HRMS_RANK.RANK_NAME,'  '),  (AS2.TITLE_NAME||' '||AS1.EMP_FNAME||' '||NVL(AS1.EMP_MNAME,'')|| ' ' || AS1.EMP_LNAME)  FROM HRMS_EMP_OFFC    LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)    LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)    LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)    LEFT JOIN HRMS_EMP_OFFC AS1 ON (HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = AS1.EMP_ID)  LEFT JOIN HRMS_TITLE AS2  ON(AS1.EMP_TITLE_CODE = AS2.TITLE_CODE)    WHERE HRMS_EMP_OFFC.EMP_ID = " + 
        competencyAssessmentEmployeeIDItr;
      Object[][] empObjects = getSqlModel().getSingleResult(empQuery);
      if ((empObjects != null) && (empObjects.length > 0)) {
        reviewerBean.setEmpId(Utility.checkNull(String.valueOf(empObjects[0][0])));
        reviewerBean.setEmpToken(Utility.checkNull(String.valueOf(empObjects[0][1])));
        reviewerBean.setEmpName(Utility.checkNull(String.valueOf(empObjects[0][2])));
        reviewerBean.setEmpBranch(Utility.checkNull(String.valueOf(empObjects[0][3])));
        reviewerBean.setEmpDepartment(Utility.checkNull(String.valueOf(empObjects[0][4])));
        reviewerBean.setEmpDesignation(Utility.checkNull(String.valueOf(empObjects[0][5])));
        reviewerBean.setEmpReportingTo(Utility.checkNull(String.valueOf(empObjects[0][6])));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getReviewDetailsTableData(ReviewerCompetencyAssessment reviewerBean, String competencyAssessmentID, String competencyAssessmentEmployeeID)
  {
    try
    {
      String reviewQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_COMMENTS, HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_SIGNOFF_ACCEPTANCE, HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_REVIEWER_COMMENTS  FROM HRMS_COMP_ASSESSMENT_APPR_DTL  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_APPROVER_ID)  WHERE HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_ASSESSMENT_ID= " + 
        competencyAssessmentID + " AND HRMS_COMP_ASSESSMENT_APPR_DTL.COMP_EMP_ID = " + competencyAssessmentEmployeeID;
      Object[][] reviewObj = getSqlModel().getSingleResult(reviewQuery);
      if ((reviewObj != null) && (reviewObj.length > 0)) {
        List reviewDataList = new ArrayList();
        for (int i = 0; i < reviewObj.length; i++) {
          ReviewerCompetencyAssessment innerBean = new ReviewerCompetencyAssessment();
          innerBean.setReviewApproverIDItr(Utility.checkNull(String.valueOf(reviewObj[i][0])));
          innerBean.setReviewApproverTokenItr(Utility.checkNull(String.valueOf(reviewObj[i][1])));
          innerBean.setReviewApproverNameItr(Utility.checkNull(String.valueOf(reviewObj[i][2])));
          innerBean.setReviewEmployeeCommentsItr(Utility.checkNull(String.valueOf(reviewObj[i][3])));
          innerBean.setReviewAgreeDisAgreeFlag(Utility.checkNull(String.valueOf(reviewObj[i][4])));

          innerBean.setListType(reviewerBean.getListType());

          innerBean.setReviewReviewerCommentsItr(Utility.checkNull(String.valueOf(reviewObj[i][5])));
          reviewDataList.add(innerBean);
        }
        reviewerBean.setReviewEmployeeIterator(reviewDataList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean updateApproverDetails(ReviewerCompetencyAssessment reviewerCompBean, String[] reviewApproverIDItr, String[] reviewReviewerCommentsItr)
  {
    boolean result = false;
    try {
      if ((reviewApproverIDItr != null) && (reviewApproverIDItr.length > 0)) {
        Object[][] dataToUpdate = new Object[reviewReviewerCommentsItr.length][4];
        for (int i = 0; i < reviewReviewerCommentsItr.length; i++) {
          dataToUpdate[i][0] = reviewReviewerCommentsItr[i];
          dataToUpdate[i][1] = reviewerCompBean.getCompetencyAssementID();
          dataToUpdate[i][2] = reviewApproverIDItr[i];
          dataToUpdate[i][3] = reviewerCompBean.getMasterCompetencyCode();
        }
        String updateQuery = "UPDATE HRMS_COMP_ASSESSMENT_APPR_DTL SET  COMP_REVIEWER_COMMENTS = ? WHERE COMP_ASSESSMENT_ID = ? AND COMP_APPROVER_ID = ? AND COMP_ID = ?";
        result = getSqlModel().singleExecute("UPDATE HRMS_COMP_ASSESSMENT_APPR_DTL SET  COMP_REVIEWER_COMMENTS = ? WHERE COMP_ASSESSMENT_ID = ? AND COMP_APPROVER_ID = ? AND COMP_ID = ?", dataToUpdate);

        if (result) {
          String query = "UPDATE HRMS_COMP_EMP_ASSESSMENT_HDR SET COMP_REVIEW_DATE = SYSDATE, COMP_REVIEWER_STATUS = 'Y'  WHERE COMP_ASSESSMENT_ID = " + 
            reviewerCompBean.getCompetencyAssementID() + " AND COMP_REVIEWER_ID = " + reviewerCompBean.getUserEmpId();
          result = getSqlModel().singleExecute(query);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}