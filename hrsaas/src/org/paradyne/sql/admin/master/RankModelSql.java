package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;


/**
 * 
 * @author Prasad
 *
 */

public class RankModelSql extends SqlBase {
	
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_RANK (RANK_ID,RANK_NAME,RANK_ABBR,RANK_DESC,IS_ACTIVE, SALARY_RANGE) " 
						+ " VALUES((SELECT NVL(MAX(RANK_ID),0)+1 FROM HRMS_RANK ),?,?,?,?,?)";//added
		
		case 2: return " UPDATE HRMS_RANK SET RANK_NAME=?,RANK_ABBR=?,RANK_DESC=?,IS_ACTIVE=?, SALARY_RANGE=? WHERE RANK_ID=?";
		
		case 3: return " DELETE FROM HRMS_RANK WHERE RANK_ID=?";
		
		case 4: return " SELECT  RANK_ID,nvl(RANK_NAME,''),nvl(RANK_ABBR,''),nvl(RANK_PARENT_CODE,''),nvl(RANK_HIGHER_CODE,'') FROM HRMS_RANK WHERE RANK_ID=?";
		
		case 5: return " SELECT RANK_ID,nvl(RANK_NAME,''),nvl(RANK_ABBR,''),nvl(RANK_PARENT_CODE,''),nvl(RANK_HIGHER_CODE,'') FROM HRMS_RANK ORDER BY RANK_ID";
		
		case 6: return " SELECT  RANK_ID,SUBSTR(RANK_NAME,0,20),nvl(RANK_ABBR,''),DECODE(IS_ACTIVE,'Y','Yes','N','No') FROM HRMS_RANK ORDER BY RANK_ID";
		
		default : return "Framework could not the query number specified";			
		}
	}
}


/*package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class RankModelSql extends SqlBase {
	
public String getQuery(int code){
		
		switch(code){
		
		case 1:return "INSERT INTO HRMS_RANK(RANK_ID,RANK_NAME,RANK_ABBR,RANK_DESC,RANK_STATUS)"
						+" VALUES((SELECT NVL(MAX(RANK_ID),0)+1 FROM HRMS_FUNC_DOMAIN_MASTER),?,?,?,?)";
		
		case 2:return "UPDATE HRMS_RANK SET RANK_NAME=?,RANK_ABBR=?,RANK_DESC=?,RANK_STATUS=? WHERE RANK_ID=?";		
		
		case 3:return "DELETE FROM HRMS_RANK WHERE RANK_ID=?";
		
		
		
		default:return "Query does not exist.";
		
		
		}
		
    }

}
*/
