/**
 * 
 */
package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

/**
 * @author aa0554
 *
 */
public class SectionMappingModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {
		case 1 : return "INSERT INTO PAS_APPR_EMP_GRP_HDR(APPR_EMP_GRP_ID,APPR_EMP_GRP_DATE,APPR_EMP_GRP_NAME,APPR_ID,APPR_TEMPLATE_ID)"
							+" VALUES(?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?,?)";
			
		
		case 2 : return "INSERT INTO PAS_APPR_EMP_GRP_DTL(APPR_EMP_GRP_ID,APPR_EMP_GRP_EMPID) VALUES(?,?)";
		
		case 3 : return "INSERT INTO PAS_APPR_QUES_MAPPING (APPR_ID,APPR_TEMPLATE_ID,APPR_EMP_GRP_ID,APPR_SECTION_ID,APPR_PHASE,APPR_QUESTION_ID,APPR_QUESTION_ORDER,APPR_QUESTION_WT)"
						+" VALUES(?,?,?,?,?,?,?,?)";
		
		case 4 : return "UPDATE PAS_APPR_EMP_GRP_HDR SET APPR_EMP_GRP_DATE=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),APPR_EMP_GRP_NAME=? WHERE APPR_EMP_GRP_ID=?";
		
		default:return "Framework could not find the query number specified";
			
		}
		
	} 
}
