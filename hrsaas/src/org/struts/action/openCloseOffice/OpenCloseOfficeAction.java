/**
 * 
 */
package org.struts.action.openCloseOffice;

import org.paradyne.bean.openCloseOffice.OpenCloseOffice;
import org.paradyne.bean.openCloseOffice.OpenCloseOfficeConfig;
import org.paradyne.model.openCloseOffice.OpenCloseOfficeModel;
import org.struts.action.common.LoginAction;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 *
 */
public class OpenCloseOfficeAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(OpenCloseOfficeAction.class);
	
	OpenCloseOffice openCloseOffice;
	
	public OpenCloseOffice getOpenCloseOffice() {
		return openCloseOffice;
	}

	public void setOpenCloseOffice(OpenCloseOffice openCloseOffice) {
		this.openCloseOffice = openCloseOffice;
	}

	public void prepare_local() throws Exception {
		
		openCloseOffice = new OpenCloseOffice();
		openCloseOffice.setMenuCode(1059);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return openCloseOffice;
	}
	
	public String input(){
		OpenCloseOfficeModel model = new OpenCloseOfficeModel();
		model.initiate(context, session);
		int value = model.checkAccess(openCloseOffice);
		
		if(value == 1){
			model.terminate();
			return SUCCESS;
		}
		model.terminate();
		return "noAccess";
	}
	
	
	public String open(){
		OpenCloseOfficeModel model = new OpenCloseOfficeModel();
		model.initiate(context, session);
		model.open(openCloseOffice,request);
		model.terminate();
		return SUCCESS;
	} //end of open method
	
	public String close(){
		OpenCloseOfficeModel model = new OpenCloseOfficeModel();
		model.initiate(context, session);
		model.close(openCloseOffice,request);
		model.terminate();
		return SUCCESS;
	} //end of open method
	
	

}
