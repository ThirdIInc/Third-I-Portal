/* Bhushan Dasare Dec 2, 2009 */

package org.struts.action.ApplicationStudio.configAuth;

import java.io.PrintWriter;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.model.ApplicationStudio.configAuth.AuthorizationCheckingModel;
import org.struts.lib.ParaActionSupport;

public class AuthorizationCheckingAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AuthorizationCheckingAction.class);
	BeanBase bean;
	
	public void doAuthorization() {
		try {
			AuthorizationCheckingModel model = new AuthorizationCheckingModel();
			model.initiate(context, session);
			
			String loginEmpId = bean.getUserEmpId();
			
			String authPassword = request.getParameter("authPassword");
			authPassword = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(authPassword);
			
			String moduleId = request.getParameter("moduleId");
			
			String message = model.doAuthorization(loginEmpId, authPassword, moduleId);
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(message);
			out.flush();
		} catch(Exception e) {
			logger.error("Exception in doAuthorization in action:" + e);
		}
	}

	public BeanBase getBean() {
		return bean;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return bean;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		bean = new BeanBase();
	}

	public void setBean(BeanBase bean) {
		this.bean = bean;
	}
}