package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

/**
 * @author Nilesh Dhandare
 */

public class InterviewAssessMasterModelSql extends SqlBase {

	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_REC_INTERVIEW_ASSESSMENT(REC_ASSESS_CODE, REC_ASSESS_PARAM, REC_ASSESS_DESC, REC_ISACTIVE) "
  +" VALUES((SELECT NVL(MAX(REC_ASSESS_CODE),0)+1 FROM HRMS_REC_INTERVIEW_ASSESSMENT ),?,?,?) ";  
  
     
		case 2: return " SELECT REC_ASSESS_CODE, REC_ASSESS_PARAM, REC_ASSESS_DESC,DECODE(REC_ISACTIVE,'Y','YES','N','NO') "
		+ " FROM HRMS_REC_INTERVIEW_ASSESSMENT "
		+ " ORDER BY  REC_ASSESS_CODE ";
		
		
		case 3: return " UPDATE HRMS_REC_INTERVIEW_ASSESSMENT SET REC_ASSESS_PARAM=?, REC_ASSESS_DESC=?, REC_ISACTIVE=? WHERE REC_ASSESS_CODE=? " ; 
		
		case 4: return " DELETE FROM HRMS_REC_INTERVIEW_ASSESSMENT WHERE REC_ASSESS_CODE=? ";
		
		default : return "Framework could not the query number specified";			
		}
	}
}