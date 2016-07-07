/**
 * 
 */
package org.paradyne.sql.attendance;

import org.paradyne.lib.SqlBase;

/**
 * @author balajim
 *
 */
public class CompOffApprovalModelSql extends SqlBase {
	
	public String getQuery(int id) {
		 
		switch(id) {
		   case 1:  return " INSERT INTO HRMS_COMPOFF_PATH (COMPID,APPROVER_CODE,APPR_DATE,COMPOFF_REMARK,COMPOFF_STATUS) VALUES( ?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?) ";
		   
		   case 2: return "UPDATE HRMS_COMPOFF_HDR  SET COMP_LEVEL=? ,COMP_APPROVER =?,COMP_ALT_APPROVER=? WHERE COMP_ID =?";
		   
		   case 3: return "UPDATE HRMS_COMPOFF_HDR  SET COMP_STATUS =?   WHERE COMP_ID =?";
		   
		    
		
		   default : return "Framework could not EXECUTE the query number specified";	
		}
		 
	}

}
