/**
 * 
 */
package org.paradyne.sql.loan;

import org.paradyne.lib.SqlBase;

/**
 * @author tarunc
 * @ modified by 
 * saipavan voleti
 * Date: 10-07-08
 *
 */
public class LoanMasterModelSql extends SqlBase {
	
	public String getQuery(int id){
		switch (id) {
		case 1 : return "INSERT INTO HRMS_LOAN_MASTER (LOAN_CODE,LOAN_DIV_CODE,LOAN_NAME,LOAN_DEBIT_CODE,LOAN_LIMIT,INT_TYPE, INT_RATE,TAXABLE, OTHER_LOAN_TERM, ADMIN_CODE, ACCOUNT_CODE,STD_RATE_SBI) "
				 		+"VALUES((SELECT NVL(MAX(LOAN_CODE), 0)+1 FROM HRMS_LOAN_MASTER),?,?,?,?,?,?,?,?,?,?,?)";
		
		case 2 : return "UPDATE HRMS_LOAN_MASTER SET LOAN_DIV_CODE = ? ,LOAN_NAME = ? ,LOAN_DEBIT_CODE = ? ,LOAN_LIMIT = ? ,INT_TYPE = ? , INT_RATE = ? ,TAXABLE = ? , OTHER_LOAN_TERM = ? , ADMIN_CODE = ? , ACCOUNT_CODE = ? ,STD_RATE_SBI = ?   WHERE LOAN_CODE = ?";
		
		case 3 : return "DELETE FROM HRMS_LOAN_MASTER WHERE LOAN_CODE = ?";
		
		case 4 : return "select LOAN_CODE, LOAN_NAME, DEBIT_NAME, NVL(LOAN_LIMIT, 0) ,IS_ADMIN_APPROVAL, 	ADMIN_CODE,OFFC.EMP_TOKEN ,OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ADMIN_NAME, 		IS_ACCOUNT_APPROVAL, ACCOUNT_CODE, OFFC1.EMP_TOKEN ,		OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS ACCOUNT_NAME FROM HRMS_LOAN_MASTER "
			 			+" inner JOIN  HRMS_DEBIT_HEAD ON(HRMS_LOAN_MASTER.LOAN_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE) LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_MASTER.ADMIN_CODE)  LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_LOAN_MASTER.ACCOUNT_CODE) "
			 			+" order by LOAN_CODE";

		default: return "Framework could not EXECUTE the query number specified";
		}
	}
}
