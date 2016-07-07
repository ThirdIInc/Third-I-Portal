/**
 * 
 */
package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;

/**
 * @author MuzaffarS
 *
 */
public class EmpCreditModelSql extends SqlBase {
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_EMP_CREDIT (CREDIT_CODE,CREDIT_APPLICABLE,CREDIT_AMT,EMP_ID) " 
						+" VALUES(?,?,?,?)";
		
		case 2: return " UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT=? WHERE CREDIT_CODE=? AND EMP_ID=?";
		
		case 3: return " DELETE FROM HRMS_EMP_CREDIT WHERE EMP_ID=?";
		
		case 4: return " SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
		
		case 5: return " SELECT CREDIT_CODE FROM HRMS_EMP_CREDIT WHERE EMP_ID = ? ";
		
		case 6: return " UPDATE HRMS_EMP_OFFC SET EMP_SAL_GRADE = ? WHERE EMP_ID=? ";
		
		case 7: return " 	INSERT INTO HRMS_EMP_PERQUISITE(PERQ_CODE,PERQ_AMT,EMP_ID) "
				  +" 	VALUES (?,?,?)";
		
		case 8: return " 	DELETE FROM HRMS_EMP_PERQUISITE WHERE EMP_ID = ? ";
		
		case 9: return " UPDATE HRMS_SALARY_MISC SET FORMULA_ID=?, GROSS_AMT=?, "
						+" TOT_MONTHLY_SAL = ?,TOT_ANNUAL_SAL =?,"
						+" TOT_ANNUAL_PERQUISITE =?,CTC =? "
						+" WHERE EMP_ID=?";
	
		default : return "Framework could not EXECUTE the query number specified";			
		}
	
	}

}

