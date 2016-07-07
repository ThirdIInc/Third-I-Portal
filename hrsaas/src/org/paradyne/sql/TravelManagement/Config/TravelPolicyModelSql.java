/**
 * 
 */
package org.paradyne.sql.TravelManagement.Config;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0417
 *
 */
public class TravelPolicyModelSql extends SqlBase {
	
	public String getQuery(int id) {
		// TODO Auto-generated method stub
		switch(id)
		{
			case 1: return " SELECT DISTINCT CADRE_NAME ,CADRE_ID  FROM  HRMS_CADRE ORDER BY CADRE_ID,CADRE_NAME "; 
			
			case 2: return " SELECT NVL(EXP_CATEGORY_NAME,' '),  DECODE(EXP_CATEGORY_UNIT,'D','Per Day','T','Per Travel','K','Per Kilometer') , EXP_CATEGORY_ID ,EXP_CATEGORY_UNIT  FROM HRMS_TMS_EXP_CATEGORY " 
                           +" WHERE EXP_CATEGORY_STATUS ='A' ORDER BY EXP_CATEGORY_ID,EXP_CATEGORY_NAME "; 
			case 3: return " SELECT NVL(JOURNEY_NAME,' ') ,NVL(JOURNEY_CLASS_NAME,' ') ,JOURNEY_ID FROM HRMS_TMS_JOURNEY "
                           +" ORDER BY JOURNEY_LEVEL,JOURNEY_ID ";
			case 4: return "  INSERT INTO HRMS_TMS_TRAVEL_POLICY (POLICY_ID, POLICY_NAME,  POLICY_EFFECTIVE_DATE," 
					       +" POLICY_SETTELMENT_DAYS, POLICY_ABBR, POLICY_CREATE_DATE, POLICY_DESC, POLICY_STATUS,  POLICY_GUIDLINES,POLICY_EXPCAT_TRAVEL,POLICY_EXPCAT_HOTEL,POLICY_TOTAL_EXPENSE ) "
					       	+" VALUES ((SELECT NVL(MAX(POLICY_ID),0)+1 FROM HRMS_TMS_TRAVEL_POLICY) ,? , TO_DATE(?,'DD-MM-YYYY'), "
					       	+" ? ,? , TO_DATE(SYSDATE,'DD-MM-YYYY') ,? ,? ,?,?,?,?) ";
			
			case 5: return "  UPDATE HRMS_TMS_TRAVEL_POLICY SET POLICY_NAME =?,  POLICY_EFFECTIVE_DATE =TO_DATE(?,'DD-MM-YYYY')," 
		        +" POLICY_SETTELMENT_DAYS =?, POLICY_ABBR=?, POLICY_CREATE_DATE = TO_DATE(SYSDATE,'DD-MM-YYYY'), POLICY_DESC =?, POLICY_STATUS = ?,  POLICY_GUIDLINES =?,POLICY_EXPCAT_TRAVEL = ?,POLICY_EXPCAT_HOTEL =?,POLICY_TOTAL_EXPENSE =? "
		       	+" WHERE POLICY_ID =? ";
	 
			default:return "Framework could not EXECUTE the query number specified"; 
		}
	
		
	}

}
