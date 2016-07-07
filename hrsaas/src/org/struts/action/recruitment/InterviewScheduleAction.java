package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.paradyne.bean.Recruitment.InterviewSchedule;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.recruitment.InterviewScheduleModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author varunk
 *
 */
public class InterviewScheduleAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(InterviewScheduleAction.class);
	InterviewSchedule intSchedule;

	public InterviewSchedule getIntSchedule() {
		return intSchedule;
	}

	public void setIntSchedule(InterviewSchedule intSchedule) {
		this.intSchedule = intSchedule;
	}

	public void prepare_local() throws Exception {
		intSchedule = new InterviewSchedule();
		intSchedule.setMenuCode(350);
	}

	public Object getModel() {
		return intSchedule;
	}

	/** method name : test checklist 
	 * purpose     : to display all the available test 
	 * 					checklist in new window
	 * return type : String
	 * parameter   : none
	 */
	public String intChecklist() {
		try {
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
			model.intChecklist(intSchedule, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "intChecklist";
	}

	/**
	 * this method is called from the Screening Status....the records
	 * which are checked are displayed here
	 * @return
	 */
	public String fromScreeningStatus() {
		try {
			intSchedule.setF9Flag("false");
			intSchedule.setRefFlag("true");
			String cancelStatus = request.getParameter("status");
			if (cancelStatus.equals("B")) {
				intSchedule.setRefInterviewFlag("true");
			} else {
				intSchedule.setRefInterviewFlag("false");
			}
			String[] requisitionName = request.getParameterValues("reqCode");
			String[] check = request.getParameterValues("hresumeChk");
			String[] requisitionCode = request
					.getParameterValues("requisitionCode");
			String[] position = request.getParameterValues("position");
			String[] candidateName = request
					.getParameterValues("candidateName");
			String[] candidateCode = request
					.getParameterValues("candidateCode");
			String[] recruiterCode = request
					.getParameterValues("recruiterCode");
			String[] hiringMgrCode = request
					.getParameterValues("hiringMgrCode");
			String[] hiringManager = request.getParameterValues("recruiter");
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
			model.viewCandidateList(intSchedule, request, check, candidateName,
					candidateCode, recruiterCode, hiringMgrCode, hiringManager,
					requisitionName, requisitionCode, position);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * this method is called to save the Schedule Interview....
	 * @return
	 * @throws Exception
	 */
	public String scheduleInt()throws Exception{
		String finalResult = "";
		try {
			String[] check = request.getParameterValues("chk");
			String[] candidateName = request
					.getParameterValues("candidateName");
			String[] candidateCode = request
					.getParameterValues("candidateCode");
			String[] recruiterCode = request.getParameterValues("recruiterId");
			String[] intRoundType = request.getParameterValues("intRoundType");
			String[] venue = request.getParameterValues("venue");
			String[] intDate = request.getParameterValues("intDate");
			String[] intTime = request.getParameterValues("intTime");
			String[] comments = request.getParameterValues("comments");
			String[] checkBox = request.getParameterValues("checkBox");
			String[] intDetailCode = null;
			intDetailCode = request.getParameterValues("interviewDtlCode");
			
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
			String result = model.saveSchedule(intSchedule, request, check,
					candidateName, candidateCode, recruiterCode, intDate,
					intTime, venue, intRoundType, comments, checkBox,
					intDetailCode);
			if (!(intSchedule.getInterviewCode().equals("")
					&& !intSchedule.getInterviewCode().equals("null") && !intSchedule
					.getInterviewCode().equals(null))) {
				intSchedule.setRescheduleFlag("true");
				intSchedule
						.setActionMessage("Candidates Rescheduled for interview successfully");
				sendNotificationMailToInterviewer(intSchedule);
				sendNotificationMailToCandidate(intSchedule,candidateCode);
				finalResult = "schdInterviewList";
			}
			/**
			 * IN THIS ELSE IF WE NAVIGATED FROM "CANDIDATE SCREENING STATUS"THEN IT STAYS IN THIS FORM...
			 */
			else {
				if (result == "true") {
					addActionMessage("Candidates scheduled for interview successfully");
					sendNotificationMailToInterviewer(intSchedule);
					sendNotificationMailToCandidate(intSchedule,candidateCode);
				}
				model.terminate();
				finalResult = SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalResult;
	}

	private void sendNotificationMailToInterviewer(
			InterviewSchedule intScheduleBean) {
		try {
			String requisitionCODE = request.getParameter("requisitionCode");
			String intCodeForEmail = "";
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);

			/*if (!(intSchedule.getInterviewCode().equals("")
					|| intSchedule.getInterviewCode().equals("null") || intSchedule
					.getInterviewCode().equals(null))) {
				intCodeForEmail = intSchedule.getInterviewCode();
			} else {
				String maxQuery = "SELECT NVL(MAX(INT_CODE),0) FROM HRMS_REC_SCHINT";
				Object[][] maxCode = model.getSqlModel().getSingleResult(
						maxQuery);
				intCodeForEmail = String.valueOf(maxCode[0][0]);
			}*/
			
			String maxQuery = "SELECT NVL(MAX(INT_CODE),0) FROM HRMS_REC_SCHINT";
			Object[][] maxCode = model.getSqlModel().getSingleResult(maxQuery);
			intCodeForEmail = String.valueOf(maxCode[0][0]);

			String interviewerCodesQuery = "SELECT INT_VIEWER_EMPID FROM HRMS_REC_SCHINT_DTL WHERE INT_CODE="
					+ intCodeForEmail
					+ " AND INT_REQS_CODE = "
					+ requisitionCODE;
			Object[][] interviewerCodesObj = model.getSqlModel()
					.getSingleResult(interviewerCodesQuery);
			if (interviewerCodesObj != null && interviewerCodesObj.length > 0) {
				for (int i = 0; i < interviewerCodesObj.length; i++) {
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate("RMS-INTERVIEW SCHEDULE TO INTERVIEWER");
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
					templateQuery1.setParameter(1, intScheduleBean.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
					templateQuery2.setParameter(1, String.valueOf(interviewerCodesObj[i][0]));

					EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
					templateQuery3.setParameter(1, intCodeForEmail);
					templateQuery3.setParameter(2, String.valueOf(interviewerCodesObj[i][0]));

					EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
					templateQuery4.setParameter(1, intCodeForEmail);
					templateQuery4.setParameter(2, String.valueOf(interviewerCodesObj[i][0]));
					
					EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
					templateQuery5.setParameter(1, intScheduleBean.getUserEmpId());

					template.configMailAlert();
					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();
					model.terminate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendNotificationMailToCandidate( InterviewSchedule intScheduleBean, String[] candidateCode) {
		try {
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
		String intDtlCodeForEmail = "SELECT INT_DTL_CODE,INT_CAND_CODE FROM HRMS_REC_SCHINT_DTL  "
					+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE) "
					+ " WHERE INT_CODE = (SELECT NVL(MAX(INT_CODE),0) FROM HRMS_REC_SCHINT) ";
		Object[][] intDetailCodeForEmail = model.getSqlModel().getSingleResult(
				intDtlCodeForEmail);

		if (intDetailCodeForEmail != null && intDetailCodeForEmail.length > 0) {
				for (int k = 0; k < intDetailCodeForEmail.length; k++) {
					for (int i = 0; i < candidateCode.length; i++) {
						if (candidateCode[i].equals(String.valueOf(intDetailCodeForEmail[k][1]))) {
							EmailTemplateBody template = new EmailTemplateBody();
							template.initiate(context, session);
							template.setEmailTemplate("Template For Interview Schedule");
							template.getTemplateQueries();

							EmailTemplateQuery frmMailQuery = template
									.getTemplateQuery(1); // FROM EMAIL
							frmMailQuery.setParameter(1, intScheduleBean.getUserEmpId());

							EmailTemplateQuery toMailQuery = template
									.getTemplateQuery(2); // TO EMAIL
							toMailQuery.setParameter(1, String.valueOf(intDetailCodeForEmail[k][1]));

							EmailTemplateQuery intSchDetailQuery1 = template.getTemplateQuery(3);
							intSchDetailQuery1.setParameter(1, intScheduleBean.getUserEmpId()); // recruiter emp id
							intSchDetailQuery1.setParameter(2, String.valueOf(intDetailCodeForEmail[k][0])); // int detail code
							intSchDetailQuery1.setParameter(3, String.valueOf(intDetailCodeForEmail[k][0])); // int detail code
							
							EmailTemplateQuery intSchDetailQuery2 = template.getTemplateQuery(4);
							intSchDetailQuery2.setParameter(1, intScheduleBean.getUserEmpId()); // recruiter emp id
							intSchDetailQuery2.setParameter(2, String.valueOf(intDetailCodeForEmail[k][0])); // int detail code
							intSchDetailQuery2.setParameter(3, String.valueOf(intDetailCodeForEmail[k][0])); // int detail code

							template.configMailAlert();
							template.sendApplicationMail();
							template.clearParameters();
							template.terminate();
						}
					}
				}
			} 
		model.terminate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * in this method Object is created and is used for setting 
	 * attributes which will then be sent to Post Resume form.
	 * @return
	 */
	public String toPostReumeForm() {
		Object[] formValues = new Object[11];
		formValues[0] = "intSch";
		formValues[1] = intSchedule.getCntPersonId();
		formValues[2] = intSchedule.getContactPerson();
		formValues[3] = intSchedule.getHiringManagerCode();
		formValues[4] = intSchedule.getHiringManager();
		formValues[5] = intSchedule.getTestReqCode();
		formValues[6] = intSchedule.getTestRequirements();
		formValues[7] = intSchedule.getConveyanceDetail();
		formValues[8] = intSchedule.getInterviewCode();
		formValues[9] = request.getParameter("headerView");
		formValues[10] = intSchedule.getF9Flag();
		request.setAttribute("formValues", formValues);//this header object is sent in post resume

		/**
		 * HERE ALL THE LIST VALUES ARE SET IN AN OBJECT WHICH IS ALO SENT TO POST RESUME
		 */
		String[] candCode = request.getParameterValues("candidateCode");
		String[] intRountType = request.getParameterValues("intRoundType");
		String[] intDate = request.getParameterValues("intDate");
		String[] intTime = request.getParameterValues("intTime");
		String[] venue = request.getParameterValues("venue");
		String[] comments = request.getParameterValues("comments");
		Object[][] formListValues = null;
		if (candCode != null && candCode.length > 0) {
			try {
				formListValues = new Object[candCode.length][9];
				int j = 1;
				for (int i = 0; i < formListValues.length; i++) {
					String[] interviewerCode = request
							.getParameterValues("paraFrm_interviewerCode" + j);
					String[] interviewerName = request
							.getParameterValues("paraFrm_interviewerName" + j);
					String[] intDtlCode = request
							.getParameterValues("interviewDtlCode");

					if (intSchedule.getFromSchdIntListFlag().equals("true")) {
						formListValues[i][0] = intDtlCode[i];
					} else {
						formListValues[i][0] = "";
					}
					formListValues[i][1] = intRountType[i];
					formListValues[i][2] = intDate[i];
					formListValues[i][3] = intTime[i];
					formListValues[i][4] = venue[i];
					formListValues[i][5] = interviewerCode[0];
					formListValues[i][6] = interviewerName[0];
					formListValues[i][7] = comments[i];
					formListValues[i][8] = candCode[i];
					j++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("formListValues", formListValues);
		}
		return "postResume";
	}

	/**
	 * THIS METHOD GETS ALL THE PREVIOUSLY OBJECT WHICH WAS SENT TO THE POST REUME FORM BACK...WITH ADDITIONAL
	 * CANDIDATE CODE WHICH IS ADDED IN THE POST RESUME
	 * @return
	 */
	public String returnFromPostResume() {
		try {
			String reqCode = request.getParameter("requisitionCode");
			String candCode = (String) request.getAttribute("candCode");
			Object[] formValues = (Object[]) request.getAttribute("formValues");//HEADER OBJECT
			Object[][] formListValues = (Object[][]) request
					.getAttribute("formListValues");//LIST VALUES
			Object[] onlineformValues = (Object[]) request
					.getAttribute("onlineformValues");
			Object[][] onlineformListValues = (Object[][]) request
					.getAttribute("onlineformListValues");
			if (String.valueOf(formValues[0]).equals("testResult")) {
				intSchedule.setHidTestResultFlag("true");
			}
			intSchedule.setHeaderView(String.valueOf(formValues[9]));
			intSchedule.setFromDate(String.valueOf(formValues[1]));
			intSchedule.setToDate(String.valueOf(formValues[2]));
			intSchedule.setSearchCriteria(String.valueOf(formValues[6]));
			intSchedule.setMarksObtained(String.valueOf(formValues[7]));
			intSchedule.setResultType(String.valueOf(formValues[8]));
			intSchedule.setHidTestTypeForSchTest(String.valueOf(formValues[5]));
			for (int i = 0; i < formValues.length; i++) {
				logger.info("formValues value ===================" + i + "  "
						+ formValues[i]);
			}
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
			/**
			 * HERE THE CANDIDATE LIST IS AGAIN SET FWITH THE PREVIOUS CANDIDATES AND THE NEW CANDIDATE WHICH
			 * IS ADDED IN THE POST RESUME....
			 */
			intSchedule.setTestFlag("true");
			model.setListAfterPostResume(intSchedule, request, formValues,
					reqCode, candCode, formListValues, onlineformValues,
					onlineformListValues);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * this method is used to delete the records from the Candidate List
	 * @return
	 * @throws Exception
	 */
	public String deleteList() throws Exception {
		try {
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
			model.deleteList(intSchedule, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * this method is called from the INTERVIEW SCHEDULED LIST....THE SELECTED RECORD IS SET IN THE LIST BELOW
	 * AND THE RESPECTIVE REQVISITION CALUES ARE ALSO SET.
	 * @return
	 * @throws Exception
	 */
	public String fromScheduleInterviewList() throws Exception {
		try {
			intSchedule.setF9Flag("false");
			intSchedule.setHeaderView("true");
			String[] reqCode = request.getParameterValues("requisitionCode");
			String[] interviewCode = request
					.getParameterValues("interviewCode");
			String[] interviewDtlCode = request
					.getParameterValues("interviewDtlCode");
			String[] radio = request.getParameterValues("radioButton");//THIS IS RADIO BUTTON VALUE FOR WHICH ROW IT IS CHECKED
			intSchedule.setFromSchdIntListFlag("true");
			String requisitionCode = "";
			String intervCode = "";
			String intervDtlCode = "";
			if (radio != null && radio.length > 0) {
				/**
				 * HERE FROM (radio[0] - 1 ) is done bcoz the value of radio is 1 greater from the row value.
				 */
				requisitionCode = reqCode[Integer.parseInt(radio[0]) - 1];
				intervCode = interviewCode[Integer.parseInt(radio[0]) - 1];
				intervDtlCode = interviewDtlCode[Integer.parseInt(radio[0]) - 1];
				InterviewScheduleModel model = new InterviewScheduleModel();
				model.initiate(context, session);
				model.getRecordForReschedule(intSchedule, request,
						requisitionCode, intervCode, intervDtlCode);
				model.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String fromIntStatListNextIntRound() throws Exception {
		return SUCCESS;
	}

	public String getRecordForReq() throws Exception {
		try {
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
			model.getRecForReq(intSchedule, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**Added by Pradeep on Date:07-10-2009
	 * following function is called to display the name,address,email id and mobile no. of the person 
	 * when that person is selected from  the Contact Person pop up window. 
	 * @return
	 */
	public String getContactDetails() throws Exception {
		try {
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
			model.getContactDetails(intSchedule);
			model.getCandidateDetails(intSchedule, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * this method is to display all the contact person name
	 * @return
	 * @throws Exception
	 */
	public String f9CntPerson() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS = 'S' ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("personid"), getMessage("personname") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "cntPersonToken", "contactPerson",
				"cntPersonId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "InterviewSchedule_getContactDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * this method is to display all the recruiter name
	 * @return
	 * @throws Exception
	 */
	public String f9Interviewer() throws Exception {
		logger.info("intSchedule.getRowId()  " + intSchedule.getRowId());
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT EMP_FNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC "
				+ "WHERE EMP_STATUS='S' ORDER BY UPPER(EMP_FNAME||' '||EMP_LNAME)  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("interviewerName") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {
				"paraFrm_interviewerName" + intSchedule.getRowId(),
				"paraFrm_interviewerCode" + intSchedule.getRowId() };

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

	/**
	 * this method is to display all the contact person name
	 * @return
	 * @throws Exception
	 */
	public String f9Requisition() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT REQS_NAME,TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),  RANK_NAME, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),REQS_CODE,REQS_HIRING_MANAGER, REQS_POSITION "
				+ " FROM HRMS_REC_REQS_HDR "
				+ " INNER JOIN HRMS_RANK ON (RANK_ID = REQS_POSITION) "
				+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER) "
				+ " INNER JOIN HRMS_REC_VACPUB_HDR ON (PUB_REQS_CODE =  REQS_CODE) "
				+ "  INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE =  HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
				+ " INNER JOIN HRMS_REC_VACPUB_RECDTL ON (HRMS_REC_VACPUB_HDR.PUB_CODE =  HRMS_REC_VACPUB_RECDTL.PUB_CODE "
				+ " AND PUB_REC_EMPID ="
				+ intSchedule.getUserEmpId()
				+ ")  "
				+ " WHERE REQS_APPROVAL_STATUS IN ('A','Q') AND REQS_STATUS = 'O' AND PUB_STATUS = 'P' "
				+ " ORDER BY REQS_DATE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("reqs.code"), getMessage("reqs.date"),
				getMessage("position"), getMessage("hiring.mgr"),
				"Required By Date" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "15", "25", "30", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "requisitionName", "reqDate", "position",
				"hiringManager", "dummyField", "requisitionCode",
				"hiringManagerCode", "positionCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "InterviewSchedule_getRecordForReq.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String generateReport() {
		try {
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
			String[] labelHeaders = { getMessage("reqs.code"),
					getMessage("position"), getMessage("hiring.mgr"),
					getMessage("Contact.Person"),
					getMessage("Conveyance.Details"),
					getMessage("Select.Interview.Requirements") };
			String[] labelCandHeaders = { "Sr No", getMessage("cand.name"),
					getMessage("Interview.Round.Type"),
					getMessage("Interview.Date"), getMessage("Interview.Time"),
					getMessage("Venue"), getMessage("Interviewer"),
					getMessage("Comments") };
			model.generatePdfreport(intSchedule, request, response,
					labelHeaders, labelCandHeaders);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String viewTestDetails() throws Exception {
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!("".equals(poolName) || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String fileDataPath = this.getText("data_path") + "/upload" + poolName + "/OnlineTestDocs/";
			this.intSchedule.setDataPath(fileDataPath);
			
			String testDetailCode = request.getParameter("testDetailCode");
			if (testDetailCode != null) {
				intSchedule.setTestDetailCode(testDetailCode);
			}
			
			String onlineTestTemplateCode = request.getParameter("onlineTestTemplateCode");
			if (onlineTestTemplateCode != null) {
				intSchedule.setOnlineTestTemplateCode(onlineTestTemplateCode);
			}
			
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
			model.viewTestDetails(intSchedule);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewTestDetails";
	}

	/**
	 * Method : openAttachedFile().
	 * Purpose : Method to open attached file.
	 * @throws IOException - Exception.
	 */
	public void openUploadedFile() throws IOException {
		String addedFile = request.getParameter("fileName");
		final String[] extension = addedFile.replace(".","#").split("#");
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = this.intSchedule.getDataPath();
		final String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = "pdf";
			final String applnDoc = "doc";
			final String applnXls = "xls";
			final String applnXlsx = "xlsx";
			final String applnJpg = "jpg";
			final String applnTxt = "gif";
			final String applnGif = "txt";

			final String mimeTypeAcrobat = "acrobat";
			final String mimeTypeMSWord = "msword";
			final String mimeTypeMSExcel = "msexcel";
			final String mimeTypeJpg = "jpg";
			final String mimeTypeTxt = "gif";
			final String mimeTypeGif = "txt";

			if (applnPdf.equals(ext)) {
				mimeType = mimeTypeAcrobat;
			} else if (applnDoc.equals(ext)) {
				mimeType = mimeTypeMSWord;
			} else if (applnXls.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnXlsx.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnJpg.equals(ext)) {
				mimeType = mimeTypeJpg;
			} else if (applnTxt.equals(ext)) {
				mimeType = mimeTypeTxt;
			} else if (applnGif.equals(ext)) {
				mimeType = mimeTypeGif;
			}

			response.setHeader("Content-type", "application/" + mimeType);
			response.setHeader("Content-disposition", "attachment;filename=\"" 	+ addedFile + "\"");

			int iChar;
			fsStream = new FileInputStream(filePath);
			oStream = response.getOutputStream();

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (fsStream != null) {
				fsStream.close();
			}

			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
	}
	
	public String nextAction() throws Exception {
		try {
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
			int seq = Integer.parseInt(String.valueOf(intSchedule
					.getQuestionSequence())) + 1;
			intSchedule.setQuestionSequence(String.valueOf(seq));
			model.viewTestDetails(intSchedule);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewTestDetails";
	}

	public String previousAction() throws Exception {
		try {
			InterviewScheduleModel model = new InterviewScheduleModel();
			model.initiate(context, session);
			int seq = Integer.parseInt(String.valueOf(intSchedule
					.getQuestionSequence())) - 1;
			intSchedule.setQuestionSequence(String.valueOf(seq));
			model.viewTestDetails(intSchedule);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewTestDetails";
	}

}
