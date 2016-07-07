/**
 * 
 */
package org.paradyne.sql.settings;

import org.paradyne.lib.SqlBase;

/**
 * @author saipavan
 *
 */
public class SuggestionApprovalModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id)
		{
			
			case 2 : return " INSERT INTO HRMS_SUGGESTION_PATH (SUGGESTION_APPL_CODE,SUGGESTION_APPROVER," +
					"SUGGESTION_APPR_DATE," +
					"SUGGESTION_PATH_STATUS," +
					"SUGGESTION_APPL_COMMENTS,SUGGESTION_PATH_ID)"
			                   +" VALUES(?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?,(SELECT (NVL(MAX(SUGGESTION_PATH_ID),0)+1) FROM HRMS_SUGGESTION_PATH WHERE SUGGESTION_APPL_CODE=?))";
			
			case 3 : return " UPDATE HRMS_SUGGESTION SET SUGGESTION_FLAG = ? WHERE SUGGESTION_CODE = ?";
			
			case 4 : return " UPDATE HRMS_SUGGESTION SET  SUGGESTION_APPR = ?, SUGGESTION_APPR_LEVEL=?,SUGGESTION_ALT_APPR=? WHERE SUGGESTION_CODE = ? ";
			
			
			default : return "Framework could not EXECUTE the query number specified";		
		}
	}
}
