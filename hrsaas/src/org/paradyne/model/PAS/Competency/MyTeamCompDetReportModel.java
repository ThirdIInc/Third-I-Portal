package org.paradyne.model.PAS.Competency;

import com.itextpdf.text.BaseColor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.MyTeamCompDetReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class MyTeamCompDetReportModel extends ModelBase
{
  static Logger logger = Logger.getLogger(MyTeamCompDetReportModel.class);

  public void genReport(MyTeamCompDetReport bean, HttpServletResponse response, HttpServletRequest request, String reportPath) {
    try {
      ReportGenerator rg = null;
      String reportType = bean.getReport();
      String title = "My Team Competency Report ";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "My Team Competency Report_" + Utility.getRandomNumber(1000);
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
        request.setAttribute("action", request.getContextPath() + "/pas/MyTeamCompDetReport_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }

      filters = filters + "Competency  Name  : " + bean.getCompName();

      String goalFrmDateQuery = "SELECT TO_CHAR(HRMS_COMP_CONFIG.COMP_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_COMP_CONFIG.COMP_TO_DATE,'DD-MM-YYYY') FROM HRMS_COMP_CONFIG  WHERE HRMS_COMP_CONFIG.COMP_ID=" + bean.getCompId();
      Object[][] goalFrmDateObj = getSqlModel().getSingleResult(goalFrmDateQuery);
      filters = filters + "\n Competency From Date: " + String.valueOf(goalFrmDateObj[0][0]);
      filters = filters + "\n Competency To Date:  " + String.valueOf(goalFrmDateObj[0][1]);

      if ((bean.getDivisionName() != null) && (!bean.getDivisionName().equals(""))) {
        whereClause = whereClause + "AND HRMS_DIVISION.DIV_ID=" + bean.getDivisionId();
        filters = filters + "\n Division:" + bean.getDivisionName();
      }

      if ((bean.getDesgName() != null) && (!bean.getDesgName().equals(""))) {
        whereClause = whereClause + "AND HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE =" + bean.getDesgId();
        filters = filters + "\n Designation:" + bean.getDesgName();
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

      String reportingType = "SELECT COMP_REPORTING FROM HRMS_COMP_CONFIG WHERE HRMS_COMP_CONFIG.COMP_ID=" + bean.getCompId();
      Object[][] reportingTypeObj = getSqlModel().getSingleResult(reportingType);
      Object[][] empIdObj = (Object[][])null;
      String query = "";
      Object[][] queryData = (Object[][])null;
      if (reportingTypeObj != null) {
        query = "SELECT DISTINCT  HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_EMP_OFFC.EMP_FNAME||' '|| \t NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),RANK_NAME,HRMS_COMPETENCY_HDR.COMPETENCY_TITLE,COMP_CATEGORY,COMP_CATEGORY_WEIGHTAGE,COMP_ASSESMENT_LEVEL \t ,HRMS_COMPETENCY_DTL.COMPETENCY_LEVEL_DESC    FROM HRMS_COMP_SELF_ASSESMENT   LEFT JOIN HRMS_COMP_EMP_ASSESSMENT_DTL ON(HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID =HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID )   INNER JOIN  HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID)  \tLEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)   LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)   LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)  INNER JOIN HRMS_COMPETENCY_HDR ON(HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)  INNER JOIN HRMS_COMPETENCY_DTL ON(HRMS_COMPETENCY_DTL.COMPETENCY_CODE = HRMS_COMPETENCY_HDR.COMPETENCY_CODE   AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL=HRMS_COMPETENCY_DTL.COMPETENCY_LEVEL AND HRMS_COMP_SELF_ASSESMENT.COMP_ID = HRMS_COMPETENCY_DTL.COMPETENCY_CODE)  INNER JOIN HRMS_COMPETENCY_ROLE ON\t\t \t\t        \t\t(HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE  AND COMPETENCY_ROLE = HRMS_EMP_OFFC.EMP_RANK )\t INNER JOIN HRMS_COMP_ASSESMENT_HDR ON(HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID = HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID =HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID )  LEFT JOIN HRMS_COMP_CATEGORIES ON (HRMS_COMPETENCY_ROLE.COMPETENCY_TYPE = COMP_CATEGORY_ID)    WHERE  HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID = " + 
          bean.getUserEmpId() + " AND  HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID= " + bean.getCompId();
      }

      query = query + whereClause;

      queryData = getSqlModel().getSingleResult(query);

      if ((queryData != null) && (queryData.length > 0)) {
        Object[][] final_object = new Object[queryData.length][9];
        for (int j = 0; j < final_object.length; j++) {
          final_object[j][0] = Integer.valueOf(j + 1);
        }
        TableDataSet tdstable = new TableDataSet();
        tdstable.setHeader(new String[] { "Sr No", "Emp.Id", "Emp.Name", "Designation", 
          "Competency Name", "Competency Category", "Competency Weightage", "Proficiency Level", "Proficiency description" });
        for (int k = 0; k < queryData.length; k++) {
          for (int l = 0; l < queryData[0].length; l++) {
            final_object[k][(l + 1)] = queryData[k][l];
          }
        }
        tdstable.setData(final_object);

        tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
        tdstable.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 2, 2, 1 });
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
}