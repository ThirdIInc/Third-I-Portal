/**
 * 
 */
package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

/**
 * @author prakashs
 *
 */
public class QuestionnaireMasterModelSql extends SqlBase {
	public String getQuery(int code)
	{
		switch( code)
		{
		case 1 : return " INSERT INTO HRMS_QUES (QUES_CODE,QUES_NAME,QUES_TYPE) VALUES (? , ? , ?)";
		
		case 2 : return " INSERT INTO HRMS_QUESDTL (QUES_CODE,QUESDTL_NAME,QUESDTL_VALUE,QUESDTL_CODE) VALUES ( ? ,? ,?,? )";
		
		case 3 : return " DELETE FROM HRMS_QUESDTL WHERE QUES_CODE= ? ";
		
		case 4 : return " DELETE FROM HRMS_QUES WHERE QUES_CODE= ? ";
				
		case 5: return " SELECT QUESDTL_NAME, QUESDTL_VALUE FROM HRMS_QUESDTL WHERE QUES_CODE=? AND QUESDTL_CODE=?";
		
		case 6: return "SELECT HRMS_QUES.QUES_CODE,QUES_NAME,QUES_TYPE,QUESDTL_CODE,QUESDTL_NAME,QUESDTL_VALUE FROM HRMS_QUES "
				+" INNER JOIN HRMS_QUESDTL ON(HRMS_QUES.QUES_CODE=HRMS_QUESDTL.QUES_CODE) WHERE HRMS_QUES.QUES_CODE=?";
		
		case 7: return "UPDATE HRMS_QUESDTL SET QUESDTL_NAME=?, QUESDTL_VALUE=? WHERE QUES_CODE=? AND QUESDTL_CODE=? ";
		
		
		case 8: return "DELETE FROM HRMS_QUESDTL WHERE QUES_CODE=? AND QUESDTL_CODE=? "; 
	
		case 9: return "UPDATE HRMS_QUES SET QUES_NAME=? ,QUES_TYPE=? WHERE QUES_CODE=?";
		
		case 10: return " SELECT  QUES_CODE , QUES_NAME , DECODE(QUES_TYPE,'T','Training','F','Training Feedback','E','Exit Interview','C','Candidate Evaluation','I','Induction Feedback') FROM HRMS_QUES " 
			+" ORDER BY upper(QUES_NAME )";		
		
		default : return "HIIIIIIIIIIIIII";
		}
	}

}
