package org.paradyne.model.recruitment;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.AppointmentDetails;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * @author aa0540
 * 
 */
public class AppointmentDetailsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AppointmentDetailsModel.class);

	public void joiningChecklist(AppointmentDetails bean,
			HttpServletRequest request) {
		try {
			ArrayList<Object> checkList = new ArrayList<Object>();
			String query = "SELECT CHECK_CODE,CHECK_NAME FROM HRMS_CHECKLIST WHERE CHECK_TYPE = 'J' AND CHECK_STATUS='A' "
					+ "ORDER BY UPPER(CHECK_NAME)";
			Object[][] checkListData = getSqlModel().getSingleResult(query);
			if (checkListData != null && checkListData.length != 0) {
				for (int i = 0; i < checkListData.length; i++) {
					AppointmentDetails bean1 = new AppointmentDetails();
					bean1.setChecklistCode(String.valueOf(checkListData[i][0]));// checklist
					// code
					bean1.setChecklistName(String.valueOf(checkListData[i][1]));// checklist
					// name
					checkList.add(bean1);
				}
				bean.setTestDataList(checkList);
				String str = request.getParameter("id");
				String str1 = request.getParameter("id1");
				bean.setTestRequirements(str);
				bean.setTestReqCode(str1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getRequsitionDetails(AppointmentDetails appointDetails,
			String code) {
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
				appointDetails.setRequisitionCode(String.valueOf(data[0][0])
						.trim());
				appointDetails.setRequisitionName(String.valueOf(data[0][1])
						.trim());
				appointDetails.setPosition(String.valueOf(data[0][2]).trim());
				appointDetails.setDivision(String.valueOf(data[0][3]).trim());
				appointDetails.setBranch(String.valueOf(data[0][4]).trim());
				appointDetails.setDepartment(String.valueOf(data[0][5]).trim());
				appointDetails.setJobDescription(String.valueOf(data[0][6])
						.trim());
				appointDetails.setRolesResponsibility(String
						.valueOf(data[0][7]).trim());
				appointDetails.setHiringMgr(String.valueOf(data[0][8]).trim());
			}// end of if
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAppointmentDetails(AppointmentDetails bean, String status) {
		try {
			Object[][] reqsDetails = getSqlModel().getSingleResult(getQuery(1),
					new Object[] { bean.getRequisitionCode() });
			Object[][] candDetails = getSqlModel().getSingleResult(getQuery(2),
					new Object[] { bean.getAppointmentCode() });

			if (reqsDetails != null && reqsDetails.length != 0) {
				bean.setRequisitionName(checkNull(
						String.valueOf(reqsDetails[0][0])).trim());
				//bean.setReqStatus(checkNull(String.valueOf(reqsDetails[0][9])));
				
				  bean.setPosition(checkNull(String.valueOf(reqsDetails[0][1])));
				  bean.setDivision(checkNull(String.valueOf(reqsDetails[0][2])));
				  bean.setBranch(checkNull(String.valueOf(reqsDetails[0][3])));
				  bean.setDepartment(checkNull(String.valueOf(reqsDetails[0][4])));
				  bean.setPositionCode(checkNull(String.valueOf(reqsDetails[0][5])));//position	  code
				  bean.setDivisionCode(checkNull(String.valueOf(reqsDetails[0][6])));//division code
				  bean.setBranchCode(checkNull(String.valueOf(reqsDetails[0][7])));//branch code
				 bean.setDeptCode(checkNull(String.valueOf(reqsDetails[0][8])));//department code
				 }

			if (candDetails != null && candDetails.length != 0) {
				bean.setCandidateCode(checkNull(
						String.valueOf(candDetails[0][0])).trim());
				bean.setCandidateName(checkNull(
						String.valueOf(candDetails[0][1])).trim());
				bean
						.setJoiningDate(checkNull(String
								.valueOf(candDetails[0][2])));
				if (status.equals("Y")) {
					bean.setAppointmentDate(checkNull(String
							.valueOf(candDetails[0][3])));
				} else if (status.equals("N")) {
					bean.setAppointmentDate(checkNull(String
							.valueOf(candDetails[0][4])));
				}
				bean
						.setCurrentCtc(checkNull(String
								.valueOf(candDetails[0][5])));
				bean.setNegotiatedCtc(checkNull(String
						.valueOf(candDetails[0][6])));

				if (bean.isViewAppointmentFlag()) {
					bean.setAppointmentStatus(checkNull(
							String.valueOf(candDetails[0][30])).trim());
				} else {
					bean.setAppointmentStatus(checkNull(
							String.valueOf(candDetails[0][7])).trim());
				}

				bean.setJobDescription(checkNull(String
						.valueOf(candDetails[0][8])));
				bean.setRolesResponsibility(checkNull(String
						.valueOf(candDetails[0][9])));
				bean.setEmpTypeCode(checkNull(String
						.valueOf(candDetails[0][10])));
				bean.setEmpType(checkNull(String.valueOf(candDetails[0][11])));
				bean.setHiringMgrCode(checkNull(String
						.valueOf(candDetails[0][12])));
				bean.setHiringMgr(checkNull(String.valueOf(candDetails[0][13]))
						.trim());
				bean.setExpJoiningDate(checkNull(String
						.valueOf(candDetails[0][14])));
				bean.setReportingToCode(checkNull(String
						.valueOf(candDetails[0][15])));
				bean.setReportingTo(checkNull(
						String.valueOf(candDetails[0][16])).trim());
				bean.setTestRequirements(checkNull(String
						.valueOf(candDetails[0][17])));
				bean
						.setGradeCode(checkNull(String
								.valueOf(candDetails[0][18])));
				bean.setGrade(checkNull(String.valueOf(candDetails[0][19])));
				bean.setTemplateCode(checkNull(String
						.valueOf(candDetails[0][20])));

				if (bean.isViewAppointmentFlag()) {
					if (String.valueOf(candDetails[0][21]).equals("true")) {
						bean.setProbation("Yes");
					} else {
						bean.setProbation("No");
					}
				} else {
					bean.setProbation(checkNull(String
							.valueOf(candDetails[0][21])));
				}

				bean.setMonths(checkNull(String.valueOf(candDetails[0][22])));
				bean.setAuthorityCode(checkNull(
						String.valueOf(candDetails[0][23])).trim());
				bean.setSigningAuthority(checkNull(
						String.valueOf(candDetails[0][24])).trim());
				bean.setDesignation(checkNull(String
						.valueOf(candDetails[0][25])));
				bean.setRemarks(checkNull(String.valueOf(candDetails[0][26])));
				bean.setCandConstraints(checkNull(String
						.valueOf(candDetails[0][27])));
				if (bean.isViewAppointmentFlag()) {
					if (String.valueOf(candDetails[0][28]).equals("true")) {
						bean.setBackgroundCheck("Yes");
					} else {
						bean.setBackgroundCheck("No");
					}
				} else {
					bean.setBackgroundCheck(checkNull(String
							.valueOf(candDetails[0][28])));
				}
				bean
						.setOfferedCtc(checkNull(String
								.valueOf(candDetails[0][29])));
				bean.setTemplate(checkNull(String.valueOf(candDetails[0][31])));

				bean.setDivisionCode(checkNull(String
						.valueOf(candDetails[0][32])));// division code
				bean
						.setBranchCode(checkNull(String
								.valueOf(candDetails[0][33])));// branch code
				bean.setDeptCode(checkNull(String.valueOf(candDetails[0][34])));// department
				// code
				bean.setPositionCode(checkNull(String
						.valueOf(candDetails[0][35])));// position code
				bean.setPosition(checkNull(String.valueOf(candDetails[0][36])));// position
				bean.setDivision(checkNull(String.valueOf(candDetails[0][37])));// division
				bean.setBranch(checkNull(String.valueOf(candDetails[0][38])));// branch
				bean
						.setDepartment(checkNull(String
								.valueOf(candDetails[0][39])));// department
				bean.setReportingToAdminCode(checkNull(String
						.valueOf(candDetails[0][40])));// Administrative
				// Reporting To
				bean.setReportingToAdmin(checkNull(String
						.valueOf(candDetails[0][41])));// Administrative
				// Reporting To name
				bean.setAnnextureFileName(checkNull(String
						.valueOf(candDetails[0][42])));// Attached Annexure

				bean.setCandidateEmailID(checkNull(String
						.valueOf(candDetails[0][43])));

				if (checkNull(String.valueOf(candDetails[0][44])).trim()
						.equals("")) {
					bean.setShowCandidateCommentsFlag(false);
				} else {
					bean.setShowCandidateCommentsFlag(true);
				}

				bean.setCandidateComments(checkNull(String
						.valueOf(candDetails[0][44])));
				bean.setAppointmentLetterRegCode(checkNull(String
						.valueOf(candDetails[0][45])));

				/**
				 * edited by varun on 12/10/2009
				 */
				Object[][] recruiterData = null;
				String recQuery = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,RESUME_REC_EMPID "
						+ " FROM HRMS_REC_RESUME_BANK "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_RESUME_BANK.RESUME_REC_EMPID) "
						+ " WHERE RESUME_REQS_CODE = "
						+ bean.getRequisitionCode()
						+ " AND RESUME_CAND_CODE = "
						+ bean.getCandidateCode()
						+ "";
				recruiterData = getSqlModel().getSingleResult(recQuery);

				if (recruiterData != null && recruiterData.length > 0) {
					bean.setRecruiterToken(String.valueOf(recruiterData[0][0]));
					bean.setRecruiterName(String.valueOf(recruiterData[0][1]));
					bean.setRecruiterId(String.valueOf(recruiterData[0][2]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Method checkNull
	 * @Purpose to check whether the selected data is null or not
	 * @param result
	 *            value of the data
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void setSysDate(AppointmentDetails bean) {
		try {
			String dateQuery = "SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') FROM DUAL";
			Object[][] sysDate = getSqlModel().getSingleResult(dateQuery);
			bean.setAppointmentDate(String.valueOf(sysDate[0][0]));
			Object[][] sighAuthData = null;
			String signingAuthFrmConf = "SELECT CONF_SIGN_AUTH,EMP_FNAME||' '||EMP_LNAME,RANK_NAME "
					+ " FROM HRMS_REC_CONF "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_CONF.CONF_SIGN_AUTH)"
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)";
			sighAuthData = getSqlModel().getSingleResult(signingAuthFrmConf);
			if (sighAuthData != null && sighAuthData.length > 0) {
				bean.setAuthorityCode(String.valueOf(sighAuthData[0][0]));// emp
																			// id
				bean.setSigningAuthority(String.valueOf(sighAuthData[0][1]));// emp
																				// name
				bean.setDesignation(String.valueOf(sighAuthData[0][2]));// designation
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String save(AppointmentDetails bean, String[] serialNo,
			String[] empCode, String[] empName, String appointmentStatus,
			String hiddenRequisitionCode) {
		String result = "";
		try {
			if (!bean.getAppointmentCode().equals("")) {
				if (bean.getAppointmentStatus().equals("OA")) {
					result = updateAppointmentDetails(bean, appointmentStatus, hiddenRequisitionCode);
				}else if (bean.getAppointmentStatus().equals("D") || bean.getAppointmentStatus().equals("I")||bean.getAppointmentStatus().equals("S")||bean.getAppointmentStatus().equals("OR")||bean.getAppointmentStatus().equals("C")) {
					result = updateAppointmentDetails(bean, appointmentStatus, hiddenRequisitionCode);
				} /*else {
					String vacancyStatsDataQuery = "SELECT VAC_APPOINT_CODE, VAC_APPOINT_GIVEN FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE = " + hiddenRequisitionCode + 
					 							   " AND VAC_STATUS = 'O' AND VAC_APPOINT_GIVEN='N' AND VAC_APPOINT_CODE IS NULL";
					Object[][] vacancyStatsDataObj = getSqlModel().getSingleResult(vacancyStatsDataQuery);
					
					if(vacancyStatsDataObj != null && vacancyStatsDataObj.length>0) { 
						result = updateAppointmentDetails(bean, appointmentStatus, hiddenRequisitionCode);
					}else {
						String vacancyAppointmentStatsDataQuery = "SELECT VAC_APPOINT_CODE, VAC_APPOINT_GIVEN FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE = "
														+ hiddenRequisitionCode	+ " AND VAC_STATUS = 'O' AND VAC_APPOINT_GIVEN='N' AND VAC_APPOINT_CODE IS NULL";
						Object[][] vacancyAppointmentStatsDataObj = getSqlModel().getSingleResult(vacancyAppointmentStatsDataQuery);
						if (vacancyAppointmentStatsDataObj != null) {
							result = updateAppointmentDetails(bean, appointmentStatus, hiddenRequisitionCode);
						} else {
							result = "alreadyProcessed";
						}
					}
				}*/
			} else {
				result = insertAppointmentDetails(bean, empCode, appointmentStatus, hiddenRequisitionCode);
			}

			if (bean.getProbation().equals("true")) {
				bean.setProbation("Yes");
			} else {
				bean.setProbation("No");
			}

			if (bean.getBackgroundCheck().equals("true")) {
				bean.setBackgroundCheck("Yes");
			} else {
				bean.setBackgroundCheck("No");
			}

			if (bean.getAppointmentStatus().equals("D")) {
				bean.setAppointmentStatus("Due");
			} else if (bean.getAppointmentStatus().equals("I")) {
				bean.setAppointmentStatus("Issued");
			} else if (bean.getAppointmentStatus().equals("S")) {
				bean.setAppointmentStatus("Sent For Approval");
			} else if (bean.getAppointmentStatus().equals("OA")) {
				bean.setAppointmentStatus("Accepted");
			} else if (bean.getAppointmentStatus().equals("OR")) {
				bean.setAppointmentStatus("Rejected");
			} else if (bean.getAppointmentStatus().equals("C")) {
				bean.setAppointmentStatus("Canceled");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void updateVacancyStatistics(String requisitionCode, String status,
			String appointmentCode) {
		try {
			if (status.trim().equals("AppointmentAccepted")) {
				  String updateVacancyOfferstatusQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN = 'Y', HRMS_REC_VACANCIES_STATS.VAC_APPOINT_DATE = SYSDATE," +
				  										 " HRMS_REC_VACANCIES_STATS.VAC_APPOINT_STATUS_DATE = SYSDATE, HRMS_REC_VACANCIES_STATS.VAC_APPOINT_CODE = " + appointmentCode + 
				  									 	 " WHERE HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE = " + requisitionCode + 
				  										 " AND ROWNUM=1 AND HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN = 'N'";
				  
				getSqlModel().singleExecute(updateVacancyOfferstatusQuery);
			} else if (status.trim().equals("AppointmentRejectCancel")) {
				Object[][] checkData = getSqlModel().getSingleResult(" SELECT HRMS_REC_VACANCIES_STATS.VAC_CODE, HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE, HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN, HRMS_REC_VACANCIES_STATS.VAC_OFFER_CODE FROM HRMS_REC_VACANCIES_STATS " +
						 											 " WHERE HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE = "+ requisitionCode + " AND ROWNUM=1 AND HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN = 'N' AND HRMS_REC_VACANCIES_STATS.VAC_APPOINT_CODE IS NOT NULL");

				String updateVacancyOfferstatusQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN = 'N', " +
													   " HRMS_REC_VACANCIES_STATS.VAC_APPOINT_CODE = NULL, HRMS_REC_VACANCIES_STATS.VAC_APPOINT_DATE=NULL, HRMS_REC_VACANCIES_STATS.VAC_APPOINT_STATUS_DATE = SYSDATE, " +			
													   " HRMS_REC_VACANCIES_STATS.VAC_OFFER_DATE = NULL, HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN = 'N', HRMS_REC_VACANCIES_STATS.VAC_OFFER_CODE = NULL " +
													   " WHERE HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE = " + requisitionCode + " AND ROWNUM=1";

				if (checkData == null) {
					updateVacancyOfferstatusQuery += " AND HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN = 'Y'";
				} else {
					updateVacancyOfferstatusQuery += " AND HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN = 'N'";
				}
				  
				getSqlModel().singleExecute(updateVacancyOfferstatusQuery);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	public String insertAppointmentDetails(AppointmentDetails bean, String[] empCode, String appointmentStatus, String hiddenPublishCode, String hiddenRequisitionCode) {
	public String insertAppointmentDetails(AppointmentDetails bean, String[] empCode, String appointmentStatus, String hiddenRequisitionCode) {
		String flag = "";
		try {
			Object[][] appointmentDetails = new Object[1][32];
 
			appointmentDetails[0][0] = bean.getRequisitionCode().trim(); // requisition
			// code
			appointmentDetails[0][1] = bean.getCandidateCode().trim(); // candidate
			// code
			appointmentDetails[0][2] = bean.getAppointmentStatus().trim(); // appointment
			// status
			appointmentDetails[0][3] = bean.getJobDescription().trim(); // job
			// description
			// code
			appointmentDetails[0][4] = bean.getRolesResponsibility().trim(); // roles
			// n
			// responsibility
			appointmentDetails[0][5] = bean.getEmpTypeCode().trim(); // employee
			// type
			// code
			appointmentDetails[0][6] = bean.getHiringMgrCode().trim(); // hiring
			// manager
			// code
			appointmentDetails[0][7] = bean.getExpJoiningDate().trim(); // expected
			// joining
			// date
			appointmentDetails[0][8] = bean.getReportingToCode().trim(); // reporting
			// to
			// code
			appointmentDetails[0][9] = bean.getTestRequirements().trim(); // joining
			// formality
			appointmentDetails[0][10] = bean.getGradeCode().trim(); // grade
			// code
			appointmentDetails[0][11] = bean.getTemplateCode().trim(); // template
			// code

			if (bean.getProbation().equals("true")) {
				appointmentDetails[0][12] = "Y";
			} else {
				appointmentDetails[0][12] = "N";
			}

			// appointmentDetails [0][12] = bean.getProbation(); //probation
			// check box
			appointmentDetails[0][13] = bean.getMonths().trim(); // probation
			// months
			appointmentDetails[0][14] = bean.getAuthorityCode().trim(); // signing
			// authority
			// code
			appointmentDetails[0][15] = bean.getRemarks().trim(); // remarks
			appointmentDetails[0][16] = bean.getCandConstraints().trim();// candidate
			// constraints

			if (bean.getAppointmentStatus().equals("S")) {
				appointmentDetails[0][17] = "P"; // approval status
			} else {
				appointmentDetails[0][17] = ""; // approval status
			}

			if (bean.getBackgroundCheck().equals("true")) {
				appointmentDetails[0][18] = "Y";
			} else {
				appointmentDetails[0][18] = "N";
			}

			appointmentDetails[0][19] = bean.getJoiningDate().trim(); // joining
			// date
			appointmentDetails[0][20] = bean.getCurrentCtc(); // current ctc
			appointmentDetails[0][21] = bean.getNegotiatedCtc().trim(); // negotiated
			// ctc
			appointmentDetails[0][22] = bean.getOfferedCtc().trim(); // offered
			// ctc
			appointmentDetails[0][23] = bean.getAppointmentDate().trim(); // appointment
			// date
			appointmentDetails[0][24] = bean.getDivisionCode();
			appointmentDetails[0][25] = bean.getBranchCode();
			appointmentDetails[0][26] = bean.getDeptCode();
			appointmentDetails[0][27] = bean.getPositionCode();
			appointmentDetails[0][28] = bean.getReportingToAdminCode();
			appointmentDetails[0][29] = bean.getKeepInformedEmpId();// keep
																	// informed
																	// ID
			appointmentDetails[0][30] = bean.getAnnextureFileName();// Annexure
																	// attached
			
			
			// #################### BEGINS ############################# 
			Object[][] chkIfPublishData = null;
			String checkIfPublished = "SELECT PUB_CODE FROM HRMS_REC_VACPUB_HDR "
					+ "WHERE PUB_REQS_CODE = "
					+ bean.getRequisitionCode()
					+ " AND PUB_STATUS = 'P'";
			chkIfPublishData = getSqlModel().getSingleResult(
					checkIfPublished);

			if (chkIfPublishData != null && chkIfPublishData.length > 0) {

			} else {
				Object[][] reqVacDtlCode = null;
				String vacDtlQuery = "SELECT VACAN_DTL_CODE,VACAN_NUMBERS FROM HRMS_REC_REQS_VACDTL "
						+ " WHERE REQS_CODE = "
						+ bean.getRequisitionCode() + "";
				reqVacDtlCode = getSqlModel().getSingleResult(
						vacDtlQuery);

				if (reqVacDtlCode != null && reqVacDtlCode.length > 0) {
					// BEGINS -- Insert Records into HRMS_REC_VACANCIES_STATS
					Object[][] pubMaxCode = null;
					String pubCode = "SELECT NVL(MAX(PUB_CODE),0)+1 FROM HRMS_REC_VACPUB_HDR";
					pubMaxCode = getSqlModel().getSingleResult(pubCode);
					 
					/*Object[][] vacancyObj = null;
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
						vacancyObj[i][2] = checkNull(String.valueOf("O"));
						vacancyObj[i][3] = checkNull(String.valueOf("N"));
						vacancyObj[i][4] = checkNull(String.valueOf("N"));
						vacancyObj[i][5] = checkNull(String.valueOf(pubMaxCode[0][0]));
						
						insertVacancyDtlQuery = "INSERT INTO HRMS_REC_VACANCIES_STATS (VAC_CODE, VAC_REQS_CODE, VAC_STATUS, VAC_OFFER_GIVEN, VAC_APPOINT_GIVEN, VAC_PUBLISH_CODE) "
													+ " VALUES(?,?,?,?,?,?)";
					}
					getSqlModel().singleExecute(insertVacancyDtlQuery, vacancyObj);*/
					// ENDS -- Insert Records into HRMS_REC_VACANCIES_STATS
					
					for (int i = 0; i < reqVacDtlCode.length; i++) {
						String insertVacHdr = "INSERT INTO HRMS_REC_VACPUB_HDR (PUB_CODE,PUB_REQS_CODE,PUB_STATUS, PUB_DATE, "
								+ " PUB_VACAN_DTLCODE, PUB_EMPID,PUB_TO_CONS, PUB_TO_REF, PUB_TO_WEB,PUB_REC_TYPE_INT, PUB_REC_TYPE_EXT, PUB_TO_CANDJOB) VALUES((SELECT NVL(MAX(PUB_CODE),0)+1 FROM HRMS_REC_VACPUB_HDR), "
								+ " "
								+ bean.getRequisitionCode()
								+ ",'P',TO_DATE(SYSDATE,'DD-MM-YYYY'),"
								+ reqVacDtlCode[i][0]
								+ ","
								+ bean.getUserEmpId()
								+ ",'Y','Y','Y','Y','Y','Y')";

						boolean resultHdrInsert = getSqlModel()
								.singleExecute(insertVacHdr);

						if (resultHdrInsert) {
							String updReqVacDtlQuey = "UPDATE HRMS_REC_REQS_VACDTL SET VACAN_STATUS='P' WHERE VACAN_DTL_CODE = "
								+ reqVacDtlCode[i][0] + "";
							getSqlModel().singleExecute(updReqVacDtlQuey);
							
							String insertVacDtl = "INSERT INTO HRMS_REC_VACPUB_RECDTL (PUB_DTL_CODE, PUB_CODE, PUB_REC_EMPID, PUB_ASG_VAC, PUB_VAC_STATUS) "
									+ " VALUES((SELECT NVL(MAX(PUB_DTL_CODE),0)+1 FROM HRMS_REC_VACPUB_RECDTL),"
									+ " (SELECT MAX(PUB_CODE) FROM HRMS_REC_VACPUB_HDR),"
									+ bean.getRecruiterId()
									+ ","
									+ reqVacDtlCode[i][1] + ",'O')";
							getSqlModel().singleExecute(insertVacDtl);
						}
					}
				}
			}

			Object[][] checkResumeData = null;
			String checkResumeBank = "SELECT RESUME_REQS_CODE,RESUME_CAND_CODE FROM HRMS_REC_RESUME_BANK "
					+ " WHERE RESUME_REQS_CODE ="
					+ bean.getRequisitionCode()
					+ " AND RESUME_CAND_CODE ="
					+ bean.getCandidateCode() + " ";
			checkResumeData = getSqlModel().getSingleResult(
					checkResumeBank);

			if (checkResumeData != null && checkResumeData.length > 0) {

			} else {
				String insertResumeBank = "INSERT INTO HRMS_REC_RESUME_BANK (RESUME_CODE, RESUME_REQS_CODE, RESUME_CAND_CODE, RESUME_REC_EMPID, "
						+ " RESUME_APPR_EMPID, RESUME_STATUS) VALUES((SELECT NVL(MAX(RESUME_CODE),0)+1 FROM HRMS_REC_RESUME_BANK),"
						+ " "
						+ bean.getRequisitionCode()
						+ ","
						+ bean.getCandidateCode()
						+ ",'"
						+ bean.getRecruiterId()
						+ "', "
						+ " "
						+ bean.getUserEmpId() + ",'D')";
				getSqlModel().singleExecute(insertResumeBank);
			}
			// #################### ENDS #############################
			
			if(bean.getAppointmentStatus().equals("D")) {
				final String regNumberQuery = "SELECT MAX(APPOINT_CODE)+1 ||'/'|| TO_CHAR(SYSDATE,'dd-mm-yyyy'), MAX(APPOINT_CODE)+1, "
										    + " TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_REC_APPOINT";
				final Object[][] regNumberObj = this.getSqlModel().getSingleResult(regNumberQuery);
				if (regNumberObj != null && regNumberObj.length > 0) {
					try {
						final ApplCodeTemplateModel model = new ApplCodeTemplateModel();
						model.initiate(context, session);
						final String autoIncrementAppointmentCode = model.generateApplicationCode(String.valueOf(regNumberObj[0][1]), "RMS-AppointmentLetter", bean.getUserEmpId(), String.valueOf(regNumberObj[0][2]));
						bean.setAppointmentLetterRegCode(this.checkNull(autoIncrementAppointmentCode));
						model.terminate();
					} catch (final Exception e) {
						e.printStackTrace();
					}
				}

				appointmentDetails[0][31] = bean.getAppointmentLetterRegCode();

				boolean result = getSqlModel().singleExecute(getQuery(3), appointmentDetails);

				if (result) {
					String query = "SELECT MAX(APPOINT_CODE) FROM HRMS_REC_APPOINT";
					Object[][] appointCode = getSqlModel().getSingleResult(query);
					if (appointCode != null && appointCode.length != 0) {
						bean.setAppointmentCode(String.valueOf(appointCode[0][0]));
					}
					flag = "saved";
					saveKeepInformedList(empCode, bean.getAppointmentCode());
				} else {
					flag = "notSaved";
				}
					
			} else {
				// Check Whether all the requisitions related vacancies are Processed Or not --- START
				/*String vacancyStatsDataQuery = "SELECT VAC_APPOINT_CODE, VAC_APPOINT_GIVEN FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE = "
												+ hiddenRequisitionCode	+ " AND VAC_STATUS = 'O' AND VAC_APPOINT_GIVEN='N' AND VAC_APPOINT_CODE IS NULL";
				Object[][] vacancyStatsDataObj = getSqlModel().getSingleResult(vacancyStatsDataQuery);*/
				
				//BEGINS - Check for current requisition is Quick Requisition or NOT.
				/*String quickRequisitionQuery = "SELECT REQS_APPROVAL_STATUS FROM HRMS_REC_REQS_HDR WHERE REQS_CODE ="+bean.getRequisitionCode();
				Object[][] quickRequisitionObj = getSqlModel().getSingleResult(quickRequisitionQuery);
				String requisitionType = "";
				if (quickRequisitionObj != null && quickRequisitionObj.length > 0 ) {
					requisitionType = Utility.checkNull(String.valueOf(quickRequisitionObj[0][0]));
				}*/
				//ENDS - Check for current requisition is Quick Requisition or NOT.
				
				/**
				 * Below we are checking whether selected requisition is Quick OR not.
				 * If it is Quick Requisition then no need to check against No. Vacancies.
				 */
				
				//if( (requisitionType.trim()!="" && requisitionType.trim().equals("Q")) || (vacancyStatsDataObj != null && vacancyStatsDataObj.length>0)) {
			/*	if(vacancyStatsDataObj != null && vacancyStatsDataObj.length>0) {*/
					final String regNumberQuery = "SELECT MAX(APPOINT_CODE)+1 ||'/'|| TO_CHAR(SYSDATE,'dd-mm-yyyy'), MAX(APPOINT_CODE)+1, "
							+ " TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_REC_APPOINT";
					final Object[][] regNumberObj = this.getSqlModel()
							.getSingleResult(regNumberQuery);
					if (regNumberObj != null && regNumberObj.length > 0) {
						try {
							final ApplCodeTemplateModel model = new ApplCodeTemplateModel();
							model.initiate(context, session);
							final String autoIncrementAppointmentCode = model
									.generateApplicationCode(String
											.valueOf(regNumberObj[0][1]),
											"RMS-AppointmentLetter", bean
													.getUserEmpId(), String
													.valueOf(regNumberObj[0][2]));
							bean.setAppointmentLetterRegCode(this
									.checkNull(autoIncrementAppointmentCode));
							model.terminate();
						} catch (final Exception e) {
							e.printStackTrace();
						}
					}

					appointmentDetails[0][31] = bean.getAppointmentLetterRegCode();

					boolean result = getSqlModel().singleExecute(getQuery(3),
							appointmentDetails);

					if (result) {
						String query = "SELECT MAX(APPOINT_CODE) FROM HRMS_REC_APPOINT";
						Object[][] appointCode = getSqlModel().getSingleResult(
								query);
						if (appointCode != null && appointCode.length != 0) {
							bean.setAppointmentCode(String
									.valueOf(appointCode[0][0]));
						}
						
						/*String updateVacancyOfferstatusQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET VAC_APPOINT_DATE = SYSDATE, VAC_APPOINT_CODE = "+bean.getAppointmentCode()+" WHERE VAC_REQS_CODE = "+hiddenRequisitionCode
																+" AND ROWNUM=1 AND VAC_APPOINT_GIVEN = 'N' AND VAC_APPOINT_CODE IS NULL";
						getSqlModel().singleExecute(updateVacancyOfferstatusQuery);
						
						if (appointmentStatus.trim().equals("OA")) {
							//updateVacancyStatistics(hiddenPublishCode,	hiddenRequisitionCode, "AppointmentAccepted");
							updateVacancyStatistics(hiddenRequisitionCode, "AppointmentAccepted", bean.getAppointmentCode());
						} else if (appointmentStatus.equals("OR") || appointmentStatus.equals("C")) {
							//updateVacancyStatistics(hiddenPublishCode, hiddenRequisitionCode, "AppointmentRejectCancel");
							updateVacancyStatistics(hiddenRequisitionCode, "AppointmentRejectCancel", bean.getAppointmentCode());
						} else if(appointmentStatus.equals("I") || appointmentStatus.equals("S")){
							String updateVacancyAppointmentstatusQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET HRMS_REC_VACANCIES_STATS.VAC_APPOINT_STATUS_DATE = SYSDATE, HRMS_REC_VACANCIES_STATS.VAC_APPOINT_CODE = " + bean.getAppointmentCode() + 
																	" WHERE HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE = " + hiddenRequisitionCode + 
																	" AND ROWNUM=1 AND HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN = 'N'";
							getSqlModel().singleExecute(updateVacancyAppointmentstatusQuery);
						}*/
						flag = "saved";
						saveKeepInformedList(empCode, bean.getAppointmentCode());
					} else {
						flag = "notSaved";
					}
				/*}else {
					flag = "alreadyProcessed";
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void saveKeepInformedList(String empCode[], String appointmentCode) {
		try {
			String empId = "";
			if (empCode != null && empCode.length > 0) {
				for (int i = 0; i < empCode.length; i++) {
					if (i < empCode.length - 1) {
						empId += empCode[i] + ",";
					} else {
						empId = empId + empCode[i];
					}
				}
			}
			String updateQuery = "  UPDATE "
					+ " HRMS_REC_APPOINT SET APPOINT_KEEP_INFORMED=?  WHERE APPOINT_CODE=?  ";
			Object updateObj[][] = new Object[1][2];
			updateObj[0][0] = empId;
			updateObj[0][1] = appointmentCode;

			getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// public String updateAppointmentDetails(AppointmentDetails bean, String
	// appointmentStatus, String hiddenPublishCode, String
	// hiddenRequisitionCode) {
	public String updateAppointmentDetails(AppointmentDetails bean,
			String appointmentStatus, String hiddenRequisitionCode) {
		String flag = "";
		try {

			/*String getAppointmentDetailsQuery = "SELECT APPOINT_CODE, APPOINT_STATUS FROM HRMS_REC_APPOINT WHERE APPOINT_REQS_CODE ="
					+ bean.getRequisitionCode()
					+ " AND APPOINT_CAND_CODE="
					+ bean.getCandidateCode();
			Object[][] getAppointmentDetailsObj = getSqlModel()
					.getSingleResult(getAppointmentDetailsQuery);
			if (getAppointmentDetailsObj != null
					&& getAppointmentDetailsObj.length > 0) {
				if (bean.getAppointmentStatus().trim().equals("OA")) {
					if (String.valueOf(getAppointmentDetailsObj[0][1]).trim().equals("OA")) {

					} else {
						updateVacancyStatistics(hiddenRequisitionCode,
								"AppointmentAccepted",
								String.valueOf(getAppointmentDetailsObj[0][0]));
					}
				} else if (bean.getAppointmentStatus().trim().equals("OR")
						|| bean.getAppointmentStatus().trim().equals("C")) {
					 
					updateVacancyStatistics(hiddenRequisitionCode,
							"AppointmentRejectCancel", String
									.valueOf(getAppointmentDetailsObj[0][0]));
				}  else if(appointmentStatus.equals("I") || appointmentStatus.equals("S")){
					String updateVacancyAppointmentstatusQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET HRMS_REC_VACANCIES_STATS.VAC_APPOINT_STATUS_DATE = SYSDATE, HRMS_REC_VACANCIES_STATS.VAC_APPOINT_CODE = " + bean.getAppointmentCode() + 
																" WHERE HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE = " + hiddenRequisitionCode + 
																" AND ROWNUM=1 AND HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN = 'N'";
					getSqlModel().singleExecute(updateVacancyAppointmentstatusQuery);
				} 
			}*/

			Object[][] appointmentDetails = new Object[1][30];

			appointmentDetails[0][0] = bean.getAppointmentStatus();
			appointmentDetails[0][1] = bean.getJobDescription();
			appointmentDetails[0][2] = bean.getRolesResponsibility();
			appointmentDetails[0][3] = bean.getEmpTypeCode();
			appointmentDetails[0][4] = bean.getHiringMgrCode();
			appointmentDetails[0][5] = bean.getExpJoiningDate();
			appointmentDetails[0][6] = bean.getReportingToCode();
			appointmentDetails[0][7] = bean.getTestRequirements();
			appointmentDetails[0][8] = bean.getGradeCode();
			appointmentDetails[0][9] = bean.getTemplateCode();

			if (bean.getProbation().equals("true")) {
				appointmentDetails[0][10] = "Y";
			} else {
				appointmentDetails[0][10] = "N";
			}

			// appointmentDetails [0][10] = bean.getProbation();
			appointmentDetails[0][11] = bean.getMonths();
			appointmentDetails[0][12] = bean.getAuthorityCode();
			appointmentDetails[0][13] = bean.getRemarks();
			appointmentDetails[0][14] = bean.getCandConstraints();

			if (bean.getAppointmentStatus().equals("S")) {
				appointmentDetails[0][15] = "P"; // approval status
			} else {
				String appQuery = "SELECT nvl(APPOINT_APPR_STATUS, ' ') FROM HRMS_REC_APPOINT WHERE APPOINT_CODE="
						+ bean.getAppointmentCode();
				Object[][] appStatus = getSqlModel().getSingleResult(appQuery);
				if (appStatus != null) {
					if (!(appStatus[0][0].equals("null"))
							&& appStatus.length > 0) {
						appointmentDetails[0][15] = String
								.valueOf(appStatus[0][0]); // approval status
					} else {
						appointmentDetails[0][15] = "";
					}
				} else {
					appointmentDetails[0][15] = "";
				}
			}

			if (bean.getBackgroundCheck().equals("true")) {
				appointmentDetails[0][16] = "Y";
			} else {
				appointmentDetails[0][16] = "N";
			}

			// appointmentDetails [0][16] = bean.getBackgroundCheck();
			// //background check box
			appointmentDetails[0][17] = bean.getJoiningDate(); // joining date
			appointmentDetails[0][18] = bean.getCurrentCtc(); // current ctc
			appointmentDetails[0][19] = bean.getNegotiatedCtc(); // negotiated
			// ctc
			appointmentDetails[0][20] = bean.getOfferedCtc(); // offered ctc
			appointmentDetails[0][21] = bean.getAppointmentDate(); // offer
			// date
			appointmentDetails[0][22] = bean.getDivisionCode();
			appointmentDetails[0][23] = bean.getBranchCode();
			appointmentDetails[0][24] = bean.getDeptCode();
			appointmentDetails[0][25] = bean.getPositionCode();
			appointmentDetails[0][26] = bean.getReportingToAdminCode(); // Administrative
			// Reporting
			// To
			appointmentDetails[0][27] = bean.getKeepInformedEmpId();// keep
			// informed
			// to id
			// //offer
			// code
			appointmentDetails[0][28] = bean.getAnnextureFileName();// Attached
			// Annexure
			appointmentDetails[0][29] = bean.getAppointmentCode(); // Appointment
			// code

			String query = getQuery(4);
			// query += ", APPOINT_POSITION =
			// "+bean.getPositionCode()+",APPOINT_DIVISION =
			// "+bean.getDivisionCode()+",APPOINT_BRANCH =
			// "+bean.getBranchCode()+",APPOINT_DEPT = "+bean.getDeptCode()+"";
			if (bean.getAppointmentStatus().equals("OA")
					|| bean.getAppointmentStatus().equals("OR")
					|| bean.getAppointmentStatus().equals("C")) {
				query += ", APPOINT_ACCEPT_DATE = TO_DATE(TO_CHAR(SYSDATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') ";
			}
			query += " ,APPOINT_ADMIN_REPORTING_TO =?, APPOINT_KEEP_INFORMED=?, APPOINT_ATTACHED_ANNEXURE=? WHERE APPOINT_CODE = ?";

			boolean result = getSqlModel().singleExecute(query,
					appointmentDetails);

			if (result) {
				flag = "updated";
			} else {
				flag = "notUpdated";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void viewDetails(AppointmentDetails appointmentdetails,
			String tempcode) throws Exception {
		String query = "SELECT NVL(TEMPLATE_NAME,'') FROM HRMS_LETTERTEMPLATE_HDR WHERE TEMPLATE_ID="
				+ String.valueOf(tempcode);
		Object[][] templateName = getSqlModel().getSingleResult(query);
	}

	public void returnFromPostResume(AppointmentDetails bean, String candCode,
			Object[] formValues) {
		try {
			bean.setRequisitionCode(String.valueOf(formValues[1]));
			bean.setRequisitionName(String.valueOf(formValues[2]));
			bean.setPosition(String.valueOf(formValues[3]));
			bean.setHiringManager(String.valueOf(formValues[4]));
			bean.setDivision(String.valueOf(formValues[5]));
			bean.setBranch(String.valueOf(formValues[6]));
			bean.setDepartment(String.valueOf(formValues[7]));

			bean.setJoiningDate(String.valueOf(formValues[8]));
			bean.setAppointmentDate(String.valueOf(formValues[9]));
			bean.setCurrentCtc(String.valueOf(formValues[10]));
			bean.setNegotiatedCtc(String.valueOf(formValues[11]));

			bean.setJobCode(String.valueOf(formValues[12]));
			bean.setJobDescription(String.valueOf(formValues[13]));
			bean.setRolesResponsibility(String.valueOf(formValues[14]));
			bean.setEmpType(String.valueOf(formValues[15]));
			bean.setEmpTypeCode(String.valueOf(formValues[16]));
			bean.setHiringMgr(String.valueOf(formValues[17]));
			bean.setHiringMgrCode(String.valueOf(formValues[18]));
			bean.setExpJoiningDate(String.valueOf(formValues[19]));
			bean.setReportingTo(String.valueOf(formValues[20]));
			bean.setReportingToCode(String.valueOf(formValues[21]));
			bean.setTestReqCode(String.valueOf(formValues[22]));
			bean.setTestRequirements(String.valueOf(formValues[23]));
			bean.setGrade(String.valueOf(formValues[24]));
			bean.setGradeCode(String.valueOf(formValues[25]));
			bean.setTemplate(String.valueOf(formValues[26]));
			bean.setTemplateCode(String.valueOf(formValues[27]));
			bean.setProbation(String.valueOf(formValues[28]));
			bean.setSigningAuthority(String.valueOf(formValues[29]));
			bean.setAuthorityCode(String.valueOf(formValues[30]));
			bean.setBackgroundCheck(String.valueOf(formValues[31]));
			bean.setDesignation(String.valueOf(formValues[32]));
			bean.setAppointmentStatus(String.valueOf(formValues[33]));
			bean.setOfferedCtc(String.valueOf(formValues[34]));
			bean.setRemarks(String.valueOf(formValues[35]));
			bean.setCandConstraints(String.valueOf(formValues[36]));
			bean.setCandidateCode(String.valueOf(formValues[37]));
			bean.setCandidateName(String.valueOf(formValues[38]));
			bean.setMonths(String.valueOf(formValues[39]));

			bean.setPositionCode(String.valueOf(formValues[40]));
			bean.setDivisionCode(String.valueOf(formValues[41]));
			bean.setDeptCode(String.valueOf(formValues[42]));
			bean.setBranchCode(String.valueOf(formValues[43]));
			bean.setRecruiterId(String.valueOf(formValues[44]));
			bean.setRecruiterName(String.valueOf(formValues[45]));
			bean.setCandidateEmailID(String.valueOf(formValues[46]));

			if (candCode != null && !candCode.equals("")) {
				String candQry = "SELECT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME, CAND_EMAIL_ID FROM HRMS_REC_CAND_DATABANK "
						+ "WHERE CAND_CODE = " + candCode;

				Object[][] candObj = getSqlModel().getSingleResult(candQry);

				if (candObj != null && candObj.length != 0) {
					bean.setCandidateName(String.valueOf(candObj[0][0]).trim());
					bean.setCandidateCode(candCode);
					bean.setCandidateEmailID(String.valueOf(candObj[0][1])
							.trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void displayIteratorValueForKeepInformed(String[] srNo,
			String[] empCode, String[] empName,
			AppointmentDetails appointmentDetails) {
		try {
			ArrayList<AppointmentDetails> list = new ArrayList<AppointmentDetails>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					AppointmentDetails bean = new AppointmentDetails();
					bean.setKeepInformedEmpId(empCode[i]);
					bean.setKeepInformedEmpName(empName[i]);
					bean.setSerialNo(srNo[i]);// sr no
					list.add(bean);
				}
				appointmentDetails.setKeepInformedList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setKeepInformed(String[] srNo, String[] empCode,
			String[] empName, AppointmentDetails appointmentDetails) {
		try {
			AppointmentDetails bean = new AppointmentDetails();
			bean.setKeepInformedEmpId(appointmentDetails.getEmployeeId());
			bean.setKeepInformedEmpName(appointmentDetails.getEmployeeName());
			ArrayList<AppointmentDetails> list = displayNewValueForKeepInformed(
					srNo, empCode, empName, appointmentDetails);
			appointmentDetails.setSerialNo(String.valueOf(list.size() + 1));
			list.add(bean);
			appointmentDetails.setKeepInformedList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ArrayList<AppointmentDetails> displayNewValueForKeepInformed(
			String[] srNo, String[] empCode, String[] empName,
			AppointmentDetails appointmentDetails) {
		ArrayList<AppointmentDetails> list = null;
		try {
			list = new ArrayList<AppointmentDetails>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					AppointmentDetails bean = new AppointmentDetails();
					bean.setKeepInformedEmpId(empCode[i]);
					bean.setKeepInformedEmpName(empName[i]);
					bean.setSerialNo(srNo[i]);
					list.add(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void removeKeepInformedData(String[] serialNo, String[] empCode,
			String[] empName, AppointmentDetails appointmentDetails) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (serialNo != null) {
				for (int i = 0; i < serialNo.length; i++) {
					AppointmentDetails bean = new AppointmentDetails();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setKeepInformedEmpId(empCode[i]);
					bean.setKeepInformedEmpName(empName[i]);
					tableList.add(bean);

				}
				tableList.remove(Integer.parseInt(appointmentDetails
						.getCheckRemove()) - 1);

			}

			appointmentDetails.setKeepInformedList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getKeepInformedSavedRecord(AppointmentDetails appointmentDetails) {
		try {
			String selectQuery = " SELECT APPOINT_KEEP_INFORMED FROM "
					+ " HRMS_REC_APPOINT WHERE APPOINT_CODE = "
					+ appointmentDetails.getAppointmentCode();

			Object selectDataObj[][] = getSqlModel().getSingleResult(
					selectQuery);
			String str = "";
			String query = "";
			if (selectDataObj != null && selectDataObj.length > 0) {
				str = String.valueOf(selectDataObj[0][0]);

				if (str.length() > 0) {
					query = "  SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
							+ " FROM HRMS_EMP_OFFC "
							+ "  WHERE  EMP_ID IN("
							+ str + ")";
				}
				Object result[][] = getSqlModel().getSingleResult(query);

				ArrayList<AppointmentDetails> leaveList = new ArrayList<AppointmentDetails>();
				if (result != null) {

					for (int i = 0; i < result.length; i++) {
						AppointmentDetails bean = new AppointmentDetails();
						bean.setKeepInformedEmpId(String.valueOf(result[i][1]));
						bean.setKeepInformedEmpName(String
								.valueOf(result[i][0]));
						bean.setSerialNo(String.valueOf(i + 1));// sr no
						leaveList.add(bean);
					}
					appointmentDetails.setKeepInformedList(leaveList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTotalAmt(String candCode, String reqCode,
			String operationType, boolean esicFlag) {
		String totalAmt = "0";
		try {
			String totalCreditQry = " SELECT ROUND(NVL(SUM(OFFER_CREDIT_AMOUNT),0)) FROM HRMS_REC_OFFER_SALARY "
					+ " LEFT JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=OFFER_CREDIT_CODE) WHERE HRMS_REC_OFFER_SALARY.OFFER_CAND_CODE= "
					+ candCode
					+ " AND HRMS_REC_OFFER_SALARY.OFFER_REQS_CODE="
					+ reqCode + " AND CREDIT_PERIODICITY='M'";
			Object[][] totCredit = getSqlModel()
					.getSingleResult(totalCreditQry);
			String totalDebitQry = "";
			if (esicFlag) {
				totalDebitQry = "SELECT DISTINCT ROUND(OFFER_CREDIT_AMOUNT*.12) + ROUND(CASE WHEN SUM(OFFER_CREDIT_AMOUNT) <10000 THEN ((OFFER_CREDIT_AMOUNT/0.40)*0.0175) ELSE 0 END), "
						+ " (ROUND(OFFER_CREDIT_AMOUNT*.12) + ROUND(CASE WHEN SUM(OFFER_CREDIT_AMOUNT) <10000 THEN ((OFFER_CREDIT_AMOUNT/0.40)*0.0475) ELSE 0 END))"
						+ " FROM HRMS_REC_OFFER_SALARY "
						+ " WHERE HRMS_REC_OFFER_SALARY.OFFER_CAND_CODE="
						+ candCode
						+ " AND HRMS_REC_OFFER_SALARY.OFFER_REQS_CODE="
						+ reqCode
						+ " AND OFFER_CREDIT_CODE=1 GROUP BY OFFER_CREDIT_AMOUNT";
			} else {
				totalDebitQry = "SELECT DISTINCT ROUND(OFFER_CREDIT_AMOUNT*.12)  "
						+ " FROM HRMS_REC_OFFER_SALARY "
						+ " WHERE HRMS_REC_OFFER_SALARY.OFFER_CAND_CODE="
						+ candCode
						+ " AND HRMS_REC_OFFER_SALARY.OFFER_REQS_CODE="
						+ reqCode
						+ " AND OFFER_CREDIT_CODE=1 GROUP BY OFFER_CREDIT_AMOUNT";
			}
			Object[][] totDebit = getSqlModel().getSingleResult(totalDebitQry);
			if (operationType.equals("takeHome")) {
				if (totDebit != null && totDebit.length > 0) {
					totalAmt = String.valueOf(Double.parseDouble(String.valueOf(totCredit[0][0]))
								- Double.parseDouble(String.valueOf(totDebit[0][0])));
				} else {
					totalAmt = String.valueOf(Double.parseDouble(String.valueOf(totCredit[0][0])));
				}
				
			} else {
				if (totDebit != null && totDebit.length > 0) {
					totalAmt = String.valueOf(Double.parseDouble(String.valueOf(totCredit[0][0]))
								+ Double.parseDouble(String.valueOf(totDebit[0][1])));
				} else {
					totalAmt = String.valueOf(Double.parseDouble(String.valueOf(totCredit[0][0])));
				}
				
				
				if (operationType.equals("ctcPerYear")) {
					totalAmt = String.valueOf(Math.round(Double
							.parseDouble(totalAmt) * 12));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalAmt;

	}

	//public String onlineAcceptRejectAppointment(HttpServletRequest request,	String candCode, String appointmentCode, String status,	String publishCode, String requisitionCode, String remarks) {
	public String onlineAcceptRejectAppointment(HttpServletRequest request,	String candCode, String appointmentCode, String status,	String requisitionCode, String remarks) {
		String result = "";
		String res = "";
		try {
			System.out.println("candCode : " + candCode);
			System.out.println("appointmentCode : " + appointmentCode);
			System.out.println("Status : " + status);
			System.out.println("remarks : " + remarks);

			String query = " SELECT APPOINT_CAND_CODE, TRIM(APPOINT_STATUS) FROM HRMS_REC_APPOINT WHERE APPOINT_CODE="
					+ appointmentCode;
			Object candidateIdObj[][] = getSqlModel().getSingleResult(query);
			if (candidateIdObj != null && candidateIdObj.length > 0) {
				if (String.valueOf(candidateIdObj[0][0]).equals(candCode)
						&& String.valueOf(candidateIdObj[0][1]).equals("I")) {
					//res = updateOfferStatus(request, appointmentCode, status, remarks, publishCode, requisitionCode);
					res = updateOfferStatus(request, appointmentCode, status, remarks, requisitionCode);
					if (res.equals("accepted")) {
						result = "Appointment accepted successfully.";
						sendMailToRecruiterRegardingAppointmentStatus(request,
								candCode, appointmentCode);
					} else if (res.equals("rejected")) {
						result = "Appointment rejected successfully.";
						sendMailToRecruiterRegardingAppointmentStatus(request,
								candCode, appointmentCode);
					} else {
						result = "Error Occured.";
					}
				} else {
					result = "Appointment request has already been processed.";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private void sendMailToRecruiterRegardingAppointmentStatus(
			HttpServletRequest request, String candCode, String appointmentCode) {
		try {
			String recruitHeadQuery = "SELECT CASE WHEN TRIM(HRMS_REC_RESUME_BANK.RESUME_REC_EMPID) IS  NULL "
					+ " THEN TRIM(HRMS_REC_RESUME_BANK.RESUME_APPR_EMPID) "
					+ " ELSE  TRIM(HRMS_REC_RESUME_BANK.RESUME_REC_EMPID) END AS RECRUITER "
					+ " FROM HRMS_REC_RESUME_BANK "
					+ " WHERE RESUME_REQS_CODE = (SELECT APPOINT_REQS_CODE FROM HRMS_REC_APPOINT WHERE APPOINT_CODE = "
					+ appointmentCode + " ) AND RESUME_CAND_CODE = " + candCode;
			Object[][] recruitHeadObj = getSqlModel().getSingleResult(
					recruitHeadQuery);
			if (recruitHeadObj != null && recruitHeadObj.length > 0) {
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template
						.setEmailTemplate("RMS-APPOINTMENT MAIL TO RECRUITER REGARDING APPOINTMENT STATUS");
				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM
				templateQuery1.setParameter(1, candCode);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO
				templateQuery2.setParameter(1, checkNull(String
						.valueOf(recruitHeadObj[0][0])));

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, appointmentCode);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, appointmentCode);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, appointmentCode);

				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, appointmentCode);
				templateQuery6.setParameter(2, candCode);

				template.configMailAlert();
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//private String updateOfferStatus(HttpServletRequest request, String appointmentCode, String status, String remarks,	String publishCode, String requisitionCode) {
	private String updateOfferStatus(HttpServletRequest request, String appointmentCode, String status, String remarks, String requisitionCode) {
		String stringToReturn = "";
		boolean result = false;
		try {
			String getAppointmentDetailsQuery = "SELECT APPOINT_CODE, APPOINT_STATUS FROM HRMS_REC_APPOINT WHERE APPOINT_CODE="
					+ appointmentCode;
			Object[][] getAppointmentDetailsObj = getSqlModel()
					.getSingleResult(getAppointmentDetailsQuery);
			if (getAppointmentDetailsObj != null
					&& getAppointmentDetailsObj.length > 0) {
				if (status.trim().equals("OA")) {
					//updateVacancyStatistics(publishCode, requisitionCode, "OA");
					updateVacancyStatistics(requisitionCode, "AppointmentAccepted", appointmentCode);
				} else {
					updateVacancyStatistics(requisitionCode, "AppointmentRejectCancel", appointmentCode);
				}
			}

			String updateQuery = "UPDATE HRMS_REC_APPOINT SET APPOINT_STATUS = '"
					+ status
					+ "' , APPOINT_ACCEPT_DATE = SYSDATE , APPOINT_CAND_COMMENT = '"
					+ remarks + "' WHERE APPOINT_CODE = " + appointmentCode;
			result = getSqlModel().singleExecute(updateQuery);
			if (result) {
				if (status.equals("OA")) {
					stringToReturn = "accepted";
				} else {
					stringToReturn = "rejected";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringToReturn;
	}

}
