package org.paradyne.model.PAS.GoalSetting;

import com.itextpdf.text.BaseColor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.MyTeamGoalReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class MyTeamGoalReportModel extends ModelBase
{
  static Logger logger = Logger.getLogger(MyTeamGoalReportModel.class);

  public void genReport(MyTeamGoalReport bean, HttpServletResponse response, HttpServletRequest request, String reportPath) {
    try {
      ReportGenerator rg = null;
      String reportType = bean.getReport();
      String title = "My Team Goal Report ";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "My Team Goal Report_" + Utility.getRandomNumber(1000);
      rds.setFileName(fileName);

      rds.setReportName(title);
      rds.setPageSize("A4");
      rds.setPageOrientation("landscape");
      rds.setUserEmpId(bean.getUserEmpId());
      rds.setReportType(reportType);
      String filters = "";
      String whereClause = "";
      rds.setTotalColumns(9);
      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, this.session, this.context, request);
      } else {
        logger.info("################# ATTACHMENT PATH #############" + reportPath + fileName);
        rg = new ReportGenerator(rds, reportPath, this.session, this.context, request);

        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
        request.setAttribute("action", "/pas/MyTeamGoalReport_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }

      filters = filters + " Goal Name : " + bean.getGoalName();

      String goalFrmDateQuery = "SELECT TO_CHAR(HRMS_GOAL_CONFIG.GOAL_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_GOAL_CONFIG.GOAL_TO_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_CONFIG  WHERE HRMS_GOAL_CONFIG.GOAL_ID=" + bean.getGoalId();
      Object[][] goalFrmDateObj = getSqlModel().getSingleResult(goalFrmDateQuery);
      filters = filters + "\n From Date : " + String.valueOf(goalFrmDateObj[0][0]);
      filters = filters + "\n To Date : " + String.valueOf(goalFrmDateObj[0][1]);

      if ((bean.getGoalStatus() != null) && (!bean.getGoalStatus().equals("")) && (!bean.getGoalStatus().equals("0"))) {
        whereClause = whereClause + " AND EMP_GOAL_APPROVAL_STATUS =" + bean.getGoalStatus();
        String goalStatus = "";
        if (bean.getGoalStatus().equals("1")) {
          goalStatus = "Draft";
        }
        if (bean.getGoalStatus().equals("2")) {
          goalStatus = "Pending";
        }
        if (bean.getGoalStatus().equals("3")) {
          goalStatus = "Approved";
        }
        if (bean.getGoalStatus().equals("4")) {
          goalStatus = "Send Back";
        }
        filters = filters + "\n Goal Status : " + goalStatus;
      }
      if ((bean.getEmpName() != null) && (!bean.getEmpName().equals(""))) {
        whereClause = whereClause + "AND HRMS_GOAL_EMP_HDR.EMP_ID=" + bean.getEmpId();
        filters = filters + "\n Employee Name : " + bean.getEmpToken() + "-" + bean.getEmpName();
      }

      TableDataSet filterData = new TableDataSet();
      filterData.setData(new Object[][] { { filters } });
      filterData.setCellAlignment(new int[1]);
      filterData.setBodyFontStyle(0);
      filterData.setCellWidth(new int[] { 100 });
      filterData.setCellColSpan(new int[] { 8 });
      filterData.setBlankRowsBelow(1);
      filterData.setCellNoWrap(new boolean[1]);
      filterData.setBorder(Boolean.valueOf(false));

      rg.addTableToDoc(filterData);

      String reportingType = "SELECT GOAL_REPORTING FROM HRMS_GOAL_CONFIG WHERE HRMS_GOAL_CONFIG.GOAL_ID=" + bean.getGoalId();
      Object[][] reportingTypeObj = getSqlModel().getSingleResult(reportingType);
      Object[][] empIdObj = (Object[][])null;
      String query = "";
      Object[][] queryData = (Object[][])null;

      String selectQuery = "SELECT REPORTING_IS_SAMEASOFFICIAL FROM HRMS_REPORTING_APPL_TYPE WHERE \tREPORTING_APPL_TYPE_NAME='Goal Setting'";
      Object[][] selectQueryObj = getSqlModel().getSingleResult(selectQuery);
      if (reportingTypeObj != null) {
        if (String.valueOf(reportingTypeObj[0][0]).equals("goal")) {
          query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||  NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), GOAL_DESCRIPTION,  NVL(GOAL_COMMENTS,''), NVL(GOAL_CATEGORY,' '),GOAL_CATEGORY_WEIGHTAGE,GOAL_WEIGHTAGE , TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY'), DECODE(EMP_GOAL_APPROVAL_STATUS,'1','Draft','2','Pending','3','Approved','4','Send Back','5','') AS STATUS FROM HRMS_GOAL_EMP_DTL INNER JOIN  HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) INNER JOIN  HRMS_GOAL_CONFIG ON(HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)  INNER JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_HDR.EMP_ID )  INNER JOIN  HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID =   HRMS_GOAL_EMP_HDR.GOAL_APPROVER_ID) LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID)   WHERE  HRMS_GOAL_EMP_HDR.EMP_ID IN( SELECT DISTINCT REPORTINGHDR_EMP_ID FROM HRMS_REPTNG_STRUCTHDR_GOAL  INNER JOIN HRMS_REPTNG_STRUCTDTL_GOAL ON HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_CODE=HRMS_REPTNG_STRUCTDTL_GOAL.REPORTINGHDR_CODE WHERE REPORTINGDTL_EMP_ID =" + 
            bean.getUserEmpId() + ")" + 
            " AND HRMS_GOAL_EMP_HDR.GOAL_ID= " + bean.getGoalId();
        }
        else if (selectQueryObj[0][0].equals("Y")) {
          query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||   NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), GOAL_DESCRIPTION,  NVL(GOAL_COMMENTS,''), NVL(GOAL_CATEGORY,' '),GOAL_CATEGORY_WEIGHTAGE,GOAL_WEIGHTAGE , TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY'), DECODE(EMP_GOAL_APPROVAL_STATUS,'1','Draft','2','Pending','3','Approved','4','Send Back','5','') AS STATUS  FROM HRMS_GOAL_EMP_DTL INNER JOIN  HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) INNER JOIN  HRMS_GOAL_CONFIG ON(HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)  INNER JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_HDR.EMP_ID )  INNER JOIN  HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID =   HRMS_GOAL_EMP_HDR.GOAL_APPROVER_ID)\t LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE) \t LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID)    WHERE  HRMS_GOAL_EMP_HDR.EMP_ID IN( SELECT DISTINCT EMP_ID FROM HRMS_EMP_OFFC  WHERE EMP_REPORTING_OFFICER =" + 
            bean.getUserEmpId() + ")" + 
            " AND HRMS_GOAL_EMP_HDR.GOAL_ID=" + bean.getGoalId();
        }
        else
        {
          query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||  NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), GOAL_DESCRIPTION,  NVL(GOAL_COMMENTS,''), NVL(GOAL_CATEGORY,' '),GOAL_CATEGORY_WEIGHTAGE,GOAL_WEIGHTAGE , TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY'), DECODE(EMP_GOAL_APPROVAL_STATUS,'1','Draft','2','Pending','3','Approved','4','Send Back','5','') AS STATUS FROM HRMS_GOAL_EMP_DTL INNER JOIN  HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) INNER JOIN  HRMS_GOAL_CONFIG ON(HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)  INNER JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_HDR.EMP_ID )  INNER JOIN  HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID =   HRMS_GOAL_EMP_HDR.GOAL_APPROVER_ID) LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID)   WHERE  HRMS_GOAL_EMP_HDR.EMP_ID IN( SELECT DISTINCT REPORTINGHDR_EMP_ID FROM HRMS_REPORTING_STRUCTUREHDR INNER JOIN  HRMS_REPORTING_STRUCTUREDTL ON  HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_CODE= HRMS_REPORTING_STRUCTUREDTL.REPORTINGHDR_CODE WHERE REPORTINGDTL_EMP_ID =" + 
            bean.getUserEmpId() + " AND REPORTINGHDR_TYPE='EmpGoal')" + 
            " AND HRMS_GOAL_EMP_HDR.GOAL_ID= " + bean.getGoalId();
        }
      }

      query = query + whereClause;

      queryData = getSqlModel().getSingleResult(query);

      if ((queryData != null) && (queryData.length > 0)) {
        TableDataSet tdstable = new TableDataSet();
        tdstable.setHeader(new String[] { "Employee Id", "Employee Name", "Goal Description", 
          "Goal Comments", "Goal Category", "Category Weightage", "Goal Weightage", "Achievement date", "Status" });
        tdstable.setData(queryData);

        tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
        tdstable.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 2, 2, 1,0 });
        tdstable.setCellWidth(new int[] { 50, 25, 25, 25, 25, 50, 25, 25, 25 });
        tdstable.setHeaderBorderDetail(3);
        tdstable.setBorderDetail(3);
        tdstable.setBorder(Boolean.valueOf(true));
        tdstable.setBlankRowsAbove(1);
        tdstable.setHeaderTable(true);
        tdstable.setBlankRowsBelow(1);
        rg.addTableToDoc(tdstable);
      } else {
        TableDataSet noData = new TableDataSet();
        Object[][] noDataObj = new Object[1][1];
        noDataObj[0][0] = "No records available";

        noData.setData(noDataObj);
        noData.setCellAlignment(new int[] { 1 });
        noData.setCellWidth(new int[] { 100 });

        noData.setBorder(Boolean.valueOf(false));
        rg.addTableToDoc(noData);
      }

      rg.process();
      if (reportPath.equals("")) {
        rg.createReport(response);
      }
      else
      {
        rg.saveReport(response);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String checkNull(String result) {
    if ((result == null) || (result.equals("null")) || (result.equals(" ")) || 
      (result.equals(""))) {
      return "";
    }

    return result;
  }
}