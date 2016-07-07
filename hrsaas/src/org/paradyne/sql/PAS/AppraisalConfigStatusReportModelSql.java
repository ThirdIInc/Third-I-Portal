package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class AppraisalConfigStatusReportModelSql extends SqlBase {

	public String getQuery(int id) 
	{
	switch (id) 
	{
			case 1 : return 
							" SELECT " +
							" 	APPR_CAL_CRT_DOJ_FLAG, " + 
							" 	APPR_CAL_CRT_ETYP_FLAG, " + 
							" 	APPR_CAL_CRT_GRD_FLAG, " + 
							" 	APPR_CAL_CRT_DEP_FLAG, " + 
							" 	APPR_CAL_CRT_DIV_FLAG " +
							" FROM " +
							" 	PAS_APPR_CALENDAR " +
							" WHERE " +
							"	APPR_ID =  ? ";
			
			case 2 : return " SELECT ROWNUM,APPR_PHASE_NAME,APPR_PHASE_ORDER,APPR_PHASE_WEIGHT_AGE,DECODE(APPR_PHASE_STATUS,'A','Active','D','De-Active') FROM PAS_APPR_PHASE_CONFIG"
							+" WHERE APPR_ID = ?  AND APPR_PHASE_STATUS = 'A' ORDER BY APPR_PHASE_ORDER";
			
							
			
			case 3 : return 	/* Date of Joining */
			
							" SELECT " + 
							" 	TO_CHAR(APPR_CRT_DOJ_FROM_DATE,'DD-MM-YYYY') AS FROM_DATE, " +
							"  	TO_CHAR(APPR_CRT_DOJ_TO_DATE,'DD-MM-YYYY') AS TO_DATE, " +
							"  	APPR_CRT_DOJ_CONDITION " + 
							" FROM " + 
							"   PAS_APPR_CAL_CRT_DOJ " + 
						    " WHERE " + 
							"   APPR_ID = ? ";
			
			case 4 : return    /* Employee Type */
			
							" SELECT " + 
							" 	TYPE_NAME " + 
							" FROM " +
							"	HRMS_EMP_TYPE " + 
							" WHERE " + 
							" 	TYPE_ID IN (SELECT APPR_CRT_EMP_TYPE FROM PAS_APPR_CAL_CRT_ETYPE WHERE APPR_ID = ?) ";
			
			case 5 : return    /* Grade */
			
							" SELECT " + 
							"	 CADRE_NAME " + 
							" FROM " + 
							" 	 HRMS_CADRE " + 
							" WHERE " + 
							" CADRE_ID IN (SELECT APPR_CRT_EMP_GRADE FROM PAS_APPR_CAL_CRT_GRD WHERE APPR_ID = ?) ";
			
			
			case 6 : return   /* Department */
			
							" SELECT " +  
							"	DEPT_NAME " +  
							" FROM " + 
							"	HRMS_DEPT " +  
							" WHERE " +  
							"	DEPT_ID IN (SELECT APPR_CRT_EMP_DEPT FROM PAS_APPR_CAL_CRT_DEPT WHERE APPR_ID = ?) "; 
			
			case 7 : return 	/* Division*/
			
							" SELECT " +  
							"	DIV_NAME " +
							" FROM " +
							"	HRMS_DIVISION " +
							" WHERE " +
							" 	DIV_ID IN (SELECT APPR_CRT_EMP_DIV FROM PAS_APPR_CAL_CRT_DIV WHERE APPR_ID = ?) "; 
			
			
			case 8 : return " SELECT ROWNUM,PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,"
							+" TO_CHAR(APPR_PHASE_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_PHASE_END_DATE,'DD-MM-YYYY'),DECODE(APPR_PHASE_LOCK,'Y','Yes','N','No') " 
							+" FROM PAS_APPR_PHASE_CONFIG "  
							+" LEFT JOIN PAS_APPR_PHASE_SCHEDULE ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_PHASE_SCHEDULE.APPR_PHASE_ID) " 
							+" WHERE PAS_APPR_PHASE_CONFIG.APPR_ID = ? "
							+" AND PAS_APPR_PHASE_CONFIG.APPR_PHASE_STATUS='A' " 
							+" ORDER BY  APPR_PHASE_ORDER ";
			
			case 9: return " SELECT ROWNUM,APPR_SCORE_FROM,APPR_SCORE_TO,APPR_SCORE_VALUE,APPR_SCORE_DESCRIPTION,APPR_BELL_PERCENTAGE FROM PAS_APPR_OVERALL_RATING"
							+" WHERE APPR_ID = ? ORDER BY APPR_SCORE_ID ";
			
			case 10: return " SELECT APPR_MIN_RATING,APPR_MAX_RATING,DECODE(APPR_ALLOW_FRACTION,'0','Yes','1','No'),"
							+" DECODE(APPR_RATING_TOLERANCE,'1','MIN','2','MAX','3','AVG') FROM PAS_APPR_QUESTION_RATING_HDR WHERE APPR_ID = ? ";
			
			case 11: return " SELECT ROWNUM,APPR_RATING_VALU,APPR_RATING_DESC FROM PAS_APPR_QUESTION_RATING_DTL WHERE APPR_ID = ? ORDER BY DTL_ID ";
			
			case 12 : return " SELECT ROWNUM,APPR_APPRAISER_GRP_NAME,APPR_PHASE_NAME,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),APPR_APPRAISER_LEVEL FROM PAS_APPR_APPRAISER "
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_APPRAISER.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID)"
							+" INNER JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID) "
							+" LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_APPRAISER.APPR_APPRAISER_CODE = HRMS_EMP_OFFC.EMP_ID) " 
							+" WHERE APPR_ID = ? ORDER BY APPR_APPRAISER_GRP_ID,APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL ";
			
			
			case 13 : return " SELECT ROWNUM,APPR_TEMPLATE_NAME FROM PAS_APPR_TEMPLATE WHERE APPR_ID = ?";
			
			case 14 : return "SELECT ROWNUM,APPR_RATING_VALU,APPR_RATING_DESC FROM PAS_APPR_QUESTION_RATING_DTL WHERE APPR_ID=?"
			  				+" ORDER BY APPR_RATING_VALU"; 
			
			default : return " Framework could not the query number specified ";
		}
	}
}
