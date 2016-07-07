package org.paradyne.model.conference;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.conference.ConferenceBookingMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Tarun Chaturvedi
 * @date 03-03-2008
 * ConferenceBookingMisReportModel class to write business logic to generate the MIS report
 */
public class ConferenceBookingMisReportModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConferenceBookingMisReportModel.class);
	
	/* method name : confBookMISReport 
	 * purpose     : to create the MIS report for Conference
	 * return type : boolean
	 * parameter   : ConferenceBookingMisReport instance, HttpServletResponse response
	 */
	public boolean confBookMISReport(ConferenceBookingMisReport bean, HttpServletResponse response){
		logger.info("inside model report" );
		
		String name = "Conference Booking MIS Report";
		String type = "Pdf";
		String title = "Conference Booking MIS Report";
		
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,title);
		
     	rg = getHeader(rg,bean);	
     	
		rg.createReport(response);
		
		return false;
	}
	
	/* method name : getHeader 
	 * purpose     : to configure the report parameters and create a pdf file for report
	 * return type : ReportGenerator
	 * parameter   : ReportGenerator rg, ConferenceBookingMisReport instance
	 */
	public ReportGenerator getHeader(ReportGenerator rg, ConferenceBookingMisReport bean){
		String query = getQuery(1);
		logger.info("query-----"+query);
		
		String empName  	= bean.getEmployeeName();
		String fromDate 	= bean.getFromDate();
		String toDate   	= bean.getToDate();
		String status       = bean.getStatus();
		
		Object[][] heading  = null;
		
		int [] bcellWidth	= {15,25,10,25};
		int [] bcellAlign	= {0,0,0,0};
		String header		= "Conference Booking MIS Report";
		String fullStatus	= "";
		
		String query1  		= "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object [][]today 	= getSqlModel().getSingleResult(query1);
		String date 		= "Date : "+(String)today[0][0];
		
		if(status.equals("P")){
			fullStatus = "Pending";
		}	//end of if
		else if(status.equals("A")){
			fullStatus = "Approved";
		}	//end of else if
		else{
			fullStatus = "Rejected";
		}	//end of else
		
	
		logger.info("empName-----"+empName);
		logger.info("fromDate-----"+fromDate);
		logger.info("toDate-----"+toDate);
		logger.info("status-----"+status);
		
		if(!empName.equals("") && !fromDate.equals("") && !toDate.equals("") && !status.equals("")){
			logger.info("Employee && from date && to date && status");
			heading = new Object[2][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[0][2] = "Status : ";
			heading[0][3] = fullStatus;
			heading[1][0] = "From Date : ";
			heading[1][1] = bean.getFromDate();
			heading[1][2] = "To Date : ";
			heading[1][3] = bean.getToDate();
			
			query += "WHERE CONF_BOOKEDBY = "+bean.getEmployeeCode()+" AND CONF_DATE BETWEEN TO_DATE('"
					  +bean.getFromDate()+"', 'DD-MM-YYYY') AND TO_DATE ('"+bean.getToDate()+"', 'DD-MM-YYYY') AND "
					  +"CONF_STATUS = '"+bean.getStatus()+"'";
		}	//end of if
		
		else if(!empName.equals("") && !fromDate.equals("") && !toDate.equals("")){
			logger.info("Employee && from date && to date");
			heading = new Object[2][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[1][0] = "From Date : ";
			heading[1][1] = bean.getFromDate();
			heading[1][2] = "To Date : ";
			heading[1][3] = bean.getToDate();
			
			query += "WHERE CONF_BOOKEDBY = "+bean.getEmployeeCode()+" AND CONF_DATE BETWEEN TO_DATE('"
					  +bean.getFromDate()+"', 'DD-MM-YYYY') AND TO_DATE ('"+bean.getToDate()+"', 'DD-MM-YYYY')";
		}	//end of else if
		
		else if(!empName.equals("") && !fromDate.equals("") && !status.equals("")){
			logger.info("Employee && from date && status");
			heading = new Object[2][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[0][2] = "Status : ";
			heading[0][3] = fullStatus;
			heading[1][0] = "From Date : ";
			heading[1][1] = bean.getFromDate();
			
			query += "WHERE CONF_BOOKEDBY = "+bean.getEmployeeCode()+" AND CONF_DATE >= TO_DATE('"
					  +bean.getFromDate()+"', 'DD-MM-YYYY') AND CONF_STATUS = '"+bean.getStatus()+"'";
		}	//end of else if
		
		else if(!empName.equals("") && !toDate.equals("") && !status.equals("")){
			logger.info("Employee && to date && status");
			heading = new Object[2][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[0][2] = "Status : ";
			heading[0][3] = fullStatus;
			heading[1][0] = "Up to Date : ";
			heading[1][1] = bean.getToDate();
			
			query += "WHERE CONF_BOOKEDBY = "+bean.getEmployeeCode()+" AND CONF_DATE <= TO_DATE('"
					  +bean.getToDate()+"', 'DD-MM-YYYY') AND "+"CONF_STATUS = '"+bean.getStatus()+"'";
		}	//end of else if
		
		else if(!fromDate.equals("") && !toDate.equals("") && !status.equals("")){
			logger.info("from date && to date && status");
			heading = new Object[2][4];
			bcellWidth [0]= 10;
			heading[0][0] = "From Date : ";
			heading[0][1] = bean.getFromDate();
			heading[0][2] = "To Date : ";
			heading[0][3] = bean.getToDate();
			heading[1][0] = "Status : ";
			heading[1][1] = fullStatus;
			
			query += "WHERE CONF_DATE BETWEEN TO_DATE('"+bean.getFromDate()+"', 'DD-MM-YYYY') AND TO_DATE ('"
					  +bean.getToDate()+"', 'DD-MM-YYYY') AND "+"CONF_STATUS = '"+bean.getStatus()+"'";
		}	//end of else if
		
		else if(!empName.equals("") && !fromDate.equals("") ){
			logger.info("Employee && from date ");
			heading = new Object[1][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[0][2] = "From Date : ";
			heading[0][3] = bean.getFromDate();
			
			query += "WHERE CONF_BOOKEDBY = "+bean.getEmployeeCode()+" AND CONF_DATE >= TO_DATE('"
					  +bean.getFromDate()+"', 'DD-MM-YYYY') " ;
		}	//end of else if
		
		else if(!empName.equals("") && !toDate.equals("") ){
			logger.info("Employee && to date");
			heading = new Object[1][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[0][2] = "Up to Date : ";
			heading[0][3] = bean.getToDate();
			
			query += "WHERE CONF_BOOKEDBY = "+bean.getEmployeeCode()+" AND CONF_DATE <= TO_DATE('"
					  +bean.getToDate()+"', 'DD-MM-YYYY') ";
		}	//end of else if
		
		else if(!empName.equals("") && !status.equals("")){
			logger.info("Employee && status");
			heading = new Object[1][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[0][2] = "Status : ";
			heading[0][3] = fullStatus;
			
			query += "WHERE CONF_BOOKEDBY = "+bean.getEmployeeCode()+" AND CONF_STATUS = '"+bean.getStatus()+"'";
		}	//end of else if
		
		else if(!fromDate.equals("") && !toDate.equals("")){
			logger.info("from date && to date");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "From Date: ";
			heading[0][1] = bean.getFromDate();
			heading[0][2] = "To Date : ";
			heading[0][3] = bean.getToDate();
			
			query += "WHERE CONF_DATE BETWEEN TO_DATE('"+bean.getFromDate()+"', 'DD-MM-YYYY') AND TO_DATE ('"
					  +bean.getToDate()+"', 'DD-MM-YYYY') ";
		}	//end of else if
		
		else if(!fromDate.equals("") && !status.equals("")){
			logger.info("from date && status");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "From Date : ";
			heading[0][1] = bean.getFromDate();
			heading[0][2] = "Status : ";
			heading[0][3] = fullStatus;
			
			query += "WHERE CONF_DATE >= TO_DATE('"+bean.getFromDate()+"', 'DD-MM-YYYY') AND CONF_STATUS = '"+bean.getStatus()+"'";
		}	//end of else if
		
		else if(!toDate.equals("") && !status.equals("")){
			logger.info("to date && status");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "Up to Date : ";
			heading[0][1] = bean.getToDate();
			heading[0][2] = "Status : ";
			heading[0][3] = fullStatus;
			
			query += "WHERE CONF_DATE <= TO_DATE('"+bean.getToDate()+"', 'DD-MM-YYYY') AND CONF_STATUS = '"+bean.getStatus()+"'";
		}	//end of else if
		
		else if(!empName.equals("")){
			logger.info("Employee");
			heading = new Object[1][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			
			query += "WHERE CONF_BOOKEDBY = "+bean.getEmployeeCode();
		}	//end of else if
		
		else if(!fromDate.equals("")){
			logger.info("from date");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "From Date : ";
			heading[0][1] = bean.getFromDate();
			
			query += "WHERE CONF_DATE >= TO_DATE('"+bean.getFromDate()+"', 'DD-MM-YYYY') ";
		}	//end of else if
		
		else if(!toDate.equals("")){
			logger.info("to date");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "Up to Date : ";
			heading[0][1] = bean.getToDate();
			
			query += "WHERE CONF_DATE <= TO_DATE('"+bean.getToDate()+"', 'DD-MM-YYYY')";
		}	//end of else if
		
		else if(!status.equals("")){
			logger.info("status");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "Status : ";
			heading[0][1] = fullStatus;
			
			query += "WHERE CONF_STATUS = '"+bean.getStatus()+"'";
		}	//end of else if
		
		query += " ORDER BY CONF_DATE DESC";
		
		logger.info("in getHeader----------------------------"+query);
		logger.info("heading----------------------------"+heading[0][0]);
		
		rg.addFormatedText(header, 6, 0, 1, 0);
		rg.addText(date, 0, 2, 0);
		rg.addText("\n", 0, 0, 0);
		rg.tableBodyNoBorder(heading,bcellWidth,bcellAlign);
		
		
		Object[][] result = getSqlModel().getSingleResult(query);
		logger.info("result.length " + result.length);
		
		Object[][] confBookingData  = new Object[result.length][8];
		logger.info("confBookingData.length " + confBookingData.length);
		
	        if(result!=null && result.length!=0){
	        	for (int i = 0; i < result.length; i++) {
	        		confBookingData[i][0] = i+1;
	        		confBookingData[i][1] = checkNull(String.valueOf(result[i][0]));
	        		confBookingData[i][2] = checkNull(String.valueOf(result[i][1]));
	        		confBookingData[i][3] = checkNull(String.valueOf(result[i][2]));
	        		confBookingData[i][4] = checkNull(String.valueOf(result[i][3]));
	        		confBookingData[i][5] = checkNull(String.valueOf(result[i][4]));
	        		confBookingData[i][6] = checkNull(String.valueOf(result[i][5]));
	        		confBookingData[i][7] = checkNull(String.valueOf(result[i][6]));
	        	}	//end of for loop
	        	
	        	String colNames[] = { "Sr. No.", "Booked By",	"Date", "From Time", "To Time", "Conference Room", 
	        							"Purpose", "No of Participants"};
	        	
				int[] cellwidth2 = {5, 20, 10, 10, 8, 20, 13, 14};
				int [] alignment = {1, 0, 1, 1, 1, 0, 0, 1};
	
			    try {
			    	rg.addText("\n", 0, 0, 0);
			    	rg.tableBody(colNames, confBookingData, cellwidth2, alignment);
				} catch (Exception e) {
					//e.printStackTrace();
					logger.error(e);
				}
	        }	//end of if
	        else{
	        	rg.addText("\n", 0, 0, 0);
	        	rg.addText("\n", 0, 0, 0);
	        	rg.addText("There is no record to display", 1, 1, 1);
	        }	//end of else 
		
		
		return rg;
	}
	
	/* method name : checkNull 
	 * purpose     : to check whether the value of the string parameter is null or not.
	 * 					if yes then it will set the value of that string to blank
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}	//end of if
		else {
			return result;
		}	//end of else
	}

	public void setEmpDetails(ConferenceBookingMisReport confBookMisReport) {
		// TODO Auto-generated method stub
		String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID "
			+"FROM HRMS_EMP_OFFC "
			//+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
			+" WHERE EMP_STATUS ='S' AND EMP_ID = "+confBookMisReport.getUserEmpId()+" ORDER BY HRMS_EMP_OFFC.EMP_ID"	;
		Object[][] obj = getSqlModel().getSingleResult(query);
		if(obj!= null || obj.length != 0){
			confBookMisReport.setEmployeeToken(checkNull(String.valueOf(obj[0][0])));
			confBookMisReport.setEmployeeName(checkNull(String.valueOf(obj[0][1])));
			confBookMisReport.setEmployeeCode(checkNull(String.valueOf(obj[0][2])));
		}
	}
}
