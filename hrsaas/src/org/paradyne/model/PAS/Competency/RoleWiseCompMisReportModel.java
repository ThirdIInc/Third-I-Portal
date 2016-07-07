package org.paradyne.model.PAS.Competency;

import com.itextpdf.text.BaseColor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.RoleWiseCompMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class RoleWiseCompMisReportModel extends ModelBase
{
  static Logger logger = Logger.getLogger(RoleWiseCompMisReportModel.class);

  public void genReport(RoleWiseCompMisReport bean, HttpServletResponse response, HttpServletRequest request, String reportPath) {
    try {
      ReportGenerator rg = null;
      String reportType = bean.getReport();
      String title = "Role Wise Competency Master ";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "Role Wise Competency Master_" + Utility.getRandomNumber(1000);
      rds.setFileName(fileName);

      rds.setReportName(title);
      rds.setPageSize("A4");
      rds.setPageOrientation("landscape");
      rds.setUserEmpId(bean.getUserEmpId());
      rds.setReportType(reportType);
      String filters = "";
      String whereClause = "";

      int maxLevels = 0;
      String levelQuery = "SELECT MAX(COMPETENCY_LEVEL) FROM HRMS_COMPETENCY_DTL";
      Object[][] levelQueryObj = getSqlModel().getSingleResult(levelQuery);
      if ((levelQueryObj != null) && (levelQueryObj.length > 0)) {
        maxLevels = Integer.parseInt(String.valueOf(levelQueryObj[0][0]));
        maxLevels++;
      }

      rds.setTotalColumns(maxLevels + 6);
      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, this.session, this.context, request);
      } else {
        logger.info("################# ATTACHMENT PATH #############" + reportPath + fileName);
        rg = new ReportGenerator(rds, reportPath, this.session, this.context, request);

        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
        request.setAttribute("action", request.getContextPath() + "/pas/RoleWiseCompMisReport_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }
      if ((bean.getRole() == null) || (bean.getRole().equals(""))) {
        filters = filters + "Designation/Grade/Role:  All";
      } else {
        whereClause = whereClause + " AND HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE = " + bean.getRoleId();
        filters = filters + "Designation/Grade/Role: " + bean.getRole();
      }

      if ((bean.getCompName() != null) && (!bean.getCompName().equals(""))) {
        whereClause = whereClause + " AND  HRMS_COMPETENCY_ROLE.COMPETENCY_CODE =" + bean.getCompId();
        filters = filters + "\n\n Competency Name: : " + bean.getCompName();
      }
      if ((bean.getCompCatagory() != null) && (!bean.getCompCatagory().equals(""))) {
        whereClause = whereClause + "AND HRMS_COMP_CATEGORIES.COMP_CATEGORY_ID = " + bean.getCompCatcode();
        filters = filters + "\n\n Competency Category : " + bean.getCompCatagory();
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

      Object[][] RoleQueryObj = (Object[][])null;
      Object[][] finalDataObject = (Object[][])null;
      Object[][] levelObj = (Object[][])null;

      String RoleQuery = "SELECT HRMS_COMPETENCY_ROLE.COMPETENCY_CODE,RANK_NAME,COMPETENCY_TITLE,COMP_CATEGORY,COMPETENCY_WEIGHTAGE, COMPETENCY_LEVEL  FROM HRMS_COMPETENCY_ROLE  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE)  LEFT JOIN HRMS_COMP_CATEGORIES ON (COMPETENCY_TYPE = COMP_CATEGORY_ID)   LEFT JOIN HRMS_RANK ON( COMPETENCY_ROLE = RANK_ID)   WHERE 1=1";

      RoleQuery = RoleQuery + whereClause;
      RoleQuery = RoleQuery + " ORDER BY COMPETENCY_ROLE,HRMS_COMPETENCY_ROLE.COMPETENCY_CODE";
      RoleQueryObj = getSqlModel().getSingleResult(RoleQuery);
      if ((RoleQueryObj != null) && (RoleQueryObj.length > 0)) {
        finalDataObject = new Object[RoleQueryObj.length][6];
        levelObj = new Object[RoleQueryObj.length][maxLevels];
        int d = 0;
        for (int i = 0; i < RoleQueryObj.length; i++) {
          int a = 0;
          for (int j = 0; j < RoleQueryObj[0].length; j++) {
            if (j == 0) {
              finalDataObject[i][a] = Integer.valueOf(i + 1);
              a++;
              String levelDesc = "SELECT COMPETENCY_TITLE||' - '||COMPETENCY_LEVEL_DESC FROM HRMS_COMPETENCY_DTL WHERE COMPETENCY_CODE =" + RoleQueryObj[i][j];
              Object[][] levelDescObj = getSqlModel().getSingleResult(levelDesc);

              for (int b = 0; b < levelDescObj.length; b++) {
                levelObj[d][b] = levelDescObj[b][0];
              }

              d++;
            }
            else if (j <= 5) {
              finalDataObject[i][a] = RoleQueryObj[i][j];
              a++;
            }

          }

        }

      }

      finalDataObject = Utility.joinArrays(finalDataObject, levelObj, 0, 0);

      String[] columns = new String[maxLevels + 6];
      int[] cellAlign = new int[maxLevels + 6];
      int[] cellWidth = new int[maxLevels + 6];
      boolean[] cellwrap = new boolean[maxLevels + 6];

      int chkcnt = 0;
      columns[chkcnt] = "Sr. No";
      cellAlign[chkcnt] = 0;
      cellWidth[chkcnt] = 40;
      cellwrap[chkcnt] = true;
      chkcnt++;
      columns[chkcnt] = "Designation";
      cellAlign[chkcnt] = 0;
      cellWidth[chkcnt] = 40;
      cellwrap[chkcnt] = true;
      chkcnt++;
      columns[chkcnt] = "Competency Name";
      cellAlign[chkcnt] = 0;
      cellWidth[chkcnt] = 35;
      cellwrap[chkcnt] = true;
      chkcnt++;
      columns[chkcnt] = "Competency Category";
      cellAlign[chkcnt] = 0;
      cellWidth[chkcnt] = 35;
      cellwrap[chkcnt] = true;
      chkcnt++;
      columns[chkcnt] = "Competency Weightage";
      cellAlign[chkcnt] = 0;
      cellWidth[chkcnt] = 35;
      cellwrap[chkcnt] = true;
      chkcnt++;
      columns[chkcnt] = "Proficiency Level";
      cellAlign[chkcnt] = 2;
      cellWidth[chkcnt] = 35;
      cellwrap[chkcnt] = true;
      chkcnt++;
      for (int k = 0; k < maxLevels; k++) {
        columns[chkcnt] = ("Level" + (k + 1));
        cellAlign[chkcnt] = 0;
        cellWidth[chkcnt] = 40;
        cellwrap[chkcnt] = true;
        chkcnt++;
      }
      TableDataSet dataTable = new TableDataSet();
      dataTable.setHeader(columns);
      dataTable.setData(finalDataObject);
      dataTable.setHeaderLines(true);
      dataTable.setHeaderBorderColor(new BaseColor(0, 255, 0));
      dataTable.setCellAlignment(cellAlign);
      dataTable.setCellWidth(cellWidth);
      dataTable.setHeaderBorderDetail(3);
      dataTable.setBorderDetail(3);
      dataTable.setBorder(Boolean.valueOf(true));
      dataTable.setHeaderTable(true);
      rg.addTableToDoc(dataTable);

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