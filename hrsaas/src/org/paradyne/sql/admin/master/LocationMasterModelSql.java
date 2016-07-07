/**
 * 
 */
package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author Lakkichand
 * @date 20 APR 2007
 */
public class LocationMasterModelSql extends SqlBase {
	public String getQuery(int id) {
		switch(id) {
		case 1: return "INSERT INTO HRMS_LOCATION (LOCATION_CODE, LOCATION_NAME, LOCATION_LEVEL_CODE, LOCATION_TYPE, IS_ACTIVE) " 
						+" VALUES((SELECT NVL(MAX(LOCATION_CODE),0)+1 FROM HRMS_LOCATION ), ?, 0, ?,? )";
		
		case 2: return "INSERT INTO HRMS_LOCATION (LOCATION_CODE, LOCATION_NAME, LOCATION_LEVEL_CODE, LOCATION_PARENT_CODE, LOCATION_TYPE,IS_ACTIVE ) " 
						+" VALUES((SELECT NVL(MAX(LOCATION_CODE),0)+1 FROM HRMS_LOCATION ), ?, 1, ?, ?,?)";
		
		case 3: return "INSERT INTO HRMS_LOCATION (LOCATION_CODE, LOCATION_NAME, LOCATION_LEVEL_CODE, LOCATION_PARENT_CODE, LOCATION_TYPE,LOCATION_CLASS,IS_ACTIVE) " 
						+" VALUES((SELECT NVL(MAX(LOCATION_CODE),0)+1 FROM HRMS_LOCATION ), ?, 2, ?, ?,?,?)";
		
		case 4 : return "DELETE FROM HRMS_LOCATION WHERE LOCATION_CODE = ?";
		
		case 5 : return "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE, DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_LOCATION "
						+" WHERE LOCATION_CODE = ?";
		
		case 6 : return "UPDATE HRMS_LOCATION SET LOCATION_NAME = UPPER(?), LOCATION_PARENT_CODE = ?, LOCATION_TYPE = ?, LOCATION_LEVEL_CODE = ?,LOCATION_CLASS = ?, IS_ACTIVE = ? "
						+" WHERE LOCATION_CODE = ?";
		
		case 7 : return "SELECT LOCATION_CODE, LOCATION_NAME, DECODE(LOCATION_TYPE, 'C','Country','S','State','Ci','City'), DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') "
						+" FROM HRMS_LOCATION "
						+" ORDER BY upper(LOCATION_NAME)";
						
		
		default : return "Framework could not the query number specified";			
		}
	}

}
