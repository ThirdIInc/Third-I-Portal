package org.paradyne.sql.settings;

import org.paradyne.lib.SqlBase;

public class TipsModelSql extends SqlBase {

	public String getQuery(int code) {

		switch (code) {
	
	
case 1:
	return " INSERT INTO HRMS_SETTINGS_TIPS(TIP_CODE,TIP_LINKNAME,TIP_LINK,TIP_FLAG,TIP_LINK_DATE,TIP_STATUS,TIP_DIVISION) "
			+ " VALUES((SELECT NVL(MAX(TIP_CODE),0) + 1 FROM HRMS_SETTINGS_TIPS),?,?,?,SYSDATE,?,?) ";
case 2:
	return " UPDATE HRMS_SETTINGS_TIPS SET TIP_LINKNAME = ?, TIP_LINK = ?, TIP_STATUS = ?, TIP_FLAG = ?, TIP_DIVISION=?" +
			   " WHERE TIP_CODE = ?";	
case 3:
	return " SELECT TIP_CODE,TIP_LINKNAME,NVL(TIP_LINK,'No File Associated'),Decode(TIP_FLAG,'Y','YES','N','NO'),TIP_STATUS ,NVL(TIP_DIVISION,' ') FROM HRMS_SETTINGS_TIPS ORDER BY TIP_CODE ";

case 4:
	return " DELETE FROM HRMS_SETTINGS_TIPS WHERE TIP_CODE = ?";
	
case 5:
     return "SELECT TIP_CODE,TIP_LINKNAME,NVL(TIP_LINK,'No File Associated') FROM HRMS_SETTINGS_TIPS WHERE TIP_FLAG = 'Y'";
     
default:
	return " HI";
     
}
		

	}
}