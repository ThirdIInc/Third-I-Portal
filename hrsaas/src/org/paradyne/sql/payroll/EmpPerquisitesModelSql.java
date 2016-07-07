package org.paradyne.sql.payroll;
import org.paradyne.lib.SqlBase;
public class EmpPerquisitesModelSql extends SqlBase{

	public EmpPerquisitesModelSql() {
		
	}
	
	public String getQuery(int i){
		
		switch(i){
			case 1	: return " 	INSERT INTO HRMS_EMP_PERQUISITE(EMP_ID, PERQ_CODE, PERQ_AMT, FROM_YEAR, TO_YEAR) "
						   + " 	VALUES (?,?,?,?,?)";
			
			case 2	: return " 	UPDATE HRMS_EMP_PERQUISITE SET  PERQ_AMT= ?   " + //, DEBIT_APPLICABLE=?
				         	 " WHERE PERQ_CODE=? AND EMP_ID=?";
			
			case 3	: return " 	DELETE FROM HRMS_EMP_PERQUISITE WHERE EMP_ID = ? AND FROM_YEAR = ? AND TO_YEAR =?";
			
			case 4	: return "	SELECT PERQ_CODE,PERQ_NAME FROM HRMS_PERQUISITE_HEAD ORDER BY PERQ_CODE ";
			
			case 5 	: return "	SELECT PERQ_CODE FROM HRMS_EMP_PERQUISITE WHERE EMP_ID = ? ";
			
			default	: return "	Query Not Found";
		}
		
	}

}


