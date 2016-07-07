package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

public class SalaryPlanModelSql extends SqlBase {
	
	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_D1_HIRE_ZIP(ZIP_ID,ZIP_CODE,SALARY_PLAN) "
					+ " VALUES(?,?,?)";
		case 2:
			return " UPDATE HRMS_D1_HIRE_ZIP SET ZIP_CODE=?,SALARY_PLAN=? WHERE ZIP_ID=?  ";
		
		case 3:
			return" SELECT ZIP_ID,ZIP_CODE FROM  HRMS_D1_HIRE_ZIP "
			        + " ORDER BY ZIP_CODE ";
		
		case 4:
			return " DELETE from HRMS_D1_HIRE_ZIP WHERE ZIP_ID=?  ";
		
		
		default:
			return "Framework could not the query number specified";

		
		
		
		}
	}

}
