package org.paradyne.model.PAS;
/*
 * @saipavan voleti
 * Date:20-06-2008
 * 
 */

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.PAS.AppraisalFormReport;
import org.paradyne.bean.PAS.AppraisalReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

public class AppraisalFormReportModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AppraisalFormReportModel.class);
	
	public void getAppraisalDtls(AppraisalFormReport bean){
		
		String sql="SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),"
				  +"TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID FROM PAS_APPR_CALENDAR 	WHERE APPR_ID="+bean.getApprId();
		Object data[][]=getSqlModel().getSingleResult(sql);
		
		if(data!=null && data.length>0){
			bean.setApprFrom(String.valueOf(data[0][1]));
			bean.setApprTo(String.valueOf(data[0][2]));
		}
		
	}

				
		/* method name : getReport
		 * purpose     : to show the PDF report
		 * return type : void
		 * parameter   : HttpServletRequest request,HttpServletResponse response, ApprisalComment instance
		 */
		public void getReport(HttpServletRequest request,
				HttpServletResponse response, AppraisalFormReport bean) {
			String s = "\nPerformance  Appraisal Form Report";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",	s);
			Object [][]heading=new Object[1][1];
			int []cells={25};
			int []align={0};
			double totalScore =0.0;
			String dateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
			Vector<Object> scoreVector= new Vector<Object>();
			Object scoreFinalData [][]= null;
			rg.addFormatedText(s, 6, 0, 1, 0);
			rg.addText("Date: "+dateData[0][0], 0, 2, 0);
			rg.addFormatedText("\n", 6, 0, 0, 0);
			
			
			//Iterate through the list of employees if a single employee is not selected
			if(bean.getEmpId().equals("")){
				
				
				
				//Gather all employees for an appraisal based on specified criteria
				
				
				
				
			}else{//Employee id selected
				
			String query = " SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,' '), "
				+ " NVL(HRMS_CENTER.CENTER_NAME,''),TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY '),TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY') FROM PAS_APPR_ELIGIBLE_EMP "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID ) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
				+ " LEFT JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_ELIGIBLE_EMP.APPR_ID)"
				+ " WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getApprId()+"  AND PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID = "+ bean.getEmpId()  ;
			/*
			if(!bean.getDivId().equals("")){//Division selected
				query+=" AND HRMS_EMP_OFFC.EMP_DIV="+bean.getDivId();
				
			}else if(!bean.getBranchId().equals("")){//Branch selected
				query+=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
				
			}else if(!bean.getDeptId().equals("")){//Department selected
				query+=" AND HRMS_EMP_OFFC.EMP_DEPT="+bean.getDeptId();
				
			}else if(!bean.getDesgId().equals("")){//Designation selected
				query+=" AND HRMS_EMP_OFFC.EMP_RANK="+bean.getDesgId();
				
			}*/

			Object [][] result = getSqlModel().getSingleResult(query);
			
			 
			Object data[][] = new Object[5][6];

			try{
					logger.info("result.length===="+result.length);
					
				data[0][0] = "Appraisal Code ";
				data[0][1] = ":";
				data[0][2] = ""+bean.getApprCode();
				data[0][3] = "";
				data[0][4] = "";
				data[0][5] = "";
				
				data[1][0] = "Employee Name ";
				data[1][1] = ":";
				data[1][2] = ""+String.valueOf(result[0][1]);
				data[1][3] = "";
				data[1][4] = "";
				data[1][5] = "";
	
				data[2][0] = "Department ";
				data[2][1] = ":";
				data[2][2] = String.valueOf(result[0][3]);
				data[2][3] = "Designation ";
				data[2][4] = ":";
				data[2][5] = String.valueOf(result[0][2]);
	
				data[3][0] = "Branch ";
				data[3][1] = ":";
				data[3][2] = String.valueOf(result[0][4]);
				data[3][3] = "Date of Joining ";
				data[3][4] = ":";
				data[3][5] = String.valueOf(result[0][5]);
				
				data[4][0] = "Appraisal Start Date ";
				data[4][1] = ":";
				data[4][2] = String.valueOf(result[0][6]);
				data[4][3] = "Appraisal End Date ";
				data[4][4] = ":";
				data[4][5] = String.valueOf(result[0][7]);
	
				int[] bcellWidth = { 20, 2, 20, 20, 2, 20 };
				int[] bcellAlign = { 0, 0, 0, 0, 0, 0 };
				
				rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				
							
				String trainingColumns[] = {"Sr.No","Question Description ","Comments"};
				String awardsColumns[] = {"Award Name","Reason For Award"};
				String dispColumns[] = {"Disciplinary Action","Comments"};
				
				int[] bcellWidthStatic = { 50, 50 };
				int[] bcellAlignStatic = { 0, 0};
				
				String templateQuery ="SELECT APPR_TEMPLATE_ID,PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID, APPR_EMP_GRP_NAME FROM PAS_APPR_EMP_GRP_DTL"
									 +" INNER JOIN PAS_APPR_EMP_GRP_HDR ON(PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID=PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID)"
									 +" WHERE APPR_ID="+bean.getApprId()+" AND APPR_EMP_GRP_EMPID="+bean.getEmpId();
				Object templateData[][] = getSqlModel().getSingleResult(templateQuery);
				
				
				//if templateData==null then employee not configured under any group in section mapping
				if(templateData!=null && templateData.length>0){
								
								String phaseQuery ="SELECT APPR_PHASE_ID,APPR_PHASE_NAME,APPR_PHASEWISE_RATING,APPR_PHASE_ORDER FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID="+bean.getApprId()+" AND APPR_PHASE_STATUS='A'"
													+" ORDER BY APPR_PHASE_ORDER";
								
								String applicabilityQuery = " SELECT APPR_TRN_FLAG,APPR_AWARD_FLAG,APPR_DISCIPLINARY_FLAG,APPR_CAREER_FLAG " 
					        						+" FROM PAS_APPR_TEMPLATE " 
					        						+" WHERE APPR_ID = "+bean.getApprId()+" AND APPR_TEMPLATE_ID = "+templateData[0][0]+" ";
													
								Object [][] applicability = getSqlModel().getSingleResult(applicabilityQuery);
					
								Object phaseObject [][]=getSqlModel().getSingleResult(phaseQuery);
								
								if(phaseObject!=null && phaseObject.length>0){					
										for (int i = 0; i < phaseObject.length; i++) {
											
															
											String levelQuery =" SELECT APPR_APPRAISER_LEVEL FROM PAS_APPR_APPRAISER " 
																+" LEFT JOIN PAS_APPR_APPRAISER_GRP_DTL ON(PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) "	
																+" WHERE APPR_PHASE_ID="+phaseObject[i][0]+" AND APPR_ID ="+bean.getApprId()+" and APPR_APPRAISEE_ID="+bean.getEmpId()                                                                                        	;
											
											Object levelObject [][]=getSqlModel().getSingleResult(levelQuery);
											
											String sectionQuery = "SELECT DISTINCT PAS_APPR_QUES_MAPPING.APPR_SECTION_ID, APPR_SECTION_NAME FROM PAS_APPR_QUES_MAPPING "
																	+" LEFT JOIN PAS_APPR_SECTION_HDR on(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_QUES_MAPPING.APPR_SECTION_ID)"
																	+" LEFT JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID=PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID)"
																	+" WHERE APPR_PHASE="+phaseObject[i][0]+" AND APPR_EMP_GRP_EMPID = "+bean.getEmpId();
												//+" ORDER BY APPR_QUESTION_ORDER";
												//+" ORDER BY PAS_APPR_QUES_MAPPING.APPR_QUESTION_ORDER " ;
											
											Object sectionObject [][]=getSqlModel().getSingleResult(sectionQuery);
											
											//1. TRAINING DATA
											String trainingQuery ="SELECT ROWNUM,APPR_QUES_DESC,'' FROM PAS_APPR_TRN_RECOMMEND "
												+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_TRN_RECOMMEND.APPR_QUESTION_CODE)"
												+" WHERE APPR_ID ="+bean.getApprId()+" AND APPR_TEMPLATE_ID="+templateData[0][0];					
											Object [][] trainingData=getSqlModel().getSingleResult(trainingQuery);
											
											/*//2. AWARD DATA
											String awardQuery ="SELECT APPR_AWARD_DESC,APPR_AWARD_COMMENT FROM PAS_APPR_AWD_ACHIEVED_COMMENT "
												+" LEFT JOIN PAS_APPR_AWARD_ACHIEVED ON(PAS_APPR_AWARD_ACHIEVED.APPR_AWARD_CODE=PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_AWARD_CODE)"
												+" WHERE PAS_APPR_AWARD_ACHIEVED.APPR_ID ="+bean.getApprId()+" AND PAS_APPR_AWARD_ACHIEVED.APPR_TEMPLATE_ID="+bean.getTemplateId();					
											Object [][] awardData=getSqlModel().getSingleResult(awardQuery);
											
											//3. DISCIPLINARY DATA
											String displQuery ="SELECT APPR_DISCP_ACTION, APPR_DISCP_COMMENTS FROM PAS_APPR_DISCIPLINARY_COMMENT "
												+" LEFT JOIN PAS_APPR_DISCIPLINARY ON (PAS_APPR_DISCIPLINARY.APPR_DISCP_ID=PAS_APPR_DISCIPLINARY_COMMENT.APPR_DISCP_ID)"
												+" WHERE PAS_APPR_DISCIPLINARY_COMMENT.APPR_ID="+bean.getApprId()+" AND PAS_APPR_DISCIPLINARY_COMMENT.APPR_EMP_ID="+bean.getEmpId()
												+" AND APPR_PHASE="+phaseObject[i][0] ; 
											Object [][] displData=getSqlModel().getSingleResult(displQuery);*/
											
											//4. CAREER DATA
											/*String careerQuery ="SELECT ROWNUM,APPR_QUES_DESC,'' FROM PAS_APPR_CAREER_COMMENT "
												+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_CAREER_COMMENT.APPR_QUESTION_CODE)"
												+" WHERE PAS_APPR_CAREER_COMMENT.APPR_ID="+bean.getApprId()+" AND PAS_APPR_CAREER_COMMENT.APPR_EMP_ID="+bean.getEmpId()
												+" AND APPR_PHASE="+phaseObject[i][0] ;   */
											
											String careerQuery ="SELECT ROWNUM,NVL(APPR_QUES_DESC,' ') FROM PAS_APPR_CAREER" 
															   +" INNER JOIN PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_CAREER.APPR_QUESTION_CODE )" 
															   +" WHERE APPR_ID="+bean.getApprId()+" AND APPR_TEMPLATE_ID="+templateData[0][0];
											
											Object [][] careerData=getSqlModel().getSingleResult(careerQuery);
											
											
														 
											if(sectionObject!=null && sectionObject.length !=0){
												heading [0][0]="Phase :"+phaseObject[i][1];
												rg.tableBodyBold(heading, cells, align) ;
												
											}
											
											
											
											
											
											if(levelObject.length !=0){
												for (int j = 0; j < levelObject.length; j++) {
													
													//Show levels only for phases other than self
													if(!String.valueOf(phaseObject[i][3]).equals("1")){//Phase order=1
														heading [0][0]="         Level :"+(j+1);
														rg.tableBodyNoBorder(heading, cells, align) ;
													}
													for (int k = 0; k < sectionObject.length; k++) {
														
														String visibilityQuery =" SELECT APPR_SECTION_VISIBILITY,APPR_SECTION_RATING,APPR_SECTION_COMMENT FROM PAS_APPR_SECTION_DTL"
																				+" WHERE APPR_ID = "+bean.getApprId()+" AND APPR_SECTION_ID = "+sectionObject[k][0]+" AND APPR_PHASE_ID = "+phaseObject[i][0];
														
														
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
															 
																
															 startQuery =" SELECT  ROWNUM,APPR_QUES_DESC,APPR_QUESTION_WT,'','','' ";
															 
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
															
															 
															 startQuery =" SELECT  ROWNUM,APPR_QUES_DESC,APPR_QUESTION_WT,'','' ";
															 
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
															 
															 
															 startQuery =" SELECT  ROWNUM,APPR_QUES_DESC,'' ";
															 
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
															+" FROM PAS_APPR_QUES_MAPPING"
															+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_QUES_MAPPING.APPR_PHASE AND PAS_APPR_PHASE_CONFIG.APPR_ID=PAS_APPR_QUES_MAPPING.APPR_ID)" 
															+" LEFT JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_QUES_MAPPING.APPR_SECTION_ID AND"
															+" PAS_APPR_SECTION_HDR.APPR_ID=PAS_APPR_QUES_MAPPING.APPR_ID"
															+" )" 
															+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID)" 
															+" LEFT JOIN PAS_APPR_COMMENTS ON(PAS_APPR_COMMENTS.APPR_SECTION_ID=PAS_APPR_QUES_MAPPING.APPR_SECTION_ID AND  PAS_APPR_QUES_MAPPING.APPR_ID=PAS_APPR_COMMENTS.APPR_ID AND "
															+" PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID=PAS_APPR_COMMENTS.APPR_TEMPLATE_ID AND PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_COMMENTS.APPR_QUESTION_CODE AND" 
															+" PAS_APPR_COMMENTS.APPR_ID="+bean.getApprId()+" AND PAS_APPR_COMMENTS.APPR_SECTION_ID="+sectionObject[k][0]+" AND PAS_APPR_COMMENTS.APPR_PHASE_ID="+phaseObject[i][0]+" AND APPR_EMP_ID="+bean.getEmpId()
															+" )"
															+" INNER JOIN PAS_APPR_EMP_GRP_HDR ON(PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID=PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID)" 
															+" INNER JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID=PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID AND APPR_EMP_GRP_EMPID="+bean.getEmpId()+")"
															+" WHERE PAS_APPR_QUES_MAPPING.APPR_ID="+bean.getApprId()+"  AND "
															+" PAS_APPR_QUES_MAPPING.APPR_SECTION_ID ="+sectionObject[k][0]+" AND" 
															+" PAS_APPR_QUES_MAPPING.APPR_PHASE ="+phaseObject[i][0] 
															+" ORDER BY APPR_QUESTION_ORDER";

														/*
														
														
														
															+" FROM PAS_APPR_COMMENTS"
															+" LEFT JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID)"
															+" LEFT JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID)"
															+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_COMMENTS.APPR_QUESTION_CODE)"
															+" LEFT JOIN PAS_APPR_QUES_MAPPING ON(PAS_APPR_QUES_MAPPING.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID AND "
															+" PAS_APPR_QUES_MAPPING.APPR_ID=PAS_APPR_COMMENTS.APPR_ID AND"
															+" PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID=PAS_APPR_COMMENTS.APPR_TEMPLATE_ID AND"
															+" PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_COMMENTS.APPR_QUESTION_CODE"
															+" AND PAS_APPR_QUES_MAPPING.APPR_ID="+bean.getApprId()+" AND PAS_APPR_QUES_MAPPING.APPR_SECTION_ID="+sectionObject[k][0]
															+" AND PAS_APPR_QUES_MAPPING.APPR_PHASE="+phaseObject[i][0]
															+" ) "
															+" INNER JOIN PAS_APPR_EMP_GRP_HDR ON(PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID=PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID)"
															+" INNER JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID=PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID AND APPR_EMP_GRP_EMPID="+bean.getEmpId()+")"
															+" WHERE APPR_ID="+bean.getApprId()+" AND APPR_EMP_ID="+bean.getEmpId()+" AND PAS_APPR_COMMENTS.APPR_SECTION_ID ="+sectionObject[k][0]
															+" AND PAS_APPR_COMMENTS.APPR_PHASE_ID ="+phaseObject[i][0]+" AND APPR_EVALUATOR_LEVEL="+levelObject[j][0]+" ORDER BY APPR_QUESTION_ORDER";
														*/
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
																heading [0][0]="Section :"+sectionObject[k][1];
																	//Subheadings like Section:Training Details
																
																								
																	rg.tableBodyNoBorder(heading, cells, align) ;
																	rg.tableBody(columns, tableData, bcellWidth1, bcellAlign1);
																	rg.addFormatedText("", 0, 0, 0, 0);
															}
													}
													
													/*
													 * Training Details Section
													 * 
													 */
													logger.info("training applicable----------------"+applicability[0][0]);
													
													if(String.valueOf(applicability[0][0]).equals("Y")){
														
														String trnVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
																		+" AND APPR_TEMPLATE_ID = "+templateData[0][0]+" "
																		+" AND APPR_PHASE ="+phaseObject[i][0]+""
																		+" AND APPR_SECTION_TYPE = 'T'" ;
														
														Object trnPhaseVisibility[][] = getSqlModel().getSingleResult(trnVisibility);
														
														if(String.valueOf(trnPhaseVisibility[0][0]).equals("Y")){
															System.out.println("SELF PHASE TRAINING>>>>"+phaseObject[i][3]);
																										
																heading [0][0]="Section :Training Details";
																rg.tableBodyNoBorder(heading, cells, align) ;
																	
																//////ADDED BY HEMANT
																	if(String.valueOf(phaseObject[i][3]).equals("1")){//SELF PHASE
																			
																			//1. Show training attended section
																			logger.info("SELF PHASE TRAINING QUESTIONS>>>>>");
																			String colNames[]={"Sr.No","Training Description","Duration   (days)","Training From","Training To","Sponsored By"};
																			int width[]={5,15,10,10,10,25};
																			int allignment[]={0,0,2,1,1,0};
																			Object emptyData[][] = new Object[5][6];
																			rg.tableBody(colNames, org.paradyne.lib.Utility.checkNullObjArr(emptyData,"\n\n"), width,allignment);
																			
																	}else{
																		
																		//1. Show training attended section													
																		String colNames[]={"Sr.No","Training Description","Duration   (days)","Training From","Training To","Sponsored By","Appraiser Comments"};
																		int width[]={5,15,10,10,10,25,30};
																		int allignment[]={0,0,2,1,1,0,0};
																		Object emptyData[][] = new Object[5][7];
																		rg.tableBody(colNames, org.paradyne.lib.Utility.checkNullObjArr(emptyData,"\n\n"), width,allignment);
																																
																		//2. Training recommendation section														
																		if(trainingData.length !=0){
																		logger.info("j======"+j);
																		Object trainingTable [][]= new Object[trainingData.length][3];
																			
																		/*for(int k=0;k<trainingData.length;k++){
																				trainingTable [k][0] = trainingData [k][0];	
																				trainingTable [k][1] = trainingData [k][1];	
																				trainingTable [k][2] = trainingData [k][2];
																		}*/
																			rg.tableBody(trainingColumns, trainingData, bcellWidthStatic, bcellAlignStatic);
																	  }
																	}	
																	
																	
															}
														}
													/*
													 * Award Details Section
													 * 
													 */
													logger.info("Award Details applicable----------------"+applicability[0][1]);
													if(String.valueOf(applicability[0][1]).equals("Y")){
														
														String awdVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
																		+" AND APPR_TEMPLATE_ID = "+templateData[0][0]+" "
																		+" AND APPR_PHASE ="+phaseObject[i][0]+""
																		+" AND APPR_SECTION_TYPE = 'A'" ;
										
														Object awdPhaseVisibility[][] = getSqlModel().getSingleResult(awdVisibility);
														
														if(String.valueOf(awdPhaseVisibility[0][0]).equals("Y")){
															
																heading [0][0]="Section :Awards & Recognition";
																rg.tableBodyNoBorder(heading, cells, align) ;
																
															//////ADDED BY HEMANT
																if(String.valueOf(phaseObject[i][3]).equals("1")){//SELF PHASE
																		
																		String colNames[]={"Sr.No","Award Description","Award Date","Issuing Authority"};
																		int width[]={4,25,10,20};
																		int allignment[]={0,0,1,0};
																		Object emptyData[][] = new Object[5][6];
																		
																		rg.tableBody(colNames, org.paradyne.lib.Utility.checkNullObjArr(emptyData,"\n\n"), width,allignment);
																	
																}else{//Other phases
																	/*if(awardData.length !=0){
																		Object awardTable [][]= new Object[1][2];
																		awardTable [0][0] = awardData [j][0];	
																		awardTable [0][1] = awardData [j][1];
																		
																	rg.tableBody(awardsColumns, awardTable, bcellWidthStatic, bcellAlignStatic);
																	}*/
				
																	String colNames[]={"Sr.No","Award Description","Award Date","Issuing Authority","Appraiser Comments"};
																	int width[]={4,25,10,20,30};
																	int allignment[]={0,0,1,0,0};
																	Object emptyData[][] = new Object[5][7];
																	
																	rg.tableBody(colNames, org.paradyne.lib.Utility.checkNullObjArr(emptyData,"\n\n"), width,allignment);
																	
																	heading [0][0]="Award Nomination";
																	rg.tableBodyNoBorder(heading, cells, align) ;
																	
																	//rg.addText("Award Nomination", 0, 0, 0);
																	
																	String colNames1[]={"Sr.No","Nominate the award","Reason for nomination "};
																	int width1[]={4,20,20};
																	int allignment1[]={0,0,1};
																	Object emptyData1[][] = new Object[5][3];
																	
																	rg.tableBody(colNames1, org.paradyne.lib.Utility.checkNullObjArr(emptyData1,"\n\n"), width1,allignment1);
																
																	
																	
																}
															}
														}
													/*
													 * Disciplinary Action History Section
													 * 
													 */
													logger.info("Disciplinary Action applicable----------------"+applicability[0][2]);
													if(String.valueOf(applicability[0][2]).equals("Y")){
														
														String discVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
																		+" AND APPR_TEMPLATE_ID = "+templateData[0][0]+" "
																		+" AND APPR_PHASE ="+phaseObject[i][0]+""
																		+" AND APPR_SECTION_TYPE = 'D'" ;
										
															Object discPhaseVisibility[][] = getSqlModel().getSingleResult(discVisibility);
										
														if(String.valueOf(discPhaseVisibility[0][0]).equals("Y")){
															
																heading [0][0]="Section :Disciplinary Action History";
																rg.tableBodyNoBorder(heading, cells, align) ;
																
															//////ADDED BY HEMANT
																if(String.valueOf(phaseObject[i][3]).equals("1")){//SELF PHASE
																		
																		String colNames[]={"Sr.No","Action","Authority","Date"};
																		int width[]={4,20,15,10};
																		int allignment[]={0,0,0,1};
																		Object emptyData[][] = new Object[5][6];
																		
																		rg.tableBody(colNames, org.paradyne.lib.Utility.checkNullObjArr(emptyData,"\n\n"), width,allignment);
																	
																}else{												
																	/*if(displData.length !=0){
																		Object dispTable [][]= new Object[1][2];
																		
																		dispTable [0][0] = displData [j][0];	
																		dispTable [0][1] = displData [j][1];
																		
																		rg.tableBody(dispColumns, dispTable, bcellWidthStatic, bcellAlignStatic);
																	}*/
																	String colNames[]={"Sr.No","Action","Authority","Date","Appraiser Comments"};
																	int width[]={4,20,15,10,30};
																	int allignment[]={0,0,0,1,0};
																	Object emptyData[][] = new Object[5][7];
																	
																	rg.tableBody(colNames, org.paradyne.lib.Utility.checkNullObjArr(emptyData,"\n\n"), width,allignment);
																
																}
														}
													}
													/*
													 * Career Progression Details Section
													 * 
													 */
													logger.info("Career Progression applicable----------------"+applicability[0][3]);
													if(String.valueOf(applicability[0][3]).equals("Y")){
														
														String carVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+bean.getApprId()+" "
														+" AND APPR_TEMPLATE_ID = "+templateData[0][0]+" "
														+" AND APPR_PHASE ="+phaseObject[i][0]+""
														+" AND APPR_SECTION_TYPE = 'C'" ;
							
														Object carPhaseVisibility[][] = getSqlModel().getSingleResult(carVisibility);
							
														if(String.valueOf(carPhaseVisibility[0][0]).equals("Y")){
															
															heading [0][0]="Section :Career Progression Details";											
															rg.tableBodyNoBorder(heading, cells, align) ;
															
															
															//////ADDED BY HEMANT
																//if(String.valueOf(phaseObject[i][3]).equals("1")){//SELF PHASE
																		
																		String colNames[]={"Sr.No","Question","Comments"};
																		int width[]={4,20,30};
																		int allignment[]={0,0,0};
																		rg.tableBody(colNames,careerData, width,allignment);
																	
																//}else{//Other phases
															
																	/*if(careerData.length !=0){
																			Object careerTable [][]= new Object[careerData.length][2];
																				for(int z=0;z<careerData.length;z++){
																						careerTable [z][0] = careerData [z][1];	
																						careerTable [z][1] = "";//careerData [z][1];
																				}
																		rg.tableBody(trainingColumns, careerTable, bcellWidthStatic, bcellAlignStatic);
														
																		}*/
																  //}//else ends
																}
													}
												
												}
											}else{
												
												rg.addFormatedText("Appraisers not configured for the employee --"+bean.getEmpName(), 1, 0, 1, 3);
												
											}
											
											
										
											String scoreQuery ="SELECT PHASE_QUES_TOTAL_RATING,PHASE_QUES_TOTAL_WT,PHASE_WEIGHTAGE,PHASE_FINAL_SCORE FROM PAS_APPR_TRACKING"
												+" WHERE APPR_ID ="+bean.getApprId()+" AND EMP_ID="+bean.getEmpId()+" and phase_id="+phaseObject[i][0];
											
											Object [][] scoreData =getSqlModel().getSingleResult(scoreQuery);
											
											
											
											
											if(String.valueOf(phaseObject[i][2]).equals("Y")){
												/*
												 * phase wise rating yes
												 */
												for (int m = 0; m < scoreData.length;m++) {
													
												
												Object tempObject [][]= new Object [1][6];
												tempObject [0][0] = scoreVector.size()+1;							// sr no
												tempObject [0][1] = phaseObject[i][1];				// phase name
												if(String.valueOf(scoreData[m][2]).equals("0")){
													tempObject [0][2] = "NA";				// phase weightage
													tempObject [0][3] = "NA";				// question weightage
													tempObject [0][4] = "NA";				// question rating
													tempObject [0][5] = "NA" ;	
												}else{
													tempObject [0][2] = scoreData[m][2];				// phase weightage
													tempObject [0][3] = scoreData[m][1];				// question weightage
													tempObject [0][4] = scoreData[m][0];				// question rating
													tempObject [0][5] = scoreData[m][3] ;				// final score
												}
												scoreVector.add(tempObject);
												
												}
											}else{
												for (int m = 0; m < scoreData.length; m++) {
													Object tempObject [][]= new Object [1][5];
													tempObject [0][0] = scoreVector.size()+1;							// sr no
													tempObject [0][1] = phaseObject[i][1];				// phase name
													tempObject [0][2] = scoreData[m][1];				// question weightage
													tempObject [0][3] = scoreData[m][0];				// question rating
													tempObject [0][4] = scoreData[m][3] ;							// final score
													scoreVector.add(tempObject);
												}
												
											}
											
											rg.pageBreak();
										}
							}
								
						////////////////////////////////////////////////////////////////		
								rg.pageBreak();
				
			} /////IF TEMPLATE
			else{
				
				rg.addFormatedText(bean.getEmpName()+" not configured under any group in section mapping", 1, 0, 1, 3);
				 
			}
				
			}catch (Exception e) {
				e.printStackTrace();
				rg.addFormatedText("Appraisal not configured for the employee --"+bean.getEmpName(), 1, 0, 1, 3);
				logger.info("Exception in getreport()"+e);
			}
			
			rg.pageBreak();
			
			
			rg.createReport(response);

		} //else ends
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