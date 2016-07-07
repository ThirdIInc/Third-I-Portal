package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;

/**
 * @author saipavan voleti
 *
 */
public class PayrollZoneMasterModelSql extends SqlBase {
	
	public String getQuery(int id) {
		switch(id) {
		
		case 1:return "UPDATE HRMS_EMP_TYPE SET TYPE_ESI=?,TYPE_PT=?,TYPE_PF_MIN_AMT=?  WHERE TYPE_ID=? ";
		
		case 2:return "UPDATE HRMS_CENTER SET CENTER_ESI=?, CENTER_PT=?, CENTER_PTAX_STATE=?,CENTER_ISMETRO=?,CENTER_ESI_CODE=? WHERE CENTER_ID=? ";
		
		case 3:return " SELECT TYPE_ID, NVl(TYPE_NAME,' '), NVL(TYPE_ESI,' '),"
						+" NVL(TYPE_PT,' '),NVL(TYPE_PF_MIN_AMT,' ')"
						+" FROM  HRMS_EMP_TYPE ORDER BY TYPE_ID ";
		
		case 4:return " SELECT CENTER_ID, NVl(CENTER_NAME,' '), NVL(CENTER_ESI,' '),"
			+" NVL(CENTER_PT,' '),HRMS_LOCATION.LOCATION_CODE,HRMS_LOCATION.LOCATION_NAME,CENTER_ESI_CODE,CENTER_ISMETRO	 "
			+" FROM  HRMS_CENTER " +
					" left JOIN  HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_CENTER.CENTER_PTAX_STATE)" +
					" ORDER BY CENTER_ID ";
		
		default: return"Framework could not EXECUTE the query number specified";
		
		}
	}

}
