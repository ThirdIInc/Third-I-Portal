/**
 * 
 */
package org.paradyne.sql.TravelManagement.Master;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0651
 *
 */
public class FoodTypeModelSql extends SqlBase {
	public String getQuery(int id) {
		// TODO Auto-generated method stub
		switch(id)
		{
			
			
			case 1: return " INSERT INTO HRMS_TMS_FOOD_TYPE(FOOD_TYPE_ID,FOOD_TYPE_NAME,FOOD_TYPE_DESC,FOOD_TYPE_STATUS	) " 
					+ " VALUES((SELECT NVL(MAX(FOOD_TYPE_ID),0)+1 FROM HRMS_TMS_FOOD_TYPE ),?,?,?)";
			
			case 2: return " UPDATE HRMS_TMS_FOOD_TYPE SET FOOD_TYPE_NAME=?,FOOD_TYPE_DESC=?,FOOD_TYPE_STATUS=? WHERE FOOD_TYPE_ID=?";
			
						
			case 3: return " DELETE FROM HRMS_TMS_FOOD_TYPE WHERE FOOD_TYPE_ID=?";
			
			case 4 : return " SELECT TOUR_FOOD_TYPE FROM  TMS_APP_TOUR_DTL WHERE TOUR_FOOD_TYPE IS NOT NULL ";
			
			default:return "Framework could not EXECUTE the query number specified"; 
		}
	
		
	}

}
