/**
 * 
 */
package org.paradyne.sql.loan;

import org.paradyne.lib.SqlBase;

/**
 * @author bhushand
 *
 */
public class LoanMisReportModelSql extends SqlBase {

	public String getQuery(int id){
		switch (id){
			case 1 : return "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, " 
							+"TO_CHAR(LOAN_APPL_DATE, 'DD-MM-YYYY'), LOAN_NAME, DECODE(LOAN_APPL_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected')status, "
							+"LOAN_AMOUNT, NVL(LOAN_SANCTION_AMOUNT, 0) " 
							+"FROM HRMS_LOAN_APPLICATION "
							+"INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "
							+"INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE) "
							+"LEFT JOIN HRMS_LOAN_PROCESS ON (HRMS_LOAN_PROCESS.LOAN_APPL_CODE = HRMS_LOAN_APPLICATION.LOAN_APPL_CODE)  ";
			
			default : return "Fram work could not found the query";
		}
	}
}
