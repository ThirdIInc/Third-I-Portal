/**
 * 
 */
package org.paradyne.sql.appraisal;

import org.paradyne.lib.SqlBase;

/**
 * @author balajim
 *
 */
public class ApprisalCommentModelSql extends SqlBase {
	
	public String getQuery(int code) {

		switch (code) {
		case 1 : return  "  SELECT PRD_START_YEAR,PRD_END_YEAR,TO_CHAR(START_DATE,'DD-MM-YYYY'),TO_CHAR(END_DATE,'DD-MM-YYYY'),CONF_CODE FROM HRMS_APPRCONFIG_PRD_HDR " 
						+" WHERE TO_DATE(SYSDATE,'DD-MM-YYYY') BETWEEN  TO_DATE(START_DATE,'DD-MM-YYYY') AND TO_DATE(END_DATE,'DD-MM-YYYY') "
						+" AND CONF_CODE = (SELECT MAX(CONF_CODE) FROM HRMS_APPRCONFIG_PRD_HDR )";

		default: return "Framework not found ";
		}
	}

}
