package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class DisciplineModelSql extends SqlBase {
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_DISCIPLINE (DISCP_ID,DISCP_NAME,DISCP_DESC,DISCP_PARENT_CODE) " 
						+"VALUES((SELECT NVL(MAX(DISCP_ID),0)+1 FROM HRMS_DISCIPLINE ),?,?,?)";
		
		case 2: return " UPDATE HRMS_DISCIPLINE SET DISCP_NAME=?,DISCP_DESC=?,DISCP_PARENT_CODE=? WHERE DISCP_ID=?";
		
		case 3: return " DELETE FROM HRMS_DISCIPLINE WHERE DISCP_ID=?";
		
		case 4: return " SELECT  DISCP_ID,DISCP_NAME,DISCP_DESC,DISCP_PARENT_CODE FROM HRMS_DISCIPLINE WHERE DISCP_ID=?";
		
		case 5: return "SELECT * FROM  HRMS_DISCIPLINE ORDER BY DISCP_ID";
		case 6: return " SELECT  DISCP_ID,DISCP_NAME,DISCP_DESC FROM HRMS_DISCIPLINE  ORDER BY  DISCP_ID";
		default : return "Framework could not the query number specified";			
		}
	}

}
