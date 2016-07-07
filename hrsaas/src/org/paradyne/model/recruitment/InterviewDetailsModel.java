package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.Recruitment.InterviewDetails;
import org.paradyne.lib.*;

public class InterviewDetailsModel extends ModelBase {

	public InterviewDetailsModel() {
	}

	public void showInterviewCandList(String intType, String intrvrEmpId,
			InterviewDetails bean, HttpServletRequest request) {
		try {

			String intrListQuery = "";
			Object intrListObj[][] = (Object[][]) null;
			/*if (intType.equals("N")) {
				intrListQuery = (new StringBuilder(
						" SELECT DISTINCT INT_CODE, INT_CAND_CODE,CAND_FIRST_NAME||' ' ||CAND_MID_NAME|| ' '||CAND_LAST_NAME,  INT_REQS_CODE,REQS_NAME, RANK_NAME,RANK_ID, TO_CHAR(INT_DATE,'DD-MM-YYYY'), INT_TIME, INT_COMMENTS, INT_ROUND_TYPE,  DIV_NAME,DIV_ID,CENTER_NAME,CENTER_ID,DEPT_NAME,DEPT_ID,INT_DTL_CODE,CAND_RESUME,INT_DATE   FROM HRMS_REC_SCHINT  LEFT JOIN HRMS_REC_RESUME_BANK ON(HRMS_REC_RESUME_BANK.RESUME_REQS_CODE = HRMS_REC_SCHINT.INT_REQS_CODE)  LEFT JOIN HRMS_REC_SCHINT_DTL\tON(HRMS_REC_SCHINT_DTL.INT_CODE = HRMS_REC_SCHINT.INT_CODE)  INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_SCHINT_DTL.INT_REQS_CODE)  INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)  LEFT JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE)  INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION) INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH) INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT)  WHERE INT_CONDUCT_STATUS = '"))
						.append(intType).append("' AND INT_VIEWER_EMPID = ")
						.append(intrvrEmpId).append("  ORDER BY INT_DATE DESC")
						.toString();
				intrListObj = getSqlModel().getSingleResult(intrListQuery);
*/
			
			if(intType.equals("N"))
			{
				 intrListQuery = " SELECT DISTINCT INT_CODE, INT_CAND_CODE, CAND_FIRST_NAME||' ' ||CAND_MID_NAME|| ' '||CAND_LAST_NAME, "
						+ " INT_REQS_CODE, REQS_NAME, RANK_NAME,RANK_ID, TO_CHAR(INT_DATE,'DD-MM-YYYY'), INT_TIME, INT_COMMENTS, ROUND_TYPE," //INT_NEW_ROUND_TYPE, "
						+ " DIV_NAME,DIV_ID,CENTER_NAME,CENTER_ID,DEPT_NAME,DEPT_ID,INT_DTL_CODE,CAND_RESUME,INT_DATE  "
						+ " ,ROUND_CODE FROM HRMS_REC_SCHINT "
						+ " LEFT JOIN HRMS_REC_RESUME_BANK ON(HRMS_REC_RESUME_BANK.RESUME_REQS_CODE = HRMS_REC_SCHINT.INT_REQS_CODE) "
						+ " LEFT JOIN HRMS_REC_SCHINT_DTL	ON(HRMS_REC_SCHINT_DTL.INT_CODE = HRMS_REC_SCHINT.INT_CODE) "
						+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_SCHINT_DTL.INT_REQS_CODE) "
						+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
						+ " LEFT JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE) "
						+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH)"
						+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT) "
						+ " LEFT JOIN HRMS_REC_ROUND_TYPE ON (HRMS_REC_ROUND_TYPE.ROUND_CODE = HRMS_REC_SCHINT_DTL.INT_ROUND_TYPE)"
						+ " WHERE INT_CONDUCT_STATUS = '" + intType	+ "' AND INT_VIEWER_EMPID = " + intrvrEmpId+"  ORDER BY INT_DATE DESC" ;
				 intrListObj = getSqlModel().getSingleResult(intrListQuery);

			} else {

				intrListQuery = (new StringBuilder(
						" SELECT DISTINCT INT_CODE, INT_CAND_CODE,CAND_FIRST_NAME||' ' ||CAND_MID_NAME|| ' '||CAND_LAST_NAME,  INT_REQS_CODE,REQS_NAME, RANK_NAME,RANK_ID, TO_CHAR(EVAL_CURRINT_DATE,'DD-MM-YYYY'), EVAL_CURRINT_TIME, EVAL_COMMENTS, INT_ROUND_TYPE,  DIV_NAME,DIV_ID,CENTER_NAME,CENTER_ID,DEPT_NAME,DEPT_ID,INT_DTL_CODE,CAND_RESUME,EVAL_CURRINT_DATE   FROM HRMS_REC_SCHINT  LEFT JOIN HRMS_REC_RESUME_BANK ON(HRMS_REC_RESUME_BANK.RESUME_REQS_CODE = HRMS_REC_SCHINT.INT_REQS_CODE)  LEFT JOIN HRMS_REC_SCHINT_DTL\tON(HRMS_REC_SCHINT_DTL.INT_CODE = HRMS_REC_SCHINT.INT_CODE)  INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_SCHINT_DTL.INT_REQS_CODE)  INNER JOIN HRMS_REC_CANDEVAL ON(HRMS_REC_CANDEVAL.EVAL_INT_DTL_CODE = HRMS_REC_SCHINT_DTL.INT_DTL_CODE)  INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)  LEFT JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE)  INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION) INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH) INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT)  WHERE INT_CONDUCT_STATUS = '"))
						.append(intType).append("' AND INT_VIEWER_EMPID = ")
						.append(intrvrEmpId)
						.append("  ORDER BY EVAL_CURRINT_DATE DESC").toString();
				intrListObj = getSqlModel().getSingleResult(intrListQuery);

				 intrListQuery = " SELECT DISTINCT INT_CODE, INT_CAND_CODE,CAND_FIRST_NAME||' ' ||CAND_MID_NAME|| ' '||CAND_LAST_NAME, "
					+ " INT_REQS_CODE,REQS_NAME, RANK_NAME,RANK_ID, TO_CHAR(EVAL_CURRINT_DATE,'DD-MM-YYYY'), EVAL_CURRINT_TIME, EVAL_COMMENTS, ROUND_TYPE, " //INT_NEW_ROUND_TYPE, "
					+ " DIV_NAME,DIV_ID,CENTER_NAME,CENTER_ID,DEPT_NAME,DEPT_ID,INT_DTL_CODE,CAND_RESUME,EVAL_CURRINT_DATE  "
					+ " ,ROUND_CODE, HRMS_REC_CANDEVAL.EVAL_CODE, HRMS_REC_SCHINT_DTL.INT_DTL_CODE FROM HRMS_REC_SCHINT "
					+ " LEFT JOIN HRMS_REC_RESUME_BANK ON(HRMS_REC_RESUME_BANK.RESUME_REQS_CODE = HRMS_REC_SCHINT.INT_REQS_CODE) "
					+ " LEFT JOIN HRMS_REC_SCHINT_DTL	ON(HRMS_REC_SCHINT_DTL.INT_CODE = HRMS_REC_SCHINT.INT_CODE) "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_SCHINT_DTL.INT_REQS_CODE) "
					+ " INNER JOIN HRMS_REC_CANDEVAL ON(HRMS_REC_CANDEVAL.EVAL_INT_DTL_CODE = HRMS_REC_SCHINT_DTL.INT_DTL_CODE) "
					+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ " LEFT JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE) "
					+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION)"
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH)"
					+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT) "
					+"  LEFT JOIN HRMS_REC_ROUND_TYPE ON (HRMS_REC_ROUND_TYPE.ROUND_CODE = HRMS_REC_SCHINT_DTL.INT_ROUND_TYPE) "
					+ " WHERE INT_CONDUCT_STATUS = '" + intType + "' AND INT_VIEWER_EMPID = " + intrvrEmpId+"  ORDER BY EVAL_CURRINT_DATE DESC";
				 
				 
				 intrListObj = getSqlModel().getSingleResult(
					intrListQuery);

			}
			String pageIndex[] = Utility.doPaging(bean.getMyPage(),
					intrListObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.valueOf(Integer
					.parseInt(String.valueOf(pageIndex[2]))));
			request.setAttribute("PageNo", Integer.valueOf(Integer
					.parseInt(String.valueOf(pageIndex[3]))));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			ArrayList list = new ArrayList();
			if (intrListObj != null && intrListObj.length > 0) {
				for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
						.parseInt(String.valueOf(pageIndex[1])); i++) {
					InterviewDetails bean1 = new InterviewDetails();
					bean1.setIntCode(String.valueOf(intrListObj[i][0]));
					bean1.setCandCode(String.valueOf(intrListObj[i][1]));
					bean1.setCandName(String.valueOf(intrListObj[i][2]));
					bean1.setReqCode(String.valueOf(intrListObj[i][3]));
					bean1.setReqName(String.valueOf(intrListObj[i][4]));
					bean1.setPosition(String.valueOf(intrListObj[i][5]));
					bean1.setPositionId(String.valueOf(intrListObj[i][6]));
					bean1.setIntDate(checkNull(String
							.valueOf(intrListObj[i][7])));
					bean1.setIntTime(checkNull(String
							.valueOf(intrListObj[i][8])));
					logger.info((new StringBuilder(
							"intrListObj[i][9]====comments====>")).append(
							intrListObj[i][9]).toString());
					bean1.setComments(checkNull(String
							.valueOf(intrListObj[i][9])));
					bean1.setIntRoundType(String.valueOf(intrListObj[i][10]));
					bean1.setDivision(String.valueOf(intrListObj[i][11]));
					bean1.setDivisionId(String.valueOf(intrListObj[i][12]));
					bean1.setBranch(String.valueOf(intrListObj[i][13]));
					bean1.setBranchId(String.valueOf(intrListObj[i][14]));
					bean1.setDepartment(String.valueOf(intrListObj[i][15]));
					bean1.setDeptId(String.valueOf(intrListObj[i][16]));
					bean1.setIntDtlCode(String.valueOf(intrListObj[i][17]));
					bean1.setResumeName(String.valueOf(intrListObj[i][18]));
					bean1.setIntRoundTypeCode(String.valueOf(intrListObj[i][20]));
					
					if(intType.equals("O")) {
						bean1.setHiddenCandidateEvaluationCode(String.valueOf(intrListObj[i][21]));
						bean1.setHiddenInterviewDetailCode(String.valueOf(intrListObj[i][22]));
					}
					list.add(bean1);
				}

				bean.setIntrList(list);
			}
	//	} 
	}catch (Exception e) {
		e.printStackTrace();
		/*logger.error((new StringBuilder(
				"Exception in interview detials model ")).append(e).toString());*/
	}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null"))
			return "";
		else
			return result;
	}

	/** @modified by @author Prajakta.Bhandare
	 * @param reqCode
	 * @param candCode
	 * @param request
	 */
	public void showInterviewDetails(String reqCode, String candCode,
			HttpServletRequest request) {
		try {
			String reqQry = (new StringBuilder(
					" SELECT REQS_NAME,RANK_NAME,DIV_NAME,CENTER_NAME,DEPT_NAME,EMP_FNAME || ' ' || EMP_LNAME  FROM HRMS_REC_REQS_HDR  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION)" 
					+"  INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_REC_REQS_HDR.REQS_BRANCH)  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT)  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)  WHERE REQS_CODE = "))
					.append(reqCode).toString();
			Object reqObj[][] = getSqlModel().getSingleResult(reqQry);
			String intDtlQry = (new StringBuilder(
					" SELECT INT_DTL_CODE,'Interview Round :- '||''||HRMS_REC_ROUND_TYPE.ROUND_TYPE,EMP_FNAME || ' ' || EMP_LNAME, HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME || ' ' || HRMS_REC_CAND_DATABANK.CAND_MID_NAME || ' ' || HRMS_REC_CAND_DATABANK.CAND_LAST_NAME " 
					+" FROM HRMS_REC_SCHINT_DTL  INNER JOIN HRMS_REC_CAND_DATABANK on(HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE) INNER JOIN HRMS_REC_ROUND_TYPE ON (HRMS_REC_ROUND_TYPE.ROUND_CODE = HRMS_REC_SCHINT_DTL.INT_ROUND_TYPE) " 
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID)  WHERE INT_CAND_CODE = "))
					.append(candCode)
					.append(" AND INT_CONDUCT_STATUS IN ('Y','O') AND INT_REQS_CODE = ")
					.append(reqCode).toString();
			Object intDtlObj[][] = getSqlModel().getSingleResult(intDtlQry);
			String query = (new StringBuilder(
					" SELECT  DISTINCT 'Interview Round :- '||''||INT_ROUND_TYPE,EMP_FNAME || ' ' || EMP_LNAME,CAND_FIRST_NAME || ' ' || CAND_MID_NAME || ' ' || CAND_LAST_NAME from HRMS_REC_SCHINT_DTL  " 
					+" INNER JOIN HRMS_REC_CAND_DATABANK on(HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHINT_DTL.INT_CAND_CODE) INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID)   WHERE INT_CAND_CODE = "))
					.append(candCode)
					.append(" AND INT_CONDUCT_STATUS IN ('Y','O') AND INT_REQS_CODE = ")
					.append(reqCode).toString();
			Object obj[][] = getSqlModel().getSingleResult(query);
			Vector vect = new Vector();
			Object evalDtlObj[][] = (Object[][]) null;
			for (int i = 0; i < intDtlObj.length; i++) {
				String evalDtlQry = (new StringBuilder(
						" SELECT EVAL_RATE_CODE,NVL(EVAL_RATE_SCORE,0),DECODE(EVAL_INT_STATUS,'S','Selected','R','Rejected','O','OnHold'), NVL(EVAL_COMMENTS,' '),NVL(EVAL_CONSTRAINTS,' ') ,NVL(EVAL_RATE_COMMENTS,' '),NVL(REC_ASSESS_PARAM,' '),NVL(REC_ASSESS_DESC,' ') " 
						+" FROM HRMS_REC_CANDEVAL_DTL  INNER JOIN HRMS_REC_CANDEVAL ON (HRMS_REC_CANDEVAL.EVAL_CODE = HRMS_REC_CANDEVAL_DTL.EVAL_CODE)  INNER JOIN HRMS_REC_SCHINT_DTL ON (HRMS_REC_SCHINT_DTL.INT_DTL_CODE = HRMS_REC_CANDEVAL.EVAL_INT_DTL_CODE) " 
						+"  INNER JOIN HRMS_REC_INTERVIEW_ASSESSMENT ON (HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_CODE= HRMS_REC_CANDEVAL_DTL.EVAL_RATE_CODE)  WHERE INT_CONDUCT_STATUS IN ('Y','O')  AND EVAL_INT_DTL_CODE = "))
						.append(intDtlObj[i][0]).toString();
				evalDtlObj = getSqlModel().getSingleResult(evalDtlQry);
				vect.add(((Object) (evalDtlObj)));
			}
			for (int v = 0; v < intDtlObj.length; v++) {
				for (int w = 0; w < intDtlObj.length; w++) {
					System.out.println("intDtlObj["+v+"]["+w+"]-----------------"+intDtlObj[v][w]);
				}
			}

			request.setAttribute("reqObj", ((Object) (reqObj)));
			request.setAttribute("intDtlObj", ((Object) (intDtlObj)));
			request.setAttribute("obj", ((Object) (intDtlObj)));
			request.setAttribute("evalDtlObj", vect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showCanceledList(InterviewDetails bean,
			HttpServletRequest request) {
		Object cancelListData[][] = (Object[][]) null;
		try {
			String query = (new StringBuilder(
					"SELECT RESUME_REQS_CODE, REQS_NAME, RESUME_CAND_CODE, CAND_FIRST_NAME||' '||CAND_LAST_NAME,  INT_ROUND_TYPE,  TO_CHAR(INT_DATE,'DD-MM-YYYY'),INT_TIME, INT_VENUE_DET, INT_VIEWER_EMPID,  A1.EMP_FNAME||' '||A1.EMP_LNAME,INT_COMMENTS,  INT_CODE,INT_DTL_CODE,CAND_RESUME,RANK_NAME,RANK_ID FROM HRMS_REC_RESUME_BANK   INNER JOIN HRMS_REC_SCHINT_DTL ON (HRMS_REC_SCHINT_DTL.INT_CAND_CODE = HRMS_REC_RESUME_BANK.RESUME_CAND_CODE  and INT_CONDUCT_STATUS = 'C'  AND HRMS_REC_SCHINT_DTL.INT_REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)    INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_RESUME_BANK.RESUME_CAND_CODE)  INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID)   INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)  INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)  WHERE (RESUME_SHEDULE_STATUS = 'I' OR RESUME_SHEDULE_STATUS = 'F')   AND RESUME_REC_EMPID = "))
					.append(bean.getUserEmpId())
					.append("  ORDER BY INT_DATE DESC  ").toString();
			cancelListData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in cancel list data", e);
		}
		String pageIndex[] = Utility.doPaging(bean.getMyPage(),
				cancelListData.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		request.setAttribute("totalPage",
				Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[2]))));
		request.setAttribute("PageNo",
				Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[3]))));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		ArrayList list = new ArrayList();
		if (cancelListData != null && cancelListData.length > 0) {
			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
					.parseInt(String.valueOf(pageIndex[1])); i++) {
				InterviewDetails bean1 = new InterviewDetails();
				bean1.setIntCode(String.valueOf(cancelListData[i][11]));
				bean1.setCandCode(String.valueOf(cancelListData[i][2]));
				bean1.setCandName(String.valueOf(cancelListData[i][3]));
				bean1.setReqCode(String.valueOf(cancelListData[i][0]));
				bean1.setReqName(String.valueOf(cancelListData[i][1]));
				bean1.setPosition(String.valueOf(cancelListData[i][14]));
				bean1.setPositionId(String.valueOf(cancelListData[i][15]));
				bean1.setIntDate(checkNull(String.valueOf(cancelListData[i][5])));
				bean1.setIntTime(checkNull(String.valueOf(cancelListData[i][6])));
				bean1.setIntRoundType(String.valueOf(cancelListData[i][4]));
				bean1.setIntDtlCode(String.valueOf(cancelListData[i][12]));
				bean1.setResumeName(String.valueOf(cancelListData[i][13]));
				list.add(bean1);
			}

			bean.setIntrList(list);
		}
	}

	static Logger logger = Logger.getLogger(InterviewDetailsModel.class);

}
