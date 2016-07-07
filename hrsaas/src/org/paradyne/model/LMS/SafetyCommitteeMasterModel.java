package org.paradyne.model.LMS;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.LMS.SafetyCommitteeMasterBean;
import org.paradyne.bean.LMS.TradeUnionWorkCouncilBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class SafetyCommitteeMasterModel extends ModelBase {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SafetyCommitteeMasterModel.class);

	public void getList(SafetyCommitteeMasterBean bean,
			HttpServletRequest request) {

		{
			Object[][] selData = null;
			ArrayList list = new ArrayList();
			try {
				String selQuery = " SELECT  DECODE(SAFETY_COMM_TYPE,'H','Health & Safety ','S','Sexual Harassment'), EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MEMBER_NAME,DECODE(SAFETY_TRAINING_STATUS,'Y','Yes','N','No'), SAFETY_MEMBER_ROLE "
						+ ",EMP_TOKEN ,SAFETY_COMM_CODE FROM HRMS_SAFETY_COMMITTEE  "
						+ "INNER JOIN HRMS_EMP_OFFC on (HRMS_EMP_OFFC.EMP_ID=HRMS_SAFETY_COMMITTEE.SAFETY_COMM_MEMBER)"
						+ "ORDER BY SAFETY_COMM_CODE";

				selData = getSqlModel().getSingleResult(selQuery);
			} catch (Exception e) {
				logger.error("exception in due query", e);
			}
			String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean
					.getMyPage(), selData.length, 20);
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
				bean.setMyPage("1");
			if (selData == null) {

			} else if (selData.length == 0) {

			} else {
				bean.setTotalRecords("" + selData.length);
				bean.setModeLength("true");
				try {
					for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
							.parseInt(String.valueOf(pageIndex[1])); i++) {
						bean.setModeLength("true");
						SafetyCommitteeMasterBean beanItt = new SafetyCommitteeMasterBean();
						beanItt.setCommitteeType(checkNull(String
								.valueOf(selData[i][0])));
						beanItt.setEmpName(checkNull(String
								.valueOf(selData[i][1])));
						beanItt.setTrainingReceivedFlag(checkNull(String
								.valueOf(selData[i][2])));
						beanItt.setRoleType(checkNull(String
								.valueOf(selData[i][3])));
						beanItt.setSafetyCommitteeID(checkNull(String
								.valueOf(selData[i][5])));

						list.add(beanItt);
					}
				} catch (Exception e) {
					logger.error("exception in for loop dueData", e);
				}
				bean.setSafetyCommitteeList(list);
			}
		}

	}

	public boolean save(SafetyCommitteeMasterBean bean,
			HttpServletRequest request) {

		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			Object insertObj[][] = new Object[1][4];

			insertObj[0][0] = bean.getCommitteeType();
			insertObj[0][1] = bean.getEmpCode();

			if (bean.getTrainingReceivedFlag().equals("true"))// Drinking
				// Water
				// Facility
				// Checked
				insertObj[0][2] = "Y";
			else
				insertObj[0][2] = "N";

			insertObj[0][3] = bean.getRoleType();

			String insertQuery = "INSERT INTO HRMS_SAFETY_COMMITTEE"
					+ "(SAFETY_COMM_CODE, SAFETY_COMM_TYPE,  SAFETY_COMM_MEMBER,SAFETY_TRAINING_STATUS, SAFETY_MEMBER_ROLE)"
					+ " VALUES((SELECT NVL(MAX(SAFETY_COMM_CODE),0)+1 FROM HRMS_SAFETY_COMMITTEE),?,?,?,?)";

			result = getSqlModel().singleExecute(insertQuery, insertObj);

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

	public boolean deleteCheckedRecords(SafetyCommitteeMasterBean bean,
			String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];

					String deleteCommHdrQuery = "DELETE FROM HRMS_SAFETY_COMMITTEE WHERE SAFETY_COMM_CODE=? ";
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
	public void calforedit(SafetyCommitteeMasterBean bean, String custID,
			HttpServletRequest request) {
		{

			try {

				String headerquery = " SELECT SAFETY_COMM_TYPE, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MEMBER_NAME,SAFETY_TRAINING_STATUS, SAFETY_MEMBER_ROLE "
						+ ",EMP_TOKEN ,SAFETY_COMM_CODE FROM HRMS_SAFETY_COMMITTEE  "
						+ "INNER JOIN HRMS_EMP_OFFC on (HRMS_EMP_OFFC.EMP_ID=HRMS_SAFETY_COMMITTEE.SAFETY_COMM_MEMBER)"
						+ " WHERE  HRMS_SAFETY_COMMITTEE.SAFETY_COMM_CODE = "
						+ custID
						+ " ORDER BY  HRMS_SAFETY_COMMITTEE.SAFETY_COMM_CODE ";

				Object[][] data = getSqlModel().getSingleResult(headerquery);

				bean.setCommitteeType(checkNull(String.valueOf(data[0][0])));
				bean.setEmpToken(checkNull(String.valueOf(data[0][4])));
				bean.setEmpName(checkNull(String.valueOf(data[0][1])));

				if (String.valueOf(data[0][2]).equals("Y")) {
					bean.setTrainingReceivedFlag("true");
				}

				bean.setRoleType(String.valueOf(data[0][3]));

				bean.setSafetyCommitteeID(String.valueOf(data[0][5]));

				for (int i = 0; i < 4; i++) {
					System.out.println(" EDIT Board Member Details::::::"
							+ data[0][i]);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

	public void setSafetyCommitteeRecord(SafetyCommitteeMasterBean bean, String custID) {
		System.out.println("Safety Committee list here----"
				+ custID);

		String query = " SELECT SAFETY_COMM_TYPE, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MEMBER_NAME,SAFETY_TRAINING_STATUS, SAFETY_MEMBER_ROLE "
				+ ",EMP_TOKEN ,SAFETY_COMM_MEMBER, SAFETY_COMM_CODE FROM HRMS_SAFETY_COMMITTEE  "
				+ "INNER JOIN HRMS_EMP_OFFC on (HRMS_EMP_OFFC.EMP_ID=HRMS_SAFETY_COMMITTEE.SAFETY_COMM_MEMBER)"
				+ " WHERE  HRMS_SAFETY_COMMITTEE.SAFETY_COMM_CODE = "
				+ custID;

		Object[][] data = getSqlModel().getSingleResult(query);

		bean.setCommitteeType(checkNull(String.valueOf(data[0][0])));
		bean.setEmpToken(checkNull(String.valueOf(data[0][4])));
		bean.setEmpName(checkNull(String.valueOf(data[0][1])));

		if (String.valueOf(data[0][2]).equals("Y")) {
			bean.setTrainingReceivedFlag("true");
		}

		bean.setRoleType(String.valueOf(data[0][3]));

		bean.setSafetyCommitteeID(String.valueOf(data[0][5]));

	}

	public boolean update(SafetyCommitteeMasterBean bean,
			HttpServletRequest request) {
		boolean result = false;
		try {

			Object updateObj[][] = new Object[1][4];

			updateObj[0][0] = bean.getCommitteeType();
			updateObj[0][1] = bean.getEmpCode();

			if (bean.getTrainingReceivedFlag().equals("true"))// Drinking
				// Water
				// Facility
				// Checked
				updateObj[0][2] = "Y";
			else
				updateObj[0][2] = "N";

			updateObj[0][3] = bean.getRoleType();

			String insertQuery = "UPDATE HRMS_SAFETY_COMMITTEE SET "
					+ "  SAFETY_COMM_TYPE = ? ,  SAFETY_COMM_MEMBER = ? ,SAFETY_TRAINING_STATUS = ? , SAFETY_MEMBER_ROLE = ? WHERE SAFETY_COMM_CODE = "
					+ bean.getSafetyCommitteeID();

			result = getSqlModel().singleExecute(insertQuery, updateObj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean delRecord(SafetyCommitteeMasterBean bean) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = bean.getSafetyCommitteeID();
		} catch (Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_SAFETY_COMMITTEE WHERE SAFETY_COMM_CODE=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}

	public void getRecord(SafetyCommitteeMasterBean bean) {
		try {
			System.out.println("Hidden code =========> "
					+ bean.getSafetyCommitteeID());
			String query = " SELECT SAFETY_COMM_TYPE, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MEMBER_NAME,SAFETY_TRAINING_STATUS, SAFETY_MEMBER_ROLE "
					+ ",EMP_TOKEN ,SAFETY_COMM_CODE FROM HRMS_SAFETY_COMMITTEE  "
					+ "INNER JOIN HRMS_EMP_OFFC on (HRMS_EMP_OFFC.EMP_ID=HRMS_SAFETY_COMMITTEE.SAFETY_COMM_MEMBER)"
					+ " WHERE  HRMS_SAFETY_COMMITTEE.SAFETY_COMM_CODE = "
					+ bean.getSafetyCommitteeID();

			Object[][] data = getSqlModel().getSingleResult(query);

			bean.setCommitteeType(checkNull(String.valueOf(data[0][0])));
			bean.setEmpToken(checkNull(String.valueOf(data[0][4])));
			bean.setEmpName(checkNull(String.valueOf(data[0][1])));

			if (String.valueOf(data[0][2]).equals("Y")) {
				bean.setTrainingReceivedFlag("true");
			}

			bean.setRoleType(String.valueOf(data[0][3]));

			bean.setSafetyCommitteeID(String.valueOf(data[0][5]));

		} catch (Exception e) {
			System.out.println("Exception in getRecord -- " + e);
		}

	}// End of getRecord

}
