package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class TemplateDefinationModelSql extends SqlBase {
	
	public String getQuery(int i){
		
		switch(i){
		
		
			case 1: return " INSERT INTO PAS_APPR_TEMPLATE (APPR_TEMPLATE_ID,APPR_ID,APPR_TEMPLATE_NAME,APPR_INSTRUCTION_FLAG, " 
							+" APPR_INSTRUCTIONS,APPR_AWARD_FLAG,APPR_DISCIPLINARY_FLAG,APPR_CAREER_FLAG,APPR_TRN_FLAG ) VALUES "
							+" ((SELECT NVL(MAX(APPR_TEMPLATE_ID), 0)+1 FROM PAS_APPR_TEMPLATE),?,?,'N','','N','N','N','N' )";
			
			case 2: return " UPDATE PAS_APPR_TEMPLATE SET APPR_ID = ? ,APPR_TEMPLATE_NAME = ? WHERE APPR_TEMPLATE_ID = ? ";
			
			
			case 3: return " SELECT NVL(MAX(APPR_TEMPLATE_ID),0) FROM PAS_APPR_TEMPLATE ";
			
			case 4 : return " UPDATE PAS_APPR_CALENDAR SET APPR_CAL_IS_STARTED = 'Y' WHERE APPR_ID = ? ";
			
			default : return  "";
		
		}
		
	}

}
