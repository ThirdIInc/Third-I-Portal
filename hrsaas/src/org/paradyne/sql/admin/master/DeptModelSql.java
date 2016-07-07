/**
 * 
 */
package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author lakkichand
 *
 */
public class DeptModelSql extends SqlBase {
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_DEPT (DEPT_ID,DEPT_NAME,DEPT_ABBR,DEPT_DIV_CODE,DEPT_DESC,DEPT_LEVEL,DEPT_PARENT_CODE,IS_ACTIVE) " 
						+ " VALUES((SELECT NVL(MAX(DEPT_ID),0)+1 FROM HRMS_DEPT ),?,?,?,?,?,?,?)";
		
		case 2: return " UPDATE HRMS_DEPT SET DEPT_NAME=?,DEPT_ABBR=?,DEPT_DIV_CODE=?,DEPT_DESC=?,DEPT_LEVEL=?,DEPT_PARENT_CODE=?,IS_ACTIVE=? WHERE DEPT_ID=?";
		
		case 3: return " DELETE FROM HRMS_DEPT WHERE DEPT_ID=?";
		
		case 4: return "  SELECT HRMS_DEPT.DEPT_ID,HRMS_DEPT.DEPT_NAME,D1.DEPT_NAME ,HRMS_DEPT.DEPT_ABBR,HRMS_DEPT.DEPT_DIV_CODE,DIV_NAME,HRMS_DEPT.DEPT_DESC,HRMS_DEPT.DEPT_LEVEL,HRMS_DEPT.DEPT_PARENT_CODE,HRMS_DEPT.IS_ACTIVE " 
						  + " FROM HRMS_DEPT "
						  + " LEFT JOIN HRMS_DEPT D1 ON (D1.DEPT_ID = HRMS_DEPT.DEPT_PARENT_CODE) "
						  + " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_DEPT.DEPT_DIV_CODE)  "
						  + " WHERE HRMS_DEPT.DEPT_ID=?	";
		
		case 5: return  " SELECT  DEPT_ID,DEPT_NAME,DEPT_DESC,DEPT_ABBR,DEPT_DIV_CODE,DIV_NAME,DEPT_PARENT_CODE,DEPT_LEVEL,DECODE(HRMS_DEPT.IS_ACTIVE,'Y','Yes','N','No') "
						+ " FROM HRMS_DEPT "
						+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_DEPT.DEPT_DIV_CODE)"
						+ " ORDER BY  upper(DEPT_NAME)";
		case 6: return  " SELECT  DEPT_ID,nvl(DEPT_NAME,' '),nvl(DIV_NAME,' ')"
		+ " FROM HRMS_DEPT "
		+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_DEPT.DEPT_DIV_CODE)"
		+ " ORDER BY DEPT_ID";

		
		default : return "Framework could not the query number specified";			
		}
	}
}
