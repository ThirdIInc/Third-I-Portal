/**
 * 
 */
package org.paradyne.sql.attendance;

import org.paradyne.lib.SqlBase;

/**
 * @author ritac
 *
 */
public class OutdoorApprovalModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id)
		{
			case 1 : return " SELECT TRN_SUB, TO_CHAR(TRN_REQ_DATE,'DD-MM-YYYY'), REQ_BY_CODE, HRMS_TITLE.TITLE_NAME||' ' ||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME, TRN_STATUS,TRN_REQ_CODE, TRN_REQ_LEVEL "
							+" FROM HRMS_TRN_REQ   "
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRN_REQ.REQ_BY_CODE) "
							+" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE)"
							+" WHERE TRN_STATUS = ? AND TRN_REQ_APPROVER = ?"+
							" ORDER BY HRMS_TRN_REQ.TRN_REQ_CODE";
			
			case 2 : return " INSERT INTO HRMS_OUTDOOR_PATH (OUTDOOR_PATH_OUTCODE,OUTDOOR_PATH_APPRCODE,OUTDOOR_PATH_DATE,OUTDOOR_PATH_STATUS,OUTDOOR_PATH_PURPOSE,OUTDOOR_PATH_LOC)"
			                   +" VALUES(?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?,?)";
			
			case 3 : return " UPDATE HRMS_OUTDOORVISIT SET OUTDOORVISIT_STATUS = ? WHERE OUTDOORVISIT_CODE = ?";
			
			case 4 : return " UPDATE HRMS_OUTDOORVISIT SET  OUTDOOR_APP_LEVEL=?, OUTDOORVISIT_APPR = ?,OUTDOOR_ALTER_APPROVER=?, OUTDOORVISIT_STATUS=? WHERE OUTDOORVISIT_CODE = ? ";
			
			case 5 : return " SELECT TRN_SUB, TO_CHAR(TRN_REQ_DATE,'DD-MM-YYYY'), "
							+" REQ_BY_CODE, HRMS_TITLE.TITLE_NAME||' ' ||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME, TRN_STATUS,TRN_REQ_CODE, "
							+" TRN_REQ_LEVEL, TRN_REQ_APPROVER, EMP_TOKEN "
							+" FROM HRMS_TRN_REQ   "
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRN_REQ.REQ_BY_CODE) "
							+" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
							+" WHERE TRN_REQ_CODE = ?";
			
			default : return "Framework could not EXECUTE the query number specified";		
		}
	}
}
