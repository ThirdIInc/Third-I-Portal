package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class CasteModelSql extends SqlBase {
	
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_CAST (CAST_ID,CAST_NAME,CAST_CATG,IS_ACTIVE) " 
						+"VALUES((SELECT NVL(MAX(CAST_ID),0)+1 FROM HRMS_CAST ),?,?,?)";
		
		case 2: return " UPDATE HRMS_CAST SET CAST_NAME=?,CAST_CATG=?,IS_ACTIVE=? WHERE CAST_ID=?";
		
		case 3: return " DELETE FROM HRMS_CAST WHERE CAST_ID=?";
		
		case 4: return " SELECT   CAST_NAME , CATG_NAME ,CAST_ID FROM HRMS_CAST JOIN HRMS_CATG ON HRMS_CAST.CAST_CATG=HRMS_CATG.CATG_ID WHERE CAST_ID=?";
		
		case 5: return " SELECT   CATG_ID , CATG_NAME FROM HRMS_CATG ";
		case 6:return " SELECT  CAST_ID, CAST_NAME , CATG_NAME , FROM HRMS_CAST  left JOIN HRMS_CATG ON HRMS_CAST.CAST_CATG=HRMS_CATG.CATG_ID  ORDER BY   CAST_ID";
		case 7:return " SELECT  CAST_ID , CAST_NAME , nvl(CATG_NAME,'') , CAST_CATG, DECODE(HRMS_CAST .IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_CAST  " 
               +" left JOIN HRMS_CATG ON HRMS_CAST.CAST_CATG=HRMS_CATG.CATG_ID ORDER BY upper(CAST_NAME)  ";		
		
		default : return "Framework could not the query number specified";			
		}
	}
}