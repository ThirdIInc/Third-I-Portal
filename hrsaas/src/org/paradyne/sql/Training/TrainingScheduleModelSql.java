/**
 * 
 */
package org.paradyne.sql.Training;

import org.paradyne.lib.SqlBase;



/**
 * @author ritac
 *
 */
public class TrainingScheduleModelSql extends SqlBase {

	public String getQuery(int id){
		
		switch(id)
		{
		case 1: return " INSERT INTO HRMS_TRN_SCHEDULE(TRN_ID,TRN_REQ_CODE,TRN_FRMDATE,TRN_TODATE,TRN_TIMESLOT,TRN_TYPE,TRN_COST,TRN_VENUE,TRN_PRPBY,TRN_APRBY) "
                      +"VALUES((SELECT NVL(MAX(TRN_ID),0)+1 FROM HRMS_TRN_SCHEDULE),?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?)";
			
		
		case 2: return 	" INSERT INTO HRMS_TRNSCD_TKNBY(TRN_ID,TKNBY_EMPCODE,TKNBY_DATE,TKNBY_FRMTIME,TKNBY_TOTIME) "
                         +"VALUES(?,?,TO_DATE(?,'DD-MM-YYYY'),?,?)";
		
		
		case 3: return "INSERT INTO HRMS_TRNSCD_EMPDTL(TRN_ID,EMPDTL_EMPCODE) VALUES(?,?)";
		
		case 4: return " UPDATE HRMS_TRN_SCHEDULE SET TRN_REQ_CODE=? ,TRN_FRMDATE=TO_DATE(?,'DD-MM-YYYY'),TRN_TODATE=TO_DATE(?,'DD-MM-YYYY'),TRN_TIMESLOT=?,TRN_TYPE=?,TRN_COST=?,TRN_VENUE=?,TRN_PRPBY=?,TRN_APRBY=? WHERE TRN_ID =?";
       
		case 5: return 	" INSERT INTO HRMS_TRNSCD_TKNBY(TRN_ID,TKNBY_EMPCODE,TKNBY_DATE,TKNBY_FRMTIME,TKNBY_TOTIME) "
                        +"VALUES(?,?,TO_DATE(?,'DD-MM-YYYY'),?,?)";
       
		case 6: return "INSERT INTO HRMS_TRNSCD_EMPDTL(TRN_ID,EMPDTL_EMPCODE) VALUES(?,?)";
		
		case 7: return " DELETE FROM HRMS_TRN_SCHEDULE WHERE TRN_ID=? ";
		
		case 8: return "  DELETE FROM HRMS_TRNSCD_TKNBY WHERE TRN_ID=? ";
		
		
		case 9: return " DELETE FROM HRMS_TRNSCD_EMPDTL WHERE TRN_ID=? ";
		
		default : return "Framework could not EXECUTE the query number specified";		
		}
		
	}
}
