package org.struts.action.kiosk;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.Loan.LoanApplication;
import org.paradyne.bean.kiosk.KioskMaster;
import org.paradyne.model.kiosk.KioskModel;
import org.struts.lib.KioskActionSupport;

public class KioskPFBalanceReportAction extends KioskActionSupport {
	
	KioskMaster bean = null;

	
	public void prepare_local() throws Exception {
		bean = new KioskMaster();

	}

	public Object getModel() {
		return bean;
	}
	
	public String pfBalanceReport(){
		System.out.println("Im in pf balance report");
		try{
			System.out.println("------bean.getUserEmpId()------"+bean.getUserEmpId());
			KioskModel model = new KioskModel();
			model.initiate(context, session);
			model.getPFBalanceReport(bean,request);
			model.terminate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "pf_balance";
	}
	


}
