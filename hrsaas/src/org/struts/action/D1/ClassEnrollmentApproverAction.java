package org.struts.action.D1;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.bean.D1.ClassEnrollmentApprover;
import org.paradyne.bean.D1.HardwareSoftwareRequestApprover;
import org.paradyne.bean.D1.ReqToBackOutApprover;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ClassEnrollmentApproverModel;
import org.paradyne.model.D1.HardwareSoftwareRequestApproverModel;
import org.paradyne.model.D1.NewHireRehireApprovalModel;
import org.paradyne.model.D1.PersonalDataChangeApprovalModel;
import org.paradyne.model.D1.PersonalDataChangeModel;
import org.paradyne.model.D1.ReqToBackOutApproverModel;
import org.struts.lib.ParaActionSupport;

public class ClassEnrollmentApproverAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ReqToBackOutVoucherAction.class);
	
	ClassEnrollmentApprover bean;
	
	

	public ClassEnrollmentApprover getReqApproverBean() {
		return bean;
	}

	public void setReqApproverBean(ClassEnrollmentApprover reqApproverBean) {
		this.bean = bean;
	}

	public void prepare_local() throws Exception {
		bean = new ClassEnrollmentApprover();
		bean.setMenuCode(2006);
	}

	public Object getModel() {
		return bean;
	}
	
	public String input()
	{
		try {
			ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isITDeptApprover = model.isITDeptApprover(userId);
			if(isITDeptApprover)
			{
				model.getPendingList(bean, request);	
			}
			else
			{
				model.getPendingList(bean, request,userId);	
			}
			bean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	
	public String getApprovedList() throws Exception {		
		try {
			ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
			model.initiate(context, session);
			
			String userId = bean.getUserEmpId();
			boolean isITDeptApprover = model.isITDeptApprover(userId);
			if(isITDeptApprover)
			{
				model.getApprovedList(bean, request);
			}
			else
			{
				model.getApprovedList(bean, request, userId);
			}
			bean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	public String getRejectedList() throws Exception {		
		try {
			ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isITDeptApprover = model.isITDeptApprover(userId);
			if(isITDeptApprover)
			{
				model.getRejectedList(bean, request);
			}
			else
			{
				model.getRejectedList(bean, request, userId);
			}
			bean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	/*public String approveApplication()
	{
		try {	
			String applicationId = bean.getClassEnrollmentApproverId();
			String approverComments = bean.getApproverComments();
			String userId = bean.getUserEmpId();
			String employeeCode = bean.getEmployeeCode();
			
			System.out.println("employeeCode=======" + employeeCode);
			String status = bean.getStatus();
			System.out.println("status=======" + status);
			
			String hrApprover = bean.getHrApprover();
			System.out.println("hrApprover=======" + hrApprover);
			
			int level = Integer.parseInt(bean.getLevel());

			ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
			model.initiate(context, session);

			String nextApprover = userId;

			Object[][] empFlow = generateEmpFlow(requesterCode, "D1", (level + 1));

			if(empFlow != null && empFlow.length > 0) {
				nextApprover = String.valueOf(empFlow[0][0]);
				level += 1;
			}
			
			
			
			if(status.equals("C")) {
				Object[][] empFlow = generateEmpFlow(employeeCode, "D1", 1);
				
				if(empFlow != null && empFlow.length > 0) {
					nextApprover = String.valueOf(empFlow[0][0]);
				}
			} else {
				Object[][] empFlow = generateEmpFlow(employeeCode, "D1", (level + 1));

				if(empFlow != null && empFlow.length > 0) {
					nextApprover = String.valueOf(empFlow[0][0]);
					level += 1;
				}
			}
			
			System.out.println("nextApprover ===== " + nextApprover);
		//	System.out.println("level = "+ level);
			status = model.approve(applicationId, approverComments, userId, status, nextApprover, level);

			Object[][] apprCommentListObj = model.getApproverCommentList(applicationId);
			populateApproverComments(apprCommentListObj);
			bean.setStatus(status);			
			bean.setApproverComments("");

			addActionMessage("Application approved successfully.");

			if(model.isUserAMagaer(applicationId, userId)) {
				sendApprovalMail(applicationId, userId, employeeCode, status,hrApprover);
			} else {
				sendApprovalMailToApplicantFinal(applicationId, userId, employeeCode, status);
			}
			
			model.terminate();
	    } catch (Exception e) {
		  e.printStackTrace();
	  }	
	    input();
	    return INPUT;
	}*/
	
	public String approveApplication()
	{	
		String applicationId = bean.getClassEnrollmentApproverId();
		//String employeeCode = reqApproverBean.getEmployeeID();
		String userId = bean.getUserEmpId();
		String status = bean.getStatus();
		String employeeCode = bean.getEmployeeCode();
		String initiatorCode = bean.getInitiatorCode();
		System.out.println("initiatorCode  ======" + initiatorCode);
		try {
			System.out.println("status  ======" + status);
			ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
			model.initiate(context, session);
			boolean result = model.approveApplicationFunction(bean, status);
			model.terminate();
			if (result) {
				addActionMessage("Application approved successfully.");
				sendApprovalMailToApplicantFinal(applicationId, userId, employeeCode, status,initiatorCode);
			} else {
				addActionMessage("Error occured.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}
		
	private void sendApprovalMail(String applicationId, String userId,
			String employeeCode, String status, String hrApprover) {
		try {
			System.out.println("SEND APPROVAL MAIL");
			// if(status.equals("approved")){

			// MAIL FROM APPROVER TO APPLICANT

			try {
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate("D1-CLASS ENROLLMENT APPLICATION DETAILS FROM  APPROVER TO APPLICANT");
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, userId);
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, employeeCode);
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applicationId);
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, applicationId);
				EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, applicationId);
				template.configMailAlert();
				// template.sendProcessManagerAlert(approver, module, msgType,
				// applnID, level);
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				
				ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
				// MAIL FROM APPROVER TO MANAGER
				model.initiate(context, session);
				/*
				 * String query = "SELECT NVL(APP_SETTINGS_EMP_ID,0) FROM HRMS_D1_APPROVAL_SETTINGS " + " where APP_SETTINGS_TYPE = 'H' ORDER BY
				 * APP_SETTINGS_EMP_ID";
				 */

				String query = " SELECT NVL(APP_EMAIL_ID,0) AS MANAGER_ID  "
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " WHERE APP_SETTINGS_TYPE = 'T' AND APP_EMAIL_ID IS NOT NULL  ";
				Object data[][] = model.getSqlModel().getSingleResult(query);
				
				for (int j = 0; j < data.length; j++) {
					if(!String.valueOf(data[j][0]).equals(hrApprover)){
						
						EmailTemplateBody templateApp = new EmailTemplateBody();
						templateApp.initiate(context, session);
						templateApp.setEmailTemplate("D1-CLASS ENROLLMENT DETAILS FROM  APPROVER TO MANAGER");
						templateApp.getTemplateQueries();
						EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
						templateQueryApp1.setParameter(1, userId);
						EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
						templateQueryApp2.setParameter(1, String.valueOf(data[0][0]));
						EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
						templateQueryApp3.setParameter(1, applicationId);
						EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
						templateQueryApp4.setParameter(1, applicationId);
						EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
						templateQueryApp5.setParameter(1, applicationId);
						
						EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
						templateQueryApp6.setParameter(1, String.valueOf(data[0][0]));
						
						
						String[] empData = null;
						
						if(data != null && data.length > 1) {
							empData = new String[data.length];
							for(int i = 1; i < empData.length; i++) {
								empData[i] = String.valueOf(data[i][0]);
							}
						}
						System.out.println("empData = " + empData.length);
						templateApp.configMailAlert();
						templateApp.sendApplicationMailToKeepInfo(empData);

						templateApp.sendApplicationMail();
						templateApp.clearParameters();
						templateApp.terminate();
					}else{
						
					}
				}
				
				
				
				
				
				

				model.terminate();

			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			logger.error(e);
		}

	}
	
	private void sendApprovalMailToApplicantFinal(String applicationId, String userId, String employeeCode, String status, String initiatorCode) {
		try {
			ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
			// MAIL FROM APPROVER TO MANAGER
			model.initiate(context, session);
			
			
			String requestQuery = " select NVL(EMP_ID,0) from HRMS_D1_CLASS_ENROLL where ENROLL_ID = "+ applicationId;
			Object initiatorData[][] = model.getSqlModel().getSingleResult(requestQuery);
			
			/*String apprQuery = " SELECT ENRL_APPR FROM HRMS_D1_CLAS_ENRL_DATA_PATH WHERE ENRL_APPL_ID =  "+ applicationId;
			Object apprDataObj[][] = model.getSqlModel().getSingleResult(apprQuery);*/
			
			
			
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CLASS ENROLLMENT APPLICATION DETAILS APPROVE/REJECTED/SENDBACK FROM  HR");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, initiatorCode);
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			
		//	if (data != null && data.length > 0) {
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, userId);
		//	}
			
			
			String query = " SELECT NVL(APP_EMAIL_ID,0) AS MANAGER_ID "
				+ " FROM HRMS_D1_APPROVAL_SETTINGS "
				+ " WHERE APP_SETTINGS_TYPE = 'T' AND APP_EMAIL_ID IS NOT NULL ";// ORDER BY APP_SETTINGS_EMP_ID";
			Object data[][] = model.getSqlModel().getSingleResult(query);
			
			String[] empData = null;
			
			if(initiatorData != null && initiatorData.length > 0) {
				empData = new String[initiatorData.length];
				for(int i = 0; i < empData.length; i++) {
					empData[i] = String.valueOf(initiatorData[i][0]);
				}
			}
			/*String[] trainingData = null;
			
			if(data != null && data.length > 0) {
				trainingData = new String[data.length];
				for(int i = 1; i < trainingData.length; i++) {
					trainingData[i] = String.valueOf(data[i][0]);
				}
			}*/
			
			/*String[] approverData = new String[apprDataObj.length];
			if (apprDataObj != null && apprDataObj.length > 1) {
				approverData = new String[apprDataObj.length];
				for (int i = 0; i < approverData.length; i++) {
					approverData[i] = String.valueOf(apprDataObj[i][0]);
				}
			}*/
			
			templateApp.configMailAlert();
			
			
			//keepData[1]=String.valueOf(apprDataObj[0][0]);
			
			
			if (status.equals("A")) {
				if(!bean.getEmployeeCode().equals(bean.getInitiatorCode())){
					templateApp.sendApplicationMailToKeepInfo(String.valueOf(initiatorData[0][0]));
				}
				
			}
			/*if (data != null && data.length > 0) {
				String keepData[]=new String[1];
				keepData[0]=String.valueOf(data[0][0]);
				templateApp.sendApplicationMailToKeepInfo(keepData);
			}*/
			
			if(data != null && data.length > 0) {
				System.out.println("@@@@@@@@@@@@@@@@2222@@@");
				templateApp.sendApplicationMailToGroups(data);
			}
			
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	private void populateApproverComments(Object[][] apprCommentListObj) {
		ArrayList approverCommentList = new ArrayList<Object>(apprCommentListObj.length);

		for(int i = 0; i < apprCommentListObj.length; i++) {
			ClassEnrollmentApprover innerBean = new ClassEnrollmentApprover();
			innerBean.setApprName(checkNull(String.valueOf(apprCommentListObj[i][0])));
			innerBean.setApprComments(checkNull(String.valueOf(apprCommentListObj[i][1])));
			innerBean.setApprDate(checkNull(String.valueOf(apprCommentListObj[i][2])));
			innerBean.setApprStatus(checkNull(String.valueOf(apprCommentListObj[i][3])));
			approverCommentList.add(innerBean);
		}
		bean.setApproverCommentList(approverCommentList);
	}
	
	
	public String rejectApplication()
	{
		try {
			String applicationId = bean.getClassEnrollmentApproverId();
			String approverComments = bean.getApproverComments();
			String userId = bean.getUserEmpId();
			String initiatorCode = bean.getInitiatorCode();
			String status = bean.getStatus();
			String employeeCode = bean.getEmployeeCode();
			ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
			model.initiate(context, session);
			model.reject(applicationId, approverComments, userId);

			/*Object[][] apprCommentListObj = model.getApproverCommentList(applicationId);
			populateApproverComments(apprCommentListObj);
			model.setBussinessUnit(bean);
		    model.setSpecialSoftwareItemsRequested(bean);*/
			sendRejectSenBackMailToApplicant(applicationId, userId, employeeCode, status,initiatorCode);
			model.terminate();
			addActionMessage("Application rejected successfully.");

		//sendRejectSenBackMailToApplicant(persDataChangeId, userId, employeeCode, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
	    return INPUT;
	}
	
	
	public String sendBackApplication()
	{
		try {
			String applicationId = bean.getClassEnrollmentApproverId();
			String approverComments = bean.getApproverComments();
			String userId = bean.getUserEmpId();
			String employeeCode = bean.getEmployeeCode();
			String initiatorCode = bean.getInitiatorCode();
			ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
			model.initiate(context, session);

			String nextApprover = userId;
			
			Object[][] empFlow = generateEmpFlow(employeeCode, "D1", 1);
			if(empFlow != null && empFlow.length > 0) {
				nextApprover = String.valueOf(empFlow[0][0]);
			}

			String status = model.sendBack(applicationId, approverComments, userId, nextApprover);

			/*Object[][] apprCommentListObj = model.getApproverCommentList(applicationId);
			populateApproverComments(apprCommentListObj);
			model.setBussinessUnit(bean);
		    model.setSpecialSoftwareItemsRequested(bean);*/
		    
			bean.setStatus(status);
			bean.setApproverComments("");
			
			sendRejectSenBackMailToApplicant(applicationId, userId, employeeCode, status , initiatorCode);
			
			
			model.terminate();

			addActionMessage("Application send back successfully.");
		//	sendRejectSenBackMailToApplicant(applicationId, userId, employeeCode, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
	    return INPUT;
	}
	private void sendRejectSenBackMailToApplicant(String applicationId, String userId, String employeeCode, String status, String initiatorCode) {
		try {
			
			System.out.println("initiatorCode===" + initiatorCode);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			
			ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
			// MAIL FROM APPROVER TO MANAGER
			model.initiate(context, session);
			String query = " SELECT NVL(APP_EMAIL_ID,0) AS MANAGER_ID  "
				+ " FROM HRMS_D1_APPROVAL_SETTINGS "
				+ " WHERE APP_SETTINGS_TYPE = 'T' AND APP_EMAIL_ID IS NOT NULL "; //AND APP_SETTINGS_EMP_ID NOT IN ("+bean.getUserEmpId()+")";// ORDER BY APP_SETTINGS_EMP_ID";
			Object data[][] = model.getSqlModel().getSingleResult(query);
			
			
			
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CLASS ENROLLMENT APPLICATION DETAILS APPROVE/REJECTED/SENDBACK FROM  HR");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, initiatorCode);
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, userId);
			
			templateApp.configMailAlert();
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);
			
			if(data != null && data.length > 0) {
				templateApp.configMailAlert();
				templateApp.sendApplicationMailToGroups(data);
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
		String typeOfList = bean.getListType();
		logger.info("List Type : "+typeOfList);
		bean.setListTypeDetailPage(typeOfList);
		String applCode=request.getParameter("applCode");
		try {
			String applicationID = request.getParameter("enrollmentHiddenID");
			ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
			model.initiate(context, session);
			/*
			 * FOR SUPER USER
			 */
			
			if(applCode !=null &&applCode.length()>0){
				applicationID=applCode;
			}
			
			model.viewApplicationFunction(bean, applicationID, typeOfList);
			Object[][] apprCommentListObj = model.getApproverCommentList(applicationID);
			populateApproverComments(apprCommentListObj);
			model.terminate();
		
		if(typeOfList.equals("pending"))
		{
			bean.setApproverCommentsFlag(true);
			getNavigationPanel(5);
			bean.setEnableAll("N");
		}else
			if(typeOfList.equals("approved"))
			{
				
				getNavigationPanel(2);
				bean.setEnableAll("N");
			}
			else
				{
					bean.setEnableAll("N");
					getNavigationPanel(2);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(applCode !=null &&applCode.length()>0){
			String status = bean.getStatus();
			System.out.println("status=======" + status);
			
			if(status.equals("D")|| status.equals("A")|| status.equals("B")|| status.equals("R"))
			{
				getNavigationPanel(7);
				bean.setEnableAll("N");
			}else {
					bean.setApproverCommentsFlag(true);
					getNavigationPanel(6);
					bean.setEnableAll("N");
					
				}
		}
		return SUCCESS;
	}
	
	
	public String backToList() throws Exception {
		input();
		return INPUT;
	}
	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

}
