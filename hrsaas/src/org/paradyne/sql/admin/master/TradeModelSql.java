package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author pranali 
 * Date 23-04-07
 */
public class TradeModelSql extends SqlBase
{
	public String getQuery(int id) 
	{
		switch(id)
		{
						
			
			case 1: return " INSERT INTO HRMS_TRADE (TRADE_CODE,TRADE_NAME,TRADE_ABBR,TRADE_DESC,TRADE_PARENT_CODE) " 
				           +"VALUES((SELECT NVL(MAX(TRADE_CODE),0)+1 FROM HRMS_TRADE ),?,?,?,?)";
			
			case 2: return " UPDATE HRMS_TRADE SET TRADE_NAME=?,TRADE_ABBR=?, TRADE_DESC=?, TRADE_PARENT_CODE=? WHERE TRADE_CODE=?";
			
			case 3: return " DELETE FROM HRMS_TRADE WHERE TRADE_CODE=?";
			
			case 4: return " SELECT HRMS_TRADE.TRADE_CODE,HRMS_TRADE.TRADE_NAME,HRMS_TRADE.TRADE_ABBR,T1.TRADE_NAME,HRMS_TRADE.TRADE_DESC,HRMS_TRADE.TRADE_PARENT_CODE " 

		           +" FROM HRMS_TRADE "
				   +" LEFT JOIN HRMS_TRADE  T1 ON (T1.TRADE_CODE = HRMS_TRADE .TRADE_PARENT_CODE)" 
				   +" WHERE HRMS_TRADE.TRADE_CODE=? "
				   +" ORDER BY HRMS_TRADE .TRADE_CODE ";
			
			case 5: return " SELECT TRADE_CODE,TRADE_NAME,TRADE_ABBR,TRADE_DESC,TRADE_PARENT_CODE FROM HRMS_TRADE ORDER BY TRADE_CODE " ;
			
			case 6: return " SELECT TRADE_CODE,TRADE_NAME,TRADE_ABBR,TRADE_DESC,TRADE_PARENT_CODE FROM HRMS_TRADE order by upper(TRADE_NAME) ";
			
			default : return "Framework could not the query number specified";
			
		}
	}
	

}
