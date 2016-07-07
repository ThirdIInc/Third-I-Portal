package org.paradyne.sql.PAS;
import org.paradyne.lib.SqlBase;

public class FormFinalizationModelSql extends SqlBase{

	public String getQuery(int id){
		
		
		switch (id) {
		
				case 1: return " SELECT APPR_TEMPLATE_NAME, APPR_INSTRUCTION_FLAG, NVL(APPR_INSTRUCTIONS,' '), APPR_AWARD_FLAG, APPR_DISCIPLINARY_FLAG, APPR_CAREER_FLAG, APPR_TRN_FLAG FROM PAS_APPR_TEMPLATE WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
				
				case 2:	return " SELECT * FROM PAS_APPR_SECTION_HDR WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?"; 
				
				case 3: return " SELECT * FROM PAS_APPR_TRN_RECOMMEND WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
				
				case 4: return " SELECT APPR_AWARD_FLAG, APPR_REASON_FLAG FROM PAS_APPR_AWD_NOMINATE WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
				
				case 5: return "SELECT * FROM PAS_APPR_COMMON_SECTION WHERE APPR_ID=? AND APPR_TEMPLATE_ID=? AND APPR_SECTION_TYPE='D' AND APPR_PHASE_VISIBILITY='Y'";
				
				case 6: return "SELECT * FROM PAS_APPR_CAREER WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
				
				default: return "hi";
			
		}
		



	}
	
}
