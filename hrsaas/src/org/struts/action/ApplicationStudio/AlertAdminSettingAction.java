/**
 * 
 */
package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.AlertAdminSetting;
import org.paradyne.model.ApplicationStudio.AlertAdminSettingModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0491
 * 
 */
public class AlertAdminSettingAction extends ParaActionSupport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	AlertAdminSetting alertAdminSettingInstance = null;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		alertAdminSettingInstance = new AlertAdminSetting();
		alertAdminSettingInstance.setMenuCode(2118);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return alertAdminSettingInstance;
	}

	public AlertAdminSetting getAlertAdminSettingInstance() {
		return alertAdminSettingInstance;
	}

	public void setAlertAdminSettingInstance(
			AlertAdminSetting alertAdminSettingInstance) {
		this.alertAdminSettingInstance = alertAdminSettingInstance;
	}

	public String reset() {
		try {
			alertAdminSettingInstance.setModuleCode("");
			alertAdminSettingInstance.setModuleName("");
			alertAdminSettingInstance.setSubject("");
			alertAdminSettingInstance.setJobCode("");
			alertAdminSettingInstance.setQuery("");
			alertAdminSettingInstance.setQuerytype("");
			alertAdminSettingInstance.setAlerttype("");
			alertAdminSettingInstance.setEmailCheck("");
			alertAdminSettingInstance.setAlertCheck("");

			alertAdminSettingInstance.setTemplateName("");
			alertAdminSettingInstance.setTemplateCode("");
			alertAdminSettingInstance.setMethodName("");
			
			alertAdminSettingInstance.setLink("");
			alertAdminSettingInstance.setLinkParameter("");
			alertAdminSettingInstance.setNoOflinkParameter("");
			alertAdminSettingInstance.setLinkParameterValue("");

		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;
	}

	public String setData() {
		try {
			AlertAdminSettingModel model = new AlertAdminSettingModel();
			model.initiate(context, session);
			model.setData(alertAdminSettingInstance);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			AlertAdminSettingModel model = new AlertAdminSettingModel();
			model.initiate(context, session);
			boolean flag = model.delete(alertAdminSettingInstance);
			if (flag) {
				addActionMessage("Record deleted successfully.");
			} else {
				addActionMessage("Record can not be deleted.");
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		reset();
		return SUCCESS;
	}

	public String save() {
		try {
			AlertAdminSettingModel model = new AlertAdminSettingModel();
			model.initiate(context, session);

			if (alertAdminSettingInstance.getJobCode().equals("")) {
				boolean flag = model.saveAlert(alertAdminSettingInstance);
				if (flag) {
					addActionMessage("Record saved successfully");
				} else {
					addActionMessage("Record can not be saved ");
				}
			} else {
				boolean flag = model.updateAlert(alertAdminSettingInstance);
				if (flag) {
					addActionMessage("Record updated successfully");
				} else {
					addActionMessage("Record can not be updated ");
				}
			}

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		reset();
		return SUCCESS;
	}

	public String f9searchaction() throws Exception {

		String query = " SELECT JOB_ID,JOB_MODULE_NAME,JOB_SUBJECT,JOB_QUERY_TYPE, JOB_MESSAGE_TYPE ,JOB_TEMPLATE_CODE ,TEMPLATE_NAME FROM HRMS_JOB "
			+" left join HRMS_EMAILTEMPLATE_HDR on (HRMS_EMAILTEMPLATE_HDR.TEMPLATE_ID = HRMS_JOB.JOB_TEMPLATE_CODE) "
			+" ORDER BY JOB_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Sr. No.", getMessage("modName"),getMessage("subject") };
		String[] headerWidth = { "10", "15","75" };
		String[] fieldNames = { "jobCode", "moduleName", "subject",
				"querytype", "alerttype","templateCode","templateName" };
		/**
		 * ,"tempContent",TEMPLATE_CONTENT SET THE COLUMN INDEX E.G. SUPPOSE THE
		 * POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH
		 * COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE
		 * COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 ,5,6};

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

		String submitToMethod = "AlertAdminSetting_setData.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9ModuleName() throws Exception {
		try {
			String query = " SELECT   MODULE_NAME ,MODULE_CODE FROM HRMS_MODULE order by MODULE_CODE ";
			String[] headers = { getMessage("modName") };
			// "Event Name","Event Module Name" };
			String[] headerWidth = { "100" };
			// String[] fieldNames = { "mailEventcode","dupModulecode" };
			String[] fieldNames = { "moduleName" };
			int[] columnIndex = { 0 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";
	}

	public String f9template() throws Exception {
		try {
			String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME  FROM  HRMS_EMAILTEMPLATE_HDR "
					+ "  ORDER BY TEMPLATE_ID ";
			String[] headers = { getMessage("template.id"),
					getMessage("template") };
			// "Event Name","Event Module Name" };
			String[] headerWidth = { "20", "80" };
			// String[] fieldNames = { "mailEventcode","dupModulecode" };
			String[] fieldNames = { "templateCode", "templateName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";
	}

}
