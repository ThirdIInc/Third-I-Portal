package org.paradyne.sql.admin.master;
import org.paradyne.lib.SqlBase;

public class RecruitmentmediumModelSql extends SqlBase {
	
	public String getQuery(int id) 
	{
		switch(id)
		{
		
		case 2: return "UPDATE HRMS_RECUITMENT_MEDIUM SET MEDIUM_NAME = ? WHERE MEDIUM_ID = ? ";	

		case 3: return "DELETE FROM HRMS_RECUITMENT_MEDIUM WHERE MEDIUM_ID = ? ";
		
		case 4: return "SELECT MEDIUM_ID,MEDIUM_NAME FROM HRMS_RECUITMENT_MEDIUM WHERE MEDIUM_ID=? ";
		
		case 5: return "SELECT MEDIUM_ID,MEDIUM_NAME FROM HRMS_RECUITMENT_MEDIUM ORDER BY MEDIUM_ID ";
		
		case 8: return "INSERT INTO HRMS_RECUITMENT_MEDIUM(MEDIUM_ID,MEDIUM_NAME) "
					+" VALUES((SELECT NVL(MAX(MEDIUM_ID),0)+1 FROM HRMS_RECUITMENT_MEDIUM ),? )";
		
		default : return "Framework did not find the query number specified";
		}
		
	}
}
