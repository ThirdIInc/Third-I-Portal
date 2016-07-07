/**
 * 
 */
package org.paradyne.sql.admin.srd;

import org.paradyne.lib.SqlBase;

/**
 * @author balajim
 *
 */
public class EmployeeHouseModelSql extends SqlBase{
	
	
	public String getQuery(int id){
		switch(id){
					 case 1: return " INSERT INTO HRMS_EMP_HOUSE(HOUSE_ID,EMP_ID,HOUSE_NUMBER,HOUSE_COLONY_ID,HOUSE_OCCUPANCY_STATUS)  "
					 			    +" VALUES((SELECT MAX(HOUSE_ID)+1 FROM HRMS_EMP_HOUSE ),?,?,?,?) ";
					 case 2: return " DELETE FROM HRMS_EMP_HOUSE WHERE HOUSE_ID =  ? "; 
					 case 3: return " UPDATE HRMS_EMP_HOUSE SET  HOUSE_NUMBER = ? , HOUSE_COLONY_ID = ? , HOUSE_OCCUPANCY_STATUS = ?  "
                                    +" WHERE  HOUSE_ID =  ? ";  
					 
					 default:return"query not found";
					 
                  }
		
            	}

        }




 