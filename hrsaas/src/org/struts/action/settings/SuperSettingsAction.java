/**
 * 
 */
package org.struts.action.settings;

import org.paradyne.bean.settings.SuperSettings;
import org.paradyne.model.settings.SuperSettingsModel;
import org.struts.action.common.LoginAction;
import org.struts.lib.ParaActionSupport;

/**
 * @author prakashs
 * 
 */
public class SuperSettingsAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoginAction.class);
	SuperSettings setting;
	String poolDir = "";
	String fileName = "";

	public String getPoolDir() {
		return poolDir;
	}

	public void setPoolDir(String poolDir) {
		this.poolDir = poolDir;
	}

	/**
	 * 
	 * Retrieving session pool
	 */
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		setting = new SuperSettings();
		setting.setMenuCode(417);
		poolDir = String.valueOf(session.getAttribute("session_pool"));
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return setting;
	}

	public SuperSettings getSetting() {
		return setting;
	}

	public void setSetting(SuperSettings setting) {
		this.setting = setting;
	}

	/**
	 * Called on Load Set data under List on Load
	 */

	public void prepare_withLoginProfileDetails() throws Exception {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		setting.setHiddenDivId("HR");
		showOnlyInfo();
		model.terminate();
	}

	/**
	 * Reset the fields
	 * 
	 * @return String
	 */

	public String reset() {

		setting.setUploadHr("");
		setting.setLinkNameHr("");
		setting.setLinkHr("");
		setting.setHiddenCode_Hr("");
		setting.setCheckFlag_hr("true");
		setting.setCheckHr("false");

		setting.setUploadQl("");
		setting.setLinkNameQl("");
		setting.setLinkQl("");
		setting.setHiddenCode_Ql("");
		setting.setCheckFlag_ql("true");
		setting.setCheckQuick("false");
		setting.setHiddenlinkPathQl("");

		setting.setUploadGs("");
		setting.setLinkNameGs("");
		setting.setLinkGs("");
		setting.setHiddenCode_Gs("");
		setting.setGenType("");
		setting.setCheckFlag_gs("true");
		setting.setCheckGeneral("false");

		return SUCCESS;
	}

	/**
	 * Saving and updating  HrWorK Communication Settings
	 * 
	 * @return String
	 */

	public String saveHrComm() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		// path to write XML
		fileName = getText("data_path")
				+ "\\dataFiles\\hrwork_comm\\hrworkcomm.xml";
		// save
		int value = model.saveHrComm(setting, "save", poolDir, fileName);
		reset();
		if (value == 0)
			addActionMessage("Duplicate Entry Found. Record cant be Added!");
		else if (value == 1)
			addActionMessage("Record Saved Successfully");
		else
			addActionMessage("Record Modified Successfully");
		setting.setCheckHr("");
		model.terminate();
		return "success";
	}

	/**
	 * Save Quick Links
	 * 
	 * @return String
	 */
	public String saveLink() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		// path to Write XML
		poolDir = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\quick_links\\quick.xml";
		// save
		int value = model.saveLink(setting, "save", poolDir);
		reset();
		if (value == 0)
			addActionMessage("Duplicate Entry Found. Record cant be Added!");
		else if (value == 1)
			addActionMessage("Record Saved Successfully");
		else
			addActionMessage("Record Modified Successfully");
		setting.setCheckQuick("");
		model.terminate();
		return "success";
	}

	/**
	 * Save General Settings
	 * 
	 * @return
	 */
	public String saveGeneral() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		// path to write XML
		poolDir = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\general\\general.xml";
		// save
		int value = model.saveGeneral(setting, "save", poolDir);
		reset();
		if (value == 0)
			addActionMessage("Duplicate Entry Found. Record cant be Added!");
		else if (value == 1)
			addActionMessage("Record Saved Successfully");
		else
			addActionMessage("Record Modified Successfully");
		return SUCCESS;
	}

	/**
	 * Saving Payroll Settings
	 * 
	 * @return String
	 */
	public String saveApplicationSetting() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		boolean flag = model.saveAppSetting(setting);
		if (flag)
			addActionMessage("Settings Saved Successfully");
		else
			addActionMessage("Settings Cant be Saved.");
		model.showAppSetting(setting);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * Edit and save HrWorK Communication Settings
	 * 
	 * @return String
	 */
	public String editHr() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		model.editHr(setting);// edit
		model.saveHrComm(setting, "show", poolDir, fileName);// save
		setting.setCheckFlag_ql("true");
		setting.setCheckFlag_gs("true");
		model.terminate();
		return "success";
	}

	/**
	 * Edit and save General Settings
	 * 
	 * @return String
	 */
	public String editGs() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		model.editGs(setting);// edit
		model.saveGeneral(setting, "show", poolDir);// save
		setting.setCheckFlag_ql("true");
		setting.setCheckFlag_hr("true");
		model.terminate();
		return "success";
	}

	/**
	 * Edit and save Quick Links
	 * 
	 * @return String
	 */
	public String editQl() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		model.editQl(setting);// edit
		model.saveLink(setting, "show", poolDir);// save
		setting.setCheckFlag_hr("true");
		setting.setCheckFlag_gs("true");
		model.terminate();
		return "success";
	}

	/**
	 * Delete from list of HrWorK Communication Settings
	 * 
	 * @return String
	 */
	public String deleteHr() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		// path to write XML
		fileName = getText("data_path")
				+ "\\dataFiles\\hrwork_comm\\hrworkcomm.xml";
		// delete
		boolean result = model.deleteHr(setting, fileName);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			reset();

		}// END if
		else {
			addActionMessage("Record Can't be Deleted!");

		}// END else
		showOnlyInfo();
		model.terminate();
		return "success";
	}

	/**
	 * Delete Quick Links from list
	 * 
	 * @return String
	 */
	public String deleteQl() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		// path to write XML
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\quick_links\\quick.xml";
		// delete
		boolean result = model.deleteQl(setting, fileName);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			reset();
		} // END if
		else {
			addActionMessage("Record Can't be Deleted!");
		}// END else
		showOnlyInfo();
		model.terminate();
		return "success";
	}

	/**
	 * Delete data from list under General Settings
	 * 
	 * @return String
	 */
	public String deleteGs() {
		SuperSettingsModel model = new SuperSettingsModel();
		model.initiate(context, session);
		// path to write XMl
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\general\\general.xml";
		// delete
		boolean result = model.deleteGs(setting, fileName);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			reset();
		} // END if
		else
			addActionMessage("Record Can't be Deleted!");
		showOnlyInfo();
		model.terminate();
		return "success";
	}

	/**
	 * Reset HRWorK Communication/General/Quick Link Settings
	 */
	public void reset_all() {
		setting.setDivFlag_HR(false);
		setting.setDivFlag_GE(false);
		setting.setDivFlag_QL(false);
		setting.setDivFlag_AS(false);
	}

	/**
	 * Set List on Load Clear Fields on load
	 * 
	 * @return String
	 */
	public String showOnlyInfo() {
		System.out.println(">>>>>>pool>>>>>>>>>" + getPoolDir());
		try {
			SuperSettingsModel model = new SuperSettingsModel();
			reset();
			reset_all();
			model.initiate(context, session);
			String hiddenDivId = setting.getHiddenDivId();
			if (hiddenDivId.equals("HR")) {
				model.saveHrComm(setting, "show", poolDir, fileName);
				setting.setDivFlag_HR(true);
			}// END if
			else if (hiddenDivId.equals("QL")) {
				model.saveLink(setting, "show", poolDir);
				setting.setDivFlag_QL(true);
			}// END else if
			else if (hiddenDivId.equals("GE")) {
				model.saveGeneral(setting, "show", poolDir);
				setting.setDivFlag_GE(true);
			}// END else if
			else if (hiddenDivId.equals("AS")) {
				showAppSetting();
				setting.setDivFlag_AS(true);
			}// END else if
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger
					.error("Exception Caught in Setting onLoad - Super Settings : "
							+ e);
			return SUCCESS;
		}
	}

	/**
	 * From on load
	 * 
	 * @return String
	 */
	public String showAppSetting() {
		SuperSettingsModel model = new SuperSettingsModel();
		reset();
		model.initiate(context, session);
		model.showAppSetting(setting);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * Search menus available for the user logged in
	 * 
	 * @return f9page
	 */
	public String f9MenuLink() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT TRIM(HRMS_MENU.MENU_NAME),DECODE( SUBSTR(M4.MENU_NAME ||'->'|| M3.MENU_NAME ||'->'|| "
				+ " M2.MENU_NAME||'->'|| M1.MENU_NAME,0,6),'->->->' 	, ''||'->'||NVL(M1.MENU_NAME,'MAIN MENU'),"
				+ " M4.MENU_NAME ||'->'|| M3.MENU_NAME ||'->'|| M2.MENU_NAME||'->'|| M1.MENU_NAME) as PARENT_MENU,"
				+ " HRMS_MENU.MENU_LINK FROM HRMS_MENU "
				+ " INNER JOIN HRMS_PROFILE_DTL ON (HRMS_PROFILE_DTL.MENU_CODE = HRMS_MENU.MENU_CODE) "
				+ " LEFT JOIN HRMS_MENU  M1 ON (M1.MENU_CODE =HRMS_MENU.MENU_PARENT_CODE) "
				+ " LEFT JOIN HRMS_MENU  M2 ON (M2.MENU_CODE =M1.MENU_PARENT_CODE) "
				+ " LEFT JOIN HRMS_MENU  M3 ON (M3.MENU_CODE =M2.MENU_PARENT_CODE) "
				+ " LEFT JOIN HRMS_MENU  M4 ON (M4.MENU_CODE =M3.MENU_PARENT_CODE) "
				+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN( "
				+ setting.getMultipleProfileCode()
				+ ") AND HRMS_MENU.MENU_LINK IS NOT NULL "
				+ " AND HRMS_MENU.MENU_LINK NOT IN(select QUICK_ADMIN_LINK from HRMS_SETTINGS_QUICKLINK_ADMIN) "
				+ " AND( PROFILE_INSERT_FLAG='Y' OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' "
				+ " OR PROFILE_VIEW_FLAG ='Y' OR PROFILE_GENERAL_FLAG ='Y') "
				+ " ORDER BY TRIM(HRMS_MENU.MENU_NAME),PARENT_MENU";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Menu Name", "Parent Menu" };

		String[] headerWidth = { "50", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "linkNameQl", "parentMenu", "hiddenlinkPathQl" };

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
