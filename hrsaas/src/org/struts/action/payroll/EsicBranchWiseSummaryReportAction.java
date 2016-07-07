package org.struts.action.payroll;

import org.paradyne.bean.payroll.EsicBranchWiseSummaryReport;
import org.paradyne.model.payroll.EsicBranchWiseSummaryReportModel;
import org.struts.lib.ParaActionSupport;

public class EsicBranchWiseSummaryReportAction extends ParaActionSupport {
	
	EsicBranchWiseSummaryReport esicBean = null;
	
	public final void prepare_local() throws Exception {
		esicBean = new EsicBranchWiseSummaryReport();
		esicBean.setMenuCode(2088);
	}

	public final Object getModel() {
		return esicBean;
	}
	
	/* This method displays the default page mapped.
	 * input
	 */
	public final String input(){
		return INPUT;
	}
	
	/** This method displays the available division to select.
	 * @return
	 * @throws Exception
	 */
	public final String f9division() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' '), NVL(DIV_ABBR,' ') FROM HRMS_DIVISION  ";
		
		
		if(esicBean.getUserProfileDivision() !=null && esicBean.getUserProfileDivision().length()>0) {
			query+= " WHERE DIV_ID IN ("+esicBean.getUserProfileDivision() +")";
		} 
			query+= " ORDER BY  DIV_ID ";
	 
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("division.code"),getMessage("division") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divId", "divName", "divAbbr"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9div
	
	/**This function is used to generate report when report button is clicked.
	 *
	 */
	
	public final void report(){
		try {
			EsicBranchWiseSummaryReportModel model = new EsicBranchWiseSummaryReportModel();
			model.initiate(context, session);
			String reportPath = "";
			model.generateReport(esicBean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** This function is used to mail the report generated.
	 * @return - view
	 */
	public final String mailReport(){
		try {
			EsicBranchWiseSummaryReportModel model = new EsicBranchWiseSummaryReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/PayrollReport" + poolName + "/";
			model.generateReport(esicBean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	/** This method resets the fields on the form
	 * @return INPUT
	 */
	public final String reset(){
		esicBean.setDivId("");
		esicBean.setDivName("");
		esicBean.setYear("");
		esicBean.setMonth("");
		return input();
	}


}
