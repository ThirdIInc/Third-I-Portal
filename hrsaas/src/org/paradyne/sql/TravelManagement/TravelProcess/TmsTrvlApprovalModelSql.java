package org.paradyne.sql.TravelManagement.TravelProcess;

import org.paradyne.lib.SqlBase;

/**
 * @author krishna date: 09-JULY-2009
 * 
 */
public class TmsTrvlApprovalModelSql extends SqlBase {
	public String getQuery(int id) {

		switch (id) {

		case 1:
			return "UPDATE TMS_APP_GUEST_DTL  SET GUEST_TRAVEL_APPLSTATUS=?  WHERE APPL_ID =? AND  APPL_CODE=?";

		case 2:
			return "UPDATE TMS_APP_EMPDTL  SET APPL_EMP_TRAVEL_APPLSTATUS=?  WHERE APPL_ID =? AND  APPL_CODE=?";

		case 3:
			return "UPDATE TMS_APP_APPROVAL_DTL  SET APPR_DTL_COMMENTS=?  WHERE APPL_ID =? AND  APPL_CODE=? AND APPR_APPROVER_ID=?";

		case 4:
			return " INSERT INTO TMS_APPROVAL_KEEPINF_DTL (APPL_CODE,KEEPINF_EMPID)VALUES(?,?)";

		case 5:
			return " DELETE FROM TMS_APPROVAL_KEEPINF_DTL WHERE APPL_CODE=? AND KEEPINF_EMPID=?";

		case 6:
			return " INSERT INTO TMS_SCH_DESK (DESK_ID,DESK_APPL_ID,DESK_APPL_CODE,DESK_STATUS,DESK_SCH_ECODE)  "
					+ "VALUES((SELECT NVL(MAX(DESK_ID),0)+1 FROM TMS_SCH_DESK ),?,?,?,?)";
		case 7:
			return "UPDATE TMS_MONITORING  SET MTR_TVL_AVA_STATUS=?,MTR_LOCAL_AVSTATUS=?,MTR_ACC_AVSTATUS=?  WHERE APPL_ID =? AND  APPL_CODE=? ";

		case 8:
			return "UPDATE TMS_APP_EMPDTL  SET APPL_EMP_TRAVEL_APPLSTATUS='H' WHERE APPL_ID=? AND APPL_CODE=? ";

		case 9:
			return "UPDATE TMS_APP_GUEST_DTL  SET GUEST_TRAVEL_APPLSTATUS='H' WHERE APPL_ID=? AND APPL_CODE=? ";

		case 10:
			return "UPDATE TMS_APP_EMPDTL  SET APPL_EMP_TRAVEL_APPLSTATUS=? WHERE APPL_ID=? AND APPL_CODE=? ";

		case 11:
			return "UPDATE TMS_APP_GUEST_DTL  SET GUEST_TRAVEL_APPLSTATUS=? WHERE APPL_ID=? AND APPL_CODE=? ";

		case 12:
			return " INSERT INTO TMS_COMMENTS_TRAIL (COMMENTS_ID,COMMENTS_APP_ID,COMMENTS_APP_CODE,COMMENTS_APP_STATE,COMMENTS_EMPID,COMMENTS_DESC,COMMENTS_DATE,COMMENTS_LEVEL) "
					+ "VALUES((SELECT NVL(MAX(COMMENTS_ID),0)+1 FROM TMS_COMMENTS_TRAIL ),?,?,?,?,?,SYSDATE,?)";

		case 13:
			return "	SELECT   TO_CHAR(COMMENTS_DATE,'DD-MM-YYYY/HH24:MI') as APP_DATE,DECODE(COMMENTS_APP_STATE,'APPL','Applicant','APPR','Approver',"
					+ " 'SCH' ,'Scheduler','SUB','Sub Scheduler') AS USER_TYPE,"
					+ " TRIM(HRMS_TITLE.TITLE_NAME||' '||TRIM(NVL(EMP_FNAME,''))||'  '|| TRIM(NVL(EMP_MNAME,' '))||'  '||TRIM(NVL(EMP_LNAME,''))) AS NAME,"
					+ " DECODE(trim(COMMENTS_LEVEL),'APRL','Submitted','A','Approved','R','Rejected','B','Returned','H','Held','U','UnHeld',"
					+ " 'CNCL','Cancellation','WDRL','With drawl',"
					+ " 'Y','Pending for scheduler','SO','Pending for Applicant','RE','Query pending for Scheduler',"
					+ " 'CO','Pending for Approver',"
					+ " 'CE','Pending for Scheduler','CN','Self Managed',NVL(COMMENTS_LEVEL,' '))AS STATUS,"
					+ " COMMENTS_DESC"
					+ " FROM TMS_COMMENTS_TRAIL"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_COMMENTS_TRAIL.COMMENTS_EMPID)"
					+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " WHERE COMMENTS_APP_ID=? AND  COMMENTS_APP_CODE=?"
					+ " UNION"
					+ " SELECT  TO_CHAR(COMMENTS_DATE,'DD-MM-YYYY/HH24:MI') AS APP_DATE,DECODE(COMMENTS_APP_STATE,'APPL','Applicant','APPR','Approver',"
					+ " 'SCH' ,'Scheduler','SUB','Sub Scheduler') AS USER_TYPE ,"
					+ " TRIM(HRMS_TITLE.TITLE_NAME||' '||TRIM(NVL(EMP_FNAME,''))||'  '|| TRIM(NVL(EMP_MNAME,' '))||'  '||TRIM(NVL(EMP_LNAME,''))) AS NAME,"
					+ " DECODE(trim(COMMENTS_LEVEL),'APRL','Submitted','A','Approved','R','Rejected','B','Returned','H','Held','U','UnHeld',"
					+ " 'CNCL','Cancellation','WDRL','With drawl',"
					+ " 'Y','Pending for scheduler','SO','Pending for Applicant','RE','Query pending for Scheduler',"
					+ " 'CO','Pending for Approver','CE','Pending for Scheduler',"
					+ " 'CN','Self Managed',NVL(COMMENTS_LEVEL,' '))AS STATUS,"
					+ " COMMENTS_DESC"
					+ " FROM TMS_COMMENTS_TRAIL"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_COMMENTS_TRAIL.COMMENTS_EMPID)"
					+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " WHERE COMMENTS_APP_ID=? AND  COMMENTS_APP_CODE=?"
					+ " ORDER BY 1";

		case 14:
			return " SELECT  COMMENTS_APP_STATE,COMMENTS_LEVEL,CASE   WHEN trim(COMMENTS_LEVEL)='APPL' THEN 'Applicant'"
					+ "WHEN trim(COMMENTS_LEVEL)='APPR' THEN 'Approver' "
					+ " WHEN trim(COMMENTS_LEVEL)='SUB' THEN 'Sub Scheduler'"
					+ " WHEN trim(COMMENTS_LEVEL)='SCH' THEN 'Scheduler' else ''"
					+ " END CASE,"
					+ " TRIM(HRMS_TITLE.TITLE_NAME||' '||TRIM(NVL(EMP_FNAME,''))||'  '|| TRIM(NVL(EMP_MNAME,' '))||'  '||TRIM(NVL(EMP_LNAME,' '))) AS NAME, COMMENTS_DESC,"
					+ " TO_CHAR(COMMENTS_DATE,'DD-MM-YYYY')  FROM TMS_COMMENTS_TRAIL"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_COMMENTS_TRAIL.COMMENTS_EMPID)"
					+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " WHERE COMMENTS_APP_ID=? AND  COMMENTS_APP_CODE=?"
					+ " ORDER BY COMMENTS_DATE";

		case 15:
			return "UPDATE TMS_APP_APPROVAL_DTL  SET APPR_STATUS=?, APPR_DTL_COMMENTS=? ,APPR_DTL_APPDATE=SYSDATE WHERE APPL_ID =? AND"
					+ " APPL_CODE= ? AND APPR_LEVEL>=?  AND APPR_APPROVER_ID=?";

		case 16:
			return "UPDATE TMS_APP_EMPDTL  SET APPL_TVL_CANCEL_STATUS=?, APPL_ACC_CANCEL_STATUS=? ,APPL_LOC_CANCEL_STATUS=? WHERE APPL_ID =? AND"
					+ " APPL_CODE= ?";

		case 17:
			return "UPDATE TMS_APP_GUEST_DTL  SET APPL_TVL_CANCEL_STATUS=?, APPL_ACC_CANCEL_STATUS=? ,APPL_LOC_CANCEL_STATUS=? WHERE APPL_ID =? AND"
					+ " APPL_CODE= ?";

		case 18:
			return "UPDATE TMS_SCH_DESK  SET DESK_STATUS=? WHERE DESK_APPL_ID =? AND"
					+ " DESK_APPL_CODE= ?";

		default:
			return " Framework could not find query number sepcified";

		}

	}

}
