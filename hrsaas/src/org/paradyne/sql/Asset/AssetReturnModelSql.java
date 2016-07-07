package org.paradyne.sql.Asset;

import org.paradyne.lib.SqlBase;

/**
 * 
 * @author krishna
 *
 */

public class AssetReturnModelSql extends SqlBase {

	public String getQuery(int code) {

		switch (code) {

		case 1:
			return " UPDATE HRMS_ASSET_APP_ASSIGNEMENT  SET ASSET_RETURN_DATE =TO_DATE(?,'DD-MM-YYYY'),ASSET_RETURN_FLAG=?  WHERE   ASSET_ITEM_CODE=? ";

		case 2:
			return " UPDATE HRMS_ASSET_MASTER_DTL SET ASSET_ASSISGN_FLAG = ? , ASSET_AVAILABLE=?  "
					+ " WHERE ASSET_ITEM_CODE =?  ";
		default:
			return "no query";
		}

	}

}
