package org.paradyne.sql.payroll.pf;

import org.paradyne.lib.SqlBase;

public class PFSlipReportModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {
		case 1:
			return " SELECT PFT_INTEREST FROM HRMS_PFTRUST_CONF ";

		case 2:
			return " SELECT "
					+ "		SAL_GPFNO , "
					+ "		(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) AS EMP_NAME, "
					+ " 	TO_CHAR(PF_OPENING_BAL, '99,999,999.99') AS PF_OPENING_BAL, "
					+ " 	TO_CHAR(PF_DEPOSITS, '99,999,999.99') AS PF_DEPOSITS, "
					+ " 	TO_CHAR(PF_INT_AMOUNT, '99,999,999.99') AS PF_INT_AMOUNT, "
					+ " 	TO_CHAR((PF_OPENING_BAL + PF_DEPOSITS + PF_INT_AMOUNT), '99,999,999.99') AS TOTAL_AMOUNT, "
					+ " 	TO_CHAR(PF_WITHDRAWAL, '99,999,999.99') AS PF_WITHDRAWAL, "
					+ "  	TO_CHAR(PF_CLOSING_BAL, '99,999,999.99') AS PF_CLOSING_BAL "
					+ " FROM "
					+ "		HRMS_PF_LEDGER A "
					+ "		INNER JOIN HRMS_SALARY_MISC B ON (A.PF_EMPID = B.EMP_ID) "
					+ " 	INNER JOIN HRMS_EMP_OFFC C ON (B.EMP_ID = C.EMP_ID) ";

		default:
			return " Framework could not the query number specified ";
		}
	}
}
