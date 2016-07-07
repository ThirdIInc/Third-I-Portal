package org.struts.action.D1;

import org.paradyne.bean.D1.CreditCheckRequest;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.CreditCheckRequestModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1380 : Manish Sakpal.
 * Created on 11th March 2011
 */
public class CreditCheckRequestAction extends ParaActionSupport {
	/** * creditBean. */
	private CreditCheckRequest creditBean;
	/** * success. */
	private final String success = "success";
	/** * yStr. */
	private final String yStr = "Y";
	/** * nStr. */
	private final String nStr = "N";

	/**
	 * Method : prepare_local. 
	 * Purpose : Used to set menu code and create bean instance
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		this.creditBean = new CreditCheckRequest();
		this.creditBean.setMenuCode(2016);
	}

	/**
	 * Method : getModel. 
	 * Purpose : Used to return bean instance
	 * @return Object
	 */
	public Object getModel() {
		return this.creditBean;
	}

	/**
	 * @return the creditBean
	 */
	public CreditCheckRequest getCreditBean() {
		return this.creditBean;
	}

	/**
	 * @param creditBean the bean to set.
	 */
	public void setCreditBean(final CreditCheckRequest creditBean) {
		this.creditBean = creditBean;
	}

	@Override
	/** 
	 * Used to set drafted, in-process, sent-back application list.
	 * @return String
	 */
	public String input() {
		try {
			final CreditCheckRequestModel model = new CreditCheckRequestModel();
			model.initiate(context, session);
			final String userId = this.creditBean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getPendingList(this.creditBean, request, userId);
			}
			this.creditBean.setListType("pending");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * Used to show approved list of applications.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String getApprovedList() throws Exception {
		try {
			final CreditCheckRequestModel model = new CreditCheckRequestModel();
			model.initiate(context, session);
			final String userId = this.creditBean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getApprovedList(this.creditBean, request, userId);
			}
			this.creditBean.setListType("approved");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * Used to show cancel list of applications.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String getCancelledList() throws Exception {
		try {
			final CreditCheckRequestModel model = new CreditCheckRequestModel();
			model.initiate(context, session);
			final String userId = this.creditBean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getCancelledList(this.creditBean, request, userId);
			}
			this.creditBean.setListType("cancelled");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * Used to show rejected list of applications.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String getRejectedList() throws Exception {
		try {
			final CreditCheckRequestModel model = new CreditCheckRequestModel();
			model.initiate(context, session);
			final String userId = this.creditBean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getRejectedList(this.creditBean, request, userId);
			}
			this.creditBean.setListType("rejected");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * Used to add new application.
	 * @return String
	 */
	public String addnew() {
		try {
			final CreditCheckRequestModel model = new CreditCheckRequestModel();
			model.initiate(context, session);
			final String userId = this.creditBean.getUserEmpId();
			model.getInformation(this.creditBean, userId);
			this.creditBean.setCreditID("");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(1);
		return this.success;
	}

	/**
	 * Used to select employee defined in HRMS_EMP_OFFC.
	 * @return String
	 */
	public String f9Authorizer() {
		String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC ";
		if (this.creditBean.getUserProfileDivision() != null && this.creditBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.creditBean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		final String[] headers = {"Authorizer ID", "Authorizer Name"};
		final String[] headerWidth = {"20", "80"};
		final String[] fieldNames = {"creditAuthorizingPersonToken", "creditAuthorizingPersonName", "creditAuthorizingPersonID"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Used to select city defined in HRMS_LOCATION.
	 * @return String
	 */
	public String f9city() {
		final String query = "SELECT CITY.LOCATION_NAME AS CITY, STATE.LOCATION_NAME AS STATE, CITY.LOCATION_CODE AS CITY_CODE FROM HRMS_LOCATION STATE " + 
				"LEFT JOIN HRMS_LOCATION CITY ON(STATE.LOCATION_CODE = CITY.LOCATION_PARENT_CODE) WHERE CITY.LOCATION_LEVEL_CODE = 2 " + 
				"ORDER BY UPPER(CITY)";
		final String[] headers = {"City Code", "City"};
		final String[] headerWidth = {"10", "45"};
		final String[] fieldNames = {"cityName", "state", "cityId"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Used to save new application details.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String draftFunction() throws Exception {
		try {
			final CreditCheckRequestModel model = new CreditCheckRequestModel();
			model.initiate(context, session);
			boolean result;
			if ("".equals(this.creditBean.getCreditID())) {
				result = model.draftFunction(this.creditBean);
				if (result) {
					this.addActionMessage("Application  drafted successfully.");
				} else {
					this.addActionMessage("Error occured in draftFunction");
					this.reset();
				}
			} else {
				result = model.updateRecords(this.creditBean);
				if (result) {
					this.addActionMessage("Application drafted successfully.");
				} else {
					this.addActionMessage("Error occured");
					this.reset();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}
	
	/**
	 * Used to save send application for approval.
	 * @return String
	 */
	public String sendForApprovalFunction() {
		boolean financePersonAvailableOrNot = false;
		try {
			final CreditCheckRequestModel model = new CreditCheckRequestModel();
			model.initiate(context, session);
			financePersonAvailableOrNot = model.isFinancePersonPresent(this.creditBean);
			if (financePersonAvailableOrNot) {
				final boolean result = model.sendForApprovalFunction(this.creditBean);
				final String applicationId = this.creditBean.getCreditID();
				final String employeeCode = this.creditBean.getCreditRequestingPersonID();
				final String userID = this.creditBean.getUserEmpId();
				final String status = this.creditBean.getStatus();
				model.terminate();
				if (result) {
					this.addActionMessage("Application send for approval. \n Tracking number : " + this.creditBean.getTrackingNumber());
					this.sendApplicantToApprovalMail(applicationId, userID, employeeCode, status);
				} else {
					this.addActionMessage("Error occured sending application.");
				}
			} else {
				this.addActionMessage("Finance authority approver is not available, Please contact to HR Department.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		if (financePersonAvailableOrNot) {
			this.input();
			return INPUT;
		} else {
			if ("".equals(this.creditBean.getCreditID())) {
				this.getNavigationPanel(1);
			} else {
				this.getNavigationPanel(2);
			}
			this.creditBean.setEnableAll(this.yStr);
			return this.success;
		}
	}

	/**Used to view details of application.
	 * @return String
	 */
	public String viewApplicationFunction() {
		String creditHiddenID = request.getParameter("creditHiddenID");
		final String hiddenStatus = request.getParameter("hiddenStatus");
		try {
			final CreditCheckRequestModel model = new CreditCheckRequestModel();
			model.initiate(context, session);
			final String applCode = request.getParameter("applCode");
			if (applCode != null && applCode.length() > 0) {
				creditHiddenID = applCode;
			}

			model.viewApplicationFunction(this.creditBean, creditHiddenID);
			model.terminate();

			if ("D".equals(hiddenStatus)) {
				this.getNavigationPanel(2);
				this.creditBean.setEnableAll(this.yStr);
			} else if ("P".equals(hiddenStatus)) {
				this.getNavigationPanel(3);
				this.creditBean.setEnableAll(this.nStr);
			} else if ("B".equals(hiddenStatus)) {
				this.getNavigationPanel(2);
				this.creditBean.setEnableAll(this.yStr);
			} else if ("A".equals(hiddenStatus)) {
				this.getNavigationPanel(4);
				this.creditBean.setEnableAll(this.nStr);
			} else if ("X".equals(hiddenStatus)) {
				this.getNavigationPanel(3);
				this.creditBean.setEnableAll(this.nStr);
			} else if ("C".equals(hiddenStatus)) {
				this.getNavigationPanel(3);
				this.creditBean.setEnableAll(this.nStr);
			} else if ("R".equals(hiddenStatus)) {
				this.getNavigationPanel(3);
				this.creditBean.setEnableAll(this.nStr);
			} else {
				this.getNavigationPanel(3);
				this.creditBean.setEnableAll(this.nStr);
			}
			if (applCode != null && applCode.length() > 0) {
				this.creditBean.setEnableAll(this.nStr);
			}
		} catch (final Exception e) {
			this.creditBean.setEnableAll(this.nStr);
			e.printStackTrace();
		}
		return this.success;
	}

	/**Used to delete application.
	 * @return String
	 */
	public String delete() {
		try {
			final CreditCheckRequestModel model = new CreditCheckRequestModel();
			model.initiate(context, session);
			final boolean result = model.delRecord(this.creditBean);
			model.terminate();
			if (result) {
				this.addActionMessage(this.getMessage("delete"));
			} else {
				this.addActionMessage(this.getMessage("no result"));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**Used to cancel application.
	 * @return String
	 */
	public String cancel() {
		final String applicationId = this.creditBean.getCreditID();
		final String employeeCode = this.creditBean.getCreditRequestingPersonID();
		final String userID = this.creditBean.getUserEmpId();
		final String status = this.creditBean.getStatus();
		try {
			final CreditCheckRequestModel model = new CreditCheckRequestModel();
			model.initiate(context, session);
			final boolean result = model.cancelFunction(this.creditBean, status);
			model.terminate();
			if (result) {
				this.addActionMessage("Application successfully send for cancellation.");
				this.sendApplicantToApprovalMail(applicationId, userID, employeeCode, status);
			} else {
				this.addActionMessage("Error occured sending cancellation request.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**Used to reset all the form fields.
	 * @return String
	 */
	public String reset() {
		this.creditBean.setCompanyName("");
		this.creditBean.setStreetAddress("");
		this.creditBean.setCityId("");
		this.creditBean.setCityName("");
		this.creditBean.setState("");
		this.creditBean.setZipCode("");
		this.creditBean.setPhoneNumber("");
		this.creditBean.setAmountOfCreditToBeAdvance("");
		this.creditBean.setIsMonthlyAnnually("");
		this.creditBean.setIsMonthlyAnnuallyRadioOptionValue("");
		this.creditBean.setIsExistingCustomer("");
		this.creditBean.setIsExistingCustomerRadioOptionValue("");
		this.creditBean.setCustomerName("");
		this.creditBean.setTypeOfEquipment("");
		this.creditBean.setComments("");
		this.creditBean.setNumberOfSites("");
		this.creditBean.setStatus("");
		return this.success;
	}

	/**Used to return back to list page.
	 * @return String
	 */
	public String back() {
		this.input();
		return INPUT;
	}

	/**Method : sendApplicantToApprovalMail.
	 * Purpose : Used to send email notification to approver.
	 * @param applicationId : Application id
	 * @param userID : Initiator id
	 * @param employeeCode : employee code
	 * @param status : Application status
	 */
	private void sendApplicantToApprovalMail(final String applicationId,
			final String userID, final String employeeCode, final String status) {
		try {
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CREDIT CHECK REQUEST FROM APPLICANT TO APPROVER");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
			templateQueryApp1.setParameter(1, userID);
			final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); 
			templateQueryApp2.setParameter(1, "0");
			final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);

			templateApp.configMailAlert();

			final CreditCheckRequestModel model = new CreditCheckRequestModel();
			model.initiate(context, session);

			final Object[][] data = model.getSqlModel().getSingleResult(" SELECT NVL(APP_EMAIL_ID,'') AS APPROVER_ID FROM HRMS_D1_APPROVAL_SETTINGS " + 
							  " WHERE APP_SETTINGS_TYPE = 'F' AND APP_EMAIL_ID IS NOT NULL");
			if (data != null && data.length > 0) {
				templateApp.sendApplicationMailToGroups(data);
			}

			templateApp.sendApplicationMailToKeepInfo(new String[] {this.creditBean.getCreditAuthorizingPersonID()});
			//templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
