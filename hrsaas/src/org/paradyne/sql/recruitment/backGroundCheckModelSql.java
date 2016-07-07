package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

public class backGroundCheckModelSql extends SqlBase {
	
	public String getQuery(int code){
		
		switch(code){
		
		
		
		case 1: return "INSERT INTO HRMS_REC_BGCHECK (BG_CODE,BG_CAND_CODE,BG_REQS_CODE,BG_CHECK_TYPE,BG_CHECK_LIST,BG_COMMENTS,BG_AGENCYCODE) " +
				"VALUES ((SELECT NVL(MAX(BG_CODE),0)+1 FROM HRMS_REC_BGCHECK),?,?,?,?,?,?)";
		
		case 2 : return "INSERT INTO HRMS_REC_BGCHECK_DTL (BG_DTL_CODE,BG_CODE,CHECKLIST_CODE,BG_RESPONSE,BG_COMMENTS,BG_DTL_PROOF) VALUES ((SELECT NVL(MAX(BG_DTL_CODE),0)+1 FROM HRMS_REC_BGCHECK_DTL),?,?,?,?,?)";
		
		case 3 : return " UPDATE HRMS_REC_BGCHECK SET BG_CAND_CODE=?,BG_REQS_CODE=?,BG_CHECK_TYPE=?,BG_CHECK_LIST=?, "
						+" BG_COMMENTS=?,BG_AGENCYCODE=? WHERE BG_CODE=?";
		
		//case 4: ""
		
		default : return "hiiiiiiiiiiiiiiiiii";
		}
		}

}
