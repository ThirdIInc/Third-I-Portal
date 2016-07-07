package org.paradyne.sql.payroll.salary;

import org.paradyne.lib.SqlBase;

public class PAProcessModelSql extends SqlBase {
	 public String getQuery(int i)
	 {
		 switch(i)
		 {
		  case 1 : return "INSERT INTO HRMS_ALLOWANCE_HDR (ALLW_ID, ALLW_FROM_DATE, ALLW_TO_DATE, " +
			  		"  ALLW_CREDIT_HEAD,  ALLW_PROCESS_DATE, ALLW_APPRAISAL_RATING_FLAG," +
			  		" ALLW_DIVISION, ALLW_DEPT, ALLW_BRANCH, ALLW_EMPTYPE, ALLW_PAYBILL, ALLW_DESG, ALLW_EMP_ID, " +
			  		" ALLW_PAY_IN_SALARY, ALLW_PAY_MONTH, ALLW_PAY_YEAR ,ALLW_PAY_MODE )" +
			  		" VALUES((SELECT NVL(MAX(ALLW_ID),0) FROM HRMS_ALLOWANCE_HDR)+1,TO_DATE(?,'DD-MM-YY'),TO_DATE(?,'DD-MM-YY'),?,TO_DATE(?,'DD-MM-YYYY') ,?,?,?,?,?,?,?,?,?,?,?,?)";
		  case 2 : return "UPDATE HRMS_ALLOWANCE_HDR SET  ALLW_FROM_DATE = TO_DATE(?,'DD-MM-YYYY'), ALLW_TO_DATE = TO_DATE(?,'DD-MM-YYYY') ,  " +
				  		"  ALLW_CREDIT_HEAD = ? , ALLW_APPRAISAL_RATING_FLAG = ?, " +
				  		 " ALLW_DIVISION = ? , ALLW_DEPT = ? , ALLW_BRANCH = ? , ALLW_EMPTYPE = ? , ALLW_PAYBILL = ?, ALLW_DESG = ?, ALLW_EMP_ID = ? , " +
				  	    " ALLW_PAY_IN_SALARY = ? , ALLW_PAY_MONTH = ? , ALLW_PAY_YEAR = ?,ALLW_PAY_MODE= ? WHERE  ALLW_ID = ? " ;
		 	 
		 
		  default : return "query not found";
		 }
	 }

}
