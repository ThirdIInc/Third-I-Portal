/**
 * 
 */
package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author lakkichand
 * @date 25 APR 2007
 */
public class DebitHeadModelSql extends SqlBase{

	public String getQuery(int id){
		switch(id){
		
		case 1: return " INSERT INTO HRMS_DEBIT_HEAD(DEBIT_CODE,DEBIT_NAME,DEBIT_ABBR)"
						+" VALUES((SELECT NVL(MAX(DEBIT_CODE),0)+1 FROM HRMS_DEBIT_HEAD),?,?)";
		
		case 2: return " UPDATE HRMS_DEBIT_HEAD SET DEBIT NAME=?,DEBIT_ABBR=? WHERE DEBIT_CODE=?";
		
		case 3: return " DELETE FROM HRMS_DEBIT_HEAD WHERE DEBIT_CODE=?";
		
		case 4: return " SELECT * FROM HRMS_DEBIT_HEAD WHERE DEBIT_CODE=?";
		
		case 5: return " SELECT * FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";
		
		default: return " Framework could not find query number sepcified";
		}
	}
	
}
