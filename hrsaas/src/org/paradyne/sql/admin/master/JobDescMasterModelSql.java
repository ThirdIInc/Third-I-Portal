package org.paradyne.sql.admin.master;
import org.paradyne.lib.SqlBase;

public class JobDescMasterModelSql extends SqlBase {
	
	
	
	public String getQuery(int id){
		switch(id){
		
		case 1:return" INSERT INTO HRMS_JOB_DESCRIPTION(JOB_DESC_CODE,JOB_DESC_NAME,JOB_DESC_DESC,JOB_DESC_ROLE_RESP,JOB_DESC_STATUS,JOB_DESC_EFFC_DATE,JOB_DESC_MIN_RECRUIT_DAYS, JOB_DESC_GRADE) "
					+ " VALUES((SELECT NVL(MAX(JOB_DESC_CODE),0)+1 FROM  HRMS_JOB_DESCRIPTION),?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?)";

		
		case 2:return "UPDATE HRMS_JOB_DESCRIPTION SET JOB_DESC_NAME=?,JOB_DESC_DESC=?,JOB_DESC_ROLE_RESP=?,JOB_DESC_EFFC_DATE=TO_DATE(?,'DD-MM-YYYY'),JOB_DESC_STATUS=?,JOB_DESC_CODE=?,JOB_DESC_MIN_RECRUIT_DAYS=?, JOB_DESC_GRADE=? WHERE JOB_DESC_CODE=?";
		
		case 3: return "DELETE FROM HRMS_JOB_DESCRIPTION WHERE  JOB_DESC_CODE=?";
		
		default:return " Query does not exist.";
		}
	}
}
