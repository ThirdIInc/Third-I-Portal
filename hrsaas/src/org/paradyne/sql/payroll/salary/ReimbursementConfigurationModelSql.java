package org.paradyne.sql.payroll.salary;

import org.paradyne.lib.SqlBase;

public class ReimbursementConfigurationModelSql extends SqlBase {

	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_CASH_CONFIG (ACCOUNTANT_EMP_ID, ACCOUNTANT_DIV_ID) VALUES(?,?)";
						
		case 2: return " UPDATE HRMS_CASH_CONFIG SET ACCOUNTANT_DIV_ID=? WHERE ACCOUNTANT_EMP_ID=? AND ACCOUNTANT_DIV_ID=?";
		
		case 3: return " DELETE FROM HRMS_CASH_CONFIG WHERE ACCOUNTANT_EMP_ID=? AND ACCOUNTANT_DIV_ID=?";
		
		case 4: return " SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_LNAME, DIV_NAME, ACCOUNTANT_EMP_ID, ACCOUNTANT_DIV_ID "
				+ " FROM HRMS_CASH_CONFIG "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CONFIG.ACCOUNTANT_EMP_ID) "
				+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_CASH_CONFIG.ACCOUNTANT_DIV_ID) "
				+ " WHERE ACCOUNTANT_EMP_ID = ? AND ACCOUNTANT_DIV_ID=?";
		
		default : return "Framework could not EXECUTE the query number specified";			
		}
	}
}
