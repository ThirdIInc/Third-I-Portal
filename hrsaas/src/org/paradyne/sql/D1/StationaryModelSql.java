package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

/**
 * @author aa1381
 *
 */
public class StationaryModelSql extends SqlBase {
	
	/** (non-Javadoc).
	 * @see org.paradyne.lib.SqlBase#getQuery(int)
	 * @param id - Used to select query.
	 * @return String.
	 */
	public String getQuery(final int id) {
		switch (id) {

		    case 1:
			    return " INSERT INTO HRMS_D1_STATIONARY(STATIONARY_ID,STATIONARY_CODE,STATIONARY_NAME) " + " VALUES(?,?,?)";
		    case 2:
			    return " UPDATE HRMS_D1_STATIONARY SET STATIONARY_CODE=?,STATIONARY_NAME=? WHERE STATIONARY_ID=?  ";
		
		    case 3:
			    return " SELECT STATIONARY_ID,STATIONARY_NAME FROM  HRMS_D1_STATIONARY " + " ORDER BY STATIONARY_NAME ";
		
		    case 4:
			    return " DELETE from HRMS_D1_STATIONARY WHERE STATIONARY_ID=?  ";
		
		
		    default:
			    return "Framework could not the query number specified";
		
		}
	
	}

}
