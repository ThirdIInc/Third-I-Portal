/**
 * 
 */
package org.paradyne.sql.MPrecruitment;

import org.paradyne.lib.SqlBase;

/**
 * @author diptip
 *
 */
public class EducationalInfoModelSql extends SqlBase {

	/**
	 * 
	 */
	public String getQuery(int id){
		switch (id) {
		case 1 : return "INSERT INTO HRMS_MP_EDUCATION( EDUCATION_ID,EDUCATION_RECRUIT_ID,EDUCATION_DEGREE,EDUCATION_QUALIFICATION," 
						+"EDUCATION_INSTITUTE, EDUCATION_UNIVERSITY, EDUCATION_YEAR_PASSING,EDUCATION_OBT_MARKS, EDUCATION_OBT_PERCENTAGE,"
						+"EDUCATION_COMP_LITER) "
						+" VALUES((SELECT NVL(MAX(EDUCATION_ID),0)+1  FROM HRMS_MP_EDUCATION),?,?,?,?,?,?,?,?,?) ";
		
		/*case 2 : return "UPDATE HRMS_MP_EDUCATION SET EDUCATION_DEGREE=?,EDUCATION_INSTITUTE=?,EDUCATION_UNIVERSITY=?,EDUCATION_YEAR_PASSING=?,"
		      +" EDUCATION_OBT_MARKS=?,EDUCATION_OBT_PERCENTAGE=? EDUCATION_QUALIFICATION=? EDUCATION_COMP_LITER=? WHERE EDUCATION_RECRUIT_ID=?";
		
		case 3 : return "DELETE FROM HRMS_MP_EDUCATION WHERE EDUCATION_RECRUIT_ID=?";*/

		default: return "hi";
			
		}
		
	}
}
