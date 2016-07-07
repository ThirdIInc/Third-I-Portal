package org.struts.action.D1;

import org.paradyne.bean.D1.ReqToBackOutApprover;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ReqToBackOutApproverModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1380
 *
 */
public class ReqToBackOutApproverAction extends ParaActionSupport {
	/** stringYes. *	 */
	private final String stringYes = "Y";
	
	/** stringNo. *	 */
	private final String stringNo = "N";
	
	/** stringApproved. *	 */
	private final String stringApproved = "approved";
	
	/** stringPending. * */
	private final String stringPending = "pending";
	
	/**  logger. *	 */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReqToBackOutVoucherAction.class);
	
	/**	 reqApproverBean. *	 */
	private ReqToBackOutApprover reqApproverBean;
	
	/**
	 * @return ReqToBackOutApprover : ReqToBackOutApprover.
	 */
	public ReqToBackOutApprover getReqApproverBean() {
		return this.reqApproverBean;
	}

	/**
	 * @param reqApproverBean : reqApproverBean.
	 */
	public void setReqApproverBean(final ReqToBackOutApprover reqApproverBean) {
		this.reqApproverBean = reqApproverBean;
	}

	/**
	 * Method : prepare_local.
	 * Purpose : For setting menu code
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		this.reqApproverBean = new ReqToBackOutApprover();
		this.reqApproverBean.setMenuCode(1179);
	}

	/**
	 * Method : getModel.
	 * Purpose : For getting model instance
	 * @return Object : Object
	 */
	public Object getModel() {
		return this.reqApproverBean;
	}
	
	/**
	 * Method : input.
	 * Purpose : For getting pending list
	 * @return String : String
	 */
	public String input() {
		try {
			final ReqToBackOutApproverModel model = new ReqToBackOutApproverModel();
			model.initiate(context, session);
			final String userId = this.reqApproverBean.getUserEmpId();
			final boolean isFinanceApprover = model.isFinanceApprover(userId);
			if (isFinanceApprover) {
				model.getPendingList(this.reqApproverBean, request);	
			}
			this.reqApproverBean.setListType(this.stringPending);
			model.terminate();
		} catch (final Exception e) {
			this.logger.info("Exception occured in input() : " + e);
		}
		return INPUT;
	}
	
	
	/**
	 * Method : getApprovedList.
	 * Purpose : For getting approved list
	 * @return : String
	 * @throws Exception : Exception
	 */
	public String getApprovedList() throws Exception {
		try {
			final ReqToBackOutApproverModel model = new ReqToBackOutApproverModel();
			model.initiate(context, session);
			final String userId = this.reqApproverBean.getUserEmpId();
			final boolean isFinanceApprover = model.isFinanceApprover(userId);
			if (isFinanceApprover) {
				model.getApprovedList(this.reqApproverBean, request);
			}
			this.reqApproverBean.setListType(this.stringApproved);
			model.terminate();
		} catch (final Exception e) {
			this.logger.info("Exception occured in getApprovedList() : " + e);
		}
		return INPUT;
	}
	
	/**
	 * Method : getRejectedList.
	 * Purpose : For getting rejected list
	 * @return : String
	 * @throws Exception : Exception
	 */
	public String getRejectedList() throws Exception {		
		try {
			final ReqToBackOutApproverModel model = new ReqToBackOutApproverModel();
			model.initiate(context, session);
			final String userId = this.reqApproverBean.getUserEmpId();
			final boolean isFinanceApprover = model.isFinanceApprover(userId);
			if (isFinanceApprover) {
				model.getRejectedList(this.reqApproverBean, request);
			}
			this.reqApproverBean.setListType("rejected");
			model.terminate();
		} catch (final Exception e) {
			this.logger.info("Exception occured in getRejectedList() : " + e);
		}
		return INPUT;
	}
	
	
	/**
	 * Method : approveApplication.
	 * Purpose : For approving application
	 * @return : String
	 */
	public String approveApplication() {	
		final String applicationId = this.reqApproverBean.getRequestID();
		final String employeeCode = this.reqApproverBean.getEmployeeID();
		final String userID = this.reqApproverBean.getUserEmpId();
		final String status = this.reqApproverBean.getStatus();
		try {
			final ReqToBackOutApproverModel model = new ReqToBackOutApproverModel();
			model.initiate(context, session);
			final boolean result = model.updateStatus(this.reqApproverBean, status);
			model.terminate();
			if (result) {
				this.addActionMessage("Application approved successfully.");
				this.sendApproverToApplicantMail(applicationId, userID, employeeCode, status); 
			} else {
				this.addActionMessage("Error occured in approveApplication.");
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in approveApplication() : " + e);
		}
		this.input();
		return INPUT;
	}
	
	/**
	 * Method : sendApproverToApplicantMail.
	 * Purpose : For sending mail from approver to applicant 
	 * @param applicationId : Application ID
	 * @param userID : Current User ID
	 * @param employeeCode : Applicant ID
	 * @param status : Status
	 */
	private void sendApproverToApplicantMail(final String applicationId, final String userID, final String employeeCode, final String status) {
		try {
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-REQUEST TO BACKOUT VOUCHER APPROVER TO INITIATOR");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); 
			templateQueryApp1.setParameter(1, userID);
			final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); 
			templateQueryApp2.setParameter(1, this.reqApproverBean.getCompletedByID());
			final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, userID);
			final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);
			templateApp.configMailAlert();
			
			final ReqToBackOutApproverModel model = new ReqToBackOutApproverModel();
			model.initiate(context, session);
			
			final String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID FROM HRMS_D1_APPROVAL_SETTINGS " + " WHERE APP_SETTINGS_TYPE = 'F' AND APP_EMAIL_ID IS NOT NULL ";
			final Object[][] data = model.getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				templateApp.sendApplicationMailToGroups(data);
			}
			
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (final Exception e) {
			this.logger.info("Exception occured in sendApproverToApplicantMail() : " + e);
		}
	}
	
	/**
	 * Method : rejectApplication.
	 * Purpose : For sending back application to applicant.
	 * @return : String
	 */
	public String rejectApplication() {
		final String applicationId = this.reqApproverBean.getRequestID();
		final String employeeCode = this.reqApproverBean.getEmployeeID();
		final String userID = this.reqApproverBean.getUserEmpId();
		final String status = this.reqApproverBean.getStatus();
		try {
			final ReqToBackOutApproverModel model = new ReqToBackOutApproverModel();
			model.initiate(context, session);
			final boolean result = model.updateStatus(this.reqApproverBean, status);
			model.terminate();
			if (result) {
				this.addActionMessage("Application rejected.");
				this.sendApproverToApplicantMail(applicationId, userID, employeeCode, status); 
			} else {
				this.addActionMessage("Error occured in rejectApplication.");
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in rejectApplication() : " + e);
		}
		this.input();
		return INPUT;
	}
	
	/**
	 * Method : sendBackApplication.
	 * Purpose : For sending back application to applicant.
	 * @return : String
	 */
	public String sendBackApplication() {
		final String applicationId = this.reqApproverBean.getRequestID();
		final String employeeCode = this.reqApproverBean.getEmployeeID();
		final String userID = this.reqApproverBean.getUserEmpId();
		final String approverID = this.reqApproverBean.getUserEmpId();		
		final String status = this.reqApproverBean.getStatus();
		try {
			final ReqToBackOutApproverModel model = new ReqToBackOutApproverModel();
			model.initiate(context, session);
			final boolean result = model.updateStatus(this.reqApproverBean, status);
			model.terminate();
			if (result) {
				this.addActionMessage("Application Sent back for changes.");
				this.sendApproverToApplicantMail(applicationId, userID, employeeCode, status); 
			} else {
				this.addActionMessage("Error occured.");
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in sendBackApplication() : " + e);
		}
		this.input();
		return INPUT;
	}
	
	/**
	 * Method : viewApplicationFunction.
	 * Purpose : For getting details of application
	 * @return : String
	 */	
	public String viewApplicationFunction() {
		final String typeOfList = this.reqApproverBean.getListType();
		this.reqApproverBean.setListTypeDetailPage(typeOfList);
		try {
			final String requestID = request.getParameter("rquestVoucherID");
			final ReqToBackOutApproverModel model = new ReqToBackOutApproverModel();
			model.initiate(context, session);
			model.viewApplicationFunction(this.reqApproverBean, requestID,
					typeOfList);
			model.getApproverCommentList(this.reqApproverBean, requestID);
			model.terminate();
		} catch (final Exception e) {
			this.logger.info("Exception occured in viewApplicationFunction() : " + e);
		}
		if (this.stringPending.equals(typeOfList)) {
			this.reqApproverBean.setEnableAll(this.stringYes);
			this.getNavigationPanel(1);
			this.reqApproverBean.setApplicationStatus("P");
		} else if (this.stringApproved.equals(typeOfList)) {
			this.reqApproverBean.setEnableAll(this.stringNo);
			this.getNavigationPanel(2);
			this.reqApproverBean.setApplicationStatus("A");
		} else {
			this.reqApproverBean.setEnableAll(this.stringNo);
			this.getNavigationPanel(2);
			this.reqApproverBean.setApplicationStatus("R");
		}
		return SUCCESS;
	}
	
	/**
	 * Method : closeApplication. Purpose : For returning back to first page
	 * 
	 * @return : String
	 * @throws Exception :
	 *             Exception
	 */
	public String closeApplication() throws Exception {
		this.input();
		return INPUT;
	}

}
