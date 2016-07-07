package org.struts.action.settlement;

import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.settlement.ResignationHRApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.settlement.ResignationHRApprovalModel;
import org.struts.lib.ParaActionSupport;

public class ResignationHRApprovalAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(ResignationHRApprovalAction.class);
  ResignationHRApproval resignHrApproval;

  public void prepare_local()
    throws Exception
  {
    this.resignHrApproval = new ResignationHRApproval();
    this.resignHrApproval.setMenuCode(1021);
  }

  public Object getModel()
  {
    return this.resignHrApproval;
  }

  public ResignationHRApproval getResignHrApproval() {
    return this.resignHrApproval;
  }

  public void setResignHrApproval(ResignationHRApproval resignHrApproval) {
    this.resignHrApproval = resignHrApproval;
  }

  public void prepare_withLoginProfileDetails()
    throws Exception
  {
    if (this.resignHrApproval.getWaveOffPeriod().equals(""))
      this.resignHrApproval.setWaveOffPeriod("0");
  }

  public String callstatus()
  {
    try
    {
      ResignationHRApprovalModel model = new ResignationHRApprovalModel();
      model.initiate(this.context, this.session);
      System.out.println("hi------------");
      model.getAllTypeOfApplications(this.resignHrApproval, this.request);
      this.resignHrApproval.setListType("pending");
      model.terminate();
    }
    catch (Exception localException) {
    }
    return "success";
  }

  public String getApprovedList()
    throws Exception
  {
    try
    {
      ResignationHRApprovalModel model = new ResignationHRApprovalModel();
      model.initiate(this.context, this.session);
      model.getApprovedList(this.resignHrApproval, this.request);
      this.resignHrApproval.setListType("approved");
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in getApprovedList------" + e);
    }
    return "success";
  }

  public String getRejectedList() throws Exception
  {
    try
    {
      ResignationHRApprovalModel model = new ResignationHRApprovalModel();
      model.initiate(this.context, this.session);
      model.getRejectedList(this.resignHrApproval, this.request);
      this.resignHrApproval.setListType("rejected");
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in getApprovedList------" + e);
    }
    return "success";
  }

  public String retriveForApproval()
  {
    try {
      ResignationHRApprovalModel model = new ResignationHRApprovalModel();
      model.initiate(this.context, this.session);

      String resignAppCode = this.request.getParameter("resignApplicationNo");

      String status = this.request.getParameter("applicationStatus");

      this.resignHrApproval.setResignCode(resignAppCode);
      model.getEmployeeDetails(this.resignHrApproval, this.request);
      if (status.equals("Y"))
      {
        this.resignHrApproval.setShowFlag("false");
        this.resignHrApproval.setApprovalFlag("false");
        model.getHrApprovalDetails(this.resignHrApproval);
      }

      if (status.equals("Z"))
      {
        this.resignHrApproval.setShowFlag("false");
        this.resignHrApproval.setApprovalFlag("false");
        model.getHrApprovalDetails(this.resignHrApproval);
      }

      model.terminate();
    }
    catch (Exception localException) {
    }
    return "resignHrApprovalForm";
  }

  public String approveRejApplication()
  {
    try
    {
      ResignationHRApprovalModel model = new ResignationHRApprovalModel();
      model.initiate(this.context, this.session);
      String Status = this.resignHrApproval.getStatus();
      String actualNtPeriode = this.resignHrApproval.getNoticePeriodActual();
      String hrntperiode = this.resignHrApproval.getNoticeperiodselect();
      String wavePeriode = this.resignHrApproval.getWaveOffPeriod();
      String apprCode = this.resignHrApproval.getApprCode();
      String hrComment = this.resignHrApproval.getHrComments();
      String regnCode = this.resignHrApproval.getResignCode();
      String withDraw = this.resignHrApproval.getWithDrawn();
      String chkApprStatus = this.resignHrApproval.getCheckApproveRejectStatus();
      Object[][] empFlow = generateEmpFlow(this.resignHrApproval.getEmployeeCode(), "Resign", 1);
      String appStatus = model.approveRejectApplication(Status, actualNtPeriode, hrntperiode, 
        wavePeriode, apprCode, hrComment, regnCode, withDraw, chkApprStatus, this.resignHrApproval, this.request);
      String[][] respEmpCode = model.getResponsibleEmpForCheckList();
      String deptCode = this.resignHrApproval.getDeptCode();
      String resignCode = this.resignHrApproval.getResignCode();
      if (appStatus.equals("rejected")) {
        addActionMessage("Your Application has been rejected  successfully");
      }
      else if (appStatus.equals("approved")) {
        addActionMessage("Your Application has been approved successfully");
        String strRespEmpCode = "";
        String strRespDeptCode = "";
        for (int i = 0; i < respEmpCode.length; i++) {
          strRespEmpCode = String.valueOf(respEmpCode[i][0]);
          strRespDeptCode = String.valueOf(respEmpCode[i][1]);
          model.addDepartmentClearance(resignCode, strRespDeptCode);
          sendMailToDept(this.resignHrApproval, empFlow, strRespEmpCode, resignCode, strRespDeptCode);
        }

      }

      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "resignHrApprovalFormJsp";
  }

  public void sendMailToDept(ResignationHRApproval resignHrApproval, Object[][] empFlow, String strResEmpCode, String resignCode, String strRespDeptCode)
  {
    try {
      System.out.println("==============EMPLOYEE CODE===============" + resignHrApproval.getEmployeeCode());
      String applicantID = resignHrApproval.getEmployeeCode();
      String respEmpForChecklist = String.valueOf(strResEmpCode);
      String approverID = String.valueOf(empFlow[0][0]);
      String hrId = resignHrApproval.getUserEmpId();
      String module = "HRM HRM";

      String level = "1";
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      template.setEmailTemplate("HR TO DEPARTMENT CLEARANCE MAIL");
      template.getTemplateQueries();
      try {
        EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
        templateQuery1.setParameter(1, approverID);
      }
      catch (Exception localException1)
      {
      }
      try {
        EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
        templateQuery2.setParameter(1, respEmpForChecklist);
      }
      catch (Exception localException2) {
      }
      try {
        EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
        templateQuery3.setParameter(1, applicantID);
      }
      catch (Exception localException3) {
      }
      try {
        EmailTemplateQuery templateQuery4 = template
          .getTemplateQuery(4);
        templateQuery4.setParameter(1, applicantID);
      }
      catch (Exception localException4) {
      }
      System.out.println("#################################" + strRespDeptCode);
      try {
        EmailTemplateQuery templateQuery15 = template.getTemplateQuery(5);

        templateQuery15.setParameter(1, strRespDeptCode);
      }
      catch (Exception localException5) {
      }
      try {
        EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
        templateQuery6.setParameter(1, respEmpForChecklist);
      }
      catch (Exception localException6) {
      }
      try {
        EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
        templateQuery7.setParameter(1, hrId);
      }
      catch (Exception localException7) {
      }
      template.configMailAlert();
      template.sendApplicationMail();
      template.clearParameters();
      template.terminate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String backToList()
  {
    return "resignHrApprovalFormJsp";
  }

  public String f9appraction()
    throws Exception
  {
    String query = "  SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,NVL(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME,'') \t,HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC    WHERE EMP_STATUS='S'   ORDER BY HRMS_EMP_OFFC.EMP_ID ";

    String[] headers = { getMessage("employee.id"), getMessage("employee") };

    String[] headerWidth = { "20", "80" };

    String[] fieldNames = { "apprToken", "apprName", "apprCode" };

    int[] columnIndex = { 0, 1, 2 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }
}