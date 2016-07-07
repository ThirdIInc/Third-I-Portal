package org.paradyne.sql.TravelManagement.TravelClaim;

import org.paradyne.lib.SqlBase;

/**
 * @author krishna date: 06-JAN-2010
 * 
 */
public class TmsTrvlClmApprovalModelSql extends SqlBase {
	public String getQuery(int id) {

		switch (id) {

		case 1:
			return " INSERT INTO TMS_EXP_APPROVAL_DTL(EXP_APPID,EXP_APPRVR_ID, EXP_APPRVR_LEVEL, EXP_APPRVD_AMT)"
					+ "VALUES(?,?,?,(SELECT NVL(EXP_TOT_EXPAMT,0) FROM TMS_CLAIM_APPL WHERE EXP_APPID="
					+"?))";

		default:
			return " Framework could not find query number sepcified";

		}

	}

}
