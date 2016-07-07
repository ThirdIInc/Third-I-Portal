package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.InterviewAssessmentReport;
import org.paradyne.model.recruitment.InterviewAssessmentReportModel;
import org.struts.lib.ParaActionSupport;

public class InterviewAssessmentReportAction extends ParaActionSupport {

	InterviewAssessmentReport reportBean;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		reportBean = new InterviewAssessmentReport();
		reportBean.setMenuCode(1080);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return reportBean;
	}

	public InterviewAssessmentReport getReportBean() {
		return reportBean;
	}

	public void setReportBean(InterviewAssessmentReport reportBean) {
		this.reportBean = reportBean;
	}

	public String reset() {
		try {
			reportBean.setReqCode("");
			reportBean.setReqName("");
			reportBean.setCandName("");
			reportBean.setCandCode("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String report() {

		try {
			InterviewAssessmentReportModel model = new InterviewAssessmentReportModel();
			model.initiate(context, session);
			model.getnerateReport(reportBean, response);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public String f9candidate() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = " SELECT CAND_FIRST_NAME || ' ' || CAND_MID_NAME || ' ' ||
		 * CAND_LAST_NAME,CAND_CODE FROM HRMS_REC_CAND_DATABANK " + " WHERE
		 * CAND_CODE NOT IN(SELECT RESUME_CAND_CODE FROM HRMS_REC_RESUME_BANK
		 * WHERE RESUME_REQS_CODE = "+conductIntr.getRequisitionCode()+")";
		 */

		/*
		 * String query = "SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||'
		 * '||CAND_LAST_NAME,CAND_CODE " + " FROM HRMS_REC_CAND_DATABANK " + "
		 * ORDER BY UPPER(CAND_FIRST_NAME||' '||CAND_MID_NAME||'
		 * '||CAND_LAST_NAME)";
		 */

		String query = " SELECT DISTINCT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,REQS_NAME,CAND_CODE ,EVAL_REQS_CODE "
				+ " FROM HRMS_REC_CAND_DATABANK "
				+ " INNER JOIN HRMS_REC_CANDEVAL  on (HRMS_REC_CANDEVAL.EVAL_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE) "
				+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_CANDEVAL.EVAL_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE) "
				+ " ORDER BY UPPER(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("cand.name"), "Requisition Name" };

		String[] headerWidth = { "50", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "candName", "reqName", "candCode", "reqCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

}
