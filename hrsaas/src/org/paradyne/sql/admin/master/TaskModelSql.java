package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class TaskModelSql extends SqlBase {
	public String getQuery(int id) 
	{
		switch(id)
		{
						
		case 1:
			return "INSERT INTO HRMS_TASK_TYPE(TYPE_CODE,TYPE_NAME)"
					+ "VALUES((SELECT NVL(MAX(TYPE_CODE),0)+1 FROM HRMS_TASK_TYPE),?)";
		case 2:
			return "UPDATE HRMS_TASK_TYPE SET TYPE_NAME=? WHERE TYPE_CODE =? ";
		case 3:
			return "DELETE FROM HRMS_TASK_TYPE WHERE TYPE_CODE=?";
		case 4:
			return "SELECT TYPE_CODE,TYPE_NAME FROM HRMS_TASK_TYPE ORDER BY TYPE_CODE";
		default:
			return "Framework could not the query number specified";
			
		}
	}
}
