package org.paradyne.sql.admin.master;
import org.paradyne.lib.SqlBase;

/**
 * 
 * @author Krishna
 * Date:07-10-2008
 */

public class TaskProjectModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {

		case 1:
			return "INSERT INTO HRMS_TASK_PROJECT(PROJECT_CODE,PROJECT_NAME,IS_ACTIVE)"
					+ "VALUES((SELECT NVL(MAX(PROJECT_CODE),0)+1 FROM HRMS_TASK_PROJECT),?,?)";
		case 2:
			return "UPDATE HRMS_TASK_PROJECT SET PROJECT_NAME=? , IS_ACTIVE=? WHERE PROJECT_CODE =? ";
		case 3:
			return "DELETE FROM HRMS_TASK_PROJECT WHERE PROJECT_CODE=?";
		case 4:
			return "SELECT PROJECT_CODE,PROJECT_NAME, DECODE(IS_ACTIVE,'Y','YES','N','NO') FROM HRMS_TASK_PROJECT order by upper(PROJECT_NAME)";
		default:
			return "Framework could not the query number specified";
		}

	}

}
