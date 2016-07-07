package org.paradyne.sql.eGov.reports;

import org.paradyne.lib.SqlBase;

public class LeaveCreditConfigeGovModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {
		
		
		case 1:
			return " DELETE FROM HRMS_LEAVE_CREDIT_CONFIG WHERE LEAVE_CONF_ID=?  ";
			
		case 2:
			return " UPDATE HRMS_LEAVE_CREDIT_CONFIG SET LEAVE_CODE=?,CREDIT_CODE=? WHERE LEAVE_CONF_ID=?  ";

		default:
			return "Framework could not the query number specified";
		}
		
	}
}
