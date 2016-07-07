/**
 * 
 */
package org.paradyne.sql.Asset;

import org.paradyne.lib.SqlBase;

/**
 * @author mangeshj
 *
 */
public class RateListModelSql extends SqlBase {

	public String getQuery(int id){
		switch (id) {
		
							
		case 1 : return " SELECT RATELIST_PRICE,ASSET_SUBTYPE_NAME,RATELIST_ITEM_CODE,UNIT_NAME FROM HRMS_ASSET_RATELIST_DTL "
						+" INNER JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE= HRMS_ASSET_RATELIST_DTL.RATELIST_ITEM_CODE) "
						+" INNER JOIN HRMS_UNIT_MASTER ON (HRMS_UNIT_MASTER.UNIT_CODE=HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT)"
						+" WHERE RATELIST_CODE=?";
		
		case 2 : return " INSERT INTO HRMS_ASSET_RATELIST (RATELIST_VENDOR,RATELIST_CODE) VALUES(?,(SELECT NVL(MAX(RATELIST_CODE),0)+1 FROM HRMS_ASSET_RATELIST ))";
		
		case 3 : return " UPDATE HRMS_ASSET_RATELIST SET RATELIST_VENDOR=? WHERE RATELIST_CODE=? ";
		
		case 4 : return " INSERT INTO HRMS_ASSET_RATELIST_DTL(RATELIST_CODE,RATELIST_ITEM_CODE,RATELIST_PRICE) VALUES(?,?,?)";
		
		case 5 : return " SELECT RATELIST_CODE FROM HRMS_ASSET_RATELIST WHERE RATELIST_VENDOR =? ";
						
		default : return "Framework could not the query number specified";			
		}
	}
}
