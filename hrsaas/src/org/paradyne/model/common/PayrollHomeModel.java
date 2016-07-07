package org.paradyne.model.common;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.common.PayrollHome;
import org.paradyne.lib.ModelBase;

public class PayrollHomeModel extends ModelBase {

	public void getPayrollMenuList(HttpServletRequest request,
			PayrollHome bean, String menuType) {
		try {

			if (menuType == null) {
				menuType = "MN";
			}
			//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE 
			String groupQuery = " select DISTINCT MENU_GROUP,MENU_GROUP_ORDER, MENU_IMAGE from HRMS_MENU INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE   and HRMS_PROFILE_DTL.PROFILE_CODE IN("
					+ bean.getMultipleProfileCode()
					+ "))"
					+ " WHERE MENU_TYPE='"
					+ menuType
					+ "' AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP_ORDER ";
			Object groupData[][] = getSqlModel().getSingleResult(groupQuery);

			String query = " SELECT DISTINCT HRMS_MENU.MENU_CODE, HRMS_MENU.MENU_NAME, MENU_LINK,MENU_IMAGE ,MENU_KEYWORDS , MENU_GROUP,MENU_GROUP_ORDER,MENU_PLACEINMENU FROM HRMS_MENU "
					+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
					+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
					+ "	OR PROFILE_GENERAL_FLAG ='Y'))"
					+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN("+bean.getMultipleProfileCode()+") and MENU_TYPE='" + menuType + "' "
					+ " AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP_ORDER,MENU_PLACEINMENU";

			/*
			 * String query = "SELECT HRMS_MENU.MENU_CODE, HRMS_MENU.MENU_NAME,
			 * MENU_SERVICE_LINK,MENU_IMAGE ,MENU_KEYWORDS , MENU_GROUP FROM
			 * HRMS_MENU INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE =
			 * HRMS_PROFILE_DTL.MENU_CODE and HRMS_PROFILE_DTL.PROFILE_CODE ='" +
			 * bean.getUserProfileId() + "') WHERE MENU_TYPE='" + menuType + "'
			 * AND MENU_ISRELEASED='Y' ORDER BY MENU_GROUP,MENU_PLACEINMENU";
			 */
			Object data[][] = getSqlModel().getSingleResult(query);
			request.setAttribute("serviceMenulst", data);
			request.setAttribute("groupData", groupData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
