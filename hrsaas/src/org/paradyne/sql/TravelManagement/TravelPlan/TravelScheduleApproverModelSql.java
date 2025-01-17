package org.paradyne.sql.TravelManagement.TravelPlan;

import org.paradyne.lib.SqlBase;

/**
 * 
 * @author Krishna
 *
 */

public class TravelScheduleApproverModelSql extends SqlBase {
	public String getQuery(int id) {

		switch (id) {

		case 1:
			return "UPDATE HRMS_TMS_SCH_DTL  SET SCH_APPR_STATUS=? ,SCH_APPR_MAIN_COMMENTS=? WHERE SCH_DTL_TRAVEL_APP_ID =?";

		case 2:
			return "INSERT INTO HRMS_TMS_SCH_DTL(SCH_DTL_ID, SCH_DTL_TRAVEL_APP_ID, SCH_DTL_MAIN_SCHEDULER,"
					+ " SCH_DTL_MAIN_SCH_COMMENTS, SCH_DTL_ALTER_SCHEDULER, SCH_DTL_ALTER_SCH_COMMENTS, "
					+ " SCH_DTL_SCH_STATUS, SCH_DTL_TRAVEL_COST, SCH_DTL_LOCAL_COST, "
					+ " SCH_DTL_LODG_COST,SCH_APPR_MAIN_COMMENTS,SCH_APPR_ADVANCE_SAN) VALUES "
					+ "  ((SELECT NVL(MAX(SCH_DTL_ID),0)+1 FROM HRMS_TMS_SCH_DTL ),?,?,?,?,?,?,?,?,?,?,?) ";

		case 3:
			return " UPDATE HRMS_TMS_SCH_DTL SET SCH_DTL_TRAVEL_APP_ID=?,SCH_DTL_MAIN_SCHEDULER=?,SCH_DTL_MAIN_SCH_COMMENTS=?,SCH_DTL_ALTER_SCHEDULER=?,"

					+ " SCH_DTL_ALTER_SCH_COMMENTS=?,SCH_APPR_STATUS=?,SCH_DTL_TRAVEL_COST=?,SCH_DTL_LOCAL_COST=?,SCH_DTL_LODG_COST=?,SCH_APPR_MAIN_COMMENTS=? ,"

					+ " SCH_APPR_ADVANCE_SAN=? WHERE SCH_DTL_ID=?";

		case 4:
			return " UPDATE HRMS_TMS_APPJOUR_DTL SET APPJOUR_DTL_FROMPALCE=?,APPJOUR_DTL_TOPLACE=?,APPJOUR_DTL_JOURDATE=TO_DATE(?,'DD-MM-YYYY'),APPJOUR_DTL_JOURTIME=?,"

					+ "	APPJOUR_SCH_VEHICLE_NO=?,APPJOUR_SCH_TICKET_NO=?,APPJOUR_SCH_TRAVEL_COST=?,APPJOUR_SCH_JOURMODE=?,APPJOUR_SCH_UPLOAD_TICKET=?"

					+ "	WHERE APPJOUR_DTL_ID=? AND APPJOUR_DTL_TRAPPID=?";

		case 5:
			return " UPDATE HRMS_TMS_APPJOUR_DTL_TRACK SET APPJOUR_DTL_MODIFIED_BY=?, APPJOUR_DTL_FROMPALCE=?,APPJOUR_DTL_TOPLACE=?,APPJOUR_DTL_JOURDATE=TO_DATE(?,'DD-MM-YYYY'),APPJOUR_DTL_JOURTIME=?,"

					+ "	APPJOUR_SCH_VEHICLE_NO=?,APPJOUR_SCH_TICKET_NO=?,APPJOUR_SCH_TRAVEL_COST=?,APPJOUR_SCH_JOURMODE=?,APPJOUR_SCH_UPLOAD_TICKET=?"

					+ "	WHERE APPJOUR_DTL_ID=? AND APPJOUR_DTL_TRAPPID=?";
		case 6:
			return "UPDATE HRMS_TMS_APPLOCAL_DTL SET APPLOCAL_SCH_CITY=?,APPLOCAL_DTL_SOURCE=?,APPLOCAL_SCH_CON_DATE=TO_DATE(?,'DD-MM-YYYY'),APPLOCAL_SCH_VEHICLE_NO=?,"

					+ "	APPLOCAL_SCH_CON_PERSON=?,APPLOCAL_SCH_CON_NO=?,APPLOCAL_SCH_PICKUP_PER=?,APPLOCAL_SCH_PICKUP_TIME=?,APPLOCAL_SCH_PICKUP_PLACE=? ,APPLOCAL_SCH_CON_COST=?,APPLOCAL_SCH_JOUR_MODE=?"

					+ "	WHERE  APPLOCAL_DTL_ID=? AND APPLOCAL_DTL_TRAPPID=?";
		case 7:
			return "UPDATE HRMS_TMS_APPLOCAL_DTL_TRACK SET  APPLOCAL_DTL_MODIFIED_BY=?,APPLOCAL_SCH_CITY=?,APPLOCAL_DTL_SOURCE=?,APPLOCAL_SCH_CON_DATE=TO_DATE(?,'DD-MM-YYYY'),APPLOCAL_SCH_VEHICLE_NO=?,"

					+ "	APPLOCAL_SCH_CON_PERSON=?,APPLOCAL_SCH_CON_NO=?,APPLOCAL_SCH_PICKUP_PER=?,APPLOCAL_SCH_PICKUP_TIME=?,APPLOCAL_SCH_PICKUP_PLACE=? ,APPLOCAL_SCH_CON_COST=?,APPLOCAL_SCH_JOUR_MODE=?"

					+ "	WHERE  APPLOCAL_DTL_ID=? AND APPLOCAL_DTL_TRAPPID=?";
		case 8:
			return "UPDATE HRMS_TMS_APPLODG_DTL SET APPLODG_SCH_FROMDATE=TO_DATE(?,'DD-MM-YYYY'),APPLODG_SCH_FROMTIME=?,APPLODG_DTL_TODATE=TO_DATE(?,'DD-MM-YYYY'),APPLODG_DTL_TOTIME=?,"

					+ " APPLODG_SCH_ADDRESS=?,APPLODG_SCH_BOOKAMT=?,APPLODG_SCH_CITYID=?,APPLODG_SCH_HOTEL=?,APPLODG_SCH_ROOM=? ,APPLODG_SCH_UPLOAD_TICKET=?"

					+ " 	WHERE APPLODG_DTL_ID=? AND APPLODG_DTL_TRAPPID=? ";
		case 9:
			return "UPDATE HRMS_TMS_APPLODG_DTL_TRACK SET APPLOCAL_DTL_MODIFIED_BY=?, APPLODG_SCH_FROMDATE=TO_DATE(?,'DD-MM-YYYY'),APPLODG_SCH_FROMTIME=?,APPLODG_DTL_TODATE=TO_DATE(?,'DD-MM-YYYY'),APPLODG_DTL_TOTIME=?,"

					+ " APPLODG_SCH_ADDRESS=?,APPLODG_SCH_BOOKAMT=?,APPLODG_SCH_CITYID=?,APPLODG_SCH_HOTEL=?,APPLODG_SCH_ROOM=? ,APPLODG_SCH_UPLOAD_TICKET=?"

					+ " 	WHERE APPLODG_DTL_ID=? AND APPLODG_DTL_TRAPPID=? ";

		case 10:
			return "INSERT INTO HRMS_TMS_ADVANCE(ADVANCE_ID, ADVANCE_REQUEST_AMT,"
					+ " ADVANCE_EMP_ID, ADVANCE_TRAPP_ID) VALUES "
					+ "  ((SELECT NVL(MAX(ADVANCE_ID),0)+1 FROM HRMS_TMS_ADVANCE ),?,?,?) ";

		default:
			return " Framework could not find query number sepcified";

		}

	}

}
