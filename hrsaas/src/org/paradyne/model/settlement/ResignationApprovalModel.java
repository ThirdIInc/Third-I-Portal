package org.paradyne.model.settlement;

import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.settlement.ResignationApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

public class ResignationApprovalModel extends ModelBase
{
  static Logger logger = Logger.getLogger(ResignationApprovalModel.class);

  public void getAllTypeOfApplications(ResignationApproval resignApproval, HttpServletRequest request, String empId)
  {
    try
    {
      System.out
        .println("getAllTypeOfApplications----------------------------------------");

      String query = " SELECT RESIGN_CODE,RESIGN_EMP,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),  RESIGN_APPL_STATUS,RESIGN_LEVEL,HRMS_EMP_OFFC.EMP_TOKEN   FROM HRMS_RESIGN   INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP)  WHERE  RESIGN_APPL_STATUS=?  AND (RESIGN_APPROVED_BY=? OR RESIGN_ALTER_APPROVER=? ) ORDER BY RESIGN_CODE DESC ";

      Object[] pendingParam = (Object[])null;
      pendingParam = new Object[] { "P", empId, empId };
      Object[][] pendingData = getSqlModel().getSingleResult(query, 
        pendingParam);
      if ((pendingData != null) && (pendingData.length > 0))
      {
        ArrayList pendingList = new ArrayList();

        for (int i = 0; i < pendingData.length; i++)
        {
          ResignationApproval resignPendingBean = new ResignationApproval();
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
        resignApproval.setPendingList(pendingList);
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void getApprovedList(ResignationApproval resignApproval, HttpServletRequest request, String empId)
  {
    try
    {
      String query = " SELECT RESIGN_CODE,RESIGN_EMP,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),  RESIGN_APPL_STATUS,RESIGN_LEVEL,HRMS_EMP_OFFC.EMP_TOKEN   FROM HRMS_RESIGN   INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP)  WHERE  RESIGN_APPL_STATUS IN (?,'Y')  AND (RESIGN_APPROVED_BY=? OR RESIGN_ALTER_APPROVER=? ) ORDER BY RESIGN_CODE DESC ";

      Object[] approvedParam = (Object[])null;
      logger.info("empId" + empId);
      approvedParam = new Object[] { "A", empId, empId };
      Object[][] approvedData = getSqlModel().getSingleResult(query, 
        approvedParam);
      if ((approvedData != null) && (approvedData.length > 0)) {
        ArrayList approvedList = new ArrayList();
        for (int i = 0; i < approvedData.length; i++) {
          ResignationApproval resignApprovalBean = new ResignationApproval();
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

        resignApproval.setAppList(approvedList);
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void getRejectedList(ResignationApproval resignApproval, HttpServletRequest request, String empId)
  {
    try
    {
      Object[] rejectedParam = (Object[])null;

      String query = " SELECT RESIGN_CODE,RESIGN_EMP,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'),  RESIGN_APPL_STATUS,RESIGN_LEVEL,HRMS_EMP_OFFC.EMP_TOKEN   FROM HRMS_RESIGN   INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP)  WHERE  RESIGN_APPL_STATUS=?  AND (RESIGN_APPROVED_BY=? OR RESIGN_ALTER_APPROVER=? ) ORDER BY RESIGN_CODE DESC ";

      rejectedParam = new Object[] { "R", empId, empId };
      Object[][] rejectedData = getSqlModel().getSingleResult(query, 
        rejectedParam);
      if ((rejectedData != null) && (rejectedData.length > 0))
      {
        ArrayList rejectedList = new ArrayList();
        for (int i = 0; i < rejectedData.length; i++)
        {
          ResignationApproval resignRejectedBean = new ResignationApproval();
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

        resignApproval.setRejList(rejectedList);
      }
    }
    catch (Exception localException) {
    }
  }

  public Object[][] generateEmpFlow(String empCode, String type, int order) {
    ReportingModel reporting = new ReportingModel();
    reporting.initiate(this.context, this.session);
    Object[][] result = reporting.generateEmpFlow(empCode, type, order);
    reporting.terminate();
    return result;
  }

  public String approveRejectApplication(HttpServletRequest request, String empCode, String resignAppNo, String status, String remarks, String approverId, String level, String acceptDate, String actualSeperationDate)
  {
    String applStatus = "pending";
    Object[][] empFlow = (Object[][])null;
    try
    {
      Object[][] changeStatus = new Object[1][4];
      changeStatus[0][0] = resignAppNo;
      changeStatus[0][1] = approverId;
      changeStatus[0][2] = status;
      changeStatus[0][3] = remarks;
      if (String.valueOf(status).equals("A"))
      {
    	  
        empFlow = generateEmpFlow(empCode, "Resign", 
          Integer.parseInt(level) + 1);
        applStatus = changeApplStatus(empFlow, 
          String.valueOf(resignAppNo), empCode, status, acceptDate, 
          actualSeperationDate);
      }
      

      if (String.valueOf(status).equals("R")) {
        Object[][] reject = new Object[1][2];
        reject[0][0] = String.valueOf(status);
        reject[0][1] = String.valueOf(resignAppNo);

        String query = "  UPDATE HRMS_RESIGN SET RESIGN_APPL_STATUS=? WHERE RESIGN_CODE=? ";

        Object[][] updateObj = new Object[1][3];
        updateObj[0][0] = acceptDate;
        updateObj[0][1] = actualSeperationDate;
        updateObj[0][2] = String.valueOf(resignAppNo);

        String updateQuery = " UPDATE HRMS_RESIGN SET RESIGN_ACCEPT_DATE=TO_DATE(?,'DD-MM-YYYY'),RESIGN_SEPR_DATE=TO_DATE(?,'DD-MM-YYYY')  WHERE RESIGN_CODE=? ";

        getSqlModel().singleExecute(updateQuery, updateObj);

        getSqlModel().singleExecute(query, reject);

        applStatus = "rejected";
      }
      String insertPathQuery = "  INSERT INTO  HRMS_RESIGN_PATH(RESIGN_PATH_ID,RESIGN_APPL_CODE,RESIGN_APPROVED_BY,RESIGN_STATUS,RESIGN_APPROVED_DATE,RESIGN_COMMENTS)  VALUES((SELECT NVL(MAX(RESIGN_PATH_ID),0)+1 FROM HRMS_RESIGN_PATH),?,?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?)  ";

      getSqlModel().singleExecute(insertPathQuery, changeStatus);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    createTemplateWithAlerts(request, status, resignAppNo, remarks, 
      empCode, approverId, level, empFlow, applStatus);

    return applStatus;
  }

  public void createTemplateWithAlerts(HttpServletRequest request, String status, String resignAppNo, String remarks, String empCode, String approverId, String level, Object[][] empFlow, String applStatus)
  {
    try
    {
      if (!String.valueOf(status).equals("P"))
      {
        try {
          MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
          processAlerts.initiate(this.context, this.session);
          String module = "Resignation";
          processAlerts.removeProcessAlert(
            String.valueOf(resignAppNo), module);
          processAlerts.terminate();
        }
        catch (Exception localException1) {
        }
        String applicant = ""; String oldApprover = ""; String newApprover = "";
        try {
          applicant = empCode;
          oldApprover = approverId;
          if (status.equals("B"))
            newApprover = "";
          else
            newApprover = String.valueOf(empFlow[0][0]);
        } catch (Exception e) {
          logger.error(e);
        }

        String empID = ""; String msgType = "";
        String applnID = String.valueOf(resignAppNo);
        String alertLevel = String.valueOf(Integer.parseInt(level) + 1);

        if (!newApprover.equals(""))
        {
          empID = newApprover;
          msgType = "A";

          EmailTemplateBody template = new EmailTemplateBody();
          template.initiate(this.context, this.session);

          template
            .setEmailTemplate("Resignation Application Sent to second approver");

          template.getTemplateQueries();

          EmailTemplateQuery templateQuery1 = template
            .getTemplateQuery(1);
          templateQuery1.setParameter(1, oldApprover);

          EmailTemplateQuery templateQuery2 = template
            .getTemplateQuery(2);
          templateQuery2.setParameter(1, newApprover);

          EmailTemplateQuery templateQuery3 = template
            .getTemplateQuery(3);
          templateQuery3.setParameter(1, applnID);

          EmailTemplateQuery templateQuery4 = template
            .getTemplateQuery(4);
          templateQuery4.setParameter(1, oldApprover);

          EmailTemplateQuery templateQuery5 = template
            .getTemplateQuery(5);
          templateQuery5.setParameter(1, applnID);

          EmailTemplateQuery templateQuery6 = template
            .getTemplateQuery(6);
          templateQuery6.setParameter(1, newApprover);

          EmailTemplateQuery templateQuery7 = template
            .getTemplateQuery(7);
          templateQuery7.setParameter(1, applnID);

          template.configMailAlert();
          template.sendApplicationMail();
          template.clearParameters();
          template.terminate();

          EmailTemplateBody template1 = new EmailTemplateBody();
          template1.initiate(this.context, this.session);

          template1
            .setEmailTemplate("Resignation Mail to employee regarding first approval");

          template1.getTemplateQueries();

          EmailTemplateQuery templateQuery8 = template1
            .getTemplateQuery(7);
          templateQuery8.setParameter(1, oldApprover);

          EmailTemplateQuery templateQuery9 = template1
            .getTemplateQuery(1);
          templateQuery9.setParameter(1, applicant);

          EmailTemplateQuery templateQuery10 = template1
            .getTemplateQuery(2);
          templateQuery10.setParameter(1, applnID);

          EmailTemplateQuery templateQuery11 = template1
            .getTemplateQuery(3);
          templateQuery11.setParameter(1, oldApprover);

          EmailTemplateQuery templateQuery12 = template1
            .getTemplateQuery(4);
          templateQuery12.setParameter(1, applnID);

          EmailTemplateQuery templateQuery13 = template1
            .getTemplateQuery(5);
          templateQuery13.setParameter(1, newApprover);

          EmailTemplateQuery templateQuery14 = template1
            .getTemplateQuery(6);
          templateQuery14.setParameter(1, applnID);

          template1.configMailAlert();

          logger.info("before send mail====");
          try {
            template1.sendApplicationMail();
          } catch (Exception e) {
            e.printStackTrace();
          }
          logger.info("after send mail====");
          template1.clearParameters();
          template1.terminate();
        }
        else {
          empID = applicant;
          msgType = "I";

          EmailTemplateBody template1 = new EmailTemplateBody();
          template1.initiate(this.context, this.session);

          template1
            .setEmailTemplate("Resignation Application Approver Mail to Employee");

          template1.getTemplateQueries();

          EmailTemplateQuery templateQuery1 = template1
            .getTemplateQuery(1);
          templateQuery1.setParameter(1, oldApprover);

          EmailTemplateQuery templateQuery2 = template1
            .getTemplateQuery(2);
          templateQuery2.setParameter(1, applicant);

          EmailTemplateQuery templateQuery3 = template1
            .getTemplateQuery(3);
          templateQuery3.setParameter(1, applnID);

          EmailTemplateQuery templateQuery4 = template1
            .getTemplateQuery(4);
          templateQuery4.setParameter(1, applnID);

          EmailTemplateQuery templateQuery5 = template1
            .getTemplateQuery(5);
          templateQuery5.setParameter(1, applnID);

          EmailTemplateQuery templateQuery6 = template1
            .getTemplateQuery(6);
          templateQuery6.setParameter(1, oldApprover);

          template1.configMailAlert();

          String keepInformedId = " SELECT  RESIGN_KEEP_INFORMED FROM HRMS_RESIGN  WHERE RESIGN_CODE=" + 
            applnID;

          Object[][] keepInformedObj = getSqlModel().getSingleResult(
            keepInformedId);
          String keepInfo = "";
          if ((keepInformedObj != null) && (keepInformedObj.length > 0)) {
            keepInfo = String.valueOf(keepInformedObj[0][0]);
            template1.sendApplicationMailToKeepInfo(keepInfo);
          }

          String actualStataus = "";
          if (status.equals("P")) {
            actualStataus = "Pending";
          }
          if (status.equals("A")) {
            actualStataus = "Approved";
          }
          if (status.equals("R")) {
            actualStataus = "Rejected";
          }

          String alternateApprover = (empFlow != null) && (empFlow.length > 0) ? 
            String.valueOf(empFlow[0][3]) : "";

          String link = "/settlement/ResignationApplication_retriveDetails.action";
          String linkParam = "resignApplicationCode=" + applnID + 
            "&resignAppStatus=" + status;

          template1.sendProcessManagerAlert(oldApprover, 
            "Resignation", "I", applnID, alertLevel, linkParam, 
            link, actualStataus, applicant, alternateApprover, 
            keepInfo, applicant, oldApprover);

          template1.sendApplicationMail();
          template1.clearParameters();
          template1.terminate();
        }
      }
    }
    catch (Exception e) {
      logger.error(e);
      e.printStackTrace();
    }
  }

  public String changeApplStatus(Object[][] empFlow, String resignAppNo, String empId, String status, String acceptDate, String actualSeperationDate)
  {
    String applStatus = "pending";

    if ((empFlow != null) && (empFlow.length != 0))
    {
      Object[][] updateApprover = new Object[1][3];
      updateApprover[0][0] = empFlow[0][2];
      updateApprover[0][1] = empFlow[0][0];
      updateApprover[0][2] = resignAppNo;

      String query = " UPDATE HRMS_RESIGN SET RESIGN_LEVEL=? , RESIGN_APPROVED_BY=? WHERE RESIGN_CODE=? ";

      getSqlModel().singleExecute(query, updateApprover);

      applStatus = "forwarded";
    }
    else {
      Object[][] statusChanged = new Object[1][2];
      statusChanged[0][0] = "A";
      statusChanged[0][1] = resignAppNo;

      String query = " UPDATE HRMS_RESIGN SET RESIGN_APPL_STATUS=? WHERE RESIGN_CODE=? ";

      getSqlModel().singleExecute(query, statusChanged);

      applStatus = "approved";
    }

    Object[][] updateObj = new Object[1][3];
    updateObj[0][0] = acceptDate;
    updateObj[0][1] = actualSeperationDate;
    updateObj[0][2] = String.valueOf(resignAppNo);

    String updateQuery = " UPDATE HRMS_RESIGN SET RESIGN_ACCEPT_DATE=TO_DATE(?,'DD-MM-YYYY'),RESIGN_SEPR_DATE=TO_DATE(?,'DD-MM-YYYY')  WHERE RESIGN_CODE=? ";

    getSqlModel().singleExecute(updateQuery, updateObj);

    return applStatus;
  }
}