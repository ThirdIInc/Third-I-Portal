package org.paradyne.model.employeeCardInfo;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.employeeCardInfo.EmployeeCardInfo;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.paradyne.model.voucher.CashVoucherModel;

public class EmployeeCardInfoModel extends ModelBase
{
  static Logger logger = Logger.getLogger(CashVoucherModel.class);

  public void loadEmployeeInf(EmployeeCardInfo bean, String empId) {
    try {
      String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, NVL(RANK_NAME, ' '), NVL(DEPT_NAME, ' '), NVL(DIV_NAME,' '),NVL(ADD_MOBILE,' '),NVL(ADD_EMAIL,' '),EMP_PHOTO,DIV_WEBSITE   FROM HRMS_EMP_OFFC LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID) LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID)  LEFT JOIN HRMS_division ON(HRMS_division.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)  LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_OFFC.EMP_ID= HRMS_EMP_ADDRESS.EMP_ID AND ADD_TYPE='P')  where EMP_ID=" + 
        empId + " ORDER BY EMP_ID ";

      Object[][] empInfoObj = getSqlModel().getSingleResult(query);
      if ((empInfoObj != null) && (empInfoObj.length > 0)) {
        bean.setEmpToken1(checkNull(String.valueOf(empInfoObj[0][0])));
        bean.setEmpName1(checkNull(String.valueOf(empInfoObj[0][1])));
        bean.setDesig(checkNull(String.valueOf(empInfoObj[0][2])));
        bean.setDept(checkNull(String.valueOf(empInfoObj[0][3])));
        bean
          .setCompanyName(checkNull(
          String.valueOf(empInfoObj[0][4])));
        bean.setMobile(checkNull(String.valueOf(empInfoObj[0][5])));
        bean.setEmailId(checkNull(String.valueOf(empInfoObj[0][6])));
        if (String.valueOf(empInfoObj[0][8]).equals("null"))
          bean.setWebSiteAddr("");
        else {
          bean.setWebSiteAddr(String.valueOf(empInfoObj[0][8]));
        }
        if (String.valueOf(empInfoObj[0][7]).equals("null")) {
          bean.setUploadFileName("");
        }
        else {
          bean.setUploadFileName(String.valueOf(empInfoObj[0][7]));
        }
      }

      String qry = "SELECT  SUBSTR(CENTER_NAME,1,20)||' '||CENTER_ADDRESS1||' '||CENTER_ADDRESS2||' '||CENTER_ADDRESS3, CENTER_PINCODE,NVL(CENTER_TELEPHONE,' '),NVL(CENTER_FAX,' '),CENTER_CITY FROM HRMS_CENTER  WHERE CENTER_ID=(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMp_ID=" + 
        empId + ")";
      Object[][] empAddrObj = getSqlModel().getSingleResult(qry);

      bean.setBranch(checkNull(String.valueOf(empAddrObj[0][0])));
      bean.setPinCode(checkNull(String.valueOf(empAddrObj[0][1])));
      bean.setOfficeNum(checkNull(String.valueOf(empAddrObj[0][2])));
      bean.setFaxNum(checkNull(String.valueOf(
        String.valueOf(empAddrObj[0][3]))));
      bean.setLocation(checkNull(String.valueOf(
        String.valueOf(empAddrObj[0][4]))));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setEmployeeInformation(String empCode, EmployeeCardInfo bean) {
    try {
      String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC  Where  EMP_ID=" + 
        empCode;
      Object[][] empObj = getSqlModel().getSingleResult(query);
      if ((empObj != null) && (empObj.length > 0)) {
        bean.setEmpToken(String.valueOf(empObj[0][0]));
        bean.setEmpName(String.valueOf(empObj[0][1]));
        bean.setEmpId(String.valueOf(empObj[0][2]));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setEmployeeCardSavedRecord(EmployeeCardInfo bean, String applicationCode)
  {
    try
    {
      String busCardInfoqry = "SELECT EMPLOYEE_APPL_CODE,EMPLOYEE_EMP_TOKEN, EMPLOYEE_EMP_NAME, EMPLOYEE_EMP_DESIGNATION, EMPLOYEE_EMP_DEPARTMANT, EMPLOYEE_EMP_COMPANY, EMPLOYEE_EMP_BRANCHADDRESS, EMPLOYEE_EMP_PINCODE, EMPLOYEE_EMP_LOCATION, EMPLOYEE_EMP_OFFICENO, EMPLOYEE_EMP_MOBILE, EMPLOYEE_EMP_FAX, EMPLOYEE_EMP_EMAIL, EMPLOYEE_EMP_WEBSITE, EMPLOYEE_COMMENT,EMPLOYEE_KEEPINFORM FROM HRMS_EMPLOYEE_CARD_INFO WHERE  EMPLOYEE_APPL_CODE=" + 
        applicationCode;

      Object[][] empCardInfoEmpInfoObj = getSqlModel().getSingleResult(
        busCardInfoqry);
      if ((empCardInfoEmpInfoObj != null) && 
        (empCardInfoEmpInfoObj.length > 0)) {
        bean.setBusCardApplCode(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][0])));
        bean.setEmpTokenp(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][1])));
        bean.setEmpNamep(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][2])));
        bean.setDesigp(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][3])));
        bean.setDeptp(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][4])));
        bean.setCompanyNamep(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][5])));
        bean.setBranchp(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][6])));
        bean.setPinCodep(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][7])));
        bean.setLocationp(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][8])));
        bean.setOfficeNump(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][9])));
        bean.setMobilep(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][10])));
        bean.setFaxNump(checkNull(String.valueOf(
          String.valueOf(empCardInfoEmpInfoObj[0][11]))));
        bean.setEmailIdp(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][12])));
        bean.setWebSiteAddrp(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][13])));
        bean.setComment(
          checkNull(String.valueOf(empCardInfoEmpInfoObj[0][14])));
        if (empCardInfoEmpInfoObj[0][15] != null) {
          String vchKiEmpId = 
            String.valueOf(empCardInfoEmpInfoObj[0][15]);
          String[] strKiEmpId = vchKiEmpId.split(",");
          Object[][] kiEmpDetails = (Object[][])null;
          ArrayList cvList = new ArrayList();
          if (strKiEmpId != null) {
            for (int i = 0; i < strKiEmpId.length; i++) {
              String kiInformEmpInfQry = "SELECT EMP_ID,EMP_TOKEN,EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID=" + 
                strKiEmpId[i];
              kiEmpDetails = getSqlModel().getSingleResult(
                kiInformEmpInfQry);
              if ((kiEmpDetails != null) && (kiEmpDetails.length > 0)) {
                EmployeeCardInfo cvApp = new EmployeeCardInfo();
                cvApp.setKeepInformedEmpIdIt(
                  String.valueOf(kiEmpDetails[0][0]));
                cvApp.setKeepInformedEmpTokenIt(
                  String.valueOf(kiEmpDetails[0][1]));
                cvApp.setKeepInformedEmpNameIt(
                  String.valueOf(kiEmpDetails[0][2]));
                cvApp.setSerialNo(String.valueOf(i));
                cvList.add(cvApp);
                bean.setKeepInformedList(cvList);
              }
            }
          }
        }

        bean.setSysInfoFlag("true");
      } else {
        bean.setBusCardApplCode("");
        bean.setEmpTokenp("");
        bean.setEmpNamep("");
        bean.setDesigp("");
        bean.setDeptp("");
        bean.setCompanyNamep("");
        bean.setBranchp("");
        bean.setPinCodep("");
        bean.setLocationp("");
        bean.setOfficeNump("");
        bean.setMobilep("");
        bean.setFaxNump("");
        bean.setEmailIdp("");
        bean.setWebSiteAddrp("");
        bean.setQtyOfCards("");
        bean.setReqDate("");
        bean.setComment("");
        bean.setSysInfoFlag("false");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String saveEmployeeCardInformation(EmployeeCardInfo bean, String status, String strKiEmpCodeIt)
  {
    Object[][] empCardInfoData = new Object[1][18];
    boolean result = false;
    String flag = "";
    empCardInfoData[0][0] = String.valueOf(bean.getEmpId());
    empCardInfoData[0][1] = String.valueOf(bean.getEmpTokenp());
    empCardInfoData[0][2] = String.valueOf(bean.getEmpNamep());
    empCardInfoData[0][3] = String.valueOf(bean.getDesigp());
    empCardInfoData[0][4] = String.valueOf(bean.getDeptp());
    empCardInfoData[0][5] = String.valueOf(bean.getCompanyNamep());
    empCardInfoData[0][6] = String.valueOf(bean.getBranchp());
    empCardInfoData[0][7] = String.valueOf(bean.getPinCodep());
    empCardInfoData[0][8] = String.valueOf(bean.getLocationp());
    empCardInfoData[0][9] = String.valueOf(bean.getOfficeNump());
    empCardInfoData[0][10] = String.valueOf(bean.getMobilep());
    empCardInfoData[0][11] = String.valueOf(bean.getFaxNump());
    empCardInfoData[0][12] = String.valueOf(bean.getEmailIdp());
    empCardInfoData[0][13] = String.valueOf(bean.getWebSiteAddrp());
    empCardInfoData[0][14] = String.valueOf(bean.getComment());
    empCardInfoData[0][15] = status;
    empCardInfoData[0][16] = strKiEmpCodeIt;
    empCardInfoData[0][17] = String.valueOf(bean.getUploadFileName());
    String qry = " INSERT  INTO HRMS_EMPLOYEE_CARD_INFO(EMPLOYEE_APPL_CODE,EMPLOYEE_EMP_ID,EMPLOYEE_EMP_TOKEN, EMPLOYEE_EMP_NAME, EMPLOYEE_EMP_DESIGNATION, EMPLOYEE_EMP_DEPARTMANT, EMPLOYEE_EMP_COMPANY, EMPLOYEE_EMP_BRANCHADDRESS, EMPLOYEE_EMP_PINCODE, EMPLOYEE_EMP_LOCATION,EMPLOYEE_EMP_OFFICENO, EMPLOYEE_EMP_MOBILE, EMPLOYEE_EMP_FAX, EMPLOYEE_EMP_EMAIL, EMPLOYEE_EMP_WEBSITE, EMPLOYEE_COMMENT, EMPLOYEE_STATUS,EMPLOYEE_KEEPINFORM,EMPLOYEE_UPLOAD_PHOTO)   VALUES((SELECT NVL(MAX(EMPLOYEE_APPL_CODE),0)+1 FROM HRMS_EMPLOYEE_CARD_INFO),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

    result = getSqlModel().singleExecute(qry, empCardInfoData);
    if (result)
      flag = "saved";
    else {
      flag = "notsaved";
    }
    return flag;
  }

  public String getECardApplCode(EmployeeCardInfo bean) {
    String applCode = "";
    try {
      String applCodeQry = "SELECT NVL(MAX(EMPLOYEE_APPL_CODE),0) FROM HRMS_EMPLOYEE_CARD_INFO";
      Object[][] applCodeObj = getSqlModel().getSingleResult(applCodeQry);
      applCode = String.valueOf(applCodeObj[0][0]);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return applCode;
  }

  public String updateEmployeeCardInformation(EmployeeCardInfo bean, String status, String strKiEmpCodeIt)
  {
    String flag = "";
    Object[][] empCardInfoUpdateData = new Object[1][19];
    boolean result = false;
    try {
      empCardInfoUpdateData[0][0] = String.valueOf(bean.getEmpTokenp());
      empCardInfoUpdateData[0][1] = String.valueOf(bean.getEmpNamep());
      empCardInfoUpdateData[0][2] = String.valueOf(bean.getDesigp());
      empCardInfoUpdateData[0][3] = String.valueOf(bean.getDeptp());
      empCardInfoUpdateData[0][4] = 
        String.valueOf(bean.getCompanyNamep());
      empCardInfoUpdateData[0][5] = String.valueOf(bean.getBranchp());
      empCardInfoUpdateData[0][6] = String.valueOf(bean.getPinCodep());
      empCardInfoUpdateData[0][7] = String.valueOf(bean.getLocationp());
      empCardInfoUpdateData[0][8] = String.valueOf(bean.getOfficeNump());
      empCardInfoUpdateData[0][9] = String.valueOf(bean.getMobilep());
      empCardInfoUpdateData[0][10] = String.valueOf(bean.getFaxNump());
      empCardInfoUpdateData[0][11] = String.valueOf(bean.getEmailIdp());
      empCardInfoUpdateData[0][12] = String.valueOf(
        bean.getWebSiteAddrp());
      empCardInfoUpdateData[0][13] = String.valueOf(bean.getComment());
      empCardInfoUpdateData[0][14] = status;
      empCardInfoUpdateData[0][15] = strKiEmpCodeIt;
      empCardInfoUpdateData[0][16] = String.valueOf(
        bean.getUploadFileName());
      empCardInfoUpdateData[0][17] = String.valueOf(
        bean.getBusCardApplCode());
      empCardInfoUpdateData[0][18] = String.valueOf(bean.getEmpId());
      String qry = " UPDATE  HRMS_EMPLOYEE_CARD_INFO SET EMPLOYEE_EMP_TOKEN=?, EMPLOYEE_EMP_NAME=?, EMPLOYEE_EMP_DESIGNATION=?, EMPLOYEE_EMP_DEPARTMANT=?, EMPLOYEE_EMP_COMPANY=?, EMPLOYEE_EMP_BRANCHADDRESS=?, EMPLOYEE_EMP_PINCODE=?, EMPLOYEE_EMP_LOCATION=?,EMPLOYEE_EMP_OFFICENO=?, EMPLOYEE_EMP_MOBILE=?, EMPLOYEE_EMP_FAX=?, EMPLOYEE_EMP_EMAIL=?, EMPLOYEE_EMP_WEBSITE=?,EMPLOYEE_COMMENT=?, EMPLOYEE_STATUS=?,EMPLOYEE_KEEPINFORM=?,EMPLOYEE_UPLOAD_PHOTO=? WHERE EMPLOYEE_APPL_CODE =? AND EMPLOYEE_EMP_ID=? ";

      result = getSqlModel().singleExecute(qry, empCardInfoUpdateData);
      if (result)
        flag = "updated";
      else
        flag = "notupdated";
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }

  public void getApproversDtl(EmployeeCardInfo bean) {
    try {
      String apprDtlQry = " SELECT  B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME EMP_NAME, EMPCARD_APPR_COMMENTS,TO_CHAR(EMPCARD_APPL_APPROVAL_DATE,'DD-MM-YYYY'),DECODE(EMPCARD_APPL_STATUS,'A','Approved','R','Rejected','B','Send Back') FROM HRMS_EMPCARD_APPROVER_DTL A INNER JOIN HRMS_EMP_OFFC B ON (A.EMPCARD_APPROVER_CODE = B.EMP_ID) WHERE EMPCARD_APPL_CODE=" + 
        bean.getBusCardApplCode();
      Object[][] apprDtlObj = getSqlModel().getSingleResult(apprDtlQry);
      int counter1 = 0;
      ArrayList tmpApprovedLst = new ArrayList();
      EmployeeCardInfo bean1 = null;
      if ((apprDtlObj != null) && (apprDtlObj.length > 0)) {
        for (int i = 0; i < apprDtlObj.length; i++) {
          bean1 = new EmployeeCardInfo();
          counter1++; bean1.setApprSrNoIt(String.valueOf(counter1));
          bean1.setApprNameIt(
            checkNull(String.valueOf(apprDtlObj[i][0])));
          bean1.setApprCommentsIt(
            checkNull(String.valueOf(apprDtlObj[i][1])));
          bean1.setApprovedDateIt(
            checkNull(String.valueOf(apprDtlObj[i][2])));
          bean1.setApprStatusIt(
            checkNull(String.valueOf(apprDtlObj[i][3])));

          tmpApprovedLst.add(bean1);
        }
        bean.setApprDtlLst(tmpApprovedLst);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void loadManager(EmployeeCardInfo busCardInfoBean, Object[][] empFlow) {
    try {
      if ((empFlow != null) && (empFlow.length > 0)) {
        Object[][] approverDataObj = new Object[empFlow.length][1];
        for (int i = 0; i < empFlow.length; i++) {
          String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '||  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')'   FROM HRMS_EMP_OFFC  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) WHERE EMP_ID IN(" + 
            empFlow[i][0] + ")";
          Object[][] temObj = getSqlModel().getSingleResult(
            selectQuery);
          approverDataObj[i][0] = String.valueOf(temObj[0][0]);
        }

        if ((approverDataObj != null) && (approverDataObj.length > 0)) {
          ArrayList arrList = new ArrayList();
          for (int i = 0; i < approverDataObj.length; i++) {
            EmployeeCardInfo bean = new EmployeeCardInfo();
            bean.setManagerNameIt(
              String.valueOf(approverDataObj[i][0]));
            String srNo = i + 1 + getOrdinalFor(i + 1) + 
              "-Approver";
            bean.setSrNoIt(srNo);
            arrList.add(bean);
          }
          busCardInfoBean.setManagerList(arrList);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getAdminName(EmployeeCardInfo empCardBean) {
    Object[][] adminDtl = new Object[1][3];
    String vdnrEmailId = "";
    String adminEmpEmailId = "";
    Object[][] adminNameObj = (Object[][])null;
    try
    {
      String divIdQry = "SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID=" + 
        empCardBean.getEmpId();
      Object[][] divIdObj = getSqlModel().getSingleResult(divIdQry);
      String confDtlQry = "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=" + 
        divIdObj[0][0] + " AND CONF_TYPE='EcardAdmin'";
      Object[][] confEmpIdObj = getSqlModel().getSingleResult(confDtlQry);
      if ((confEmpIdObj != null) && (confEmpIdObj.length > 0)) {
        adminDtl[0][1] = String.valueOf(confEmpIdObj[0][0]);
        String empMailIdQry = "SELECT EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC  WHERE EMP_ID=" + 
          confEmpIdObj[0][0];
        adminNameObj = getSqlModel().getSingleResult(empMailIdQry);
        if ((adminNameObj != null) && (adminNameObj.length > 0)) {
          ArrayList arrList = new ArrayList();
          for (int i = 0; i < adminNameObj.length; i++) {
            EmployeeCardInfo bean = new EmployeeCardInfo();
            bean.setManagerIdIt(String.valueOf(adminNameObj[i][0]));
            bean.setManagerNameIt(
              String.valueOf(adminNameObj[i][1]));
            String srNo = i + 1 + getOrdinalFor(i + 1) + 
              "-Approver";
            bean.setSrNoIt(srNo);
            arrList.add(bean);
          }
          empCardBean.setManagerList(arrList);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getOrdinalFor(int value) {
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

  public Object[][] getVendorDtl(EmployeeCardInfo bean)
  {
    String vndrDtlQry = "SELECT CONF_ECARD_VNDR_FLAG, CONF_ECARD_VNDR_EMAIL FROM HRMS_ADMIN_CONF";
    Object[][] vndrDtlObj = getSqlModel().getSingleResult(vndrDtlQry);
    if ((vndrDtlObj != null) && (vndrDtlObj.length > 0)) {
      if (vndrDtlObj[0][0].equals("Y")) {
        bean.setTomail(String.valueOf(vndrDtlObj[0][1]));
        return vndrDtlObj;
      }
      return null;
    }

    return null;
  }

  public Object[][] getAdminDetails(EmployeeCardInfo bean)
  {
    Object[][] adminDtl = new Object[1][3];
    String vdnrEmailId = "";
    String adminEmpEmailId = "";
    Object[][] empMailIdQryObj = (Object[][])null;
    try {
      String vndrDtlQry = "SELECT CONF_ECARD_VNDR_FLAG, CONF_ECARD_VNDR_EMAIL FROM HRMS_ADMIN_CONF";
      Object[][] vndrDtlObj = getSqlModel().getSingleResult(vndrDtlQry);
      if ((vndrDtlObj != null) && (vndrDtlObj.length > 0)) {
        if (vndrDtlObj[0][0].equals("Y")) {
          vdnrEmailId = String.valueOf(vndrDtlObj[0][1]);
          adminDtl[0][0] = vdnrEmailId;
        } else {
          adminDtl[0][0] = "";
        }
      }
      String divIdQry = "SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION WHERE DIV_NAME='" + 
        bean.getCompanyName() + "'";
      Object[][] divIdObj = getSqlModel().getSingleResult(divIdQry);
      String confDtlQry = "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=" + 
        divIdObj[0][0];
      Object[][] confEmpIdObj = getSqlModel().getSingleResult(confDtlQry);
      if ((confEmpIdObj != null) && (confEmpIdObj.length > 0)) {
        adminDtl[0][1] = String.valueOf(confEmpIdObj[0][0]);
        String empMailIdQry = "SELECT ADD_EMAIL FROM HRMS_EMP_ADDRESS  WHERE EMP_ID=" + 
          confEmpIdObj[0][0];
        empMailIdQryObj = getSqlModel().getSingleResult(empMailIdQry);
        if ((empMailIdQryObj != null) && (empMailIdQryObj.length > 0)) {
          adminEmpEmailId = String.valueOf(empMailIdQryObj[0][0]);
          adminDtl[0][2] = adminEmpEmailId;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return adminDtl;
  }

  public void keepInformEmployee(EmployeeCardInfo bean) {
    try {
      String qry = "SELECT EMPLOYEE_KEEPINFORM FROM HRMS_EMPLOYEE_CARD_INFO WHERE EMPLOYEE_EMP_ID=" + 
        bean.getEmpId() + 
        " AND EMPLOYEE_APPL_CODE=" + 
        bean.getBusCardApplCode();
      Object[][] keepInfEmpIdObj = getSqlModel().getSingleResult(qry);
      if ((keepInfEmpIdObj != null) && (keepInfEmpIdObj.length > 0)) {
        String vchKiEmpId = String.valueOf(keepInfEmpIdObj[0][0]);
        String[] strKiEmpId = vchKiEmpId.split(",");
        Object[][] kiEmpDetails = (Object[][])null;
        ArrayList cvList = new ArrayList();
        if (strKiEmpId != null)
          for (int i = 0; i < strKiEmpId.length; i++) {
            String empQry = "SELECT EMP_ID,EMP_TOKEN,EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID=" + 
              strKiEmpId[i];
            kiEmpDetails = getSqlModel().getSingleResult(empQry);
            if ((kiEmpDetails != null) && (kiEmpDetails.length > 0)) {
              EmployeeCardInfo cvApp = new EmployeeCardInfo();
              cvApp.setKeepInformedEmpIdIt(
                String.valueOf(kiEmpDetails[0][0]));
              cvApp.setKeepInformedEmpTokenIt(
                String.valueOf(kiEmpDetails[0][1]));
              cvApp.setKeepInformedEmpNameIt(
                String.valueOf(kiEmpDetails[0][2]));
              cvApp.setSerialNo(String.valueOf(i));
              cvList.add(cvApp);
              bean.setKeepInformedList(cvList);
            }
          }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void displayIteratorValueForKeepInformed(String[] srNo, String[] empId, String[] empToken, String[] empName, EmployeeCardInfo cvoucher)
  {
    try
    {
      ArrayList cvList = new ArrayList();
      if (srNo != null) {
        for (int i = 0; i < srNo.length; i++) {
          EmployeeCardInfo busCardInfo = new EmployeeCardInfo();
          busCardInfo.setSerialNoIt(srNo[i]);
          busCardInfo.setKeepInformedEmpIdIt(empId[i]);
          busCardInfo.setKeepInformedEmpTokenIt(empToken[i]);
          busCardInfo.setKeepInformedEmpNameIt(empName[i]);
          cvList.add(busCardInfo);
        }
        cvoucher.setKeepInformedList(cvList);
      }
    } catch (Exception e) {
      logger
        .error("Exception in displayIteratorValueForKeepInformed----------" + 
        e);
    }
  }

  public void setKeepInformed(String[] srNo, String[] empCode, String[] empToken, String[] empName, EmployeeCardInfo busCardInfo)
  {
    try {
      EmployeeCardInfo cvApp = new EmployeeCardInfo();
      cvApp.setKeepInformedEmpIdIt(busCardInfo.getKiEmployeeId());
      cvApp.setKeepInformedEmpTokenIt(busCardInfo.getKiEmployeeToken());
      cvApp.setKeepInformedEmpNameIt(busCardInfo.getKiEmployeeName());
      ArrayList cvList = displayNewValueForKeepInformed(
        srNo, empCode, empToken, empName, busCardInfo);
      cvApp.setSerialNoIt(String.valueOf(cvList.size() + 1));
      cvList.add(cvApp);
      busCardInfo.setKeepInformedList(cvList);
    } catch (Exception e) {
      logger.error("Exception in setKeepInformed----------" + e);
    }
  }

  private ArrayList<EmployeeCardInfo> displayNewValueForKeepInformed(String[] srNo, String[] empId, String[] empToken, String[] empName, EmployeeCardInfo bean)
  {
    ArrayList cvList = null;
    try {
      cvList = new ArrayList();
      if (srNo != null)
        for (int i = 0; i < srNo.length; i++) {
          EmployeeCardInfo cvbean = new EmployeeCardInfo();
          cvbean.setSerialNoIt(srNo[i]);
          cvbean.setKeepInformedEmpTokenIt(empToken[i]);
          cvbean.setKeepInformedEmpIdIt(empId[i]);
          cvbean.setKeepInformedEmpNameIt(empName[i]);
          cvList.add(cvbean);
        }
    }
    catch (Exception e) {
      logger
        .error("Exception in displayNewValueForKeepInformed----------" + 
        e);
    }
    return cvList;
  }

  public boolean saveApproverDataEmployeeCardInformation(String status, String empId, String applCode, String appComments, String managerID, boolean isVenderApproval, HttpServletRequest request)
  {
    Object[][] busCardInfoUpdateData = new Object[1][3];
    Object[][] updateAdminData = new Object[1][3];
    boolean result = false;
    String flag = "";
    Object[][] confEmpIdObj = (Object[][])null;
    try {
      busCardInfoUpdateData[0][0] = String.valueOf(status);
      busCardInfoUpdateData[0][1] = String.valueOf(empId);
      busCardInfoUpdateData[0][2] = String.valueOf(applCode);

      String updateQry = " UPDATE  HRMS_EMPLOYEE_CARD_INFO SET  EMPLOYEE_STATUS=?  WHERE  EMPLOYEE_EMP_ID=? AND EMPLOYEE_APPL_CODE =? ";
      result = getSqlModel().singleExecute(updateQry, 
        busCardInfoUpdateData);

      String divIdQry = "SELECT DIV_ID FROM HRMS_DIVISION WHERE div_id=(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID=" + 
        empId + ")";
      Object[][] divIdObj = getSqlModel().getSingleResult(divIdQry);

      String confDtlQry = "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=" + 
        divIdObj[0][0] + "AND CONF_TYPE='EcardAdmin'";
      confEmpIdObj = getSqlModel().getSingleResult(confDtlQry);

      updateAdminData[0][0] = String.valueOf(confEmpIdObj[0][0]);
      updateAdminData[0][1] = String.valueOf(empId);
      updateAdminData[0][2] = String.valueOf(applCode);

      String updateAdminIDQry = " UPDATE  HRMS_EMPLOYEE_CARD_INFO SET  EMPLOYEE_ADMIN_ID=?  WHERE  EMPLOYEE_EMP_ID=? AND EMPLOYEE_APPL_CODE =? ";
      result = getSqlModel().singleExecute(updateAdminIDQry, 
        updateAdminData);

      Object[][] saveApprDtls = new Object[1][6];
      Date date = new Date();
      SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
      String sysDate = formater.format(date);
      saveApprDtls[0][0] = String.valueOf(applCode);
      saveApprDtls[0][1] = managerID;
      saveApprDtls[0][2] = String.valueOf(status);
      saveApprDtls[0][3] = String.valueOf(appComments);
      saveApprDtls[0][4] = String.valueOf(sysDate);
      saveApprDtls[0][5] = String.valueOf(empId);
      for (int i = 0; i < saveApprDtls.length; i++) {
        for (int j = 0; j < saveApprDtls[0].length; j++) {
          System.out.println("saveApprDtls[i][j]----------" + 
            saveApprDtls[i][j]);
        }
      }
      if (result) {
        String saveQry = "INSERT INTO HRMS_EMPCARD_APPROVER_DTL(EMPCARD_APPL_CODE, EMPCARD_APPROVER_CODE, EMPCARD_APPL_STATUS, EMPCARD_APPR_COMMENTS, EMPCARD_APPL_APPROVAL_DATE, EMPCARD_APP_DTL_ID, EMPCARD_EMP_ID) VALUES(?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),(SELECT NVL(MAX(EMPCARD_APP_DTL_ID),0)+1 FROM HRMS_EMPCARD_APPROVER_DTL),?)";

        result = getSqlModel().singleExecute(saveQry, saveApprDtls);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    sendApproverApplicationAlert(applCode, "", empId, null, null, status, 
      String.valueOf(confEmpIdObj[0][0]), isVenderApproval, request);
    return result;
  }

  public void removeKeepInformedData(String[] serialNo, String[] empCode, String[] empToken, String[] empName, EmployeeCardInfo busCardInfoBean, String removeEmpId, String empID)
  {
    try
    {
      ArrayList tableList = new ArrayList();
      String qry = "SELECT EMPLOYEE_KEEPINFORM FROM HRMS_EMPLOYEE_CARD_INFO WHERE EMPLOYEE_EMP_ID=" + 
        empID + 
        " AND BUSINESS_APPL_CODE=" + 
        busCardInfoBean.getBusCardApplCode();
      Object[][] keepInfEmpIdObj = getSqlModel().getSingleResult(qry);
      if ((keepInfEmpIdObj != null) && (keepInfEmpIdObj.length > 0)) {
        String strKeepInfEmpId = String.valueOf(keepInfEmpIdObj[0][0]);
        String[] splitEmpId = strKeepInfEmpId.split(",");
        String employeeId = "";
        for (int i = 0; i < splitEmpId.length; i++) {
          if (!splitEmpId[i].equals(removeEmpId)) {
            if (employeeId.equals(""))
              employeeId = String.valueOf(splitEmpId[i]);
            else {
              employeeId = employeeId + "," + 
                String.valueOf(splitEmpId[i]);
            }
          }
        }
        String upateQry = "UPDATE  HRMS_EMPLOYEE_CARD_INFO SET  EMPLOYEE_KEPPINFORM='" + 
          employeeId + 
          "' WHERE EMPLOYEE_EMP_ID=" + 
          busCardInfoBean.getEmpId() + 
          " AND EMPLOYEE_APPL_CODE=" + 
          busCardInfoBean.getBusCardApplCode();
        getSqlModel().singleExecute(upateQry);
      }
      if (empCode != null) {
        for (int i = 0; i < empCode.length; i++) {
          EmployeeCardInfo bean = new EmployeeCardInfo();
          bean.setSerialNo(String.valueOf(i + 1));
          bean.setKeepInformedEmpTokenIt(empCode[i]);
          bean.setKeepInformedEmpIdIt(empToken[i]);
          bean.setKeepInformedEmpNameIt(empName[i]);
          tableList.add(bean);
        }
        tableList.remove(Integer.parseInt(
          busCardInfoBean.getCheckRemove()) - 1);
      }
      busCardInfoBean.setKeepInformedList(tableList);
    } catch (Exception e) {
      logger.error("Exception in removeKeepInformedData------" + e);
    }
  }

  public void report(EmployeeCardInfo bean, HttpServletRequest request, HttpServletResponse response, Object[][] empFlow)
  {
    String s = "\nEMPLOYEE Card Information\n\n";
    ReportGenerator rg = new ReportGenerator("Pdf", 
      s);

    Object[][] data = new Object[17][4];

    data[0][0] = "Applicant Details in System";
    data[0][1] = "";
    data[0][2] = "EMPLOYEE Card Information To Be Printed ";
    data[0][3] = "\n";

    data[1][0] = "";
    data[1][1] = "";
    data[1][2] = "";
    data[1][3] = "\n";

    data[2][0] = "Employee ID .";
    data[2][1] = (": " + bean.getEmpToken1());
    data[2][2] = "Employee ID. ";
    data[2][3] = (": " + bean.getEmpTokenp());

    data[3][0] = "Employee Name .";
    data[3][1] = (": " + bean.getEmpName1());
    data[3][2] = "Employee Name. ";
    data[3][3] = (": " + bean.getEmpNamep());

    data[4][0] = "Designation.";
    data[4][1] = (": " + bean.getDesig());
    data[4][2] = "Designation.";
    data[4][3] = (": " + bean.getDesigp());

    data[5][0] = "Department.";
    data[5][1] = (": " + bean.getDept());
    data[5][2] = "Department.";
    data[5][3] = (": " + bean.getDeptp());

    data[6][0] = "Company Name.";
    data[6][1] = (": " + bean.getCompanyName());
    data[6][2] = "Company Name";
    data[6][3] = (": " + bean.getCompanyNamep());

    data[7][0] = "Branch Address.";
    data[7][1] = (": " + bean.getBranch());
    data[7][2] = "Branch Address";
    data[7][3] = (": " + bean.getBranchp());

    data[8][0] = "Pin Code.";
    data[8][1] = (": " + bean.getPinCode());
    data[8][2] = "Pin Code";
    data[8][3] = (": " + bean.getPinCodep());

    data[9][0] = "Location";
    data[9][1] = (": " + bean.getLocation());
    data[9][2] = "Location";
    data[9][3] = (": " + bean.getLocationp());

    data[10][0] = "Office Number";
    data[10][1] = (": " + bean.getOfficeNum());
    data[10][2] = "Office Number";
    data[10][3] = (": " + bean.getOfficeNump());

    data[11][0] = "Mobile Number";
    data[11][1] = (": " + bean.getMobile());
    data[11][2] = "Mobile Number";
    data[11][3] = (": " + bean.getMobilep());

    data[12][0] = "Fax Number";
    data[12][1] = (": " + bean.getFaxNum());
    data[12][2] = "Fax Number";
    data[12][3] = (": " + bean.getFaxNump());

    data[13][0] = "Email Id";
    data[13][1] = (": " + bean.getEmailId());
    data[13][2] = "Email Id";
    data[13][3] = (": " + bean.getEmailIdp());

    data[14][0] = "Web Address";
    data[14][1] = (": " + bean.getWebSiteAddr());
    data[14][2] = "Web Address";
    data[14][3] = (": " + bean.getWebSiteAddrp());

    data[15][0] = "\n  Comments \n";
    data[15][1] = "";
    data[15][2] = "";
    data[15][3] = "";

    data[16][0] = "Comment";
    data[16][1] = (": " + bean.getComment());

    int[] bcellWidth = { 10, 10, 10, 10 };
    int[] bcellAlign = new int[4];
    rg.addFormatedText(s, 6, 0, 1, 0);
    rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);

    String selectQuery = " SELECT  B.EMP_TOKEN,B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME EMP_NAME,DECODE(EMPCARD_APPL_STATUS,'A','Approved','R','Rejected','B','Send Back') FROM HRMS_EMPCARD_APPROVER_DTL A INNER JOIN HRMS_EMP_OFFC B ON (A.EMPCARD_APPROVER_CODE = B.EMP_ID) WHERE EMPCARD_APPL_CODE=" + 
      bean.getBusCardApplCode();

    Object[][] managerData = getSqlModel().getSingleResult(selectQuery);

    Object[][] approvalTable = new Object[managerData.length][4];
    if ((managerData != null) && (managerData.length > 0)) {
      for (int i = 0; i < managerData.length; i++) {
        approvalTable[i][0] = String.valueOf(i + 1);
        approvalTable[i][1] = 
          checkNull(String.valueOf(managerData[i][0]));
        approvalTable[i][2] = 
          checkNull(String.valueOf(managerData[i][1]));
        approvalTable[i][3] = 
          checkNull(String.valueOf(managerData[i][2]));
      }
    }

    String[] appCol = { "Sr. No", "Approver Id", "Approver Name", "Status" };
    int[] appCell = { 5, 10, 25, 10 };
    int[] apprAlign = { 1, 1, 0, 1 };
    rg.tableBody(appCol, approvalTable, appCell, apprAlign);
    rg.createReport(response);
  }

  public boolean deleteBusinessCardInf(EmployeeCardInfo bean)
  {
    boolean result = false;
    try {
      String deleteQry = "DELETE FROM  HRMS_EMPLOYEE_CARD_INFO WHERE EMPLOYEE_APPL_CODE=" + 
        bean.getBusCardApplCode();
      result = getSqlModel().singleExecute(deleteQry);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public String checkNull(String result) {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public Object[][] getStatus(String applCode, String dtlId)
  {
    Object[][] apprDtlObj = (Object[][])null;
    try {
      String apprDtlQry = "SELECT EMPCARD_APPROVER_CODE,DECODE(EMPCARD_APPL_STATUS,'A','Approved','R','Rejected','B','Send Back') FROM HRMS_EMPCARD_APPROVER_DTL  WHERE EMPCARD_APPL_CODE=" + 
        applCode + 
        " AND HRMS_EMPCARD_APPROVER_DTL.EMPCARD_APP_DTL_ID =" + 
        dtlId;
      apprDtlObj = getSqlModel().getSingleResult(apprDtlQry);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return apprDtlObj;
  }

  public String onlineApproveRejectSendBack(HttpServletRequest request, String empId, String applCode, String status, String appComments, String managerID, String level)
  {
    Object[][] statusObj = new Object[1][2];
    boolean result = false;
    String res = "";
    String appStatus = "";
    String resultStatus = "";
    String dtlId = "";
    try {
      String query = " SELECT DECODE(EMPLOYEE_STATUS,'P','Pending','A','Approved','R','Rejected','B','Send Back')FROM  HRMS_EMPLOYEE_CARD_INFO WHERE EMPLOYEE_APPL_CODE =" + 
        applCode;
      Object[][] approverIdObj = getSqlModel().getSingleResult(query);
      if ((approverIdObj != null) && (approverIdObj.length > 0))
        if (String.valueOf(approverIdObj[0][0]).equalsIgnoreCase(
          "Pending")) {
          result = saveApproverDataEmployeeCardInformation(status, 
            empId, applCode, appComments, managerID, false, request);
          String maxQuery = " SELECT MAX(EMPCARD_APP_DTL_ID) FROM HRMS_EMPCARD_APPROVER_DTL ";
          Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);
          dtlId = String.valueOf(maxObj[0][0]);
          statusObj = getStatus(applCode, dtlId);

          logger.info("res....." + result);

          appStatus = String.valueOf(statusObj[0][1]);
          if (appStatus.equalsIgnoreCase("approved")) {
            resultStatus = "Employee Card has been approved.";
          }
          else if (appStatus.equalsIgnoreCase("rejected")) {
            resultStatus = "Employee Card request has been rejected.";
          }
          else if (appStatus.equalsIgnoreCase("Send Back")) {
            resultStatus = "Employee Card has been sent back to applicant.";
          }
          else {
            resultStatus = "Error Occured.";
          }
        }
        else if (String.valueOf(approverIdObj[0][0])
          .equalsIgnoreCase("Send Back")) {
          resultStatus = "Employee Card has already been processed.";
        } else {
          resultStatus = "Employee Card has already been processed.";
        }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return resultStatus;
  }

  public void sendApproverApplicationAlert(String applnID, String module, String applicant, Object[][] empFlow, String applnDate, String status, String adminId, boolean isVenderApproval, HttpServletRequest request)
  {
    try
    {
      try
      {
        MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
        processAlerts.initiate(this.context, this.session);
        module = "Employee Card";
        processAlerts.removeProcessAlert(applnID, module);
        processAlerts.terminate();
      } catch (Exception e) {
        e.printStackTrace();
      }
      String actualStataus = "";
      String[] newKeepInfoObj = (String[])null;
      String[] newVndrKeepInfoObj = (String[])null;
      String alertlink = "";
      String linkParam = "";
      String alternateApprover = "";
      String msgType = "A";
      String level = "1";

      String approver = adminId;
      EmailTemplateBody template = new EmailTemplateBody();
      template.initiate(this.context, this.session);
      template
        .setEmailTemplate("EMPLOYEE CARD INFORMATION -  APPROVER TO APPLICANT");
      template.getTemplateQueries();
      try {
        EmailTemplateQuery templateQuery1 = template
          .getTemplateQuery(1);
        templateQuery1.setParameter(1, approver);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        EmailTemplateQuery templateQuery2 = template
          .getTemplateQuery(2);
        templateQuery2.setParameter(1, applicant);
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
      try {
        template.configMailAlert();
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        alertlink = "/employeeCard/EmployeeCardInfo_retriveDetails.action";
        linkParam = "applicationCode=" + applnID + "&empCode=" + 
          applicant + "&appStatus=P";
        String[] keepInfo = request
          .getParameterValues("keepInformedEmpIdIt");
        String keepInformId = "";
        if (keepInfo != null)
        {
          for (int i = 0; i < keepInfo.length; i++) {
            if (i == keepInfo.length - 1)
              keepInformId = keepInformId + String.valueOf(keepInfo[i]);
            else {
              keepInformId = keepInformId + String.valueOf(keepInfo[i]) + ",";
            }
          }
        }
        if (!keepInformId.equals(""))
          keepInformId = keepInformId.substring(0, 
            keepInformId.length() - 1);
        alternateApprover = "";

        if (status.equals("A")) {
          actualStataus = "Approved";

          template.sendProcessManagerAlert(approver, module, "I", 
            applnID, level, linkParam, alertlink, 
            actualStataus, applicant, alternateApprover, 
            keepInformId, applicant, approver);
        }

        if (status.equals("R")) {
          actualStataus = "Rejected";

          template.sendProcessManagerAlert(approver, module, "I", 
            applnID, level, linkParam, alertlink, 
            actualStataus, applicant, alternateApprover, 
            keepInformId, applicant, approver);
        }

        if (status.equals("B")) {
          actualStataus = "Sent Back";

          String alertlink1 = "/employeeCard/EmployeeCardInfo_getApproverDetails.action";
          String linkParam1 = "hiddenAppCode=" + applnID + "&empId=" + 
            applicant + "&status=B&pplicationLevel=1";

          template.sendProcessManagerAlert("", module, "A", applnID, 
            level, linkParam1, alertlink1, actualStataus, 
            applicant, "", "", applicant, approver);

          template.sendProcessManagerAlert(approver, module, "I", 
            applnID, level, linkParam, alertlink, 
            actualStataus, applicant, alternateApprover, 
            keepInformId, "", approver);
        }
      }
      catch (Exception e) {
        logger.error(e);
      }
      String[] attachment = new String[1];
      attachment[0] = "";

      template.sendApplicationMail();
      template.clearParameters();
      template.terminate();
      if ((isVenderApproval) && (status.equals("A")))
        try {
          EmailTemplateBody templateVender = new EmailTemplateBody();
          templateVender.initiate(this.context, this.session);
          templateVender
            .setEmailTemplate("EMPLOYEE CARD INFORMATION -  APPROVER TO VENDOR");
          templateVender.getTemplateQueries();
          try {
            EmailTemplateQuery templateQuery1 = templateVender
              .getTemplateQuery(1);
            templateQuery1.setParameter(1, approver);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
        	  EmailTemplateQuery templateQuery2 = templateVender
              .getTemplateQuery(2);
          }
          catch (Exception e)
          {
            EmailTemplateQuery templateQuery2;
            e.printStackTrace();
          }
          try
          {
            EmailTemplateQuery templateQuery3 = templateVender
              .getTemplateQuery(3);
            templateQuery3.setParameter(1, applnID);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
            EmailTemplateQuery templateQuery4 = templateVender
              .getTemplateQuery(4);
            templateQuery4.setParameter(1, applnID);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
        	  EmailTemplateQuery templateQuery5 = templateVender
              .getTemplateQuery(5);
          }
          catch (Exception e)
          {
            EmailTemplateQuery templateQuery5;
            e.printStackTrace();
          }
          templateVender.configMailAlert();
          templateVender.sendApplicationMail();
          templateVender.clearParameters();
          templateVender.terminate();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}