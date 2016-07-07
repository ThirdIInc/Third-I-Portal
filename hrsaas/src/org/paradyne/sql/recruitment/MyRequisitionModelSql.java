/**
 * 
 */
package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0540
 *
 */
public class MyRequisitionModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id) {
			/*case 1 : return "SELECT HRMS_REC_VACPUB_HDR.PUB_CODE, PUB_REQS_CODE, REQS_NAME, RANK_NAME, "
							+"B1.EMP_FNAME||' '||B1.EMP_MNAME||' '||B1.EMP_LNAME, A1.EMP_FNAME||' '||A1.EMP_MNAME"
							+"||' '||A1.EMP_LNAME, TO_CHAR(PUB_DATE, 'DD-MM-YYYY'), PUB_ASG_VAC, "
							+"TO_CHAR(VACAN_REQ_DATE, 'DD-MM-YYYY'), REQS_POSITION, VACAN_DTL_CODE "
							+"FROM HRMS_REC_VACPUB_RECDTL "
							+"INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
							+"INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE) "
							+"LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
							+"LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
							+"INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) " 
							+"INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY) " 
							+"WHERE PUB_REC_EMPID = ? AND PUB_VAC_STATUS = ?";*/
		
		/*case 1 : return "SELECT HRMS_REC_VACPUB_HDR.PUB_CODE, PUB_REQS_CODE, REQS_NAME, RANK_NAME,"
		+" B1.EMP_FNAME||' '||B1.EMP_MNAME||' '||B1.EMP_LNAME, A1.EMP_FNAME||' '||A1.EMP_MNAME"
		+" ||' '||A1.EMP_LNAME, TO_CHAR(PUB_DATE, 'DD-MM-YYYY'), PUB_ASG_VAC,"
		+" TO_CHAR(VACAN_REQ_DATE, 'DD-MM-YYYY'), REQS_POSITION, VACAN_DTL_CODE,DIV_NAME,CENTER_NAME,DEPT_NAME"
		+" FROM HRMS_REC_VACPUB_RECDTL"
		+" INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE)"
		+" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE)"
		+" LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE)"
		+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)"
		+" INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)" 
		+" INNER JOIN HRMS_DIVISION on (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
		+" LEFT JOIN HRMS_CENTER on (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
		+" LEFT JOIN HRMS_DEPT on (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
		+" INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY) "
		+"WHERE PUB_REC_EMPID = ? AND PUB_VAC_STATUS = ?";
			default : return "";*/
		case 1 : return "SELECT HRMS_REC_VACPUB_HDR.PUB_CODE, PUB_REQS_CODE, REQS_NAME, RANK_NAME,"
		+" B1.EMP_FNAME||' '||B1.EMP_MNAME||' '||B1.EMP_LNAME, A1.EMP_FNAME||' '||A1.EMP_MNAME"
		+" ||' '||A1.EMP_LNAME, TO_CHAR(PUB_DATE, 'DD-MM-YYYY'), PUB_ASG_VAC,"
		+" TO_CHAR(VACAN_REQ_DATE, 'DD-MM-YYYY'), REQS_POSITION, VACAN_DTL_CODE,DIV_NAME,CENTER_NAME,DEPT_NAME"
		+" FROM HRMS_REC_VACPUB_RECDTL"
		+" INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE)"
		+" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE)"
		+" LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE)"
		+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)"
		+" INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)" 
		+" INNER JOIN HRMS_DIVISION on (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
		+" LEFT JOIN HRMS_CENTER on (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
		+" LEFT JOIN HRMS_DEPT on (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
		+" INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY) "
		+"WHERE PUB_REC_EMPID = ? AND REQS_STATUS = ?";
			default : return "";
		}
	}
}
