package org.paradyne.model.TravelManagement.TravelPlan;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelPlan.TravelScheduleApprover;
import org.paradyne.lib.ModelBase;

/**
 * 
 * @author Krishna
 *
 */
public class TravelScheduleApproverModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelScheduleApproverModel.class);

	public void callStatus(TravelScheduleApprover bean, String status,
			HttpServletRequest request) {
		// check the status here

		String chkSql = "SELECT MANG_AUTH_BRANCH FROM HRMS_TMS_MANG_AUTH WHERE MANG_AUTH_SCHEDULER ="
				+ bean.getUserEmpId()
				+ " AND MANG_AUTH_STATUS ='A' AND MANG_AUTH_DEFAULT_FLAG ='Y'";
		Object[][] chkData = getSqlModel().getSingleResult(chkSql);

		bean.setStat(status);
		logger.info("Status---------------" + bean.getStat());
		String appStatus = bean.getStat();
		String query = "";
		// check status here		
		query = "  SELECT EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPNAME,TRAVEL_APP_REQUEST ,  TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY'), "
				+ "SCH_APPR_STATUS,NVL(SCH_APPR_MAIN_COMMENTS,''), TRAVEL_APP_ID,NVL(SCH_DTL_SCH_APPR_CHKFLAG,'Y')  FROM HRMS_TMS_TRAVEL_APP  "
				+ "	LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID)  "
				+ "	LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)  "
				+ "	LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)	"
				+ " LEFT JOIN HRMS_TMS_SCH_DTL ON(HRMS_TMS_SCH_DTL.SCH_DTL_TRAVEL_APP_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID)";
		// +"	WHERE TRAVEL_APP_STATUS  ='"+status+"'  AND ( TRAVEL_APP_APPROVER ="+bean.getUserEmpId() +"  OR  TRAVEL_APP_ALTER_APPROVER = "+bean.getUserEmpId() +") ORDER BY TRAVEL_APP_APPDATE  DESC " ;

		if (appStatus.equals("P")) {//pending
			logger.info("PENDING CASE---------------");
			query += " WHERE TRAVEL_APP_STATUS ='A' AND SCH_DTL_SCH_STATUS ='S' AND SCH_APPR_STATUS='P'";
		}

		else if (appStatus.equals("A"))//  approved
		{
			query += " WHERE TRAVEL_APP_STATUS ='A' AND SCH_APPR_STATUS ='A'";
		}

		else if (appStatus.equals("R"))// Rejected
		{
			query += "WHERE TRAVEL_APP_STATUS ='A' AND SCH_APPR_STATUS ='R'";

		}

		boolean chkFlag = callRequistionStatus(bean);

		System.out.println("chkFlag ============= " + chkFlag);
		if (chkFlag == false) {
			query += "AND 1=2";
		}
		if (chkData != null && chkData.length > 0) {
		} else {
			/*query += " AND HRMS_EMP_OFFC.EMP_CENTER IN(SELECT MANG_AUTH_BRANCH FROM HRMS_TMS_MANG_AUTH WHERE MANG_AUTH_SCHEDULER ="
					+ bean.getUserEmpId() + " AND MANG_AUTH_STATUS ='A') ";*/
			query += " AND ((HRMS_EMP_OFFC.EMP_CENTER IN(SELECT MANG_AUTH_BRANCH FROM HRMS_TMS_MANG_AUTH WHERE MANG_AUTH_SCHEDULER ="+bean.getUserEmpId()+" ) AND "  
					+" (SCH_DTL_SCH_APPR_CHKFLAG ='Y' OR SCH_DTL_SCH_APPR_CHKFLAG IS NULL )) OR (SCH_APPR_ALTER ="+bean.getUserEmpId()+"  AND SCH_DTL_SCH_APPR_CHKFLAG ='N' ))";
		}

		query += "  ORDER BY TRAVEL_APP_APPDATE DESC";

		Object[][] result = getSqlModel().getSingleResult(query);
		doPaging(bean, result.length, new Object[][] {}, request);
		int fromTotRec = Integer.parseInt(bean.getFromTotRec());
		int toTotRec = Integer.parseInt(bean.getToTotRec());

		ArrayList travelList = new ArrayList();

		for (int i = fromTotRec; i < toTotRec; i++) {
			TravelScheduleApprover bean1 = new TravelScheduleApprover();
			bean1.setTravelEmpId(checkNull(String.valueOf(result[i][0])));
			bean1.setEmpName(checkNull(String.valueOf(result[i][1])));
			bean1.setTrRequestName(checkNull(String.valueOf(result[i][2])));
			bean1.setAppDate(checkNull(String.valueOf(result[i][3])));
			bean1.setCheckStatus(checkNull(String.valueOf(result[i][4])));
			bean1.setComment(checkNull(String.valueOf(result[i][5])));
			bean1.setTravelAppId(checkNull(String.valueOf(result[i][6])));
			if(String.valueOf(result[i][6]).equals("Y")){
				bean1.setForwardFlag("true");
			}else{
				bean1.setForwardFlag("false");
			} 
			if (status.equals("A"))
				bean1.setApprStatus("Approved");
			if (status.equals("R"))
				bean1.setApprStatus("Rejected");
			travelList.add(bean1);

		} // end of for loop

		bean.setTravelSchApprList(travelList);

		if (travelList.size() == 0) { // bean.setNoData("true");
			bean.setNoData(true);
			logger.info("no data flag========" + bean.isNoData());
		} else {
			bean.setNoData(false);
		}

		if (!status.equals("P")) {
			System.out.println("status checking In Model  " + status);
			bean.setApprflag("true");
			logger.info("Appr Status--------" + bean.getApprflag());
		}

	} // end of callStatus

	public void doPaging(TravelScheduleApprover bean, int empLength,
			Object[][] attnObj, HttpServletRequest request) {
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

	public void callDtl(TravelScheduleApprover bean, String trvlAppId,
			HttpServletRequest request) {
		String appStatus = bean.getStat();
		logger.info("STATUS IN CALLDTL METHOD");

		if (appStatus.equals("P")) {

			setEmpDtls(bean, request);
			setTrvlLocalSchDtls(bean);
			setTotBookingDtls(bean, request);
			setTotLocalConvDtls(bean, request);
			setTotLodgeDtls(bean, request);

		} else if (appStatus.equals("R") || appStatus.equals("A")) {
			setEmpDtls(bean, request);
			setTrvlLocalSchDtls(bean);
			setSchTrvlBookingDtls(bean, request);
			setSchTrvlLocalConvDtls(bean, request);
			setSchTrvlLodgeDtls(bean, request);
		}

	}

	private void setTotBookingDtls(TravelScheduleApprover bean,
			HttpServletRequest request) {
		// booking details iterator
		String query1 = " SELECT C1.LOCATION_NAME || '-' || C2.LOCATION_NAME, APPJOUR_DTL_FROMPALCE, APPJOUR_DTL_TOPLACE ,"
				+ " TO_CHAR(APPJOUR_SCH_TRAVEL_DATE,'DD-MM-YYYY'), APPJOUR_SCH_TRAVEL_TIME,APPJOUR_SCH_JOURMODE,CASE WHEN NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),'')='-' THEN '' ELSE (JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME) END AS JOURNEY_MODE"
				+ " ,NVL(APPJOUR_SCH_VEHICLE_NO,''),NVL(APPJOUR_SCH_TICKET_NO,''),NVL(APPJOUR_SCH_TRAVEL_COST,''),"
				+ " APPJOUR_DTL_ID ,NVL(APPJOUR_SCH_UPLOAD_TICKET,'') FROM HRMS_TMS_APPJOUR_DTL"
				+ " LEFT JOIN  HRMS_LOCATION C1 ON (C1.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_FROMPALCE)"
				+ " LEFT JOIN  HRMS_LOCATION C2 ON (C2.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_TOPLACE)"
				+ " LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_APPJOUR_DTL.APPJOUR_SCH_JOURMODE=HRMS_TMS_JOURNEY.JOURNEY_ID)"
				+ " WHERE  APPJOUR_DTL_TRAPPID =" + bean.getTravelAppId();
		
		Object[][] detl = getSqlModel().getSingleResult(query1);

		if (detl.length > 0 && detl != null) {

			ArrayList trvlList = new ArrayList();
			int j = 1;
			for (int i = 0; i < detl.length; i++) {

				TravelScheduleApprover bean1 = new TravelScheduleApprover();
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
				
				//bean1.setAppJournDtlId(checkNull(String.valueOf(detl[i][11])));
				request.setAttribute("paraFrm_uploadFileName" + i, checkNull(String
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

	private void setTotLocalConvDtls(TravelScheduleApprover bean,
			HttpServletRequest request) {
		// for Local Conveyance Details Iterator

		String locQuery = "SELECT LOCATION_NAME ,NVL(APPLOCAL_DTL_SOURCE,' ') ,APPLOCAL_DTL_CITY ,TO_CHAR(APPLOCAL_DTL_FROM_DATE,'DD-MM-YYYY') AS ST_DATE ,APPLOCAL_SCH_JOUR_MODE"
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

				TravelScheduleApprover locConvBean = new TravelScheduleApprover();

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

	private void setTrvlLocalSchDtls(TravelScheduleApprover bean) {

		String schQuery = "SELECT NVL(SCH_DTL_TRAVEL_COST,0),NVL(SCH_DTL_LOCAL_COST,0),NVL(SCH_DTL_LODG_COST,0),NVL(SCH_DTL_MAIN_SCH_COMMENTS,''),NVL(SCH_APPR_MAIN_COMMENTS,'')"
				+ " FROM HRMS_TMS_SCH_DTL WHERE SCH_DTL_TRAVEL_APP_ID="
				+ bean.getTravelAppId();

		Object[][] schData = getSqlModel().getSingleResult(schQuery);

		if (schData.length > 0 && schData != null) {

			bean.setTotTrvCost(checkNull(String.valueOf(schData[0][0])));
			bean.setTotTariffCost(checkNull(String.valueOf(schData[0][1])));
			bean.setTotLodgCost(checkNull(String.valueOf(schData[0][2])));
			bean.setSchComment(checkNull(String.valueOf(schData[0][3])));
			bean.setSchApprComment(checkNull(String.valueOf(schData[0][4])));
		}
	}

	private void setSchTrvlBookingDtls(TravelScheduleApprover bean,
			HttpServletRequest request) {
		logger.info("I am in setSchTrvlBookingDtls");

		String query = " SELECT NVL(C1.LOCATION_NAME ||'-'||C2.LOCATION_NAME,''),NVL(TO_CHAR(APPJOUR_SCH_TRAVEL_DATE,'DD-MM-YYYY'),'') AS APP_DATE, NVL(APPJOUR_SCH_TRAVEL_TIME,''),"
				+ " CASE WHEN NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),'')='-' THEN '' ELSE (JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME) END AS JOURNEY_MODE,NVL(APPJOUR_SCH_VEHICLE_NO,''),NVL(APPJOUR_SCH_TICKET_NO,''),NVL(APPJOUR_SCH_TRAVEL_COST,''),NVL(APPJOUR_SCH_UPLOAD_TICKET,'')"

				//+ ",NVL(APPJOUR_CANCEL_TRAVEL_COST,0)"
				+ " ,APPJOUR_DTL_ID FROM HRMS_TMS_APPJOUR_DTL"
				+ " LEFT JOIN  HRMS_LOCATION C1 ON (C1.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_FROMPALCE)"
				+ " LEFT JOIN  HRMS_LOCATION C2 ON (C2.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_TOPLACE)"
				+ " LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_APPJOUR_DTL.APPJOUR_SCH_JOURMODE=HRMS_TMS_JOURNEY.JOURNEY_ID)"
				+ " WHERE  APPJOUR_DTL_TRAPPID =" + bean.getTravelAppId();

		Object[][] detl = getSqlModel().getSingleResult(query);

		if (detl.length > 0 && detl != null) {

			ArrayList trvlList = new ArrayList();
			for (int i = 0; i < detl.length; i++) {
				TravelScheduleApprover bean1 = new TravelScheduleApprover();
				bean1.setSourceAndDest(checkNull(String.valueOf(detl[i][0])));
				bean1.setTravelDate(checkNull(String.valueOf(detl[i][1])));
				bean1.setTravelTime(checkNull(String.valueOf(detl[i][2])));
				bean1.setTravelMdAndCls(checkNull(String.valueOf(detl[i][3])));
				bean1.setTrvlVehNo(checkNull(String.valueOf(detl[i][4])));
				bean1.setTicktNo(checkNull(String.valueOf(detl[i][5])));
				bean1.setTicktCost(checkNull(String.valueOf(detl[i][6])));
				bean1.setUploadFileName(checkNull(String.valueOf(detl[i][7])));
				bean1.setAppJournDtlId(checkNull(String.valueOf(detl[i][8])));
				//bean1.setTrvCancAmt(checkNull(String.valueOf(detl[i][9])));
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

	private void setTotLodgeDtls(TravelScheduleApprover bean,
			HttpServletRequest request) {

		// for lodging details Iterator

		String lodgQuery = "SELECT APPLODG_SCH_CITYID,NVL(LOCATION_NAME,''),APPLODG_SCH_HOTEL,NVL(HOTEL_TYPE_NAME,''),APPLODG_SCH_ROOM,NVL(ROOM_TYPE_NAME,'')"
				+ "	,TO_CHAR(APPLODG_SCH_FROMDATE,'DD-MM-YYYY'),NVL(APPLODG_SCH_FROMTIME,''),NVL(TO_CHAR(APPLODG_SCH_TODATE,'DD-MM-YYYY'),''),NVL(APPLODG_SCH_TOTIME,'')"
				+ "	,NVL(APPLODG_SCH_ADDRESS,''),NVL(APPLODG_SCH_BOOKAMT,''),APPLODG_DTL_ID,NVL(APPLODG_SCH_UPLOAD_TICKET,'') FROM HRMS_TMS_APPLODG_DTL"
				+ "	LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = APPLODG_DTL_CITY )"
				+ "	LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_DTL_HOTEL)"
				+ "	LEFT JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_DTL_ROOM)"
				+ "	WHERE APPLODG_DTL_TRAPPID=" + bean.getTravelAppId();

		Object[][] lodgData = getSqlModel().getSingleResult(lodgQuery);

		if (lodgData.length > 0 && lodgData != null) {

			ArrayList lodgeDtlsArr = new ArrayList();
			int j2 = 1;
			for (int i = 0; i < lodgData.length; i++) {

				TravelScheduleApprover lodgeBean = new TravelScheduleApprover();

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

				lodgeBean.setLodgAddrs(checkNull(String
						.valueOf(lodgData[i][10])));

				lodgeBean.setLodgBokAmt(checkNull(String
						.valueOf(lodgData[i][11])));
				lodgeBean.setLodgeDtlId(checkNull(String
						.valueOf(lodgData[i][12])));
				lodgeBean.setLodgUploadFileName(checkNull(String
						.valueOf(lodgData[i][13])));
				
				
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

	private void setSchTrvlLocalConvDtls(TravelScheduleApprover bean,
			HttpServletRequest request) {

		String locQuery = "SELECT NVL(LOCATION_NAME,''),NVL(APPLOCAL_SCH_SOURCE,'') ,NVL(TO_CHAR(APPLOCAL_SCH_CON_DATE,'DD-MM-YYYY'),'') AS ST_DATE "
				+ "	,CASE WHEN NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),'')='-' THEN '' ELSE (JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME) END AS JOURNEY_MODE,NVL(APPLOCAL_SCH_VEHICLE_NO,''), NVL(APPLOCAL_SCH_CON_PERSON,''), "
				+ " NVL(APPLOCAL_SCH_CON_NO,''), NVL(APPLOCAL_SCH_PICKUP_PER,''),"
				+ "	NVL(APPLOCAL_SCH_PICKUP_TIME,''),NVL(APPLOCAL_SCH_PICKUP_PLACE,''),NVL(APPLOCAL_SCH_CON_COST,'') ,APPLOCAL_DTL_ID FROM HRMS_TMS_APPLOCAL_DTL"
				+ "	LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE =HRMS_TMS_APPLOCAL_DTL.APPLOCAL_SCH_CITY)"
				+ "	LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_APPLOCAL_DTL.APPLOCAL_SCH_JOUR_MODE=HRMS_TMS_JOURNEY.JOURNEY_ID)"
				+ "	WHERE APPLOCAL_DTL_TRAPPID =" + bean.getTravelAppId();

		Object[][] locData = getSqlModel().getSingleResult(locQuery);

		if (locData.length > 0 && locData != null) {

			ArrayList trvLocList = new ArrayList();
			for (int i = 0; i < locData.length; i++) {
				TravelScheduleApprover locConvBean = new TravelScheduleApprover();

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
				/*locConvBean.setLocCancAmt(checkNull(String
						.valueOf(locData[i][12])));*/

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

	private void setSchTrvlLodgeDtls(TravelScheduleApprover bean,
			HttpServletRequest request) {

		// for lodging details Iterator

		String lodgQuery = "SELECT NVL(LOCATION_NAME,''),NVL(TO_CHAR(APPLODG_SCH_FROMDATE,'DD-MM-YYYY'),''),NVL(APPLODG_SCH_FROMTIME,''),"
				+ "	NVL(TO_CHAR(APPLODG_SCH_TODATE,'DD-MM-YYYY'),''),NVL(APPLODG_SCH_TOTIME,''),NVL(HOTEL_TYPE_NAME,''),NVL(ROOM_TYPE_NAME,'')"
				+ "	,NVL(APPLODG_SCH_ADDRESS,''),NVL(APPLODG_SCH_BOOKAMT,''),NVL(APPLODG_SCH_UPLOAD_TICKET,'')"
				+ "	,APPLODG_DTL_ID  FROM HRMS_TMS_APPLODG_DTL"
				+ "	LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = APPLODG_SCH_CITYID )"
				+ "	LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_SCH_HOTEL)"
				+ "	LEFT JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_SCH_ROOM)"
				+ "	WHERE APPLODG_DTL_TRAPPID  =" + bean.getTravelAppId();

		Object[][] lodgData = getSqlModel().getSingleResult(lodgQuery);

		if (lodgData.length > 0 && lodgData != null) {

			ArrayList lodgeDtlsArr = new ArrayList();

			for (int i = 0; i < lodgData.length; i++) {

				TravelScheduleApprover lodgeBean = new TravelScheduleApprover();
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
				/*lodgeBean.setLodgCancAmt(checkNull(String
						.valueOf(lodgData[i][11])));*/

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

	private void setEmpDtls(TravelScheduleApprover bean,
			HttpServletRequest request) {
		logger.info("I am in call Deatails");
		String dataQUery = "SELECT 	EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,CENTER_NAME,DEPT_NAME,"
				+ "	TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY') AS APP_DATE,NVL(CADRE_NAME,' '),ROUND((SYSDATE-EMP_DOB)/365) AS DOB"
				+ ",NVL(ADD_MOBILE,' ') ,TRAVEL_APP_EMPID, "
				+ " NVL(TRAVEL_APP_ADVANCE,0),DECODE(TRAVEL_APP_PAYMODE,'C','Cash','S','Salary','Q','Cheque','T','Transfer') AS PAY_MODE"
				+ " ,SCH_APPR_ADVANCE_SAN" 				
				+ " ,TRAVEL_APP_REQUEST,TO_CHAR(TRAVEL_APP_START_DATE,'DD-MM-YYYY') AS START_DTAE, TO_CHAR(TRAVEL_APP_END_DATE,'DD-MM-YYYY') AS END_DATE,NVL(PURPOSE_NAME,'')"
				+" FROM HRMS_TMS_TRAVEL_APP"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID=HRMS_EMP_OFFC.EMP_ID)"
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )"
				+ " LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
				+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )"
				+ " LEFT  JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID =HRMS_EMP_OFFC.EMP_ID)"
				+ " LEFT JOIN  HRMS_TMS_SCH_DTL ON(HRMS_TMS_SCH_DTL.SCH_DTL_TRAVEL_APP_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID) "
				+ "LEFT  JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_PURPOSE) "
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
			bean.setDob(checkNull(String.valueOf(data[0][6])));
			bean.setMobile(checkNull(String.valueOf(data[0][7])));
			bean.setEmpId(checkNull(String.valueOf(data[0][8])));

			if (bean.getStat().equals("P")) {
				bean.setSchAdvAmt(checkNull(String.valueOf(data[0][9])));
				//bean.setSchReqAmount(checkNull(String.valueOf(data[0][9])));
			} else {
				bean.setSchAdvAmt(checkNull(String.valueOf(data[0][11])));
			}

			//bean.setSchReqAmount(checkNull(String.valueOf(data[0][9])));			

			bean.setSchPayMode(checkNull(String.valueOf(data[0][10])));
			// bean.setSchComment(checkNull(String.valueOf(data[0][11])));
			
			
			bean.setTrvlRequest(checkNull(String.valueOf(data[0][12])));
			bean.setTourStrtDate(checkNull(String.valueOf(data[0][13])));
			bean.setTourEndDate(checkNull(String.valueOf(data[0][14])));
			bean.setTourPurpose(checkNull(String.valueOf(data[0][15])));
			

		}

	}

	public boolean changeApplStatus(TravelScheduleApprover trvlSchlApr,
			String travelAppId, String chkStatus, String comments) {
		logger.info("I am in changeApplStatus");
		Object[][] updateApprover = new Object[1][3];
		updateApprover[0][0] = chkStatus;
		updateApprover[0][1] = comments;
		updateApprover[0][2] = travelAppId;
		return getSqlModel().singleExecute(getQuery(1), updateApprover);

	}

	public boolean saveSchlerData(TravelScheduleApprover trvlSchl,
			HttpServletRequest request) {
		logger.info("I am in saveSchlerData");
		//check for the AppId ...if it is there update the table else add new record

		boolean check = checkAppId(trvlSchl);

		if (check) {
			Object addSchlData[][] = new Object[1][11];
			addSchlData[0][0] = checkNull(trvlSchl.getAppID());// Travel Application Id
			addSchlData[0][1] = checkNull(trvlSchl.getUserEmpId());// Main Scheduler
			addSchlData[0][2] = checkNull(trvlSchl.getSchComment());// Main Scheduler comments
			addSchlData[0][3] = "3";// Alternate Scheduler
			addSchlData[0][4] = "COMMENTS";// Alternate Scheduler comments
			//addSchlData[0][5] = "A";// Status
			//addSchlData[0][5] = checkNull(trvlSchl.getCheckStatus());//
			addSchlData[0][5] = checkNull(trvlSchl.getSavingStatus());//ststus
			addSchlData[0][6] = checkNull(trvlSchl.getTotTrvCost());// Total travel cost
			addSchlData[0][7] = checkNull(trvlSchl.getTotTariffCost());// Total local cost
			addSchlData[0][8] = checkNull(trvlSchl.getTotLodgCost());// Total lodging cost
			addSchlData[0][9] = checkNull(trvlSchl.getSchApprComment());//Schedule Approver comments
			addSchlData[0][10] = checkNull(trvlSchl.getSchAdvAmt());//SCH_APPR_ADVANCE_SAN

			return getSqlModel().singleExecute(getQuery(2), addSchlData);
		} else {

			//App Id is there get that id and update the table

			String tableId = getDtlTableId(trvlSchl);

			Object upDateSchlData[][] = new Object[1][12];

			upDateSchlData[0][0] = checkNull(trvlSchl.getAppID());// Travel Application Id
			upDateSchlData[0][1] = checkNull(trvlSchl.getUserEmpId());// Main Scheduler
			upDateSchlData[0][2] = checkNull(trvlSchl.getSchComment());// Main Scheduler comments
			upDateSchlData[0][3] = "3";// Alternate Scheduler
			upDateSchlData[0][4] = "COMMENTS";// Alternate Scheduler comments
			//upDateSchlData[0][5] = "A";// Status
			//upDateSchlData[0][5] = checkNull(trvlSchl.getCheckStatus());//status		
			upDateSchlData[0][5] = checkNull(trvlSchl.getSavingStatus());//ststus
			upDateSchlData[0][6] = checkNull(trvlSchl.getTotTrvCost());// Total travel cost
			upDateSchlData[0][7] = checkNull(trvlSchl.getTotTariffCost());// Total local cost
			upDateSchlData[0][8] = checkNull(trvlSchl.getTotLodgCost());// Total lodging cost			
			upDateSchlData[0][9] = checkNull(trvlSchl.getSchApprComment());//Schedule Approver comments
			upDateSchlData[0][11] = tableId;//Schedule Detail Id
			upDateSchlData[0][10] = checkNull(trvlSchl.getSchAdvAmt());//SCH_APPR_ADVANCE_SAN
			logger.info("aaaaaaaaaaaaaaaaaa" + trvlSchl.getSchAdvAmt());

			return getSqlModel().singleExecute(getQuery(3), upDateSchlData);

		}
	}

	private boolean checkAppId(TravelScheduleApprover bean) {

		String query = "SELECT * FROM HRMS_TMS_SCH_DTL WHERE SCH_DTL_TRAVEL_APP_ID="
				+ bean.getAppID();
		Object[][] Data = getSqlModel().getSingleResult(query);

		if (Data.length > 0 && Data != null)
			return false;
		else
			return true;

	}

	private String getDtlTableId(TravelScheduleApprover bean) {
		String query = "SELECT SCH_DTL_ID FROM HRMS_TMS_SCH_DTL WHERE SCH_DTL_TRAVEL_APP_ID="
				+ bean.getAppID();
		Object[][] Data = getSqlModel().getSingleResult(query);
		if (Data.length > 0 && Data != null)
			return String.valueOf(Data[0][0]);
		else
			return "";

	}

	// methods for updation

	public void upDateBookDtls(TravelScheduleApprover bean,
			HttpServletRequest request) {
		logger.info("I am in upDateBookDtls");
		String[] journFrom = request.getParameterValues("appJournDtlFrom");
		String[] journeyTo = request.getParameterValues("appJournDtlTo");
		String[] jourDate = request.getParameterValues("travelDate");
		String[] travelTime = request.getParameterValues("travelTime");
		String[] vehNo = request.getParameterValues("trvlVehNo");
		String[] tcktNo = request.getParameterValues("ticktNo");
		String[] tcktCost = request.getParameterValues("ticktCost");
		String[] uploadTcktDtls = request.getParameterValues("uploadFileName");
		// mode and upload files
		String[] journeyId = request.getParameterValues("appJournDtlId");

		try {
			Object upDateBook[][] = new Object[1][11];
			Object updateJourTrack[][] = new Object[1][12];
			for (int i = 0; i < journFrom.length; i++) {
				upDateBook[0][0] = checkNull(String.valueOf(journFrom[i]));
				upDateBook[0][1] = checkNull(String.valueOf(journeyTo[i]));
				upDateBook[0][2] = checkNull(String.valueOf(jourDate[i]));
				upDateBook[0][3] = checkNull(String.valueOf(travelTime[i]));
				upDateBook[0][4] = checkNull(String.valueOf(vehNo[i]));
				upDateBook[0][5] = checkNull(String.valueOf(tcktNo[i]));
				upDateBook[0][6] = checkNull(String.valueOf(tcktCost[i]));
				upDateBook[0][7] = checkNull((String) request
						.getParameter("travelMdAndClsId" + (i + 1)));
				upDateBook[0][8] = checkNull(String.valueOf(uploadTcktDtls[i]));				
				upDateBook[0][9] = checkNull(String.valueOf(journeyId[i]));
				upDateBook[0][10] = bean.getAppID();

				//for updation of journey table track tables

				for (int x = 0; x < 11; x++) {
					updateJourTrack[0][x + 1] = upDateBook[0][x];
				}
				updateJourTrack[0][0] = bean.getUserEmpId();

				getSqlModel().singleExecute(getQuery(4), upDateBook);

				getSqlModel().singleExecute(getQuery(5), updateJourTrack);

			}

		} catch (Exception e) {
			logger.info("krishna" + e.getMessage());
		}

	}

	public void upDateLocConDtls(TravelScheduleApprover bean,
			HttpServletRequest request) {

		String[] cityId = request.getParameterValues("locConCityId");
		String[] source = request.getParameterValues("locConSource");
		String[] date = request.getParameterValues("locConDate");
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
				upDateLocCon[0][2] = checkNull(String.valueOf(date[i]));
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

				getSqlModel().singleExecute(getQuery(6), upDateLocCon);
				getSqlModel().singleExecute(getQuery(7), updateLocConTrack);

			}
		} catch (Exception e) {
			logger.info("krishna-------------------" + e.getMessage());
		}

	}

	public void upDateLodgDtls(TravelScheduleApprover bean,
			HttpServletRequest request) {
		logger.info("I am in upDateBookDtls");
		String[] frmDate = request.getParameterValues("lodgFrmDate");
		String[] chkInTm = request.getParameterValues("lodgChkInTm");
		String[] toDate = request.getParameterValues("lodgToDate");
		String[] chkOutTm = request.getParameterValues("lodgChkOutTm");
		String[] addr = request.getParameterValues("lodgAddrs");
		String[] bookAmt = request.getParameterValues("lodgBokAmt");
		String[] lodgUploadTcktDtls = request.getParameterValues("lodgUploadFileName");
		String[] lodgeDtlId = request.getParameterValues("lodgeDtlId");

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
				/*upDateLodg[0][9] = checkNull((String) request
						.getParameter("lodgUploadFileName" + (i + 1)));// upLoad files
				*/
				
				upDateLodg[0][9] = checkNull(String.valueOf(lodgUploadTcktDtls[i]));
				
				upDateLodg[0][10] = checkNull(String.valueOf(lodgeDtlId[i]));
				upDateLodg[0][11] = bean.getAppID();

				for (int z = 0; z < 12; z++) {
					updateLodgTrack[0][z + 1] = upDateLodg[0][z];
				}
				updateLodgTrack[0][0] = bean.getUserEmpId();

				getSqlModel().singleExecute(getQuery(8), upDateLodg);

				getSqlModel().singleExecute(getQuery(9), updateLodgTrack);

			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("krishna" + e.getMessage());
		}

	}

	public boolean callRequistionStatus(TravelScheduleApprover bean) {
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

	public void saveAdvancceDtls(TravelScheduleApprover trvlSchlApr) {
		logger.info("saving advance details.....");
		logger.info("Requested Amonut------" + trvlSchlApr.getSchReqAmount());
		logger.info("Issued Amount-----" + trvlSchlApr.getSchAdvAmt());
		logger.info("empId--------" + trvlSchlApr.getEmpId());
		logger.info("Travel Application Id" + trvlSchlApr.getTravelAppId());

		Object saveAdvDtl[][] = new Object[1][3];
		saveAdvDtl[0][0] = checkNull(String.valueOf(trvlSchlApr
				.getSchReqAmount()));
		//saveAdvDtl[0][1] = checkNull(String.valueOf(trvlSchlApr.getSchAdvAmt()));
		saveAdvDtl[0][1] = checkNull(String.valueOf(trvlSchlApr.getEmpId()));
		saveAdvDtl[0][2] = checkNull(String.valueOf(trvlSchlApr
				.getTravelAppId()));
		getSqlModel().singleExecute(getQuery(10), saveAdvDtl);

	}
	
	public void callForward(TravelScheduleApprover bean){
		String sql ="  UPDATE HRMS_TMS_SCH_DTL SET SCH_DTL_SCH_APPR_CHKFLAG ='N' ,SCH_APPR_MAIN_COMMENTS ='"+bean.getSchAppComment()+"',SCH_APPR_ALTER ="+bean.getAltSchEmpId()+" WHERE SCH_DTL_TRAVEL_APP_ID = "+bean.getTrvAppId();
		getSqlModel().singleExecute(sql);
	}

}
