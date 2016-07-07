package org.struts.action.D1;

import org.paradyne.bean.D1.CBTApprovalBean;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.CBTApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1380
 *
 */
public class CBTApprovalAction extends ParaActionSupport {
	/** logger.	 *	 */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CBTApprovalAction.class);
	
	/** approved.	 *	 */
	private final String listApproved = "approved";
	
	/** listRejected.	 *	 */
	private final String listRejected = "rejected";
	
	/** strN.	 *	 */
	private final String strN = "N";
	
	/** cbtApprBean. *	 */
	private CBTApprovalBean cbtApprBean;
	
	/**
	 * @return cbtApprBean
	 */
	public CBTApprovalBean getCbtApprBean() {
		return this.cbtApprBean;
	}

	/**
	 * @param cbtApprBean : CBTApprovalBean
	 */
	public void setCbtApprBean(final CBTApprovalBean cbtApprBean) {
		this.cbtApprBean = cbtApprBean;
	}

	/**
	 * Method prepare_local.
	 * Purpose : For setting menu code
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		this.cbtApprBean = new CBTApprovalBean();
		this.cbtApprBean.setMenuCode(2014);
	}
	
	/**
	 * Method getModel.
	 * Purpose : For getting model
	 * @return Object : Object
	 */
	public Object getModel() {
		return this.cbtApprBean;
	}
	
	/**
	 * Method input.
	 * Purpose : For initial information
	 * @return String : String
	 */
	public String input() {
		CBTApprovalModel model;
		try {
			final String currentUserID = this.cbtApprBean.getUserEmpId();
			model = new CBTApprovalModel();
			model.initiate(context, session);
			final boolean isTrainingAuthority = model.isTrainingAuthority(currentUserID);
			if (isTrainingAuthority) {
				model.getForwardedPendingList(this.cbtApprBean, request,
						currentUserID);
			} else {
				model.getPendingList(this.cbtApprBean, request, currentUserID);
			}
			this.cbtApprBean.setListType("pending");
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occure in input() :" + e);
		}
		return INPUT;
	}
	
	/**
	 * Method getApprovedList.
	 * Purpose : For getting approved list
	 * @return String : String
	 */
	public String getApprovedList() {
		try {
			final CBTApprovalModel model = new CBTApprovalModel();
			model.initiate(context, session);
			final String currentUserID = this.cbtApprBean.getUserEmpId();
			final boolean isTrainingAuthority = model.isTrainingAuthority(currentUserID);
			if (isTrainingAuthority) {
				model.getApprovedListInTrainingAuthority(this.cbtApprBean, request);
			} else {
				model.getApprovedList(this.cbtApprBean, request);
			}
			this.cbtApprBean.setListType(this.listApproved);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occure in getApprovedList() :" + e);
		}
		return INPUT;
	}
	
	/**
	 * Method getRejectedList.
	 * Purpose : For getting rejected list
	 * @return String : String
	 */
	public String getRejectedList() {
		try {
			final CBTApprovalModel model = new CBTApprovalModel();
			model.initiate(context, session);
			model.getRejectedList(this.cbtApprBean, request);
			this.cbtApprBean.setListType(this.listRejected);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occure in getRejectedList() :" + e);
		}
		return INPUT;
	}
	
	/**
	 * Method viewApplicationFunction.
	 * Purpose : For showing details of the application
	 * @return String : String
	 */
	public String viewApplicationFunction() {
		try {
			final String cbtHiddenID = request.getParameter("cbtHiddenID");
			final CBTApprovalModel model = new CBTApprovalModel();
			model.initiate(context, session);
			model.viewApplication(this.cbtApprBean, cbtHiddenID);
			model.terminate();
			if (this.listApproved.equals(this.cbtApprBean.getListType()) || this.listRejected.equals(this.cbtApprBean.getListType())) {
				this.getNavigationPanel(2);
				this.cbtApprBean.setApproverCommentsFlag(false);
			} else {
				this.getNavigationPanel(1);
				this.cbtApprBean.setApproverCommentsFlag(true);	
			}
			this.cbtApprBean.setEnableAll(this.strN);
		} catch (final Exception e) {
			this.logger.error("Exception occure in viewApplicationFunction() :" + e);
		}
		return SUCCESS;
	}
	
	/**
	 * Method : backToListPage.
	 * Purpose : For getting back to pending list
	 * @return String
	 */
	public String backToListPage() {
		this.input();
		return INPUT;
	}
	
	/**
	 * Method : approveApplication.
	 * Purpose : For approving application
	 * @return String
	 */
	public String approveApplication() {
		boolean trainingAuthAvailble = false;
		final CBTApprovalModel model = new CBTApprovalModel();
		model.initiate(context, session);
		final String status = this.cbtApprBean.getStatus();
		final String applicationID = this.cbtApprBean.getCbtID();
		final String approverID = this.cbtApprBean.getUserEmpId();
		final String approverComments = this.cbtApprBean.getApproverComments();
		final String initiatorID = this.cbtApprBean.getCompletedByID();
		try {
			trainingAuthAvailble = model.isTrainingAuthorityAvailable();
			if (trainingAuthAvailble) {
				final boolean result = model.updateStatus(this.cbtApprBean, status, applicationID, approverID, approverComments);
				if (result) {
					this.addActionMessage("Application approved successfully.");
					if ("F".equals(status)) {
						this.sendMailToTrainingAuthority(approverID, applicationID, status);
					} else {
						this.sendMailToInitiator(initiatorID, approverID, status, applicationID);
					}
				} else {
					this.addActionMessage(" Exception is occured while processing application.");
				}
			} else {	
				this.addActionMessage("Training Authotity person is not available,\n Please contact to HR department.");
			}
		} catch (final Exception e) {
			this.logger.error("Exception occure in approveApplication() :" + e);
		}
		
		if (trainingAuthAvailble) {
			this.input();
			return INPUT;
		} else {
			this.getNavigationPanel(1);
			this.cbtApprBean.setEnableAll(this.strN);
			this.cbtApprBean.setStatus("_F");
			model.getApproverCommentList(this.cbtApprBean, applicationID);
			return SUCCESS;
		}
		
	}
	
	/**
	 * Method : sendMailToTrainingAuthority.
	 * Purpose : For sending mail to training authority
	 * @param approverID : Approver ID
	 * @param applicationID : Application ID
	 * @param status : Status
	 */
	private void sendMailToTrainingAuthority(final String approverID, final String applicationID, final String status) {
		try {
			final EmailTemplateBody mangTemp = new EmailTemplateBody();
			mangTemp.initiate(context, session);
			mangTemp.setEmailTemplate("D1-CBT MAIL FROM MANAGER TO TRAINING AUTHORITY");
			mangTemp.getTemplateQueries();

			final EmailTemplateQuery query1 = mangTemp.getTemplateQuery(1);
			query1.setParameter(1, approverID);
			
			final EmailTemplateQuery query2 = mangTemp.getTemplateQuery(2);
			query2.setParameter(1, "0");
			
			final EmailTemplateQuery query3 = mangTemp.getTemplateQuery(3);
			query3.setParameter(1, applicationID);
			
			final EmailTemplateQuery query4 = mangTemp.getTemplateQuery(4);
			query4.setParameter(1, applicationID);
			
			final EmailTemplateQuery query5 = mangTemp.getTemplateQuery(5);
			query5.setParameter(1, applicationID);
			
			mangTemp.configMailAlert();
			
			/*ArrayList<String>  trainingList =new ArrayList<String>();
			if(data!=null && data.length>1){
					for(int i = 1; i < data.length; i++) {
						trainingList.add(String.valueOf(data[i][0]));
					}
					
					String[] keepInformToData = new String[trainingList.size()];
					int count = 0;
					for (int i = 0; i < trainingList.size(); i++) {
						keepInformToData[count] = trainingList.get(i);
						count++;
					}
					mangTemp.configMailAlert();
					mangTemp.sendApplicationMailToKeepInfo(keepInformToData);
				}else{
					mangTemp.configMailAlert();
				}*/
			//Send group mail to "Training Authority"
			
			final CBTApprovalModel model = new CBTApprovalModel();
			model.initiate(context, session);

			final String trainingAuthQuery = " SELECT NVL(APP_EMAIL_ID,'') AS APPROVER_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'T' AND APP_EMAIL_ID IS NOT NULL";
			final Object[][] trainingAuthData = model.getSqlModel().getSingleResult(trainingAuthQuery);
			
			if (trainingAuthData != null && trainingAuthData.length > 0) {
				mangTemp.sendApplicationMailToGroups(trainingAuthData);
			}
			//mangTemp.sendApplicationMail();
			mangTemp.clearParameters();
			mangTemp.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occured in sendMailToTrainingAuthority(): " + e);
		}
	}

	/**
	 * Method : rejectApplication.
	 * Purpose : For rejecting application
	 * @return String
	 */
	public String rejectApplication() {
		try {
			final String status = this.cbtApprBean.getStatus();
			final String applicationID = this.cbtApprBean.getCbtID();
			final String approverID = this.cbtApprBean.getUserEmpId();
			final String approverComments = this.cbtApprBean.getApproverComments();
			
			final String initiatorID = this.cbtApprBean.getCompletedByID();
			final CBTApprovalModel model = new CBTApprovalModel();
			model.initiate(context, session);
			final boolean result = model.updateRejectStatus(this.cbtApprBean, status, applicationID, approverID, approverComments);
			if (result) {
				this.addActionMessage("Application rejected successfully.");
				this.sendMailToInitiator(initiatorID, approverID, status, applicationID);
			} else {
				this.addActionMessage("Exception occured while processing the application.");
			}
		} catch (final Exception e) {
			this.logger.error("Exception occure in rejectApplication() :" + e);
		}
		this.input();
		return INPUT;
	}
	
	
	/**
	 * Method : sendBackApplication.
	 * Purpose : For sending back application to the applicant
	 * @return String
	 */
	public String sendBackApplication() {
		try {
			final String status = this.cbtApprBean.getStatus();
			final String applicationID = this.cbtApprBean.getCbtID();
			final String approverID = this.cbtApprBean.getUserEmpId();
			final String approverComments = this.cbtApprBean.getApproverComments();
			final String initiatorID = this.cbtApprBean.getCompletedByID();
			final String employeeID = this.cbtApprBean.getEmployeeID();
			
			final CBTApprovalModel model = new CBTApprovalModel();
			model.initiate(context, session);
			final boolean result = model.updateSentBackStatus(this.cbtApprBean, status, applicationID, approverID, approverComments);
			if (result) {
				this.addActionMessage("Application sent back successfully.");
				this.sendMailToInitiator(initiatorID, approverID, status, applicationID);
			} else {
				this.addActionMessage("exception occured while processing application.");
			}
		} catch (final Exception e) {
			this.logger.error("Exception occure in sendBackApplication() :" + e);
		}
		this.input();
		return INPUT;
	}
	
	/**
	 * Method : sendMailToInitiator.
	 * Purpose : For sending mail to initiator
	 * @param initiatorID : Initiator ID
	 * @param approverID : Approver ID
	 * @param status : Status
	 * @param applicationID : Application ID
	 */
	private void sendMailToInitiator(final String initiatorID, final String approverID, final String status, final String applicationID) {
		try {
			final EmailTemplateBody mangTemp = new EmailTemplateBody();
			mangTemp.initiate(context, session);
			mangTemp.setEmailTemplate("D1-CBT MAIL FROM TRAINING AUTHORITY TO INITIATOR");
			mangTemp.getTemplateQueries();

			final EmailTemplateQuery query1 = mangTemp.getTemplateQuery(1);
			query1.setParameter(1, approverID);
			
			final EmailTemplateQuery query2 = mangTemp.getTemplateQuery(2);
			query2.setParameter(1, initiatorID);
			
			final EmailTemplateQuery query3 = mangTemp.getTemplateQuery(3);
			query3.setParameter(1, applicationID);
			
			final EmailTemplateQuery query4 = mangTemp.getTemplateQuery(4);
			query4.setParameter(1, applicationID);
			
			final EmailTemplateQuery query5 = mangTemp.getTemplateQuery(5);
			query5.setParameter(1, applicationID);
			
			final EmailTemplateQuery query6 = mangTemp.getTemplateQuery(6);
			query6.setParameter(1, approverID);
			
			final CBTApprovalModel model = new CBTApprovalModel();
			model.initiate(context, session);
			
			/*if(status.equals("_P")){
				mangTemp.configMailAlert();
			}else{
				String query = " SELECT NVL(APP_SETTINGS_EMP_ID,0) AS APPROVER_ID "
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)"
					+ " WHERE APP_SETTINGS_TYPE = 'T' AND APP_SETTINGS_EMP_ID NOT IN ("+this.cbtApprBean.getUserEmpId()+")" 
					+" ORDER BY APP_SETTINGS_EMP_ID";
				Object data[][] = model.getSqlModel().getSingleResult(query);
	
				ArrayList<String>  trainingList =new ArrayList<String>();
				if(data!=null && data.length>0){
					for(int i = 0; i < data.length; i++) {
						trainingList.add(String.valueOf(data[i][0]));
					}
					trainingList.add(this.cbtApprBean.getApprovedID());
			
					String[] keepInformToData = new String[trainingList.size()];
					int count = 0;
					for (int i = 0; i < trainingList.size(); i++) {
						keepInformToData[count] = trainingList.get(i);
						count++;
					}
					mangTemp.configMailAlert();
					mangTemp.sendApplicationMailToKeepInfo(keepInformToData);
				}else{
					mangTemp.configMailAlert();
					mangTemp.sendApplicationMailToKeepInfo(new String[]{this.cbtApprBean.getApprovedID()});
				}
			}*/
			
			
			if ("_P".equals(status)) {
				mangTemp.configMailAlert();
			} else {
				mangTemp.configMailAlert();
				final String query = " SELECT NVL(APP_EMAIL_ID,'') AS APPROVER_ID FROM HRMS_D1_APPROVAL_SETTINGS " + 
						 " WHERE APP_SETTINGS_TYPE = 'T' AND APP_EMAIL_ID IS NOT NULL ";
				final Object[][] data = model.getSqlModel().getSingleResult(
						query);
				if (data != null && data.length > 0) {
					mangTemp.sendApplicationMailToGroups(data);
				}
				mangTemp.sendApplicationMailToKeepInfo(new String[] {this.cbtApprBean.getApprovedID()});
			}
			mangTemp.sendApplicationMail();
			mangTemp.clearParameters();
			mangTemp.terminate();
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception occured in sendRejectedMailToInitiator(): " + e);
		}
	}

}
