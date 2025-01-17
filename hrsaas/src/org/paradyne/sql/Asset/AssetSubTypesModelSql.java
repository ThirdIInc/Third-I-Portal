/**
 * 
 */
package org.paradyne.sql.Asset;

import org.paradyne.lib.SqlBase;

/**
 * @author mangeshj
 *
 */
public class AssetSubTypesModelSql extends SqlBase {
	public String getQuery(int id) {
		switch(id) {
		
		case 1: return "INSERT INTO HRMS_ASSET_SUBTYPE(ASSET_SUBTYPE_CODE,ASSET_CATEGORY_CODE,ASSET_SUBTYPE_NAME,ASSET_SUBTYPE_FLAG,ASSET_SUBTYPE_UNIT, "
						+" ASSET_SUBTYPE_LEADTIME,ASSET_REORDER_LEVEL,ASSET_INVENTORY_TYPE,ASSET_SUBTYPE_ISACTIVE,ASSET_SAFETY_STOCK)VALUES(NVL((SELECT MAX(ASSET_SUBTYPE_CODE) FROM HRMS_ASSET_SUBTYPE),0)+1,?,?,?,?,?,?,?,?,?)"; 
		
		case 2 : return "UPDATE HRMS_ASSET_SUBTYPE SET ASSET_CATEGORY_CODE =?,ASSET_SUBTYPE_NAME=?,ASSET_SUBTYPE_FLAG=?,ASSET_SUBTYPE_UNIT=?,ASSET_SUBTYPE_LEADTIME =?,"
						+" ASSET_REORDER_LEVEL=?,ASSET_INVENTORY_TYPE=?,ASSET_SUBTYPE_ISACTIVE=?,ASSET_SAFETY_STOCK=? WHERE ASSET_SUBTYPE_CODE =?";
		
		case 3 : return "SELECT HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE,NVL(ASSET_CATEGORY_NAME,''),ASSET_SUBTYPE_CODE,NVL(ASSET_SUBTYPE_NAME,''),nvl(ASSET_SUBTYPE_FLAG,''), "
						+" ASSET_SUBTYPE_UNIT, ASSET_SUBTYPE_LEADTIME,ASSET_REORDER_LEVEL,nvl(ASSET_INVENTORY_TYPE,''),nvl(ASSET_SUBTYPE_ISACTIVE,''),ASSET_SAFETY_STOCK FROM HRMS_ASSET_SUBTYPE " 
						+" INNER JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE = HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE) "
						+" WHERE ASSET_SUBTYPE_CODE=?" ;
		
		case 4 : return "SELECT nvl(ASSET_CATEGORY_NAME,''), nvl(ASSET_SUBTYPE_CODE,''),nvl(ASSET_SUBTYPE_NAME,'') ,"
						+" DECODE(ASSET_SUBTYPE_FLAG,'R','Returnable','N','Non-Returnable',''),CASE WHEN ASSET_SUBTYPE_ISACTIVE ='Y' THEN 'Yes' ELSE 'No' END FROM HRMS_ASSET_SUBTYPE " 
						+" INNER JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE = HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE) "
						+" ORDER BY ASSET_SUBTYPE_CODE ";
		
		case 5 : return "DELETE FROM HRMS_ASSET_SUBTYPE WHERE ASSET_SUBTYPE_CODE =?";
		
		case 6 : return "SELECT MAX(ASSET_SUBTYPE_CODE) FROM  HRMS_ASSET_SUBTYPE";
		
		case 7 : return "DELETE FROM HRMS_ASSET_PROPERTY_MASTE WHERE ASSET_SUBTYPE =?";
		
		case 8 : return "INSERT INTO HRMS_ASSET_PROPERTY_MASTER(ASSET_SUBTYPE, PROPERTY_ID, PROPERTY_ATTRIBUTE, UNIT_ID) VALUES(?,? "
			+" ,?,?)";
		
		default : return "Framework could not the query number specified";			
		}
	}

}
