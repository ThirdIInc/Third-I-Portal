package org.paradyne.sql.Training;

import org.paradyne.lib.SqlBase;

public class TrainingAssessmentModelSql extends SqlBase {
	
	public String getQuery(int queryCode){
		switch(queryCode){
		
		case 1:  return "  INSERT INTO HRMS_TRN_ASSHDR(TRNASS_CODE,TRNASS_ECODE,TRNASS_DESC,TRNASS_TRNID)  "
		+"  VALUES((SELECT NVL(MAX(TRNASS_CODE),0)+1 FROM HRMS_TRN_ASSHDR),?,?,?)	";
		
		
		case 2: return " DELETE FROM HRMS_TRN_ASSHDR WHERE TRNASS_CODE=?";
		
		case 3:return " DELETE FROM  HRMS_TRNASSDTL WHERE TRNASSDTL_CODE=?";
	
		case 4:return " UPDATE  HRMS_TRN_ASSHDR SET TRNASS_DESC=? WHERE TRNASS_CODE=?";
		
		default: return " Query Not Found ";
		}
	}
}