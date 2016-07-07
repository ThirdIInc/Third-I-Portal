package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class AccountCategoryModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {
		case 1: return " INSERT INTO HRMS_ACCOUNTING_CATEGORY (ACCOUNT_CATEGORY_ID, ACCOUNT_CATEGORY_NAME,ACCOUNT_CATEGORY_ABBR,IS_ACTIVE)"
					+ " VALUES((SELECT NVL(MAX(ACCOUNT_CATEGORY_ID),0)+1 FROM HRMS_DIVISION ),?,?,?) ";

		case 2: return " UPDATE HRMS_ACCOUNTING_CATEGORY SET ACCOUNT_CATEGORY_NAME=?,ACCOUNT_CATEGORY_ABBR=?,IS_ACTIVE=? WHERE DIV_ID=? ";

		case 3: return " DELETE FROM HRMS_ACCOUNTING_CATEGORY WHERE ACCOUNT_CATEGORY_ID = ? ";

		case 4:	return " SELECT ACCOUNT_CATEGORY_NAME,ACCOUNT_CATEGORY_ABBR from "
					+ " WHERE ACCOUNT_CATEGORY_ID=?" ;
			
		case 5: return " SELECT ACCOUNT_CATEGORY_ID, ACCOUNT_CATEGORY_NAME, ACCOUNT_CATEGORY_ABBR,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') " +
				" from HRMS_ACCOUNTING_CATEGORY ORDER BY UPPER(ACCOUNT_CATEGORY_NAME) ";
			
		default: return "Query is not specified";
		}
	}
}