package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

public class BusinessUnitModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_D1_BUSSINESS_UNIT(BUSS_UNIT_ID,BUSS_UNIT_CODE,BUSS_UNIT_NAME,BUSS_EMP_ID) "
					+ " VALUES(?,?,?,?)";
		case 2:
			return " UPDATE HRMS_D1_BUSSINESS_UNIT SET BUSS_UNIT_CODE=?,"
					+ " BUSS_UNIT_NAME=?, BUSS_EMP_ID=? WHERE BUSS_UNIT_ID=? ";

		case 3:
			return " SELECT BUSS_UNIT_ID,BUSS_UNIT_NAME  FROM  HRMS_D1_BUSSINESS_UNIT "
					+ " ORDER BY BUSS_UNIT_NAME ";

		case 4:
			return " DELETE FROM HRMS_D1_BUSSINESS_UNIT WHERE BUSS_UNIT_ID=?  ";

		default:
			return "Framework could not specified the query number";

		}
	}

}
