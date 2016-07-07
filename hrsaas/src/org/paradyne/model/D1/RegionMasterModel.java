package org.paradyne.model.D1;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.BusinessUnitBean;
import org.paradyne.bean.D1.RegionMasterBean;
import org.paradyne.bean.TravelManagement.Master.CityGrade;
import org.paradyne.bean.admin.master.CenterMaster;
import org.paradyne.bean.admin.master.DeptMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class RegionMasterModel extends ModelBase {

	/**For inserting record into DB */
	public boolean addRegionData(RegionMasterBean bean) {
		if (!checkDuplicateAdd(bean)) {

			Object addObj[][] = new Object[1][2];

			
			String query1 = "SELECT NVL(MAX(REGION_ID),0)+1 FROM  HRMS_D1_REGION";
			Object[][] regId = getSqlModel().getSingleResult(query1);
			
			addObj[0][0] = checkNull(String.valueOf(regId[0][0]));
			addObj[0][1] = bean.getRegionName().trim();

			bean.setRegionCode(String.valueOf(regId[0][0]));
			
			return getSqlModel().singleExecute(getQuery(1), addObj);

		} else {
			return false;

		}
	}

	/* for checking duplicate entry of record during insertion*/

	public boolean checkDuplicateAdd(RegionMasterBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_D1_REGION WHERE UPPER(REGION_NAME) LIKE '"
				+ bean.getRegionName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**  Modifing the record */
	public boolean update(RegionMasterBean bean) {
		if (!checkDuplicateMod(bean)) {
		
			Object addObj[][] = new Object[1][2];

			addObj[0][0] = bean.getRegionName().trim();
			addObj[0][1] = bean.getRegionCode().trim();

			return getSqlModel().singleExecute(getQuery(2), addObj);
		}
		else {
			return false;

		}
	}

	/* for checking duplicate entry of record during modification*/

	public boolean checkDuplicateMod(RegionMasterBean bean) {

		boolean result = false;
		String query = "SELECT * FROM HRMS_D1_REGION WHERE UPPER(REGION_NAME) LIKE '"
				+ bean.getRegionName().trim().toUpperCase()
				+ "' AND REGION_ID not in(" + bean.getRegionCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/** Generating the list Onload */
	public void initialData(RegionMasterBean regionBean,
			HttpServletRequest request) {

		Object[][] regData = getSqlModel().getSingleResult(getQuery(3));

		if (regData != null && regData.length > 0) {
			regionBean.setModeLength("true");

			regionBean.setTotalNoOfRecords(String.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(regionBean.getMyPage(),
					regData.length, 20);
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
				regionBean.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				RegionMasterBean bean = new RegionMasterBean();
				bean.setRegionCode(checkNull(String.valueOf(regData[i][0])));
				bean.setRegionName(checkNull(String.valueOf(regData[i][1])));

				List.add(bean);
			}// end of loop
			System.out.println("in main page---" + List.size());
			regionBean.setRegionList(List);
		}

		else {

			regionBean.setRegionList(null);

		}
	}

	public boolean deleteCheckedRecords(RegionMasterBean bean, String[] code) {
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

	public boolean delete(RegionMasterBean regionBean,
			HttpServletRequest request) {
		boolean result = false;
		System.out
				.println("venodrMasterBean.getVendorCode() in delete method call ======"
						+ regionBean.getRegionCode());
		String deleteId = regionBean.getRegionCode();

		String delQuery = "DELETE FROM HRMS_D1_REGION WHERE REGION_ID="
				+ deleteId;
		result = getSqlModel().singleExecute(delQuery);
		return result;
	}
	//Check Null Function//	

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	public void calforedit(RegionMasterBean regionBean) {

		try {
			System.out
					.println("regionBean.getRegionCode() here we get in calForEdit is-------------"
							+ regionBean.getRegionCode());
			String query = " SELECT REGION_NAME FROM HRMS_D1_REGION WHERE  REGION_ID= "+ regionBean.getRegionCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			regionBean.setRegionName(String.valueOf(data[0][0]));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
