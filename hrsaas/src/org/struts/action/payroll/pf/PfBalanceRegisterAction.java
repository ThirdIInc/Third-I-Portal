/**
 * 
 */
package org.struts.action.payroll.pf;

import org.paradyne.bean.payroll.pf.PfBalanceRegister;
import org.paradyne.model.payroll.pf.PfBalanceRegisterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0554
 *
 */
public class PfBalanceRegisterAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	PfBalanceRegister pfReg;
	@Override
	public void prepare_local() throws Exception {
		pfReg =  new PfBalanceRegister();
		pfReg.setMenuCode(1102);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return pfReg;
	}
	
	public String f9Deptaction() throws Exception {
		String query = " SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_NAME ";
		String[] headers = {getMessage("department") };

		String[] headerWidth = {"100" };


		String[] fieldNames = { "deptName","deptCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String f9Div() throws Exception {
		String query = " SELECT DIV_NAME,DIV_ID FROM  HRMS_DIVISION ";

		if (pfReg.getUserProfileDivision() != null
				&& pfReg.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN (" + pfReg.getUserProfileDivision()
					+ ")";
		query += " ORDER BY  DIV_NAME ";
		String[] headers = { getMessage("division") };

		String[] headerWidth = { "100" };


		String[] fieldNames = { "divName","divCode" };

		int[] columnIndex = { 0, 1 };


		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * Following function is called in jsp page to show the branch code and name
	 */
	public String f9BranchAction() throws Exception {
		String query = " SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_NAME ";

		String[] headers = { getMessage("branch") };

		String[] headerWidth = { "100" };


		String[] fieldNames = {  "branchName","branchCode" };

		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String getPFBalanceReport() throws Exception {
		PfBalanceRegisterModel model = new PfBalanceRegisterModel();
		model.initiate(context, session);
		model.generateReport(pfReg,response);

		model.terminate();
		return null;
	}

}
