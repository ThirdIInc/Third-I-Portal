package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.TestSchedule;
import org.paradyne.model.recruitment.TestScheduleModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author varunk
 * 
 */
public class TestScheduleAction extends ParaActionSupport {
	TestSchedule testSchedule;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		testSchedule = new TestSchedule();
		testSchedule.setMenuCode(347);
	}

	public Object getModel() {
		return testSchedule;
	}

	public TestSchedule getTestSchedule() {
		return testSchedule;
	}

	public void setTestSchedule(TestSchedule testSchedule) {
		this.testSchedule = testSchedule;
	}

	public String fromScreeningStatus() {
		try {
			testSchedule.setF9Flag("false");
			testSchedule.setRefFlag("true");
			String cancelStatus = request.getParameter("status");
			if (cancelStatus.equals("B")) {
				testSchedule.setRefCanTestFlag("true");
			} else {
				testSchedule.setRefCanTestFlag("false");
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
			for (int i = 0; i < requisitionCode.length; i++) {
				if (String.valueOf(check[i]).equals("Y")) {
					testSchedule.setRequisitionName(requisitionName[i]);
					testSchedule.setRequisitionCode(requisitionCode[i]);
					testSchedule.setHiringManager(hiringManager[i]);
					testSchedule.setPosition(position[i]);
				}
			}
			TestScheduleModel model = new TestScheduleModel();
			model.initiate(context, session);
			model.viewCandidateList(testSchedule, request, requisitionName,
					check, requisitionCode, position, candidateName,
					candidateCode, recruiterCode, hiringMgrCode, hiringManager);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String fromTestRescheduleList() throws Exception {
		try {
			String[] reqCode = request.getParameterValues("requisitionCode");
			String[] radio = request.getParameterValues("radioButton");// THIS
																		// IS
																		// RADIO
																		// BUTTON
																		// VALUE
																		// FOR
																		// WHICH
																		// ROW
																		// IT IS
																		// CHECKED
			String[] testCod = request.getParameterValues("testCode");
			String[] tesDtlCode = request.getParameterValues("testDtlCode");
			testSchedule.setFromTestRescheduleFlag("true");
			testSchedule.setIsFrmTestReschedule("true");
			testSchedule.setF9Flag("false");
			String requisitionCode = "";
			String testCode = "";
			String testDtlCode = "";
			if (radio != null && radio.length > 0) {
				/**
				 * HERE FROM (radio[0] - 1 ) is done bcoz the value of radio is
				 * 1 greater from the row value.
				 */
				requisitionCode = reqCode[Integer.parseInt(radio[0]) - 1];
				testCode = testCod[Integer.parseInt(radio[0]) - 1];
				testDtlCode = tesDtlCode[Integer.parseInt(radio[0]) - 1];
				testSchedule.setTestDtlCode(testDtlCode);
				TestScheduleModel model = new TestScheduleModel();
				model.initiate(context, session);
				model.getRecordForTestReschedule(testSchedule, request,
						requisitionCode, testCode, testDtlCode);
				model.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String scheduleTest() throws Exception {
		try {
			String[] check = request.getParameterValues("chk");
			String[] requisitionName = request.getParameterValues("reqName");
			String[] requisitionCode = request
					.getParameterValues("requisitionCodeIterator");
			String[] candidateName = request
					.getParameterValues("candidateName");
			String[] candidateCode = request
					.getParameterValues("candidateCode");
			String[] recruiterCode = request.getParameterValues("recruiterId");
			String[] hiringManager = request
					.getParameterValues("hiringManagerIterator");
			String[] hiringMgrCode = request
					.getParameterValues("hiringManagerCode");
			String[] emailId = request.getParameterValues("emailId");
			String[] testRound = request.getParameterValues("testRoundType");
			String[] testDate = request.getParameterValues("testDate");
			String[] testTime = request.getParameterValues("testTime");
			String[] contactNo = request.getParameterValues("contactNo");
			String[] comments = request.getParameterValues("comments");
			String[] checkBox = request.getParameterValues("checkBox");
			String testtemplatecode = (String) request
					.getParameter("templateId");
			TestScheduleModel model = new TestScheduleModel();
			model.initiate(context, session);
			if (testSchedule.getFromTestRescheduleFlag().equals("true")) {
				String result = model.saveSchedule(testSchedule, check,
						requisitionName, requisitionCode, candidateName,
						candidateCode, recruiterCode, hiringManager,
						hiringMgrCode, emailId, testDate, testTime, contactNo,
						comments, checkBox, testtemplatecode, testRound);
				addActionMessage("Candidates Rescheduled for test successfully");
				return "testRescheduleList";
			} else if (testSchedule.getFromTestResultFlag().equals("true")) {
				String result = model.saveScheduleForMultipleTest(testSchedule,
						check, requisitionName, requisitionCode, candidateName,
						candidateCode, recruiterCode, hiringManager,
						hiringMgrCode, emailId, testDate, testTime, contactNo,
						comments, checkBox, testtemplatecode, testRound);
				addActionMessage("Candidates scheduled for next test round successfully");
				return "testResultForm";
			} else {
				String result = model.saveSchedule(testSchedule, check,
						requisitionName, requisitionCode, candidateName,
						candidateCode, recruiterCode, hiringManager,
						hiringMgrCode, emailId, testDate, testTime, contactNo,
						comments, checkBox, testtemplatecode, testRound);
				if (result == "true") {
					addActionMessage("Candidates scheduled for test successfully");
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * method name : test checklist purpose : to display all the available test
	 * checklist in new window return type : String parameter : none
	 */
	public String testChecklist() {
		try {
			TestScheduleModel model = new TestScheduleModel();
			model.initiate(context, session);
			model.testChecklist(testSchedule, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "testChecklist";
	}

	/**
	 * this method is used to delete the records from the Candidate List
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteList() throws Exception {
		try {
			TestScheduleModel model = new TestScheduleModel();
			model.initiate(context, session);
			model.deleteList(testSchedule, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * in this method Object is created and is used for setting attributes which
	 * will then be sent to Post Resume form.
	 * 
	 * @return
	 */
	public String toPostReumeForm() {
		try {
			Object[] formValues = new Object[20];
			formValues[0] = "testSch";
			formValues[1] = testSchedule.getCntPersonId();
			formValues[2] = testSchedule.getContactPerson();
			formValues[3] = testSchedule.getVenue();
			formValues[4] = testSchedule.getTestType();
			formValues[5] = testSchedule.getTestReqCode();
			formValues[6] = testSchedule.getTestRequirements();
			formValues[7] = testSchedule.getTemplateId();
			formValues[8] = testSchedule.getTestTemplate();
			formValues[9] = testSchedule.getConveyanceDetail();
			formValues[10] = testSchedule.getIsFrmTestReschedule();
			formValues[11] = testSchedule.getTestDtlCode();
			formValues[12] = testSchedule.getF9Flag();
			formValues[13] = testSchedule.getFromTestResultFlag();
			formValues[14] = testSchedule.getHidTestTypeForSchTest();
			formValues[15] = testSchedule.getResultType();
			formValues[16] = testSchedule.getFromDate();
			formValues[17] = testSchedule.getToDate();
			formValues[18] = testSchedule.getSearchCriteria();
			formValues[19] = testSchedule.getMarksObtained();
			request.setAttribute("formValues", formValues);
			/**
			 * HERE ALL THE LIST VALUES ARE SET IN AN OBJECT WHICH IS ALO SENT
			 * TO POST RESUME
			 */
			String[] candCode = request.getParameterValues("candidateCode");
			String[] testDate = request.getParameterValues("testDate");
			String[] testTime = request.getParameterValues("testTime");
			String[] comments = request.getParameterValues("comments");
			if (candCode != null && candCode.length != 0) {
				Object[][] formListValues = new Object[candCode.length][4];
				for (int i = 0; i < candCode.length; i++) {
					formListValues[i][0] = candCode[i];
					formListValues[i][1] = testDate[i];
					formListValues[i][2] = testTime[i];
					formListValues[i][3] = comments[i];
				}
				request.setAttribute("formListValues", formListValues);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "postResume";
	}

	public String fromTestResult() {
		try {
			String reqCode = request.getParameter("requisitionCode");
			String candCode = (String) request.getAttribute("candCode");
			Object[] formValues = (Object[]) request.getAttribute("formValues");
			Object[][] formListValues = (Object[][]) request
					.getAttribute("formListValues");// LIST VALUES
			testSchedule.setFromTestResultFlag("true");
			TestScheduleModel model = new TestScheduleModel();
			model.initiate(context, session);
			model.setListAfterTestResult(reqCode, candCode, formValues,
					formListValues, testSchedule);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String returnFromPostResume() {
		try {
			String reqCode = request.getParameter("requisitionCode");
			String candCode = (String) request.getAttribute("candCode");
			Object[] formValues = (Object[]) request.getAttribute("formValues");
			Object[][] formListValues = (Object[][]) request
					.getAttribute("formListValues");// LIST VALUES
			if (formValues[10].equals("true")) {
				testSchedule.setFromTestRescheduleFlag("true");
				testSchedule.setTestDtlCode(String.valueOf(formValues[11]));
			}
			testSchedule.setFromTestResultFlag(String.valueOf(formValues[13]));
			if (testSchedule.getFromTestResultFlag().equals("true")) {
				testSchedule.setHidTestTypeForSchTest(String
						.valueOf(formValues[14]));
				testSchedule.setResultType(String.valueOf(formValues[15]));
				testSchedule.setFromDate(String.valueOf(formValues[16]));
				testSchedule.setToDate(String.valueOf(formValues[17]));
				testSchedule.setSearchCriteria(String.valueOf(formValues[18]));
				testSchedule.setMarksObtained(String.valueOf(formValues[19]));
			}
			TestScheduleModel model = new TestScheduleModel();
			model.initiate(context, session);
			model.setListAfterPostResume(testSchedule, formValues, reqCode,
					candCode, formListValues);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getRecordForReq() throws Exception {
		try {
			TestScheduleModel model = new TestScheduleModel();
			model.initiate(context, session);
			model.getRecForReq(testSchedule, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * Added by Pradeep on Date:06-10-2009 following function is called to
	 * display the name,address,email id and mobile no. of the person when that
	 * person is selected from the Contact Person pop up window.
	 * 
	 * @return
	 */
	public String getContactDetails() throws Exception {
		try {
			String[] check = request.getParameterValues("chk");
			String[] requisitionName = request.getParameterValues("reqName");
			String[] requisitionCode = request
					.getParameterValues("requisitionCodeIterator");
			String[] candidateName = request
					.getParameterValues("candidateName");
			String[] candidateCode = request
					.getParameterValues("candidateCode");
			String[] recruiterCode = request.getParameterValues("recruiterId");
			String[] hiringManager = request
					.getParameterValues("hiringManagerIterator");
			String[] hiringMgrCode = request
					.getParameterValues("hiringManagerCode");
			String[] emailId = request.getParameterValues("emailId");
			String[] testRound = request.getParameterValues("testRoundType");
			String[] testDate = request.getParameterValues("testDate");
			String[] testTime = request.getParameterValues("testTime");
			String[] contactNo = request.getParameterValues("contactNo");
			String[] comments = request.getParameterValues("comments");
			String[] checkBox = request.getParameterValues("checkBox");
			String testtemplatecode = (String) request
					.getParameter("templateId");
			TestScheduleModel model = new TestScheduleModel();
			model.initiate(context, session);
			model.getContactDetails(testSchedule);
			model.getCandidateDetails(testSchedule, check, requisitionName,
					requisitionCode, candidateName, candidateCode,
					recruiterCode, hiringManager, hiringMgrCode, emailId,
					testDate, testTime, contactNo, comments, checkBox,
					testtemplatecode, testRound);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String f9testtemplate() throws Exception {
		String query = "	 SELECT TEMPLATE_TEST_NAME, TO_CHAR(TEMPLATE_DURATION, 'HH24:MI:SS'), DECODE(TEMPLATE_TEST_TYPE,'O', 'Objective', 'S', 'Subjective', 'B', 'Both'),"
				+ " TEMPLATE_TOTAL_QUES,"
				+ " TEMPLATE_TOTAL_MARKS, TEMPLATE_PASSING_MARKS, TEMPLATE_CODE "
				+ " FROM HRMS_REC_TEST_TEMPLATE"
				+ " ORDER BY UPPER(TEMPLATE_TEST_NAME)";

		String[] headers = { getMessage("test.name"),
				getMessage("test.duration"), getMessage("test.type"),
				getMessage("total.ques"), getMessage("total.marks"),
				getMessage("passing.marks") };

		String[] headerWidth = { "30", "10", "10", "15", "10", "10" };

		String[] fieldNames = { "testTemplate", "testDuration", "testType1",
				"totalNoQue", "testTotalMarks", "passingMark", "templateId" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };

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
	 * 
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
		String submitToMethod = "TestSchedule_getContactDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * this method is to display all the contact person name
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Requisition() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT REQS_NAME,TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),  RANK_NAME,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'), "
				+ " REQS_CODE, REQS_POSITION, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME "
				+ " FROM HRMS_REC_REQS_HDR "
				+ " INNER JOIN HRMS_RANK ON (RANK_ID = REQS_POSITION) "
				+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER) "
				+ " INNER JOIN HRMS_REC_VACPUB_HDR ON (PUB_REQS_CODE =  REQS_CODE) "
				+ " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE =  HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
				+ " INNER JOIN HRMS_REC_VACPUB_RECDTL ON (HRMS_REC_VACPUB_HDR.PUB_CODE =  HRMS_REC_VACPUB_RECDTL.PUB_CODE "
				+ " AND PUB_REC_EMPID ="
				+ testSchedule.getUserEmpId()
				+ ")  "
				+ " WHERE REQS_APPROVAL_STATUS IN ('A','Q') AND REQS_STATUS = 'O' AND PUB_STATUS = 'P' "
				+ " ORDER BY REQS_DATE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("reqs.code"), getMessage("reqs.date"),
				getMessage("position"), "Required By Date" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "15", "25", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "requisitionName", "reqDate", "position",
				"dummyField", "requisitionCode", "positionCode",
				"hiringManager" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };

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
		String submitToMethod = "TestSchedule_getRecordForReq.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

}
