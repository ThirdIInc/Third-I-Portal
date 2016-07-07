package org.paradyne.sql.admin.master;
/*
 * author:Pradeep Ku Sahoo
 */

import org.paradyne.lib.SqlBase;

public class HolidayMasterModelSql  extends SqlBase  {
	public String getQuery(int id) {
		switch(id) {
		case 1: return "INSERT INTO HRMS_HOLIDAY(HOLI_DATE,HOLI_DESC,HOLI_TYPE,IS_ACTIVE) VALUES "
		        +"  (TO_DATE(?,'DD-MM-YYYY'),?,?,?) ";
		
		
		case 2: return "SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY WHERE HOLI_DATE=TO_DATE(?,'DD-MM-YYYY') "; 
		
		case 3:return "UPDATE HRMS_HOLIDAY SET HOLI_DESC= ?,HOLI_DATE=TO_DATE(?,'DD-MM-YYYY'),HOLI_TYPE=?,IS_ACTIVE=? WHERE HOLI_DATE=TO_DATE(?,'DD-MM-YYYY') ";
		
		case 4:return "DELETE FROM HRMS_HOLIDAY WHERE  HOLI_DATE=TO_DATE(?,'DD-MM-YYYY') ";
		
		case 5:return "SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY'),HOLI_DESC,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_HOLIDAY order by HOLI_DATE ";
		
		default:return "Query does not exist";
		
		}
	}
}
