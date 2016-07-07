package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class FinancialStatementModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_MONTH_FNY_STATEMENT (BASIC_CODE, DA_CODE, HRA_CODE, MB_CODE, OT_CODE, LE_CODE, BONUS_CODE, GRATUITY_CODE, WRK_CMP_CODE, PF_CODE, ESI_CODE, TAX_CODE, OTHER_DEBIT_CODE, RECOVERY_CODE, EMP_EPF_CODE, EMP_ESI_CODE) "
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		default:
			return "Framework could not the query number specified";

		}
	}
	
}
