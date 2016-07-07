package org.struts.action.kiosk;

import org.apache.log4j.Logger;
import org.paradyne.bean.payroll.SalarySlipMisReport;
import org.paradyne.bean.payroll.pf.PFSlipReport;
import org.paradyne.model.payroll.pf.PFSlipReportModel;
import org.struts.lib.KioskActionSupport;

public class KioskPFSlipAction extends KioskActionSupport {
	
	Logger logger = Logger.getLogger(KioskPFSlipAction.class);
	
	PFSlipReport pfslipbean;

	@Override
	public void prepare_local() throws Exception {

		pfslipbean =new  PFSlipReport();

	}

	public Object getModel() {
		return pfslipbean;
	}
	
	
	public PFSlipReport getPfslipbean() {
		return pfslipbean;
	}

	public void setPfslipbean(PFSlipReport pfslipbean) {
		this.pfslipbean = pfslipbean;
	}
	
public String pfSlipReport() {
		
		System.out.println("Im in pf slip method");
		
		try {
			PFSlipReportModel model = new PFSlipReportModel();
			model.initiate(context, session);
			pfslipbean.setSEmpId(pfslipbean.getUserEmpId());
			logger.info(" In If EmpCode------" + pfslipbean.getSEmpId());
			int financialYearTo = Integer.parseInt(pfslipbean.getSFinancialYearFrm())+1;
			String toYear = String.valueOf(financialYearTo);
			pfslipbean.setSFinancialYearTo(toYear);
			model.generateReport(pfslipbean, response,1);
			model.terminate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;		
	
	}

}
