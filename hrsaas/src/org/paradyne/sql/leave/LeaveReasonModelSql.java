package org.paradyne.sql.leave;

import org.paradyne.lib.SqlBase;

public class LeaveReasonModelSql extends SqlBase {

	public String getQuery(int id) {
		switch(id) {
		case 1: return "INSERT INTO HRMS_LEAVE_REASON(REASON_CODE,REASON_NAME,IS_ACTIVE)" 
      					+"VALUES(NVL((SELECT MAX(REASON_CODE) FROM HRMS_LEAVE_REASON),0)+1,?,?)"; 
		
		case 2: return " UPDATE HRMS_LEAVE_REASON SET REASON_NAME =?,IS_ACTIVE=?  WHERE REASON_CODE =? " ;

		case 3: return " DELETE FROM HRMS_LEAVE_REASON WHERE REASON_CODE = ?";

		case 4 :return " SELECT REASON_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_LEAVE_REASON WHERE REASON_CODE = ?";

		case 5: return " SELECT REASON_CODE,REASON_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_LEAVE_REASON ORDER BY REASON_CODE ";
		
		default : return "Framework could not the query number specified";			
		}
	}
}
