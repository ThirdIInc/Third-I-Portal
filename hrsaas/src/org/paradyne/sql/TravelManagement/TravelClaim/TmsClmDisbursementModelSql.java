package org.paradyne.sql.TravelManagement.TravelClaim;

import org.paradyne.lib.SqlBase;

/**
 * @author krishna date: 12-JAN-2010
 * 
 */
public class TmsClmDisbursementModelSql extends SqlBase {
	public String getQuery(int id) {

		switch (id) {

		case 1:
			return " INSERT INTO TMS_EXP_APPROVAL_DTL(EXP_APPID,EXP_APPRVR_ID, EXP_APPRVR_LEVEL, EXP_APPRVD_AMT)"
					+ "VALUES(?,?,?,?)";
			
		case 2:
			return " INSERT INTO TMS_EXP_DISB_BAL(EXP_DISB_BAL_ID,EXP_DISB_BAL_DISBID, EXP_DISB_PAID_BAL,EXP_DISB_PAYDATE,EXP_DISB_PAYMODE,EXP_DISB_CHEQUE_NO,"
					+ "EXP_DISB_CHEQUE_DATE,EXP_DISB_BANK_ID,EXP_DISB_TRNSFR_ACCNO,EXP_DISB_CMTS,EXP_DISB_MONTH,EXP_DISB_YEAR) VALUES((SELECT NVL(MAX(EXP_DISB_BAL_ID),0)+1 FROM TMS_EXP_DISB_BAL),?"
					+",?,TO_DATE(?,'DD-MM-YYYY'),?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?)";
			
		/*case 3:
			return " UPDATE TMS_EXP_DISB_BAL  SET EXP_DISB_PAID_BAL=?,EXP_DISB_PAYDATE=TO_DATE(?,'DD-MM-YYYY'),EXP_DISB_PAYMODE=?,EXP_DISB_CHEQUE_NO=?,"
					+ "EXP_DISB_CHEQUE_DATE=(?,'DD-MM-YYYY'),EXP_DISB_BANK_ID=?,EXP_DISB_TRNSFR_ACCNO=?,EXP_DISB_CMTS=? WHERE EXP_DISB_BAL_DISBID=? AND EXP_DISB_BAL_ID=?";
			*/
		case 3:return "DELETE FROM TMS_EXP_DISB_BAL WHERE EXP_DISB_BAL_DISBID=?";	
		
		case 4:return "	UPDATE TMS_CLAIM_APPL SET EXP_APP_STATUS=? WHERE EXP_APPID=?"  ;

		
		default:
			return " Framework could not find query number sepcified";

		}

	}

}
