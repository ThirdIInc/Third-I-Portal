package org.struts.action.D1;

import org.paradyne.bean.D1.Termination;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.TerminationModel;
import org.struts.lib.ParaActionSupport;
import org.apache.log4j.Logger;

/** * @author aa1380 : Manish Sakpal. * */
public class TerminationAction extends ParaActionSupport {
	/**	logger. * */
	private Logger logger = Logger.getLogger(TerminationAction.class);
	/**	trBean. * */
	private Termination trBean;
	/**	twentyStr. * */
	private final String twentyStr = "20";
	/**	eightyStr. * */
	private final String eightyStr = "80";
	/**	falseStr. * */
	private final String falseStr = "false";
	/**	f9pageStr. * */
	private final String f9pageStr = "f9page";
	/**	yStr. * */
	private final String yStr = "Y";
	/**	nStr. * */
	private final String nStr = "N";
	/**	dStr. * */
	private final String dStr = "D";
	/**	pStr. * */
	private final String pStr = "P";
	/**	bStr. * */
	private final String bStr = "B";
	/**	aStr. * */
	private final String aStr = "A";
	/**	rStr. * */
	private final String rStr = "R";
	/**	pendingStr. * */
	private final String pendingStr = "pending";
	/**	rejectedStr. * */
	private final String rejectedStr = "rejected";
	/**	approvedStr. * */
	private final String approvedStr = "approved";

	/**
	 * Method : prepare_local. 
	 * Purpose : Used to set menu code and create bean instance
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		this.trBean = new Termination();
		this.trBean.setMenuCode(2024);
	}

	/**
	 * @return the trBean
	 */
	public Termination getTrBean() {
		return this.trBean;
	}

	/**
	 * @param trBean : the trBean to set.
	 */
	public void setTrBean(final Termination trBean) {
		this.trBean = trBean;
	}

	/**
	 * Method : getModel. 
	 * Purpose : Used to return bean instance
	 * @return Object
	 */
	public Object getModel() {
		return this.trBean;
	}

	@Override
	/** 
	 * Used to set pending list.
	 * @return String
	 */
	public String input() {
		try {
			final TerminationModel model = new TerminationModel();
			model.initiate(context, session);
			final String logInUserID = this.trBean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(logInUserID);
			if (isCurrentUser) {
				model.getPendingList(this.trBean, request, logInUserID);
			}
			this.trBean.setListType(this.pendingStr);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * Method : addnew.
	 * Purpose : Used to add new application
	 * @return String
	 */
	public String addnew() {
		try {
			final TerminationModel model = new TerminationModel();
			model.initiate(context, session);
			this.trBean.setTerminationID("");
			model.getLoginUserInformation(this.trBean, this.trBean.getUserEmpId());
			this.trBean.setApplicationStatus("");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(1);
		return SUCCESS;
	}

	/**
	 * Used to get approved list of application.
	 * @return String
	 */
	public String getApprovedList() {
		try {
			final TerminationModel model = new TerminationModel();
			model.initiate(context, session);
			final String userId = this.trBean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getApprovedList(this.trBean, request, userId);
			}
			this.trBean.setListType(this.approvedStr);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * Used to get rejected list of applications.
	 * @return String
	 */
	public String getRejectedList() {
		try {
			final TerminationModel model = new TerminationModel();
			model.initiate(context, session);
			final String userId = this.trBean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getRejectedList(this.trBean, request, userId);
			}
			this.trBean.setListType(this.rejectedStr);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * Return back to list page.
	 * @return String
	 */
	public String backFunction() {
		final TerminationModel model = new TerminationModel();
		model.initiate(context, session);
		final String listType = Utility.checkNull(this.trBean.getListType());
		if ("".equals(listType) || this.pendingStr.equals(listType)) {
			this.input();
		} else if (this.approvedStr.equals(listType)) {
			this.getApprovedList();
		} else if (this.rejectedStr.equals(listType)) {
			this.getRejectedList();
		}
		return INPUT;
	}

	/**
	 * Used to delete saved record.
	 * @return String
	 */
	public String deleteFunction() {
		try {
			final TerminationModel model = new TerminationModel();
			model.initiate(context, session);
			final boolean result = model.deleteRecord(this.trBean);
			if (result) {
				this.addActionMessage("Record deleted successfully.");
			} else {
				this.addActionMessage("Error occured while deleting record.");
			}
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**
	 * Used to select employee, to whom manager want to terminate.
	 * @return String
	 */
	public String f9Employee() {
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_LNAME , EMP_ID  FROM HRMS_EMP_OFFC ";
		if (this.trBean.getUserProfileDivision() != null && this.trBean.getUserProfileDivision().length() > 0) {
			query += " WHERE  HRMS_EMP_OFFC.EMP_DIV IN (" + this.trBean.getUserProfileDivision() + " )";
		} else {
			query += " WHERE 1 = 1 ";
		}
		query += " AND EMP_STATUS = 'S' AND EMP_ID NOT IN(" + this.trBean.getCompletedByID() + "  )";
		query += " AND EMP_ID NOT IN(SELECT TER_EMP_ID FROM HRMS_D1_TERMINATION WHERE TER_STATUS IN('A','D','B','P')) ";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		final String[] headers = {"Employee ID", "Employee"};
		final String[] headerWidth = {this.twentyStr, this.eightyStr};
		final String[] fieldNames = {"employeeToken", "employeeName", "employeeNumber"};
		final int[] columnIndex = {0, 1, 2 };
		final String submitFlag = "true";
		final String submitToMethod = "Termination_setAddressCity.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return this.f9pageStr;
	}

	/**
	 * Used to set address and city based on selected employee.
	 * @return String
	 */
	public String setAddressCity() {
		try {
			final TerminationModel model = new TerminationModel();
			model.initiate(context, session);
			model.setsetAddressCity(this.trBean);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}

		this.getNavigationPanel(1);
		this.trBean.setEnableAll(this.yStr);
		return SUCCESS;
	}

	/**
	 * Used to select city.
	 * @return String
	 */
	public String f9city() {
		final String query = "SELECT CITY.LOCATION_NAME AS CITY, STATE.LOCATION_NAME AS STATE, CITY.LOCATION_CODE AS CITY_CODE FROM HRMS_LOCATION STATE " + 
							 " LEFT JOIN HRMS_LOCATION CITY ON(STATE.LOCATION_CODE = CITY.LOCATION_PARENT_CODE) WHERE CITY.LOCATION_LEVEL_CODE = 2 " + 
							 " ORDER BY UPPER(CITY)";
		final String[] headers = {"City Code", "City"};
		final String[] headerWidth = {"10", "45"};
		final String[] fieldNames = {"cityName", "state", "cityID"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = this.falseStr;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9pageStr;
	}


	/**
	 * Used to get state.
	 * @return String
	 */
	public String getState() {
		try {
			final TerminationModel model = new TerminationModel();
			model.initiate(context, session);
			model.getStateFromCity(this.trBean);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(1);
		this.trBean.setEnableAll(this.yStr);
		return SUCCESS;
	}

	/**
	 * Used to select executive.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String f9Executive() throws Exception {
		final String query = " SELECT DISTINCT RANK_ID,TO_CHAR(RANK_NAME) FROM  HRMS_RANK  ORDER BY  RANK_ID ";
		final String[] headers = {"Executive ID", "Executive Name"};
		final String[] headerWidth = {this.twentyStr, this.eightyStr};
		final String[] fieldNames = {"executiveID", "executiveName"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = this.falseStr;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9pageStr;
	}

	/**
	 *Used to select department.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String f9DepartmentNum() throws Exception {
		String query = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT  ";
		if (this.trBean.getUserProfileDivision() != null && this.trBean.getUserProfileDivision().length() > 0) {
			query += " WHERE DEPT_DIV_CODE IN (" + this.trBean.getUserProfileDivision() + ")";
		}
		query += " ORDER BY DEPT_ID DESC";
		final String[] headers = {"Department ID", "Department"};
		final String[] headerWidth = {this.twentyStr, this.eightyStr};
		final String[] fieldNames = {"deptID", "depetNumber"};
		final int[] columnIndex = {0, 1 };
		final String submitFlag = this.falseStr;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9pageStr;
	}

	/**
	 * Used to draft application.
	 * @return String
	 */
	public String draftFunction() {
		try {
			final TerminationModel model = new TerminationModel();
			model.initiate(context, session);
			boolean result;
			if ("".equals(this.trBean.getTerminationID())) {
				result = model.saveRecords(this.trBean);
				if (result) {
					this.addActionMessage("Application saved successfully.");
				} else {
					this.addActionMessage("Error occured in draftFunction");
				}
			} else {
				result = model.updateRecords(this.trBean);
				if (result) {
					this.addActionMessage("Application submitted successfully.");
				} else {
					this.addActionMessage("Error occured");
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**
	 * Used to view application details.
	 * @return String
	 */
	public String viewApplicationFunction() {
		String terminationHiddenID = request.getParameter("terminationHiddenID");
		final String hiddenStatus = request.getParameter("hiddenStatus");
		final TerminationModel model = new TerminationModel();
		try {
			model.initiate(context, session);
			/*
			 * FOR SUPER USER
			 */
			final String applCode = request.getParameter("applCode");
			if (applCode != null && applCode.length() > 0) {
				terminationHiddenID = applCode;
			}

			model.viewApplication(this.trBean, terminationHiddenID);
			model.getApproverCommentList(this.trBean, terminationHiddenID);
			model.terminate();

			if (this.dStr.equals(hiddenStatus)) {
				this.getNavigationPanel(2);
				this.trBean.setEnableAll(this.yStr);
				this.trBean.setApplicationStatus(this.dStr);
			} else if (this.pStr.equals(hiddenStatus)) {
				this.getNavigationPanel(3);
				this.trBean.setEnableAll(this.nStr);
				this.trBean.setApplicationStatus(this.pStr);
			} else if (this.bStr.equals(hiddenStatus)) {
				this.getNavigationPanel(2);
				this.trBean.setEnableAll(this.yStr);
				this.trBean.setApplicationStatus(this.bStr);
			} else if (this.aStr.equals(hiddenStatus)) {
				this.getNavigationPanel(4);
				this.trBean.setEnableAll(this.nStr);
				this.trBean.setApplicationStatus(this.aStr);
			} else if (this.rStr.equals(hiddenStatus)) {
				this.getNavigationPanel(3);
				this.trBean.setEnableAll(this.nStr);
				this.trBean.setApplicationStatus(this.rStr);
			}
			if (applCode != null && applCode.length() > 0) {
				this.getNavigationPanel(0);
				this.trBean.setEnableAll(this.nStr);
			}
		} catch (final Exception e) {
			this.getNavigationPanel(0);
			this.trBean.setEnableAll(this.nStr);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * Used to reset all the form fields.
	 * @return String
	 */
	public String reset() {
		this.trBean.setTerminationID("");
		this.trBean.setEmployeeNumber("");
		this.trBean.setEmployeeToken("");
		this.trBean.setEmployeeName("");
		this.trBean.setHomeAddress("");
		this.trBean.setZipCode("");
		this.trBean.setCityID("");
		this.trBean.setCityName("");
		this.trBean.setState("");
		this.trBean.setTerminationType("");
		this.trBean.setTerminationTypeRadioOptionValue("");
		this.trBean.setTerminationDate("");
		this.trBean.setLastDayWorkDate("");
		this.trBean.setIfTerDateAndLastDayWorkDateDiffer("");
		this.trBean.setVoluntaryTerminationReason("");
		this.trBean.setInVoluntaryTerminationReason("");
		this.trBean.setOtherTerminationReason("");
		this.trBean.setEligibleToRehire("");
		this.trBean.setIfNoOrProvisional("");
		this.trBean.setDeptID("");
		this.trBean.setDepetNumber("");
		this.trBean.setExecutiveID("");
		this.trBean.setExecutiveName("");
		this.trBean.setManagerName("");
		this.trBean.setManagerPhone("");
		this.trBean.setVacationHrsTaken("");
		this.trBean.setCommentsEntered("");
		this.input();
		this.getNavigationPanel(1);
		return SUCCESS;
	}

	/**
	 * Used to edit saved record.
	 * @return String
	 */
	public String editFunction() {
		final String terminationHiddenID = this.trBean.getTerminationID();
		final TerminationModel model = new TerminationModel();
		try {
			model.initiate(context, session);
			model.viewApplication(this.trBean, terminationHiddenID);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(1);
		this.trBean.setEnableAll(this.yStr);
		return SUCCESS;
	}

	/**
	 * Used to send application for approval.
	 * @return String
	 */
	public String sendForApproval() {
		final TerminationModel model = new TerminationModel();
		final String status = this.trBean.getStatus();
		try {
			model.initiate(context, session);
			final boolean result = model.sendForApprovalFunction(this.trBean, status);
			if (result) {
				this.addActionMessage("Application Successfully Send For Approval.\n Tracking Number : " + this.trBean.getTrackingNumber());
				this.sendMailToHR();
			} else {
				this.addActionMessage("Error occured while finalizing application.");
			}
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**
	 * This method is used to send email notification to the HR person regarding approval of termination form.
	 */
	private void sendMailToHR() {
		try {
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-TERMINATION APPLICATION FROM MANGER TO HR");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
			templateQueryApp1.setParameter(1, this.trBean.getUserEmpId());

			final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);
			templateQueryApp2.setParameter(1, "0");

			final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, this.trBean.getTerminationID());

			final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, this.trBean.getTerminationID());

			final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, this.trBean.getTerminationID());

			templateApp.configMailAlert();
			final TerminationModel model = new TerminationModel();
			model.initiate(context, session);
			final String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID FROM HRMS_D1_APPROVAL_SETTINGS " + 
						   " WHERE APP_SETTINGS_TYPE ='H' AND APP_EMAIL_ID IS NOT NULL ";
			final Object[][] data = model.getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				templateApp.sendApplicationMailToGroups(data);
			}
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Used for Selecting Approver.
	 * @return String
	 */
	public String f9Approver() {
		String str = "0";
		if (!"".equals(this.trBean.getEmployeeNumber())) {
			str = this.trBean.getEmployeeNumber();
		}
		String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID  FROM HRMS_EMP_OFFC ";
		if (this.trBean.getUserProfileDivision() != null && this.trBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.trBean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}

		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str + ")";
		query += " ORDER BY HRMS_EMP_OFFC.EMP_ID";

		final String[] headers = {this.getMessage("employee.id"), this.getMessage("employee")};
		final String[] headerWidth = {this.twentyStr, this.eightyStr};
		final String[] fieldNames = {"managerToken", "managerName", "managerCode"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = this.falseStr;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9pageStr;
	}

}
