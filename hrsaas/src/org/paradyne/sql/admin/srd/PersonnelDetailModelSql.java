package org.paradyne.sql.admin.srd;

import org.paradyne.lib.SqlBase;

/**
 * @author lakkichand
 * @date 03 May 2007
 */

/** Updated By
 * @author Prajakta.Bhandare
 * @date 12 Dec 2012
 */
public class PersonnelDetailModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {

		case 1:
			
			return "INSERT INTO HRMS_EMP_PERS (EMP_ID,EMP_CASTE_CATG,EMP_CASTE,EMP_SUBCAST,"
					+" EMP_HEIGHT,EMP_WEIGHT, EMP_IDMARK,EMP_BLDGP,EMP_HANDI_DESC,EMP_HOBBY," 
					+" EMP_MARITAL_STATUS,EMP_MARRG_DATE,EMP_HOMETOWN,EMP_NATIONALITY,EMP_RELIGION," 
					+" EMP_ISHANDICAP,EMP_IS_CRIMINAL_REC,EMP_DISEASES,EMP_AILMENTS,EMP_ALLERGIES,EMP_CRIMINAL_REC_DESC)"
					+" VALUES(?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?)";

		case 2:
			return "UPDATE HRMS_EMP_PERS SET EMP_CASTE_CATG=?,EMP_CASTE=?, EMP_SUBCAST=?,EMP_HEIGHT=?,"
					+" EMP_WEIGHT=?,EMP_IDMARK=?,EMP_BLDGP=?,EMP_HANDI_DESC=?,EMP_HOBBY=?,"
					+" EMP_MARITAL_STATUS=?,EMP_MARRG_DATE=TO_DATE(?,'DD-MM-YYYY'),EMP_HOMETOWN=?,"
					+" EMP_NATIONALITY=?,EMP_RELIGION=?,EMP_IS_CRIMINAL_REC=?,EMP_DISEASES=?,"
					+" EMP_AILMENTS=?, EMP_ALLERGIES=?,EMP_CRIMINAL_REC_DESC=?,EMP_ISHANDICAP=?"
					+" WHERE EMP_ID=?"; 

		case 3:
			return " SELECT HRMS_EMP_PERS.EMP_ID,EMP_BLDGP,EMP_MARITAL_STATUS," 
					+ " TO_CHAR(EMP_MARRG_DATE,'DD-MM-YYYY'),RELIGION_NAME, CATG_NAME,"
					+ " CAST_NAME,EMP_SUBCAST,EMP_HANDI_DESC,EMP_NATIONALITY,EMP_HEIGHT,"
					+ " EMP_WEIGHT,EMP_IDMARK,EMP_HOBBY,"
					+ " EMP_ISHANDICAP,EMP_IS_CRIMINAL_REC,EMP_DISEASES,EMP_AILMENTS,"
					+ " EMP_ALLERGIES,EMP_CRIMINAL_REC_DESC,"
					+ " HRMS_LOCATION.LOCATION_NAME,HRMS_LOCATION.LOCATION_CODE,HRMS_RELIGION.RELIGION_ID,HRMS_CAST.CAST_ID,"
					+ " HRMS_CATG.CATG_ID" 
					+ " FROM HRMS_EMP_PERS"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_PERS.EMP_ID)"
					+ " LEFT JOIN HRMS_CATG ON(HRMS_EMP_PERS.EMP_CASTE_CATG=HRMS_CATG.CATG_ID)"
					+ " LEFT JOIN HRMS_CAST ON(HRMS_EMP_PERS.EMP_CASTE=HRMS_CAST.CAST_ID)" 
					+ " LEFT JOIN HRMS_RELIGION ON(HRMS_RELIGION.RELIGION_ID=HRMS_EMP_PERS.EMP_RELIGION)" 
					+ " LEFT JOIN HRMS_LOCATION ON(HRMS_EMP_PERS.EMP_HOMETOWN=HRMS_LOCATION.LOCATION_CODE)"
					+ " WHERE HRMS_EMP_PERS.EMP_ID=?"; 

		case 4:
			return "SELECT EMP_ID FROM HRMS_EMP_PERS WHERE EMP_ID=?";
		case 5:
			return "UPDATE HRMS_EMP_LANGUAGE SET LANGUAGE_CODE=?,LANGUAGE_READ=?,LANGUAGE_WRITE=?,LANGUAGE_SPEAK=?,"
					+" LANGUAGE_MOTHER_TOUNGE=? WHERE EMP_ID=? AND LANGUAGE_CODE=?";
		case 6:
			return " SELECT  HRMS_LANGUAGE.LANGUAGE_CODE,NVL(LANGUAGE_NAME,''),NVL(LANGUAGE_READ,'N'),  NVL(LANGUAGE_WRITE,'N')," 
					+" NVL(LANGUAGE_SPEAK,'N'), NVL(LANGUAGE_MOTHER_TOUNGE,'N') "   
					+" FROM HRMS_EMP_LANGUAGE "  
					+" LEFT JOIN  HRMS_LANGUAGE on (HRMS_EMP_LANGUAGE.LANGUAGE_CODE=HRMS_LANGUAGE.LANGUAGE_CODE)"
					+" WHERE LANGUAGE_IS_ACTIVE='Y'  AND EMP_ID=? ";
		case 7:
			return "INSERT INTO HRMS_EMP_LANGUAGE(EMP_ID,LANGUAGE_CODE,LANGUAGE_READ, LANGUAGE_WRITE,"
			+ " LANGUAGE_SPEAK, LANGUAGE_MOTHER_TOUNGE) VALUES(?,?,?,?,?,?)";
		
		case 8:
			 return "DELETE FROM HRMS_EMP_LANGUAGE WHERE EMP_ID=? AND LANGUAGE_CODE=?";
		
		
		default:
			return "";
		}
	}

}
