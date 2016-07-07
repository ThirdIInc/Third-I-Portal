package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

/**
 * @author aa1381
 *
 */
public class ClassRequestModelSql extends SqlBase {

	/** (non-Javadoc).
	 * @see org.paradyne.lib.SqlBase#getQuery(int)
	 * @param id - which query to execute.
	 * @return String.
	 */
	public String getQuery(final int id) {
		switch (id) {

		    case 1:
			    return " INSERT INTO HRMS_D1_CLASS_REQUEST(CLASS_REQUEST_ID,CLASS_NAME, CLASS_DESCRIPTION, DIVISION_ID, PRE_REQUISITE, INSTRUCTOR_NAME, REQUEST_START_DATE, REQUEST_END_DATE, DURATION_OF_CLASS, REQUEST_START_TIME, REQUEST_END_TIME, CLASSROOM, MAX_OF_STUDENTS, LOCATION, ADDRESS, TELEPHONE, CONTACT_NAME, HOTEL_INFORMATION,HOTEL_ATTACHMENT,COMMENTS,CLASS_APPL_DATE,STATUS,TRACKING_NUMBER,CLASS_INITIATOR,PERS_INITIATOR_DATE) " + " VALUES(?,?,?,?,?,?,TO_DATE(?,'dd-mm-yyyy'),TO_DATE(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,SYSDATE)";
		
		    case 2:
			    return " UPDATE HRMS_D1_CLASS_REQUEST SET CLASS_NAME=?, CLASS_DESCRIPTION=?, DIVISION_ID=?, PRE_REQUISITE=?, INSTRUCTOR_NAME=?, REQUEST_START_DATE=TO_DATE(?,'dd-mm-yyyy'), REQUEST_END_DATE=TO_DATE(?,'dd-mm-yyyy'), DURATION_OF_CLASS=?, REQUEST_START_TIME=?,REQUEST_END_TIME=?, CLASSROOM=?, MAX_OF_STUDENTS=?, LOCATION=?, ADDRESS=?, TELEPHONE=?, CONTACT_NAME=?, HOTEL_INFORMATION=?, HOTEL_ATTACHMENT=? ,COMMENTS=?,STATUS=? WHERE CLASS_REQUEST_ID=?  ";

		    case 3:
			    return " SELECT CLASS_REQUEST_ID,CLASS_NAME,CLASS_DESCRIPTION  FROM  HRMS_D1_CLASS_REQUEST " + " ORDER BY CLASS_NAME ";

		    case 4:
		            return " DELETE FROM HRMS_D1_CLASS_REQUEST WHERE CLASS_REQUEST_ID=?";
			
		    default:
			    return "Framework could not the query number specified";

		}
	}

}
