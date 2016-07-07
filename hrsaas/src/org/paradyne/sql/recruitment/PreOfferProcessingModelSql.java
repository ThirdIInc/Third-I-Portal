package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;
/*
 * @author ganesh
 * Date: 10/11/2010
 *  
 */
public class PreOfferProcessingModelSql extends SqlBase {
	
	public String getQuery(int code){
		
		switch(code){
		
		
		
		case 1: return "INSERT INTO HRMS_REC_PRECHECK (PRE_CODE,PRE_CAND_CODE,PRE_REQS_CODE,PRE_CHECK_TYPE,PRE_CHECK_LIST,PRE_COMMENTS,PRE_AGENCYCODE) " +
				"VALUES ((SELECT NVL(MAX(PRE_CODE),0)+1 FROM HRMS_REC_PRECHECK),?,?,?,?,?,?)";
		
		case 2 : return "INSERT INTO HRMS_REC_PRECHECK_DTL (PRE_DTL_CODE,PRE_CODE,CHECKLIST_CODE,PRE_RESPONSE,PRE_COMMENTS,PRE_DTL_PROOF) VALUES ((SELECT NVL(MAX(PRE_DTL_CODE),0)+1 FROM HRMS_REC_PRECHECK_DTL),?,?,?,?,?)";
		
		case 3 : return " UPDATE HRMS_REC_PRECHECK SET PRE_CAND_CODE=?,PRE_REQS_CODE=?,PRE_CHECK_TYPE=?,PRE_CHECK_LIST=?, "
						+" PRE_COMMENTS=?,PRE_AGENCYCODE=? WHERE PRE_CODE=?";
		
		//case 4: ""
		
		default : return "hiiiiiiiiiiiiiiiiii";
		}
		}

}
