package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class DefineSectionModelSql extends SqlBase {

	public String getQuery(int i) {

		switch (i) {

		case 1:
			return " SELECT APPR_PHASE_ID,APPR_PHASE_NAME FROM PAS_APPR_PHASE_CONFIG  WHERE APPR_ID = ? AND APPR_PHASE_STATUS = 'A' ORDER BY APPR_PHASE_ORDER";

		case 2:
			return " INSERT INTO PAS_APPR_SECTION_HDR (APPR_ID,APPR_TEMPLATE_ID,APPR_SECTION_ID,APPR_SECTION_NAME,APPR_SECTION_ORDER) VALUES "
					+ " ( ?,?,(SELECT NVL(MAX(APPR_SECTION_ID),0)+1 FROM PAS_APPR_SECTION_HDR),?,?)";

		case 3:
			return " UPDATE PAS_APPR_SECTION_HDR SET APPR_SECTION_NAME = ? , APPR_SECTION_ORDER = ? WHERE APPR_SECTION_ID= ? AND APPR_TEMPLATE_ID= ? AND APPR_ID = ? ";

		case 4:
			return " SELECT NVL(MAX(APPR_SECTION_ID),0) FROM PAS_APPR_SECTION_HDR ";

		case 5:
			return " INSERT INTO PAS_APPR_SECTION_DTL (APPR_ID,APPR_TEMPLATE_ID,APPR_SECTION_ID,APPR_PHASE_ID,APPR_SECTION_VISIBILITY,APPR_SECTION_RATING,APPR_SECTION_COMMENT) "
					+ " VALUES(?,?,?,?,?,?,?) ";

		/*case 6:
			return " SELECT APPR_SECTION_ID,APPR_PHASE_ID,APPR_SECTION_VISIBILITY,APPR_SECTION_RATING,APPR_SECTION_COMMENT FROM PAS_APPR_SECTION_DTL  "
					+ " WHERE APPR_ID = ? AND  APPR_PHASE_ID IN "
					+" (SELECT APPR_PHASE_ID FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID = ? AND APPR_PHASE_STATUS = 'A') AND APPR_TEMPLATE_ID = ? "
					+" ORDER BY APPR_SECTION_ID,APPR_PHASE_ID ";
*/
			
		case 6: return " SELECT PAS_APPR_SECTION_HDR.APPR_SECTION_ID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID,APPR_SECTION_VISIBILITY,APPR_SECTION_RATING,APPR_SECTION_COMMENT " 
						+" FROM PAS_APPR_SECTION_DTL "  
						+" INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_SECTION_DTL.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) "
						+" INNER JOIN PAS_APPR_SECTION_HDR ON (PAS_APPR_SECTION_DTL.APPR_SECTION_ID = PAS_APPR_SECTION_HDR.APPR_SECTION_ID)"
						+" WHERE PAS_APPR_PHASE_CONFIG.APPR_ID = ? AND APPR_PHASE_STATUS = 'A' "
						+" AND PAS_APPR_SECTION_HDR.APPR_TEMPLATE_ID = ? " 
						+" ORDER BY APPR_SECTION_ORDER,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER ";	
		
		case 7:
			return " SELECT APPR_SECTION_ID,APPR_SECTION_NAME FROM PAS_APPR_SECTION_HDR WHERE APPR_ID = ? AND APPR_TEMPLATE_ID = ?   ORDER BY APPR_SECTION_ORDER ";

		case 8:
			return " UPDATE PAS_APPR_SECTION_DTL SET APPR_SECTION_VISIBILITY = ? ,APPR_SECTION_RATING = ?, APPR_SECTION_COMMENT = ? "
					+ " WHERE APPR_PHASE_ID= ? AND APPR_SECTION_ID = ? AND APPR_TEMPLATE_ID = ? AND APPR_ID = ? ";

		case 9:
			return " SELECT MAX(APPR_SECTION_ID) FROM PAS_APPR_SECTION_HDR ";

		case 10:
			return " SELECT APPR_PHASE_ID FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID = ? AND APPR_PHASE_STATUS = 'A' AND APPR_PHASE_ID NOT IN "
					+" (SELECT APPR_PHASE_ID FROM PAS_APPR_SECTION_DTL WHERE APPR_ID = ? AND APPR_TEMPLATE_ID = ?  )";

		case 11:
			return " INSERT INTO PAS_APPR_SECTION_HDR (APPR_ID,APPR_TEMPLATE_ID,APPR_SECTION_ID,APPR_SECTION_NAME,APPR_SECTION_ORDER) VALUES "
					+ " ( ?,?,?,?,?)";
			
		case 12: return "  SELECT * FROM PAS_APPR_COMMENTS WHERE APPR_ID = ? and APPR_TEMPLATE_ID = ? ";
			
		case 13: return " DELETE FROM PAS_APPR_SECTION_DTL WHERE APPR_ID = ? AND APPR_TEMPLATE_ID = ? ";
		
		case 14: return " DELETE FROM PAS_APPR_SECTION_HDR WHERE APPR_ID = ? AND APPR_TEMPLATE_ID = ? ";
		
		case 15 : return " DELETE FROM PAS_APPR_SECTION_DTL WHERE APPR_SECTION_ID = ? ";
		
		case 16 : return " DELETE FROM PAS_APPR_SECTION_HDR WHERE APPR_SECTION_ID = ? ";
		
		default:
			return "asda";
		}
	}

}
