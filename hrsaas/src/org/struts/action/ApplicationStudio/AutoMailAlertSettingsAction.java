
/**
 * 
 */
package org.struts.action.ApplicationStudio;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.ApplicationStudio.AutoMailAlertSettings;
import org.paradyne.model.ApplicationStudio.AutoMailAlertSettingsModel;

/**
 * @author 
 * 
 */
public class AutoMailAlertSettingsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	AutoMailAlertSettings autoMailSettings = null;
	

	public AutoMailAlertSettings getAutoMailSettings() {
		return autoMailSettings;
	}

	public void setAutoMailSettings(AutoMailAlertSettings autoMailSettings) {
		this.autoMailSettings = autoMailSettings;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		autoMailSettings = new AutoMailAlertSettings();
		autoMailSettings.setMenuCode(1052);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return autoMailSettings;
	}

	public String input(){
		AutoMailAlertSettingsModel model = new AutoMailAlertSettingsModel();
		autoMailSettings.setEnableAll("N");
		getNavigationPanel(1);
		model.initiate(context, session);
		model.displaySettings(autoMailSettings, request);
		model.terminate();
		return "onloadList";
	}
	
	public String addNew(){
		try {
			getNavigationPanel(2);
		//	autoMailSettings.setEnableAll("N");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	private String displaySettings() {
		// TODO Auto-generated method stub
		AutoMailAlertSettingsModel model = new AutoMailAlertSettingsModel();
		model.initiate(context, session);
		model.displaySettings(autoMailSettings, request);
		model.terminate();
		return SUCCESS;
	}

	public String save() {
		AutoMailAlertSettingsModel model = new AutoMailAlertSettingsModel();
		try {
			model.initiate(context, session);
			
			if(autoMailSettings.getAutoCode().equals(""))
			{
				String flag = model.saveSettings(autoMailSettings);
				if (flag.equals("saved")) {
					addActionMessage(getMessage("save"));
					getNavigationPanel(3);
				} else if (flag.equals("duplicate")) { 
					addActionMessage("Duplicate record found");
					getNavigationPanel(1);
				} else {
					addActionMessage(getMessage("save.error"));
				}
			}
			else
			{
				String flag = model.updateSettings(autoMailSettings);
				if (flag.equals("update")) {
					addActionMessage(getMessage("update"));
					getNavigationPanel(3);
				} else if (flag.equals("duplicate")) { 
					addActionMessage("Duplicate record found");
					getNavigationPanel(1);
				} else {
					addActionMessage(getMessage("update.error"));
				}
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	 
	/**
	 * To delete one or more records from the list
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		String code[] = request.getParameterValues("hdeleteCode");
		
		AutoMailAlertSettingsModel model = new AutoMailAlertSettingsModel();
		model.initiate(context, session);
		
		
		boolean result = model.deleteAutoAlert(autoMailSettings, code);
		
		if(result) {
			
			addActionMessage(getMessage("delete"));
			
			autoMailSettings.setTempCode("");
			autoMailSettings.setAutoFlag("");
			autoMailSettings.setIndDptFlag("");
			autoMailSettings.setAllDptFlag("");
			autoMailSettings.setIndBrnFlag("");
			autoMailSettings.setAllBrnFlag("");
			autoMailSettings.setIndDesgFlag("");
			autoMailSettings.setAllDesgFlag("");
			autoMailSettings.setIndDivFlag("");
			autoMailSettings.setAllDivFlag("");
			//autoMailSettings.setTempCode("");
			autoMailSettings.setTempName("");
			autoMailSettings.setIndEmpFlag("");
			autoMailSettings.setAllEmpFlag("");
			autoMailSettings.setAutoMailName("");
			autoMailSettings.setReportingToFlag("");
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.displaySettings(autoMailSettings, request);
		getNavigationPanel(1);
		model.terminate();

		return "onloadList";
	}

	public String f9mailTemplate() throws Exception {

		String query = " SELECT TEMPLATE_ID, NVL(TEMPLATE_NAME,'') FROM HRMS_BIRTHDAYTEMPLATE_HDR ORDER BY TEMPLATE_NAME";
		String[] headers = { getMessage("template.code"), getMessage("template") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "tempCode", "tempName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		AutoMailAlertSettingsModel model = new AutoMailAlertSettingsModel();
		model.initiate(context, session);
		model.calforedit(autoMailSettings,autoMailSettings.getHiddencode());
		//input();
		//model.hasData(autoMailSettings, request);
		getNavigationPanel(3);
		autoMailSettings.setEnableAll("N");
		model.terminate();
		return "centerData";
	}

	/**
	 * To reset the fields
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			 
			autoMailSettings.setTempCode("");
			autoMailSettings.setTempName("");
			autoMailSettings.setAutoFlag("");
			autoMailSettings.setIndDptFlag("");
			autoMailSettings.setAllDptFlag("");
			autoMailSettings.setIndBrnFlag("");
			autoMailSettings.setAllBrnFlag("");
			autoMailSettings.setIndDesgFlag("");
			autoMailSettings.setAllDesgFlag("");
			autoMailSettings.setIndDivFlag("");
			autoMailSettings.setAllDivFlag("");
			autoMailSettings.setAllEmpFlag("");
			autoMailSettings.setReportingToFlag("");
			autoMailSettings.setAutoCode("");
			autoMailSettings.setAutoMailName("");
			getNavigationPanel(2);
		} catch(Exception e) {
			logger.error("Error in reset" + e);
		}
		return "centerData";
	}
	/**
	 * To Search record For list Page
	 * @return string
	 * @throws Exception
	 */
	public String setData()
	{
		AutoMailAlertSettingsModel model = new AutoMailAlertSettingsModel();
		model.initiate(context, session);
		model.calforedit(autoMailSettings,autoMailSettings.getAutoCode());
		getNavigationPanel(3);
		autoMailSettings.setEnableAll("N");
		model.terminate();
		return "centerData";
	}
	
	public String cancel() {
		try {
			reset();
			input();
			getNavigationPanel(1);
			return "onloadList";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	/**
	 * To generate report
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		AutoMailAlertSettingsModel model = new AutoMailAlertSettingsModel();
		model.initiate(context, session);
		String[]label={"Sr.No",getMessage("enable.mailalertfor"),getMessage("isEnable"),getMessage("reporting.flag")};
		model.getReport(autoMailSettings, request, response, context, session,label);
		
		model.terminate();
		return null;
	}
	
	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		AutoMailAlertSettingsModel model = new AutoMailAlertSettingsModel();
		model.initiate(context, session);
		model.displaySettings(autoMailSettings, request);
		getNavigationPanel(1);
		model.terminate();
		return "onloadList";
	}
	
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT AUTO_MAIL_NAME,DECODE(AUTO_SEND,'Y','Yes','N','No'),AUTO_CODE FROM HRMS_AUTO_MAIL_ALERT  ORDER BY AUTO_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("templateName"), getMessage("isEnable")};

		String[] headerWidth = { "50", "50"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"autoMailName", "isEnable","autoCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1,2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AutoMailSetting_setData.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
}
