package org.struts.action.businessCardInfo;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.businessCardInfo.BusinessCardInfo;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.businessCardInfo.BusinessCardInfoModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class BusinessCardInfoAction extends ParaActionSupport
{
  private static final long serialVersionUID = 1L;
  static Logger logger = Logger.getLogger(BusinessCardInfoAction.class);
  BusinessCardInfo bean;

  public void prepare_local()
    throws Exception
  {
    this.bean = new BusinessCardInfo();
    this.bean.setMenuCode(2240);
  }
  public void prepare_withLoginProfileDetails() throws Exception {
    BusinessCardInfoModel model = new BusinessCardInfoModel();
    model.initiate(this.context, this.session);
    this.bean.setEmpId(this.bean.getUserEmpId());
    String source = this.request.getParameter("src");
    this.bean.setSource(source);
    String adminApproval = model.IsBcardDAdminFlag();
    boolean isAdminApproval = adminApproval.equals("Y");
    this.bean.setIsAdminApproval(isAdminApproval);
    this.bean.setEmpId(this.bean.getUserEmpId());
    model.terminate();
  }

  public Object getModel() {
    return this.bean;
  }

  public BusinessCardInfo getBean() {
    return this.bean;
  }

  public void setBean(BusinessCardInfo bean) {
    this.bean = bean;
  }

  public String input() {
    this.bean.setEmpFlag("true");
    getNavigationPanel(1);
    this.bean.setEmpId(this.bean.getUserEmpId());
    loadEmpDetails();
    return "success";
  }

  public String f9Employee() throws Exception {
    String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
    if ((this.bean.getUserProfileDivision() != null) && 
      (this.bean.getUserProfileDivision().length() > 0))
      query = query + "AND EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
    query = query + " ORDER BY EMP_ID";
    String[] headers = { getMessage("empToken"), getMessage("empName") };
    String[] headerWidth = { "10", "30" };
    String[] fieldNames = { "empToken", "empName", "empId" };
    int[] columnIndex = { 0, 1, 2 };
    String submitFlag = "true";
    String submitToMethod = "BusinessCardInfo_loadEmpDetails.action";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String loadEmpDetails() {
    BusinessCardInfoModel model = new BusinessCardInfoModel();
    model.initiate(this.context, this.session);
    String empId = this.bean.getEmpId();
    model.loadEmployeeInf(this.bean, empId);
    model.setBusinessCardSavedRecord(this.bean, this.bean.getBusCardApplCode());
    Object[][] empFlow = generateEmpFlow(this.bean.getEmpId(), 
      "BusinessCardInfo", 1);
    model.loadManager(this.bean, empFlow, this.bean.getIsAdminApproval());
    this.bean.setKeepInfoFlag("true");
    this.bean.setKiEmployeeId("");
    this.bean.setKiEmployeeName("");
    this.bean.setKiEmployeeToken("");
    this.bean.setEmpFlag("true");
    model.terminate();
    getNavigationPanel(1);
    return "success";
  }

  public String retriveDetails() {
    try {
      String source = this.request.getParameter("src");
      this.bean.setSource(source);
      BusinessCardInfoModel model = new BusinessCardInfoModel();
      model.initiate(this.context, this.session);
      String applicationCode = this.request.getParameter("applicationCode");
      String empCode = this.request.getParameter("empCode");
      String status = this.request.getParameter("appStatus");
      model.loadEmployeeInf(this.bean, empCode);
      model.setBusinessCardSavedRecord(this.bean, applicationCode);
      model.setEmployeeInformation(empCode, this.bean);
      Object[][] empFlow = generateEmpFlow(empCode, "BusinessCardInfo", 1);
      model.loadManager(this.bean, empFlow, this.bean.getIsAdminApproval());
      this.bean.setKeepInfoFlag("false");
      this.bean.setKiEmployeeId("");
      this.bean.setKiEmployeeName("");
      this.bean.setKiEmployeeToken("");
      this.bean.setEmpFlag("false");
      if (status.equals("D")) {
        getNavigationPanel(7);
      } else {
        model.getApproversDtl(this.bean);
        getNavigationPanel(5);
      }
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String saveBusinessCardInfo() {
    try {
      BusinessCardInfoModel model = new BusinessCardInfoModel();
      String result = "";
      model.initiate(this.context, this.session);
      String status = this.request.getParameter("status");
      Object[][] empFlow = generateEmpFlow(this.bean.getEmpId(), 
        "BusinessCardInfo", 1);

      if (empFlow == null) {
        addActionMessage("Reporting structure not defined for the employee\n" + 
          this.bean.getEmpName());
        getNavigationPanel(1);
        try {
          prepare_withLoginProfileDetails();
          return input();
        } catch (Exception e) {
          logger.error("Exception in save");
        }
      }
      String adminApproval = model.IsBcardDAdminFlag();
      String admin = model.getAdminAppDet(this.bean);
      if ((adminApproval == null) || (admin == null) || ((adminApproval.equals("N")) && (admin == null))) {
        addActionMessage("Please configure the Admin before Send for Approval or Draft");
        getNavigationPanel(6);
        return "success";
      }
      boolean isAdminApproval = adminApproval.equals("Y");

      String[] keepInformEmpIdIt = this.request
        .getParameterValues("keepInformedEmpIdIt");
      String strKiEmpCodeIt = "";
      if (keepInformEmpIdIt != null) {
        for (int cnt = 0; cnt < keepInformEmpIdIt.length; cnt++) {
          if (cnt == 0)
            strKiEmpCodeIt = 
              String.valueOf(keepInformEmpIdIt[cnt]);
          else {
            strKiEmpCodeIt = strKiEmpCodeIt + "," + 
              String.valueOf(keepInformEmpIdIt[cnt]);
          }
        }
      }
      if ((empFlow != null) && (empFlow.length > 0)) {
        String[] keepInfo = this.request
          .getParameterValues("keepInformedEmpIdIt");
        String module = "Business Card";
        Date date = new Date();
        SimpleDateFormat formater = new SimpleDateFormat(
          "dd/MM/yyyy");
        String sysDate = formater.format(date);
        String applicant = this.bean.getEmpId();

        if (this.bean.getBusCardApplCode().equals("")) {
          result = model.saveBusinessCardInformation(this.bean, 
            status, strKiEmpCodeIt, empFlow);
          String applID = model.getBCardApplCode(this.bean);
          this.bean.setBusCardApplCode(applID);
          model.loadManager(this.bean, empFlow, 
            this.bean.getIsAdminApproval());
          model.keepInformEmployee(this.bean);
          if (result.equals("saved")) {
            if (status.equals("D")) {
              sendProcessManagerAlertDraft();
              addActionMessage("Your application has been Draft successfully.");
            } else if (status.equals("P")) {
              sendApplicationAlert(applID, module, applicant, 
                empFlow, isAdminApproval, 
                adminApproval, sysDate, keepInfo);
              addActionMessage("Your application has been sent for approval successfully.");
            } else {
              addActionMessage(getMessage("Record saved successfully"));
            }
          } else if (result.equals("notsaved"))
            addActionMessage("Record not saved successfully");
        }
        else {
          result = model.updateEmployeeCardInformation(this.bean, 
            status, strKiEmpCodeIt, empFlow);
          model.loadManager(this.bean, empFlow, 
            this.bean.getIsAdminApproval());
          model.keepInformEmployee(this.bean);
          if (result.equals("updated")) {
            if (status.equals("D")) {
              addActionMessage("Your application has been Draft successfully.");
            } else if (status.equals("P"))
            {
              try {
                MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
                processAlerts.initiate(this.context, this.session);
                processAlerts.removeProcessAlert(
                  this.bean.getBusCardApplCode(), 
                  "Business Card");
                processAlerts.terminate();
              } catch (Exception e) {
                e.printStackTrace();
              }
              String applID = this.bean.getBusCardApplCode();
              sendApplicationAlert(applID, module, applicant, 
                empFlow, isAdminApproval, 
                adminApproval, sysDate, keepInfo);
              addActionMessage("Your application has been sent for approval successfully.");
            } else {
              addActionMessage(getMessage("Record saved successfully"));
            }
          } else if (result.equals("notupdated"))
            addActionMessage("Record not updated successfully");
        }
      }
      else {
        addActionMessage("Reporting structure not define for Employee");
        this.bean.setEmpTokenp("");
        this.bean.setEmpNamep("");
        this.bean.setDesigp("");
        this.bean.setDeptp("");
        this.bean.setCompanyNamep("");
        this.bean.setBranchp("");
        this.bean.setPinCodep("");
        this.bean.setLocationp("");
        this.bean.setOfficeNump("");
        this.bean.setMobilep("");
        this.bean.setFaxNump("");
        this.bean.setEmailIdp("");
        this.bean.setWebSiteAddrp("");
        this.bean.setQtyOfCards("");
        this.bean.setReqDate("");
        this.bean.setComment("");
        this.bean.setSysInfoFlag("");
        this.bean.setApproverCode("");
      }
      this.bean.setKeepInfoFlag("true");
      this.bean.setEmpFlag("true");
      model.terminate();
      getNavigationPanel(1);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    if (this.bean.getSource().equals("mymessages"))
      return "mymessages";
    if (this.bean.getSource().equals("myservices")) {
      return "serviceJsp";
    }
    return "success";
  }

  public void sendApplicationAlert(String applnID, String module, String applicant, Object[][] empFlow, boolean isAdminApproval, String adminApproval, String applnDate, String[] keepInfo)
  {
    try
    {
      BusinessCardInfoModel model = new BusinessCardInfoModel();
      model.initiate(this.context, this.session);
      String MgrApproved = model.IsMgrApproved(this.bean.getBusCardApplCode());
      String level = "1";
      String approver = String.valueOf(empFlow[0][0]);
      String admin = model.getAdminAppDet(this.bean);

      model.terminate();
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      template
        .setEmailTemplate("BUSINESS CARD INFORMATION - APPLICANT TO APPROVER");
      template.getTemplateQueries();
      try {
        EmailTemplateQuery templateQuery1 = template
          .getTemplateQuery(1);
        templateQuery1.setParameter(1, applicant);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery2 = template
          .getTemplateQuery(2);
        templateQuery2.setParameter(1, approver);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery3 = template
          .getTemplateQuery(3);
        templateQuery3.setParameter(1, applnID);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery4 = template
          .getTemplateQuery(4);
        templateQuery4.setParameter(1, approver);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery5 = template
          .getTemplateQuery(5);
        templateQuery5.setParameter(1, applicant);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery6 = template
          .getTemplateQuery(6);
        templateQuery6.setParameter(1, applicant);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery7 = template
          .getTemplateQuery(7);
        templateQuery7.setParameter(1, applnID);
      } catch (Exception e) {
        e.printStackTrace();
      }
      template.configMailAlert();

      String[] link_param = new String[3];
      String[] link_label = new String[3];
      String applicationType = "BusinessCardInfoAppl";

      link_param[0] = 
        (applicationType + "#" + applicant + "#" + applnID + 
        "#" + "A" + "#" + "..." + "#" + approver + "#" + 
        MgrApproved + "#" + adminApproval + "#" + admin);
      link_param[1] = 
        (applicationType + "#" + applicant + "#" + applnID + 
        "#" + "R" + "#" + "..." + "#" + approver + "#" + 
        MgrApproved + "#" + adminApproval + "#" + admin);
      link_param[2] = 
        (applicationType + "#" + applicant + "#" + applnID + 
        "#" + "B" + "#" + "..." + "#" + approver + "#" + 
        MgrApproved + "#" + adminApproval + "#" + admin);
     /* link_label[0] = "Approve";
      link_label[1] = "Reject";
      link_label[2] = "Send Back";*/

      String alertlink = "/businessCard/BusinessCardInfo_getApproverDetails.action";
      String linkParam = "hiddenAppCode=" + applnID + "&empId=" + 
        applicant + "&status=P&pplicationLevel=1";
      try {
        template.sendProcessManagerAlert(approver, module, "A", 
          applnID, level, linkParam, alertlink, "Pending", 
          applicant, String.valueOf(empFlow[0][3]), "", "", 
          applicant);
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (keepInfo != null) {
        template.sendApplicationMailToKeepInfo(keepInfo);
      }

      //template.addOnlineLink(this.request, link_param, link_label);
      template.sendApplicationMail();
      template.clearParameters();
      template.terminate();

      EmailTemplateBody templateApplicant = new EmailTemplateBody();
      templateApplicant.initiate(this.context, this.session);
      templateApplicant
        .setEmailTemplate("BUSINESS CARD INFORMATION -  APPLICATION DETAILS TO APPLICANT");
      templateApplicant.getTemplateQueries();
      try {
        EmailTemplateQuery templateQuery1 = templateApplicant
          .getTemplateQuery(1);
      }
      catch (Exception e)
      {
        EmailTemplateQuery templateQuery1;
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery2 = templateApplicant
          .getTemplateQuery(2);
        templateQuery2.setParameter(1, applicant);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery3 = templateApplicant
          .getTemplateQuery(3);
        templateQuery3.setParameter(1, applnID);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery4 = templateApplicant
          .getTemplateQuery(4);
        templateQuery4.setParameter(1, approver);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery5 = templateApplicant
          .getTemplateQuery(5);
        templateQuery5.setParameter(1, applicant);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery6 = templateApplicant
          .getTemplateQuery(6);
        templateQuery6.setParameter(1, applicant);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery7 = templateApplicant
          .getTemplateQuery(7);
        templateQuery7.setParameter(1, applnID);
      } catch (Exception e) {
        e.printStackTrace();
      }
      templateApplicant.configMailAlert();
      String keepInformId = "";

      if (keepInfo != null) {
        for (int i = 0; i < keepInfo.length; i++) {
          if (i == keepInfo.length - 1)
            keepInformId = keepInformId + String.valueOf(keepInfo[i]);
          else {
            keepInformId = keepInformId + String.valueOf(keepInfo[i]) + ",";
          }
        }
      }
      try
      {
        alertlink = "/businessCard/BusinessCardInfo_retriveDetails.action";
        linkParam = "applicationCode=" + applnID + "&empCode=" + 
          applicant + "&appStatus=P";

        templateApplicant.sendProcessManagerAlert("", module, "I", 
          applnID, level, linkParam, alertlink, "Pending", 
          applicant, "", keepInformId, applicant, applicant);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        if (!String.valueOf(empFlow[0][3]).equals("0"))
          templateApplicant
            .sendApplicationMailToAlternateApprover(
            String.valueOf(empFlow[0][3]));
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      templateApplicant.sendApplicationMail();
      templateApplicant.clearParameters();
      templateApplicant.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String f9KeepInformedEmployee() {
    String[] eId = this.request.getParameterValues("keepInformedEmpCode");
    String str = this.bean.getEmpId();
    if (eId != null) {
      for (int i = 0; i < eId.length; i++)
        str = str + "," + eId[i];
    }
    try
    {
      String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, \tHRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID \tFROM HRMS_EMP_OFFC ";

      query = query + " WHERE EMP_STATUS='S' ";
      if ((this.bean.getUserProfileDivision() != null) && 
        (this.bean.getUserProfileDivision().length() > 0))
        query = query + "AND EMP_DIV IN (" + this.bean.getUserProfileDivision() + 
          ")";
      query = query + " AND EMP_ID NOT IN(" + str + ") ";
      query = query + "\tORDER BY HRMS_EMP_OFFC.EMP_ID";

      String[] headers = { getMessage("kiEmpToken"), 
        getMessage("kiEmpName") };
      String[] headerWidth = { "20", "80" };
      String[] fieldNames = { "kiEmployeeToken", "kiEmployeeName", 
        "kiEmployeeId" };
      int[] columnIndex = { 0, 1, 2 };
      String submitFlag = "false";
      String submitToMethod = "";
      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
        submitFlag, submitToMethod);
      return "f9page";
    } catch (Exception e) {
      logger.error(e.getMessage());
    }return "";
  }

  public String addKeepInformedEmpList()
  {
    try {
      BusinessCardInfoModel model = new BusinessCardInfoModel();
      model.initiate(this.context, this.session);
      String[] serialNo = this.request.getParameterValues("serialNoIt");
      String[] empToken = this.request
        .getParameterValues("keepInformedEmpTokenIt");
      String[] empId = this.request.getParameterValues("keepInformedEmpIdIt");
      String[] empName = this.request
        .getParameterValues("keepInformedEmpNameIt");

      model.displayIteratorValueForKeepInformed(serialNo, empId, 
        empToken, empName, this.bean);
      model.setKeepInformed(serialNo, empId, empToken, empName, this.bean);
      this.bean.setKiEmployeeName("");
      Object[][] empFlow = generateEmpFlow(this.bean.getEmpId(), 
        "BusinessCardInfo", 1);
      model.loadManager(this.bean, empFlow, this.bean.getIsAdminApproval());
      this.bean.setKeepInfoFlag("true");
      this.bean.setEmpFlag("true");
      getNavigationPanel(1);
      model.terminate();
    } catch (Exception localException) {
    }
    return "success";
  }

  public String removeKeepInformed() {
    try {
      String[] serialNo = this.request.getParameterValues("serialNoIt");
      String[] empCode = this.request
        .getParameterValues("keepInformedEmpTokenIt");
      String[] empToken = this.request
        .getParameterValues("keepInformedEmpIdIt");
      String[] empName = this.request
        .getParameterValues("keepInformedEmpNameIt");
      String empIDApp = this.bean.getEmpId();
      BusinessCardInfoModel model = new BusinessCardInfoModel();
      model.initiate(this.context, this.session);
      String removeEmpId = this.request.getParameter("kiInfrEmployeeCode");
      Object[][] empFlow = generateEmpFlow(this.bean.getEmpId(), 
        "BusinessCardInfo", 1);
      model.loadManager(this.bean, empFlow, this.bean.getIsAdminApproval());
      model.removeKeepInformedData(serialNo, empCode, empToken, empName, 
        this.bean, removeEmpId, empIDApp);
      this.bean.setCheckRemove("");
      this.bean.setKeepInfoFlag("true");
      this.bean.setEmpFlag("true");
      getNavigationPanel(1);
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in removeKeepInformed--------" + e);
    }
    return "success";
  }

  public String delete() {
    BusinessCardInfoModel model = new BusinessCardInfoModel();

    model.initiate(this.context, this.session);
    boolean result = model.deleteBusinessCardInf(this.bean);
    this.bean.setEmpFlag("true");
    if (result) {
      deleteProcessManagerAlert("Business Card", 
        this.bean.getBusCardApplCode());
      addActionMessage("Record deleted successfully");
    } else {
      addActionMessage("Record not deleted successfully");
    }
    reset();
    model.terminate();
    getNavigationPanel(1);
    if (this.bean.getSource().equals("mymessages"))
      return "mymessages";
    if (this.bean.getSource().equals("myservices")) {
      return "serviceJsp";
    }
    return "success";
  }

  public String report()
  {
    BusinessCardInfoModel model = new BusinessCardInfoModel();
    model.initiate(this.context, this.session);
    Object[][] empFlow = generateEmpFlow(this.bean.getEmpId(), 
      "BusinessCardInfo", 1);
    model.report(this.bean, this.request, this.response, empFlow);
    model.terminate();
    getNavigationPanel(1);
    return null;
  }

  public String reset() {
    this.bean.setEmpName("");
    this.bean.setEmpToken("");
    this.bean.setEmpId("");
    this.bean.setEmpTokenp("");
    this.bean.setEmpNamep("");
    this.bean.setDesigp("");
    this.bean.setDeptp("");
    this.bean.setCompanyNamep("");
    this.bean.setBranchp("");
    this.bean.setPinCodep("");
    this.bean.setLocationp("");
    this.bean.setOfficeNump("");
    this.bean.setMobilep("");
    this.bean.setFaxNump("");
    this.bean.setEmailIdp("");
    this.bean.setWebSiteAddrp("");
    this.bean.setQtyOfCards("");
    this.bean.setReqDate("");
    this.bean.setComment("");
    this.bean.setSysInfoFlag("");
    this.bean.setKeepInfoFlag("true");
    this.bean.setEmpFlag("true");
    this.bean.setApproverCode("");
    getNavigationPanel(1);
    return "success";
  }

  public String getApproverDetails() {
    try {
      BusinessCardInfoModel model = new BusinessCardInfoModel();
      model.initiate(this.context, this.session);
      String empId = this.request.getParameter("empId");
      String applId = this.request.getParameter("hiddenAppCode");
      String status = this.request.getParameter("status");
      this.bean.setBusCardApplCode(applId);
      this.bean.setEmpId(empId);

      model.loadEmployeeInf(this.bean, empId);
      model.setBusinessCardSavedRecord(this.bean, applId);
      model.setEmployeeInformation(empId, this.bean);
      Object[][] empFlow = generateEmpFlow(empId, "BusinessCardInfo", 1);
      model.loadManager(this.bean, empFlow, this.bean.getIsAdminApproval());
      model.getApproversDtl(this.bean);
      this.bean.setKeepInfoFlag("false");
      this.bean.setEmpFlag("false");
      this.bean.setCommentFlag("true");
      this.bean.setKiEmployeeId("");
      this.bean.setKiEmployeeName("");
      this.bean.setKiEmployeeToken("");
      model.terminate();
      if (status.equals("B"))
        getNavigationPanel(8);
      else
        getNavigationPanel(2);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String saveApproverDataBusinessCardInfo() {
    try {
      BusinessCardInfoModel model = new BusinessCardInfoModel();
      model.initiate(this.context, this.session);

      boolean isVenderApproval = false;
      String venderEmail = "";
      String approver = null;
      Object[][] venderApproval = model.getVendorDtl(this.bean);
      if ((venderApproval != null) && (venderApproval.length > 0)) {
        isVenderApproval = String.valueOf(venderApproval[0][0]).equals("Y");
        venderEmail = String.valueOf(venderApproval[0][1]);
        this.bean.setTomail(venderEmail);
      }
      String adminApproval = model.IsBcardDAdminFlag();
      boolean isAdminApproval = adminApproval.equals("Y");

      String empId = this.bean.getEmpId();
      String applnStatus = this.request.getParameter("status");
      String[] keepInfo = this.request
        .getParameterValues("keepInformedEmpIdIt");
      Object[][] empFlow = generateEmpFlow(empId, "BusinessCardInfo", 1);
      String admin = model.getAdminAppDet(this.bean);

      if (this.bean.getUserEmpId().equals(String.valueOf(empFlow[0][0])))
        approver = String.valueOf(empFlow[0][0]);
      else if ((admin != null) && 
        (isAdminApproval) && (this.bean.getUserEmpId().equals(admin))) {
        approver = admin;
      }

      String MgrApproved = model.IsMgrApproved(this.bean.getBusCardApplCode());
      boolean result = model.saveApproverDataBusinessCardInformation(this.bean, 
        this.bean.getEmpId(), this.bean.getBusCardApplCode(), this.bean.getStatus(), 
        this.bean.getApprComments(), approver, empFlow, keepInfo, 
        MgrApproved, isAdminApproval, admin, isVenderApproval, venderEmail, this.request);

      model.loadManager(this.bean, empFlow, this.bean.getIsAdminApproval());
      model.keepInformEmployee(this.bean);
      model.loadEmployeeInf(this.bean, empId);
      this.bean.setKeepInfoFlag("false");
      String adminId = "0";
      String vndrId = "0";
      if (applnStatus.equals("A")) {
        Object[][] adminConfDtl = (Object[][])null;
        adminConfDtl = model.getAdminDetails(this.bean);
        if ((adminConfDtl != null) && (adminConfDtl.length > 0)) {
          vndrId = String.valueOf(adminConfDtl[0][0]);
          adminId = String.valueOf(adminConfDtl[0][1]);
        }
        addActionMessage("Your application has been approved successfully");
      }
      else if (applnStatus.equals("R")) {
        addActionMessage("Your application has been rejected successfully");
      } else {
        addActionMessage("Your application has been send back successfully");
      }

      model.getApproversDtl(this.bean);
      model.terminate();
      getNavigationPanel(6);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
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
      String msgType = "A";
      String applicantID = this.bean.getEmpId();
      String module = "Business Card";
      String applnID = this.bean.getBusCardApplCode();
      String level = "1";

      String link = "/businessCard/BusinessCardInfo_retriveDetails.action";
      String linkParam = "applicationCode=" + applnID + "&empCode=" + 
        applicantID + "&appStatus=D";

      String message = alertProp.getProperty("draftAlertMessage");
      message = message.replace("<#EMP_NAME#>", this.bean.getEmpName().trim());
      message = message.replace("<#APPL_TYPE#>", "Business Card");
      template.sendProcessManagerAlertDraft(applicantID, module, msgType, 
        applnID, level, linkParam, link, message, "Draft", 
        applicantID, applicantID);
      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String vendorDetails() {
    String result = "";
    try {
      BusinessCardInfoModel model = new BusinessCardInfoModel();
      model.initiate(this.context, this.session);

      if ((this.bean.getStatus().equals("A")) || (this.bean.getStatus().equals("R")) || 
        (this.bean.getStatus().equals("B"))) {
        if (this.bean.getStatus().equals("A")) {
          model.getVendorDtl(this.bean);
        }
        result = "vendorDetails";
        getNavigationPanel(4);
      } else {
        addActionMessage("Application  has not been  approved/rejected/sent back  sucessfully");
        result = "success";
        getNavigationPanel(2);
        this.bean.setCommentFlag("true");
      }
      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public void deleteProcessManagerAlert(String moduleName, String applicationCode)
  {
    try {
      BusinessCardInfoModel model = new BusinessCardInfoModel();
      model.initiate(this.context, this.session);
      String query = " DELETE FROM HRMS_ALERT_MESSAGE WHERE ALERT_APPLICATION_ID=" + 
        applicationCode + 
        " AND UPPER(ALERT_MODULE) LIKE '" + 
        moduleName.trim().toUpperCase() + "'";
      model.getSqlModel().singleExecute(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}