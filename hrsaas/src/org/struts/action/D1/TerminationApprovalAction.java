package org.struts.action.D1;

import org.paradyne.bean.D1.TerminationApproval;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.TerminationApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1380 : Manish Sakpal. *
 */
public class TerminationApprovalAction extends ParaActionSupport {
	/** terApprBean. * */
	private TerminationApproval terApprBean;

	/**
	 * Method : prepare_local. 
	 * Purpose : Used to set menu code and create bean instance
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		this.terApprBean = new TerminationApproval();
		this.terApprBean.setMenuCode(2039);
	}
	/**
	 * Method : getModel. 
	 * Purpose : Used to return bean instance
	 * @return Object
	 */
	public Object getModel() {
		return this.terApprBean;
	}

	/**
	 * @return the terApprBean
	 */
	public TerminationApproval getTerApprBean() {
		return this.terApprBean;
	}
	/**
	 * @param terApprBean : the terApprBean to set.
	 */
	public void setTerApprBean(final TerminationApproval terApprBean) {
		this.terApprBean = terApprBean;
	}

	@Override
	/** 
	 * Used to set pending list.
	 * @return String
	 */
	public String input() {
		try {
			final TerminationApprovalModel model = new TerminationApprovalModel();
			model.initiate(context, session);
			final String userId = this.terApprBean.getUserEmpId();
			final boolean isHRApprover = model.isHRApprover(userId);
			if (isHRApprover) {
				model.getPendingList(this.terApprBean, request);
			}
			this.terApprBean.setListType("pending");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**Used to get approved list of applications.
	 * @return String
	 */
	public String getApprovedList() {
		try {
			final TerminationApprovalModel model = new TerminationApprovalModel();
			model.initiate(context, session);
			final String userId = this.terApprBean.getUserEmpId();
			final boolean isHRApprover = model.isHRApprover(userId);
			if (isHRApprover) {
				model.getApprovedList(this.terApprBean, request);
			}
			this.terApprBean.setListType("approved");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**Used to get reject list of applications.
	 * @return String
	 */
	public String getRejectedList() {
		try {
			final TerminationApprovalModel model = new TerminationApprovalModel();
			model.initiate(context, session);
			final String userId = this.terApprBean.getUserEmpId();
			final boolean isHRApprover = model.isHRApprover(userId);
			if (isHRApprover) {
				model.getRejectedList(this.terApprBean, request);
			}
			this.terApprBean.setListType("rejected");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**Used to approve application.
	 * @return String
	 */
	public String approveApplication() {
		final String applicationId = this.terApprBean.getTerminationID();
		final String status = this.terApprBean.getStatus();
		try {
			final TerminationApprovalModel model = new TerminationApprovalModel();
			model.initiate(context, session);
			final boolean result = model.updateStatus(this.terApprBean, status, applicationId);
			model.terminate();
			if (result) {
				this.addActionMessage("Application approved successfully.");
				model.insertIntoApplicationSecurity(this.terApprBean);
				this.sendMailToManager();
			} else {
				this.addActionMessage("Error occured in approveApplication.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**Used to reject application.
	 * @return String
	 */
	public String rejectApplication() {
		final String applicationId = this.terApprBean.getTerminationID();
		final String status = this.terApprBean.getStatus();
		try {
			final TerminationApprovalModel model = new TerminationApprovalModel();
			model.initiate(context, session);
			final boolean result = model.updateStatus(this.terApprBean, status, applicationId);
			model.terminate();
			if (result) {
				this.addActionMessage("Application rejected.");
				this.sendMailToManager();
			} else {
				this.addActionMessage("Error occured in rejectApplication.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**Used to sent back application.
	 * @return String
	 */
	public String sendBackApplication() {
		final String applicationId = this.terApprBean.getTerminationID();
		final String status = this.terApprBean.getStatus();
		try {
			final TerminationApprovalModel model = new TerminationApprovalModel();
			model.initiate(context, session);
			final boolean result = model.updateStatus(this.terApprBean, status, applicationId);
			model.terminate();
			if (result) {
				this.addActionMessage("Application Sent back for changes.");
				this.sendMailToManager();
			} else {
				this.addActionMessage("Error occured in sendBackApplication.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**Used to view application details.
	 * @return String.
	 */
	public String viewApplicationFunction() {
		String terminationHiddenID = request.getParameter("hiddenApplicationID");
		final String hiddenStatus = request.getParameter("hiddenStatus");
		final TerminationApprovalModel model = new TerminationApprovalModel();
		final String applCode = request.getParameter("applCode");
		try {
			model.initiate(context, session);
			/*
			 * FOR SUPER USER
			 */
			if (applCode != null && applCode.length() > 0) {
				terminationHiddenID = applCode;
			}

			model.viewApplication(this.terApprBean, terminationHiddenID);
			model.terminate();

			if (hiddenStatus.equals("P")) {
				this.getNavigationPanel(1);
				this.terApprBean.setApplicationStatus("P");
			} else if (hiddenStatus.equals("A")) {
				this.getNavigationPanel(2);
				this.terApprBean.setApplicationStatus("A");
			} else {
				this.getNavigationPanel(2);
				this.terApprBean.setApplicationStatus("R");
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		if (applCode != null && applCode.length() > 0) {
			if (this.terApprBean.getStatus().equals("D") || 
					 this.terApprBean.getStatus().equals("A") ||
					 this.terApprBean.getStatus().equals("B") ||
					 this.terApprBean.getStatus().equals("R")) {
				this.getNavigationPanel(4);
				this.terApprBean.setEnableAll("N");
			} else {
				this.getNavigationPanel(3);
				this.terApprBean.setEnableAll("N");
			}
		}
		this.terApprBean.setEnableAll("N");
		return SUCCESS;
	}

	/**Return back to list.
	 * @return String
	 */
	public String backFunction() {
		final TerminationApprovalModel model = new TerminationApprovalModel();
		model.initiate(context, session);
		final String listType = Utility.checkNull(this.terApprBean.getListType());
		if ("".equals(listType) || "pending".equals(listType)) {
			this.input();
		} else if ("approved".equals(listType)) {
			this.getApprovedList();
		} else if ("rejected".equals(listType)) {
			this.getRejectedList();
		}
		return INPUT;
	}

	/**
	 * Used to send mail to manager.
	 */
	private void sendMailToManager() {
		try {
			final TerminationApprovalModel model = new TerminationApprovalModel();
			// MAIL FROM HR TO MANAGER
			model.initiate(context, session);
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-TERMINATION APPLICATION FROM HR TO MANAGER");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); 
			templateQueryApp1.setParameter(1, this.terApprBean.getUserEmpId());

			final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); 
			templateQueryApp2.setParameter(1, this.terApprBean.getCompletedByID());

			final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, this.terApprBean.getTerminationID());

			final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, this.terApprBean.getTerminationID());

			final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, this.terApprBean.getUserEmpId());

			final EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, this.terApprBean.getTerminationID());

			templateApp.configMailAlert();

			final String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID FROM HRMS_D1_APPROVAL_SETTINGS " + 
						   " WHERE APP_SETTINGS_TYPE ='H' AND APP_EMAIL_ID IS NOT NULL";
			final Object[][] data = model.getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				templateApp.sendApplicationMailToGroups(data);
			}

			final String initiatorQuery = " select NVL(TER_MANG_NAME,0) from HRMS_D1_TERMINATION where TER_ID = " + this.terApprBean.getTerminationID();
			final Object[][] initiatorData = model.getSqlModel().getSingleResult(initiatorQuery);
			String[] empData = null;
			if (initiatorData != null && initiatorData.length > 0) {
				empData = new String[initiatorData.length];
				for (int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(initiatorData[i][0]);
				}
			}

			if ("A".equals(this.terApprBean.getStatus())) {
				if (!this.terApprBean.getEmployeeNumber().equals(this.terApprBean.getCompletedByID())) {
					templateApp.sendApplicationMailToKeepInfo(String.valueOf(initiatorData[0][0]));
				}

			}

			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
