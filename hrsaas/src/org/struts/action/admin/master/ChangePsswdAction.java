/**
 * 
 */
package org.struts.action.admin.master;
import org.paradyne.bean.admin.master.ChangePsswdBean;
import org.paradyne.lib.StringEncrypter.EncryptionException;
import org.paradyne.model.admin.master.ChangePsswdModel;

/**
 * @author MuzaffarS
 *
 */
public class ChangePsswdAction extends org.struts.lib.ParaActionSupport {
	ChangePsswdBean bean;

	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		bean =new ChangePsswdBean();
		
	}

	 
	public Object getModel() {
		
		return bean;
	}
	public String changePass()
	{
		ChangePsswdModel model =new ChangePsswdModel();
		model.initiate(context,session);
		
		try {
			model.changePsswd(bean);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		model.terminate();
		return "success";
		
	}
	public String savePsswd() throws EncryptionException
	{	
		logger.info(" the change password is");
		ChangePsswdModel model =new ChangePsswdModel();
		model.initiate(context,session);
		model.savePsswd(bean);
		
		bean.setNewpsswd1("");
		bean.setNewpsswd2("");
		bean.setOldpsswd("");
		model.terminate();
		return "success";
		
	}

}
