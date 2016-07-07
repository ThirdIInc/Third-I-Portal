package org.paradyne.model.helpdesk;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.helpdesk.HelpDeskTechMISReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

/**
 * @author Pramila Naik
 * @date 30-03-12
 * @description HelpdeskModel class serves as model for help desk application
 *              form to write business logic to save, update, delete help desk
 *              application
 */
public class HelpdeskTechMISReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HelpdeskTechMISReportModel.class);
	
/** @param helpDeskReport bean 
 * @param response
 * @param request
 * @param reportPath -Path of the report to mail
 * @return null
 * This method is used to generate the report using  ireportV2 library for 
 * the Technical team members how many time they  processed the Request Category
 */
	/*public void RequestAnalysisReport(HelpDeskTechMISReport helpDeskReport,	
			HttpServletResponse response, HttpServletRequest request,String reportPath) {
		logger.info("RequestAnalysisReport");
	try{
		String reportType = helpDeskReport.getReportType();
		System.out.println("reportType-----"+ reportType);
		ReportDataSet rds = new ReportDataSet();
		rds.setReportType(reportType);
		String fileName = "Help Desk Request Analysis Report "+Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setUserEmpId(helpDeskReport.getUserEmpId());
		rds.setReportName("Help Desk Request Analysis Report");
		rds.setShowPageNo(true);
		rds.setUserEmpId(helpDeskReport.getUserEmpId());
		org.paradyne.lib.ireportV2.ReportGenerator rgs=null;
		String Technisions = "SELECT DISTINCT ( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME) FROM HELPDESK_TECHNICIANS "+ 
		 "INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HELPDESK_TECHNICIANS.TECH_CODE) ";
		Object[][]TechnisionsObj=getSqlModel().getSingleResult(Technisions);
		if(TechnisionsObj!=null && TechnisionsObj.length>0){
		rds.setTotalColumns(TechnisionsObj.length+1);
		}else{
			rds.setTotalColumns(2);
		}
		if(reportPath.equals("")){
			rgs = new org.paradyne.lib.ireportV2.ReportGenerator(rds,session, context,request);
		}else{
			logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
			rgs = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
			//request.setAttribute("reportPath", reportPath);
			request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
			request.setAttribute("action", "HelpDeskTechMisReport_TechTeamReport.action");
			request.setAttribute("fileName", fileName + "." + reportType);
		}
		
		String filters = "";
		if(helpDeskReport.getFromDate()!= null && !helpDeskReport.getFromDate().equals("")){
			
			
			filters+="From Date : "+helpDeskReport.getFromDate();
			
		}
		
		if(helpDeskReport.getToDate()!= null && !helpDeskReport.getToDate().equals("")){
			
			
			filters+="\n\nTo Date : "+helpDeskReport.getToDate();
			
		}
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filters } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setBodyFontStyle(0);
		filterData.setCellWidth(new int[] { 100 });
		filterData.setCellColSpan(new int[]{8});
		filterData.setBlankRowsBelow(1);
		filterData.setCellNoWrap(new boolean[]{false});
		filterData.setBorder(false);
		//filterData.setBlankRowsBelow(1);
		rgs.addTableToDoc(filterData);
		
		String query="   SELECT DISTINCT HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY,"+
			         " HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME||'#'||( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME),"+
			         "COUNT(*)"+ " FROM HELPDESK_ACTIVITY_LOG  INNER JOIN HELPDESK_REQUEST_HDR ON(HELPDESK_REQUEST_HDR.REQUEST_ID = HELPDESK_ACTIVITY_LOG.REQUEST_ID)"+
			         " INNER JOIN HELPDESK_REQUEST_TYPE ON HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID=REQUEST_TYPE "+
			         " INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY) "+
			         " INNER JOIN HELPDESK_TECHNICIANS ON (HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY = HELPDESK_TECHNICIANS.TECH_CODE) "+
			         "  WHERE HELPDESK_ACTIVITY_LOG.REQUEST_DATE BETWEEN TO_DATE('"+helpDeskReport.getFromDate()+"','DD-MM-YYYY') AND TO_DATE('"+helpDeskReport.getToDate()+"','DD-MM-YYYY') "+
			         " GROUP BY (HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY),HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME,( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME)";
		
		HashMap<String,Object[][]> map=getSqlModel().getSingleResultMap(query, 1, 2);
		TableDataSet tdstable = new TableDataSet();
		
		
			String Catagory = "SELECT REQUEST_TYPE_NAME FROM HELPDESK_REQUEST_TYPE ";
			
			Object[][]CatagoryObj=getSqlModel().getSingleResult(Catagory);
		
			
			if(CatagoryObj !=null && CatagoryObj.length>0 && TechnisionsObj!=null && TechnisionsObj.length>0){
		Object [][] final_object= new Object[CatagoryObj.length+1][TechnisionsObj.length+1];
		
		String[] headers=new String[1+TechnisionsObj.length];
		headers[0]="Catagory";
		
		
		for (int i = 0; i < final_object.length; i++) {
			if(i==0){
				final_object[i][0]="Catagory";
			}else{
			final_object[i][0]=String.valueOf(CatagoryObj[i-1][0]);
			}
		}
		for (int i = 0; i < final_object[0].length; i++) {
			if(i!=0){
				final_object[0][i]=String.valueOf(TechnisionsObj[i-1][0]);
				
			}
		
		}
		for (int i = 0; i < final_object.length; i++) {
			for (int j = 0; j < final_object[0].length; j++) {
				if(i!=0 && j!=0){
					final_object[i][j]="0";
					try {
						Object[][] mapObj = (Object[][]) map.get(String
								.valueOf(final_object[i][0])
								+ "#" + String.valueOf(final_object[0][j]));
						final_object[i][j]=String.valueOf(mapObj[0][2]);
					} catch (Exception e) {
						final_object[i][j]="0";	
					}
					
				}
			}
		}
		
		Object[] headerObject=(Object[])final_object[0];
		String[]headerStr=new String[headerObject.length];
		for (int i = 0; i < headerStr.length; i++) {
			headerStr[i]=String.valueOf(headerObject[i]);
		}
		int[] cellAlignment=new int[headerObject.length] ;
		for (int i = 0; i < cellAlignment.length; i++) {
			if(i==0)
			cellAlignment[i]=0;
			else
			cellAlignment[i]=2;
		}
		int[] cellWidth=new int[headerObject.length] ;
		for (int i = 0; i < cellWidth.length; i++) {
			cellWidth[i]=20;
		}
		Object[][]new_final_obj_data =new Object[final_object.length-1][final_object[0].length];
		for (int i = 0; i < new_final_obj_data.length; i++) {
			new_final_obj_data[i]=final_object[i+1];
		}
		
		tdstable.setHeader(headerStr);
		tdstable.setData(new_final_obj_data);// data object from query
		//It assign Cell alignment for Text column it should be Left alignment = 0
//For Date Data Field it should be Center Alignment=1
                         //For number Data Field it should be Right Alignment=2
		tdstable.setCellAlignment(cellAlignment);
		tdstable.setCellWidth(cellWidth);  //The Total should be 100
		tdstable.setBorderDetail(0);
		//This functions in tabledataset are used to generate and format headers
		tdstable.setBorder(true);         // Set Border to header   
		tdstable.setHeaderTable(true);    
		tdstable.setHeaderBorderDetail(2);		
		rgs.addTableToDoc(tdstable);
		tdstable.setBorderDetail(3);
	}else{
		TableDataSet noData = new TableDataSet();
		Object[][] noDataObj = new Object[1][1];
		noDataObj[0][0] = "No records available";
		
		noData.setData(noDataObj);
		noData.setCellAlignment(new int[] { 1 });
		noData.setCellWidth(new int[] { 100 });
		noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
				Font.BOLD, new BaseColor(0, 0, 0));
		noData.setBorder(false);
		rgs.addTableToDoc(noData);
	}
		if (obj!=null  && obj.length>0) {
			for (int k = 0; k < obj.length; k++) {
				System.out.println("obj{"+k+"}"+obj[k][0]);
			}
		}
			
			
		rgs.process();
		if(reportPath.equals("")){
		rgs.createReport(response);
		}
		else{
			 Generates the report as attachment
			rgs.saveReport(response);
		}
		

	} catch (Exception e) {
		e.printStackTrace();
	}
	}*/
	
	public void RequestAnalysisReport(HelpDeskTechMISReport helpDeskReport,	
			HttpServletResponse response, HttpServletRequest request,String reportPath) {
		logger.info("RequestAnalysisReport");
	try{
		String reportType = helpDeskReport.getReportType();
		System.out.println("reportType-----"+ reportType);
		ReportDataSet rds = new ReportDataSet();
		rds.setReportType(reportType);
		String fileName = "Request Management Report "+Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setUserEmpId(helpDeskReport.getUserEmpId());
		rds.setReportName("Request Management Report");
		rds.setShowPageNo(true);
		//rds.setPageSize("A5");
		if(reportType.equals("PDF")){
			rds.setPageOrientation("landscape");
		}
		rds.setUserEmpId(helpDeskReport.getUserEmpId());
		org.paradyne.lib.ireportV2.ReportGenerator rgs=null;
		String Technisions = "SELECT DISTINCT ( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME) FROM HELPDESK_TECHNICIANS "+ 
		 "INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID IN(HELPDESK_TECHNICIANS.TECH_CODE,HELPDESK_TECHNICIANS.MANAGER_CODE)) ";
		Object[][]TechnisionsObj=getSqlModel().getSingleResult(Technisions);
		if(TechnisionsObj!=null && TechnisionsObj.length>0){
		rds.setTotalColumns(TechnisionsObj.length+1);
		}else{
			rds.setTotalColumns(2);
		}
		if(reportPath.equals("")){
			rgs = new org.paradyne.lib.ireportV2.ReportGenerator(rds,session, context,request);
		}else{
			logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
			rgs = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
			//request.setAttribute("reportPath", reportPath);
			request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
			request.setAttribute("action", "HelpDeskTechMisReport_TechTeamReport.action");
			request.setAttribute("fileName", fileName + "." + reportType);
		}
		
		String filters = "";
		if(helpDeskReport.getFromDate()!= null && !helpDeskReport.getFromDate().equals("")){
			
			
			filters+="From Date : "+helpDeskReport.getFromDate();
			
		}
		
		if(helpDeskReport.getToDate()!= null && !helpDeskReport.getToDate().equals("")){
			
			
			filters+="\n\nTo Date : "+helpDeskReport.getToDate();
			
		}
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filters } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setBodyFontStyle(0);
		filterData.setCellWidth(new int[] { 100 });
		filterData.setCellColSpan(new int[]{8});
		filterData.setBlankRowsBelow(1);
		filterData.setCellNoWrap(new boolean[]{false});
		filterData.setBorder(false);
		//filterData.setBlankRowsBelow(1);
		rgs.addTableToDoc(filterData);
		
		/*String query="   SELECT DISTINCT HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY,"+
			         " HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME||'#'||( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME),"+
			         "COUNT(*)"+ " FROM HELPDESK_ACTIVITY_LOG  INNER JOIN HELPDESK_REQUEST_HDR ON(HELPDESK_REQUEST_HDR.REQUEST_ID = HELPDESK_ACTIVITY_LOG.REQUEST_ID)"+
			         " INNER JOIN HELPDESK_REQUEST_TYPE ON HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID=REQUEST_TYPE "+
			         " INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY) "+
			         " INNER JOIN HELPDESK_TECHNICIANS ON (HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY = HELPDESK_TECHNICIANS.TECH_CODE) "+
			         "  WHERE HELPDESK_ACTIVITY_LOG.REQUEST_DATE BETWEEN TO_DATE('"+helpDeskReport.getFromDate()+"','DD-MM-YYYY') AND TO_DATE('"+helpDeskReport.getToDate()+"','DD-MM-YYYY') "+
			         " GROUP BY (HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY),HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME,( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME)";
		*/
		String query="";
		if(helpDeskReport.getFromDate().equals(helpDeskReport.getToDate())){
			 query="SELECT DISTINCT HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY, "+
			"HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME||'#'||( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME), "+
			"COUNT(*) , SUM(NVL(REQUEST_CALL_DURATION,0)),NVL(FlOOR(SUM(REQUEST_CALL_DURATION)/60),0) || ' Hrs' || NVL(MOD(SUM(REQUEST_CALL_DURATION),60),0)||' Mins'  FROM HELPDESK_ACTIVITY_LOG  INNER JOIN HELPDESK_REQUEST_HDR ON(HELPDESK_REQUEST_HDR.REQUEST_ID = HELPDESK_ACTIVITY_LOG.REQUEST_ID) "+
			"INNER JOIN HELPDESK_REQUEST_TYPE ON HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID=REQUEST_TYPE  "+
			"INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY) " +
			"WHERE HELPDESK_ACTIVITY_LOG.REQUEST_MGR_STATUS IN('N') and HELPDESK_ACTIVITY_LOG.REQUEST_EMP_STATUS IN('N') "+
			" AND HELPDESK_ACTIVITY_LOG.REQUEST_DATE LIKE TO_DATE('"+helpDeskReport.getToDate()+"','DD-MM-YYYY')"+
			" GROUP BY (HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY),HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME,( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME)";

			
		}else{
		 query="SELECT DISTINCT HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY, "+
					"HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME||'#'||( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME), "+
					"COUNT(*) , SUM(NVL(REQUEST_CALL_DURATION,0)),NVL(FlOOR(SUM(REQUEST_CALL_DURATION)/60),0) || ' Hrs' || NVL(MOD(SUM(REQUEST_CALL_DURATION),60),0)||' Mins'  FROM HELPDESK_ACTIVITY_LOG  INNER JOIN HELPDESK_REQUEST_HDR ON(HELPDESK_REQUEST_HDR.REQUEST_ID = HELPDESK_ACTIVITY_LOG.REQUEST_ID) "+
					"INNER JOIN HELPDESK_REQUEST_TYPE ON HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID=REQUEST_TYPE  "+
					"INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY) " +
					"WHERE HELPDESK_ACTIVITY_LOG.REQUEST_MGR_STATUS IN('N') and HELPDESK_ACTIVITY_LOG.REQUEST_EMP_STATUS IN('N') "+
					" AND HELPDESK_ACTIVITY_LOG.REQUEST_DATE BETWEEN TO_DATE('"+helpDeskReport.getFromDate()+"','DD-MM-YYYY') AND TO_DATE('"+helpDeskReport.getToDate()+"','DD-MM-YYYY')"+
					" GROUP BY (HELPDESK_ACTIVITY_LOG.REQUEST_ACTION_BY),HELPDESK_REQUEST_TYPE.REQUEST_TYPE_NAME,( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME)";

		}
		HashMap<String,Object[][]> map=getSqlModel().getSingleResultMap(query, 1, 2);
		TableDataSet tdstable = new TableDataSet();
		
		
			String Catagory = "SELECT REQUEST_TYPE_NAME FROM HELPDESK_REQUEST_TYPE ";
			
			Object[][]CatagoryObj=getSqlModel().getSingleResult(Catagory);
		
			
			if(CatagoryObj !=null && CatagoryObj.length>0 && TechnisionsObj!=null && TechnisionsObj.length>0){
				int checkCount=TechnisionsObj.length;
				String[] columns = new String[checkCount+1];
				int[] colspan = new int[checkCount+1];
				int[] cellAlign = new int[checkCount+1] ;
				int[] cellWidth = new int[checkCount+1] ;
				boolean[] cellwrap = new boolean[checkCount+1];
				
				
				int chkcnt=0;
				columns[chkcnt]="Catagory";
				cellAlign[chkcnt]=0;
				cellWidth[chkcnt]=20;
				colspan[chkcnt]=1;
				cellwrap[chkcnt]=true;
				chkcnt+=1;
				for(int a=0;a< TechnisionsObj.length;a++){
						columns[chkcnt]=String.valueOf(TechnisionsObj[a][0]);
						cellAlign[chkcnt]=0;
						cellWidth[chkcnt]=35;
						colspan[chkcnt]=2;
						cellwrap[chkcnt]=true;
						chkcnt+=1;
						
				}
				
				
				tdstable.setHeader(columns);
				tdstable.setData(null);// data object from query
				tdstable.setHeaderLines(true);
				tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
				tdstable.setCellAlignment(cellAlign);
				tdstable.setCellWidth(cellWidth); 
				tdstable.setCellNoWrap(cellwrap);
				tdstable.setHeaderColSpan(colspan);
				tdstable.setHeaderBorderDetail(2);	
				tdstable.setBorderDetail(3);
				tdstable.setBorder(true); 
				tdstable.setHeaderTable(true);   
				rgs.addTableToDoc(tdstable);
				
				
			
				
				checkCount= TechnisionsObj.length*2;
				String[] columnsDet = new String[checkCount+1];
				int[] cellAlignDet = new int[checkCount+1] ;
				int[] cellWidthDet = new int[checkCount+1] ;
				boolean[] cellwrapDet = new boolean[checkCount+1];
				chkcnt=0;
				columnsDet[chkcnt]="";
				cellAlignDet[chkcnt]=0;
				cellWidth[chkcnt]=20;
				cellwrap[chkcnt]=true;
				chkcnt+=1;
				
				for(int a=0;a< TechnisionsObj.length;a++){
					for(int b=0;b<2;b++){
						if(b==0){
							columnsDet[chkcnt]="Total Request";
							cellAlignDet[chkcnt]=0;
							cellWidthDet[chkcnt]=35;
							cellwrapDet[chkcnt]=true;
							chkcnt+=1;
						}
						if(b==1){
							columnsDet[chkcnt]="Total Time";
							cellAlignDet[chkcnt]=0;
							cellWidthDet[chkcnt]=35;
							cellwrapDet[chkcnt]=true;
							chkcnt+=1;
						}
						
				}	
			}
				
				
				int rows=CatagoryObj.length;
				int cols=checkCount+1;
				Object[][] finalObject= new Object[rows][cols];
				
				for(int i=0;i<CatagoryObj.length;i++){
					finalObject[i][0] = String.valueOf(CatagoryObj[i][0]);
				}
				
				Object[][] AvgObject= new Object[1][cols];
				Object[][]AgTimeHrsMinu = new Object[1][cols];
				for (int i = 0; i < AvgObject.length; i++) {
					for (int j = 0; j < AvgObject[0].length; j++) {
						AvgObject[i][j]=new Double(0);
						AgTimeHrsMinu[i][j]=new String("0");
					}
				}
				
				for(int b=0;b< finalObject.length;b++){
					int count =1;
					System.out.println("hello"+b);
					for(int a=0;a< TechnisionsObj.length;a++){
								Object[][] mapObj =null ; 
								try {
									mapObj = (Object[][]) map.get(String
											.valueOf(finalObject[b][0])
											+ "#" + String.valueOf(TechnisionsObj[a][0]));
									
								} catch (Exception e) {
									e.printStackTrace();
								}
								if(mapObj !=null && mapObj.length>0){
									for(int c=0;c<2;c++){
										if(c==0){
											finalObject[b][count]= String.valueOf(mapObj[0][2]);
											
											AvgObject[0][count]= Double.parseDouble(String.valueOf(AvgObject[0][count]))+ Double.parseDouble(String.valueOf(mapObj[0][2]));
												count++;
												
												
												       
										}
										if(c==1){
											finalObject[b][count]= String.valueOf(mapObj[0][4]);
											if(String.valueOf(mapObj[0][3])!=null)
											AvgObject[0][count]= Double.parseDouble(String.valueOf(AvgObject[0][count]))+ Double.parseDouble(String.valueOf(mapObj[0][3]));
											String timequery = "SELECT Floor("+AvgObject[0][count]+"/60)||'Hrs'|| MOD("+AvgObject[0][count]+",60)|| 'Mins' from Dual";
											Object[][]timequeryObj=getSqlModel().getSingleResult(timequery);
											AgTimeHrsMinu[0][count] = String.valueOf(timequeryObj[0][0]);
											 count++;
											
										}
										
									}
								}else{
									for(int c=0;c<2;c++){
										if(c==0){
											finalObject[b][count]= "0";
												count++;
										}
										if(c==1){
											finalObject[b][count]= "0";
											count++;
										}
										
									}
								}
						}
					
				}
				AgTimeHrsMinu[0][0]="Total";
				finalObject=Utility.joinArrays(finalObject, AgTimeHrsMinu,1,0);
						
				Object[][] 	avgTimeObj=new Object[1][TechnisionsObj.length+1];
				//avgTimeObj[0][0]="Average Time";
				Object[][] 	avgTimeHrsMinObj=new Object[1][TechnisionsObj.length+1];
				avgTimeHrsMinObj[0][0]="Average Time";
				int col_count=1;
				for (int i = 0; i < AvgObject[0].length; i++) {
					if(i%2==1){
							avgTimeObj[0][col_count]=Math.abs(Double.parseDouble(String.valueOf(AvgObject[0][i+1]))/Double.parseDouble(String.valueOf(AvgObject[0][i])));
							if(String.valueOf(avgTimeObj[0][col_count]).equals("NaN")){
								avgTimeObj[0][col_count]="0";
													
							}
							String timequery = "SELECT Floor("+avgTimeObj[0][col_count]+"/60)||'  Hrs '|| Floor(MOD("+avgTimeObj[0][col_count]+",60))|| ' Mins' from Dual";
							Object[][]timequeryObj=getSqlModel().getSingleResult(timequery);
							avgTimeHrsMinObj[0][col_count] = String.valueOf(timequeryObj[0][0]);
							col_count++;
					}
				}
				
							
				TableDataSet dataTable = new TableDataSet();
				dataTable.setHeaderTable(true);  
				dataTable.setHeader(columnsDet);
				dataTable.setData(finalObject);// data object from query
				dataTable.setHeaderBorderDetail(3);	
				dataTable.setCellAlignment(cellAlignDet);
				dataTable.setCellWidth(cellWidthDet); 
				dataTable.setBorderDetail(3);
				tdstable.setHeaderColSpan(colspan);
				rgs.addTableToDoc(dataTable);
				 
				
				
				TableDataSet avgTable = new TableDataSet();
				avgTable.setData(avgTimeHrsMinObj);// data object from query
				avgTable.setCellAlignment(cellAlign);
				avgTable.setCellWidth(cellWidth); 
				avgTable.setBorderDetail(3);
				avgTable.setBorder(true); 
				avgTable.setBlankRowsBelow(1);
				avgTable.setCellColSpan(colspan);
				rgs.addTableToDoc(avgTable);
			
			}else{
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				/*noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
						Font.BOLD, new BaseColor(0, 0, 0));*/
				noData.setBorder(false);
				rgs.addTableToDoc(noData);
			}
			
			
			
			rgs.process();
			if(reportPath.equals("")){
			rgs.createReport(response);
			}
			else{
			/* Generates the report as attachment*/
			rgs.saveReport(response);
			}

	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
