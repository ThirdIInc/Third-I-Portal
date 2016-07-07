/**
 * aa0417
   Jul 7, 2009
 */
package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.PartnerChangePassword;
import org.paradyne.model.recruitment.PartnerChangePasswordModel;

/**
 * @author aa0417
 *
 */
public class PartnerChangePasswordAction extends PartnerActionSupport {

 
	PartnerChangePassword pass;
	public void prepare_local() throws Exception { 
		pass = new PartnerChangePassword();
		pass.setMenuCode(908);
	} 
 
	public Object getModel() { 
		return pass;
	}

	public PartnerChangePassword getPass() {
		return pass;
	}

	public void setPass(PartnerChangePassword pass) {
		this.pass = pass;
	}
	
	public String input()
	{ 
		PartnerChangePasswordModel model= new PartnerChangePasswordModel();
		model.initiate(context, session);
	    model.callonLoad(pass);
		model.terminate(); 
		return "success";
	}
	
	public String save()
	{ 
		PartnerChangePasswordModel model= new PartnerChangePasswordModel();
		model.initiate(context, session);
	    String result = model.save(pass);
	    if(result.equals("success"))
	    {
	     addActionMessage(getMessage("success"));	
	    }
	    
	    if(result.equals("oldInvalid"))
	    {
	     addActionMessage(getMessage("oldInvalid"));	
	    }
		model.terminate(); 
		return "success";
	}

}
