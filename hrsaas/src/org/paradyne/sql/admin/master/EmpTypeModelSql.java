/**
 * @author lakkichand
 * @date 24 Apr 2007
 */

package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class EmpTypeModelSql extends SqlBase{

	public String getQuery(int id){
		switch (id){
    		case 1: return " INSERT INTO HRMS_EMP_TYPE(TYPE_ID, TYPE_NAME, TYPE_ABBR, TYPE_ESI, TYPE_PT, TYPE_PF, IS_ACTIVE) " +
    		" VALUES(?, ?, ?, ?, ?, ?, ?) ";
    		
    		case 2: return " UPDATE HRMS_EMP_TYPE SET TYPE_NAME =?, TYPE_ABBR =?, TYPE_ESI =?, TYPE_PT =?, TYPE_PF =?,IS_ACTIVE=? "
    	                	+ " WHERE TYPE_ID = ? ";
    		
    		case 3: return " DELETE FROM HRMS_EMP_TYPE WHERE TYPE_ID = ? ";
    		
    		case 4: return " SELECT TYPE_ID, TYPE_NAME, TYPE_ABBR, TYPE_ESI, TYPE_PT, TYPE_PF,IS_ACTIVE  FROM HRMS_EMP_TYPE WHERE TYPE_ID = ? ";
    		
    		case 5: return " SELECT TYPE_ID, TYPE_NAME, TYPE_ABBR,CASE WHEN IS_ACTIVE='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_EMP_TYPE ORDER BY upper(TYPE_NAME) ";
    		
    		case 6: return " SELECT TYPE_NAME FROM HRMS_EMP_TYPE";
    		
    		default:return "Framework could not find the query number specified";
		}		
	}
}