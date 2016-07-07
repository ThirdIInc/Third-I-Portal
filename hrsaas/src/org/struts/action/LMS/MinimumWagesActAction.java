package org.struts.action.LMS;

import org.paradyne.bean.LMS.GeneralInfo;
import org.paradyne.bean.LMS.MinimumWages;
import org.paradyne.bean.LMS.MinimumWagesActBean;
import org.paradyne.bean.LMS.WorkForceInfo;
import org.paradyne.model.LMS.MinimumWagesActModel;
import org.struts.lib.ParaActionSupport;

public class MinimumWagesActAction extends ParaActionSupport {

	MinimumWagesActBean minwageBean;
	GeneralInfo generalInfo;
	WorkForceInfo workForceInfo;
	MinimumWages minimumWages;

	/**
	 * @return the minimumWages
	 */
	public MinimumWages getMinimumWages() {
		return minimumWages;
	}

	/**
	 * @param minimumWages the minimumWages to set
	 */
	public void setMinimumWages(MinimumWages minimumWages) {
		this.minimumWages = minimumWages;
	}

	/**
	 * @return the workForceInfo
	 */
	public WorkForceInfo getWorkForceInfo() {
		return workForceInfo;
	}

	/**
	 * @param workForceInfo the workForceInfo to set
	 */
	public void setWorkForceInfo(WorkForceInfo workForceInfo) {
		this.workForceInfo = workForceInfo;
	}

	public void prepare_local() throws Exception {
		minwageBean = new MinimumWagesActBean();
		minwageBean.setMenuCode(1138);
	}

	public Object getModel() {

		return minwageBean;
	}

	public MinimumWagesActBean getMinwageBean() {
		return minwageBean;
	}

	public void setMinwageBean(MinimumWagesActBean minwageBean) {
		this.minwageBean = minwageBean;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		MinimumWagesActModel model = new MinimumWagesActModel();
		model.initiate(context, session);
		
		/*String query = "SELECT * FROM hrms_emp_offc";
		Object[][] obj = model.getSqlModel().getSingleResult(query);
		System.out.println("sql ================="+String.valueOf(obj[0][0]));*/
		
		String query = "SELECT * FROM TBL_LMS_EMPLOYEEROLE";
		Object[][] obj = model.getLMSSqlModel().getSingleResult(query);
		System.out.println("lms sql================="+String.valueOf(obj[0][0]));
		
		
		
		
		String orgId = "1";
		generalInfo = model.getLabourGeneralInfo(orgId);
		System.out.println("value======="+generalInfo.getName_Occupier());
		workForceInfo = model.getLabourWorkforceInfo(orgId);
		
		if(!model.checkIfEmpTypeConfigured())
			addActionMessage("emp type not configured");
		else
			addActionMessage("emp type configured");
		model.terminate();

	}

	/** This input function is get called for displaying Onload List */
	public String input() {
		System.out.println("m in input");

		getNavigationPanel(1);
		return "input";
	}

	public String next() {
		try {
			MinimumWagesActModel model = new MinimumWagesActModel();
			model.initiate(context, session);
			String orgId = "46";
			String month = "8";
			String year = "2010";
			minwageBean.setMonth(month);
			minwageBean.setYear(year);
			minimumWages = model.getLabourWageRecords(orgId, month, year, request);
			model.terminate();
			getNavigationPanel(2);
			return "next";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String cancel() {
		try {

			getNavigationPanel(1);
			return "input";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @return the generalInfo
	 */
	public GeneralInfo getGeneralInfo() {
		return generalInfo;
	}

	/**
	 * @param generalInfo the generalInfo to set
	 */
	public void setGeneralInfo(GeneralInfo generalInfo) {
		this.generalInfo = generalInfo;
	}

}
