/**
 * 
 */
package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;

/**
 * @author IBRAHIM
 *
 */
public class BonusAcqReportModelSql extends SqlBase {
	
	public String getQuery(int i){
		
		switch(i){
		
		case 1	: return "";
		
		default : return "QUERY NOT FOUND BONUS ACQ. REPORT MODEL";
		
		}
	}

}
