package org.paradyne.model.settlement;

import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.settlement.ResignationHRApproval;
import org.paradyne.lib.DateUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.model.common.ReportingModel;

public class ResignationHRApprovalModel extends ModelBase
{
  static Logger logger = Logger.getLogger(ResignationHRApprovalModel.class);

  public void getAllTypeOfApplications(ResignationHRApproval resignHrApproval, HttpServletRequest request)
  {
    try
    {
      String query = " SELECT RESIGN_CODE,RESIGN_EMP,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),  RESIGN_APPL_STATUS,RESIGN_LEVEL,HRMS_EMP_OFFC.EMP_TOKEN  FROM HRMS_RESIGN  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP)  WHERE  RESIGN_APPL_STATUS='A'  ORDER BY RESIGN_CODE DESC ";

      Object[][] pendingData = getSqlModel().getSingleResult(query);

      if ((pendingData != null) && (pendingData.length > 0))
      {
        ArrayList pendingList = new ArrayList();

        for (int i = 0; i < pendingData.length; i++)
        {
          ResignationHRApproval resignPendingBean = new ResignationHRApproval();
          resignPendingBean.setResignAppNo(
            String.valueOf(pendingData[i][0]));

          resignPendingBean.setEmpCode(
            String.valueOf(pendingData[i][1]));

          resignPendingBean.setEmpName(
            String.valueOf(pendingData[i][2]));

          resignPendingBean
            .setDate(String.valueOf(pendingData[i][3]));

          resignPendingBean.setPendingStatus(
            String.valueOf(pendingData[i][4]));
          resignPendingBean.setLevel(
            String.valueOf(pendingData[i][5]));
          resignPendingBean.setTokenNo(
            String.valueOf(pendingData[i][6]));

          pendingList.add(resignPendingBean);
        }
        resignHrApproval.setPendingList(pendingList);
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void getApprovedList(ResignationHRApproval resignHrApproval, HttpServletRequest request)
  {
    try
    {
      String query = " SELECT RESIGN_CODE,RESIGN_EMP,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),  RESIGN_APPL_STATUS,RESIGN_LEVEL,HRMS_EMP_OFFC.EMP_TOKEN   FROM HRMS_RESIGN   INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP)  WHERE  RESIGN_APPL_STATUS='Y'  ORDER BY RESIGN_CODE DESC ";

      Object[][] approvedData = getSqlModel().getSingleResult(query);

      if ((approvedData != null) && (approvedData.length > 0))
      {
        ArrayList approvedList = new ArrayList();
        for (int i = 0; i < approvedData.length; i++) {
          ResignationHRApproval resignApprovalBean = new ResignationHRApproval();
          resignApprovalBean.setAppResignAppNo(
            String.valueOf(approvedData[i][0]));

          resignApprovalBean.setAppEmpId(
            String.valueOf(approvedData[i][1]));

          resignApprovalBean.setAppEmpName(
            String.valueOf(approvedData[i][2]));

          resignApprovalBean.setAppResignAppDate(
            String.valueOf(approvedData[i][3]));

          resignApprovalBean.setAppStatus(
            String.valueOf(approvedData[i][4]));
          resignApprovalBean.setAppLevel(
            String.valueOf(approvedData[i][5]));
          resignApprovalBean.setAppEmpToken(
            String.valueOf(approvedData[i][6]));

          approvedList.add(resignApprovalBean);
        }

        resignHrApproval.setAppList(approvedList);
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void getRejectedList(ResignationHRApproval resignHrApproval, HttpServletRequest request)
  {
    try
    {
      String query = " SELECT RESIGN_CODE,RESIGN_EMP,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),  RESIGN_APPL_STATUS,RESIGN_LEVEL,HRMS_EMP_OFFC.EMP_TOKEN   FROM HRMS_RESIGN   INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP)  WHERE  RESIGN_APPL_STATUS='Z'  ORDER BY RESIGN_CODE DESC ";

      Object[][] rejectedData = getSqlModel().getSingleResult(query);
      if ((rejectedData != null) && (rejectedData.length > 0))
      {
        ArrayList rejectedList = new ArrayList();
        for (int i = 0; i < rejectedData.length; i++)
        {
          ResignationHRApproval resignRejectedBean = new ResignationHRApproval();
          resignRejectedBean.setRejResignAppNo(
            String.valueOf(rejectedData[i][0]));

          resignRejectedBean.setRejEmpId(
            String.valueOf(rejectedData[i][1]));
          resignRejectedBean.setRejEmpName(
            String.valueOf(rejectedData[i][2]));
          resignRejectedBean.setRejResignAppDate(
            String.valueOf(rejectedData[i][3]));
          resignRejectedBean.setRejStatus(
            String.valueOf(rejectedData[i][4]));
          resignRejectedBean.setRejLevel(
            String.valueOf(rejectedData[i][5]));
          resignRejectedBean.setRejEmpToken(
            String.valueOf(rejectedData[i][6]));

          rejectedList.add(resignRejectedBean);
        }

        resignHrApproval.setRejList(rejectedList);
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void getEmployeeDetails(ResignationHRApproval resignHrApproval, HttpServletRequest request)
  {
    try {
      Object[] bean = new Object[1];
      bean[0] = resignHrApproval.getResignCode();
      String query = " SELECT  DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME), \tTO_CHAR(RESIGN_DATE,'DD-MM-YYYY') , \tNVL(RANK_NAME,' '),NVL(CENTER_NAME,''),  \tNVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '), \tNVL(TO_CHAR(RESIGN_TENT_SEPR_DATE,'DD-MM-YYYY'),' '), \tRESIGN_APPL_STATUS, RESIGN_REASON,RESIGN_REMARK, \tHRMS_RESIGN.RESIGN_CODE,HRMS_EMP_OFFC.EMP_ID ,TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY'),TO_CHAR(RESIGN_ACCEPT_DATE,'DD-MM-YYYY'),DEPT_ID,NVL(CADRE_NAME,'')  FROM HRMS_RESIGN  \tLEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP)\tLEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) \tLEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) WHERE HRMS_RESIGN.RESIGN_CODE=? ";

      Object[][] data = getSqlModel().getSingleResult(query, bean);

      if ((data != null) && (data.length > 0)) {
        resignHrApproval.setEmpToken(
          checkNull(String.valueOf(data[0][0])).concat("       -"));
        resignHrApproval.setEmployeeName(
          checkNull(String.valueOf(data[0][1])));
        resignHrApproval.setResignDate(
          checkNull(String.valueOf(data[0][2])));
        resignHrApproval.setDesignationName(
          checkNull(String.valueOf(data[0][3])));
        resignHrApproval.setBranchName(
          checkNull(String.valueOf(data[0][4])));
        resignHrApproval.setDateOfJoin(
          checkNull(String.valueOf(data[0][5])));
        resignHrApproval.setSeperationDate(
          checkNull(String.valueOf(data[0][6])));
        resignHrApproval.setApplicationStatus(
          checkNull(String.valueOf(data[0][7])));
        resignHrApproval.setAppReason(
          checkNull(String.valueOf(data[0][8])));
        resignHrApproval.setAppComment(
          checkNull(String.valueOf(data[0][9])));
        resignHrApproval.setResignCode(
          checkNull(String.valueOf(data[0][10])));
        resignHrApproval.setEmployeeCode(
          checkNull(String.valueOf(data[0][11])));
        resignHrApproval.setActualSeperationDate(
          checkNull(String.valueOf(data[0][12])));
        resignHrApproval.setAcceptDate(
          checkNull(String.valueOf(data[0][13])));
        resignHrApproval.setDeptCode(checkNull(String.valueOf(data[0][14])));
        resignHrApproval.setCardeName(checkNull(String.valueOf(data[0][15])));

        String approverCommentsQuery = "  SELECT RESIGN_COMMENTS FROM HRMS_RESIGN_PATH WHERE RESIGN_APPL_CODE=" + resignHrApproval.getResignCode();

        Object[][] approverCommentsObj = getSqlModel().getSingleResult(approverCommentsQuery);

        if ((approverCommentsObj != null) && (approverCommentsObj.length > 0))
        {
          resignHrApproval.setApproverComments(
            checkNull(String.valueOf(approverCommentsObj[0][0])));
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public Object[][] generateEmpFlow(String empCode, String type, int order)
  {
    ReportingModel reporting = new ReportingModel();
    reporting.initiate(this.context, this.session);
    Object[][] result = reporting.generateEmpFlow(empCode, type, order);
    reporting.terminate();
    return result;
  }

  public String approveRejectApplication(String status, String noticePeriod, String noticePeriodInDaysOrMonth, String waveOffPeriod, String waveOfApprover, String hrComments, String applCode, String withDrawn, String applicationStatus, ResignationHRApproval resignHrApproval, HttpServletRequest request)
  {
    String applStatus = "pending";
    try
    {
      try {
        String period = resignHrApproval.getWaveOffPeriod();
        logger.info("getWaveOffPeriod  : " + resignHrApproval.getWaveOffPeriod());
        if (period.equals(""))
          period = "0";
        int totalDays = getTotalDays(resignHrApproval, resignHrApproval.getNoticePeriodActual(), 
          period, resignHrApproval.getNoticeperiodselect());
        logger.info("totalDays in update  : " + totalDays);
        resignHrApproval.setNoticePeriod(String.valueOf(totalDays));
      } catch (Exception e) {
        logger.error("Error in setting notice period : " + e);
      }

      Object[][] updateObj = new Object[1][9];
      updateObj[0][0] = status;
      updateObj[0][1] = noticePeriod;
      updateObj[0][2] = noticePeriodInDaysOrMonth;
      updateObj[0][3] = waveOfApprover;
      updateObj[0][4] = hrComments;
      updateObj[0][5] = withDrawn;
      updateObj[0][6] = applicationStatus;
      updateObj[0][7] = resignHrApproval.getNoticePeriod();
      updateObj[0][8] = applCode;

      if (String.valueOf(updateObj[0][5]).equals("true"))
        updateObj[0][5] = "Y";
      else {
        updateObj[0][5] = "N";
      }

      String updateQuery = " UPDATE HRMS_RESIGN SET RESIGN_STATUS=?,RESIGN_NOTICEPERIOD_ACTUAL=?,RESIGN_NOTICE_STATUS=?, RESIGN_WAVEOFF_APPROVER=? , RESIGN_HR_COMMENTS=?,RESIGN_WITHDRAWN=? ,RESIGN_APPL_STATUS=? ,RESIGN_NOTICE_PERIOD=?  WHERE RESIGN_CODE=? ";

      getSqlModel().singleExecute(updateQuery, updateObj);

      System.out.println("================applicationStatus============" + applicationStatus);
      if (applicationStatus.equals("Y"))
        applStatus = "approved";
      else if (applicationStatus.equals("Z"))
        applStatus = "rejected";
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return applStatus;
  }

  public void getHrApprovalDetails(ResignationHRApproval resignHrApproval)
  {
    try
    {
      String query = " SELECT RESIGN_STATUS,NVL(RESIGN_NOTICEPERIOD_ACTUAL,'0'), RESIGN_NOTICE_STATUS,   RESIGN_HR_COMMENTS,RESIGN_WITHDRAWN,NVL(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME,''),NVL(RESIGN_NOTICE_PERIOD,'0'),NVL(RESIGN_NOTICEPERIOD_ACTUAL - RESIGN_NOTICE_PERIOD,'0') FROM HRMS_RESIGN \t LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_WAVEOFF_APPROVER)    WHERE RESIGN_CODE=" + 
        resignHrApproval.getResignCode();

      Object[][] data = getSqlModel().getSingleResult(query);

      if ((data != null) && (data.length > 0)) {
        resignHrApproval
          .setStatus(checkNull(String.valueOf(data[0][0])));
        resignHrApproval.setNoticePeriodActual(
          checkNull(String.valueOf(data[0][1])));
        resignHrApproval.setNoticeperiodselect(
          checkNull(String.valueOf(data[0][2])));
        resignHrApproval.setHrComments(
          checkNull(String.valueOf(data[0][3])));
        if (String.valueOf(data[0][4]).equals("Y"))
          resignHrApproval.setWithDrawn("true");
        else {
          resignHrApproval.setWithDrawn("false");
        }
        resignHrApproval.setApprName(
          checkNull(String.valueOf(data[0][5])));

        String actualPeriod = String.valueOf(data[0][1]);
        String period = String.valueOf(data[0][6]);
        String stats = String.valueOf(data[0][2]);
        int totalDays = getTotalDays(resignHrApproval, actualPeriod, period, stats);
        logger.info("totalDays retrieval  : " + totalDays);
        resignHrApproval.setWaveOffPeriod(String.valueOf(totalDays));
      }
    }
    catch (Exception localException)
    {
    }
  }

  public int getTotalDays(ResignationHRApproval bean, String actualPeriod, String period, String stats)
  {
    DateUtility c = new DateUtility();
    logger.info("getResignDate......." + bean.getResignDate());
    logger.info("getNoticeStatus......." + stats);
    logger.info("getNoticePeriodActual......." + actualPeriod);
    logger.info("period......." + period);
    int totalDays = 0;
    if (bean.getNoticeperiodselect().equals("M")) {
      String resignDate = bean.getResignDate();
      String noticeDate = c.getNoticeDate(bean.getResignDate(), stats, 
        Integer.parseInt(actualPeriod));
      logger.info("Notice date   :" + noticeDate);
      String[][] rangeObj = c.getDatesBetween(resignDate, noticeDate);

      for (int i = 0; i < rangeObj.length; i++)
      {
        if (i == 0) {
          System.out.print("----if----" + rangeObj[i][0].substring(0, 2));
          System.out.println("||" + rangeObj[i][2]);

          totalDays = totalDays + (Integer.parseInt(rangeObj[i][2]) - 
            Integer.parseInt(rangeObj[i][0].substring(0, 2)));
        } else {
          System.out.println("--else----" + 
            rangeObj[i][1].substring(0, 2));

          totalDays = totalDays + 
            Integer.parseInt(rangeObj[i][1]
            .substring(0, 2));
        }
      }

      System.out.println("if--totalDays----" + totalDays);
      totalDays -= Integer.parseInt(period);
      System.out.println("if11--totalDays----" + totalDays);
    } else {
      totalDays = Integer.parseInt(actualPeriod) - Integer.parseInt(period);
      System.out.println("else--totalDays----" + totalDays);
    }
    return totalDays;
  }

  public String[][] getResponsibleEmpForCheckList() {
    String[][] resEmpCodeCheckList = (String[][])null;
    try
    {
      String strQry = "SELECT  EMP_CODE,DEPT_CODE   FROM HRMS_DEPT_CLEARCHKLIST_HDR INNER JOIN  HRMS_DEPT ON(HRMS_DEPT.DEPT_ID= HRMS_DEPT_CLEARCHKLIST_HDR.DEPT_CODE) INNER JOIN  HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_DEPT_CLEARCHKLIST_HDR.EMP_CODE) ORDER BY  HRMS_DEPT_CLEARCHKLIST_HDR.DEPT_CODE";

      Object[][] data = getSqlModel().getSingleResult(strQry);
      if ((data != null) && (data.length > 0)) {
        resEmpCodeCheckList = new String[data.length][2];
        for (int i = 0; i < resEmpCodeCheckList.length; i++) {
          resEmpCodeCheckList[i][0] = String.valueOf(data[i][0]);
          resEmpCodeCheckList[i][1] = String.valueOf(data[i][1]);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resEmpCodeCheckList;
  }

  public void addDepartmentClearance(String resignCode, String deptCode)
  {
    String inserQuery = "INSERT INTO HRMS_DEPT_CLEARANCE_HDR(RESIGN_CODE, DEPT_CLEARANCE_STATUS, DEPT_CODE) VALUES(" + resignCode + ",'N'," + deptCode + ")";
    getSqlModel().singleExecute(inserQuery);
  }
}