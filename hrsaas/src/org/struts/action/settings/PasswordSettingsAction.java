package org.struts.action.settings;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.settings.SettingMaster;
import org.paradyne.model.settings.SettingMasterModel;

/**
 * @author Pankaj_Jain
 * 
 */
public class PasswordSettingsAction extends ParaActionSupport {

	SettingMaster bean;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		bean = new SettingMaster();
		bean.setMenuCode(663);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	/**
	 * Call on load Setting Password Settings on Load
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		// TODO Auto-generated method stub
		showPassSetting();
	}

	/**
	 * Saving PAssword Settings
	 * 
	 * @return String
	 */
	public String savePasswordSetting() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		// save
		boolean flag = model.savePassSetting(bean);
		if (flag)
			addActionMessage("Settings Saved Successfully");
		else
			addActionMessage("Settings Cant be Saved.");
		// Set Password settings field after save
		model.showPassSetting(bean);
		model.terminate();
		return "success";
	}

	/**
	 * From on load Setting fields on load and after save
	 * 
	 * @return
	 */
	public String showPassSetting() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.showPassSetting(bean);
		model.terminate();
		return "success";
	}

}
