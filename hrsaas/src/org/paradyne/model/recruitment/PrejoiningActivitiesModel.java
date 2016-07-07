package org.paradyne.model.recruitment;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.prejoiningActivities;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;

/*
 * @author saipavan voleti
 *  
 */

public class PrejoiningActivitiesModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PrejoiningActivitiesModel.class);

	/**
	 * @param bean
	 * @param request
	 *            purpose: showing check list details.
	 */
	public void showCheckList(prejoiningActivities bean,
			HttpServletRequest request) {
		try {
			if (String.valueOf(bean.getCheckListType()) != "") {
				String Query = "SELECT CHECK_CODE,CHECK_NAME  FROM HRMS_CHECKLIST	WHERE CHECK_TYPE='"
						+ bean.getCheckListType() + "'	AND CHECK_STATUS='A'";

				Object obj[][] = getSqlModel().getSingleResult(Query);
				Object empobj[][] = new Object[obj.length][3];

				ArrayList<Object> list = new ArrayList<Object>();
				if (obj != null && obj.length > 0) {

					for (int i = 0; i < obj.length; i++) {
						prejoiningActivities bean1 = new prejoiningActivities();
						bean1.setPrejoinListName(String.valueOf(obj[i][1]));
						bean1.setPrejoinListCode(String.valueOf(obj[i][0]));
						bean1.setPrejoinTargetDate("");
						empobj[i][0] = "";
						empobj[i][1] = "";
						empobj[i][2] = "";

						list.add(bean1);
					}
					bean.setPrejoiningList(list);
					request.setAttribute("funcObj", empobj);
					// checkListitemcode checkListName checkListresponce
					// checkListComments
					bean.setPrejoiningListLength(String.valueOf(list.size()));
					bean.setNoData("false");
				} else {
					bean.setPrejoiningListLength("0");
					bean.setNoData("true");
				}

			}
			bean.setChecklistTable(true);
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

	/**
	 * @param bean
	 * @param request
	 *            purpose: showing pre joining check list details.
	 */
	public void setCheckList(prejoiningActivities bean,
			HttpServletRequest request) {
		try {
			if (String.valueOf(bean.getCheckListType()) != "") {
				request.setAttribute("listname", String.valueOf(bean
						.getCheckListType()));

				String Query = "select (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) as employeename,"
						+ "PREJOIN_DTL_RESPERSON,PREJOIN_DTL_ACTREQUIRED,HRMS_CHECKLIST.CHECK_NAME,"
						+ "	to_char(PREJOIN_DTL_TARDATE,'dd-mm-yyyy') "
						+ "	  ,HRMS_REC_PREJOIN_DTL.CHECKLIST_CODE,PREJOIN_DTL_ACTSTATUS,decode(PREJOIN_DTL_ACTREQUIRED,'Y','Yes','N','No'),decode(PREJOIN_DTL_ACTSTATUS,'O','Open','C','Close'),"
						+ "HRMS_REC_PREJOIN_DTL.PREJOIN_DTL_CODE"
						+ "		from HRMS_REC_PREJOIN_DTL "
						+ "	left JOIN HRMS_REC_PREJOIN ON (HRMS_REC_PREJOIN.PREJOIN_CODE=HRMS_REC_PREJOIN_DTL.PREJOIN_CODE)"
						+ "	left JOIN HRMS_CHECKLIST ON (HRMS_CHECKLIST.CHECK_CODE=HRMS_REC_PREJOIN_DTL.CHECKLIST_CODE)"
						+ "	 left join hrms_emp_offc on(hrms_emp_offc.EMP_ID=HRMS_REC_PREJOIN_DTL.PREJOIN_DTL_RESPERSON)"
						+ "	where HRMS_REC_PREJOIN_DTL.PREJOIN_CODE="
						+ bean.getPrejoinCode()
						+ "  and HRMS_CHECKLIST.CHECK_TYPE='"
						+ bean.getCheckListType()
						+ "' and HRMS_CHECKLIST.CHECK_STATUS='A' order by PREJOIN_DTL_ACTREQUIRED desc ";

				Object obj[][] = getSqlModel().getSingleResult(Query);
				ArrayList<Object> list = new ArrayList<Object>();
				if (obj != null && obj.length > 0) {

					request.setAttribute("funcObj", obj);

					for (int i = 0; i < obj.length; i++) {
						prejoiningActivities prebean = new prejoiningActivities();

						prebean.setResponsiblePersonName(checkNull(String
								.valueOf(obj[i][0])));
						prebean.setRespName(String.valueOf(obj[i][0]));
						prebean.setRespCode(String.valueOf(obj[i][1]));
						prebean.setResponsiblePersonCode(checkNull(String
								.valueOf(obj[i][1])));

						prebean.setCheckListCode(String.valueOf(obj[i][5]));
						prebean.setPrejoinListName(String.valueOf(obj[i][3]));
						prebean.setPrejoinTargetDate(checkNull(String
								.valueOf(obj[i][4])));

						prebean.setActivityRequired(String.valueOf(obj[i][2]));

						prebean.setActivityStatus(String.valueOf(obj[i][6]));
						prebean.setPrejoinListCode(String.valueOf(obj[i][5]));

						prebean.setDupactivityRequired(String
								.valueOf(obj[i][7]));
						prebean.setDupactivityStatus(String.valueOf(obj[i][8]));
						list.add(prebean);
					}
					bean.setPrejoiningList(list);
					bean.setPrejoiningListLength(String.valueOf(list.size()));
					bean.setNoData("false");
				} else {
					bean.setPrejoiningListLength("0");
					bean.setNoData("true");
				}
			}
			bean.setChecklistTable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bean
	 * @param ckcode
	 * @param targetdate
	 * @param resperson
	 * @param actRequired
	 * @param status
	 * @return boolean after saving pre joining application.
	 */
	public boolean save(prejoiningActivities bean, Object[] ckcode,
			Object[] targetdate, Object[] resperson, Object[] actRequired,
			Object[] status) {
		boolean result = false;
		try {
			Object add[][] = new Object[1][8];
			add[0][0] = bean.getReqCode();
			add[0][1] = bean.getCandidateCode();
			add[0][2] = bean.getReportingTo();
			add[0][3] = bean.getOfferDate();
			add[0][4] = bean.getJoinDate();
			add[0][5] = bean.getCheckListType();
			if (bean.getAppstatus().equalsIgnoreCase("Close")) {
				add[0][6] = "C";
			} else {
				add[0][6] = "O";
			}
			add[0][7] = bean.getUserEmpId();
			result = getSqlModel().singleExecute(getQuery(1), add);

			if (result) {
				String query = " SELECT NVL(MAX(PREJOIN_CODE),0) FROM HRMS_REC_PREJOIN";
				Object resultCode[][] = getSqlModel().getSingleResult(query);
				int loopcount = 0;
				if (ckcode != null) {
					for (int i = 0; i < ckcode.length; i++) {
						Object chkDtl[][] = new Object[1][6];
						chkDtl[0][0] = String.valueOf(resultCode[0][0]);
						chkDtl[0][1] = String.valueOf(ckcode[i]);
						chkDtl[0][4] = String.valueOf(actRequired[i]);
						if (String.valueOf(actRequired[i]).equals("N")) {

						} else {
							chkDtl[0][2] = String.valueOf(targetdate[i]);
							chkDtl[0][3] = String.valueOf(resperson[i]);
						}

						if (String.valueOf(actRequired[i]).equals("N")) {
							loopcount++;
							chkDtl[0][5] = "C";
						} else {
							chkDtl[0][5] = String.valueOf(status[i]);
						}

						result = getSqlModel().singleExecute(getQuery(2),
								chkDtl);
					}
				}
				bean.setPrejoinCode(String.valueOf(resultCode[0][0]));

				int listlength = 0;
				for (int z = 0; z < ckcode.length; z++) {
					if (String.valueOf(status[z]).equals("C"))
						listlength++;
				}

				/*
				 * changing the pre joining application status.
				 */
				if (loopcount == ckcode.length || listlength == ckcode.length) {
					String updatehdr = " update HRMS_REC_PREJOIN set PREJOIN_STATUS='C' where PREJOIN_CODE="
							+ String.valueOf(resultCode[0][0]);
					boolean result1 = getSqlModel().singleExecute(updatehdr);
				} else {
					String dtlrecords = "select  PREJOIN_CODE from HRMS_REC_PREJOIN_DTL where PREJOIN_DTL_ACTSTATUS='C' and PREJOIN_CODE="
							+ String.valueOf(resultCode[0][0]);

					String countofdtl = "select  PREJOIN_CODE from HRMS_REC_PREJOIN_DTL where  PREJOIN_CODE="
							+ String.valueOf(resultCode[0][0]);

					Object dtlresult[][] = getSqlModel().getSingleResult(
							dtlrecords);

					Object countresult[][] = getSqlModel().getSingleResult(
							countofdtl);

					if (dtlresult.length == countresult.length) {
						String updatehdr = " update HRMS_REC_PREJOIN set PREJOIN_STATUS='C' where PREJOIN_CODE="
								+ String.valueOf(resultCode[0][0]);
						boolean result1 = getSqlModel()
								.singleExecute(updatehdr);
					}
				}

				if (result) {
					generateMailTemplate(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param bean
	 * @param ckcode
	 * @param targetdate
	 * @param resperson
	 * @param actRequired
	 * @param status
	 * @return boolean after updating application.
	 */
	public boolean update(prejoiningActivities bean, Object[] ckcode,
			Object[] targetdate, Object[] resperson, Object[] actRequired,
			Object[] status) {
		boolean result = false;
		try {
			Object add[][] = new Object[1][9];
			add[0][0] = bean.getReqCode();
			add[0][1] = bean.getCandidateCode();
			add[0][2] = bean.getReportingTo();
			add[0][3] = bean.getOfferDate();
			add[0][4] = bean.getJoinDate();
			add[0][5] = bean.getCheckListType();
			if (bean.getAppstatus().equalsIgnoreCase("Close")) {
				add[0][6] = "C";
			} else{
				add[0][6] = "O";
			}
			add[0][7] = bean.getUserEmpId();
			add[0][8] = bean.getPrejoinCode();
			result = getSqlModel().singleExecute(getQuery(3), add);
			if (result) {
				String delQuery = "delete from HRMS_REC_PREJOIN_DTL where PREJOIN_CODE="
						+ bean.getPrejoinCode();
				result = getSqlModel().singleExecute(delQuery);
				int loopcount = 0;
				if (ckcode != null) {
					for (int i = 0; i < ckcode.length; i++) {
						Object chkDtl[][] = new Object[1][6];
						chkDtl[0][0] = String.valueOf(bean.getPrejoinCode());
						chkDtl[0][1] = String.valueOf(ckcode[i]);
						chkDtl[0][4] = String.valueOf(actRequired[i]);
						chkDtl[0][5] = String.valueOf(status[i]);
						if (String.valueOf(actRequired[i]).equals("N")) {

						} else {
							chkDtl[0][2] = String.valueOf(targetdate[i]);
							chkDtl[0][3] = String.valueOf(resperson[i]);
						}

						if (String.valueOf(actRequired[i]).equals("N")) {
							loopcount++;
							chkDtl[0][5] = "C";
						} else{
							chkDtl[0][5] = String.valueOf(status[i]);
						}
						result = getSqlModel().singleExecute(getQuery(2),
								chkDtl);
					}

					int listlength = 0;
					for (int z = 0; z < ckcode.length; z++) {
						if (String.valueOf(status[z]).equals("C"))
							listlength++;
					}

					if (loopcount == ckcode.length
							|| listlength == ckcode.length) {
						String updatehdr = " update HRMS_REC_PREJOIN set PREJOIN_STATUS='C' where PREJOIN_CODE="
								+ bean.getPrejoinCode();
						boolean result1 = getSqlModel()
								.singleExecute(updatehdr);
					} else {
						String dtlrecords = "select  PREJOIN_CODE from HRMS_REC_PREJOIN_DTL where PREJOIN_DTL_ACTSTATUS='C' and PREJOIN_CODE="
								+ bean.getPrejoinCode();
						String countofdtl = "select  PREJOIN_CODE from HRMS_REC_PREJOIN_DTL where  PREJOIN_CODE="
								+ bean.getPrejoinCode();
						Object dtlresult[][] = getSqlModel().getSingleResult(
								dtlrecords);
						Object countresult[][] = getSqlModel().getSingleResult(
								countofdtl);
						if (dtlresult.length == countresult.length) {
							String updatehdr = " update HRMS_REC_PREJOIN set PREJOIN_STATUS='C' where PREJOIN_CODE="
									+ bean.getPrejoinCode();
							boolean result1 = getSqlModel().singleExecute(
									updatehdr);
						}

					}
					  if(result) {
						  	generateMailTemplate(bean); 
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param bean
	 * @param status
	 *            purpose:setting pre joining check list application details
	 * @param jointDate
	 * @param joinfDate
	 * @param offtDate
	 * @param offfDate
	 * @param position1
	 * @param candidate1
	 */
	public void setprejoinchececkList(prejoiningActivities bean, String status,
			String candidate1, String position1, String offfDate,
			String offtDate, String joinfDate, String jointDate,
			HttpServletRequest request) {
		int count = 0;
		String concatStr = "";
		try {
			String prejoinQuery = "";
			prejoinQuery = "select CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME as name,"
					+ " HRMS_RANK.RANK_NAME,to_char(PREJOIN_OFFERDATE,'dd-mm-yyyy'),"
					+ " to_char(PREJOIN_JOINDATE,'dd-mm-yyyy'),(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) as empname,"
					+ "  decode(PREJOIN_STATUS,'O','Open','C','Close'), "
					+ " PREJOIN_CODE,PREJOIN_CAND_CODE, PREJOIN_REPORTERCODE,PREJOIN_CHECK_LIST"
					+ " from HRMS_REC_PREJOIN  "
					+ " inner JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_PREJOIN.PREJOIN_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+ " inner JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_PREJOIN.PREJOIN_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE) "
					+ " inner join hrms_emp_offc on(hrms_emp_offc.EMP_ID=HRMS_REC_PREJOIN.PREJOIN_REPORTERCODE) "
					+ " inner join HRMS_RANK on(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID)";

			if (status.equalsIgnoreCase("C")) {
				prejoinQuery += " where PREJOIN_STATUS='C'  ";
			} else {
				prejoinQuery += " where PREJOIN_STATUS='O'  ";
			}
			if (bean.getApplyFilterFlag().equals("true")) {
				if (!bean.getCandCode1().equals("")) {
					prejoinQuery += " AND HRMS_REC_PREJOIN.PREJOIN_CAND_CODE="
							+ bean.getCandCode1();
					concatStr += candidate1 + " :" + bean.getCandidateName1()
							+ ",";
				} 
				if (!bean.getPositionId().equals("")) {
					prejoinQuery += " AND RANK_ID=" + bean.getPositionId();
					concatStr += position1 + " :" + bean.getPositionName()
							+ ",";
				} 
				if (!(bean.getOfferFDate().trim().equals("") || bean
						.getOfferFDate().trim().equals("null"))) {
					prejoinQuery = prejoinQuery
							+ " AND PREJOIN_OFFERDATE >=TO_DATE(" + "'"
							+ bean.getOfferFDate() + "'" + ",'DD-MM-YYYY')";
					concatStr += offfDate + " :" + bean.getOfferFDate() + ",";
				} 
				if (!(bean.getOfferTDate().trim().equals("") || bean
						.getOfferTDate().trim().equals("null"))) {
					prejoinQuery = prejoinQuery
							+ " AND PREJOIN_OFFERDATE <=TO_DATE(" + "'"
							+ bean.getOfferTDate() + "'" + ",'DD-MM-YYYY')";
					concatStr += offtDate + " :" + bean.getOfferTDate() + ",";
				} 
				if (!(bean.getJoinFDate().trim().equals("") || bean
						.getJoinFDate().trim().equals("null"))) {
					prejoinQuery = prejoinQuery
							+ " AND PREJOIN_JOINDATE >=TO_DATE(" + "'"
							+ bean.getJoinFDate() + "'" + ",'DD-MM-YYYY')";
					concatStr += joinfDate + " :" + bean.getJoinFDate() + ",";
				} 
				if (!(bean.getJoinTDate().trim().equals("") || bean
						.getJoinTDate().trim().equals("null"))) {
					prejoinQuery = prejoinQuery
							+ " AND PREJOIN_JOINDATE <=TO_DATE(" + "'"
							+ bean.getJoinTDate() + "'" + ",'DD-MM-YYYY')";
					concatStr += jointDate + " :" + bean.getJoinTDate() + ",";
				} 
			} else {
				bean.setApplyFilterFlag("false");
			}
			if (bean.getUserProfileDivision().length() > 0) {

				prejoinQuery += " AND HRMS_REC_REQS_HDR.REQS_DIVISION IN ("
						+ bean.getUserProfileDivision() + ")";
			}
			prejoinQuery += " ORDER BY  REQS_DIVISION";

			Object obj[][] = getSqlModel().getSingleResult(prejoinQuery);
			if (obj != null && obj.length > 0) {
				bean.setModeLength("true");
			} else {
				bean.setModeLength("false");
			}
			String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length,
					20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("abc", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("xyz", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			ArrayList<Object> list = new ArrayList<Object>();
			if (obj != null && obj.length > 0) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					prejoiningActivities bgcheck = new prejoiningActivities();
					bgcheck.setLcandidate(String.valueOf(obj[i][0]));
					bgcheck.setLposition(String.valueOf(obj[i][1]));
					bgcheck.setLofferDate(String.valueOf(obj[i][2]));
					bgcheck.setLjoinDate(String.valueOf(obj[i][3]));
					bgcheck.setLreporterName(String.valueOf(obj[i][4]));
					bgcheck.setLofferstatus(String.valueOf(obj[i][5]));
					bgcheck.setLoffercode(String.valueOf(obj[i][6]));  
					list.add(bgcheck);
				}
				bean.setLChkList(list);
				bean.setNoData("false");
				bean.setTotalRecords(String.valueOf(obj.length));

			} else
				bean.setNoData("true");
			bean.setTotalRecords(String.valueOf(obj.length));

			bean.setLChkListLength(String.valueOf(list.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String[] dispArr = concatStr.split(",");
			request.setAttribute("dispArr", dispArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param perbean
	 * @param request
	 *            purpose setting selected pre joining application details.
	 */
	public void f9searchdtl(prejoiningActivities perbean,
			HttpServletRequest request) {
		try {
			String Reqquery = " select REQS_CODE,REQS_NAME,RANK_NAME,decode(REQS_STATUS,'O','Open','C','Close'),"
					+ " HRMS_DIVISION.DIV_NAME,hrms_center.CENTER_NAME,HRMS_DEPT.DEPT_NAME"
					+ " from HRMS_REC_REQS_HDR "
					+ " inner join HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
					+ " inner join hrms_center ON (hrms_center.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
					+ " inner join HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
					+ " inner join HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
					+ " Where REQS_CODE=" + perbean.getReqCode();

			String Candquery = "SELECT CAND_CODE,CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME from HRMS_REC_CAND_DATABANK "
					+ " Where CAND_CODE=" + perbean.getCandidateCode();
			Object Reqdtl[][] = getSqlModel().getSingleResult(Reqquery);
			Object Candtl[][] = getSqlModel().getSingleResult(Candquery);
			if (Reqdtl != null && Reqdtl.length > 0) {
				perbean.setReqCode(String.valueOf(Reqdtl[0][0]));
				perbean.setReqName(String.valueOf(Reqdtl[0][1]));
				perbean.setPosition(String.valueOf(Reqdtl[0][2]));
				perbean.setActivityStatus(String.valueOf(Reqdtl[0][3]));
				perbean.setDivision(String.valueOf(Reqdtl[0][4]));
				perbean.setBranch(String.valueOf(Reqdtl[0][5]));
				perbean.setDepartment(String.valueOf(Reqdtl[0][6]));
			}
			if (Candtl != null && Candtl.length > 0) {
				perbean.setCandidateCode(String.valueOf(Candtl[0][0]));
				perbean.setCandidateName(String.valueOf(Candtl[0][1]));
			}
			String PreQuery = "Select PREJOIN_CHECK_LIST ,DECODE(PREJOIN_CHECK_LIST,'J','Joining CheckList','M','Medical CheckList','T','Transfer CheckList','I','Interview CheckList','B','Background Verification CheckList','P','Pre Joining Activities')as listname,"
					+ " to_char(PREJOIN_OFFERDATE,'dd-mm-yyyy'),to_char(PREJOIN_JOINDATE,'dd-mm-yyyy'),decode(PREJOIN_STATUS,'O','Open','C','Close') "
					+ " ,PREJOIN_REPORTERCODE,(hrms_emp_offc.EMP_FNAME||' '||hrms_emp_offc.EMP_MNAME||' '||hrms_emp_offc.EMP_LNAME) as name "
					+ " ,PREJOIN_CODE FROM HRMS_REC_PREJOIN  "
					+ " inner join hrms_emp_offc on(hrms_emp_offc.EMP_ID=HRMS_REC_PREJOIN.PREJOIN_REPORTERCODE)"
					+ " where PREJOIN_CODE=" + perbean.getPrejoinCode();
			Object preQuerydtl[][] = getSqlModel().getSingleResult(PreQuery);
			perbean.setCheckListType(String.valueOf(preQuerydtl[0][0]));
			perbean.setDupcheckListType(String.valueOf(preQuerydtl[0][1]));
			perbean.setOfferDate(String.valueOf(preQuerydtl[0][2]));
			perbean.setJoinDate(String.valueOf(preQuerydtl[0][3]));
			perbean.setActivityStatus(String.valueOf(preQuerydtl[0][4]));
			perbean.setAppstatus(String.valueOf(preQuerydtl[0][4]));
			perbean.setReportingTo(String.valueOf(preQuerydtl[0][5]));
			perbean.setReportingName(String.valueOf(preQuerydtl[0][6]));
			perbean.setPrejoinCode(String.valueOf(preQuerydtl[0][7]));
			setCheckList(perbean, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bean
	 * @return boolean after deleting the application.
	 */
	public boolean deleteprecheck(prejoiningActivities bean) {
		boolean result = false;
		try {
			String delpre = "DELETE FROM HRMS_REC_PREJOIN WHERE PREJOIN_CODE ="
					+ bean.getPrejoinCode();
			String delpreDtl = "DELETE FROM HRMS_REC_PREJOIN_DTL WHERE PREJOIN_CODE="
					+ bean.getPrejoinCode();
				result = getSqlModel().singleExecute(delpreDtl);
				if (result) {
					result = getSqlModel().singleExecute(delpre);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * pre joining application details....!!
	 * 
	 */
	public void preCheckRecord(prejoiningActivities bean,
			HttpServletRequest request) {
		try {
			String precheckquery = "SELECT PREJOIN_CAND_CODE,CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME AS NAME,"
					+ " PREJOIN_REQS_CODE, HRMS_REC_REQS_HDR.REQS_NAME, "
					+ " PREJOIN_REPORTERCODE,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) AS NAME, "
					+ " TO_CHAR(PREJOIN_OFFERDATE,'DD-MM-YYYY'),TO_CHAR(PREJOIN_JOINDATE,'DD-MM-YYYY'),PREJOIN_CHECK_LIST,PREJOIN_STATUS,PREJOIN_CODE,DECODE(PREJOIN_STATUS,'O','OPEN','C','CLOSE') "
					+ " FROM HRMS_REC_PREJOIN INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_PREJOIN.PREJOIN_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE) "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_PREJOIN.PREJOIN_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_PREJOIN.PREJOIN_REPORTERCODE) "
					+ " WHERE PREJOIN_CODE=" + bean.getPrejoinCode();
			Object preRecorddtl[][] = getSqlModel().getSingleResult(
					precheckquery);
			if (preRecorddtl != null && preRecorddtl.length > 0) {
				bean.setCandidateCode(String.valueOf(preRecorddtl[0][0]));
				bean.setCandidateName(String.valueOf(preRecorddtl[0][1]));
				bean.setReqCode(String.valueOf(preRecorddtl[0][2]));
				bean.setReqName(String.valueOf(preRecorddtl[0][3]));
				bean.setReportingTo(String.valueOf(preRecorddtl[0][4]));
				bean.setReportingName(String.valueOf(preRecorddtl[0][5]));
				bean.setOfferDate(String.valueOf(preRecorddtl[0][6]));
				bean.setJoinDate(String.valueOf(preRecorddtl[0][7]));
				bean.setCheckListType(String.valueOf(preRecorddtl[0][8]));
				bean.setActivityStatus(String.valueOf(preRecorddtl[0][9]));
				bean.setPrejoinCode(String.valueOf(preRecorddtl[0][10]));
				bean.setAppstatus(String.valueOf(preRecorddtl[0][11]));
				String Reqquery = " select HRMS_RANK.RANK_name,decode(REQS_STATUS,'O','Open','C','Close'),"
						+ " HRMS_DIVISION.DIV_NAME,hrms_center.CENTER_NAME,HRMS_DEPT.DEPT_NAME"
						+ " from HRMS_REC_REQS_HDR "
						+ " inner join HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " inner join hrms_center ON (hrms_center.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
						+ " inner join HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						+ " inner join HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
						+ " Where REQS_CODE=" + bean.getReqCode();
				Object Reqdtl[][] = getSqlModel().getSingleResult(Reqquery);
				if (Reqdtl != null && Reqdtl.length > 0) {
					bean.setPosition(String.valueOf(Reqdtl[0][0]));
					bean.setDivision(String.valueOf(Reqdtl[0][2]));
					bean.setBranch(String.valueOf(Reqdtl[0][3]));
					bean.setDepartment(String.valueOf(Reqdtl[0][4]));
				}
				setCheckList(bean, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectedrecord(prejoiningActivities prebean,
			HttpServletRequest request) {
		try {
			String query = "select CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME as name, "
					+ " HRMS_REC_REQS_HDR.REQS_NAME,PREJOIN_CODE ,(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) as empname, "
					+ " PREJOIN_REQS_CODE,PREJOIN_CAND_CODE, PREJOIN_REPORTERCODE, to_char(PREJOIN_OFFERDATE,'dd-mm-yyyy'),to_char(PREJOIN_JOINDATE,'dd-mm-yyyy'), PREJOIN_CHECK_LIST,"
					+ " decode(PREJOIN_STATUS,'O','Open','C','Close') ,"
					+ " DECODE(PREJOIN_CHECK_LIST,'J','Joining CheckList','M','Medical CheckList','T','Transfer CheckList','I','Interview CheckList','B','Background Verification CheckList','P','Pre Joining Activities')as listname "
					+ " from HRMS_REC_PREJOIN "
					+ " inner JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_PREJOIN.PREJOIN_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+ " inner JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_PREJOIN.PREJOIN_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					+ " inner join hrms_emp_offc on(hrms_emp_offc.EMP_ID=HRMS_REC_PREJOIN.PREJOIN_REPORTERCODE)"
					+ " WHERE PREJOIN_CODE=" + prebean.getHiddencode();
			Object preRecorddtl[][] = getSqlModel().getSingleResult(query);
			if (preRecorddtl != null && preRecorddtl.length > 0) {
				prebean.setCandidateName(String.valueOf(preRecorddtl[0][0]));
				prebean.setReqName(String.valueOf(preRecorddtl[0][1]));
				prebean.setPrejoinCode(String.valueOf(preRecorddtl[0][2]));
				prebean.setReportingName(String.valueOf(preRecorddtl[0][3]));
				prebean.setReqCode(String.valueOf(preRecorddtl[0][4]));
				prebean.setCandidateCode(String.valueOf(preRecorddtl[0][5]));
				prebean.setReportingTo(String.valueOf(preRecorddtl[0][6]));
				prebean.setOfferDate(String.valueOf(preRecorddtl[0][7]));
				prebean.setJoinDate(String.valueOf(preRecorddtl[0][8]));
				prebean.setCheckListType(String.valueOf(preRecorddtl[0][9]));
				prebean.setAppstatus(String.valueOf(preRecorddtl[0][10]));
				prebean.setDupcheckListType(String.valueOf(preRecorddtl[0][11]));
				String Reqquery = " select REQS_CODE,REQS_NAME,RANK_NAME,decode(REQS_STATUS,'O','Open','C','Close'),"
						+ " HRMS_DIVISION.DIV_NAME,hrms_center.CENTER_NAME,HRMS_DEPT.DEPT_NAME"
						+ " from HRMS_REC_REQS_HDR "
						+ " inner join HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " inner join hrms_center ON (hrms_center.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
						+ " inner join HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						+ " inner join HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
						+ " Where REQS_CODE=" + prebean.getReqCode();
				Object Reqdtl[][] = getSqlModel().getSingleResult(Reqquery);
				if (Reqdtl != null && Reqdtl.length > 0) {
					prebean.setReqCode(String.valueOf(Reqdtl[0][0]));
					prebean.setPosition(String.valueOf(Reqdtl[0][2]));
					prebean.setDivision(String.valueOf(Reqdtl[0][4]));
					prebean.setBranch(String.valueOf(Reqdtl[0][5]));
					prebean.setDepartment(String.valueOf(Reqdtl[0][6]));
				}
				setCheckList(prebean, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * methods for monitoring..!
	 */

	public void setActivityMonitor(prejoiningActivities prebean, String status,
			String candidate, String position, String frmDdate, String toDate,
			HttpServletRequest request) {
		int count = 0;
		String concatStr = "";
		try {
			String prejoinQuery = "";
			prejoinQuery = "SELECT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME AS NAME,HRMS_RANK.RANK_NAME, "
					+ " TO_CHAR(PREJOIN_DTL_TARDATE,'DD-MM-YYYY'), "
					+ " HRMS_CHECKLIST.CHECK_NAME, "
					+ " PREJOIN_DTL_ACTSTATUS,"
					+ " HRMS_REC_PREJOIN_DTL.PREJOIN_DTL_CODE,DECODE(PREJOIN_DTL_ACTSTATUS,'O','OPEN','C','CLOSE') "
					+ " ,HRMS_REC_PREJOIN_DTL.PREJOIN_CODE FROM HRMS_REC_PREJOIN_DTL "
					+ " LEFT JOIN HRMS_REC_PREJOIN ON (HRMS_REC_PREJOIN.PREJOIN_CODE=HRMS_REC_PREJOIN_DTL.PREJOIN_CODE) "
					+ " LEFT JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_PREJOIN.PREJOIN_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE) "
					+ " LEFT JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_PREJOIN.PREJOIN_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+ " LEFT JOIN HRMS_CHECKLIST ON (HRMS_CHECKLIST.CHECK_CODE=HRMS_REC_PREJOIN_DTL.CHECKLIST_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_PREJOIN_DTL.PREJOIN_DTL_RESPERSON)"
					+ " INNER JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID) "
					+ " WHERE HRMS_CHECKLIST.CHECK_STATUS='A' "
					+ " AND HRMS_REC_PREJOIN_DTL.PREJOIN_DTL_ACTREQUIRED='Y' ";
			if (status.equalsIgnoreCase("C")) {
				prejoinQuery += " AND HRMS_REC_PREJOIN_DTL.PREJOIN_DTL_ACTSTATUS='C'	"
						+ " AND HRMS_REC_PREJOIN_DTL.PREJOIN_DTL_RESPERSON="
						+ prebean.getUserEmpId();
				prebean.setConduct("false");
				prebean.setBgStatus("C");
			} else {
				prejoinQuery += " AND HRMS_REC_PREJOIN_DTL.PREJOIN_DTL_ACTSTATUS='O'	"
						+ " AND HRMS_REC_PREJOIN_DTL.PREJOIN_DTL_RESPERSON="
						+ prebean.getUserEmpId();
				prebean.setConduct("true");
				prebean.setBgStatus("P");
			}
			if (prebean.getApplyFilterFlag().equals("true")) {
				if (!prebean.getCandCode1().equals("")) {
					prejoinQuery += "AND HRMS_REC_PREJOIN.PREJOIN_CAND_CODE="
							+ prebean.getCandCode1();
					concatStr += candidate + " :" + prebean.getCandidateName1()
							+ ",";
				} 
				if (!prebean.getPositionId().equals("")) {
					prejoinQuery += "AND RANK_ID='" + prebean.getPositionId()
							+ "'";
					concatStr += position + " :" + prebean.getPositionName()
							+ ",";

				} 

				if (!(prebean.getTargetFDate().trim().equals("") || prebean
						.getTargetFDate().trim().equals("null"))) {
					prejoinQuery = prejoinQuery
							+ " AND PREJOIN_DTL_TARDATE >=TO_DATE(" + "'"
							+ prebean.getTargetFDate() + "'" + ",'DD-MM-YYYY')";
					concatStr += frmDdate + " :" + prebean.getTargetFDate()
							+ ",";

				} 
				if (!(prebean.getTargetTDate().trim().equals("") || prebean
						.getTargetTDate().trim().equals("null"))) {
					prejoinQuery = prejoinQuery
							+ " AND PREJOIN_DTL_TARDATE <=TO_DATE(" + "'"
							+ prebean.getTargetTDate() + "'" + ",'DD-MM-YYYY')";
					concatStr += toDate + " :" + prebean.getTargetTDate() + ",";

				} 

				if (prebean.getUserProfileDivision() != null
						&& !prebean.getUserProfileDivision().equals("")) {
					prejoinQuery += " AND REQS_DIVISION IN ("
							+ prebean.getUserProfileDivision() + ")";

				}

			} else {
				prebean.setApplyFilterFlag("false");
			}
			prejoinQuery += " order by REQS_DIVISION ";

			Object obj[][] = getSqlModel().getSingleResult(prejoinQuery);

			if (obj != null && obj.length > 0) {
				prebean.setModeLength("true");
			} else {
				prebean.setModeLength("false");
			}

			String[] pageIndex = Utility.doPaging(prebean.getMyPage(),
					obj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("abc", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("xyz", Integer.parseInt(String
					.valueOf(pageIndex[3])));

			if (pageIndex[4].equals("1"))
				prebean.setMyPage("1");

			ArrayList<Object> list = new ArrayList<Object>();

			if (obj != null && obj.length > 0) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					prejoiningActivities bgcheck = new prejoiningActivities();
					bgcheck.setLcandidate(String.valueOf(obj[i][0]));
					bgcheck.setLposition(String.valueOf(obj[i][1]));
					bgcheck.setLtargetDate(checkNull(String.valueOf(obj[i][2])));  
					bgcheck.setLcheckListname(String.valueOf(obj[i][3]));  
					bgcheck.setLactivityRequired(String.valueOf(obj[i][4]));
					bgcheck.setLchecklistdtlcode(String.valueOf(obj[i][5]));
					bgcheck.setDupactivityRequired(String.valueOf(obj[i][6]));
					bgcheck.setLreqcode(String.valueOf(obj[i][7]));  
					list.add(bgcheck);
				}
				prebean.setMonitorList(list);
				prebean.setNoData("false");
				prebean.setTotalRecords(String.valueOf(obj.length));
			} else
				prebean.setNoData("true");
			prebean.setLChkListLength(String.valueOf(list.size()));
			prebean.setTotalRecords(String.valueOf(obj.length));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String[] dispArr = concatStr.split(",");
			request.setAttribute("dispArr", dispArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param prebean
	 * @param ckcode
	 * @param actRequired
	 * @param prejoincodes
	 * @return boolean after changing the application status.
	 */
	public boolean monitersave(prejoiningActivities prebean, Object[] ckcode,
			Object[] actRequired, Object[] prejoincodes) {
		boolean result = false;
		String prejoindtlcodes = "";
		try {
			if (ckcode != null && ckcode.length > 0) {
				for (int i = 0; i < ckcode.length; i++) {
					if (!String.valueOf(ckcode[i]).equals("")) {
						if (String.valueOf(actRequired[i]).equals("C")) {
							String updatestatus = " update HRMS_REC_PREJOIN_DTL set PREJOIN_DTL_ACTSTATUS='"
									+ String.valueOf(actRequired[i])
									+ "'  where PREJOIN_DTL_CODE="
									+ String.valueOf(ckcode[i]);
							result = getSqlModel().singleExecute(updatestatus);
							if (result)
								prejoindtlcodes += ckcode[i] + ",";
						}
					}
				}
				if (result) {
					String updatehdr = "UPDATE HRMS_REC_PREJOIN SET PREJOIN_STATUS='C' WHERE PREJOIN_CODE NOT IN (SELECT DISTINCT PREJOIN_CODE FROM HRMS_REC_PREJOIN_DTL WHERE PREJOIN_DTL_ACTSTATUS='O')	";
					result = getSqlModel().singleExecute(updatehdr);
				}
				generateMailTemplateforMonitoring(prebean, prejoindtlcodes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void generateMailTemplate(prejoiningActivities bean) {
		try {
			/*String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " WHERE EVENT_CODE=15";
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);*/
			String query = "SELECT  PREJOIN_DTL_RESPERSON ,CHECKLIST_CODE   FROM HRMS_REC_PREJOIN_DTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_PREJOIN_DTL.PREJOIN_DTL_RESPERSON)"
					+ " INNER JOIN HRMS_CHECKLIST ON(HRMS_CHECKLIST.CHECK_CODE=HRMS_REC_PREJOIN_DTL.CHECKLIST_CODE) "
					+ " WHERE PREJOIN_CODE=" + bean.getPrejoinCode() + " AND PREJOIN_DTL_ACTSTATUS='O'";

			Object Assignercode[][] = null;
			Assignercode = getSqlModel().getSingleResult(query);
			for (int i = 0; i < Assignercode.length; i++) {  
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate("Prejoining Activity Assignment");
				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
				templateQuery1.setParameter(1, bean.getUserEmpId());

				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
				templateQuery2.setParameter(1, "" + Assignercode[i][0]); // Assignercode[i]

				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, "" + Assignercode[i][0]); // bean.getUserEmpId()

				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, "" + bean.getPrejoinCode()); 
				templateQuery4.setParameter(2, "" + String.valueOf(Assignercode[i][1])); // check list code

				EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, "" + bean.getUserEmpId()); // bean.getUserEmpId()

				template.configMailAlert();
				template.sendApplicationMail();
				template.clearParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateMailTemplateforMonitoring(prejoiningActivities bean,
			String prejoindtlcodes) {
		try {
			/*String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " WHERE EVENT_CODE=21";
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);*/

			logger.info("prejoindtlcodes --->" + prejoindtlcodes);
			String Predtlcode[] = prejoindtlcodes.split(",");

			Object[][] assignerEmpcode = new Object[Predtlcode.length][1];
			Object[][] assignerDupEmpcode = null;
			for (int j = 0; j < Predtlcode.length; j++) {
				String assignerQuery = "SELECT PREJOIN_ASSIGNERCODE FROM HRMS_REC_PREJOIN_DTL "
						+ " INNER JOIN HRMS_REC_PREJOIN ON (HRMS_REC_PREJOIN.PREJOIN_CODE=HRMS_REC_PREJOIN_DTL.PREJOIN_CODE)"
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_PREJOIN.PREJOIN_ASSIGNERCODE)"
						+ " WHERE HRMS_REC_PREJOIN_DTL.PREJOIN_DTL_CODE="
						+ String.valueOf(Predtlcode[j]);

				assignerDupEmpcode = getSqlModel().getSingleResult(assignerQuery);
				assignerEmpcode[j][0] = String.valueOf(assignerDupEmpcode[0][0]);
			}

			for (int i = 0; i < Predtlcode.length; i++) { // Assignercode.length
				if (Predtlcode[i] != "") {
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					/*template.setEmailTemplate(String.valueOf(tempData[0][3]));*/
					template.setEmailTemplate("Prejoining Activity Completion");
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
					templateQuery1.setParameter(1, bean.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
					templateQuery2.setParameter(1, String.valueOf(assignerEmpcode[i][0])); // Assignercode[i]

					EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
					templateQuery3.setParameter(1, "" + Predtlcode[i]); 

					EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
					templateQuery4.setParameter(1, "" + bean.getUserEmpId()); // bean.getUserEmpId()

					template.configMailAlert();
					template.sendApplicationMail();
					template.clearParameters();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
}// end of main method

