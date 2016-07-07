package org.paradyne.model.PAS.GoalSetting;

import com.itextpdf.text.BaseColor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.GoalSettingStatusMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class GoalSettingStatusMisReportModel extends ModelBase
{
  static Logger logger = Logger.getLogger(GoalSettingStatusMisReportModel.class);

  public void genReport(GoalSettingStatusMisReport bean, HttpServletResponse response, HttpServletRequest request, String reportPath)
  {
    try {
      ReportGenerator rg = null;
      String reportType = bean.getReport();
      String title = "Goal Status Report ";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "Goal Status Report_" + Utility.getRandomNumber(1000);
      rds.setFileName(fileName);

      rds.setReportName(title);
      rds.setPageSize("A4");
      rds.setPageOrientation("landscape");
      rds.setUserEmpId(bean.getUserEmpId());
      rds.setReportType(reportType);
      String filters = "";

      filters = filters + "Goal Name : " + bean.getGoalName();
      String goalFrmDateQuery = "SELECT TO_CHAR(HRMS_GOAL_CONFIG.GOAL_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_GOAL_CONFIG.GOAL_TO_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_CONFIG  WHERE HRMS_GOAL_CONFIG.GOAL_ID=" + bean.getGoalId();
      Object[][] goalFrmDateObj = getSqlModel().getSingleResult(goalFrmDateQuery);
      filters = filters + "\nFrom Date : " + String.valueOf(goalFrmDateObj[0][0]);
      filters = filters + "\nTo Date : " + String.valueOf(goalFrmDateObj[0][1]);

      if ((bean.getEmpId() != null) && (!bean.getEmpId().equals("")))
      {
        filters = filters + "\n Employee Name : " + bean.getEmpToken() + "-" + bean.getEmpName();
      }
      if ((bean.getDivisionId() != null) && (!bean.getDivisionId().equals("")))
      {
        filters = filters + "\n Division : " + bean.getDivisionName();
      }
      if ((bean.getDeptId() != null) && (!bean.getDeptId().equals("")))
      {
        filters = filters + "\n Department : " + bean.getDeptName();
      }
      if ((bean.getDesgId() != null) && (!bean.getDesgId().equals("")))
      {
        filters = filters + "\n Designation : " + bean.getDesgName();
      }
      if ((bean.getBranchId() != null) && (!bean.getBranchId().equals("")))
      {
        filters = filters + "\n Branch : " + bean.getBranchName();
      }

      rds.setTotalColumns(9);
      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, this.session, this.context, request);
      } else {
        logger.info("################# ATTACHMENT PATH #############" + reportPath + fileName);
        rg = new ReportGenerator(rds, reportPath, this.session, this.context, request);

        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
        request.setAttribute("action", "/pas/GoalSettingStatusMisReport_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
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

      Object[][] AssessTitle = (Object[][])null;
      TableDataSet AssHeaderData = null;
      String pendingEmpQuery = "";
      if (bean.getShowGoalSettingReport().equals("true")) {
        AssessTitle = new Object[1][1];
        AssessTitle[0][0] = "Goal Setting Report :";
        AssHeaderData = new TableDataSet();
        AssHeaderData.setData(new Object[][] { { "Goal Setting Report : " } });

        AssHeaderData.setCellAlignment(new int[1]);
        AssHeaderData.setCellWidth(new int[] { 100 });
        AssHeaderData.setBodyFontStyle(1);
        AssHeaderData.setCellColSpan(new int[] { 8 });

        AssHeaderData.setBorderDetail(0);
        AssHeaderData.setBlankRowsBelow(1);

        rg.addTableToDoc(AssHeaderData);
        if (bean.getShowPendingEmployee().equals("true")) {
          AssessTitle = new Object[1][1];
          AssessTitle[0][0] = "Employees not submitted the goal setting application :";
          AssHeaderData = new TableDataSet();
          AssHeaderData.setData(new Object[][] { { "Employees not submitted the goal setting application : " } });

          AssHeaderData.setCellAlignment(new int[1]);
          AssHeaderData.setCellWidth(new int[] { 100 });
          AssHeaderData.setBodyFontStyle(1);
          AssHeaderData.setCellColSpan(new int[] { 8 });

          AssHeaderData.setBorderDetail(0);
          AssHeaderData.setBlankRowsBelow(1);

          rg.addTableToDoc(AssHeaderData);

          pendingEmpQuery = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME,  NVL(HRMS_DIVISION.DIV_NAME,' '),  NVL(CENTER_NAME,''),  NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  ')  FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_DEPT ON(EMP_DEPT=HRMS_DEPT.DEPT_ID) LEFT JOIN HRMS_RANK ON(EMP_RANK=HRMS_RANK.RANK_ID)   LEFT JOIN HRMS_CENTER ON(EMP_CENTER=HRMS_CENTER.CENTER_ID) LEFT JOIN HRMS_DIVISION ON(EMP_DIV=HRMS_DIVISION.DIV_ID)   WHERE  EMP_STATUS='S' AND EMP_ID NOT IN (SELECT EMP_ID FROM HRMS_GOAL_EMP_HDR WHERE  GOAL_ID=" + 
            bean.getGoalId() + ") AND EMP_ID IN (SELECT EMP_ID FROM HRMS_GOAL_ELIGIBLE_EMP WHERE  GOAL_ID=" + bean.getGoalId() + ")";

          if (!bean.getDivisionId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND EMP_DIV=" + bean.getDivisionId();
          }
          if (!bean.getBranchId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND EMP_CENTER=" + bean.getBranchId();
          }
          if (!bean.getDeptId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND EMP_DEPT=" + bean.getDeptId();
          }
          if (!bean.getDesgId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND EMP_RANK=" + bean.getDesgId();
          }
          if (!bean.getEmpId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND EMP_ID=" + bean.getEmpId();
          }

          pendingEmpQuery = pendingEmpQuery + "\tORDER BY EMP_ID";
          Object[][] pendingEmp = getSqlModel().getSingleResult(pendingEmpQuery);
          if ((pendingEmp != null) && (pendingEmp.length > 0)) {
            String[] empColumns = { "Employee Id", "Employee Name", "Division", "Branch", "Department", "Designation" };
            int[] empcellWidth = { 10, 30, 20, 20, 20, 20 };
            int[] empcellAlign = new int[6];
            TableDataSet tdstable = new TableDataSet();
            tdstable.setHeader(empColumns);
            tdstable.setCellAlignment(empcellAlign);
            tdstable.setCellWidth(empcellWidth);
            tdstable.setBlankRowsBelow(1);
            tdstable.setHeaderTable(true);
            tdstable.setHeaderBorderDetail(3);
            tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
            tdstable.setData(pendingEmp);
            tdstable.setBorderDetail(3);
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
        }
        if (bean.getShowGoalSettingselfReport().equals("true")) {
          AssessTitle = new Object[1][1];
          AssessTitle[0][0] = "Pending with Self :";
          AssHeaderData = new TableDataSet();
          AssHeaderData.setData(new Object[][] { { "Pending with Self : " } });

          AssHeaderData.setCellAlignment(new int[1]);
          AssHeaderData.setCellWidth(new int[] { 100 });
          AssHeaderData.setBodyFontStyle(1);
          AssHeaderData.setCellColSpan(new int[] { 8 });

          AssHeaderData.setBorderDetail(0);
          AssHeaderData.setBlankRowsBelow(1);

          rg.addTableToDoc(AssHeaderData);
          pendingEmpQuery = "SELECT OFFC1.EMP_TOKEN,  (HRMS_TITLE .TITLE_NAME||' '|| OFFC1.EMP_FNAME||' '||NVL(OFFC1.EMP_MNAME,' ')|| ' ' || OFFC1.EMP_LNAME),  NVL(HRMS_DIVISION.DIV_NAME,' '),  NVL(CENTER_NAME,''),  NVL(HRMS_DEPT.DEPT_NAME,' '), NVL(HRMS_RANK.RANK_NAME,'  '),  DECODE(EMP_GOAL_APPROVAL_STATUS,'1','Draft','4','Send Back','6','Revise')  FROM HRMS_GOAL_EMP_HDR   INNER JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID =   HRMS_GOAL_EMP_HDR.EMP_ID )  LEFT JOIN  HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID)   LEFT JOIN HRMS_TITLE  ON(OFFC1.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)   LEFT JOIN HRMS_DEPT ON(OFFC1.EMP_DEPT=HRMS_DEPT.DEPT_ID)    LEFT JOIN HRMS_RANK ON(OFFC1.EMP_RANK=HRMS_RANK.RANK_ID)    LEFT JOIN HRMS_CENTER ON(OFFC1.EMP_CENTER=HRMS_CENTER.CENTER_ID)   LEFT JOIN HRMS_DIVISION ON(OFFC1.EMP_DIV=HRMS_DIVISION.DIV_ID)  WHERE EMP_GOAL_APPROVAL_STATUS IN(1,4,6) AND HRMS_GOAL_EMP_HDR.GOAL_ID = " + 
            bean.getGoalId();

          if (!bean.getDivisionId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND OFFC1.EMP_DIV=" + bean.getDivisionId();
          }
          if (!bean.getBranchId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND OFFC1.EMP_CENTER=" + bean.getBranchId();
          }
          if (!bean.getDeptId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND OFFC1.EMP_DEPT=" + bean.getDeptId();
          }
          if (!bean.getDesgId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND OFFC1.EMP_RANK=" + bean.getDesgId();
          }
          if (!bean.getEmpId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND OFFC1.EMP_ID=" + bean.getEmpId();
          }

          pendingEmpQuery = pendingEmpQuery + "\tORDER BY OFFC1.EMP_ID";
          Object[][] pendingEmp = getSqlModel().getSingleResult(pendingEmpQuery);
          if ((pendingEmp != null) && (pendingEmp.length > 0)) {
            String[] empColumns = { "Employee Id", "Employee Name", "Division", "Branch", "Department", "Designation", "Status" };
            int[] empcellWidth = { 10, 30, 20, 20, 20, 20, 20 };
            int[] empcellAlign = new int[7];
            TableDataSet tdstable = new TableDataSet();
            tdstable.setHeader(empColumns);
            tdstable.setCellAlignment(empcellAlign);
            tdstable.setCellWidth(empcellWidth);
            tdstable.setBlankRowsBelow(1);
            tdstable.setHeaderTable(true);
            tdstable.setHeaderBorderDetail(3);
            tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
            tdstable.setData(pendingEmp);
            tdstable.setBorderDetail(3);
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
        }

        if (bean.getShowGoalSettingManagerReport().equals("true"))
        {
          AssessTitle = new Object[1][1];
          AssessTitle[0][0] = "Pending with Manager :";
          AssHeaderData = new TableDataSet();
          AssHeaderData.setData(new Object[][] { { "Pending with Manager : " } });

          AssHeaderData.setCellAlignment(new int[1]);
          AssHeaderData.setCellWidth(new int[] { 100 });
          AssHeaderData.setBodyFontStyle(1);
          AssHeaderData.setCellColSpan(new int[] { 8 });

          AssHeaderData.setBorderDetail(0);
          AssHeaderData.setBlankRowsBelow(1);

          rg.addTableToDoc(AssHeaderData);

          pendingEmpQuery = " SELECT OFFC1.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '|| OFFC1.EMP_FNAME||' '||NVL(OFFC1.EMP_MNAME,' ')|| ' ' || OFFC1.EMP_LNAME) ,  NVL(HRMS_DIVISION.DIV_NAME,' '),  NVL(CENTER_NAME,''),  NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  '),   GOAL_LEVEL ,(OFFC2.EMP_FNAME||' '||NVL(OFFC2.EMP_MNAME,' ')|| ' ' || OFFC2.EMP_LNAME)   FROM HRMS_GOAL_EMP_HDR  INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID =   HRMS_GOAL_EMP_HDR.GOAL_APPROVER_ID)  INNER JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID =   HRMS_GOAL_EMP_HDR.EMP_ID )  LEFT JOIN  HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID)  LEFT JOIN HRMS_TITLE  ON(OFFC1.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_DEPT ON(OFFC1.EMP_DEPT=HRMS_DEPT.DEPT_ID)   LEFT JOIN HRMS_RANK ON(OFFC1.EMP_RANK=HRMS_RANK.RANK_ID)   LEFT JOIN HRMS_CENTER ON(OFFC1.EMP_CENTER=HRMS_CENTER.CENTER_ID)  LEFT JOIN HRMS_DIVISION ON(OFFC1.EMP_DIV=HRMS_DIVISION.DIV_ID)  WHERE EMP_GOAL_APPROVAL_STATUS IN(2)  AND HRMS_GOAL_EMP_HDR.GOAL_ID=" + 
            bean.getGoalId();

          if (!bean.getDivisionId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND OFFC1.EMP_DIV=" + bean.getDivisionId();
          }
          if (!bean.getBranchId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND OFFC1.EMP_CENTER=" + bean.getBranchId();
          }
          if (!bean.getDeptId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND OFFC1.EMP_DEPT=" + bean.getDeptId();
          }
          if (!bean.getDesgId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND OFFC1.EMP_RANK=" + bean.getDesgId();
          }
          if (!bean.getEmpId().equals("")) {
            pendingEmpQuery = pendingEmpQuery + " AND OFFC1.EMP_ID=" + bean.getEmpId();
          }

          pendingEmpQuery = pendingEmpQuery + "\tORDER BY OFFC1.EMP_ID";
          Object[][] pendingEmp = getSqlModel().getSingleResult(pendingEmpQuery);
          if ((pendingEmp != null) && (pendingEmp.length > 0)) {
            String[] empColumns = { "Employee Id", "Employee Name", "Division", "Branch", "Department", "Designation", "Pending At Level", "Pending With" };
            int[] empcellWidth = { 10, 30, 20, 20, 20, 20, 10, 20 };
            int[] empcellAlign = { 0, 0, 0, 0, 0, 0, 1 };
            TableDataSet tdstable = new TableDataSet();
            tdstable.setHeader(empColumns);
            tdstable.setCellAlignment(empcellAlign);
            tdstable.setCellWidth(empcellWidth);
            tdstable.setBlankRowsBelow(1);
            tdstable.setHeaderTable(true);
            tdstable.setHeaderBorderDetail(3);
            tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
            tdstable.setData(pendingEmp);
            tdstable.setBorderDetail(3);
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
        }
      }
      if (bean.getShowGoalAssesmentReport().equals("true")) {
        AssessTitle = new Object[1][1];
        AssessTitle[0][0] = "Goal Assessment Report :";
        AssHeaderData = new TableDataSet();
        AssHeaderData.setData(new Object[][] { { "Goal Assessment Report : " } });

        AssHeaderData.setCellAlignment(new int[1]);
        AssHeaderData.setCellWidth(new int[] { 100 });
        AssHeaderData.setBodyFontStyle(1);
        AssHeaderData.setCellColSpan(new int[] { 8 });
        AssHeaderData.setBorderDetail(0);
        AssHeaderData.setBlankRowsBelow(1);
        rg.addTableToDoc(AssHeaderData);
        String reviewDateQuery = "SELECT TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_ASSESSMENT_CONFIG WHERE GOAL_ID=" + 
          bean.getGoalId();
        Object[][] reviewDate = getSqlModel().getSingleResult(reviewDateQuery);
        if ((reviewDate != null) && (reviewDate.length > 0)) {
          for (int i = 0; i < reviewDate.length; i++) {
            String filterDate = "";
            filterDate = filterDate + "Goal Review Date: \t" + String.valueOf(reviewDate[i][0]);
            TableDataSet tdstable1 = new TableDataSet();
            tdstable1.setData(new Object[][] { { filterDate } });
            tdstable1.setCellAlignment(new int[1]);
            tdstable1.setCellWidth(new int[] { 100 });
            tdstable1.setBodyFontStyle(1);
            tdstable1.setCellColSpan(new int[] { 8 });
            tdstable1.setBorderDetail(0);
            tdstable1.setBlankRowsBelow(1);
            rg.addTableToDoc(tdstable1);
            if (bean.getShowGoalAssesmentSelfReport().equals("true")) {
              AssessTitle = new Object[1][1];
              AssessTitle[0][0] = "Pending with Self :";
              AssHeaderData = new TableDataSet();
              AssHeaderData.setData(new Object[][] { { "Pending with Self : " } });

              AssHeaderData.setCellAlignment(new int[1]);
              AssHeaderData.setCellWidth(new int[] { 100 });
              AssHeaderData.setBodyFontStyle(1);
              AssHeaderData.setCellColSpan(new int[] { 8 });

              AssHeaderData.setBorderDetail(0);
              AssHeaderData.setBlankRowsBelow(1);

              rg.addTableToDoc(AssHeaderData);

              pendingEmpQuery = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), NVL(HRMS_DIVISION.DIV_NAME,'') ,NVL(HRMS_CENTER.CENTER_NAME,''), NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  ') FROM HRMS_GOAL_EMP_ASSESSMENT_HDR INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID  LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)   LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) LEFT JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID)    LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  INNER JOIN HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) INNER JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID   WHERE HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_STATUS IN(1) AND HRMS_GOAL_CONFIG.GOAL_ID=" + 
                bean.getGoalId() + 
                " AND TO_CHAR(TO_DATE(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'))=TO_DATE('" + reviewDate[i][0] + "','DD-MM-YYYY')";

              if (!bean.getDivisionId().equals("")) {
                pendingEmpQuery = pendingEmpQuery + " AND HRMS_EMP_OFFC.EMP_DIV=" + bean.getDivisionId();
              }
              if (!bean.getBranchId().equals("")) {
                pendingEmpQuery = pendingEmpQuery + " AND HRMS_EMP_OFFC.EMP_CENTER=" + bean.getBranchId();
              }
              if (!bean.getDeptId().equals("")) {
                pendingEmpQuery = pendingEmpQuery + " AND HRMS_EMP_OFFC.EMP_DEPT=" + bean.getDeptId();
              }
              if (!bean.getDesgId().equals("")) {
                pendingEmpQuery = pendingEmpQuery + " AND HRMS_EMP_OFFC.EMP_RANK=" + bean.getDesgId();
              }
              if (!bean.getEmpId().equals("")) {
                pendingEmpQuery = pendingEmpQuery + " AND HRMS_EMP_OFFC.EMP_ID=" + bean.getEmpId();
              }
              pendingEmpQuery = pendingEmpQuery + "\tORDER BY HRMS_EMP_OFFC.EMP_ID";
              Object[][] pendingEmp = getSqlModel().getSingleResult(pendingEmpQuery);
              if ((pendingEmp != null) && (pendingEmp.length > 0)) {
                String[] empColumns = { "Employee Id", "Employee Name", "Division", "Branch", "Department", "Designation" };
                int[] empcellWidth = { 10, 30, 20, 20, 20, 20 };
                int[] empcellAlign = new int[6];
                TableDataSet tdstable = new TableDataSet();
                tdstable.setHeader(empColumns);
                tdstable.setCellAlignment(empcellAlign);
                tdstable.setCellWidth(empcellWidth);
                tdstable.setBlankRowsBelow(1);
                tdstable.setHeaderTable(true);
                tdstable.setHeaderBorderDetail(3);
                tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
                tdstable.setData(pendingEmp);
                tdstable.setBorderDetail(3);
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
            }
            if (bean.getShowGoalAssesmentManagerReport().equals("true")) {
              AssessTitle = new Object[1][1];
              AssessTitle[0][0] = "Pending with Manager :";
              AssHeaderData = new TableDataSet();
              AssHeaderData.setData(new Object[][] { { "Pending with Manager : " } });

              AssHeaderData.setCellAlignment(new int[1]);
              AssHeaderData.setCellWidth(new int[] { 100 });
              AssHeaderData.setBodyFontStyle(1);
              AssHeaderData.setCellColSpan(new int[] { 8 });

              AssHeaderData.setBorderDetail(0);
              AssHeaderData.setBlankRowsBelow(1);

              rg.addTableToDoc(AssHeaderData);
              pendingEmpQuery = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), NVL(HRMS_DIVISION.DIV_NAME,'') ,NVL(HRMS_CENTER.CENTER_NAME,''), NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  '), GOAL_ASSESSER_LEVEL,(OFFC2.EMP_FNAME||' '||NVL(OFFC2.EMP_MNAME,' ')|| ' ' || OFFC2.EMP_LNAME) FROM HRMS_GOAL_EMP_ASSESSMENT_HDR INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID  LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)   LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) LEFT JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID)    LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  INNER JOIN HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) INNER JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID   INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HRMS_GOAL_EMP_ASSESSMENT_HDR .GOAL_ASSESSER_ID) WHERE HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_STATUS IN(5) AND HRMS_GOAL_CONFIG.GOAL_ID=" + 
                bean.getGoalId() + 
                " AND TO_CHAR(TO_DATE(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'))=TO_DATE('" + reviewDate[i][0] + "','DD-MM-YYYY')";

              if (!bean.getDivisionId().equals("")) {
                pendingEmpQuery = pendingEmpQuery + " AND HRMS_EMP_OFFC.EMP_DIV=" + bean.getDivisionId();
              }
              if (!bean.getBranchId().equals("")) {
                pendingEmpQuery = pendingEmpQuery + " AND HRMS_EMP_OFFC.EMP_CENTER=" + bean.getBranchId();
              }
              if (!bean.getDeptId().equals("")) {
                pendingEmpQuery = pendingEmpQuery + " AND HRMS_EMP_OFFC.EMP_DEPT=" + bean.getDeptId();
              }
              if (!bean.getDesgId().equals("")) {
                pendingEmpQuery = pendingEmpQuery + " AND HRMS_EMP_OFFC.EMP_RANK=" + bean.getDesgId();
              }
              if (!bean.getEmpId().equals("")) {
                pendingEmpQuery = pendingEmpQuery + " AND HRMS_EMP_OFFC.EMP_ID=" + bean.getEmpId();
              }
              pendingEmpQuery = pendingEmpQuery + "\tORDER BY HRMS_EMP_OFFC.EMP_ID";
              Object[][] pendingEmp = getSqlModel().getSingleResult(pendingEmpQuery);
              if ((pendingEmp != null) && (pendingEmp.length > 0)) {
                String[] empColumns = { "Employee Id", "Employee Name", "Division", "Branch", "Department", "Designation", "Pending At Level", "Pending With" };
                int[] empcellWidth = { 10, 30, 20, 20, 20, 20, 10, 20 };
                int[] empcellAlign = new int[8];
                TableDataSet tdstable = new TableDataSet();
                tdstable.setHeader(empColumns);
                tdstable.setCellAlignment(empcellAlign);
                tdstable.setCellWidth(empcellWidth);
                tdstable.setBlankRowsBelow(1);
                tdstable.setHeaderTable(true);
                tdstable.setHeaderBorderDetail(3);
                tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
                tdstable.setData(pendingEmp);
                tdstable.setBorderDetail(3);
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
            }
          }

        }

        AssessTitle = new Object[1][1];
        AssessTitle[0][0] = "Employees Completed The Goal:";
        AssHeaderData = new TableDataSet();
        AssHeaderData.setData(new Object[][] { { "Employees Completed The Goal : " } });

        AssHeaderData.setCellAlignment(new int[1]);
        AssHeaderData.setCellWidth(new int[] { 100 });
        AssHeaderData.setBodyFontStyle(1);
        AssHeaderData.setCellColSpan(new int[] { 8 });

        AssHeaderData.setBorderDetail(0);
        AssHeaderData.setBlankRowsBelow(1);

        rg.addTableToDoc(AssHeaderData);

        pendingEmpQuery = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), NVL(HRMS_DIVISION.DIV_NAME,'') ,NVL(HRMS_CENTER.CENTER_NAME,''), NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  '), TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_EMP_ASSESSMENT_HDR INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID  LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)   LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) LEFT JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID)    LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  INNER JOIN HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) INNER JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID   INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HRMS_GOAL_EMP_HDR.GOAL_APPROVER_ID) WHERE HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_STATUS IN(2) AND HRMS_GOAL_CONFIG.GOAL_ID=" + 
          bean.getGoalId();

        if (!bean.getDivisionId().equals("")) {
          pendingEmpQuery = pendingEmpQuery + " AND EMP_DIV=" + bean.getDivisionId();
        }
        if (!bean.getBranchId().equals("")) {
          pendingEmpQuery = pendingEmpQuery + " AND EMP_CENTER=" + bean.getBranchId();
        }
        if (!bean.getDeptId().equals("")) {
          pendingEmpQuery = pendingEmpQuery + " AND EMP_DEPT=" + bean.getDeptId();
        }
        if (!bean.getDesgId().equals("")) {
          pendingEmpQuery = pendingEmpQuery + " AND EMP_RANK=" + bean.getDesgId();
        }
        if (!bean.getEmpId().equals("")) {
          pendingEmpQuery = pendingEmpQuery + " AND EMP_ID=" + bean.getEmpId();
        }

        pendingEmpQuery = pendingEmpQuery + "\tORDER BY EMP_ID";
        Object[][] pendingEmp = getSqlModel().getSingleResult(pendingEmpQuery);
        if ((pendingEmp != null) && (pendingEmp.length > 0)) {
          String[] empColumns = { "Employee Id", "Employee Name", "Division", "Branch", "Department", "Designation", "Assessment Date" };
          int[] empcellWidth = { 10, 30, 20, 20, 20, 20, 20 };
          int[] empcellAlign = { 0, 0, 0, 0, 0, 0, 1 };
          TableDataSet tdstable = new TableDataSet();
          tdstable.setHeader(empColumns);
          tdstable.setCellAlignment(empcellAlign);
          tdstable.setCellWidth(empcellWidth);
          tdstable.setBlankRowsBelow(1);
          tdstable.setHeaderTable(true);
          tdstable.setHeaderBorderDetail(3);
          tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
          tdstable.setData(pendingEmp);
          tdstable.setBorderDetail(3);
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

      }

      rg.process();
      if (reportPath.equals("")) {
        rg.createReport(response);
      }
      else
      {
        rg.saveReport(response);
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}