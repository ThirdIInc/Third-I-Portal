package org.paradyne.sql.TravelManagement.TravelMaster;

import org.paradyne.lib.SqlBase;

	public class TravelApprovaleModelSql  extends SqlBase {
		public String getQuery(int id) {
			 
			switch(id) {      
			   case 1:  return " INSERT INTO HRMS_TRAVEL_PATH (TRAVEL_ID, APPROVER_CODE, APPR_DATE, TRAVEL_REMARK, TRAVEL_STATUS) VALUES( ?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?) ";
			   
			   case 2: return "UPDATE HRMS_TMS_TRAVEL_APP  SET TRAVEL_APP_LEVEL=? , TRAVEL_APP_APPROVER =? ,TRAVEL_APP_ALTER_APPROVER = ? WHERE TRAVEL_APP_ID =?";
			   
			   case 3: return "UPDATE HRMS_TMS_TRAVEL_APP  SET TRAVEL_APP_STATUS =?   WHERE TRAVEL_APP_ID = ?";  
			
			   default : return "Framework could not EXECUTE the query number specified";	
			}
			 
		}

	}


