/**
 * 
 */
package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalConfigReport;
import org.paradyne.model.PAS.AppraisalConfigReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 *
 */
public class AppraisalConfigReportAction extends ParaActionSupport {

	AppraisalConfigReport apprConfigReport;
	public void prepare_local() throws Exception {
		apprConfigReport = new AppraisalConfigReport();
		apprConfigReport.setMenuCode(841);
	}

	public Object getModel() {
		return apprConfigReport;
	}
	
	public AppraisalConfigReport getapprConfigReport() {
		return apprConfigReport;
	}

	public void setapprConfigReport(AppraisalConfigReport apprConfigReport) {
		this.apprConfigReport = apprConfigReport;
	}
	
	public String f9AppraisalCode()
	{
		String query = " SELECT APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID,'','','','' FROM PAS_APPR_CALENDAR " 
				+ " WHERE APPR_ID IN (SELECT DISTINCT APPR_ID FROM PAS_APPR_TEMPLATE) ";
		String headers[] = {getMessage("appr.code"),getMessage("appr.start.date"),getMessage("appr.end.date")};
		String headerWidth[]= {"50","25","25"};
		String fieldNames[]={"apprName","startDate","endDate","apprCode","tempName","tempCode","groupName","groupCode"};
		int columnIndex[]={0,1,2,3,4,5,6,7};
		String submitFlag="false";
		String submitToMethod="";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String f9TemplateCode()
	{
		String query = " SELECT APPR_TEMPLATE_NAME,APPR_TEMPLATE_ID,'','' FROM PAS_APPR_TEMPLATE WHERE APPR_ID = "+apprConfigReport.getApprCode();
		String headers[] = {getMessage("temp.code")};
		String headerWidth[]= {"50"};
		String fieldNames[]={"tempName","tempCode","groupName","groupCode"};
		int columnIndex[]={0,1,2,3};
		String submitFlag="false";
		String submitToMethod="";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String f9Group()
	{
		String query = " SELECT APPR_EMP_GRP_NAME,APPR_EMP_GRP_ID FROM PAS_APPR_EMP_GRP_HDR  WHERE APPR_ID = "+apprConfigReport.getApprCode()+" AND APPR_TEMPLATE_ID = "+apprConfigReport.getTempCode()
		 		     + " ORDER BY  APPR_EMP_GRP_ID ";
		String headers[] = {getMessage("group")};
		String headerWidth[]= {"50"};
		String fieldNames[]={"groupName","groupCode"};
		int columnIndex[]={0,1};
		String submitFlag="false";
		String submitToMethod="";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String previewReport()
	{
		AppraisalConfigReportModel model = new AppraisalConfigReportModel();
		model.initiate(context, session);
		model.generateConfigReport(request, response, apprConfigReport.getApprCode(),apprConfigReport.getTempCode(),apprConfigReport.getTempName(),apprConfigReport.getGroupCode());
		model.terminate();
		return null;
	}
	
	
	public String genReport()throws Exception{
			
		AppraisalConfigReportModel model=new AppraisalConfigReportModel();
			model.initiate(context, session);
			model.getReport(apprConfigReport,response,request,"");
			model.terminate();
			getNavigationPanel(1);
			return null;
		}
	public final String mailReport() {
		
		try {
			AppraisalConfigReportModel model = new AppraisalConfigReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath = getText("data_path") + "/Report/Master"
					+ poolName + "/";
			model.getReport(apprConfigReport,response,request,reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}	
}
