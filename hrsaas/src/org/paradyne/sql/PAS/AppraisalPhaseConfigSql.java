package org.paradyne.sql.PAS;
import org.paradyne.lib.SqlBase;

public class AppraisalPhaseConfigSql extends SqlBase{

	
		public String getQuery(int id){
			
		
			switch (id) {
			
					case 1 : return "INSERT INTO HRMS_MP_EDUCATION(APPR_ID, APPR_PHASE_ID, APPR_PHASE_NAME, APPR_PHASE_ORDER,"
								   +" APPR_PHASE_STATUS, APPR_PHASEWISE_RATING, APPR_PHASE_WEIGHT-AGE, APPR_PHASE DESCRIPTION) "
								   +" VALUES((SELECT NVL(MAX(APPR_PHASE_ID),0)+1  FROM PAS_APPR_PHASE_CONFIG),?,?,?,?,?,?,?) ";
					
					case 2 : return "";
					
					/*case 2 : return "UPDATE HRMS_MP_EDUCATION SET EDUCATION_DEGREE=?,EDUCATION_INSTITUTE=?,EDUCATION_UNIVERSITY=?,EDUCATION_YEAR_PASSING=?,"
					      +" EDUCATION_OBT_MARKS=?,EDUCATION_OBT_PERCENTAGE=? EDUCATION_QUALIFICATION=? EDUCATION_COMP_LITER=? WHERE EDUCATION_RECRUIT_ID=?";
					
					case 3 : return "DELETE FROM HRMS_MP_EDUCATION WHERE EDUCATION_RECRUIT_ID=?";*/
		
					default: return "hi";
				
			}
			
	
	
	
		}
}
