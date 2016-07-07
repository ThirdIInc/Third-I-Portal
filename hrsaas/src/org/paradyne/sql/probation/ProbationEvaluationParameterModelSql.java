package org.paradyne.sql.probation;

import org.paradyne.lib.SqlBase;

public class ProbationEvaluationParameterModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_PROBATION_EVALUATION(PROB_EVAL_CODE, PROB_EVAL_QUES_NAME) "
					+ " VALUES(?,?)";
		
		case 2:
			return " INSERT INTO HRMS_PROBATION_EVALUATION_DTL(PROBATION_INCR_CODE, QUESTION_ATTRIBUTE, ATTRIBUTE_VALUE,PROB_EVAL_ID) "
					+ " VALUES(?,?,?,?)";
		
		case 3:
			return " UPDATE HRMS_PROBATION_EVALUATION SET PROB_EVAL_QUES_NAME=? WHERE PROB_EVAL_CODE=?  ";
			
		case 4:
			return " UPDATE HRMS_PROBATION_EVALUATION_DTL SET QUESTION_ATTRIBUTE=?,ATTRIBUTE_VALUE=?,PROB_EVAL_ID=? WHERE PROBATION_INCR_CODE=?  ";
		
		case 5:
			return " SELECT PROB_EVAL_CODE, PROB_EVAL_QUES_NAME FROM HRMS_PROBATION_EVALUATION ";
		
		
		case 6:
			return " DELETE from HRMS_PROBATION_EVALUATION WHERE PROB_EVAL_CODE=?  ";
			
			
			
		default:
			return "Framework could not the query number specified";

		}
	}

}
