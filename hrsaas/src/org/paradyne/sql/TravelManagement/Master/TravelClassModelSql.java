package org.paradyne.sql.TravelManagement.Master;

import org.paradyne.lib.SqlBase;

public class TravelClassModelSql extends SqlBase {

	public String getQuery(int id) {

		switch (id) {

		case 1:
			return " INSERT INTO HRMS_TMS_JOURNEY_CLASS(CLASS_ID,CLASS_NAME,CLASS_JOURNEY_ID,CLASS_STATUS,CLASS_LEVEL,CLASS_MIN_EXPENSE) "
					+ " VALUES(?,?,?,?,?,?)";

		case 2:
			return " UPDATE HRMS_TMS_JOURNEY_CLASS SET CLASS_NAME=?,CLASS_JOURNEY_ID=?,CLASS_STATUS=?,CLASS_MIN_EXPENSE=? WHERE CLASS_ID=?";

		case 3:
			return " DELETE FROM HRMS_TMS_JOURNEY_CLASS WHERE CLASS_ID=?";

		default:
			return " FRAMEWORK COULD NOT EXECUTE THE QUERY NUMBER SPECIFIED";
		}

	}

}
