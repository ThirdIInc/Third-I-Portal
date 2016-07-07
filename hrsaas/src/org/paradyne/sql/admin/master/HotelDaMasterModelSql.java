/**
 * 
 */
package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author radhas
 *
 */
public class HotelDaMasterModelSql extends SqlBase 
{
	public String getQuery(int id){
		switch(id){
		
		case 1: return "INSERT INTO HRMS_HOTEL_MASTER(HOTEL_ID,HOTEL_CLASSOFCITY,HOTEL_BASIC_FROM,HOTEL_BASIC_TO,HOTEL_RATE,HOTEL_DA_TYPE) VALUES((SELECT NVL(MAX(HOTEL_ID),0)+1 from HRMS_HOTEL_MASTER),?,?,?,?,?)";
		
		case 2: return "DELETE FROM HRMS_HOTEL_MASTER WHERE HOTEL_ID=?" ;
		
		case 3: return "UPDATE HRMS_HOTEL_MASTER SET HOTEL_BASIC_FROM=?,HOTEL_BASIC_TO=?,HOTEL_CLASSOFCITY=?,HOTEL_RATE=?,HOTEL_DA_TYPE=? WHERE HOTEL_ID=? ";
		
		case 4: return "SELECT HOTEL_ID,HOTEL_BASIC_FROM,HOTEL_BASIC_TO,HOTEL_CLASSOFCITY,HOTEL_RATE,DECODE(HOTEL_DA_TYPE,'hotel','Hotel','ordy','Ordinary') FROM HRMS_HOTEL_MASTER ORDER BY HOTEL_ID";
		
		case 5: return "SELECT HOTEL_BASIC_FROM,HOTEL_BASIC_TO,HOTEL_CLASSOFCITY,HOTEL_RATE,HOTEL_DA_TYPE FROM HRMS_HOTEL_MASTER";
		
		case 6: return "SELECT HOTEL_ID,HOTEL_BASIC_FROM,HOTEL_BASIC_TO FROM HRMS_HOTEL_MASTER ORDER BY HOTEL_ID ";
		
		default: return "QUERY NOT FOUND";
		}
		
}
}


