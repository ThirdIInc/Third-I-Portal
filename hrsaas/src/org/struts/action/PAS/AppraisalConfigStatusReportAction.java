package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalConfigStatusReport;
import org.paradyne.model.PAS.AppraisalConfigReportModel;
import org.paradyne.model.PAS.AppraisalConfigStatusReportModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalConfigStatusReportAction extends ParaActionSupport {

	
	AppraisalConfigStatusReport apprConfigStatus;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		apprConfigStatus = new AppraisalConfigStatusReport();
		apprConfigStatus.setMenuCode(883);
			
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apprConfigStatus;
	}

	public AppraisalConfigStatusReport getApprConfigStatus() {
		return apprConfigStatus;
	}

	public void setApprConfigStatus(AppraisalConfigStatusReport apprConfigStatus) {
		this.apprConfigStatus = apprConfigStatus;
	}
	
	public String input() 
	{
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String f9SelectAppraisal() throws Exception 
	{
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID " +
					   " FROM PAS_APPR_CALENDAR WHERE APPR_ID IN (SELECT APPR_ID FROM PAS_APPR_ELIGIBLE_EMP )ORDER BY APPR_ID ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { "Appraisal Code", "Start Date", "End Date" };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "20", "30", "30" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "apprCode", "appStartDate", "appEndDate","apprId" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	public void getReport(){
		
		AppraisalConfigStatusReportModel model = new AppraisalConfigStatusReportModel();
		model.initiate(context, session);
		model.getReport(request, response, apprConfigStatus);
		model.terminate();
		
	}

	public String genReport()throws Exception{
		
		AppraisalConfigStatusReportModel model=new AppraisalConfigStatusReportModel();
			model.initiate(context, session);
			model.genReport(apprConfigStatus,request,response,"");
			model.terminate();
			getNavigationPanel(1);
			return null;
		}
	public final String mailReport() {
		
		try {
			AppraisalConfigStatusReportModel model = new AppraisalConfigStatusReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath = getText("data_path") + "/Report/Master"
					+ poolName + "/";
			model.genReport(apprConfigStatus,request,response,reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}	
}
