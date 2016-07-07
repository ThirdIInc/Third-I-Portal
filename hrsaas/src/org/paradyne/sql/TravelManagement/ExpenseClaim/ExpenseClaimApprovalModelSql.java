/**
 * 
 */
package org.paradyne.sql.TravelManagement.ExpenseClaim;

import org.paradyne.lib.SqlBase;

/**
 * @author AA0554
 *
 */
public class ExpenseClaimApprovalModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id) {
		case 1: return "SELECT EXP_APP_ID, EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
						+" HRMS_EMP_OFFC.EMP_LNAME, EXP_APP_LEVEL, to_char(EXP_APP_APPDATE,'DD-MM-YYYY'),EXP_APP_EMPID,EXP_TRAVEL_REQUEST"
						+" FROM HRMS_TMS_EXP_APP "
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_EXP_APP.EXP_APP_EMPID)"
						+" WHERE EXP_APP_STATUS=? AND (EXP_APP_APPROVER =? OR EXP_APP_ALTER_APPROVER = ?)";
		
		case 2 : return "UPDATE HRMS_TMS_EXP_APP SET EXP_APP_LEVEL = ?, EXP_APP_APPROVER = ? ,EXP_APP_ALTER_APPROVER=? WHERE EXP_APP_ID = ? ";	
		
		case 3 : return "UPDATE HRMS_TMS_EXP_APP SET EXP_APP_STATUS = ? WHERE EXP_APP_ID = ?";
		
		case 4 : return "INSERT INTO HRMS_TMS_EXP_APP_PATH (EXP_APP_PATH_ID, EXP_APP_PATH_APPROVER_CODE, EXP_APP_PATH_APPROVER_DATE, EXP_APP_PATH_APPROVER_REMARK, EXP_APP_PATH_STATUS) " 
						+" VALUES(?, ?, TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'), ?, ?)";


		default : return "Framework could not the query number specified";
			
		}
	}
}
