package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class LWFConfigurationMasterModelSql extends SqlBase {
public String getQuery(int id){
		
		switch(id){
		
		
		case 1: return " DELETE FROM HRMS_LWF_SLAB_HDR WHERE LWF_CODE=?";
		case 2: return " DELETE FROM HRMS_LWF_SLAB_DTL WHERE LWF_CODE=?";
		case 3: return " DELETE FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE=?";
		
			
		default: return "query no. is not specified ";
		}
		
		
	}
}
