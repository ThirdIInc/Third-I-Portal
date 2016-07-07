/**
 * 
 */
package org.struts.action.recruitment;

import java.io.PrintWriter;

import org.paradyne.bean.Recruitment.ConductInterview;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.recruitment.ConductInterviewModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain.
 * 
 */
public class ConductInterviewAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ConductInterviewAction.class);
	ConductInterview conductIntr;

	public void prepare_local() throws Exception {
		conductIntr = new ConductInterview();
		conductIntr.setMenuCode(777);
	}

	public Object getModel() {
		return conductIntr;
	}

	public String conductInterview() {
		try {
			ConductInterviewModel model = new ConductInterviewModel();
			model.initiate(context, session);
			model.setCandidateData(request, conductIntr);
			String listType = request.getParameter("listType");
			if (listType != null && listType.trim().equals("O")) {
				conductIntr.setOnHoldInterviewFlag("true");
				conductIntr.setOnHoldCalculation("onHoldData");
			}  
			Object[][] data = model.setEvaluationDetails(request, conductIntr);
			if (data != null && data.length > 0) {
				request.setAttribute("dataObj", data);
			}
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			return SUCCESS;
		}
	}

	public String input() {
		try {
			reset();
			ConductInterviewModel model = new ConductInterviewModel();
			model.initiate(context, session);
			Object[][] data = model.setEvaluationDetails(request, conductIntr);
			if (data != null && data.length > 0) {
				request.setAttribute("dataObj", data);
			} 		
			model.terminate();
			conductIntr.setAddNewFlag("true");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String addNewInterview() {
		try {
			reset();
			ConductInterviewModel model = new ConductInterviewModel();
			model.initiate(context, session);
			conductIntr.setMenuCode(782);
			conductIntr.setAddNewFlag("true");
			Object[][] data = model.setEvaluationDetails(request, conductIntr);
			if (data != null && data.length > 0) {
				request.setAttribute("dataObj", data);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void reset() {
		conductIntr.setRequisitionCode("");
		conductIntr.setRequisitionName("");
		conductIntr.setCandName("");
		conductIntr.setCandCode("");
		conductIntr.setPosition("");
		conductIntr.setPositionCode("");
		conductIntr.setDepartment("");
		conductIntr.setDeptCode("");
		conductIntr.setDivision("");
		conductIntr.setDivisionCode("");
		conductIntr.setBranch("");
		conductIntr.setBranchCode("");
		conductIntr.setIntDate("");
		conductIntr.setIntTime("");
		conductIntr.setIntRound("");
		conductIntr.setComments("");
		conductIntr.setCheckFlag("false");
		conductIntr.setQlfDts("");
		conductIntr.setTechSkills("");
		conductIntr.setCommLevel("");
		conductIntr.setMgmtSkills("");
		conductIntr.setPersonality("");
		conductIntr.setLrngSkills("");
		conductIntr.setRlvntExp("");
		conductIntr.setSutAbility("");
		conductIntr.setEvalScore("");
		conductIntr.setPercentage("");
	}

	public String submitRec() {
		try {
			ConductInterviewModel model = new ConductInterviewModel();
			model.initiate(context, session);
			String result = "";
			String parameterCode[] = request
					.getParameterValues("parameterCode");

			String parameterRating[] = request
					.getParameterValues("parameterName");

			String[] parameterComment = request
					.getParameterValues("evalRateComments");
			
			if (conductIntr.getAddNewFlag().equals("true")) {
				result = model.addNewSubmit(conductIntr, parameterCode,
						parameterRating, parameterComment);
			} else {
				result = model.submit(conductIntr, parameterCode,
						parameterRating, parameterComment);
			}

			logger.info("result   :" + result);
			if (result == "3") {
				PrintWriter pw = response.getWriter();
				response.setContentType("text/html");
				pw
						.println("<html><body><script>alert('Candidate Rejected');</script></body></html>");
				try {
					sendRejectMail(conductIntr.getCandCode());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			if (result == "2") {
				PrintWriter pw = response.getWriter();
				response.setContentType("text/html");
				pw
						.println("<html><body><script>alert('Candidate offered successfully');</script></body></html>");
				// ..pw.println("Candidate offered successfully");
			}

			if (result == "1") {
				PrintWriter pw = response.getWriter();
				pw
						.println("<html><body><script>alert('Candidate forwarded successfully');</script></body></html>");
				// pw.println("Candidate forwarded successfully");
			}

			if (result.trim().equals("keptOnHold")) {
				PrintWriter pw = response.getWriter();
				pw
						.println("<html><body><script>alert('Candidate kept Onhold successfully');</script></body></html>");
			}

			if (result.trim().equals("alreadyProcessed")) {
				PrintWriter pw = response.getWriter();
				pw
						.println("<html><body><script>alert('All the vacancies for this requisition are already processed');</script></body></html>");
			}
			// logger.info("flag in conduct interview
			// action"+conductIntr.getBckToIntrFlag());
			/*
			 * if(conductIntr.getBckToIntrFlag().equals("true")){
			 * //mod.initiate(context, session); //
			 * request.setAttribute("stat","N");
			 * //mod.showInterviewCandList("Y", conductIntr.getUserEmpId(),
			 * bean, request); //model.getCandidateInterview("N",
			 * conductIntr.getUserEmpId(), intDetails, request);
			 * //str="interviewPending"; // mod.terminate(); }
			 */

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "interviewPending";
	}

	public void sendRejectMail(String candidateCode) {
		try {

			System.out.println("candidateCode  " + candidateCode);

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("Candidature Evaluation feedback");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			templateQuery2.setParameter(1, candidateCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, candidateCode);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);

			template.configMailAlert();
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String f9EmployeeType() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT TYPE_ID,TO_CHAR(TYPE_NAME) FROM  HRMS_EMP_TYPE  "
				+ " ORDER BY  TYPE_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Type code", "Type name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empTypeCode", "empType" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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

	public String f9SelectInterviewer() {

		String query = "  SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ "	NVL(CENTER_NAME,' '),NVL(RANK_NAME,' ') , EMP_ID FROM HRMS_EMP_OFFC  "
				+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE"
				+ " where EMP_STATUS = 'S'" + " ORDER BY HRMS_EMP_OFFC.EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Id", "Employee Name", "Branch",
				"Designation" };

		String[] headerWidth = { "20", "30", "30", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "hEmpToken", "selectInter", "hBranch", "hDesg",
				"selectInterId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

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

	public String f9Recruiter() {
		ConductInterviewModel model = new ConductInterviewModel();
		model.initiate(context, session);
		Object[][] chkIfPublishData = null;
		try {
			String checkIfPublished = "SELECT PUB_CODE FROM HRMS_REC_VACPUB_HDR "
					+ "WHERE PUB_REQS_CODE = "
					+ conductIntr.getRequisitionCode()
					+ " AND PUB_STATUS = 'P'";
			chkIfPublishData = model.getSqlModel().getSingleResult(
					checkIfPublished);
		} catch (Exception e) {
			logger.error("exception in chkIfPublishData", e);
		} // end of catch
		model.terminate();
		String query = "";

		if (chkIfPublishData != null && chkIfPublishData.length > 0) {
			query = "SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,NVL(CENTER_NAME,' '),NVL(RANK_NAME,' '), "
					+ " PUB_REC_EMPID FROM HRMS_REC_VACPUB_RECDTL "
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID) "
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)  "
					+ " INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK   "
					+ " WHERE PUB_REQS_CODE = "
					+ conductIntr.getRequisitionCode()
					+ " AND PUB_STATUS = 'P'";
		} // end of if
		else {
			query = "  SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
					+ "	NVL(CENTER_NAME,' '),NVL(RANK_NAME,' ') , EMP_ID FROM HRMS_EMP_OFFC  "
					+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE"
					+ " where EMP_STATUS = 'S'"
					+ " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		} // end of else

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Id", "Employee Name", "Branch",
				"Designation" };

		String[] headerWidth = { "20", "30", "30", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "recruiterToken", "recruiterName", "hBranch",
				"hDesg", "recruiterId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

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

	public String forwardToPostCandidate() {
		Object[] formValues = new Object[40];
		formValues[0] = "cndtInt";
		formValues[1] = conductIntr.getRequisitionCode();
		formValues[2] = conductIntr.getRequisitionName();
		formValues[3] = conductIntr.getHiringManager();
		formValues[4] = conductIntr.getDeptCode();
		formValues[5] = conductIntr.getDepartment();
		formValues[6] = conductIntr.getBranchCode();
		formValues[7] = conductIntr.getBranch();
		formValues[8] = conductIntr.getPosition();
		formValues[9] = conductIntr.getPositionCode();
		formValues[10] = conductIntr.getDivision();
		formValues[11] = conductIntr.getDivisionCode();
		formValues[12] = conductIntr.getIntRound();
		formValues[13] = conductIntr.getIntDate();
		formValues[14] = conductIntr.getIntTime();
		formValues[15] = conductIntr.getQlfDts();
		formValues[16] = conductIntr.getTechSkills();
		formValues[17] = conductIntr.getCommLevel();
		formValues[18] = conductIntr.getMgmtSkills();
		formValues[19] = conductIntr.getPersonality();
		formValues[20] = conductIntr.getLrngSkills();
		formValues[21] = conductIntr.getRlvntExp();
		formValues[22] = conductIntr.getSutAbility();
		formValues[23] = conductIntr.getEvalScore();
		formValues[24] = conductIntr.getPercentage();
		formValues[25] = conductIntr.getCurrentCTC();
		formValues[26] = conductIntr.getReadyReloc();
		formValues[27] = conductIntr.getNegoCTC();
		formValues[28] = conductIntr.getIntrStatus();
		formValues[29] = conductIntr.getExptdJoinDate();
		formValues[30] = conductIntr.getEmpType();
		formValues[31] = conductIntr.getEmpTypeCode();
		formValues[32] = conductIntr.getConstraints();
		formValues[33] = conductIntr.getComments();
		formValues[34] = conductIntr.getMakeOffer();
		formValues[35] = conductIntr.getFwdNxtRnd();
		formValues[36] = conductIntr.getNxtRoundNo();
		formValues[37] = conductIntr.getSelectInterId();
		formValues[38] = conductIntr.getSelectInter();
		formValues[39] = conductIntr.getAddNewFlag();

		request.setAttribute("formValues", formValues);
		request.setAttribute("formListValues", new Object[0][0]);
		return "postResume";
	}

	public String returnFromPostResume() {
		String candCode = (String) request.getAttribute("candCode");
		Object[] formValues = (Object[]) request.getAttribute("formValues");// HEADER
		// OBJECT
		ConductInterviewModel model = new ConductInterviewModel();
		model.initiate(context, session);
		model.setCandEvalAfterPostResume(candCode, formValues, conductIntr);
		model.setDropDownValueList(conductIntr);
		model.terminate();
		return SUCCESS;
	}

	public String f9Requisition() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */

		String query = " SELECT REQS_NAME, RANK_NAME, DIV_NAME, CENTER_NAME, DEPT_NAME,EMP_FNAME || ' ' ||EMP_LNAME, REQS_CODE, "
				+ " REQS_JOBDESC_NAME, REQS_ROLE_RESPON, REQS_HIRING_MANAGER,RANK_ID,DIV_ID,CENTER_ID,DEPT_ID "
				+ " FROM HRMS_REC_REQS_HDR INNER JOIN HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_POSITION = HRMS_RANK.RANK_ID) "
				+ " INNER JOIN HRMS_DIVISION ON (HRMS_REC_REQS_HDR.REQS_DIVISION = HRMS_DIVISION.DIV_ID) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_REC_REQS_HDR.REQS_BRANCH = HRMS_CENTER.CENTER_ID) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_REC_REQS_HDR.REQS_DEPT = HRMS_DEPT.DEPT_ID)"
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER = HRMS_EMP_OFFC.EMP_ID) "
				+ " INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
				+ " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
				+ " WHERE REQS_APPROVAL_STATUS IN ('A','Q') AND REQS_STATUS = 'O'  AND VACAN_STATUS='P' AND REQS_HIRING_MANAGER = "
				+ conductIntr.getUserEmpId() + " ORDER BY REQS_DATE DESC ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("reqs.code"), getMessage("position"),
				getMessage("division"), getMessage("branch"),
				getMessage("department") };

		String[] headerWidth = { "20", "20", "20", "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "requisitionName", "position", "division",
				"branch", "department", "hiringManager", "requisitionCode",
				"jobDesc", "rolesResponsibility", "hiringManagerCode",
				"positionCode", "divisionCode", "branchCode", "deptCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

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

		String query = "SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,CAND_CODE "
				+ "FROM HRMS_REC_CAND_DATABANK "
				+ "WHERE CAND_CODE NOT IN (SELECT DISTINCT RESUME_CAND_CODE FROM HRMS_REC_RESUME_BANK "
				+ "WHERE RESUME_REQS_CODE = "
				+ conductIntr.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT EVAL_CAND_CODE FROM HRMS_REC_CANDEVAL "
				+ "WHERE EVAL_REQS_CODE = "
				+ conductIntr.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT OFFER_CAND_CODE FROM HRMS_REC_OFFER "
				+ "WHERE OFFER_REQS_CODE = "
				+ conductIntr.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT APPOINT_CAND_CODE FROM HRMS_REC_APPOINT "
				+ "WHERE APPOINT_REQS_CODE = "
				+ conductIntr.getRequisitionCode()
				+ ") AND  CAND_CONVERT_FLAG = 'N' "
				+ "ORDER BY UPPER(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("cand.name") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "candName", "candCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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

	public ConductInterview getConductIntr() {
		return conductIntr;
	}

	public void setConductIntr(ConductInterview conductIntr) {
		this.conductIntr = conductIntr;
	}

	public String detailsOffer() throws Exception {
		try {
			conductIntr.getAddNewFlag().equals("true");
			String reqCode = request.getParameter("reqCode");
			ConductInterviewModel model = new ConductInterviewModel();
			model.initiate(context, session);
			model.getRequsitionDetails(conductIntr, reqCode);
			/*Object[][] data = model.setEvaluationDetails(request, conductIntr);
			if (data != null && data.length > 0) {
				request.setAttribute("dataObj", data);
			}*/
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**Method : f9GroupAction.
	 * Purpose : Used to select group defined in the HRMS_REC_INTERVIEW_GROUP
	 * @return String
	 * @throws Exception : Exception
	 */
	public String f9GroupAction() throws Exception {
		String groupQuery = " SELECT  HRMS_REC_INTERVIEW_GROUP.GROUP_NAME, "
				+ " HRMS_REC_INTERVIEW_GROUP.GROUP_DESCRIPTION, HRMS_REC_INTERVIEW_GROUP.GROUP_ABBR, "
				+ " HRMS_REC_INTERVIEW_GROUP.GROUP_ID FROM HRMS_REC_INTERVIEW_GROUP "
				+" WHERE HRMS_REC_INTERVIEW_GROUP.GROUP_IS_ACTIVE='Y'";

		String[] headers = { "Group Name", "Group Description", "Group Abbreviation", };
		String[] headerWidth = { "20", "20", "20" };
		String[] fieldNames = { "groupName", "groupDesc", "groupAbbr", "groupId" };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlag = "true";
		String submitToMethod = "ConductInterview_groupDetails.action";
		setF9Window(groupQuery, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	/**Used to set parameters mapped for selected group.
	 * @return String.
	 */
	public String groupDetails() {
		try {
			conductIntr.setAfterGroupSelectionFlag(true);
			ConductInterviewModel model = new ConductInterviewModel();
			model.initiate(context, session);
			conductIntr.setGroupId(conductIntr.getGroupId());
			Object[][] groupData = model.getRecruitmentGroupList(conductIntr);
			if (groupData != null && groupData.length > 0) {
				request.setAttribute("dataObj", groupData);
			} else {
				conductIntr.setParameterMappedOrNotFlag(true);
			}
			conductIntr.setEvalScore("");
			conductIntr.setPercentage("");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
