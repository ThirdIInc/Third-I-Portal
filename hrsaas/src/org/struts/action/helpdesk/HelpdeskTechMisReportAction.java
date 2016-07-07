package org.struts.action.helpdesk;

import org.paradyne.bean.helpdesk.HelpDeskTechMISReport;
import org.paradyne.model.helpdesk.HelpdeskTechMISReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pramila Naik
 * @date 30-03-12
 * @description HelpdeskTechMisReportModel class serves as model for help desk application
 *              form to write business logic to generate the MIS Report for technical team how many time process the 
 *              Request Catagory
 */

public class HelpdeskTechMisReportAction extends ParaActionSupport {

	
	HelpDeskTechMISReport helpDeskReport;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HelpdeskTechMisReportAction.class);
	
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		helpDeskReport = new HelpDeskTechMISReport();
		helpDeskReport.setMenuCode(5008);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return helpDeskReport;
	}
	
	
	/* method name : reset 
	 * purpose     : to reset all the form fields to blank values
	 * return type : String
	 * parameter   : none
	 */
	public String reset(){
		helpDeskReport.setFromDate("");
		helpDeskReport.setToDate("");
		
		return "success";
	}
	
	/* method name : RequestAnalysisReport 
	 * purpose     : to generate the report as per the selected filters
	 * return type : null
	 * parameter   : none
	 */
	public void RequestAnalysisReport(){
		logger.info("in action's RequestAnalysisReport method");
		
		HelpdeskTechMISReportModel model = new HelpdeskTechMISReportModel();
		
		model.initiate(context, session);
		model.RequestAnalysisReport(helpDeskReport, response,request,"");
		model.terminate();
		
	}
	/** @return String 
	 * 
	 */
	public String TechTeamReport(){
		logger.info("in action's RequestAnalysisReport method");
			
		return "techReport";
		
	}
	/**To email the report
	 * 
	 * @return String
	 */
	public final String mailReport() {
		try {
		    final HelpdeskTechMISReportModel model = new HelpdeskTechMISReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath = getText("data_path") + "/Report/Master"
					+ poolName + "/";
			model.RequestAnalysisReport(helpDeskReport,response, request, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
}
