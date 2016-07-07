package org.paradyne.sql.admin.srd;

import org.paradyne.lib.SqlBase;

/**
 * @author riteshr
 * updated By
 * @author Prajakta.Bhandare
 * @date 07 Jan 2013
 */
public class PayHistoryModelSql extends SqlBase {

	public String getQuery(int id) {

		switch (id) {

		case 1:
			return "INSERT INTO HRMS_EMP_INCR (INCR_CODE,EMP_ID,INCR_PAY_TYPE,INCR_NEW_CTC,INCR_OLD_CTC,INCR_DATE)"
					+ " VALUES((SELECT NVL(MAX(INCR_CODE),0)+1 FROM HRMS_EMP_INCR),UPPER(?),?,?,?,TO_DATE(?,'DD-MM-YYYY'))";

		case 2:
			return "SELECT getValue('incrementType',INCR_PAY_TYPE), "
					+ " INCR_NEW_CTC,INCR_OLD_CTC,"
					+ "  TO_CHAR(INCR_DATE,'DD-MM-YYYY'),INCR_CODE"
					+ "  FROM HRMS_EMP_INCR"
					+ " WHERE EMP_ID = ? ORDER BY INCR_CODE";

		case 3:
			return " SELECT INCR_PAY_TYPE,INCR_NEW_CTC,INCR_OLD_CTC,"
					+ " TO_CHAR(INCR_DATE,'DD-MM-YYYY')"
					+ " FROM HRMS_EMP_INCR WHERE  INCR_CODE = ? "
					+ " AND EMP_ID=?";

		case 4:
			return " UPDATE HRMS_EMP_INCR  SET INCR_PAY_TYPE = ?,INCR_NEW_CTC = ?,INCR_OLD_CTC = ?, "
					+ " INCR_DATE = TO_DATE(?,'DD-MM-YYYY')"
					+ " WHERE INCR_CODE = ? ";

		case 5:
			return " DELETE FROM  HRMS_EMP_INCR  WHERE INCR_CODE = ? ";

		case 7:
			return " SELECT (CENTER_NAME),RANK_NAME,EMP_ID,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '|| EMP_MNAME|| '  ' ||EMP_LNAME),EMP_TOKEN FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ " INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ " WHERE EMP_ID = ?";

		case 8: 
			return " SELECT INCR_PAY_TYPE,INCR_NEW_CTC,INCR_OLD_CTC,"
					+ " TO_CHAR(INCR_DATE,'DD-MM-YYYY')"
					+ " FROM HRMS_EMP_INCR WHERE  EMP_ID = ? ";
			
		default:
			return " query no. is not specified  ";
		}
	}

}
