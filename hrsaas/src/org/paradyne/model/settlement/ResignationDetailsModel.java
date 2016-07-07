package org.paradyne.model.settlement;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.settlement.ResignationDetails;
import org.paradyne.lib.DateUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class ResignationDetailsModel extends ModelBase
{
  static Logger logger = Logger.getLogger(ModelBase.class);

  public String save(ResignationDetails bean)
  {
    String sameResgQue = "SELECT TO_CHAR(RESIGN_DATE,'DD-MM-YYYY') FROM HRMS_RESIGN WHERE RESIGN_EMP=" + 
      bean.getEmpCode();
    Object[][] sameResgObj = getSqlModel().getSingleResult(sameResgQue);
    if (sameResgObj.length > 0) {
      for (int i = 0; i < sameResgObj.length; i++) {
        int days = Integer.parseInt(String.valueOf(sameResgObj[i][0])
          .substring(0, 2));
        int month = Integer.parseInt(String.valueOf(sameResgObj[i][0])
          .substring(3, 5));
        int year = Integer.parseInt(String.valueOf(sameResgObj[i][0])
          .substring(6, 10));
        int resgDays = Integer.parseInt(String.valueOf(
          bean.getResignDate()).substring(0, 2));
        int resgMonth = Integer.parseInt(String.valueOf(
          bean.getResignDate()).substring(3, 5));
        int resgYear = Integer.parseInt(String.valueOf(
          bean.getResignDate()).substring(6, 10));

        if ((year == resgYear) && (month == resgMonth) && 
          (days == resgDays)) {
          return "sameResg";
        }
      }

    }

    try
    {
      String period = bean.getWaveOffPeriod();
      logger.info("getWaveOffPeriod  : " + bean.getWaveOffPeriod());
      if (period.equals(""))
        period = "0";
      int totalDays = getTotalDays(bean, bean.getNoticePeriodActual(), 
        period, bean.getNoticeperiodselect());
      logger.info("totalDays in insert  : " + totalDays);
      bean.setNoticePeriod(String.valueOf(totalDays));
    } catch (Exception e) {
      logger.error("Error in setting notice period : " + e);
    }
    Object[][] addObj = new Object[1][15];

    addObj[0][0] = bean.getEmpCode();
    addObj[0][1] = bean.getNoticePeriod();
    addObj[0][2] = bean.getStatus().trim();
    addObj[0][3] = bean.getResignDate();
    addObj[0][4] = bean.getAcceptDate();
    addObj[0][5] = bean.getSepdate();
    addObj[0][6] = bean.getReason();
    addObj[0][7] = bean.getRemark();

    if (bean.getWithDrawn().equals("true"))
      addObj[0][8] = "Y";
    else {
      addObj[0][8] = "N";
    }
    addObj[0][9] = bean.getNoticeperiodselect();
    addObj[0][10] = bean.getApprCode();

    addObj[0][11] = bean.getNoticePeriodActual();
    addObj[0][12] = bean.getAccByCode();
    addObj[0][13] = checkNull(bean.getHrComments());
    addObj[0][14] = "Y";

    boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
    if (result) {
      return "saved";
    }
    return "notSaved";
  }

  public int getTotalDays(ResignationDetails bean, String actualPeriod, String period, String stats)
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

      System.out.println("if--totalDays----" + totalDays);
      totalDays -= Integer.parseInt(period);
      System.out.println("if11--totalDays----" + totalDays);
    } else {
      totalDays = Integer.parseInt(actualPeriod) - 
        Integer.parseInt(period);
      System.out.println("else--totalDays----" + totalDays);
    }
    return totalDays;
  }

  public ResignationDetails getRecord(ResignationDetails bean)
  {
    Object[] addObj = new Object[1];

    addObj[0] = bean.getEmpCode();

    Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);

    for (int i = 0; i < data.length; i++)
    {
      bean.setEmployeeToken(String.valueOf(data[i][0]).trim());
      bean.setEmpName(checkNull(String.valueOf(data[i][1])).trim());
      bean.setDesignation(checkNull(String.valueOf(data[i][2])).trim());

      bean.setBranch(checkNull(String.valueOf(data[i][3])).trim());
      bean.setDateofjoin(checkNull(String.valueOf(data[i][4])).trim());
    }
    return bean;
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }
    return result;
  }

  public boolean delete(ResignationDetails bean)
  {
    Object[][] delObj = new Object[1][1];

    delObj[0][0] = bean.getRegCode();

    return getSqlModel().singleExecute(getQuery(3), delObj);
  }

  public boolean update(ResignationDetails bean)
  {
    try
    {
      String period = bean.getWaveOffPeriod();
      logger.info("getWaveOffPeriod  : " + bean.getWaveOffPeriod());
      if (period.equals(""))
        period = "0";
      int totalDays = getTotalDays(bean, bean.getNoticePeriodActual(), 
        period, bean.getNoticeperiodselect());
      logger.info("totalDays in update  : " + totalDays);
      bean.setNoticePeriod(String.valueOf(totalDays));
    } catch (Exception e) {
      logger.error("Error in setting notice period : " + e);
    }

    Object[][] modObj = new Object[1][16];

    modObj[0][0] = bean.getEmpCode();
    modObj[0][1] = bean.getNoticePeriod();
    modObj[0][2] = bean.getStatus();
    modObj[0][3] = bean.getResignDate();
    modObj[0][4] = bean.getAcceptDate();
    modObj[0][5] = bean.getSepdate();
    modObj[0][6] = bean.getReason();
    modObj[0][7] = bean.getRemark();
    modObj[0][8] = 
      String.valueOf(bean.getWithDrawn().equals("true") ? "Y" : 
      "N");

    modObj[0][9] = bean.getNoticeperiodselect();
    modObj[0][10] = checkNull(bean.getApprCode());
    modObj[0][11] = bean.getNoticePeriodActual();
    modObj[0][12] = checkNull(bean.getAccByCode());
    modObj[0][13] = checkNull(bean.getHrComments());
    modObj[0][14] = "Y";
    modObj[0][15] = bean.getRegCode();

    return getSqlModel().singleExecute(getQuery(2), modObj);
  }

  public void getReport(HttpServletRequest request, HttpServletResponse response, ResignationDetails bean)
  {
    String s = "\n Resignation Details Report\n\n";

    ReportGenerator rg = new ReportGenerator("Pdf", 
      s);

    rg.addText("\n\n\n\n\n\n\n\n", 0, 0, 0);
    rg.addText("DATE : " + bean.getToday(), 0, 0, 0);
    rg.addText("\n", 0, 0, 0);
    rg.addText(bean.getEmpName(), 0, 0, 0);
    rg.addText("\n", 0, 0, 0);
    rg.addText(bean.getBranch(), 0, 0, 0);
    rg.addText("\n", 0, 0, 0);
    String message2 = "\n\n\nDear " + bean.getEmpName() + ",";
    rg.addTextBold(message2, 0, 3, 0);
    rg.addText("\n", 0, 0, 0);
    String message5 = "Subject: Resignation ";
    rg.addTextBold(message5, 0, 3, 0);
    rg.addText("\n", 0, 0, 0);
    rg.addText("\n", 0, 0, 0);

    String message7 = "Further to your resignation letter dated " + 
      bean.getResignDate() + 
      " we hereby confirm that your resignation" + 
      " \n\nhas been accepted and you are relieved from the services of the organisation with" + 
      " \n\nimmediate effect.";

    rg.addText(message7, 0, 3, 0);

    String message8 = "\n\n\n\n\n\n\n\n\nAuthority Signature";
    rg.addText(message8, 0, 0, 0);

    rg.createReport(response);
  }

  public void setWithDrw(ResignationDetails resig, String resgCode)
  {
    String query1 = " SELECT E.EMP_TOKEN,(E.EMP_FNAME||'  '||E.EMP_MNAME||'  '||E.EMP_LNAME) ,E.EMP_ID ,  NVL(RANK_NAME,' '),NVL(CENTER_NAME,''), NVL(TO_CHAR(E.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),  NVL(TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY'),' '), NVL(TO_CHAR(RESIGN_ACCEPT_DATE,'DD-MM-YYYY'),' '),  RESIGN_STATUS, NVL(RESIGN_NOTICE_PERIOD,'0'),RESIGN_NOTICE_STATUS,NVL(RESIGN_NOTICEPERIOD_ACTUAL,'0'),  NVL(RESIGN_NOTICEPERIOD_ACTUAL - RESIGN_NOTICE_PERIOD,'0'),E1.EMP_FNAME||' '||E1.EMP_MNAME||' '||E1.EMP_LNAME  ,E1.EMP_ID, E2.EMP_FNAME||' '||E2.EMP_MNAME||' '||E2.EMP_LNAME  ,E2.EMP_ID ,SETTL_LOCKFLAG, TO_CHAR(RESIGN_DATE,'DD-MM-YYYY')  FROM HRMS_RESIGN  LEFT JOIN HRMS_EMP_OFFC E ON(E.EMP_ID = HRMS_RESIGN.RESIGN_EMP)  LEFT JOIN HRMS_EMP_OFFC E1 ON (E1.EMP_ID = HRMS_RESIGN.RESIGN_WAVEOFF_APPROVER)  LEFT JOIN HRMS_EMP_OFFC E2 ON (E2.EMP_ID = HRMS_RESIGN.RESIGN_ACCEPTED_BY)  LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=E.EMP_CENTER)  LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=E.EMP_RANK)  LEFT JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE)  WHERE RESIGN_CODE=" + 
      resgCode;

    Object[][] result1 = getSqlModel().getSingleResult(query1);

    resig.setEmployeeToken(String.valueOf(result1[0][0]).trim());
    resig.setEmpName(String.valueOf(result1[0][1]).trim());
    resig.setEmpCode(String.valueOf(result1[0][2]).trim());
    resig.setDesignation(String.valueOf(result1[0][3]).trim());
    resig.setBranch(String.valueOf(result1[0][4]).trim());
    resig.setDateofjoin(String.valueOf(result1[0][5]).trim());
    resig.setSepdate(String.valueOf(result1[0][6]).trim());
    resig.setAcceptDate(String.valueOf(result1[0][7]).trim());
    resig.setStatus(String.valueOf(result1[0][8]).trim());
    resig.setNoticePeriod(String.valueOf(result1[0][9]).trim());

    resig.setNoticeperiodselect(String.valueOf(result1[0][10]).trim());
    resig.setNoticePeriodActual(String.valueOf(result1[0][11]).trim());

    resig.setApprName(String.valueOf(result1[0][13]).trim());
    resig.setApprCode(String.valueOf(result1[0][14]).trim());
    resig.setAcceptedBy(String.valueOf(result1[0][15]).trim());
    resig.setAccByCode(String.valueOf(result1[0][16]).trim());
    resig.setLockFlag(String.valueOf(result1[0][17]).trim());
    resig.setResignDate(String.valueOf(result1[0][18]).trim());

    String actualPeriod = String.valueOf(result1[0][11]);
    String period = String.valueOf(result1[0][9]);
    String stats = String.valueOf(result1[0][10]);
    int totalDays = getTotalDays(resig, actualPeriod, period, stats);
    logger.info("totalDays retrieval  : " + totalDays);
    resig.setWaveOffPeriod(String.valueOf(totalDays));

    String query = "SELECT NVL(RESIGN_REASON,' '),NVL(RESIGN_REMARK,' '),RESIGN_HR_COMMENTS FROM HRMS_RESIGN WHERE RESIGN_CODE=" + 
      resgCode;

    Object[][] result = getSqlModel().getSingleResult(query);

    String que = "SELECT RESIGN_WITHDRAWN FROM HRMS_RESIGN WHERE RESIGN_CODE=" + 
      resgCode;

    resig.setReason(String.valueOf(result[0][0]).trim());
    resig.setRemark(String.valueOf(result[0][1]).trim());
    resig.setHrComments(checkNull(String.valueOf(result[0][2]).trim()));

    Object[][] data = getSqlModel().getSingleResult(que);

    for (int i = 0; i < data.length; i++) {
      resig.setWithDrawn(String.valueOf(data[i][0]).equals("Y") ? "true" : 
        "false");
    }
    setData(resig);
  }

  public void setData(ResignationDetails resig)
  {
    String query = "SELECT \tTITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,NVL(CADRE_NAME,'') \t FROM HRMS_EMP_OFFC \t LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)   LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID= HRMS_EMP_OFFC.EMP_CADRE)  WHERE HRMS_EMP_OFFC.EMP_ID =" + 
      resig.getEmpCode();

    Object[][] result = getSqlModel().getSingleResult(query);

    resig.setEmpName(String.valueOf(result[0][0]).trim());
    resig.setCadreName(String.valueOf(result[0][1]).trim());
  }

  public void getreportForDues(HttpServletResponse response, ResignationDetails resig)
  {
    try
    {
      String reportName = " NO DUES FORM ";
      ReportGenerator rg = new ReportGenerator(
        "Pdf", reportName);

      String empDetailsQuery = " SELECT \tTITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME  , HRMS_EMP_OFFC.EMP_TOKEN,HRMS_RANK.RANK_NAME,HRMS_DEPT.DEPT_NAME,TO_CHAR( EMP_REGULAR_DATE,'DD-MM-YYYY') ,TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY') FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) INNER JOIN  HRMS_RANK  ON ( HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) WHERE HRMS_EMP_OFFC.EMP_ID =" + 
        resig.getEmpCode();

      Object[][] empDetailsObj = getSqlModel().getSingleResult(
        empDetailsQuery);

      Object[][] nameData = new Object[3][4];

      if ((empDetailsObj != null) && (empDetailsObj.length > 0))
      {
        nameData[0][0] = "Name of the Employee :";
        nameData[0][1] = checkNull(String.valueOf(empDetailsObj[0][0]));
        nameData[0][2] = "Employee No :";
        nameData[0][3] = checkNull(String.valueOf(empDetailsObj[0][1]));

        nameData[1][0] = "Designation :";
        nameData[1][1] = checkNull(String.valueOf(empDetailsObj[0][2]));
        nameData[1][2] = "Department";
        nameData[1][3] = checkNull(String.valueOf(empDetailsObj[0][3]));

        nameData[2][0] = "Date of Joining :";
        nameData[2][1] = checkNull(String.valueOf(empDetailsObj[0][4]));
        nameData[2][2] = "Date of Leaving";
        nameData[2][3] = checkNull(String.valueOf(empDetailsObj[0][5]));
      }

      int[] width_1 = { 15, 25, 15, 25 };
      int[] align_1 = new int[4];

      String[] colNames = { "Sr. No", "Department", "Items", 
        "Handed over To", "Signature of HOD" };

      int[] cellWidth = { 5, 25, 20, 20, 20 };
      int[] alignment = new int[5];

      String message = " Request the employee to obtain the No dues from the following Departments:";

      String msg = " \n\n\n\n\n Signature of the employee: \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   Date:  ";

      String str = "Date :";

      String departmentQuery = " SELECT DEPT_ID,' '||'\n'||'\n'||'\n'||DEPT_NAME||'\n'||'\n'||'\n'||' ','' ,'','' FROM HRMS_DEPT ";

      Object[][] deptDataObj = getSqlModel().getSingleResult(
        departmentQuery);

      if ((deptDataObj != null) && (deptDataObj.length > 0)) {
        rg.addTextBold("NO DUES FORM", 0, 1, 0);
        rg.addTextBold("\n", 0, 0, 0);
        rg.tableBodyNoBorder(nameData, width_1, align_1);
        rg.addText(message, 0, 0, 0);
        rg.addText("\n", 0, 0, 0);
        rg.tableBody(colNames, deptDataObj, cellWidth, alignment);
        rg.addText(msg, 0, 0, 0);
      }
      else {
        rg.addTextBold("There is no data to display.", 0, 1, 0);
      }

      rg.createReport(response);
    }
    catch (Exception e) {
      logger.error("Exception in getreportForDues-----------------" + e);
    }
  }

  public void callResignList(ResignationDetails resig, HttpServletRequest request, String profileDivision)
  {
    String listQuery = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME), TO_CHAR(RESIGN_DATE,'DD-MM-YYYY') , NVL(RESIGN_NOTICE_PERIOD,'0'),RESIGN_STATUS, \tNVL(RANK_NAME,' '),NVL(CENTER_NAME,''), \tNVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '), NVL(TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY'),' '), NVL(TO_CHAR(RESIGN_ACCEPT_DATE,'DD-MM-YYYY'),' '), HRMS_RESIGN.RESIGN_CODE,HRMS_EMP_OFFC.EMP_ID,SETTL_LOCKFLAG, RESIGN_DATE  FROM HRMS_RESIGN \tLEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP)\tLEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)\tLEFT JOIN HRMS_EMP_QUA ON(HRMS_EMP_QUA.EMP_ID = HRMS_RESIGN.RESIGN_EMP)\tLEFT JOIN HRMS_QUA ON(HRMS_QUA.QUA_ID=HRMS_EMP_QUA.QUA_MAST_CODE)  \tLEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) LEFT JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE) ";

    listQuery = listQuery + profileDivision;
    listQuery = listQuery + "\tORDER BY RESIGN_DATE DESC ";
    Object[][] result = getSqlModel().getSingleResult(listQuery);
    if ((result != null) && (result.length > 0))
      resig.setModeLength("true");
    else
      resig.setModeLength("false");
    String[] pageIndex = Utility.doPaging(resig.getMyPage(), result.length, 
      20);
    if (pageIndex == null) {
      pageIndex[0] = "0";
      pageIndex[1] = "20";
      pageIndex[2] = "1";
      pageIndex[3] = "1";
      pageIndex[4] = "";
    }

    request.setAttribute("totalPage", Integer.valueOf(Integer.parseInt(
      String.valueOf(pageIndex[2]))));
    request.setAttribute("pageNo", Integer.valueOf(Integer.parseInt(
      String.valueOf(pageIndex[3]))));
    if (pageIndex[4].equals("1"))
      resig.setMyPage("1");
    ArrayList tableList = new ArrayList();
    if (result.length > 0) {
      int i = Integer.parseInt(pageIndex[0]);
      for (; i < 
        Integer.parseInt(pageIndex[1]); i++) {
        ResignationDetails bean1 = new ResignationDetails();
        bean1.setEmpTokenItt(checkNull(String.valueOf(result[i][0])));
        bean1.setEmpNameItt(checkNull(String.valueOf(result[i][1])));
        bean1.setResignDateItt(checkNull(String.valueOf(result[i][2])));
        bean1
          .setResignCodeItt(checkNull(
          String.valueOf(result[i][10])));
        bean1.setEmpCodeItt(checkNull(String.valueOf(result[i][11])));
        tableList.add(bean1);
      }
      resig.setTableList(tableList);
      resig.setTotalRecords(String.valueOf(result.length));
      resig.setListFlag("true");
    } else {
      resig.setNoData("true");
      resig.setTableList(null);
      resig.setTotalRecords(String.valueOf(result.length));
      resig.setListFlag("true");
    }
  }

  public boolean delChkdRec(ResignationDetails resig, String[] code) {
    int count = 0;
    boolean result = false;
    for (int i = 0; i < code.length; i++) {
      if (!code[i].equals("")) {
        Object[][] delete = new Object[1][1];
        delete[0][0] = code[i];
        String delQuery = " DELETE FROM HRMS_RESIGN WHERE RESIGN_CODE=?";
        result = getSqlModel().singleExecute(delQuery, delete);
        if (!result) {
          count++;
        }
      }
    }
    if (count != 0) {
      result = false;
      return result;
    }
    result = true;
    return result;
  }

  public void getResignEmployeeList(ResignationDetails resig)
  {
    try {
      String resignEmpQuery = " SELECT EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME)  , TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),EMP_LEAVE_DATE  FROM HRMS_EMP_OFFC  WHERE EMP_STATUS='N' AND EMP_LEAVE_DATE > SYSDATE-60 AND EMP_LEAVE_DATE IS NOT NULL  ORDER BY EMP_LEAVE_DATE DESC ";

      Object[][] resignEmpObj = getSqlModel().getSingleResult(
        resignEmpQuery);
      ArrayList resignEmpList = new ArrayList();
      if ((resignEmpObj != null) && (resignEmpObj.length > 0)) {
        for (int i = 0; i < resignEmpObj.length; i++) {
          ResignationDetails bean = new ResignationDetails();
          bean.setResignEmployeeToken(
            checkNull(String.valueOf(resignEmpObj[i][0])));
          bean.setResignEmployeeName(
            checkNull(String.valueOf(resignEmpObj[i][1])));
          bean.setResignationDate(
            checkNull(String.valueOf(resignEmpObj[i][2])));
          resignEmpList.add(bean);
        }
        resig.setResignList(resignEmpList);
      }
    }
    catch (Exception localException)
    {
    }
  }
}