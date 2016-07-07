package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

public class RegionMasterModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_D1_REGION(REGION_ID,REGION_NAME) "
					+ " VALUES(?,?)";
		case 2:
			return " UPDATE HRMS_D1_REGION SET REGION_NAME =? WHERE REGION_ID=?  ";
		
		case 3:
			return" SELECT REGION_ID,REGION_NAME  FROM  HRMS_D1_REGION "
			        + " ORDER BY REGION_NAME ";
		
		case 4:
			return " DELETE from HRMS_D1_REGION WHERE REGION_ID=?  ";
		
		
		
		
		default:
			return "Framework could not the query number specified";

		
		
		
		}
	}

}