package org.paradyne.sql.probation;

import org.paradyne.lib.SqlBase;

public class DefineHRModelSql extends SqlBase {
	
	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_HR_SETTING(DIV_CODE, EMP_CODE) " + " VALUES(?,?)";
		
		case 2:
			return " INSERT INTO HRMS_HR_SETTING(BRANCH_CODE, EMP_CODE) " + " VALUES(?,?)";
			
		default:
			return "Framework could not the query number specified";
		}
	
	}
}
