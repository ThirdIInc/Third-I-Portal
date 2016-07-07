package org.paradyne.sql.TravelManagement.TravelPlan;

import org.paradyne.lib.SqlBase;

public class TravelApproverModelSql extends SqlBase {
	public String getQuery(int id) {
		 
		switch(id) {      
		   case 1:  return " INSERT INTO HRMS_TMS_APP_PATH (APP_PATH_APP_ID, APP_PATH_APPROVER_CODE, APP_PATH_APPROVER_DATE, APP_PATH_APPROVER_REMARK, APP_PATH_STATUS) VALUES( ?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?) ";
		   
		   case 2: return "UPDATE HRMS_TMS_TRAVEL_APP  SET TRAVEL_APP_LEVEL=? , TRAVEL_APP_APPROVER =? ,TRAVEL_APP_ALTER_APPROVER = ? WHERE TRAVEL_APP_ID =?";
		   
		   case 3: return "UPDATE HRMS_TMS_TRAVEL_APP  SET TRAVEL_APP_STATUS =?   WHERE TRAVEL_APP_ID = ?"; 
		   
		   case 4:  return " INSERT INTO HRMS_TMS_CANCEL_APP_PATH (APP_PATH_CAN_APP_ID, APP_PATH_CAN_APPROVER_CODE, APP_PATH_CAN_APPROVER_DATE, APP_PATH_CAN_APPROVER_REMARK, APP_PATH_CAN_STATUS) VALUES( ?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?) ";
		
		   case 5 : return "UPDATE HRMS_TMS_TRAVEL_APP  SET TRAVEL_CANCEL_STATUS  =?   WHERE TRAVEL_APP_ID = ?";  
		   
		   case 6: return "UPDATE HRMS_TMS_TRAVEL_APP  SET TRAVEL_CANCEL_LEVEL=? , TRAVEL_CANCEL_APPROVER =? ,TRAVEL_CANCEL_ALTER_APPROVER = ? WHERE TRAVEL_APP_ID =?";
		  
		   default : return "Framework could not EXECUTE the query number specified";	
		}
		 
	}

}
