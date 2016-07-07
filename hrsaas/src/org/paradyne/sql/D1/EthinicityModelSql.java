package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

/**
 * @author aa1381
 *
 */
public class EthinicityModelSql extends SqlBase {
	

	/** (non-Javadoc).
	 * @see org.paradyne.lib.SqlBase#getQuery(int)
	 * @param id - Used to select query.
	 * @return String.
	 */
	public String getQuery(final int id) {
		switch (id) {

		    case 1:
			    return " INSERT INTO HRMS_D1_ETHIC(ETHIC_CODE,ETHINICITY) " + " VALUES(?,?)";
		    case 2:
			    return " UPDATE HRMS_D1_ETHIC SET ETHINICITY=? WHERE ETHIC_CODE=?  ";
		
		    case 3:
			    return " SELECT ETHIC_CODE,ETHINICITY FROM  HRMS_D1_ETHIC ORDER BY ETHINICITY ";
		
		    case 4:
			    return " DELETE from HRMS_D1_ETHIC WHERE ETHIC_CODE=?  ";
		
		    default:
			    return "Framework could not the query number specified";

		}
	}
	
}
