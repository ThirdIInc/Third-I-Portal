package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

public class ApprovalSettingsModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_D1_APPROVAL_SETTINGS(APP_SETTINGS_ID,APP_EMAIL_ID,APP_SETTINGS_TYPE) "
					+ " VALUES(?,?,'W')";
		
		default:
			return "Framework could not the query number specified";

		}
	}

}
