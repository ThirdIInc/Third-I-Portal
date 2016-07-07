/**
 * @author Anantha lakshmi 
 */
package org.struts.action.D1;
import org.paradyne.bean.D1.CustomerConciergeServiceRequestApprover;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.CustomerConciergeServiceRequestApproverModel;
import org.struts.lib.ParaActionSupport;
public class CustConciergeServiceReqApproverAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustConciergeServiceReqApproverAction.class);
	CustomerConciergeServiceRequestApprover ccsrApprBean;
	public void prepare_local() throws Exception {
		ccsrApprBean = new CustomerConciergeServiceRequestApprover();
		ccsrApprBean.setMenuCode(5002);
	}

	public CustomerConciergeServiceRequestApprover getCcApprBean() {
		return ccsrApprBean;
	}

	public void setAsipoApprBean(CustomerConciergeServiceRequestApprover ccsrApprBean) {
		this.ccsrApprBean = ccsrApprBean;
	}

	public Object getModel() {
		return ccsrApprBean;
	}
	
	public String input()
	{
		try {
			CustomerConciergeServiceRequestApproverModel model = new CustomerConciergeServiceRequestApproverModel();
			model.initiate(context, session);
			String userId = ccsrApprBean.getUserEmpId();
			boolean isLogisticApprover = model.isLogisticApprover(userId);
			if(isLogisticApprover)
			{
				model.getpendingList(ccsrApprBean, request);
			}
			ccsrApprBean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	
	public String getApprovedList() throws Exception {		
		try {
			CustomerConciergeServiceRequestApproverModel model = new CustomerConciergeServiceRequestApproverModel();
			model.initiate(context, session);
			String userId = ccsrApprBean.getUserEmpId();
			boolean isLogisticApprover = model.isLogisticApprover(userId);
			if(isLogisticApprover)
			{
				model.getApprovedList(ccsrApprBean, request);
			}
			ccsrApprBean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	public String getRejectedList() throws Exception {		
		try {
			CustomerConciergeServiceRequestApproverModel model = new CustomerConciergeServiceRequestApproverModel();
			model.initiate(context, session);
			String userId = ccsrApprBean.getUserEmpId();
			boolean isLogisticApprover = model.isLogisticApprover(userId);
			if(isLogisticApprover)
			{
				model.getRejectedList(ccsrApprBean, request);
			}
			ccsrApprBean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	
	public String approveApplication()
	{	
		String applicationId = ccsrApprBean.getApplicationID();
		String employeeCode = ccsrApprBean.getCompletedByID();
		String approverID = ccsrApprBean.getUserEmpId();
		String status = ccsrApprBean.getStatus();
		try {
			CustomerConciergeServiceRequestApproverModel model = new CustomerConciergeServiceRequestApproverModel();
			model.initiate(context, session);
			//boolean result = model.approveApplicationFunction(ccsrApprBean, status);
			boolean result = model.updateStatus(ccsrApprBean, status, applicationId);
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
		//String applicationId = ccsrApprBean.getPurchaseID();
		String applicationId = ccsrApprBean.getApplicationID();
		String employeeCode = ccsrApprBean.getCompletedByID();
		String approverID = ccsrApprBean.getUserEmpId();
		String status = ccsrApprBean.getStatus();
		try {
			CustomerConciergeServiceRequestApproverModel model = new CustomerConciergeServiceRequestApproverModel();
			model.initiate(context, session);
			//boolean result = model.rejectApplicationFunction(ccsrApprBean, status);
			boolean result = model.updateStatus(ccsrApprBean, status, applicationId);
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
		//String applicationId = ccsrApprBean.getPurchaseID();
		String applicationId = ccsrApprBean.getApplicationID();
		String employeeCode = ccsrApprBean.getCompletedByID();
		String approverID = ccsrApprBean.getUserEmpId();
		String status = ccsrApprBean.getStatus();
		try {
			CustomerConciergeServiceRequestApproverModel model = new CustomerConciergeServiceRequestApproverModel();
			model.initiate(context, session);
			//boolean result = model.sendBackApplicationFunction(ccsrApprBean, status);
			boolean result = model.updateStatus(ccsrApprBean, status, applicationId);
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
	
	

	
	
	private void sendApproverToApplicantMail(String applicationId,String approverID, String employeeCode, String status)
	{
		try {
			CustomerConciergeServiceRequestApproverModel model = new CustomerConciergeServiceRequestApproverModel();
			// MAIL FROM HR TO MANAGER
			model.initiate(context, session);
			
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CUSTOMER CONCIERGE SERVICE REQUEST APPLICATION FROM APPROVER TO APPLICANT");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, approverID);
			
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, employeeCode);
			
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);
			
			templateApp.configMailAlert();
			
			String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID FROM HRMS_D1_APPROVAL_SETTINGS "
							+ " WHERE APP_SETTINGS_TYPE ='D' AND APP_EMAIL_ID IS NOT NULL";
			Object data[][] = model.getSqlModel().getSingleResult(query);
			if(data!=null && data.length>0){
				System.out.println("=============B4=============");
				//templateApp.sendApplicationMailToGroups(data);
				System.out.println("==========AFTER================");
			}
			//templateApp.sendApplicationMailToKeepInfo(new String[]{terApprBean.getEmployeeNumber()});
			
			String initiatorQuery = " SELECT EMP_FNAME||' '||EMP_LNAME  FROM HRMS_D1_CUST_SERVICE_REQ "
									+" INNER JOIN HRMS_EMP_OFFC  ON (HRMS_EMP_OFFC.EMP_ID=HRMS_D1_CUST_SERVICE_REQ.CCSR_COMPLETED_BY) "
									+" WHERE CCSR_APPLICATION_ID="+ applicationId;
                        Object initiatorData[][] = model.getSqlModel().getSingleResult(initiatorQuery);
                        
                        String[] empData = null;
                        
                        if(initiatorData != null && initiatorData.length > 0) {
                                empData = new String[initiatorData.length];
                                for(int i = 1; i < empData.length; i++) {
                                        empData[i] = String.valueOf(initiatorData[i][0]);
                                }
                        }
                        
                        if (status.equals("A")) {
                                
                                if(!approverID.equals(employeeCode)){
                                        System.out.println("INSIDE IFFFFFFF");
                                        //templateApp.sendApplicationMailToKeepInfo(String.valueOf(initiatorData[0][0]));
                                }
                                
                        }
                        
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	public String viewApplicationFunction()
	{
		String typeOfList = ccsrApprBean.getListType();
		try {
			String applicationID = request.getParameter("applicationID");
			CustomerConciergeServiceRequestApproverModel model = new CustomerConciergeServiceRequestApproverModel();
			model.initiate(context, session);
			model.viewApplicationFunction(ccsrApprBean, applicationID, typeOfList);
			model.getApproverCommentList(ccsrApprBean,applicationID);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(typeOfList.equals("pending"))
		{
			ccsrApprBean.setEnableAll("Y");
			getNavigationPanel(1);
			ccsrApprBean.setApplicationStatus("P");
			//ccsrApprBean.setApproverCommentFlag("true");
		}else
			if(typeOfList.equals("approved"))
			{
				ccsrApprBean.setEnableAll("N");
				getNavigationPanel(2);
				ccsrApprBean.setApplicationStatus("A");
				ccsrApprBean.setApproverCommentFlag("true");
			}
			else
			{
				ccsrApprBean.setEnableAll("N");
					getNavigationPanel(2);
					ccsrApprBean.setApplicationStatus("R");
					ccsrApprBean.setApproverCommentFlag("true");
			}
		return SUCCESS;
	}
	
	public String back() throws Exception {
		//System.out.println(" STAT"+ccsrApprBean.getStatus());
		if(ccsrApprBean.getStatus().equals("A")){
			getApprovedList();
		}
		else if(ccsrApprBean.getStatus().equals("R")){
			getRejectedList();
		}
		else{
			input();
			ccsrApprBean.setListType("pending");
		}
		return INPUT;
	}
	
	

}
