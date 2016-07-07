package org.paradyne.model.PAS;
/*
 * @Hemant Nagam
 * Date:20-06-2008
 * 
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.PAS.AppraisalStatistic;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

public class AppraisalStatisticModel extends ModelBase {

	
	public void getAppraisalDtls(AppraisalStatistic bean){
		
		String sql="SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),"
				  +"TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID FROM PAS_APPR_CALENDAR 	WHERE APPR_ID="+bean.getApprId();
		Object data[][]=getSqlModel().getSingleResult(sql);
		
		if(data!=null && data.length>0){
			bean.setApprFrom(String.valueOf(data[0][1]));
			bean.setApprTo(String.valueOf(data[0][2]));
		}
		
	}
	
	
	
	/**
	 * @param request
	 * @param response
	 * @param appStat
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, AppraisalStatistic bean) {
		
		String s = "Appraisal Statistics Report";
		String FINAL_QUERY ="";
			
		FINAL_QUERY = "SELECT COUNT(PAS_APPR_SCORE.APPR_ID) FROM PAS_APPR_SCORE  "
					+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=PAS_APPR_SCORE.EMP_ID) "
					+"	INNER JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_SCORE.APPR_ID )"
					+"  WHERE PAS_APPR_SCORE.APPR_ID= "+bean.getApprId();//+" AND APPR_SCORE_FINALIZE='Y' ";
		
		String s1 = " ",s2="";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",s);
		String type = bean.getAppstat();
		String filter = "";
		String FILTER_QUERY = " ";
		String condition = " ";
		int option = 1;
		 
		 
 try{ 
		if (type.equals("desi")) { //checking for designation
			filter = "Designation";
			//FILTER_QUERY = "SELECT RANK_ID,RANK_NAME FROM HRMS_RANK ORDER BY RANK_NAME";
			FILTER_QUERY = "SELECT  DISTINCT RANK_ID,RANK_NAME FROM HRMS_RANK"  
						  +" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"  
						  +" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)"  
						  +" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getApprId()+" ORDER BY RANK_ID"; 
			condition = " AND HRMS_EMP_OFFC.EMP_RANK = ";
			
		} else if (type.equals("branch")) {//checking for branchwise
			filter = "Branch";
			//FILTER_QUERY = " SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_NAME ";
			FILTER_QUERY = "SELECT  DISTINCT CENTER_ID, CENTER_NAME FROM HRMS_CENTER"  
						  +" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_CENTER.CENTER_ID = hrms_emp_offc.EMP_CENTER)"  
						  +" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)"  
						  +" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getApprId()+" ORDER BY CENTER_ID ";
				
				
			condition = " AND HRMS_EMP_OFFC.EMP_CENTER = ";
			
		} else if (type.equals("dept")) { //checking for department wise
			filter = "Department";
			//FILTER_QUERY = " SELECT  DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_NAME ";
			FILTER_QUERY = " SELECT DISTINCT DEPT_ID,DEPT_NAME FROM  HRMS_DEPT"  
						  +" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DEPT.DEPT_ID = hrms_emp_offc.EMP_DEPT)"  
						  +" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (hrms_emp_offc.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)"  
						  +" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getApprId()+" ORDER BY DEPT_ID"; 
			condition = "  AND HRMS_EMP_OFFC.EMP_DEPT = ";

		} else {
			filter = "Organization"; 
			FILTER_QUERY = " ";
			// condition="where APPR_SCORE_DESC = R1 ";
			option = 0;

		}
		
		
		Object data[][] = null;
		Object finalobj[][] = null;
		Object totobj[][]=null;//new Object[1][7];
		int  count=0,sum=0;
		
		String ratingSql = "SELECT APPR_SCORE_VALUE,APPR_SCORE_FROM, APPR_SCORE_TO FROM PAS_APPR_OVERALL_RATING WHERE APPR_ID="+bean.getApprId()+" ORDER BY APPR_SCORE_ID ";
		Object ratingData[][] = getSqlModel().getSingleResult(ratingSql);
		
		
		totobj = new Object[1][2+ratingData.length];
		
		if (option == 1) {//DESIG/BRANCH/DEPT wise statistics
					
					//Retrieve filter data
					data = getSqlModel().getSingleResult(FILTER_QUERY);
					if (data!=null && data.length > 0) {		
						finalobj = new Object[data.length][7];
							
							for (int i = 0; i < data.length; i++) {								
								count=0;
				                int index = 1;
								for (int j = 0; j < ratingData.length; j++) {//////////////
									if (option == 1) {
										s1 = FINAL_QUERY + condition + data[i][0]
										 + " AND (APPR_FINAL_SCORE >= " + ratingData[j][1]+ " AND APPR_FINAL_SCORE<="+ratingData[j][2]+")";
										
									}
									Object[][] test = getSqlModel().getSingleResult(s1);
									finalobj[i][index] = test[0][0];
									count=count+Integer.parseInt(String.valueOf(finalobj[i][index]));
									index++;
								} //for j ends
								
								finalobj[i][0] = data[i][1];
								finalobj[i][index] =count;
				                sum+=count;
								
							} //for data ends
				} //if data ends
					
				//	finalobj[i][6]=sum;
					
					
					totobj[0][0]=" Organization wise status : ";
			        int totalrecords=0;
			        int index = 1;
			        
			        for (int j = 0; j < ratingData.length; j++) {
						 s2 = FINAL_QUERY+" AND (APPR_FINAL_SCORE >= " + ratingData[j][1]+ " AND APPR_FINAL_SCORE<="+ratingData[j][2]+")";//hkn
						 Object [][]total=getSqlModel().getSingleResult(s2);
					     totobj[0][index]=total[0][0];
					     totalrecords+=Integer.parseInt(String.valueOf(totobj[0][index]));
					     index++;
					
					}
					totobj[0][index]=totalrecords;
			
		}
		else {//ORGANIZATION WISE STATISTICS
				
				finalobj = new Object[1][2+ratingData.length];
				int orgcount = 0;
				int index = 1;
				
				for (int j = 0; j < ratingData.length; j++) {
	                
					s1 = FINAL_QUERY + " AND (APPR_FINAL_SCORE >= " + ratingData[j][1]+ " AND APPR_FINAL_SCORE<="+ratingData[j][2]+")";
					Object[][] test = getSqlModel().getSingleResult(s1);
					finalobj[0][index] = test[0][0];
					orgcount=orgcount+Integer.parseInt(String.valueOf(finalobj[0][index]));
					index++;
	                
				}
	
				finalobj[0][0] = "Organization";
				finalobj[0][finalobj[0].length-1]=orgcount;
					
		} //else ends		
		
		
		String colnames[] =null;// new int[2+];//{filter, "Exceeds Expectation", "Exceeds In Some", "Meets Expectation","Meets Most","Does Not Meet","Total" };
		int cellwidth[] = null;//{ 40, 17, 17, 17, 17, 17,10};
		int alignment[] = null;//{ 0, 1, 1, 1, 1, 1,1 };
		
		
		
		//CREATE DYNAMIC COLUMN NAMES FOR THE TABLE
		if(ratingData!=null && ratingData.length>0){
			
			colnames = new String[2+ratingData.length];
			cellwidth = new int[2+ratingData.length];
			alignment = new int[2+ratingData.length];
			
			colnames [0] = filter;
			colnames[colnames.length-1] = "Total";
			cellwidth[0] = 40;
			cellwidth[colnames.length-1] = 10; 
			alignment[0] = 0;
			alignment[colnames.length-1] = 1; 
			int index=1;
			
			for(int j=0;j<ratingData.length;j++){
				colnames[index] = String.valueOf(ratingData[j][0]);
				cellwidth[index] = 17;
				alignment[index] = 0;
				index++;
			}
			
		}else{
			colnames = new String[2];
			cellwidth = new int[2];
			alignment = new int[2];
			
			colnames [0] = filter;
			colnames[colnames.length-1] = "Total";
			cellwidth[0] = 40;
			cellwidth[colnames.length-1] = 10;
			alignment[0] = 0;
			alignment[colnames.length-1] = 1;
		}
		

		
		rg.addFormatedText(filter+"- Wise Appraisal Statistics Report\n ", 6, 0, 1, 0);
		//rg.addFormatedText("Appraisal Code: "+bean.getApprCode()+"Appraisal From: "+bean.getApprFrom() + " Appraisal To: "+bean.getApprTo(),1, 0, 0, 0);//Appraisal Code
		
		String apprStr[]={"\n\n\nAppraisal Code : ",bean.getApprCode()," \nAppraisal Start Date : ",bean.getApprFrom()," \nAppraisal End Date : ",bean.getApprTo()};
		int style[]={1,5,1,5,1,5};
		//rg.addFormatedText(apprStr,style,0,0,0);
		
		Object apprData[][] = new Object[4][2];
		apprData[0][0] = " 			";
		apprData[0][1] = " 			";
		apprData[1][0] = "Appraisal Code :";
		apprData[1][1] = ""+bean.getApprCode();
		apprData[2][0] = "Appraisal Start Date :";
		apprData[2][1] = ""+bean.getApprFrom();
		apprData[3][0] = "Appraisal End Date :";
		apprData[3][1] = ""+bean.getApprTo();
		int w[] = {10,30};
		int a[] = {0,0};
		int b[] = {0,0};
		int font[] = {9,9};
		
		rg.tableBodyNoBorder(apprData, w, a, b, font);
		/*
		rg.addText("\n\nAppraisal Code : "+bean.getApprCode(), 0, 0, 0);
		rg.addText("Appraisal Start Date : "+bean.getApprFrom(), 0, 0, 0);
		rg.addText("Appraisal End Date : "+bean.getApprTo(), 0, 0, 0);
		*/
		//rg.addText("\n\n\nAppraisal Code: "+bean.getApprCode()+"Appraisal From: "+bean.getApprFrom() + " Appraisal To: "+bean.getApprTo(), 1, 0, 2);
		
		
		String sysDate="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object dateObj[][] = getSqlModel().getSingleResult(sysDate);
		
		
		rg.addText("Date :"+String.valueOf(dateObj[0][0]), 1, 2, 2);
		rg.addFormatedText("", 0, 0, 1, 0);
		rg.addText("\n", 0, 1, 0);
		rg.tableBody(colnames, finalobj, cellwidth, alignment);
		rg.addText("\n", 0, 1, 0);
		if (option == 1)
			rg.tableBody(totobj, cellwidth, alignment);
		}catch(Exception e){
			
				rg.addFormatedText("\n\n No records to display ", 0, 0, 1, 0);
		}
		
		rg.createReport(response);
		
	}



	public void getStatisticReport(AppraisalStatistic bean,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {

		ReportDataSet rds = new ReportDataSet();
		String reportType = bean.getReport(); // Pdf/Xls/Doc
		rds.setReportType(reportType);
		String fileName = "Appraisal Statistics Report"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Appraisal Statistics Report");
		rds.setShowPageNo(true);

		rds.setGeneratedByText(bean.getUserEmpId());
		rds.setUserEmpId(bean.getUserEmpId());
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("portrait");
		rds.setTotalColumns(4);

		org.paradyne.lib.ireportV2.ReportGenerator rg = null;

		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);
		} else {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
					reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "."
					+ reportType);
			request.setAttribute("fileName", fileName + "." + reportType);
			request.setAttribute("action", "AppraisalStatistic_input.action");
		}
		
		String FINAL_QUERY ="";
			
		FINAL_QUERY = "SELECT COUNT(PAS_APPR_SCORE.APPR_ID) FROM PAS_APPR_SCORE  "
					+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=PAS_APPR_SCORE.EMP_ID) "
					+"	INNER JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_SCORE.APPR_ID )"
					+"  WHERE PAS_APPR_SCORE.APPR_ID= "+bean.getApprId();//+" AND APPR_SCORE_FINALIZE='Y' ";
		
		String s1 = " ",s2="";
		String type = bean.getAppstat();
		String filter = "";
		String FILTER_QUERY = " ";
		String condition = " ";
		int option = 1;
		 
		 
 try{ 
		if (type.equals("desi")) { //checking for designation
			filter = "Designation";
			FILTER_QUERY = "SELECT  DISTINCT RANK_ID,RANK_NAME FROM HRMS_RANK"  
						  +" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"  
						  +" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)"  
						  +" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getApprId()+" ORDER BY RANK_ID"; 
			condition = " AND HRMS_EMP_OFFC.EMP_RANK = ";
			
		} else if (type.equals("branch")) {//checking for branchwise
			filter = "Branch";
			FILTER_QUERY = "SELECT  DISTINCT CENTER_ID, CENTER_NAME FROM HRMS_CENTER"  
						  +" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_CENTER.CENTER_ID = hrms_emp_offc.EMP_CENTER)"  
						  +" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)"  
						  +" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getApprId()+" ORDER BY CENTER_ID ";
				
				
			condition = " AND HRMS_EMP_OFFC.EMP_CENTER = ";
			
		} else if (type.equals("dept")) { //checking for department wise
			filter = "Department";
			FILTER_QUERY = " SELECT DISTINCT DEPT_ID,DEPT_NAME FROM  HRMS_DEPT"  
						  +" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DEPT.DEPT_ID = hrms_emp_offc.EMP_DEPT)"  
						  +" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (hrms_emp_offc.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)"  
						  +" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getApprId()+" ORDER BY DEPT_ID"; 
			condition = "  AND HRMS_EMP_OFFC.EMP_DEPT = ";

		} else {
			filter = "Organization"; 
			FILTER_QUERY = " ";
			option = 0;

		}
		
		
		Object data[][] = null;
		Object finalobj[][] = null;
		Object totobj[][]=null;//new Object[1][7];
		int  count=0,sum=0;
		
		String ratingSql = "SELECT APPR_SCORE_VALUE,APPR_SCORE_FROM, APPR_SCORE_TO FROM PAS_APPR_OVERALL_RATING WHERE APPR_ID="+bean.getApprId()+" ORDER BY APPR_SCORE_ID ";
		Object ratingData[][] = getSqlModel().getSingleResult(ratingSql);
		
		
		totobj = new Object[1][2+ratingData.length];
		
		if (option == 1) {//DESIG/BRANCH/DEPT wise statistics
					
					data = getSqlModel().getSingleResult(FILTER_QUERY);
					if (data!=null && data.length > 0) {		
						finalobj = new Object[data.length][7];
							
							for (int i = 0; i < data.length; i++) {								
								count=0;
				                int index = 1;
								for (int j = 0; j < ratingData.length; j++) {//////////////
									if (option == 1) {
										s1 = FINAL_QUERY + condition + data[i][0]
										 + " AND (APPR_FINAL_SCORE >= " + ratingData[j][1]+ " AND APPR_FINAL_SCORE<="+ratingData[j][2]+")";
										
									}
									Object[][] test = getSqlModel().getSingleResult(s1);
									finalobj[i][index] = test[0][0];
									count=count+Integer.parseInt(String.valueOf(finalobj[i][index]));
									index++;
								} //for j ends
								
								finalobj[i][0] = data[i][1];
								finalobj[i][index] =count;
				                sum+=count;
								
							} //for data ends
				} //if data ends
					
					totobj[0][0]=" Organization wise status : ";
			        int totalrecords=0;
			        int index = 1;
			        
			        for (int j = 0; j < ratingData.length; j++) {
						 s2 = FINAL_QUERY+" AND (APPR_FINAL_SCORE >= " + ratingData[j][1]+ " AND APPR_FINAL_SCORE<="+ratingData[j][2]+")";//hkn
						 Object [][]total=getSqlModel().getSingleResult(s2);
					     totobj[0][index]=total[0][0];
					     totalrecords+=Integer.parseInt(String.valueOf(totobj[0][index]));
					     index++;
					
					}
					totobj[0][index]=totalrecords;
			
		}
		else {//ORGANIZATION WISE STATISTICS
				
				finalobj = new Object[1][2+ratingData.length];
				int orgcount = 0;
				int index = 1;
				
				for (int j = 0; j < ratingData.length; j++) {
	                
					s1 = FINAL_QUERY + " AND (APPR_FINAL_SCORE >= " + ratingData[j][1]+ " AND APPR_FINAL_SCORE<="+ratingData[j][2]+")";
					Object[][] test = getSqlModel().getSingleResult(s1);
					finalobj[0][index] = test[0][0];
					orgcount=orgcount+Integer.parseInt(String.valueOf(finalobj[0][index]));
					index++;
	                
				}
	
				finalobj[0][0] = "Organization";
				finalobj[0][finalobj[0].length-1]=orgcount;
					
		} //else ends		
		
		
		String colnames[] =null;// new int[2+];//{filter, "Exceeds Expectation", "Exceeds In Some", "Meets Expectation","Meets Most","Does Not Meet","Total" };
		int cellwidth[] = null;//{ 40, 17, 17, 17, 17, 17,10};
		int alignment[] = null;//{ 0, 1, 1, 1, 1, 1,1 };
		
		if(ratingData!=null && ratingData.length>0){
			
			colnames = new String[2+ratingData.length];
			cellwidth = new int[2+ratingData.length];
			alignment = new int[2+ratingData.length];
			
			colnames [0] = filter;
			colnames[colnames.length-1] = "Total";
			cellwidth[0] = 40;
			cellwidth[colnames.length-1] = 10; 
			alignment[0] = 0;
			alignment[colnames.length-1] = 1; 
			int index=1;
			
			for(int j=0;j<ratingData.length;j++){
				colnames[index] = String.valueOf(ratingData[j][0]);
				cellwidth[index] = 17;
				alignment[index] = 0;
				index++;
			}
			
		}else{
			colnames = new String[2];
			cellwidth = new int[2];
			alignment = new int[2];
			
			colnames [0] = filter;
			colnames[colnames.length-1] = "Total";
			cellwidth[0] = 40;
			cellwidth[colnames.length-1] = 10;
			alignment[0] = 0;
			alignment[colnames.length-1] = 1;
		}
		
		
		Object[][] appraisaldataObj = new Object[1][1];
		appraisaldataObj[0][0] = filter+"- Wise Appraisal Statistics Report\n ";
		TableDataSet appraisaldata = new TableDataSet();
		appraisaldata.setData(appraisaldataObj);
		appraisaldata.setCellAlignment(new int[] { 0 });
		appraisaldata.setCellWidth(new int[] { 100 });
		appraisaldata.setBorder(false);
		rg.addTableToDoc(appraisaldata);
		
		
		String apprStr[]={"\n\n\nAppraisal Code : ",bean.getApprCode()," \nAppraisal Start Date : ",bean.getApprFrom()," \nAppraisal End Date : ",bean.getApprTo()};
		int style[]={1,5,1,5,1,5};
		
		Object apprData[][] = new Object[4][2];
		apprData[0][0] = " 			";
		apprData[0][1] = " 			";
		apprData[1][0] = "Appraisal Code :";
		apprData[1][1] = ""+bean.getApprCode();
		apprData[2][0] = "Appraisal Start Date :";
		apprData[2][1] = ""+bean.getApprFrom();
		apprData[3][0] = "Appraisal End Date :";
		apprData[3][1] = ""+bean.getApprTo();
		int w[] = {10,30};
		int a[] = {0,0};
		int b[] = {0,0};
		int font[] = {9,9};
		
		
		TableDataSet appdata = new TableDataSet();
		appdata.setData(apprData);
		appdata.setCellAlignment(new int[] { 0,0 });
		appdata.setCellWidth(new int[] { 20,80 });
		appdata.setBorder(false);
		rg.addTableToDoc(appdata);
		
		
		String sysDate="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object dateObj[][] = getSqlModel().getSingleResult(sysDate);
		
		
		if (option == 1){
			//rg.tableBody(totobj, cellwidth, alignment);
		if(finalobj != null && finalobj.length > 0){
		TableDataSet phaseData = new TableDataSet();
		
		TableDataSet finalData = new TableDataSet();
		finalData.setHeader(colnames);
		finalData.setHeaderTable(true);
		finalData.setCellAlignment(alignment);
		finalData.setCellWidth(cellwidth);
		finalData.setHeaderBorderDetail(3);
		finalData.setData(finalobj); // data object from query
		finalData.setBorderDetail(3);
		rg.addTableToDoc(finalData);
		
		int totalRecords=finalobj.length;
		TableDataSet totalEmp = new TableDataSet();
		totalEmp.setData(new Object[][] { { "Total Records : "
				+ totalRecords } });
		totalEmp.setCellAlignment(new int[] { 0 });
		totalEmp.setCellWidth(new int[] { 100 });
		totalEmp.setBorderDetail(0);
		totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
		totalEmp.setBodyFontStyle(1);
		totalEmp.setBlankRowsAbove(1);
		rg.addTableToDoc(totalEmp);
		}else{
			System.out.println("Within If--->No records available");
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "\nNo records available\n\n";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });

			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}
		
		}
		}catch(Exception e){
			
				e.printStackTrace();
		}
		
		rg.process();

		if (reportPath.equals("")) {
			rg.createReport(response);
			} else {
			rg.saveReport(response);
			}
		
	
		
	}

	
	
	
	
}