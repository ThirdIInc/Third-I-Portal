package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class CreditModelSql extends SqlBase  {
	
	/**
	 * @author pranali 
	 * Date 25-04-07
	 */
	public String getQuery(int id) 
	{
		switch(id)
		{
						
			
			case 1: return " INSERT INTO HRMS_HR_CREDIT_HEAD (CREDIT_CODE,CREDIT_NAME,CREDIT_ABBR) " 
				           +"VALUES((SELECT NVL(MAX(CREDIT_CODE),0)+1 FROM HRMS_HR_CREDIT_HEAD  ),?,?)";
			
			case 2: return " UPDATE HRMS_HR_CREDIT_HEAD SET CREDIT_NAME=?,CREDIT_ABBR=? WHERE CREDIT_CODE=?";
			
			case 3: return " DELETE FROM HRMS_HR_CREDIT_HEAD WHERE CREDIT_CODE=?";
			
			case 4: return " SELECT CREDIT_CODE,CREDIT_NAME,CREDIT_ABBR FROM HRMS_HR_CREDIT_HEAD WHERE CREDIT_CODE=?";
			
			case 5: return " SELECT CREDIT_CODE,CREDIT_NAME,CREDIT_ABBR FROM HRMS_HR_CREDIT_HEAD " ;
			
			default : return "Framework could not the query number specified";
			
		}
	}

}
