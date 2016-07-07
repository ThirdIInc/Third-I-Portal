/**
 * 
 */
package org.paradyne.sql.Asset;

import org.paradyne.lib.SqlBase;

/**
 * @author mangeshj
 *
 */
public class PurchaseApprovalModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id) {
		case 1: return "SELECT PURCHASE_CODE,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
					 +" HRMS_EMP_OFFC.EMP_LNAME,PURCHASE_EMP_CODE,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),PURCHASE_LEVEL,PURCHASE_STATUS,PURCHASE_ORDER_NO "  
					 +" FROM HRMS_ASSET_PURCHASE_HDR "
					 +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE) "
					 +" WHERE PURCHASE_STATUS=? AND (PURCHASE_APPROVER_ID=? OR PURCHASE_ALTER_APPROVER=?) ";
		
		case 2 : return "UPDATE HRMS_ASSET_PURCHASE_HDR SET PURCHASE_LEVEL = ?, PURCHASE_APPROVER_ID = ?,PURCHASE_ALTER_APPROVER=? WHERE PURCHASE_CODE = ? ";	
		
		case 3 : return "UPDATE HRMS_ASSET_PURCHASE_HDR SET PURCHASE_STATUS = ? WHERE PURCHASE_CODE = ?";
		
		case 4 : return "INSERT INTO HRMS_ASSET_PURCHASE_PATH (PURCHASE_CODE, PURCHASE_APPROVER, PURCHASE_APPR_DATE, PURCHASE_APPL_COMMENTS, PURCHASE_PATH_STATUS,PURCHASE_PATH_ID) " 
						+" VALUES(?, ?, TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'), ?, ?,(SELECT (NVL(MAX (PURCHASE_PATH_ID),0)+1) FROM HRMS_ASSET_PURCHASE_PATH WHERE PURCHASE_CODE=?  ))";

		

		default : return "Framework could not the query number specified";
			
		}
}
}
