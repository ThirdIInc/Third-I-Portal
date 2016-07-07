/**
 * 
 */
package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.ConsultantBoard;
import org.paradyne.model.recruitment.ConsultantBoardModel;


/**
 * @author AA0517
 *
 */
public class ConsultantBoardAction extends PartnerActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.DyneActionSupport.class);

	ConsultantBoard manPowerReqs;

	public void prepare_local() throws Exception {
		manPowerReqs = new ConsultantBoard();
		manPowerReqs.setMenuCode(1118);
	}

	public void prepare_withLoginProfileDetails() throws Exception {

	}

	public Object getModel() {
		return manPowerReqs;
	}

	public ConsultantBoard getManPowerReqs() {
		return manPowerReqs;
	}

	public void setManPowerReqs(ConsultantBoard manPowerReqs) {
		this.manPowerReqs = manPowerReqs;
	}

	public String input() {
		ConsultantBoardModel model = new ConsultantBoardModel();
		model.initiate(context, session);
		model.callPartnerJobBorad(manPowerReqs, request);
		model.terminate();
		return "success";
	}

}
