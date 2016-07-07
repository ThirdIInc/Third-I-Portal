package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class RelationModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {
		case 1:
			return " INSERT INTO HRMS_RELATION (RELATION_CODE,RELATION_NAME,IS_ACTIVE) "
					+ "VALUES((SELECT NVL(MAX(RELATION_CODE),0)+1 FROM HRMS_RELATION ),?,?)";

		case 2:
			return " UPDATE HRMS_RELATION SET RELATION_NAME=?,IS_ACTIVE=? WHERE RELATION_CODE=?";

		case 3:
			return " DELETE FROM HRMS_RELATION WHERE RELATION_CODE=?";

		case 4:
			return " SELECT * FROM HRMS_RELATION WHERE RELATION_CODE=?";
			
		

		case 5:
			return " SELECT RELATION_CODE,RELATION_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_RELATION ORDER BY upper(RELATION_NAME)";

		default:
			return "Framework could not the query number specified";
		}
	}
}