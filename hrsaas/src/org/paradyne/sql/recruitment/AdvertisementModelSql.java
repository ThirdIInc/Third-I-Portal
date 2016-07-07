package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

public class AdvertisementModelSql extends SqlBase {

	public String getQuery(int queryCode)
	{
		switch(queryCode)
		{
		case 1:  return "  INSERT INTO HRMS_REC_ADVT_HDR ( ADVT_CODE, REQS_CODE,ADVT_POSITION, ADVT_NOOFVAC ) "
					   +"  VALUES ((SELECT NVL(MAX(ADVT_CODE),0)+1  FROM HRMS_REC_ADVT_HDR),? ,? ,? ) "; 
		
		case 2:  return "  INSERT INTO HRMS_REC_ADVT_DTL ( ADVT_DTL_CODE, ADVT_CODE, ADVT_MODE, ADVT_NAME, ADVT_START_DATE," 
						+" ADVT_END_DATE, ADVT_DETAILS, ADVT_COST) VALUES " 
						+" ( ? , 	(SELECT NVL(MAX(ADVT_CODE),0)  FROM HRMS_REC_ADVT_HDR) " 
						+" ,? ,? ,TO_DATE(?,'DD-MM-YYYY') ,TO_DATE(?,'DD-MM-YYYY'), ? ,? ) "; 
		
		case 3:  return "  DELETE FROM HRMS_REC_ADVT_DTL WHERE ADVT_CODE =? "; 
 
		case 4:  return "  DELETE FROM  HRMS_REC_ADVT_HDR WHERE ADVT_CODE = ? "; 
		
		case 5:  return " UPDATE HRMS_REC_ADVT_HDR SET  REQS_CODE = ? ,ADVT_POSITION = ? , ADVT_NOOFVAC= ? WHERE ADVT_CODE = ? ";
	  
		case 6:  return "  INSERT INTO HRMS_REC_ADVT_DTL ( ADVT_DTL_CODE, ADVT_CODE, ADVT_MODE, ADVT_NAME, ADVT_START_DATE," 
		+" ADVT_END_DATE, ADVT_DETAILS, ADVT_COST) VALUES " 
		+" ( ? , ? ,? ,? ,TO_DATE(?,'DD-MM-YYYY') ,TO_DATE(?,'DD-MM-YYYY'), ? ,? ) "; 
		 
		default: return " Query not Found ";
		}
	}
}
