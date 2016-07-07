package org.paradyne.sql.PAS;
import org.paradyne.lib.SqlBase;

public class WeightageDistributionModelSql extends SqlBase{

	public String getQuery(int id){
		
		
		switch (id) {
				
				case 1: return " SELECT APPR_PHASE_NAME,APPR_PHASE_WEIGHT_AGE, PAS_APPR_PHASE_CONFIG.APPR_ID, APPR_PHASE_ID FROM PAS_APPR_PHASE_CONFIG "
							  +" INNER JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_PHASE_CONFIG.APPR_ID)"
							  +" WHERE PAS_APPR_CALENDAR.APPR_ID=? AND APPR_PHASE_STATUS='A'";
				
				default: return "hi";
			
		}
		



	}
	
}
