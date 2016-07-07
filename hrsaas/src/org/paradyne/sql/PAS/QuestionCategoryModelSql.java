package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class QuestionCategoryModelSql extends SqlBase {
	
	
	public String getQuery(int id) 
	{
		switch (id) {
				
				case 1 : return " SELECT APPR_QUES_CATG_CODE, APPR_QUES_CATEG_NAME, APPR_QUES_CATG_ISACTIVE, APPR_QUES_CATG_DESC " + 
							    " FROM PAS_APPR_QUESTION_CATGORY " +
							    " ORDER BY APPR_QUES_CATG_CODE ";
				
				case 2 : return " INSERT INTO PAS_APPR_QUESTION_CATGORY (APPR_QUES_CATG_CODE, APPR_QUES_CATEG_NAME, APPR_QUES_CATG_ISACTIVE, APPR_QUES_CATG_DESC)" +
							    " VALUES(?,?,?,?)";
				
				case 3 : return " UPDATE PAS_APPR_QUESTION_CATGORY SET APPR_QUES_CATEG_NAME = ?, APPR_QUES_CATG_ISACTIVE = ?, APPR_QUES_CATG_DESC = ?" +
				  			    " WHERE APPR_QUES_CATG_CODE = ? ";
				
				case 4 : return " DELETE FROM PAS_APPR_QUESTION_CATGORY WHERE APPR_QUES_CATG_CODE = ? ";
				
				case 5 : return " SELECT APPR_QUES_CATG_CODE, APPR_QUES_CATEG_NAME, APPR_QUES_CATG_ISACTIVE, APPR_QUES_CATG_DESC " + 
								" FROM PAS_APPR_QUESTION_CATGORY WHERE APPR_QUES_CATG_CODE = ? "; 
				
				case 6 : return " SELECT NVL(MAX(APPR_QUES_CATG_CODE),0)+1 FROM PAS_APPR_QUESTION_CATGORY ";
				
				default : return " Framework could not the query number specified";
			
		}
		
	}

}
