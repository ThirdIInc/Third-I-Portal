package org.paradyne.sql.attendance;

import org.paradyne.lib.SqlBase;

/**
 * @author balajim
 *
 */

public class CompOffModelSql extends SqlBase {	
	public String getQuery(int id) {
		switch (id) {
    		case 1 : return " INSERT INTO HRMS_COMPOFF_HDR(COMP_ID, COMP_EMPID, COMP_APPDATE, COMP_APPROVER, COMP_COMMENTS, COMP_ALT_APPROVER) " +
    		" VALUES(?, ?, TO_DATE(?, 'DD-MM-YYYY'), ?, ?, ?) ";
    		
    		case 2 : return " INSERT INTO HRMS_COMPOFF_DTL(COMPDTL_COMPID, COMPDTL_PROJECT, COMPDTL_PROJECTDATE, COMPDTL_STARTTIME, COMPDTL_ENDTIME ,COMPDTL_EMPID) " +  
    		" VALUES((SELECT NVL(MAX(COMP_ID), 0) FROM HRMS_COMPOFF_HDR), ?, TO_DATE(?, 'DD-MM-YYYY'), ?, ? ,?) ";
    		
    		case 3 : return " UPDATE HRMS_COMPOFF_HDR SET COMP_EMPID = ?, COMP_APPDATE = TO_DATE(?, 'DD-MM-YYYY'), COMP_APPROVER = ?, " +
    		" COMP_COMMENTS = ?, COMP_ALT_APPROVER = ? WHERE COMP_ID = ? ";
    		
    		case 4 : return " INSERT INTO HRMS_COMPOFF_DTL(COMPDTL_COMPID, COMPDTL_PROJECT, COMPDTL_PROJECTDATE, COMPDTL_STARTTIME, COMPDTL_ENDTIME,COMPDTL_EMPID ) " +  
    		" VALUES(?, ?, TO_DATE(?, 'DD-MM-YYYY'), ?, ?,?) ";
    
    		default : return "Framework could not EXECUTE the query number specified";
		}
	}
}