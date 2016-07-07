package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;
/**
 * @author pranali 
 * Date 25-04-07
 */
public class DesignationModelSql extends SqlBase{

	
	public String getQuery(int id) 
	{
		switch(id)
		{
			
			case 1: return " INSERT INTO HRMS_DESG (DESG_ID,DESG_NAME,DESG_PARENT_CODE,DESG_HIGHER_CODE,DESG_ABBR,DESG_DESC,DESG_LEVEL,DESG_AUTH_RECM,DESG_AUTH_APPR) " 
				           +"VALUES((SELECT NVL(MAX(DESG_ID),0)+1 FROM HRMS_DESG ),?,?,?,?,?,?,?,?)";
			
			case 2: return " UPDATE HRMS_DESG SET DESG_NAME=?,DESG_PARENT_CODE=?,DESG_HIGHER_CODE=?,DESG_ABBR=?,DESG_DESC=?,DESG_LEVEL=?,DESG_AUTH_RECM=?,DESG_AUTH_APPR=? WHERE DESG_ID=?";
			
			case 3: return " DELETE FROM HRMS_DESG WHERE DESG_ID=?";
			
			case 4: return " SELECT DESG_ID,DESG_NAME,DESG_PARENT_CODE,DESG_HIGHER_CODE,DESG_ABBR,DESG_DESC,DESG_LEVEL,DESG_AUTH_RECM,DESG_AUTH_APPR FROM HRMS_DESG WHERE DESG_ID=?";
			
			case 5: return " SELECT DESG_ID,DESG_NAME,DESG_PARENT_CODE,DESG_HIGHER_CODE,NVL(DESG_ABBR,' '),NVL(DESG_DESC,' '),NVL(DESG_LEVEL,' '),NVL(DESG_AUTH_RECM,' '),NVL(DESG_AUTH_APPR,' ') FROM HRMS_DESG ORDER BY DESG_ID" ;
			
			default : return "Framework could not the query number specified";
			
		}
	}
}
