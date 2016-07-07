package org.paradyne.sql.TravelManagement.Master;

import org.paradyne.lib.SqlBase;

public class CityGradeModelSql extends SqlBase {

	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_CITY_GRADE(GRADE_ID,GRADE_NAME,GRADE_CITIES ) "
  + " VALUES((SELECT NVL(MAX(GRADE_ID),0)+1 FROM HRMS_CITY_GRADE   ),?,?)";  
  
     
		case 2: return " SELECT GRADE_ID,GRADE_NAME,GRADE_CITIES "
		+ " FROM  HRMS_CITY_GRADE "
		+ " ORDER BY GRADE_ID ";
	
		
		case 3: return " UPDATE HRMS_CITY_GRADE SET GRADE_NAME =?,GRADE_CITIES =? WHERE GRADE_ID=? " ; 
		
		case 4: return " DELETE FROM  HRMS_CITY_GRADE WHERE GRADE_ID=? ";
		
		default : return "Framework could not the query number specified";			
		}
	}
}
















