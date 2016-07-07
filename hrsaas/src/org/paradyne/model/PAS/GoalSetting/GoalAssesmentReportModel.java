package org.paradyne.model.PAS.GoalSetting;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Set;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.GoalSetting.GoalAssesMentReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

/**@modified by Prajakta.Bhandare
 * @date 30 May 2013
 */
public class GoalAssesmentReportModel extends ModelBase{
  static Logger logger = Logger.getLogger(GoalAssesmentReportModel.class);

  /** @modified by @author Prajakta.Bhandare for adding comments in report
 * @param bean
 * @param response
 * @param request
 * @param reportPath
 */
public void genReport(GoalAssesMentReport bean, HttpServletResponse response, HttpServletRequest request, String reportPath){
	try{
	      ReportGenerator rg = null;
	      String reportType = bean.getReport();
	      String title = "Goal  Assessment Report ";
	      ReportDataSet rds = new ReportDataSet();
	      String fileName = "Goal  Assessment Report_" + Utility.getRandomNumber(1000);
	      rds.setFileName(fileName);

	      rds.setReportName(title);
	      rds.setPageSize("A4");
	      rds.setPageOrientation("landscape");
	      rds.setUserEmpId(bean.getUserEmpId());
	      rds.setReportType(reportType);
	      
	      int numManagers=0;
	      int checkCount = 0;
	      String filters="";
	      String whereClause="";
	      String EmpAssmentId = "";
	      String goalHeaderId="";
	      //QUERY FOR DISPLAYING GOAL NAME, GOAL TO DATE, GOAL FROM DATE
	      String goalFrmDateQuery = "SELECT TO_CHAR(HRMS_GOAL_CONFIG.GOAL_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_GOAL_CONFIG.GOAL_TO_DATE,'DD-MM-YYYY')" 
	      							+" FROM HRMS_GOAL_CONFIG  WHERE HRMS_GOAL_CONFIG.GOAL_ID=" + bean.getGoalId();
	      Object[][] goalFrmDateObj = getSqlModel().getSingleResult(goalFrmDateQuery);
	      filters = filters + "Goal Name : " + bean.getGoalName();
	      filters = filters + "\n From Date : " + String.valueOf(goalFrmDateObj[0][0]);
	      filters = filters + "\n To Date : " + String.valueOf(goalFrmDateObj[0][1]);
	      if ((bean.getEmpId() != null) && (!bean.getEmpId().equals(""))) {
	          whereClause = whereClause + " AND HRMS_GOAL_EMP_HDR.EMP_ID=" + bean.getEmpId();
	          filters = filters + "\n   Employee Name : " + bean.getEmpToken() + "-" + bean.getEmpName();
	        }
	      
	      filters=filters+"\n Goal Assessment Date : " +bean.getAssesmentDate();
	      if (reportPath.equals("")) {
	        rg = new ReportGenerator(rds, 
	          this.session, this.context, request);
	      } else {
	        logger.info("################# ATTACHMENT PATH #############" + 
	          reportPath + fileName);
	        rg = new ReportGenerator(rds, 
	          reportPath, this.session, this.context, request);
	        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
	        request.setAttribute("action", "/pas/GoalAssesReport_input.action");
	        request.setAttribute("fileName", fileName + "." + reportType);
	      }
	      TableDataSet filterData = new TableDataSet();
	      filterData.setData(new Object[][] { { filters } });
	      filterData.setCellAlignment(new int[1]);
	      filterData.setBodyFontStyle(0);
	      filterData.setCellWidth(new int[] { 100 });
	      filterData.setCellColSpan(new int[] { checkCount + 10 });
	      filterData.setCellNoWrap(new boolean[] { true });
	      filterData.setBlankRowsBelow(1);
	      filterData.setBorder(Boolean.valueOf(false));
	      rg.addTableToDoc(filterData);
	      //Query for Goal_hrd_id
	      String goalHdrQuery="SELECT GOAL_HDR_ID FROM HRMS_GOAL_EMP_HDR WHERE GOAL_ID="+bean.getGoalId();
	      Object[][] goalHdrObj=getSqlModel().getSingleResult(goalHdrQuery);
	    //QUERY FOR GOAL SETTING DATA
	      String querySettingData = "SELECT E.EMP_TOKEN, E.EMP_FNAME||' '||E.EMP_MNAME||' '||E.EMP_LNAME EMPNAME, "
	       +" NVL(GOAL_DESCRIPTION,''),NVL(GOAL_WEIGHTAGE,''), NVL(GOAL_CATEGORY,''),NVL(GOAL_CATEGORY_WEIGHTAGE,''),NVL(GOAL_COMMENTS,''),TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY'), NVL(SELF_GOAL_RATING,''), " 
	       +" NVL(SELF_GOAL_COMMENT,''),HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID, TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'),EMP_GOAL_ASSESSMENT_ID,EMP_ID FROM HRMS_GOAL_EMP_DTL"
	       +" INNER JOIN HRMS_GOAL_EMP_HDR ON (HRMS_GOAL_EMP_HDR.GOAL_HDR_ID=HRMS_GOAL_EMP_DTL.GOAL_HDR_ID)"
	       +"INNER JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)"
	       +" LEFT JOIN  HRMS_GOAL_EMP_ASSESSMENT_HDR ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) "
	       +" LEFT JOIN HRMS_GOAL_SELF_ASSESSMENT_DTL ON("
	       +" HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID"
	       +" AND HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID )"
	       +" LEFT JOIN  HRMS_EMP_OFFC E ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID=E.EMP_ID)"
	       +" LEFT JOIN HRMS_GOAL_CATEGORIES ON(HRMS_GOAL_EMP_DTL.GOAL_CATEGORY_CODE=HRMS_GOAL_CATEGORIES.GOAL_CATEGORY_ID)"
	       +" WHERE HRMS_GOAL_CONFIG.GOAL_ID="+bean.getGoalId()
	       +" AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE=TO_DATE('"+bean.getAssesmentDate()+"','DD-MM-YYYY')"
	       +" AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE IS NOT NULL";
	      	if ((bean.getEmpId() != null) && (!bean.getEmpId().equals(""))) {
	      			 querySettingData+= " AND HRMS_GOAL_EMP_HDR.EMP_ID=" + bean.getEmpId();
	      	 }
	      	querySettingData+= " and E.EMP_STATUS='S' ORDER BY GOAL_ASSESSMENT_DATE,EMP_GOAL_ASSESSMENT_ID";
	      
	     Object[][] settingDataObj=getSqlModel().getSingleResult(querySettingData);
	     
	     try {
				String numManagersQuery="SELECT NVL(MAX(GOAL_ASSESSMENT_LEVEL),0) FROM HRMS_GOAL_EMP_ASSESSMENT_DTL "
										+" WHERE EMP_GOAL_ASSESSMENT_ID  IN (SELECT EMP_GOAL_ASSESSMENT_ID FROM HRMS_GOAL_EMP_ASSESSMENT_HDR  WHERE GOAL_ID  IN (SELECT GOAL_HDR_ID FROM HRMS_GOAL_EMP_HDR WHERE GOAL_ID="+bean.getGoalId()+"))";
				Object[][] numManagersData=getSqlModel().getSingleResult(numManagersQuery);
				if (numManagersData!=null && numManagersData.length>0) {
					if(!String.valueOf(numManagersData[0][0]).equals("")){
					numManagers= Integer.parseInt(String.valueOf(numManagersData[0][0]));
					checkCount += numManagers;
					}
				}else{
					numManagers=0;
				}
				} catch (RuntimeException e) {
				e.printStackTrace();
				}
		
				String query = "SELECT  HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID || '#' || GOAL_ASSESSMENT_LEVEL || '#' || HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ID,"
				   	 +" R.EMP_FNAME||' '||R.EMP_MNAME||' '||R.EMP_LNAME REVIEWER_NAME ,  "
				   	 +" HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_RATING, HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_COMMENT"
				   	 +" FROM HRMS_GOAL_EMP_DTL "
				   	 +" INNER JOIN HRMS_GOAL_EMP_HDR ON HRMS_GOAL_EMP_HDR.GOAL_HDR_ID=HRMS_GOAL_EMP_DTL.GOAL_HDR_ID "
				   	 +" LEFT JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID" 
				   	 +" AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE=TO_DATE('"+bean.getAssesmentDate()+"','DD-MM-YYYY')) "
				   	 +" LEFT JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON "
				   	 +" ( HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID "
				   	 +" AND HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID ) "
				   	 +" LEFT JOIN HRMS_GOAL_SELF_ASSESSMENT_DTL ON("
				   	 +" HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID"
				   	 +" AND HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID )"	
				   	 +" INNER JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID "
				   	 +" LEFT JOIN HRMS_EMP_OFFC R ON HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ASSESSER_ID=R.EMP_ID "
				   	 +" WHERE HRMS_GOAL_CONFIG.GOAL_ID="+bean.getGoalId();
				    HashMap map = getSqlModel().getSingleResultMap(query, 0, 2);
				    
				   int cols = 10 + numManagers*3;	
				   Object final_obj_new[][]=new Object[settingDataObj.length][10 + numManagers*3];
				   if (goalHdrObj!=null && goalHdrObj.length > 0) {
				   if (settingDataObj!=null && settingDataObj.length > 0) {
				   for (int i = 0; i < settingDataObj.length; i++) {
					for (int j = 0; j < settingDataObj[0].length-4; j++) {
						final_obj_new[i][j]=settingDataObj[i][j];
					}
				   }
				  
				   for (int j = 0; j < settingDataObj.length; j++) {
					   int manConut=0;
					   for (int i = 1; i <= numManagers; i++) {
						   Object[][] manObj =(Object[][])map
					        .get(String.valueOf(settingDataObj[j][13]+"#"+i+"#"+settingDataObj[j][10]));
						   try {
							final_obj_new[j][10 + manConut++] = manObj[0][1];
							final_obj_new[j][10 + manConut++] = manObj[0][2];
							final_obj_new[j][10 + manConut++] = manObj[0][3];
						} catch (Exception e) {
							// TODO: handle exception
						}
						 
					   }
				   }
				 
				   
				   
				  
				   
				  
				   rds.setTotalColumns(checkCount*3 + 10);
				   String[] columns = new String[checkCount*3 + 10];
					  int[] GoalcellAlign = new int[checkCount*3 + 10];
					  int[] GoalcellWidth = new int[checkCount*3 + 10];
					  boolean[] Goalcellwrap = new boolean[checkCount*3 + 10];

					  int chkcnt = 0;
					  columns[chkcnt] = "Employee Id";
					  GoalcellAlign[chkcnt] = 0;
					  GoalcellWidth[chkcnt] = 40;
					  Goalcellwrap[chkcnt] = false;
					  chkcnt++;
					  columns[chkcnt] = "Employee Name";
					  GoalcellAlign[chkcnt] = 0;
					  GoalcellWidth[chkcnt] = 40;
					  Goalcellwrap[chkcnt] = false;
					  chkcnt++;

					  columns[chkcnt] = "Goal Description";
					  GoalcellAlign[chkcnt] = 0;
					  GoalcellWidth[chkcnt] = 43;
					  Goalcellwrap[chkcnt] = false;
					  chkcnt++;
					  columns[chkcnt] = "Goal Weightage";
					  GoalcellAlign[chkcnt] = 2;
					  GoalcellWidth[chkcnt] = 40;
					  Goalcellwrap[chkcnt] = false;
					  chkcnt++;
					  columns[chkcnt] = "Goal Category";
					  GoalcellAlign[chkcnt] = 0;
					  GoalcellWidth[chkcnt] = 35;
					  Goalcellwrap[chkcnt] = false;
					  chkcnt++;
					  columns[chkcnt] = "Category Weightage";
					  GoalcellAlign[chkcnt] = 2;
					  GoalcellWidth[chkcnt] = 45;
					  Goalcellwrap[chkcnt] = false;
					  chkcnt++;
					  columns[chkcnt] = "Goal Comments";
					  GoalcellAlign[chkcnt] = 0;
					  GoalcellWidth[chkcnt] = 40;
					  Goalcellwrap[chkcnt] = false;
					  chkcnt++;
					  columns[chkcnt] = "Achievement date";
					  GoalcellAlign[chkcnt] = 1;
					  GoalcellWidth[chkcnt] = 47;
					  Goalcellwrap[chkcnt] = false;
					  chkcnt++;
					  columns[chkcnt] = "Self Ratings";
					  GoalcellAlign[chkcnt] = 2;
					  GoalcellWidth[chkcnt] = 35;
					  Goalcellwrap[chkcnt] = false;
					  chkcnt++;
					  //added by Prajakta B for comments in report
					  columns[chkcnt] = "Self Comments";
					  GoalcellAlign[chkcnt] = 0;
					  GoalcellWidth[chkcnt] = 40;
					  Goalcellwrap[chkcnt] = false;
					  chkcnt++;
					  for (int a1 = 0; a1 < numManagers; a1++)
					  {
						columns[chkcnt] = ("Manager " + (a1 + 1) + " Name");
						GoalcellAlign[chkcnt] = 2;
						GoalcellWidth[chkcnt] = 35;
						Goalcellwrap[chkcnt] = false;
						chkcnt++;  
					    columns[chkcnt] = ("Manager " + (a1 + 1) + " Rating");
					    GoalcellAlign[chkcnt] = 2;
					    GoalcellWidth[chkcnt] = 35;
					    Goalcellwrap[chkcnt] = false;
					    chkcnt++;
					    columns[chkcnt] = ("Manager " + (a1 + 1) + " Comments");
					    GoalcellAlign[chkcnt] = 0;
					    GoalcellWidth[chkcnt] = 40;
					    Goalcellwrap[chkcnt] = false;
					    chkcnt++;
					  }
					  //Ends added by Prajakta B for comments in report
					  TableDataSet tdstable = new TableDataSet();
					  tdstable.setHeader(columns);
					  tdstable.setData(final_obj_new);
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
	}
				   else{
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
	}catch (Exception e) {
		 System.out.println("In Forth Catch...........");
		e.printStackTrace();
	}
	/*
    try{
      ReportGenerator rg = null;
      String reportType = bean.getReport();
      String title = "Goal  Assessment Report ";
      ReportDataSet rds = new ReportDataSet();
      String fileName = "Goal  Assessment Report_" + Utility.getRandomNumber(1000);
      rds.setFileName(fileName);

      rds.setReportName(title);
      rds.setPageSize("A4");
      rds.setPageOrientation("landscape");
      rds.setUserEmpId(bean.getUserEmpId());
      rds.setReportType(reportType);

      String whereClause = "";
      int checkCount = 0;
      String filters = "";
      String GoalHdrId = "";
      String EmpAssmentId = "";
      int numManagers = 0;

      filters = filters + "Goal Name : " + bean.getGoalName();
      String goalFrmDateQuery = "SELECT TO_CHAR(HRMS_GOAL_CONFIG.GOAL_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(HRMS_GOAL_CONFIG.GOAL_TO_DATE,'DD-MM-YYYY') FROM HRMS_GOAL_CONFIG  WHERE HRMS_GOAL_CONFIG.GOAL_ID=" + bean.getGoalId();
      Object[][] goalFrmDateObj = getSqlModel().getSingleResult(goalFrmDateQuery);
      filters = filters + "\n From Date : " + String.valueOf(goalFrmDateObj[0][0]);
      filters = filters + "\n To Date : " + String.valueOf(goalFrmDateObj[0][1]);
      if ((bean.getEmpId() != null) && (!bean.getEmpId().equals(""))) {
        whereClause = whereClause + " AND HRMS_GOAL_EMP_HDR.EMP_ID=" + bean.getEmpId();
        filters = filters + "\n   Employee Name : " + bean.getEmpToken() + "-" + bean.getEmpName();
      }

      String query = " SELECT HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_CONFIG.GOAL_NAME,"
    	  			+ " TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY') ,"
    	  			+ " HRMS_GOAL_EMP_HDR.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN, "
    	  			+ " (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' ||  HRMS_EMP_OFFC.EMP_LNAME), "
    	  			+ " NVL(HRMS_CENTER.CENTER_NAME,'  '),NVL(HRMS_RANK.RANK_NAME,'  '), NVL(HRMS_DEPT.DEPT_NAME,' ')"
    	  			+ " FROM HRMS_GOAL_EMP_HDR  "
    	  			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_HDR.EMP_ID ) "
    	  			+ " LEFT JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID = HRMS_GOAL_EMP_HDR.GOAL_ID)" 
    	  			+ " LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE)"
    	  			+ " LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID)  "
    	  			+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  "
    	  			+ " LEFT JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
    	  			+ " WHERE   HRMS_GOAL_EMP_HDR.GOAL_ID=" + bean.getGoalId();
      query = query + whereClause;
      query = query + "  ORDER BY EMP_ID";

      Object[][] queryData = getSqlModel().getSingleResult(query);
      if ((queryData != null) && (queryData.length > 0)) {
        GoalHdrId = String.valueOf(queryData[0][0]);
      }
      if ((queryData != null) && (queryData.length > 0)) {
        String numManagersData = "SELECT MAX(GOAL_PATH_ID) "
        	 					+ " FROM HRMS_GOAL_PATH "
        	 					+ " WHERE GOAL_CODE IN(SELECT HRMS_GOAL_EMP_HDR.GOAL_HDR_ID "
        	 					+ " from HRMS_GOAL_EMP_HDR where GOAL_ID=" + bean.getGoalId() + ")";
        Object[][] numManagersObj = getSqlModel().getSingleResult(numManagersData);
        if ((numManagersObj != null) && (numManagersObj[0][0] != null)) {
          numManagers = Integer.parseInt(String.valueOf(numManagersObj[0][0]));
          checkCount += numManagers;
        }

      }

      if ((bean.getAssesmentDate() != null) && (!bean.getAssesmentDate().equals(""))) {
        whereClause = whereClause + " AND GOAL_ASSESSMENT_DATE = TO_DATE('" + bean.getAssesmentDate() + "','DD-MM-YYYY')";
        String dateValue = bean.getAssesmentDate();
        String[] datearray = dateValue.split("-");
        int month = Integer.parseInt(datearray[1]);
        String monthValue = Utility.month(month);
        //filters = filters + "\n Assessment :" + monthValue + " - " + datearray[2];
      }

      rds.setTotalColumns(checkCount + 9);
      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, 
          this.session, this.context, request);
      } else {
        logger.info("################# ATTACHMENT PATH #############" + 
          reportPath + fileName);
        rg = new ReportGenerator(rds, 
          reportPath, this.session, this.context, request);

        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
        request.setAttribute("action", "/pas/GoalAssesReport_input.action");
        request.setAttribute("fileName", fileName + "." + reportType);
      }
      TableDataSet filterData = new TableDataSet();
      filterData.setData(new Object[][] { { filters } });
      filterData.setCellAlignment(new int[1]);
      filterData.setBodyFontStyle(0);
      filterData.setCellWidth(new int[] { 100 });
      filterData.setCellColSpan(new int[] { checkCount + 9 });
      filterData.setCellNoWrap(new boolean[] { true });
      filterData.setBlankRowsBelow(1);
      filterData.setBorder(Boolean.valueOf(false));
      rg.addTableToDoc(filterData);

      HashMap map = null;
      if (!GoalHdrId.equals(""))
      {
        String queryAssesmentData = "SELECT TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') , "
        	+ " EMP_GOAL_ASSESSMENT_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
        	+ " (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' ||  HRMS_EMP_OFFC.EMP_LNAME),"
        	+ " GOAL_DESCRIPTION, GOAL_WEIGHTAGE, NVL(GOAL_CATEGORY,' '),GOAL_CATEGORY_WEIGHTAGE,"
        	+ " NVL(GOAL_COMMENTS,''),TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY') "
        	+ " FROM HRMS_GOAL_EMP_DTL "
        	+ " INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID) "
        	+ " LEFT JOIN HRMS_GOAL_EMP_HDR ON (HRMS_GOAL_EMP_HDR.GOAL_HDR_ID = HRMS_GOAL_EMP_DTL.GOAL_HDR_ID)  "
        	+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =   HRMS_GOAL_EMP_HDR.EMP_ID ) "
        	+ " LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE) "
        	+ " LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID)"
        	+ " WHERE GOAL_DTL_STATUS='A' AND HRMS_GOAL_EMP_HDR.GOAL_HDR_ID IN"
        	+ " (SELECT HRMS_GOAL_EMP_HDR.GOAL_HDR_ID from HRMS_GOAL_EMP_HDR where GOAL_ID=" + bean.getGoalId() + ")"  
        	+ " AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE <=SYSDATE";
        queryAssesmentData = queryAssesmentData + whereClause;
        queryAssesmentData = queryAssesmentData + " ORDER BY GOAL_ASSESSMENT_DATE,HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID,EMP_GOAL_ASSESSMENT_ID ";
        map = getSqlModel().getSingleResultMap(queryAssesmentData, 0, 2);

        String assesDate = "SELECT TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') "
       					+ " FROM HRMS_GOAL_ASSESSMENT_CONFIG "
       					+ "  WHERE GOAL_ID=" + bean.getGoalId();
        if ((bean.getAssesmentDate() != null) && (!bean.getAssesmentDate().equals(""))) {
        	assesDate= assesDate + " AND GOAL_ASSESSMENT_DATE = TO_DATE('"+bean.getAssesmentDate()+"','DD-MM-YYYY')";
        }
        Object[][] assesDateObj = getSqlModel().getSingleResult(assesDate);
        if ((assesDateObj!= null) && (map != null)) {
          for (int i = 0; i < assesDateObj.length; i++) { 
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
        	 
            Object[][] goalDataAssesDateWise = 
              (Object[][])map
              .get(String.valueOf(assesDateObj[i][0]));
            if ((goalDataAssesDateWise != null) && 
              (goalDataAssesDateWise.length > 0)) {
            	 
              Object[][] creditheadtitle = new Object[1][1];
              creditheadtitle[0][0] = checkNull(String.valueOf(assesDateObj[i][0]));
              
              int cols = 10 + numManagers*2;
              System.out.println("Cols" + cols);
              Object[][] final_Object = new Object[goalDataAssesDateWise.length][cols];

              for (int j = 0; j < goalDataAssesDateWise.length; j++) {
                for (int k = 0; k < goalDataAssesDateWise[0].length - 2; k++) {
                  final_Object[j][k] = goalDataAssesDateWise[j][(k + 2)];
                }

              }

              String assesmentIdQuery = "SELECT EMP_GOAL_ASSESSMENT_ID from HRMS_GOAL_EMP_ASSESSMENT_HDR  INNER JOIN HRMS_GOAL_EMP_HDR ON (HRMS_GOAL_EMP_HDR.GOAL_HDR_ID = HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID) where HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID  in (SELECT HRMS_GOAL_EMP_HDR.GOAL_HDR_ID from HRMS_GOAL_EMP_HDR where GOAL_ID=" + 
                bean.getGoalId() + ") AND GOAL_ASSESSMENT_DATE = TO_DATE('" + String.valueOf(assesDateObj[i][0]) + "','DD-MM-YYYY')";
              assesmentIdQuery = assesmentIdQuery + whereClause;
              assesmentIdQuery = assesmentIdQuery + " ORDER BY GOAL_ASSESSMENT_DATE,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID ";
              Object[][] assesmentIdObj = getSqlModel().getSingleResult(assesmentIdQuery);
              int h = 0;
              for (int k = 0; k < assesmentIdObj.length; k++) {
                EmpAssmentId = String.valueOf(assesmentIdObj[k][0]);
                String detailId = " SELECT  SELF_GOAL_DTL_ID FROM HRMS_GOAL_SELF_ASSESSMENT_DTL WHERE SELF_GOAL_ASSESSMENT_ID=" + EmpAssmentId+"ORDER BY SELF_GOAL_DTL_ID";
                Object[][] detailIdObj = getSqlModel().getSingleResult(detailId);
                Object[][] selfGoalDetails = (Object[][])null;

                HashMap selfAssesmentDet = getSelfAssesmentDetails(EmpAssmentId);
                if (selfAssesmentDet == null)
                  continue;
                if (detailIdObj != null) {
                  for (int m = 0; m < detailIdObj.length; m++) {
                    selfGoalDetails = 
                      (Object[][])selfAssesmentDet
                      .get(String.valueOf(detailIdObj[m][0]));
                    if (selfGoalDetails != null) {
                      for (int l = 0; l < selfGoalDetails.length; l++) {
                        for (int n = 0; n < selfGoalDetails[0].length - 1; n++) {
                          System.out.println("Deatils" + selfGoalDetails[l][n]);
                          final_Object[h][(n + 8)] = selfGoalDetails[l][(n + 1)];
                          
                        }
                        h++;
                      }
                    }

                  }

                }

              }

              int s = 0;
              for (int k = 0; k < assesmentIdObj.length; k++) {
                EmpAssmentId = String.valueOf(assesmentIdObj[k][0]);
                String detailId = " SELECT  SELF_GOAL_DTL_ID FROM HRMS_GOAL_SELF_ASSESSMENT_DTL WHERE SELF_GOAL_ASSESSMENT_ID=" + EmpAssmentId+"ORDER BY SELF_GOAL_DTL_ID";
                Object[][] detailIdObj = getSqlModel().getSingleResult(detailId);
                HashMap EmpAssesmentDet = getEmpAssesmentDetails(EmpAssmentId);
                if ((EmpAssesmentDet == null) || 
                  (detailIdObj == null)) continue;
                for (int m = 0; m < detailIdObj.length; m++) {
                  int a = 0;
                  Object[][] empGoalDetails = 
                    (Object[][])EmpAssesmentDet
                    .get(String.valueOf(detailIdObj[m][0]));
                  if (empGoalDetails != null) {
                    for (int l = 0; l < empGoalDetails.length; l++) {
                      for (int n = 0; n < empGoalDetails[0].length - 1; n++) {
                        System.out.println("Deatils" + empGoalDetails[l][n] + "N Value" + n);

                        final_Object[s][(a + 10)] = empGoalDetails[l][(n + 1)];
                        a++;
                      }

                    }

                    s++;
                  }

                }

              }

              String[] columns = new String[checkCount*2 + 10];
              int[] GoalcellAlign = new int[checkCount*2 + 10];
              int[] GoalcellWidth = new int[checkCount*2 + 10];
              boolean[] Goalcellwrap = new boolean[checkCount*2 + 10];

              int chkcnt = 0;
              columns[chkcnt] = "Employee Id";
              GoalcellAlign[chkcnt] = 0;
              GoalcellWidth[chkcnt] = 40;
              Goalcellwrap[chkcnt] = false;
              chkcnt++;
              columns[chkcnt] = "Employee Name";
              GoalcellAlign[chkcnt] = 0;
              GoalcellWidth[chkcnt] = 40;
              Goalcellwrap[chkcnt] = false;
              chkcnt++;

              columns[chkcnt] = "Goal Description";
              GoalcellAlign[chkcnt] = 0;
              GoalcellWidth[chkcnt] = 43;
              Goalcellwrap[chkcnt] = false;
              chkcnt++;
              columns[chkcnt] = "Goal Weightage";
              GoalcellAlign[chkcnt] = 2;
              GoalcellWidth[chkcnt] = 40;
              Goalcellwrap[chkcnt] = false;
              chkcnt++;
              columns[chkcnt] = "Goal Category";
              GoalcellAlign[chkcnt] = 0;
              GoalcellWidth[chkcnt] = 35;
              Goalcellwrap[chkcnt] = false;
              chkcnt++;
              columns[chkcnt] = "Category Weightage";
              GoalcellAlign[chkcnt] = 2;
              GoalcellWidth[chkcnt] = 45;
              Goalcellwrap[chkcnt] = false;
              chkcnt++;
              columns[chkcnt] = "Goal Comments";
              GoalcellAlign[chkcnt] = 0;
              GoalcellWidth[chkcnt] = 40;
              Goalcellwrap[chkcnt] = false;
              chkcnt++;
              columns[chkcnt] = "Achievement date";
              GoalcellAlign[chkcnt] = 1;
              GoalcellWidth[chkcnt] = 47;
              Goalcellwrap[chkcnt] = false;
              chkcnt++;
              columns[chkcnt] = "Self Ratings";
              GoalcellAlign[chkcnt] = 2;
              GoalcellWidth[chkcnt] = 35;
              Goalcellwrap[chkcnt] = false;
              chkcnt++;
              //added by Prajakta B for comments in report
              columns[chkcnt] = "Self Comments";
              GoalcellAlign[chkcnt] = 0;
              GoalcellWidth[chkcnt] = 40;
              Goalcellwrap[chkcnt] = false;
              chkcnt++;
              for (int a = 0; a < numManagers; a++)
              {
                columns[chkcnt] = ("Manager " + (a + 1) + " Rating");
                GoalcellAlign[chkcnt] = 2;
                GoalcellWidth[chkcnt] = 35;
                Goalcellwrap[chkcnt] = false;
                chkcnt++;
                columns[chkcnt] = ("Manager " + (a + 1) + " Comments");
                GoalcellAlign[chkcnt] = 0;
                GoalcellWidth[chkcnt] = 40;
                Goalcellwrap[chkcnt] = false;
                chkcnt++;
              }
 //Ends added by Prajakta B for comments in report
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
    catch (Exception e)
    {
      e.printStackTrace();
    }
*/}

  public String checkNull(String result) {
    if ((result == null) || (result.equals("null")) || (result.equals(" ")) || 
      (result.equals(""))) {
      return "";
    }

    return result;
  }

  /**@modified by @author Prajakta.Bhandare for adding comments in report
 * @param assmentId
 * @return
 */
/*public HashMap getSelfAssesmentDetails(String assmentId)
  {
    Object[][] result = (Object[][])null;
    String query = "SELECT  SELF_GOAL_DTL_ID,SELF_GOAL_RATING, NVL(SELF_GOAL_COMMENT,'') FROM HRMS_GOAL_SELF_ASSESSMENT_DTL INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID=" + 
      assmentId + ")"  
      +" LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) "
      +" ORDER BY SELF_GOAL_DTL_ID,SELF_ASSESSMENT_DTL_ID";
    System.out.println("The Query : " + query);
    result = getSqlModel().getSingleResult(
      query);
    HashMap map = getSqlModel().getSingleResultMap(query, 0, 2);
    return map;
  }*/

  /**@modified by @author Prajakta.Bhandare for adding comments in report
 * @param assmentId
 * @return
 */
public HashMap getEmpAssesmentDetails(String assmentId,String assesDate) {
     String query = "SELECT  HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_ID || '#' || GOAL_ASSESSMENT_LEVEL || '#' || HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ID,"
   	 +" R.EMP_FNAME||' '||R.EMP_MNAME||' '||R.EMP_LNAME REVIEWER_NAME ,  "
   	 +" HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_RATING, HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_COMMENT"
   	 +" FROM HRMS_GOAL_EMP_DTL "
   	 +" INNER JOIN HRMS_GOAL_EMP_HDR ON HRMS_GOAL_EMP_HDR.GOAL_HDR_ID=HRMS_GOAL_EMP_DTL.GOAL_HDR_ID "
   	 +" LEFT JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID" 
   	 +" AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE=TO_DATE('"+assesDate+"','DD-MM-YYYY')) "
   	 +" LEFT JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON "
   	 +" ( HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID "
   	 +" AND HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID ) "
   	 +" LEFT JOIN HRMS_GOAL_SELF_ASSESSMENT_DTL ON("
   	 +" HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID"
   	 +" AND HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID )"	
   	 +" INNER JOIN HRMS_GOAL_CONFIG ON HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID "
   	 +" LEFT JOIN HRMS_EMP_OFFC R ON HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ASSESSER_ID=R.EMP_ID "
   	 +" WHERE HRMS_GOAL_CONFIG.GOAL_ID="+assmentId+" ";
   // +" AND HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE IS NOT NULL";
   	   
   
    HashMap map = getSqlModel().getSingleResultMap(query, 0, 2);
    return map;
  }
}