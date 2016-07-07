package org.struts.action.kiosk;

import org.paradyne.bean.kiosk.KioskMaster;
import org.paradyne.model.kiosk.KioskModel;
import org.struts.lib.KioskActionSupport;

public class KioskLICPolicyReportAction extends KioskActionSupport {

KioskMaster bean = null;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new KioskMaster();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}
	
	public String report() {
		System.out.println("in report LIC report method---------------");
		KioskModel model = new KioskModel();
		model.initiate(context, session);
		String result = model.getKioskLICPolicyReport(response,bean);
		model.terminate();
		return null;

	}// end of report

	public KioskMaster getBean() {
		return bean;
	}

	public void setBean(KioskMaster bean) {
		this.bean = bean;
	}
	

}
