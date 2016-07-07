/**
 * 
 */
package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;

/**
 * @author Pankaj_Jain
 * 
 */
public class MonthlyArrearsModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {
		case 1 : return " INSERT INTO HRMS_ARREARS_2008(ARREARS_CODE,ARREARS_MONTH,ARREARS_YEAR,ARREARS_DAYS,"
					   +" ARREARS_REF_MONTH,ARREARS_REF_YEAR,ARREARS_TYPE,ARREARS_NET_AMT,ARREARS_CREDITS_AMT,"
					   +" ARREARS_DEBITS_AMT) VALUES((SELECT NVL(MAX(ARREARS_CODE),0)+1),?,?,?,?,?,?,?,?,?)";
		
		case 2 : return "";
		
		default : return "";
		}
	}
}
