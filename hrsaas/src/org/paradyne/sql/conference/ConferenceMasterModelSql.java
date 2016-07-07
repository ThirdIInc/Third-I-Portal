package org.paradyne.sql.conference;

import org.paradyne.lib.SqlBase;

/**
 * @author Tarun Chaturvedi
 *
 */
public class ConferenceMasterModelSql extends SqlBase {
	public String getQuery(int id){
		switch(id){
		
		/*case 1 : return "INSERT INTO HRMS_CONF_ACCESORIES (ACCESSORY_CODE, ACCESSORY_NAME, ACCESSORY_RES_PERSON) "
						+"VALUES ((SELECT NVL(MAX(ACCESSORY_CODE),0)+1 FROM HRMS_CONF_ACCESORIES),?,? )";*/
				
		case 1 : return "INSERT INTO HRMS_CONF_ACCESORIES (ACCESSORY_CODE, ACCESSORY_NAME,ACCESSORY_ISACTIVE) "
						+"VALUES ((SELECT NVL(MAX(ACCESSORY_CODE),0)+1 FROM HRMS_CONF_ACCESORIES),?,? )";
		
		/*case 2 : return "UPDATE HRMS_CONF_ACCESORIES SET ACCESSORY_NAME =?, ACCESSORY_RES_PERSON = ? "
						+"WHERE ACCESSORY_CODE = ?";*/
		
		case 2 : return "UPDATE HRMS_CONF_ACCESORIES SET ACCESSORY_NAME =?,ACCESSORY_ISACTIVE =? WHERE ACCESSORY_CODE = ?";
		
		
		case 3 : return "DELETE FROM HRMS_CONF_ACCESORIES WHERE ACCESSORY_CODE = ?";
		
		/*case 4 : return "SELECT ACCESSORY_CODE, ACCESSORY_NAME, HRMS_TITLE.TITLE_NAME||' ' ||HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME " 
						+"||' '||HRMS_EMP_OFFC.EMP_LNAME NAME "
						+"FROM HRMS_CONF_ACCESORIES "
						+"INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_ACCESORIES.ACCESSORY_RES_PERSON) "
						+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
						+"ORDER BY ACCESSORY_CODE";*/
		
		case 4 : return "SELECT ACCESSORY_CODE, ACCESSORY_NAME,CASE WHEN ACCESSORY_ISACTIVE ='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_CONF_ACCESORIES "
						+" ORDER BY ACCESSORY_CODE";
		
		case  5: return " DELETE FROM HRMS_CONF_ACCESORIES WHERE ACCESSORY_CODE = ?";
					
		
		
		 
		default : return "Framework could not found the query number specified";		
		}
	}
}
