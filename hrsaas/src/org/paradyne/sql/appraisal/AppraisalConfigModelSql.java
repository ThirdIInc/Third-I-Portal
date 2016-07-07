/**
 *Bhushan
 *Jan 23, 2008 
 **/

package org.paradyne.sql.appraisal;

import org.paradyne.lib.SqlBase;

public class AppraisalConfigModelSql extends SqlBase {
	public String getQuery(int id) {
		try {
			switch (id) {
			case 1 : return  " SELECT EMP_ID, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,'N','N' FROM HRMS_EMP_OFFC" 
							+" WHERE EMP_STATUS = 'S' AND EMP_DEPT IS NOT NULL ORDER BY UPPER(NAME) ";

			case 2 : return " SELECT HRMS_APPRFACT_DTL.APPRFACT_CODE, APPRFACT_NAME, "
							+ " DECODE(APPRFACT_TYPE, 'S','Self Appraisal', 'K', 'KRAs', 'P', 'Personality Traits') TYPE, MAX(RATINGS_VAL), NVL(FLAG,'N') "
							+ " FROM HRMS_APPRFACT "
							+ " INNER JOIN HRMS_APPRFACT_DTL ON(HRMS_APPRFACT_DTL.APPRFACT_CODE = HRMS_APPRFACT.APPRFACT_CODE) "
							+ " LEFT JOIN  HRMS_APPRCONFIG_FACT ON(HRMS_APPRCONFIG_FACT.APPRFACT_CODE = HRMS_APPRFACT.APPRFACT_CODE  ) "
							+ " where DESG_ID =  ? "
							+ " GROUP BY HRMS_APPRFACT_DTL.APPRFACT_CODE, APPRFACT_NAME, APPRFACT_TYPE, FLAG "
							+ " ORDER BY HRMS_APPRFACT_DTL.APPRFACT_CODE ";

			case 3 : return " SELECT HRMS_APPRCONFIG_EMP.EMP_ID, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,'N','N'"
							+ " FROM  HRMS_APPRCONFIG_EMP "
							+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_APPRCONFIG_EMP.EMP_ID) "
							+ " WHERE DESG_ID = ? ";

				//	case 4 : return " INSERT INTO HRMS_APPRCONFIG_EMP VALUES(?, ?) ";

			

			case 6 : return " UPDATE HRMS_APPRCONFIG_FACT SET FLAG = ? where APPRFACT_CODE = ? and DESG_ID = ?  ";

			

			case 9 : return " INSERT INTO HRMS_APPRCONFIG_PRD_HDR (CONF_CODE,PRD_START_MTH,PRD_START_YEAR,PRD_END_MTH,PRD_END_YEAR,APP_FREQ) "
							+ " VALUES ((SELECT nvl(MAX(CONF_CODE),0)+1 FROM HRMS_APPRCONFIG_PRD_HDR), ? , ? ,? ,?,?) ";

			case 10 : return " INSERT INTO HRMS_APPRCONFIG_PRD_DTL (CONF_CODE,LOCK_FLAG,FINALIZE_FLAG,EMP_ID ) "
							+ " VALUES (?,?,?,?)";

			case 11 : return " UPDATE HRMS_APPRCONFIG_PRD_HDR SET FLAG = 'Y' WHERE CONF_CODE = ? ";

			case 12 : return " UPDATE HRMS_APPRCONFIG_PRD_HDR SET FLAG = 'N' WHERE CONF_CODE = ? ";

			case 13 : return " SELECT * FROM(SELECT HRMS_APPRCONFIG_PRD_DTL.EMP_ID, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME," 
							+" HRMS_APPRCONFIG_SELFCHECK,HRMS_APPRCONFIG_MGRCHECK, "
							+" HRMS_APPRCONFIG_HRCHECK,HRMS_APPRCONFIG_MGMTCHECK "
							+" FROM HRMS_EMP_OFFC "
							+" LEFT JOIN HRMS_APPRCONFIG_PRD_DTL ON (HRMS_EMP_OFFC.EMP_ID = HRMS_APPRCONFIG_PRD_DTL.EMP_ID ) "
							+" WHERE CONF_CODE =  ? AND EMP_STATUS = 'S' AND EMP_DEPT IS NOT NULL "
							+" UNION "
							+" SELECT HRMS_EMP_OFFC.EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,'','','','' "
							+" FROM HRMS_EMP_OFFC "
							+" LEFT JOIN HRMS_APPRCONFIG_PRD_DTL ON (HRMS_APPRCONFIG_PRD_DTL.EMP_ID = HRMS_EMP_OFFC.EMP_ID) " 
							+" WHERE HRMS_EMP_OFFC.EMP_ID NOT IN(SELECT EMP_ID FROM HRMS_APPRCONFIG_PRD_DTL) AND EMP_STATUS = 'S' AND EMP_DEPT IS NOT NULL) "
							+" ORDER BY UPPER(NAME)";

			case 14 : return " UPDATE HRMS_APPRCONFIG_PRD_HDR SET PRD_START_MTH = ? ,PRD_START_YEAR = ? ,PRD_END_MTH = ? ,PRD_END_YEAR = ? ,APP_FREQ = ? "
							+ " WHERE  CONF_CODE = ? ";

			case 15 : return " UPDATE HRMS_APPRCONFIG_PRD_DTL SET LOCK_FLAG = ?  WHERE  EMP_ID = ?  AND CONF_CODE = ? ";

			case 16 : return " UPDATE HRMS_APPRCONFIG_PRD_DTL SET FINALIZE_FLAG = ?  WHERE  EMP_ID = ?  AND CONF_CODE = ? ";
			
			case 17 : return "SELECT DISTINCT DESG_ID, HRMS_RANK.RANK_NAME "
							+"FROM HRMS_APPRCONFIG_FACT "
							+"INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_APPRCONFIG_FACT.DESG_ID) " 
							+"WHERE DESG_ID = ?";
			
			
			
			case 19 : return "SELECT DECODE(PRD_START_MTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', "
							+"5, 'May', 6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, "
							+"'November', 12, 'December')START_MONTH, PRD_START_YEAR, "
							+"DECODE(PRD_END_MTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May', "
							+"6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, "
							+"'December')END_MONTH, PRD_END_YEAR,  "
							+"TO_CHAR(START_DATE, 'DD-MM-YYYY') START_DATE, TO_CHAR(END_DATE, 'DD-MM-YYYY') END_DATE "
							+"FROM HRMS_APPRCONFIG_PRD_HDR "
							+"WHERE CONF_CODE = ?";
			
			case 20 : return " INSERT INTO HRMS_APPRAISAL_SCHEDULE(CONF_CODE,APP_TYPE,START_DATE,END_DATE,LOCK_FLAG) "
							+" VALUES(?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?)";
			
			case 21 : return " SELECT MAX(CONF_CODE) FROM HRMS_APPRCONFIG_PRD_HDR ";
			
			case 22 : return " UPDATE HRMS_APPRAISAL_SCHEDULE SET START_DATE = TO_DATE(?,'DD-MM-YYYY'),END_DATE = TO_DATE(?,'DD-MM-YYYY'),LOCK_FLAG = ? "
							+" WHERE CONF_CODE = ? AND APP_TYPE = ? ";
			
			case 23 : return " SELECT TO_CHAR(START_DATE,'DD-MM-YYYY'),TO_CHAR(END_DATE,'DD-MM-YYYY'),LOCK_FLAG FROM HRMS_APPRAISAL_SCHEDULE "
							+" WHERE CONF_CODE = ?";
			
			case 24 : return " INSERT INTO HRMS_APPRCONFIG_PRD_DTL (CONF_CODE,EMP_ID,HRMS_APPRCONFIG_SELFCHECK,HRMS_APPRCONFIG_MGRCHECK, "
							+" HRMS_APPRCONFIG_HRCHECK,HRMS_APPRCONFIG_MGMTCHECK) "
							+" VALUES(?,?,?,?,?,?) ";
			
			case 25 : return " UPDATE HRMS_APPRCONFIG_PRD_DTL SET HRMS_APPRCONFIG_SELFCHECK = ?,HRMS_APPRCONFIG_MGRCHECK = ?, "
							+" HRMS_APPRCONFIG_HRCHECK = ?,HRMS_APPRCONFIG_MGMTCHECK = ? "
							+" WHERE EMP_ID = ? AND CONF_CODE = ? ";
			
			case 26 : return " DELETE FROM HRMS_APPRCONFIG_PRD_DTL WHERE CONF_CODE = ?";
			
			case 27 : return " DELETE FROM HRMS_APPRAISAL_SCHEDULE WHERE CONF_CODE = ?";
			
			case 28 : return " DELETE FROM HRMS_APPRCONFIG_PRD_HDR WHERE CONF_CODE = ?";
			
			case 29 : return " UPDATE HRMS_APPRAISAL SET APPR_HOD_LEVEL = ?, APPR_STATUS = ? WHERE APPR_EMP_ID = ? AND CONF_CODE = ? ";

			default : return " Query not Found I m in default case of Sql File ";
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}