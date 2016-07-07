/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.MenuRelease;
import org.paradyne.lib.ModelBase;

/**
 * @author AA0563
 *
 */
public class MenuReleaseModel extends ModelBase {

	public String[][] getMenuList(MenuRelease menuRelease) {
		String query = "SELECT MENU_CODE,MENU_NAME FROM HRMS_MENU WHERE MENU_PARENT_CODE=0 ORDER BY MENU_TABORDER,MENU_CODE ";
		Object[][] obj = getSqlModel().getSingleResult(query);
		String[][] strObj = null;
		if (obj != null) {
			if (obj.length > 0) {
				strObj = new String[obj.length][2];

				

				for (int i = 0; i < strObj.length; i++) {// no.of rows
					for (int j = 0; j < strObj[0].length; j++) {// no.of columns
						strObj[i][j] = String.valueOf(obj[i][j]);
					}// end of j loop
				}// end of i loop
			}// end of if
			else {
				strObj = new String[0][0];
			}// end of else
		}// end of nested if
		return strObj;

	}

	public String[][] getMenuItems(String menuCode, MenuRelease menuRelease) {

		String query = "SELECT  HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_PARENT_CODE,HRMS_MENU.MENU_NAME "
				+ " FROM HRMS_MENU  "
				+ "  START WITH HRMS_MENU.MENU_CODE ="
				+ menuCode
				+ "  CONNECT BY PRIOR  HRMS_MENU.MENU_CODE=HRMS_MENU.MENU_PARENT_CODE "
				+ "  ORDER BY MENU_PLACEINMENU,HRMS_MENU.MENU_CODE ";

		Object[][] obj = getSqlModel().getSingleResultInsensitive(query);
		String[][] strObj = null;
		if (obj.length > 0) {
			strObj = new String[obj.length][3];

			

			for (int i = 0; i < strObj.length; i++) {// no.of rows
				for (int j = 0; j < strObj[0].length; j++) {// no.of columns
					strObj[i][j] = String.valueOf(obj[i][j]);
				}// end of j loop
			}// end of i loop
		}// end of if
		else {
			strObj = new String[0][0];
		}// end of else
		return strObj;
	}

	public boolean saveMenuFlags(MenuRelease menuRelease, String[] insertFlg) {
		// TODO Auto-generated method stub
		
		System.out.println("Profile id -------"+ menuRelease.getProfileId());

			String query = " SELECT distinct HRMS_MENU.MENU_CODE ,HRMS_MENU.MENU_NAME FROM HRMS_MENU "
						 + " WHERE HRMS_MENU.MENU_CODE !=0 "
						 + " START WITH HRMS_MENU.MENU_CODE = "+ menuRelease.getSelMenuCode()
						 + " CONNECT BY PRIOR  HRMS_MENU.MENU_CODE=HRMS_MENU.MENU_PARENT_CODE ";

			Object[][] menuObj = getSqlModel().getSingleResultInsensitive(query);
			
			Object[][] deleteObj = new Object[menuObj.length][2];
			Object[][] menuInsertObj = new Object[menuObj.length][2];
			for (int i = 0; i < menuObj.length; i++) {
				// FIRST SET ALL FLAG AS "N"
				menuInsertObj[i][0] = String.valueOf(menuObj[i][0]);
				deleteObj[i][0] = "N";
				deleteObj[i][1] = String.valueOf(menuObj[i][0]);
				menuInsertObj[i][1] = "N";
				
				// SET INSERT FLAG

				if (insertFlg != null) {

					for (int j = 0; j < insertFlg.length; j++) {
						if (String.valueOf(menuInsertObj[i][0]).equals(insertFlg[j])) {
							deleteObj[i][0] = "Y";
							menuInsertObj[i][1] = "Y";
						}// end of if

					}// end of j loop
				}// end of if
				
				System.out.println("Objectttttttttttt"+ deleteObj[i][0]+"-----------"+deleteObj[i][1]);
			}// end of i loop

			
			// INSERT INTO PROFILE
			String insertQuery = "UPDATE HRMS_MENU set MENU_ISRELEASED=? where MENU_code=?";
			return getSqlModel().singleExecute(insertQuery, deleteObj);

			
	}

	public String[][] getProfileMenus(MenuRelease menuRelease) {
		// TODO Auto-generated method stub

		String query = "  SELECT distinct HRMS_MENU.MENU_CODE,MENU_ISRELEASED,MENU_PLACEINMENU"
				+ " FROM HRMS_MENU  "
				+ "  WHERE HRMS_MENU.MENU_CODE !=0"
				+ " START WITH HRMS_MENU.MENU_CODE ="
				+ menuRelease.getSelMenuCode()
				+ " CONNECT BY PRIOR  HRMS_MENU.MENU_CODE=HRMS_MENU.MENU_PARENT_CODE "
				+ " ORDER BY MENU_PLACEINMENU, HRMS_MENU.MENU_CODE   ";

		Object[][] obj = getSqlModel().getSingleResultInsensitive(query);
		String[][] strObj = null;
		if (obj.length > 0) {
			strObj = new String[obj.length][obj[0].length];

			

			for (int i = 0; i < strObj.length; i++) {// no.of rows
				for (int j = 0; j < strObj[0].length; j++) {// no.of columns
					strObj[i][j] = String.valueOf(obj[i][j]);
				}// end of j loop
			}// end of i loop
		}// end of if
		else {
			strObj = new String[0][0];
		}// end of else
		return strObj;
	}

}
