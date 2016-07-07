package org.paradyne.sql.admin.master;
import org.paradyne.lib.SqlBase;
/*
 * Pradeep Kumar Sahoo
 * Date:28.06.2007
 */

public class AwardMasterModelSql extends SqlBase {
	public String getQuery(int id) 
	{
		switch(id)
		{
		case 1: return " INSERT INTO HRMS_AWARD_MASTER(AWARD_CODE,AWARD_TYPE,IS_ACTIVE) "
						+" VALUES((SELECT NVL(MAX(AWARD_CODE),0)+1 FROM HRMS_AWARD_MASTER),?,?) " ;
		
		case 2: return "UPDATE HRMS_AWARD_MASTER SET AWARD_TYPE = ? , IS_ACTIVE=? WHERE AWARD_CODE = ? " ;
		
		case 3: return "DELETE FROM HRMS_AWARD_MASTER WHERE AWARD_CODE = ? " ;
		
		case 4 : return" SELECT  AWARD_CODE,AWARD_TYPE,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_AWARD_MASTER ORDER BY upper(AWARD_TYPE) "; 
		
		default: return " Query is not available " ;
		}
	}
}
