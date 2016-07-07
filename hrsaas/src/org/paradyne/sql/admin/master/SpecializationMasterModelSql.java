package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;
/**
 * 
 * @author Prasad
 *
 */

public class SpecializationMasterModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {
	         case 1: return " INSERT INTO  HRMS_SPECIALIZATION (SPEC_ID,SPEC_NAME,SPEC_ABBR,SPEC_DESC,SPEC_STATUS)" 
				+ " VALUES((SELECT NVL(MAX(SPEC_ID),0)+1 FROM HRMS_SPECIALIZATION),?,?,?,?)";
	          
	         case 2: return "UPDATE HRMS_SPECIALIZATION SET SPEC_NAME=?,SPEC_ABBR=?,SPEC_DESC=?,SPEC_STATUS=? WHERE  SPEC_ID=?";
	
	         case 3: return "DELETE FROM HRMS_SPECIALIZATION WHERE  SPEC_ID=?";
	         
	         case 4: return "SELECT SPEC_ID,SPEC_NAME,SPEC_ABBR,SPEC_DESC,SPEC_STATUS FROM HRMS_SPECIALIZATION ORDER BY  SPEC_ID";
	
				
	         case 5: return "SELECT SPEC_ID,SPEC_NAME,SPEC_ABBR,decode(SPEC_STATUS,'A','Active','D','Deactive') FROM HRMS_SPECIALIZATION ORDER BY  SPEC_ID";	
	
			default: return "no query is found.";
		}
	}

}
