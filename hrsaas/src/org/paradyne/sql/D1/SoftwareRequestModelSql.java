package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

/**
 * @author aa1381
 *
 */
public class SoftwareRequestModelSql extends SqlBase {

	
	/** (non-Javadoc).
	 * @see org.paradyne.lib.SqlBase#getQuery(int)
	 * @param id - Used to select query.
	 * @return String.
	 */
	public String getQuery(final int id) {
		switch (id) {

	            case 1:
			    return " INSERT INTO HRMS_D1_SPECIAL_SW_REQ(SPECIAL_SOFTWARE_ID,SPECIAL_SOFTWARE_CODE,SPECIAL_SOFTWARE_NAME) " 	+ " VALUES(?,?,?)";
	            case 2:
			    return " UPDATE HRMS_D1_SPECIAL_SW_REQ SET SPECIAL_SOFTWARE_CODE=?,SPECIAL_SOFTWARE_NAME=? WHERE SPECIAL_SOFTWARE_ID=?  ";
		
	            case 3:
			    return " SELECT SPECIAL_SOFTWARE_ID,SPECIAL_SOFTWARE_NAME FROM  HRMS_D1_SPECIAL_SW_REQ " + " ORDER BY SPECIAL_SOFTWARE_NAME ";
		
	            case 4:
			    return " DELETE from HRMS_D1_SPECIAL_SW_REQ WHERE SPECIAL_SOFTWARE_ID=?  ";
		
		
	            default:
			    return "Framework could not the query number specified";
		}
	}
	
}
