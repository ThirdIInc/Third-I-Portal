package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

/**
 * @author aa1380.
 *
 */
public class ReqToBackOutVoucherModelSql extends SqlBase {
	/**
	 * Method : getQuery.
	 * Purpose : For getting respective number of query.
	 * @param id : id
	 * @return String
	 */
	public String getQuery(final int id) {
		switch (id) {

		    case 1:
			    return " INSERT INTO HRMS_D1_REQ_BACK_OUT(VOUCHER_REQUEST_ID, VOUCHER_FROM_EMP, VOUCHER_REQ_TO_DATE, VOUCHER_VENDER_NAME, " + 
					" VOUCHER_VENDER_NUMBER, VOUCHER_LINE_NUMBER, VOUCHER_QUANTITY, VOUCHER_VOUCHER_NUMBER, VOUCHER_REASON_FOR_REQUEST, VOUCHER_RMA, " + 
					" VOUCHER_AIR_BILL_NUMBER, VOUCHER_IS_CREDIT_MEMO, VOUCHER_COMMENTS, VOUCHER_STATUS, VOUCHER_PURCHASE_ORDER_NUMBER )" + 
					" VALUES((SELECT NVL(MAX(VOUCHER_REQUEST_ID),0)+1 FROM HRMS_D1_REQ_BACK_OUT ),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?)";

		    case 2:
			    return " UPDATE HRMS_D1_REQ_BACK_OUT SET VOUCHER_FROM_EMP =?, VOUCHER_REQ_TO_DATE =TO_DATE(?,'DD-MM-YYYY'), " + 
					" VOUCHER_VENDER_NAME =?, VOUCHER_VENDER_NUMBER =?, VOUCHER_LINE_NUMBER =?, VOUCHER_QUANTITY =?, VOUCHER_VOUCHER_NUMBER =?, " + 
					" VOUCHER_REASON_FOR_REQUEST=? ,VOUCHER_RMA=?, VOUCHER_AIR_BILL_NUMBER=?, VOUCHER_IS_CREDIT_MEMO=?, VOUCHER_COMMENTS=?, " + 
					" VOUCHER_STATUS=?, VOUCHER_PURCHASE_ORDER_NUMBER=? WHERE VOUCHER_REQUEST_ID=? ";

		    case 3:
			    return " DELETE FROM  HRMS_D1_REQ_BACK_OUT WHERE VOUCHER_REQUEST_ID=? ";

		    default:
			    return "Framework could not the query number specified";
		}
	}
}
