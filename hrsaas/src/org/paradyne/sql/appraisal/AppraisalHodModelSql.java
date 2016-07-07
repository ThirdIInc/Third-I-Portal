/**
 * 
 */
package org.paradyne.sql.appraisal;

import org.paradyne.lib.SqlBase;

/**
 * @author prakashs
 *
 */
public class AppraisalHodModelSql extends SqlBase {
	
	public String getQuery(int code){
		switch(code)
		{
		
		case 1 : return " SELECT (HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), "
							+" NVL(HRMS_DEPT.DEPT_NAME,' '),HRMS_APPRAISAL.APPR_EMP_ID,HRMS_APPRAISAL.APPR_CODE,APPR_HOD_LEVEL,NVL(HRMS_RANK.RANK_NAME,' ') FROM HRMS_APPRAISAL " 
							+" left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_APPRAISAL.APPR_EMP_ID ) " 
							+" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
							+" LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
							+" LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
							+" WHERE CONF_CODE = (SELECT MAX(CONF_CODE) FROM HRMS_APPRAISAL) AND APPR_STATUS = 'P' AND APPR_HOD_LEVEL = '1' AND APPR_HOD_CODE = ? ";
		
		default  : return "hi";
		}
	}

}
