package org.paradyne.model.PAS.Competency;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompDevPlanMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class CompDevPlanMisReportModel extends ModelBase
{
  static Logger logger = Logger.getLogger(CompDevPlanMisReportModel.class);

  public void genReport(CompDevPlanMisReport bean, HttpServletResponse response, HttpServletRequest request, String reportPath)
  {
    try
    {
      ReportGenerator rg = null;
      String reportType = bean.getReport();
      String title = "Competency Development Plan Report";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "Competency Development Plan Report_" + Utility.getRandomNumber(1000);
      rds.setFileName(fileName);

      rds.setReportName(title);

      rds.setPageSize("landscape");
      rds.setPageOrientation("landscape");
      rds.setUserEmpId(bean.getUserEmpId());
      rds.setReportType(reportType);

      int checkCount = 0;
      String whereClause = "";
      String filters = "";

      String queryComp = " SELECT COMP_NAME, TO_CHAR(COMP_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(COMP_TO_DATE,'DD-MM-YYYY') FROM HRMS_COMP_CONFIG WHERE COMP_ID=" + bean.getCompId();
      Object[][] queryCompData = getSqlModel().getSingleResult(queryComp);

      filters = filters + "Competency Name : " + bean.getCompName();
      if ((queryCompData != null) && (queryCompData.length > 0)) {
        filters = filters + "\nComptency From  : " + String.valueOf(queryCompData[0][1]);
        filters = filters + "\n Competency To:  " + String.valueOf(queryCompData[0][2]);
      }

      if ((bean.getDivisionName() != null) && (!bean.getDivisionName().equals(""))) {
        whereClause = " AND OFFC1.EMP_DIV = " + bean.getDivisionId();
        filters = filters + "\n Division: : " + bean.getDivisionName();
      }
      if ((bean.getDevPlan() != null) && (!bean.getDevPlan().equals(""))) {
        whereClause = whereClause + " AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_DEV_CODE= " + bean.getDevPlanId();
        filters = filters + "\n Development Plan: " + bean.getDevPlan();
      }

      int totManagers = 0;
      String totalManagers = "SELECT MAX(COMP_ASSESSER_LEVEL) FROM  HRMS_COMP_EMP_ASSESSMENT_HDR WHERE  COMP_ID=" + bean.getCompId();
      Object[][] totalManagersObj = getSqlModel().getSingleResult(totalManagers);
      if ((totalManagersObj != null) && (totalManagers.length() > 0)) {
        totManagers = Integer.parseInt(String.valueOf(totalManagersObj[0][0]));
        checkCount = totManagers * 4;
      }

      rds.setTotalColumns(checkCount + 5);
      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, 
          this.session, this.context, request);
      } else {
        logger.info("################# ATTACHMENT PATH #############" + 
          reportPath + fileName);
        rg = new ReportGenerator(rds, 
          reportPath, this.session, this.context, request);

        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
        request.setAttribute("action", request.getContextPath() + "/pas/CompDevPlanMisReport_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }
      TableDataSet filterData = new TableDataSet();
      filterData.setData(new Object[][] { { filters } });
      filterData.setCellAlignment(new int[1]);
      filterData.setBodyFontStyle(0);
      filterData.setCellWidth(new int[] { 100 });
      filterData.setCellColSpan(new int[] { checkCount + 5 });
      filterData.setCellNoWrap(new boolean[] { true });
      filterData.setBlankRowsBelow(1);
      filterData.setBorder(Boolean.valueOf(false));
      rg.addTableToDoc(filterData);

      HashMap map = null;
      String query = "SELECT COMP_ASSESSMENT_ID,OFFC1.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||OFFC1.EMP_FNAME||' '||NVL(OFFC1.EMP_MNAME,' ')|| ' ' ||   OFFC1.EMP_LNAME) AS EMP_NAME, HRMS_RANK.RANK_NAME,COMPETENCY_TITLE,OFFC2.EMP_FNAME||' '||OFFC2.EMP_LNAME,COMP_DEV_PLAN ,COMP_DEV_TARGET_DATE FROM HRMS_COMP_EMP_ASSESSMENT_DTL INNER JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID=HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID) INNER JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID=HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID)  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE)  LEFT JOIN HRMS_COMPETENCY_DEV_PLAN ON(HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_DEV_CODE=HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE)  LEFT JOIN HRMS_TITLE  ON(OFFC1.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_RANK ON(OFFC1.EMP_RANK=HRMS_RANK.RANK_ID)  INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID =HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID )  WHERE   HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID= " + 
        bean.getCompId();
      query = query + whereClause;
      query = query + " ORDER BY COMP_ASSESSMENT_ID,HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID ";

      map = getSqlModel().getSingleResultMap(query, 0, 2);
      String assesDate = "SELECT  HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID,TO_CHAR(COMP_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_COMP_ASSESSMENT_CONFIG WHERE COMP_ID=" + bean.getCompId();
      Object[][] assesDateObj = getSqlModel().getSingleResult(assesDate);
      if ((assesDateObj != null) && (map != null)) {
        for (int i = 0; i < assesDateObj.length; i++) {
          Object[][] compDataAssesDateWise = 
            (Object[][])map
            .get(String.valueOf(assesDateObj[i][0]));

          if ((compDataAssesDateWise != null) && 
            (compDataAssesDateWise.length > 0)) {
            Object[][] creditheadtitle = new Object[1][1];
            creditheadtitle[0][0] = Utility.checkNull(String.valueOf(assesDateObj[i][1]));
            TableDataSet AssHeaderData = new TableDataSet();
            AssHeaderData.setData(new Object[][] { { "Assessment Date: " + 
              String.valueOf(assesDateObj[i][1]) } });

            AssHeaderData.setCellAlignment(new int[1]);
            AssHeaderData.setCellWidth(new int[] { 100 });
            AssHeaderData.setBodyFontStyle(1);
            AssHeaderData.setCellColSpan(new int[] { checkCount + 5 });

            AssHeaderData.setBorderDetail(0);
            AssHeaderData.setBlankRowsBelow(1);

            rg.addTableToDoc(AssHeaderData);
            int cols = 5 + checkCount;

            String[] columns = new String[checkCount + 5];
            int[] colspan = new int[checkCount + 5];
            int[] GoalcellAlign = new int[checkCount + 5];
            int[] GoalcellWidth = new int[checkCount + 5];
            boolean[] Goalcellwrap = new boolean[checkCount + 5];

            int chkcnt = 0;
            columns[chkcnt] = "Sr. No.";
            GoalcellAlign[chkcnt] = 0;
            GoalcellWidth[chkcnt] = 20;
            Goalcellwrap[chkcnt] = true;
            chkcnt++;
            columns[chkcnt] = "Emp.Id";
            GoalcellAlign[chkcnt] = 0;
            GoalcellWidth[chkcnt] = 20;
            colspan[chkcnt] = 1;
            Goalcellwrap[chkcnt] = true;
            chkcnt++;
            columns[chkcnt] = "Emp.Name";
            GoalcellAlign[chkcnt] = 0;
            GoalcellWidth[chkcnt] = 35;
            Goalcellwrap[chkcnt] = true;
            chkcnt++;
            columns[chkcnt] = "Designation";
            GoalcellAlign[chkcnt] = 0;
            GoalcellWidth[chkcnt] = 35;
            Goalcellwrap[chkcnt] = true;
            chkcnt++;
            columns[chkcnt] = "Competency Name";
            GoalcellAlign[chkcnt] = 0;
            GoalcellWidth[chkcnt] = 35;
            Goalcellwrap[chkcnt] = true;
            chkcnt++;
            for (int l = 0; l < totManagers; l++) {
              for (int m = 0; m < 4; m++) {
                if (m == 0) {
                  columns[chkcnt] = ("Manager" + (l + 1) + " Name");
                  GoalcellAlign[chkcnt] = 0;
                  GoalcellWidth[chkcnt] = 35;
                  Goalcellwrap[chkcnt] = true;
                  chkcnt++;
                }
                if (m == 1) {
                  columns[chkcnt] = "Development Plan";
                  GoalcellAlign[chkcnt] = 0;
                  GoalcellWidth[chkcnt] = 35;
                  Goalcellwrap[chkcnt] = true;
                  chkcnt++;
                }
                if (m == 2) {
                  columns[chkcnt] = "Target Date to Achieve";
                  GoalcellAlign[chkcnt] = 0;
                  GoalcellWidth[chkcnt] = 35;
                  Goalcellwrap[chkcnt] = true;
                  chkcnt++;
                }
                if (m == 3) {
                  columns[chkcnt] = "Development Comment";
                  GoalcellAlign[chkcnt] = 0;
                  GoalcellWidth[chkcnt] = 35;
                  Goalcellwrap[chkcnt] = true;
                  chkcnt++;
                }
              }
            }
            String assesmentId = String.valueOf(assesDateObj[i][0]);
            String numComp = "SELECT  HRMS_COMP_SELF_ASSESMENT.COMP_ID,HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID,OFFC1.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||OFFC1.EMP_FNAME||' '||NVL(OFFC1.EMP_MNAME,' ')|| ' ' ||  OFFC1.EMP_LNAME) AS EMP_NAME, HRMS_RANK.RANK_NAME,COMPETENCY_TITLE FROM HRMS_COMP_SELF_ASSESMENT INNER JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID=HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID)  INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)  LEFT JOIN HRMS_TITLE  ON(OFFC1.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)   \t  LEFT JOIN HRMS_RANK ON(OFFC1.EMP_RANK=HRMS_RANK.RANK_ID)  INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID =HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID )  INNER JOIN HRMS_COMP_EMP_ASSESSMENT_DTL  ON(HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID = HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID AND HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID = HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID  AND HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE = HRMS_COMP_SELF_ASSESMENT.COMP_ID)  WHERE   HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID= " + 
              bean.getCompId() + " AND HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID=" + assesmentId;
            numComp = numComp + whereClause;
            Object[][] numCompObj = getSqlModel().getSingleResult(numComp);

            if ((numCompObj != null) && (numCompObj.length > 0)) {
              Object[][] final_Object = new Object[numCompObj.length][cols];
              for (int g = 0; g < final_Object.length; g++) {
                final_Object[g][0] = Integer.valueOf(g + 1);
              }
              for (int k = 0; k < numCompObj.length; k++) {
                for (int l = 0; l < numCompObj[0].length - 2; l++) {
                  final_Object[k][(l + 1)] = numCompObj[k][(l + 2)];
                }
                String queryMgrDevplan = "SELECT OFFC2.EMP_FNAME||' '||OFFC2.EMP_LNAME,COMP_DEV_PLAN, TO_CHAR(COMP_DEV_TARGET_DATE,'DD-MM-YYYY'),NVL(HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_DEV_COMMENT,'') AS COMP_DEV_COMMENT FROM HRMS_COMP_EMP_ASSESSMENT_DTL\tLEFT JOIN HRMS_COMPETENCY_DEV_PLAN ON(HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_DEV_CODE=HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE)  INNER JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID=HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID)\tWHERE COMP_ASSESSMENT_ID=" + 
                  assesmentId + 
                  " AND COMPETENCY_CODE=" + numCompObj[k][0] + " AND COMP_EMP_ID=" + numCompObj[k][1];
                Object[][] queryMgrDevplanObj = getSqlModel().getSingleResult(queryMgrDevplan);
                int a = 5;
                if ((queryMgrDevplanObj != null) && (queryMgrDevplanObj.length > 0)) {
                  for (int n = 0; n < queryMgrDevplanObj.length; n++) {
                    for (int m = 0; m < queryMgrDevplanObj[0].length; m++) {
                      final_Object[k][a] = queryMgrDevplanObj[n][m];
                      a++;
                    }
                  }

                }

              }

              TableDataSet tdstable = new TableDataSet();

              tdstable.setHeader(columns);
              tdstable.setData(final_Object);
              tdstable.setCellAlignment(GoalcellAlign);
              tdstable.setCellWidth(GoalcellWidth);
              tdstable.setCellNoWrap(Goalcellwrap);
              tdstable.setBorderDetail(3);
              tdstable.setBlankRowsBelow(1);
              tdstable.setBorder(Boolean.valueOf(true));
              tdstable.setHeaderTable(true);
              tdstable.setHeaderBorderDetail(3);
              rg.addTableToDoc(tdstable);
            }
            else {
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
        }
      } else {
        TableDataSet noData = new TableDataSet();
        Object[][] noDataObj = new Object[1][1];
        noDataObj[0][0] = "Records are not finalized";

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
      else {
        rg.saveReport(response);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}