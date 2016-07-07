/**
 * Bhushan
 * May 7, 2008
**/

package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class BranchHolidayMasterModelSql extends SqlBase
{
	public String getQuery(int id)
	{
		switch (id)
		{
			case 1 : return " SELECT TO_CHAR(HRMS_HOLIDAY.HOLI_DATE, 'DD-MON-YYYY'), HOLI_DESC, " +
			" CASE WHEN HRMS_HOLIDAY_BRANCH.HOLI_DATE = HRMS_HOLIDAY.HOLI_DATE THEN 'Y' ELSE 'N' END APPLICABLE " +
			" FROM HRMS_HOLIDAY " +
			" LEFT JOIN HRMS_HOLIDAY_BRANCH ON(HRMS_HOLIDAY_BRANCH.HOLI_DATE = HRMS_HOLIDAY.HOLI_DATE AND CENTER_ID = ?) " +
			" WHERE TO_CHAR(HRMS_HOLIDAY.HOLI_DATE, 'YYYY') = ? ORDER BY HRMS_HOLIDAY.HOLI_DATE ";
			
			case 2 : return " DELETE FROM HRMS_HOLIDAY_BRANCH WHERE CENTER_ID = ? ";
			
			case 3 : return " INSERT INTO HRMS_HOLIDAY_BRANCH(CENTER_ID, HOLI_DATE,IS_ACTIVE) VALUES(?, TO_DATE(?, 'DD-MON-YYYY'), ?) ";

			default : return "";
		}
	}
}