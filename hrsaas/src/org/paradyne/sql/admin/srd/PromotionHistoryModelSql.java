package org.paradyne.sql.admin.srd;

import org.paradyne.lib.SqlBase;

/**
 * @author Prajakta.Bhandare
 * @date 09 Jan 2013
 *
 */
public class PromotionHistoryModelSql extends SqlBase {

	public String getQuery(int id) {

		switch (id) {

		case 1:
			return 	"INSERT INTO HRMS_PROMO_HISTORY (PROMO_ID,EMP_ID ,PROMO_POST,"
					+" PROMO_FROM_DT,PROMO_DEPT,PROMO_BRANCH,PROMO_CTC)"
					+" VALUES((SELECT NVL(MAX(PROMO_ID),0)+1 FROM HRMS_PROMO_HISTORY),?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?)";

		case 2:
			return 	"SELECT NVL(HRMS_RANK.RANK_NAME,''),NVL(TO_CHAR(PROMO_FROM_DT,'DD-MM-YYYY'),''), "
			+" PROMO_ID,HRMS_DEPT.DEPT_NAME,HRMS_CENTER.CENTER_NAME,"
			+" PROMO_CTC FROM HRMS_PROMO_HISTORY "
			+" LEFT JOIN HRMS_RANK ON (HRMS_PROMO_HISTORY.PROMO_POST=HRMS_RANK.RANK_ID)" 
			+" LEFT JOIN HRMS_CENTER ON (HRMS_PROMO_HISTORY.PROMO_BRANCH=HRMS_CENTER.CENTER_ID)"
			+" LEFT JOIN HRMS_DEPT ON (HRMS_PROMO_HISTORY.PROMO_DEPT=HRMS_DEPT.DEPT_ID) "
			+" WHERE EMP_ID =? ORDER BY PROMO_ID"; 

		case 3:
			return 	"SELECT NVL(HRMS_RANK.RANK_NAME,''), PROMO_POST, TO_CHAR(PROMO_FROM_DT,'DD-MM-YYYY')," 
					+" HRMS_DEPT.DEPT_NAME,PROMO_DEPT,HRMS_CENTER.CENTER_NAME,PROMO_BRANCH, PROMO_CTC,PROMO_ID "
					+" FROM HRMS_PROMO_HISTORY "
					+" LEFT JOIN HRMS_RANK ON (HRMS_PROMO_HISTORY.PROMO_POST=HRMS_RANK.RANK_ID) "
					+" LEFT JOIN HRMS_DEPT ON (HRMS_PROMO_HISTORY.PROMO_DEPT=HRMS_DEPT.DEPT_ID) "
					+" LEFT JOIN HRMS_CENTER ON (HRMS_PROMO_HISTORY.PROMO_BRANCH= HRMS_CENTER.CENTER_ID)" 
					+" WHERE PROMO_ID =? AND HRMS_PROMO_HISTORY.EMP_ID =?";

		case 4:
			return " UPDATE HRMS_PROMO_HISTORY SET PROMO_POST = ?,PROMO_FROM_DT = TO_DATE(?,'DD-MM-YYYY'), "
					+ " PROMO_DEPT = ?,PROMO_BRANCH = ?,PROMO_CTC = ? "
					+ " WHERE PROMO_ID = ? AND EMP_ID=? ";

		case 5:
			return " DELETE FROM  HRMS_PROMO_HISTORY WHERE PROMO_ID = ? AND EMP_ID=? ";

		case 7:
			return " SELECT HRMS_RANK.RANK_NAME,HRMS_DEPT.DEPT_NAME," +
					" TO_CHAR(PROM_DATE,'DD-MM-YYYY')," +
					" TO_CHAR(EFFECT_DATE,'DD-MM-YYYY'),PROM_GR0SSAMT,OLD_CTC" +
					"  FROM HRMS_PROMOTION INNER JOIN HRMS_RANK ON" +
					" (HRMS_PROMOTION.PRO_DESG=HRMS_RANK.RANK_ID) LEFT JOIN HRMS_DEPT ON " +
					" (HRMS_PROMOTION.PRO_DEPT=HRMS_DEPT.DEPT_ID) " +
					" LEFT JOIN HRMS_CENTER ON (HRMS_PROMOTION.PRO_BRANCH= HRMS_CENTER.CENTER_ID) " +
					" WHERE EMP_CODE=? AND LOCK_FLAG='Y'  ORDER BY PROM_CODE ";


		default:
			return " query no. is not specified  ";
		}
	}

}
