package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;


public class TestTemplateModelSql extends SqlBase{
	
	public String getQuery(int id){
		switch (id) {
		case 1 : return "SELECT TEMPLATE_TEST_NAME, TO_CHAR(TEMPLATE_DURATION, 'HH24:MI'), DECODE(TEMPLATE_TEST_TYPE, "
						+"'O', 'Objective', 'S', 'Subjective', 'B', 'Both'), TEMPLATE_TOTAL_QUES, "
						+"TEMPLATE_TOTAL_MARKS, TEMPLATE_PASSING_MARKS,TEMPLATE_CODE "
						+"FROM HRMS_REC_TEST_TEMPLATE "
						+"ORDER BY UPPER(TEMPLATE_TEST_NAME)";
		
		case 2 : return "SELECT  SUBJECT_CODE,SUBJECT_NAME FROM HRMS_REC_SUBJECT "					   
					    +"ORDER BY SUBJECT_NAME";
		
		case 3 : return "SELECT CAT_CODE,CAT_NAME FROM HRMS_REC_CATEGORY "		   				
		   				+"ORDER BY CAT_NAME";
		
		case 4 : return "  INSERT INTO HRMS_REC_TEST_TEMPLATE (TEMPLATE_CODE, TEMPLATE_TEST_NAME, TEMPLATE_DURATION, "
						+" TEMPLATE_TEST_TYPE, TEMPLATE_ONLINE_SCORE, TEMPLATE_TOTAL_MARKS, TEMPLATE_TOTAL_QUES, "
						+" TEMPLATE_EQUAL_WEGHT, TEMPLATE_MARKS_CORERCT, TEMPLATE_MARKS_HARD, TEMPLATE_MARKS_MEDIUM, "
						+" TEMPLATE_MARKS_EASY, TEMPLATE_MARKS_WRONG,TEMPLATE_WRONG_HARD,TEMPLATE_WRONG_MED,TEMPLATE_WRONG_EASY,TEMPLATE_MARKS_NOANS, "
						+" TEMPLATE_PASSING_MARKS, TEMPLATE_INSTRUCTION,TEMPLATE_EASY_QUECOUNT,TEMPLATE_MEDIUM_QUECOUNT," +
						 " TEMPLATE_HARD_QUECOUNT,TEMPLATE_TOTAL_QUECOUNT,TEMPLATE_EASY_MARKS_COUNT," +
						 " TEMPLATE_MEDIUM_MARKS_COUNT,TEMPLATE_HARD_MARKS_COUNT,TEMPLATE_TOTAL_MARKS_COUNT," +
						 " TEMPLATE_TYPE,TEMPLATE_EQUAL_MARKS) "
						+" VALUES ((SELECT NVL(MAX(TEMPLATE_CODE),0)+1 FROM HRMS_REC_TEST_TEMPLATE), "
						+" ?, TO_DATE(?, 'HH24:MI:SS'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		case 5 : return "INSERT INTO HRMS_REC_TESTTEMP_QUES (TEMPLATE_DTL_CODE, TEMPLATE_CODE, TEMPLATE_QUES_CODE, "
						+"TEMPLATE_SUB_CODE, TEMPLATE_CAT_CODE, TEMPLATE_COMPLEXICITY) "
						+"VALUES ((SELECT NVL(MAX(TEMPLATE_DTL_CODE),0)+1 FROM HRMS_REC_TESTTEMP_QUES), "
						+"?, ?, ?, ?, ?)";
		
		case 6 : return "INSERT INTO HRMS_REC_TESTTEMPDTL (TEMPLATE_DTLCODE, TEMPLATE_CODE, TEMPLATE_SUBCODE, "
						+"TEMPLATE_CATCODE, TEMPLATE_NOOFQUES, TEMPLATE_COM_LEVEL) "
						+"VALUES((SELECT NVL(MAX(TEMPLATE_DTLCODE),0)+1 FROM HRMS_REC_TESTTEMPDTL), "
						+"?, ?, ?, ?, ?)";
		
		case 7 : return " UPDATE HRMS_REC_TEST_TEMPLATE SET TEMPLATE_TEST_NAME = ?, TEMPLATE_DURATION = TO_DATE(?, 'HH24:MI:SS'), "
						+" TEMPLATE_TEST_TYPE = ?, TEMPLATE_ONLINE_SCORE = ?, TEMPLATE_TOTAL_MARKS = ?, "
						+" TEMPLATE_TOTAL_QUES = ?, TEMPLATE_EQUAL_WEGHT = ?, TEMPLATE_MARKS_CORERCT = ?, "
						+" TEMPLATE_MARKS_HARD = ?, TEMPLATE_MARKS_MEDIUM = ?, TEMPLATE_MARKS_EASY = ?," +
						 " TEMPLATE_MARKS_WRONG = ?,TEMPLATE_WRONG_HARD = ?,TEMPLATE_WRONG_MED = ?," +
						 " TEMPLATE_WRONG_EASY = ?, "
						+" TEMPLATE_MARKS_NOANS = ?, TEMPLATE_PASSING_MARKS = ?, TEMPLATE_INSTRUCTION = ? ," +
						 " TEMPLATE_EASY_QUECOUNT = ?,TEMPLATE_MEDIUM_QUECOUNT = ?," +
						 " TEMPLATE_HARD_QUECOUNT = ?,TEMPLATE_TOTAL_QUECOUNT = ?," +
						 " TEMPLATE_EASY_MARKS_COUNT = ?,TEMPLATE_MEDIUM_MARKS_COUNT = ? ," +
						 " TEMPLATE_HARD_MARKS_COUNT =?,TEMPLATE_TOTAL_MARKS_COUNT=?,TEMPLATE_TYPE=?,TEMPLATE_EQUAL_MARKS=? "
						+" WHERE TEMPLATE_CODE = ?";
		
		case 8 : return "DELETE FROM HRMS_REC_TESTTEMP_QUES WHERE TEMPLATE_CODE = ?";
		
		case 9 : return "DELETE FROM HRMS_REC_TESTTEMPDTL WHERE TEMPLATE_CODE = ?";
		
		case 10 : return "DELETE FROM HRMS_REC_TEST_TEMPLATE WHERE TEMPLATE_CODE = ?";
		
		case 11 : return " SELECT TEMPLATE_ONLINE_SCORE, TEMPLATE_EQUAL_WEGHT, TEMPLATE_MARKS_CORERCT, TEMPLATE_MARKS_WRONG, "
						 +" TEMPLATE_MARKS_NOANS, TEMPLATE_INSTRUCTION, NVL(TEMPLATE_MARKS_HARD,0), NVL(TEMPLATE_MARKS_MEDIUM,0), "
						 +" NVL(TEMPLATE_MARKS_EASY,0),NVL(TEMPLATE_WRONG_HARD,0),NVL(TEMPLATE_WRONG_MED,0),NVL(TEMPLATE_WRONG_EASY,0)," +
						  " TEMPLATE_EASY_QUECOUNT, TEMPLATE_MEDIUM_QUECOUNT, TEMPLATE_HARD_QUECOUNT," +
						  " TEMPLATE_TOTAL_QUECOUNT, TEMPLATE_EASY_MARKS_COUNT," +
						  " TEMPLATE_MEDIUM_MARKS_COUNT, TEMPLATE_HARD_MARKS_COUNT, " +
						  " TEMPLATE_TOTAL_MARKS_COUNT,TEMPLATE_TYPE,NVL(TEMPLATE_EQUAL_MARKS,0) "
						 +" FROM HRMS_REC_TEST_TEMPLATE "
						 +" WHERE TEMPLATE_CODE = ?";
		
		case 12 : return "SELECT TEMPLATE_QUES_CODE, TEMPLATE_SUB_CODE, TEMPLATE_CAT_CODE, TEMPLATE_COMPLEXICITY "
						 +"FROM HRMS_REC_TESTTEMP_QUES "
						 +"WHERE TEMPLATE_CODE = ?";
		
		case 13 : return "SELECT SUBJECT_NAME, CAT_NAME, TEMPLATE_SUBCODE, TEMPLATE_CATCODE, TEMPLATE_NOOFQUES, "
						+"DECODE(TEMPLATE_COM_LEVEL, 'H', 'Hard', 'M', 'Medium', 'E', 'Easy') "
						+"FROM HRMS_REC_TESTTEMPDTL "
						+"LEFT JOIN HRMS_REC_SUBJECT ON(HRMS_REC_SUBJECT.SUBJECT_CODE = TEMPLATE_SUBCODE) " 
						+"LEFT JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE = TEMPLATE_CATCODE) " 
						+"WHERE TEMPLATE_CODE = ?";
		
		case 14 : return "SELECT TEMPLATE_TEST_NAME,TO_CHAR(TEMPLATE_DURATION,'HH24:MI'), DECODE(TEMPLATE_TEST_TYPE,'O', 'Objective', 'S', 'Subjective', 'B', 'Both')," +
						" TEMPLATE_ONLINE_SCORE,NVL(TEMPLATE_TOTAL_MARKS,0),NVL(TEMPLATE_TOTAL_QUES,0),NVL(TEMPLATE_MARKS_NOANS,0)," +
						" NVL(TEMPLATE_PASSING_MARKS,0),TEMPLATE_INSTRUCTION,TEMPLATE_MARKS_CORERCT,NVL(TEMPLATE_MARKS_HARD,0)," +
						" NVL(TEMPLATE_MARKS_MEDIUM,0),NVL(TEMPLATE_MARKS_EASY,0),TEMPLATE_EQUAL_WEGHT,TEMPLATE_MARKS_WRONG," +
						" NVL(TEMPLATE_WRONG_HARD,0),NVL(TEMPLATE_WRONG_MED,0),NVL(TEMPLATE_WRONG_EASY,0),TEMPLATE_EASY_QUECOUNT, " +
						" TEMPLATE_MEDIUM_QUECOUNT, TEMPLATE_HARD_QUECOUNT, TEMPLATE_TOTAL_QUECOUNT, " +
						" TEMPLATE_EASY_MARKS_COUNT, TEMPLATE_MEDIUM_MARKS_COUNT, TEMPLATE_HARD_MARKS_COUNT," +
						" TEMPLATE_TOTAL_MARKS_COUNT,TEMPLATE_TYPE,NVL(TEMPLATE_EQUAL_MARKS,0) FROM HRMS_REC_TEST_TEMPLATE"
					   +" WHERE TEMPLATE_CODE = ?";
		
		case 15: return "SELECT TEMPLATE_QUES_CODE FROM  HRMS_REC_TESTTEMP_QUES WHERE TEMPLATE_CODE =?";
		
		case 16 : return "SELECT CAT_CODE,CAT_NAME FROM HRMS_REC_CATEGORY WHERE CAT_SUB_CODE IN (?) "		   				
			+"ORDER BY CAT_NAME";
		
		default: return "";
		}		
	}	
}
