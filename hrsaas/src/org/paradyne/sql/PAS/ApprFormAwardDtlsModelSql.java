package org.paradyne.sql.PAS;


import org.paradyne.lib.SqlBase;

public class ApprFormAwardDtlsModelSql extends SqlBase {

	
	public String getQuery(int i){
		
		switch(i){
				
			case 1 : return " SELECT APPR_TRN_FLAG FROM PAS_APPR_TEMPLATE WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
			
			case 2 : return " SELECT APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT FROM PAS_APPR_COMMON_SECTION "
			   				+" WHERE APPR_ID = ? AND APPR_TEMPLATE_ID= ? AND APPR_PHASE = ? AND APPR_SECTION_TYPE = 'A'";
			
			case 3: return " SELECT APPR_AWARD_CODE, NVL(APPR_AWARD_DESC,' '), TO_CHAR(APPR_AWARD_DATE,'DD-MM-YYYY'), NVL(APPR_ISSUING_AUTHORITY,' ') FROM" 
						  +" PAS_APPR_AWARD_ACHIEVED WHERE APPR_ID=? AND APPR_TEMPLATE_ID=? AND APPR_EMP_ID = ? ORDER BY APPR_AWARD_CODE ";
			
			case 4: return "SELECT * FROM PAS_APPR_AWD_ACHIEVED_COMMENT WHERE APPR_AWARD_CODE in( ? )AND APPR_APPRAISER_ID != ? ";
			
			case 5 : return "INSERT INTO PAS_APPR_AWARD_ACHIEVED(APPR_ID, APPR_TEMPLATE_ID, APPR_AWARD_CODE, APPR_AWARD_DESC, APPR_AWARD_DATE, APPR_ISSUING_AUTHORITY, APPR_EMP_ID)VALUES(?,?,(SELECT NVL(MAX(APPR_AWARD_CODE),0)+1 FROM PAS_APPR_AWARD_ACHIEVED ),?,TO_DATE(?,'DD-MM-YYYY'),?,?)";
			
			case 6 : return "UPDATE PAS_APPR_AWARD_ACHIEVED SET APPR_AWARD_DESC=?, APPR_AWARD_DATE=TO_DATE(?,'DD-MM-YYYY'), APPR_ISSUING_AUTHORITY=?"
						   +" WHERE APPR_ID = ? AND APPR_TEMPLATE_ID = ? AND APPR_AWARD_CODE = ? AND APPR_EMP_ID =?";
			
			case 7 : return " DELETE FROM PAS_APPR_AWARD_ACHIEVED WHERE APPR_AWARD_CODE=?";
			
			case 8 : return " SELECT APPR_AWARD_FLAG FROM PAS_APPR_TEMPLATE WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
			
			case 9 : return " SELECT PAS_APPR_AWARD_ACHIEVED.APPR_AWARD_CODE,NVL(APPR_AWARD_DESC,' '), NVL(TO_CHAR(APPR_AWARD_DATE,'DD-MM-YYYY'),' '), NVL(APPR_ISSUING_AUTHORITY,' '),NVL(APPR_AWARD_COMMENT,' '),NVL(PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_AWARD_CODE,'') FROM PAS_APPR_AWARD_ACHIEVED"
						   +" LEFT JOIN PAS_APPR_AWD_ACHIEVED_COMMENT ON(PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_ID=PAS_APPR_AWARD_ACHIEVED.APPR_ID AND"
						   +" PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_EMP_ID = PAS_APPR_AWARD_ACHIEVED.APPR_EMP_ID AND"
						   +" PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_TEMPLATE_ID = PAS_APPR_AWARD_ACHIEVED.APPR_TEMPLATE_ID AND"
						   +" PAS_APPR_AWARD_ACHIEVED.APPR_AWARD_CODE = PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_AWARD_CODE AND"
						   +" APPR_PHASE_ID = ? AND APPR_APPRAISER_ID=?)WHERE PAS_APPR_AWARD_ACHIEVED.APPR_ID =? AND"
						   +" PAS_APPR_AWARD_ACHIEVED.APPR_TEMPLATE_ID =? AND PAS_APPR_AWARD_ACHIEVED.APPR_EMP_ID = ? ORDER BY APPR_AWARD_CODE ";
			
			case 10 : return "SELECT APPR_AWARD_FLAG, APPR_REASON_FLAG FROM PAS_APPR_AWD_NOMINATE WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
			
			case 11 : return "SELECT NVL(APPR_AWARD,' '), NVL(APPR_AWARD_REASON,' '), APPR_AWARD_ID FROM PAS_APPR_AWD_NOMINATE_COMMENT WHERE APPR_ID=? AND APPR_TEMPLATE_ID=? AND APPR_EMP_ID=? AND APPR_PHASE_ID=? AND  APPR_APPRAISER_ID = ? ORDER BY APPR_AWARD_ID";
			
			case 12 : return "INSERT INTO PAS_APPR_AWD_ACHIEVED_COMMENT(APPR_ID,APPR_TEMPLATE_ID,  APPR_PHASE_ID, APPR_EMP_ID, APPR_AWARD_CODE, APPR_AWARD_COMMENT,APPR_APPRAISER_ID)VALUES(?,?,?,?,?,?,?)";
			
			case 13 : return " UPDATE PAS_APPR_AWD_ACHIEVED_COMMENT SET APPR_AWARD_COMMENT = ? WHERE APPR_ID=? AND APPR_TEMPLATE_ID=? AND APPR_PHASE_ID=? AND APPR_EMP_ID=? AND APPR_AWARD_CODE=? AND APPR_APPRAISER_ID=?";
			
			case 14 : return "INSERT INTO PAS_APPR_AWD_NOMINATE_COMMENT(APPR_ID, APPR_TEMPLATE_ID, APPR_EMP_ID, APPR_AWARD, APPR_AWARD_REASON, APPR_PHASE_ID, APPR_AWARD_ID,APPR_APPRAISER_ID)VALUES(?,?,?,?,?,?,(SELECT NVL(MAX(APPR_AWARD_ID),0)+1 FROM PAS_APPR_AWD_NOMINATE_COMMENT ),?)";
			
			case 15 : return " UPDATE PAS_APPR_AWD_NOMINATE_COMMENT SET APPR_AWARD=?,APPR_AWARD_REASON=? WHERE APPR_ID=? AND APPR_TEMPLATE_ID=? AND APPR_EMP_ID=? AND APPR_PHASE_ID=? AND APPR_AWARD_ID=? AND APPR_APPRAISER_ID=?";
			
			case 16 : return "DELETE FROM PAS_APPR_AWD_NOMINATE_COMMENT WHERE APPR_AWARD_ID=? AND APPR_APPRAISER_ID=?";
			
			case 17 : return "SELECT APPR_PHASE_ID,APPR_PHASE_NAME,PAS_APPR_AWARD_ACHIEVED.APPR_AWARD_CODE,NVL(APPR_AWARD_DESC,' '), NVL(TO_CHAR(APPR_AWARD_DATE,'DD-MM-YYYY'),' '), NVL(APPR_ISSUING_AUTHORITY,' '),NVL(APPR_AWARD_COMMENT,' '),NVL(PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_AWARD_CODE,'') FROM PAS_APPR_AWARD_ACHIEVED"
							+" LEFT JOIN PAS_APPR_AWD_ACHIEVED_COMMENT ON(PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_ID=PAS_APPR_AWARD_ACHIEVED.APPR_ID AND"
							+" PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_EMP_ID = PAS_APPR_AWARD_ACHIEVED.APPR_EMP_ID AND"
							+" PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_TEMPLATE_ID = PAS_APPR_AWARD_ACHIEVED.APPR_TEMPLATE_ID AND"
							+" PAS_APPR_AWARD_ACHIEVED.APPR_AWARD_CODE = PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_AWARD_CODE "
							+" )"//AND APPR_PHASE_ID NOT IN  (?) 
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_ID = PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_ID AND PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_PHASE_ID )WHERE" 
							+" PAS_APPR_AWARD_ACHIEVED.APPR_ID =? AND PAS_APPR_AWARD_ACHIEVED.APPR_TEMPLATE_ID =? AND" 
							+" PAS_APPR_AWARD_ACHIEVED.APPR_EMP_ID = ?" 
							+" AND("
							+" PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER<"
							+"(SELECT APPR_PHASE_ORDER FROM PAS_APPR_PHASE_CONFIG WHERE APPR_PHASE_ID=?)" 
							+"OR (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER="
							+"(SELECT APPR_PHASE_ORDER FROM PAS_APPR_PHASE_CONFIG WHERE APPR_PHASE_ID=?)  AND APPR_APPRAISER_ID!=?)"
							+") "
							+" ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID,PAS_APPR_AWD_ACHIEVED_COMMENT.APPR_AWARD_CODE ";
			
			case 18 : return "SELECT APPR_IS_SELFPHASE FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID=? AND APPR_PHASE_ID=?";
			
			case 19 : return "SELECT PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID, PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME, PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL FROM PAS_APPR_APPRAISER" 
							+" INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_ID=PAS_APPR_APPRAISER.APPR_ID AND"
							+" PAS_APPR_APPRAISER.APPR_PHASE_ID=PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID"
							+")WHERE PAS_APPR_PHASE_CONFIG.APPR_ID=? AND" 
							+" PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=? AND"
							+" APPR_APPRAISER_CODE=?";
			
			case 20 : return "SELECT MAX(APPR_AWARD_CODE) FROM PAS_APPR_AWARD_ACHIEVED";
			
			case 21 : return " DELETE FROM PAS_APPR_AWD_ACHIEVED_COMMENT WHERE APPR_AWARD_CODE = ? ";
			
			
			//-------------------
			
			case 22: return "DELETE FROM PAS_APPR_AWD_ACHIEVED_COMMENT WHERE APPR_ID = ? AND APPR_EMP_ID = ? AND APPR_APPRAISER_ID = ? ";
			
			case 23: return "DELETE FROM PAS_APPR_AWARD_ACHIEVED WHERE APPR_ID = ? AND APPR_EMP_ID = ? ";
			
			default: return "";
		
		}
	}
	
}
