package org.struts.action.pramotion;

import org.paradyne.bean.pramotion.PramotionMaster;
import org.struts.lib.ParaActionSupport;

public class PramotionMasterAction extends ParaActionSupport {

	PramotionMaster pramotion;
	
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub

	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return pramotion;
	}

	public PramotionMaster getPramotion() {
		return pramotion;
	}

	public void setPramotion(PramotionMaster pramotion) {
		this.pramotion = pramotion;
	}

}
