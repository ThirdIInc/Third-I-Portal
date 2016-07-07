package org.paradyne.sql.Asset;

import org.paradyne.lib.SqlBase;

/**
 * 
 * @author Krishna
 * 
 */

public class VendorMasterModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {

		case 1:
			return "  INSERT INTO HRMS_VENDOR(VENDOR_CODE,VENDOR_NAME,VENDOR_ADDRESS,VENDOR_CONTACT_NO,VENDOR_EMAIL,VENDOR_CITY,VENDOR_STATE,VENDOR_PINCODE,VENDOR_ISACTIVE,VENDOR_TYPE)"
					+ "  VALUES((SELECT NVL(MAX(VENDOR_CODE),0)+1 FROM HRMS_VENDOR),?,?,?,?,?,?,?,?,?)";
		case 2:
			return "  UPDATE HRMS_VENDOR SET VENDOR_NAME=?,"
					+ " VENDOR_ADDRESS=?,VENDOR_CONTACT_NO=?,VENDOR_EMAIL=?,VENDOR_CITY=?,VENDOR_STATE=?,VENDOR_PINCODE=?,VENDOR_ISACTIVE=?,VENDOR_TYPE=? WHERE VENDOR_CODE =?  ";
		case 3:
			return "DELETE FROM HRMS_VENDOR WHERE VENDOR_CODE=?";

		case 4:
			return " SELECT VENDOR_CODE,VENDOR_NAME,CASE WHEN VENDOR_ISACTIVE ='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_VENDOR";

		default:
			return "Framework could not the query number specified";
		}

	}

}
