/**
 * 
 */
package org.paradyne.sql.payroll.pf;

import org.paradyne.lib.SqlBase;

/**
 * @author AA0554
 *
 */
public class PfInterestCalModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id) {
		case 1: return "INSERT INTO HRMS_PF_LEDGER(PF_CODE, PF_EMPID, PF_FROM_YEAR, PF_TO_YEAR, PF_OPENING_BAL, PF_CLOSING_BAL, PF_INT_AMOUNT, PF_WITHDRAWAL, PF_DEPOSITS)"
						+" VALUES((SELECT MAX(NVL(PF_CODE,0)) +1 FROM HRMS_PF_LEDGER ),?,?,?,?,?,?,?,?)";
		
		case 2: return "UPDATE HRMS_PF_LEDGER SET PF_OPENING_BAL=?, PF_CLOSING_BAL=?, PF_INT_AMOUNT=?, PF_WITHDRAWAL=?, PF_DEPOSITS=?"
						+" WHERE PF_EMPID=? AND PF_FROM_YEAR=?";
			
		default:return "";
			
		}
	}
}
