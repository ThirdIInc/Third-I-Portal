package org.paradyne.sql.admin.master;
import org.paradyne.lib.SqlBase;
/*
 * author:Pradeep Kumar Sahoo
 * Date:24.08.2007
 */
public class ForeignExchangeModelSql extends SqlBase {
	
	
	public String getQuery(int id) {
		switch(id) {
		
		case 1:return "INSERT INTO HRMS_COUNTRY(COUNTRY_CODE,COUNTRY_NAME,COUNTRY_CURRENCY,COUNTRY_EXCHANGE_RATE,COUNTRY_EXCHANGE_TYPE) "
					+" VALUES((SELECT NVL(MAX(COUNTRY_CODE),0)+1 FROM HRMS_COUNTRY),?,?,?,?)";
		
		case 2:return "UPDATE HRMS_COUNTRY SET COUNTRY_NAME = ?,COUNTRY_CURRENCY=? ,COUNTRY_EXCHANGE_RATE = ?, COUNTRY_EXCHANGE_TYPE=?  WHERE  "
					+"  COUNTRY_CODE= ? ";
		
		case 3:return " DELETE FROM HRMS_COUNTRY WHERE COUNTRY_CODE = ? ";
		
		case 4:return " SELECT COUNTRY_CODE,NVL(COUNTRY_NAME,' '),NVL(COUNTRY_CURRENCY,''),NVL(COUNTRY_EXCHANGE_RATE,''),COUNTRY_EXCHANGE_TYPE FROM HRMS_COUNTRY WHERE COUNTRY_CODE = ? ";
		
		case 5: return "SELECT COUNTRY_CODE,NVL(COUNTRY_NAME,' '),NVL(COUNTRY_CURRENCY,''),NVL(COUNTRY_EXCHANGE_RATE,'') FROM HRMS_COUNTRY ORDER BY COUNTRY_CODE  ";
		
		default: return "Query does not exist";
		
		}
		
	}

}
