package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

public class PostAppliedModelSql extends SqlBase {
	public String getQuery(int id) {
		switch(id) {
		case 1: return "INSERT INTO  HRMS_POST_APP(POST_CODE,POST_NAME)VALUES((SELECT NVL(MAX(POST_CODE),0)+1 FROM  HRMS_POST_APP),?)";
		case 2: return " DELETE FROM  HRMS_POST_APP WHERE POST_CODE=?";
		case 3: return " SELECT  QUALF_CODE,QUALF_NAME ORDER BY QUALF_CODE ";
		case 4: return " UPDATE HRMS_POST_APP SET POST_NAME=? WHERE POST_CODE=?";
		
		case 5: return " SELECT POST_CODE,POST_NAME FROM HRMS_POST_APP ";
		
		default : return "Framework could not the query number specified";			
		}
	}


}
