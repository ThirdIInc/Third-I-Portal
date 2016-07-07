package org.paradyne.sql.TravelManagement.Master;

import org.paradyne.lib.SqlBase;

/**
 * @author ayyappa
 *
 */

public class CurrencyMasterModelSql extends SqlBase {
	@Override
	public String getQuery(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case 1: return " INSERT INTO HRMS_CURRENCY ( CURRENCY_ID, CURRENCY_NAME, CURRENCY_ABBR, CURRENCY_DESC, CURRENCY_STATUS ) "
						+ " VALUES(NVL((SELECT MAX(CURRENCY_ID) FROM HRMS_CURRENCY ), 0) + 1 , ?, ?, ?, ? ) ";
			
		case 2: return " UPDATE HRMS_CURRENCY SET CURRENCY_NAME=?, CURRENCY_ABBR=?, CURRENCY_DESC=?, CURRENCY_STATUS=? "
						+ " WHERE CURRENCY_ID=? ";
		
		case 3: return " DELETE FROM HRMS_CURRENCY WHERE CURRENCY_ID=? ";
		
		case 4: return "SELECT CURRENCY_ID, CURRENCY_NAME, CURRENCY_ABBR, CURRENCY_DESC, DECODE(CURRENCY_STATUS,'A','Active','D','De Active') AS STATUS  "
						+ " FROM HRMS_CURRENCY ORDER BY CURRENCY_ID ";
		
		case 5: return "SELECT CURRENCY_ID, CURRENCY_NAME, CURRENCY_ABBR, CURRENCY_DESC, CURRENCY_STATUS "
						+ " FROM HRMS_CURRENCY WHERE CURRENCY_ID  = ? ";
		
		default: return "Framework could not the query number specified";
			
		}
		
	}

}
