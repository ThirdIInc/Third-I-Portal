package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

public class BusinessReqDocApprovalModelSql extends SqlBase {

	/**
	 * (non-Javadoc).
	 * 
	 * @see org.paradyne.lib.SqlBase#getQuery(int)
	 * @return String.
	 * @param id -
	 *            Used to choose spesific query.
	 */
	public String getQuery(final int id) {
		switch (id) {

		case 1:
			return "	INSERT INTO HRMS_D1_BUSINESS_PATH (BUSINESS_CODE,BUSINESS_APPROVER_CODE, BUSINESS_COMMENTS, "
					+ "  BUSINESS_APPR_DATE, BUSINESS_PATH_ID, BUSINESS_STATUS, BUSINESS_APPROVER_TYPE, BUSINESS_DOC_TYPE, "
					+ " BUSINESS_UPLOAD_FILE) VALUES((SELECT NVL(MAX(BUSINESS_CODE),0)+1) FROM HRMS_D1_BUSINESS_PATH),?,?,SYSDATE,?,?,?,?,?)";

		case 2:
			return "UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_FORWARD_EMPID =? BUSINESS_CODE =? ";
		default:
			return "Framework could not the query number specified";
		}
	}
}
