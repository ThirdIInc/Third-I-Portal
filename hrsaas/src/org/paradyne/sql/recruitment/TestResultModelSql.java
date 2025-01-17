/**
 * 
 */
package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0540
 *
 */
public class TestResultModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id) {
		
			case 1 : return "SELECT HRMS_REC_SCHTEST_DTL.TEST_DTL_CODE, HRMS_REC_SCHTEST_DTL.TEST_CODE, HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE, "
							+"CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME, CAND_MOB_PHONE, CAND_EMAIL_ID, "
							+"HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE, TO_CHAR(HRMS_REC_SCHTEST_DTL.TEST_DATE, 'DD-MM-YYYY' ), "
							+"HRMS_REC_SCHTEST_DTL.TEST_TIME, TEST_TOTAL_MARKS, TEST_OBT_MARKS, "
							+"TEST_RESULT, HRMS_REC_SCHTEST.TEST_TYPE, HRMS_REC_TESTRESULT.TEST_RESULT_CODE,TEST_COMMENTS,TEST_ROUND_TYPE,TEST_STATUS  "
							+" ,HRMS_REC_TESTRESULT.TEST_OBJECTIVE_MARKS, HRMS_REC_TESTRESULT.TEST_SUBJECTIVE_MARKS, " 
							+" HRMS_REC_TEST_TEMPLATE.TEMPLATE_TOTAL_MARKS, HRMS_REC_TEST_TEMPLATE.TEMPLATE_TEST_TYPE, HRMS_REC_SCHTEST.TEST_TEMPLATE "
							+"FROM HRMS_REC_SCHTEST_DTL " 
							+"INNER JOIN HRMS_REC_SCHTEST ON (HRMS_REC_SCHTEST.TEST_CODE = HRMS_REC_SCHTEST_DTL.TEST_CODE) "
							+" INNER JOIN HRMS_REC_TEST_TEMPLATE ON (HRMS_REC_SCHTEST.TEST_TEMPLATE = HRMS_REC_TEST_TEMPLATE.TEMPLATE_CODE)"
							+"INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE) "
							+"LEFT JOIN HRMS_REC_TESTRESULT ON (HRMS_REC_TESTRESULT.TEST_DTL_CODE = HRMS_REC_SCHTEST_DTL.TEST_DTL_CODE) ";
			
			case 2 : return "INSERT INTO HRMS_REC_TESTRESULT (TEST_RESULT_CODE, TEST_REQS_CODE, TEST_CAND_CODE, "
							+"TEST_TOTAL_MARKS, TEST_OBT_MARKS, TEST_RESULT, TEST_TYPE,TEST_COMMENTS,TEST_DTL_CODE) VALUES "
							+"((SELECT NVL(MAX(TEST_RESULT_CODE),0)+1 FROM HRMS_REC_TESTRESULT), ?, ?, ?, ?, ?, ?,?,?)";

			default : return "";
		}
	}
}
