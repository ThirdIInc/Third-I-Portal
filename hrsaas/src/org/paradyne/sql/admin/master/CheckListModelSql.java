package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase; 
/**
 * @author pranali 
 * Date 26-04-07
 */
public class CheckListModelSql extends SqlBase{

	
	public String getQuery(int id) 
	{
		switch(id)
		{
						
			
			case 1: return " INSERT INTO HRMS_CHECKLIST  (CHECK_CODE,CHECK_NAME,CHECK_TYPE,CHECK_DESC,CHECK_STATUS) " 
				           +"VALUES((SELECT NVL(MAX(CHECK_CODE),0)+1 FROM HRMS_CHECKLIST ),?,?,?,?)";
			
			case 2: return " UPDATE HRMS_CHECKLIST SET CHECK_NAME=?,CHECK_TYPE=? ,CHECK_DESC=?,CHECK_STATUS=? WHERE CHECK_CODE=?";
			
			case 3: return " DELETE FROM HRMS_CHECKLIST WHERE CHECK_CODE=?";
			
			case 4: return " SELECT CHECK_CODE,CHECK_NAME,CHECK_TYPE,CHECK_DESC,CHECK_STATUS FROM HRMS_CHECKLIST WHERE CHECK_CODE=?";
			
			case 5: return " SELECT CHECK_CODE,CHECK_NAME," +
					" DECODE(CHECK_TYPE,'J','Joining CheckList','M','Medical CheckList','T','Transfer CheckList','I','Interview CheckList','B','Background Verification CheckList','P','Prejoining CheckList'), " +
					"  DECODE(CHECK_STATUS,'A','Active','D','Deactive') FROM HRMS_CHECKLIST ORDER BY upper(CHECK_NAME) " ;
			
			default : return "Framework could not the query number specified";
			
		}
	}
}
