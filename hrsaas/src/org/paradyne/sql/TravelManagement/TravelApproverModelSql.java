package org.paradyne.sql.TravelManagement;

import org.paradyne.lib.SqlBase;

public class TravelApproverModelSql extends SqlBase {
	public String getQuery(int id) {
		 
		switch(id) {      
		   case 1:  return " INSERT INTO HRMS_TRAVEL_PATH (TRAVEL_ID, APPROVER_CODE, APPR_DATE, TRAVEL_REMARK, TRAVEL_STATUS) VALUES( ?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?) ";
		   
		   case 2: return "UPDATE HRMS_TRAVEL  SET TRAVEL_LEVEL=? , TRAVEL_APPROVER =? ,TRAVEL_ALTER_APPROVER = ? WHERE TRAVEL_ID =?";
		   
		   case 3: return "UPDATE HRMS_TRAVEL  SET TRAVEL_STATUS =?   WHERE TRAVEL_ID = ?";  
		
		   default : return "Framework could not EXECUTE the query number specified";	
		}
		 
	}

}
