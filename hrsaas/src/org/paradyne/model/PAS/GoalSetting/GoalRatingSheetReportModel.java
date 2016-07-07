package org.paradyne.model.PAS.GoalSetting;

import com.itextpdf.text.BaseColor;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.GoalRatingSheetReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class GoalRatingSheetReportModel extends ModelBase
{
  static Logger logger = Logger.getLogger(GoalRatingSheetReportModel.class);

  public void genReport(GoalRatingSheetReport bean, HttpServletResponse response, HttpServletRequest request, String reportPath) {
    try {
      ReportGenerator rg = null;
      String reportType = bean.getReport();
      String title = "Goal Rating Sheet Report ";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "Goal Rating Sheet Report_" + Utility.getRandomNumber(1000);
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
        request.setAttribute("action", request.getContextPath() + "/pas/GoalRatingReport_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }

      filters = filters + "Goal Name : " + bean.getGoalName();
      String goalFrmDateQuery = "SELECT TO_CHAR(HRMS_GOAL_CONFIG.GOAL_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_GOAL_CONFIG.GOAL_TO_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_CONFIG  WHERE HRMS_GOAL_CONFIG.GOAL_ID=" + bean.getGoalId();
      Object[][] goalFrmDateObj = getSqlModel().getSingleResult(goalFrmDateQuery);
      filters = filters + "\t\t\t\t\t\t\t\t\t\t                                   From Date : " + String.valueOf(goalFrmDateObj[0][0]);
      filters = filters + "\t\t\t\t\t\t\t\t\t\t\t\t                       \t \t To Date : " + String.valueOf(goalFrmDateObj[0][1]);

      if ((bean.getEmpName() != null) && (!bean.getEmpName().equals(""))) {
        whereClause = whereClause + "AND HRMS_GOAL_EMP_HDR.EMP_ID=" + bean.getEmpId();
        filters = filters + "\t\t\t\t\t\t\t\t\t\t                              Employee Name : " + bean.getEmpToken() + "-" + bean.getEmpName();
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

      int numbAssesmentDet = 0;
      int checkCount = 0;
      String empdetails = "SELECT EMP_TOKEN ||'#'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME FROM HRMS_GOAL_EMP_HDR INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) INNER JOIN HRMS_GOAL_CONFIG\tON( HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID) WHERE HRMS_GOAL_EMP_HDR.GOAL_ID= " + 
        bean.getGoalId();

      empdetails = empdetails + whereClause;
      empdetails = empdetails + " ORDER BY HRMS_GOAL_EMP_HDR.EMP_ID";
      Object[][] empdetailsObj = getSqlModel().getSingleResult(empdetails);
      Object[][] numAssesmentObj = (Object[][])null;
      if ((empdetailsObj != null) && (empdetailsObj.length > 0)) {
        String numAssesment = "SELECT GOAL_ASSESSMENT_DATE,MULTIPLE_RATING_OPTION FROM HRMS_GOAL_ASSESSMENT_CONFIG INNER JOIN HRMS_GOAL_CONFIG ON  HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ID=HRMS_GOAL_CONFIG.GOAL_ID WHERE HRMS_GOAL_CONFIG.GOAL_ID= " + 
          bean.getGoalId();
        numAssesmentObj = getSqlModel().getSingleResult(numAssesment);
        if (numAssesmentObj != null) {
          numbAssesmentDet = numAssesmentObj.length;
          checkCount = numbAssesmentDet;
        }

        String[] columns = new String[checkCount + 3];
        int[] colspan = new int[checkCount + 3];
        int[] GoalcellAlign = new int[checkCount + 3];

        boolean[] Goalcellwrap = new boolean[checkCount + 3];

        int chkcnt = 0;
        columns[chkcnt] = "Emp.Id";
        GoalcellAlign[chkcnt] = 0;

        colspan[chkcnt] = 1;
        Goalcellwrap[chkcnt] = true;
        chkcnt++;
        columns[chkcnt] = "Emp.Name";
        GoalcellAlign[chkcnt] = 0;

        colspan[chkcnt] = 1;
        Goalcellwrap[chkcnt] = true;
        chkcnt++;
        for (int a = 0; a < numbAssesmentDet; a++) {
          columns[chkcnt] = ("Assessment " + (a + 1));
          GoalcellAlign[chkcnt] = 0;

          colspan[chkcnt] = 3;
          Goalcellwrap[chkcnt] = true;
          chkcnt++;
        }

        columns[chkcnt] = "Final Rating";
        GoalcellAlign[chkcnt] = 0;

        colspan[chkcnt] = 1;
        Goalcellwrap[chkcnt] = true;
        chkcnt++;

        checkCount = numbAssesmentDet * 3;
        String[] columnsDet = new String[checkCount + 3];
        int[] GoalcellAlignDet = new int[checkCount + 3];
        int[] GoalcellWidthDet = new int[checkCount + 3];
        boolean[] GoalcellwrapDet = new boolean[checkCount + 3];
        chkcnt = 0;
        columnsDet[chkcnt] = " ";
        GoalcellAlignDet[chkcnt] = 0;
        GoalcellWidthDet[chkcnt] = 20;
        GoalcellwrapDet[chkcnt] = true;
        chkcnt++;
        columnsDet[chkcnt] = "";
        GoalcellAlignDet[chkcnt] = 0;
        GoalcellWidthDet[chkcnt] = 35;
        GoalcellwrapDet[chkcnt] = true;
        chkcnt++;
        for (int a = 0; a < numbAssesmentDet; a++) {
          for (int b = 0; b < 3; b++) {
            if (b == 0) {
              columnsDet[chkcnt] = "Self";
              GoalcellAlignDet[chkcnt] = 0;
              GoalcellWidthDet[chkcnt] = 35;
              GoalcellwrapDet[chkcnt] = true;
              chkcnt++;
            }
            if (b == 1) {
              columnsDet[chkcnt] = "Manager";
              GoalcellAlignDet[chkcnt] = 0;
              GoalcellWidthDet[chkcnt] = 35;
              GoalcellwrapDet[chkcnt] = true;
              chkcnt++;
            }
            if (b == 2) {
              columnsDet[chkcnt] = "Total";
              GoalcellAlignDet[chkcnt] = 0;
              GoalcellWidthDet[chkcnt] = 35;
              GoalcellwrapDet[chkcnt] = true;
              chkcnt++;
            }
          }
        }
        columnsDet[chkcnt] = "";
        GoalcellAlignDet[chkcnt] = 0;
        GoalcellWidthDet[chkcnt] = 35;
        GoalcellwrapDet[chkcnt] = true;
        chkcnt++;

        int rows = empdetailsObj.length;
        int cols = checkCount + 3;

        Object[][] nullObject = new Object[rows][cols];

        String EmpAssesData = "SELECT EMP_TOKEN||'#'|| EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME, nvl(GOAL_REVIEW_SELF_RATING,0), nvl(GOAL_REVIEW_MGR_RATING,0), nvl(GOAL_REVIEW_FINAL_RATING,0),nvl(FINAL_RATING,0) FROM HRMS_GOAL_EMP_HDR INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID)  INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR\tON( HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) INNER JOIN HRMS_GOAL_CONFIG\tON( HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)  WHERE HRMS_GOAL_EMP_HDR.GOAL_ID=" + 
          bean.getGoalId();

        EmpAssesData = EmpAssesData + whereClause;
        EmpAssesData = EmpAssesData + " ORDER BY HRMS_GOAL_EMP_HDR.EMP_ID";
        String queryRow = " SELECT DISTINCT EMP_TOKEN||'#'|| EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID FROM HRMS_GOAL_EMP_ASSESSMENT_HDR  INNER JOIN HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID)  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID)  INNER JOIN HRMS_GOAL_CONFIG\tON( HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)  WHERE HRMS_GOAL_EMP_HDR.GOAL_ID = " + 
          bean.getGoalId() + 
          "  ORDER BY HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID";

        Object[][] rowLength = getSqlModel().getSingleResult(queryRow);
        Object[][] finalObject = new Object[rowLength.length][cols];
        HashMap map = getSqlModel().getSingleResultMap(EmpAssesData, 0, 2);
        for (int i = 0; i < rowLength.length; i++) {
          int a = 0;
          Object[][] goalDataAssesDateWise = 
            (Object[][])map
            .get(String.valueOf(rowLength[i][0]));
          if ((goalDataAssesDateWise != null) && (goalDataAssesDateWise.length > 0)) {
            for (int j = 0; j < goalDataAssesDateWise.length; j++)
            {
              for (int k = 0; k < goalDataAssesDateWise[0].length - 1; k++) {
                if ((k == 0) && (j == 0)) {
                  String empTokenName = String.valueOf(goalDataAssesDateWise[j][k]);
                  String[] empTokenNameArr = empTokenName.split("#");
                  finalObject[i][a] = empTokenNameArr[0];
                  finalObject[i][(a + 1)] = empTokenNameArr[1];
                }
                if ((k != 0) && (j >= 1)) {
                  finalObject[i][(a + 2)] = goalDataAssesDateWise[j][k];
                  a++;
                }

                if ((k != 0) && (j == 0)) {
                  finalObject[i][(a + 2)] = goalDataAssesDateWise[j][k];
                  a++;
                }
              }
            }
          }

        }

        String finalRatingQuery = " SELECT DISTINCT HRMS_GOAL_EMP_HDR.EMP_ID,FINAL_RATING from HRMS_GOAL_EMP_HDR  INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR\tON( HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID)  INNER JOIN HRMS_GOAL_CONFIG\tON( HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)  WHERE HRMS_GOAL_EMP_HDR.GOAL_ID =" + 
          bean.getGoalId();

        finalRatingQuery = finalRatingQuery + whereClause;
        finalRatingQuery = finalRatingQuery + " ORDER BY HRMS_GOAL_EMP_HDR.EMP_ID";
        Object[][] finalRatingObj = getSqlModel()
          .getSingleResult(finalRatingQuery);

        for (int m = 0; m < finalRatingObj.length; m++) {
          for (int n = 0; n <= finalObject[0].length - 1; n++) {
            if (n == finalObject[0].length - 1) {
              finalObject[m][n] = finalRatingObj[m][1];
            }
          }
        }

        if ((finalObject != null) && (finalObject.length > 0))
        {
          TableDataSet tdstable = new TableDataSet();
          tdstable.setHeader(columns);
          tdstable.setData(null);

          tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
          tdstable.setCellAlignment(GoalcellAlign);
          tdstable.setCellWidth(GoalcellWidthDet);
          tdstable.setCellNoWrap(Goalcellwrap);
          tdstable.setHeaderColSpan(colspan);
          tdstable.setHeaderBorderDetail(3);
          tdstable.setBorderDetail(3);
          tdstable.setBorder(Boolean.valueOf(true));
          tdstable.setHeaderTable(true);
          rg.addTableToDoc(tdstable);

          TableDataSet dataTable = new TableDataSet();
          dataTable.setHeader(columnsDet);
          dataTable.setData(finalObject);

          dataTable.setHeaderBorderColor(new BaseColor(0, 255, 0));
          dataTable.setCellAlignment(GoalcellAlignDet);
          dataTable.setCellWidth(GoalcellWidthDet);
          tdstable.setCellNoWrap(GoalcellwrapDet);
          dataTable.setHeaderBorderDetail(3);
          dataTable.setBorderDetail(3);
          dataTable.setBorder(Boolean.valueOf(true));
          dataTable.setHeaderTable(true);
          dataTable.setBlankRowsBelow(1);
          rg.addTableToDoc(dataTable);
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