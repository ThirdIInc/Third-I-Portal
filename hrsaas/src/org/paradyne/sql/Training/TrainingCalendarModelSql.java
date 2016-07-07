/**
 * 
 */
package org.paradyne.sql.Training;

import org.paradyne.lib.SqlBase;

/**
 * @author ankita.wankhade
 *
 */
public class TrainingCalendarModelSql extends SqlBase {
	
	public String getQuery(int id){
		switch(id){
		case 1:
			return "Select TRAINING_ID,PROGRAM_NAME,COURSE_DESCRIPTION,TO_CHAR(TRN_SCH_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(TRN_SCH_TO_DATE,'DD-MM-YYYY'),TO_CHAR(ENROLL_CUTOFF_DATE,'DD-MM-YYYY') "
                       +" from TND_TRAINING_CALENDAR "
                       +" inner join WBT_PROGRAM_HDR on TND_TRAINING_CALENDAR.TRAINING_ID=WBT_PROGRAM_HDR.PROGRAM_ID";
		
		default:
			return " FRAMEWORK COULD NOT THE QUERY NUMBER SPECIFIED";
		}
	
		
	
	
	
	}
}
