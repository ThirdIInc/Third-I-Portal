/**
 * @author Pradeep
 */
package org.paradyne.model.recruitment;

import org.paradyne.bean.Recruitment.CandDataBank;
import org.paradyne.bean.Recruitment.ResumeApproval;
import org.paradyne.bean.Recruitment.backGroundCheck;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.common.LabelManagementModel;

import com.lowagie.text.Image;
import com.sun.mail.iap.Response;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResumeApprovalModel extends ModelBase {
	public HttpServletRequest request;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ResumeApprovalModel.class);

	public void delQual(ResumeApproval resumeApproval, String[] name,
			String[] code, String[] lvl, String[] spN, String[] spCode,
			String[] del, String[] year, String[] est, String[] detCode,
			HttpServletRequest request) {
		try {

			/**
			 * ArrayList list = new ArrayList(); Object qualObj[][] = new
			 * Object[1][3]; ResumeApproval bean1 = new ResumeApproval();
			 * 
			 * bean1.setSrNo(String.valueOf(1)); bean1.setQualDtlId("");
			 * qualObj[0][0] = ""; bean1.setSpelDtlId(""); qualObj[0][1] = "";
			 * qualObj[0][2] = ""; bean1.setEstbName("");
			 * bean1.setYearofPass(""); list.add(bean1); bean.setQualList(list);
			 * request.setAttribute("qualObj", qualObj);
			 */

			ArrayList<Object> tableList = new ArrayList<Object>();

			int counter = 0;
			for (int i = 0; i < name.length; i++) {
				if (!(String.valueOf(del[i]).equals("Y"))) {
					counter++;
				}
			}
			Object qualObj[][] = new Object[counter][5];
			if (est != null) {
				int m = 0;

				for (int i = 0; i < est.length; i++) {
					ResumeApproval bean = new ResumeApproval();
					// if(String.valueOf(hdel[i]).equals("Y")){
					// if(!(detCode[i].equals("") || detCode[i].equals(" ") ||
					// detCode[i].equals("null"))){
					// Object [][]del=new Object[1][2];
					// del[0][0]=detCode[i];
					// del[0][1]=bean.getReqCode();
					// getSqlModel().singleExecute(getQuery(13),del);
					// }
					//
					// }else

					if (!(String.valueOf(del[i]).equals("Y"))) {
						bean.setYearofPass(year[i]);
						bean.setEstbName(est[i]);
						bean.setQualDetCode(detCode[i]);
						qualObj[m][0] = name[i];
						qualObj[m][1] = lvl[i];
						qualObj[m][2] = spN[i];
						qualObj[m][3] = code[i];
						qualObj[m][4] = spCode[i];

						tableList.add(bean);
						m++;

					}

				}

			}
			resumeApproval.setQualList(tableList);
			request.setAttribute("qualObj", qualObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delRef(ResumeApproval resumeApproval, String[] ref,
			String[] profession, String[] contact, String[] comment,
			String[] code, String[] del, HttpServletRequest request) {
		try {

			ArrayList<Object> tableList = new ArrayList<Object>();
			int counter = 0;
			for (int i = 0; i < ref.length; i++) {
				if (!(String.valueOf(del[i]).equals("Y"))) {
					counter++;
				}
			}
			logger.info("val of counter" + counter);
			Object expObj[][] = new Object[counter][3];
			if (ref != null) {
				int m = 0;

				for (int i = 0; i < ref.length; i++) {
					ResumeApproval bean = new ResumeApproval();
					// if(String.valueOf(hdel[i]).equals("Y")){
					// if(!(detCode[i].equals("") || detCode[i].equals(" ") ||
					// detCode[i].equals("null"))){
					// Object [][]del=new Object[1][2];
					// del[0][0]=detCode[i];
					// del[0][1]=bean.getReqCode();
					// getSqlModel().singleExecute(getQuery(13),del);
					// }
					//
					// }else

					if (!(String.valueOf(del[i]).equals("Y"))) {
						bean.setRefName(ref[i]);
						bean.setProfession(profession[i]);
						bean.setContactDet(contact[i]);
						bean.setRefComment(comment[i]);
						bean.setRefDtlCode(code[i]);
						tableList.add(bean);

						m++;
					}

				}

			}
			resumeApproval.setRefList(tableList);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delExp(ResumeApproval resumeApproval, String[] employer,
			String[] profession, String[] joiningDate, String[] relieving,
			String[] splAch, String[] ctc, String[] detCode, String[] indsType,
			String[] indCode, String[] jobDescr, String[] del,
			HttpServletRequest request) {
		try {

			ArrayList<Object> tableList = new ArrayList<Object>();
			int counter = 0;
			for (int i = 0; i < employer.length; i++) {
				if (!(String.valueOf(del[i]).equals("Y"))) {
					counter++;
				}
			}
			logger.info("val of counter" + counter);
			Object expObj[][] = new Object[counter][3];
			if (employer != null) {
				int m = 0;

				for (int i = 0; i < employer.length; i++) {
					ResumeApproval bean = new ResumeApproval();
					// if(String.valueOf(hdel[i]).equals("Y")){
					// if(!(detCode[i].equals("") || detCode[i].equals(" ") ||
					// detCode[i].equals("null"))){
					// Object [][]del=new Object[1][2];
					// del[0][0]=detCode[i];
					// del[0][1]=bean.getReqCode();
					// getSqlModel().singleExecute(getQuery(13),del);
					// }
					//
					// }else

					if (!(String.valueOf(del[i]).equals("Y"))) {
						bean.setEmprName(employer[i]);
						bean.setLastJobPro(profession[i]);
						bean.setJoinDate(joiningDate[i]);
						bean.setRelvDate(relieving[i]);
						bean.setSpecAch(splAch[i]);
						bean.setCtcExp(ctc[i]);
						bean.setExpDtlId(detCode[i]);
						expObj[m][0] = jobDescr[i];
						expObj[m][1] = indsType[i];
						expObj[m][2] = indCode[i];
						tableList.add(bean);

						m++;
					}

				}

			}
			resumeApproval.setExpList(tableList);
			request.setAttribute("expObj", expObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delFunc(ResumeApproval resumeApproval, String[] exp,
			String[] detCode, String[] funCode, String[] name, String[] del,
			HttpServletRequest request) {
		try {

			ArrayList<Object> tableList = new ArrayList<Object>();
			int counter = 0;
			for (int i = 0; i < exp.length; i++) {
				if (!(String.valueOf(del[i]).equals("Y"))) {
					counter++;
				}
			}
			logger.info("val of counter" + counter);
			Object funcObj[][] = new Object[counter][2];
			if (exp != null) {
				int m = 0;

				for (int i = 0; i < exp.length; i++) {
					ResumeApproval bean = new ResumeApproval();
					// if(String.valueOf(hdel[i]).equals("Y")){
					// if(!(detCode[i].equals("") || detCode[i].equals(" ") ||
					// detCode[i].equals("null"))){
					// Object [][]del=new Object[1][2];
					// del[0][0]=detCode[i];
					// del[0][1]=bean.getReqCode();
					// getSqlModel().singleExecute(getQuery(13),del);
					// }
					//
					// }else

					if (!(String.valueOf(del[i]).equals("Y"))) {
						bean.setFuncExp(exp[i]);
						bean.setFuncDtlId(detCode[i]);
						funcObj[m][0] = name[i];
						funcObj[m][1] = funCode[i];
						tableList.add(bean);
						m++;
					}

				}

			}
			resumeApproval.setFuncList(tableList);
			request.setAttribute("funcObj", funcObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delSkill(ResumeApproval resumeApproval, String[] exp,
			String[] detCode, String[] skillCode, String[] name, String[] del,
			HttpServletRequest request) {
		try {

			ArrayList<Object> tableList = new ArrayList<Object>();
			int counter = 0;
			for (int i = 0; i < exp.length; i++) {
				if (!(String.valueOf(del[i]).equals("Y"))) {
					counter++;
				}
			}
			logger.info("val of counter" + counter);
			Object skillObj[][] = new Object[counter][2];
			if (exp != null) {
				int m = 0;

				for (int i = 0; i < exp.length; i++) {
					ResumeApproval bean = new ResumeApproval();
					// if(String.valueOf(hdel[i]).equals("Y")){
					// if(!(detCode[i].equals("") || detCode[i].equals(" ") ||
					// detCode[i].equals("null"))){
					// Object [][]del=new Object[1][2];
					// del[0][0]=detCode[i];
					// del[0][1]=bean.getReqCode();
					// getSqlModel().singleExecute(getQuery(13),del);
					// }
					//
					// }else

					if (!(String.valueOf(del[i]).equals("Y"))) {
						bean.setSkillExp(exp[i]);
						bean.setSkillDtlId(detCode[i]);
						skillObj[m][0] = name[i];
						skillObj[m][1] = skillCode[i];
						tableList.add(bean);
						m++;
					}

				}

			}
			resumeApproval.setSkillList(tableList);
			request.setAttribute("skillObj", skillObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * following function is called to display the list of records in page wise
	 * .Per page will display only 20 records.
	 */
	public void showRecords(ResumeApproval resumeApproval,
			HttpServletRequest request, String path, String msg, String msg1,
			String msg2, String msg3) {
		/*
		 * query to select the application data from HRMS_REC_CAND_DATABANK
		 */
		int count = 0;
		String concatStr = "";
		try {
			String showQuery = "SELECT CAND_CODE,CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,NVL(TO_CHAR(CAND_DOB,'dd-mm-yyyy'),' '),"
					+ "NVL(CAND_EXP_YEAR,0), NVL(CAND_EXP_MONTH,0),"
					+ "TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'),CASE WHEN CAND_SHORT_STATUS='S' THEN 'Short Listed' WHEN CAND_SHORT_STATUS='R' THEN 'Rejected' WHEN CAND_SHORT_STATUS='N' THEN 'New' ELSE ' ' END,CAND_POSTING_DATE,"
					+ " DECODE(CAND_REF_TYPE,'C','Consultant','O','Online','D','Direct','E','Employee Referral') FROM"
					+ " HRMS_REC_CAND_DATABANK WHERE 1=1 ";

			if (resumeApproval.getPageFlag().equals("true")) {

				if (!(resumeApproval.getSearchCandId().equals("") || resumeApproval
						.getSearchCandId().equals("null"))) {

					showQuery += " AND CAND_CODE="
							+ resumeApproval.getSearchCandId();
					concatStr += getMessage("cand.name", "346", path) + " :"
							+ resumeApproval.getSearchCandName() + ",";

				}
				boolean flag = false;
				if (!(resumeApproval.getExpYear().equals("") || resumeApproval
						.getExpYear().equals("null"))) {
					showQuery += " AND CAND_EXP_YEAR="
							+ resumeApproval.getExpYear();

					if (!(resumeApproval.getExpMonth().equals("") || resumeApproval
							.getExpMonth().equals("null"))) {
						logger.info("in if part");
						concatStr += getMessage("experience", "346", path)
								+ " :" + resumeApproval.getExpYear()
								+ "   Year" + " "
								+ resumeApproval.getExpMonth() + " Months"
								+ ",";
						flag = true;
					} else {
						logger.info("in else part");

						concatStr += getMessage("experience", "346", path)
								+ " :" + resumeApproval.getExpYear() + "  Year"
								+ ",";
						flag = true;
					}

					if (resumeApproval.getExpMonth().equals("")
							|| resumeApproval.getExpMonth().equals("null")) {
						showQuery += " AND CAND_EXP_MONTH=" + "'0'";
					}

					// concatStr+=getMessage("experience")+resumeApproval.getExpYear()+"
					// " +resumeApproval.getExpMonth()+",";
				}

				if (!(resumeApproval.getExpMonth().equals("") || resumeApproval
						.getExpMonth().equals("null"))) {
					showQuery += " AND CAND_EXP_MONTH="
							+ resumeApproval.getExpMonth();// +" AND
															// CAND_EXP_YEAR="+"'0'";

					if (resumeApproval.getExpYear().equals("")
							|| resumeApproval.getExpYear().equals("null")) {
						showQuery += " AND CAND_EXP_YEAR=" + "'0'";
					}
					if (flag == false) {
						concatStr += getMessage("experience", "346", path)
								+ ":" + resumeApproval.getExpMonth()
								+ "  Month" + ",";
					}

				}

				if (!(resumeApproval.getCandStatus().equals("") || resumeApproval
						.getCandStatus().equals("null"))) {
					showQuery += " AND CAND_SHORT_STATUS=" + "'"
							+ resumeApproval.getCandStatus() + "'";

					if (resumeApproval.getCandStatus().equals("N")) {
						concatStr += msg2 + " : New,";// getMessage("stat","346",path)+"
														// : New,";
					} else if (resumeApproval.getCandStatus().equals("S")) {
						concatStr += msg2 + " : Short Listed,";// getMessage("stat","346",path)+"
																// : Short
																// Listed,";
					} else if (resumeApproval.getCandStatus().equals("R")) {
						concatStr += msg2 + " : Rejected,";// getMessage("stat","346",path)+"
															// : Short Listed,";
					}

				}

				if (!(resumeApproval.getFromDate().equals("dd-mm-yyyy") || resumeApproval
						.getFromDate().equals("null"))) {
					showQuery += " AND CAND_POSTING_DATE >=TO_DATE(" + "'"
							+ resumeApproval.getFromDate() + "'"
							+ ",'DD-MM-YYYY')";
					concatStr += msg + " :" + resumeApproval.getFromDate()
							+ ",";// getMessage("postingFrmDt","346",path)+"
									// :"+resumeApproval.getFromDate()+",";

				}

				if (!(resumeApproval.getToDate().equals("dd-mm-yyyy") || resumeApproval
						.getToDate().equals("null"))) {
					showQuery += " AND CAND_POSTING_DATE <=TO_DATE(" + "'"
							+ resumeApproval.getToDate() + "'"
							+ ",'DD-MM-YYYY')";
					concatStr += msg1 + " :" + resumeApproval.getToDate() + ",";// getMessage("postingToDt","346",path)+"
																				// :"+resumeApproval.getToDate()+",";
				}

				if (!(resumeApproval.getResumeSrc().equals("") || resumeApproval
						.getResumeSrc().equals("null"))) {
					showQuery += " AND CAND_REF_TYPE=" + "'"
							+ resumeApproval.getResumeSrc() + "'";
					if (resumeApproval.getResumeSrc().equals("C")) {
						concatStr += msg3 + " : Consultant,";
					} else if (resumeApproval.getResumeSrc().equals("O")) {
						concatStr += msg3 + " : Online,";
					} else if (resumeApproval.getResumeSrc().equals("D")) {
						concatStr += msg3 + " : Direct,";
					} else if (resumeApproval.getResumeSrc().equals("E")) {
						concatStr += msg3 + " : Employee Referral,";
					}

				}

			}
			showQuery += " AND CAND_RESUME_APPROVAL_FLAG not in ('Y','R') ORDER BY CAND_POSTING_DATE DESC";

			Object[][] candidateData = getSqlModel().getSingleResult(showQuery);

			String[] pageIndex = Utility.doPaging(resumeApproval.getMyPage(),
					candidateData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				resumeApproval.setMyPage("1");

			ArrayList<Object> list = new ArrayList<Object>();
			if (candidateData != null && candidateData.length != 0) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					ResumeApproval bean = new ResumeApproval();
					resumeApproval.setMode(true);
					resumeApproval.setTotRecord(String
							.valueOf(candidateData.length));
					bean.setSrNo(String.valueOf(i + 1));
					// serial number
					bean.setCandCode(String.valueOf(candidateData[i][0]));
					// Candidate Code
					bean.setHiddenCandCode(String.valueOf(candidateData[i][0]));
					if (String.valueOf(candidateData[i][1]).equals("null"))
						bean.setFirstName("");
					else
						bean.setFirstName(String.valueOf(candidateData[i][1])); // Name
					if (String.valueOf(candidateData[i][2]).equals("null"))
						bean.setDob("");
					else
						bean.setDob(String.valueOf(candidateData[i][2])); // date
																			// of
																			// Birth
					if (String.valueOf(candidateData[i][3]).equals("null"))
						candidateData[0][3] = "0";
					if (String.valueOf(candidateData[i][4]).equals("null"))
						candidateData[0][4] = "0";
					bean.setExperience(checkNull(String
							.valueOf(candidateData[i][3]))
							+ " Year"
							+ "  "
							+ checkNull(String.valueOf(candidateData[i][4]))
							+ " Months"); // experience

					if (String.valueOf(candidateData[i][5]).equals("null")
							|| String.valueOf(candidateData[i][5]).equals("")
							|| String.valueOf(candidateData[i][5]).equals(" "))
						bean.setPostingDate("");
					else
						bean
								.setPostingDate(String
										.valueOf(candidateData[i][5])); // Posting
																		// date
					if (String.valueOf(candidateData[i][6]).equals("null"))
						bean.setShortStatus("");
					else
						bean
								.setShortStatus(String
										.valueOf(candidateData[i][6])); // short
																		// list
																		// status

					bean.setResSource(checkNull(String
							.valueOf(candidateData[i][8])));
					list.add(bean);
				} // for loop ends
				resumeApproval.setNoData("false"); // if there is no
													// application for selected
													// status set noDataFlag to
													// true
			} // if statement ends
			else {
				resumeApproval.setNoData("true"); // if there is no
													// application for selected
													// status set noDataFlag to
													// true
			} // else statement ends

			resumeApproval.setLoadList(list);
		} catch (Exception e) {
			logger.info("---exception in show record model", e);
		}

		try {
			String[] dispArr = concatStr.split(",");
			request.setAttribute("dispArr", dispArr);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 
	 */
	public boolean saveRecord(ResumeApproval bean) {
		// ArrayList<Object> candidateList = new ArrayList<Object>();
		boolean result = false;
		try {

			String query = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] dt = getSqlModel().getSingleResult(query);
			Object[][] addObj = new Object[1][29];

			// cand title added
			addObj[0][0] = bean.getFirstName();
			addObj[0][1] = bean.getMiddleName();
			addObj[0][2] = bean.getLastName();
			addObj[0][3] = bean.getDob();
			addObj[0][4] = bean.getGender();
			addObj[0][5] = bean.getMaritalStatus();
			if (bean.getMinExp().equals("") || bean.getMinExp().equals("null")
					|| bean.getMinExp().equals(" ")) {
				addObj[0][6] = String.valueOf("0");
			} else {
				addObj[0][6] = bean.getMinExp();
			}

			if (bean.getMaxExp().equals("") || bean.getMaxExp().equals("null")
					|| bean.getMaxExp().equals(" ")) {
				addObj[0][7] = String.valueOf("0");
			} else {
				addObj[0][7] = bean.getMaxExp();
			}

			addObj[0][8] = bean.getCurrentCtc();
			addObj[0][9] = bean.getExpectedCtc();
			addObj[0][10] = bean.getResPhone();
			addObj[0][11] = bean.getMobile();
			addObj[0][12] = bean.getOffPhone();
			addObj[0][13] = bean.getEmail();
			addObj[0][14] = bean.getWheYouEmp();
			addObj[0][15] = bean.getRelocate();
			addObj[0][16] = bean.getDoYou();
			addObj[0][17] = bean.getRefEmpName();
			addObj[0][18] = String.valueOf("D");
			addObj[0][19] = bean.getUploadFileName();
			addObj[0][20] = bean.getUploadPhoto();
			addObj[0][21] = bean.getOtherInfo();
			addObj[0][22] = String.valueOf(dt[0][0]);
			addObj[0][23] = bean.getPassport();
			addObj[0][24] = bean.getPanNo();
			addObj[0][25] = bean.getTitleCode();

			addObj[0][26] = bean.getCandidateCompany().trim();
			addObj[0][27] = bean.getCandidatePosition().trim();

			addObj[0][28] = bean.getCurrentLocation().trim();

			checkDupRecord(bean);

			if (bean.getDupChkFlag().equals("false")) {
				result = getSqlModel().singleExecute(getQuery(1), addObj);
				if (result) {
					String maxQuery = "SELECT MAX(CAND_CODE) FROM HRMS_REC_CAND_DATABANK";
					Object[][] candCode = getSqlModel().getSingleResult(
							maxQuery);
					if (candCode != null)
						bean.setCandCode(String.valueOf(candCode[0][0]));

					Object[][] tempAddr = new Object[1][7];
					tempAddr[0][0] = bean.getCandCode();
					tempAddr[0][1] = bean.getCityCode();
					tempAddr[0][2] = bean.getStateCode();
					tempAddr[0][3] = bean.getCountryCode();
					tempAddr[0][4] = bean.getPincode();
					tempAddr[0][5] = bean.getAddress();
					tempAddr[0][6] = String.valueOf("C");

					Object[][] permAddr = new Object[1][7];
					permAddr[0][0] = bean.getCandCode();
					permAddr[0][1] = bean.getCityCode1();
					permAddr[0][2] = bean.getStateCode1();
					permAddr[0][3] = bean.getCountryCode1();
					permAddr[0][4] = bean.getPincode1();
					permAddr[0][5] = bean.getAddress1();
					permAddr[0][6] = String.valueOf("P");

					result = getSqlModel().singleExecute(getQuery(7), tempAddr);
					result = getSqlModel().singleExecute(getQuery(8), permAddr);

				}
			}
			// candidateList.add(bean);

			return result;

		} catch (Exception e) {

			logger.info("Exception in saveRecord method ", e);
			return false;
		}

	}

	public void checkDupRecord(ResumeApproval bean) {

		try {
			String Query = "SELECT CONF_DUP_CHECK,CONF_EMAIL_FLAG,CONF_MOBILE_FLAG,CONF_PASSPORT_FLAG,CONF_PANNO_FLAG,CONF_DOB_FLAG,CONF_NAME_FLAG	FROM HRMS_REC_CONF";
			Object[][] Data = getSqlModel().getSingleResult(Query);

			if (Data != null && Data.length > 0) {

				String Query1 = "";
				if (Data[0][0].equals("Y")) {
					Query1 = "SELECT CAND_CODE,CAND_FIRST_NAME,CAND_LAST_NAME,TO_CHAR(CAND_DOB,'DD-MM-YYYY'),CAND_EMAIL_ID,CAND_PAN_NO,CAND_PASSPORT_NO FROM HRMS_REC_CAND_DATABANK ";
					if (Data[0][6].equals("Y") || Data[0][5].equals("Y")
							|| Data[0][1].equals("Y") || Data[0][2].equals("Y")
							|| Data[0][4].equals("Y") || Data[0][3].equals("Y")) {
						Query1 += " WHERE ";
					}
					int count = 0;
					if (Data[0][6].equals("Y")
							&& (!bean.getFirstName().equals("") || !bean
									.getFirstName().equals(null))) {
						Query1 += " UPPER(CAND_FIRST_NAME ||' ' || CAND_LAST_NAME ) =UPPER('"
								+ bean.getFirstName()
								+ " "
								+ bean.getLastName() + "')";
					} else
						count++;

					/*
					 * if(Data[0][6].equals("Y") &&
					 * (!bean.getLastName().equals("") ||
					 * !bean.getLastName().equals(null))) { Query1 += " AND
					 * UPPER(CAND_LAST_NAME) =UPPER('"+bean.getLastName()+"')"; }
					 */

					if (Data[0][5].equals("Y")
							&& (!bean.getDob().equals("") || !bean.getDob()
									.equals(null))) {
						if (count == 1)
							Query1 += "  CAND_DOB =TO_DATE('" + bean.getDob()
									+ "','DD-MM-YYYY')";
						else
							Query1 += " AND CAND_DOB =TO_DATE('"
									+ bean.getDob() + "','DD-MM-YYYY')";
					} else
						count++;

					if (Data[0][1].equals("Y")
							&& (!bean.getEmail().equals("") || !bean.getEmail()
									.equals(null))) {
						if (count == 2)
							Query1 += " UPPER(CAND_EMAIL_ID) = UPPER('"
									+ bean.getEmail() + "')";
						else
							Query1 += " AND UPPER(CAND_EMAIL_ID) = UPPER('"
									+ bean.getEmail() + "')";
					} else
						count++;

					if (Data[0][2].equals("Y")
							&& (!bean.getMobile().equals("") || !bean
									.getMobile().equals(null))) {
						if (count == 3)
							Query1 += " UPPER(CAND_MOB_PHONE) = "
									+ bean.getMobile();
						else
							Query1 += " AND UPPER(CAND_MOB_PHONE) = "
									+ bean.getMobile();
					} else
						count++;

					if (Data[0][4].equals("Y")) {
						if (bean.getPanNo() != null) {
							if (count == 4)
								Query1 += "  UPPER(CAND_PAN_NO) = UPPER('"
										+ bean.getPanNo() + "')";
							else
								Query1 += " OR UPPER(CAND_PAN_NO) = UPPER('"
										+ bean.getPanNo() + "')";
						}
					} else
						count++;

					if (Data[0][3].equals("Y")) {
						if (bean.getPassport() != null) {
							if (count == 5)
								Query1 += " UPPER(CAND_PASSPORT_NO) = UPPER('"
										+ bean.getPassport() + "')";
							else
								Query1 += " OR UPPER(CAND_PASSPORT_NO) = UPPER('"
										+ bean.getPassport() + "')";
						}
					} else
						count++;
					checkNull(bean.getCandCode());
					if (!bean.getCandCode().trim().equals(""))
						Query1 += " and CAND_CODE !=" + bean.getCandCode();

					if (count != 6) {
						Object[][] Data1 = getSqlModel()
								.getSingleResult(Query1);

						if (Data1 != null && Data1.length > 0) {
							bean.setDupChkFlag("true");
						} else {
							bean.setDupChkFlag("false");
						}

					} else
						bean.setDupChkFlag("false");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setDupChkFlag("false");
		}

	}

	/**
	 * 
	 */
	public boolean saveRefDet(String[] refName, String[] profes,
			String[] refContact, String[] refComments, String candCode) {
		boolean result = false;
		try {
			if (refName != null) {
				if (refName.length > 0) {

					for (int i = 0; i < refName.length; i++) {
						if (!(refName[i].trim().equals("null")
								|| refName[i].trim().equals("") || refName[i]
								.trim().equals(" "))) {
							Object[][] refObj = new Object[1][5];
							refObj[0][0] = candCode;
							refObj[0][1] = refName[i];
							refObj[0][2] = profes[i];
							refObj[0][3] = refContact[i];
							refObj[0][4] = refComments[i];
							result = getSqlModel().singleExecute(getQuery(2),
									refObj);
						}
					}

				}
			}
		} catch (Exception e) {
			logger.info("Exception in saveRefDet method ", e);
		}
		return result;
	}

	/**
	 * @purpose : To retrive Candidate information
	 */
	public void searchCandDet(ResumeApproval resumeApproval, String str,
			HttpServletRequest request) {
		try {

			String tempAddrsQuery = " SELECT NVL(HRMS_LOCATION.LOCATION_NAME,' '),NVL(state.LOCATION_NAME,' '),NVL(country.LOCATION_NAME,' '),HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY,NVL(HRMS_REC_CD_ADDRESSDTL.CAND_ADD,' '),HRMS_REC_CD_ADDRESSDTL.CAND_ADD_PINCODE FROM HRMS_LOCATION "
					+ " LEFT JOIN HRMS_REC_CD_ADDRESSDTL ON(HRMS_LOCATION.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY)"
					+ " LEFT JOIN HRMS_LOCATION state on(state.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE)"
					+ " LEFT JOIN HRMS_LOCATION country on(country.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY)"
					+ " where HRMS_REC_CD_ADDRESSDTL.CAND_CODE="
					+ resumeApproval.getCandCode()
					+ " and HRMS_REC_CD_ADDRESSDTL.CAND_ADD_TYPE='C' ";
			Object[][] candCurrAdd = getSqlModel().getSingleResult(
					tempAddrsQuery);

			if (candCurrAdd != null && candCurrAdd.length > 0) {
				resumeApproval.setCity(String.valueOf(candCurrAdd[0][0]));
				resumeApproval.setState(String.valueOf(candCurrAdd[0][1]));
				resumeApproval.setCountry(String.valueOf(candCurrAdd[0][2]));
				resumeApproval.setCityCode(String.valueOf(candCurrAdd[0][3]));
				resumeApproval.setStateCode(String.valueOf(candCurrAdd[0][4]));
				resumeApproval
						.setCountryCode(String.valueOf(candCurrAdd[0][5]));
				if (String.valueOf(candCurrAdd[0][6]).equals("")
						|| String.valueOf(candCurrAdd[0][6]).equals("null")) {
					resumeApproval.setAddress("");
				} else {
					resumeApproval.setAddress(String.valueOf(candCurrAdd[0][6])
							.trim());
				}
				if (String.valueOf(candCurrAdd[0][7]).equals("null")
						|| String.valueOf(candCurrAdd[0][7]).equals("")
						|| String.valueOf(candCurrAdd[0][7]).equals(" ")) {
					resumeApproval.setPincode("");
				} else {
					resumeApproval
							.setPincode(String.valueOf(candCurrAdd[0][7]));
				}

			}

			String permAddrsQuery = " SELECT NVL(HRMS_LOCATION.LOCATION_NAME,' '),NVL(state.LOCATION_NAME,' '),NVL(country.LOCATION_NAME,' '),HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY,NVL(HRMS_REC_CD_ADDRESSDTL.CAND_ADD,' '),HRMS_REC_CD_ADDRESSDTL.CAND_ADD_PINCODE FROM HRMS_LOCATION "
					+ " LEFT JOIN HRMS_REC_CD_ADDRESSDTL ON(HRMS_LOCATION.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY)"
					+ " LEFT JOIN HRMS_LOCATION state on(state.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE)"
					+ " LEFT JOIN HRMS_LOCATION country on(country.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY)"
					+ " where HRMS_REC_CD_ADDRESSDTL.CAND_CODE="
					+ resumeApproval.getCandCode()
					+ " and HRMS_REC_CD_ADDRESSDTL.CAND_ADD_TYPE='P' ";
			Object[][] permAdd = getSqlModel().getSingleResult(permAddrsQuery);
			if (permAdd != null && permAdd.length > 0) {
				resumeApproval.setCity1(String.valueOf(permAdd[0][0]));
				resumeApproval.setState1(String.valueOf(permAdd[0][1]));
				resumeApproval.setCountry1(String.valueOf(permAdd[0][2]));
				resumeApproval.setCityCode1(String.valueOf(permAdd[0][3]));
				resumeApproval.setStateCode1(String.valueOf(permAdd[0][4]));
				resumeApproval.setCountryCode1(String.valueOf(permAdd[0][5]));
				if (String.valueOf(permAdd[0][6]).equals("")
						|| String.valueOf(permAdd[0][6]).equals("null")) {
					resumeApproval.setAddress1("");
				} else {
					resumeApproval.setAddress1(String.valueOf(permAdd[0][6])
							.trim());
				}
				resumeApproval
						.setAddress1(String.valueOf(permAdd[0][6]).trim());
				if (String.valueOf(permAdd[0][7]).equals("null")
						|| String.valueOf(permAdd[0][7]).equals("")
						|| String.valueOf(permAdd[0][7]).equals(" ")) {
					resumeApproval.setPincode1("");
				} else {
					resumeApproval.setPincode1(String.valueOf(permAdd[0][7]));
				}
			}
			String query = "SELECT  NVL(CAND_FIRST_NAME,' '), NVL(CAND_MID_NAME,' '), NVL(CAND_LAST_NAME,' '),TO_CHAR(CAND_DOB,'DD-MM-YYYY'), CAND_GENDER, "
					+ " CAND_MART_STATUS, CAND_OFF_PHONE, CAND_MOB_PHONE, CAND_RES_PHONE, CAND_EMAIL_ID, "
					+ " CAND_EMPLOYED, CAND_RELOCATION, CAND_EXP_YEAR, CAND_EXP_MONTH, CAND_DOYOUKNOW, "
					+ " NVL(CAND_REF_EMPID,' '),DECODE(CAND_GENDER,'M','Male','F','Female','O','Other'),DECODE(CAND_MART_STATUS,'S','Single','M','Married','D','Divorced'),"
					+ " NVL(CAND_RESUME,' '),NVL(CAND_PHOTO,' '),' ',' ',NVL(CAND_REF_EMPID,' '),CAND_CURR_CTC,CAND_EXPC_CTC,"
					+ " CAND_OTHER_INFO,NVL(CAND_PASSPORT_NO,' '),NVL(CAND_PAN_NO,' '),NVL(TITLE_NAME,''), CAND_TITLE_CODE,NVL(CAND_COMPANY,' '),NVL(CAND_POSITION,' '),NVL(CAND_LOCATION,' '),CAND_RESUME_APPROVAL_FLAG FROM HRMS_REC_CAND_DATABANK "
					// "LEFT JOIN HRMS_EMP_OFFC
					// ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_CAND_DATABANK.CAND_REF_EMPID)"+
					+ " LEFT JOIN HRMS_TITLE ON(TITLE_CODE=CAND_TITLE_CODE)"
					+ " WHERE CAND_CODE=" + resumeApproval.getCandCode();
			String exp = "";
			Object[][] candDet = getSqlModel().getSingleResult(query);
			if (candDet != null) {

				String yr = String.valueOf(candDet[0][12]);
				String mn = String.valueOf(candDet[0][13]);

				double mon;
				double year;
				if (yr.equals("null") || yr.equals("")) {
					year = 0;
				} else {
					year = Double.parseDouble(yr);
				}
				if (mn.equals("null") || mn.equals("")) {
					mon = 0;
				} else {
					mon = Double.parseDouble(mn);
				}

				if (mon != 0 && year != 0) {
					if (year > 1 && mon > 1) {

						exp += year + " " + "Years " + mon + " " + "Months";
					} else if (!(year > 1 && mon > 1)) {

						exp += year + " " + " Year " + mon + " " + "Month";
					} else if (year > 1 && (!(mon > 1))) {

						exp += year + " " + " Years " + mon + " " + "Month";
					} else if ((!(year > 1)) && mon > 1) {

						exp += year + " " + " Year " + mon + " " + "Months";
					}
				} else {

					if (year > 1) {

						exp += year + " " + "Years ";
					} else if (!(year > 1)) {

						exp += year + " " + " Year ";
					}

				}

				resumeApproval.setExpView(exp);

				if (String.valueOf(candDet[0][0]).equals("null"))
					resumeApproval.setFirstName("");
				else
					resumeApproval.setFirstName(String.valueOf(candDet[0][0]));

				if (String.valueOf(candDet[0][1]).equals("null"))
					resumeApproval.setMiddleName("");
				else
					resumeApproval.setMiddleName(String.valueOf(candDet[0][1]));

				if (String.valueOf(candDet[0][2]).equals("null"))
					resumeApproval.setLastName("");
				else
					resumeApproval.setLastName(String.valueOf(candDet[0][2]));

				resumeApproval.setDob(checkNull(String.valueOf(candDet[0][3])));
				resumeApproval.setGender(checkNull(String
						.valueOf(candDet[0][4])));
				resumeApproval.setMaritalStatus(checkNull(String
						.valueOf(candDet[0][5])));
				if (String.valueOf(candDet[0][6]).equals("null")) {
					resumeApproval.setOffPhone("");
				} else {
					resumeApproval.setOffPhone(String.valueOf(candDet[0][6]));
				}
				if (String.valueOf(candDet[0][7]).equals("null")) {
					resumeApproval.setMobile("");
				} else {
					resumeApproval.setMobile(String.valueOf(candDet[0][7]));
				}
				if (String.valueOf(candDet[0][8]).equals("null"))
					resumeApproval.setResPhone("");
				else
					resumeApproval.setResPhone(String.valueOf(candDet[0][8]));
				if (String.valueOf(candDet[0][9]).equals("null"))
					resumeApproval.setEmail("");
				else
					resumeApproval.setEmail(String.valueOf(candDet[0][9]));
				if (String.valueOf(candDet[0][10]).equals("null"))
					resumeApproval.setWheYouEmp("");
				else
					resumeApproval.setWheYouEmp(String.valueOf(candDet[0][10]));

				if (String.valueOf(candDet[0][11]).equals("null")
						|| String.valueOf(candDet[0][11]).equals(""))
					resumeApproval.setRelocate("");
				else
					resumeApproval.setRelocate(String.valueOf(candDet[0][11]));
				if (String.valueOf(candDet[0][12]).equals("null")
						|| String.valueOf(candDet[0][12]).equals(""))
					resumeApproval.setMinExp("");
				else
					resumeApproval.setMinExp(String.valueOf(candDet[0][12]));
				if (String.valueOf(candDet[0][13]).equals("null"))
					resumeApproval.setMaxExp("");
				else
					resumeApproval.setMaxExp(String.valueOf(candDet[0][13]));

				if (String.valueOf(candDet[0][14]).equals("null"))
					resumeApproval.setDoYou("");
				else
					resumeApproval.setDoYou(String.valueOf(candDet[0][14]));

				if (String.valueOf(candDet[0][15]).equals("null"))
					resumeApproval.setRefEmpId("");
				else
					resumeApproval.setRefEmpId(String.valueOf(candDet[0][15]));

				resumeApproval.setGenderView(checkNull(String
						.valueOf(candDet[0][16])));
				resumeApproval.setMarriageView(checkNull(String
						.valueOf(candDet[0][17])));
				resumeApproval
						.setUploadFileName(String.valueOf(candDet[0][18]));
				resumeApproval.setUploadPhoto(String.valueOf(candDet[0][19]));
				resumeApproval.setRefEmpTok(String.valueOf(candDet[0][20])
						.trim());
				resumeApproval.setRefEmpName(String.valueOf(candDet[0][22])
						.trim());
				// resumeApproval.setRefEmpId(String.valueOf(candDet[0][22]).trim());
				if (String.valueOf(candDet[0][23]).trim().equals("null")
						|| String.valueOf(candDet[0][23]).trim().equals("")) {
					resumeApproval.setCurrentCtc("");
				} else {
					resumeApproval.setCurrentCtc(String.valueOf(candDet[0][23])
							.trim());
				}

				if (String.valueOf(candDet[0][24]).trim().equals("null")
						|| String.valueOf(candDet[0][24]).trim().equals("null")) {
					resumeApproval.setExpectedCtc("");
				} else {
					resumeApproval.setExpectedCtc(String
							.valueOf(candDet[0][24]).trim());
				}

				if (String.valueOf(candDet[0][25]).trim().equals("")
						|| String.valueOf(candDet[0][25]).trim().equals("null")) {
					resumeApproval.setOtherInfo("");
				} else {
					resumeApproval.setOtherInfo(String.valueOf(candDet[0][25])
							.trim());
				}

				if (String.valueOf(candDet[0][26]).trim().equals("")
						|| String.valueOf(candDet[0][26]).trim().equals("null")) {
					resumeApproval.setPassport("");
				} else {
					resumeApproval.setPassport(String.valueOf(candDet[0][26])
							.trim());
				}

				if (String.valueOf(candDet[0][27]).trim().equals("")
						|| String.valueOf(candDet[0][27]).trim().equals("null")) {
					resumeApproval.setPanNo("");
				} else {
					resumeApproval.setPanNo(String.valueOf(candDet[0][27])
							.trim());
				}

				if (String.valueOf(candDet[0][28]).trim().equals("")
						|| String.valueOf(candDet[0][28]).trim().equals("null")) {
					resumeApproval.setTitleName("");
				} else {
					resumeApproval.setTitleName(String.valueOf(candDet[0][28])
							.trim());
				}
				if (String.valueOf(candDet[0][29]).trim().equals("")
						|| String.valueOf(candDet[0][29]).trim().equals("null")) {
					resumeApproval.setTitleCode("");
				} else {
					resumeApproval.setTitleCode(String.valueOf(candDet[0][29])
							.trim());
				}
				if (String.valueOf(candDet[0][14]).equals("Y")) {// do u know
																	// anybody
					resumeApproval.setRadioFlag("true");
				} else {
					resumeApproval.setRadioFlag("false");
				}
				logger.info("val of whether u emp"
						+ String.valueOf(candDet[0][10]));
				if (String.valueOf(candDet[0][10]).equals("Y")) {// Whether u
																	// employed
																	// earlier
																	// in the
																	// group
					resumeApproval.setRadioFlag1("true");
				} else {
					resumeApproval.setRadioFlag1("false");
				}
				logger.info("val of relocation u emp"
						+ String.valueOf(candDet[0][11]));
				if (String.valueOf(candDet[0][11]).equals("Y")) {// Relocate
					resumeApproval.setRadioFlag2("true");
				} else {
					resumeApproval.setRadioFlag2("false");
				}

				resumeApproval.setCandidateCompany(checkNull(String.valueOf(
						candDet[0][30]).trim()));

				resumeApproval.setCandidatePosition(checkNull(String.valueOf(
						candDet[0][31]).trim()));

				resumeApproval.setCurrentLocation(checkNull(String.valueOf(
						candDet[0][32]).trim()));

				resumeApproval.setCheckStatus(checkNull(String.valueOf(
						candDet[0][33]).trim()));

				String photo = resumeApproval.getUploadPhoto();
				request.setAttribute("photo", photo);
			}
		} catch (RuntimeException e) {
			logger.info("Exception in searchCandDet method", e);
		}
	}

	/**
	 * @purpose : To retrive Candidate Qualification Details in the second page
	 */
	public void retCandQual(ResumeApproval resumeApproval,
			HttpServletRequest request) {
		try {
			String query = "SELECT  CAND_DTL_CODE, CAND_QUA_CODE,NVL(QUA_ABBR,' '),CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' "
					+ " WHEN QUA_LEVEL='DI' THEN 'Diploma' WHEN QUA_LEVEL='GR' THEN 'Graduate' WHEN QUA_LEVEL='PG' THEN 'Post Graduate'"
					+ " WHEN QUA_LEVEL='PH' THEN 'Phd' WHEN QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END,CAND_SPEC_CODE,NVL(SPEC_ABBR,' '), NVL(CAND_ESTB_NAME,' '), "
					+ " CAND_YEAR_PASS, CAND_PERC_MARKS FROM HRMS_REC_CD_QUADTL "
					+ " LEFT JOIN HRMS_QUA ON  HRMS_QUA.QUA_ID = HRMS_REC_CD_QUADTL.CAND_QUA_CODE"
					+ " LEFT JOIN HRMS_SPECIALIZATION ON HRMS_SPECIALIZATION.SPEC_ID = HRMS_REC_CD_QUADTL.CAND_SPEC_CODE "
					+ " WHERE CAND_CODE=" + resumeApproval.getCandCode();

			Object[][] candQualDets = getSqlModel().getSingleResult(query);
			if (candQualDets != null && candQualDets.length > 0) {
				resumeApproval.setQualiFlag("true");
				Object[][] qualObj = new Object[candQualDets.length][5];
				ArrayList list = new ArrayList();
				for (int i = 0; i < candQualDets.length; i++) {
					ResumeApproval loopBean = new ResumeApproval();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setQualDetCode(String.valueOf(String
							.valueOf(candQualDets[i][1])));
					qualObj[i][0] = String.valueOf(String
							.valueOf(candQualDets[i][2]));// Qua Name
					qualObj[i][1] = String.valueOf(String
							.valueOf(candQualDets[i][3]));// Qua Level
					qualObj[i][2] = String.valueOf(String
							.valueOf(candQualDets[i][5]));// Specialization
															// name
					qualObj[i][3] = String.valueOf(String
							.valueOf(candQualDets[i][1]));// Qualification Id
					qualObj[i][4] = String.valueOf(String
							.valueOf(candQualDets[i][4]));// Specialization
															// Code
					loopBean.setEstbName(String.valueOf(String
							.valueOf(candQualDets[i][6])));
					if (String.valueOf(candQualDets[i][7]).equals("null")
							|| String.valueOf(candQualDets[i][7]).equals("")) {
						loopBean.setYearofPass("");
					} else {
						loopBean.setYearofPass(String.valueOf(String
								.valueOf(candQualDets[i][7])));
					}
					loopBean.setPercMarks(String.valueOf(String
							.valueOf(candQualDets[i][8])));
					list.add(loopBean);
				}// end of if statement
				request.setAttribute("qualObj", qualObj);
				resumeApproval.setQualList(list);
			} else {
				resumeApproval.setQualiFlag("false");
			}
		} catch (Exception e) {
			logger.info("Exception in searchRefDtl method", e);
		}
	}

	/**
	 * @purpose : To retrive and Candidate information
	 */
	public void retCandSkill(ResumeApproval resumeApproval,
			HttpServletRequest request) {
		try {
			String query = "SELECT CAND_DTL_CODE ,CAND_SF_CODE,NVL(SKILL_NAME,' '), CAND_EXP FROM HRMS_REC_CD_SKILLFUNCDTL "
					+ " LEFT JOIN HRMS_SKILL ON  HRMS_SKILL.SKILL_ID = HRMS_REC_CD_SKILLFUNCDTL.CAND_SF_CODE"
					+
					/*
					 * " INNER JOIN HRMS_EMP_OFFC OFFC1 ON OFFC1.EMP_ID =
					 * HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER " + " INNER JOIN
					 * HRMS_EMP_OFFC OFFC ON OFFC.EMP_ID =
					 * HRMS_REC_REQS_HDR.REQS_APPLIED_BY " + " INNER JOIN
					 * HRMS_RANK ON HRMS_RANK.RANK_ID =
					 * HRMS_REC_REQS_HDR.REQS_POSITION " + " INNER JOIN
					 * HRMS_DIVISION ON HRMS_DIVISION.DIV_ID =
					 * HRMS_REC_REQS_HDR.REQS_DIVISION " + " INNER JOIN
					 * HRMS_DEPT ON HRMS_DEPT.DEPT_ID =
					 * HRMS_REC_REQS_HDR.REQS_DEPT " + " INNER JOIN HRMS_CENTER
					 * ON HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH " +
					 */
					" WHERE CAND_CODE="
					+ resumeApproval.getCandCode()
					+ "AND CAND_FIELD_TYPE='S'";

			Object[][] candSkillDets = getSqlModel().getSingleResult(query);
			if (candSkillDets != null && candSkillDets.length > 0) {
				resumeApproval.setSkillFlag("true");
				Object[][] skillObj = new Object[candSkillDets.length][2];
				ArrayList list = new ArrayList();
				for (int i = 0; i < candSkillDets.length; i++) {
					ResumeApproval loopBean = new ResumeApproval();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setSkillDtlId(String.valueOf(String
							.valueOf(candSkillDets[i][0])));
					// loopBean.setSkillCode(String.valueOf(String.valueOf(candSkillDets[i][1])));
					skillObj[i][0] = (String.valueOf(String.valueOf(
							candSkillDets[i][2]).trim()));
					skillObj[i][1] = String.valueOf(String
							.valueOf(candSkillDets[i][1]));
					if (String.valueOf(candSkillDets[i][3]).equals("null")
							|| String.valueOf(candSkillDets[i][3]).equals("")) {
						loopBean.setSkillExp("");
					} else {
						loopBean.setSkillExp(String.valueOf(String.valueOf(
								candSkillDets[i][3]).trim()));
					}

					list.add(loopBean);
				}// end of if statement
				request.setAttribute("skillObj", skillObj);
				resumeApproval.setSkillList(list);
			} else {
				resumeApproval.setSkillFlag("false");
			}
		} catch (Exception e) {
			logger.info("Exception in searchRefDtl method", e);
		}
	}

	/**
	 * @purpose : To retrive and Candidate information
	 */
	public void retCandFunc(ResumeApproval resumeApproval,
			HttpServletRequest request) {
		try {
			String query = "SELECT CAND_DTL_CODE ,CAND_SF_CODE,NVL(FUNC_DOMAIN_NAME,' '), CAND_EXP FROM HRMS_REC_CD_SKILLFUNCDTL "
					+ " LEFT JOIN HRMS_FUNC_DOMAIN_MASTER ON HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE  = HRMS_REC_CD_SKILLFUNCDTL.CAND_SF_CODE"
					+
					/*
					 * " INNER JOIN HRMS_EMP_OFFC OFFC1 ON OFFC1.EMP_ID =
					 * HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER " + " INNER JOIN
					 * HRMS_EMP_OFFC OFFC ON OFFC.EMP_ID =
					 * HRMS_REC_REQS_HDR.REQS_APPLIED_BY " + " INNER JOIN
					 * HRMS_RANK ON HRMS_RANK.RANK_ID =
					 * HRMS_REC_REQS_HDR.REQS_POSITION " + " INNER JOIN
					 * HRMS_DIVISION ON HRMS_DIVISION.DIV_ID =
					 * HRMS_REC_REQS_HDR.REQS_DIVISION " + " INNER JOIN
					 * HRMS_DEPT ON HRMS_DEPT.DEPT_ID =
					 * HRMS_REC_REQS_HDR.REQS_DEPT " + " INNER JOIN HRMS_CENTER
					 * ON HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH " +
					 */
					" WHERE CAND_CODE="
					+ resumeApproval.getCandCode()
					+ " AND CAND_FIELD_TYPE='F'";

			Object[][] candFuncDets = getSqlModel().getSingleResult(query);
			if (candFuncDets != null && candFuncDets.length > 0) {
				resumeApproval.setDomFlag("true");
				Object[][] funcObj = new Object[candFuncDets.length][2];
				ArrayList list = new ArrayList();
				for (int i = 0; i < candFuncDets.length; i++) {
					ResumeApproval loopBean = new ResumeApproval();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setFuncDtlId(String.valueOf(String
							.valueOf(candFuncDets[i][0])));
					// loopBean.setFuncCode(String.valueOf(String.valueOf(candFuncDets[i][1])));
					funcObj[i][0] = (String.valueOf(String.valueOf(
							candFuncDets[i][2]).trim()));
					funcObj[i][1] = (String.valueOf(String
							.valueOf(candFuncDets[i][1])));
					if (String.valueOf(candFuncDets[i][3]).equals("")
							|| String.valueOf(candFuncDets[i][3])
									.equals("null")) {
						loopBean.setFuncExp("");
					} else {
						loopBean.setFuncExp(String.valueOf(String.valueOf(
								candFuncDets[i][3]).trim()));
					}

					list.add(loopBean);
				}// end of if statement
				request.setAttribute("funcObj", funcObj);
				resumeApproval.setFuncList(list);
			} else {
				resumeApproval.setDomFlag("false");
			}
		} catch (Exception e) {
			logger.info("Exception in searchRefDtl method", e);
		}
	}

	/**
	 * @purpose : To retrive and Candidate information
	 */
	public void retCandExp(ResumeApproval resumeApproval,
			HttpServletRequest request) {
		try {
			String query = "SELECT  CAND_DTL_CODE,NVL(CAND_EMPLOYER_NAME,' '),NVL(CAND_EMP_PORF,' '), NVL(TO_CHAR(CAND_RELV_DATE,'DD-MM-YYYY'),' '), NVL(TO_CHAR(CAND_JOIN_DATE,'DD-MM-YYYY'),' '), NVL(CAND_JOB_DESC,' '), "
					+ " ' ',CAND_INDUS_TYPE,NVL(INDUS_ABBRIVIATION,' '),NVL(CAND_SPEC_ACHV,' '), CAND_CTC "
					+ "  FROM HRMS_REC_CD_EXPDTL "
					+
					// " LEFT JOIN HRMS_JOB_DESCRIPTION ON
					// HRMS_REC_CD_EXPDTL.CAND_JOB_DESC =
					// HRMS_JOB_DESCRIPTION.JOB_DESC_CODE " +
					" LEFT JOIN HRMS_INDUS_TYPE ON HRMS_REC_CD_EXPDTL.CAND_INDUS_TYPE = HRMS_INDUS_TYPE.INDUS_CODE "
					+
					/*
					 * " INNER JOIN HRMS_EMP_OFFC OFFC1 ON OFFC1.EMP_ID =
					 * HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER " + " INNER JOIN
					 * HRMS_EMP_OFFC OFFC ON OFFC.EMP_ID =
					 * HRMS_REC_REQS_HDR.REQS_APPLIED_BY " + " INNER JOIN
					 * HRMS_RANK ON HRMS_RANK.RANK_ID =
					 * HRMS_REC_REQS_HDR.REQS_POSITION " + " INNER JOIN
					 * HRMS_DIVISION ON HRMS_DIVISION.DIV_ID =
					 * HRMS_REC_REQS_HDR.REQS_DIVISION " + " INNER JOIN
					 * HRMS_DEPT ON HRMS_DEPT.DEPT_ID =
					 * HRMS_REC_REQS_HDR.REQS_DEPT " + " INNER JOIN HRMS_CENTER
					 * ON HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH " +
					 */
					" WHERE CAND_CODE="
					+ resumeApproval.getCandCode()
					+ " ORDER BY CAND_JOIN_DATE";
			Object[][] candExpDets = getSqlModel().getSingleResult(query);

			if (candExpDets != null && candExpDets.length > 0) {
				resumeApproval.setExpFlag("true");
				Object[][] expObj = new Object[candExpDets.length][3];
				ArrayList list = new ArrayList();
				for (int i = 0; i < candExpDets.length; i++) {
					ResumeApproval loopBean = new ResumeApproval();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setExpDtlId(String.valueOf(String
							.valueOf(candExpDets[i][0])));
					loopBean.setEmprName(String.valueOf(
							String.valueOf(candExpDets[i][1])).trim());
					loopBean.setLastJobPro(String.valueOf(
							String.valueOf(candExpDets[i][2])).trim());
					loopBean.setRelvDate(String.valueOf(
							String.valueOf(candExpDets[i][3])).trim());
					loopBean.setJoinDate(String.valueOf(
							String.valueOf(candExpDets[i][4])).trim());
					expObj[i][0] = String.valueOf(String
							.valueOf(candExpDets[i][5]));// Candidate Job
															// Description
					loopBean.setIndsId(String.valueOf(String
							.valueOf(candExpDets[i][7])));
					expObj[i][1] = String.valueOf(String
							.valueOf(candExpDets[i][8]));// Industry Type
					expObj[i][2] = String.valueOf(String
							.valueOf(candExpDets[i][7]));// Industry Id
					loopBean.setSpecAch(String.valueOf(String
							.valueOf(candExpDets[i][9])));
					if (String.valueOf(candExpDets[i][10]).equals("null")
							|| String.valueOf(candExpDets[i][10]).equals("")) {
						loopBean.setCtcExp("");
					} else {
						loopBean.setCtcExp(String.valueOf(String
								.valueOf(candExpDets[i][10])));
					}
					list.add(loopBean);
				}// end of if statement
				request.setAttribute("expObj", expObj);
				resumeApproval.setExpList(list);
			} else {
				resumeApproval.setExpFlag("false");
			}
		} catch (Exception e) {
			logger.info("Exception in searchRefDtl method", e);
		}
	}

	public boolean updateFirst(ResumeApproval bean) {
		boolean result = false;
		ArrayList<Object> candidateList = new ArrayList<Object>();
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysDate = formater.format(date);
		/*
		 * String query="SELECT TO_CHAR(CAND_POSTING_DATE,'DD-MM-YYYY') FROM
		 * HRMS_REC_CAND_DATABANK where cand_code="+bean.getCandCode();
		 * Object[][] dt= getSqlModel().getSingleResult(query);
		 */
		Object[][] addObj = new Object[1][29];
		// cand title added
		addObj[0][0] = bean.getFirstName();
		addObj[0][1] = bean.getMiddleName();
		addObj[0][2] = bean.getLastName();
		addObj[0][3] = bean.getDob();
		addObj[0][4] = bean.getGender();
		addObj[0][5] = bean.getMaritalStatus();

		if (bean.getMinExp().equals("") || bean.getMinExp().equals("null")
				|| bean.getMinExp().equals(" ")) {
			addObj[0][6] = String.valueOf("0");
		} else {
			addObj[0][6] = bean.getMinExp();
		}

		if (bean.getMaxExp().equals("") || bean.getMaxExp().equals("null")
				|| bean.getMaxExp().equals(" ")) {
			addObj[0][7] = String.valueOf("0");
		} else {
			addObj[0][7] = bean.getMaxExp();
		}

		// addObj[0][6] = bean.getMinExp();
		// addObj[0][7] = bean.getMaxExp();
		if (bean.getCurrentCtc().equals("null")
				|| bean.getCurrentCtc().equals("")) {
			addObj[0][8] = String.valueOf("");
		} else {
			addObj[0][8] = bean.getCurrentCtc();
		}

		if (bean.getExpectedCtc().equals("null")
				|| bean.getExpectedCtc().equals("")) {
			addObj[0][9] = String.valueOf("");
		} else {
			addObj[0][9] = bean.getExpectedCtc();
		}

		addObj[0][10] = bean.getResPhone();
		addObj[0][11] = bean.getMobile();
		addObj[0][12] = bean.getOffPhone();
		addObj[0][13] = bean.getEmail();
		addObj[0][14] = bean.getWheYouEmp();
		addObj[0][15] = bean.getRelocate();
		addObj[0][16] = bean.getDoYou();
		logger.info("Do u know" + bean.getDoYou());
		logger.info("Relocate" + bean.getRelocate());
		if (bean.getRadioEmp().equals("Y")) {
			bean.setRadioFlag("true");
		} else {
			bean.setRadioFlag("false");
		}
		addObj[0][17] = String.valueOf("");
		addObj[0][18] = sysDate;
		addObj[0][19] = bean.getUploadFileName();
		addObj[0][20] = bean.getUploadPhoto();
		addObj[0][21] = bean.getOtherInfo();
		addObj[0][22] = bean.getPassport();
		addObj[0][23] = bean.getPanNo();

		addObj[0][24] = bean.getTitleCode();

		addObj[0][25] = bean.getCandidateCompany().trim();
		addObj[0][26] = bean.getCandidatePosition().trim();

		addObj[0][27] = bean.getCurrentLocation().trim();

		addObj[0][28] = bean.getCandCode();

		checkDupRecord(bean);

		if (bean.getDupChkFlag().equals("false")) {
			result = getSqlModel().singleExecute(getQuery(9), addObj);

			String deleteQuery = "DELETE FROM HRMS_REC_CD_ADDRESSDTL WHERE CAND_CODE="
					+ bean.getCandCode();
			getSqlModel().singleExecute(deleteQuery);

			String delRefQuery = "DELETE FROM HRMS_REC_CD_REFDTL WHERE CAND_CODE="
					+ bean.getCandCode();
			getSqlModel().singleExecute(delRefQuery);

			Object[][] tempAddr = new Object[1][7];
			tempAddr[0][0] = bean.getCandCode();
			tempAddr[0][1] = bean.getCityCode();
			tempAddr[0][2] = bean.getStateCode();
			tempAddr[0][3] = bean.getCountryCode();
			tempAddr[0][4] = bean.getPincode();
			tempAddr[0][5] = bean.getAddress();
			tempAddr[0][6] = String.valueOf("C");

			Object[][] permAddr = new Object[1][7];
			permAddr[0][0] = bean.getCandCode();
			permAddr[0][1] = bean.getCityCode1();
			permAddr[0][2] = bean.getStateCode1();
			permAddr[0][3] = bean.getCountryCode1();
			permAddr[0][4] = bean.getPincode1();
			permAddr[0][5] = bean.getAddress1();
			permAddr[0][6] = String.valueOf("P");
			getSqlModel().singleExecute(getQuery(7), tempAddr);
			getSqlModel().singleExecute(getQuery(8), permAddr);

			// result1 = "false";
			// bean.setResult(result1);
			candidateList.add(bean);
			if (!(bean.getRefEmpName().equals("")
					|| bean.getRefEmpName().equals("null") || bean
					.getRefEmpName().equals(" "))) {

				Object[][] updateRef = new Object[1][2];
				updateRef[0][0] = bean.getRefEmpName();
				updateRef[0][1] = bean.getCandCode();
				result = getSqlModel().singleExecute(getQuery(10), updateRef);
			}
		}
		return result;
	}

	public boolean deleteRecord(ResumeApproval bean) {
		String deleteCand = "DELETE FROM HRMS_REC_CAND_DATABANK WHERE CAND_CODE="
				+ bean.getCandCode();
		boolean result = getSqlModel().singleExecute(deleteCand);
		if (result) {
			String delFunc = " DELETE FROM HRMS_REC_CD_SKILLFUNCDTL WHERE CAND_CODE="
					+ bean.getCandCode() + " AND CAND_FIELD_TYPE='F'";
			getSqlModel().singleExecute(delFunc);

			String delSkill = " DELETE FROM HRMS_REC_CD_SKILLFUNCDTL WHERE CAND_CODE="
					+ bean.getCandCode() + " AND CAND_FIELD_TYPE='S'";
			getSqlModel().singleExecute(delSkill);

			String deleteExp = "DELETE FROM HRMS_REC_CD_EXPDTL WHERE CAND_CODE="
					+ bean.getCandCode();
			getSqlModel().singleExecute(deleteExp);

			String deleteQua = "DELETE FROM HRMS_REC_CD_QUADTL WHERE CAND_CODE="
					+ bean.getCandCode();
			getSqlModel().singleExecute(deleteQua);

			String deleteRef = "DELETE FROM HRMS_REC_CD_REFDTL WHERE CAND_CODE="
					+ bean.getCandCode();
			getSqlModel().singleExecute(deleteRef);

			String deleteAddrs = "DELETE FROM HRMS_REC_CD_ADDRESSDTL WHERE CAND_CODE="
					+ bean.getCandCode();
			getSqlModel().singleExecute(deleteAddrs);
		}
		return result;

		// HRMS_REC_CD_SKILLFUNCDTL,HRMS_REC_CD_EXPDTL,HRMS_REC_CD_REFDTL,HRMS_REC_CD_ADDRESSDTL

	}

	/**
	 * @purpose : To retrive and Requisition header information
	 */
	public void searchRefDtl(ResumeApproval resumeApproval) {
		try {
			String query = "SELECT CAND_DTL_CODE, NVL(CAND_REFNAME,' '), NVL(CAND_PROFES,' '), NVL(CAND_CONTACT,' '), "
					+ " NVL(CAND_COMMENTS,' ') FROM HRMS_REC_CD_REFDTL"
					+ " WHERE CAND_CODE=" + resumeApproval.getCandCode();
			Object[][] candRefDets = getSqlModel().getSingleResult(query);
			if (candRefDets != null && candRefDets.length > 0) {
				resumeApproval.setRefFlag("true");
				ArrayList list = new ArrayList();
				for (int i = 0; i < candRefDets.length; i++) {
					ResumeApproval loopBean = new ResumeApproval();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setRefDtlCode(String.valueOf(String
							.valueOf(candRefDets[i][0])));
					loopBean.setRefName(String.valueOf(String
							.valueOf(candRefDets[i][1])));
					loopBean.setProfession(String.valueOf(String
							.valueOf(candRefDets[i][2])));
					loopBean.setContactDet(String.valueOf(String
							.valueOf(candRefDets[i][3])));
					loopBean.setRefComment(String.valueOf(String
							.valueOf(candRefDets[i][4])));
					list.add(loopBean);
				}// end of if statement

				resumeApproval.setRefList(list);
			} else {
				resumeApproval.setRefFlag("false");
			}
		} catch (RuntimeException e) {
			logger.info("Exception in searchRefDtl method", e);
		}
	}

	/**
	 * @purpose : For adding row in vacancy table
	 */
	public void addRowVac(ResumeApproval bean) {
		ArrayList list = new ArrayList();
		ResumeApproval bean1 = new ResumeApproval();

		bean1.setSrNo(String.valueOf(1));
		bean1.setRefName("");
		bean1.setProfession("");
		bean1.setContactDet("");
		bean1.setRefComment("");
		list.add(bean1);
		bean.setRefList(list);

	} // end of method

	/**
	 * @purpose : For adding row in qualification table
	 */
	public void addRowQual(ResumeApproval bean, HttpServletRequest request) {
		ArrayList list = new ArrayList();
		Object qualObj[][] = new Object[1][5];
		ResumeApproval bean1 = new ResumeApproval();

		bean1.setSrNo(String.valueOf(1));
		bean1.setQualDtlId("");
		qualObj[0][0] = "";
		bean1.setSpelDtlId("");
		qualObj[0][1] = "";
		qualObj[0][2] = "";
		qualObj[0][3] = "";
		qualObj[0][4] = "";
		bean1.setEstbName("");
		bean1.setYearofPass("");
		list.add(bean1);
		bean.setQualList(list);
		request.setAttribute("qualObj", qualObj);
	} // end of method

	/**
	 * @purpose : For adding row in qualification table
	 */
	public void addRowExp(ResumeApproval bean, HttpServletRequest request) {
		ArrayList list = new ArrayList();
		Object expObj[][] = new Object[1][3];
		ResumeApproval bean1 = new ResumeApproval();

		bean1.setSrNo(String.valueOf(1));
		bean1.setExpDtlId("");
		bean1.setEmprName("");
		bean1.setLastJobPro("");
		bean1.setJoinDate("");
		bean1.setRelvDate("");
		expObj[0][0] = "";
		bean1.setSpecAch("");
		expObj[0][1] = "";
		expObj[0][2] = "";
		bean1.setIndsId("");
		bean1.setCtcExp("");
		list.add(bean1);
		bean.setExpList(list);
		request.setAttribute("expObj", expObj);
	} // end of method

	/**
	 * @purpose : For adding row in qualification table
	 */
	public void addRowSkill(ResumeApproval bean, HttpServletRequest request) {
		ArrayList list = new ArrayList();
		Object skillObj[][] = new Object[1][2];
		ResumeApproval bean1 = new ResumeApproval();

		bean1.setSrNo(String.valueOf(1));
		bean1.setSkillDtlId("");
		skillObj[0][0] = "";
		skillObj[0][1] = "";
		bean1.setSkillCode("");
		bean1.setSkillExp("");
		list.add(bean1);
		bean.setSkillList(list);
		request.setAttribute("skillObj", skillObj);
	} // end of method

	/**
	 * @purpose : For adding row in qualification table
	 */
	public void addRowFunc(ResumeApproval bean, HttpServletRequest request) {
		ArrayList list = new ArrayList();
		Object funcObj[][] = new Object[1][2];
		ResumeApproval bean1 = new ResumeApproval();

		bean1.setSrNo(String.valueOf(1));
		bean1.setFuncDtlId("");
		funcObj[0][0] = "";
		funcObj[0][1] = "";
		bean1.setFuncCode("");
		bean1.setFuncExp("");
		list.add(bean1);
		bean.setFuncList(list);
		request.setAttribute("funcObj", funcObj);
	} // end of method

	/**
	 * @purpose : For adding row in qualification table
	 */

	public void saveQual(Object[][] qualObj, String candCode) {
		// boolean result=false;
		// String deleteQuery = "DELETE FROM HRMS_REC_CD_QUADTL WHERE
		// CAND_CODE="+candCode;
		// result = getSqlModel().singleExecute(deleteQuery);
		// if(result){
		// result =
		getSqlModel().singleExecute(getQuery(3), qualObj);
		// }

	} // end of method

	public void delQuali(String candCode) {
		String deleteQuery = "DELETE FROM HRMS_REC_CD_QUADTL WHERE CAND_CODE="
				+ candCode;
		getSqlModel().singleExecute(deleteQuery);
	}

	public void delExp(String candCode) {
		String deleteQuery = "DELETE FROM HRMS_REC_CD_EXPDTL WHERE CAND_CODE="
				+ candCode;
		getSqlModel().singleExecute(deleteQuery);
	}

	public void delSkill(String candCode) {
		String deleteQuery = "DELETE FROM HRMS_REC_CD_SKILLFUNCDTL WHERE CAND_CODE="
				+ candCode + " AND CAND_FIELD_TYPE='S'";
		getSqlModel().singleExecute(deleteQuery);
	}

	public void delFun(String candCode) {
		String deleteQuery = "DELETE FROM HRMS_REC_CD_SKILLFUNCDTL WHERE CAND_CODE="
				+ candCode + " AND CAND_FIELD_TYPE='F'";
		getSqlModel().singleExecute(deleteQuery);
	}

	/**
	 * @purpose : For adding row in qualification table
	 */
	public void saveSkill(Object[][] skillObj, String candCode) {
		// boolean result=false;
		// String deleteQuery = "DELETE FROM HRMS_REC_CD_SKILLFUNCDTL WHERE
		// CAND_CODE="+candCode+" AND CAND_FIELD_TYPE='S'";
		// result = getSqlModel().singleExecute(deleteQuery);
		// if(result){
		// result =
		getSqlModel().singleExecute(getQuery(5), skillObj);
		// }
	} // end of method

	/**
	 * @purpose : For adding row in qualification table
	 */
	public void saveExp(Object[][] expObj, String candCode) {
		try {
			// boolean result=false;
			// String deleteQuery = "DELETE FROM HRMS_REC_CD_EXPDTL WHERE
			// CAND_CODE="+candCode;
			// result = getSqlModel().singleExecute(deleteQuery);
			// if(result){
			getSqlModel().singleExecute(getQuery(4), expObj);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of method

	/**
	 * @purpose : For adding row in qualification table
	 */
	public void saveFunc(Object[][] funcObj, String candCode) {
		// boolean result=false;
		// String deleteQuery = "DELETE FROM HRMS_REC_CD_SKILLFUNCDTL WHERE
		// CAND_CODE="+candCode+" AND CAND_FIELD_TYPE='F'";
		// result = getSqlModel().singleExecute(deleteQuery);
		// if(result){
		// result =
		getSqlModel().singleExecute(getQuery(5), funcObj);
		// }
	} // end of method

	/**
	 * following function is called when the Export To Pdf or Export To Text
	 * report button is clicked.
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @param type
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, ResumeApproval bean, String type) {
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,
				"Candidate Databank Report");
		try {

			if (type.equals("Pdf")) {
				rg.setFName("Candidate Databank Report.Pdf");
			} else {
				rg.setFName("Candidate Databank Report.doc");
			}
			String candDetQuery = "SELECT  NVL(CAND_FIRST_NAME,' '), NVL(CAND_MID_NAME,' '), NVL(CAND_LAST_NAME,' '),TO_CHAR(CAND_DOB,'DD-MM-YYYY'), CAND_GENDER, "
					+ "CAND_MART_STATUS, CAND_OFF_PHONE, CAND_MOB_PHONE, CAND_RES_PHONE, CAND_EMAIL_ID, "
					+ "CASE WHEN CAND_EMPLOYED='Y' THEN 'Yes' ELSE 'No' END, CASE WHEN CAND_RELOCATION='Y' THEN 'Yes' ELSE 'No' END, NVL(CAND_EXP_YEAR,0), NVL(CAND_EXP_MONTH,0), CASE WHEN CAND_DOYOUKNOW='Y' THEN 'Yes' else 'No' end, "
					+ "CAND_REF_EMPID,DECODE(CAND_GENDER,'M','Male','F','Female','O','Other'),DECODE(CAND_MART_STATUS,'S','Single','M','Married','D','Divorced'),"
					+ " NVL(CAND_RESUME,' '),NVL(CAND_PHOTO,' '),' ',' ',CAND_REF_EMPID,CAND_CURR_CTC,CAND_EXPC_CTC,"
					+ " NVL(CAND_OTHER_INFO,' '),NVL(CAND_PASSPORT_NO,' '),NVL(CAND_PAN_NO,' ') FROM HRMS_REC_CAND_DATABANK "
					+ " WHERE CAND_CODE=" + bean.getCandCode();
			Object[][] candDets = getSqlModel().getSingleResult(candDetQuery);

			String permAddrsQuery = " SELECT NVL(HRMS_LOCATION.LOCATION_NAME,' '),NVL(state.LOCATION_NAME,' '),NVL(country.LOCATION_NAME,' '),HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY,NVL(HRMS_REC_CD_ADDRESSDTL.CAND_ADD,' '),HRMS_REC_CD_ADDRESSDTL.CAND_ADD_PINCODE FROM HRMS_LOCATION "
					+ " LEFT JOIN HRMS_REC_CD_ADDRESSDTL ON(HRMS_LOCATION.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY)"
					+ " LEFT JOIN HRMS_LOCATION state on(state.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE)"
					+ " LEFT JOIN HRMS_LOCATION country on(country.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY)"
					+ " where HRMS_REC_CD_ADDRESSDTL.CAND_CODE="
					+ bean.getCandCode()
					+ " and HRMS_REC_CD_ADDRESSDTL.CAND_ADD_TYPE='P' ";
			Object[][] permAdd = getSqlModel().getSingleResult(permAddrsQuery);

			String tempAddrsQuery = " SELECT NVL(HRMS_LOCATION.LOCATION_NAME,' '),NVL(state.LOCATION_NAME,' '),NVL(country.LOCATION_NAME,' '),HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE,HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY,NVL(HRMS_REC_CD_ADDRESSDTL.CAND_ADD,' '),HRMS_REC_CD_ADDRESSDTL.CAND_ADD_PINCODE FROM HRMS_LOCATION "
					+ " LEFT JOIN HRMS_REC_CD_ADDRESSDTL ON(HRMS_LOCATION.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY)"
					+ " LEFT JOIN HRMS_LOCATION state on(state.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_STATE)"
					+ " LEFT JOIN HRMS_LOCATION country on(country.LOCATION_CODE=HRMS_REC_CD_ADDRESSDTL.CAND_ADD_COUNTRY)"
					+ " where HRMS_REC_CD_ADDRESSDTL.CAND_CODE="
					+ bean.getCandCode()
					+ " and HRMS_REC_CD_ADDRESSDTL.CAND_ADD_TYPE='C' ";
			Object[][] candCurrAdd = getSqlModel().getSingleResult(
					tempAddrsQuery);

			String query = "SELECT ROWNUM, NVL(CAND_REFNAME,' '), NVL(CAND_PROFES,' '), NVL(CAND_CONTACT,' '), "
					+ " NVL(CAND_COMMENTS,' ') FROM HRMS_REC_CD_REFDTL"
					+ " WHERE CAND_CODE=" + bean.getCandCode();
			Object[][] candRefDets = getSqlModel().getSingleResult(query);
			String exp = "";
			int year = Integer.parseInt(String.valueOf(candDets[0][12]));
			int month = Integer.parseInt(String.valueOf(candDets[0][13]));
			if (year > 1 && month > 1) {
				exp += year + " Years " + month + " Months";
			} else if (year > 1 && !(month > 1)) {
				exp += year + " Years " + month + " Month";

			} else if (!(year > 1) && month > 1) {
				exp += year + " Year " + month + " Months";
			} else {
				exp += year + " Year " + month + " Month";
			}
			Object[][] candData = new Object[16][7];
			candData[0][0] = "First Name";
			candData[0][1] = ":";
			candData[0][2] = candDets[0][0];
			candData[0][3] = "";
			candData[0][4] = "";
			candData[0][5] = "";
			candData[0][6] = "";
			candData[1][0] = "Middle Name";
			candData[1][1] = ":";
			candData[1][2] = candDets[0][1];
			candData[1][3] = "";
			candData[1][4] = "";
			candData[1][5] = "";
			candData[1][6] = "";
			candData[2][0] = "Last Name";
			candData[2][1] = ":";
			candData[2][2] = candDets[0][2];
			candData[2][3] = "";
			candData[2][4] = "";
			candData[2][5] = "";
			candData[2][6] = "";
			candData[3][0] = "Date of Birth";
			candData[3][1] = ":";
			candData[3][2] = candDets[0][3];
			candData[3][3] = "";
			candData[3][4] = "";
			candData[3][5] = "";
			candData[3][6] = "";
			candData[4][0] = "Gender";
			candData[4][1] = ":";
			candData[4][2] = candDets[0][16];
			candData[4][3] = "";
			candData[4][4] = "";
			candData[4][5] = "";
			candData[4][6] = "";
			candData[5][0] = "Marital Status";
			candData[5][1] = ":";
			candData[5][2] = candDets[0][17];
			candData[5][3] = "";
			candData[5][4] = "";
			candData[5][5] = "";
			candData[5][6] = "";
			candData[6][0] = "Experience";
			candData[6][1] = ":";
			candData[6][2] = exp;
			candData[6][3] = "";
			candData[6][4] = "";
			candData[6][5] = "";
			candData[6][6] = "";
			candData[7][0] = "Current Address";
			candData[7][1] = ":";
			candData[7][2] = candCurrAdd[0][6];
			candData[7][3] = "";
			candData[7][4] = "Permanent Address";
			candData[7][5] = ":";
			if (permAdd != null && permAdd.length > 0) {
				candData[7][6] = String.valueOf(permAdd[0][6]).equals("")
						|| String.valueOf(permAdd[0][6]).equals("") ? " "
						: String.valueOf(permAdd[0][6]);
			} else {
				candData[7][6] = "";
			}
			candData[8][0] = "City";
			candData[8][1] = ":";
			candData[8][2] = candCurrAdd[0][0];
			candData[8][3] = "";
			candData[8][4] = "City";
			candData[8][5] = ":";
			if (permAdd != null && permAdd.length > 0) {
				candData[8][6] = String.valueOf(permAdd[0][0]).equals("")
						|| String.valueOf(permAdd[0][0]).equals("") ? " "
						: String.valueOf(permAdd[0][0]);
			} else {
				candData[8][6] = "";
			}

			candData[9][0] = "State";
			candData[9][1] = ":";
			candData[9][2] = candCurrAdd[0][1];
			candData[9][3] = "";
			candData[9][4] = "State";
			candData[9][5] = ":";
			if (permAdd != null && permAdd.length > 0) {
				candData[9][6] = String.valueOf(permAdd[0][1]).equals("")
						|| String.valueOf(permAdd[0][1]).equals("null") ? ""
						: String.valueOf(permAdd[0][1]);
			} else {
				candData[9][6] = "";
			}
			candData[10][0] = "Country";
			candData[10][1] = ":";
			candData[10][2] = candCurrAdd[0][2];
			candData[10][3] = "";
			candData[10][4] = "Country";
			candData[10][5] = ":";
			if (permAdd != null && permAdd.length > 0) {
				candData[10][6] = String.valueOf(permAdd[0][2]).equals("")
						|| String.valueOf(permAdd[0][2]).equals("null") ? ""
						: String.valueOf(permAdd[0][2]);
			} else {
				candData[10][6] = "";
			}
			candData[11][0] = "Pincode";
			candData[11][1] = ":";
			candData[11][2] = candCurrAdd[0][7];
			candData[11][3] = "";
			candData[11][4] = "Pincode";
			candData[11][5] = ":";
			if (permAdd != null && permAdd.length > 0) {
				candData[11][6] = String.valueOf(permAdd[0][7]).equals("")
						|| String.valueOf(permAdd[0][7]).equals("null") ? ""
						: String.valueOf(permAdd[0][7]);
			} else {
				candData[11][6] = "";
			}
			candData[12][0] = "Residence Phone";
			candData[12][1] = ":";
			candData[12][2] = candDets[0][8];
			candData[12][3] = "";
			candData[12][4] = "Mobile";
			candData[12][5] = ":";
			candData[12][6] = candDets[0][7];
			candData[13][0] = "Office Phone";
			candData[13][1] = ":";
			candData[13][2] = candDets[0][6];
			candData[13][3] = "";
			candData[13][4] = "Email Id";
			candData[13][5] = ":";
			candData[13][6] = candDets[0][9];
			candData[14][0] = "Current CTC in Lacs";
			candData[14][1] = ":";
			candData[14][2] = candDets[0][23];
			candData[14][3] = "";
			candData[14][4] = "Expected CTC in Lacs";
			candData[14][5] = ":";
			candData[14][6] = candDets[0][24];
			// candData[15][0]="Other
			// Information";candData[15][1]=":";candData[15][2]=candDets[0][25];candData[15][3]="";candData[15][4]="";candData[15][5]="";candData[15][6]="";
			int[] candCellWidth = { 9, 1, 10, 1, 9, 1, 11 };
			int[] candCellAlign = { 0, 0, 0, 0, 0, 0, 0 };
			if (type.equals("Pdf"))
				rg.addText("\n", 0, 0, 0);

			rg.addFormatedText("Candidate Databank Report", 6, 0, 1, 0);
			if (type.equals("Pdf"))
				rg.addText("\n\n\n", 0, 0, 0);

			rg.addFormatedText("Candidate Details :", 6, 0, 0, 0);
			if (type.equals("Pdf"))
				rg.addText("\n", 0, 0, 0);
			rg.tableBodyNoBorder(candData, candCellWidth, candCellAlign);

			Object[][] othrInf = new Object[5][3];
			int[] othrInfCellWidth = { 12, 1, 15 };
			int[] othrInfCellAlign = { 0, 0, 0 };

			othrInf[0][0] = "Other Information";
			othrInf[0][1] = ":";
			othrInf[0][2] = candDets[0][25];
			othrInf[1][0] = "Do you know anybody currently working with the group?";
			othrInf[1][1] = ":";
			othrInf[1][2] = candDets[0][14];
			if (String.valueOf(candDets[0][14]).equals("Yes")) {
				othrInf[2][0] = "Employee Details";
				othrInf[2][1] = ":";
				othrInf[2][2] = candDets[0][22];
			}
			othrInf[3][0] = "Whether you employed earlier in the group?";
			othrInf[3][1] = ":";
			othrInf[3][2] = candDets[0][10];
			othrInf[4][0] = "Relocation ";
			othrInf[4][1] = ":";
			othrInf[4][2] = candDets[0][11];
			rg.tableBodyNoBorder(othrInf, othrInfCellWidth, othrInfCellAlign);
			// Reference Details
			if (candRefDets != null && candRefDets.length > 0) {
				if (type.equals("Pdf"))
					rg.addText("\n", 0, 0, 0);

				rg.addFormatedText("Reference Details : ", 6, 0, 0, 0);
				String refCol[] = { "Sr No", "Reference Name", "Profession",
						"Contact Details", "Comments" };
				int[] refCellWidth = { 4, 10, 8, 7, 8 };
				int[] refCellAlign = { 1, 0, 0, 0, 0 };
				rg.tableBody(refCol, candRefDets, refCellWidth, refCellAlign);
				if (type.equals("Pdf"))
					rg.addText("\n", 0, 0, 0);

			}// End of if

			String qualQuery = "SELECT ROWNUM ,NVL(QUA_ABBR,' '),CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' "
					+ " WHEN QUA_LEVEL='DI' THEN 'Diploma' WHEN QUA_LEVEL='GR' THEN 'Graduate' WHEN QUA_LEVEL='PG' THEN 'Post Graduate'"
					+ " WHEN QUA_LEVEL='PH' THEN 'Phd' WHEN QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END,NVL(SPEC_ABBR,' '), NVL(CAND_ESTB_NAME,' '), "
					+ " CAND_YEAR_PASS, CAND_PERC_MARKS FROM HRMS_REC_CD_QUADTL "
					+ " LEFT JOIN HRMS_QUA ON  HRMS_QUA.QUA_ID = HRMS_REC_CD_QUADTL.CAND_QUA_CODE"
					+ " LEFT JOIN HRMS_SPECIALIZATION ON HRMS_SPECIALIZATION.SPEC_ID = HRMS_REC_CD_QUADTL.CAND_SPEC_CODE "
					+ " WHERE CAND_CODE=" + bean.getCandCode();

			Object[][] candQualDets = getSqlModel().getSingleResult(qualQuery);
			if (candQualDets != null && candQualDets.length > 0) {
				rg.addFormatedText("Qualification Details :", 6, 0, 0, 0);
				String qualCol[] = { "Sr No", "Qualification\nAbbreviation",
						"Qualification\nLevel", "Specialization\nAbbreviation",
						"Establishment\nName", "Year of\nPassing" };
				int[] qualCellWidth = { 3, 8, 8, 8, 8, 8 };
				int[] qualCellAlign = { 1, 0, 0, 0, 0, 0 };
				rg.tableBody(qualCol, candQualDets, qualCellWidth,
						qualCellAlign);
				if (type.equals("Pdf"))
					rg.addText("\n", 0, 0, 0);
			}

			String expQuery = "SELECT  ROWNUM,NVL(CAND_EMPLOYER_NAME,' '),NVL(CAND_EMP_PORF,' '),  NVL(TO_CHAR(CAND_JOIN_DATE,'DD-MM-YYYY'),' '),NVL(TO_CHAR(CAND_RELV_DATE,'DD-MM-YYYY'),' '), NVL(CAND_JOB_DESC,' '), "
					+ " NVL(CAND_SPEC_ACHV,' '),NVL(INDUS_NAME,' '), NVL(CAND_CTC,0) "
					+ "  FROM HRMS_REC_CD_EXPDTL "
					+ " LEFT JOIN HRMS_INDUS_TYPE ON HRMS_REC_CD_EXPDTL.CAND_INDUS_TYPE = HRMS_INDUS_TYPE.INDUS_CODE "
					+ " WHERE CAND_CODE=" + bean.getCandCode();
			Object[][] candExpDets = getSqlModel().getSingleResult(expQuery);
			if (candExpDets != null && candExpDets.length > 0) {
				String expCol[] = { "Sr No", "Employer Name",
						"Last Job\nProfession", "Joining\nDate",
						"Relieving\nDate", "Job\nDescription",
						"Special\nAchievement", "Industry Type", "CTC in Lacs" };
				int[] expCellWidth = { 3, 8, 8, 8, 8, 8, 8, 8, 8 };
				int[] expCellAlign = { 1, 0, 0, 0, 0, 0, 0, 0, 0 };
				rg.addFormatedText("Experience History :", 6, 0, 0, 0);
				rg.tableBody(expCol, candExpDets, expCellWidth, expCellAlign);
				if (type.equals("Pdf"))
					rg.addText("\n", 0, 0, 0);
			}
			String skillQuery = "SELECT ROWNUM,NVL(SKILL_NAME,' '), NVL(CAND_EXP,0) FROM HRMS_REC_CD_SKILLFUNCDTL "
					+ " LEFT JOIN HRMS_SKILL ON  HRMS_SKILL.SKILL_ID = HRMS_REC_CD_SKILLFUNCDTL.CAND_SF_CODE"
					+ " WHERE CAND_CODE="
					+ bean.getCandCode()
					+ "AND CAND_FIELD_TYPE='S'";
			Object[][] candSkillDets = getSqlModel()
					.getSingleResult(skillQuery);
			if (candSkillDets != null && candSkillDets.length > 0) {
				rg.addFormatedText("Skill Details :", 6, 0, 0, 0);
				String[] skillCol = { "Sr No", "Skill Name",
						"Experience in Years" };
				int[] skillCellWidth = { 5, 10, 10 };
				int[] skillCellALign = { 1, 0, 1 };
				rg.tableBody(skillCol, candSkillDets, skillCellWidth,
						skillCellALign);
				if (type.equals("Pdf"))
					rg.addText("\n", 0, 0, 0);
			}
			String funcQuery = "SELECT ROWNUM,NVL(FUNC_DOMAIN_NAME,' '), CAND_EXP FROM HRMS_REC_CD_SKILLFUNCDTL "
					+ " LEFT JOIN HRMS_FUNC_DOMAIN_MASTER ON HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE  = HRMS_REC_CD_SKILLFUNCDTL.CAND_SF_CODE"
					+ " WHERE CAND_CODE="
					+ bean.getCandCode()
					+ " AND CAND_FIELD_TYPE='F'";
			Object[][] candFuncDets = getSqlModel().getSingleResult(funcQuery);

			if (candFuncDets != null && candFuncDets.length > 0) {
				rg.addFormatedText("Domain/Functional Details :", 6, 0, 0, 0);
				String[] skillCol = { "Sr No", "Domain/Functional Name",
						"Experience in Years" };
				int[] skillCellWidth = { 5, 15, 10 };
				int[] skillCellALign = { 1, 0, 1 };
				rg.tableBody(skillCol, candFuncDets, skillCellWidth,
						skillCellALign);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rg.createReport(response);

	}

	public String getMessage(String key, String menuCode, String path) {
		String obj = "";
		try {
			LabelManagementModel model = new LabelManagementModel();
			HashMap hMap = (HashMap) context.getAttribute("common"
					+ session.getAttribute("session_pool"));
			obj = (String) hMap.get(key);
			if (obj == null || obj.equals(null)) {
				hMap = model.loadFormLabels(Integer.parseInt(menuCode), path);
				obj = (String) hMap.get(key);
			}
		} catch (Exception e) {
			return "Message Not Found";
		}
		if (obj == null)
			return "Message Not Found";
		return obj;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public boolean insertApprovalStatus(String CandCode, String status,
			HttpServletRequest request) {
		
		boolean result = false ;

		try {
			
			//INSERT INTO TMS_EXP_APPROVAL_DTL
			/*String insertQuery = " INSERT INTO HRMS_REC_CAND_DATABANK (CAND_CODE , CAND_RESUME_APPROVAL_FLAG) VALUES ("
					+ CandCode + ",'" + status + "')";*/
			String updateQuery = "";
		
				 updateQuery = " UPDATE HRMS_REC_CAND_DATABANK SET CAND_RESUME_APPROVAL_FLAG = '"
					+ status + "' WHERE CAND_CODE=" + CandCode;
		
			
			result = getSqlModel().singleExecute(updateQuery);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	
	/**
	 * @param bean
	 * @param status
	 * purpose:setting background application list(pending or conducted)
	 */
	public void setBackgroundList(ResumeApproval bean,String status,HttpServletRequest request) {
		String concatStr = "";
		String ConductQuery="";
		if(status.equals("Y")) // when status is Approved
		{ 
			System.out.println(" status ##########" + status);
		   ConductQuery = " SELECT CAND_CODE,CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,NVL(TO_CHAR(CAND_DOB,'dd-mm-yyyy'),' '),"
				+ "	NVL(CAND_EXP_YEAR,0), NVL(CAND_EXP_MONTH,0),TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'),"
				+ "		CASE WHEN CAND_SHORT_STATUS='S' THEN 'Short Listed' WHEN CAND_SHORT_STATUS='R' THEN 'Rejected' "
				+ "		WHEN CAND_SHORT_STATUS='N' THEN 'New' ELSE ' ' END,CAND_POSTING_DATE, "
				+ "		DECODE(CAND_REF_TYPE,'C','Consultant','O','Online','D','Direct','E','Employee Referral'),"
				+ "		CAND_RESUME_APPROVAL_FLAG "
				+ "		FROM HRMS_REC_CAND_DATABANK WHERE 1=1 AND CAND_RESUME_APPROVAL_FLAG = 'Y' ORDER BY CAND_POSTING_DATE DESC";
		   
		   System.out.println(" ConductQuery ##########" + ConductQuery.length());
		 bean.setConductflag(false);
		   
		   
		}else	// when status is rejected
	     {
			System.out.println(" status @@@@@@@@@" + status);                   
			ConductQuery = " SELECT CAND_CODE,CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,NVL(TO_CHAR(CAND_DOB,'dd-mm-yyyy'),' '),"
				+ "	NVL(CAND_EXP_YEAR,0), NVL(CAND_EXP_MONTH,0),TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'),"
				+ "		CASE WHEN CAND_SHORT_STATUS='S' THEN 'Short Listed' WHEN CAND_SHORT_STATUS='R' THEN 'Rejected' "
				+ "		WHEN CAND_SHORT_STATUS='N' THEN 'New' ELSE ' ' END,CAND_POSTING_DATE, "
				+ "		DECODE(CAND_REF_TYPE,'C','Consultant','O','Online','D','Direct','E','Employee Referral'),"
				+ "		CAND_RESUME_APPROVAL_FLAG "
				+ "		FROM HRMS_REC_CAND_DATABANK WHERE 1=1 AND CAND_RESUME_APPROVAL_FLAG = 'R' ORDER BY CAND_POSTING_DATE DESC";
			
	  bean.setConductflag(true);
	     }
			logger.info("ConductQuery"+ConductQuery);
		try {
			Object obj[][] = getSqlModel().getSingleResult(ConductQuery);
			
			
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					obj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			
		  
		
			ArrayList<Object> list = new ArrayList<Object>();
			
			if (obj != null && obj.length != 0) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					ResumeApproval bean1 = new ResumeApproval();
					bean1.setMode(true);
					bean1.setTotRecord(String
							.valueOf(obj.length));
					bean1.setSrNo(String.valueOf(i + 1));
					// serial number
					bean1.setCandCode(String.valueOf(obj[i][0]));
					// Candidate Code
					bean1.setHiddenCandCode(String.valueOf(obj[i][0]));
					if (String.valueOf(obj[i][1]).equals("null"))
						bean1.setFirstName("");
					else
						bean1.setFirstName(String.valueOf(obj[i][1])); // Name
					if (String.valueOf(obj[i][2]).equals("null"))
						bean1.setDob("");
					else
						bean1.setDob(String.valueOf(obj[i][2])); // date
																			// of
																			// Birth
					if (String.valueOf(obj[i][3]).equals("null"))
						obj[0][3] = "0";
					if (String.valueOf(obj[i][4]).equals("null"))
						obj[0][4] = "0";
					bean1.setExperience(checkNull(String
							.valueOf(obj[i][3]))
							+ " Year"
							+ "  "
							+ checkNull(String.valueOf(obj[i][4]))
							+ " Months"); // experience

					if (String.valueOf(obj[i][5]).equals("null")
							|| String.valueOf(obj[i][5]).equals("")
							|| String.valueOf(obj[i][5]).equals(" "))
						bean1.setPostingDate("");
					else
						bean1
								.setPostingDate(String
										.valueOf(obj[i][5])); // Posting
																		// date
					if (String.valueOf(obj[i][6]).equals("null"))
						bean1.setShortStatus("");
					else
						bean1
								.setShortStatus(String
										.valueOf(obj[i][6])); // short
																		// list
																		// status

					bean1.setResSource(checkNull(String
							.valueOf(obj[i][8])));
					list.add(bean1);
				} // for loop ends
				bean.setNoData("false"); // if there is no
													// application for selected
													// status set noDataFlag to
													// true
			} // if statement ends
			else {
				bean.setNoData("true"); // if there is no
													// application for selected
													// status set noDataFlag to
													// true
			}
			
			//bean.setBgpendinglistLength(String.valueOf(list.size()));
			
			bean.setLoadList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String[] dispArr = concatStr.split(",");
			request.setAttribute("dispArr", dispArr);
		} catch (Exception e) {
			// TODO: handle exception
		}

	 }

	
	
}