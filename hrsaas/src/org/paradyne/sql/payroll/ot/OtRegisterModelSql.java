package org.paradyne.sql.payroll.ot;

import org.paradyne.lib.SqlBase;

/**Created on 1st Mar 2012.
 * @author aa1380
 *
 */
public class OtRegisterModelSql extends SqlBase {

    /**Method : getQuery.
     * Purpose : this method is used to execute requested query from given query ID
     * @return String
     * @param id : given Query ID
     */
    public String getQuery(final int id) {
		switch (id) {
		case 1:
			return "DELETE FROM HRMS_OT_REGISTER WHERE HRMS_OT_REGISTER.EMP_ID = ? AND TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'DD-MM-YYYY') = ?";
		case 2:
			return "INSERT INTO HRMS_OT_REGISTER(HRMS_OT_REGISTER.OT_ID, HRMS_OT_REGISTER.EMP_ID,HRMS_OT_REGISTER.OT_SHIFT_CODE, HRMS_OT_REGISTER.OT_DATE,  HRMS_OT_REGISTER.APPR_SINGLE_OT , HRMS_OT_REGISTER.APPR_DOUBLE_OT ) VALUES (?, ?, ?, TO_DATE(?,'DD-MM-YYYY'), TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'))";
		default:
			return "Framework could not EXECUTE the query number specified";
		}
	}
}
