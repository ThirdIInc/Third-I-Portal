package org.paradyne.sql.admin.master;
import org.paradyne.lib.SqlBase;
/*
 * Anantha lakshmi
 */

public class LangMasterModelSql extends SqlBase {
	public String getQuery(int id) 
	{
		switch(id)
		{
		case 1: return " INSERT INTO HRMS_LANGUAGE(LANGUAGE_CODE, LANGUAGE_NAME, COUNTRY_NAME,LANGUAGE_IS_ACTIVE) "
						+" VALUES((SELECT NVL(MAX(LANGUAGE_CODE),0)+1 FROM HRMS_LANGUAGE),?,?,?) " ;
		
		case 2: return "UPDATE HRMS_LANGUAGE SET LANGUAGE_NAME = ? ,COUNTRY_NAME=?, LANGUAGE_IS_ACTIVE=? WHERE LANGUAGE_CODE = ? " ;
		
		case 3: return "DELETE FROM HRMS_LANGUAGE WHERE LANGUAGE_CODE = ? " ;
		
		case 4 : return " SELECT  LANGUAGE_CODE, LANGUAGE_NAME, COUNTRY_NAME, DECODE(LANGUAGE_IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_LANGUAGE ORDER BY upper(LANGUAGE_NAME) "; 
		
		default: return " Query is not available " ;
		}
	}
}
