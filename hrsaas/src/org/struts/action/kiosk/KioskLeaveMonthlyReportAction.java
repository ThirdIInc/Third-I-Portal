package org.struts.action.kiosk;

import org.paradyne.bean.kiosk.KioskMaster;
import org.paradyne.model.kiosk.KioskModel;
import org.paradyne.model.leave.LeaveMisReportModel;
import org.struts.lib.KioskActionSupport;

import com.opensymphony.xwork2.ActionSupport;

public class KioskLeaveMonthlyReportAction extends KioskActionSupport {

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
		try {
			System.out.println("in report method---------------");
			KioskModel model = new KioskModel();
			model.initiate(context, session);
			String result = model.getKioskLeaveMonthReport(response, bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}// end of report

	public KioskMaster getBean() {
		return bean;
	}

	public void setBean(KioskMaster bean) {
		this.bean = bean;
	}

 
	
	
}
