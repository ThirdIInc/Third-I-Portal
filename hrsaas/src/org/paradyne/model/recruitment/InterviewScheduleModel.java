package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Recruitment.InterviewSchedule;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author varunk
 * 
 */
public class InterviewScheduleModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(InterviewScheduleModel.class);

	/*
	 * Added by Pradeep on date:07-10-2009 following function is called to
	 * display the contact person name,his cell number,email id and city name
	 */
	public void getContactDetails(InterviewSchedule bean) {
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

	public void viewCandidateList(InterviewSchedule intSchedule,
			HttpServletRequest request, String[] check, String[] candidateName,
			String[] candidateCode, String[] recruiterCode,
			String[] hiringMgrCode, String[] hiringManager,
			String[] requisitionName, String[] requisitionCode,
			String[] position) {

		ArrayList<Object> candidateList = new ArrayList<Object>();
		int j = 1;
		try {
			for (int i = 0; i < candidateCode.length; i++) {
				InterviewSchedule bean = new InterviewSchedule();
				if (String.valueOf(check[i]).equals("Y")) {

					intSchedule.setRequisitionName(requisitionName[i]);
					intSchedule.setRequisitionCode(requisitionCode[i]);
					intSchedule.setPosition(position[i]);
					intSchedule.setHiringManager(hiringManager[i]);
					intSchedule.setHiringManagerCode(hiringMgrCode[i]);
					// bean.setRequisitionCode(requisitionCode[i]);
					// bean.setReqName(requisitionName[i]);
					bean.setCandidateName(candidateName[i]);
					bean.setCandidateCode(candidateCode[i]);
					// bean.setInterviewerName(hiringManager[i]);
					request.setAttribute("paraFrm_interviewerName" + j,
							hiringManager[i]);
					request.setAttribute("paraFrm_interviewerCode" + j,
							hiringMgrCode[i]);
					// bean.setInterviewerCode(hiringMgrCode[i]);
					bean.setRecruiterId(recruiterCode[i]);
					setRoundTypeDropDownValue(bean);
					candidateList.add(bean);
					j++;
				}
			}
			intSchedule.setCandidateList(candidateList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setRoundTypeDropDownValue(InterviewSchedule testBean) {
		try {
			TreeMap<String, String> map = new TreeMap<String, String>();
			String selectSql = " SELECT ROUND_CODE,ROUND_TYPE FROM HRMS_REC_ROUND_TYPE ORDER BY ROUND_CODE  ";
			Object dataObj[][] = getSqlModel().getSingleResult(selectSql);
			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					map.put(String.valueOf(dataObj[i][0]), String
							.valueOf(dataObj[i][1]));
				}
			}
			testBean.setInterviewRoundTypeMap(map);
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
	public void intChecklist(InterviewSchedule intSchedule,
			HttpServletRequest request) {
		try {
			String query = " SELECT CHECK_CODE,CHECK_NAME FROM HRMS_CHECKLIST WHERE CHECK_TYPE = 'I' AND CHECK_STATUS='A' ORDER BY UPPER(CHECK_NAME)";
			Object obj[][] = getSqlModel().getSingleResult(query);
			ArrayList<Object> arr = new ArrayList<Object>();
			for (int i = 0; i < obj.length; i++) {
				InterviewSchedule bean = new InterviewSchedule();
				bean.setChecklistCode(String.valueOf(obj[i][0]));
				bean.setChecklistName(String.valueOf(obj[i][1]));
				arr.add(bean);
			}
			intSchedule.setTestDataList(arr);
			String str = request.getParameter("id");
			intSchedule.setTestReqCode(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteList(InterviewSchedule intSchedule,
			HttpServletRequest request) {
		try {
			String[] intRoundType = request.getParameterValues("intRoundType");
			// String [] interviewerName =
			// request.getParameterValues("paraFrm_interviewerName");
			// String [] interviewerCode =
			// request.getParameterValues("paraFrm_interviewerCode");
			String[] candidateName = request
					.getParameterValues("candidateName");
			String[] candidateCode = request
					.getParameterValues("candidateCode");
			String[] recruiterCode = request.getParameterValues("recruiterId");
			String[] intDate = request.getParameterValues("intDate");
			String[] intTime = request.getParameterValues("intTime");
			String[] venue = request.getParameterValues("venue");
			String[] comments = request.getParameterValues("comments");
			String[] checkBox = request.getParameterValues("checkBox");
			String intName[] = null;
			String intCode[] = null;
			// String [] hiringManager =
			// request.getParameterValues("hiringManager");
			// String [] hiringMgrCode =
			// request.getParameterValues("hiringManagerCode");
			// String [] emailId = request.getParameterValues("emailId");
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (candidateCode != null && candidateCode.length > 0) {
				intName = new String[candidateCode.length];
				intCode = new String[candidateCode.length];
				int j = 1;
					// testSchedule.setReqCode(requisitionCode[1]);
					for (int i = 0; i < candidateCode.length; i++) {
						InterviewSchedule bean = new InterviewSchedule();
						intName[i] = (String) request
								.getParameter("paraFrm_interviewerName" + j);
						intCode[i] = (String) request
								.getParameter("paraFrm_interviewerCode" + j);
						if (!(String.valueOf(checkBox[i]).equals("Y"))) {

							bean.setCandidateName(candidateName[i]);
							bean.setCandidateCode(candidateCode[i]);
							bean.setIntDate(intDate[i]);
							bean.setIntTime(intTime[i]);
							bean.setIntRoundType(intRoundType[i]);
							bean.setVenue(venue[i]);
							request.setAttribute("paraFrm_interviewerName" + j,
									intName[i]);
							request.setAttribute("paraFrm_interviewerCode" + j,
									intCode[i]);
							// bean.setInterviewerName(interviewerName[i]);
							// bean.setInterviewerCode(interviewerCode[i]);
							bean.setComments(comments[i]);
							bean.setRecruiterId(recruiterCode[i]);
							// bean.setHiringManager(hiringManager[i]);
							// bean.setHiringManagerCode(hiringMgrCode[i]);
							// bean.setReqName(requisitionName[i]);
							// bean.setRequisitionCode(requisitionCode[i]);
							// bean.setEmailId(emailId[i]);
							// bean.setContactNo(contactNo[i]);
							setRoundTypeDropDownValue(bean);
							tableList.add(bean);
							j++;
						}
					}
				intSchedule.setCandidateList(tableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Added by Pradeep Kumar Sahoo on Date:07-10-2009 following function is
	 * called to display the candidate details when any record is selected from
	 * the contact person pop up window.
	 * 
	 * @param intSchedule
	 * @param request
	 */

	public void getCandidateDetails(InterviewSchedule intSchedule,
			HttpServletRequest request) {
		try {
			String[] intRoundType = request.getParameterValues("intRoundType");
			String[] candidateName = request
					.getParameterValues("candidateName");
			String[] candidateCode = request
					.getParameterValues("candidateCode");
			String[] recruiterCode = request.getParameterValues("recruiterId");
			String[] intDate = request.getParameterValues("intDate");
			String[] intTime = request.getParameterValues("intTime");
			String[] venue = request.getParameterValues("venue");
			String[] comments = request.getParameterValues("comments");
			String[] interviewDtlCode = request.getParameterValues("interviewDtlCode");
			String intName[] = null;
			String intCode[] = null;
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (candidateCode != null && candidateCode.length > 0) {
				intName = new String[candidateCode.length];
				intCode = new String[candidateCode.length];
				int j = 1;
					for (int i = 0; i < candidateCode.length; i++) {
						InterviewSchedule bean = new InterviewSchedule();
						intName[i] = (String) request
								.getParameter("paraFrm_interviewerName" + j);
						intCode[i] = (String) request
								.getParameter("paraFrm_interviewerCode" + j);
						bean.setCandidateName(candidateName[i]);
						bean.setCandidateCode(candidateCode[i]);
						bean.setIntDate(intDate[i]);
						bean.setIntTime(intTime[i]);
						bean.setIntRoundType(intRoundType[i]);
						bean.setVenue(venue[i]);
						request.setAttribute("paraFrm_interviewerName" + j,
								intName[i]);
						request.setAttribute("paraFrm_interviewerCode" + j,
								intCode[i]);
						bean.setComments(comments[i]);
						bean.setRecruiterId(recruiterCode[i]);
						bean.setInterviewDtlCode(interviewDtlCode[i]);
						setRoundTypeDropDownValue(bean);
						tableList.add(bean);
						j++;

					}
				intSchedule.setCandidateList(tableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * this method is used to save the Interview Schedule
	 * 
	 * @param intSchedule
	 * @param request
	 * @param check
	 * @param candidateName
	 * @param candidateCode
	 * @param recruiterCode
	 * @param intDate
	 * @param intTime
	 * @param venue
	 * @param intRoundType
	 * @param comments
	 * @param checkBox
	 * @param intDetailCode
	 * @return
	 */
	public String saveSchedule(InterviewSchedule intSchedule,
			HttpServletRequest request, String[] check, String[] candidateName,
			String[] candidateCode, String[] recruiterCode, String[] intDate,
			String[] intTime, String[] venue, String[] intRoundType,
			String[] comments, String[] checkBox, String[] intDetailCode) {

		String result = "";
		boolean schIntHdr = false;
		boolean schIntDtl = false;
		boolean updResumeBank = false;
		String intName[] = null;
		String intCode[] = null;
		Object[][] maxCode = null;
		ArrayList<Object> tableList = new ArrayList<Object>();
		String intCodeForEmail = "";

		try {
			/**
			 * HERE IF WE NAVIGATED FROM "SCHEDULE INTERVIEW LIST" THEN THIS IF
			 * CONDITION SATISFIES..
			 */
			if (!(intSchedule.getInterviewCode().equals("")
					|| intSchedule.getInterviewCode().equals("null") || intSchedule
					.getInterviewCode().equals(null))) {
				intCodeForEmail = intSchedule.getInterviewCode();
			}
			/**
			 * IF WE NAVIGATED FROM "CANDIDATED SCREENING STATUS" THEN THIS
			 * CONDITION IS SATISFIED. BECAUSE NEW HEADER WILL BE INSERTED.
			 */
			else {
					String maxQuery = "SELECT NVL(MAX(INT_CODE),0)+1 FROM HRMS_REC_SCHINT";
					maxCode = getSqlModel().getSingleResult(maxQuery);
					intCodeForEmail = String.valueOf(maxCode[0][0]);
			}
			if (maxCode == null) {
				// only for Reschedule
				if (intSchedule.getInterviewReschFlag().equals("true")) {
					String query = "INSERT INTO HRMS_REC_SCHINT (INT_CODE,INT_REQS_CODE,INT_CONTACT_PER,INT_CONVEY,INT_REQM,INT_REC_EMPID,INT_HIRE_EMPID) "
							+ " VALUES((SELECT NVL(MAX(INT_CODE),0)+1 FROM HRMS_REC_SCHINT),"
							+ intSchedule.getRequisitionCode()
							+ ",'"
							+ intSchedule.getContactPerson()
							+ "',"
							+ " '"
							+ intSchedule.getConveyanceDetail()
							+ "','"
							+ intSchedule.getTestRequirements()
							+ "',"
							+ intSchedule.getUserEmpId()
							+ ","
							+ intSchedule.getHiringManagerCode() + ")";
					schIntHdr = getSqlModel().singleExecute(query);
				}
			} else if (maxCode.length == 0) {
				// only for Reschedule
				if (intSchedule.getInterviewReschFlag().equals("true")) {
					String query = "INSERT INTO HRMS_REC_SCHINT (INT_CODE,INT_REQS_CODE,INT_CONTACT_PER,INT_CONVEY,INT_REQM,INT_REC_EMPID,INT_HIRE_EMPID) "
							+ " VALUES((SELECT NVL(MAX(INT_CODE),0)+1 FROM HRMS_REC_SCHINT),"
							+ intSchedule.getRequisitionCode()
							+ ",'"
							+ intSchedule.getContactPerson()
							+ "',"
							+ " '"
							+ intSchedule.getConveyanceDetail()
							+ "','"
							+ intSchedule.getTestRequirements()
							+ "',"
							+ intSchedule.getUserEmpId()
							+ ","
							+ intSchedule.getHiringManagerCode() + ")";
					schIntHdr = getSqlModel().singleExecute(query);
				}

			} else {
					String query = "INSERT INTO HRMS_REC_SCHINT (INT_CODE,INT_REQS_CODE,INT_CONTACT_PER,INT_CONVEY,INT_REQM,INT_REC_EMPID,INT_HIRE_EMPID) "
							+ " VALUES("
							+ String.valueOf(maxCode[0][0])
							+ ","
							+ intSchedule.getRequisitionCode()
							+ ",'"
							+ intSchedule.getContactPerson()
							+ "',"
							+ " '"
							+ intSchedule.getConveyanceDetail()
							+ "','"
							+ intSchedule.getTestRequirements()
							+ "',"
							+ intSchedule.getUserEmpId()
							+ ","
							+ intSchedule.getHiringManagerCode() + ")";
					schIntHdr = getSqlModel().singleExecute(query);
			}
			Object[][] saveData = null;
			if (candidateCode != null && candidateCode.length > 0) {
				saveData = new Object[candidateCode.length][1];
				intName = new String[candidateCode.length];
				intCode = new String[candidateCode.length];
				int j = 1;
				int resetCount = 0;
					for (int i = 0; i < candidateCode.length; i++) {
						InterviewSchedule bean = new InterviewSchedule();
						intName[i] = (String) request.getParameter("paraFrm_interviewerName" + (i + 1));
						intCode[i] = (String) request.getParameter("paraFrm_interviewerCode" + (i + 1));
						if (String.valueOf(checkBox[i]).equals("Y")) {
							resetCount++;
							/**
							 * HERE IN THIS IF CONDITION IS SATISFIED IF WE
							 * NAVIGATED FROM "SCHEDULE INTERVIEW LIST" THEN THE
							 * CANDIDATES WHOSE INTERVIEW DETAIL CODE IS NOT NULL,
							 * THAT MEANS THOSE CANDIDATES DATA IS ALREADY PRESENT
							 * IN THE DETAIL...SO IT IS UPDATED.
							 */
							if (!(intDetailCode[i] == null
									|| intDetailCode[i].equals("") || intDetailCode[i]
									.equals("null"))) {
								// for only reschedule if condition
								if (intSchedule.getInterviewReschFlag().equals(
										"true")) {
									String updateSql = "UPDATE HRMS_REC_SCHINT_DTL SET INT_CONDUCT_STATUS = 'R'"
											+ " WHERE INT_DTL_CODE = "
											+ intDetailCode[i] + "";
									boolean updResult = getSqlModel()
											.singleExecute(updateSql);

									// for update HRMS_REC_INTSTATUS table
									String updateIntStatus = "UPDATE HRMS_REC_INTSTATUS SET STATUS_STAGE = 'X'"
											+ " WHERE STATUS_INTDTLCODE = "
											+ intDetailCode[i];
									getSqlModel()
											.singleExecute(updateIntStatus);

									String insetQuery = "INSERT INTO HRMS_REC_SCHINT_DTL (INT_DTL_CODE,INT_CODE,INT_CAND_CODE,INT_REQS_CODE,INT_DATE,INT_TIME, "
											+ " INT_COMMENTS,INT_VIEWER_EMPID,INT_ROUND_TYPE,INT_VENUE_DET) VALUES ((SELECT NVL(MAX(INT_DTL_CODE),0)+1 FROM HRMS_REC_SCHINT_DTL),"
											+ " (SELECT NVL(MAX(INT_CODE),0) FROM HRMS_REC_SCHINT),"
											+ candidateCode[i]
											+ ","
											+ intSchedule.getRequisitionCode()
											+ ",TO_DATE('"
											+ intDate[i]
											+ "','DD-MM-YYYY'),'"
											+ intTime[i]
											+ "',"
											+ " '"
											+ comments[i]
											+ "',"
											+ intCode[i]
											+ ",'"
											+ intRoundType[i]
											+ "','"
											+ venue[i] + "')";
									schIntDtl = getSqlModel().singleExecute(
											insetQuery);

									String insertIntStatus = "INSERT INTO HRMS_REC_INTSTATUS (STATUS_CODE,STATUS_REQS_CODE,STATUS_CAND_CODE,STATUS_STAGE,STATUS_INTCODE,STATUS_INTDTLCODE) "
											+ " VALUES ((SELECT NVL(MAX(STATUS_CODE),0)+1 FROM HRMS_REC_INTSTATUS),"
											+ " "
											+ intSchedule.getRequisitionCode()
											+ ","
											+ candidateCode[i]
											+ ",'I',(SELECT NVL(MAX(INT_CODE),0) FROM HRMS_REC_SCHINT),(SELECT NVL(MAX(INT_DTL_CODE),0) FROM HRMS_REC_SCHINT_DTL))";
									boolean instIntStatus = getSqlModel()
											.singleExecute(insertIntStatus);

								} else {
									String updateIntDtl = "UPDATE HRMS_REC_SCHINT_DTL SET INT_ROUND_TYPE = '"
											+ intRoundType[i]
											+ "' , INT_DATE = TO_DATE('"
											+ intDate[i]
											+ "','DD-MM-YYYY'),INT_TIME = '"
											+ intTime[i]
											+ "', "
											+ " INT_VENUE_DET = '"
											+ venue[i]
											+ "' , INT_VIEWER_EMPID = "
											+ intCode[i]
											+ " , INT_COMMENTS = '"
											+ comments[i]
											+ "' "
											+ " WHERE INT_DTL_CODE = "
											+ intDetailCode[i] + "";
									boolean updResult = getSqlModel()
											.singleExecute(updateIntDtl);
								}

							}
							/**
							 * ELSE THE DATA IS INSERTED....
							 */
							else {
								/**
								 * HERE IF WE NAVIGATED FROM "SCHEDULE INTERVIEW
								 * LIST" THEN THIS IF CONDITION IS SATISFIED
								 */
								Object[][] maxDtl = null;
								if (!(intSchedule.getInterviewCode().equals("")
										|| intSchedule.getInterviewCode()
												.equals("null") || intSchedule
										.getInterviewCode().equals(null))) {

										String maxDtlCodeQuery = "SELECT NVL(MAX(INT_DTL_CODE),0)+1 FROM HRMS_REC_SCHINT_DTL";
										maxDtl = getSqlModel().getSingleResult(
												maxDtlCodeQuery);
									
									String query = "INSERT INTO HRMS_REC_SCHINT_DTL (INT_DTL_CODE,INT_CODE,INT_CAND_CODE,INT_REQS_CODE,INT_DATE,INT_TIME, "
											+ " INT_COMMENTS,INT_VIEWER_EMPID,INT_ROUND_TYPE,INT_VENUE_DET) VALUES ("
											+ String.valueOf(maxDtl[0][0])
											+ ","
											+ " (SELECT NVL(MAX(INT_CODE),0) FROM HRMS_REC_SCHINT),"
											+ candidateCode[i]
											+ ","
											+ intSchedule.getRequisitionCode()
											+ ",TO_DATE('"
											+ intDate[i]
											+ "','DD-MM-YYYY'),'"
											+ intTime[i]
											+ "',"
											+ " '"
											+ comments[i]
											+ "',"
											+ intCode[i]
											+ ",'"
											+ intRoundType[i]
											+ "','"
											+ venue[i] + "')";
									schIntDtl = getSqlModel().singleExecute(
											query);
									// for only reschedule if condition
									if (intSchedule.getInterviewReschFlag()
											.equals("true")) {
										//Use String.valueOf(maxDtl[0][0]) instead of intDetailCode[i]
										String updateIntStatus = "UPDATE HRMS_REC_INTSTATUS SET STATUS_STAGE = 'X'"
												+ " WHERE STATUS_INTDTLCODE = "+String.valueOf(maxDtl[0][0]);
												//+ intDetailCode[i];
										getSqlModel().singleExecute(
												updateIntStatus);
									}

									String insertIntStatus = "INSERT INTO HRMS_REC_INTSTATUS (STATUS_CODE,STATUS_REQS_CODE,STATUS_CAND_CODE,STATUS_STAGE,STATUS_INTCODE,STATUS_INTDTLCODE) "
											+ " VALUES ((SELECT NVL(MAX(STATUS_CODE),0)+1 FROM HRMS_REC_INTSTATUS),"
											+ " "
											+ intSchedule.getRequisitionCode()
											+ ","
											+ candidateCode[i]
											+ ",'I',"
											+ intSchedule.getInterviewCode()
											+ "," + maxDtl[0][0] + ")";
									boolean instIntStatus = getSqlModel()
											.singleExecute(insertIntStatus);
								}
								/**
								 * HERE IF WE COME DIRECTLY OR NAVIGATED FROM
								 * "CANDIDATE SCREENING STATUS" THEN THIS ELSE IS
								 * SATISFIED..
								 */
								else {
										String maxDtlCodeQuery = "SELECT NVL(MAX(INT_DTL_CODE),0)+1 FROM HRMS_REC_SCHINT_DTL";
										maxDtl = getSqlModel().getSingleResult(
												maxDtlCodeQuery);
										
									String query = "INSERT INTO HRMS_REC_SCHINT_DTL (INT_DTL_CODE,INT_CODE,INT_CAND_CODE,INT_REQS_CODE,INT_DATE,INT_TIME, "
											+ " INT_COMMENTS,INT_VIEWER_EMPID,INT_ROUND_TYPE,INT_VENUE_DET) VALUES ("
											+ maxDtl[0][0]
											+ ","
											+ " ((SELECT NVL(MAX(INT_CODE),0) FROM HRMS_REC_SCHINT)),"
											+ candidateCode[i]
											+ ","
											+ intSchedule.getRequisitionCode()
											+ ",TO_DATE('"
											+ intDate[i]
											+ "','DD-MM-YYYY'),'"
											+ intTime[i]
											+ "',"
											+ " '"
											+ comments[i]
											+ "',"
											+ intCode[i]
											+ ",'"
											+ intRoundType[i]
											+ "','"
											+ venue[i] + "')";
									schIntDtl = getSqlModel().singleExecute(
											query);

									// for only reschedule if condition
									if (intSchedule.getInterviewReschFlag()
											.equals("true")) {
										// for update HRMS_REC_INTSTATUS table AS
										// STATUS_STAGE X
										String updateIntStatus = "UPDATE HRMS_REC_INTSTATUS SET STATUS_STAGE = 'X'"
												+ " WHERE STATUS_INTDTLCODE = "
												+ intDetailCode[i];
										getSqlModel().singleExecute(
												updateIntStatus);
									}

									String insertIntStatus = "INSERT INTO HRMS_REC_INTSTATUS (STATUS_CODE,STATUS_REQS_CODE,STATUS_CAND_CODE,STATUS_STAGE,STATUS_INTCODE,STATUS_INTDTLCODE) "
											+ " VALUES ((SELECT NVL(MAX(STATUS_CODE),0)+1 FROM HRMS_REC_INTSTATUS),"
											+ " "
											+ intSchedule.getRequisitionCode()
											+ ","
											+ candidateCode[i]
											+ ",'I',"
											+ maxCode[0][0]
											+ ","
											+ maxDtl[0][0] + ")";
									boolean instIntStatus = getSqlModel()
											.singleExecute(insertIntStatus);
								}

							}

							/**
							 * HERE IN HRMS_REC_RESUME_BANK THE STATUS IS UPDATED TO
							 * "I"..
							 */
							if (schIntDtl) {
									String updResumeQuery = "UPDATE HRMS_REC_RESUME_BANK SET RESUME_STATUS = 'I' "
											+ " WHERE RESUME_REQS_CODE="
											+ intSchedule.getRequisitionCode()
											+ " AND RESUME_CAND_CODE="
											+ candidateCode[i] + "";
									boolean updResumeBankStauts = getSqlModel()
											.singleExecute(updResumeQuery);
								

									String updQuery = "UPDATE HRMS_REC_RESUME_BANK SET RESUME_SHEDULE_STATUS = 'I' "
											+ " WHERE RESUME_REQS_CODE="
											+ intSchedule.getRequisitionCode()
											+ " AND RESUME_CAND_CODE="
											+ candidateCode[i]
											+ " AND RESUME_STATUS IN ('I', 'B') ";

									updResumeBank = getSqlModel()
											.singleExecute(updQuery);
								
								intSchedule.setInterviewReschFlag("false");
							}

						}

						/**
						 * HERE THE RECORDS WHICH WERE NOT CHECKED FOR THE SCHEDULE
						 * INTERVIEW THOSE RECORDS ARE SET IN THE LIST AGAIN...
						 */
						if (!(String.valueOf(checkBox[i]).equals("Y"))) {// records which are not checked
							bean.setCandidateName(candidateName[i]);
							bean.setCandidateCode(candidateCode[i]);
							bean.setIntRoundType(intRoundType[i]);
							bean.setIntDate(intDate[i]);
							bean.setIntTime(intTime[i]);
							bean.setComments(comments[i]);
							request.setAttribute("paraFrm_interviewerName" + j,
									intName[i]);
							request.setAttribute("paraFrm_interviewerCode" + j,
									intCode[i]);
							bean.setRecruiterId(recruiterCode[i]);
							setRoundTypeDropDownValue(bean);
							tableList.add(bean);
							j++;
						}
					}
				intSchedule.setCandidateList(tableList);

				/**
				 * if the list is empty then the header values will be reset....
				 */
				if (resetCount == candidateCode.length) {
					reset(intSchedule);
				}
			}
			result = "true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public void reset(InterviewSchedule intSchedule) {
		intSchedule.setRequisitionCode("");
		intSchedule.setRequisitionName("");
		intSchedule.setPosition("");
		intSchedule.setConveyanceDetail("");
		intSchedule.setCntPersonId("");
		intSchedule.setContactPerson("");
		intSchedule.setTestRequirements("");
		intSchedule.setHiringManager("");
		intSchedule.setHiringManagerCode("");
	}

	public void setListAfterPostResume(InterviewSchedule intSchedule,
			HttpServletRequest request, Object[] formValues, String reqCode,
			String candCode, Object[][] formListValues,
			Object[] onlineformValues, Object[][] onlineformListValues) {
		if (formValues != null && formValues.length > 0) {
			intSchedule.setRequisitionCode(reqCode);
			// intSchedule.setCntPersonId(String.valueOf(formValues [1]));
			intSchedule.setContactPerson(String.valueOf(formValues[2]));
			intSchedule.setHiringManagerCode(String.valueOf(formValues[3]));
			intSchedule.setHiringManager(String.valueOf(formValues[4]));
			intSchedule.setTestReqCode(String.valueOf(formValues[5]));
			intSchedule.setTestRequirements(String.valueOf(formValues[6]));
			intSchedule.setConveyanceDetail(String.valueOf(formValues[7]));
			intSchedule.setInterviewCode(String.valueOf(formValues[8]));// HEADER INTERVIEW CODE IS SET
			intSchedule.setF9Flag(String.valueOf(formValues[10]));
		}

		Object[][] candidateData = null;
		ArrayList<Object> candidateList = new ArrayList<Object>();
		try {
			/**
			 * HERE IN THIS QUERY THE CANIDATE CODES WHICH ARE GOT FROM THE POST
			 * RESUME ARE PASSED
			 */
			String query = " SELECT CAND_FIRST_NAME||' '||CAND_LAST_NAME,CAND_CODE FROM HRMS_REC_CAND_DATABANK "
					+ " WHERE CAND_CODE IN("
					+ candCode
					+ ") ORDER BY UPPER(CAND_FIRST_NAME||''||CAND_LAST_NAME)";

			candidateData = getSqlModel().getSingleResult(query);
			if (candidateData != null && candidateData.length > 0) {
				int j = 1;
				/**
				 * CANCIDATE LENGTH FOR LOOP IS RUN
				 */
				for (int i = 0; i < candidateData.length; i++) {
					boolean flag = false;
					InterviewSchedule bean = new InterviewSchedule();

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
							if (formListValues[k][8] != null) {
								if (formListValues[k][8].equals(String
										.valueOf(candidateData[i][1]))) {
									bean.setCandidateName(String
											.valueOf(candidateData[i][0]));
									bean.setCandidateCode(String
											.valueOf(candidateData[i][1]));
									bean.setRecruiterId(intSchedule
											.getUserEmpId());

									/**
									 * FOR CANDIDATES ADDED IN THE POST REUME
									 * THEIR InterviewDtlCode WILL BE BLANK
									 * WHERE THE CANDIDATES SENT FROM THE
									 * SCHEDULE INTERVIEW LIST THEIR
									 * InterviewDtlCode WILL NOT BE BLANK
									 */
									if (formListValues[k][0].equals("")
											|| formListValues[k][0]
													.equals("null")
											|| formListValues[k][0]
													.equals(null)) {
										bean.setInterviewDtlCode("");
									} else {
										bean.setInterviewDtlCode(String
												.valueOf(formListValues[k][0]));
									}
									bean.setIntRoundType(String
											.valueOf(formListValues[k][1]));
									bean.setIntDate(String
											.valueOf(formListValues[k][2]));
									bean.setIntTime(String
											.valueOf(formListValues[k][3]));
									bean.setVenue(String
											.valueOf(formListValues[k][4]));
									request.setAttribute(
											"paraFrm_interviewerName" + j, ""
													+ formListValues[k][6]);
									request.setAttribute(
											"paraFrm_interviewerCode" + j, ""
													+ formListValues[k][5]);
									bean.setComments(String
											.valueOf(formListValues[k][7]));
									flag = true;
									break;
								}
							}
						}
					}
					/**
					 * HERE IF THE formListValues[k][8] DOES NOT MATCH THE
					 * candidateData[i][1], THAT MEAN THE NEW CANDIDATE WAS
					 * ADDED IN THE POST RESUME...SO IS ADDED IN THE LIST HERE
					 * IN THIS IF CONDITION
					 */
					if (!flag) {
						bean.setCandidateName(String
								.valueOf(candidateData[i][0]));
						bean.setCandidateCode(String
								.valueOf(candidateData[i][1]));
						bean.setRecruiterId(intSchedule.getUserEmpId());
						request.setAttribute("paraFrm_interviewerName" + j,
								formValues[4]);
						request.setAttribute("paraFrm_interviewerCode" + j,
								formValues[3]);
					}
					setRoundTypeDropDownValue(bean);
					candidateList.add(bean);
					j++;
				}
				intSchedule.setCandidateList(candidateList);// LIST IS ADDED.
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}  else {
			return result;
		} 
	}// end of checkNull

	/**
	 * THIS METHOD IS CALLED TO SET THE RECORD LIST FOR UPDATING.....FOR
	 * RESCHEDULING THE RECORDS.
	 * 
	 * @param intSchedule
	 * @param request
	 * @param requisitionCode
	 * @param intervCode
	 * @param intervDtlCode
	 */
	public void getRecordForReschedule(InterviewSchedule intSchedule,
			HttpServletRequest request, String requisitionCode,
			String intervCode, String intervDtlCode) {

		try {
			Object[][] reqHdrData = null;
			Object[][] intHdrData = null;
			Object[][] intDtlData = null;
				String reqHdrQuery = "SELECT REQS_NAME,RANK_NAME,EMP_FNAME||' '||EMP_LNAME,REQS_HIRING_MANAGER "
						+ " FROM HRMS_REC_REQS_HDR "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_REC_REQS_HDR.REQS_POSITION) "
						+ " WHERE REQS_CODE = " + requisitionCode + "";
				reqHdrData = getSqlModel().getSingleResult(reqHdrQuery);
			if (reqHdrData != null && reqHdrData.length > 0) {
				intSchedule.setRequisitionName(String.valueOf(reqHdrData[0][0]));
				intSchedule.setRequisitionCode(requisitionCode);
				intSchedule.setPosition(String.valueOf(reqHdrData[0][1]));
				intSchedule.setHiringManager(String.valueOf(reqHdrData[0][2]));
				intSchedule.setHiringManagerCode(String
						.valueOf(reqHdrData[0][3]));
			}
				String intHdrQuery = "SELECT '',INT_CONVEY,INT_REQM,INT_CONTACT_PER  "
						+ " FROM HRMS_REC_SCHINT  WHERE INT_CODE = " + intervCode + "";
				intHdrData = getSqlModel().getSingleResult(intHdrQuery);
			if (intHdrData != null && intHdrData.length > 0) {
				intSchedule.setConveyanceDetail(checkNull(String
						.valueOf(intHdrData[0][1])));
				intSchedule.setTestRequirements(checkNull(String
						.valueOf(intHdrData[0][2])));
				intSchedule.setContactPerson(String.valueOf(intHdrData[0][3]));
				intSchedule.setInterviewCode(intervCode);
			}
				String intDtlQuery = "SELECT INT_CAND_CODE,CAND_FIRST_NAME||' '||CAND_LAST_NAME,INT_ROUND_TYPE,TO_CHAR(INT_DATE,'DD-MM-YYYY'),INT_TIME,INT_VENUE_DET, "
						+ " INT_VIEWER_EMPID,A1.EMP_FNAME||' '||A1.EMP_LNAME,INT_COMMENTS "
						+ " FROM HRMS_REC_SCHINT_DTL "
						+ "  LEFT JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE)   "
						+ " LEFT JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID) "
						+ " WHERE INT_DTL_CODE = " + intervDtlCode + "";
				intDtlData = getSqlModel().getSingleResult(intDtlQuery);
			if (intDtlData != null && intDtlData.length > 0) {
				ArrayList<Object> candidateList = new ArrayList<Object>();
				InterviewSchedule bean = new InterviewSchedule();
				bean.setCandidateCode(String.valueOf(intDtlData[0][0]));
				bean.setCandidateName(String.valueOf(intDtlData[0][1]));
				if (String.valueOf(intDtlData[0][2]).equals("")
						|| String.valueOf(intDtlData[0][2]).equals("null")
						|| String.valueOf(intDtlData[0][2]).equals(null)) {
					bean.setIntRoundType("");
				} else {
					bean.setIntRoundType(String.valueOf(intDtlData[0][2]));
				}

				if (String.valueOf(intDtlData[0][3]).equals("")
						|| String.valueOf(intDtlData[0][3]).equals("null")
						|| String.valueOf(intDtlData[0][3]).equals(null)) {
					bean.setIntDate("");
				} else {
					bean.setIntDate(String.valueOf(intDtlData[0][3]));
				}

				if (String.valueOf(intDtlData[0][4]).equals("")
						|| String.valueOf(intDtlData[0][4]).equals("null")
						|| String.valueOf(intDtlData[0][4]).equals(null)) {
					bean.setIntTime("");
				} else {
					bean.setIntTime(String.valueOf(intDtlData[0][4]));
				}

				if (String.valueOf(intDtlData[0][5]).equals("")
						|| String.valueOf(intDtlData[0][5]).equals("null")
						|| String.valueOf(intDtlData[0][5]).equals(null)) {
					bean.setVenue("");
				} else {
					bean.setVenue(String.valueOf(intDtlData[0][5]));
				}

				/**
				 * 1 is hardcoded bcoz only 1 data can be resheduled at a time.
				 */
				request.setAttribute("paraFrm_interviewerCode" + 1, ""
						+ intDtlData[0][6]);
				request.setAttribute("paraFrm_interviewerName" + 1, ""
						+ intDtlData[0][7]);

				if (String.valueOf(intDtlData[0][8]).equals("")
						|| String.valueOf(intDtlData[0][8]).equals("null")
						|| String.valueOf(intDtlData[0][8]).equals(null)) {
					bean.setComments("");
				} else {
					bean.setComments(String.valueOf(intDtlData[0][8]));
				}

				bean.setInterviewDtlCode(intervDtlCode);
				setRoundTypeDropDownValue(bean);
				candidateList.add(bean);
				intSchedule.setCandidateList(candidateList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRecForReq(InterviewSchedule intSchedule,
			HttpServletRequest request) {

		Object[][] data = null;
		try {
			String query = "SELECT CAND_CODE,CAND_FIRST_NAME||' '||CAND_LAST_NAME "
					+ " FROM HRMS_REC_RESUME_BANK "
					+ " INNER JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) "
					+ " WHERE RESUME_STATUS='I' AND RESUME_SHEDULE_STATUS='N' AND RESUME_REQS_CODE ="
					+ intSchedule.getRequisitionCode()
					+ ""
					+ " AND RESUME_REC_EMPID ="
					+ intSchedule.getUserEmpId()
					+ "";
			data = getSqlModel().getSingleResult(query);
		ArrayList<Object> candidateList = new ArrayList<Object>();
		int j = 1;
			for (int i = 0; i < data.length; i++) {
				InterviewSchedule bean = new InterviewSchedule();
				bean.setCandidateName(String.valueOf(data[i][1]));
				bean.setCandidateCode(String.valueOf(data[i][0]));
				request.setAttribute("paraFrm_interviewerName" + j, intSchedule.getHiringManager());
				request.setAttribute("paraFrm_interviewerCode" + j, intSchedule.getHiringManagerCode());
				bean.setRecruiterId(intSchedule.getUserEmpId());
				setRoundTypeDropDownValue(bean);
				candidateList.add(bean);
				j++;
			}
			intSchedule.setCandidateList(candidateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generatePdfreport(InterviewSchedule bean,
			HttpServletRequest request, HttpServletResponse response,
			String[] labelHeaders, String[] labelCandHeaders) {

		try {
			String s = "\nSchedule Interview Report\n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Pdf", s);
			rg.setFName(s);
			Object data[][] = new Object[4][4];
			data[0][0] = "" + labelHeaders[0];
			data[0][1] = ": " + bean.getRequisitionName();
			data[0][2] = "" + labelHeaders[1];
			data[0][3] = ": " + bean.getPosition();
			data[1][0] = "" + labelHeaders[2];
			data[1][1] = ": " + bean.getHiringManager();
			data[1][2] = "";
			if (!bean.getCntPersonId().equals(""))
				data[1][2] = ":" + labelHeaders[3];
			data[1][3] = "";
			if (!bean.getCntPersonId().equals(""))
				data[1][3] = ": " + bean.getContactPerson();
			data[2][0] = "";
			if (!bean.getConveyanceDetail().equals("")) {
				data[2][0] = "" + labelHeaders[4];
				data[2][1] = ": " + bean.getConveyanceDetail();

			}
			data[3][0] = "";
			if (!bean.getConveyanceDetail().equals("")) {
				data[3][0] = "" + labelHeaders[5];
				data[3][1] = ": " + bean.getTestRequirements();
			}
			int[] bcellWidth = { 20, 30, 20, 30 };
			int[] bcellAlign = { 0, 0, 0, 0 };
			rg.addFormatedText(s, 6, 0, 1, 0);
			rg.addFormatedText("", 1, 0, 2, 3);
				String dateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
				Object[][] date = getSqlModel().getSingleResult(dateQuery);

				rg.addText("Date: " + String.valueOf(date[0][0]), 0, 2, 0);
				rg.addFormatedText("", 1, 0, 2, 3);
				rg.addFormatedText("", 1, 0, 2, 3);
				rg.addFormatedText("", 1, 0, 2, 3);
				rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				/*
				 * String appCol[] = { "Sr no", "Approver Id", "Approver Name",
				 * "Date", "Status", "Signature" };
				 */
				int appCell[] = { 8, 15, 15, 15, 15, 10, 20, 15 };
				int apprAlign[] = { 1, 0, 0, 0, 0, 0, 0, 0 };
				rg.addFormatedText("", 1, 0, 2, 3);
				rg.addFormatedText("", 1, 0, 2, 3);
				rg.addFormatedText("Candidate List Details :", 6, 0, 0, 0);
				rg.addFormatedText("", 1, 0, 2, 3);
				rg.addFormatedText("", 1, 0, 2, 3);
				Object[][] approvalTable;
				String intDtlQuery = "select rownum,CAND_FIRST_NAME||' '||CAND_LAST_NAME,INT_ROUND_TYPE,TO_CHAR(INT_DATE,'DD-MM-YYYY'),INT_TIME,INT_VENUE_DET,"
						+ " A1.EMP_FNAME||' '||A1.EMP_LNAME, INT_COMMENTS "
						+
						// ", decode(INT_CONDUCT_STATUS,'Y','Conducted','N','not
						// Conducted')"
						" from HRMS_REC_SCHINT_DTL inner JOIN HRMS_REC_SCHINT  ON (HRMS_REC_SCHINT_DTL.INT_CODE = HRMS_REC_SCHINT.INT_CODE) "
						+ " LEFT JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE)"
						+ " LEFT JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID) "
						+ " WHERE  HRMS_REC_SCHINT.INT_REQS_CODE ="
						+ bean.getRequisitionCode();
				if (!bean.getCntPersonId().equals("")) {
					intDtlQuery += " and HRMS_REC_SCHINT.INT_CONTACT_PER="
							+ bean.getCntPersonId();
				}
				intDtlQuery += " order by INT_DATE asc";
				approvalTable = getSqlModel().getSingleResult(intDtlQuery);
				if (approvalTable != null && approvalTable.length > 0) {

					for (int i = 0; i < approvalTable.length; i++) {
						approvalTable[i][0] = String.valueOf(i + 1);
					}

					rg.tableBody(labelCandHeaders, approvalTable, appCell,
							apprAlign);
				} else {
					rg.addFormatedText("No candidate records available.", 1, 0,
							1, 3);
				}
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateWordreport(InterviewSchedule intSchedule,
			HttpServletRequest request, HttpServletResponse response,
			String[] labelHeaders, String[] labelCandHeaders) {
	}

	public void viewTestDetails(InterviewSchedule bean) {
		try {
			String sql = "SELECT REQS_NAME AS REQCODE,RANK_NAME  AS  POSITION , TEST_TOTAL_MARKS, TEST_OBT_MARKS,DECODE(TEST_RESULT,'P','Pass','F','Fail'), "
					+ " DECODE(HRMS_REC_TESTRESULT.TEST_TYPE, 'S','Subjective', 'O','Objective', 'B','Both') ,TO_CHAR(TEST_DATE,'DD-MM-YYYY'), TEST_TIME, "
					+ " NVL(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,''), "
					+ " NVL(HRMS_REC_TESTRESULT.TEST_COMMENTS,' '),NVL(TEST_ROUND_TYPE,' ')   FROM HRMS_REC_TESTRESULT "
					+ " LEFT JOIN HRMS_REC_REQS_HDR  ON(TEST_REQS_CODE =HRMS_REC_REQS_HDR.REQS_CODE ) "
					+ " LEFT JOIN HRMS_RANK  ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ " LEFT JOIN  HRMS_REC_SCHTEST_DTL ON (HRMS_REC_TESTRESULT.TEST_DTL_CODE = HRMS_REC_SCHTEST_DTL.TEST_DTL_CODE )  "
					+ " LEFT JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE =TEST_CAND_CODE  ) "
					+ " WHERE  TEST_STATUS IN('Y') AND TEST_CAND_CODE = " + bean.getCandCode()
					+ " AND TEST_REQS_CODE =" + bean.getRequisitionCode()					 
					+ " AND HRMS_REC_TESTRESULT.TEST_DTL_CODE = " + bean.getTestDetailCode();
					
			Object[][] data = getSqlModel().getSingleResult(sql);
			if (data != null && data.length > 0) {
				bean.setViewTestFlag("true");
				bean.setViewReq(String.valueOf(data[0][0]));
				bean.setViewPosition(String.valueOf(data[0][1]));
				bean.setViewCandidateName(String.valueOf(data[0][8]));
				ArrayList testList = new ArrayList();
				for (int i = 0; i < data.length; i++) {
					InterviewSchedule bean1 = new InterviewSchedule();
					bean1.setViewTotalMarks(checkNull(String.valueOf(data[i][2])));
					bean1.setViewOptMarks(checkNull(String.valueOf(data[i][3])));
					bean1.setViewResult(checkNull(String.valueOf(data[i][4])));
					bean1.setViewTestType(checkNull(String.valueOf(data[i][5])));
					bean1.setViewTestDate(checkNull(String.valueOf(data[i][6])));
					bean1.setViewTestTime(checkNull(String.valueOf(data[i][7])));
					bean1.setViewTestComments(checkNull(String.valueOf(data[i][9]))); 
					bean1.setViewTestRoundType(checkNull(String.valueOf(data[i][10])));
					testList.add(bean1);
				}
				bean.setViewTestList(testList);
			} else {
				bean.setViewTestFlag("false");

				Object[][] reqData = null;
					String reqQuery = "SELECT REQS_NAME,RANK_NAME  AS  POSITION FROM HRMS_REC_REQS_HDR "
							+ " LEFT JOIN HRMS_RANK  ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION) "
							+ " WHERE REQS_CODE = " + bean.getRequisitionCode();
					reqData = getSqlModel().getSingleResult(reqQuery);
				if (reqData != null && reqData.length > 0) {
					bean.setViewReq(String.valueOf(reqData[0][0]));
					bean.setViewPosition(String.valueOf(reqData[0][1]));
				}  

				Object[][] candName = null;
					String candQuery = "SELECT CAND_FIRST_NAME||' '||CAND_LAST_NAME FROM HRMS_REC_CAND_DATABANK "
							+ " WHERE CAND_CODE = " + bean.getCandCode();
					candName = getSqlModel().getSingleResult(candQuery);
				if (candName != null && candName.length > 0) {
					bean.setViewCandidateName(String.valueOf(candName[0][0]));
				}  
			}
			String maxSequence = "";
			Object[][] maxSeqData = null;
				String maxSeqQuery = "SELECT max(HRMS_REC_ONLINETEST.ONLINE_SEQS) FROM HRMS_REC_ONLINETEST  "
						+ " WHERE HRMS_REC_ONLINETEST.ONLINE_REQS_CODE = " + bean.getRequisitionCode()
						+ " AND HRMS_REC_ONLINETEST.ONLINE_CAND_CODE = " + bean.getCandCode() 
						+ " AND HRMS_REC_ONLINETEST.ONLINE_TEMPLATE_ID = " + bean.getOnlineTestTemplateCode();
				maxSeqData = getSqlModel().getSingleResult(maxSeqQuery);
			if (maxSeqData != null && maxSeqData.length > 0) {
				maxSequence = String.valueOf(maxSeqData[0][0]);
			}
			Object[][] questionData = null;
				String questionQuery = "SELECT ONLINE_QUES_CODE, REPLACE(QUES_NAME,'\\n','<br>'), QUES_TYPE, ONLINE_SEQS, ONLINE_SUBJ_ANSWER, ONLINE_ANSWER, " 
						+" NVL(ANSWER_UPLOADED_DOC,''), NVL(QUES_UPLOADED_DOC,''), HRMS_REC_SUBJECT.SUBJECT_NAME , HRMS_REC_CATEGORY.CAT_NAME " 
						+" FROM HRMS_REC_ONLINETEST "
						+ " INNER JOIN  HRMS_REC_QUESBANK ON(HRMS_REC_QUESBANK.QUES_CODE = HRMS_REC_ONLINETEST.ONLINE_QUES_CODE) "
						+ " INNER JOIN HRMS_REC_SUBJECT ON (HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_QUESBANK.QUES_SUB_CODE)  "
						+ " INNER JOIN HRMS_REC_CATEGORY ON (HRMS_REC_CATEGORY.CAT_CODE = HRMS_REC_QUESBANK.QUES_CAT_CODE) "
						+ " WHERE HRMS_REC_ONLINETEST.ONLINE_REQS_CODE = " + bean.getRequisitionCode()
						+ " AND HRMS_REC_ONLINETEST.ONLINE_CAND_CODE = " + bean.getCandCode()
						+ " AND HRMS_REC_ONLINETEST.ONLINE_SEQS = " + bean.getQuestionSequence()
						+ " AND HRMS_REC_ONLINETEST.ONLINE_TEMPLATE_ID = " + bean.getOnlineTestTemplateCode();
				questionData = getSqlModel().getSingleResult(questionQuery);
			if (questionData != null && questionData.length > 0) {
				bean.setQuestionName(String.valueOf(questionData[0][1]));
				bean.setSubject(checkNull(String.valueOf(questionData[0][8])));
				bean.setCategory(checkNull(String.valueOf(questionData[0][9])));
				bean.setQuestionSequence(String.valueOf(questionData[0][3]));
				if (String.valueOf(questionData[0][3]).equals("1")) {
					bean.setPreviousButtonFlag("false");
				} else {
					bean.setPreviousButtonFlag("true");
				}
				if (maxSequence.equals(String.valueOf(questionData[0][3]))) {
					bean.setNextButtonFlag("false");
				} else {
					bean.setNextButtonFlag("true");
				}

				if (String.valueOf(questionData[0][2]).equals("S")) {
					bean.setSubjectiveFlag("true");
					bean.setSubjAnswer(checkNull(String.valueOf(questionData[0][4])));
					if (!checkNull(String.valueOf(questionData[0][6])).equals("")) {
						bean.setAnswerAttachedFlag(true);
						bean.setAnswerUploadedDoc(checkNull(String.valueOf(questionData[0][6])));
					}	
					if (!checkNull(String.valueOf(questionData[0][7])).equals("")) {
						bean.setDocumentAttachedFlag(true);
						bean.setQuestionUploadedDoc(checkNull(String.valueOf(questionData[0][7])));
					}
				} else {
					bean.setSubjectiveFlag("false");
					Object[][] optionData = null;
						String optionQuery = "SELECT QUES_CODE,OPTION_CODE,OPTION_DESC,OPTION_ANS_FLAG FROM HRMS_REC_QUESOPTION "
								+ " WHERE QUES_CODE IN("
								+ questionData[0][0]
								+ ")";
						optionData = getSqlModel().getSingleResult(optionQuery);
					if (optionData != null && optionData.length > 0) {
						ArrayList<Object> optionList = new ArrayList<Object>();
							for (int i = 0; i < optionData.length; i++) {
								InterviewSchedule listBean = new InterviewSchedule();
								listBean.setOptionName(String.valueOf(optionData[i][2]));
								 
								String[] optionGiven = String.valueOf(questionData[0][5]).split(",");
								for (int j = 0; j < optionGiven.length; j++) {
									if (String.valueOf(optionGiven[j]).equals(String.valueOf(optionData[i][1]))) {
										listBean.setOptionCheckBox("true");
									} 
								}
								
								optionList.add(listBean);
							}  
							bean.setOnLineTestList(optionList);
					}  
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
