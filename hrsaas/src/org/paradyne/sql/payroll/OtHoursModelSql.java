/**
 * 
 */
package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;


/**
 * @author Hemant
 *
 */
public class OtHoursModelSql extends SqlBase{
	
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_TAX_INVESTMENT (INV_CODE,INV_NAME,INV_LIMIT1,INV_LIMIT2,INV_ORDER,INV_CHAPTER,INV_SECTION,INV_TYPE)" 
		//             + " VALUES(?,?,?,?,?,?,?,?)";
		+ " VALUES((SELECT NVL(MAX(INV_CODE),0)+1 FROM HRMS_TAX_INVESTMENT),?,?,?,?,?,?,?)";
						
		case 2: return " UPDATE HRMS_TAX_INVESTMENT SET INV_NAME=?,INV_LIMIT1=?,INV_LIMIT2=?,INV_ORDER=?,INV_CHAPTER=?,INV_SECTION=?,INV_TYPE=? WHERE INV_CODE=?";
		
		case 3: return " DELETE FROM HRMS_TAX_INVESTMENT WHERE INV_CODE=?";
		
		case 4: return " SELECT  INV_NAME,INV_LIMIT1,INV_LIMIT2,INV_ORDER,INV_CHAPTER,INV_SECTION,INV_TYPE FROM HRMS_TAX_INVESTMENT WHERE INV_CODE=?";
		
		case 5:return "SELECT INV_CODE,INV_NAME,INV_LIMIT1,INV_LIMIT2,INV_ORDER,INV_CHAPTER,INV_SECTION,INV_TYPE FROM HRMS_TAX_INVESTMENT";
			
		
		default : return "Framework could not EXECUTE the query number specified";			
		}
	}

}
