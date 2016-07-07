package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class QuestionnaireDefinitionModelSql extends SqlBase 
{
	public String getQuery(int id) 
	{
		switch (id) 
		{
				
				case 1 : return " SELECT APPR_QUES_CODE, APPR_QUES_DESC, APPR_QUES_TYPE, APPR_QUES_STATUS, APPR_QUES_MANADATORY, B.APPR_QUES_CATG_CODE, B.APPR_QUES_CATEG_NAME, APPR_ANSWER_CHAR_LIMIT " + 
							    " FROM PAS_APPR_QUESTIONNAIRE A, PAS_APPR_QUESTION_CATGORY B " +
							    " WHERE A.APPR_QUES_CATG_CODE = B.APPR_QUES_CATG_CODE " +
							    " ORDER BY APPR_QUES_CODE ";
				
				case 2 : return " INSERT INTO PAS_APPR_QUESTIONNAIRE (APPR_QUES_CODE, APPR_QUES_DESC, APPR_QUES_TYPE, APPR_QUES_STATUS, APPR_QUES_MANADATORY, APPR_QUES_CATG_CODE, APPR_ANSWER_CHAR_LIMIT)" +
							    " VALUES(?,?,?,?,?,?,?) ";
				
				case 3 : return " UPDATE PAS_APPR_QUESTIONNAIRE SET APPR_QUES_DESC = ?, APPR_QUES_TYPE = ?, APPR_QUES_STATUS = ?, " +
								" APPR_QUES_MANADATORY = ?, APPR_QUES_CATG_CODE = ?, APPR_ANSWER_CHAR_LIMIT = ? " +
				  			    " WHERE APPR_QUES_CODE = ? ";
				
				case 4 : return " DELETE FROM PAS_APPR_QUESTIONNAIRE WHERE APPR_QUES_CODE = ? ";
				
				case 5 : return " SELECT APPR_QUES_CODE, APPR_QUES_DESC, APPR_QUES_TYPE, APPR_QUES_STATUS, APPR_ANSWER_CHAR_LIMIT, APPR_QUES_MANADATORY, B.APPR_QUES_CATG_CODE, B.APPR_QUES_CATEG_NAME " + 
								" FROM PAS_APPR_QUESTIONNAIRE A, PAS_APPR_QUESTION_CATGORY B WHERE A.APPR_QUES_CATG_CODE = B.APPR_QUES_CATG_CODE AND APPR_QUES_CODE = ? "; 
				
				/* This query require for populate the question category  */
				case 6 : return " SELECT APPR_QUES_CATG_CODE, INITCAP(APPR_QUES_CATEG_NAME) " + 
							    " FROM PAS_APPR_QUESTION_CATGORY WHERE APPR_QUES_CATG_ISACTIVE = 'A' " +
							    " ORDER BY APPR_QUES_CATEG_NAME ASC ";
				
				case 7 : return " SELECT NVL(MAX(APPR_QUES_CODE),0)+1 FROM PAS_APPR_QUESTIONNAIRE ";
				
				default : return " Framework could not the query number specified";
			
		}
		
	}
	
}


