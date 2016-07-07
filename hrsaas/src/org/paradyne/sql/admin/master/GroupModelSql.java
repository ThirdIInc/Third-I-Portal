package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;
/**
 * @author pranali 
 * Date 24-04-07
 */
public class GroupModelSql extends SqlBase {


	public String getQuery(int id) 
	{
		switch(id)
		{
						
			
			case 1: return " INSERT INTO HRMS_GROUP (GROUP_ID,GROUP_NAME,GROUP_DESC) " 
				           +"VALUES((SELECT NVL(MAX(GROUP_ID),0)+1 FROM HRMS_GROUP ),?,?)";
			
			case 2: return " UPDATE HRMS_GROUP SET GROUP_NAME=?, GROUP_DESC=? WHERE GROUP_ID=?";
			
			case 3: return " DELETE FROM HRMS_GROUP WHERE GROUP_ID=?";
			
				
			case 4: return " SELECT GROUP_ID,GROUP_NAME, GROUP_DESC FROM HRMS_GROUP ORDER BY GROUP_ID"  ;
			
			case 5: return " SELECT GROUP_ID,GROUP_NAME, GROUP_DESC FROM HRMS_GROUP WHERE GROUP_ID=?"  ;
			case 6: return " SELECT GROUP_ID,GROUP_NAME, NVL(GROUP_DESC,'') FROM HRMS_GROUP ORDER BY GROUP_ID" ;
			
			
			default : return "Framework could not the query number specified";
			
		}
	}
}
