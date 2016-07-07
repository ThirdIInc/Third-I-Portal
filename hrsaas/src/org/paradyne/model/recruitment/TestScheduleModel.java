package org.paradyne.model.recruitment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.TestSchedule;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;

/**
 * @author varunk
 * 
 */

public class TestScheduleModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TestScheduleModel.class);

	/*
	 * Added by Pradeep on date:06-10-2009 following function is called to
	 * display the contact person name,his cell number,email id and city name
	 */
	public void getContactDetails(TestSchedule bean) {
		try {
			String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,LOCATION_NAME,ADD_EMAIL,ADD_MOBILE "
					+ " FROM HRMS_EMP_OFFC LEFT JOIN HRMS_EMP_ADDRESS ON HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID"
					+ " LEFT JOIN HRMS_LOCATION ON HRMS_LOCATION.LOCATION_CODE=HRMS_EMP_ADDRESS.ADD_CITY"
					+ " WHERE HRMS_EMP_OFFC.EMP_ID="
					+ bean.getCntPersonId()
					+ " AND ADD_TYPE='P'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean.setContactPerson(String.valueOf(data[0][0]) + "\n"
						+ checkNull(String.valueOf(data[0][1])) + "\n"
						+ checkNull(String.valueOf(data[0][2])) + "\n"
						+ checkNull(String.valueOf(data[0][3])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void viewCandidateList(TestSchedule testSchedule,
			HttpServletRequest request, String[] requisitionName,
			String[] check, String[] requisitionCode, String[] position,
			String[] candidateName, String[] candidateCode,
			String[] recruiterCode, String[] hiringMgrCode,
			String[] hiringManager) {

		Object[][] candidateData = null;
		ArrayList<Object> candidateList = new ArrayList<Object>();
		try {
			for (int i = 0; i < requisitionCode.length; i++) {
				TestSchedule bean = new TestSchedule();
				if (String.valueOf(check[i]).equals("Y")) {
					String query = "SELECT CAND_EMAIL_ID,NVL(CAND_MOB_PHONE,' ') FROM HRMS_REC_CAND_DATABANK WHERE CAND_CODE ="
							+ candidateCode[i] + "";
					candidateData = getSqlModel().getSingleResult(query);
					if (candidateData == null) {
						bean.setEmailId("");
						bean.setContactNo("");
					} else if (candidateData.length == 0) {
						bean.setEmailId("");
						bean.setContactNo("");
					} else {
						bean.setEmailId(checkNull(String
								.valueOf(candidateData[0][0])));
						if (String.valueOf(candidateData[0][1]).equals("")
								|| String.valueOf(candidateData[0][1]).equals(
										null)
								|| String.valueOf(candidateData[0][1]).equals(
										"null")) {
							bean.setContactNo("");
						} else {
							bean.setContactNo(checkNull(String
									.valueOf(candidateData[0][1])));
						}
					}
					bean.setRequisitionCodeIterator(requisitionCode[i]);
					bean.setReqName(requisitionName[i]);
					bean.setCandidateName(candidateName[i]);
					bean.setCandidateCode(candidateCode[i]);
					bean.setHiringManagerIterator(hiringManager[i]);
					bean.setHiringManagerCode(hiringMgrCode[i]);
					bean.setRecruiterId(recruiterCode[i]);

					candidateList.add(bean);
				}
			}
			testSchedule.setCandidateList(candidateList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * CUrrently TEST TEMPLATE is HARD CODED
	 * 
	 * @param testSchedule
	 * @param request
	 * @param check
	 * @param requisitionName
	 * @param requisitionCode
	 * @param candidateName
	 * @param candidateCode
	 * @param recruiterCode
	 * @param hiringManager
	 * @param hiringMgrCode
	 * @param emailId
	 * @param testDate
	 * @param testTime
	 * @param contactNo
	 * @param comments
	 * @param testRound
	 */
	public String saveSchedule(TestSchedule testSchedule, String[] check,
			String[] requisitionName, String[] requisitionCode,
			String[] candidateName, String[] candidateCode,
			String[] recruiterCode, String[] hiringManager,
			String[] hiringMgrCode, String[] emailId, String[] testDate,
			String[] testTime, String[] contactNo, String[] comments,
			String[] checkBox, String testtemplatecode, String[] testRound) {
		String result = "";
		try {
			int resetCount = 0;
			boolean schTestHdr = false;
			boolean schTestDtl = false;
			boolean updResumeBank = false;
			String testCodeForEmail = "";
			Object[][] maxCode = null;
			if (testSchedule.getFromTestRescheduleFlag().equals("true")) {
				String updateDtlForSingle = "UPDATE HRMS_REC_SCHTEST_DTL SET TEST_STATUS = 'R' "
						+ " WHERE TEST_DTL_CODE = "
						+ testSchedule.getTestDtlCode() + " ";
				getSqlModel().singleExecute(updateDtlForSingle);
			}
			ArrayList<Object> tableList = new ArrayList<Object>();
			String maxQuery = "SELECT NVL(MAX(TEST_CODE),0)+1 FROM HRMS_REC_SCHTEST";
			maxCode = getSqlModel().getSingleResult(maxQuery);
			testCodeForEmail = String.valueOf(maxCode[0][0]);
			if (maxCode != null && maxCode.length > 0) {
				String query = "INSERT INTO HRMS_REC_SCHTEST (TEST_CODE,TEST_REQS_CODE,TEST_CONTACT_PER,TEST_VENUE_DET,TEST_CONVEY, "
						+ " TEST_TYPE,TEST_REQM,TEST_TEMPLATE,TEST_REC_EMPID) "
						+ " VALUES("
						+ maxCode[0][0]
						+ ","
						+ testSchedule.getRequisitionCode()
						+ ",'"
						+ testSchedule.getContactPerson()
						+ "', '"
						+ testSchedule.getVenue()
						+ "','"
						+ testSchedule.getConveyanceDetail()
						+ "','"
						+ testSchedule.getTestType()
						+ "', '"
						+ testSchedule.getTestRequirements()
						+ "',"
						+ testtemplatecode
						+ ","
						+ testSchedule.getUserEmpId()
						+ ")";
				schTestHdr = getSqlModel().singleExecute(query);
			}
			if (schTestHdr) {
				Object[][] saveData = null;
				if (requisitionCode != null && requisitionCode.length > 0) {
					saveData = new Object[requisitionCode.length][1];
					for (int i = 0; i < requisitionCode.length; i++) {
						TestSchedule bean = new TestSchedule();
						if (String.valueOf(checkBox[i]).equals("Y")) {
							resetCount++;
							String query = "INSERT INTO HRMS_REC_SCHTEST_DTL (TEST_DTL_CODE,TEST_CODE,TEST_CAND_CODE,TEST_REQS_CODE,TEST_DATE,TEST_TIME,TEST_COMMENTS,TEST_ROUND_TYPE) "
									+ " VALUES ((SELECT NVL(MAX(TEST_DTL_CODE),0)+1 FROM HRMS_REC_SCHTEST_DTL),"
									+ maxCode[0][0]
									+ ","
									+ " "
									+ candidateCode[i]
									+ ","
									+ testSchedule.getRequisitionCode()
									+ ",TO_DATE('"
									+ testDate[i]
									+ "','DD-MM-YYYY'),'"
									+ testTime[i]
									+ "','"
									+ comments[i] + "','" + testRound[i] + "')";
							schTestDtl = getSqlModel().singleExecute(query);

							if (schTestDtl) {
								String updQuery = "UPDATE HRMS_REC_RESUME_BANK SET RESUME_SHEDULE_STATUS = 'T' "
										+ " WHERE RESUME_REQS_CODE="
										+ testSchedule.getRequisitionCode()
										+ " AND RESUME_CAND_CODE="
										+ candidateCode[i]
										+ " AND RESUME_STATUS IN ('T', 'B') ";

								updResumeBank = getSqlModel().singleExecute(
										updQuery);
							}
						}

						if (!(String.valueOf(checkBox[i]).equals("Y"))) {
							bean.setReqName(requisitionName[i]);
							bean.setCandidateName(candidateName[i]);
							bean.setCandidateCode(candidateCode[i]);
							bean.setEmailId(checkNull(emailId[i]));
							bean.setContactNo(checkNull(contactNo[i]));
							bean.setTestRoundType(checkNull(testRound[i]));
							bean.setTestDate(testDate[i]);
							bean.setTestTime(testTime[i]);
							bean.setComments(comments[i]);
							bean.setHiringManagerIterator(hiringManager[i]);
							bean.setHiringManagerCode(hiringMgrCode[i]);
							bean.setRecruiterId(recruiterCode[i]);
							tableList.add(bean);
						}
					}
					testSchedule.setCandidateList(tableList);
					if (resetCount == candidateCode.length) {
						reset(testSchedule);
					}
				}
				result = "true";
				// String eventCode = "66";
				sendNotificationMail(testCodeForEmail, testSchedule
						.getUserEmpId(), candidateCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String saveScheduleForMultipleTest(TestSchedule testSchedule,
			String[] check, String[] requisitionName, String[] requisitionCode,
			String[] candidateName, String[] candidateCode,
			String[] recruiterCode, String[] hiringManager,
			String[] hiringMgrCode, String[] emailId, String[] testDate,
			String[] testTime, String[] contactNo, String[] comments,
			String[] checkBox, String testtemplatecode, String[] testRound) {
		String result = "";
		String testCodeForEmail = "";
		Object[][] maxCode = null;
		boolean schTestHdr = false;
		boolean schTestDtl = false;
		boolean updResumeBank = false;
		try {
			String maxQuery = "SELECT NVL(MAX(TEST_CODE),0)+1 FROM HRMS_REC_SCHTEST";
			maxCode = getSqlModel().getSingleResult(maxQuery);
			testCodeForEmail = String.valueOf(maxCode[0][0]);

			if (maxCode != null && maxCode.length > 0) {
				String query = "INSERT INTO HRMS_REC_SCHTEST (TEST_CODE,TEST_REQS_CODE,TEST_CONTACT_PER,TEST_VENUE_DET,TEST_CONVEY, "
						+ " TEST_TYPE,TEST_REQM,TEST_TEMPLATE,TEST_REC_EMPID) "
						+ " VALUES("
						+ maxCode[0][0]
						+ ","
						+ testSchedule.getRequisitionCode()
						+ ",'"
						+ testSchedule.getContactPerson()
						+ "', '"
						+ testSchedule.getVenue()
						+ "','"
						+ testSchedule.getConveyanceDetail()
						+ "','"
						+ testSchedule.getTestType()
						+ "', '"
						+ testSchedule.getTestRequirements()
						+ "',"
						+ testtemplatecode
						+ ","
						+ testSchedule.getUserEmpId()
						+ ")";
				schTestHdr = getSqlModel().singleExecute(query);
			}

			if (schTestHdr) {
				if (requisitionCode != null && requisitionCode.length > 0) {
					for (int i = 0; i < requisitionCode.length; i++) {
						if (String.valueOf(checkBox[i]).equals("Y")) {
							Object[][] checkIfAppearedForTestBefore = null;
							String queryAppeared = "SELECT HRMS_REC_SCHTEST_DTL.TEST_DTL_CODE FROM HRMS_REC_SCHTEST_DTL "
									+ " WHERE HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = " + candidateCode[i]
									+ " AND HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = " + testSchedule.getRequisitionCode()
									+ " AND HRMS_REC_SCHTEST_DTL.TEST_STATUS = 'Y'";
							checkIfAppearedForTestBefore = getSqlModel()
									.getSingleResult(queryAppeared);

							if (checkIfAppearedForTestBefore != null
									&& checkIfAppearedForTestBefore.length > 0) {
								String updatePreviousTestStatus = "UPDATE HRMS_REC_SCHTEST_DTL SET HRMS_REC_SCHTEST_DTL.TEST_STATUS = 'X' "
										+ "WHERE HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE =" + candidateCode[i]
										+ " AND HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = " + testSchedule.getRequisitionCode()
										+ " AND HRMS_REC_SCHTEST_DTL.TEST_STATUS = 'Y'";
								getSqlModel().singleExecute(updatePreviousTestStatus);
							}

							String query = "INSERT INTO HRMS_REC_SCHTEST_DTL (TEST_DTL_CODE,TEST_CODE,TEST_CAND_CODE,TEST_REQS_CODE,TEST_DATE,TEST_TIME,TEST_COMMENTS,TEST_ROUND_TYPE) "
									+ " VALUES ((SELECT NVL(MAX(TEST_DTL_CODE),0)+1 FROM HRMS_REC_SCHTEST_DTL),"
									+ maxCode[0][0]
									+ ","
									+ " "
									+ candidateCode[i]
									+ ","
									+ testSchedule.getRequisitionCode()
									+ ",TO_DATE('"
									+ testDate[i]
									+ "','DD-MM-YYYY'),'"
									+ testTime[i]
									+ "','"
									+ comments[i] + "','" + testRound[i] + "')";
							schTestDtl = getSqlModel().singleExecute(query);

							if (schTestDtl) {
								String updQuery = "UPDATE HRMS_REC_RESUME_BANK SET RESUME_SHEDULE_STATUS = 'T' "
										+ " WHERE RESUME_REQS_CODE="
										+ testSchedule.getRequisitionCode()
										+ " AND RESUME_CAND_CODE="
										+ candidateCode[i]
										+ " AND RESUME_STATUS IN ('T', 'B') ";

								updResumeBank = getSqlModel().singleExecute(
										updQuery);

							}
						}
					}
				}
				result = "true";
				sendNotificationMail(testCodeForEmail, testSchedule.getUserEmpId(), candidateCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void sendNotificationMail(String testCodeForEmail,
			String userEmpId, String[] candidateCode) {
		try {
			Object[][] testDetailCodeForEmail = null;
			if (testCodeForEmail != null && testCodeForEmail.length()>0) {
				String intDtlCodeForEmail = "SELECT TEST_DTL_CODE,TEST_CAND_CODE "
						+ " FROM HRMS_REC_SCHTEST_DTL "
						+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE) "
						+ " WHERE TEST_CODE=" + testCodeForEmail;
				testDetailCodeForEmail = getSqlModel().getSingleResult(intDtlCodeForEmail);

				if (testDetailCodeForEmail != null && testDetailCodeForEmail.length > 0) {
					for (int k = 0; k < testDetailCodeForEmail.length; k++) {
						for (int i = 0; i < candidateCode.length; i++) {
							if (candidateCode[i].equals(String.valueOf(testDetailCodeForEmail[k][1]))) {
								EmailTemplateBody template1 = new EmailTemplateBody();
								template1.initiate(context, session);
								// template1.setEmailTemplate("Template for test
								// schedule for written test");
								template1.setEmailTemplate("RMS-Mail to Candidate regarding test schedule");
								template1.getTemplateQueries();

								EmailTemplateQuery frmMailQuery = template1.getTemplateQuery(1);
								frmMailQuery.setParameter(1, userEmpId);
								EmailTemplateQuery toMailQuery = template1.getTemplateQuery(2);
								toMailQuery.setParameter(1, String.valueOf(testDetailCodeForEmail[k][1]));

								EmailTemplateQuery intSchDetailQuery1 = template1.getTemplateQuery(3);
								intSchDetailQuery1.setParameter(1, userEmpId); // recruiter
								// id
								intSchDetailQuery1.setParameter(2, String.valueOf(testDetailCodeForEmail[k][0]));

								EmailTemplateQuery intSchDetailQuery2 = template1.getTemplateQuery(4);
								intSchDetailQuery2.setParameter(1, String.valueOf(testDetailCodeForEmail[k][0]));

								template1.configMailAlert();
								template1.sendApplicationMail();
								template1.clearParameters();
								template1.terminate();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void reset(TestSchedule testSchedule) {
		try {
			testSchedule.setRequisitionName("");
			testSchedule.setRequisitionCode("");
			testSchedule.setPosition("");
			testSchedule.setPositionCode("");
			testSchedule.setContactPerson("");
			testSchedule.setCntPersonId("");
			testSchedule.setCntPersonToken("");
			testSchedule.setConveyanceDetail("");
			testSchedule.setVenue("");
			testSchedule.setTestType("");
			testSchedule.setTestRequirements("");
			testSchedule.setTestReqCode("");
			testSchedule.setTemplateId("");
			testSchedule.setTestTemplate("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * method name : testChecklist purpose : to retrieve all the test checklist
	 * and set them in a new window return type : void parameter : TestSchedule
	 * instance
	 * 
	 * @param request
	 */
	public void testChecklist(TestSchedule testSchedule,
			HttpServletRequest request) {

		try {
			String query = " SELECT CHECK_CODE,CHECK_NAME FROM HRMS_CHECKLIST WHERE CHECK_TYPE = 'I' AND CHECK_STATUS='A' ORDER BY UPPER(CHECK_NAME)";
			Object obj[][] = getSqlModel().getSingleResult(query);
			ArrayList<Object> arr = new ArrayList<Object>();
			for (int i = 0; i < obj.length; i++) {
				TestSchedule bean = new TestSchedule();
				bean.setChecklistCode(String.valueOf(obj[i][0]));
				bean.setChecklistName(String.valueOf(obj[i][1]));
				arr.add(bean);
			}
			testSchedule.setTestDataList(arr);
			String str = request.getParameter("id");
			testSchedule.setTestReqCode(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteList(TestSchedule testSchedule, HttpServletRequest request) {
		try {
			String[] requisitionName = request.getParameterValues("reqName");
			String[] requisitionCode = request
					.getParameterValues("requisitionCode");
			String[] candidateName = request
					.getParameterValues("candidateName");
			String[] candidateCode = request
					.getParameterValues("candidateCode");
			String[] recruiterCode = request.getParameterValues("recruiterId");
			String[] hiringManager = request
					.getParameterValues("hiringManager");
			String[] hiringMgrCode = request
					.getParameterValues("hiringManagerCode");
			String[] emailId = request.getParameterValues("emailId");
			String[] testDate = request.getParameterValues("testDate");
			String[] testTime = request.getParameterValues("testTime");
			String[] contactNo = request.getParameterValues("contactNo");
			String[] comments = request.getParameterValues("comments");
			String[] checkBox = request.getParameterValues("checkBox");
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (requisitionCode != null && requisitionCode.length == 0) {
				for (int i = 0; i < requisitionCode.length; i++) {
					TestSchedule bean = new TestSchedule();
					if (!(String.valueOf(checkBox[i]).equals("Y"))) {
						bean.setReqName(requisitionName[i]);
						bean.setRequisitionCodeIterator(requisitionCode[i]);
						bean.setCandidateName(candidateName[i]);
						bean.setCandidateCode(candidateCode[i]);
						bean.setEmailId(checkNull(emailId[i]));
						bean.setContactNo(checkNull(contactNo[i]));
						bean.setTestDate(testDate[i]);
						bean.setTestTime(testTime[i]);
						bean.setComments(comments[i]);
						bean.setHiringManagerIterator(hiringManager[i]);
						bean.setHiringManagerCode(hiringMgrCode[i]);
						bean.setRecruiterId(recruiterCode[i]);
						tableList.add(bean);
					}
				}
				testSchedule.setCandidateList(tableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setListAfterTestResult(String reqCode, String candCode,
			Object[] formValues, Object[][] formListValues,
			TestSchedule testSchedule) {
		try {
			if (formValues == null && formValues.length == 0) {
				testSchedule.setRequisitionCode(reqCode);
				testSchedule.setFormName(String.valueOf(formValues[0]));
				testSchedule.setFromDate(String.valueOf(formValues[1]));
				testSchedule.setToDate(String.valueOf(formValues[2]));
				testSchedule.setHidTestTypeForSchTest(String
						.valueOf(formValues[5]));
				testSchedule.setSearchCriteria(String.valueOf(formValues[6]));
				testSchedule.setMarksObtained(String.valueOf(formValues[7]));
				testSchedule.setResultType(String.valueOf(formValues[8]));
				testSchedule.setF9Flag(String.valueOf(formValues[12]));
			}

			Object[][] candidateData = null;
			ArrayList<Object> candidateList = new ArrayList<Object>();

			String query = " SELECT CAND_FIRST_NAME||' '||CAND_LAST_NAME, CAND_EMAIL_ID, NVL(CAND_MOB_PHONE,' '), CAND_CODE "
					+ "FROM HRMS_REC_CAND_DATABANK "
					+ " WHERE CAND_CODE IN("
					+ candCode + ")";
			candidateData = getSqlModel().getSingleResult(query);

			if (candidateData != null && candidateData.length != 0) {
				for (int i = 0; i < candidateData.length; i++) {
					TestSchedule bean = new TestSchedule();

					bean.setCandidateCode(String.valueOf(candidateData[i][3]));
					bean.setCandidateName(String.valueOf(candidateData[i][0]));
					bean.setEmailId(String.valueOf(candidateData[i][1]));
					bean.setContactNo(String.valueOf(candidateData[i][2]));

					bean.setRequisitionCodeIterator(testSchedule
							.getRequisitionCode());
					bean.setReqName(testSchedule.getRequisitionName());

					candidateList.add(bean);
				}
				testSchedule.setCandidateList(candidateList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setListAfterPostResume(TestSchedule testSchedule,
			Object[] formValues, String reqCode, String candCode,
			Object[][] formListValues) {
		try {
			if (formValues == null && formValues.length == 0) {
				testSchedule.setRequisitionCode(reqCode);
				testSchedule.setContactPerson(String.valueOf(formValues[2]));
				testSchedule.setVenue(String.valueOf(formValues[3]));
				testSchedule.setTestType(String.valueOf(formValues[4]));
				testSchedule.setTestReqCode(String.valueOf(formValues[5]));
				testSchedule.setTestRequirements(String.valueOf(formValues[6]));
				testSchedule.setTemplateId(String.valueOf(formValues[7]));
				testSchedule.setTestTemplate(String.valueOf(formValues[8]));
				testSchedule.setConveyanceDetail(String.valueOf(formValues[9]));
				testSchedule.setF9Flag(String.valueOf(formValues[12]));
			}

			Object[][] candidateData = null;
			ArrayList<Object> candidateList = new ArrayList<Object>();

			String query = " SELECT CAND_FIRST_NAME||' '||CAND_LAST_NAME, CAND_EMAIL_ID, NVL(CAND_MOB_PHONE,' '), CAND_CODE "
					+ "FROM HRMS_REC_CAND_DATABANK "
					+ " WHERE CAND_CODE IN("
					+ candCode + ")";

			candidateData = getSqlModel().getSingleResult(query);

			if (candidateData != null && candidateData.length != 0) {
				for (int i = 0; i < candidateData.length; i++) {
					boolean flag = false;
					TestSchedule bean = new TestSchedule();

					/**
					 * THIS FOR LOOP IS RUN TO SET THE LIST VALUES FOR THE
					 * CANDIDATES WHICH WERE PREVIOUSLYT PRESENT IN THE SCHEDULE
					 * INTERVIEW LIST.....AND WERE JUS FORWARDED TO THE POST
					 * REUME FORM
					 */
					if (formListValues != null && formListValues.length != 0) {
						for (int k = 0; k < formListValues.length; k++) {
							/**
							 * HERE THE CANDIDATE CODE ARE CHECKED....
							 * formListValues[k][8]===CONTAINS THE PREVIOUS
							 * CANDIDATE CODE WHICH WAS SENT TO POST RESUME
							 * candidateData[i][1] === THE CANDIDATE CODE WHICH
							 * ARE GET FROM THE POST RESUME
							 * 
							 */

							if (formListValues[k][0].equals(String
									.valueOf(candidateData[i][3]))) {
								bean.setTestDate(String
										.valueOf(formListValues[k][1]));
								bean.setTestTime(String
										.valueOf(formListValues[k][2]));
								bean.setComments(String
										.valueOf(formListValues[k][3]));

								bean.setCandidateCode(String
										.valueOf(candidateData[i][3]));
								bean.setCandidateName(String
										.valueOf(candidateData[i][0]));
								bean.setEmailId(String
										.valueOf(candidateData[i][1]));
								bean.setContactNo(String
										.valueOf(candidateData[i][2]));

								flag = true;
							}
						}
					}

					if (!flag) {
						bean.setCandidateCode(String
								.valueOf(candidateData[i][3]));
						bean.setCandidateName(String
								.valueOf(candidateData[i][0]));
						bean.setEmailId(String.valueOf(candidateData[i][1]));
						bean.setContactNo(String.valueOf(candidateData[i][2]));
					}
					bean.setRequisitionCodeIterator(testSchedule
							.getRequisitionCode());
					bean.setReqName(testSchedule.getRequisitionName());

					candidateList.add(bean);
				}
			}
			testSchedule.setCandidateList(candidateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRecForReq(TestSchedule testSchedule,
			HttpServletRequest request) {
		Object[][] data = null;
		try {
			String query = "SELECT RESUME_REQS_CODE,REQS_NAME,CAND_CODE,CAND_FIRST_NAME||' '||CAND_LAST_NAME,NVL(CAND_MOB_PHONE,' '),CAND_EMAIL_ID,"
					+ "   EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_REC_RESUME_BANK "
					+ " INNER JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)  "
					+ "  LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) "
					+ " WHERE RESUME_STATUS='T' AND RESUME_SHEDULE_STATUS='N' AND RESUME_REQS_CODE ="
					+ testSchedule.getRequisitionCode()
					+ ""
					+ " AND RESUME_REC_EMPID = "
					+ testSchedule.getUserEmpId()
					+ "";
			data = getSqlModel().getSingleResult(query);

			ArrayList<Object> candidateList = new ArrayList<Object>();
			for (int i = 0; i < data.length; i++) {
				TestSchedule bean = new TestSchedule();
				if (String.valueOf(data[i][5]).equals("")
						|| String.valueOf(data[i][5]).equals(null)
						|| String.valueOf(data[i][5]).equals("null")) {
					bean.setEmailId("");
				} else {
					bean.setEmailId(checkNull(String.valueOf(data[i][5])));
				}
				if (String.valueOf(data[i][4]).equals("")
						|| String.valueOf(data[i][4]).equals(null)
						|| String.valueOf(data[i][4]).equals("null")) {
					bean.setContactNo("");
				} else {
					bean.setContactNo(checkNull(String.valueOf(data[i][4])));
				}
				bean.setRequisitionCodeIterator(String.valueOf(data[i][0]));
				bean.setReqName(String.valueOf(data[i][1]));
				bean.setCandidateName(String.valueOf(data[i][3]));
				bean.setCandidateCode(String.valueOf(data[i][2]));
				bean.setHiringManagerIterator(String.valueOf(data[i][6]));
				bean.setHiringManagerCode(String.valueOf(data[i][7]));
				bean.setRecruiterId(testSchedule.getUserEmpId());
				candidateList.add(bean);
			}
			testSchedule.setCandidateList(candidateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRecordForTestReschedule(TestSchedule testSchedule,
			HttpServletRequest request, String requisitionCode,
			String testCode, String testDtlCode) {
		Object[][] reqHdrData = null;
		Object[][] testHdrData = null;
		Object[][] testDtlData = null;
		try {
			String reqHdrQuery = "SELECT REQS_NAME,RANK_NAME "
					+ " FROM HRMS_REC_REQS_HDR "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ " WHERE REQS_CODE = " + requisitionCode + "";
			reqHdrData = getSqlModel().getSingleResult(reqHdrQuery);
		} catch (Exception e) {
			logger.error("exception in reqHdrQuery", e);
		} // end of catch

		if (reqHdrData == null) {

		} // end of if
		else if (reqHdrData.length == 0) {

		} // end of else if
		else {
			testSchedule.setRequisitionName(String.valueOf(reqHdrData[0][0]));
			testSchedule.setRequisitionCode(requisitionCode);
			testSchedule.setPosition(String.valueOf(reqHdrData[0][1]));
		} // end of else

		try {
			String testHdrQuery = "SELECT TEST_CONTACT_PER,TEST_VENUE_DET,TEST_CONVEY,TEST_TYPE "
					+ " ,TEST_REQM,TEST_TEMPLATE,'',TEMPLATE_TEST_NAME "
					+ " FROM HRMS_REC_SCHTEST  "
					// +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =
					// HRMS_REC_SCHTEST.TEST_CONTACT_PER) "
					+ " INNER JOIN HRMS_REC_TEST_TEMPLATE ON (HRMS_REC_TEST_TEMPLATE.TEMPLATE_CODE = HRMS_REC_SCHTEST.TEST_TEMPLATE) "
					+ " WHERE TEST_CODE =  " + testCode + "";
			testHdrData = getSqlModel().getSingleResult(testHdrQuery);
		} catch (Exception e) {
			logger.error("exception in intHdrQuery", e);
		} // end of catch

		if (testHdrData == null) {

		} // end of if
		else if (testHdrData.length == 0) {

		} // end of else if
		else {
			testSchedule.setTestCode(testCode);
			// testSchedule.setCntPersonId(String.valueOf(testHdrData[0][0]));
			testSchedule.setContactPerson(checkNull(String
					.valueOf(testHdrData[0][0])));
			testSchedule.setTestType(checkNull(String
					.valueOf(testHdrData[0][3])));
			testSchedule.setTestTemplate(checkNull(String
					.valueOf(testHdrData[0][7])));
			testSchedule.setTemplateId(checkNull(String
					.valueOf(testHdrData[0][5])));
			testSchedule.setVenue(checkNull(String.valueOf(testHdrData[0][1])));
			testSchedule.setConveyanceDetail(checkNull(String
					.valueOf(testHdrData[0][2])));
			testSchedule.setTestRequirements(checkNull(String
					.valueOf(testHdrData[0][4])));
		} // end of else

		try {
			String testDtlQuery = "SELECT TEST_REQS_CODE,REQS_NAME,TO_CHAR(TEST_DATE,'DD-MM-YYYY'),TEST_TIME,TEST_COMMENTS,TEST_CAND_CODE,CAND_FIRST_NAME||' '||CAND_LAST_NAME, "
					+ " CAND_EMAIL_ID,NVL(CAND_MOB_PHONE,' '),TEST_ROUND_TYPE "
					+ " FROM HRMS_REC_SCHTEST_DTL "
					+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE) "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE) "
					+ " WHERE TEST_DTL_CODE = " + testDtlCode + "";
			testDtlData = getSqlModel().getSingleResult(testDtlQuery);
		} catch (Exception e) {
			logger.error("exception in intDtlQuery", e);
		}

		if (testDtlData == null) {

		} // end of if
		else if (testDtlData.length == 0) {

		} // end of else if
		else {
			testSchedule.setTestDtlCode(testDtlCode);
			ArrayList<Object> candidateList = new ArrayList<Object>();
			TestSchedule bean = new TestSchedule();
			bean.setRequisitionCodeIterator(String.valueOf(testDtlData[0][0]));
			bean.setReqName(String.valueOf(testDtlData[0][1]));
			bean.setCandidateCode(String.valueOf(testDtlData[0][5]));
			bean.setCandidateName(String.valueOf(testDtlData[0][6]));
			bean.setEmailId(checkNull(String.valueOf(testDtlData[0][7])));
			bean.setContactNo(checkNull(String.valueOf(testDtlData[0][8])));
			bean.setTestDate(checkNull(String.valueOf(testDtlData[0][2])));
			bean.setTestTime(checkNull(String.valueOf(testDtlData[0][3])));
			bean.setComments(checkNull(String.valueOf(testDtlData[0][4])));
			bean.setTestRoundType(checkNull(String.valueOf(testDtlData[0][9])));
			candidateList.add(bean);
			testSchedule.setCandidateList(candidateList);
		} // end of else

	} // end of getRecordForTestReschedule method

	public String rescheduleCandidates(TestSchedule testSchedule,
			String[] check, String[] requisitionName, String[] requisitionCode,
			String[] candidateName, String[] candidateCode,
			String[] recruiterCode, String[] hiringManager,
			String[] hiringMgrCode, String[] emailId, String[] testDate,
			String[] testTime, String[] contactNo, String[] comments,
			String[] checkBox, String testtemplatecode) {

		try {
			Object[][] chkForSingleCandidate = null;
			String query = "SELECT TEST_CAND_CODE FROM HRMS_REC_SCHTEST_DTL "
					+ " WHERE TEST_CODE = " + testSchedule.getTestCode() + "";
			chkForSingleCandidate = getSqlModel().getSingleResult(query);
			if (chkForSingleCandidate != null
					&& chkForSingleCandidate.length > 0) {
				if (chkForSingleCandidate.length == 1) {
					String updateHdrForSingle = "UPDATE HRMS_REC_SCHTEST SET TEST_CONTACT_PER = '"
							+ testSchedule.getContactPerson()
							+ "',TEST_VENUE_DET = '"
							+ testSchedule.getVenue()
							+ "',"
							+ " TEST_TYPE = '"
							+ testSchedule.getTestType()
							+ "',TEST_REQM = '"
							+ testSchedule.getTestRequirements()
							+ "' "
							+ " WHERE TEST_CODE = "
							+ testSchedule.getTestCode() + "";
					getSqlModel().singleExecute(updateHdrForSingle);

					String updateDtlForSingle = "UPDATE HRMS_REC_SCHTEST_DTL SET TEST_DATE = TO_DATE('"
							+ testDate[0]
							+ "','DD-MM-YYYY'),TEST_TIME = '"
							+ testTime[0]
							+ "',"
							+ " TEST_COMMENTS = '"
							+ comments[0]
							+ "',TEST_STATUS = 'R' "
							+ " WHERE TEST_DTL_CODE = "
							+ testSchedule.getTestDtlCode()
							+ " AND TEST_CAND_CODE = " + candidateCode[0] + "";
					getSqlModel().singleExecute(updateDtlForSingle);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getCandidateDetails(TestSchedule testSchedule, String[] check,
			String[] requisitionName, String[] requisitionCode,
			String[] candidateName, String[] candidateCode,
			String[] recruiterCode, String[] hiringManager,
			String[] hiringMgrCode, String[] emailId, String[] testDate,
			String[] testTime, String[] contactNo, String[] comments,
			String[] checkBox, String testtemplatecode, String[] testRound) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			for (int i = 0; i < requisitionCode.length; i++) {
				TestSchedule bean = new TestSchedule();
				bean.setReqName(requisitionName[i]);
				bean.setRequisitionCodeIterator(requisitionCode[i]);
				bean.setCandidateName(candidateName[i]);
				bean.setCandidateCode(candidateCode[i]);
				bean.setEmailId(checkNull(emailId[i]));
				bean.setContactNo(checkNull(contactNo[i]));
				bean.setTestRoundType(testRound[i]);
				bean.setTestDate(testDate[i]);
				bean.setTestTime(testTime[i]);
				bean.setComments(comments[i]);
				bean.setHiringManagerIterator(hiringManager[i]);
				bean.setHiringManagerCode(hiringMgrCode[i]);
				bean.setRecruiterId(recruiterCode[i]);
				tableList.add(bean);
			}
			testSchedule.setCandidateList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
