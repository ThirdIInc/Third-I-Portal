package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class CastCenterManagementModelSql extends SqlBase{
	
	
	public String getQuery(int code)
	{
		switch( code)
		{
		case 1 : return " INSERT INTO HRMS_COST_CENTER (COST_CENTER_ID,COST_CENTER_ABBR, COST_CENTER_NAME) VALUES (? , ? , ?)";
		
		case 2 : return " INSERT INTO HRMS_SUB_COST_CENTER ( COST_CENTER_ID, SUB_COST_CENTER_ABBR,SUB_COST_CENTER_NAME, SUB_COST_CENTER_ID) "
                        +" VALUES (? ,? ,? ,? )";
		
		case 3 : return " DELETE FROM HRMS_COST_CENTER WHERE COST_CENTER_ID = ? ";
		
		case 4 : return " DELETE FROM HRMS_SUB_COST_CENTER WHERE COST_CENTER_ID= ? ";
						
		case 6: return " SELECT  HRMS_COST_CENTER.COST_CENTER_ID,HRMS_COST_CENTER.COST_CENTER_ABBR, HRMS_COST_CENTER.COST_CENTER_NAME,SUB_COST_CENTER_ABBR ,SUB_COST_CENTER_NAME FROM HRMS_COST_CENTER"
					+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SUB_COST_CENTER.COST_CENTER_ID)"
					+" ORDER BY HRMS_COST_CENTER.COST_CENTER_ID";
		
		
		default : return "HIIIIIIIIIIIIII";
		}
	}


}
