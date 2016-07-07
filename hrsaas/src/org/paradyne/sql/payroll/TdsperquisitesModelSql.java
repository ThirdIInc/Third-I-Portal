package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;

public class TdsperquisitesModelSql extends SqlBase  {
	public String getQuery(int id) {
		switch(id) {
		
		
		case 1: return "INSERT INTO HRMS_PERQUISITE_HEAD(PERQ_CODE,PERQ_NAME,PERQ_ABBR,PERQ_PERIOD,PERQ_TAXABLE_FLAG,PERQ_IS_EXEMPTED, PERQ_EXEMPT_UNDER_SECTION )Values((select Nvl(max(PERQ_CODE),0)+1 from HRMS_PERQUISITE_HEAD ),?,?,?,?,?,?) ";
		case 2: return "UPDATE HRMS_PERQUISITE_HEAD SET PERQ_NAME=?,PERQ_ABBR=?,PERQ_PERIOD=?,PERQ_TAXABLE_FLAG=? ,PERQ_IS_EXEMPTED=?, PERQ_EXEMPT_UNDER_SECTION=? WHERE PERQ_CODE=?";
		case 3: return "DELETE HRMS_PERQUISITE_HEAD WHERE  PERQ_CODE=?";
		case 4: return "SELECT PERQ_CODE,NVL(PERQ_NAME,' '),NVL(PERQ_ABBR,' ')," +
				" DECODE(PERQ_PERIOD,'Q','Quarterly','M','Monthly','A','Annually','H','Half Yearly'),DECODE(PERQ_TAXABLE_FLAG,'Y','YES','N','NO')   FROM HRMS_PERQUISITE_HEAD ORDER BY PERQ_CODE";
		
		default : return "Framework could not EXECUTE the query number specified";
		
		
		}
	}
}
