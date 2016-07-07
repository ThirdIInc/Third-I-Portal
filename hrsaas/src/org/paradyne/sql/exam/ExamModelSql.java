package org.paradyne.sql.exam;

import org.paradyne.lib.SqlBase;

public class ExamModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_REC_SUBJECT (SUBJECT_CODE,SUBJECT_NAME,SUBJECT_ABBR,SUBJECT_DESC,"
					+ "SUBJECT_STATUS, IS_SECTION)"
					+ " VALUES((SELECT NVL(MAX(SUBJECT_CODE),0)+1 FROM HRMS_REC_SUBJECT ),?,?,?,?,?)";

		case 2:
			return " UPDATE HRMS_REC_SUBJECT SET SUBJECT_NAME=?,SUBJECT_ABBR=?,SUBJECT_DESC=?," +
					"SUBJECT_STATUS=?, IS_SECTION=? WHERE SUBJECT_CODE=?";

		case 3:
			return " DELETE FROM HRMS_REC_SUBJECT WHERE SUBJECT_CODE=?";
			
		case 4:
			return " SELECT  SUBJECT_CODE,SUBJECT_NAME,SUBJECT_ABBR,SUBJECT_DESC FROM HRMS_REC_SUBJECT ORDER BY SUBJECT_CODE ";
		
		case 5:	return " INSERT INTO HRMS_REC_CATEGORY (CAT_CODE,CAT_SUB_CODE,CAT_NAME, CAT_STATUS, CAT_ORDER)"
			+ " VALUES((SELECT NVL(MAX(CAT_CODE),0)+1 FROM HRMS_REC_CATEGORY ),?,?,?,(SELECT NVL(MAX(CAT_ORDER),0)+1 FROM HRMS_REC_CATEGORY ))";
		
		
		case 6:return "UPDATE HRMS_REC_CATEGORY SET CAT_NAME=? WHERE CAT_CODE=? AND CAT_SUB_CODE=?";
		
		case 7:return "UPDATE HRMS_REC_CATEGORY SET CAT_NAME=?, CAT_STATUS=? WHERE CAT_CODE=? ";

		default:
			return "Query Not Found";
		}
	}

}
