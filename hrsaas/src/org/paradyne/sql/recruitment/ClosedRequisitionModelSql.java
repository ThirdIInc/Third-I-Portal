package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

public class ClosedRequisitionModelSql extends SqlBase {


	
	public String getQuery(int id) 
	{
		switch (id) {
				//case 1	: return " update HRMS_REC_REQS_HDR set REQS_CLOSE_DATE = SYSDATE, REQS_STATUS = 'C', where REQS_CODE = ' + reqCode.getReqCode() + ' " ;
		
				default	: return "Framework could not the query number specified";
		}
	}

}
