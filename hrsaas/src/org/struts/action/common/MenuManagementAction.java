/**
 * 
 */
package org.struts.action.common;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.common.MenuManagement;
import org.paradyne.model.common.MenuManagementModel;

/**
 * @author debajanid
 * 
 */
public class MenuManagementAction extends ParaActionSupport {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * 
	 */

	MenuManagement menu;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {

		menu = new MenuManagement();
		menu.setMenuCode(615);
	}

	public Object getModel() {

		return menu;
	}

	public MenuManagement getMenu() {
		return menu;
	}

	public void setMenu(MenuManagement menu) {
		this.menu = menu;
	}

	/**
	 * For saving the form details
	 * 
	 * @return reset() - Resets the form after saving
	 * @throws Exception
	 */
	public String save() throws Exception {
		MenuManagementModel model = new MenuManagementModel();
		boolean result;
		model.initiate(context, session);
		// for insertion
		if (menu.getMenuID().equals("")) {
			result = model.addMenu(menu);
			if (result) {
				addActionMessage(getText("addMessage", ""));
			}// end of if
			else {
				addActionMessage("Menu can not be added");
			}// end of else
		}// end of if
		// for updation
		else {
			result = model.modMenu(menu);
			if (result) {
				addActionMessage(getText("modMessage", ""));
			}// end of if
			else {
				addActionMessage("Menu can not be modified");
			}// end of if
		}// end of if

		model.terminate();
		return reset();
	}

	/**
	 * Resets all the fields of the form
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		menu.setMenuID("");
		menu.setMenuLink("");
		menu.setMenuName("");
		menu.setMenuParntId("");
		menu.setMenuParValue("");
		menu.setMenuValue("");
		menu.setMessage("");
		menu.setPlacement("");
		menu.setOrder("");
		menu.setTarget("");

		return "success";
	}

	/**
	 * To delete any selected record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		MenuManagementModel model = new MenuManagementModel();
		boolean result = false;
		model.initiate(context, session);
		if (menu.getMenuID().equals("")) {

		}// end of if
		else {
			result = model.deleteMenu(menu);
		}// end of else
		model.terminate();
		if (result) {
			addActionMessage(getText("delMessage", ""));
			reset();
		} // end of if

		else {
			addActionMessage("Branch can not be deleted");
		}// end of if
		return "success";
	}

	/**
	 * To generate report
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String report() throws Exception {

		try {
			MenuManagementModel model = new MenuManagementModel();
			model.initiate(context, session);
			model.getMenuReport(menu);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in report");
		}
		return "report";
	}

	/**
	 * To set the fields of the form after search
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String menuRecord() throws Exception {
		MenuManagementModel model = new MenuManagementModel();
		model.initiate(context, session);
		model.getMenuRecord(menu);
		model.terminate();
		return "success";
	}

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_NAME,M1.MENU_NAME FROM HRMS_MENU "
				+ "	LEFT JOIN HRMS_MENU M1 ON (M1.MENU_CODE = HRMS_MENU.MENU_PARENT_CODE) "
				+ " ORDER BY HRMS_MENU.MENU_TABORDER,HRMS_MENU.MENU_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Menu Code", "Menu  Name", "Menu Parent Name" };

		String[] headerWidth = { "20", "40", "40" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "menu.menuID", "menu.menuName", "parname" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "MenuManagement_menuRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Menuaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_NAME , DECODE( SUBSTR(M4.MENU_NAME ||'->'|| M3.MENU_NAME ||'->'|| "
				+ "	M2.MENU_NAME||'->'|| M1.MENU_NAME,0,6),'->->->' "
				+ "	, ''||'->'||NVL(M1.MENU_NAME,'MAIN MENU'),M4.MENU_NAME ||'->'|| M3.MENU_NAME ||'->'|| M2.MENU_NAME||'->'|| M1.MENU_NAME) "

				+ "	FROM HRMS_MENU "

				+ "	LEFT JOIN HRMS_MENU  M1 ON (M1.MENU_CODE =HRMS_MENU.MENU_PARENT_CODE) "
				+ "	LEFT JOIN HRMS_MENU  M2 ON (M2.MENU_CODE =M1.MENU_PARENT_CODE) "
				+ "	LEFT JOIN HRMS_MENU  M3 ON (M3.MENU_CODE =M2.MENU_PARENT_CODE) "
				+ "	LEFT JOIN HRMS_MENU  M4 ON (M4.MENU_CODE =M3.MENU_PARENT_CODE) "
				+ "	ORDER BY HRMS_MENU.MENU_TABORDER,HRMS_MENU.MENU_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Parent Code", "Parent  Name", "Parent Path" };

		String[] headerWidth = { "10", "40", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "menu.menuParntId", "menu.menuParValue",
				"menuParPath" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
