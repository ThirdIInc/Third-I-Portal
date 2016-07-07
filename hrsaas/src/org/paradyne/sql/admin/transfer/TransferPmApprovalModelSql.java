package org.paradyne.sql.admin.transfer;
import org.paradyne.lib.SqlBase;
public class TransferPmApprovalModelSql extends SqlBase {
	public String getQuery(int id){
		switch(id){
				
		case 1:return " UPDATE HRMS_TRANSFER SET TRANSFER_CEONO =?, TRANSFER_CEODATE=TO_DATE(?,'DD-MM-YYYY') WHERE TRANSFER_CODE = ? " ; 
		
		
		default: return "Query could not find";

		
		
		}
}
	
	
	
}
