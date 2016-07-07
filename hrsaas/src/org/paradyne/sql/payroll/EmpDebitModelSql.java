/**
 * 
 */
package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;

/**
 * @author Ibrahim
 *
 */
public class EmpDebitModelSql extends SqlBase{

	/**
	 * 
	 */
	public EmpDebitModelSql() {
		// TODO Auto-generated constructor stub
	}
	
	public String getQuery(int i){
		
		switch(i){
			case 1	: return " 	INSERT INTO HRMS_EMP_DEBIT(DEBIT_CODE,DEBIT_APPLICABLE,DEBIT_AMT,EMP_ID) "
						  +" 	VALUES (?,?,?,?)";
			
			case 2	: return " 	UPDATE HRMS_EMP_DEBIT SET  DEBIT_AMT= ? , DEBIT_APPLICABLE=?  WHERE DEBIT_CODE=? AND EMP_ID=?";
			
			case 3	: return " 	DELETE FROM HRMS_EMP_DEBIT WHERE EMP_ID = ?";
			
			case 4	: return "	SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE ";
			
			case 5 	: return "	SELECT DEBIT_CODE FROM HRMS_EMP_DEBIT WHERE EMP_ID = ? ";
			
			default	: return "	Query Not Found";
		}
		
	}

}


