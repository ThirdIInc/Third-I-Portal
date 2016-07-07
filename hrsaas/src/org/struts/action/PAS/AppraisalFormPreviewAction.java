/**
 * 
 */
package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalFormPreview;
import org.paradyne.model.PAS.AppraisalFormPreviewModel;
import org.paradyne.model.PAS.AppraiserConfigurationReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 *
 */
public class AppraisalFormPreviewAction extends ParaActionSupport {

	AppraisalFormPreview apprFormPrv;
	public void prepare_local() throws Exception {
		apprFormPrv = new AppraisalFormPreview();
		apprFormPrv.setMenuCode(840);
	}

	public Object getModel() {
		return apprFormPrv;
	}
	
	public AppraisalFormPreview getApprFormPrv() {
		return apprFormPrv;
	}

	public void setApprFormPrv(AppraisalFormPreview apprFormPrv) {
		this.apprFormPrv = apprFormPrv;
	}
	
	public String f9AppraisalCode()
	{
		String query = " SELECT APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID,'','','','','','' FROM PAS_APPR_CALENDAR " +
					   " WHERE APPR_ID IN (SELECT DISTINCT APPR_ID FROM PAS_APPR_TEMPLATE) ";
		
		String headers[] = {getMessage("appr.code"),getMessage("appr.start.date"),getMessage("appr.end.date")};
		String headerWidth[]= {"50","25","25"};
		String fieldNames[]={"apprName","startDate","endDate","apprCode","tempName","tempCode","groupCode","groupName","empCode","empName"};
		int columnIndex[]={0,1,2,3,4,5,6,7,8,9};
		String submitFlag="false";
		String submitToMethod="";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String f9TemplateCode()
	{
		String query = " SELECT APPR_TEMPLATE_NAME,APPR_TEMPLATE_ID,'','','','' FROM PAS_APPR_TEMPLATE WHERE APPR_ID = "+apprFormPrv.getApprCode();
		String headers[] = {getMessage("temp.code")};
		String headerWidth[]= {"50"};
		String fieldNames[]={"tempName","tempCode","groupCode","groupName","empCode","empName"};
		int columnIndex[]={0,1,2,3,4,5};
		String submitFlag="false";
		String submitToMethod="";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String previewReport()
	{
		AppraisalFormPreviewModel model = new AppraisalFormPreviewModel();
		model.initiate(context, session);
		model.generatePreviewReport(request, response, apprFormPrv);
		//model.generatePreviewReport(apprFormPrv.getApprCode(),apprFormPrv.getTempCode());
		model.terminate();
		return null;
	}
	
	public String f9Group()
	{
		String query = " SELECT APPR_EMP_GRP_NAME,APPR_EMP_GRP_ID,'','' FROM PAS_APPR_EMP_GRP_HDR  WHERE APPR_ID = "+apprFormPrv.getApprCode()+" AND APPR_TEMPLATE_ID = "+apprFormPrv.getTempCode()
		 		     + " ORDER BY  APPR_EMP_GRP_ID ";
		String headers[] = {getMessage("group")};
		String headerWidth[]= {"50"};
		String fieldNames[]={"groupName","groupCode","empCode","empName"};
		int columnIndex[]={0,1,2,3};
		String submitFlag="false";
		String submitToMethod="";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	public String f9Employee()
	{
		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME,DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME,EMP_ID FROM HRMS_EMP_OFFC " 
					 + " INNER JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID = HRMS_EMP_OFFC.EMP_ID) "  
					 + " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)"
					 + " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " 
					 + " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " 
					 + " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " 
					 + " WHERE APPR_EMP_GRP_ID = "+apprFormPrv.getGroupCode();
	
		String headers[] = {getMessage("employee.id"),getMessage("employee"),getMessage("division"),getMessage("branch"),getMessage("department"),getMessage("designation")};
		String headerWidth[]= {"12","23","20","15","15","15"};
		String fieldNames[]={"empToken","empName","empDiv","empBrn","empDept","empDesg","empCode"};
		int columnIndex[]={0,1,2,3,4,5,6};
		String submitFlag="false";
		String submitToMethod="";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String genReport()throws Exception{
		
		AppraisalFormPreviewModel model=new AppraisalFormPreviewModel();
			model.initiate(context, session);
			model.getReport(apprFormPrv,response,request,"");
			model.terminate();
			getNavigationPanel(1);
			return null;
		}
	public final String mailReport() {
		
		try {
			AppraisalFormPreviewModel model = new AppraisalFormPreviewModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath = getText("data_path") + "/Report/Master"
					+ poolName + "/";
			model.getReport(apprFormPrv,response,request,reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}	

}
