package org.paradyne.sql.conference;

import org.paradyne.lib.SqlBase;

public class VenueMasterModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id) {
		case 1 : return "INSERT INTO HRMS_CONF_VENUE (VENUE_CODE, VENUE_NAME, VENUE_RES_PERSON,VENUE_BRANCH,VENUE_ISACTIVE) "
						+"VALUES((SELECT NVL(MAX(VENUE_CODE),0)+1 FROM HRMS_CONF_VENUE),?,?,?,?)";
		
		case 2 : return "UPDATE HRMS_CONF_VENUE SET VENUE_NAME =?,VENUE_RES_PERSON=?,VENUE_BRANCH=?,VENUE_ISACTIVE =? WHERE VENUE_CODE = ?";
		
		case 3 : return "DELETE FROM HRMS_CONF_VENUE WHERE VENUE_CODE = ?";
		
		case 4 : return "SELECT VENUE_CODE, VENUE_NAME FROM HRMS_CONF_VENUE ORDER BY VENUE_CODE";
		
		case 5: return " DELETE FROM HRMS_CONF_VENUE WHERE VENUE_CODE = ? ";
		
		case 6: return " SELECT VENUE_CODE, VENUE_NAME,VENUE_BRANCH,NVL(CENTER_NAME,' '),VENUE_RES_PERSON,NVL(EMP_FNAME ||' '||EMP_MNAME   ||' '||EMP_LNAME,' '),CASE WHEN VENUE_ISACTIVE ='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_CONF_VENUE " 
					 +" LEFT JOIN HRMS_CENTER ON(CENTER_ID= VENUE_BRANCH)"
					 +" LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=VENUE_RES_PERSON) ORDER BY VENUE_NAME ";
		
		default : return "";
		}
	}
}
