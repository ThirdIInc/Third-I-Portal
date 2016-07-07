package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalReport;
import org.paradyne.model.PAS.AppraisalReportModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalReportAction extends ParaActionSupport {

	AppraisalReport apprReport;
	public AppraisalReport getApprReport() {
		return apprReport;
	}

	public void setApprReport(AppraisalReport apprReport) {
		this.apprReport = apprReport;
	}

	public void prepare_local() throws Exception {
		apprReport = new AppraisalReport();
		apprReport.setMenuCode(823);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apprReport;
	}
	public String input() {
		getNavigationPanel(1);
		return INPUT;
	}
public String f9AppCal()throws Exception{
		
		String query = " SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID,TO_CHAR(APPR_CAL_START_DATE,'Mon YYYY')||'-'||TO_CHAR(APPR_CAL_END_DATE,'Mon YYYY') FROM PAS_APPR_CALENDAR "
			+" ORDER BY APPR_ID";

		
		String[] headers = { getMessage("appraisal.code"),getMessage("appraisal.from"),getMessage("appraisal.to")};

		
		String[] headerWidth = { "50","25","25" };

		String[] fieldNames = { "apprCode","frmDate","toDate","apprId","apprPeriod"};

		int[] columnIndex = { 0,1,2,3,4 };

		String submitFlag = "false";

		String submitToMethod = "AppraisalReport_input.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		
		return "f9page";
	}
	
public String f9Employee()throws Exception{
		
		String query = "SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),RANK_NAME,"
						+" CENTER_NAME,HRMS_EMP_OFFC.EMP_ID FROM PAS_APPR_ELIGIBLE_EMP "
						+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)"
						+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
						+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_EMP_OFFC.EMP_RANK)"
						+" WHERE APPR_ID="+apprReport.getApprId();

		
		String[] headers = { getMessage("employee.name")+" Id",getMessage("employee.name"),getMessage("designation"),getMessage("branch")};

		
		String[] headerWidth = { "15","45","20","20" };

		String[] fieldNames = { "empToken","empName","desg","branch","empCode"};

		int[] columnIndex = { 0,1,2,3,4 };

		String submitFlag = "true";

		String submitToMethod = "AppraisalReport_getTemplate.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}

	public String getTemplate(){
		AppraisalReportModel model = new AppraisalReportModel();
		model.initiate(context, session);
		model.getTemplateCode(apprReport,apprReport.getEmpCode());
		model.terminate();
		getNavigationPanel(1);
		return SUCCESS;
	}
public String getReport(){
	AppraisalReportModel model = new AppraisalReportModel();
	model.initiate(context, session);
	model.getReport(request, response, apprReport);
	model.terminate();
	return null;
}

}
