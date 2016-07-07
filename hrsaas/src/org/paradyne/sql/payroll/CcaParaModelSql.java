/**
 * 
 */
package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;

/**
 * @author Venkatesh
 *
 */
public class CcaParaModelSql extends SqlBase{
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_PAY_CCA_PARAMETER (CCA_CODE,EQUI_SAL_FROM,EQUI_SAL_TO,CCA_AMT)" 
						+" VALUES((SELECT NVL(MAX(CCA_CODE),0)+1 FROM HRMS_PAY_CCA_PARAMETER),TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?)";
		
		case 2: return " UPDATE HRMS_PAY_CCA_PARAMETER SET EQUI_SAL_FROM=?,EQUI_SAL_TO=?,CCA_AMT=? WHERE CCA_CODE=?";
		
		case 3: return " DELETE FROM HRMS_PAY_CCA_PARAMETER WHERE CCA_CODE=?";
		
		case 4: return " SELECT  CCA_CODE,TO_CHAR(EQUI_SAL_FROM,'DD-MM-YYYY'),TO_CHAR(EQUI_SAL_TO,'DD-MM-YYYY'),CCA_AMT FROM HRMS_PAY_CCA_PARAMETER WHERE CCA_CODE=? ";
		
		case 5:return "SELECT CCA_CODE,TO_CHAR(EQUI_SAL_FROM,'DD-MM-YYYY'),TO_CHAR(EQUI_SAL_TO,'DD-MM-YYYY'),CCA_AMT FROM HRMS_PAY_CCA_PARAMETER order by cca_code";
			
		default : return "Framework could not EXECUTE the query number specified";			
		}
	}

}
