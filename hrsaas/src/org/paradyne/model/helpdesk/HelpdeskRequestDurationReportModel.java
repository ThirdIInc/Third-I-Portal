package org.paradyne.model.helpdesk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.helpdesk.HelpdeskRequestDurationReport;
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

public class HelpdeskRequestDurationReportModel extends ModelBase {

	

	
		static org.apache.log4j.Logger logger = org.apache.log4j.Logger
				.getLogger(HelpdeskRequestDurationReportModel.class);
		
	/** @param helpDeskReport bean 
	 * @param response
	 * @param request
	 * @param reportPath -Path of the report to mail
	 * @return null
	 * This method is used to generate the report using  ireportV2 library for 
	 * the Technical team members how many time they  processed the Request Category
	 */
		public void RequestDurationReport(HelpdeskRequestDurationReport helpDeskReport,	
				HttpServletResponse response, HttpServletRequest request,String reportPath) {
			logger.info("RequestDurationReport");
		try{
			String reportType = helpDeskReport.getReport();
			System.out.println("reportType-----"+ reportType);
			ReportDataSet rds = new ReportDataSet();
			rds.setReportType(reportType);
			String fileName = "Request Analysis Report "+Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setUserEmpId(helpDeskReport.getUserEmpId());
			rds.setReportName("Request Analysis Report");
			rds.setShowPageNo(true);
			rds.setUserEmpId(helpDeskReport.getUserEmpId());
			org.paradyne.lib.ireportV2.ReportGenerator rgs=null;
			rds.setTotalColumns(15);
			rds.setPageOrientation("landscape");
			
		
			if(reportPath.equals("")){
				rgs = new org.paradyne.lib.ireportV2.ReportGenerator(rds,session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
				rgs = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "HelpDeskReqDurationReport_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			
			String filters = "";
			String whereClause="";
			String helpStatus="";
			String status = helpDeskReport.getStatus();
			if (status.equals("O")) {
				helpStatus = "Open";
			}
			if (status.equals("I")) {
				helpStatus = "In Process";
			}
			//end of if
			if (status.equals("R")) {
				helpStatus = "Resolved";
			}
			if (status.equals("O")) {
				helpStatus = "Open";
			}
			if (status.equals("C")) {
				helpStatus = "Closed";
			}
			if (status.equals("W")) {
				helpStatus = "Waiting For Approval";
			}
			if (status.equals("P")) {
				helpStatus = "Waiting For Parts";
			}
			
			if (!helpDeskReport.getEmployeeName().equals("")) {
				System.out.println("Hello"+helpDeskReport.getEmployeeCode());
				filters += "\n\nEmployee Name : " + helpDeskReport.getEmployeeName();
				whereClause += " AND REQUEST_POSTED_FOR ="
						+ helpDeskReport.getEmployeeCode();
				System.out.println("+bean.getEmployeeCode() ="
						+ helpDeskReport.getEmployeeCode());
			}
			if (!helpDeskReport.getTeamMemberName().equals("")) {
				filters += "\n\nRequest Owner  : " + helpDeskReport.getTeamMemberName();
				
				/*whereClause += " AND REQUEST_PENDING_WITH ="
				+ helpDeskReport.getTeamMemberCode();
				System.out.println("+bean.getTeamMemberCode() ="
				+ helpDeskReport.getTeamMemberCode());*/
				whereClause +=" AND  HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID "+
				" IN(SELECT REQ_TYPE_CODE FROM HELPDESK_MGRREPORTING_HDR where MANAGER_CODE="+helpDeskReport.getTeamMemberCode()+")";
			}
			if (!helpDeskReport.getCenterName().equals("")) {
				filters += "\n\nBranch : " + helpDeskReport.getCenterName();
				
				whereClause += " AND HRMS_CENTER.CENTER_NAME ='"
					+ helpDeskReport.getCenterName()+"'";
			System.out.println("+bean.getCenterName() ="
					+ helpDeskReport.getCenterName());
			}
			if (!helpDeskReport.getDeptName().equals("")) {
				filters += "\n\nDepartment : " + helpDeskReport.getDeptName();
				whereClause += " AND REQUEST_APPLIED_TO_DEPT ="
					+ helpDeskReport.getDeptCode();
				System.out.println("+bean.getDeptCode() ="
					+ helpDeskReport.getDeptCode());
			}
			if (!helpDeskReport.getCatagoryName().equals("")) {
				filters += "\n\nCategory : " + helpDeskReport.getCatagoryName();
				
				whereClause += " AND REQUEST_TYPE ="
					+ helpDeskReport.getCatagoryCode();
			System.out.println("+bean.getCatagoryCode() ="
					+ helpDeskReport.getCatagoryCode());
			}
			
			if (!helpDeskReport.getStatus().equals("") || helpDeskReport.getStatus()==null) {
				filters += "\n\n Status : " + helpStatus;
				whereClause += " AND REQUEST_APPL_STATUS ='"+helpDeskReport.getStatus()+"' "; 
				System.out.println("+bean.getStatus() ="	+ helpDeskReport.getStatus());
			}
			 if(!helpDeskReport.getFromDate().equals("") || helpDeskReport.getFromDate()==null
					|| !helpDeskReport.getToDate().equals("")|| helpDeskReport.getToDate()==null){
				 filters += "\n\nFrom Date : " + helpDeskReport.getFromDate();
				 filters += "\n\nTo Date : " + helpDeskReport.getToDate();
				 if(helpDeskReport.getFromDate().equals(helpDeskReport.getToDate())){
					 whereClause += " AND HR1.REQUEST_DATE LIKE TO_DATE('"+helpDeskReport.getFromDate()+"', 'DD-MM-YYYY') ";
				 }else{
					whereClause += " AND HR1.REQUEST_DATE >= TO_DATE('"+helpDeskReport.getFromDate()+"', 'DD-MM-YYYY') "+
								   " AND HR1.REQUEST_DATE <= TO_DATE('"+helpDeskReport.getToDate()+"', 'DD-MM-YYYY')";
				 }
				}	
				
			
					
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setBodyFontStyle(0);
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellNoWrap(new boolean[]{true});
			filterData.setBlankRowsBelow(1);
			filterData.setBorder(false);
			rgs.addTableToDoc(filterData);
			
			/*String query="SELECT  ROWNUM,REQUEST_TOKEN,case when OFFC1.EMP_TOKEN is null then "+
						 " REQUEST_CLIENT_NAME else "+
						 " OFFC1.EMP_TOKEN ||'-'||( OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME) "+ 
						  " end, HRMS_CENTER.CENTER_NAME,TO_CHAR(HR1.REQUEST_DATE,'DD-MM-YYYY'), DECODE(HR1.REQUEST_APPLIED_FOR,'S','Self','O','Other','C','Client'), " +
						  " DEPT_NAME,REQUEST_TYPE_NAME,REQUEST_SUBTYPE_NAME, "+
						  " REQUEST_SUBJECT, TO_CHAR(HELPDESK_ACTIVITY_LOG.REQUEST_DATE,'DD-MM-YYYY HH24:MI'),FLOOR(NVL(REQUEST_COMPLETION_TIME,0)/60)||' Hrs. '|| MOD(NVL(REQUEST_COMPLETION_TIME,0),60)||' Mins.' "+ 
						  " FROM  HELPDESK_REQUEST_HDR HR1 " +
						  " INNER JOIN HELPDESK_ACTIVITY_LOG ON(HELPDESK_ACTIVITY_LOG.REQUEST_ID = HR1.REQUEST_ID AND REQUEST_STATUS='R') "+
						  " LEFT JOIN HRMS_EMP_OFFC OFFC1 on(OFFC1.EMP_ID=HR1.REQUEST_POSTED_FOR) "+
						  " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=OFFC1.EMP_CENTER) "+
						  " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HR1.REQUEST_APPLIED_TO_DEPT) "+
						  " LEFT JOIN HELPDESK_REQUEST_TYPE ON (HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HR1.REQUEST_TYPE) "+
						  " LEFT JOIN HELPDESK_REQUEST_SUBTYPE ON (HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID = HR1.REQUEST_SUBTYPE) "+
						  " WHERE 1=1 ";*/
			
			String query=" SELECT   ROWNUM,REQUEST_TOKEN,case when OFFC1.EMP_TOKEN is null then " +
						 " REQUEST_CLIENT_NAME else "+
				         " OFFC1.EMP_TOKEN ||'-'||( OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME) "+
						 " end, HRMS_CENTER.CENTER_NAME,TO_CHAR(HR1.REQUEST_DATE,'DD-MM-YYYY'), DECODE(HR1.REQUEST_APPLIED_FOR,'S','Self','O','Other','C','Client'), "+   
				  		 " OFFC2.EMP_TOKEN ||'-'||( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME), "+ 
				  		 " DEPT_NAME,REQUEST_TYPE_NAME,REQUEST_SUBTYPE_NAME,   "+
				  		" DECODE(HR1.REQUEST_APPL_STATUS,'B','Send Back','N','Rejected','D','Draft','C','Closed',HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_NAME), "+
				 		" REQUEST_SUBJECT,  "+
				  		" FLOOR(NVL(REQUEST_COMPLETION_TIME,0)/60)||' Hrs. '|| MOD(NVL(REQUEST_COMPLETION_TIME,0),60)||' Mins.'  ,  "+
				  		" TO_CHAR(REQUEST_COMPLITION_DATE,'DD-MM-YYYY HH24:MI'), FLOOR(NVL(ACTUAL_COMPLITION_TIME,0)/60)||' Hrs. '|| round(MOD(NVL(ACTUAL_COMPLITION_TIME,0),60))||' Mins.' "+   
	   			  		" FROM  HELPDESK_REQUEST_HDR HR1  "+
				 		" LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON(HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV =HR1.REQUEST_APPL_STATUS) "+
				  		" LEFT JOIN HRMS_EMP_OFFC OFFC1 on(OFFC1.EMP_ID=HR1.REQUEST_POSTED_FOR)  "+
				  		" LEFT JOIN HRMS_EMP_OFFC OFFC2 on(OFFC2.EMP_ID=HR1.REQUEST_PENDING_WITH) "+ 
				  		" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=OFFC1.EMP_CENTER) "+
				  		" LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HR1.REQUEST_APPLIED_TO_DEPT) "+
				  		" LEFT JOIN HELPDESK_REQUEST_TYPE ON (HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HR1.REQUEST_TYPE) "+
						" LEFT JOIN HELPDESK_REQUEST_SUBTYPE ON (HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID = HR1.REQUEST_SUBTYPE) "+  
						" WHERE 1=1 ";
			
			 query+= whereClause;
			
			 query +=" order by  REQUEST_TOKEN DESC ";
			 
			 TableDataSet tdstable = new TableDataSet();

				String[] header = { "Sr. No.", "Request Id", "Employee Id-Name", "Branch",
						"Requested On","Requested For","Request Owner ","Request to Department", "Category",
						"Request/Problem Type",
						 "Status","Subject","Process Time","Date","Actual Duration"};
						 
				tdstable.setHeader(header);
				
				
				int[] cellWidth  = {2, 10, 10, 10, 10, 10, 10, 10, 10, 10,10,10,10,20,10};
				tdstable.setCellWidth(cellWidth);
				
				int[] cellAlign = { 1, 0,  0, 0, 1, 0, 0, 0, 0, 0,1,1,0,0,0};
				tdstable.setCellAlignment(cellAlign);

				
			 Object[][] queryData = getSqlModel().getSingleResult(query);
			 int totalRecords=0;
			 
			 if (queryData == null || queryData.length == 0) {
				 totalRecords=0;
					System.out.println("Within If--->No records available");
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "\nNo records available\n\n";

					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });

					noData.setBorder(false);
					rgs.addTableToDoc(noData);
				}else {
					for (int i = 0; i < queryData.length; i++) {
						queryData[i][0] = String.valueOf(i + 1);
					}
					totalRecords=queryData.length;
					System.out.println("In getLeaveMisReport()QeryData Length: "
							+ queryData.length);
					tdstable.setHeaderTable(true);
					tdstable.setHeaderBorderDetail(3);
					tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
					tdstable.setData(queryData); 
					tdstable.setBorderDetail(3);
					rgs.addTableToDoc(tdstable);
					
				}
			 Object[][] sumTimeData =null;
			 if(queryData!=null && queryData.length>0){
			 String sumTime="SELECT SUM(REQUEST_COMPLETION_TIME) from  HELPDESK_REQUEST_HDR where REQUEST_APPL_STATUS in('R','C')";
			  sumTimeData = getSqlModel().getSingleResult(sumTime);
			 }
			 int totalTime=0;
			 String strTotalTime="";
			 if(sumTimeData!=null && sumTimeData.length>0){
				 totalTime = Integer.parseInt(String.valueOf(sumTimeData[0][0]));
				 
				 String timequery = "SELECT Floor("+sumTimeData[0][0]+"/60)||'  Hrs '|| Floor(MOD("+sumTimeData[0][0]+",60))|| ' Mins' from Dual";
					Object[][]timequeryObj=getSqlModel().getSingleResult(timequery);
					strTotalTime =String.valueOf(timequeryObj[0][0]);
			 }
			 String strAvgTime="";
			 double AvgTime=0.0;
			 
			 if(totalTime!=0){
				 AvgTime = totalTime/totalRecords;
				 String timequery = "SELECT Floor("+AvgTime+"/60)||'  Hrs '|| Floor(MOD("+AvgTime+",60))|| ' Mins' from Dual";
					Object[][]timequeryObj=getSqlModel().getSingleResult(timequery);
					 
					strAvgTime= String.valueOf(timequeryObj[0][0]);
			 }
			 String footerData ="Total Records : " + totalRecords;
			 footerData +="\t\t\t\t\t\t                     Total Time  :" + strTotalTime;
			 footerData += "\t\t\t\t\t\t\t                        Avg Time  : " +strAvgTime;
			 
			 TableDataSet totalEmp = new TableDataSet();
				totalEmp.setData(new Object[][] { { footerData }});
				totalEmp.setCellAlignment(new int[] { 0 });
				totalEmp.setCellWidth(new int[] { 100 });
				totalEmp.setBorderDetail(0);
				totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
				totalEmp.setBodyFontStyle(1);
				totalEmp.setBlankRowsAbove(1);
				rgs.addTableToDoc(totalEmp);
				
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

