/**
 * 
 */
package org.paradyne.sql.Training;

import org.paradyne.lib.SqlBase;

/**
 * @author ritac
 *
 */
public class TrainingDetailsModelSql extends SqlBase {

	
	
	public String getQuery(int id){
		
		switch(id)
		{
		case 1: return " INSERT INTO HRMS_TRN_DETAILS(TRN_DETAILS_ID,TRN_DETAILS_SELCODE,TRN_DETAILS_ACT_COST) "
                      +"VALUES((SELECT NVL(MAX(TRN_DETAILS_ID),0)+1 FROM HRMS_TRN_DETAILS),?,?)";
			
		
		case 2: return 	" UPDATE HRMS_TRN_DETAILS set TRN_DETAILS_SELCODE=?,TRN_DETAILS_ACT_COST=? WHERE TRN_DETAILS_ID= ? ";
		
		case 3: return 	" DELETE FROM HRMS_TRN_DETAILS WHERE TRN_DETAILS_ID= ? ";
		
		
		case 4: return "INSERT INTO HRMS_TRNSCD_EMPDTL(TRN_ID,EMPDTL_EMPCODE) VALUES(?,?)";
		
		case 5: return " UPDATE HRMS_TRN_SCHEDULE SET TRN_REQ_CODE=? ,TRN_FRMDATE=TO_DATE(?,'DD-MM-YYYY'),TRN_TODATE=TO_DATE(?,'DD-MM-YYYY'),TRN_TIMESLOT=?,TRN_TYPE=?,TRN_COST=?,TRN_VENUE=?,TRN_PRPBY=?,TRN_APRBY=? WHERE TRN_ID =?";
       
		
		
		default : return "Framework could not EXECUTE the query number specified";		
		}
		
	}
}
