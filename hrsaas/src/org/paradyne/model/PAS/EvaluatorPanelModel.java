package org.paradyne.model.PAS;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.paradyne.bean.PAS.AppraisalReport;
import org.paradyne.bean.PAS.EvaluatorPanel;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;
import com.lowagie.text.Image;

public class EvaluatorPanelModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(EvaluatorPanelModel.class);
	public void getAppraisalRecords(EvaluatorPanel bean,HttpServletRequest request){
		
		Object[][] repData = getSqlModel().getSingleResult(getQuery(1),new Object[]{bean.getUserEmpId()});
		ArrayList<Object> obj = new ArrayList<Object>();
		if(repData!=null && repData.length!=0){
		
			if(repData!=null && repData.length!=0){
		for (int i = 0; i < repData.length; i++) {
			EvaluatorPanel bean1 = new EvaluatorPanel();
			bean1.setApprId(checkNull(String.valueOf(repData[i][0])));
			bean1.setEmpId(checkNull(String.valueOf(repData[i][1])));
			bean1.setEmpName(checkNull(String.valueOf(repData[i][2])));
			bean1.setEmpDeptName(checkNull(String.valueOf(repData[i][3])));
			bean1.setEmpDesgName(checkNull(String.valueOf(repData[i][4])));
			bean1.setPhaseCode(checkNull(String.valueOf(repData[i][5])));
			bean1.setPhaseName(checkNull(String.valueOf(repData[i][6])));
			bean1.setApprCode(checkNull(String.valueOf(repData[i][7])));
			obj.add(bean1);
		}//end of for loop
			}//End of if

		bean.setDataList(obj);
		}else{
			bean.setNoData("true");
			
		}
	
	}
	
	/* 
	 * method name : checkNull
	 * purpose     : check the string is null or not
	 * return type : String
	 * parameter   : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}  // end of if
		else {
			return result;
		} // end of else
	}

	public void getProcessedList(EvaluatorPanel bean,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Object[][] repData = getSqlModel().getSingleResult(getQuery(2),new Object[]{bean.getUserEmpId()});
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),
				repData.length, 20);// to display the page number
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		request.setAttribute("totalPage1", Integer.parseInt(String
				.valueOf(pageIndex[2]))); //   to set the total number of page
		request.setAttribute("pageNo1", Integer.parseInt(String
				.valueOf(pageIndex[3])));// to set the page number
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");

		if(repData!=null && repData.length!=0){
			ArrayList<Object> obj = new ArrayList<Object>();
		for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
			EvaluatorPanel bean1 = new EvaluatorPanel();
			bean1.setApprId(checkNull(String.valueOf(repData[i][0])));
			bean1.setEmpId(checkNull(String.valueOf(repData[i][1])));
			bean1.setEmpName(checkNull(String.valueOf(repData[i][2])));
			bean1.setApprStartDate(checkNull(String.valueOf(repData[i][3])));
			bean1.setApprEndDate(checkNull(String.valueOf(repData[i][4])));
			bean1.setPhaseCode(checkNull(String.valueOf(repData[i][5])));
			bean1.setPhaseName(checkNull(String.valueOf(repData[i][6])));
			bean1.setApprCode(checkNull(String.valueOf(repData[i][7])));
			obj.add(bean1);
		}//end of for loop
		
		bean.setProcessedList(obj);
		bean.setNoData("false");
		}else{
			bean.setNoData("true");
			
		}
	}


	public void getReport(HttpServletRequest request,
			HttpServletResponse response,String empId,String apprId,String phaseCode,String appraiserCode) 
	{

				Object [] apprCode = new Object[2];
					apprCode [0] = empId;
					apprCode [1] = apprId;
				
				String templateCode="";
				Object [][] visibilityData = getSqlModel().getSingleResult(getQuery(3), apprCode);
							
				if (visibilityData != null && visibilityData.length != 0) {
					templateCode= checkNull(String.valueOf(visibilityData[0][0]));
				}		
				ReportDataSet rds = new ReportDataSet();
				rds.setReportType("Pdf");
				rds.setFileName("Appraisal Report");
				int[] bcellWidthStatic = { 8,50, 45 };
				int[] bcellAlignStatic = { 1,0, 0};
				String trainingColumns[] = {"Sr.No","Question Description","Comments"};		
				String awardsColumns[] = {"Sr.No","Award Name","Reason For Award"};		
				String dispColumns[] = {"Sr.No","Disciplinary Action","Comments"};
				
				org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
				Object[][] nameObj = null;
				
				String divQuery="SELECT DIV_NAME,DIV_NAME||',\n'||DIV_ADDRESS1||'\n'||DIV_ADDRESS2||'\n'||DIV_ADDRESS3||'\n'||LOCATION_NAME||'-'||DIV_PINCODE FROM HRMS_DIVISION  "
					+" left join HRMS_LOCATION on (HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID )"
					+" LEFT JOIN HRMS_EMP_OFFC ON(DIV_ID=EMP_DIV) WHERE EMP_ID="+empId;
				
				Object [][]divDetails=getSqlModel().getSingleResult(divQuery);
				
				String logoQuery="select COMPANY_CODE,COMPANY_LOGO from HRMS_COMPANY";
				Object logoObj[][]=getSqlModel().getSingleResult(logoQuery);
				
				  try{
					  if(logoObj!=null && logoObj.length>0)
					  {
						  String filename="";
						  if(!String.valueOf(logoObj[0][1]).equals(""))
						  {
							  nameObj = new Object[1][2];
							  filename=String.valueOf(logoObj[0][1]);
							  String filePath = context.getRealPath("/")+"pages/Logo/"+session.getAttribute("session_pool")+"/"+filename;
							  nameObj[0][0]=Image.getInstance(filePath);
						 }else
							  nameObj[0][0]="";
					  }else{
						  nameObj[0][0]="";
					  }
				  }catch (Exception e) {
					  logger.info("Exception --------------------error in getting image "+e);
				  }
				try {
					nameObj[0][1] = ""+divDetails[0][1];
				} catch (Exception e1) {
					logger.info("Exception --------------------error in getting image "+e1);
					nameObj[0][0] = "";
					nameObj[0][1] = ""+divDetails[0][1];
				}
				try{
					TableDataSet tdsName = new TableDataSet();
					tdsName.setData(nameObj);
					tdsName.setCellAlignment(new int[]{0,2});
					tdsName.setCellWidth(new int[]{50,50});
					tdsName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
					tdsName.setBlankRowsBelow(1);
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
				
				String periodQuery = " select TO_CHAR(APPR_CAL_START_DATE,'Mon YYYY')||'-'||TO_CHAR(APPR_CAL_END_DATE,'Mon YYYY') from PAS_APPR_CALENDAR where APPR_ID = "+apprId;
				Object [][] resultObj = getSqlModel().getSingleResult(periodQuery);
						
				try {
					Object [][]formTitle=new Object[2][1];
					formTitle[0][0] ="\nAnnual Appraisal Form";
					formTitle[1][0] ="\nPeriod "+checkNull(String.valueOf(resultObj[0][0]));
					TableDataSet reportTitleTable = new TableDataSet();
					reportTitleTable.setData(formTitle);
					reportTitleTable.setCellAlignment(new int[] {1});
					reportTitleTable.setCellWidth(new int[] {100 });
					reportTitleTable.setBlankRowsAbove(2);
					reportTitleTable.setBodyFontDetails(Font.HELVETICA, 18, Font.BOLD, new Color(0,0,0));
					rg.addTableToDoc(reportTitleTable);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				}
				
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
					+ " WHERE PAS_APPR_CALENDAR.APPR_ID = "+apprId+"  AND PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID = "+ empId  ;
				

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
								+" WHERE APPR_ID ="+apprId+" and APPR_APPRAISEE_ID="+empId+" and APPR_IS_SELFPHASE!='Y' ORDER BY APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL ";
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
				
				String phaseQuery ="SELECT APPR_PHASE_ID,APPR_PHASE_NAME,APPR_PHASEWISE_RATING,APPR_PHASE_WEIGHT_AGE,NVL(APPR_IS_SELFPHASE,'N') FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID="+apprId+" AND APPR_PHASE_STATUS='A'"
				+" AND APPR_PHASE_ORDER <= (SELECT APPR_PHASE_ORDER FROM PAS_APPR_PHASE_CONFIG WHERE APPR_PHASE_ID = "+phaseCode+" AND APPR_ID ="+apprId+" )"
				+" ORDER BY APPR_PHASE_ORDER";

				String applicabilityQuery = " SELECT APPR_TRN_FLAG,APPR_AWARD_FLAG,APPR_DISCIPLINARY_FLAG,APPR_CAREER_FLAG " 
				+" FROM PAS_APPR_TEMPLATE " 
				+" WHERE APPR_ID = "+apprId+" AND APPR_TEMPLATE_ID = "+templateCode+" ";

				Object applicability [][] = getSqlModel().getSingleResult(applicabilityQuery);
				Object phaseObject [][]=getSqlModel().getSingleResult(phaseQuery);
				
				for (int i = 0; i < phaseObject.length; i++) {
					
					String levelQuery =" SELECT APPR_APPRAISER_LEVEL,APPR_APPRAISER_CODE,PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID,EMP_FNAME||' '||EMP_MNAME||''||EMP_LNAME FROM PAS_APPR_APPRAISER " 
										+" LEFT JOIN PAS_APPR_APPRAISER_GRP_DTL ON(PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) "
										+" LEFT JOIN HRMS_EMP_OFFC ON(APPR_APPRAISER_CODE=EMP_ID)"
										+" WHERE APPR_PHASE_ID="+phaseObject[i][0]+" AND APPR_ID ="+apprId+" and APPR_APPRAISEE_ID="+empId
										+" AND APPR_APPRAISER_LEVEL <= (SELECT APPR_APPRAISER_LEVEL FROM PAS_APPR_APPRAISER " 
										+" INNER JOIN PAS_APPR_APPRAISER_GRP_DTL ON(PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) "
										+" WHERE APPR_APPRAISER_CODE = "+appraiserCode+" AND APPR_ID = "+apprId+" AND APPR_APPRAISEE_ID= "+empId+" AND APPR_PHASE_ID = "+phaseCode+")"
										+" ORDER BY APPR_APPRAISER_LEVEL";
					
					Object levelObject [][]=getSqlModel().getSingleResult(levelQuery);
					
					String sectionQuery = "SELECT DISTINCT PAS_APPR_QUES_MAPPING.APPR_SECTION_ID, APPR_SECTION_NAME,APPR_SECTION_ORDER,PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID FROM PAS_APPR_QUES_MAPPING "
						+" LEFT JOIN PAS_APPR_SECTION_HDR on(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_QUES_MAPPING.APPR_SECTION_ID)"
						+" LEFT JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID=PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID)"
						+" WHERE APPR_PHASE="+phaseObject[i][0]+" AND APPR_EMP_GRP_EMPID = "+empId+" AND PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID = "+templateCode
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
														+" WHERE APPR_ID = "+apprId+" AND APPR_SECTION_ID = "+sectionObject[k][0]+" AND APPR_PHASE_ID = "+phaseObject[i][0]+" AND PAS_APPR_SECTION_DTL.APPR_TEMPLATE_ID = "+templateCode;
															
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
									+" AND PAS_APPR_QUES_MAPPING.APPR_ID="+apprId+" AND APPR_EMP_ID = "+empId+"AND PAS_APPR_QUES_MAPPING.APPR_SECTION_ID = "+sectionObject[k][0]+" AND APPR_PHASE = "+phaseObject[i][0]+" ) "
									+" WHERE APPR_ID="+apprId+" AND APPR_EMP_ID="+empId+" AND PAS_APPR_COMMENTS.APPR_SECTION_ID ="+sectionObject[k][0]
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
							
								String trnVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+apprId+" "
										+" AND APPR_TEMPLATE_ID = "+templateCode+" "
										+" AND APPR_PHASE ="+phaseObject[i][0]+""
										+" AND APPR_SECTION_TYPE = 'T'" ;
			
								Object trnPhaseVisibility[][] = getSqlModel().getSingleResult(trnVisibility);
			
								String awdVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+apprId+" "
										+" AND APPR_TEMPLATE_ID = "+templateCode+" "
										+" AND APPR_PHASE ="+phaseObject[i][0]+""
										+" AND APPR_SECTION_TYPE = 'A'" ;

								Object awdPhaseVisibility[][] = getSqlModel().getSingleResult(awdVisibility);
				
								String discVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+apprId+" "
									+" AND APPR_TEMPLATE_ID = "+templateCode+" "
									+" AND APPR_PHASE ="+phaseObject[i][0]+""
									+" AND APPR_SECTION_TYPE = 'D'" ;

								Object discPhaseVisibility[][] = getSqlModel().getSingleResult(discVisibility);

								String carVisibility = " SELECT APPR_PHASE_VISIBILITY FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID = "+apprId+" "
									+" AND APPR_TEMPLATE_ID = "+templateCode+" "
									+" AND APPR_PHASE ="+phaseObject[i][0]+""
									+" AND APPR_SECTION_TYPE = 'C'" ;

								Object carPhaseVisibility[][] = getSqlModel().getSingleResult(carVisibility);

								//4. CAREER DATA
								String careerQuery ="SELECT ROWNUM,APPR_QUES_DESC,APPR_CAREER_COMMENT FROM PAS_APPR_CAREER_COMMENT "
									+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_CAREER_COMMENT.APPR_QUESTION_CODE)"
									+" WHERE PAS_APPR_CAREER_COMMENT.APPR_ID="+apprId+" AND PAS_APPR_CAREER_COMMENT.APPR_EMP_ID="+empId
									+" AND APPR_PHASE="+phaseObject[i][0];          
								
								//5. General comment  DATA
								String generalQuery =" SELECT X_POSITION,Y_POSITION,APPR_COMMENTS FROM PAS_EMP_GENERAL_COMMENTS "
									+" WHERE APPR_ID ="+apprId+" AND APPR_TEMPLATE_ID ="+templateCode+" and APPR_PHASE = "+phaseObject[i][0] +" and APPR_EMP_ID = "+empId;      
								
								String headerLabels = " select LABEL_NAME from PAS_GENERAL_COMMENT_DTL where APPR_GENERAL_ID = ? "
											//+" ( SELECT APPR_GENERAL_ID from PAS_GENERAL_COMMENT_HDR where APPR_ID = "+bean.getApprId()+" and APPR_TEMPLATE_ID = "+templateCode+") "				 
											+" order by X_POSITION,Y_POSITION ";
								
								String query1 = " SELECT APPR_COMMENT_FLAG ,APPR_COLUMN_NOS,APPR_GENERAL_ID FROM PAS_GENERAL_COMMENT_HDR "
										+ " WHERE APPR_ID=" + apprId+" and APPR_TEMPLATE_ID = "+templateCode;
								
							if(String.valueOf(phaseObject[i][4]).equals("N")){						// if not self phase
							
							//1. TRAINING DATA
							String trainingQuery ="SELECT ROWNUM,APPR_QUES_DESC,APPR_TRNRECOM_COMMENT FROM PAS_APPR_TRN_RECOMMEND_COMMENT "
								+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_QUESTION_CODE)"
								+" WHERE APPR_ID ="+apprId+" AND APPR_EMP_ID="+empId+" AND APPR_PHASE_ID="+phaseObject[i][0] +" AND APPR_APPRAISER_ID ="+levelObject[j][1];
							
							
							//2. AWARD DATA
							String awardQuery ="SELECT ROWNUM,APPR_AWARD_DESC,APPR_AWARD_COMMENT FROM PAS_APPR_AWD_ACHIEVED_COMMENT "
								+" LEFT JOIN PAS_APPR_AWARD_ACHIEVED ON(PAS_APPR_AWARD_ACHIEVED.APPR_AWARD_CODE=PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_AWARD_CODE)"
								+" WHERE PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_ID ="+apprId+" AND PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_EMP_ID="+empId+" AND APPR_PHASE_ID="+phaseObject[i][0] +" AND APPR_APPRAISER_ID ="+levelObject[j][1];
							
							
							//3. DISCIPLINARY DATA
							String displQuery ="SELECT ROWNUM,APPR_DISCP_ACTION, APPR_DISCP_COMMENTS FROM PAS_APPR_DISCIPLINARY_COMMENT "
								+" LEFT JOIN PAS_APPR_DISCIPLINARY ON (PAS_APPR_DISCIPLINARY.APPR_DISCP_ID=PAS_APPR_DISCIPLINARY_COMMENT.APPR_DISCP_ID)"
								+" WHERE PAS_APPR_DISCIPLINARY_COMMENT.APPR_ID="+apprId+" AND PAS_APPR_DISCIPLINARY_COMMENT.APPR_EMP_ID="+empId
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
											+" WHERE APPR_ID ="+apprId+" and APPR_TEMPLATE_ID ="+templateCode+" and APPR_EMP_ID = "+empId;
						
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
											+" WHERE APPR_ID ="+apprId+" and APPR_TEMPLATE_ID ="+templateCode+" and APPR_EMP_ID = "+empId;
					
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
											+" WHERE APPR_ID ="+apprId+" and APPR_TEMPLATE_ID ="+templateCode+" and APPR_EMP_ID = "+empId;


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
											
											generalQuery +=" and APPR_APPRAISER_CODE ="+empId+" order by X_POSITION,Y_POSITION ";
											
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
					rg.addProperty(rg.PAGE_BREAK);
				}
										
				rg.process();
				rg.createReport(response);
	}

	public void getEligibleRecords(EvaluatorPanel bean,
			HttpServletRequest request) {
		Object[][] repData2 = getSqlModel().getSingleResult(getQuery(4),new Object[]{bean.getUserEmpId()});
		if(repData2!=null && repData2.length!=0){
			ArrayList<Object> obj = new ArrayList<Object>();
		for (int i = 0; i < repData2.length; i++) {
			Object[][] repData1 = getSqlModel().getSingleResult(getQuery(5),new Object[]{
				String.valueOf(repData2[i][0]),String.valueOf(repData2[i][1]),String.valueOf(repData2[i][2]),String.valueOf(repData2[i][3])
			});
			if(repData1!=null && repData1.length!=0){
				for(int j=0;j<repData1.length;j++){
					EvaluatorPanel bean1 = new EvaluatorPanel();
					bean1.setApprId(checkNull(String.valueOf(repData1[j][0])));
					bean1.setEmpId(checkNull(String.valueOf(repData1[j][1])));
					bean1.setEmpName(checkNull(String.valueOf(repData1[j][2])));
					bean1.setEmpDeptName(checkNull(String.valueOf(repData1[j][3])));
					bean1.setEmpDesgName(checkNull(String.valueOf(repData1[j][4])));
					bean1.setApprCode(checkNull(String.valueOf(repData1[j][5])));
					obj.add(bean1);
				}//End of for
			}//End of if
		}//end of for loop
		
		bean.setEligibleList(obj);
		bean.setNoData1("false");
		}else{
			bean.setNoData1("true");
		}
	}
}
