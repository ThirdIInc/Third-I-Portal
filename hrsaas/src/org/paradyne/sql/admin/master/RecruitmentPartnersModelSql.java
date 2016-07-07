
package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author Prasad
 *
 */
public class RecruitmentPartnersModelSql extends SqlBase{
	
	public String getQuery(int id){
		
		switch(id){
		
			case 1: return "INSERT INTO HRMS_REC_PARTNER (REC_CODE,REC_PARTNERNAME,REC_TYPE,REC_CONTACTPERSON,REC_ADDRESS,REC_CITY,REC_PHONENO,REC_PINCODE,REC_MOBILENO,REC_DESC,REC_EMAIL,REC_FAXNO,REC_STATUS,REC_CHARGES,REC_PASSWORD,REC_TERMS_CONDITIONS,REC_START_DATE,REC_END_DATE) " 
				           +"  VALUES((SELECT NVL(MAX(REC_CODE),0)+1 FROM HRMS_REC_PARTNER),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY')) ";
				           
				          
			
			case 2: return " UPDATE HRMS_REC_PARTNER SET REC_PARTNERNAME=?,REC_TYPE=?,REC_CONTACTPERSON=?," +
					" REC_ADDRESS=?,REC_CITY=?,REC_PHONENO=?,REC_PINCODE=?,REC_MOBILENO=?,REC_DESC=?,REC_EMAIL=?," +
					" REC_FAXNO=?,REC_STATUS=?,REC_CHARGES=?,REC_TERMS_CONDITIONS=?," +
					" REC_START_DATE=TO_DATE(?,'DD-MM-YYYY'),REC_END_DATE=TO_DATE(?,'DD-MM-YYYY')  WHERE REC_CODE=?";
			
			case 3: return " DELETE FROM HRMS_REC_PARTNER WHERE REC_CODE=?";

			default : return "Framework could not the query number specified";
		}
	}
}

