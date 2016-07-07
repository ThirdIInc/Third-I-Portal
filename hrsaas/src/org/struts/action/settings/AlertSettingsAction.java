/**
 * 
 */
package org.struts.action.settings;

import org.paradyne.bean.settings.SettingMaster;
import org.paradyne.model.settings.SettingMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 * 
 */
public class AlertSettingsAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	SettingMaster bean;
	String poolDir = "";
	String fileName = "";

	public void prepare_local() throws Exception {
		bean = new SettingMaster();
		poolDir = String.valueOf(session.getAttribute("session_pool"));
		bean.setMenuCode(666);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	/**
	 * Called On Load Set fields
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		// TODO Auto-generated method stub
		showEmail();
	}

	/**
	 * To add email information
	 * 
	 * @return String
	 */
	public String saveEmail() {
		try {
			SettingMasterModel model = new SettingMasterModel();
			model.initiate(context, session);
			String[] chkStatus = request.getParameterValues("chkemail");
			String[] linkCode = request.getParameterValues("linkCode_EM");
			// path to write XML
			fileName = getText("data_path") + "\\datafiles\\" + poolDir
					+ "\\xml\\email_info\\email_info.xml";
			int value = 0;
			if (chkStatus != null) {
				logger.info("chkemail " + chkStatus.length);
				logger.info("linkCode_EM " + linkCode.length);
				value = model.saveEmail(bean, fileName, chkStatus, linkCode);
				if (value == 0)
					addActionMessage("Record can't be saved!");
				else
					addActionMessage("Record saved successfully!");
			} // END if
			else
				addActionMessage("No link avialable to save");
			model.terminate();
			return "success";
		} catch (Exception e) {
			logger.info("Error while saving email" + e);
			addActionMessage("Error while saving email");
			return "success";
		}
	}

	/**
	 * From on load Setting fields on load
	 * 
	 * @return String
	 */
	public String showEmail() {
		SettingMasterModel model = new SettingMasterModel();
		model.initiate(context, session);
		model.showEmailInfo(bean);
		return "success";
	}

}
