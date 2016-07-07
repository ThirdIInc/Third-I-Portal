package org.paradyne.sql.TravelManagement;

import org.paradyne.lib.SqlBase;

public class TravelSchApproverModelSql extends SqlBase {
	public String getQuery(int id) {

		switch (id) {
		case 1:
			return " INSERT INTO HRMS_TMS_EXP_DISB ( EXP_DISB_ID,EXP_DISB_EMPID,EXP_DISB_TRAVEL_APPID,EXP_DISB_TOT_EXPAMT,EXP_DISB_ADVANCE_AMT,EXP_DISB_OTHER_DEDUCT,EXP_DISB_PAID_AMT,EXP_DISB_BALANCE_AMT,EXP_DISB_PAYMENT_DATE"
					+ " , EXP_DISB_PAYMODE,EXP_DISB_BANK_NAME,EXP_DISB_CHEQUE_NO,EXP_DISB_CHEQUE_DATE ,EXP_DISB_SAL_MONTH,EXP_DISB_SAL_YEAR ,DISB_BAL_COMMENTS,EXP_DISB_STATUS,EXP_DISB_EXPAPP_ID,EXP_DISB_PAID_RECORD,EXP_DISB_BAL_RECORD) VALUES( (SELECT NVL(MAX(EXP_DISB_ID),0)+1 FROM HRMS_TMS_EXP_DISB),?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?)";

		case 2:
			return " INSERT INTO HRMS_TMS_EXP_DISB_BAL (DISB_BAL_ID,DISB_BAL_PAID_BAL,DISB_BAL_PEND_BAL,DISB_BAL_PAYMENT_DATE,DISB_BAL_PAYMODE,DISB_BAL_BANK_NAME,DISB_BAL_CHEQUE_NO,DISB_BAL_CHEQUE_DATE,DISB_BAL_SAL_MONTH,"
					+ "DISB_BAL_SAL_YEAR ,DISB_BAL_COMMENT,DISB_BAL_EXP_DISB_ID) VALUES( (SELECT NVL(MAX(DISB_BAL_ID),0)+1 FROM HRMS_TMS_EXP_DISB_BAL),?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?)";

		case 3:
			return "UPDATE HRMS_TRAVEL  SET TRAVEL_LEVEL=? , TRAVEL_APPROVER =? ,TRAVEL_ALTER_APPROVER = ? WHERE TRAVEL_ID =?";

		case 5:
			return " UPDATE HRMS_TMS_EXP_DISB  SET EXP_DISB_PAID_RECORD = NVL(EXP_DISB_PAID_RECORD,0)+? , EXP_DISB_BAL_RECORD =? ,EXP_DISB_STATUS = ? WHERE EXP_DISB_EXPAPP_ID =?";

		case 6:
			return " INSERT INTO HRMS_TMS_EXP_DISB_BAL ( DISB_BAL_ID,DISB_BAL_TOT_BALANCE,DISB_BAL_PAID_BAL,DISB_BAL_PEND_BAL,DISB_BAL_PAYMENT_DATE,DISB_BAL_PAYMODE,DISB_BAL_CHEQUE_NO,DISB_BAL_CHEQUE_DATE,"
					+ "DISB_BAL_BANK_NAME,DISB_BAL_SAL_MONTH ,DISB_BAL_SAL_YEAR,DISB_BAL_COMMENT,DISB_BAL_EXP_DISB_ID) VALUES( (SELECT NVL(MAX(DISB_BAL_ID),0)+1 FROM HRMS_TMS_EXP_DISB_BAL),?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?)";

		default:
			return " Framework could not find query number sepcified";

		}

	}

}
