package org.paradyne.model.recruitment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.backGroundCheck;
import org.paradyne.lib.MailModel;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SendEmail;
import org.paradyne.lib.Utility;

/*
 * @author saipavan voleti
 *  
 */
public class backGroundCheckModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(backGroundCheckModel.class);

	/**
	 *  following function is called in the
	 * action class when the candidate is selected from the pop up window.
	 * 
	 * @param bean
	 */

	public void showOffer(backGroundCheck bean) {
		try {
			String query = "SELECT CASE WHEN OFFER_STATUS='D' THEN 'Due' WHEN OFFER_STATUS='P' THEN 'Pending' WHEN  OFFER_STATUS='A' THEN 'Approved'"
					+ " WHEN OFFER_STATUS='R' THEN 'Approval Rejection'  WHEN OFFER_STATUS='I' THEN 'Issued' WHEN OFFER_STATUS='OA' THEN 'Accepted'"
					+ " WHEN OFFER_STATUS='OR' THEN 'Rejected' WHEN OFFER_STATUS='C' THEN 'Canceled' END "
					+ " FROM HRMS_REC_OFFER WHERE OFFER_CAND_CODE="
					+ bean.getCandidateCode();
			Object obj[][] = getSqlModel().getSingleResult(query);
			if (obj != null && obj.length > 0) {
				bean.setOfferStatus(String.valueOf(obj[0][0]));
			} else {
				bean.setOfferStatus("Not Given");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bean
	 *            purpose:setting check list details
	 */
	public void showCheckList(backGroundCheck bean) {
		try {
			if (String.valueOf(bean.getCheckListType()) != "") {
				String Query = "SELECT CHECK_CODE,CHECK_NAME FROM HRMS_CHECKLIST	WHERE CHECK_TYPE='"
						+ bean.getCheckListType() + "'	AND CHECK_STATUS='A'";

				Object obj[][] = getSqlModel().getSingleResult(Query);
				ArrayList<Object> list = new ArrayList<Object>();
				if (obj != null && obj.length > 0) {
					for (int i = 0; i < obj.length; i++) {
						backGroundCheck bgcheck = new backGroundCheck();
						bgcheck.setCheckListitemcode(String.valueOf(obj[i][0]));
						bgcheck.setCheckListName(String.valueOf(obj[i][1]));
						bgcheck.setCheckListresponce("");
						bgcheck.setCheckListComments("");
						list.add(bgcheck);
					}
					bean.setChkList(list);
					// checkListitemcode checkListName checkListresponce
					// checkListComments
					bean.setChkLength(String.valueOf(list.size()));
					bean.setNoData("false");
				} else {
					bean.setChkLength("0");
					bean.setNoData("true");
				}
				bean.setChecklistTable(true);
			} else {
				bean.setChecklistTable(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bean
	 *            purpose:setting background check list details
	 */

	public void setCheckList(backGroundCheck bean, String backgroundCheckCode, String checkListType) {
		try {
			if (String.valueOf(bean.getCheckListType()) != "") {
				String Query = "select  HRMS_REC_BGCHECK_DTL.CHECKLIST_CODE,"
						+ " CHECK_NAME,BG_RESPONSE,HRMS_REC_BGCHECK_DTL.BG_COMMENTS,HRMS_REC_BGCHECK_DTL.BG_CODE"
						+ ",decode(BG_RESPONSE,'S ','Satisfied','N ','Not Satisfied','NA','Not Applicable'), HRMS_REC_BGCHECK_DTL.BG_DTL_PROOF  from HRMS_REC_BGCHECK_DTL"
						+ " left JOIN HRMS_REC_BGCHECK ON (HRMS_REC_BGCHECK.BG_CODE=HRMS_REC_BGCHECK_DTL.BG_CODE)"
						+ " left JOIN HRMS_CHECKLIST ON (HRMS_CHECKLIST.CHECK_CODE=HRMS_REC_BGCHECK_DTL.CHECKLIST_CODE)"
						+ " where HRMS_REC_BGCHECK_DTL.BG_CODE=" + backgroundCheckCode
						+ " and HRMS_CHECKLIST.CHECK_TYPE='" + checkListType + "'";

				Object obj[][] = getSqlModel().getSingleResult(Query);
				ArrayList<Object> list = new ArrayList<Object>();
				if (obj != null && obj.length > 0) {
					for (int i = 0; i < obj.length; i++) {
						backGroundCheck bgcheck = new backGroundCheck();
						bgcheck.setCheckListitemcode(String.valueOf(obj[i][0]));
						bgcheck.setCheckListName(String.valueOf(obj[i][1]));
						bgcheck.setCheckListresponce(String.valueOf(obj[i][2]));
						bgcheck.setDupcheckListresponce(checkNull(String
								.valueOf(obj[i][5])));
						bgcheck.setCheckListComments(checkNull(String
								.valueOf(obj[i][3])));
						bgcheck.setUploadLocFileName(checkNull(String
								.valueOf(obj[i][6])));
						list.add(bgcheck);
					}
					bean.setChkList(list);
					// checkListitemcode checkListName checkListresponce
					// checkListComments

					bean.setChkLength(String.valueOf(list.size()));
					bean.setNoData("false");
				} else {
					bean.setChkLength("0");
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
	 * @param response
	 * @param comments
	 * @return boolean after saving the application.
	 */
	public boolean save(backGroundCheck bean, Object[] ckcode,
			Object[] response, Object[] comments, Object[] dtlproof) {
		try {

			Object add[][] = new Object[1][6];
			add[0][0] = bean.getCandidateCode();
			add[0][1] = bean.getReqCode();
			if (bean.getBgCheckType().equals("O")) { // when background check
														// type is Out source
				add[0][2] = "O";
			} else {
				add[0][2] = "I"; // when background check type is Inhouse
			}
			add[0][3] = bean.getCheckListType();
			add[0][4] = bean.getOverallComments();
			if (bean.getOutsourceAgencyCode() != "") {
				add[0][5] = bean.getOutsourceAgencyCode();
			} else {
				add[0][5] = "";
			}

			boolean result = getSqlModel().singleExecute(getQuery(1), add);
			if (result) {
				String query = " SELECT NVL(MAX(BG_CODE),0) FROM HRMS_REC_BGCHECK";
				Object resultCode[][] = getSqlModel().getSingleResult(query);
				if (ckcode != null) {
					for (int i = 0; i < ckcode.length; i++) {
						Object chkDtl[][] = new Object[1][5];
						chkDtl[0][0] = String.valueOf(resultCode[0][0]);
						chkDtl[0][1] = String.valueOf(ckcode[i]);
						chkDtl[0][2] = String.valueOf(response[i]);
						chkDtl[0][3] = String.valueOf(comments[i]);
						chkDtl[0][4] = String.valueOf(dtlproof[i]);
						result = getSqlModel().singleExecute(getQuery(2),
								chkDtl);
					}
				}
				bean.setBgcheckCode(String.valueOf(resultCode[0][0]));

				boolean res = false;
				if (!bean.getChkoffercode().trim().equals("")) // updating
																// background
																// verification
																// status in
																// offer.
				{
					String updateStatusQuery = "UPDATE HRMS_REC_OFFER SET OFFER_BG_REQ='C' WHERE  OFFER_CODE="
							+ bean.getChkoffercode();
					res = getSqlModel().singleExecute(updateStatusQuery);
				}
				return result;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param bean
	 * @param ckcode
	 * @param response
	 * @param comments
	 * @return boolean after modifying the application.
	 */
	public boolean update(backGroundCheck bean, Object[] ckcode,
			Object[] response, Object[] comments, Object[] dtlproof) {
		try {
			Object add[][] = new Object[1][7];
			add[0][0] = bean.getCandidateCode();
			add[0][1] = bean.getReqCode();
			if (bean.getBgCheckType().equals("O")) {
				add[0][2] = "O"; // when background check type is Out source
			} else {
				add[0][2] = "I"; // when background check type is In house
			}
			add[0][3] = bean.getCheckListType();
			add[0][4] = bean.getOverallComments();
			if (bean.getOutsourceAgencyCode() != "") {
				add[0][5] = bean.getOutsourceAgencyCode();
			} else {
				add[0][5] = ""; // bean.getOutsourceAgencyCode();
			}
			add[0][6] = bean.getBgcheckCode();
			// add[0][7] = bean.getUploadLocFileName();

			boolean result = getSqlModel().singleExecute(getQuery(3), add);
			if (result) {
				String delQuery = "delete from HRMS_REC_BGCHECK_DTL where BG_CODE="
						+ bean.getBgcheckCode();
				result = getSqlModel().singleExecute(delQuery);
				String query = " SELECT NVL(MAX(BG_CODE),0) FROM HRMS_REC_BGCHECK";
				Object resultCode[][] = getSqlModel().getSingleResult(query);
				if (ckcode != null) {
					for (int i = 0; i < ckcode.length; i++) {
						Object chkDtl[][] = new Object[1][5];
						chkDtl[0][0] = String.valueOf(bean.getBgcheckCode());
						chkDtl[0][1] = String.valueOf(ckcode[i]);
						chkDtl[0][2] = String.valueOf(response[i]);
						chkDtl[0][3] = String.valueOf(comments[i]);
						chkDtl[0][4] = String.valueOf(dtlproof[i]);
						result = getSqlModel().singleExecute(getQuery(2),
								chkDtl);
					}
				}
				return result;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param bean
	 * @param status
	 *            purpose:setting background application list(pending or
	 *            conducted)
	 */
	public void setBackgroundList(backGroundCheck bean, String status,
			HttpServletRequest request) {
		String listRecordQuery = "";
		if (String.valueOf(status).trim().equals("P")) { // when status is pending
			bean.setConductflag(false);
			listRecordQuery = "select CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,REQS_NAME, "
					+ " HRMS_RANK.RANK_NAME, "
					+ " CASE WHEN OFFER_STATUS='OA' THEN 'Accepted ' ELSE 'offer Status' End,"
					+ "OFFER_CODE,HRMS_REC_REQS_HDR.REQS_CODE,OFFER_CAND_CODE,CAND_RESUME"
					+ " 	FROM  HRMS_REC_OFFER"
					+ " 	INNER JOIN HRMS_REC_CAND_DATABANK  ON(HRMS_REC_OFFER.OFFER_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+ " 	INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_OFFER.OFFER_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE) "
					+ " 	INNER JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID) "
					+ " 	WHERE OFFER_STATUS='OA' AND OFFER_BG_REQ='Y'";
		} else { // when status is conducted
			listRecordQuery = " SELECT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME as name, "
					+ "  HRMS_REC_REQS_HDR.REQS_NAME,HRMS_RANK.RANK_NAME,"
					+ " DECODE(OFFER_STATUS, 'D ','Due','I', 'Issued', 'OA', 'Accepted', 'OR', 'Rejected', 'C ', 'Canceled'),"
					+ " HRMS_REC_OFFER.OFFER_CODE,BG_REQS_CODE,BG_CAND_CODE,CAND_RESUME, "
					+ " CASE WHEN BG_CHECK_LIST='J' THEN 'Joining ' WHEN BG_CHECK_LIST='M' THEN 'Medical' "
					+ " WHEN BG_CHECK_LIST='T' THEN 'Transfer' WHEN BG_CHECK_LIST='I' THEN 'Interview'  "
					+ " WHEN BG_CHECK_LIST='B' THEN 'Background' WHEN BG_CHECK_LIST='P' THEN 'Prejoining' END, "
					+" BG_CODE, BG_CHECK_LIST FROM HRMS_REC_BGCHECK "
					+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_BGCHECK.BG_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE) "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_BGCHECK.BG_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE) "
					+ " left join HRMS_REC_OFFER on(HRMS_REC_OFFER.OFFER_REQS_CODE=HRMS_REC_BGCHECK.BG_REQS_CODE and "
					+ " HRMS_REC_OFFER.OFFER_CAND_CODE=HRMS_REC_BGCHECK.BG_CAND_CODE )"
					+ " INNER JOIN HRMS_RANK ON(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID)"
					+ " ORDER BY BG_CODE ";
		}

		try {
			Object listRecodObj[][] = getSqlModel().getSingleResult(listRecordQuery);

			String[] pageIndex = Utility.doPaging(bean.getMyPage(), listRecodObj.length,
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

			ArrayList<Object> pendingList = new ArrayList<Object>();
			ArrayList<Object> conductedList = new ArrayList<Object>();

			if (listRecodObj != null && listRecodObj.length > 0) {

				// for (int i = 0; i < obj.length; i++) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					
					if(String.valueOf(status).trim().equals("P")){
						backGroundCheck bgcheck = new backGroundCheck();
						bgcheck.setLcandidate(String.valueOf(listRecodObj[i][0]));
						bgcheck.setLreqName(String.valueOf(listRecodObj[i][1]));
						bgcheck.setLposition(checkNull(String.valueOf(listRecodObj[i][2])));
						bgcheck.setLofferstatus(checkNull(String.valueOf(listRecodObj[i][3])));
						bgcheck.setLoffercode(checkNull(String.valueOf(listRecodObj[i][4])));
						bgcheck.setLreqcode(String.valueOf(listRecodObj[i][5]));
						bgcheck.setLcancode(String.valueOf(listRecodObj[i][6]));
						bgcheck.setResume(String.valueOf(listRecodObj[i][7]));
						pendingList.add(bgcheck);
					}else {
						backGroundCheck bgcheck = new backGroundCheck();
						bgcheck.setLcandidate(String.valueOf(listRecodObj[i][0]));
						bgcheck.setLreqName(String.valueOf(listRecodObj[i][1]));
						bgcheck.setLposition(checkNull(String.valueOf(listRecodObj[i][2])));
						bgcheck.setLofferstatus(checkNull(String.valueOf(listRecodObj[i][3])));
						bgcheck.setLoffercode(checkNull(String.valueOf(listRecodObj[i][4])));
						bgcheck.setLreqcode(String.valueOf(listRecodObj[i][5]));
						bgcheck.setLcancode(String.valueOf(listRecodObj[i][6]));
						bgcheck.setResume(String.valueOf(listRecodObj[i][7]));
						bgcheck.setLchecklistType(String.valueOf(listRecodObj[i][8]));
						bgcheck.setBackgroundCheckCode(String.valueOf(listRecodObj[i][9]));
						bgcheck.setBackgroundCheckListType(String.valueOf(listRecodObj[i][10]));
						conductedList.add(bgcheck);
						System.out.println("total Conducted Records >>"+String.valueOf(listRecodObj.length));
						bgcheck.setTotalConductedRecords(String.valueOf(listRecodObj.length));
					}
				}
				
				if(String.valueOf(status).trim().equals("P")){
					bean.setBgpendingChkList(pendingList);
					System.out.println("total Pending Records >>"+String.valueOf(listRecodObj.length));
					bean.setTotalPendingRecords(String.valueOf(listRecodObj.length));
				}else{
					bean.setBgconductChkList(conductedList);
					System.out.println("total Conducted Records >>"+String.valueOf(listRecodObj.length));
					bean.setTotalConductedRecords(String.valueOf(listRecodObj.length));
				}
				bean.setNoData("false");

			} else {
				bean.setNoData("true");

			}
			bean.setBgpendinglistLength(String.valueOf(pendingList.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bgcheck
	 *            purpose:setting conducted candidate details
	 */
	public void conducted(backGroundCheck bgcheck) {
		try {
			String Reqquery = " select REQS_CODE,REQS_NAME,HRMS_RANK.RANK_NAME,decode(REQS_STATUS,'O','Open','C','Close'),"
					+ " HRMS_DIVISION.DIV_NAME,hrms_center.CENTER_NAME,HRMS_DEPT.DEPT_NAME"
					+ " from HRMS_REC_REQS_HDR "
					+ " inner join HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
					+ " inner join hrms_center ON (hrms_center.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
					+ " inner join HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
					+ " inner join HRMS_RANK on(HRMS_REC_REQS_HDR.REQS_POSITION=HRMS_RANK.RANK_ID) "
					+ " Where REQS_CODE=" + bgcheck.getChkreqcode();

			String Candquery = "SELECT CAND_CODE,CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME from HRMS_REC_CAND_DATABANK "
					+ " Where CAND_CODE=" + bgcheck.getChkcandidatecode();

			/*String offerQuery = "select DECODE(OFFER_STATUS, 'I ', 'Issued', 'OA', 'Accepted', 'OR', 'Rejected', 'C ', 'Canceled')"
					+ " from hrms_rec_offer where OFFER_REQS_CODE="
					+ bgcheck.getChkreqcode()
					+ " and OFFER_CAND_CODE="
					+ bgcheck.getChkcandidatecode();*/
			
			String offerQuery = "SELECT CASE WHEN OFFER_STATUS='D' THEN 'Due' WHEN OFFER_STATUS='P' THEN 'Pending' WHEN  OFFER_STATUS='A' THEN 'Approved'"
				+ " WHEN OFFER_STATUS='R' THEN 'Approval Rejection'  WHEN OFFER_STATUS='I' THEN 'Issued' WHEN OFFER_STATUS='OA' THEN 'Accepted'"
				+ " WHEN OFFER_STATUS='S' THEN 'Rejected' WHEN OFFER_STATUS='C' THEN 'Canceled' END "
				+ " FROM HRMS_REC_OFFER WHERE OFFER_CAND_CODE="+ bgcheck.getChkcandidatecode() +" and OFFER_REQS_CODE = "+bgcheck.getChkreqcode();
				

			Object Reqdtl[][];
			Object Candtl[][];

			Reqdtl = getSqlModel().getSingleResult(Reqquery);
			Candtl = getSqlModel().getSingleResult(Candquery);
			Object offer[][] = getSqlModel().getSingleResult(offerQuery);

			if (Reqdtl != null && Reqdtl.length > 0) {
				bgcheck.setReqCode(String.valueOf(Reqdtl[0][0]));
				bgcheck.setReqName(String.valueOf(Reqdtl[0][1]));
				bgcheck.setPosition(checkNull(String.valueOf(Reqdtl[0][2])));
				bgcheck.setDivision(String.valueOf(Reqdtl[0][4]));
				bgcheck.setBranch(String.valueOf(Reqdtl[0][5]));
				bgcheck.setDepartment(String.valueOf(Reqdtl[0][6]));

			}
			if (Candtl != null && Candtl.length > 0) {
				bgcheck.setCandidateCode(String.valueOf(Candtl[0][0]));
				bgcheck.setCandidateName(String.valueOf(Candtl[0][1]));
			}

			if (offer != null && offer.length > 0) {
				bgcheck.setOfferStatus(checkNull(String.valueOf(offer[0][0])));
			} else {
				bgcheck.setOfferStatus("Not Given");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return res;
	}

	/**
	 * @param bgcheck
	 *            purpose:setting selected record details.
	 * @param string3 
	 * @param string2 
	 * @param requisitionCode 
	 */
	public void f9searchdtl(backGroundCheck bgcheck, String requisitionCode, 
				String candidateCode, String backgroundCheckCode, String backgroundCheckListType) {
		try {
			String Reqquery = " SELECT REQS_CODE,REQS_NAME,RANK_NAME,DECODE(REQS_STATUS,'O','OPEN','C','CLOSE'),"
					+ " HRMS_DIVISION.DIV_NAME,HRMS_CENTER.CENTER_NAME,HRMS_DEPT.DEPT_NAME"
					+ " FROM HRMS_REC_REQS_HDR "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
					+ " WHERE REQS_CODE=" + requisitionCode;
			String Candquery = "SELECT CAND_CODE,CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME from HRMS_REC_CAND_DATABANK "
					+ " Where CAND_CODE=" + candidateCode;

			/*String offerQuery = "select DECODE(OFFER_STATUS, 'I ', 'Issued', 'OA', 'Accepted', 'OR', 'Rejected', 'C ', 'Canceled')"
					+ " from hrms_rec_offer where OFFER_REQS_CODE="
					+ bgcheck.getReqCode()
					+ " and OFFER_CAND_CODE="
					+ bgcheck.getCandidateCode();*/
			
			String offerQuery = "SELECT CASE WHEN OFFER_STATUS='D' THEN 'Due' WHEN OFFER_STATUS='P' THEN 'Pending' WHEN  OFFER_STATUS='A' THEN 'Approved'"
				+ " WHEN OFFER_STATUS='R' THEN 'Approval Rejection'  WHEN OFFER_STATUS='I' THEN 'Issued' WHEN OFFER_STATUS='OA' THEN 'Accepted'"
				+ " WHEN OFFER_STATUS='S' THEN 'Rejected' WHEN OFFER_STATUS='C' THEN 'Canceled' END "
				+ " FROM HRMS_REC_OFFER WHERE OFFER_CAND_CODE="+ candidateCode +" and OFFER_REQS_CODE = "+requisitionCode;
				

			Object Reqdtl[][] = getSqlModel().getSingleResult(Reqquery);
			Object Candtl[][] = getSqlModel().getSingleResult(Candquery);
			Object offer[][] = getSqlModel().getSingleResult(offerQuery);
			if (Reqdtl != null && Reqdtl.length > 0) {
				bgcheck.setReqCode(String.valueOf(Reqdtl[0][0]));
				bgcheck.setReqName(String.valueOf(Reqdtl[0][1]));
				bgcheck.setPosition(String.valueOf(Reqdtl[0][2]));
				bgcheck.setDivision(String.valueOf(Reqdtl[0][4]));
				bgcheck.setBranch(String.valueOf(Reqdtl[0][5]));
				bgcheck.setDepartment(String.valueOf(Reqdtl[0][6]));

			}
			if (offer != null && offer.length > 0) {
				bgcheck.setOfferStatus(checkNull(String.valueOf(offer[0][0])));
			} else {
				bgcheck.setOfferStatus("Not Given");
			}

			if (Candtl != null && Candtl.length > 0) {
				bgcheck.setCandidateCode(String.valueOf(Candtl[0][0]));
				bgcheck.setCandidateName(String.valueOf(Candtl[0][1]));
			}
			String BgQuery = "SELECT BG_CHECK_TYPE ,decode(BG_CHECK_TYPE,'I','In House','O','Out Source'),DECODE(BG_CHECK_LIST,'J','Joining CheckList','M','Medical CheckList','T','Transfer CheckList','I','Interview CheckList','B','Background Verification CheckList','P','Prejoining CheckList'),"
					+ "  BG_COMMENTS,BG_AGENCYCODE FROM HRMS_REC_BGCHECK WHERE BG_CODE=" + backgroundCheckCode;
			Object BgQuerydtl[][] = getSqlModel().getSingleResult(BgQuery);

			bgcheck.setBgCheckType(String.valueOf(BgQuerydtl[0][0]));
			bgcheck.setDupbgCheckType(String.valueOf(BgQuerydtl[0][1]));
			bgcheck.setDupcheckListType(String.valueOf(BgQuerydtl[0][2]));
			bgcheck.setOverallComments(checkNull(String
					.valueOf(BgQuerydtl[0][3])));

			if (String.valueOf(BgQuerydtl[0][0]).equals("O")) {
				if (String.valueOf(BgQuerydtl[0][4]) != "") {
					String outsourceQuery = "SELECT REC_CODE,REC_PARTNERNAME FROM HRMS_REC_PARTNER where REC_CODE="
							+ String.valueOf(BgQuerydtl[0][4]);

					Object outsourcedtl[][] = getSqlModel().getSingleResult(
							outsourceQuery);
					if (outsourcedtl != null && outsourcedtl.length > 0) {
						bgcheck.setOutsourceAgencyCode(String
								.valueOf(outsourcedtl[0][0]));
						bgcheck.setOutsourceAgencyName(checkNull(String
								.valueOf(outsourcedtl[0][1])));
					}
				}
			} else {
				bgcheck.setOutsourceAgencyCode("");
				bgcheck.setOutsourceAgencyName("");

			}
			setCheckList(bgcheck, backgroundCheckCode, backgroundCheckListType);
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
	 * @param bgcheck
	 * @return boolean after deleting backGroundCheck application
	 */
	public boolean deleteBgcheck(backGroundCheck bgcheck) {
		boolean result = false;
		try {
			String delBgDtl = "DELETE FROM HRMS_REC_BGCHECK_DTL WHERE BG_CODE ="+ bgcheck.getBgcheckCode();	
			String delBg = "DELETE FROM HRMS_REC_BGCHECK WHERE BG_CODE ="+ bgcheck.getBgcheckCode();
			result = getSqlModel().singleExecute(delBgDtl);
			if (result) {
				result = getSqlModel().singleExecute(delBg);
				if(! bgcheck.getChkoffercode().equals("")) {
					String updateOfferStatus = "UPDATE HRMS_REC_OFFER SET OFFER_BG_REQ='Y' WHERE OFFER_CODE="+bgcheck.getChkoffercode();
					getSqlModel().singleExecute(updateOfferStatus);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * @param bgcheck purpose:setting selected backGroundCheck details.
	 */
	public void bgCheckRecord(backGroundCheck bgcheck) {
		try {
			String bgcheckquery = " SELECT BG_CODE,CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME as name,"
					+ " HRMS_REC_REQS_HDR.REQS_NAME,BG_REQS_CODE,BG_CAND_CODE,BG_CHECK_LIST,"
					+
					// "BG_CHECK_TYPE," +
					" HRMS_REC_PARTNER.REC_PARTNERNAME,BG_AGENCYCODE "
					+ " 	FROM HRMS_REC_BGCHECK "
					+ " 	INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_BGCHECK.BG_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+ " 	INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_BGCHECK.BG_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					+ " 	left JOIN HRMS_REC_PARTNER ON (HRMS_REC_PARTNER.REC_CODE=HRMS_REC_BGCHECK.BG_AGENCYCODE)"
					+ " WHERE BG_CODE=" + bgcheck.getBgcheckCode();
			Object bgRecorddtl[][] = getSqlModel()
					.getSingleResult(bgcheckquery);
			if (bgRecorddtl != null && bgRecorddtl.length > 0) {
				bgcheck.setBgcheckCode(String.valueOf(bgRecorddtl[0][0]));
				bgcheck.setCandidateName(String.valueOf(bgRecorddtl[0][1]));
				bgcheck.setReqName(String.valueOf(bgRecorddtl[0][2]));
				bgcheck.setReqCode(String.valueOf(bgRecorddtl[0][3]));
				bgcheck.setCandidateCode(String.valueOf(bgRecorddtl[0][4]));
				bgcheck.setCheckListType(String.valueOf(bgRecorddtl[0][5]));
				bgcheck.setOutsourceAgencyName(checkNull(String
						.valueOf(bgRecorddtl[0][6])));
				bgcheck.setOutsourceAgencyCode(checkNull(String
						.valueOf(bgRecorddtl[0][7])));

				f9searchdtl(bgcheck, bgcheck.getReqCode(), bgcheck.getCandidateCode(), bgcheck.getBgcheckCode(), bgcheck.getCheckListType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getRecords(backGroundCheck bean, HttpServletRequest request) {
		try {
			String query = " SELECT NVL(INDUS_NAME,' '),NVL(INDUS_ABBRIVIATION,' '),CASE WHEN INDUS_STATUS='A' THEN 'Active' ELSE 'Deactive' END,NVL(INDUS_DESCRIPTION,' '),INDUS_CODE  FROM HRMS_INDUS_TYPE"
					+ " ORDER BY INDUS_CODE";

			Object[][] data = getSqlModel().getSingleResult(query);

			ArrayList<Object> obj = new ArrayList<Object>();
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					data.length, 20);
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

			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean sendBackgroundMailAlert(backGroundCheck bgcheck) {
		boolean result = false;
		try {
			String bgcheckMailToMailID = bgcheck.getBgcheckMailToMailID();
			String[] toEmailID = bgcheckMailToMailID.split(";");

			if (toEmailID != null && toEmailID.length > 0) {
				MailModel mail_model = new MailModel();
				mail_model.initiate(context, session);
				Object[][] mailBoxData = mail_model.getServerMailBox("", String
						.valueOf(toEmailID[0]));
				mail_model.terminate();

				MailUtility mod = new MailUtility();
				mod.initiate(context, session);

				String[] toEmpAddArr = new String[toEmailID.length];
				if (toEmailID != null && toEmailID.length > 0) {
					for (int j = 0; j < toEmailID.length; j++) {
						toEmpAddArr[j] = String.valueOf(toEmailID[j]);
						System.out.println("EMAIL ID  >>>>>>>>"
								+ toEmpAddArr[j]);
					}
				}

				sendMail(toEmpAddArr, getDefaultMailIds(), bgcheck
						.getBgcheckMailSubject().trim(), bgcheck.getHtmlcode(),
						mailBoxData);

				mod.terminate();
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public String[][] getDefaultMailIds() {
		String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID ORDER BY SYSMAIL_CODE ";
		Object fromEmp[][] = getSqlModel().getSingleResult(fromQuery);
		String[][] mailIds = new String[fromEmp.length][2];
		if (fromEmp != null && fromEmp.length > 0) {
			for (int i = 0; i < fromEmp.length; i++) {
				mailIds[i][0] = String.valueOf(fromEmp[i][0]);
				mailIds[i][1] = String.valueOf(fromEmp[i][1]);
			}
		}
		return mailIds;
	}

	private void sendMail(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody, Object[][] mailBoxData) {
		try {
			// HtmlEmail email=null;
			int patch = 80;
			int count = toMailId.length / patch;
			int rem = toMailId.length % patch;
			if (rem > 0) {
				count = count + 1;
			}
			int k = 0;
			if (patch > toMailId.length) {
				patch = toMailId.length;
			}

			for (int i = 0; i < count; i++) {
				String[] tomailIds = null;
				if (i == count - 1) {
					if (rem > 0) {
						tomailIds = new String[rem];
					} else {
						tomailIds = new String[patch];
					}
				} else {
					tomailIds = new String[patch];
				}

				for (int j = 0; j < tomailIds.length; j++) {
					tomailIds[j] = toMailId[k];
					k++;
				}
				fireEmail(mailBoxData, subject, textBody, tomailIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fireEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {
		try {
			SendEmail email = new SendEmail();
			String[][] fromEmail = getDefaultMailIds();
			email.sendMailToKeepInfo(tomailIds, fromEmail, subject, textBody,
					mailBoxData);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	 
}
