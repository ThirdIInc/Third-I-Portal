/**
 * 
 */
package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

import com.sun.org.apache.bcel.internal.generic.RETURN;



/**
 * @author riteshr
 * @modified by Lakkichand
 * * @modified by Ganesh 16/9/2011
 */
public class BankModelSql extends SqlBase {
	
	public String getQuery(int id){
		
		switch(id){
		
		case 1: return " INSERT INTO HRMS_BANK (BANK_MICR_CODE,BANK_NAME,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_CITY,BRANCH_CODE,BANK_BSR_CODE,BANK_IFSC_CODE,IS_ACTIVE )"
						+"VALUES(?,?,?,?,?,?,?,?,?)";
		
		case 2: return  "SELECT BANK_MICR_CODE,BANK_NAME,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_CITY,BRANCH_CODE,BANK_BSR_CODE,BANK_IFSC_CODE,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO')  FROM HRMS_BANK WHERE BANK_MICR_CODE = ?";
		
		case 3: return " DELETE FROM HRMS_BANK WHERE BANK_ID=?";
		
		case 4: return  "SELECT BANK_MICR_CODE,BANK_NAME,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_CITY,BRANCH_CODE,IS_ACTIVE=? FROM HRMS_BANK ";
		
		case 5: return " UPDATE HRMS_BANK SET BANK_NAME=?,BRANCH_NAME=?,BRANCH_ADDRESS=?,BRANCH_CITY=?,BRANCH_CODE=? ,BANK_MICR_CODE=? ,BANK_BSR_CODE=? ,BANK_IFSC_CODE =? , IS_ACTIVE=?  WHERE BANK_MICR_CODE=?";
		
		//case 6: return " SELECT BANK_MICR_CODE,BANK_NAME,BRANCH_NAME,BRANCH_CITY FROM HRMS_BANK  ORDER BY  BANK_MICR_CODE";
		case 6: return " SELECT BANK_ID,BANK_NAME FROM HRMS_BANK WHERE BANK_TYPE='P' ORDER BY  BANK_NAME ASC";
		
		default: return "query no. is not specified ";
		}
		
		
	}

}
