/**
 * Bhushan
 * Dec 19, 2007
**/

package org.paradyne.sql.budget;

import org.paradyne.lib.SqlBase;

public class BudgetPlanningModelSql extends SqlBase 
{
	public String getQuery(int id)
	{
		try 
		{
			switch (id)
			{
				case 1 : return " INSERT INTO HRMS_BDGTPLAN VALUES((SELECT NVL(MAX(BDGTPLAN_CODE),0)+1 " +
				" FROM HRMS_BDGTPLAN), TO_DATE(?,'DD-MM-YYYY'), TO_DATE(?,'DD-MM-YYYY'), ?) ";
				
				case 2 : return " SELECT MAX(BDGTPLAN_CODE) FROM HRMS_BDGTPLAN  ";
				
				case 3 : return " INSERT INTO HRMS_BDGTPLAN_DTL VALUES(?, ?, ?, ?) ";
				
				case 4 : return " SELECT HRMS_BDGTPLAN_DTL.BDGT_HD_CODE, BDGT_HD_NAME, BDGT_AMOUNT " +
				" FROM HRMS_BDGTPLAN_DTL " +
				" INNER JOIN HRMS_BDGT_HD ON(HRMS_BDGT_HD.BDGT_HD_CODE = HRMS_BDGTPLAN_DTL.BDGT_HD_CODE) " +
				" WHERE BDGTPLAN_CODE = ? ORDER BY HRMS_BDGT_HD.BDGT_HD_CODE ";
				
				case 5 : return " UPDATE HRMS_BDGTPLAN SET BDGT_FROM_DATE = TO_DATE(?,'DD-MM-YYYY'), BDGT_TO_DATE = TO_DATE(?,'DD-MM-YYYY'), BDGT_DETAILS = ? " +
				" WHERE BDGTPLAN_CODE = ? ";
				
				case 6 : return " UPDATE HRMS_BDGTPLAN_DTL SET BDGT_BALANCE = BDGT_BALANCE + (NVL(?, 0.0) - BDGT_AMOUNT), BDGT_AMOUNT = ? " +
				" WHERE BDGT_HD_CODE = ? AND BDGTPLAN_CODE = ? ";
								
				case 7 : return " DELETE FROM HRMS_BDGTPLAN WHERE BDGTPLAN_CODE = ? ";
				
				case 8 : return " DELETE FROM HRMS_BDGTPLAN_DTL WHERE BDGTPLAN_CODE = ? ";
				
				case 9 : return " SELECT * FROM HRMS_BDGTPLAN WHERE TO_DATE(?,'DD-MM-YYYY') BETWEEN BDGT_FROM_DATE AND BDGT_TO_DATE  ";
				
				case 10 : return " SELECT TO_CHAR(BDGT_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(BDGT_TO_DATE,'DD-MM-YYYY'), BDGT_DETAILS FROM HRMS_BDGTPLAN WHERE BDGTPLAN_CODE = ? ";
				
				case 11 : return " SELECT ROWNUM ,BDGT_HD_NAME, BDGT_AMOUNT FROM HRMS_BDGTPLAN_DTL " +
				" INNER JOIN HRMS_BDGT_HD ON(HRMS_BDGT_HD.BDGT_HD_CODE = HRMS_BDGTPLAN_DTL.BDGT_HD_CODE) " +
				" WHERE BDGTPLAN_CODE = ? ORDER BY HRMS_BDGT_HD.BDGT_HD_CODE ";
				
				case 12 : return " SELECT * FROM HRMS_BDGTEXP WHERE BDGT_EDATE BETWEEN TO_DATE(?,'DD-MM-YYYY') AND TO_DATE(?,'DD-MM-YYYY') ";
				
				default : return "";
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}