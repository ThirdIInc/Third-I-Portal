package org.paradyne.model.PAS.GoalSetting;

import java.awt.Color;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.EmployeeGoalSetting;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.ReportGenerator;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.model.common.ReportingModel;

public class EmployeeGoalSettingModel extends ModelBase
{
  static Logger logger = Logger.getLogger(EmployeeGoalSettingModel.class);

  public void getEmployeeDetails(EmployeeGoalSetting bean)
  {
    String empQuery = "SELECT EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID = " + 
      bean.getUserEmpId();
    Object[][] empObj = getSqlModel().getSingleResult(empQuery);

    bean.setEmpCode(Utility.checkNull(String.valueOf(empObj[0][0])));
    bean.setEmpToken(Utility.checkNull(String.valueOf(empObj[0][1])));
    bean.setEmpName(Utility.checkNull(String.valueOf(empObj[0][2])));
  }
  public void getEmployeeGoalDetails(EmployeeGoalSetting bean) {
    String empQuery = "SELECT HRMS_GOAL_EMP_HDR.EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_GOAL_EMP_HDR.GOAL_ID,GOAL_NAME, EMP_GOAL_APPROVAL_STATUS,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'), GOAL_HDR_ID,GOAL_LEVEL,NVL(GOAL_REPORTING,'reporting'),DECODE(IS_GOAL_CATEGORY,'N','false','Y','true','false') FROM HRMS_GOAL_EMP_HDR LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID)  LEFT JOIN HRMS_GOAL_CONFIG ON(HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID) WHERE GOAL_HDR_ID=" + 
      bean.getEmpGoalId();
    Object[][] empObj = getSqlModel().getSingleResult(empQuery);

    bean.setEmpCode(Utility.checkNull(String.valueOf(empObj[0][0])));
    bean.setEmpToken(Utility.checkNull(String.valueOf(empObj[0][1])));
    bean.setEmpName(Utility.checkNull(String.valueOf(empObj[0][2])));
    bean.setGoalPeriodId(Utility.checkNull(String.valueOf(empObj[0][3])));
    bean.setGoalPeriod(Utility.checkNull(String.valueOf(empObj[0][4])));
    bean.setApprovalStatus(Utility.checkNull(String.valueOf(empObj[0][5])));
    bean.setGoalFromDate(Utility.checkNull(String.valueOf(empObj[0][6])));
    bean.setGoalToDate(Utility.checkNull(String.valueOf(empObj[0][7])));
    bean.setEmpGoalId(Utility.checkNull(String.valueOf(empObj[0][8])));
    bean.setLevel(Utility.checkNull(String.valueOf(empObj[0][9])));
    bean.setReportingType(Utility.checkNull(String.valueOf(empObj[0][10])));
    bean.setIsGoalCategory(Utility.checkNull(String.valueOf(empObj[0][11])));
  }

  public void getDraftList(EmployeeGoalSetting bean, HttpServletRequest request)
  {
    String draftQuery = "SELECT GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),HRMS_GOAL_EMP_HDR.EMP_ID, GOAL_HDR_ID,NVL(GOAL_REPORTING,'reporting') FROM HRMS_GOAL_EMP_HDR LEFT JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID) LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) WHERE EMP_GOAL_APPROVAL_STATUS=1 AND HRMS_GOAL_EMP_HDR.EMP_ID=" + 
      bean.getUserEmpId();

    Object[][] draftData = getSqlModel().getSingleResult(draftQuery);

    ArrayList draftTableList = new ArrayList();
    if ((draftData != null) || (draftData.length > 0)) {
      bean.setNoDraftData("false");
      for (int i = 0; i < draftData.length; i++) {
        EmployeeGoalSetting beanList = new EmployeeGoalSetting();
        beanList.setGoalPeriodList(Utility.checkNull(String.valueOf(draftData[i][0])));
        beanList.setGoalFromDateList(Utility.checkNull(String.valueOf(draftData[i][1])));
        beanList.setGoalToDateList(Utility.checkNull(String.valueOf(draftData[i][2])));
        beanList.setEmpCodeList(Utility.checkNull(String.valueOf(draftData[i][3])));
        beanList.setDraftGoalNo(Utility.checkNull(String.valueOf(draftData[i][4])));
        beanList.setReportingType(Utility.checkNull(String.valueOf(draftData[i][5])));
        draftTableList.add(beanList);
      }
      bean.setDraftList(draftTableList);
    } else {
      bean.setNoDraftData("true");
    }
  }

  public void getApprovedList(EmployeeGoalSetting bean, HttpServletRequest request)
  {
    String approvedQuery = "SELECT GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),HRMS_GOAL_EMP_HDR.EMP_ID, GOAL_HDR_ID,NVL(GOAL_REPORTING,'reporting') FROM HRMS_GOAL_EMP_HDR LEFT JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID) LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) WHERE EMP_GOAL_APPROVAL_STATUS IN(3,5) AND HRMS_GOAL_EMP_HDR.EMP_ID=" + 
      bean.getUserEmpId();

    Object[][] approvedData = getSqlModel().getSingleResult(approvedQuery);

    String[] pageIndexApproved = Utility.doPaging(bean.getMyPage(), 
      approvedData.length, 20);
    if (pageIndexApproved == null) {
      pageIndexApproved[0] = "0";
      pageIndexApproved[1] = "20";
      pageIndexApproved[2] = "1";
      pageIndexApproved[3] = "1";
      pageIndexApproved[4] = "";
    }
    logger.info(" pageIndexApproved[0] ===" + pageIndexApproved[0]);
    logger.info(" bean.getMyPage(1)[0] ===" + bean.getMyPage());
    request.setAttribute("totalPage", Integer.valueOf(Integer.parseInt(
      String.valueOf(pageIndexApproved[2]))));
    request.setAttribute("pageNo", Integer.valueOf(Integer.parseInt(
      String.valueOf(pageIndexApproved[3]))));
    if (pageIndexApproved[4].equals("1")) {
      bean.setMyPage("1");
    }

    if ((approvedData != null) || (approvedData.length > 0)) {
      ArrayList approvedTableList = new ArrayList();
      int i = Integer.parseInt(pageIndexApproved[0]);
      for (; i < 
        Integer.parseInt(pageIndexApproved[1]); i++) {
        EmployeeGoalSetting beanList = new EmployeeGoalSetting();
        beanList.setGoalPeriodList(Utility.checkNull(String.valueOf(approvedData[i][0])));
        beanList.setGoalFromDateList(Utility.checkNull(String.valueOf(approvedData[i][1])));
        beanList.setGoalToDateList(Utility.checkNull(String.valueOf(approvedData[i][2])));
        beanList.setEmpCodeList(Utility.checkNull(String.valueOf(approvedData[i][3])));
        beanList.setApprovedGoalNo(Utility.checkNull(String.valueOf(approvedData[i][4])));
        beanList.setReportingType(Utility.checkNull(String.valueOf(approvedData[i][5])));
        approvedTableList.add(beanList);
      }
      bean.setApprovedList(approvedTableList);
      if (approvedTableList.size() > 0) {
        bean.setApproveLength("true");
      } else {
        bean.setApproveLength("false");
        bean.setMyPage("");
      }
    } else {
      bean.setApproveLength("false");
      bean.setMyPage("");
    }
  }

  public void getSubmitList(EmployeeGoalSetting bean, HttpServletRequest request)
  {
    String submitQuery = "SELECT GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),HRMS_GOAL_EMP_HDR.EMP_ID, GOAL_HDR_ID,NVL(GOAL_REPORTING,'reporting') FROM HRMS_GOAL_EMP_HDR LEFT JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID) LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) WHERE EMP_GOAL_APPROVAL_STATUS=2 AND HRMS_GOAL_EMP_HDR.EMP_ID=" + 
      bean.getUserEmpId();

    Object[][] submitData = getSqlModel().getSingleResult(submitQuery);

    ArrayList submitTableList = new ArrayList();
    if ((submitData != null) || (submitData.length > 0)) {
      for (int i = 0; i < submitData.length; i++) {
        EmployeeGoalSetting beanList = new EmployeeGoalSetting();
        beanList.setGoalPeriodList(Utility.checkNull(String.valueOf(submitData[i][0])));
        beanList.setGoalFromDateList(Utility.checkNull(String.valueOf(submitData[i][1])));
        beanList.setGoalToDateList(Utility.checkNull(String.valueOf(submitData[i][2])));
        beanList.setEmpCodeList(Utility.checkNull(String.valueOf(submitData[i][3])));
        beanList.setSubmitGoalNo(Utility.checkNull(String.valueOf(submitData[i][4])));
        beanList.setReportingType(Utility.checkNull(String.valueOf(submitData[i][5])));
        submitTableList.add(beanList);
      }
      bean.setSubmitList(submitTableList);
    }
  }

  public void getRevisedList(EmployeeGoalSetting bean, HttpServletRequest request)
  {
    String revisedQuery = "SELECT GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),HRMS_GOAL_EMP_HDR.EMP_ID, GOAL_HDR_ID,NVL(GOAL_REPORTING,'reporting') FROM HRMS_GOAL_EMP_HDR LEFT JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID) LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) WHERE EMP_GOAL_APPROVAL_STATUS=6 AND HRMS_GOAL_EMP_HDR.EMP_ID=" + 
      bean.getUserEmpId();

    Object[][] revisedData = getSqlModel().getSingleResult(revisedQuery);

    ArrayList revisedTableList = new ArrayList();
    if ((revisedData != null) || (revisedData.length > 0)) {
      for (int i = 0; i < revisedData.length; i++) {
        EmployeeGoalSetting beanList = new EmployeeGoalSetting();
        beanList.setGoalPeriodList(Utility.checkNull(String.valueOf(revisedData[i][0])));
        beanList.setGoalFromDateList(Utility.checkNull(String.valueOf(revisedData[i][1])));
        beanList.setGoalToDateList(Utility.checkNull(String.valueOf(revisedData[i][2])));
        beanList.setEmpCodeList(Utility.checkNull(String.valueOf(revisedData[i][3])));
        beanList.setRevisedGoalNo(Utility.checkNull(String.valueOf(revisedData[i][4])));
        beanList.setReportingType(Utility.checkNull(String.valueOf(revisedData[i][5])));
        revisedTableList.add(beanList);
      }
      bean.setRevisedList(revisedTableList);
    }
  }

  public void getReturnedList(EmployeeGoalSetting bean, HttpServletRequest request)
  {
    String returnedQuery = "SELECT GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),HRMS_GOAL_EMP_HDR.EMP_ID, GOAL_HDR_ID,NVL(GOAL_REPORTING,'reporting') FROM HRMS_GOAL_EMP_HDR LEFT JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID) LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) WHERE EMP_GOAL_APPROVAL_STATUS=4 AND HRMS_GOAL_EMP_HDR.EMP_ID=" + 
      bean.getUserEmpId();

    Object[][] returnedData = getSqlModel().getSingleResult(returnedQuery);

    ArrayList returnedTableList = new ArrayList();
    if ((returnedData != null) || (returnedData.length > 0)) {
      for (int i = 0; i < returnedData.length; i++) {
        EmployeeGoalSetting beanList = new EmployeeGoalSetting();
        beanList.setGoalPeriodList(Utility.checkNull(String.valueOf(returnedData[i][0])));
        beanList.setGoalFromDateList(Utility.checkNull(String.valueOf(returnedData[i][1])));
        beanList.setGoalToDateList(Utility.checkNull(String.valueOf(returnedData[i][2])));
        beanList.setEmpCodeList(Utility.checkNull(String.valueOf(returnedData[i][3])));
        beanList.setReturnedGoalNo(Utility.checkNull(String.valueOf(returnedData[i][4])));
        beanList.setReportingType(Utility.checkNull(String.valueOf(returnedData[i][5])));
        returnedTableList.add(beanList);
      }
      bean.setReturnList(returnedTableList);
    }
  }

  public void addGoals(EmployeeGoalSetting bean, HttpServletRequest request, String approverId)
  {
    if (bean.getParaId().equals("")) {
      boolean result = false;
      if (bean.getEmpGoalId().equals("")) {
        Object[][] maxEmpGoalIdObj = getSqlModel().getSingleResult("SELECT NVL(MAX(GOAL_HDR_ID),0)+1 FROM HRMS_GOAL_EMP_HDR");
        String maxEmpGoalId = String.valueOf(maxEmpGoalIdObj[0][0]);
        String hdrInsertQuery = "INSERT INTO HRMS_GOAL_EMP_HDR(GOAL_ID, EMP_ID, GOAL_HDR_ID, EMP_GOAL_APPROVAL_STATUS, GOAL_APPROVER_ID) VALUES(?,?,?,1,?)";

        Object[][] hdrInsertObj = new Object[1][4];
        hdrInsertObj[0][0] = bean.getGoalPeriodId();
        hdrInsertObj[0][1] = bean.getEmpCode();
        hdrInsertObj[0][2] = maxEmpGoalId;
        hdrInsertObj[0][3] = approverId;

        result = getSqlModel().singleExecute(hdrInsertQuery, hdrInsertObj);
        if (result)
          bean.setEmpGoalId(maxEmpGoalId); 
      } else {
        result = true;
      }if (result)
      {
        Object[][] insertDtlObj = new Object[1][6];
        insertDtlObj[0][0] = bean.getEmpGoalId();
        insertDtlObj[0][1] = bean.getGoalDesc();
        insertDtlObj[0][2] = bean.getGoalAchieveDate();
        insertDtlObj[0][3] = bean.getGoalWeightage();
        insertDtlObj[0][4] = bean.getGoalComments();
        insertDtlObj[0][5] = bean.getGoalCategoryCode();

        String insertDtlQuery = "INSERT INTO HRMS_GOAL_EMP_DTL(GOAL_HDR_ID, GOAL_DTL_ID, GOAL_DESCRIPTION, GOAL_ACHIEVEMENT_DATE ,GOAL_WEIGHTAGE,GOAL_COMMENTS,GOAL_CATEGORY_CODE)  VALUES(?,(SELECT NVL(MAX(GOAL_DTL_ID),0)+1 FROM HRMS_GOAL_EMP_DTL), ?,TO_DATE(?,'DD-MM-YYYY'),?,?,?)";

        result = getSqlModel().singleExecute(insertDtlQuery, insertDtlObj);
      }

    }
    else
    {
      Object[][] updateDtlObj = new Object[1][6];
      updateDtlObj[0][0] = bean.getGoalDesc();
      updateDtlObj[0][1] = bean.getGoalAchieveDate();
      updateDtlObj[0][2] = bean.getGoalWeightage();
      updateDtlObj[0][3] = bean.getGoalComments();
      updateDtlObj[0][4] = bean.getGoalCategoryCode();
      updateDtlObj[0][5] = bean.getParaId();

      String insertDtlQuery = "UPDATE HRMS_GOAL_EMP_DTL SET GOAL_DESCRIPTION=?, GOAL_ACHIEVEMENT_DATE=TO_DATE(?,'DD-MM-YYYY') ,GOAL_WEIGHTAGE=?,GOAL_COMMENTS=?  , GOAL_CATEGORY_CODE =? WHERE GOAL_DTL_ID=? ";

      getSqlModel().singleExecute(insertDtlQuery, updateDtlObj);
    }
    getGoalsList(bean, request);
  }

  public void getGoalDetailsEdit(String paraId, EmployeeGoalSetting goalSetting, HttpServletRequest request) {
    String selDtlQuery = " SELECT  GOAL_DTL_ID, GOAL_DESCRIPTION, TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY') ,GOAL_WEIGHTAGE,GOAL_COMMENTS,GOAL_CATEGORY_CODE FROM HRMS_GOAL_EMP_DTL where GOAL_DTL_ID=" + 
      paraId;
    Object[][] dtlGoal = getSqlModel().getSingleResult(selDtlQuery);
    if ((dtlGoal != null) && (dtlGoal.length > 0)) {
      goalSetting.setGoalAchieveDate(checkNull(String.valueOf(dtlGoal[0][2])));
      goalSetting.setGoalDesc(checkNull(String.valueOf(dtlGoal[0][1])));
      goalSetting.setGoalWeightage(checkNull(String.valueOf(dtlGoal[0][3])));
      goalSetting.setParaId(checkNull(String.valueOf(dtlGoal[0][0])));
      goalSetting.setGoalComments(checkNull(String.valueOf(dtlGoal[0][4])));
      goalSetting.setGoalCategoryCode(checkNull(String.valueOf(dtlGoal[0][5])));
    } else {
      goalSetting.setGoalAchieveDate("");
      goalSetting.setGoalDesc("");
      goalSetting.setGoalWeightage("");
      goalSetting.setParaId("");
      goalSetting.setGoalComments("");
      goalSetting.setGoalCategoryCode("");
    }

    getGoalsList(goalSetting, request);
  }
  public String checkNull(String result) {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public void showGoalsList(EmployeeGoalSetting bean, HttpServletRequest request) {
    String[] goalDescList = request.getParameterValues("goalDescList");
    String[] goalAchieveDateList = request.getParameterValues("goalAchieveDateList");

    String[] goalWeightageList = request.getParameterValues("goalWeightageList");

    String[] goalCommentsList = request.getParameterValues("goalCommentsList");
    ArrayList goalList = new ArrayList();
    try {
      if ((goalDescList != null) || (goalDescList.length > 0))
        for (int i = 0; i < goalAchieveDateList.length; i++) {
          EmployeeGoalSetting beanList = new EmployeeGoalSetting();
          beanList.setGoalDescList(goalDescList[i]);
          beanList.setGoalAchieveDateList(goalAchieveDateList[i]);
          beanList.setGoalWeightageList(goalWeightageList[i]);
          beanList.setGoalCommentsList(checkNull(goalCommentsList[i]));
          goalList.add(beanList);
        }
    }
    catch (Exception localException)
    {
    }
    bean.setEmpGoalList(goalList);
  }

  public void getGoalsList(EmployeeGoalSetting bean, HttpServletRequest request)
  {
    String goalListQuery = "SELECT GOAL_DESCRIPTION, TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY'),NVL(GOAL_WEIGHTAGE,0),NVL(GOAL_COMMENTS,' '), NVL(GOAL_DTL_STATUS,'A'),GOAL_DTL_ID,NVL(GOAL_CATEGORY,' '),GOAL_CATEGORY_CODE,nvl(GOAL_CATEGORY_WEIGHTAGE,0) FROM HRMS_GOAL_EMP_DTL  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID)  WHERE GOAL_DTL_STATUS='A' AND GOAL_HDR_ID=" + 
      bean.getEmpGoalId() + 
      " ORDER BY GOAL_CATEGORY_CODE,GOAL_ACHIEVEMENT_DATE ";

    Object[][] goalListObj = getSqlModel().getSingleResult(goalListQuery);
    double totalWeightage = 0.0D;
    ArrayList goalList = new ArrayList();
    try {
      if ((goalListObj != null) || (goalListObj.length > 0)) {
        for (int i = 0; i < goalListObj.length; i++) {
          EmployeeGoalSetting beanList = new EmployeeGoalSetting();
          beanList.setGoalDescList(String.valueOf(goalListObj[i][0]));
          beanList.setGoalAchieveDateList(checkNull(String.valueOf(goalListObj[i][1])));
          beanList.setGoalWeightageList(checkNull(String.valueOf(goalListObj[i][2])));
          beanList.setGoalCommentsList(checkNull(String.valueOf(goalListObj[i][3])));
          beanList.setGoalDTLStatusList(checkNull(String.valueOf(goalListObj[i][4])) + "_");
          beanList.setGoalDTLidList(checkNull(String.valueOf(goalListObj[i][5])));
          beanList.setGoalCategoryNameList(checkNull(String.valueOf(goalListObj[i][6])));
          beanList.setGoalCategoryCodeList(checkNull(String.valueOf(goalListObj[i][7])));
          beanList.setGoalCategoryWeightageList(checkNull(String.valueOf(goalListObj[i][8])));
          beanList.setIsGoalCategory(bean.getIsGoalCategory());
          if (!checkNull(String.valueOf(goalListObj[i][4])).equals("D")) {
            totalWeightage += Double.parseDouble(String.valueOf(String.valueOf(goalListObj[i][2])));
          }
          goalList.add(beanList);
        }
      }
      logger.info("totalWeightage===" + totalWeightage);
      bean.setTotalWeightage(String.valueOf(totalWeightage));
    }
    catch (Exception localException) {
    }
    bean.setEmpGoalList(goalList);
  }

  public void getApproverCommentList(EmployeeGoalSetting bean)
  {
    String approverListQuery = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(GOAL_APPR_DATE,'DD-MM-YYYY'),DECODE(GOAL_PATH_STATUS,'3','APPROVED','4','SENT BACK'), NVL(GOAL_APPR_COMMENTS,'') FROM HRMS_GOAL_PATH LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_APPROVER) WHERE GOAL_CODE=" + 
      bean.getEmpGoalId();
    Object[][] approverListObj = getSqlModel().getSingleResult(approverListQuery);

    ArrayList approverList = new ArrayList();
    try {
      if ((approverListObj != null) || (approverListObj.length > 0)) {
        for (int i = 0; i < approverListObj.length; i++) {
          EmployeeGoalSetting beanList = new EmployeeGoalSetting();
          beanList.setApproverName(Utility.checkNull(String.valueOf(approverListObj[i][0])));
          beanList.setApprovedDate(Utility.checkNull(String.valueOf(approverListObj[i][1])));
          beanList.setApproveStatus(Utility.checkNull(String.valueOf(approverListObj[i][2])));
          beanList.setApproverCommentList(Utility.checkNull(String.valueOf(approverListObj[i][3])));
          approverList.add(beanList);
        }
        if (approverList.size() > 0)
          bean.setCommentFlag("true");
        else
          bean.setCommentFlag("false");
      }
    }
    catch (Exception e) {
      bean.setCommentFlag("false");
    }
    bean.setApprList(approverList);
  }

  public boolean removeGoals(EmployeeGoalSetting bean, HttpServletRequest request)
  {
    String updateStatusQuery = "UPDATE HRMS_GOAL_EMP_DTL SET GOAL_DTL_STATUS='D' WHERE GOAL_DTL_ID=" + bean.getParaId();
    return getSqlModel().singleExecute(updateStatusQuery);
  }
  public boolean saveAsDraft(EmployeeGoalSetting bean, HttpServletRequest request, String approverId) {
    boolean result = false;
    if (bean.getEmpGoalId().equals("")) {
      Object[][] maxEmpGoalIdObj = getSqlModel().getSingleResult("SELECT NVL(MAX(GOAL_HDR_ID),0)+1 FROM HRMS_GOAL_EMP_HDR");
      String maxEmpGoalId = String.valueOf(maxEmpGoalIdObj[0][0]);
      String hdrInsertQuery = "INSERT INTO HRMS_GOAL_EMP_HDR(GOAL_ID, EMP_ID, GOAL_HDR_ID, EMP_GOAL_APPROVAL_STATUS, GOAL_APPROVER_ID) VALUES(?,?,?,1,?)";

      Object[][] hdrInsertObj = new Object[1][4];
      hdrInsertObj[0][0] = bean.getGoalPeriodId();
      hdrInsertObj[0][1] = bean.getEmpCode();
      hdrInsertObj[0][2] = maxEmpGoalId;
      hdrInsertObj[0][3] = approverId;

      result = getSqlModel().singleExecute(hdrInsertQuery, hdrInsertObj);
      if (result) {
        bean.setEmpGoalId(maxEmpGoalId);
        result = saveEmpGoalDetails(bean, request);
      }
    }
    else {
      String hdrUpdateQuery = "UPDATE HRMS_GOAL_EMP_HDR SET GOAL_ID=?, GOAL_APPROVER_ID=?, EMP_GOAL_APPROVAL_STATUS=1,GOAL_LEVEL=1 WHERE GOAL_HDR_ID=?";

      Object[][] hdrUpdateObj = new Object[1][3];
      hdrUpdateObj[0][0] = bean.getGoalPeriodId();
      hdrUpdateObj[0][1] = approverId;
      hdrUpdateObj[0][2] = bean.getEmpGoalId();

      result = getSqlModel().singleExecute(hdrUpdateQuery, hdrUpdateObj);
      if (result) {
        result = saveEmpGoalDetails(bean, request);
      }

    }

    return true;
  }

  public boolean saveEmpGoalDetails(EmployeeGoalSetting bean, HttpServletRequest request)
  {
    boolean result = false;
    String[] goalDescList = request.getParameterValues("goalDescList");
    String[] goalAchieveDateList = request.getParameterValues("goalAchieveDateList");

    String[] goalWeightageList = request.getParameterValues("goalWeightageList");
    String[] goalCommentsList = request.getParameterValues("goalCommentsList");
    String[] goalDTLStatus = request.getParameterValues("goalDTLStatusList");
    String[] goalCategoryCode = request.getParameterValues("goalCategoryCodeList");

    result = getSqlModel().singleExecute("DELETE FROM HRMS_GOAL_EMP_DTL WHERE GOAL_HDR_ID =" + bean.getEmpGoalId());
    int maxGoalDtlId = 1;
    if (result) {
      Object[][] maxGoalDtlObj = getSqlModel().getSingleResult("SELECT NVL(MAX(GOAL_DTL_ID),0)+1 FROM HRMS_GOAL_EMP_DTL");
      maxGoalDtlId = Integer.parseInt(String.valueOf(maxGoalDtlObj[0][0]));
    }
    String insertDtlQuery = "INSERT INTO HRMS_GOAL_EMP_DTL(GOAL_HDR_ID, GOAL_DTL_ID, GOAL_DESCRIPTION, GOAL_ACHIEVEMENT_DATE ,GOAL_WEIGHTAGE,GOAL_COMMENTS,GOAL_DTL_STATUS,GOAL_CATEGORY_CODE) VALUES(?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?)";

    Object[][] insertDtlObj = new Object[goalDescList.length][8];
    for (int i = 0; i < insertDtlObj.length; i++) {
      insertDtlObj[i][0] = bean.getEmpGoalId();
      insertDtlObj[i][1] = Integer.valueOf(maxGoalDtlId + i);
      insertDtlObj[i][2] = goalDescList[i];
      insertDtlObj[i][3] = goalAchieveDateList[i];
      insertDtlObj[i][4] = goalWeightageList[i];
      insertDtlObj[i][5] = goalCommentsList[i];
      insertDtlObj[i][6] = goalDTLStatus[i].replace("_", "");
      insertDtlObj[i][7] = goalCategoryCode[i];
    }
    if (result) {
      result = getSqlModel().singleExecute(insertDtlQuery, insertDtlObj);
    }

    return result;
  }

  public boolean updateApprovalStatus(String empGoalId, String status) {
    String updateQuery = "UPDATE HRMS_GOAL_EMP_HDR SET EMP_GOAL_APPROVAL_STATUS = " + status + " WHERE GOAL_HDR_ID = " + empGoalId;

    return getSqlModel().singleExecute(updateQuery);
  }

  public void insertApproverComments(EmployeeGoalSetting bean, String status) {
    String insertQuery = "INSERT INTO HRMS_GOAL_PATH (GOAL_CODE, GOAL_APPROVER, GOAL_APPR_DATE, GOAL_APPR_COMMENTS, GOAL_PATH_ID, GOAL_PATH_STATUS) VALUES (?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,(SELECT NVL(MAX(GOAL_PATH_ID),0)+1 FROM HRMS_GOAL_PATH WHERE GOAL_CODE = ?),?) ";

    Object[][] insertObj = new Object[1][5];
    insertObj[0][0] = bean.getEmpGoalId();
    insertObj[0][1] = bean.getUserEmpId();
    insertObj[0][2] = bean.getApproverComment();
    insertObj[0][3] = bean.getEmpGoalId();
    insertObj[0][4] = status;

    getSqlModel().singleExecute(insertQuery, insertObj);
  }

  public boolean saveAssesmentData(EmployeeGoalSetting bean)
  {
    String reviewDatesQuery = "SELECT TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_ASSESSMENT_CONFIG WHERE GOAL_ID =" + bean.getGoalPeriodId();
    Object[][] reviewDatesObj = getSqlModel().getSingleResult(reviewDatesQuery);
    int level = 1;
    String reportingType = bean.getReportingType();
    Object[][] empFlow = generateEmpFlow(bean.getEmpCode(), 1, reportingType);

    if ((reportingType.equals("goal")) && 
      (empFlow != null) && (empFlow.length > 0))
      while ((empFlow != null) && (empFlow.length > 0) && (String.valueOf(empFlow[0][3]).equals("N"))) {
        level++;
        empFlow = generateEmpFlow(bean.getEmpCode(), level, reportingType);
      }
    try
    {
      if ((empFlow != null) && (empFlow.length > 0)) {
        if ((reviewDatesObj != null) && (reviewDatesObj.length > 0)) {
          String checkDateQuery = "SELECT EMP_GOAL_ASSESSMENT_ID FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE GOAL_ID=" + bean.getEmpGoalId();
          Object[][] checkDateObj = getSqlModel().getSingleResult(checkDateQuery);

          if ((checkDateObj == null) || (checkDateObj.length <= 0)) {
            Object[][] maxAssesmentIdObj = getSqlModel().getSingleResult("SELECT NVL(MAX(EMP_GOAL_ASSESSMENT_ID),0)+1 FROM HRMS_GOAL_EMP_ASSESSMENT_HDR");
            int maxAssesmentId = Integer.parseInt(String.valueOf(maxAssesmentIdObj[0][0]));

            String insertQuery = "INSERT INTO HRMS_GOAL_EMP_ASSESSMENT_HDR ( GOAL_ID, EMP_ID, EMP_GOAL_ASSESSMENT_ID,GOAL_ASSESSMENT_DATE, GOAL_ASSESSMENT_STATUS, GOAL_ASSESSER_ID,GOAL_ASSESSER_LEVEL) VALUES ( ?, ?, ?,  TO_DATE( ?, 'DD-MM-YYYY'), 1, ?,?) ";

            Object[][] insertObject = new Object[reviewDatesObj.length][6];
            String assesserId = "";
            if ((empFlow != null) && (empFlow.length > 0))
              assesserId = String.valueOf(empFlow[0][0]);
            else {
              assesserId = bean.getUserEmpId();
            }
            for (int i = 0; i < insertObject.length; i++)
            {
              insertObject[i][0] = bean.getEmpGoalId();
              insertObject[i][1] = bean.getEmpCode();
              insertObject[i][2] = Integer.valueOf(maxAssesmentId++);
              insertObject[i][3] = String.valueOf(reviewDatesObj[i][0]);
              insertObject[i][4] = assesserId;
              insertObject[i][5] = Integer.valueOf(level);
            }
            return getSqlModel().singleExecute(insertQuery, insertObject);
          }
        }
      }
      else
        return true;
    }
    catch (Exception e) {
      logger.error("exception in saveAssetmentData");
    }
    return false;
  }

  public boolean deleteEmpGoals(String goalId) {
    if (getSqlModel().singleExecute("DELETE FROM HRMS_GOAL_EMP_HDR WHERE GOAL_HDR_ID IN(" + goalId + ")")) {
      return getSqlModel().singleExecute("DELETE FROM HRMS_GOAL_EMP_DTL WHERE GOAL_HDR_ID IN (" + goalId + ") AND GOAL_DTL_STATUS='A'");
    }
    return false;
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

  public boolean approveGoal(EmployeeGoalSetting goalSetting, Object[][] empFlow, String esclationwrkFlowFlag) {
    boolean result = false;

    if (esclationwrkFlowFlag.equals("Y"))
    {
      if ((empFlow != null) && (empFlow.length > 0))
      {
        System.out.println("String.valueOf(empFlow[0][6]) : " + String.valueOf(empFlow[0][6]));

        if (String.valueOf(empFlow[0][6]).equals("A"))
        {
          Object[][] updateApprover = new Object[1][3];
          updateApprover[0][0] = empFlow[0][2];
          updateApprover[0][1] = empFlow[0][0];
          updateApprover[0][2] = goalSetting.getEmpGoalId();

          String updateQuery = "  UPDATE HRMS_GOAL_EMP_HDR  SET GOAL_LEVEL=? ,GOAL_APPROVER_ID=? WHERE GOAL_HDR_ID=?  ";
          result = getSqlModel().singleExecute(updateQuery, updateApprover);
        }
        else
        {
          String updateQuery = "  UPDATE HRMS_GOAL_EMP_HDR  SET EMP_GOAL_APPROVAL_STATUS=3 WHERE GOAL_HDR_ID=" + goalSetting.getEmpGoalId();
          result = getSqlModel().singleExecute(updateQuery);
          saveAssesmentData(goalSetting);
        }

      }

    }
    else if ((empFlow != null) && (empFlow.length > 0))
    {
      if (String.valueOf(empFlow[0][6]).equals("R"))
      {
        String updateQuery = "  UPDATE HRMS_GOAL_EMP_HDR  SET EMP_GOAL_APPROVAL_STATUS=3 WHERE GOAL_HDR_ID=" + goalSetting.getEmpGoalId();
        result = getSqlModel().singleExecute(updateQuery);
        saveAssesmentData(goalSetting);
      }
      else
      {
        System.out.println("The length of empFlow :::::::: " + empFlow.length);
        Object[][] updateApprover = new Object[1][3];
        updateApprover[0][0] = empFlow[0][2];
        updateApprover[0][1] = empFlow[0][0];
        updateApprover[0][2] = goalSetting.getEmpGoalId();

        String updateQuery = "  UPDATE HRMS_GOAL_EMP_HDR  SET GOAL_LEVEL=? ,GOAL_APPROVER_ID=? WHERE GOAL_HDR_ID=?  ";
        result = getSqlModel().singleExecute(updateQuery, updateApprover);
      }
    }
    else
    {
      String updateQuery = "  UPDATE HRMS_GOAL_EMP_HDR  SET EMP_GOAL_APPROVAL_STATUS=3 WHERE GOAL_HDR_ID=" + goalSetting.getEmpGoalId();
      result = getSqlModel().singleExecute(updateQuery);
      saveAssesmentData(goalSetting);
    }

    return result;
  }

  public Object[][] generateEmpFlow(String empCode, int order, String reportingType)
  {
    if (!reportingType.equals("goal")) {
      ReportingModel reporting = new ReportingModel();
      reporting.initiate(this.context, this.session);
      Object[][] result = reporting.generateEmpFlow(empCode, "EmpGoal", order);
      reporting.terminate();
      return result;
    }

    String query = " SELECT REPORTINGHDR_EMP_ID FROM HRMS_REPTNG_STRUCTHDR_GOAL  WHERE 1 = 1 AND REPORTINGHDR_EMP_ID = " + 
      empCode + 
      " AND REPORTINGHDR_DEPT_ID = 0 AND REPORTINGHDR_BRN_ID = 0 AND REPORTINGHDR_DESG_ID = 0";
    Object[][] obj = getSqlModel().getSingleResult(query);
    try
    {
      if (obj.length > 0) {
        System.out.println("if...............");

        String query1 = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS,NVL(REPORTINGDTL_APPROVER_TYPE,'A') FROM HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE = (SELECT REPORTINGHDR_CODE FROM HRMS_REPTNG_STRUCTHDR_GOAL WHERE REPORTINGHDR_EMP_ID =" + 
          obj[0][0] + 
          " AND REPORTINGHDR_DEPT_ID = 0 AND REPORTINGHDR_BRN_ID = 0 AND REPORTINGHDR_DESG_ID = 0 ) AND  REPORTINGDTL_CODE = " + 
          order;
        return getSqlModel().getSingleResult(query1);
      }

      Object[][] deptCheck = (Object[][])null;
      String query3 = "";
      System.out.println("else...............");
      String query2 = " SELECT EMP_DEPT , EMP_CENTER,EMP_RANK  FROM HRMS_EMP_OFFC WHERE EMP_ID = " + 
        empCode;

      Object[][] obj1 = getSqlModel().getSingleResult(query2);

      if (!String.valueOf(obj1[0][0]).equals("")) {
        query3 = " SELECT REPORTINGHDR_CODE FROM HRMS_REPTNG_STRUCTHDR_GOAL   WHERE REPORTINGHDR_DEPT_ID = " + 
          obj1[0][0] + 
          " AND REPORTINGHDR_EMP_ID = 0 ";

        deptCheck = getSqlModel().getSingleResult(query3);
      }
      if (deptCheck.length != 0)
      {
        query3 = query3 + " AND REPORTINGHDR_BRN_ID =" + obj1[0][1];
        Object[][] deptBranchCheck = getSqlModel().getSingleResult(
          query3);
        if (deptBranchCheck.length != 0)
        {
          logger
            .info("deptBranchCheck length*********Dept+branch" + 
            deptBranchCheck.length);
          query3 = query3 + " AND REPORTINGHDR_DESG_ID =" + obj1[0][2];
          Object[][] allCheck = getSqlModel().getSingleResult(
            query3);
          logger.info("query*******" + query3);
          if (allCheck.length != 0) {
            logger
              .info("allCheck length*********Dept+branch+desg" + 
              allCheck.length);
            String allResult = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS,NVL(REPORTINGDTL_APPROVER_TYPE,'A') FROM HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE = " + 
              allCheck[0][0] + 
              " AND  REPORTINGDTL_CODE = " + order;
            return getSqlModel().getSingleResult(allResult);
          }
          String deptBranchResult = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS,NVL(REPORTINGDTL_APPROVER_TYPE,'A') FROM HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE = " + 
            deptBranchCheck[0][0] + 
            " AND  REPORTINGDTL_CODE = " + order;
          return getSqlModel().getSingleResult(
            deptBranchResult);
        }

        String deptDesg = " SELECT REPORTINGHDR_CODE FROM HRMS_REPTNG_STRUCTHDR_GOAL   WHERE REPORTINGHDR_DEPT_ID = " + 
          obj1[0][0] + 
          " AND REPORTINGHDR_DESG_ID =" + 
          obj1[0][2] + 
          " AND REPORTINGHDR_EMP_ID = 0 ";
        logger.info("deptCheck length*********Dept+branch" + 
          deptCheck.length);
        Object[][] deptDesgResult = getSqlModel()
          .getSingleResult(deptDesg);
        logger.info("deptDesgResult length*********" + 
          deptDesgResult.length);
        if ((deptDesgResult != null) && (deptDesgResult.length != 0)) {
          String deptDesgReturn = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS,NVL(REPORTINGDTL_APPROVER_TYPE,'A') FROM HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE = " + 
            deptDesgResult[0][0] + 
            " AND  REPORTINGDTL_CODE = " + order;
          return getSqlModel()
            .getSingleResult(deptDesgReturn);
        }
        String deptOnly = "SELECT REPORTINGHDR_CODE FROM HRMS_REPTNG_STRUCTHDR_GOAL   WHERE REPORTINGHDR_DEPT_ID = " + 
          obj1[0][0] + 
          " AND REPORTINGHDR_EMP_ID = 0 AND REPORTINGHDR_DESG_ID = 0 AND REPORTINGHDR_BRN_ID = 0  ";
        Object[][] deptOnlyObject = getSqlModel()
          .getSingleResult(deptOnly);
        if ((deptOnlyObject == null) || 
          (deptOnlyObject.length == 0)) {
          return null;
        }
        String deptResult = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS,NVL(REPORTINGDTL_APPROVER_TYPE,'A') FROM HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE = " + 
          deptOnlyObject[0][0] + 
          " AND  REPORTINGDTL_CODE = " + order;
        return getSqlModel()
          .getSingleResult(deptResult);
      }

      String query6 = " SELECT REPORTINGHDR_CODE FROM HRMS_REPTNG_STRUCTHDR_GOAL   WHERE REPORTINGHDR_BRN_ID = " + 
        obj1[0][1] + 
        " AND REPORTINGHDR_EMP_ID = 0 ";
      Object[][] branchCheck = getSqlModel().getSingleResult(
        query6);
      if (branchCheck.length != 0) {
        logger.info("branchCheck length*********branch" + 
          branchCheck.length);
        query6 = query6 + "AND REPORTINGHDR_DESG_ID =" + obj1[0][2];
        Object[][] branchDesgCheck = getSqlModel()
          .getSingleResult(query6);

        if (branchDesgCheck.length != 0) {
          logger
            .info("branchDesgCheck length*********desg+branch" + 
            branchDesgCheck.length);
          String branchDesgResult = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS,NVL(REPORTINGDTL_APPROVER_TYPE,'A') FROM HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE = " + 
            branchDesgCheck[0][0] + 
            " AND  REPORTINGDTL_CODE = " + order;
          return getSqlModel().getSingleResult(
            branchDesgResult);
        }

        String branchDesgResult = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS,NVL(REPORTINGDTL_APPROVER_TYPE,'A') FROM HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE = " + 
          branchCheck[0][0] + 
          " AND  REPORTINGDTL_CODE = " + order;
        return getSqlModel().getSingleResult(
          branchDesgResult);
      }

      String query8 = " SELECT REPORTINGHDR_CODE FROM HRMS_REPTNG_STRUCTHDR_GOAL   WHERE REPORTINGHDR_DESG_ID = " + 
        obj1[0][2] + 
        " AND REPORTINGHDR_EMP_ID = 0 ";
      Object[][] desgCheck = getSqlModel().getSingleResult(
        query8);
      if (desgCheck.length != 0) {
        logger.info("desgCheck length*********desg" + 
          desgCheck.length);
        String query5 = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS,NVL(REPORTINGDTL_APPROVER_TYPE,'A') FROM HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE = " + 
          desgCheck[0][0] + 
          " AND  REPORTINGDTL_CODE = " + order;
        return getSqlModel().getSingleResult(query5);
      }

      return null;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }return null;
  }

  public void genReport(EmployeeGoalSetting bean, HttpServletResponse response, HttpServletRequest request, String[] labels)
  {
    String title = "Goal Setting Report ";
    ReportDataSet rds = new ReportDataSet();
    rds.setFileName("Goal Setting Report_" + bean.getEmpName());
    rds.setReportName(title);
    rds.setPageSize("landscape");
    rds.setReportType("Pdf");
    ReportGenerator rg = new ReportGenerator(rds);

    TableDataSet repTitle = new TableDataSet();
    Object[][] repTitleObj = new Object[1][1];
    repTitleObj[0][0] = title;
    repTitle.setData(repTitleObj);
    repTitle.setCellAlignment(new int[] { 1 });
    repTitle.setCellWidth(new int[] { 100 });
    repTitle.setBodyFontDetails(1, 10.0F, 1, new Color(0, 0, 0));
    repTitle.setBorder(Boolean.valueOf(false));
    repTitle.setBlankRowsBelow(1);
    rg.addTableToDoc(repTitle);

    TableDataSet goalDetails = new TableDataSet();
    Object[][] goalDetailsObj = new Object[3][4];
    goalDetailsObj[0][0] = (labels[0] + " :");
    goalDetailsObj[0][1] = bean.getEmpToken();
    goalDetailsObj[0][2] = (labels[1] + " :");
    goalDetailsObj[0][3] = bean.getEmpName();
    goalDetailsObj[1][0] = (labels[2] + " :");
    goalDetailsObj[1][1] = bean.getGoalPeriod();
    goalDetailsObj[1][2] = (labels[3] + " :");
    goalDetailsObj[1][3] = (bean.getHiddenStatus().equals("3") ? "Approved" : "Pending");
    goalDetailsObj[2][0] = (labels[4] + " :");
    goalDetailsObj[2][1] = bean.getGoalFromDate();
    goalDetailsObj[2][2] = (labels[5] + " :");
    goalDetailsObj[2][3] = bean.getGoalToDate();
    goalDetails.setData(goalDetailsObj);
    goalDetails.setCellAlignment(new int[4]);
    goalDetails.setCellWidth(new int[] { 20, 30, 20, 30 });
    goalDetails.setBorder(Boolean.valueOf(false));
    goalDetails.setBlankRowsBelow(1);
    rg.addTableToDoc(goalDetails);

    Object[][] goalHeading = new Object[1][1];
    goalHeading[0][0] = (labels[6] + " :");
    TableDataSet goalTitle = new TableDataSet();
    goalTitle.setData(goalHeading);
    goalTitle.setCellAlignment(new int[1]);
    goalTitle.setCellWidth(new int[] { 100 });
    goalTitle.setBodyFontDetails(1, 10.0F, 1, new Color(0, 0, 0));
    goalTitle.setBlankRowsAbove(1);
    rg.addTableToDoc(goalTitle);

    String goalListQuery = "SELECT NVL(GOAL_CATEGORY,' '),GOAL_DESCRIPTION, NVL(GOAL_COMMENTS,' '), TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY'),NVL(GOAL_WEIGHTAGE,0) FROM HRMS_GOAL_EMP_DTL  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID)  WHERE NVL(GOAL_DTL_STATUS,'A') !='D' AND GOAL_HDR_ID=" + 
      bean.getEmpGoalId() + 
      " ORDER BY GOAL_CATEGORY_CODE,GOAL_ACHIEVEMENT_DATE ";
    Object[][] goalListObj = getSqlModel().getSingleResult(goalListQuery);

    if (bean.getIsGoalCategory().equals("true")) {
      Object[][] goalList = new Object[goalListObj.length][6];
      for (int i = 0; i < goalList.length; i++) {
        goalList[i][0] = (i + 1);
        goalList[i][1] = checkNull(String.valueOf(goalListObj[i][0]));
        goalList[i][2] = checkNull(String.valueOf(goalListObj[i][1]));
        goalList[i][3] = checkNull(String.valueOf(goalListObj[i][2]));
        goalList[i][4] = checkNull(String.valueOf(goalListObj[i][3]));
        goalList[i][5] = checkNull(String.valueOf(goalListObj[i][4]));
      }
      String[] goalListCol = { labels[7], labels[18], labels[8], labels[9], labels[10], labels[11] };
      TableDataSet goalListTDS = new TableDataSet();
      goalListTDS.setData(goalList);
      goalListTDS.setHeader(goalListCol);
      goalListTDS.setCellAlignment(new int[] { 1, 0, 0, 0, 1, 2 });
      goalListTDS.setCellWidth(new int[] { 10, 20, 30, 30, 20, 10 });
      goalListTDS.setBlankRowsAbove(1);
      goalListTDS.setBorder(Boolean.valueOf(true));
      goalListTDS.setHeaderBGColor(new Color(225, 225, 225));
      rg.addTableToDoc(goalListTDS);
    } else {
      Object[][] goalList = new Object[goalListObj.length][5];
      for (int i = 0; i < goalList.length; i++) {
        goalList[i][0] = (i + 1);
        goalList[i][1] = checkNull(String.valueOf(goalListObj[i][1]));
        goalList[i][2] = checkNull(String.valueOf(goalListObj[i][2]));
        goalList[i][3] = checkNull(String.valueOf(goalListObj[i][3]));
        goalList[i][4] = checkNull(String.valueOf(goalListObj[i][4]));
      }
      String[] goalListCol = { labels[7], labels[8], labels[9], labels[10], labels[11] };
      TableDataSet goalListTDS = new TableDataSet();
      goalListTDS.setData(goalList);
      goalListTDS.setHeader(goalListCol);
      goalListTDS.setCellAlignment(new int[] { 1, 0, 0, 1, 2 });
      goalListTDS.setCellWidth(new int[] { 10, 30, 30, 20, 10 });
      goalListTDS.setBlankRowsAbove(1);
      goalListTDS.setBorder(Boolean.valueOf(true));
      goalListTDS.setHeaderBGColor(new Color(225, 225, 225));
      rg.addTableToDoc(goalListTDS);
    }

    String[] apprName = request.getParameterValues("approverName");
    if ((apprName != null) && (apprName.length > 0))
    {
      Object[][] approverHeading = new Object[1][1];
      approverHeading[0][0] = (labels[12] + " :");
      TableDataSet approverTitle = new TableDataSet();
      approverTitle.setData(approverHeading);
      approverTitle.setCellAlignment(new int[1]);
      approverTitle.setCellWidth(new int[] { 100 });
      approverTitle.setBodyFontDetails(1, 10.0F, 1, new Color(0, 0, 0));
      approverTitle.setBlankRowsAbove(1);
      rg.addTableToDoc(approverTitle);

      String[] apprDate = request.getParameterValues("approvedDate");
      String[] apprStatus = request.getParameterValues("approveStatus");
      String[] apprComments = request.getParameterValues("approverCommentList");
      Object[][] apprNameObj = new Object[apprName.length][5];
      for (int i = 0; i < apprName.length; i++) {
        apprNameObj[i][0] = (i + 1);
        apprNameObj[i][1] = apprName[i];
        apprNameObj[i][2] = apprDate[i];
        apprNameObj[i][3] = apprStatus[i];
        apprNameObj[i][4] = apprComments[i];
      }
      String[] apprListCol = { labels[13], labels[14], labels[15], labels[16], labels[17] };
      TableDataSet apprListTDS = new TableDataSet();
      apprListTDS.setData(apprNameObj);
      apprListTDS.setHeader(apprListCol);
      apprListTDS.setCellAlignment(new int[] { 1, 0, 1, 1 });
      apprListTDS.setCellWidth(new int[] { 10, 20, 15, 15, 40 });
      apprListTDS.setBlankRowsAbove(1);
      apprListTDS.setBorder(Boolean.valueOf(true));
      apprListTDS.setHeaderBGColor(new Color(225, 225, 225));

      rg.addTableToDoc(apprListTDS);
    }

    rg.process();
    rg.createReport(response);
  }
  public void setGoalCategories(EmployeeGoalSetting bean) {
    String categoryQuery = "SELECT GOAL_CATEGORY_ID,GOAL_CATEGORY,GOAL_CATEGORY_WEIGHTAGE FROM HRMS_GOAL_CATEGORIES WHERE GOAL_ID=" + bean.getGoalPeriodId();
    Object[][] categoryObj = getSqlModel().getSingleResult(categoryQuery);
    HashMap categoryMap = new HashMap();
    try {
      if ((categoryObj != null) && (categoryObj.length > 0)) {
        for (int i = 0; i < categoryObj.length; i++) {
          categoryMap.put(String.valueOf(categoryObj[i][0]), 
            String.valueOf(categoryObj[i][1]));
        }
      }
      categoryMap = 
        Utility.sortMapByValue(categoryMap, null, Boolean.valueOf(true));
      bean.setCategoryMap(categoryMap);
    }
    catch (Exception localException)
    {
    }
  }

  public void getCompetencyData(EmployeeGoalSetting empGoalSet) {
    String query = "SELECT CADRE_COMPETENCY_DTL FROM HRMS_CADRE_DTL  INNER JOIN HRMS_EMP_OFFC ON CADRE_ID=EMP_CADRE  WHERE EMP_ID =" + 
      empGoalSet.getEmpCode() + "  ORDER BY CADRE_ID";
    Object[][] data = getSqlModel().getSingleResult(query);
    ArrayList empGoalList = new ArrayList();
    for (int i = 0; i < data.length; i++) {
      EmployeeGoalSetting bean = new EmployeeGoalSetting();
      bean.setCompetency(String.valueOf(data[i][0]));
      System.out.println("---------comp---------" + bean.getCompetency());
      empGoalList.add(bean);
    }
    empGoalSet.setEmpGoalList(empGoalList);
  }
}