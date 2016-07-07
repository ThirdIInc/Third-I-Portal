package org.struts.action.D1;

import org.apache.log4j.Logger;
import org.paradyne.bean.D1.CBTFormBean;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.CBTFormModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1380
 * 
 */
public class CBTFormAction extends ParaActionSupport {
	
	/**	StringF9. *	 */
	private final String stringF9 = "f9page";
	
	/**	stringFalse. *	 */
	private final String stringFalse = "false";
	
	
	/**	f9Width10. *	 */
	private final String f9Width10 = "10";
	
	/**	f9Width20. *	 */
	private final String f9Width20 = "20";
	
	/**	f9Width30. *	 */
	private final String f9Width30 = "30";
	
	/**	f9Width40. *	 */
	private final String f9Width40 = "40";
	
	/**	f9Width80. *	 */
	private final String f9Width80 = "80";
	
	/**	department. *	 */
	private final String department = "Department";
	
	
	/**	departmentNo. *	 */
	private final String departmentNo = "deptNo";
	
	
	/**	departmentName. *	 */
	private final String departmentName = "deptName";
	
	/**	stringY. *	 */
	private final String stringY = "Y";
	
	/**	stringN. *	 */
	private final String stringN = "N";
	
	/**	stringN. *	 */
	private final String stringD = "D";
	
	/**	stringN. *	 */
	private final String stringP = "P";
	
	/**	stringN. *	 */
	private final String stringB = "B";
	
	/**	stringN. *	 */
	private final String stringX = "X";
	
	/**	stringN. *	 */
	private final String stringA = "A";
	
	/**	stringN. *	 */
	private final String stringC = "C";
	
	/**	stringN. *	 */
	private final String stringR = "R";
	
	/**	stringN. *	 */
	private final String stringF = "F";
	
	
	/** logger. *	*/
	private Logger logger = Logger.getLogger(CBTFormAction.class);
	
	/** cbtBean. * */
	private CBTFormBean cbtBean;
	
	/**
	 * @return cbtBean.
	 */
	public CBTFormBean getCbtBean() {
		return this.cbtBean;
	}

	/**
	 * @param cbtBean : CBTFormBean.
	 */
	public void setCbtBean(final CBTFormBean cbtBean) {
		this.cbtBean = cbtBean;
	}

	/**
	 * Method : prepare_local.
	 * Purpose : For setting menu code
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		this.cbtBean = new CBTFormBean();
		this.cbtBean.setMenuCode(2013);
	}
	
	/**
	 * Method : getModel.
	 * @return cbtBean
	 */
	public Object getModel() {
		return this.cbtBean;
	}

	
	/**
	 * Method : input.
	 * Purpose : For getting initial list
	 * @return String
	 */
	public String input() {
		try {
			final CBTFormModel model = new CBTFormModel();
			model.initiate(context, session);
			final String userId = this.cbtBean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getPendingList(this.cbtBean, request, userId);
			}
			this.cbtBean.setListType("pending");
			model.terminate();
		} catch (final Exception e) {
			this.logger.info(" Exception occured in input() : " + e);
		}
		this.getNavigationPanel(1);
		return INPUT;
	}

	
	/**
	 * Method : addNew.
	 * Purpose : For adding new application
	 * @return String
	 */
	public String addNew() {
		try {
			this.cbtBean.setCbtID("");
			final CBTFormModel model = new CBTFormModel();
			model.initiate(context, session);
			model.getEmployeeDetails(this.cbtBean.getUserEmpId(), this.cbtBean);
			model.getReportingManager(this.cbtBean);
			model.getInitiatorDetail(this.cbtBean);
		} catch (final Exception e) {
			this.logger.info(" Exception occured in addNew() : " + e);
		}
		this.getNavigationPanel(1);
		return SUCCESS;
	}

	/**
	 * Method : sendForApproval.
	 * Purpose : For sending application to approval
	 * @return String
	 */
	public String sendForApproval() {
		try {
			final CBTFormModel model = new CBTFormModel();
			model.initiate(context, session);
			final boolean result = model.sendForApproval(this.cbtBean);
			final String initiator = this.cbtBean.getCompletedByID();
			final String applicationID = this.cbtBean.getCbtID();
			String approver = "";
			final String defaultManagerID = this.cbtBean.getDefaultManagerID();
			final String changedManagerID = this.cbtBean.getChangeMyManagerID();
			if (!"".equals(changedManagerID)) {
				approver = changedManagerID;
			} else {
				approver = defaultManagerID;
			}
			final String status = this.cbtBean.getStatus();
			
			if (result) {
				this.addActionMessage("Application successfully send for approval.\n Tracking number : " + this.cbtBean.getTrackingNumber());
				this.sendMailToManager(initiator, approver, status, applicationID);
			} else {
				this.addActionMessage("Exception occured while sending for approval.");
			}
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**
	 * Method : sendMailToManager.
	 * Purpose : For sending mail to manager
	 * @param initiator : Initiator ID
	 * @param approver : Approver ID
	 * @param status : Status
	 * @param applicationID : Application ID
	 */
	private void sendMailToManager(final String initiator, final String approver, final String status, final String applicationID) {
		try {
			final EmailTemplateBody mangTemp = new EmailTemplateBody();
			mangTemp.initiate(context, session);
			mangTemp.setEmailTemplate("D1-CBT MAIL FROM APPLICANT TO MANAGER");
			mangTemp.getTemplateQueries();
			final EmailTemplateQuery query1 = mangTemp.getTemplateQuery(1);
			query1.setParameter(1, initiator);

			final EmailTemplateQuery query2 = mangTemp.getTemplateQuery(2);
			query2.setParameter(1, approver);

			final EmailTemplateQuery query3 = mangTemp.getTemplateQuery(3);
			query3.setParameter(1, applicationID);

			final EmailTemplateQuery query4 = mangTemp.getTemplateQuery(4);
			query4.setParameter(1, applicationID);

			final EmailTemplateQuery query5 = mangTemp.getTemplateQuery(5);
			query5.setParameter(1, applicationID);

			mangTemp.configMailAlert();
			mangTemp.sendApplicationMail();
			mangTemp.clearParameters();
			mangTemp.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occured in sendMailToManager(): " + e);
		}
	}

	/**
	 * Method : f9Approver.
	 * Purpose : For selecting approver
	 * @return String
	 */
	public String f9Approver() {
		String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC ";

		if (this.cbtBean.getUserProfileDivision() != null && this.cbtBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.cbtBean.getUserProfileDivision() + "  )";
		} else {
			query += " WHERE 1=1 ";
		}

		System.out.println(this.cbtBean.getDefaultManagerID());
		if (!"".equals(this.cbtBean.getDefaultManagerID())) {
			query += "  AND EMP_ID NOT IN(" + this.cbtBean.getEmployeeID() + " )";
		}

		query += " AND EMP_ID NOT IN(" + this.cbtBean.getEmployeeID() + ")";

		final String[] headers = {"Approver ID", "Approver Name" };
		final String[] headerWidth = {this.f9Width20, this.f9Width80 };
		final String[] fieldNames = {"changeMyManagerToken", "changeMyManagerName", "changeMyManagerID" };
		final int[] columnIndex = {0, 1, 2 };
		final String submitFlag = this.stringFalse;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.stringF9;
	}

	/**
	 * Method : f9Employee.
	 * Purpose : For selecting employee
	 * @return String
	 */
	public String f9Employee() {
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN ,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) , " + 
				 " HRMS_D1_DEPARTMENT.DEPT_NUMBER , HRMS_DEPT.DEPT_NAME, HRMS_EMP_OFFC.EMP_ID " + 
				 " FROM HRMS_EMP_OFFC " + 
				 " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) " + 
				 " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) ";

		query += this.getprofileQuery(this.cbtBean);
		query += " AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		final String[] headers = {"Employee Token", "Employee", "Department No.", this.department };
		final String[] headerWidth = {this.f9Width20, this.f9Width30, this.f9Width10, this.f9Width40 };
		final String[] fieldNames = {"employeeToken", "employeeName", this.departmentNo, this.departmentName, "employeeID" };
		final int[] columnIndex = {0, 1, 2, 3, 4 };
		final String submitFlag = "true";
		final String submitToMethod = "CBTForm_getReportingPerson.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return this.stringF9;
	}

	/**
	 * Method : getReportingPerson.
	 * Purpose : For getting reporting person
	 * @return String
	 */
	public String getReportingPerson() {
		final CBTFormModel model = new CBTFormModel();
		model.initiate(context, session);
		model.getReportingManager(this.cbtBean);
		model.terminate();
		if ("".equals(this.cbtBean.getStatus())) {
			this.getNavigationPanel(1);
			this.cbtBean.setEnableAll(this.stringY);
		} else {
			this.getNavigationPanel(2);
			this.cbtBean.setEnableAll(this.stringY);
		}
		return SUCCESS;

	}

	/**
	 * Method : f9Course.
	 * Purpose : For selecting course
	 * @return String
	 */
	public String f9Course() {
		final String query = "select CLASS_NAME, CLASS_DESCRIPTION,CLASS_REQUEST_ID from HRMS_D1_CLASS_REQUEST  where STATUS='A' order by CLASS_REQUEST_ID";
		final String[] headers = {this.getMessage("course"), this.getMessage("course.desc") };
		final String[] headerWidth = {this.f9Width10, this.f9Width30 };
		final String[] fieldNames = {"courseNo", "courseDesc", "courseCode" };
		final int[] columnIndex = {0, 1, 2 };
		final String submitFlag = this.stringFalse;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return this.stringF9;
	}


	/**
	 * Method : f9Department.
	 * Purpose : For selecting departments
	 * @return String
	 * @throws Exception : Exception
	 */
	public String f9Department() throws Exception {
		final String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_NAME DESC";
		final String[] headers = {"Department ID", this.department };
		final String[] headerWidth = {this.f9Width20, this.f9Width80 };
		final String[] fieldNames = {this.departmentNo, this.departmentName };
		final int[] columnIndex = {0, 1 };
		final String submitFlag = this.stringFalse;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return this.stringF9;
	}

	/**
	 * Method : draftFunction.
	 * Purpose : For drafting application
	 * @return String
	 */
	public String draftFunction() {
		try {
			final CBTFormModel model = new CBTFormModel();
			model.initiate(context, session);
			boolean result = false;
			if ("".equals(this.cbtBean.getCbtID())) {
				result = model.save(this.cbtBean);
				if (result) {
					this.addActionMessage(this.getMessage("save"));
				} else {
					this.addActionMessage("duplicate record found ");
				}
			} else {
				result = model.update(this.cbtBean);
				if (result) {
					this.addActionMessage(this.getText("modMessage", ""));
				} else {
					this.addActionMessage("duplicate record found");
				}
			}
			model.terminate();
		} catch (final Exception e) {
			this.logger.info("Exception occured in draftFunction : " + e);
		}
		this.input();
		return INPUT;
	}

	/**
	 * Method : reset.
	 * Purpose : For setting fields
	 * @return String
	 */
	public String reset() {
		this.cbtBean.setCbtID("");
		this.cbtBean.setCourseCode("");
		this.cbtBean.setCourseDesc("");
		this.cbtBean.setCourseNo("");
		this.cbtBean.setAddress("");
		this.cbtBean.setCity("");
		this.cbtBean.setState("");
		this.cbtBean.setProvidence("");
		this.cbtBean.setCountry("");
		this.cbtBean.setZip("");
		this.cbtBean.setPhNo("");
		this.cbtBean.setEmailAddress("");
		this.cbtBean.setFaxNo("");
		this.cbtBean.setLocationRadioValue("");
		this.cbtBean.setChangeMyManagerID("");
		this.cbtBean.setChangeMyManagerToken("");
		this.cbtBean.setChangeMyManagerName("");
		this.addNew();
		return SUCCESS;
	}

	/**
	 * Method : back.
	 * Purpose : return to pending list
	 * @return String
	 */
	public String back() {
		this.input();
		return INPUT;
	}

	/**
	 * Method : delete.
	 * Purpose : return to pending list
	 * @return String
	 * @throws Exception : Exception
	 */
	public String delete() throws Exception {
		final CBTFormModel model = new CBTFormModel();
		model.initiate(context, session);
		boolean result;
		result = model.delete(this.cbtBean, request);
		if (result) {
			this.addActionMessage("Record Deleted successfully.");
		} else {
			this.addActionMessage("Record can not be deleted.");
		}
		model.terminate();
		this.input();
		return INPUT;
	}

	/**
	 * Method : viewApplicationFunction.
	 * Purpose : return showing details of application
	 * @return String
	 */
	public String viewApplicationFunction() {
		String cBTHiddenID = request.getParameter("CBTHiddenID");
		final String hiddenStatus = request.getParameter("hiddenStatus");
		try {
			final CBTFormModel model = new CBTFormModel();
			model.initiate(context, session);
			final String applCode = request.getParameter("applCode");
			if (applCode != null && applCode.length() > 0) {
				cBTHiddenID = applCode;
				
				System.out.println("cBTHiddenID : "+cBTHiddenID);
			}
			System.out.println("OUT cBTHiddenID : "+cBTHiddenID);
			model.viewApplicationFunction(this.cbtBean, cBTHiddenID);
			model.terminate();

			if (this.stringD.equals(hiddenStatus)) {
				this.getNavigationPanel(2);
				this.cbtBean.setApplnStatus(this.stringD);
				this.cbtBean.setEnableAll(this.stringY);
			} else if (hiddenStatus.equals(this.stringP)) {
				this.getNavigationPanel(3);
				this.cbtBean.setApplnStatus(this.stringP);
				this.cbtBean.setEnableAll(this.stringN);
			} else if (hiddenStatus.equals(this.stringB)) {
				this.getNavigationPanel(2);
				this.cbtBean.setApplnStatus(this.stringB);
				this.cbtBean.setEnableAll(this.stringY);
			} else if (this.stringA.equals(hiddenStatus)) {
				this.getNavigationPanel(4);
				this.cbtBean.setApplnStatus(this.stringA);
				this.cbtBean.setEnableAll(this.stringN);
			} else if (this.stringX.equals(hiddenStatus)) {
				this.getNavigationPanel(3);
				this.cbtBean.setEnableAll(this.stringN);
			} else if (hiddenStatus.equals(this.stringC)) {
				this.getNavigationPanel(3);
				this.cbtBean.setEnableAll(this.stringN);
			} else if (hiddenStatus.equals(this.stringR)) {
				this.getNavigationPanel(3);
				this.cbtBean.setApplnStatus(this.stringR);
				this.cbtBean.setEnableAll(this.stringN);
			} else if (hiddenStatus.equals(this.stringF)) {
				this.getNavigationPanel(3);
				this.cbtBean.setApplnStatus(this.stringF);
				this.cbtBean.setEnableAll(this.stringN);
			} else {
				this.getNavigationPanel(3);
				this.cbtBean.setEnableAll(this.stringN);
			}
			if (applCode != null && applCode.length() > 0) {
				this.getNavigationPanel(0);
				this.cbtBean.setEnableAll(this.stringN);
			}
		} catch (final Exception e) {
			this.getNavigationPanel(0);
			this.cbtBean.setEnableAll(this.stringN);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * Method : getApprovedList.
	 * Purpose : return getting approved list
	 * @return String
	 * @throws Exception : Exception
	 */
	public String getApprovedList() throws Exception {
		try {
			final CBTFormModel model = new CBTFormModel();
			model.initiate(context, session);
			final String userId = this.cbtBean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getApprovedList(this.cbtBean, request, userId);
			}
			this.cbtBean.setListType("approved");
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception in getApprovedList------" + e);
		}
		return INPUT;
	}

	/**
	 * Method : getRejectedList.
	 * Purpose : return getting rejected list
	 * @return String
	 * @throws Exception : Exception
	 */
	public String getRejectedList() throws Exception {
		try {
			final CBTFormModel model = new CBTFormModel();
			model.initiate(context, session);
			final String userId = this.cbtBean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getRejectedList(this.cbtBean, request, userId);
			}
			this.cbtBean.setListType("reject");
			model.terminate();
		} catch (final Exception e) {
			this.logger.info("Exception occured in getRejectedList : " + e);
		}
		return INPUT;
	}

	/**
	 * Method : checkNull.
	 * Purpose : return checking whether given string is null or not
	 * @return String
	 * @param result : String
	 */
	public String checkNull(final String result) {
		if (result == null || "".equals(result) || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}

}
