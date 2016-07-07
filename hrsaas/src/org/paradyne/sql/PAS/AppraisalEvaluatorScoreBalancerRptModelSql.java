package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class AppraisalEvaluatorScoreBalancerRptModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {
		case 1:
			return

			" SELECT "
					+ "	EMP_TOKEN, "
					+ "	(HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME), "
					+ "	NVL(HRMS_DEPT.DEPT_NAME,'  '), "
					+ "	APPR_SCORE AS ACTUAL_SCORE, "
					+ "	CASE WHEN APPR_SCORE_ADJUST IS NULL THEN APPR_ADJUST_FACTOR || '0' ELSE APPR_ADJUST_FACTOR || '' || APPR_SCORE_ADJUST END AS ADJUSTED_SCORE, "
					+ "	APPR_FINAL_SCORE AS FINAL_SCORE, " 
					+ " PAS_APPR_SCORE.EMP_ID " 
					+ " FROM PAS_APPR_SCORE "
					+ "	LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_SCORE.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ "	LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
					+ " WHERE APPR_ID = ? AND " + "	PAS_APPR_SCORE.EMP_ID IN " + "	( "
					+ "		SELECT " + "			DISTINCT EMP_ID " + "		FROM "
					+ "			PAS_APPR_TRACKING " + "		WHERE "
					+ "			PHASE_FORWARD='Y' AND "
					+ "			APPRAISAL_STATUS = 'Y' AND "
					+ "			NEXT_PHASE_FORWARD = 'Y' AND "
					+ "			APPR_ID = ? AND " + " NEXT_APPRAISER = ? " + "	) ";

		case 2:
			return

			" SELECT " + "		APPR_PHASE_ID " + " FROM "
					+ " 	PAS_APPR_PHASE_CONFIG " + " WHERE "
					+ "		APPR_ID = ? AND " + "	APPR_PHASE_ORDER = " + " 	( "
					+ "		SELECT " + "			MAX(APPR_PHASE_ORDER) " + "		FROM "
					+ "			PAS_APPR_PHASE_CONFIG " + "		WHERE "
					+ "			APPR_ID = ? " + "	) ";
		
		case 3:
			return
			
					"SELECT APPR_APPRAISER_CODE FROM PAS_APPR_APPRAISER " + 
					" INNER JOIN PAS_APPR_APPRAISER_GRP_DTL ON (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID = " +  
					" PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) WHERE APPR_APPRAISEE_ID = ? AND APPR_ID = ? AND APPR_PHASE_ID =? " +
					" AND APPR_APPRAISER_LEVEL=(SELECT MAX(A.APPR_APPRAISER_LEVEL) FROM PAS_APPR_APPRAISER A INNER JOIN" +
					" PAS_APPR_APPRAISER_GRP_DTL B ON (A.APPR_APPRAISER_GRP_ID = B.APPR_APPRAISER_GRP_ID) WHERE B.APPR_APPRAISEE_ID = ? AND " +
					" A.APPR_ID = ? AND A.APPR_PHASE_ID =? ) ";

		
		default:
			return " Framework could not the query number specified";
		}
	}
}
