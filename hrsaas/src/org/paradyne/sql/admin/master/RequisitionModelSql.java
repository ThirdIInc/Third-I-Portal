package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class RequisitionModelSql extends SqlBase {
	
	
	
	public String getQuery(int id) 
	{
		switch(id)
		{
						
			
			case 1: return " INSERT INTO HRMS_REQUISITION_HEAD (REQUISITION_HEAD_CODE,REQUISITION_HEAD_NAME) VALUES((SELECT NVL(MAX(REQUISITION_HEAD_CODE),0)+1 FROM HRMS_REQUISITION_HEAD),?)";
			
            case 2: return " UPDATE HRMS_REQUISITION_HEAD SET REQUISITION_HEAD_NAME=? WHERE REQUISITION_HEAD_CODE=?";
			
			case 3: return " DELETE FROM HRMS_REQUISITION_HEAD WHERE REQUISITION_HEAD_CODE=?";
			
			case 4: return " SELECT REQUISITION_HEAD_CODE,REQUISITION_HEAD_NAME  FROM HRMS_REQUISITION_HEAD ORDER BY REQUISITION_HEAD_CODE  ";
			
			default : return "Framework could not the query number specified";
			
		}
	}

}
