/**
 * 
 */
package org.paradyne.sql.TravelManagement.Master;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0651
 *
 */
public class TravelAuthoritiesModelSql extends SqlBase {
	
	public String getQuery(int id) {
		// TODO Auto-generated method stub
		switch(id)
		{
			
			
			case 1: return " INSERT INTO HRMS_TMS_MANG_AUTH(MANG_AUTH_ID,MANG_AUTH_BRANCH,MANG_AUTH_SCHEDULER,MANG_AUTH_SCH_APPROVER"
					+ " ,MANG_AUTH_ACCOUNT_PER,MANG_AUTH_DESC,MANG_AUTH_STATUS,MANG_AUTH_DEFAULT_FLAG	) " 
					+ " VALUES(?,?,?,?,?,?,?,?)";
			
			case 2: return " UPDATE HRMS_TMS_MANG_AUTH SET MANG_AUTH_BRANCH=?,MANG_AUTH_SCHEDULER=?,MANG_AUTH_SCH_APPROVER=?,MANG_AUTH_ACCOUNT_PER=?,MANG_AUTH_DESC=?,MANG_AUTH_STATUS=?,MANG_AUTH_DEFAULT_FLAG=? WHERE MANG_AUTH_ID=?";
			
						
			case 3: return " DELETE FROM HRMS_TMS_MANG_AUTH WHERE MANG_AUTH_ID=?";
			
			case 4: return "insert into HRMS_TMS_MANG_ALTER_AUTH" 
				+ " (MANG_ALTER_AUTH_ID,MANG_ALTER_AUTH_BRANCH,MANG_ALTER_AUTH_SCHEDULER," 
				+ " MANG_ALTER_AUTH_SCH_APPROVER,MANG_ALTER_AUTH_ACCOUNT_PER,MANG_AUTH_SCHDULER_EMPID, " 
				+ " MANG_AUTH_SCHDULE_APPR_EMPID,MANG_AUTH_ACCOUNT_PER_EMPID,MANG_AUTH_APP_ID)" 
				+ " values(?,?,?,?,?,?,?,?,?)";
			
			case 5: return "UPDATE HRMS_TMS_MANG_ALTER_AUTH SET MANG_ALTER_AUTH_BRANCH=?,MANG_ALTER_AUTH_SCHEDULER=?,MANG_ALTER_AUTH_SCH_APPROVER=?,"
				+ " MANG_ALTER_AUTH_ACCOUNT_PER=?,MANG_AUTH_SCHDULER_EMPID=?,MANG_AUTH_SCHDULE_APPR_EMPID=?,"
				+ " MANG_AUTH_ACCOUNT_PER_EMPID=? WHERE MANG_ALTER_AUTH_BRANCH=? AND MANG_AUTH_SCHDULER_EMPID=?";
			
			default:return "Framework could not EXECUTE the query number specified"; 
		}
	
		
	}


}
