/**
 * 
 */
package org.struts.action.leave;

import org.paradyne.bean.leave.LeaveBalance;
import org.paradyne.bean.leave.LeavePolicySetting;
import org.paradyne.model.leave.LeavePolicyModel;
import org.paradyne.model.leave.LeavePolicySettingModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 * @since 17/04/2009
 * 
 */
public class LeavePolicySettingAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LeavePolicySettingAction.class);

	LeavePolicySetting policySetting;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		policySetting = new LeavePolicySetting();
		policySetting.setMenuCode(838);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return policySetting;
	}

	/**
	 * @return the policySetting
	 */
	public LeavePolicySetting getPolicySetting() {
		return policySetting;
	}

	/**
	 * @param policySetting
	 *            the policySetting to set
	 */
	public void setPolicySetting(LeavePolicySetting policySetting) {
		this.policySetting = policySetting;
	}

	/**
	 * Input method. Called on load
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String input() throws Exception {
		// TODO Auto-generated method stub
		getNavigationPanel(1);
		policySetting.setOnLoadFlag(true);
		policySetting.setNextFlag(false);
		policySetting.setNewPolicyFlag(false);
		policySetting.setNewExceptionFlag(false);
		policySetting.setExcepCancel(false);
		policySetting.setPolicyCancel(false);
		policySetting.setEditExceptions(false);
		policySetting.setEditPolicies(false);
		return SUCCESS;
	}// end input method

	/**
	 * Used to navigate to screen 2 on clicking Next button after selecting a
	 * division. Displays the policy list as well as exception list for the
	 * division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String next() throws Exception {
		getNavigationPanel(2);
		LeavePolicySettingModel model = new LeavePolicySettingModel();
		model.initiate(context, session);
		model.getPolicyList(policySetting, request);
		model.getEmployeePolicyList(policySetting, request);
		model.terminate();
		policySetting.setNextFlag(true);
		policySetting.setOnLoadFlag(true);
		return SUCCESS;
	}// end next method

	/**
	 * Navigates to screen 3 where new policies to the selected division can be
	 * added. Also Displays the added policy list for the division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addNewPolicy() throws Exception {
		getNavigationPanel(3);
		policySetting.setHiddenCode("");
		policySetting.setNewPolicyFlag(true);
		policySetting.setOnLoadFlag(false);
		policySetting.setNextFlag(false);
		policySetting.setNewExceptionFlag(false);
		policySetting.setExcepCancel(false);
		policySetting.setPolicyCancel(true);
		policySetting.setEditExceptions(false);
		policySetting.setEditPolicies(false);
		LeavePolicySettingModel model = new LeavePolicySettingModel();
		model.initiate(context, session);
		model.getPolicyList(policySetting, request);
		model.terminate();
		return SUCCESS;
	}// end addNewPolicy method

	/**
	 * Navigates to screen 4 where employee exceptions to the selected division
	 * can be added. Also Displays the added exceptions for the division.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addExceptions() throws Exception {
		getNavigationPanel(3);
		policySetting.setHiddenCode("");
		policySetting.setNewExceptionFlag(true);
		policySetting.setExcepCancel(true);
		policySetting.setNewPolicyFlag(false);
		policySetting.setPolicyCancel(false);
		policySetting.setOnLoadFlag(false);
		policySetting.setNextFlag(false);
		policySetting.setEditExceptions(false);
		policySetting.setEditPolicies(false);
		LeavePolicySettingModel model = new LeavePolicySettingModel();
		model.initiate(context, session);
		model.getEmployeePolicyList(policySetting, request);
		model.terminate();
		return SUCCESS;
	}// end addExceptions method

	/**
	 * Cancel and return to screen 2 from Add Policies screen
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String cancelFirst() throws Exception {
		// call to next method
		next();
		// reset the filters
		policySetting.setBranchCode("");
		policySetting.setBranchName("");
		policySetting.setDeptCode("");
		policySetting.setDeptName("");
		policySetting.setDesgCode("");
		policySetting.setDesgName("");
		policySetting.setEmpTypeCode("");
		policySetting.setEmpTypeName("");
		policySetting.setNewPolicyFlag(false);
		policySetting.setNewExceptionFlag(false);
		policySetting.setPolicyCancel(true);
		policySetting.setHiddenCode("");
		policySetting.setHiddenDivCode("");

		return SUCCESS;
	}// end cancelFirst method

	/**
	 * Cancel and return to screen 2 from Add Exceptions screen
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String cancelSec() throws Exception {
		// call to next method
		next();
		// reset the employee fields
		policySetting.setEmployeeCode("");
		policySetting.setTokenNo("");
		policySetting.setEmployeeName("");
		policySetting.setNewPolicyFlag(false);
		policySetting.setNewExceptionFlag(false);
		policySetting.setExcepCancel(true);
		policySetting.setHiddenCode("");
		policySetting.setHiddenDivCode("");
		return SUCCESS;
	}// end cancelSec method

	public String getLeavePolicyCode()
	{
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		System.out.println("leaveApplication.getEmpCode()------"+policySetting.getEmployeeCode());
		String leavePolicyCode = model.getLeavePolicy(policySetting.getEmployeeCode());
		logger.info("leavePolicyCode in method ===="+leavePolicyCode);
		model.terminate();
		return leavePolicyCode;
	}
	
	/**
	 * Assign the policy. Policies are assigned division wise. No 2 combinations
	 * of filters can be repeated.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String assignPolicy() throws Exception {
		getNavigationPanel(3);
		LeavePolicySettingModel model = new LeavePolicySettingModel();
		model.initiate(context, session);
		int result = 0;
		if (!(policySetting.getEmployeeCode().equals(""))
				&& !(policySetting.getEmployeeCode() == null)
				&& !policySetting.getEmployeeCode().equals("null")) {
			String leavePolicyCode = getLeavePolicyCode();
			if(leavePolicyCode.equals(policySetting.getPolicyCode())){
				addActionMessage("The selected leave policy has already been assigned to the employee.");
				resetExceptions();
				return SUCCESS;
			}
			if (policySetting.isNewExceptionFlag() == true) {
				result = model.assignPolicy(policySetting, request);
				if (result==1)
					addActionMessage("Leave policy assigned successfully");
				else if(result == 2){
					addActionMessage("Leave policy modified successfully");
					policySetting.setHiddenCode("");
				}
				else
					addActionMessage("Please delete the currently assigned policy to assign a new one");
				resetExceptions();
			}// end if new exception
			return SUCCESS;
		}else{
			result = model.assignPolicy(policySetting, request);
			if (result==1)
				addActionMessage("Leave policy assigned successfully");
			else if(result == 2){
				addActionMessage("Leave policy modified successfully");
				policySetting.setHiddenCode("");
			}
			else
				addActionMessage("Please delete the currently assigned policy to assign a new one");
			logger.info("New Policy Flag========="
					+ policySetting.isNewPolicyFlag());
			logger.info("New Exception Flag========="
					+ policySetting.isNewExceptionFlag());
			if (policySetting.isNewPolicyFlag() == true) {
				resetFilters();
			}// end if new policy
			if (policySetting.isNewExceptionFlag() == true) {
				resetExceptions();
			}// end if new exception
		}
		model.terminate();
		
		return SUCCESS;
	}// end of assignPolicy method

	/**
	 * Edit the policy list. Sets values to the fields
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String editList() throws Exception {
		getNavigationPanel(3);
		policySetting.setNewPolicyFlag(true);
		policySetting.setEditPolicies(true);
		policySetting.setOnLoadFlag(false);
		policySetting.setNextFlag(false);
		policySetting.setNewExceptionFlag(false);
		policySetting.setExcepCancel(false);
		LeavePolicySettingModel model = new LeavePolicySettingModel();
		model.initiate(context, session);
		// called for edit
		model.callEditList(policySetting, request);
		// get policy list
		model.getPolicyList(policySetting, request);
		model.terminate();
		return SUCCESS;
	}// end of editList method

	/**
	 * Delete record from policy or exception list
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteList() throws Exception {
		LeavePolicySettingModel model = new LeavePolicySettingModel();
		model.initiate(context, session);
		boolean result = model.callDeleteList(policySetting, request);
		if (result)
			addActionMessage(getMessage("delete"));
		else
			addActionMessage(getMessage("del.error"));
		if (policySetting.isNextFlag() == true) {// if next flag
			getNavigationPanel(2);
			model.getPolicyList(policySetting, request);
			model.getEmployeePolicyList(policySetting, request);
			policySetting.setOnLoadFlag(true);
		}// end if
		else if (policySetting.isNewPolicyFlag() == true) {// if new policy
			// flag
			getNavigationPanel(3);
			resetFilters();
			policySetting.setPolicyCancel(true);
		}// end if
		else if (policySetting.isNewExceptionFlag() == true) {// if exception
			// flag
			getNavigationPanel(3);
			resetExceptions();
			policySetting.setExcepCancel(true);
		}// end if
		model.terminate();
		policySetting.setHiddenCode("");
		return SUCCESS;
	}// end of deleteList method

	/**
	 * Edit the employee exception list
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String editEmployeeList() throws Exception {
		getNavigationPanel(3);
		policySetting.setNewExceptionFlag(true);
		policySetting.setEditExceptions(true);
		policySetting.setNewPolicyFlag(false);
		policySetting.setPolicyCancel(false);
		policySetting.setOnLoadFlag(false);
		policySetting.setNextFlag(false);
		LeavePolicySettingModel model = new LeavePolicySettingModel();
		model.initiate(context, session);
		// called for editing employee list
		model.callEditEmpList(policySetting, request);
		// get the employee list
		model.getEmployeePolicyList(policySetting, request);
		model.terminate();
		return SUCCESS;
	}// end of editEmployeeList method

	/**
	 * Searching employee type
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9empType() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE ORDER BY TYPE_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Type Code", getMessage("employee.type") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empTypeCode", "empTypeName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "LeavePolicySetting_addNewPolicy.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end f9empType method

	/**
	 * To select any division
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9division() throws Exception {
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
		
		if(policySetting.getUserProfileDivision() !=null && policySetting.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+policySetting.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		String[] headers = { getMessage("division.code"),
				getMessage("division") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "divisionCode", "divisionName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "LeavePolicySetting_input.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9division method

	/**
	 * To select any department
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9dept() throws Exception {
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		String[] headers = { getMessage("department.code"),
				getMessage("department") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "deptCode", "deptName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "LeavePolicySetting_addNewPolicy.action";
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
		String submitFlag = "false";
		String submitToMethod = "LeavePolicySetting_addNewPolicy.action";
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
		String submitFlag = "false";
		String submitToMethod = "LeavePolicySetting_addNewPolicy.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9designation method

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
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
				+ " FROM HRMS_EMP_OFFC ";
	 
		query +=" WHERE EMP_STATUS='S' AND EMP_ID NOT IN("
				+ str
				+ ") AND EMP_DIV ="
				+ policySetting.getHiddenDivCode();
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "tokenNo", "employeeName", "employeeCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "LeavePolicySetting_addExceptions.action";
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
				+ policySetting.getHiddenDivCode()
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

	/**
	 * To reset the employee & policy fields after deleting any record from
	 * exception list
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String resetExceptions() throws Exception {
		getNavigationPanel(3);
		policySetting.setEmployeeCode("");
		policySetting.setTokenNo("");
		policySetting.setEmployeeName("");
		policySetting.setPolicyCode("");
		policySetting.setPolicyName("");
		LeavePolicySettingModel model = new LeavePolicySettingModel();
		model.initiate(context, session);
		model.getEmployeePolicyList(policySetting, request);
		model.terminate();
		return SUCCESS;
	}// end resetExceptions method

	/**
	 * To reset the filters after deleting any record from the policy list
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String resetFilters() throws Exception {
		getNavigationPanel(3);
		policySetting.setBranchCode("");
		policySetting.setBranchName("");
		policySetting.setDeptCode("");
		policySetting.setDeptName("");
		policySetting.setDesgCode("");
		policySetting.setDesgName("");
		policySetting.setEmpTypeCode("");
		policySetting.setEmpTypeName("");
		policySetting.setPolicyCode("");
		policySetting.setPolicyName("");
		LeavePolicySettingModel model = new LeavePolicySettingModel();
		model.initiate(context, session);
		model.getPolicyList(policySetting, request);
		model.terminate();
		return SUCCESS;
	}// end resetFilters method

}// end of class
