/**
 * 
 */
package org.paradyne.model.recruitment;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.CreateOffer;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa0540
 * 
 */
public class CreateOfferModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CreateOfferModel.class);

	public void showOfferList(CreateOffer bean, HttpServletRequest request,
			String msg, String msg1, String msg2, String msg3, String msg4,
			String msg5, String msg6, String msg7) {
		try {
			Object[][] offerListData = null;
			ArrayList<Object> offerList = new ArrayList<Object>();
			String concatStr = "";
			String query = "";
			// if user wants to see approval related offer list
			if (bean.getOfferStatus().equals("P")
					|| bean.getOfferStatus().equals("A")
					|| bean.getOfferStatus().equals("R")) {
				query = getQuery(1);
				query += "WHERE HRMS_REC_OFFER.OFFER_APPR_STATUS = '" + bean.getOfferStatus() + "'";
				if (!(bean.getSearchHidRequisitionCode().equals("") || bean.getSearchHidRequisitionCode().equals("null"))) {
					query += " AND HRMS_REC_REQS_HDR.REQS_CODE=" + bean.getSearchHidRequisitionCode();
					concatStr += msg + " :" + bean.getSearchRequisitionCode() + ",";
				}

				if (!(bean.getSearchCandCode().equals("") || bean
						.getSearchCandCode().equals("null"))) {
					query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
					concatStr += msg1 + " :" + bean.getSearchCandName() + ",";
				}

				if (!(bean.getSearchPositionId().equals("") || bean
						.getSearchPositionId().equals("null"))) {
					query += " AND HRMS_REC_REQS_HDR.REQS_POSITION=" + bean.getSearchPositionId();
					concatStr += msg2 + " :" + bean.getSearchPosition() + ",";
				}

				if (!(bean.getSearchHiringMgrId().equals("") || bean
						.getSearchHiringMgrId().equals("null"))) {
					query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
							+ bean.getSearchHiringMgrId();
					concatStr += msg3 + " :" + bean.getSearchHiringMgr() + ",";
				}
				
				if (!(bean.getSearchDueSinceDays().equals("") || bean
						.getSearchDueSinceDays().equals("null"))) {
					query += " AND TO_DATE(SYSDATE, 'DD-MM-YYYY')-TO_DATE(HRMS_REC_OFFER.OFFER_DUE_DATE, 'DD-MM-YYYY')>="
							+ bean.getSearchDueSinceDays();
					concatStr += msg4 + " :" + bean.getSearchDueSinceDays()
							+ ",";
				}
				//query += " ORDER BY NVL(TO_DATE(SYSDATE, 'DD-MM-YYYY')-TO_DATE(OFFER_DUE_DATE, 'DD-MM-YYYY'),0) ASC";
			} else {
				if (bean.getOfferStatus().equals("D")) {
					query = getQuery(2);
					
					query += "WHERE HRMS_REC_OFFER.OFFER_STATUS = '" + bean.getOfferStatus()
							+ "'";
					if (!(bean.getSearchHidRequisitionCode().equals("") || bean
							.getSearchHidRequisitionCode().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
								+ bean.getSearchHidRequisitionCode();
						concatStr += msg + " :"
								+ bean.getSearchRequisitionCode() + ",";
					}

					if (!(bean.getSearchCandCode().equals("") || bean
							.getSearchCandCode().equals("null"))) {
						query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
						concatStr += msg1 + " :" + bean.getSearchCandName()
								+ ",";
					}

					if (!(bean.getSearchPositionId().equals("") || bean
							.getSearchPositionId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_POSITION="
								+ bean.getSearchPositionId();
						concatStr += msg2 + " :" + bean.getSearchPosition()
								+ ",";
					}

					if (!(bean.getSearchHiringMgrId().equals("") || bean
							.getSearchHiringMgrId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
								+ bean.getSearchHiringMgrId();
						concatStr += msg3 + " :" + bean.getSearchHiringMgr()
								+ ",";
					}
					
					if (!(bean.getSearchDueSinceDays().equals("") || bean
							.getSearchDueSinceDays().equals("null"))) {
						query += " AND NVL(TO_DATE(SYSDATE, 'DD-MM-YYYY')-TO_DATE(HRMS_REC_OFFER.OFFER_DUE_DATE, 'DD-MM-YYYY'),0)>="
								+ bean.getSearchDueSinceDays();
						concatStr += msg4 + " :" + bean.getSearchDueSinceDays()
								+ ",";
					}
					//query += " ORDER BY NVL(TO_DATE(SYSDATE, 'DD-MM-YYYY')-TO_DATE(OFFER_DUE_DATE, 'DD-MM-YYYY'),0) ASC";
				} else if (bean.getOfferStatus().equals("I")) {
					query = getQuery(1);
					query += "WHERE HRMS_REC_OFFER.OFFER_STATUS = '" + bean.getOfferStatus()
							+ "'";
					if (!(bean.getSearchHidRequisitionCode().equals("") || bean
							.getSearchHidRequisitionCode().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
								+ bean.getSearchHidRequisitionCode();
						concatStr += msg + " :"
								+ bean.getSearchRequisitionCode() + ",";
					}

					if (!(bean.getSearchCandCode().equals("") || bean
							.getSearchCandCode().equals("null"))) {
						query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
						concatStr += msg1 + " :" + bean.getSearchCandName()
								+ ",";
					}

					if (!(bean.getSearchPositionId().equals("") || bean
							.getSearchPositionId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_POSITION="
								+ bean.getSearchPositionId();
						concatStr += msg2 + " :" + bean.getSearchPosition()
								+ ",";
					}

					if (!(bean.getSearchHiringMgrId().equals("") || bean
							.getSearchHiringMgrId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
								+ bean.getSearchHiringMgrId();
						concatStr += msg3 + " :" + bean.getSearchHiringMgr()
								+ ",";
					}
					if (!(bean.getOffFrmDate().equals("") || bean
							.getOffFrmDate().equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_DATE >=TO_DATE(" + "'"
								+ bean.getOffFrmDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg4 + " :" + bean.getOffFrmDate() + ",";
					}

					if (!(bean.getOffToDate().equals("") || bean.getOffToDate()
							.equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_DATE <=TO_DATE(" + "'"
								+ bean.getOffToDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg5 + " :" + bean.getOffToDate() + ",";
					}
				} else if (bean.getOfferStatus().equals("OA")) {
					query = getQuery(1);
					query += "WHERE HRMS_REC_OFFER.OFFER_STATUS = '" + bean.getOfferStatus()
							+ "'";
					if (!(bean.getSearchHidRequisitionCode().equals("") || bean
							.getSearchHidRequisitionCode().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
								+ bean.getSearchHidRequisitionCode();
						concatStr += msg + " :"
								+ bean.getSearchRequisitionCode() + ",";
					}

					if (!(bean.getSearchCandCode().equals("") || bean
							.getSearchCandCode().equals("null"))) {
						query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
						concatStr += msg1 + " :" + bean.getSearchCandName()
								+ ",";
					}

					if (!(bean.getSearchPositionId().equals("") || bean
							.getSearchPositionId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_POSITION="
								+ bean.getSearchPositionId();
						concatStr += msg2 + " :" + bean.getSearchPosition()
								+ ",";
					}

					if (!(bean.getSearchHiringMgrId().equals("") || bean
							.getSearchHiringMgrId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
								+ bean.getSearchHiringMgrId();
						concatStr += msg3 + " :" + bean.getSearchHiringMgr()
								+ ",";
					}
					if (!(bean.getOffFrmDate().equals("") || bean
							.getOffFrmDate().equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_DATE >=TO_DATE(" + "'"
								+ bean.getOffFrmDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg4 + " :" + bean.getOffFrmDate() + ",";
					}

					if (!(bean.getOffToDate().equals("") || bean.getOffToDate()
							.equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_DATE <=TO_DATE(" + "'"
								+ bean.getOffToDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg5 + " :" + bean.getOffToDate() + ",";
					}
					if (!(bean.getOffAccFrmDate().equals("") || bean
							.getOffAccFrmDate().equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_ACCEPT_DATE >=TO_DATE(" + "'"
								+ bean.getOffAccFrmDate() + "'"
								+ ",'DD-MM-YYYY')";
						concatStr += msg6 + " :" + bean.getOffAccFrmDate()
								+ ",";
					}

					if (!(bean.getOffAccToDate().equals("") || bean
							.getOffAccToDate().equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_ACCEPT_DATE <=TO_DATE(" + "'"
								+ bean.getOffAccToDate() + "'"
								+ ",'DD-MM-YYYY')";
						concatStr += msg7 + " :" + bean.getOffAccToDate() + ",";
					}

				} else if (bean.getOfferStatus().equals("S")) {
					query = getQuery(1);
					query += "WHERE HRMS_REC_OFFER.OFFER_STATUS IN ('OR', '" + bean.getOfferStatus()
							+ "')";
					if (!(bean.getSearchHidRequisitionCode().equals("") || bean
							.getSearchHidRequisitionCode().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
								+ bean.getSearchHidRequisitionCode();
						concatStr += msg + " :"
								+ bean.getSearchRequisitionCode() + ",";
					}

					if (!(bean.getSearchCandCode().equals("") || bean
							.getSearchCandCode().equals("null"))) {
						query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
						concatStr += msg1 + " :" + bean.getSearchCandName()
								+ ",";
					}

					if (!(bean.getSearchPositionId().equals("") || bean
							.getSearchPositionId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_POSITION="
								+ bean.getSearchPositionId();
						concatStr += msg2 + " :" + bean.getSearchPosition()
								+ ",";
					}

					if (!(bean.getSearchHiringMgrId().equals("") || bean
							.getSearchHiringMgrId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
								+ bean.getSearchHiringMgrId();
						concatStr += msg3 + " :" + bean.getSearchHiringMgr()
								+ ",";
					}
					if (!(bean.getOffFrmDate().equals("") || bean
							.getOffFrmDate().equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_DATE >=TO_DATE(" + "'"
								+ bean.getOffFrmDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg4 + " :" + bean.getOffFrmDate() + ",";
					}

					if (!(bean.getOffToDate().equals("") || bean.getOffToDate()
							.equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_DATE <=TO_DATE(" + "'"
								+ bean.getOffToDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg5 + " :" + bean.getOffToDate() + ",";
					}
					if (!(bean.getOffRejFrmDate().equals("") || bean
							.getOffRejFrmDate().equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_ACCEPT_DATE >=TO_DATE(" + "'"
								+ bean.getOffRejFrmDate() + "'"
								+ ",'DD-MM-YYYY')";
						concatStr += msg6 + " :" + bean.getOffRejFrmDate()
								+ ",";
					}

					if (!(bean.getOffRejToDate().equals("") || bean
							.getOffRejToDate().equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_ACCEPT_DATE <=TO_DATE(" + "'"
								+ bean.getOffRejToDate() + "'"
								+ ",'DD-MM-YYYY')";
						concatStr += msg7 + " :" + bean.getOffRejToDate() + ",";
					}
				} else {
					query = getQuery(1);
					query += "WHERE HRMS_REC_OFFER.OFFER_STATUS = '" + bean.getOfferStatus()
							+ "'";
					if (!(bean.getSearchHidRequisitionCode().equals("") || bean
							.getSearchHidRequisitionCode().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
								+ bean.getSearchHidRequisitionCode();
						concatStr += msg + " :"
								+ bean.getSearchRequisitionCode() + ",";
					}

					if (!(bean.getSearchCandCode().equals("") || bean
							.getSearchCandCode().equals("null"))) {
						query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
						concatStr += msg1 + " :" + bean.getSearchCandName()
								+ ",";
					}

					if (!(bean.getSearchPositionId().equals("") || bean
							.getSearchPositionId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_POSITION="
								+ bean.getSearchPositionId();
						concatStr += msg2 + " :" + bean.getSearchPosition()
								+ ",";
					}

					if (!(bean.getSearchHiringMgrId().equals("") || bean
							.getSearchHiringMgrId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
								+ bean.getSearchHiringMgrId();
						concatStr += msg3 + " :" + bean.getSearchHiringMgr()
								+ ",";
					}
					if (!(bean.getOffFrmDate().equals("") || bean
							.getOffFrmDate().equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_DATE >=TO_DATE(" + "'"
								+ bean.getOffFrmDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg4 + " :" + bean.getOffFrmDate() + ",";
					}

					if (!(bean.getOffToDate().equals("") || bean.getOffToDate()
							.equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_DATE <=TO_DATE(" + "'"
								+ bean.getOffToDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg5 + " :" + bean.getOffToDate() + ",";
					}
					if (!(bean.getOffCanFrmDate().equals("") || bean
							.getOffCanFrmDate().equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_ACCEPT_DATE >=TO_DATE(" + "'"
								+ bean.getOffCanFrmDate() + "'"
								+ ",'DD-MM-YYYY')";
						concatStr += msg6 + " :" + bean.getOffCanFrmDate()
								+ ",";
					}

					if (!(bean.getOffCanToDate().equals("") || bean
							.getOffCanToDate().equals("null"))) {
						query += " AND HRMS_REC_OFFER.OFFER_ACCEPT_DATE <=TO_DATE(" + "'"
								+ bean.getOffCanToDate() + "'"
								+ ",'DD-MM-YYYY')";
						concatStr += msg7 + " :" + bean.getOffCanToDate() + ",";
					}
					/* added by Nilesh */
					if (bean.getUserProfileDivision() != null
							&& !bean.getUserProfileDivision().equals("")) {
						query += " AND HRMS_REC_REQS_HDR.REQS_DIVISION IN ("
								+ bean.getUserProfileDivision() + ")";

					}
					//query += " ORDER BY  REQS_DIVISION";
				}
			}
			query +=" ORDER BY TO_DATE(HRMS_REC_OFFER.OFFER_EXP_JOINDATE, 'DD-MM-YYYY') DESC";
			 
			offerListData = getSqlModel().getSingleResult(query);
			if (offerListData != null && offerListData.length > 0) {
				bean.setRecordLength(true);
			} else {
				bean.setRecordLength(false);
			}
			String[] pageIndex = Utility.doPaging(bean.getMyPageOffer(),
					offerListData.length, 20);
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
			if (pageIndex[4].equals("1")) {
				bean.setMyPageOffer("1");
			}

			if (offerListData != null && offerListData.length != 0) {
				bean.setRecordLength(true);
				bean.setTotalRecords(String.valueOf(offerListData.length));
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					String query1 = getQuery(2);
					try {
						CreateOffer bean1 = new CreateOffer();
						bean1.setOfferCode(checkNull(String
								.valueOf(offerListData[i][0]))); // Offer code
						bean1.setReqCodeOffer(checkNull(String
								.valueOf(offerListData[i][1])));// Requisition
																// code
						bean1.setReqNameOffer(checkNull(String
								.valueOf(offerListData[i][2])));// Requisition
																// name
						bean1.setCandCodeOffer(checkNull(String
								.valueOf(offerListData[i][3])));// Candidate
																// code
						bean1.setCanddNameOffer(checkNull(String
								.valueOf(offerListData[i][4])));// Candidate
																// name
						bean1.setPositionOffer(checkNull(String
								.valueOf(offerListData[i][6])));// Position
						bean1.setPositionOfferCode(checkNull(String
								.valueOf(offerListData[i][5])));// Position code
						bean1.setHireMgrOffer(checkNull(String
								.valueOf(offerListData[i][8])));// Hiring
																// manager
						bean1.setHireMgrOfferCode(checkNull(String
								.valueOf(offerListData[i][7])));// Hiring
																// manager code
						if (bean.getOfferStatus().equals("P")
								|| bean.getOfferStatus().equals("A")
								|| bean.getOfferStatus().equals("R")) {
							bean1.setSignAuthorOfferCode(checkNull(String
									.valueOf(offerListData[i][16])));
							bean1.setSignAuthorOffer(checkNull(String
									.valueOf(offerListData[i][17])));
						}
						if (checkNull(String.valueOf(offerListData[i][9]))
								.equals(""))
							bean1.setDueDays("0"); // Due days
						else
							bean1.setDueDays(checkNull(String
									.valueOf(offerListData[i][9]))); // Due
																		// days

						bean1.setOfferDate(checkNull(String
								.valueOf(offerListData[i][10])));// Offer
																	// date
						bean1.setOfferAcceptedDate(checkNull(String
								.valueOf(offerListData[i][11])));// Offer
																	// acceptance
																	// date
						bean1.setOfferApprovedDate(checkNull(String
								.valueOf(offerListData[i][12])));// Offer
																	// approved
																	// date
						bean1.setOfferedCtc(checkNull(String
								.valueOf(offerListData[i][13])));// Offered
																	// CTC
						bean1.setResumeOffer(checkNull(String
								.valueOf(offerListData[i][14])));// Resume
						bean1.setOfferTemplate(checkNull(String
								.valueOf(offerListData[i][15])));// Offer template code
						
						bean1.setJoiningDateIterator(checkNull(String.valueOf(offerListData[i][18])));// Offer template code
						offerList.add(bean1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				bean.setOfferList(offerList);
			} else {
				bean.setNoOfferDataFlag(true);
				bean.setNoData(true);
			}
			String[] dispArr = concatStr.split(",");
			request.setAttribute("dispArr", dispArr);
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

	/**
	 * @Method cancelOffer
	 * @Purpose to cancel the offer
	 * @param bean
	 * @return boolean
	 */
	public boolean cancelOffer(CreateOffer bean) {
		boolean result = false;
		try {
			String query = "UPDATE HRMS_REC_OFFER SET HRMS_REC_OFFER.OFFER_STATUS = 'C', HRMS_REC_OFFER.OFFER_ACCEPT_DATE = TO_DATE(TO_CHAR(SYSDATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') "
					+ "WHERE HRMS_REC_OFFER.OFFER_CODE = " + bean.getHiddenOfferCode();
			result = getSqlModel().singleExecute(query);
			
			if(result){
				String updateVacancyQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN = 'N', HRMS_REC_VACANCIES_STATS.VAC_OFFER_STATUS_DATE = SYSDATE, " +
											" HRMS_REC_VACANCIES_STATS.VAC_OFFER_CODE=NULL, HRMS_REC_VACANCIES_STATS.VAC_OFFER_DATE = NULL, " +
											" HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN = 'N', HRMS_REC_VACANCIES_STATS.VAC_APPOINT_CODE = NULL, " + 
											" HRMS_REC_VACANCIES_STATS.VAC_APPOINT_DATE = NULL WHERE VAC_OFFER_CODE = "+bean.getHiddenOfferCode();
			result = getSqlModel().singleExecute(updateVacancyQuery);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void getKeepInformedSavedRecord(CreateOffer bean) {
		try {
			String selectQuery = " SELECT HRMS_REC_OFFER.OFFER_KEEP_INFORMED FROM HRMS_REC_OFFER WHERE HRMS_REC_OFFER.OFFER_CODE=" + bean.getHiddenOfferCode();
					//+ bean.getOfferCode() ;
			
			Object selectDataObj[][] = getSqlModel().getSingleResult(
					selectQuery);
			String str = "";
			String query = "";
			if (selectDataObj != null && selectDataObj.length > 0) {
				str = String.valueOf(selectDataObj[0][0]);

				if (str.length() > 0) {
					query = "  SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID "
							+ " FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID IN(" + str + ")";
				}
				Object result[][] = getSqlModel().getSingleResult(query);

				ArrayList<CreateOffer> leaveList = new ArrayList<CreateOffer>();
				if (result != null) {

					for (int i = 0; i < result.length; i++) {
						CreateOffer localbean = new CreateOffer();
						localbean.setKeepInformedEmpId(String
								.valueOf(result[i][1]));
						localbean.setKeepInformedEmpName(String
								.valueOf(result[i][0]));
						localbean.setSerialNo(String.valueOf(i + 1));
						leaveList.add(localbean);
					}
					bean.setKeepInformedList(leaveList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
