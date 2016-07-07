package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class AppraisalInstructionModelSql extends SqlBase {
	
	public String getQuery(int i){
		
		switch(i){
		
		case 1 : return " SELECT APPR_RATING_VALU,APPR_RATING_DESC FROM PAS_APPR_QUESTION_RATING_DTL "
						+" WHERE  APPR_ID = ? ORDER BY APPR_RATING_VALU ";
		
		case 2 : return "UPDATE PAS_APPR_TEMPLATE SET APPR_INSTRUCTION_FLAG = ? , APPR_INSTRUCTIONS = ? WHERE APPR_ID = ? AND APPR_TEMPLATE_ID = ? ";
		
		case 3: return " SELECT APPR_INSTRUCTION_FLAG,APPR_INSTRUCTIONS FROM PAS_APPR_TEMPLATE WHERE APPR_ID= ? AND APPR_TEMPLATE_ID= ?";
		
		case 4 : return " SELECT APPR_RATING_TYPE FROM PAS_APPR_QUESTION_RATING_HDR WHERE APPR_ID = ?";
		default : return "";
		}
	}

}
