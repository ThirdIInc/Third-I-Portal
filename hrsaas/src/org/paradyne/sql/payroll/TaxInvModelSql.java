/**
 * 
 */
package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;


/**
 * @author Hemant and Pradeep Kumar Sahoo
 * Date:02-12-2008
 *
 */
public class TaxInvModelSql extends SqlBase{
	
	public String getQuery(int id) {
		switch(id) {
		
		case 1: return " INSERT INTO HRMS_TAX_INVESTMENT (INV_CODE, INV_NAME, INV_SECTION, INV_CHAPTER, INV_LIMIT1, INV_TYPE, INV_GROUP, INV_OTHER_FLAG, INV_IS_INCLUDE_IN_80C_LIMIT)" 
		+ " VALUES((SELECT NVL(MAX(INV_CODE),0)+1 FROM HRMS_TAX_INVESTMENT),?,?,?,?,?,?,?,?)";
						
		case 2: return " UPDATE HRMS_TAX_INVESTMENT SET INV_NAME=?, INV_SECTION=?, INV_CHAPTER=?, INV_LIMIT1=?, INV_TYPE=?, INV_GROUP=?, INV_OTHER_FLAG=?, INV_IS_INCLUDE_IN_80C_LIMIT=? WHERE HRMS_TAX_INVESTMENT.INV_CODE=?";
		
		default : return "Framework could not EXECUTE the query number specified";			
		}
	}

}
