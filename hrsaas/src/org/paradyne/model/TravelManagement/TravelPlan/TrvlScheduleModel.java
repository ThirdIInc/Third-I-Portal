package org.paradyne.model.TravelManagement.TravelPlan;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;
import org.paradyne.bean.TravelManagement.TravelPlan.TravelApplication;
import org.paradyne.bean.TravelManagement.TravelPlan.TrvlSchedule;
import org.paradyne.lib.ModelBase;

/**
 * 
 * @author krishna
 * 
 */
public class TrvlScheduleModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TrvlScheduleModel.class);

	public void callStatus(TrvlSchedule bean, String status,
			HttpServletRequest request) {
		bean.setPendingFlag("false");
		// check the status here
		String chkSql = "SELECT MANG_AUTH_BRANCH FROM HRMS_TMS_MANG_AUTH WHERE MANG_AUTH_SCHEDULER ="
				+ bean.getUserEmpId()
				+ " AND  MANG_AUTH_DEFAULT_FLAG ='Y'";
		Object[][] chkData = getSqlModel().getSingleResult(chkSql);
		
		String alterSchSql ="";
		Object[][] alterData = getSqlModel().getSingleResult(alterSchSql);

		bean.setStat(status);
		String appStatus = bean.getStat();
		String query = "";

		// check status here

		query = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPNAME,TRAVEL_APP_REQUEST, "
				+ "TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY'), LOCATION_TYPE_NAME,"
				+ " DECODE(TRAVEL_APP_TRAVEL_ARRG,'S','NO','C','YES'), "
				+ " DECODE(TRAVEL_APP_ACCOMMODATION,'S','NO','C','YES') ,"
				+ " CASE WHEN TRAVEL_APP_ADVANCE >0 THEN 'YES' ELSE 'NO' END AS ADVANCE ,"
				+ " TRAVEL_APP_EMPID,TRAVEL_APP_ID ,"  
				+" CASE WHEN SCH_DTL_SCH_CHKFLAG ='Y' OR SCH_DTL_SCH_CHKFLAG IS NULL  THEN 'true' ELSE 'false' END "
				+" FROM HRMS_TMS_TRAVEL_APP"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID )"
				+ " LEFT JOIN HRMS_TMS_LOCATION_TYPE  "
				+ " ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID= HRMS_TMS_TRAVEL_APP.TRAVEL_APP_LOCATION_TYPE)"
				+ " LEFT JOIN HRMS_TMS_SCH_DTL ON(HRMS_TMS_SCH_DTL.SCH_DTL_TRAVEL_APP_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID)";

		if (appStatus.equals("P")) {
			bean.setPendingFlag("true");
			logger.info("PENDING CASE---------------");
			query += "WHERE TRAVEL_APP_STATUS ='A' AND (SCH_DTL_SCH_STATUS ='U' OR SCH_DTL_SCH_STATUS IS NULL) ";
		}

		else if (appStatus.equals("ST"))// Schedule Travel List
		{

			query += "WHERE SCH_DTL_SCH_STATUS ='S'  AND (SCH_APPR_STATUS !='A' OR SCH_APPR_STATUS IS NULL) AND (SCH_APPR_STATUS !='R' OR SCH_APPR_STATUS IS NULL)";
		} else if (appStatus.equals("SA"))// scheduled approved
		{
			query += "WHERE SCH_DTL_SCH_STATUS ='S'  AND SCH_APPR_STATUS='A'";
		}

		else if (appStatus.equals("SR"))// Scheduled Rejected
		{
			query += "WHERE TRAVEL_APP_STATUS ='A' AND SCH_APPR_STATUS ='R'";
      //set the flag for  updating status while rescheduling
			bean.setChkRejFlg(true);
		}

		else if (appStatus.equals("SC"))// schedule for cancel
		{

			query += "WHERE TRAVEL_APP_STATUS ='A' AND TRAVEL_CANCEL_STATUS ='A' AND (SCH_DTL_CANCEL_STATUS !='C' OR SCH_DTL_CANCEL_STATUS IS NULL OR SCH_DTL_CANCEL_STATUS ='P') ";

		}

		else if (appStatus.equals("SCD"))// schedule for cancel
		{

			query += "WHERE TRAVEL_APP_STATUS ='A' AND SCH_DTL_CANCEL_STATUS ='C'";

		}

		boolean chkFlag = callRequistionStatus(bean);
		System.out.println("chkFlag ============= " + chkFlag);
		if (chkFlag == false) {
			query += "AND 1=2";
		}
		if (chkData != null && chkData.length > 0) {
		} else {
			/*query += " AND HRMS_EMP_OFFC.EMP_CENTER IN(SELECT MANG_AUTH_BRANCH FROM HRMS_TMS_MANG_AUTH WHERE MANG_AUTH_SCHEDULER ="
					+ bean.getUserEmpId() + ") ";*/
			query += " AND ((HRMS_EMP_OFFC.EMP_CENTER IN(SELECT MANG_AUTH_BRANCH FROM HRMS_TMS_MANG_AUTH WHERE MANG_AUTH_SCHEDULER ="+bean.getUserEmpId()+")" 
					+"  AND (SCH_DTL_SCH_CHKFLAG ='Y' OR SCH_DTL_SCH_CHKFLAG IS NULL))  OR "
					+"( SCH_DTL_ALTER_SCHEDULER ="+bean.getUserEmpId()+" AND SCH_DTL_SCH_CHKFLAG ='N' ) )"; 
		}

		query += "  ORDER BY TRAVEL_APP_APPDATE DESC";

		Object[][] result = getSqlModel().getSingleResult(query);
		doPaging(bean, result.length, new Object[][] {}, request);
		int fromTotRec = Integer.parseInt(bean.getFromTotRec());
		int toTotRec = Integer.parseInt(bean.getToTotRec());

		ArrayList travelList = new ArrayList();

		for (int i = fromTotRec; i < toTotRec; i++) {
			TrvlSchedule bean1 = new TrvlSchedule();
			bean1.setEmpName(checkNull(String.valueOf(result[i][0])));
			bean1.setTrvlReqName(checkNull(String.valueOf(result[i][1])));
			bean1.setTrvlDate(checkNull(String.valueOf(result[i][2])));
			bean1.setLocType(checkNull(String.valueOf(result[i][3])));
			bean1.setTrvlArgmt(checkNull(String.valueOf(result[i][4])));
			bean1.setTrvlAccom(checkNull(String.valueOf(result[i][5])));
			bean1.setTrvlAdv(checkNull(String.valueOf(result[i][6])));
			// bean1.setEmployeeId(checkNull(String.valueOf(result[i][7])));
			bean1.setTravelAppId(checkNull(String.valueOf(result[i][8])));
			bean1.setForwrdFlag(checkNull(String.valueOf(result[i][9])));
			travelList.add(bean1);
			if (status.equals("P")) {
				bean1.setPenFlag(true);
			} else if (status.equals("ST")) {
				bean1.setTrvlFlag(true);

			} else if (status.equals("SA")) {
				bean1.setApprvdFlag(true);
			} else if (status.equals("SR")) {
				bean1.setRegctedFlag(true);
			} else if (status.equals("SC")) {
				bean1.setCancelFlag(true);
			} else if (status.equals("SCD")) {
				bean1.setCanceledFlag(true);
			} else {
				bean1.setRegctedFlag(true);
			}

		} // end of for loop

		bean.setTrvlSchList(travelList);

		logger.info("ITERATOR SIZE________________" + travelList.size());
		logger.info("no data flag========" + bean.isNoData());

		if (travelList.size() == 0) { // bean.setNoData("true");
			bean.setNoData(true);
			logger.info("no data flag========" + bean.isNoData());
		} else {
			bean.setNoData(false);
		}

	} // end of callStatus

	public void doPaging(TrvlSchedule bean, int empLength, Object[][] attnObj,
			HttpServletRequest request) {
		try {
			/*
			 * totalRec -: No. of records per page fromTotRec -: Starting No. of
			 * record to show on a current page toTotRec -: Last No. of record
			 * to show on a current page pageNo -: Current page to show
			 * totalPage -: Total No. of Pages
			 */

			String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM HRMS_SALARY_CONF ";
			Object[][] pagingObj = getSqlModel().getSingleResult(pagingSql);
			int totalRec = Integer.parseInt(String.valueOf(pagingObj[0][0]));

			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int searchCount = 0;
			int totalPage = 0;
			String empExists = "false";

			java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(
					(double) empLength / totalRec);
			totalPage = Integer.parseInt(bigDecRow1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			if (String.valueOf(bean.getHdPage()).equals("null")
					|| String.valueOf(bean.getHdPage()).equals(null)
					|| String.valueOf(bean.getHdPage()).equals("")) {
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if (toTotRec > empLength) {
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			} else {
				pageNo = Integer.parseInt(bean.getHdPage());

				if (pageNo == 1) {
					fromTotRec = 0;
					toTotRec = totalRec;
				} else {
					toTotRec = toTotRec * pageNo;
					fromTotRec = toTotRec - totalRec;
				}
				if (toTotRec > empLength) {
					toTotRec = empLength;
				}

			}

			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));

			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("fromTotRec", fromTotRec);
			request.setAttribute("toTotRec", toTotRec);
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
		}// end of else1
	}

	public void callDtl(TrvlSchedule bean, String trvlAppId,
			HttpServletRequest request) {

		// get the data from data base for that expId

		String appStatus = bean.getStat();
		//call method to set the valid travel mode,Hotel and Room type variables

		setTrvlModeHotelRoom(bean);

		if (appStatus.equals("P") || appStatus.equals("SR")) {

			setEmpDtls(bean, request);
			// check the status whether it is u or not

			if (checkStatus(bean)) {
				// in case of not finalized case -----
				logger.info("already Saved record");
				setTrvlLocalSchDtls(bean);
				setTotBookingDtls(bean, request);
				setTotLocalConvDtls(bean, request);
				setTotLodgeDtls(bean, request);

			} else {
				setBookingDtls(bean, request);
				setLocalConvDtls(bean, request);
				setLodgeDtls(bean, request);

			}

		}// pending over

		else if (appStatus.equals("ST")) {// for view purpose

			setEmpDtls(bean, request);
			setTrvlLocalSchDtls(bean);
			setSchTrvlBookingDtls(bean, request);
			setSchTrvlLocalConvDtls(bean, request);
			setSchTrvlLodgeDtls(bean, request);

		}

		else if (appStatus.equals("SA") || appStatus.equals("SC")) {
			
			setEmpDtls(bean, request);
			if (appStatus.equals("SC")) {
				setCancelCmts(bean);
			}

			setTrvlLocalSchDtls(bean);
			setSchTrvlBookingDtls(bean, request);
			setSchTrvlLocalConvDtls(bean, request);
			setSchTrvlLodgeDtls(bean, request);
		} else if (appStatus.equals("SCD")) {// for view purpose

			setEmpDtls(bean, request);

			setCancelEtcAmounts(bean);
			setTrvlLocalSchDtls(bean);
			setSchTrvlBookingDtls(bean, request);
			setSchTrvlLocalConvDtls(bean, request);
			setSchTrvlLodgeDtls(bean, request);

		}

	}

	private void setTrvlModeHotelRoom(TrvlSchedule bean) {

		String PolSql = "SELECT TRAVEL_APP_POLICY_ID  FROM HRMS_TMS_TRAVEL_APP WHERE TRAVEL_APP_ID ="
				+ bean.getTravelAppId();
		Object[][] polData = getSqlModel().getSingleResult(PolSql);

		if (polData.length > 0 && polData != null) {

			// for travel Mode			
			String trModeSql = "  SELECT PTM_TRAVEL_MODE_ID FROM HRMS_TMS_POLICY_TRAVEL_MODE "
					+ "	LEFT JOIN HRMS_TMS_JOURNEY ON(JOURNEY_ID = PTM_TRAVEL_MODE_ID) "
					+ "	WHERE PTM_POLICY_ID ="
					+ polData[0][0]
					+ " AND PTM_STATUS ='Y' ORDER BY PTM_ID ";

			Object[][] trModeData = getSqlModel().getSingleResult(trModeSql);
			String trModeId = "";
			if (trModeData != null && trModeData.length > 0) {

				for (int i = 0; i < trModeData.length; i++) {
					if (i < trModeData.length - 1) {
						trModeId += "*" + trModeData[i][0] + "*,";
					} else {
						trModeId += "*" + trModeData[i][0] + "*";
					}
				}
				logger.info("valid Travel Mode=========== " + trModeId);
				bean.setValidTrMode(trModeId);
			}

			// FOR HOTEL TYPE

			String hotelTypeSql = " SELECT NVL(HOTEL_TYPE_NAME,' '),NVL(ROOM_TYPE_NAME,' ') ,NVL(PHT_HOTEL_TYPE_ID,0),NVL(PHT_ROOM_ID,0) FROM HRMS_TMS_POLICY_HOTEL_TYPE "
					+ "	LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HOTEL_TYPE_ID=PHT_HOTEL_TYPE_ID) "
					+ "	LEFT JOIN HRMS_TMS_ROOM_TYPE ON(ROOM_TYPE_ID=PHT_ROOM_ID) "
					+ "	WHERE PHT_POLICY_ID ="
					+ polData[0][0]
					+ " AND PHT_STATUS ='Y'	ORDER BY PHT_ID ";
			Object[][] hotelData = getSqlModel().getSingleResult(hotelTypeSql);
			String validHotelType = "";

			if (hotelData != null && hotelData.length > 0) {
				for (int j = 0; j < hotelData.length; j++) {
					if (j < (hotelData.length - 1)) {
						validHotelType = validHotelType + "*" + hotelData[j][2]
								+ "*H*" + "" + hotelData[j][3] + "*R,";
					} else {
						validHotelType = validHotelType + "*" + hotelData[j][2]
								+ "*H*" + "" + hotelData[j][3] + "*R";
					}

				}
				logger.info("validHotelType=========== " + validHotelType);
				bean.setValidHotelType(validHotelType);
			}

		}

	}

	private void setCancelEtcAmounts(TrvlSchedule bean) {

		String schQuery = "SELECT NVL(SCH_DTL_TRAVEL_COST,0),NVL(SCH_DTL_LOCAL_COST,0),NVL(SCH_DTL_LODG_COST,0),"
				+ "	NVL(SCH_DTL_TRAVEL_CANCEL_COST,''),NVL(SCH_DTL_LOCAL_CANCEL_COST,''),NVL(SCH_DTL_LODG_CANCEL_COST,'')"
				+ "	FROM HRMS_TMS_SCH_DTL WHERE SCH_DTL_TRAVEL_APP_ID="
				+ bean.getTravelAppId();

		Object[][] schData = getSqlModel().getSingleResult(schQuery);

		if (schData.length > 0 && schData != null) {
			bean.setTotTrvCost(checkNull(String.valueOf(schData[0][0])));
			bean.setTotTariffCost(checkNull(String.valueOf(schData[0][1])));
			bean.setTotLodgCost(checkNull(String.valueOf(schData[0][2])));
			bean.setTravelCost(checkNull(String.valueOf(schData[0][0])));
			bean.setLocalCost(checkNull(String.valueOf(schData[0][1])));
			bean.setLodgingCost(checkNull(String.valueOf(schData[0][2])));

			bean.setTotTrvCost(checkNull(String.valueOf(schData[0][3])));
			bean.setTotTariffCanCost(checkNull(String.valueOf(schData[0][4])));
			bean.setTotLodgCanCost(checkNull(String.valueOf(schData[0][5])));

			bean.setTravelCancelAmt(checkNull(String.valueOf(schData[0][3])));
			bean.setLocalCancelAmt(checkNull(String.valueOf(schData[0][4])));
			bean.setLodgeCancelAmt(checkNull(String.valueOf(schData[0][5])));

		}
	}

	private void setTotLodgeDtls(TrvlSchedule bean, HttpServletRequest request) {

		// for lodging details Iterator

		String lodgQuery = "SELECT APPLODG_SCH_CITYID,NVL(LOCATION_NAME,''),APPLODG_SCH_HOTEL,NVL(HOTEL_TYPE_NAME,''),APPLODG_SCH_ROOM,NVL(ROOM_TYPE_NAME,'')"
				+ "	,TO_CHAR(APPLODG_SCH_FROMDATE,'DD-MM-YYYY'),NVL(APPLODG_SCH_FROMTIME,''),NVL(TO_CHAR(APPLODG_SCH_TODATE,'DD-MM-YYYY'),''),NVL(APPLODG_SCH_TOTIME,'')"
				+ "	,NVL(APPLODG_SCH_ADDRESS,''),NVL(APPLODG_SCH_BOOKAMT,''),APPLODG_DTL_ID ,APPLODG_SCH_UPLOAD_TICKET FROM HRMS_TMS_APPLODG_DTL"
				+ "	LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = APPLODG_DTL_CITY )"
				+ "	LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_DTL_HOTEL)"
				+ "	LEFT JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_DTL_ROOM)"
				+ "	WHERE APPLODG_DTL_TRAPPID=" + bean.getTravelAppId();

		String lodgQuery1 = "SELECT APPLODG_DTL_CITY,NVL(LOCATION_NAME,''),APPLODG_DTL_HOTEL,NVL(HOTEL_TYPE_NAME,''),APPLODG_DTL_ROOM,NVL(ROOM_TYPE_NAME,'')"
				+ " ,TO_CHAR(APPLODG_DTL_FROMDATE,'DD-MM-YYYY'),APPLODG_DTL_FROMTIME,TO_CHAR(APPLODG_DTL_TODATE,'DD-MM-YYYY'),APPLODG_DTL_TOTIME"
				+ " ,APPLODG_DTL_ID FROM HRMS_TMS_APPLODG_DTL"
				+ " LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = APPLODG_DTL_CITY )"
				+ " LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_DTL_HOTEL)"
				+ " LEFT JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_DTL_ROOM)"
				+ " WHERE APPLODG_DTL_TRAPPID  =" + bean.getTravelAppId();

		Object[][] lodgData = getSqlModel().getSingleResult(lodgQuery);

		if (lodgData.length > 0 && lodgData != null) {

			ArrayList lodgeDtlsArr = new ArrayList();
			int j2 = 1;
			for (int i = 0; i < lodgData.length; i++) {

				TrvlSchedule lodgeBean = new TrvlSchedule();

				request.setAttribute("lodgeCityId" + j2, checkNull(String
						.valueOf(lodgData[i][0])));
				request.setAttribute("lodgeCity" + j2, checkNull(String
						.valueOf(lodgData[i][1])));

				request.setAttribute("lodgeHotelId" + j2, checkNull(String
						.valueOf(lodgData[i][2])));
				request.setAttribute("lodgeHotel" + j2, checkNull(String
						.valueOf(lodgData[i][3])));

				request.setAttribute("lodgeRoomId" + j2, checkNull(String
						.valueOf(lodgData[i][4])));
				request.setAttribute("lodgeRoom" + j2, checkNull(String
						.valueOf(lodgData[i][5])));

				lodgeBean.setLodgFrmDate(checkNull(String
						.valueOf(lodgData[i][6])));
				lodgeBean.setLodgChkInTm(checkNull(String
						.valueOf(lodgData[i][7])));
				lodgeBean.setLodgToDate(checkNull(String
						.valueOf(lodgData[i][8])));
				lodgeBean.setLodgChkOutTm(checkNull(String
						.valueOf(lodgData[i][9])));

				lodgeBean.setLodgAddrs(checkNull(String	.valueOf(lodgData[i][10]))); 
				lodgeBean.setLodgBokAmt(checkNull(String.valueOf(lodgData[i][11])));
				lodgeBean.setLodgeDtlId(checkNull(String .valueOf(lodgData[i][12])));
				request.setAttribute("paraFrm_lodgUploadFileName" + j2, checkNull(String.valueOf(lodgData[i][13])));
				
				lodgeBean.setLodgUploadFileName(checkNull(String .valueOf(lodgData[i][13])));
				
				lodgeDtlsArr.add(lodgeBean);
				j2++;
			} // end of for loop
			logger.info("ITERATOR SIZE________________" + lodgeDtlsArr.size());
			logger.info("no data flag========" + bean.isNoData());

			bean.setLodgingDtls(lodgeDtlsArr);
			bean.setLodgFlg(true);
		} else {
			bean.setLodgFlg(false);
		}

	}

	private void setTotLocalConvDtls(TrvlSchedule bean,
			HttpServletRequest request) {
		// for Local Conveyance Details Iterator

		String locQuery = "SELECT LOCATION_NAME ,NVL(APPLOCAL_SCH_SOURCE,' ') ,APPLOCAL_DTL_CITY ,TO_CHAR(APPLOCAL_SCH_CON_DATE,'DD-MM-YYYY') AS ST_DATE ,APPLOCAL_SCH_JOUR_MODE"
				+ " ,CASE WHEN NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),'')='-' THEN '' ELSE (JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME) END AS JOURNEY_MODE"
				+ " ,NVL(APPLOCAL_SCH_VEHICLE_NO,''),NVL(APPLOCAL_SCH_CON_PERSON,''),NVL(APPLOCAL_SCH_CON_NO,''),NVL(APPLOCAL_SCH_PICKUP_PER,''),"
				+ " NVL(APPLOCAL_SCH_PICKUP_TIME,''),NVL(APPLOCAL_SCH_PICKUP_PLACE,''),NVL(APPLOCAL_SCH_CON_COST,0),APPLOCAL_DTL_ID"
				+ " FROM HRMS_TMS_APPLOCAL_DTL"
				+ " LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE =HRMS_TMS_APPLOCAL_DTL.APPLOCAL_DTL_CITY)"
				+ " LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_APPLOCAL_DTL.APPLOCAL_SCH_JOUR_MODE=HRMS_TMS_JOURNEY.JOURNEY_ID)"
				+ " WHERE APPLOCAL_DTL_TRAPPID=" + bean.getTravelAppId();

		Object[][] locData = getSqlModel().getSingleResult(locQuery);

		if (locData.length > 0 && locData != null) {

			ArrayList trvLocList = new ArrayList();
			int j1 = 1;
			for (int i = 0; i < locData.length; i++) {

				TrvlSchedule locConvBean = new TrvlSchedule();

				locConvBean.setLocConCity(checkNull(String
						.valueOf(locData[i][0])));
				locConvBean.setLocConSource(checkNull(String
						.valueOf(locData[i][1])));
				locConvBean.setLocConCityId(checkNull(String
						.valueOf(locData[i][2])));
				locConvBean.setLocConDate(checkNull(String
						.valueOf(locData[i][3])));

				request.setAttribute("LocTrvlModId" + j1, checkNull(String
						.valueOf(locData[i][4])));
				request.setAttribute("LocTrvlMod" + j1, checkNull(String
						.valueOf(locData[i][5])));

				locConvBean.setLocConTrvlModNum(checkNull(String
						.valueOf(locData[i][6])));
				locConvBean.setLocConTrvlConPer(checkNull(String
						.valueOf(locData[i][7])));
				locConvBean.setLocConTrvlConNum(checkNull(String
						.valueOf(locData[i][8])));
				locConvBean.setLocConTrvlPcPer(checkNull(String
						.valueOf(locData[i][9])));
				locConvBean.setLocConTrvlPcTime(checkNull(String
						.valueOf(locData[i][10])));
				locConvBean.setLocConTrvlPcPlace(checkNull(String
						.valueOf(locData[i][11])));
				locConvBean.setLocConTrvlPcToriffCst(checkNull(String
						.valueOf(locData[i][12])));

				locConvBean.setLocConId(checkNull(String
						.valueOf(locData[i][13])));

				trvLocList.add(locConvBean);
				j1++;
			} // end of for loop
			logger.info("ITERATOR SIZE________________" + trvLocList.size());
			logger.info("no data flag========" + bean.isNoData());

			bean.setLocalConvDtls(trvLocList);
			bean.setLocalConFlg(true);
		} else {
			bean.setLocalConFlg(false);
		}

	}

	private void setTotBookingDtls(TrvlSchedule bean, HttpServletRequest request) {
		// booking details iterator
		String query1 = " SELECT C1.LOCATION_NAME || '-' || C2.LOCATION_NAME, APPJOUR_DTL_FROMPALCE, APPJOUR_DTL_TOPLACE ,"
				+ " TO_CHAR(APPJOUR_SCH_TRAVEL_DATE,'DD-MM-YYYY'), APPJOUR_SCH_TRAVEL_TIME,APPJOUR_SCH_JOURMODE,CASE WHEN NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),'')='-' THEN '' ELSE (JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME) END AS JOURNEY_MODE"
				+ " ,NVL(APPJOUR_SCH_VEHICLE_NO,''),NVL(APPJOUR_SCH_TICKET_NO,''),NVL(APPJOUR_SCH_TRAVEL_COST,''),"
				+ " APPJOUR_DTL_ID ,APPJOUR_SCH_UPLOAD_TICKET FROM HRMS_TMS_APPJOUR_DTL"
				+ " LEFT JOIN  HRMS_LOCATION C1 ON (C1.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_FROMPALCE)"
				+ " LEFT JOIN  HRMS_LOCATION C2 ON (C2.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_TOPLACE)"
				+ " LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_APPJOUR_DTL.APPJOUR_SCH_JOURMODE=HRMS_TMS_JOURNEY.JOURNEY_ID)"
				+ " WHERE  APPJOUR_DTL_TRAPPID =" + bean.getTravelAppId();

		Object[][] detl = getSqlModel().getSingleResult(query1);

		if (detl.length > 0 && detl != null) {

			ArrayList trvlList = new ArrayList();
			int j = 1;
			for (int i = 0; i < detl.length; i++) {

				TrvlSchedule bean1 = new TrvlSchedule();
				bean1.setSourceAndDest(checkNull(String.valueOf(detl[i][0])));

				bean1.setAppJournDtlFrom(checkNull(String.valueOf(detl[i][1])));

				bean1.setAppJournDtlTo(checkNull(String.valueOf(detl[i][2])));
				bean1.setTravelDate(checkNull(String.valueOf(detl[i][3])));
				bean1.setTravelTime(checkNull(String.valueOf(detl[i][4])));
				request.setAttribute("trModeId" + j, checkNull(String
						.valueOf(detl[i][5])));
				request.setAttribute("trMode" + j, checkNull(String
						.valueOf(detl[i][6])));
				bean1.setTrvlVehNo(checkNull(String.valueOf(detl[i][7])));
				bean1.setTicktNo(checkNull(String.valueOf(detl[i][8])));
				bean1.setTicktCost(checkNull(String.valueOf(detl[i][9])));
				bean1.setAppJournDtlId(checkNull(String.valueOf(detl[i][10]))); 
				request.setAttribute("paraFrm_uploadFileName" + j, checkNull(String
						.valueOf(detl[i][11]))); 
				
				bean1.setUploadFileName(checkNull(String.valueOf(detl[i][11]))); 
				
				
				trvlList.add(bean1);

				j++;
			} // end of for loop
			logger.info("ITERATOR SIZE________________" + trvlList.size());
			logger.info("no data flag========" + bean.isNoData());

			bean.setTravelSchList(trvlList);
			bean.setJourBkFlg(true);

		} else {
			bean.setJourBkFlg(false);
		}

	}

	private void setCancelCmts(TrvlSchedule bean) {

		String cmtsQuery = "SELECT NVL(SCH_APPR_MAIN_COMMENTS,'') ,NVL(SCH_APPR_ALTER_COMMENTS,'')  FROM HRMS_TMS_SCH_DTL"

				+ "	WHERE SCH_DTL_TRAVEL_APP_ID=" + bean.getTravelAppId();
		;

		Object[][] cmtData = getSqlModel().getSingleResult(cmtsQuery);

		if (cmtData.length > 0 && cmtData != null) {

			bean.setTrvApprCmts(checkNull(String.valueOf(cmtData[0][0])) + "\n"
					+ checkNull(String.valueOf(cmtData[0][1])));

		}

	}

	private void setTrvlLocalSchDtls(TrvlSchedule bean) {

		String schQuery = "SELECT NVL(SCH_DTL_TRAVEL_COST,0),NVL(SCH_DTL_LOCAL_COST,0),NVL(SCH_DTL_LODG_COST,0),NVL(SCH_DTL_MAIN_SCH_COMMENTS,'')"
				+ " FROM HRMS_TMS_SCH_DTL WHERE SCH_DTL_TRAVEL_APP_ID="
				+ bean.getTravelAppId();

		Object[][] schData = getSqlModel().getSingleResult(schQuery);

		if (schData.length > 0 && schData != null) {

			bean.setTotTrvCost(checkNull(String.valueOf(schData[0][0])));
			bean.setTotTariffCost(checkNull(String.valueOf(schData[0][1])));
			bean.setTotLodgCost(checkNull(String.valueOf(schData[0][2])));
			bean.setSchComment(checkNull(String.valueOf(schData[0][3])));

		}

	}

	private void setSchTrvlLodgeDtls(TrvlSchedule bean,
			HttpServletRequest request) {

		// for lodging details Iterator

		String lodgQuery = "SELECT NVL(LOCATION_NAME,''),NVL(TO_CHAR(APPLODG_SCH_FROMDATE,'DD-MM-YYYY'),''),NVL(APPLODG_SCH_FROMTIME,''),"
				+ "	NVL(TO_CHAR(APPLODG_SCH_TODATE,'DD-MM-YYYY'),''),NVL(APPLODG_SCH_TOTIME,''),NVL(HOTEL_TYPE_NAME,''),NVL(ROOM_TYPE_NAME,'')"
				+ "	,NVL(APPLODG_SCH_ADDRESS,''),NVL(APPLODG_SCH_BOOKAMT,''),NVL(APPLODG_SCH_UPLOAD_TICKET,'')"
				+ "	,APPLODG_DTL_ID,NVL(APPLODG_CANCEL_COST,0) FROM HRMS_TMS_APPLODG_DTL"
				+ "	LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = APPLODG_SCH_CITYID )"
				+ "	LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_SCH_HOTEL)"
				+ "	LEFT JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_SCH_ROOM)"
				+ "	WHERE APPLODG_DTL_TRAPPID  =" + bean.getTravelAppId();

		Object[][] lodgData = getSqlModel().getSingleResult(lodgQuery);

		if (lodgData.length > 0 && lodgData != null) {

			ArrayList lodgeDtlsArr = new ArrayList();

			for (int i = 0; i < lodgData.length; i++) {

				TrvlSchedule lodgeBean = new TrvlSchedule();
				lodgeBean
						.setLodgCity(checkNull(String.valueOf(lodgData[i][0])));
				lodgeBean.setLodgFrmDate(checkNull(String
						.valueOf(lodgData[i][1])));
				lodgeBean.setLodgChkInTm(checkNull(String
						.valueOf(lodgData[i][2])));
				lodgeBean.setLodgToDate(checkNull(String
						.valueOf(lodgData[i][3])));
				lodgeBean.setLodgChkOutTm(checkNull(String
						.valueOf(lodgData[i][4])));

				lodgeBean
						.setLodgHotel(checkNull(String.valueOf(lodgData[i][5])));
				lodgeBean
						.setLodgRoom(checkNull(String.valueOf(lodgData[i][6])));
				lodgeBean
						.setLodgAddrs(checkNull(String.valueOf(lodgData[i][7])));
				lodgeBean.setLodgBokAmt(checkNull(String
						.valueOf(lodgData[i][8])));
				lodgeBean.setLodgUploadFileName(checkNull(String
						.valueOf(lodgData[i][9])));

				lodgeBean.setLodgeDtlId(checkNull(String
						.valueOf(lodgData[i][10])));
				lodgeBean.setLodgCancAmt(checkNull(String
						.valueOf(lodgData[i][11])));

				lodgeDtlsArr.add(lodgeBean);

			} // end of for loop
			logger.info("ITERATOR SIZE________________" + lodgeDtlsArr.size());
			logger.info("no data flag========" + bean.isNoData());

			bean.setLodgingDtls(lodgeDtlsArr);
			bean.setLodgFlg(true);
		} else {
			bean.setLodgFlg(false);
		}

	}

	private void setSchTrvlLocalConvDtls(TrvlSchedule bean,
			HttpServletRequest request) {

		String locQuery = "SELECT NVL(LOCATION_NAME,''),NVL(APPLOCAL_SCH_SOURCE,'') ,NVL(TO_CHAR(APPLOCAL_SCH_CON_DATE,'DD-MM-YYYY'),'') AS ST_DATE "
				+ "	,CASE WHEN NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),'')='-' THEN '' ELSE (JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME) END AS JOURNEY_MODE,NVL(APPLOCAL_SCH_VEHICLE_NO,''), NVL(APPLOCAL_SCH_CON_PERSON,''), "
				+ " NVL(APPLOCAL_SCH_CON_NO,''), NVL(APPLOCAL_SCH_PICKUP_PER,''),"
				+ "	NVL(APPLOCAL_SCH_PICKUP_TIME,''),NVL(APPLOCAL_SCH_PICKUP_PLACE,''),NVL(APPLOCAL_SCH_CON_COST,'') ,APPLOCAL_DTL_ID,NVL(APPLOCAL_CANCEL_COST,0) FROM HRMS_TMS_APPLOCAL_DTL"
				+ "	LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE =HRMS_TMS_APPLOCAL_DTL.APPLOCAL_SCH_CITY)"
				+ "	LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_APPLOCAL_DTL.APPLOCAL_SCH_JOUR_MODE=HRMS_TMS_JOURNEY.JOURNEY_ID)"
				+ "	WHERE APPLOCAL_DTL_TRAPPID =" + bean.getTravelAppId();

		Object[][] locData = getSqlModel().getSingleResult(locQuery);

		if (locData.length > 0 && locData != null) {

			ArrayList trvLocList = new ArrayList();
			for (int i = 0; i < locData.length; i++) {
				TrvlSchedule locConvBean = new TrvlSchedule();

				locConvBean.setLocConCity(checkNull(String
						.valueOf(locData[i][0])));
				locConvBean.setLocConSource(checkNull(String
						.valueOf(locData[i][1])));
				locConvBean.setLocConDate(checkNull(String
						.valueOf(locData[i][2])));
				locConvBean.setLocConTrvlMod(checkNull(String
						.valueOf(locData[i][3])));
				locConvBean.setLocConTrvlModNum(checkNull(String
						.valueOf(locData[i][4])));
				locConvBean.setLocConTrvlConPer(checkNull(String
						.valueOf(locData[i][5])));
				locConvBean.setLocConTrvlConNum(checkNull(String
						.valueOf(locData[i][6])));
				locConvBean.setLocConTrvlPcPer(checkNull(String
						.valueOf(locData[i][7])));
				locConvBean.setLocConTrvlPcTime(checkNull(String
						.valueOf(locData[i][8])));
				locConvBean.setLocConTrvlPcPlace(checkNull(String
						.valueOf(locData[i][9])));
				locConvBean.setLocConTrvlPcToriffCst(checkNull(String
						.valueOf(locData[i][10])));
				locConvBean.setLocConId(checkNull(String
						.valueOf(locData[i][11])));
				locConvBean.setLocCancAmt(checkNull(String
						.valueOf(locData[i][12])));

				trvLocList.add(locConvBean);

			} // end of for loop
			logger.info("ITERATOR SIZE________________" + trvLocList.size());
			logger.info("no data flag========" + bean.isNoData());

			bean.setLocalConvDtls(trvLocList);
			bean.setLocalConFlg(true);
		} else {
			bean.setLocalConFlg(false);
		}

	}

	private void setSchTrvlBookingDtls(TrvlSchedule bean,
			HttpServletRequest request) {
		logger.info("I am in setSchTrvlBookingDtls");

		String query = " SELECT NVL(C1.LOCATION_NAME ||'-'||C2.LOCATION_NAME,''),NVL(TO_CHAR(APPJOUR_SCH_TRAVEL_DATE,'DD-MM-YYYY'),'') AS APP_DATE, NVL(APPJOUR_SCH_TRAVEL_TIME,''),"
				+ " CASE WHEN NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),'')='-' THEN '' ELSE (JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME) END AS JOURNEY_MODE,NVL(APPJOUR_SCH_VEHICLE_NO,''),NVL(APPJOUR_SCH_TICKET_NO,''),NVL(APPJOUR_SCH_TRAVEL_COST,''),NVL(APPJOUR_SCH_UPLOAD_TICKET,'')"

				+ ",NVL(APPJOUR_CANCEL_TRAVEL_COST,0)"
				+ " ,APPJOUR_DTL_ID ,APPJOUR_SCH_UPLOAD_TICKET FROM HRMS_TMS_APPJOUR_DTL"
				+ " LEFT JOIN  HRMS_LOCATION C1 ON (C1.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_FROMPALCE)"
				+ " LEFT JOIN  HRMS_LOCATION C2 ON (C2.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_TOPLACE)"
				+ " LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_APPJOUR_DTL.APPJOUR_SCH_JOURMODE=HRMS_TMS_JOURNEY.JOURNEY_ID)"
				+ " WHERE  APPJOUR_DTL_TRAPPID =" + bean.getTravelAppId();

		Object[][] detl = getSqlModel().getSingleResult(query);

		if (detl.length > 0 && detl != null) {
				int j =1;
			ArrayList trvlList = new ArrayList();
			for (int i = 0; i < detl.length; i++) {
				TrvlSchedule bean1 = new TrvlSchedule();
				bean1.setSourceAndDest(checkNull(String.valueOf(detl[i][0])));
				bean1.setTravelDate(checkNull(String.valueOf(detl[i][1])));
				bean1.setTravelTime(checkNull(String.valueOf(detl[i][2])));
				bean1.setTravelMdAndCls(checkNull(String.valueOf(detl[i][3])));
				bean1.setTrvlVehNo(checkNull(String.valueOf(detl[i][4])));
				bean1.setTicktNo(checkNull(String.valueOf(detl[i][5])));
				bean1.setTicktCost(checkNull(String.valueOf(detl[i][6])));
				bean1.setUploadFileName(checkNull(String.valueOf(detl[i][7])));
				bean1.setAppJournDtlId(checkNull(String.valueOf(detl[i][8])));
				bean1.setTrvCancAmt(checkNull(String.valueOf(detl[i][9])));
				
				request.setAttribute("paraFrm_uploadFileName" + j, checkNull(String
						.valueOf(detl[i][10]))); 
				bean1.setUploadFileName(checkNull(String.valueOf(detl[i][10])));
				
				
				j++;
				trvlList.add(bean1);

			} // end of for loop
			logger.info("ITERATOR SIZE________________" + trvlList.size());
			logger.info("no data flag========" + bean.isNoData());

			bean.setTravelSchList(trvlList);
			bean.setJourBkFlg(true);
		} else {
			bean.setJourBkFlg(false);
		}

	}

	private void setLodgeDtls(TrvlSchedule bean, HttpServletRequest request) {

		// for lodging details Iterator

		String lodgQuery = "SELECT APPLODG_DTL_CITY,NVL(LOCATION_NAME,''),APPLODG_DTL_HOTEL,NVL(HOTEL_TYPE_NAME,''),APPLODG_DTL_ROOM,NVL(ROOM_TYPE_NAME,'')"
				+ " ,TO_CHAR(APPLODG_DTL_FROMDATE,'DD-MM-YYYY'),APPLODG_DTL_FROMTIME,TO_CHAR(APPLODG_DTL_TODATE,'DD-MM-YYYY'),APPLODG_DTL_TOTIME"
				+ " ,APPLODG_DTL_ID ,APPLODG_SCH_UPLOAD_TICKET  FROM HRMS_TMS_APPLODG_DTL"
				+ " LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = APPLODG_DTL_CITY )"
				+ " LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_DTL_HOTEL)"
				+ " LEFT JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_DTL_ROOM)"
				+ " WHERE APPLODG_DTL_TRAPPID  =" + bean.getTravelAppId();

		Object[][] lodgData = getSqlModel().getSingleResult(lodgQuery);

		if (lodgData.length > 0 && lodgData != null) {

			ArrayList lodgeDtlsArr = new ArrayList();
			int j2 = 1;
			for (int i = 0; i < lodgData.length; i++) {

				TrvlSchedule lodgeBean = new TrvlSchedule();

				request.setAttribute("lodgeCityId" + j2, checkNull(String
						.valueOf(lodgData[i][0])));
				request.setAttribute("lodgeCity" + j2, checkNull(String
						.valueOf(lodgData[i][1])));

				request.setAttribute("lodgeHotelId" + j2, checkNull(String
						.valueOf(lodgData[i][2])));
				request.setAttribute("lodgeHotel" + j2, checkNull(String
						.valueOf(lodgData[i][3])));

				request.setAttribute("lodgeRoomId" + j2, checkNull(String
						.valueOf(lodgData[i][4])));
				request.setAttribute("lodgeRoom" + j2, checkNull(String
						.valueOf(lodgData[i][5])));

				lodgeBean.setLodgFrmDate(checkNull(String
						.valueOf(lodgData[i][6])));
				lodgeBean.setLodgChkInTm(checkNull(String
						.valueOf(lodgData[i][7])));
				lodgeBean.setLodgToDate(checkNull(String
						.valueOf(lodgData[i][8])));
				lodgeBean.setLodgChkOutTm(checkNull(String.valueOf(lodgData[i][9]))); 
				lodgeBean.setLodgeDtlId(checkNull(String.valueOf(lodgData[i][10])));
				request.setAttribute("paraFrm_lodgUploadFileName" + j2, checkNull(String.valueOf(lodgData[i][11])));
				lodgeBean.setUploadFileName(checkNull(String.valueOf(lodgData[i][11])));
				
				lodgeDtlsArr.add(lodgeBean);
				j2++;
			} // end of for loop
			logger.info("ITERATOR SIZE________________" + lodgeDtlsArr.size());
			logger.info("no data flag========" + bean.isNoData());

			bean.setLodgingDtls(lodgeDtlsArr);
			bean.setLodgFlg(true);
		} else {
			bean.setLodgFlg(false);
		}

	}

	private void setLocalConvDtls(TrvlSchedule bean, HttpServletRequest request) {
		// for Local Conveyance Details Iterator

		String locQuery = "SELECT LOCATION_NAME ,NVL(APPLOCAL_DTL_SOURCE,' ') ,APPLOCAL_DTL_CITY ,TO_CHAR(APPLOCAL_DTL_FROM_DATE,'DD-MM-YYYY') AS ST_DATE ,APPLOCAL_SCH_JOUR_MODE"
				+ "	,CASE WHEN NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),'')='-' THEN '' ELSE (JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME) END AS JOURNEY_MODE,APPLOCAL_DTL_ID"
				+ "	FROM HRMS_TMS_APPLOCAL_DTL"
				+ "	LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE =HRMS_TMS_APPLOCAL_DTL.APPLOCAL_DTL_CITY)"
				+ "	LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_APPLOCAL_DTL.APPLOCAL_SCH_JOUR_MODE=HRMS_TMS_JOURNEY.JOURNEY_ID)"
				+ "	WHERE APPLOCAL_DTL_TRAPPID  =" + bean.getTravelAppId();

		Object[][] locData = getSqlModel().getSingleResult(locQuery);

		if (locData.length > 0 && locData != null) {

			ArrayList trvLocList = new ArrayList();
			int j1 = 1;
			for (int i = 0; i < locData.length; i++) {

				TrvlSchedule locConvBean = new TrvlSchedule();

				locConvBean.setLocConCity(checkNull(String
						.valueOf(locData[i][0])));
				locConvBean.setLocConSource(checkNull(String
						.valueOf(locData[i][1])));
				locConvBean.setLocConCityId(checkNull(String
						.valueOf(locData[i][2])));
				locConvBean.setLocConDate(checkNull(String
						.valueOf(locData[i][3])));

				request.setAttribute("LocTrvlModId" + j1, checkNull(String
						.valueOf(locData[i][4])));
				request.setAttribute("LocTrvlMod" + j1, checkNull(String
						.valueOf(locData[i][5])));
				logger.info("values----"
						+ request.getAttribute("LocTrvlModId" + j1));

				logger.info("values----"
						+ request.getAttribute("LocTrvlMod" + j1));

				locConvBean
						.setLocConId(checkNull(String.valueOf(locData[i][6])));

				trvLocList.add(locConvBean);
				j1++;
			} // end of for loop
			logger.info("ITERATOR SIZE________________" + trvLocList.size());
			logger.info("no data flag========" + bean.isNoData());

			bean.setLocalConvDtls(trvLocList);
			bean.setLocalConFlg(true);
		} else {
			bean.setLocalConFlg(false);
		}

	}

	private void setBookingDtls(TrvlSchedule bean, HttpServletRequest request) {
		// booking details iterator

		String query1 = " SELECT C1.LOCATION_NAME ||'-'|| C2.LOCATION_NAME, APPJOUR_DTL_FROMPALCE, APPJOUR_DTL_TOPLACE ,"
				+ " TO_CHAR(APPJOUR_DTL_JOURDATE,'DD-MM-YYYY'), APPJOUR_DTL_JOURTIME,APPJOUR_DTL_JOURMODE,CASE WHEN NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),'')='-' THEN '' ELSE (JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME) END AS JOURNEY_MODE"
				+ " ,APPJOUR_DTL_ID ,APPJOUR_SCH_UPLOAD_TICKET FROM HRMS_TMS_APPJOUR_DTL"
				+ " LEFT JOIN  HRMS_LOCATION C1 ON (C1.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_FROMPALCE)"
				+ " LEFT JOIN  HRMS_LOCATION C2 ON (C2.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_TOPLACE)"
				+ "	LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_JOURMODE=HRMS_TMS_JOURNEY.JOURNEY_ID)  "
				+ " WHERE  APPJOUR_DTL_TRAPPID =" + bean.getTravelAppId();

		Object[][] detl = getSqlModel().getSingleResult(query1);

		if (detl.length > 0 && detl != null) {

			ArrayList trvlList = new ArrayList();
			int j = 1;
			for (int i = 0; i < detl.length; i++) {

				TrvlSchedule bean1 = new TrvlSchedule();
				bean1.setSourceAndDest(checkNull(String.valueOf(detl[i][0])));
				bean1.setAppJournDtlFrom(checkNull(String.valueOf(detl[i][1])));
				bean1.setAppJournDtlTo(checkNull(String.valueOf(detl[i][2])));
				bean1.setTravelDate(checkNull(String.valueOf(detl[i][3])));
				bean1.setTravelTime(checkNull(String.valueOf(detl[i][4])));
				request.setAttribute("trModeId" + j, checkNull(String
						.valueOf(detl[i][5])));
				request.setAttribute("trMode" + j, checkNull(String
						.valueOf(detl[i][6])));

				bean1.setAppJournDtlId(checkNull(String.valueOf(detl[i][7])));
				request.setAttribute("paraFrm_uploadFileName" + j, checkNull(String.valueOf(detl[i][8]))); 
				bean1.setUploadFileName(checkNull(String.valueOf(detl[i][8])));
				
				trvlList.add(bean1);

				j++;
			} // end of for loop
			logger.info("ITERATOR SIZE________________" + trvlList.size());
			logger.info("no data flag========" + bean.isNoData());

			bean.setTravelSchList(trvlList);
			bean.setJourBkFlg(true);

		} else {
			bean.setJourBkFlg(false);
		}

	}

	private void setEmpDtls(TrvlSchedule bean, HttpServletRequest request) {
		logger.info("I am in call Deatails");
		String appStatus = bean.getStat();
		String dataQUery = "SELECT 	EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,CENTER_NAME,DEPT_NAME,"
				+ "	TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY') AS APP_DATE,NVL(CADRE_NAME,' '),ROUND((SYSDATE-EMP_DOB)/365) AS DOB"
				+ ",NVL(ADD_MOBILE,' ') ,TRAVEL_APP_EMPID, ";
		

		if (appStatus.equals("SA"))
			dataQUery+="NVL(SCH_APPR_ADVANCE_SAN,'')";				
		
		else
		{
			dataQUery+="NVL(TRAVEL_APP_ADVANCE,0)";
		}
			    
		dataQUery+=",DECODE(TRAVEL_APP_PAYMODE,'C','Cash','S','Salary','Q','Cheque','T','Transfer') AS PAYMODE"
				+ " ,TRAVEL_APP_REQUEST,TO_CHAR(TRAVEL_APP_START_DATE,'DD-MM-YYYY') AS START_DTAE, TO_CHAR(TRAVEL_APP_END_DATE,'DD-MM-YYYY') AS END_DATE,NVL(PURPOSE_NAME,'')"
				+ " FROM HRMS_TMS_TRAVEL_APP"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID=HRMS_EMP_OFFC.EMP_ID)"
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )"
				+ " LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
				+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )"
				+ " LEFT  JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID =HRMS_EMP_OFFC.EMP_ID)"
				+ "LEFT JOIN HRMS_TMS_SCH_DTL ON(HRMS_TMS_SCH_DTL.SCH_DTL_TRAVEL_APP_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID)"
				+ " LEFT  JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_PURPOSE)"
				+ " WHERE TRAVEL_APP_ID=" + bean.getTravelAppId();
		logger.info("APPLICATION ID-------------------------------"
				+ bean.getTravelAppId());

		Object[][] data = getSqlModel().getSingleResult(dataQUery);

		if (data.length > 0 && data != null) {

			bean.setEmpToken(checkNull(String.valueOf(data[0][0])));
			bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));
			bean.setBrnchName(checkNull(String.valueOf(data[0][2])));
			bean.setDeptName(checkNull(String.valueOf(data[0][3])));
			// bean.setDesgName(checkNull(String.valueOf(data[0][4])));
			bean.setApplDate(checkNull(String.valueOf(data[0][4])));
			bean.setGrdName(checkNull(String.valueOf(data[0][5])));
			//for showing Travel Policy
			bean.setGrade(checkNull(String.valueOf(data[0][5])));
			bean.setDob(checkNull(String.valueOf(data[0][6])));
			bean.setMobile(checkNull(String.valueOf(data[0][7])));
			bean.setEmpId(checkNull(String.valueOf(data[0][8])));
			bean.setSchAdvAmt(checkNull(String.valueOf(data[0][9])));
			// for storing the data in advance table -----request Advance amount
			bean.setReqAdvAmt(checkNull(String.valueOf(data[0][9])));
			bean.setSchPayMode(checkNull(String.valueOf(data[0][10])));
			// bean.setSchComment(checkNull(String.valueOf(data[0][11])));
		
			bean.setTrvlRequest(checkNull(String.valueOf(data[0][11])));
			bean.setTourStrtDate(checkNull(String.valueOf(data[0][12])));
			bean.setTourEndDate(checkNull(String.valueOf(data[0][13])));
			bean.setTourPurpose(checkNull(String.valueOf(data[0][14])));
			
		}

	}

	public boolean saveSchlerData(TrvlSchedule trvlSchl,HttpServletRequest request) {
		
		//FOR MAIN SCHEDULER
		String schId ="0";
		String mainSchSql =" SELECT MANG_AUTH_SCHEDULER  FROM HRMS_TMS_MANG_AUTH WHERE  MANG_AUTH_BRANCH = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="+trvlSchl.getEmpId()+" )";
		Object [][] data = getSqlModel().getSingleResult(mainSchSql);
		if(data!= null && data.length >0){
			schId =checkNull(""+data[0][0]);
		}
		
		logger.info("I am in saveSchlerData");
		// check for the AppId ...if it is there update the table else add new
		// record

		boolean check = checkAppId(trvlSchl);

		if (check) {
			Object addSchlData[][] = new Object[1][8];
			addSchlData[0][0] = checkNull(trvlSchl.getAppID());// Travel Application Id
			addSchlData[0][1] = schId;// Main
			// Scheduler
			addSchlData[0][2] = checkNull(trvlSchl.getSchComment());// Main
			// Scheduler
			// comments
		//	addSchlData[0][3] = "3";// Alternate Scheduler
		//	addSchlData[0][4] = "COMMENTS";// Alternate Scheduler comments
			// addSchlData[0][5] = "U";// Status
			addSchlData[0][3] = checkNull(trvlSchl.getSaveStatusFlag());// status

			addSchlData[0][4] = checkNull(trvlSchl.getTotTrvCost());// Total
			// travel
			// cost
			addSchlData[0][5] = checkNull(trvlSchl.getTotTariffCost());// Total
			// local
			// cost
			addSchlData[0][6] = checkNull(trvlSchl.getTotLodgCost());// Total
			// lodging
			// cost
			addSchlData[0][7] = "P";
			
			
			
			return getSqlModel().singleExecute(getQuery(1), addSchlData);
		} else {
			// App Id is there get that id and update the table

			String tableId = getDtlTableId(trvlSchl);

			Object upDateSchlData[][] = new Object[1][9];

			upDateSchlData[0][0] = checkNull(trvlSchl.getAppID());// Travel
			// Application
			// Id
			upDateSchlData[0][1] = schId;// Main
			// Scheduler
			upDateSchlData[0][2] = checkNull(trvlSchl.getSchComment());// Main
			// Scheduler
			// comments
			//upDateSchlData[0][3] = "3";// Alternate Scheduler
			//upDateSchlData[0][4] = "COMMENTS";// Alternate Scheduler comments
			// upDateSchlData[0][5] = "U";// Status
			upDateSchlData[0][3] = checkNull(trvlSchl.getSaveStatusFlag());// status
			upDateSchlData[0][4] = checkNull(trvlSchl.getTotTrvCost());// Total
			// travel
			// cost
			upDateSchlData[0][5] = checkNull(trvlSchl.getTotTariffCost());// Total
			// local
			// cost
			upDateSchlData[0][6] = checkNull(trvlSchl.getTotLodgCost());// Total
			// lodging
			// cost
			upDateSchlData[0][7] = "P";
			upDateSchlData[0][8] = tableId;// Schedule Detail Id

			return getSqlModel().singleExecute(getQuery(5), upDateSchlData);

		}
	}

	// methods for updation

	public void upDateBookDtls(TrvlSchedule bean, HttpServletRequest request) {

		logger.info("I am in upDateBookDtls");
		String[] journFrom = request.getParameterValues("appJournDtlFrom");
		String[] journeyTo = request.getParameterValues("appJournDtlTo");
		String[] jourDate = request.getParameterValues("travelDate");
		String[] travelTime = request.getParameterValues("travelTime");
		String[] vehNo = request.getParameterValues("trvlVehNo");
		String[] tcktNo = request.getParameterValues("ticktNo");
		String[] tcktCost = request.getParameterValues("ticktCost");
		String[] uploadFileName = request.getParameterValues("uploadFileName");
		// mode and upload files
		String[] journeyId = request.getParameterValues("appJournDtlId");

		try {
			Object upDateBook[][] = new Object[1][11];
			Object updateJourTrack[][] = new Object[1][12];
			int j =1;
			for (int i = 0; i < journFrom.length; i++) {
				upDateBook[0][0] = checkNull(String.valueOf(journFrom[i]));
				upDateBook[0][1] = checkNull(String.valueOf(journeyTo[i]));
				upDateBook[0][2] = checkNull(String.valueOf(jourDate[i]));
				upDateBook[0][3] = checkNull(String.valueOf(travelTime[i]));
				upDateBook[0][4] = checkNull(String.valueOf(vehNo[i]));
				upDateBook[0][5] = checkNull(String.valueOf(tcktNo[i]));
				upDateBook[0][6] = checkNull(String.valueOf(tcktCost[i]));
				upDateBook[0][7] = checkNull((String) request.getParameter("travelMdAndClsId" + (i+1)));
				upDateBook[0][8] = checkNull(uploadFileName[i]);
			 System.out.println("upDateBook[0][8] ===== "+upDateBook[0][8]);
				upDateBook[0][9] = checkNull(String.valueOf(journeyId[i]));
				upDateBook[0][10] = bean.getAppID();

				// for updation of journey table track tables

				for (int x = 0; x < 11; x++) {
					updateJourTrack[0][x + 1] = upDateBook[0][x];
				}
				updateJourTrack[0][0] = bean.getUserEmpId();

				getSqlModel().singleExecute(getQuery(2), upDateBook);

				getSqlModel().singleExecute(getQuery(6), updateJourTrack);
				j++;

			}

		} catch (Exception e) {
			logger.info("krishna" + e.getMessage());
		}

	
	}

	public void upDateLocConDtls(TrvlSchedule bean, HttpServletRequest request) {
		logger.info("I am in upDateBookDtls");
		String[] cityId = request.getParameterValues("locConCityId");
		String[] source = request.getParameterValues("locConSource12");
		String[] date = request.getParameterValues("locConDate12");
		String[] vehcleNo = request.getParameterValues("locConTrvlModNum");
		String[] conPer = request.getParameterValues("locConTrvlConPer");
		String[] conPerNo = request.getParameterValues("locConTrvlConNum");
		String[] picPer = request.getParameterValues("locConTrvlPcPer");
		String[] picTime = request.getParameterValues("locConTrvlPcTime");
		String[] picPlace = request.getParameterValues("locConTrvlPcPlace");
		String[] locConIds = request.getParameterValues("locConId");
		String[] toriffCost = request
				.getParameterValues("locConTrvlPcToriffCst");

		try {
			// .................mode ,appId [order]

			Object upDateLocCon[][] = new Object[1][13];
			Object updateLocConTrack[][] = new Object[1][14];

			for (int i = 0; i < cityId.length; i++) {
				upDateLocCon[0][0] = checkNull(String.valueOf(cityId[i]));
				upDateLocCon[0][1] = checkNull(String.valueOf(source[i]));
				logger.info("Travel Source rakkkkkkkkkkkkkk======"+upDateLocCon[0][1]);
				upDateLocCon[0][2] = checkNull(String.valueOf(date[i]));
				logger.info("Travel Date  rakkkkkkkkkkkkkk======"+upDateLocCon[0][2]);
				upDateLocCon[0][3] = checkNull(String.valueOf(vehcleNo[i]));
				upDateLocCon[0][4] = checkNull(String.valueOf(conPer[i]));
				upDateLocCon[0][5] = checkNull(String.valueOf(conPerNo[i]));
				upDateLocCon[0][6] = checkNull(String.valueOf(picPer[i]));
				upDateLocCon[0][7] = checkNull(String.valueOf(picTime[i]));
				upDateLocCon[0][8] = checkNull(String.valueOf(picPlace[i]));
				upDateLocCon[0][9] = checkNull(String.valueOf(toriffCost[i]));

				upDateLocCon[0][10] = checkNull((String) request
						.getParameter("locConTrvlModId" + (i + 1)));
				upDateLocCon[0][11] = checkNull(String.valueOf(locConIds[i]));

				upDateLocCon[0][12] = bean.getAppID();

				for (int y = 0; y < 13; y++) {
					updateLocConTrack[0][y + 1] = upDateLocCon[0][y];
				}
				updateLocConTrack[0][0] = bean.getUserEmpId();

				getSqlModel().singleExecute(getQuery(3), upDateLocCon);
				getSqlModel().singleExecute(getQuery(7), updateLocConTrack);

			}
		} catch (Exception e) {
			logger.info("krishna-------------------" + e.getMessage());
		}

	}

	public void upDateLodgDtls(TrvlSchedule bean, HttpServletRequest request) {
		logger.info("I am in upDateBookDtls");
		String[] frmDate = request.getParameterValues("lodgFrmDate");
		String[] chkInTm = request.getParameterValues("lodgChkInTm");
		String[] toDate = request.getParameterValues("lodgToDate");
		String[] chkOutTm = request.getParameterValues("lodgChkOutTm");
		String[] addr = request.getParameterValues("lodgAddrs");
		String[] bookAmt = request.getParameterValues("lodgBokAmt");
		String[] lodgeDtlId = request.getParameterValues("lodgeDtlId");
		String[] lodgUploadFileName = request.getParameterValues("lodgUploadFileName");

		// order........, cityId,hotel,room,upLoadfile,AppId  

		try {
			logger.info("length of frmDate" + frmDate.length);
			Object upDateLodg[][] = new Object[1][12];
			Object updateLodgTrack[][] = new Object[1][13];

			for (int i = 0; i < frmDate.length; i++) {
				upDateLodg[0][0] = checkNull(String.valueOf(frmDate[i]));
				upDateLodg[0][1] = checkNull(String.valueOf(chkInTm[i]));
				upDateLodg[0][2] = checkNull(String.valueOf(toDate[i]));
				upDateLodg[0][3] = checkNull(String.valueOf(chkOutTm[i]));
				upDateLodg[0][4] = checkNull(String.valueOf(addr[i]));
				upDateLodg[0][5] = checkNull(String.valueOf(bookAmt[i]));
				upDateLodg[0][6] = checkNull((String) request
						.getParameter("lodgCityId" + (i + 1)));// cityId
				upDateLodg[0][7] = checkNull((String) request
						.getParameter("lodgHotelType" + (i + 1)));// hotelId
				upDateLodg[0][8] = checkNull((String) request
						.getParameter("lodgRoomType" + (i + 1)));// RoomId
				upDateLodg[0][9] = checkNull(lodgUploadFileName[i]);// upLoad
				// files

				logger.info("krishna.................." + upDateLodg[0][9]);

				upDateLodg[0][10] = checkNull(String.valueOf(lodgeDtlId[i]));
				upDateLodg[0][11] = bean.getAppID();

				for (int z = 0; z < 12; z++) {
					updateLodgTrack[0][z + 1] = upDateLodg[0][z];
				}
				updateLodgTrack[0][0] = bean.getUserEmpId();

				getSqlModel().singleExecute(getQuery(4), upDateLodg);

				getSqlModel().singleExecute(getQuery(8), updateLodgTrack);

			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("krishna" + e.getMessage());
		}

	}

	private boolean checkAppId(TrvlSchedule bean) {

		String query = "SELECT * FROM HRMS_TMS_SCH_DTL WHERE SCH_DTL_TRAVEL_APP_ID="
				+ bean.getAppID();
		Object[][] Data = getSqlModel().getSingleResult(query);

		if (Data.length > 0 && Data != null)
			return false;
		else
			return true;

	}

	private String getDtlTableId(TrvlSchedule bean) {
		String query = "SELECT SCH_DTL_ID FROM HRMS_TMS_SCH_DTL WHERE SCH_DTL_TRAVEL_APP_ID="
				+ bean.getAppID();
		Object[][] Data = getSqlModel().getSingleResult(query);
		if (Data.length > 0 && Data != null)
			return String.valueOf(Data[0][0]);
		else
			return "";

	}

	public boolean saveCanceled(TrvlSchedule bean, HttpServletRequest request) {

		updateTravelDetails(bean, request);
		updateLocalDetails(bean, request);
		updateLodgeDetails(bean, request);

		Object upDateCancel[][] = new Object[1][8];
		upDateCancel[0][0] = checkNull(bean.getTravelCancelAmt());
		upDateCancel[0][1] = checkNull(bean.getLocalCancelAmt());
		upDateCancel[0][2] = checkNull(bean.getLodgCancAmt());
		upDateCancel[0][3] = checkNull(bean.getTotalSchedCost());
		upDateCancel[0][4] = checkNull(bean.getTotalRefundCost());
		upDateCancel[0][5] = "C";
		upDateCancel[0][6] = checkNull(bean.getCanCmts());
		upDateCancel[0][7] = bean.getAppID();
		return getSqlModel().singleExecute(getQuery(9), upDateCancel);

	}

	private void updateLodgeDetails(TrvlSchedule bean,
			HttpServletRequest request) {

		String[] lodgCanAmt = request.getParameterValues("lodgCancAmt");
		String[] lodgeDtlId = request.getParameterValues("lodgeDtlId");

		try {
			Object upDateLodge[][] = new Object[1][3];
			for (int i = 0; i < lodgCanAmt.length; i++) {
				upDateLodge[0][0] = checkNull(String.valueOf(lodgCanAmt[i]));
				upDateLodge[0][1] = checkNull(String.valueOf(lodgeDtlId[i]));
				upDateLodge[0][2] = bean.getAppID();

				getSqlModel().singleExecute(getQuery(14), upDateLodge);
				getSqlModel().singleExecute(getQuery(15), upDateLodge);
			}

		} catch (Exception e) {
			logger.info("krishna" + e.getMessage());
		}

	}

	private void updateLocalDetails(TrvlSchedule bean,
			HttpServletRequest request) {

		String[] trvlLocCanAmt = request.getParameterValues("locCancAmt");
		String[] locConIds = request.getParameterValues("locConId");

		try {
			Object upDateLocal[][] = new Object[1][3];
			for (int i = 0; i < trvlLocCanAmt.length; i++) {
				upDateLocal[0][0] = checkNull(String.valueOf(trvlLocCanAmt[i]));
				upDateLocal[0][1] = checkNull(String.valueOf(locConIds[i]));
				upDateLocal[0][2] = bean.getAppID();

				getSqlModel().singleExecute(getQuery(12), upDateLocal);
				getSqlModel().singleExecute(getQuery(13), upDateLocal);
			}

		} catch (Exception e) {
			logger.info("krishna" + e.getMessage());
		}

	}

	private void updateTravelDetails(TrvlSchedule bean,
			HttpServletRequest request) {
		logger.info("I am in upDateBookDtls");

		String[] trvlCanAmt = request.getParameterValues("trvCancAmt");
		String[] journeyId = request.getParameterValues("appJournDtlId");

		try {
			Object upDateTravel[][] = new Object[1][3];
			for (int i = 0; i < trvlCanAmt.length; i++) {
				upDateTravel[0][0] = checkNull(String.valueOf(trvlCanAmt[i]));
				upDateTravel[0][1] = checkNull(String.valueOf(journeyId[i]));
				upDateTravel[0][2] = bean.getAppID();
				// for updation of journey table track tables
				getSqlModel().singleExecute(getQuery(10), upDateTravel);
				getSqlModel().singleExecute(getQuery(11), upDateTravel);
			}

		} catch (Exception e) {
			logger.info("krishna" + e.getMessage());
		}

	}

	private boolean checkStatus(TrvlSchedule bean) {

		String query = "SELECT * FROM HRMS_TMS_SCH_DTL WHERE SCH_DTL_SCH_STATUS='U' AND SCH_DTL_TRAVEL_APP_ID="
				+ bean.getAppID()+" AND SCH_DTL_TRAVEL_COST IS NOT NULL";
		Object[][] data = getSqlModel().getSingleResult(query);

		if (data.length > 0 && data != null) {
			return true;
		} else {
			return false;
		}

	}

	public boolean callRequistionStatus(TrvlSchedule bean) {
		String reqSql = "SELECT PROCESS_MANAGER_SCH_REQ  FROM HRMS_TMS_PROCESS_MANAGER ";
		Object[][] settleData = getSqlModel().getSingleResult(reqSql);
		if (settleData != null && settleData.length > 0) {

			if (("" + settleData[0][0]).equals("Y")) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public void saveAdvanceDtls(TrvlSchedule bean) {

		Object addObj[][] = new Object[1][4];
		addObj[0][0] = checkNull(bean.getReqAdvAmt());
		addObj[0][1] = checkNull(bean.getSchAdvAmt());
		addObj[0][2] = checkNull(bean.getEmpId());
		addObj[0][3] = checkNull(bean.getAppID());
		getSqlModel().singleExecute(getQuery(16), addObj);

	}

	public void callAlterschUpdate(TrvlSchedule bean,HttpServletRequest request )
	{
	    String trAppId = request.getParameter("trvAppId");
		String sql ="UPDATE  HRMS_TMS_SCH_DTL SET SCH_DTL_ALTER_SCHEDULER = "+bean.getAltSchEmpId()+",SCH_DTL_SCH_CHKFLAG ='N' WHERE  SCH_DTL_TRAVEL_APP_ID ="+trAppId;
		getSqlModel().singleExecute(sql);
		
	}
}
