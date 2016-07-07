package org.paradyne.sql.conference;

import org.paradyne.lib.SqlBase;

public class ConferenceBookingMisReportModelSql extends SqlBase {
	
	public String getQuery(int id){
		switch (id){
			case 1 : return "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
							+"TO_CHAR(CONF_DATE, 'DD-MM-YYYY'), CONF_FROM_TIME , CONF_TO_TIME, HRMS_CONF_VENUE.VENUE_NAME, "
							+"NVL(CONF_PURPOSE,' '), CONF_PARTICIPANT, CONF_CODE "
							+"FROM HRMS_CONF_HDR "
							+"INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_HDR.CONF_BOOKEDBY) "
							+"INNER JOIN HRMS_CONF_VENUE ON (HRMS_CONF_VENUE.VENUE_CODE = CONF_ROOMNO) ";
							//+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) ";
			
			case 2 : return "SELECT ACCESSORY_CODE, ACCESSORY_NAME, NVL(CONF_ACC_QTY,0) "
							+"FROM HRMS_CONF_ACCESORIES "
							+"LEFT JOIN HRMS_CONF_DTL ON (HRMS_CONF_ACCESORIES.ACCESSORY_CODE = HRMS_CONF_DTL.CONF_ACC_CODE " 
							+"AND CONF_CODE=?) WHERE  CONF_ACC_REQUIRED_FLAG = 'Y' "
							+"ORDER BY ACCESSORY_CODE";
			
			default : return "";
		}
	}

}
