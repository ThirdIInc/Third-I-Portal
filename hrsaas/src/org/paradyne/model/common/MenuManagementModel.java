/**
 * 
 */
package org.paradyne.model.common;

import java.util.ArrayList;

import org.paradyne.bean.common.MenuManagement;
import org.paradyne.bean.common.ProfileBean;
import org.paradyne.lib.ModelBase;

/**
 * @author debajanid
 * 
 */
public class MenuManagementModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/**
	 * To set the fields of the form
	 * 
	 * @param menu
	 */
	public void getMenuRecord(MenuManagement menu) {
		Object addObj[] = new Object[1];
		addObj[0] = menu.getMenuID();

		Object data[][] = getSqlModel().getSingleResult(getQuery(4), addObj);

		for (int i = 0; i < data.length; i++) {
			menu.setMenuID(checkNull(String.valueOf(data[0][0])));
			menu.setMenuName(checkNull(String.valueOf(data[0][1])));
			menu.setMenuLink(checkNull(String.valueOf(data[0][2])));
			menu.setMenuParntId(checkNull(String.valueOf(data[0][3])));
			menu.setMenuParValue(checkNull(String.valueOf(data[0][4])));
			menu.setTarget(checkNull(String.valueOf(data[0][5])));
			menu.setMessage(checkNull(String.valueOf(data[0][6])));
			menu.setPlacement(checkNull(String.valueOf(data[0][7])));
			menu.setOrder(checkNull(String.valueOf(data[0][8])));

		}//end of loop
	}

	/**
	 * Replacing null with blank
	 * 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}//end of if 
		else {
			return result;
		}//end of else
	}

	/**
	 * To save the record
	 * 
	 * @param menu
	 * @return boolean
	 */
	public boolean addMenu(MenuManagement menu) {
		// TODO Auto-generated method stub
		Object addObj[][] = new Object[1][7];
		addObj[0][0] = menu.getMenuName().trim();
		addObj[0][1] = menu.getMenuLink().trim();
		addObj[0][2] = menu.getMenuParntId().trim();
		addObj[0][3] = menu.getTarget().trim();
		addObj[0][4] = menu.getOrder().trim();
		addObj[0][5] = menu.getMessage().trim();
		addObj[0][6] = menu.getPlacement().trim();
		return getSqlModel().singleExecute(getQuery(1), addObj);

	}

	/**
	 * To modify any record
	 * 
	 * @param menu
	 * @return boolean
	 */
	public boolean modMenu(MenuManagement menu) {
		// TODO Auto-generated method stub
		Object modObj[][] = new Object[1][8];
		modObj[0][0] = menu.getMenuName().trim();
		modObj[0][1] = menu.getMenuLink().trim();
		modObj[0][2] = menu.getMenuParntId().trim();
		modObj[0][3] = menu.getTarget().trim();
		modObj[0][4] = menu.getOrder().trim();
		modObj[0][5] = menu.getMessage().trim();
		modObj[0][6] = menu.getPlacement().trim();
		modObj[0][7] = menu.getMenuID().trim();
		return getSqlModel().singleExecute(getQuery(2), modObj);

	}

	/**
	 * Deleting any selected record
	 * 
	 * @param menu
	 * @return
	 */
	public boolean deleteMenu(MenuManagement menu) {
		// TODO Auto-generated method stub
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = menu.getMenuID();
		logger.info("Menu ID" + String.valueOf(delObj[0][0]));
		boolean result = getSqlModel().singleExecute(getQuery(3), delObj);
		logger.info("result" + result);
		return result;
	}

	/**
	 * To generate report sets on list
	 * 
	 * @param menu
	 */
	public void getMenuReport(MenuManagement menu) {
		// TODO Auto-generated method stub
		Object[][] obj = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> menuList = new ArrayList<Object>();
		for (int i = 0; i < obj.length; i++) {

			MenuManagement menuBean = new MenuManagement();
			menuBean.setMenuName(checkNull(String.valueOf(obj[i][1])));
			menuBean.setMenuLink(checkNull(String.valueOf(obj[i][2])));
			menuBean.setMenuParntId(checkNull(String.valueOf(obj[i][3])));
			menuBean.setMenuParValue(checkNull(String.valueOf(obj[i][4])));
			menuBean.setTotalpath(checkNull(String.valueOf(obj[i][5])));
			menuBean.setTarget(checkNull(String.valueOf(obj[i][6])));
			menuBean.setMessage(checkNull(String.valueOf(obj[i][7])));
			menuBean.setPlacement(checkNull(String.valueOf(obj[i][8])));
			menuBean.setOrder(checkNull(String.valueOf(obj[i][9])));
			menuList.add(menuBean);
		}//end of loop
		menu.setMenuList(menuList);
	}

}
