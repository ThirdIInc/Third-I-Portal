package org.paradyne.sql.PAS;


import org.paradyne.lib.SqlBase;

public class ApprFormSectionModelSql extends SqlBase {

	
	public String getQuery(int i){
		
		switch(i){
		
		
		case 1 : return " SELECT DISTINCT PAS_APPR_SECTION_HDR.APPR_SECTION_ID, APPR_SECTION_ORDER,APPR_SECTION_VISIBILITY  FROM PAS_APPR_SECTION_HDR "
						+" LEFT JOIN PAS_APPR_SECTION_DTL ON (PAS_APPR_SECTION_HDR.APPR_SECTION_ID = PAS_APPR_SECTION_DTL.APPR_SECTION_ID) "
						+" WHERE PAS_APPR_SECTION_HDR.APPR_ID = ? AND PAS_APPR_SECTION_HDR.APPR_TEMPLATE_ID = ? AND APPR_PHASE_ID = ? "
						+" AND APPR_SECTION_VISIBILITY ='Y' "
						+" ORDER BY APPR_SECTION_ORDER  ";
		
		case 2: return " SELECT DISTINCT PAS_APPR_SECTION_DTL.APPR_SECTION_ID,NVL(APPR_SECTION_NAME,''),APPR_SECTION_VISIBILITY,"
						+" APPR_SECTION_RATING,APPR_SECTION_COMMENT "
						+" FROM PAS_APPR_SECTION_DTL " 
						+" LEFT JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_DTL.APPR_SECTION_ID = PAS_APPR_SECTION_HDR.APPR_SECTION_ID) " 
						+" WHERE PAS_APPR_SECTION_DTL.APPR_ID=  ?"  
						+" AND PAS_APPR_SECTION_DTL.APPR_TEMPLATE_ID= ?  AND PAS_APPR_SECTION_DTL.APPR_PHASE_ID = ?  AND PAS_APPR_SECTION_DTL.APPR_SECTION_ID = ? ";
						 

		
		case 3: return " SELECT DISTINCT APPR_QUESTION_ID,APPR_QUES_DESC,APPR_QUES_TYPE,APPR_QUESTION_WT,APPR_ANSWER_CHAR_LIMIT,APPR_QUES_MANADATORY,APPR_ANSWER_CHAR_LIMIT,APPR_QUESTION_ORDER FROM PAS_APPR_QUES_MAPPING	"
						+" LEFT JOIN PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE) "
						+" WHERE APPR_ID= ? AND APPR_TEMPLATE_ID= ? AND APPR_PHASE =  ? AND APPR_SECTION_ID= ?  and APPR_EMP_GRP_ID = ? "
						+" ORDER BY APPR_QUESTION_ORDER ";
		
		
		case 4 : return " INSERT INTO PAS_APPR_COMMENTS (APPR_ID,APPR_TEMPLATE_ID,APPR_SECTION_ID,APPR_EMP_ID,APPR_QUESTION_CODE,APPR_QUES_COMMENTS, "
						+" APPR_QUES_RATING,APPR_PHASE_ID,APPR_QUES_WEIGHTAGE,APPR_QUES_PERCENT_WT,APPR_EVALUATOR_CODE,APPR_EVALUATOR_LEVEL) "
						+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
		
		
		case 5: return	" SELECT  APPR_QUES_CODE,APPR_QUES_DESC,APPR_QUES_TYPE,APPR_QUESTION_WT,APPR_QUES_MANADATORY, "
						+" APPR_ANSWER_CHAR_LIMIT,APPR_QUES_RATING,NVL(APPR_QUES_COMMENTS,''),APPR_QUESTION_ORDER "  
						+" FROM PAS_APPR_QUESTIONNAIRE  INNER JOIN PAS_APPR_COMMENTS ON (PAS_APPR_COMMENTS.APPR_QUESTION_CODE = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE AND APPR_EMP_ID = ? "  
						+" AND PAS_APPR_COMMENTS.APPR_SECTION_ID = ? )	" 
						+" INNER JOIN PAS_APPR_QUES_MAPPING  ON (PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE  AND  "
						+" PAS_APPR_QUES_MAPPING.APPR_SECTION_ID = ? and APPR_PHASE = ? and APPR_EMP_GRP_ID = ?) WHERE PAS_APPR_QUES_MAPPING.APPR_ID= ? AND  "
						+" PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID = ?   AND  APPR_EVALUATOR_CODE = ? and APPR_PHASE_ID = ? ORDER BY APPR_QUESTION_ORDER ";

		
		/*" SELECT  APPR_QUES_CODE,APPR_QUES_DESC,APPR_QUES_TYPE,APPR_QUESTION_WT,APPR_QUES_MANADATORY,APPR_ANSWER_CHAR_LIMIT,APPR_QUES_RATING,NVL(APPR_QUES_COMMENTS,''),APPR_QUESTION_ORDER "
						+" FROM PAS_APPR_QUESTIONNAIRE " 
						+" INNER JOIN PAS_APPR_COMMENTS ON (PAS_APPR_COMMENTS.APPR_QUESTION_CODE = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE AND APPR_EMP_ID = ?  "
						+" AND PAS_APPR_COMMENTS.APPR_PHASE_ID = ? and PAS_APPR_COMMENTS.APPR_SECTION_ID = ? )	"   
						+" INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE " 
						+" AND PAS_APPR_QUES_MAPPING.APPR_SECTION_ID = ? )"
						+" WHERE PAS_APPR_QUES_MAPPING.APPR_ID= ? AND PAS_APPR_QUES_MAPPING.APPR_TEMPLATE_ID = ? AND    PAS_APPR_QUES_MAPPING.APPR_PHASE = ?  AND  APPR_EVALUATOR_CODE = ? ORDER BY APPR_QUESTION_ORDER ";
		*/
		
		case 6 : return " SELECT * FROM PAS_APPR_COMMENTS WHERE APPR_ID= ? AND APPR_TEMPLATE_ID= ? AND APPR_PHASE_ID =  ? AND APPR_SECTION_ID= ? AND APPR_EMP_ID = ? AND APPR_EVALUATOR_CODE = ? ";
		
		
		case 7 : return " DELETE FROM PAS_APPR_COMMENTS WHERE APPR_ID = ? AND APPR_TEMPLATE_ID = ? AND APPR_PHASE_ID = ? AND APPR_SECTION_ID = ? AND APPR_EMP_ID = ? and APPR_EVALUATOR_LEVEL = ? ";
		
		
		/*case 8 : return " SELECT APPR_QUES_DESC,NVL(APPR_QUES_RATING,''),NVL(APPR_QUES_COMMENTS,''),APPR_QUESTION_ORDER " 
						+" FROM PAS_APPR_COMMENTS " 
						+" INNER JOIN  PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_COMMENTS.APPR_QUESTION_CODE = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE ) "
						+" INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE) "
						+" WHERE  PAS_APPR_COMMENTS.APPR_ID= ? AND PAS_APPR_COMMENTS.APPR_TEMPLATE_ID= ? AND " 
						+" PAS_APPR_COMMENTS.APPR_PHASE_ID =  ? AND PAS_APPR_COMMENTS.APPR_SECTION_ID= ? AND APPR_EMP_ID = ? "
						+" ORDER BY APPR_QUESTION_ORDER "; 
		*/
		
		case 8 : return " SELECT PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME, APPR_QUES_DESC,NVL(APPR_QUES_RATING,''),NVL(APPR_QUES_COMMENTS,''),APPR_QUESTION_ORDER "
						+" FROM PAS_APPR_COMMENTS "  
						+" INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_COMMENTS.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) "
						+" INNER JOIN  PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_COMMENTS.APPR_QUESTION_CODE = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE ) " 
						+" INNER JOIN PAS_APPR_QUES_MAPPING ON (PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID = PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE) " 
						+" WHERE  PAS_APPR_COMMENTS.APPR_ID= ? AND PAS_APPR_COMMENTS.APPR_TEMPLATE_ID= ? AND   "
						+" PAS_APPR_COMMENTS.APPR_SECTION_ID= ? AND APPR_EMP_ID = ? AND PAS_APPR_COMMENTS.APPR_PHASE_ID IN (13,14) " 
						+" ORDER BY PAS_APPR_COMMENTS.APPR_PHASE_ID,APPR_QUESTION_ORDER ";
	
		
		case 9 : return " SELECT DISTINCT PAS_APPR_PHASE_CONFIG.APPR_ID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,"
						 +" APPR_PHASE_ORDER FROM PAS_APPR_COMMENTS "
						 +" INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_COMMENTS.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) "
						 +" LEFT JOIN PAS_APPR_SECTION_DTL on (PAS_APPR_COMMENTS.APPR_PHASE_ID = PAS_APPR_SECTION_DTL.APPR_PHASE_ID)  "
						 +" WHERE PAS_APPR_COMMENTS.APPR_ID = ? AND APPR_PHASE_STATUS ='A' AND APPR_SECTION_VISIBILITY = 'Y'  "
						 +" AND PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID != ?  ORDER BY APPR_PHASE_ORDER  ";
		
		case 10: return " SELECT APPR_PHASE_ORDER FROM PAS_APPR_PHASE_CONFIG WHERE APPR_PHASE_ORDER > (SELECT APPR_PHASE_ORDER FROM PAS_APPR_PHASE_CONFIG  "
						+" WHERE APPR_PHASE_ID = ? AND APPR_ID = ? AND APPR_PHASE_STATUS='A' ) " 
						+" AND APPR_ID =  ? AND APPR_PHASE_STATUS='A' "
						+" ORDER BY APPR_PHASE_ORDER "; 
		/*case 10: return " SELECT APPR_PHASE_ORDER FROM PAS_APPR_PHASE_CONFIG WHERE APPR_PHASE_ORDER >= 3"
						+"AND APPR_ID =  ? AND APPR_PHASE_STATUS='A'"
						+"ORDER BY APPR_PHASE_ORDER ";*/

		case 11: return " SELECT APPR_PHASE_ID FROM PAS_APPR_PHASE_CONFIG WHERE APPR_PHASE_ORDER = ? AND APPR_ID = ? AND APPR_PHASE_STATUS='A' ";
		
		
		case 12 : return " SELECT PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID FROM PAS_APPR_APPRAISER_GRP_DTL " 
						+" LEFT JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID ) "
						+" WHERE APPR_APPRAISEE_ID = ? AND APPR_ID = ? "; 
		
			
		case 13 : return " SELECT APPR_APPRAISER_CODE,APPR_APPRAISER_LEVEL FROM PAS_APPR_APPRAISER "
						+" WHERE APPR_APPRAISER_GRP_ID = ? AND APPR_ID = ? AND APPR_PHASE_ID = ? " 
						+" ORDER BY  APPR_APPRAISER_LEVEL ";
		
		case 14 : return " INSERT INTO PAS_APPR_TRACKING(APPR_ID,TEMPLATE_CODE,EMP_ID,PHASE_ID,PHASE_FORWARD,APPRAISAL_STATUS,"
						+" NEXT_APPRAISER,NEXT_PHASE_ID,NEXT_PHASE_FORWARD,PHASE_QUES_TOTAL_RATING,PHASE_QUES_TOTAL_WT,PHASE_PERCENTAGE,PHASE_WEIGHTAGE,PHASE_FINAL_SCORE,PHASE_INSERTED_EMPLOYEE,PHASE_APPRAISER_LEVEL,PHASE_INSERTED_DATE) VALUES(?,?,?,?,'Y','N',?,?,'N',NVL(?,'0'),NVL(?,'0'),NVL(?,'0'),NVL(?,'0'),NVL(?,'0'),?,?,SYSDATE)";
		
		
		case 15 : return " UPDATE PAS_APPR_TRACKING SET NEXT_PHASE_FORWARD = 'Y' WHERE APPR_ID =? AND TEMPLATE_CODE =? AND EMP_ID = ? AND NEXT_PHASE_ID = ? AND NEXT_APPRAISER = ? ";
		
		
		/*case 16 : return " UPDATE PAS_APPR_TRACKING SET NEXT_PHASE_FORWARD = 'Y' ,APPRAISAL_STATUS ='Y' ,PHASE_ISCOMPLETE = 'Y' " 
					+" WHERE APPR_ID =? AND TEMPLATE_CODE = ? AND EMP_ID = ? ";*/
		
		case 16 : return " UPDATE PAS_APPR_TRACKING SET NEXT_PHASE_FORWARD = 'N' ,APPRAISAL_STATUS ='N' ,PHASE_ISCOMPLETE = 'N' " 
						+" WHERE APPR_ID =? AND TEMPLATE_CODE = ? AND EMP_ID = ?" ;
		
				
		case 17 : return " SELECT NVL(SUM(APPR_QUES_PERCENT_WT),'0'),NVL(SUM(APPR_QUES_WEIGHTAGE),'0') FROM PAS_APPR_COMMENTS WHERE APPR_ID = ? AND APPR_TEMPLATE_ID = ?  "
						+" AND APPR_EMP_ID = ?  AND APPR_PHASE_ID = ? AND APPR_EVALUATOR_LEVEL = ? AND APPR_QUES_WEIGHTAGE != 0  ";
		
		case 18 : return " SELECT APPR_PHASEWISE_RATING,APPR_PHASE_WEIGHT_AGE FROM PAS_APPR_PHASE_CONFIG "
						+" WHERE APPR_ID = ? AND APPR_PHASE_ID = ? ";
		
		
		case 20 : return " SELECT APPR_RATING_TOLERANCE from PAS_APPR_QUESTION_RATING_HDR where APPR_ID = ? ";
		
		
		case 21 : return " SELECT SUM(MIN(PHASE_FINAL_SCORE)),SUM(MIN(PHASE_QUES_TOTAL_WT)) FROM PAS_APPR_TRACKING WHERE APPR_ID = ? and EMP_ID = ? and TEMPLATE_CODE = ? "
						+" GROUP BY PHASE_ID ORDER BY PHASE_ID ";
		
		
		case 22 : return " SELECT SUM(MAX(PHASE_FINAL_SCORE)),SUM(MAX(PHASE_QUES_TOTAL_WT)) FROM PAS_APPR_TRACKING WHERE APPR_ID = ? and EMP_ID = ? and TEMPLATE_CODE = ? "
						+" GROUP BY PHASE_ID ORDER BY PHASE_ID ";
		
		case 23 : return " SELECT SUM(AVG(PHASE_FINAL_SCORE)),SUM(MAX(PHASE_QUES_TOTAL_WT)) FROM PAS_APPR_TRACKING WHERE APPR_ID = ? and EMP_ID = ? and TEMPLATE_CODE = ? "
						+" GROUP BY PHASE_ID ORDER BY PHASE_ID ";
		
		case 24 : return " INSERT INTO PAS_APPR_SCORE (APPR_ID,TEMPLATE_CODE,EMP_ID,APPR_SCORE,APPR_FINAL_SCORE,APPR_FINAL_SCORE_VALUE,APPR_FINAL_SCORE_DESC) VALUES(?,?,?,?,?,?,?) ";
		//---------------
		
		case 25 : return " SELECT APPR_SCORE_FROM ,APPR_SCORE_VALUE,APPR_SCORE_DESCRIPTION FROM "
							+" PAS_APPR_OVERALL_RATING "
							+" WHERE APPR_ID = ? ORDER BY APPR_SCORE_FROM DESC ";
		
		case 19 : return " SELECT NVL(APPR_APPRAISER_LEVEL,1) FROM PAS_APPR_APPRAISER "
						+" LEFT JOIN PAS_APPR_APPRAISER_GRP_DTL on (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID =PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) " 
						+" LEFT JOIN PAS_APPR_APPRAISER_GRP_HDR on (PAS_APPR_APPRAISER_GRP_HDR.APPR_ID = PAS_APPR_APPRAISER.APPR_ID) "
						+" WHERE PAS_APPR_APPRAISER.APPR_ID = ? and PAS_APPR_APPRAISER.APPR_APPRAISER_CODE = ? and APPR_PHASE_ID = ? AND APPR_APPRAISEE_ID = ? ";

		//--------------emp group for ques mapping
		case 26 : return " SELECT PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER  FROM PAS_APPR_EMP_GRP_DTL"
						+" INNER JOIN PAS_APPR_EMP_GRP_HDR ON (PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID = PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID) "
						+" LEFT JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_ID=PAS_APPR_EMP_GRP_HDR.APPR_ID)"
						+" WHERE APPR_EMP_GRP_EMPID = ? and PAS_APPR_EMP_GRP_HDR.APPR_ID=? "; 
		
		
		case 27 : return " SELECT APPR_RATING_VALU,APPR_RATING_DESC from PAS_APPR_QUESTION_RATING_DTL WHERE APPR_ID = ? ";
		
		
		case 28 : return " SELECT APPR_SECTION_RATING FROM  PAS_APPR_SECTION_DTL WHERE APPR_ID = ? AND APPR_TEMPLATE_ID	= ? AND APPR_SECTION_ID	= ? AND APPR_PHASE_ID = ? ";
		
		case 29 : return " SELECT APPR_ALLOW_FRACTION,APPR_RATING_TYPE FROM PAS_APPR_QUESTION_RATING_HDR WHERE APPR_ID = ? ";
		
		
		default: return "";
		}
	}
	
}
