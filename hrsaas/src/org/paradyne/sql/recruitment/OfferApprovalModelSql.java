package org.paradyne.sql.recruitment;
import org.paradyne.lib.SqlBase;
/**
 * 
 * @author Pradeep Sahoo
 * Date:07-02-2009
 *
 */
public class OfferApprovalModelSql extends SqlBase {
public String getQuery(int code){
		
		switch(code){
		
		case 1 : return "UPDATE HRMS_REC_OFFER SET OFFER_APPR_STATUS=? ,OFFER_APPR_DATE=TO_DATE(?,'DD-MM-YYYY'),OFFER_STATUS=? WHERE OFFER_REQS_CODE=? AND OFFER_CODE=? AND OFFER_CAND_CODE=?";
		
		case 2 : return "UPDATE HRMS_REC_APPOINT SET APPOINT_APPR_STATUS=? ,APPOINT_APPR_DATE=TO_DATE(?,'DD-MM-YYYY'),APPOINT_STATUS=? WHERE APPOINT_REQS_CODE=? AND APPOINT_CODE=? AND APPOINT_CAND_CODE=?";
		
		default : return "Query does not exist";
		
		}
	}
}
