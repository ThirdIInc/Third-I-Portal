package org.paradyne.sql.Asset;

import org.paradyne.lib.SqlBase;

/**
 * 
 * @author Krishna
 * 
 */

public class UnitMasterModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_UNIT_MASTER(UNIT_CODE,UNIT_NAME,UNIT_ISACTIVE)"
					+ "VALUES((SELECT NVL(MAX(UNIT_CODE),0)+1 FROM HRMS_UNIT_MASTER),?,?)";
		case 2:
			return " UPDATE HRMS_UNIT_MASTER SET UNIT_NAME=?,UNIT_ISACTIVE=? WHERE UNIT_CODE =? ";
		case 3:
			return " DELETE FROM HRMS_UNIT_MASTER WHERE UNIT_CODE=? ";
		case 4:
			return " SELECT UNIT_CODE,UNIT_NAME,CASE WHEN UNIT_ISACTIVE ='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_UNIT_MASTER ";
		default:
			return "Framework could not the query number specified";
		}

	}

}
