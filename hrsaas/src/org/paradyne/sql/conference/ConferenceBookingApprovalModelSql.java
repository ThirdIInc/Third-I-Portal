package org.paradyne.sql.conference;

import org.paradyne.lib.SqlBase;

public class ConferenceBookingApprovalModelSql extends SqlBase {

	public String getQuery(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case 1 : return "UPDATE HRMS_CONF_HDR SET CONF_LEVEL = ?, CONF_APPROVER = ? WHERE CONF_CODE = ? ";
		
		case 2 : return "UPDATE HRMS_CONF_HDR SET CONF_STATUS = ? WHERE CONF_CODE = ?";
		
		case 3 : return "INSERT INTO HRMS_CONF_PATH (CONF_PATH_ID, CONF_CODE, CONF_APPROVER, CONF_APPROVED_DATE, CONF_COMMENTS, CONF_STATUS, "
						+"CONF_APPLIED_BY) VALUES((SELECT NVL(MAX(CONF_PATH_ID),0)+1 FROM HRMS_CONF_PATH WHERE CONF_CODE = ?), ?, ?, TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'), ?, ?, ?)";

		case 4 : return " SELECT EMP_TOKEN, EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME, "
						+ " TO_CHAR(CONF_DATE,'DD-MM-YYYY'), CONF_BOOKEDBY, CONF_STATUS, CONF_CODE, CONF_APPROVER, CONF_LEVEL, "
						+ " CONF_FROM_TIME, CONF_TO_TIME,CONF_ROOMNO FROM HRMS_CONF_HDR   "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_HDR.CONF_BOOKEDBY) " 
						+ " WHERE CONF_STATUS = '?' AND (CONF_APPROVER = ? OR CONF_ALT_APPROVER= ?)"
						+ " ORDER BY CONF_DATE DESC";
		
		default:return"";
			
		}

	}
}
