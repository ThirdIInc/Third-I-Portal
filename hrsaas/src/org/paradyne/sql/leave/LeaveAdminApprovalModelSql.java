package org.paradyne.sql.leave;

import org.paradyne.lib.SqlBase;

public class LeaveAdminApprovalModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {

		case 1:
			return "   SELECT LEAVE_APPL_CODE,HRMS_LEAVE_HDR.EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(LEAVE_APPL_DATE,'DD-MM-YYYY'),"
					+ "	 LEAVE_STATUS,LEAVE_LEVEL,HRMS_EMP_OFFC.EMP_TOKEN "
					+ "	 FROM HRMS_LEAVE_HDR "
					+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_HDR.EMP_ID)"
					+ "	 WHERE  LEAVE_STATUS=?  AND (APPROVED_BY=? OR ALTER_APPROVER=? ) ORDER BY LEAVE_APPL_CODE DESC ";

		case 2:
			return "	INSERT INTO HRMS_LEAVE_PATH(LEAVE_PATH_ID,LEAVE_APPL_CODE,LEAVE_ASSESED_BY,LEAVE_STATUS,LEAVE_ASSESS_DATE,LEAVE_COMMENTS) "
					+ " VALUES((SELECT NVL(MAX(LEAVE_PATH_ID),0)+1 FROM HRMS_LEAVE_PATH),?,?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?) ";

		case 3:
			return "UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS=? WHERE LEAVE_APPL_CODE=?";

		case 4:
			return "UPDATE HRMS_LEAVE_HDR  SET LEAVE_LEVEL=? ,APPROVED_BY=? ,ALTER_APPROVER=? WHERE LEAVE_APPL_CODE=? ";

		case 5:
			return " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS=?,LEAVE_APPROVEDBY_ADMIN =? WHERE LEAVE_APPL_CODE=?";

		case 6:
			return " UPDATE HRMS_LEAVE_DTL  SET LEAVE_DTL_STATUS=? WHERE LEAVE_APPLICATION_CODE=? ";

		case 7:
			return "UPDATE HRMS_LEAVE_DTL  SET LEAVE_APPROVED_BY=?  WHERE LEAVE_APPLICATION_CODE=? ";

		case 8:
			return " UPDATE HRMS_LEAVE_DTLHISTORY  SET LEAVE_DTL_STATUS=? WHERE LEAVE_APPLICATION_CODE=? ";
		case 25:
			return "UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS =?,LEAVE_APPROVEDBY_ADMIN =? WHERE LEAVE_APPL_CODE =? ";
		case 26:
			return "UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = ? WHERE LEAVE_APPLICATION_CODE=?";
		case 27:
			return "UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS = ? WHERE LEAVE_APPLICATION_CODE=?";

		default:
			return "QUERY NOT FOUND";

		}
	}
}