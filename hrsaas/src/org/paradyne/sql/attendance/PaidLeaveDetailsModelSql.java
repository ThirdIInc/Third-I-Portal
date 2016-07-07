package org.paradyne.sql.attendance;

import org.paradyne.lib.SqlBase;

public class PaidLeaveDetailsModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {
		case 1:
			return "SELECT LEAVE_ID, LEAVE_NAME FROM HRMS_LEAVE  ORDER BY LEAVE_ID ";

		case 2:
			return " SELECT LEAVE_ID,TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_CLOSING_BALANCE,0) "
					+ " FROM HRMS_LEAVE  "
					+ "	LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID = ?) "
					+ "	ORDER BY LEAVE_ID ";

		default:
			return " Query could not find";
		}
	}
}