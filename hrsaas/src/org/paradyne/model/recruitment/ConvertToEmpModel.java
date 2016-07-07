package org.paradyne.model.recruitment;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.Recruitment.ConvertToEmp;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;

public class ConvertToEmpModel extends ModelBase
{
  static Logger logger = Logger.getLogger(ConvertToEmpModel.class);

  public void getRecords(ConvertToEmp bean, HttpServletRequest request)
  {
    logger.info("bean.getMy Page" + bean.getMyPage());
    try
    {
      String query = "SELECT APPOINT_REQS_CODE, REQS_NAME, APPOINT_CAND_CODE, " +
      		"CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME, " +
      		"REQS_POSITION, RANK_NAME,APPOINT_EMP_TYPE,TYPE_NAME, " +
      		"REQS_HIRING_MANAGER, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, CAND_RESUME, " +
      		"APPOINT_CODE,APPOINT_DIVISION,APPOINT_BRANCH,APPOINT_DEPT " +
      		"FROM HRMS_REC_APPOINT " + 
      		"INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_APPOINT.APPOINT_REQS_CODE) " +
      		"INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_APPOINT.APPOINT_CAND_CODE) " +
      		"INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) " +
      		"INNER JOIN HRMS_EMP_TYPE ON (HRMS_REC_APPOINT.APPOINT_EMP_TYPE = HRMS_EMP_TYPE.TYPE_ID) " +
      		"INNER JOIN HRMS_RANK ON (HRMS_REC_APPOINT.APPOINT_POSITION = HRMS_RANK.RANK_ID) " +
      		"INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_REC_APPOINT.APPOINT_DIVISION) " +
      		"INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_REC_APPOINT.APPOINT_BRANCH) " +
      		"INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_REC_APPOINT.APPOINT_DEPT) " +
      		"WHERE APPOINT_STATUS = 'OA' and APPOINT_CONVERT_EMP='N' AND HRMS_REC_REQS_HDR.REQS_STATUS NOT IN ('C') ORDER BY  REQS_DIVISION";

      Object[][] data = getSqlModel().getSingleResult(query);
      if ((data != null) && (data.length > 0))
        bean.setModeLength("true");
      else {
        bean.setModeLength("false");
      }
      ArrayList reqList = new ArrayList();
      String[] pageIndex = Utility.doPaging(bean.getMyPage(), data.length, 20);
      if (pageIndex == null) {
        pageIndex[0] = "0";
        pageIndex[1] = "20";
        pageIndex[2] = "1";
        pageIndex[3] = "1";
        pageIndex[4] = "";
      }

      request.setAttribute("totalPage", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[2]))));
      request.setAttribute("PageNo", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[3]))));
      if (pageIndex[4].equals("1")) {
        bean.setMyPage("1");
      }

      if ((data != null) && (data.length > 0)) {
        for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
          ConvertToEmp convtEmp = new ConvertToEmp();
          convtEmp.setReqsCode(String.valueOf(data[i][0]));
          convtEmp.setReqsName(checkNull(String.valueOf(data[i][1])));
          convtEmp.setCandidateCode(String.valueOf(data[i][2]));
          convtEmp.setCandidateName(checkNull(String.valueOf(data[i][3])));
          convtEmp.setPosition(checkNull(String.valueOf(data[i][5])));
          convtEmp.setEmpType(checkNull(String.valueOf(data[i][7])));
          convtEmp.setHireMan(checkNull(String.valueOf(data[i][9])));
          convtEmp.setCandResume(checkNull(String.valueOf(data[i][10])));
          convtEmp.setAppointmentCodeItr(checkNull(String.valueOf(data[i][11])));
          convtEmp.setIttrdivisionCode(checkNull(String.valueOf(data[i][12])));
          convtEmp.setIttrdesgnCode(checkNull(String.valueOf(data[i][4])));
          convtEmp.setIttrbranchCode(checkNull(String.valueOf(data[i][13])));
          convtEmp.setIttrdeptCode(checkNull(String.valueOf(data[i][14])));
          reqList.add(convtEmp);
        }
        bean.setRadioChk("true");
        bean.setList(reqList);
        bean.setTotalRecords(String.valueOf(data.length));
      } else {
        bean.setData("true");
        bean.setTotalRecords(String.valueOf("0"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean convertEmp(String requisitionCode, String candidateCode, ConvertToEmp convertToEmp)
  {
    boolean result = false;
    String query = "select CAND_FIRST_NAME,CAND_MID_NAME,CAND_LAST_NAME,to_char(CAND_DOB,'dd-mm-yyyy'),CAND_PHOTO,CAND_GENDER, APPOINT_EMP_TYPE, APPOINT_GRADE,APPOINT_REPORT_TO,to_char(APPOINT_EXP_JOINDATE,'dd-mm-yyyy'),CAND_MART_STATUS,CAND_PASSPORT_NO,CAND_PAN_NO,CAND_OFF_PHONE,CAND_RES_PHONE, CAND_MOB_PHONE,CAND_EMAIL_ID  from HRMS_REC_CAND_DATABANK  left join  HRMS_REC_APPOINT on(HRMS_REC_APPOINT.APPOINT_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE)  where CAND_CODE=" + 
      candidateCode;

    String offcInsert = "insert into hrms_emp_offc(EMP_ID,EMP_FNAME, EMP_MNAME, EMP_LNAME, EMP_DOB, EMP_PHOTO,EMP_GENDER,EMP_TYPE,EMP_CADRE,EMP_REPORTING_OFFICER,EMP_REGULAR_DATE,EMP_STATUS,EMP_IND_STATUS,EMP_CENTER, EMP_RANK, EMP_DEPT, EMP_DIV) values((SELECT NVL(MAX(EMP_ID),0)+1 FROM HRMS_EMP_OFFC),?,?,?,to_date(?,'DD-MM-YYYY'),?,?,?,?,?,to_date(?,'DD-MM-YYYY'),'S','N'," + 
      convertToEmp.getBranchCode() + "," + convertToEmp.getDesgCode() + "," + 
      convertToEmp.getDeptCode() + "," + convertToEmp.getDivisionCode() + ")";
    try
    {
      Object[][] data = getSqlModel().getSingleResult(query);
      logger.info("grade " + String.valueOf(data[0][7]));
      Object[][] addObj = new Object[1][10];
      if ((data != null) && (data.length > 0)) {
        addObj[0][0] = checkNull(String.valueOf(data[0][0]));
        addObj[0][1] = checkNull(String.valueOf(data[0][1]));
        addObj[0][2] = checkNull(String.valueOf(data[0][2]));
        addObj[0][3] = checkNull(String.valueOf(data[0][3]));
        addObj[0][4] = checkNull(String.valueOf(data[0][4]));
        addObj[0][5] = checkNull(String.valueOf(data[0][5]));
        addObj[0][6] = checkNull(String.valueOf(data[0][6]));
        addObj[0][7] = checkNull(String.valueOf(data[0][7]));
        addObj[0][8] = checkNull(String.valueOf(data[0][8]));
        addObj[0][9] = checkNull(String.valueOf(data[0][9]));
      }

      result = getSqlModel().singleExecute(offcInsert, addObj);

      String empIdQuery = "SELECT MAX(EMP_ID) FROM HRMS_EMP_OFFC";
      Object[][] empData = getSqlModel().getSingleResult(empIdQuery);
      convertToEmp.setEmpId(String.valueOf(empData[0][0]));

      ApplCodeTemplateModel tempModel = new ApplCodeTemplateModel();
      tempModel.initiate(this.context, this.session);
      String newToken = "";
      Date date = new Date();
      SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
      String sysDate = formater.format(date);
      newToken = tempModel.generateApplicationCode("", "EmpId", convertToEmp.getEmpId(), sysDate);

      String updateAutoToken = "UPDATE HRMS_EMP_OFFC SET EMP_TOKEN = '" + newToken + "' WHERE EMP_ID = " + convertToEmp.getEmpId();
      getSqlModel().singleExecute(updateAutoToken);

      if ((!String.valueOf(data[0][12]).equals("null")) && (!String.valueOf(data[0][12]).equals("")) && (!String.valueOf(data[0][12]).equals(" ")))
      {
        Object[][] addSal = new Object[1][2];
        addSal[0][0] = convertToEmp.getEmpId();
        addSal[0][1] = String.valueOf(data[0][12]);
        String addSalQuery = "INSERT INTO HRMS_SALARY_MISC(EMP_ID,SAL_PANNO) values (?,?)";
        getSqlModel().singleExecute(addSalQuery, addSal);
      }

      Object[][] persDet = new Object[1][3];
      persDet[0][0] = convertToEmp.getEmpId();
      if (String.valueOf(data[0][10]).equals("S"))
        persDet[0][1] = String.valueOf("U");
      else
        persDet[0][1] = String.valueOf(data[0][10]);
      persDet[0][2] = String.valueOf(data[0][11]);

      String addPersQuery = "INSERT INTO HRMS_EMP_PERS(EMP_ID,EMP_MARITAL_STATUS,EMP_PASSPORT) VALUES (?,?,?)";
      getSqlModel().singleExecute(addPersQuery, persDet);

      String currentAddrs = "SELECT NVL(HRMS_LOCATION.LOCATION_NAME,' '),NVL(state.LOCATION_NAME,' '),NVL(country.LOCATION_NAME,' ') ,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE,  HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY,NVL(HRMS_REC_CD_ADDRESSDTL.CAND_ADD,' '),  HRMS_REC_CD_ADDRESSDTL.CAND_ADD_PINCODE FROM HRMS_LOCATION  LEFT JOIN   HRMS_REC_CD_ADDRESSDTL ON(HRMS_LOCATION.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY) LEFT JOIN HRMS_LOCATION state  on(state.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE) LEFT JOIN HRMS_LOCATION country   on(country.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY) where HRMS_REC_CD_ADDRESSDTL.CAND_CODE=" + 
        candidateCode + " and " + 
        "  HRMS_REC_CD_ADDRESSDTL.CAND_ADD_TYPE='C' ";
      Object[][] currentAddrsData = getSqlModel().getSingleResult(currentAddrs);

      if ((currentAddrsData != null) && (currentAddrsData.length > 0)) {
        Object[][] currentAddress = new Object[1][6];
        currentAddress[0][0] = convertToEmp.getEmpId();
        currentAddress[0][1] = String.valueOf("L");
        currentAddress[0][2] = checkNull(String.valueOf(currentAddrsData[0][3]));
        currentAddress[0][3] = checkNull(String.valueOf(currentAddrsData[0][1]));
        currentAddress[0][4] = checkNull(String.valueOf(currentAddrsData[0][2]));
        currentAddress[0][5] = checkNull(String.valueOf(currentAddrsData[0][7]));
        for (int i = 0; i < currentAddress.length; i++) {
          for (int j = 0; j < currentAddress[0].length; j++) {
            System.out.println("currentAddress[0][" + j + "] >>" + currentAddress[0][j]);
          }
        }
        String currAddress = "INSERT INTO HRMS_EMP_ADDRESS(EMP_ID,ADD_TYPE,ADD_CITY,ADD_STATE,ADD_CNTRY,ADD_PINCODE) VALUES (?,?,?,?,?,?)";
        getSqlModel().singleExecute(currAddress, currentAddress);
      }

      String permanentAddrs = "SELECT NVL(HRMS_LOCATION.LOCATION_NAME,' '),NVL(state.LOCATION_NAME,' '),NVL(country.LOCATION_NAME,' ') ,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE,  HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY,NVL(HRMS_REC_CD_ADDRESSDTL.CAND_ADD,' '),  HRMS_REC_CD_ADDRESSDTL.CAND_ADD_PINCODE FROM HRMS_LOCATION  LEFT JOIN   HRMS_REC_CD_ADDRESSDTL ON(HRMS_LOCATION.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY) LEFT JOIN HRMS_LOCATION state  on(state.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE) LEFT JOIN HRMS_LOCATION country   on(country.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY) where HRMS_REC_CD_ADDRESSDTL.CAND_CODE=" + 
        candidateCode + " and " + 
        "  HRMS_REC_CD_ADDRESSDTL.CAND_ADD_TYPE='P' ";
      Object[][] permanentddrsData = getSqlModel().getSingleResult(permanentAddrs);

      if ((permanentddrsData != null) && (permanentddrsData.length > 0)) {
        Object[][] permAddrs = new Object[1][10];
        permAddrs[0][0] = convertToEmp.getEmpId();
        permAddrs[0][1] = String.valueOf("P");
        permAddrs[0][2] = checkNull(String.valueOf(permanentddrsData[0][3]));
        permAddrs[0][3] = checkNull(String.valueOf(permanentddrsData[0][1]));
        permAddrs[0][4] = checkNull(String.valueOf(permanentddrsData[0][2]));
        permAddrs[0][5] = checkNull(String.valueOf(data[0][13]));
        permAddrs[0][6] = checkNull(String.valueOf(data[0][14]));
        permAddrs[0][7] = checkNull(String.valueOf(data[0][15]));
        permAddrs[0][8] = checkNull(String.valueOf(data[0][16]));
        permAddrs[0][9] = checkNull(String.valueOf(permanentddrsData[0][7]));
        for (int i = 0; i < permAddrs.length; i++) {
          for (int j = 0; j < permAddrs[0].length; j++) {
            System.out.println("permAddrs[0][" + j + "] >>" + permAddrs[0][j]);
          }
        }
        String permAddress = "INSERT INTO HRMS_EMP_ADDRESS(EMP_ID,ADD_TYPE,ADD_CITY,ADD_STATE,ADD_CNTRY,ADD_PH1,ADD_PH2,ADD_MOBILE,ADD_EMAIL,ADD_PINCODE) VALUES (?,?,?,?,?,?,?,?,?,?)";
        getSqlModel().singleExecute(permAddress, permAddrs);
      }

    }
    catch (Exception e)
    {
      logger.error("--------", e);
    }
    String selectQual = "SELECT CAND_QUA_CODE, CAND_ESTB_NAME,CAND_PERC_MARKS  from HRMS_REC_CD_QUADTL where CAND_CODE=" + 
      candidateCode;
    Object[][] dataQual = getSqlModel().getSingleResult(selectQual);
    if ((dataQual != null) && (dataQual.length > 0)) {
      for (int i = 0; i < dataQual.length; i++) {
        Object[][] addQual = new Object[1][4];
        addQual[0][0] = dataQual[i][0];
        addQual[0][1] = dataQual[i][1];
        addQual[0][2] = dataQual[i][2];
        addQual[0][3] = convertToEmp.getEmpId();
        String addQualQuery = "insert into HRMS_EMP_QUA (QUA_ID,QUA_MAST_CODE, QUA_UNIV, QUA_PER,EMP_ID) values  ((SELECT NVL(MAX(QUA_ID),0)+1 FROM HRMS_EMP_QUA),?,?,?,?)";

        result = getSqlModel().singleExecute(addQualQuery, addQual);
      }

    }

    String selectExp = "SELECT CAND_EMPLOYER_NAME, CAND_EMP_PORF, CAND_JOB_DESC,TO_CHAR(CAND_JOIN_DATE,'DD-MM-YYYY'), TO_CHAR(CAND_RELV_DATE,'DD-MM-YYYY'),  CAND_CTC from HRMS_REC_CD_EXPDTL where CAND_CODE=" + 
      candidateCode;
    Object[][] dataExp = getSqlModel().getSingleResult(selectExp);
    if ((dataExp != null) && (dataExp.length > 0)) {
      String addExpQuery = "insert into HRMS_EMP_EXP (EXP_ID,EXP_EMPLOYER, EXP_JOBTITLE, EXP_JOBDESC, EXP_JOINING_DATE, EXP_RELIEVING_DATE, EXP_SCALE_OF_PAY,EMP_ID) values  ((SELECT NVL(MAX(EXP_ID),0)+1 FROM HRMS_EMP_EXP),?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?," + 
        convertToEmp.getEmpId() + ")";
      result = getSqlModel().singleExecute(addExpQuery, dataExp);
    }
    try
    {
      String salaryQuery = "select OFFER_CREDIT_CODE,OFFER_CREDIT_AMOUNT from HRMS_REC_OFFER_SALARY  where OFFER_REQS_CODE=" + 
        requisitionCode + " and OFFER_CAND_CODE=" + candidateCode;

      Object[][] salaryData = getSqlModel().getSingleResult(salaryQuery);

      if ((salaryData != null) && (salaryData.length > 0)) {
        String salaryInsert = "INSERT INTO HRMS_EMP_CREDIT VALUES (" + convertToEmp.getEmpId() + ",?,?,'Y',0)";
        result = getSqlModel().singleExecute(salaryInsert, salaryData);
      }
    }
    catch (Exception e) {
      logger.error("exception in salary insert", e);
    }
    try
    {
      logger.info("emp id is" + convertToEmp.getEmpId());
      String updateAppt = "update HRMS_REC_APPOINT set APPOINT_CONVERT_EMP='Y',APPOINT_CONVERT_EMPID=" + convertToEmp.getEmpId() + " where APPOINT_REQS_CODE=" + requisitionCode + " and " + 
        "APPOINT_CAND_CODE=" + candidateCode;

      getSqlModel().singleExecute(updateAppt);
    }
    catch (Exception e) {
      logger.error("--------", e);
    }

    String pubCodeIn = "";
    String recQuery = "select RESUME_REC_EMPID from HRMS_REC_RESUME_BANK  where RESUME_REQS_CODE=" + requisitionCode + " and " + 
      "RESUME_CAND_CODE=" + candidateCode;
    Object[][] recEmpId = getSqlModel().getSingleResult(recQuery);
    if ((recEmpId != null) && (recEmpId.length > 0)) {
      String pubCodeQuery = "select PUB_CODE,PUB_VACAN_DTLCODE from HRMS_REC_VACPUB_HDR where PUB_REQS_CODE=" + requisitionCode + " " + 
        " order by PUB_DATE ASC";
      Object[][] pubCode = getSqlModel().getSingleResult(pubCodeQuery);
      if ((pubCode != null) && (pubCode.length > 0)) {
        for (int i = 0; i < pubCode.length; i++) {
          pubCodeIn = pubCodeIn + pubCode[i][0] + ",";
        }
        pubCodeIn = pubCodeIn.substring(0, pubCodeIn.length() - 1);

        String vacnaQuery = "select PUB_DTL_CODE,nvl(PUB_ASG_VAC,0),nvl(PUB_CLOSE_VACAN,0) from  HRMS_REC_VACPUB_RECDTL INNER JOIN HRMS_REC_VACPUB_HDR ON(HRMS_REC_VACPUB_HDR.PUB_CODE=HRMS_REC_VACPUB_RECDTL.PUB_CODE)  where HRMS_REC_VACPUB_RECDTL.PUB_CODE IN(" + 
          pubCodeIn + ") and PUB_VAC_STATUS='O' AND PUB_REC_EMPID=" + String.valueOf(recEmpId[0][0]);

        Object[][] vacanDet = getSqlModel().getSingleResult(vacnaQuery);
        if ((vacanDet != null) && (vacanDet.length > 0))
        {
          int i = 0; if (i < vacanDet.length) {
            int closeVac = Integer.parseInt(String.valueOf(vacanDet[i][2])) + 1;
            if (i == vacanDet.length - 1)
            {
              String updateVacan = "update HRMS_REC_VACPUB_RECDTL set PUB_CLOSE_VACAN = " + (Integer.parseInt(String.valueOf(vacanDet[i][2])) + 1) + 
                " where PUB_DTL_CODE=" + String.valueOf(vacanDet[i][0]);
              result = getSqlModel().singleExecute(updateVacan);

              if (closeVac >= Integer.parseInt(String.valueOf(vacanDet[i][1]))) {
                String updateVacStatus = "update HRMS_REC_VACPUB_RECDTL set PUB_VAC_STATUS = 'C', PUB_VAC_CLOSEDATE = TO_DATE(( SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL),'DD-MM-YYYY') where PUB_DTL_CODE=" + 
                  String.valueOf(vacanDet[i][0]) + " AND PUB_VAC_CLOSEDATE IS NULL AND PUB_VAC_STATUS != 'C'";
                result = getSqlModel().singleExecute(updateVacStatus);
              }

            }
            else if (Integer.parseInt(String.valueOf(vacanDet[i][1])) > Integer.parseInt(String.valueOf(vacanDet[i][2]))) {
              String updateVacan = "update HRMS_REC_VACPUB_RECDTL set PUB_CLOSE_VACAN = " + (Integer.parseInt(String.valueOf(vacanDet[i][2])) + 1) + 
                " where PUB_DTL_CODE=" + String.valueOf(vacanDet[i][0]);
              result = getSqlModel().singleExecute(updateVacan);

              if (closeVac >= Integer.parseInt(String.valueOf(vacanDet[i][1]))) {
                String updateVacStatus = "update HRMS_REC_VACPUB_RECDTL set PUB_VAC_STATUS = 'C', PUB_VAC_CLOSEDATE = TO_DATE(( SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL),'DD-MM-YYYY') where PUB_DTL_CODE=" + 
                  String.valueOf(vacanDet[i][0]) + " AND PUB_VAC_CLOSEDATE IS NULL AND PUB_VAC_STATUS != 'C'";
                result = getSqlModel().singleExecute(updateVacStatus);
              }
            }

          }

        }

        for (int i = 0; i < pubCode.length; i++) {
          logger.info("pubCode.length======>>" + pubCode.length);
          String vacPubStatusQuery = "SELECT PUB_VAC_STATUS FROM HRMS_REC_VACPUB_RECDTL WHERE PUB_CODE = " + pubCode[i][0];
          Object[][] vacPubStatus = getSqlModel().getSingleResult(vacPubStatusQuery);

          if ((vacPubStatus != null) && (vacPubStatus.length > 0)) {
            logger.info("111111======>>");
            int countStatus = 0;
            for (int j = 0; j < vacPubStatus.length; j++) {
              if (String.valueOf(vacPubStatus[j][0]).equals("O")) {
                countStatus++;
              }
            }

            if (countStatus == 0) {
              logger.info("2222======>>");
              logger.info("pubCode[i][1]======>>" + pubCode[i][1]);
              String updateVacStatus = "UPDATE HRMS_REC_REQS_VACDTL SET VACAN_STATUS = 'C',VACAN_CLOSE_DATE = TO_DATE(SYSDATE,'DD-MM-YYYY')  WHERE VACAN_DTL_CODE = " + 
                pubCode[i][1] + " AND VACAN_CLOSE_DATE IS NULL AND VACAN_STATUS != 'C'";
              getSqlModel().singleExecute(updateVacStatus);
            }
          }
        }
      }
    }

    closeRequisition(requisitionCode, candidateCode);
    System.out.println("######## result ######## >>>>>" + result);
    return result;
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public void getRec(String reqCode, ConvertToEmp convertToEmp, String candCode)
  {
    String query = "SELECT APPOINT_DIVISION,DIV_NAME,APPOINT_BRANCH,CENTER_NAME,APPOINT_DEPT,DEPT_NAME,APPOINT_POSITION,  RANK_NAME   FROM HRMS_REC_APPOINT  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_APPOINT.APPOINT_POSITION)  INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_REC_APPOINT.APPOINT_DIVISION)  INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_REC_APPOINT.APPOINT_BRANCH)  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_REC_APPOINT.APPOINT_DEPT)  WHERE APPOINT_REQS_CODE = " + 
      reqCode + " AND APPOINT_CAND_CODE = " + candCode;

    Object[][] data = getSqlModel().getSingleResult(query);
    if ((data != null) && (data.length > 0)) {
      convertToEmp.setDivisionCode(String.valueOf(data[0][0]));
      convertToEmp.setDivisionName(String.valueOf(data[0][1]));
      convertToEmp.setBranchCode(String.valueOf(data[0][2]));
      convertToEmp.setBranchName(String.valueOf(data[0][3]));
      convertToEmp.setDeptCode(String.valueOf(data[0][4]));
      convertToEmp.setDeptName(String.valueOf(data[0][5]));
      convertToEmp.setDesgCode(String.valueOf(data[0][6]));
      convertToEmp.setDesgName(String.valueOf(data[0][7]));
    }
  }

  public void getEmployeeData(ConvertToEmp convertToEmp, HttpServletRequest request)
  {
    try
    {
      String query = "SELECT DISTINCT NVL(EMP_TOKEN,' '),EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,CENTER_NAME,DEPT_NAME,RANK_NAME,DIV_NAME, TYPE_NAME,REQS_NAME FROM HRMS_EMP_OFFC  INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) INNER JOIN HRMS_REC_APPOINT ON (HRMS_REC_APPOINT.APPOINT_CONVERT_EMPID=HRMS_EMP_OFFC.EMP_ID)  INNER JOIN HRMS_REC_REQS_HDR  on ( HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_APPOINT.APPOINT_REQS_CODE)  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_OFFC.EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)  WHERE HRMS_REC_APPOINT.APPOINT_CONVERT_EMP='Y' ";

      Object[][] data = getSqlModel().getSingleResult(query);
      if ((data != null) && (data.length > 0))
      {
        convertToEmp.setModeLength("true");
      }
      else {
        convertToEmp.setModeLength("false");
      }

      ArrayList list = new ArrayList();
      String[] pageIndex = Utility.doPaging(convertToEmp.getMyPage1(), data.length, 20);
      if (pageIndex == null) {
        pageIndex[0] = "0";
        pageIndex[1] = "20";
        pageIndex[2] = "1";
        pageIndex[3] = "1";
        pageIndex[4] = "";
      }

      request.setAttribute("totalPage1", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[2]))));
      request.setAttribute("PageNo1", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[3]))));
      if (pageIndex[4].equals("1")) {
        convertToEmp.setMyPage1("1");
      }
      if ((data != null) && (data.length > 0)) {
        for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++)
        {
          ConvertToEmp convtEmp = new ConvertToEmp();
          convtEmp.setEmpTok(String.valueOf(data[i][0]));
          convtEmp.setEmpName(String.valueOf(data[i][1]));
          convtEmp.setEmpBrn(String.valueOf(data[i][2]));
          convtEmp.setEmpDept(String.valueOf(data[i][3]));
          convtEmp.setEmpDesg(String.valueOf(data[i][4]));
          convtEmp.setEmpDiv(String.valueOf(data[i][5]));
          convtEmp.setEmpTyp(String.valueOf(data[i][6]));
          convtEmp.setReqsName(String.valueOf(data[i][7]));
          list.add(convtEmp);
        }
        convertToEmp.setTotalRecords(String.valueOf(data.length));
      }
      else {
        convertToEmp.setData("true");
        convertToEmp.setTotalRecords(String.valueOf(data.length));
      }

      convertToEmp.setEmpList(list);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void closeRequisition(String reqCode, String candCode)
  {
    try
    {
      int assignedVac = 0;
      int closedVac = 0;
      String totVacQuery = "SELECT SUM(NVL(VACAN_NUMBERS,0)) FROM HRMS_REC_REQS_VACDTL WHERE REQS_CODE=" + reqCode;
      Object[][] totVac = getSqlModel().getSingleResult(totVacQuery);
      int vacTot = Integer.parseInt(String.valueOf(totVac[0][0]));
      String pubCodeIn = "";

      String pubCodeQuery = "select PUB_CODE from HRMS_REC_VACPUB_HDR where PUB_REQS_CODE=" + reqCode + " " + 
        " order by PUB_DATE ASC";
      Object[][] pubCode = getSqlModel().getSingleResult(pubCodeQuery);
      if ((pubCode != null) && (pubCode.length > 0)) {
        for (int i = 0; i < pubCode.length; i++) {
          pubCodeIn = pubCodeIn + pubCode[i][0] + ",";
        }
        pubCodeIn = pubCodeIn.substring(0, pubCodeIn.length() - 1);
      }

      String vacnaQuery = "select PUB_DTL_CODE,nvl(PUB_ASG_VAC,0),nvl(PUB_CLOSE_VACAN,0) from  HRMS_REC_VACPUB_RECDTL INNER JOIN HRMS_REC_VACPUB_HDR ON(HRMS_REC_VACPUB_HDR.PUB_CODE=HRMS_REC_VACPUB_RECDTL.PUB_CODE)  where HRMS_REC_VACPUB_RECDTL.PUB_CODE IN(" + 
        pubCodeIn + ") AND PUB_VAC_STATUS='C' ";
      Object[][] vacanDet = getSqlModel().getSingleResult(vacnaQuery);

      if ((vacanDet != null) && (vacanDet.length > 0)) {
        for (int i = 0; i < vacanDet.length; i++) {
          assignedVac += Integer.parseInt(String.valueOf(vacanDet[i][1]));
          closedVac += Integer.parseInt(String.valueOf(vacanDet[i][2]));
        }

        if (vacTot == closedVac) {
          String updateQuery = "update HRMS_REC_REQS_HDR set REQS_STATUS ='C', REQS_CLOSE_DATE=TO_DATE(SYSDATE,'DD-MM-YYYY')  where REQS_CODE=" + 
            reqCode;
          getSqlModel().singleExecute(updateQuery);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void getSearch(ConvertToEmp convertEmp, String reqname, String position, String candname, String emp, HttpServletRequest request)
  {
    try
    {
      int count = 0;
      String concatStr = "";
      String query = "SELECT APPOINT_REQS_CODE, REQS_NAME, APPOINT_CAND_CODE, CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME, REQS_POSITION, RANK_NAME,APPOINT_EMP_TYPE,TYPE_NAME, REQS_HIRING_MANAGER, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, CAND_RESUME FROM HRMS_REC_APPOINT INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_APPOINT.APPOINT_REQS_CODE) INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_APPOINT.APPOINT_CAND_CODE) INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)  INNER JOIN HRMS_EMP_TYPE ON (HRMS_REC_APPOINT.APPOINT_EMP_TYPE = HRMS_EMP_TYPE.TYPE_ID)INNER JOIN HRMS_RANK ON (HRMS_REC_APPOINT.APPOINT_POSITION = HRMS_RANK.RANK_ID) WHERE APPOINT_STATUS = 'OA' and APPOINT_CONVERT_EMP='N'";

      if (convertEmp.getApplyFilterFlag().equals("true")) {
        if (!convertEmp.getRequisitionId().equals("")) {
          query = query + " AND APPOINT_REQS_CODE=" + convertEmp.getRequisitionId();
          concatStr = concatStr + reqname + " :" + convertEmp.getRequisitionName() + ",";
        }

        if (!convertEmp.getPositionId().equals("")) {
          query = query + " AND REQS_POSITION =" + convertEmp.getPositionId();
          concatStr = concatStr + position + " :" + convertEmp.getPositionName() + ",";
        }

        if (!convertEmp.getCandCode1().equals("")) {
          query = query + " AND APPOINT_CAND_CODE =" + convertEmp.getCandCode1();
          concatStr = concatStr + candname + " :" + convertEmp.getCandidateName1() + ",";
        }

        if (!convertEmp.getEmpTypeId().equals("")) {
          query = query + " AND HRMS_REC_APPOINT.APPOINT_EMP_TYPE =" + convertEmp.getEmpTypeId();
          concatStr = concatStr + emp + " :" + convertEmp.getEmployeeName() + ",";
        }
      }
      else
      {
        convertEmp.setApplyFilterFlag("false");
      }
      Object[][] data = getSqlModel().getSingleResult(query);

      if ((data != null) && (data.length > 0))
      {
        convertEmp.setModeLength("true");
      }
      else {
        convertEmp.setModeLength("false");
      }

      ArrayList reqList = new ArrayList();

      String[] pageIndex = Utility.doPaging(convertEmp.getMyPage(), data.length, 20);
      if (pageIndex == null) {
        pageIndex[0] = "0";
        pageIndex[1] = "20";
        pageIndex[2] = "1";
        pageIndex[3] = "1";
        pageIndex[4] = "";
      }
      logger.info("=============String.valueOf(pageIndex[2]+++++" + Integer.parseInt(String.valueOf(pageIndex[2])));
      request.setAttribute("totalPage", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[2]))));
      request.setAttribute("PageNo", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[3]))));
      if (pageIndex[4].equals("1")) {
        convertEmp.setMyPage("1");
      }
      if ((data != null) && (data.length > 0))
      {
        for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
          ConvertToEmp convtEmp = new ConvertToEmp();
          convtEmp.setReqsCode(String.valueOf(data[i][0]));
          convtEmp.setReqsName(checkNull(String.valueOf(data[i][1])));
          convtEmp.setCandidateCode(String.valueOf(data[i][2]));
          convtEmp.setCandidateName(checkNull(String.valueOf(data[i][3])));
          convtEmp.setPosition(checkNull(String.valueOf(data[i][5])));
          convtEmp.setEmpType(checkNull(String.valueOf(data[i][7])));
          convtEmp.setHireMan(checkNull(String.valueOf(data[i][9])));
          convtEmp.setCandResume(checkNull(String.valueOf(data[i][10])));

          reqList.add(convtEmp);
        }

        convertEmp.setList(reqList);
        convertEmp.setTotalRecords(String.valueOf(data.length));
      }
      else {
        convertEmp.setData("true");
        convertEmp.setTotalRecords(String.valueOf("0"));
      }

      String[] dispArr = concatStr.split(",");
      request.setAttribute("dispArr", dispArr);
    }
    catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }
}