package org.paradyne.model.PAS.GoalSetting;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.EmpGoalReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;

public class EmpGoalReportModel extends ModelBase{
  static Logger logger = Logger.getLogger(EmpGoalReportModel.class);

  public void genReport(EmpGoalReport bean, HttpServletResponse response, HttpServletRequest request, String reportPath){
    try{
      ReportGenerator rg = null;
      String reportType = bean.getReport();
      String title = "Employee Goal Report ";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "Employee Goal Report_" + Utility.getRandomNumber(1000);
      rds.setFileName(fileName);
      rds.setReportName(title);
      rds.setPageSize("landscape");
      rds.setPageOrientation("landscape");
      rds.setUserEmpId(bean.getUserEmpId());
      rds.setReportType(reportType);

      int checkCount = 0;
      String filters = "";
      String GoalHdrId = "";
      String EmpAssmentId = "";
      int numManagers = 0;
      String query = " SELECT HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_CONFIG.GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY')," 
      				+" TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY') ,HRMS_GOAL_EMP_HDR.EMP_ID,\tHRMS_EMP_OFFC.EMP_TOKEN,"
      				+" (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' ||  HRMS_EMP_OFFC.EMP_LNAME),"
      				+" NVL(HRMS_CENTER.CENTER_NAME,'  '),NVL(HRMS_RANK.RANK_NAME,'  '),\tNVL(HRMS_DEPT.DEPT_NAME,' ')"
      				+" FROM HRMS_GOAL_EMP_HDR " 
      				+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_HDR.EMP_ID )"
      				+" LEFT JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID"
      				+" LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)"
      				+" LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
      				+" LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
      				+" LEFT JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
      				+" WHERE  HRMS_GOAL_EMP_HDR.EMP_ID = " + 
      				bean.getEmpId()
      				+"  AND HRMS_GOAL_EMP_HDR.GOAL_ID=" + bean.getGoalId()
      				+"  ORDER BY EMP_ID";

      Object[][] queryData = getSqlModel().getSingleResult(query);

      if ((queryData != null) && (queryData.length > 0)) {
        GoalHdrId = String.valueOf(queryData[0][0]);
        filters = filters + "Goal Name : " + String.valueOf(queryData[0][1]);
        filters = filters + "\nGoal From : " + String.valueOf(queryData[0][2]);
        filters = filters + "\n Goal To: " + String.valueOf(queryData[0][3]);
        filters = filters + "\nEmployee ID:  : " + String.valueOf(queryData[0][5]) + "                                    Employee Name: : " + String.valueOf(queryData[0][6]);
        filters = filters + "\n Branch: " + String.valueOf(queryData[0][7]);
        filters = filters + "\nDesignation : " + String.valueOf(queryData[0][8]);
        filters = filters + "\nDepartment: " + String.valueOf(queryData[0][9]);
      }

      if (bean.getShowSelfAssessment().equals("true"))
      {
        checkCount += 3;
      }
      if (bean.getShowManagerAssessment().equals("true"))
      {
        if ((queryData != null) && (queryData.length > 0)) {
          String numManagersData = "SELECT MAX(GOAL_PATH_ID) FROM HRMS_GOAL_PATH WHERE GOAL_CODE=" + GoalHdrId;
          Object[][] numManagersObj = getSqlModel().getSingleResult(numManagersData);
          if (numManagersObj != null) {
            numManagers = Integer.parseInt(String.valueOf(numManagersObj[0][0]));
            checkCount += numManagers * 3;
          }
        }

      }

      rds.setTotalColumns(checkCount + 6);
      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, 
          this.session, this.context, request);
      } else {
        logger.info("################# ATTACHMENT PATH #############" + 
          reportPath + fileName);
        rg = new ReportGenerator(rds, 
          reportPath, this.session, this.context, request);

        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
        request.setAttribute("action", "/pas/EmpGoalReport_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }
      TableDataSet filterData = new TableDataSet();
      filterData.setData(new Object[][] { { filters } });
      filterData.setCellAlignment(new int[1]);
      filterData.setBodyFontStyle(0);
      filterData.setCellWidth(new int[] { 100 });
      filterData.setCellColSpan(new int[] { checkCount + 6 });
      filterData.setCellNoWrap(new boolean[] { true });
      filterData.setBlankRowsBelow(1);
      filterData.setBorder(Boolean.valueOf(false));
      rg.addTableToDoc(filterData);

      HashMap map1 = null;
      if (!GoalHdrId.equals("")) {
        String queryAssesmentData = "SELECT TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') ,"
        	+ " EMP_GOAL_ASSESSMENT_ID,GOAL_DESCRIPTION, GOAL_WEIGHTAGE,NVL(GOAL_CATEGORY,' '),"
        	+ " GOAL_CATEGORY_WEIGHTAGE,NVL(GOAL_COMMENTS,''),"
        	+ " TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY')"
        	+ " FROM HRMS_GOAL_EMP_DTL "
        	+ " INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID) "
        	+ " LEFT JOIN HRMS_GOAL_EMP_HDR On (HRMS_GOAL_EMP_HDR.GOAL_HDR_ID = HRMS_GOAL_EMP_DTL.GOAL_HDR_ID) "
        	+ " LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) "
        	+ " WHERE GOAL_DTL_STATUS='A' AND HRMS_GOAL_EMP_HDR.GOAL_HDR_ID = " + GoalHdrId 
        	+ " AND EMP_ID =" + bean.getEmpId() + " " 
        	+ " AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE <=SYSDATE  ORDER BY GOAL_ASSESSMENT_DATE,GOAL_DTL_ID";

        map1 = getSqlModel().getSingleResultMap(queryAssesmentData, 0, 2);
      }
      String assesDate = "SELECT TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_ASSESSMENT_CONFIG WHERE GOAL_ID=" + bean.getGoalId();
      Object[][] assesDateObj = getSqlModel().getSingleResult(assesDate);
      if ((assesDateObj != null) && (map1 != null)) {
        for (int i = 0; i < assesDateObj.length; i++) {
          Object[][] goalDataAssesDateWise = 
            (Object[][])map1
            .get(String.valueOf(assesDateObj[i][0]));

          if ((goalDataAssesDateWise != null) && 
            (goalDataAssesDateWise.length > 0)) {
            Object[][] creditheadtitle = new Object[1][1];
            creditheadtitle[0][0] = checkNull(String.valueOf(assesDateObj[i][0]));
            TableDataSet AssHeaderData = new TableDataSet();
            AssHeaderData.setData(new Object[][] { { "Assessment Date: " + 
              String.valueOf(assesDateObj[i][0]) } });

            AssHeaderData.setCellAlignment(new int[1]);
            AssHeaderData.setCellWidth(new int[] { 100 });
            AssHeaderData.setBodyFontStyle(1);
            AssHeaderData.setCellColSpan(new int[] { checkCount + 6 });

            AssHeaderData.setBorderDetail(0);
            AssHeaderData.setBlankRowsBelow(1);

            rg.addTableToDoc(AssHeaderData);
            int cols = goalDataAssesDateWise[0].length - 2 + 3 + numManagers * 3;
            System.out.println("Cols" + cols);
            Object[][] final_Object = new Object[goalDataAssesDateWise.length][cols];

            for (int j = 0; j < goalDataAssesDateWise.length; j++) {
              for (int k = 0; k < goalDataAssesDateWise[0].length - 2; k++) {
                final_Object[j][k] = goalDataAssesDateWise[j][(k + 2)];
              }

            }

            EmpAssmentId = String.valueOf(goalDataAssesDateWise[0][1]);
            String detailId = " SELECT  SELF_GOAL_DTL_ID FROM HRMS_GOAL_SELF_ASSESSMENT_DTL WHERE SELF_GOAL_ASSESSMENT_ID=" + EmpAssmentId+"order by SELF_GOAL_DTL_ID";
            Object[][] detailIdObj = getSqlModel().getSingleResult(detailId);
            Object[][] selfGoalDetails = (Object[][])null;
            if (bean.getShowSelfAssessment().equals("true")) {
              HashMap selfAssesmentDet = getSelfAssesmentDetails(EmpAssmentId);
              if (selfAssesmentDet != null)
              {
                if (detailIdObj != null) {
                  for (int m = 0; m < detailIdObj.length; m++) {
                    selfGoalDetails = 
                      (Object[][])selfAssesmentDet
                      .get(String.valueOf(detailIdObj[m][0]));
                    if (selfGoalDetails != null) {
                      for (int l = 0; l < selfGoalDetails.length; l++) {
                        for (int n = 0; n < selfGoalDetails[0].length - 1; n++) {
                          System.out.println("Deatils" + selfGoalDetails[l][n]);
                          final_Object[m][(n + 6)] = selfGoalDetails[l][(n + 1)];
                        }
                      }
                    }
                  }
                }
              }

            }

            if (bean.getShowManagerAssessment().equals("true")) {
              HashMap EmpAssesmentDet = getEmpAssesmentDetails(EmpAssmentId);
              if ((EmpAssesmentDet == null) || 
                      (detailIdObj == null)) continue;
              if ((EmpAssesmentDet != null) && 
                (detailIdObj != null)) {
            	 try {
            	  int s=0;
            	  for (int m = 0; m < detailIdObj.length; m++) {
                      int a = 0;
                  Object[][] empGoalDetails = 
                    (Object[][])EmpAssesmentDet
                    .get(String.valueOf(detailIdObj[m][0]));
                  if (empGoalDetails != null) {
                    for (int l = 0; l < empGoalDetails.length; l++) {
                      for (int n = 0; n < empGoalDetails[0].length-1; n++) {
                        System.out.println("Deatils" + empGoalDetails[l][n] + "N Value" + n);
                        
                        	 if (selfGoalDetails != null)
                             {
                               final_Object[m][(a + 9)] = empGoalDetails[l][(n + 1)];
                               a++;
                             } else {
                               final_Object[m][(a + 6)] = empGoalDetails[l][(n + 1)];
                               a++;
                             }
                      }
                    }
                  }

                }
            	 } catch (Exception e) {
 					// TODO: handle exception
 				} 
             	  
              }

            }

            String[] columns = new String[checkCount + 6];
            int[] GoalcellAlign = new int[checkCount + 6];
            int[] GoalcellWidth = new int[checkCount + 6];
            boolean[] Goalcellwrap = new boolean[checkCount + 6];

            int chkcnt = 0;
            columns[chkcnt] = "Goal Description";
            GoalcellAlign[chkcnt] = 0;
            GoalcellWidth[chkcnt] = 40;
            Goalcellwrap[chkcnt] = true;
            chkcnt++;
            columns[chkcnt] = "Goal Weightage";
            GoalcellAlign[chkcnt] = 2;
            GoalcellWidth[chkcnt] = 40;
            Goalcellwrap[chkcnt] = true;
            chkcnt++;
            columns[chkcnt] = "Goal Category";
            GoalcellAlign[chkcnt] = 0;
            GoalcellWidth[chkcnt] = 35;
            Goalcellwrap[chkcnt] = true;
            chkcnt++;
            columns[chkcnt] = "Category Weightage";
            GoalcellAlign[chkcnt] = 2;
            GoalcellWidth[chkcnt] = 35;
            Goalcellwrap[chkcnt] = true;
            chkcnt++;
            columns[chkcnt] = "Goal Comments";
            GoalcellAlign[chkcnt] = 0;
            GoalcellWidth[chkcnt] = 35;
            Goalcellwrap[chkcnt] = true;
            chkcnt++;
            columns[chkcnt] = "Achievement date";
            GoalcellAlign[chkcnt] = 1;
            GoalcellWidth[chkcnt] = 35;
            Goalcellwrap[chkcnt] = true;
            chkcnt++;

            if (bean.getShowSelfAssessment().equals("true"))
            {
              columns[chkcnt] = "Achievement Information";
              GoalcellAlign[chkcnt] = 0;
              GoalcellWidth[chkcnt] = 40;
              Goalcellwrap[chkcnt] = true;
              chkcnt++;

              columns[chkcnt] = "Self Ratings";
              GoalcellAlign[chkcnt] = 2;
              GoalcellWidth[chkcnt] = 35;
              Goalcellwrap[chkcnt] = true;
              chkcnt++;
              columns[chkcnt] = "Self Comments";
              GoalcellAlign[chkcnt] = 0;
              GoalcellWidth[chkcnt] = 35;
              Goalcellwrap[chkcnt] = true;
              chkcnt++;
            }

            if (bean.getShowManagerAssessment().equals("true"))
            {
              for (int a = 0; a < numManagers; a++) {
                columns[chkcnt] = ("Manager " + (a + 1) + " Name");
                GoalcellAlign[chkcnt] = 0;
                GoalcellWidth[chkcnt] = 40;
                Goalcellwrap[chkcnt] = true;
                chkcnt++;
                columns[chkcnt] = ("Manager " + (a + 1) + " Rating");
                GoalcellAlign[chkcnt] = 2;
                GoalcellWidth[chkcnt] = 40;
                Goalcellwrap[chkcnt] = true;
                chkcnt++;
                columns[chkcnt] = ("Manager " + (a + 1) + " Comments");
                GoalcellAlign[chkcnt] = 0;
                GoalcellWidth[chkcnt] = 40;
                Goalcellwrap[chkcnt] = true;
                chkcnt++;
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
      Object[][] AssesmentweightageObj = (Object[][])null;
      if (!GoalHdrId.equals("")) {
        String Assesmentweightage = "SELECT  TO_CHAR(GOAL_ASSESSMENT_DATE,'MONTH-YYYY'),"
        	+" HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_WEIGTAGE,HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEW_SELF_RATING, "
        	+" HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEW_MGR_RATING, HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEW_FINAL_RATING  " 
        	+" FROM HRMS_GOAL_EMP_ASSESSMENT_HDR  "
        	+" INNER JOIN HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_HDR.GOAL_HDR_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID)"
        	+" INNER  JOIN HRMS_GOAL_ASSESSMENT_CONFIG ON(HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)"
        	+" WHERE HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ID=" + bean.getGoalId() 
        	+" AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=" + GoalHdrId
        	+" GROUP BY HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_WEIGTAGE,TO_CHAR(GOAL_ASSESSMENT_DATE,'MONTH-YYYY'),"
        	+" HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEW_SELF_RATING,"  
        	+" HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEW_MGR_RATING, HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_REVIEW_FINAL_RATING";

        AssesmentweightageObj = getSqlModel().getSingleResult(Assesmentweightage);
      }
      if (AssesmentweightageObj != null) {
        TableDataSet tdstable = new TableDataSet();
        tdstable.setHeader(new String[] { "Assessment", "Assessment weightage", "Self Rating", 
          "Manager Rating", "Final Rating" });
        tdstable.setData(AssesmentweightageObj);
        tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
        tdstable.setCellAlignment(new int[] { 1, 2, 2, 2, 2 });
        tdstable.setCellWidth(new int[] { 50, 25, 25, 25, 25 });
        tdstable.setHeaderBorderDetail(3);
        tdstable.setBorderDetail(3);
        tdstable.setBorder(Boolean.valueOf(true));
        tdstable.setBlankRowsAbove(1);
        tdstable.setHeaderTable(true);
        tdstable.setBlankRowsBelow(1);
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

      String finalRatingDet = "SELECT FINAL_RATING "
    	  +" FROM  HRMS_GOAL_EMP_HDR"
    	  +" WHERE HRMS_GOAL_EMP_HDR.GOAL_ID =" + bean.getGoalId() + " AND EMP_ID =" + bean.getEmpId();
      Object[][] finalRatingObj = getSqlModel().getSingleResult(finalRatingDet);
      String finalRatingNum = "";
      if ((finalRatingObj != null) && (finalRatingObj.length > 0) && (finalRatingObj[0][0] != null)) {
        finalRatingNum = finalRatingNum + "Final Rating : " + String.valueOf(finalRatingObj[0][0]);
      }

      TableDataSet finalRating = new TableDataSet();
      finalRating.setData(new Object[][] { { finalRatingNum } });
      finalRating.setCellAlignment(new int[1]);
      finalRating.setCellWidth(new int[] { 100 });
      finalRating.setCellNoWrap(new boolean[1]);
      finalRating.setBorderDetail(0);
      finalRating.setBodyFontStyle(1);
      finalRating.setBlankRowsAbove(1);
      rg.addTableToDoc(finalRating);

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

  public String checkNull(String result) {
    if ((result == null) || (result.equals("null")) || (result.equals(" ")) || 
      (result.equals(""))) {
      return "";
    }

    return result;
  }

  public HashMap getSelfAssesmentDetails(String assmentId)
  {
    Object[][] result = (Object[][])null;
    String query = "SELECT  SELF_GOAL_DTL_ID,DECODE(IS_GOAL_ACHIEVED,'Y','Achieved','P','Partialy Achieved','O', 'On Going',''),SELF_GOAL_RATING,NVL(SELF_GOAL_COMMENT,'') FROM HRMS_GOAL_SELF_ASSESSMENT_DTL INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID=" + 
      assmentId + ")" + 
      " LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) ";

    System.out.println("The Query : " + query);
    result = getSqlModel().getSingleResult(
      query);
    HashMap map = getSqlModel().getSingleResultMap(query, 0, 2);
    return map;
  }

  public HashMap getEmpAssesmentDetails(String assmentId) {
    Object[][] result = (Object[][])null;
    String query = "SELECT GOAL_DTL_ID,EMP_FNAME||' '||EMP_LNAME, NVL(GOAL_RATING,''),NVL(GOAL_COMMENT,'')  FROM HRMS_GOAL_EMP_ASSESSMENT_DTL LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID)  WHERE EMP_GOAL_ASSESSMENT_ID =" + 
      assmentId + "  ORDER BY GOAL_DTL_ID,ASSESSMENT_DTL_ID ";

    System.out.println("The Query : " + query);
    result = getSqlModel().getSingleResult(
      query);
    HashMap map = getSqlModel().getSingleResultMap(query, 0, 2);
    return map;
  }
}