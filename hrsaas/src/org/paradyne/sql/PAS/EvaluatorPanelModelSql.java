package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class EvaluatorPanelModelSql extends SqlBase {

	public String getQuery(int i) {

		switch (i) {

		/*
		 * case 1 : return " SELECT DISTINCT APPR_ID,APPR_EMP_ID, " +"
		 * (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||'
		 * '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' ||
		 * HRMS_EMP_OFFC.EMP_LNAME), " +" NVL(HRMS_DEPT.DEPT_NAME,'
		 * '),NVL(HRMS_RANK.RANK_NAME,'
		 * '),APPR_PHASE_ID,NVL(PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,'') " +"
		 * FROM PAS_APPR_COMMENTS " +" LEFT JOIN HRMS_EMP_OFFC ON
		 * (PAS_APPR_COMMENTS.APPR_EMP_ID = HRMS_EMP_OFFC.EMP_ID) " +" LEFT JOIN
		 * HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE) " +"
		 * LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) " +"
		 * LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) " +"
		 * LEFT JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_COMMENTS.APPR_PHASE_ID =
		 * PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) ";
		 */

		case 1:
			return " SELECT DISTINCT APPR_ID,HRMS_EMP_OFFC.EMP_ID, "
					+ " (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), "
					+ " NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  '),APPR_PHASE_ID,NVL(PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,''),APPR_CAL_CODE "
					+ " FROM PAS_APPR_TRACKING "
					+ " INNER JOIN PAS_APPR_CALENDAR ON (PAS_APPR_CALENDAR.APPR_ID = PAS_APPR_TRACKING.APPR_ID )"
					+ " LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_TRACKING.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE) "
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
					+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
					+ " LEFT JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_TRACKING.NEXT_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) "
					+ " WHERE NEXT_APPRAISER = ?  "
					+ "AND PHASE_FORWARD='Y' "
					+ "AND APPRAISAL_STATUS = 'N' "
					+
					// " AND NEXT_PHASE_FORWARD = 'N' "//for Manager and self
					// give priority 1 , 1
					" AND TO_DATE(SYSDATE,'DD-MM-YYYY') <= TO_DATE(APPR_CAL_VALID_DATE,'DD-MM-YYYY') ";

		case 2:
			return " SELECT DISTINCT APPR_ID,HRMS_EMP_OFFC.EMP_ID, "
					+ " (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), "
					+ " TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_PHASE_ID,NVL(PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,''),APPR_CAL_CODE "
					+ " FROM PAS_APPR_TRACKING "
					+ " INNER JOIN PAS_APPR_CALENDAR ON (PAS_APPR_CALENDAR.APPR_ID = PAS_APPR_TRACKING.APPR_ID ) "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_TRACKING.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE) "
					// +" LEFT JOIN HRMS_DEPT
					// ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
					// +" LEFT JOIN HRMS_RANK
					// ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
					+ " LEFT JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_TRACKING.NEXT_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) "
					+ " WHERE NEXT_APPRAISER = ?  AND NEXT_PHASE_FORWARD = 'Y' "
					+ " ORDER BY APPR_ID DESC ";

		case 3:
			return " SELECT DISTINCT PAS_APPR_EMP_GRP_HDR.APPR_TEMPLATE_ID,APPR_INSTRUCTION_FLAG FROM PAS_APPR_EMP_GRP_DTL "
					+ " LEFT JOIN PAS_APPR_EMP_GRP_HDR ON (PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID = PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID) "
					+ " LEFT JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID = PAS_APPR_QUES_MAPPING.APPR_EMP_GRP_ID) "
					+ " LEFT JOIN PAS_APPR_TEMPLATE ON (PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID = PAS_APPR_TEMPLATE.APPR_TEMPLATE_ID) "
					+ " WHERE APPR_EMP_GRP_EMPID = ? AND PAS_APPR_EMP_GRP_HDR.APPR_ID = ?  ";

		case 4:
			return " SELECT PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID,APPR_PHASE_ID,APPR_ID,APPR_APPRAISER_CODE  FROM PAS_APPR_APPRAISER "
					+ " WHERE APPR_APPRAISER_CODE = ? ";

		case 5:
			return " SELECT DISTINCT PAS_APPR_CALENDAR.APPR_ID,HRMS_EMP_OFFC.EMP_ID, "
					+ " (HRMS_TITLE .TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), "
					+ " NVL(HRMS_DEPT.DEPT_NAME,' '),NVL(HRMS_RANK.RANK_NAME,'  '),APPR_CAL_CODE "
					+ " FROM PAS_APPR_APPRAISER_GRP_DTL "
					+ " inner join PAS_APPR_APPRAISER_GRP_HDR on PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID "
					+ " INNER JOIN PAS_APPR_CALENDAR ON (PAS_APPR_CALENDAR.APPR_ID = PAS_APPR_APPRAISER_GRP_HDR.APPR_ID ) "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_TITLE  ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE .TITLE_CODE) "
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
					+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
					+ " WHERE PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID in (?) "
					+ " AND  PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID NOT IN ( "
					+ " SELECT EMP_ID FROM PAS_APPR_TRACKING WHERE  "
					+ " NEXT_PHASE_ID = ? and APPR_ID = ? and NEXT_APPRAISER = ? )   "
					+ " AND TO_DATE(SYSDATE,'DD-MM-YYYY') <= TO_DATE(APPR_CAL_VALID_DATE,'DD-MM-YYYY') ";

		default:
			return "";
		}
	}

}
