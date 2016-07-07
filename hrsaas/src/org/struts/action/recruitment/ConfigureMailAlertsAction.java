package org.struts.action.recruitment;
/*
 * @author saipavan voleti
 * 
 */

import org.paradyne.bean.Recruitment.ConfigureMailAlerts;
import org.paradyne.model.recruitment.ConfigureMailAlertsModel;
import org.struts.lib.ParaActionSupport;

public class ConfigureMailAlertsAction extends ParaActionSupport {

	ConfigureMailAlerts Confbean;
	@Override
	public void prepare_local() throws Exception {
		
		Confbean=new ConfigureMailAlerts();
		Confbean.setMenuCode(926);

	}

	public Object getModel() {
		
		return Confbean;
	}

	public ConfigureMailAlerts getConfbean() {
		return Confbean;
	}

	public void setConfbean(ConfigureMailAlerts confbean) {
		Confbean = confbean;
	}

	
	/*@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		ConfigureMailAlertsModel model=new ConfigureMailAlertsModel();
		model.initiate(context, session);
		model.getSettingDtl(Confbean);
		model.terminate();
	}*/
	public String input()  {
		ConfigureMailAlertsModel model=new ConfigureMailAlertsModel();
		model.initiate(context,session);
		//model.getSettingDtl(Confbean);
		model.terminate();
		
		return SUCCESS;
	}
	
	
	
	
	public String saveSetting() {
		ConfigureMailAlertsModel model=new ConfigureMailAlertsModel();
		model.initiate(context, session);
		boolean result=false;
			//model.saveSetting(Confbean,request);
		if(result)
		{
			addActionMessage("Configure Mail Alerts Setting updated successfully.");
		}
		else
		{
			addActionMessage("Configure Mail Alerts Setting cann't updated.");
		}
	//	model.getSettingDtl(Confbean);
		model.terminate();
		return SUCCESS;
	}
	
	/*
	public String centralizeInput()  {
		ConfigureMailAlertsModel model=new ConfigureMailAlertsModel();
		model.initiate(context, session);
		model.getCentraliseSettingDtl(Confbean);
		model.terminate();
		
		return "centralizePage";
	}
	
	public String saveCentraliseSetting() {
		ConfigureMailAlertsModel model=new ConfigureMailAlertsModel();
		model.initiate(context, session);
		boolean result=model.saveCentraliseSetting(Confbean);
		if(result)
		{
			addActionMessage("Centralised Panel Setting updated successfully.");
		}
		else
		{
			addActionMessage("Centralised Panel Setting cann't updated.");
		}
		model.getCentraliseSettingDtl(Confbean);
		model.terminate();
		return "centralizeSuccess";
	}
	*/
}
