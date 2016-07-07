package org.paradyne.sql.voucher;

import org.paradyne.lib.SqlBase;

public class VoucherApprovalAcModelSql extends SqlBase {

	
	public String getQuery(int id) {
		switch(id) {
		   
		case 1: return "SELECT VOUCHER_APPL_CODE,EMP_ID, HRMS_EMP_OFFC.EMP_TITLE_NOTUSED||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(APP_DATE, 'DD-MM-YYYY'),NVL(VCH_TOTALAMT,0) , "
					   +"NVL(ACCT_STATUS, 'P') "
					   +"FROM HRMS_VOUCHER_APPL "
					   +"INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_VOUCHER_APPL.EMP_CODE) "
					   +"WHERE ADMIN_STATUS='A' AND ACCT_STATUS = ?";

     case 2 : return "UPDATE HRMS_VOUCHER_APPL SET ACCT_STATUS = ? WHERE VOUCHER_APPL_CODE = ?";

			
		default : return "Framework could not EXECUTE the query number specified";			
		}
	}
}
