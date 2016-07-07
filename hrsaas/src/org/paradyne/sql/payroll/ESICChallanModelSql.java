/**
 * 
 */
package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;

/**
 * @author Dipti
 *
 */
public class ESICChallanModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_ESI_CHALLAN (CHALLAN_CODE,CHALLAN_MONTH,CHALLAN_YEAR,CHALLAN_PAYMODE,CHALLAN_CHEQUENO,CHALLAN_DIVISION,CHALLAN_PAYDATE,CHALLAN_AMOUNT,CHALLAN_CHLN_NO,CHALLAN_TYPE,CHALLAN_ONHOLD, CHALLAN_EMPLOYEE_COUNT, CHALLAN_WAGES_AMOUNT, CHALLAN_EMPLOYEE_ESI, CHALLAN_EMPLOYER_ESI, CHALLAN_BANK_MICR)"
					+ " VALUES(?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?)";
		case 2:
			return " UPDATE HRMS_ESI_CHALLAN SET CHALLAN_MONTH=?, CHALLAN_YEAR=?, CHALLAN_PAYMODE=?, CHALLAN_CHEQUENO=?, CHALLAN_DIVISION=?, CHALLAN_PAYDATE=TO_DATE(?,'DD-MM-YYYY'), CHALLAN_AMOUNT=?, CHALLAN_CHLN_NO=?, CHALLAN_TYPE=?, CHALLAN_ONHOLD=?, CHALLAN_EMPLOYEE_COUNT=?, CHALLAN_WAGES_AMOUNT=?, CHALLAN_EMPLOYEE_ESI=?, CHALLAN_EMPLOYER_ESI=?, CHALLAN_BANK_MICR=? WHERE CHALLAN_CODE=?";
			
		case 3:
			return " DELETE FROM HRMS_ESI_CHALLAN WHERE CHALLAN_CODE = ?";
		
		default:
			return "Query Not Found";
		}
	}

}
