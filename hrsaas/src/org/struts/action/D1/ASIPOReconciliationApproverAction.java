/**
 * @author aa1380 : manish sakpal
 * Created on : 8th March 2011 
 */

package org.struts.action.D1;

import org.paradyne.bean.D1.ASIPOReconciliationApprover;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ASIPOReconciliationApproverModel;
import org.paradyne.model.D1.ASIPOReconciliationModel;
import org.struts.lib.ParaActionSupport;

public class ASIPOReconciliationApproverAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ASIPOReconciliationApproverAction.class);
	ASIPOReconciliationApprover asipoApprBean;
	public void prepare_local() throws Exception {
		asipoApprBean = new ASIPOReconciliationApprover();
		asipoApprBean.setMenuCode(2012);
	}

	public ASIPOReconciliationApprover getAsipoApprBean() {
		return asipoApprBean;
	}

	public void setAsipoApprBean(ASIPOReconciliationApprover asipoApprBean) {
		this.asipoApprBean = asipoApprBean;
	}

	public Object getModel() {
		return asipoApprBean;
	}
	
	public String input()
	{
		try {
			ASIPOReconciliationApproverModel model = new ASIPOReconciliationApproverModel();
			model.initiate(context, session);
			String userId = asipoApprBean.getUserEmpId();
			boolean isLogisticApprover = model.isLogisticApprover(userId);
			if(isLogisticApprover)
			{
				model.getpendingList(asipoApprBean, request);
			}
			asipoApprBean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	
	public String getApprovedList() throws Exception {		
		try {
			ASIPOReconciliationApproverModel model = new ASIPOReconciliationApproverModel();
			model.initiate(context, session);
			String userId = asipoApprBean.getUserEmpId();
			boolean isLogisticApprover = model.isLogisticApprover(userId);
			if(isLogisticApprover)
			{
				model.getApprovedList(asipoApprBean, request);
			}
			asipoApprBean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	public String getRejectedList() throws Exception {		
		try {
			ASIPOReconciliationApproverModel model = new ASIPOReconciliationApproverModel();
			model.initiate(context, session);
			String userId = asipoApprBean.getUserEmpId();
			boolean isLogisticApprover = model.isLogisticApprover(userId);
			if(isLogisticApprover)
			{
				model.getRejectedList(asipoApprBean, request);
			}
			asipoApprBean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	
	public String approveApplication()
	{	
		String applicationId = asipoApprBean.getPurchaseID();
		String employeeCode = asipoApprBean.getCompletedByID();
		String approverID = asipoApprBean.getUserEmpId();
		String status = asipoApprBean.getStatus();
		try {
			ASIPOReconciliationApproverModel model = new ASIPOReconciliationApproverModel();
			model.initiate(context, session);
			//boolean result = model.approveApplicationFunction(asipoApprBean, status);
			boolean result = model.updateStatus(asipoApprBean, status, applicationId);
			model.terminate();
			if (result) {
				addActionMessage("Application approved successfully.");
				sendApproverToApplicantMail(applicationId, approverID, employeeCode, status); 
			} else {
				addActionMessage("Error occured.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	
	public String rejectApplication()
	{
		String applicationId = asipoApprBean.getPurchaseID();
		String employeeCode = asipoApprBean.getCompletedByID();
		String approverID = asipoApprBean.getUserEmpId();
		String status = asipoApprBean.getStatus();
		try {
			ASIPOReconciliationApproverModel model = new ASIPOReconciliationApproverModel();
			model.initiate(context, session);
			//boolean result = model.rejectApplicationFunction(asipoApprBean, status);
			boolean result = model.updateStatus(asipoApprBean, status, applicationId);
			model.terminate();
			if (result) {
				addActionMessage("Application rejected.");
				sendApproverToApplicantMail(applicationId, approverID, employeeCode, status);  
			} else {
				addActionMessage("Error occured.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}
	
	
	public String sendBackApplication()
	{
		String applicationId = asipoApprBean.getPurchaseID();
		String employeeCode = asipoApprBean.getCompletedByID();
		String approverID = asipoApprBean.getUserEmpId();
		String status = asipoApprBean.getStatus();
		try {
			ASIPOReconciliationApproverModel model = new ASIPOReconciliationApproverModel();
			model.initiate(context, session);
			//boolean result = model.sendBackApplicationFunction(asipoApprBean, status);
			boolean result = model.updateStatus(asipoApprBean, status, applicationId);
			model.terminate();
			if (result) {
				addActionMessage("Application Sent back for changes.");
				sendApproverToApplicantMail(applicationId, approverID, employeeCode, status); 
			} else {
				addActionMessage("Error occured.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}
	
	
	private void sendApproverToApplicantMail(String applicationId,
			String approverID, String employeeCode, String status) {
		try {
			// MAIL FROM Approver To Applicant
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-ASIPO RECONCILIATION APPLICATION FROM APPROVER TO INITIATOR");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, approverID);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, asipoApprBean.getCompletedByID());
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, approverID);
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);
			templateApp.configMailAlert();
			
			ASIPOReconciliationApproverModel model = new ASIPOReconciliationApproverModel();
			model.initiate(context, session);
			String query = " SELECT NVL(APP_EMAIL_ID,'') AS APPROVER_ID FROM HRMS_D1_APPROVAL_SETTINGS "
							+ " WHERE APP_SETTINGS_TYPE = 'L' AND APP_EMAIL_ID IS NOT NULL ";
			Object data[][] = model.getSqlModel().getSingleResult(query);
			
			/*String[] empData = null;
			if(data != null && data.length > 0) {
				empData = new String[data.length];
				for(int i = 0; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
				}
				templateApp.configMailAlert();
				templateApp.sendApplicationMailToKeepInfo(empData);
			}*/
			
			if(data != null && data.length > 0) {
				templateApp.sendApplicationMailToGroups(data);
			}
			
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String viewApplicationFunction()
	{
		String typeOfList = asipoApprBean.getListType();
		try {
			String applicationID = request.getParameter("applicationID");
			ASIPOReconciliationApproverModel model = new ASIPOReconciliationApproverModel();
			model.initiate(context, session);
			model.viewApplicationFunction(asipoApprBean, applicationID, typeOfList);
			model.getApproverCommentList(asipoApprBean,applicationID);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(typeOfList.equals("pending"))
		{
			asipoApprBean.setEnableAll("Y");
			getNavigationPanel(1);
			asipoApprBean.setApplicationStatus("P");
		}else
			if(typeOfList.equals("approved"))
			{
				asipoApprBean.setEnableAll("N");
				getNavigationPanel(2);
				asipoApprBean.setApplicationStatus("A");
			}
			else
				{
				asipoApprBean.setEnableAll("N");
					getNavigationPanel(2);
					asipoApprBean.setApplicationStatus("R");
				}
		return SUCCESS;
	}
	
	public String closeApplication() throws Exception {
		input();
		return INPUT;
	}

}
