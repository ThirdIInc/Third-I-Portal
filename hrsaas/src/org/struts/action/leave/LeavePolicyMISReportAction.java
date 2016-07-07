/**
 * 
 */
package org.struts.action.leave;

import org.paradyne.bean.leave.LeavePolicyMIS;
import org.paradyne.model.leave.LeavePolicyMISReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0476
 *
 */
public class LeavePolicyMISReportAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	LeavePolicyMIS leavePolicyMIS;

	public LeavePolicyMIS getLeavePolicyMIS() {
		return leavePolicyMIS;
	}

	public void setLeavePolicyMIS(LeavePolicyMIS leavePolicyMIS) {
		this.leavePolicyMIS = leavePolicyMIS;
	}

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		leavePolicyMIS = new LeavePolicyMIS();
		leavePolicyMIS.setMenuCode(846);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		if (leavePolicyMIS.isGeneralFlag()) {
			LeavePolicyMISReportModel model = new LeavePolicyMISReportModel();
			model.initiate(context, session);
			model.getEmployeeDetails(leavePolicyMIS.getUserEmpId(),
					leavePolicyMIS);
			model.terminate();
		}//end of if
	}//end of prepare_withLoginProfileDetails

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return leavePolicyMIS;
	}

	public String reset() {

		if (!leavePolicyMIS.isGeneralFlag()) {
			leavePolicyMIS.setTokenNo("");
			leavePolicyMIS.setDivisionCode("");
			leavePolicyMIS.setDivisionName("");
			leavePolicyMIS.setEmployeeCode("");
			leavePolicyMIS.setEmployeeName("");
		}
		leavePolicyMIS.setDeptCode("");
		leavePolicyMIS.setDeptName("");
		leavePolicyMIS.setBranchCode("");
		leavePolicyMIS.setBranchName("");
		leavePolicyMIS.setEmpTypeName("");
		leavePolicyMIS.setEmpTypeCode("");
		leavePolicyMIS.setDesgCode("");
		leavePolicyMIS.setDesgName("");
		leavePolicyMIS.setReportType("Pdf");
		return SUCCESS;
	}

	public String resetDIV() {
		//leavePolicyMIS.setTokenNo("");
		//leavePolicyMIS.setEmployeeCode("");
		//leavePolicyMIS.setEmployeeName("");
		leavePolicyMIS.setDeptCode("");
		leavePolicyMIS.setDeptName("");
		leavePolicyMIS.setBranchCode("");
		leavePolicyMIS.setBranchName("");
		leavePolicyMIS.setEmpTypeName("");
		leavePolicyMIS.setEmpTypeCode("");
		leavePolicyMIS.setDesgCode("");
		leavePolicyMIS.setDesgName("");

		return SUCCESS;
	}
	
	

	public String resetEMP() {
		leavePolicyMIS.setTokenNo("");
		leavePolicyMIS.setEmployeeCode("");
		leavePolicyMIS.setEmployeeName("");

		return SUCCESS;
	}

	public String report() throws Exception {
		LeavePolicyMISReportModel model = new LeavePolicyMISReportModel();
		model.initiate(context, session);
		model.report(leavePolicyMIS, request, response, "");
		model.terminate();
		return null;
	}
	
	public final String mailReport(){
		try {
			LeavePolicyMISReportModel model = new LeavePolicyMISReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.report(leavePolicyMIS, request, response, reportPath);			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	

	public String f9dept() throws Exception {
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		String[] headers = { getMessage("department.code"),
				getMessage("department") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "deptCode", "deptName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "LeavePolicyMIS_resetEMP.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9dept method

	/**
	 * To select any branch
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9branch() throws Exception {
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

		String[] headers = { getMessage("branch.code"), getMessage("branch") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "branchCode", "branchName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "LeavePolicyMIS_resetEMP.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9branch method

	public String f9division() throws Exception {
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
		
			if(leavePolicyMIS.getUserProfileDivision() !=null && leavePolicyMIS.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+leavePolicyMIS.getUserProfileDivision() +")"; 
			
				query+= " ORDER BY  DIV_ID ";
				
		String[] headers = { getMessage("division.code"),
				getMessage("division") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "divisionCode", "divisionName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "LeavePolicyMIS_resetEMP.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9branch method

	/**
	 * To select any designation
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9designation() throws Exception {

		String query = " SELECT RANK_ID,RANK_NAME FROM HRMS_RANK "
				+ " ORDER BY  RANK_ID ";

		String[] headers = { getMessage("designation.code"),
				getMessage("designation") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "desgCode", "desgName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "LeavePolicyMIS_resetEMP.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9designation method

	public String f9empType() throws Exception {

		String query = " SELECT  TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE ORDER BY TYPE_ID ";

		String[] headers = { "Type Code", getMessage("employee.type") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "empTypeCode", "empTypeName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "LeavePolicyMIS_resetEMP.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end f9empType method

	/**
	 * To select any employee
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9employee() throws Exception {
		// to remove an already selected employee from the search list
		String[] empCode = request.getParameterValues("employeeCodeItr");
		String str = "0";
		if (empCode != null) {
			for (int i = 0; i < empCode.length; i++) {// loop x
				str += "," + empCode[i];
			}// end loop x
		}// end if

		String div_code = leavePolicyMIS.getDivisionCode();
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
				+ " FROM HRMS_EMP_OFFC ";
			
				query += getprofileQuery(leavePolicyMIS);
				
				query += " AND EMP_STATUS='S' AND EMP_DIV="+ div_code;
				
				query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { "Employee Id", "Employee" };
		
		String[] headerWidth = { "20", "80" };
		
		String[] fieldNames = { "tokenNo", "employeeName", "employeeCode" };
		
		int[] columnIndex = { 0, 1, 2 };
		
		String submitFlag = "true";
		
		
		String submitToMethod = "LeavePolicyMIS_resetDIV.action";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}// end f9employee method

	/**
	 * To select the policy of selected division
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9policy() throws Exception {
		String query = " SELECT DISTINCT LEAVE_POLICY_CODE, LEAVE_POLICY_NAME  FROM HRMS_LEAVE_POLICY_HDR "
				+ " WHERE DIV_CODE="
				//	+ policySetting.getHiddenDivCode()
				+ " ORDER BY  LEAVE_POLICY_CODE ";

		String[] headers = { "Policy Code", getMessage("assign.policy") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "policyCode", "policyName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9policy method

}
