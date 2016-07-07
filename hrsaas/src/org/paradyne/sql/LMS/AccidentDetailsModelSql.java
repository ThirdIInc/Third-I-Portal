/**
 * @author Manish Sakpal
 * created on 11th Feb. 2011
 */

package org.paradyne.sql.LMS;

import org.paradyne.lib.SqlBase;

public class AccidentDetailsModelSql extends SqlBase {
	public String getQuery(int id) {
	
		switch (id) {
		case 1:
			return "INSERT INTO HRMS_ACCIDENT_HDR (ACCIDENT_CODE,ACC_CASUALITY_TYPE,ACCIDENT_TYPE,ACCIDENT_PLACE," 
					+" ACCIDENT_DATE,ACCIDENT_TIME,ACCIDENT_CAUSE,IS_ACCIDENT_INVESTIGATED,"
					+" ACC_GENERAL_LOCATION,ACC_SPECIFIC_LOCATION,ACC_INVESTIGATION_MEANS,ACC_PREVENTIVE_MEASURES)"
					+" VALUES(?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'HH:MI'),?,?,?,?,?,?)";
			
		case 2:
			return " INSERT INTO HRMS_ACCIDENT_VICTMS (VICTIM_CODE,ACCIDENT_CODE,VICTIM_EMP_ID,VICTIM_STATUS,"
					+" VICTIM_INJURY_DTL,VICTIM_BODY_PARTS_AFFCTD,DEATH_DATE,DEATH_TIME,VICTIM_INSURANCE_NO,"
					+" VICTIM_COMPENSATION_AMT,IS_LEGAL_HEIR_EMPLOYED,HEIR_NAME,HEIR_RELATIONSHIP,"
					+" VICTIM_HOSPITIZATION,ACC_WORK_PERFORMED,ACC_PROTECTIVE_MEASURES)"
					+" VALUES(?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'HH:MI'),?,?,?,?,?,?,?,?)";
		
		case 3:
			return "INSERT INTO HRMS_ACCIDENT_WITNESS (WITNESS_ID,ACCIDENT_CODE,WITNESS_NAME,WITNESS_ADDRESS,WITNESS_OCCUPATION)"
					+" VALUES(?,?,?,?,?)";
			
		default:
			return " Framework could not find query number specified";
	
		}
	}
}
