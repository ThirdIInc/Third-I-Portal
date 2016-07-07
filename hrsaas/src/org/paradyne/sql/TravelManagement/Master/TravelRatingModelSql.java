package org.paradyne.sql.TravelManagement.Master;

import org.paradyne.lib.SqlBase;

public class TravelRatingModelSql extends SqlBase 
{
	public String getQuery(int id) 
	{
		switch(id) 
		{
		case 1: return " INSERT INTO TMS_RATING_PARAM(RATING_ID, RATING_NAME, RATING_TYPE) "
						+ " VALUES((SELECT NVL(MAX(RATING_ID),0)+1 FROM TMS_RATING_PARAM),?,?)";  
     
		case 2: return " SELECT RATING_ID, RATING_NAME, NVL(RATING_TYPE,' ') FROM  TMS_RATING_PARAM "
						+ " ORDER BY RATING_ID ";
	
		case 3: return " UPDATE TMS_RATING_PARAM SET RATING_NAME=?,RATING_TYPE=? WHERE RATING_ID=? " ; 
		
		case 4: return " DELETE FROM TMS_RATING_PARAM WHERE RATING_ID=? ";
		
		default : return "Framework could not the query number specified";			
		}
	}

}
