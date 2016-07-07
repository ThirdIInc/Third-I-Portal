package org.paradyne.model.LMS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.LMS.HouseMasterBean;
import org.paradyne.bean.LMS.HouseMasterBean;
import org.paradyne.lib.ModelBase;

public class HouseMasterModel extends ModelBase {

	public void getList(HouseMasterBean bean, HttpServletRequest request) {
		Object[][] selData = null;

		try {
			String selQuery = "SELECT  HOUSE_NO,COLONY_NAME, HOUSE_ADDRESS,LMS_HOUSE_ID FROM LMS_HOUSE LEFT JOIN LMS_COLONY ON(LMS_HOUSE.COLONY_ID=LMS_COLONY.COLONY_ID)ORDER BY LMS_HOUSE_ID ";
			selData = getSqlModel().getSingleResult(selQuery);

			// System.out.println("=========== After Query ===========");
			selData = getSqlModel().getSingleResult(selQuery);
		} catch (Exception e) {

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

		try {
			System.out.println("pageIndex  of 2============    "
					+ Integer.parseInt(String.valueOf(pageIndex[2])));
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		if (selData == null) {

		}

		ArrayList<Object> list = new ArrayList<Object>();
		if (selData != null && selData.length > 0) {

			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
					.parseInt(String.valueOf(pageIndex[1])); i++) {
				HouseMasterBean subBean = new HouseMasterBean();

				subBean.setIttHouseNo(checkNull(String.valueOf(selData[i][0])));
				subBean.setIttColonyName(checkNull(String
						.valueOf(selData[i][1])));
				subBean.setIttHouseAddress(checkNull(String
						.valueOf(selData[i][2])));

				subBean.setIttSrN0(String.valueOf(i + 1));
				subBean.setIttHouseId(String.valueOf(selData[i][3]));

				list.add(subBean);
			}
		}
		bean.setHouseMasterItt(list);
	}

	public boolean save(HouseMasterBean bean) {

		boolean result = false;

		String hNo = bean.getHouseNo();
		String cId = bean.getColonyId();
		String hAddress = bean.getHouseAddress();
		String noFamily = bean.getNoFamily();
		String noIndividual = bean.getNoIndividual();

		String selQuery = " SELECT * FROM LMS_HOUSE WHERE UPPER(HOUSE_NO) LIKE '"
				+ bean.getHouseNo().trim().toUpperCase() + "'";
		Object[][] selData = getSqlModel().getSingleResult(selQuery);
		if (selData.length == 0) {

			Object insObj[][] = new Object[1][5];

			insObj[0][0] = hNo;
			insObj[0][1] = cId;
			insObj[0][2] = hAddress;
			insObj[0][3] = noFamily;
			insObj[0][4] = noIndividual;

			String insQuery = "INSERT INTO LMS_HOUSE ( LMS_HOUSE_ID, HOUSE_NO, COLONY_ID, HOUSE_ADDRESS, NO_OF_FAMILY, NO_OF_INDIVIDUAL) VALUES((SELECT NVL(MAX(LMS_HOUSE_ID),0)+1 FROM LMS_HOUSE) ,?,?,?,?,?)";
			result = getSqlModel().singleExecute(insQuery, insObj);

			String Query = "SELECT MAX(LMS_HOUSE_ID) FROM LMS_HOUSE";
			Object[][] data = getSqlModel().getSingleResult(Query);
			bean.setHouseId(String.valueOf(data[0][0]));
		}
		return result;

	}

	public boolean update(HouseMasterBean bean) {
		boolean result = false;
		try {

			String hNo = bean.getHouseNo();
			String cId = bean.getColonyId();
			String hAddress = bean.getHouseAddress();
			String noFamily = bean.getNoFamily();
			String noIndividual = bean.getNoIndividual();
			String hId = bean.getHouseId();

			Object upObj[][] = new Object[1][6];
			upObj[0][0] = hNo;
			upObj[0][1] = cId;
			upObj[0][2] = hAddress;
			upObj[0][3] = noFamily;
			upObj[0][4] = noIndividual;
			upObj[0][5] = hId;

			String upQuery = "UPDATE LMS_HOUSE SET  HOUSE_NO=?,  COLONY_ID=?, HOUSE_ADDRESS=?, NO_OF_FAMILY=?, NO_OF_INDIVIDUAL=? WHERE LMS_HOUSE_ID=?";

			result = getSqlModel().singleExecute(upQuery, upObj);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void dblClickItt(HouseMasterBean bean) {

		getDetails(bean);
	}

	public void getDetails(HouseMasterBean bean) {
		try {
			String hId = bean.getHouseId();
			String selQuery = "SELECT  HOUSE_NO,COLONY_NAME,LMS_HOUSE.COLONY_ID, HOUSE_ADDRESS,NO_OF_FAMILY, NO_OF_INDIVIDUAL,LMS_HOUSE_ID FROM LMS_HOUSE INNER JOIN LMS_COLONY ON(LMS_HOUSE.COLONY_ID=LMS_COLONY.COLONY_ID) WHERE LMS_HOUSE_ID="
					+ hId;
			Object[][] data = getSqlModel().getSingleResult(selQuery);

			bean.setHouseNo(String.valueOf(data[0][0]));
			bean.setColonyName(String.valueOf(data[0][1]));
			bean.setColonyId(String.valueOf(data[0][2]));
			bean.setHouseAddress(String.valueOf(data[0][3]));
			bean.setNoFamily(checkNull(String.valueOf(data[0][4])));
			bean.setNoIndividual(checkNull(String.valueOf(data[0][5])));
			bean.setHouseId(String.valueOf(data[0][6]));

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean delete(HouseMasterBean bean, HttpServletRequest request) {
		boolean result = false;

		String hId = bean.getHouseId();

		String delQuery = "DELETE FROM LMS_HOUSE WHERE LMS_HOUSE_ID="+hId;
		result = getSqlModel().singleExecute(delQuery);
		getList(bean, request);
		return result;
	}

	public boolean deleteCheck(HouseMasterBean bean, String[] code,
			String[] houseId, HttpServletRequest request) {
		boolean flag = false;

	
		for (int j = 0; j < code.length; j++) {
			if (code[j].equals("Y")) {

				String delQuery = "DELETE FROM LMS_HOUSE WHERE LMS_HOUSE_ID="
						+ houseId[j] + "";
				flag = getSqlModel().singleExecute(delQuery);

			}

		}
		getList(bean, request);

		return flag;

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

}
