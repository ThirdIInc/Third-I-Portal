package org.paradyne.model.LMS;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.LMS.TradeUnionWorkCouncilBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class TradeUnionWorkCouncilModel extends ModelBase {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TradeUnionWorkCouncilModel.class);

	public void getList(TradeUnionWorkCouncilBean councilBean,
			HttpServletRequest request) {

		{
			Object[][] selData = null;
			ArrayList list = new ArrayList();
			try {
				String selQuery = " SELECT  COMMITTEE_NAME, DECODE(COMMITTEE_TYPE, 'T','Trade Union','W','Work Council'), COMMITTEE_LEADER, DECODE(BARGAIN_AGRMNT_PRESENT,'B','Yes','N','No'), "
						+ "DECODE(GRIEVANCE_PROC_PRESENT,'G','Yes','N','No'),COMMITTEE_ID FROM HRMS_COMMITEE_HDR ORDER BY COMMITTEE_ID ";

				selData = getSqlModel().getSingleResult(selQuery);
			} catch (Exception e) {
				logger.error("exception in due query", e);
			}
			String[] pageIndex = new org.paradyne.lib.Utility().doPaging(
					councilBean.getMyPage(), selData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				councilBean.setMyPage("1");
			if (selData == null) {

			} else if (selData.length == 0) {

			} else {
				councilBean.setTotalRecords("" + selData.length);
				councilBean.setModeLength("true");
				try {
					for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
							.parseInt(String.valueOf(pageIndex[1])); i++) {
						councilBean.setModeLength("true");
						TradeUnionWorkCouncilBean beanItt = new TradeUnionWorkCouncilBean();
						beanItt.setCommitteeName(checkNull(String
								.valueOf(selData[i][0])));
						beanItt.setCommitteeType(checkNull(String
								.valueOf(selData[i][1])));
						beanItt.setLeaderName(checkNull(String
								.valueOf(selData[i][2])));
						beanItt.setBargainingAgreement(checkNull(String
								.valueOf(selData[i][3])));
						beanItt.setProcedureGrievance(checkNull(String
								.valueOf(selData[i][4])));
						beanItt.setTradeUnionWorkCouncilID(checkNull(String
								.valueOf(selData[i][5])));
						list.add(beanItt);
					}
				} catch (Exception e) {
					logger.error("exception in for loop dueData", e);
				}
				councilBean.setTradeUnionWorkCouncilItt(list);
			}
		}

	}

	public boolean save(TradeUnionWorkCouncilBean councilBean,
			HttpServletRequest request) {

		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			Object insertObj[][] = new Object[1][5];

			insertObj[0][0] = councilBean.getCommitteeType();
			insertObj[0][1] = councilBean.getCommitteeName();
			insertObj[0][2] = councilBean.getLeaderName();
			/*
			 * insertObj[0][3] = councilBean .getBoardMemberName();
			 * insertObj[0][4] = councilBean .getBoardMemberType();
			 */
			System.out.println("councilBean.getBargainingAgreement() = "
					+ councilBean.getBargainingAgreement());
			if (councilBean.getBargainingAgreement().equals("B")) {
				insertObj[0][3] = "B";
			} else {
				insertObj[0][3] = "N";
			}

			/*
			 * insertObj[0][6] = councilBean .getAgreementName();
			 * insertObj[0][7] = councilBean.getAgreementDetails();
			 * insertObj[0][8] = councilBean.getAgrrementExpiryDate();
			 */

			if (councilBean.getProcedureGrievance().equals("G")) {
				insertObj[0][4] = "G";
			} else {
				insertObj[0][4] = "N";
			}

			/*
			 * insertObj[0][10] = councilBean.getProcedureName();
			 * insertObj[0][10] = councilBean.getProcedureDetails();
			 */

			String insertQuery = "INSERT INTO HRMS_COMMITEE_HDR"
					+ "(COMMITTEE_ID, COMMITTEE_TYPE,  COMMITTEE_NAME,COMMITTEE_LEADER, BARGAIN_AGRMNT_PRESENT, GRIEVANCE_PROC_PRESENT)"
					+ " VALUES((SELECT NVL(MAX(COMMITTEE_ID),0)+1 FROM HRMS_COMMITEE_HDR),?,?,?,?,?)";

			result = getSqlModel().singleExecute(insertQuery, insertObj);

			// General Information end
			// Enteries for board members

			String[] boardMember = request
					.getParameterValues("boardMemberName");
			String[] boardMemberType = request
					.getParameterValues("boardMemberType");

			Object insertBoardMembersDtlQueryObj[][] = new Object[1][2];
			if (insertBoardMembersDtlQueryObj != null
					&& insertBoardMembersDtlQueryObj.length > 0) {
				logger.info("--insertBoardMembersDtlQueryObj--len-"
						+ insertBoardMembersDtlQueryObj.length);
				for (int i = 0; i < boardMember.length; i++) {

					insertBoardMembersDtlQueryObj[0][0] = boardMember[i];
					insertBoardMembersDtlQueryObj[0][1] = boardMemberType[i];

					String insertBoardMembersDtlQuery = " INSERT INTO HRMS_COMMITEE_MEMBERS ( COMMITTEE_DTL_ID, COMMITTEE_ID, COMMITTEE_MEMBER, COMMITTEE_REPRESENTATIVE ) "
							+ "VALUES ( (SELECT NVL(MAX(COMMITTEE_DTL_ID),0)+1 FROM HRMS_COMMITEE_MEMBERS), (select MAX(COMMITTEE_ID ) from HRMS_COMMITEE_HDR), ?, ?) ";

					result = getSqlModel().singleExecute(
							insertBoardMembersDtlQuery,
							insertBoardMembersDtlQueryObj);

				}

			}

			for (int i = 0; i < 2; i++) {
				System.out.println("Board Member Details::::::"
						+ insertBoardMembersDtlQueryObj[0][i]);
			}

			// Enteries for board members end

			// Enteries for Bargaing Aggreement

			Object bargainAgrrementIDObj[][] = getSqlModel()
					.getSingleResult(
							"SELECT NVL(MAX(COMM_AGRMNT_GRVNCE_ID),0)+1 FROM HRMS_COMM_AGRMNT_GRVNCE");
			int bargainAgrrementIncrementedID = Integer.parseInt(""
					+ bargainAgrrementIDObj[0][0]);

			String[] agreementName = request
					.getParameterValues("agreementName");
			String[] agreementDetails = request
					.getParameterValues("agreementDetails");
			String[] agreementExpDate = request
					.getParameterValues("agrrementExpiryDate");
			String[] agreementDoc = request
					.getParameterValues("uploadLocFileName");
			int k = 0;

			Object bargainAgrrementDetailObj[][] = new Object[agreementName.length][6];
			for (int i = 0; i < agreementName.length; i++) {
				bargainAgrrementDetailObj[k][0] = ++bargainAgrrementIncrementedID;
				bargainAgrrementDetailObj[k][1] = checkNull(String
						.valueOf(agreementName[i]));
				;
				bargainAgrrementDetailObj[k][2] = agreementDetails[i];
				bargainAgrrementDetailObj[k][3] = agreementExpDate[i];
				bargainAgrrementDetailObj[k][4] = agreementDoc[i];
				bargainAgrrementDetailObj[k][5] = councilBean
						.getBargainingAgreement();

				k++;
			}
			String bargainAgrrementDetailsQuery = " INSERT INTO HRMS_COMM_AGRMNT_GRVNCE ( COMM_AGRMNT_GRVNCE_ID, COMMITTEE_ID, COMM_AGRMNT_GRVNCE_NAME, COMM_AGRMNT_GRVNCE_DTLS, COMM_AGRMNT_GRVNCE_EXPIRY, COMM_AGRMNT_GRVNCE_DOCS, COMM_AGRMNT_GRVNCE_TYPE) "
					+ "VALUES ( ?, (select MAX(COMMITTEE_ID ) from HRMS_COMMITEE_HDR), ?, ?,TO_DATE(?,'DD-MM-YYYY'),?,?) ";
			getSqlModel().singleExecute(bargainAgrrementDetailsQuery,
					bargainAgrrementDetailObj);

			for (int i = 0; i < 6; i++) {
				System.out.println("Bargaing Aggreement Details::::::"
						+ bargainAgrrementDetailObj[0][i]);
			}

			// Enteries for Bargaing Aggreement end

			// Enteries for Procedure Grievance
			Object procedureGrievanceIDObj[][] = getSqlModel()
					.getSingleResult(
							"SELECT NVL(MAX(COMM_AGRMNT_GRVNCE_ID),0)+1 FROM HRMS_COMM_AGRMNT_GRVNCE");
			int procedureGrievanceIncrementedID = Integer.parseInt(""
					+ procedureGrievanceIDObj[0][0]);

			String[] procedureName = request
					.getParameterValues("procedureName");
			String[] procedureDetails = request
					.getParameterValues("procedureDetails");
			String[] procedureDoc = request
					.getParameterValues("uploadProcGrievanceLocFileName");
			int l = 0;

			Object procedureGrievanceDetailObj[][] = new Object[agreementName.length][5];
			for (int i = 0; i < agreementName.length; i++) {
				procedureGrievanceDetailObj[l][0] = ++procedureGrievanceIncrementedID;
				procedureGrievanceDetailObj[l][1] = checkNull(String
						.valueOf(procedureName[i]));
				procedureGrievanceDetailObj[l][2] = procedureDetails[i];
				procedureGrievanceDetailObj[l][3] = procedureDoc[i];
				procedureGrievanceDetailObj[l][4] = councilBean
						.getProcedureGrievance();

				l++;
			}
			String procedureGrievanceDetailsQuery = " INSERT INTO HRMS_COMM_AGRMNT_GRVNCE ( COMM_AGRMNT_GRVNCE_ID, COMMITTEE_ID, COMM_AGRMNT_GRVNCE_NAME, COMM_AGRMNT_GRVNCE_DTLS, COMM_AGRMNT_GRVNCE_DOCS, COMM_AGRMNT_GRVNCE_TYPE) "
					+ "VALUES ( ?, (select MAX(COMMITTEE_ID ) from HRMS_COMMITEE_HDR), ?, ?,?,?) ";
			getSqlModel().singleExecute(procedureGrievanceDetailsQuery,
					procedureGrievanceDetailObj);

			for (int i = 0; i < 5; i++) {
				System.out.println("Procedure Grievance Details::::::"
						+ procedureGrievanceDetailObj[0][i]);
			}

			// Enteries for Procedure Grievance end
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * following function is called to delete a record
	 * 
	 * @param custID
	 * 
	 * @param cbean
	 * @return
	 */
	public boolean delRecord(TradeUnionWorkCouncilBean councilBean,
			String tradeID) {
		boolean result = false;

		Object del[][] = new Object[1][1];
		// to delete the single record after clicking on saving or searching
		// button
		del[0][0] = tradeID;

		System.out.println("councilBean.getTradeUnionWorkCouncilID() = "
				+ councilBean.getTradeUnionWorkCouncilID());
		String deleteAgrGrivQuery = "DELETE FROM HRMS_COMM_AGRMNT_GRVNCE WHERE COMMITTEE_ID=? ";

		result = getSqlModel().singleExecute(deleteAgrGrivQuery, del);
		System.out.println("deleteAgrGrivQuery = "
				+ deleteAgrGrivQuery.length());
		String deleteBargAgreeQuery = "DELETE FROM HRMS_COMMITEE_MEMBERS WHERE COMMITTEE_ID=? ";

		result = getSqlModel().singleExecute(deleteBargAgreeQuery, del);
		System.out.println("deleteBargAgreeQuery = "
				+ deleteBargAgreeQuery.length());
		String deleteQuery = "DELETE FROM HRMS_COMMITEE_HDR WHERE COMMITTEE_ID=? ";

		result = getSqlModel().singleExecute(deleteQuery, del);

		return result;
	}// End of delRecord method

	public boolean deleteCheckedRecords(TradeUnionWorkCouncilBean councilBean,
			String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];

					String deleteAgrGrivQuery = "DELETE FROM HRMS_COMM_AGRMNT_GRVNCE WHERE COMMITTEE_ID=? ";
					result = getSqlModel().singleExecute(deleteAgrGrivQuery,
							delete);

					String deleteBargAgreeQuery = "DELETE FROM HRMS_COMMITEE_MEMBERS WHERE COMMITTEE_ID=? ";
					result = getSqlModel().singleExecute(deleteBargAgreeQuery,
							delete);

					String deleteCommHdrQuery = "DELETE FROM HRMS_COMMITEE_HDR WHERE COMMITTEE_ID=? ";
					result = getSqlModel().singleExecute(deleteCommHdrQuery,
							delete);

					if (!result)
						count++;
				}// end of if
			} // end of for loop
		} // end of if
		if (count != 0) {
			result = false;
			return result;
		} // end of if
		else {
			result = true;
			return result;
		} // end of else
	} // end of deleteCheckedRecords

	/*
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	public void calforedit(TradeUnionWorkCouncilBean councilBean,
			String custID, HttpServletRequest request) {
		{

			try {

				String headerquery = " SELECT  COMMITTEE_NAME, COMMITTEE_TYPE, "
						+ " COMMITTEE_LEADER, BARGAIN_AGRMNT_PRESENT, GRIEVANCE_PROC_PRESENT"
						+ " FROM HRMS_COMMITEE_HDR"
						+ " WHERE  HRMS_COMMITEE_HDR.COMMITTEE_ID = "
						+ custID
						+ " ORDER BY HRMS_COMMITEE_HDR.COMMITTEE_ID ";

				Object[][] data = getSqlModel().getSingleResult(headerquery);

				councilBean.setCommitteeName(checkNull(String
						.valueOf(data[0][0])));
				councilBean.setCommitteeType(checkNull(String
						.valueOf(data[0][1])));
				councilBean
						.setLeaderName(checkNull(String.valueOf(data[0][2])));

				System.out.println("String.valueOf(updateObj[0][3] = "
						+ String.valueOf(data[0][3]));

				/*
				 * if((String.valueOf(data[0][3])).equals("B")){
				 * councilBean.setBargainingAgreement(checkNull(String.valueOf(data[0][3])));
				 * request.setAttribute("valueRadioStatus",
				 * councilBean.getBargainingAgreement()); }
				 */

				councilBean.setBargAgrrOption(String.valueOf(data[0][3]));
				councilBean.setProcgrievanceOption(checkNull(String
						.valueOf(data[0][4])));

				councilBean.setTradeUnionWorkCouncilID(custID);

				for (int i = 0; i < 4; i++) {
					System.out.println(" EDIT Board Member Details::::::"
							+ data[0][i]);
				}

				System.out
						.println("councilBean.getTradeUnionWorkCouncilID()  : "
								+ councilBean.getTradeUnionWorkCouncilID());

				String query = " SELECT  COMMITTEE_NAME, DECODE(COMMITTEE_TYPE, 'T','Trade Union','W','Work Council'), "
						+ " COMMITTEE_LEADER, DECODE(BARGAIN_AGRMNT_PRESENT,'Y','Yes','N','No'), DECODE(GRIEVANCE_PROC_PRESENT,'Y','Yes','N','No'),"
						+ " HRMS_COMMITEE_MEMBERS.COMMITTEE_MEMBER, HRMS_COMMITEE_MEMBERS.COMMITTEE_REPRESENTATIVE"
						+ " FROM HRMS_COMMITEE_HDR"
						+ " INNER JOIN HRMS_COMMITEE_MEMBERS ON (HRMS_COMMITEE_MEMBERS.COMMITTEE_ID=HRMS_COMMITEE_HDR.COMMITTEE_ID)"
						+ " WHERE  HRMS_COMMITEE_HDR.COMMITTEE_ID = "
						+ custID
						+ " ORDER BY HRMS_COMMITEE_HDR.COMMITTEE_ID ";

				Object witnessObj[][] = getSqlModel().getSingleResult(query);
				System.out.println("Length :: " + witnessObj.length);

				ArrayList<Object> witnessList = new ArrayList<Object>();
				if (witnessObj != null && witnessObj.length > 0) {
					for (int i = 0; i < witnessObj.length; i++) {

						TradeUnionWorkCouncilBean innerbean = new TradeUnionWorkCouncilBean();

						innerbean.setCommitteeName(checkNull(String
								.valueOf(witnessObj[i][0])));
						innerbean.setCommitteeType(checkNull(String
								.valueOf(witnessObj[i][1])));
						innerbean.setLeaderName(checkNull(String
								.valueOf(witnessObj[i][2])));
						innerbean.setBargainingAgreement(checkNull(String
								.valueOf(witnessObj[i][3])));
						innerbean.setProcedureGrievance(checkNull(String
								.valueOf(witnessObj[i][4])));
						innerbean.setBoardMemberName(checkNull(String
								.valueOf(witnessObj[i][5])));
						innerbean.setBoardMemberType(checkNull(String
								.valueOf(witnessObj[i][6])));

						witnessList.add(innerbean);
					}
					councilBean.setBoardMembersDetailsList(witnessList);
				}

				// Bargaining Agreement

				String bargainQuery = " SELECT COMM_AGRMNT_GRVNCE_NAME, COMM_AGRMNT_GRVNCE_DTLS,TO_CHAR(COMM_AGRMNT_GRVNCE_EXPIRY,'DD-MM-YYYY'), COMM_AGRMNT_GRVNCE_DOCS, COMM_AGRMNT_GRVNCE_TYPE, "
						+ "	COMM_AGRMNT_GRVNCE_ID FROM HRMS_COMM_AGRMNT_GRVNCE"
						+ " WHERE  HRMS_COMM_AGRMNT_GRVNCE.COMMITTEE_ID = "
						+ custID
						+ " AND HRMS_COMM_AGRMNT_GRVNCE.COMM_AGRMNT_GRVNCE_TYPE='B' ORDER BY HRMS_COMM_AGRMNT_GRVNCE.COMMITTEE_ID ";

				Object bargainObj[][] = getSqlModel().getSingleResult(
						bargainQuery);
				System.out.println("Length :: " + bargainObj.length);

				ArrayList<Object> bargainList = new ArrayList<Object>();
				if (bargainObj != null && bargainObj.length > 0) {
					for (int i = 0; i < bargainObj.length; i++) {

						TradeUnionWorkCouncilBean innerbean = new TradeUnionWorkCouncilBean();

						innerbean.setAgreementName(checkNull(String
								.valueOf(bargainObj[i][0])));
						innerbean.setAgreementDetails(checkNull(String
								.valueOf(bargainObj[i][1])));
						innerbean.setAgrrementExpiryDate(checkNull(String
								.valueOf(bargainObj[i][2])));
						innerbean.setUploadLocFileName(checkNull(String
								.valueOf(bargainObj[i][3])));
						innerbean.setBargainingAgreement(checkNull(String
								.valueOf(bargainObj[i][4])));

						innerbean
								.setCommitteeAgreementGrievanceId(checkNull(String
										.valueOf(bargainObj[i][5])));

						bargainList.add(innerbean);
					}
					councilBean.setBargainAggreementList(bargainList);
				}

				// Procedure Grievance

				String procedureQuery = " SELECT COMM_AGRMNT_GRVNCE_NAME, COMM_AGRMNT_GRVNCE_DTLS,COMM_AGRMNT_GRVNCE_DOCS, COMM_AGRMNT_GRVNCE_TYPE, "
						+ "	COMM_AGRMNT_GRVNCE_ID FROM HRMS_COMM_AGRMNT_GRVNCE"
						+ " WHERE  HRMS_COMM_AGRMNT_GRVNCE.COMMITTEE_ID = "
						+ custID
						+ " AND HRMS_COMM_AGRMNT_GRVNCE.COMM_AGRMNT_GRVNCE_TYPE='G' ORDER BY HRMS_COMM_AGRMNT_GRVNCE.COMMITTEE_ID ";

				Object procedureObj[][] = getSqlModel().getSingleResult(
						procedureQuery);
				System.out.println("Length :: " + procedureObj.length);

				ArrayList<Object> procedureList = new ArrayList<Object>();
				if (procedureObj != null && procedureObj.length > 0) {
					for (int i = 0; i < procedureObj.length; i++) {

						TradeUnionWorkCouncilBean innerbean = new TradeUnionWorkCouncilBean();

						innerbean.setProcedureName(checkNull(String
								.valueOf(procedureObj[i][0])));
						innerbean.setProcedureDetails(checkNull(String
								.valueOf(procedureObj[i][1])));
						innerbean
								.setUploadProcGrievanceLocFileName(checkNull(String
										.valueOf(procedureObj[i][2])));
						innerbean.setProcedureGrievance(checkNull(String
								.valueOf(procedureObj[i][3])));

						innerbean
								.setCommitteeAgreementGrievanceId(checkNull(String
										.valueOf(procedureObj[i][4])));

						procedureList.add(innerbean);
					}
					councilBean.setProcedureGrievanceList(procedureList);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

	public boolean update(TradeUnionWorkCouncilBean councilBean,
			HttpServletRequest request) {
		boolean result = false;
		try {

			Object updateObj[][] = new Object[1][5];

			updateObj[0][0] = councilBean.getCommitteeType();
			updateObj[0][1] = councilBean.getCommitteeName();
			updateObj[0][2] = councilBean.getLeaderName();
			/*
			 * insertObj[0][3] = councilBean .getBoardMemberName();
			 * insertObj[0][4] = councilBean .getBoardMemberType();
			 */

			if (councilBean.getBargainingAgreement().equals("B")) {
				updateObj[0][3] = "B";
			} else {
				updateObj[0][3] = "N";
			}

			/*
			 * insertObj[0][6] = councilBean .getAgreementName();
			 * insertObj[0][7] = councilBean.getAgreementDetails();
			 * insertObj[0][8] = councilBean.getAgrrementExpiryDate();
			 */

			if (councilBean.getProcedureGrievance().equals("G")) {
				updateObj[0][4] = "G";
			} else {
				updateObj[0][4] = "N";
			}

			/*
			 * insertObj[0][10] = councilBean.getProcedureName();
			 * insertObj[0][10] = councilBean.getProcedureDetails();
			 */

			String updateQuery = "UPDATE HRMS_COMMITEE_HDR SET "
					+ " COMMITTEE_TYPE = ? ,  COMMITTEE_NAME  = ? ,COMMITTEE_LEADER = ? , BARGAIN_AGRMNT_PRESENT = ? , GRIEVANCE_PROC_PRESENT = ? WHERE COMMITTEE_ID = "
					+ councilBean.getTradeUnionWorkCouncilID();

			result = getSqlModel().singleExecute(updateQuery, updateObj);

			// General Information end
			// Enteries for board members

			Object detailQueryObj[][] = getSqlModel().getSingleResult(
					"SELECT " + "	COMMITTEE_ID FROM HRMS_COMMITEE_MEMBERS"
							+ " WHERE  HRMS_COMMITEE_MEMBERS.COMMITTEE_ID = "
							+ councilBean.getTradeUnionWorkCouncilID());
			Object detailObj[][] = new Object[detailQueryObj.length][1];
			int z = 0;

			if (detailQueryObj != null && detailQueryObj.length > 0) {
				for (int i = 0; i < detailQueryObj.length; i++) {
					detailObj[z][0] = detailQueryObj[i][0];
					System.out.println("ID [" + i + "]" + detailObj[z][0]);
					z++;
				}
				String deleteWitnessQuery = "DELETE FROM HRMS_COMMITEE_MEMBERS WHERE HRMS_COMMITEE_MEMBERS.COMMITTEE_ID=?";
				getSqlModel().singleExecute(deleteWitnessQuery, detailObj);
			}
			// Delete and insert Begins

			String[] boardMember = request
					.getParameterValues("boardMemberName");
			String[] boardMemberType = request
					.getParameterValues("boardMemberType");

			Object insertBoardMembersDtlQueryObj[][] = new Object[1][2];
			if (insertBoardMembersDtlQueryObj != null
					&& insertBoardMembersDtlQueryObj.length > 0) {
				logger.info("--insertBoardMembersDtlQueryObj--len-"
						+ insertBoardMembersDtlQueryObj.length);
				for (int i = 0; i < boardMember.length; i++) {

					insertBoardMembersDtlQueryObj[0][0] = boardMember[i];
					insertBoardMembersDtlQueryObj[0][1] = boardMemberType[i];

					String insertBoardMembersDtlQuery = " INSERT INTO HRMS_COMMITEE_MEMBERS ( COMMITTEE_DTL_ID, COMMITTEE_ID, COMMITTEE_MEMBER, COMMITTEE_REPRESENTATIVE ) "
							+ "VALUES ( (SELECT NVL(MAX(COMMITTEE_DTL_ID),0)+1 FROM HRMS_COMMITEE_MEMBERS), (select MAX(COMMITTEE_ID ) from HRMS_COMMITEE_HDR), ?, ?) ";

					result = getSqlModel().singleExecute(
							insertBoardMembersDtlQuery,
							insertBoardMembersDtlQueryObj);

				}

			}

			for (int i = 0; i < 2; i++) {
				System.out.println("Board Member Details::::::"
						+ insertBoardMembersDtlQueryObj[0][i]);
			}

			// Delete and insert Ends

			// Enteries for Bargaining Agreement

			Object detailBargainingAgreementQueryObj[][] = getSqlModel()
					.getSingleResult(
							"SELECT COMMITTEE_ID FROM HRMS_COMM_AGRMNT_GRVNCE"
									+ " WHERE HRMS_COMM_AGRMNT_GRVNCE.COMMITTEE_ID = "
									+ councilBean.getTradeUnionWorkCouncilID());
			Object detailBargainingAgreementObj[][] = new Object[detailBargainingAgreementQueryObj.length][1];
			int t = 0;

			if (detailBargainingAgreementQueryObj != null
					&& detailBargainingAgreementQueryObj.length > 0) {
				for (int i = 0; i < detailBargainingAgreementQueryObj.length; i++) {
					detailBargainingAgreementObj[t][0] = detailBargainingAgreementQueryObj[i][0];
					System.out.println("ID [" + i + "]"
							+ detailBargainingAgreementObj[t][0]);
					t++;
				}
				String deleteWitnessQuery = "DELETE FROM HRMS_COMM_AGRMNT_GRVNCE WHERE HRMS_COMM_AGRMNT_GRVNCE.COMMITTEE_ID=?  AND COMM_AGRMNT_GRVNCE_TYPE = 'B' ";
				getSqlModel().singleExecute(deleteWitnessQuery,
						detailBargainingAgreementObj);
			}

			// Delete and insert Begins

			String[] agreementName = request
					.getParameterValues("agreementName");
			String[] agreementDetails = request
					.getParameterValues("agreementDetails");
			String[] agreementExpDate = request
					.getParameterValues("agrrementExpiryDate");
			String[] agreementDoc = request
					.getParameterValues("uploadLocFileName");

			Object bargainAgrrementDetailObj[][] = new Object[1][5];
			for (int i = 0; i < agreementName.length; i++) {
				// bargainAgrrementDetailObj[k][0] =
				// ++bargainAgrrementIncrementedID;
				System.out.println("qqqqqqq");

				// bargainAgrrementDetailObj[k][1] =
				// councilBean.getTradeUnionWorkCouncilID();

				bargainAgrrementDetailObj[0][0] = agreementName[i];

				bargainAgrrementDetailObj[0][1] = agreementDetails[i];
				bargainAgrrementDetailObj[0][2] = agreementExpDate[i];
				bargainAgrrementDetailObj[0][3] = agreementDoc[i];
				bargainAgrrementDetailObj[0][4] = councilBean
						.getBargainingAgreement();

				String bargainAgrrementDetailsQuery = " INSERT INTO HRMS_COMM_AGRMNT_GRVNCE ( COMM_AGRMNT_GRVNCE_ID, COMMITTEE_ID, COMM_AGRMNT_GRVNCE_NAME, COMM_AGRMNT_GRVNCE_DTLS, COMM_AGRMNT_GRVNCE_EXPIRY, COMM_AGRMNT_GRVNCE_DOCS, COMM_AGRMNT_GRVNCE_TYPE) "
						+ "VALUES ( (SELECT NVL(MAX(COMM_AGRMNT_GRVNCE_ID),0)+1 FROM HRMS_COMM_AGRMNT_GRVNCE), (select MAX(COMMITTEE_ID ) from HRMS_COMMITEE_HDR), ?, ?,TO_DATE(?,'DD-MM-YYYY'),?,?) ";
				getSqlModel().singleExecute(bargainAgrrementDetailsQuery,
						bargainAgrrementDetailObj);
			}

			for (int i = 0; i < 5; i++) {
				System.out.println("Bargaing Aggreement Details::::::"
						+ bargainAgrrementDetailObj[0][i]);
			}

			// Enteries for Bargaing Aggreement end

			// Enteries for Procedure Grievance

			Object detailProcedureGrievanceQueryObj[][] = getSqlModel()
					.getSingleResult(
							"SELECT COMMITTEE_ID FROM HRMS_COMM_AGRMNT_GRVNCE"
									+ " WHERE HRMS_COMM_AGRMNT_GRVNCE.COMMITTEE_ID = "
									+ councilBean.getTradeUnionWorkCouncilID());
			Object detailProcedureGrievanceObj[][] = new Object[detailProcedureGrievanceQueryObj.length][1];
			int r = 0;

			if (detailProcedureGrievanceQueryObj != null
					&& detailProcedureGrievanceQueryObj.length > 0) {
				for (int i = 0; i < detailProcedureGrievanceQueryObj.length; i++) {
					detailProcedureGrievanceObj[r][0] = detailProcedureGrievanceQueryObj[i][0];
					System.out.println("ID [" + i + "]"
							+ detailProcedureGrievanceObj[r][0]);
					r++;
				}

				String deleteWitnessQuery = "DELETE FROM HRMS_COMM_AGRMNT_GRVNCE WHERE HRMS_COMM_AGRMNT_GRVNCE.COMMITTEE_ID=? AND COMM_AGRMNT_GRVNCE_TYPE = 'G' ";
				getSqlModel().singleExecute(deleteWitnessQuery,
						detailProcedureGrievanceObj);

			}

			// Delete and insert Begins
			Object procedureGrievanceIDObj[][] = getSqlModel()
					.getSingleResult(
							"SELECT NVL(MAX(COMM_AGRMNT_GRVNCE_ID),0)+1 FROM HRMS_COMM_AGRMNT_GRVNCE");
			int procedureGrievanceIncrementedID = Integer.parseInt(""
					+ procedureGrievanceIDObj[0][0]);

			String[] procedureName = request
					.getParameterValues("procedureName");
			String[] procedureDetails = request
					.getParameterValues("procedureDetails");
			String[] procedureDoc = request
					.getParameterValues("uploadProcGrievanceLocFileName");
			int l = 0;

			Object procedureGrievanceDetailObj[][] = new Object[1][4];
			for (int i = 0; i < procedureName.length; i++) {
				try {
					// procedureGrievanceDetailObj[l][0] =
					// ++procedureGrievanceIncrementedID;
					procedureGrievanceDetailObj[0][0] = checkNull(String
							.valueOf(procedureName[i]));
					procedureGrievanceDetailObj[0][1] = procedureDetails[i];
					procedureGrievanceDetailObj[0][2] = procedureDoc[i];
					procedureGrievanceDetailObj[0][3] = councilBean
							.getProcedureGrievance();

					String procedureGrievanceDetailsQuery = " INSERT INTO HRMS_COMM_AGRMNT_GRVNCE ( COMM_AGRMNT_GRVNCE_ID, COMMITTEE_ID, COMM_AGRMNT_GRVNCE_NAME, COMM_AGRMNT_GRVNCE_DTLS, COMM_AGRMNT_GRVNCE_DOCS, COMM_AGRMNT_GRVNCE_TYPE) "
							+ "VALUES ( (SELECT NVL(MAX(COMM_AGRMNT_GRVNCE_ID),0)+1 FROM HRMS_COMM_AGRMNT_GRVNCE), (select MAX(COMMITTEE_ID ) from HRMS_COMMITEE_HDR), ?, ?,?,?) ";
					getSqlModel().singleExecute(procedureGrievanceDetailsQuery,
							procedureGrievanceDetailObj);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			for (int i = 0; i < 4; i++) {
				System.out.println("Procedure Grievance Details::::::"
						+ procedureGrievanceDetailObj[0][i]);
			}

			// Enteries for Procedure Grievance end

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
