package org.struts.action.PAS;
/*
 * @saipavan voleti
 * Date:20-06-2008
 */

import org.paradyne.bean.PAS.AppraisalFormReport;
import org.paradyne.model.PAS.AppraisalFormReportModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalFormReportAction extends ParaActionSupport {
	// class for Generating Statistical report based on branch and department and organization and designation wise.


	AppraisalFormReport appFormRpt;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		this.appFormRpt =new AppraisalFormReport();
		appFormRpt.setMenuCode(845); 
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		
		return appFormRpt;
	}

	
	
	/** 
	 * @return String after resetting all the fields
	 */
	public String reset() {
		
		appFormRpt.setApprCode("");
		appFormRpt.setApprId("");
		appFormRpt.setApprFrom("");
		appFormRpt.setApprTo("");
		appFormRpt.setTemplate("");
		appFormRpt.setTemplateId("");
		appFormRpt.setEmpId("");
		appFormRpt.setEmpToken("");
		appFormRpt.setEmpName("");
		
		getNavigationPanel(1);		
		return SUCCESS;
	}
	
	public String getAppraisalDtls()throws Exception{
		
		AppraisalFormReportModel model=new AppraisalFormReportModel();
		model.initiate(context, session);
		model.getAppraisalDtls(appFormRpt);
		model.terminate();
		getNavigationPanel(1);
		return SUCCESS;
	}

	
	/**
	 * @return String after Generating report
	 */
	public String report() {
		AppraisalFormReportModel model =new AppraisalFormReportModel();
	   model.initiate(context, session);
	 	   model.getReport(request, response, appFormRpt);
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

		
		String[] headerWidth = { "50","25","25" };

		String[] fieldNames = { "apprCode","apprFrom","apprTo","apprId"};

		int[] columnIndex = { 0,1,2,3 };

		String submitFlag = "true";

		String submitToMethod = "AppraisalFormReport_getAppraisalDtls.action";

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
					+" WHERE APPR_ID="+appFormRpt.getApprId();

	
	String[] headers = { getMessage("appraisal.emp")+" Id",getMessage("appraisal.emp"),getMessage("appraisal.desg"),getMessage("appraisal.branch")};

	
	String[] headerWidth = { "15","45","20","20" };

	String[] fieldNames = { "empToken","empName","desg","branch","empId"};

	int[] columnIndex = { 0,1,2,3,4 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	return "f9page";
}


}
