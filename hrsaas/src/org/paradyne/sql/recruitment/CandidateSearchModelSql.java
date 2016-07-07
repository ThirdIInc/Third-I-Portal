/**
 * 
 */
package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

/**
 * @author shashikant
 *
 */
public class CandidateSearchModelSql extends SqlBase{

	public String getQuery(int id){
		switch (id) {
		case 1: return "SELECT LOCATION_CODE, LOCATION_NAME FROM HRMS_LOCATION "
					   +"WHERE LOCATION_TYPE = 'Ci' "
					   +"ORDER BY UPPER(LOCATION_NAME)";
		
		case 2: return "SELECT LOCATION_CODE, LOCATION_NAME FROM HRMS_LOCATION "
		   				+"WHERE LOCATION_TYPE = 'S'"
		   				+"ORDER BY UPPER(LOCATION_NAME)";
		
		case 3: return "SELECT LOCATION_CODE, LOCATION_NAME FROM HRMS_LOCATION "
		   				+"WHERE LOCATION_TYPE = 'C'"
		   				+"ORDER BY UPPER(LOCATION_NAME)";
		
		case 4 : return "SELECT FUNC_DOMAIN_CODE, FUNC_DOMAIN_NAME FROM HRMS_FUNC_DOMAIN_MASTER "
						+"WHERE FUNC_DOMAIN_STATUS = 'A' "
						+"ORDER BY UPPER(FUNC_DOMAIN_NAME)";
		
		case 5 : return "SELECT INDUS_CODE, INDUS_NAME FROM HRMS_INDUS_TYPE "
						+"WHERE INDUS_STATUS = 'A' "
						+"ORDER BY UPPER(INDUS_NAME)";
		
		case 6 : return "SELECT QUA_ID, QUA_NAME FROM HRMS_QUA "
						+"WHERE QUA_STATUS = 'A' "
						+"ORDER BY UPPER(QUA_NAME)";
		
		case 7 : return "SELECT SPEC_ID, SPEC_NAME FROM HRMS_SPECIALIZATION "
						+"WHERE SPEC_STATUS = 'A' "
						+"ORDER BY UPPER(SPEC_NAME)";
		
		case 8 : return "SELECT SKILL_ID, SKILL_NAME FROM HRMS_SKILL "
						+"WHERE SKILL_STATUS = 'A' "
						+"ORDER BY UPPER(SKILL_NAME)";
		
		case 9 : return "INSERT INTO HRMS_REC_CANDIDATE_SEARCH (SEARCH_CODE, SEARCH_KEY_WORDS, SEARCH_CRITERIA, "
						+"SERACH_IN_SHORT, SERACH_IN_REJECT, SERACH_IN_NEW, REQS_CODE, SEARCH_SOURCE, SEARCH_POSI_CRITERIA, "
						+"SEARCH_FROM_DATE, SEARCH_TO_DATE, SEARCH_MIN_EXP, SEARCH_MAX_EXP, SEARCH_FNAME, SEARCH_LNAME, "
						+"SEARCH_GENDER, SEARCH_MARITAL_STATUS, SEARCH_CITY, SEARCH_STATE, SEARCH_COUNTRY, SEARCH_FUNAREA, "
						+"SEARCH_INDTYPE, SEARCH_QUALI, SEARCH_SPEC, SEARCH_SKILL, SEARCH_SORTON, SEARCH_CV) "
						+"VALUES ((SELECT NVL(MAX(SEARCH_CODE),0)+1 FROM HRMS_REC_CANDIDATE_SEARCH), ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'DD-MM-YYYY'), "
						+"TO_DATE(?, 'DD-MM-YYYY'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		case 10 : return "SELECT SEARCH_CRITERIA, SERACH_IN_SHORT, SERACH_IN_REJECT, SERACH_IN_NEW, SEARCH_SOURCE, "
						 +"SEARCH_POSI_CRITERIA, TO_CHAR(SEARCH_FROM_DATE, 'DD-MM-YYYY'), TO_CHAR(SEARCH_TO_DATE, 'DD-MM-YYYY'), "
						 +"SEARCH_FNAME, SEARCH_LNAME, SEARCH_GENDER, " 
						 +"SEARCH_MARITAL_STATUS, SEARCH_CITY, SEARCH_STATE, SEARCH_COUNTRY, SEARCH_FUNAREA, "
						 +"SEARCH_INDTYPE, SEARCH_QUALI, SEARCH_SPEC, SEARCH_SKILL, SEARCH_SORTON, SEARCH_CV, "
						 +"SEARCH_MIN_EXP, SEARCH_MAX_EXP "
						 +"FROM HRMS_REC_CANDIDATE_SEARCH "
						 +"WHERE SEARCH_CODE = ?";
		
		case 11 : return "UPDATE HRMS_REC_CANDIDATE_SEARCH SET SEARCH_KEY_WORDS = ?, SEARCH_CRITERIA = ?, "
						 +"SERACH_IN_SHORT = ?, SERACH_IN_REJECT = ?, SERACH_IN_NEW = ?, REQS_CODE = ?, SEARCH_SOURCE = ?, "
						 +"SEARCH_POSI_CRITERIA = ?, SEARCH_FROM_DATE = TO_DATE(?, 'DD-MM-YYYY'), SEARCH_TO_DATE = TO_DATE(?, 'DD-MM-YYYY'), "
						 +"SEARCH_MIN_EXP = ?, SEARCH_MAX_EXP = ?, SEARCH_FNAME = ?, SEARCH_LNAME = ?, "
						 +"SEARCH_GENDER = ?, SEARCH_MARITAL_STATUS = ?, SEARCH_CITY = ?, SEARCH_STATE = ?, "
						 +"SEARCH_COUNTRY = ?, SEARCH_FUNAREA = ?, SEARCH_INDTYPE = ?, SEARCH_QUALI = ?, "
						 +"SEARCH_SPEC = ?, SEARCH_SKILL = ?, SEARCH_SORTON = ?, SEARCH_CV = ? "
						 +"WHERE SEARCH_CODE = ?";

		default: return "";
		}
	}
}
