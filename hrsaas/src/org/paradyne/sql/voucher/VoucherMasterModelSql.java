package org.paradyne.sql.voucher;

import org.paradyne.lib.SqlBase;

public class VoucherMasterModelSql extends SqlBase {
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_VCH_HD (VCH_CODE,VCH_NAME)" 
						+ " VALUES((SELECT NVL(MAX(VCH_CODE),0)+1 FROM HRMS_VCH_HD),?)";


		
		case 2: return " UPDATE HRMS_VCH_HD SET VCH_NAME =? WHERE VCH_CODE = ?";
		
		case 3: return " DELETE FROM HRMS_VCH_HD WHERE VCH_CODE=?";
		
       case 4: return " SELECT   VCH_CODE,VCH_NAME  FROM HRMS_VCH_HD";
		
        case 5: return " SELECT  VCH_CODE, VCH_NAME  FROM HRMS_VCH_HD ORDER  BY VCH_CODE ";
        
         case 6: return " SELECT  VCH_CODE, VCH_NAME FROM HRMS_VCH_HD ORDER  BY VCH_CODE ";
		
		
		
			
		default : return "Framework could not EXECUTE the query number specified";			
		}
	}
}
