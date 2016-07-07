/**
 * 
 */
package org.paradyne.model.recruitment;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.VacancyManagement;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author varunk
 * 
 */

public class VacancyManagementModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * this method is to retrieve requisition data which is open and whose
	 * status is approved.
	 * 
	 * @param vacancyMgmt
	 * @param reStatus
	 * @param approvalStatus
	 */
	/**
	 * added the parameter vacStatus in the following method on
	 * Date:17-03-2009(Pradeep)
	 * 
	 */
	public void getRecord(VacancyManagement vacancyMgmt, String reStatus,
			String approvalStatus, String vacStatus, String msg, String msg1,
			String msg2, String msg3, String msg4, String msg5, String msg6,
			HttpServletRequest request) {
		int count = 0;
		String concatStr = "";
		ArrayList<Object> tableList = new ArrayList<Object>();
		Object[][] reqData = null;
		logger.info("vacancyMgmt.getDivId()   " + vacancyMgmt.getDivId());
		try {
			/*
			 * String query = "SELECT
			 * HRMS_REC_REQS_HDR.REQS_CODE,RANK_NAME,B1.EMP_FNAME||'
			 * '||B1.EMP_LNAME, " +" A1.EMP_FNAME||'
			 * '||A1.EMP_LNAME,TO_CHAR(REQS_DATE,'DD-MM-YYYY'), " +"
			 * VACAN_NUMBERS,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),VACAN_DTL_CODE,REQS_NAME,B1.EMP_ID,A1.EMP_ID, " +"
			 * DECODE(REQS_RECTYPE_INT,'N','No','Y','Yes'),DECODE(REQS_RECTYPE_EXT,'N','No','Y','Yes')
			 * FROM HRMS_REC_REQS_HDR " +" INNER JOIN HRMS_REC_REQS_VACDTL ON
			 * (HRMS_REC_REQS_VACDTL.REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) " +"
			 * INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =
			 * HRMS_REC_REQS_HDR.REQS_POSITION) " +" INNER JOIN HRMS_EMP_OFFC A1
			 * ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) " +" INNER
			 * JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID =
			 * HRMS_REC_REQS_HDR.REQS_APPLIED_BY) " +" WHERE
			 * REQS_STATUS='"+reStatus+"' AND REQS_APPROVAL_STATUS IN
			 * ('"+approvalStatus+"','Q') AND VACAN_STATUS='"+vacStatus+"' ";
			 */
			String query = "";

			if (reStatus.equals("C")) {
				// vacancyMgmt.setPublishButtonFlag("true");
				query = "SELECT HRMS_REC_REQS_HDR.REQS_CODE,RANK_NAME,B1.EMP_FNAME||' '||B1.EMP_LNAME, "
						+ " A1.EMP_FNAME||' '||A1.EMP_LNAME,TO_CHAR(REQS_DATE,'DD-MM-YYYY'),"
						+ " VACAN_NUMBERS,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),VACAN_DTL_CODE,REQS_NAME,B1.EMP_ID,A1.EMP_ID,"
						+ " DECODE(REQS_RECTYPE_INT,'N','No','Y','Yes'),DECODE(REQS_RECTYPE_EXT,'N','No','Y','Yes') "
						+ "  FROM HRMS_REC_REQS_HDR "
						+ " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
						+ " INNER JOIN HRMS_DIVISION on (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_CENTER on (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
						+ " INNER JOIN HRMS_DEPT on (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						+ " INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
						+ " INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY)"
						+ " WHERE  REQS_APPROVAL_STATUS IN ('" + approvalStatus
						+ "','Q') AND  VACAN_STATUS='" + reStatus + "' "+" AND (REQS_HIRING_MANAGER="+vacancyMgmt.getUserEmpId()
						+ " OR (select CONF_REC_HEAD from HRMS_REC_CONF)="+vacancyMgmt.getUserEmpId()+")";  //REQS_STATUS='" + reStatus + "' ";
			}else if(vacStatus.equals("P")) {
				query = "SELECT HRMS_REC_REQS_HDR.REQS_CODE,RANK_NAME,B1.EMP_FNAME||' '||B1.EMP_LNAME, "
						+ " A1.EMP_FNAME||' '||A1.EMP_LNAME,TO_CHAR(REQS_DATE,'DD-MM-YYYY'),"
						+ " VACAN_NUMBERS,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),VACAN_DTL_CODE,REQS_NAME,B1.EMP_ID,A1.EMP_ID,"
						+ " DECODE(REQS_RECTYPE_INT,'N','No','Y','Yes'),DECODE(REQS_RECTYPE_EXT,'N','No','Y','Yes'), PUB_CODE"
						+ "  FROM HRMS_REC_REQS_HDR "
						+" INNER  JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
						+" INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
						+ " INNER JOIN HRMS_DIVISION on (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_CENTER on (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
						+ " INNER JOIN HRMS_DEPT on (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						+ " INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
					+ " INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY)"
					+ " WHERE REQS_STATUS='"+ reStatus+ "' AND REQS_APPROVAL_STATUS IN ('"+ approvalStatus+ "','Q') AND VACAN_STATUS='" + vacStatus + "' "
                    + " AND (REQS_HIRING_MANAGER="+vacancyMgmt.getUserEmpId()
                    + " OR (select CONF_REC_HEAD from HRMS_REC_CONF)="+vacancyMgmt.getUserEmpId()+")";
			} else {
				query = "SELECT HRMS_REC_REQS_HDR.REQS_CODE,RANK_NAME,B1.EMP_FNAME||' '||B1.EMP_LNAME, "
						+ " A1.EMP_FNAME||' '||A1.EMP_LNAME,TO_CHAR(REQS_DATE,'DD-MM-YYYY'),"
						+ " VACAN_NUMBERS,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),VACAN_DTL_CODE,REQS_NAME,B1.EMP_ID,A1.EMP_ID,"
						+ " DECODE(REQS_RECTYPE_INT,'N','No','Y','Yes'),DECODE(REQS_RECTYPE_EXT,'N','No','Y','Yes') "
						+ "  FROM HRMS_REC_REQS_HDR "
						+ " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
						+ " INNER JOIN HRMS_DIVISION on (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_CENTER on (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
						+ " INNER JOIN HRMS_DEPT on (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						+ " INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
						+ " INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY)"
						+ " WHERE REQS_STATUS='"
						+ reStatus
						+ "' AND REQS_APPROVAL_STATUS IN ('"
						+ approvalStatus
						+ "','Q') AND VACAN_STATUS='" + vacStatus + "' "
						+" AND (REQS_HIRING_MANAGER="+vacancyMgmt.getUserEmpId()
						+" OR (select CONF_REC_HEAD from HRMS_REC_CONF)="+vacancyMgmt.getUserEmpId()+")";

			}

			if (vacancyMgmt.getApplyFilterFlag().equals("true")) {
				if (!(vacancyMgmt.getDivId().equals("") || vacancyMgmt
						.getDivId().equals("null"))) {
					query = query + " AND DIV_ID=" + vacancyMgmt.getDivId();
					concatStr += msg + " :" + vacancyMgmt.getDivName() + ",";
				}
				if (!(vacancyMgmt.getBranchId().equals("") || vacancyMgmt
						.getBranchId().equals(""))) {
					query = query + " AND CENTER_ID="
							+ vacancyMgmt.getBranchId();
					concatStr += msg1 + " :" + vacancyMgmt.getBranchName()
							+ ",";

				}
				if (!(vacancyMgmt.getDeptId().equals("") || vacancyMgmt
						.getDeptId().equals(""))) {
					query = query + " AND DEPT_ID=" + vacancyMgmt.getDeptId();
					concatStr += msg2 + " :" + vacancyMgmt.getDeptName() + ",";

				}

				if (!(vacancyMgmt.getHrManagerId().equals("") || vacancyMgmt
						.getHrManagerId().equals(""))) {
					query = query + " AND A1.EMP_ID="
							+ vacancyMgmt.getHrManagerId();
					concatStr += msg3 + " :" + vacancyMgmt.getManagerName()
							+ ",";

				}
				if (!(vacancyMgmt.getPositionId().equals("") || vacancyMgmt
						.getPositionId().equals(""))) {
					query = query + " AND RANK_Id="
							+ vacancyMgmt.getPositionId();
					concatStr += msg4 + " :" + vacancyMgmt.getPositionName()
							+ ",";
				}

				if (!(vacancyMgmt.getFDate().trim().equals("") || vacancyMgmt
						.getFDate().trim().equals("null"))) {
					query = query + " AND REQS_DATE >=TO_DATE(" + "'"
							+ vacancyMgmt.getFDate() + "'" + ",'DD-MM-YYYY')";
					concatStr += msg5 + " :" + vacancyMgmt.getFDate() + ",";
				}
				if (!(vacancyMgmt.getTDate().trim().equals("") || vacancyMgmt
						.getTDate().trim().equals("null"))) {
					query = query + " AND REQS_DATE <=TO_DATE(" + "'"
							+ vacancyMgmt.getTDate() + "'" + ",'DD-MM-YYYY')";
					concatStr += msg6 + " :" + vacancyMgmt.getTDate() + ",";

				}
			} else {
				vacancyMgmt.setApplyFilterFlag("false");
			}
			query = query + " 	ORDER BY REQS_DATE DESC";

			reqData = getSqlModel().getSingleResult(query);

			if (reqData != null && reqData.length > 0) {
				vacancyMgmt.setModeLength("true");
			} else {
				vacancyMgmt.setModeLength("false");
			}
			String[] pageIndex = Utility.doPaging(vacancyMgmt.getMyPage(),
					reqData.length, 20);
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
				vacancyMgmt.setMyPage("1");

			if (reqData.length > 0) {// this if to check if query contains
										// data or not

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {// loop for req query
					VacancyManagement bean = new VacancyManagement();
					if (vacStatus.equals("P")) {
						bean.setPublishButtonFlag("true");
					} else if (vacStatus.equals("C")) {
						bean.setPublishButtonFlag("true");
						bean.setCloseVacancyFlag("true");
					}
					bean.setReqCode(String.valueOf(reqData[i][0]));// req code
					bean.setPosition(String.valueOf(reqData[i][1]));// position
					bean.setAppliedBy(String.valueOf(reqData[i][2]));// applied
																		// by
					bean.setHiringMgr(String.valueOf(reqData[i][3]));// hiring
																		// manager
					if (String.valueOf(reqData[i][4]).equals("")
							|| String.valueOf(reqData[i][4]).equals("null")
							|| String.valueOf(reqData[i][4]).equals(null)) {
						bean.setReqDate("");// req date
					} else {
						bean.setReqDate(String.valueOf(reqData[i][4]));// req
																		// date
					}
					bean.setNoOfVacancies(String.valueOf(reqData[i][5]));// no
																			// of
																			// vacancies
					bean.setRequiredDate(checkNull(String
							.valueOf(reqData[i][6])));// required date
					bean.setVacanDtlCode(String.valueOf(reqData[i][7]));// vacancy
																		// Dtl
																		// Code
					bean.setReqName(String.valueOf(reqData[i][8]));// req name
					bean.setAppliedEmpId(String.valueOf(reqData[i][9]));// applied
																		// by
																		// empId
					bean.setHiringEmpId(String.valueOf(reqData[i][10]));// hiring
																		// empId
					bean.setRecruitmentInternal(String.valueOf(reqData[i][11]));
					bean.setRecruitmentExternal(String.valueOf(reqData[i][12]));
					if(vacStatus.equals("P")) {
						/*String getNoOfVacancy = "SELECT VAC_REQS_CODE, VAC_STATUS, VAC_PUBLISH_CODE  FROM HRMS_REC_VACANCIES_STATS "  
												+" WHERE VAC_STATUS = 'C' AND VAC_REQS_CODE = "+String.valueOf(reqData[i][0])+" AND VAC_PUBLISH_CODE = "+String.valueOf(reqData[i][13]);
						Object[][] getNoOfacancyObj = getSqlModel().getSingleResult(getNoOfVacancy);
						if(getNoOfacancyObj != null && getNoOfacancyObj.length>0) {
							bean.setNoOfVacancies(String.valueOf((Integer.parseInt(String.valueOf(reqData[i][5])) - Integer.parseInt(String.valueOf(getNoOfacancyObj.length)))));
						}*/
						
						String dataAvailableQuery = "SELECT VAC_REQS_CODE, VAC_STATUS, VAC_PUBLISH_CODE  FROM HRMS_REC_VACANCIES_STATS "   
													+" WHERE VAC_STATUS = 'O' AND VAC_REQS_CODE = "+String.valueOf(reqData[i][0])+" AND VAC_PUBLISH_CODE = "+String.valueOf(reqData[i][13]);
						Object dataAvailableObj[][] = getSqlModel().getSingleResult(dataAvailableQuery);
						if(dataAvailableObj == null || dataAvailableObj.length ==0) {
							bean.setCloseVacancyFlag("true");
						}else{
							bean.setCloseVacancyFlag("false");
						}
						
						bean.setVacancyPublishCode(String.valueOf(reqData[i][13]));
					}
					tableList.add(bean);// data added in list
				}
				vacancyMgmt.setList(tableList);// table list have been set
				vacancyMgmt.setTotalRecords(String.valueOf(reqData.length));

			} else {// if reqData is null
				vacancyMgmt.setNoData("true");// No data to display message
												// shown
				vacancyMgmt.setList(null);
				vacancyMgmt.setTotalRecords(String.valueOf(reqData.length));

			}

		} catch (Exception e) {
			logger.error("exception in getRecord method", e);
		}

		try {
			String[] dispArr = concatStr.split(",");
			request.setAttribute("dispArr", dispArr);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	/*
	 * this method is used to get the record for public requisition where status
	 * =P.
	 * 
	 */
	public void getPublishedRecord(VacancyManagement vacancyMgmt,
			String pubStatus, String approvalStatus, String reqStatus,
			String msg, String msg1, String msg2, String msg3, String msg4,
			String msg5, String msg6, HttpServletRequest request) {
		int count = 0;
		String concatStr = "";

		ArrayList<Object> tableList = new ArrayList<Object>();
		Object[][] reqData = null;
		String query = "";
		try {
			if (pubStatus.equals("C")) {
				/*query = "SELECT HRMS_REC_REQS_HDR.REQS_CODE,RANK_NAME,B1.EMP_FNAME||' '||B1.EMP_LNAME, "
						+ " A1.EMP_FNAME||' '||A1.EMP_LNAME,TO_CHAR(REQS_DATE,'DD-MM-YYYY'),"
						+ " VACAN_NUMBERS,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),VACAN_DTL_CODE,REQS_NAME,B1.EMP_ID,A1.EMP_ID,"
						+ " DECODE(REQS_RECTYPE_INT,'N','No','Y','Yes'),DECODE(REQS_RECTYPE_EXT,'N','No','Y','Yes')"
						+ "  FROM HRMS_REC_REQS_HDR "
						+ " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
						+ " INNER JOIN HRMS_DIVISION on (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_CENTER on (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
						+ " INNER JOIN HRMS_DEPT on (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						+ " INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
						+ " INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY)"
						+ " WHERE  REQS_APPROVAL_STATUS IN ('"
						+ approvalStatus
						+ "','Q') AND VACAN_STATUS='C' ";*/
				 
				query = "SELECT HRMS_REC_REQS_HDR.REQS_CODE,RANK_NAME,B1.EMP_FNAME||' '||B1.EMP_LNAME, "
					+ " A1.EMP_FNAME||' '||A1.EMP_LNAME,TO_CHAR(REQS_DATE,'DD-MM-YYYY'),"
					+ " VACAN_NUMBERS,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),VACAN_DTL_CODE,REQS_NAME,B1.EMP_ID,A1.EMP_ID,"
					+ " DECODE(REQS_RECTYPE_INT,'N','No','Y','Yes'),DECODE(REQS_RECTYPE_EXT,'N','No','Y','Yes')"
					+ "  FROM HRMS_REC_REQS_HDR "
					+ " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ " INNER JOIN HRMS_DIVISION on (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
					+ " INNER JOIN HRMS_CENTER on (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
					+ " INNER JOIN HRMS_DEPT on (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
					+ " INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
					+ " INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY)"
					+ " WHERE  REQS_APPROVAL_STATUS IN ('" + approvalStatus + "','Q') AND VACAN_STATUS='C'"
					+ " AND (REQS_HIRING_MANAGER="+vacancyMgmt.getUserEmpId()
					+ " OR (select CONF_REC_HEAD from HRMS_REC_CONF)="+vacancyMgmt.getUserEmpId()+")"; //AND REQS_STATUS='C' ";
			} else {
				query = "SELECT HRMS_REC_REQS_HDR.REQS_CODE,RANK_NAME,B1.EMP_FNAME||' '||B1.EMP_LNAME,  A1.EMP_FNAME||' '||A1.EMP_LNAME, "
						+ " TO_CHAR(REQS_DATE,'DD-MM-YYYY'),  VACAN_NUMBERS,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'), "
						+ " VACAN_DTL_CODE,REQS_NAME,B1.EMP_ID,A1.EMP_ID, DECODE(REQS_RECTYPE_INT,'N','No','Y','Yes'),DECODE(REQS_RECTYPE_EXT,'N','No','Y','Yes'), PUB_CODE"
						+ " FROM HRMS_REC_REQS_HDR  "
						/*
						 * + " INNER JOIN HRMS_REC_REQS_VACDTL ON
						 * (HRMS_REC_REQS_VACDTL.REQS_CODE =
						 * HRMS_REC_REQS_HDR.REQS_CODE) " + " INNER JOIN
						 * HRMS_REC_VACPUB_HDR ON
						 * (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE =
						 * HRMS_REC_REQS_HDR.REQS_CODE) "
						 */
						+ " INNER  JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE)  "
						+ " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)  "
						+ " INNER JOIN HRMS_DIVISION on (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_CENTER on (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
						+ " INNER JOIN HRMS_DEPT on (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						+ " INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) "
						+ " INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY)  "
						+ " WHERE PUB_STATUS='"
						+ pubStatus
						+ "' AND REQS_APPROVAL_STATUS IN('"
						+ approvalStatus
						+ "','Q') and REQS_STATUS='"
						+ reqStatus
						+ "'  AND VACAN_STATUS='P' "
						+" AND (REQS_HIRING_MANAGER="+vacancyMgmt.getUserEmpId()
						+" OR (select CONF_REC_HEAD from HRMS_REC_CONF)="+vacancyMgmt.getUserEmpId()+")";
			}

			if (vacancyMgmt.getApplyFilterFlag().equals("true")) {
				if (!(vacancyMgmt.getDivId().equals("") || vacancyMgmt
						.getDivId().equals(""))) {
					query = query + " AND DIV_ID=" + vacancyMgmt.getDivId();
					concatStr += msg + " :" + vacancyMgmt.getDivName() + ",";
				}
				if (!(vacancyMgmt.getBranchId().equals("") || vacancyMgmt
						.getBranchId().equals(""))) {
					query = query + " AND CENTER_ID="
							+ vacancyMgmt.getBranchId();
					concatStr += msg1 + " :" + vacancyMgmt.getBranchName()
							+ ",";
				}
				if (!(vacancyMgmt.getDeptId().equals("") || vacancyMgmt
						.getDeptId().equals(""))) {
					query = query + " AND DEPT_ID=" + vacancyMgmt.getDeptId();
					concatStr += msg2 + " :" + vacancyMgmt.getDeptName() + ",";
				}

				if (!(vacancyMgmt.getHrManagerId().equals("") || vacancyMgmt
						.getHrManagerId().equals(""))) {
					query = query + " AND A1.EMP_ID="
							+ vacancyMgmt.getHrManagerId();
					concatStr += msg3 + " :" + vacancyMgmt.getManagerName()
							+ ",";
				}
				if (!(vacancyMgmt.getPositionId().equals("") || vacancyMgmt
						.getPositionId().equals(""))) {
					query = query + " AND RANK_ID="
							+ vacancyMgmt.getPositionId();
					concatStr += msg4 + " :" + vacancyMgmt.getPositionName()
							+ ",";

				}

				if (!(vacancyMgmt.getFDate().trim().equals("") || vacancyMgmt
						.getFDate().trim().equals("null"))) {
					query = query + " AND REQS_DATE >=TO_DATE(" + "'"
							+ vacancyMgmt.getFDate() + "'" + ",'DD-MM-YYYY')";
					concatStr += msg5 + " :" + vacancyMgmt.getFDate() + ",";

				}
				if (!(vacancyMgmt.getTDate().trim().equals("") || vacancyMgmt
						.getTDate().trim().equals("null"))) {
					query = query + " AND REQS_DATE <=TO_DATE(" + "'"
							+ vacancyMgmt.getTDate() + "'" + ",'DD-MM-YYYY')";
					concatStr += msg6 + " :" + vacancyMgmt.getTDate() + ",";

				}

			} else {
				vacancyMgmt.setApplyFilterFlag("true");
			}
			query = query + " 	ORDER BY REQS_DATE DESC";
			reqData = getSqlModel().getSingleResult(query);

			if (reqData != null && reqData.length > 0) {
				vacancyMgmt.setModeLength("true");
			} else {
				vacancyMgmt.setModeLength("false");
			}
			String[] pageIndex = Utility.doPaging(vacancyMgmt.getMyPage(),
					reqData.length, 20);
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
				vacancyMgmt.setMyPage("1");

			if (reqData.length > 0) {// this if to check if query contains
										// data or not
				for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
						.parseInt(String.valueOf(pageIndex[1])); i++) {// loop
																		// for
																		// req
																		// query
					VacancyManagement bean = new VacancyManagement();

					bean.setReqCode(String.valueOf(reqData[i][0]));// req code
					bean.setPosition(checkNull(String.valueOf(reqData[i][1])));// position
					bean.setAppliedBy(String.valueOf(reqData[i][2]));// applied
																		// by
					bean.setHiringMgr(String.valueOf(reqData[i][3]));// hiring
																		// manager
					if (String.valueOf(reqData[i][4]).equals("")
							|| String.valueOf(reqData[i][4]).equals("null")
							|| String.valueOf(reqData[i][4]).equals(null)) {
						bean.setReqDate("");// req date
					} else {
						bean.setReqDate(String.valueOf(reqData[i][4]));// req
																		// date
					}
					bean.setNoOfVacancies(checkNull(String
							.valueOf(reqData[i][5])));// no of vacancies
					bean.setRequiredDate(checkNull(String
							.valueOf(reqData[i][6])));// required date
					bean.setVacanDtlCode(String.valueOf(reqData[i][7]));// vacancy
																		// Dtl
																		// Code
					bean.setReqName(String.valueOf(reqData[i][8]));// req name
					bean.setAppliedEmpId(String.valueOf(reqData[i][9]));// applied
																		// by
																		// empId
					bean.setHiringEmpId(String.valueOf(reqData[i][10]));// hiring
																		// empId
					
					if(pubStatus.equals("P")){
						/*String getNoOfVacancy = "SELECT VAC_REQS_CODE, VAC_STATUS, VAC_PUBLISH_CODE  FROM HRMS_REC_VACANCIES_STATS "  
							+" WHERE VAC_STATUS = 'C' AND VAC_REQS_CODE = "+String.valueOf(reqData[i][0])+" AND VAC_PUBLISH_CODE = "+String.valueOf(reqData[i][13]);
						Object[][] getNoOfacancyObj = getSqlModel().getSingleResult(getNoOfVacancy);
						if(getNoOfacancyObj != null && getNoOfacancyObj.length>0) {
							bean.setNoOfVacancies(String.valueOf((Integer.parseInt(String.valueOf(reqData[i][5])) - Integer.parseInt(String.valueOf(getNoOfacancyObj.length)))));
						}*/
						
						String dataAvailableQuery = "SELECT VAC_REQS_CODE, VAC_STATUS, VAC_PUBLISH_CODE  FROM HRMS_REC_VACANCIES_STATS "   
													+" WHERE VAC_STATUS = 'O' AND VAC_REQS_CODE = "+String.valueOf(reqData[i][0])+" AND VAC_PUBLISH_CODE = "+String.valueOf(reqData[i][13]);
						Object dataAvailableObj[][] = getSqlModel().getSingleResult(dataAvailableQuery);
						if(dataAvailableObj == null || dataAvailableObj.length ==0) {
							bean.setCloseVacancyFlag("true");
						}else{
							bean.setCloseVacancyFlag("false");
						}
						bean.setVacancyPublishCode(String.valueOf(reqData[i][13]));
					}
					bean.setPublishButtonFlag("true");
					if(pubStatus.equals("C")){
						bean.setCloseVacancyFlag("true");
					}
					tableList.add(bean);// data added in list
				}
				vacancyMgmt.setList(tableList);// table list have been set
				vacancyMgmt.setNoData("false");
				vacancyMgmt.setTotalRecords(String.valueOf(reqData.length));// disply
																			// the
																			// total
																			// no
																			// of
																			// records.

			} else {// if reqData is null
				vacancyMgmt.setNoData("true");// No data to display message
												// shown
				vacancyMgmt.setList(null);
				vacancyMgmt.setTotalRecords(String.valueOf(reqData.length));

			}

		} // End of 1st try block

		catch (Exception e) {
			logger.error("exception in getPublishedRecord method", e);
		}// end of 1st catch block
		try {
			String[] dispArr = concatStr.split(",");
			request.setAttribute("dispArr", dispArr);
		}// End of try 2nd block
		catch (Exception e) {

			// TODO: handle exception
		}// end of 2nd catch block
	}

	public boolean updateRequisitionStatus(VacancyManagement vacancyMgmt,
			HttpServletRequest request, String requisitionCode, String noOfVacancies) {
		boolean result = false;
		try {
			String getNumberOfColumns = "SELECT HRMS_REC_REQS_VACDTL.REQS_CODE, HRMS_REC_REQS_VACDTL.VACAN_NUMBERS, HRMS_REC_REQS_VACDTL.VACAN_STATUS FROM HRMS_REC_REQS_VACDTL WHERE HRMS_REC_REQS_VACDTL.REQS_CODE = "+requisitionCode;
			Object[][] getNumberOfVacanciesObj = getSqlModel().getSingleResult(getNumberOfColumns);
			if(getNumberOfVacanciesObj != null) {
				if(getNumberOfVacanciesObj.length > 1) {
					String updateRequisitionVacancyStatus = "UPDATE HRMS_REC_REQS_VACDTL SET HRMS_REC_REQS_VACDTL.VACAN_STATUS = 'C', "
														+" HRMS_REC_REQS_VACDTL.VACAN_CLOSE_DATE = TO_DATE(( SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL),'DD-MM-YYYY') "
														+" WHERE HRMS_REC_REQS_VACDTL.REQS_CODE = "+requisitionCode+" AND HRMS_REC_REQS_VACDTL.VACAN_NUMBERS="+noOfVacancies;
					result = getSqlModel().singleExecute(updateRequisitionVacancyStatus);
				}else{
					String updateRequisitionVacancyStatus = "UPDATE HRMS_REC_REQS_VACDTL SET HRMS_REC_REQS_VACDTL.VACAN_STATUS = 'C', "
						+" HRMS_REC_REQS_VACDTL.VACAN_CLOSE_DATE = TO_DATE(( SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL),'DD-MM-YYYY') "
						+" WHERE HRMS_REC_REQS_VACDTL.REQS_CODE = "+requisitionCode+" AND HRMS_REC_REQS_VACDTL.VACAN_NUMBERS="+noOfVacancies;
					result = getSqlModel().singleExecute(updateRequisitionVacancyStatus);
					
					String updateRequisitionStatus = "UPDATE HRMS_REC_REQS_HDR SET HRMS_REC_REQS_HDR.REQS_STATUS = 'C', " 
						+" HRMS_REC_REQS_HDR.REQS_CLOSE_DATE = TO_DATE(( SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL),'DD-MM-YYYY') " 
						+" WHERE HRMS_REC_REQS_HDR.REQS_CODE = "+requisitionCode;
					result = getSqlModel().singleExecute(updateRequisitionStatus);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*public void getReport(VacancyManagement bean, HttpServletResponse response,
			String[] labelNames, HttpServletRequest request) { 
		try {

			System.out.println("IN VACANCY MANAGEMENT REPORT");

			String reportType = "";
			if (bean.getReportType().equals("P")) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals("X")) {
				reportType = "Xls";
			}
			if (bean.getReportType().equals("T")) {
				reportType = "Txt";
			}
			logger.info("reportType--------------->" + reportType + "<-------");

			String reportName = "";
			if (!bean.getReportTitle().equals(""))
				reportName = bean.getReportTitle();
			else
				reportName = "Vacancy Management Report";
			org.paradyne.lib.report.ReportGenerator rg1 = new org.paradyne.lib.report.ReportGenerator(
					reportType, reportName, "");
			rg1.addText("\n", 0, 0, 0);

		//	ReportDataSet rds = new ReportDataSet();
		//	rds.setReportType("Xls");

		//	org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
			
			
			
			

			String title = " Vacancy Management Report ";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Vacancy Management Report_"+bean.getDivision());
			rds.setReportName(title);
			rds.setPageSize("landscape");
			System.out.println("bean.getReportType() : "+bean.getReportType());
			rds.setReportType("Xls");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
			
			
			
			//For Report heading
			TableDataSet repTitle = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;			
			repTitle.setData(repTitleObj);
			repTitle.setCellAlignment(new int[] { 1 });
			repTitle.setCellWidth(new int[] { 100 });
			repTitle.setBodyFontDetails(Font.HELVETICA, 10,Font.BOLD, new Color(0, 0, 0));
			repTitle.setBorder(false);
			repTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(repTitle);			
			
			
			String whereClause="";
			if(!bean.getBranchName().equals(""))
			{
				whereClause +=" AND HRMS_CENTER.CENTER_ID ="+bean.getBranchId();
			}
			if(!bean.getDivName().equals(""))
			{
				whereClause +=" AND HRMS_REC_REQS_HDR.REQS_DIVISION ="+bean.getDivId();
			}
			if(!bean.getDeptName().equals(""))
			{
				whereClause +=" AND HRMS_REC_REQS_HDR.REQS_DEPT ="+bean.getDeptId();
			}
			if(!bean.getManagerName().equals(""))
			{
				whereClause +=" AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER ="+bean.getHrManagerId();
			}
			if(!bean.getPositionName().equals(""))
			{
				whereClause +=" AND HRMS_REC_REQS_HDR.REQS_POSITION ="+bean.getPositionId();
			}
			
			if((!bean.getFDate().equals(""))&& (bean.getTDate().equals("")))
			{				
				whereClause +=" AND TO_CHAR(REQS_DATE,'DD-MM-YYYY') >= '"+bean.getFDate()+"'";
			}
		 if((!bean.getTDate().equals(""))&&(bean.getFDate().equals("")))
			{
				whereClause +=" AND TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY') <= '"+bean.getTDate()+"'";
			}
			
						
			
	
			String query = " SELECT HRMS_REC_REQS_HDR.REQS_CODE,RANK_NAME,B1.EMP_FNAME||' '||B1.EMP_LNAME,  A1.EMP_FNAME||' '||A1.EMP_LNAME,"
					+ " TO_CHAR(REQS_DATE,'DD-MM-YYYY'), VACAN_NUMBERS,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),VACAN_DTL_CODE,REQS_NAME,"
					+ " B1.EMP_ID,A1.EMP_ID, DECODE(REQS_RECTYPE_INT,'N','No','Y','Yes'),DECODE(REQS_RECTYPE_EXT,'N','No','Y','Yes'),NVL(DEPT_NAME,'') ,NVL(DIV_NAME,''),NVL(HRMS_CENTER.CENTER_NAME,'')  "
					+ " FROM HRMS_REC_REQS_HDR "
					+ "  INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
					+ "  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ "  INNER JOIN HRMS_DIVISION on (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION) "
					+ "  INNER JOIN HRMS_CENTER on (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
					+ "   INNER JOIN HRMS_DEPT on (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT) "
					+ "   INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) "
					+ "   INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY) "
					+ "  WHERE REQS_STATUS='O' AND REQS_APPROVAL_STATUS IN ('A','Q') AND VACAN_STATUS='O'  	";
					
			
			query += whereClause;
			
			query +="  ORDER BY REQS_DATE DESC";
			
			Object[][] expDetail = getSqlModel().getSingleResult(query);
			

			Object[][] objTabularData = new Object[expDetail.length][10];
			String[] columns = new String[] { "Sr. No.","Hiring Manager", "Requisiton Title",
					"Position", "Requisiton Date", "No. of Vacancy",
					"Required By Date", "Division","Branch","Department"};
			int[] bcellAlign = new int[] { 0, 0, 0, 0, 2, 2, 1,2,2,2,2 };
			int[] bcellWidth = new int[] { 2, 25, 20, 15, 10, 10, 25, 10, 10, 25,15 };

			int count = 1;
			
			if (expDetail != null && expDetail.length > 0) {
				Object[][] expenseDtlDetName = new Object[1][1];
				 expenseDtlDetName[0][0] = "Vacancy Management Details ";
					TableDataSet expenseDtlDetNameTable = new TableDataSet();
					expenseDtlDetNameTable.setData(expenseDtlDetName);
					expenseDtlDetNameTable.setCellAlignment(new int[] { 1 });
					expenseDtlDetNameTable.setCellWidth(new int[] { 100 });
					expenseDtlDetNameTable.setBodyFontDetails(Font.HELVETICA, 10,
							Font.BOLD, new Color(0, 0, 0));
					expenseDtlDetNameTable.setBodyBGColor(new Color(192, 192, 192));
					rg.addTableToDoc(expenseDtlDetNameTable);
					expenseDtlDetNameTable.setBlankRowsAbove(1);
				//	expenseDtlDetNameTable.setBlankRowsBelow(1);
				for (int i = 0; i < expDetail.length; i++) {
					
					objTabularData[i][0] = count++;
					objTabularData[i][1] = String.valueOf(expDetail[i][3]);
					objTabularData[i][2] = String.valueOf(expDetail[i][8]);
					objTabularData[i][3] = String.valueOf(expDetail[i][1]);
					objTabularData[i][4] = String.valueOf(expDetail[i][4]);
					objTabularData[i][5] = String.valueOf(expDetail[i][5]);
					objTabularData[i][6] = String.valueOf(expDetail[i][6]);
					objTabularData[i][7] = String.valueOf(expDetail[i][14]);
					objTabularData[i][8] = String.valueOf(expDetail[i][15]);
					objTabularData[i][9] = String.valueOf(expDetail[i][13]);

					
				}
			}
			else
			{
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}

			Object[][] expenseDtlDet = new Object[1][1];
			expenseDtlDet[0][0] = " ";
			TableDataSet expenseDtlDetTable = new TableDataSet();
			expenseDtlDetTable.setData(expenseDtlDet);
			expenseDtlDetTable.setCellAlignment(new int[] { 1 });
			expenseDtlDetTable.setCellWidth(new int[] { 100 });
			expenseDtlDetTable.setBodyFontDetails(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0));
			expenseDtlDetTable.setBodyBGColor(new Color(192, 192, 192));
			rg.addTableToDoc(expenseDtlDetTable);
			/// expenseDtlDetTable.setBlankRowsAbove(1);
			 expenseDtlDetTable.setBlankRowsBelow(1);
			 
			
			 

			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(columns);
			tdstable.setData(objTabularData);
			tdstable.setCellAlignment(bcellAlign);
			tdstable.setCellWidth(bcellWidth);
			tdstable.setBorder(true);
			tdstable.setHeaderBGColor(new Color(225, 225, 225));
			rg.addTableToDoc(tdstable);

			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // End of Function for report
*/
}