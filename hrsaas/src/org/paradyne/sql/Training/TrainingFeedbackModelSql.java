/**
 * 
 */
package org.paradyne.sql.Training;

import org.paradyne.lib.SqlBase;

/**
 * @author ritac
 *
 */
public class TrainingFeedbackModelSql extends SqlBase {

	public String getQuery(int queryCode){
		switch(queryCode){
		
		case 1:  return "  INSERT INTO HRMS_TRN_FBKHDR(TRNFBK_CODE,TRNFBK_ECODE,TRNFBK_DESC,TRNFBK_TRNID)  "
		+"  VALUES((SELECT NVL(MAX(TRNFBK_CODE),0)+1 FROM HRMS_TRN_FBKHDR),?,?,?)	";
		
		
		case 2: return " DELETE FROM HRMS_TRN_FBKHDR WHERE TRNFBK_CODE=?";
		
		case 3:return " DELETE FROM  HRMS_TRN_FBKDTL WHERE TRNFBKDTL_CODE=?";
	
		case 4:return " UPDATE  HRMS_TRN_FBKHDR SET TRNFBK_DESC=? WHERE TRNFBK_CODE=?";
		
		case 5: return "  SELECT DISTINCT HRMS_QUES.QUES_CODE,QUES_NAME FROM HRMS_QUES  "
						+" INNER JOIN HRMS_QUESDTL ON(HRMS_QUESDTL.QUES_CODE=HRMS_QUES.QUES_CODE)   "
						+" WHERE QUES_TYPE='F'";
		
		default: return " Query Not Found ";
		}
	}
}
