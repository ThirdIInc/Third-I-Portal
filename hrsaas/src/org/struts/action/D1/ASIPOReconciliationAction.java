/**
 * @author aa1380 : manish sakpal
 * Created on : 7th March 2011 
 */

package org.struts.action.D1;

import org.paradyne.bean.D1.ASIPOReconciliation;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ASIPOReconciliationModel;
import org.struts.lib.ParaActionSupport;

public class ASIPOReconciliationAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ASIPOReconciliationAction.class);
	ASIPOReconciliation asipoBean;
	
	public void prepare_local() throws Exception {
		asipoBean = new ASIPOReconciliation();
		asipoBean.setMenuCode(2011);
	}

	public Object getModel() {
		return asipoBean;
	}

	public ASIPOReconciliation getAsipoBean() {
		return asipoBean;
	}

	public void setAsipoBean(ASIPOReconciliation asipoBean) {
		this.asipoBean = asipoBean;
	}
	
	public String input()
	{
		try {
			ASIPOReconciliationModel model = new ASIPOReconciliationModel();
			model.initiate(context, session);
			String userId = asipoBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getPendingList(asipoBean, request,userId);	
			}			
			asipoBean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String addnew()
	{
		try {
			ASIPOReconciliationModel model = new ASIPOReconciliationModel();
			model.initiate(context, session);
			model.getLoginUserInformation(asipoBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		asipoBean.setApplicationID("");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String reset()
	{
		asipoBean.setApplicationID("");
		asipoBean.setPurchaseRequisition("");
		asipoBean.setDatePartsWereReceived("");
		asipoBean.setPurchaseOrderNumberOnSlip("");
		asipoBean.setDecisionOnePartNumber("");
		asipoBean.setVendorName("");
		asipoBean.setComments("");
		asipoBean.setPurchaseOrderNumber("");
		asipoBean.setProblemDescription("");
		asipoBean.setApplicationID("");
		asipoBean.setApplicationID("");
		asipoBean.setApplicationID("");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	
	public String delete()
	{
		try {
			ASIPOReconciliationModel model = new ASIPOReconciliationModel();
			model.initiate(context, session);
			boolean result = model.delRecord(asipoBean);
			model.terminate();
			if (result) {
				addActionMessage(getMessage("delete"));
			} else {
				addActionMessage(getMessage("no result"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}
	
	public String back() throws Exception {
		asipoBean.setApplicationID("");
		input();
		return INPUT;
	}
	
	
	public String draftFunction() throws Exception {
		try {
			
			ASIPOReconciliationModel model = new ASIPOReconciliationModel();
			model.initiate(context, session);
			boolean result;
			if (asipoBean.getApplicationID().equals("")) 
			{
				result = model.draftFunction(asipoBean);
				if (result) 
				{
					addActionMessage("Application drafted successfully.");
				} 
				else 
				{
					addActionMessage("Error occured");
					reset();
				}			
			} 
			else 
			{
				result = model.updateRecords(asipoBean);
				if (result) 
				{
					addActionMessage("Application drafted successfully.");
				} 
				else 
				{
					addActionMessage("Error occured");
					reset();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}
	
	
	
	public String sendForApprovalFunction()
	{	
		boolean logisticPersonAvailableOrNot = false;
		try {
			ASIPOReconciliationModel model = new ASIPOReconciliationModel();
			model.initiate(context, session);
			logisticPersonAvailableOrNot = model.isLogisticApprover();
			if(logisticPersonAvailableOrNot){
				boolean result = model.sendForApprovalFunction(asipoBean);
				String applicationId = asipoBean.getApplicationID();
				String employeeCode = asipoBean.getCompletedByID();
				String userID = asipoBean.getUserEmpId();
				String status = asipoBean.getStatus();
				model.terminate();
				if (result) {
					addActionMessage("Application Successfully Send For Approval.\n Tracking Number : "+asipoBean.getTrackingNumber());
					sendApplicantToApprovalMail(applicationId, userID, employeeCode, status);
				} else {
					addActionMessage("Error occured sending application.");
				}
			}else{
				addActionMessage("Corporate Procurement Group-NONINV (Logistics Department)authority approver is not available, \nPlease contact to HR Department.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(logisticPersonAvailableOrNot){
			input();
			return INPUT;
		}else{
			if(asipoBean.getApplicationID().equals(""))
			{
				getNavigationPanel(1);
			}else{
				getNavigationPanel(2);
				asipoBean.setApplicationStatus("D");
			}
				asipoBean.setEnableAll("Y");
			return SUCCESS;
		}
		
	}
	
	
	private void sendApplicantToApprovalMail(String applicationId,
			String userID, String employeeCode, String status) {
		try {
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-ASIPO RECONCILIATION APPLICATION FROM APPLICANT TO APPROVER");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userID);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, "0");
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			
			templateApp.configMailAlert();
			/*String[] empData = null;
			
			if(data != null && data.length > 1) {
				empData = new String[data.length];
				for(int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
				}
			}
			templateApp.configMailAlert();
			templateApp.sendApplicationMailToKeepInfo(empData);
			*/
			
			ASIPOReconciliationModel model = new ASIPOReconciliationModel();
			model.initiate(context, session);
			String keepinformDroupQuery = " SELECT NVL(APP_EMAIL_ID,'') AS APPROVER_ID FROM HRMS_D1_APPROVAL_SETTINGS "
										+ " WHERE APP_SETTINGS_TYPE = 'L' AND APP_EMAIL_ID IS NOT NULL";
			Object keepinformDroupData[][] = model.getSqlModel().getSingleResult(keepinformDroupQuery);
			
			if(keepinformDroupData!=null && keepinformDroupData.length>0){
				templateApp.sendApplicationMailToGroups(keepinformDroupData);
			}
			//templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String viewApplicationFunction()
	{		
		String purchaseHiddenID = request.getParameter("purchaseHiddenID");
		String hiddenStatus = request.getParameter("hiddenStatus");
		try {
			ASIPOReconciliationModel model = new ASIPOReconciliationModel();
			model.initiate(context, session);
			
			/*
			 * FOR SUPER USER
			 */
			String applCode=request.getParameter("applCode");
			if(applCode !=null &&applCode.length()>0){
				purchaseHiddenID=applCode;
			}
			
			model.viewApplicationFunction(asipoBean, purchaseHiddenID);
			model.terminate();
			
			
		
		if (hiddenStatus.equals("D")) {
			getNavigationPanel(2);
			asipoBean.setEnableAll("Y");
			asipoBean.setApplicationStatus("D");
		} else if (hiddenStatus.equals("P")) {
			getNavigationPanel(3);
			asipoBean.setEnableAll("N");
			asipoBean.setApplicationStatus("P");
		} else if (hiddenStatus.equals("B")) {
			getNavigationPanel(2);			
			asipoBean.setEnableAll("Y");
			asipoBean.setApplicationStatus("B");
		} else if (hiddenStatus.equals("A")) {
			getNavigationPanel(4);
			asipoBean.setEnableAll("N");
			asipoBean.setApplicationStatus("A");
		} else if (hiddenStatus.equals("X")) {
			getNavigationPanel(3);
			asipoBean.setEnableAll("N");
			asipoBean.setApplicationStatus("X");
		} else if (hiddenStatus.equals("C")) {
			getNavigationPanel(3);
			asipoBean.setEnableAll("N");
			asipoBean.setApplicationStatus("C");
		} else if (hiddenStatus.equals("R")) {
			getNavigationPanel(3);
			asipoBean.setEnableAll("N");
			asipoBean.setApplicationStatus("R");
		} else {
			getNavigationPanel(3);
			asipoBean.setEnableAll("N");
			asipoBean.setApplicationStatus("Z");
		}if(applCode !=null &&applCode.length()>0){
			getNavigationPanel(0);
			asipoBean.setEnableAll("N");
		}
	} catch (Exception e) {
		getNavigationPanel(0);
		asipoBean.setEnableAll("N");
		e.printStackTrace();
	}
		return SUCCESS;
	}
	
	
	public String getApprovedList() throws Exception {		
		try {
			ASIPOReconciliationModel model = new ASIPOReconciliationModel();
			model.initiate(context, session);
			String userId = asipoBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getApprovedList(asipoBean, request, userId);
			}	
			asipoBean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	public String getCancelledList() throws Exception {		
		try {
			ASIPOReconciliationModel model = new ASIPOReconciliationModel();
			model.initiate(context, session);
			String userId = asipoBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getCancelledList(asipoBean, request, userId);
			}	
			asipoBean.setListType("cancelled");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	public String getRejectedList() throws Exception {		
		try {
			ASIPOReconciliationModel model = new ASIPOReconciliationModel();
			model.initiate(context, session);
			String userId = asipoBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getRejectedList(asipoBean, request, userId);
			}
			asipoBean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String cancel()
	{
		String applicationId = asipoBean.getApplicationID();
		String employeeCode = asipoBean.getCompletedByID();
		String userID = asipoBean.getUserEmpId();
		String status = asipoBean.getStatus();	
		try {
			ASIPOReconciliationModel model = new ASIPOReconciliationModel();
			model.initiate(context, session);
			boolean result = model.cancelFunction(asipoBean, status);
			model.terminate();
			if (result) {
				addActionMessage("Application successfully send for cancellation.");
				sendApplicantToApprovalMail(applicationId, userID, employeeCode, status);
				} 
			else {
				addActionMessage("Error occured sending cancellation request.");
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		input();
		return INPUT;
	}
}
