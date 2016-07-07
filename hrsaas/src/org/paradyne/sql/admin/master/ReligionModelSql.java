package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author pranali 
 * Date 24-04-07
 */
public class ReligionModelSql extends SqlBase {
	
	public String getQuery(int id) 
	{
		switch(id)
		{
						
			
			case 1: return " INSERT INTO HRMS_RELIGION (RELIGION_ID,RELIGION_NAME,IS_ACTIVE) " 
				           +"VALUES((SELECT NVL(MAX(RELIGION_ID),0)+1 FROM HRMS_RELIGION ),?,?)";
			
			case 2: return " UPDATE HRMS_RELIGION SET RELIGION_NAME=? , IS_ACTIVE=?  WHERE RELIGION_ID=?";
			
			case 3: return " DELETE FROM HRMS_RELIGION WHERE RELIGION_ID=?";
			
				
			case 4: return " SELECT RELIGION_ID,RELIGION_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO') FROM HRMS_RELIGION ORDER BY upper(RELIGION_NAME)" ;
			
			default : return "Framework could not the query number specified";
			
		}
	}
}
