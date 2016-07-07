/**
 * 
 */
package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author riteshr
 *
 */
public class PunishMasterModelSql extends SqlBase {
	
	
public String getQuery(int id){
		
		
		
		switch (id) {
		
		case 1 :  return " INSERT INTO HRMS_PUNISHMENT ( PUNISH_ID, PUNISH_NAME ,FINANCIAL_IMPLICATIONS,IS_MAJOR,PUNISH_SALARY) "
				+" VALUES((SELECT NVL(MAX( PUNISH_ID),0)+1 FROM HRMS_PUNISHMENT),?,?,?,?) "; 
		
		case 2: return " SELECT  PUNISH_ID,PUNISH_NAME, NVL(FINANCIAL_IMPLICATIONS,' ') FROM HRMS_PUNISHMENT ORDER BY upper(PUNISH_NAME) ";		
		case 4 :  return " UPDATE HRMS_PUNISHMENT  SET PUNISH_NAME = ?,FINANCIAL_IMPLICATIONS = ?, IS_MAJOR = ? ,PUNISH_SALARY=? "
								 +" WHERE PUNISH_ID = ? "; 
		
		case 5: return  " DELETE FROM  HRMS_PUNISHMENT  WHERE PUNISH_ID = ? "; 
		
		case 6:return " INSERT INTO HRMS_PUNISH_SALARY(PUNISH_ID,PUNISH_CREDIT_CODE,PUNISH_PERCENTAGE) VALUES(?,?,?)";
		
		case 7: return  " DELETE FROM  HRMS_PUNISH_SALARY  WHERE PUNISH_ID = ? "; 
		default: return " query no. is not specified  ";
		}
	}

	

}
