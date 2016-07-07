package org.paradyne.sql.vendor;
/** @ Author : Archana Salunkhe
 * @ purpose : Developed to define Vendor Reporting Structure 
 * @ Date : 03-May-2012
 */
import org.paradyne.lib.SqlBase;

public class VendorReportStructureModelSql extends SqlBase {

	public String getQuery(int queryID){
		
		switch(queryID){
		
		case 1 :
			return "SELECT VENDOR_INFO.PARTNER_CODE,PARTNER_NAME, "
					+" (EMP_FNAME||' '||EMP_LNAME),DECODE(APPROVER_TYPE, 'Mgr', 'Manager','Ack','Acknowleged','Acc','Accountant'),"
					+" APPROVER_LEVEL,APPROVER_CODE,REPORTING_ID FROM  VENDOR_REPORTING_STR"  
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = VENDOR_REPORTING_STR.APPROVER_CODE)"
					+" INNER JOIN VENDOR_INFO ON(VENDOR_INFO.PARTNER_LOGIN_CODE = VENDOR_REPORTING_STR.PARTNER_CODE)"
					+" ORDER BY REPORTING_ID";
			
		case 2:
			return "SELECT VENDOR_INFO.PARTNER_CODE,PARTNER_NAME, (EMP_FNAME||' '||EMP_LNAME),"
	        	   +" APPROVER_TYPE,APPROVER_LEVEL,EMP_TOKEN,APPROVER_CODE,REPORTING_ID FROM  VENDOR_REPORTING_STR"  
	        	   +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = VENDOR_REPORTING_STR.APPROVER_CODE)"
	        	   +" INNER JOIN VENDOR_INFO ON(VENDOR_INFO.PARTNER_LOGIN_CODE = VENDOR_REPORTING_STR.PARTNER_CODE)"
	        	   +" WHERE REPORTING_ID =?";
		
		case 3:
			return "INSERT INTO VENDOR_REPORTING_STR (PARTNER_CODE,APPROVER_CODE, APPROVER_TYPE,APPROVER_LEVEL,REPORTING_ID)  "
			       +" VALUES(?,?,?,?,?);";
		
		case 4: 
			return "UPDATE VENDOR_REPORTING_STR SET PARTNER_CODE =?,APPROVER_CODE =?, APPROVER_TYPE =?, "
			       +" APPROVER_LEVEL =? WHERE REPORTING_ID =?";
		default:
			return "Query not Found";
		}
	}
}

