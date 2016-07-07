/**
 * 
 */
package org.paradyne.sql.TravelManagement.ExpenseClaim;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0417
 *
 */
public class TravelAdvanceDefaulterModelSql extends SqlBase {
	
	public String getQuery(int id) {
		// TODO Auto-generated method stub
		switch(id)
		{
			case 1: return " INSERT INTO HRMS_TMS_ADVANCE_DFT_HDR ( ADVANCE_DFT_HDR_ID, ADVANCE_DFT_HDR_FROM_DATE, ADVANCE_DFT_HDR_TO_DATE, "
                 +" ADVANCE_DFT_HDR_SAL_MONTH, ADVANCE_DFT_HDR_SAL_YEAR, ADVANCE_DFT_HDR_SYSDATE) VALUES (" 
                 +"(SELECT NVL(MAX(ADVANCE_DFT_HDR_ID),0)+1 FROM HRMS_TMS_ADVANCE_DFT_HDR) ,TO_DATE(?,'DD-MM-YYYY') ,TO_DATE(?,'DD-MM-YYYY') , "
                 +" ?, ?, TO_DATE(SYSDATE,'DD-MM-YYYY'))";
			
			default:return "Framework could not EXECUTE the query number specified"; 
		}
	
		
	}

}
