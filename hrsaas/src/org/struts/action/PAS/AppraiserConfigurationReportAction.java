package org.struts.action.PAS;

import java.util.LinkedHashMap;

import org.paradyne.bean.PAS.AppraiserConfigurationReport;
import org.paradyne.model.PAS.AppraiserConfigurationReportModel;
import org.struts.lib.ParaActionSupport;

public class AppraiserConfigurationReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppraiserConfigurationReportAction.class);
	AppraiserConfigurationReport bean = null;
	
	public void prepare_local() throws Exception {
		bean = new AppraiserConfigurationReport();
		bean.setMenuCode(885);
	}

	public Object getModel() {
		return bean;
	}
	
	public void setAppraiserConfigurationReport(AppraiserConfigurationReport appraiserConfigurationReport) 
	{
		this.bean = appraiserConfigurationReport;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception 
	{
		AppraiserConfigurationReportModel model = new AppraiserConfigurationReportModel();
  		model.initiate(context, session);
  		model.terminate();
	}

	public String input() 
	{
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String getReport()
	{
		AppraiserConfigurationReportModel model = new AppraiserConfigurationReportModel();
		model.initiate(context, session);
		model.getReport(request, response, bean);
		model.terminate();
		return null;
	}
	
	public String getPhaseDetails()
	{
		AppraiserConfigurationReportModel model = new AppraiserConfigurationReportModel();
		model.initiate(context, session);
		try 
		{
			LinkedHashMap hMap = model.getPhaseList(bean.getSAppId());
			bean.setPhaseList(hMap);
		}
		catch (Exception e) 
		{
			logger.error(e.getMessage());
		}
		Reset();
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
	
	private void Reset()
	{
		bean.setSGroup("");
		bean.setSGroupId("");
		bean.setAppraiseeCode("");
		bean.setAppraiseeName("");
		bean.setAppraiseeId("");
		bean.setAppraiserCode("");
		bean.setAppraiserName("");
		bean.setAppraiserId("");
		bean.setPhaseName("");
		//bean.setPhaseList(null);
	}
	
	public String f9SelectAppraisalCalendar() throws Exception 
	{
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID " +
					   " FROM PAS_APPR_CALENDAR WHERE APPR_ID IN (SELECT APPR_ID FROM PAS_APPR_APPRAISER)ORDER BY APPR_ID ";
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
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AppraiserConfigurationReport_getPhaseDetails.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9SelectGroupName() throws Exception
	{
		String query =  " SELECT " +
						"	INITCAP(APPR_APPRAISER_GRP_NAME), " +
						"	APPR_APPRAISER_GRP_ID " +
						" FROM " +
						"	PAS_APPR_APPRAISER_GRP_HDR " +
						" WHERE " +
						"	APPR_ID = " + bean.getSAppId() +
						" ORDER BY " +
						"	APPR_APPRAISER_GRP_NAME ";
		
		String[] headers = { "Group Name" };

		String[] headerWidth = { "100" };
		String[] fieldNames = { "sGroup", "sGroupId" };
		int[] columnIndex = { 0, 1 };
		
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9SelectAppraiseeName() throws Exception
	{
		String sWhereClause = " WHERE ";
		
		if (bean.getSAppId()!=null && !bean.getSAppId().equals(""))
		{
			sWhereClause = sWhereClause + " APPR_ID = " + bean.getSAppId();
		}
		if (bean.getSGroupId()!=null && !bean.getSGroupId().equals(""))
		{
			sWhereClause = sWhereClause + " AND B.APPR_APPRAISER_GRP_ID = " + bean.getSGroupId();
		}
		//if (!bean.getAppraiserCode().equals(""))
		//{
		//	sWhereClause = sWhereClause + " AND B.APPR_APPRAISEE_ID = " + bean.getAppraiserCode();
		//}

		String query = " SELECT " +
					   "	EMP_TOKEN," +
					   "	INITCAP(EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME) AS EMPNAME," +
					   "	EMP_ID " +
					   " FROM " +
					   "	HRMS_EMP_OFFC " +
					   " WHERE " +
					   "	EMP_ID IN ( " +
					   "				SELECT APPR_APPRAISEE_ID FROM PAS_APPR_APPRAISER_GRP_HDR A " +
					   "				INNER JOIN PAS_APPR_APPRAISER_GRP_DTL B ON (A.APPR_APPRAISER_GRP_ID = B.APPR_APPRAISER_GRP_ID) " +
					   					sWhereClause +	" ) " +
					   " ORDER BY" +
					   "	EMPNAME ";				
		
		String[] headers = { "Appraisee Id", "Appraisee Name" };

		String[] headerWidth = { "50", "50" };
		String[] fieldNames = { "appraiseeCode", "appraiseeName", "appraiseeId" };
		int[] columnIndex = { 0, 1, 2 };
		
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9SelectAppraiserName() throws Exception
	{
		String sWhereClause = " WHERE ";
		
		if (bean.getSAppId()!=null && !bean.getSAppId().equals(""))
		{
			sWhereClause = sWhereClause + " APPR_ID = " + bean.getSAppId();
		}
		if (bean.getSGroupId()!=null && !bean.getSGroupId().equals(""))
		{
			sWhereClause = sWhereClause + " AND B.APPR_APPRAISER_GRP_ID = " + bean.getSGroupId();
		}
		if (bean.getAppraiseeId()!=null && !bean.getAppraiseeId().equals(""))
		{
			sWhereClause = sWhereClause + " AND B.APPR_APPRAISEE_ID = " + bean.getAppraiseeId();
		}
		
		String query = " SELECT " +
					   "	EMP_TOKEN," +
					   "	INITCAP(EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME) AS EMPNAME," +
					   "	EMP_ID " +
					   " FROM " +
					   "	HRMS_EMP_OFFC " +
					   " WHERE " +
					   "	EMP_ID IN ( " +
					   "				SELECT APPR_APPRAISER_CODE FROM PAS_APPR_APPRAISER A " +
					   "				INNER JOIN PAS_APPR_APPRAISER_GRP_DTL B ON (A.APPR_APPRAISER_GRP_ID = B.APPR_APPRAISER_GRP_ID) " +
					   					sWhereClause +	" ) " +
					   " ORDER BY" +
					   "	EMPNAME ";	
		
		String[] headers = { "Appraiser Id", "Appraiser Name" };

		String[] headerWidth = { "50", "50" };
		String[] fieldNames = { "appraiserCode", "appraiserName", "appraiserId" };
		int[] columnIndex = { 0, 1, 2 };
		
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
public String genReport()throws Exception{
		
	AppraiserConfigurationReportModel model=new AppraiserConfigurationReportModel();
		model.initiate(context, session);
		model.getReport(bean,response,request,"");
		model.terminate();
		getNavigationPanel(1);
		return null;
	}
public final String mailReport() {
	
	try {
		AppraiserConfigurationReportModel model = new AppraiserConfigurationReportModel();
		model.initiate(context, session);
		String poolName = String.valueOf(session
				.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		String reportPath = getText("data_path") + "/Report/Master"
				+ poolName + "/";
		model.getReport(bean,response,request,reportPath);
		model.terminate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "mailReport";
}	
}
