package org.paradyne.sql.leave;

import org.paradyne.lib.SqlBase;

public class LeaveModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {
		case 1:
			return "SELECT LEAVE_CODE, LEAVE_NAME FROM HRMS_LEAVE";

		case 2:
			return "INSERT INTO HRMS_LEAVE (LEAVE_ID,LEAVE_NAME) "
					+ " VALUES (?,?)";

		default:
			return "Framework could not the query number specified";
		}
	}
}