package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.AlertEmailSetting;
import org.paradyne.model.ApplicationStudio.AlertEmailSettingModel;
import org.struts.lib.ParaActionSupport;
public class AlertEmailSettingAction extends ParaActionSupport {

	AlertEmailSetting alertEmail;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		alertEmail=new AlertEmailSetting();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return alertEmail;
	}

	public AlertEmailSetting getAlertEmail() {
		return alertEmail;
	}

	public void setAlertEmail(AlertEmailSetting alertEmail) {
		this.alertEmail = alertEmail;
	}
	
	
	public String clear()
	{
		alertEmail.setModCode("");
		alertEmail.setModName("");
		alertEmail.setNoOfDays("");
		alertEmail.setAlertType("");
		alertEmail.setApplicantToapprover("");
		alertEmail.setApproverToapplicant("");
		alertEmail.setApproverToapprover("");
		
		
		return SUCCESS; 
	}
	
	
	public String save()
	{
		
		AlertEmailSettingModel model=new AlertEmailSettingModel();
		model.initiate(context, session);
		boolean flag=model.save(alertEmail);
		if(flag)
		{
			addActionMessage("Record saved successfully");
		}
		else
		{
			addActionMessage("Error while saving record");
		}
		model.terminate();
		return SUCCESS;
	}
	
	public String f9Modules() {
		
			String profileCode = alertEmail.getMultipleProfileCode();
			//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE 
			String query = " SELECT NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), HRMS_MENU.MENU_CODE FROM HRMS_MENU " +
			" INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE" +
			" AND ( PROFILE_INSERT_FLAG='Y'  OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y'  OR " +  
			" PROFILE_GENERAL_FLAG ='Y')) " +
			" LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE) " + 
			" WHERE MENU_PARENT_CODE = 0 AND PROFILE_CODE IN(" + profileCode + ") ORDER BY MENU_TABORDER ";

			String[] headers = {"Module Name"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"modName", "modCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		
	}

}
