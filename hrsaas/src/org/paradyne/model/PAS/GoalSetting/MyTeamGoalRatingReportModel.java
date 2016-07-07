package org.paradyne.model.PAS.GoalSetting;

import com.itextpdf.text.BaseColor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.PAS.GoalSetting.MyTeamGoalRatingReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class MyTeamGoalRatingReportModel extends ModelBase
{
  public void getReport(MyTeamGoalRatingReport myTemGaolReport, HttpServletResponse response, HttpServletRequest request, String reportPath)
  {
    try
    {
      ReportGenerator rg = null;
      String reportType = myTemGaolReport.getReport();
      String title = "My Team Goal Rating Report";
      ReportDataSet rds = new ReportDataSet();
      String fileName = " My Team Goal Rating Report_" + Utility.getRandomNumber(1000);
      rds.setFileName(fileName);

      rds.setReportName(title);
      rds.setPageSize("A4");
      rds.setPageOrientation("landscape");
      rds.setUserEmpId(myTemGaolReport.getUserEmpId());
      rds.setReportType(reportType);
      rds.setTotalColumns(8);
      String filters = "";
      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, this.session, this.context, request);
      }
      else {
        rg = new ReportGenerator(rds, reportPath, this.session, this.context, request);

        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
        request.setAttribute("action", "/pas/MyTeamGoalRatingReport_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }
      filters = filters + "Goal Name : " + myTemGaolReport.getGoalName();

      String goalFrmDateQuery = "SELECT TO_CHAR(HRMS_GOAL_CONFIG.GOAL_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_GOAL_CONFIG.GOAL_TO_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_CONFIG  WHERE HRMS_GOAL_CONFIG.GOAL_ID=" + myTemGaolReport.getGoalId();
      Object[][] goalFrmDateObj = getSqlModel().getSingleResult(goalFrmDateQuery);
      filters = filters + "\n From Date : " + String.valueOf(goalFrmDateObj[0][0]);
      filters = filters + "\n To Date : " + String.valueOf(goalFrmDateObj[0][1]);

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

      String queryDes = "SELECT ROWNUM,EMP_TOKEN   ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), NVL(HRMS_DEPT.DEPT_NAME,' '),  NVL(HRMS_CENTER.CENTER_NAME,''), NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_CADRE.CADRE_NAME,' '), NVL(FINAL_RATING,'0')    FROM HRMS_GOAL_EMP_HDR  LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_GOAL_EMP_HDR.EMP_ID ) LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) LEFT JOIN HRMS_CADRE ON(HRMS_EMP_OFFC.EMP_CADRE =HRMS_CADRE.CADRE_ID) LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID\t= HRMS_EMP_OFFC.EMP_DIV) WHERE GOAL_ID =" + 
        myTemGaolReport.getGoalId() + " AND HRMS_GOAL_EMP_HDR.EMP_ID IN (SELECT HRMS_EMP_OFFC.EMP_ID " + 
        "FROM HRMS_EMP_OFFC " + 
        "WHERE EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_ID<>" + myTemGaolReport.getUserEmpId() + " " + 
        "CONNECT BY PRIOR  HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_OFFC.EMP_REPORTING_OFFICER " + 
        "START WITH HRMS_EMP_OFFC.EMP_ID=" + myTemGaolReport.getUserEmpId() + " )";
      if ((myTemGaolReport.getEmpId() != null) && (myTemGaolReport.getEmpId().length() > 0))
      {
        queryDes = queryDes + " AND HRMS_GOAL_EMP_HDR.EMP_ID = " + myTemGaolReport.getEmpId();
      }

      Object[][] data = getSqlModel().getSingleResult(queryDes);

      if ((data != null) && (data.length > 0)) {
        TableDataSet tdstable = new TableDataSet();
        tdstable.setHeader(new String[] { "Sr.No", "Employee Token", "Employee Name", 
          "Department", "Branch", "Designation", "Grade", "Final Score" });
        tdstable.setData(data);

        tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
        tdstable.setCellAlignment(new int[8]);
        tdstable.setCellWidth(new int[] { 7, 25, 25, 25, 25, 50, 25, 25 });
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
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}