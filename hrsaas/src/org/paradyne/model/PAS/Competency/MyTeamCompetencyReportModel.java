package org.paradyne.model.PAS.Competency;

import com.itextpdf.text.BaseColor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.PAS.Competency.MyTeamCompetencyReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class MyTeamCompetencyReportModel extends ModelBase
{
  public void getReport(MyTeamCompetencyReport myTeamCompReport, HttpServletResponse response, HttpServletRequest request, String reportPath)
  {
    try
    {
      ReportGenerator rg = null;
      String reportType = myTeamCompReport.getReport();
      String title = "My Team Competency Rating Report";
      ReportDataSet rds = new ReportDataSet();
      String fileName = " My Team Competency Rating Report_" + Utility.getRandomNumber(1000);
      rds.setFileName(fileName);

      rds.setReportName(title);

      rds.setPageOrientation("landscape");
      rds.setUserEmpId(myTeamCompReport.getUserEmpId());
      rds.setReportType(reportType);
      rds.setTotalColumns(8);

      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, this.session, this.context, request);
      }
      else {
        rg = new ReportGenerator(rds, reportPath, this.session, this.context, request);

        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
        request.setAttribute("action", request.getContextPath() + "/pas/MyTeamCompReport_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }

      String queryDes = "SELECT ROWNUM,EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), NVL(HRMS_DEPT.DEPT_NAME,' '), NVL(HRMS_CENTER.CENTER_NAME,''), NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_CADRE.CADRE_NAME,' '), NVL(TO_CHAR(FINAL_SCORE,9999999999990.99),'0')  FROM HRMS_COMP_EMP LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP.COMP_EMP_ID )  LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  LEFT JOIN HRMS_CADRE ON(HRMS_EMP_OFFC.EMP_CADRE=HRMS_CADRE.CADRE_ID)  LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID\t= HRMS_EMP_OFFC.EMP_DIV)  WHERE HRMS_COMP_EMP.COMP_ID = " + 
        myTeamCompReport.getCompId() + " AND HRMS_COMP_EMP.COMP_EMP_ID IN ( SELECT HRMS_EMP_OFFC.EMP_ID " + 
        "FROM HRMS_EMP_OFFC " + 
        "WHERE EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_ID<>" + myTeamCompReport.getUserEmpId() + " " + 
        "CONNECT BY PRIOR  HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_OFFC.EMP_REPORTING_OFFICER " + 
        "START WITH HRMS_EMP_OFFC.EMP_ID=" + myTeamCompReport.getUserEmpId() + "  )";
      if ((myTeamCompReport.getEmpId() != null) && (myTeamCompReport.getEmpId().length() > 0))
      {
        queryDes = queryDes + " AND HRMS_COMP_EMP.COMP_EMP_ID = " + myTeamCompReport.getEmpId();
      }

      Object[][] data = getSqlModel().getSingleResult(queryDes);

      if ((data != null) && (data.length > 0)) {
        TableDataSet tdstable = new TableDataSet();
        tdstable.setHeader(new String[] { "Sr.No", "Employee Token", "Employee Name", 
          "Department", "Branch", "Designation", "Grade", "Final Score" });
        tdstable.setData(data);
        tdstable.setHeaderLines(true);
        tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
        tdstable.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 0, 0, 1 });
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