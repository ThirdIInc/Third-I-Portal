/**
 * 
 */
package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.ConsultantPortal;
import org.paradyne.model.recruitment.ConsultantPortalModel;

/**
 * @author AA0517
 *
 */
public class ConsultantPortalAction extends PartnerActionSupport {
static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.DyneActionSupport.class);
	
	ConsultantPortal consultantPortal;
	
	public void prepare_local() throws Exception {
		consultantPortal=new ConsultantPortal();
		consultantPortal.setMenuCode(907);
	}


public Object getModel() {
	return consultantPortal;
}
 
	public ConsultantPortal getManPowerReqs() {
	return consultantPortal;
}

public void setManPowerReqs(ConsultantPortal consultantPortal) {
	this.consultantPortal = consultantPortal;
}

	public String input()
	{
		ConsultantPortalModel model = new ConsultantPortalModel();
		model.initiate(context, session);
		model.callPartnerJobBorad(consultantPortal,request);
		model.terminate();
		return "success";
	}

}
