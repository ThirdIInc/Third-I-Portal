package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.NavigationPanelSettings;
import org.paradyne.model.ApplicationStudio.NavigationPanelSettingsModel; 
import org.struts.lib.ParaActionSupport;

/**
 * @author Bhushan Dasare
 * @date Dec 22, 2008
 */

/**
 * To design navigation panel for every form
 */

public class NavigationPanelSettingsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NavigationPanelSettingsAction.class);
	NavigationPanelSettings bean;

	public void prepare_local() throws Exception {
		bean = new NavigationPanelSettings();
		bean.setMenuCode(736);
	}

	public Object getModel() {
		return bean;
	}

	public NavigationPanelSettings getBean() {
		return bean;
	}

	public void setBean(NavigationPanelSettings bean) {
		this.bean = bean;
	}

	public String f9Profiles() {
		try {
			String query = " SELECT PROFILE_NAME, PROFILE_CODE FROM HRMS_PROFILE_HDR ORDER BY PROFILE_CODE ";

			String[] headers = {"PROFILE NAME"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"proName", "proCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Branch
	
	public String f9Modules() {
		try {
			String profileCode = bean.getMultipleProfileCode();
			//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE 
			String query = " SELECT NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), HRMS_MENU.MENU_CODE FROM HRMS_MENU " +
			" INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE" +
			" AND ( PROFILE_INSERT_FLAG='Y'  OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y'  OR " +  
			" PROFILE_GENERAL_FLAG ='Y')) " +
			" LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE) " + 
			" WHERE MENU_PARENT_CODE = 0 AND PROFILE_CODE IN( " + profileCode + ") ORDER BY MENU_TABORDER ";

			String[] headers = {getMessage("module")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"modName", "modCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}
	
	public String f9Forms() {
		try {
			String modCode = bean.getModCode();
			String profileCode = bean.getUserProfileId();
			//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE 
			String query = " SELECT DISTINCT MENU_NAME, HRMS_MENU.MENU_CODE FROM HRMS_MENU " +
			" INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE " +
			" AND (PROFILE_INSERT_FLAG = 'Y' OR PROFILE_UPDATE_FLAG ='Y' OR PROFILE_DELETE_FLAG = 'Y' " +
			" OR PROFILE_VIEW_FLAG = 'Y' OR PROFILE_GENERAL_FLAG ='Y') AND PROFILE_CODE IN( " + profileCode + ")) " +
			" WHERE MENU_LINK IS NOT NULL AND MENU_PARENT_CODE != 0 " +
			" START WITH HRMS_MENU.MENU_CODE = " + modCode + " CONNECT BY PRIOR HRMS_MENU.MENU_CODE = HRMS_MENU.MENU_PARENT_CODE " +
			" ORDER BY UPPER(MENU_NAME) ";

			String[] headers = {getMessage("formname")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"formName", "formCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String getButtons1() {
		try {
			NavigationPanelSettingsModel model = new NavigationPanelSettingsModel();
			model.initiate(context, session);
		
			String fileName = getText("data_path") + "/NavigationPanel/";
			String message = model.getButtons(fileName, bean, request);
			if(!message.equals("")) {
				addActionMessage(message);
			}
			bean.setImgFlag("false");
			bean.setSvFlag("true");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String reset() {
		try {
			bean.setModCode("");
			bean.setModName("");
			bean.setFormCode("");
			bean.setFormName("");
			bean.setListButtons(false);
			bean.setTempCode("");
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String save() {
		try {
			NavigationPanelSettingsModel model = new NavigationPanelSettingsModel();
			model.initiate(context, session);
			String fileCode=bean.getFormCode();
			
			String fileName = getText("data_path") + "/NavigationPanel/"+fileCode+".xml";
			
			System.out.println("fileName-----------------"+fileName);
			String message = model.save(bean, fileName, request);
			if(!message.equals("")) {
				addActionMessage(message);
			}
			
			model.terminate();			
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}
}