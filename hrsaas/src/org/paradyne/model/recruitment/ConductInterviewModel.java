package org.paradyne.model.recruitment;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.ConductInterview;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;

/**
 * @author Pankaj_Jain
 * 
 */
public class ConductInterviewModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ConductInterviewModel.class);

	public void setCandidateData(HttpServletRequest request,
			ConductInterview bean) {
		try {
			int rowId = Integer.parseInt(request.getParameter("selCand"));
			String candName[] = request.getParameterValues("candName");
			String candCode[] = request.getParameterValues("candCode");
			String reqName[] = request.getParameterValues("reqName");
			String reqCode[] = request.getParameterValues("reqCode");
			String position[] = request.getParameterValues("position");
			String positionId[] = request.getParameterValues("positionId");
			String intCode[] = request.getParameterValues("intCode");
			String intDtlCode[] = request.getParameterValues("intDtlCode");
			String intDate[] = request.getParameterValues("intDate");
			String intTime[] = request.getParameterValues("intTime");
			String intRound[] = request.getParameterValues("intRoundType");
			String department[] = request.getParameterValues("department");
			String deptId[] = request.getParameterValues("deptId");
			String division[] = request.getParameterValues("division");
			String divisionId[] = request.getParameterValues("divisionId");
			String branch[] = request.getParameterValues("branch");
			String branchId[] = request.getParameterValues("branchId");
			String interviewRoundTypeConductInterviewID = request
					.getParameter("interviewRoundTypeConductInterviewID");
			bean.setRequisitionName(reqName[rowId]);
			bean.setRequisitionCode(reqCode[rowId]);
			bean.setCandName(candName[rowId]);
			bean.setCandCode(candCode[rowId]);
			bean.setPosition(position[rowId]);
			bean.setPositionCode(positionId[rowId]);
			bean.setDepartment(department[rowId]);
			bean.setDeptCode(deptId[rowId]);
			bean.setDivision(division[rowId]);
			bean.setDivisionCode(divisionId[rowId]);
			bean.setBranch(branch[rowId]);
			bean.setBranchCode(branchId[rowId]);
			bean.setIntDate(intDate[rowId]);
			bean.setIntTime(intTime[rowId]);
			bean.setIntRound(interviewRoundTypeConductInterviewID);
			bean.setComments("");
			bean.setIntDtlCode(intDtlCode[rowId]);
			bean.setIntCode(intCode[rowId]);
			bean.setCheckFlag("true");
			bean.setBckToIntrFlag("true");

			/**
			 * edited by varun on 09/10/2009...
			 */
			Object[][] recruiterData = null;
			String recQuery = "  SELECT RESUME_REC_EMPID,EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME "
					+ " FROM HRMS_REC_RESUME_BANK "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_RESUME_BANK.RESUME_REC_EMPID) "
					+ " WHERE RESUME_REQS_CODE = "
					+ reqCode[rowId]
					+ " AND RESUME_CAND_CODE = " + candCode[rowId] + "";
			recruiterData = getSqlModel().getSingleResult(recQuery);

			if (recruiterData != null && recruiterData.length > 0) {
				bean.setRecruiterId(String.valueOf(recruiterData[0][0]));
				bean.setRecruiterToken(String.valueOf(recruiterData[0][1]));
				bean.setRecruiterName(String.valueOf(recruiterData[0][2]));
			}

			Object[][] jdData = null;
			String query = "SELECT REQS_JOBDESC_NAME, REQS_ROLE_RESPON, REQS_HIRING_MANAGER "
					+ " FROM HRMS_REC_REQS_HDR "
					+ " WHERE REQS_CODE = "
					+ bean.getRequisitionCode() + "";
			jdData = getSqlModel().getSingleResult(query);

			if (jdData != null && jdData.length != 0) {
				bean.setJobDesc(String.valueOf(jdData[0][0]));
				bean.setRolesResponsibility(String.valueOf(jdData[0][1]));
				bean.setHiringManagerCode(String.valueOf(jdData[0][2]));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String submit(ConductInterview bean, String[] parameterCode,
			String[] parameterRating, String[] parameterComment) {
		String result = "";
		try {
				//String vacancyStatsDataQuery = "SELECT VAC_OFFER_CODE, VAC_OFFER_GIVEN FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE = "+bean.getRequisitionCode() +" AND VAC_STATUS = 'O' AND VAC_OFFER_GIVEN='N' AND VAC_OFFER_CODE IS NULL";
				//Object[][] vacancyStatsDataObj = getSqlModel().getSingleResult(vacancyStatsDataQuery);
			//	if(vacancyStatsDataObj != null && vacancyStatsDataObj.length>0) {
					Object saveCandEval[][] = new Object[1][21];
					saveCandEval[0][0] = bean.getRequisitionCode();
					saveCandEval[0][1] = bean.getCandCode();
					saveCandEval[0][2] = bean.getIntDate();
					saveCandEval[0][3] = bean.getIntTime();
					saveCandEval[0][4] = bean.getCurrentCTC();
					saveCandEval[0][5] = bean.getNegoCTC();
					saveCandEval[0][6] = bean.getExptdJoinDate();
					saveCandEval[0][7] = bean.getConstraints();
					saveCandEval[0][8] = bean.getReadyReloc();
					saveCandEval[0][9] = bean.getEmpTypeCode();
					saveCandEval[0][10] = bean.getIntrStatus();
					saveCandEval[0][11] = bean.getComments();
					if (bean.getMakeOffer().equals("true")) {
						saveCandEval[0][12] = "Y";
						saveCandEval[0][13] = "N";
					} else {
						saveCandEval[0][12] = "N";
						saveCandEval[0][13] = "Y";
					}
					
					if (bean.getIntrStatus().equals("R") || bean.getIntrStatus().equals("O")) {
						saveCandEval[0][12] = "N";
						saveCandEval[0][13] = "N";
					}
					saveCandEval[0][14] = bean.getSelectInterId();
					saveCandEval[0][15] = bean.getIntRound();
					saveCandEval[0][16] = bean.getEvalScore();
					saveCandEval[0][17] = bean.getPercentage();
					saveCandEval[0][18] = bean.getIntDtlCode();
					saveCandEval[0][19] = bean.getStrength().trim();
					saveCandEval[0][20] = bean.getWeakness().trim();
					/**Here we are checking whether interview status is On-hold OR not.
					 * if yes then update HRMS_REC_CANDEVAL 
					 *& delete insert records in HRMS_REC_CANDEVAL_DTL table with respect to candidateEvaluationCode
					 */
					
					if(!bean.getCandidateEvaluationCode().trim().equals("")) {
						String updateCandEvalHdrQuery = "UPDATE HRMS_REC_CANDEVAL SET HRMS_REC_CANDEVAL.EVAL_REQS_CODE = ?, HRMS_REC_CANDEVAL.EVAL_CAND_CODE = ?, " + 
														" HRMS_REC_CANDEVAL.EVAL_CURRINT_DATE = TO_DATE(?,'DD-MM-YYYY'), HRMS_REC_CANDEVAL.EVAL_CURRINT_TIME = ?, " +
														" HRMS_REC_CANDEVAL.EVAL_CURR_CTC = ?, HRMS_REC_CANDEVAL.EVAL_NEGO_CTC = ?, " + 
														" HRMS_REC_CANDEVAL.EVAL_EXP_JOINDATE = TO_DATE(?,'DD-MM-YYYY'), HRMS_REC_CANDEVAL.EVAL_CONSTRAINTS = ?, " +
														" HRMS_REC_CANDEVAL.EVAL_READY_RELOC = ?, HRMS_REC_CANDEVAL.EVAL_EMP_TYPE = ?, HRMS_REC_CANDEVAL.EVAL_INT_STATUS = ?, HRMS_REC_CANDEVAL.EVAL_COMMENTS = ?, " +
														" HRMS_REC_CANDEVAL.EVAL_MAKE_OFFER = ?, HRMS_REC_CANDEVAL.EVAL_FWD_NEXTROU = ?, HRMS_REC_CANDEVAL.EVAL_INT_EMPID = ?, HRMS_REC_CANDEVAL.EVAL_ROUND_TYPE = ?, " +
														" HRMS_REC_CANDEVAL.EVAL_SCORE = ?, HRMS_REC_CANDEVAL.EVAL_PERCENTAGE = ?, HRMS_REC_CANDEVAL.EVAL_INT_DTL_CODE = ?, HRMS_REC_CANDEVAL.EVAL_STRENGTH = ?, HRMS_REC_CANDEVAL.EVAL_WEAKNESS = ? " + 
														" WHERE HRMS_REC_CANDEVAL.EVAL_CODE = " + bean.getCandidateEvaluationCode(); 
						getSqlModel().singleExecute(updateCandEvalHdrQuery, saveCandEval);
						String deleteCandEvalDtlQuery = "DELETE FROM HRMS_REC_CANDEVAL_DTL WHERE EVAL_CODE = " + bean.getCandidateEvaluationCode();
						boolean deleteCandEvalDtlObj = getSqlModel().singleExecute(deleteCandEvalDtlQuery);
						if(deleteCandEvalDtlObj) {
							String evalDtlQuery = "INSERT INTO HRMS_REC_CANDEVAL_DTL(EVAL_DTL_CODE, EVAL_CODE, EVAL_RATE_CODE, EVAL_RATE_SCORE, EVAL_RATE_COMMENTS) VALUES"
								+ " (?," + bean.getCandidateEvaluationCode() + ",?,?,?)";

							String maxDtlCode = " SELECT NVL(MAX(EVAL_DTL_CODE),0) FROM HRMS_REC_CANDEVAL_DTL ";
							Object[][] maxCodeObj = getSqlModel().getSingleResult(maxDtlCode);
							int maxCode = Integer.parseInt("" + maxCodeObj[0][0]);

							if (parameterCode != null && parameterCode.length > 0) {
								Object evalDtlObj[][] = new Object[parameterCode.length][4];
								for (int i = 0; i < parameterCode.length; i++) {
									evalDtlObj[i][0] = ++maxCode;
									evalDtlObj[i][1] = parameterCode[i];
									evalDtlObj[i][2] = parameterRating[i];
									evalDtlObj[i][3] = parameterComment[i];
								}
								getSqlModel().singleExecute(evalDtlQuery, evalDtlObj);
							}
						}
					} else {
						String evalHdrQuery = "INSERT INTO HRMS_REC_CANDEVAL (EVAL_CODE,EVAL_REQS_CODE, EVAL_CAND_CODE, "
							+ "EVAL_CURRINT_DATE, EVAL_CURRINT_TIME,EVAL_CURR_CTC, EVAL_NEGO_CTC, EVAL_EXP_JOINDATE, EVAL_CONSTRAINTS,"
							+ "EVAL_READY_RELOC, EVAL_EMP_TYPE, EVAL_INT_STATUS, EVAL_COMMENTS,"
							+ "EVAL_MAKE_OFFER, EVAL_FWD_NEXTROU, EVAL_INT_EMPID, EVAL_ROUND_TYPE,"
							+ "EVAL_SCORE, EVAL_PERCENTAGE, EVAL_INT_DTL_CODE,EVAL_STRENGTH, EVAL_WEAKNESS) "
							+ " VALUES((SELECT NVL(MAX(EVAL_CODE),0) + 1 FROM HRMS_REC_CANDEVAL),"
							+ "?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						getSqlModel().singleExecute(evalHdrQuery, saveCandEval);
					
						String evalDtlQuery = "INSERT INTO HRMS_REC_CANDEVAL_DTL(EVAL_DTL_CODE, EVAL_CODE, EVAL_RATE_CODE, EVAL_RATE_SCORE, EVAL_RATE_COMMENTS) VALUES"
											+ " (?,(SELECT MAX(EVAL_CODE) FROM HRMS_REC_CANDEVAL),?,?,?)";

						String maxDtlCode = " SELECT NVL(MAX(EVAL_DTL_CODE),0) FROM HRMS_REC_CANDEVAL_DTL ";
						Object[][] maxCodeObj = getSqlModel().getSingleResult(maxDtlCode);
						int maxCode = Integer.parseInt("" + maxCodeObj[0][0]);

						if (parameterCode != null && parameterCode.length > 0) {
							Object evalDtlObj[][] = new Object[parameterCode.length][4];

							for (int i = 0; i < parameterCode.length; i++) {
									evalDtlObj[i][0] = ++maxCode;
									evalDtlObj[i][1] = parameterCode[i];
									evalDtlObj[i][2] = parameterRating[i];
									evalDtlObj[i][3] = parameterComment[i];
							}
							getSqlModel().singleExecute(evalDtlQuery, evalDtlObj);
						}
					}
					
					String statusToUpdate = "";
					if(bean.getIntrStatus().equals("O")){
						statusToUpdate = "O";
					}else{
						statusToUpdate = "Y";
					}
					String updateSchIntDtl = " UPDATE HRMS_REC_SCHINT_DTL SET INT_CONDUCT_STATUS =  '"+statusToUpdate+"'"   //'Y' "
							+ " ,INT_ROUND_TYPE ='" + bean.getIntRound() //INT_NEW_ROUND_TYPE
							+ "' WHERE INT_DTL_CODE = " + bean.getIntDtlCode();
					getSqlModel().singleExecute(updateSchIntDtl);

					if (!bean.getIntrStatus().equals("R")) {
						if (bean.getMakeOffer().equals("false")) {
							if (bean.getIntrStatus().equals("O")) {
								String insertStatus = "UPDATE HRMS_REC_INTSTATUS SET STATUS_INTDTLCODE = (SELECT NVL(MAX(INT_DTL_CODE),0) FROM HRMS_REC_SCHINT_DTL),"
										+ " STATUS_STAGE = 'O', STATUS_EVACODE = (SELECT NVL(MAX(EVAL_CODE),0) FROM HRMS_REC_CANDEVAL) "
										+ " WHERE STATUS_INTDTLCODE = "
										+ bean.getIntDtlCode();
								getSqlModel().singleExecute(insertStatus);
								result = "keptOnHold";
							} else {
								String insertSchIntDtl = "INSERT INTO HRMS_REC_SCHINT_DTL COLUMNS(INT_DTL_CODE,INT_CODE,"
													   + " INT_CAND_CODE,INT_REQS_CODE,INT_VIEWER_EMPID, INT_ROUND_TYPE )" // INT_NEW_ROUND_TYPE
													   + " VALUES((SELECT NVL(MAX(INT_DTL_CODE),0)+1 FROM HRMS_REC_SCHINT_DTL),?,?,?,?,?)";
								Object[][] schDtlObj = new Object[1][5];
								schDtlObj[0][0] = bean.getIntCode();
								schDtlObj[0][1] = bean.getCandCode();
								schDtlObj[0][2] = bean.getRequisitionCode();
								schDtlObj[0][3] = bean.getSelectInterId();
								schDtlObj[0][4] = bean.getNxtRoundNo();
								getSqlModel().singleExecute(insertSchIntDtl, schDtlObj);
								
								String insertStatus = "UPDATE HRMS_REC_INTSTATUS SET STATUS_INTDTLCODE = (SELECT NVL(MAX(INT_DTL_CODE),0) FROM HRMS_REC_SCHINT_DTL),"
													+ " STATUS_STAGE = 'F', STATUS_EVACODE = (SELECT NVL(MAX(EVAL_CODE),0) FROM HRMS_REC_CANDEVAL) "
													+ " WHERE STATUS_INTDTLCODE = " + bean.getIntDtlCode();
								getSqlModel().singleExecute(insertStatus);
								result = "1";
							}
							
						} else {
							String interviewStatusToUpdate = "";
							if (bean.getIntrStatus().equals("O")) {
								interviewStatusToUpdate = "O";
							} else {
								interviewStatusToUpdate = "M";
							}
							
							String insertStatus = "UPDATE HRMS_REC_INTSTATUS SET STATUS_STAGE = '"+ interviewStatusToUpdate +"',"
												+ " STATUS_EVACODE = (SELECT NVL(MAX(EVAL_CODE),0) FROM HRMS_REC_CANDEVAL) "
												+ " WHERE STATUS_INTDTLCODE = " + bean.getIntDtlCode();
							getSqlModel().singleExecute(insertStatus);
						
							String offer = " INSERT INTO HRMS_REC_OFFER(OFFER_CODE,OFFER_REQS_CODE,OFFER_CAND_CODE,OFFER_STATUS, "
									+ " OFFER_EMP_TYPE,OFFER_JOIN_DATE,OFFER_CONSTRAINTS,OFFER_DUE_DATE,OFFER_CURR_CTC, "
									+ " OFFER_NEG_CTC, OFFER_JD, OFFER_ROLES_RESP, OFFER_HIRE_MANAGER,OFFER_DIVISION, "
									+ " OFFER_BRANCH, OFFER_DEPT, OFFER_POSITION,OFFER_SIGN_AUTH) "
									+ " VALUES((SELECT NVL(MAX(OFFER_CODE),0)+1 FROM HRMS_REC_OFFER),?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,"
									+ "  TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,(SELECT CONF_SIGN_AUTH FROM HRMS_REC_CONF)) ";

							Object[][] offerObj = new Object[1][15];
							offerObj[0][0] = bean.getRequisitionCode();
							offerObj[0][1] = bean.getCandCode();
							offerObj[0][2] = "D";
							offerObj[0][3] = bean.getEmpTypeCode();
							offerObj[0][4] = bean.getExptdJoinDate();
							offerObj[0][5] = bean.getConstraints();
							offerObj[0][6] = bean.getCurrentCTC();
							offerObj[0][7] = bean.getNegoCTC();
							offerObj[0][8] = bean.getJobDesc();
							offerObj[0][9] = bean.getRolesResponsibility();
							offerObj[0][10] = bean.getHiringManagerCode();
							offerObj[0][11] = bean.getDivisionCode();
							offerObj[0][12] = bean.getBranchCode();
							offerObj[0][13] = bean.getDeptCode();
							offerObj[0][14] = bean.getPositionCode();

							getSqlModel().singleExecute(offer, offerObj);
						/*
							String updateVacancyOfferstatusQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET VAC_OFFER_DATE = SYSDATE, VAC_OFFER_CODE = (SELECT NVL(MAX(OFFER_CODE),0) FROM HRMS_REC_OFFER) WHERE VAC_REQS_CODE = "
									+ bean.getRequisitionCode() + " AND ROWNUM=1 AND VAC_OFFER_GIVEN = 'N' AND VAC_OFFER_CODE IS NULL";
							getSqlModel().singleExecute(updateVacancyOfferstatusQuery);*/
							result = "2";
						}
					} else {
						String insertStatus = "UPDATE HRMS_REC_INTSTATUS SET STATUS_STAGE = 'R',"
								+ " STATUS_EVACODE = (SELECT NVL(MAX(EVAL_CODE),0) FROM HRMS_REC_CANDEVAL) "
								+ " WHERE STATUS_INTDTLCODE = "
								+ bean.getIntDtlCode();
						getSqlModel().singleExecute(insertStatus);
						result = "3";
					}
			//	}else{
			//		result = "alreadyProcessed";
			//	}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// =========== for mail to recruiter ==============
		// String temaplateCode ="";
		try {
			String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " WHERE EVENT_CODE=63";
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);
			System.out.println("tempData.length======= " + tempData.length);
			if (tempData != null && tempData.length > 0) {
				System.out.println("String.valueOf(tempData[0][0])========= "
						+ String.valueOf(tempData[0][0]));
				if (!(String.valueOf(tempData[0][0]).equals("N"))) {
					// temaplateCode =String.valueOf(tempData[0][1]);
					String evalMaxCode = "";
					String evalSql = " SELECT NVL(MAX(EVAL_CODE),0) FROM HRMS_REC_CANDEVAL ";
					Object[][] evalData = getSqlModel()
							.getSingleResult(evalSql);
					if (evalData != null && evalData.length > 0) {
						evalMaxCode = String.valueOf(evalData[0][0]);
					}

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);

					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, bean.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, evalMaxCode);

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, evalMaxCode);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, evalMaxCode);

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, evalMaxCode);

					template.configMailAlert();
					template.sendApplicationMail();
					template.clearParameters();
					// template.terminate();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ============ end of recruiter ============
		return result;
	}

	/**
	 * Following method is used to set the data back to candidate evaluation
	 * bean which is sent to post resume form
	 * 
	 * @param candCode
	 * @param formValues
	 * @param bean
	 */
	public void setCandEvalAfterPostResume(String candCode,
			Object[] formValues, ConductInterview bean) {
		// formValues object returned by post resume setting data back to bean
		try {
			if (formValues != null) {
				for (int i = 0; i < formValues.length; i++)
					bean.setRequisitionCode(String.valueOf(formValues[1]));
				bean.setRequisitionName(String.valueOf(formValues[2]));
				bean.setHiringManager(String.valueOf(formValues[3]));
				bean.setDeptCode(String.valueOf(formValues[4]));
				bean.setDepartment(String.valueOf(formValues[5]));
				bean.setBranchCode(String.valueOf(formValues[6]));
				bean.setBranch(String.valueOf(formValues[7]));
				bean.setPosition(String.valueOf(formValues[8]));
				bean.setPositionCode(String.valueOf(formValues[9]));
				bean.setDivision(String.valueOf(formValues[10]));
				bean.setDivisionCode(String.valueOf(formValues[11]));
				bean.setIntRound(String.valueOf(formValues[12]));
				bean.setIntDate(String.valueOf(formValues[13]));
				bean.setIntTime(String.valueOf(formValues[14]));
				bean.setQlfDts(String.valueOf(formValues[15]));
				bean.setTechSkills(String.valueOf(formValues[16]));
				bean.setCommLevel(String.valueOf(formValues[17]));
				bean.setMgmtSkills(String.valueOf(formValues[18]));
				bean.setPersonality(String.valueOf(formValues[19]));
				bean.setLrngSkills(String.valueOf(formValues[20]));
				bean.setRlvntExp(String.valueOf(formValues[21]));
				bean.setSutAbility(String.valueOf(formValues[22]));
				bean.setEvalScore(String.valueOf(formValues[23]));
				bean.setPercentage(String.valueOf(formValues[24]));
				bean.setCurrentCTC(String.valueOf(formValues[25]));
				bean.setReadyReloc(String.valueOf(formValues[26]));
				bean.setNegoCTC(String.valueOf(formValues[27]));
				bean.setIntrStatus(String.valueOf(formValues[28]));
				bean.setExptdJoinDate(String.valueOf(formValues[29]));
				bean.setEmpType(String.valueOf(formValues[30]));
				bean.setEmpTypeCode(String.valueOf(formValues[31]));
				bean.setConstraints(String.valueOf(formValues[32]));
				bean.setComments(String.valueOf(formValues[33]));
				bean.setMakeOffer(String.valueOf(formValues[34]));
				bean.setFwdNxtRnd(String.valueOf(formValues[35]));
				bean.setNxtRoundNo(String.valueOf(formValues[36]));
				bean.setSelectInterId(String.valueOf(formValues[37]));
				bean.setSelectInter(String.valueOf(formValues[38]));
				bean.setAddNewFlag(String.valueOf(formValues[39]));
			}

			if (candCode != null && !candCode.equals("")) {
				String candQry = " SELECT CAND_FIRST_NAME || ' ' || CAND_MID_NAME || ' ' || CAND_LAST_NAME FROM HRMS_REC_CAND_DATABANK "
						+ " WHERE CAND_CODE = " + candCode;
				Object candObj[][] = getSqlModel().getSingleResult(candQry);
				// setting new candidate to bean
				bean.setCandCode(candCode);
				bean.setCandName(String.valueOf(candObj[0][0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of method

	public String addNewSubmit(ConductInterview bean, String[] parameterCode,
			String[] parameterRating, String[] parameterComment) {
		bean.setBckToIntrFlag("true");
		String result = "";
		Object[][] maxIntHdrCode = null;
		Object[][] maxIntDtlCode = null;
		String interviewer = "";
		String statusStage = "M";
		try {
				
			//############################ BEGIN == QUICK REQUISITION ###############################
			Object[][] chkIfPublishData = null;
			String checkIfPublished = "SELECT PUB_CODE FROM HRMS_REC_VACPUB_HDR " + 
									  "WHERE PUB_REQS_CODE = " + bean.getRequisitionCode() + " AND PUB_STATUS = 'P'";
			chkIfPublishData = getSqlModel().getSingleResult(checkIfPublished);
			if (chkIfPublishData != null && chkIfPublishData.length > 0) {

			} else {
				Object[][] reqVacDtlCode = null;
				String vacDtlQuery = "SELECT VACAN_DTL_CODE,VACAN_NUMBERS FROM HRMS_REC_REQS_VACDTL "
									+ " WHERE REQS_CODE = "	+ bean.getRequisitionCode();
				reqVacDtlCode = getSqlModel().getSingleResult(vacDtlQuery);
				if (reqVacDtlCode != null && reqVacDtlCode.length > 0) {
					// BEGINS -- Insert Records into HRMS_REC_VACANCIES_STATS
					Object[][] pubMaxCode = null;
					String pubCode = "SELECT NVL(MAX(PUB_CODE),0)+1 FROM HRMS_REC_VACPUB_HDR";
					pubMaxCode = getSqlModel().getSingleResult(pubCode);
					 
					Object[][] vacancyObj = null;
					String insertVacancyDtlQuery = "";
					String maxCodeQuery = "SELECT NVL(MAX(VAC_CODE),0)+1 FROM HRMS_REC_VACANCIES_STATS";
					Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(maxCodeQuery);
					int count=0;
					if(maxCodeQueryObj!=null && maxCodeQueryObj.length>0) {
						count =Integer.parseInt(String.valueOf(maxCodeQueryObj[0][0]));
					}
					vacancyObj =new Object[Integer.parseInt(String.valueOf(reqVacDtlCode[0][1]))][6];
					for (int i = 0; i < vacancyObj.length; i++) {
						vacancyObj[i][0] = count++; 
						vacancyObj[i][1] = bean.getRequisitionCode();
						vacancyObj[i][2] = Utility.checkNull(String.valueOf("O"));
						vacancyObj[i][3] = Utility.checkNull(String.valueOf("N"));
						vacancyObj[i][4] = Utility.checkNull(String.valueOf("N"));
						vacancyObj[i][5] = Utility.checkNull(String.valueOf(pubMaxCode[0][0]));
						
						insertVacancyDtlQuery = "INSERT INTO HRMS_REC_VACANCIES_STATS (VAC_CODE, VAC_REQS_CODE, VAC_STATUS, VAC_OFFER_GIVEN, VAC_APPOINT_GIVEN, VAC_PUBLISH_CODE) "
													+ " VALUES(?,?,?,?,?,?)";
					}
					getSqlModel().singleExecute(insertVacancyDtlQuery, vacancyObj);
					// ENDS -- Insert Records into HRMS_REC_VACANCIES_STATS
					
					for (int i = 0; i < reqVacDtlCode.length; i++) {
						String insertVacHdr = "INSERT INTO HRMS_REC_VACPUB_HDR (PUB_CODE,PUB_REQS_CODE,PUB_STATUS, PUB_DATE, "
								+ " PUB_VACAN_DTLCODE, PUB_EMPID,PUB_TO_CONS, PUB_TO_REF, PUB_TO_WEB,PUB_REC_TYPE_INT, PUB_REC_TYPE_EXT, PUB_TO_CANDJOB) VALUES("
								+ Utility.checkNull(String.valueOf(pubMaxCode[0][0])) + "," 
								+ bean.getRequisitionCode()
								+ ",'P',TO_DATE(SYSDATE,'DD-MM-YYYY'),"
								+ reqVacDtlCode[i][0]
								+ ","
								+ bean.getUserEmpId()
								+ ",'Y','Y','Y','Y','Y','Y')";

						boolean resultHdrInsert = getSqlModel().singleExecute(
								insertVacHdr);

						if (resultHdrInsert) {
							String updReqVacDtlQuey = "UPDATE HRMS_REC_REQS_VACDTL SET VACAN_STATUS='P' WHERE VACAN_DTL_CODE = "
								+ reqVacDtlCode[i][0] + "";
							getSqlModel().singleExecute(updReqVacDtlQuey);
									
							String insertVacDtl = "INSERT INTO HRMS_REC_VACPUB_RECDTL (PUB_DTL_CODE, PUB_CODE, PUB_REC_EMPID, PUB_ASG_VAC, PUB_VAC_STATUS) "
									+ " VALUES((SELECT NVL(MAX(PUB_DTL_CODE),0)+1 FROM HRMS_REC_VACPUB_RECDTL),"
									+ " (SELECT NVL(MAX(PUB_CODE),0) FROM HRMS_REC_VACPUB_HDR),"
									+ bean.getRecruiterId()
									+ ","
									+ reqVacDtlCode[i][1] + ",'O')";
							getSqlModel().singleExecute(insertVacDtl);
						} // end of if
					} // end of loop
				} // end of if
			}
			
			
			String chkVacancyStatsDataQuery = "SELECT VAC_OFFER_CODE, VAC_OFFER_GIVEN FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE = "+ bean.getRequisitionCode() +" AND VAC_STATUS = 'O' AND VAC_OFFER_GIVEN='N' AND VAC_OFFER_CODE IS NULL";
			Object[][] chkVacancyStatsDataObj = getSqlModel().getSingleResult(chkVacancyStatsDataQuery);
			if(chkVacancyStatsDataObj !=null && chkVacancyStatsDataObj.length>0){
				Object[][] checkResumeData = null;
				String checkResumeBank = "SELECT RESUME_REQS_CODE,RESUME_CAND_CODE FROM HRMS_REC_RESUME_BANK "
						+ " WHERE RESUME_REQS_CODE ="
						+ bean.getRequisitionCode()
						+ " AND RESUME_CAND_CODE =" + bean.getCandCode() + " ";
				checkResumeData = getSqlModel().getSingleResult(checkResumeBank);
				if (checkResumeData != null && checkResumeData.length > 0) {

				} else {
					String insertResumeBank = "INSERT INTO HRMS_REC_RESUME_BANK (RESUME_CODE, RESUME_REQS_CODE, RESUME_CAND_CODE, RESUME_REC_EMPID, "
							+ " RESUME_APPR_EMPID, RESUME_STATUS) VALUES((SELECT NVL(MAX(RESUME_CODE),0)+1 FROM HRMS_REC_RESUME_BANK),"
							+ " "
							+ bean.getRequisitionCode()
							+ ","
							+ bean.getCandCode()
							+ ",'"
							+ bean.getRecruiterId()
							+ "', " + " " + bean.getUserEmpId() + ",'D')";
					getSqlModel().singleExecute(insertResumeBank);
				}
			}
			//############################ ENDS  == QUICK REQUISITION ###############################
			
			
			//String vacancyStatsDataQuery = "SELECT VAC_OFFER_CODE, VAC_OFFER_GIVEN FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE = "+bean.getRequisitionCode() +" AND VAC_STATUS = 'O' AND VAC_OFFER_GIVEN='N' AND VAC_OFFER_CODE IS NULL";
			//	Object[][] vacancyStatsDataObj = getSqlModel().getSingleResult(vacancyStatsDataQuery);
			//	if(vacancyStatsDataObj != null && vacancyStatsDataObj.length>0) {
					if (bean.getSelectInterId().equals("")) {
						interviewer = bean.getUserEmpId();
					} else {
						interviewer = bean.getSelectInterId();
					}
					boolean resultHdr = false;
					boolean resultDtl = false;
					int dtlCode = 0;
					String queryHdr = "SELECT NVL(MAX(INT_CODE),0) + 1 FROM HRMS_REC_SCHINT";
					maxIntHdrCode = getSqlModel().getSingleResult(queryHdr);
					String queryDtl = "SELECT NVL(MAX(INT_DTL_CODE),0) + 1 FROM HRMS_REC_SCHINT_DTL";
					maxIntDtlCode = getSqlModel().getSingleResult(queryDtl);
					if (maxIntHdrCode != null && maxIntHdrCode.length != 0) {
						String insertSchdHdr = "INSERT INTO HRMS_REC_SCHINT (INT_CODE, INT_REQS_CODE,INT_HIRE_EMPID,INT_REC_EMPID)"
								+ " VALUES("
								+ maxIntHdrCode[0][0]
								+ ","
								+ bean.getRequisitionCode()
								+ ","
								+ bean.getUserEmpId()
								+ ","
								+ bean.getUserEmpId()
								+ ")";
						resultHdr = getSqlModel().singleExecute(insertSchdHdr);
					}
					if (resultHdr) {
						dtlCode = Integer.parseInt(String.valueOf(maxIntDtlCode[0][0]));
						String statusToUpdate = "";
						if(bean.getIntrStatus().equals("O")) {
							statusToUpdate = "O";
						}else{
							statusToUpdate = "Y";
						}
						String insertSchdDtl = "INSERT INTO HRMS_REC_SCHINT_DTL (INT_DTL_CODE, INT_CODE, INT_CAND_CODE, "
								+ "INT_REQS_CODE, INT_DATE, INT_TIME, INT_VIEWER_EMPID, INT_ROUND_TYPE , INT_CONDUCT_STATUS)" //INT_NEW_ROUND_TYPE
								+ " VALUES ("
								+ dtlCode
								+ ","
								+ maxIntHdrCode[0][0]
								+ ","
								+ bean.getCandCode()
								+ ","
								+ bean.getRequisitionCode()
								+ ","
								+ " TO_DATE('"
								+ bean.getIntDate()
								+ "','DD-MM-YYYY'),'"
								+ bean.getIntTime() + "'," + bean.getUserEmpId()// for
								// Make
								// offer....
								+ "," + " '" + bean.getIntRound() + "','"+statusToUpdate+"')";   //'Y'
						resultDtl = getSqlModel().singleExecute(insertSchdDtl);
						
						if(bean.getIntrStatus().equals("O")) {
							result = "keptOnHold";
						}else{
							result = "2";
						}
						
						/***************************************************************
						 * this IF is for fwd to next round..
						 */
						if (!bean.getNxtRoundNo().equals("-1")) {
							dtlCode = dtlCode + 1;
							statusStage = "F";
							result = "1";
							String insertSchdDtlFwdNxt = "INSERT INTO HRMS_REC_SCHINT_DTL (INT_DTL_CODE, INT_CODE, INT_CAND_CODE, "
									+ "INT_REQS_CODE,INT_VIEWER_EMPID, INT_ROUND_TYPE, INT_CONDUCT_STATUS)" // INT_NEW_ROUND_TYPE
									+ " VALUES ("
									+ dtlCode
									+ ","
									+ maxIntHdrCode[0][0]
									+ ","
									+ bean.getCandCode()
									+ ","
									+ bean.getRequisitionCode()
									+ ","
									+ bean.getSelectInterId()// For fwd next
									// interview round..
									+ "," + " '" + bean.getNxtRoundNo() + "','N')";
							resultDtl = getSqlModel()
									.singleExecute(insertSchdDtlFwdNxt);

						}

						if (resultDtl) {
							Object saveCandEval[][] = new Object[1][21];
							saveCandEval[0][0] = bean.getRequisitionCode();
							saveCandEval[0][1] = bean.getCandCode();
							saveCandEval[0][2] = bean.getIntDate();
							saveCandEval[0][3] = bean.getIntTime();
							saveCandEval[0][4] = bean.getCurrentCTC();
							saveCandEval[0][5] = bean.getNegoCTC();
							saveCandEval[0][6] = bean.getExptdJoinDate();
							saveCandEval[0][7] = bean.getConstraints();
							saveCandEval[0][8] = bean.getReadyReloc();
							saveCandEval[0][9] = bean.getEmpTypeCode();
							saveCandEval[0][10] = bean.getIntrStatus();
							saveCandEval[0][11] = bean.getComments();
							if (bean.getMakeOffer().equals("true")) {
								saveCandEval[0][12] = "Y";
								saveCandEval[0][13] = "N";
							} else {
								saveCandEval[0][12] = "N";
								saveCandEval[0][13] = "Y";
							}
							if (bean.getIntrStatus().equals("R") || bean.getIntrStatus().equals("O")) {
								saveCandEval[0][12] = "N";
								saveCandEval[0][13] = "N";
							}
							saveCandEval[0][14] = bean.getSelectInterId();
							saveCandEval[0][15] = bean.getIntRound();
							saveCandEval[0][16] = bean.getEvalScore();
							saveCandEval[0][17] = bean.getPercentage();
							saveCandEval[0][18] = maxIntDtlCode[0][0];

							saveCandEval[0][19] = bean.getStrength().trim();

							saveCandEval[0][20] = bean.getWeakness().trim();
							String evalHdrQuery = "INSERT INTO HRMS_REC_CANDEVAL (EVAL_CODE,EVAL_REQS_CODE, EVAL_CAND_CODE, "
									+ "EVAL_CURRINT_DATE, EVAL_CURRINT_TIME,EVAL_CURR_CTC, EVAL_NEGO_CTC, EVAL_EXP_JOINDATE, EVAL_CONSTRAINTS,"
									+ "EVAL_READY_RELOC, EVAL_EMP_TYPE, EVAL_INT_STATUS, EVAL_COMMENTS,"
									+ "EVAL_MAKE_OFFER, EVAL_FWD_NEXTROU, EVAL_INT_EMPID, EVAL_ROUND_TYPE,"
									+ "EVAL_SCORE, EVAL_PERCENTAGE, EVAL_INT_DTL_CODE,EVAL_STRENGTH, EVAL_WEAKNESS) "
									+ " VALUES((SELECT NVL(MAX(EVAL_CODE),0) + 1 FROM HRMS_REC_CANDEVAL),"
									+ "?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							getSqlModel().singleExecute(evalHdrQuery, saveCandEval);

							String evalDtlQuery = "INSERT INTO HRMS_REC_CANDEVAL_DTL(EVAL_DTL_CODE, EVAL_CODE, EVAL_RATE_CODE, EVAL_RATE_SCORE, EVAL_RATE_COMMENTS, EVAL_GROUP_ID) VALUES"
									+ " (?,(SELECT MAX(EVAL_CODE) FROM HRMS_REC_CANDEVAL),?,?,?)";
							String maxDtlCode = " SELECT NVL(MAX(EVAL_DTL_CODE),0) FROM HRMS_REC_CANDEVAL_DTL ";
							Object[][] maxCodeObj = getSqlModel().getSingleResult(
									maxDtlCode);
							int maxCode = Integer.parseInt("" + maxCodeObj[0][0]);

							if (parameterCode != null && parameterCode.length > 0) {
								Object evalDtlObj[][] = new Object[parameterCode.length][4];
								for (int i = 0; i < parameterCode.length; i++) {
									evalDtlObj[i][0] = ++maxCode;
									evalDtlObj[i][1] = parameterCode[i];
									evalDtlObj[i][2] = parameterRating[i];
									evalDtlObj[i][3] = parameterComment[i];
									evalDtlObj[i][4] = bean.getGroupId();
								}
								getSqlModel().singleExecute(evalDtlQuery, evalDtlObj);
							}

							String insertIntStatus = "INSERT INTO HRMS_REC_INTSTATUS (STATUS_CODE, STATUS_REQS_CODE, STATUS_CAND_CODE, "
									+ "STATUS_STAGE, STATUS_INTCODE, STATUS_INTDTLCODE, STATUS_EVACODE)"
									+ " VALUES((SELECT NVL(MAX(STATUS_CODE),0) + 1 FROM HRMS_REC_INTSTATUS),"
									+ bean.getRequisitionCode()
									+ ","
									+ " "
									+ bean.getCandCode()
									+ ",'"
									+ statusStage
									+ "',"
									+ maxIntHdrCode[0][0]
									+ ","
									+ dtlCode
									+ ",(SELECT MAX(EVAL_CODE) FROM HRMS_REC_CANDEVAL))";
							getSqlModel().singleExecute(insertIntStatus);

							/**
							 * for interview status reject
							 */
							if (bean.getIntrStatus().equals("R")) {
								result = "3";
								String insertIntStatusReject = "INSERT INTO HRMS_REC_INTSTATUS (STATUS_CODE, STATUS_REQS_CODE, STATUS_CAND_CODE, "
										+ "STATUS_STAGE, STATUS_INTCODE, STATUS_INTDTLCODE, STATUS_EVACODE)"
										+ " VALUES((SELECT NVL(MAX(STATUS_CODE),0) + 1 FROM HRMS_REC_INTSTATUS),"
										+ bean.getRequisitionCode()
										+ ","
										+ " "
										+ bean.getCandCode()
										+ ",'R',"
										+ maxIntHdrCode[0][0]
										+ ","
										+ dtlCode
										+ ",(SELECT MAX(EVAL_CODE) FROM HRMS_REC_CANDEVAL))";
								getSqlModel().singleExecute(insertIntStatusReject);
							}

							if (bean.getMakeOffer().equals("true")) {
								String offer = " INSERT INTO HRMS_REC_OFFER(OFFER_CODE,OFFER_REQS_CODE,OFFER_CAND_CODE,OFFER_STATUS, "
										+ " OFFER_EMP_TYPE,OFFER_JOIN_DATE,OFFER_CONSTRAINTS,OFFER_DUE_DATE,OFFER_CURR_CTC, "
										+ " OFFER_NEG_CTC, OFFER_JD, OFFER_ROLES_RESP,OFFER_HIRE_MANAGER,OFFER_DIVISION, "
										+ " OFFER_BRANCH, OFFER_DEPT, OFFER_POSITION) "
										+ " VALUES((SELECT NVL(MAX(OFFER_CODE),0)+1 FROM HRMS_REC_OFFER),?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,"
										+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?) ";
								Object[][] offerObj = new Object[1][15];
								offerObj[0][0] = bean.getRequisitionCode();
								offerObj[0][1] = bean.getCandCode();
								offerObj[0][2] = "D";
								offerObj[0][3] = bean.getEmpTypeCode();
								offerObj[0][4] = bean.getExptdJoinDate();
								offerObj[0][5] = bean.getConstraints();
								offerObj[0][6] = bean.getCurrentCTC();
								offerObj[0][7] = bean.getNegoCTC();
								offerObj[0][8] = bean.getJobDesc();
								offerObj[0][9] = bean.getRolesResponsibility();
								offerObj[0][10] = bean.getHiringManagerCode();
								offerObj[0][11] = bean.getDivisionCode();
								offerObj[0][12] = bean.getBranchCode();
								offerObj[0][13] = bean.getDeptCode();
								offerObj[0][14] = bean.getPositionCode();
								getSqlModel().singleExecute(offer, offerObj);

								/*String updateVacancyOfferstatusQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET VAC_OFFER_DATE = SYSDATE, VAC_OFFER_CODE = (SELECT NVL(MAX(OFFER_CODE),0) FROM HRMS_REC_OFFER) WHERE VAC_REQS_CODE = "
										+ bean.getRequisitionCode() + " AND ROWNUM=1 AND VAC_OFFER_GIVEN = 'N' AND VAC_OFFER_CODE IS NULL";
								getSqlModel().singleExecute(updateVacancyOfferstatusQuery);*/
							}
						}
					}
					
					
					// =========== for mail to recruiter ==============

					String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS "
							+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
							+ " WHERE EVENT_CODE=63";
					Object[][] tempData = getSqlModel().getSingleResult(tempSql);
					if (tempData != null && tempData.length > 0) {
						if (!(String.valueOf(tempData[0][0]).equals("N"))) {
							String evalMaxCode = "";
							String evalSql = " SELECT NVL(MAX(EVAL_CODE),0) FROM HRMS_REC_CANDEVAL ";
							Object[][] evalData = getSqlModel()
									.getSingleResult(evalSql);
							if (evalData != null && evalData.length > 0) {
								evalMaxCode = String.valueOf(evalData[0][0]);
							}

							EmailTemplateBody template = new EmailTemplateBody();
							template.initiate(context, session);

							template.setEmailTemplate(String.valueOf(tempData[0][3]));
							template.getTemplateQueries();

							EmailTemplateQuery templateQuery1 = template
									.getTemplateQuery(1);
							templateQuery1.setParameter(1, bean.getUserEmpId());

							EmailTemplateQuery templateQuery2 = template
									.getTemplateQuery(2);
							templateQuery2.setParameter(1, evalMaxCode);

							EmailTemplateQuery templateQuery3 = template
									.getTemplateQuery(3);
							templateQuery3.setParameter(1, evalMaxCode);

							EmailTemplateQuery templateQuery4 = template
									.getTemplateQuery(4);
							templateQuery4.setParameter(1, evalMaxCode);

							EmailTemplateQuery templateQuery5 = template
									.getTemplateQuery(5);
							templateQuery5.setParameter(1, evalMaxCode);

							template.configMailAlert();
							template.sendApplicationMail();
							template.clearParameters();
							// template.terminate();
						}
					}
			//	}else{
			//		result = "alreadyProcessed";
			//	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getRequsitionDetails(ConductInterview condInt, String code) {
		try {
			String query = "SELECT REQS_CODE,REQS_NAME,RANK_NAME,DIV_NAME,CENTER_NAME,DEPT_NAME, "
					+ "REQS_JOB_DESCRIPTION,REQS_ROLE_RESPON,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME "
					+ "from HRMS_REC_REQS_HDR "
					+ "LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION  "
					+ "LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION  "
					+ "LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT  "
					+ "LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH "
					+ "LEFT JOIN HRMS_EMP_OFFC OFFC1 ON OFFC1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER "
					+ "WHERE REQS_CODE= " + code;
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				condInt.setRequisitionCode(String.valueOf(data[0][0]).trim());
				condInt.setRequisitionName(String.valueOf(data[0][1]).trim());
				condInt.setPosition(String.valueOf(data[0][2]).trim());
				condInt.setDivision(String.valueOf(data[0][3]).trim());
				condInt.setBranch(String.valueOf(data[0][4]).trim());
				condInt.setDepartment(String.valueOf(data[0][5]).trim());
				// condInt.setJobDescription(String.valueOf(data[0][6]).trim());
				condInt.setRolesResponsibility(String.valueOf(data[0][7])
						.trim());
				// condInt.setHiringMgr(String.valueOf(data[0][8]).trim());
			}// end of if
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object[][] setEvaluationDetails(HttpServletRequest request,
			ConductInterview conductIntr) {
		Object[][] data = null;
		String query = "";
		try {
			setDropDownValueList(conductIntr);
			if(conductIntr.getOnHoldInterviewFlag().equals("true")) {
				query = "SELECT HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_CODE, HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_PARAM, HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_DESC , HRMS_REC_CANDEVAL_DTL.EVAL_RATE_SCORE, HRMS_REC_CANDEVAL_DTL.EVAL_RATE_COMMENTS " +
						" FROM HRMS_REC_INTERVIEW_ASSESSMENT "+ 
						" INNER JOIN HRMS_REC_CANDEVAL_DTL on (HRMS_REC_CANDEVAL_DTL.EVAL_RATE_CODE = HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_CODE) "+
						" WHERE REC_ISACTIVE='Y'  AND EVAL_CODE = "+conductIntr.getCandidateEvaluationCode() +
						" ORDER BY  REC_ASSESS_CODE";
				
				String otherInformationQuery = " SELECT EVAL_CURR_CTC, EVAL_READY_RELOC, EVAL_NEGO_CTC, EVAL_INT_STATUS, TO_CHAR(EVAL_EXP_JOINDATE,'DD-MM-YYYY'), EVAL_EMP_TYPE, TYPE_NAME, EVAL_CONSTRAINTS, EVAL_COMMENTS, EVAL_STRENGTH, EVAL_WEAKNESS, " +
							   " EVAL_MAKE_OFFER, EVAL_FWD_NEXTROU, EVAL_SCORE, EVAL_PERCENTAGE FROM HRMS_REC_CANDEVAL " +
							   " LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_REC_CANDEVAL.EVAL_EMP_TYPE) " +
							   " WHERE EVAL_CODE = "+conductIntr.getCandidateEvaluationCode(); 
				Object[][] otherInformationObj = getSqlModel().getSingleResult(otherInformationQuery);
				if (otherInformationObj != null && otherInformationObj.length > 0) {
					conductIntr.setCurrentCTC(Utility.checkNull(String.valueOf(otherInformationObj[0][0])));
					conductIntr.setReadyReloc(Utility.checkNull(String.valueOf(otherInformationObj[0][1])));
					conductIntr.setNegoCTC(Utility.checkNull(String.valueOf(otherInformationObj[0][2])));
					conductIntr.setIntrStatus(Utility.checkNull(String.valueOf(otherInformationObj[0][3])));
					conductIntr.setExptdJoinDate(Utility.checkNull(String.valueOf(otherInformationObj[0][4])));
					conductIntr.setEmpTypeCode(Utility.checkNull(String.valueOf(otherInformationObj[0][5])));
					conductIntr.setEmpType(Utility.checkNull(String.valueOf(otherInformationObj[0][6])));
					conductIntr.setConstraints(Utility.checkNull(String.valueOf(otherInformationObj[0][7])));
					conductIntr.setComments(Utility.checkNull(String.valueOf(otherInformationObj[0][8])));
					conductIntr.setStrength(Utility.checkNull(String.valueOf(otherInformationObj[0][9])));
					conductIntr.setWeakness(Utility.checkNull(String.valueOf(otherInformationObj[0][10])));
					conductIntr.setMakeOffer(Utility.checkNull(String.valueOf(otherInformationObj[0][11])));
					conductIntr.setFwdNxtRnd(Utility.checkNull(String.valueOf(otherInformationObj[0][12])));
					conductIntr.setEvalScore(Utility.checkNull(String.valueOf(otherInformationObj[0][13])));
					conductIntr.setPercentage(Utility.checkNull(String.valueOf(otherInformationObj[0][14])));
				}
					
					
			} else {
				query = " SELECT REC_ASSESS_CODE, REC_ASSESS_PARAM, REC_ASSESS_DESC " +
						" FROM HRMS_REC_INTERVIEW_ASSESSMENT WHERE REC_ISACTIVE='Y' " +
						" ORDER BY  REC_ASSESS_CODE ";
			}
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void setDropDownValueList(ConductInterview conductIntr) {
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
			conductIntr.setTmap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object[][] getRecruitmentGroupList(ConductInterview conductIntr) {
		Object[][] groupData = null;
		String query = "";
		try {
			setDropDownValueList(conductIntr);
			if (conductIntr.getOnHoldInterviewFlag().equals("true")) {
				query = "SELECT HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_CODE, HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_PARAM, HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_DESC , HRMS_REC_CANDEVAL_DTL.EVAL_RATE_SCORE, HRMS_REC_CANDEVAL_DTL.EVAL_RATE_COMMENTS "
						+ " FROM HRMS_REC_INTERVIEW_ASSESSMENT "
						+ " INNER JOIN HRMS_REC_CANDEVAL_DTL on (HRMS_REC_CANDEVAL_DTL.EVAL_RATE_CODE = HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_CODE) "
						+ " WHERE REC_ISACTIVE='Y'  AND EVAL_CODE = " + conductIntr.getCandidateEvaluationCode()
						+ " ORDER BY  REC_ASSESS_CODE";

				String otherInformationQuery = " SELECT EVAL_CURR_CTC, EVAL_READY_RELOC, EVAL_NEGO_CTC, EVAL_INT_STATUS, TO_CHAR(EVAL_EXP_JOINDATE,'DD-MM-YYYY'), EVAL_EMP_TYPE, TYPE_NAME, EVAL_CONSTRAINTS, EVAL_COMMENTS, EVAL_STRENGTH, EVAL_WEAKNESS, "
						+ " EVAL_MAKE_OFFER, EVAL_FWD_NEXTROU, EVAL_SCORE, EVAL_PERCENTAGE FROM HRMS_REC_CANDEVAL "
						+ " LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_REC_CANDEVAL.EVAL_EMP_TYPE) "
						+ " WHERE EVAL_CODE = " + conductIntr.getCandidateEvaluationCode();
				Object[][] otherInformationObj = getSqlModel().getSingleResult(
						otherInformationQuery);
				if (otherInformationObj != null
						&& otherInformationObj.length > 0) {
					conductIntr.setCurrentCTC(Utility.checkNull(String
							.valueOf(otherInformationObj[0][0])));
					conductIntr.setReadyReloc(Utility.checkNull(String
							.valueOf(otherInformationObj[0][1])));
					conductIntr.setNegoCTC(Utility.checkNull(String
							.valueOf(otherInformationObj[0][2])));
					conductIntr.setIntrStatus(Utility.checkNull(String
							.valueOf(otherInformationObj[0][3])));
					conductIntr.setExptdJoinDate(Utility.checkNull(String
							.valueOf(otherInformationObj[0][4])));
					conductIntr.setEmpTypeCode(Utility.checkNull(String
							.valueOf(otherInformationObj[0][5])));
					conductIntr.setEmpType(Utility.checkNull(String
							.valueOf(otherInformationObj[0][6])));
					conductIntr.setConstraints(Utility.checkNull(String
							.valueOf(otherInformationObj[0][7])));
					conductIntr.setComments(Utility.checkNull(String
							.valueOf(otherInformationObj[0][8])));
					conductIntr.setStrength(Utility.checkNull(String
							.valueOf(otherInformationObj[0][9])));
					conductIntr.setWeakness(Utility.checkNull(String
							.valueOf(otherInformationObj[0][10])));
					conductIntr.setMakeOffer(Utility.checkNull(String
							.valueOf(otherInformationObj[0][11])));
					conductIntr.setFwdNxtRnd(Utility.checkNull(String
							.valueOf(otherInformationObj[0][12])));
					conductIntr.setEvalScore(Utility.checkNull(String
							.valueOf(otherInformationObj[0][13])));
					conductIntr.setPercentage(Utility.checkNull(String
							.valueOf(otherInformationObj[0][14])));
				}

			} else {
				query = " SELECT HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_CODE, "
						+ " HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_PARAM, HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_DESC "
						+ " FROM HRMS_REC_INTERVIEW_ASSESSMENT "
						+ " WHERE HRMS_REC_INTERVIEW_ASSESSMENT.REC_ISACTIVE ='Y' AND "
						+ " HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_GROUP_ID = " + conductIntr.getGroupId();
			}
			groupData = getSqlModel().getSingleResult(query);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return groupData;
	}
	
public String checkNull(String result) {
	if (result == null || result.equals("null")) {
		return "";
	}  // end of if
	else {
		return result;
	} // end of else
}

} // end of class
