package org.struts.action.payroll.pf;

import org.paradyne.bean.payroll.pf.PFSlipReport;
import org.paradyne.model.payroll.pf.PFSlipReportModel;
import org.struts.lib.ParaActionSupport;

public class PFSlipReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PFSlipReportAction.class);

	PFSlipReport bean = null;

	@Override
	public void prepare_local() throws Exception {
		bean = new PFSlipReport();
		bean.setMenuCode(934);
	}

	public Object getModel() {
		return bean;
	}

	public void setPFSlipReport(PFSlipReport bean) {
		this.bean = bean;
	}

	public String input() throws Exception {
		getPopulateData();
		getNavigationPanel(1);
		return INPUT;
	}
	
	private void getPopulateData() {
		PFSlipReportModel model = new PFSlipReportModel();
		model.initiate(context, session);
		if (bean.isGeneralFlag())
		{
			bean.setSEmpId(bean.getUserEmpId());
			model.getLoginUserInfo(bean, request);
		}
		model.terminate();
	}

	public String f9Div() {
		try {
			/*String query = 	" SELECT " 
						 	+ " 	DISTINCT DIV_NAME, " 
						 	+ "		DIV_ID  " 
						 	+ " FROM " 
						 	+ " 	HRMS_SALARY_MISC A " 
						 	+ " 	INNER JOIN HRMS_PF_LEDGER B ON (A.EMP_ID = B.PF_EMPID) AND A.SAL_PFTRUST_FLAG = 'Y' "
						 	+ " 	INNER JOIN HRMS_EMP_OFFC C ON (A.EMP_ID = C.EMP_ID) "
						 	+ "  	INNER JOIN HRMS_DIVISION D ON (D.DIV_ID = C.EMP_DIV) ";*/
		 	 
			String query = 	" SELECT " 
			 	+ " 	DISTINCT DIV_NAME, " 
			 	+ "		DIV_ID  " 
			 	+ " FROM " 
			 	+ " 	HRMS_SALARY_MISC A " 
			 	+ " 	INNER JOIN HRMS_PF_LEDGER B ON (A.EMP_ID = B.PF_EMPID) AND A.SAL_PFTRUST_FLAG = 'Y' "
			 	+ " 	INNER JOIN HRMS_EMP_OFFC   ON (A.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
			 	+ "  	INNER JOIN HRMS_DIVISION D ON (D.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) ";
			
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
							+ "		HRMS_SALARY_MISC A "
							+ " 	INNER JOIN HRMS_PF_LEDGER B ON (A.EMP_ID = B.PF_EMPID) AND A.SAL_PFTRUST_FLAG = 'Y' "
							+ " 	INNER JOIN HRMS_EMP_OFFC C ON (A.EMP_ID = C.EMP_ID) "
							+ "   	INNER JOIN HRMS_CENTER D ON (D.CENTER_ID = C.EMP_CENTER) "
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
							+ "		HRMS_SALARY_MISC A "
							+ " 	INNER JOIN HRMS_PF_LEDGER B ON (A.EMP_ID = B.PF_EMPID) AND A.SAL_PFTRUST_FLAG = 'Y' "
							+ " 	INNER JOIN HRMS_EMP_OFFC C ON (A.EMP_ID = C.EMP_ID) "
							+ " 	INNER JOIN HRMS_DEPT D ON (D.DEPT_ID = C.EMP_DEPT) "
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
	
	public String f9Employee()throws Exception
	{
		String query = 	" SELECT "
						+ "		DISTINCT HRMS_EMP_OFFC.EMP_TOKEN, "
						+ "		(HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) AS EMPLOYEE_NAME, "
						+ "		HRMS_EMP_OFFC.EMP_ID "
						+ " FROM "
						+ "		HRMS_SALARY_MISC A  "
						+ "		INNER JOIN HRMS_PF_LEDGER B ON (A.EMP_ID = B.PF_EMPID) AND A.SAL_PFTRUST_FLAG = 'Y' "
						+ " 	INNER JOIN HRMS_EMP_OFFC  ON (A.EMP_ID = HRMS_EMP_OFFC.EMP_ID) ";
		
		query += getprofileQuery(bean);
		
		query +=" ORDER BY EMPLOYEE_NAME ";
		
		String[] headers = {"Employee Code", "Employee Name"};
		String[] headerWidth = { "50", "50" };
		String[] fieldNames = {"sEmpCode","sEmpName", "sEmpId" };
		int[] columnIndex = { 0,1,2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String f9Designation()throws Exception
	{
		String query = 	" SELECT "
						+ "		DISTINCT RANK_NAME, " 
						+ "		RANK_ID "
						+ " FROM "
						+ "		HRMS_SALARY_MISC A "
						+ "		INNER JOIN HRMS_PF_LEDGER B ON (A.EMP_ID = B.PF_EMPID) AND A.SAL_PFTRUST_FLAG = 'Y' "
						+ " 	INNER JOIN HRMS_EMP_OFFC C ON (A.EMP_ID = C.EMP_ID) "	
						+ " 	INNER JOIN HRMS_RANK D ON (D.RANK_ID = C.EMP_RANK) "
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
	
	public String report() throws Exception
	{
		PFSlipReportModel model = new PFSlipReportModel();
		model.initiate(context, session);
		model.generateReport(bean, response,0);
		model.terminate();
		return null;
	}

}
