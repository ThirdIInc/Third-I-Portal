package org.paradyne.model.PAS.GoalSetting;

import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.GoalReportingStructure;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.lib.ParaActionSupport;

public class GoalReportingStructureModel extends ModelBase
{
  Logger logger = Logger.getLogger(ParaActionSupport.class);

  public boolean saveReportingStructure(GoalReportingStructure bean, String[] approverId, String[] view, String[] rating, String[] comments, String[] apprReview)
  {
    boolean result = false;
    Object[][] hdrCode = new Object[1][1];
    Object[][] insertFormData = new Object[1][4];
    Object[][] updateFormData = new Object[1][5];
    try
    {
      Object[][] formData = getFormData(bean);
      Object[] isDataExist = checkSave(bean, formData);
      insertFormData[0][0] = formData[0][0];
      insertFormData[0][1] = formData[0][1];
      insertFormData[0][2] = formData[0][2];
      insertFormData[0][3] = formData[0][3];

      updateFormData[0][0] = formData[0][0];
      updateFormData[0][1] = formData[0][1];
      updateFormData[0][2] = formData[0][2];
      updateFormData[0][3] = formData[0][3];

      if (isDataExist[0] == null) {
        String insertintoHeader = "INSERT INTO HRMS_REPTNG_STRUCTHDR_GOAL (REPORTINGHDR_CODE, REPORTINGHDR_DEPT_ID,  REPORTINGHDR_BRN_ID, REPORTINGHDR_EMP_ID, REPORTINGHDR_DESG_ID)  VALUES ((SELECT NVL(MAX(REPORTINGHDR_CODE),0)+1 FROM HRMS_REPTNG_STRUCTHDR_GOAL), ?, ?, ?, ?) ";

        result = getSqlModel().singleExecute(insertintoHeader, insertFormData);

        if (result) {
          String MaxHeaderQuery = " SELECT NVL(MAX (REPORTINGHDR_CODE),0) FROM HRMS_REPTNG_STRUCTHDR_GOAL ";
          Object[][] maxHdrCode = getSqlModel().getSingleResult(MaxHeaderQuery);

          hdrCode[0][0] = maxHdrCode[0][0];
        }

      }
      else
      {
        hdrCode[0][0] = isDataExist[0];

        updateFormData[0][4] = hdrCode[0][0];
        String headerUpdateQuery = "UPDATE HRMS_REPTNG_STRUCTHDR_GOAL SET REPORTINGHDR_DEPT_ID = ?, REPORTINGHDR_BRN_ID = ?, REPORTINGHDR_EMP_ID = ?, REPORTINGHDR_DESG_ID = ?WHERE REPORTINGHDR_CODE = ?";

        result = getSqlModel().singleExecute(headerUpdateQuery, updateFormData);
        if (result) {
          String deleteDtlQuery = "DELETE FROM HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE = ? ";

          result = getSqlModel().singleExecute(deleteDtlQuery, hdrCode);
        }
      }
      if ((result) && 
        (approverId != null) && (approverId.length != 0)) {
        Object[][] tableData = new Object[1][7];
        for (int i = 0; i < approverId.length; i++) {
          tableData[0][0] = hdrCode[0][0];
          tableData[0][1] = hdrCode[0][0];
          tableData[0][2] = approverId[i];
          tableData[0][3] = (view[i].equals("true") ? "Y" : "N");
          tableData[0][4] = (rating[i].equals("true") ? "Y" : "N");
          tableData[0][5] = (comments[i].equals("true") ? "Y" : "N");
          System.out.println("----------Approver----------" + apprReview[i]);
          tableData[0][6] = (apprReview[i].equals("Approver") ? "A" : "R");

          String insertDtlQuery = "INSERT INTO HRMS_REPTNG_STRUCTDTL_GOAL (REPORTINGDTL_CODE, REPORTINGHDR_CODE, REPORTINGDTL_EMP_ID, REPORTINGDTL_VIEW, REPORTINGDTL_RATING, REPORTINGDTL_COMMENTS,REPORTINGDTL_APPROVER_TYPE) VALUES ((SELECT NVL(MAX(REPORTINGDTL_CODE),0)+1 FROM HRMS_REPTNG_STRUCTDTL_GOAL WHERE REPORTINGHDR_CODE = ?), ?, ?, ?, ?, ?,?)";

          result = getSqlModel().singleExecute(insertDtlQuery, tableData);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public String showApproverData(GoalReportingStructure bean)
  {
    String result = "";
    Object[][] formData = getFormData(bean);
    Object[] hdrCode = checkSave(bean, formData);
    try {
      if (hdrCode[0] != null) {
        ArrayList list = new ArrayList();
        String showApproverQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME NAME, DECODE(REPORTINGDTL_VIEW,'Y','true','false'), DECODE(REPORTINGDTL_RATING,'Y','true','false'), DECODE(REPORTINGDTL_COMMENTS,'Y','true','false'),DECODE(REPORTINGDTL_APPROVER_TYPE,'A','Approver','R','Reviewer') FROM HRMS_REPTNG_STRUCTDTL_GOAL  INNER JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REPTNG_STRUCTDTL_GOAL.REPORTINGDTL_EMP_ID) WHERE REPORTINGHDR_CODE = " + 
          String.valueOf(hdrCode[0]) + 
          " ORDER BY REPORTINGDTL_CODE";

        Object[][] getApproverData = getSqlModel().getSingleResult(showApproverQuery);

        if ((getApproverData != null) && (getApproverData.length != 0))
          for (int i = 0; i < getApproverData.length; i++) {
            GoalReportingStructure bean1 = new GoalReportingStructure();
            bean1.setEmpIdIterator(
              checkNull(String.valueOf(getApproverData[i][0])));
            bean1.setEmpTokenIterator(
              checkNull(String.valueOf(getApproverData[i][1])));
            bean1.setEmpNameIterator(
              checkNull(String.valueOf(getApproverData[i][2])));
            bean1.setViewIterator(checkNull(String.valueOf(getApproverData[i][3])));
            bean1.setRatingIterator(checkNull(String.valueOf(getApproverData[i][4])));
            bean1.setCommentsIterator(checkNull(String.valueOf(getApproverData[i][5])));
            bean1.setApprreviewIterator(checkNull(String.valueOf(getApproverData[i][6])));
            String srNo = i + 1 + getOrdinalFor(i + 1) + 
              "-Approver";
            bean1.setSrNoIterator(srNo);
            list.add(bean1);
          }
        else {
          result = "no data";
        }

        bean.setHdrCode(String.valueOf(hdrCode[0]));
        bean.setEmpList(list);
      } else {
        result = "no data";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public Object[][] getFormData(GoalReportingStructure bean)
  {
    Object[][] headerData = new Object[1][5];
    try
    {
      String deptCode = bean.getDeptCode();
      String branchCode = bean.getBrnCode();
      String desgCode = bean.getDesignationCode();
      String empCode = bean.getEmpCode();

      if (deptCode.equals("")) {
        deptCode = "0";
      }
      if (branchCode.equals("")) {
        branchCode = "0";
      }
      if (desgCode.equals("")) {
        desgCode = "0";
      }
      if (empCode.equals("")) {
        empCode = "0";
      }
      headerData[0][0] = deptCode;
      headerData[0][1] = branchCode;
      headerData[0][2] = empCode;
      headerData[0][3] = desgCode;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return headerData;
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

  public Object[] checkSave(GoalReportingStructure bean, Object[][] headerData)
  {
    Object[] selectData = new Object[4];
    Object[] hdrCode = new Object[1];
    try
    {
      selectData[0] = headerData[0][0];
      selectData[1] = headerData[0][1];
      selectData[2] = headerData[0][2];
      selectData[3] = headerData[0][3];
      String query = "SELECT REPORTINGHDR_CODE FROM HRMS_REPTNG_STRUCTHDR_GOAL  WHERE REPORTINGHDR_DEPT_ID = ? AND REPORTINGHDR_BRN_ID = ?  AND REPORTINGHDR_EMP_ID = ? AND REPORTINGHDR_DESG_ID = ? ";

      Object[][] isDataExist = getSqlModel().getSingleResult(query, selectData);

      if ((isDataExist != null) && (isDataExist.length != 0))
        hdrCode[0] = isDataExist[0][0];
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return hdrCode;
  }

  public void shuffleColumns(GoalReportingStructure goalBean, String srNo, String[] approverId, String[] approverToken, String[] approverName, String[] designation, String[] designationId, String[] srNoIterator, String buttonType, String[] view, String[] rating, String[] comments, String[] apprReview)
  {
    boolean result = false;
    int shuffleCode = Integer.parseInt(srNo);

    if ((approverId != null) && (approverId.length != 0)) {
      ArrayList list = new ArrayList();
      ArrayList list1 = new ArrayList();

      for (int i = 0; i < approverId.length; i++) {
        GoalReportingStructure bean1 = new GoalReportingStructure();
        bean1.setEmpIdIterator(approverId[i]);
        bean1.setEmpTokenIterator(approverToken[i]);
        bean1.setEmpNameIterator(approverName[i]);
        bean1.setDesgNameIterator(designation[i]);
        bean1.setDesgIdIterator(designationId[i]);
        bean1.setSrNoIterator(srNoIterator[i]);
        bean1.setViewIterator(view[i]);
        bean1.setRatingIterator(rating[i]);
        bean1.setCommentsIterator(comments[i]);
        bean1.setApprreviewIterator(apprReview[i]);
        list.add(bean1);
      }

      if (buttonType.equals("up")) {
        GoalReportingStructure bean1 = 
          (GoalReportingStructure)list
          .get(shuffleCode - 1);
        shuffleCode = Integer.parseInt(srNo) - 1;
        if (shuffleCode > 0) {
          list.add(shuffleCode - 1, bean1);
          list.remove(shuffleCode + 1);
        }
      }
      else if (buttonType.equals("down")) {
        GoalReportingStructure bean1 = 
          (GoalReportingStructure)list
          .get(shuffleCode - 1);
        shuffleCode = Integer.parseInt(srNo) + 1;
        if ((shuffleCode > 0) && (shuffleCode < list.size() + 1)) {
          list.add(shuffleCode, bean1);
          list.remove(shuffleCode - 2);
        }
      }
      for (int i = 0; i < list.size(); i++) {
        GoalReportingStructure bean1 = (GoalReportingStructure)list.get(i);
        String showSrNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
        bean1.setSrNoIterator(showSrNo);
        list1.add(bean1);
      }
      goalBean.setEmpList(list1);
    }
  }

  public ArrayList addApprover(GoalReportingStructure bean, String[] approverId, String[] approverToken, String[] approverName, String[] view, String[] rating, String[] comments, String[] apprreview)
  {
    ArrayList list = new ArrayList();
    int count = 0;
    try {
      if ((approverId != null) && (approverId.length != 0)) {
        for (int i = 0; i < approverId.length; i++) {
          GoalReportingStructure bean1 = new GoalReportingStructure();

          if ((!bean.getSrNo().equals("")) && (i == Integer.parseInt(bean.getSrNo()) - 1))
          {
            bean1.setEmpIdIterator(bean.getEmpId());
            bean1.setEmpTokenIterator(bean.getEmpTokenAdd());
            bean1.setEmpNameIterator(bean.getEmpNameAdd());
            bean1.setViewIterator("false");
            bean1.setRatingIterator("false");
            bean1.setCommentsIterator("false");
            System.out.println("======Approver ID==========not=========" + bean.getApprReviewRadio());
            if (bean.getApprReviewRadio().equals("A"))
              bean1.setApprreviewIterator("Approver");
            else
              bean1.setApprreviewIterator("Reviewer");
          }
          else {
            bean1.setEmpIdIterator(approverId[i]);
            bean1.setEmpTokenIterator(approverToken[i]);
            bean1.setEmpNameIterator(approverName[i]);
            bean1.setViewIterator(view[i]);
            bean1.setRatingIterator(rating[i]);
            bean1.setCommentsIterator(comments[i]);

            System.out.println("====APPR==========" + apprreview[i]);
            if (apprreview[i].equals("Approver"))
              bean1.setApprreviewIterator("Approver");
            else {
              bean1.setApprreviewIterator("Reviewer");
            }
          }
          String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
          bean1.setSrNoIterator(srNo);

          count = i + 1;
          list.add(bean1);
        }
      }
      if ((!bean.getEmpId().equals("")) && (bean.getSrNo().equals(""))) {
        bean.setEmpIdIterator(bean.getEmpId());
        bean.setEmpTokenIterator(bean.getEmpTokenAdd());
        bean.setEmpNameIterator(bean.getEmpNameAdd());
        String srNo = count + 1 + getOrdinalFor(count + 1) + 
          "-Approver";
        bean.setSrNoIterator(srNo);
        bean.setViewIterator("false");
        bean.setRatingIterator("false");
        bean.setCommentsIterator("false");
        if (bean.getApprReviewRadio().equals("A"))
          bean.setApprreviewIterator("Approver");
        else
          bean.setApprreviewIterator("Reviewer");
        list.add(bean);
      }
      bean.setEmpList(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public boolean deleteApprover(GoalReportingStructure bean, String srNo, String[] approverId, String[] approverToken, String[] approverName, String[] designation, String[] designationId, String[] view, String[] rating, String[] comments, String[] apprReview)
  {
    boolean result = false;
    try
    {
      if ((approverId != null) && (approverId.length != 0)) {
        ArrayList list = new ArrayList();
        ArrayList list1 = new ArrayList();
        for (int i = 0; i < approverId.length; i++) {
          GoalReportingStructure bean1 = new GoalReportingStructure();
          bean1.setEmpIdIterator(approverId[i]);
          bean1.setEmpTokenIterator(approverToken[i]);
          bean1.setEmpNameIterator(approverName[i]);
          bean1.setDesgNameIterator(designation[i]);
          bean1.setDesgIdIterator(designationId[i]);
          bean1.setViewIterator(view[i]);
          bean1.setRatingIterator(rating[i]);
          bean1.setCommentsIterator(comments[i]);
          bean1.setApprreviewIterator(apprReview[i]);
          list.add(bean1);
        }
        list.remove(Integer.parseInt(srNo) - 1);

        for (int i = 0; i < list.size(); i++) {
          GoalReportingStructure bean1 = 
            (GoalReportingStructure)list
            .get(i);
          String showSrNo = i + 1 + getOrdinalFor(i + 1) + 
            "-Approver";
          bean1.setSrNoIterator(showSrNo);
          list1.add(bean1);
        }
        bean.setEmpList(list1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public String checkNull(String result) {
    if ((result == null) || (result.equals("null")) || (result.equals("")) || (result == "")) {
      return "";
    }

    return result;
  }

  public void genApproverListReport(GoalReportingStructure goalBean, HttpServletRequest request, HttpServletResponse response)
  {
    String type = "Xls";
    String title = "Goal Reporting Structure";
    ReportGenerator rg = new ReportGenerator(type, title);
    String header = "Goal Reporting Structure \n\n\n\n";
    rg.addFormatedText(header, 2, 0, 1, 0);
    rg.addTextBold(" Goal Reporting Structure", 0, 1, 0);
    rg.addTextBold(" ", 0, 1, 0);
    String[] colNames = { "Approver Id", "Approver Name", "Approver Branch", "Approver Dept" };
    int[] cellWidth = { 30, 30, 30, 30 };
    int[] cellTxt = { 60, 60, 20, 60 };
    int[] cellAll = { 0, 1 };

    String query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME NAME,  HRMS_CENTER.CENTER_NAME,DEPT_NAME,EMP_ID FROM HRMS_EMP_OFFC LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER  LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  WHERE EMP_STATUS = 'S' ";

    String empId = goalBean.getEmpCode();
    String strQuery = "";
    Object[][] approvedListData = (Object[][])null;

    strQuery = query + " AND EMP_ID IN(SELECT REPORTINGHDR_EMP_ID FROM  HRMS_REPTNG_STRUCTHDR_GOAL )ORDER BY EMP_ID";
    approvedListData = getSqlModel().getSingleResult(strQuery);
    query = query + " AND EMP_ID NOT IN(SELECT REPORTINGHDR_EMP_ID FROM  HRMS_REPTNG_STRUCTHDR_GOAL )";
    query = query + " ORDER BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME";

    Object[][] data = getSqlModel().getSingleResult(query);
    if ((data != null) && (data.length > 0)) {
      try {
        Object[][] result = new Object[data.length][4];
        for (int i = 0; i < data.length; i++) {
          result[i][0] = checkNull(String.valueOf(data[i][0]));
          result[i][1] = checkNull(String.valueOf(data[i][1]));
          result[i][2] = checkNull(String.valueOf(data[i][2]));
          result[i][3] = checkNull(String.valueOf(data[i][3]));
        }
        rg.addTextBold("Employee pending for configuration", 0, 1, 0);
        rg.tableBody(colNames, result, cellWidth, cellAll);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if ((approvedListData != null) && (approvedListData.length > 0)) {
      try {
        Object[][] approvedListResult = new Object[approvedListData.length][4];
        for (int i = 0; i < approvedListResult.length; i++) {
          approvedListResult[i][0] = checkNull(String.valueOf(approvedListData[i][0]));
          approvedListResult[i][1] = checkNull(String.valueOf(approvedListData[i][1]));
          approvedListResult[i][2] = checkNull(String.valueOf(approvedListData[i][2]));
          approvedListResult[i][3] = checkNull(String.valueOf(approvedListData[i][3]));
        }

        rg.addTextBold(" ", 0, 1, 0);
        rg.addTextBold(" ", 0, 1, 0);
        rg.addTextBold("Configured employee list ", 0, 1, 0);
        rg.tableBody(colNames, approvedListResult, cellWidth, cellAll);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    rg.createReport(response);
  }
}