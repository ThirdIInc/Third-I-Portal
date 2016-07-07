/**
 * 
 */
package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0540
 *
 */
public class CreateOfferModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id) {
			case 1 : return "SELECT HRMS_REC_OFFER.OFFER_CODE, HRMS_REC_OFFER.OFFER_REQS_CODE, HRMS_REC_REQS_HDR.REQS_NAME, HRMS_REC_OFFER.OFFER_CAND_CODE, "
							+"HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME, HRMS_REC_REQS_HDR.REQS_POSITION, HRMS_RANK.RANK_NAME, "
							+"HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER, E2.EMP_FNAME||' '||E2.EMP_MNAME||' '||E2.EMP_LNAME,  "
							+"TO_DATE(SYSDATE, 'DD-MM-YYYY')-TO_DATE(HRMS_REC_OFFER.OFFER_DUE_DATE, 'DD-MM-YYYY'), "
							+"TO_CHAR(HRMS_REC_OFFER.OFFER_DATE, 'DD-MM-YYYY'), TO_CHAR(HRMS_REC_OFFER.OFFER_ACCEPT_DATE, 'DD-MM-YYYY'), "
							+"TO_CHAR(HRMS_REC_OFFER.OFFER_APPR_DATE, 'DD-MM-YYYY'), HRMS_REC_OFFER.OFFER_OFFERED_CTC, HRMS_REC_CAND_DATABANK.CAND_RESUME, HRMS_REC_OFFER.OFFER_TEMPLATE," +
							" E1.EMP_ID,(E1.EMP_FNAME||' '||E1.EMP_MNAME||' '||E1.EMP_LNAME) as signname, TO_CHAR(HRMS_REC_OFFER.OFFER_EXP_JOINDATE, 'DD-MM-YYYY') "							
							+"FROM HRMS_REC_OFFER "
							+"INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_OFFER.OFFER_REQS_CODE) "
							+"INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_OFFER.OFFER_CAND_CODE) "
							+"INNER JOIN HRMS_EMP_OFFC E2 ON (E2.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) "
							+"LEFT JOIN HRMS_EMP_OFFC E1  ON (E1.EMP_ID = HRMS_REC_OFFER.OFFER_SIGN_AUTH) "
							+" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION ";						
			//+"INNER JOIN HRMS_RANK ON (HRMS_REC_OFFER.OFFER_POSITION = HRMS_RANK.RANK_ID) "; 
							//+"WHERE OFFER_STATUS = ?";
			
			case 2 : return "SELECT HRMS_REC_OFFER.OFFER_CODE, HRMS_REC_OFFER.OFFER_REQS_CODE, HRMS_REC_REQS_HDR.REQS_NAME, HRMS_REC_OFFER.OFFER_CAND_CODE, "
			+"HRMS_REC_CAND_DATABANK.CAND_FIRST_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_MID_NAME||' '||HRMS_REC_CAND_DATABANK.CAND_LAST_NAME, HRMS_REC_REQS_HDR.REQS_POSITION, HRMS_RANK.RANK_NAME, "
			+"HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER, E2.EMP_FNAME||' '||E2.EMP_MNAME||' '||E2.EMP_LNAME,  "
			+"TO_DATE(SYSDATE, 'DD-MM-YYYY')-TO_DATE(HRMS_REC_OFFER.OFFER_DUE_DATE, 'DD-MM-YYYY'), "
			+"TO_CHAR(HRMS_REC_OFFER.OFFER_DATE, 'DD-MM-YYYY'), TO_CHAR(HRMS_REC_OFFER.OFFER_ACCEPT_DATE, 'DD-MM-YYYY'), "
			+"TO_CHAR(HRMS_REC_OFFER.OFFER_APPR_DATE, 'DD-MM-YYYY'), HRMS_REC_OFFER.OFFER_OFFERED_CTC, HRMS_REC_CAND_DATABANK.CAND_RESUME, HRMS_REC_OFFER.OFFER_TEMPLATE," +
			" E1.EMP_ID,(E1.EMP_FNAME||' '||E1.EMP_MNAME||' '||E1.EMP_LNAME) as signname, TO_CHAR(HRMS_REC_OFFER.OFFER_EXP_JOINDATE, 'DD-MM-YYYY') "							
			+"FROM HRMS_REC_OFFER "
			+"INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_OFFER.OFFER_REQS_CODE) "
			+"INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_OFFER.OFFER_CAND_CODE) "
			+"INNER JOIN HRMS_EMP_OFFC E2 ON (E2.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) "
			+"LEFT JOIN HRMS_EMP_OFFC E1  ON (E1.EMP_ID = HRMS_REC_OFFER.OFFER_SIGN_AUTH) "
			+"LEFT JOIN HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_POSITION = HRMS_RANK.RANK_ID) ";
			//+"INNER JOIN HRMS_RANK ON (HRMS_REC_OFFER.OFFER_POSITION = HRMS_RANK.RANK_ID) ";
			//+"WHERE OFFER_STATUS = ?";
			
			default : return "";
		}
	}
}
