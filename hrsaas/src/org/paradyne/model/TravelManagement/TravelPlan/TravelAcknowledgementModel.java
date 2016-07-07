package org.paradyne.model.TravelManagement.TravelPlan;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelPlan.TravelAcknowledgement;
import org.paradyne.lib.ModelBase;

/**
 * @author Dilip
 *  
 */
public class TravelAcknowledgementModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	

	public void callStatus(TravelAcknowledgement bean,
			HttpServletRequest request, String status) {
		bean.setStat(status);
		
		String Query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,TRAVEL_APP_REQUEST, "
				+ " TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY'),TRAVEL_APP_EMPID,TRAVEL_APP_ID," ;
		if(bean.getChkFlag().equals("true")){
			Query += " HRMS_TMS_TRAVEL_APP.TRAVEL_APLT_CONF_STATUS,";
		}else
		{ Query += " HRMS_TMS_TRAVEL_APP.TRAVEL_CANCEL_STATUS,";
		} 	 
		Query += " nvl(TRAVEL_APLT_CONF_COMMENTS,'')"
				+ "  FROM HRMS_TMS_TRAVEL_APP"
				+ "  LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID )"
				+ "  INNER JOIN HRMS_TMS_SCH_DTL  ON (HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID = HRMS_TMS_SCH_DTL.SCH_DTL_TRAVEL_APP_ID AND HRMS_TMS_SCH_DTL.SCH_APPR_STATUS ='A')"
				+ " WHERE 1=1";
		if (status.equals("P") ) {
				Query += " AND   HRMS_TMS_TRAVEL_APP.TRAVEL_APLT_CONF_STATUS ='P'  ";
		}
		if (status.equals("A") && bean.getChkFlag().equals("true") ) {
			Query += " AND HRMS_TMS_TRAVEL_APP.TRAVEL_APLT_CONF_STATUS ='A'";
		}
		if (status.equals("R") && bean.getChkFlag().equals("true") ) {
			Query += " AND HRMS_TMS_TRAVEL_APP.TRAVEL_APLT_CONF_STATUS ='R'";
		}
		//========= 
		if (status.equals("A") && bean.getChkFlag().equals("false") ) {
			Query += " AND HRMS_TMS_TRAVEL_APP.TRAVEL_CANCEL_STATUS ='A'";
		}
		if (status.equals("R") && bean.getChkFlag().equals("false") ) {
			Query += " AND HRMS_TMS_TRAVEL_APP.TRAVEL_CANCEL_STATUS ='R'";
		}
		Query += " AND TRAVEL_APP_EMPID="+bean.getUserEmpId();
		Query += " ORDER  BY TRAVEL_APP_APPDATE DESC ";
		
		Object[][] result = getSqlModel().getSingleResult(Query);
		doPaging(bean, result.length, new Object[][] {}, request);
		int fromTotRec = Integer.parseInt(bean.getFromTotRec());
		int toTotRec = Integer.parseInt(bean.getToTotRec());

		ArrayList travelList = new ArrayList();
		for (int i = fromTotRec; i < toTotRec; i++) {
		TravelAcknowledgement bean1 = new TravelAcknowledgement();
		// ============================= for iteratror========
			bean1.setEmpName(String.valueOf(result[i][0]));
			bean1.setTrRequestName(String.valueOf(result[i][1]));
			bean1.setAppDate(String.valueOf(result[i][2]));
			bean1.setTravelEmpId(String.valueOf(result[i][3]));
			bean1.setTravelAppId(String.valueOf(result[i][4]));
			bean1.setComment(checkNull(String.valueOf(result[i][6])));
			bean1.setCheckStatus(String.valueOf(result[i][5]));
			travelList.add(bean1);
			if (status.equals("A")) {
				bean1.setStatusNew("Accepted");
				bean1.setTestFlag("true");
			} // end of if
			if (status.equals("R")) {
				bean1.setStatusNew("Rejected");
				bean1.setTestFlag("true");
			} // end of if
			
		} // end of for loop
		bean.setTravelList(travelList);
		if (travelList.size() == 0) {
			bean.setListLength("0");
			bean.setNoData("true");
		} else {
			bean.setListLength("1");
			bean.setNoData("false");
		}

	}

	public boolean getUpdateStatus(TravelAcknowledgement travelAcknowledgement,
			String status, String travelId, String comment) {
		// TODO Auto-generated method stub
				boolean flag = false;
			String Query = "UPDATE HRMS_TMS_TRAVEL_APP  SET TRAVEL_APLT_CONF_STATUS ='"	+ status+ "',TRAVEL_APLT_CONF_COMMENTS='"+ comment+"'";
				        if(status.equals("R"))
				        {			        
				        	 Query+= ",TRAVEL_CANCEL_STATUS ='P'";
				        }
				      Query+= "WHERE  TRAVEL_APP_ID =" + travelId + " ";
					 flag = getSqlModel().singleExecute(Query);
		     		return flag;
			       }

	public void callDetail(TravelAcknowledgement travelAcknowledgement,
			HttpServletRequest request) {

		try {

			String Query = "SELECT  distinct EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,"
					+ "   CENTER_NAME,DEPT_NAME,NVL(CADRE_NAME,' '),TO_CHAR(SYSDATE,'DD-MM-YYYY'),nvl(ADD_MOBILE,'') FROM HRMS_EMP_OFFC"
					+ "   LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE )"
					+ "   INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER )"
					+ "  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )"
					+ "  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )"
					+ "   LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
					+ "   left join HRMS_TMS_TRAVEL_APP  on(HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID=HRMS_EMP_OFFC.EMP_ID)"
					+ " left JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ "  WHERE  TRAVEL_APP_EMPID ="
					+ travelAcknowledgement.getTravelEmpId()
					+ " and TRAVEL_APP_ID="
					+ travelAcknowledgement.getTravelAppId() + "";

			Object[][] data = getSqlModel().getSingleResult(Query);

			if (data != null && data.length > 0)

			{
				travelAcknowledgement.setEmpToken(String.valueOf(data[0][0]));
				travelAcknowledgement.setEName(String.valueOf(data[0][1]));
				travelAcknowledgement.setBranchName(String.valueOf(data[0][2]));
				travelAcknowledgement.setDept(String.valueOf(data[0][3]));
				travelAcknowledgement.setGrade(String.valueOf(data[0][4]));
				travelAcknowledgement.setADate(String.valueOf(data[0][5]));
				travelAcknowledgement.setContactNo(checkNull(String
						.valueOf(data[0][6])));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

	public String checkNull(String result)
			{
				if (result == null || result.equals("null")) {
					return "";
				} else {
					return result;
				}
		
			}

	public void doPaging(TravelAcknowledgement bean, int empLength,
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

	public void travelDetail(TravelAcknowledgement travelAcknowledgement,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		
		String query = " SELECT   NVL(C1.LOCATION_NAME ||'-'||C2.LOCATION_NAME,''),TO_CHAR(APPJOUR_DTL_JOURDATE,'DD-MM-YYYY'),"
				+ "	APPJOUR_DTL_JOURTIME,CASE WHEN JOURNEY_NAME IS NOT NULL THEN NVL(JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME,' ') ELSE"
                +" NVL(JOURNEY_CLASS_NAME,' ') END,NVL(APPJOUR_SCH_VEHICLE_NO,''),"
				+ " NVL(APPJOUR_SCH_TICKET_NO,''),NVL(APPJOUR_SCH_UPLOAD_TICKET,' ')"
				+ "	FROM HRMS_TMS_APPJOUR_DTL"
				+ "	LEFT JOIN  HRMS_LOCATION C1 ON (C1.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_FROMPALCE)"
				+ "	LEFT JOIN  HRMS_LOCATION C2 ON (C2.LOCATION_CODE = HRMS_TMS_APPJOUR_DTL.APPJOUR_DTL_TOPLACE)"
				+ "	LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_APPJOUR_DTL.APPJOUR_SCH_JOURMODE=HRMS_TMS_JOURNEY.JOURNEY_ID) "
				+ "	WHERE  APPJOUR_DTL_TRAPPID = "
				+ travelAcknowledgement.getTravelAppId() + "";
		
		Object[][] data = getSqlModel().getSingleResult(query);
		logger.info(" travel Details=========="+data);
		ArrayList<Object> list = new ArrayList<Object>();
	if(data.length>0 && data!=null){
			travelAcknowledgement.setTravelFlag("true");
			for (int i = 0; i < data.length; i++) {
				TravelAcknowledgement bean = new TravelAcknowledgement();
	
				bean.setItSource(checkNull(String.valueOf(data[i][0])));
				bean.setItJourneyDate(checkNull(String.valueOf(data[i][1])));
				bean.setItTime(checkNull(String.valueOf(data[i][2])));
				bean.setItMode(checkNull(String.valueOf(data[i][3])));
				bean.setItBTFNumber(checkNull(String.valueOf(data[i][4])));
				bean.setItTicketNumber(checkNull(String.valueOf(data[i][5])));
				bean.setUploadTravelTicket(checkNull(String.valueOf(data[i][6])));
				list.add(bean);
			 }
				travelAcknowledgement.setTravelDtlList(list);
	}else{
		travelAcknowledgement.setTravelFlag("false");
}
		if (list.size() == 0) {
			travelAcknowledgement.setListLength("0");
			travelAcknowledgement.setNoData("true");
		} else {
			travelAcknowledgement.setListLength("1");
			travelAcknowledgement.setNoData("false");
		}
	}

	public void conveyDetail(TravelAcknowledgement travelAcknowledgement,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String Query = "SELECT   NVL(APPLOCAL_SCH_SOURCE,'') ,NVL(TO_CHAR(APPLOCAL_SCH_CON_DATE,'DD-MM-YYYY'),'') AS ST_DATE "
				+ ",CASE WHEN JOURNEY_CLASS_NAME is not null THEN NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),' ')" 
                +" ELSE NVL(JOURNEY_NAME,' ') end AS JOURNEY_MODE,NVL(APPLOCAL_SCH_VEHICLE_NO,''), "
				+ " NVL(APPLOCAL_SCH_CON_PERSON,''),NVL(APPLOCAL_SCH_CON_NO,''), NVL(APPLOCAL_SCH_PICKUP_PER,''),NVL(APPLOCAL_SCH_PICKUP_PLACE,''),"
				+ "	NVL(APPLOCAL_SCH_PICKUP_TIME,'')FROM HRMS_TMS_APPLOCAL_DTL	"
				+ "	LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE =HRMS_TMS_APPLOCAL_DTL.APPLOCAL_SCH_CITY)"
				+ "	LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_APPLOCAL_DTL.APPLOCAL_SCH_JOUR_MODE=HRMS_TMS_JOURNEY.JOURNEY_ID)"
				+ "	WHERE APPLOCAL_DTL_TRAPPID  ="
				+ travelAcknowledgement.getTravelAppId() + "";

		Object[][] result = getSqlModel().getSingleResult(Query);
		logger.info(" conveyDetail------------------"+result);
		ArrayList<Object> List = new ArrayList<Object>();
		if(result.length>0 && result!=null){
			travelAcknowledgement.setConvenceFlag("true");
		for (int i = 0; i < result.length; i++) {
			TravelAcknowledgement bean1 = new TravelAcknowledgement();
			bean1.setSourcedt(checkNull(String.valueOf(result[i][0])));
			bean1.setDate(checkNull(String.valueOf(result[i][1])));
			bean1.setTravelMode(checkNull(String.valueOf(result[i][2])));
			bean1.setModeNumber(checkNull(String.valueOf(result[i][3])));
			bean1.setCntPerson(checkNull(String.valueOf(result[i][4])));
			bean1.setCntNumber(checkNull(String.valueOf(result[i][5])));
			bean1.setPickPerson(checkNull(String.valueOf(result[i][6])));
			bean1.setPickPlace(checkNull(String.valueOf(result[i][7])));
			bean1.setPickTime(checkNull(String.valueOf(result[i][8])));
			List.add(bean1);

		}
		travelAcknowledgement.setConveyList(List);
		}else{
			travelAcknowledgement.setConvenceFlag("false");
		}
		if (List.size() == 0) {
			travelAcknowledgement.setListLength("0");
			travelAcknowledgement.setNoData("true");
		} else {
			travelAcknowledgement.setListLength("1");
			travelAcknowledgement.setNoData("false");
		}
		
	}

	public void lodgingDetail(TravelAcknowledgement travelAcknowledgement,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String query = " SELECT NVL(LOCATION_NAME,''),NVL(TO_CHAR(APPLODG_SCH_FROMDATE,'DD-MM-YYYY'),''),NVL(APPLODG_SCH_FROMTIME,''),"
				+ " NVL(TO_CHAR(APPLODG_SCH_TODATE,'DD-MM-YYYY'),''),NVL(APPLODG_SCH_TOTIME,''),NVL(HOTEL_TYPE_NAME,''),NVL(ROOM_TYPE_NAME,'')"
				+ ",NVL(APPLODG_SCH_ADDRESS,''),NVL(APPLODG_SCH_UPLOAD_TICKET,' ')"
				+ " FROM HRMS_TMS_APPLODG_DTL"
				+ "	LEFT JOIN  HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = APPLODG_SCH_CITYID )"
				+ " LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_SCH_HOTEL)"
				+ " LEFT JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID=HRMS_TMS_APPLODG_DTL.APPLODG_SCH_ROOM)"
				+ " WHERE APPLODG_DTL_TRAPPID  ="
				+ travelAcknowledgement.getTravelAppId() + "";

		Object[][] data = getSqlModel().getSingleResult(query);
		ArrayList<Object> logList = new ArrayList<Object>();
		if(data.length>0 && data!=null){
			travelAcknowledgement.setLodgeFlag("true");
		for (int i = 0; i < data.length; i++) {
			TravelAcknowledgement bean2 = new TravelAcknowledgement();

			bean2.setCity(checkNull(String.valueOf(data[i][0])));
			bean2.setFromDate(checkNull(String.valueOf(data[i][1])));
			bean2.setCheckIn(checkNull(String.valueOf(data[i][2])));
			bean2.setToDate(checkNull(String.valueOf(data[i][3])));
			bean2.setCheckOut(checkNull(String.valueOf(data[i][4])));
			bean2.setHotelType(checkNull(String.valueOf(data[i][5])));
			bean2.setRoomType(checkNull(String.valueOf(data[i][6])));
			bean2.setAddress(checkNull(String.valueOf(data[i][7])));
			bean2.setLodgeticketDeatails((checkNull(String.valueOf(data[i][8]))));
			
			logList.add(bean2);

		}
		travelAcknowledgement.setLodgeList(logList);
		}
		else{
			travelAcknowledgement.setLodgeFlag("false");
			
		}
		if (logList.size() == 0) {
			travelAcknowledgement.setListLength("0");
			travelAcknowledgement.setNoData("true");
		} else {
			travelAcknowledgement.setListLength("1");
			travelAcknowledgement.setNoData("false");
		}
		
	}

	public void travelAdvance(TravelAcknowledgement travelAcknowledgement,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String query = "SELECT nvl(SCH_APPR_ADVANCE_SAN,0),DECODE(TRAVEL_APP_PAYMODE,'C','CASH','T','Transfer','Q','Cheque'),nvl(SCH_DTL_MAIN_SCH_COMMENTS||' '||SCH_DTL_ALTER_SCH_COMMENTS,'')"
				+ "	 FROM HRMS_TMS_TRAVEL_APP"
				+ "	INNER JOIN HRMS_TMS_SCH_DTL  ON (HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID= HRMS_TMS_SCH_DTL.SCH_DTL_TRAVEL_APP_ID)"
				+ "	 where TRAVEL_APP_ID="
				+ travelAcknowledgement.getTravelAppId() + " ";
		logger.info(" ==========call Modi========== " + query);
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			travelAcknowledgement.setAddAmount(checkNull(String
					.valueOf(data[0][0])));
			travelAcknowledgement.setPayMode(checkNull(String
					.valueOf(data[0][1])));
			travelAcknowledgement.setSchComment(checkNull(String
					.valueOf(data[0][2])));
			}
	}

	public void reject(TravelAcknowledgement travelAcknowledgement,
			String travelId) {
		// TODO Auto-generated method stub
		boolean flag = false;

		String Query = "UPDATE HRMS_TMS_TRAVEL_APP  SET TRAVEL_APLT_CONF_STATUS ='R'"
				+ " WHERE  TRAVEL_APP_ID =" + travelId + "  ";
		flag = getSqlModel().singleExecute(Query);

	}

	public void pending(TravelAcknowledgement travelAcknowledgement,
			String travelId) {
		// TODO Auto-generated method stub
		boolean flag = false;

		String Query = "UPDATE HRMS_TMS_TRAVEL_APP  SET TRAVEL_APLT_CONF_STATUS ='P'"
				+ " WHERE  TRAVEL_APP_ID =" + travelId + "  ";
			flag = getSqlModel().singleExecute(Query);
	}

	public void accept(TravelAcknowledgement travelAcknowledgement,
			String travelId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String Query = "UPDATE HRMS_TMS_TRAVEL_APP  SET TRAVEL_APLT_CONF_STATUS ='A'"
				+ " WHERE  TRAVEL_APP_ID =" + travelId + "  ";
		flag = getSqlModel().singleExecute(Query);
	}
	
	public boolean upDateStatus(TravelAcknowledgement bean,String travelId ,String comment) {
		String Query = "UPDATE HRMS_TMS_TRAVEL_APP  SET TRAVEL_APLT_CONF_STATUS ='"+bean.getStat()+"'"
				+ ",TRAVEL_APLT_CONF_COMMENTS='"+comment+"',TRAVEL_CANCEL_STATUS='P' WHERE  TRAVEL_APP_ID =" + travelId + "  ";
    		return getSqlModel().singleExecute(Query);
	}

	public void travelTicket(TravelAcknowledgement travelAcknowledgement) {
		// TODO Auto-generated method stub
		String query="SELECT NVL(APPJOUR_SCH_UPLOAD_TICKET,'') FROM HRMS_TMS_APPJOUR_DTL" 
			+"  WHERE APPJOUR_DTL_TRAPPID="+travelAcknowledgement.getTravelAppId()+"   ";
		Object[][] data=getSqlModel().getSingleResult(query);
		travelAcknowledgement.setItTicketNumber(String.valueOf(data[0][0]));
		
	}

}
