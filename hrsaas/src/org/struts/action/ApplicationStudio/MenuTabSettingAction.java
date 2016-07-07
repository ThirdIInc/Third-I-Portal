/**
 * 
 */
package org.struts.action.ApplicationStudio;

import java.util.ArrayList;

import org.paradyne.bean.ApplicationStudio.MenuTabSetting;
import org.paradyne.model.ApplicationStudio.MenuTabSettingModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0563
 *
 */
public class MenuTabSettingAction extends ParaActionSupport {
	MenuTabSetting ms;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		ms = new MenuTabSetting();
		ms.setMenuCode(762);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return ms;
	}

	/**
	 * @return the ms
	 */
	public MenuTabSetting getMs() {
		return ms;
	}

	/**
	 * @param ms the ms to set
	 */
	public void setMs(MenuTabSetting ms) {
		this.ms = ms;
	}

	public String save() throws Exception {

		MenuTabSettingModel model = new MenuTabSettingModel();
		model.initiate(context, session);
		boolean result;
		String menuName[] = request.getParameterValues("editmenu");
		String menucode[] = request.getParameterValues("editmenucode");
		ArrayList<Object[][]> al = new ArrayList<Object[][]>();
		Object test[][] = new Object[1][1];
		int cnt = 0;
		for (int i = 0; i < menuName.length; i++) {
			if (menuName[i] != null && !menuName[i].equals("")) {
				cnt++;
			}

		}
		Object[][] getData = new Object[cnt][2];
		int count = 0;
		for (int i = 0; i < menuName.length; i++) {
			if (menuName[i] != null && !menuName[i].equals("")
					&& menucode[i] != null && !menucode[i].equals("")) {
				getData[count][0] = menuName[i];
				getData[count][1] = menucode[i];
				count++;
			}

		}

		result = model.saveData(ms, getData);
		if (result) {
			addActionMessage(getMessage("save"));
		} else
			addActionMessage(getMessage("duplicate"));

		model.getMenuItems(ms, request);
		model.terminate();

		return "success";
	}

	public String f9menuAction() throws Exception {
		try {
			//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE 
			String query = " SELECT distinct HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME) "  
						 + " FROM HRMS_MENU "  
						 + " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE "
						 + " AND ( PROFILE_INSERT_FLAG='Y'  OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y'  OR " 
						 + " PROFILE_GENERAL_FLAG ='Y')) "
						 + " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE) " 
						 + " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN("+ms.getMultipleProfileCode()+") AND MENU_PARENT_CODE=0 AND MENU_ISRELEASED='Y' " 
						 + " ORDER BY HRMS_MENU.MENU_CODE ";

			String[] headers = { getMessage("menu.code"),
					getMessage("menu.name") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "menuCode", "menuName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "true";

			String submitToMethod = "MenuTabSetting_getMenuList.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "f9page";
	}

	public String getMenuList() throws Exception {
		System.out.println("entry to menu list model");
		MenuTabSettingModel model = new MenuTabSettingModel();
		model.initiate(context, session);
		model.initiate(context, session);
		model.getMenuItems(ms, request);
		model.terminate();
		return "success";
	}

}
