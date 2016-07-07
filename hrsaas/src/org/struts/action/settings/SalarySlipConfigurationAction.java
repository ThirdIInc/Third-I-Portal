package org.struts.action.settings;

import org.paradyne.bean.settings.SalarySlipConfiguration;
import org.paradyne.model.settings.SalarySlipConfigurationModel;
import org.struts.lib.ParaActionSupport;

public class SalarySlipConfigurationAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SalarySlipConfigurationAction.class);

	SalarySlipConfiguration salarySlipBean = null;
	public SalarySlipConfiguration getSalarySlipBean() {
		return salarySlipBean;
	}

	public void setSalarySlipBean(SalarySlipConfiguration salarySlipBean) {
		this.salarySlipBean = salarySlipBean;
	}

	@Override
	public void prepare_local() throws Exception {
		salarySlipBean = new SalarySlipConfiguration();
		salarySlipBean.setMenuCode(1117);
	}

	public Object getModel() {
		return salarySlipBean;
	}
	
	public String input() throws Exception {
		try {
			SalarySlipConfigurationModel model = new SalarySlipConfigurationModel();
			model.initiate(context, session);
			model.getSalaryConfigurationSettings(salarySlipBean, request);
			model.terminate();
		} catch (Exception e) {
		}
		return INPUT ;
	}
	
	public String saveSettings(){
		try {
			boolean result = false;
			SalarySlipConfigurationModel model = new SalarySlipConfigurationModel();
			model.initiate(context, session);
			result = model.saveSettings(salarySlipBean);
			if (result) {
				addActionMessage("Record saved successfully");
			} else {
				addActionMessage("Record could not be saved");
			}
			model.getSalaryConfigurationSettings(salarySlipBean, request);
			model.terminate();
		} catch (Exception e) {
		}
		return INPUT;
		
	}

}
