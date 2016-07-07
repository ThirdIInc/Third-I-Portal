package org.paradyne.model.D1;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.ReferralSourceBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class ReferralSourceModel extends ModelBase {

	/* Generating the list Onload */
	public void initialData(ReferralSourceBean refBean,
			HttpServletRequest request) {

		Object[][] refData = getSqlModel().getSingleResult(getQuery(3));

		if (refData != null && refData.length > 0) {
			refBean.setModeLength("true");

			refBean.setTotalNoOfRecords(String.valueOf(refData.length));

			String[] pageIndex = Utility.doPaging(refBean.getMyPage(),
					refData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				refBean.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();

			refBean.setReferralListLength(true);
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				ReferralSourceBean bean = new ReferralSourceBean();
				bean.setRefCode(checkNull(String.valueOf(refData[i][0])));
				bean.setRefSource(checkNull(String.valueOf(refData[i][1])));

				List.add(bean);
			}// end of loop
			System.out.println("in main page---" + List.size());
			refBean.setReferralList(List);
		}

		else {

			refBean.setReferralList(null);

		}
	}

	/* Check Null Function */

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/* For inserting record into DB */

	public boolean insert(ReferralSourceBean refBean) {
		if (!checkDuplicateAdd(refBean)) {

			Object addObj[][] = new Object[1][2];
			String query1 = "SELECT NVL(MAX(REFERRAL_ID),0)+1 FROM  HRMS_D1_REFERRAL";
			Object[][] softId = getSqlModel().getSingleResult(query1);

			addObj[0][0] = checkNull(String.valueOf(softId[0][0]));
			addObj[0][1] = refBean.getRefSource().trim();

			refBean.setRefCode(String.valueOf(softId[0][0]));
			return getSqlModel().singleExecute(getQuery(1), addObj);
		}

		else {
			return false;
		}

	}

	/* for checking duplicate entry of record during insertion */

	public boolean checkDuplicateAdd(ReferralSourceBean bean) {
		boolean result = false;
		String query = "SELECT REFERRAL_SOURCE_NAME FROM HRMS_D1_REFERRAL WHERE UPPER(REFERRAL_SOURCE_NAME) LIKE '"
				+ bean.getRefSource().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/* Modifing the record */

	public boolean update(ReferralSourceBean bean) {
		if (!checkDuplicateUpdate(bean)) {
		
		Object updateObj[][] = new Object[1][2];

		updateObj[0][0] = bean.getRefSource().trim();
		updateObj[0][1] = bean.getRefCode().trim();
		return getSqlModel().singleExecute(getQuery(2), updateObj);
		}
		else {
			return false;
		}
	}

	/* for checking duplicate entry of record during updation */

	public boolean checkDuplicateUpdate(ReferralSourceBean bean) {
		boolean result = false;
		String query = "SELECT REFERRAL_SOURCE_NAME FROM HRMS_D1_REFERRAL WHERE UPPER(REFERRAL_SOURCE_NAME) LIKE '"
				+ bean.getRefSource().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/*
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */

	public void editBusinessData(ReferralSourceBean refBean) {

		try {
			System.out
					.println("refBean.getRefCode() here we get in editBusinessData is-------------"
							+ refBean.getRefCode());

			String query = " SELECT REFERRAL_SOURCE_NAME FROM HRMS_D1_REFERRAL  WHERE REFERRAL_ID= "
					+ refBean.getRefCode();

			Object[][] data = getSqlModel().getSingleResult(query);

			refBean.setRefSource(String.valueOf(data[0][0]));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* Delete multiple records when click on delete button on list jsp */

	public boolean deleteCheckedRecords(ReferralSourceBean bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(4), delete);
					if (!result)
						count++;

				}// end of if
			}// end of for loop
		}// end of if
		if (count != 0) {
			result = false;
			return result;
		}// end of if
		else {
			result = true;
			return result;
		}// end of else
	}

	/* Delete single record when click on delete button on on saving data */

	public boolean delete(ReferralSourceBean refBean, HttpServletRequest request) {
		boolean result = false;
		System.out.println("refBean.getRefCode() in delete method call ======"
				+ refBean.getRefCode());
		String deleteId = refBean.getRefCode();

		String delQuery = "DELETE FROM HRMS_D1_REFERRAL WHERE REFERRAL_ID="
				+ deleteId;
		result = getSqlModel().singleExecute(delQuery);
		return result;
	}

}
