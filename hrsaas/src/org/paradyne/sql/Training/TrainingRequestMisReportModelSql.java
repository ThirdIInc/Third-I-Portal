package org.paradyne.sql.Training;

import org.paradyne.lib.SqlBase;

public class TrainingRequestMisReportModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id)
		{
			case 1 : return "SELECT TO_CHAR(TRN_REQ_DATE,'DD-MM-YYYY'), TRN_SUB,TRN_REQ_FOR, "
							+"HRMS_TITLE.TITLE_NAME||' ' ||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
							+"DECODE(TRN_STATUS, 'A','Approved', 'P', 'Pending') "
							+"FROM HRMS_TRN_REQ "
							+"LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_TRN_REQ.REQ_BY_CODE) "
							+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) ";
			
			case 2 : return "SELECT (HRMS_TITLE.TITLE_NAME||' ' ||EMP_FNAME ||' '|| EMP_MNAME ||' '||EMP_LNAME)NAME, "
							+"AGE, SERVICE_PERIOD "
							+"FROM HRMS_TRN_DTL "
							+"INNER JOIN HRMS_EMP_OFFC ON (HRMS_TRN_DTL.EMP_CODE= HRMS_EMP_OFFC.EMP_ID) "
							+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) ";
			
			default : return "Framework could not EXECUTE the query number specified";			
		}
	 }
}
