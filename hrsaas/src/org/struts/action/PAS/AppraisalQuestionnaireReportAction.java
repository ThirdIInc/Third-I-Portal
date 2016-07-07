package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalQuestionnaireReport;
import org.paradyne.model.PAS.AppraisalEligibleEmpReportModel;
import org.paradyne.model.PAS.AppraisalQuestionnaireReportModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalQuestionnaireReportAction extends ParaActionSupport 
{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppraisalQuestionnaireReportAction.class);
	AppraisalQuestionnaireReport bean = null;

	public void prepare_local() throws Exception 
	{
		bean = new AppraisalQuestionnaireReport();
		bean.setMenuCode(839);
	}

	public Object getModel()  
	{
		return bean;
	}
	
	public void setAppraisalQuestionnaireReport(AppraisalQuestionnaireReport appraisalQuestionnaireEmpReport) 
	{
		this.bean = appraisalQuestionnaireEmpReport;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception 
	{
		AppraisalQuestionnaireReportModel model = new AppraisalQuestionnaireReportModel();
  		model.initiate(context, session);
  		model.getQuestionCategoryList(bean); /* Populate the Question Category */
  		model.terminate();
	}

	public String input() 
	{
		getNavigationPanel(1);
		return INPUT;
	}

	/*public String getReport()
	{
		AppraisalQuestionnaireReportModel model = new AppraisalQuestionnaireReportModel();
		model.initiate(context, session);
		model.getReport(request, response, bean);
		model.terminate();
		return null;
	}*/
	
	
	// Added by Tinshuk Banafar...Begin...
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 * @throws Exception
	 */

	public final String getReport() throws Exception {
		AppraisalQuestionnaireReportModel model = new AppraisalQuestionnaireReportModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getQueEmpReport(bean, request, response, reportPath);
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
		    final AppraisalQuestionnaireReportModel model = new AppraisalQuestionnaireReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			String reportPath = getText("data_path") + "\\Report\\Master"
					+ poolName + "\\";
			model.getQueEmpReport(bean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String f9QuestionCategory(){
		try{
		String query=" SELECT  APPR_QUES_CATG_CODE, INITCAP(APPR_QUES_CATEG_NAME) "  +
					"	 FROM PAS_APPR_QUESTION_CATGORY  "+
					"	ORDER BY APPR_QUES_CATEG_NAME";
		
		final String textAreaId = "paraFrm_QuestionCategory";
		final String hiddenFieldId = "paraFrm_sQuestionCategoryCode";
		final String header = this.getMessage("reporting.to");
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag, submitToMethod);
	} catch (final Exception e) {
		e.printStackTrace();
	} 
	return "f9multiSelect";
		
	}
	// Added by Tinshuk Banafar...End...
	
}
