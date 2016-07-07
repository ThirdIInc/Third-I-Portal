package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author pranali 
 * Date 24-04-07
 */
public class HandicapModelSql extends SqlBase{

	
	public String getQuery(int id) 
	{
		switch(id)
		{
						
			
			case 1: return " INSERT INTO HRMS_HANDI_CATG (CATG_ID,CATG_NAME) " 
				           +"VALUES((SELECT NVL(MAX(CATG_ID),0)+1 FROM HRMS_HANDI_CATG ),?)";
			
			case 2: return " UPDATE HRMS_HANDI_CATG SET CATG_NAME=? WHERE CATG_ID=?";
			
			case 3: return " DELETE FROM HRMS_HANDI_CATG WHERE CATG_ID=?";
			
				
			case 4: return " SELECT CATG_ID,CATG_NAME FROM HRMS_HANDI_CATG " ;
			
			default : return "Framework could not the query number specified";
			
		}
	}
}
