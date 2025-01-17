package org.paradyne.sql.PAS;
import org.paradyne.lib.SqlBase;

public class CareerProgressionModelSql extends SqlBase{

	public String getQuery(int id){
		
		
		switch (id) {
				
				case 1 : return " SELECT APPR_CAL_CODE, APPR_CAL_START_DATE, APPR_CAL_END_DATE,APPR_ID FROM PAS_APPR_CALENDAR WHERE APPR_ID=?";
				
				case 2 : return " SELECT APPR_TEMPLATE_NAME,APPR_TEMPLATE_ID,APPR_ID FROM PAS_APPR_TEMPLATE WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
				
				case 3: return " SELECT APPR_PHASE_NAME, PAS_APPR_PHASE_CONFIG.APPR_ID, APPR_PHASE_ID FROM PAS_APPR_PHASE_CONFIG "
							  +" INNER JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_PHASE_CONFIG.APPR_ID)"
							  +" WHERE PAS_APPR_CALENDAR.APPR_ID=? AND APPR_PHASE_STATUS='A'";
				
				case 4 : return " UPDATE PAS_APPR_TEMPLATE SET APPR_CAREER_FLAG=? WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
				
				case 5 : return " INSERT INTO PAS_APPR_COMMON_SECTION(APPR_ID, APPR_TEMPLATE_ID, APPR_PHASE, APPR_SECTION_TYPE, APPR_COMMONSECTION_ID, APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT) VALUES(?,?,?,'C',(SELECT NVL(MAX(APPR_COMMONSECTION_ID),0)+1 FROM PAS_APPR_COMMON_SECTION ),?,?)";
				
				case 6 : return " INSERT INTO PAS_APPR_CAREER(APPR_ID, APPR_TEMPLATE_ID, APPR_QUESTION_CODE, APPR_CAREER_ID)VALUES(?,?,?,(SELECT NVL(MAX(APPR_CAREER_ID),0)+1 FROM PAS_APPR_CAREER ))";
				
				case 7 : return " SELECT PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID,NVL(APPR_PHASE_NAME,' '),NVL(APPR_SECTION_TYPE,' '), APPR_COMMONSECTION_ID, NVL(APPR_PHASE_VISIBILITY,' '), NVL(APPR_PHASE_COMMENT,' '),PAS_APPR_COMMON_SECTION.APPR_ID "
							+" FROM PAS_APPR_PHASE_CONFIG" 
							+" INNER JOIN  PAS_APPR_COMMON_SECTION ON(PAS_APPR_PHASE_CONFIG.APPR_ID = PAS_APPR_COMMON_SECTION.APPR_ID" 
							+" AND PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_COMMON_SECTION.APPR_PHASE AND PAS_APPR_COMMON_SECTION.APPR_TEMPLATE_ID=? AND APPR_SECTION_TYPE='C')" 
							+" WHERE PAS_APPR_PHASE_CONFIG .APPR_ID=? AND APPR_PHASE_STATUS='A'";
				
				case 8 : return " SELECT APPR_CAREER_FLAG FROM PAS_APPR_TEMPLATE WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
				
				case 9 : return " SELECT APPR_ID, APPR_TEMPLATE_ID, APPR_CAREER_ID,APPR_QUESTION_CODE,NVL(APPR_QUES_DESC,' ') FROM PAS_APPR_CAREER"
							   +" INNER JOIN PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_CAREER.APPR_QUESTION_CODE )"
							   +" WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
				
				case 10 : return " UPDATE PAS_APPR_COMMON_SECTION SET APPR_PHASE_VISIBILITY=?, APPR_PHASE_COMMENT=? WHERE APPR_ID=? AND APPR_TEMPLATE_ID=? AND APPR_PHASE=? AND APPR_SECTION_TYPE=? AND APPR_COMMONSECTION_ID=?";
				
				case 11 :  return " DELETE FROM PAS_APPR_CAREER WHERE APPR_ID=? AND APPR_TEMPLATE_ID=? AND APPR_CAREER_ID IN (?)";
				
				//Allow Comments queries
				
				case 12 : return " INSERT INTO PAS_GENERAL_COMMENT_HDR(APPR_ID,APPR_TEMPLATE_ID,APPR_COMMENT_FLAG,APPR_COLUMN_NOS,APPR_GENERAL_ID) "
									+ " VALUES(?,?,?,?, (SELECT NVL(MAX(APPR_GENERAL_ID),0)+1 FROM PAS_GENERAL_COMMENT_HDR) ) ";
				
				case 13 : return " INSERT INTO PAS_GENERAL_COMMENT_DTL(APPR_GENERAL_ID,X_POSITION,Y_POSITION,LABEL_NAME) "
									+ " VALUES(?,?,?,?) ";
				
				case 14 : return " UPDATE PAS_GENERAL_COMMENT_HDR SET APPR_COMMENT_FLAG=?,APPR_COLUMN_NOS=? WHERE APPR_ID=? AND APPR_TEMPLATE_ID=? ";
				
				case 15 : return " SELECT APPR_COMMENT_FLAG,APPR_COLUMN_NOS,APPR_GENERAL_ID FROM PAS_GENERAL_COMMENT_HDR " 
									+ " WHERE APPR_ID=? AND APPR_TEMPLATE_ID=? ";
				
				case 16 : return " SELECT LABEL_NAME FROM PAS_GENERAL_COMMENT_DTL WHERE APPR_GENERAL_ID=? " 
									+ " ORDER BY X_POSITION,Y_POSITION ";
				
				case 17 : return " DELETE FROM PAS_GENERAL_COMMENT_DTL WHERE APPR_GENERAL_ID = ? ";
				
				//case 11 : return " UPDATE PAS_APPR_TRN_RECOMMEND SET ";
				
				//case 4: return " SELECT ";
				
					
				
				default: return "hi";
			
		}
		



	}
	
}
