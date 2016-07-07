package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalEligibleEmpReport;
import org.paradyne.model.PAS.AppraisalEligibleEmpReportModel;
import org.paradyne.model.PAS.AppraisalReportModel;
import org.paradyne.model.admin.srd.QualificationMisReportModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalEligibleEmpReportAction extends ParaActionSupport {

	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppraisalEligibleEmpReportAction.class);
	AppraisalEligibleEmpReport bean = null;
	
	public void prepare_local() throws Exception {
		bean = new AppraisalEligibleEmpReport();
		bean.setMenuCode(836);
	}

	public Object getModel() {
		return bean;
	}
	
	public void setAppraisalEligibleEmpReport(AppraisalEligibleEmpReport appraisalEligibleEmpReport) {
		this.bean = appraisalEligibleEmpReport;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception 
	{
		AppraisalEligibleEmpReportModel model = new AppraisalEligibleEmpReportModel();
  		model.initiate(context, session);
  		model.terminate();
	}

	public String input() 
	{
		getNavigationPanel(1);
		return INPUT;
	}
	
	/*public String getReport()
	{
		AppraisalEligibleEmpReportModel model = new AppraisalEligibleEmpReportModel();
		model.initiate(context, session);
		model.getReport(request, response, bean);
		model.terminate();
		return null;
	}*/
	
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
		String[] fieldNames = { "sAppCode", "sAppStartDate", "sAppEndDate","sAppId" };

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

	
	// Added by Tinshuk Banafar...Begin...
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 * @throws Exception
	 */

	public final String getReport() throws Exception {
		AppraisalEligibleEmpReportModel model = new AppraisalEligibleEmpReportModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getEligibleEmpReport(bean, request, response, reportPath);
		model.terminate();
		return null;
	}
	/**
	 * THIS METHOD IS USED FOR GENERATING E-MAIL REPORT
	 * 
	 * @return String
	 * @throws Exception
	 */

	public final String mailReport() {
		try {
		    final AppraisalEligibleEmpReportModel model = new AppraisalEligibleEmpReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			String reportPath = getText("data_path") + "\\Report\\Master"
					+ poolName + "\\";
			model.getEligibleEmpReport(bean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	// Added by Tinshuk Banafar...End...
	
}
