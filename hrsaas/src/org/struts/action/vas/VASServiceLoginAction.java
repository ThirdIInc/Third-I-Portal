package org.struts.action.vas;

import org.paradyne.bean.vas.VASServiceLogin;
import org.paradyne.model.vas.VASServiceLoginModel;
import org.struts.lib.ParaActionSupport;

public class VASServiceLoginAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(VASServiceLoginAction.class);

	VASServiceLogin vasServLogin;

	public void prepare_local() throws Exception {
		vasServLogin = new VASServiceLogin();
		vasServLogin.setMenuCode(944);

	}

	public Object getModel() {
		return vasServLogin;
	}

	public VASServiceLogin getVasServLogin() {
		return vasServLogin;
	}

	public void setVasServLogin(VASServiceLogin vasServLogin) {
		this.vasServLogin = vasServLogin;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		callOnLoad();
	}

	public String callOnLoad() throws Exception {
		VASServiceLoginModel model = new VASServiceLoginModel();
		model.initiate(context, session);
		String accountsAdded = model.getAccountData(vasServLogin);
		if (accountsAdded.equals("accountsAdded")) {
			request.setAttribute("accountsAdded", accountsAdded);
		}
		model.terminate();
		return SUCCESS;
	}

}
