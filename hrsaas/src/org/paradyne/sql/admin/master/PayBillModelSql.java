package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;
/**
 * @author pranali 
 * Date 25-04-07
 */
public class PayBillModelSql extends SqlBase{

	
	public String getQuery(int id) 
	{
		switch(id)
		{
			
			case 1: return " INSERT INTO HRMS_PAYBILL (PAYBILL_ID,PAYBILL_GROUP) " 
				           +"VALUES((SELECT NVL(MAX(PAYBILL_ID),0)+1 FROM HRMS_PAYBILL),?)";
			
			case 2: return " UPDATE HRMS_PAYBILL SET PAYBILL_GROUP=? WHERE PAYBILL_ID=?";
			
			case 3: return " DELETE FROM HRMS_PAYBILL WHERE PAYBILL_ID=?";
			
			case 4: return " SELECT PAYBILL_GROUP WHERE PAYBILL_ID=?";
			
			case 5: return " SELECT  PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL ORDER BY PAYBILL_ID" ;
			
			case 6: return "SELECT PAYBILL_ID,PAYBILL_GROUP FROM  HRMS_PAYBILL ORDER BY  PAYBILL_ID";
			default : return "Framework could not the query number specified";
			
		}
	}
}
