package org.paradyne.sql.recruitment;
import org.paradyne.lib.SqlBase;

/**
 * @author ritac
 *
 */
public class CandidateScreeningModelSql extends SqlBase {
public String getQuery(int code){
		
		switch(code){
		
		case 1 : return "UPDATE HRMS_REC_RESUME_BANK SET RESUME_STATUS=? ,RESUME_COMMENTS=?,RESUME_APPR_DATE=TO_DATE(?,'DD-MM-YYYY') WHERE RESUME_CODE=? AND RESUME_CAND_CODE=? AND RESUME_REQS_CODE=?";
		
	
		
		default : return "Query does not exist";
		
		}
	}
}