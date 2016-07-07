package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class ScoreBalancerModelSql extends SqlBase {
	
	public String getQuery(int i){
		
		switch(i){
		
		
		case 1 : return "  SELECT PAS_APPR_SCORE.EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME),NVL(HRMS_DEPT.DEPT_NAME,'  '), "
						+" APPR_SCORE,APPR_ADJUST_FACTOR,APPR_SCORE_ADJUST,APPR_FINAL_SCORE,TEMPLATE_CODE,DECODE(APPR_SCORE_FINALIZE,'Y','true','false') "
						+" FROM PAS_APPR_SCORE " 
						+" LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_SCORE.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
						+" LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
						+" WHERE APPR_ID = ? ";
		
		case 2 : return "  SELECT PAS_APPR_TRACKING.EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME) FROM PAS_APPR_TRACKING "
						+" LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_TRACKING.NEXT_APPRAISER = HRMS_EMP_OFFC.EMP_ID) " 
						+" WHERE APPR_ID = ?  ";
		
		case 3 : return " UPDATE PAS_APPR_SCORE SET APPR_SCORE_ADJUST = ?,APPR_ADJUST_FACTOR = ?,APPR_FINAL_SCORE= ?,APPR_FINAL_SCORE_VALUE = ?,APPR_FINAL_SCORE_DESC = ? WHERE  EMP_ID = ? AND APPR_ID = ? AND TEMPLATE_CODE = ? ";
		
		
		case 4: return " SELECT APPR_SCORE_FROM ,APPR_SCORE_VALUE,APPR_SCORE_DESCRIPTION FROM "
							+" PAS_APPR_OVERALL_RATING "
							+" WHERE APPR_ID = ? ORDER BY APPR_SCORE_FROM DESC ";
		
		
		default : return " ";
		}
	}

}
