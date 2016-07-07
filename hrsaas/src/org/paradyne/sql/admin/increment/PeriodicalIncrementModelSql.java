package org.paradyne.sql.admin.increment;

import org.paradyne.lib.SqlBase;

/**
 * @author Radha Saoji 
 *
 */
public class PeriodicalIncrementModelSql extends SqlBase {
	
	public String getQuery(int queryID){
			
			switch(queryID){
			
			case 1: return "SELECT HRMS_INCR_HDR.EMP_ID,TO_CHAR(TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),INCR_NEW_BASIC,"
						+"	TRADE_NAME,CENTER_NAME,TO_CHAR(INCR_CURR_DATE,'DD-MM-YYYY'),TO_CHAR(INCR_EFFECTIVE_DATE,'DD-MM-YYYY'),INCR_PAY_BASIC FROM HRMS_INCR_HDR"
						+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_INCR_HDR.EMP_ID)"
						+"  LEFT JOIN HRMS_TRADE ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRADE.TRADE_CODE)"
						+"	LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CENTER.CENTER_ID)"
						+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
						+"	WHERE HRMS_INCR_HDR.EMP_ID=?"
						+"	ORDER BY HRMS_INCR_HDR.EMP_ID";
										
			
			default: return "QUERY NOT FOUND";
			}
	}
}