package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

/**
 * @author aa1381
 *
 */
public class ClassRequestApproveModelSql extends SqlBase {
		/** (non-Javadoc).
		 * @see org.paradyne.lib.SqlBase#getQuery(int)
		 * @return String.
		 * @param id - Used to choose spesific query.
		 */
	public String getQuery(final int id) {
		switch (id) {

		    case 1:
			    return " INSERT INTO HRMS_D1_CLASS_REQ_DATA_PATH(CLASS_REQUEST_ID,CLASS_REQ_APPROVER, CLASS_REQ_COMMENTS, CLASS_REQ_DATE, CLASS_REQ_PATH_ID, CLASS_REQ_STATUS) " + " VALUES((SELECT NVL(MAX(CLASS_REQUEST_ID),0)+1 FROM HRMS_D1_CLASS_REQ_DATA_PATH ),?,?,SYSDATE,?,?)";
		    case 2:
			    return " UPDATE HRMS_D1_CLASS_REQUEST SET CLASS_REQ_APPROVER=?, CLASS_REQ_COMMENTS=?, CLASS_REQ_DATE=TO_DATE(?,'dd-mm-yyyy'), CLASS_REQ_PATH_ID=?, CLASS_REQ_STATUS=? WHERE CLASS_REQUEST_ID=?  ";

		    default:
		            return "Framework could not the query number specified";
		}
	}
}
