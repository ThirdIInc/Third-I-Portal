package org.paradyne.sql.common;

import org.paradyne.lib.SqlBase;

public class UserModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " DELETE FROM HRMS_LOGIN WHERE LOGIN_CODE=?";
		

		default:
			return "Framework could not the query number specified";

		}
	}


}
