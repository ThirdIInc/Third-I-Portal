package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalSummaryReport;
import org.paradyne.model.PAS.AppraisalSummaryReportModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalSummaryReportAction extends ParaActionSupport {

	AppraisalSummaryReport apprReport;
	public AppraisalSummaryReport getApprReport() {
		return apprReport;
	}

	public void setApprReport(AppraisalSummaryReport apprReport) {
		this.apprReport = apprReport;
	}

	public void prepare_local() throws Exception {
		apprReport = new AppraisalSummaryReport();
		apprReport.setMenuCode(879);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apprReport;
	}
	public String input() {
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String reset(){
		apprReport.setApprCode("");
		apprReport.setApprId("");
		apprReport.setApprFrom("");
		apprReport.setApprTo("");
		apprReport.setTemplate("");
		apprReport.setTemplateId("");
		apprReport.setDivId("");
		apprReport.setDivision("");
		apprReport.setBranchId("");
		apprReport.setBranch("");
		apprReport.setDeptId("");
		apprReport.setDept("");
		apprReport.setDesgId("");
		apprReport.setDesg("");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String getAppraisalDtls()throws Exception{
		
		AppraisalSummaryReportModel model=new AppraisalSummaryReportModel();
		model.initiate(context, session);
		model.getAppraisalDtls(apprReport);
		model.terminate();
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String getReport()throws Exception{
		
		AppraisalSummaryReportModel model=new AppraisalSummaryReportModel();
		model.initiate(context, session);
		model.getReport(apprReport,request,response);
		model.terminate();
		getNavigationPanel(1);
		return null;
	}
public String genReport()throws Exception{
		
		AppraisalSummaryReportModel model=new AppraisalSummaryReportModel();
		model.initiate(context, session);
		model.getReport(apprReport,response,request,"");
		model.terminate();
		getNavigationPanel(1);
		return null;
	}
public final String mailReport() {
	
	try {
		AppraisalSummaryReportModel model = new AppraisalSummaryReportModel();
		model.initiate(context, session);
		String poolName = String.valueOf(session
				.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		String reportPath = getText("data_path") + "/Report/Master"
				+ poolName + "/";
		model.getReport(apprReport,response,request,reportPath);
		model.terminate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "mailReport";
}	
public String f9AppCal()throws Exception{
		
		String query = " SELECT  APPR_CAL_CODE,APPR_TEMPLATE_NAME,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),PAS_APPR_CALENDAR.APPR_ID,APPR_TEMPLATE_ID "
					  +" FROM PAS_APPR_CALENDAR "
					  +" INNER JOIN PAS_APPR_TEMPLATE ON(PAS_APPR_TEMPLATE.APPR_ID=PAS_APPR_CALENDAR.APPR_ID)"					  
					  +" ORDER BY APPR_ID";
		
		String[] headers = { getMessage("appraisal.code"),getMessage("appraisal.temp"),getMessage("appraisal.from"),getMessage("appraisal.to")};
		
		String[] headerWidth = { "25","25","25","25" };

		String[] fieldNames = { "apprCode","template","apprFrom","apprTo","apprId","templateId"};

		int[] columnIndex = { 0,1,2,3,4,5 };

		String submitFlag = "true";

		String submitToMethod = "AppraisalSummaryReport_getAppraisalDtls.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		
		return "f9page";
	}
	

public String f9Division()throws Exception{
		
		String query = " SELECT DIV_NAME,DIV_ID	 FROM HRMS_DIVISION";
		
		String[] headers = {getMessage("appraisal.div")};
		
		String[] headerWidth = { "50" };
		
		String[] fieldNames = { "division","divId"};
		
		int[] columnIndex = { 0,1};
		
		String submitFlag = "false";
		
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		
		return "f9page";
	}	
	
public String f9Branch()throws Exception{
	
	String query = " SELECT CENTER_NAME,CENTER_ID	FROM HRMS_CENTER";
	
	String[] headers = {getMessage("appraisal.branch")};
	
	String[] headerWidth = { "50" };
	
	String[] fieldNames = { "branch","branchId"};
	
	int[] columnIndex = { 0,1};
	
	String submitFlag = "false";
	
	String submitToMethod = "";
	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	
	return "f9page";
}	

public String f9Dept()throws Exception{
	
	String query = " SELECT DEPT_NAME,DEPT_ID	FROM HRMS_DEPT";
	
	String[] headers = {getMessage("appraisal.dept")};
	
	String[] headerWidth = { "50" };
	
	String[] fieldNames = { "dept","deptId"};
	
	int[] columnIndex = { 0,1};
	
	String submitFlag = "false";
	
	String submitToMethod = "";
	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	
	return "f9page";
}	
public String f9Desg()throws Exception{
	
	String query = " SELECT RANK_NAME,RANK_ID	FROM HRMS_RANK";
	
	String[] headers = {getMessage("appraisal.desg")};
	
	String[] headerWidth = { "50" };
	
	String[] fieldNames = { "desg","desgId"};
	
	int[] columnIndex = { 0,1};
	
	String submitFlag = "false";
	
	String submitToMethod = "";
	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	
	return "f9page";
}	

}
