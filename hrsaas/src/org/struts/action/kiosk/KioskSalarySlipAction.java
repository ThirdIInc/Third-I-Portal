package org.struts.action.kiosk;

import org.apache.log4j.Logger;

import org.paradyne.bean.kiosk.KioskMaster;
import org.paradyne.bean.kiosk.KioskSalarySlipMaster;
import org.paradyne.bean.payroll.SalarySlipMisReport;
import org.paradyne.model.kiosk.KioskModel;
import org.paradyne.model.payroll.SalarySlipMisReportModel;
import org.struts.lib.KioskActionSupport;

public class KioskSalarySlipAction extends KioskActionSupport {
	SalarySlipMisReport salMisbean;

	
	Logger logger = Logger.getLogger(KioskSalarySlipAction.class);

	public Object getModel() {
		return salMisbean;
	}
	
	 
	public SalarySlipMisReport getSalMisbean() {
		return salMisbean;
	}


	public void setSalMisbean(SalarySlipMisReport salMisbean) {
		this.salMisbean = salMisbean;
	}


	public void prepare_local() throws Exception {
		salMisbean =new  SalarySlipMisReport();
	}
	
	public String salarySlipReport() {

		try {
		 	
			SalarySlipMisReportModel model = new SalarySlipMisReportModel();
			model.initiate(context, session);
				logger.info(" In If EmpCode------" + salMisbean.getEmpCode());
				salMisbean.setEmpCode(salMisbean.getUserEmpId());
				setDivCode(salMisbean,salMisbean.getUserEmpId());
				String [] headers={getMessage("role"),getMessage("department"),getMessage("employee.id"),getMessage("bank.name"),
						getMessage("employee.type"),getMessage("pay.mode"),getMessage("doj"),getMessage("sal.name"),getMessage("PF.number"),getMessage("PAN.number"),
						getMessage("acc.no"),getMessage("grade"),getMessage("sal.grad"),getMessage("branch"),getMessage("designation"),getMessage("payBill"),getMessage("trade"),getMessage("reporting.to")};
				model.generateReport(request, response, salMisbean,1,headers);
				model.terminate();
				return null;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	public void setDivCode(SalarySlipMisReport salMisbean,String loginUserEmpCode) {
		try {
			SalarySlipMisReportModel model = new SalarySlipMisReportModel();
			model.initiate(context, session);
			String query = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+loginUserEmpCode;
			Object divCodeObj[][] = model.getSqlModel().getSingleResult(query);
			if (divCodeObj != null && divCodeObj.length > 0) {
				salMisbean.setSalDivisionId(String.valueOf(divCodeObj[0][0]));
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	 
	
}
