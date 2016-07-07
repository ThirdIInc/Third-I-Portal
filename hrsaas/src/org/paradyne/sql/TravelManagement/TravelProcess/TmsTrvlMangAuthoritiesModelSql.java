/**
 * 
 */
package org.paradyne.sql.TravelManagement.TravelProcess;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0651
 *
 */
public class TmsTrvlMangAuthoritiesModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO TMS_MANG_AUTH_HDR(AUTH_ID,AUTH_ALL_BRNCH,AUTH_BRNCH_ID,AUTH_MAIN_SCHL_ID"
					+ " ,AUTH_ALT_MAIN_SCHL_ID,AUTH_DESC,AUTH_STATUS,AUTH_SCH_APPROVAL,AUTH_ACCOUNTENT"
					// UPDATED BY REEBA
					+ " ,AUTH_ALT_ACCNT_ID,AUTH_POLICY_VIOLN_ESCLN_ID,AUTH_ACCOUNTANT_EMAIL_ID,CLM_ACK_WORKFLOW_ENABLE,CLM_ADMIN_WORKFLOW_ENABLE 	) "
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		case 2:
			return " INSERT INTO TMS_MNG_AUTH_DTL(AUTH_ID,AUTH_DTL_ID,AUTH_DTL_SUB_SCH_ID,AUTH_DTL_TRAVELFLAG"
					+ " ,AUTH_DTL_CONVFLAG,AUTH_DTL_LODGEFLAG,AUTH_DTL_TYPE) "
					+ " VALUES(?,?,?,?,?,?,?)";

		case 3:
			return "DELETE FROM TMS_MNG_AUTH_DTL WHERE AUTH_ID=?";

		case 4:
			return "DELETE FROM TMS_MANG_AUTH_HDR WHERE AUTH_ID=?";

		case 5:
			return "UPDATE TMS_MANG_AUTH_HDR SET AUTH_ALL_BRNCH=?,AUTH_BRNCH_ID=?,AUTH_MAIN_SCHL_ID=?,"
					+ " AUTH_ALT_MAIN_SCHL_ID=?,AUTH_DESC=?,AUTH_STATUS=?,AUTH_SCH_APPROVAL=?,AUTH_ACCOUNTENT=?"
					// UPDATED BY REEBA
					+ " ,AUTH_ALT_ACCNT_ID=?,AUTH_POLICY_VIOLN_ESCLN_ID=?, AUTH_ACCOUNTANT_EMAIL_ID=?,CLM_ACK_WORKFLOW_ENABLE=?,CLM_ADMIN_WORKFLOW_ENABLE=?"
					+ " WHERE AUTH_ID=?";
			
			
		case 6:
			return " INSERT INTO TMS_MNG_AUTH_DTL(AUTH_ID,AUTH_DTL_ID,AUTH_DTL_SUB_SCH_ID,AUTH_DTL_TRAVELFLAG"
					+ " ,AUTH_DTL_CONVFLAG,AUTH_DTL_LODGEFLAG,AUTH_DTL_ACKNOLEDGEFLAG, AUTH_DTL_ADVANCEFLAG, AUTH_DTL_CLAIMFLAG, AUTH_DTL_TYPE) "
					+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
	

		default:
			return "Framework could not EXECUTE the query number specified";
		}

	}

}
