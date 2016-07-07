/**
 * 
 */
package org.paradyne.sql.recruitment;

import org.paradyne.lib.SqlBase;

/**
 * @author pankajj
 *
 */
public class EntranceTestMasterModelSql extends SqlBase {
	public String getQuery(int queryCode)
	{
		switch(queryCode)
		{
		case 1:  return " INSERT INTO HRMS_ENTR_EXAM (EXAM_CODE,EXAM_NAME,EXAM_TOTAL_MARKS,EXAM_PASS_MARKS) "
					   +" VALUES((SELECT NVL(MAX(EXAM_CODE),0) + 1 FROM HRMS_ENTR_EXAM),?,?,?) ";
		
		case 2:  return " UPDATE HRMS_ENTR_EXAM SET EXAM_NAME =?,EXAM_TOTAL_MARKS = ?,EXAM_PASS_MARKS = ? WHERE EXAM_CODE = ?";
		
		case 3:  return " DELETE FROM HRMS_ENTR_EXAM WHERE EXAM_CODE = ? ";
		case 4:	 return " SELECT EXAM_CODE,EXAM_NAME,EXAM_TOTAL_MARKS,EXAM_PASS_MARKS FROM HRMS_ENTR_EXAM ORDER BY EXAM_CODE";
		case 5:	 return " SELECT EXAM_CODE,EXAM_NAME,EXAM_TOTAL_MARKS,EXAM_PASS_MARKS FROM HRMS_ENTR_EXAM ORDER BY EXAM_CODE";
		default: return " Query not Found ";
		}
	}

}
