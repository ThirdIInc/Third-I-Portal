package org.struts.action.payroll.pension;

import org.paradyne.bean.payroll.pension.PensionSlip;
import org.paradyne.model.payroll.pension.PensionSlipModel;
import org.struts.lib.ParaActionSupport;

public class PensionSlipAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(PensionSlipAction.class);
	
	PensionSlip bean = null;
	
	@Override
	public void prepare_local() throws Exception {
		bean = new PensionSlip();
		bean.setMenuCode(952);
	}

	public Object getModel() {
		return bean;
	}
	
	public void setPensionSlip(PensionSlip bean) {
		this.bean = bean;
	}
	
	public String input() throws Exception {
		getNavigationPanel(1);
		
		if (bean.isGeneralFlag()) {
			PensionSlipModel model = new PensionSlipModel();
			model.initiate(context, session);
			bean.setSEmpId(bean.getUserEmpId());
			model.getLoginUserInfo(bean, request);
			model.terminate();
		}
		
		return INPUT;
	}
	
	public String report() throws Exception {
		PensionSlipModel model = new PensionSlipModel();
		model.initiate(context, session);
		model.generateReport(bean, response);
		model.terminate();
		return null;
	}
	
	public String f9Div() {
		try {
			String query = 	" SELECT " 
						 	+ " 	DISTINCT DIV_NAME, " 
						 	+ "		DIV_ID  " 
						 	+ " FROM " 
						 	+ " 	HRMS_EMP_OFFC A "
						 	+ " 	INNER JOIN HRMS_DIVISION B ON (A.EMP_DIV = B.DIV_ID) ";
						 	
							 
							if(bean.getUserProfileDivision() !=null && bean.getUserProfileDivision().length()>0)
								query+= " WHERE DIV_ID IN ("+bean.getUserProfileDivision() +")"; 
								query+= " ORDER BY  DIV_NAME ";
					 
			String[] headers = { getMessage("division") };

			String[] headerWidth = { "100" };

			String[] fieldNames = { "sDivName", "sDivId" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}

	public String f9Brch() {
		try {
			String query = 	" SELECT "
							+ " 	DISTINCT CENTER_NAME,"
							+ "		CENTER_ID "
							+ " FROM "
							+ " 	HRMS_EMP_OFFC A"
							+ " 	INNER JOIN HRMS_CENTER B ON (A.EMP_CENTER = B.CENTER_ID )  "
							+ " ORDER BY " 
							+ "		CENTER_NAME ";

			String[] headers = { getMessage("branch") };

			String[] headerWidth = { "100" };

			String[] fieldNames = { "sBrchName", "sBrchId" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}

	public String f9Dept() {
		try {
			String query = 	" SELECT "
							+ "		DISTINCT DEPT_NAME,"
							+ "		DEPT_ID "
							+ "	FROM "
							+ " 	HRMS_EMP_OFFC A "
							+ " 	INNER JOIN HRMS_DEPT B ON (A.EMP_DEPT = B.DEPT_ID) "
							+ " ORDER BY " 
							+ "		DEPT_NAME ";

			String[] headers = { getMessage("department") };

			String[] headerWidth = { "100" };

			String[] fieldNames = { "sDeptName", "sDeptId" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}
	
	public String f9Employee()throws Exception	{
		String query = 	" SELECT "
						+ "		DISTINCT EMP_TOKEN, "
						+ "		(EMP_FNAME || ' ' || EMP_MNAME|| ' ' || EMP_LNAME) AS EMPLOYEE_NAME, "
						+ "		EMP_ID "
						+ " FROM " 
						+ "		HRMS_EMP_OFFC ";
		
		query += getprofileQuery(bean);
 
		query +=" AND EMP_STATUS = 'R'  ORDER BY  EMPLOYEE_NAME ";
		
		String[] headers = {"Employee Code", "Employee Name"};
		String[] headerWidth = { "50", "50" };
		String[] fieldNames = {"sEmpCode","sEmpName", "sEmpId" };
		int[] columnIndex = { 0,1,2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String f9Designation()throws Exception {
		String query = 	" SELECT "
						+ "		DISTINCT RANK_NAME, " 
						+ "		RANK_ID "
						+ " FROM "
						+ "		HRMS_EMP_OFFC A "
						+ " 	INNER JOIN HRMS_RANK B ON (A.EMP_RANK = B.RANK_ID) "
						+ " ORDER BY " 
						+ "		RANK_NAME ";
		
		String[] headers = { "Designation" };
		String[] headerWidth = { "100" };
		String[] fieldNames = {"sDesignation","sDesignationCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
}
