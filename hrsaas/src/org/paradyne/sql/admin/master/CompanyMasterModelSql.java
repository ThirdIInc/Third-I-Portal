/**
 * 
 */
package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author balajim
 *
 */
public class CompanyMasterModelSql extends SqlBase {
	
	public String getQuery(int id) 
	{
		switch (id) {
				case 1: return " INSERT INTO HRMS_COMPANY (COMPANY_CODE,COMPANY_NAME,COMPANY_ADDRESS,COMPANY_CITYID,COMPANY_PIN,COMPANY_TELPHONE,COMPANY_WEBSITE,COMPANY_TAN,COMPANY_BANK,COMPANY_LOGO ,COMPANY_DISPLAY_NAME) "
							  +" VALUES ((SELECT NVL(MAX(COMPANY_CODE),0)+1 FROM HRMS_COMPANY),?,?,?,?,?,?,?,?,?,?) ";  
		
				case 2 : return " UPDATE HRMS_COMPANY SET COMPANY_NAME = ? ,COMPANY_ADDRESS = ? ,COMPANY_CITYID = ?,COMPANY_PIN = ? ,COMPANY_TELPHONE = ? ,COMPANY_WEBSITE = ? ,COMPANY_TAN = ?,COMPANY_BANK = ? , "
				                +" COMPANY_LOGO = ? ,COMPANY_DISPLAY_NAME=? WHERE COMPANY_CODE = ?";
				case 3 : return " DELETE FROM HRMS_COMPANY WHERE COMPANY_CODE = ? ";
				
				case 4: return " SELECT NVL(COMPANY_ADDRESS,''),HRMS_LOCATION.LOCATION_NAME,COMPANY_PIN,COMPANY_TELPHONE, "
					 +"COMPANY_WEBSITE,COMPANY_TAN,COMPANY_BANK ,NVL(COMPANY_LOGO,''),COMPANY_CODE,COMPANY_NAME,COMPANY_CITYID,COMPANY_DISPLAY_NAME FROM  HRMS_COMPANY  "
					 +"LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE = HRMS_COMPANY.COMPANY_CITYID)" ;
					
				
				default: return "Framework could not the query number specified";
		}
	}

}
