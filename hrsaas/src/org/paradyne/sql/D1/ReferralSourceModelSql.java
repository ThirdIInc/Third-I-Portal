package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

public class ReferralSourceModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {

		case 1:
			return " INSERT INTO HRMS_D1_REFERRAL(REFERRAL_ID,REFERRAL_SOURCE_NAME) "
					+ " VALUES(?,?)";
		case 2:
			return " UPDATE HRMS_D1_REFERRAL SET REFERRAL_SOURCE_NAME=? WHERE REFERRAL_ID=?  ";
		
		case 3:
			return" SELECT REFERRAL_ID,REFERRAL_SOURCE_NAME FROM  HRMS_D1_REFERRAL ORDER BY REFERRAL_SOURCE_NAME ";
		
		case 4:
			return " DELETE from HRMS_D1_REFERRAL WHERE REFERRAL_ID=?  ";
		
		
		default:
			return "Framework could not the query number specified";

		
		
		
		}
	}

}
