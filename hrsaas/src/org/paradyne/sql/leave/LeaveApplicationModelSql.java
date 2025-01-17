package org.paradyne.sql.leave;

import org.paradyne.lib.SqlBase;

public class LeaveApplicationModelSql extends SqlBase {

	public LeaveApplicationModelSql() {
	}

	public String getQuery(int queryID) {

		switch (queryID) {

		case 1:
			return " INSERT INTO HRMS_LEAVE_HDR(LEAVE_APPL_CODE,EMP_ID,LEAVE_APPL_DATE,LEAVE_COMMENTS, LEAVE_REASON,LEAVE_STATUS,LEAVE_FROM_DATE,LEAVE_TO_DATE,APPROVED_BY,ALTER_APPROVER,LEAVE_REASON_CODE)  "
					+ " VALUES(?,?,SYSDATE,?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?)";

		case 2:
			return " INSERT INTO HRMS_LEAVE_DTL (LEAVE_APPLICATION_CODE,EMP_ID,LEAVE_CODE,LEAVE_DAYS,LEAVE_FROM_DATE,LEAVE_TO_DATE) "
					+ "	VALUES ((SELECT MAX(LEAVE_APPL_CODE) FROM HRMS_LEAVE_HDR),?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'))";

		case 3:
			return " SELECT LEAVE_COMMENTS,LEAVE_REASON, "
					+ " DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'D','Draft','P','Pending','B','Sent Back','N','Cancelled','R','Rejected','A','Approved','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected'),TO_CHAR(LEAVE_APPL_DATE,'DD-MM-YYYY'),NVL(REASON_NAME,''),NVL(LEAVE_REASON_CODE,0)  FROM HRMS_LEAVE_HDR "
					+ "  LEFT JOIN  HRMS_LEAVE_REASON ON(HRMS_LEAVE_REASON.REASON_CODE =HRMS_LEAVE_HDR.LEAVE_REASON_CODE) "
					+ " WHERE LEAVE_APPL_CODE = ?";

		case 4:
			return " SELECT LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),LEAVE_DAYS,LEAVE_DTL_ID FROM HRMS_LEAVE_DTL "
					+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID)"
					+ " WHERE EMP_ID=? AND LEAVE_APPLICATION_CODE= ? ";

		case 5:
			return " UPDATE HRMS_LEAVE_HDR SET EMP_ID=?,LEAVE_COMMENTS=?,LEAVE_REASON=?,LEAVE_FROM_DATE=TO_DATE(?,'DD-MM-YYYY'),LEAVE_TO_DATE=TO_DATE(?,'DD-MM-YYYY'),APPROVED_BY=? ,ALTER_APPROVER=?,LEAVE_STATUS=?,LEAVE_APPL_DATE=SYSDATE,LEAVE_REASON_CODE=? "
					+ " WHERE LEAVE_APPL_CODE=? ";

		case 6:
			return " SELECT LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME, "
					+ " TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE, "
					+ " LEAVE_DAYS,LEAVE_DTL_ID"
					+ " FROM HRMS_LEAVE_DTL  "
					+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID) "
					+ " INNER JOIN HRMS_LEAVE_BALANCE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_BALANCE.LEAVE_CODE) "
					+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE "
					+ " AND HRMS_LEAVE_POLICY_DTL.EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID =?))"
					+ " WHERE  LEAVE_DTL_ID =? AND EMP_ID =? ";

		case 7:
			return " DELETE FROM HRMS_LEAVE_HDR WHERE LEAVE_APPL_CODE = ? ";

		case 8:
			return "  SELECT  HRMS_LEAVE_DTL.LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY : HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY')),	"
					+ " DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY : HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')),DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_DAYS_HRS,'HH24:MI')||'  (HH24:MI)',LEAVE_DAYS),DECODE(LEAVE_HRS_FLAG,'Y',NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,0)||' Days '||TO_CHAR(HRMS_LEAVE_BALANCE.LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,0)),"
					+ " NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,0),NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0),LEAVE_HALFDAY_SESSION_FRMDATE,LEAVE_HALFDAY_SESSION_TODATE,LEAVE_UPLOAD_PROOF_NAME,LEAVE_UPLOAD_PROOF_DOCS,NVL(LEAVE_PENALTY_DAYS,0),NVL( LEAVE_PENALTY_ADJUST_DAYS,0),NVL(LEAVE_PENALTY_UNADJUST_DAYS,0),TO_CHAR(LEAVE_MATERNITY_DATE,'DD-MM-YYYY'),LEAVE_HALF_PAY FROM HRMS_LEAVE_DTL "
					+ " INNER JOIN HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE) "
					+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID)	"
					+ " LEFT JOIN  HRMS_LEAVE_BALANCE ON (HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE.LEAVE_CODE "
					+ " AND HRMS_LEAVE_BALANCE.EMP_ID = ?)"
					+ " WHERE LEAVE_APPLICATION_CODE=?  AND HRMS_LEAVE_DTL.EMP_ID=? ";
		case 9:
			return " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ " HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID "
					+ " FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ " INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID=?"
					+ " ORDER BY HRMS_EMP_OFFC.EMP_ID ";

		case 10:
			return " SELECT NVL(LEAVE_STATUS,'P'),LEAVE_LEVEL FROM HRMS_LEAVE_HDR WHERE LEAVE_APPL_CODE=? ";

		case 11:
			return " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID,EMP_TYPE,HRMS_EMP_OFFC.EMP_GENDER "
					+ "	FROM HRMS_EMP_OFFC "
					+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID=?";

		case 12:
			return "DELETE FROM HRMS_LEAVE_DTL WHERE LEAVE_APPLICATION_CODE=?";

		case 13:
			return " SELECT LEAVE_ID,TO_CHAR(LEAVE_NAME) , TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY'),LEAVE_DAYS FROM HRMS_LEAVE  "
					+ " INNER JOIN HRMS_LEAVE_DTL ON(HRMS_LEAVE_DTL.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID ) "
					+ " WHERE HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE = ? ORDER BY LEAVE_ID ";

		case 14:
			return "SELECT MAX(LEAVE_APPL_CODE),MAX(LEAVE_APPL_CODE) FROM HRMS_LEAVE_HDR";

		case 15:
			return "INSERT INTO HRMS_LEAVE_DTL (LEAVE_APPLICATION_CODE,LEAVE_CODE,LEAVE_DAYS,LEAVE_FROM_DATE,LEAVE_TO_DATE,EMP_ID,LEAVE_DTL_STATUS,LEAVE_APPROVED_BY,LEAVE_HALFDAY_SESSION_FRMDATE,LEAVE_HALFDAY_SESSION_TODATE,LEAVE_UPLOAD_PROOF_NAME, LEAVE_UPLOAD_PROOF_DOCS,LEAVE_PENALTY_DAYS, LEAVE_PENALTY_ADJUST_DAYS, LEAVE_PENALTY_UNADJUST_DAYS,LEAVE_MATERNITY_DATE,LEAVE_HALF_PAY) "
					+ " VALUES (?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?)";

		case 16:
			return " UPDATE HRMS_LEAVE_BALANCE SET "
					+ " LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE - ? , LEAVE_ONHOLD =NVL(LEAVE_ONHOLD,0) + ?"
					+ " WHERE  LEAVE_CODE =? AND EMP_ID =? ";

		case 17:
			return " SELECT  LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),	"
					+ " TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),LEAVE_DAYS FROM HRMS_LEAVE_DTL 	"
					+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID)	"
					+ "  WHERE  HRMS_LEAVE_DTL.EMP_ID=? ORDER BY   TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY') DESC ";

		case 18:
			return "INSERT INTO HRMS_LEAVE_DTLHISTORY(LEAVE_APPLICATION_CODE,LEAVE_CODE,LEAVE_DAYS,LEAVE_FROM_DATE,LEAVE_TO_DATE,EMP_ID,LEAVE_DTL_STATUS,LEAVE_MONTH,LEAVE_YEAR) "
					+ " VALUES (?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?)";

		case 19:
			return "  SELECT  HRMS_EMP_OFFC.EMP_TOKEN,  "
			+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  "
			+ " TO_CHAR(HRMS_LEAVE_HDR.LEAVE_APPL_DATE,'DD-MM-YYYY'),HRMS_LEAVE_HDR.EMP_ID,HRMS_LEAVE_HDR.LEAVE_APPL_CODE,HRMS_LEAVE_HDR.LEAVE_STATUS "
			+ " FROM HRMS_LEAVE_HDR "
			+ "	INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID  = HRMS_LEAVE_HDR.EMP_ID  "
			+ "	WHERE HRMS_LEAVE_HDR.LEAVE_STATUS=? AND HRMS_LEAVE_HDR.EMP_ID=? "
			+ " ORDER BY LEAVE_APPL_CODE DESC ";
			
		case 20: return  " DELETE FROM HRMS_LEAVE_DTL WHERE LEAVE_APPLICATION_CODE=? ";
		
		case 21: return " DELETE FROM HRMS_LEAVE_DTLHISTORY WHERE LEAVE_APPLICATION_CODE=? ";

		case 22:
			return " INSERT INTO HRMS_LEAVE_HDR(LEAVE_REASON_CODE,EMP_ID,LEAVE_APPL_DATE,LEAVE_COMMENTS, LEAVE_REASON,LEAVE_STATUS,LEAVE_FROM_DATE,LEAVE_TO_DATE,APPROVED_BY,ALTER_APPROVER,LEAVE_HRS_FLAG,LEAVE_APPL_CODE)  "
					+ " VALUES(?,?,SYSDATE,?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,'Y',?)";
		case 23:
			return "INSERT INTO HRMS_LEAVE_DTL (LEAVE_APPLICATION_CODE,LEAVE_CODE,LEAVE_DAYS,LEAVE_FROM_DATE,LEAVE_TO_DATE,EMP_ID,LEAVE_DTL_STATUS,LEAVE_APPROVED_BY,LEAVE_HALFDAY_SESSION_FRMDATE,LEAVE_HALFDAY_SESSION_TODATE,LEAVE_UPLOAD_PROOF_NAME, LEAVE_UPLOAD_PROOF_DOCS,LEAVE_PENALTY_DAYS, LEAVE_PENALTY_ADJUST_DAYS, LEAVE_PENALTY_UNADJUST_DAYS,LEAVE_MATERNITY_DATE,LEAVE_DAYS_HRS) "
					+ " VALUES (?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),TO_DATE(?,'DD-MM-YYYY HH24:MI'),?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'HH24:MI'))";
		case 24:
			return "INSERT INTO HRMS_LEAVE_DTLHISTORY(LEAVE_APPLICATION_CODE,LEAVE_CODE,LEAVE_DAYS,LEAVE_FROM_DATE,LEAVE_TO_DATE,EMP_ID,LEAVE_DTL_STATUS,LEAVE_MONTH,LEAVE_YEAR,LEAVE_DAYS_HRS) "
					+ " VALUES (?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),TO_DATE(?,'DD-MM-YYYY HH24:MI'),?,?,?,?,TO_DATE(?,'HH24:MI'))";

		case 25:
			return "UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS =? WHERE LEAVE_APPL_CODE =? ";
		case 26:
			return "UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = ? WHERE LEAVE_APPLICATION_CODE=?";
		case 27:
			return "UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS = ? WHERE LEAVE_APPLICATION_CODE=?";

		case 28:
			return " UPDATE  HRMS_LEAVE_HDR SET LEAVE_REASON_CODE=?,EMP_ID=?,LEAVE_COMMENTS=?, LEAVE_REASON=?,LEAVE_STATUS=?,LEAVE_FROM_DATE=TO_DATE(?,'DD-MM-YYYY'),LEAVE_TO_DATE=TO_DATE(?,'DD-MM-YYYY'),APPROVED_BY=?,ALTER_APPROVER=?,LEAVE_HRS_FLAG='Y' WHERE LEAVE_APPL_CODE=?  ";
			
			
		default:
			return "QUERY NOT FOUND";

		}
	}
}