package org.struts.action.settings;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.settings.UserCreation;
import org.paradyne.lib.MailUtility;
import org.paradyne.model.admin.master.JobDescMasterModel;
import org.paradyne.model.settings.UserCreationModel;

/**
 * @author reebaj
 * 
 */

public class UserCreationAction extends ParaActionSupport {

	UserCreation userCreation;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		userCreation = new UserCreation();
		userCreation.setMenuCode(570);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return userCreation;
	}

	public UserCreation getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(UserCreation userCreation) {
		this.userCreation = userCreation;
	}

	/**
	 * Creating user names using different combinations
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String submit() throws Exception {

		UserCreationModel model = new UserCreationModel();
		model.initiate(context, session);
		boolean result = model.submit(userCreation, request);
		if (result) {
			addActionMessage("Login Generated Successfully.");
		}// end of if
		else {
			addActionMessage("No new users.");
		}// end of else
		model.terminate();

		return "success";
	}

	/**
	 * Sending Mail to employees
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String sendMail() throws Exception {
		boolean result = false;

		UserCreationModel model = new UserCreationModel();
		try {
			model.initiate(context, session);
			String query = "SELECT EMP_ID FROM HRMS_LOGIN";
			Object[][] chk = model.getSqlModel().getSingleResult(query);
			
			MailUtility mail=new MailUtility();
			mail.initiate(context, session);
			String chkDeftMail[]= mail.getDefaultMailIds();
			
			if (chk.length > 0) {
				result = model.sendMail(userCreation, request);
				if (result) {
					addActionMessage("Mail Sent Successfully");
				}// end of nested if
				else if(!(chkDeftMail.length>0)){
					addActionMessage("Message sent failed.\n Please configure mail account properly. ");
					return SUCCESS;    
				}// end of else
			}// end of if
			else {
				addActionMessage("No users have been created.");
			}// end of else

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception caught in SEND MAIL");
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * Creating user names using email id
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String submitEmail() throws Exception {
		UserCreationModel model = new UserCreationModel();
		model.initiate(context, session);
		logger.info("action-----------------");
		boolean result = model.submitEmail(userCreation, request);
		if (result) {
			addActionMessage("Login Generated Successfully.");
		}// end of if
		else {
			addActionMessage("No new users.");
		}// end of else
		
		model.terminate();

		return "success";
	}

 
	/* ************************* FILTERS FOR SENDING MAIL ********************** */

	/** DIVISION * */
	public String f9division() throws Exception {
		
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
		
		if(userCreation.getUserProfileDivision() !=null && userCreation.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+userCreation.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
			
		String[] headers = { getMessage("division.code"), getMessage("division") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "divisionCode", "divisionName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "UserCreation_resetDivMail.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/** BRANCH * */
	public String f9branch() throws Exception {
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

		String[] headers = { getMessage("branch.code"), getMessage("branch") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "branchCode", "branchName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "UserCreation_resetCntMail.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/** DEPARTMENT * */
	public String f9dept() throws Exception {
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		String[] headers = { "department.code", getMessage("department") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "deptCode", "deptName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "UserCreation_resetDptMail.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/** DESIGNATION * */
	public String f9designation() throws Exception {
		String query = " SELECT RANK_ID,RANK_NAME FROM HRMS_RANK "
				+ " ORDER BY  RANK_ID ";

		String[] headers = { getMessage("designation.code"), getMessage("designation") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "desgCode", "desgName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "UserCreation_resetDesgMail.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/** INDIVIDUAL EMPLOYEE * */
	public String f9employee() throws Exception {
		String query = " SELECT EMP_ID,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME FROM hrms_emp_offc ";
			 
		query += getprofileQuery(userCreation);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "employeeCode", "employeeName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "UserCreation_resetEmpMail.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/*
	 * * RESET FILTERS ON SELECTION
	 * ***
	 */

	/** RESET IF INDIVIDUAL EMPLOYEE IS SELECTED * */
	public String resetEmpMail() {
		userCreation.setDivisionCode("");
		userCreation.setDivisionName("");
		userCreation.setBranchCode("");
		userCreation.setBranchName("");
		userCreation.setDeptCode("");
		userCreation.setDeptName("");
		userCreation.setDesgCode("");
		userCreation.setDesgName("");
		return SUCCESS;

	}

	/** RESET IF DIVISION IS SELECTED * */
	public String resetDivMail() {
		userCreation.setEmployeeCode("");
		userCreation.setEmployeeName("");
		userCreation.setBranchCode("");
		userCreation.setBranchName("");
		userCreation.setDeptCode("");
		userCreation.setDeptName("");
		userCreation.setDesgCode("");
		userCreation.setDesgName("");

		return SUCCESS;

	}

	/** RESET IF DEPARTMENT IS SELECTED * */
	public String resetDptMail() {
		userCreation.setEmployeeCode("");
		userCreation.setEmployeeName("");
		userCreation.setDivisionCode("");
		userCreation.setDivisionName("");
		userCreation.setBranchCode("");
		userCreation.setBranchName("");
		userCreation.setDesgCode("");
		userCreation.setDesgName("");

		return SUCCESS;

	}

	/** RESET IF BRANCH IS SELECTED * */
	public String resetCntMail() {
		userCreation.setEmployeeCode("");
		userCreation.setEmployeeName("");
		userCreation.setDivisionCode("");
		userCreation.setDivisionName("");
		userCreation.setDeptCode("");
		userCreation.setDeptName("");
		userCreation.setDesgCode("");
		userCreation.setDesgName("");

		return SUCCESS;

	}

	/** RESET IF DESIGNATION IS SELECTED * */
	public String resetDesgMail() {
		userCreation.setEmployeeCode("");
		userCreation.setEmployeeName("");
		userCreation.setDivisionCode("");
		userCreation.setDivisionName("");
		userCreation.setBranchCode("");
		userCreation.setBranchName("");
		userCreation.setDeptCode("");
		userCreation.setDeptName("");

		return SUCCESS;

	}

}
