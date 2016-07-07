package org.struts.action.D1;

import org.paradyne.bean.D1.CreditCheckRequestApprover;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.CreditCheckRequestApproverModel;
import org.paradyne.model.D1.ReqToBackOutApproverModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1380 : Manish Sakpal.
 */
public class CreditCheckRequestApproverAction extends ParaActionSupport {
	/** * creditApproverBean. */
	private CreditCheckRequestApprover creditApproverBean;
	/** * inputStr. */
	private String inputStr = "input";
	/** * successStr. */
	private String successStr = "success";
	/** * pendingStr. */
	private String pendingStr = "pending";

	/**
	 * Method : prepare_local. 
	 * Purpose : Used to set menu code and create bean instance
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		this.creditApproverBean = new CreditCheckRequestApprover();
		this.creditApproverBean.setMenuCode(2017);
	}

	/**
	 * @return the creditBean
	 */
	public CreditCheckRequestApprover getCreditApproverBean() {
		return this.creditApproverBean;
	}
	/**
	 * @param creditApproverBean the bean to set.
	 */
	public void setCreditApproverBean(final CreditCheckRequestApprover creditApproverBean) {
		this.creditApproverBean = creditApproverBean;
	}

	/**
	 * Method : getModel. 
	 * Purpose : Used to return bean instance
	 * @return Object
	 */
	public Object getModel() {
		return this.creditApproverBean;
	}

	@Override
	/** 
	 * Used to set pending application list.
	 * @return String
	 */
	public String input() {
		try {
			final CreditCheckRequestApproverModel model = new CreditCheckRequestApproverModel();
			model.initiate(context, session);
			final String userId = this.creditApproverBean.getUserEmpId();
			final boolean isFinanceApprover = model.isFinanceApprover(userId);
			if (isFinanceApprover) {
				model.getpendingList(this.creditApproverBean, request);
			}
			this.creditApproverBean.setListType(this.pendingStr);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.inputStr;
	}

	/**
	 * Used to show approved list of applications.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String getApprovedList() throws Exception {
		try {
			final CreditCheckRequestApproverModel model = new CreditCheckRequestApproverModel();
			model.initiate(context, session);
			final String userId = this.creditApproverBean.getUserEmpId();
			final boolean isFinanceApprover = model.isFinanceApprover(userId);
			if (isFinanceApprover) {
				model.getApprovedList(this.creditApproverBean, request);
			}
			this.creditApproverBean.setListType("approved");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.inputStr;
	}

	/**
	 * Used to show rejected list of applications.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String getRejectedList() throws Exception {
		try {
			final CreditCheckRequestApproverModel model = new CreditCheckRequestApproverModel();
			model.initiate(context, session);
			final String userId = this.creditApproverBean.getUserEmpId();
			final boolean isFinanceApprover = model.isFinanceApprover(userId);
			if (isFinanceApprover) {
				model.getRejectedList(this.creditApproverBean, request);
			}
			this.creditApproverBean.setListType("rejected");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.inputStr;
	}

	/**
	 * Used to approve application.
	 * @return String
	 */
	public String approveApplication() {
		final String applicationId = this.creditApproverBean.getCreditID();
		final String employeeCode = this.creditApproverBean.getCreditRequestingPersonID();
		final String approverID = this.creditApproverBean.getUserEmpId();
		final String status = this.creditApproverBean.getStatus();
		try {
			final CreditCheckRequestApproverModel model = new CreditCheckRequestApproverModel();
			model.initiate(context, session);
			final boolean result = model.updateStatus(this.creditApproverBean, status, applicationId);
			model.terminate();
			if (result) {
				this.addActionMessage("Application approved successfully.");
				this.sendApproverToApplicantMail(applicationId, approverID, employeeCode, status);
			} else {
				this.addActionMessage("Error occured while approving application.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return this.inputStr;
	}

	/**
	 * Used to reject application.
	 * @return String
	 */
	public String rejectApplication() {
		final String applicationId = this.creditApproverBean.getCreditID();
		final String employeeCode = this.creditApproverBean.getCreditRequestingPersonID();
		final String approverID = this.creditApproverBean.getUserEmpId();
		final String status = this.creditApproverBean.getStatus();
		try {
			final CreditCheckRequestApproverModel model = new CreditCheckRequestApproverModel();
			model.initiate(context, session);
			final boolean result = model.updateStatus(this.creditApproverBean, status, applicationId);
			model.terminate();
			if (result) {
				this.addActionMessage("Application rejected.");
				this.sendApproverToApplicantMail(applicationId, approverID, employeeCode, status);
			} else {
				this.addActionMessage("Error occured.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return this.inputStr;
	}

	/**
	 * Used to send-back application.
	 * @return String
	 */
	public String sendBackApplication() {
		final String applicationId = this.creditApproverBean.getCreditID();
		final String employeeCode = this.creditApproverBean.getCreditRequestingPersonID();
		final String approverID = this.creditApproverBean.getUserEmpId();
		final String status = this.creditApproverBean.getStatus();
		try {
			final CreditCheckRequestApproverModel model = new CreditCheckRequestApproverModel();
			model.initiate(context, session);
			final boolean result = model.updateStatus(this.creditApproverBean, status, applicationId);
			model.terminate();
			if (result) {
				this.addActionMessage("Application Sent back for changes.");
				this.sendApproverToApplicantMail(applicationId, approverID, employeeCode, status);
			} else {
				this.addActionMessage("Error occured.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return this.inputStr;
	}

	/**Used to send email notification to applicant.
	 * @param applicationId : application Id
	 * @param approverID : approver ID
	 * @param employeeCode : employeeCode
	 * @param status : status
	 */
	private void sendApproverToApplicantMail(final String applicationId, final String approverID, final String employeeCode, final String status) {
		try {
			// MAIL FROM Approver To Applicant
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CREDIT CHECK REQUEST FROM APPROVER TO INITIATOR");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
			templateQueryApp1.setParameter(1, approverID);
			final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);
			templateQueryApp2.setParameter(1, this.creditApproverBean.getCompletedByID());
			final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, approverID);
			final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);

			templateApp.configMailAlert();

			final ReqToBackOutApproverModel model = new ReqToBackOutApproverModel();
			model.initiate(context, session);

			final String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'F' AND APP_EMAIL_ID IS NOT NULL ";
			final Object[][] data = model.getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				templateApp.sendApplicationMailToGroups(data);
			}
			templateApp.sendApplicationMailToKeepInfo(new String[] {this.creditApproverBean.getCreditAuthorizingPersonID()});
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Used to get application details.
	 * @return String
	 */
	public String viewApplicationFunction() {
		final String typeOfList = this.creditApproverBean.getListType();
		try {
			final String applicationID = request.getParameter("applicationID");
			final CreditCheckRequestApproverModel model = new CreditCheckRequestApproverModel();
			model.initiate(context, session);
			model.viewApplicationFunction(this.creditApproverBean, applicationID, typeOfList);
			model.getApproverCommentList(this.creditApproverBean, applicationID);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		if (this.pendingStr.equals(typeOfList)) {
			this.creditApproverBean.setEnableAll("N");
			this.creditApproverBean.setNewStatus(this.pendingStr);
			this.getNavigationPanel(1);
		} else if ("approved".equals(typeOfList)) {
			this.creditApproverBean.setEnableAll("N");
			this.creditApproverBean.setNewStatus("Approved");
			this.getNavigationPanel(2);
		} else {
			this.creditApproverBean.setNewStatus("Rejected");
			this.creditApproverBean.setEnableAll("N");
			this.getNavigationPanel(2);
		}
		return this.successStr;
	}

	/**Return to list page.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String backToListPage() throws Exception {
		this.input();
		return this.inputStr;
	}

}
