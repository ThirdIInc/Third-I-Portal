package org.paradyne.model.PAS.Competency;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.Competency.CompetencyStatusMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

public class CompetencyStatusMisReportModel extends ModelBase
{
  static Logger logger = Logger.getLogger(CompetencyStatusMisReportModel.class);

  public void genReport(CompetencyStatusMisReport bean, HttpServletResponse response, HttpServletRequest request, String reportPath)
  {
    try {
      ReportGenerator rg = null;
      String reportType = bean.getReport();
      String title = "Competency Status Report ";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "Competency Status Report_" + Utility.getRandomNumber(1000);
      rds.setFileName(fileName);

      rds.setReportName(title);
      rds.setPageSize("A4");
      rds.setPageOrientation("landscape");
      rds.setUserEmpId(bean.getUserEmpId());
      rds.setReportType(reportType);
      String filters = "";

      filters = filters + "Competency Name : " + bean.getCompName();
      String goalFrmDateQuery = "SELECT TO_CHAR(HRMS_COMP_CONFIG.COMP_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_COMP_CONFIG.COMP_TO_DATE,'DD-MM-YYYY') FROM HRMS_COMP_CONFIG  WHERE HRMS_COMP_CONFIG.COMP_ID=" + bean.getCompId();
      Object[][] goalFrmDateObj = getSqlModel().getSingleResult(goalFrmDateQuery);
      filters = filters + "\nCompetency From Date : " + String.valueOf(goalFrmDateObj[0][0]);
      filters = filters + "\nCompetency To Date : " + String.valueOf(goalFrmDateObj[0][1]);

      if ((bean.getEmpId() != null) && (!bean.getEmpId().equals("")))
      {
        filters = filters + "\n Employee Name : " + bean.getEmpToken() + "-" + bean.getEmpName();
      }

      if ((bean.getDeptId() != null) && (!bean.getDeptId().equals("")))
      {
        filters = filters + "\n Department : " + bean.getDeptName();
      }
      if ((bean.getDivisionId() != null) && (!bean.getDivisionId().equals("")))
      {
        filters = filters + "\n Division : " + bean.getDivisionName();
      }
      if ((bean.getDesgId() != null) && (!bean.getDesgId().equals("")))
      {
        filters = filters + "\n Designation : " + bean.getDesgName();
      }
      if ((bean.getBranchId() != null) && (!bean.getBranchId().equals("")))
      {
        filters = filters + "\n Branch : " + bean.getBranchName();
      }

      rds.setTotalColumns(8);
      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, this.session, this.context, request);
      } else {
        logger.info("################# ATTACHMENT PATH #############" + reportPath + fileName);
        rg = new ReportGenerator(rds, reportPath, this.session, this.context, request);

        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
        request.setAttribute("action", "/pas/CompetencyStatusMisReport_input.action");
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
      String query = "SELECT TO_CHAR(COMP_ASSESSMENT_DATE,'DD-MM-YY'),COMP_ASSESSMENT_ID FROM HRMS_COMP_ASSESSMENT_CONFIG WHERE  COMP_ID=" + bean.getCompId();
      Object[][] reviewDate = getSqlModel().getSingleResult(query);
      if ((reviewDate != null) && (reviewDate.length > 0)) {
        for (int i = 0; i < reviewDate.length; i++) {
          String filterDate = "";
          filterDate = filterDate + "Competency Review Date: \t" + String.valueOf(reviewDate[i][0]);
          TableDataSet tdstable1 = new TableDataSet();
          tdstable1.setData(new Object[][] { { filterDate } });
          tdstable1.setCellAlignment(new int[1]);
          tdstable1.setCellWidth(new int[] { 100 });
          tdstable1.setBodyFontStyle(1);
          tdstable1.setCellColSpan(new int[] { 8 });
          tdstable1.setBorderDetail(0);
          tdstable1.setBlankRowsBelow(1);
          rg.addTableToDoc(tdstable1);
          String pendingEmpQuery = "";
          if (bean.getShowCompAssesmentSelfReport().equals("true")) {
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

            pendingEmpQuery = "SELECT DISTINCT OFFC1.EMP_TOKEN,(HRMS_TITLE .TITLE_NAME||' '||  OFFC1.EMP_FNAME||' '||NVL(OFFC1.EMP_MNAME,' ')|| ' ' || OFFC1.EMP_LNAME), NVL(HRMS_DIVISION.DIV_NAME,' '),NVL(CENTER_NAME,''),NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  ') FROM HRMS_COMP_CONFIG  INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID)   LEFT JOIN HRMS_COMP_ASSESMENT_HDR ON (HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_ID = HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID AND HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID=HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID)  INNER JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID =   HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)  LEFT JOIN HRMS_TITLE  ON(OFFC1.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_DEPT ON(OFFC1.EMP_DEPT=HRMS_DEPT.DEPT_ID)   LEFT JOIN HRMS_RANK ON(OFFC1.EMP_RANK=HRMS_RANK.RANK_ID)   LEFT JOIN HRMS_CENTER ON(OFFC1.EMP_CENTER=HRMS_CENTER.CENTER_ID)  LEFT JOIN HRMS_DIVISION ON(OFFC1.EMP_DIV=HRMS_DIVISION.DIV_ID)  WHERE HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE <=SYSDATE  AND NVL(HRMS_COMP_ASSESMENT_HDR.COMP_ASSESMENT_STATUS,'N') !='Y'  AND HRMS_COMP_CONFIG.COMP_PUBLISH_STATUS = '2' AND TO_CHAR(TO_DATE(COMP_ASSESSMENT_DATE,'DD-MM-YY')) =TO_DATE('" + 
              reviewDate[i][0] + "','DD-MM-YY')" + 
              " AND HRMS_COMP_CONFIG.COMP_ID= " + bean.getCompId();

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
          if (bean.getShowCompAssesmentManagerReport().equals("true")) {
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
            pendingEmpQuery = "  SELECT OFFC1.EMP_TOKEN,(HRMS_TITLE .TITLE_NAME||' '||   OFFC1.EMP_FNAME||' '||NVL(OFFC1.EMP_MNAME,' ')|| ' ' || OFFC1.EMP_LNAME), NVL(HRMS_DIVISION.DIV_NAME,' '),NVL(CENTER_NAME,''),  NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  '), HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_LEVEL,  (OFFC2.EMP_FNAME||' '||NVL(OFFC2.EMP_MNAME,' ')|| ' ' || OFFC2.EMP_LNAME)  FROM HRMS_COMP_EMP_ASSESSMENT_HDR    INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID)    INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID)  INNER JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID)  LEFT JOIN HRMS_TITLE  ON(OFFC1.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_DEPT ON(OFFC1.EMP_DEPT=HRMS_DEPT.DEPT_ID)   LEFT JOIN HRMS_RANK ON(OFFC1.EMP_RANK=HRMS_RANK.RANK_ID)   LEFT JOIN HRMS_CENTER ON(OFFC1.EMP_CENTER=HRMS_CENTER.CENTER_ID)  LEFT JOIN HRMS_DIVISION ON(OFFC1.EMP_DIV=HRMS_DIVISION.DIV_ID)  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = 'P' AND TO_CHAR(TO_DATE(COMP_ASSESSMENT_DATE,'DD-MM-YY')) =TO_DATE('" + 
              reviewDate[i][0] + "','DD-MM-YY')" + 
              " AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = " + bean.getCompId();

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
              int[] empcellAlign = { 0, 0, 0, 0, 0, 0, 1 ,0};
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
       
        if (bean.getShowCompAssesmentPendingReport().equals("true")) {
            AssessTitle = new Object[1][1];
            AssessTitle[0][0] = "Employees not submitted Competency Application :";
            AssHeaderData = new TableDataSet();
            AssHeaderData.setData(new Object[][] { { "Employees not submitted Competency Application : " } });

            AssHeaderData.setCellAlignment(new int[1]);
            AssHeaderData.setCellWidth(new int[] { 100 });
            AssHeaderData.setBodyFontStyle(1);
            AssHeaderData.setCellColSpan(new int[] { 8 });

            AssHeaderData.setBorderDetail(0);
            AssHeaderData.setBlankRowsBelow(1);

            rg.addTableToDoc(AssHeaderData);

            String pendingQuery = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN, (HRMS_TITLE .TITLE_NAME||' '||  HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) AS  ENAME," 
            		+" NVL(HRMS_DIVISION.DIV_NAME,' '),NVL(CENTER_NAME,''),NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  ') FROM HRMS_EMP_OFFC LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)" 
            		+" LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)   LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)   LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) " 
            		+" LEFT JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID)  LEFT JOIN HRMS_COMP_ASSESMENT_HDR   ON (HRMS_EMP_OFFC.EMP_ID =   HRMS_COMP_ASSESMENT_HDR.COMP_EMP_ID)"
            		+" LEFT JOIN HRMS_COMP_ASSESSMENT_CONFIG ON("
            		+" HRMS_COMP_ASSESMENT_HDR.COMPETENCY_ID=HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID"
          		+" AND TO_CHAR(TO_DATE(COMP_ASSESSMENT_DATE,'DD-MM-YY')) =TO_DATE('" + 
              reviewDate[i][0] + "','DD-MM-YY'))"
          		+" LEFT JOIN HRMS_COMP_CONFIG ON(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID=HRMS_COMP_CONFIG.COMP_ID  AND HRMS_COMP_CONFIG.COMP_ID="+bean.getCompId()+" AND HRMS_COMP_CONFIG.COMP_PUBLISH_STATUS = '2') " 
          		+" WHERE HRMS_EMP_OFFC.EMP_ID NOT IN(SELECT COMP_EMP_ID FROM HRMS_COMP_ASSESMENT_HDR WHERE COMPETENCY_ID="+bean.getCompId()+" AND COMP_ASSESMENT_ID="+reviewDate[i][1]+") AND EMP_STATUS='S'";

            
            if (!bean.getDivisionId().equals("")) {
              pendingQuery = pendingQuery + " AND HRMS_EMP_OFFC.EMP_DIV=" + bean.getDivisionId();
            }
            if (!bean.getBranchId().equals("")) {
              pendingQuery = pendingQuery + " AND HRMS_EMP_OFFC.EMP_CENTER=" + bean.getBranchId();
            }
            if (!bean.getDeptId().equals("")) {
              pendingQuery = pendingQuery + " AND HRMS_EMP_OFFC.EMP_DEPT=" + bean.getDeptId();
            }
            if (!bean.getDesgId().equals("")) {
              pendingQuery = pendingQuery + " AND HRMS_EMP_OFFC.EMP_RANK=" + bean.getDesgId();
            }
            if (!bean.getEmpId().equals("")) {
              pendingQuery = pendingQuery + " AND HRMS_EMP_OFFC.EMP_ID=" + bean.getEmpId();
            }
            pendingQuery = pendingQuery + "ORDER BY ENAME";
            Object[][] pendingEmp = getSqlModel().getSingleResult(pendingQuery);
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
        AssessTitle = new Object[1][1];
        AssessTitle[0][0] = "Employees Completed The Competency:";
        AssHeaderData = new TableDataSet();
        AssHeaderData.setData(new Object[][] { { "Employees Completed The Competency : " } });

        AssHeaderData.setCellAlignment(new int[1]);
        AssHeaderData.setCellWidth(new int[] { 100 });
        AssHeaderData.setBodyFontStyle(1);
        AssHeaderData.setCellColSpan(new int[] { 8 });

        AssHeaderData.setBorderDetail(0);
        AssHeaderData.setBlankRowsBelow(1);

        rg.addTableToDoc(AssHeaderData);
        String EmpQuery = "";
        EmpQuery = "  SELECT OFFC1.EMP_TOKEN,(HRMS_TITLE .TITLE_NAME||' '||   OFFC1.EMP_FNAME||' '||NVL(OFFC1.EMP_MNAME,' ')|| ' ' || OFFC1.EMP_LNAME), NVL(HRMS_DIVISION.DIV_NAME,' '),NVL(CENTER_NAME,''),  NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  ') ,TO_CHAR(COMP_ASSESSMENT_DATE,'DD-MM-YY') FROM HRMS_COMP_EMP_ASSESSMENT_HDR    INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID)    INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID) " 
        		+" INNER JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID)  INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSER_ID)  LEFT JOIN HRMS_TITLE  ON(OFFC1.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)  LEFT JOIN HRMS_DEPT ON(OFFC1.EMP_DEPT=HRMS_DEPT.DEPT_ID)   LEFT JOIN HRMS_RANK ON(OFFC1.EMP_RANK=HRMS_RANK.RANK_ID)   LEFT JOIN HRMS_CENTER ON(OFFC1.EMP_CENTER=HRMS_CENTER.CENTER_ID)  LEFT JOIN HRMS_DIVISION ON(OFFC1.EMP_DIV=HRMS_DIVISION.DIV_ID)  WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_STATUS = 'A' " 
        		+" AND TO_CHAR(TO_DATE(COMP_ASSESSMENT_DATE,'DD-MM-YY')) =TO_DATE('" +  reviewDate[i][0] + "','DD-MM-YY') AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID = " + bean.getCompId();

        if (!bean.getDivisionId().equals("")) {
          EmpQuery = EmpQuery + " AND OFFC1.EMP_DIV=" + bean.getDivisionId();
        }
        if (!bean.getBranchId().equals("")) {
          EmpQuery = EmpQuery + " AND OFFC1.EMP_CENTER=" + bean.getBranchId();
        }
        if (!bean.getDeptId().equals("")) {
          EmpQuery = EmpQuery + " AND OFFC1.EMP_DEPT=" + bean.getDeptId();
        }
        if (!bean.getDesgId().equals("")) {
          EmpQuery = EmpQuery + " AND OFFC1.EMP_RANK=" + bean.getDesgId();
        }
        if (!bean.getEmpId().equals("")) {
          EmpQuery = EmpQuery + " AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID=" + bean.getEmpId();
        }

        EmpQuery = EmpQuery + "\tORDER BY EMP_ID";
        Object[][] pendingEmp = getSqlModel().getSingleResult(EmpQuery);
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