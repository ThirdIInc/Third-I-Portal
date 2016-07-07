package org.paradyne.sql.admin.master;
import org.paradyne.lib.SqlBase;
/*
 * Pradeep Ku Sahoo
 * Date:26.06.2007
 */
public class TitleMasterModelSql extends SqlBase {
	
	
	public String getQuery(int id) 
	{
		switch(id)
		{
		
		case 1: return "INSERT INTO HRMS_TITLE(TITLE_CODE,TITLE_NAME,IS_ACTIVE)  "
						+" VALUES((SELECT NVL(MAX(TITLE_CODE),0)+1 FROM HRMS_TITLE),?,? ) "; // IS_ACTIVE field is added by Abhijit
		
		
		case 2:return " UPDATE HRMS_TITLE SET TITLE_NAME= ?,IS_ACTIVE=? WHERE TITLE_CODE = ? "; 
		
		case 3:return " DELETE FROM HRMS_TITLE WHERE TITLE_CODE = ? " ;
		//case 4:return "  SELECT  TITLE_CODE,TITLE_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO') FROM HRMS_TITLE ORDER BY upper(TITLE_NAME)";
		case 4:return "  SELECT  TITLE_CODE,TITLE_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO') FROM HRMS_TITLE ORDER BY TITLE_CODE";
		
		default : return "Query number not specified " ;
		}
		
		
	}		
		
		
		

}
