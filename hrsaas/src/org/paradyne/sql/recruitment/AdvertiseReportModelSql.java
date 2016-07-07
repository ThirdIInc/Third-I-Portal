package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

public class AdvertiseReportModelSql extends SqlBase {

	public String getQuery(int id) {
		// TODO Auto-generated method stub
		switch(id)
		{
		
			case 1: return " INSERT INTO HRMS_REC_ADVREP_FILTERS (ADVREP_CODE, ADVREP_REQ_CODE, ADVREP_POSITION_CODE,ADVREP_DATEOPTION, "  
							+" ADVREP_REQDATE_FROM, ADVREP_REQSTATUS, ADVREP_REPOPTION, ADVREP_NAME, ADVREP_REQDATE_TO, ADVREP_USEREMPID, ADVREP_MODE_ADVERTISE," 
							+" ADVREP_SELECTED_REQ,ADVREP_RADIO_OPTION) VALUES (( SELECT NVL(MAX(ADVREP_CODE),0)+1 FROM HRMS_REC_ADVREP_FILTERS ),? ,? ,? ,TO_DATE(?,'DD-MM-YYYY') , ?, " 
							+" ? , ?, TO_DATE(?,'DD-MM-YYYY'), ? ,? ,? ,?) ";
			
			case 2: return "  INSERT INTO HRMS_REC_ADVREP_SORT ( ADVREP_CODE, SORT_BY, SORT_BY_ORDER, SORT_THENBY, SORT_THENBY_ORDER, SORT_THENBY1, "
					 	  +" SORT_THENBY1_ORDER) VALUES ( ( SELECT NVL(MAX(ADVREP_CODE),0) FROM HRMS_REC_ADVREP_FILTERS ) ,? ,? , ?, ?, ? , ? ) ";
			
			case 3: return " INSERT INTO HRMS_REC_ADVREP_COLDEF ( ADVREP_CODE, COL_REQNAME, COL_NUMVAC, COL_MODEADVERTISE, COL_AGENCYNAME, COL_ADVDATE, " 
					+" COL_ADVCOST, COL_ONLINERESP,COL_POSITION) VALUES (( SELECT NVL(MAX(ADVREP_CODE),0) FROM HRMS_REC_ADVREP_FILTERS ) ,? ,? ,?,? ,? ,? , ?,?)  ";
						
			case 4: return " INSERT INTO HRMS_REC_ADVREP_ADV ( ADVREP_CODE, ADV_NUMVAC_OPTION, ADV_NUMVAC,  ADV_ONLINERESP_OPTION," +
						  " ADV_ONLINERESP, ADV_COST_OPTION,  ADV_COST)VALUES (( SELECT NVL(MAX(ADVREP_CODE),0) FROM HRMS_REC_ADVREP_FILTERS ) ,? ,? ,? ,? ,? ,? ) ";
			
			//case 5: return " INSERT INTO HRMS_REC_VACREP_REQS (VACREP_CODE, VACREP_REQS_CODE) VALUES (( SELECT NVL(MAX(VACREP_CODE),0)FROM HRMS_REC_VACREP_FILTERS) ,? ) ";
			
			case 6: return " UPDATE HRMS_REC_ADVREP_FILTERS SET ADVREP_REQ_CODE = ?, ADVREP_POSITION_CODE = ? ,ADVREP_DATEOPTION = ?, "  
						+" ADVREP_REQDATE_FROM = TO_DATE(?,'DD-MM-YYYY'), ADVREP_REQSTATUS = ?, ADVREP_REPOPTION = ?, ADVREP_NAME = ? , ADVREP_REQDATE_TO = TO_DATE(?,'DD-MM-YYYY'), ADVREP_USEREMPID = ? , ADVREP_MODE_ADVERTISE = ? ," 
						+" ADVREP_SELECTED_REQ = ? , ADVREP_RADIO_OPTION =? WHERE ADVREP_CODE =? "; 
			
			case 7: return "  UPDATE HRMS_REC_ADVREP_SORT SET  SORT_BY = ? , SORT_BY_ORDER = ? , SORT_THENBY = ? , SORT_THENBY_ORDER = ? , SORT_THENBY1 = ? , "
						+" SORT_THENBY1_ORDER = ?  WHERE ADVREP_CODE = ?"; 
			
			case 8: return " UPDATE HRMS_REC_ADVREP_COLDEF SET COL_REQNAME = ?, COL_NUMVAC = ?, COL_MODEADVERTISE = ? , COL_AGENCYNAME = ? , COL_ADVDATE = ? , " 
			+" COL_ADVCOST = ? , COL_ONLINERESP = ? ,COL_POSITION = ? WHERE ADVREP_CODE = ?  ";
			
			case 9: return " UPDATE  HRMS_REC_ADVREP_ADV SET ADV_NUMVAC_OPTION = ? , ADV_NUMVAC = ?,  ADV_ONLINERESP_OPTION = ?," 
			      +" ADV_ONLINERESP= ?, ADV_COST_OPTION = ?,  ADV_COST = ? WHERE ADVREP_CODE = ?";

			//case 10: return " UPDATE HRMS_REC_VACREP_REQS SET  VACREP_REQS_CODE = ? WHERE VACREP_CODE = ?";
			
			default:return " FRAMEWORK COULD NOT EXECUTE THE QUERY NUMBER SPECIFIED"; 
		}
	
		
	}
	
}
