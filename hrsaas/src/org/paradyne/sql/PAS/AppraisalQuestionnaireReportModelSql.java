package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class AppraisalQuestionnaireReportModelSql extends SqlBase 
{
	public String getQuery(int id) 
	{
		switch (id) 
		{
			case 1 : return 
						" SELECT " +
						"	APPR_QUES_CATG_CODE, " +
						"	INITCAP(APPR_QUES_CATEG_NAME) " + 
						" FROM PAS_APPR_QUESTION_CATGORY " +
						" 	ORDER BY APPR_QUES_CATEG_NAME ";
			
			case 2 : return 
						" SELECT " + 
						" 	ROWNUM, " + 
						" 	APPR_QUES_DESC, " + 
						" 	CASE APPR_QUES_TYPE WHEN 'D' THEN 'Descriptive' WHEN 'O' THEN 'Objective' END as TYPE, " +
						" 	CASE APPR_QUES_STATUS WHEN 'A' THEN 'Active' WHEN 'D' THEN 'De-Active' END as TYPE, " + 
						" 	CASE APPR_QUES_MANADATORY WHEN 'Y' THEN 'Yes' WHEN 'N' THEN 'No' END as TYPE, " +  
						" 	B.APPR_QUES_CATEG_NAME " + 
						" FROM " + 
						" 	PAS_APPR_QUESTIONNAIRE A INNER JOIN PAS_APPR_QUESTION_CATGORY B ON A.APPR_QUES_CATG_CODE = B.APPR_QUES_CATG_CODE " +
						" ORDER BY " +
						"	ROWNUM,B.APPR_QUES_CATG_CODE ";
			
			case 3 : return
			
						" SELECT " +
						"	APPR_QUES_CATEG_NAME " +
						" FROM " +
						"	PAS_APPR_QUESTION_CATGORY " +
						" WHERE " +
						"	APPR_QUES_CATG_CODE in(?) ";
			
			default : return " Framework could not the query number specified ";
		}
	}
}
