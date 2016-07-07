package org.paradyne.model.portal;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.lib.ModelBase;

/**
 * @author aa1381 25th Jan 2012.
 *
 */
public class TipsSettingModel extends ModelBase {

	/**
	 * Method : getTipsData().
	 * @param request : Used to set Attribute Value.
	 */
	public void getTipsData(HttpServletRequest request) {
		System.out.println("In Tips Model ----------------------------");
		String query = "SELECT TIP_LINKNAME,TIP_LINK,NVL(TIP_DIVISION,'B') FROM HRMS_SETTINGS_TIPS WHERE TIP_FLAG='Y' ORDER BY TIP_CODE DESC ";
		Object[][] tipsData = getSqlModel().getSingleResult(query);

		if (tipsData != null && tipsData.length > 0) {
			request.setAttribute("tipsData", tipsData);
		}

	}

}
