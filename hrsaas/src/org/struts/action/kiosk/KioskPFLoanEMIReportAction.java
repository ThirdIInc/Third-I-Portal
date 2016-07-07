package org.struts.action.kiosk;

import org.paradyne.bean.kiosk.KioskMaster;
import org.paradyne.model.kiosk.KioskModel;
import org.struts.lib.KioskActionSupport;

import com.opensymphony.xwork2.ActionSupport;

public class KioskPFLoanEMIReportAction extends KioskActionSupport {
	KioskMaster bean = null;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new KioskMaster();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public KioskMaster getBean() {
		return bean;
	}

	public void setBean(KioskMaster bean) {
		this.bean = bean;
	}

	public String pfLoanEMIReport() {
		System.out.println("Im in pf loan EMI method");

		try {
			System.out.println("------bean.getUserEmpId()------"
					+ bean.getUserEmpId());
			KioskModel model = new KioskModel();
			model.initiate(context, session);
			// String result = model.getKioskPFLoanEMIReport(response, bean);
			model.getKioskPFLoanEMIReport(response, bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
