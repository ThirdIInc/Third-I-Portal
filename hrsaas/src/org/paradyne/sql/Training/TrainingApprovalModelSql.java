/**
 * 
 */
package org.paradyne.sql.Training;

import org.paradyne.lib.SqlBase;

/**
 * @author shashikant
 *
 */
public class TrainingApprovalModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id)
		{
			case 1 : return "SELECT TRN_SUB, TO_CHAR(TRN_REQ_DATE,'DD-MM-YYYY'), REQ_BY_CODE, HRMS_TITLE.TITLE_NAME||' ' ||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME, TRN_STATUS,TRN_REQ_CODE, TRN_REQ_LEVEL "
							+"FROM HRMS_TRN_REQ   "
							+"INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRN_REQ.REQ_BY_CODE) "
							+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE)"
							+"WHERE TRN_STATUS = ? AND TRN_REQ_APPROVER = ?"+
							" ORDER BY HRMS_TRN_REQ.TRN_REQ_CODE";
			
			case 2 : return "INSERT INTO HRMS_TRN_PATH (TRAINING_CODE, APPROVER_CODE, APPROVED_DATE, REMARKS, STATUS, "
							+"REQUESTED_BY) VALUES(?, ?, TO_DATE(SYSDATE,'DD-MM-YYYY'), ?, ?, ?)";
			
			case 3 : return "UPDATE HRMS_TRN_REQ SET TRN_STATUS = ? WHERE TRN_REQ_CODE = ?";
			
			case 4 : return "UPDATE HRMS_TRN_REQ SET TRN_REQ_LEVEL = ?, TRN_REQ_APPROVER = ? WHERE TRN_REQ_CODE = ? ";
			
			case 5 : return "SELECT TRN_SUB, TO_CHAR(TRN_REQ_DATE,'DD-MM-YYYY'), "
							+"REQ_BY_CODE, HRMS_TITLE.TITLE_NAME||' ' ||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME, TRN_STATUS,TRN_REQ_CODE, "
							+"TRN_REQ_LEVEL, TRN_REQ_APPROVER, EMP_TOKEN "
							+"FROM HRMS_TRN_REQ   "
							+"INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRN_REQ.REQ_BY_CODE) "
							+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
							+"WHERE TRN_REQ_CODE = ?";
			
			default : return "Framework could not EXECUTE the query number specified";		
		}
	}
}
