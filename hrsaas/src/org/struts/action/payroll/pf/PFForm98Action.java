package org.struts.action.payroll.pf;

import org.paradyne.bean.payroll.pf.PfForm98;
import org.paradyne.model.payroll.pf.PfForm98Model;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author REEBA JOSEPH
 * 01 NOVEMBER 2010
 *
 */

public class PFForm98Action extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(PFForm98Action.class);
	
	PfForm98 form98 = null;
	
	public PfForm98 getForm98() {
		return form98;
	}

	public void setForm98(PfForm98 form98) {
		this.form98 = form98;
	}

	@Override
	public void prepare_local() throws Exception {
		form98 = new PfForm98();
		form98.setMenuCode(1100);

	}

	public Object getModel() {
		return form98;
	}
	
	/**
	 * Method to search employees
	 * @return
	 * @throws Exception
	 */
	public String f9Employee() throws Exception {
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
			+" (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) AS NAME ,NVL(CENTER_NAME,' ') "
			+" ,HRMS_EMP_OFFC.EMP_ID,EMP_STATUS, DIV_NAME,DEPT_NAME,RANK_NAME,NVL(SAL_GPFNO,' ') "
			+" FROM HRMS_EMP_OFFC  "
			+" INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
			+" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)"
			+" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
			+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
			+" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
			+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
			+" WHERE SAL_PFTRUST_FLAG='Y' AND "
			+" EMP_STATUS='S'  ORDER BY UPPER(NAME)";


		String[] headers = {getMessage("employee.id"),getMessage("employee"),getMessage("branch") };
	
		String[] headerWidth = { "15", "35","25" };
	
		String[] fieldNames = { "empToken",
				"empName","branchName","empCode","empStatus","divName","deptName","desgName","empPfNo" };
	
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	
		String submitFlag = "false";
	
		String submitToMethod = "";
	
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}//end of method f9Employee
	
	/**
	 * Method to reset form fields
	 * @return
	 * @throws Exception
	 */
	public String reset() throws Exception {
		form98.setEmpCode("");
		form98.setEmpToken("");
		form98.setEmpName("");
		form98.setFromMonth("");
		form98.setFromYear("");
		form98.setToMonth("");
		form98.setToYear("");
		form98.setBranchName("");
		form98.setDeptName("");
		form98.setDivName("");
		form98.setDesgName("");
		form98.setEmpPfNo("");
		form98.setEmpStatus("");
		return SUCCESS;
	}//end of method reset
	
	/**
	 * Method to generate report
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception {
		PfForm98Model model = new PfForm98Model();
		model.initiate(context, session);
		model.getReport(form98, response);
		model.terminate();
		return null;
	}//end of method report

}
