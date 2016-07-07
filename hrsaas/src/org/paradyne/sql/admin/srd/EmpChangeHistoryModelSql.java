package org.paradyne.sql.admin.srd;

import org.paradyne.lib.SqlBase;

public class EmpChangeHistoryModelSql extends SqlBase {
	
	
	public String getQuery(int id){
		switch(id){
		 case 1: return " INSERT INTO HRMS_EMP_CHANGE_HISTORY(EMP_ID,CHANGE_TYPE,OLD_VALUE,NEW_VALUE,CHANGE_AUTHORITY_NO,CHANGE_AUTHORITY_DATE,CHANGE_DATE) VALUES(?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'))";
		 case 2: return "  SELECT  CASE WHEN CHANGE_TYPE= 'F' THEN 'First Name'  WHEN CHANGE_TYPE= 'M' THEN 'Middle Name'  WHEN CHANGE_TYPE= 'L' THEN 'Last Name'   WHEN CHANGE_TYPE= 'D'  THEN 'Date of Birth'    WHEN CHANGE_TYPE= 'T' THEN 'Token No' ELSE 'Home Town' END ,OLD_VALUE,NEW_VALUE,TO_CHAR(CHANGE_DATE,'DD-MM-YYYY')  "
                        +" FROM HRMS_EMP_CHANGE_HISTORY WHERE EMP_ID = ? ";
		 
		 
		 
		 
		 default:return"";
    }
		
	}

	
	

}
