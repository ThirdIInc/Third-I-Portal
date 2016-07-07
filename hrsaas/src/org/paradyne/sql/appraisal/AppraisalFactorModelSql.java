/**
 *Bhushan
 *Jan 22, 2008 
**/

package org.paradyne.sql.appraisal;

import org.paradyne.lib.SqlBase;

public class AppraisalFactorModelSql extends SqlBase 
{
	public String getQuery(int id)
	{
		try
		{
			switch (id)
			{
				/*
				 * old query for insert
				 * 
				 case 1 : return " INSERT INTO HRMS_APPRFACT VALUES((SELECT NVL(MAX(APPRFACT_CODE),0)+1 FROM HRMS_APPRFACT), ?, ?) ";
				*/
				case 1 : return " INSERT INTO HRMS_APPRFACT VALUES((SELECT NVL(MAX(APPRFACT_CODE),0)+1 FROM HRMS_APPRFACT), ?, ?,?) ";
			
				case 2 : return " SELECT MAX(APPRFACT_CODE) FROM HRMS_APPRFACT ";
				
				case 3 : return " INSERT INTO HRMS_APPRFACT_DTL VALUES(?, ?, ?) ";
				
				case 4 : return " UPDATE HRMS_APPRFACT SET APPRFACT_NAME = ?, APPRFACT_TYPE = ?,APPRFACT_WEIGHTAGE=? WHERE APPRFACT_CODE = ? ";
				
				case 5 : return " DELETE FROM HRMS_APPRFACT_DTL WHERE APPRFACT_CODE = ? ";
				
				case 6 : return " DELETE FROM HRMS_APPRFACT WHERE APPRFACT_CODE = ? ";
				
				case 7 : return " SELECT RATINGS, RATINGS_VAL FROM HRMS_APPRFACT_DTL WHERE APPRFACT_CODE = ? ORDER BY RATINGS_VAL DESC ";
				
				/*case 8 : return "SELECT HRMS_APPRFACT_DTL.APPRFACT_CODE, APPRFACT_NAME, "
						+ " DECODE(APPRFACT_TYPE, 'S','Self Appraisal', 'K', 'KRAs', 'P', 'Personality Traits') TYPE, MAX(RATINGS_VAL), 'N' "
						+ " FROM HRMS_APPRFACT "
						+ " INNER JOIN HRMS_APPRFACT_DTL ON(HRMS_APPRFACT_DTL.APPRFACT_CODE = HRMS_APPRFACT.APPRFACT_CODE) "
						+ " GROUP BY HRMS_APPRFACT_DTL.APPRFACT_CODE, APPRFACT_NAME, APPRFACT_TYPE "
						+ " ORDER BY HRMS_APPRFACT_DTL.APPRFACT_CODE ";*/
				
				/*case 8 : return "SELECT HRMS_APPRFACT.APPRFACT_CODE, APPRFACT_NAME, "
						+ " DECODE(APPRFACT_TYPE, 'S','Self Appraisal', 'K', 'KRAs', 'P', 'Personality Traits') TYPE,'N',NVL(APPRFACT_WEIGHTAGE,0) "
						+ " FROM HRMS_APPRFACT "
						//+ " GROUP BY HRMS_APPRFACT.APPRFACT_CODE, APPRFACT_NAME, APPRFACT_TYPE "
						+ " ORDER BY TYPE ";*/
				case 8 :return "SELECT HRMS_APPRFACT.APPRFACT_CODE, APPRFACT_NAME,  DECODE(APPRFACT_TYPE, 'S','Self Appraisal', 'K', 'KRAs', 'P', 'Personality Traits') COL,"
						+" NVL(FLAG,'N'),NVL(APPRFACT_WEIGHTAGE,0)  FROM HRMS_APPRFACT " 
						+" LEFT JOIN HRMS_APPRCONFIG_FACT  ON (HRMS_APPRFACT.APPRFACT_CODE = HRMS_APPRCONFIG_FACT.APPRFACT_CODE AND DESG_ID = ? ) "
						+" ORDER BY COL ";

				case 9 : return " SELECT HRMS_APPRFACT.APPRFACT_CODE,NVL(FLAG,'N') FROM HRMS_APPRFACT "
				+ " LEFT JOIN HRMS_APPRCONFIG_FACT  ON (HRMS_APPRFACT.APPRFACT_CODE = HRMS_APPRCONFIG_FACT.APPRFACT_CODE AND DESG_ID = ? ) "
				+ " ORDER BY APPRFACT_CODE";
				
				case 10 : return " INSERT INTO HRMS_APPRCONFIG_FACT VALUES(?, ?, ?) ";
				
				case 18 : return "SELECT HRMS_APPRCONFIG_FACT.APPRFACT_CODE, APPRFACT_NAME, "
						+" DECODE (APPRFACT_TYPE, 'P', 'Personality Traits', 'S', 'Self Appraisal', 'K', 'KRAs') TYPE, NVL(APPRFACT_WEIGHTAGE,0) "
						+" FROM HRMS_APPRCONFIG_FACT "
						+" LEFT JOIN HRMS_APPRFACT  ON (HRMS_APPRFACT.APPRFACT_CODE = HRMS_APPRCONFIG_FACT.APPRFACT_CODE) "
						//+"INNER JOIN HRMS_APPRFACT_DTL ON(HRMS_APPRFACT_DTL.APPRFACT_CODE = HRMS_APPRFACT.APPRFACT_CODE) "  
						+" WHERE DESG_ID = ? "
						//+"GROUP BY APPRFACT_TYPE,HRMS_APPRFACT_DTL.APPRFACT_CODE, APPRFACT_NAME "
						+" ORDER BY APPRFACT_TYPE ";
				
				default : return "";
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}