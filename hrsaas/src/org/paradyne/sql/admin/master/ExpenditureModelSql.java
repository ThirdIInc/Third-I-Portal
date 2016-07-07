package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class ExpenditureModelSql extends SqlBase {
	public String getQuery(int id) {
		switch(id) {
		case 1: return "INSERT INTO HRMS_DEPUT_EXPEND_TYPE(EXPENDITURE_CODE,EXPENDITURE_NAME) "
                       +" VALUES((SELECT NVL(MAX(EXPENDITURE_CODE),0)+1 FROM HRMS_DEPUT_EXPEND_TYPE),?) "; 
		
		case 2: return " UPDATE HRMS_DEPUT_EXPEND_TYPE SET EXPENDITURE_NAME =? WHERE EXPENDITURE_CODE =? "; 
		
		case 3: return " DELETE FROM HRMS_DEPUT_EXPEND_TYPE WHERE EXPENDITURE_CODE = ?";
		
		case 4: return " SELECT EXPENDITURE_CODE,EXPENDITURE_NAME FROM HRMS_DEPUT_EXPEND_TYPE ORDER BY EXPENDITURE_CODE ";
		
		default : return "Framework could not the query number specified";			
		}
	}
	

}
 