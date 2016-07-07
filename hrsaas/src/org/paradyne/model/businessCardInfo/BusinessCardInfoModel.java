package org.paradyne.model.businessCardInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.businessCardInfo.BusinessCardInfo;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.paradyne.model.voucher.CashVoucherModel;

public class BusinessCardInfoModel extends ModelBase
{
  static Logger logger = Logger.getLogger(CashVoucherModel.class);

  public void loadEmployeeInf(BusinessCardInfo bean, String empId) {
    String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, NVL(RANK_NAME, ' '), NVL(DEPT_NAME, ' '), NVL(DIV_NAME,' '),NVL(ADD_MOBILE,' '),NVL(ADD_EMAIL,' '),EMP_ID,DIV_WEBSITE  FROM HRMS_EMP_OFFC LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID) LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID)  LEFT JOIN HRMS_division ON(HRMS_division.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)  LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_OFFC.EMP_ID= HRMS_EMP_ADDRESS.EMP_ID AND ADD_TYPE='P')  where EMP_ID=" + 
      empId + " ORDER BY EMP_ID ";
    Object[][] empInfoObj = getSqlModel().getSingleResult(query);
    if ((empInfoObj != null) && (empInfoObj.length > 0)) {
      bean.setEmpToken1(checkNull(String.valueOf(empInfoObj[0][0])));
      bean.setEmpName1(checkNull(String.valueOf(empInfoObj[0][1])));
      bean.setDesig(checkNull(String.valueOf(empInfoObj[0][2])));
      bean.setDept(checkNull(String.valueOf(empInfoObj[0][3])));
      bean.setCompanyName(checkNull(String.valueOf(empInfoObj[0][4])));
      bean.setMobile(checkNull(String.valueOf(empInfoObj[0][5])));
      bean.setEmailId(checkNull(String.valueOf(empInfoObj[0][6])));
      bean.setEmpId(checkNull(String.valueOf(empInfoObj[0][7])));
      if (String.valueOf(empInfoObj[0][8]).equals("null"))
        bean.setWebSiteAddr("");
      else {
        bean.setWebSiteAddr(String.valueOf(empInfoObj[0][8]));
      }
    }
    String qry = "SELECT  SUBSTR(CENTER_NAME,1,20)||' '||CENTER_ADDRESS1||' '||CENTER_ADDRESS2||' '||CENTER_ADDRESS3, CENTER_PINCODE,NVL(CENTER_TELEPHONE,' '),NVL(CENTER_FAX,' '),CENTER_CITY FROM HRMS_CENTER  WHERE CENTER_ID=(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID=" + 
      empId + ")";
    Object[][] empAddrObj = getSqlModel().getSingleResult(qry);
    if ((empAddrObj.length > 0) && (empAddrObj != null)) {
      bean.setBranch(checkNull(String.valueOf(empAddrObj[0][0])));
      bean.setPinCode(checkNull(String.valueOf(empAddrObj[0][1])));
      bean.setOfficeNum(checkNull(String.valueOf(empAddrObj[0][2])));
      bean.setFaxNum(checkNull(String.valueOf(
        String.valueOf(empAddrObj[0][3]))));
      bean.setLocation(checkNull(String.valueOf(String.valueOf(empAddrObj[0][4]))));
    }
    
    if((empInfoObj != null) && (empInfoObj.length > 0) && (empAddrObj.length > 0) && (empAddrObj != null))
    {	
    }
    else
    {
    	String generalQuery = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC where EMP_ID=" + 
        empId + " ORDER BY EMP_ID ";
    	 Object[][] generalempInfoObj = getSqlModel().getSingleResult(generalQuery);
    	    if ((generalempInfoObj != null) && (generalempInfoObj.length > 0)) {
    	      bean.setEmpToken1(checkNull(String.valueOf(generalempInfoObj[0][0])));
    	      bean.setEmpName1(checkNull(String.valueOf(generalempInfoObj[0][1])));
    	      bean.setEmpId(checkNull(String.valueOf(generalempInfoObj[0][2])));
    }
   }
    
  }

  public void setEmployeeInformation(String empCode, BusinessCardInfo bean)
  {
    try
    {
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

  public void setBusinessCardSavedRecord(BusinessCardInfo bean, String applicationCode)
  {
    String busCardInfoqry = "SELECT BUSINESS_APPL_CODE,BUSINESS_EMP_TOKEN, BUSINESS_EMP_NAME, BUSINESS_EMP_DESIGNATION, BUSINESS_EMP_DEPARTMANT, BUSINESS_EMP_COMPANY, BUSINESS_EMP_BRANCHADDRESS, BUSINESS_EMP_PINCODE, BUSINESS_EMP_LOCATION, BUSINESS_EMP_OFFICENO, BUSINESS_EMP_MOBILE, BUSINESS_EMP_FAX, BUSINESS_EMP_EMAIL, BUSINESS_EMP_WEBSITE, BUSINESS_QTY_CARDS,TO_CHAR(BUSINESS_REQUIRE_DATE,'DD-MM-YYYY'), BUSINESS_COMMENT,BUSINESS_KEEPINFORM ,BUSINESS_STATUS FROM HRMS_BUSINESS_CARD_INFO WHERE  BUSINESS_APPL_CODE=" + 
      applicationCode;

    Object[][] busCardInfoEmpInfoObj = getSqlModel().getSingleResult(
      busCardInfoqry);
    if ((busCardInfoEmpInfoObj != null) && (busCardInfoEmpInfoObj.length > 0)) {
      bean.setBusCardApplCode(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][0])));
      bean.setEmpTokenp(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][1])));
      bean.setEmpNamep(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][2])));
      bean.setDesigp(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][3])));
      bean
        .setDeptp(checkNull(
        String.valueOf(busCardInfoEmpInfoObj[0][4])));
      bean.setCompanyNamep(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][5])));
      bean.setBranchp(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][6])));
      bean.setPinCodep(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][7])));
      bean.setLocationp(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][8])));
      bean.setOfficeNump(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][9])));
      bean.setMobilep(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][10])));
      bean.setFaxNump(checkNull(String.valueOf(
        String.valueOf(busCardInfoEmpInfoObj[0][11]))));
      bean.setEmailIdp(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][12])));
      bean.setWebSiteAddrp(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][13])));
      bean.setQtyOfCards(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][14])));
      bean.setReqDate(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][15])));
      bean.setComment(
        checkNull(String.valueOf(busCardInfoEmpInfoObj[0][16])));

      bean.setStatus(
        String.valueOf(busCardInfoEmpInfoObj[0][18]));

      if (busCardInfoEmpInfoObj[0][17] != null) {
        String vchKiEmpId = 
          String.valueOf(busCardInfoEmpInfoObj[0][17]);
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
              BusinessCardInfo cvApp = new BusinessCardInfo();
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
  }

  public String saveBusinessCardInformation(BusinessCardInfo bean, String status, String strKiEmpCodeIt, Object[][] empFlow)
  {
    Object[][] busCardInfoData = new Object[1][20];
    boolean result = false;
    String flag = "";
    if ((String.valueOf(empFlow[0][0]).equals("")) || (String.valueOf(empFlow[0][0]).equals("null")) || (empFlow[0][0] == null)) {
      empFlow[0][0] = "0";
    }
    busCardInfoData[0][0] = String.valueOf(bean.getEmpId());
    busCardInfoData[0][1] = String.valueOf(bean.getEmpTokenp());
    busCardInfoData[0][2] = String.valueOf(bean.getEmpNamep());
    busCardInfoData[0][3] = String.valueOf(bean.getDesigp());
    busCardInfoData[0][4] = String.valueOf(bean.getDeptp());
    busCardInfoData[0][5] = String.valueOf(bean.getCompanyNamep());
    busCardInfoData[0][6] = String.valueOf(bean.getBranchp());
    busCardInfoData[0][7] = String.valueOf(bean.getPinCodep());
    busCardInfoData[0][8] = String.valueOf(bean.getLocationp());
    busCardInfoData[0][9] = String.valueOf(bean.getOfficeNump());
    busCardInfoData[0][10] = String.valueOf(bean.getMobilep());
    busCardInfoData[0][11] = String.valueOf(bean.getFaxNump());
    busCardInfoData[0][12] = String.valueOf(bean.getEmailIdp());
    busCardInfoData[0][13] = String.valueOf(bean.getWebSiteAddrp());
    busCardInfoData[0][14] = String.valueOf(bean.getQtyOfCards());
    busCardInfoData[0][15] = String.valueOf(bean.getReqDate());
    busCardInfoData[0][16] = String.valueOf(bean.getComment());
    busCardInfoData[0][17] = status;
    busCardInfoData[0][18] = strKiEmpCodeIt;
    busCardInfoData[0][19] = empFlow[0][0];

    String qry = " INSERT  INTO HRMS_BUSINESS_CARD_INFO(BUSINESS_APPL_CODE,BUSINESS_EMP_ID, BUSINESS_EMP_TOKEN, BUSINESS_EMP_NAME, BUSINESS_EMP_DESIGNATION, BUSINESS_EMP_DEPARTMANT, BUSINESS_EMP_COMPANY, BUSINESS_EMP_BRANCHADDRESS, BUSINESS_EMP_PINCODE, BUSINESS_EMP_LOCATION,BUSINESS_EMP_OFFICENO, BUSINESS_EMP_MOBILE, BUSINESS_EMP_FAX, BUSINESS_EMP_EMAIL, BUSINESS_EMP_WEBSITE, BUSINESS_QTY_CARDS,BUSINESS_REQUIRE_DATE, BUSINESS_COMMENT, BUSINESS_STATUS,BUSINESS_KEEPINFORM,BUSINESS_APPROVER )   VALUES((SELECT NVL(MAX(BUSINESS_APPL_CODE),0)+1 FROM HRMS_BUSINESS_CARD_INFO),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?) ";

    result = getSqlModel().singleExecute(qry, busCardInfoData);
    if (result)
      flag = "saved";
    else {
      flag = "notsaved";
    }
    return flag;
  }

  public boolean saveApproverDataBusinessCardInformation(BusinessCardInfo bean, String empId, String applCode, String status, String appComments, String approver, Object[][] empFlow, String[] keepInfo, String MgrApproved, boolean isAdminApproval, String admin, boolean isVenderApproval, String venderEmail, HttpServletRequest request)
  {
    Object[][] busCardInfoUpdateData = new Object[1][3];
    boolean result = false;
    busCardInfoUpdateData[0][0] = String.valueOf(status);
    busCardInfoUpdateData[0][1] = String.valueOf(empId);
    busCardInfoUpdateData[0][2] = String.valueOf(applCode);
    String qry = " UPDATE  HRMS_BUSINESS_CARD_INFO SET  BUSINESS_STATUS=?  WHERE BUSINESS_EMP_ID =? AND BUSINESS_APPL_CODE=?";
    result = getSqlModel().singleExecute(qry, busCardInfoUpdateData);

    Object[][] saveApprDtls = new Object[1][6];
    Date date = new Date();
    SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
    String sysDate = formater.format(date);

    saveApprDtls[0][0] = String.valueOf(applCode);
    saveApprDtls[0][1] = String.valueOf(approver);
    saveApprDtls[0][2] = String.valueOf(status);
    saveApprDtls[0][3] = String.valueOf(appComments);
    saveApprDtls[0][4] = String.valueOf(sysDate);
    saveApprDtls[0][5] = String.valueOf(empId);

    String ManagerApproved = IsMgrApproved(bean.getBusCardApplCode());
    if (ManagerApproved.equals("Y"))
    {
      if (result) {
        String saveQry = "INSERT INTO HRMS_BUSINESCARD_APPROVER_DTL(BCARD_APPL_CODE, BCARD_APPROVER_CODE, BCARD_APPL_STATUS, BCARD_APPR_COMMENTS, BCARD_APPL_APPROVAL_DATE, BCARD_APP_DTL_ID, BCARD_EMP_ID, BCARD_APPROVER_TYPE) VALUES(?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),(SELECT NVL(MAX(BCARD_APP_DTL_ID),0)+1 FROM HRMS_BUSINESCARD_APPROVER_DTL),?, 'A')";

        result = getSqlModel().singleExecute(saveQry, saveApprDtls);
      }

    }
    else if (result) {
      String saveQry = "INSERT INTO HRMS_BUSINESCARD_APPROVER_DTL(BCARD_APPL_CODE, BCARD_APPROVER_CODE, BCARD_APPL_STATUS, BCARD_APPR_COMMENTS, BCARD_APPL_APPROVAL_DATE, BCARD_APP_DTL_ID, BCARD_EMP_ID, BCARD_APPROVER_TYPE) VALUES(?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),(SELECT NVL(MAX(BCARD_APP_DTL_ID),0)+1 FROM HRMS_BUSINESCARD_APPROVER_DTL),?, 'M')";

      result = getSqlModel().singleExecute(saveQry, saveApprDtls);
    }

    sendApproverApplicationAlert(request, applCode, "Business Card", 
      empId, empFlow, isAdminApproval, MgrApproved, 
      keepInfo, status, approver, admin, isVenderApproval, venderEmail);
    return result;
  }

  public Object[][] getStatus(String applCode, String managerID, String dtlId) {
    Object[][] apprDtlObj = (Object[][])null;
    try {
      String apprDtlQry = "SELECT BCARD_APPROVER_CODE,DECODE(BCARD_APPL_STATUS,'A','Approved','R','Rejected','B','Send Back') FROM HRMS_BUSINESCARD_APPROVER_DTL  WHERE BCARD_APPL_CODE=" + 
        applCode + "AND BCARD_APPROVER_CODE=" + managerID + "AND BCARD_APP_DTL_ID=" + dtlId;
      apprDtlObj = getSqlModel().getSingleResult(apprDtlQry);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return apprDtlObj;
  }
  public String updateBuisnessCardMgrsStatus(String applcode, String applicant, String status) {
    Object[][] busCardInfoUpdateData = new Object[1][3];
    String flag = "";
    boolean result = false;
    busCardInfoUpdateData[0][0] = status;
    busCardInfoUpdateData[0][1] = String.valueOf(applcode);
    busCardInfoUpdateData[0][2] = String.valueOf(applicant);
    String qry = " UPDATE  HRMS_BUSINESS_CARD_INFO SET BUSINESS_MGR_APPR=? WHERE BUSINESS_APPL_CODE =? AND BUSINESS_EMP_ID=? ";

    result = getSqlModel().singleExecute(qry, busCardInfoUpdateData);
    if (result)
      flag = "updated";
    else {
      flag = "notupdated";
    }
    return flag;
  }

  public String updateEmployeeCardInformation(BusinessCardInfo bean, String status, String strKiEmpCodeIt, Object[][] empFlow) {
    Object[][] busCardInfoUpdateData = new Object[1][21];
    String flag = "";
    boolean result = false;
    if ((String.valueOf(empFlow[0][0]).equals("")) || (String.valueOf(empFlow[0][0]).equals("null")) || (empFlow[0][0] == null)) {
      empFlow[0][0] = "0";
    }
    busCardInfoUpdateData[0][0] = String.valueOf(bean.getEmpTokenp());
    busCardInfoUpdateData[0][1] = String.valueOf(bean.getEmpNamep());
    busCardInfoUpdateData[0][2] = String.valueOf(bean.getDesigp());
    busCardInfoUpdateData[0][3] = String.valueOf(bean.getDeptp());
    busCardInfoUpdateData[0][4] = String.valueOf(bean.getCompanyNamep());
    busCardInfoUpdateData[0][5] = String.valueOf(bean.getBranchp());
    busCardInfoUpdateData[0][6] = String.valueOf(bean.getPinCodep());
    busCardInfoUpdateData[0][7] = String.valueOf(bean.getLocationp());
    busCardInfoUpdateData[0][8] = String.valueOf(bean.getOfficeNump());
    busCardInfoUpdateData[0][9] = String.valueOf(bean.getMobilep());
    busCardInfoUpdateData[0][10] = String.valueOf(bean.getFaxNump());
    busCardInfoUpdateData[0][11] = String.valueOf(bean.getEmailIdp());
    busCardInfoUpdateData[0][12] = String.valueOf(bean.getWebSiteAddrp());
    busCardInfoUpdateData[0][13] = String.valueOf(bean.getComment());
    busCardInfoUpdateData[0][14] = status;
    busCardInfoUpdateData[0][15] = strKiEmpCodeIt;
    busCardInfoUpdateData[0][16] = empFlow[0][0];
    busCardInfoUpdateData[0][17] = String.valueOf(bean.getQtyOfCards());
    busCardInfoUpdateData[0][18] = String.valueOf(bean.getReqDate());
    busCardInfoUpdateData[0][19] = 
      String.valueOf(bean.getBusCardApplCode());
    busCardInfoUpdateData[0][20] = String.valueOf(bean.getEmpId());
    
    String qry = " UPDATE  HRMS_BUSINESS_CARD_INFO SET BUSINESS_EMP_TOKEN=?, BUSINESS_EMP_NAME=?, BUSINESS_EMP_DESIGNATION=?, BUSINESS_EMP_DEPARTMANT=?, BUSINESS_EMP_COMPANY=?, BUSINESS_EMP_BRANCHADDRESS=?, BUSINESS_EMP_PINCODE=?, BUSINESS_EMP_LOCATION=?,BUSINESS_EMP_OFFICENO=?, BUSINESS_EMP_MOBILE=?, BUSINESS_EMP_FAX=?, BUSINESS_EMP_EMAIL=?, BUSINESS_EMP_WEBSITE=?,BUSINESS_COMMENT=?, BUSINESS_STATUS=?,BUSINESS_KEEPINFORM=?,BUSINESS_APPROVER = ?, BUSINESS_QTY_CARDS = ?, BUSINESS_REQUIRE_DATE=TO_DATE(?,'DD-MM-YYYY') WHERE BUSINESS_APPL_CODE =? AND BUSINESS_EMP_ID=? ";

    result = getSqlModel().singleExecute(qry, busCardInfoUpdateData);
    if (result)
      flag = "updated";
    else {
      flag = "notupdated";
    }
    return flag;
  }

  public String getBCardApplCode(BusinessCardInfo bean) {
    String applCodeQry = "SELECT NVL(MAX(BUSINESS_APPL_CODE),0) FROM HRMS_BUSINESS_CARD_INFO";
    Object[][] applCodeObj = getSqlModel().getSingleResult(applCodeQry);
    String applCode = String.valueOf(applCodeObj[0][0]);
    return applCode;
  }

  public void getApproversDtl(BusinessCardInfo bean)
  {
    try {
      String apprDtlQry = " SELECT  B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME EMP_NAME, BCARD_APPR_COMMENTS,TO_CHAR(BCARD_APPL_APPROVAL_DATE,'DD-MM-YYYY'),DECODE(BCARD_APPL_STATUS,'A','Approved','R','Rejected','B','Send Back') FROM HRMS_BUSINESCARD_APPROVER_DTL A INNER JOIN HRMS_EMP_OFFC B ON (A.BCARD_APPROVER_CODE = B.EMP_ID) WHERE BCARD_APPL_CODE=" + 
        bean.getBusCardApplCode();
      Object[][] apprDtlObj = getSqlModel().getSingleResult(apprDtlQry);
      int counter1 = 0;
      ArrayList tmpApprovedLst = new ArrayList();
      BusinessCardInfo bean1 = null;
      if ((apprDtlObj != null) && (apprDtlObj.length > 0)) {
        for (int i = 0; i < apprDtlObj.length; i++) {
          bean1 = new BusinessCardInfo();
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

  public void loadManager(BusinessCardInfo busCardInfoBean, Object[][] empFlow, boolean IsAdminApproval) {
    try {
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
            BusinessCardInfo bean = new BusinessCardInfo();
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
      else
      {
        getAdminName(busCardInfoBean);
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

  public void keepInformEmployee(BusinessCardInfo bean)
  {
    String qry = "SELECT BUSINESS_KEEPINFORM FROM HRMS_BUSINESS_CARD_INFO WHERE BUSINESS_EMP_ID=" + 
      bean.getEmpId() + 
      " AND BUSINESS_APPL_CODE=" + 
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
            BusinessCardInfo cvApp = new BusinessCardInfo();
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

  public void displayIteratorValueForKeepInformed(String[] srNo, String[] empId, String[] empToken, String[] empName, BusinessCardInfo cvoucher)
  {
    try
    {
      ArrayList cvList = new ArrayList();
      if (srNo != null) {
        for (int i = 0; i < srNo.length; i++) {
          BusinessCardInfo busCardInfo = new BusinessCardInfo();
          busCardInfo.setSerialNoIt(srNo[i]);
          busCardInfo.setKeepInformedEmpIdIt(empId[i]);
          busCardInfo.setKeepInformedEmpTokenIt(empToken[i]);
          busCardInfo.setKeepInformedEmpNameIt(empName[i]);
          cvList.add(busCardInfo);
        }
        cvoucher.setKeepInformedList(cvList);
      }
    }
    catch (Exception e) {
      logger
        .error("Exception in displayIteratorValueForKeepInformed----------" + 
        e);
    }
  }

  public void setKeepInformed(String[] srNo, String[] empCode, String[] empToken, String[] empName, BusinessCardInfo busCardInfo)
  {
    try
    {
      BusinessCardInfo cvApp = new BusinessCardInfo();
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

  private ArrayList<BusinessCardInfo> displayNewValueForKeepInformed(String[] srNo, String[] empId, String[] empToken, String[] empName, BusinessCardInfo bean)
  {
    ArrayList cvList = null;
    try {
      cvList = new ArrayList();
      if (srNo != null)
        for (int i = 0; i < srNo.length; i++) {
          BusinessCardInfo cvbean = new BusinessCardInfo();
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

  public void removeKeepInformedData(String[] serialNo, String[] empCode, String[] empToken, String[] empName, BusinessCardInfo busCardInfoBean, String removeEmpId, String empID)
  {
    try
    {
      ArrayList tableList = new ArrayList();
      String qry = "SELECT BUSINESS_KEEPINFORM FROM HRMS_BUSINESS_CARD_INFO WHERE BUSINESS_EMP_ID=" + 
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
        String upateQry = "UPDATE  HRMS_BUSINESS_CARD_INFO SET  BUSINESS_KEPPINFORM='" + 
          employeeId + 
          "' WHERE BUSINESS_EMP_ID=" + 
          busCardInfoBean.getEmpId() + 
          " AND BUSINESS_APPL_CODE=" + 
          busCardInfoBean.getBusCardApplCode();
        getSqlModel().singleExecute(upateQry);
      }
      if (empCode != null) {
        for (int i = 0; i < empCode.length; i++) {
          BusinessCardInfo bean = new BusinessCardInfo();
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

  public boolean deleteBusinessCardInf(BusinessCardInfo bean)
  {
    String deleteQry = "DELETE FROM  HRMS_BUSINESS_CARD_INFO WHERE BUSINESS_APPL_CODE=" + 
      bean.getBusCardApplCode();
    boolean result = getSqlModel().singleExecute(deleteQry);
    return result;
  }

  public String checkNull(String result) {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public void report(BusinessCardInfo bean, HttpServletRequest request, HttpServletResponse response, Object[][] empFlow)
  {
    String s = "\nBusiness Card Information\n\n";
    ReportGenerator rg = new ReportGenerator("Pdf", 
      s);

    Object[][] data = new Object[19][4];

    data[0][0] = "Applicant Details in System";
    data[0][1] = "";
    data[0][2] = "Business Card Information To Be Printed ";
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

    data[15][0] = "\n Quantity and Comments \n";
    data[15][1] = "";
    data[15][2] = "";
    data[15][3] = "";

    data[16][0] = "Quality of Cards";
    data[16][1] = (": " + bean.getQtyOfCards());
    data[16][2] = "Required upto date";
    data[16][3] = (": " + bean.getReqDate());

    data[17][0] = "Comment";
    data[17][1] = (": " + bean.getComment());

    int[] bcellWidth = { 10, 10, 10, 10 };
    int[] bcellAlign = new int[4];
    rg.addFormatedText(s, 6, 0, 1, 0);
    rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);

    String selectQuery = " SELECT  B.EMP_TOKEN,B.EMP_FNAME||' '||B.EMP_MNAME||' '||B.EMP_LNAME EMP_NAME,DECODE(BCARD_APPL_STATUS,'A','Approved','R','Rejected','B','Send Back') FROM HRMS_BUSINESCARD_APPROVER_DTL A INNER JOIN HRMS_EMP_OFFC B ON (A.BCARD_APPROVER_CODE = B.EMP_ID) WHERE BCARD_APPL_CODE=" + 
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
        approvalTable[i][3] = checkNull(String.valueOf(managerData[i][2]));
      }

    }

    String[] appCol = { "Sr. No", "Approver Id", "Approver Name", "Status" };
    int[] appCell = { 5, 10, 25, 10 };
    int[] apprAlign = { 1, 1, 0, 1 };
    rg.tableBody(appCol, approvalTable, appCell, apprAlign);
    rg.createReport(response);
  }

  public Object[][] getVendorDtl(BusinessCardInfo bean) {
    String vndrDtlQry = "SELECT CONF_BCARD_VNDR_FLAG,CONF_BCARD_VNDR_EMAIL FROM HRMS_ADMIN_CONF";
    Object[][] vndrDtlObj = getSqlModel().getSingleResult(vndrDtlQry);
    if ((vndrDtlObj != null) && (vndrDtlObj.length > 0)) {
      if (vndrDtlObj[0][0].equals("Y")) {
        bean.setTomail(String.valueOf(vndrDtlObj[0][1]));
        return vndrDtlObj;
      }
      bean.setTomail("");
      return null;
    }

    return null;
  }

  public Object[][] getAdminDetails(BusinessCardInfo bean)
  {
    Object[][] adminDtl = new Object[1][2];
    String vdnrEmailId = "";
    String adminEmpEmailId = "";
    Object[][] empMailIdQryObj = (Object[][])null;
    Object[][] divIdObj = (Object[][])null;
    Object[][] confEmpIdObj = (Object[][])null;
    String vndrDtlQry = "SELECT CONF_BCARD_VNDR_FLAG, CONF_BCARD_VNDR_EMAIL,CONF_BCARD_MGR_FLAG  FROM HRMS_ADMIN_CONF";
    Object[][] vndrDtlObj = getSqlModel().getSingleResult(vndrDtlQry);

    if ((vndrDtlObj != null) && (vndrDtlObj.length > 0)) {
      if (vndrDtlObj[0][0].equals("Y")) {
        vdnrEmailId = String.valueOf(vndrDtlObj[0][1]);
        adminDtl[0][0] = vdnrEmailId;
      } else {
        adminDtl[0][0] = "";
      }
      String divIdQry = "SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION WHERE DIV_NAME='" + bean.getCompanyName() + "'";
      divIdObj = getSqlModel().getSingleResult(divIdQry);

      if ((divIdObj != null) && (divIdObj.length > 0)) {
        String confDtlQry = "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=" + divIdObj[0][0] + " AND CONF_TYPE='BcardAdmin'";
        confEmpIdObj = getSqlModel().getSingleResult(confDtlQry);
      }

      if ((confEmpIdObj != null) && (confEmpIdObj.length > 0)) {
        String empMailIdQry = "SELECT ADD_EMAIL FROM HRMS_EMP_ADDRESS  WHERE EMP_ID=" + confEmpIdObj[0][0];

        empMailIdQryObj = getSqlModel().getSingleResult(empMailIdQry);
      }

      if ((empMailIdQryObj != null) && (empMailIdQryObj.length > 0)) {
        adminEmpEmailId = String.valueOf(empMailIdQryObj[0][0]);
        adminDtl[0][1] = adminEmpEmailId;
      } else {
        adminDtl[0][1] = "";
      }

    }

    return adminDtl;
  }

  public String getAdminAppDet(BusinessCardInfo bean) {
    String divIdQry = "SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID=" + bean.getEmpId();
    Object[][] divIdObj = getSqlModel().getSingleResult(divIdQry);

    String confDtlQry = "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=" + divIdObj[0][0] + "AND CONF_TYPE='BcardAdmin'";
    Object[][] confEmpIdObj = getSqlModel().getSingleResult(confDtlQry);

    if ((confEmpIdObj != null) && (confEmpIdObj.length > 0)) {
      return String.valueOf(confEmpIdObj[0][0]);
    }
    return null;
  }

  public void getAdminName(BusinessCardInfo bean)
  {
    Object[][] adminDtl = new Object[1][3];
    Object[][] adminNameObj = (Object[][])null;
    try
    {
      String divIdQry = "SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID=" + bean.getEmpId();
      Object[][] divIdObj = getSqlModel().getSingleResult(divIdQry);
      String confDtlQry = "SELECT  CONF_EMP FROM HRMS_ADMIN_CONF_DTL WHERE CONF_DIV=" + divIdObj[0][0] + " AND CONF_TYPE='BcardAdmin'";
      Object[][] confEmpIdObj = getSqlModel().getSingleResult(confDtlQry);
      if ((confEmpIdObj != null) && (confEmpIdObj.length > 0)) {
        adminDtl[0][1] = String.valueOf(confEmpIdObj[0][0]);
        String empMailIdQry = "SELECT EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC  WHERE EMP_ID=" + confEmpIdObj[0][0];
        adminNameObj = getSqlModel().getSingleResult(empMailIdQry);
        if ((adminNameObj != null) && (adminNameObj.length > 0)) {
          ArrayList arrList = new ArrayList();
          for (int i = 0; i < adminNameObj.length; i++) {
            BusinessCardInfo bean1 = new BusinessCardInfo();
            bean1.setManagerIdIt(String.valueOf(adminNameObj[i][0]));
            bean1.setManagerNameIt(String.valueOf(adminNameObj[i][1]));
            String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
            bean.setSrNoIt(srNo);
            arrList.add(bean1);
          }
          bean.setManagerList(arrList);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String IsBcardDAdminFlag()
  {
    String isAdminFlaguery = "SELECT CONF_BCARD_MGR_FLAG  FROM HRMS_ADMIN_CONF";
    Object[][] isAdminFlagObj = getSqlModel().getSingleResult(isAdminFlaguery);
    if ((isAdminFlagObj != null) && (isAdminFlagObj.length > 0)) {
      return String.valueOf(isAdminFlagObj[0][0]);
    }
    return null;
  }

  public String IsMgrApproved(String busCardApplCode)
  {
    String IsMgrApproved = "SELECT BUSINESS_MGR_APPR FROM HRMS_BUSINESS_CARD_INFO WHERE BUSINESS_APPL_CODE=" + busCardApplCode;
    Object[][] IsMgrApproved1 = getSqlModel().getSingleResult(IsMgrApproved);
    if ((IsMgrApproved1 != null) && (IsMgrApproved1.length > 0)) {
      return String.valueOf(IsMgrApproved1[0][0]);
    }
    return null;
  }

  public String onlineApproveRejectSendBack(HttpServletRequest request, String empId, String applCode, String status, String appComments, String managerID, String MgrApproved, String ManagerApproval, String admin)
  {
    Object[][] statusObj = new Object[1][2];
    boolean result = false;
    String res = "";
    String appStatus = "";
    String resultStatus = "";
    String dtlId = "";
    try {
      String query = " SELECT BUSINESS_MGR_APPR,DECODE(BUSINESS_STATUS,'P','Pending','A','Approved','R','Rejected','B','Send Back')FROM  HRMS_BUSINESS_CARD_INFO WHERE BUSINESS_APPL_CODE =" + 
        applCode;

      Object[][] approverIdObj = getSqlModel().getSingleResult(query);

      if ((approverIdObj != null) && (approverIdObj.length > 0)) {
        boolean IsManagerApproval = ManagerApproval.equals("Y");
        if ((IsManagerApproval) && (String.valueOf(approverIdObj[0][0]).equals("N"))) {
          result = saveApproverDataBusinessCardInformation(null, empId, applCode, status, appComments, managerID, 
            null, null, MgrApproved, IsManagerApproval, admin, false, null, request);
          String maxQuery = " SELECT MAX(BCARD_APP_DTL_ID) FROM HRMS_BUSINESCARD_APPROVER_DTL ";
          Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);
          dtlId = String.valueOf(maxObj[0][0]);
          statusObj = getStatus(applCode, managerID, dtlId);

          logger.info("res....." + result);

          appStatus = String.valueOf(statusObj[0][1]);
          if (appStatus.equalsIgnoreCase("approved")) {
            resultStatus = "Business Card has been approved.";
          }
          else if (appStatus.equalsIgnoreCase("rejected")) {
            resultStatus = "Business Card request has been rejected.";
          }
          else if (appStatus.equalsIgnoreCase("Send Back")) {
            resultStatus = "Business Card has been sent back to applicant.";
          }
          else
          {
            resultStatus = "Error Occured.";
          }
        } else if ((!IsManagerApproval) && (String.valueOf(approverIdObj[0][1]).equals("Pending"))) {
          result = saveApproverDataBusinessCardInformation(null, empId, applCode, status, appComments, managerID, 
            null, null, MgrApproved, IsManagerApproval, admin, false, null, request);
          String maxQuery = " SELECT MAX(BCARD_APP_DTL_ID) FROM HRMS_BUSINESCARD_APPROVER_DTL ";
          Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);
          dtlId = String.valueOf(maxObj[0][0]);
          statusObj = getStatus(applCode, admin, dtlId);
          logger.info("res....." + result);

          appStatus = String.valueOf(statusObj[0][1]);
          if (appStatus.equalsIgnoreCase("approved")) {
            resultStatus = "Business Card has been approved.";
          }
          else if (appStatus.equalsIgnoreCase("rejected")) {
            resultStatus = "Business Card request has been rejected.";
          }
          else if (appStatus.equalsIgnoreCase("Send Back")) {
            resultStatus = "Business Card has been sent back to applicant.";
          }
          else
          {
            resultStatus = "Error Occured.";
          }
        } else if (admin.equals(managerID)) {
          String Adminquery = " SELECT BCARD_APPROVER_CODE,DECODE(BCARD_APPL_STATUS,'P','Pending','A','Approved','R','Rejected','B','Send Back')FROM  HRMS_BUSINESCARD_APPROVER_DTL WHERE HRMS_BUSINESCARD_APPROVER_DTL.BCARD_APPL_CODE =" + 
            applCode + 
            " AND HRMS_BUSINESCARD_APPROVER_DTL.BCARD_APPROVER_CODE=" + admin;

          Object[][] adminApprovedObj = getSqlModel().getSingleResult(Adminquery);
          if ((adminApprovedObj != null) && (adminApprovedObj.length == 0)) {
            result = saveApproverDataBusinessCardInformation(null, empId, applCode, status, appComments, managerID, 
              null, null, MgrApproved, IsManagerApproval, admin, false, null, request);
            String maxQuery = " SELECT MAX(BCARD_APP_DTL_ID) FROM HRMS_BUSINESCARD_APPROVER_DTL ";
            Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);
            dtlId = String.valueOf(maxObj[0][0]);
            statusObj = getStatus(applCode, admin, dtlId);

            logger.info("res....." + result);

            appStatus = String.valueOf(statusObj[0][1]);
            if (appStatus.equalsIgnoreCase("approved")) {
              resultStatus = "Business Card has been approved.";
            }
            else if (appStatus.equalsIgnoreCase("rejected")) {
              resultStatus = "Business Card request has been rejected.";
            }
            else if (appStatus.equalsIgnoreCase("Send Back")) {
              resultStatus = "Business Card has been sent back to applicant.";
            }
            else
            {
              resultStatus = "Error Occured.";
            }
          } else {
            resultStatus = "Business Card has been allready processed";
          }
        } else {
          resultStatus = "Business Card has been allready processed";
        }
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return resultStatus;
  }

  public void sendApproverApplicationAlert(HttpServletRequest request, String applnID, String module, String applicant, Object[][] empFlow, boolean isAdminApproval, String MgrApproved, String[] keepInfo, String status, String approver, String adminId, boolean venderFlag, String venderEmail)
  {
    try
    {
      BusinessCardInfoModel model = new BusinessCardInfoModel();
      model.initiate(this.context, this.session);
      try
      {
        MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
        processAlerts.initiate(this.context, this.session);
        module = "Business Card";
        processAlerts.removeProcessAlert(applnID, module);
        processAlerts.terminate();
      } catch (Exception e) {
        e.printStackTrace();
      }
      String actualStataus = "";
      String alertlink = "";
      String keepInfoTo = "";

      if (keepInfo != null) {
        for (int i = 0; i < keepInfo.length; i++) {
          if (i == keepInfo.length - 1)
            keepInfoTo = keepInfoTo + String.valueOf(keepInfo[i]);
          else {
            keepInfoTo = keepInfoTo + String.valueOf(keepInfo[i]) + ",";
          }
        }
      }
      String linkParam = "";
      String alternateApprover = "";
      String level = "1";
      if ((isAdminApproval) && (MgrApproved.equals("N")))
      {
        alternateApprover = (empFlow != null) && 
          (!String.valueOf(
          empFlow[0][3]).equals("0")) ? 
          String.valueOf(empFlow[0][3]) : "";
        if (status.equals("A")) {
          actualStataus = "Approved";
          String flag = model.updateBuisnessCardMgrsStatus(applnID, applicant, "Y");

          EmailTemplateBody template = new EmailTemplateBody();
          template.initiate(this.context, this.session);
          template.setEmailTemplate("BUSINESS CARD INFORMATION -  MANAGER TO ADMIN");
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
            templateQuery2.setParameter(1, adminId);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try
          {
            EmailTemplateQuery templateQuery3 = template
              .getTemplateQuery(3);
            templateQuery3.setParameter(1, applnID);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try
          {
            EmailTemplateQuery templateQuery4 = template
              .getTemplateQuery(4);
            templateQuery4.setParameter(1, adminId);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
            EmailTemplateQuery template1Query5 = template
              .getTemplateQuery(5);
            template1Query5.setParameter(1, applicant);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
            EmailTemplateQuery template1Query6 = template
              .getTemplateQuery(6);
            template1Query6.setParameter(1, applicant);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
            EmailTemplateQuery template1Query7 = template
              .getTemplateQuery(7);
            template1Query7.setParameter(1, applnID);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
            EmailTemplateQuery template1Query8 = template
              .getTemplateQuery(8);
            template1Query8.setParameter(1, applnID);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
            template.configMailAlert();
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
            alertlink = "/businessCard/BusinessCardInfo_getApproverDetails.action";
            linkParam = "hiddenAppCode=" + applnID + "&empId=" + applicant + "&status=P&pplicationLevel=1";
            template.sendProcessManagerAlert(adminId, module, "A", 
              applnID, level, linkParam, alertlink, "Pending", 
              applicant, alternateApprover, "", "", approver);
          } catch (Exception e) {
            e.printStackTrace();
          }
          String[] link_param = new String[3];
          String[] link_label = new String[3];
          String applicationType = "BusinessCardInfoAppl";

          link_param[0] = 
            (applicationType + "#" + applicant + "#" + applnID + 
            "#" + "A" + "#" + "..." + "#" + adminId + "#" + MgrApproved + "#" + 
            isAdminApproval + "#" + adminId);
          link_param[1] = 
            (applicationType + "#" + applicant + "#" + applnID + 
            "#" + "R" + "#" + "..." + "#" + adminId + "#" + MgrApproved + "#" + 
            isAdminApproval + "#" + adminId);
          link_param[2] = 
            (applicationType + "#" + applicant + "#" + applnID + 
            "#" + "B" + "#" + "..." + "#" + adminId + "#" + MgrApproved + "#" + 
            isAdminApproval + "#" + adminId);
          /*link_label[0] = "Approve";
          link_label[1] = "Reject";
          link_label[2] = "Send Back";

          template.addOnlineLink(request, link_param, link_label);*/
          template.sendApplicationMail();
          template.clearParameters();
          template.terminate();
        }
        EmailTemplateBody template1 = new EmailTemplateBody();
        template1.initiate(this.context, this.session);
        if (!status.equals("A"))
          template1.setEmailTemplate("BUSINESS CARD INFORMATION -  APPROVER TO APPLICANT");
        else {
          template1.setEmailTemplate("BUSINESS CARD INFORMATION -  APPROVER TO APPLICANT FORWARDED TO ADMIN");
        }
        template1.getTemplateQueries();
        try {
          EmailTemplateQuery template1Query1 = template1
            .getTemplateQuery(1);
          template1Query1.setParameter(1, approver);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          EmailTemplateQuery template1Query2 = template1
            .getTemplateQuery(2);
          template1Query2.setParameter(1, applicant);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try
        {
          EmailTemplateQuery template1Query3 = template1
            .getTemplateQuery(3);
          template1Query3.setParameter(1, applnID);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try
        {
          EmailTemplateQuery template1Query4 = template1
            .getTemplateQuery(4);
          template1Query4.setParameter(1, approver);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          EmailTemplateQuery template1Query5 = template1
            .getTemplateQuery(5);
          template1Query5.setParameter(1, applicant);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          EmailTemplateQuery template1Query6 = template1
            .getTemplateQuery(6);
          template1Query6.setParameter(1, applicant);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          EmailTemplateQuery template1Query7 = template1
            .getTemplateQuery(7);
          template1Query7.setParameter(1, applnID);
        } catch (Exception e) {
          e.printStackTrace();
        }
        if (status.equals("A")) {
          try {
            EmailTemplateQuery template1Query8 = template1
              .getTemplateQuery(8);
            template1Query8.setParameter(1, adminId);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        
        try {
            EmailTemplateQuery template1Query9 = template1
              .getTemplateQuery(9);
            template1Query9.setParameter(1, applnID);
          } catch (Exception e) {
            e.printStackTrace();
          }

        template1.configMailAlert();
        try {
          alertlink = "/businessCard/BusinessCardInfo_retriveDetails.action";
          linkParam = "applicationCode=" + applnID + "&empCode=" + 
            applicant + "&appStatus=" + status;
          if (status.equals("A")) {
            actualStataus = "Approved";
          }
          if (status.equals("R")) {
            actualStataus = "Rejected";
          }
          if (status.equals("B")) {
            actualStataus = "Sent Back";

            String alertlink1 = "/businessCard/BusinessCardInfo_getApproverDetails.action";
            String linkParam1 = "hiddenAppCode=" + applnID + "&empId=" + 
              applicant + "&status=B&pplicationLevel=1";
            String flag = model.updateBuisnessCardMgrsStatus(applnID, applicant, "N");

            template1.sendProcessManagerAlert("", module, "A", applnID, 
              level, linkParam1, alertlink1, actualStataus, 
              applicant, "", "", applicant, approver);

            template1.sendProcessManagerAlert(approver, module, "I", 
              applnID, level, linkParam, alertlink, 
              actualStataus, applicant, alternateApprover, 
              keepInfoTo, "", approver);
          }
          alternateApprover = (empFlow != null) && 
            (!String.valueOf(
            empFlow[0][3]).equals("0")) ? 
            String.valueOf(empFlow[0][3]) : "";
          if (!status.equals("B"))
            template1.sendProcessManagerAlert(approver, module, "I", 
              applnID, level, linkParam, alertlink, actualStataus, 
              applicant, alternateApprover, keepInfoTo, applicant, approver);
        }
        catch (Exception e) {
          logger.error(e);
        }
        try {
          if (!String.valueOf(empFlow[0][3]).equals("0"))
            template1.sendApplicationMailToAlternateApprover(String.valueOf(empFlow[0][3]));
        }
        catch (Exception e) {
          e.printStackTrace();
        }
        String[] attachment = new String[1];
        attachment[0] = "";
        if (keepInfo != null) {
          template1.sendApplicationMailToKeepInfo(keepInfo);
        }
        template1.sendApplicationMail();
        template1.clearParameters();
        template1.terminate();
      } else {
        if (isAdminApproval) {
          approver = adminId;
        }
        else {
          approver = String.valueOf(empFlow[0][0]);
        }
        EmailTemplateBody template = new EmailTemplateBody();
        template.initiate(this.context, this.session);
        template
          .setEmailTemplate("BUSINESS CARD INFORMATION -  APPROVER TO APPLICANT");
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
          templateQuery3.setParameter(1, applnID);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try
        {
          EmailTemplateQuery templateQuery4 = template
            .getTemplateQuery(4);
          templateQuery4.setParameter(1, approver);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          EmailTemplateQuery template1Query5 = template
            .getTemplateQuery(5);
          template1Query5.setParameter(1, applicant);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          EmailTemplateQuery template1Query6 = template
            .getTemplateQuery(6);
          template1Query6.setParameter(1, applicant);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          EmailTemplateQuery template1Query7 = template
            .getTemplateQuery(7);
          template1Query7.setParameter(1, applnID);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          EmailTemplateQuery template1Query8 = template
            .getTemplateQuery(8);
          template1Query8.setParameter(1, applnID);
        } catch (Exception e) {
          e.printStackTrace();
        }
        template.configMailAlert();
        try {
          alertlink = "/businessCard/BusinessCardInfo_retriveDetails.action";
          linkParam = "applicationCode=" + applnID + "&empCode=" + 
            applicant + "&appStatus=" + status;
          if (status.equals("A")) {
            actualStataus = "Approved";
          }
          if (status.equals("R")) {
            actualStataus = "Rejected";
          }
          if (status.equals("B")) {
            actualStataus = "Sent Back";
            String alertlink1 = "/businessCard/BusinessCardInfo_getApproverDetails.action";
            String linkParam1 = "hiddenAppCode=" + applnID + "&empId=" + 
              applicant + "&status=B&pplicationLevel=1";

            String flag = model.updateBuisnessCardMgrsStatus(applnID, applicant, "N");
            template.sendProcessManagerAlert("", module, "A", applnID, 
              level, linkParam1, alertlink1, actualStataus, 
              applicant, "", "", applicant, approver);
            template.sendProcessManagerAlert(approver, module, "I", 
              applnID, level, linkParam, alertlink, 
              actualStataus, applicant, alternateApprover, 
              keepInfoTo, "", approver);
          }
          alternateApprover = (empFlow != null) && 
            (!String.valueOf(
            empFlow[0][3]).equals("0")) ? 
            String.valueOf(empFlow[0][3]) : "";
          if (!status.equals("B"))
            template.sendProcessManagerAlert(approver, module, "I", 
              applnID, level, linkParam, alertlink, actualStataus, 
              applicant, alternateApprover, keepInfoTo, applicant, approver);
        }
        catch (Exception e) {
          logger.error(e);
        }
        try {
          if (!String.valueOf(empFlow[0][3]).equals("0"))
            template.sendApplicationMailToAlternateApprover(String.valueOf(empFlow[0][3]));
        }
        catch (Exception e) {
          e.printStackTrace();
        }
        if (keepInfo != null) {
          template.sendApplicationMailToKeepInfo(keepInfo);
        }
        String[] attachment = new String[1];
        attachment[0] = "";

        template.sendApplicationMail();
        template.clearParameters();
        template.terminate();

        if ((venderFlag) && (status.equals("A"))) {
          EmailTemplateBody templateVender = new EmailTemplateBody();
          templateVender.initiate(this.context, this.session);
          templateVender
            .setEmailTemplate("BUSINESS CARD INFORMATION - TO VENDER");
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
          try
          {
            EmailTemplateQuery templateQuery4 = templateVender
              .getTemplateQuery(4);
            templateQuery4.setParameter(1, approver);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
            EmailTemplateQuery template1Query5 = templateVender
              .getTemplateQuery(5);
            template1Query5.setParameter(1, applicant);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
            EmailTemplateQuery template1Query6 = templateVender
              .getTemplateQuery(6);
          }
          catch (Exception e)
          {
            EmailTemplateQuery template1Query6;
            e.printStackTrace();
          }
          try {
            EmailTemplateQuery template1Query7 = templateVender
              .getTemplateQuery(7);
            template1Query7.setParameter(1, applnID);
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
            EmailTemplateQuery template1Query8 = templateVender
              .getTemplateQuery(8);
            template1Query8.setParameter(1, applnID);
          } catch (Exception e) {
            e.printStackTrace();
          }
          templateVender.configMailAlert();
          templateVender.sendApplicationMail();
          templateVender.clearParameters();
          templateVender.terminate();
        }
      }

      model.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}