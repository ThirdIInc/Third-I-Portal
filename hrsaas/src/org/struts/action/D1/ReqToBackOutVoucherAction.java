package org.struts.action.D1;

import org.paradyne.bean.D1.ReqToBackOutVoucher;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ReqToBackOutVoucherModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1380.
 *
 */
public class ReqToBackOutVoucherAction extends ParaActionSupport {
	/**	 statusD. *	 */
	private final String statusD = "D";
	/**	 statusP. *	 */
	private final String statusP = "P";
	/**	 statusA. *	 */
	private final String statusA = "A";
	/**	 statusR. *	 */
	private final String statusR = "R";
	/**	 statusC. *	 */
	private final String statusC = "C";
	/**	 statusZ. *	 */
	private final String statusZ = "Z";
	/**	 statusX. *	 */
	private final String statusX = "X";
	/**	 statusB. *	 */
	private final String statusB = "B";
	
	/** stringYes. *	 */
	private final String stringYes = "Y";
	
	/** stringNo. *	 */
	private final String stringNo = "N";
	
	/**	 * Logger.	 */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReqToBackOutVoucherAction.class);

	/**	 reqbean. *	 */
	private ReqToBackOutVoucher reqbean;

	/**	
	 * Method : prepare_local.
	 * Purpose : For setting Menu Code 
	 * @throws Exception : Exception 
	 */
	public void prepare_local() throws Exception {
		this.reqbean = new ReqToBackOutVoucher();
		this.reqbean.setMenuCode(1177);
	}

	/**	
	 * Method : getModel().
	 * Purpose : For getting model instance
	 * @return Object
	 */
	public Object getModel() {
		return this.reqbean;
	}

	/**
	 * Method : input
	 * Purpose : for getting current user related information.
	 * @return String
	 */
	public String input() {
		try {
			final ReqToBackOutVoucherModel model = new ReqToBackOutVoucherModel();
			model.initiate(context, session);
			final String userId = this.reqbean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getPendingList(this.reqbean, request, userId);
			}
			this.reqbean.setListType("pending");
			model.terminate();
		} catch (final Exception e) {
			this.logger.info("Exception occured in input() : " + e);
		}
		return INPUT;
	}

	/**
	 * Method : getApprovedList.
	 * Purpose : For getting approved application
	 * @return : String
	 * @throws Exception : Exception
	 */
	public String getApprovedList() throws Exception {
		try {
			final ReqToBackOutVoucherModel model = new ReqToBackOutVoucherModel();
			model.initiate(context, session);
			final String userId = this.reqbean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getApprovedList(this.reqbean, request, userId);
			}
			this.reqbean.setListType("approved");
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occured in getApprovedList() : " + e);
		}
		return INPUT;
	}

	/**
	 * Method : getCancelledList.
	 * Purpose : For getting canceled application list.
	 * @return : String
	 * @throws Exception : Exception
	 */
	public String getCancelledList() throws Exception {
		try {
			final ReqToBackOutVoucherModel model = new ReqToBackOutVoucherModel();
			model.initiate(context, session);
			final String userId = this.reqbean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getCancelledList(this.reqbean, request, userId);
			}
			this.reqbean.setListType("cancelled");
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception in getApprovedList() " + e);
		}
		return INPUT;
	}

	/**
	 * Method : getRejectedList.
	 * Purpose : For getting rejected application List
	 * @return : String
	 * @throws Exception : Exception
	 */
	public String getRejectedList() throws Exception {
		try {
			final ReqToBackOutVoucherModel model = new ReqToBackOutVoucherModel();
			model.initiate(context, session);
			final String userId = this.reqbean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getRejectedList(this.reqbean, request, userId);
			}
			this.reqbean.setListType("rejected");
			model.terminate();
		} catch (final Exception e) {
			this.logger.info("Exception occured in getRejectedList " + e); 
		}
		return INPUT;
	}

	/**
	 * Method : addNew.
	 * Purpose : For adding new application.
	 * @return : String
	 */
	public String addNew() {
		try {
			final ReqToBackOutVoucherModel model = new ReqToBackOutVoucherModel();
			model.initiate(context, session);
			model.getFormRelatedInformation(this.reqbean);
			model.terminate();
			this.reset();
		} catch (final Exception e) {
			this.logger.info("Exception occured in addNew : " + e); 
		}
		this.getNavigationPanel(1);
		return SUCCESS;
	}

	/**
	 * Method : draftFunction.
	 * Purpose : For drafting application
	 * @return : String
	 * @throws Exception : Exception
	 */
	public String draftFunction() throws Exception {
		try {
			final ReqToBackOutVoucherModel model = new ReqToBackOutVoucherModel();
			model.initiate(context, session);
			final boolean result;
			if ("".equals(this.reqbean.getRequestID())) {
				result = model.draftFunction(this.reqbean);
				if (result) {
					this.addActionMessage("Application saved successfully.");
				} else {
					this.addActionMessage("Error occured while darfting application.");
					this.reset();
				}
			} else {
				result = model.updateRecords(this.reqbean);
				if (result) {
					this.addActionMessage("Application drafted successfully.");
				} else {
					this.addActionMessage("Error occured");
					this.reset();
				}
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in draftFunction : " + e); 
		}
		this.input();
		return INPUT;
	}

	/**
	 * Method : back.
	 * Purpose : for getting back to the first page 
	 * @return : String
	 * @throws Exception : Exception
	 */
	public String back() throws Exception {
		this.reqbean.setRequestID("");
		this.input();
		return INPUT;
	}

	/**
	 * Method : callPage.
	 * Purpose : For paging.
	 * @return : String
	 * @throws Exception : Exception
	 */
	public String callPage() throws Exception {
		this.input();
		return INPUT;
	}

	/**
	 * Method : reset.
	 * Purpose : for reseting data
	 * @return : Sting
	 * @throws Exception : Exception
	 */
	public String reset() throws Exception {
		this.reqbean.setRequestID("");
		this.reqbean.setVendorID("");
		this.reqbean.setVendorName("");
		this.reqbean.setVendorNumber("");
		this.reqbean.setPurchaseOrderNumber("");
		this.reqbean.setLineNumber("");
		this.reqbean.setQuantity("");
		this.reqbean.setVoucherNumber("");
		this.reqbean.setReasonForRequest("");
		this.reqbean.setRma("");
		this.reqbean.setAirBillNumber("");
		this.reqbean.setCreditMemoRadio("");
		this.reqbean.setComments("");
		this.reqbean.setStatus("");
		this.getNavigationPanel(1);
		return SUCCESS;
	}

	/**
	 * MEthod : delete.
	 * Purpose : For Deleting application. 
	 * @return String.
	 */
	public String delete() {
		try {
			final ReqToBackOutVoucherModel model = new ReqToBackOutVoucherModel();
			model.initiate(context, session);
			final boolean result = model.delRecord(this.reqbean);
			model.terminate();
			if (result) {
				this.addActionMessage(this.getMessage("delete"));

			} else {
				this.addActionMessage(this.getMessage("no result"));
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in delete : " + e); 
		}
		this.input();
		return INPUT;
	}

	/**
	 * Method : sendForApprovalFunction.
	 * Purpose : For sending application for approval
	 * @return : String
	 */
	public String sendForApprovalFunction() {
		final ReqToBackOutVoucherModel model = new ReqToBackOutVoucherModel();
		model.initiate(context, session);
		final boolean financeAvailable = model.isFinanceApproverAvailable();
		try {
			if (financeAvailable) {
				final boolean result = model.sendForApprovalFunction(this.reqbean);
				final String applicationId = this.reqbean.getRequestID();
				final String employeeCode = this.reqbean.getEmployeeID();
				final String userID = this.reqbean.getUserEmpId();
				final String status = this.reqbean.getStatus();
				model.terminate();
				if (result) {
					this.addActionMessage("Application Successfully Send For Approval.\n Tracking Number : " + this.reqbean.getTrackingNumber());
					this.sendApplicantToApprovalMail(applicationId, userID, employeeCode, status);
				} else {
					this.addActionMessage("Error occured sending application.");
				}
			} else {
				this.addActionMessage("Finance authority person is not available,\n Please contact to HR department.");
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in sendForApprovalFunction() " + e); 
		}

		if (financeAvailable) {
			this.input();
			return INPUT;
		} else {
			if ("".equals(this.reqbean.getRequestID())) {
				this.getNavigationPanel(1);
			} else {
				this.getNavigationPanel(2);
				this.reqbean.setApplicationStatus(this.statusD);
			}
			this.reqbean.setEnableAll(this.stringYes);
			return SUCCESS;
		}

	}

	/**
	 * Method : sendApplicantToApprovalMail.
	 * Purpose : Send mail to approvers
	 * @param applicationId : Application ID
	 * @param userID : Current User ID 
	 * @param employeeCode : Employee ID
	 * @param status : Status
	 */
	private void sendApplicantToApprovalMail(final String applicationId,
			final String userID, final String employeeCode, final String status) {
		try {
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-REQUEST TO BACKOUT VOUCHER APPLICANT TO APPROVER");
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

			/*String[] empData = null;
			
			if(data != null && data.length > 1) {
				empData = new String[data.length];
				for(int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
				}
			}*/

			final ReqToBackOutVoucherModel model = new ReqToBackOutVoucherModel();
			model.initiate(context, session);

			final String query = " SELECT NVL(APP_EMAIL_ID,'') AS APPROVER_ID FROM HRMS_D1_APPROVAL_SETTINGS " + " WHERE APP_SETTINGS_TYPE = 'F' AND APP_EMAIL_ID IS NOT NULL";
			final Object[][] data = model.getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				templateApp.configMailAlert();
				templateApp.sendApplicationMailToGroups(data);
			}
			//templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (final Exception e) {
			this.logger.info("Exception occured in sendApplicantToApprovalMail() " + e); 
		}
	}

	/**
	 * Method : viewApplicationFunction.
	 * Purpose : For viewing application details
	 * @return : String
	 */
	public String viewApplicationFunction() {
		this.reqbean.setListTypeDetailPage(this.reqbean.getListType());
		String hiddenVoucherID = request.getParameter("hiddenVoucherID");
		final String hiddenStatus = request.getParameter("hiddenStatus");
		try {
			final ReqToBackOutVoucherModel model = new ReqToBackOutVoucherModel();
			model.initiate(context, session);
			/*
			 * FOR SUPER USER
			 */
			final String applCode = request.getParameter("applCode");
			if (applCode != null && applCode.length() > 0) {
				hiddenVoucherID = applCode;
			}

			model.viewApplicationFunction(this.reqbean, hiddenVoucherID);
			model.terminate();

			if (hiddenStatus.equals(this.statusD)) {
				this.getNavigationPanel(2);
				this.reqbean.setEnableAll(this.stringYes);
				this.reqbean.setApplicationStatus(this.statusD);
			} else if (hiddenStatus.equals(this.statusP)) {
				this.getNavigationPanel(3);
				this.reqbean.setEnableAll(this.stringNo);
				this.reqbean.setApplicationStatus(this.statusP);
			} else if (hiddenStatus.equals(this.statusB)) {
				this.getNavigationPanel(2);
				this.reqbean.setEnableAll(this.stringYes);
				this.reqbean.setApplicationStatus(this.statusB);
			} else if (hiddenStatus.equals(this.statusA)) {
				this.getNavigationPanel(4);
				this.reqbean.setEnableAll(this.stringNo);
				this.reqbean.setApplicationStatus(this.statusA);
			} else if (hiddenStatus.equals(this.statusX)) {
				this.getNavigationPanel(3);
				this.reqbean.setEnableAll(this.stringNo);
				this.reqbean.setApplicationStatus(this.statusX);
			} else if (hiddenStatus.equals(this.statusC)) {
				this.getNavigationPanel(3);
				this.reqbean.setEnableAll(this.stringNo);
				this.reqbean.setApplicationStatus(this.statusC);
			} else if (hiddenStatus.equals(this.statusR)) {
				this.getNavigationPanel(3);
				this.reqbean.setEnableAll(this.stringNo);
				this.reqbean.setApplicationStatus(this.statusR);
			} else {
				this.getNavigationPanel(3);
				this.reqbean.setEnableAll(this.stringNo);
				this.reqbean.setApplicationStatus(this.statusZ);
			}
			if (applCode != null && applCode.length() > 0) {
				this.getNavigationPanel(0);
				this.reqbean.setEnableAll(this.stringNo);
			}
		} catch (final Exception e) {
			this.getNavigationPanel(0);
			this.reqbean.setEnableAll(this.stringNo);
			this.logger.info("Exception occured in viewApplicationFunction() " + e); 
		}
		return SUCCESS;
	}

	/**
	 * Method : cancel.
	 * Purpose : For canceling application 
	 * @return : String
	 */
	public String cancel() {
		final String applicationId = this.reqbean.getRequestID();
		final String employeeCode = this.reqbean.getEmployeeID();
		final String userID = this.reqbean.getUserEmpId();
		final String status = this.reqbean.getStatus();
		try {
			final ReqToBackOutVoucherModel model = new ReqToBackOutVoucherModel();
			model.initiate(context, session);
			final boolean result = model.cancelFunction(this.reqbean, status);
			model.terminate();
			if (result) {
				this.addActionMessage("Application successfully send for cancellation.");
				this.sendApplicantToApprovalMail(applicationId, userID,
						employeeCode, status);
			} else {
				this.addActionMessage("Error occured sending cancellation request.");
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in getRejectedList() " + e); 
		}
		this.input();
		return INPUT;
	}

	/**
	 * Method : f9vendor.
	 * Purpose : For getting vendor list
	 * @return : String
	 */
	public String f9vendor() {
		final String query = "SELECT VENDOR_EIN_NUMBER, VENDOR_NAME, VENDOR_ID FROM HRMS_D1_VENDOR";
		final String[] headers = {"Vendor Number", "Vendor" };
		final String[] headerWidth = {"20", "80" };
		final String[] fieldNames = {"vendorNumber", "vendorName", "vendorID" };
		final int[] columnIndex = {0, 1, 2 };
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
}
