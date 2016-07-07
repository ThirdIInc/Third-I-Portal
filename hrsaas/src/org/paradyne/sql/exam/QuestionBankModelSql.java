package org.paradyne.sql.exam;

import org.paradyne.lib.SqlBase;
/**
 * 
 * @author Pradeep Kumar Sahoo
 *  *
 */
public class QuestionBankModelSql extends SqlBase {
public String  getQuery(int code){
		
		switch(code){
		case 1: return "INSERT INTO HRMS_REC_QUESBANK (QUES_CODE,QUES_SUB_CODE,QUES_TYPE ,QUES_LIMIT,QUES_MARK,QUES_LEVEL,QUES_NAME,QUES_STATUS,QUES_CAT_CODE, QUES_UPLOADED_DOC, QUES_UPLOAD_ANSWER_FLAG)"
		+" VALUES((SELECT NVL(MAX(QUES_CODE),0)+1 FROM HRMS_REC_QUESBANK),?,?,?,?,?,?,?,?,?,?) ";
		
		case 2:return " DELETE FROM HRMS_REC_QUESOPTION WHERE QUES_CODE=?";
		
		case 3:return " DELETE FROM HRMS_REC_QUESBANK WHERE QUES_CODE=?";
	
		case 4:return "INSERT INTO HRMS_REC_QUESOPTION(OPTION_CODE,QUES_CODE,OPTION_DESC,OPTION_ANS_FLAG) "
					+" VALUES ((SELECT NVL(MAX(OPTION_CODE),0)+1 FROM HRMS_REC_QUESOPTION),?,?,?)";
		
		case 5:return "UPDATE HRMS_REC_QUESOPTION SET OPTION_ANS_FLAG=?,OPTION_DESC=? WHERE OPTION_CODE=? AND QUES_CODE=?";
		
		case 6:return " UPDATE HRMS_REC_QUESBANK set QUES_SUB_CODE =?,QUES_TYPE=?,QUES_LIMIT=?,QUES_MARK=?,QUES_LEVEL=?,QUES_NAME=?,QUES_STATUS=? ,QUES_CAT_CODE=?, QUES_UPLOADED_DOC=?, QUES_UPLOAD_ANSWER_FLAG=? WHERE QUES_CODE=? ";
		default : return " HI";
		}
}
		
}
