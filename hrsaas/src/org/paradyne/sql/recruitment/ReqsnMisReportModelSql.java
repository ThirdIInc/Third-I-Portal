package org.paradyne.sql.recruitment;
import org.paradyne.lib.SqlBase;
/**
 * Created on Date:27-10-2009
 * @author Pradeep Kumar Sahoo
 *
 */
public class ReqsnMisReportModelSql extends SqlBase {
	
	public String getQuery(int id){
		switch (id) {
		
		case 1:return " INSERT INTO HRMS_REC_MPR_FILTERS(MPR_FIL_CODE,MPR_FIL_DIV,MPR_FIL_BRN, MPR_FIL_DEPT,MPR_FIL_POSITION, "
					+" MPR_FIL_DATE_OPT,MPR_FIL_FRM_DATE,MPR_FIL_TO_DATE,MPR_REP_FIL_STATUS,MPR_FIL_REP_OPT,MPR_FIL_USER_NAME,MPR_FIL_USER_ID)"
					+" VALUES((SELECT NVL(MAX(MPR_FIL_CODE), 0)+1 FROM HRMS_REC_MPR_FILTERS),?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?)";
		
		case 2:return "INSERT INTO HRMS_REC_MPR_SORT(MPR_FIL_CODE,SORT_BY,SORT_BY_ORDER,SORT_THENBY,SORT_THENBY_ORDER,SORT_THENBY1 ,SORT_THENBY_OREDR1)"
					+" VALUES(?,?,?,?,?,?,?)";
		
		case 3:return "INSERT INTO HRMS_REC_MPR_ADV(MPR_FIL_CODE,ADV_HIRING_MGR,ADV_REQS_STAT ,ADV_VAC_OPT  ,ADV_VAC_VAL) "
					+" VALUES(?,?,?,?,?)";	
		
		case 4:return "INSERT INTO HRMS_REC_MPR_REQS(MPR_FIL_CODE,REQS_CODE) VALUES(?,?)";
		
		case 5:return "UPDATE HRMS_REC_MPR_FILTERS SET MPR_FIL_DIV=?,MPR_FIL_BRN=?, MPR_FIL_DEPT=?,MPR_FIL_POSITION=?, "
					+" MPR_FIL_DATE_OPT=?,MPR_FIL_FRM_DATE=TO_DATE(?,'DD-MM-YYYY'),MPR_FIL_TO_DATE=TO_DATE(?,'DD-MM-YYYY'),MPR_REP_FIL_STATUS=?,MPR_FIL_REP_OPT=?"
					+" WHERE MPR_FIL_CODE=?";
		
		case 6:return "UPDATE HRMS_REC_MPR_SORT SET SORT_BY=?,SORT_BY_ORDER=?,SORT_THENBY=?,SORT_THENBY_ORDER=?,SORT_THENBY1=? ,SORT_THENBY_OREDR1=?"
					+" WHERE MPR_FIL_CODE=?";
		
		case 7:return " UPDATE HRMS_REC_MPR_ADV SET ADV_HIRING_MGR=?,ADV_REQS_STAT=? ,ADV_VAC_OPT=?  ,ADV_VAC_VAL=? WHERE MPR_FIL_CODE=?";
		
		case 8:return "DELETE FROM HRMS_REC_MPR_REQS WHERE MPR_FIL_CODE=?";
		
		default:return "Query does not exist.";
		
		
		}
	}	
	

}
