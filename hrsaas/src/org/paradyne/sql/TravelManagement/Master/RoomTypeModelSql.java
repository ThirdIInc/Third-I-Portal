
package org.paradyne.sql.TravelManagement.Master;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0651
 *
 */
public class RoomTypeModelSql extends SqlBase {
	
	public String getQuery(int id) {
		// TODO Auto-generated method stub
		switch(id)
		{
			
			
			case 1: return " INSERT INTO HRMS_TMS_ROOM_TYPE(ROOM_TYPE_ID,ROOM_TYPE_NAME,ROOM_TYPE_DESC,ROOM_TYPE_STATUS,ROOM_LEVEL,ROOM_HOTEL_TYPE	) " 
					+ " VALUES((SELECT NVL(MAX(ROOM_TYPE_ID),0)+1 FROM HRMS_TMS_ROOM_TYPE ),?,?,?,(SELECT NVL(MAX(ROOM_LEVEL),0)+1 FROM HRMS_TMS_ROOM_TYPE ),?)";
			
			case 2: return " UPDATE HRMS_TMS_ROOM_TYPE SET ROOM_TYPE_NAME=?,ROOM_TYPE_DESC=?,ROOM_TYPE_STATUS=?,ROOM_HOTEL_TYPE=? WHERE ROOM_TYPE_ID=?";
			
						
			case 3: return " DELETE FROM HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_ID=?";
			
			default:return "Framework could not EXECUTE the query number specified"; 
		}
	
		
	}

}
