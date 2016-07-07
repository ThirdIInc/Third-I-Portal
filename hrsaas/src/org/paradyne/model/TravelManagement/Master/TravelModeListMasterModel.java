package org.paradyne.model.TravelManagement.Master;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.Master.TravelModeListMasterBean;
import org.paradyne.lib.ModelBase;

public class TravelModeListMasterModel extends ModelBase {

	public void getList(TravelModeListMasterBean bean,
			HttpServletRequest request) {
		Object[][] selData = null;

		try {
			String selQuery = "SELECT TRAVEL_CARRIER_ID,TRAVEL_CARRIER_NAME,JOURNEY_NAME FROM TMS_CARRIER "
				+ " LEFT JOIN  HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=TMS_CARRIER.TRAVEL_MODE_ID) "
				+ " ORDER BY TRAVEL_CARRIER_ID  ";
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

				TravelModeListMasterBean subBean = new TravelModeListMasterBean();

				subBean.setIttModeCode(String.valueOf(selData[i][0]));
				subBean.setIttAirlineBusTrain(String.valueOf(selData[i][1]));
				subBean.setIttTravelModeName(String.valueOf(selData[i][2]));
				subBean.setIttSrN0(String.valueOf(i + 1));
				list.add(subBean);
			}

		}
		bean.setTravelModeMasterItt(list);

	}

	public boolean save(TravelModeListMasterBean bean) {
		boolean result = false;

		String journeyId = bean.getJourneyId();
		String airlinesBusTrain = bean.getAirlineBusTrain();

		Object[][] insData = new Object[1][2];
		insData[0][0] = journeyId;
		insData[0][1] = airlinesBusTrain;
		System.out.println(".................." + journeyId);
		System.out.println(".................." + airlinesBusTrain);
		String insQuery = "INSERT INTO TMS_CARRIER (TRAVEL_CARRIER_ID, TRAVEL_MODE_ID, TRAVEL_CARRIER_NAME) VALUES((SELECT NVL(MAX(TRAVEL_CARRIER_ID),0)+1 FROM  TMS_CARRIER) ,?,?)";
		result = getSqlModel().singleExecute(insQuery, insData);

		String Query = "SELECT MAX(TRAVEL_CARRIER_ID) FROM  TMS_CARRIER";
		Object[][] data = getSqlModel().getSingleResult(Query);
		bean.setModeId(String.valueOf(data[0][0]));
		return result;
	}

	public void dblClickItt(TravelModeListMasterBean bean) {

		getDetails(bean);
	}

	public boolean deleteCheck(TravelModeListMasterBean bean, String[] code,
			String[] modeId, HttpServletRequest request) {

		boolean flag = false;
		for (int j = 0; j < code.length; j++) {
			if (code[j].equals("Y")) {

				String delQuery = "DELETE FROM TMS_CARRIER WHERE TRAVEL_CARRIER_ID="
						+ modeId[j] + "";
				flag = getSqlModel().singleExecute(delQuery);

			}

		}
		getList(bean, request);

		return flag;

	}

	public void getDetails(TravelModeListMasterBean bean) {

		String modeId = bean.getModeId();
		String selQuery = "SELECT TRAVEL_CARRIER_ID,TRAVEL_MODE_ID,JOURNEY_NAME,TRAVEL_CARRIER_NAME FROM TMS_CARRIER LEFT JOIN  HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=TMS_CARRIER.TRAVEL_MODE_ID)  WHERE TRAVEL_CARRIER_ID= "
				+ modeId;

		Object[][] data = getSqlModel().getSingleResult(selQuery);

		bean.setModeId(String.valueOf(data[0][0]));
		bean.setJourneyId(String.valueOf(data[0][1]));
		bean.setTravelModeName(String.valueOf(data[0][2]));
		bean.setAirlineBusTrain(String.valueOf(data[0][3]));

	}

	public boolean delete(TravelModeListMasterBean bean,
			HttpServletRequest request) {
		boolean result = false;
		String modeId = bean.getModeId();

		String delQuery = "DELETE FROM TMS_CARRIER WHERE TRAVEL_CARRIER_ID="
				+ modeId;
		result = getSqlModel().singleExecute(delQuery);
		getList(bean, request);

		return result;
	}

	public boolean update(TravelModeListMasterBean bean) {
		boolean result;
		String journeyId = bean.getJourneyId();
		String airlinesBusTrain = bean.getAirlineBusTrain();
		String modeId = bean.getModeId();

		Object[][] upData = new Object[1][2];

		upData[0][1] = airlinesBusTrain;
		upData[0][0] = journeyId;

		System.out.println("++++++++++++++++++++" + airlinesBusTrain);
		System.out.println("++++++++++++++++++++" + journeyId);

		String upQuery = "UPDATE TMS_CARRIER SET  TRAVEL_MODE_ID=?,TRAVEL_CARRIER_NAME=? WHERE TRAVEL_CARRIER_ID= "
				+ modeId;

		result = getSqlModel().singleExecute(upQuery, upData);

		return result;
	}

}
