package org.paradyne.model.PAS;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.AppraisalMisReport;
import org.paradyne.bean.payroll.SalarySlipMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.ReportGenerator;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;

public class AppraisalMisReportModel extends ModelBase {
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AppraisalMisReportModel.class); 

	HashMap<String,Object[][]> appraiserMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> commentMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> selfTrainingMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> trainingRecommondMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> appraiserTrainingMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> awdAchievedMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> awdDetailMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> awdNominateMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> selfDiscpMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> appraiserDiscpMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> careerpMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> scoreMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> phaseSoreMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> templateMap = new HashMap<String, Object[][]>();
	HashMap<String,Object[][]> phaseWtDisplayMap = new HashMap<String, Object[][]>();
	HashMap<String, Object[]> visibilityMap = new HashMap<String, Object[]>();
	HashMap<String, Object[][]> map = new HashMap<String, Object[][]>();
	HashMap<String, Object[][]> generalMap = new HashMap<String, Object[][]>();
	HashMap<String, Object[][]> goalMap = new HashMap<String, Object[][]>();
	HashMap<String, Object[][]> goalApproverMap = new HashMap<String, Object[][]>();
	
	/*
	 * This method to be used for adding filter constraints to the Query's.
	 */
	public String getFilter(AppraisalMisReport bean)
	{
		String query="";
		if(!bean.getDivisionId().equals(""))
			query+=" and HRMS_EMP_OFFC.EMP_DIV="+bean.getDivisionId();
		if(!bean.getBranchId().equals(""))
			query+=" and HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
		if(!bean.getDeptId().equals(""))
			query+=" and HRMS_EMP_OFFC.EMP_DEPT="+bean.getDeptId();
		if(!bean.getEmpTypeId().equals(""))
			query+=" and HRMS_EMP_OFFC.EMP_TYPE="+bean.getEmpTypeId();
		if(!bean.getEmpId().equals(""))
			query+=" and HRMS_EMP_OFFC.EMP_Id="+bean.getEmpId();
		
		return query;
	}
	
	/*
	 * This method  will set the totalRecords and record per page value...! this varible
	 *  to be used for Generating DownLoad appraisal report.
	 */
	public void generateUrlList(HttpServletRequest request,
				HttpServletResponse response, AppraisalMisReport bean) {
			try{
				 Object [][] resultObj = getSqlModel().getSingleResult(getQuery(1, bean));	
					if (resultObj != null && resultObj.length >0) {
						request.setAttribute("totalRecords",resultObj.length);
						request.setAttribute("recPerPage",Integer.parseInt(bean.getRecordsPerPage()));
						bean.setRecordFlag("true");
					}else{
						request.setAttribute("totalRecords",0);
					}
				}
				catch (Exception e) {
					logger.error("Error in generateUrlList == "+e);
			}
		}
	
	public void generatePreviewReport(HttpServletRequest request,
			HttpServletResponse response, AppraisalMisReport bean, String[] careerLabels)
	{
		

		try{
			appraiserMap = getSqlModel().getSingleResultMap(getQuery(2, bean),5,2);
			commentMap = getSqlModel().getSingleResultMap(getQuery(3, bean),6,2);
			selfTrainingMap = getSqlModel().getSingleResultMap(getQuery(4, bean),6,2);
			appraiserTrainingMap = getSqlModel().getSingleResultMap(getQuery(14, bean),4,2);
			trainingRecommondMap = getSqlModel().getSingleResultMap(getQuery(5, bean),4,2);
			awdAchievedMap =  getSqlModel().getSingleResultMap(getQuery(6, bean),4,2);
			awdDetailMap =  getSqlModel().getSingleResultMap(getQuery(7, bean),4,2);
			awdNominateMap =  getSqlModel().getSingleResultMap(getQuery(15,bean),4,2);
			selfDiscpMap =  getSqlModel().getSingleResultMap(getQuery(8, bean),4,2);
			appraiserDiscpMap =  getSqlModel().getSingleResultMap(getQuery(9, bean),4,2);
			careerpMap =  getSqlModel().getSingleResultMap(getQuery(10, bean),4,2);
			scoreMap =  getSqlModel().getSingleResultMap(getQuery(12, bean),7,0);
			phaseSoreMap =  getSqlModel().getSingleResultMap(getQuery(13,bean),4,2);
			templateMap = getSqlModel().getSingleResultMap(getQuery(16, bean),0,2);
			phaseWtDisplayMap = getSqlModel().getSingleResultMap(getQuery(18,bean),0,2);
			generalMap = getSqlModel().getSingleResultMap(getQuery(19,bean),3,2);
			map = getSqlModel().getSingleResultMap(getQuery(20,bean),3,2);
			goalMap = getSqlModel().getSingleResultMap(getQuery(21,bean),4,2);
			goalApproverMap = getSqlModel().getSingleResultMap(getQuery(22,bean),1,2);
			
			
			Object [][] visibilityObject = getSqlModel().getSingleResult(getQuery(17,bean));
			if(visibilityObject != null && visibilityObject.length > 0){
				for (int i = 0; i < visibilityObject.length; i++) {
					visibilityMap.put(""+String.valueOf(visibilityObject[i][0])+"_"
										+String.valueOf(visibilityObject[i][1])+"_"
										+String.valueOf(visibilityObject[i][2]),visibilityObject[i]);
				}
			}
			String goalQueryHDR = "SELECT GOAL_ID,GOAL_NAME FROM HRMS_GOAL_CONFIG WHERE APPRAISAL_CODE = "
				+ bean.getApprId() + "  ORDER BY GOAL_ID ";
			Object[][] goalHdrData = getSqlModel().getSingleResult(goalQueryHDR);
			ReportDataSet rds = new ReportDataSet();
			rds.setReportType("Pdf");
			rds.setFileName("Preview Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
			
			Object [][] empObj = getSqlModel().getSingleResult(getQuery(11, bean));

			if(empObj != null && empObj.length > 0){
				System.out.println("----------1--------------");
				String logoQuery=" SELECT COMPANY_CODE,COMPANY_LOGO FROM HRMS_COMPANY ";
				Object logoObj[][]=getSqlModel().getSingleResult(logoQuery);
				Object[][] nameObj = new Object[1][2];
				try{
					  if(logoObj!=null && logoObj.length>0)
					  	{
						  String filename="";
						  	if(!String.valueOf(logoObj[0][1]).equals(""))
						  	{
						  		filename=String.valueOf(logoObj[0][1]);
						  		String filePath = context.getRealPath("/")+"pages/Logo/"+session.getAttribute("session_pool")+"/"+filename;
						  		nameObj[0][0]=rg.getImage(filePath);
						  	}else
						  nameObj[0][0]="";
					  	}else{
					  		nameObj[0][0]="";
					  	}
				  }catch (Exception e) {
					  logger.info("Exception --------------------error in getting image "+e);
					  nameObj[0][0]="";
					  nameObj[0][1]="";
				  }
				  String divQuery=" SELECT DIV_NAME,DIV_NAME||',\n'||DIV_ADDRESS1||'\n'||DIV_ADDRESS2||'\n'||DIV_ADDRESS3||'\n'||LOCATION_NAME||'-'||DIV_PINCODE FROM HRMS_DIVISION "
						+" left join HRMS_LOCATION on (HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID )"
						+" WHERE DIV_ID = "+bean.getDivisionId();
				
				Object [][]divDetails=getSqlModel().getSingleResult(divQuery);
					if(divDetails!=null && divDetails.length>0)
						nameObj[0][1] = ""+divDetails[0][1];
					try{
						/* TableDataSet tdsName = new TableDataSet();
						 tdsName.setData(nameObj);
						 tdsName.setCellAlignment(new int[]{0,2});
						 tdsName.setCellWidth(new int[]{50,50});
	     				 tdsName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
	     				 tdsName.setBlankRowsBelow(1);
						 rg.createHeader(tdsName);*/
					}catch(Exception e){
						logger.info("error in putting the report header with name and division --"+e);
					}
					
				  int i=0;
				  int noOfRecordsPerPage=0;
				  if (!bean.getRecordsPerPage().equals("")) 	
						noOfRecordsPerPage=Integer.parseInt(bean.getRecordsPerPage());
				  int startRecord=0,endpage=0;
				  /*if(!"".equals(bean.getRangeCode()))
					{
					  System.out.println("----------2--------------");
					 startRecord=Integer.parseInt(bean.getRangeCode())*noOfRecordsPerPage;
					 endpage=startRecord+noOfRecordsPerPage;
					}*/
				  if (endpage>=empObj.length) {
						endpage=empObj.length;
					}
				  i=startRecord;
				  if(!bean.getEmpId().equals(""))
					{
						i=0;
						endpage=1;
					}
				 
				  for (; i < endpage; i++) {
					
					  /* Object [][]reportFirstPage=new Object[1][1];
						reportFirstPage[0][0] ="\n\n\n"+divDetails[0][0];
						TableDataSet reportFirstPageTable = new TableDataSet();
						reportFirstPageTable.setData(reportFirstPage);
						reportFirstPageTable.setCellAlignment(new int[] {1});
						reportFirstPageTable.setCellWidth(new int[] {100 });
						reportFirstPageTable.setBlankRowsAbove(4);
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
						rg.addTableToDoc(reportTitleTable);*/
					   
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
						System.out.println("----------3--------------");
						Object data[][] = new Object[5][4];
						data[0][0] = "Employee Id :";
						data[0][1] = ""+String.valueOf(empObj[i][0]);
						data[0][2] = "Employee Name :";
						data[0][3] = String.valueOf(empObj[i][1]);

						data[1][0] = "Branch :";
						data[1][1] = String.valueOf(empObj[i][4]);
						data[1][2] = "Department :";
						data[1][3] = String.valueOf(empObj[i][3]);
						
						data[2][0] = "Designation :";
						data[2][1] = String.valueOf(empObj[i][2]);
						data[2][2] = "Reporting To :";
						data[2][3] = String.valueOf(empObj[i][9]);
						
						data[3][0] = "Date of Joining :";
						data[3][1] = String.valueOf(empObj[i][5]);
						data[3][2] = "Appraisal Code :";
						data[3][3] = String.valueOf(empObj[i][8]);
						
						data[4][0] = "Appraisal Start Date :";
						data[4][1] = String.valueOf(empObj[i][6]);
						data[4][2] = "Appraisal End Date :";
						data[4][3] = String.valueOf(empObj[i][7]);
						
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
						
						Object appraiserObj[][]= appraiserMap.get(String.valueOf(empObj[i][10]));
						if(appraiserObj != null && appraiserObj.length > 0 ){
							for (int j = 0; j < appraiserObj.length; j++) {
								appraiserObj[j][0]=(j+1);
								/*if(j==0){
									appraiserObj[j][2]=String.valueOf(empObj[i][1]);
									appraiserObj[j][3]=String.valueOf(empObj[i][2]);
								}*/
								System.out.println("----------4--------------");
							}
							TableDataSet appraisertable = new TableDataSet();
							appraisertable.setData(appraiserObj);
							appraisertable.setCellAlignment(new int[]{1,0,0,0,1});
							appraisertable.setCellWidth(new int[]{10,25,30,20,20});
							appraisertable.setHeader(new String[]{"Sr.No.","Phase","Appraiser Name","Designation","Appraiser Level"});
							appraisertable.setBorder(true);
							appraisertable.setHeaderBGColor(new Color(225,225,225));
							appraisertable.setBlankRowsBelow(1);
							rg.addTableToDoc(appraisertable);
						}
						Object commentObj[][]= commentMap.get(String.valueOf(empObj[i][10]));
						Object [][] templateCode = templateMap.get(String.valueOf(empObj[i][10]));
						try {
							if(commentObj != null && commentObj.length > 0 ){
								rg.addProperty(rg.PAGE_BREAK);
								String empId = String.valueOf(commentObj[0][6]);
								String phaseId = String.valueOf(commentObj[0][7]);
								String apprLevel = String.valueOf(commentObj[0][10]);
								String sectionId = String.valueOf(commentObj[0][11]);
								int count = 0;
								System.out.println("----------5--------------");
								ArrayList dataList = new ArrayList();
								for (int j = 0; j < commentObj.length; j++) {
									if(empId.equals(String.valueOf(commentObj[j][6]))) {
										if(phaseId.equals(String.valueOf(commentObj[j][7]))){
											if(apprLevel.equals(String.valueOf(commentObj[j][10]))){
												if(sectionId.equals(String.valueOf(commentObj[j][11]))){
													dataList.add(commentObj[j]);
													count++;
												}else if (count < commentObj.length){
													 getData(dataList, rg,String.valueOf(templateCode[0][1]));
													 dataList = new ArrayList();
													 empId = String.valueOf(commentObj[j][6]);
													 phaseId = String.valueOf(commentObj[j][7]);
													 apprLevel = String.valueOf(commentObj[j][10]);
													 sectionId = String.valueOf(commentObj[j][11]);
													 j--;
												}
											}else if (count < commentObj.length){
												getData(dataList, rg,String.valueOf(templateCode[0][1]));
												 dataList = new ArrayList();
												 empId = String.valueOf(commentObj[j][6]);
												 phaseId = String.valueOf(commentObj[j][7]);
												 apprLevel = String.valueOf(commentObj[j][10]);
												 sectionId = String.valueOf(commentObj[j][11]);
												 j--;
											}
										}else if (count < commentObj.length){
											getData(dataList, rg,String.valueOf(templateCode[0][1]));
											 dataList = new ArrayList();
											 empId = String.valueOf(commentObj[j][6]);
											 phaseId = String.valueOf(commentObj[j][7]);
											 apprLevel = String.valueOf(commentObj[j][10]);
											 sectionId = String.valueOf(commentObj[j][11]);
											 j--;
											 System.out.println("----------6--------------");
										}
									}else if (count < commentObj.length){
										getData(dataList, rg,String.valueOf(templateCode[0][1]));
										 dataList = new ArrayList();
										 empId = String.valueOf(commentObj[j][6]);
										 phaseId = String.valueOf(commentObj[j][7]);
										 apprLevel = String.valueOf(commentObj[j][10]);
										 sectionId = String.valueOf(commentObj[j][11]);
										 j--;
									}
								}
								getData(dataList, rg,String.valueOf(templateCode[0][1]));
							}
						} catch (Exception e) {
							logger.info("error in putting the comment data  --"+e);
						}
						
						Object trainingSelfObj[][] = selfTrainingMap.get(String.valueOf(empObj[i][10]));
						if(trainingSelfObj != null && trainingSelfObj.length > 0){
							try {
								Object phaseHeading[][]=new Object[1][3];
								phaseHeading [0][0]="Training Details";
								phaseHeading [0][1]="";
								phaseHeading [0][2]="";
								TableDataSet phaseTitle = new TableDataSet();
								phaseTitle.setData(phaseHeading);
								phaseTitle.setCellAlignment(new int[] {0,0,0 });
								phaseTitle.setCellWidth(new int[] { 30,60,10 });
								phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
								rg.addTableToDoc(phaseTitle);
								
								for (int K = 0; K < trainingSelfObj.length; K++) 
									trainingSelfObj[K][0] =""+(K+1);
								
								Object trnHeading[][]=new Object[1][3];
								trnHeading [0][2]="Section :Training Attended";
								trnHeading [0][0]="";
								trnHeading [0][1]="";
								TableDataSet trainingTitle = new TableDataSet();
								trainingTitle.setData(trnHeading);
								trainingTitle.setCellAlignment(new int[] {0,0,0 });
								trainingTitle.setCellWidth(new int[] { 5,5,90 });
								//trainingTitle.setBlankRowsAbove(1);
								trainingTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								rg.addTableToDoc(trainingTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(trainingSelfObj);
								tdstable.setCellAlignment(new int[]{1,0,1,1,1,0});
								tdstable.setCellWidth(new int[]{8,30,15,15,15,17});
								tdstable.setHeader(new String[]{"Sr.No.","Training Desc","Training From","Training To","Training Duration","Training Sponcer"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								rg.addTableToDoc(tdstable);
								
								Object [][] trainingApprComtObj = appraiserTrainingMap.get(String.valueOf(empObj[i][10]));
								if(trainingApprComtObj != null && trainingApprComtObj.length > 0){
									
									for (int K = 0; K < trainingApprComtObj.length; K++) 
										trainingApprComtObj[K][0] =""+(K+1);
									
									trnHeading = new Object[1][3];
									trnHeading [0][2]="Section :Training Attended Comment";
									trnHeading [0][0]="";
									trnHeading [0][1]="";
									trainingTitle = new TableDataSet();
									trainingTitle.setData(trnHeading);
									trainingTitle.setCellAlignment(new int[] {0,0,0 });
									trainingTitle.setCellWidth(new int[] { 5,5,90 });
									//trainingTitle.setBlankRowsAbove(1);
									trainingTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
									rg.addTableToDoc(trainingTitle);
									
									tdstable = new TableDataSet();
									tdstable.setData(trainingApprComtObj);
									tdstable.setCellAlignment(new int[]{1,0,0,0});
									tdstable.setCellWidth(new int[]{8,20,36,36});
									tdstable.setHeader(new String[]{"Sr.No","Phase","Training Desc","Comments"});
									tdstable.setHeaderBGColor(new Color(225,225,225));
									tdstable.setBorder(true);
									rg.addTableToDoc(tdstable);
								}
							} catch (Exception e) {
								logger.info("error in putting the Training Attended data  --"+e);
							}
						}
						
						Object trainingApprObj[][]= trainingRecommondMap.get(String.valueOf(empObj[i][10]));
						if(trainingApprObj != null && trainingApprObj.length > 0){
							try {
								if(trainingSelfObj == null && trainingSelfObj.length == 0){
									Object phaseHeading[][]=new Object[1][3];
									phaseHeading [0][0]="Training Details";
									phaseHeading [0][1]="";
									phaseHeading [0][2]="";
									TableDataSet phaseTitle = new TableDataSet();
									phaseTitle.setData(phaseHeading);
									phaseTitle.setCellAlignment(new int[] {0,0,0 });
									phaseTitle.setCellWidth(new int[] { 30,60,10 });
									phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
									rg.addTableToDoc(phaseTitle);
								}
								for (int K = 0; K < trainingApprObj.length; K++) 
									trainingApprObj[K][0] =""+(K+1);
								
								Object trnHeading[][]=new Object[1][3];
								trnHeading [0][2]="Section :Training Recommendations";
								trnHeading [0][0]="";
								trnHeading [0][1]="";
								TableDataSet trainingTitle = new TableDataSet();
								trainingTitle.setData(trnHeading);
								trainingTitle.setCellAlignment(new int[] {0,0,0 });
								trainingTitle.setCellWidth(new int[] { 5,5,90 });
								trainingTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								//trainingTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(trainingTitle);
									
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(trainingApprObj);
								tdstable.setCellAlignment(new int[]{1,0,0,0});
								tdstable.setCellWidth(new int[]{8,20,36,36});
								tdstable.setHeader(new String[]{"Sr.No","Phase","Question Description","Comments"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								rg.addTableToDoc(tdstable);
							} catch (Exception e) {
								logger.info("error in putting the Training Details data  --"+e);
							}
						}
						
						Object awdAchievedObj[][]= awdAchievedMap.get(String.valueOf(empObj[i][10]));
						if(awdAchievedObj != null && awdAchievedObj.length > 0){
							try {
								Object phaseHeading[][]=new Object[1][3];
								phaseHeading [0][0]="Awards & Recognition";
								phaseHeading [0][1]="";
								phaseHeading [0][2]="";
								TableDataSet phaseTitle = new TableDataSet();
								phaseTitle.setData(phaseHeading);
								phaseTitle.setCellAlignment(new int[] {0,0,0 });
								phaseTitle.setCellWidth(new int[] { 30,60,10 });
								phaseTitle.setBlankRowsAbove(2);
								phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
								rg.addTableToDoc(phaseTitle);
								
								for (int K = 0; K < awdAchievedObj.length; K++) 
									awdAchievedObj[K][0] =""+(K+1);
								
								Object awardsHeading[][]=new Object[1][3];
								awardsHeading [0][2]="Section :Awards Achieved";
								awardsHeading [0][0]="";
								awardsHeading [0][1]="";
								TableDataSet awardsTitle = new TableDataSet();
								awardsTitle.setData(awardsHeading);
								awardsTitle.setCellAlignment(new int[] {0,0,0 });
								awardsTitle.setCellWidth(new int[] { 5,5,90 });
								awardsTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								//awardsTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(awardsTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(awdAchievedObj);
								tdstable.setCellWidth(new int []{8,50,15,27});
								tdstable.setCellAlignment(new int []{1,0,1,0});
								tdstable.setHeader(new String[]{"Sr.No.","Award Description","Award Date","Issuing Authority"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								rg.addTableToDoc(tdstable);
								
								Object awdAchievedComtObj[][]= awdDetailMap.get(String.valueOf(empObj[i][10]));
								if(awdAchievedComtObj != null && awdAchievedComtObj.length > 0){
									try {
										for (int K = 0; K < awdAchievedComtObj.length; K++) 
											awdAchievedComtObj[K][0] =""+(K+1);
										
										awardsHeading=new Object[1][3];
										awardsHeading [0][2]="Section :Awards Achieved Comment";
										awardsHeading [0][0]="";
										awardsHeading [0][1]="";
										awardsTitle = new TableDataSet();
										awardsTitle.setData(awardsHeading);
										awardsTitle.setCellAlignment(new int[] {0,0,0 });
										awardsTitle.setCellWidth(new int[] { 5,5,90 });
										awardsTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
										//awardsTitle.setBlankRowsAbove(1);
										rg.addTableToDoc(awardsTitle);
										
										tdstable = new TableDataSet();
										tdstable.setData(awdAchievedComtObj);
										tdstable.setCellAlignment(new int[]{1,0,0,0});
										tdstable.setCellWidth(new int[]{8,20,36,36});
										tdstable.setHeader(new String[]{"Sr.No","Phase","Award Description","Comments"});
										tdstable.setHeaderBGColor(new Color(225,225,225));
										tdstable.setBorder(true);
										//tdstable.setBlankRowsBelow(2);
										rg.addTableToDoc(tdstable);
									} catch (Exception e) {
										logger.info("error in putting the Awards Achieved Comment data  --"+e);
									}
								}
							} catch (Exception e) {
								logger.info("error in putting the Awards Achieved data  --"+e);
							}
						}
						
						Object [][] awdNominateObj =awdNominateMap.get(String.valueOf(empObj[i][10]));
						if(awdNominateObj != null && awdNominateObj.length > 0){
							try {
								if(awdAchievedObj == null && awdAchievedObj.length == 0){
									Object phaseHeading[][]=new Object[1][3];
									phaseHeading [0][0]="Awards & Recognition";
									phaseHeading [0][1]="";
									phaseHeading [0][2]="";
									TableDataSet phaseTitle = new TableDataSet();
									phaseTitle.setData(phaseHeading);
									phaseTitle.setCellAlignment(new int[] {0,0,0 });
									phaseTitle.setCellWidth(new int[] { 30,60,10 });
									phaseTitle.setBlankRowsAbove(2);
									phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
									rg.addTableToDoc(phaseTitle);
								}
								for (int K = 0; K < awdNominateObj.length; K++) 
									awdNominateObj[K][0] =""+(K+1);
								
								Object awardsHeading[][]=new Object[1][3];
								awardsHeading [0][2]="Section :Nominate Awards";
								awardsHeading [0][0]="";
								awardsHeading [0][1]="";
								TableDataSet awardsTitle = new TableDataSet();
								awardsTitle.setData(awardsHeading);
								awardsTitle.setCellAlignment(new int[] {0,0,0 });
								awardsTitle.setCellWidth(new int[] { 5,5,90 });
								awardsTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								//awardsTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(awardsTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(awdNominateObj);
								tdstable.setCellAlignment(new int[]{1,0,0,0});
								tdstable.setCellWidth(new int[]{8,20,36,36});
								tdstable.setHeader(new String[]{"Sr.No","Phase","Award Name","Reason For Award"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								//tdstable.setBlankRowsBelow(2);
								rg.addTableToDoc(tdstable);
							} catch (Exception e) {
								logger.info("error in putting the Nominate Awards data  --"+e);
							}
						}
						
						Object selfDiscpObj[][]= selfDiscpMap.get(String.valueOf(empObj[i][10]));
						if(selfDiscpObj != null && selfDiscpObj.length > 0){
							
							try {
								Object phaseHeading[][]=new Object[1][3];
								phaseHeading [0][0]="Disciplinary Action Details";
								phaseHeading [0][1]="";
								phaseHeading [0][2]="";
								TableDataSet phaseTitle = new TableDataSet();
								phaseTitle.setData(phaseHeading);
								phaseTitle.setCellAlignment(new int[] {0,0,0 });
								phaseTitle.setCellWidth(new int[] { 30,60,10 });
								phaseTitle.setBlankRowsAbove(2);
								phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
								rg.addTableToDoc(phaseTitle);
								
								for (int K = 0; K < selfDiscpObj.length; K++) 
									selfDiscpObj[K][0] =""+(K+1);
								
								Object dispHeading[][]=new Object[1][3];
								dispHeading [0][2]="Section :Disciplinary Action History";
								dispHeading [0][0]="";
								dispHeading [0][1]="";
								TableDataSet dispActionTitle = new TableDataSet();
								dispActionTitle.setData(dispHeading);
								dispActionTitle.setCellAlignment(new int[] {0,0,0 });
								dispActionTitle.setCellWidth(new int[] { 5,5,90 });
								dispActionTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								//dispActionTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(dispActionTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(selfDiscpObj);
								tdstable.setCellAlignment(new int []{1,0,1,0});
								tdstable.setCellWidth(new int []{8,50,15,27});
								tdstable.setHeader(new String []{"Sr.No.","Disciplinary Action","Action Taken Date","Issuing Authority"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								//tdstable.setBlankRowsBelow(1);
								rg.addTableToDoc(tdstable);
							} catch (Exception e) {
								logger.info("error in putting the Disciplinary Action History data  --"+e);
							}
						}
						
						Object appraiserDiscpObj[][]= appraiserDiscpMap.get(String.valueOf(empObj[i][10]));
						if(appraiserDiscpObj != null && appraiserDiscpObj.length > 0 ){
							
							try {
								if(selfDiscpObj == null && selfDiscpObj.length == 0){
									Object phaseHeading[][]=new Object[1][3];
									phaseHeading [0][0]="Disciplinary Action Details";
									phaseHeading [0][1]="";
									phaseHeading [0][2]="";
									TableDataSet phaseTitle = new TableDataSet();
									phaseTitle.setData(phaseHeading);
									phaseTitle.setCellAlignment(new int[] {0,0,0 });
									phaseTitle.setCellWidth(new int[] { 30,60,10 });
									phaseTitle.setBlankRowsAbove(2);
									phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
									rg.addTableToDoc(phaseTitle);
								}
								for (int K = 0; K < appraiserDiscpObj.length; K++) 
									appraiserDiscpObj[K][0] =""+(K+1);
								
								Object dispHeading[][]=new Object[1][3];
								dispHeading [0][2]="Section :Disciplinary Action Comment";
								dispHeading [0][0]="";
								dispHeading [0][1]="";
								TableDataSet dispActionTitle = new TableDataSet();
								dispActionTitle.setData(dispHeading);
								dispActionTitle.setCellAlignment(new int[] {0,0,0 });
								dispActionTitle.setCellWidth(new int[] { 5,5,90 });
								dispActionTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								//dispActionTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(dispActionTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(appraiserDiscpObj);
								tdstable.setCellAlignment(new int[]{1,0,0,0});
								tdstable.setCellWidth(new int[]{8,20,36,36});
								tdstable.setHeader(new String[]{"Sr.No","Phase","Disciplinary Action","Comments"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								//tdstable.setBlankRowsBelow(1);
								rg.addTableToDoc(tdstable);
							} catch (Exception e) {
								logger.info("error in putting the Disciplinary Action Comment data  --"+e);
							}
						}
						
						Object careerObj[][]= careerpMap.get(String.valueOf(empObj[i][10]));
						if(careerObj != null && careerObj.length > 0 ){
							
							try {
								for (int K = 0; K < careerObj.length; K++) 
									careerObj[K][0] =""+(K+1);
								Object phaseHeading[][]=new Object[1][3];
								phaseHeading [0][0]=careerLabels[0];
								phaseHeading [0][1]="";
								phaseHeading [0][2]="";
								TableDataSet phaseTitle = new TableDataSet();
								phaseTitle.setData(phaseHeading);
								phaseTitle.setCellAlignment(new int[] {0,0,0 });
								phaseTitle.setCellWidth(new int[] { 30,60,10 });
								phaseTitle.setBlankRowsAbove(2);
								phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
								rg.addTableToDoc(phaseTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(careerObj);
								tdstable.setCellAlignment(new int[]{1,0,0,0});
								tdstable.setCellWidth(new int[]{8,20,36,36});
								tdstable.setHeader(new String[]{"Sr.No","Phase","Question Description","Comments"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								rg.addTableToDoc(tdstable);
							} catch (Exception e) {
								logger.info("error in putting the Career Progression Details data  --"+e);
							}
						}
						
						if(templateCode != null && templateCode.length > 0){
							Object [][] cmtVisibility = map.get(String.valueOf(templateCode[0][1]));
							if(cmtVisibility != null && cmtVisibility.length > 0){
								if(String.valueOf(cmtVisibility[0][1]).equals("Y")){
									Object [][] generalObj = generalMap.get(String.valueOf(empObj[i][10]));
										if(generalObj != null && generalObj.length > 0){
											int count=0;
											int cnt=0;
											String []generalColums=new String[2+cmtVisibility.length];
											int []generalColumsWidth=new int[2+cmtVisibility.length];
											int []generalAlign=new int[2+cmtVisibility.length];
											generalColums[0]="Sr.No.";
											generalColums[1]="Phase Name";
											generalColumsWidth[0]=10;
											generalColumsWidth[1]=15;
											generalAlign[0]=1;
											generalAlign[1]=0;
											for (int j = 0; j < cmtVisibility.length; j++) {
												generalColums[j+2]=String.valueOf(cmtVisibility[j][0]);
												generalColumsWidth[j+2]=20;
												generalAlign[j+2]=0;
											}
											Object[][]generalData=null;
											try{
												
												int TOTAL_COMMENTS=cmtVisibility.length;		
												generalData=new Object[generalObj.length/TOTAL_COMMENTS][2+TOTAL_COMMENTS];
												for (int x = 0; x < generalData.length; x++) {
													generalData[x][0]=""+(x+1);
													generalData[x][1]=generalObj[count][1];
													for (int j = 0; j < TOTAL_COMMENTS; j++) {
														generalData[x][2+j]=generalObj[cnt][2];
														cnt++;
													}																		
													count+=TOTAL_COMMENTS;	
												}
												
											}catch(Exception e){
												logger.info("Error in putting general comment in career progression --"+e);
												
											}
											
											/*
											 * Put Object in report
											 */
											Object generalHeading[][]=new Object[1][3];
											generalHeading [0][0]=careerLabels[1];
											generalHeading [0][1]="";
											generalHeading [0][2]="";
											TableDataSet generalTitle = new TableDataSet();
											generalTitle.setData(generalHeading);
											generalTitle.setCellAlignment(new int[] { 0,1,0 });
											generalTitle.setCellWidth(new int[] { 30,60,10 });
											generalTitle.setBlankRowsAbove(2);
											generalTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
											rg.addTableToDoc(generalTitle);
											
											TableDataSet generalTable = new TableDataSet();
											generalTable.setData(generalData);
											generalTable.setCellAlignment(generalAlign);
											generalTable.setCellWidth(generalColumsWidth);
											generalTable.setHeader(generalColums);
											generalTable.setHeaderBGColor(new Color(225,225,225));
											generalTable.setBorder(true);
											rg.addTableToDoc(generalTable);
										}
									}
							  }
						}
						if(!"EvaluationView".equals(bean.getReportType()))
						{
							double FINAL_WGT=0.0;
							Object [][]goalObj=null;
							Object[][]goalMapObj = getSqlModel().getSingleResult("select * from PAS_APPR_GOAL_COMP_MAP where APPR_CODE="+bean.getApprId()+" and APPR_MAP_TYPE = 'G'");
							Object[][]compMapObj = getSqlModel().getSingleResult("select * from PAS_APPR_GOAL_COMP_MAP where APPR_CODE="+bean.getApprId()+" and APPR_MAP_TYPE = 'C'");
							try {
								/*
								 * Goal Setting Section
								 * 
								 */
								
								if(goalMapObj!=null && goalMapObj.length>0)
								{
									if(goalHdrData !=null && goalHdrData.length>0)
										for (int p = 0; p < goalHdrData.length; p++) {
											rg = getEmpGoalSection(bean, rg, String
													.valueOf(empObj[i][10]), String
													.valueOf(goalHdrData[p][0]), String
													.valueOf(goalHdrData[p][1]));
											
											String qoalQuery="SELECT NVL(GOAL_RATING_WEIGHTAGE,0) AS GOAL_WT, NVL(GOAL_SELF_WEIGHTAGE,0) AS SELF_WGT, NVL(GOAL_MANAGER_WEIGHTAGE,0) AS MGR_WT, MULTIPLE_RATING_OPTION AS AVG_RT, NVL(APPRAISAL_RATING_WEIGHTAGE,0) AS APPR_WGT  ,NVL(GOAL_REVIEW_SELF_RATING,0), NVL(GOAL_REVIEW_MGR_RATING,0), NVL(GOAL_REVIEW_FINAL_RATING,0),HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_CONFIG.GOAL_ID, "+
															"HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID,TO_CHAR(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE,'DD-MM-YYYY')AS ASSESSMENT_DATE,NVL(GOAL_ASSESSMENT_WEIGTAGE,0) "+	
															 "FROM HRMS_GOAL_CONFIG  	INNER JOIN HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_HDR.GOAL_ID=HRMS_GOAL_CONFIG.GOAL_ID )	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) "+ 
															"LEFT JOIN HRMS_GOAL_ASSESSMENT_CONFIG ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE= HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_DATE and HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ID="+goalHdrData[p][0]+" ) "+ 	
															"WHERE HRMS_GOAL_CONFIG.APPRAISAL_CODE="+bean.getApprId()+"  AND HRMS_GOAL_EMP_HDR.EMP_GOAL_APPROVAL_STATUS=3 AND HRMS_EMP_OFFC.EMP_ID="+String.valueOf(empObj[i][10])+" AND HRMS_GOAL_CONFIG.GOAL_ID = "+goalHdrData[p][0];
												//double FINAL_WGT=0.0;
												goalObj = getSqlModel().getSingleResult(qoalQuery);
												String goalHdrCode="0";
												String goalId="0";
											if(goalObj!=null && goalObj.length>0){

											for (int m = 0; m < goalObj.length; m++) {
												goalHdrCode=String.valueOf(goalObj[m][8]);
												goalId=String.valueOf(goalObj[m][9]);
												/*String assessmentIdQuery="SELECT  HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID,TO_CHAR(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE,'DD-MM-YYYY')AS ASSESSMENT_DATE,NVL(GOAL_ASSESSMENT_WEIGTAGE,0) FROM HRMS_GOAL_EMP_ASSESSMENT_HDR "+ 
																		  "LEFT JOIN HRMS_GOAL_ASSESSMENT_CONFIG ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE= HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_DATE and HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ID="+goalId+" ) "+ 
																		  "WHERE HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID="+goalHdrCode;
												Object[][]assessmentIdObj=getSqlModel().getSingleResult(assessmentIdQuery);
												if(assessmentIdObj !=null && assessmentIdObj.length>0){
													
													
													for(int n=0;n<assessmentIdObj.length;n++)
													{*/
														Object [][]goalHeading=new Object[1][1];
														goalHeading [0][0]="Employee Goal Assessment :";
														TableDataSet tdsGoalScoreHeading = new TableDataSet();
														tdsGoalScoreHeading.setData(goalHeading);
														tdsGoalScoreHeading.setCellAlignment(new int[] {0});
														tdsGoalScoreHeading.setCellWidth(new int[] {100 });
														tdsGoalScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
														tdsGoalScoreHeading.setBlankRowsAbove(1);
														rg.addTableToDoc(tdsGoalScoreHeading);
														
														String empSelfGoalQuery="SELECT SELF_GOAL_DTL_ID,NVL(GOAL_CATEGORY,' ')AS GOAL_CATEGORY,GOAL_DESCRIPTION,"
																				+ " TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY')AS ACHIEVEMENT_DATE ,NVL(GOAL_COMMENTS,' ') AS  KRA,"
																				+ " NVL(GOAL_WEIGHTAGE,0)AS WEIGHTAGE,SELF_GOAL_RATING AS RATING,NVL(SELF_GOAL_COMMENT,'') AS COMMENTS ,"
																				+ " NVL(GOAL_DTL_STATUS,'A'),GOAL_CATEGORY_CODE,IS_GOAL_ACHIEVED "
																				+ " FROM HRMS_GOAL_SELF_ASSESSMENT_DTL"
																				+ " INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID ="+goalObj[m][10]+" ) "
																				+ " LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) ";
														Object[][]empSelfObj=getSqlModel().getSingleResult(empSelfGoalQuery);
														if(empSelfObj !=null && empSelfObj.length>0){
															Object [][]goalAssessDateHeading=new Object[1][2];
															goalAssessDateHeading [0][0]="Assessment Date: "+goalObj[m][11];
															goalAssessDateHeading [0][1]="Assessment Weightage "+goalObj[m][12];
															TableDataSet tdsGoalAssessDateHeading = new TableDataSet();
															tdsGoalAssessDateHeading.setData(goalAssessDateHeading);
															tdsGoalAssessDateHeading.setCellAlignment(new int[] {0,2});
															tdsGoalAssessDateHeading.setCellWidth(new int[] {50,50 });
															tdsGoalAssessDateHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
															tdsGoalAssessDateHeading.setBlankRowsAbove(1);
															rg.addTableToDoc(tdsGoalAssessDateHeading);
																																																																	
															Object goalObject[][] = new Object[empSelfObj.length][7];										
																for (int j = 0; j < empSelfObj.length; j++) {
																	goalObject[j][0]=(j+1);
																	goalObject[j][1]=empSelfObj[j][1];
																	goalObject[j][2]=empSelfObj[j][2];
																	goalObject[j][3]=empSelfObj[j][3];
																	goalObject[j][4]=empSelfObj[j][4];
																	goalObject[j][5]=empSelfObj[j][5];
																	goalObject[j][6]=empSelfObj[j][6];
																	goalObject[j][7]=empSelfObj[j][7];
																}	
															
															TableDataSet tdsGoalScoreTable = new TableDataSet();
															tdsGoalScoreTable.setData(goalObject);
															tdsGoalScoreTable.setCellAlignment(new int[]{1,0,0,1,0,1,1,0});
															tdsGoalScoreTable.setCellWidth(new int[]{10,15,15,10,10,10,10,20});
															tdsGoalScoreTable.setHeader(new String []{"Sr.No.","Goal Category","Goal Description","Achievement Date","KRA","Weightage","Rating","Comment"});
															tdsGoalScoreTable.setBorder(true);
															tdsGoalScoreTable.setHeaderBGColor(new Color(225,225,225));
															rg.addTableToDoc(tdsGoalScoreTable);
														
															Object [][]mgrHeading=new Object[1][1];
															mgrHeading [0][0]="Manager Goal Assessment :";
															TableDataSet mgrScoreHeading = new TableDataSet();
															mgrScoreHeading.setData(mgrHeading);
															mgrScoreHeading.setCellAlignment(new int[] {0});
															mgrScoreHeading.setCellWidth(new int[] {100 });
															mgrScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
															mgrScoreHeading.setBlankRowsAbove(1);
															rg.addTableToDoc(mgrScoreHeading);
															
															Object [][]goalAssessDateHeading1=new Object[1][2];
															goalAssessDateHeading1 [0][0]="Assessment Date: "+goalObj[m][11];
															goalAssessDateHeading1 [0][1]="Assessment Weightage "+goalObj[m][12];
															TableDataSet tdsGoalAssessDateHeading1 = new TableDataSet();
															tdsGoalAssessDateHeading1.setData(goalAssessDateHeading1);
															tdsGoalAssessDateHeading1.setCellAlignment(new int[] {0,2});
															tdsGoalAssessDateHeading1.setCellWidth(new int[] {50,50 });
															tdsGoalAssessDateHeading1.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
															tdsGoalAssessDateHeading1.setBlankRowsAbove(1);
															rg.addTableToDoc(tdsGoalAssessDateHeading1);
															
														
															String mgrApprQuery="SELECT NVL(GOAL_RATING,''),NVL(GOAL_COMMENT,''),GOAL_DTL_ID,EMP_FNAME||' '||EMP_LNAME FROM HRMS_GOAL_EMP_ASSESSMENT_DTL LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID)" 
																+"	WHERE EMP_GOAL_ASSESSMENT_ID ="+goalObj[m][10]+"	";
														Object[][]mgrObj=getSqlModel().getSingleResult(mgrApprQuery);
														if(mgrObj !=null && mgrObj.length>0){
															Object goalMgrObject[][] = null;
															if(mgrObj.length==empSelfObj.length){								
																goalMgrObject=new Object[empSelfObj.length][8];
																for (int j = 0; j < empSelfObj.length; j++) {
																	goalMgrObject[j][0]=(j+1);//SR NO
																	goalMgrObject[j][1]=empSelfObj[j][1];
																	goalMgrObject[j][2]=empSelfObj[j][2];
																	goalMgrObject[j][3]=empSelfObj[j][3];
																	goalMgrObject[j][4]=mgrObj[j][3];
																	goalMgrObject[j][5]=empSelfObj[j][5];
																	goalMgrObject[j][6]=mgrObj[j][0];
																	goalMgrObject[j][7]=mgrObj[j][1];
																}								
															}
															else{
																int count=-1;
																goalMgrObject=new Object[mgrObj.length][8];
																for (int j = 0; j < mgrObj.length; j++) {
																	if(j%2==0){
																		count++;
																	}		
																	goalMgrObject[j][0]=(j+1);//SR NO
																	goalMgrObject[j][1]=empSelfObj[count][1];
																	goalMgrObject[j][2]=empSelfObj[count][2];
																	goalMgrObject[j][3]=empSelfObj[count][3];
																	goalMgrObject[j][4]=mgrObj[j][3];
																	goalMgrObject[j][5]=empSelfObj[count][5];
																	goalMgrObject[j][6]=mgrObj[j][0];
																	goalMgrObject[j][7]=mgrObj[j][1];
																	
																}								
															
															}
															TableDataSet mgrScoreTable = new TableDataSet();
															mgrScoreTable.setData(goalMgrObject);
															mgrScoreTable.setCellAlignment(new int[]{1,0,0,1,0,1,1,0});
															mgrScoreTable.setCellWidth(new int[]{10,15,15,10,15,8,7,20});
															mgrScoreTable.setHeader(new String []{"Sr.No.","Goal Category","Goal Description","Achievement Date","Manager Name","Weightage","Rating","Comment"});
															mgrScoreTable.setBorder(true);
															mgrScoreTable.setHeaderBGColor(new Color(225,225,225));
															rg.addTableToDoc(mgrScoreTable);
														}else
														{
															Object [][]goalInfo=new Object[1][1];
															goalInfo [0][0]="Goal is not finalize";
															TableDataSet tdsGoalScoreManagerInfo = new TableDataSet();
															tdsGoalScoreManagerInfo.setData(goalInfo);
															tdsGoalScoreManagerInfo.setCellAlignment(new int[] {0});
															tdsGoalScoreManagerInfo.setCellWidth(new int[] {100 });
															tdsGoalScoreManagerInfo.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
															tdsGoalScoreManagerInfo.setBlankRowsAbove(1);
															rg.addTableToDoc(tdsGoalScoreManagerInfo);
														}
														
														
														}
														else
														{
															Object [][]goalInfo=new Object[1][1];
															goalInfo [0][0]="Goal is not finalize";
															TableDataSet tdsGoalScoreManagerInfo = new TableDataSet();
															tdsGoalScoreManagerInfo.setData(goalInfo);
															tdsGoalScoreManagerInfo.setCellAlignment(new int[] {0});
															tdsGoalScoreManagerInfo.setCellWidth(new int[] {100 });
															tdsGoalScoreManagerInfo.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
															tdsGoalScoreManagerInfo.setBlankRowsAbove(1);
															rg.addTableToDoc(tdsGoalScoreManagerInfo);
														}
													//}
												//}
												
											}
											
										}
										}
									
								}
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							//}
							
							
							/*
							 * Goal Setting Ends
							 */
							
							/*
							 * Competency block Start
							 * 
							 * 
							 */
							
							if(compMapObj!=null && compMapObj.length>0)
							{
								String compHedingSql="SELECT APPR_MAP_CODE,NVL(COMP_NAME,'') FROM PAS_APPR_GOAL_COMP_MAP "+
								"INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=PAS_APPR_GOAL_COMP_MAP.APPR_MAP_CODE) "+
								 "WHERE APPR_CODE = "+bean.getApprId()+" AND APPR_MAP_TYPE='C'";
								Object[][]compHedingObj=getSqlModel().getSingleResult(compHedingSql);
								String compId="0";
								if(compHedingObj!=null && compHedingObj.length > 0)
								{
									compId=checkNull(String.valueOf(compHedingObj[0][0]));
									
									
									String compAssessmentIdSql="SELECT  HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID,TO_CHAR(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE,'DD-MM-YYYY')AS ASSESSMENT_DATE,NVL(COMP_ASSESSMENT_WEIGTAGE,0) FROM HRMS_COMP_EMP_ASSESSMENT_HDR "+ 
																"LEFT JOIN HRMS_COMP_ASSESSMENT_CONFIG ON(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID) "+ 
																"WHERE  EMP_ID = "+String.valueOf(empObj[i][10])+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID= "+compId;
									Object[][]compAssessmentIdObj=getSqlModel().getSingleResult(compAssessmentIdSql);
									if(compAssessmentIdObj!=null && compAssessmentIdObj.length > 0){
										
										Object [][]compHeading=new Object[1][1];
										compHeading [0][0]="Competency Name : "  +checkNull(String.valueOf(compHedingObj[0][1])) ;
										TableDataSet comphdrHeading = new TableDataSet();
										comphdrHeading.setData(compHeading);
										comphdrHeading.setCellAlignment(new int[] {0});
										comphdrHeading.setCellWidth(new int[] {100 });
										comphdrHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
										comphdrHeading.setBlankRowsAbove(1);
										rg.addTableToDoc(comphdrHeading);
										
										Object [][]compEmpHdrHeading=new Object[1][1];
										compEmpHdrHeading [0][0]="Employee Competency Assessment :";
										TableDataSet tdsCompScoreHeading = new TableDataSet();
										tdsCompScoreHeading.setData(compEmpHdrHeading);
										tdsCompScoreHeading.setCellAlignment(new int[] {0});
										tdsCompScoreHeading.setCellWidth(new int[] {100 });
										tdsCompScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
										tdsCompScoreHeading.setBlankRowsAbove(1);
										rg.addTableToDoc(tdsCompScoreHeading);
										for(int n=0;n<compAssessmentIdObj.length;n++)
										{
											Object [][]compAssessDateHeading=new Object[1][2];
											compAssessDateHeading [0][0]="Assessment Date: "+compAssessmentIdObj[n][1];
											compAssessDateHeading [0][1]="Assessment Weightage "+compAssessmentIdObj[n][2];
											TableDataSet tdsCompAssessDateHeading = new TableDataSet();
											tdsCompAssessDateHeading.setData(compAssessDateHeading);
											tdsCompAssessDateHeading.setCellAlignment(new int[] {0,2});
											tdsCompAssessDateHeading.setCellWidth(new int[] {50,50 });
											tdsCompAssessDateHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
											tdsCompAssessDateHeading.setBlankRowsAbove(1);
											rg.addTableToDoc(tdsCompAssessDateHeading);
											
											
											String compEmpAssessmentSql="SELECT  DISTINCT  NVL(HRMS_COMPETENCY_HDR.COMPETENCY_TITLE,' '), NVL(COMP_RATING,0),HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL,NVL(COMPETENCY_WEIGHTAGE,0),HRMS_COMPETENCY_ROLE.COMPETENCY_CODE,NVL(COMP_COMMENTS,'') "+ 
																		"FROM HRMS_COMP_SELF_ASSESMENT "+ 
																		"INNER JOIN HRMS_EMP_OFFC ON (HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID=HRMS_EMP_OFFC.EMP_ID) "+ 
																		"INNER JOIN HRMS_COMP_EMP_ASSESSMENT_HDR ON HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID =HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID "+ 
																		"INNER JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMP_SELF_ASSESMENT.COMP_ID = HRMS_COMPETENCY_ROLE.COMPETENCY_CODE AND HRMS_EMP_OFFC.EMP_RANK=HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE) "+
																		"INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE) "+ 
																		"INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID) "+
																		"WHERE  COMP_EMP_ID = "+String.valueOf(empObj[i][10])+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID= "+compId+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID="+compAssessmentIdObj[n][0]+"  ORDER BY HRMS_COMPETENCY_ROLE.COMPETENCY_CODE";
											Object[][]compSelfRatingObj=getSqlModel().getSingleResult(compEmpAssessmentSql);
											Object[][]compEmpRatObj=null;
											if(compSelfRatingObj!=null && compSelfRatingObj.length > 0)
											{
												compEmpRatObj=new Object[compSelfRatingObj.length][6];
												for(int k=0;k<compSelfRatingObj.length;k++)
												{
													compEmpRatObj[k][0]=(k+1);
													compEmpRatObj[k][1]=compSelfRatingObj[k][0];
													compEmpRatObj[k][2]=compSelfRatingObj[k][3];
													compEmpRatObj[k][3]=compSelfRatingObj[k][2];
													compEmpRatObj[k][4]=compSelfRatingObj[k][1];
													compEmpRatObj[k][5]=compSelfRatingObj[k][5];
												}
												TableDataSet empScoreTable = new TableDataSet();
												empScoreTable.setData(compEmpRatObj);
												empScoreTable.setCellAlignment(new int[]{1,0,1,0,1,0});
												empScoreTable.setCellWidth(new int[]{10,20,10,20,10,20});
												empScoreTable.setHeader(new String []{"Sr.No.","Competency Name","Competency Weightage","Proficiency Level ","Rating","Comments"});
												empScoreTable.setBorder(true);
												empScoreTable.setHeaderBGColor(new Color(225,225,225));
												rg.addTableToDoc(empScoreTable);
											}
											
											String mgrCompSql="SELECT  DISTINCT  NVL(HRMS_COMPETENCY_HDR.COMPETENCY_TITLE,' '), NVL(COMP_RATING,0),HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL,NVL(COMPETENCY_WEIGHTAGE,0),HRMS_COMPETENCY_ROLE.COMPETENCY_CODE,e2.EMP_FNAME||' '||e2.EMP_LNAME,NVL(COMP_COMMENT,'') "+ 
																"FROM HRMS_COMP_EMP_ASSESSMENT_DTL "+ 
																"INNER JOIN HRMS_EMP_OFFC e1 ON (HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID=e1.EMP_ID) "+ 
																"inner join hrms_emp_offc e2 on (HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID=e2.EMP_ID) "+
																"INNER JOIN HRMS_COMP_EMP_ASSESSMENT_HDR ON HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID =HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID "+ 
																"INNER JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE = HRMS_COMPETENCY_ROLE.COMPETENCY_CODE AND e1.EMP_RANK=HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE) "+
																"INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE) "+ 
																"INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID) "+
																"WHERE  COMP_EMP_ID = "+String.valueOf(empObj[i][10])+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID= "+compId+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID="+compAssessmentIdObj[n][0]+" ORDER BY HRMS_COMPETENCY_ROLE.COMPETENCY_CODE";
											
											Object[][]compMgrRatingObj=getSqlModel().getSingleResult(mgrCompSql);
											Object[][]compMgrRatObj=null;
											if(compMgrRatingObj!=null && compMgrRatingObj.length > 0)
											{
												Object [][]compMgrHdrHeading=new Object[1][1];
												compMgrHdrHeading [0][0]="Manager Competency Assessment :";
												TableDataSet tdsMgrCompScoreHeading = new TableDataSet();
												tdsMgrCompScoreHeading.setData(compMgrHdrHeading);
												tdsMgrCompScoreHeading.setCellAlignment(new int[] {0});
												tdsMgrCompScoreHeading.setCellWidth(new int[] {100 });
												tdsMgrCompScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
												tdsMgrCompScoreHeading.setBlankRowsAbove(1);
												rg.addTableToDoc(tdsMgrCompScoreHeading);
												
												Object [][]compAssessDateHeading1=new Object[1][2];
												compAssessDateHeading1 [0][0]="Assessment Date: "+compAssessmentIdObj[n][1];
												compAssessDateHeading1[0][1]="Assessment Weightage "+compAssessmentIdObj[n][2];
												TableDataSet tdsCompAssessDateHeading1 = new TableDataSet();
												tdsCompAssessDateHeading1.setData(compAssessDateHeading1);
												tdsCompAssessDateHeading1.setCellAlignment(new int[] {0,2});
												tdsCompAssessDateHeading1.setCellWidth(new int[] {50,50 });
												tdsCompAssessDateHeading1.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
												tdsCompAssessDateHeading1.setBlankRowsAbove(1);
												rg.addTableToDoc(tdsCompAssessDateHeading1);
												
												compMgrRatObj=new Object[compMgrRatingObj.length][7];
												for(int k=0;k<compMgrRatingObj.length;k++)
												{
													compMgrRatObj[k][0]=(k+1);//Sr No 
													compMgrRatObj[k][1]=compMgrRatingObj[k][0];//comp Name
													compMgrRatObj[k][2]=compMgrRatingObj[k][3];//weightage
													compMgrRatObj[k][3]=compMgrRatingObj[k][2];//level
													compMgrRatObj[k][4]=compMgrRatingObj[k][5];//Manager
													compMgrRatObj[k][5]=compMgrRatingObj[k][1];//Rating
													compMgrRatObj[k][6]=compMgrRatingObj[k][6];//Comments
												}
												TableDataSet managerScoreTable = new TableDataSet();
												managerScoreTable.setData(compMgrRatObj);
												managerScoreTable.setCellAlignment(new int[]{1,0,1,0,1,1,0});
												managerScoreTable.setCellWidth(new int[]{10,20,10,20,10,20,20});
												managerScoreTable.setHeader(new String []{"Sr.No.","Competency Name","Competency Weightage","Proficiency Level ","Manager Name","Rating","Comments"});
												managerScoreTable.setBorder(true);
												managerScoreTable.setHeaderBGColor(new Color(225,225,225));
												rg.addTableToDoc(managerScoreTable);
											}
											
										}
										
									}
									
									
								}
							}
						}
						
						
						/**
						 * SET EMPLOYEE GOAL DETAILS
						 */
						
						
						
						
				  
						
						
				} 
				 
			}else{
				Object [][]noData=new Object[1][1];
				noData [0][0]="No Data to display  :";
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(noData);
				noDataTable.setCellAlignment(new int[] {0});
				noDataTable.setCellWidth(new int[] {100 });
				noDataTable.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
				rg.addTableToDoc(noDataTable);
			}
				rg.process();
			 rg.createReport(response);
		}catch(Exception e){
			logger.error("Error in generateReport == "+e);
			e.printStackTrace();
		}
	
	}
	
	public Object[][] getScoreDescription(Object [][] scoredataObj,String finalScore){
		Object [][]finalObj = new Object[1][3];
		try{
			//Object [][] scoreObj =  getSqlModel().getSingleResult(getQuery(25), new Object[]{apprCode});
			
			String value= String.valueOf(Math.round(Double.parseDouble(finalScore)));
			
			if (scoredataObj != null && scoredataObj.length != 0) {
			for(int i=0;i<scoredataObj.length;i++){
						
						finalObj[0][0] = Utility.twoDecimals(String.valueOf(finalScore));
					
						if(Double.parseDouble(String.valueOf(finalObj[0][0])) >= Double.parseDouble(String.valueOf(scoredataObj[i][0])))
						{	// get score value and description for final score						
							finalObj[0][1]=String.valueOf(scoredataObj[i][1]);
							finalObj[0][2]=String.valueOf(scoredataObj[i][2]);
							
							break;
						}
				}
			}
			logger.info("getScoreDescription score--------------"+finalObj[0][0]);
			logger.info("getScoreDescription score value----------"+finalObj[0][1]);
			logger.info("getScoreDescription scoredesc-----------"+finalObj[0][2]);
			
		}catch(Exception e){
			logger.error("error in getScoreDescription --------------"+e);
		}
		return  finalObj;
	}
	
	public void generateReport(HttpServletRequest request,
			HttpServletResponse response, AppraisalMisReport bean, String[] careerLabels){
		Object [][] apprscoreObj=null;
		try{
			appraiserMap = getSqlModel().getSingleResultMap(getQuery(2, bean),5,2);
			commentMap = getSqlModel().getSingleResultMap(getQuery(3, bean),6,2);
			selfTrainingMap = getSqlModel().getSingleResultMap(getQuery(4, bean),6,2);
			appraiserTrainingMap = getSqlModel().getSingleResultMap(getQuery(14, bean),4,2);
			trainingRecommondMap = getSqlModel().getSingleResultMap(getQuery(5, bean),4,2);
			awdAchievedMap =  getSqlModel().getSingleResultMap(getQuery(6, bean),4,2);
			awdDetailMap =  getSqlModel().getSingleResultMap(getQuery(7, bean),4,2);
			awdNominateMap =  getSqlModel().getSingleResultMap(getQuery(15,bean),4,2);
			selfDiscpMap =  getSqlModel().getSingleResultMap(getQuery(8, bean),4,2);
			appraiserDiscpMap =  getSqlModel().getSingleResultMap(getQuery(9, bean),4,2);
			careerpMap =  getSqlModel().getSingleResultMap(getQuery(10, bean),4,2);
			scoreMap =  getSqlModel().getSingleResultMap(getQuery(12, bean),7,0);
			phaseSoreMap =  getSqlModel().getSingleResultMap(getQuery(13,bean),4,2);
			templateMap = getSqlModel().getSingleResultMap(getQuery(16, bean),0,2);
			phaseWtDisplayMap = getSqlModel().getSingleResultMap(getQuery(18,bean),0,2);
			generalMap = getSqlModel().getSingleResultMap(getQuery(19,bean),3,2);
			map = getSqlModel().getSingleResultMap(getQuery(20,bean),3,2);
			goalMap = getSqlModel().getSingleResultMap(getQuery(21,bean),4,2);
			goalApproverMap = getSqlModel().getSingleResultMap(getQuery(22,bean),1,2);
			String sqlRatingScale=" SELECT APPR_SCORE_FROM ,APPR_SCORE_VALUE,APPR_SCORE_DESCRIPTION FROM "
								+" PAS_APPR_OVERALL_RATING "
								+" WHERE APPR_ID = ? ORDER BY APPR_SCORE_FROM DESC ";
			apprscoreObj=getSqlModel().getSingleResult(sqlRatingScale, new Object[]{bean.getApprId()});
			Object [][] visibilityObject = getSqlModel().getSingleResult(getQuery(17,bean));
			if(visibilityObject != null && visibilityObject.length > 0){
				for (int i = 0; i < visibilityObject.length; i++) {
					visibilityMap.put(""+String.valueOf(visibilityObject[i][0])+"_"
										+String.valueOf(visibilityObject[i][1])+"_"
										+String.valueOf(visibilityObject[i][2]),visibilityObject[i]);
				}
			}
			String goalQueryHDR = "SELECT GOAL_ID,GOAL_NAME FROM HRMS_GOAL_CONFIG WHERE APPRAISAL_CODE = "
				+ bean.getApprId() + "  ORDER BY GOAL_ID ";
			Object[][] goalHdrData = getSqlModel().getSingleResult(goalQueryHDR);
			ReportDataSet rds = new ReportDataSet();
			rds.setReportType("Pdf");
			rds.setFileName("Appraisal Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
			
			Object [][] empObj = getSqlModel().getSingleResult(getQuery(11, bean));

			if(empObj != null && empObj.length > 0){
				System.out.println("----------1--------------");
				String logoQuery=" SELECT COMPANY_CODE,COMPANY_LOGO FROM HRMS_COMPANY ";
				Object logoObj[][]=getSqlModel().getSingleResult(logoQuery);
				Object[][] nameObj = new Object[1][2];
				try{
					  if(logoObj!=null && logoObj.length>0)
					  	{
						  String filename="";
						  	if(!String.valueOf(logoObj[0][1]).equals(""))
						  	{
						  		filename=String.valueOf(logoObj[0][1]);
						  		String filePath = context.getRealPath("/")+"pages/Logo/"+session.getAttribute("session_pool")+"/"+filename;
						  		nameObj[0][0]=rg.getImage(filePath);
						  	}else
						  nameObj[0][0]="";
					  	}else{
					  		nameObj[0][0]="";
					  	}
				  }catch (Exception e) {
					  logger.info("Exception --------------------error in getting image "+e);
					  nameObj[0][0]="";
					  nameObj[0][1]="";
				  }
				  String divQuery=" SELECT DIV_NAME,DIV_NAME||',\n'||DIV_ADDRESS1||'\n'||DIV_ADDRESS2||'\n'||DIV_ADDRESS3||'\n'||LOCATION_NAME||'-'||DIV_PINCODE FROM HRMS_DIVISION "
						+" left join HRMS_LOCATION on (HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID )"
						+" WHERE DIV_ID = "+bean.getDivisionId();
				
				Object [][]divDetails=getSqlModel().getSingleResult(divQuery);
					if(divDetails!=null && divDetails.length>0)
						nameObj[0][1] = ""+divDetails[0][1];
					try{
						 TableDataSet tdsName = new TableDataSet();
						 tdsName.setData(nameObj);
						 tdsName.setCellAlignment(new int[]{0,2});
						 tdsName.setCellWidth(new int[]{50,50});
	     				 tdsName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
	     				 tdsName.setBlankRowsBelow(1);
						 rg.createHeader(tdsName);
					}catch(Exception e){
						logger.info("error in putting the report header with name and division --"+e);
					}
					
				  int i=0;
				  int noOfRecordsPerPage=0;
				  if (!bean.getRecordsPerPage().equals("")) 	
						noOfRecordsPerPage=Integer.parseInt(bean.getRecordsPerPage());
				  int startRecord=0,endpage=0;
				  if(!bean.getRangeCode().equals(""))
					{
					  System.out.println("----------2--------------");
					 startRecord=Integer.parseInt(bean.getRangeCode())*noOfRecordsPerPage;
					 endpage=startRecord+noOfRecordsPerPage;
					}
				  if (endpage>=empObj.length) {
						endpage=empObj.length;
					}
				  i=startRecord;
				  if(!bean.getEmpId().equals(""))
					{
						i=0;
						endpage=1;
					}
				 
				  for (; i < endpage; i++) {
					
					   Object [][]reportFirstPage=new Object[1][1];
						reportFirstPage[0][0] ="\n\n\n"+divDetails[0][0];
						TableDataSet reportFirstPageTable = new TableDataSet();
						reportFirstPageTable.setData(reportFirstPage);
						reportFirstPageTable.setCellAlignment(new int[] {1});
						reportFirstPageTable.setCellWidth(new int[] {100 });
						reportFirstPageTable.setBlankRowsAbove(4);
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
						System.out.println("----------3--------------");
						Object data[][] = new Object[5][4];
						data[0][0] = "Employee Id :";
						data[0][1] = ""+String.valueOf(empObj[i][0]);
						data[0][2] = "Employee Name :";
						data[0][3] = String.valueOf(empObj[i][1]);

						data[1][0] = "Branch :";
						data[1][1] = String.valueOf(empObj[i][4]);
						data[1][2] = "Department :";
						data[1][3] = String.valueOf(empObj[i][3]);
						
						data[2][0] = "Designation :";
						data[2][1] = String.valueOf(empObj[i][2]);
						data[2][2] = "Reporting To :";
						data[2][3] = String.valueOf(empObj[i][9]);
						
						data[3][0] = "Date of Joining :";
						data[3][1] = String.valueOf(empObj[i][5]);
						data[3][2] = "Appraisal Code :";
						data[3][3] = String.valueOf(empObj[i][8]);
						
						data[4][0] = "Appraisal Start Date :";
						data[4][1] = String.valueOf(empObj[i][6]);
						data[4][2] = "Appraisal End Date :";
						data[4][3] = String.valueOf(empObj[i][7]);
						
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
						
						Object appraiserObj[][]= appraiserMap.get(String.valueOf(empObj[i][10]));
						if(appraiserObj != null && appraiserObj.length > 0 ){
							for (int j = 0; j < appraiserObj.length; j++) {
								appraiserObj[j][0]=(j+1);
								/*if(j==0){
									appraiserObj[j][2]=String.valueOf(empObj[i][1]);
									appraiserObj[j][3]=String.valueOf(empObj[i][2]);
								}*/
								System.out.println("----------4--------------");
							}
							TableDataSet appraisertable = new TableDataSet();
							appraisertable.setData(appraiserObj);
							appraisertable.setCellAlignment(new int[]{1,0,0,0,1});
							appraisertable.setCellWidth(new int[]{10,25,30,20,20});
							appraisertable.setHeader(new String[]{"Sr.No.","Phase","Appraiser Name","Designation","Appraiser Level"});
							appraisertable.setBorder(true);
							appraisertable.setHeaderBGColor(new Color(225,225,225));
							appraisertable.setBlankRowsBelow(1);
							rg.addTableToDoc(appraisertable);
						}
						Object commentObj[][]= commentMap.get(String.valueOf(empObj[i][10]));
						Object [][] templateCode = templateMap.get(String.valueOf(empObj[i][10]));
						try {
							if(commentObj != null && commentObj.length > 0 ){
								rg.addProperty(rg.PAGE_BREAK);
								String empId = String.valueOf(commentObj[0][6]);
								String phaseId = String.valueOf(commentObj[0][7]);
								String apprLevel = String.valueOf(commentObj[0][10]);
								String sectionId = String.valueOf(commentObj[0][11]);
								int count = 0;
								System.out.println("----------5--------------");
								ArrayList dataList = new ArrayList();
								for (int j = 0; j < commentObj.length; j++) {
									if(empId.equals(String.valueOf(commentObj[j][6]))) {
										if(phaseId.equals(String.valueOf(commentObj[j][7]))){
											if(apprLevel.equals(String.valueOf(commentObj[j][10]))){
												if(sectionId.equals(String.valueOf(commentObj[j][11]))){
													dataList.add(commentObj[j]);
													count++;
												}else if (count < commentObj.length){
													 getData(dataList, rg,String.valueOf(templateCode[0][1]));
													 dataList = new ArrayList();
													 empId = String.valueOf(commentObj[j][6]);
													 phaseId = String.valueOf(commentObj[j][7]);
													 apprLevel = String.valueOf(commentObj[j][10]);
													 sectionId = String.valueOf(commentObj[j][11]);
													 j--;
												}
											}else if (count < commentObj.length){
												getData(dataList, rg,String.valueOf(templateCode[0][1]));
												 dataList = new ArrayList();
												 empId = String.valueOf(commentObj[j][6]);
												 phaseId = String.valueOf(commentObj[j][7]);
												 apprLevel = String.valueOf(commentObj[j][10]);
												 sectionId = String.valueOf(commentObj[j][11]);
												 j--;
											}
										}else if (count < commentObj.length){
											getData(dataList, rg,String.valueOf(templateCode[0][1]));
											 dataList = new ArrayList();
											 empId = String.valueOf(commentObj[j][6]);
											 phaseId = String.valueOf(commentObj[j][7]);
											 apprLevel = String.valueOf(commentObj[j][10]);
											 sectionId = String.valueOf(commentObj[j][11]);
											 j--;
											 System.out.println("----------6--------------");
										}
									}else if (count < commentObj.length){
										getData(dataList, rg,String.valueOf(templateCode[0][1]));
										 dataList = new ArrayList();
										 empId = String.valueOf(commentObj[j][6]);
										 phaseId = String.valueOf(commentObj[j][7]);
										 apprLevel = String.valueOf(commentObj[j][10]);
										 sectionId = String.valueOf(commentObj[j][11]);
										 j--;
									}
								}
								getData(dataList, rg,String.valueOf(templateCode[0][1]));
							}
						} catch (Exception e) {
							logger.info("error in putting the comment data  --"+e);
						}
						
						Object trainingSelfObj[][] = selfTrainingMap.get(String.valueOf(empObj[i][10]));
						if(trainingSelfObj != null && trainingSelfObj.length > 0){
							try {
								Object phaseHeading[][]=new Object[1][3];
								phaseHeading [0][0]="Training Details";
								phaseHeading [0][1]="";
								phaseHeading [0][2]="";
								TableDataSet phaseTitle = new TableDataSet();
								phaseTitle.setData(phaseHeading);
								phaseTitle.setCellAlignment(new int[] {0,0,0 });
								phaseTitle.setCellWidth(new int[] { 30,60,10 });
								phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
								rg.addTableToDoc(phaseTitle);
								
								for (int K = 0; K < trainingSelfObj.length; K++) 
									trainingSelfObj[K][0] =""+(K+1);
								
								Object trnHeading[][]=new Object[1][3];
								trnHeading [0][2]="Section :Training Attended";
								trnHeading [0][0]="";
								trnHeading [0][1]="";
								TableDataSet trainingTitle = new TableDataSet();
								trainingTitle.setData(trnHeading);
								trainingTitle.setCellAlignment(new int[] {0,0,0 });
								trainingTitle.setCellWidth(new int[] { 5,5,90 });
								//trainingTitle.setBlankRowsAbove(1);
								trainingTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								rg.addTableToDoc(trainingTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(trainingSelfObj);
								tdstable.setCellAlignment(new int[]{1,0,1,1,1,0});
								tdstable.setCellWidth(new int[]{8,30,15,15,15,17});
								tdstable.setHeader(new String[]{"Sr.No.","Training Desc","Training From","Training To","Training Duration","Training Sponcer"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								rg.addTableToDoc(tdstable);
								
								Object [][] trainingApprComtObj = appraiserTrainingMap.get(String.valueOf(empObj[i][10]));
								if(trainingApprComtObj != null && trainingApprComtObj.length > 0){
									
									for (int K = 0; K < trainingApprComtObj.length; K++) 
										trainingApprComtObj[K][0] =""+(K+1);
									
									trnHeading = new Object[1][3];
									trnHeading [0][2]="Section :Training Attended Comment";
									trnHeading [0][0]="";
									trnHeading [0][1]="";
									trainingTitle = new TableDataSet();
									trainingTitle.setData(trnHeading);
									trainingTitle.setCellAlignment(new int[] {0,0,0 });
									trainingTitle.setCellWidth(new int[] { 5,5,90 });
									//trainingTitle.setBlankRowsAbove(1);
									trainingTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
									rg.addTableToDoc(trainingTitle);
									
									tdstable = new TableDataSet();
									tdstable.setData(trainingApprComtObj);
									tdstable.setCellAlignment(new int[]{1,0,0,0});
									tdstable.setCellWidth(new int[]{8,20,36,36});
									tdstable.setHeader(new String[]{"Sr.No","Phase","Training Desc","Comments"});
									tdstable.setHeaderBGColor(new Color(225,225,225));
									tdstable.setBorder(true);
									rg.addTableToDoc(tdstable);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								logger.info("error in putting the Training Attended data  --"+e);
							}
						}
						
						Object trainingApprObj[][]= trainingRecommondMap.get(String.valueOf(empObj[i][10]));
						if(trainingApprObj != null && trainingApprObj.length > 0){
							try {
								if(trainingSelfObj == null && trainingSelfObj.length == 0){
									Object phaseHeading[][]=new Object[1][3];
									phaseHeading [0][0]="Training Details";
									phaseHeading [0][1]="";
									phaseHeading [0][2]="";
									TableDataSet phaseTitle = new TableDataSet();
									phaseTitle.setData(phaseHeading);
									phaseTitle.setCellAlignment(new int[] {0,0,0 });
									phaseTitle.setCellWidth(new int[] { 30,60,10 });
									phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
									rg.addTableToDoc(phaseTitle);
								}
								for (int K = 0; K < trainingApprObj.length; K++) 
									trainingApprObj[K][0] =""+(K+1);
								
								Object trnHeading[][]=new Object[1][3];
								trnHeading [0][2]="Section :Training Recommendations";
								trnHeading [0][0]="";
								trnHeading [0][1]="";
								TableDataSet trainingTitle = new TableDataSet();
								trainingTitle.setData(trnHeading);
								trainingTitle.setCellAlignment(new int[] {0,0,0 });
								trainingTitle.setCellWidth(new int[] { 5,5,90 });
								trainingTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								//trainingTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(trainingTitle);
									
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(trainingApprObj);
								tdstable.setCellAlignment(new int[]{1,0,0,0});
								tdstable.setCellWidth(new int[]{8,20,36,36});
								tdstable.setHeader(new String[]{"Sr.No","Phase","Question Description","Comments"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								rg.addTableToDoc(tdstable);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								logger.info("error in putting the Training Details data  --"+e);
							}
						}
						
						Object awdAchievedObj[][]= awdAchievedMap.get(String.valueOf(empObj[i][10]));
						if(awdAchievedObj != null && awdAchievedObj.length > 0){
							try {
								Object phaseHeading[][]=new Object[1][3];
								phaseHeading [0][0]="Awards & Recognition";
								phaseHeading [0][1]="";
								phaseHeading [0][2]="";
								TableDataSet phaseTitle = new TableDataSet();
								phaseTitle.setData(phaseHeading);
								phaseTitle.setCellAlignment(new int[] {0,0,0 });
								phaseTitle.setCellWidth(new int[] { 30,60,10 });
								phaseTitle.setBlankRowsAbove(2);
								phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
								rg.addTableToDoc(phaseTitle);
								
								for (int K = 0; K < awdAchievedObj.length; K++) 
									awdAchievedObj[K][0] =""+(K+1);
								
								Object awardsHeading[][]=new Object[1][3];
								awardsHeading [0][2]="Section :Awards Achieved";
								awardsHeading [0][0]="";
								awardsHeading [0][1]="";
								TableDataSet awardsTitle = new TableDataSet();
								awardsTitle.setData(awardsHeading);
								awardsTitle.setCellAlignment(new int[] {0,0,0 });
								awardsTitle.setCellWidth(new int[] { 5,5,90 });
								awardsTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								//awardsTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(awardsTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(awdAchievedObj);
								tdstable.setCellWidth(new int []{8,50,15,27});
								tdstable.setCellAlignment(new int []{1,0,1,0});
								tdstable.setHeader(new String[]{"Sr.No.","Award Description","Award Date","Issuing Authority"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								rg.addTableToDoc(tdstable);
								
								Object awdAchievedComtObj[][]= awdDetailMap.get(String.valueOf(empObj[i][10]));
								if(awdAchievedComtObj != null && awdAchievedComtObj.length > 0){
									try {
										for (int K = 0; K < awdAchievedComtObj.length; K++) 
											awdAchievedComtObj[K][0] =""+(K+1);
										
										awardsHeading=new Object[1][3];
										awardsHeading [0][2]="Section :Awards Achieved Comment";
										awardsHeading [0][0]="";
										awardsHeading [0][1]="";
										awardsTitle = new TableDataSet();
										awardsTitle.setData(awardsHeading);
										awardsTitle.setCellAlignment(new int[] {0,0,0 });
										awardsTitle.setCellWidth(new int[] { 5,5,90 });
										awardsTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
										//awardsTitle.setBlankRowsAbove(1);
										rg.addTableToDoc(awardsTitle);
										
										tdstable = new TableDataSet();
										tdstable.setData(awdAchievedComtObj);
										tdstable.setCellAlignment(new int[]{1,0,0,0});
										tdstable.setCellWidth(new int[]{8,20,36,36});
										tdstable.setHeader(new String[]{"Sr.No","Phase","Award Description","Comments"});
										tdstable.setHeaderBGColor(new Color(225,225,225));
										tdstable.setBorder(true);
										//tdstable.setBlankRowsBelow(2);
										rg.addTableToDoc(tdstable);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										logger.info("error in putting the Awards Achieved Comment data  --"+e);
									}
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								logger.info("error in putting the Awards Achieved data  --"+e);
							}
						}
						
						Object [][] awdNominateObj =awdNominateMap.get(String.valueOf(empObj[i][10]));
						if(awdNominateObj != null && awdNominateObj.length > 0){
							try {
								if(awdAchievedObj == null && awdAchievedObj.length == 0){
									Object phaseHeading[][]=new Object[1][3];
									phaseHeading [0][0]="Awards & Recognition";
									phaseHeading [0][1]="";
									phaseHeading [0][2]="";
									TableDataSet phaseTitle = new TableDataSet();
									phaseTitle.setData(phaseHeading);
									phaseTitle.setCellAlignment(new int[] {0,0,0 });
									phaseTitle.setCellWidth(new int[] { 30,60,10 });
									phaseTitle.setBlankRowsAbove(2);
									phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
									rg.addTableToDoc(phaseTitle);
								}
								for (int K = 0; K < awdNominateObj.length; K++) 
									awdNominateObj[K][0] =""+(K+1);
								
								Object awardsHeading[][]=new Object[1][3];
								awardsHeading [0][2]="Section :Nominate Awards";
								awardsHeading [0][0]="";
								awardsHeading [0][1]="";
								TableDataSet awardsTitle = new TableDataSet();
								awardsTitle.setData(awardsHeading);
								awardsTitle.setCellAlignment(new int[] {0,0,0 });
								awardsTitle.setCellWidth(new int[] { 5,5,90 });
								awardsTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								//awardsTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(awardsTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(awdNominateObj);
								tdstable.setCellAlignment(new int[]{1,0,0,0});
								tdstable.setCellWidth(new int[]{8,20,36,36});
								tdstable.setHeader(new String[]{"Sr.No","Phase","Award Name","Reason For Award"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								//tdstable.setBlankRowsBelow(2);
								rg.addTableToDoc(tdstable);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								logger.info("error in putting the Nominate Awards data  --"+e);
							}
						}
						
						Object selfDiscpObj[][]= selfDiscpMap.get(String.valueOf(empObj[i][10]));
						if(selfDiscpObj != null && selfDiscpObj.length > 0){
							
							try {
								Object phaseHeading[][]=new Object[1][3];
								phaseHeading [0][0]="Disciplinary Action Details";
								phaseHeading [0][1]="";
								phaseHeading [0][2]="";
								TableDataSet phaseTitle = new TableDataSet();
								phaseTitle.setData(phaseHeading);
								phaseTitle.setCellAlignment(new int[] {0,0,0 });
								phaseTitle.setCellWidth(new int[] { 30,60,10 });
								phaseTitle.setBlankRowsAbove(2);
								phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
								rg.addTableToDoc(phaseTitle);
								
								for (int K = 0; K < selfDiscpObj.length; K++) 
									selfDiscpObj[K][0] =""+(K+1);
								
								Object dispHeading[][]=new Object[1][3];
								dispHeading [0][2]="Section :Disciplinary Action History";
								dispHeading [0][0]="";
								dispHeading [0][1]="";
								TableDataSet dispActionTitle = new TableDataSet();
								dispActionTitle.setData(dispHeading);
								dispActionTitle.setCellAlignment(new int[] {0,0,0 });
								dispActionTitle.setCellWidth(new int[] { 5,5,90 });
								dispActionTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								//dispActionTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(dispActionTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(selfDiscpObj);
								tdstable.setCellAlignment(new int []{1,0,1,0});
								tdstable.setCellWidth(new int []{8,50,15,27});
								tdstable.setHeader(new String []{"Sr.No.","Disciplinary Action","Action Taken Date","Issuing Authority"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								//tdstable.setBlankRowsBelow(1);
								rg.addTableToDoc(tdstable);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								logger.info("error in putting the Disciplinary Action History data  --"+e);
							}
						}
						
						Object appraiserDiscpObj[][]= appraiserDiscpMap.get(String.valueOf(empObj[i][10]));
						if(appraiserDiscpObj != null && appraiserDiscpObj.length > 0 ){
							
							try {
								if(selfDiscpObj == null && selfDiscpObj.length == 0){
									Object phaseHeading[][]=new Object[1][3];
									phaseHeading [0][0]="Disciplinary Action Details";
									phaseHeading [0][1]="";
									phaseHeading [0][2]="";
									TableDataSet phaseTitle = new TableDataSet();
									phaseTitle.setData(phaseHeading);
									phaseTitle.setCellAlignment(new int[] {0,0,0 });
									phaseTitle.setCellWidth(new int[] { 30,60,10 });
									phaseTitle.setBlankRowsAbove(2);
									phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
									rg.addTableToDoc(phaseTitle);
								}
								for (int K = 0; K < appraiserDiscpObj.length; K++) 
									appraiserDiscpObj[K][0] =""+(K+1);
								
								Object dispHeading[][]=new Object[1][3];
								dispHeading [0][2]="Section :Disciplinary Action Comment";
								dispHeading [0][0]="";
								dispHeading [0][1]="";
								TableDataSet dispActionTitle = new TableDataSet();
								dispActionTitle.setData(dispHeading);
								dispActionTitle.setCellAlignment(new int[] {0,0,0 });
								dispActionTitle.setCellWidth(new int[] { 5,5,90 });
								dispActionTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
								//dispActionTitle.setBlankRowsAbove(1);
								rg.addTableToDoc(dispActionTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(appraiserDiscpObj);
								tdstable.setCellAlignment(new int[]{1,0,0,0});
								tdstable.setCellWidth(new int[]{8,20,36,36});
								tdstable.setHeader(new String[]{"Sr.No","Phase","Disciplinary Action","Comments"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								//tdstable.setBlankRowsBelow(1);
								rg.addTableToDoc(tdstable);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								logger.info("error in putting the Disciplinary Action Comment data  --"+e);
							}
						}
						
						Object careerObj[][]= careerpMap.get(String.valueOf(empObj[i][10]));
						if(careerObj != null && careerObj.length > 0 ){
							
							try {
								for (int K = 0; K < careerObj.length; K++) 
									careerObj[K][0] =""+(K+1);
								Object phaseHeading[][]=new Object[1][3];
								phaseHeading [0][0]=careerLabels[0];
								phaseHeading [0][1]="";
								phaseHeading [0][2]="";
								TableDataSet phaseTitle = new TableDataSet();
								phaseTitle.setData(phaseHeading);
								phaseTitle.setCellAlignment(new int[] {0,0,0 });
								phaseTitle.setCellWidth(new int[] { 30,60,10 });
								phaseTitle.setBlankRowsAbove(2);
								phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
								rg.addTableToDoc(phaseTitle);
								
								TableDataSet tdstable = new TableDataSet();
								tdstable.setData(careerObj);
								tdstable.setCellAlignment(new int[]{1,0,0,0});
								tdstable.setCellWidth(new int[]{8,20,36,36});
								tdstable.setHeader(new String[]{"Sr.No","Phase","Question Description","Comments"});
								tdstable.setHeaderBGColor(new Color(225,225,225));
								tdstable.setBorder(true);
								rg.addTableToDoc(tdstable);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								logger.info("error in putting the Career Progression Details data  --"+e);
							}
						}
						
						if(templateCode != null && templateCode.length > 0){
							Object [][] cmtVisibility = map.get(String.valueOf(templateCode[0][1]));
							if(cmtVisibility != null && cmtVisibility.length > 0){
								if(String.valueOf(cmtVisibility[0][1]).equals("Y")){
									Object [][] generalObj = generalMap.get(String.valueOf(empObj[i][10]));
										if(generalObj != null && generalObj.length > 0){
											int count=0;
											int cnt=0;
											String []generalColums=new String[2+cmtVisibility.length];
											int []generalColumsWidth=new int[2+cmtVisibility.length];
											int []generalAlign=new int[2+cmtVisibility.length];
											generalColums[0]="Sr.No.";
											generalColums[1]="Phase Name";
											generalColumsWidth[0]=10;
											generalColumsWidth[1]=15;
											generalAlign[0]=1;
											generalAlign[1]=0;
											for (int j = 0; j < cmtVisibility.length; j++) {
												generalColums[j+2]=String.valueOf(cmtVisibility[j][0]);
												generalColumsWidth[j+2]=20;
												generalAlign[j+2]=0;
											}
											Object[][]generalData=null;
											try{
												
												int TOTAL_COMMENTS=cmtVisibility.length;		
												generalData=new Object[generalObj.length/TOTAL_COMMENTS][2+TOTAL_COMMENTS];
												for (int x = 0; x < generalData.length; x++) {
													generalData[x][0]=""+(x+1);
													generalData[x][1]=generalObj[count][1];
													for (int j = 0; j < TOTAL_COMMENTS; j++) {
														generalData[x][2+j]=generalObj[cnt][2];
														cnt++;
													}																		
													count+=TOTAL_COMMENTS;	
												}
												
											}catch(Exception e){
												logger.info("Error in putting general comment in career progression --"+e);
												
											}
											
											/*
											 * Put Object in report
											 */
											Object generalHeading[][]=new Object[1][3];
											generalHeading [0][0]=careerLabels[1];
											generalHeading [0][1]="";
											generalHeading [0][2]="";
											TableDataSet generalTitle = new TableDataSet();
											generalTitle.setData(generalHeading);
											generalTitle.setCellAlignment(new int[] { 0,1,0 });
											generalTitle.setCellWidth(new int[] { 30,60,10 });
											generalTitle.setBlankRowsAbove(2);
											generalTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
											rg.addTableToDoc(generalTitle);
											
											TableDataSet generalTable = new TableDataSet();
											generalTable.setData(generalData);
											generalTable.setCellAlignment(generalAlign);
											generalTable.setCellWidth(generalColumsWidth);
											generalTable.setHeader(generalColums);
											generalTable.setHeaderBGColor(new Color(225,225,225));
											generalTable.setBorder(true);
											rg.addTableToDoc(generalTable);
										}
									}
							  }
						}
						double FINAL_WGT=0.0;
						Object [][]goalObj=null;
						Object[][]goalMapObj = getSqlModel().getSingleResult("select * from PAS_APPR_GOAL_COMP_MAP where APPR_CODE="+bean.getApprId()+" and APPR_MAP_TYPE = 'G'");
						Object[][]compMapObj = getSqlModel().getSingleResult("select * from PAS_APPR_GOAL_COMP_MAP where APPR_CODE="+bean.getApprId()+" and APPR_MAP_TYPE = 'C'");
						try {
							/*
							 * Goal Setting Section
							 * 
							 */
							
							if(goalMapObj!=null && goalMapObj.length>0)
							{
								if(goalHdrData !=null && goalHdrData.length>0)
								{
									for (int p = 0; p < goalHdrData.length; p++) {
										rg = getEmpGoalSection(bean, rg, String
												.valueOf(empObj[i][10]), String
												.valueOf(goalHdrData[p][0]), String
												.valueOf(goalHdrData[p][1]));
										
										String qoalQuery=" SELECT NVL(GOAL_RATING_WEIGHTAGE,0) AS GOAL_WT, NVL(GOAL_SELF_WEIGHTAGE,0) AS SELF_WGT, "
														 + " NVL(GOAL_MANAGER_WEIGHTAGE,0) AS MGR_WT, MULTIPLE_RATING_OPTION AS AVG_RT, "
														 + " NVL(APPRAISAL_RATING_WEIGHTAGE,0) AS APPR_WGT  ,NVL(GOAL_REVIEW_SELF_RATING,0), "
														 + " NVL(GOAL_REVIEW_MGR_RATING,0), NVL(GOAL_REVIEW_FINAL_RATING,0),HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_CONFIG.GOAL_ID, "
														 + " HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID,TO_CHAR(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE,'DD-MM-YYYY')AS ASSESSMENT_DATE,"
														 + " NVL(GOAL_ASSESSMENT_WEIGTAGE,0) "	
														 + " FROM HRMS_GOAL_CONFIG "
														 + " INNER JOIN HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_HDR.GOAL_ID=HRMS_GOAL_CONFIG.GOAL_ID )	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) " 
														 + " LEFT JOIN HRMS_GOAL_ASSESSMENT_CONFIG ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE= HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_DATE and HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ID="+goalHdrData[p][0]+" ) "
														 + " WHERE HRMS_GOAL_CONFIG.APPRAISAL_CODE="+bean.getApprId()+"  AND HRMS_GOAL_EMP_HDR.EMP_GOAL_APPROVAL_STATUS=3 AND HRMS_EMP_OFFC.EMP_ID="+String.valueOf(empObj[i][10])+" AND HRMS_GOAL_CONFIG.GOAL_ID = "+goalHdrData[p][0];
											//double FINAL_WGT=0.0;
											goalObj = getSqlModel().getSingleResult(qoalQuery);
											String goalHdrCode="0";
											String goalId="0";
										if(goalObj!=null && goalObj.length>0){

										for (int m = 0; m < goalObj.length; m++) {
											goalHdrCode=String.valueOf(goalObj[m][9]);
											goalId=String.valueOf(goalObj[m][10]);
											/*String assessmentIdQuery="SELECT  HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID,TO_CHAR(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE,'DD-MM-YYYY')AS ASSESSMENT_DATE,NVL(GOAL_ASSESSMENT_WEIGTAGE,0) FROM HRMS_GOAL_EMP_ASSESSMENT_HDR "+ 
																	  "LEFT JOIN HRMS_GOAL_ASSESSMENT_CONFIG ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE= HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_DATE and HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ID="+goalId+" ) "+ 
																	  "WHERE HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID="+goalHdrCode;
											Object[][]assessmentIdObj=getSqlModel().getSingleResult(assessmentIdQuery);
											if(assessmentIdObj !=null && assessmentIdObj.length>0){
												
												
												for(int n=0;n<assessmentIdObj.length;n++)
												{*/
													Object [][]goalHeading=new Object[1][1];
													goalHeading [0][0]="Employee Goal Assessment :";
													TableDataSet tdsGoalScoreHeading = new TableDataSet();
													tdsGoalScoreHeading.setData(goalHeading);
													tdsGoalScoreHeading.setCellAlignment(new int[] {0});
													tdsGoalScoreHeading.setCellWidth(new int[] {100 });
													tdsGoalScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
													tdsGoalScoreHeading.setBlankRowsAbove(1);
													rg.addTableToDoc(tdsGoalScoreHeading);
													
													String empSelfGoalQuery="SELECT SELF_GOAL_DTL_ID,NVL(GOAL_CATEGORY,' ')AS GOAL_CATEGORY,"
																			+ " GOAL_DESCRIPTION, TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY')AS ACHIEVEMENT_DATE ,"
																			+ " NVL(GOAL_COMMENTS,' ') AS  KRA,NVL(GOAL_WEIGHTAGE,0)AS WEIGHTAGE,SELF_GOAL_RATING AS RATING,"
																			+ " NVL(SELF_GOAL_COMMENT,'') AS COMMENTS , NVL(GOAL_DTL_STATUS,'A'),GOAL_CATEGORY_CODE,IS_GOAL_ACHIEVED "
																			+ " FROM HRMS_GOAL_SELF_ASSESSMENT_DTL"
																			+ " INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID ="+goalObj[m][10]+" ) "
																			+ " LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) ";
													Object[][]empSelfObj=getSqlModel().getSingleResult(empSelfGoalQuery);
													if(empSelfObj !=null && empSelfObj.length>0){
														Object [][]goalAssessDateHeading=new Object[1][2];
														goalAssessDateHeading [0][0]="Assessment Date: "+goalObj[m][11];
														goalAssessDateHeading [0][1]="Assessment Weightage "+goalObj[m][12];
														TableDataSet tdsGoalAssessDateHeading = new TableDataSet();
														tdsGoalAssessDateHeading.setData(goalAssessDateHeading);
														tdsGoalAssessDateHeading.setCellAlignment(new int[] {0,2});
														tdsGoalAssessDateHeading.setCellWidth(new int[] {50,50 });
														tdsGoalAssessDateHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
														tdsGoalAssessDateHeading.setBlankRowsAbove(1);
														rg.addTableToDoc(tdsGoalAssessDateHeading);
														
														
														
														
														
														Object goalObject[][] = new Object[empSelfObj.length][9];										
															for (int j = 0; j < empSelfObj.length; j++) {
																
																System.out
																.println("empSelfObj["+j+"][1]:::"+empSelfObj[j][1]);
																System.out
																.println("empSelfObj["+j+"][2]:::"+empSelfObj[j][2]);
																System.out
																.println("empSelfObj["+j+"][3]:::"+empSelfObj[j][3]);
																System.out
																.println("empSelfObj["+j+"][4]:::"+empSelfObj[j][4]);
																System.out
																.println("empSelfObj["+j+"][5]:::"+empSelfObj[j][5]);
																System.out
																.println("empSelfObj["+j+"][6]:::"+empSelfObj[j][6]);
																
																System.out
																.println("empSelfObj["+j+"][7]:::"+empSelfObj[j][7]);
																System.out
																.println("empSelfObj["+j+"][8]:::"+empSelfObj[j][8]);
																
																
																goalObject[j][0]=(j+1);
																goalObject[j][1]=empSelfObj[j][1];
																goalObject[j][2]=empSelfObj[j][2];
																goalObject[j][3]=empSelfObj[j][3];
																goalObject[j][4]=empSelfObj[j][4];
																goalObject[j][5]=empSelfObj[j][5];
																goalObject[j][6]=empSelfObj[j][6];
																goalObject[j][7]=empSelfObj[j][7];
																goalObject[j][8]= empSelfObj[j][8];
															}	
														
														TableDataSet tdsGoalScoreTable = new TableDataSet();
														tdsGoalScoreTable.setData(goalObject);
														tdsGoalScoreTable.setCellAlignment(new int[]{1,0,0,1,0,1,1,0});
														tdsGoalScoreTable.setCellWidth(new int[]{10,15,15,10,10,10,10,20});
														tdsGoalScoreTable.setHeader(new String []{"Sr.No.","Goal Category","Goal Description","Achievement Date","KRA","Weightage","Rating","Comment"});
														tdsGoalScoreTable.setBorder(true);
														tdsGoalScoreTable.setHeaderBGColor(new Color(225,225,225));
														rg.addTableToDoc(tdsGoalScoreTable);
													
														Object [][]mgrHeading=new Object[1][1];
														mgrHeading [0][0]="Manager Goal Assessment :";
														TableDataSet mgrScoreHeading = new TableDataSet();
														mgrScoreHeading.setData(mgrHeading);
														mgrScoreHeading.setCellAlignment(new int[] {0});
														mgrScoreHeading.setCellWidth(new int[] {100 });
														mgrScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
														mgrScoreHeading.setBlankRowsAbove(1);
														rg.addTableToDoc(mgrScoreHeading);
														
														Object [][]goalAssessDateHeading1=new Object[1][2];
														goalAssessDateHeading1 [0][0]="Assessment Date: "+goalObj[m][11];
														goalAssessDateHeading1 [0][1]="Assessment Weightage "+goalObj[m][12];
														TableDataSet tdsGoalAssessDateHeading1 = new TableDataSet();
														tdsGoalAssessDateHeading1.setData(goalAssessDateHeading1);
														tdsGoalAssessDateHeading1.setCellAlignment(new int[] {0,2});
														tdsGoalAssessDateHeading1.setCellWidth(new int[] {50,50 });
														tdsGoalAssessDateHeading1.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
														tdsGoalAssessDateHeading1.setBlankRowsAbove(1);
														rg.addTableToDoc(tdsGoalAssessDateHeading1);
														
													
														String mgrApprQuery="SELECT NVL(GOAL_RATING,''),NVL(GOAL_COMMENT,''),GOAL_DTL_ID,EMP_FNAME||' '||EMP_LNAME FROM HRMS_GOAL_EMP_ASSESSMENT_DTL LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID)" 
															+"	WHERE EMP_GOAL_ASSESSMENT_ID ="+goalObj[m][10]+"	";
													Object[][]mgrObj=getSqlModel().getSingleResult(mgrApprQuery);
													if(mgrObj !=null && mgrObj.length>0){
														Object goalMgrObject[][] = null;
														if(mgrObj.length==empSelfObj.length){								
															goalMgrObject=new Object[empSelfObj.length][8];
															for (int j = 0; j < empSelfObj.length; j++) {
																goalMgrObject[j][0]=(j+1);//SR NO
																goalMgrObject[j][1]=empSelfObj[j][1];
																goalMgrObject[j][2]=empSelfObj[j][2];
																goalMgrObject[j][3]=empSelfObj[j][3];
																goalMgrObject[j][4]=mgrObj[j][3];
																goalMgrObject[j][5]=empSelfObj[j][5];
																goalMgrObject[j][6]=mgrObj[j][0];
																goalMgrObject[j][7]=mgrObj[j][1];
															}								
														}
														else{
															int count=-1;
															goalMgrObject=new Object[mgrObj.length][8];
															for (int j = 0; j < mgrObj.length; j++) {
																if(j%2==0){
																	count++;
																}		
																goalMgrObject[j][0]=(j+1);
																goalMgrObject[j][1]=empSelfObj[count][1];
																goalMgrObject[j][2]=empSelfObj[count][2];
																goalMgrObject[j][3]=empSelfObj[count][3];
																goalMgrObject[j][4]=mgrObj[j][3];
																goalMgrObject[j][5]=empSelfObj[count][5];
																goalMgrObject[j][6]=mgrObj[j][0];
																goalMgrObject[j][7]=mgrObj[j][1];
																
															}								
														
														}
														TableDataSet mgrScoreTable = new TableDataSet();
														mgrScoreTable.setData(goalMgrObject);
														mgrScoreTable.setCellAlignment(new int[]{1,0,0,1,0,1,1,0});
														mgrScoreTable.setCellWidth(new int[]{10,15,15,10,15,8,7,20});
														mgrScoreTable.setHeader(new String []{"Sr.No.","Goal Category","Goal Description","Achievement Date","Manager Name","Weightage","Rating","Comment"});
														mgrScoreTable.setBorder(true);
														mgrScoreTable.setHeaderBGColor(new Color(225,225,225));
														rg.addTableToDoc(mgrScoreTable);
													}
													
													
													}
												//}
											//}
											
										}
										
									}
									}
								}
									
								
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						/**
						 * SET EMPLOYEE GOAL DETAILS
						 */
						
						
						
						
				  //}
						
						
						/*
						 * Goal Setting Ends
						 */
						
						/*
						 * Competency block Start
						 * 
						 * 
						 */
						
						if(compMapObj!=null && compMapObj.length>0)
						{
							String compHedingSql="SELECT APPR_MAP_CODE,NVL(COMP_NAME,'') FROM PAS_APPR_GOAL_COMP_MAP "+
							"INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=PAS_APPR_GOAL_COMP_MAP.APPR_MAP_CODE) "+
							 "WHERE APPR_CODE = "+bean.getApprId()+" AND APPR_MAP_TYPE='C'";
							Object[][]compHedingObj=getSqlModel().getSingleResult(compHedingSql);
							String compId="0";
							if(compHedingObj!=null && compHedingObj.length > 0)
							{
								compId=checkNull(String.valueOf(compHedingObj[0][0]));
								
								
								String compAssessmentIdSql="SELECT  HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID,TO_CHAR(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_DATE,'DD-MM-YYYY')AS ASSESSMENT_DATE,NVL(COMP_ASSESSMENT_WEIGTAGE,0) FROM HRMS_COMP_EMP_ASSESSMENT_HDR "+ 
															"LEFT JOIN HRMS_COMP_ASSESSMENT_CONFIG ON(HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID) "+ 
															"WHERE  EMP_ID = "+String.valueOf(empObj[i][10])+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID= "+compId;
								Object[][]compAssessmentIdObj=getSqlModel().getSingleResult(compAssessmentIdSql);
								if(compAssessmentIdObj!=null && compAssessmentIdObj.length > 0){
									
									Object [][]compHeading=new Object[1][1];
									compHeading [0][0]="Competency Name : "  +checkNull(String.valueOf(compHedingObj[0][1])) ;
									TableDataSet comphdrHeading = new TableDataSet();
									comphdrHeading.setData(compHeading);
									comphdrHeading.setCellAlignment(new int[] {0});
									comphdrHeading.setCellWidth(new int[] {100 });
									comphdrHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
									comphdrHeading.setBlankRowsAbove(1);
									rg.addTableToDoc(comphdrHeading);
									
									Object [][]compEmpHdrHeading=new Object[1][1];
									compEmpHdrHeading [0][0]="Employee Competency Assessment :";
									TableDataSet tdsCompScoreHeading = new TableDataSet();
									tdsCompScoreHeading.setData(compEmpHdrHeading);
									tdsCompScoreHeading.setCellAlignment(new int[] {0});
									tdsCompScoreHeading.setCellWidth(new int[] {100 });
									tdsCompScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
									tdsCompScoreHeading.setBlankRowsAbove(1);
									rg.addTableToDoc(tdsCompScoreHeading);
									for(int n=0;n<compAssessmentIdObj.length;n++)
									{
										Object [][]compAssessDateHeading=new Object[1][2];
										compAssessDateHeading [0][0]="Assessment Date: "+compAssessmentIdObj[n][1];
										compAssessDateHeading [0][1]="Assessment Weightage "+compAssessmentIdObj[n][2];
										TableDataSet tdsCompAssessDateHeading = new TableDataSet();
										tdsCompAssessDateHeading.setData(compAssessDateHeading);
										tdsCompAssessDateHeading.setCellAlignment(new int[] {0,2});
										tdsCompAssessDateHeading.setCellWidth(new int[] {50,50 });
										tdsCompAssessDateHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
										tdsCompAssessDateHeading.setBlankRowsAbove(1);
										rg.addTableToDoc(tdsCompAssessDateHeading);
										
										
										String compEmpAssessmentSql="SELECT  DISTINCT  NVL(HRMS_COMPETENCY_HDR.COMPETENCY_TITLE,' '), NVL(COMP_RATING,0),HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL,NVL(COMPETENCY_WEIGHTAGE,0),HRMS_COMPETENCY_ROLE.COMPETENCY_CODE,NVL(COMP_COMMENTS,'') "+ 
																	"FROM HRMS_COMP_SELF_ASSESMENT "+ 
																	"INNER JOIN HRMS_EMP_OFFC ON (HRMS_COMP_SELF_ASSESMENT.COMP_EMP_ID=HRMS_EMP_OFFC.EMP_ID) "+ 
																	"INNER JOIN HRMS_COMP_EMP_ASSESSMENT_HDR ON HRMS_COMP_SELF_ASSESMENT.COMP_ASSESMENT_ID =HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID "+ 
																	"INNER JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMP_SELF_ASSESMENT.COMP_ID = HRMS_COMPETENCY_ROLE.COMPETENCY_CODE AND HRMS_EMP_OFFC.EMP_RANK=HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE) "+
																	"INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE) "+ 
																	"INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID) "+
																	"WHERE  COMP_EMP_ID = "+String.valueOf(empObj[i][10])+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID= "+compId+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID="+compAssessmentIdObj[n][0]+"  ORDER BY HRMS_COMPETENCY_ROLE.COMPETENCY_CODE";
										Object[][]compSelfRatingObj=getSqlModel().getSingleResult(compEmpAssessmentSql);
										Object[][]compEmpRatObj=null;
										if(compSelfRatingObj!=null && compSelfRatingObj.length > 0)
										{
											compEmpRatObj=new Object[compSelfRatingObj.length][6];
											for(int k=0;k<compSelfRatingObj.length;k++)
											{
												compEmpRatObj[k][0]=(k+1);
												compEmpRatObj[k][1]=compSelfRatingObj[k][0];
												compEmpRatObj[k][2]=compSelfRatingObj[k][3];
												compEmpRatObj[k][3]=compSelfRatingObj[k][2];
												compEmpRatObj[k][4]=compSelfRatingObj[k][1];
												compEmpRatObj[k][5]=compSelfRatingObj[k][5];
											}
											TableDataSet empScoreTable = new TableDataSet();
											empScoreTable.setData(compEmpRatObj);
											empScoreTable.setCellAlignment(new int[]{1,0,1,0,1,0});
											empScoreTable.setCellWidth(new int[]{10,20,10,10,10,30});
											empScoreTable.setHeader(new String []{"Sr.No.","Competency Name","Competency Weightage","Proficiency Level ","Rating","Comments"});
											empScoreTable.setBorder(true);
											empScoreTable.setHeaderBGColor(new Color(225,225,225));
											rg.addTableToDoc(empScoreTable);
										}
										
										String mgrCompSql="SELECT  DISTINCT  NVL(HRMS_COMPETENCY_HDR.COMPETENCY_TITLE,' '), NVL(COMP_RATING,0),HRMS_COMPETENCY_ROLE.COMPETENCY_LEVEL,NVL(COMPETENCY_WEIGHTAGE,0),HRMS_COMPETENCY_ROLE.COMPETENCY_CODE,e2.EMP_FNAME||' '||e2.EMP_LNAME,NVL(COMP_COMMENT,'') "+ 
															"FROM HRMS_COMP_EMP_ASSESSMENT_DTL "+ 
															"INNER JOIN HRMS_EMP_OFFC e1 ON (HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_EMP_ID=e1.EMP_ID) "+ 
															"inner join hrms_emp_offc e2 on (HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID=e2.EMP_ID) "+
															"INNER JOIN HRMS_COMP_EMP_ASSESSMENT_HDR ON HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESSMENT_ID =HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID "+ 
															"INNER JOIN HRMS_COMPETENCY_ROLE ON (HRMS_COMP_EMP_ASSESSMENT_DTL.COMPETENCY_CODE = HRMS_COMPETENCY_ROLE.COMPETENCY_CODE AND e1.EMP_RANK=HRMS_COMPETENCY_ROLE.COMPETENCY_ROLE) "+
															"INNER JOIN HRMS_COMPETENCY_HDR ON (HRMS_COMPETENCY_HDR.COMPETENCY_CODE=HRMS_COMPETENCY_ROLE.COMPETENCY_CODE) "+ 
															"INNER JOIN HRMS_COMP_CONFIG ON (HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID) "+
															"WHERE  COMP_EMP_ID = "+String.valueOf(empObj[i][10])+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID= "+compId+" AND HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID="+compAssessmentIdObj[n][0]+" ORDER BY HRMS_COMPETENCY_ROLE.COMPETENCY_CODE";
										
										Object[][]compMgrRatingObj=getSqlModel().getSingleResult(mgrCompSql);
										Object[][]compMgrRatObj=null;
										if(compMgrRatingObj!=null && compMgrRatingObj.length > 0)
										{
											Object [][]compMgrHdrHeading=new Object[1][1];
											compMgrHdrHeading [0][0]="Manager Competency Assessment :";
											TableDataSet tdsMgrCompScoreHeading = new TableDataSet();
											tdsMgrCompScoreHeading.setData(compMgrHdrHeading);
											tdsMgrCompScoreHeading.setCellAlignment(new int[] {0});
											tdsMgrCompScoreHeading.setCellWidth(new int[] {100 });
											tdsMgrCompScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
											tdsMgrCompScoreHeading.setBlankRowsAbove(1);
											rg.addTableToDoc(tdsMgrCompScoreHeading);
											
											Object [][]compAssessDateHeading1=new Object[1][2];
											compAssessDateHeading1 [0][0]="Assessment Date: "+compAssessmentIdObj[n][1];
											compAssessDateHeading1[0][1]="Assessment Weightage "+compAssessmentIdObj[n][2];
											TableDataSet tdsCompAssessDateHeading1 = new TableDataSet();
											tdsCompAssessDateHeading1.setData(compAssessDateHeading1);
											tdsCompAssessDateHeading1.setCellAlignment(new int[] {0,2});
											tdsCompAssessDateHeading1.setCellWidth(new int[] {50,50 });
											tdsCompAssessDateHeading1.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
											tdsCompAssessDateHeading1.setBlankRowsAbove(1);
											rg.addTableToDoc(tdsCompAssessDateHeading1);
											
											compMgrRatObj=new Object[compMgrRatingObj.length][7];
											for(int k=0;k<compMgrRatingObj.length;k++)
											{
												compMgrRatObj[k][0]=(k+1);//Sr No 
												compMgrRatObj[k][1]=compMgrRatingObj[k][0];//comp Name
												compMgrRatObj[k][2]=compMgrRatingObj[k][3];//weightage
												compMgrRatObj[k][3]=compMgrRatingObj[k][2];//level
												compMgrRatObj[k][4]=compMgrRatingObj[k][5];//Manager
												compMgrRatObj[k][5]=compMgrRatingObj[k][1];//Rating
												compMgrRatObj[k][6]=compMgrRatingObj[k][6];//Comments
											}
											TableDataSet managerScoreTable = new TableDataSet();
											managerScoreTable.setData(compMgrRatObj);
											managerScoreTable.setCellAlignment(new int[]{1,0,1,0,1,1,0});
											managerScoreTable.setCellWidth(new int[]{5,20,10,8,15,7,30});
											managerScoreTable.setHeader(new String []{"Sr.No.","Competency Name","Competency Weightage","Proficiency Level ","Manager Name","Rating","Comments"});
											managerScoreTable.setBorder(true);
											managerScoreTable.setHeaderBGColor(new Color(225,225,225));
											rg.addTableToDoc(managerScoreTable);
										}
										
									}
									
								}
								
								
							}
						}
						if(isAppraisalScoreShow(bean.getApprId()))
						{
							String finalTotalScore="0";
							Object []finalScoreObject = scoreMap.get(String.valueOf(empObj[i][10]));
							
							if(finalScoreObject !=null && finalScoreObject.length > 0 ){
								
								rg.addProperty(rg.PAGE_BREAK);
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
								
								Object [][]scoreHeading=new Object[1][1];
								scoreHeading [0][0]="Appraisal Score Sheet :";
								TableDataSet tdsScoreHeading = new TableDataSet();
								tdsScoreHeading.setData(scoreHeading);
								tdsScoreHeading.setCellAlignment(new int[] {0});
								tdsScoreHeading.setCellWidth(new int[] {100 });
								tdsScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
								tdsScoreHeading.setBlankRowsAbove(1);
								rg.addTableToDoc(tdsScoreHeading);
							
								Object [][]scoreObj = phaseSoreMap.get(String.valueOf(empObj[i][10]));
								if(scoreObj !=null && scoreObj.length > 0 ){
									for (int K = 0; K < scoreObj.length; K++) 
										scoreObj[K][0] =""+(K+1);
									
									//Object adjusrObject[][] = new Object[3][4];
									Object adjusrObject[][] = new Object[1][4];
									
									adjusrObject [0][0] ="";
									adjusrObject [0][1] = "Total Score";
									adjusrObject [0][2] ="";
									adjusrObject [0][3] =String.valueOf(finalScoreObject[6]);
									finalTotalScore=String.valueOf(finalScoreObject[6]);
									/*adjusrObject [1][0] ="";
									adjusrObject [1][1] = "Adjustment Factor";
									adjusrObject [1][2] ="";
									if(String.valueOf(finalScoreObject[4]).equals("null") || String.valueOf(finalScoreObject[4])== null || String.valueOf(finalScoreObject[4]).equals(" ") )
										adjusrObject [1][3]="";
									else
										adjusrObject [1][3] =checkNull(String.valueOf(finalScoreObject[3]))+" "+checkNull(String.valueOf(finalScoreObject[4]));
									
									adjusrObject [2][0] ="";
									adjusrObject [2][1] = "Final Score";
									adjusrObject [2][2] ="";
									adjusrObject [2][3] =checkNull(String.valueOf(finalScoreObject[0]));*/
									
									scoreObj=Utility.joinArrays(scoreObj, adjusrObject, 1,0);
									
									TableDataSet tdsScoreTable = new TableDataSet();
									tdsScoreTable.setData(scoreObj);
									tdsScoreTable.setCellAlignment(new int[]{1,0,1,1});
									tdsScoreTable.setCellWidth(new int[]{10,50,20,20});
									tdsScoreTable.setHeader(new String []{"Sr.No.","Phase Name","Weightage","Score"});
									tdsScoreTable.setBorder(true);
									tdsScoreTable.setHeaderBGColor(new Color(225,225,225));
									rg.addTableToDoc(tdsScoreTable);
									
									
									if(String.valueOf(finalScoreObject[5]).equals("N")){
										Object [][]finalizeNote=new Object[1][1];
										finalizeNote [0][0]="Note - Score has not been finalized.  ";
										TableDataSet finalizeTable = new TableDataSet();
										finalizeTable.setData(finalizeNote);
										finalizeTable.setCellAlignment(new int[] {2});
										finalizeTable.setCellWidth(new int[] {100 });
										finalizeTable.setBodyFontDetails(Font.HELVETICA, 8, Font.NORMAL, new Color(0,0,0));
										rg.addTableToDoc(finalizeTable);
									}	
									
									/**
									 * ADDED BY SHASHIKANT
									 */
								
									//String.valueOf(empObj[i][10])
									
									
									
									
									if(goalMapObj!=null && goalMapObj.length>0)
									{
										if(goalHdrData!=null && goalHdrData.length>0)
										{
											
											double TOTAL_FINAL_GOAL_SCORE=0;
											for (int p = 0; p < goalHdrData.length; p++) {
												
												
												
												double SELF_GOAL_TOTAL_WTG=0;
												double MGR_GOAL_TOTAL_WTG=0;
												double SELF_GOAL_TOTAL_SCORE=0;
												double MGR_GOAL_TOTAL_SCORE=0;
												double FINAL_GOAL_SCORE_ASST=0;
												
												String qoalQuery="SELECT NVL(GOAL_RATING_WEIGHTAGE,0) AS GOAL_WT, NVL(GOAL_SELF_WEIGHTAGE,0) AS SELF_WGT, NVL(GOAL_MANAGER_WEIGHTAGE,0) AS MGR_WT, MULTIPLE_RATING_OPTION AS AVG_RT, NVL(APPRAISAL_RATING_WEIGHTAGE,0) AS APPR_WGT  ,NVL(GOAL_REVIEW_SELF_RATING,0), NVL(GOAL_REVIEW_MGR_RATING,0), NVL(GOAL_REVIEW_FINAL_RATING,0),HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_CONFIG.GOAL_ID, "+
												"HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID,TO_CHAR(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE,'DD-MM-YYYY')AS ASSESSMENT_DATE,NVL(GOAL_ASSESSMENT_WEIGTAGE,0) "+	
												 "FROM HRMS_GOAL_CONFIG  	INNER JOIN HRMS_GOAL_EMP_HDR ON(HRMS_GOAL_EMP_HDR.GOAL_ID=HRMS_GOAL_CONFIG.GOAL_ID )	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) "+ 
												"LEFT JOIN HRMS_GOAL_ASSESSMENT_CONFIG ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE= HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_DATE and HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ID="+goalHdrData[p][0]+" ) "+ 	
												"WHERE HRMS_GOAL_CONFIG.APPRAISAL_CODE="+bean.getApprId()+"  AND HRMS_GOAL_EMP_HDR.EMP_GOAL_APPROVAL_STATUS=3 AND HRMS_EMP_OFFC.EMP_ID="+String.valueOf(empObj[i][10])+" AND HRMS_GOAL_CONFIG.GOAL_ID = "+goalHdrData[p][0];
									//double FINAL_WGT=0.0;
												Object goalfinalObj[][] = getSqlModel().getSingleResult(qoalQuery);
												if(goalfinalObj!=null && goalfinalObj.length>0)
												{
													Object [][]goalHeading=new Object[1][1];
													goalHeading [0][0]="Goal Score Sheet : " +goalHdrData[p][1];
													TableDataSet tdsGoalScoreHeading = new TableDataSet();
													tdsGoalScoreHeading.setData(goalHeading);
													tdsGoalScoreHeading.setCellAlignment(new int[] {0});
													tdsGoalScoreHeading.setCellWidth(new int[] {100 });
													tdsGoalScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
													tdsGoalScoreHeading.setBlankRowsAbove(1);
													rg.addTableToDoc(tdsGoalScoreHeading);
													
													for(int j=0;j<goalfinalObj.length;j++)
													{
														SELF_GOAL_TOTAL_WTG+=Double.parseDouble(String.valueOf(goalfinalObj[j][1]));
														MGR_GOAL_TOTAL_WTG+=Double.parseDouble(String.valueOf(goalfinalObj[j][2]));
														FINAL_GOAL_SCORE_ASST=FINAL_GOAL_SCORE_ASST+((Double.parseDouble(String.valueOf(goalfinalObj[j][7])))*(Double.parseDouble(String.valueOf(goalfinalObj[j][12])))/100);
													}
													
													System.out.println("SELF_GOAL_TOTAL_WTG ::  "+SELF_GOAL_TOTAL_WTG +" AVG :: "+SELF_GOAL_TOTAL_WTG/goalfinalObj.length);
													System.out.println("MGR_GOAL_TOTAL_WTG ::  "+MGR_GOAL_TOTAL_WTG+" AVG :: "+MGR_GOAL_TOTAL_WTG/goalfinalObj.length);
													System.out.println("FINAL_GOAL_SCORE_ASST ::  "+FINAL_GOAL_SCORE_ASST);
													
													SELF_GOAL_TOTAL_SCORE=((SELF_GOAL_TOTAL_WTG/goalfinalObj.length)*FINAL_GOAL_SCORE_ASST/100);
													MGR_GOAL_TOTAL_SCORE=((MGR_GOAL_TOTAL_WTG/goalfinalObj.length)*FINAL_GOAL_SCORE_ASST/100);
													System.out
															.println("Self Score :: "+SELF_GOAL_TOTAL_SCORE);
													System.out
													.println("Manager Score :: "+MGR_GOAL_TOTAL_SCORE);
													TOTAL_FINAL_GOAL_SCORE+=FINAL_GOAL_SCORE_ASST;
													
													Object goalObject[][] = new Object[3][4];										
													goalObject [0][0] ="1";
													goalObject [0][1] = "Self";
													goalObject [0][2] =String.valueOf(SELF_GOAL_TOTAL_WTG/goalfinalObj.length);
													goalObject [0][3] =Utility.twoDecimals(SELF_GOAL_TOTAL_SCORE);										
													goalObject [1][0] ="2";
													goalObject [1][1] = "Manager";
													goalObject [1][2] =String.valueOf(MGR_GOAL_TOTAL_WTG/goalfinalObj.length);
													goalObject [1][3] =Utility.twoDecimals(MGR_GOAL_TOTAL_SCORE);										
													goalObject [2][0] ="";
													goalObject [2][1] = "Total Score";
													goalObject [2][2] ="";
													goalObject [2][3] =String.valueOf(Utility.twoDecimals(SELF_GOAL_TOTAL_SCORE+MGR_GOAL_TOTAL_SCORE));										
													TableDataSet tdsGoalScoreTable = new TableDataSet();
													tdsGoalScoreTable.setData(goalObject);
													tdsGoalScoreTable.setCellAlignment(new int[]{1,0,1,1});
													tdsGoalScoreTable.setCellWidth(new int[]{10,50,20,20});
													tdsGoalScoreTable.setHeader(new String []{"Sr.No.","Phase Name","Weightage","Score"});
													tdsGoalScoreTable.setBorder(true);
													tdsGoalScoreTable.setHeaderBGColor(new Color(225,225,225));
													rg.addTableToDoc(tdsGoalScoreTable);
												}
												System.out.println("TOTAL_FINAL_GOAL_SCORE :: "+TOTAL_FINAL_GOAL_SCORE);
											}
										}
									}
									
									
									
									if(compMapObj!=null && compMapObj.length>0)
									{
										String compRatingTotalSql="SELECT distinct NVL(COMP_RATING_WEIGHTAGE,0) AS COMP_WT,NVL(COMP_SELF_WEIGHTAGE,0) AS SELF_WT,NVL(COMP_MANAGER_WEIGHTAGE,0) AS MGR_WGT, MULTIPLE_RATING_OPTION AS AVG_RT,NVL(COMP_REVIEW_SELF_RATING,0),NVL(COMP_REVIEW_MGR_RATING,0),NVL(COMP_REVIEW_FINAL_RATING,0),HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_WEIGTAGE,COMP_RATING_FLAG FROM HRMS_COMP_CONFIG "+
										"INNER JOIN HRMS_COMP_EMP ON (HRMS_COMP_CONFIG.COMP_ID=HRMS_COMP_EMP.COMP_ID) "+ 
										"INNER JOIN HRMS_COMP_EMP_ASSESSMENT_HDR ON(HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ID=HRMS_COMP_CONFIG.COMP_ID AND HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID= "+String.valueOf(empObj[i][10])+") "+
										"INNER JOIN HRMS_COMP_ASSESSMENT_CONFIG ON (HRMS_COMP_ASSESSMENT_CONFIG.COMP_ID=HRMS_COMP_CONFIG.COMP_ID AND HRMS_COMP_ASSESSMENT_CONFIG.COMP_ASSESSMENT_ID=HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID) "+
										"INNER JOIN PAS_APPR_GOAL_COMP_MAP ON (PAS_APPR_GOAL_COMP_MAP.APPR_MAP_CODE=HRMS_COMP_CONFIG.COMP_ID) "+
										"WHERE PAS_APPR_GOAL_COMP_MAP.APPR_CODE="+bean.getApprId()+" AND HRMS_COMP_EMP.COMP_EMP_ID= "+String.valueOf(empObj[i][10])+" AND APPR_MAP_TYPE='C'";
			
										Object[][]compRatingTotalObj=getSqlModel().getSingleResult(compRatingTotalSql);
										double COMP_SELF_WTG=0;
										double COMP_MGR_WTG=0;
										double COMP_SELF_SCORE=0;
										double COMP_MGR_SCORE=0;
										double FINAL_COMP_SCORE=0;
										String CALCULATE_RATING_FLAG="N";
										if(compRatingTotalObj!=null && compRatingTotalObj.length > 0)
										{
											
											for(int j=0;j<compRatingTotalObj.length;j++)
											{
												COMP_SELF_WTG+=Double.parseDouble(String.valueOf(compRatingTotalObj[j][1]));
												COMP_MGR_WTG+=Double.parseDouble(String.valueOf(compRatingTotalObj[j][2]));
												FINAL_COMP_SCORE=FINAL_COMP_SCORE+((Double.parseDouble(String.valueOf(compRatingTotalObj[j][6])))*(Double.parseDouble(String.valueOf(compRatingTotalObj[j][7])))/100);
												CALCULATE_RATING_FLAG=String.valueOf(compRatingTotalObj[j][8]);
											}
											System.out.println("CALCULATE_RATING_FLAG ::: "+CALCULATE_RATING_FLAG);
											if(CALCULATE_RATING_FLAG.equals("Y"))
											{
												if(FINAL_COMP_SCORE>100)
												{
													FINAL_COMP_SCORE=100;
												}
											}
											System.out.println("COMP_SELF_WTG ::  "+COMP_SELF_WTG +" AVG :: "+COMP_SELF_WTG/compRatingTotalObj.length);
											System.out.println("COMP_MGR_WTG ::  "+COMP_MGR_WTG+" AVG :: "+COMP_MGR_WTG/compRatingTotalObj.length);
											System.out.println("FINAL_GOAL_SCORE_ASST ::  "+FINAL_COMP_SCORE);
											
											COMP_SELF_SCORE=((COMP_SELF_WTG/compRatingTotalObj.length)*FINAL_COMP_SCORE/100);
											COMP_MGR_SCORE=((COMP_MGR_WTG/compRatingTotalObj.length)*FINAL_COMP_SCORE/100);
											System.out
													.println("Self Score :: "+COMP_SELF_SCORE);
											System.out
											.println("Manager Score :: "+COMP_MGR_SCORE);
											
											Object [][]compHeading=new Object[1][1];
											compHeading [0][0]="Competency Score Sheet :";
											TableDataSet tdsComplScoreHeading = new TableDataSet();
											tdsComplScoreHeading.setData(compHeading);
											tdsComplScoreHeading.setCellAlignment(new int[] {0});
											tdsComplScoreHeading.setCellWidth(new int[] {100 });
											tdsComplScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
											tdsComplScoreHeading.setBlankRowsAbove(1);
											rg.addTableToDoc(tdsComplScoreHeading);
											
											Object compObject[][] = new Object[3][4];										
											compObject [0][0] ="1";
											compObject [0][1] = "Self";
											compObject [0][2] =String.valueOf(COMP_SELF_WTG/compRatingTotalObj.length);
											compObject [0][3] =Utility.twoDecimals(COMP_SELF_SCORE);										
											compObject [1][0] ="2";
											compObject [1][1] = "Manager";
											compObject [1][2] =String.valueOf(COMP_MGR_WTG/compRatingTotalObj.length);
											compObject [1][3] =Utility.twoDecimals(COMP_MGR_SCORE);										
											compObject [2][0] ="";
											compObject [2][1] = "Total Score";
											compObject [2][2] ="";
											compObject [2][3] =Utility.twoDecimals(FINAL_COMP_SCORE);										
											TableDataSet tdsCompScoreTable = new TableDataSet();
											tdsCompScoreTable.setData(compObject);
											tdsCompScoreTable.setCellAlignment(new int[]{1,0,1,1});
											tdsCompScoreTable.setCellWidth(new int[]{10,50,20,20});
											tdsCompScoreTable.setHeader(new String []{"Sr.No.","Phase Name","Weightage","Score"});
											tdsCompScoreTable.setBorder(true);
											tdsCompScoreTable.setHeaderBGColor(new Color(225,225,225));
											rg.addTableToDoc(tdsCompScoreTable);
											
										}
									}
									
									
									if((compMapObj !=null && compMapObj.length > 0)&& (goalMapObj!=null && goalMapObj.length>0)){										
										
										
										String sqlFinalScore="SELECT NVL(APPR_WEIGHTAGE,0) AS APPR_WEIGHTAGE,(NVL(APPR_INDIVIDUAL_SCORE,0)*NVL(APPR_WEIGHTAGE,0)/100) AS APPR_FINAL_SCORE,NVL(APPR_GOAL_WEIGHTAGE,0) AS GOAL_WEIGHTAGE ,NVL(GOAL_SCORE,0) AS GOAL_SCORE ,NVL(APPR_COMP_WEIGHTAGE,0) AS COMP_WEIGHTAGE, "+
															 "NVL(COMP_SCORE,0) AS COMP_SCORE ,NVL(APPR_ADJUST_FACTOR,' ')||''||NVL(APPR_SCORE_ADJUST,0) AS APPR_ADJUST_FACTOR, "+
															 "NVL(APPR_SCORE,0) AS TOTAL,NVL(APPR_FINAL_SCORE,0) AS FINAL_TOTAL FROM PAS_APPR_SCORE WHERE APPR_ID="+bean.getApprId()+"AND EMP_ID="+String.valueOf(empObj[i][10]);
										Object[][]finalScoreObj=getSqlModel().getSingleResult(sqlFinalScore);
											if(finalScoreObj!=null && finalScoreObj.length>0)
											{
												Object [][]finalHeading=new Object[1][1];
												finalHeading [0][0]="Final Score Sheet :";
												TableDataSet tdsFinalScoreHeading = new TableDataSet();
												tdsFinalScoreHeading.setData(finalHeading);
												tdsFinalScoreHeading.setCellAlignment(new int[] {0});
												tdsFinalScoreHeading.setCellWidth(new int[] {100 });
												tdsFinalScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
												tdsFinalScoreHeading.setBlankRowsAbove(1);
												rg.addTableToDoc(tdsFinalScoreHeading);
												
												Object obj[][] = new Object[6][4];										
												obj [0][0] ="1";
												obj [0][1] = "Goal Rating";
												obj [0][2] =String.valueOf(finalScoreObj[0][2]);
												obj [0][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][3])));	
												obj [1][0] ="2";
												obj [1][1] = "Competency Rating";
												obj [1][2] =String.valueOf(finalScoreObj[0][4]);
												obj [1][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][5])));	
												obj [2][0] ="3";
												obj [2][1] = "Appraisal Rating";
												obj [2][2] =String.valueOf(finalScoreObj[0][0]);
												obj [2][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][1])));									
												obj [3][0] ="";
												obj [3][1] = "Total Score Achieved";
												obj [3][2] ="";
												obj [3][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][7])));
												obj [4][0] ="";
												obj [4][1] = "Adjustment Factor";
												obj [4][2] ="";
												obj [4][3] =String.valueOf(finalScoreObj[0][6]);									
												obj [5][0] ="";
												obj [5][1] = "Total";
												obj [5][2] ="";
												obj [5][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][8])));									
												finalTotalScore=String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][8]))));
												TableDataSet tdsFinalScoreTable = new TableDataSet();
												tdsFinalScoreTable.setData(obj);
												tdsFinalScoreTable.setCellAlignment(new int[]{1,0,1,1});
												tdsFinalScoreTable.setCellWidth(new int[]{10,50,20,20});
												tdsFinalScoreTable.setHeader(new String []{"Sr.No.","Phase Name","Weightage","Score"});
												tdsFinalScoreTable.setBorder(true);
												tdsFinalScoreTable.setHeaderBGColor(new Color(225,225,225));
												rg.addTableToDoc(tdsFinalScoreTable);
											}
										
										
										
										
										
										
																														
									}
									else if(goalMapObj!=null && goalMapObj.length>0)
									{
										
										String sqlFinalScore="SELECT NVL(APPR_WEIGHTAGE,0) AS APPR_WEIGHTAGE,(NVL(APPR_INDIVIDUAL_SCORE,0)*NVL(APPR_WEIGHTAGE,0)/100) AS APPR_FINAL_SCORE,NVL(APPR_GOAL_WEIGHTAGE,0) AS GOAL_WEIGHTAGE ,NVL(GOAL_SCORE,0) AS GOAL_SCORE ,NVL(APPR_COMP_WEIGHTAGE,0) AS COMP_WEIGHTAGE, "+
										 "NVL(COMP_SCORE,0) AS COMP_SCORE ,NVL(APPR_ADJUST_FACTOR,' ')||''||NVL(APPR_SCORE_ADJUST,0) AS APPR_ADJUST_FACTOR, "+
										 "NVL(APPR_SCORE,0) AS TOTAL,NVL(APPR_FINAL_SCORE,0) AS FINAL_TOTAL FROM PAS_APPR_SCORE WHERE APPR_ID="+bean.getApprId()+"AND EMP_ID="+String.valueOf(empObj[i][10]);;
										Object[][]finalScoreObj=getSqlModel().getSingleResult(sqlFinalScore);
											if(finalScoreObj!=null && finalScoreObj.length>0)
											{
												Object [][]finalHeading=new Object[1][1];
												finalHeading [0][0]="Final Score Sheet :";
												TableDataSet tdsFinalScoreHeading = new TableDataSet();
												tdsFinalScoreHeading.setData(finalHeading);
												tdsFinalScoreHeading.setCellAlignment(new int[] {0});
												tdsFinalScoreHeading.setCellWidth(new int[] {100 });
												tdsFinalScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
												tdsFinalScoreHeading.setBlankRowsAbove(1);
												rg.addTableToDoc(tdsFinalScoreHeading);
												
												Object obj[][] = new Object[5][4];										
												obj [0][0] ="1";
												obj [0][1] = "Goal Rating";
												obj [0][2] =String.valueOf(finalScoreObj[0][2]);
												obj [0][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][3])));	
												obj [1][0] ="2";
												obj [1][1] = "Appraisal Rating";
												obj [1][2] =String.valueOf(finalScoreObj[0][0]);
												obj [1][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][1])));									
												obj [2][0] ="";
												obj [2][1] = "Total Score Achieved";
												obj [2][2] ="";
												obj [2][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][7])));
												obj [3][0] ="";
												obj [3][1] = "Adjustment Factor";
												obj [3][2] ="";
												obj [3][3] =String.valueOf(finalScoreObj[0][6]);									
												obj [4][0] ="";
												obj [4][1] = "Total";
												obj [4][2] ="";
												obj [4][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][8])));									
												finalTotalScore=String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][8]))));
												TableDataSet tdsFinalScoreTable = new TableDataSet();
												tdsFinalScoreTable.setData(obj);
												tdsFinalScoreTable.setCellAlignment(new int[]{1,0,1,1});
												tdsFinalScoreTable.setCellWidth(new int[]{10,50,20,20});
												tdsFinalScoreTable.setHeader(new String []{"Sr.No.","Phase Name","Weightage","Score"});
												tdsFinalScoreTable.setBorder(true);
												tdsFinalScoreTable.setHeaderBGColor(new Color(225,225,225));
												rg.addTableToDoc(tdsFinalScoreTable);
											}
									}
									else if(compMapObj!=null && compMapObj.length>0)
									{
										String sqlFinalScore="SELECT NVL(APPR_WEIGHTAGE,0) AS APPR_WEIGHTAGE,(NVL(APPR_INDIVIDUAL_SCORE,0)*NVL(APPR_WEIGHTAGE,0)/100) AS APPR_FINAL_SCORE,NVL(APPR_GOAL_WEIGHTAGE,0) AS GOAL_WEIGHTAGE ,NVL(GOAL_SCORE,0) AS GOAL_SCORE ,NVL(APPR_COMP_WEIGHTAGE,0) AS COMP_WEIGHTAGE, "+
										 "NVL(COMP_SCORE,0) AS COMP_SCORE ,NVL(APPR_ADJUST_FACTOR,' ')||''||NVL(APPR_SCORE_ADJUST,0) AS APPR_ADJUST_FACTOR, "+
										 "NVL(APPR_SCORE,0) AS TOTAL,NVL(APPR_FINAL_SCORE,0) AS FINAL_TOTAL FROM PAS_APPR_SCORE WHERE APPR_ID="+bean.getApprId()+"AND EMP_ID="+String.valueOf(empObj[i][10]);;
										Object[][]finalScoreObj=getSqlModel().getSingleResult(sqlFinalScore);
											if(finalScoreObj!=null && finalScoreObj.length>0)
											{
												Object [][]finalHeading=new Object[1][1];
												finalHeading [0][0]="Final Score Sheet :";
												TableDataSet tdsFinalScoreHeading = new TableDataSet();
												tdsFinalScoreHeading.setData(finalHeading);
												tdsFinalScoreHeading.setCellAlignment(new int[] {0});
												tdsFinalScoreHeading.setCellWidth(new int[] {100 });
												tdsFinalScoreHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
												tdsFinalScoreHeading.setBlankRowsAbove(1);
												rg.addTableToDoc(tdsFinalScoreHeading);
												
												Object obj[][] = new Object[5][4];										
												obj [0][0] ="1";
												obj [0][1] = "Competency Rating";
												obj [0][2] =String.valueOf(finalScoreObj[0][4]);
												obj [0][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][5])));	
												obj [1][0] ="2";
												obj [1][1] = "Appraisal Rating";
												obj [1][2] =String.valueOf(finalScoreObj[0][0]);
												obj [1][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][1])));									
												obj [2][0] ="";
												obj [2][1] = "Total Score Achieved";
												obj [2][2] ="";
												obj [2][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][7])));
												obj [3][0] ="";
												obj [3][1] = "Adjustment Factor";
												obj [3][2] ="";
												obj [3][3] =String.valueOf(finalScoreObj[0][6]);									
												obj [4][0] ="";
												obj [4][1] = "Total";
												obj [4][2] ="";
												obj [4][3] =Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][8])));									
												finalTotalScore=String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(finalScoreObj[0][8]))));
												TableDataSet tdsFinalScoreTable = new TableDataSet();
												tdsFinalScoreTable.setData(obj);
												tdsFinalScoreTable.setCellAlignment(new int[]{1,0,1,1});
												tdsFinalScoreTable.setCellWidth(new int[]{10,50,20,20});
												tdsFinalScoreTable.setHeader(new String []{"Sr.No.","Phase Name","Weightage","Score"});
												tdsFinalScoreTable.setBorder(true);
												tdsFinalScoreTable.setHeaderBGColor(new Color(225,225,225));
												rg.addTableToDoc(tdsFinalScoreTable);
											}
									}
									//END
									//else{
									String ratingAchieved="";
									Object ratingScaleAchived[][]=getScoreDescription(apprscoreObj, finalTotalScore);
									if(ratingScaleAchived!=null && ratingScaleAchived.length>0)
									 ratingAchieved = checkNull(String.valueOf(ratingScaleAchived[0][0]))+" "+checkNull(String.valueOf(ratingScaleAchived[0][1]));
									if(!(checkNull(String.valueOf(finalScoreObject[1])).equals(""))){
										ratingAchieved += " ("+checkNull(String.valueOf(ratingScaleAchived[0][2]))+")";
									}
									System.out.println("the rating Achived ::: "+ratingAchieved);
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
									//}
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
									mgmtTable.setBlankRowsBelow(6);
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
								}
							}
							
						}
						
					rg.addProperty(rg.PAGE_BREAK);
				} 
				 
			}else{
				Object [][]noData=new Object[1][1];
				noData [0][0]="No Data to display  :";
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(noData);
				noDataTable.setCellAlignment(new int[] {0});
				noDataTable.setCellWidth(new int[] {100 });
				noDataTable.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
				rg.addTableToDoc(noDataTable);
			}
				rg.process();
			 rg.createReport(response);
		}catch(Exception e){
			logger.error("Error in generateReport == "+e);
			e.printStackTrace();
		}
	}
	

	public String getQuery(int index,AppraisalMisReport bean){
		String sql="";
				sql= "SELECT APPR_TEMPLATE_ID,APPR_SECTION_ID,APPR_PHASE_ID,APPR_SECTION_VISIBILITY,APPR_SECTION_RATING,APPR_SECTION_COMMENT "+  
				" FROM PAS_APPR_SECTION_DTL "+  
				"WHERE APPR_ID = "+bean.getApprId()+" "; 
				if(bean.getPhaseCode()!=null && bean.getPhaseCode().length()>0 )
				{
					sql+="AND  APPR_SECTION_ID IN ( SELECT APPR_SECTION_ID "+
				"FROM PAS_APPR_SECTION_DTL  WHERE APPR_ID = "+bean.getApprId()+" AND APPR_PHASE_ID="+bean.getPhaseCode()+" AND (APPR_SECTION_VISIBILITY='Y' OR APPR_SECTION_RATING='Y' OR APPR_SECTION_COMMENT='Y')  ) ";
				}
				sql+="ORDER BY APPR_TEMPLATE_ID,APPR_SECTION_ID,APPR_PHASE_ID  ";
			switch(index){
			
			case 1 : return " SELECT ROWNUM,APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID "
							+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getApprId()+" and APPR_EMP_STATUS = 'A' "
							+ getFilter(bean)
							+" ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ";
			
			case 2 : return " SELECT ROWNUM,APPR_PHASE_NAME,OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||''||OFFC1.EMP_LNAME,RANK_NAME,APPR_APPRAISER_LEVEL,APPR_APPRAISEE_ID " 
							+" FROM PAS_APPR_APPRAISER " 
							+" INNER JOIN PAS_APPR_APPRAISER_GRP_DTL ON(PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) " 
							+" INNER JOIN HRMS_EMP_OFFC OFFC1 ON(APPR_APPRAISER_CODE= OFFC1.EMP_ID)  "
							+" INNER JOIN HRMS_RANK ON(RANK_ID=EMP_RANK) "
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_APPRAISER.APPR_PHASE_ID) "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID "  
							+" WHERE PAS_APPR_APPRAISER.APPR_ID = "+bean.getApprId() 
							+ getFilter(bean)
							+" ORDER BY APPR_APPRAISEE_ID,APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL ";
			
			/*case 3 : return " SELECT ROWNUM,"
							+" APPR_QUES_DESC,APPR_QUES_WEIGHTAGE,APPR_QUES_RATING,APPR_QUES_PERCENT_WT,APPR_QUES_COMMENTS, "
							+" APPR_EMP_ID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID,APPR_PHASE_NAME,(REPT.EMP_FNAME||' '||REPT.EMP_MNAME|| ' ' || REPT.EMP_LNAME),APPR_EVALUATOR_LEVEL, "
							+" APPR_SECTION_ID,APPR_SECTION_NAME "
							+" FROM PAS_APPR_COMMENTS "
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID)"
							+" INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID ) "
							+" INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_COMMENTS.APPR_QUESTION_CODE) "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_COMMENTS.APPR_EMP_ID "
							+"  INNER JOIN HRMS_EMP_OFFC REPT ON REPT.EMP_ID = PAS_APPR_COMMENTS.APPR_EVALUATOR_CODE "
							+" WHERE PAS_APPR_COMMENTS.APPR_ID = "+bean.getApprId()+" " 
							+ getFilter(bean)
							+" ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL,APPR_SECTION_ORDER";
			*/
			/*case 3:  return " SELECT ROWNUM, APPR_QUES_DESC,APPR_QUES_WEIGHTAGE,APPR_QUES_RATING,APPR_QUES_PERCENT_WT,APPR_QUES_COMMENTS,  APPR_EMP_ID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID,APPR_PHASE_NAME,(REPT.EMP_FNAME||' '||REPT.EMP_MNAME|| ' ' || REPT.EMP_LNAME),APPR_EVALUATOR_LEVEL,  APPR_SECTION_ID,APPR_SECTION_NAME  FROM PAS_APPR_COMMENTS "
							+ "INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_COMMENTS.APPR_ID=PAS_APPR_QUES_MAPPING.APPR_ID "
							+ "AND PAS_APPR_COMMENTS.APPR_PHASE_ID=PAS_APPR_QUES_MAPPING.APPR_PHASE "
							+ "AND PAS_APPR_COMMENTS.APPR_SECTION_ID=PAS_APPR_QUES_MAPPING.APPR_SECTION_ID "
							+ "AND PAS_APPR_COMMENTS.APPR_TEMPLATE_ID=PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID "
							+ "AND APPR_QUESTION_CODE=APPR_QUESTION_ID) "
							+ "INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID) "
							+ "INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID ) "
							+ "INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_COMMENTS.APPR_QUESTION_CODE) "
							+ "INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_COMMENTS.APPR_EMP_ID "
							+ "INNER JOIN HRMS_EMP_OFFC REPT ON REPT.EMP_ID = PAS_APPR_COMMENTS.APPR_EVALUATOR_CODE "
							+ "WHERE PAS_APPR_COMMENTS.APPR_ID = "
							+ bean.getApprId()
							+ " "
							+ getFilter(bean)
							+ " ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL,APPR_SECTION_ORDER,APPR_QUESTION_ORDER";
*/
			case 3:	return " SELECT ROWNUM, Q_DESC,WT,RATING,PERCENT,COMMENTS,EMPID,PHASEID,PHASE_NAME,REPNAME,EVAL_LEVEL,SECID,SEC_NAME FROM ( "
							+ " SELECT  DISTINCT APPR_QUES_DESC AS Q_DESC,"
							+ " APPR_QUES_WEIGHTAGE  AS WT,"
							+ " APPR_QUES_RATING AS RATING,APPR_QUES_PERCENT_WT as PERCENT,"
							+ " APPR_QUES_COMMENTS AS COMMENTS,  "
							+ " APPR_EMP_ID AS EMPID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID AS PHASEID,"
							+ " APPR_PHASE_NAME AS PHASE_NAME,(REPT.EMP_FNAME||' '||REPT.EMP_MNAME|| ' ' || REPT.EMP_LNAME) AS REPNAME,"
							+ " APPR_EVALUATOR_LEVEL AS EVAL_LEVEL,  APPR_SECTION_ID AS SECID,"
							+ " APPR_SECTION_NAME AS SEC_NAME,APPR_PHASE_ORDER,"
							+ " APPR_SECTION_ORDER,APPR_QUESTION_ORDER  FROM PAS_APPR_COMMENTS "
							+ " INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_COMMENTS.APPR_ID=PAS_APPR_QUES_MAPPING.APPR_ID "
							+ " AND PAS_APPR_COMMENTS.APPR_PHASE_ID=PAS_APPR_QUES_MAPPING.APPR_PHASE "
							+ " AND PAS_APPR_COMMENTS.APPR_SECTION_ID=PAS_APPR_QUES_MAPPING.APPR_SECTION_ID "
							+ " AND PAS_APPR_COMMENTS.APPR_TEMPLATE_ID=PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID "
							+ " AND PAS_APPR_COMMENTS.APPR_QUESTION_CODE=PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID ) "
							+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID) "
							+ " INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID ) "
							+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_COMMENTS.APPR_QUESTION_CODE) "
							+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_COMMENTS.APPR_EMP_ID "
							+ " INNER JOIN HRMS_EMP_OFFC REPT ON REPT.EMP_ID = PAS_APPR_COMMENTS.APPR_EVALUATOR_CODE WHERE PAS_APPR_COMMENTS.APPR_ID="
							+ bean.getApprId()
							+ " "
							+ getFilter(bean)
							+ " ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL,APPR_SECTION_ORDER,APPR_QUESTION_ORDER)";
			case 4 : return " SELECT ROWNUM,APPR_TRN_DESC,TO_CHAR(APPR_TRN_FROM,'dd-mm-yyyy'),TO_CHAR(APPR_TRN_TO,'dd-mm-yyyy'),APPR_TRN_DURATION,APPR_TRN_SPONCER,APPR_EMP_ID " 
							+" FROM PAS_APPR_TRN_ATTENDED "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_TRN_ATTENDED.APPR_EMP_ID "
							+" WHERE APPR_ID = "+bean.getApprId()
							+ getFilter(bean)
							+" ORDER BY APPR_EMP_ID ";
			
			case 5 : return " SELECT ROWNUM,APPR_PHASE_NAME,APPR_QUES_DESC,APPR_TRNRECOM_COMMENT,APPR_EMP_ID,PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_PHASE_ID "
							+" FROM PAS_APPR_TRN_RECOMMEND_COMMENT " 
							+" INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_QUESTION_CODE) "
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID= PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_PHASE_ID) "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_EMP_ID "
							+" WHERE PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_ID = "+bean.getApprId()
							+ getFilter(bean)
							+" ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER ";

			case 6 : return " SELECT ROWNUM,APPR_AWARD_DESC,TO_CHAR(APPR_AWARD_DATE,'dd-mm-yyyy'),APPR_ISSUING_AUTHORITY,APPR_EMP_ID  "
							+" FROM PAS_APPR_AWARD_ACHIEVED "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_AWARD_ACHIEVED.APPR_EMP_ID "
							+" WHERE APPR_ID = "+bean.getApprId()
							+ getFilter(bean)
							+" ORDER BY APPR_EMP_ID ";
			
			case 7 : return " SELECT ROWNUM,APPR_PHASE_NAME,APPR_AWARD_DESC,APPR_AWARD_COMMENT,PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_EMP_ID,PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_PHASE_ID "
							+" FROM PAS_APPR_AWD_ACHIEVED_COMMENT "  
							+" INNER JOIN PAS_APPR_AWARD_ACHIEVED ON(PAS_APPR_AWARD_ACHIEVED.APPR_AWARD_CODE=PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_AWARD_CODE ) "
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID= PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_PHASE_ID) "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_EMP_ID "
							+" WHERE PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_ID = "+bean.getApprId()
							+ getFilter(bean)
							+" ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER ";
			
			case 8 : return " SELECT ROWNUM,APPR_DISCP_ACTION,TO_CHAR(APPR_DISCP_DATE,'dd-mm-yyyy'),APPR_DISCP_AUTHORITY ,APPR_EMP_ID " 
							+" FROM PAS_APPR_DISCIPLINARY "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_DISCIPLINARY.APPR_EMP_ID "
							+" WHERE APPR_ID = "+bean.getApprId()
							+ getFilter(bean)
							+" ORDER BY APPR_EMP_ID ";

			case 9 : return " SELECT ROWNUM,APPR_PHASE_NAME,APPR_DISCP_ACTION, APPR_DISCP_COMMENTS,PAS_APPR_DISCIPLINARY_COMMENT.APPR_EMP_ID,APPR_PHASE "
							+" FROM PAS_APPR_DISCIPLINARY_COMMENT " 
							+" INNER JOIN PAS_APPR_DISCIPLINARY ON (PAS_APPR_DISCIPLINARY.APPR_DISCP_ID=PAS_APPR_DISCIPLINARY_COMMENT.APPR_DISCP_ID ) "
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID= PAS_APPR_DISCIPLINARY_COMMENT.APPR_PHASE ) "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_DISCIPLINARY_COMMENT.APPR_EMP_ID "
							+" WHERE PAS_APPR_DISCIPLINARY_COMMENT.APPR_ID = "+bean.getApprId()
							+ getFilter(bean)
							+" ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER ";
			
			case 10 : return " SELECT ROWNUM,APPR_PHASE_NAME,APPR_QUES_DESC,APPR_CAREER_COMMENT,APPR_EMP_ID,APPR_PHASE "
							+" FROM PAS_APPR_CAREER_COMMENT " 
							+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_CAREER_COMMENT.APPR_QUESTION_CODE) "
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID= PAS_APPR_CAREER_COMMENT.APPR_PHASE ) "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_CAREER_COMMENT.APPR_EMP_ID "
							+" WHERE PAS_APPR_CAREER_COMMENT.APPR_ID = "+bean.getApprId()
							+ getFilter(bean)
							+" ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER ";
			
			case 11 : return " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),  NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,' '), " 
							+" NVL(HRMS_CENTER.CENTER_NAME,''),TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY '),TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_CAL_CODE, "
							+" (offc1.EMP_FNAME||' '||offc1.EMP_MNAME|| ' ' || offc1.EMP_LNAME),PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID "
							+" FROM PAS_APPR_ELIGIBLE_EMP "
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID ) " 
							+" INNER JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) " 
							+" INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) " 
							+" INNER JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) " 
							+" INNER JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID= PAS_APPR_ELIGIBLE_EMP.APPR_ID) "
							+" LEFT JOIN HRMS_EMP_OFFC offc1 ON (HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = offc1.EMP_ID) "
							+" WHERE PAS_APPR_CALENDAR.APPR_ID = "+bean.getApprId()+" "+ getFilter(bean)
							+" ORDER BY (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) ";
			
			/*case 12 : return " SELECT APPR_FINAL_SCORE, APPR_FINAL_SCORE_VALUE,APPR_FINAL_SCORE_DESC,APPR_ADJUST_FACTOR,APPR_SCORE_ADJUST,APPR_SCORE_FINALIZE,APPR_SCORE,PAS_APPR_SCORE.EMP_ID FROM PAS_APPR_SCORE "
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_SCORE.EMP_ID )"
							+" WHERE APPR_ID ="+bean.getApprId()
							+ getFilter(bean)
							+" ORDER BY EMP_ID "; */
			case 12 : return " SELECT APPR_FINAL_SCORE, APPR_FINAL_SCORE_VALUE,APPR_FINAL_SCORE_DESC,APPR_ADJUST_FACTOR,APPR_SCORE_ADJUST,APPR_SCORE_FINALIZE,NVL(APPR_INDIVIDUAL_SCORE,APPR_SCORE),PAS_APPR_SCORE.EMP_ID FROM PAS_APPR_SCORE "
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_SCORE.EMP_ID )"
							+" WHERE APPR_ID ="+bean.getApprId()
							+ getFilter(bean)
							+" ORDER BY EMP_ID ";
			
			case 13 : return " SELECT ROWNUM,APPR_PHASE_NAME,APPR_PHASE_WEIGHT_AGE,PHASE_FINAL_SCORE,PAS_APPR_TRACKING.EMP_ID FROM  PAS_APPR_TRACKING "
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_TRACKING.PHASE_ID)"
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_TRACKING.EMP_ID )"
							+" WHERE PAS_APPR_TRACKING.APPR_ID = "+bean.getApprId()
							+ getFilter(bean)
							+" ORDER BY EMP_ID,APPR_PHASE_ORDER,PHASE_APPRAISER_LEVEL ";

			case 14 : return " SELECT ROWNUM,APPR_PHASE_NAME,APPR_TRN_DESC,APPR_TRNATTEN_COMMENT,PAS_APPR_TRN_ATTENDED_COMMENT.APPR_EMP_ID " 
							+" FROM PAS_APPR_TRN_ATTENDED_COMMENT "
							+" INNER JOIN PAS_APPR_TRN_ATTENDED ON PAS_APPR_TRN_ATTENDED.APPR_TRN_CODE = PAS_APPR_TRN_ATTENDED_COMMENT.APPR_TRN_CODE "
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_TRN_ATTENDED_COMMENT.APPR_PHASE_ID "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_TRN_ATTENDED_COMMENT.APPR_EMP_ID "
							+" WHERE PAS_APPR_TRN_ATTENDED_COMMENT.APPR_ID = "+bean.getApprId()
							+ getFilter(bean)
							+" ORDER BY PAS_APPR_TRN_ATTENDED_COMMENT.APPR_EMP_ID,APPR_PHASE_ORDER ";
			
			case 15 : return " SELECT ROWNUM,APPR_PHASE_NAME,APPR_AWARD,APPR_AWARD_REASON,APPR_EMP_ID FROM PAS_APPR_AWD_NOMINATE_COMMENT "
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_AWD_NOMINATE_COMMENT.APPR_PHASE_ID "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_AWD_NOMINATE_COMMENT.APPR_EMP_ID "
							+" WHERE PAS_APPR_AWD_NOMINATE_COMMENT.APPR_ID = "+bean.getApprId()
							+  getFilter(bean)
							+" ORDER BY PAS_APPR_AWD_NOMINATE_COMMENT.APPR_EMP_ID,APPR_PHASE_ORDER ";
			
			case 16 : return " SELECT APPR_EMP_GRP_EMPID,APPR_TEMPLATE_ID FROM PAS_APPR_EMP_GRP_DTL "
							+" INNER JOIN PAS_APPR_EMP_GRP_HDR ON PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID = PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID "
							+" WHERE PAS_APPR_EMP_GRP_HDR.APPR_ID = "+bean.getApprId()
							+  getFilter(bean);
			
			/*case 17 : return " SELECT APPR_TEMPLATE_ID,APPR_SECTION_ID,APPR_PHASE_ID,APPR_SECTION_VISIBILITY,APPR_SECTION_RATING,APPR_SECTION_COMMENT " 
							+" FROM PAS_APPR_SECTION_DTL " 
							+" WHERE APPR_ID = "+bean.getApprId()+" ORDER BY APPR_TEMPLATE_ID,APPR_SECTION_ID,APPR_PHASE_ID ";
			*/
			case 17 : return  sql;

			
			case 18 : return " SELECT APPR_PHASE_ID,APPR_QUESWT_DISPLAY_FLAG,APPR_IS_SELFPHASE FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID = "+bean.getApprId();
			
			case 19 : return " SELECT ROWNUM,APPR_PHASE_NAME,APPR_COMMENTS,APPR_EMP_ID,X_POSITION,Y_POSITION,APPR_PHASE from PAS_EMP_GENERAL_COMMENTS "
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_EMP_GENERAL_COMMENTS.APPR_PHASE "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_EMP_GENERAL_COMMENTS.APPR_EMP_ID "
							+" WHERE PAS_EMP_GENERAL_COMMENTS.APPR_ID = "+bean.getApprId()
							+  getFilter(bean)
							+" ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER,X_POSITION,Y_POSITION ";
			
			case 20 : return " SELECT LABEL_NAME,APPR_COMMENT_FLAG,APPR_COLUMN_NOS,APPR_TEMPLATE_ID FROM PAS_GENERAL_COMMENT_DTL "
							+" INNER JOIN PAS_GENERAL_COMMENT_HDR ON PAS_GENERAL_COMMENT_HDR.APPR_GENERAL_ID = PAS_GENERAL_COMMENT_DTL.APPR_GENERAL_ID "
							+" WHERE  PAS_GENERAL_COMMENT_HDR.APPR_ID = "+bean.getApprId()
							+" ORDER BY X_POSITION,Y_POSITION ";
			
			case 21 : return " SELECT GOAL_DESCRIPTION,NVL(GOAL_COMMENTS,'') CMM,TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY') ACHDATE , GOAL_WEIGHTAGE,"
						+" HRMS_GOAL_EMP_HDR.EMP_ID||'#'||HRMS_GOAL_EMP_HDR.GOAL_ID,HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_CATEGORIES.GOAL_CATEGORY "
						+" FROM HRMS_GOAL_EMP_HDR "
						+" INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID)  "
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID)  "
						+" INNER JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)"
						+" LEFT JOIN HRMS_GOAL_CATEGORIES ON(HRMS_GOAL_CATEGORIES.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)"
						+" WHERE HRMS_GOAL_EMP_HDR.EMP_GOAL_APPROVAL_STATUS IN (3) AND APPRAISAL_CODE="+bean.getApprId()+" "+getFilter(bean)
						+" ORDER BY HRMS_GOAL_EMP_HDR.GOAL_HDR_ID";
		
			case 22 : return " SELECT APPROVER.EMP_FNAME||' '||APPROVER.EMP_MNAME|| ' ' || APPROVER.EMP_LNAME|| '-' || GOAL_APPR_COMMENTS,GOAL_CODE FROM HRMS_GOAL_PATH"
						+" INNER JOIN HRMS_EMP_OFFC APPROVER ON(APPROVER.EMP_ID=GOAL_APPROVER)"
						+" INNER JOIN HRMS_GOAL_EMP_HDR ON (HRMS_GOAL_EMP_HDR.GOAL_HDR_ID=GOAL_CODE)"
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID)"
						+" INNER JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)"
						+"  WHERE EMP_GOAL_APPROVAL_STATUS=3 AND APPRAISAL_CODE="+bean.getApprId()+" "+getFilter(bean);
			
/*			case 21 : return " SELECT HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID,EMP_TOKEN,"
			+" EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME, "
			+" GOAL_DESCRIPTION,NVL(GOAL_COMMENTS,'') CMM,TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY') ACHDATE ," 
			+" TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') ASSDATE,"
			+" DECODE(EMP_GOAL_APPROVAL_STATUS,'1','Draft','2','Pending For Approval','3','Approved')," 
			+" SELF_GOAL_RATING,NVL(SELF_GOAL_COMMENT,''),GOAL_RATING,GOAL_COMMENT ,HRMS_GOAL_EMP_DTL.GOAL_DTL_ID,HRMS_GOAL_EMP_HDR.EMP_ID||'#'||HRMS_GOAL_EMP_HDR.GOAL_ID"
			+" FROM HRMS_GOAL_EMP_HDR"
			+" INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) "
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) "
			+" LEFT JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON (HRMS_GOAL_EMP_HDR.GOAL_HDR_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID)"
			+" LEFT JOIN HRMS_GOAL_SELF_ASSESSMENT_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID "
			+" AND HRMS_GOAL_EMP_DTL.GOAL_DTL_ID=SELF_GOAL_DTL_ID   ) "
			+" LEFT JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON (HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID "
			+" AND HRMS_GOAL_EMP_DTL.GOAL_DTL_ID=HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ID ) "
			//+" WHERE HRMS_GOAL_EMP_HDR.GOAL_ID="+goalId+" AND EMP_GOAL_APPROVAL_STATUS IN (3)"
			+" WHERE EMP_GOAL_APPROVAL_STATUS IN (3)"
			// +" AND HRMS_GOAL_EMP_HDR.EMP_ID="
			+" ORDER BY HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_EMP_HDR.GOAL_ID,SELF_GOAL_ASSESSMENT_ID,SELF_GOAL_DTL_ID";
*/			
			case 23 : return " SELECT ROWNUM,APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP "
			+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID "
			+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getApprId()+" and APPR_EMP_STATUS = 'A' "
			+" ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ";
			
			default : return "Query not found";
			
			}
		}
	
	public boolean isAppraisalScoreShow(String apprisalId){
		boolean result=true;
		String sqlQuery="SELECT NVL(APPR_SCORE_FLAG,'Y') FROM PAS_APPR_CALENDAR WHERE APPR_ID="+apprisalId;
		Object [][] dataObj=getSqlModel().getSingleResult(sqlQuery);
		if(dataObj!=null && dataObj.length>0)
		{
			if("N".equals(checkNull(String.valueOf(dataObj[0][0]))))
				result=false;
		}
		
		return result;
	}
	
	public void printCommentData(Object[][] phaseCommentObj,ReportGenerator rg,String templateCode){
		Object[] visibilityObj = visibilityMap.get(templateCode+"_"+phaseCommentObj[0][11]+"_"
				+phaseCommentObj[0][7]);
		Object [][] wtDisplayObj = null;
		if(visibilityObj != null && visibilityObj.length > 0 ){
			Object phaseHeading[][]=new Object[1][3];
			phaseHeading [0][0]="Phase :"+phaseCommentObj[0][8];
			phaseHeading [0][1]="";
			phaseHeading [0][2]="";
			TableDataSet phaseTitle = new TableDataSet();
			phaseTitle.setData(phaseHeading);
			phaseTitle.setCellAlignment(new int[] {0,0,0 });
			phaseTitle.setCellWidth(new int[] { 30,60,10 });
			phaseTitle.setBodyFontDetails(Font.HELVETICA, 11, Font.BOLD, new Color(0,0,0));
			//phaseTitle.setBlankRowsAbove(2);
			rg.addTableToDoc(phaseTitle);
			
			 wtDisplayObj = phaseWtDisplayMap.get(String.valueOf(phaseCommentObj[0][7]));
			if(String.valueOf(wtDisplayObj[0][2]).equals("N")){
				Object levelHeading[][]=new Object[1][4];
				levelHeading [0][0]="";
				levelHeading [0][1]="Level :"+phaseCommentObj[0][10];
				levelHeading [0][2]="";
				levelHeading [0][3]="Appraiser :"+String.valueOf(phaseCommentObj[0][9]);
				TableDataSet levelTitle = new TableDataSet();
				levelTitle.setData(levelHeading);
				levelTitle.setCellAlignment(new int[] {0,0,0,0 });
				levelTitle.setCellWidth(new int[] { 5,40,10,45 });
				levelTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
				rg.addTableToDoc(levelTitle);
				System.out.println("gjfdgk99999999999999fbdgdf");
			}
			
			Object sectionHeading[][]=new Object[1][3];
			sectionHeading [0][2]="Section :"+phaseCommentObj[0][12];
			sectionHeading [0][0]="";
			sectionHeading [0][1]="";
			
			TableDataSet sectionTitle = new TableDataSet();
			sectionTitle.setData(sectionHeading);
			sectionTitle.setCellAlignment(new int[] {0,0,0 });
			sectionTitle.setCellWidth(new int[] { 5,5,90 });
			sectionTitle.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
			//sectionTitle.setBlankRowsAbove(1);
			rg.addTableToDoc(sectionTitle);
		}
		
		
		int[] bcellWidth = null;
		int[] bcellAlign = null;
		String [] columns = null;
		try {
			//System.out.println("templateCode :: "+templateCode);
			//System.out.println("phaseCommentObj[0][11] ::: "+phaseCommentObj[0][11]);
			//System.out.println("phaseCommentObj[0][7] :::: "+phaseCommentObj[0][7]);
			/*Object[] visibilityObj = visibilityMap.get(templateCode+"_"+phaseCommentObj[0][11]+"_"
					+phaseCommentObj[0][7]);*/
			if(visibilityObj != null && visibilityObj.length > 0 ){
				wtDisplayObj = phaseWtDisplayMap.get(String.valueOf(phaseCommentObj[0][7]));
				//System.out.println("visibilityObj[3] :::: Section Flag :::  "+visibilityObj[3]);
				//System.out.println("visibilityObj[4] :::: Section Rating ::: "+visibilityObj[4]);
				//System.out.println("visibilityObj[5] :::: Section Comment ::: "+visibilityObj[5]);
				if(String.valueOf(visibilityObj[3]).equals("Y")){
					if(String.valueOf(visibilityObj[4]).equals("Y") && String.valueOf(visibilityObj[5]).equals("Y")){
						if(String.valueOf(wtDisplayObj[0][1]).equals("Y")){
							 columns = new String[]{"Sr.No.","Question Description","Weightage","Rating","Actuals","Comments"}; 
							 bcellWidth = new int[]{8,30,12,8,10,32}; 
							 bcellAlign = new int[]{1,0,1,1,1,0}; 
						}else{
							 columns = new String[]{"Sr.No.","Question Description","Rating","Comments"}; 
							 bcellWidth = new int[]{8,40,8,44}; 
							 bcellAlign = new int[]{1,0,1,0}; 
							 for (int i = 0; i < phaseCommentObj.length; i++) {
									 phaseCommentObj[i][2]= phaseCommentObj[i][3];
									 phaseCommentObj[i][3]= phaseCommentObj[i][5];
							}
						}
					}else if(String.valueOf(visibilityObj[4]).equals("Y") && String.valueOf(visibilityObj[5]).equals("N")){
						if(String.valueOf(wtDisplayObj[0][1]).equals("Y")){
							 columns = new String[]{"Sr.No.","Question Description","Weightage","Rating","Actuals"}; 
							 bcellWidth = new int[]{8,62,10,10,10}; 
							 bcellAlign = new int[]{1,0,1,1,1}; 
						}else{
							 columns = new String[]{"Sr.No.","Question Description","Rating"}; 
							 bcellWidth = new int[]{8,84,8}; 
							 bcellAlign = new int[]{1,0,1,}; 
							 for (int i = 0; i < phaseCommentObj.length; i++) {
									 phaseCommentObj[i][2]= phaseCommentObj[i][3];
							 }
						}
					}else if(String.valueOf(visibilityObj[4]).equals("N") && String.valueOf(visibilityObj[5]).equals("Y")){
						 columns = new String[]{"Sr.No.","Question Description","Comments"}; 
						 bcellWidth = new int[]{8,47,50}; 
						 bcellAlign = new int[]{1,0,0}; 
						 	for (int i = 0; i < phaseCommentObj.length; i++) {
								 	phaseCommentObj[i][2]= phaseCommentObj[i][5];
						 }
					}
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("Error in printCommentData setting V R C  == "+e);
			bcellAlign=new int[] {1,0,1,1,1,0};
			bcellWidth=new int[]{8,30,12,8,12,30};
			columns=new String[]{"Sr.No.","Question Description","Weightage","Rating","Average","Comments"};
		}
		TableDataSet tdstable = new TableDataSet();
		tdstable.setData(phaseCommentObj);
		//tdstable.setCellAlignment(new int[] {1,0,1,1,1,0});
		//tdstable.setCellWidth(new int[]{8,30,12,8,12,30});
		//tdstable.setHeader(new String[]{"Sr.No.","Question Description","Weightage","Rating","Average","Comments"});
		tdstable.setCellAlignment(bcellAlign);
		tdstable.setCellWidth(bcellWidth);
		tdstable.setHeader(columns);
		tdstable.setBorder(true);
		tdstable.setBlankRowsBelow(2);
		tdstable.setHeaderBGColor(new Color(225,225,225));
		rg.addTableToDoc(tdstable);
	}
	
	public void getData(ArrayList dataList,ReportGenerator rg,String templateCode ) {
		if(dataList.size() > 0 ){
			Object[][] phaseCommentObj = new Object[dataList.size()][13];
			System.out.println("----------1-------getData-------");
			for (int k = 0; k < dataList.size(); k++) {
				phaseCommentObj[k] = (Object[]) dataList.get(k);
			}
			if (phaseCommentObj != null && phaseCommentObj.length > 0) {
				
				for (int j = 0; j < phaseCommentObj.length; j++) 
					phaseCommentObj[j][0]=(j+1);
					
				printCommentData(phaseCommentObj, rg,templateCode);
			}
		}
	}
	public String checkNull(String result) {
		
		if (result == null || result.equals("null") || result.equals(" ")) {
			return "";
		} else {
			return result;
		}
	}
	
	/*public ReportGenerator getEmpGoalSection(AppraisalMisReport bean,ReportGenerator rg,String empId,String goalId,String goalName){
		
		Object [][]temObj =goalMap.get(empId+"#"+goalId);
		
		String query=" SELECT HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID,EMP_TOKEN,"
			 +" EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME, "
			 +" GOAL_DESCRIPTION,NVL(GOAL_COMMENTS,'') CMM,TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY') ACHDATE ," 
			 +" TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY') ASSDATE,"
			 +" DECODE(EMP_GOAL_APPROVAL_STATUS,'1','Draft','2','Pending For Approval','3','Approved')," 
			 +" SELF_GOAL_RATING,NVL(SELF_GOAL_COMMENT,''),GOAL_RATING,GOAL_COMMENT ,HRMS_GOAL_EMP_DTL.GOAL_DTL_ID"
			 +" FROM HRMS_GOAL_EMP_HDR"
			 +" INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) "
			 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_GOAL_EMP_HDR.EMP_ID) "
			 +" LEFT JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON (HRMS_GOAL_EMP_HDR.GOAL_HDR_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID)"
			 +" LEFT JOIN HRMS_GOAL_SELF_ASSESSMENT_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID "
			 +" AND HRMS_GOAL_EMP_DTL.GOAL_DTL_ID=SELF_GOAL_DTL_ID   ) "
			 +" LEFT JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON (HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID "
			 +" AND HRMS_GOAL_EMP_DTL.GOAL_DTL_ID=HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ID ) "
			 +" WHERE HRMS_GOAL_EMP_HDR.GOAL_ID="+goalId+" AND EMP_GOAL_APPROVAL_STATUS IN (3)"
			 +" AND HRMS_GOAL_EMP_HDR.EMP_ID="+empId
			 +" ORDER BY HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,SELF_GOAL_ASSESSMENT_ID,SELF_GOAL_DTL_ID";
			//Object [][] goalObj=getSqlModel().getSingleResult(query);
			Object [][] goalObj=goalMap.get(empId+"#"+goalId);
			goalObj =  Utility.removeColumns(goalObj, new int[] {14});
			Vector <Object []>testVector=new Vector<Object []>();
			Vector <String>commnetsVector=new Vector<String>();
			String goalHDRId="";
			String goalAssId="";
			String goalDtlId="";
			String commentsRating="";
			int maxCol =9;
			
			 * End Goal Setting Data
			 * 
			 
			
		
			if(goalObj !=null && goalObj.length>0){
				Object goalHeading[][]=new Object[1][1];
				goalHeading [0][0]="Goal Name :"+goalName;
				TableDataSet goalTitleTDS = new TableDataSet();
				goalTitleTDS.setData(goalHeading);
				goalTitleTDS.setCellAlignment(new int[] {0 });
				goalTitleTDS.setCellWidth(new int[] { 100 });
				goalTitleTDS.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
				goalTitleTDS.setBlankRowsAbove(1);
				rg.addTableToDoc(goalTitleTDS);
				
				int tempCol=9;
				for (int i = 0; i < goalObj.length; i++) {
					
					
					if(i==0){
						goalHDRId =String.valueOf(goalObj[i][0]);
						goalAssId =String.valueOf(goalObj[i][1]);
						goalDtlId =String.valueOf(goalObj[i][13]);
						testVector.add(goalObj[i]);
						commentsRating =goalObj[i][11]+"#.#.#."+goalObj[i][12];
						tempCol+=2;
					}else {
						if(goalHDRId.equals(String.valueOf(goalObj[i][0])) && goalAssId.equals(String.valueOf(goalObj[i][1]))
								&& goalDtlId.equals(String.valueOf(goalObj[i][13]))){
							if(commentsRating.equals("")){
								commentsRating =goalObj[i][11]+"#.#.#."+goalObj[i][12];
								tempCol+=2;
							}else{
								commentsRating =commentsRating+","+goalObj[i][11]+"#.#.#."+goalObj[i][12];
								tempCol+=2;
							}
							
						}else{
							goalHDRId =String.valueOf(goalObj[i][0]);
							goalAssId =String.valueOf(goalObj[i][1]);
							goalDtlId =String.valueOf(goalObj[i][13]);
							tempCol=9;
							commnetsVector.add(commentsRating);
							testVector.add(goalObj[i]);
							commentsRating =goalObj[i][11]+"#.#.#."+goalObj[i][12];
							
							tempCol+=2;
						}
					}
					if(i==goalObj.length-1 ){
						
						commnetsVector.add(commentsRating);
						if(!(goalHDRId.equals(String.valueOf(goalObj[i][0])) && goalAssId.equals(String.valueOf(goalObj[i][1]))
								&& goalDtlId.equals(String.valueOf(goalObj[i][13])))){
						tempCol+=2;
						}
					}
					if(tempCol> maxCol){
						maxCol =tempCol;
					}
				}
				
				
				 * Create final object to be displayed in the report
				 *  
				 
				Object [][]finalObj=new Object[testVector.size()][maxCol];
				String []columns=new String[maxCol];
				int []cellWidth=new int[maxCol];
				int []cellAlign=new int[maxCol];
				columns[0]="Emp.Id";
				columns[1]="Emp.Name";
				columns[2]="Goal Description";
				columns[3]="Goal Comments";
				columns[4]="Achievement date";
				columns[5]="Assessment Scheduled date";
				columns[6]="Status";
				columns[7]="Self Ratings";
				columns[8]="Self Comments";
				
				cellWidth[0]=10;
				cellWidth[1]=20;
				cellWidth[2]=20;
				cellWidth[3]=20;
				cellWidth[4]=15;
				cellWidth[5]=15;
				cellWidth[6]=15;
				cellWidth[7]=15;
				cellWidth[8]=20;
				
				cellAlign[0]=0;
				cellAlign[1]=0;
				cellAlign[2]=0;
				cellAlign[3]=0;
				cellAlign[4]=1;
				cellAlign[5]=0;
				cellAlign[6]=0;
				cellAlign[7]=2;
				cellAlign[8]=0;
				
				try {
					
					int level=1;
					for (int j = 0; j < cellAlign.length; j++) {
						
						columns[j + 9] = "Manager " + (level) + " Rating";
						cellWidth[j + 9] = 15;
						cellAlign[j + 9] = 2;
						j++;
						columns[j + 9] = "Manager " + (level++) + " Comments";
						cellWidth[j + 9] = 20;
						cellAlign[j + 9] = 0;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				for (int i = 0; i < finalObj.length; i++) {
					int index=0;
					Object []tempObj=testVector.get(i);
					for (int j = 0; j < tempObj.length-5; j++) {
						finalObj[i][j]=tempObj[j+2];
						index++;
					}
					
					String tempComment=commnetsVector.get(i);
					String levelComment[]=tempComment.split(",");
					for (int j = 0; j < levelComment.length; j++) {
						String []ratingComment=levelComment[j].split("#.#.#.");
						finalObj[i][index]=ratingComment[0];
						index++;
						finalObj[i][index]=ratingComment[1];
						index++;
					}
					
					
				}
				
				finalObj =  Utility.checkNullObjArr(finalObj, "");
				finalObj =  Utility.removeColumns(finalObj, new int[] {0,1,6});
				columns = Utility.removeColumns(columns,new int[] {0,1,6});
				cellAlign = Utility.removeColumns(cellAlign,new int[] {0,1,6});
				cellWidth = Utility.removeColumns(cellWidth,new int[] {0,1,6});
				TableDataSet tdstable = new TableDataSet();
				tdstable.setBlankRowsAbove(1);
				tdstable.setHeader(columns);
				tdstable.setData(finalObj);
				tdstable.setCellAlignment(cellAlign);
				tdstable.setCellWidth(cellWidth);
				tdstable.setBorder(true);
				tdstable.setHeaderBGColor(new Color(225, 225, 225));
				tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);
				
	}
			return rg;	
}*/
	
	private ReportGenerator getEmpGoalSection(AppraisalMisReport bean,
			ReportGenerator rg, String empId, String goalId,String goalName) {
		String keyString=empId+"#"+goalId;
		Object [][]goalObj=goalMap.get(keyString);
		
		if(goalObj !=null && goalObj.length>0){
			
			Object [][]goalApproverObj=goalApproverMap.get(String.valueOf(goalObj[0][5]));
			Object goalApprFinalObj[][]=new Object[1][goalApproverObj.length];
			
			for (int i = 0; i < goalApprFinalObj[0].length; i++) {
				goalApprFinalObj[0][i] =goalApproverObj[i][0];
			}
			Object goalHeading[][]=new Object[1][1];
			goalHeading [0][0]="Goal Name :"+goalName;
			TableDataSet goalTitleTDS = new TableDataSet();
			goalTitleTDS.setData(goalHeading);
			goalTitleTDS.setCellAlignment(new int[] {0 });
			goalTitleTDS.setCellWidth(new int[] { 100 });
			goalTitleTDS.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
			goalTitleTDS.setBlankRowsAbove(1);
			rg.addTableToDoc(goalTitleTDS);
			
			
			/*String []columns={"Goal Description","Goal Comments","Achievement Date","Weightage","Approver 1","Approver 2"};
			int []cellAlign={0,0,1,2,0,0};
			int []cellWidth={20,30,15,10,15,15};*/
			
			//rg.addTableToDoc(tdstable);
			goalObj =Utility.removeColumns(goalObj, new int[]{4,5});
			String []columns= new String [goalObj[0].length+goalApprFinalObj[0].length];
			int []cellAlign=new int [goalObj[0].length+goalApprFinalObj[0].length];
			int []cellWidth=new int [goalObj[0].length+goalApprFinalObj[0].length];
			
			/*String []approverColumns=new String[goalApprFinalObj[0].length];
			int []approverCellAlign=new int[goalApprFinalObj[0].length];
			int []approverCellWidth=new int[goalApprFinalObj[0].length];*/
			
			columns[0]="Goal Description";			
			columns[1]="Goal Comments";
			columns[2]="Achievement Date";
			columns[3]="Weightage";
			columns[4]="Goal Category";
			cellAlign[0]=0;
			cellAlign[1]=0;
			cellAlign[2]=1;
			cellAlign[3]=2;
			cellAlign[4]=0;
			cellWidth[0]=20;
			cellWidth[1]=25;
			cellWidth[2]=10;
			cellWidth[3]=10;
			cellWidth[4]=15;
			
			for (int i = 0; i < goalApprFinalObj[0].length; i++) {
				columns[i+5]="Approver Name-Comments";
				cellAlign[i+5]=0;
				cellWidth[i+5]=20;
			}
			
			
			Object joinObject[][]= new Object[goalObj.length][goalObj[0].length+goalApprFinalObj[0].length];
			for (int i = 0; i < goalObj.length; i++) {
				for (int j = 0; j < goalObj[0].length; j++) {
					joinObject[i][j]=goalObj[i][j];
				}
			}
			
			for (int i = 0; i < goalApproverObj.length; i++) {
				joinObject[0][5+i]=goalApproverObj[i][0];
			}
			
			TableDataSet tdstable = new TableDataSet();
			tdstable.setBlankRowsAbove(1);
			tdstable.setHeader(columns);
			tdstable.setData(joinObject);
			tdstable.setCellAlignment(cellAlign);
			tdstable.setCellWidth(cellWidth);
			tdstable.setBorder(true);
			tdstable.setHeaderBGColor(new Color(225, 225, 225));
			tdstable.setBlankRowsBelow(1);
			
			//HashMap<String, Object> finalMap = rg.joinTableDataSet(tdstable, approverTdstable, true, 70);
			rg.addTableToDoc(tdstable);
			
			
			
			
			
			
			
			
			
			
			
			
		}
		return rg;
	}
	public void getEmployeeDetails(String userEmpId,AppraisalMisReport appraisalMisReport) {
		// TODO Auto-generated method stub
		try{
			Object[] beanObj = new Object[1];
			beanObj[0] = userEmpId;
			String query = " SELECT HRMS_EMP_OFFC.EMP_ID, "
					+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	EMP_TOKEN,EMP_DIV  "
					+ "	FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ "  WHERE HRMS_EMP_OFFC.EMP_ID =" + beanObj[0] + " ";
			Object[][] values = getSqlModel().getSingleResult(query);
			appraisalMisReport.setEmpId(String.valueOf(values[0][0]));
			appraisalMisReport.setEmpName(String.valueOf(values[0][1]));
			appraisalMisReport.setEmpToken(String.valueOf(values[0][2]));
			appraisalMisReport.setDivisionId((String.valueOf(values[0][3])));
		}catch (Exception e) {
			
			logger.info("when onloading ..!!");
		}
	
	}
}
