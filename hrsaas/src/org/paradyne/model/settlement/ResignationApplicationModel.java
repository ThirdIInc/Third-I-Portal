package org.paradyne.model.settlement;

import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.settlement.ResignationApplication;
import org.paradyne.lib.DateUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;

public class ResignationApplicationModel extends ModelBase
{
  static Logger logger = Logger.getLogger(ResignationApplicationModel.class);

  public String save(ResignationApplication resignApp, String status, String approverId, String alterapproverId, String[] empCode)
  {
    boolean result = false;
    String returnResult = "";
    try {
      String sameResgQue = "SELECT TO_CHAR(RESIGN_DATE,'DD-MM-YYYY') FROM HRMS_RESIGN WHERE RESIGN_EMP=" + 
        resignApp.getEmpCode() + " and  RESIGN_APPL_STATUS not in('R','W') ";
      Object[][] sameResgObj = getSqlModel().getSingleResult(sameResgQue);
      if (sameResgObj.length > 0) {
        for (int i = 0; i < sameResgObj.length; i++) {
          int days = Integer.parseInt(String.valueOf(
            sameResgObj[i][0]).substring(0, 2));
          int month = Integer.parseInt(String.valueOf(
            sameResgObj[i][0]).substring(3, 5));
          int year = Integer.parseInt(String.valueOf(
            sameResgObj[i][0]).substring(6, 10));
          int resgDays = Integer.parseInt(String.valueOf(
            resignApp.getResignDate()).substring(0, 2));
          int resgMonth = Integer.parseInt(String.valueOf(
            resignApp.getResignDate()).substring(3, 5));
          int resgYear = Integer.parseInt(String.valueOf(
            resignApp.getResignDate()).substring(6, 10));

          if ((year == resgYear) && (month == resgMonth) && 
            (days == resgDays)) {
            return "sameResg";
          }

        }

      }

      String resignationCodeQuery = " SELECT NVL(MAX(RESIGN_CODE),0)+1 FROM HRMS_RESIGN ";
      Object[][] obj = getSqlModel()
        .getSingleResult(resignationCodeQuery);
      resignApp.setResignCode(checkNull(String.valueOf(obj[0][0])));

      String empId = "";
      if ((empCode != null) && (empCode.length > 0)) {
        for (int i = 0; i < empCode.length; i++) {
          if (i < empCode.length - 1)
            empId = empId + empCode[i] + ",";
          else {
            empId = empId + empCode[i];
          }
        }

      }

      String insertQuery = "  INSERT INTO HRMS_RESIGN(RESIGN_CODE,RESIGN_EMP,RESIGN_DATE,RESIGN_TENT_SEPR_DATE,RESIGN_APPL_STATUS,  RESIGN_REASON, RESIGN_REMARK ,RESIGN_APPROVED_BY, RESIGN_ALTER_APPROVER,RESIGN_KEEP_INFORMED )   VALUES((SELECT NVL(MAX(RESIGN_CODE),0)+1 FROM HRMS_RESIGN ),?,  TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?) ";

      Object[][] addObj = new Object[1][9];

      addObj[0][0] = resignApp.getEmpCode().trim();
      addObj[0][1] = resignApp.getResignDate().trim();
      addObj[0][2] = resignApp.getSeperationDate().trim();
      addObj[0][3] = status;
      addObj[0][4] = resignApp.getAppReason().trim();
      addObj[0][5] = resignApp.getAppComment().trim();
      addObj[0][6] = approverId.trim();
      addObj[0][7] = alterapproverId.trim();
      addObj[0][8] = empId;

      result = getSqlModel().singleExecute(insertQuery, addObj);
      if (result)
        returnResult = "saved";
      else {
        returnResult = "notSaved";
      }
    }
    catch (Exception localException)
    {
    }

    return returnResult;
  }

  public String update(ResignationApplication resignApp, String status, String approverId, String alterapproverId, String[] empCode)
  {
    boolean result = false;
    String returnResult = "";
    try {
      String updateQuery = " UPDATE HRMS_RESIGN SET RESIGN_EMP=?,RESIGN_DATE=TO_DATE(?,'DD-MM-YYYY'), RESIGN_TENT_SEPR_DATE=TO_DATE(?,'DD-MM-YYYY'),   RESIGN_APPL_STATUS=?,   RESIGN_REASON=?,  RESIGN_REMARK=? , RESIGN_APPROVED_BY=?, RESIGN_ALTER_APPROVER=? ,RESIGN_KEEP_INFORMED = ?   WHERE RESIGN_CODE=?  ";

      Object[][] updateObj = new Object[1][10];

      String empId = "";
      if ((empCode != null) && (empCode.length > 0)) {
        for (int i = 0; i < empCode.length; i++) {
          if (i < empCode.length - 1)
            empId = empId + empCode[i] + ",";
          else {
            empId = empId + empCode[i];
          }
        }
      }

      updateObj[0][0] = resignApp.getEmpCode().trim();
      updateObj[0][1] = resignApp.getResignDate().trim();
      updateObj[0][2] = resignApp.getSeperationDate().trim();
      updateObj[0][3] = status;
      updateObj[0][4] = resignApp.getAppReason().trim();
      updateObj[0][5] = resignApp.getAppComment().trim();
      updateObj[0][6] = approverId.trim();
      updateObj[0][7] = alterapproverId.trim();
      updateObj[0][8] = empId;
      updateObj[0][9] = resignApp.getResignCode().trim();

      result = getSqlModel().singleExecute(updateQuery, updateObj);
      if (result)
        returnResult = "update";
      else {
        returnResult = "notUpdate";
      }
    }
    catch (Exception localException)
    {
    }
    return returnResult;
  }

  public void getAllTypeOfApplications(ResignationApplication resignApp, HttpServletRequest request, String empId)
  {
    try
    {
      Object[] draftParam = (Object[])null;
      Object[] submitParam = (Object[])null;
      Object[] withdrawnParam = (Object[])null;

      draftParam = new Object[] { "D", empId };
      submitParam = new Object[] { "P", empId };
      withdrawnParam = new Object[] { "W", empId };

      String query = "  SELECT  HRMS_EMP_OFFC.EMP_TOKEN,   HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),  \tRESIGN_EMP,RESIGN_CODE,RESIGN_APPL_STATUS  FROM HRMS_RESIGN   \t INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP) \t WHERE RESIGN_APPL_STATUS =? AND RESIGN_EMP=?  ORDER BY RESIGN_CODE DESC ";

      Object[][] draftData = getSqlModel().getSingleResult(query, 
        draftParam);
      logger.info("draftData.length-----------------" + draftData.length);
      resignApp.setDraftList(null);
      if ((draftData != null) && (draftData.length > 0))
      {
        logger
          .info("in draft data-----------------" + 
          draftData.length);

        ArrayList DraftList = new ArrayList();

        for (int i = 0; i < draftData.length; i++) {
          ResignationApplication resignBeanDraft = new ResignationApplication();
          resignBeanDraft.setResignEmpToken(
            String.valueOf(draftData[i][0]));
          resignBeanDraft.setResignEmpName(
            String.valueOf(draftData[i][1]));
          resignBeanDraft.setResignAppDate(
            String.valueOf(draftData[i][2]));
          resignBeanDraft.setDraftResignEmpId(
            String.valueOf(draftData[i][3]));
          resignBeanDraft.setDraftResignAppNo(
            String.valueOf(draftData[i][4]));
          resignBeanDraft.setResignAppStatus(
            String.valueOf(draftData[i][5]));
          DraftList.add(resignBeanDraft);
        }
        resignApp.setDraftList(DraftList);
      }
      Object[][] submitData = getSqlModel().getSingleResult(query, 
        submitParam);

      resignApp.setSubmitList(null);

      if ((submitData != null) && (submitData.length > 0))
      {
        logger.info("in draft submitData-----------------" + 
          submitData.length);
        ArrayList submitList = new ArrayList();
        for (int i = 0; i < submitData.length; i++)
        {
          ResignationApplication resignSubmitBean = new ResignationApplication();

          resignSubmitBean.setResignEmpToken(
            String.valueOf(submitData[i][0]));
          resignSubmitBean.setResignEmpName(
            String.valueOf(submitData[i][1]));
          resignSubmitBean.setResignAppDate(
            String.valueOf(submitData[i][2]));
          resignSubmitBean.setSubmitEmpId(
            String.valueOf(submitData[i][3]));
          resignSubmitBean.setSubmitResignAppNo(
            String.valueOf(submitData[i][4]));
          resignSubmitBean.setResignAppStatus(
            String.valueOf(submitData[i][5]));
          submitList.add(resignSubmitBean);
        }
        resignApp.setSubmitList(submitList);
      }
      Object[][] withdrawnData = getSqlModel().getSingleResult(query, 
        withdrawnParam);
      resignApp.setWithdrawnList(null);

      if ((withdrawnData != null) && (withdrawnData.length > 0))
      {
        logger.info("in draft withdrawnData-----------------" + 
          withdrawnData.length);
        ArrayList withdrawnList = new ArrayList();
        for (int i = 0; i < withdrawnData.length; i++)
        {
          ResignationApplication resignWithdrawnBean = new ResignationApplication();

          resignWithdrawnBean.setResignEmpToken(
            String.valueOf(withdrawnData[i][0]));
          resignWithdrawnBean.setResignEmpName(
            String.valueOf(withdrawnData[i][1]));
          resignWithdrawnBean.setResignAppDate(
            String.valueOf(withdrawnData[i][2]));
          resignWithdrawnBean.setWithdrawnEmpId(
            String.valueOf(withdrawnData[i][3]));
          resignWithdrawnBean.setWithdrawnAppNo(
            String.valueOf(withdrawnData[i][4]));
          resignWithdrawnBean.setResignAppStatus(
            String.valueOf(withdrawnData[i][5]));
          withdrawnList.add(resignWithdrawnBean);
        }
        resignApp.setWithdrawnList(withdrawnList);
      }
    }
    catch (Exception localException)
    {
    }
  }

  public String checkNull(String result) {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public void getEmployeeDetails(ResignationApplication resignApp)
  {
    try
    {
      String query = "  SELECT   HRMS_EMP_OFFC.EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME), \tNVL(RANK_NAME,' '),NVL(CENTER_NAME,''), NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '), \tHRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)  LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  WHERE EMP_ID=" + 
        resignApp.getUserEmpId();

      Object[][] data = getSqlModel().getSingleResult(query);

      if ((data != null) && (data.length > 0)) {
        resignApp.setEmpToken(checkNull(String.valueOf(data[0][0])));
        resignApp.setEmpName(checkNull(String.valueOf(data[0][1])));
        resignApp.setDesignationName(
          checkNull(String.valueOf(data[0][2])));
        resignApp.setBranchName(checkNull(String.valueOf(data[0][3])));
        resignApp.setDateOfJoin(checkNull(String.valueOf(data[0][4])));
        resignApp.setEmpCode(checkNull(String.valueOf(data[0][5])));
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void getApprovedList(ResignationApplication resignApp, HttpServletRequest request, String empId)
  {
    try
    {
      String query = "  SELECT  HRMS_EMP_OFFC.EMP_TOKEN,   HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),  \tRESIGN_EMP,RESIGN_CODE,RESIGN_APPL_STATUS  FROM HRMS_RESIGN   \t INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP) \t WHERE RESIGN_APPL_STATUS IN('A','Y') AND RESIGN_EMP=?  ORDER BY RESIGN_CODE DESC ";

      Object[] approvedParam = (Object[])null;
      approvedParam = new Object[] { empId };
      Object[][] approvedData = getSqlModel().getSingleResult(query, 
        approvedParam);

      if ((approvedData != null) && (approvedData.length > 0))
      {
        ArrayList approvedList = new ArrayList();

        for (int i = 0; i < approvedData.length; i++) {
          ResignationApplication resignApprovedBean = new ResignationApplication();
          resignApprovedBean.setResignEmpToken(
            String.valueOf(approvedData[i][0]));
          resignApprovedBean.setResignEmpName(
            String.valueOf(approvedData[i][1]));
          resignApprovedBean.setResignAppDate(
            String.valueOf(approvedData[i][2]));
          resignApprovedBean.setApprovedResignEmpId(
            String.valueOf(approvedData[i][3]));
          resignApprovedBean.setApprovedResignAppNo(
            String.valueOf(approvedData[i][4]));
          resignApprovedBean.setResignAppStatus(
            String.valueOf(approvedData[i][5]));
          approvedList.add(resignApprovedBean);
        }
        resignApp.setApprovedList(approvedList);
      }
    }
    catch (Exception e) {
      logger.error("Exception in getApprovedList------" + e);
      e.printStackTrace();
    }
  }

  public void getRejectedList(ResignationApplication resignApp, HttpServletRequest request, String empId)
  {
    try
    {
      String query = "  SELECT  HRMS_EMP_OFFC.EMP_TOKEN,   HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),  \tRESIGN_EMP,RESIGN_CODE,RESIGN_APPL_STATUS  FROM HRMS_RESIGN   \t INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP) \t WHERE RESIGN_APPL_STATUS IN('R','Z') AND RESIGN_EMP=?  ORDER BY RESIGN_CODE DESC ";

      Object[] rejectedParam = (Object[])null;
      rejectedParam = new Object[] { empId };

      Object[][] rejectedData = getSqlModel().getSingleResult(query, 
        rejectedParam);

      if ((rejectedData != null) && (rejectedData.length > 0))
      {
        ArrayList rejectedList = new ArrayList();
        for (int i = 0; i < rejectedData.length; i++)
        {
          ResignationApplication resignRejectedBean = new ResignationApplication();
          resignRejectedBean.setResignEmpToken(
            String.valueOf(rejectedData[i][0]));
          resignRejectedBean.setResignEmpName(
            String.valueOf(rejectedData[i][1]));
          resignRejectedBean.setResignAppDate(
            String.valueOf(rejectedData[i][2]));
          resignRejectedBean.setRejectedResignEmpId(
            String.valueOf(rejectedData[i][3]));
          resignRejectedBean.setRejectedResignAppNo(
            String.valueOf(rejectedData[i][4]));
          resignRejectedBean.setResignAppStatus(
            String.valueOf(rejectedData[i][5]));
          rejectedList.add(resignRejectedBean);
        }

        resignApp.setRejectedList(rejectedList);
      }
    }
    catch (Exception e) {
      logger.error("Exception in getRejectedList------" + e);
    }
  }

  public String getHRempCode(String empCode)
  {
    String hrempCode = "";
    Object[][] data = (Object[][])null;
    String sqlQuery = "";
    sqlQuery = "SELECT EMP_CODE FROM HRMS_HR_SETTING WHERE BRANCH_CODE=(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID=" + 
      empCode + ")";
    data = getSqlModel().getSingleResult(sqlQuery);
    if (((data == null) && (data.length == 0)) || (data.length == 0))
    {
      sqlQuery = "SELECT EMP_CODE FROM HRMS_HR_SETTING WHERE DIV_CODE=(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID=" + 
        empCode + ")";
      data = getSqlModel().getSingleResult(sqlQuery);
    }
    if ((data != null) && (data.length > 0))
    {
      for (int i = 0; i < data.length; i++)
      {
        if (i == 0)
          hrempCode = hrempCode + String.valueOf(data[i][0]);
        else {
          hrempCode = hrempCode + "," + String.valueOf(data[i][0]);
        }
      }
    }
    return hrempCode;
  }

  public void getRecord(ResignationApplication resignApp) {
    try {
      Object[] param = new Object[1];

      param[0] = resignApp.getResignCode();

      String query = " SELECT NVL(RESIGN_APPL_STATUS,'P'),RESIGN_LEVEL FROM HRMS_RESIGN WHERE RESIGN_CODE=? ";

      Object[][] applData = getSqlModel().getSingleResult(query, param);

      if (String.valueOf(applData[0][1]).equals("1"))
      {
        resignApp.setStatus(String.valueOf(applData[0][0]));
        resignApp.setHiddenStatus(String.valueOf(applData[0][0]));
      }
      else if ((!String.valueOf(applData[0][1]).equals("1")) && 
        (String.valueOf(applData[0][0]).equals("P"))) {
        resignApp.setStatus("F");
        resignApp.setHiddenStatus("F");
      }
      else
      {
        resignApp.setStatus(String.valueOf(applData[0][0]));
        resignApp.setHiddenStatus(String.valueOf(applData[0][0]));
      }
      resignApp.setLevel(String.valueOf(applData[0][1]));
    } catch (Exception e) {
      logger.error("Exception in getRecord--------- " + e);
    }
  }

  public void getApplicationDetails(ResignationApplication resignApp)
  {
    try
    {
      Object[] bean = new Object[1];
      bean[0] = resignApp.getResignCode();

      String query = " SELECT  DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME), \tTO_CHAR(RESIGN_DATE,'DD-MM-YYYY') , \tNVL(RANK_NAME,' '),NVL(CENTER_NAME,''),  \t\tNVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '), \tNVL(TO_CHAR(RESIGN_TENT_SEPR_DATE,'DD-MM-YYYY'),' '), \tRESIGN_APPL_STATUS, RESIGN_REASON,RESIGN_REMARK, \tHRMS_RESIGN.RESIGN_CODE,HRMS_EMP_OFFC.EMP_ID ,TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY')  ,NVL(TO_CHAR(RESIGN_ACCEPT_DATE,'DD-MM-YYYY'),' ')  \tFROM HRMS_RESIGN  \t\tLEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP)\tLEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) \tLEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  WHERE HRMS_RESIGN.RESIGN_CODE=? ";

      Object[][] data = getSqlModel().getSingleResult(query, bean);

      if ((data != null) && (data.length > 0)) {
        resignApp.setEmpToken(checkNull(String.valueOf(data[0][0])));
        resignApp.setEmpName(checkNull(String.valueOf(data[0][1])));
        resignApp.setResignDate(checkNull(String.valueOf(data[0][2])));
        resignApp.setDesignationName(
          checkNull(String.valueOf(data[0][3])));
        resignApp.setBranchName(checkNull(String.valueOf(data[0][4])));
        resignApp.setDateOfJoin(checkNull(String.valueOf(data[0][5])));
        resignApp.setSeperationDate(
          checkNull(String.valueOf(data[0][6])));
        resignApp.setStatus(checkNull(String.valueOf(data[0][7])));
        resignApp.setAppReason(checkNull(String.valueOf(data[0][8])));
        resignApp.setAppComment(checkNull(String.valueOf(data[0][9])));
        resignApp.setResignCode(checkNull(String.valueOf(data[0][10])));
        resignApp.setEmpCode(checkNull(String.valueOf(data[0][11])));
        resignApp.setSeperationDateActual(
          checkNull(String.valueOf(data[0][12])));
        resignApp.setAcceptDate(checkNull(String.valueOf(data[0][13])));
      }
    }
    catch (Exception e)
    {
      logger.error("Exception in getApplicationDetails--------- " + e);
    }
  }

  public boolean delete(ResignationApplication resignApp)
  {
    boolean result = false;
    try
    {
      String query = "   DELETE FROM HRMS_RESIGN WHERE RESIGN_CODE=" + 
        resignApp.getResignCode();

      result = getSqlModel().singleExecute(query);
    }
    catch (Exception localException)
    {
    }
    return result;
  }

  public boolean withdrawApplication(ResignationApplication resignApp)
  {
    boolean result = false;
    try
    {
      String updateQuery = " UPDATE HRMS_RESIGN SET RESIGN_APPL_STATUS='W',RESIGN_WITHDRAWN='Y'  WHERE RESIGN_CODE=" + 
        resignApp.getResignCode();

      result = getSqlModel().singleExecute(updateQuery);
    }
    catch (Exception localException) {
    }
    return result;
  }

  public boolean setApproverDetails(ResignationApplication resignApp)
  {
    boolean isRecordInPath = false;
    try {
      String approverCommentQuery = "  SELECT EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' '),TO_CHAR(RESIGN_APPROVED_DATE,'DD-MM-YYYY'),  DECODE(RESIGN_STATUS,'D','Draft','P','Pending','A','Approved','R','Rejected','Y','Hr Approved','Z','Hr Rejected'),RESIGN_COMMENTS  FROM HRMS_RESIGN_PATH   INNER JOIN HRMS_EMP_OFFC ON (HRMS_RESIGN_PATH.RESIGN_APPROVED_BY= HRMS_EMP_OFFC.EMP_ID)  WHERE HRMS_RESIGN_PATH.RESIGN_APPL_CODE=" + 
        resignApp.getResignCode() + 
        " ORDER BY RESIGN_PATH_ID DESC ";
      Object[][] approverCommentObj = getSqlModel().getSingleResult(
        approverCommentQuery);
      if ((approverCommentObj != null) && (approverCommentObj.length > 0)) {
        ArrayList arrList = new ArrayList();
        isRecordInPath = true;
        for (int i = 0; i < approverCommentObj.length; i++) {
          ResignationApplication resignBean = new ResignationApplication();
          resignBean.setPrevApproverID(
            checkNull(String.valueOf(approverCommentObj[i][0])));
          resignBean.setPrevApproverName(
            checkNull(String.valueOf(approverCommentObj[i][1])));
          resignBean.setPrevApproverDate(
            checkNull(String.valueOf(approverCommentObj[i][2])));
          resignBean.setPrevApproverStatus(
            checkNull(String.valueOf(approverCommentObj[i][3])));
          resignBean.setPrevApproverComment(
            checkNull(String.valueOf(approverCommentObj[i][4])));

          arrList.add(resignBean);
        }
        resignApp.setApproverCommentList(arrList);
      }
    }
    catch (Exception localException)
    {
    }
    return isRecordInPath;
  }

  public void setActualSeperationDate(ResignationApplication resignApp)
  {
    try {
      String query = " SELECT TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY') FROM HRMS_RESIGN WHERE RESIGN_CODE=" + 
        resignApp.getResignCode();
      Object[][] data = getSqlModel().getSingleResult(query);
      resignApp.setSeperationDateActual(
        checkNull(String.valueOf(data[0][0])));
    }
    catch (Exception localException)
    {
    }
  }

  public void sethrApprovalDetails(ResignationApplication resignApp) {
    try {
      String query = " SELECT RESIGN_STATUS,NVL(RESIGN_NOTICEPERIOD_ACTUAL,'0'), RESIGN_NOTICE_STATUS,   RESIGN_HR_COMMENTS,RESIGN_WITHDRAWN,NVL(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME,''),NVL(RESIGN_NOTICE_PERIOD,'0'),NVL(RESIGN_NOTICEPERIOD_ACTUAL - RESIGN_NOTICE_PERIOD,'0') FROM HRMS_RESIGN \t LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_WAVEOFF_APPROVER)    WHERE RESIGN_CODE=" + 
        resignApp.getResignCode();

      Object[][] data = getSqlModel().getSingleResult(query);

      if ((data != null) && (data.length > 0)) {
        resignApp
          .setStatusSelect(checkNull(String.valueOf(data[0][0])));
        resignApp.setNoticePeriodActual(
          checkNull(String.valueOf(data[0][1])));
        resignApp.setNoticeperiodselect(
          checkNull(String.valueOf(data[0][2])));
        resignApp.setHrComments(checkNull(String.valueOf(data[0][3])));
        if (String.valueOf(data[0][4]).equals("Y"))
          resignApp.setWithDrawn("true");
        else {
          resignApp.setWithDrawn("false");
        }
        resignApp.setApprName(checkNull(String.valueOf(data[0][5])));

        String actualPeriod = String.valueOf(data[0][1]);

        String period = String.valueOf(data[0][6]);
        String stats = String.valueOf(data[0][2]);
        int totalDays = getTotalDays(resignApp, actualPeriod, period, 
          stats);
        logger.info("totalDays retrieval  : " + totalDays);
        resignApp.setWaveOffPeriod(String.valueOf(totalDays));
      }
    }
    catch (Exception localException)
    {
    }
  }

  public int getTotalDays(ResignationApplication bean, String actualPeriod, String period, String stats)
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
          System.out.print("----if----" + 
            rangeObj[i][0].substring(0, 2));
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

      totalDays -= Integer.parseInt(period);
    } else {
      totalDays = Integer.parseInt(actualPeriod) - 
        Integer.parseInt(period);
    }
    return totalDays;
  }

  public void displayIteratorValueForKeepInformed(String[] srNo, String[] empCode, String[] empName, ResignationApplication resignApp)
  {
    try
    {
      ArrayList list = new ArrayList();
      if (srNo != null) {
        for (int i = 0; i < srNo.length; i++) {
          ResignationApplication bean = new ResignationApplication();
          bean.setKeepInformedEmpId(empCode[i]);
          bean.setKeepInformedEmpName(empName[i]);
          bean.setSerialNo(srNo[i]);
          list.add(bean);
        }
        resignApp.setKeepInformedList(list);
      }
    }
    catch (Exception e) {
      logger
        .error("Exception in displayIteratorValueForKeepInformed----------" + 
        e);
    }
  }

  public void setKeepInformed(String[] srNo, String[] empCode, String[] empName, ResignationApplication resignApp)
  {
    try
    {
      ResignationApplication bean = new ResignationApplication();
      bean.setKeepInformedEmpId(resignApp.getEmployeeId());
      bean.setKeepInformedEmpName(resignApp.getEmployeeName());
      ArrayList list = displayNewValueForKeepInformed(srNo, empCode, 
        empName, resignApp);
      bean.setSerialNo(String.valueOf(list.size() + 1));
      list.add(bean);
      resignApp.setKeepInformedList(list);
    } catch (Exception e) {
      logger.error("Exception in setKeepInformed----------" + e);
    }
  }

  private ArrayList displayNewValueForKeepInformed(String[] srNo, String[] empCode, String[] empName, ResignationApplication resignApp)
  {
    ArrayList list = null;
    try {
      list = new ArrayList();
      if (srNo != null)
      {
        for (int i = 0; i < srNo.length; i++) {
          ResignationApplication bean = new ResignationApplication();
          bean.setKeepInformedEmpId(empCode[i]);
          bean.setKeepInformedEmpName(empName[i]);
          bean.setSerialNo(srNo[i]);
          list.add(bean);
        }
      }
    } catch (Exception e) {
      logger.error("Exception in displayNewValueForKeepInformed----------", e);
    }
    return list;
  }

  public void removeKeepInformedData(String[] serialNo, String[] empCode, String[] empName, ResignationApplication resignApp)
  {
    try {
      ArrayList tableList = new ArrayList();
      if (serialNo != null) {
        for (int i = 0; i < serialNo.length; i++) {
          ResignationApplication bean = new ResignationApplication();
          bean.setSerialNo(String.valueOf(i + 1));
          bean.setKeepInformedEmpId(empCode[i]);
          bean.setKeepInformedEmpName(empName[i]);
          tableList.add(bean);
        }

        tableList.remove(Integer.parseInt(
          resignApp.getCheckRemove()) - 1);
      }

      resignApp.setKeepInformedList(tableList);
    } catch (Exception e) {
      logger.error("Exception in removeKeepInformedData------" + e);
    }
  }

  public void setApproverData(ResignationApplication resignApp, Object[][] empFlow)
  {
    try
    {
      if ((empFlow != null) && (empFlow.length > 0)) {
        Object[][] approverDataObj = new Object[empFlow.length][1];
        for (int i = 0; i < empFlow.length; i++)
        {
          String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '||  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')'   FROM HRMS_EMP_OFFC  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) WHERE EMP_ID IN(" + 
            empFlow[i][0] + ")";

          Object[][] temObj = getSqlModel().getSingleResult(
            selectQuery);
          approverDataObj[i][0] = String.valueOf(temObj[0][0]);
        }

        if ((approverDataObj != null) && (approverDataObj.length > 0)) {
          ArrayList arrList = new ArrayList();
          for (int i = 0; i < approverDataObj.length; i++) {
            ResignationApplication bean = new ResignationApplication();
            bean.setApproverName(
              String.valueOf(approverDataObj[i][0]));
            String srNo = i + 1 + getOrdinalFor(i + 1) + 
              "-Approver";
            bean.setSrNoIterator(srNo);
            arrList.add(bean);
          }
          resignApp.setApproverList(arrList);
        }
      }
    }
    catch (Exception e) {
      logger.error("Exception in setApproverData------" + e);
    }
  }

  public String getOrdinalFor(int value)
  {
    int hundredRemainder = value % 100;

    if ((hundredRemainder >= 10) && (hundredRemainder <= 20)) {
      return "th";
    }

    int tenRemainder = value % 10;

    switch (tenRemainder) {
    case 1:
      return "st";
    case 2:
      return "nd";
    case 3:
      return "rd";
    }
    return "th";
  }

  public String getKeepInformedSavedRecord(ResignationApplication resignApp)
  {
    String isKeepInform = "N";
    try {
      String selectQuery = " SELECT RESIGN_KEEP_INFORMED FROM  HRMS_RESIGN  WHERE RESIGN_CODE=" + 
        resignApp.getResignCode();

      Object[][] selectDataObj = getSqlModel().getSingleResult(
        selectQuery);
      String str = "";
      String query = "";
      if ((selectDataObj != null) && (selectDataObj.length > 0)) {
        str = String.valueOf(selectDataObj[0][0]);

        if (str.length() > 0) {
          query = "  SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID  FROM HRMS_EMP_OFFC   WHERE  EMP_ID IN(" + 
            str + ")";
        }
        Object[][] result = getSqlModel().getSingleResult(query);

        ArrayList list = new ArrayList();
        if ((result != null) && (result.length > 0))
        {
          for (int i = 0; i < result.length; i++) {
            ResignationApplication bean = new ResignationApplication();
            bean.setKeepInformedEmpId(
              String.valueOf(result[i][1]));
            bean.setKeepInformedEmpName(
              String.valueOf(result[i][0]));
            bean.setSerialNo(String.valueOf(i + 1));
            if (resignApp.getUserEmpId().equals(
              bean.getKeepInformedEmpId())) {
              isKeepInform = "Y";
            }
            list.add(bean);
          }
          resignApp.setKeepInformedList(list);
        }
      }
    } catch (Exception e) {
      logger.error("Exception in getKeepInformedSavedRecord----------" + 
        e);
    }
    return isKeepInform;
  }
}