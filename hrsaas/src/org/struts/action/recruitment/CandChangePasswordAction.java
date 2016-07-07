package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.CandChangePassword;
import org.paradyne.model.recruitment.CandChangePasswordModel;
import org.struts.lib.DyneActionSupport;

/**
 * @author aa0517
 * 
 */
public class CandChangePasswordAction extends DyneActionSupport {
	CandChangePassword chngPass = new CandChangePassword();

	public void prepare_local() throws Exception {
		chngPass = new CandChangePassword();
		chngPass.setMenuCode(903);
	}

	public Object getModel() {
		return chngPass;
	}

	public CandChangePassword getChngPass() {
		return chngPass;
	}

	public void setChngPass(CandChangePassword chngPass) {
		this.chngPass = chngPass;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			CandChangePasswordModel model = new CandChangePasswordModel();
			model.initiate(context, session);
			model.getRecord(chngPass);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String save() throws Exception {
		try {
			CandChangePasswordModel model = new CandChangePasswordModel();
			model.initiate(context, session);
			String result = model.save(chngPass);
			if (result.equals("1")) {
				addActionMessage("Please enter old password correctly.");
			} else if (result.equals("2")) {
				java.io.PrintWriter out = response.getWriter();
				addActionMessage("Password changed successfully.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
