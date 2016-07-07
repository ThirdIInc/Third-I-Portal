package org.paradyne.sql.admin.increment;

import org.paradyne.lib.SqlBase;

/**
 * @author sunil 
 
 *
 */
public class AnnualIncrementModelSql extends SqlBase {
	
	public String getQuery(int queryID){
			
			switch(queryID){
			
			/*case 1: return " INSERT INTO HRMS_INCR_HDR (INCR_CODE,EMP_ID,INCR_CURR_DATE,INCR_NEW_BASIC,INCR_NONQUALIFIED_DAYS," 
						+"   INCR_EFFECTIVE_DATE,INCR_PAY_BASIC,INCR_MTH,INCR_YEAR)"
						+"	VALUES(SEQ_INCR_CODE.NEXTVAL,?,TO_DATE(?,'DD-MM-YYYY'),?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?)";
			 */
			
			case 1: return " INSERT INTO HRMS_INCR_HDR (EMP_ID,INCR_PREVIOUS_DATE,INCR_CURR_DATE,INCR_NONQUALIFIED_DAYS," 
							+"   INCR_EFFECTIVE_DATE,INCR_PAY_BASIC,INCR_NEW_BASIC,INCR_MTH,INCR_YEAR,INCR_DCE_LIST,INCR_DCE_DATE,INCR_DCE_SERIAL_NO,INCR_ISLOCKED,INCR_PAYSCALE,INCR_ARREAR_TODATE)"
							+"	VALUES(?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,TO_DATE(?,'DD-MM-YYYY'))";
			
			case 2 : return " DELETE FROM HRMS_INCR_HDR WHERE INCR_MTH =? AND INCR_YEAR =? AND INCR_ISLOCKED !='L' ";
			
			default: return "QUERY NOT FOUND";
			}
	}
}
			
			