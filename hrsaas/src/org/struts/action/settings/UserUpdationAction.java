package org.struts.action.settings;

import org.paradyne.bean.settings.UserUpdation;
import org.paradyne.model.settings.UserUpdationModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author reebaj
 *
 */

public class UserUpdationAction extends ParaActionSupport {
	
	UserUpdation userUpdation;

	public UserUpdationAction() {
		// TODO Auto-generated constructor stub
	}

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		userUpdation = new UserUpdation();
		userUpdation.setMenuCode(583);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return userUpdation;
	}

	public UserUpdation getUserUpdation() {
		return userUpdation;
	}

	public void setUserUpdation(UserUpdation userUpdation) {
		this.userUpdation = userUpdation;
	}
	
	public String process()throws Exception{
		
		logger.info("inside process action before model...............");
		UserUpdationModel model = new UserUpdationModel();
		model.initiate(context, session);
		model.process(userUpdation);
		logger.info("inside process action after model...............");
		model.terminate();
		
		return "success";
	}

}
