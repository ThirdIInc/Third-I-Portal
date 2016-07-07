package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalEvaluatorScoreBalancerRpt;
import org.paradyne.model.PAS.AppraisalEvaluatorScoreBalancerRptModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalEvaluatorScoreBalancerRptAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AppraisalEvaluatorScoreBalancerRptAction.class);

	AppraisalEvaluatorScoreBalancerRpt bean = null;

	public void prepare_local() throws Exception {
		bean = new AppraisalEvaluatorScoreBalancerRpt();
		bean.setMenuCode(925);
	}

	public Object getModel() {
		return bean;
	}

	public void setAppraisalEvaluatorScoreBalancerRpt(
			AppraisalEvaluatorScoreBalancerRpt bean) {
		this.bean = bean;
	}

	public String input() throws Exception {
		return INPUT;
	}

	public String f9AppraisalCode() {
		String query = " SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID  FROM PAS_APPR_CALENDAR WHERE APPR_ID IN (SELECT APPR_ID FROM PAS_APPR_SCORE )ORDER BY APPR_ID ";
		String headers[] = { getMessage("appr.code"),
				getMessage("appr.start.date"), getMessage("appr.end.date") };
		String headerWidth[] = { "50", "25", "25" };
		String fieldNames[] = { "sAppCode", "sAppStartDate", "sAppEndDate", "sAppId" };
		int columnIndex[] = { 0, 1, 2, 3 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public Object generateReport() throws Exception {
		
		AppraisalEvaluatorScoreBalancerRptModel model = new AppraisalEvaluatorScoreBalancerRptModel();
		model.initiate(context, session);
		model.generateReport(bean, response);
		model.terminate();
		return null;
	}

}
