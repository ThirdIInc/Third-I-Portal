package org.struts.action.settings;

import org.paradyne.bean.settings.EmailConfig;
import org.struts.lib.ParaActionSupport;

public class EmailConfigAction extends ParaActionSupport {

	EmailConfig emailConfig;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		emailConfig=new EmailConfig();

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return emailConfig;
	}

}
