package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.InterviewEvaluationBean;
import org.paradyne.model.recruitment.InterviewEvaluationReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0517
 *
 */
public class InterviewEvaluationReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(InterviewEvaluationReportAction.class);

	InterviewEvaluationBean intEvalReport;

	public InterviewEvaluationBean getIntEvalReport() {
		return intEvalReport;
	}

	public void setIntEvalReport(InterviewEvaluationBean intEvalReport) {
		this.intEvalReport = intEvalReport;
	}

	public void prepare_local() throws Exception {
		intEvalReport = new InterviewEvaluationBean();
		intEvalReport.setMenuCode(937);
	}

	public Object getModel() {
		return intEvalReport;
	}

	public String input() {
		return "success";
	}

	public String report() {
		try {
			InterviewEvaluationReportModel model = new InterviewEvaluationReportModel();
			model.initiate(context, session);
			model.getReport(request, response, intEvalReport);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String reset() {
		intEvalReport.setReqname("");
		intEvalReport.setReqCode("");
		intEvalReport.setReqDate("");
		intEvalReport.setReqPosition("");
		intEvalReport.setInterviewerName("");
		intEvalReport.setInterviewerToken("");
		intEvalReport.setInterviewerId("");
		intEvalReport.setCandidateName("");
		intEvalReport.setCandidateCode("");
		intEvalReport.setPosition("");
		intEvalReport.setPositionId("");
		intEvalReport.setFrmDate("");
		intEvalReport.setToDate("");
		intEvalReport.setReportType("1");
		return "success";
	}

	public String f9actionReqName() {
		String query = " SELECT NVL(HRMS_REC_REQS_HDR.REQS_NAME,' '), TO_CHAR(HRMS_REC_REQS_HDR.REQS_DATE,'DD-MM-YYYY'), HRMS_RANK.RANK_NAME, HRMS_REC_REQS_HDR.REQS_CODE "
				+ " FROM HRMS_REC_REQS_HDR "
				+ " INNER JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_REC_REQS_HDR.REQS_POSITION) "
				+ " WHERE HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS IN ('A','Q') ORDER BY HRMS_REC_REQS_HDR.REQS_DATE DESC";

		String[] headers = { getMessage("requisitionTitle"),
				getMessage("requisitionDate"), getMessage("position") };

		String[] headerWidth = { "40", "20", "40" };

		String[] fieldNames = { "reqname", "reqDate", "reqPosition", "reqCode" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9actionInterviewer() {
		String query = "SELECT EMP_TOKEN,(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) AS NAME, EMP_ID FROM HRMS_EMP_OFFC  WHERE EMP_STATUS='S' ORDER BY UPPER(NAME)";

		String[] headers = { "Interviewer ID", "Inteviewer Name" };

		String[] headerWidth = { "50", "50" };

		String[] fieldNames = { "interviewerToken", "interviewerName",
				"interviewerId" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9Candidate() {
		String query = "SELECT CAND_FIRST_NAME||' '||CAND_LAST_NAME,CAND_CODE FROM  HRMS_REC_CAND_DATABANK "
				+ " ORDER BY  CAND_FIRST_NAME||' '||CAND_LAST_NAME";

		String[] headers = { "Candidate Name" };

		String[] headerWidth = { "50" };

		String[] fieldNames = { "candidateName", "candidateCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Position() {
		String query = " SELECT HRMS_RANK.RANK_NAME, HRMS_RANK.RANK_ID FROM HRMS_RANK WHERE  HRMS_RANK.IS_ACTIVE ='Y' ORDER BY UPPER(HRMS_RANK.RANK_NAME)";

		String[] headers = { "Position Name" };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "position", "positionId" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

}
