package org.paradyne.model.common;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.common.ConfigurationHome;
import org.paradyne.lib.ModelBase;

public class ConfigurationHomeModel extends ModelBase{

	public void getConfigureMenuList(HttpServletRequest request, ConfigurationHome configHome, String menuType) {
	
		String menuQuery="SELECT DISTINCT MENU_GROUP,MENU_GROUP_ORDER, MENU_IMAGE "
						+ " FROM HRMS_MENU "
						+ " INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE   AND HRMS_PROFILE_DTL.PROFILE_CODE ='"
						+ configHome.getUserProfileId()
						+ "')"
						+ " WHERE MENU_TYPE='"
						+ menuType
						+ "' AND MENU_ISRELEASED='Y' "
						+ " ORDER BY MENU_GROUP_ORDER ";
		Object groupData[][] = getSqlModel().getSingleResult(menuQuery);
		String query = " SELECT HRMS_MENU.MENU_CODE, HRMS_MENU.MENU_NAME, MENU_LINK,MENU_IMAGE ,MENU_KEYWORDS , MENU_GROUP FROM HRMS_MENU "
					+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
					+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
					+ "	OR PROFILE_GENERAL_FLAG ='Y'))"
					+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE ="+configHome.getUserProfileId()+" and MENU_TYPE='" + menuType + "' "
					+ " AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP_ORDER,MENU_PLACEINMENU";
		
		Object data[][] = getSqlModel().getSingleResult(query);
		request.setAttribute("serviceMenulst", data);
		request.setAttribute("groupData", groupData);
	}

}
