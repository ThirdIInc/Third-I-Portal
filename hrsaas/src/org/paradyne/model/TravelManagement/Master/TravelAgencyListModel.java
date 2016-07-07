/*
 * Added by manish sakpal
 * 
 */

package org.paradyne.model.TravelManagement.Master;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.Master.TravelAgencyListBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import com.ibm.icu.impl.Utility;

public class TravelAgencyListModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelAgencyListModel.class);

	public void getAgencyList(TravelAgencyListBean bean,
			HttpServletRequest request) {
		Object[][] selData = null;
		ArrayList list = new ArrayList();
		try {
			String selQuery = " SELECT AGENCY_NAME, AGENCY_CONTACT_PERSON,AGENCY_CODE,LOCATION_NAME  FROM TMS_TRAVEL_AGENCY "
					+ "    INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=TMS_TRAVEL_AGENCY.AGENCY_CITY) "
					+ "   ORDER BY AGENCY_CODE ";

			System.out.println("=========== After Query ===========");
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
					TravelAgencyListBean beanItt = new TravelAgencyListBean();
					beanItt.setAgencyNameList(String.valueOf(selData[i][0]));
					beanItt.setContactPersonList(String.valueOf(selData[i][1]));
					beanItt.setAgencycode(String.valueOf(selData[i][2]));
					beanItt.setCity(String.valueOf(selData[i][3]));
					list.add(beanItt);
				}
			} catch (Exception e) {
				logger.error("exception in for loop dueData", e);
			}
			bean.setAgencyList(list);
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/* for inserting record */
	public boolean addData(TravelAgencyListBean bean) 
	{
		boolean result = false;
		
		String selQuery=" SELECT * FROM TMS_TRAVEL_AGENCY WHERE UPPER(AGENCY_NAME) LIKE '"
			+ bean.getAgencyName().trim().toUpperCase() + "'";
Object [][]selData=getSqlModel().getSingleResult(selQuery);
if(selData.length==0){

		Object addObj[][] = new Object[1][9];

		addObj[0][0] = bean.getAgencyName();
		addObj[0][1] = bean.getContactPerson();
		addObj[0][2] = bean.getAddress();
		addObj[0][3] = bean.getPhone1();
		addObj[0][4] = bean.getPhone2();
		addObj[0][5] = bean.getEmailId1();
		addObj[0][6] = bean.getEmailId2();
		addObj[0][7] = bean.getCityId();
		addObj[0][8] = bean.getTravelmodeCode().trim();

		result = getSqlModel().singleExecute(getQuery(1), addObj);

		if (result) {
			String autoCodeQuery = " SELECT NVL(MAX(AGENCY_CODE),0) FROM TMS_TRAVEL_AGENCY ";

			Object[][] data = getSqlModel().getSingleResult(autoCodeQuery);

			if (data != null && data.length > 0) {
				bean.setAgencycode(String.valueOf(data[0][0]));
			}
		}
}
		return result;

	} // end of if

	/**
	 * following function is called to delete a record
	 * 
	 * @param bean
	 * @return
	 */
	public boolean delRecord(TravelAgencyListBean bean) {
		Object del[][] = new Object[1][1];
		// to delete the single record after clicking on saving or searching
		// button
		del[0][0] = bean.getAgencycode();
		return getSqlModel().singleExecute(getQuery(3), del);
	}

	/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(TravelAgencyListBean bean) {
		boolean result = false;
		String query = "SELECT AGENCY_NAME, AGENCY_CONTACT_PERSON, AGENCY_ADDRESS, AGENCY_PHONE_1, " 
						+" AGENCY_PHONE_2, AGENCY_EMAIL_1, AGENCY_EMAIL_2,NVL(AGENCY_TRAVEL_MODE_AVAIL,' ' ),AGENCY_CITY, LOCATION_NAME FROM TMS_TRAVEL_AGENCY WHERE UPPER(AGENCY_NAME) LIKE '"
				+ bean.getAgencyName().trim().toUpperCase()
				+ "' AND AGENCY_CODE not in(" + bean.getAgencycode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for modifing the record */
	public boolean update(TravelAgencyListBean bean) 
	{
		boolean result=false;
		try
		{
			if (!checkDuplicateMod(bean)) 
			{
				Object addObj[][] = new Object[1][10];
				addObj[0][0] = bean.getAgencyName();
				addObj[0][1] = bean.getContactPerson();
				addObj[0][2] = bean.getAddress();
				addObj[0][3] = bean.getPhone1();
				addObj[0][4] = bean.getPhone2();
				addObj[0][5] = bean.getEmailId1();
				addObj[0][6] = bean.getEmailId2();
				addObj[0][7] = bean.getCityId();
				addObj[0][8] = bean.getTravelmodeCode().trim();
				addObj[0][9] = bean.getAgencycode();

				result = getSqlModel().singleExecute(getQuery(2), addObj);
				
			} // end of if
		else
			result = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception occurred======>"+e);
		}
		return result;
	}

	/*
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	public void calforedit(TravelAgencyListBean bean, String agencyID) {

		try {
			String query = " SELECT AGENCY_NAME, NVL(AGENCY_CONTACT_PERSON,''), NVL(AGENCY_ADDRESS,''), NVL(AGENCY_PHONE_1,''), NVL(AGENCY_PHONE_2,''), NVL(AGENCY_EMAIL_1,' '), NVL(AGENCY_EMAIL_2,' '), NVL(AGENCY_TRAVEL_MODE_AVAIL,' ' ), AGENCY_CITY,LOCATION_NAME  FROM TMS_TRAVEL_AGENCY INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=TMS_TRAVEL_AGENCY.AGENCY_CITY)  WHERE  AGENCY_CODE = "
					+ agencyID + " ORDER BY AGENCY_CODE ";
			Object[][] data = getSqlModel().getSingleResult(query);
			bean.setAgencyName(String.valueOf(data[0][0]));
			bean.setContactPerson(String.valueOf(data[0][1]));
			bean.setAddress(String.valueOf(data[0][2]));
			bean.setPhone1(org.paradyne.lib.Utility.checkNull(String.valueOf(data[0][3])));	
			bean.setPhone2(org.paradyne.lib.Utility.checkNull(String.valueOf(data[0][4])));	
			bean.setEmailId1(org.paradyne.lib.Utility.checkNull(String.valueOf(data[0][5])));	
			bean.setEmailId2(org.paradyne.lib.Utility.checkNull(String.valueOf(data[0][6])));	
			bean.setCity(String.valueOf(data[0][9]));
			bean.setCityId(String.valueOf(data[0][8]));
			
			bean.setAgencycode(agencyID);

			String str = "";

			if (data != null && data.length > 0) {
				String journeyNameQuery = " SELECT NVL(JOURNEY_NAME,' ') FROM HRMS_TMS_JOURNEY_MODE  WHERE JOURNEY_ID IN("
						+ String.valueOf(data[0][7]) + ")";

				Object journeyNameQueryObj[][] = getSqlModel().getSingleResult(
						journeyNameQuery);
				if (journeyNameQueryObj != null
						&& journeyNameQueryObj.length > 0) {
					Object[][] journeyNameDataObj = new Object[journeyNameQueryObj.length][1];
					for (int i = 0; i < journeyNameDataObj.length; i++) {
						bean.setTravelmodeCode(String.valueOf(data[0][7]));
						journeyNameDataObj[i][0] = String
								.valueOf(journeyNameQueryObj[i][0]);
					}

					if (journeyNameDataObj != null
							&& journeyNameDataObj.length > 0) {
						for (int j = 0; j < journeyNameDataObj.length; j++) {
							str += String.valueOf(j + 1) + ".";
							str += String.valueOf(journeyNameDataObj[j][0])
									+ "\n ";
							bean.setTravelmode(str);
						}

					}

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean deleteCheckedRecords(TravelAgencyListBean bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(3), delete);

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

	public void getRecord(TravelAgencyListBean bean) {
		try {
			System.out
					.println("Hidden code =========> " + bean.getAgencycode());
			String query = "SELECT AGENCY_NAME,AGENCY_CONTACT_PERSON,AGENCY_ADDRESS,AGENCY_PHONE_1,AGENCY_PHONE_2,AGENCY_EMAIL_1,AGENCY_EMAIL_2,HRMS_LOCATION.LOCATION_NAME ,AGENCY_CITY "
					+ " ,NVL(AGENCY_TRAVEL_MODE_AVAIL,' ') FROM  TMS_TRAVEL_AGENCY "
					+ " INNER JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=TMS_TRAVEL_AGENCY.AGENCY_CITY)"
					+ " WHERE AGENCY_CODE="
					+ bean.getAgencycode()
					+ " ORDER BY AGENCY_CODE ";
			Object[][] data = getSqlModel().getSingleResult(query);
			bean.setAgencyName(String.valueOf(data[0][0]));
			bean.setContactPerson(String.valueOf(data[0][1]));
			bean.setAddress(String.valueOf(data[0][2]));
			bean.setPhone1(org.paradyne.lib.Utility.checkNull(String.valueOf(data[0][3])));	
			bean.setPhone2(org.paradyne.lib.Utility.checkNull(String.valueOf(data[0][4])));	
			bean.setEmailId1(org.paradyne.lib.Utility.checkNull(String.valueOf(data[0][5])));	
			bean.setEmailId2(org.paradyne.lib.Utility.checkNull(String.valueOf(data[0][6])));	
			bean.setCity(String.valueOf(data[0][7]));
			bean.setCityId(String.valueOf(data[0][8]));
			
			String str ="";
			
			if (data != null && data.length > 0) {
				String journeyNameQuery = " SELECT NVL(JOURNEY_NAME,' ') FROM HRMS_TMS_JOURNEY_MODE  WHERE JOURNEY_ID IN("
						+ String.valueOf(data[0][9]) + ")";

				Object journeyNameQueryObj[][] = getSqlModel().getSingleResult(
						journeyNameQuery);
				if (journeyNameQueryObj != null
						&& journeyNameQueryObj.length > 0) {
					Object[][] journeyNameDataObj = new Object[journeyNameQueryObj.length][1];
					for (int i = 0; i < journeyNameDataObj.length; i++) {
						bean.setTravelmodeCode(String.valueOf(data[0][9]));
						journeyNameDataObj[i][0] = String
								.valueOf(journeyNameQueryObj[i][0]);
					}

					if (journeyNameDataObj != null
							&& journeyNameDataObj.length > 0) {
						for (int j = 0; j < journeyNameDataObj.length; j++) {
							str += String.valueOf(j + 1) + ".";
							str += String.valueOf(journeyNameDataObj[j][0])
									+ "\n ";
							bean.setTravelmode(str);
						}

					}

				}

			}
			
			

		} catch (Exception e) {
			System.out.println("Exception in getRecord -- " + e);
		}

	}

}
