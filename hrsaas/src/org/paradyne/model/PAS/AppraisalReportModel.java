/**
 * 
 */
package org.paradyne.model.PAS;

import java.awt.Color;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.AppraisalReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.lowagie.text.Font;
import com.lowagie.text.Image;

/**
 * @author AA0554
 *
 */
public class AppraisalReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AppraisalReportModel.class);
	
		/* method name : getReport
		 * purpose     : to show the PDF report
		 * return type : void
		 * parameter   : HttpServletRequest request,HttpServletResponse response, ApprisalComment instance
		 */
	
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, AppraisalReport bean) {
		
		ReportDataSet rds = new ReportDataSet();
		rds.setReportType("Pdf");
		rds.setFileName("Appraisal Report");
		//rds.setPageSize("A3");
		//rds.setPageOrientation("portrait");
		Object heading[][]=new Object[1][3];
		int[] bcellWidthStatic = { 8,50, 45 };
		int[] bcellAlignStatic = { 1,0, 0};
		
		String trainingColumns[] = {"Sr.No","Question Description","Comments"};		//Pending
		String awardsColumns[] = {"Sr.No","Award Name","Reason For Award"};		//Pending	
		String dispColumns[] = {"Sr.No","Disciplinary Action","Comments"};//Pending
		Vector<Object> scoreVector= new Vector<Object>();
		double totalScore=0;
		Object scoreFinalData [][]= null;
		
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
		

		Object[][] nameObj = null;
		String divQuery="SELECT DIV_NAME,DIV_NAME||',\n'||DIV_ADDRESS1||'\n'||DIV_ADDRESS2||'\n'||DIV_ADDRESS3||'\n'||LOCATION_NAME||'-'||DIV_PINCODE FROM HRMS_DIVISION "
			+" left join HRMS_LOCATION on (HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID )"
			+" LEFT JOIN HRMS_EMP_OFFC ON(DIV_ID=EMP_DIV) WHERE EMP_ID="+bean.getEmpCode();
		
		Object [][]divDetails=getSqlModel().getSingleResult(divQuery);
		String logoQuery="select COMPANY_CODE,COMPANY_LOGO from HRMS_COMPANY";
		  Object logoObj[][]=getSqlModel().getSingleResult(logoQuery);
		  try{
		  if(logoObj!=null && logoObj.length>0)
		  {
			  String filename="";
			  if(!String.valueOf(logoObj[0][1]).equals(""))
			  {
				  //String clientUser = (String) session.getAttribute("session_pool");
				  nameObj = new Object[1][2];
				  filename=String.valueOf(logoObj[0][1]);
				  String filePath = context.getRealPath("/")+"pages/Logo/"+session.getAttribute("session_pool")+"/"+filename;
				  nameObj[0][0]=Image.getInstance(filePath);
				 // isLogo = true;
					
			 }else
				  nameObj[0][0]="";
			
		  }else{
			  nameObj[0][0]="";
		  }
	}catch (Exception e) {
		logger.info("Exception --------------------error in getting image "+e);
	}
		try {
			
			//nameObj[0][0] = Image.getInstance("C:\\hrwork\\dataFiles\\logo.jpg");
			//nameObj[0][0] = rg.getImage("C:\\hrwork\\dataFiles\\logo.jpg");
			//nameObj[0][1] = "";
			nameObj[0][1] = ""+divDetails[0][1];
			//nameObj[0][3] = "\n\n\n"+rg.getCurrentDate("dd:MM:yyyy");
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			logger.info("Exception --------------------error in getting image "+e1);
			nameObj[0][0] = "";
			//nameObj[0][1] = "";
			nameObj[0][1] = ""+divDetails[0][1];
			//nameObj[0][3] = "\n\n\n"+rg.getCurrentDate("dd:MM:yyyy");
		}
		
		try{
		TableDataSet tdsName = new TableDataSet();
		tdsName.setData(nameObj);
		tdsName.setCellAlignment(new int[]{0,2});
		tdsName.setCellWidth(new int[]{50,50});
		//tdsName.setBorder(true);
		tdsName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
		//rg.addTableToDoc(tdsName);
		rg.createHeader(tdsName);
		}catch(Exception e){
			logger.info("error in image "+e);
			//e.printStackTrace();
		}
		
		Object [][]reportFirstPage=new Object[1][1];
		reportFirstPage[0][0] ="\n\n\n"+divDetails[0][0];
		TableDataSet reportFirstPageTable = new TableDataSet();
		reportFirstPageTable.setData(reportFirstPage);
		reportFirstPageTable.setCellAlignment(new int[] {1});
		reportFirstPageTable.setCellWidth(new int[] {100 });
		reportFirstPageTable.setBlankRowsAbove(5);
		reportFirstPageTable.setBodyFontDetails(Font.HELVETICA, 22, Font.BOLD, new Color(0,0,0));
		rg.addTableToDoc(reportFirstPageTable);
		
		Object [][]formTitle=new Object[2][1];
		formTitle[0][0] ="\nAnnual Appraisal Form";
		formTitle[1][0] ="\nPeriod "+bean.getApprPeriod();
		TableDataSet reportTitleTable = new TableDataSet();
		reportTitleTable.setData(formTitle);
		reportTitleTable.setCellAlignment(new int[] {1});
		reportTitleTable.setCellWidth(new int[] {100 });
		reportTitleTable.setBlankRowsAbove(2);
		reportTitleTable.setBodyFontDetails(Font.HELVETICA, 18, Font.BOLD, new Color(0,0,0));
		rg.addTableToDoc(reportTitleTable);
		
		Object [][]appraiseeDet=new Object[1][1];
		appraiseeDet[0][0] ="Appraisee Details";
		TableDataSet appraiseeDetTable = new TableDataSet();
		appraiseeDetTable.setData(appraiseeDet);
		appraiseeDetTable.setCellAlignment(new int[] {1});
		appraiseeDetTable.setCellWidth(new int[] {100 });
		appraiseeDetTable.setBlankRowsAbove(2);
		appraiseeDetTable.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
		appraiseeDetTable.setBodyBGColor(new Color(192,192,192));
		rg.addTableToDoc(appraiseeDetTable);
		
		/*
		 * query for appraisal details i.e. employee name & its official details,
		 * 
		 */
		
		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,' '), "
			+ " NVL(HRMS_CENTER.CENTER_NAME,''),TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY '),TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_CAL_CODE,(offc1.EMP_FNAME||' '||offc1.EMP_MNAME|| ' ' || offc1.EMP_LNAME) FROM PAS_APPR_ELIGIBLE_EMP "
			+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID ) "
			+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
			+ " LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
			+ " LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
			+ " LEFT JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID= PAS_APPR_ELIGIBLE_EMP.APPR_ID)"
			+"  LEFT JOIN HRMS_EMP_OFFC offc1 ON (HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = offc1.EMP_ID)"
			+ " WHERE PAS_APPR_CALENDAR.APPR_ID = "+bean.getApprId()+"  AND PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID = "+ bean.getEmpCode()  ;
		

		Object [][] result = getSqlModel().getSingleResult(query);
		Object data[][] = new Object[5][4];
		data[0][0] = "Employee Id :";
		data[0][1] = ""+String.valueOf(result[0][0]);
		data[0][2] = "Employee Name :";
		data[0][3] = String.valueOf(result[0][1]);

		data[1][0] = "Branch :";
		data[1][1] = String.valueOf(result[0][4]);
		data[1][2] = "Department :";
		data[1][3] = String.valueOf(result[0][3]);
		
		data[2][0] = "Designation :";
		data[2][1] = String.valueOf(result[0][2]);
		data[2][2] = "Reporting To :";
		data[2][3] = String.valueOf(result[0][9]);
		
		data[3][0] = "Date of Joining :";
		data[3][1] = String.valueOf(result[0][5]);
		data[3][2] = "Appraisal Code :";
		data[3][3] = String.valueOf(result[0][8]);
		
		data[4][0] = "Appraisal Start Date :";
		data[4][1] = String.valueOf(result[0][6]);
		data[4][2] = "Appraisal End Date :";
		data[4][3] = String.valueOf(result[0][7]);
		
		TableDataSet tdsEmpData = new TableDataSet();
		tdsEmpData.setData(data);
		tdsEmpData.setBorder(true);
		tdsEmpData.setCellAlignment(new int[] {0,0,0,0 });
		tdsEmpData.setCellWidth(new int[] {25, 25, 25, 25 });
		tdsEmpData.setBlankRowsAbove(1);
		rg.addTableToDoc(tdsEmpData);
		
		Object [][]appraiserDet=new Object[1][1];
		appraiserDet[0][0] ="Appraisers Details";
		TableDataSet appraiserDetTable = new TableDataSet();
		appraiserDetTable.setData(appraiserDet);
		appraiserDetTable.setCellAlignment(new int[] {1});
		appraiserDetTable.setCellWidth(new int[] {100 });
		appraiserDetTable.setBlankRowsAbove(2);
		appraiserDetTable.setBlankRowsBelow(1);
		appraiserDetTable.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
		appraiserDetTable.setBodyBGColor(new Color(192,192,192));
		rg.addTableToDoc(appraiserDetTable);
		
		String appraiserTempQuery="SELECT APPR_PHASE_NAME,EMP_FNAME||' '||EMP_MNAME||''||EMP_LNAME,RANK_NAME,APPR_APPRAISER_LEVEL FROM PAS_APPR_APPRAISER "
						+" LEFT JOIN PAS_APPR_APPRAISER_GRP_DTL ON(PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) "
						+" LEFT JOIN HRMS_EMP_OFFC ON(APPR_APPRAISER_CODE=EMP_ID)"
						+" LEFT JOIN HRMS_RANK ON(RANK_ID=EMP_RANK)"
						+" LEFT JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_APPRAISER.APPR_PHASE_ID) "
						+" WHERE APPR_ID ="+bean.getApprId()+" and APPR_APPRAISEE_ID="+bean.getEmpCode()+" and APPR_IS_SELFPHASE!='Y' ORDER BY APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL ";
		Object appraiserTempObj[][] =getSqlModel().getSingleResult(appraiserTempQuery);
		
		Object appraiserObj[][]= new Object[appraiserTempObj.length][5];
		
		for (int i = 0; i < appraiserObj.length; i++) {
			for (int j = 0; j < appraiserObj[0].length; j++) {
				if(j==0){
					appraiserObj[i][j]=""+(i+1);
				}else{
					appraiserObj[i][j]=appraiserTempObj[i][j-1];
				}
			}
		}
		TableDataSet appraisertable = new TableDataSet();
		appraisertable.setData(appraiserObj);
		appraisertable.setCellAlignment(new int[]{1,0,0,0,1});
		appraisertable.setCellWidth(new int[]{10,25,30,20,15});
		appraisertable.setHeader(new String[]{"Sr.No.","Phase","Appraiser Name","Designation","Appraiser Level"});
		appraisertable.setBorder(true);
		appraisertable.setHeaderBGColor(new Color(225,225,225));
		//tdstable.setBlankRowsAbove(0);
		appraisertable.setBlankRowsBelow(1);
		rg.addTableToDoc(appraisertable);
		
		rg.addProperty(rg.PAGE_BREAK);
		
		String phaseQuery ="SELECT APPR_PHASE_ID,APPR_PHASE_NAME,APPR_PHASEWISE_RATING,APPR_PHASE_WEIGHT_AGE,NVL(APPR_IS_SELFPHASE,'N') FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID="+bean.getApprId()+" AND APPR_PHASE_STATUS='A'"
		+" ORDER BY APPR_PHASE_ORDER";

		String applicabilityQuery = " SELECT APPR_TRN_FLAG,APPR_AWARD_FLAG,APPR_DISCIPLINARY_FLAG,APPR_CAREER_FLAG " 
		+" FROM PAS_APPR_TEMPLATE " 
		+" WHERE APPR_ID = "+bean.getApprId()+" AND APPR_TEMPLATE_ID = "+bean.getTemplateCode()+" ";

		Object applicability [][] = getSqlModel().getSingleResult(applicabilityQuery);
		Object phaseObject [][]=getSqlModel().getSingleResult(phaseQuery);
		
		for (int i = 0; i < phaseObject.length; i++) {

			
			String levelQuery =" SELECT APPR_APPRAISER_LEVEL,APPR_APPRAISER_CODE,PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID,EMP_FNAME||' '||EMP_MNAME||''||EMP_LNAME FROM PAS_APPR_APPRAISER " 
								+" LEFT JOIN PAS_APPR_APPRAISER_GRP_DTL ON(PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) "
								+" LEFT JOIN HRMS_EMP_OFFC ON(APPR_APPRAISER_CODE=EMP_ID)"
								+" WHERE APPR_PHASE_ID="+phaseObject[i][0]+" AND APPR_ID ="+bean.getApprId()+" and APPR_APPRAISEE_ID="+bean.getEmpCode()+" ORDER BY APPR_APPRAISER_LEVEL"                                                                                        	;
			
			Object levelObject [][]=getSqlModel().getSingleResult(levelQuery);
			
			
			String sectionQuery = "SELECT DISTINCT PAS_APPR_QUES_MAPPING.APPR_SECTION_ID, APPR_SECTION_NAME,APPR_SECTION_ORDER,PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID FROM PAS_APPR_QUES_MAPPING "
				+" LEFT JOIN PAS_APPR_SECTION_HDR on(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_QUES_MAPPING.APPR_SECTION_ID)"
				+" LEFT JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID=PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID)"
				+" WHERE APPR_PHASE="+phaseObject[i][0]+" AND APPR_EMP_GRP_EMPID = "+bean.getEmpCode()+" AND PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID = "+bean.getTemplateCode()
				+" ORDER BY APPR_SECTION_ORDER " ;
			
			Object sectionObject [][]=getSqlModel().getSingleResult(sectionQuery);
			
						
						 
			if(sectionObject.length !=0){
				Object phaseHeading[][]=new Object[1][3];
				phaseHeading [0][0]="Phase :"+phaseObject[i][1];
				phaseHeading [0][1]="";
				phaseHeading [0][2]="";
				TableDataSet phaseTitle = new TableDataSet();
				phaseTitle.setData(phaseHeading);
				phaseTitle.setCellAlignment(new int[] {0,0,0 });
				phaseTitle.setCellWidth(new int[] { 30,60,10 });
				phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
				phaseTitle.setBlankRowsAbove(2);
				rg.addTableToDoc(phaseTitle);
				
			}
			
			if(levelObject.length !=0){
				for (int j = 0; j < levelObject.length; j++) {
					Object levelHeading[][]=new Object[1][4];
					levelHeading [0][0]="";
					levelHeading [0][1]="Level :"+(j+1);
					levelHeading [0][2]="";
					if(String.valueOf(phaseObject[i][4]).equals("N")){
						levelHeading [0][3]="Appraiser :"+String.valueOf(levelObject[j][3]);
					}else
					levelHeading [0][3]="";
					TableDataSet levelTitle = new TableDataSet();
					levelTitle.setData(levelHeading);
					levelTitle.setCellAlignment(new int[] {0,0,0,0 });
					levelTitle.setCellWidth(new int[] { 5,40,10,45 });
					levelTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
					//levelTitle.setBlankRowsAbove(1);
					rg.addTableToDoc(levelTitle);
					for (int k = 0; k < sectionObject.length; k++) {
						
						String visibilityQuery =" SELECT APPR_SECTION_VISIBILITY,APPR_SECTION_RATING,APPR_SECTION_COMMENT FROM PAS_APPR_SECTION_DTL"
												+" WHERE APPR_ID = "+bean.getApprId()+" AND APPR_SECTION_ID = "+sectionObject[k][0]+" AND APPR_PHASE_ID = "+phaseObject[i][0]+" AND PAS_APPR_SECTION_DTL.APPR_TEMPLATE_ID = "+bean.getTemplateCode();
													
						Object[][] visibility = getSqlModel().getSingleResult(visibilityQuery);
						
						String [] columns = null;
						String startQuery = null;
						int[] bcellWidth1 = null;
						int[] bcellAlign1 = null;
						Object[][] tempObj = null;
						logger.info("rating--------- "+visibility[0][1]);
						logger.info("comment--------- "+visibility[0][2]);
						
						//If visibility of phase is Y(Enabled)
						if(String.valueOf(visibility[0][0]).equals("Y")){
						
							
							/*     Rating  			     Comments
							 * 	  (visibility[0][1])	(visibility[0][2])
							 * 1.   Y 		 					Y
							 * 2.   Y        					N
							 * 3.   N        					Y
							 * 4.   N       					N
							 */ 
						//1.	
						if(String.valueOf(visibility[0][1]).equals("Y") && String.valueOf(visibility[0][2]).equals("Y")){
							 
							 columns = new String[6]; 
							 bcellWidth1 = new int[6]; 
							 bcellAlign1 = new int[6]; 
							 
							 columns[0] ="Sr.No.";
							 columns[1] ="Question Description";
							 columns[2] ="Weightage";
							 columns[3] ="Rating";
							 columns[4] ="Average";
							 columns[5] ="Comments";
							 
							 bcellWidth1[0]= 8;
							 bcellWidth1[1]= 30;
							 bcellWidth1[2]= 12;
							 bcellWidth1[3]= 8;
							 bcellWidth1[4]= 10;
							 bcellWidth1[5]= 32;
							 
							 bcellAlign1[0]= 1;
							 bcellAlign1[1]= 0;
							 bcellAlign1[2]= 1;
							 bcellAlign1[3]= 1;
							 bcellAlign1[4]= 1;
							 bcellAlign1[5]= 0;
							 
								
							 startQuery =" SELECT  ROWNUM,APPR_QUES_DESC,APPR_QUES_WEIGHTAGE,APPR_QUES_RATING,APPR_QUES_PERCENT_WT,APPR_QUES_COMMENTS ";
							 
						}//2.
						else if(String.valueOf(visibility[0][1]).equals("Y") && String.valueOf(visibility[0][2]).equals("N")){

							 columns = new String[5]; 
							 
							 bcellWidth1 = new int[5]; 
							 bcellAlign1 = new int[5]; 
							 
							 columns[0] ="Sr.No.";
							 columns[1] ="Question Description";
							 columns[2] ="Weightage";
							 columns[3] ="Rating";
							 columns[4] ="Average";
							 
							 bcellWidth1[0]= 8;
							 bcellWidth1[1]= 62;
							 bcellWidth1[2]= 10;
							 bcellWidth1[3]= 10;
							 bcellWidth1[4]= 10;
									
							 bcellAlign1[0]= 1;
							 bcellAlign1[1]= 0;
							 bcellAlign1[2]= 1;
							 bcellAlign1[3]= 1;
							 bcellAlign1[4]= 1;
							
							 
							 startQuery =" SELECT  ROWNUM,APPR_QUES_DESC,APPR_QUES_WEIGHTAGE,APPR_QUES_RATING,APPR_QUES_PERCENT_WT ";
							 
						}//3.
						else if(String.valueOf(visibility[0][1]).equals("N") && String.valueOf(visibility[0][2]).equals("Y")){

								
							 columns = new String[3]; 
							 bcellWidth1 = new int[3]; 
							 bcellAlign1 = new int[3];
							 
							 columns[0] ="Sr.No.";
							 columns[1] ="Question Description";
							 columns[2] ="Comment";
							 
							 bcellWidth1[0]= 8;
							 bcellWidth1[1]= 47;
							 bcellWidth1[2]= 45;
											
							 bcellAlign1[0]= 1;
							 bcellAlign1[1]= 0;
							 bcellAlign1[2]= 0;
							 
							 
							 startQuery =" SELECT  ROWNUM,APPR_QUES_DESC,APPR_QUES_COMMENTS ";
							 
						}//4.
						else if(String.valueOf(visibility[0][1]).equals("N") && String.valueOf(visibility[0][2]).equals("N")){

							
							 columns = new String[2]; 
							 bcellWidth1 = new int[2]; 
							 bcellAlign1 = new int[2];
							 
							 columns[0] ="Sr.No.";
							 columns[1] ="Question Description";
							 
							 bcellWidth1[0]= 8;
							 bcellWidth1[1]= 50;
									
							 bcellAlign1[0]= 1;
							 bcellAlign1[1]= 0;
							 
							 
							 startQuery =" SELECT  ROWNUM,APPR_QUES_DESC ";
							 
						}
					}
						String finalQuery = startQuery
							+" FROM PAS_APPR_COMMENTS"
							+" LEFT JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID)"
							+" LEFT JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID)"
							+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_COMMENTS.APPR_QUESTION_CODE)"
							+" INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID AND APPR_EMP_GRP_ID = "+sectionObject[k][3] 
							+" AND PAS_APPR_QUES_MAPPING.APPR_ID="+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getEmpCode()+"AND PAS_APPR_QUES_MAPPING.APPR_SECTION_ID = "+sectionObject[k][0]+" AND APPR_PHASE = "+phaseObject[i][0]+" ) "
							+" WHERE APPR_ID="+bean.getApprId()+" AND APPR_EMP_ID="+bean.getEmpCode()+" AND PAS_APPR_COMMENTS.APPR_SECTION_ID ="+sectionObject[k][0]
							+" AND PAS_APPR_COMMENTS.APPR_PHASE_ID ="+phaseObject[i][0]+" AND APPR_EVALUATOR_LEVEL="+levelObject[j][0]+" ORDER BY APPR_QUESTION_ORDER";
						
						/*
						 * 
						 */
						Object tableData [][]=getSqlModel().getSingleResult(finalQuery);	
						
						try {
							tempObj = new Object[tableData.length][tableData[0].length];
							for (int w = 0; w < tableData.length; w++) {
								tempObj[w][0] = w + 1;
								for (int m = 1; m < tableData[0].length; m++) {
									tempObj[w][m] = tableData[w][m];
								}
							}
							tableData = tempObj;
						} catch (Exception e) {
						}
						
							if(tableData!=null && tableData.length !=0){
								Object sectionHeading[][]=new Object[1][3];
								sectionHeading [0][2]="Section :"+sectionObject[k][1];
								sectionHeading [0][0]="";
								sectionHeading [0][1]="";
									//Subheadings like Section:Training Details
								
								TableDataSet sectionTitle = new TableDataSet();
								sectionTitle.setData(sectionHeading);
								sectionTitle.setCellAlignment(new int[] {0,0,0 });
								sectionTitle.setCellWidth(new int[] { 5,5,90 });
								sectionTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								sectionTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(sectionTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(tableData);
								tdstable.setCellAlignment(bcellAlign1);
								tdstable.setCellWidth(bcellWidth1);
								tdstable.setHeader(columns);
								tdstable.setBorder(true);
								//tdstable.setBlankRowsAbove(0);
								tdstable.setBlankRowsBelow(1);
								tdstable.setHeaderBGColor(new Color(225,225,225));
								rg.addTableToDoc(tdstable);
									
							}
					}
					
						String trnVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
								+" AND APPR_TEMPLATE_ID = "+bean.getTemplateCode()+" "
								+" AND APPR_PHASE ="+phaseObject[i][0]+""
								+" AND APPR_SECTION_TYPE = 'T'" ;
	
						Object trnPhaseVisibility[][] = getSqlModel().getSingleResult(trnVisibility);
	
						String awdVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
								+" AND APPR_TEMPLATE_ID = "+bean.getTemplateCode()+" "
								+" AND APPR_PHASE ="+phaseObject[i][0]+""
								+" AND APPR_SECTION_TYPE = 'A'" ;

						Object awdPhaseVisibility[][] = getSqlModel().getSingleResult(awdVisibility);
		
						String discVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
							+" AND APPR_TEMPLATE_ID = "+bean.getTemplateCode()+" "
							+" AND APPR_PHASE ="+phaseObject[i][0]+""
							+" AND APPR_SECTION_TYPE = 'D'" ;

						Object discPhaseVisibility[][] = getSqlModel().getSingleResult(discVisibility);

						String carVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
							+" AND APPR_TEMPLATE_ID = "+bean.getTemplateCode()+" "
							+" AND APPR_PHASE ="+phaseObject[i][0]+""
							+" AND APPR_SECTION_TYPE = 'C'" ;

						Object carPhaseVisibility[][] = getSqlModel().getSingleResult(carVisibility);

						//4. CAREER DATA
						String careerQuery ="SELECT ROWNUM,APPR_QUES_DESC,APPR_CAREER_COMMENT FROM PAS_APPR_CAREER_COMMENT "
							+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_CAREER_COMMENT.APPR_QUESTION_CODE)"
							+" WHERE PAS_APPR_CAREER_COMMENT.APPR_ID="+bean.getApprId()+" AND PAS_APPR_CAREER_COMMENT.APPR_EMP_ID="+bean.getEmpCode()
							+" AND APPR_PHASE="+phaseObject[i][0];          
						
						//5. General comment  DATA
						String generalQuery =" SELECT X_POSITION,Y_POSITION,APPR_COMMENTS FROM PAS_EMP_GENERAL_COMMENTS "
							+" WHERE APPR_ID ="+bean.getApprId()+" AND APPR_TEMPLATE_ID ="+bean.getTemplateCode()+" and APPR_PHASE = "+phaseObject[i][0] +" and APPR_EMP_ID = "+bean.getEmpCode();      
						
						String headerLabels = " select LABEL_NAME from PAS_GENERAL_COMMENT_DTL where APPR_GENERAL_ID = ? "
									//+" ( SELECT APPR_GENERAL_ID from PAS_GENERAL_COMMENT_HDR where APPR_ID = "+bean.getApprId()+" and APPR_TEMPLATE_ID = "+bean.getTemplateCode()+") "				 
									+" order by X_POSITION,Y_POSITION ";
						
						String query1 = " SELECT APPR_COMMENT_FLAG ,APPR_COLUMN_NOS,APPR_GENERAL_ID FROM PAS_GENERAL_COMMENT_HDR "
								+ " WHERE APPR_ID=" + bean.getApprId()+" and APPR_TEMPLATE_ID = "+bean.getTemplateCode();
						
					if(String.valueOf(phaseObject[i][4]).equals("N")){						// if not self phase
					
					//1. TRAINING DATA
					String trainingQuery ="SELECT ROWNUM,APPR_QUES_DESC,APPR_TRNRECOM_COMMENT FROM PAS_APPR_TRN_RECOMMEND_COMMENT "
						+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_QUESTION_CODE)"
						+" WHERE APPR_ID ="+bean.getApprId()+" AND APPR_EMP_ID="+bean.getEmpCode()+" AND APPR_PHASE_ID="+phaseObject[i][0] +" AND APPR_APPRAISER_ID ="+levelObject[j][1];
					
					
					//2. AWARD DATA
					String awardQuery ="SELECT ROWNUM,APPR_AWARD_DESC,APPR_AWARD_COMMENT FROM PAS_APPR_AWD_ACHIEVED_COMMENT "
						+" LEFT JOIN PAS_APPR_AWARD_ACHIEVED ON(PAS_APPR_AWARD_ACHIEVED.APPR_AWARD_CODE=PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_AWARD_CODE)"
						+" WHERE PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_ID ="+bean.getApprId()+" AND PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_EMP_ID="+bean.getEmpCode()+" AND APPR_PHASE_ID="+phaseObject[i][0] +" AND APPR_APPRAISER_ID ="+levelObject[j][1];
					
					
					//3. DISCIPLINARY DATA
					String displQuery ="SELECT ROWNUM,APPR_DISCP_ACTION, APPR_DISCP_COMMENTS FROM PAS_APPR_DISCIPLINARY_COMMENT "
						+" LEFT JOIN PAS_APPR_DISCIPLINARY ON (PAS_APPR_DISCIPLINARY.APPR_DISCP_ID=PAS_APPR_DISCIPLINARY_COMMENT.APPR_DISCP_ID)"
						+" WHERE PAS_APPR_DISCIPLINARY_COMMENT.APPR_ID="+bean.getApprId()+" AND PAS_APPR_DISCIPLINARY_COMMENT.APPR_EMP_ID="+bean.getEmpCode()
						+" AND APPR_PHASE="+phaseObject[i][0]+" AND APPR_APPRAISER_ID ="+levelObject[j][1];      
					
					/*
					 * Training Details Section
					 * 
					 */
					logger.info("training applicable----------------"+applicability[0][0]);
					if(String.valueOf(applicability[0][0]).equals("Y")){
						
						try {
							
							if(String.valueOf(trnPhaseVisibility[0][0]).equals("Y")){
								
								Object [][] trainingData=getSqlModel().getSingleResult(trainingQuery);
								
								Object trnHeading[][]=new Object[1][3];
								trnHeading [0][2]="Section :Training Details";
								trnHeading [0][0]="";
								trnHeading [0][1]="";
								TableDataSet trainingTitle = new TableDataSet();
								trainingTitle.setData(trnHeading);
								trainingTitle.setCellAlignment(new int[] {0,0,0 });
								trainingTitle.setCellWidth(new int[] { 5,5,90 });
								trainingTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								trainingTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(trainingTitle);
									
										if(trainingData.length !=0){
											logger.info("j======"+j);

											for (int K = 0; K < trainingData.length; K++) {
												trainingData[K][0] =""+(K+1);
												}
											
											TableDataSet tdstable = new TableDataSet();
											tdstable.setData(trainingData);
											tdstable.setCellAlignment(bcellAlignStatic);
											tdstable.setCellWidth(bcellWidthStatic);
											tdstable.setHeader(trainingColumns);
											tdstable.setHeaderBGColor(new Color(225,225,225));
											tdstable.setBorder(true);
											tdstable.setBlankRowsBelow(1);
											rg.addTableToDoc(tdstable);
										}
								}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
						}
					/*
					 * Award Details Section
					 * 
					 */
					logger.info("Award Details applicable----------------"+applicability[0][1]);
					if(String.valueOf(applicability[0][1]).equals("Y")){
						
						try {
							
							if(String.valueOf(awdPhaseVisibility[0][0]).equals("Y")){
								
								Object [][] awardData=getSqlModel().getSingleResult(awardQuery);
								
								Object awardsHeading[][]=new Object[1][3];
								awardsHeading [0][2]="Section :Awards & Recognition";
								awardsHeading [0][0]="";
								awardsHeading [0][1]="";
								TableDataSet awardsTitle = new TableDataSet();
								awardsTitle.setData(awardsHeading);
								awardsTitle.setCellAlignment(new int[] {0,0,0 });
								awardsTitle.setCellWidth(new int[] { 5,5,90 });
								awardsTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								awardsTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(awardsTitle);
									
										if(awardData.length !=0){
											
											for (int K = 0; K < awardData.length; K++) {
												awardData[K][0] =""+(K+1);
												}
											
											TableDataSet tdstable = new TableDataSet();
											tdstable.setData(awardData);
											tdstable.setCellAlignment(bcellAlignStatic);
											tdstable.setCellWidth(bcellWidthStatic);
											tdstable.setHeader(awardsColumns);
											tdstable.setHeaderBGColor(new Color(225,225,225));
											tdstable.setBorder(true);
											//tdstable.setBlankRowsAbove(0);
											tdstable.setBlankRowsBelow(1);
											rg.addTableToDoc(tdstable);
										}	
									
								}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
						}
					/*
					 * Disciplinary Action History Section
					 * 
					 */
					logger.info("Disciplinary Action applicable----------------"+applicability[0][2]);
					if(String.valueOf(applicability[0][2]).equals("Y")){
						
						try {
							
							if(String.valueOf(discPhaseVisibility[0][0]).equals("Y")){
								
								Object [][] displData=getSqlModel().getSingleResult(displQuery);
								
								Object dispHeading[][]=new Object[1][3];
								dispHeading [0][2]="Section :Disciplinary Action History";
								dispHeading [0][0]="";
								dispHeading [0][1]="";
								TableDataSet dispActionTitle = new TableDataSet();
								dispActionTitle.setData(dispHeading);
								dispActionTitle.setCellAlignment(new int[] {0,0,0 });
								dispActionTitle.setCellWidth(new int[] { 5,5,90 });
								dispActionTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								dispActionTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(dispActionTitle);
									
										if(displData.length !=0){
											
											for (int K = 0; K < displData.length; K++) {
												displData[K][0] =""+(K+1);
												}
											
											TableDataSet tdstable = new TableDataSet();
											tdstable.setData(displData);
											tdstable.setCellAlignment(bcellAlignStatic);
											tdstable.setCellWidth(bcellWidthStatic);
											tdstable.setHeader(dispColumns);
											tdstable.setHeaderBGColor(new Color(225,225,225));
											tdstable.setBorder(true);
											//tdstable.setBlankRowsAbove(1);
											tdstable.setBlankRowsBelow(1);
											rg.addTableToDoc(tdstable);
										}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
					}
					/*
					 * Career Progression Details Section
					 * 
					 */
					logger.info("Career Progression applicable----------------"+applicability[0][3]);
					if(String.valueOf(applicability[0][3]).equals("Y")){
						
						try {
							
							if(String.valueOf(carPhaseVisibility[0][0]).equals("Y")){
								
								careerQuery += " AND APPR_APPRAISER_ID = "+levelObject[j][1];
								
								Object [][] careerData=getSqlModel().getSingleResult(careerQuery);
								
								Object careerHeading[][]=new Object[1][3];
								careerHeading [0][2]="Section :Career Progression Details";
								careerHeading [0][0]="";
								careerHeading [0][1]="";
								TableDataSet careerTitle = new TableDataSet();
								careerTitle.setData(careerHeading);
								careerTitle.setCellAlignment(new int[] {0,0,0 });
								careerTitle.setCellWidth(new int[] { 5,5,90 });
								careerTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								careerTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(careerTitle);
									
										if(careerData.length !=0){
											
											for (int K = 0; K < careerData.length; K++) {
												careerData[K][0] =""+(K+1);
												}
											
											TableDataSet tdstable = new TableDataSet();
											tdstable.setData(careerData);
											tdstable.setCellAlignment(bcellAlignStatic);
											tdstable.setCellWidth(bcellWidthStatic);
											tdstable.setHeader(trainingColumns);
											tdstable.setHeaderBGColor(new Color(225,225,225));
											tdstable.setBorder(true);
											//tdstable.setBlankRowsAbove(0);
											tdstable.setBlankRowsBelow(1);
											rg.addTableToDoc(tdstable);
										}
									
										Object [][] dataObj = getSqlModel().getSingleResult(query1);
										
										if(String.valueOf(dataObj[0][0]).equals("Y")){
											
											generalQuery +=" and APPR_APPRAISER_CODE ="+levelObject[j][1] +" order by X_POSITION,Y_POSITION ";
											
											Object [][] generalData=getSqlModel().getSingleResult(generalQuery);
											Object [][] headerObj = getSqlModel().getSingleResult(headerLabels,new Object[]{String.valueOf(dataObj[0][2])});
											
											if(generalData != null && generalData.length > 0){
												
												Object generalTableData[][]=new Object[generalData.length/(Integer.parseInt(""+dataObj[0][1]))][Integer.parseInt(""+dataObj[0][1])];
												String generalTableHeader[]=new String[Integer.parseInt(""+dataObj[0][1])]; 
												
												int p=0;
												int [] cellAlignmetn =new int[Integer.parseInt(""+dataObj[0][1])];
												int [] cellWidth =new int[Integer.parseInt(""+dataObj[0][1])];
												for (int k = 0; k < generalData.length/(Integer.parseInt(""+dataObj[0][1])) ; k++) {
													
													for (int k2 = 0; k2 < Integer.parseInt(""+dataObj[0][1]); k2++) {
														if(k==0)
															generalTableHeader[k2] = ""+headerObj[k2][0];
															                      
														generalTableData[k][k2] = generalData[p++][2];
														cellAlignmetn[k2]=0;
														cellWidth[k2]=20;
													}
													
												}
												
												TableDataSet tdsGeneral = new TableDataSet();
												tdsGeneral.setData(generalTableData);
												tdsGeneral.setHeader(generalTableHeader);
												tdsGeneral.setHeaderBGColor(new Color(225,225,225));
												tdsGeneral.setCellAlignment(cellAlignmetn);
												tdsGeneral.setCellWidth(cellWidth);
												tdsGeneral.setBorder(true);
												rg.addTableToDoc(tdsGeneral);
												
											}
											
										}
							}
								
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else{//for self appraisal
					
					/*
					 * Training Details Section
					 * 
					 */
					logger.info("training applicable----------------"+applicability[0][0]);
					if(String.valueOf(applicability[0][0]).equals("Y")){
						try {
							if(String.valueOf(trnPhaseVisibility[0][0]).equals("Y")){
								
								String trainingQuery = " SELECT ROWNUM,APPR_TRN_DESC,TO_CHAR(APPR_TRN_FROM,'dd-mm-yyyy'),TO_CHAR(APPR_TRN_TO,'dd-mm-yyyy'),APPR_TRN_DURATION,APPR_TRN_SPONCER from PAS_APPR_TRN_ATTENDED "
									+" WHERE APPR_ID ="+bean.getApprId()+" and APPR_TEMPLATE_ID ="+bean.getTemplateCode()+" and APPR_EMP_ID = "+bean.getEmpCode();
				
								Object [][] trnObj = getSqlModel().getSingleResult(trainingQuery);
								
								for (int K = 0; K < trnObj.length; K++) {
									trnObj[K][0] =""+(K+1);
									}
								
								Object trnHeading[][]=new Object[1][3];
								trnHeading [0][2]="Section :Training Details";
								trnHeading [0][0]="";
								trnHeading [0][1]="";
								TableDataSet trainingTitle = new TableDataSet();
								trainingTitle.setData(trnHeading);
								trainingTitle.setCellAlignment(new int[] {0,0,0 });
								trainingTitle.setCellWidth(new int[] { 5,5,90 });
								trainingTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								trainingTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(trainingTitle);
									
										if(trnObj.length !=0){
																						
											TableDataSet tdstable = new TableDataSet();
											tdstable.setData(trnObj);
											tdstable.setCellAlignment(new int[]{1,0,1,1,1,0});
											tdstable.setCellWidth(new int[]{8,30,15,15,15,17});
											tdstable.setHeader(new String[]{"Sr.No.","Training Desc","Training From","Training To","Training Duration","Training Sponcer"});
											tdstable.setHeaderBGColor(new Color(225,225,225));
											tdstable.setBorder(true);
											tdstable.setBlankRowsBelow(1);
											rg.addTableToDoc(tdstable);
										}
								}
					
						}catch(Exception e){
							
						}
					}
					/*
					 * Award Details Section
					 * 
					 */
					logger.info("Award Details applicable----------------"+applicability[0][1]);
					if(String.valueOf(applicability[0][1]).equals("Y")){
						try {
							if(String.valueOf(awdPhaseVisibility[0][0]).equals("Y")){
							
								String awardQuery = " SELECT ROWNUM,APPR_AWARD_DESC,TO_CHAR(APPR_AWARD_DATE,'dd-mm-yyyy'),APPR_ISSUING_AUTHORITY from PAS_APPR_AWARD_ACHIEVED "
									+" WHERE APPR_ID ="+bean.getApprId()+" and APPR_TEMPLATE_ID ="+bean.getTemplateCode()+" and APPR_EMP_ID = "+bean.getEmpCode();
			
								Object [][] awdObj = getSqlModel().getSingleResult(awardQuery);

								for (int K = 0; K < awdObj.length; K++) {
									awdObj[K][0] =""+(K+1);
									}
								
								Object awardsHeading[][]=new Object[1][3];
								awardsHeading [0][2]="Section :Awards & Recognition";
								awardsHeading [0][0]="";
								awardsHeading [0][1]="";
								TableDataSet awardsTitle = new TableDataSet();
								awardsTitle.setData(awardsHeading);
								awardsTitle.setCellAlignment(new int[] {0,0,0 });
								awardsTitle.setCellWidth(new int[] { 5,5,90 });
								awardsTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								awardsTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(awardsTitle);
									
										if(awdObj.length !=0){

											TableDataSet tdstable = new TableDataSet();
											tdstable.setData(awdObj);
											tdstable.setCellWidth(new int []{8,50,15,27});
											tdstable.setCellAlignment(new int []{1,0,1,0});
											tdstable.setHeader(new String[]{"Sr.No.","Award Description","Award Date","Issuing Authority"});
											tdstable.setHeaderBGColor(new Color(225,225,225));
											tdstable.setBorder(true);
											tdstable.setBlankRowsBelow(1);
											rg.addTableToDoc(tdstable);
										}	
								
								
							}
							}catch(Exception e){
								
							}
					}
					/*
					 * Disciplinary Action History Section
					 * 
					 */
					logger.info("Disciplinary Action applicable----------------"+applicability[0][2]);
					if(String.valueOf(applicability[0][2]).equals("Y")){
						try {
							if(String.valueOf(discPhaseVisibility[0][0]).equals("Y")){
						
								String discQuery = " SELECT ROWNUM,APPR_DISCP_ACTION,TO_CHAR(APPR_DISCP_DATE,'dd-mm-yyyy'),APPR_DISCP_AUTHORITY from PAS_APPR_DISCIPLINARY "
									+" WHERE APPR_ID ="+bean.getApprId()+" and APPR_TEMPLATE_ID ="+bean.getTemplateCode()+" and APPR_EMP_ID = "+bean.getEmpCode();


								Object [][] descObj = getSqlModel().getSingleResult(discQuery);
								
								for (int K = 0; K < descObj.length; K++) {
									descObj[K][0] =""+(K+1);
									}
								
								Object dispHeading[][]=new Object[1][3];
								dispHeading [0][2]="Section :Disciplinary Action History";
								dispHeading [0][0]="";
								dispHeading [0][1]="";
								TableDataSet dispActionTitle = new TableDataSet();
								dispActionTitle.setData(dispHeading);
								dispActionTitle.setCellAlignment(new int[] {0,0,0 });
								dispActionTitle.setCellWidth(new int[] { 5,5,90 });
								dispActionTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								dispActionTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(dispActionTitle);
									
										if(descObj.length !=0){
											
											TableDataSet tdstable = new TableDataSet();
											tdstable.setData(descObj);
											tdstable.setCellAlignment(new int []{1,0,1,0});
											tdstable.setCellWidth(new int []{8,50,15,27});
											tdstable.setHeader(new String []{"Sr.No.","Disciplinary Action","Action Taken Date","Issuing Authority"});
											tdstable.setHeaderBGColor(new Color(225,225,225));
											tdstable.setBorder(true);
											tdstable.setBlankRowsBelow(1);
											rg.addTableToDoc(tdstable);
										}
								
							}
						}catch(Exception e){
							
						}
					}
					/*
					 * Career Progression Details Section
					 * 
					 */
					logger.info("Career Progression applicable----------------"+applicability[0][3]);
					if(String.valueOf(applicability[0][3]).equals("Y")){
						try {
							if(String.valueOf(carPhaseVisibility[0][0]).equals("Y")){
								
								careerQuery += " AND APPR_APPRAISER_ID is null ";
								
								Object [][] careerData=getSqlModel().getSingleResult(careerQuery);
								
								Object careerHeading[][]=new Object[1][3];
								careerHeading [0][2]="Section :Career Progression Details";
								careerHeading [0][0]="";
								careerHeading [0][1]="";
								TableDataSet careerTitle = new TableDataSet();
								careerTitle.setData(careerHeading);
								careerTitle.setCellAlignment(new int[] {0,0,0 });
								careerTitle.setCellWidth(new int[] { 5,5,90 });
								careerTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								careerTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(careerTitle);
								
								if(careerData.length !=0){
									
									for (int K = 0; K < careerData.length; K++) {
										careerData[K][0] =""+(K+1);
										}
									
									TableDataSet tdstable = new TableDataSet();
									tdstable.setData(careerData);
									tdstable.setCellAlignment(bcellAlignStatic);
									tdstable.setCellWidth(bcellWidthStatic);
									tdstable.setHeader(trainingColumns);
									tdstable.setHeaderBGColor(new Color(225,225,225));
									tdstable.setBorder(true);
									//tdstable.setBlankRowsAbove(0);
									tdstable.setBlankRowsBelow(1);
									rg.addTableToDoc(tdstable);
								}
							
								Object [][] dataObj = getSqlModel().getSingleResult(query1);
								
								if(String.valueOf(dataObj[0][0]).equals("Y")){
									
									generalQuery +=" and APPR_APPRAISER_CODE ="+bean.getEmpCode()+" order by X_POSITION,Y_POSITION ";
									
									Object [][] generalData=getSqlModel().getSingleResult(generalQuery);
									Object [][] headerObj = getSqlModel().getSingleResult(headerLabels,new Object[]{String.valueOf(dataObj[0][2])});
									
									if(generalData != null && generalData.length > 0){
										
										Object generalTableData[][]=new Object[generalData.length/(Integer.parseInt(""+dataObj[0][1]))][Integer.parseInt(""+dataObj[0][1])];
										String generalTableHeader[]=new String[Integer.parseInt(""+dataObj[0][1])]; 
										
										int p=0;
										int [] cellAlignmetn =new int[Integer.parseInt(""+dataObj[0][1])];
										int [] cellWidth =new int[Integer.parseInt(""+dataObj[0][1])];
										for (int k = 0; k < generalData.length/(Integer.parseInt(""+dataObj[0][1])) ; k++) {
											
											for (int k2 = 0; k2 < Integer.parseInt(""+dataObj[0][1]); k2++) {
												if(k==0)
													generalTableHeader[k2] = ""+headerObj[k2][0];
													                      
												generalTableData[k][k2] = generalData[p++][2];
												cellAlignmetn[k2]=0;
												cellWidth[k2]=20;
											}
											
										}
										
										TableDataSet tdsGeneral = new TableDataSet();
										tdsGeneral.setData(generalTableData);
										tdsGeneral.setHeader(generalTableHeader);
										tdsGeneral.setHeaderBGColor(new Color(225,225,225));
										tdsGeneral.setCellAlignment(cellAlignmetn);
										tdsGeneral.setCellWidth(cellWidth);
										tdsGeneral.setBorder(true);
										rg.addTableToDoc(tdsGeneral);
										
									}
									
								}
							}
						}catch(Exception e)
						{
							
						}
					}
				}
				
				}
			}
			
			/*			
			String scoreQuery ="SELECT PHASE_QUES_TOTAL_RATING,PHASE_QUES_TOTAL_WT,PHASE_WEIGHTAGE,PHASE_FINAL_SCORE FROM PAS_APPR_TRACKING"
				+" WHERE APPR_ID ="+bean.getApprId()+" AND EMP_ID="+bean.getEmpCode()+" and phase_id="+phaseObject[i][0];
			
			Object [][] scoreData =getSqlModel().getSingleResult(scoreQuery);
					
			
			if(scoreData != null && scoreData.length > 0 ){
				if(String.valueOf(phaseObject[i][2]).equals("Y")){
					
					// * phase wise rating yes
					 
				//	for (int m = 0; m < scoreData.length;m++) {
						
					
					int totalWeight=0;
					String finalScore="0";
					Object tempObject [][]= new Object [1][6];
					tempObject [0][0] = scoreVector.size()+1;	// sr no
					
					logger.info("section length------------"+sectionObject.length);
													
					if(String.valueOf(phaseObject[i][3]).equals("0")){
						tempObject [0][1] = phaseObject[i][1];	// phase name
						tempObject [0][2] = "NA";				// phase weightage
						tempObject [0][3] = "NA";				// question weightage
						tempObject [0][4] = "NA";				// question rating
						tempObject [0][5] = "NA" ;	
					}else{
						tempObject [0][1] = phaseObject[i][1];
						for(int n=0;n<sectionObject.length;n++){
							
							String sectionScoreQuery= " SELECT SUM(nvl(APPR_QUES_WEIGHTAGE,0)),SUM(nvl(APPR_QUES_PERCENT_WT,0)) FROM PAS_APPR_COMMENTS " 
												+" WHERE APPR_ID = "+bean.getApprId()+" AND APPR_SECTION_ID = "+sectionObject[n][0]+" AND APPR_EMP_ID = "+bean.getEmpCode()+" AND APPR_PHASE_ID = "+phaseObject[i][0]+" AND "
												+" APPR_EVALUATOR_LEVEL = (SELECT MAX(APPR_EVALUATOR_LEVEL) FROM PAS_APPR_COMMENTS  "
												+"	WHERE APPR_ID ="+bean.getApprId()+" AND APPR_SECTION_ID="+sectionObject[n][0]+" AND APPR_EMP_ID="+bean.getEmpCode()+" AND APPR_PHASE_ID = "+phaseObject[i][0]+")";
							
							Object [][] sectionScoreObject = getSqlModel().getSingleResult(sectionScoreQuery);
							
							if(n!=0){							
								tempObject [0][1] = ""+tempObject [0][1] +" + "+ sectionObject[n][1];
								tempObject [0][2] = phaseObject[i][3];				// phase weightage
								tempObject [0][3] = ""+tempObject [0][3] +" + "+sectionScoreObject [0][0];
								tempObject [0][4] = ""+tempObject [0][4] +" + "+Utility.twoDecimals(""+sectionScoreObject [0][1]);
								
								
								totalScore = totalScore + Double.parseDouble(Utility.twoDecimals(""+sectionScoreObject [0][1]));
								totalWeight = totalWeight + Integer.parseInt(""+sectionScoreObject [0][0]);
							}
							else{
								tempObject [0][1] = ""+tempObject [0][1] +" ( "+ sectionObject[n][1];
								tempObject [0][2] = phaseObject[i][3];				// phase weightage
								tempObject [0][3] =  " ( "+sectionScoreObject [0][0];
								tempObject [0][4] =  " ( "+Utility.twoDecimals(""+sectionScoreObject [0][1]);
								
								totalScore = Double.parseDouble(Utility.twoDecimals(""+sectionScoreObject [0][1]));
								totalWeight = Integer.parseInt(""+sectionScoreObject [0][0]);
							}
							
							logger.info("phase+section-------------"+tempObject [0][1]);
							
												
						}
						int score =Integer.parseInt(""+Math.round(totalScore));
						finalScore=getPercent(""+phaseObject[i][3],score,totalWeight);
						logger.info("finalScore-------------"+finalScore);
						tempObject [0][1] = ""+tempObject [0][1]+" )";						
						tempObject [0][3] = ""+tempObject [0][3]+" )";	
						tempObject [0][4] = ""+tempObject [0][4]+" )";	
						//tempObject [0][3] = scoreData[m][1];				// question weightage
						//tempObject [0][4] = scoreData[m][0];				// question rating
						tempObject [0][5] = finalScore ;				// final score
					}
					scoreVector.add(tempObject);
					
					//}
				}else{
					for (int m = 0; m < scoreData.length; m++) {
						Object tempObject [][]= new Object [1][5];
						tempObject [0][0] = scoreVector.size()+1;							// sr no
						tempObject [0][1] = phaseObject[i][1];			// phase name
						tempObject [0][2] = scoreData[m][1];				// question weightage
						tempObject [0][3] = scoreData[m][0];				// question rating
						tempObject [0][4] = scoreData[m][3] ;							// final score
						scoreVector.add(tempObject);
					}
					
				}
				
				
			}*/
		
			rg.addProperty(rg.PAGE_BREAK);
		}
		
		Object [][]scorePageTitle=new Object[1][1];
		scorePageTitle[0][0] ="\nEMPLOYEE APPRAISAL SCORE SHEET ";
		TableDataSet tdsScoreTitle = new TableDataSet();
		tdsScoreTitle.setData(scorePageTitle);
		tdsScoreTitle.setCellAlignment(new int[] {1});
		tdsScoreTitle.setCellWidth(new int[] {100 });
		tdsScoreTitle.setBodyFontDetails(Font.HELVETICA, 15, Font.BOLD, new Color(0,0,0));
		tdsScoreTitle.setBlankRowsAbove(1);
		rg.addTableToDoc(tdsScoreTitle);
		
		rg.addTableToDoc(tdsEmpData);
		
		String finalScoreQuery ="SELECT APPR_FINAL_SCORE, APPR_FINAL_SCORE_VALUE,APPR_FINAL_SCORE_DESC,APPR_ADJUST_FACTOR,APPR_SCORE_ADJUST,APPR_SCORE_FINALIZE FROM PAS_APPR_SCORE "
			+" WHERE APPR_ID ="+bean.getApprId()+ " AND EMP_ID ="+bean.getEmpCode();
			
		Object finalScoreObject [][]= getSqlModel().getSingleResult(finalScoreQuery);
		
		if(finalScoreObject !=null && finalScoreObject.length > 0 ){
		String scoreSheetQuery="";
		
		Object [][]scoreHeading=new Object[1][1];
		scoreHeading [0][0]="Score Sheet :";
		TableDataSet tdsScoreHeading = new TableDataSet();
		tdsScoreHeading.setData(scoreHeading);
		tdsScoreHeading.setCellAlignment(new int[] {0});
		tdsScoreHeading.setCellWidth(new int[] {100 });
		tdsScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
		tdsScoreHeading.setBlankRowsAbove(1);
		rg.addTableToDoc(tdsScoreHeading);
		
		String scoreQuery=" SELECT ROWNUM,APPR_PHASE_NAME,APPR_PHASE_WEIGHT_AGE,PHASE_FINAL_SCORE FROM  PAS_APPR_TRACKING "
			 +" LEFT JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_TRACKING.PHASE_ID)"
			 +" WHERE PAS_APPR_TRACKING.APPR_ID="+bean.getApprId()+" AND EMP_ID="+bean.getEmpCode()+" AND TEMPLATE_CODE="+bean.getTemplateCode()+" ORDER BY APPR_PHASE_ORDER ";

		Object [][]scoreObj=getSqlModel().getSingleResult(scoreQuery);
			for (int j = 0; j < scoreObj.length; j++) {
			scoreObj[j][0] =""+(j+1);
			}
			
			Object totalScoreObj[][]= new Object[1][4];
			
			Object adjusrObject[][] = new Object[1][4];
			
			
			adjusrObject [0][1] = "Adjustment Factor";
			adjusrObject [0][0] ="";
			adjusrObject [0][2] ="";
			if(String.valueOf(finalScoreObject[0][4]).equals("null") || String.valueOf(finalScoreObject[0][4])== null || String.valueOf(finalScoreObject[0][4]).equals(" ") )
				adjusrObject [0][3]="";
			else
				adjusrObject [0][3] =checkNull(String.valueOf(finalScoreObject[0][3]))+" "+checkNull(String.valueOf(finalScoreObject[0][4]));
			
			
			totalScoreObj [0][1] = "Total Score";
			totalScoreObj [0][0] ="";
			totalScoreObj [0][2] ="";
			totalScoreObj [0][3] =checkNull(String.valueOf(finalScoreObject[0][0]));
			String scoreColumns[] = {"Sr.No.","Phase Name","Weightage","Score"};
			
			int[] bcellWidthScore = { 10,50,20,20  };
			int[] bcellAlignScore = { 1,0,1,1};
		
			scoreObj=Utility.joinArrays(scoreObj, adjusrObject, 1, 0);
			scoreObj=Utility.joinArrays(scoreObj, totalScoreObj, 1, 0);
		TableDataSet tdsScoreTable = new TableDataSet();
		tdsScoreTable.setData(scoreObj);
		tdsScoreTable.setCellAlignment(bcellAlignScore);
		tdsScoreTable.setCellWidth(bcellWidthScore);
		tdsScoreTable.setHeader(scoreColumns);
		tdsScoreTable.setBorder(true);
		tdsScoreTable.setHeaderBGColor(new Color(225,225,225));
		//tdsScoreTable.setBlankRowsAbove(1);
		rg.addTableToDoc(tdsScoreTable);
		
		String ratingAchieved = checkNull(String.valueOf(finalScoreObject[0][0]))+" "+checkNull(String.valueOf(finalScoreObject[0][1]));
		System.out.println(ratingAchieved);
		if(!(checkNull(String.valueOf(finalScoreObject[0][1])).equals(""))){
			ratingAchieved += " ("+checkNull(String.valueOf(finalScoreObject[0][2]))+")";
		}
		if(String.valueOf(finalScoreObject[0][5]).equals("N")){
			Object [][]finalizeNote=new Object[1][1];
			finalizeNote [0][0]="Note - Score has not been finalized.  ";
			TableDataSet finalizeTable = new TableDataSet();
			finalizeTable.setData(finalizeNote);
			finalizeTable.setCellAlignment(new int[] {2});
			finalizeTable.setCellWidth(new int[] {100 });
			finalizeTable.setBodyFontDetails(Font.HELVETICA, 8, Font.NORMAL, new Color(0,0,0));
			rg.addTableToDoc(finalizeTable);
		}	
		System.out.println(ratingAchieved);
		Object [][]ratingHeading=new Object[1][1];
		ratingHeading [0][0]="Rating  Achieved :  ";
		TableDataSet ratingTable = new TableDataSet();
		ratingTable.setData(ratingHeading);
		ratingTable.setCellAlignment(new int[] {0});
		ratingTable.setCellWidth(new int[] {100 });
		ratingTable.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
		ratingTable.setBlankRowsAbove(1);
		rg.addTableToDoc(ratingTable);
		
		Object [][]ratingData=new Object[1][2];
		ratingData [0][1]=ratingAchieved;
		ratingData [0][0]="";
		TableDataSet ratingDataTable = new TableDataSet();
		ratingDataTable.setData(ratingData);
		ratingDataTable.setCellAlignment(new int[] {0,0});
		ratingDataTable.setCellWidth(new int[] {10,90 });
		ratingDataTable.setBlankRowsAbove(1);
		rg.addTableToDoc(ratingDataTable);
		
		}
			/*
		
			if(String.valueOf(phaseObject[0][2]).equals("Y")){
				scoreFinalData = new Object [scoreVector.size()][6];
				for (int m = 0; m <scoreFinalData.length ; m++) {
					scoreFinalData [m][0] = String.valueOf(((Object[][])scoreVector.get(m))[0][0]); 
					scoreFinalData [m][1] = String.valueOf(((Object[][])scoreVector.get(m))[0][1]); 
					scoreFinalData [m][2] = String.valueOf(((Object[][])scoreVector.get(m))[0][2]); 
					scoreFinalData [m][3] = String.valueOf(((Object[][])scoreVector.get(m))[0][3]); 
					scoreFinalData [m][4] = String.valueOf(((Object[][])scoreVector.get(m))[0][4]); 
					scoreFinalData [m][5] = String.valueOf(((Object[][])scoreVector.get(m))[0][5]); 
					
				}
				Object totalScoreObj[][]= new Object[1][6];
				
				Object adjusrObject[][] = new Object[1][6];
				
				
				adjusrObject [0][1] = "Adjustment Factor";
				adjusrObject [0][0] ="";
				adjusrObject [0][2] ="";
				adjusrObject [0][3] ="";
				adjusrObject [0][4] ="";
				if(String.valueOf(finalScoreObject[0][4]).equals("null") || String.valueOf(finalScoreObject[0][4])== null || String.valueOf(finalScoreObject[0][4]).equals(" ") )
					adjusrObject [0][5]="";
				else
					adjusrObject [0][5] =checkNull(String.valueOf(finalScoreObject[0][3]))+" "+checkNull(String.valueOf(finalScoreObject[0][4]));
				
				
				totalScoreObj [0][1] = "Total Score";
				totalScoreObj [0][0] ="";
				totalScoreObj [0][2] ="";
				totalScoreObj [0][3] ="";
				totalScoreObj [0][4] ="";
				totalScoreObj [0][5] =checkNull(String.valueOf(finalScoreObject[0][0]));
				String scoreColumns[] = {"Sr.No.","Phase Name","Phase Weightage","Question Weightage","Question Rating","Score"};
				
				int[] bcellWidthScore = { 8, 35,15,15,15,15 };
				int[] bcellAlignScore = { 1, 0,1, 1, 1, 1};
				
				scoreFinalData = Utility.joinArrays(scoreFinalData, adjusrObject, 1, 0);
				scoreFinalData = Utility.joinArrays(scoreFinalData, totalScoreObj, 1, 0);
				TableDataSet scoreTable = new TableDataSet();
				scoreTable.setData(scoreFinalData);
				scoreTable.setCellAlignment(bcellAlignScore);
				scoreTable.setCellWidth(bcellWidthScore);
				scoreTable.setHeader(scoreColumns);
				scoreTable.setHeaderBGColor(new Color(225,225,225));
				scoreTable.setBorder(true);
				scoreTable.setBlankRowsAbove(0);
				rg.addTableToDoc(scoreTable);
				
				
			}else{

				scoreFinalData = new Object [scoreVector.size()][5];
				
				for (int m = 0; m <scoreFinalData.length ; m++) {
					scoreFinalData [m][0] = String.valueOf(((Object[][])scoreVector.get(m))[0][0]); 
					scoreFinalData [m][1] = String.valueOf(((Object[][])scoreVector.get(m))[0][1]); 
					scoreFinalData [m][2] = String.valueOf(((Object[][])scoreVector.get(m))[0][2]); 
					scoreFinalData [m][3] = String.valueOf(((Object[][])scoreVector.get(m))[0][3]); 
					scoreFinalData [m][4] = String.valueOf(((Object[][])scoreVector.get(m))[0][4]); 
				}
				
				totalScore = totalScore / Integer.parseInt(""+scoreFinalData.length);
				
				String scoreColumns[] = {"Sr.No.","Phase Name","Question Weightage","Question Rating","Score"};
				
				Object totalScoreObj[][]= new Object[1][5];
				
				Object adjusrObject[][] = new Object[1][5];
				
				logger.info("Adjustment Factor-------------"+finalScoreObject[0][4]);
				adjusrObject [0][1] = "Adjustment Factor";
				adjusrObject [0][0] ="";
				adjusrObject [0][2] ="";
				adjusrObject [0][3] ="";
				if(String.valueOf(finalScoreObject[0][4]).equals("null") || String.valueOf(finalScoreObject[0][4])== null || String.valueOf(finalScoreObject[0][4]).equals(" ") )
					adjusrObject [0][4]="";
				else
					adjusrObject [0][4] =checkNull(String.valueOf(finalScoreObject[0][3]))+" "+checkNull(String.valueOf(finalScoreObject[0][4]));
				
				totalScoreObj [0][1] = "Total Score";
				totalScoreObj [0][0] ="";
				totalScoreObj [0][2] ="";
				totalScoreObj [0][3] ="";
				totalScoreObj [0][4] =checkNull(String.valueOf(finalScoreObject[0][0]));
				
				int[] bcellWidthScore = { 15, 40,15,15,15 };
				int[] bcellAlignScore = { 1, 0,1, 1, 1};
				
				scoreFinalData = Utility.joinArrays(scoreFinalData, adjusrObject, 1, 0);
				scoreFinalData = Utility.joinArrays(scoreFinalData, totalScoreObj, 1, 0);
				
				TableDataSet scoreTable = new TableDataSet();
				scoreTable.setData(scoreFinalData);
				scoreTable.setCellAlignment(bcellAlignScore);
				scoreTable.setCellWidth(bcellWidthScore);
				scoreTable.setHeader(scoreColumns);
				scoreTable.setHeaderBGColor(new Color(225,225,225));
				scoreTable.setBorder(true);
				scoreTable.setBlankRowsAbove(0);
				rg.addTableToDoc(scoreTable);
				
									
			}
			String finalizeQuery = " SELECT NVL(APPR_SCORE_FINALIZE,'N') FROM PAS_APPR_SCORE WHERE APPR_ID = "+bean.getApprId()+" AND EMP_ID = "+bean.getEmpCode();
			
			Object [][] finalizeObj = getSqlModel().getSingleResult(finalizeQuery);
			
			if(finalizeObj != null && finalizeObj.length > 0 ){
				if(String.valueOf(finalizeObj[0][0]).equals("N")){
					Object [][]finalizeNote=new Object[1][1];
					finalizeNote [0][0]="Note - Score has not been finalized.  ";
					TableDataSet finalizeTable = new TableDataSet();
					finalizeTable.setData(finalizeNote);
					finalizeTable.setCellAlignment(new int[] {2});
					finalizeTable.setCellWidth(new int[] {100 });
					finalizeTable.setBodyFontDetails(Font.HELVETICA, 8, Font.NORMAL, new Color(0,0,0));
					//finalizeTable.setBlankRowsAbove(1);
					rg.addTableToDoc(finalizeTable);
				}
			}
			*/
			
		
		Object [][]hrTableData=new Object[1][2];
		
		hrTableData [0][0]="HR comments  :";
		hrTableData [0][1]="";
		TableDataSet hrTable = new TableDataSet();
		hrTable.setData(hrTableData);
		hrTable.setCellAlignment(new int[] {0,0});
		hrTable.setCellWidth(new int[] {40,60 });
		hrTable.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
		hrTable.setBlankRowsAbove(1);
		hrTable.setBlankRowsBelow(8);
		rg.addTableToDoc(hrTable);
		
		
		Object [][]mgmtTableData=new Object[8][2];
		mgmtTableData [0][0]="Management comments  :";
		mgmtTableData [0][1]="";
		TableDataSet mgmtTable = new TableDataSet();
		mgmtTable.setData(mgmtTableData);
		mgmtTable.setCellAlignment(new int[] {0,0});
		mgmtTable.setCellWidth(new int[] {40,60 });
		mgmtTable.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
		mgmtTable.setBlankRowsBelow(8);
		rg.addTableToDoc(mgmtTable);
		
		
		Object [][]authSignData=new Object[2][4];
		authSignData [0][0]="_______________";
		authSignData [0][1]="_______________";
		authSignData [0][2]="_______________";
		authSignData [0][3]="_______________";
		
		authSignData [1][0]="Appraisee Signature";
		authSignData [1][1]="Reporting Manager Signature";
		authSignData [1][2]="HR Manager Signature";
		authSignData [1][3]="Management Signature";
		
		TableDataSet authSigntTable = new TableDataSet();
		authSigntTable.setData(authSignData);
		authSigntTable.setCellAlignment(new int[] {1,1,1,1});
		authSigntTable.setCellWidth(new int[] {25,25,25,25});
		authSigntTable.setBodyFontDetails(Font.NORMAL, 10, Font.NORMAL, new Color(0,0,0));
		authSigntTable.setBlankRowsAbove(1);
		rg.addTableToDoc(authSigntTable);
		
		rg.process();
		rg.createReport(response);

	}
		public void getReportOld(HttpServletRequest request,
				HttpServletResponse response, AppraisalReport bean) {
			String s = "\nEmployee wise  Appraisal Report";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",	"Performance  Appraisal Report");
			Object [][]heading=new Object[1][1];
			int []cells={25};
			int []align={0};
			double totalScore=0;
			String dateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
			Vector<Object> scoreVector= new Vector<Object>();
			Object scoreFinalData [][]= null;
			rg.addFormatedText(s, 6, 0, 1, 0);
			rg.addText("Date: "+dateData[0][0], 0, 2, 0);
			//rg.setFName("Performance  Appraisal Report");
			rg.addFormatedText("\n", 6, 0, 0, 0);
			/*
			 * query for appraisal details i.e. employee name & its official details,
			 * 
			 */
			
			String query = " SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,' '), "
				+ " NVL(HRMS_CENTER.CENTER_NAME,''),TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY '),TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_CAL_CODE FROM PAS_APPR_ELIGIBLE_EMP "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID ) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
				+ " LEFT JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID= PAS_APPR_ELIGIBLE_EMP.APPR_ID)"
				+ " WHERE APPR_ID = "+bean.getApprId()+"  AND PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID = "+ bean.getEmpCode()  ;
			

			Object [][] result = getSqlModel().getSingleResult(query);
			
			 
			Object data[][] = new Object[4][6];

			try{
				logger.info("result.length===="+result.length);
				
			
			data[0][0] = "Employee Name ";
			data[0][1] = ":";
			data[0][2] = ""+String.valueOf(result[0][1]);
			data[0][3] = "Appraisal Code ";
			data[0][4] = ":";
			data[0][5] = String.valueOf(result[0][8]);

			data[1][0] = "Department ";
			data[1][1] = ":";
			data[1][2] = String.valueOf(result[0][3]);
			data[1][3] = "Designation ";
			data[1][4] = ":";
			data[1][5] = String.valueOf(result[0][2]);

			data[2][0] = "Branch ";
			data[2][1] = ":";
			data[2][2] = String.valueOf(result[0][4]);
			data[2][3] = "Date of Joining ";
			data[2][4] = ":";
			data[2][5] = String.valueOf(result[0][5]);
			
			data[3][0] = "Appraisal Start Date ";
			data[3][1] = ":";
			data[3][2] = String.valueOf(result[0][6]);
			data[3][3] = "Appraisal End Date ";
			data[3][4] = ":";
			data[3][5] = String.valueOf(result[0][7]);

			int[] bcellWidth = { 20, 10, 20, 20, 10, 20 };
			int[] bcellAlign = { 0, 0, 0, 0, 0, 0 };
			
			rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
			
						
			String trainingColumns[] = {"Question Description","Comments"};
			String awardsColumns[] = {"Award Name","Reason For Award"};
			String dispColumns[] = {"Disciplinary Action","Comments"};
			
			int[] bcellWidthStatic = { 50, 50 };
			int[] bcellAlignStatic = { 0, 0};
			
			String finalizeQuery = " SELECT NVL(APPR_SCORE_FINALIZE,'N') FROM PAS_APPR_SCORE WHERE APPR_ID = "+bean.getApprId()+" AND EMP_ID = "+bean.getEmpCode();
			
			Object [][] finalizeObj = getSqlModel().getSingleResult(finalizeQuery);
			
			if(finalizeObj != null && finalizeObj.length > 0 ){
				if(String.valueOf(finalizeObj[0][0]).equals("N")){
					rg.addFormatedText("\n", 6, 0, 0, 0);
					rg.addText("Note - Score has not been finalized.", 6, 0, 0);
					rg.addFormatedText("\n", 6, 0, 0, 0);
				}
			}
			
			String phaseQuery ="SELECT APPR_PHASE_ID,APPR_PHASE_NAME,APPR_PHASEWISE_RATING,APPR_PHASE_WEIGHT_AGE FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID="+bean.getApprId()+" AND APPR_PHASE_STATUS='A'"
								+" ORDER BY APPR_PHASE_ORDER";
			
			String applicabilityQuery = " SELECT APPR_TRN_FLAG,APPR_AWARD_FLAG,APPR_DISCIPLINARY_FLAG,APPR_CAREER_FLAG " 
        						+" FROM PAS_APPR_TEMPLATE " 
        						+" WHERE APPR_ID = "+bean.getApprId()+" AND APPR_TEMPLATE_ID = "+bean.getTemplateCode()+" ";

			Object [][] applicability = getSqlModel().getSingleResult(applicabilityQuery);

			Object phaseObject [][]=getSqlModel().getSingleResult(phaseQuery);
			for (int i = 0; i < phaseObject.length; i++) {
				
								
				String levelQuery =" SELECT APPR_APPRAISER_LEVEL,APPR_APPRAISER_CODE,PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID FROM PAS_APPR_APPRAISER " 
									+" LEFT JOIN PAS_APPR_APPRAISER_GRP_DTL ON(PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) "	
									+" WHERE APPR_PHASE_ID="+phaseObject[i][0]+" AND APPR_ID ="+bean.getApprId()+" and APPR_APPRAISEE_ID="+bean.getEmpCode()+" ORDER BY APPR_APPRAISER_LEVEL"                                                                                        	;
				
				Object levelObject [][]=getSqlModel().getSingleResult(levelQuery);
				
				
				
				String sectionQuery = "SELECT DISTINCT PAS_APPR_QUES_MAPPING.APPR_SECTION_ID, APPR_SECTION_NAME,APPR_SECTION_ORDER,PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID FROM PAS_APPR_QUES_MAPPING "
					+" LEFT JOIN PAS_APPR_SECTION_HDR on(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_QUES_MAPPING.APPR_SECTION_ID)"
					+" LEFT JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID=PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID)"
					+" WHERE APPR_PHASE="+phaseObject[i][0]+" AND APPR_EMP_GRP_EMPID = "+bean.getEmpCode()+" AND PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID = "+bean.getTemplateCode()
					+" ORDER BY APPR_SECTION_ORDER " ;
				
				Object sectionObject [][]=getSqlModel().getSingleResult(sectionQuery);
				
							
							 
				if(sectionObject.length !=0){
					heading [0][0]="Phase :"+phaseObject[i][1];
					rg.tableBodyBold(heading, cells, align) ;
					
				}
				
				if(levelObject.length !=0){
					for (int j = 0; j < levelObject.length; j++) {
						heading [0][0]="         Level :"+(j+1);
						rg.tableBodyNoBorder(heading, cells, align) ;
						for (int k = 0; k < sectionObject.length; k++) {
							
							String visibilityQuery =" SELECT APPR_SECTION_VISIBILITY,APPR_SECTION_RATING,APPR_SECTION_COMMENT FROM PAS_APPR_SECTION_DTL"
													+" WHERE APPR_ID = "+bean.getApprId()+" AND APPR_SECTION_ID = "+sectionObject[k][0]+" AND APPR_PHASE_ID = "+phaseObject[i][0]+" AND PAS_APPR_SECTION_DTL.APPR_TEMPLATE_ID = "+bean.getTemplateCode();
														
							Object[][] visibility = getSqlModel().getSingleResult(visibilityQuery);
							
							String [] columns = null;
							String startQuery = null;
							int[] bcellWidth1 = null;
							int[] bcellAlign1 = null;
							Object[][] tempObj = null;
							logger.info("rating--------- "+visibility[0][1]);
							logger.info("comment--------- "+visibility[0][2]);
							
							//If visibility of phase is Y(Enabled)
							if(String.valueOf(visibility[0][0]).equals("Y")){
							
								
								/*     Rating  			     Comments
								 * 	  (visibility[0][1])	(visibility[0][2])
								 * 1.   Y 		 					Y
								 * 2.   Y        					N
								 * 3.   N        					Y
								 * 4.   N       					N
								 */ 
							//1.	
							if(String.valueOf(visibility[0][1]).equals("Y") && String.valueOf(visibility[0][2]).equals("Y")){
								 
								 columns = new String[6]; 
								 bcellWidth1 = new int[6]; 
								 bcellAlign1 = new int[6]; 
								 
								 columns[0] ="Sr.No.";
								 columns[1] ="Question Description";
								 columns[2] ="Weightage";
								 columns[3] ="Rating";
								 columns[4] ="Average";
								 columns[5] ="Comments";
								 
								 bcellWidth1[0]= 8;
								 bcellWidth1[1]= 30;
								 bcellWidth1[2]= 12;
								 bcellWidth1[3]= 8;
								 bcellWidth1[4]= 10;
								 bcellWidth1[5]= 32;
								 
								 bcellAlign1[0]= 1;
								 bcellAlign1[1]= 0;
								 bcellAlign1[2]= 1;
								 bcellAlign1[3]= 1;
								 bcellAlign1[4]= 1;
								 bcellAlign1[5]= 0;
								 
									
								 startQuery =" SELECT  ROWNUM,APPR_QUES_DESC,APPR_QUES_WEIGHTAGE,APPR_QUES_RATING,APPR_QUES_PERCENT_WT,APPR_QUES_COMMENTS ";
								 
							}//2.
							else if(String.valueOf(visibility[0][1]).equals("Y") && String.valueOf(visibility[0][2]).equals("N")){

								 columns = new String[5]; 
								 
								 bcellWidth1 = new int[5]; 
								 bcellAlign1 = new int[5]; 
								 
								 columns[0] ="Sr.No.";
								 columns[1] ="Question Description";
								 columns[2] ="Weightage";
								 columns[3] ="Rating";
								 columns[4] ="Average";
								 
								 bcellWidth1[0]= 8;
								 bcellWidth1[1]= 62;
								 bcellWidth1[2]= 10;
								 bcellWidth1[3]= 10;
								 bcellWidth1[4]= 10;
										
								 bcellAlign1[0]= 1;
								 bcellAlign1[1]= 0;
								 bcellAlign1[2]= 1;
								 bcellAlign1[3]= 1;
								 bcellAlign1[4]= 1;
								
								 
								 startQuery =" SELECT  ROWNUM,APPR_QUES_DESC,APPR_QUES_WEIGHTAGE,APPR_QUES_RATING,APPR_QUES_PERCENT_WT ";
								 
							}//3.
							else if(String.valueOf(visibility[0][1]).equals("N") && String.valueOf(visibility[0][2]).equals("Y")){

									
								 columns = new String[3]; 
								 bcellWidth1 = new int[3]; 
								 bcellAlign1 = new int[3];
								 
								 columns[0] ="Sr.No.";
								 columns[1] ="Question Description";
								 columns[2] ="Comment";
								 
								 bcellWidth1[0]= 8;
								 bcellWidth1[1]= 47;
								 bcellWidth1[2]= 45;
												
								 bcellAlign1[0]= 1;
								 bcellAlign1[1]= 0;
								 bcellAlign1[2]= 0;
								 
								 
								 startQuery =" SELECT  ROWNUM,APPR_QUES_DESC,APPR_QUES_COMMENTS ";
								 
							}//4.
							else if(String.valueOf(visibility[0][1]).equals("N") && String.valueOf(visibility[0][2]).equals("N")){

								
								 columns = new String[2]; 
								 bcellWidth1 = new int[2]; 
								 bcellAlign1 = new int[2];
								 
								 columns[0] ="Sr.No.";
								 columns[1] ="Question Description";
								 
								 bcellWidth1[0]= 8;
								 bcellWidth1[1]= 50;
										
								 bcellAlign1[0]= 1;
								 bcellAlign1[1]= 0;
								 
								 
								 startQuery =" SELECT  ROWNUM,APPR_QUES_DESC ";
								 
							}
						}
							String finalQuery = startQuery
								+" FROM PAS_APPR_COMMENTS"
								+" LEFT JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID)"
								+" LEFT JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID)"
								+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_COMMENTS.APPR_QUESTION_CODE)"
								+" INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID AND APPR_EMP_GRP_ID = "+sectionObject[k][3] 
								+" AND PAS_APPR_QUES_MAPPING.APPR_ID="+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getEmpCode()+"AND PAS_APPR_QUES_MAPPING.APPR_SECTION_ID = "+sectionObject[k][0]+" AND APPR_PHASE = "+phaseObject[i][0]+" ) "
								+" WHERE APPR_ID="+bean.getApprId()+" AND APPR_EMP_ID="+bean.getEmpCode()+" AND PAS_APPR_COMMENTS.APPR_SECTION_ID ="+sectionObject[k][0]
								+" AND PAS_APPR_COMMENTS.APPR_PHASE_ID ="+phaseObject[i][0]+" AND APPR_EVALUATOR_LEVEL="+levelObject[j][0]+" ORDER BY APPR_QUESTION_ORDER";
							
							/*
							 * 
							 */
							Object tableData [][]=getSqlModel().getSingleResult(finalQuery);	
							
							try {
								tempObj = new Object[tableData.length][tableData[0].length + 1];
								for (int w = 0; w < tableData.length; w++) {
									tempObj[w][0] = w + 1;
									for (int m = 1; m < tableData[0].length; m++) {
										tempObj[w][m] = tableData[w][m];
									}
								}
								tableData = tempObj;
							} catch (Exception e) {
							}
							
								if(tableData!=null && tableData.length !=0){
									heading [0][0]="              Section :"+sectionObject[k][1];
										//Subheadings like Section:Training Details
									
																	
										rg.tableBodyNoBorder(heading, cells, align) ;
										rg.tableBody(columns, tableData, bcellWidth1, bcellAlign1);
										rg.addFormatedText("", 0, 0, 0, 0);
								}
						}
						
						//1. TRAINING DATA
						String trainingQuery ="SELECT APPR_QUES_DESC,APPR_TRNRECOM_COMMENT FROM PAS_APPR_TRN_RECOMMEND_COMMENT "
							+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_QUESTION_CODE)"
							+" WHERE APPR_ID ="+bean.getApprId()+" AND APPR_EMP_ID="+bean.getEmpCode()+" AND APPR_PHASE_ID="+phaseObject[i][0] +" AND APPR_APPRAISER_ID ="+levelObject[j][1];
						
						Object [][] trainingData=getSqlModel().getSingleResult(trainingQuery);
						
						//2. AWARD DATA
						String awardQuery ="SELECT APPR_AWARD_DESC,APPR_AWARD_COMMENT FROM PAS_APPR_AWD_ACHIEVED_COMMENT "
							+" LEFT JOIN PAS_APPR_AWARD_ACHIEVED ON(PAS_APPR_AWARD_ACHIEVED.APPR_AWARD_CODE=PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_AWARD_CODE)"
							+" WHERE PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_ID ="+bean.getApprId()+" AND PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_EMP_ID="+bean.getEmpCode()+" AND APPR_PHASE_ID="+phaseObject[i][0] +" AND APPR_APPRAISER_ID ="+levelObject[j][1];
						
						Object [][] awardData=getSqlModel().getSingleResult(awardQuery);
						
						//3. DISCIPLINARY DATA
						String displQuery ="SELECT APPR_DISCP_ACTION, APPR_DISCP_COMMENTS FROM PAS_APPR_DISCIPLINARY_COMMENT "
							+" LEFT JOIN PAS_APPR_DISCIPLINARY ON (PAS_APPR_DISCIPLINARY.APPR_DISCP_ID=PAS_APPR_DISCIPLINARY_COMMENT.APPR_DISCP_ID)"
							+" WHERE PAS_APPR_DISCIPLINARY_COMMENT.APPR_ID="+bean.getApprId()+" AND PAS_APPR_DISCIPLINARY_COMMENT.APPR_EMP_ID="+bean.getEmpCode()
							+" AND APPR_PHASE="+phaseObject[i][0]+" AND APPR_APPRAISER_ID ="+levelObject[j][1];      

						Object [][] displData=getSqlModel().getSingleResult(displQuery);
						
						//4. CAREER DATA
						String careerQuery ="SELECT APPR_QUES_DESC,APPR_CAREER_COMMENT FROM PAS_APPR_CAREER_COMMENT "
							+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_CAREER_COMMENT.APPR_QUESTION_CODE)"
							+" WHERE PAS_APPR_CAREER_COMMENT.APPR_ID="+bean.getApprId()+" AND PAS_APPR_CAREER_COMMENT.APPR_EMP_ID="+bean.getEmpCode()
							+" AND APPR_PHASE="+phaseObject[i][0] +" AND APPR_APPRAISER_ID = "+levelObject[j][1];          
							

						Object [][] careerData=getSqlModel().getSingleResult(careerQuery);
						
						/*
						 * Training Details Section
						 * 
						 */
						logger.info("training applicable----------------"+applicability[0][0]);
						if(String.valueOf(applicability[0][0]).equals("Y")){
							
							try {
								String trnVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
												+" AND APPR_TEMPLATE_ID = "+bean.getTemplateCode()+" "
												+" AND APPR_PHASE ="+phaseObject[i][0]+""
												+" AND APPR_SECTION_TYPE = 'T'" ;
								
								Object trnPhaseVisibility[][] = getSqlModel().getSingleResult(trnVisibility);
								
								if(String.valueOf(trnPhaseVisibility[0][0]).equals("Y")){        
										heading [0][0]="              Section :Training Details";
										rg.tableBodyNoBorder(heading, cells, align) ;
											if(trainingData.length !=0){
												logger.info("j======"+j);
												Object trainingTable [][]= new Object[trainingData.length][2];

												for(int k=0;k<trainingData.length;k++){
														trainingTable [k][0] = trainingData [k][0];	
														trainingTable [k][1] = trainingData [k][1];
												}
										rg.tableBody(trainingColumns, trainingData, bcellWidthStatic, bcellAlignStatic);
											}
									}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}
							}
						/*
						 * Award Details Section
						 * 
						 */
						logger.info("Award Details applicable----------------"+applicability[0][1]);
						if(String.valueOf(applicability[0][1]).equals("Y")){
							
							try {
								String awdVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
												+" AND APPR_TEMPLATE_ID = "+bean.getTemplateCode()+" "
												+" AND APPR_PHASE ="+phaseObject[i][0]+""
												+" AND APPR_SECTION_TYPE = 'A'" ;

								Object awdPhaseVisibility[][] = getSqlModel().getSingleResult(awdVisibility);
								
								if(String.valueOf(awdPhaseVisibility[0][0]).equals("Y")){
										
										heading [0][0]="              Section :Awards & Recognition";
										rg.tableBodyNoBorder(heading, cells, align) ;
										
											if(awardData.length !=0){
												Object awardTable [][]= new Object[1][2];
												awardTable [0][0] = awardData [j][0];	
												awardTable [0][1] = awardData [j][1];
											rg.tableBody(awardsColumns, awardTable, bcellWidthStatic, bcellAlignStatic);
											}
									}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}
							}
						/*
						 * Disciplinary Action History Section
						 * 
						 */
						logger.info("Disciplinary Action applicable----------------"+applicability[0][2]);
						if(String.valueOf(applicability[0][2]).equals("Y")){
							
							try {
								String discVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
												+" AND APPR_TEMPLATE_ID = "+bean.getTemplateCode()+" "
												+" AND APPR_PHASE ="+phaseObject[i][0]+""
												+" AND APPR_SECTION_TYPE = 'D'" ;

									Object discPhaseVisibility[][] = getSqlModel().getSingleResult(discVisibility);

								if(String.valueOf(discPhaseVisibility[0][0]).equals("Y")){
									
										heading [0][0]="              Section :Disciplinary Action History";
										rg.tableBodyNoBorder(heading, cells, align) ;
										if(displData.length !=0){
											Object dispTable [][]= new Object[1][2];
											
											dispTable [0][0] = displData [j][0];	
											dispTable [0][1] = displData [j][1];
											
											rg.tableBody(dispColumns, dispTable, bcellWidthStatic, bcellAlignStatic);
										}
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}
						}
						/*
						 * Career Progression Details Section
						 * 
						 */
						logger.info("Career Progression applicable----------------"+applicability[0][3]);
						if(String.valueOf(applicability[0][3]).equals("Y")){
							
							try {
								String carVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
								+" AND APPR_TEMPLATE_ID = "+bean.getTemplateCode()+" "
								+" AND APPR_PHASE ="+phaseObject[i][0]+""
								+" AND APPR_SECTION_TYPE = 'C'" ;

								Object carPhaseVisibility[][] = getSqlModel().getSingleResult(carVisibility);

								if(String.valueOf(carPhaseVisibility[0][0]).equals("Y")){
									
									heading [0][0]="              Section :Career Progression Details";
									
									rg.tableBodyNoBorder(heading, cells, align) ;
										if(careerData.length !=0){
												Object careerTable [][]= new Object[careerData.length][2];
													for(int z=0;z<careerData.length;z++){
															careerTable [z][0] = careerData [z][0];	
															careerTable [z][1] = careerData [z][1];
													}
											rg.tableBody(trainingColumns, careerData, bcellWidthStatic, bcellAlignStatic);

											}
									}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					
					}
				}
				
				
							
				String scoreQuery ="SELECT PHASE_QUES_TOTAL_RATING,PHASE_QUES_TOTAL_WT,PHASE_WEIGHTAGE,PHASE_FINAL_SCORE FROM PAS_APPR_TRACKING"
					+" WHERE APPR_ID ="+bean.getApprId()+" AND EMP_ID="+bean.getEmpCode()+" and phase_id="+phaseObject[i][0];
				
				Object [][] scoreData =getSqlModel().getSingleResult(scoreQuery);
						
				
				if(scoreData != null && scoreData.length > 0 ){
					if(String.valueOf(phaseObject[i][2]).equals("Y")){
						/*
						 * phase wise rating yes
						 */
					//	for (int m = 0; m < scoreData.length;m++) {
							
						
						int totalWeight=0;
						String finalScore="0";
						Object tempObject [][]= new Object [1][6];
						tempObject [0][0] = scoreVector.size()+1;	// sr no
						
						logger.info("section length------------"+sectionObject.length);
														
						if(String.valueOf(phaseObject[i][3]).equals("0")){
							tempObject [0][1] = phaseObject[i][1];	// phase name
							tempObject [0][2] = "NA";				// phase weightage
							tempObject [0][3] = "NA";				// question weightage
							tempObject [0][4] = "NA";				// question rating
							tempObject [0][5] = "NA" ;	
						}else{
							tempObject [0][1] = phaseObject[i][1];
							for(int n=0;n<sectionObject.length;n++){
								
								String sectionScoreQuery= " SELECT SUM(nvl(APPR_QUES_WEIGHTAGE,0)),SUM(nvl(APPR_QUES_PERCENT_WT,0)) FROM PAS_APPR_COMMENTS " 
													+" WHERE APPR_ID = "+bean.getApprId()+" AND APPR_SECTION_ID = "+sectionObject[n][0]+" AND APPR_EMP_ID = "+bean.getEmpCode()+" AND APPR_PHASE_ID = "+phaseObject[i][0]+" AND "
													+" APPR_EVALUATOR_LEVEL = (SELECT MAX(APPR_EVALUATOR_LEVEL) FROM PAS_APPR_COMMENTS  "
													+"	WHERE APPR_ID ="+bean.getApprId()+" AND APPR_SECTION_ID="+sectionObject[n][0]+" AND APPR_EMP_ID="+bean.getEmpCode()+" AND APPR_PHASE_ID = "+phaseObject[i][0]+")";
								
								Object [][] sectionScoreObject = getSqlModel().getSingleResult(sectionScoreQuery);
								
								if(n!=0){							
									tempObject [0][1] = ""+tempObject [0][1] +" + "+ sectionObject[n][1];
									tempObject [0][2] = phaseObject[i][3];				// phase weightage
									tempObject [0][3] = ""+tempObject [0][3] +" + "+sectionScoreObject [0][0];
									tempObject [0][4] = ""+tempObject [0][4] +" + "+Utility.twoDecimals(""+sectionScoreObject [0][1]);
									
									
									totalScore = totalScore + Double.parseDouble(Utility.twoDecimals(""+sectionScoreObject [0][1]));
									totalWeight = totalWeight + Integer.parseInt(""+sectionScoreObject [0][0]);
								}
								else{
									tempObject [0][1] = ""+tempObject [0][1] +" ( "+ sectionObject[n][1];
									tempObject [0][2] = phaseObject[i][3];				// phase weightage
									tempObject [0][3] =  " ( "+sectionScoreObject [0][0];
									tempObject [0][4] =  " ( "+Utility.twoDecimals(""+sectionScoreObject [0][1]);
									
									totalScore = Double.parseDouble(Utility.twoDecimals(""+sectionScoreObject [0][1]));
									totalWeight = Integer.parseInt(""+sectionScoreObject [0][0]);
								}
								
								logger.info("phase+section-------------"+tempObject [0][1]);
								
													
							}
							int score =Integer.parseInt(""+Math.round(totalScore));
							finalScore=getPercent(""+phaseObject[i][3],score,totalWeight);
							logger.info("finalScore-------------"+finalScore);
							tempObject [0][1] = ""+tempObject [0][1]+" )";						
							tempObject [0][3] = ""+tempObject [0][3]+" )";	
							tempObject [0][4] = ""+tempObject [0][4]+" )";	
							//tempObject [0][3] = scoreData[m][1];				// question weightage
							//tempObject [0][4] = scoreData[m][0];				// question rating
							tempObject [0][5] = finalScore ;				// final score
						}
						scoreVector.add(tempObject);
						
						//}
					}else{
						for (int m = 0; m < scoreData.length; m++) {
							Object tempObject [][]= new Object [1][5];
							tempObject [0][0] = scoreVector.size()+1;							// sr no
							tempObject [0][1] = phaseObject[i][1];			// phase name
							tempObject [0][2] = scoreData[m][1];				// question weightage
							tempObject [0][3] = scoreData[m][0];				// question rating
							tempObject [0][4] = scoreData[m][3] ;							// final score
							scoreVector.add(tempObject);
						}
						
					}
					
					rg.pageBreak();
				}
			
			}
			
			rg.pageBreak();
			rg.addFormatedText("\nEMPLOYEE APPRAISAL SCORE SHEET ", 6, 0, 1, 0);
			rg.addText("Date: "+dateData[0][0], 0, 2, 0);
			rg.addFormatedText("\n", 6, 0, 0, 0);
			rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
			heading [0][0]="Score Sheet :";
			rg.tableBodyBold(heading, cells, align) ;
			rg.addFormatedText("", 6, 0, 0, 0);
			
			String finalScoreQuery ="SELECT APPR_FINAL_SCORE, APPR_FINAL_SCORE_VALUE,APPR_FINAL_SCORE_DESC,APPR_ADJUST_FACTOR,APPR_SCORE_ADJUST FROM PAS_APPR_SCORE "
									+" WHERE APPR_ID ="+bean.getApprId()+ " AND EMP_ID ="+bean.getEmpCode();
									
			Object finalScoreObject [][]= getSqlModel().getSingleResult(finalScoreQuery);
			
			if(finalScoreObject !=null && finalScoreObject.length > 0 ){
				if(String.valueOf(phaseObject[0][2]).equals("Y")){
					scoreFinalData = new Object [scoreVector.size()][6];
					for (int m = 0; m <scoreFinalData.length ; m++) {
						scoreFinalData [m][0] = String.valueOf(((Object[][])scoreVector.get(m))[0][0]); 
						scoreFinalData [m][1] = String.valueOf(((Object[][])scoreVector.get(m))[0][1]); 
						scoreFinalData [m][2] = String.valueOf(((Object[][])scoreVector.get(m))[0][2]); 
						scoreFinalData [m][3] = String.valueOf(((Object[][])scoreVector.get(m))[0][3]); 
						scoreFinalData [m][4] = String.valueOf(((Object[][])scoreVector.get(m))[0][4]); 
						scoreFinalData [m][5] = String.valueOf(((Object[][])scoreVector.get(m))[0][5]); 
						
					}
					Object totalScoreObj[][]= new Object[1][6];
					
					Object adjusrObject[][] = new Object[1][6];
					
					
					adjusrObject [0][1] = "Adjustment Factor";
					adjusrObject [0][0] ="";
					adjusrObject [0][2] ="";
					adjusrObject [0][3] ="";
					adjusrObject [0][4] ="";
					if(String.valueOf(finalScoreObject[0][4]).equals("null") || String.valueOf(finalScoreObject[0][4])== null || String.valueOf(finalScoreObject[0][4]).equals(" ") )
						adjusrObject [0][5]="";
					else
						adjusrObject [0][5] =checkNull(String.valueOf(finalScoreObject[0][3]))+" "+checkNull(String.valueOf(finalScoreObject[0][4]));
					
					
					totalScoreObj [0][1] = "Total Score";
					totalScoreObj [0][0] ="";
					totalScoreObj [0][2] ="";
					totalScoreObj [0][3] ="";
					totalScoreObj [0][4] ="";
					totalScoreObj [0][5] =checkNull(String.valueOf(finalScoreObject[0][0]));
					String scoreColumns[] = {"Sr.No.","Phase Name","Phase Weightage","Question Weightage","Question Rating","Score"};
					
					int[] bcellWidthScore = { 5, 35,15,15,15,15 };
					int[] bcellAlignScore = { 0, 0,1, 1, 1, 1};
					rg.tableBody(scoreColumns,scoreFinalData, bcellWidthScore, bcellAlignScore);
					rg.tableBody(adjusrObject, bcellWidthScore, bcellAlignScore);
					rg.tableBody(totalScoreObj, bcellWidthScore, bcellAlignScore);
					
				}else{
	
					scoreFinalData = new Object [scoreVector.size()][5];
					
					for (int m = 0; m <scoreFinalData.length ; m++) {
						scoreFinalData [m][0] = String.valueOf(((Object[][])scoreVector.get(m))[0][0]); 
						scoreFinalData [m][1] = String.valueOf(((Object[][])scoreVector.get(m))[0][1]); 
						scoreFinalData [m][2] = String.valueOf(((Object[][])scoreVector.get(m))[0][2]); 
						scoreFinalData [m][3] = String.valueOf(((Object[][])scoreVector.get(m))[0][3]); 
						scoreFinalData [m][4] = String.valueOf(((Object[][])scoreVector.get(m))[0][4]); 
					}
					
					totalScore = totalScore / Integer.parseInt(""+scoreFinalData.length);
					
					String scoreColumns[] = {"Sr.No.","Phase Name","Question Weightage","Question Rating","Score"};
					
					Object totalScoreObj[][]= new Object[1][5];
					
					Object adjusrObject[][] = new Object[1][5];
					
					logger.info("Adjustment Factor-------------"+finalScoreObject[0][4]);
					adjusrObject [0][1] = "Adjustment Factor";
					adjusrObject [0][0] ="";
					adjusrObject [0][2] ="";
					adjusrObject [0][3] ="";
					if(String.valueOf(finalScoreObject[0][4]).equals("null") || String.valueOf(finalScoreObject[0][4])== null || String.valueOf(finalScoreObject[0][4]).equals(" ") )
						adjusrObject [0][4]="";
					else
						adjusrObject [0][4] =checkNull(String.valueOf(finalScoreObject[0][3]))+" "+checkNull(String.valueOf(finalScoreObject[0][4]));
					
					totalScoreObj [0][1] = "Total Score";
					totalScoreObj [0][0] ="";
					totalScoreObj [0][2] ="";
					totalScoreObj [0][3] ="";
					totalScoreObj [0][4] =checkNull(String.valueOf(finalScoreObject[0][0]));
					
					int[] bcellWidthScore = { 15, 40,15,15,15 };
					int[] bcellAlignScore = { 0, 0,1, 1, 1};
					
					rg.tableBody(scoreColumns,scoreFinalData, bcellWidthScore, bcellAlignScore);
					rg.tableBody(adjusrObject, bcellWidthScore, bcellAlignScore);
					rg.tableBody(totalScoreObj, bcellWidthScore, bcellAlignScore);
				
				}
				
				
				String ratingAchieved = checkNull(String.valueOf(finalScoreObject[0][0]))+" "+checkNull(String.valueOf(finalScoreObject[0][1]));
				System.out.println(ratingAchieved);
				if(!(checkNull(String.valueOf(finalScoreObject[0][1])).equals(""))){
					ratingAchieved += " ("+checkNull(String.valueOf(finalScoreObject[0][2]))+")";
				}
			
				System.out.println(ratingAchieved);
				rg.addFormatedText("\n", 4, 0, 0, 0);
				heading [0][0]= "Rating  Achieved :  ";
				rg.tableBodyBold(heading, cells, align) ;
				rg.addText(ratingAchieved, 2, 0, 0);
				rg.addFormatedText("\n", 4, 0, 0, 0);
				rg.addFormatedText("\n", 4, 0, 0, 0);
			
			}
			
			heading [0][0]="HR comments  :";
			rg.tableBodyBold(heading, cells, align) ;
			rg.addFormatedText("", 6, 0, 0, 0);
			rg.addFormatedText("\n", 4, 0, 0, 0);
			rg.addFormatedText("\n", 4, 0, 0, 0);
			rg.addFormatedText("\n", 4, 0, 0, 0);
			rg.addFormatedText("\n", 4, 0, 0, 0);
			rg.addFormatedText("\n", 4, 0, 0, 0);
			rg.addFormatedText("\n", 4, 0, 0, 0);
			
			/*
			 * Space for adding Management comments if any
			 * 
			 */
			heading [0][0]="Management Comment :";
			rg.tableBodyBold(heading, cells, align) ;
			rg.addFormatedText("\n", 4, 0, 0, 0);
			rg.addFormatedText("\n", 4, 0, 0, 0);
			rg.addFormatedText("\n", 4, 0, 0, 0);
			rg.addFormatedText("\n", 4, 0, 0, 0);
			rg.addFormatedText("\n", 4, 0, 0, 0);
			rg.addFormatedText("\n", 4, 0, 0, 0);
			
			rg.addFormatedText("__________________", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 0, 3);
			rg.addFormatedText("  Authorized Signatory ", 1, 0, 2, 3);
			
			
			}
			catch (Exception e) {
				e.printStackTrace();
				rg.addFormatedText("No Appraisal record for --"+bean.getEmpName(), 1, 0, 1, 3);
				logger.info("Exception in getreport()"+e);
			}
			
			rg.pageBreak();
			
			
			rg.createReport(response);

		}
		
		public void getTemplateCode(AppraisalReport bean,String empCode){
			
			String templateQuery =" SELECT DISTINCT PAS_APPR_EMP_GRP_HDR.APPR_TEMPLATE_ID,APPR_INSTRUCTION_FLAG FROM PAS_APPR_EMP_GRP_DTL "
							+" LEFT JOIN PAS_APPR_EMP_GRP_HDR ON (PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID = PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID) "
							+" LEFT JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID = PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID) "
							+" LEFT JOIN PAS_APPR_TEMPLATE ON (PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID = PAS_APPR_TEMPLATE.APPR_TEMPLATE_ID) "
							+" WHERE APPR_EMP_GRP_EMPID = "+empCode+" AND PAS_APPR_EMP_GRP_HDR.APPR_ID =  "+bean.getApprId()+" ";
			
			
			
			Object [][] visibilityData = getSqlModel().getSingleResult(templateQuery);
			
						logger.info("visibilityData=========="+visibilityData.length);
			if (visibilityData != null && visibilityData.length != 0) {
				
					bean.setTemplateCode(checkNull(String.valueOf(visibilityData[0][0])));
							
			}
		}
		

		public String getPercent(String weightage, int rating, int outOfRating){
			String percent = "0";
			try{
				logger.info("weightage-----"+weightage);
				logger.info("rating-----"+rating);
				logger.info("outOfRating-----"+outOfRating);
				
					percent = ""+Math.round((Double.parseDouble(weightage) *  rating)/outOfRating);
								
				
				logger.info("percent"+percent);
				return percent;
			}catch(Exception e){
				e.printStackTrace();
				return "0";
			}
		}
	/* 
	 * method name : checkNull 
	 * purpose     : check the null value
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {
		
		if (result == null || result.equals("null") || result.equals(" ")) {
			return "";
		} else {
			return result;
		}
	}
	
}
	

