/**
 * 
 */
package org.paradyne.sql.admin.srd;

import org.paradyne.lib.SqlBase;

/**
 * @author riteshr
 *
 */

public class LeaveHistoryModelSql extends SqlBase {
	
public String getQuery(int id){
		
		
		
		switch (id) {
		
		case 1 :  return " INSERT INTO HRMS_LEAVE_DTL (LEAVE_DTL_ID,EMP_ID,LEAVE_CODE,LEAVE_DAYS,LEAVE_FROM_DATE,LEAVE_TO_DATE) "
					+" VALUES((SELECT NVL(MAX(LEAVE_DTL_ID),0)+1 FROM HRMS_LEAVE_DTL),UPPER(?),?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY')) "; 
		
		
		case 2 :  return " SELECT LEAVE_NAME,LEAVE_DAYS,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'), "
					+" TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),LEAVE_DTL_ID FROM HRMS_LEAVE_DTL "
					+" INNER JOIN HRMS_LEAVE ON (HRMS_LEAVE_DTL.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID) " 
					+" WHERE EMP_ID = ? ORDER BY  LEAVE_DTL_ID ";
		
		case 3 :  return " SELECT LEAVE_NAME,LEAVE_DAYS,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'), "
				+" TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
				+" INNER JOIN HRMS_LEAVE ON (HRMS_LEAVE_DTL.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID) " 
				+" WHERE   LEAVE_DTL_ID = ?  ";
		
		case 4 :  return " UPDATE HRMS_LEAVE_DTL  SET LEAVE_CODE = ?,LEAVE_DAYS = ?,LEAVE_FROM_DATE = TO_DATE(?,'DD-MM-YYYY'), "
				 +" LEAVE_TO_DATE = TO_DATE(?,'DD-MM-YYYY') WHERE LEAVE_DTL_ID = ? "; 
		
		case 5: return  " DELETE FROM  HRMS_LEAVE_DTL  WHERE LEAVE_DTL_ID = ? "; 
		
		
		case 7 : return " SELECT (CENTER_NAME),RANK_NAME,EMP_ID,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '|| EMP_MNAME|| '  ' ||EMP_LNAME),EMP_TOKEN FROM HRMS_EMP_OFFC " 
				+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+" INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+" INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+" WHERE EMP_ID = ?";

		
		default: return " query no. is not specified  ";
		}
	}


}
