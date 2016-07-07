package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

/**
 * @author aa1381
 *
 */
public class ImprintTypeModelSql extends SqlBase {

	/** (non-Javadoc).
	 * @see org.paradyne.lib.SqlBase#getQuery(int)
	 * @param id - Used to select query.
	 * @return String.
	 */
	public String getQuery(int id) {
		switch (id) {

		    case 1:
			    return " INSERT INTO HRMS_D1_IMPRINT_TYPE(IMPRINT_ID,IMPRINT_CODE,IMPRINT_NAME) " + " VALUES(?,?,?)";
		    case 2:
			    return " UPDATE HRMS_D1_IMPRINT_TYPE SET IMPRINT_CODE=?,IMPRINT_NAME=? WHERE IMPRINT_ID=?  ";
		
		    case 3:
		            return " SELECT IMPRINT_ID,IMPRINT_NAME FROM  HRMS_D1_IMPRINT_TYPE " + " ORDER BY IMPRINT_NAME ";
		
		    case 4:
		            return " DELETE from HRMS_D1_IMPRINT_TYPE WHERE IMPRINT_ID=?  ";
		
		
		    default:
			    return "Framework could not the query number specified";

		}
	
	}
	
	
}
