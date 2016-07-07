/*
 *
 *
 */
package org.paradyne.sql.leave;

import org.paradyne.lib.SqlBase;

/**
 * @author Venkatesh
 *
 */
public class LeaveAuditModelSql extends SqlBase{

	/*
	 * 
	 */
	public LeaveAuditModelSql() {
		// TODO Auto-generated constructor stub
	}

	
	public String getQuery(int queryID){
		
		switch(queryID){
		/*
		 * case 1: return " SELECT DISTINCT HRMS_LEAVE_DTL.EMP_ID,HRMS_EMP_OFFC.EMP_TITLE||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"+
					   " LEAVE_DAYS,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),LEAVE_DTL_ID,LEAVE_AUDIT_FLAG,LEAVE_AUDIT_DATE,LEAVE_AUDIT_BY	"+
					   " FROM HRMS_LEAVE_DTL INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  = HRMS_LEAVE_DTL.EMP_ID) "+
					   " WHERE HRMS_LEAVE_DTL.EMP_ID=? ORDER BY HRMS_LEAVE_DTL.EMP_ID ";
		 * 
		 * 
		 */
		case 1: return " SELECT DISTINCT HRMS_LEAVE_DTL.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"+
					   " LEAVE_DAYS,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),LEAVE_DTL_ID,LEAVE_AUDIT_FLAG,LEAVE_AUDIT_DATE,LEAVE_AUDIT_BY	"+
					   " FROM HRMS_LEAVE_DTL INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  = HRMS_LEAVE_DTL.EMP_ID) "+
					   " WHERE HRMS_LEAVE_DTL.EMP_ID=? and LEAVE_DTL_STATUS!='N' ORDER BY HRMS_LEAVE_DTL.EMP_ID ";
		
		case 2: return " UPDATE HRMS_LEAVE_DTL SET LEAVE_AUDIT_FLAG=? ,LEAVE_AUDIT_DATE=SYSDATE,LEAVE_AUDIT_BY=? WHERE LEAVE_DTL_ID=? ";
		
		default: return "QUERY NOT FOUND";		

		}
	}
}