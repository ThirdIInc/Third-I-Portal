package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class ApprFormInstructionModelSql extends SqlBase {
	
	public String getQuery(int i){
		
		switch(i){
		
		case 1 : return " SELECT APPR_TEMPLATE_ID,APPR_INSTRUCTION_FLAG,APPR_INSTRUCTIONS FROM PAS_APPR_TEMPLATE "
						+" WHERE APPR_ID = ? AND APPR_TEMPLATE_ID = ? ";
		
		
		case 2 : return " SELECT APPR_RATING_VALU,APPR_RATING_DESC from PAS_APPR_QUESTION_RATING_DTL WHERE APPR_ID = ? ";
						
						
		default : return "";
		}
	}

}
