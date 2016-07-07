package org.paradyne.sql.admin.master;
import org.paradyne.lib.SqlBase;
/*
 * author:Pradeep Ku. Sahoo
 */

public class LeaveMasterModelSql extends SqlBase {
	public String getQuery(int id){
		switch(id){
		
		case 1:return " INSERT INTO HRMS_LEAVE(LEAVE_ID,LEAVE_NAME,LEAVE_ABBR,IS_ACTIVE,IS_HALF_DAY) VALUES((SELECT NVL(MAX(LEAVE_ID),0)+1 FROM HRMS_LEAVE),?,?,?,?) "; 
		 
		case 2:return " UPDATE HRMS_LEAVE SET LEAVE_NAME = ?,lEAVE_ABBR = ?,IS_ACTIVE=? ,IS_HALF_DAY=? WHERE LEAVE_ID = ? ";
		
		case 3:return " DELETE FROM HRMS_LEAVE WHERE LEAVE_ID = ? ";
		
		case 4:return " SELECT LEAVE_ID,LEAVE_NAME ,NVL(LEAVE_ABBR,''), DECODE(IS_ACTIVE,'Y','YES','N','NO') ,IS_HALF_DAY FROM HRMS_LEAVE WHERE LEAVE_ID= ? " ;
		
		case 5:return " SELECT LEAVE_ID,LEAVE_NAME ,NVL(LEAVE_ABBR,''), DECODE(IS_ACTIVE,'Y','YES','N','NO') FROM HRMS_LEAVE ORDER BY upper(LEAVE_NAME) " ;
		
		default: return "Query does not exist";
		}
	}
}
