package org.paradyne.sql.settlement;

import org.paradyne.lib.SqlBase;
/**
 * @author Rita
 * @since 29/11/2007
 */
public class ExitIntQuesModelSql extends SqlBase {
	
	public String getQuery(int id){
		 
		
		switch(id){
	
		case 1:return  " INSERT INTO HRMS_EXIT_HDR(RESIGN_CODE,EXIT_DATE) VALUES(?,TO_DATE(?,'DD-MM-YYYY')) ";
		 
		case 2:return "  SELECT DISTINCT HRMS_QUES.QUES_CODE,QUES_NAME FROM HRMS_QUES  "
						+" INNER JOIN HRMS_QUESDTL ON(HRMS_QUESDTL.QUES_CODE=HRMS_QUES.QUES_CODE)   "
						+" WHERE QUES_TYPE='E'";
		
		case 3:return " INSERT INTO HRMS_EXIT_DTL (RESIGN_CODE,EXIT_QUES_CODE,EXIT_QUES_RATE,EXIT_QUES_COMMENTS) "
					 +" VALUES (?,?,?,?)";
		
		case 4:return " DELETE FROM HRMS_EXIT_HDR WHERE RESIGN_CODE = ? ";
		
		
		
		case 5:return "SELECT HRMS_QUES.QUES_NAME,HRMS_EXIT_DTL.EXIT_QUES_COMMENTS,HRMS_EXIT_DTL.EXIT_QUES_RATE FROM HRMS_EXIT_DTL"+
					 " INNER JOIN HRMS_EXITQUESHDR ON(HRMS_EXIT_HDR.RESIGN_CODE =HRMS_EXIT_DTL.RESIGN_CODE)"+
					 " INNER JOIN HRMS_QUES ON(HRMS_QUES.QUES_CODE = HRMS_EXIT_HDR.EXIT_QUES_CODE)";
						
		case 6:return "UPDATE HRMS_EXIT_DTL set EXIT_QUES_RATE = ?,EXIT_QUES_COMMENTS = ? WHERE RESIGN_CODE = ? ";
		case 7:return " DELETE FROM HRMS_EXIT_DTL WHERE RESIGN_CODE = ? ";
		default:return"";
		}
	}

}
