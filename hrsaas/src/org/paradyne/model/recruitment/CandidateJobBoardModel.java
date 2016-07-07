package org.paradyne.model.recruitment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.CandidateLoginBean;
import org.paradyne.bean.Recruitment.EmployeeRequisition;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;

/**
 * @author AA0517
 * 
 */
public class CandidateJobBoardModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.DyneActionSupport.class);

	public void getRecord(EmployeeRequisition manPowerReqs,
			HttpServletRequest request) {
		Object[][] data = null;
		/*
		 * Object[][]recConfig = null; double configMonths = 0; try { String
		 * query = "SELECT CONF_APPLY_DUR FROM HRMS_REC_CONF"; recConfig =
		 * getSqlModel().getSingleResult(query); } catch (Exception e) {
		 * logger.error("exception in recConfig query",e); } //end of catch
		 * if(recConfig !=null && recConfig.length > 0){ configMonths =
		 * Double.parseDouble(String.valueOf(recConfig[0][0])); } //end of if
		 */

		try {
			String query = "SELECT HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME,REQS_POSITION,RANK_NAME, "
					+ " REQS_BRANCH,CENTER_NAME,SUM(VACAN_NUMBERS), NVL(DEPT_NAME,' ') FROM HRMS_REC_REQS_HDR "
					+ " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE = HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH) "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ " INNER JOIN HRMS_DEPT ON (REQS_DEPT=DEPT_ID)"
					+ " WHERE REQS_STATUS = 'O' "
					// + " AND REQS_APPROVAL_STATUS = 'A' "
					+ " AND REQS_APPROVAL_STATUS IN ('A','Q') AND VACAN_STATUS = 'P' AND PUB_TO_CANDJOB = 'Y' "
					+ " GROUP BY HRMS_REC_REQS_HDR.REQS_CODE,REQS_NAME,REQS_POSITION,REQS_BRANCH,CENTER_NAME,RANK_NAME, DEPT_NAME";
			data = getSqlModel().getSingleResult(query);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			ArrayList<Object> list = new ArrayList<Object>();

			Object[][] maxCandDatabankCode = null;
			String maxCandCode = "  select CAND_DATABANK_CODE from  HRMS_REC_CAND_LOGIN where CAND_CODE="
					+ manPowerReqs.getCandidateUserEmpId();
			maxCandDatabankCode = getSqlModel().getSingleResult(maxCandCode);

			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					EmployeeRequisition bean = new EmployeeRequisition();
					bean.setApplyFlag("true");
					String candPostingQuery = "SELECT POST_REQS_CODE,TO_CHAR(POST_POSTING_DATE,'DD-MM-YYYY') FROM HRMS_REC_CAND_POSTING "
							+ " WHERE POST_REQS_CODE="
							+ data[i][0]
							+ " AND POST_CAND_CODE = "
							+ String.valueOf(maxCandDatabankCode[0][0])
							+ " "
							+ " AND POST_RESUME_SOURCE = 'O'";
					Object[][] postingData = getSqlModel().getSingleResult(
							candPostingQuery);

					if (postingData != null && postingData.length > 0) {
						bean.setApplyFlag("false");
						/*
						 * Object[][]canReApply = null;
						 * logger.info("postingData[0][1]=======>"+postingData[0][1]);
						 * String canApplyQuery = "SELECT
						 * MONTHS_BETWEEN(TO_DATE('"+sysDate+"','DD-MM-YYYY'),TO_DATE('"+postingData[0][1]+"','DD-MM-YYYY'))
						 * Month_diff FROM dual"; canReApply =
						 * getSqlModel().getSingleResult(canApplyQuery);
						 * logger.info("canReApply=======>"+canReApply[0][0]);
						 * 
						 * double appliedMonths =
						 * Double.parseDouble(String.valueOf(canReApply[0][0]));
						 * logger.info("appliedMonths=======>"+appliedMonths);
						 * logger.info("configMonths=======>"+configMonths);
						 * if(appliedMonths > configMonths ){ logger.info("in
						 * if=======>"); bean.setApplyFlag("true"); } //end of
						 * if else{ logger.info("in else=======>");
						 * bean.setApplyFlag("false"); } //end of else
						 */
					}
					bean.setReqCode(String.valueOf(data[i][0]));
					bean.setReqName(String.valueOf(data[i][1]));
					bean.setReqPositionCode(String.valueOf(data[i][2]));
					bean.setReqPositionName(String.valueOf(data[i][3]));
					bean.setReqBrnCode(String.valueOf(data[i][4]));
					bean.setReqBrn(String.valueOf(data[i][5]));
					bean.setNoOfVac(String.valueOf(data[i][6]));
					bean.setReqDept(String.valueOf(data[i][7]));
					list.add(bean);
				}
				manPowerReqs.setList(list);
			} else {
				manPowerReqs.setJobBoardNoRecord("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sourceOfResume(String requisitionCode,
			EmployeeRequisition manPowerReqs) {
		Object[][] advertisementName = null;
		try {
			String query = "SELECT ADVT_NAME,ADVT_DTL_CODE FROM HRMS_REC_ADVT_DTL "
					+ " INNER JOIN HRMS_REC_ADVT_HDR ON (HRMS_REC_ADVT_HDR.ADVT_CODE = HRMS_REC_ADVT_DTL.ADVT_CODE) "
					+ " WHERE HRMS_REC_ADVT_DTL.ADVT_CODE IN (SELECT ADVT_CODE FROM HRMS_REC_ADVT_HDR WHERE REQS_CODE = "
					+ requisitionCode + ")";
			advertisementName = getSqlModel().getSingleResult(query);

			HashMap statMap = new HashMap();
			for (int i = 0; i < advertisementName.length; i++) {
				statMap.put(advertisementName[i][1], advertisementName[i][0]);
			}
			statMap.put("S", "Select");
			statMap.put("0", "Others");
			manPowerReqs.setStatMap(statMap);
		} catch (Exception e) {
			logger.error("exception in advertisementName query", e);
		}
	}

	public String applyForJob(EmployeeRequisition manPowerReqs, String reqCode) {
		String result = "Please define recruitment setting first then apply.";
		try {
			Object[][] recConfig = null;
			recConfig = getRecConfig();// for recruitment configuration
			// parameters.
			if (recConfig != null && recConfig.length > 0) {
				if (String.valueOf(recConfig[0][0]).equals("Y")) {
					result = checkAndApplyForMultiplePosition(manPowerReqs,
							reqCode);
				} else {
					result = checkAndApplyForSinglePosition(manPowerReqs,
							reqCode);
				}

				sendMailToRecruiter(manPowerReqs, reqCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void sendMailToRecruiter(EmployeeRequisition manPowerReqs,
			String reqCode) {
		try {
			
			String query = "SELECT CAND_DATABANK_CODE FROM HRMS_REC_CAND_LOGIN WHERE CAND_CODE = "
				+ manPowerReqs.getCandidateUserEmpId() + "";
			Object[][]dataObj =getSqlModel().getSingleResult(query);
			
			String candidate =(dataObj!=null && dataObj.length>0)?String.valueOf(dataObj[0][0]):"";
			
			String updateReqCodetoDatabank = "UPDATE HRMS_REC_CAND_DATABANK SET CAND_REQUISITION_CODE =? "
					+ ", CAND_POSTRESUME_EMPID=?"
					+ " ,CAND_POSTRESUME_REQUISITIONID =? "
					+ " ,CAND_COMMENTS=? WHERE CAND_CODE =? ";

			getSqlModel().singleExecute(
					updateReqCodetoDatabank,
					new Object[][] { { reqCode,
						candidate, reqCode,
							manPowerReqs.getSourceComments().trim(),
							candidate } });

			String recruiterListQuery = "SELECT PUB_REC_EMPID AS RECRUITER_ID FROM  HRMS_REC_VACPUB_RECDTL "
					+ " WHERE PUB_CODE IN (SELECT PUB_CODE FROM HRMS_REC_VACPUB_HDR WHERE PUB_REQS_CODE = "
					+ reqCode + ")";
			Object[][] recruiterObj = getSqlModel().getSingleResult(
					recruiterListQuery);
			if (recruiterObj != null && recruiterObj.length > 0) {
				for (int i = 0; i < recruiterObj.length; i++) {
					EmailTemplateBody templateApp = new EmailTemplateBody();
					templateApp.initiate(context, session);
					templateApp
							.setEmailTemplate("RMS-CANDIDATE POST RESUME MAIL TO RECRUITER FROM CANDIDATE JOB BOARD");
					templateApp.getTemplateQueries();
					EmailTemplateQuery templateQueryApp1 = templateApp
							.getTemplateQuery(1); // FROM EMAIL
					templateQueryApp1.setParameter(1, manPowerReqs
							.getCandidateUserEmpId());

					EmailTemplateQuery templateQueryApp2 = templateApp
							.getTemplateQuery(2); // TO EMAIL
					templateQueryApp2.setParameter(1, String
							.valueOf(recruiterObj[i][0]));

					EmailTemplateQuery templateQueryApp3 = templateApp
							.getTemplateQuery(3);
					templateQueryApp3.setParameter(1, String
							.valueOf(recruiterObj[i][0]));

					EmailTemplateQuery templateQueryApp4 = templateApp
							.getTemplateQuery(4);
					templateQueryApp4.setParameter(1, reqCode);
					
					System.out.println("manPowerReqs.getCandidateUserEmpId() in send mail to recruiter       ::"+manPowerReqs.getCandidateUserEmpId());

					EmailTemplateQuery templateQueryApp5 = templateApp
							.getTemplateQuery(5);
					templateQueryApp5.setParameter(1, manPowerReqs.getCandidateUserEmpId());
					
					//previous reqcode

					templateApp.configMailAlert();
					templateApp.sendApplicationMail();
					templateApp.clearParameters();
					templateApp.terminate();
				}
			}

			String resetReqCodetoDatabank = "UPDATE HRMS_REC_CAND_DATABANK SET CAND_REQUISITION_CODE = '' "
					+ " WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId();
			getSqlModel().singleExecute(resetReqCodetoDatabank);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String checkAndApplyForMultiplePosition(
			EmployeeRequisition manPowerReqs, String reqCode) {
		String value = "";
		Object[][] checkAppliedOrNot = null;
		try {
			String appliedCheckQuery = "SELECT CAND_DATABANK_CODE FROM HRMS_REC_CAND_LOGIN WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId() + "";
			checkAppliedOrNot = getSqlModel()
					.getSingleResult(appliedCheckQuery);

			if (checkAppliedOrNot != null && checkAppliedOrNot.length > 0) {
				/**
				 * this is to check whether you have applied earlier or not
				 */
				if (String.valueOf(checkAppliedOrNot[0][0]).equals("null")) {
					value = applyForFirstTime(manPowerReqs, reqCode);
				} else {
					value = applyForOtherPosition(manPowerReqs, reqCode);
				}
			}
		} catch (Exception e) {
			logger.error("exception in appliedCheckQuery", e);
		}
		return value;
	}

	private String applyForOtherPosition(EmployeeRequisition manPowerReqs,
			String reqCode) {

		String value = "";
		String maxCodeOfCanDataBank = "";
		try {

			Object[][] maxCandDatabankCode = null;
			String maxCandCode = "  select CAND_DATABANK_CODE from  HRMS_REC_CAND_LOGIN where CAND_CODE="
					+ manPowerReqs.getCandidateUserEmpId();
			maxCandDatabankCode = getSqlModel().getSingleResult(maxCandCode);

			maxCodeOfCanDataBank = String.valueOf(maxCandDatabankCode[0][0]);

			Object[][] onlineDataBank = null;
			onlineDataBank = getOnlineDataBank(manPowerReqs
					.getCandidateUserEmpId());
			if (onlineDataBank != null && onlineDataBank.length > 0) {

				// max code of it
				String updateDatabank = "UPDATE HRMS_REC_CAND_DATABANK SET CAND_FIRST_NAME = ?, CAND_MID_NAME= ?, CAND_LAST_NAME= ?, CAND_PHOTO= ?, "
						+ " CAND_DOB= ?, CAND_GENDER= ?, CAND_MART_STATUS= ?, CAND_OFF_PHONE= ?, CAND_MOB_PHONE= ?, CAND_RES_PHONE= ?, CAND_EMAIL_ID= ?,  "
						+ " CAND_EMPLOYED= ?, CAND_RELOCATION= ?, CAND_OTHER_INFO= ?, CAND_RESUME= ?, CAND_EXP_YEAR= ?, CAND_EXP_MONTH= ?, CAND_SHORT_STATUS= ?, "
						+ " CAND_REF_TYPE= ?, CAND_CURR_CTC= ?, CAND_EXPC_CTC= ?, CAND_POSTING_DATE= ?, CAND_DOYOUKNOW= ?, CAND_REF_EMPID= ?,  "
						+ " CAND_PASSPORT_NO= ?, CAND_PAN_NO= ? WHERE  CAND_CODE ="
						+ maxCodeOfCanDataBank + "";
				getSqlModel().singleExecute(updateDatabank, onlineDataBank);
			}

			/*
			 * Object[][] onlineAddressDetail =
			 * getOnlineAddressDetail(manPowerReqs.getCandidateUserEmpId());
			 */
			// Address Details INSERT Begins
			String addressQuery = "SELECT CAND_ADD_TYPE, CAND_ADD, CAND_ADD_CITY, "
					+ " CAND_ADD_PINCODE, CAND_ADD_STATE, CAND_ADD_COUNTRY FROM HRMS_REC_CDOL_ADDRESSDTL  "
					+ " WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId()
					+ " AND CAND_ADD_TYPE IN ('C','P') ";
			Object[][] onlineAddressDetail = getSqlModel().getSingleResult(
					addressQuery);

			Object[][] onlineAddressDtlObj = null;
			String insertAddressDtlQuery = "";
			if (onlineAddressDetail != null && onlineAddressDetail.length > 0) {
				String deleteAddressDTLQuery = "DELETE FROM HRMS_REC_CD_ADDRESSDTL WHERE CAND_CODE = "
						+ maxCodeOfCanDataBank;
				getSqlModel().singleExecute(deleteAddressDTLQuery);

				String maxCodeQuery = " SELECT NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_ADDRESSDTL ";
				Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
						maxCodeQuery);
				int count = 0;
				if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
					count = Integer.parseInt(String
							.valueOf(maxCodeQueryObj[0][0]));
				}
				onlineAddressDtlObj = new Object[onlineAddressDetail.length][7];
				for (int i = 0; i < onlineAddressDetail.length; i++) {
					onlineAddressDtlObj[i][0] = count++;
					if (String.valueOf(onlineAddressDetail[i][0]).equals("C")) {
						onlineAddressDtlObj[i][1] = "C";
					} else {
						onlineAddressDtlObj[i][1] = "P";
					}
					// onlineAddressDtlObj[i][1] =
					// checkNull(String.valueOf(onlineAddressDetail[i][0]));
					onlineAddressDtlObj[i][2] = checkNull(String
							.valueOf(onlineAddressDetail[i][1]));
					onlineAddressDtlObj[i][3] = checkNull(String
							.valueOf(onlineAddressDetail[i][2]));
					onlineAddressDtlObj[i][4] = checkNull(String
							.valueOf(onlineAddressDetail[i][3]));
					onlineAddressDtlObj[i][5] = checkNull(String
							.valueOf(onlineAddressDetail[i][4]));
					onlineAddressDtlObj[i][6] = checkNull(String
							.valueOf(onlineAddressDetail[i][5]));

					// max code of it

					insertAddressDtlQuery = "INSERT INTO HRMS_REC_CD_ADDRESSDTL (CAND_DTL_CODE,CAND_CODE,CAND_ADD_TYPE,CAND_ADD,CAND_ADD_CITY, "
							+ " CAND_ADD_PINCODE,CAND_ADD_STATE,CAND_ADD_COUNTRY) VALUES(?, "
							+ maxCodeOfCanDataBank + ",?,?,?,?,?,?)";
				}
				getSqlModel().singleExecute(insertAddressDtlQuery,
						onlineAddressDtlObj);
			}
			// Address Details INSERT Ends

			/*
			 * Object[][] onlineExpDetail =
			 * getOnlineExpDetail(manPowerReqs.getCandidateUserEmpId());
			 */
			// Experience Details INSERT Begins
			String expQuery = "SELECT CAND_EMPLOYER_NAME, CAND_EMP_PORF, TO_CHAR(CAND_JOIN_DATE,'DD-MM-YYYY'), TO_CHAR(CAND_RELV_DATE,'DD-MM-YYYY'), "
					+ " CAND_JOB_DESC, CAND_SPEC_ACHV, CAND_INDUS_TYPE, CAND_CTC "
					+ " FROM HRMS_REC_CDOL_EXPDTL WHERE CAND_CODE ="
					+ manPowerReqs.getCandidateUserEmpId();
			Object[][] onlineExpDetail = getSqlModel()
					.getSingleResult(expQuery);

			Object[][] onlineExperienceDtlObj = null;
			String insertExperienceDtlQuery = "";
			if (onlineExpDetail != null && onlineExpDetail.length > 0) {
				String deleteExpDTLQuery = "DELETE FROM HRMS_REC_CD_EXPDTL WHERE CAND_CODE = "
						+ maxCodeOfCanDataBank;
				getSqlModel().singleExecute(deleteExpDTLQuery);

				String maxCodeQuery = " SELECT NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_EXPDTL ";
				Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
						maxCodeQuery);
				int count = 0;
				if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
					count = Integer.parseInt(String
							.valueOf(maxCodeQueryObj[0][0]));
				}
				onlineExperienceDtlObj = new Object[onlineExpDetail.length][9];

				for (int i = 0; i < onlineExpDetail.length; i++) {
					onlineExperienceDtlObj[i][0] = count++;
					onlineExperienceDtlObj[i][1] = checkNull(String
							.valueOf(onlineExpDetail[i][0]));
					onlineExperienceDtlObj[i][2] = checkNull(String
							.valueOf(onlineExpDetail[i][1]));
					onlineExperienceDtlObj[i][3] = checkNull(String
							.valueOf(onlineExpDetail[i][2]));
					onlineExperienceDtlObj[i][4] = checkNull(String
							.valueOf(onlineExpDetail[i][3]));
					onlineExperienceDtlObj[i][5] = checkNull(String
							.valueOf(onlineExpDetail[i][4]));
					onlineExperienceDtlObj[i][6] = checkNull(String
							.valueOf(onlineExpDetail[i][5]));
					onlineExperienceDtlObj[i][7] = checkNull(String
							.valueOf(onlineExpDetail[i][6]));
					onlineExperienceDtlObj[i][8] = checkNull(String
							.valueOf(onlineExpDetail[i][7]));

					// max code of it

					insertExperienceDtlQuery = "INSERT INTO  HRMS_REC_CD_EXPDTL (CAND_DTL_CODE, CAND_CODE, CAND_EMPLOYER_NAME, CAND_EMP_PORF, CAND_JOIN_DATE, "
							+ " CAND_RELV_DATE, CAND_JOB_DESC,CAND_SPEC_ACHV, CAND_INDUS_TYPE, CAND_CTC) "
							+ " VALUES(?,"
							+ maxCodeOfCanDataBank
							+ ",?,?,TO_DATE(?, 'DD-MM-YYYY'),TO_DATE(?, 'DD-MM-YYYY'),?,?,?,?)";
				}
				getSqlModel().singleExecute(insertExperienceDtlQuery,
						onlineExperienceDtlObj);
			}
			// Experience Details INSERT Ends

			/*
			 * Object[][] onlineFuncDtl =
			 * getOnlineFuncDtl(manPowerReqs.getCandidateUserEmpId());
			 */
			// Functional Details INSERT Begins
			String funcQuery = "SELECT CAND_FUNC_CODE,CAND_EXP FROM HRMS_REC_CDOL_FUNCDTL WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId();
			Object[][] onlineFuncDtl = getSqlModel().getSingleResult(funcQuery);

			Object[][] onlineFuncDtlObj = null;
			String insertFuncDtlQuery = "";
			if (onlineFuncDtl != null && onlineFuncDtl.length > 0) {
				String deleteSkillFunDTLQuery = "DELETE FROM HRMS_REC_CD_SKILLFUNCDTL WHERE CAND_FIELD_TYPE = 'F' AND CAND_CODE = "
						+ maxCodeOfCanDataBank;
				getSqlModel().singleExecute(deleteSkillFunDTLQuery);

				String maxCodeQuery = " SELECT NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_SKILLFUNCDTL ";
				Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
						maxCodeQuery);
				int count = 0;
				if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
					count = Integer.parseInt(String
							.valueOf(maxCodeQueryObj[0][0]));
				}
				onlineFuncDtlObj = new Object[onlineFuncDtl.length][3];
				for (int i = 0; i < onlineFuncDtl.length; i++) {
					onlineFuncDtlObj[i][0] = count++;
					onlineFuncDtlObj[i][1] = checkNull(String
							.valueOf(onlineFuncDtl[i][0]));
					onlineFuncDtlObj[i][2] = checkNull(String
							.valueOf(onlineFuncDtl[i][1]));

					// max code of it

					insertFuncDtlQuery = "INSERT INTO HRMS_REC_CD_SKILLFUNCDTL (CAND_DTL_CODE, CAND_CODE, CAND_FIELD_TYPE, CAND_SF_CODE, CAND_EXP) "
							+ " VALUES(?," + maxCodeOfCanDataBank + ",'F',?,?)";
				}
				getSqlModel().singleExecute(insertFuncDtlQuery,
						onlineFuncDtlObj);
			}
			// Functional Details INSERT Ends

			/*
			 * Object[][] onlineSkillDetail =
			 * getOnlineSkillDetail(manPowerReqs.getCandidateUserEmpId());
			 */
			// SKILL Details INSERT Begins
			String skillQuery = "SELECT CAND_SKILL_CODE,CAND_EXP FROM HRMS_REC_CDOL_SKILLDTL WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId();
			Object[][] onlineSkillDetail = getSqlModel().getSingleResult(
					skillQuery);

			Object onlineSkillDetailObj[][] = null;
			String insertSkillDetailQuery = "";
			if (onlineSkillDetail != null && onlineSkillDetail.length > 0) {
				String deleteSkillDTLQuery = "DELETE FROM HRMS_REC_CD_SKILLFUNCDTL WHERE CAND_FIELD_TYPE = 'S' AND CAND_CODE = "
						+ maxCodeOfCanDataBank;
				getSqlModel().singleExecute(deleteSkillDTLQuery);

				String maxCodeQuery = " SELECT NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_SKILLFUNCDTL ";
				Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
						maxCodeQuery);
				int count = 0;
				if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
					count = Integer.parseInt(String
							.valueOf(maxCodeQueryObj[0][0]));
				}
				onlineSkillDetailObj = new Object[onlineSkillDetail.length][3];

				for (int i = 0; i < onlineSkillDetail.length; i++) {
					onlineSkillDetailObj[i][0] = count++;
					onlineSkillDetailObj[i][1] = checkNull(String
							.valueOf(onlineSkillDetail[i][0]));
					onlineSkillDetailObj[i][2] = checkNull(String
							.valueOf(onlineSkillDetail[i][1]));

					// max code of it

					insertSkillDetailQuery = "INSERT INTO HRMS_REC_CD_SKILLFUNCDTL (CAND_DTL_CODE, CAND_CODE, CAND_FIELD_TYPE, CAND_SF_CODE, CAND_EXP) "
							+ " VALUES(?," + maxCodeOfCanDataBank + ",'S',?,?)";
				}
				getSqlModel().singleExecute(insertSkillDetailQuery,
						onlineSkillDetailObj);
			}
			// SKILL Details INSERT Ends

			// Reference Details INSERT Begins
			String refQuery = "SELECT CAND_REFNAME, CAND_PROFES, CAND_CONTACT, CAND_COMMENTS FROM HRMS_REC_CDOL_REFDTL WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId() + "";
			Object[][] onlineRefDetail = getSqlModel()
					.getSingleResult(refQuery);

			Object insertRefObj[][] = null;
			String insertRefDetailQuery = "";
			if (onlineRefDetail != null && onlineRefDetail.length > 0) {
				String deleteReferenceDTLQuery = "DELETE FROM HRMS_REC_CD_REFDTL WHERE CAND_CODE = "
						+ maxCodeOfCanDataBank;
				getSqlModel().singleExecute(deleteReferenceDTLQuery);

				String maxCodeQuery = " SELECT  NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_REFDTL ";
				Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
						maxCodeQuery);
				int count = 0;
				if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
					count = Integer.parseInt(String
							.valueOf(maxCodeQueryObj[0][0]));
				}
				insertRefObj = new Object[onlineRefDetail.length][5];
				for (int i = 0; i < onlineRefDetail.length; i++) {
					insertRefObj[i][0] = count++;
					insertRefObj[i][1] = checkNull(String
							.valueOf(onlineRefDetail[i][0]));
					insertRefObj[i][2] = checkNull(String
							.valueOf(onlineRefDetail[i][1]));
					insertRefObj[i][3] = checkNull(String
							.valueOf(onlineRefDetail[i][2]));
					insertRefObj[i][4] = checkNull(String
							.valueOf(onlineRefDetail[i][3]));

					// max code of it

					insertRefDetailQuery = "INSERT INTO HRMS_REC_CD_REFDTL (CAND_DTL_CODE, CAND_CODE, CAND_REFNAME, CAND_PROFES, CAND_CONTACT, CAND_COMMENTS) "
							+ " VALUES(?," + maxCodeOfCanDataBank + ",?,?,?,?)";
				}
				getSqlModel().singleExecute(insertRefDetailQuery, insertRefObj);
			}
			// Reference Details INSERT Ends

			// Qualification Details INSERT Begins
			/*
			 * Object[][] onlineQualificationDtl =
			 * getOnlineQualificationDtl(manPowerReqs.getCandidateUserEmpId());
			 */
			String QuaQuery = "SELECT CAND_QUA_CODE, CAND_SPEC_CODE, CAND_ESTB_NAME, "
					+ " CAND_YEAR_PASS, CAND_PERC_MARKS FROM HRMS_REC_CDOL_QUADTL WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId();
			Object[][] onlineQualificationDtl = getSqlModel().getSingleResult(
					QuaQuery);

			Object onlineQualificationDtlObj[][] = null;
			String insertQualificationDetailsQuery = "";
			if (onlineQualificationDtl != null
					&& onlineQualificationDtl.length > 0) {
				String deleteQualDTLQuery = "DELETE FROM HRMS_REC_CD_QUADTL WHERE CAND_CODE = "
						+ maxCodeOfCanDataBank;
				getSqlModel().singleExecute(deleteQualDTLQuery);

				// max code of it

				String maxCodeQuery = " SELECT  NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_QUADTL ";
				Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
						maxCodeQuery);
				int count = 0;
				if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
					count = Integer.parseInt(String
							.valueOf(maxCodeQueryObj[0][0]));
				}

				onlineQualificationDtlObj = new Object[onlineQualificationDtl.length][6];
				for (int i = 0; i < onlineQualificationDtl.length; i++) {
					onlineQualificationDtlObj[i][0] = count++;
					onlineQualificationDtlObj[i][1] = checkNull(String
							.valueOf(onlineQualificationDtl[i][0]));
					onlineQualificationDtlObj[i][2] = checkNull(String
							.valueOf(onlineQualificationDtl[i][1]));
					onlineQualificationDtlObj[i][3] = checkNull(String
							.valueOf(onlineQualificationDtl[i][2]));
					onlineQualificationDtlObj[i][4] = checkNull(String
							.valueOf(onlineQualificationDtl[i][3]));
					onlineQualificationDtlObj[i][5] = checkNull(String
							.valueOf(onlineQualificationDtl[i][4]));
					insertQualificationDetailsQuery = "INSERT INTO HRMS_REC_CD_QUADTL (CAND_DTL_CODE, CAND_CODE,CAND_QUA_CODE, CAND_SPEC_CODE, CAND_ESTB_NAME, "
							+ " CAND_YEAR_PASS, CAND_PERC_MARKS) "
							+ " VALUES(?,"
							+ maxCodeOfCanDataBank
							+ ",?,?,?,?,?)";
				}
				getSqlModel().singleExecute(insertQualificationDetailsQuery,
						onlineQualificationDtlObj);
			}

			String insertCandidatePosting = "INSERT INTO HRMS_REC_CAND_POSTING (POST_CODE, POST_REQS_CODE, POST_CAND_CODE, POST_RESUME_SOURCE, POST_POSTING_DATE) "
					+ " VALUES ((SELECT  NVL(MAX(POST_CODE),0)+1 FROM HRMS_REC_CAND_POSTING),"
					+ reqCode
					+ ","
					+ maxCodeOfCanDataBank
					+ ",'O',(SELECT TO_DATE(SYSDATE,'DD-MM-YYYY')FROM DUAL))";
			getSqlModel().singleExecute(insertCandidatePosting);

			String updateAdvtResponses = "UPDATE HRMS_REC_ADVT_DTL SET ADVT_RESPONSES = (SELECT  NVL(MAX(ADVT_RESPONSES),0)+1 FROM HRMS_REC_ADVT_DTL WHERE ADVT_DTL_CODE = "
					+ manPowerReqs.getAdvtName()
					+ ")"
					+ " WHERE ADVT_DTL_CODE = "
					+ manPowerReqs.getAdvtName()
					+ "";
			getSqlModel().singleExecute(updateAdvtResponses);
			value = "You have successfully applied for this position";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	private String applyForOtherPosition_OLD(EmployeeRequisition manPowerReqs,
			String reqCode) {
		String value = "";
		try {
			Object[][] onlineDataBank = null;
			onlineDataBank = getOnlineDataBank(manPowerReqs
					.getCandidateUserEmpId());
			if (onlineDataBank != null && onlineDataBank.length > 0) {
				String updateDatabank = "UPDATE HRMS_REC_CAND_DATABANK SET CAND_FIRST_NAME = ?, CAND_MID_NAME= ?, CAND_LAST_NAME= ?, CAND_PHOTO= ?, "
						+ " CAND_DOB= ?, CAND_GENDER= ?, CAND_MART_STATUS= ?, CAND_OFF_PHONE= ?, CAND_MOB_PHONE= ?, CAND_RES_PHONE= ?, CAND_EMAIL_ID= ?,  "
						+ " CAND_EMPLOYED= ?, CAND_RELOCATION= ?, CAND_OTHER_INFO= ?, CAND_RESUME= ?, CAND_EXP_YEAR= ?, CAND_EXP_MONTH= ?, CAND_SHORT_STATUS= ?, "
						+ " CAND_REF_TYPE= ?, CAND_CURR_CTC= ?, CAND_EXPC_CTC= ?, CAND_POSTING_DATE= ?, CAND_DOYOUKNOW= ?, CAND_REF_EMPID= ?,  "
						+ " CAND_PASSPORT_NO= ?, CAND_PAN_NO= ? WHERE  CAND_CODE ="
						+ manPowerReqs.getCandidateUserEmpId() + "";
				getSqlModel().singleExecute(updateDatabank, onlineDataBank);
			}

			// Address Details UPDATE Begins
			/*
			 * Object[][] onlineAddressDetail =
			 * getOnlineAddressDetail(manPowerReqs.getCandidateUserEmpId());
			 */
			String addressQuery = "SELECT CAND_ADD_TYPE, CAND_ADD, CAND_ADD_CITY, "
					+ " CAND_ADD_PINCODE, CAND_ADD_STATE, CAND_ADD_COUNTRY FROM HRMS_REC_CDOL_ADDRESSDTL  "
					+ " WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId()
					+ " AND CAND_ADD_TYPE = 'C' ";
			Object[][] onlineAddressDetail = getSqlModel().getSingleResult(
					addressQuery);
			Object[][] updateonlineAddressDtlObj = null;
			String updateAddressDetailQuery = "";
			if (onlineAddressDetail != null && onlineAddressDetail.length > 0) {
				updateonlineAddressDtlObj = new Object[onlineAddressDetail.length][6];
				for (int i = 0; i < onlineAddressDetail.length; i++) {
					updateonlineAddressDtlObj[i][0] = checkNull(String
							.valueOf(onlineAddressDetail[i][0]));
					updateonlineAddressDtlObj[i][1] = checkNull(String
							.valueOf(onlineAddressDetail[i][1]));
					updateonlineAddressDtlObj[i][2] = checkNull(String
							.valueOf(onlineAddressDetail[i][2]));
					updateonlineAddressDtlObj[i][3] = checkNull(String
							.valueOf(onlineAddressDetail[i][3]));
					updateonlineAddressDtlObj[i][4] = checkNull(String
							.valueOf(onlineAddressDetail[i][4]));
					updateonlineAddressDtlObj[i][5] = checkNull(String
							.valueOf(onlineAddressDetail[i][5]));

					updateAddressDetailQuery = "UPDATE HRMS_REC_CD_ADDRESSDTL SET CAND_ADD_TYPE=?, CAND_ADD=?, CAND_ADD_CITY=?, "
							+ " CAND_ADD_PINCODE=?, CAND_ADD_STATE=?, CAND_ADD_COUNTRY=? WHERE CAND_CODE = "
							+ manPowerReqs.getCandidateUserEmpId() + "";
				}
				getSqlModel().singleExecute(updateAddressDetailQuery,
						updateonlineAddressDtlObj);
			}
			// Address Details UPDATE Ends

			// Experience Details UPDATE Begins
			/*
			 * Object[][] onlineExpDetail =
			 * getOnlineExpDetail(manPowerReqs.getCandidateUserEmpId());
			 */
			String expQuery = "SELECT CAND_EMPLOYER_NAME, CAND_EMP_PORF, TO_CHAR(CAND_JOIN_DATE,'DD-MM-YYYY'), TO_CHAR(CAND_RELV_DATE,'DD-MM-YYYY'), "
					+ " CAND_JOB_DESC, CAND_SPEC_ACHV, CAND_INDUS_TYPE, CAND_CTC "
					+ " FROM HRMS_REC_CDOL_EXPDTL WHERE CAND_CODE ="
					+ manPowerReqs.getCandidateUserEmpId();
			Object[][] onlineExpDetail = getSqlModel()
					.getSingleResult(expQuery);
			System.out.println("onlineExpDetail >>>>>>>>>>>>>>>>"
					+ onlineExpDetail.length);
			Object[][] updateonlineExperienceDtlObj = null;
			String updateExperienceDtlQuery = "";
			if (onlineExpDetail != null && onlineExpDetail.length > 0) {
				for (int i = 0; i < onlineExpDetail.length; i++) {
					for (int j = 0; j < onlineExpDetail[0].length; j++) {
						System.out
								.println("onlineExpDetail[i][j]-----------------------"
										+ onlineExpDetail[i][j]);
					}
				}

				updateonlineExperienceDtlObj = new Object[onlineExpDetail.length][8];
				for (int i = 0; i < onlineExpDetail.length; i++) {
					System.out.println("LOOP >>>>>>>>>>>>>>>>" + i);
					updateonlineExperienceDtlObj[i][0] = checkNull(String
							.valueOf(onlineExpDetail[i][0]));
					updateonlineExperienceDtlObj[i][1] = checkNull(String
							.valueOf(onlineExpDetail[i][1]));
					updateonlineExperienceDtlObj[i][2] = checkNull(String
							.valueOf(onlineExpDetail[i][2]));
					updateonlineExperienceDtlObj[i][3] = checkNull(String
							.valueOf(onlineExpDetail[i][3]));
					updateonlineExperienceDtlObj[i][4] = checkNull(String
							.valueOf(onlineExpDetail[i][4]));
					updateonlineExperienceDtlObj[i][5] = checkNull(String
							.valueOf(onlineExpDetail[i][5]));
					updateonlineExperienceDtlObj[i][6] = checkNull(String
							.valueOf(onlineExpDetail[i][6]));
					updateonlineExperienceDtlObj[i][7] = checkNull(String
							.valueOf(onlineExpDetail[i][7]));

					updateExperienceDtlQuery = "UPDATE HRMS_REC_CD_EXPDTL SET CAND_EMPLOYER_NAME=?, CAND_EMP_PORF=?, CAND_JOIN_DATE= TO_DATE(?,'DD-MM-YYYY'), CAND_RELV_DATE = TO_DATE(?,'DD-MM-YYYY'), "
							+ " CAND_JOB_DESC=?, CAND_SPEC_ACHV=?, CAND_INDUS_TYPE=?, CAND_CTC=? WHERE  CAND_CODE ="
							+ manPowerReqs.getCandidateUserEmpId();
				}
				getSqlModel().singleExecute(updateExperienceDtlQuery,
						updateonlineExperienceDtlObj);
			}
			// Experience Details UPDATE Ends

			// Skill Function Details UPDATE Begins
			/*
			 * Object[][] onlineFuncDtl =
			 * getOnlineFuncDtl(manPowerReqs.getCandidateUserEmpId());
			 */
			String funcQuery = "SELECT CAND_FUNC_CODE,CAND_EXP FROM HRMS_REC_CDOL_FUNCDTL WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId();
			Object[][] onlineFuncDtl = getSqlModel().getSingleResult(funcQuery);

			Object[][] updateOnlineFuncDtlObj = null;
			String updateFuncDtlQuery = "";
			if (onlineFuncDtl != null && onlineFuncDtl.length > 0) {
				updateOnlineFuncDtlObj = new Object[onlineFuncDtl.length][2];
				for (int i = 0; i < onlineFuncDtl.length; i++) {
					updateOnlineFuncDtlObj[i][0] = checkNull(String
							.valueOf(onlineFuncDtl[i][0]));
					updateOnlineFuncDtlObj[i][1] = checkNull(String
							.valueOf(onlineFuncDtl[i][1]));

					updateFuncDtlQuery = "UPDATE HRMS_REC_CD_SKILLFUNCDTL SET CAND_SF_CODE=?,CAND_EXP=? "
							+ " WHERE CAND_FIELD_TYPE = 'F' AND CAND_CODE = "
							+ manPowerReqs.getCandidateUserEmpId() + "";
				}
				getSqlModel().singleExecute(updateFuncDtlQuery,
						updateOnlineFuncDtlObj);
			}
			// Skill Function Details UPDATE Ends

			// Skill Details UPDATE Begins
			/*
			 * Object[][] onlineSkillDetail =
			 * getOnlineSkillDetail(manPowerReqs.getCandidateUserEmpId());
			 */
			String skillQuery = "SELECT CAND_SKILL_CODE,CAND_EXP FROM HRMS_REC_CDOL_SKILLDTL WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId();
			Object[][] onlineSkillDetail = getSqlModel().getSingleResult(
					skillQuery);
			Object updateOnlineSkillDetailObj[][] = null;
			String updateSkillDetailQuery = "";
			if (onlineSkillDetail != null && onlineSkillDetail.length > 0) {
				updateOnlineSkillDetailObj = new Object[onlineSkillDetail.length][2];
				for (int i = 0; i < onlineSkillDetail.length; i++) {
					updateOnlineSkillDetailObj[i][0] = checkNull(String
							.valueOf(onlineSkillDetail[i][0]));
					updateOnlineSkillDetailObj[i][1] = checkNull(String
							.valueOf(onlineSkillDetail[i][1]));
					updateSkillDetailQuery = "UPDATE HRMS_REC_CD_SKILLFUNCDTL SET CAND_SF_CODE=?,CAND_EXP=? "
							+ "WHERE CAND_FIELD_TYPE = 'S' AND CAND_CODE = "
							+ manPowerReqs.getCandidateUserEmpId() + "";
				}
				getSqlModel().singleExecute(updateSkillDetailQuery,
						updateOnlineSkillDetailObj);
			}
			// Skill Details UPDATE Begins

			// Reference Details UPDATE Begins
			/*
			 * Object[][] onlineRefDetail =
			 * getOnlineRefDetail(manPowerReqs.getCandidateUserEmpId());
			 */
			String refQuery = "SELECT CAND_REFNAME, CAND_PROFES, CAND_CONTACT, CAND_COMMENTS FROM HRMS_REC_CDOL_REFDTL WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId() + "";
			Object[][] onlineRefDetail = getSqlModel()
					.getSingleResult(refQuery);

			Object updateRefObj[][] = null;
			String updateRefDetailQuery = "";
			if (onlineRefDetail != null && onlineRefDetail.length > 0) {
				updateRefObj = new Object[onlineRefDetail.length][4];
				for (int i = 0; i < onlineRefDetail.length; i++) {
					updateRefObj[i][0] = checkNull(String
							.valueOf(onlineRefDetail[i][0]));
					updateRefObj[i][1] = checkNull(String
							.valueOf(onlineRefDetail[i][1]));
					updateRefObj[i][2] = checkNull(String
							.valueOf(onlineRefDetail[i][2]));
					updateRefObj[i][3] = checkNull(String
							.valueOf(onlineRefDetail[i][3]));
					updateRefDetailQuery = "UPDATE HRMS_REC_CD_REFDTL SET  CAND_REFNAME=?, CAND_PROFES=?, CAND_CONTACT=?, CAND_COMMENTS=? "
							+ " WHERE CAND_CODE = "
							+ manPowerReqs.getCandidateUserEmpId() + "";
				}
				getSqlModel().singleExecute(updateRefDetailQuery, updateRefObj);
			}
			// Reference Details UPDATE Ends

			// Qualification Details UPDATE Begins
			/*
			 * Object[][] onlineQualificationDtl =
			 * getOnlineQualificationDtl(manPowerReqs.getCandidateUserEmpId());
			 */
			String QuaQuery = "SELECT CAND_QUA_CODE, CAND_SPEC_CODE, CAND_ESTB_NAME, "
					+ " CAND_YEAR_PASS, CAND_PERC_MARKS FROM HRMS_REC_CDOL_QUADTL WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId();
			Object[][] onlineQualificationDtl = getSqlModel().getSingleResult(
					QuaQuery);

			Object updateQualificationDtlObj[][] = null;
			String updateQualificationDetailsQuery = "";
			if (onlineQualificationDtl != null
					&& onlineQualificationDtl.length > 0) {
				updateQualificationDtlObj = new Object[onlineQualificationDtl.length][5];
				for (int i = 0; i < onlineQualificationDtl.length; i++) {
					updateQualificationDtlObj[i][0] = checkNull(String
							.valueOf(onlineQualificationDtl[i][0]));
					updateQualificationDtlObj[i][1] = checkNull(String
							.valueOf(onlineQualificationDtl[i][1]));
					updateQualificationDtlObj[i][2] = checkNull(String
							.valueOf(onlineQualificationDtl[i][2]));
					updateQualificationDtlObj[i][3] = checkNull(String
							.valueOf(onlineQualificationDtl[i][3]));
					updateQualificationDtlObj[i][4] = checkNull(String
							.valueOf(onlineQualificationDtl[i][4]));
					updateQualificationDetailsQuery = "UPDATE HRMS_REC_CD_QUADTL SET CAND_QUA_CODE=?, CAND_SPEC_CODE=?, CAND_ESTB_NAME=?, "
							+ " CAND_YEAR_PASS=?, CAND_PERC_MARKS=? WHERE CAND_CODE = "
							+ manPowerReqs.getCandidateUserEmpId() + "";
				}
				getSqlModel().singleExecute(updateQualificationDetailsQuery,
						updateQualificationDtlObj);
			}
			// Qualification Details UPDATE Ends

			String insertCandidatePosting = "INSERT INTO HRMS_REC_CAND_POSTING (POST_CODE, POST_REQS_CODE, POST_CAND_CODE, POST_RESUME_SOURCE, POST_POSTING_DATE) "
					+ " VALUES ((SELECT  NVL(MAX(POST_CODE),0)+1 FROM HRMS_REC_CAND_POSTING),"
					+ reqCode
					+ ","
					+ manPowerReqs.getCandidateUserEmpId()
					+ ",'O',(SELECT TO_DATE(SYSDATE,'DD-MM-YYYY')FROM DUAL))";
			getSqlModel().singleExecute(insertCandidatePosting);
			String updateAdvtResponses = "UPDATE HRMS_REC_ADVT_DTL SET ADVT_RESPONSES = (SELECT  NVL(MAX(ADVT_RESPONSES),0)+1 FROM HRMS_REC_ADVT_DTL WHERE ADVT_DTL_CODE = "
					+ manPowerReqs.getAdvtName()
					+ ")"
					+ " WHERE ADVT_DTL_CODE = "
					+ manPowerReqs.getAdvtName()
					+ "";
			getSqlModel().singleExecute(updateAdvtResponses);
			value = "You have successfully applied for this position";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	private String checkAndApplyForSinglePosition(
			EmployeeRequisition manPowerReqs, String reqCode) {
		Object[][] postingData = null;
		String value = "";
		try {
			postingData = getPostingData(manPowerReqs);
			if (postingData != null && postingData.length > 0) {
				value = "You cannot apply for this job, as you have already applied for one position";
			} else {
				value = applyForFirstTime(manPowerReqs, reqCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	private String applyForFirstTime(EmployeeRequisition manPowerReqs,
			String reqCode) {
		String value = "";
		synchronized (this) {

			try {

				System.out
						.println("manPowerReqs.getCandidateUserEmpId()   >>>>>>>>>>>>>"
								+ manPowerReqs.getCandidateUserEmpId());
				Object[][] maxCandDatabankCode = null;
				String maxCandCode = " SELECT NVL(MAX(CAND_CODE),0)+1   FROM HRMS_REC_CAND_DATABANK ";
				maxCandDatabankCode = getSqlModel()
						.getSingleResult(maxCandCode);

				/*
				 * Object[][] onlineDataBank =
				 * getOnlineDataBank(manPowerReqs.getCandidateUserEmpId());
				 */
				// Candidate Details INSERT Begins
				String query = "SELECT CAND_FIRST_NAME, CAND_MID_NAME, CAND_LAST_NAME, CAND_PHOTO, "
						+ " CAND_DOB, CAND_GENDER, CAND_MART_STATUS, CAND_OFF_PHONE, CAND_MOB_PHONE, CAND_RES_PHONE, CAND_EMAIL_ID, "
						+ " CAND_EMPLOYED, CAND_RELOCATION, CAND_OTHER_INFO, CAND_RESUME, CAND_EXP_YEAR, CAND_EXP_MONTH, CAND_SHORT_STATUS, "
						+ " CAND_REF_TYPE, CAND_CURR_CTC, CAND_EXPC_CTC, CAND_POSTING_DATE, CAND_DOYOUKNOW, CAND_REF_EMPID, "
						+ " CAND_PASSPORT_NO, CAND_PAN_NO FROM HRMS_REC_CDOL_DATABANK WHERE CAND_CODE = "
						+ manPowerReqs.getCandidateUserEmpId();
				Object[][] onlineDataBank = getSqlModel()
						.getSingleResult(query);

				String insertonlineDataBankDtlQuery = "";
				if (onlineDataBank != null && onlineDataBank.length > 0) {
					insertonlineDataBankDtlQuery = "INSERT INTO HRMS_REC_CAND_DATABANK (CAND_CODE, CAND_FIRST_NAME, CAND_MID_NAME, CAND_LAST_NAME, CAND_PHOTO, "
							+ " CAND_DOB, CAND_GENDER, CAND_MART_STATUS, CAND_OFF_PHONE, CAND_MOB_PHONE, CAND_RES_PHONE, CAND_EMAIL_ID, "
							+ " CAND_EMPLOYED, CAND_RELOCATION, CAND_OTHER_INFO, CAND_RESUME, CAND_EXP_YEAR, CAND_EXP_MONTH, CAND_SHORT_STATUS, "
							+ " CAND_REF_TYPE, CAND_CURR_CTC, CAND_EXPC_CTC, CAND_POSTING_DATE, CAND_DOYOUKNOW, CAND_REF_EMPID, "
							+ " CAND_PASSPORT_NO, CAND_PAN_NO) VALUES("
							+ maxCandDatabankCode[0][0]
							+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					getSqlModel().singleExecute(insertonlineDataBankDtlQuery,
							onlineDataBank);
				}
				// Candidate Details INSERT Ends

				/*
				 * Object[][] onlineAddressDetail =
				 * getOnlineAddressDetail(manPowerReqs.getCandidateUserEmpId());
				 */
				// Address Details INSERT Begins
				String addressQuery = "SELECT CAND_ADD_TYPE, CAND_ADD, CAND_ADD_CITY, "
						+ " CAND_ADD_PINCODE, CAND_ADD_STATE, CAND_ADD_COUNTRY FROM HRMS_REC_CDOL_ADDRESSDTL  "
						+ " WHERE CAND_CODE = "
						+ manPowerReqs.getCandidateUserEmpId()
						+ " AND CAND_ADD_TYPE IN ('C','P') ";
				Object[][] onlineAddressDetail = getSqlModel().getSingleResult(
						addressQuery);

				Object[][] onlineAddressDtlObj = null;
				String insertAddressDtlQuery = "";
				if (onlineAddressDetail != null
						&& onlineAddressDetail.length > 0) {
					String maxCodeQuery = " SELECT NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_ADDRESSDTL ";
					Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
							maxCodeQuery);
					int count = 0;
					if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
						count = Integer.parseInt(String
								.valueOf(maxCodeQueryObj[0][0]));
					}
					onlineAddressDtlObj = new Object[onlineAddressDetail.length][7];
					for (int i = 0; i < onlineAddressDetail.length; i++) {
						onlineAddressDtlObj[i][0] = count++;
						if (String.valueOf(onlineAddressDetail[i][0]).equals(
								"C")) {
							onlineAddressDtlObj[i][1] = "C";
						} else {
							onlineAddressDtlObj[i][1] = "P";
						}
						// onlineAddressDtlObj[i][1] =
						// checkNull(String.valueOf(onlineAddressDetail[i][0]));
						onlineAddressDtlObj[i][2] = checkNull(String
								.valueOf(onlineAddressDetail[i][1]));
						onlineAddressDtlObj[i][3] = checkNull(String
								.valueOf(onlineAddressDetail[i][2]));
						onlineAddressDtlObj[i][4] = checkNull(String
								.valueOf(onlineAddressDetail[i][3]));
						onlineAddressDtlObj[i][5] = checkNull(String
								.valueOf(onlineAddressDetail[i][4]));
						onlineAddressDtlObj[i][6] = checkNull(String
								.valueOf(onlineAddressDetail[i][5]));

						insertAddressDtlQuery = "INSERT INTO HRMS_REC_CD_ADDRESSDTL (CAND_DTL_CODE,CAND_CODE,CAND_ADD_TYPE,CAND_ADD,CAND_ADD_CITY, "
								+ " CAND_ADD_PINCODE,CAND_ADD_STATE,CAND_ADD_COUNTRY) VALUES(?, "
								+ maxCandDatabankCode[0][0] + ",?,?,?,?,?,?)";
					}
					getSqlModel().singleExecute(insertAddressDtlQuery,
							onlineAddressDtlObj);
				}
				// Address Details INSERT Ends

				/*
				 * Object[][] onlineExpDetail =
				 * getOnlineExpDetail(manPowerReqs.getCandidateUserEmpId());
				 */
				// Experience Details INSERT Begins
				String expQuery = "SELECT CAND_EMPLOYER_NAME, CAND_EMP_PORF, TO_CHAR(CAND_JOIN_DATE,'DD-MM-YYYY'), TO_CHAR(CAND_RELV_DATE,'DD-MM-YYYY'), "
						+ " CAND_JOB_DESC, CAND_SPEC_ACHV, CAND_INDUS_TYPE, CAND_CTC "
						+ " FROM HRMS_REC_CDOL_EXPDTL WHERE CAND_CODE ="
						+ manPowerReqs.getCandidateUserEmpId();
				Object[][] onlineExpDetail = getSqlModel().getSingleResult(
						expQuery);

				Object[][] onlineExperienceDtlObj = null;
				String insertExperienceDtlQuery = "";
				if (onlineExpDetail != null && onlineExpDetail.length > 0) {
					String maxCodeQuery = " SELECT NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_EXPDTL ";
					Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
							maxCodeQuery);
					int count = 0;
					if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
						count = Integer.parseInt(String
								.valueOf(maxCodeQueryObj[0][0]));
					}
					onlineExperienceDtlObj = new Object[onlineExpDetail.length][9];

					for (int i = 0; i < onlineExpDetail.length; i++) {
						onlineExperienceDtlObj[i][0] = count++;
						onlineExperienceDtlObj[i][1] = checkNull(String
								.valueOf(onlineExpDetail[i][0]));
						onlineExperienceDtlObj[i][2] = checkNull(String
								.valueOf(onlineExpDetail[i][1]));
						onlineExperienceDtlObj[i][3] = checkNull(String
								.valueOf(onlineExpDetail[i][2]));
						onlineExperienceDtlObj[i][4] = checkNull(String
								.valueOf(onlineExpDetail[i][3]));
						onlineExperienceDtlObj[i][5] = checkNull(String
								.valueOf(onlineExpDetail[i][4]));
						onlineExperienceDtlObj[i][6] = checkNull(String
								.valueOf(onlineExpDetail[i][5]));
						onlineExperienceDtlObj[i][7] = checkNull(String
								.valueOf(onlineExpDetail[i][6]));
						onlineExperienceDtlObj[i][8] = checkNull(String
								.valueOf(onlineExpDetail[i][7]));
						insertExperienceDtlQuery = "INSERT INTO  HRMS_REC_CD_EXPDTL (CAND_DTL_CODE, CAND_CODE, CAND_EMPLOYER_NAME, CAND_EMP_PORF, CAND_JOIN_DATE, "
								+ " CAND_RELV_DATE, CAND_JOB_DESC,CAND_SPEC_ACHV, CAND_INDUS_TYPE, CAND_CTC) "
								+ " VALUES(?,"
								+ maxCandDatabankCode[0][0]
								+ ",?,?,TO_DATE(?, 'DD-MM-YYYY'),TO_DATE(?, 'DD-MM-YYYY'),?,?,?,?)";
					}
					getSqlModel().singleExecute(insertExperienceDtlQuery,
							onlineExperienceDtlObj);
				}
				// Experience Details INSERT Ends

				/*
				 * Object[][] onlineFuncDtl =
				 * getOnlineFuncDtl(manPowerReqs.getCandidateUserEmpId());
				 */
				// Functional Details INSERT Begins
				String funcQuery = "SELECT CAND_FUNC_CODE,CAND_EXP FROM HRMS_REC_CDOL_FUNCDTL WHERE CAND_CODE = "
						+ manPowerReqs.getCandidateUserEmpId();
				Object[][] onlineFuncDtl = getSqlModel().getSingleResult(
						funcQuery);

				Object[][] onlineFuncDtlObj = null;
				String insertFuncDtlQuery = "";
				if (onlineFuncDtl != null && onlineFuncDtl.length > 0) {
					String maxCodeQuery = " SELECT NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_SKILLFUNCDTL ";
					Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
							maxCodeQuery);
					int count = 0;
					if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
						count = Integer.parseInt(String
								.valueOf(maxCodeQueryObj[0][0]));
					}
					onlineFuncDtlObj = new Object[onlineFuncDtl.length][3];
					for (int i = 0; i < onlineFuncDtl.length; i++) {
						onlineFuncDtlObj[i][0] = count++;
						onlineFuncDtlObj[i][1] = checkNull(String
								.valueOf(onlineFuncDtl[i][0]));
						onlineFuncDtlObj[i][2] = checkNull(String
								.valueOf(onlineFuncDtl[i][1]));
						insertFuncDtlQuery = "INSERT INTO HRMS_REC_CD_SKILLFUNCDTL (CAND_DTL_CODE, CAND_CODE, CAND_FIELD_TYPE, CAND_SF_CODE, CAND_EXP) "
								+ " VALUES(?,"
								+ maxCandDatabankCode[0][0]
								+ ",'F',?,?)";
					}
					getSqlModel().singleExecute(insertFuncDtlQuery,
							onlineFuncDtlObj);
				}
				// Functional Details INSERT Ends

				/*
				 * Object[][] onlineSkillDetail =
				 * getOnlineSkillDetail(manPowerReqs.getCandidateUserEmpId());
				 */
				// SKILL Details INSERT Begins
				String skillQuery = "SELECT CAND_SKILL_CODE,CAND_EXP FROM HRMS_REC_CDOL_SKILLDTL WHERE CAND_CODE = "
						+ manPowerReqs.getCandidateUserEmpId();
				Object[][] onlineSkillDetail = getSqlModel().getSingleResult(
						skillQuery);

				Object onlineSkillDetailObj[][] = null;
				String insertSkillDetailQuery = "";
				if (onlineSkillDetail != null && onlineSkillDetail.length > 0) {
					String maxCodeQuery = " SELECT NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_SKILLFUNCDTL ";
					Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
							maxCodeQuery);
					int count = 0;
					if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
						count = Integer.parseInt(String
								.valueOf(maxCodeQueryObj[0][0]));
					}
					onlineSkillDetailObj = new Object[onlineSkillDetail.length][3];

					for (int i = 0; i < onlineSkillDetail.length; i++) {
						onlineSkillDetailObj[i][0] = count++;
						onlineSkillDetailObj[i][1] = checkNull(String
								.valueOf(onlineSkillDetail[i][0]));
						onlineSkillDetailObj[i][2] = checkNull(String
								.valueOf(onlineSkillDetail[i][1]));
						insertSkillDetailQuery = "INSERT INTO HRMS_REC_CD_SKILLFUNCDTL (CAND_DTL_CODE, CAND_CODE, CAND_FIELD_TYPE, CAND_SF_CODE, CAND_EXP) "
								+ " VALUES(?,"
								+ maxCandDatabankCode[0][0]
								+ ",'S',?,?)";
					}
					getSqlModel().singleExecute(insertSkillDetailQuery,
							onlineSkillDetailObj);
				}
				// SKILL Details INSERT Ends

				// Reference Details INSERT Begins
				String refQuery = "SELECT CAND_REFNAME, CAND_PROFES, CAND_CONTACT, CAND_COMMENTS FROM HRMS_REC_CDOL_REFDTL WHERE CAND_CODE = "
						+ manPowerReqs.getCandidateUserEmpId() + "";
				Object[][] onlineRefDetail = getSqlModel().getSingleResult(
						refQuery);

				Object insertRefObj[][] = null;
				String insertRefDetailQuery = "";
				if (onlineRefDetail != null && onlineRefDetail.length > 0) {
					String maxCodeQuery = " SELECT  NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_REFDTL ";
					Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
							maxCodeQuery);
					int count = 0;
					if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
						count = Integer.parseInt(String
								.valueOf(maxCodeQueryObj[0][0]));
					}
					insertRefObj = new Object[onlineRefDetail.length][5];
					for (int i = 0; i < onlineRefDetail.length; i++) {
						insertRefObj[i][0] = count++;
						insertRefObj[i][1] = checkNull(String
								.valueOf(onlineRefDetail[i][0]));
						insertRefObj[i][2] = checkNull(String
								.valueOf(onlineRefDetail[i][1]));
						insertRefObj[i][3] = checkNull(String
								.valueOf(onlineRefDetail[i][2]));
						insertRefObj[i][4] = checkNull(String
								.valueOf(onlineRefDetail[i][3]));
						insertRefDetailQuery = "INSERT INTO HRMS_REC_CD_REFDTL (CAND_DTL_CODE, CAND_CODE, CAND_REFNAME, CAND_PROFES, CAND_CONTACT, CAND_COMMENTS) "
								+ " VALUES(?,"
								+ maxCandDatabankCode[0][0]
								+ ",?,?,?,?)";
					}
					getSqlModel().singleExecute(insertRefDetailQuery,
							insertRefObj);
				}
				// Reference Details INSERT Ends

				// Qualification Details INSERT Begins
				/*
				 * Object[][] onlineQualificationDtl =
				 * getOnlineQualificationDtl(manPowerReqs.getCandidateUserEmpId());
				 */
				String QuaQuery = "SELECT CAND_QUA_CODE, CAND_SPEC_CODE, CAND_ESTB_NAME, "
						+ " CAND_YEAR_PASS, CAND_PERC_MARKS FROM HRMS_REC_CDOL_QUADTL WHERE CAND_CODE = "
						+ manPowerReqs.getCandidateUserEmpId();
				Object[][] onlineQualificationDtl = getSqlModel()
						.getSingleResult(QuaQuery);

				Object onlineQualificationDtlObj[][] = null;
				String insertQualificationDetailsQuery = "";
				if (onlineQualificationDtl != null
						&& onlineQualificationDtl.length > 0) {
					String maxCodeQuery = " SELECT  NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_QUADTL ";
					Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(
							maxCodeQuery);
					int count = 0;
					if (maxCodeQueryObj != null && maxCodeQueryObj.length > 0) {
						count = Integer.parseInt(String
								.valueOf(maxCodeQueryObj[0][0]));
					}

					onlineQualificationDtlObj = new Object[onlineQualificationDtl.length][6];
					for (int i = 0; i < onlineQualificationDtl.length; i++) {
						onlineQualificationDtlObj[i][0] = count++;
						onlineQualificationDtlObj[i][1] = checkNull(String
								.valueOf(onlineQualificationDtl[i][0]));
						onlineQualificationDtlObj[i][2] = checkNull(String
								.valueOf(onlineQualificationDtl[i][1]));
						onlineQualificationDtlObj[i][3] = checkNull(String
								.valueOf(onlineQualificationDtl[i][2]));
						onlineQualificationDtlObj[i][4] = checkNull(String
								.valueOf(onlineQualificationDtl[i][3]));
						onlineQualificationDtlObj[i][5] = checkNull(String
								.valueOf(onlineQualificationDtl[i][4]));
						insertQualificationDetailsQuery = "INSERT INTO HRMS_REC_CD_QUADTL (CAND_DTL_CODE, CAND_CODE,CAND_QUA_CODE, CAND_SPEC_CODE, CAND_ESTB_NAME, "
								+ " CAND_YEAR_PASS, CAND_PERC_MARKS) "
								+ " VALUES(?,"
								+ maxCandDatabankCode[0][0]
								+ ",?,?,?,?,?)";
					}
					getSqlModel().singleExecute(
							insertQualificationDetailsQuery,
							onlineQualificationDtlObj);
				}
				// Qualification Details INSERT Ends

				/*
				 * Object[][]whetherCandidateApplied = null; try { String
				 * whetherApplied = "SELECT POST_CODE,POST_CAND_CODE FROM
				 * HRMS_REC_CAND_POSTING " +" WHERE POST_REQS_CODE = "+reqCode+"
				 * AND POST_CAND_CODE = "+maxCandDatabankCode[0][0]+"";
				 * whetherCandidateApplied =
				 * getSqlModel().getSingleResult(whetherApplied); } catch
				 * (Exception e) { logger.error("exception in
				 * whetherCandidateApplied query",e); } //end of catch
				 * if(whetherCandidateApplied !=null &&
				 * whetherCandidateApplied.length > 0){ String
				 * updateCandidatePosting = "UPDATE HRMS_REC_CAND_POSTING SET
				 * POST_POSTING_DATE = " +" (SELECT
				 * TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')FROM DUAL) " +"
				 * WHERE POST_CODE = "+whetherCandidateApplied[0][0]+"";
				 * getSqlModel().singleExecute(updateCandidatePosting); } //end
				 * of if else{ String insertCandidatePosting = "INSERT INTO
				 * HRMS_REC_CAND_POSTING (POST_CODE, POST_REQS_CODE,
				 * POST_CAND_CODE, POST_RESUME_SOURCE, POST_POSTING_DATE) " +"
				 * VALUES ((SELECT NVL(MAX(POST_CODE),0)+1 FROM
				 * HRMS_REC_CAND_POSTING),"+reqCode+","+maxCandDatabankCode[0][0]+",'O',(SELECT
				 * TO_DATE(SYSDATE,'DD-MM-YYYY')FROM DUAL))";
				 * getSqlModel().singleExecute(insertCandidatePosting); } //end
				 * of else
				 */
				String insertCandidatePosting = "INSERT INTO HRMS_REC_CAND_POSTING (POST_CODE, POST_REQS_CODE, POST_CAND_CODE, POST_RESUME_SOURCE, POST_POSTING_DATE) "
						+ " VALUES ((SELECT  NVL(MAX(POST_CODE),0)+1 FROM HRMS_REC_CAND_POSTING),"
						+ reqCode
						+ ","
						+ maxCandDatabankCode[0][0]
						+ ",'O',(SELECT TO_DATE(SYSDATE,'DD-MM-YYYY')FROM DUAL))";
				getSqlModel().singleExecute(insertCandidatePosting);

				/**
				 * Change by vishwambhar
				 */
				String updateCandLoginCode = "UPDATE HRMS_REC_CAND_LOGIN SET  CAND_DATABANK_CODE = "
						+ maxCandDatabankCode[0][0]
						+ " WHERE CAND_CODE = "
						+ manPowerReqs.getCandidateUserEmpId() + "";
				getSqlModel().singleExecute(updateCandLoginCode);
				Object[][] dataToSwipeCodes = null;

				/*
				 * String queryToCheckExistingCode = "SELECT CAND_CODE FROM
				 * HRMS_REC_CDOL_DATABANK WHERE CAND_CODE = " +
				 * maxCandDatabankCode[0][0] + ""; dataToSwipeCodes =
				 * getSqlModel().getSingleResult( queryToCheckExistingCode);
				 * 
				 * if (dataToSwipeCodes != null && dataToSwipeCodes.length > 0) {
				 *//**
					 * first updated the candidate user employee id to zero of
					 * online databank....
					 */
				/*
				 * swipeOnlineDatabankCode(manPowerReqs, maxCandDatabankCode,
				 * dataToSwipeCodes);
				 * 
				 *//**
					 * here to swipe the codes of HRMS_REC_CDOL_ADDRESSDTL
					 */
				/*
				 * swipeOnlineDatabankAddressCode(manPowerReqs,
				 * maxCandDatabankCode, dataToSwipeCodes);
				 * 
				 *//**
					 * here to swipe the codes of HRMS_REC_CDOL_EXPDTL
					 */
				/*
				 * swipeOnlineDatabankExpCode(manPowerReqs, maxCandDatabankCode,
				 * dataToSwipeCodes);
				 *//**
					 * here to swipe the codes of HRMS_REC_CDOL_FUNCDTL
					 */
				/*
				 * swipeOnlineDatabankFuncCode(manPowerReqs,
				 * maxCandDatabankCode, dataToSwipeCodes);
				 *//**
					 * here to swipe the codes of HRMS_REC_CDOL_SKILLDTL
					 */
				/*
				 * swipeOnlineDatabankSkillCode(manPowerReqs,
				 * maxCandDatabankCode, dataToSwipeCodes);
				 *//**
					 * here to swipe the codes of HRMS_REC_CDOL_REFDTL
					 */
				/*
				 * swipeOnlineDatabankRefCode(manPowerReqs, maxCandDatabankCode,
				 * dataToSwipeCodes);
				 *//**
					 * here to swipe the codes of HRMS_REC_CDOL_QUADTL
					 */
				/*
				 * swipeOnlineDatabankQuaCode(manPowerReqs, maxCandDatabankCode,
				 * dataToSwipeCodes);
				 *  } // end of if else {
				 * functionToSwipeCandidateCode("HRMS_REC_CDOL_DATABANK", String
				 * .valueOf(maxCandDatabankCode[0][0]), manPowerReqs
				 * .getCandidateUserEmpId());
				 * functionToSwipeCandidateCode("HRMS_REC_CDOL_ADDRESSDTL",
				 * String .valueOf(maxCandDatabankCode[0][0]), manPowerReqs
				 * .getCandidateUserEmpId());
				 * functionToSwipeCandidateCode("HRMS_REC_CDOL_EXPDTL", String
				 * .valueOf(maxCandDatabankCode[0][0]), manPowerReqs
				 * .getCandidateUserEmpId());
				 * functionToSwipeCandidateCode("HRMS_REC_CDOL_FUNCDTL", String
				 * .valueOf(maxCandDatabankCode[0][0]), manPowerReqs
				 * .getCandidateUserEmpId());
				 * functionToSwipeCandidateCode("HRMS_REC_CDOL_SKILLDTL", String
				 * .valueOf(maxCandDatabankCode[0][0]), manPowerReqs
				 * .getCandidateUserEmpId());
				 * functionToSwipeCandidateCode("HRMS_REC_CDOL_REFDTL", String
				 * .valueOf(maxCandDatabankCode[0][0]), manPowerReqs
				 * .getCandidateUserEmpId());
				 * functionToSwipeCandidateCode("HRMS_REC_CDOL_QUADTL", String
				 * .valueOf(maxCandDatabankCode[0][0]), manPowerReqs
				 * .getCandidateUserEmpId()); } // end of else
				 */String updateAdvtResponses = "UPDATE HRMS_REC_ADVT_DTL SET ADVT_RESPONSES = (SELECT  NVL(MAX(ADVT_RESPONSES),0)+1 FROM HRMS_REC_ADVT_DTL WHERE ADVT_DTL_CODE = "
						+ manPowerReqs.getAdvtName()
						+ ")"
						+ " WHERE ADVT_DTL_CODE = "
						+ manPowerReqs.getAdvtName() + "";
				getSqlModel().singleExecute(updateAdvtResponses);
				/*
				 * CandidateLoginBean loginBean_session = new
				 * CandidateLoginBean();
				 * loginBean_session.setCandidateCode(String
				 * .valueOf(maxCandDatabankCode[0][0]));
				 * session.setAttribute("loginBeanData", loginBean_session);
				 * manPowerReqs.setCandidateUserEmpId(String
				 * .valueOf(maxCandDatabankCode[0][0]));
				 */
				value = "You have successfully applied for this position";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	private void swipeOnlineDatabankSkillCode(EmployeeRequisition manPowerReqs,
			Object[][] maxCandDatabankCode, Object[][] dataToSwipeCodes) {
		try {
			/**
			 * first updated the candidate user employee id to zero of online
			 * skillfunctional dtl....
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_SKILLDTL", "0",
					manPowerReqs.getCandidateUserEmpId());
			/**
			 * here the existing candidate is been swiped...
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_SKILLDTL", manPowerReqs
					.getCandidateUserEmpId(), String
					.valueOf(dataToSwipeCodes[0][0]));
			functionToSwipeCandidateCode("HRMS_REC_CDOL_SKILLDTL", String
					.valueOf(maxCandDatabankCode[0][0]), "0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void swipeOnlineDatabankQuaCode(EmployeeRequisition manPowerReqs,
			Object[][] maxCandDatabankCode, Object[][] dataToSwipeCodes) {
		try {
			/**
			 * first updated the candidate user employee id to zero of online
			 * qualification dtl....
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_QUADTL", "0",
					manPowerReqs.getCandidateUserEmpId());
			/**
			 * here the existing candidate is been swiped...
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_QUADTL", manPowerReqs
					.getCandidateUserEmpId(), String
					.valueOf(dataToSwipeCodes[0][0]));
			functionToSwipeCandidateCode("HRMS_REC_CDOL_QUADTL", String
					.valueOf(maxCandDatabankCode[0][0]), "0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of swipeOnlineDatabankQuaCode method

	private void swipeOnlineDatabankRefCode(EmployeeRequisition manPowerReqs,
			Object[][] maxCandDatabankCode, Object[][] dataToSwipeCodes) {
		try {
			/**
			 * first updated the candidate user employee id to zero of online
			 * reference dtl....
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_REFDTL", "0",
					manPowerReqs.getCandidateUserEmpId());
			/**
			 * here the existing candidate is been swiped...
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_REFDTL", manPowerReqs
					.getCandidateUserEmpId(), String
					.valueOf(dataToSwipeCodes[0][0]));
			functionToSwipeCandidateCode("HRMS_REC_CDOL_REFDTL", String
					.valueOf(maxCandDatabankCode[0][0]), "0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of swipeOnlineDatabankRefCode method

	private void swipeOnlineDatabankFuncCode(EmployeeRequisition manPowerReqs,
			Object[][] maxCandDatabankCode, Object[][] dataToSwipeCodes) {
		try {
			/**
			 * first updated the candidate user employee id to zero of online
			 * skillfunctional dtl....
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_FUNCDTL", "0",
					manPowerReqs.getCandidateUserEmpId());
			/**
			 * here the existing candidate is been swiped...
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_FUNCDTL", manPowerReqs
					.getCandidateUserEmpId(), String
					.valueOf(dataToSwipeCodes[0][0]));
			functionToSwipeCandidateCode("HRMS_REC_CDOL_FUNCDTL", String
					.valueOf(maxCandDatabankCode[0][0]), "0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of swipeOnlineDatabankFuncCode method

	private void swipeOnlineDatabankExpCode(EmployeeRequisition manPowerReqs,
			Object[][] maxCandDatabankCode, Object[][] dataToSwipeCodes) {
		try {
			/**
			 * first updated the candidate user employee id to zero of online
			 * exp dtl....
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_EXPDTL", "0",
					manPowerReqs.getCandidateUserEmpId());
			/**
			 * here the existing candidate is been swiped...
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_EXPDTL", manPowerReqs
					.getCandidateUserEmpId(), String
					.valueOf(dataToSwipeCodes[0][0]));
			functionToSwipeCandidateCode("HRMS_REC_CDOL_EXPDTL", String
					.valueOf(maxCandDatabankCode[0][0]), "0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of swipeOnlineDatabankExpCode method

	private void swipeOnlineDatabankAddressCode(
			EmployeeRequisition manPowerReqs, Object[][] maxCandDatabankCode,
			Object[][] dataToSwipeCodes) {
		try {
			/**
			 * first updated the candidate user employee id to zero of online
			 * address dtl....
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_ADDRESSDTL", "0",
					manPowerReqs.getCandidateUserEmpId());
			/**
			 * here the existing candidate is been swiped...
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_ADDRESSDTL",
					manPowerReqs.getCandidateUserEmpId(), String
							.valueOf(dataToSwipeCodes[0][0]));
			functionToSwipeCandidateCode("HRMS_REC_CDOL_ADDRESSDTL", String
					.valueOf(maxCandDatabankCode[0][0]), "0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of swipeOnlineDatabankAddressCode method

	private void swipeOnlineDatabankCode(EmployeeRequisition manPowerReqs,
			Object[][] maxCandDatabankCode, Object[][] dataToSwipeCodes) {
		try {
			/**
			 * first updated the candidate user employee id to zero of online
			 * databank....
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_DATABANK", "0",
					manPowerReqs.getCandidateUserEmpId());
			/**
			 * here the existing candidate is been swiped...
			 */
			functionToSwipeCandidateCode("HRMS_REC_CDOL_DATABANK", manPowerReqs
					.getCandidateUserEmpId(), String
					.valueOf(dataToSwipeCodes[0][0]));
			functionToSwipeCandidateCode("HRMS_REC_CDOL_DATABANK", String
					.valueOf(maxCandDatabankCode[0][0]), "0");
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // end of swipeOnlineDatabankCode method

	private void functionToSwipeCandidateCode(String tableName,
			String candidateCode1, String candidateCode2) {
		try {
			String updateQuery = "UPDATE " + tableName + " SET CAND_CODE = "
					+ candidateCode1 + " " + " WHERE CAND_CODE = "
					+ candidateCode2 + "";
			getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			logger.error("exception in functionToSwipeCandidateCode method", e);
		} // end of catch

	} // end of functionToSwipeCandidateCode method

	private Object[][] getOnlineQualificationDtl(String candidateUserEmpId) {
		Object[][] onlineQualificationDtl = null;
		try {
			String QuaQuery = "SELECT CAND_QUA_CODE, CAND_SPEC_CODE, CAND_ESTB_NAME, "
					+ " CAND_YEAR_PASS, CAND_PERC_MARKS FROM HRMS_REC_CDOL_QUADTL WHERE CAND_CODE = "
					+ candidateUserEmpId + "";
			onlineQualificationDtl = getSqlModel().getSingleResult(QuaQuery);
		} catch (Exception e) {
			logger.error("exception in online qua query", e);
		} // end of catch
		return onlineQualificationDtl;
	} // end of getOnlineQualificationDtl method

	private Object[][] getOnlineRefDetail(String candidateUserEmpId) {
		Object[][] onlineRefDetail = null;
		try {
			String refQuery = "SELECT CAND_REFNAME, CAND_PROFES, CAND_CONTACT, CAND_COMMENTS FROM HRMS_REC_CDOL_REFDTL WHERE CAND_CODE = "
					+ candidateUserEmpId + "";
			onlineRefDetail = getSqlModel().getSingleResult(refQuery);
		} catch (Exception e) {
			logger.error("exception in refQuery", e);
		}
		return onlineRefDetail;
	}

	private Object[][] getOnlineSkillDetail(String candidateUserEmpId) {
		Object[][] onlineSkillDetail = null;
		try {
			String skillQuery = "SELECT CAND_SKILL_CODE,CAND_EXP FROM HRMS_REC_CDOL_SKILLDTL WHERE CAND_CODE = "
					+ candidateUserEmpId + "";
			onlineSkillDetail = getSqlModel().getSingleResult(skillQuery);
		} catch (Exception e) {
			logger.error("exception in onlineSkillDetail query", e);
		}
		return onlineSkillDetail;
	} // end of getOnlineSkillDetail method

	private Object[][] getOnlineFuncDtl(String candidateUserEmpId) {
		Object[][] onlineFuncDtl = null;
		try {
			String funcQuery = "SELECT CAND_FUNC_CODE,CAND_EXP FROM HRMS_REC_CDOL_FUNCDTL WHERE CAND_CODE = "
					+ candidateUserEmpId + "";
			onlineFuncDtl = getSqlModel().getSingleResult(funcQuery);
		} catch (Exception e) {
			logger.error("exception in onlineFunc query", e);
		} // end of catch
		return onlineFuncDtl;
	} // end of getOnlineFuncDtl method

	private Object[][] getOnlineExpDetail(String candidateUserEmpId) {
		Object[][] onlineExpDetail = null;
		try {
			String expQuery = "SELECT CAND_EMPLOYER_NAME, CAND_EMP_PORF, CAND_JOIN_DATE, CAND_RELV_DATE, "
					+ " CAND_JOB_DESC, CAND_SPEC_ACHV, CAND_INDUS_TYPE, CAND_CTC "
					+ " FROM HRMS_REC_CDOL_EXPDTL WHERE CAND_CODE ="
					+ candidateUserEmpId + " ";
			onlineExpDetail = getSqlModel().getSingleResult(expQuery);
		} catch (Exception e) {
			logger.error("exception in expQuery quewry", e);
		} // end of catch
		return onlineExpDetail;
	} // end of getOnlineExpDetail method

	/*
	 * private Object[][] getOnlineCertDetail(String candidateUserEmpId) {
	 * Object[][]onlineCertDetail = null; try { String certQuery = "SELECT
	 * CAND_CERT_NAME, CAND_ISS_AUTH, " + " CAND_ISS_ON, CAND_VALID_TILL FROM
	 * HRMS_REC_CDOL_CERTDTL WHERE CAND_CODE =" + candidateUserEmpId + " ";
	 * onlineCertDetail = getSqlModel().getSingleResult(certQuery); } catch
	 * (Exception e) { logger.error("exception in onlineCertDetail quewry",e); }
	 * //end of catch return onlineCertDetail; } //end of getOnlineCertDetail
	 * method
	 */
	private Object[][] getOnlineAddressDetail(String candidateUserEmpId) {
		Object[][] onlineAddressDetail = null;
		try {
			String addressQuery = "SELECT CAND_ADD_TYPE, CAND_ADD, CAND_ADD_CITY, "
					+ " CAND_ADD_PINCODE, CAND_ADD_STATE, CAND_ADD_COUNTRY FROM HRMS_REC_CDOL_ADDRESSDTL  "
					+ " WHERE CAND_CODE = "
					+ candidateUserEmpId
					+ " AND CAND_ADD_TYPE = 'C' ";
			onlineAddressDetail = getSqlModel().getSingleResult(addressQuery);
		} catch (Exception e) {
			logger.error("exception in online address query", e);
		} // end of catch
		return onlineAddressDetail;
	} // end of getOnlineAddressDetail method

	private Object[][] getOnlineDataBank(String candidateUserEmpId) {

		Object[][] onlineDataBank = null;
		try {
			String query = "SELECT CAND_FIRST_NAME, CAND_MID_NAME, CAND_LAST_NAME, CAND_PHOTO, "
					+ " CAND_DOB, CAND_GENDER, CAND_MART_STATUS, CAND_OFF_PHONE, CAND_MOB_PHONE, CAND_RES_PHONE, CAND_EMAIL_ID, "
					+ " CAND_EMPLOYED, CAND_RELOCATION, CAND_OTHER_INFO, CAND_RESUME, CAND_EXP_YEAR, CAND_EXP_MONTH, CAND_SHORT_STATUS, "
					+ " CAND_REF_TYPE, CAND_CURR_CTC, CAND_EXPC_CTC, CAND_POSTING_DATE, CAND_DOYOUKNOW, CAND_REF_EMPID, "
					+ " CAND_PASSPORT_NO, CAND_PAN_NO FROM HRMS_REC_CDOL_DATABANK WHERE CAND_CODE = "
					+ candidateUserEmpId + " ";
			onlineDataBank = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in onlineDataBank query", e);
		}
		return onlineDataBank;
	} // end of getOnlineDataBank method

	private Object[][] getPostingData(EmployeeRequisition manPowerReqs) {
		Object[][] data = null;

		try {
			String query = "SELECT * FROM HRMS_REC_CAND_POSTING "
					+ " WHERE POST_CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId() + ""
					+ "  AND POST_RESUME_SOURCE = 'O'";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in postingData", e);
		} // end of catch
		return data;
	} // end of getPostingData method

	private Object[][] getRecConfig() {
		Object[][] data = null;
		try {
			String query = "SELECT CONF_MULTI_POST,CONF_APPLY_DUR FROM HRMS_REC_CONF";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in recConfigParameters", e);
		} // end of catch
		return data;
	} // end of getRecConfig method

	public String checkProfileUpdate(EmployeeRequisition manPowerReqs) {
		String result = "no";
		Object[][] data = null;
		try {
			String query = "SELECT CAND_RESUME FROM HRMS_REC_CDOL_DATABANK WHERE CAND_CODE = "
					+ manPowerReqs.getCandidateUserEmpId() + "";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in resume check query", e);
		} // end of catch

		if (data != null && data.length > 0) {

			if (String.valueOf(data[0][0]).equals("")
					|| String.valueOf(data[0][0]).equals("null")
					|| String.valueOf(data[0][0]).equals(null)) {
				result = "no";
			} // end of if
			else {
				result = "yes";
			} // end of else
		} // end of if
		return result;
	} // end of checkProfileUpdate method

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}// end of checkNull

	public Object[][] getJobNameDetails(EmployeeRequisition manPowerReqs,
			HttpServletRequest request, String reqCodeStr) {
		// TODO Auto-generated method stub
		Object reqDtlObj[][] = null;
		try {
			String query = " SELECT NVL(HRMS_REC_REQS_HDR.REQS_JOBDESC_NAME,' '),  NVL(HRMS_REC_REQS_HDR.REQS_JOB_DESCRIPTION,' '), NVL(HRMS_REC_REQS_HDR.REQS_ROLE_RESPON,' '),   NVL(HRMS_REC_REQS_HDR.REQS_SPECIAL_REQ,' '),   NVL(HRMS_REC_REQS_HDR.REQS_PERSONEL_REQ,' ')"
					+ " FROM HRMS_REC_REQS_HDR "
					+ "WHERE REQS_CODE= "
					+ reqCodeStr;
			reqDtlObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return reqDtlObj;
	}

} //end of class
