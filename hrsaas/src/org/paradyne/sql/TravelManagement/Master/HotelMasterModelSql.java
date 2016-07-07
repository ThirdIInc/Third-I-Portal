package org.paradyne.sql.TravelManagement.Master;

import org.paradyne.lib.SqlBase;
/**
 * @author Nilesh Dhandare
 */
public class HotelMasterModelSql extends SqlBase {

	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_TRAVEL_HOTEL_MASTER(HOTEL_ID,HOTEL_NAME,HOTEL_TYPE,HOTEL_CONTACT_PERSON,HOTEL_ADDRESS,HOTEL_CITY,HOTEL_PHONE1,"
						+" HOTEL_PHONE2,HOTEL_EMAIL,HOTEL_REMARK,HOTEL_DIST_FROM_AIRPORT,HOTEL_ZONE,HOTEL_DINNER_PACKAGE,HOTEL_DINNER_PACKAGE_COST) "
						+ " VALUES((SELECT NVL(MAX(HOTEL_ID),0)+1 FROM HRMS_TRAVEL_HOTEL_MASTER ),?,?,?,?,?,?,?,?,?,?,?,?,?) ";  
  
     
		case 2: return " SELECT HOTEL_NAME,HOTEL_ADDRESS,HOTEL_ID "
		+ " FROM  HRMS_TRAVEL_HOTEL_MASTER "
		+ " ORDER BY HOTEL_ID ";
	
		case 3: return " UPDATE HRMS_TRAVEL_HOTEL_MASTER SET HOTEL_NAME =?,HOTEL_TYPE =?,HOTEL_CONTACT_PERSON =?,HOTEL_ADDRESS =?,HOTEL_CITY =?," 
						+" HOTEL_PHONE1 =?,HOTEL_PHONE2 =?,HOTEL_EMAIL =?,HOTEL_REMARK=?, HOTEL_DIST_FROM_AIRPORT=?, HOTEL_ZONE=?, HOTEL_DINNER_PACKAGE=?, HOTEL_DINNER_PACKAGE_COST=? WHERE HOTEL_ID=? " ; 
		
		case 4: return " DELETE FROM  HRMS_TRAVEL_HOTEL_MASTER WHERE HOTEL_ID=? ";
		
		default : return "Framework could not the query number specified";			
		}
	}
}




















