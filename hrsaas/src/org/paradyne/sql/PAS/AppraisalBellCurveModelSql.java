package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class AppraisalBellCurveModelSql extends SqlBase 
{
	public String getQuery(int id) 
	{
		switch (id) 
		{
			/* Y Axis Data */
			case 1 : return " SELECT APPR_SCORE_VALUE FROM PAS_APPR_OVERALL_RATING WHERE APPR_ID = 4 ORDER BY APPR_SCORE_ID ";
			
			/* X Axis Data */
			//case 2 : return " SELECT COUNT(APPR_FINAL_SCORE_VALUE), APPR_FINAL_SCORE_VALUE FROM PAS_APPR_SCORE WHERE APPR_ID = 4 GROUP BY APPR_FINAL_SCORE_VALUE ";
			
			case 2 : return 
			
						" SELECT " +
						" 	 APPR_SCORE_VALUE, " +
						"	 COUNT(APPR_FINAL_SCORE_VALUE), " +
						"	 APPR_SCORE_FROM," +
						"	 APPR_SCORE_TO" +
						" FROM " +
						"	 PAS_APPR_SCORE RIGHT JOIN PAS_APPR_OVERALL_RATING " +
						"	 ON(PAS_APPR_OVERALL_RATING.APPR_SCORE_VALUE = PAS_APPR_SCORE.APPR_FINAL_SCORE_VALUE " +
						"	 AND PAS_APPR_OVERALL_RATING.APPR_ID = PAS_APPR_SCORE.APPR_ID) " +
						" WHERE " +
						"	 PAS_APPR_OVERALL_RATING.APPR_ID = ? AND APPR_SCORE_FINALIZE='Y' " +
						" GROUP BY " +
						"	 APPR_SCORE_VALUE,APPR_SCORE_ID,APPR_SCORE_FROM,APPR_SCORE_TO " +
						" ORDER BY " +
						" 	 APPR_SCORE_ID ";
			
			case 3 : return
			
						" SELECT " +
						"	 APPR_FINAL_SCORE, " +
						"	 COUNT(APPR_FINAL_SCORE) " +
						" FROM " +
						" 	 PAS_APPR_SCORE RIGHT JOIN PAS_APPR_OVERALL_RATING " +
						" 	 ON(PAS_APPR_OVERALL_RATING.APPR_SCORE_VALUE = PAS_APPR_SCORE.APPR_FINAL_SCORE_VALUE " +
						" 	 AND PAS_APPR_OVERALL_RATING.APPR_ID = PAS_APPR_SCORE.APPR_ID) " +
						" WHERE " +
						" 	 PAS_APPR_OVERALL_RATING.APPR_ID = ? " +
						" GROUP BY " +
						"  	 APPR_FINAL_SCORE ";
			
			default : return " Framework could not the query number specified";
			
		}
	
	}
}

