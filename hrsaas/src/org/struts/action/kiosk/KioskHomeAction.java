package org.struts.action.kiosk;

import org.paradyne.bean.kiosk.KioskMaster;
import org.struts.lib.KioskActionSupport;

public class KioskHomeAction extends KioskActionSupport{

static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(KioskHomeAction.class);

KioskMaster bean = null;
public Object getModel() {
	// TODO Auto-generated method stub
	return bean;
}

public void prepare() throws Exception {
	bean = new KioskMaster();
	
}



public String pfMonthlyReport(){
	return "pf_Monthly_Report";
}
public String salarySlip(){
	return "salary_slip";
}
public String pfSlip(){
	
	return "pf_slip";
}


@Override
public void prepare_local() throws Exception {
	// TODO Auto-generated method stub
	
}


}
