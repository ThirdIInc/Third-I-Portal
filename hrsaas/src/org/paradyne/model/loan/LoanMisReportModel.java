/**
 * 
 */
package org.paradyne.model.loan;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Loan.LoanMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author mangeshj
 * @date 21-07-2008
 * LoanIndexModel class to write business logic to generate the loan MIS report
 */
public class LoanMisReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoanMisReportModel.class);
	
	/* method name : loanMisReport 
	 * purpose     : to create the loan MIS report as per the selected criteria
	 * return type : boolean
	 * parameter   : LoanMisReport bean, HttpServletResponse response
	 */
	public boolean loanMisReport(LoanMisReport bean, HttpServletResponse response){
		logger.info("inside model report" );
		String name = "Loan MIS Report";
		String type = "Pdf";
		String title = "Loan MIS Report";
		
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,title);
		//rg.genHeader("Conference Booking");
     	rg = getHeader(rg,bean);	
		rg.createReport(response);
		return false;
	}
	
	/* method name : getHeader 
	 * purpose     : to create the loan MIS report as per the selected criteria
	 * return type : ReportGenerator
	 * parameter   : ReportGenerator rg, LoanMisReport bean
	 */
	public ReportGenerator getHeader(ReportGenerator rg, LoanMisReport bean){
		String query = getQuery(1);
		logger.info("query-----"+query);
		
		String empName  	= bean.getEmployeeName();
		String fromDate 	= bean.getFromDate();
		String toDate   	= bean.getToDate();
		String status       = bean.getStatus();
		
		Object[][] heading  = null;
		
		int [] bcellWidth	= {15,25,10,25};
		int [] bcellAlign	= {0,0,0,0};
		String header		= "Loan MIS Report";
		String fullStatus	= "";
		
		String query1  		= "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object [][]today 	= getSqlModel().getSingleResult(query1);
		String date 		= "Date : "+(String)today[0][0];
		
		if(status.equals("P")){
			fullStatus = "Pending";
		}	//end of if
		else if(status.equals("A")){
			fullStatus = "Approved";
		}	//end of elseif
		else{
			fullStatus = "Rejected";
		}	//end of if
		
	
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
			
			query += "WHERE LOAN_EMP_ID = "+bean.getEmployeeCode()+" AND LOAN_APPL_DATE BETWEEN TO_DATE('"
					  +bean.getFromDate()+"', 'DD-MM-YYYY') AND TO_DATE ('"+bean.getToDate()+"', 'DD-MM-YYYY') AND "
					  +"LOAN_APPL_STATUS = '"+bean.getStatus()+"'";
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
			
			query += "WHERE LOAN_EMP_ID = "+bean.getEmployeeCode()+" AND LOAN_APPL_DATE BETWEEN TO_DATE('"
					  +bean.getFromDate()+"', 'DD-MM-YYYY') AND TO_DATE ('"+bean.getToDate()+"', 'DD-MM-YYYY')";
		}	//end of elseif
		
		else if(!empName.equals("") && !fromDate.equals("") && !status.equals("")){
			logger.info("Employee && from date && status");
			heading = new Object[2][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[0][2] = "Status : ";
			heading[0][3] = fullStatus;
			heading[1][0] = "From Date : ";
			heading[1][1] = bean.getFromDate();
			
			query += "WHERE LOAN_EMP_ID = "+bean.getEmployeeCode()+" AND LOAN_APPL_DATE >= TO_DATE('"
					  +bean.getFromDate()+"', 'DD-MM-YYYY') AND LOAN_APPL_STATUS = '"+bean.getStatus()+"'";
		}	//end of elseif
		
		else if(!empName.equals("") && !toDate.equals("") && !status.equals("")){
			logger.info("Employee && to date && status");
			heading = new Object[2][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[0][2] = "Status : ";
			heading[0][3] = fullStatus;
			heading[1][0] = "Up to Date : ";
			heading[1][1] = bean.getToDate();
			
			query += "WHERE LOAN_EMP_ID = "+bean.getEmployeeCode()+" AND LOAN_APPL_DATE <= TO_DATE('"
					  +bean.getToDate()+"', 'DD-MM-YYYY') AND "+"LOAN_APPL_STATUS = '"+bean.getStatus()+"'";
		}	//end of elseif
		
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
			
			query += "WHERE LOAN_APPL_DATE BETWEEN TO_DATE('"+bean.getFromDate()+"', 'DD-MM-YYYY') AND TO_DATE ('"
					  +bean.getToDate()+"', 'DD-MM-YYYY') AND "+"LOAN_APPL_STATUS = '"+bean.getStatus()+"'";
		}	//end of elseif
		
		else if(!empName.equals("") && !fromDate.equals("") ){
			logger.info("Employee && from date ");
			heading = new Object[1][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[0][2] = "From Date : ";
			heading[0][3] = bean.getFromDate();
			
			query += "WHERE LOAN_EMP_ID = "+bean.getEmployeeCode()+" AND LOAN_APPL_DATE >= TO_DATE('"
					  +bean.getFromDate()+"', 'DD-MM-YYYY') " ;
		}	//end of elseif
		
		else if(!empName.equals("") && !toDate.equals("") ){
			logger.info("Employee && to date");
			heading = new Object[1][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[0][2] = "Up to Date : ";
			heading[0][3] = bean.getToDate();
			
			query += "WHERE LOAN_EMP_ID = "+bean.getEmployeeCode()+" AND LOAN_APPL_DATE <= TO_DATE('"
					  +bean.getToDate()+"', 'DD-MM-YYYY') ";
		}	//end of elseif
		
		else if(!empName.equals("") && !status.equals("")){
			logger.info("Employee && status");
			heading = new Object[1][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			heading[0][2] = "Status : ";
			heading[0][3] = fullStatus;
			
			query += "WHERE LOAN_EMP_ID = "+bean.getEmployeeCode()+" AND LOAN_APPL_STATUS = '"+bean.getStatus()+"'";
		}	//end of elseif
		
		else if(!fromDate.equals("") && !toDate.equals("")){
			logger.info("from date && to date");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "From Date: ";
			heading[0][1] = bean.getFromDate();
			heading[0][2] = "To Date : ";
			heading[0][3] = bean.getToDate();
			
			query += "WHERE LOAN_APPL_DATE BETWEEN TO_DATE('"+bean.getFromDate()+"', 'DD-MM-YYYY') AND TO_DATE ('"
					  +bean.getToDate()+"', 'DD-MM-YYYY') ";
		}	//end of elseif
		
		else if(!fromDate.equals("") && !status.equals("")){
			logger.info("from date && status");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "From Date : ";
			heading[0][1] = bean.getFromDate();
			heading[0][2] = "Status : ";
			heading[0][3] = fullStatus;
			
			query += "WHERE LOAN_APPL_DATE >= TO_DATE('"+bean.getFromDate()+"', 'DD-MM-YYYY') AND LOAN_APPL_STATUS = '"+bean.getStatus()+"'";
		}	//end of elseif
		
		else if(!toDate.equals("") && !status.equals("")){
			logger.info("to date && status");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "Up to Date : ";
			heading[0][1] = bean.getToDate();
			heading[0][2] = "Status : ";
			heading[0][3] = fullStatus;
			
			query += "WHERE LOAN_APPL_DATE <= TO_DATE('"+bean.getToDate()+"', 'DD-MM-YYYY') AND LOAN_APPL_STATUS = '"+bean.getStatus()+"'";
		}	//end of elseif
		
		else if(!empName.equals("")){
			logger.info("Employee");
			heading = new Object[1][4];
			heading[0][0] = "Employee Name : ";
			heading[0][1] = bean.getEmployeeName();
			
			query += "WHERE LOAN_EMP_ID = "+bean.getEmployeeCode();
		}	//end of elseif
		
		else if(!fromDate.equals("")){
			logger.info("from date");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "From Date : ";
			heading[0][1] = bean.getFromDate();
			
			query += "WHERE LOAN_APPL_DATE >= TO_DATE('"+bean.getFromDate()+"', 'DD-MM-YYYY') ";
		}	//end of elseif
		
		else if(!toDate.equals("")){
			logger.info("to date");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "Up to Date : ";
			heading[0][1] = bean.getToDate();
			
			query += "WHERE LOAN_APPL_DATE <= TO_DATE('"+bean.getToDate()+"', 'DD-MM-YYYY')";
		}	//end of elseif
		
		else if(!status.equals("")){
			logger.info("status");
			bcellWidth [0]= 10;
			heading = new Object[1][4];
			heading[0][0] = "Status : ";
			heading[0][1] = fullStatus;
			
			query += "WHERE LOAN_APPL_STATUS = '"+bean.getStatus()+"'";
		}	//end of elseif
			
		query += " ORDER BY LOAN_APPL_DATE DESC";
		logger.info("in getHeader----------------------------"+query);
		logger.info("heading----------------------------"+heading[0][0]);
		
		rg.addFormatedText(header, 6, 0, 1, 0);
		rg.addText(date, 0, 2, 0);
		rg.addText("\n", 0, 0, 0);
		rg.tableBodyNoBorder(heading,bcellWidth,bcellAlign);
		
		
		Object[][] result = getSqlModel().getSingleResult(query);
		logger.info("result.length " + result.length);
		
		Object[][] loanData  = new Object[result.length][7];
		logger.info("loanDataData.length " + loanData.length);
		
		//rg.addFormatedText(header, 6, 0, 1, 0);
		//rg.addText(date, 0, 2, 0);
		//rg.addText("\n", 0, 0, 0);
		//rg.addFormatedText(heading, 4, 0, 0, 0);
	        if(result!=null && result.length!=0){
	        	for (int i = 0; i < result.length; i++) {
	        		loanData[i][0] = i+1;
	        		loanData[i][1] = checkNull(String.valueOf(result[i][0]));
	        		loanData[i][2] = checkNull(String.valueOf(result[i][1]));
	        		loanData[i][3] = checkNull(String.valueOf(result[i][2]));
	        		loanData[i][4] = checkNull(String.valueOf(result[i][3]));
	        		loanData[i][5] = checkNull(String.valueOf(result[i][4]));
	        		loanData[i][6] = checkNull(String.valueOf(result[i][5]));
	        		//loanData[i][7] = checkNull(String.valueOf(result[i][6]));
	        	}	//end of for loop
	        	String colNames[] = { "Sr. No.", "Employee Name", "Applied Date", "Loan Type", "Status", "Loan Amount", 
	        							"Sanction Amount"};
				//String colNames2[] = { "Sr. No.", "Accessory Name",	"Quantity"};
				int[] cellwidth2 = {8, 20, 12, 15, 10, 15, 20};
				int [] alignment = {1, 0, 1, 0, 0, 1, 1};
	
			    try {
			    	rg.addText("\n", 0, 0, 0);
			    	rg.tableBody(colNames, loanData, cellwidth2, alignment);
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
}
