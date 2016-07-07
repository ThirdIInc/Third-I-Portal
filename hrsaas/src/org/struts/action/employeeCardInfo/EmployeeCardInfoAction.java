package org.struts.action.employeeCardInfo;

import com.lowagie.text.Image;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.employeeCardInfo.EmployeeCardInfo;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.businessCardInfo.BusinessCardInfoModel;
import org.paradyne.model.employeeCardInfo.EmployeeCardInfoModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class EmployeeCardInfoAction extends ParaActionSupport
{
  private static final long serialVersionUID = 1L;
  static Logger logger = Logger.getLogger(EmployeeCardInfoAction.class);
  EmployeeCardInfo bean;

  public void prepare_local()
    throws Exception
  {
    this.bean = new EmployeeCardInfo();
    this.bean.setMenuCode(2244);
    String source = this.request.getParameter("src");
    System.out.println("source--------------" + source);
    this.bean.setSource(source);
  }

  public Object getModel() {
    return this.bean;
  }

  public EmployeeCardInfo getBean() {
    return this.bean;
  }

  public void setBean(EmployeeCardInfo bean) {
    this.bean = bean;
  }

  public String input() {
    this.bean.setEmpFlag("true");
    this.bean.setFlag("true");
    this.bean.setEmpId(this.bean.getUserEmpId());
    loadEmpDetails();
    getNavigationPanel(1);
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
    String submitToMethod = "EmployeeCardInfo_loadEmpDetails.action";
    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String loadEmpDetails() {
    EmployeeCardInfoModel model = new EmployeeCardInfoModel();
    model.initiate(this.context, this.session);
    String empId = this.bean.getEmpId();
    this.bean.setIsAttachClick("0");
    model.loadEmployeeInf(this.bean, empId);
    model.setEmployeeCardSavedRecord(this.bean, this.bean.getBusCardApplCode());
    model.getAdminName(this.bean);
    this.bean.setKeepInfoFlag("true");
    this.bean.setEmpFlag("true");
    this.bean.setKiEmployeeId("");
    this.bean.setKiEmployeeName("");
    this.bean.setKiEmployeeToken("");
    String photo = this.bean.getUploadFileName();
    String str = (String)this.session.getAttribute("session_pool");
    String img = model.getServletContext().getRealPath("//") + 
      "//pages//images//" + str + "//employee//" + photo;
    this.bean.setFlag("false");
    try {
    	Image  image = Image.getInstance(img);
    }
    catch (Exception e)
    {
      Image image;
      photo = "NoImage.jpg";
    }
    this.request.setAttribute("photo", photo);
    this.request.setAttribute("photoAttach", (String)
      this.request.getAttribute("photoAttach"));
    model.terminate();
    getNavigationPanel(1);
    return "success";
  }

  public String uploadPhoto() {
    try {
      this.bean.setIsAttachClick("1");
      EmployeeCardInfoModel model = new EmployeeCardInfoModel();
      model.initiate(this.context, this.session);
      String empId = this.bean.getEmpId();

      model.getAdminName(this.bean);
      this.bean.setKeepInfoFlag("true");
      this.bean.setEmpFlag("true");
      this.bean.setKiEmployeeId("");
      this.bean.setKiEmployeeName("");
      this.bean.setKiEmployeeToken("");

      String photo = this.bean.getUploadFileName1();
      String str = (String)this.session.getAttribute("session_pool");
      String img = model.getServletContext().getRealPath("//") + 
        "//pages//images//" + str + "//employee//" + photo;
      this.bean.setFlag("false");
      try {
    	  Image image = Image.getInstance(img);
      }
      catch (Exception e)
      {
        Image image;
        photo = "NoImage.jpg";
      }
      String photo1 = this.bean.getUploadFileName();
      String str1 = (String)this.session.getAttribute("session_pool");
      String img1 = model.getServletContext().getRealPath("//") + 
        "//pages//images//" + str + "//employee//" + photo;
      this.bean.setFlag("false");
      try {
    	  Image image1 = Image.getInstance(img1);
      }
      catch (Exception e)
      {
        Image image1;
        photo = "NoImage.jpg";
      }
      this.request.setAttribute("photo", photo1);
      this.request.setAttribute("photoAttach", photo);
      model.terminate();
      getNavigationPanel(1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String retriveDetails() {
    try {
      String source = this.request.getParameter("src");
      this.bean.setSource(source);
      EmployeeCardInfoModel model = new EmployeeCardInfoModel();
      model.initiate(this.context, this.session);
      String applicationCode = this.request.getParameter("applicationCode");
      String empCode = this.request.getParameter("empCode");
      String status = this.request.getParameter("appStatus");
      model.loadEmployeeInf(this.bean, empCode);
      model.setEmployeeCardSavedRecord(this.bean, applicationCode);
      model.setEmployeeInformation(empCode, this.bean);

      model.getAdminName(this.bean);
      model.getApproversDtl(this.bean);
      this.bean.setKiEmployeeId("");
      this.bean.setKiEmployeeName("");
      this.bean.setKiEmployeeToken("");
      this.bean.setEmpFlag("false");
      model.terminate();
      String photo = this.bean.getUploadFileName();
      String str = (String)this.session.getAttribute("session_pool");
      String img = model.getServletContext().getRealPath("//") + 
        "//pages//images//" + str + "//employee//" + photo;
      this.bean.setFlag("false");
      try {
    	  Image image = Image.getInstance(img);
      }
      catch (Exception e)
      {
        Image image;
        photo = "NoImage.jpg";
      }
      this.request.setAttribute("photo", photo);
      this.request.setAttribute("photoAttach", String.valueOf(
        this.request.getAttribute("photoAttach")));
      if (status.equals("D")) {
        this.bean.setKeepInfoFlag("true");
        getNavigationPanel(7);
      } else {
        this.bean.setKeepInfoFlag("false");
        getNavigationPanel(3);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public String saveEmployeeCardInfo() {
    try {
      EmployeeCardInfoModel model = new EmployeeCardInfoModel();
      String result = "";
      model.initiate(this.context, this.session);
      String status = this.request.getParameter("status");
      String managerID = this.request.getParameter("managerIdIt");
      String[] keepInformEmpIdIt = this.request
        .getParameterValues("keepInformedEmpIdIt");
      String strKiEmpCodeIt = "";
      if (keepInformEmpIdIt != null) {
        for (int cnt = 0; cnt < keepInformEmpIdIt.length; cnt++) {
          if (cnt == 0)
            strKiEmpCodeIt = String.valueOf(keepInformEmpIdIt[cnt]);
          else {
            strKiEmpCodeIt = strKiEmpCodeIt + "," + 
              String.valueOf(keepInformEmpIdIt[cnt]);
          }

        }

      }

      String[] keepInfo = this.request
        .getParameterValues("keepInformedEmpIdIt");
      String module = "Employee Card";
      Date date = new Date();
      SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
      String sysDate = formater.format(date);
      String applicant = this.bean.getEmpId();
      if (this.bean.getBusCardApplCode().equals("")) {
        result = model.saveEmployeeCardInformation(this.bean, status, 
          strKiEmpCodeIt);
        String applID = model.getECardApplCode(this.bean);
        this.bean.setBusCardApplCode(applID);

        model.getAdminName(this.bean);
        model.keepInformEmployee(this.bean);
        if (result.equals("saved")) {
          if (status.equals("D")) {
            sendProcessManagerAlertDraft();
            addActionMessage("Your application has been Draft successfully.");
          } else if (status.equals("P"))
          {
            sendApplicationAlert(applID, module, applicant, null, 
              managerID, sysDate, keepInfo);
            addActionMessage("Your application has been sent for approval successfully.");
          }
          else
          {
            addActionMessage(getMessage("Record saved successfully"));
          }
        } else if (result.equals("notsaved"))
          addActionMessage("Record not saved successfully");
      }
      else {
        result = model.updateEmployeeCardInformation(this.bean, status, 
          strKiEmpCodeIt);

        model.getAdminName(this.bean);
        model.keepInformEmployee(this.bean);
        if (result.equals("updated")) {
          if (status.equals("D")) {
            addActionMessage("Your application has been Draft successfully.");
          } else if (status.equals("P"))
          {
            try
            {
              MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
              processAlerts.initiate(this.context, this.session);
              processAlerts.removeProcessAlert(
                this.bean.getBusCardApplCode(), "Employee Card");
              processAlerts.terminate();
            } catch (Exception e) {
              e.printStackTrace();
            }

            String applID = this.bean.getBusCardApplCode();
            sendApplicationAlert(applID, module, applicant, null, 
              managerID, sysDate, keepInfo);
            addActionMessage("Your application has been sent for approval successfully.");
          }
          else
          {
            addActionMessage(getMessage("Record saved successfully"));
          }
        } else if (result.equals("notupdated")) {
          addActionMessage("Record not updated successfully");
        }

      }

      this.bean.setKeepInfoFlag("true");
      this.bean.setEmpFlag("true");
      this.bean.setManagerIdIt(managerID);
      this.request.setAttribute("managerIdIt", managerID);
      model.terminate();
      getNavigationPanel(1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (this.bean.getSource().equals("mymessages"))
      return "mymessages";
    if (this.bean.getSource().equals("myservices")) {
      return "serviceJsp";
    }
    return "success";
  }

  public void sendApplicationAlert(String applnID, String module, String applicant, Object[][] empFlow, String managerID, String applnDate, String[] keepInfo)
  {
    try
    {
      String approverId = managerID;
      String msgType = "A";
      String level = "1";
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      template
        .setEmailTemplate("EMPLOYEE CARD INFORMATION - APPLICANT TO APPROVER");
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
        templateQuery2.setParameter(1, approverId);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try
      {
        EmailTemplateQuery templateQuery3 = template
          .getTemplateQuery(3);
        templateQuery3.setParameter(1, applicant);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try
      {
        EmailTemplateQuery templateQuery4 = template
          .getTemplateQuery(4);
        templateQuery4.setParameter(1, approverId);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery5 = template
          .getTemplateQuery(5);
        templateQuery5.setParameter(1, applnID);
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
      try
      {
        template.configMailAlert();
      } catch (Exception e) {
        e.printStackTrace();
      }

      String[] link_param = new String[3];
      String[] link_label = new String[3];
      String applicationType = "EmployeeCardInfoAppl";
      link_param[0] = 
        (applicationType + "#" + applicant + "#" + applnID + 
        "#" + "A" + "#" + "..." + "#" + approverId + "#" + level);
      link_param[1] = 
        (applicationType + "#" + applicant + "#" + applnID + 
        "#" + "R" + "#" + "..." + "#" + approverId + "#" + level);
      link_param[2] = 
        (applicationType + "#" + applicant + "#" + applnID + 
        "#" + "B" + "#" + "..." + "#" + approverId + "#" + level);
      /*link_label[0] = "Approve";
      link_label[1] = "Reject";
      link_label[2] = "Send Back";*/
      String alertlink = "/employeeCard/EmployeeCardInfo_getApproverDetails.action";
      String linkParam = "hiddenAppCode=" + applnID + "&empId=" + 
        applicant + "&status=P&pplicationLevel=1";
      try {
        template.sendProcessManagerAlert(approverId, module, msgType, 
          applnID, level, linkParam, alertlink, "Pending", 
          applicant, "", "", "", applicant);
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
        .setEmailTemplate("EMPLOYEE  CARD INFORMATION -  APPLICATION DETAILS TO APPLICANT");
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
        templateQuery4.setParameter(1, approverId);
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
          else
            keepInformId = keepInformId + String.valueOf(keepInfo[i]) + ",";
        }
      }
      try
      {
        alertlink = "/employeeCard/EmployeeCardInfo_retriveDetails.action";
        linkParam = "applicationCode=" + applnID + "&empCode=" + 
          applicant + "&appStatus=P";
        templateApplicant.sendProcessManagerAlert("", module, "I", 
          applnID, level, linkParam, alertlink, "Pending", 
          applicant, "", keepInformId, applicant, applicant);
      } catch (Exception e) {
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
      EmployeeCardInfoModel model = new EmployeeCardInfoModel();
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

      model.getAdminName(this.bean);
      this.bean.setKeepInfoFlag("true");
      this.bean.setEmpFlag("true");
      String photo = this.bean.getUploadFileName();
      String str = (String)this.session.getAttribute("session_pool");
      String img = model.getServletContext().getRealPath("//") + 
        "//pages//images//" + str + "//employee//" + photo;
      this.bean.setFlag("false");
      try {
    	  Image image = Image.getInstance(img);
      }
      catch (Exception e)
      {
        Image image;
        photo = "NoImage.jpg";
      }
      this.request.setAttribute("photo", photo);
      this.request.setAttribute("photoAttach", String.valueOf(
        this.request.getAttribute("photoAttach")));
      getNavigationPanel(1);
      model.terminate();
    } catch (Exception localException1) {
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
      EmployeeCardInfoModel model = new EmployeeCardInfoModel();
      model.initiate(this.context, this.session);
      String removeEmpId = this.request.getParameter("kiInfrEmployeeCode");

      model.getAdminName(this.bean);
      model.removeKeepInformedData(serialNo, empCode, empToken, empName, 
        this.bean, removeEmpId, empIDApp);
      this.bean.setCheckRemove("");
      this.bean.setKeepInfoFlag("true");
      getNavigationPanel(1);
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in removeKeepInformed--------" + e);
    }
    return "success";
  }

  public String delete()
  {
    EmployeeCardInfoModel model = new EmployeeCardInfoModel();

    model.initiate(this.context, this.session);
    boolean result = model.deleteBusinessCardInf(this.bean);
    this.bean.setEmpFlag("true");
    if (result) {
      deleteProcessManagerAlert("Employee Card", 
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
    EmployeeCardInfoModel model = new EmployeeCardInfoModel();
    model.initiate(this.context, this.session);

    model.report(this.bean, this.request, this.response, null);
    model.terminate();
    getNavigationPanel(1);
    return null;
  }

  public String reset() {
    this.bean.setFlag("true");
    this.bean.setUploadFileName("");
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
    this.bean.setKeepInfoFlag("false");
    this.bean.setEmpFlag("true");
    getNavigationPanel(1);
    return "success";
  }

  public String getApproverDetails() {
    try {
      EmployeeCardInfoModel model = new EmployeeCardInfoModel();
      model.initiate(this.context, this.session);
      String empId = this.request.getParameter("empId");
      String applID = this.request.getParameter("hiddenAppCode");
      String status = this.request.getParameter("status");
      this.bean.setEmpId(empId);
      this.bean.setBusCardApplCode(applID);

      model.loadEmployeeInf(this.bean, empId);
      model.setEmployeeCardSavedRecord(this.bean, applID);
      model.setEmployeeInformation(empId, this.bean);

      model.getAdminName(this.bean);
      model.getApproversDtl(this.bean);
      this.bean.setKeepInfoFlag("false");
      this.bean.setEmpFlag("false");
      this.bean.setCommentFlag("true");
      this.bean.setKiEmployeeId("");
      this.bean.setKiEmployeeName("");
      this.bean.setKiEmployeeToken("");
      String photo = this.bean.getUploadFileName();
      String str = (String)this.session.getAttribute("session_pool");
      String img = model.getServletContext().getRealPath("//") + 
        "//pages//images//" + str + "//employee//" + photo;
      this.bean.setFlag("false");
      try {
    	  Image image = Image.getInstance(img);
      }
      catch (Exception e)
      {
        Image image;
        photo = "NoImage.jpg";
      }
      this.request.setAttribute("photo", photo);
      this.request.setAttribute("photoAttach", String.valueOf(
        this.request.getAttribute("photoAttach")));
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

  public String saveApproverDataEmployeeCardInfo() {
    try {
      EmployeeCardInfoModel model = new EmployeeCardInfoModel();
      model.initiate(this.context, this.session);

      String empId = this.bean.getEmpId();
      boolean isVenderApproval = false;
      String venderEmail = "";
      String applnStatus = this.request.getParameter("status");
      String[] keepInformEmpIdIt = this.request
        .getParameterValues("keepInformedEmpIdIt");
      String managerID = this.request.getParameter("managerIdIt");

      String ecardAdmin = this.request.getParameter("ecardAdmin");
      String photo = this.bean.getUploadFileName();
      String str = (String)this.session.getAttribute("session_pool");
      String img = model.getServletContext().getRealPath("//") + 
        "//pages//images//" + str + "//employee//" + photo;
      this.bean.setFlag("false");
      try {
    	  Image image = Image.getInstance(img);
      }
      catch (Exception e)
      {
        Image image;
        photo = "NoImage.jpg";
      }
      this.request.setAttribute("photo", photo);
      this.request.setAttribute("photoAttach", String.valueOf(
        this.request.getAttribute("photoAttach")));

      Object[][] venderApproval = model.getVendorDtl(this.bean);
      if ((venderApproval != null) && (venderApproval.length > 0)) {
        isVenderApproval = String.valueOf(venderApproval[0][0]).equals("Y");
        venderEmail = String.valueOf(venderApproval[0][1]);
        this.bean.setTomail(venderEmail);
      }
      boolean result = model.saveApproverDataEmployeeCardInformation(applnStatus, 
        this.bean.getEmpId(), this.bean.getBusCardApplCode(), 
        this.bean.getApprComments(), managerID, isVenderApproval, this.request);

      model.getAdminName(this.bean);
      model.keepInformEmployee(this.bean);
      model.loadEmployeeInf(this.bean, empId);
      this.bean.setKeepInfoFlag("false");

      String adminId = "0";
      String vndrId = "0";
      String adminEmailId = "0";

      if (applnStatus.equals("A")) {
        Object[][] adminConfDtl = (Object[][])null;
        adminConfDtl = model.getAdminDetails(this.bean);
        if ((adminConfDtl != null) && (adminConfDtl.length > 0)) {
          vndrId = String.valueOf(adminConfDtl[0][0]);
          adminId = String.valueOf(adminConfDtl[0][1]);
          adminEmailId = String.valueOf(adminConfDtl[0][2]);
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
      getNavigationPanel(5);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "success";
  }

  public void sendProcessManagerAlertDraft()
  {
    try {
      FileInputStream alertFin = new FileInputStream(getText("data_path") + 
        "\\Alerts\\alertLinks.properties");
      Properties alertProp = new Properties();
      alertProp.load(alertFin);
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      String msgType = "A";
      String applicantID = this.bean.getEmpId();
      String module = "Employee Card";
      String applnID = this.bean.getBusCardApplCode();
      String level = "1";
      String link = "/employeeCard/EmployeeCardInfo_retriveDetails.action";
      String linkParam = "applicationCode=" + applnID + "&empCode=" + 
        applicantID + "&appStatus=D";
      String message = alertProp.getProperty("draftAlertMessage");
      message = message.replace("<#EMP_NAME#>", this.bean.getEmpName().trim());
      message = message.replace("<#APPL_TYPE#>", "Employee Card");
      template.sendProcessManagerAlertDraft(applicantID, module, msgType, 
        applnID, level, linkParam, link, message, "Draft", 
        applicantID, applicantID);
      template.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
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