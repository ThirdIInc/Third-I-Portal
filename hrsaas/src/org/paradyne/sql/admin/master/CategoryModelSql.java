package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class CategoryModelSql extends SqlBase {
	
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_CATG (CATG_ID,CATG_NAME,CATG_DESC,CATG_AGE,IS_ACTIVE) " 
						+"VALUES((SELECT NVL(MAX(CATG_ID),0)+1 FROM HRMS_CATG ),?,?,?,?)";
		
		case 2: return " UPDATE HRMS_CATG SET CATG_NAME=?,CATG_DESC=?,CATG_AGE=?,IS_ACTIVE=? WHERE CATG_ID=?";
		
		case 3: return " DELETE FROM HRMS_CATG WHERE CATG_ID=?";
		 
		case 4: return " SELECT  CATG_ID,NVL(CATG_NAME,' '),DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'),NVL(CATG_DESC,' '),NVL(CATG_AGE,' ') FROM HRMS_CATG  WHERE CATG_ID=?";
		
		case 5: return " SELECT   CATG_ID,CATG_NAME,CATG_DESC,CATG_AGE FROM HRMS_CATG  ORDER BY CATG_ID ";
		case 6: return " SELECT  CATG_ID,NVL(CATG_NAME,' '),NVL(CATG_DESC,' '),DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_CATG ORDER BY  upper(CATG_NAME)";
		
		
		default : return "Framework could not the query number specified";			
		}
	}
}