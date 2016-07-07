package org.paradyne.sql.TravelManagement.TravelMaster;

import org.paradyne.lib.SqlBase;

public class TravelPurposeModelSql 
extends SqlBase {
	
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_TMS_PURPOSE (PURPOSE_ID,PURPOSE_NAME,PURPOSE_DESC,PURPOSE_STATUS) "
						+"VALUES((SELECT NVL(MAX(PURPOSE_ID),0)+1 FROM HRMS_TMS_PURPOSE ),?,?,?)";
		
		case 2: return " UPDATE HRMS_TMS_PURPOSE SET PURPOSE_NAME=?,PURPOSE_DESC=?,PURPOSE_STATUS=? WHERE PURPOSE_ID=?";
		
		case 3: return " DELETE FROM HRMS_TMS_PURPOSE  WHERE PURPOSE_ID=?";
		
		case 4: return " SELECT  PURPOSE_ID,nvl(PURPOSE_NAME,'') FROM HRMS_TMS_PURPOSE WHERE PURPOSE_ID=?";
		
		case 5: return " SELECT PURPOSE_ID,nvl(PURPOSE_NAME,'')FROM HRMS_TMS_PURPOSE ORDER BY PURPOSE_ID";
		
		case 6: return " SELECT  PURPOSE_ID,SUBSTR(PURPOSE_NAME,0,20),,DECODE(PURPOSE_STATUS,'A','Active','D','Deactive') FROM HRMS_TMS_PURPOSE ORDER BY PURPOSE_ID";
		
		
		default : return "Framework could not the query number specified";			
		}
	}
}
