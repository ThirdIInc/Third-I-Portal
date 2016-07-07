/**
 * @author Anantha Lakshmi
 * 
 */

package org.struts.action.D1;

import org.paradyne.bean.D1.CustomerConciergeServiceRequest;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.CustomerConciergeServiceRequestModel;
import org.struts.lib.ParaActionSupport;

public class CustomerConciergeServiceRequestAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(CustomerConciergeServiceRequestAction.class);
	CustomerConciergeServiceRequest ccBean;
	
	public void prepare_local() throws Exception {
		ccBean = new CustomerConciergeServiceRequest();
		ccBean.setMenuCode(5001);
	}

	public Object getModel() {
		return ccBean;
	}

	public CustomerConciergeServiceRequest getCcBean() {
		return ccBean;
	}

	public void setAsipoBean(CustomerConciergeServiceRequest ccBean) {
		this.ccBean = ccBean;
	}
	
	public String input()
	{
		try {
			CustomerConciergeServiceRequestModel model = new CustomerConciergeServiceRequestModel();
			model.initiate(context, session);
			String userId = ccBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getPendingList(ccBean, request,userId);	
			}			
			ccBean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String addnew()
	{
		try {
			CustomerConciergeServiceRequestModel model = new CustomerConciergeServiceRequestModel();
			model.initiate(context, session);
			model.getLoginUserInformation(ccBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ccBean.setApplicationID("");
		ccBean.setDraftListFlag(true);
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String reset()
	{
		ccBean.setApplicationID("");
		ccBean.setApplicationID("");
		// up Anu
		ccBean.setCustName("");
		ccBean.setStreetAddr("");
		ccBean.setCustState("");
		ccBean.setCustCity("");
		ccBean.setCustPinCode("");
		ccBean.setMngrName("");
		ccBean.setNumAlertPads("");
		ccBean.setMaxOrder("");
		ccBean.setDraftListFlag(true);
		getNavigationPanel(1);
		
		return SUCCESS;
	}
	
	
	public String delete()
	{
		try {
			CustomerConciergeServiceRequestModel model = new CustomerConciergeServiceRequestModel();
			model.initiate(context, session);
			boolean result = model.delRecord(ccBean);
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
		ccBean.setApplicationID("");
		//System.out.println("STAT =============="+ccBean.getStatus());
		if(ccBean.getStatus().equals("A")){
			getApprovedList();
		}
		else if(ccBean.getStatus().equals("R")){
			getRejectedList();
		}
		else{
			input();
			ccBean.setListType("pending");
		}
		return INPUT;
	}
	
	
	public String draftFunction() throws Exception {
		try {
			
			CustomerConciergeServiceRequestModel model = new CustomerConciergeServiceRequestModel();
			model.initiate(context, session);
			boolean result;
			if (ccBean.getApplicationID().equals("")) 
			{
				result = model.draftFunction(ccBean);
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
				result = model.updateRecords(ccBean);
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
			CustomerConciergeServiceRequestModel model = new CustomerConciergeServiceRequestModel();
			model.initiate(context, session);
			logisticPersonAvailableOrNot = model.isLogisticApprover();
			if(logisticPersonAvailableOrNot){
				boolean result = model.sendForApprovalFunction(ccBean);
				
				
				
				String applicationId = ccBean.getApplicationID();
				String employeeCode = ccBean.getCompletedByID();
				String userID = ccBean.getUserEmpId();
				String status = ccBean.getStatus();
				model.terminate();
				if (result) {
					addActionMessage("Application Successfully Send For Approval.\n Tracking Number : "+ccBean.getTrackingNumber());
					
					System.out.println("=======userID========>>>>>>>>>>>>>>"+userID);
					
					System.out.println("=======employeeCode========>>>>>>>>>>>>>>"+employeeCode);
					
					sendApplicantToGroupMail(applicationId, userID, employeeCode, status);
				} else {
					addActionMessage("Error occured sending application.");
				}
			}else{
				addActionMessage("Customer Concierge Program Group(Logistics Department)authority approver is not available, \nPlease contact to Admin.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(logisticPersonAvailableOrNot){
			input();
			return INPUT;
		}else{
			if(ccBean.getApplicationID().equals(""))
			{
				getNavigationPanel(1);
			}else{
				getNavigationPanel(2);
				ccBean.setApplicationStatus("D");
			}
				ccBean.setEnableAll("Y");
			return SUCCESS;
		}
		
	}
	
	
	
	
	private void sendApplicantToGroupMail(String applicationId,String userID, String employeeCode, String status)
	{
		try {
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CUSTOMER CONCIERGE SERVICE REQUEST APPLICATION FROM APPLICANT TO APPROVER");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1,userID);
			
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, "0");
			
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			
			templateApp.configMailAlert();
			CustomerConciergeServiceRequestModel model = new CustomerConciergeServiceRequestModel();
			model.initiate(context, session);
			String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID FROM HRMS_D1_APPROVAL_SETTINGS "
							+ " WHERE APP_SETTINGS_TYPE ='D' AND APP_EMAIL_ID IS NOT NULL ";
			Object data[][] = model.getSqlModel().getSingleResult(query);
			if(data!=null && data.length>0){
				templateApp.sendApplicationMailToGroups(data);
			}
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
		System.out.println("=======hiddenStatus======="+hiddenStatus);
		try {
			CustomerConciergeServiceRequestModel model = new CustomerConciergeServiceRequestModel();
			model.initiate(context, session);
			String applCode=request.getParameter("applCode");
			if(applCode !=null &&applCode.length()>0){
				purchaseHiddenID=applCode;
			}
			
			model.viewApplicationFunction(ccBean, purchaseHiddenID);
			model.terminate();
		
		if (hiddenStatus.equals("D")) {
			getNavigationPanel(2);
			ccBean.setEnableAll("Y");
			ccBean.setApplicationStatus("D");
			ccBean.setDraftListFlag(true);
		} else if (hiddenStatus.equals("P")) {
			getNavigationPanel(3);
			ccBean.setEnableAll("N");
			ccBean.setApplicationStatus("P");
			ccBean.setPendingListFlag(true);
		} else if (hiddenStatus.equals("B")) {
			getNavigationPanel(2);			
			ccBean.setEnableAll("Y");
			ccBean.setApplicationStatus("B");
			ccBean.setApproverCommentsFlag(true);
			ccBean.setDraftListFlag(true);
		} else if (hiddenStatus.equals("A")) {
			getNavigationPanel(4);
			ccBean.setEnableAll("N");
			ccBean.setApprovedListFlag(true);
			ccBean.setApplicationStatus("A");
			ccBean.setListType("approved");
		} else if (hiddenStatus.equals("X")) {
			getNavigationPanel(3);
			ccBean.setEnableAll("N");
			ccBean.setApplicationStatus("X");
		} else if (hiddenStatus.equals("C")) {
			getNavigationPanel(3);
			ccBean.setEnableAll("N");
			ccBean.setApplicationStatus("C");
		} else if (hiddenStatus.equals("R")) {
			getNavigationPanel(3);
			ccBean.setEnableAll("N");
			ccBean.setRejectedListFlag(true);
			ccBean.setApplicationStatus("R");
			ccBean.setApproverCommentsFlag(true);
			ccBean.setRejectedListFlag(true);
		} else {
			getNavigationPanel(3);
			ccBean.setEnableAll("N");
			ccBean.setApplicationStatus("Z");
		}if(applCode !=null &&applCode.length()>0){
			getNavigationPanel(0);
			ccBean.setEnableAll("N");
		}
	} catch (Exception e) {
		getNavigationPanel(0);
		ccBean.setEnableAll("N");
		e.printStackTrace();
	}
		return SUCCESS;
	}
	
	
	public String getApprovedList() throws Exception {		
		try {
			CustomerConciergeServiceRequestModel model = new CustomerConciergeServiceRequestModel();
			model.initiate(context, session);
			String userId = ccBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getApprovedList(ccBean, request, userId);
			}	
			ccBean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	public String getCancelledList() throws Exception {		
		try {
			CustomerConciergeServiceRequestModel model = new CustomerConciergeServiceRequestModel();
			model.initiate(context, session);
			String userId = ccBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getCancelledList(ccBean, request, userId);
			}	
			ccBean.setListType("cancelled");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	public String getRejectedList() throws Exception {		
		try {
			CustomerConciergeServiceRequestModel model = new CustomerConciergeServiceRequestModel();
			model.initiate(context, session);
			String userId = ccBean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getRejectedList(ccBean, request, userId);
			}
			ccBean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
}
