package org.paradyne.model.PAS.Competency;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.EmpCompitencyReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class EmpCompitencyReportModel extends ModelBase
{
  static Logger logger = Logger.getLogger(EmpCompitencyReportModel.class);

  public void genReport(EmpCompitencyReport bean, HttpServletResponse response, HttpServletRequest request, String reportPath)
  {
    try
    {
      ReportGenerator rg = null;
      String reportType = bean.getReport();
      String title = "Employee  Wise Competency Report ";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "Employee  Wise Competency Report_" + Utility.getRandomNumber(1000);
      rds.setFileName(fileName);

      rds.setReportName(title);
      rds.setPageSize("landscape");
      rds.setPageOrientation("landscape");
      rds.setUserEmpId(bean.getUserEmpId());
      rds.setReportType(reportType);
      int checkCount = 0;
      String EmpAssmentId = "";
      int numManagers = 0;
      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, 
          this.session, this.context, request);
      }
      else {
        logger.info("################# ATTACHMENT PATH #############" + 
          reportPath + fileName);
        rg = new ReportGenerator(rds, 
          reportPath, this.session, this.context, request);

        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);

        request.setAttribute("action", "/pas/EmpCompitencyReport_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }
      String query = " SELECT HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID, HRMS_COMP_CONFIG.COMP_ID,  HRMS_COMP_CONFIG.COMP_NAME, TO_CHAR(HRMS_COMP_CONFIG.COMP_FROM_DATE,'DD-MM-YYYY'),  TO_CHAR(HRMS_COMP_CONFIG.COMP_TO_DATE,'DD-MM-YYYY'), EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' ||   HRMS_EMP_OFFC.EMP_LNAME), NVL(HRMS_CENTER.CENTER_CITY,'  '),HRMS_RANK.RANK_ID,\tNVL(HRMS_DEPT.DEPT_NAME,' '),  TO_CHAR(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE,'DD-MM-YYYY'),HRMS_EMP_OFFC.EMP_ID,HRMS_RANK.RANK_NAME  FROM HRMS_COMP_CONFIG   INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID)  LEFT JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID ) INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID ) LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  \t  LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  \t LEFT JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) WHERE   HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID= " + 
        bean.getCompId();
      if ((bean.getEmpId() == null) || (bean.getEmpId().equals("")) || (bean.getEmpId().equals("null"))) {
        query = query + " ORDER BY HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE ";
      }
      else {
        query = query + " AND HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID =" + bean.getEmpId() + 
          " ORDER BY HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE";
      }
      Object[][] queryData = getSqlModel().getSingleResult(query);
      String dataQuery = "SELECT HRMS_COMP_CONFIG.COMP_NAME, TO_CHAR(HRMS_COMP_CONFIG.COMP_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(HRMS_COMP_CONFIG.COMP_TO_DATE,'DD-MM-YYYY')  FROM HRMS_COMP_CONFIG  WHERE  HRMS_COMP_CONFIG.COMP_ID= " + 
        bean.getCompId();
      Object[][] Data = getSqlModel().getSingleResult(dataQuery);
      String filter = "";
      filter = filter + " \n Competency Name : " + bean.getCompName();
      filter = filter + "\n Competency From  : " + String.valueOf(Data[0][1]);
      filter = filter + "\n Competency To:  " + String.valueOf(Data[0][2]);
      TableDataSet filterDate = new TableDataSet();
      filterDate.setData(new Object[][] { { filter } });
      filterDate.setCellAlignment(new int[1]);
      filterDate.setBodyFontStyle(0);
      filterDate.setCellWidth(new int[] { 100 });
      filterDate.setCellColSpan(new int[] { 12 });
      filterDate.setCellNoWrap(new boolean[] { true });
      filterDate.setBlankRowsBelow(1);
      filterDate.setBorder(Boolean.valueOf(false));
      rg.addTableToDoc(filterDate);
      if ((queryData != null) && (queryData.length > 0)) {
        for (int i = 0; i < queryData.length; i++) {
          String filters = "";
          filters = filters + "\nEmployee ID:  : " + 
            String.valueOf(queryData[i][5]) + 
            "                                   \t Employee Name: : " + 
            String.valueOf(queryData[i][6]);
          filters = filters + "\n Branch: " + String.valueOf(queryData[i][7]);
          filters = filters + "\nDesignation : " + 
            String.valueOf(queryData[i][12]);
          filters = filters + "\nDepartment: " + 
            String.valueOf(queryData[i][9]);
          rds.setTotalColumns(12);
          TableDataSet filterData = new TableDataSet();
          filterData.setData(new Object[][] { { filters } });
          filterData.setCellAlignment(new int[1]);
          filterData.setBodyFontStyle(0);
          filterData.setCellWidth(new int[] { 100 });
          filterData.setCellColSpan(new int[] { 12 });
          filterData.setCellNoWrap(new boolean[] { true });
          filterData.setBlankRowsBelow(1);
          filterData.setBorder(Boolean.valueOf(false));
          rg.addTableToDoc(filterData);
          String queryApp = "SELECT DISTINCT COMP_ASSESER_ID, EMP_FNAME||'  ' || EMP_LNAME  FROM HRMS_COMP_EMP_ASSESSMENT_DTL   INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID)  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
            String.valueOf(queryData[i][0]) + "  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + queryData[i][11] + " ORDER BY COMP_ASSESER_ID";
          Object[][] totApproverObj = getSqlModel().getSingleResult(queryApp);
          int totApplength = totApproverObj.length;
          Object[][] creditheadtitle = new Object[1][1];
          creditheadtitle[0][0] = Utility.checkNull(String.valueOf(queryData[i][10]));
          TableDataSet AssHeaderData = new TableDataSet();
          AssHeaderData.setData(new Object[][] { { "Assessment Date: " + 
            String.valueOf(queryData[i][10]) } });

          AssHeaderData.setCellAlignment(new int[1]);
          AssHeaderData.setCellWidth(new int[] { 100 });
          AssHeaderData.setBodyFontStyle(1);
          AssHeaderData.setCellColSpan(new int[] { totApplength * 3 + 6 });

          AssHeaderData.setBorderDetail(0);
          AssHeaderData.setBlankRowsBelow(1);

          rg.addTableToDoc(AssHeaderData);
          String competencyQuery = "SELECT HRMS_COMP_SELF_ASSESMENT.COMP_ID, HRMS_COMPETENCY_HDR.COMPETENCY_TITLE ,COMP_CATEGORY,COMPETENCY_WEIGHTAGE, HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_LEVEL,  HRMS_COMP_SELF_ASSESMENT.COMP_RATING, HRMS_COMP_SELF_ASSESMENT.COMP_COMMENTS FROM HRMS_COMP_SELF_ASSESMENT   INNER JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)  INNER JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE )  LEFT JOIN HRMS_COMP_CATEGORIES ON (HRMS_COMPETENCY_ROLE.COMPETENCY_TYPE = COMP_CATEGORY_ID)  WHERE HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE=" + 
            String.valueOf(queryData[i][8]) + " AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID = " + String.valueOf(queryData[i][0]) + 
            " AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = " + queryData[i][11];
          Object[][] noOfCompObj = getSqlModel().getSingleResult(competencyQuery);
          HashMap competencyMap = getSqlModel().getSingleResultMap(competencyQuery, 0, 2);

          String approverQuery = "SELECT HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE,HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_RATING,NVL(COMP_DEV_PLAN,' ')||' - '|| TO_CHAR(COMP_DEV_TARGET_DATE,'DD-MM-YYYY'),HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_COMMENT  FROM HRMS_COMP_EMP_ASSESSMENT_DTL  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID)    LEFT JOIN HRMS_COMPETENCY_DEV_PLAN ON(HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_DEV_CODE=HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE)  WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
            String.valueOf(queryData[i][0]) + 
            " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + queryData[i][11] + 
            " ORDER BY HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE DESC";
          Object[][] noOfAppObj = getSqlModel().getSingleResult(approverQuery);
          HashMap approverMap = getSqlModel().getSingleResultMap(approverQuery, 0, 2);

          String numberOfApproverQuery = " SELECT DISTINCT HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID  FROM HRMS_COMP_EMP_ASSESSMENT_DTL   WHERE HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = " + 
            String.valueOf(queryData[i][0]) + " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID = " + queryData[i][11];
          Object[][] numberOfApproverObj = getSqlModel().getSingleResult(numberOfApproverQuery);
          if ((noOfCompObj != null) && (noOfCompObj.length > 0))
          {
            int totalRows = 0;

            int totalapprovers = 0;
            if ((noOfCompObj != null) && (noOfCompObj.length > 0))
            {
              totalRows = noOfCompObj.length;
            }
            if ((numberOfApproverObj != null) && (numberOfApproverObj.length > 0)) {
              totalapprovers = numberOfApproverObj.length;
            }

            Object[][] compObj = new Object[totalRows][6];

            Object[][] approverObj = new Object[totalRows][totalapprovers * 3];

            int hdrchkcnt = 0;
            String[] columns = new String[totalapprovers * 3 + 6];
            int[] cellAlign = new int[totalapprovers * 3 + 6];
            int[] cellWidth = new int[totalapprovers * 3 + 6];
            boolean[] cellwrap = new boolean[totalapprovers * 3 + 6];
            columns[hdrchkcnt] = "Competency";
            cellAlign[hdrchkcnt] = 0;
            cellWidth[hdrchkcnt] = 35;
            cellwrap[hdrchkcnt] = false;
            hdrchkcnt++;
            columns[hdrchkcnt] = "Competency Category";
            cellAlign[hdrchkcnt] = 0;
            cellWidth[hdrchkcnt] = 35;
            cellwrap[hdrchkcnt] = false;
            hdrchkcnt++;
            columns[hdrchkcnt] = "Competency Weightage";
            cellAlign[hdrchkcnt] = 2;
            cellWidth[hdrchkcnt] = 35;
            cellwrap[hdrchkcnt] = false;
            hdrchkcnt++;
            columns[hdrchkcnt] = "Proficiency Level";
            cellAlign[hdrchkcnt] = 2;
            cellWidth[hdrchkcnt] = 20;
            cellwrap[hdrchkcnt] = false;
            hdrchkcnt++;
            columns[hdrchkcnt] = "Self Rating";
            cellAlign[hdrchkcnt] = 2;
            cellWidth[hdrchkcnt] = 20;
            cellwrap[hdrchkcnt] = false;
            hdrchkcnt++;
            columns[hdrchkcnt] = "Self Comments";
            cellAlign[hdrchkcnt] = 0;
            cellWidth[hdrchkcnt] = 35;
            cellwrap[hdrchkcnt] = false;
            hdrchkcnt++;
            Object[][] finalObj = (Object[][])null;
            for (int a = 0; a < totApplength; a++) {
              for (int b = 0; b < 3; b++) {
                if (b == 0) {
                  columns[hdrchkcnt] = (String.valueOf(totApproverObj[a][1]) + " Rating");
                  cellAlign[hdrchkcnt] = 2;
                  cellWidth[hdrchkcnt] = 35;
                  cellwrap[hdrchkcnt] = false;
                  hdrchkcnt++;
                }
                if (b == 1) {
                  columns[hdrchkcnt] = "Development Plan with Target Date";
                  cellAlign[hdrchkcnt] = 1;
                  cellWidth[hdrchkcnt] = 35;
                  cellwrap[hdrchkcnt] = false;
                  hdrchkcnt++;
                }
                if (b != 2)
                  continue;
                columns[hdrchkcnt] = (String.valueOf(totApproverObj[a][1]) + " Comments ");
                cellAlign[hdrchkcnt] = 0;
                cellWidth[hdrchkcnt] = 35;
                cellwrap[hdrchkcnt] = false;
                hdrchkcnt++;
              }

            }

            if ((noOfCompObj != null) && (noOfCompObj.length > 0)) {
              for (int l = 0; l < noOfCompObj.length; l++)
              {
                Object[][] innerCompObj = (Object[][])null;
                try {
                  innerCompObj = (Object[][])competencyMap.get(String.valueOf(noOfCompObj[l][0]));
                  if ((innerCompObj != null) && (innerCompObj.length > 0))
                    for (int k = 1; k < innerCompObj[0].length; k++)
                      compObj[l][(k - 1)] = String.valueOf(innerCompObj[0][k]);
                }
                catch (Exception e)
                {
                  e.printStackTrace();
                }

                Object[][] innerObj = (Object[][])null;
                try {
                  innerObj = (Object[][])approverMap.get(String.valueOf(noOfCompObj[l][0]));
                  if ((innerObj != null) && (innerObj.length > 0)) {
                    int count = 0;
                    int noOfAppr = 1;
                    for (int j = 0; j < innerObj.length; j++)
                      for (int j2 = 1; j2 < innerObj[0].length; j2++)
                      {
                        approverObj[l][count] = String.valueOf(innerObj[j][j2]);
                        count++;
                      }
                  }
                }
                catch (Exception e) {
                  e.printStackTrace();
                }

              }

              if ((compObj != null) && (compObj.length > 0)) {
                finalObj = compObj;
              }
              if ((approverObj != null) && (approverObj.length > 0)) {
                finalObj = approverObj;
              }
              if ((compObj != null) && (compObj.length > 0) && (approverObj != null) && (approverObj.length > 0)) {
                finalObj = Utility.joinArrays(compObj, approverObj, 0, 0);
              }
            }
            TableDataSet tdstable = new TableDataSet();
            tdstable.setHeader(columns);
            tdstable.setData(finalObj);
            tdstable.setCellAlignment(cellAlign);
            tdstable.setCellWidth(cellWidth);
            tdstable.setCellNoWrap(cellwrap);
            tdstable.setBorderDetail(3);
            tdstable.setBlankRowsBelow(1);
            tdstable.setBorder(Boolean.valueOf(true));
            tdstable.setHeaderTable(true);
            tdstable.setHeaderBorderDetail(3);
            rg.addTableToDoc(tdstable);
          }
          else
          {
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
      else
      {
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