package org.paradyne.model.recruitment;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.bean.Recruitment.CandidateScreeningApproval;
import org.struts.action.recruitment.CandidateScreeningApprovalAction;

import javax.servlet.http.*;

import java.util.*;

/**
 * 
 * @author Pradeep Date:12-01-2009
 * 
 */

public class CandidateScreeningModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CandidateScreeningModel.class);

	/*
	 * following function is called to generate the list of records to be
	 * displayed when any button is clicked.
	 */
	public void getCandidateRecords(CandidateScreeningApproval bean,
			String status, HttpServletRequest request) {

		String query = "SELECT RESUME_REQS_CODE,NVL(REQS_NAME,' '),NVL(RANK_NAME,' '),CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME"
				+ " ,NVL(HRMS_REC_CAND_DATABANK.CAND_EXP_YEAR,0),CAND_CURR_CTC,CASE WHEN CAND_GENDER='M' THEN 'Male' WHEN CAND_GENDER='F' THEN 'Female' WHEN CAND_GENDER='O' THEN 'Other' ELSE ' ' END,"
				+ " EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(CAND_RESUME,' '),RESUME_STATUS,NVL(RESUME_COMMENTS,' '),NVL(HRMS_REC_CAND_DATABANK.CAND_EXP_MONTH,0),"
				+ " RESUME_CODE,RESUME_CAND_CODE ,RESUME_REC_EMPID FROM HRMS_REC_RESUME_BANK"
				+ " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)"
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
				+ " INNER JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_RESUME_BANK.RESUME_CAND_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_RESUME_BANK.RESUME_REC_EMPID)"
				+ " WHERE RESUME_STATUS="
				+ "'"
				+ status
				+ "' AND RESUME_APPR_EMPID = "
				+ bean.getUserEmpId()
				+ " ORDER BY REQS_DATE DESC";

		Object[][] data = getSqlModel().getSingleResult(query);
		if (status.equals("O") && data != null && data.length > 0) {
			bean.setSaveCandFlag("true");
		} else {
			bean.setSaveCandFlag("false");
		}
		ArrayList<Object> reqList = new ArrayList<Object>();
		if (data != null && data.length > 0) {
			bean.setModeLength("true");
		} else {
			bean.setModeLength("false");
			bean.setSelectname("1");
		}

		String[] pageIndex = Utility
				.doPaging(bean.getMyPage(), data.length, 20);
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

		if (data != null && data.length > 0) {

			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				CandidateScreeningApproval candScrApp = new CandidateScreeningApproval();
				candScrApp.setSaveCandFlag(bean.getSaveCandFlag());
				candScrApp.setResReqCode(String.valueOf(data[i][0]));// Resume
				// Requisition
				// Code
				candScrApp.setReqCode(String.valueOf(data[i][1]));// Requisition
				// Code
				candScrApp.setPosition(String.valueOf(data[i][2]));// Position
				candScrApp.setCandidateName(String.valueOf(data[i][3]));// Candidate
				// Name
				String exp = "";
				int year = Integer.parseInt(String.valueOf(data[i][4]));
				int month = Integer.parseInt(String.valueOf(data[i][11]));
				if (year > 1 && month > 1) {
					exp += year + " Years\n " + month + " Months";
				} else if (year > 1 && !(month > 1)) {
					exp += year + " Years\n " + month + " Month";

				} else if (!(year > 1) && month > 1) {
					exp += year + " Year\n " + month + " Months";
				} else {
					exp += year + " Year\n " + month + " Month";
				}

				candScrApp.setExp(String.valueOf(exp));// Exp

				if (String.valueOf(data[i][5]).equals("")
						|| String.valueOf(data[i][5]).equals("null")) {
					candScrApp.setCtc(String.valueOf(""));
				} else {
					candScrApp.setCtc(String.valueOf(data[i][5]));// Ctc
				}
				// candScrApp.setCtc(String.valueOf(data[i][5]));//Ctc
				candScrApp.setGender(String.valueOf(data[i][6]));// Gender
				candScrApp.setRecruiter(String.valueOf(data[i][7]));// Recruiter
				// Name
				candScrApp.setResume(String.valueOf(data[i][8]));// Resume
				// Name
				if (status.equals("O")) {
					candScrApp.setOpenFlag("true");
				} else {
					candScrApp.setOpenFlag("false");
				}

				candScrApp.setComment(String.valueOf(data[i][10]));// Comment
				candScrApp.setResCode(String.valueOf(data[i][12]));// Resume
				// Code
				candScrApp.setResCandCode(String.valueOf(data[i][13]));// Resume
				// Candidate
				// Code
				candScrApp.setItRecrruiterId(String.valueOf(data[i][14]));// recruiter
				// Code
				reqList.add(candScrApp);
			}

			bean.setList(reqList);
			bean.setTotalRecords(String.valueOf(data.length));
		} else {
			bean.setData("true");
			bean.setTotalRecords(String.valueOf(data.length));
		}
		if (!status.equals(bean.getHiddenStatusPage())) {
			bean.setHiddenStatusPage(status);
			bean.setMyPage("1");
		}

	}

	/**
	 * following function is called to update the resume status and comments
	 * 
	 * @param bean
	 * @param code
	 * @param comment
	 * @param status
	 * @return
	 */
	public boolean updateResume(CandidateScreeningApproval bean, String code[],
			String[] comment, String[] status, HttpServletRequest request,
			String[] candidateCode, String[] resReqsnCode,
			HttpServletResponse response) {

		boolean flag = false;
		String query1 = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object[][] data2 = getSqlModel().getSingleResult(query1);
		String st = request.getParameter("status");
		String[] itRecrruiterId = request.getParameterValues("itRecrruiterId");// Recruiter
																				// Id
		if (code != null && code.length > 0) {
			Object[][] data = new Object[code.length][6];
			String recruiterId = "";
			String itCandidateCode = "";
			String itReqCode = "";
			boolean chkflag = true;

			for (int i = 0; i < code.length; i++) {
				if (String.valueOf(status[i]).equals("")
						|| String.valueOf(status[i]).equals("null")) {
					if (st.equals("O") || st.equals("")) {
						data[i][0] = String.valueOf("O");
					} else {
						data[i][0] = st;
					}
				} else {
					data[i][0] = status[i];
				}
				data[i][1] = comment[i];
				data[i][2] = String.valueOf(data2[0][0]);
				data[i][3] = code[i];// RESUME CODE
				data[i][4] = candidateCode[i];// CANDIDATE CODE
				data[i][5] = resReqsnCode[i];// RESUME REQUISITION CODE
				flag = getSqlModel().singleExecute(getQuery(1), data);

				try {
					if (flag) {
						if (String.valueOf(status[i]).equals("R")) {
							sendRejectMail(candidateCode[i]);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		return flag;
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

	public void getRecord(CandidateScreeningApproval bean, String status,
			HttpServletRequest request, String reqsLabel, String postionLabel,
			String candidatNameLabel, String candExpLabel, String hiringLabel,
			String genderLabel) {
		// TODO Auto-generated method stub
		String query = "SELECT RESUME_REQS_CODE,NVL(REQS_NAME,' '),NVL(RANK_NAME,' '),CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME"
				+ " ,NVL(HRMS_REC_CAND_DATABANK.CAND_EXP_YEAR,0),CAND_CURR_CTC,CASE WHEN CAND_GENDER='M' THEN 'Male' WHEN CAND_GENDER='F' THEN 'Female' WHEN CAND_GENDER='O' THEN 'Other' ELSE ' ' END,"
				+ " EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(CAND_RESUME,' '),RESUME_STATUS,NVL(RESUME_COMMENTS,' '),NVL(HRMS_REC_CAND_DATABANK.CAND_EXP_MONTH,0),"
				+ " RESUME_CODE,RESUME_CAND_CODE ,RESUME_REC_EMPID FROM HRMS_REC_RESUME_BANK"
				+ " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)"
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
				+ " INNER JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_RESUME_BANK.RESUME_CAND_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_RESUME_BANK.RESUME_REC_EMPID)"
				+ " WHERE RESUME_STATUS="
				+ "'"
				+ status
				+ "' AND RESUME_APPR_EMPID = " + bean.getUserEmpId() + "";

		String conCat = "";
		// checking whether applied filter flag is true or false
		if (bean.getAppliedFilterFlag().equals("true")) {
			if (!bean.getRequisitionId().equals("")) {
				query += " AND  RESUME_REQS_CODE =" + bean.getRequisitionId();
				conCat += reqsLabel + " : " + bean.getRequisitionName() + ",";
			}
			if (!bean.getPositionId().equals("")) {
				query = query + " AND REQS_POSITION=" + bean.getPositionId();
				conCat += postionLabel + " : " + bean.getPositionName() + ",";
			}

			if (!bean.getCandCode1().equals("")) {
				query += " AND CAND_CODE=" + bean.getCandCode1();
				conCat += candidatNameLabel + " : " + bean.getCandidateName1()
						+ ",";
			}
			boolean chkFlag = false;
			if (!bean.getYear1().equals("")) {
				query += " AND CAND_EXP_YEAR=" + bean.getYear1();
				chkFlag = true;
				if (!bean.getMonth().equals("")) {
					conCat += candExpLabel + " : " + bean.getYear1() + " Year "
							+ bean.getMonth() + " Month ,";
				} else {
					conCat += candExpLabel + " : " + bean.getYear1()
							+ " Year ,";
				}
			}
			if (!bean.getMonth().equals("")) {
				query += " AND CAND_EXP_MONTH=" + bean.getMonth();
				if (chkFlag == false) {
					conCat += candExpLabel + " : " + bean.getMonth()
							+ " Month ,";
				}
			}

			if (!bean.getHrManagerId().equals("")) {
				query += " AND EMP_ID=" + bean.getHrManagerId();
				conCat += hiringLabel + " : " + bean.getManagerName() + ",";
			}
			if (!bean.getCandGender().equals("")) {
				query += " AND CAND_GENDER='" + bean.getCandGender() + "'";
				if (bean.getCandGender().equals("M")) {
					conCat += genderLabel + " : Male ,";
				}
				if (bean.getCandGender().equals("F")) {
					conCat += genderLabel + " : Female ,";
				}
				if (bean.getCandGender().equals("O")) {
					conCat += genderLabel + " : Others ,";
				}
			}

		} // end of if
		query += " ORDER BY REQS_DATE DESC ";
		try {
			// display the applied filter on jsp.
			if (conCat.equals("")) {
				request.setAttribute("filterArr", null);
			} else {
				String[] filterArr = conCat.split(",");
				request.setAttribute("filterArr", filterArr);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		Object[][] data = getSqlModel().getSingleResult(query);
		if (status.equals("O") && data != null && data.length > 0) {
			bean.setSaveCandFlag("true");
		} else {
			bean.setSaveCandFlag("false");
		}
		ArrayList<Object> reqList = new ArrayList<Object>();
		if (!status.equals(bean.getHiddenStatusPage())) {
			bean.setHiddenStatusPage(status);
			bean.setMyPage("1");
		}
		String[] pageIndex = Utility
				.doPaging(bean.getMyPage(), data.length, 20);
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

		if (data != null && data.length > 0) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				CandidateScreeningApproval candScrApp = new CandidateScreeningApproval();
				candScrApp.setSaveCandFlag(bean.getSaveCandFlag());
				candScrApp.setResReqCode(String.valueOf(data[i][0]));// Resume
				// Requisition
				// Code
				candScrApp.setReqCode(String.valueOf(data[i][1]));// Requisition
				// Code
				candScrApp.setPosition(String.valueOf(data[i][2]));// Position
				candScrApp.setCandidateName(String.valueOf(data[i][3]));// Candidate
				// Name
				String exp = "";
				int year = Integer.parseInt(String.valueOf(data[i][4]));
				int month = Integer.parseInt(String.valueOf(data[i][11]));
				if (year > 1 && month > 1) {
					exp += year + " Years\n " + month + " Months";
				} else if (year > 1 && !(month > 1)) {
					exp += year + " Years\n " + month + " Month";

				} else if (!(year > 1) && month > 1) {
					exp += year + " Year\n " + month + " Months";
				} else {
					exp += year + " Year\n " + month + " Month";
				}

				candScrApp.setExp(String.valueOf(exp));// Exp

				if (String.valueOf(data[i][5]).equals("")
						|| String.valueOf(data[i][5]).equals("null")) {
					candScrApp.setCtc(String.valueOf(""));
				} else {
					candScrApp.setCtc(String.valueOf(data[i][5]));// Ctc
				}
				// candScrApp.setCtc(String.valueOf(data[i][5]));//Ctc
				candScrApp.setGender(String.valueOf(data[i][6]));// Gender
				candScrApp.setRecruiter(String.valueOf(data[i][7]));// Recruiter
				// Name
				candScrApp.setResume(String.valueOf(data[i][8]));// Resume
				// Name
				if (status.equals("O")) {
					candScrApp.setOpenFlag("true");
				} else {
					candScrApp.setOpenFlag("false");
				}
				candScrApp.setComment(String.valueOf(data[i][10]));// Comment
				candScrApp.setResCode(String.valueOf(data[i][12]));// Resume
				// Code
				candScrApp.setResCandCode(String.valueOf(data[i][13]));// Resume
				// Candidate
				// Code
				candScrApp.setItRecrruiterId(String.valueOf(data[i][14]));// Recruiter
				// Id
				reqList.add(candScrApp);
			} // end of for loop
			logger.info("getMyPage======== " + bean.getMyPage());
			bean.setList(reqList);
			bean.setTotalRecords(String.valueOf(data.length));
			bean.setModeLength("true");
		} else {
			bean.setData("true");
			bean.setModeLength("false");
			bean.setTotalRecords("0");
			bean.setSelectname("1");

		}
	}

}
