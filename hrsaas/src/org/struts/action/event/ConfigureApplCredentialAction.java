package org.struts.action.event;

import org.paradyne.bean.event.ConfigureApplCredential;
import org.paradyne.model.event.CeoConfigurationModel;
import org.paradyne.model.event.ConfigureApplCredentialModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.struts.lib.ParaActionSupport;

public class ConfigureApplCredentialAction extends ParaActionSupport {

	ConfigureApplCredential bean;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new ConfigureApplCredential();
		bean.setMenuCode(1168);
	}
	
	
	public String input() {
		try {
			ConfigureApplCredentialModel model = new ConfigureApplCredentialModel();
			model.initiate(context, session);
			String applCode =request.getParameter("applCode");
			System.out.println("applCode  "+applCode);
			model.terminate();
		} catch (Exception e) {
			 
		}
		return SUCCESS;
	}
	 

	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			ConfigureApplCredentialModel model = new ConfigureApplCredentialModel();
			model.initiate(context, session);
			String applCode =request.getParameter("applCode");
			System.out.println("applCode  "+applCode);
			try {
				bean.setApplicationCode(applCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			model.setEmployeeInfo(bean);
			model.displayData(bean);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public ConfigureApplCredential getBean() {
		return bean;
	}

	public void setBean(ConfigureApplCredential bean) {
		this.bean = bean;
	}

	public String reset() {
		bean.setEmpCode("");
		bean.setEmpToken("");
		bean.setEmpName("");
		bean.setApplicationCode("");
		bean.setUserName("");
		bean.setUserPassword("");

		return SUCCESS;
	}

	public String save() {
		try {
			ConfigureApplCredentialModel model = new ConfigureApplCredentialModel();
			model.initiate(context, session);

			String query = "select * from HRMS_APPL_CREDENTIAL "
					+ " where HRMS_APPL_EMPCODE=" + bean.getUserEmpId();
			Object data[][] = model.getSqlModel().getSingleResult(query);
			boolean res = model.saveData(bean);
			if (res) {
				addActionMessage("Record saved successfully");
				model.displayData(bean);

			} else {
				addActionMessage("Record can not be saved");

			}
			bean.setApplicationCode("");
			bean.setUserName("");
			bean.setUserPassword("");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String f9employee() throws Exception {

		try {

			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
					+ "	,EMP_ID " + " FROM HRMS_EMP_OFFC    ";

			query += " WHERE 1=1 AND EMP_STATUS='S'";
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0)
				query += "AND EMP_DIV IN (" + bean.getUserProfileDivision()
						+ ")";

			query += "ORDER BY EMP_ID ";
			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "empToken", "empName", "empCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

}
