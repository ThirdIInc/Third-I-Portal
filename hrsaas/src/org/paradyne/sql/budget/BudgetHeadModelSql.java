/**
 * Bhushan
 * Dec 18, 2007
**/

package org.paradyne.sql.budget;

import org.paradyne.lib.SqlBase;

public class BudgetHeadModelSql extends SqlBase {

	public String getQuery(int id)
	{
		try
		{
			switch(id) 
			{
				case 1 : return " INSERT INTO HRMS_BDGT_HD VALUES((SELECT NVL(MAX(BDGT_HD_CODE),0)+1 FROM HRMS_BDGT_HD), ?) ";
				
				case 2 : return " DELETE FROM HRMS_BDGT_HD WHERE BDGT_HD_CODE = ? ";
				
				case 3 : return " UPDATE HRMS_BDGT_HD SET BDGT_HD_NAME = ? WHERE BDGT_HD_CODE = ? ";
				
				case 4 : return " SELECT BDGT_HD_NAME FROM HRMS_BDGT_HD ORDER BY BDGT_HD_CODE ";
				
				case 5 : return " SELECT BDGT_HD_CODE, BDGT_HD_NAME FROM HRMS_BDGT_HD ORDER BY BDGT_HD_CODE ";
				
				
				default : return "";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}