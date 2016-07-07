package org.paradyne.sql.LMS;

import org.paradyne.lib.SqlBase;

public class EmployeeTypeModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {
		case 1:
			return " INSERT INTO LMS_EMPLOYEE_TYPE(HRMS_TYPE_ID,LMS_TYPE_ID ) "
					+ " VALUES(?,?)";

		case 2:
			return " SELECT TYPE_ID,TYPE_NAME " + " FROM HRMS_EMP_TYPE "
					+ " ORDER BY TYPE_ID ";

		default:
			return "Framework could not the query number specified";
		}
	}

}
