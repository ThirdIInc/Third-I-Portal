package org.paradyne.model.TravelManagement.Master;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.InterviewAssessMaster;
import org.paradyne.bean.TravelManagement.Master.HotelMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class HotelMasterModel extends ModelBase {

	/* for inserting record */
	public boolean addData(HotelMaster hotelMaster, String[] rmtype,
			String[] actrate, String[] corprate, String[] isbreakFast) {

		boolean result = false;
		try {
			Object addObj[][] = new Object[1][13];
			addObj[0][0] = hotelMaster.getHotelName();
			addObj[0][1] = hotelMaster.getTypeId();
			addObj[0][2] = hotelMaster.getContactPerson();
			addObj[0][3] = hotelMaster.getAddress();
			addObj[0][4] = hotelMaster.getCity().trim();
			addObj[0][5] = hotelMaster.getPhone1();
			addObj[0][6] = hotelMaster.getPhone2();
			addObj[0][7] = hotelMaster.getEmailId();
			addObj[0][8] = hotelMaster.getHotelMasterRemark();
			addObj[0][9] = hotelMaster.getDistanceFromAirport();
			addObj[0][10] = hotelMaster.getZoneName();
			
			if (hotelMaster.getDinnerPackage().equals("true")) {
				addObj[0][11] = "Y";
			}
			else
			{
				addObj[0][11] = "N";
			}
			addObj[0][12] = hotelMaster.getDinnerPackageCost();
			
			result = getSqlModel().singleExecute(getQuery(1), addObj);
			if (result) {
				String autoCodeQuery = " SELECT NVL(MAX(HOTEL_ID),0) FROM HRMS_TRAVEL_HOTEL_MASTER ";

				Object[][] data = getSqlModel().getSingleResult(autoCodeQuery);

				if (data != null && data.length > 0) {
					hotelMaster.setHiddencode(String.valueOf(data[0][0]));
				}
			}
			String query = " INSERT INTO HRMS_TRAVEL_HOTEL_MASTER_DTL(HOTEL_ID,ROOM_TYPE,ROOM_ACTUAL_RATE,ROOM_CORPORATE_RATE,ROOM_ISBREAKFAST)"
					+ " VALUES(?,?,?,?,?) ";
			if (result) {
				Object saveDtlObj[][] = null;
				if (rmtype != null && rmtype.length > 0) {
					saveDtlObj = new Object[rmtype.length][5];
					for (int i = 0; i < rmtype.length; i++) {
						saveDtlObj[i][0] = hotelMaster.getHiddencode();
						saveDtlObj[i][1] = rmtype[i];
						saveDtlObj[i][2] = actrate[i];
						saveDtlObj[i][3] = corprate[i];
						saveDtlObj[i][4] = isbreakFast[i];
					}
				}

				result = getSqlModel().singleExecute(query, saveDtlObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}// end of if

	/**
	 * following function is called to delete a record
	 * 
	 * @param bean
	 * @return
	 */
	public boolean delRecord(HotelMaster hotelMaster) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = hotelMaster.getHiddencode();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getSqlModel().singleExecute(getQuery(4), del);
	}

	/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(HotelMaster hotelMaster) {
		boolean result = false;
		try {
			String query = "SELECT * FROM HRMS_TRAVEL_HOTEL_MASTER WHERE UPPER(HOTEL_NAME) LIKE '"
					+ hotelMaster.getHotelName().trim().toUpperCase()
					+ "' AND HOTEL_ID not in(" + hotelMaster.getHotelId() + ")";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	/* for modifing the record */
	public boolean update(HotelMaster hotelMaster, String[] rmtype,
			String[] actrate, String[] corprate, String[] isbreakFast) {

		boolean result = false;
		if (!checkDuplicateMod(hotelMaster)) {
			Object addObj[][] = new Object[1][14];
			addObj[0][0] = hotelMaster.getHotelName();
			addObj[0][1] = hotelMaster.getTypeId();
			addObj[0][2] = hotelMaster.getContactPerson();
			addObj[0][3] = hotelMaster.getAddress();
			addObj[0][4] = hotelMaster.getCity().trim();
			addObj[0][5] = hotelMaster.getPhone1();
			addObj[0][6] = hotelMaster.getPhone2();
			addObj[0][7] = hotelMaster.getEmailId();
			addObj[0][8] = hotelMaster.getHotelMasterRemark();
			addObj[0][9] = hotelMaster.getDistanceFromAirport();
			addObj[0][10] = hotelMaster.getZoneName();
			if(hotelMaster.getDinnerPackage().equals("true"))
			{
				addObj[0][11] = "Y";
			}
			else
			{
				addObj[0][11] = "N";
			}
			
			addObj[0][12] = hotelMaster.getDinnerPackageCost();
			addObj[0][13] = hotelMaster.getHiddencode();

			result = getSqlModel().singleExecute(getQuery(3), addObj);

			if (result) {
				Object saveDtlObj[][] = null;
				if (rmtype != null && rmtype.length > 0) {

					String deleteQuery = " DELETE FROM HRMS_TRAVEL_HOTEL_MASTER_DTL WHERE HOTEL_ID= "
							+ hotelMaster.getHiddencode();

					String query = " INSERT INTO HRMS_TRAVEL_HOTEL_MASTER_DTL(HOTEL_ID,ROOM_TYPE,ROOM_ACTUAL_RATE,ROOM_CORPORATE_RATE,ROOM_ISBREAKFAST)"
							+ " VALUES(?,?,?,?,?) ";

					result = getSqlModel().singleExecute(deleteQuery);
					saveDtlObj = new Object[rmtype.length][5];
					for (int i = 0; i < rmtype.length; i++) {
						saveDtlObj[i][0] = hotelMaster.getHiddencode();
						saveDtlObj[i][1] = rmtype[i];
						saveDtlObj[i][2] = actrate[i];
						saveDtlObj[i][3] = corprate[i];
						saveDtlObj[i][4] = isbreakFast[i];
					}
					result = getSqlModel().singleExecute(query, saveDtlObj);
				}
			}

		}// end of if
		else {
			return result;
		}

		return result;
	}

	/*
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	public void calforedit(HotelMaster hotelMaster) {

		try {
			String query = " SELECT HOTEL_NAME,HOTEL_TYPE,HOTEL_TYPE_NAME ,HOTEL_CONTACT_PERSON,HOTEL_ADDRESS,HOTEL_CITY,HOTEL_CITY,"
					+ " HOTEL_PHONE1, HOTEL_PHONE2, HOTEL_EMAIL ,HOTEL_REMARK,NVL(HOTEL_DIST_FROM_AIRPORT,0),NVL(HOTEL_AVERAGE_RATING,0), "
					+" HOTEL_ZONE, HOTEL_DINNER_PACKAGE, HOTEL_DINNER_PACKAGE_COST FROM HRMS_TRAVEL_HOTEL_MASTER "
					//+ " INNER JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_TRAVEL_HOTEL_MASTER.HOTEL_CITY) "
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TRAVEL_HOTEL_MASTER.HOTEL_TYPE) WHERE  HOTEL_ID= "
					+ hotelMaster.getHiddencode();
			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				hotelMaster.setHotelName(String.valueOf(data[0][0]));
				hotelMaster.setTypeId(String.valueOf(data[0][1]));
				hotelMaster.setHotelType(checkNull(String.valueOf(data[0][2])));
				hotelMaster.setContactPerson(checkNull(String
						.valueOf(data[0][3])));
				hotelMaster.setAddress(checkNull(String.valueOf(data[0][4])));
				hotelMaster.setCityId(checkNull(String.valueOf(data[0][5])));
				hotelMaster.setCity(checkNull(String.valueOf(data[0][6])));
				hotelMaster.setPhone1(checkNull(String.valueOf(data[0][7])));
				hotelMaster.setPhone2(checkNull(String.valueOf(data[0][8])));
				hotelMaster.setEmailId(checkNull(String.valueOf(data[0][9])));
				hotelMaster.setHotelMasterRemark(checkNull(String
						.valueOf(data[0][10])));
				hotelMaster.setDistanceFromAirport(checkNull(String.valueOf(data[0][11])));
				hotelMaster.setAverageRating(checkNull(String.valueOf(data[0][12])));
				hotelMaster.setZoneName(checkNull(String.valueOf(data[0][13])));
				if(checkNull(String.valueOf(data[0][14])).equals("Y"))
				{
					hotelMaster.setDinnerPackage("true");
				}
				else
				{
					hotelMaster.setDinnerPackage("false");
				}
				
				hotelMaster.setDinnerPackageCost(checkNull(String.valueOf(data[0][15])));
			}

		} catch (Exception e) {
			e.printStackTrace();
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

	/* generating the list onload */
	public void intData(HotelMaster hotelMaster, HttpServletRequest request) {

		Object[][] repData = getSqlModel().getSingleResult(getQuery(2));

		if (repData != null && repData.length > 0) {
			hotelMaster.setModeLength("true");

			hotelMaster.setTotalNoOfRecords(String.valueOf(repData.length));

			String[] pageIndex = Utility.doPaging(hotelMaster.getMyPage(),
					repData.length, 20);
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
				hotelMaster.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				HotelMaster bean = new HotelMaster();
				bean.setHotelName(checkNull(String.valueOf(repData[i][0])));
				bean.setAddress(checkNull(String.valueOf(repData[i][1])));
				bean.setHotelId(checkNull(String.valueOf(repData[i][2])));

				List.add(bean);
			}// end of loop

			hotelMaster.setHoteldataList(List);
		}

		else {

			// message shown
			hotelMaster.setHoteldataList(null);

		}
	}

	public boolean deleteCheckedRecords(HotelMaster hotelMaster, String[] code) {
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

	public void getRecord(HotelMaster hotelMaster) {
		try {
			/*Change the query -Nilesh*/
			String query = " select HOTEL_NAME,HOTEL_TYPE,HOTEL_TYPE_NAME ,HOTEL_CONTACT_PERSON,HOTEL_ADDRESS,HOTEL_CITY,HOTEL_CITY,"
					+ " HOTEL_PHONE1, HOTEL_PHONE2, HOTEL_EMAIL ,HOTEL_REMARK,NVL(HOTEL_DIST_FROM_AIRPORT,0),NVL(HOTEL_AVERAGE_RATING,0), "
					+" HOTEL_ZONE, HOTEL_DINNER_PACKAGE, HOTEL_DINNER_PACKAGE_COST FROM HRMS_TRAVEL_HOTEL_MASTER "
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE on(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TRAVEL_HOTEL_MASTER.HOTEL_TYPE) WHERE  HOTEL_ID= "
					+ hotelMaster.getHiddencode() + " ORDER BY HOTEL_ID ";

			Object[][] data = getSqlModel().getSingleResult(query);

			hotelMaster.setHotelName(checkNull(String.valueOf(data[0][0])));
			hotelMaster.setTypeId(checkNull(String.valueOf(data[0][1])));
			hotelMaster.setHotelType(checkNull(String.valueOf(data[0][2])));
			hotelMaster.setContactPerson(checkNull(String.valueOf(data[0][3])));
			hotelMaster.setAddress(checkNull(String.valueOf(data[0][4])));
			hotelMaster.setCityId(checkNull(String.valueOf(data[0][5])));
			hotelMaster.setCity(checkNull(String.valueOf(data[0][6])));
			hotelMaster.setPhone1(checkNull(String.valueOf(data[0][7])));
			hotelMaster.setPhone2(checkNull(String.valueOf(data[0][8])));
			hotelMaster.setEmailId(checkNull(String.valueOf(data[0][9])));
			hotelMaster.setHotelMasterRemark(checkNull(String.valueOf(data[0][10])));
			hotelMaster.setDistanceFromAirport(checkNull(String.valueOf(data[0][11])));
			hotelMaster.setAverageRating(checkNull(String.valueOf(data[0][12])));
			hotelMaster.setZoneName(checkNull(String.valueOf(data[0][13])));
			if(checkNull(String.valueOf(data[0][14])).equals("Y"))
			{
				hotelMaster.setDinnerPackage("true");
			}
			else
			{
				hotelMaster.setDinnerPackage("false");
			}
			
			hotelMaster.setDinnerPackageCost(checkNull(String.valueOf(data[0][15])));

		} catch (Exception e) {
			System.out.println("Exception in getRecord -- " + e);
		}

	}

	public void getRoom(HotelMaster hotelMaster) {
		try {

			String query = " SELECT NVL(UPPER(ROOM_TYPE_NAME),' '),ROOM_TYPE_ID,ROOM_LEVEL FROM HRMS_TMS_ROOM_TYPE "
					+ "  INNER JOIN HRMS_TMS_HOTEL_TYPE  ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE)"
					+ "  WHERE ROOM_HOTEL_TYPE="
					+ hotelMaster.getTypeId()
					+ " AND ROOM_TYPE_STATUS='A' "
					+ " ORDER BY HOTEL_LEVEL,ROOM_LEVEL ";

			ArrayList<Object> roomList = new ArrayList<Object>();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					HotelMaster hotelMasterLocalBean = new HotelMaster();
					hotelMasterLocalBean.setRoomtypeName(String
							.valueOf(data[i][0]));
					hotelMasterLocalBean.setRoomtypeId(String
							.valueOf(data[i][1]));
					roomList.add(hotelMasterLocalBean);
				}

				hotelMaster.setRoomList(roomList);
			} else {
				hotelMaster.setRoomList(null);
			}

		} catch (Exception e) {
			System.out.println("Exception in getRecord -- " + e);
		}

	}

	public void setRoomtypeRecord(HotelMaster hotelMaster) {
		try {
			// TODO Auto-generated method stub
			System.out.println("hello roomlist here----"
					+ hotelMaster.getHiddencode());

			String query = " SELECT * FROM HRMS_TRAVEL_HOTEL_MASTER_DTL "
					+ " WHERE HOTEL_ID=" + hotelMaster.getHiddencode();

			Object[][] resultObj = getSqlModel().getSingleResult(query);

			String roomTypeQuery = "";

			if (resultObj != null && resultObj.length == 0) {

				roomTypeQuery = " SELECT DISTINCT ROOM_TYPE_ID,NVL(ROOM_TYPE_NAME,''),0,0,'N' "
						+ "  FROM HRMS_TMS_ROOM_TYPE  "
						+ "  WHERE 	ROOM_HOTEL_TYPE=(SELECT HOTEL_TYPE FROM HRMS_TRAVEL_HOTEL_MASTER WHERE HOTEL_ID="
						+ hotelMaster.getHiddencode() + ") ";

			} else {

				roomTypeQuery = " SELECT ROOM_TYPE,NVL(ROOM_TYPE_NAME,''),NVL(ROOM_ACTUAL_RATE,0),NVL(ROOM_CORPORATE_RATE,0),ROOM_ISBREAKFAST "
						+ " FROM HRMS_TMS_ROOM_TYPE LEFT JOIN HRMS_TRAVEL_HOTEL_MASTER_DTL"
						+ " ON (HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID=HRMS_TRAVEL_HOTEL_MASTER_DTL.ROOM_TYPE) "
						+ " WHERE HOTEL_ID=" + hotelMaster.getHiddencode();
			}

			Object[][] roomTypeQueryObj = getSqlModel().getSingleResult(
					roomTypeQuery);
			ArrayList<Object> roomList = new ArrayList<Object>();
			if (roomTypeQueryObj != null && roomTypeQueryObj.length > 0) {
				for (int i = 0; i < roomTypeQueryObj.length; i++) {
					HotelMaster hotelMasterLocalBean = new HotelMaster();
					hotelMasterLocalBean.setRoomtypeId(String
							.valueOf(roomTypeQueryObj[i][0]));
					hotelMasterLocalBean.setRoomtypeName(String
							.valueOf(roomTypeQueryObj[i][1]));
					hotelMasterLocalBean.setActualRate(String
							.valueOf(roomTypeQueryObj[i][2]));
					hotelMasterLocalBean.setCorporateRate(String
							.valueOf(roomTypeQueryObj[i][3]));
					hotelMasterLocalBean.setIsbreakFast(String
							.valueOf(roomTypeQueryObj[i][4]));
					roomList.add(hotelMasterLocalBean);

				}
				hotelMaster.setRoomList(roomList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
