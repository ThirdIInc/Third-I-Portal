package org.struts.action.conference;

import org.paradyne.bean.conference.ConferenceBookingMisReport;
import org.paradyne.model.conference.ConferenceBookingMisReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Tarun Chaturvedi
 * @date 03-03-2008
 * ConferenceBookingMisReportAction class to generate the MIS report
 * as per the selected filters
 */
public class ConferenceBookingMisReportAction extends ParaActionSupport {

	ConferenceBookingMisReport confBookMisReport;
	
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		confBookMisReport = new ConferenceBookingMisReport();
		confBookMisReport.setMenuCode(525);
	}
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConferenceBookingMisReportAction.class);

	public Object getModel() {
		// TODO Auto-generated method stub
		return confBookMisReport;
	}
	
	public ConferenceBookingMisReport getConfBookMisReport() {
		return confBookMisReport;
	}

	public void setConfBookMisReport(ConferenceBookingMisReport confBookMisReport) {
		this.confBookMisReport = confBookMisReport;
	}
	
	public String input() throws Exception {
		
		if(confBookMisReport.isGeneralFlag()){
			ConferenceBookingMisReportModel model = new ConferenceBookingMisReportModel();
			model.initiate(context, session);
			model.setEmpDetails(confBookMisReport);
			model.terminate();
		}
		
		return SUCCESS;
	}
	

	/* method name : reset 
	 * purpose     : to reset all the form fields to blank values
	 * return type : String
	 * parameter   : none
	 */
	public String reset(){
		logger.info("in side reset method");
		if(!confBookMisReport.isGeneralFlag()){
			confBookMisReport.setEmployeeCode("");
			confBookMisReport.setEmployeeToken("");
			confBookMisReport.setEmployeeName("");
		}
		confBookMisReport.setFromDate("");
		confBookMisReport.setToDate("");
		confBookMisReport.setStatus("");
		
		return "success";
	}
	
	/* method name : report 
	 * purpose     : to generate the report as per the selected filters
	 * return type : String
	 * parameter   : none
	 */
	public void report(){
		logger.info("in side report method");
		ConferenceBookingMisReportModel model = new ConferenceBookingMisReportModel();
		model.initiate(context, session);
		logger.info("Emp Code in Action "+confBookMisReport.getEmployeeCode());
		model.confBookMISReport(confBookMisReport, response);
		model.terminate();
	}
	
	/* method name : f9actionEmployeeName 
	 * purpose     : to select an employee name
	 * return type : String
	 * parameter   : none
	 */
	public String f9actionEmployeeName(){
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID "
						+"FROM HRMS_EMP_OFFC ";
						//+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
						
						query += getprofileQuery(confBookMisReport);
						query +=" AND EMP_STATUS ='S' ORDER BY HRMS_EMP_OFFC.EMP_ID"	;	
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		//String []headers ={"Employee ID","Employee Name"};
		
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				 };
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String []headerwidth={"40","60"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String []fieldNames={"employeeToken", "employeeName", "employeeCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int []columnIndex={0,1, 2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlage ="false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		
		return "f9page";
		
	}

	
}
