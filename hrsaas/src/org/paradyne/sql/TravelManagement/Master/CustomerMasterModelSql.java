/*
* Added by manish sakpal
* 
*/

package org.paradyne.sql.TravelManagement.Master;

import org.paradyne.lib.SqlBase;

public class CustomerMasterModelSql extends SqlBase
{
	
	public String getQuery(int id) 
	{
			switch(id) 
			{
			case 1: return " INSERT INTO TMS_TRAVEL_CUSTOMER (TRAVEL_CUST_ID,TRAVEL_CUST_NAME, TRAVEL_CUST_CONTACT_PERSON,TRAVEL_CUST_ADDRESS,TRAVEL_CUST_PHONE,TRAVEL_CUST_EMAIL,TRAVEL_CUST_CITY, TRAVEL_PROJECT_ID)"
							 +" VALUES((SELECT NVL(MAX(TRAVEL_CUST_ID),0)+1 FROM TMS_TRAVEL_CUSTOMER ),?,?,?,?,?,?,?)";	     
							 
			 
			case 2: return " UPDATE TMS_TRAVEL_CUSTOMER SET TRAVEL_CUST_NAME =?,TRAVEL_CUST_CONTACT_PERSON =?,TRAVEL_CUST_ADDRESS =?,TRAVEL_CUST_PHONE =?,TRAVEL_CUST_EMAIL =?,TRAVEL_CUST_CITY=?,TRAVEL_PROJECT_ID=? WHERE TRAVEL_CUST_ID=? " ; 
			
			case 3: return " DELETE FROM  TMS_TRAVEL_CUSTOMER WHERE TRAVEL_CUST_ID=? ";
			
			default : return "Framework could not the query number specified";			
			}
	}

}	
	

