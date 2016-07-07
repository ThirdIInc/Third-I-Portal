package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;
/**
 * 
 * @author saipavan
 *
 */

public class ModuleMasterModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {
	         case 1: return " INSERT INTO  HRMS_MODULE (MODULE_CODE, MODULE_NAME, MODULE_DESC,MODULE_AUTHFLAG,MODULE_TYPE)" 
				+ " VALUES((SELECT NVL(MAX(MODULE_CODE),0)+1 FROM HRMS_MODULE),?,?,?,?)";
	          
	         case 2: return "UPDATE HRMS_MODULE SET MODULE_NAME=?,MODULE_DESC=?,MODULE_AUTHFLAG=?,MODULE_TYPE=? WHERE  MODULE_CODE=?";
	
	         case 3: return "DELETE FROM HRMS_MODULE WHERE  MODULE_CODE=?";
	         
	         case 4: return "SELECT MODULE_CODE, MODULE_NAME, MODULE_DESC,MODULE_AUTHFLAG,MODULE_TYPE FROM HRMS_MODULE ORDER BY  MODULE_CODE";
	
				
	         case 5: return "SELECT MODULE_CODE, MODULE_NAME,MODULE_TYPE,MODULE_DESC FROM HRMS_MODULE ORDER BY  MODULE_CODE";	
	
			default: return "no query is found.";
		}
	}

}
