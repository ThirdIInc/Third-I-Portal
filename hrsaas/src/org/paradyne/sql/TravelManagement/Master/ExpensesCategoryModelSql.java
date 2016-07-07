/**
 * 
 */
package org.paradyne.sql.TravelManagement.Master;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0651
 *
 */
public class ExpensesCategoryModelSql extends SqlBase {
	public String getQuery(int id) {
		// TODO Auto-generated method stub
		switch(id)
		{
			
			
			case 1: return " INSERT INTO HRMS_TMS_EXP_CATEGORY(EXP_CATEGORY_ID,EXP_CATEGORY_NAME,EXP_CATEGORY_UNIT,EXP_CATEGORY_DESC,EXP_CATEGORY_STATUS	) " 
					+ " VALUES((SELECT NVL(MAX(EXP_CATEGORY_ID),0)+1 FROM HRMS_TMS_EXP_CATEGORY ),?,?,?,?)";
			
			case 2: return " UPDATE HRMS_TMS_EXP_CATEGORY SET EXP_CATEGORY_NAME=?,EXP_CATEGORY_UNIT=?,EXP_CATEGORY_DESC=?,EXP_CATEGORY_STATUS=? WHERE EXP_CATEGORY_ID=?";
			
						
			case 3: return " DELETE FROM HRMS_TMS_EXP_CATEGORY WHERE EXP_CATEGORY_ID=?";
			
			default:return "Framework could not EXECUTE the query number specified"; 
		}
	
		
	}

}
