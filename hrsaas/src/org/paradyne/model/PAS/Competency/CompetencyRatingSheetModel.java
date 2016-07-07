package org.paradyne.model.PAS.Competency;

import com.itextpdf.text.BaseColor;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompetencyRatingSheet;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class CompetencyRatingSheetModel extends ModelBase
{
  static Logger logger = Logger.getLogger(CompetencyRatingSheetModel.class);

  public void genReport(CompetencyRatingSheet bean, HttpServletResponse response, HttpServletRequest request, String reportPath)
  {
    try
    {
      ReportGenerator rg = null;
      String reportType = bean.getReport();
      String title = "Competency Rating Sheet ";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "Competency Rating Sheet_" + Utility.getRandomNumber(1000);
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
        request.setAttribute("action", request.getContextPath() + "/pas/CompetencyRatingSheet_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }

      String queryComp = " SELECT COMP_NAME,TO_CHAR(COMP_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(COMP_TO_DATE,'DD-MM-YYYY') FROM HRMS_COMP_CONFIG WHERE COMP_ID=" + bean.getCompId();
      Object[][] queryCompData = getSqlModel().getSingleResult(queryComp);

      filters = filters + "Competency Name : " + bean.getCompName();
      if ((queryCompData != null) && (queryCompData.length > 0)) {
        filters = filters + "\nCompetency From Date : " + String.valueOf(queryCompData[0][1]);
        filters = filters + "\n Competency To Date:  " + String.valueOf(queryCompData[0][2]);
      }

      if ((bean.getDivisionName() != null) && (!bean.getDivisionName().equals(""))) {
        whereClause = whereClause + "AND HRMS_EMP_OFFC.EMP_DIV=" + bean.getDivisionId();
        filters = filters + "\n Division: : " + bean.getDivisionName();
      }
      if ((bean.getDesgName() != null) && (!bean.getDesgName().equals(""))) {
        whereClause = whereClause + "AND HRMS_EMP_OFFC.EMP_RANK=" + bean.getDesgId();
        filters = filters + "\n Designation: " + bean.getDesgName();
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
      String empdetails = " SELECT EMP_TOKEN ||'#'||  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME  FROM HRMS_COMP_ASSESMENT_HDR INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMP_CONFIG\tON( HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID) WHERE HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID=" + 
        bean.getCompId();

      empdetails = empdetails + whereClause;
      empdetails = empdetails + " ORDER BY HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID";
      Object[][] empdetailsObj = getSqlModel().getSingleResult(empdetails);
      Object[][] numAssesmentObj = (Object[][])null;
      if ((empdetailsObj != null) && (empdetailsObj.length > 0)) {
        String numAssesment = " SELECT COMP_ASSESSMENT_ID, TO_CHAR(COMP_ASSESSMENT_DATE,'Month-YY') FROM HRMS_COMP_ASSESSMENT_CONFIG  WHERE HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID=" + 
          bean.getCompId();
        numAssesmentObj = getSqlModel().getSingleResult(numAssesment);
        if (numAssesmentObj != null) {
          numbAssesmentDet = numAssesmentObj.length;
          checkCount = numbAssesmentDet;
        }

        String[] columns = new String[checkCount + 4];
        int[] colspan = new int[checkCount + 4];
        int[] GoalcellAlign = new int[checkCount + 4];

        boolean[] Goalcellwrap = new boolean[checkCount + 4];

        int chkcnt = 0;
        columns[chkcnt] = "Sr. No.";
        GoalcellAlign[chkcnt] = 0;

        colspan[chkcnt] = 1;
        Goalcellwrap[chkcnt] = true;
        chkcnt++;
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
          columns[chkcnt] = String.valueOf(numAssesmentObj[a][1]);
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
        String[] columnsDet = new String[checkCount + 4];
        int[] GoalcellAlignDet = new int[checkCount + 4];
        int[] GoalcellWidthDet = new int[checkCount + 4];
        boolean[] GoalcellwrapDet = new boolean[checkCount + 4];
        chkcnt = 0;
        columnsDet[chkcnt] = " ";
        GoalcellAlignDet[chkcnt] = 0;
        GoalcellWidthDet[chkcnt] = 5;
        GoalcellwrapDet[chkcnt] = true;
        chkcnt++;
        columnsDet[chkcnt] = " ";
        GoalcellAlignDet[chkcnt] = 0;
        GoalcellWidthDet[chkcnt] = 10;
        GoalcellwrapDet[chkcnt] = true;
        chkcnt++;
        columnsDet[chkcnt] = "";
        GoalcellAlignDet[chkcnt] = 0;
        GoalcellWidthDet[chkcnt] = 15;
        GoalcellwrapDet[chkcnt] = true;
        chkcnt++;
        for (int a = 0; a < numbAssesmentDet; a++) {
          for (int b = 0; b < 3; b++) {
            if (b == 0) {
              columnsDet[chkcnt] = "Self";
              GoalcellAlignDet[chkcnt] = 0;
              GoalcellWidthDet[chkcnt] = 5;
              GoalcellwrapDet[chkcnt] = true;
              chkcnt++;
            }
            if (b == 1) {
              columnsDet[chkcnt] = "Manager";
              GoalcellAlignDet[chkcnt] = 0;
              GoalcellWidthDet[chkcnt] = 5;
              GoalcellwrapDet[chkcnt] = true;
              chkcnt++;
            }
            if (b == 2) {
              columnsDet[chkcnt] = "Total";
              GoalcellAlignDet[chkcnt] = 0;
              GoalcellWidthDet[chkcnt] = 10;
              GoalcellwrapDet[chkcnt] = true;
              chkcnt++;
            }
          }
        }
        columnsDet[chkcnt] = "";
        GoalcellAlignDet[chkcnt] = 0;
        GoalcellWidthDet[chkcnt] = 20;
        GoalcellwrapDet[chkcnt] = true;
        chkcnt++;

        int rows = empdetailsObj.length;
        int cols = checkCount + 4;

        HashMap map = null;

        String EmpAssesData = " SELECT EMP_TOKEN||'#'|| EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME,NVL(COMP_REVIEW_SELF_RATING,0), NVL(COMP_REVIEW_MGR_RATING,0),NVL(COMP_REVIEW_FINAL_RATING,0),NVL(FINAL_SCORE,0)  FROM    HRMS_COMP_EMP_ASSESSMENT_HDR   INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  INNER JOIN HRMS_COMP_EMP ON (HRMS_COMP_EMP.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID AND HRMS_COMP_EMP.COMP_EMP_ID= HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID=" + 
          bean.getCompId();

        EmpAssesData = EmpAssesData + whereClause;
        EmpAssesData = EmpAssesData + " ORDER BY HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID ";
        String queryRow = " SELECT DISTINCT EMP_TOKEN||'#'|| EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME,HRMS_COMP_EMP_ASSESSMENT_HDR .EMP_ID FROM HRMS_COMP_EMP_ASSESSMENT_HDR  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  INNER JOIN HRMS_COMP_CONFIG\tON( HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID)   INNER JOIN HRMS_COMP_EMP ON (HRMS_COMP_EMP.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID AND HRMS_COMP_EMP.COMP_EMP_ID= HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = " + 
          bean.getCompId() + 
          " ORDER BY HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID";

        Object[][] rowLength = getSqlModel().getSingleResult(queryRow);
        Object[][] finalObject = new Object[rowLength.length][cols];

        map = getSqlModel().getSingleResultMap(EmpAssesData, 0, 2);
        if ((map != null) && (map.size() > 0))
        {
          for (int i = 0; i < rowLength.length; i++) {
            int a = 1;
            Object[][] goalDataAssesDateWise = 
              (Object[][])map
              .get(String.valueOf(rowLength[i][0]));
            if ((goalDataAssesDateWise == null) || (goalDataAssesDateWise.length <= 0))
            {
              continue;
            }
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
                  finalObject[i][(a + 2)] = Utility.twoDecimals(Double.parseDouble(String.valueOf(goalDataAssesDateWise[j][k])));
                  a++;
                }

                if ((k != 0) && (j == 0)) {
                  finalObject[i][(a + 2)] = Utility.twoDecimals(Double.parseDouble(String.valueOf(goalDataAssesDateWise[j][k])));
                  a++;
                }

              }

            }

          }

        }

        String finalRatingQuery = " SELECT DISTINCT  COMP_EMP_ID, NVL(FINAL_SCORE,0) FROM HRMS_COMP_EMP_ASSESSMENT_HDR  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  INNER JOIN HRMS_COMP_CONFIG\tON( HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID)   INNER JOIN HRMS_COMP_EMP ON (HRMS_COMP_EMP.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID AND HRMS_COMP_EMP.COMP_EMP_ID= HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID) WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID =" + 
          bean.getCompId();

        finalRatingQuery = finalRatingQuery + whereClause;
        Object[][] finalRatingObj = getSqlModel()
          .getSingleResult(finalRatingQuery);

        if ((finalObject != null) && (finalObject.length > 0)) {
          for (int m = 0; m < finalRatingObj.length; m++) {
            for (int n = 0; n <= finalObject[0].length - 1; n++) {
              if (n == finalObject[0].length - 1) {
                finalObject[m][n] = Utility.twoDecimals(Double.parseDouble(String.valueOf(finalRatingObj[m][1])));
              }
            }
          }
          for (int l = 0; l < finalObject.length; l++) {
            finalObject[l][0] = Integer.valueOf(l + 1);
          }
        }

        if ((finalObject != null) && (finalObject.length > 0) && (finalObject[0][0] != null))
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
          dataTable.setCellNoWrap(GoalcellwrapDet);
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
}