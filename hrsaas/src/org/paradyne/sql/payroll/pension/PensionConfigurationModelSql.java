package org.paradyne.sql.payroll.pension;

import org.paradyne.lib.SqlBase;

public class PensionConfigurationModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {
		case 1:
			return 	  " SELECT " 
					+ "		PENS_CODE," 
					+ "		DECODE(PENS_TYPE_CODE, "  
					+ "		1, 'Superannuation',  "
					+ "		2, 'Voluntary', " 
					+ "		3, 'Compulsory', "
					+ " 	4, 'Death'," 
					+ "		'-'),"
					+ "		PENS_MIN_SERVICE," 
					+ "		PENS_MAX_SERVICE,"
					+ "		PENS_FORMULA," 
					+ "		PENS_COMP_FORMULA, " 
					+ "		TO_CHAR(PENS_MIN_AMOUNT, '99,999,990.99')" 
					+ " FROM "
					+ "		HRMS_PENSION_CONF WHERE PENS_TYPE_CODE=? " 
					+ " ORDER BY " 
					+ " 	PENS_CODE ";
			
		case 2:
			return  "  SELECT " +
					"	NVL(MAX(PENS_CODE),0)+1 AS PENS_CODE " +
					" FROM " +
					"	HRMS_PENSION_CONF";
			
		case 3:
			return  " INSERT INTO HRMS_PENSION_CONF " +
					" (PENS_CODE, PENS_TYPE_CODE, PENS_EFECTIVE_FROM, PENS_MIN_SERVICE, PENS_MAX_SERVICE, PENS_MIN_AMOUNT, PENS_EMP_STATUS, PENS_EMOL_FORMULA, PENS_EMOL_MONTHS, PENS_FORMULA) " +
				    " VALUES(?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?) ";	
			
		case 4:
			return  " INSERT INTO HRMS_PENSION_CONF " +
					" (PENS_CODE, PENS_TYPE_CODE, PENS_EFECTIVE_FROM, PENS_MIN_SERVICE, PENS_MAX_SERVICE, PENS_MIN_AMOUNT, PENS_EMP_STATUS, PENS_EMOL_FORMULA, PENS_EMOL_MONTHS, PENS_FORMULA, PENS_VOL_WEIYEARS) " +
				    " VALUES(?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?) ";	
			
		case 5:
			return  " SELECT  " +
					" 	PENS_CODE, " +
					"	PENS_TYPE_CODE, " +
					"	TO_CHAR(PENS_EFECTIVE_FROM,'DD-MM-YYYY'), " +
					"	PENS_MIN_SERVICE, " +
					"	PENS_MAX_SERVICE, " +
					"	PENS_MIN_AMOUNT, " +
					"	PENS_EMP_STATUS, " +
					"	PENS_EMOL_FORMULA, " +
					"	PENS_EMOL_MONTHS, " +
					"	PENS_FORMULA, " +
					"	PENS_VOL_WEIYEARS," +
					"	PENS_COMP_FORMULA " +
					" FROM " +
					"	HRMS_PENSION_CONF " +
					" WHERE" +
					"	PENS_CODE = ? "; 
			
		case 6:
			return  " UPDATE " + 
					" 	HRMS_PENSION_CONF " +
					" SET " +
					"	PENS_TYPE_CODE = ?, " + 
					"	PENS_EFECTIVE_FROM = TO_DATE(?,'DD-MM-YYYY'), " + 
					"	PENS_MIN_SERVICE = ?, " + 
					"	PENS_MAX_SERVICE = ?, " + 
					"	PENS_MIN_AMOUNT = ?, " + 
					"	PENS_EMP_STATUS = ?, " + 
					"	PENS_EMOL_FORMULA = ?, " +
					"	PENS_EMOL_MONTHS = ?, " +
					"	PENS_FORMULA = ?, " +
					"	PENS_VOL_WEIYEARS = ?, " +
					"	PENS_COMP_FORMULA = ? " +
					" WHERE " +
					"	PENS_CODE = ? ";
			
			
		case 7 :	
			return " DELETE FROM " +
					"	HRMS_PENSION_CONF " +
					" WHERE " +
					"	PENS_CODE = ? ";	
			
		case 8:
			return  " INSERT INTO HRMS_PENSION_CONF " +
					" (PENS_CODE, PENS_TYPE_CODE, PENS_EFECTIVE_FROM, PENS_MIN_SERVICE, PENS_MAX_SERVICE, PENS_MIN_AMOUNT, PENS_EMP_STATUS, PENS_EMOL_FORMULA, PENS_EMOL_MONTHS, PENS_FORMULA, PENS_COMP_FORMULA) " +
				    " VALUES(?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?) ";		
		
		case 9:
			return " SELECT " +
				   "	PENS_BASICHEAD, " +
				   " 	PENS_BASIC_PERN, " +
				   " 	(SELECT CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE CREDIT_CODE = PENS_BASICHEAD) AS CREDIT_HEAD1, " +
				   " 	PENS_DPHEAD, " + 
				   " 	PENS_DP_PERN, " +
				   " 	(SELECT CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE CREDIT_CODE = PENS_DPHEAD) AS CREDIT_HEAD1, " + 
				   "	PENS_DAHEAD, " + 
				   " 	PENS_DA_PERN, " +
				   " 	(SELECT CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE CREDIT_CODE = PENS_DAHEAD) AS CREDIT_HEAD1 " + 
				   " FROM " +
				   "	HRMS_PENSION_AMTSPLIT ";
		case 10 :	
			return " DELETE " +
				   " FROM " +
				   " 	HRMS_PENSION_AMTSPLIT ";
		
		case 11 :
			return  " INSERT INTO HRMS_PENSION_AMTSPLIT " +
					" (PENS_BASICHEAD,PENS_BASIC_PERN,PENS_DPHEAD,PENS_DP_PERN,PENS_DAHEAD,PENS_DA_PERN) " +
					" VALUES(?,?,?,?,?,?) ";	
			
		case 12:
			return " INSERT INTO HRMS_PENSION_CREDIT_CONF(CREDIT_CONF_CODE,CREDIT_CODE,CREDIT_TYPE,CREDIT_VALUES) "
					+ " VALUES(?,?,?,?)";
		
		case 13:
			return " UPDATE HRMS_PENSION_CREDIT_CONF SET CREDIT_CODE=?,CREDIT_TYPE=? ,CREDIT_VALUES =? WHERE CREDIT_CONF_CODE=?  ";
		
			
		default:
			
			return " Framework could not the query number specified";
		}

	}

}
