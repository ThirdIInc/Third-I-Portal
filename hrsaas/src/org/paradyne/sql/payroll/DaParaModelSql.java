/**
 * 
 */
package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;

/**
 * @author Venkatesh
 *  @author LAKKICHAND
 */
public class DaParaModelSql extends SqlBase{
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_DA_PARAMETER (DA_CODE,DA_RATE,DA_EFFECTIVE_DATE) " 
						+  "  VALUES((SELECT NVL(MAX(DA_CODE),0)+1 FROM HRMS_DA_PARAMETER),?,TO_DATE(?,'DD-MM-YYYY'))";
		
		case 2: return " UPDATE HRMS_DA_PARAMETER SET DA_RATE=?,DA_EFFECTIVE_DATE=TO_DATE(?,'DD-MM-YYYY') WHERE DA_CODE=?";
		
		case 3: return " DELETE FROM HRMS_DA_PARAMETER WHERE DA_CODE=?";
		
		case 4: return " SELECT  DA_CODE,DA_RATE,TO_CHAR(DA_EFFECTIVE_DATE,'DD-MM-YYYY') FROM HRMS_DA_PARAMETER WHERE DA_CODE=? ";
		
		case 5:return "SELECT DA_CODE,DA_RATE,TO_CHAR(DA_EFFECTIVE_DATE,'DD-MM-YYYY') FROM HRMS_DA_PARAMETER ORDER BY DA_CODE";
			
		case 6:return " UPDATE HRMS_CREDIT_HEAD SET CREDIT_FORMULA=? WHERE CREDIT_CODE=3";
		
		default : return "Framework could not EXECUTE the query number specified";
		
		}
	}

}
