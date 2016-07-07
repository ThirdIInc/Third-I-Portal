/**
 * 
 */
package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class MailEventsMasterModelSql extends SqlBase {
	
	public String getQuery(int id){
		
		switch(id){
		

		case 1:
			return " INSERT INTO HRMS_MAIL_EVENTS (EVENT_CODE,EVENT_NAME, EVENT_DESC,EVENT_MODULE_CODE)"
					+ "VALUES((Select NVL(MAX(EVENT_CODE),0)+1 from HRMS_MAIL_EVENTS),?,?,?)";

		case 2:
			return " UPDATE HRMS_MAIL_EVENTS SET EVENT_NAME=?,EVENT_DESC=?,EVENT_MODULE_CODE=?  WHERE EVENT_CODE=?";

		case 3:
			return " DELETE FROM HRMS_MAIL_EVENTS WHERE EVENT_CODE=?";

		case 4:
			return "  select EVENT_CODE,EVENT_NAME,HRMS_MODULE.MODULE_NAME,EVENT_DESC from HRMS_MAIL_EVENTS "
					 +" inner join HRMS_MODULE on (HRMS_MODULE.MODULE_CODE=HRMS_MAIL_EVENTS.EVENT_MODULE_CODE) "
					 +" order by EVENT_CODE";

		/*
		case 5:
			return " INSERT INTO HRMS_REC_MAIL_CONF  (MAIL_EVENT_CODE )"
					+ "VALUES((Select max(nvl(MAIL_EVENT_CODE,0))+1 from HRMS_REC_MAIL_CONF))";
*/
		
		default: return "query no. is not specified ";
		}
		
		
	}

}
