/**
 * 
 */
package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author ritac
 * @modified by  saipavan
 *
 */
public class ESIMasterModelSql extends SqlBase {
	
	public String getQuery(int id) 
	{
		switch(id)
		{
		case 1: return "INSERT INTO HRMS_ESI(ESI_CODE,ESI_DATE,ESI_GROSS_UPTO,ESI_EMP_PERCENTAGE,ESI_COMP_PERCENTAGE,ESI_DEBIT_CODE,ESI_FORMULA,ESI_MONTH_START, ESI_MONTH_END)"
					+" VALUES ((SELECT NVL(MAX(ESI_CODE),0)+1 FROM HRMS_ESI ),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?)";
		
		case 2:return "UPDATE HRMS_ESI SET ESI_DATE=TO_DATE(?,'DD-MM-YYYY'),ESI_GROSS_UPTO=?,ESI_EMP_PERCENTAGE=?,ESI_COMP_PERCENTAGE=?,ESI_DEBIT_CODE=?,ESI_FORMULA=?, ESI_MONTH_START=?, ESI_MONTH_END=? WHERE ESI_CODE =?";
		
		case 3:return "DELETE FROM HRMS_ESI WHERE ESI_CODE=? ";
		
		case 4: return "SELECT ESI_CODE,TO_CHAR(ESI_DATE,'DD-MM-YYYY'),NVL(ESI_GROSS_UPTO,'') FROM HRMS_ESI ORDER BY ESI_CODE "; 

		default: return  "hiiiiiiii";
		}
		
	}


}
