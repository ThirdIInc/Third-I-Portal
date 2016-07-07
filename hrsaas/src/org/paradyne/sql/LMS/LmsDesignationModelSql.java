package org.paradyne.sql.LMS;

import org.paradyne.lib.SqlBase;

public class LmsDesignationModelSql extends SqlBase {
	public String getQuery(int id) {

		switch (id) {
		case 1: return " INSERT INTO LMS_DESIGNATION (HRMS_ROLE_ID,LMS_ROLE_ID) VALUES(?,?) ";
		
		default:
			return " Framework could not find query number specified";
		}
	}
}