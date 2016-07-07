/**
 * 
 */
package org.paradyne.sql.TravelManagement.Master;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0651
 *
 */
public class TravelModeModelSql extends SqlBase {
	
	public String getQuery(int id) {
		// TODO Auto-generated method stub
		switch(id)
		{
		
			case 1: return " INSERT INTO HRMS_TMS_JOURNEY_MODE(JOURNEY_ID,JOURNEY_NAME,JOURNEY_STATUS,JOURNEY_LEVEL) " 
			+ " VALUES(?,?,?,?)";
			
			case 2: return " UPDATE HRMS_TMS_JOURNEY_MODE SET JOURNEY_NAME=?,JOURNEY_STATUS=? WHERE JOURNEY_ID=?";
						
			case 3: return " DELETE FROM HRMS_TMS_JOURNEY_MODE WHERE JOURNEY_ID=?";
			
			default:return " FRAMEWORK COULD NOT EXECUTE THE QUERY NUMBER SPECIFIED"; 
		}
	
		
	}

}
