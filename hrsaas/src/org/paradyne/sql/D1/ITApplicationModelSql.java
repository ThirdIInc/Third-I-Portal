package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

/**
 * @author aa1381
 * 
 */
public class ITApplicationModelSql extends SqlBase {
		
		/** (non-Javadoc).
		 * @see org.paradyne.lib.SqlBase#getQuery(int)
		 * @return String.
		 * @param id - Used to select specific query.
		 */
	public String getQuery(final int id) {
	        
		switch (id) {

	            case 1:
			    return " INSERT INTO HRMS_D1_IT_SEC_APPLICATIONS(APPLN_ID,APPLN_NAME,APPLN_SECTION,APPLN_ACTIVE) " + " VALUES(?,?,?,?)";
		    case 2:
			    return " UPDATE HRMS_D1_IT_SEC_APPLICATIONS SET APPLN_NAME=? ,APPLN_SECTION=?,APPLN_ACTIVE=? WHERE APPLN_ID=?  ";

		    case 3:
			    return " SELECT APPLN_ID,APPLN_NAME,CASE WHEN APPLN_ACTIVE='Y' THEN 'Yes' ELSE 'No' END FROM  HRMS_D1_IT_SEC_APPLICATIONS ORDER BY APPLN_NAME ";

		    case 4:
			    return " DELETE from HRMS_D1_IT_SEC_APPLICATIONS WHERE APPLN_ID=?  ";

		    default:
			    return "Framework could not the query number specified";
		}
	}
}
