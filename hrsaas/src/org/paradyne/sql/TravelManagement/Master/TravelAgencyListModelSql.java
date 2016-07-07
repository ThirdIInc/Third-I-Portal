/*
 * Added by manish sakpal
 * 
 */


package org.paradyne.sql.TravelManagement.Master;
import org.paradyne.lib.SqlBase;
public class TravelAgencyListModelSql extends SqlBase
{
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO TMS_TRAVEL_AGENCY(AGENCY_CODE,AGENCY_NAME, AGENCY_CONTACT_PERSON, AGENCY_ADDRESS, AGENCY_PHONE_1, AGENCY_PHONE_2, AGENCY_EMAIL_1, AGENCY_EMAIL_2,AGENCY_CITY,AGENCY_TRAVEL_MODE_AVAIL)"
						+" VALUES((SELECT NVL(MAX(AGENCY_CODE),0)+1 FROM TMS_TRAVEL_AGENCY ),?,?,?,?,?,?,?,?,?)";  
  
     
		
		case 2: return " UPDATE TMS_TRAVEL_AGENCY SET AGENCY_NAME =?,AGENCY_CONTACT_PERSON =?,AGENCY_ADDRESS =?,AGENCY_PHONE_1 =?,AGENCY_PHONE_2 =?,AGENCY_EMAIL_1 =?,AGENCY_EMAIL_2 =?,AGENCY_CITY=? ,AGENCY_TRAVEL_MODE_AVAIL=? WHERE AGENCY_CODE=? " ; 
		
		case 3: return " DELETE FROM  TMS_TRAVEL_AGENCY WHERE AGENCY_CODE=? ";
		
		default : return "Framework could not the query number specified";			
		}
	}

}
























