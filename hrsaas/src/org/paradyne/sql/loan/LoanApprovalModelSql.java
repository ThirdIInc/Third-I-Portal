/**
 * 
 */
package org.paradyne.sql.loan;

import org.paradyne.lib.SqlBase;

/**
 * @author tarunc
 *
 */
public class LoanApprovalModelSql extends SqlBase {

	public String getQuery(int id){
		switch (id) {
		case 1 : return "SELECT LOAN_APPL_CODE, LOAN_EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
						+" TO_CHAR(LOAN_APPL_DATE, 'DD-MM-YYYY'), LOAN_AMOUNT, LOAN_APPL_STATUS, LOAN_APPL_APPROVER, LOAN_APPL_LEVEL ,LOAN_CODE"
						+" FROM HRMS_LOAN_APPLICATION "
						+" INNER JOIN HRMS_EMP_OFFC ON (LOAN_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
						+" WHERE LOAN_APPL_STATUS = ? AND (LOAN_APPL_APPROVER= ? OR LOAN_APPL_ALTER_APPROVER=?) "
						+" ORDER BY LOAN_APPL_DATE DESC"; 
		
		case 2 : return "UPDATE HRMS_LOAN_APPLICATION SET LOAN_APPL_LEVEL = ?, LOAN_APPL_APPROVER = ? WHERE LOAN_APPL_CODE = ? ";
		
		case 3 : return "UPDATE HRMS_LOAN_APPLICATION SET LOAN_APPL_STATUS = ? WHERE LOAN_APPL_CODE = ?";
		
		case 4 : return "INSERT INTO HRMS_LOAN_PATH (LOAN_PATH_ID, LOAN_APPL_CODE, LOAN_APPROVER_CODE, LOAN_APPROVED_DATE, LOAN_COMMENTS, LOAN_APPL_STATUS, "
						+"LOAN_APPLIED_BY,LOAN_APPR_AMT) VALUES((SELECT NVL(MAX(LOAN_PATH_ID),0)+1 FROM HRMS_LOAN_PATH WHERE LOAN_APPL_CODE = ?), ?, ?, "
						+"TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'), ?, ?, ?,?)";

		default : return "";
		}
	}
}
