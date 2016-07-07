
package org.struts.action.settlement;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.settlement.ResignationApplication;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.paradyne.model.settlement.ResignationApplicationModel;
import org.paradyne.model.settlement.ResignationApprovalModel;
import org.struts.lib.ParaActionSupport;

public class ResignationApplicationAction extends ParaActionSupport
{
  private static final long serialVersionUID = 1L;
  static Logger logger = Logger.getLogger(ResignationApplicationAction.class);
  ResignationApplication resignApp;

  public void prepare_local()
    throws Exception
  {
    this.resignApp = new ResignationApplication();
    this.resignApp.setMenuCode(1017);
  }

  public Object getModel() {
    return this.resignApp;
  }

  public ResignationApplication getResignApp() {
    return this.resignApp;
  }

  public void setResignApp(ResignationApplication resignApp) {
    this.resignApp = resignApp;
  }

  public void prepare_withLoginProfileDetails() throws Exception
  {
    try
    {
      getNavigationPanel(1);
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      model.getAllTypeOfApplications(this.resignApp, this.request, 
        this.resignApp.getUserEmpId());

      this.resignApp.setListType("pending");
      model.getEmployeeDetails(this.resignApp);
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in prepare_withLoginProfileDetails--------" + 
        e);
      e.printStackTrace();
    }
  }

  public void setApproverList(String empCode)
  {
    try {
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      ReportingModel model1 = new ReportingModel();
      model1.initiate(this.context, this.session);
      Object[][] empFlow = model1.generateEmpFlow(empCode, "Resign");
      model.setApproverData(this.resignApp, empFlow);
      model1.terminate();
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in setApproverList------" + e);
      e.printStackTrace();
    }
  }

  public String addNew() {
    try {
      String source = this.request.getParameter("src");
      this.resignApp.setSource(source);
      getNavigationPanel(2);
      setApproverList(this.resignApp.getUserEmpId());
      this.resignApp.setButtonFlag("false");
      if (this.resignApp.isGeneralFlag())
        this.resignApp.setShowFlag("false");
    }
    catch (Exception e) {
      logger.error("Exception in addNew--------" + e);
      e.printStackTrace();
    }
    return "resignApplicationJsp";
  }

  public String back() {
    try {
      getNavigationPanel(1);
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      model.getAllTypeOfApplications(this.resignApp, this.request, 
        this.resignApp.getUserEmpId());
      this.resignApp.setListType("pending");
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in back------" + e);
    }
    return "success";
  }

  public String reset() {
    try {
      getNavigationPanel(2);

      if (!this.resignApp.isGeneralFlag()) {
        this.resignApp.setEmpCode("");
        this.resignApp.setEmpName("");
        this.resignApp.setEmpToken("");
        this.resignApp.setDesignationName("");
        this.resignApp.setBranchName("");
        this.resignApp.setDateOfJoin("");
        this.resignApp.setDraftList(null);
      }
      this.resignApp.setSeperationDate("");
      this.resignApp.setAppReason("");
      this.resignApp.setAppComment("");
      this.resignApp.setResignCode("");
      this.resignApp.setResignDate("");
      this.resignApp.setStatus("");
      this.resignApp.setDraftList(null);
      this.resignApp.setKeepInformedEmpId("");
      this.resignApp.setKeepInformedEmpName("");
      this.resignApp.setKeepInformedList(null);
      this.resignApp.setEmployeeId("");
      this.resignApp.setEmployeeName("");
      this.resignApp.setEmployeeToken("");
      
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "resignApplicationJsp";
  }

  public String getApprovedList() throws Exception
  {
    try {
      getNavigationPanel(1);
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      model.getApprovedList(this.resignApp, this.request, this.resignApp.getUserEmpId());
      this.resignApp.setListType("approved");
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in getApprovedList------" + e);
    }
    return "success";
  }

  public String getRejectedList() throws Exception {
    try {
      getNavigationPanel(1);
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      model.getRejectedList(this.resignApp, this.request, this.resignApp.getUserEmpId());
      this.resignApp.setListType("rejected");
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in getRejectedList------" + e);
    }
    return "success";
  }

  public String retriveDetails() {
    try {
      String source = this.request.getParameter("src");
      String isKeepInformTo = "";
      this.resignApp.setSource(source);

      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      String resignAppCode = this.request
        .getParameter("resignApplicationCode");
      String status = this.request.getParameter("resignAppStatus");
      this.resignApp.setResignCode(resignAppCode);

      String empCode = " SELECT RESIGN_EMP FROM HRMS_RESIGN  WHERE RESIGN_CODE=" + 
        this.resignApp.getResignCode();

      Object[][] empCodeObj = model.getSqlModel()
        .getSingleResult(empCode);
      if ((empCodeObj != null) && (empCodeObj.length > 0)) {
        this.resignApp.setEmpCode(String.valueOf(empCodeObj[0][0]));
      }
      model.getRecord(this.resignApp);
      model.getApplicationDetails(this.resignApp);
      isKeepInformTo = model.getKeepInformedSavedRecord(this.resignApp);

      setApproverList(this.resignApp.getEmpCode());
      if (status.equals("D")) {
        getNavigationPanel(6);
      }
      if (status.equals("P")) {
        this.resignApp.setShowFlag("false");
        this.resignApp.setImageFlag("false");
        getNavigationPanel(4);
        if (isKeepInformTo.equals("Y")) {
          getNavigationPanel(5);
        }
        model.setApproverDetails(this.resignApp);
      }
      if ((status.equals("A")) || (status.equals("R")) || 
        (status.equals("Y")) || (status.equals("Z"))) {
        this.resignApp.setImageFlag("false");
        this.resignApp.setShowFlag("false");
        model.setApproverDetails(this.resignApp);
        this.resignApp.setPrevAppCommentListFlag("true");
        this.resignApp.setApprovalFlag("true");
        if (isKeepInformTo.equals("Y")) {
          getNavigationPanel(5);
        }
        if ((status.equals("Y")) || (status.equals("Z"))) {
          this.resignApp.setHrApprovalFlag("true");
          model.sethrApprovalDetails(this.resignApp);
        }
        getNavigationPanel(5);
      }
      if (status.equals("W")) {
        this.resignApp.setImageFlag("false");
        this.resignApp.setShowFlag("false");
        getNavigationPanel(5);
      }
      model.terminate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return "resignApplicationJsp";
  }

  public String withdrawApplication() {
    try {
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      boolean flag = model.withdrawApplication(this.resignApp);
      Object[][] empFlow = generateEmpFlow(resignApp.getEmpCode(),
				"Resign", 1);
      if (flag) {
        sendProcessManagerAlertForWithdraw();
        addActionMessage("Record withdrawn successfully");
        String applicantID = resignApp.getEmpCode();
		String approverID = String.valueOf(empFlow[0][0]);
		String module = "Resignation";
		String applnID = resignApp.getResignCode();
		String level = "1";
		EmailTemplateBody template = new EmailTemplateBody();
		template.initiate(context, session);
		template.setEmailTemplate("RESIGNATION  WITHDRAW APPLICATION - APPLICANT TO APPROVER");
		template.getTemplateQueries();
		EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
		templateQuery1.setParameter(1, applicantID);
		EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
		templateQuery2.setParameter(1, approverID);
		// Subject + Body
		EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
		templateQuery3.setParameter(1, applnID);

		EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
		templateQuery4.setParameter(1, applnID);

		EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
		templateQuery5.setParameter(1, approverID);

		template.configMailAlert();
		String keepInformedId= " SELECT  RESIGN_KEEP_INFORMED FROM HRMS_RESIGN "
			+" WHERE RESIGN_CODE="+applnID;
		Object[][] keepInformedObj = model.getSqlModel().getSingleResult(keepInformedId);
		 
		if(keepInformedObj!=null && keepInformedObj.length >0)
		{
			String keepInfo=String.valueOf(keepInformedObj[0][0]);
			template.sendApplicationMailToKeepInfo(keepInfo);
		}
		try {
			if (!String.valueOf(empFlow[0][3]).equals("0")) {
				logger
						.info("sendApplicationMailToAlternateApprover");
				template
						.sendApplicationMailToAlternateApprover(String
								.valueOf(empFlow[0][3]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		final Properties alertProp;
		FileInputStream alertFin;
		alertFin = new FileInputStream(getText("data_path")
				+ "/Alerts/alertLinks.properties");
		alertProp = new Properties();
		alertProp.load(alertFin);
		final String link = "/settlement/ResignationApplication_retriveDetails.action";
		final String linkParam = "resignApplicationCode=" + applnID + "&resignAppStatus=W";
		  String message = alertProp.getProperty("withdrawAlertMessage");
		message = message.replace("<#EMP_NAME#>", this.resignApp
				.getEmpName().trim());
		message = message.replace("<#APPL_TYPE#>", "Resignation");
		/* template.sendProcessManagerAlertWithdraw(applicantID, module, "I",
					applnID, level, linkParam, link, message, "Withdraw",
					applicantID, applicantID,keepInformedId,approverID);*/
		template.sendApplicationMail();
		template.clearParameters();
		template.terminate();

      } else {
        addActionMessage("Record can't be withdrawn");
      }
      model.getAllTypeOfApplications(this.resignApp, this.request, 
        this.resignApp.getUserEmpId());
      this.resignApp.setListType("pending");

      getNavigationPanel(1);
      model.terminate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    if (this.resignApp.getSource().equals("mymessages"))
      return "mymessages";
    if (this.resignApp.getSource().equals("myservices")) {
      return "serviceJsp";
    }
    return "success";
  }

  public String retriveForApproval()
  {
    try
    {
      String source = this.request.getParameter("src");
      this.resignApp.setSource(source);

      getNavigationPanel(7);
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      String resignAppCode = this.request.getParameter("resignApplicationNo");

      String status = this.request.getParameter("applicationStatus");

      logger.info("resignAppCode " + resignAppCode);
      logger.info("status " + status);
      this.resignApp.setResignCode(resignAppCode);

      String empCode = " SELECT RESIGN_EMP FROM HRMS_RESIGN  WHERE RESIGN_CODE=" + 
        this.resignApp.getResignCode();

      Object[][] empCodeObj = model.getSqlModel()
        .getSingleResult(empCode);
      if ((empCodeObj != null) && (empCodeObj.length > 0)) {
        this.resignApp.setEmpCode(String.valueOf(empCodeObj[0][0]));
      }

      model.getRecord(this.resignApp);
      model.getApplicationDetails(this.resignApp);
      model.setActualSeperationDate(this.resignApp);
      model.getKeepInformedSavedRecord(this.resignApp);
      setApproverList(this.resignApp.getEmpCode());
      if (status.equals("P")) {
        this.resignApp.setButtonFlag("true");
        this.resignApp.setCloseBtnFlag("true");
        this.resignApp.setApproverDtlFlag("true");
        this.resignApp.setShowFlag("false");

        boolean result = model.setApproverDetails(this.resignApp);
        if (result)
          this.resignApp.setPrevAppCommentListFlag("true");
        else {
          try {
            Date date = new Date();
            SimpleDateFormat formater = new SimpleDateFormat(
              "dd-MM-yyyy");
            String sysDate = formater.format(date);
            this.resignApp.setAcceptDate(sysDate);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }

      if ((status.equals("A")) || (status.equals("R")) || (status.equals("Y"))) {
        this.resignApp.setButtonFlag("false");
        this.resignApp.setCloseBtnFlag("true");
        this.resignApp.setApproverDtlFlag("false");
        this.resignApp.setShowFlag("false");
        model.setApproverDetails(this.resignApp);
        this.resignApp.setPrevAppCommentListFlag("true");
      }

      this.resignApp.setApprovalFlag("true");
      this.resignApp.setImageFlag("false");
      model.terminate();
    } catch (Exception e) {
      logger
        .error("Exception in retriveForApproval-----------------" + 
        e);
      e.printStackTrace();
    }
    return "resignApplicationJsp";
  }

  public void sendProcessManagerAlertDraft()
  {
    try
    {
      FileInputStream alertFin = new FileInputStream(getText("data_path") + 
        "\\Alerts\\alertLinks.properties");
      Properties alertProp = new Properties();
      alertProp.load(alertFin);
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);

      String applicantID = this.resignApp.getEmpCode();
      String module = "Resignation";
      String applnID = this.resignApp.getResignCode();
      String level = "1";
      String link = "/settlement/ResignationApplication_retriveDetails.action";
      String linkParam = "resignApplicationCode=" + applnID + "&resignAppStatus=D";
      String message = alertProp.getProperty("draftAlertMessage");
      message = message.replace("<#EMP_NAME#>", this.resignApp.getEmpName().trim());
      message = message.replace("<#APPL_TYPE#>", "Resignation");
      template.sendProcessManagerAlertDraft(applicantID, module, "A", 
        applnID, level, linkParam, link, message, "Draft", 
        applicantID, applicantID);
      template.terminate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String save() {
    try {
      getNavigationPanel(2);
      String status = this.request.getParameter("checkStatus");
      logger.info("status-------------------" + status);
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      Object[][] empFlow = generateEmpFlow(this.resignApp.getEmpCode(), 
        "Resign", 1);
      String[] empCode = this.request.getParameterValues("keepInformedEmpId");
      String[] empName = this.request
        .getParameterValues("keepInformedEmpName");
      String[] serialNo = this.request.getParameterValues("serialNo");
      String[] keep = this.request
      .getParameterValues("keepInformedEmpId");

      String flag = "";
      if ((empFlow != null) && (empFlow.length > 0)) {
        if (this.resignApp.getResignCode().equals("")) {
          flag = model.save(this.resignApp, status, 
            String.valueOf(empFlow[0][0]), 
            String.valueOf(empFlow[0][3]), empCode);
          if (flag.equals("saved")) {
            addActionMessage(getMessage("save"));
          } else {
            if (flag.equals("notSaved")) {
              addActionMessage(getMessage("save.error"));

              getNavigationPanel(2);
              return "resignApplicationJsp";
            }
            if (flag.equals("sameResg")) {
              addActionMessage("Resignation is already done for " + 
                this.resignApp.getEmpName() + 
                " on resignation date " + 
                this.resignApp.getResignDate());

              getNavigationPanel(2);
              return "resignApplicationJsp";
            }

            addActionMessage(getMessage("save.error"));

            getNavigationPanel(2);
            return "resignApplicationJsp";
          }
        }
        else {
          flag = model.update(this.resignApp, status, 
            String.valueOf(empFlow[0][0]), 
            String.valueOf(empFlow[0][3]), empCode);
          if (flag.equals("update")) {
            addActionMessage(getMessage("update")); } else {
            if (flag.equals("notUpdate")) {
              addActionMessage(getMessage("update.error"));
              reset();
              getNavigationPanel(2);
              return "resignApplicationJsp";
            }

            addActionMessage(getMessage("update.error"));
            reset();
            getNavigationPanel(2);
            return "resignApplicationJsp";
          }
        }
      }
      else {
        addActionMessage("Reporting Structure Not Defined for the Employee\n" + 
          this.resignApp.getEmpName());
        return "resignApplicationJsp";
      }

      if (status.equals("D")) {
        getNavigationPanel(3);
        setApproverList(this.resignApp.getEmpCode());
        model.displayIteratorValueForKeepInformed(serialNo, empCode, 
          empName, this.resignApp);
        if (this.resignApp.isGeneralFlag()) {
          this.resignApp.setShowFlag("false");
        }
        sendProcessManagerAlertDraft();
        if (this.resignApp.getSource().equals("mymessages"))
          return "mymessages";
        if (this.resignApp.getSource().equals("myservices")) {
          return "serviceJsp";
        }
        return "resignApplicationJsp";
      }

      if ((flag.equals("saved")) || (flag.equals("update")))
      {
        if (status.equals("P")) {
          try {
            ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
            processAlerts.initiate(this.context, this.session);

            processAlerts.removeProcessAlert(String.valueOf(this.resignApp.getResignCode()), 
              "Resignation");
            String applicantID = this.resignApp.getEmpCode();
            String approverID = String.valueOf(empFlow[0][0]);
            String module = "Resignation";
            String applnID = this.resignApp.getResignCode();
            String level = "1";
            EmailTemplateBody template = new EmailTemplateBody();
            template.initiate(this.context, this.session);
            template
              .setEmailTemplate("Resignation Application Submission Template");
            template.getTemplateQueries();
            EmailTemplateQuery templateQuery1 = template
              .getTemplateQuery(1);

            EmailTemplateQuery templateQuery2 = template
              .getTemplateQuery(2);
            templateQuery2.setParameter(1, applicantID);

            EmailTemplateQuery templateQuery3 = template
              .getTemplateQuery(3);
            templateQuery3.setParameter(1, approverID);

            EmailTemplateQuery templateQuery4 = template
              .getTemplateQuery(4);
            templateQuery4.setParameter(1, applnID);

            EmailTemplateQuery templateQuery5 = template
              .getTemplateQuery(5);
            templateQuery5.setParameter(1, applnID);

            template.configMailAlert();

            
            if (keep != null)
            {
              logger.info("sendApplicationMailToKeepInfo----------------------------------");
              template.sendApplicationMailToKeepInfo(keep);
            }

            String alertCCId = "0";

            if (keep != null) {
              for (int i = 0; i < keep.length; i++) {
                if (i == keep.length)
                  alertCCId = alertCCId + keep[i];
                else
                  alertCCId = alertCCId + "," + keep[i];
              }
            }
            try
            {
              if (!String.valueOf(empFlow[0][3]).equals("0")) {
                logger
                  .info("sendApplicationMailToAlternateApprover");
                template
                  .sendApplicationMailToAlternateApprover(
                  String.valueOf(empFlow[0][3]));
              }
            }
            catch (Exception e)
            {
              e.printStackTrace();
            }

            String link = "/settlement/ResignationApplication_retriveDetails.action";
            String linkParam = "resignApplicationCode=" + applnID + "&resignAppStatus=P";
            String approverId = approverID;
            try {
              template
                .sendProcessManagerAlert("", module, "I", applnID, 
                level, linkParam, link, "Pending", applicantID, 
                "", alertCCId, applicantID, applicantID);
            }
            catch (Exception e) {
              e.printStackTrace();
            }

            template.sendApplicationMail();
            template.clearParameters();

            template.terminate();

            EmailTemplateBody template_applicant = new EmailTemplateBody();
            template_applicant.initiate(this.context, this.session);
            template_applicant
              .setEmailTemplate("Resignation Application Applicant to First Approver");
            template_applicant.getTemplateQueries();
            EmailTemplateQuery templateQueryApp1 = template_applicant
              .getTemplateQuery(1);

            templateQueryApp1.setParameter(1, applicantID);
            EmailTemplateQuery templateQueryApp2 = template_applicant
              .getTemplateQuery(2);
            templateQueryApp2.setParameter(1, approverID);

            EmailTemplateQuery templateQueryApp3 = template_applicant
              .getTemplateQuery(3);
            templateQueryApp3.setParameter(1, approverID);
            EmailTemplateQuery templateQueryApp4 = template_applicant
              .getTemplateQuery(4);
            templateQueryApp4.setParameter(1, applnID);
            EmailTemplateQuery templateQueryApp5 = template_applicant
              .getTemplateQuery(5);
            templateQueryApp5.setParameter(1, applnID);

            template_applicant.configMailAlert();

            link = "/settlement/ResignationApplication_retriveForApproval.action";
            linkParam = "resignApplicationNo=" + applnID + "&applicationStatus=P";
            try
            {
              template_applicant.sendProcessManagerAlert(approverID, "Resignation", "A", 
                applnID, level, linkParam, link, "Pending", 
                applicantID, String.valueOf(empFlow[0][3]), "", "", 
                applicantID);
            }
            catch (Exception e) {
              e.printStackTrace();
            }

            template_applicant.sendApplicationMail();
            template_applicant.clearParameters();
            template_applicant.terminate();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
        }
        getNavigationPanel(1);
        model.getAllTypeOfApplications(this.resignApp, this.request, 
          this.resignApp.getUserEmpId());
        this.resignApp.setListType("pending");
      }
      model.terminate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    if (this.resignApp.getSource().equals("mymessages"))
      return "mymessages";
    if (this.resignApp.getSource().equals("myservices")) {
      return "serviceJsp";
    }
    return "success";
  }

  public void deleteProcessManagerAlert(String moduleName, String resignCode)
  {
    try
    {
      LeaveApplicationModel model = new LeaveApplicationModel();
      model.initiate(this.context, this.session);
      String query = " DELETE FROM HRMS_ALERT_MESSAGE WHERE ALERT_APPLICATION_ID=" + 
        resignCode + 
        " AND UPPER(ALERT_MODULE) LIKE '" + 
        moduleName.trim().toUpperCase() + "'";
      model.getSqlModel().singleExecute(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String delete() {
    try {
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      boolean result = model.delete(this.resignApp);
      if (result) {
        deleteProcessManagerAlert("Resignation", this.resignApp.getResignCode());
        addActionMessage("Record deleted successfully");
        reset();
      } else {
        addActionMessage("Record can't be deleted ");
      }
      model.getAllTypeOfApplications(this.resignApp, this.request, 
        this.resignApp.getUserEmpId());

      this.resignApp.setListType("pending");
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }

    getNavigationPanel(1);

    if (this.resignApp.getSource().equals("mymessages")) {
      return "mymessages";
    }
    return "success";
  }

  public String approveRejApplication()
  {
    try
    {
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);

      this.resignApp.setApprovalFlag("true");
      this.resignApp.setImageFlag("false");

      ResignationApprovalModel resignApprovalmodel = new ResignationApprovalModel();
      resignApprovalmodel.initiate(this.context, this.session);

      String appStatus = resignApprovalmodel.approveRejectApplication(
        this.request, this.resignApp.getEmpCode(), this.resignApp.getResignCode(), 
        this.resignApp.getCheckApproveRejectStatus(), 
        this.resignApp.getApproverComments(), this.resignApp.getUserEmpId(), 
        this.resignApp.getLevel(), this.resignApp.getAcceptDate(), 
        this.resignApp.getSeperationDateActual());

      logger.info("appStatus--------------" + appStatus);
      if (appStatus.equals("rejected"))
        addActionMessage("Application rejected successfully");
      else {
        addActionMessage("Application approved successfully");
      }

      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.resignApp.getSource().equals("mymessages")) {
      return "mymessages";
    }
    return "approvalJspPage";
  }

  public String backToApprovalList()
  {
    return "approvalJspPage";
  }

  public String f9action()
    throws Exception
  {
    String query = "  SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,NVL(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME,'') \t, NVL(RANK_NAME,' '),NVL(CENTER_NAME,'')    ,NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)   LEFT JOIN HRMS_EMP_QUA ON(HRMS_EMP_QUA.EMP_ID = HRMS_EMP_OFFC.EMP_ID)    LEFT JOIN HRMS_QUA ON(HRMS_QUA.QUA_ID=HRMS_EMP_QUA.QUA_MAST_CODE)     LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)";

    query = query + getprofileQuery(this.resignApp);
    query = query + " AND EMP_STATUS='S'";
    query = query + "\tORDER BY HRMS_EMP_OFFC.EMP_ID";

    String[] headers = { getMessage("employee.id"), getMessage("employee") };

    String[] headerWidth = { "20", "80" };

    String[] fieldNames = { "empToken", "empName", "designationName", 
      "branchName", "dateOfJoin", "empCode" };

    int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

    String submitFlag = "true";

    String submitToMethod = "ResignationApplication_clearFields.action";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String clearFields()
  {
    try
    {
      this.resignApp.setResignDate("");
      this.resignApp.setSeperationDate("");
      this.resignApp.setAppComment("");
      this.resignApp.setAppReason("");
      setApproverList(this.resignApp.getEmpCode());
    } catch (Exception e) {
      e.printStackTrace();
    }

    getNavigationPanel(2);
    return "resignApplicationJsp";
  }

  public String searchSavedRecord()
    throws Exception
  {
    String query = " SELECT  DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME), \tTO_CHAR(RESIGN_DATE,'DD-MM-YYYY') , \tNVL(RANK_NAME,' '),NVL(CENTER_NAME,''),  \t\tNVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '), \tNVL(TO_CHAR(RESIGN_TENT_SEPR_DATE,'DD-MM-YYYY'),' '), \tRESIGN_APPL_STATUS, \tHRMS_RESIGN.RESIGN_CODE,HRMS_EMP_OFFC.EMP_ID \tFROM HRMS_RESIGN  \t\tLEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP)\tLEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) \tLEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  ORDER BY RESIGN_CODE ";

    String[] headers = { getMessage("employee.id"), getMessage("employee") };

    String[] headerWidth = { "20", "80" };

    String[] fieldNames = { "empToken", "empName", "resignDate", 
      "designationName", "branchName", "dateOfJoin", 
      "seperationDate", "status", "resignCode", "empCode" };

    int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);

    return "f9page";
  }

  public String addKeepInformedEmpList() {
    try {
      String[] serialNo = this.request.getParameterValues("serialNo");

      String[] empCode = this.request.getParameterValues("keepInformedEmpId");

      String[] empName = this.request
        .getParameterValues("keepInformedEmpName");
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);

      model.displayIteratorValueForKeepInformed(serialNo, empCode, 
        empName, this.resignApp);
      setApproverList(this.resignApp.getEmpCode());

      model.setKeepInformed(serialNo, empCode, empName, this.resignApp);

      this.resignApp.setEmployeeId("");
      this.resignApp.setEmployeeName("");
      this.resignApp.setEmployeeToken("");
      if (this.resignApp.isGeneralFlag()) {
        this.resignApp.setShowFlag("false");
      }
      model.terminate();

      getNavigationPanel(2);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "resignApplicationJsp";
  }

  public String removeKeepInformed()
  {
    try {
      String[] serialNo = this.request.getParameterValues("serialNo");
      String[] empCode = this.request.getParameterValues("keepInformedEmpId");
      String[] empName = this.request
        .getParameterValues("keepInformedEmpName");
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      model.removeKeepInformedData(serialNo, empCode, empName, this.resignApp);
      setApproverList(this.resignApp.getEmpCode());

      if (this.resignApp.isGeneralFlag()) {
        this.resignApp.setShowFlag("false");
      }
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in removeKeepInformed--------" + e);
    }
    getNavigationPanel(2);
    return "resignApplicationJsp";
  }

  public String f9KeepInformedEmployee()
  {
    String[] eId = this.request.getParameterValues("keepInformedEmpId");
    String str = "0";
    if (eId != null) {
      for (int i = 0; i < eId.length; i++) {
        str = str + "," + eId[i];
      }

    }

    try
    {
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);
      String hrempId = model.getHRempCode(this.resignApp.getEmpCode());
      if (hrempId == null) {
        hrempId = "0";
      }
      String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, \tHRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID \tFROM HRMS_EMP_OFFC  WHERE EMP_STATUS='S'";

      query = query + "AND EMP_ID IN (" + hrempId + ")";
      query = query + " AND EMP_ID NOT IN(" + str + ") ";

      String[] headers = { getMessage("employee.id"), 
        getMessage("employee") };

      String[] headerWidth = { "20", "80" };

      String[] fieldNames = { "employeeToken", "employeeName", 
        "employeeId" };

      int[] columnIndex = { 0, 1, 2 };

      String submitFlag = "false";

      String submitToMethod = "";

      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
        submitFlag, submitToMethod);
      model.terminate();

      return "f9page";
    } catch (Exception e) {
      logger.error(e.getMessage());
    }return "";
  }

  public String getDetails()
  {
    try
    {
      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);

      String query = " SELECT RESIGN_REASON,RESIGN_REMARK FROM HRMS_RESIGN  WHERE RESIGN_CODE=" + 
        this.resignApp.getResignCode();

      Object[][] data = model.getSqlModel().getSingleResult(query);

      if ((data != null) && (data.length > 0)) {
        this.resignApp.setAppReason(checkNull(String.valueOf(data[0][0])));
        this.resignApp.setAppComment(checkNull(String.valueOf(data[0][1])));
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return "success";
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public void sendProcessManagerAlertForWithdraw()
  {
    try
    {
      ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
      processAlerts.initiate(this.context, this.session);

      processAlerts.removeProcessAlert(String.valueOf(this.resignApp.getResignCode()), 
        "Resignation");
      processAlerts.terminate();

      ResignationApplicationModel model = new ResignationApplicationModel();
      model.initiate(this.context, this.session);

      String query = "SELECT RESIGN_APPROVED_BY,RESIGN_KEEP_INFORMED FROM HRMS_RESIGN WHERE RESIGN_CODE=" + this.resignApp.getResignCode();

      Object[][] selectObj = model.getSqlModel().getSingleResult(query);

      String managerId = "";

      String keepInformedId = "";

      if ((selectObj != null) && (selectObj.length > 0))
      {
        managerId = String.valueOf(selectObj[0][0]);
        keepInformedId = String.valueOf(selectObj[0][1]);
      }

      FileInputStream alertFin = new FileInputStream(getText("data_path") + 
        "\\Alerts\\alertLinks.properties");
      Properties alertProp = new Properties();
      alertProp.load(alertFin);
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String msgType = "A";
      String applicantID = this.resignApp.getEmpCode();
      String module = "Resignation";
      String applnID = this.resignApp.getResignCode();
      String level = "1";
      String link = "/settlement/ResignationApplication_retriveDetails.action";
      String linkParam = "resignApplicationCode=" + applnID + "&resignAppStatus=W";
      String message = alertProp.getProperty("withdrawAlertMessage");
      message = message.replace("<#EMP_NAME#>", this.resignApp.getEmpName().trim());
      message = message.replace("<#APPL_TYPE#>", "Resignation");
      template.sendProcessManagerAlertWithdraw(applicantID, module, "I", 
        applnID, level, linkParam, link, message, "Withdraw", 
        applicantID, applicantID, keepInformedId, managerId);
      template.terminate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}