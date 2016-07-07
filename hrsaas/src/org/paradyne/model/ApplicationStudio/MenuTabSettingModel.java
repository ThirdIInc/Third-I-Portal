/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.ApplicationStudio.MenuTabSetting;
import org.paradyne.lib.Menu;
import org.paradyne.lib.MenuList;
import org.paradyne.lib.ModelBase;

/**
 * @author AA0563
 *
 */
public class MenuTabSettingModel extends ModelBase {
	private Vector vect = new Vector();

	public void getMenuItems(MenuTabSetting ms, HttpServletRequest request) {
		String query = " SELECT distinct HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME),HRMS_MENU.MENU_NAME,HRMS_MENU.MENU_PARENT_CODE , 'N' , 'N' , 'N' , 'N' ,"
				+ " 'N' , 'N',level-2,HRMS_MENU.MENU_PLACEINMENU  "
				+ " FROM HRMS_MENU "
				+ "   left join  HRMS_MENU_CLIENT ON(HRMS_MENU_CLIENT.MENU_CODE=HRMS_MENU.MENU_CODE)"
				+ "  WHERE HRMS_MENU.MENU_CODE !=0 AND  HRMS_MENU.MENU_ISRELEASED ='Y' "
				+ " START WITH HRMS_MENU.MENU_CODE = "
				+ ms.getMenuCode()
				+ "   CONNECT BY PRIOR  HRMS_MENU.MENU_CODE=HRMS_MENU.MENU_PARENT_CODE"
				+ "  ORDER BY HRMS_MENU.MENU_PLACEINMENU,MENU_PARENT_CODE, HRMS_MENU.MENU_CODE ";

		Object[][] menudata = getSqlModel().getSingleResult(query);
		System.out.println("length of menu data is" + menudata.length);
		request.setAttribute("tot", menudata.length);

		MenuList menuList = new MenuList(menudata);
		Menu menu = (Menu) menuList.getMenuList();
		LinkedList list = menu.getChild();
		System.out.println("list.size()...." + list.size());
		for (int i = 0; i < list.size(); i++) {
			Menu menuChild = (Menu) list.get(i);

			createMenuBar5(menuChild, ms);

		}

		//////////vector...... forloop......size/3
		int count = 0;
		Object[][] obj = new Object[vect.size() / 3][3];
		ArrayList al = new ArrayList();
		for (int i = 0; i < vect.size() / 3; i++) {
			MenuTabSetting bean = new MenuTabSetting();
			obj[i][0] = vect.get(count++);
			obj[i][1] = vect.get(count++);
			obj[i][2] = vect.get(count++);
			bean.setMenuCode(Integer.parseInt(String.valueOf(obj[i][0])));
			bean.setMenuName((String.valueOf(obj[i][1])));
			bean.setOrgmenuname((String.valueOf(obj[i][2])));
			al.add(bean);
		}
		ms.setMenusetlist(al);

	}

	public void createMenuBar5(Menu menuBean, MenuTabSetting ms) {
		int count = menuBean.getMenuCount();
		MenuTabSetting bean1 = new MenuTabSetting();

		vect.add(menuBean.getMenuId());
		vect.add(menuBean.getMenuDesc());
		vect.add(menuBean.getMenuClass());
		if (count == 0) {
		} else {
			LinkedList list = menuBean.getChild();
			for (int i = 0; i < list.size(); i++) {
				createMenuBar5((Menu) list.get(i), ms);

			}

		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public boolean saveData(MenuTabSetting ms, Object[][] getData) {
		String query = null;
		boolean flag1 = false;

		for (int k = 0; k < getData.length; k++) {
			System.out.println("entry to  first for loop for duplicate");
			String editquery = "SELECT MENU_PARENT_CODE FROM HRMS_MENU WHERE MENU_CODE="
					+ getData[k][1];
			Object objedit[][] = getSqlModel().getSingleResult(editquery);
			String childmenuquery = "SELECT MENU_NAME FROM HRMS_MENU WHERE MENU_PARENT_CODE="
					+ objedit[0][0] + " AND  MENU_CODE!=" + getData[k][1];
			Object childedit[][] = getSqlModel()
					.getSingleResult(childmenuquery);
			for (int l = 0; l < childedit.length; l++) {

				String menuName = String.valueOf(getData[k][0]).trim();
				String childstr = String.valueOf(childedit[l][0]).trim();
				while (menuName.indexOf("  ") > -1)
					menuName = menuName.replace("  ", " ");
				while (childstr.indexOf("  ") > -1)
					childstr = childstr.replace("  ", " ");

				if (menuName.equalsIgnoreCase(childstr)) {

					flag1 = false;
					return flag1;
				}

			}

		}

		Object[][] delData = new Object[getData.length][1];
		for (int i = 0; i < getData.length; i++) {
			delData[i][0] = getData[i][1];
		}
		String delQuery = "DELETE FROM  HRMS_MENU_CLIENT WHERE MENU_CODE=?";
		boolean flag = getSqlModel().singleExecute(delQuery, delData);

		if (flag) {
			String query1 = "INSERT INTO HRMS_MENU_CLIENT(MENU_NAME,MENU_CODE)VALUES(?,?)";
			flag1 = getSqlModel().singleExecute(query1, getData);
		}

		if (flag1 == true)
			return flag1;
		else
			return flag1;
	}

}
