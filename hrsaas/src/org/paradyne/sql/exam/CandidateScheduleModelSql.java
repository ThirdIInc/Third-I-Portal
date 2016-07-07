/**
 * 
 */
package org.paradyne.sql.exam;

import org.paradyne.lib.SqlBase;

/**
 * @author diptip
 *
 */
public class CandidateScheduleModelSql extends SqlBase {

	/**
	 * 
	 */
	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_TEST_SCHEDULE (SCHEDULE_CODE,SCHEDULE_CANDIDATE_CODE,SCHEDULE_DATE,SCHEDULE_TIME,SCHEDULE_PAPER,CANDIDATE_USERNAME,CANDIDATE_PASSWORD)"
					+ " VALUES((SELECT NVL(MAX(SCHEDULE_CODE),0)+1 FROM HRMS_TEST_SCHEDULE ),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?)";
			
		case 2:
			return " UPDATE HRMS_TEST_SCHEDULE SET SCHEDULE_CANDIDATE_CODE=?,SCHEDULE_DATE=TO_DATE(?,'DD-MM-YYYY'),SCHEDULE_TIME=?,SCHEDULE_PAPER=? WHERE SCHEDULE_CODE=?";
			
		case 3:
			return " DELETE FROM HRMS_TEST_SCHEDULE WHERE SCHEDULE_CODE=?";

			

		
		default:
			return "Query Not Found";
		}
	}


}
