/*
 * Author Anantha lakshmi
 */
package org.paradyne.sql.PAS;
import org.paradyne.lib.SqlBase;
public class DepartmentRatingModelSql extends SqlBase 
{
	public String getQuery(int id) 
	{
		switch (id) 
		{
			/* Y Axis Data */
			case 1 : return " INSERT INTO PAS_APPR_DEPT_RATING(DEPT_MOD_RATING,DEPT_RATING,DEPT_ID,APPR_ID)"+"  VALUES(?,?,?,?)";
			case 2 : return " SELECT DEPT_NO FROM HRMS_DEPT WHERE DEPT_NAME=?";
			case 3 : return " DELETE FROM PAS_APPR_DEPT_RATING WHERE APPR_ID=?";
			default : return " Framework could not the query number specified";
		}
	
	}
}

