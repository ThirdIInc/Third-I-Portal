/**
 * 
 */
package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0554
 *
 */
public class AppraiserConfigModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {
		case 1 : return "INSERT INTO PAS_APPR_APPRAISER_GRP_HDR(APPR_APPRAISER_GRP_ID,APPR_APPRAISER_GRP_DATE,APPR_APPRAISER_GRP_NAME,APPR_ID)"
							+" VALUES(?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?)";
			
		
		case 2 : return "INSERT INTO PAS_APPR_APPRAISER_GRP_DTL(APPR_APPRAISER_GRP_ID,APPR_APPRAISEE_ID) VALUES(?,?)";
		
		case 3 : return "INSERT INTO PAS_APPR_APPRAISER (APPR_ID,APPR_APPRAISER_GRP_ID,APPR_PHASE_ID,APPR_APPRAISER_CODE,APPR_APPRAISER_LEVEL)"
						+" VALUES(?,?,?,?,?)";
		
		case 4 : return "UPDATE PAS_APPR_APPRAISER_GRP_HDR SET APPR_APPRAISER_GRP_DATE=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),APPR_APPRAISER_GRP_NAME=? WHERE APPR_APPRAISER_GRP_ID=?";
		
		default:return "Framework could not find the query number specified";
			
		}
		
	} 
}
