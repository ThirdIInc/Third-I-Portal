/**
 * 
 */
package org.paradyne.sql.TravelManagement.TravelProcess;

import org.paradyne.lib.SqlBase;

/**
 * @author Pankaj_Jain
 *
 */
public class TravelMonitoringModelSql extends SqlBase {
	public String getQuery(int id) {

		switch (id) {
		
		case 1:
			return "UPDATE TMS_SUGG_TRAVELLING SET  TVLNG_ID_NO=?, TVLNG_TCK_NO=?, TVLNG_COST=?,TVLNG_DETAILS=? "
			+ " ,TVLNG_CANCEL_CHARGE=?,TVLNG_REFUND_AMT=?,TVLNG_COMMENTS=?"
			+ " WHERE TVLNG_DTL_CODE=? AND TVLNG_SEL_FLAG='Y'";
			
		case 2:
			return "UPDATE TMS_SUGG_LOC_CONV SET LOCCONV_TARIFFCOST=?,LOCCONV_DETAILS=? "
			+ " ,LOCCONV_CANCEL_CHARGE=?,LOCCONV_REFUND_AMT=?,LOCCONV_COMMENTS=?"
			+ " WHERE LOCCONV_DTL_CODE=?";
			
		case 3:
			return "UPDATE  TMS_SUGG_ACCOM SET ACCOM_BOOKING_AMT=?,ACCOM_DETAILS=? "
			+ " ,ACCOM_CANCEL_CHARGE=?,ACCOM_REFUND_AMT=?,ACCOM_COMMENTS=?"
			+ " WHERE ACCOM_LODGEDTL_CODE=?";
			
		default:
			return " Framework could not find query number sepcified";

		}

	}

}
