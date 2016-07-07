package org.struts.action.PAS;
/*
 * @saipavan voleti
 * Date:20-06-2008
 */

import org.paradyne.bean.PAS.AppraisalStatistic;
import org.paradyne.model.PAS.AppraisalStatisticModel;
import org.paradyne.model.PAS.AppraisalStatusReportModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalStatisticAction extends ParaActionSupport {
	// class for Generating Statistical report based on branch and department and organization and designation wise.


	AppraisalStatistic appStat;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		this.appStat =new AppraisalStatistic();
		appStat.setMenuCode(844); 
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		
		return appStat;
	}

	public AppraisalStatistic getAppStat() {
		return appStat;
	}

	public void setAppStat(AppraisalStatistic appStat) {
		this.appStat = appStat;
	}

	
	/** 
	 * @return String after resetting all the fields
	 */
	public String reset() {
		
		appStat.setApprCode("");
		appStat.setApprId("");
		appStat.setApprFrom("");
		appStat.setApprTo("");
		appStat.setTemplate("");
		appStat.setTemplateId("");
		appStat.setAppstat("");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String getAppraisalDtls()throws Exception{
		
		AppraisalStatisticModel model=new AppraisalStatisticModel();
		model.initiate(context, session);
		model.getAppraisalDtls(appStat);
		model.terminate();
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	/**
	 * @return String after Generating report
	 */
	public String report() {
		AppraisalStatisticModel model =new AppraisalStatisticModel();
	   model.initiate(context, session);
	 	   model.getReport(request, response, appStat);
	   model.terminate();
		
	   getNavigationPanel(1);
		return null;
	}
	
	/**
	 * @return String after setting the appraisal period. 
	 */
public String f9AppCal()throws Exception{
		
		String query = " SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),PAS_APPR_CALENDAR.APPR_ID "
					  +" FROM PAS_APPR_CALENDAR "
					  //+" INNER JOIN PAS_APPR_TEMPLATE ON(PAS_APPR_TEMPLATE.APPR_ID=PAS_APPR_CALENDAR.APPR_ID)"					  
					  +" ORDER BY APPR_ID";

		
		String[] headers = { getMessage("appraisal.code"),getMessage("appraisal.from"),getMessage("appraisal.to")};
		//getMessage("appraisal.temp")

		
		String[] headerWidth = { "50","25","25" };

		String[] fieldNames = { "apprCode","apprFrom","apprTo","apprId"};

		int[] columnIndex = { 0,1,2,3 };

		String submitFlag = "true";

		String submitToMethod = "AppraisalStatistic_getAppraisalDtls.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		
		return "f9page";
	}


//Added by Tinshuk Banafar...Begin...
/**
 * THIS METHOD IS USED FOR GENERATING REPORT
 * 
 * @return String
 * @throws Exception
 */

public final String getReport() throws Exception {
	AppraisalStatisticModel model =new AppraisalStatisticModel();
	model.initiate(context, session);
	String reportPath = "";
	model.getStatisticReport(appStat, request, response, reportPath);
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
	    final AppraisalStatisticModel model =new AppraisalStatisticModel();
		model.initiate(context, session);
		String poolName = String.valueOf(session
				.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "\\" + poolName;
		}
		String reportPath = getText("data_path") + "\\Report\\Master"
				+ poolName + "\\";
		model.getStatisticReport(appStat, request, response, reportPath);
		model.terminate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "mailReport";
}
// Added by Tinshuk Banafar...End...
	
}
