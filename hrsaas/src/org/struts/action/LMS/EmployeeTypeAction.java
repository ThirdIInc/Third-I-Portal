package org.struts.action.LMS;

import java.util.LinkedHashMap;

import org.paradyne.bean.LMS.EmployeeTypeBean;
import org.paradyne.model.LMS.EmployeeTypeModel;
import org.struts.lib.ParaActionSupport;

public class EmployeeTypeAction extends ParaActionSupport {

	EmployeeTypeBean emptypeBean;

	public void prepare_local() throws Exception {

		emptypeBean = new EmployeeTypeBean();
		emptypeBean.setMenuCode(1135);

	}

	public Object getModel() {

		return emptypeBean;
	}

	public EmployeeTypeBean getEmptypeBean() {
		return emptypeBean;
	}

	public void setEmptypeBean(EmployeeTypeBean emptypeBean) {
		this.emptypeBean = emptypeBean;
	}

	/**This input function is get called for displaying Onload List*/
	public String input() throws Exception {

		EmployeeTypeModel model;
		try {
			model = new EmployeeTypeModel();
			model.initiate(context, session);
			model.emptypeData(emptypeBean, request,"Y");
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		getNavigationPanel(1);
		return "input";
	}

	

	public String save() throws Exception {

		try {
			EmployeeTypeModel model = new EmployeeTypeModel();
			model.initiate(context, session);
			boolean result = model.insertData(emptypeBean, request);
			if (result)
				addActionMessage("Record saved Successfully.");
			model.emptypeData(emptypeBean, request, "Y");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return "input";
	}

	public String reset() throws Exception {

		try {
			emptypeBean.setTypeofEmployment("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return "input";

	}

}
